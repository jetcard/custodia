/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.iface;

import java.util.ArrayList;
import pop.comun.dominio.MaeAsesor;
/**
 *
 * @author EC23329
 */
public interface IMaeAsesorDao {
    ArrayList<MaeAsesor> fetchAll(MaeAsesor oMaeAsesor);
    
    ArrayList<MaeAsesor> fetchAllUser(MaeAsesor oMaeAsesor);
}
