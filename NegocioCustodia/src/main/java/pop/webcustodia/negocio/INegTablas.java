/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.negocio;

import java.util.List;
import javax.ejb.Remote;
import pop.comun.dominio.CobTablas;

/**
 *
 * @author Jyoverar
 */
@Remote
public interface INegTablas {
    
    List<CobTablas> listarTablas(CobTablas oCobTablas) throws Exception;
    List<CobTablas> listarTablasEva(CobTablas oCobTablas) throws Exception;
    List<CobTablas> listarTablaDetalle(CobTablas oCobTablas) throws Exception;
    List<CobTablas> listarDocDetTablas(CobTablas oCobTablas) throws Exception;
    
    List<CobTablas> listarTablasInv(String lcfondo,String lsTchn,CobTablas oCobTablas) throws Exception;
    List<CobTablas> listarTablasInvf2(String lcfondo,String lsTchn,CobTablas oCobTablas) throws Exception;
    List<CobTablas> listarSolicitante(CobTablas oCobTablas) throws Exception;
    List<CobTablas> listarTablasCartas(CobTablas oCobTablas) throws Exception;
    List<CobTablas> listarTablasCartasDet(CobTablas oCobTablas) throws Exception;
    List<CobTablas> listarTablasDocusCustodia(CobTablas oCobTablas,String tchn) throws Exception;
    
}
