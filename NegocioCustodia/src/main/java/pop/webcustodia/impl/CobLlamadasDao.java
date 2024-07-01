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
import pop.comun.dominio.CobCdr;
import pop.comun.dominio.CobLlamadas;
import pop.comun.dominio.CobMaeSeguimiento;
import pop.comun.dominio.CobSeguimiento;
import pop.comun.dominio.CobTablas;
import pop.comun.dominio.MaeFondo;
import pop.comun.dominio.MaeInversion;
import pop.webcustodia.common.DBUtil;
import pop.webcustodia.common.ParameterDirection;
import pop.webcustodia.common.ParameterOracle;
import pop.webcustodia.iface.ICobLlamadasDao;

import pop.webcustodia.conn.Conexion;
import pop.webcustodia.dao.FactoryDao;
/**
 *
 * @author Jyoverar
 */
public class CobLlamadasDao extends DBUtil implements ICobLlamadasDao{

    private OracleConnection cn = null;

    public CobLlamadasDao() {

    }

    public CobLlamadasDao(OracleConnection cnx) {
        cn = cnx;
    }
    
    @Override
    public Integer insert(CobLlamadas oCobLlamadas) {
        //System.out.println(" <i> insertar llamada " + LocalDateTime.now());
        Integer newID = 0;
        OracleCallableStatement cmd = null;
        
        try {                        
            // name of procedure
            String sp = "{call PKG_COB_LLAMADAS.SP_INS_COB_LLAMADAS(?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameters
            List<ParameterOracle> oLis = new ArrayList<>();
            // fill parameters
            oLis = insertParameters(oCobLlamadas);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("segur");
            // execute procedure
            runSP(oLis, cn, cmd, sp);

            newID =  getOutputParameter("PO_C_COB_LLAMADAS_ID").getParameterInt();
                    
        } catch (SQLException e) {            
            Logger.getLogger(LaftPersonaDao.class.getName()).log(Level.SEVERE, null, e);
        }
        finally 
        {
            if (cmd != null) {
              try { cmd.close(); } catch (SQLException e) { ; }
              cmd = null;
            }
            if (cn != null) {
              try { cn.close(); } catch (SQLException e) { ; }
              cn = null;
            }
        }
        //System.out.println(" <f> insertar llamada " + LocalDateTime.now());
        return newID;
    }

