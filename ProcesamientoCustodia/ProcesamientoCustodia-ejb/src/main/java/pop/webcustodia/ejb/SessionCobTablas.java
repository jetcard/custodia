/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import pop.comun.dominio.CobTablas;
import pop.webcustodia.dao.FactoryDao;
import pop.webcustodia.negocio.INegCobTablas;


/**
 *
 * @author HH38092
 */
@Stateless(mappedName = "ejbCobTablas")
public class SessionCobTablas implements INegCobTablas{
    FactoryDao ofDao = new FactoryDao();
    
    @Override
    public List<CobTablas> listarTablas(CobTablas oCobTablas) throws Exception {
        List<CobTablas> oTablasList;
        oTablasList = ofDao.getCobTablas().fetchAllTablasGenerales(oCobTablas);
        return oTablasList;
    }
    
    @Override
    public Integer insertarCobTablas(CobTablas oCobTablas) throws Exception {
        Integer Rspta = 0;
        Rspta = ofDao.getCobTablas().insertarCobTablas(oCobTablas);
        return Rspta;
    }
    
    @Override
    public Integer modificarCobTablas(CobTablas oCobTablas) throws Exception {
        Integer Rspta = 0;
        Rspta = ofDao.getCobTablas().modificarCobTablas(oCobTablas);
        return Rspta;
    }
    
    @Override
    public List<CobTablas> listarDetalle(CobTablas oCobTablas) throws Exception {
        List<CobTablas> oTablasList;
        oTablasList = ofDao.getCobTablas().fetchAllDetalle(oCobTablas);
        return oTablasList;
    }
    
    @Override
    public Integer insertarCobDetalle(CobTablas oCobTablas) throws Exception {
        Integer Rspta = 0;
        Rspta = ofDao.getCobTablas().insertarCobDetalle(oCobTablas);
        return Rspta;
    }    
    
    @Override
    public Integer modificarCobDetalle(CobTablas oCobTablas) throws Exception {
        Integer Rspta = 0;
        Rspta = ofDao.getCobTablas().modificarCobDetalle(oCobTablas);
        return Rspta;
    }    
}
