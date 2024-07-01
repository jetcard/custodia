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
 * @author HH38092
 */
@Remote
public interface INegCobTablas {
 
     List<CobTablas> listarTablas(CobTablas oCobTablas) throws Exception;
     Integer insertarCobTablas(CobTablas oCobTablas) throws Exception;
     Integer modificarCobTablas(CobTablas oCobTablas) throws Exception;
     List<CobTablas> listarDetalle(CobTablas oCobTablas) throws Exception;
     Integer insertarCobDetalle(CobTablas oCobTablas) throws Exception;
     Integer modificarCobDetalle(CobTablas oCobTablas) throws Exception;
}
