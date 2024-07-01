/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.ejb;

import java.util.List;
import javax.ejb.Stateless;
import oracle.jdbc.OracleConnection;
import pop.webcustodia.conn.Conexion;
import pop.webcustodia.dao.FactoryDao;
import pop.webcustodia.negocio.INegDashboard;
/**
 *
 * @author Jhon Yovera
 */
@Stateless(mappedName = "ejbDashboard")
public class SessionDashboard  implements INegDashboard {

     FactoryDao ofDao = new FactoryDao();

    @Override
    public List<List<Object>> listarDeposito() throws Exception {
        List<List<Object>> oDepoList = null;

        oDepoList = ofDao.getMaeDashboard().fetchAll();        
        return oDepoList;
    }
    
  

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public Boolean cargarDatosFPH() throws Exception{
        Boolean resultado;
       
        resultado = ofDao.getMaeDashboard().loadDataFPH();
        return resultado;
    }

    @Override
    public Boolean cargarDatosFEM() throws Exception{
        Boolean resultado;
        
        resultado = ofDao.getMaeDashboard().loadDataFEM();
        return resultado;
    }

    @Override
    public Boolean cargarDatosFMY() throws Exception{
        Boolean resultado;
        
        resultado = ofDao.getMaeDashboard().loadDataFMY();
        return resultado;
    }

    @Override
    public Boolean cargarDatosFPO() throws Exception{
        Boolean resultado;
        
        resultado = ofDao.getMaeDashboard().loadDataFPO();
        return resultado;
    }

    
}
