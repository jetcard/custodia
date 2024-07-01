/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.servicio;

import java.util.List;
import pop.comun.dominio.MaeInmueble;
import pop.comun.dominio.MaePersonaInmueble;
import pop.webcustodia.iface.IInmuebleServ;
import pop.webcustodia.negocio.INegInmueble;
import pop.webcustodia.util.Utilidades;

/**
 *
 * @author Jyoverar
 */
public class InmuebleServ implements IInmuebleServ {

    INegInmueble iNegInmuebleEJB;

    @Override
    public Integer insertar(MaeInmueble oMaeInmueble) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean actualizar(MaeInmueble oMaeInmueble) throws Exception {
        iNegInmuebleEJB = (INegInmueble) Utilidades.getEJBRemote("SessionInmueble", INegInmueble.class.getName());
        return iNegInmuebleEJB.actualizar(oMaeInmueble);
    }

    @Override
    public void borrar(MaeInmueble oMaeInmueble) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MaeInmueble> listarEmail(MaeInmueble oMaeInmueble) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MaePersonaInmueble> listarPersonaInmueble(MaeInmueble oMaeInmueble) throws Exception {
        iNegInmuebleEJB = (INegInmueble) Utilidades.getEJBRemote("SessionInmueble", INegInmueble.class.getName());
        return iNegInmuebleEJB.listarPersonaInmueble(oMaeInmueble);
    }

}
