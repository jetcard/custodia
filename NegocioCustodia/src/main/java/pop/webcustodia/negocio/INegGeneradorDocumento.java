/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.negocio;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import pop.comun.dominio.MaeEmail;
import pop.comun.dominio.MaeInversion;


/**
 *
 * @author Jyoverar
 */
@Remote
public interface INegGeneradorDocumento {

    Integer anulaGeneraDoc(MaeInversion oMaeInversion) throws Exception;
    Integer insertarGeneraDoc(MaeInversion oMaeInversion) throws Exception;
    Integer insertarConstancias(MaeInversion oMaeInversion) throws Exception;    
    Integer generaCarta1(MaeInversion maeInversion) throws Exception;
    Integer generaConstancia(MaeInversion maeInversion) throws Exception;
    MaeInversion anulaConstancia(MaeInversion maeInversion) throws Exception;
    List<MaeInversion> listarPropietarios(MaeInversion oMaeInversion) throws Exception;
    List<MaeInversion> listarClientes(MaeInversion oMaeInversion) throws Exception;
    List<MaeInversion> listarAllPropietarios(MaeInversion oMaeInversion) throws Exception;
    List<MaeInversion> listarHistoricoCarta(MaeInversion oMaeInversion) throws Exception;
    List<MaeInversion> listarCartas(MaeInversion oMaeInversion) throws Exception;
    List<MaeInversion> listarConstancias(MaeInversion oMaeInversion) throws Exception;
    Integer notificarXEmail(MaeInversion oMaeInversion) throws Exception;
    ArrayList<MaeEmail> fetchAll() throws Exception;
}
