/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.iface;

import java.util.List;
import pop.comun.dominio.CobTablas;

/**
 *
 * @author HH38092
 */
public interface ICobTablasServ {
      List<CobTablas> listarTablasGenerales(CobTablas oCobTablas) throws Exception;
      Integer insertarCobTablas(CobTablas oCobTablas) throws Exception;
      Integer modificarCobTablas(CobTablas oCobTablas) throws Exception;
      List<CobTablas> listarDetalle(CobTablas oCobTablas) throws Exception;
      Integer insertarCobDetalle(CobTablas oCobTablas) throws Exception;
      Integer modificarCobDetalle(CobTablas oCobTablas) throws Exception;
}
