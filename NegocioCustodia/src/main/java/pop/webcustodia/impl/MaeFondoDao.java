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

import pop.comun.dominio.MaeFondo;
import pop.webcustodia.common.DBUtil;
import pop.webcustodia.common.ParameterDirection;
import pop.webcustodia.common.ParameterOracle;
import pop.webcustodia.iface.IMaeFondoDao;

import pop.webcustodia.conn.Conexion;
import pop.webcustodia.dao.FactoryDao;
/**
 *
 * @author Jyoverar
 */
public class MaeFondoDao extends DBUtil implements IMaeFondoDao {

    private OracleConnection cn = null;

    public MaeFondoDao() {

    }

    public MaeFondoDao(OracleConnection cnx) {
        cn = cnx;
    }

    @Override
    public Integer insert(MaeFondo oMaeFondo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(MaeFondo oMaeFondo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(MaeFondo oMaeFondo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<MaeFondo> fetchAll(MaeFondo oMaeFondo) {
        ArrayList<MaeFondo> lstFondos = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;

        System.out.println(":-------:----: " );
        
        try {
            // name of procedure
            String sp = "{call PKG_CUSTODIA.SP_BUS_MAEFONDO(?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters(oMaeFondo);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("ctdia");
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                //tablas
                MaeFondo newMaeFondo = new MaeFondo();
                newMaeFondo.setCFondoId(resultSet.getString("C_FONDO_ID"));
                newMaeFondo.setDFondo(resultSet.getString("D_FONDO"));
                newMaeFondo.setADireccion(resultSet.getString("A_DIRECCION"));
                newMaeFondo.seteEstado(resultSet.getString("E_ESTADO"));
                //
                lstFondos.add(newMaeFondo);
            }

        } catch (Exception e) {
            Logger.getLogger(MaeFondoDao.class.getName()).log(Level.SEVERE, null, e);
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
        return lstFondos;
    }

    @Override
    public ArrayList<MaeFondo> fetchDashboard(MaeFondo oMaeFondo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
 

    public OracleConnection getCn() {
        return cn;
    }

    public void setCn(OracleConnection cn) {
        this.cn = cn;
    }

    private List<ParameterOracle> listParameters(MaeFondo oMaeFondo) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        
        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oMaeFondo.getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_FONDO", oMaeFondo.getDFondo(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_A_DIRECCION", oMaeFondo.getADireccion(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_E_ESTADO", oMaeFondo.geteEstado(), OracleTypes.CHAR, ParameterDirection.Input));
        
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }
    
    
}
