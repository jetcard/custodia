/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.impl;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleResultSet;
import oracle.jdbc.OracleTypes;

import pop.comun.dominio.MaeDireccion;
import pop.comun.dominio.MaeDocOtros;
import pop.comun.dominio.MaeEmail;
import pop.comun.dominio.MaeFondo;
import pop.comun.dominio.MaeInmueble;
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
/**
 *
 * @author Jyoverar
 */
public class MaeInversionDao extends DBUtil implements IMaeInversionDoa {

    private OracleConnection cn = null;

    public MaeInversionDao() {

    }

    public MaeInversionDao(OracleConnection cnx) { 
        cn = cnx;
    }

    
    @Override
    public ArrayList<MaeInversion> fetchDebtors(MaeInversion oMaeInversion) {
        //System.out.println(" <i> MaeInversionDao fetchDebtors " + LocalDateTime.now());
        ArrayList<MaeInversion> lstInversion = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        //OracleResultSet resultSetB = null;

        try {
            // name of procedure
            String sp = "{call PKG_CUSTODIA.SP_COB_BUSCAR_PERS(?,?,?,?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameter(oMaeInversion);
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
                newInversion.setFEmision(resultSet.getDate("F_EMISION"));
                newInversion.setFRegistro(resultSet.getDate("FREGISTRO"));
                newInversion.setSEstado(resultSet.getString("ESTADO"));
                newInversion.setSIndicador(resultSet.getString("INDICADOR"));
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
        
        return lstInversion;
    }

    
    
    @Override
    public ArrayList<MaeInversion> fetchDebtorsMovi(MaeInversion oMaeInversion) {
        //System.out.println(" <i> MaeInversionDao fetchDebtors " + LocalDateTime.now());
        ArrayList<MaeInversion> lstInversion = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        //OracleResultSet resultSetB = null;

        try {
            // name of procedure
            String sp = "{call PKG_CUSTODIA.SP_COB_BUSCAR_PERS_MOVI(?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameterMovi(oMaeInversion);
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
                newInversion.setAmpliado(resultSet.getString("AMPLIACION"));
                newInversion.setRefinanciado(resultSet.getString("REFINANCIADO"));
                newInversion.setJudicial(resultSet.getString("JUDICIAL"));
                newInversion.setCancelado(resultSet.getString("CANCELADO"));
                newInversion.setTransfAmpl(resultSet.getString("TRANFAMP"));
                newInversion.setTransfendosado(resultSet.getString("TRANFEND"));
                newInversion.setCobranza(resultSet.getString("COBRANZA"));
                newInversion.setTransfrefin(resultSet.getString("TRANFREF"));
                newInversion.setSEstado(resultSet.getString("ESTADO"));
                newInversion.setNDocumento(resultSet.getInt("PRESTADO"));
                newInversion.setSPrestamo(resultSet.getString("PRESTAMO"));
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
    public ArrayList<MaeInversion> fetchDebtorsf2(MaeInversion oMaeInversion) {
        //System.out.println(" <i> MaeInversionDao fetchDebtors " + LocalDateTime.now());
        ArrayList<MaeInversion> lstInversion = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        //OracleResultSet resultSetB = null;

        try {
            // name of procedure
            String sp = "{call PKG_CUSTODIA.SP_COB_BUSCAR_PERSf2(?,?,?,?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameter(oMaeInversion);
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
                newInversion.setFEmision(resultSet.getDate("F_EMISION"));
                newInversion.setFRegistro(resultSet.getDate("FREGISTRO"));
                newInversion.setSEstado(resultSet.getString("ESTADO"));
                newInversion.setsEstadof2(resultSet.getString("ESTADOF2"));
                
                newInversion.setEsActivo(resultSet.getString("ESACTIVO"));
                newInversion.setEsjudicial(resultSet.getString("ESJUDICIAL"));
                newInversion.setsTipOpera(resultSet.getString("TIPOOPERA"));
                newInversion.setSIndicador(resultSet.getString("INDICADOR"));
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
    public ArrayList<MaeInversion> fetchDebtorsCons(MaeInversion oMaeInversion) {
        //System.out.println(" <i> MaeInversionDao fetchDebtors " + LocalDateTime.now());
        ArrayList<MaeInversion> lstInversion = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        //OracleResultSet resultSetB = null;

        try {
            // name of procedure
            String sp = "{call PKG_CUSTODIA.SP_CUSTODIA_CONSULTA(?,?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameterCCus(oMaeInversion);
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
                newInversion.setFEmision(resultSet.getDate("F_EMISION"));
                newInversion.setFRegistro(resultSet.getDate("FREGISTRO"));
                newInversion.setSEstado(resultSet.getString("ESTADO"));
                newInversion.setSIndicador(resultSet.getString("INDICADOR"));
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
    public ArrayList<MaeInversion> fetchMovDocumentos(MaeInversion oMaeInversion) {
        //System.out.println(" <i> MaeInversionDao fetchDebtors " + LocalDateTime.now());
        ArrayList<MaeInversion> lstInversion = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        //OracleResultSet resultSetB = null;

        try {
            // name of procedure
            String sp = "{call PKG_CUSTODIA.SP_MOVIMIENTO_DOCUMENTOS(?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameterconsMov(oMaeInversion);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("ctdia");            
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                //fondo
                
                MaeInversion newInversion = new MaeInversion();
                newInversion.setCInversion(resultSet.getString("DVALOR_BV"));
                newInversion.setsDocumentoId(resultSet.getString("DOCUMENTO_ID"));
                newInversion.setDescripcion(resultSet.getString("DOCUMENTO"));
                newInversion.setNDocumento(resultSet.getInt("NRO_ORDEN"));
                newInversion.setDFechaIng(resultSet.getDate("DFECHA_ENTRADA"));
                newInversion.setDFechaSal(resultSet.getDate("DFECHA_SALIDA"));
                newInversion.setFRegistro(resultSet.getDate("DFECHA_REGISTRO"));
                newInversion.setNDias(resultSet.getInt("NDIAS"));
                newInversion.setSComentario(resultSet.getString("SCOMENTARIO"));
                newInversion.setDesDestino(resultSet.getString("AREA"));
                newInversion.setSMotivo(resultSet.getString("DESMOTIVO"));
                newInversion.setSSolicitante(resultSet.getString("NOMBRES"));
                newInversion.setJefeCustodia(resultSet.getString("NOMBRESCUS"));
                newInversion.setJefeLegal(resultSet.getString("NOMBRESLEGAL"));
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
    public ArrayList<MaeInversion> fetchConsultaxDoc(MaeInversion oMaeInversion) {
        System.out.println("PKG_CUSTODIA.SP_CONSULTAXDOCUMENTO" + LocalDateTime.now());
        ArrayList<MaeInversion> lstInversion = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        //OracleResultSet resultSetB = null;

        try {
            // name of procedure
            String sp = "{call PKG_CUSTODIA.SP_CONSULTAXDOCUMENTO(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
       
            oList = listParameterConsxDoc(oMaeInversion);
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
                MaePersona newPersona = new MaePersona();
                newPersona.setMaeFondo(newFondo);
                newPersona.setCPersonaId(resultSet.getInt("C_PERSONA_ID"));
                newPersona.setANroDocumento(resultSet.getString("A_NRO_DOCUMENTO"));
             
                newPersona.setDNombres(resultSet.getString("NOMBRES"));
                MaeInversion newInversion = new MaeInversion();
                newInversion.setCInversion(resultSet.getString("C_INVERSION"));
                newInversion.setDescripcion(resultSet.getString("DOCUMENTO"));
                newInversion.setSFechaIng(resultSet.getString("FREGISTRO"));
                newInversion.setSEstado(resultSet.getString("ESTADODOC"));
                newInversion.seteEstado(resultSet.getString("ESTADOREGI"));
                newInversion.setsTipOpera(resultSet.getString("TIPOPERACION"));
                newInversion.setMaeFondo(newFondo);
                newInversion.setcPersonaId(newPersona);
                lstInversion.add(newInversion);
                
                //System.out.println("----------" + resultSet.getString("C_INVERSION"));
                
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
    public ArrayList<MaeInversion> fetchConsultaxMov(MaeInversion oMaeInversion) {
        System.out.println(" <i> SP_CONSULTAXMOVIMIENTO" + LocalDateTime.now());
        ArrayList<MaeInversion> lstInversion = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        //OracleResultSet resultSetB = null;

        try {
            // name of procedure
            String sp = "{call PKG_CUSTODIA.SP_CONSULTAXMOVIMIENTO(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameterConsxMov(oMaeInversion);
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
                MaePersona newPersona = new MaePersona();
                newPersona.setMaeFondo(newFondo);
                newPersona.setCPersonaId(resultSet.getInt("C_PERSONA_ID"));
                newPersona.setANroDocumento(resultSet.getString("A_NRO_DOCUMENTO"));
  
                newPersona.setDNombres(resultSet.getString("PROPIETARIO"));
                
                MaeInversion newInversion = new MaeInversion();
                newInversion.setCInversion(resultSet.getString("C_INVERSION"));
                newInversion.setDescripcion(resultSet.getString("DOCUMENTO"));
                newInversion.setSEstado(resultSet.getString("CONDICION"));  //CONDICION
                newInversion.seteEstado(resultSet.getString("INDICADOR")); // INDICADOR
                newInversion.setDesDestino(resultSet.getString("AREA_SOLICITANTE"));
                newInversion.setDusuarioNombres(resultSet.getString("SOLICITANTE"));
                newInversion.setSFechaIng(resultSet.getString("FECINGRESO"));    //fecha de ingreso
                newInversion.setSFechaSal(resultSet.getString("FECSALIDA")); //fecha de salida
                newInversion.setnMovIng(resultSet.getInt("NROENTRADA") );
                newInversion.setnMovSal(resultSet.getInt("NROSALIDA") );
                newInversion.setNDias(resultSet.getInt("NDIAS") );
                newInversion.setsTipOpera(resultSet.getString("TIPOPERACION")); //
                newInversion.setcTipoInv(resultSet.getString("TIPOMOV"));
                newInversion.setSComentario(resultSet.getString("ESPRESTAMO"));
                newInversion.setMaeFondo(newFondo);
                newInversion.setcPersonaId(newPersona);
                
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
    public ArrayList<MaeInversion> fetchCustodia(MaeInversion oMaeInversion) {
        //System.out.println(" <i> MaeInversionDao fetchDebtors " + LocalDateTime.now());
        ArrayList<MaeInversion> lstInversion = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        //OracleResultSet resultSetB = null;

        try {
            // name of procedure
            String sp = "{call PKG_CUSTODIA.SP_COB_BUSCAR_CUSTODIA(?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameterCus(oMaeInversion);
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
                MaePersona newPersona = new MaePersona();
                newPersona.setMaeFondo(newFondo);
                newPersona.setCPersonaId(resultSet.getInt("C_PERSONA_ID"));
                newPersona.setANroDocumento(resultSet.getString("A_NRO_DOCUMENTO"));
             
                newPersona.setDApePat(resultSet.getString("D_APE_PAT"));
                newPersona.setDApeMat(resultSet.getString("D_APE_MAT"));
                newPersona.setDNombres(resultSet.getString("D_NOMBRES"));
             
                
                MaeInversion newInversion = new MaeInversion();
                newInversion.setCInversion(resultSet.getString("C_INVERSION"));
                newInversion.setsDocumentoId(resultSet.getString("DOCUMENTO_ID"));
                newInversion.setDescripcion(resultSet.getString("DOCUMENTO"));
                newInversion.setFRegistro(resultSet.getDate("FREGISTRO"));
                newInversion.setfInterno(resultSet.getDate("FREGISTRO"));
                newInversion.setSEstado(resultSet.getString("ESTADO"));
                newInversion.setMaeFondo(newFondo);
                newInversion.setcPersonaId(newPersona);
                newInversion.setSSubsanado(resultSet.getString("C_SUBSANADO"));
                newInversion.setSComentario(resultSet.getString("C_COMENTARIO"));
                newInversion.setFSubsanado(resultSet.getDate("F_SUBSANADO"));
                newInversion.setsSelec(resultSet.getString("SELECIONADO"));
                newInversion.setFGiro(resultSet.getDate("D_FGIRO"));
                if (resultSet.getString("SELECIONADO").equals("S")){
                    newInversion.setXFlagSel(Boolean.TRUE);
                }else{
                  newInversion.setXFlagSel(Boolean.FALSE);
                }
                newInversion.setsCerrado(resultSet.getString("CERRADO"));
                if (resultSet.getString("CERRADO").equals("S")){
                    newInversion.setXFlagCerrado(Boolean.TRUE);
                }else{
                  newInversion.setXFlagCerrado(Boolean.FALSE);
                }
                newInversion.setNDias(resultSet.getInt("NDIAS"));
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
    public ArrayList<MaeEmail> fetchEmail() {
        //System.out.println(" <i> MaeInversionDao fetchDebtors " + LocalDateTime.now());
        ArrayList<MaeEmail> lstEmail = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
       
        //OracleResultSet resultSetB = null;
        try {
            // name of procedure
            String sp = "{call PKG_CUSTODIA.SP_COB_BUSCAR_EMAIL(?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameterEmail();
            //Abre conexion a la BD
            
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("ctdia");            
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();
            while (resultSet.next()) {
                //fondo
          
                MaeEmail newMail = new MaeEmail();
                newMail.setDusuario(resultSet.getString("USUARIO"));
                newMail.setClave(resultSet.getString("CLAVE"));
                newMail.setCuerpo(resultSet.getString("CUERPO"));
                newMail.setAsunto(resultSet.getString("ASUNTO"));
                newMail.setDemail(resultSet.getString("CORREO"));
                newMail.setDnombre(resultSet.getString("NOTIFICA"));
                lstEmail.add(newMail);
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
        return lstEmail;
    }

    
   @Override
    public ArrayList<MaeInversion> fetchDetalleCustodia(MaeInversion oMaeInversion) {
        //System.out.println(" <i> MaeInversionDao fetchDebtors " + LocalDateTime.now());
        ArrayList<MaeInversion> lstInversion = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        //OracleResultSet resultSetB = null;
        try {
            // name of procedure
            String sp = "{call PKG_CUSTODIA.SP_COB_BUSCAR_DETALLE_DOC(?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameterDetalleCus(oMaeInversion);
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
                MaePersona newPersona = new MaePersona();
                newPersona.setMaeFondo(newFondo);
                
                MaeInversion newInversion = new MaeInversion();
                newInversion.setiDoc_numero(resultSet.getInt("NRO_ITEM"));
                newInversion.setCInversion(resultSet.getString("DVALOR_BV"));
                newInversion.setsDocumentoId(resultSet.getString("DOCUMENTO_PADRE"));
                newInversion.setSCODDocumento(resultSet.getString("DOCUMENTO_ID"));
                newInversion.setDescripcion(resultSet.getString("DESCRIPCION"));
                newInversion.setFRegistro(resultSet.getDate("F_REGISTRO"));
                newInversion.setMaeFondo(newFondo);
                newInversion.setcPersonaId(newPersona);
                newInversion.setSComentario(resultSet.getString("C_COMENTARIO"));
                
                
                lstInversion.add(newInversion);
            }
            System.out.println("canctidad es lstinversion"+lstInversion.size());
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
        return lstInversion;
    }

    
    
    @Override
    public ArrayList<MaeInversion> fetchCustodiaf2(MaeInversion oMaeInversion) {
        //System.out.println(" <i> MaeInversionDao fetchDebtors " + LocalDateTime.now());
        ArrayList<MaeInversion> lstInversion = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        //OracleResultSet resultSetB = null;
        try {
            // name of procedure
            String sp = "{call PKG_CUSTODIA.SP_COB_BUSCAR_CUSTODIAf2(?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameterCus(oMaeInversion);
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
                MaePersona newPersona = new MaePersona();
                newPersona.setMaeFondo(newFondo);
                newPersona.setCPersonaId(resultSet.getInt("C_PERSONA_ID"));
                newPersona.setANroDocumento(resultSet.getString("A_NRO_DOCUMENTO"));
             
                newPersona.setDApePat(resultSet.getString("D_APE_PAT"));
                newPersona.setDApeMat(resultSet.getString("D_APE_MAT"));
                newPersona.setDNombres(resultSet.getString("D_NOMBRES"));
             
                
                MaeInversion newInversion = new MaeInversion();
                newInversion.setCInversion(resultSet.getString("C_INVERSION"));
                newInversion.setsDocumentoId(resultSet.getString("DOCUMENTO_ID"));
                newInversion.setDescripcion(resultSet.getString("DOCUMENTO"));
                newInversion.setFRegistro(resultSet.getDate("FREGISTRO"));
                newInversion.setfInterno(resultSet.getDate("FREGISTRO"));
                newInversion.setSEstado(resultSet.getString("ESTADO"));
                newInversion.setsEstadof2(resultSet.getString("ESTADO2"));
                newInversion.setMaeFondo(newFondo);
                newInversion.setcPersonaId(newPersona);
                newInversion.setSSubsanado(resultSet.getString("C_SUBSANADO"));
                newInversion.setSComentario(resultSet.getString("C_COMENTARIO"));
                newInversion.setFSubsanado(resultSet.getDate("F_SUBSANADO"));
                newInversion.setsSelec(resultSet.getString("SELECIONADO"));
                if (resultSet.getString("SELECIONADO").equals("S")){
                    newInversion.setXFlagSel(Boolean.TRUE);
                }else{
                  newInversion.setXFlagSel(Boolean.FALSE);
                }
                newInversion.setsCerrado(resultSet.getString("CERRADO"));
                if (resultSet.getString("CERRADO").equals("S")){
                    newInversion.setXFlagCerrado(Boolean.TRUE);
                }else{
                  newInversion.setXFlagCerrado(Boolean.FALSE);
                }
                newInversion.setNDias(resultSet.getInt("NDIAS"));
                
                newInversion.setsCodOrigen(resultSet.getString("C_ORIGEN"));
                newInversion.setsCodDigital(resultSet.getString("S_DIGITAL"));
                newInversion.setsCustodia(resultSet.getString("S_ESCUSTODIA"));
                newInversion.setsDestino(resultSet.getString("S_DESTINO"));
                newInversion.setFEscritura(resultSet.getDate("F_ESCRITURA"));
                newInversion.setSNotaria(resultSet.getString("S_NOTARIA"));
                newInversion.setsAsHipo(resultSet.getString("S_ASHIPO"));
                newInversion.setsAsExpTchn(resultSet.getString("S_ASEXPTCHN"));
                newInversion.setsPeHipo(resultSet.getString("S_PEHIPO"));
                newInversion.setsAsPoder(resultSet.getString("S_ASPODER"));
                newInversion.setsPePoder(resultSet.getString("S_PEPODER"));
                newInversion.setsTchn(resultSet.getString("S_TCHN"));
                newInversion.setfUsuarioAdd(resultSet.getDate("D_USU_INS"));
                int res=resultSet.getInt("FLAG_DETALLE");
                System.out.println("flag_detalle"+res);
                if (res==1){
                   newInversion.setXFlagDetalle(true);
                }else{
                    newInversion.setXFlagDetalle(false);
                }
                
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
        return lstInversion;
    }


      @Override
    public ArrayList<MaeInversion> fetchCustodiaMovi(MaeInversion oMaeInversion) {
        //System.out.println(" <i> MaeInversionDao fetchDebtors " + LocalDateTime.now());
        ArrayList<MaeInversion> lstInversion = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        //OracleResultSet resultSetB = null;
        try {
            // name of procedure
                String sp = "{call PKG_CUSTODIA.SP_COB_BUSCAR_DOCUMENTOS(?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameterCus(oMaeInversion);
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
                MaePersona newPersona = new MaePersona();
                newPersona.setMaeFondo(newFondo);
                newPersona.setCPersonaId(resultSet.getInt("C_PERSONA_ID"));
                newPersona.setANroDocumento(resultSet.getString("A_NRO_DOCUMENTO"));
             
                newPersona.setDApePat(resultSet.getString("D_APE_PAT"));
                newPersona.setDApeMat(resultSet.getString("D_APE_MAT"));
                newPersona.setDNombres(resultSet.getString("D_NOMBRES"));
             
                
                MaeInversion newInversion = new MaeInversion();
                newInversion.setCInversion(resultSet.getString("C_INVERSION"));
                newInversion.setsDocumentoId(resultSet.getString("DOCUMENTO_ID"));
                newInversion.setDescripcion(resultSet.getString("DOCUMENTO"));
                newInversion.setFRegistro(resultSet.getDate("FREGISTRO"));
                newInversion.setfInterno(resultSet.getDate("FREGISTRO"));
                newInversion.setSEstado(resultSet.getString("DESTADO"));
                newInversion.setsDestino(resultSet.getString("CODAREA"));
                newInversion.setDesDestino(resultSet.getString("DESCODAREA"));
                newInversion.setDusuarioNombres(resultSet.getString("SOLICITANTE"));
                newInversion.setSFechaIng(resultSet.getString("FECINGRESO"));    //fecha de ingreso
                newInversion.setSFechaSal(resultSet.getString("FECSALIDA")); //fecha de salida
                newInversion.setSFechaDevol(resultSet.getString("FECDESEMBOLSO"));
                newInversion.setNDias(resultSet.getInt("DIAS") );
                newInversion.setsTipOpera(resultSet.getString("MOTIVO")); //
                newInversion.setSMotivo(resultSet.getString("IDMOTIVO"));
                newInversion.setcTipoInv(resultSet.getString("TIPOMOVI"));
                newInversion.setMaeFondo(newFondo);
                newInversion.setcPersonaId(newPersona);
                
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
        return lstInversion;
    }

    
    @Override
    public ArrayList<MaeInversion> fetchCustodiaxDoc(MaeInversion oMaeInversion) {
        //System.out.println(" <i> MaeInversionDao fetchDebtors " + LocalDateTime.now());
        ArrayList<MaeInversion> lstInversion = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        //OracleResultSet resultSetB = null;
        try {
            // name of procedure
            String sp = "{call PKG_CUSTODIA.SP_COB_BUSCAR_X_DOC(?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameterxDoc(oMaeInversion);
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
                MaePersona newPersona = new MaePersona();
                newPersona.setMaeFondo(newFondo);
                newPersona.setCPersonaId(resultSet.getInt("C_PERSONA_ID"));
                newPersona.setANroDocumento(resultSet.getString("A_NRO_DOCUMENTO"));
             
                newPersona.setDApePat(resultSet.getString("D_APE_PAT"));
                newPersona.setDApeMat(resultSet.getString("D_APE_MAT"));
                newPersona.setDNombres(resultSet.getString("D_NOMBRES"));
             
                
                MaeInversion newInversion = new MaeInversion();
                newInversion.setCInversion(resultSet.getString("C_INVERSION"));
                newInversion.setsDocumentoId(resultSet.getString("DOCUMENTO_ID"));
                newInversion.setDescripcion(resultSet.getString("DOCUMENTO"));
                newInversion.setFRegistro(resultSet.getDate("FREGISTRO"));
                newInversion.setfInterno(resultSet.getDate("FREGISTRO"));
                newInversion.setSEstado(resultSet.getString("DESTADO"));
                newInversion.setsDestino(resultSet.getString("CODAREA"));
                newInversion.setDusuarioNombres(resultSet.getString("SOLICITANTE"));
                newInversion.setSFechaIng(resultSet.getString("FECINGRESO"));    //fecha de ingreso
                newInversion.setSFechaSal(resultSet.getString("FECSALIDA")); //fecha de salida
                newInversion.setcTipoInv(resultSet.getString("TIPOMOVI")); // tipo movimiento
                newInversion.setNDias(resultSet.getInt("DIAS") );
                newInversion.setsTipOpera(resultSet.getString("MOTIVO")); //
                newInversion.setSMotivo(resultSet.getString("IDMOTIVO"));
                newInversion.setMaeFondo(newFondo);
                newInversion.setcPersonaId(newPersona);
                
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
        return lstInversion;
    }

    
    
    @Override
    public ArrayList<MaeInversion> fetchListaSolicitante(String cArea) {
        //System.out.println(" <i> MaeInversionDao fetchDebtors " + LocalDateTime.now());
        ArrayList<MaeInversion> lstInversion = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        //OracleResultSet resultSetB = null;
        try {
            // name of procedure
            String sp = "{call PKG_INFO_AREAS.SP_BUSCA_USU_AREA(?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameterSol(cArea);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("ctdia");            
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();
            while (resultSet.next()) {
                //fondo
                    
                
                MaeInversion newInversion = new MaeInversion();
                newInversion.setSSolicitante(resultSet.getString("USUARIO"));
                newInversion.setSNombreSol(resultSet.getString("NOMBRES"));
                
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
        return lstInversion;
    }

    
    
    
    
    
    @Override
    public Integer validaCorreo(MaeInversion oMaeInversion) {
        System.out.println(" <i> MaeInversionDao fetchDebtors " + oMaeInversion.getSComentario());
        Integer Rspta = 0;
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        //OracleResultSet resultSetB = null;

        try {
            // name of procedure
            String sp = "{call PKG_CUSTODIA.SP_VALIDA_CORREO(?,?,?,?,?)}";
            // list of parameter
             
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            
            oList = listParameterValcorreo(oMaeInversion);
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
    public Integer InsCustodia(MaeInversion oMaeInversion) {
        System.out.println(" <i> MaeInversionDao fetchDebtors " + oMaeInversion.getSComentario());
        Integer Rspta = 1;
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        //OracleResultSet resultSetB = null;

        try {
            // name of procedure
            String sp = "{call PKG_CUSTODIA.INSERTAR_CUSTODIA(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameter
             
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            System.out.println( oMaeInversion.getSEstado()  );
           
            oList = listParameterIns(oMaeInversion);
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
    public Integer InsCustodiaf2(MaeInversion oMaeInversion) {
        System.out.println(" <i> MaeInversionDao fetchDebtors " + oMaeInversion.getSComentario());
        Integer Rspta = 1;
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        //OracleResultSet resultSetB = null;

        try {
            // name of procedure
            String sp = "{call PKG_CUSTODIA.INSERTAR_CUSTODIAf2(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameter
             
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
           
            oList = listParameterInsf2(oMaeInversion);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
    
            cn = conex.ConexionOpen("ctdia");
            // execute procedure
            // execute procedure
            
            runSP(oList, cn, cmd, sp);
          Rspta = getOutputParameter("PO_I_PROCESO").getParameterInt();
          Rspta = 1;
      
            
            
        } catch (Exception e) {
            Rspta=0;
            Logger.getLogger(MaeInversion.class.getName()).log(Level.SEVERE, null, e);
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
    public Integer DelDocCustodia(MaeInversion oMaeInversion) {
        System.out.println(" <i> MaeInversionDao fetchDebtors " + oMaeInversion.getSComentario());
        Integer Rspta = 1;
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        //OracleResultSet resultSetB = null;

        try {
            // name of procedure
            String sp = "{call PKG_CUSTODIA.ELIMINAR_DOC_DETALLE(?,?,?,?,?,?,?,?)}";
            // list of parameter
             
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
           
            oList = listParameterDelCusDocum(oMaeInversion);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
    
            cn = conex.ConexionOpen("ctdia");
            // execute procedure
            // execute procedure
            
            runSP(oList, cn, cmd, sp);
          Rspta = getOutputParameter("PO_I_PROCESO").getParameterInt();
          System.out.println("respuesta "+ Rspta);
            
            
        } catch (Exception e) {
            Rspta=0;
            Logger.getLogger(MaeInversion.class.getName()).log(Level.SEVERE, null, e);
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
    public Integer InsCustodiaMov(MaeInversion oMaeInversion) {
        System.out.println(" <i> MaeInversionDao fetchDebtors " + oMaeInversion.getSComentario());
        Integer Rspta = 1;
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        //OracleResultSet resultSetB = null;

        try {
            // name of procedure
            String sp = "{call PKG_CUSTODIA.INSERTAR_CUSTODIAMOV(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameter
             
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            
            oList = listParameterInsMov(oMaeInversion);
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
    public Integer DelCustodia(String cFondo,String cMaeInversion ) {
        Integer Rspta = 1;
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        //OracleResultSet resultSetB = null;
      
        try {
            // name of procedure
            String sp = "{call PKG_CUSTODIA.ELIMINAR_CUSTODIA(?,?,?,?,?)}";
            // list of parameter
             
            List<ParameterOracle> oList = new ArrayList<>();
        
           
            oList = listParameterDel(cFondo,cMaeInversion);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("ctdia");
            // execute procedure
            // execute procedure
            
            runSP(oList, cn, cmd, sp);
       
       //     Rspta = getOutputParameter("PO_I_PROCESO").getParameterInt();
            
            
        } catch (Exception e) {
            Logger.getLogger(MaeInversion.class.getName()).log(Level.SEVERE, null, e);
            Rspta=0;
        }
        finally 
        {
            Rspta=1;
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


    
    private List<MaeTelefono> listTel(List<MaeTelefono> oListBusq, int cPersId) {
        List<MaeTelefono> oList = new ArrayList<>();

        for (MaeTelefono tel : oListBusq) {
            if (tel.getCPersonaId() == (cPersId)) {
                oList.add(tel);
            }
        }
        return oList;
    }

    private List<MaePersonaInmueble> listPer(List<MaePersonaInmueble> oListBusq, Number cInmuebleId, Number cMaeInversionId) {
        List<MaePersonaInmueble> oList = new ArrayList<>();

        oListBusq.stream().filter((perInm) -> (perInm.getMaeInmueble().getCInmuebleId().equals(cInmuebleId)
                & perInm.getMaeInmueble().getMaeInversion().getcMaeInversionId().equals(cMaeInversionId))).forEach((perInm) -> {
            oList.add(perInm);
        });
        return oList;
    }

    private List<ParameterOracle> listParameter(MaeInversion oMaeInversion) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oMaeInversion.getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_INVERSION", oMaeInversion.getCInversion(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_A_NRO_DOCUMENTO", oMaeInversion.getcPersonaId().getANroDocumento(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_TIPO_FECHA", oMaeInversion.getCorigenId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PD_FECHA_INI", oMaeInversion.getFinicio(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PD_FECHA_FIN", oMaeInversion.getFfin(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO_ID", oMaeInversion.getcUsuarioAdd(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
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

    private List<ParameterOracle> listParameterMC(MaeInversion oMaeInversion) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oMaeInversion.getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_INVERSION", oMaeInversion.getCInversion(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }
    
     private List<ParameterOracle> listParameterMovi(MaeInversion oMaeInversion) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oMaeInversion.getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_INVERSION", oMaeInversion.getCInversion(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_A_NRO_DOCUMENTO", oMaeInversion.getcPersonaId().getANroDocumento(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO_ID", oMaeInversion.getcUsuarioAdd(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }
    
    private List<ParameterOracle> listParameterCCus(MaeInversion oMaeInversion) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oMaeInversion.getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_INVERSION", oMaeInversion.getCInversion(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_A_NRO_DOCUMENTO", oMaeInversion.getcPersonaId().getANroDocumento(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_ESTADO", oMaeInversion.getSEstado(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_INDICADOR", oMaeInversion.getSIndicador(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_TIPO_FECHA", oMaeInversion.getCorigenId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PD_FECHA_INI", oMaeInversion.getFinicio(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PD_FECHA_FIN", oMaeInversion.getFfin(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO_ID", oMaeInversion.getcUsuarioAdd(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

    
    
    
     private List<ParameterOracle> listParameterCus(MaeInversion oMaeInversion) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oMaeInversion.getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_INVERSION", oMaeInversion.getCInversion(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO_ID", oMaeInversion.getcUsuarioAdd(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }
     
     
    private List<ParameterOracle> listParameterDetalleCus(MaeInversion oMaeInversion) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oMaeInversion.getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_INVERSION", oMaeInversion.getCInversion(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_DOCUMENTO", oMaeInversion.getsDocumentoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }
      
     
    private List<ParameterOracle> listParameterconsMov(MaeInversion oMaeInversion) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PN_NUMERO", oMaeInversion.getNDocumento() ,OracleTypes.NUMBER , ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oMaeInversion.getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_INVERSION", oMaeInversion.getCInversion(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_TIPO_INV", oMaeInversion.getsTipOpera(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }
      
     
     
    private List<ParameterOracle> listParameterxDoc(MaeInversion oMaeInversion) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oMaeInversion.getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_INVERSION", oMaeInversion.getCInversion(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_DOCUMENTO", oMaeInversion.getsDocumentoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO_ID", oMaeInversion.getcUsuarioAdd(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }
      
     
    private List<ParameterOracle> listParameterEmail() {
        List<ParameterOracle> oListParam = new ArrayList<>();

          oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }
    
    
    private List<ParameterOracle> listParameterValcorreo(MaeInversion oMaeInversion) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oMaeInversion.getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_DVALOR_BV", oMaeInversion.getCInversion(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_I_PROCESO", 0, OracleTypes.NUMBER, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }
     

    
    private List<ParameterOracle> listParameterIns(MaeInversion oMaeInversion) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oMaeInversion.getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_DVALOR_BV", oMaeInversion.getCInversion(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_DOCUMENTO_ID", oMaeInversion.getsDocumentoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PD_FREGISTRO", oMaeInversion.getFRegistro(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_ESTADO", oMaeInversion.getSEstado(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_COMENTARIO", oMaeInversion.getSComentario(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_CSUBSANADO", oMaeInversion.getSSubsanado(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PD_FSUBSANADO", oMaeInversion.getFSubsanado(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_CUSU_INS", oMaeInversion.getcUsuarioAdd() , OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PD_DUSU_INS", oMaeInversion.getfUsuarioAdd(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_CUSU_UPD", oMaeInversion.getcUsuarioMod(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PD_DUSU_UPD", oMaeInversion.getfUsuarioMod(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_SELEC", oMaeInversion.getsSelec(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PD_FGIRO", oMaeInversion.getFGiro(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_I_PROCESO", 0, OracleTypes.NUMBER, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }
     
   private List<ParameterOracle> listParameterDelCusDocum(MaeInversion oMaeInversion) { 
        List<ParameterOracle> oListParam = new ArrayList<>();
        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oMaeInversion.getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_DVALOR_BV", oMaeInversion.getCInversion(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_NROITEM", oMaeInversion.getiDoc_numero(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_DOCUMENTO_P", oMaeInversion.getsDocumentoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_DOCUMENTO", oMaeInversion.getsCODDocumento(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_I_PROCESO", 0, OracleTypes.NUMBER, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        return oListParam;

   }

    private List<ParameterOracle> listParameterInsf2(MaeInversion oMaeInversion) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oMaeInversion.getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_DVALOR_BV", oMaeInversion.getCInversion(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_DOCUMENTO_ID", oMaeInversion.getsDocumentoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PD_FINTERNO", oMaeInversion.getfInterno(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PD_FREGISTRO", oMaeInversion.getFRegistro(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_ESTADO", oMaeInversion.getSEstado(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_COMENTARIO", oMaeInversion.getSComentario(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_CSUBSANADO", oMaeInversion.getSSubsanado(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PD_FSUBSANADO", oMaeInversion.getFSubsanado(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_DIGITAL", oMaeInversion.getsCodDigital(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_ESCUSTODIA", oMaeInversion.getsCustodia(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PDF_ESCRITURA", oMaeInversion.getFEscritura(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PS_NOTARIA", oMaeInversion.getSNotaria(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PS_ASHIPO", oMaeInversion.getsAsHipo(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PS_ASEXPTCHN", oMaeInversion.getsAsExpTchn(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PS_PEHIPO", oMaeInversion.getsPeHipo(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PS_ASPODER", oMaeInversion.getsAsPoder(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PS_PEPODER", oMaeInversion.getsPePoder(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PS_TCHN", oMaeInversion.getsTchn(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_ORIGEN", oMaeInversion.getsCodOrigen(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PS_DESTINO", oMaeInversion.getsDestino(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_CUSU_INS", oMaeInversion.getcUsuarioAdd() , OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PD_DUSU_INS", oMaeInversion.getfUsuarioAdd(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_CUSU_UPD", oMaeInversion.getcUsuarioMod(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PD_DUSU_UPD", oMaeInversion.getfUsuarioMod(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_SELEC", oMaeInversion.getsSelec(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_I_PROCESO", 0, OracleTypes.NUMBER, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        
        return oListParam;
    }
     

     
    private List<ParameterOracle> listParameterDel(String cFondo, String  cMaeInversion) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", cFondo, OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_DVALOR_BV", cMaeInversion, OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_I_PROCESO", 0, OracleTypes.INTEGER, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }
         

    private List<ParameterOracle> listParameterSol(String cArea) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        oListParam.add(new ParameterOracle("PI_C_TABLA_DET_ID", cArea, OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }
         

    
    
    private List<ParameterOracle> listParameterInsMov(MaeInversion oMaeInversion) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        oListParam.add(new ParameterOracle("PS_NRO_ORDEN",  oMaeInversion.getNDocumento(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PS_SCOD_AREA", oMaeInversion.getsDestino(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PS_SCOD_SOLICITANTE", oMaeInversion.getSSolicitante(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PD_DFECHA_SALIDA", oMaeInversion.getDFechaSal(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PD_DFECHA_ENTRADA", oMaeInversion.getDFechaIng(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PN_NDIAS", oMaeInversion.getNDias(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PS_SMOTIVO", oMaeInversion.getSMotivo(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PS_DOCUMENTO_ID", oMaeInversion.getsDocumentoId() , OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PS_SCOMENTARIO", oMaeInversion.getSComentario(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PS_DVALOR_BV",oMaeInversion.getCInversion(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PS_C_FONDO_ID",oMaeInversion.getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PS_C_USU_INS",  oMaeInversion.getcUsuarioAdd(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PD_D_USU_INS", oMaeInversion.getfUsuarioAdd(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PS_C_USU_UPD", oMaeInversion.getcUsuarioMod(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PD_D_USU_UPD", oMaeInversion.getfUsuarioMod(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PS_TIPOMOV", oMaeInversion.getsTipOpera(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PS_SELECCION", oMaeInversion.getsSelec(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_I_PROCESO", 0, OracleTypes.NUMBER, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        
        return oListParam;
    }
     

    
    public OracleConnection getCn() {
        return cn;
    }

    public void setCn(OracleConnection cn) {
        this.cn = cn;
    }

    

    private List<ParameterOracle> listParameterConsXMov(MaeInversion oMaeInversion) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PN_NUMERO", oMaeInversion.getNDocumento() ,OracleTypes.NUMBER , ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oMaeInversion.getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_INVERSION", oMaeInversion.getCInversion(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_TIPO_INV", oMaeInversion.getsTipOpera(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }
      
     
     
    private List<ParameterOracle> listParameterConsxDoc(MaeInversion oMaeInversion) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oMaeInversion.getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_INVERSION", oMaeInversion.getCInversion(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_A_NRO_DOCUMENTO", oMaeInversion.getcPersonaId().getANroDocumento(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_NOMBRES", oMaeInversion.getcPersonaId().getDApeMat(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_ESTADOREG", oMaeInversion.getSEstado(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_ESTADODOC", oMaeInversion.getsEstadof2(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_DOCUMENTO", oMaeInversion.getSCODDocumento(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PD_FECHA_INI", oMaeInversion.getFinicio(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PD_FECHA_FIN", oMaeInversion.getFfin(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO_ID", oMaeInversion.getcUsuarioAdd(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
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
       

    

    private List<ParameterOracle> listParameterConsxMov(MaeInversion oMaeInversion) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oMaeInversion.getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_INVERSION", oMaeInversion.getCInversion(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_A_NRO_DOCUMENTO", oMaeInversion.getcPersonaId().getANroDocumento(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_NOMBRES", oMaeInversion.getcPersonaId().getDApeMat(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_ESTADOREG", oMaeInversion.getSPrestamo(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_ESTADODOC", oMaeInversion.getSEstado(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_DOCUMENTO", oMaeInversion.getSCODDocumento(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PD_FECHA_INI", oMaeInversion.getFinicio(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PD_FECHA_FIN", oMaeInversion.getFfin(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO_ID", oMaeInversion.getcUsuarioAdd(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;  
    }
       
    @Override
    public Integer InsDocCustodia(MaeDocOtros oMaeDocOtros) {
        System.out.println(" <i> MaeInversionDao InsDocCustodia" + oMaeDocOtros.toString());
        Integer Rspta = 1;
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            String sp = "{call PKG_COB_TABLAS.SP_INSERTAR_DOC_CUST(?,?,?,?,?,?,?)}";
            List<ParameterOracle> oList = new ArrayList<>();
            oList = listParameterInsDocCust(oMaeDocOtros);
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("ctdia");
            runSP(oList, cn, cmd, sp);
          Rspta = getOutputParameter("PO_I_PROCESO").getParameterInt();
        } catch (Exception e) {
            Logger.getLogger(MaeInversion.class.getName()).log(Level.SEVERE, null, e);
            Rspta=0;
        }
        finally 
        {
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
        return Rspta;
    }
    
    
      @Override
    public Integer GrabarDocDetCustodia(MaeInversion oInversion) {
        Integer Rspta = 1;
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            String sp = "{call PKG_CUSTODIA.GUARDAR_DOC_DETALLE(?,?,?,?,?,?,?,?,?,?,?,?)}";
            List<ParameterOracle> oList = new ArrayList<>();
            oList = listParametergrabDocDetCust(oInversion);
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("ctdia");
            System.out.println("paso por aqui GUARDAR_DOC_DETALLE" );
            runSP(oList, cn, cmd, sp);
          Rspta = getOutputParameter("PO_I_PROCESO").getParameterInt();
          System.out.println("paso por aqui guardar" +Rspta);
        } catch (Exception e) {
            Logger.getLogger(MaeInversion.class.getName()).log(Level.SEVERE, null, e);
            Rspta=0;
        }
        finally 
        {
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
        return Rspta;
    }
    
    private List<ParameterOracle> listParameterInsDocCust(MaeDocOtros oMaeDocOtros) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        oListParam.add(new ParameterOracle("PC_C_FONDO_ID", oMaeDocOtros.getCfondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_DVALOR_BV", oMaeDocOtros.getDvalorBV(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_DOCUMENTO_ID", oMaeDocOtros.getDocumentoID(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_CUSU_INS", oMaeDocOtros.getcUsuarioAdd() , OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_I_PROCESO", 0, OracleTypes.NUMBER, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }    
    
    
    
    private List<ParameterOracle> listParametergrabDocDetCust(MaeInversion oMaeInversion) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        oListParam.add(new ParameterOracle("PC_C_FONDO_ID", oMaeInversion.getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_DVALOR_BV", oMaeInversion.getCInversion(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_NROITEM", oMaeInversion.getiDoc_numero(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_DOCUMENTO_P", oMaeInversion.getsDocumentoId() , OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_DOCUMENTO", oMaeInversion.getSCODDocumento(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PS_DESCRIPCION", oMaeInversion.getDescripcion(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PS_COMENTARIO", oMaeInversion.getsComentario(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PD_FECHA", oMaeInversion.getFRegistro(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PS_USUARIO", oMaeInversion.getcUsuarioAdd(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_I_PROCESO", 0, OracleTypes.NUMBER, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }    
}
