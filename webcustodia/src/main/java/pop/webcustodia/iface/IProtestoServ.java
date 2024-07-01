/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.iface;

import java.util.List;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MovimientoProtesto;

/**
 *
 * @author RC433838
 */
public interface IProtestoServ {
    
    String remProtesto(MovimientoProtesto oProtesto) throws Exception;   
    
    String remRegistroBNB(MovimientoProtesto oProtesto) throws Exception;  
    
    String addProtesto(MovimientoProtesto oProtesto) throws Exception; 
    
    List<MaeInversion> allInversiones(MaeInversion oInversion) throws Exception;    
    
    List<MovimientoProtesto> allProtestos(MovimientoProtesto oProtesto) throws Exception;   
    
    List<MovimientoProtesto> buscaInversion(MaeInversion oInversion) throws Exception;
    
    List<MovimientoProtesto> allRegistroBNB(MovimientoProtesto oProtesto) throws Exception;   
    
    List<MovimientoProtesto> allMovimientosBNB(MovimientoProtesto oProtesto) throws Exception;  
    
    Integer exportarBNB(List<MovimientoProtesto> oProtesto) throws Exception; 
    
}
