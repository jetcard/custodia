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
import pop.comun.dominio.CobTablas;
import pop.webcustodia.common.DBUtil;
import pop.webcustodia.common.ParameterDirection;
import pop.webcustodia.common.ParameterOracle;
import pop.webcustodia.iface.ICobTablasDao;

import pop.webcustodia.conn.Conexion;
import pop.webcustodia.dao.FactoryDao;
/**
 *
 * @author Jyoverar
 */
public class CobTablasDao extends DBUtil implements ICobTablasDao {
    
    private OracleConnection cn = null;

    public CobTablasDao() {

    }

    public CobTablasDao(OracleConnection cnx) {
        cn = cnx;
    }

    @Override
    public Integer insert(CobTablas oCobTablas) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(CobTablas oCobTablas) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(CobTablas oCobTablas) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<CobTablas> fetchAll(CobTablas oCobTablas) {
        
        //System.out.println(" <i> CobTablas fetch all " + LocalDateTime.now());
        
        ArrayList<CobTablas> lstTablas = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;

        try {
            // name of procedure
            String sp = "{call PKG_COB_TABLAS.SP_CARGAR_TABLA(?,?,?,?,?,?,?)}";
            // list of parameter
            System.out.println(sp);
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters(oCobTablas);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("ctdia");            
            // execute procedure
            runSearch(oList, cn, cmd, sp);

            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                //tablas
                CobTablas newCobTablas = new CobTablas();
                newCobTablas.setCtablaId(resultSet.getString("C_TABLA_ID"));
                newCobTablas.setCtablaDetId(resultSet.getString("C_TABLA_DET_ID"));
                newCobTablas.setDdescripcion(resultSet.getString("D_DESCRIPCION"));
                newCobTablas.setDdescCorta(resultSet.getString("D_DESC_CORTA"));
                newCobTablas.seteEstado(resultSet.getString("E_ESTADO"));
                newCobTablas.setPrioridad(resultSet.getInt("PRIORI"));
                //
                lstTablas.add(newCobTablas);
                
                
            }

        } catch (Exception e) {
            Logger.getLogger(CobTablas.class.getName()).log(Level.SEVERE, null, e);
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

        //System.out.println(" <f> CobTablas fetch all " + LocalDateTime.now());
        
        return lstTablas;
    }
    
    @Override
    public ArrayList<CobTablas> fetchEvaAll(CobTablas oCobTablas) {
        
        //System.out.println(" <i> CobTablas fetch all " + LocalDateTime.now());
        
        ArrayList<CobTablas> lstTablas = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;

        try {
            // name of procedure
            String sp = "{call PKG_COB_TABLAS.SP_CARGAR_TABLA(?,?,?,?,?,?,?)}";
            // list of parameter
            System.out.println(sp);
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters(oCobTablas);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("eva");            
            // execute procedure
            runSearch(oList, cn, cmd, sp);

            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                //tablas
                CobTablas newCobTablas = new CobTablas();
                newCobTablas.setCtablaId(resultSet.getString("C_TABLA_ID"));
                newCobTablas.setCtablaDetId(resultSet.getString("C_TABLA_DET_ID"));
                newCobTablas.setDdescripcion(resultSet.getString("D_DESCRIPCION"));
                newCobTablas.setDdescCorta(resultSet.getString("D_DESC_CORTA"));
                newCobTablas.seteEstado(resultSet.getString("E_ESTADO"));
                //newCobTablas.setPrioridad(resultSet.getInt("PRIORI"));
                //
                lstTablas.add(newCobTablas);
            }

        } catch (Exception e) {
            Logger.getLogger(CobTablas.class.getName()).log(Level.SEVERE, null, e);
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

        //System.out.println(" <f> CobTablas fetch all " + LocalDateTime.now());
        
        return lstTablas;
    }    
    
