/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.iface;

import java.util.ArrayList;
import pop.comun.dominio.TabArchivo;

/**
 *
 * @author Jyoverar
 */
public interface ITabArchivoDao {

    Integer insert(TabArchivo oTabArchivo);
    
    ArrayList<TabArchivo> fetchAll(TabArchivo oTabArchivo);
}
