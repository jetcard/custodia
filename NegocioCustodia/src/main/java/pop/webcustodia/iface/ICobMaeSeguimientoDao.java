/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.iface;

import java.util.ArrayList;
import pop.comun.dominio.CobMaeSeguimiento;

/**
 *
 * @author Jyoverar
 */
public interface ICobMaeSeguimientoDao {
    
    Integer insert(CobMaeSeguimiento oCobMaeSeguimiento);

    void update(CobMaeSeguimiento oCobMaeSeguimiento);

    void delete(CobMaeSeguimiento oCobMaeSeguimiento);
    
    CobMaeSeguimiento fetch(CobMaeSeguimiento oCobMaeSeguimiento);

    ArrayList<CobMaeSeguimiento> fetchAll(CobMaeSeguimiento oCobMaeSeguimiento);
    
    CobMaeSeguimiento fetchingSingle(CobMaeSeguimiento oCobMaeSeguimiento);
    
    ArrayList<CobMaeSeguimiento> fetchCall(CobMaeSeguimiento oCobMaeSeguimiento);
    
}
