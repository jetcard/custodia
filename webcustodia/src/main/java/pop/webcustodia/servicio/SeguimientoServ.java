/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.servicio;

import java.io.Serializable;
import java.util.List;
import pop.comun.dominio.CobMaeSeguimiento;
import pop.webcustodia.iface.ISeguimientoServ;
import pop.webcustodia.negocio.INegCobMaeSeguimiento;
import pop.webcustodia.util.Utilidades;

/**
 *
 * @author Jyoverar
 */
public class SeguimientoServ implements ISeguimientoServ, Serializable {

    INegCobMaeSeguimiento iNegSeguimientoEJB;
    
    @Override
    public CobMaeSeguimiento listarSeguimiento(CobMaeSeguimiento oCobMaeSeguimiento) throws Exception {
        iNegSeguimientoEJB = (INegCobMaeSeguimiento) Utilidades.getEJBRemote("SessionSeguimiento", INegCobMaeSeguimiento.class.getName());
        return iNegSeguimientoEJB.listarSeguimiento(oCobMaeSeguimiento);
    }

    @Override
    public Integer insertar(CobMaeSeguimiento oCobMaeSeguimiento) throws Exception {
        iNegSeguimientoEJB = (INegCobMaeSeguimiento) Utilidades.getEJBRemote("SessionSeguimiento", INegCobMaeSeguimiento.class.getName());
        return iNegSeguimientoEJB.insertar(oCobMaeSeguimiento);
    }

    @Override
    public CobMaeSeguimiento buscar(CobMaeSeguimiento oCobMaeSeguimiento) throws Exception {
        iNegSeguimientoEJB = (INegCobMaeSeguimiento) Utilidades.getEJBRemote("SessionSeguimiento", INegCobMaeSeguimiento.class.getName());
        return iNegSeguimientoEJB.buscar(oCobMaeSeguimiento);
    }

    @Override
    public List<CobMaeSeguimiento> listarSeguimientoLlamada(CobMaeSeguimiento oCobMaeSeguimiento) throws Exception {
        iNegSeguimientoEJB = (INegCobMaeSeguimiento) Utilidades.getEJBRemote("SessionSeguimiento", INegCobMaeSeguimiento.class.getName());
        return iNegSeguimientoEJB.listarSeguimientoLlamada(oCobMaeSeguimiento);
    }
    
}
