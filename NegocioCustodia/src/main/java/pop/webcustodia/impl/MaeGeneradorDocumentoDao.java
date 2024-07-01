/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.impl;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleResultSet;
import oracle.jdbc.OracleTypes;
import pop.comun.dominio.DocPlantilla;

import pop.comun.dominio.MaeDireccion;
import pop.comun.dominio.MaeEmail;
import pop.comun.dominio.MaeFondo;
import pop.comun.dominio.MaeInmueble;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MaeInversionEstado;
import pop.comun.dominio.MaePersona;
import pop.comun.dominio.MaePersonaInmueble;
import pop.comun.dominio.MaeTelefono;
import pop.comun.dominio.MaeUbigeo;

import pop.webcustodia.common.DBUtil;
import pop.webcustodia.common.ParameterDirection;
import pop.webcustodia.common.ParameterOracle;
import pop.webcustodia.iface.IMaeInversionDoa;

import pop.webcustodia.conn.Conexion;
import pop.webcustodia.dao.FactoryDao;
import pop.webcustodia.iface.IGeneraDocumentoDoa;
/**
 *
 * @author Jyoverar
 */
public class MaeGeneradorDocumentoDao extends DBUtil implements IGeneraDocumentoDoa {

    private OracleConnection cn = null;

    public MaeGeneradorDocumentoDao() {

    }

    public MaeGeneradorDocumentoDao(OracleConnection cnx) {
        cn = cnx;
    }

    

    
    
    public OracleConnection getCn() {
        return cn;
    }

    public void setCn(OracleConnection cn) {
        this.cn = cn;
    }

    

    
    @Override
    public ArrayList<MaeInversion> fetchPropietarios(MaeInversion oMaeInversion) {
        //System.out.println(" <i> MaeInversionDao fetchDebtors " + LocalDateTime.now());
        ArrayList<MaeInversion> lstInversion = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        //OracleResultSet resultSetB = null;

        try {
            // name of procedure
            String sp = "{call PKG_GENERADOR_DOC.SP_COB_BUSCAR_PERS(?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameterGD(oMaeInversion);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("ctdia");            
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                //fondo
                MaeFondo newFondo = new MaeFondo();
                newFondo.setCFondoId(resultSet.getString("C_FONDO_ID"));
                newFondo.setDFondo(resultSet.getString("D_FONDO"));
                //persona
                MaePersona newPersona = new MaePersona();
                newPersona.setMaeFondo(newFondo);
                newPersona.setCPersonaId(resultSet.getInt("C_PERSONA_ID"));
                newPersona.setANroDocumento(resultSet.getString("A_NRO_DOCUMENTO"));
                newPersona.setDApePat(resultSet.getString("D_APE_PAT"));
                newPersona.setDApeMat(resultSet.getString("D_APE_MAT"));
                newPersona.setDNombres(resultSet.getString("D_NOMBRES"));
                MaeInversion newInversion = new MaeInversion();
                newInversion.setMaeFondo(newFondo);
                newInversion.setcPersonaId(newPersona);
                newInversion.setcMaeInversionId(resultSet.getInt("C_MAE_INVERSION_ID"));
                newInversion.setcInversionId(resultSet.getString("C_INVERSION_ID"));
                newInversion.setcTipoInv(resultSet.getString("C_TIPO_INV"));
                newInversion.setCInversion(resultSet.getString("C_INVERSION"));
                newInversion.setNCuotas(resultSet.getInt("NCUOTASATRASADAS"));
                newInversion.setsDocumentoId(resultSet.getString("ULTDOCUMENTO"));
                newInversion.setDescripcion(resultSet.getString("ULTDOCUMENTO"));
                lstInversion.add(newInversion);
            }
        } catch (Exception e) {
            Logger.getLogger(MaePersona.class.getName()).log(Level.SEVERE, null, e);
        }
        finally {
            // Always make sure result sets and statements are closed,
            // and the connection is returned to the pool
            if (resultSet != null) {
                try { resultSet.close(); } catch (Exception e) { ; }
                resultSet = null;
              }
            if (cmd != null) {
              try { cmd.close(); } catch (Exception e) { ; }
              cmd = null;
            }
            if (cn != null){
              try { cn.close(); } catch (Exception e) { ; }
                cn = null;
            }
        }
        
        System.out.println("Cartas notariales...: " + LocalDateTime.now());
        
