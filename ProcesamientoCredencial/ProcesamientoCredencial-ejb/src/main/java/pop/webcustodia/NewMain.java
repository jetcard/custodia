/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia;

import java.util.logging.Level;
import java.util.logging.Logger;
import pop.webcustodia.dominio.Usuario;

/**
 *
 * @author Jyoverar
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            SessionCredencial sa = new SessionCredencial();
            Usuario oTabUsuario = new Usuario();
            oTabUsuario.setUsuarioId("YROBLES");
            oTabUsuario.setContrasenia("JYOVERA");
            Usuario tabUsuario = sa.obtenerUsuario(oTabUsuario);
            
            System.out.println(tabUsuario.getMensaje());
            
            System.out.println(tabUsuario.getPerNom());
            
        } catch (Exception ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
