/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.negocio;

import java.util.List;
import javax.ejb.Remote;
import pop.comun.dominio.CobMaeSeguimiento;

/**
 *
 * @author Jyoverar
 */
@Remote
public interface INegCobMaeSeguimiento {

    Integer insertar(CobMaeSeguimiento oCobMaeSeguimiento) throws Exception;

    CobMaeSeguimiento buscar(CobMaeSeguimiento oCobMaeSeguimiento) throws Exception;

    CobMaeSeguimiento listarSeguimiento(CobMaeSeguimiento oCobMaeSeguimiento) throws Exception;
    
    List<CobMaeSeguimiento> listarSeguimientoLlamada(CobMaeSeguimiento oCobMaeSeguimiento) throws Exception;

}
