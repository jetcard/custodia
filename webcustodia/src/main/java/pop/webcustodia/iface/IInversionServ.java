/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.iface;

import java.util.List;
import pop.comun.dominio.MaeDocOtros;
import pop.comun.dominio.MaeEmail;
import pop.comun.dominio.MaeInversion;


/**
 *
 * @author Jyoverar
 */
public interface IInversionServ {
    String  imprimeMOV(MaeInversion oMaeInversion) throws Exception;
    List<MaeInversion> listarDeudorMov(MaeInversion oMaeInversion) throws Exception; 
    List<MaeInversion> listarDeudor(MaeInversion oMaeInversion) throws Exception;
    List<MaeInversion> listarDeudorf2(MaeInversion oMaeInversion) throws Exception;
    List<MaeInversion> listarDeudorConsulta(MaeInversion oMaeInversion) throws Exception;
    List<MaeInversion> listarConsultaxDoc(MaeInversion oMaeInversion) throws Exception;
    List<MaeInversion> listarConsultaxMov(MaeInversion oMaeInversion) throws Exception;
    List<MaeInversion> listarCustodia(MaeInversion oMaeInversion) throws Exception;
    List<MaeInversion> listarCustodiaMovi(MaeInversion oMaeInversion) throws Exception;
    List<MaeInversion> listarCustodiaxDoc(MaeInversion oMaeInversion) throws Exception;
    List<MaeInversion> listaSolicitante(String cArea) throws Exception;
    List<MaeInversion> listarCustodiaf2(MaeInversion oMaeInversion) throws Exception;
    List<MaeInversion> listarDetalleCustodia(MaeInversion oMaeInversion) throws Exception;
    List<MaeInversion> listarDocumentosMovi(MaeInversion oMaeInversion) throws Exception;
    
    Integer insertarCustodia(MaeInversion oMaeInversion) throws Exception;
    Integer insertarCustodiaf2(MaeInversion oMaeInversion) throws Exception;
    Integer eliminarDocCustodia(MaeInversion oMaeInversion) throws Exception;
    Integer insertarCustodiaMov(MaeInversion oMaeInversion) throws Exception;
    Integer validaCorreo(MaeInversion oMaeInversion) throws Exception;
    Integer eliminarCustodia(String pFondo,String pInv) throws Exception;
    List<MaeEmail> ListarEmail() throws Exception;
    Integer insertarDocCustodia(MaeDocOtros oMaeDocOtros) throws Exception;
    Integer grabarDocDetCustodia(MaeInversion oMaeInversion) throws Exception;
    
    
}
