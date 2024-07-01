/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.servicio;

import java.util.List;
import pop.comun.dominio.MaeFondo;

import pop.comun.dominio.MaeFechaPor;
import pop.webcustodia.iface.IFondoServ;
import pop.webcustodia.negocio.INegFondo;
import pop.webcustodia.util.Utilidades;

/**
 *
 * @author Jyoverar
 */
public class FondoServ implements IFondoServ {

    INegFondo iNegFondoEJB;

    @Override
    public List<MaeFondo> listarFondos(MaeFondo oMaeFondo) throws Exception {  
        
         iNegFondoEJB = (INegFondo) Utilidades.getEJBRemote("SessionFondo", INegFondo.class.getName()); 
         System.out.println("================0000000000=============== "+iNegFondoEJB);
        return iNegFondoEJB.listarFondos(oMaeFondo);
    }
//listarFondosUser
    

    
    
}
