/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.ejb;

import java.util.List;
import javax.ejb.Stateless;
import oracle.jdbc.OracleConnection;
import pop.comun.dominio.CobTablas;
import pop.webcustodia.conn.Conexion;
import pop.webcustodia.dao.FactoryDao;
import pop.webcustodia.negocio.INegTablas;


/**
 *
 * @author Jyoverar
 */
@Stateless(mappedName = "ejbTablas")
public class SessionTablas implements INegTablas{
    
    FactoryDao ofDao = new FactoryDao();

    @Override
    public List<CobTablas> listarTablas(CobTablas oCobTablas) throws Exception{
        List<CobTablas> oTablasList;
       
        oTablasList = ofDao.getCobTablas().fetchAll(oCobTablas);
        return oTablasList;
    }
    
    @Override
    public List<CobTablas> listarTablasEva(CobTablas oCobTablas) throws Exception{
        List<CobTablas> oTablasList;
       
        oTablasList = ofDao.getCobTablas().fetchEvaAll(oCobTablas);
        return oTablasList;
    }    
    
    @Override
    public List<CobTablas> listarTablaDetalle(CobTablas oCobTablas) throws Exception{
        List<CobTablas> oTablasList;
       
        oTablasList = ofDao.getCobTablas().fetchTablaDetalle(oCobTablas);
        return oTablasList;
    }    
    
    @Override
    public List<CobTablas> listarDocDetTablas(CobTablas oCobTablas) throws Exception{
        List<CobTablas> oTablasList;
       
        oTablasList = ofDao.getCobTablas().fetchDocDetTablas(oCobTablas);
        return oTablasList;
    }    
    
    
    @Override
    public List<CobTablas> listarTablasInv(String lcfondo,String lsTchn,CobTablas oCobTablas) throws Exception{
        List<CobTablas> oTablasList;
       
        oTablasList = ofDao.getCobTablas().fetchAllInv(lcfondo,lsTchn,oCobTablas);
        return oTablasList;
    }
    
    
    @Override
    public List<CobTablas> listarSolicitante(CobTablas oCobTablas) throws Exception{
        List<CobTablas> oTablasList;
       
        oTablasList = ofDao.getCobTablas().fetchSolicitante(oCobTablas);
        return oTablasList;
    }
    
    
    @Override
    public List<CobTablas> listarTablasInvf2(String lcfondo,String lsTchn,CobTablas oCobTablas) throws Exception{
        List<CobTablas> oTablasList;
       
        oTablasList = ofDao.getCobTablas().fetchAllInvf2(lcfondo,lsTchn,oCobTablas);
        return oTablasList;
    }
    
    @Override
    public List<CobTablas> listarTablasCartas(CobTablas oCobTablas) throws Exception{
        List<CobTablas> oTablasList;
       
        oTablasList = ofDao.getCobTablas().fetchAllCartas(oCobTablas);
        return oTablasList;
    }
    
    @Override
    public List<CobTablas> listarTablasCartasDet(CobTablas oCobTablas) throws Exception{
        List<CobTablas> oTablasList;
       
        oTablasList = ofDao.getCobTablas().fetchAllCartasDet(oCobTablas);
        return oTablasList;
    }
    
    @Override
    public List<CobTablas> listarTablasDocusCustodia(CobTablas oCobTablas,String tchn) throws Exception{
        List<CobTablas> oTablasList;
       
        oTablasList = ofDao.getCobTablas().fetchAllDocusCustodia(oCobTablas,tchn);
        return oTablasList;
    }    
}
