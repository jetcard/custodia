/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.servicio;

import java.io.Serializable;
import java.util.List;
import pop.comun.dominio.MaeDocOtros;
import pop.comun.dominio.MaeEmail;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MaeReporte;

import pop.webcustodia.iface.IInversionServ;
import pop.webcustodia.negocio.INegInversion;
import pop.webcustodia.util.Utilidades;

/**
 *
 * @author Jyoverar
 */
public class InversionServ implements IInversionServ, Serializable {

    INegInversion iNegInversionEJB;

    @Override
    public List<MaeInversion> listarDeudorMov(MaeInversion oMaeInversion) throws Exception {
        iNegInversionEJB = (INegInversion) Utilidades.getEJBRemote("SessionInversion", INegInversion.class.getName());
        return iNegInversionEJB.listarDeudorMov(oMaeInversion);
    }
    
    @Override
    public List<MaeInversion> listarDeudorf2(MaeInversion oMaeInversion) throws Exception {
        iNegInversionEJB = (INegInversion) Utilidades.getEJBRemote("SessionInversion", INegInversion.class.getName());
        return iNegInversionEJB.listarDeudorf2(oMaeInversion);
    }
    @Override
    public List<MaeInversion> listarDeudor(MaeInversion oMaeInversion) throws Exception {
        iNegInversionEJB = (INegInversion) Utilidades.getEJBRemote("SessionInversion", INegInversion.class.getName());
        return iNegInversionEJB.listarDeudor(oMaeInversion);
    }
    
    
    @Override
    public List<MaeInversion> listarDeudorConsulta(MaeInversion oMaeInversion) throws Exception {
        iNegInversionEJB = (INegInversion) Utilidades.getEJBRemote("SessionInversion", INegInversion.class.getName());
        return iNegInversionEJB.listarDeudorCons(oMaeInversion);
    }
 
       
    @Override
    public List<MaeInversion> listarConsultaxDoc(MaeInversion oMaeInversion) throws Exception {
        iNegInversionEJB = (INegInversion) Utilidades.getEJBRemote("SessionInversion", INegInversion.class.getName());
        return iNegInversionEJB.listarConsultaxDoc(oMaeInversion);
    }
    
    
    @Override
    public List<MaeInversion> listarConsultaxMov(MaeInversion oMaeInversion) throws Exception {
        iNegInversionEJB = (INegInversion) Utilidades.getEJBRemote("SessionInversion", INegInversion.class.getName());
        return iNegInversionEJB.listarConsultaxMov(oMaeInversion);
    }
    
    
    @Override
    public List<MaeInversion> listarCustodia(MaeInversion oMaeInversion) throws Exception {
        iNegInversionEJB = (INegInversion) Utilidades.getEJBRemote("SessionInversion", INegInversion.class.getName());
        return iNegInversionEJB.listarCustodia(oMaeInversion);
    }

    @Override
    public List<MaeInversion> listarCustodiaf2(MaeInversion oMaeInversion) throws Exception {
        iNegInversionEJB = (INegInversion) Utilidades.getEJBRemote("SessionInversion", INegInversion.class.getName());
        return iNegInversionEJB.listarCustodiaf2(oMaeInversion);
    }
    
    @Override
    public List<MaeInversion> listarCustodiaMovi(MaeInversion oMaeInversion) throws Exception {
        iNegInversionEJB = (INegInversion) Utilidades.getEJBRemote("SessionInversion", INegInversion.class.getName());
        return iNegInversionEJB.listarCustodiaMovi(oMaeInversion);
    }
    
    @Override
    public Integer eliminarDocCustodia(MaeInversion oMaeInversion) throws Exception {
        iNegInversionEJB = (INegInversion) Utilidades.getEJBRemote("SessionInversion", INegInversion.class.getName());
        return iNegInversionEJB.eliminarDocCustodia(oMaeInversion);
    }
    
