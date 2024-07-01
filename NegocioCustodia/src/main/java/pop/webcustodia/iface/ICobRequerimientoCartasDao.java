/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.iface;

import java.util.List;
import pop.comun.dominio.CobRequerimientoCartas;
import pop.comun.dominio.CobRequerimientoCriterios;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MovimientoCartas;


/**
 *
 * @author RC433838
 */
public interface ICobRequerimientoCartasDao {
    
    String removerRequerimiento(CobRequerimientoCartas oRequerimiento); 

    String cambiaEstadoReq(CobRequerimientoCartas oRequerimiento); 
        
    String addRequerimiento(CobRequerimientoCriterios oCriterio); 

    List<MaeInversion> allInversiones(MaeInversion oInversion);
    
    List<CobRequerimientoCartas> allRequerimientos(CobRequerimientoCartas oRequerimiento);
    
    List<CobRequerimientoCriterios> findReqSugerido();
    
    List<CobRequerimientoCriterios> findCriterios(CobRequerimientoCriterios oRequerimiento);
    
    List<MovimientoCartas> findCartas(MaeInversion oInversion);  
       
    
}
