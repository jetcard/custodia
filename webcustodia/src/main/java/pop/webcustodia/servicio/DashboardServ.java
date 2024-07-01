/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.servicio;

import java.io.Serializable;
import java.util.List;
import pop.webcustodia.iface.IDashboardServ;
import pop.webcustodia.negocio.INegDashboard;
import pop.webcustodia.util.Utilidades;

/**
 *
 * @author Jhon Yovera
 */
public class DashboardServ  implements IDashboardServ, Serializable {
    
    INegDashboard iNegDashboardEJB;

    @Override
    public List<List<Object>> listarDeposito() throws Exception {
        iNegDashboardEJB = (INegDashboard) Utilidades.getEJBRemote("SessionDashboard", INegDashboard.class.getName());     
        return iNegDashboardEJB.listarDeposito();
    }

    
    @Override
    public Boolean cargarDatosFPH() throws Exception {
        iNegDashboardEJB = (INegDashboard) Utilidades.getEJBRemote("SessionDashboard", INegDashboard.class.getName());     
        return iNegDashboardEJB.cargarDatosFPH();
    }

    @Override
    public Boolean cargarDatosFEM() throws Exception {
        iNegDashboardEJB = (INegDashboard) Utilidades.getEJBRemote("SessionDashboard", INegDashboard.class.getName());     
        return iNegDashboardEJB.cargarDatosFEM();
    }

    @Override
    public Boolean cargarDatosFMY() throws Exception {
        iNegDashboardEJB = (INegDashboard) Utilidades.getEJBRemote("SessionDashboard", INegDashboard.class.getName());     
        return iNegDashboardEJB.cargarDatosFMY();
    }

    @Override
    public Boolean cargarDatosFPO() throws Exception {
        iNegDashboardEJB = (INegDashboard) Utilidades.getEJBRemote("SessionDashboard", INegDashboard.class.getName());     
        return iNegDashboardEJB.cargarDatosFPO();
    }
    
    
    
    
}