    @Override
    public ArrayList<CobTablas> fetchTablaDetalle(CobTablas oCobTablas) {
        
        //System.out.println(" <i> CobTablas fetch all " + LocalDateTime.now());
        
        ArrayList<CobTablas> lstTablas = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;

        try {
            // name of procedure
            String sp = "{call PKG_COB_TABLAS.SP_CARGAR_TABLADETALLE(?,?,?,?,?,?,?)}";
            // list of parameter
            System.out.println(sp);
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameterTabDet(oCobTablas);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("ctdia");            
            // execute procedure
            runSearch(oList, cn, cmd, sp);

            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                //tablas
                CobTablas newCobTablas = new CobTablas();
                newCobTablas.setCtablaId(resultSet.getString("C_TABLA_ID"));
                newCobTablas.setCtablaDetId(resultSet.getString("C_TABLA_DET_ID"));
                newCobTablas.setDdescripcion(resultSet.getString("D_DESCRIPCION"));
                newCobTablas.setDdescCorta(resultSet.getString("D_DESC_CORTA"));
                newCobTablas.seteEstado(resultSet.getString("E_ESTADO"));
                newCobTablas.setPrioridad(resultSet.getInt("PRIORI"));
                newCobTablas.setCriterio1(resultSet.getString("C_CRITERIO1"));
                newCobTablas.setCriterio2(resultSet.getString("C_CRITERIO2"));
                newCobTablas.setCriterio3(resultSet.getString("C_CRITERIO3"));
                newCobTablas.setCriterio4(resultSet.getInt("N_CRITERIO4"));
                newCobTablas.setCriterio5(resultSet.getInt("N_CRITERIO5"));
                newCobTablas.setCriterio6(resultSet.getInt("N_CRITERIO6"));
                newCobTablas.setCriterio7(resultSet.getDate("D_CRITERIO7"));
                newCobTablas.setCriterio8(resultSet.getDate("D_CRITERIO8"));
                newCobTablas.setCriterio9(resultSet.getDate("D_CRITERIO9"));
                //
                lstTablas.add(newCobTablas);
            }

        } catch (Exception e) {
            Logger.getLogger(CobTablas.class.getName()).log(Level.SEVERE, null, e);
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

        //System.out.println(" <f> CobTablas fetch all " + LocalDateTime.now());
        
        return lstTablas;
    }

    
    @Override
    public ArrayList<CobTablas> fetchDocDetTablas(CobTablas oCobTablas) {
        
        //System.out.println(" <i> CobTablas fetch all " + LocalDateTime.now());
        
        ArrayList<CobTablas> lstTablas = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;

        try {
            // name of procedure
            String sp = "{call PKG_COB_TABLAS.SP_CARGAR_TABLADOCDET(?,?,?,?,?)}";
            // list of parameter
            System.out.println("getid"+oCobTablas.getCtablaId());
            System.out.println("getpadre"+oCobTablas.getCodpadre());
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameterTabDocDet(oCobTablas);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("ctdia");            
            // execute procedure
            runSearch(oList, cn, cmd, sp);

            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                //tablas
                CobTablas newCobTablas = new CobTablas();
                newCobTablas.setCtablaId(resultSet.getString("C_TABLA_ID"));
                newCobTablas.setCtablaDetId(resultSet.getString("C_TABLA_DET_ID"));
                newCobTablas.setDdescripcion(resultSet.getString("D_DESCRIPCION"));
                newCobTablas.setDdescCorta(resultSet.getString("D_DESC_CORTA"));
                newCobTablas.seteEstado(resultSet.getString("E_ESTADO"));
                newCobTablas.setPrioridad(resultSet.getInt("PRIORI"));
                
                lstTablas.add(newCobTablas);
            }

        } catch (Exception e) {
            Logger.getLogger(CobTablas.class.getName()).log(Level.SEVERE, null, e);
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

        //System.out.println(" <f> CobTablas fetch all " + LocalDateTime.now());
        
        return lstTablas;
    }

    
    
    
    @Override
    public ArrayList<CobTablas> fetchSolicitante(CobTablas oCobTablas) {
        
        //System.out.println(" <i> CobTablas fetch all " + LocalDateTime.now());
        
        ArrayList<CobTablas> lstTablas = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;

        try {
            // name of procedure
            String sp = "{call PKG_INFO_AREAS.SP_BUSCA_USU_AREA(?,?,?,?)}";
            // list of parameter
            System.out.println(sp);
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParametersSol(oCobTablas);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("ctdia");            
            // execute procedure
            runSearch(oList, cn, cmd, sp);

            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                //tablas
                CobTablas newCobTablas = new CobTablas();
                newCobTablas.setCtablaId(resultSet.getString("USUARIO"));
                newCobTablas.setCtablaDetId(resultSet.getString("USUARIO"));
                newCobTablas.setDdescripcion(resultSet.getString("NOMBRES"));
                newCobTablas.setDdescCorta(resultSet.getString("NOMBRES"));
                //
                lstTablas.add(newCobTablas);
            }

        } catch (Exception e) {
            Logger.getLogger(CobTablas.class.getName()).log(Level.SEVERE, null, e);
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

        //System.out.println(" <f> CobTablas fetch all " + LocalDateTime.now());
        
        return lstTablas;
    }

    
    
