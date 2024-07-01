/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleResultSet;
import oracle.jdbc.OracleTypes;

import pop.comun.dominio.MaeFechaPor;
import pop.comun.dominio.MaeFondo;
import pop.webcustodia.common.DBUtil;
import pop.webcustodia.common.ParameterDirection;
import pop.webcustodia.common.ParameterOracle;
import pop.webcustodia.iface.IMaeFondoDao;

import pop.webcustodia.conn.Conexion;
import pop.webcustodia.dao.FactoryDao;
import pop.webcustodia.iface.IMaeFechaPorDao;
/**
 *
 * @author Jyoverar
 */
public class MaeFechaPorDao extends DBUtil implements IMaeFechaPorDao {

    private OracleConnection cn = null;

    public MaeFechaPorDao() {

    }

    public MaeFechaPorDao(OracleConnection cnx) {
        cn = cnx;
    }

    @Override
    public ArrayList<MaeFechaPor> fetchAllFecha() {
        ArrayList<MaeFechaPor> lstMaeFechaPor = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;

        try {
            // name of procedure
            String sp = "{call PKG_CUSTODIA.SP_BUS_TIPO_FECHA(?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters();
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("ctdia");
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                //tablas
                MaeFechaPor newMaeFechaPor = new MaeFechaPor();
                newMaeFechaPor.setCMaeFechaPorId(resultSet.getString("C_TABLA_DET_ID"));
                newMaeFechaPor.setDMaeFechaPorId(resultSet.getString("D_DESCRIPCION"));
                lstMaeFechaPor.add(newMaeFechaPor);
            }

        } catch (Exception e) {
            Logger.getLogger(MaeFechaPorDao.class.getName()).log(Level.SEVERE, null, e);
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
        return lstMaeFechaPor;
    }

   
    public OracleConnection getCn() {
        return cn;
    }

    public void setCn(OracleConnection cn) {
        this.cn = cn;
    }

    private List<ParameterOracle> listParameters() {
        List<ParameterOracle> oListParam = new ArrayList<>();
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }
    
  
    
}
