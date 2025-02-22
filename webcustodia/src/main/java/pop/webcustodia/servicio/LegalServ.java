/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.servicio;

import java.io.Serializable;
import java.util.List;
import pop.comun.dominio.CobTchn;
import pop.comun.dominio.LegDemanTchn;
import pop.comun.dominio.LegSeguiTchn;
import pop.comun.dominio.LegalTchn;
import pop.comun.dominio.MaeAnio;
import pop.comun.dominio.MaeDeposito;
import pop.comun.dominio.MaeEstadoCuenta;
import pop.comun.dominio.MaeEstadoLegal;
import pop.comun.dominio.MaeMes;

import pop.webcustodia.iface.ILegalServ;

import pop.webcustodia.negocio.INegLegal;
import pop.webcustodia.util.Utilidades;

/**
 *
 * @author Jyoverar
 */
public class LegalServ implements ILegalServ, Serializable {

    INegLegal iNegLegal;
    
    
    @Override
    public List<LegalTchn> listarTchnLegal(LegalTchn oLegalTchn) throws Exception {
        iNegLegal = (INegLegal) Utilidades.getEJBRemote("SessionLegal", INegLegal.class.getName());     
        return iNegLegal.listarTchnLegal(oLegalTchn);
    }
    
    @Override
    public List<LegalTchn> listarTchnLegalMODY(LegalTchn oLegalTchn) throws Exception {
        iNegLegal = (INegLegal) Utilidades.getEJBRemote("SessionLegal", INegLegal.class.getName());     
        return iNegLegal.listarTchnLegalMODY(oLegalTchn);
    }
    
    
    
    @Override
    public List<LegalTchn> listarTchnLegalRepo(LegalTchn oLegalTchn) throws Exception {
        iNegLegal = (INegLegal) Utilidades.getEJBRemote("SessionLegal", INegLegal.class.getName());     
        return iNegLegal.listarTchnLegalRepo(oLegalTchn);
    }
    
    @Override
    public List<LegalTchn> listarEtapaTchnLegal(LegalTchn oLegalTchn) throws Exception {
        iNegLegal = (INegLegal) Utilidades.getEJBRemote("SessionLegal", INegLegal.class.getName());     
        return iNegLegal.listarEtapaTchnLegal(oLegalTchn);
    }
    
    
    @Override
    public List<LegalTchn> listarEtapaTchnMODY(LegalTchn oLegalTchn) throws Exception {
        iNegLegal = (INegLegal) Utilidades.getEJBRemote("SessionLegal", INegLegal.class.getName());     
        return iNegLegal.listarEtapaTchnMODY(oLegalTchn);
    }

    @Override
    public List<LegSeguiTchn> listarSeguiTchnLegal(LegSeguiTchn olegSeguiTchn) throws Exception {
        iNegLegal = (INegLegal) Utilidades.getEJBRemote("SessionLegal", INegLegal.class.getName());     
        return iNegLegal.listarSeguiTchnLegal(olegSeguiTchn);
    }
    
    
    @Override
    public List<LegSeguiTchn> listarSeguiTchnLegalMODY(LegSeguiTchn olegSeguiTchn) throws Exception {
        iNegLegal = (INegLegal) Utilidades.getEJBRemote("SessionLegal", INegLegal.class.getName());     
        return iNegLegal.listarSeguiTchnLegalMODY(olegSeguiTchn);
    }
    
    @Override
    public List<MaeEstadoLegal> listarEstadoTchnLegal(String idEtapa) throws Exception {
        iNegLegal = (INegLegal) Utilidades.getEJBRemote("SessionLegal", INegLegal.class.getName());     
        return iNegLegal.listarEstadoTchnLegal(idEtapa);
    }
    
    
    @Override
    public List<MaeEstadoLegal> listarEstadoTchnLegalMODY() throws Exception {
        iNegLegal = (INegLegal) Utilidades.getEJBRemote("SessionLegal", INegLegal.class.getName());     
        return iNegLegal.listarEstadoTchnLegalMODY();
    }
    
    
    @Override
    public List<MaeEstadoLegal> listarEspecialTchnLegal(String codigo,String codJuz) throws Exception {
        iNegLegal = (INegLegal) Utilidades.getEJBRemote("SessionLegal", INegLegal.class.getName());     
        return iNegLegal.listarEspecialTchnLegal(codigo,codJuz);
    }
    
  
    
    @Override
    public Integer insertarDemanda(LegalTchn oLegalTchn) throws Exception {
        iNegLegal = (INegLegal) Utilidades.getEJBRemote("SessionLegal", INegLegal.class.getName());     
        return iNegLegal.insertarDemanda(oLegalTchn);
    }
    
    @Override
    public Integer insertarDemandaMODY(LegalTchn oLegalTchn) throws Exception {
        iNegLegal = (INegLegal) Utilidades.getEJBRemote("SessionLegal", INegLegal.class.getName());     
        return iNegLegal.insertarDemandaMODY(oLegalTchn);
    }

