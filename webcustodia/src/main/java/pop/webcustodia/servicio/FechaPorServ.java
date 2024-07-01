/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.servicio;

import java.util.List;
import pop.comun.dominio.MaeFondo;

import pop.comun.dominio.MaeFechaPor;
import pop.webcustodia.iface.IFechaPorServ;
import pop.webcustodia.negocio.INegFechaPor;
import pop.webcustodia.negocio.INegFondo;
import pop.webcustodia.util.Utilidades;

/**
 *
 * @author Jyoverar
 */
public class FechaPorServ implements IFechaPorServ {

    INegFechaPor INegFechaPor;

 

    @Override
    public List<MaeFechaPor> listarFechaPor() throws Exception {
      INegFechaPor = (INegFechaPor) Utilidades.getEJBRemote("SessionFechaPor", INegFechaPor.class.getName());
        return INegFechaPor.listarFechaPor();
   }


    
}
