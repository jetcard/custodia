/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.impl;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleResultSet;
import oracle.jdbc.OracleTypes;
import pop.comun.dominio.CobTchn;
import pop.comun.dominio.LegDemanTchn;
import pop.comun.dominio.LegSeguiTchn;
import pop.comun.dominio.LegalTchn;
import pop.comun.dominio.MaeAnio;
import pop.comun.dominio.MaeDeposito;
import pop.comun.dominio.MaeDepositoPago;
import pop.comun.dominio.MaeDireccion;
import pop.comun.dominio.MaeEstadoCuenta;
import pop.comun.dominio.MaeEstadoLegal;
import pop.comun.dominio.MaeFondo;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MaeMes;
import pop.comun.dominio.MaePersona;
import pop.comun.dominio.MaeUbigeo;
import pop.webcustodia.common.DBUtil;
import pop.webcustodia.common.ParameterDirection;
import pop.webcustodia.common.ParameterOracle;
import pop.webcustodia.iface.IMaeEstadoCuentaDao;

import pop.webcustodia.conn.Conexion;
import pop.webcustodia.dao.FactoryDao;
import pop.webcustodia.iface.ILegalDao;
/**
 *
 * @author Jyoverar
 */
public class LegalDao extends DBUtil implements ILegalDao {

    private OracleConnection cn = null;

    public LegalDao() {

    }

