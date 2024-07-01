/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.servicio;

import java.io.Serializable;
import java.util.List;
import pop.comun.dominio.MaeCuotaPago;
import pop.webcustodia.iface.ICuotaPagoServ;
import pop.webcustodia.negocio.INegCuotaPago;
import pop.webcustodia.util.Utilidades;

/**
 *
 * @author Jyoverar
 */
public class CuotaPagoServ implements ICuotaPagoServ, Serializable {

    INegCuotaPago iNegCuotaPagoEJB;

    @Override
    public List<MaeCuotaPago> listarCuotaPago(MaeCuotaPago oMaeCuotaPago) throws Exception {
        iNegCuotaPagoEJB = (INegCuotaPago) Utilidades.getEJBRemote("SessionCuotaPago", INegCuotaPago.class.getName());
        return iNegCuotaPagoEJB.listarCuotaPago(oMaeCuotaPago);
    }

    @Override
    public MaeCuotaPago calcularCuotaPagoFuturo(MaeCuotaPago oMaeCuotaPago) throws Exception {
        iNegCuotaPagoEJB = (INegCuotaPago) Utilidades.getEJBRemote("SessionCuotaPago", INegCuotaPago.class.getName());
        return iNegCuotaPagoEJB.calcularCuotaPagoFuturo(oMaeCuotaPago);
    }
    
}
