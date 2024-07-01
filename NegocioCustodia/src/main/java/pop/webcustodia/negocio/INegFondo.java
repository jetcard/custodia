/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.negocio;

import java.util.List;
import javax.ejb.Remote;
import pop.comun.dominio.MaeFondo;

/**
 *
 * @author Jyoverar
 */
@Remote
public interface INegFondo {

    List<MaeFondo> listarFondos(MaeFondo oMaeFondo) throws Exception;
  }
