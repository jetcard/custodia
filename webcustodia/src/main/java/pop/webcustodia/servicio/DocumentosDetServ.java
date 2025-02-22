/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.servicio;

import java.io.Serializable;
import java.util.List;
import pop.comun.dominio.TabDocumentosDet;
import pop.webcustodia.iface.IDocumentosDetServ;
import pop.webcustodia.negocio.INegTabDocumentosDet;
import pop.webcustodia.util.Utilidades;

/**
 *
 * @author Jyoverar
 */
public class DocumentosDetServ implements IDocumentosDetServ, Serializable {

    INegTabDocumentosDet iNegDocumentosDetEJB;

    @Override
    public Integer insertar(TabDocumentosDet oTabDocumentosDet) throws Exception {
        iNegDocumentosDetEJB = (INegTabDocumentosDet) Utilidades.getEJBRemote("SessionDocumentosDet", INegTabDocumentosDet.class.getName());
        return iNegDocumentosDetEJB.insertar(oTabDocumentosDet);
    }

    @Override
    public List<TabDocumentosDet> listarDocumentosDet(TabDocumentosDet oTabDocumentosDet) throws Exception {
        iNegDocumentosDetEJB = (INegTabDocumentosDet) Utilidades.getEJBRemote("SessionDocumentosDet", INegTabDocumentosDet.class.getName());
        return iNegDocumentosDetEJB.listarDocumentosDet(oTabDocumentosDet);
    }

    @Override
    public TabDocumentosDet buscarSolo(TabDocumentosDet oTabDocumentosDet) throws Exception {
        iNegDocumentosDetEJB = (INegTabDocumentosDet) Utilidades.getEJBRemote("SessionDocumentosDet", INegTabDocumentosDet.class.getName());
        return iNegDocumentosDetEJB.buscarSolo(oTabDocumentosDet);
    }

}
