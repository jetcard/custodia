/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.negocio;

import java.util.List;
import javax.ejb.Remote;
import pop.comun.dominio.MaeCuotaPago;

/**
 *
 * @author Jyoverar
 */
@Remote
public interface INegCuotaPago {

    List<MaeCuotaPago> listarCuotaPago(MaeCuotaPago oMaeCuotaPago) throws Exception;

    MaeCuotaPago calcularCuotaPagoFuturo(MaeCuotaPago oMaeCuotaPago) throws Exception;
}
