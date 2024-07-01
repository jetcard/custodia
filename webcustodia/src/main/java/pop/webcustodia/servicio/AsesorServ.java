/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.servicio;

import java.util.List;
import pop.comun.dominio.MaeAsesor;
import pop.webcustodia.iface.IAsesorServ;
import pop.webcustodia.negocio.INegAsesor;
import pop.webcustodia.util.Utilidades;

/**
 *
 * @author EC23329
 */
public class AsesorServ implements IAsesorServ{
    INegAsesor iNegAsesorEJB;

    @Override
    public List<MaeAsesor> listarAsesor(MaeAsesor oMaeAsesor) throws Exception {
         iNegAsesorEJB = (INegAsesor) Utilidades.getEJBRemote("SessionAsesor", INegAsesor.class.getName());
        return iNegAsesorEJB.listarAsesor(oMaeAsesor);
    }
    
    @Override
    public List<MaeAsesor> listarAsesorUser(MaeAsesor oMaeAsesor) throws Exception {
         iNegAsesorEJB = (INegAsesor) Utilidades.getEJBRemote("SessionAsesor", INegAsesor.class.getName());
        return iNegAsesorEJB.listarAsesorUser(oMaeAsesor);
    }
}