    @Override
    public Integer insertarSegDemanda(LegSeguiTchn olegSeguiTchn) throws Exception {
        iNegLegal = (INegLegal) Utilidades.getEJBRemote("SessionLegal", INegLegal.class.getName());     
        return iNegLegal.insertarSegDemanda(olegSeguiTchn);
    }
  
    @Override
    public Integer insertarSegDemandaMODY(LegSeguiTchn olegSeguiTchn) throws Exception {
        iNegLegal = (INegLegal) Utilidades.getEJBRemote("SessionLegal", INegLegal.class.getName());     
        return iNegLegal.insertarSegDemandaMODY(olegSeguiTchn);
    }
  
    
    @Override
    public Integer eliminarMODY(LegSeguiTchn olegSeguiTchn) throws Exception {
        iNegLegal = (INegLegal) Utilidades.getEJBRemote("SessionLegal", INegLegal.class.getName());     
        return iNegLegal.eliminarMODY(olegSeguiTchn);
    }
    
    
    @Override
    public List<LegSeguiTchn> modificarSegLegal(LegSeguiTchn olegSeguiTchn) throws Exception {
        iNegLegal = (INegLegal) Utilidades.getEJBRemote("SessionLegal", INegLegal.class.getName());     
        return iNegLegal.modificarSegLegal(olegSeguiTchn);
    }
    
    @Override
    public List<LegSeguiTchn> modificarSegLegalMODY(LegSeguiTchn olegSeguiTchn) throws Exception {
        iNegLegal = (INegLegal) Utilidades.getEJBRemote("SessionLegal", INegLegal.class.getName());     
        return iNegLegal.modificarSegLegalMODY(olegSeguiTchn);
    }
    
     @Override
    public Integer CerrarEtapaSegDemanda(LegSeguiTchn olegSeguiTchn) throws Exception {
        iNegLegal = (INegLegal) Utilidades.getEJBRemote("SessionLegal", INegLegal.class.getName());     
        return iNegLegal.CerrarEtapaSegDemanda(olegSeguiTchn);
    }

    @Override
    public List<LegalTchn> listarSeguidores(LegalTchn oLegalTchn) throws Exception {
        iNegLegal = (INegLegal) Utilidades.getEJBRemote("SessionLegal", INegLegal.class.getName());     
        return iNegLegal.ListaSeguidores(oLegalTchn);
    }
    
    
     @Override
    public List<LegalTchn>listarTchnLegalAsesor(LegalTchn oLegalTchn) throws Exception{
      iNegLegal = (INegLegal) Utilidades.getEJBRemote("SessionLegal", INegLegal.class.getName());     
        return iNegLegal.listarTchnLegalAsesor(oLegalTchn);
    }    
    
    
    // consolidados codigos judiciales activos
    @Override
    public List<List<Object>> cargarDashboardActivo(String LsUsuario) throws Exception {
        iNegLegal = (INegLegal) Utilidades.getEJBRemote("SessionLegal", INegLegal.class.getName());     
        return iNegLegal.cargarDashboardActivo(LsUsuario);
    }

    // consolidados codigos judiciales cancelados
    @Override
    public List<List<Object>> cargarDashboardCancelado(String LsUsuario) throws Exception {
        iNegLegal = (INegLegal) Utilidades.getEJBRemote("SessionLegal", INegLegal.class.getName());     
        return iNegLegal.cargarDashboardCancelado(LsUsuario);
    }

    
    @Override
    public List<List<Object>> cargarDashboardEtapaxFondo() throws Exception {
        iNegLegal = (INegLegal) Utilidades.getEJBRemote("SessionLegal", INegLegal.class.getName());     
        return iNegLegal.cargarDashboardEtapaxFondo();
    }
    
      // consolidados codigos judiciales activos
    @Override
    public List<LegalTchn>lista_Dashboard_etapaxfondo() throws Exception {
        iNegLegal = (INegLegal) Utilidades.getEJBRemote("SessionLegal", INegLegal.class.getName());     
        return iNegLegal.lista_Dashboard_etapaxfondo();
    }
    
     // consolidados codigos judiciales activos
    @Override
    public List<LegalTchn>lista_Dashboard_etapaDesem() throws Exception {
        iNegLegal = (INegLegal) Utilidades.getEJBRemote("SessionLegal", INegLegal.class.getName());     
        return iNegLegal.lista_Dashboard_etapaDesem();
    } 
    
     @Override
    public List<LegalTchn>Lista_Dashboard_AseCal() throws Exception {
        iNegLegal = (INegLegal) Utilidades.getEJBRemote("SessionLegal", INegLegal.class.getName());     
        return iNegLegal.lista_Dashboard_AseCal();
    } 
   
