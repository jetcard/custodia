/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.servicio;

import java.io.Serializable;
import java.util.List;
import pop.comun.dominio.MaeSesion;
import pop.webcustodia.iface.ISesionServ;
import pop.webcustodia.negocio.INegSesion;
import pop.webcustodia.util.Utilidades;

/**
 *
 * @author Jyoverar
 */
public class SesionServ implements ISesionServ, Serializable {

    INegSesion iNegSesionEJB;
    
    @Override
    public Integer insertar(MaeSesion oMaeSesion) throws Exception {
        iNegSesionEJB = (INegSesion) Utilidades.getEJBRemote("SessionSesion", INegSesion.class.getName());     
        return iNegSesionEJB.insertar(oMaeSesion);
    }

    @Override
    public void borrar(MaeSesion oMaeSesion) throws Exception {
        iNegSesionEJB = (INegSesion) Utilidades.getEJBRemote("SessionSesion", INegSesion.class.getName());
        iNegSesionEJB.borrar(oMaeSesion);
    }

    @Override
    public List<MaeSesion> listarSesion(MaeSesion oMaeSesion) throws Exception {
        iNegSesionEJB = (INegSesion) Utilidades.getEJBRemote("SessionSesion", INegSesion.class.getName());     
        return iNegSesionEJB.listarSesion(oMaeSesion);
    }
    
}
