/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.iface;

import java.util.ArrayList;
import pop.comun.dominio.MaeEmail;
import pop.comun.dominio.MaeInversion;


/**
 *
 * @author Jyoverar
 */
public interface IGeneraDocumentoDoa {
    ArrayList<MaeInversion> fetchPropietarios(MaeInversion oMaeInversion);
    ArrayList<MaeInversion> fetchClientes(MaeInversion oMaeInversion);
    ArrayList<MaeInversion> fetchAllPropietarios(MaeInversion oMaeInversion);
    ArrayList<MaeInversion> listarHistoricoCarta(MaeInversion oMaeInversion);
    Integer fetchInsGeneraDoc(MaeInversion oMaeInversion);
    Integer fetchAnulaCarta(MaeInversion oMaeInversion);    
    Integer fetchInsertaConstancia(MaeInversion oMaeInversion);      
    ArrayList<MaeInversion> fetchGeneraCarta(MaeInversion oMaeInversion);      
    MaeInversion fetchGeneraConstancia(MaeInversion oMaeInversion);      
    MaeInversion anulaConstancia(MaeInversion oMaeInversion);      
    ArrayList<MaeInversion> fetchlistarCartas(MaeInversion oMaeInversion);  
    ArrayList<MaeInversion> fetchlistarConstancias(MaeInversion oMaeInversion);
    Integer notificarXEmail(MaeInversion oMaeInversion);
    ArrayList<MaeEmail> fetchAll();
}
