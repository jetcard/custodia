/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.servicio;

import java.io.Serializable;
import java.util.List;
import pop.comun.dominio.TabArchivo;
import pop.webcustodia.iface.IArchivoServ;
import pop.webcustodia.negocio.INegArchivo;
import pop.webcustodia.util.Utilidades;

/**
 *
 * @author Jyoverar
 */
public class ArchivoServ implements IArchivoServ, Serializable {
    
     INegArchivo iNegArchivoEJB;


    @Override
    public Integer insertar(TabArchivo oTabArchivo) throws Exception {
        iNegArchivoEJB = (INegArchivo) Utilidades.getEJBRemote("SessionArchivo", INegArchivo.class.getName());     
        return iNegArchivoEJB.insertar(oTabArchivo);
    }

    @Override
    public List<TabArchivo> listar(TabArchivo oTabArchivo) throws Exception {
        iNegArchivoEJB = (INegArchivo) Utilidades.getEJBRemote("SessionArchivo", INegArchivo.class.getName());     
        return iNegArchivoEJB.listar(oTabArchivo);
    }
    
}
