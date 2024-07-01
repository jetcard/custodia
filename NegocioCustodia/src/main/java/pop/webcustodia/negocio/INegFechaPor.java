/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.negocio;

import java.util.List;
import javax.ejb.Remote;
import pop.comun.dominio.MaeFondo;

import pop.comun.dominio.MaeFechaPor;

/**
 *
 * @author Jyoverar
 */
@Remote
public interface INegFechaPor {

    List<MaeFechaPor> listarFechaPor() throws Exception;
    
    
}