        return lstInversion;
    }

    
    @Override
    public ArrayList<MaeInversion> fetchClientes(MaeInversion oMaeInversion) {
        System.out.println(" <i> MaeInversionDao fetchDebtors " + LocalDateTime.now());
        ArrayList<MaeInversion> lstInversion = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        //OracleResultSet resultSetB = null;

        try {
            // name of procedure
            String sp = "{call PKG_GENERADOR_DOC.SP_COB_BUSCAR_CLIENTES(?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();

            // fill parameters
            oList.add(new ParameterOracle("PI_C_FONDO_ID", oMaeInversion.getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
            System.out.println(" oMaeInversion.getMaeFondo().getCFondoId() : "+oMaeInversion.getMaeFondo().getCFondoId());
            oList.add(new ParameterOracle("PI_C_INVERSION", oMaeInversion.getCInversion(), OracleTypes.CHAR, ParameterDirection.Input));
            System.out.println(" oMaeInversion.getCInversion() : "+oMaeInversion.getCInversion());
            oList.add(new ParameterOracle("PI_A_NRO_DOCUMENTO", oMaeInversion.getcPersonaId().getANroDocumento(), OracleTypes.CHAR, ParameterDirection.Input));
            System.out.println(" oMaeInversion.getcPersonaId().getANroDocumento() : "+oMaeInversion.getcPersonaId().getANroDocumento());
            oList.add(new ParameterOracle("PI_C_TABLA_DET_ID", oMaeInversion.getSCODDocumento(), OracleTypes.CHAR, ParameterDirection.Input));
            System.out.println(" oMaeInversion.getSCODDocumento() : "+oMaeInversion.getSCODDocumento());
            oList.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
            oList.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
            oList.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

            //Abre conexion a la BDS
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("ctdia");            
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                //fondo
                MaeFondo newFondo = new MaeFondo();
                newFondo.setCFondoId(resultSet.getString("C_FONDO_ID"));
                newFondo.setDFondo(resultSet.getString("D_FONDO"));
                //persona
                MaePersona newPersona = new MaePersona();
                newPersona.setMaeFondo(newFondo);
                newPersona.setCPersonaId(resultSet.getInt("C_PERSONA_ID"));
                newPersona.setANroDocumento(resultSet.getString("A_NRO_DOCUMENTO"));
                newPersona.setDApePat(resultSet.getString("D_APE_PAT"));
                newPersona.setDApeMat(resultSet.getString("D_APE_MAT"));
                newPersona.setDNombres(resultSet.getString("D_NOMBRES"));
                MaeInversion newInversion = new MaeInversion();
                newInversion.setMaeFondo(newFondo);
                newInversion.setcPersonaId(newPersona);
                newInversion.setcMaeInversionId(resultSet.getInt("C_MAE_INVERSION_ID"));
                newInversion.setcInversionId(resultSet.getString("C_INVERSION_ID"));
                newInversion.setcTipoInv(resultSet.getString("C_TIPO_INV"));
                newInversion.setCInversion(resultSet.getString("C_INVERSION"));
                newInversion.setNCuotas(resultSet.getInt("NCUOTASATRASADAS"));
                newInversion.setsDocumentoId(resultSet.getString("ULTDOCUMENTO"));
                newInversion.setDescripcion(resultSet.getString("ULTDOCUMENTO"));
                lstInversion.add(newInversion);
            }
        } catch (Exception e) {
            Logger.getLogger(MaePersona.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();///
        }
        finally {
            // Always make sure result sets and statements are closed,
            // and the connection is returned to the pool
            if (resultSet != null) {
                try { resultSet.close(); } catch (Exception e) { ; }
                resultSet = null;
              }
            if (cmd != null) {
              try { cmd.close(); } catch (Exception e) { ; }
              cmd = null;
            }
            if (cn != null){
              try { cn.close(); } catch (Exception e) { ; }
                cn = null;
            }
        }
        
        System.out.println("Cartas notariales...: " + LocalDateTime.now());
        
        return lstInversion;
    }

        
    
    @Override
    public ArrayList<MaeInversion> fetchAllPropietarios(MaeInversion oMaeInversion) {
        //System.out.println(" <i> MaeInversionDao fetchDebtors " + LocalDateTime.now());
        ArrayList<MaeInversion> lstInversion = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        //OracleResultSet resultSetB = null;
                
                
        try {
             //System.out.println(" PKG_GENERADOR_DOC.SP_COB_BUSCAR_PROPIETARIO(?,?,?,?,?,?");
            // name of procedure
            String sp = "{call PKG_GENERADOR_DOC.SP_COB_BUSCAR_PROPIETARIO(?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            
            oList = listParameterTP(oMaeInversion);
            
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("ctdia");            
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                
                
                //fondo
                MaeFondo newFondo = new MaeFondo();
                newFondo.setCFondoId(resultSet.getString("C_FONDO_ID"));
                newFondo.setDFondo(resultSet.getString("D_FONDO"));
                //persona
                MaePersona newPersona = new MaePersona();
                newPersona.setMaeFondo(newFondo);
                newPersona.setCPersonaId(resultSet.getInt("C_PERSONA_ID"));
                newPersona.setANroDocumento(resultSet.getString("A_NRO_DOCUMENTO"));
                newPersona.setDApePat(resultSet.getString("D_APE_PAT"));
                newPersona.setDApeMat(resultSet.getString("D_APE_MAT"));
                newPersona.setDNombres(resultSet.getString("D_NOMBRES"));
                newPersona.setPorcentaje(resultSet.getInt("P_INMUEBLE"));
                MaeInversion newInversion = new MaeInversion();
                newInversion.setMaeFondo(newFondo);
                newInversion.setcPersonaId(newPersona);
                newInversion.setcMaeInversionId(resultSet.getInt("C_MAE_INVERSION_ID"));
                newInversion.setcInversionId(resultSet.getString("C_INVERSION_ID"));
                newInversion.setcTipoInv(resultSet.getString("C_TIPO_INV"));
                newInversion.setCInversion(resultSet.getString("C_INVERSION"));
                //newInversion.setNCuotas(resultSet.getInt("NCUOTASATRASADAS"));
                //newInversion.setsDocumentoId(resultSet.getString("ULTDOCUMENTO"));
                //newInversion.setDescripcion(resultSet.getString("ULTDOCUMENTO"));
                lstInversion.add(newInversion);
            }
        } catch (Exception e) {
            Logger.getLogger(MaePersona.class.getName()).log(Level.SEVERE, null, e);
        }
        finally {
            // Always make sure result sets and statements are closed,
            // and the connection is returned to the pool
            if (resultSet != null) {
                try { resultSet.close(); } catch (Exception e) { ; }
                resultSet = null;
              }
            if (cmd != null) {
              try { cmd.close(); 
              } catch (Exception e) { ; }
              cmd = null;
            }
            if (cn != null){
              try { cn.close(); } catch (Exception e) { ; }
                cn = null;
            }
        }
        //System.out.println(" <f> MaeInversionDao fetchDebtors " + LocalDateTime.now());
        return lstInversion;
    }


    @Override
    public ArrayList<MaeInversion> listarHistoricoCarta(MaeInversion oMaeInversion) {
        //System.out.println(" <i> MaeInversionDao fetchDebtors " + LocalDateTime.now());
        ArrayList<MaeInversion> lstInversion = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        //OracleResultSet resultSetB = null;

        try {
            // name of procedure
            String sp = "{call PKG_GENERADOR_DOC.SP_HISTORICO_CARTA(?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameterMC(oMaeInversion);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("ctdia");            
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();
            while (resultSet.next()) {
                MaeFondo newFondo = new MaeFondo();
                newFondo.setCFondoId(resultSet.getString("CODFONDO"));
                //persona
                MaeInversion newInversion = new MaeInversion();
                newInversion.setMaeFondo(newFondo);
                newInversion.setCInversion(resultSet.getString("DVALOR_BV"));
                newInversion.setDescripcion(resultSet.getString("CARTA"));
                newInversion.setcUsuarioAdd(resultSet.getString("C_USUARIO_ADD"));
                newInversion.setDFechaIng(resultSet.getDate("FRECIBIDO"));
                newInversion.setcTipoInv(resultSet.getString("TIPOREC"));
                newInversion.setSEstado(resultSet.getString("TRECIBIDO"));
                newInversion.setFEmision(resultSet.getDate("FEMISION"));
                newInversion.setDesDestino(resultSet.getString("NOM_ARCHIVO"));
                newInversion.setSCODDocumento(resultSet.getString("TIPOFORMATO"));
                newInversion.setSTCarta(resultSet.getString("TIPOCARTA"));
                newInversion.setSComentario(resultSet.getString("SCOMENTARIO"));
                newInversion.setSNombreSol(resultSet.getString("SPERRECIBIDO"));
                lstInversion.add(newInversion);
            }
        } catch (Exception e) {
            Logger.getLogger(MaePersona.class.getName()).log(Level.SEVERE, null, e);
        }
        finally {
            // Always make sure result sets and statements are closed,
            // and the connection is returned to the pool
            if (resultSet != null) {
                try { resultSet.close(); } catch (Exception e) { ; }
                resultSet = null;
              }
            if (cmd != null) {
              try { cmd.close(); } catch (Exception e) { ; }
              cmd = null;
            }
            if (cn != null){
              try { cn.close(); } catch (Exception e) { ; }
                cn = null;
            }
        }
        //System.out.println(" <f> MaeInversionDao fetchDebtors " + LocalDateTime.now());
        return lstInversion;
    }
    

    @Override
    public Integer fetchAnulaCarta(MaeInversion oMaeInversion) {
        System.out.println(" <i> MaeInversionDao SP_ANULA_CARTAS " );
        Integer Rspta = 1;
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        //OracleResultSet resultSetB = null;

        try {
            // name of procedure
            String sp = "{call PKG_GENERADOR_DOC.SP_ANULA_CARTAS(?,?,?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            
           
            oList = listParAnula(oMaeInversion);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
    
            cn = conex.ConexionOpen("ctdia");
            // execute procedure
            // execute procedure
            runSP(oList, cn, cmd, sp);
            Rspta = getOutputParameter("PO_I_PROCESO").getParameterInt();
            
            
        } catch (Exception e) {
            Logger.getLogger(MaeInversion.class.getName()).log(Level.SEVERE, null, e);
            Rspta=0;
        }
        finally 
        {
      
              //    Rspta=1;
      
            // Always make sure result sets and statements are closed,
            // and the connection is returned to the pool
            if (resultSet != null) {
                try { resultSet.close(); } catch (Exception e) { ; }
                resultSet = null;
              }
            if (cmd != null) {
              try { cmd.close(); } catch (Exception e) { ; }
              cmd = null;
            }
            if (cn != null) {
              try { cn.close(); } catch (Exception e) { ; }
                cn = null;
            }
        }
        //System.out.println(" <f> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        return Rspta;
    }

    
    @Override
    public Integer fetchInsGeneraDoc(MaeInversion oMaeInversion) {
        System.out.println(" <i> MaeInversionDao SP_INSERTAR_CARTAS " );
        Integer Rspta = 1;
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        //OracleResultSet resultSetB = null;

        try {
            // name of procedure
            String sp = "{call PKG_GENERADOR_DOC.SP_INSERTAR_CARTAS(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            
           
            oList = listParameterInsCartas(oMaeInversion);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
    
            cn = conex.ConexionOpen("ctdia");
            // execute procedure
            // execute procedure
            runSP(oList, cn, cmd, sp);
            Rspta = getOutputParameter("PO_I_PROCESO").getParameterInt();
            
            
        } catch (Exception e) {
            Logger.getLogger(MaeInversion.class.getName()).log(Level.SEVERE, null, e);
            Rspta=0;
        }
        finally 
        {
      
              //    Rspta=1;
      
            // Always make sure result sets and statements are closed,
            // and the connection is returned to the pool
            if (resultSet != null) {
                try { resultSet.close(); } catch (Exception e) { ; }
                resultSet = null;
              }
            if (cmd != null) {
              try { cmd.close(); } catch (Exception e) { ; }
              cmd = null;
            }
            if (cn != null) {
              try { cn.close(); } catch (Exception e) { ; }
                cn = null;
            }
        }
        //System.out.println(" <f> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        return Rspta;
    }

    
    @Override
    public Integer fetchInsertaConstancia(MaeInversion oMaeInversion) {
        System.out.println(" <i> MaeInversionDao fetchInsertaConstancia " );
        Integer Rspta = 0;
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        //OracleResultSet resultSetB = null;
        
        System.out.println("fetchInsertaConstancia " + LocalDateTime.now());
        //System.out.println("PI_C_FONDO_ID "+);
        try {
            // name of procedure
            String sp = "{call PKG_GENERADOR_DOC.SP_INSERTAR_CONSTANCIAS(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
           System.out.println("SE CAE CERCA");
            oList = listParameterInsConstancia(oMaeInversion);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
   
            System.out.println("inta1");
                    
            cn = conex.ConexionOpen("ctdia");
            // execute procedure
            // execute procedure
            
             System.out.println("inta2");
                    
            runSP(oList, cn, cmd, sp);
             System.out.println(getOutputParameter("PO_I_PROCESO").getParameterInt());
            System.out.println("_________________________________________________SE CAE EN LA SIGUIENTE LÍNEA______________________________________________________");
            Rspta = getOutputParameter("PO_I_PROCESO").getParameterInt();
            
            
        } catch (Exception e) {
            Logger.getLogger(MaeInversion.class.getName()).log(Level.SEVERE, null, e);
            System.out.println(".....................................................................................");
            System.out.println(".....................................................................................");
            System.out.println(".....................................................................................");
            System.out.println(".....................................................................................");
            System.out.println(".....................................................................................");
            Rspta=-1;
        }
        finally 
        {
      
              //    Rspta=1;
      
            // Always make sure result sets and statements are closed,
            // and the connection is returned to the pool
            if (resultSet != null) {
                try { resultSet.close(); } catch (Exception e) { ; }
                resultSet = null;
              }
            if (cmd != null) {
              try { cmd.close(); } catch (Exception e) { ; }
              cmd = null;
            }
            if (cn != null) {
              try { cn.close(); } catch (Exception e) { ; }
                cn = null;
            }
        }
        //System.out.println(" <f> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        return Rspta;
    }    
   
    private List<ParameterOracle> listParAnula(MaeInversion oMaeInversion) {
        List<ParameterOracle> oListParam = new ArrayList<>();
                
        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oMaeInversion.getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_INVERSION", oMaeInversion.getCInversion(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PS_TIPOFORMATO", oMaeInversion.getSCODDocumento(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PS_TIPOCARTA", oMaeInversion.getSTCarta(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PD_FEMISION", oMaeInversion.getFEmision(), OracleTypes.DATE, ParameterDirection.Input));        
        oListParam.add(new ParameterOracle("PS_C_USUARIO_ADD", oMaeInversion.getcUsuarioAdd(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_I_PROCESO", 0, OracleTypes.NUMBER, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }
    
    private List<ParameterOracle> listParameterInsCartas(MaeInversion oMaeInversion) {
        List<ParameterOracle> oListParam = new ArrayList<>();
                
        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oMaeInversion.getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_INVERSION", oMaeInversion.getCInversion(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PS_TIPOFORMATO", oMaeInversion.getSCODDocumento(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PS_TIPOCARTA", oMaeInversion.getSTCarta(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PD_FEMISION", oMaeInversion.getFEmision(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PD_FRECIBIDO", oMaeInversion.getFRegistro(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PS_TRECIBIDO", oMaeInversion.getSEstado(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PS_SPERRECIBIDO", oMaeInversion.getSNombreSol(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PS_SCOMENTARIO", oMaeInversion.getSComentario() , OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PS_NOM_ARCHIVO", oMaeInversion.getSNombreArchivo(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PS_C_USUARIO_ADD", oMaeInversion.getcUsuarioAdd(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PS_REQ_TIPO", oMaeInversion.getReq_tipo(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PS_REQ_NUMERO", oMaeInversion.getReq_numero(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_I_PROCESO", 0, OracleTypes.NUMBER, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }
    

    private List<ParameterOracle> listParameterInsConstancia(MaeInversion oMaeInversion) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        System.out.println("/////////////////////////////////////////////////////////////////////////////////////////////////////////////");
        System.out.println("//////////////////////////// PKG_GENERADOR_DOC.SP_INSERTAR_CONSTANCIAS //////////////////////////////////////");
        System.out.println("/////////////////////////////////////////////////////////////////////////////////////////////////////////////");
        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oMaeInversion.getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        System.out.println("oMaeInversion.getMaeFondo().getCFondoId() = "+oMaeInversion.getMaeFondo().getCFondoId());
        oListParam.add(new ParameterOracle("PI_C_INVERSION", oMaeInversion.getCInversion(), OracleTypes.CHAR, ParameterDirection.Input));
        System.out.println("oMaeInversion.getCInversion() = "+oMaeInversion.getCInversion());
        oListParam.add(new ParameterOracle("PS_TIPOFORMATO", oMaeInversion.getSCODDocumento(), OracleTypes.CHAR, ParameterDirection.Input));
        System.out.println("oMaeInversion.getSCODDocumento() = "+oMaeInversion.getSCODDocumento());
        oListParam.add(new ParameterOracle("PS_TIPOCARTA", oMaeInversion.getSTCarta(), OracleTypes.CHAR, ParameterDirection.Input));
        System.out.println("oMaeInversion.getSTCarta() = "+oMaeInversion.getSTCarta());
        ///oListParam.add(new ParameterOracle("PD_FEMISION", oMaeInversion.getFEmision(), OracleTypes.DATE, ParameterDirection.Input));
        //formatea
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        try{
            Date fechaEmision=sdf1.parse("05/06/2024");
            oListParam.add(new ParameterOracle("PD_FEMISION", oMaeInversion.getFEmision()!=null?oMaeInversion.getFEmision():fechaEmision, OracleTypes.DATE, ParameterDirection.Input));
            System.out.println("oMaeInversion.getFEmision() = "+oMaeInversion.getFEmision());
        }catch(ParseException pe){
            pe.printStackTrace();
        }
        //System.out.println("java.lang.ClassCastException: java.lang.String cannot be cast to java.util.Date");
        //oListParam.add(new ParameterOracle("PD_FRECIBIDO", oMaeInversion.getFRegistro()!=null?oMaeInversion.getFRegistro():new Date(), OracleTypes.DATE, ParameterDirection.Input));
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
        try{
            Date fechaRecibido=sdf2.parse("12/06/2024");
            oListParam.add(new ParameterOracle("PD_FRECIBIDO", oMaeInversion.getFRegistro()!=null?oMaeInversion.getFRegistro():fechaRecibido, OracleTypes.DATE, ParameterDirection.Input));
            System.out.println("oMaeInversion.getFRegistro() = "+oMaeInversion.getFRegistro());
        }catch(ParseException pe){
            pe.printStackTrace();
        }
        
        
        //System.out.println("oMaeInversion.getFRegistro() = "+oMaeInversion.getFRegistro()!=null?oMaeInversion.getFRegistro():"FALTA FRegistro(), campo FRECIBIDO");
        oListParam.add(new ParameterOracle("PS_TRECIBIDO", oMaeInversion.getSEstado()!=null?oMaeInversion.getSEstado():null, OracleTypes.CHAR, ParameterDirection.Input));
        System.out.println("oMaeInversion.getSEstado() = "+oMaeInversion.getSEstado()!=null?oMaeInversion.getSEstado():"falta PS_TRECIBIDO (SEstado)");
        oListParam.add(new ParameterOracle("PS_SPERRECIBIDO", oMaeInversion.getSNombreSol()!=null?oMaeInversion.getSNombreSol():null, OracleTypes.CHAR, ParameterDirection.Input));
        System.out.println("oMaeInversion.getSNombreSol() = "+oMaeInversion.getSNombreSol()!=null?oMaeInversion.getSNombreSol():" FALTA SNombreSol, campo SPERRECIBIDO ");
        oListParam.add(new ParameterOracle("PS_SCOMENTARIO", oMaeInversion.getSComentario() , OracleTypes.CHAR, ParameterDirection.Input));
        System.out.println("oMaeInversion.getSComentario() = "+oMaeInversion.getSComentario());
        oListParam.add(new ParameterOracle("PS_NOM_ARCHIVO", oMaeInversion.getSNombreArchivo()!=null?oMaeInversion.getSNombreArchivo():null, OracleTypes.CHAR, ParameterDirection.Input));
        System.out.println("oMaeInversion.getSNombreArchivo() = "+oMaeInversion.getSNombreArchivo()!=null?oMaeInversion.getSNombreArchivo():"FALTA SNombreArchivo, campo PS_NOM_ARCHIVO");
        oListParam.add(new ParameterOracle("PS_C_USUARIO_ADD", oMaeInversion.getcUsuarioAdd(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_I_PROCESO", 0, OracleTypes.NUMBER, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        System.out.println("/////////////////////////////////////////////////////////////////////////////////////////////////////////////");
        System.out.println("/////////////////////////////////////////////////////////////////////////////////////////////////////////////");
        System.out.println("/////////////////////////////////////////////////////////////////////////////////////////////////////////////");
        return oListParam;
    }
    


    @Override
    public ArrayList<MaeInversion> fetchGeneraCarta(MaeInversion oMaeInversion) {
        System.out.println("PKG_GENERADOR_DOC.SP_GENERA_CARTA" + LocalDateTime.now());
        ArrayList<MaeInversion> lstInversion = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        //OracleResultSet resultSetB = null;

        try {
            // name of procedure
            if (oMaeInversion.getSTCarta().equals("0005") || oMaeInversion.getSTCarta().equals("0011")){
                String sp = "{call PKG_GENERADOR_DOC.SP_GENERA_CARTA_END(?,?,?,?,?)}";
                // list of parameter
                List<ParameterOracle> oList = new ArrayList<>();

                oList = listParameterPGC(oMaeInversion);
                //Abre conexion a la BD
                Conexion conex = new Conexion();
                cn = conex.ConexionOpen("ctdia");            
                // execute procedure
                runSearch(oList, cn, cmd, sp);
                resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();
                while (resultSet.next()) {
                    //fondo
                    MaeFondo newFondo = new MaeFondo();
                    newFondo.setCFondoId(resultSet.getString("C_FONDO_ID"));
                    newFondo.setDFondo(resultSet.getString("D_FONDO"));
                    newFondo.setDFondocorto(resultSet.getString("D_FONDOCORTO"));
                    MaeInmueble newMaeInmueble = new MaeInmueble();
                    newMaeInmueble.setCInmueble(resultSet.getInt("CINMUEBLE"));
                    newMaeInmueble.setADir1(resultSet.getString("TDIRECCION"));
                    newMaeInmueble.setADir2(resultSet.getString("REFERENCIA"));
                    newMaeInmueble.setCUbigeoId(resultSet.getString("DISTRITO"));
                    MaeInversion newInversion = new MaeInversion();
                    newInversion.setCInversion(resultSet.getString("DVALOR_BV"));
                    newInversion.setSNombreSol(resultSet.getString("PROPIETARIOS"));
                    newInversion.setFEscritura(resultSet.getDate("F_ESCRITURA"));
                    newInversion.setSNotaria(resultSet.getString("S_NOTARIA"));                    
                    newInversion.setSAsHipo(resultSet.getString("S_ASHIPO"));
                    newInversion.setSPeHipo(resultSet.getString("S_PEHIPO"));
                    newInversion.setSAsExpTchn(resultSet.getString("S_ASEXPTCHN"));  
                    newInversion.setSTchn(resultSet.getString("S_TCHN"));
                    newInversion.setfTchn(resultSet.getDate("EMISION_TCHN"));
                    newInversion.setSNombanco(resultSet.getString("NOMBANCO"));
                    newInversion.setNRcuenta(resultSet.getString("NROCUENTA"));
                    newInversion.setSDfondoOri(resultSet.getString("FONDOORIGEN"));
                    newInversion.setSDireccionDestino(resultSet.getString("DIREDESTINO"));
                    newInversion.setSDireccionOrigen(resultSet.getString("DIRECCIONORIGEN"));
                    newInversion.setSNro_ruc(resultSet.getString("NRUC_FONDO"));
                    newInversion.setMaeFondo(newFondo);
                    newInversion.setcInmueble(newMaeInmueble);
                    lstInversion.add(newInversion);
                }
            }else{
                String sp = "{call PKG_GENERADOR_DOC.SP_GENERA_CARTA(?,?,?,?,?)}";
                // list of parameter
                List<ParameterOracle> oList = new ArrayList<>();

                oList = listParameterPGC(oMaeInversion);
                //Abre conexion a la BD
                Conexion conex = new Conexion();
                cn = conex.ConexionOpen("ctdia");            
                // execute procedure
                runSearch(oList, cn, cmd, sp);
                resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();
                while (resultSet.next()) {
                    //fondo
                    MaeFondo newFondo = new MaeFondo();
                    newFondo.setCFondoId(resultSet.getString("C_FONDO_ID"));
                    newFondo.setDFondo(resultSet.getString("D_FONDO"));
                    newFondo.setDFondocorto(resultSet.getString("D_FONDOCORTO"));
                    MaeInmueble newMaeInmueble = new MaeInmueble();
                    newMaeInmueble.setCInmueble(resultSet.getInt("CINMUEBLE"));
                    newMaeInmueble.setADir1(resultSet.getString("TDIRECCION"));
                    newMaeInmueble.setADir2(resultSet.getString("REFERENCIA"));
                    newMaeInmueble.setCUbigeoId(resultSet.getString("DISTRITO"));
                    MaeInversion newInversion = new MaeInversion();
                    newInversion.setCInversion(resultSet.getString("DVALOR_BV"));
                    newInversion.setSNombreSol(resultSet.getString("PROPIETARIOS"));
                    newInversion.setFEscritura(resultSet.getDate("F_ESCRITURA"));
                    newInversion.setSNotaria(resultSet.getString("S_NOTARIA"));
                    newInversion.setSAsHipo(resultSet.getString("S_ASHIPO"));
                    newInversion.setSPeHipo(resultSet.getString("S_PEHIPO"));
                    newInversion.setSAsExpTchn(resultSet.getString("S_ASEXPTCHN"));  
                    newInversion.setSTchn(resultSet.getString("S_TCHN"));
                    newInversion.setfTchn(resultSet.getDate("EMISION_TCHN"));
                    newInversion.setMaeFondo(newFondo);
                    newInversion.setcInmueble(newMaeInmueble);
                    lstInversion.add(newInversion);
                }
            }    
        } catch (Exception e) {
            Logger.getLogger(MaePersona.class.getName()).log(Level.SEVERE, null, e);
        }
        finally {
            // Always make sure result sets and statements are closed,
            // and the connection is returned to the pool
            if (resultSet != null) {
                try { resultSet.close(); } catch (Exception e) { ; }
                resultSet = null;
              }
            if (cmd != null) {
              try { cmd.close(); } catch (Exception e) { ; }
              cmd = null;
            }
            if (cn != null){
              try { cn.close(); } catch (Exception e) { ; }
                cn = null;
            }
        }
        //System.out.println(" <f> MaeInversionDao fetchDebtors " + LocalDateTime.now());
        return lstInversion;
    }

    @Override
    public MaeInversion fetchGeneraConstancia(MaeInversion oMaeInversion) {
        System.out.println("PKG_GENERADOR_DOC.SP_GENERA_CONSTANCIA" + LocalDateTime.now());
        ArrayList<DocPlantilla> listPlantilla = new ArrayList<>();
        MaeInversion newInversion = null;
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        //OracleResultSet resultSetB = null;

        try {
            // name of procedure
                
                String sp = "{call PKG_GENERADOR_DOC.SP_GENERA_CONSTANCIA(?,?,?,?,?)}";
                // list of parameter
                List<ParameterOracle> oList = new ArrayList<>();

                oList = listParamConstancias(oMaeInversion);
                //Abre conexion a la BD
                Conexion conex = new Conexion();
                cn = conex.ConexionOpen("ctdia");            
                // execute procedure
                runSearch(oList, cn, cmd, sp);
                resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();
                while (resultSet.next()) {
                    System.out.println("next() "+resultSet.getString("VALOR") );
   
                    newInversion = new MaeInversion();
                    System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");                    
                    newInversion.setSTipoDoc(resultSet.getString("DOC_TIPO")!=null?resultSet.getString("DOC_TIPO"):"TIPODOC ");
                    System.out.println("next() "+resultSet.getString("VALOR") );
                    newInversion.setNDocNumero(resultSet.getInt("DOC_NUMERO"));
                    //newInversion.setNDocNumero(resultSet.getInt("DOC_NUMERO")!=0?resultSet.getInt("DOC_NUMERO"):" cero");
                    newInversion.setSNombreFormato(resultSet.getString("NOM_FORMATO")!=null?resultSet.getString("NOM_FORMATO"):"NOM_FORMATO ");
                    newInversion.setSNombreArchivo(resultSet.getString("NOM_ARCHIVO")!=null?resultSet.getString("NOM_ARCHIVO"):"NOM_ARCHIVO ");
                    System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");  
                    DocPlantilla docPla= new DocPlantilla();
                    docPla.setIndice(resultSet.getInt("INDICE"));
                    docPla.setConcepto(resultSet.getString("CONCEPTO"));
                    docPla.setValor(resultSet.getString("VALOR"));
                    listPlantilla.add(docPla);
                    
                    newInversion.setListaDocPlantilla(listPlantilla);
                   
                }
            
        } catch (Exception e) {
            Logger.getLogger(MaePersona.class.getName()).log(Level.SEVERE, null, e);
        }
        finally {
            // Always make sure result sets and statements are closed,
            // and the connection is returned to the pool
            if (resultSet != null) {
                try { resultSet.close(); } catch (Exception e) { ; }
                resultSet = null;
              }
            if (cmd != null) {
              try { cmd.close(); } catch (Exception e) { ; }
              cmd = null;
            }
            if (cn != null){
              try { cn.close(); } catch (Exception e) { ; }
                cn = null;
            }
        }
        //System.out.println(" <f> MaeInversionDao fetchDebtors " + LocalDateTime.now());
        return newInversion;
    }
  
    @Override
    public MaeInversion anulaConstancia(MaeInversion oMaeInversion) {
        System.out.println("PKG_GENERADOR_DOC.SP_ANULA_CONSTANCIA" + LocalDateTime.now());
        Integer Rspta = 0;
        ArrayList<DocPlantilla> listPlantilla = new ArrayList<>();
        MaeInversion newInversion = null;
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        //OracleResultSet resultSetB = null;

        try {
            // name of procedure
                
            String sp = "{call PKG_GENERADOR_DOC.SP_ANULA_CONSTANCIA(?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();

            oList = paramAnulConst(oMaeInversion);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("ctdia");            
            // execute procedure
            runSP(oList, cn, cmd, sp);
            Rspta = getOutputParameter("PO_I_PROCESO").getParameterInt();
            newInversion = new MaeInversion();
            newInversion.setSTipoDoc("CL");
            newInversion.setNDocNumero(Rspta);

            
        } catch (Exception e) {
            Logger.getLogger(MaePersona.class.getName()).log(Level.SEVERE, null, e);
        }
        finally {
            // Always make sure result sets and statements are closed,
            // and the connection is returned to the pool
            if (resultSet != null) {
                try { resultSet.close(); } catch (Exception e) { ; }
                resultSet = null;
              }
            if (cmd != null) {
              try { cmd.close(); } catch (Exception e) { ; }
              cmd = null;
            }
            if (cn != null){
              try { cn.close(); } catch (Exception e) { ; }
                cn = null;
            }
        }
        //System.out.println(" <f> MaeInversionDao fetchDebtors " + LocalDateTime.now());
        return newInversion;
    }  
    
    private List<ParameterOracle> listParameterPGC(MaeInversion oMaeInversion) {
           List<ParameterOracle> oListParam = new ArrayList<>();

           oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oMaeInversion.getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
           oListParam.add(new ParameterOracle("PI_C_INVERSION", oMaeInversion.getCInversion(), OracleTypes.CHAR, ParameterDirection.Input));
           oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
           oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
           oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

           return oListParam;
       }

    private List<ParameterOracle> listParamConstancias(MaeInversion oMaeInversion) {
           List<ParameterOracle> oListParam = new ArrayList<>();
           System.out.println("-------------------------------------------------------------------------------");
           oListParam.add(new ParameterOracle("PI_DOC_TIPO", oMaeInversion.getSTipoDoc(), OracleTypes.CHAR, ParameterDirection.Input));
           System.out.println("PI_DOC_TIPO ==========>  oMaeInversion.getSTipoDoc() ===========================> "+oMaeInversion.getSTipoDoc());
           oListParam.add(new ParameterOracle("PI_DOC_NUMERO", oMaeInversion.getNDocNumero(), OracleTypes.NUMBER, ParameterDirection.Input));
           System.out.println("PI_DOC_NUMERO ==========>  oMaeInversion.getNDocNumero() =========================> "+oMaeInversion.getNDocNumero());
           oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
           //System.out.println(""+);
           oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
           //System.out.println(""+);
           oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));
           // System.out.println(""+);
            System.out.println("-------------------------------------------------------------------------------");
           return oListParam;
       } 

    private List<ParameterOracle> paramAnulConst(MaeInversion oMaeInversion) {
           List<ParameterOracle> oListParam = new ArrayList<>();

           oListParam.add(new ParameterOracle("PI_DOC_TIPO", oMaeInversion.getSTipoDoc(), OracleTypes.CHAR, ParameterDirection.Input));
           oListParam.add(new ParameterOracle("PI_DOC_NUMERO", oMaeInversion.getNDocNumero(), OracleTypes.NUMBER, ParameterDirection.Input));
           oListParam.add(new ParameterOracle("PU_USUARIO_UPD", oMaeInversion.getcUsuarioMod(), OracleTypes.CHAR, ParameterDirection.Input));           
           oListParam.add(new ParameterOracle("PO_I_PROCESO", 0, OracleTypes.NUMBER, ParameterDirection.Output));
           oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
           oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));


           return oListParam;
       }


    private List<ParameterOracle> listParameterGD(MaeInversion oMaeInversion) {
        List<ParameterOracle> oListParam = new ArrayList<>();
    

        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oMaeInversion.getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_INVERSION", oMaeInversion.getCInversion(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_A_NRO_DOCUMENTO", oMaeInversion.getcPersonaId().getANroDocumento(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

    
    private List<ParameterOracle> listParameterTP(MaeInversion oMaeInversion) {
        List<ParameterOracle> oListParam = new ArrayList<>();
    

        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oMaeInversion.getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_INVERSION", oMaeInversion.getCInversion(), OracleTypes.CHAR, ParameterDirection.Input));        
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        

        return oListParam;
    }    
     
    
    private List<ParameterOracle> listParameterCC(MaeInversion oMaeInversion) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oMaeInversion.getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_INVERSION", oMaeInversion.getCInversion(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PS_TIPOFORMATO", oMaeInversion.getSCODDocumento(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PS_TIPOCARTA", oMaeInversion.getSTCarta(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
               
    }

    private List<ParameterOracle> listParConst(MaeInversion oMaeInversion) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oMaeInversion.getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_INVERSION", oMaeInversion.getCInversion(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PS_TIPOFORMATO", oMaeInversion.getSCODDocumento(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PS_TIPOCARTA", oMaeInversion.getSTCarta(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PD_FEMISION1", oMaeInversion.getFinicio(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PD_FEMISION2", oMaeInversion.getFfin(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PS_C_FLAG_NOTIF", oMaeInversion.getEstaNotificado(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PS_C_FECHA_NOTIF", oMaeInversion.getFechaNotificacion(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }    
    private List<ParameterOracle> listParameterMC(MaeInversion oMaeInversion) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oMaeInversion.getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_INVERSION", oMaeInversion.getCInversion(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TIPO", oMaeInversion.getSCODDocumento(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        

        return oListParam;
    }

    
    @Override
    public ArrayList<MaeInversion> fetchlistarCartas(MaeInversion oMaeInversion) {
        //System.out.println(" <i> MaeInversionDao fetchDebtors " + LocalDateTime.now());
        ArrayList<MaeInversion> lstInversion = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        //OracleResultSet resultSetB = null;

        try {
            // name of procedure
            String sp = "{call PKG_GENERADOR_DOC.SP_CONSULTA_CARTAS(?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameterCC(oMaeInversion);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("ctdia");            
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                //fondo
                MaeFondo newFondo = new MaeFondo();
                newFondo.setCFondoId(resultSet.getString("C_FONDO_ID"));
                newFondo.setDFondo(resultSet.getString("D_FONDO"));
                //persona
                MaePersona newPersona = new MaePersona();
                newPersona.setMaeFondo(newFondo);
                newPersona.setCPersonaId(resultSet.getInt("C_PERSONA_ID"));
                newPersona.setANroDocumento(resultSet.getString("A_NRO_DOCUMENTO"));
                newPersona.setDNombres(resultSet.getString("PROPIETARIO"));
                MaeInversion newInversion = new MaeInversion();
                newInversion.setMaeFondo(newFondo);
                newInversion.setcPersonaId(newPersona);
                newInversion.setcMaeInversionId(resultSet.getInt("C_MAE_INVERSION_ID"));
                newInversion.setcInversionId(resultSet.getString("C_INVERSION_ID"));
                newInversion.setCInversion(resultSet.getString("DVALOR_BV"));
                newInversion.setSComentario(resultSet.getString("SCOMENTARIO"));
                newInversion.setSNombreSol(resultSet.getString("SPERRECIBIDO"));
                newInversion.setFRegistro(resultSet.getDate("FRECIBIDO"));
                newInversion.setFEmision(resultSet.getDate("FEMISION"));
                newInversion.setSMotivo(resultSet.getString("TIPOREC"));
                newInversion.setSEstado(resultSet.getString("CARTA"));
                newInversion.seteEstado(resultSet.getString("TIPOCARTA"));
                newInversion.setsDocumentoId(resultSet.getString("TIPOFORMATO"));
                newInversion.setSCODDocumento(resultSet.getString("FORMATO"));
                lstInversion.add(newInversion);
            }
        } catch (Exception e) {
            Logger.getLogger(MaePersona.class.getName()).log(Level.SEVERE, null, e);
        }
        finally {
            // Always make sure result sets and statements are closed,
            // and the connection is returned to the pool
            if (resultSet != null) {
                try { resultSet.close(); } catch (Exception e) { ; }
                resultSet = null;
              }
            if (cmd != null) {
              try { cmd.close(); } catch (Exception e) { ; }
              cmd = null;
            }
            if (cn != null){
              try { cn.close(); } catch (Exception e) { ; }
                cn = null;
            }
        }
        //System.out.println(" <f> MaeInversionDao fetchDebtors " + LocalDateTime.now());
        return lstInversion;
    }

    
    @Override
    public ArrayList<MaeInversion> fetchlistarConstancias(MaeInversion oMaeInversion) {
        //System.out.println(" <i> MaeInversionDao fetchDebtors " + LocalDateTime.now());
        ArrayList<MaeInversion> lstInversion = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        //OracleResultSet resultSetB = null;

        System.out.println(" {call PKG_GENERADOR_DOC.SP_CONSULTA_CONSTANCIAS(?,?,?,?,?,?,?,?,?,?,?)}");
        try {
            // name of procedure
            String sp = "{call PKG_GENERADOR_DOC.SP_CONSULTA_CONSTANCIAS(?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParConst(oMaeInversion);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("ctdia");            
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                //fondo
                MaeFondo newFondo = new MaeFondo();
                newFondo.setCFondoId(resultSet.getString("C_FONDO_ID"));
                newFondo.setDFondo(resultSet.getString("D_FONDO"));
                //persona
                MaePersona newPersona = new MaePersona();
                newPersona.setMaeFondo(newFondo);
                newPersona.setCPersonaId(resultSet.getInt("C_PERSONA_ID"));
                newPersona.setANroDocumento(resultSet.getString("A_NRO_DOCUMENTO"));
                newPersona.setDNombres(resultSet.getString("PROPIETARIO"));
                MaeInversion newInversion = new MaeInversion();
                newInversion.setMaeFondo(newFondo);
                newInversion.setcPersonaId(newPersona);
                newInversion.setcMaeInversionId(resultSet.getInt("C_MAE_INVERSION_ID"));
                newInversion.setcInversionId(resultSet.getString("C_INVERSION_ID"));
                newInversion.setCInversion(resultSet.getString("DVALOR_BV"));
                newInversion.setSComentario(resultSet.getString("SCOMENTARIO"));
                newInversion.setSNombreSol(resultSet.getString("SPERRECIBIDO"));
                newInversion.setFRegistro(resultSet.getDate("FRECIBIDO"));
                newInversion.setFEmision(resultSet.getDate("FEMISION"));
                newInversion.setSMotivo(resultSet.getString("TIPOREC"));
                newInversion.setSEstado(resultSet.getString("CARTA"));
                newInversion.seteEstado(resultSet.getString("TIPOCARTA"));
                newInversion.setsDocumentoId(resultSet.getString("TIPOFORMATO"));
                newInversion.setSCODDocumento(resultSet.getString("FORMATO"));                
                newInversion.setSTipoDoc(resultSet.getString("DOC_TIPO"));
                newInversion.setNDocNumero(resultSet.getInt("DOC_NUMERO")); 
                newInversion.setDesDestino(resultSet.getString("NOM_ARCHIVO"));
                newInversion.setDocSituacion(resultSet.getInt("DOC_SITUACION"));
                newInversion.setEstaNotificado(resultSet.getString("PS_C_FLAG_NOTIF"));
                newInversion.setFechaNotificacion(resultSet.getString("PS_C_FECHA_NOTIF"));
                newInversion.setSelected(false);
                
                System.out.println("situtttttt99999999999999999999999999999tttttttt " + newInversion.getDocSituacion());
                
                lstInversion.add(newInversion);
            }
        } catch (Exception e) {
            Logger.getLogger(MaePersona.class.getName()).log(Level.SEVERE, null, e);
        }
        finally {
            // Always make sure result sets and statements are closed,
            // and the connection is returned to the pool
            if (resultSet != null) {
                try { resultSet.close(); } catch (Exception e) { ; }
                resultSet = null;
              }
            if (cmd != null) {
              try { cmd.close(); } catch (Exception e) { ; }
              cmd = null;
            }
            if (cn != null){
              try { cn.close(); } catch (Exception e) { ; }
                cn = null;
            }
        }
        //System.out.println(" <f> MaeInversionDao fetchDebtors " + LocalDateTime.now());
        return lstInversion;
    }
    
    @Override
    public Integer notificarXEmail(MaeInversion oMaeInversion) {
        Integer rspta = 1;
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_GENERADOR_DOC.SP_NOTIFICAR_CON_EMAIL(?,?,?,?,?,?,?,?,?)}";
            // list of parameters
            List<ParameterOracle> oLis = new ArrayList<>();
            // fill parameters
            oLis = insertParameters(oMaeInversion);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("ctdia");
            // execute procedure
            runSP(oLis, cn, cmd, sp);

            rspta = getOutputParameter("PO_I_PROCESO").getParameterInt();
        }
        catch (Exception e) {
            Logger.getLogger(MaeGeneradorDocumentoDao.class.getName()).log(Level.SEVERE, null, e);
            rspta=0;            
        }        
        finally {
            // Always make sure result sets and statements are closed,
            // and the connection is returned to the pool
            if (resultSet != null) {
                try { resultSet.close(); } catch (Exception e) { ; }
                resultSet = null;
              }
            if (cmd != null) {
              try { cmd.close(); } catch (Exception e) { ; }
              cmd = null;
            }
            if (cn != null){
              try { cn.close(); } catch (Exception e) { ; }
                cn = null;
            }
        }
        return rspta;        
    }
    
    private List<ParameterOracle> insertParameters(MaeInversion oMaeInversion) {
        List<ParameterOracle> oListParam = new ArrayList<>();       
        oListParam.add(new ParameterOracle("PI_C_CODFONDO", oMaeInversion.getMaeFondo().getCFondoId().replaceAll("\\s", ""), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_DVALORBV", oMaeInversion.getCInversion().replaceAll("\\s", ""), OracleTypes.CHAR, ParameterDirection.Input));        
        oListParam.add(new ParameterOracle("PI_C_FLAG_RECIBIOEMAIL", oMaeInversion.getEstaNotificado(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_FECHA_EMAILRECIBIDO", oMaeInversion.getFechaNotificacion(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_TIPOFORMATO", oMaeInversion.getsDocumentoId().replaceAll("\\s", ""), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PU_USUARIO_UPD", oMaeInversion.getcUsuarioMod().replaceAll("\\s", ""), OracleTypes.CHAR, ParameterDirection.Input));         
        oListParam.add(new ParameterOracle("PO_I_PROCESO", 0, OracleTypes.NUMBER, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        return oListParam;
    }    
    
    @Override
    public ArrayList<MaeEmail> fetchAll() {
        System.out.println(" <i> MaeInversionDao fetchInvTele " + LocalDateTime.now());
        ArrayList<MaeEmail> lstEmails = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;

        try {
            // name of procedure
            String sp = "{call PKG_GENERADOR_DOC.SP_CONSULTAR_EMAILS(?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParametersAll();
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("ctdia");
            // execute procedure
            runSearch(oList, cn, cmd, sp);

            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                MaeEmail newEmail = new MaeEmail();
                newEmail.setCemailId(resultSet.getInt("C_EMAIL_ID"));
                //newEmail.getMaePersona().setCPersonaId(resultSet.getInt("C_PERSONA_ID"));
                newEmail.setDemail(resultSet.getString("D_EMAIL"));
                lstEmails.add(newEmail);
            }
        } catch (Exception e) {
            Logger.getLogger(MaeGeneradorDocumentoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        finally 
        {
            // Always make sure result sets and statements are closed,
            // and the connection is returned to the pool
            if (resultSet != null) {
                try { resultSet.close(); } catch (Exception e) { ; }
                resultSet = null;
              }
            if (cmd != null) {
              try { cmd.close(); } catch (Exception e) { ; }
              cmd = null;
            }
            if (cn != null) {
              try { cn.close(); } catch (Exception e) { ; }
                cn = null;
            }
        } 
        return lstEmails;
    }
    
    private List<ParameterOracle> listParametersAll() {
        List<ParameterOracle> oListParam = new ArrayList<>();
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        return oListParam;
    }   
    
    
}
