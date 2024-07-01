/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.impl;

import java.time.LocalDateTime;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleResultSet;
import oracle.jdbc.OracleTypes;
import pop.comun.dominio.CobTablas;
import pop.comun.dominio.MaeFondo;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MaePersona;
import pop.comun.dominio.MovimientoBNB;
import pop.comun.dominio.MovimientoProtesto;
import pop.webcustodia.common.DBUtil;
import pop.webcustodia.common.ParameterDirection;
import pop.webcustodia.common.ParameterOracle;
import pop.webcustodia.conn.Conexion;
import pop.webcustodia.iface.IMovimientoProtestoDao;

/**
 *
 * @author RC433838
 */
public class MovimientoProtestoDao  extends DBUtil implements IMovimientoProtestoDao {

    private OracleConnection cn = null;

    public MovimientoProtestoDao() {

    }

    public MovimientoProtestoDao(OracleConnection cnx) {
        cn = cnx;
    }
    
    public OracleConnection getCn() {
        return cn;
    }

    public void setCn(OracleConnection cn) {
        this.cn = cn;
    }    
    
    @Override
    public String remProtesto(MovimientoProtesto oProtesto) {
        System.out.println(" <i> remProtesto(MovimientoProtesto oProtesto) "+ LocalDateTime.now() );
        String Rspta = null;
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        //OracleResultSet resultSetB = null;

        try {
            // name of procedure
            String sp = "{call PKG_PROTESTOS.SP_ELIMINAR_PROTESTO(?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
           
            oList.add(new ParameterOracle("P_DOC_NUMERO", oProtesto.getDoc_numero(), OracleTypes.NUMBER, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_C_USUARIO_UPD", oProtesto.getcUsuarioMod(), OracleTypes.VARCHAR, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output)); 
            
            //Abre conexion a la BD
            Conexion conex = new Conexion();           
    
            cn = conex.ConexionOpen("ctdia");
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("P_RESULTADO").getParameterResultSet();
            while (resultSet.next()) {   
                Rspta = resultSet.getString("RPTA");
            }
            
        } catch (Exception e) {
            Logger.getLogger(MaeInversion.class.getName()).log(Level.SEVERE, null, e);
            Rspta="Error en los parametros";
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
        System.out.println(" <f> addProtesto(MovimientoProtesto oProtesto) " + LocalDateTime.now());
        return Rspta;
    }

    @Override
    public String remRegistroBNB(MovimientoProtesto oProtesto) {
        System.out.println(" <i> remRegistroBNB(MovimientoProtesto oProtesto) "+ LocalDateTime.now() );
        String Rspta = null;
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        //OracleResultSet resultSetB = null;
        
        try {
            // name of procedure
            String sp = "{call PKG_PROTESTOS.SP_ELIMINAR_BNB(?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
           
            oList.add(new ParameterOracle("P_DOC_NUMERO", oProtesto.getDoc_numero(), OracleTypes.NUMBER, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_C_USUARIO_UPD", oProtesto.getcUsuarioMod(), OracleTypes.VARCHAR, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output)); 
            
            //Abre conexion a la BD
            Conexion conex = new Conexion();           
    
            cn = conex.ConexionOpen("ctdia");
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("P_RESULTADO").getParameterResultSet();
            while (resultSet.next()) {   
                Rspta = resultSet.getString("RPTA");
            }
            
            
        } catch (Exception e) {
            
            Logger.getLogger(MaeInversion.class.getName()).log(Level.SEVERE, null, e);
            Rspta="Error en los parametros";
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
        System.out.println(" <f> remRegistroBNB(MovimientoProtesto oProtesto) " + LocalDateTime.now());
        return Rspta;
    }    
    @Override
    public String addProtesto(MovimientoProtesto oProtesto) {
        System.out.println(" <i> addProtesto(MovimientoProtesto oProtesto) "+ LocalDateTime.now() );
        String Rspta = null;
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        //OracleResultSet resultSetB = null;

        try {
            // name of procedure
            String sp = "{call PKG_PROTESTOS.SP_INSERTAR_PROTESTO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
           
            oList.add(new ParameterOracle("P_DOC_NUMERO", oProtesto.getDoc_numero(), OracleTypes.NUMBER, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_DOC_TIPO", oProtesto.getDoc_tipo(), OracleTypes.VARCHAR, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_CODFONDO", oProtesto.getCodfondo(), OracleTypes.VARCHAR, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_FEMISION", oProtesto.getFemision(), OracleTypes.DATE, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_D_USUARIO_ADD", oProtesto.getfUsuarioAdd(), OracleTypes.DATE, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_ESTADO", oProtesto.getEstado(), OracleTypes.VARCHAR, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_FPROTESTO", oProtesto.getFprotesto(), OracleTypes.DATE, ParameterDirection.Input));            
            oList.add(new ParameterOracle("P_DVALOR_BV", oProtesto.getDvalor_bv(), OracleTypes.VARCHAR, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_C_USUARIO_ADD", oProtesto.getcUsuarioAdd(), OracleTypes.VARCHAR, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_COMENTARIO", oProtesto.getComentario() , OracleTypes.VARCHAR, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_D_USUARIO_UPD", oProtesto.getfUsuarioMod(), OracleTypes.DATE, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_C_USUARIO_UPD", oProtesto.getcUsuarioMod(), OracleTypes.VARCHAR, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_REQ_NUMERO", oProtesto.getReq_numero(), OracleTypes.NUMBER, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_REQ_TIPO", oProtesto.getReq_tipo(), OracleTypes.VARCHAR, ParameterDirection.Input));
        
            oList.add(new ParameterOracle("P_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output)); 
            
            //Abre conexion a la BD
            Conexion conex = new Conexion();           
    
            cn = conex.ConexionOpen("ctdia");
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("P_RESULTADO").getParameterResultSet();
            while (resultSet.next()) {   
                Rspta = resultSet.getString("RPTA");
            }
            
        } catch (Exception e) {
            Logger.getLogger(MaeInversion.class.getName()).log(Level.SEVERE, null, e);
            Rspta="Error en los parametros";
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
        System.out.println(" <f> addProtesto(MovimientoProtesto oProtesto) " + LocalDateTime.now());
        return Rspta;
    }

    @Override
    public List<MaeInversion> allInversiones(MaeInversion oInversion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MovimientoProtesto> allProtestos(MovimientoProtesto oProtesto) {
        
        System.out.println(" <i> allProtestos(MovimientoProtesto oProtesto) "+ LocalDateTime.now());
        List<MovimientoProtesto> lisProtestos = new ArrayList<MovimientoProtesto>();        
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        //OracleResultSet resultSetB = null;

        try {
            // name of procedure
            String sp = "{call PKG_PROTESTOS.SP_LISTAR_PROTESTO(?,?,?,?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
           //System.out.println(" uno");
            oList.add(new ParameterOracle("P_DVALOR_BV", oProtesto.getDvalor_bv(), OracleTypes.VARCHAR, ParameterDirection.Input));           
            oList.add(new ParameterOracle("P_DOC_TIPO", oProtesto.getDoc_tipo(), OracleTypes.VARCHAR, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_DOC_NUMERO", oProtesto.getDoc_numero(), OracleTypes.NUMBER, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_CODFONDO", oProtesto.getCodfondo(), OracleTypes.VARCHAR, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_FEMISION1", oProtesto.getFemision(), OracleTypes.DATE, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_FEMISION2", oProtesto.getFemision2(), OracleTypes.DATE, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_FPROTESTO1", oProtesto.getFprotesto(), OracleTypes.DATE, ParameterDirection.Input));            
            oList.add(new ParameterOracle("P_FPROTESTO2", oProtesto.getFprotesto2(), OracleTypes.DATE, ParameterDirection.Input));            
            oList.add(new ParameterOracle("P_ESTADO", oProtesto.getEstado(), OracleTypes.VARCHAR, ParameterDirection.Input));            
            oList.add(new ParameterOracle("P_RESULTADO", "", OracleTypes.CURSOR, ParameterDirection.Output)); 
            
            //System.out.println(" dos");
            
            
            //Abre conexion a la BD
            Conexion conex = new Conexion();
    
            cn = conex.ConexionOpen("ctdia");
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("P_RESULTADO").getParameterResultSet();
            while (resultSet.next()) {  
                //System.out.println(" tres");
                MovimientoProtesto movpro = new MovimientoProtesto();
                movpro.setDoc_tipo(resultSet.getString("DOC_TIPO"));
                movpro.setDoc_numero(resultSet.getInt("DOC_NUMERO"));                
                movpro.setCodfondo(resultSet.getString("CODFONDO"));
                movpro.setDvalor_bv(resultSet.getString("DVALOR_BV"));
                movpro.setFemision(resultSet.getDate("FEMISION"));
                movpro.setFprotesto(resultSet.getDate("FPROTESTO"));
                movpro.setEstado(resultSet.getString("ESTADO"));
                movpro.setcUsuarioAdd(resultSet.getString("C_USUARIO_ADD"));
                movpro.setfUsuarioAdd(resultSet.getDate("D_USUARIO_ADD"));                
                movpro.setcUsuarioMod(resultSet.getString("C_USUARIO_UPD"));
                movpro.setfUsuarioMod(resultSet.getDate("D_USUARIO_UPD"));
                movpro.setComentario(resultSet.getString("COMENTARIO"));
                
                CobTablas cobtab = new CobTablas();
                cobtab.setCtablaId("0012");
                cobtab.setCtablaDetId(resultSet.getString("ESTADO"));
                cobtab.setDdescripcion(resultSet.getString("D_DESCRIPCION"));
                movpro.setMae_estado(cobtab);
                
                MaeFondo maeFondo = new MaeFondo();
                maeFondo.setCFondoId(resultSet.getString("CODFONDO"));
                maeFondo.setDFondo(resultSet.getString("D_FONDO"));
                movpro.setMaeFondo(maeFondo);
                
                MaePersona persona = new MaePersona();
                persona.setDApePat(resultSet.getString("TAPATERNO"));
                persona.setDApeMat(resultSet.getString("TAMATERNO"));
                persona.setDNombres(resultSet.getString("TNOMBRES"));
                        
                MaeInversion inversion = new MaeInversion();
                inversion.setcPersonaId(persona);
                movpro.setMaeInversion(inversion);
                
                lisProtestos.add(movpro);        
            }
            //System.out.println(" cuatro");
            
        } catch (Exception e) {
            Logger.getLogger(MaeInversion.class.getName()).log(Level.SEVERE, null, e);            
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
        System.out.println(" <f> allProtestos(MovimientoProtesto oProtesto) " + LocalDateTime.now());
        return lisProtestos;
        
        
    }
    
    
    @Override
    public List<MovimientoProtesto> allRegistroBnb(MovimientoProtesto oProtesto) {
        
        System.out.println(" <i> allProtestos(MovimientoProtesto oProtesto) "+ LocalDateTime.now());
        List<MovimientoProtesto> lisProtestos = new ArrayList<MovimientoProtesto>();        
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        //OracleResultSet resultSetB = null;

        try {
            // name of procedure
            String sp = "{call PKG_PROTESTOS.SP_LISTAR_CUSTODIA_BNB(?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
           //System.out.println(" uno");
            
            oList.add(new ParameterOracle("P_DOC_TIPO", oProtesto.getDoc_tipo(), OracleTypes.VARCHAR, ParameterDirection.Input));            
            oList.add(new ParameterOracle("P_CODFONDO", oProtesto.getCodfondo(), OracleTypes.VARCHAR, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_FEMISION1", oProtesto.getFemision(), OracleTypes.DATE, ParameterDirection.Input));                       
            oList.add(new ParameterOracle("P_ESTADO", oProtesto.getEstado(), OracleTypes.VARCHAR, ParameterDirection.Input)); 
            oList.add(new ParameterOracle("p_REQ_TIPO", oProtesto.getReq_tipo(), OracleTypes.VARCHAR, ParameterDirection.Input));             
            oList.add(new ParameterOracle("P_RESULTADO", "", OracleTypes.CURSOR, ParameterDirection.Output)); 
            
            //System.out.println(" dos");
            
            
            //Abre conexion a la BD
            Conexion conex = new Conexion();
    
            cn = conex.ConexionOpen("ctdia");
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("P_RESULTADO").getParameterResultSet();
            while (resultSet.next()) {  
                //System.out.println(" tres");
                MovimientoProtesto movpro = new MovimientoProtesto();
                              
                MovimientoBNB  bnb = new MovimientoBNB();
                bnb.setNro_serie(resultSet.getInt("NRO_SERIE"));
               
                bnb.setF_ingreso(resultSet.getDate("F_INGRESO"));
                bnb.setF_emision(resultSet.getDate("F_EMISION"));                
                bnb.setF_vencimiento(resultSet.getDate("F_VENCIMIENTO"));
                bnb.setCodigo(resultSet.getInt("CODIGO"));
                bnb.setNombre(resultSet.getString("NOMBRE"));
                bnb.setValor_nominal(resultSet.getString("VALOR_NOMINAL"));
                
                bnb.setRegistro_predio(resultSet.getString("REGISTRO_PREDIO"));
                bnb.setTasa(resultSet.getDouble("TASA"));
                bnb.setNro_cuenta(resultSet.getInt("NRO_CUENTA"));
                bnb.setCustodio(resultSet.getString("CUSTODIO"));
                bnb.setTipo(resultSet.getString("TIPO"));
                
                bnb.setCantidad(resultSet.getInt("CANTIDAD"));
                bnb.setPrecio(resultSet.getString("PRECIO"));
                bnb.setAgencia(resultSet.getString("AGENCIA"));
                bnb.setTitulo(resultSet.getString("TITULO"));
                
                bnb.setRut(resultSet.getString("RUT"));
                bnb.setSku(resultSet.getString("SKU"));
                bnb.setDoc_numero(resultSet.getInt("DOC_NUMERO"));
                
                        
                movpro.setBnb(bnb);
                lisProtestos.add(movpro);     
                
            }
            System.out.println("total:::: "+lisProtestos.size());
            
        } catch (Exception e) {
            Logger.getLogger(MaeInversion.class.getName()).log(Level.SEVERE, null, e);            
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
        System.out.println(" <f> allProtestos(MovimientoProtesto oProtesto) " + LocalDateTime.now());
        return lisProtestos;
        
        
    }
    
    
    @Override
    public List<MovimientoProtesto> allMovimientosBnb(MovimientoProtesto oProtesto) {
        
        System.out.println(" <i> allMovimientosBnb(MovimientoProtesto oProtesto) "+ LocalDateTime.now());
        List<MovimientoProtesto> lisProtestos = new ArrayList<MovimientoProtesto>();        
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        //OracleResultSet resultSetB = null;

        try {
            // name of procedure
            String sp = "{call PKG_PROTESTOS.SP_MOVIMIENTOS_BNB(?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
           //System.out.println(" uno");
            
            oList.add(new ParameterOracle("P_DOC_TIPO", oProtesto.getDoc_tipo(), OracleTypes.VARCHAR, ParameterDirection.Input));            
            oList.add(new ParameterOracle("P_CODFONDO", oProtesto.getCodfondo(), OracleTypes.VARCHAR, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_FEMISION1", oProtesto.getFemision(), OracleTypes.DATE, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_FEMISION2", oProtesto.getFprotesto(), OracleTypes.DATE, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_ESTADO", oProtesto.getEstado(), OracleTypes.VARCHAR, ParameterDirection.Input)); 
            oList.add(new ParameterOracle("p_REQ_TIPO", oProtesto.getReq_tipo(), OracleTypes.VARCHAR, ParameterDirection.Input));             
            oList.add(new ParameterOracle("P_RESULTADO", "", OracleTypes.CURSOR, ParameterDirection.Output)); 
            
            //System.out.println(" dos");
            
            
            //Abre conexion a la BD
            Conexion conex = new Conexion();
    
            cn = conex.ConexionOpen("ctdia");
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("P_RESULTADO").getParameterResultSet();
            while (resultSet.next()) {  
                //System.out.println(" tres");
                MovimientoProtesto movpro = new MovimientoProtesto();
                              
                MovimientoBNB  bnb = new MovimientoBNB();
                bnb.setNro_serie(resultSet.getInt("NRO_SERIE"));               
                bnb.setF_ingreso(resultSet.getDate("F_INGRESO"));
                bnb.setCodigo(resultSet.getInt("CODIGO"));
                bnb.setNombre(resultSet.getString("NOMBRE"));
                bnb.setValor_nominal(resultSet.getString("VALOR_NOMINAL"));
                bnb.setPrecio(resultSet.getString("PRECIO"));
                bnb.setTipo(resultSet.getString("TIPO"));
                bnb.setTitulo(resultSet.getString("REQ_TIPO"));
                bnb.setDoc_numero(resultSet.getInt("DOC_NUMERO"));                
                        
                movpro.setBnb(bnb);
                lisProtestos.add(movpro);     
                
            }
            System.out.println("total:::: "+lisProtestos.size());
            
        } catch (Exception e) {
            Logger.getLogger(MaeInversion.class.getName()).log(Level.SEVERE, null, e);            
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
        System.out.println(" <f> allMovimientosBnb(MovimientoProtesto oProtesto) " + LocalDateTime.now());
        return lisProtestos;
        
        
    }
        
    
    @Override
    public List<MovimientoProtesto> buscaInversion(MaeInversion oInversion) {
        
        System.out.println(" <i> allProtestos(MovimientoProtesto oProtesto) "+ LocalDateTime.now());
        List<MovimientoProtesto> lisProtestos = new ArrayList<MovimientoProtesto>();        
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        //OracleResultSet resultSetB = null;

        try {
            // name of procedure
            String sp = "{call PKG_PROTESTOS.SP_BUSCAR_INVERSION(?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
           System.out.println(" uno");
            oList.add(new ParameterOracle("P_CODFONDO", oInversion.getMaeFondo().getCFondoId(), OracleTypes.VARCHAR, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_DVALOR_BV", oInversion.getCInversion(), OracleTypes.VARCHAR, ParameterDirection.Input));      
            oList.add(new ParameterOracle("P_RESULTADO", "", OracleTypes.CURSOR, ParameterDirection.Output)); 
            
            System.out.println(" dos");
            
            
            //Abre conexion a la BD
            Conexion conex = new Conexion();
    
            cn = conex.ConexionOpen("ctdia");
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("P_RESULTADO").getParameterResultSet();
            while (resultSet.next()) {  
                System.out.println(" tres");
                MovimientoProtesto movpro = new MovimientoProtesto();
                                
                movpro.setCodfondo(resultSet.getString("C_FONDO_ID"));
                movpro.setDvalor_bv(resultSet.getString("DVALOR_BV"));
                              
                MaePersona persona = new MaePersona();
                persona.setDApePat(resultSet.getString("TAPATERNO"));
                persona.setDApeMat(resultSet.getString("TAMATERNO"));
                persona.setDNombres(resultSet.getString("TNOMBRES"));
                        
                MaeInversion inversion = new MaeInversion();
                inversion.setcPersonaId(persona);
                movpro.setMaeInversion(inversion);
                
                lisProtestos.add(movpro);        
            }
            System.out.println(" cuatro");
            
        } catch (Exception e) {
            Logger.getLogger(MaeInversion.class.getName()).log(Level.SEVERE, null, e);            
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
        System.out.println(" <f> allProtestos(MovimientoProtesto oProtesto) " + LocalDateTime.now());
        return lisProtestos;
        
        
    }    
    
}