    @Override
    public List<LegalTchn>lista_Dashboard_x_asesor() throws Exception {
        iNegLegal = (INegLegal) Utilidades.getEJBRemote("SessionLegal", INegLegal.class.getName());     
        return iNegLegal.lista_Dashboard_x_asesor();
    } 
    
    
    @Override
    public List<LegalTchn>lista_Dashboard_fondoDese() throws Exception {
        iNegLegal = (INegLegal) Utilidades.getEJBRemote("SessionLegal", INegLegal.class.getName());     
        return iNegLegal.lista_Dashboard_fondoDese();
    } 
    
    @Override
    public List<List<Object>> cargarDashboardEtapaxDesem() throws Exception {
        iNegLegal = (INegLegal) Utilidades.getEJBRemote("SessionLegal", INegLegal.class.getName());     
        return iNegLegal.cargarDashboardEtapaxDesem();
    }
    
    
    @Override
    public List<List<Object>> cargarDashboardFondoxIndi() throws Exception {
        iNegLegal = (INegLegal) Utilidades.getEJBRemote("SessionLegal", INegLegal.class.getName());     
        return iNegLegal.cargarDashboardFondoxIndi();
    }
    
       @Override
    public List<List<Object>> cargarDashboardAsesorxIndi() throws Exception {
        iNegLegal = (INegLegal) Utilidades.getEJBRemote("SessionLegal", INegLegal.class.getName());     
        return iNegLegal.cargarDashboardAsesorxIndi();
    }

    @Override
    public List<MaeMes> listar_mes() throws Exception {
         iNegLegal = (INegLegal) Utilidades.getEJBRemote("SessionLegal", INegLegal.class.getName());     
        return iNegLegal.listar_mes();
    }
    
    @Override
    public List<MaeAnio> listar_anio() throws Exception {
         iNegLegal = (INegLegal) Utilidades.getEJBRemote("SessionLegal", INegLegal.class.getName());     
        return iNegLegal.listar_anio();
    }

    @Override
    public List<LegalTchn> listar_LegalImpxfondo(String Mes, String Anio) throws Exception {
        iNegLegal = (INegLegal) Utilidades.getEJBRemote("SessionLegal", INegLegal.class.getName());     
        return iNegLegal.listar_LegalImpxfondo(Mes,Anio);
    }

     @Override
    public List<LegalTchn> lista_TopVisiCap(String Mes, String Anio) throws Exception {
        iNegLegal = (INegLegal) Utilidades.getEJBRemote("SessionLegal", INegLegal.class.getName());     
        return iNegLegal.lista_TopVisiCap(Mes,Anio);
    }
    

    @Override
    public List<LegalTchn> lista_TopVisiPop(String Mes, String Anio) throws Exception {
          iNegLegal = (INegLegal) Utilidades.getEJBRemote("SessionLegal", INegLegal.class.getName());     
        return iNegLegal.lista_TopVisiPop(Mes,Anio);
    }

    @Override
    public List<LegalTchn> lista_TopVisiMyp(String Mes, String Anio) throws Exception {
          iNegLegal = (INegLegal) Utilidades.getEJBRemote("SessionLegal", INegLegal.class.getName());     
        return iNegLegal.lista_TopVisiMyp(Mes,Anio);
    }

    @Override
    public List<LegalTchn> lista_TopVisiPrh(String Mes, String Anio) throws Exception {
          iNegLegal = (INegLegal) Utilidades.getEJBRemote("SessionLegal", INegLegal.class.getName());     
        return iNegLegal.lista_TopVisiPrh(Mes,Anio);
    }
    
   

    @Override
    public List<List<Object>> cargarDashboard_Impxfondo() throws Exception {
            iNegLegal = (INegLegal) Utilidades.getEJBRemote("SessionLegal", INegLegal.class.getName());     
        return iNegLegal.cargarDashboard_Impxfondo();
    }

    @Override
    public List<LegalTchn>  Lista_ImpTopneg(String Mes, String Anio) throws Exception {
            iNegLegal = (INegLegal) Utilidades.getEJBRemote("SessionLegal", INegLegal.class.getName());     
        return iNegLegal.lista_ImpTopneg(Mes, Anio);
    }
    
    
    @Override
    public List<LegalTchn>  Lista_xAseEtapa(String Mes, String Anio) throws Exception {
            iNegLegal = (INegLegal) Utilidades.getEJBRemote("SessionLegal", INegLegal.class.getName());     
        return iNegLegal.Lista_xAseEtapa(Mes, Anio);
    }
    
    @Override
    public List<LegalTchn>  Lista_xAseIndicador(String Mes, String Anio) throws Exception {
            iNegLegal = (INegLegal) Utilidades.getEJBRemote("SessionLegal", INegLegal.class.getName());     
        return iNegLegal.Lista_xAseIndicador(Mes, Anio);
    }
    
    @Override
    public List<List<Object>>  Dashboard_xAseIndicador() throws Exception {
            iNegLegal = (INegLegal) Utilidades.getEJBRemote("SessionLegal", INegLegal.class.getName());     
        return iNegLegal.Dashboard_xAseIndicador();
    }

    
    
}
