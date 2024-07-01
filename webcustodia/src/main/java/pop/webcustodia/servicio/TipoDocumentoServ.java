/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.servicio;

import java.io.Serializable;
import java.util.List;
import pop.comun.dominio.TabTipoDocumento;
import pop.webcustodia.iface.ITipoDocumentoServ;
import pop.webcustodia.negocio.INegTabTipoDocumento;
import pop.webcustodia.util.Utilidades;

/**
 *
 * @author Jyoverar
 */
public class TipoDocumentoServ implements ITipoDocumentoServ, Serializable {

    INegTabTipoDocumento iNegTabTipoDocumentoEJB;
    
    @Override
    public List<TabTipoDocumento> listarTipoDocumento(TabTipoDocumento oTabTipoDocumento) throws Exception {
        iNegTabTipoDocumentoEJB = (INegTabTipoDocumento) Utilidades.getEJBRemote("SessionTipoDocumento", INegTabTipoDocumento.class.getName());
        return iNegTabTipoDocumentoEJB.listarTipoDocumento(oTabTipoDocumento);
    }
    
}
