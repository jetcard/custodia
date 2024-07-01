/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.negocio;

import java.util.List;
import javax.ejb.Remote;
import pop.comun.dominio.CobRequerimientoCartas;
import pop.comun.dominio.CobRequerimientoCriterios;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MovimientoCartas;

/**
 *
 * @author RC433838
 */
@Remote
public interface INegCobRequerimiento {
    
    String removerRequerimiento(CobRequerimientoCartas oRquerimiento) throws Exception; 
    
    String cambiaEstadoReq(CobRequerimientoCartas oRquerimiento) throws Exception;     
    
    String addRequerimiento(CobRequerimientoCriterios oCriterio) throws Exception;
    
    List<MaeInversion> allInversiones(MaeInversion oInversion) throws Exception;  
    
    List<CobRequerimientoCartas> allRequerimientos(CobRequerimientoCartas oRquerimiento) throws Exception;
    
    List<CobRequerimientoCriterios> findCriterios(CobRequerimientoCriterios oCriterios) throws Exception;
    
    List<CobRequerimientoCriterios> findReqSugerido() throws Exception;
    
    List<MovimientoCartas> findCartas(MaeInversion oInversion) throws Exception;    
    
}