    @Override
    public void update(CobLlamadas oCobLlamadas) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(CobLlamadas oCobLlamadas) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<CobLlamadas> fetchAll(CobLlamadas oCobLlamadas) {
        //System.out.println(" <i> CobLlamadasDao fetchAll " + LocalDateTime.now());
        ArrayList<CobLlamadas> lstLlamadas = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_COB_LLAMADAS.SP_BUS_COB_LLAMADAS(?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters_bus(oCobLlamadas);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("segur");
            // execute procedure
            runSearch(oList, cn, cmd, sp);

            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                // fondo
                MaeFondo fondo = new MaeFondo();
                fondo.setCFondoId(resultSet.getString("C_FONDO_ID"));
                fondo.setDFondo(resultSet.getString("D_FONDO"));
                //inversion
                MaeInversion inversion = new MaeInversion();
                inversion.setcMaeInversionId(resultSet.getInt("C_MAE_INVERSION_ID"));
                inversion.setCInversion(resultSet.getString("C_INVERSION"));
                inversion.setMaeFondo(fondo);
                // mae seguimiento
                CobMaeSeguimiento cms = new CobMaeSeguimiento();
                cms.setCmaeSeguimientoId(resultSet.getInt("C_MAE_SEGUIMIENTO_ID"));
                cms.setMaeInversion(inversion);
                // cob seguimiento
                CobSeguimiento cs = new CobSeguimiento();
                cs.setCcobSeguimientoId(resultSet.getInt("C_COB_SEGUIMIENTO_ID"));
                cs.setCobMaeSeguimiento(cms);
                // llamada
                CobLlamadas cl = new CobLlamadas();
                cl.setCcodLlamadaId(resultSet.getInt("C_COB_LLAMADAS_ID"));
                cl.setCcodDisposicionId(resultSet.getString("C_DISPOSICION_ID"));
                cl.setCtipoFamiliaId(resultSet.getString("C_TIPO_FAMILIA_ID"));
                cl.setDtipoFamilia(resultSet.getString("D_FAMILIA"));
                cl.setDdescripcion(resultSet.getString("D_DESCRIPCION"));
                cl.setcUsuarioAdd(resultSet.getString("C_USUARIO_ADD"));
                cl.setfUsuarioAdd(resultSet.getDate("F_USUARIO_ADD"));
                cl.setCobSeguimiento(cs);
                
                CobTablas tf = new CobTablas();
                tf.setDdescCorta(resultSet.getString("D_DESC_CORTA_F"));
                CobTablas ts = new CobTablas();
                ts.setDdescCorta(resultSet.getString("D_DESC_CORTA_S"));
                cl.setTipoFamilia(tf);
                cl.setTipoAccion(ts);
                
                lstLlamadas.add(cl);
                        
            }

        } catch (Exception e) {
            Logger.getLogger(CobLlamadasDao.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <i> CobLlamadasDao fetchAll " + LocalDateTime.now());
        return lstLlamadas;
    }
    
    @Override
    public ArrayList<CobLlamadas> fetchAllPhone(CobCdr oCobCdr) {
        //System.out.println(" <i> CobLlamadasDao fetchAll " + LocalDateTime.now());
        ArrayList<CobLlamadas> lstLlamadas = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_COB_LLAMADAS.SP_BUS_COB_LLAMADAS_TELE(?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters_busPhone(oCobCdr);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("segur");
            // execute procedure
            runSearch(oList, cn, cmd, sp);

            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                // fondo
                MaeFondo fondo = new MaeFondo();
                fondo.setCFondoId(resultSet.getString("C_FONDO_ID"));
                fondo.setDFondo(resultSet.getString("D_FONDO"));
                //inversion
                MaeInversion inversion = new MaeInversion();
                inversion.setcMaeInversionId(resultSet.getInt("C_MAE_INVERSION_ID"));
                inversion.setCInversion(resultSet.getString("C_INVERSION"));
                inversion.setMaeFondo(fondo);
                // mae seguimiento
                CobMaeSeguimiento cms = new CobMaeSeguimiento();
                cms.setCmaeSeguimientoId(resultSet.getInt("C_MAE_SEGUIMIENTO_ID"));
                cms.setMaeInversion(inversion);
                // cob seguimiento
                CobSeguimiento cs = new CobSeguimiento();
                cs.setCcobSeguimientoId(resultSet.getInt("C_COB_SEGUIMIENTO_ID"));
                cs.setCobMaeSeguimiento(cms);
                // llamada
                CobLlamadas cl = new CobLlamadas();
                cl.setCcodLlamadaId(resultSet.getInt("C_COB_SEG_DET_ID"));
                cl.setCcodDisposicionId(resultSet.getString("C_DISPOSICION_ID"));
                cl.setCtipoFamiliaId(resultSet.getString("C_TIPO_FAMILIA_ID"));
                cl.setDtipoFamilia(resultSet.getString("D_FAMILIA"));
                cl.setDdescripcion(resultSet.getString("D_DESCRIPCION"));
                cl.setcUsuarioAdd(resultSet.getString("C_USUARIO_ADD"));
                cl.setfUsuarioAdd(resultSet.getDate("F_USUARIO_ADD"));
                cl.setCobSeguimiento(cs);
                
                CobTablas tf = new CobTablas();
                tf.setDdescCorta(resultSet.getString("D_DESC_CORTA_F"));
                CobTablas ts = new CobTablas();
                ts.setDdescCorta(resultSet.getString("D_DESC_CORTA_S"));
                cl.setTipoFamilia(tf);
                cl.setTipoAccion(ts);
                
                lstLlamadas.add(cl);
                        
            }

        } catch (Exception e) {
            Logger.getLogger(CobLlamadasDao.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <i> CobLlamadasDao fetchAll " + LocalDateTime.now());
        return lstLlamadas;
    }
    
    
    
    private List<ParameterOracle> insertParameters(CobLlamadas oCobLlamadas){
        List<ParameterOracle> oListParam = new ArrayList<>();
        
        oListParam.add(new ParameterOracle("PI_C_COB_SEGUIMIENTO_ID", oCobLlamadas.getCobSeguimiento().getCcobSeguimientoId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_COB_LLAMADA", oCobLlamadas.getCcodLlamada(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_DISPOSICION_ID", oCobLlamadas.getCcodDisposicionId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TIPO_FAMILIA_ID", oCobLlamadas.getCtipoFamiliaId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_FAMILIA", oCobLlamadas.getDtipoFamilia(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_SITUACION_ID",oCobLlamadas.getCsituacionId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_DESCRIPCION", oCobLlamadas.getDdescripcion(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO_ADD", oCobLlamadas.getcUsuarioAdd(), OracleTypes.CHAR, ParameterDirection.Input));
                
        oListParam.add(new ParameterOracle("PO_C_COB_LLAMADAS_ID", 0, OracleTypes.NUMBER, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));
                
        return oListParam;
    }

    private List<ParameterOracle> listParameters_bus(CobLlamadas oCobLlamadas) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_FINICIO", oCobLlamadas.getfIniBusq(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_FFIN", oCobLlamadas.getfFinBusq(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO_ADD", oCobLlamadas.getcUsuarioAdd(), OracleTypes.CHAR, ParameterDirection.Input));
        
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

    private List<ParameterOracle> listParameters_busPhone(CobCdr oCobCdr) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_A_NUMERO", oCobCdr.getDst(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_F_USUARIO_ADD", oCobCdr.getCalldate(), OracleTypes.DATE, ParameterDirection.Input));
                
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

}
