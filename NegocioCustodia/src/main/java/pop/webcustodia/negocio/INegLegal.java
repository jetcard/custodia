/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.negocio;

import java.util.List;
import javax.ejb.Remote;
import pop.comun.dominio.CobTchn;
import pop.comun.dominio.LegDemanTchn;
import pop.comun.dominio.LegSeguiTchn;
import pop.comun.dominio.LegalTchn;
import pop.comun.dominio.MaeAnio;
import pop.comun.dominio.MaeDeposito;
import pop.comun.dominio.MaeEstadoCuenta;
import pop.comun.dominio.MaeEstadoLegal;
import pop.comun.dominio.MaeMes;

/**
 *
 * @author Jyoverar
 */
@Remote
public interface INegLegal {
     
     List<LegalTchn> listarTchnLegal(LegalTchn oLegalTchn) throws Exception;
     List<LegalTchn> listarTchnLegalRepo(LegalTchn oLegalTchn) throws Exception;
     List<LegSeguiTchn> listarSeguiTchnLegal(LegSeguiTchn olegSeguiTchn) throws Exception;
     List<MaeEstadoLegal> listarEstadoTchnLegal(String idEtapa) throws Exception;  
     List<MaeEstadoLegal> listarEspecialTchnLegal(String codigo,String codjuz) throws Exception;
     List<LegalTchn> listarEtapaTchnLegal(LegalTchn oLegalTchn) throws Exception;
     Integer insertarDemanda(LegalTchn oLegalTchn)throws Exception;
     Integer insertarSegDemanda(LegSeguiTchn olegSeguiTchn)throws Exception;
     List<LegSeguiTchn> modificarSegLegal(LegSeguiTchn olegSeguiTchn) throws Exception;
     Integer CerrarEtapaSegDemanda(LegSeguiTchn olegSeguiTchn)throws Exception;
     List<LegalTchn> ListaSeguidores(LegalTchn oLegalTchn) throws Exception;
     List<LegalTchn>listarTchnLegalAsesor(LegalTchn oLegalTchn) throws Exception;
    
     /// MIGRACION  
     Integer insertarDemandaMODY(LegalTchn oLegalTchn)throws Exception;
     List<LegalTchn> listarTchnLegalMODY(LegalTchn oLegalTchn) throws Exception;
     List<LegSeguiTchn> modificarSegLegalMODY(LegSeguiTchn olegSeguiTchn) throws Exception;
     List<LegalTchn> listarEtapaTchnMODY(LegalTchn oLegalTchn) throws Exception;
     List<MaeEstadoLegal> listarEstadoTchnLegalMODY() throws Exception;  
     List<LegSeguiTchn> listarSeguiTchnLegalMODY(LegSeguiTchn olegSeguiTchn) throws Exception;
     Integer insertarSegDemandaMODY(LegSeguiTchn olegSeguiTchn)throws Exception;
     Integer eliminarMODY(LegSeguiTchn olegSeguiTchn)throws Exception;
     List<LegalTchn> listar_LegalImpxfondo(String Mes,String Anio) throws Exception;
     List<LegalTchn> lista_TopVisiCap(String Mes,String Anio) throws Exception;
     List<LegalTchn> lista_TopVisiPop(String Mes,String Anio) throws Exception;
     List<LegalTchn> lista_TopVisiMyp(String Mes,String Anio) throws Exception;
     List<LegalTchn> lista_TopVisiPrh(String Mes,String Anio) throws Exception;
     List<List<Object>> cargarDashboard_Impxfondo() throws Exception;
     List<LegalTchn> lista_ImpTopneg(String Mes,String Anio) throws Exception;
     List<LegalTchn> Lista_xAseEtapa(String Mes,String Anio) throws Exception;
     List<LegalTchn> Lista_xAseIndicador(String Mes,String Anio) throws Exception;
     List<List<Object>> Dashboard_xAseIndicador() throws Exception;
     
      
     
     // DASHBOARD
     List<List<Object>>cargarDashboardActivo(String LsUsuario) throws Exception;
     List<List<Object>>cargarDashboardCancelado(String LsUsuario) throws Exception;
     List<List<Object>>cargarDashboardEtapaxFondo() throws Exception;
     List<LegalTchn> lista_Dashboard_etapaxfondo() throws Exception;
     List<LegalTchn> lista_Dashboard_etapaDesem() throws Exception;
     List<LegalTchn> lista_Dashboard_AseCal() throws Exception;
     List<LegalTchn> lista_Dashboard_x_asesor() throws Exception;
     List<LegalTchn> lista_Dashboard_fondoDese() throws Exception;
     List<List<Object>>cargarDashboardEtapaxDesem() throws Exception;
     List<List<Object>>cargarDashboardFondoxIndi() throws Exception;
     List<List<Object>>cargarDashboardAsesorxIndi() throws Exception;
     List<MaeMes> listar_mes() throws Exception;
     List<MaeAnio> listar_anio() throws Exception;
     
 
     
}
