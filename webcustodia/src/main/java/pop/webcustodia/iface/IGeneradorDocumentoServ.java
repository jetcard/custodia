/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.iface;

import java.util.ArrayList;
import java.util.List;
import pop.comun.dominio.MaeEmail;
import pop.comun.dominio.MaeInversion;


/**
 *
 * @author Jyoverar
 */
public interface IGeneradorDocumentoServ {
    List<MaeInversion> listarPropietarios(MaeInversion oMaeInversion) throws Exception;
    List<MaeInversion> listarClientes(MaeInversion oMaeInversion) throws Exception;
    List<MaeInversion> listarAllPropietarios(MaeInversion oMaeInversion) throws Exception;
    List<MaeInversion> listarHistoricoCarta(MaeInversion oMaeInversion) throws Exception;
    Integer insertarGeneraDoc(MaeInversion oMaeInversion) throws Exception;
    Integer anularGeneraDoc(MaeInversion oMaeInversion) throws Exception;
    Integer insertarConstLiqui(MaeInversion oMaeInversion) throws Exception;    
    MaeInversion anulaConstancia(MaeInversion oMaeInversion) throws Exception;    
    Integer generaCarta1(MaeInversion maeInversion) throws Exception;
    Integer generaConstancia(MaeInversion maeInversion) throws Exception;
    List<MaeInversion> listarCartas(MaeInversion maeInversion) throws Exception;
    List<MaeInversion> listarConstancias(MaeInversion maeInversion) throws Exception;
    Integer notificarXEmail(MaeInversion oMaeInversion) throws Exception;
    ArrayList<MaeEmail> fetchAll() throws Exception;
}
