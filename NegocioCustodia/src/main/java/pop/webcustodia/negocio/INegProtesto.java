/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.negocio;

import java.util.List;
import javax.ejb.Remote;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MovimientoProtesto;

/**
 *
 * @author RC433838
 */
@Remote
public interface INegProtesto {
    
    String removerProtesto(MovimientoProtesto oProtesto) throws Exception; 
    
    String removerRegistroBNB(MovimientoProtesto oProtesto) throws Exception; 
    
    String agregarProtesto(MovimientoProtesto oProtesto) throws Exception;
    
    List<MaeInversion> todosInversiones(MaeInversion oInversion) throws Exception;  
    
    List<MovimientoProtesto> todosProtestos(MovimientoProtesto oProtesto) throws Exception;
    
    List<MovimientoProtesto> todosRegistroBnb(MovimientoProtesto oProtesto) throws Exception;    
    
    List<MovimientoProtesto> todosMovimientosBnb(MovimientoProtesto oProtesto) throws Exception;   
    
    List<MovimientoProtesto> buscaInversion(MaeInversion oInversion) throws Exception;
    
    Integer exportarRegistroBNB(List<MovimientoProtesto> oProtesto) throws Exception; 
    
}
