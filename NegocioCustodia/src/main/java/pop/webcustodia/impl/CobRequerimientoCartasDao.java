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
import pop.comun.dominio.CobMaeSeguimiento;
import pop.comun.dominio.CobRequerimientoCartas;
import pop.comun.dominio.CobRequerimientoCriterios;
import pop.comun.dominio.CobTablas;
import pop.comun.dominio.MaeFondo;
import pop.comun.dominio.MaeInmueble;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MaePersona;
import pop.comun.dominio.MovimientoCartas;
import pop.webcustodia.common.DBUtil;
import pop.webcustodia.common.ParameterDirection;
import pop.webcustodia.common.ParameterOracle;
import pop.webcustodia.conn.Conexion;
import pop.webcustodia.iface.ICobRequerimientoCartasDao;

/**
 *
 * @author RC433838
 */
public class CobRequerimientoCartasDao extends DBUtil implements ICobRequerimientoCartasDao{

    private OracleConnection cn = null;

    
    /**
     * @return the cn
     */
    public OracleConnection getCn() {
        return cn;
    }

    /**
     * @param cn the cn to set
     */
    public void setCn(OracleConnection cn) {
        this.cn = cn;
    }

    @Override
    public String removerRequerimiento(CobRequerimientoCartas oRequerimiento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public String cambiaEstadoReq(CobRequerimientoCartas oRequerimiento) {
        
        System.out.println(" <i> cambiaEstadoReq(CobRequerimientoCartas oRequerimiento) "+ LocalDateTime.now() );
        
        
        String Rspta = null;
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        //OracleResultSet resultSetB = null;

        try {
            // name of procedure
            String sp = "{call PKG_REQUERIMIENTOS.SP_CAMBIA_ESTADO_CUSTODIA(?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            
            oList.add(new ParameterOracle("P_REQ_TIPO", oRequerimiento.getReqTipo(), OracleTypes.VARCHAR, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_REQ_NUMERO", oRequerimiento.getReqNumero(), OracleTypes.NUMBER, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_REQ_ESTADO", oRequerimiento.getReqEstado(), OracleTypes.VARCHAR, ParameterDirection.Input));    
            oList.add(new ParameterOracle("P_REQ_COMENTARIO", oRequerimiento.getReqComentario(), OracleTypes.VARCHAR, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_C_USUARIO_MOD", oRequerimiento.getcUsuarioMod(), OracleTypes.VARCHAR, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output)); 

            
            //Abre conexion a la BD
            Conexion conex = new Conexion();
    
            cn = conex.ConexionOpen("eva");
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
            if (getCn() != null) {
              try { getCn().close(); } catch (Exception e) { ; }
                setCn(null);
            }
        }
        System.out.println(" <f> rcambiaEstadoReq(CobRequerimientoCartas oRequerimiento) " + LocalDateTime.now());
        return Rspta;
    }    
    
    @Override
    public String addRequerimiento(CobRequerimientoCriterios oCriterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MaeInversion> allInversiones(MaeInversion oInversion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CobRequerimientoCartas> allRequerimientos(CobRequerimientoCartas oRequerimiento) {
        System.out.println(" <i> allRequerimientos " + LocalDateTime.now());
        List<CobRequerimientoCartas> listRequerimiento = new ArrayList<CobRequerimientoCartas>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;

        try {
            // name of procedure
            String sp = "{call PKG_REQUERIMIENTOS.SP_BUSCAR_REQUERIMIENTO(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
        
                        
            oList.add(new ParameterOracle("P_FONDO_ID", oRequerimiento.getFondoId(), OracleTypes.VARCHAR, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_DVALOR_BV", oRequerimiento.getDvalor_bv(), OracleTypes.VARCHAR, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_CUOTA_DIA1", 0, OracleTypes.NUMBER, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_CUOTA_DIA2", 0, OracleTypes.NUMBER, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_REQ_ENVIO1", oRequerimiento.getReqEnvio(), OracleTypes.DATE, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_REQ_ENVIO2", oRequerimiento.getReqEnvio2(), OracleTypes.DATE, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_REQ_RECEPCION1", oRequerimiento.getReqRecepcion(), OracleTypes.DATE, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_REQ_RECEPCION2", oRequerimiento.getReqRecepcion2(), OracleTypes.DATE, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_TNOMBRES", oRequerimiento.getInversion().getcPersonaId().getDNombres(), OracleTypes.VARCHAR, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_REQ_ESTADO", oRequerimiento.getReqEstado(), OracleTypes.VARCHAR, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_TIPO_CARTA", oRequerimiento.getTipoCarta(), OracleTypes.VARCHAR, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_PRIORI", 0, OracleTypes.NUMBER, ParameterDirection.Input));            
            oList.add(new ParameterOracle("P_RESULTADO", "", OracleTypes.CURSOR, ParameterDirection.Output));
                        
            //Abre conexion a la BD
            Conexion conex = new Conexion();
    
            cn = conex.ConexionOpen("eva");
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("P_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                
                CobRequerimientoCartas oReq = new CobRequerimientoCartas();
                oReq.setReqTipo(resultSet.getString("REQ_TIPO"));
                oReq.setReqNumero(resultSet.getInt("REQ_NUMERO"));
                oReq.setFondoId(resultSet.getString("FONDO_ID"));
                oReq.setDvalor_bv(resultSet.getString("DVALOR_BV"));
                oReq.setReqEmision(resultSet.getDate("REQ_EMISION"));
                oReq.setReqEnvio(resultSet.getDate("REQ_ENVIO"));
                oReq.setReqRecepcion(resultSet.getDate("REQ_RECEPCION"));
                oReq.setTipoCarta(resultSet.getString("TIPO_CARTA"));
                oReq.setReqEstado(resultSet.getString("REQ_ESTADO"));
                oReq.setReqComentario(resultSet.getString("REQ_COMENTARIO"));
                
                CobTablas tipoCarta = new CobTablas();
                tipoCarta.setCtablaDetId(resultSet.getString("TIPO_CARTA"));                
                tipoCarta.setDdescripcion(resultSet.getString("DESC_CARTA"));
                oReq.setCarta(tipoCarta);
                
                MaeFondo fondo = new MaeFondo();
                fondo.setCFondoId(resultSet.getString("FONDO_ID"));
                fondo.setDFondo(resultSet.getString("D_ABREVIADO"));
                oReq.setFondo(fondo);
                
                MaeInversion inversion = new MaeInversion();
                inversion.setCInversion(resultSet.getString("DVALOR_BV"));
                inversion.setcMaeInversionId(resultSet.getInt("C_MAE_INVERSION_ID"));
                inversion.setcInversionId(resultSet.getString("C_INVERSION_ID"));
                inversion.setNCuotas(resultSet.getInt("CUOTAS"));
                
                MaeInmueble inm = new MaeInmueble();
                inm.setDDir1(resultSet.getString("DUBIGEO"));                
                inversion.setMaeInmueble(inm);                
                
                MaePersona persona= new MaePersona();
                persona.setDApePat(resultSet.getString("TAPATERNO"));
                persona.setDApeMat(resultSet.getString("TAMATERNO"));
                persona.setDNombres(resultSet.getString("TNOMBRES"));
                inversion.setcPersonaId(persona);
                inversion.setMaeFondo(fondo);
                
                CobMaeSeguimiento seguimiento =new CobMaeSeguimiento();
                seguimiento.setMaeInversion(inversion);
                oReq.setInversion(inversion);
                oReq.setMaeSeguimiento(seguimiento);

                listRequerimiento.add(oReq);
            
                                              
            }
            System.out.println("fin allRequerimientos "+LocalDateTime.now());
        } catch (Exception e) {
            Logger.getLogger(MaeDireccionDao.class.getName()).log(Level.SEVERE, null, e);
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
        
        //System.out.println(" <f> CobSeguimiento fetch " + LocalDateTime.now());
        return listRequerimiento;
    }

    @Override
    public List<CobRequerimientoCriterios> findReqSugerido() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CobRequerimientoCriterios> findCriterios(CobRequerimientoCriterios oRequerimiento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MovimientoCartas> findCartas(MaeInversion oInversion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
