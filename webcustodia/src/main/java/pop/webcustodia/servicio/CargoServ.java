/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.servicio;

import java.io.Serializable;
import pop.comun.dominio.MaeCargo;
import pop.webcustodia.iface.ICargoServ;
import pop.webcustodia.negocio.INegCargo;
import pop.webcustodia.util.Utilidades;

/**
 *
 * @author Jyoverar
 */
public class CargoServ implements ICargoServ, Serializable{
    
    INegCargo iNegCargoEJB;

    @Override
    public MaeCargo calcularCargoAtrasado(MaeCargo oMaeCargo) throws Exception {
        iNegCargoEJB = (INegCargo) Utilidades.getEJBRemote("SessionCargo", INegCargo.class.getName());
        return iNegCargoEJB.calcularCargoAtrasado(oMaeCargo);
    }
    
}
