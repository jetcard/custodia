/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.servicio;

import java.io.Serializable;
import java.util.List;
import pop.comun.dominio.CobTablas;
import pop.webcustodia.iface.ITablasServ;
import pop.webcustodia.negocio.INegTablas;
import pop.webcustodia.util.Utilidades;

/**
 *
 * @author Jyoverar
 */
public class TablasServ implements ITablasServ, Serializable {
    
    INegTablas iNegTablasEJB;

    @Override
    public List<CobTablas> listarTablas(CobTablas oCobTablas) throws Exception {
        iNegTablasEJB = (INegTablas) Utilidades.getEJBRemote("SessionTablas", INegTablas.class.getName());
        return iNegTablasEJB.listarTablas(oCobTablas);
    }
    
    @Override
    public List<CobTablas> listarTablasEva(CobTablas oCobTablas) throws Exception {
        iNegTablasEJB = (INegTablas) Utilidades.getEJBRemote("SessionTablas", INegTablas.class.getName());
        return iNegTablasEJB.listarTablasEva(oCobTablas);
    }    
    
    @Override
    public List<CobTablas> listarTablaDetalle(CobTablas oCobTablas) throws Exception {
        iNegTablasEJB = (INegTablas) Utilidades.getEJBRemote("SessionTablas", INegTablas.class.getName());
        return iNegTablasEJB.listarTablaDetalle(oCobTablas);
    }    
    
    @Override
    public List<CobTablas> listarDocDetTablas(CobTablas oCobTablas) throws Exception {
        iNegTablasEJB = (INegTablas) Utilidades.getEJBRemote("SessionTablas", INegTablas.class.getName());
        return iNegTablasEJB.listarDocDetTablas(oCobTablas);
    }    
    
    
    @Override
    public List<CobTablas> listarTablasInv(String lcfondo,String lsTchn,CobTablas oCobTablas) throws Exception {
        iNegTablasEJB = (INegTablas) Utilidades.getEJBRemote("SessionTablas", INegTablas.class.getName());
        return iNegTablasEJB.listarTablasInv(lcfondo,lsTchn,oCobTablas);
    }

    @Override
    public List<CobTablas> listarTablasInvf2(String lcfondo,String lsTchn,CobTablas oCobTablas) throws Exception {
        iNegTablasEJB = (INegTablas) Utilidades.getEJBRemote("SessionTablas", INegTablas.class.getName());
        return iNegTablasEJB.listarTablasInvf2(lcfondo,lsTchn,oCobTablas);
    }
   
    @Override
    public List<CobTablas> listarSolicitante(CobTablas oCobTablas) throws Exception {
        iNegTablasEJB = (INegTablas) Utilidades.getEJBRemote("SessionTablas", INegTablas.class.getName());
        return iNegTablasEJB.listarSolicitante(oCobTablas);
    }

    @Override
    public List<CobTablas> listarTablasCartas(CobTablas oCobTablas) throws Exception {
        iNegTablasEJB = (INegTablas) Utilidades.getEJBRemote("SessionTablas", INegTablas.class.getName());
        return iNegTablasEJB.listarTablasCartas(oCobTablas);
    }
  @Override
    public List<CobTablas> listarTablasCartasDet(CobTablas oCobTablas) throws Exception {
        iNegTablasEJB = (INegTablas) Utilidades.getEJBRemote("SessionTablas", INegTablas.class.getName());
        return iNegTablasEJB.listarTablasCartasDet(oCobTablas);
    }

    @Override
    public List<CobTablas> listarTablasDocusCustodia(CobTablas oCobTablas,String tchn) throws Exception {
        iNegTablasEJB = (INegTablas) Utilidades.getEJBRemote("SessionTablas", INegTablas.class.getName());
        return iNegTablasEJB.listarTablasDocusCustodia(oCobTablas,tchn);
    }    
}
