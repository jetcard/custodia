/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.servicio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import pop.comun.dominio.MaeEmail;
import pop.comun.dominio.MaeInversion;
import pop.webcustodia.iface.IGeneradorDocumentoServ;
import pop.webcustodia.negocio.INegGeneradorDocumento;
import pop.webcustodia.util.Utilidades;

/**
 *
 * @author Jyoverar
 */
public class GeneradorDocumentoServ implements IGeneradorDocumentoServ, Serializable {

    INegGeneradorDocumento iNegGeneradorDocumentoEJB;

    
    @Override
    public List<MaeInversion> listarPropietarios(MaeInversion oMaeInversion) throws Exception {
         iNegGeneradorDocumentoEJB = (INegGeneradorDocumento) Utilidades.getEJBRemote("SessionGeneradorDocumento", INegGeneradorDocumento.class.getName());
        return iNegGeneradorDocumentoEJB.listarPropietarios(oMaeInversion);
    }
    
    @Override
    public List<MaeInversion> listarClientes(MaeInversion oMaeInversion) throws Exception {
         iNegGeneradorDocumentoEJB = (INegGeneradorDocumento) Utilidades.getEJBRemote("SessionGeneradorDocumento", INegGeneradorDocumento.class.getName());
        return iNegGeneradorDocumentoEJB.listarClientes(oMaeInversion);
    }    

    @Override
    public List<MaeInversion> listarAllPropietarios(MaeInversion oMaeInversion) throws Exception {
         iNegGeneradorDocumentoEJB = (INegGeneradorDocumento) Utilidades.getEJBRemote("SessionGeneradorDocumento", INegGeneradorDocumento.class.getName());
        return iNegGeneradorDocumentoEJB.listarAllPropietarios(oMaeInversion);
    }    
    
    @Override
    public List<MaeInversion> listarHistoricoCarta(MaeInversion oMaeInversion) throws Exception {
         iNegGeneradorDocumentoEJB = (INegGeneradorDocumento) Utilidades.getEJBRemote("SessionGeneradorDocumento", INegGeneradorDocumento.class.getName());
        return iNegGeneradorDocumentoEJB.listarHistoricoCarta(oMaeInversion);
    }

    @Override
    public Integer anularGeneraDoc(MaeInversion oMaeInversion) throws Exception {
        iNegGeneradorDocumentoEJB = (INegGeneradorDocumento) Utilidades.getEJBRemote("SessionGeneradorDocumento", INegGeneradorDocumento.class.getName());
        return iNegGeneradorDocumentoEJB.anulaGeneraDoc(oMaeInversion);
    }
    
    @Override
    public Integer insertarGeneraDoc(MaeInversion oMaeInversion) throws Exception {
        iNegGeneradorDocumentoEJB = (INegGeneradorDocumento) Utilidades.getEJBRemote("SessionGeneradorDocumento", INegGeneradorDocumento.class.getName());
        return iNegGeneradorDocumentoEJB.insertarGeneraDoc(oMaeInversion);
    }
    
    @Override
    public Integer insertarConstLiqui(MaeInversion oMaeInversion) throws Exception {
        iNegGeneradorDocumentoEJB = (INegGeneradorDocumento) Utilidades.getEJBRemote("SessionGeneradorDocumento", INegGeneradorDocumento.class.getName());
        return iNegGeneradorDocumentoEJB.insertarConstancias(oMaeInversion);
    }  
    
    @Override
    public MaeInversion anulaConstancia(MaeInversion oMaeInversion) throws Exception {
        iNegGeneradorDocumentoEJB = (INegGeneradorDocumento) Utilidades.getEJBRemote("SessionGeneradorDocumento", INegGeneradorDocumento.class.getName());
        return iNegGeneradorDocumentoEJB.anulaConstancia(oMaeInversion);
    }      

    
    @Override
    public Integer generaCarta1(MaeInversion maeInversion) throws Exception {
        iNegGeneradorDocumentoEJB = (INegGeneradorDocumento) Utilidades.getEJBRemote("SessionGeneradorDocumento", INegGeneradorDocumento.class.getName());
        return iNegGeneradorDocumentoEJB.generaCarta1(maeInversion);
    }
    
    @Override
    public Integer generaConstancia(MaeInversion maeInversion) throws Exception {
        iNegGeneradorDocumentoEJB = (INegGeneradorDocumento) Utilidades.getEJBRemote("SessionGeneradorDocumento", INegGeneradorDocumento.class.getName());
        return iNegGeneradorDocumentoEJB.generaConstancia(maeInversion);
    }    


    @Override
    public List<MaeInversion> listarCartas(MaeInversion oMaeInversion) throws Exception {
         iNegGeneradorDocumentoEJB = (INegGeneradorDocumento) Utilidades.getEJBRemote("SessionGeneradorDocumento", INegGeneradorDocumento.class.getName());
        return iNegGeneradorDocumentoEJB.listarCartas(oMaeInversion);
    }
    
    @Override
    public List<MaeInversion> listarConstancias(MaeInversion oMaeInversion) throws Exception {
         iNegGeneradorDocumentoEJB = (INegGeneradorDocumento) Utilidades.getEJBRemote("SessionGeneradorDocumento", INegGeneradorDocumento.class.getName());
        return iNegGeneradorDocumentoEJB.listarConstancias(oMaeInversion);
    }    

    @Override
    public Integer notificarXEmail(MaeInversion oMaeInversion) throws Exception {
         iNegGeneradorDocumentoEJB = (INegGeneradorDocumento) Utilidades.getEJBRemote("SessionGeneradorDocumento", INegGeneradorDocumento.class.getName());
        return iNegGeneradorDocumentoEJB.notificarXEmail(oMaeInversion);
    } 

    @Override
    public ArrayList<MaeEmail> fetchAll() throws Exception {
         iNegGeneradorDocumentoEJB = (INegGeneradorDocumento) Utilidades.getEJBRemote("SessionGeneradorDocumento", INegGeneradorDocumento.class.getName());
        return iNegGeneradorDocumentoEJB.fetchAll();
    }

}
