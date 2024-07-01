/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.servicio;

import java.io.Serializable;
import java.util.List;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MovimientoProtesto;
import pop.webcustodia.iface.IProtestoServ;
import pop.webcustodia.negocio.INegProtesto;
import pop.webcustodia.util.Utilidades;

/**
 *
 * @author RC433838
 */
public class ProtestoServ implements IProtestoServ, Serializable {
    
    INegProtesto iNegProtestoEJB;

    @Override
    public String remProtesto(MovimientoProtesto oProtesto) throws Exception {
        iNegProtestoEJB = (INegProtesto) Utilidades.getEJBRemote("SessionProtesto", INegProtesto.class.getName());
        return iNegProtestoEJB.removerProtesto(oProtesto);
    }
    
    @Override
    public String remRegistroBNB(MovimientoProtesto oProtesto) throws Exception {
        iNegProtestoEJB = (INegProtesto) Utilidades.getEJBRemote("SessionProtesto", INegProtesto.class.getName());
        return iNegProtestoEJB.removerRegistroBNB(oProtesto);
    }    

    @Override
    public String addProtesto(MovimientoProtesto oProtesto) throws Exception {
        iNegProtestoEJB = (INegProtesto) Utilidades.getEJBRemote("SessionProtesto", INegProtesto.class.getName());
        return iNegProtestoEJB.agregarProtesto(oProtesto);
    }

    @Override
    public List<MaeInversion> allInversiones(MaeInversion oInversion) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MovimientoProtesto> allProtestos(MovimientoProtesto oProtesto) throws Exception {
        List<MovimientoProtesto> lista;        
        iNegProtestoEJB = (INegProtesto) Utilidades.getEJBRemote("SessionProtesto", INegProtesto.class.getName());        
        lista = iNegProtestoEJB.todosProtestos(oProtesto);        
        return lista;
    }
    
   
    
    @Override
    public List<MovimientoProtesto> buscaInversion(MaeInversion oInversion) throws Exception {
        List<MovimientoProtesto> lista;        
        iNegProtestoEJB = (INegProtesto) Utilidades.getEJBRemote("SessionProtesto", INegProtesto.class.getName());        
        lista = iNegProtestoEJB.buscaInversion(oInversion);        
        return lista;
    }    

    @Override
    public List<MovimientoProtesto> allRegistroBNB(MovimientoProtesto oProtesto) throws Exception {
        List<MovimientoProtesto> lista;        
        
        iNegProtestoEJB = (INegProtesto) Utilidades.getEJBRemote("SessionProtesto", INegProtesto.class.getName());        
        lista = iNegProtestoEJB.todosRegistroBnb(oProtesto);        
        return lista;
    }
    
    @Override
    public List<MovimientoProtesto> allMovimientosBNB(MovimientoProtesto oProtesto) throws Exception {
        List<MovimientoProtesto> lista;        
        
        iNegProtestoEJB = (INegProtesto) Utilidades.getEJBRemote("SessionProtesto", INegProtesto.class.getName());        
        lista = iNegProtestoEJB.todosMovimientosBnb(oProtesto);        
        return lista;
    }    
    
    @Override
    public Integer exportarBNB(List<MovimientoProtesto> oProtesto) throws Exception {
        iNegProtestoEJB = (INegProtesto) Utilidades.getEJBRemote("SessionProtesto", INegProtesto.class.getName());
        return iNegProtestoEJB.exportarRegistroBNB(oProtesto);
    }        
    

}