    @Override
    public ArrayList<CobTablas> fetchAllInv(String lcfondo,String lsTchn, CobTablas oCobTablas) {
        
        System.out.println(" <i> CobTablas fetch all " +lcfondo+ lsTchn);
        
        ArrayList<CobTablas> lstTablas = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;

        try {
            // name of procedure
            String sp = "{call PKG_COB_TABLAS.SP_CARGAR_TABLA_INV(?,?,?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParametersInv(lcfondo,lsTchn,oCobTablas);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("ctdia");            
            // execute procedure
            runSearch(oList, cn, cmd, sp);

            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                //tablas
                CobTablas newCobTablas = new CobTablas();
                newCobTablas.setCtablaId(resultSet.getString("C_TABLA_ID"));
                newCobTablas.setCtablaDetId(resultSet.getString("C_TABLA_DET_ID"));
                newCobTablas.setDdescripcion(resultSet.getString("D_DESCRIPCION"));
                newCobTablas.setDdescCorta(resultSet.getString("D_DESC_CORTA"));
                newCobTablas.seteEstado(resultSet.getString("E_ESTADO"));
                //
                lstTablas.add(newCobTablas);
            }

        } catch (Exception e) {
            Logger.getLogger(CobTablas.class.getName()).log(Level.SEVERE, null, e);
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

        //System.out.println(" <f> CobTablas fetch all " + LocalDateTime.now());
        
        return lstTablas;
    }

    
     @Override
    public ArrayList<CobTablas> fetchAllInvf2(String lcfondo,String lsTchn, CobTablas oCobTablas) {
                  
        
        System.out.println(" <i> CobTablas fetch all " +lcfondo+ lsTchn);
        
        ArrayList<CobTablas> lstTablas = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;

        try {
            // name of procedure
            String sp = "{call PKG_COB_TABLAS.SP_CARGAR_TABLA_INVf2(?,?,?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParametersInv(lcfondo,lsTchn,oCobTablas);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("ctdia");            
            // execute procedure
             
            runSearch(oList, cn, cmd, sp);

            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                //tablas
                System.out.println(resultSet.getString("D_DESCRIPCION"));
                CobTablas newCobTablas = new CobTablas();
                newCobTablas.setCtablaId(resultSet.getString("C_TABLA_ID"));
                newCobTablas.setCtablaDetId(resultSet.getString("C_TABLA_DET_ID"));
                newCobTablas.setDdescripcion(resultSet.getString("D_DESCRIPCION"));
                newCobTablas.setDdescCorta(resultSet.getString("D_DESC_CORTA"));
                newCobTablas.seteEstado(resultSet.getString("E_ESTADO"));
                newCobTablas.setFlg_detalle(resultSet.getInt("FLAG_DETALLE"));
                //
                lstTablas.add(newCobTablas);
            }

        } catch (Exception e) {
            Logger.getLogger(CobTablas.class.getName()).log(Level.SEVERE, null, e);
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

        //System.out.println(" <f> CobTablas fetch all " + LocalDateTime.now());
        
        return lstTablas;
    }

    
    
