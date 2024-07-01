/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.dao;

import java.sql.Connection;
import oracle.jdbc.OracleConnection;
import pop.webcustodia.iface.ICobRequerimientoCartasDao;

import pop.webcustodia.iface.ICobTablasDao;
import pop.webcustodia.iface.IConexionDao;
import pop.webcustodia.iface.IGeneraDocumentoDoa;
import pop.webcustodia.iface.ILaftPersonaDao;


import pop.webcustodia.iface.IMaeDashboardDao;
import pop.webcustodia.iface.IMaeDireccionDao;
import pop.webcustodia.iface.IMaeEmailDao;
import pop.webcustodia.iface.IMaeEstadoCuentaDao;
import pop.webcustodia.iface.IMaeFondoDao;
import pop.webcustodia.iface.IMaeInmuebleDao;
import pop.webcustodia.iface.IMaeInversionDoa;
import pop.webcustodia.iface.IMaeNotificacionDao;
import pop.webcustodia.iface.IMaePersonaDao; 
import pop.webcustodia.iface.IMaeSesionDao;
import pop.webcustodia.iface.IMaeTelefonoDao;
import pop.webcustodia.iface.IMaeTipoCambioDao;
import pop.webcustodia.iface.IMaeUbigeoDao;
import pop.webcustodia.iface.IMaeFechaPorDao;
import pop.webcustodia.iface.IMovimientoProtestoDao;


import pop.webcustodia.impl.CobTablasDao;
import pop.webcustodia.impl.ConexionDao;
import pop.webcustodia.impl.LaftPersonaDao;


import pop.webcustodia.impl.MaeDashboardDao;
import pop.webcustodia.impl.MaeDireccionDao;
import pop.webcustodia.impl.MaeFondoDao;

import pop.webcustodia.impl.MaeInmuebleDao;
import pop.webcustodia.impl.MaeInversionDao;
import pop.webcustodia.impl.MaeNotificacionDao;
import pop.webcustodia.impl.MaePersonaDao;
import pop.webcustodia.impl.MaeSesionDao;
import pop.webcustodia.impl.MaeUbigeoDao;

import pop.webcustodia.impl.MaeFechaPorDao; 
import pop.webcustodia.impl.MaeGeneradorDocumentoDao;
import pop.webcustodia.impl.MovimientoProtestoDao;
import pop.webcustodia.impl.CobRequerimientoCartasDao;

/**
 *
 * @author Jyoverar
 */
public class FactoryDao {

    public IConexionDao getConexionDao() {
        return ConexionDao.Instance();
    }
 
    public IMaePersonaDao getMaePersonaDao() {
        return new MaePersonaDao();
    }

    public ILaftPersonaDao getLaftPersonaDao() {
        return new LaftPersonaDao();
    }

    public IMaeInversionDoa getMaeInversionDao() {
        return new MaeInversionDao();
    }
  
    public IGeneraDocumentoDoa getGeneraDocumentoDao() {
        return new MaeGeneradorDocumentoDao();
    }     
    
    public IMaeDashboardDao getMaeDashboard() {
        return new MaeDashboardDao();
    }

    public IMaeUbigeoDao getMaeUbigeo() {
        return new MaeUbigeoDao();
    }


    public ICobTablasDao getCobTablas() {
        return new CobTablasDao();
    }

 

    public IMaeSesionDao getMaeSesion() {
        return new MaeSesionDao();
    }

    public IMaeNotificacionDao getMaeNotificacion() {
        return new MaeNotificacionDao();
    }
    
   
    public IMaeFondoDao getFondo() {
        return new MaeFondoDao();
    }

  
    public IMaeDireccionDao getDireccion() {
        return new MaeDireccionDao();
    }


 

    public IMaeInmuebleDao getInmueble() {
        return new MaeInmuebleDao();
    }

     
     public IMaeFechaPorDao getMaeFechaPor() {
        return new MaeFechaPorDao();
    }
     
    public IMovimientoProtestoDao getProtesto(){
        return new MovimientoProtestoDao();
    }
    
    public ICobRequerimientoCartasDao getRequerimiento(){
        return new CobRequerimientoCartasDao();
    }    

}
