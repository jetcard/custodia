/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.servicio;

import java.io.Serializable;
import java.util.List;
import pop.comun.dominio.TabTipoDocumentoEstado;
import pop.webcustodia.iface.ITipoDocumentoEstadoServ;
import pop.webcustodia.negocio.INegTabTipoDocumentoEstado;
import pop.webcustodia.util.Utilidades;

/**
 *
 * @author Jyoverar
 */
public class TipoDocumentoEstadoServ implements ITipoDocumentoEstadoServ, Serializable{

    INegTabTipoDocumentoEstado iNegTabTipoDocumentoEstadoEJB;
    
    @Override
    public List<TabTipoDocumentoEstado> listarTipoDocumentoEstado(TabTipoDocumentoEstado oTabTipoDocumentoEstado) throws Exception {
        iNegTabTipoDocumentoEstadoEJB = (INegTabTipoDocumentoEstado) Utilidades.getEJBRemote("SessionTipoDocumentoEstado", INegTabTipoDocumentoEstado.class.getName());
        return iNegTabTipoDocumentoEstadoEJB.listarTipoDocumentoEstado(oTabTipoDocumentoEstado);
    }
    
}
