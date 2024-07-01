/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.servicio;

import java.io.Serializable;
import java.util.List;
import pop.comun.dominio.CobTablas;
import pop.webcustodia.iface.ICobTablasServ;
import pop.webcustodia.negocio.INegCobTablas;
import pop.webcustodia.util.Utilidades;

/**
 *
 * @author HH38092
 */
public class CobTablasServ implements ICobTablasServ, Serializable {
    
    INegCobTablas iNegCobTablasEJB;

    @Override
    public List<CobTablas> listarTablasGenerales(CobTablas oCobTablas) throws Exception {
        iNegCobTablasEJB = (INegCobTablas) Utilidades.getEJBRemote("SessionCobTablas", INegCobTablas.class.getName());
        return iNegCobTablasEJB.listarTablas(oCobTablas);
    }
    
    @Override
    public Integer insertarCobTablas(CobTablas oCobTablas) throws Exception {
        iNegCobTablasEJB = (INegCobTablas) Utilidades.getEJBRemote("SessionCobTablas", INegCobTablas.class.getName());     
        return iNegCobTablasEJB.insertarCobTablas(oCobTablas);
    }
    
    @Override
    public Integer modificarCobTablas(CobTablas oCobTablas) throws Exception {
        iNegCobTablasEJB = (INegCobTablas) Utilidades.getEJBRemote("SessionCobTablas", INegCobTablas.class.getName());     
        return iNegCobTablasEJB.modificarCobTablas(oCobTablas);
    }
    
    @Override
    public List<CobTablas> listarDetalle(CobTablas oCobTablas) throws Exception {
        iNegCobTablasEJB = (INegCobTablas) Utilidades.getEJBRemote("SessionCobTablas", INegCobTablas.class.getName());
        return iNegCobTablasEJB.listarDetalle(oCobTablas);
    }
    
    @Override
    public Integer insertarCobDetalle(CobTablas oCobTablas) throws Exception {
        iNegCobTablasEJB = (INegCobTablas) Utilidades.getEJBRemote("SessionCobTablas", INegCobTablas.class.getName());     
        return iNegCobTablasEJB.insertarCobDetalle(oCobTablas);
    }
    
    @Override
    public Integer modificarCobDetalle(CobTablas oCobTablas) throws Exception {
        iNegCobTablasEJB = (INegCobTablas) Utilidades.getEJBRemote("SessionCobTablas", INegCobTablas.class.getName());     
        return iNegCobTablasEJB.modificarCobDetalle(oCobTablas);
    }    
}