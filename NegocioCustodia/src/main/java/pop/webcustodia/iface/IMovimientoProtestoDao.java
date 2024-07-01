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
public interface IMovimientoProtestoDao {
    
    String remProtesto(MovimientoProtesto oProtesto); 
    String remRegistroBNB(MovimientoProtesto oProtesto);     
    
    String addProtesto(MovimientoProtesto oProtesto); 

    List<MaeInversion> allInversiones(MaeInversion oInversion);
    
    List<MovimientoProtesto> allProtestos(MovimientoProtesto oProtesto); 
    
    List<MovimientoProtesto> allRegistroBnb(MovimientoProtesto oProtesto);     
    List<MovimientoProtesto> allMovimientosBnb(MovimientoProtesto oProtesto);     
    
    List<MovimientoProtesto> buscaInversion(MaeInversion oInversion);
    
    
}