    public LegalDao(OracleConnection cnx) {
        cn = cnx;
    }
    
    
     @Override
    public Integer insertDemanda(LegalTchn oLegalTchn) {
        //System.out.println(" <i> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());

        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        Integer newID = 0;
        try {
       
            // name of procedure
            String sp = "{call PKG_LEGAL.SP_INSERTAR_LEG_TCHN(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameter
            
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = INSParameters_legal(oLegalTchn);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("segur");
            // execute procedure
            // execute procedure
            
            runSP(oList, cn, cmd, sp);
            newID = getOutputParameter("PO_I_PROCESO").getParameterInt();
        } catch (Exception e) {
            Logger.getLogger(LegDemanTchn.class.getName()).log(Level.SEVERE, null, e);
            newID=0;
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
        //System.out.println(" <f> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        return newID;
    }
    
    
     @Override
    public Integer insertDemandaMODY(LegalTchn oLegalTchn) {
        //System.out.println(" <i> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());

        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        Integer newID = 0;
        try {
       
            // name of procedure
            String sp = "{call PKG_LEGAL.SP_INSERTAR_LEG_MODY(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameter
            
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = INSParameters_legal(oLegalTchn);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("segur");
            // execute procedure
            // execute procedure
            
            runSP(oList, cn, cmd, sp);
            newID = getOutputParameter("PO_I_PROCESO").getParameterInt();
        } catch (Exception e) {
            Logger.getLogger(LegDemanTchn.class.getName()).log(Level.SEVERE, null, e);
            newID=0;
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
        //System.out.println(" <f> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        return newID;
    }
    
    
      @Override
    public Integer insertSegDemanda(LegSeguiTchn olegSeguiTchn) {
        
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        Integer newID = 0;
        try {
         
            
           
            // name of procedure
            String sp = "{call PKG_LEGAL.SP_INSERTAR_SEG_TCHN(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameter
            
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = INSParameters_Seglegal(olegSeguiTchn);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("segur");
            // execute procedure
            // execute procedure
            runSP(oList, cn, cmd, sp);
            newID = getOutputParameter("PO_I_PROCESO").getParameterInt();
           
        } catch (Exception e) {
            Logger.getLogger(LegDemanTchn.class.getName()).log(Level.SEVERE, null, e);
            newID=0;
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
        //System.out.println(" <f> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        return newID;
    }
    
    
    @Override
    public Integer insertSegDemandaMODY(LegSeguiTchn olegSeguiTchn) {
        System.out.println(olegSeguiTchn.getLs_tipoEtapa() + "-" + olegSeguiTchn.getLsCodEst() + "-" + olegSeguiTchn.getLsDescrip() );
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        Integer newID = 0;
        try {
         
            
           
            // name of procedure
            String sp = "{call PKG_LEGAL.SP_INSERTAR_SEG_TCHN_MODY(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameter
            
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = INSParameters_Seglegal(olegSeguiTchn);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("segur");
            // execute procedure
            // execute procedure
            runSP(oList, cn, cmd, sp);
            newID = getOutputParameter("PO_I_PROCESO").getParameterInt();
           
        } catch (Exception e) {
            Logger.getLogger(LegDemanTchn.class.getName()).log(Level.SEVERE, null, e);
            newID=0;
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
        //System.out.println(" <f> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        return newID;
    }
    
    
    @Override
    public Integer eliminarMODY(LegSeguiTchn olegSeguiTchn) {
        System.out.println(olegSeguiTchn.getLs_tipoEtapa() + "-" + olegSeguiTchn.getLsCodEst() + "-" + olegSeguiTchn.getLsDescrip() + "-" + olegSeguiTchn.getLsIdSegui() );
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        Integer newID = 0;
        try {
         
            
           
            // name of procedure
            String sp = "{call PKG_LEGAL.SP_ELIMINAR_SEG_TCHN_MODY(?,?,?,?,?,?)}";
            // list of parameter
            
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = delParameters_Seglegal(olegSeguiTchn);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("segur");
            // execute procedure
            // execute procedure
            runSP(oList, cn, cmd, sp);
            newID = getOutputParameter("PO_I_PROCESO").getParameterInt();
           
        } catch (Exception e) {
            Logger.getLogger(LegDemanTchn.class.getName()).log(Level.SEVERE, null, e);
            newID=0;
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
        //System.out.println(" <f> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        return newID;
    }
    
    
     @Override
    public ArrayList<LegalTchn> fetchAllTLegal(LegalTchn oLegalTchn) {
        //System.out.println(" <i> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        ArrayList<LegalTchn> lstTchn = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_LEGAL.SP_LISTA_EST_TCHN(?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters_legal(oLegalTchn);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("segur");
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                //fondo
                MaeFondo newFondo = new MaeFondo();
                newFondo.setCFondoId(resultSet.getString("C_FONDO_ID"));
                newFondo.setDFondo(resultSet.getString("D_FONDO"));
                //inversion
                MaeInversion newInversion = new MaeInversion();
                newInversion.setCInversion(resultSet.getString("C_INVERSION"));
                newInversion.setcMaeInversionId(Integer.parseInt(resultSet.getString("C_MAE_INVERSION_ID")));
                newInversion.setcTipoInv(resultSet.getString("C_TIPO_INV"));
                newInversion.setMaeFondo(newFondo);
                //persona
                MaePersona newPersona = new MaePersona();
                newPersona.setMaeFondo(newFondo);
                newPersona.setCPersonaId(Integer.parseInt(resultSet.getString("C_PERSONA_ID")));
                newPersona.setANroDocumento(resultSet.getString("A_NRO_DOCUMENTO"));
                newPersona.setDApePat(resultSet.getString("D_APE_PAT"));
                newPersona.setDApeMat(resultSet.getString("D_APE_MAT"));
                newPersona.setDNombres(resultSet.getString("D_NOMBRES"));
                //newPersona.setCTipoDocumento(resultSet.getString("D_DESC_CORTA"));

                newInversion.setcPersonaId(newPersona);

                //tchn
                LegalTchn newLegTchn = new LegalTchn();
                newLegTchn.setFondo(newFondo);
                newLegTchn.setMaeInversion(newInversion);
                newLegTchn.setNroCuotasAtrasadas(resultSet.getInt("NROCUOTAS_ATRAS"));
                newLegTchn.setEstado(resultSet.getString("ESTADO"));
                newLegTchn.setEtapa(resultSet.getString("ETAPA"));
                newLegTchn.setDIR1(resultSet.getString("D_DIR1"));
                newLegTchn.setIdestado(resultSet.getString("idestado"));
                newLegTchn.setIdetapa(resultSet.getString("IDETAPA"));
                newLegTchn.setNvaloriza(resultSet.getDouble("VALORIZA"));
                newLegTchn.setLnDiastra(resultSet.getInt("DIAS_TRASA"));
                newLegTchn.setLdFechaTasa(resultSet.getDate("FTASA"));
                newLegTchn.setLsIndicador(resultSet.getString("INDICADOR"));
                newLegTchn.setFcerrar(resultSet.getString("CERRAR"));
                newLegTchn.setFconclusion(resultSet.getString("CONCLUIR"));
                newLegTchn.setMoneda(resultSet.getString("MONEDA"));
                if (resultSet.getString("MONEDA").contains("SOLES")){
                      newLegTchn.setSimbolo("S/.");
                }else{
                   newLegTchn.setSimbolo("$.");
                }
                if (resultSet.getString("ETAPA").equals("Cobranza")){
                   newLegTchn.setFcerrar("S");
                   newLegTchn.setEstado("");
                   newLegTchn.setIdestado("");
                   newLegTchn.setLnDiastra(0);
                   newLegTchn.setLsIndicador("");
                }
                
                 if (resultSet.getInt("FLAGHABI")==1){
                    System.out.println("FLAGHABI");
                    newLegTchn.setFmostrar(true);
                    newLegTchn.setFmodificar(true);
                    newLegTchn.setFnuevo(false);
                    if (resultSet.getString("CERRAR").equals("S")  ){
                        System.out.println("CERRAR"+newLegTchn.getFconclusion()+ "  "+ newLegTchn.getFcerrar());
                        newLegTchn.setFmostrar(true);
                        newLegTchn.setFmodificar(false);
                        newLegTchn.setFnuevo(true);
                        newLegTchn.setEstado("");
                        newLegTchn.setIdestado("");
                        newLegTchn.setLnDiastra(0);
                        newLegTchn.setLsIndicador("");
                        if (newLegTchn.getIdetapa().equals("0616")){
                             newLegTchn.setIdetapa("0617");
                             newLegTchn.setEtapa("Postulatoria");
                             
                        }else if (newLegTchn.getIdetapa().equals("0617")){
                            
                            newLegTchn.setIdetapa("0618");
                            newLegTchn.setEtapa("Probatoria");
                        }else if (newLegTchn.getIdetapa().equals("0618")){
                            
                            newLegTchn.setIdetapa("0619");
                            newLegTchn.setEtapa("Decisoria");
                        }else if (newLegTchn.getIdetapa().equals("0619")){
                            
                            newLegTchn.setIdetapa("0620");
                            newLegTchn.setEtapa("Impugnatoria");
                        }else if (newLegTchn.getIdetapa().equals("0620")){
                            
                            newLegTchn.setIdetapa("0621");
                            newLegTchn.setEtapa("Ejecución");
                        }else if (newLegTchn.getIdetapa().equals("0621")){
                            
                            newLegTchn.setIdetapa("0622");
                            newLegTchn.setEtapa("Final");
                        }   
                    }
                    if ( resultSet.getString("CONCLUIR").equals("S") ){
                        
                        newLegTchn.setFmostrar(true);
                        newLegTchn.setFmodificar(false);
                        if (newLegTchn.getIdetapa().equals("0622") &&  newLegTchn.getIdestado().equals("0004") ){
                            newLegTchn.setFnuevo(false);
                        }else{
                            newLegTchn.setFnuevo(true);
                        }
                        newLegTchn.setIdetapa("0622");
                        newLegTchn.setEtapa("Final");
                        
                    }
                    if ( resultSet.getString("REMATE").equals("S") ){
                        
                        newLegTchn.setFmostrar(true);
                        newLegTchn.setFmodificar(false);
                        if (newLegTchn.getIdetapa().equals("0622") &&  newLegTchn.getIdestado().equals("0004") ){
                            newLegTchn.setFnuevo(false);
                        }else{
                            newLegTchn.setFnuevo(true);
                        }
                        newLegTchn.setIdetapa("0621");
                        newLegTchn.setEtapa("Ejecucion");
                        
                    }
                     if (newLegTchn.getIdetapa().equals("0622") &&  newLegTchn.getIdestado().equals("0004") ){
                            newLegTchn.setFmostrar(false);
                            newLegTchn.setFmodificar(false);
                            newLegTchn.setFnuevo(false);
                        }
                }else{    
                     if (newLegTchn.getIdetapa().equals("0622") &&  newLegTchn.getIdestado().equals("0004") ){
                            newLegTchn.setFmostrar(false);
                            newLegTchn.setFmodificar(false);
                            newLegTchn.setFnuevo(false);
                        }else{
                            newLegTchn.setFmostrar(true);
                            newLegTchn.setFmodificar(false);
                            newLegTchn.setFnuevo(true);
                        }
                     
                }
                lstTchn.add(newLegTchn);
            }

        } catch (Exception e) {
            Logger.getLogger(MaeDeposito.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        return lstTchn;
    }
    
    
     @Override
    public ArrayList<LegalTchn> fetchAllTLegalMody(LegalTchn oLegalTchn) {
        //System.out.println(" <i> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        ArrayList<LegalTchn> lstTchn = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_LEGAL.SP_LISTA_EST_TCHN_MODY(?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters_legal(oLegalTchn);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("segur");
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                //fondo
                MaeFondo newFondo = new MaeFondo();
                newFondo.setCFondoId(resultSet.getString("C_FONDO_ID"));
                newFondo.setDFondo(resultSet.getString("D_FONDO"));
                //inversion
                MaeInversion newInversion = new MaeInversion();
                newInversion.setCInversion(resultSet.getString("C_INVERSION"));
                newInversion.setcMaeInversionId(Integer.parseInt(resultSet.getString("C_MAE_INVERSION_ID")));
                newInversion.setcTipoInv(resultSet.getString("C_TIPO_INV"));
                newInversion.setMaeFondo(newFondo);
                //persona
                MaePersona newPersona = new MaePersona();
                newPersona.setMaeFondo(newFondo);
                newPersona.setCPersonaId(Integer.parseInt(resultSet.getString("C_PERSONA_ID")));
                newPersona.setANroDocumento(resultSet.getString("A_NRO_DOCUMENTO"));
                newPersona.setDApePat(resultSet.getString("D_APE_PAT"));
                newPersona.setDApeMat(resultSet.getString("D_APE_MAT"));
                newPersona.setDNombres(resultSet.getString("D_NOMBRES"));
                //newPersona.setCTipoDocumento(resultSet.getString("D_DESC_CORTA"));

                newInversion.setcPersonaId(newPersona);

                //tchn
                LegalTchn newLegTchn = new LegalTchn();
                newLegTchn.setFondo(newFondo);
                newLegTchn.setMaeInversion(newInversion);
             
                newLegTchn.setEstado(resultSet.getString("ESTADO"));
                newLegTchn.setEtapa(resultSet.getString("ETAPA"));
                newLegTchn.setDIR1(resultSet.getString("D_DIR1"));
                newLegTchn.setIdestado(resultSet.getString("idestado"));
                newLegTchn.setIdetapa(resultSet.getString("IDETAPA"));
                newLegTchn.setNvaloriza(resultSet.getDouble("VALORIZA"));
                newLegTchn.setLnDiastra(resultSet.getInt("DIAS_TRASA"));
                newLegTchn.setLdFechaTasa(resultSet.getDate("FTASA"));


                
                lstTchn.add(newLegTchn);
            }

        } catch (Exception e) {
            Logger.getLogger(MaeDeposito.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        return lstTchn;
    }
    
    @Override
    public ArrayList<LegalTchn> fetchAllTLegalRepo(LegalTchn oLegalTchn) {
        //System.out.println(" <i> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        ArrayList<LegalTchn> lstTchn = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_LEGAL.SP_LISTA_EST_TCHN(?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters_legal(oLegalTchn);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("segur");
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                //fondo
                MaeFondo newFondo = new MaeFondo();
                newFondo.setCFondoId(resultSet.getString("C_FONDO_ID"));
                newFondo.setDFondo(resultSet.getString("D_FONDO"));
                //inversion
                MaeInversion newInversion = new MaeInversion();
                newInversion.setCInversion(resultSet.getString("C_INVERSION"));
                newInversion.setcMaeInversionId(Integer.parseInt(resultSet.getString("C_MAE_INVERSION_ID")));
                newInversion.setcTipoInv(resultSet.getString("C_TIPO_INV"));
                newInversion.setMaeFondo(newFondo);
                //persona
                MaePersona newPersona = new MaePersona();
                newPersona.setMaeFondo(newFondo);
                newPersona.setCPersonaId(Integer.parseInt(resultSet.getString("C_PERSONA_ID")));
                newPersona.setANroDocumento(resultSet.getString("A_NRO_DOCUMENTO"));
                newPersona.setDApePat(resultSet.getString("D_APE_PAT"));
                newPersona.setDApeMat(resultSet.getString("D_APE_MAT"));
                newPersona.setDNombres(resultSet.getString("D_NOMBRES"));
                //newPersona.setCTipoDocumento(resultSet.getString("D_DESC_CORTA"));

                newInversion.setcPersonaId(newPersona);

                //tchn
                LegalTchn newLegTchn = new LegalTchn();
                newLegTchn.setFondo(newFondo);
                newLegTchn.setMaeInversion(newInversion);
                newLegTchn.setNroCuotasAtrasadas(resultSet.getInt("NROCUOTAS_ATRAS"));
                newLegTchn.setEstado(resultSet.getString("ESTADO"));
                newLegTchn.setEtapa(resultSet.getString("ETAPA"));
                newLegTchn.setDIR1(resultSet.getString("D_DIR1"));
                newLegTchn.setIdestado(resultSet.getString("idestado"));
                newLegTchn.setIdetapa(resultSet.getString("IDETAPA"));
                newLegTchn.setNvaloriza(resultSet.getDouble("VALORIZA"));
                newLegTchn.setLnDiastra(resultSet.getInt("DIAS_TRASA"));
                newLegTchn.setLdFechaTasa(resultSet.getDate("FTASA"));
                newLegTchn.setLsIndicador(resultSet.getString("INDICADOR"));
                newLegTchn.setFcerrar(resultSet.getString("CERRAR"));
                newLegTchn.setFconclusion(resultSet.getString("CONCLUIR"));
                if (resultSet.getString("ETAPA").equals("Cobranza")){
                   newLegTchn.setFcerrar("S");
                   newLegTchn.setEstado("");
                   newLegTchn.setIdestado("");
                   newLegTchn.setLnDiastra(0);
                   newLegTchn.setLsIndicador("");
                }
                
                 if (resultSet.getInt("FLAGHABI")==1){
                    newLegTchn.setFmostrar(true);
                    if (resultSet.getString("CERRAR").equals("S")  ){
                        newLegTchn.setFmostrar(true);
                        
                        newLegTchn.setEstado("");
                        newLegTchn.setIdestado("");
                        newLegTchn.setLnDiastra(0);
                        newLegTchn.setLsIndicador("");
                        if (newLegTchn.getIdetapa().equals("0616")){
                             newLegTchn.setIdetapa("0617");
                             newLegTchn.setEtapa("Postulatoria");
                             
                        }else if (newLegTchn.getIdetapa().equals("0617")){
                            newLegTchn.setIdetapa("0618");
                            newLegTchn.setEtapa("Probatoria");
                        }else if (newLegTchn.getIdetapa().equals("0618")){
                            newLegTchn.setIdetapa("0619");
                            newLegTchn.setEtapa("Decisoria");
                        }else if (newLegTchn.getIdetapa().equals("0619")){
                            newLegTchn.setIdetapa("0620");
                            newLegTchn.setEtapa("Impugnatoria");
                        }else if (newLegTchn.getIdetapa().equals("0620")){
                            newLegTchn.setIdetapa("0621");
                            newLegTchn.setEtapa("Ejecución");
                        }else if (newLegTchn.getIdetapa().equals("0621")){
                            newLegTchn.setIdetapa("0622");
                            newLegTchn.setEtapa("Final");
                        }   
                    }
                     if ( resultSet.getString("CONCLUIR").equals("S") ){
                        newLegTchn.setFmostrar(true);
                        newLegTchn.setIdetapa("0622");
                        newLegTchn.setEtapa("Final");
                        
                    }
                      if ( resultSet.getString("REMATE").equals("S") ){
                        
                        newLegTchn.setFmostrar(true);
                        newLegTchn.setFmodificar(false);
                        newLegTchn.setFnuevo(true);
                        newLegTchn.setIdetapa("0621");
                        newLegTchn.setEtapa("Ejecucion");
                        
                    }
                }else{    
                        newLegTchn.setFmostrar(true);
                }
                lstTchn.add(newLegTchn);
            }

        } catch (Exception e) {
            Logger.getLogger(MaeDeposito.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        return lstTchn;
    }
    
    @Override
    public ArrayList<MaeEstadoLegal> fetchEstadoAllTLegal(String idEtapa) {
        //System.out.println(" <i> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        ArrayList<MaeEstadoLegal> lstTchn = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_LEGAL.SP_ESTADOS_SEGUIMIENTO(?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            
            oList = listParameters_Estado(idEtapa);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("segur");
            // execute procedure
            
            runSearch(oList, cn, cmd, sp);
            
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                //fondo
            
                //tchn
                MaeEstadoLegal newmaeEstadoLEgal = new MaeEstadoLegal();
               
                newmaeEstadoLEgal.setCodigo(resultSet.getString("CARGUM"));
                newmaeEstadoLEgal.setEstado(resultSet.getString("DDESCRIPCION"));
                
                lstTchn.add(newmaeEstadoLEgal);
            }

        } catch (Exception e) {
            Logger.getLogger(MaeDeposito.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
         
        return lstTchn;
    }
    
    
      @Override
    public ArrayList<MaeEstadoLegal> fetchEstadoAllTLegalMODY() {
        //System.out.println(" <i> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        ArrayList<MaeEstadoLegal> lstTchn = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_LEGAL.SP_LISTA_ETAPAS_MODY(?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            
            oList = listParameters_EstadoMODY();
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("segur");
            // execute procedure
            
            runSearch(oList, cn, cmd, sp);
            
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                //fondo
            
                //tchn
                MaeEstadoLegal newmaeEstadoLEgal = new MaeEstadoLegal();
               
                newmaeEstadoLEgal.setCodigo(resultSet.getString("CTABLA"));
                newmaeEstadoLEgal.setEstado(resultSet.getString("DDESCRIPCION"));
                 
                lstTchn.add(newmaeEstadoLEgal);
            }

        } catch (Exception e) {
            Logger.getLogger(MaeDeposito.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
         
        return lstTchn;
    }
    
    
    
    @Override
    public ArrayList<MaeEstadoLegal> fetchEspecialAllTLegal(String codigo,String codjuz  ) {
        //System.out.println(" <i> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        ArrayList<MaeEstadoLegal> lstTchn = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_LEGAL.SP_LISTAR_ESPECIALISTA(?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            
            oList = listParameters_Espe(codigo,codjuz);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("segur");
            // execute procedure
            
            runSearch(oList, cn, cmd, sp);
            
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                 //tchn
                MaeEstadoLegal newmaeEstadoLEgal = new MaeEstadoLegal();
               
                newmaeEstadoLEgal.setCodigo(resultSet.getString("CARGUM"));
                newmaeEstadoLEgal.setEstado(resultSet.getString("DDESCRIPCION"));
                
                lstTchn.add(newmaeEstadoLEgal);
            }


        } catch (Exception e) {
            Logger.getLogger(MaeEstadoLegal.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        return lstTchn;
    }
    
    
    
     @Override
    public ArrayList<LegalTchn> fetchEtapaAllTLegal(LegalTchn oLegalTchn) {
        //System.out.println(" <i> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        ArrayList<LegalTchn> lstTchn = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_LEGAL.SP_LISTA_ETAPA_TCHN(?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            
            oList = listParameters_etapa (oLegalTchn);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("segur");
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                //fondo
                MaeFondo newFondo = new MaeFondo();
                newFondo.setCFondoId(resultSet.getString("C_FONDO_ID"));
                newFondo.setDFondo(resultSet.getString("D_FONDO"));
                //inversion
                MaeInversion newInversion = new MaeInversion();
                newInversion.setCInversion(resultSet.getString("C_INVERSION"));
               
                newInversion.setMaeFondo(newFondo);
                //persona
                MaePersona newPersona = new MaePersona();
                newPersona.setMaeFondo(newFondo);
                newPersona.setCPersonaId(Integer.parseInt(resultSet.getString("C_PERSONA_ID")));
                newPersona.setDApePat(resultSet.getString("D_APE_PAT"));
                newPersona.setDApeMat(resultSet.getString("D_APE_MAT"));
                newPersona.setDNombres(resultSet.getString("D_NOMBRES"));
                //newPersona.setCTipoDocumento(resultSet.getString("D_DESC_CORTA"));

                newInversion.setcPersonaId(newPersona);

                //tchn
                LegalTchn newLegTchn = new LegalTchn();
                newLegTchn.setFondo(newFondo);
                newLegTchn.setMaeInversion(newInversion);
                newLegTchn.setEtapa(resultSet.getString("ETAPA"));
                newLegTchn.setIdetapa(resultSet.getString("TIPOETAPA"));
                newLegTchn.setNvaloriza(resultSet.getDouble("VALORIZA"));
                newLegTchn.setLdFechaTasa(resultSet.getDate("FTASA"));
                newLegTchn.setLdFecha(resultSet.getDate("FECHA")); 
                newLegTchn.setLdFechaAutomisor(resultSet.getDate("FECHAAUTOMISOR")); 
                newLegTchn.setLnCapital(resultSet.getDouble("MONTO_CAPITAL"));
                newLegTchn.setLnInteres(resultSet.getDouble("MONTO_INTERES"));
                newLegTchn.setLnMora(resultSet.getDouble("MONTO_MORA"));
                newLegTchn.setLnTotal(resultSet.getDouble("MONTO"));
                newLegTchn.setDdescripcion(resultSet.getString("DESCRIPCION"));
                newLegTchn.setLsCodJuz(resultSet.getString("CODJUZ"));
                newLegTchn.setLsCodEsp(resultSet.getString("CODESP"));
                newLegTchn.setLsNroExp(resultSet.getString("NROEXP"));
                newLegTchn.setLnmontoAdm(resultSet.getDouble("MONTO_ADMITIDO"));
                newLegTchn.setLsNomJuz(resultSet.getString("NOMJUZ"));
                newLegTchn.setLsNomEsp(resultSet.getString("NOMESPE"));
                newLegTchn.setFmostrar(false);
                newLegTchn.setFnuevo(false);
                newLegTchn.setFmodificar(true);
                newLegTchn.setcUsuarioAdd(resultSet.getString("C_USUARIO_ADD"));
                newLegTchn.setMoneda(resultSet.getString("MONEDA"));
                if (resultSet.getString("MONEDA").contains("SOLES")){
                    newLegTchn.setSimbolo("S/.");
                }else{
                    newLegTchn.setSimbolo("$.");
                }
                lstTchn.add(newLegTchn);
            }

        } catch (Exception e) {
            Logger.getLogger(MaeDeposito.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        return lstTchn;
    }
    
    
    
     @Override
    public ArrayList<LegalTchn> fetchEtapaAllTMODY(LegalTchn oLegalTchn) {
        //System.out.println(" <i> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        ArrayList<LegalTchn> lstTchn = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_LEGAL.SP_LISTA_ETAPA_TCHN_MODY(?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            
            oList = listParameters_etapa (oLegalTchn);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("segur");
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                //fondo
                MaeFondo newFondo = new MaeFondo();
                newFondo.setCFondoId(resultSet.getString("C_FONDO_ID"));
                newFondo.setDFondo(resultSet.getString("D_FONDO"));
                //inversion
                MaeInversion newInversion = new MaeInversion();
                newInversion.setCInversion(resultSet.getString("C_INVERSION"));
               
                newInversion.setMaeFondo(newFondo);
                //persona
                MaePersona newPersona = new MaePersona();
                newPersona.setMaeFondo(newFondo);
                newPersona.setCPersonaId(Integer.parseInt(resultSet.getString("C_PERSONA_ID")));
                newPersona.setDApePat(resultSet.getString("D_APE_PAT"));
                newPersona.setDApeMat(resultSet.getString("D_APE_MAT"));
                newPersona.setDNombres(resultSet.getString("D_NOMBRES"));
                //newPersona.setCTipoDocumento(resultSet.getString("D_DESC_CORTA"));

                newInversion.setcPersonaId(newPersona);

                //tchn
                LegalTchn newLegTchn = new LegalTchn();
                newLegTchn.setFondo(newFondo);
                newLegTchn.setMaeInversion(newInversion);
                newLegTchn.setEtapa(resultSet.getString("ETAPA"));
                newLegTchn.setIdetapa(resultSet.getString("TIPOETAPA"));
                newLegTchn.setNvaloriza(resultSet.getDouble("VALORIZA"));
                newLegTchn.setLdFechaTasa(resultSet.getDate("FTASA"));
                newLegTchn.setLdFecha(resultSet.getDate("FECHA")); 
                newLegTchn.setLdFechaAutomisor(resultSet.getDate("FECHAAUTOMISOR")); 
                newLegTchn.setLnCapital(resultSet.getDouble("MONTO_CAPITAL"));
                newLegTchn.setLnInteres(resultSet.getDouble("MONTO_INTERES"));
                newLegTchn.setLnMora(resultSet.getDouble("MONTO_MORA"));
                newLegTchn.setLnTotal(resultSet.getDouble("MONTO"));
                newLegTchn.setDdescripcion(resultSet.getString("DESCRIPCION"));
                newLegTchn.setLsCodJuz(resultSet.getString("CODJUZ"));
                newLegTchn.setLsCodEsp(resultSet.getString("CODESP"));
                newLegTchn.setLsNroExp(resultSet.getString("NROEXP"));
                newLegTchn.setLnmontoAdm(resultSet.getDouble("MONTO_ADMITIDO"));
                newLegTchn.setLsNomJuz(resultSet.getString("NOMJUZ"));
                newLegTchn.setLsNomEsp(resultSet.getString("NOMESPE"));
                newLegTchn.setFmostrar(false);
                newLegTchn.setFnuevo(false);
                newLegTchn.setFmodificar(true);
                newLegTchn.setcUsuarioAdd(resultSet.getString("C_USUARIO_ADD"));
                lstTchn.add(newLegTchn);
            }

        } catch (Exception e) {
            Logger.getLogger(MaeDeposito.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        return lstTchn;
    }
    
    @Override
    public ArrayList<LegSeguiTchn> fetchSeguiAllTLegal(LegSeguiTchn olegSeguiTchn) {
        //System.out.println(" <i> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        ArrayList<LegSeguiTchn> lstTchn = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_LEGAL.SP_LISTAR_SEGUIM_TCHN(?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            
            oList = listParameters_segui (olegSeguiTchn);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("segur");
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                //fondo
                MaeFondo newFondo = new MaeFondo();
                newFondo.setCFondoId(resultSet.getString("FONDO"));
                
                //inversion
                MaeInversion newInversion = new MaeInversion();
                newInversion.setCInversion(resultSet.getString("CODIGOTCHN"));
               
                //tchn
                LegSeguiTchn newlegSeguiTchn=new LegSeguiTchn();
                
                newlegSeguiTchn.setFondo(newFondo);
                newlegSeguiTchn.setMaeInversion(newInversion);
                newlegSeguiTchn.setLs_tipoEtapa(resultSet.getString("TIPOETAPA"));
                newlegSeguiTchn.setLsCodEst(resultSet.getString("ESTADO"));
                newlegSeguiTchn.setLsEstado(resultSet.getString("DESESTADO"));
                
                newlegSeguiTchn.setLd_fecha(resultSet.getDate("FECHA"));
                newlegSeguiTchn.setLsIdSegui(resultSet.getInt("NSEGUI"));
                newlegSeguiTchn.setcUsuarioMod(resultSet.getString("C_USUARIO_MOD"));
                newlegSeguiTchn.setLsDescrip(resultSet.getString("DESCRIPCION"));
                newlegSeguiTchn.setLsDestipoEtapa(resultSet.getString("DESTIPOETAPA"));
                newlegSeguiTchn.setLnCorrelativo(resultSet.getInt("NUMERO_SEG"));
                newlegSeguiTchn.setFconcluir(resultSet.getString("CONCLUIR"));
                newlegSeguiTchn.setLn_dias(resultSet.getInt("N_DIAS"));
                newlegSeguiTchn.setLdFechaval(resultSet.getDate("FECHA_VAL"));
                newlegSeguiTchn.setLnMontoval(resultSet.getDouble("MONTO_VAL"));
                newlegSeguiTchn.setLnMontodolval(resultSet.getDouble("MONTODOL_VAL"));
                newlegSeguiTchn.setLnInteres(resultSet.getDouble("INTERES"));
                newlegSeguiTchn.setLnIntMora(resultSet.getDouble("MORA"));
                newlegSeguiTchn.setLnCostas(resultSet.getDouble("COSTAS"));
                newlegSeguiTchn.setLnCostos(resultSet.getDouble("COSTOS"));
                newlegSeguiTchn.setcUsuarioAdd(resultSet.getString("C_USUARIO_ADD")); 
                if (resultSet.getString("CONCLUIR")=="N"){
                    newlegSeguiTchn.setCconcluir(false);
                }else{
                    newlegSeguiTchn.setCconcluir(true);
                }
                
                if (resultSet.getString("FREMATE")=="N"){
                    newlegSeguiTchn.setCremate(false);
                }else{
                    newlegSeguiTchn.setCremate(true);
                }
                
                newlegSeguiTchn.setFmostrar(true);
                newlegSeguiTchn.setFnuevo(false);
                newlegSeguiTchn.setFmodificar(true);
                
                lstTchn.add(newlegSeguiTchn);
            }

        } catch (Exception e) {
            Logger.getLogger(MaeDeposito.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        return lstTchn;
    }
    
    
    @Override
    public ArrayList<LegSeguiTchn> fetchSeguiAllTLegalMODY(LegSeguiTchn olegSeguiTchn) {
        //System.out.println(" <i> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        ArrayList<LegSeguiTchn> lstTchn = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_LEGAL.SP_LISTAR_SEGUIM_TCHN_MODY(?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            
            oList = listParameters_segui (olegSeguiTchn);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("segur");
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                //fondo
                MaeFondo newFondo = new MaeFondo();
                newFondo.setCFondoId(resultSet.getString("FONDO"));
                
                //inversion
                MaeInversion newInversion = new MaeInversion();
                newInversion.setCInversion(resultSet.getString("CODIGOTCHN"));
               
                //tchn
                LegSeguiTchn newlegSeguiTchn=new LegSeguiTchn();
                
                newlegSeguiTchn.setFondo(newFondo);
                newlegSeguiTchn.setMaeInversion(newInversion);
                newlegSeguiTchn.setLs_tipoEtapa(resultSet.getString("TIPOETAPA"));
                newlegSeguiTchn.setLsCodEst(resultSet.getString("ESTADO"));
                newlegSeguiTchn.setLsEstado(resultSet.getString("DESESTADO"));
                
                newlegSeguiTchn.setLd_fecha(resultSet.getDate("FECHA"));
                newlegSeguiTchn.setLsIdSegui(resultSet.getInt("NSEGUI"));
                newlegSeguiTchn.setcUsuarioMod(resultSet.getString("C_USUARIO_MOD"));
                newlegSeguiTchn.setLsDescrip(resultSet.getString("DESCRIPCION"));
                newlegSeguiTchn.setLsDestipoEtapa(resultSet.getString("DESTIPOETAPA"));
                newlegSeguiTchn.setLnCorrelativo(resultSet.getInt("NUMERO_SEG"));
                newlegSeguiTchn.setFconcluir(resultSet.getString("CONCLUIR"));
                newlegSeguiTchn.setLn_dias(resultSet.getInt("N_DIAS"));
                newlegSeguiTchn.setLdFechaval(resultSet.getDate("FECHA_VAL"));
                newlegSeguiTchn.setLnMontoval(resultSet.getDouble("MONTO_VAL"));
                newlegSeguiTchn.setLnMontodolval(resultSet.getDouble("MONTODOL_VAL"));
                newlegSeguiTchn.setLnInteres(resultSet.getDouble("INTERES"));
                newlegSeguiTchn.setLnIntMora(resultSet.getDouble("MORA"));
                newlegSeguiTchn.setLnCostas(resultSet.getDouble("COSTAS"));
                newlegSeguiTchn.setLnCostos(resultSet.getDouble("COSTOS"));
                newlegSeguiTchn.setcUsuarioAdd(resultSet.getString("C_USUARIO_ADD")); 
                if (resultSet.getString("CONCLUIR")=="N"){
                    newlegSeguiTchn.setCconcluir(false);
                }else{
                    newlegSeguiTchn.setCconcluir(true);
                }
                
                if (resultSet.getString("FREMATE")=="N"){
                    newlegSeguiTchn.setCremate(false);
                }else{
                    newlegSeguiTchn.setCremate(true);
                }
                
                newlegSeguiTchn.setFmostrar(true);
                newlegSeguiTchn.setFnuevo(false);
                newlegSeguiTchn.setFmodificar(true);
                
                lstTchn.add(newlegSeguiTchn);
            }

        } catch (Exception e) {
            Logger.getLogger(MaeDeposito.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        return lstTchn;
    }
    
    
    @Override
    public ArrayList<LegSeguiTchn> fetchModificarSeguiAllTLegal(LegSeguiTchn olegSeguiTchn) {
         
        ArrayList<LegSeguiTchn> lstTchn = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_LEGAL.SP_LISTAR_ESTADO_SEGUIM_TCHN(?,?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
           
            oList = listParameters_est_segui (olegSeguiTchn);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("segur");
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                //fondo
                MaeFondo newFondo = new MaeFondo();
                newFondo.setCFondoId(resultSet.getString("FONDO"));
                
                //inversion
                MaeInversion newInversion = new MaeInversion();
                newInversion.setCInversion(resultSet.getString("CODIGOTCHN"));
               
                //tchn
                LegSeguiTchn newlegSeguiTchn=new LegSeguiTchn();
                
                newlegSeguiTchn.setFondo(newFondo);
                newlegSeguiTchn.setMaeInversion(newInversion);
                newlegSeguiTchn.setLs_tipoEtapa(resultSet.getString("TIPOETAPA"));
                newlegSeguiTchn.setLsCodEst(resultSet.getString("ESTADO"));
                newlegSeguiTchn.setLsEstado(resultSet.getString("DESESTADO"));
                
                newlegSeguiTchn.setLd_fecha(resultSet.getDate("FECHA"));
                newlegSeguiTchn.setLsIdSegui(resultSet.getInt("NUMERO_SEG"));
                newlegSeguiTchn.setcUsuarioMod(resultSet.getString("C_USUARIO_MOD"));
                newlegSeguiTchn.setLsDescrip(resultSet.getString("DESCRIPCION"));
                newlegSeguiTchn.setLsDestipoEtapa(resultSet.getString("DESTIPOETAPA"));
                newlegSeguiTchn.setLnCorrelativo(resultSet.getInt("NUMERO_SEG"));
                newlegSeguiTchn.setFconcluir(resultSet.getString("CONCLUIR") );
                newlegSeguiTchn.setFremate(resultSet.getString("FREMATE"));
                newlegSeguiTchn.setLdFechaval(resultSet.getDate("FECHA_VAL"));
                newlegSeguiTchn.setLnMontoval(resultSet.getDouble("MONTO_VAL"));
                newlegSeguiTchn.setLnMontodolval(resultSet.getDouble("MONTODOL_VAL"));
                newlegSeguiTchn.setLnInteres(resultSet.getDouble("INTERES"));
                newlegSeguiTchn.setLnIntMora(resultSet.getDouble("MORA"));
                
                newlegSeguiTchn.setLnCostas(resultSet.getDouble("COSTAS"));
                newlegSeguiTchn.setLnCostos(resultSet.getDouble("COSTOS"));
                newlegSeguiTchn.setcUsuarioAdd(resultSet.getString("C_USUARIO_ADD")); 
                newlegSeguiTchn.setMoneda(resultSet.getString("MONEDA"));
                if (resultSet.getString("MONEDA").contains("SOLES")){
                    newlegSeguiTchn.setSimbolo("S/.");
                }else{
                    newlegSeguiTchn.setSimbolo("$.");
                }
                
                if (newlegSeguiTchn.getFconcluir().contains("N")){
                    newlegSeguiTchn.setCconcluir(false);
                      
                }else{
                    newlegSeguiTchn.setCconcluir(true);
                    
                }
                 if (newlegSeguiTchn.getFremate().contains("N")){
                    newlegSeguiTchn.setCremate(false);
                      
                }else{
                    newlegSeguiTchn.setCremate(true);
                    
                }
                newlegSeguiTchn.setFmostrar(true);
                newlegSeguiTchn.setFnuevo(false);
                newlegSeguiTchn.setFmodificar(true);
                lstTchn.add(newlegSeguiTchn);
            }

        } catch (Exception e) {
            Logger.getLogger(MaeDeposito.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        return lstTchn;
    }
    
    
    
    @Override
    public ArrayList<LegSeguiTchn> fetchModificarSeguiAllTMODY(LegSeguiTchn olegSeguiTchn) {
           
        ArrayList<LegSeguiTchn> lstTchn = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_LEGAL.SP_LISTAR_ESTADO_SEGUIM_MODY(?,?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
           
            oList = listParameters_est_segui (olegSeguiTchn);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("segur");
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                //fondo
                MaeFondo newFondo = new MaeFondo();
                newFondo.setCFondoId(resultSet.getString("FONDO"));
                
                //inversion
                MaeInversion newInversion = new MaeInversion();
                newInversion.setCInversion(resultSet.getString("CODIGOTCHN"));
               
                //tchn
                LegSeguiTchn newlegSeguiTchn=new LegSeguiTchn();
                
                newlegSeguiTchn.setFondo(newFondo);
                newlegSeguiTchn.setMaeInversion(newInversion);
                newlegSeguiTchn.setLs_tipoEtapa(resultSet.getString("TIPOETAPA"));
                newlegSeguiTchn.setLsCodEst(resultSet.getString("ESTADO"));
                newlegSeguiTchn.setLsEstado(resultSet.getString("DESESTADO"));
                
                newlegSeguiTchn.setLd_fecha(resultSet.getDate("FECHA"));
                newlegSeguiTchn.setLsIdSegui(resultSet.getInt("NUMERO_SEG"));
                newlegSeguiTchn.setcUsuarioMod(resultSet.getString("C_USUARIO_MOD"));
                newlegSeguiTchn.setLsDescrip(resultSet.getString("DESCRIPCION"));
                newlegSeguiTchn.setLsDestipoEtapa(resultSet.getString("DESTIPOETAPA"));
                newlegSeguiTchn.setLnCorrelativo(resultSet.getInt("NUMERO_SEG"));
                newlegSeguiTchn.setFconcluir(resultSet.getString("CONCLUIR") );
                newlegSeguiTchn.setFremate(resultSet.getString("FREMATE"));
                newlegSeguiTchn.setLdFechaval(resultSet.getDate("FECHA_VAL"));
                newlegSeguiTchn.setLnMontoval(resultSet.getDouble("MONTO_VAL"));
                newlegSeguiTchn.setLnMontodolval(resultSet.getDouble("MONTODOL_VAL"));
                newlegSeguiTchn.setLnInteres(resultSet.getDouble("INTERES"));
                newlegSeguiTchn.setLnIntMora(resultSet.getDouble("MORA"));
                newlegSeguiTchn.setLnCostas(resultSet.getDouble("COSTAS"));
                newlegSeguiTchn.setLnCostos(resultSet.getDouble("COSTOS"));
                newlegSeguiTchn.setcUsuarioAdd(resultSet.getString("C_USUARIO_ADD")); 
                if (newlegSeguiTchn.getFconcluir().contains("N")){
                    newlegSeguiTchn.setCconcluir(false);
                      
                }else{
                    newlegSeguiTchn.setCconcluir(true);
                    
                }
                 if (newlegSeguiTchn.getFremate().contains("N")){
                    newlegSeguiTchn.setCremate(false);
                      
                }else{
                    newlegSeguiTchn.setCremate(true);
                    
                }
                newlegSeguiTchn.setFmostrar(true);
                newlegSeguiTchn.setFnuevo(false);
                newlegSeguiTchn.setFmodificar(true);
                lstTchn.add(newlegSeguiTchn);
            }

        } catch (Exception e) {
            Logger.getLogger(MaeDeposito.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        return lstTchn;
    }

    @Override
    public Integer fetchCerrarEtapaSegDemanda(LegSeguiTchn olegSeguiTchn) {
         
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        Integer newID = 0;
        try {
         
            
           
            // name of procedure
            String sp = "{call PKG_LEGAL.SP_CERRAR_ETAPA_TCHN(?,?,?,?,?)}";
            // list of parameter
            
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = Parameters_CerrarEtapalegal(olegSeguiTchn);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("segur");
            // execute procedure
            // execute procedure
            runSP(oList, cn, cmd, sp);
           
        } catch (Exception e) {
            Logger.getLogger(LegDemanTchn.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        return newID;
    }
    
    @Override
    public ArrayList<LegalTchn> listarTchnLegalAsesor(LegalTchn oLegalTchn) {
        //System.out.println(" <i> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        ArrayList<LegalTchn> lstTchn = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_LEGAL.SP_LISTAR_DEMAN_X_ASESOR(?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters_legalAsesor(oLegalTchn);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("segur");
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                //fondo
                MaeFondo newFondo = new MaeFondo();
                newFondo.setCFondoId(resultSet.getString("C_FONDO_ID"));
                newFondo.setDFondo(resultSet.getString("D_FONDO"));
                //inversion
                MaeInversion newInversion = new MaeInversion();
                newInversion.setCInversion(resultSet.getString("CODIGOTCHN"));
                newInversion.setMaeFondo(newFondo);
                //persona
               
                LegalTchn newLegTchn = new LegalTchn();
                newLegTchn.setFondo(newFondo);
                newLegTchn.setMaeInversion(newInversion);
                newLegTchn.setEtapa(resultSet.getString("ETAPA"));
                newLegTchn.setLnDiastra(resultSet.getInt("DIAS_TRASA"));
                newLegTchn.setLsIndicador(resultSet.getString("INDICADOR"));
                newLegTchn.setLdFecha(resultSet.getDate("Fechaultmod"));
                newLegTchn.setLdFechaTasa(resultSet.getDate("FECHAING"));
                newLegTchn.setLsusuario(resultSet.getString("ASESOR"));
                
                 
                lstTchn.add(newLegTchn);
            }

        } catch (Exception e) {
            Logger.getLogger(MaeDeposito.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        return lstTchn;
    }
    
    
    
    
    @Override
    public ArrayList<LegalTchn>lista_Dashboard_etapaxfondo() {
        //System.out.println(" <i> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        ArrayList<LegalTchn> lstTchn = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_LEGAL.Sp_Lista_Dashboard_etapa_fondo(?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters_Dash();
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("segur");
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADOE").getParameterResultSet();

            while (resultSet.next()) {
                //fondo
            
                LegalTchn newLegTchn = new LegalTchn();
                newLegTchn.setIdetapa(resultSet.getString("Tipoetapa"));
                newLegTchn.setEtapa(resultSet.getString("Etapa"));
                newLegTchn.setiCapital(resultSet.getInt("EMPRENDEDOR"));
                newLegTchn.setiPopular(resultSet.getInt("POPULAR"));
                newLegTchn.setiMype(resultSet.getInt("MYPE"));
                newLegTchn.setiPerez(resultSet.getInt("PEREZH"));
                newLegTchn.setiTotalFondo(resultSet.getInt("TOTAL"));
                
                 
                lstTchn.add(newLegTchn);
            }

        } catch (Exception e) {
            Logger.getLogger(MaeDeposito.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        return lstTchn;
    }
    
    
    @Override
    public ArrayList<LegalTchn>lista_Dashboard_etapaDesem() {
        //System.out.println(" <i> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        ArrayList<LegalTchn> lstTchn = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_LEGAL.Sp_Lista_Dashboard_EtapaDesem(?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters_Dash();
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("segur");
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADOE").getParameterResultSet();

            while (resultSet.next()) {
                //fondo
                LegalTchn newLegTchn = new LegalTchn();
                newLegTchn.setIdetapa(resultSet.getString("TIPOETAPA"));
                newLegTchn.setEtapa(resultSet.getString("ETAPA"));
                newLegTchn.setiOptimo(resultSet.getInt("OPTIMO"));
                newLegTchn.setiNormal(resultSet.getInt("NORMAL1"));
                newLegTchn.setiRegular(resultSet.getInt("REGULAR"));
                newLegTchn.setiDeficiente(resultSet.getInt("DEFICIENTE"));
                newLegTchn.setiTotalIndi(resultSet.getInt("TOTAL"));
                lstTchn.add(newLegTchn);
            }

        } catch (Exception e) {
            Logger.getLogger(MaeDeposito.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        return lstTchn;
    }
    
    
    @Override
    public ArrayList<LegalTchn>lista_Dashboard_AseCal() {
        //System.out.println(" <i> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        ArrayList<LegalTchn> lstTchn = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_LEGAL.Sp_Genera_Dashboard_AseCal(?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters_Dash();
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("segur");
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADOE").getParameterResultSet();

            while (resultSet.next()) {
                //fondo
                LegalTchn newLegTchn = new LegalTchn();
                newLegTchn.setLsAsesor(resultSet.getString("ASESOR"));
                newLegTchn.setiCalifi(resultSet.getInt("CALIFICACION"));
                newLegTchn.setiPostula(resultSet.getInt("POSTULATORIO"));
                newLegTchn.setiProba(resultSet.getInt("PROBATORIO"));
                newLegTchn.setiDesiso(resultSet.getInt("DESISORIA"));
                newLegTchn.setiImpug(resultSet.getInt("IMPUGNATORIA"));
                newLegTchn.setiEjecu(resultSet.getInt("EJECUCION"));
                newLegTchn.setiFinal1(resultSet.getInt("FINAL1"));
                newLegTchn.setiTotalAse(resultSet.getInt("TOTAL"));
                lstTchn.add(newLegTchn);
            }

        } catch (Exception e) {
            Logger.getLogger(MaeDeposito.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        return lstTchn;
    }


    @Override
    public ArrayList<LegalTchn>lista_Dashboard_x_asesor() {
        //System.out.println(" <i> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        ArrayList<LegalTchn> lstTchn = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_LEGAL.Sp_Lista_Dashboard_x_asesor(?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters_Dash();
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("segur");
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADOE").getParameterResultSet();

            while (resultSet.next()) {
              
                LegalTchn newLegTchn = new LegalTchn();
                newLegTchn.setLsAsesor(resultSet.getString("ASESOR"));
                newLegTchn.setiOptimo(resultSet.getInt("OPTIMO"));
                newLegTchn.setiNormal(resultSet.getInt("NORMAL1"));
                newLegTchn.setiRegular(resultSet.getInt("REGULAR"));
                newLegTchn.setiDeficiente(resultSet.getInt("DEFICIENTE"));
                newLegTchn.setiTotalIndi(resultSet.getInt("TOTAL"));
                lstTchn.add(newLegTchn);
            }

        } catch (Exception e) {
            Logger.getLogger(MaeDeposito.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        return lstTchn;
    }

    
    
    @Override
    public ArrayList<LegalTchn>lista_Dashboard_fondoDese() {
        //System.out.println(" <i> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        ArrayList<LegalTchn> lstTchn = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_LEGAL.Sp_Lista_Dashboard_fondoDese(?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters_Dash();
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("segur");
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADOE").getParameterResultSet();

            while (resultSet.next()) {
                MaeFondo newFondo = new MaeFondo();
                newFondo.setCFondoId(resultSet.getString("C_Fondo_Id"));
                newFondo.setDFondo(resultSet.getString("D_FONDO"));
                //fondo
                LegalTchn newLegTchn = new LegalTchn();
                newLegTchn.setFondo(newFondo);
                newLegTchn.setiOptimo(resultSet.getInt("OPTIMO"));
                newLegTchn.setiNormal(resultSet.getInt("NORMAL1"));
                newLegTchn.setiRegular(resultSet.getInt("REGULAR"));
                newLegTchn.setiDeficiente(resultSet.getInt("DEFICIENTE"));
                newLegTchn.setiTotalIndi(resultSet.getInt("TOTAL"));
                lstTchn.add(newLegTchn);
            }

        } catch (Exception e) {
            Logger.getLogger(MaeDeposito.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        return lstTchn;
    }

    
    @Override
    public ArrayList<List<Object>> cargarDashboardActivo(String LsUsuario) {
        ArrayList<List<Object>> lstDatos = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
     
        try {
            // name of procedure
            String sp = "{call PKG_LEGAL.Sp_Genera_Dashboard_activo(?,?,?,?,?,? )}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParametersDash(LsUsuario);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("segur");
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            
            List<Object> lstJudi = new ArrayList<>();
            List<Object> lstResul = new ArrayList<>();

            resultSet = getOutputParameter("PO_CURSOR_RESULTADOE").getParameterResultSet();
            // MESES
            while (resultSet.next()) {
                lstResul.add(resultSet.getInt("CANTIDAD"));
            }
            lstDatos.add(lstResul);

            resultSet = getOutputParameter("PO_CURSOR_RESULTADOA").getParameterResultSet();

            // fondo 0001
            while (resultSet.next()) {
                lstJudi.add(resultSet.getInt("CANTIDAD1"));
            }
            lstDatos.add(lstJudi);
            // fondo 0002
            resultSet = getOutputParameter("PO_CURSOR_RESULTADOB").getParameterResultSet();
            List<Object> lstCal = new ArrayList<>();
            while (resultSet.next()) {
                lstCal.add(resultSet.getInt("CANTIDAD2"));
            }
            lstDatos.add(lstCal);
            
        } catch (Exception e) {
            Logger.getLogger(MaeDashboardDao.class.getName()).log(Level.SEVERE, null, e);
        }
        finally 
        {
            if (resultSet != null) {
              try { resultSet.close(); } catch (SQLException e) { ; }
              resultSet = null;
            }
            if (cmd != null) {
              try { cmd.close(); } catch (SQLException e) { ; }
              cmd = null;
            }
            if (cn != null) {
              try { cn.close(); } catch (SQLException e) { ; }
              cn = null;
            }
        } 
        return lstDatos;
    }    
    
    
    @Override
    public ArrayList<List<Object>> cargarDashboardCancelado(String LsUsuario) {
        ArrayList<List<Object>> lstDatos = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
     
        try {
            // name of procedure
            String sp = "{call PKG_LEGAL.Sp_Genera_Dashboard_cancelado(?,?,?,?,?,? )}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParametersDash(LsUsuario);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("segur");
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            
            List<Object> lstJudi = new ArrayList<>();
            List<Object> lstResul = new ArrayList<>();

            resultSet = getOutputParameter("PO_CURSOR_RESULTADOE").getParameterResultSet();
            // MESES
            while (resultSet.next()) {
                lstResul.add(resultSet.getInt("CANTIDAD"));
            }
            lstDatos.add(lstResul);

            resultSet = getOutputParameter("PO_CURSOR_RESULTADOA").getParameterResultSet();

            // fondo 0001
            while (resultSet.next()) {
                lstJudi.add(resultSet.getInt("CANTIDAD1"));
            }
            lstDatos.add(lstJudi);
            // fondo 0002
            resultSet = getOutputParameter("PO_CURSOR_RESULTADOB").getParameterResultSet();
            List<Object> lstCal = new ArrayList<>();
            while (resultSet.next()) {
                lstCal.add(resultSet.getInt("CANTIDAD2"));
            }
            lstDatos.add(lstCal);
            
        } catch (Exception e) {
            Logger.getLogger(MaeDashboardDao.class.getName()).log(Level.SEVERE, null, e);
        }
        finally 
        {
            if (resultSet != null) {
              try { resultSet.close(); } catch (SQLException e) { ; }
              resultSet = null;
            }
            if (cmd != null) {
              try { cmd.close(); } catch (SQLException e) { ; }
              cmd = null;
            }
            if (cn != null) {
              try { cn.close(); } catch (SQLException e) { ; }
              cn = null;
            }
        } 
        return lstDatos;
    }    
    
    
    
    @Override
    public ArrayList<List<Object>> cargarDashboardEtapaxFondo() {
        ArrayList<List<Object>> lstDatos = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
     
        try {
            // name of procedure
            String sp = "{call PKG_LEGAL.Sp_Genera_Dashboard_etap_fdo(?,?,?,?,?,?,? )}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            
            // fill parameters
            oList = listParametersDash_01();
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("segur");
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            
            List<Object> lsteta1 = new ArrayList<>();
            List<Object> lsteta2 = new ArrayList<>();
            List<Object> lsteta3 = new ArrayList<>();
            List<Object> lsteta4 = new ArrayList<>();
            List<Object> lstfondo = new ArrayList<>();

            resultSet = getOutputParameter("PO_CURSOR_RESULTADOA").getParameterResultSet();
            // fondo1
            while (resultSet.next()) {
                lsteta1.add(resultSet.getInt("CANTIDAD"));
            }
            lstDatos.add(lsteta1);

            resultSet = getOutputParameter("PO_CURSOR_RESULTADOB").getParameterResultSet();

            // fondo2
            while (resultSet.next()) {
                lsteta2.add(resultSet.getInt("CANTIDAD"));
            }
            lstDatos.add(lsteta2);
            
            // fondo3
            resultSet = getOutputParameter("PO_CURSOR_RESULTADOC").getParameterResultSet();
            while (resultSet.next()) {
                lsteta3.add(resultSet.getInt("CANTIDAD"));
            }
            lstDatos.add(lsteta3);
            
            // fondo4
            resultSet = getOutputParameter("PO_CURSOR_RESULTADOD").getParameterResultSet();
            while (resultSet.next()) {
                lsteta4.add(resultSet.getInt("CANTIDAD"));
            }
            lstDatos.add(lsteta4);
            
            
             // fondos
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO0").getParameterResultSet();
            while (resultSet.next()) {
               lstfondo.add( "'" + (String) resultSet.getString("Desetapa") + "'");
            }
            lstDatos.add(lstfondo);
            
        } catch (Exception e) {
            Logger.getLogger(MaeDashboardDao.class.getName()).log(Level.SEVERE, null, e);
        }
        finally 
        {
            if (resultSet != null) {
              try { resultSet.close(); } catch (SQLException e) { ; }
              resultSet = null;
            }
            if (cmd != null) {
              try { cmd.close(); } catch (SQLException e) { ; }
              cmd = null;
            }
            if (cn != null) {
              try { cn.close(); } catch (SQLException e) { ; }
              cn = null;
            }
        } 
        return lstDatos;
    }    
    
    
    @Override
    public ArrayList<List<Object>> cargarDashboardEtapaxDesem() {
        ArrayList<List<Object>> lstDatos = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
     
        try {
            // name of procedure
            String sp = "{call PKG_LEGAL.Sp_Genera_Dashboard_EtapaDese(?,?,?,?,?,?,? )}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParametersDash_01();
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("segur");
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            
            List<Object> lsteta1 = new ArrayList<>();
            List<Object> lsteta2 = new ArrayList<>();
            List<Object> lsteta3 = new ArrayList<>();
            List<Object> lsteta4 = new ArrayList<>();
            List<Object> lstCali = new ArrayList<>();

            resultSet = getOutputParameter("PO_CURSOR_RESULTADOA").getParameterResultSet();
            // fondo1
            while (resultSet.next()) {
                lsteta1.add(resultSet.getInt("CANTIDAD"));
            }
            lstDatos.add(lsteta1);

            resultSet = getOutputParameter("PO_CURSOR_RESULTADOB").getParameterResultSet();

            // fondo2
            while (resultSet.next()) {
                lsteta2.add(resultSet.getInt("CANTIDAD"));
            }
            lstDatos.add(lsteta2);
            
            // fondo3
            resultSet = getOutputParameter("PO_CURSOR_RESULTADOC").getParameterResultSet();
            while (resultSet.next()) {
                lsteta3.add(resultSet.getInt("CANTIDAD"));
            }
            lstDatos.add(lsteta3);
            
            // fondo4
            resultSet = getOutputParameter("PO_CURSOR_RESULTADOD").getParameterResultSet();
            while (resultSet.next()) {
                lsteta4.add(resultSet.getInt("CANTIDAD"));
            }
            lstDatos.add(lsteta4);
            
            
             // fondos
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO0").getParameterResultSet();
            while (resultSet.next()) {
               lstCali.add( "'" + (String) resultSet.getString("desETAPA") + "'");
            }
            lstDatos.add(lstCali);
            
        } catch (Exception e) {
            Logger.getLogger(MaeDashboardDao.class.getName()).log(Level.SEVERE, null, e);
        }
        finally 
        {
            if (resultSet != null) {
              try { resultSet.close(); } catch (SQLException e) { ; }
              resultSet = null;
            }
            if (cmd != null) {
              try { cmd.close(); } catch (SQLException e) { ; }
              cmd = null;
            }
            if (cn != null) {
              try { cn.close(); } catch (SQLException e) { ; }
              cn = null;
            }
        } 
        return lstDatos;
    }    
    
    
    @Override
    public ArrayList<List<Object>> cargarDashboardFondoxIndi() {
        ArrayList<List<Object>> lstDatos = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
     
        try {
            // name of procedure
            String sp = "{call PKG_LEGAL.Sp_Genera_Dashboard_FondoDesem(?,?,?,?,?,?,? )}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParametersDash_01();
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("segur");
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            
            List<Object> lsteta1 = new ArrayList<>();
            List<Object> lsteta2 = new ArrayList<>();
            List<Object> lsteta3 = new ArrayList<>();
            List<Object> lsteta4 = new ArrayList<>();
            List<Object> lstCali = new ArrayList<>();

            resultSet = getOutputParameter("PO_CURSOR_RESULTADOA").getParameterResultSet();
            // fondo1
            while (resultSet.next()) {
                lsteta1.add(resultSet.getInt("CANTIDAD"));
            }
            lstDatos.add(lsteta1);

            resultSet = getOutputParameter("PO_CURSOR_RESULTADOB").getParameterResultSet();

            // fondo2
            while (resultSet.next()) {
                lsteta2.add(resultSet.getInt("CANTIDAD"));
            }
            lstDatos.add(lsteta2);
            
            // fondo3
            resultSet = getOutputParameter("PO_CURSOR_RESULTADOC").getParameterResultSet();
            while (resultSet.next()) {
                lsteta3.add(resultSet.getInt("CANTIDAD"));
            }
            lstDatos.add(lsteta3);
            
            // fondo4
            resultSet = getOutputParameter("PO_CURSOR_RESULTADOD").getParameterResultSet();
            while (resultSet.next()) {
                lsteta4.add(resultSet.getInt("CANTIDAD"));
            }
            lstDatos.add(lsteta4);
            
            
             // fondos
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO0").getParameterResultSet();
            while (resultSet.next()) {
               lstCali.add( "'" + (String) resultSet.getString("fondo") + "'");
            }
            lstDatos.add(lstCali);
            
        } catch (Exception e) {
            Logger.getLogger(MaeDashboardDao.class.getName()).log(Level.SEVERE, null, e);
        }
        finally 
        {
            if (resultSet != null) {
              try { resultSet.close(); } catch (SQLException e) { ; }
              resultSet = null;
            }
            if (cmd != null) {
              try { cmd.close(); } catch (SQLException e) { ; }
              cmd = null;
            }
            if (cn != null) {
              try { cn.close(); } catch (SQLException e) { ; }
              cn = null;
            }
        } 
        return lstDatos;
    }    
    
    
    @Override
    public ArrayList<List<Object>> cargarDashboardAsesorxIndi() {
        ArrayList<List<Object>> lstDatos = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
     
        try {
            // name of procedure
            //String sp = "{call PKG_LEGAL.Sp_Genera_Dashboard_03(?,?,?,? )}";
            
            String sp = "{call PKG_LEGAL.Sp_Genera_Dashboard_AsesxIndi(?,?,?,?,?,?,? )}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            //oList = listParametersDash_03(); -- se comento
            oList = listParametersDash_01();
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("segur");
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            
            List<Object> lsteta1 = new ArrayList<>();
            List<Object> lsteta2 = new ArrayList<>();
            List<Object> lsteta3 = new ArrayList<>();
            List<Object> lsteta4 = new ArrayList<>();
            List<Object> lsteta5 = new ArrayList<>();
            
            

            resultSet = getOutputParameter("PO_CURSOR_RESULTADOA").getParameterResultSet();
            // fondo1
            while (resultSet.next()) {
               lsteta1.add("'" + (String) resultSet.getString("ASESOR") + "'," +   resultSet.getInt("PORC") );
            }
            lstDatos.add(lsteta1);

            
             // fondos
            resultSet = getOutputParameter("PO_CURSOR_RESULTADOB").getParameterResultSet();
            while (resultSet.next()) {
               lsteta2.add( "'" + (String) resultSet.getString("ASESOR") + "'," +   resultSet.getInt("PORC") );
               
            }
            lstDatos.add(lsteta2);
            
             // fondos
            resultSet = getOutputParameter("PO_CURSOR_RESULTADOC").getParameterResultSet();
            while (resultSet.next()) {
               lsteta3.add( "'" + (String) resultSet.getString("ASESOR") + "'," +   resultSet.getInt("PORC") );
               
            }
            lstDatos.add(lsteta3);
            
            
            resultSet = getOutputParameter("PO_CURSOR_RESULTADOD").getParameterResultSet();
            while (resultSet.next()) {
               lsteta4.add( "'" + (String) resultSet.getString("ASESOR") + "'," +   resultSet.getInt("PORC") );
               
            }
            lstDatos.add(lsteta4);
            

            resultSet = getOutputParameter("PO_CURSOR_RESULTADO0").getParameterResultSet();
            while (resultSet.next()) {
               lsteta5.add( "'" + (String) resultSet.getString("ASESOR") + "'," +   resultSet.getInt("PORC") );
               
            }
            lstDatos.add(lsteta5);
            
            
            
        } catch (Exception e) {
            Logger.getLogger(MaeDashboardDao.class.getName()).log(Level.SEVERE, null, e);
        }
        finally 
        {
            if (resultSet != null) {
              try { resultSet.close(); } catch (SQLException e) { ; }
              resultSet = null;
            }
            if (cmd != null) {
              try { cmd.close(); } catch (SQLException e) { ; }
              cmd = null;
            }
            if (cn != null) {
              try { cn.close(); } catch (SQLException e) { ; }
              cn = null;
            }
        } 
        return lstDatos;
    }    
    
    
    private List<ParameterOracle> listParameters_bus(LegalTchn oLegalTchn) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oLegalTchn.getMaeInversion().getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_MAE_INVERSION_ID", oLegalTchn.getMaeInversion().getcMaeInversionId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_INVERSION", oLegalTchn.getMaeInversion().getCInversion(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_A_NRO_DOCUMENTO", oLegalTchn.getMaeInversion().getcPersonaId().getANroDocumento(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_APENOMBRE", oLegalTchn.getdDatoBusq(), OracleTypes.CHAR, ParameterDirection.Input));
//        oListParam.add(new ParameterOracle("PI_C_UBIGEO_ID_P", oLegalTchn.getMaeInversion().getMaeInmueble().getMaeUbigeo().getCUbigeoPad(), OracleTypes.CHAR, ParameterDirection.Input));
   //     oListParam.add(new ParameterOracle("PI_C_UBIGEO_ID_D", oLegalTchn.getMaeInversion().getMaeInmueble().getMaeUbigeo().getCUbigeoId(), OracleTypes.CHAR, ParameterDirection.Input));
        //        
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }
    
    private List<ParameterOracle> listParameters_legal(LegalTchn oLegalTchn) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oLegalTchn.getMaeInversion().getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_MAE_INVERSION_ID", oLegalTchn.getMaeInversion().getcMaeInversionId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_INVERSION", oLegalTchn.getMaeInversion().getCInversion(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_A_NRO_DOCUMENTO", oLegalTchn.getMaeInversion().getcPersonaId().getANroDocumento(), OracleTypes.CHAR, ParameterDirection.Input));
        
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }
    
    private List<ParameterOracle> listParameters_etapa(LegalTchn oLegalTchn) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_FONDO_ID",  oLegalTchn.getFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_INVERSION", oLegalTchn.getMaeInversion().getCInversion(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_TIPOETAPA", oLegalTchn.getIdetapa(), OracleTypes.VARCHAR, ParameterDirection.Input));
         oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }
    
    
    
    private List<ParameterOracle> listParameters_segui(LegSeguiTchn olegSeguiTchn) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PC_CODIGOTCHN", olegSeguiTchn.getMaeInversion().getCInversion(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_FONDO",  olegSeguiTchn.getFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_TIPOETAPA", olegSeguiTchn.getLs_tipoEtapa(), OracleTypes.VARCHAR, ParameterDirection.Input));
         oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }
    
    private List<ParameterOracle> listParameters_est_segui(LegSeguiTchn olegSeguiTchn) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_NSEGUI", olegSeguiTchn.getLsIdSegui(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_CODIGOTCHN", olegSeguiTchn.getMaeInversion().getCInversion(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_FONDO",  olegSeguiTchn.getFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_TIPOETAPA", olegSeguiTchn.getLs_tipoEtapa(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_ESTADO", olegSeguiTchn.getLsCodEst(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }
    
    private List<ParameterOracle> INSParameters_legal(LegalTchn oLegalTchn) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PC_CODIGOTCHN", oLegalTchn.getMaeInversion().getCInversion(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_FONDO", oLegalTchn.getMaeInversion().getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_TIPOETAPA", oLegalTchn.getIdetapa(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PD_FECHA", oLegalTchn.getLdFecha(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_MONTO_CAPITAL",oLegalTchn.getLnCapital(), OracleTypes.DOUBLE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_MONTO_INTERES",   oLegalTchn.getLnInteres(),OracleTypes.DOUBLE,ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_MONTO_MORA", oLegalTchn.getLnMora() , OracleTypes.DOUBLE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_MONTO",  oLegalTchn.getLnCapital() +  oLegalTchn.getLnInteres() +  oLegalTchn.getLnMora() ,OracleTypes.DOUBLE, ParameterDirection.Input));        
        oListParam.add(new ParameterOracle("PC_DESCRIPCION",oLegalTchn.getDdescripcion(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PD_FTASA", oLegalTchn.getLdFechaTasa(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PD_FECHAAUTO", oLegalTchn.getLdFechaAutomisor(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_CODJUZ", oLegalTchn.getLsCodJuz(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_CODESP", oLegalTchn.getLsCodEsp(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_NROEXP", oLegalTchn.getLsNroExp(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_MONTO_ADMI", oLegalTchn.getLnmontoAdm(), OracleTypes.DOUBLE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_C_USUARIO_ADD", oLegalTchn.getLsusuario(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_USUARIO_MOD", oLegalTchn.getLsusuario(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_I_PROCESO", 0, OracleTypes.NUMBER, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));
                             
                               

        return oListParam;
    }
    
    
    
    private List<ParameterOracle> INSParameters_Seglegal(LegSeguiTchn olegSeguiTchn) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_NSEGUI",olegSeguiTchn.getLnCorrelativo(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_CODIGOTCHN",olegSeguiTchn.getMaeInversion().getCInversion() , OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_FONDO", olegSeguiTchn.getFondo().getCFondoId(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_TIPOETAPA", olegSeguiTchn.getLs_tipoEtapa(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_ESTADO",olegSeguiTchn.getLsCodEst(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PD_FECHA", olegSeguiTchn.getLd_fecha(),OracleTypes.DATE,ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_DESCRIPCION", olegSeguiTchn.getLsDescrip(), OracleTypes.VARCHAR , ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_CONCLUIR",olegSeguiTchn.getFconcluir(), OracleTypes.VARCHAR , ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_REMATE",olegSeguiTchn.getFremate(), OracleTypes.VARCHAR , ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PD_FECHA_VAL",olegSeguiTchn.getLdFechaval(), OracleTypes.DATE , ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PN_MONTO_VAL",olegSeguiTchn.getLnMontoval(), OracleTypes.DOUBLE , ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PN_MONTODOL_VAL",olegSeguiTchn.getLnMontodolval(), OracleTypes.DOUBLE , ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PN_INTERES",olegSeguiTchn.getLnInteres(), OracleTypes.DOUBLE , ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PN_MORA",olegSeguiTchn.getLnIntMora(), OracleTypes.DOUBLE , ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PN_COSTOS",olegSeguiTchn.getLnCostos(), OracleTypes.DOUBLE , ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PN_COSTAS",olegSeguiTchn.getLnCostas(), OracleTypes.DOUBLE , ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_C_USUARIO_ADD",  olegSeguiTchn.getLs_UsuarioAdd() ,OracleTypes.VARCHAR, ParameterDirection.Input));        
        oListParam.add(new ParameterOracle("PC_USUARIO_MOD", olegSeguiTchn.getLs_UsuarioAdd(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_I_PROCESO", "", OracleTypes.NUMBER, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));
                             
                               

        return oListParam;
    }
    
    
    private List<ParameterOracle> delParameters_Seglegal(LegSeguiTchn olegSeguiTchn) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_NSEGUI",olegSeguiTchn.getLsIdSegui(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_CODIGOTCHN",olegSeguiTchn.getMaeInversion().getCInversion() , OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_FONDO", olegSeguiTchn.getFondo().getCFondoId(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_I_PROCESO", "", OracleTypes.NUMBER, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));
                             
                               

        return oListParam;
    }
    
    private List<ParameterOracle> Parameters_CerrarEtapalegal(LegSeguiTchn olegSeguiTchn) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PC_CODIGOTCHN",olegSeguiTchn.getMaeInversion().getCInversion() , OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_FONDO", olegSeguiTchn.getFondo().getCFondoId(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_TIPOETAPA", olegSeguiTchn.getLs_tipoEtapa(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));
                             
                               

        return oListParam;
    }
    
    

    private List<ParameterOracle> listParameters_busEstCta(MaeEstadoCuenta oMaeEstadoCuenta) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_MAE_INVERSION_ID", oMaeEstadoCuenta.getMaeInversion().getcMaeInversionId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TIPO_INV", oMaeEstadoCuenta.getMaeInversion().getcTipoInv(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_F_PROCESO", oMaeEstadoCuenta.getfIniBusq(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO_ADD", oMaeEstadoCuenta.getcUsuarioAdd(), OracleTypes.CHAR, ParameterDirection.Input));
        //        
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }
    
     private List<ParameterOracle> listParameters_Estado(String idEtapa) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PC_TIPOETAPA", idEtapa, OracleTypes.CHAR, ParameterDirection.Input));
          
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

     private List<ParameterOracle> listParameters_EstadoMODY() {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }
     
     
     private List<ParameterOracle> listParameters_Espe(String codigo,String codjuz) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        oListParam.add(new ParameterOracle("PC_CODIGO", codigo, OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_CODIGO2", codjuz, OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
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

    @Override
    public ArrayList<LegalTchn> fetchListaSeguidores(LegalTchn oLegalTchn) {
        ArrayList<LegalTchn> lstTchn = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
         
            
           
            // name of procedure
            String sp = "{call PKG_LEGAL.SP_LISTAR_LEG_DET(?,?,?,?,?)}";
            // list of parameter
            
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameterseg(oLegalTchn.getLsCodJuz());
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("segur");
            // execute procedure
            // execute procedure
            runSP(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                LegalTchn newLegalTchn=new LegalTchn();
                newLegalTchn.setLsCodEsp(resultSet.getString("CARGUM"));
                newLegalTchn.setLsNomEsp(resultSet.getString("DDESCRIPCION"));
                newLegalTchn.setLsCodJuz(resultSet.getString("DCORTA"));
                lstTchn.add(newLegalTchn);
            }
           
        } catch (Exception e) {
            Logger.getLogger(LegalTchn.class.getName()).log(Level.SEVERE, null, e);
            
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
        //System.out.println(" <f> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        return lstTchn;
    }

     private List<ParameterOracle> listParameterseg(String codigo) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        oListParam.add(new ParameterOracle("PC_CODIGO", codigo, OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }
    
    private List<ParameterOracle> listParameters_legalAsesor(LegalTchn oLegalTchn) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PC_FONDO", oLegalTchn.getMaeInversion().getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_TIPOETAPA", oLegalTchn.getIdetapa(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

    private List<ParameterOracle> listParametersDash(String LsUsuario) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        oListParam.add(new ParameterOracle("PC_C_USUARIO",  LsUsuario ,OracleTypes.VARCHAR, ParameterDirection.Input));    
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADOE", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADOA", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADOB", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

    
     private List<ParameterOracle> listParametersDash_01() {
        List<ParameterOracle> oListParam = new ArrayList<>();
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADOA", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADOB", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADOC", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADOD", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO0", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }
     
     private List<ParameterOracle> listParametersDash_03() {
        List<ParameterOracle> oListParam = new ArrayList<>();
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADOA", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO0", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }
     
    private List<ParameterOracle> listParameters_Dash() {
        List<ParameterOracle> oListParam = new ArrayList<>();
          
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADOE", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

    private List<ParameterOracle> listParameters_DashxImp(String mes,String anio) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        oListParam.add(new ParameterOracle("PI_MES", mes, OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_ANIO", anio, OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

    
    
    @Override
    public ArrayList<MaeMes> fetchListarMes() {
         //System.out.println(" <i> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        ArrayList<MaeMes>  lstMes = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_LEGAL.Sp_Lista_Dashboard_mes(?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            
            oList = listParameters_Dash();
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("segur");
            // execute procedure
            
            runSearch(oList, cn, cmd, sp);
            
            resultSet = getOutputParameter("PO_CURSOR_RESULTADOE").getParameterResultSet();

            while (resultSet.next()) {
                 //tchn
                MaeMes newMaeMes = new MaeMes();
                newMaeMes.setCMesId(resultSet.getString("CODIGO"));
                newMaeMes.setDMes(resultSet.getString("MES"));
                lstMes.add(newMaeMes);
            }


        } catch (Exception e) {
            Logger.getLogger(MaeEstadoLegal.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        return lstMes;
    }
       
            
    
     @Override
    public ArrayList<MaeAnio> fetchListarAnio() {
         //System.out.println(" <i> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        ArrayList<MaeAnio>  lstAnio = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_LEGAL.Sp_Lista_Dashboard_Anio(?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            
            oList = listParameters_Dash();
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("segur");
            // execute procedure
            
            runSearch(oList, cn, cmd, sp);
            
            resultSet = getOutputParameter("PO_CURSOR_RESULTADOE").getParameterResultSet();

            while (resultSet.next()) {
                 //tchn
                MaeAnio newMaeAnio = new MaeAnio();
                newMaeAnio.setCAnioId(resultSet.getString("ANIO"));
                newMaeAnio.setDAnio(resultSet.getString("ANIO"));
                lstAnio.add(newMaeAnio);
            }


        } catch (Exception e) {
            Logger.getLogger(MaeEstadoLegal.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        return lstAnio;
    }
    
                

    @Override
    public ArrayList<LegalTchn> fetchLegalImpxfondo(String Mes, String Anio) {
               //System.out.println(" <i> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        ArrayList<LegalTchn>  lstlegal = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_LEGAL.Sp_Lista_Dashboard_Impxfondo(?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            
            oList = listParameters_DashxImp(Mes,Anio);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("segur");
            // execute procedure
            
            runSearch(oList, cn, cmd, sp);
            
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                 //tchn
                LegalTchn newLegalTchn = new LegalTchn();
                newLegalTchn.setLsAsesor(resultSet.getString("ASESOR"));
                newLegalTchn.setiCapital(resultSet.getInt("CAPITAL"));
                newLegalTchn.setiPopular(resultSet.getInt("POPULAR"));
                newLegalTchn.setiMype(resultSet.getInt("MYPE"));
                newLegalTchn.setiPerez(resultSet.getInt("PEREZH"));
                newLegalTchn.setiTotalAse(resultSet.getInt("TOTAL"));
                lstlegal.add(newLegalTchn);
            }


        } catch (Exception e) {
            Logger.getLogger(MaeEstadoLegal.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        return lstlegal;
    }

    
    
    
    
    @Override
    public ArrayList<LegalTchn> fetchLista_TopVisiCap(String Mes, String Anio) {
               //System.out.println(" <i> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        ArrayList<LegalTchn>  lstlegal = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_LEGAL.Sp_Lista_Dashboard_TopVisiCap(?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            
            oList = listParameters_DashxImp(Mes,Anio);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("segur");
            // execute procedure
            
            runSearch(oList, cn, cmd, sp);
            
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                MaeInversion newInversion = new MaeInversion();
                newInversion.setCInversion(resultSet.getString("CODIGOTCHN"));

                 //tchn
                LegalTchn newLegalTchn = new LegalTchn();
                    newLegalTchn.setMaeInversion(newInversion);
                    newLegalTchn.setiTotalFondo(resultSet.getInt("CODIGO")) ;
                    newLegalTchn.setiTotalAse(resultSet.getInt("CANTIDAD"));
                    newLegalTchn.setIdetapa(resultSet.getString("TIPOETAPA"));
                    newLegalTchn.setLsAsesor(resultSet.getString("ASESOR"));

                lstlegal.add(newLegalTchn);
            }


        } catch (Exception e) {
            Logger.getLogger(MaeEstadoLegal.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        return lstlegal;
    }
     
    
    
    
    @Override
    public ArrayList<LegalTchn> fetchLista_TopVisiPop(String Mes, String Anio) {
               //System.out.println(" <i> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        ArrayList<LegalTchn>  lstlegal = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_LEGAL.Sp_Lista_Dashboard_TopVisiPop(?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            
            oList = listParameters_DashxImp(Mes,Anio);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("segur");
            // execute procedure
            
            runSearch(oList, cn, cmd, sp);
            
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                MaeInversion newInversion = new MaeInversion();
                newInversion.setCInversion(resultSet.getString("CODIGOTCHN"));

                 //tchn
                LegalTchn newLegalTchn = new LegalTchn();
                    newLegalTchn.setMaeInversion(newInversion);
                    newLegalTchn.setiTotalFondo(resultSet.getInt("CODIGO")) ;
                    newLegalTchn.setiTotalAse(resultSet.getInt("CANTIDAD"));
                    newLegalTchn.setIdetapa(resultSet.getString("TIPOETAPA"));
                    newLegalTchn.setLsAsesor(resultSet.getString("ASESOR"));
                lstlegal.add(newLegalTchn);
            }


        } catch (Exception e) {
            Logger.getLogger(MaeEstadoLegal.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        return lstlegal;
    }
    
    
      @Override
    public ArrayList<LegalTchn> fetchLista_TopVisiMyp(String Mes, String Anio) {
               //System.out.println(" <i> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        ArrayList<LegalTchn>  lstlegal = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_LEGAL.Sp_Lista_Dashboard_TopVisiMyp(?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            
            oList = listParameters_DashxImp(Mes,Anio);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("segur");
            // execute procedure
            
            runSearch(oList, cn, cmd, sp);
            
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                MaeInversion newInversion = new MaeInversion();
                newInversion.setCInversion(resultSet.getString("CODIGOTCHN"));

                 //tchn
                LegalTchn newLegalTchn = new LegalTchn();
                
                    newLegalTchn.setMaeInversion(newInversion);
                    newLegalTchn.setiTotalFondo(resultSet.getInt("CODIGO")) ;
                    newLegalTchn.setiTotalAse(resultSet.getInt("CANTIDAD"));
                    newLegalTchn.setIdetapa(resultSet.getString("TIPOETAPA"));
                    newLegalTchn.setLsAsesor(resultSet.getString("ASESOR"));
                lstlegal.add(newLegalTchn);
            }


        } catch (Exception e) {
            Logger.getLogger(MaeEstadoLegal.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        return lstlegal;
    }
    
    
      @Override
    public ArrayList<LegalTchn> fetchLista_TopVisiPrh(String Mes, String Anio) {
               //System.out.println(" <i> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        ArrayList<LegalTchn>  lstlegal = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_LEGAL.Sp_Lista_Dashboard_TopVisiPrh(?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            
            oList = listParameters_DashxImp(Mes,Anio);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("segur");
            // execute procedure
            
            runSearch(oList, cn, cmd, sp);
            
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                MaeInversion newInversion = new MaeInversion();
                newInversion.setCInversion(resultSet.getString("CODIGOTCHN"));

                 //tchn
                LegalTchn newLegalTchn = new LegalTchn();
                
                    newLegalTchn.setMaeInversion(newInversion);
                    newLegalTchn.setiTotalFondo(resultSet.getInt("CODIGO")) ;
                    newLegalTchn.setiTotalAse(resultSet.getInt("CANTIDAD"));
                    newLegalTchn.setIdetapa(resultSet.getString("TIPOETAPA"));
                    newLegalTchn.setLsAsesor(resultSet.getString("ASESOR"));
                lstlegal.add(newLegalTchn);
            }


        } catch (Exception e) {
            Logger.getLogger(MaeEstadoLegal.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        return lstlegal;
    }

    @Override
    public ArrayList<List<Object>> fetchDashboard_Impxfondo() {
        ArrayList<List<Object>> lstDatos = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
     
        try {
            // name of procedure
            String sp = "{call PKG_LEGAL.Sp_Genera_Dashboard_Impxfondo(?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParametersDash_imp();
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("segur");
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            
            List<Object> lst1 = new ArrayList<>();
            List<Object> lst2 = new ArrayList<>();
            List<Object> lst3 = new ArrayList<>();
            List<Object> lst4 = new ArrayList<>();
            List<Object> lst5 = new ArrayList<>();
            List<Object> lst00 = new ArrayList<>();

             
            resultSet = getOutputParameter("PO_CURSOR_RESULTADOA").getParameterResultSet();
            // asesor 0001
            while (resultSet.next()) {
                lst1.add(resultSet.getInt("CANTIDAD"));
            }
            lstDatos.add(lst1);

            // asesor 0002
            resultSet = getOutputParameter("PO_CURSOR_RESULTADOB").getParameterResultSet();
            while (resultSet.next()) {
                lst2.add(resultSet.getInt("CANTIDAD"));
            }
            lstDatos.add(lst2);

            // asesor 0003
            resultSet = getOutputParameter("PO_CURSOR_RESULTADOC").getParameterResultSet();
            while (resultSet.next()) {
                lst3.add(resultSet.getInt("CANTIDAD"));
            }
            lstDatos.add(lst3);
            
            // asesor 0004
            resultSet = getOutputParameter("PO_CURSOR_RESULTADOD").getParameterResultSet();
            while (resultSet.next()) {
                lst4.add(resultSet.getInt("CANTIDAD"));
            }
            lstDatos.add(lst4);

            
             
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO0").getParameterResultSet();
            while (resultSet.next()) {
              lst00.add( "'" + (String) resultSet.getString("Asesor") + "'");
              lst5.add(  resultSet.getString("Asesor")  );
            }
            lstDatos.add(lst5);
            lstDatos.add(lst00);
            
        } catch (Exception e) {
            Logger.getLogger(MaeDashboardDao.class.getName()).log(Level.SEVERE, null, e);
        }
        finally 
        {
            if (resultSet != null) {
              try { resultSet.close(); } catch (SQLException e) { ; }
              resultSet = null;
            }
            if (cmd != null) {
              try { cmd.close(); } catch (SQLException e) { ; }
              cmd = null;
            }
            if (cn != null) {
              try { cn.close(); } catch (SQLException e) { ; }
              cn = null;
            }
        } 
        return lstDatos;
    }
    
    
     private List<ParameterOracle> listParametersDash_imp() {
        List<ParameterOracle> oListParam = new ArrayList<>();
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADOA", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADOB", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADOC", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADOD", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO0", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }



    @Override
    public ArrayList<LegalTchn> fetchlista_ImpTopneg(String Mes, String Anio) {
               //System.out.println(" <i> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        ArrayList<LegalTchn>  lstlegal = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_LEGAL.Sp_Lista_DashboardImp_Topneg(?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            
            oList = listParameters_DashxImp(Mes,Anio);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("segur");
            // execute procedure
            
            runSearch(oList, cn, cmd, sp);
            
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                MaeInversion newInversion = new MaeInversion();
                newInversion.setCInversion(resultSet.getString("CODIGOTCHN"));

                 //tchn
                LegalTchn newLegalTchn = new LegalTchn();
                newLegalTchn.setMaeInversion(newInversion);
                newLegalTchn.setiTotalIndi(resultSet.getInt("CODIGO")) ;
                newLegalTchn.setDdescripcion(resultSet.getString("D_FONDO"));
                newLegalTchn.setEtapa(resultSet.getString("DTIPOETAPA"));
                newLegalTchn.setLnDiastra(resultSet.getInt("DIASTRAN"));
                newLegalTchn.setiTotalFondo(resultSet.getInt("CANTIDAD"));
                lstlegal.add(newLegalTchn);
            }


        } catch (Exception e) {
            Logger.getLogger(MaeEstadoLegal.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        return lstlegal;
    }
    
    
    
    @Override
    public ArrayList<LegalTchn> fetchLista_xAseEtapa(String Mes, String Anio) {
               //System.out.println(" <i> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        ArrayList<LegalTchn>  lstlegal = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_LEGAL.Sp_Lista_Dashboard_ImpxEtapa(?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            
            oList = listParameters_DashxImp(Mes,Anio);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("segur");
            // execute procedure
            
            runSearch(oList, cn, cmd, sp);
            
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                  //tchn
                LegalTchn newLegalTchn = new LegalTchn();
                newLegalTchn.setLsAsesor(resultSet.getString("ASESOR"));
                newLegalTchn.setiCalifi(resultSet.getInt("CALIFICACION"));
                newLegalTchn.setiPostula(resultSet.getInt("POSTULATORIO"));
                newLegalTchn.setiDesiso(resultSet.getInt("PROBATORIO"));
                newLegalTchn.setiImpug(resultSet.getInt("DECISORIO"));
                newLegalTchn.setiEjecu(resultSet.getInt("EJECUCION"));
                newLegalTchn.setiImpug(resultSet.getInt("IMPUGNATORIO"));
                newLegalTchn.setiFinal1(resultSet.getInt("FINAL1"));
                newLegalTchn.setiTotalAse(resultSet.getInt("TOTAL"));
                lstlegal.add(newLegalTchn);
            }


        } catch (Exception e) {
            Logger.getLogger(MaeEstadoLegal.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        return lstlegal;
    }
    
    
    
     @Override
    public ArrayList<LegalTchn> fetchLista_xAseIndicador(String Mes, String Anio) {
               //System.out.println(" <i> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        ArrayList<LegalTchn>  lstlegal = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_LEGAL.Sp_Lista_Dashboard_ImpxIndi(?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            
            oList = listParameters_DashxImp(Mes,Anio);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("segur");
            // execute procedure
            
            runSearch(oList, cn, cmd, sp);
            
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                  //tchn
                LegalTchn newLegalTchn = new LegalTchn();
                newLegalTchn.setLsAsesor(resultSet.getString("ASESOR"));
                newLegalTchn.setiOptimo(resultSet.getInt("OPTIMO"));
                newLegalTchn.setiNormal(resultSet.getInt("NORMAL1"));
                newLegalTchn.setiRegular(resultSet.getInt("REGULAR"));
                newLegalTchn.setiDeficiente(resultSet.getInt("DEFICIENTE"));
                newLegalTchn.setiTotalAse(resultSet.getInt("TOTAL"));
                lstlegal.add(newLegalTchn);
            }


        } catch (Exception e) {
            Logger.getLogger(MaeEstadoLegal.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        return lstlegal;
    }
    



    @Override
    public ArrayList<List<Object>> fetchDashboard_xAseIndicador() {
        ArrayList<List<Object>> lstDatos = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
     
        try {
            // name of procedure
            String sp = "{call PKG_LEGAL.Sp_Genera_Dashboard_AseDese(?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParametersDash_imp();
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("segur");
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            
            List<Object> lst1 = new ArrayList<>();
            List<Object> lst2 = new ArrayList<>();
            List<Object> lst3 = new ArrayList<>();
            List<Object> lst4 = new ArrayList<>();
            List<Object> lst5 = new ArrayList<>();
            

             
            resultSet = getOutputParameter("PO_CURSOR_RESULTADOA").getParameterResultSet();
            // asesor 0001
            while (resultSet.next()) {
                lst1.add(resultSet.getInt("CANTIDAD"));
            }
            lstDatos.add(lst1);

            // asesor 0002
            resultSet = getOutputParameter("PO_CURSOR_RESULTADOB").getParameterResultSet();
            while (resultSet.next()) {
                lst2.add(resultSet.getInt("CANTIDAD"));
            }
            lstDatos.add(lst2);

            // asesor 0003
            resultSet = getOutputParameter("PO_CURSOR_RESULTADOC").getParameterResultSet();
            while (resultSet.next()) {
                lst3.add(resultSet.getInt("CANTIDAD"));
            }
            lstDatos.add(lst3);
            
            // asesor 0004
            resultSet = getOutputParameter("PO_CURSOR_RESULTADOD").getParameterResultSet();
            while (resultSet.next()) {
                lst4.add(resultSet.getInt("CANTIDAD"));
            }
            lstDatos.add(lst4);

            
             
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO0").getParameterResultSet();
            while (resultSet.next()) {
              lst5.add( "'" + (String) resultSet.getString("Asesor") + "'");
            }
            lstDatos.add(lst5);
            
        } catch (Exception e) {
            Logger.getLogger(MaeDashboardDao.class.getName()).log(Level.SEVERE, null, e);
        }
        finally 
        {
            if (resultSet != null) {
              try { resultSet.close(); } catch (SQLException e) { ; }
              resultSet = null;
            }
            if (cmd != null) {
              try { cmd.close(); } catch (SQLException e) { ; }
              cmd = null;
            }
            if (cn != null) {
              try { cn.close(); } catch (SQLException e) { ; }
              cn = null;
            }
        } 
        return lstDatos;
    }    
    
}

