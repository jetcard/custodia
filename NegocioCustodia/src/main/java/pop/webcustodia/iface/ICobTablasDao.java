/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.iface;

import java.util.ArrayList;
import pop.comun.dominio.CobTablas;

/**
 *
 * @author Jyoverar
 */
public interface ICobTablasDao {

    Integer insert(CobTablas oCobTablas);

    void update(CobTablas oCobTablas);

    void delete(CobTablas oCobTablas);

    ArrayList<CobTablas> fetchAll(CobTablas oCobTablas);
    ArrayList<CobTablas> fetchEvaAll(CobTablas oCobTablas);
    ArrayList<CobTablas> fetchTablaDetalle(CobTablas oCobTablas);
    ArrayList<CobTablas> fetchDocDetTablas(CobTablas oCobTablas);
    ArrayList<CobTablas> fetchAllInv(String lcfondo,String lsTchn, CobTablas oCobTablas);
    ArrayList<CobTablas> fetchSolicitante(CobTablas oCobTablas);
    ArrayList<CobTablas> fetchAllInvf2(String lcfondo,String lsTchn, CobTablas oCobTablas);
    ArrayList<CobTablas> fetchAllCartas(CobTablas oCobTablas);
    ArrayList<CobTablas> fetchAllCartasDet(CobTablas oCobTablas);
    
    ArrayList<CobTablas> fetchAllTablasGenerales(CobTablas oCobTablas);
    
    Integer insertarCobTablas(CobTablas oCobTablas);
    Integer modificarCobTablas(CobTablas oCobTablas);    
    ArrayList<CobTablas> fetchAllDetalle(CobTablas oCobTablas);
    Integer insertarCobDetalle(CobTablas oCobTablas);
    Integer modificarCobDetalle(CobTablas oCobTablas);
    ArrayList<CobTablas> fetchAllDocusCustodia(CobTablas oCobTablas,String tchn);
}