    @Override
    public List<MaeInversion> listarCustodiaxDoc(MaeInversion oMaeInversion) throws Exception {
        iNegInversionEJB = (INegInversion) Utilidades.getEJBRemote("SessionInversion", INegInversion.class.getName());
        return iNegInversionEJB.listarCustodiaxDoc(oMaeInversion);
    }
    
    
    
    
    
    @Override
    public List<MaeInversion> listaSolicitante(String cArea) throws Exception {
        iNegInversionEJB = (INegInversion) Utilidades.getEJBRemote("SessionInversion", INegInversion.class.getName());
        return iNegInversionEJB.listaSolicitante(cArea);
    }
    
    
    @Override
    public Integer insertarCustodia(MaeInversion oMaeInversion) throws Exception {
        iNegInversionEJB = (INegInversion) Utilidades.getEJBRemote("SessionInversion", INegInversion.class.getName());
        return iNegInversionEJB.insertarCustodia(oMaeInversion);
    }

    @Override
    public Integer insertarCustodiaf2(MaeInversion oMaeInversion) throws Exception {
        iNegInversionEJB = (INegInversion) Utilidades.getEJBRemote("SessionInversion", INegInversion.class.getName());
        return iNegInversionEJB.insertarCustodiaf2(oMaeInversion);
    }


    
    @Override
    public Integer insertarCustodiaMov(MaeInversion oMaeInversion) throws Exception {
        iNegInversionEJB = (INegInversion) Utilidades.getEJBRemote("SessionInversion", INegInversion.class.getName());
        return iNegInversionEJB.insertarCustodiaMov(oMaeInversion);
    }

    @Override
    public Integer validaCorreo(MaeInversion oMaeInversion) throws Exception {
        iNegInversionEJB = (INegInversion) Utilidades.getEJBRemote("SessionInversion", INegInversion.class.getName());
        return iNegInversionEJB.validaCorreo(oMaeInversion);
    }

    
    @Override
    public Integer eliminarCustodia(String pFondo, String pInv) throws Exception {
        iNegInversionEJB = (INegInversion) Utilidades.getEJBRemote("SessionInversion", INegInversion.class.getName());
        return iNegInversionEJB.deleteCustodia(pFondo, pInv);
    }

    @Override
    public List<MaeEmail> ListarEmail() throws Exception {
        iNegInversionEJB = (INegInversion) Utilidades.getEJBRemote("SessionInversion", INegInversion.class.getName());
        return iNegInversionEJB.ListarEmail();
    }

  
    
    
    @Override
    public String  imprimeMOV(MaeInversion oMaeInversion) throws Exception {
        iNegInversionEJB = (INegInversion) Utilidades.getEJBRemote("SessionInversion", INegInversion.class.getName());
        return iNegInversionEJB.imprimeMOV(oMaeInversion);
    }

    @Override
    public List<MaeInversion> listarDocumentosMovi(MaeInversion oMaeInversion) throws Exception {
         iNegInversionEJB = (INegInversion) Utilidades.getEJBRemote("SessionInversion", INegInversion.class.getName());
        return iNegInversionEJB.listarDocumentosMovi(oMaeInversion);
    }
    
     @Override
    public List<MaeInversion> listarDetalleCustodia(MaeInversion oMaeInversion) throws Exception {
         iNegInversionEJB = (INegInversion) Utilidades.getEJBRemote("SessionInversion", INegInversion.class.getName());
        return iNegInversionEJB.listarDetalleCustodia(oMaeInversion);
    }
    
    
    @Override
    public Integer insertarDocCustodia(MaeDocOtros oMaeDocOtros) throws Exception {
        iNegInversionEJB = (INegInversion) Utilidades.getEJBRemote("SessionInversion", INegInversion.class.getName());
        return iNegInversionEJB.insertarDocCustodia(oMaeDocOtros);
    }    

    @Override
    public Integer grabarDocDetCustodia(MaeInversion oMaeInversion) throws Exception {
        iNegInversionEJB = (INegInversion) Utilidades.getEJBRemote("SessionInversion", INegInversion.class.getName());
        return iNegInversionEJB.grabarDocDetCustodia(oMaeInversion);
    }
    
}
