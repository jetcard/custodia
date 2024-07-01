/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.iface;

import java.util.ArrayList;
import pop.comun.dominio.MaeFondo;


/**
 *
 * @author Jyoverar
 */
public interface IMaeFondoDao {
    
    Integer insert(MaeFondo oMaeFondo);
    
    void update(MaeFondo oMaeFondo);
    
    void delete(MaeFondo oMaeFondo);
    
    ArrayList<MaeFondo> fetchAll(MaeFondo oMaeFondo);
    
    ArrayList<MaeFondo> fetchDashboard(MaeFondo oMaeFondo);
    

    
}
