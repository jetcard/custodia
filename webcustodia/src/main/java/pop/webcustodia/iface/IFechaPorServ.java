/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.iface;

import java.util.List;

import pop.comun.dominio.MaeFechaPor;

/**
 *
 * @author Jyoverar
 */
public interface IFechaPorServ {

    
    List<MaeFechaPor> listarFechaPor() throws Exception;
 }