    private List<ParameterOracle> listParameters(CobTablas oCobTablas) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        
        oListParam.add(new ParameterOracle("PI_C_TABLA_ID", oCobTablas.getCtablaId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TABLA_DET_ID", oCobTablas.getCtablaDetId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_DESCRIPCION", oCobTablas.getDdescripcion(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_E_ESTADO", oCobTablas.geteEstado(), OracleTypes.CHAR, ParameterDirection.Input));
        
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }
    
    
    private List<ParameterOracle> listParameterTabDet(CobTablas oCobTablas) {
        System.out.println("oCobTablas = "+oCobTablas);
        List<ParameterOracle> oListParam = new ArrayList<>();
        System.out.println("000000000000000000000000000000000 listParameterTabDet 000000000000000000000000000000000000000");
        oListParam.add(new ParameterOracle("PI_C_TABLA_ID", oCobTablas.getCtablaId(), OracleTypes.CHAR, ParameterDirection.Input));
        System.out.println("oCobTablas.getCtablaId() = "+oCobTablas.getCtablaId());
        oListParam.add(new ParameterOracle("PI_C_TABLA_DET_ID", oCobTablas.getCtablaDetId(), OracleTypes.CHAR, ParameterDirection.Input));
        System.out.println("oCobTablas.getCtablaDetId() = "+oCobTablas.getCtablaDetId());
        oListParam.add(new ParameterOracle("PI_C_CRITERIO1", oCobTablas.getCriterio1(), OracleTypes.CHAR, ParameterDirection.Input));
        System.out.println("oCobTablas.getCriterio1()= "+oCobTablas.getCriterio1());
        oListParam.add(new ParameterOracle("PI_C_CRITERIO2", oCobTablas.getCriterio2(), OracleTypes.CHAR, ParameterDirection.Input));
        System.out.println("oCobTablas.getCriterio2() = "+oCobTablas.getCriterio2());
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }    
    
    
    private List<ParameterOracle> listParameterTabDocDet(CobTablas oCobTablas) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        System.out.println("informa"+ oCobTablas.getCodpadre());
        oListParam.add(new ParameterOracle("PI_C_TABLA_ID", oCobTablas.getCtablaId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_PADRE_ID", oCobTablas.getCodpadre(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }    
    
    
    private List<ParameterOracle> listParametersSol(CobTablas oCobTablas) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        oListParam.add(new ParameterOracle("PI_C_TABLA_DET_ID", oCobTablas.getCtablaDetId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }
    
     private List<ParameterOracle> listParametersInv(String lcfondo,String lsTchn, CobTablas oCobTablas) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", lcfondo, OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_INVERSION", lsTchn, OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TABLA_ID", oCobTablas.getCtablaId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TABLA_DET_ID", oCobTablas.getCtablaDetId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_DESCRIPCION", oCobTablas.getDdescripcion(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_E_ESTADO", oCobTablas.geteEstado(), OracleTypes.CHAR, ParameterDirection.Input));
        
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
    public ArrayList<CobTablas> fetchAllCartas(CobTablas oCobTablas) {
        
        //System.out.println(" <i> CobTablas fetch all " + LocalDateTime.now());
        
        ArrayList<CobTablas> lstTablas = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;

        try {
            // name of procedure
            String sp = "{call PKG_COB_TABLAS.SP_TABLA_CARTAS(?,?,?,?,?)}";
            // list of parameter
            System.out.println(sp);
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParametersCartas(oCobTablas);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("ctdia");            
            // execute procedure
            runSearch(oList, cn, cmd, sp);

            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                //tablas
                CobTablas newCobTablas = new CobTablas();
                newCobTablas.setCtablaId(resultSet.getString("C_TABLA_ID"));
                newCobTablas.setCtablaDetId(resultSet.getString("C_TABLA_DET_ID"));
                newCobTablas.setDdescripcion(resultSet.getString("D_DESCRIPCION"));
                newCobTablas.setDdescCorta(resultSet.getString("D_DESCRIPCION"));
                //
                lstTablas.add(newCobTablas);
            }

        } catch (Exception e) {
            Logger.getLogger(CobTablas.class.getName()).log(Level.SEVERE, null, e);
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

        //System.out.println(" <f> CobTablas fetch all " + LocalDateTime.now());
        
        return lstTablas;
    }

    @Override
    public ArrayList<CobTablas> fetchAllCartasDet(CobTablas oCobTablas) {
        
        //System.out.println(" <i> CobTablas fetch all " + LocalDateTime.now());
        
        ArrayList<CobTablas> lstTablas = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;

        try {
            // name of procedure
            String sp = "{call PKG_COB_TABLAS.SP_TABLA_CARTAS_DET(?,?,?,?,?)}";
            // list of parameter
            System.out.println(sp);
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParametersCartas(oCobTablas);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("ctdia");            
            // execute procedure
            runSearch(oList, cn, cmd, sp);

            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                //tablas
                CobTablas newCobTablas = new CobTablas();
                newCobTablas.setCtablaId(resultSet.getString("C_TABLA_ID"));
                newCobTablas.setCtablaDetId(resultSet.getString("C_TABLA_DET_ID"));
                newCobTablas.setDdescripcion(resultSet.getString("D_DESCRIPCION"));
                newCobTablas.setDdescCorta(resultSet.getString("D_DESCRIPCION"));
                //
                lstTablas.add(newCobTablas);
            }

        } catch (Exception e) {
            Logger.getLogger(CobTablas.class.getName()).log(Level.SEVERE, null, e);
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

        //System.out.println(" <f> CobTablas fetch all " + LocalDateTime.now());
        
        return lstTablas;
    }
    
     private List<ParameterOracle> listParametersCartas(CobTablas oCobTablas) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        oListParam.add(new ParameterOracle("PI_C_TABLA_ID", oCobTablas.getCtablaId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TABLA_DET_ID", oCobTablas.getCtablaDetId(), OracleTypes.CHAR, ParameterDirection.Input));
        
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }
     
    @Override
    public ArrayList<CobTablas> fetchAllTablasGenerales(CobTablas oCobTablas) {
        ArrayList<CobTablas> lstTablas = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;

        try {
            String sp = "{call PKG_COB_TABLAS.SP_TABLA_GENERAL(?,?,?,?)}";
            System.out.println(sp);
            List<ParameterOracle> oList = new ArrayList<>();
            oList = listParametersGenerales(oCobTablas);
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("ctdia");            
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();
            while (resultSet.next()) {
                CobTablas newCobTablas = new CobTablas();
                newCobTablas.setCtablaId(resultSet.getString("C_TABLA_ID"));
                newCobTablas.setCtablaDetId(resultSet.getString("C_TABLA_DET_ID"));
                newCobTablas.setDdescripcion(resultSet.getString("D_DESCRIPCION"));
                newCobTablas.setDdescCorta(resultSet.getString("D_DESC_CORTA"));
                newCobTablas.seteEstado(resultSet.getString("E_ESTADO"));
                newCobTablas.setPrioridad(resultSet.getInt("PRIORI"));
                lstTablas.add(newCobTablas);
            }
        } catch (Exception e) {
            Logger.getLogger(CobTablas.class.getName()).log(Level.SEVERE, null, e);
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
        return lstTablas;
    }
    
    private List<ParameterOracle> listParametersGenerales(CobTablas oCobTablas) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        oListParam.add(new ParameterOracle("PI_D_DESCRIPCION", oCobTablas.getDdescripcion(), OracleTypes.CHAR, ParameterDirection.Input));
        
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        return oListParam;
    }

    
    @Override
    public Integer insertarCobTablas(CobTablas oCobTablas) {
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        Integer newID = 0;
        try {
            String sp = "{call PKG_COB_TABLAS.SP_INSERTAR_TABLA_GENERAL(?,?,?,?,?,?)}";
            List<ParameterOracle> oList = new ArrayList<>();
            oList = INSParameters_CobTablas(oCobTablas);
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("ctdia");
            runSP(oList, cn, cmd, sp);
            newID = getOutputParameter("PO_I_PROCESO").getParameterInt();
        } catch (Exception e) {
            Logger.getLogger(CobTablas.class.getName()).log(Level.SEVERE, null, e);
            newID=1;
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
        return newID;
    }

    private List<ParameterOracle> INSParameters_CobTablas(CobTablas oCobTablas) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        oListParam.add(new ParameterOracle("PC_DESCRIPCION", oCobTablas.getDdescripcion().trim(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_DESC_CORTA", oCobTablas.getDdescCorta().trim(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_CUSU_INS", oCobTablas.getcUsuarioAdd(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_I_PROCESO", 0, OracleTypes.NUMBER, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        return oListParam;
    }
    
   @Override
    public Integer modificarCobTablas(CobTablas oCobTablas) {
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        Integer newID = 0;
        try {
            String sp = "{call PKG_COB_TABLAS.SP_MODIFICAR_TABLA_GENERAL(?,?,?,?,?,?,?)}";
            List<ParameterOracle> oList = new ArrayList<>();
            oList = MODIParameters_CobTablas(oCobTablas);
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("ctdia");
            runSP(oList, cn, cmd, sp);
            newID = getOutputParameter("PO_I_PROCESO").getParameterInt();
        } catch (Exception e) {
            Logger.getLogger(CobTablas.class.getName()).log(Level.SEVERE, null, e);
            newID=1;
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
        return newID;
    }

    private List<ParameterOracle> MODIParameters_CobTablas(CobTablas oCobTablas) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        oListParam.add(new ParameterOracle("PC_C_TABLA_ID", oCobTablas.getCtablaId().trim(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_DESCRIPCION", oCobTablas.getDdescripcion().trim(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_DESC_CORTA", oCobTablas.getDdescCorta().trim(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_CUSU_INS", oCobTablas.getcUsuarioAdd(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_I_PROCESO", 0, OracleTypes.NUMBER, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        return oListParam;
    }
    
    @Override
    public ArrayList<CobTablas> fetchAllDetalle(CobTablas oCobTablas) {
        ArrayList<CobTablas> lstTablas = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            String sp = "{call PKG_COB_TABLAS.SP_TABLA_DETALLE(?,?,?,?)}";
            System.out.println(sp);
            List<ParameterOracle> oList = new ArrayList<>();
            oList = listParametersAllDetalle(oCobTablas);
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("ctdia");            
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();
            while (resultSet.next()) {
                CobTablas newCobTablas = new CobTablas();
                newCobTablas.setCtablaId(resultSet.getString("C_TABLA_ID"));
                newCobTablas.setCtablaDetId(resultSet.getString("C_TABLA_DET_ID"));
                newCobTablas.setDdescripcion(resultSet.getString("D_DESCRIPCION"));
                newCobTablas.setDdescCorta(resultSet.getString("D_DESC_CORTA"));
                newCobTablas.seteEstado(resultSet.getString("E_ESTADO"));
                newCobTablas.setPrioridad(resultSet.getInt("PRIORI"));
                newCobTablas.setCodpadre(resultSet.getString("C_TABLA_DET_PADRE"));
                newCobTablas.setFlg_detalle(resultSet.getInt("FLAG_DETALLE"));
                System.out.println("CTABLAPADER"+resultSet.getString("C_TABLA_DET_PADRE"));
                System.out.println("FLAGDETALLE"+resultSet.getString("FLAG_DETALLE"));
                lstTablas.add(newCobTablas);
            }
        } catch (Exception e) {
            Logger.getLogger(CobTablas.class.getName()).log(Level.SEVERE, null, e);
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
        return lstTablas;
    }
    
    private List<ParameterOracle> listParametersAllDetalle(CobTablas oCobTablas) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        oListParam.add(new ParameterOracle("PI_C_TABLA_ID", oCobTablas.getCtablaId(), OracleTypes.CHAR, ParameterDirection.Input));
        
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        return oListParam;
    }
    
    @Override
    public Integer insertarCobDetalle(CobTablas oCobTablas) {
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        Integer newID = 0;
        try {
            String sp = "{call PKG_COB_TABLAS.SP_INSERTAR_TABLA_DETALLE(?,?,?,?,?,?,?,?,?,?)}";
            List<ParameterOracle> oList = new ArrayList<>();
            oList = INSParameters_CobDetalle(oCobTablas);
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("ctdia");
            runSP(oList, cn, cmd, sp);
            newID = getOutputParameter("PO_I_PROCESO").getParameterInt();
        } catch (Exception e) {
            Logger.getLogger(CobTablas.class.getName()).log(Level.SEVERE, null, e);
            newID=1;
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
        return newID;
    }

    private List<ParameterOracle> INSParameters_CobDetalle(CobTablas oCobTablas) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        oListParam.add(new ParameterOracle("PC_C_TABLA_ID", oCobTablas.getCtablaId().trim(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_DESCRIPCION", oCobTablas.getDdescripcion().trim(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_C_PADRE", oCobTablas.getCodpadre(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_DESC_CORTA", oCobTablas.getDdescCorta().trim(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_ESTADO", oCobTablas.geteEstado().trim(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_FLAGDETALLE", oCobTablas.getFlg_detalle(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_CUSU_INS", oCobTablas.getcUsuarioAdd(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_I_PROCESO", 0, OracleTypes.NUMBER, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        return oListParam;
    }
    
   @Override
    public Integer modificarCobDetalle(CobTablas oCobTablas) {
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        Integer newID = 0;
        try {
            String sp = "{call PKG_COB_TABLAS.SP_MODIFICAR_DETALLE(?,?,?,?,?,?,?,?,?,?,?)}";
            List<ParameterOracle> oList = new ArrayList<>();
            oList = MODIParameters_CobDetalle(oCobTablas);
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("ctdia");
            runSP(oList, cn, cmd, sp);
            newID = getOutputParameter("PO_I_PROCESO").getParameterInt();
        } catch (Exception e) {
            Logger.getLogger(CobTablas.class.getName()).log(Level.SEVERE, null, e);
            newID=1;
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
        return newID;
    }

    private List<ParameterOracle> MODIParameters_CobDetalle(CobTablas oCobTablas) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        oListParam.add(new ParameterOracle("PC_C_TABLA_ID", oCobTablas.getCtablaId().trim(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_C_TABLA_DET_ID", oCobTablas.getCtablaDetId().trim(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_DESCRIPCION", oCobTablas.getDdescripcion().trim(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_DESC_CORTA", oCobTablas.getDdescCorta().trim(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_C_PADRE", oCobTablas.getCodpadre().trim(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_ESTADO", oCobTablas.geteEstado().trim(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_FLAGDETALLE", oCobTablas.getFlg_detalle(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PC_CUSU_INS", oCobTablas.getcUsuarioAdd(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_I_PROCESO", 0, OracleTypes.NUMBER, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        return oListParam;
    }
    
    @Override
    public ArrayList<CobTablas> fetchAllDocusCustodia(CobTablas oCobTablas,String tchn) {
        
        //System.out.println(" <i> CobTablas fetch all " + LocalDateTime.now());
        
        ArrayList<CobTablas> lstTablas = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;

        try {
            // name of procedure
            String sp = "{call PKG_COB_TABLAS.SP_LISTAR_DOCUS_CUSTODIA(?,?,?,?,?)}";
            // list of parameter
            System.out.println("estees"+tchn);
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParametersDocusCustodia(oCobTablas,tchn);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("ctdia");            
            // execute procedure
            runSearch(oList, cn, cmd, sp);

            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                //tablas
                CobTablas newCobTablas = new CobTablas();
                newCobTablas.setCtablaId(resultSet.getString("C_TABLA_ID"));
                newCobTablas.setCtablaDetId(resultSet.getString("C_TABLA_DET_ID"));
                newCobTablas.setDdescripcion(resultSet.getString("D_DESCRIPCION"));
                newCobTablas.setDdescCorta(resultSet.getString("D_DESC_CORTA"));
                newCobTablas.setCodpadre(resultSet.getString("C_TABLA_DET_PADRE"));
                newCobTablas.seteEstado(resultSet.getString("E_ESTADO"));
                newCobTablas.setPrioridad(resultSet.getInt("PRIORI"));
                //
                lstTablas.add(newCobTablas);
            }

        } catch (Exception e) {
            Logger.getLogger(CobTablas.class.getName()).log(Level.SEVERE, null, e);
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
        
        return lstTablas;
    }
        
    private List<ParameterOracle> listParametersDocusCustodia(CobTablas oCobTablas,String tchn) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        
        oListParam.add(new ParameterOracle("PI_C_TABLA_ID", oCobTablas.getCtablaId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_TCHN", tchn, OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }        
}

