/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import pop.comun.dominio.CobRequerimientoCartas;
import pop.comun.dominio.CobRequerimientoCriterios;
import pop.comun.dominio.CobTablas;
import pop.comun.dominio.MaeCuotaPago;
import pop.comun.dominio.MaeFondo;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MaePersona;
import pop.comun.dominio.MovimientoCartas;
import pop.webcustodia.filter.SessionUtils;
import pop.webcustodia.iface.ICuotaPagoServ;
import pop.webcustodia.iface.IFondoServ;
import pop.webcustodia.iface.IRequerimientoServ;
import pop.webcustodia.iface.ISeguimientoServ;
import pop.webcustodia.iface.ITablasServ;
import pop.webcustodia.servicio.CuotaPagoServ;
import pop.webcustodia.servicio.FondoServ;
import pop.webcustodia.servicio.RequerimientoServ;
import pop.webcustodia.servicio.SeguimientoServ;
import pop.webcustodia.servicio.TablasServ;

/**
 *
 * @author RC433838
 */
@Named
@ViewScoped
public class RequerimientoBean extends CobRequerimientoCartas implements Serializable {

    private IFondoServ fondoServ = new FondoServ();
    private ITablasServ tablasServ = new TablasServ();
    private IRequerimientoServ requeriminetoServ = new RequerimientoServ();    
    private ICuotaPagoServ cuotaPagoServ = new CuotaPagoServ();    
    private ISeguimientoServ seguimientoServ = new SeguimientoServ();    

    private MaeInversion maeInversion = new MaeInversion();

    private MaeFondo maeFondo = new MaeFondo();
    private List<MaeFondo> maeFondoList; 

    private CobTablas cobTablasEstados = new CobTablas();  
    private List<CobTablas> listaEstados;
    
    private List<CobTablas> listaTipoRequerimiento;    
    
    private List<MovimientoCartas> cartasList = new ArrayList<>();
    
    private MaePersona maePersona = new MaePersona();
    private List<CobRequerimientoCartas> requerimientoList;
       
    
    private Integer param1;
    private String errorNewMod = "";
    private String pagePrev;
    private String pageNext;
        
    
    

 
    public void initTipoCons(LoginBean login, String title, String prev, String next){
            
        try {
     
            login.setPageTitle(title);
            setPagePrev(prev);
            setPageNext(next);            
            
        } catch (Exception e) {

        }
      
    }
    
    public void postLoad(){
        
   
        
    }     
    
    public void listarFondos() {
        System.out.println("RequerimientoBean().listarFondos");
        try {
          
            if (getMaeFondoList() == null) {
                setMaeFondoList(getFondoServ().listarFondos(new MaeFondo()));
            }
            
            System.out.println("cantidad de Fondos -> " + getMaeFondoList().size());
            
        } catch (Exception e) {
            System.out.println("listarFondos() --> " + e.getMessage());
        }
    }

    public void listarEstados(String defecto){
            
        try {
            
            List<CobTablas> lista = new ArrayList<CobTablas>();
            
            getCobTablasEstados().setCtablaId("0623");
            getCobTablasEstados().setCtablaDetId(null);
            
            for(CobTablas ct: getTablasServ().listarTablasEva(getCobTablasEstados())){
                
                if(!ct.getCtablaDetId().equals("0001"))
                    lista.add(ct);
                
            }
            setListaEstados(lista);
            setReqEstado(defecto);
            
        } catch (Exception e) {
            
            System.out.println("listarEstados(String defecto) --> " + e.getMessage());
            
        }
      
    }  
    
    public void listarTipoReque(){
            
        try {
            List<CobTablas> lista = new ArrayList<CobTablas>();
            
            getCobTablasEstados().setCtablaId("0622");
            getCobTablasEstados().setCtablaDetId(null);
            
            
            for(CobTablas ct: getTablasServ().listarTablasEva(getCobTablasEstados())){

                if(!ct.getCtablaDetId().equals("1000") && !ct.getCtablaDetId().equals("2000"))
                    lista.add(ct);                
                
            }
            
            setListaTipoRequerimiento(lista);
            
        } catch (Exception e) {
            System.out.println("listarTipoReque() --> " + e.getMessage());
        }
      
    }     
    
    public void listarRequerimientos() {
        try {
            System.out.println("RequerimientoBean.listarRequerimientos(super)");

            CobRequerimientoCartas oReq = new CobRequerimientoCartas();
            oReq.setFondoId(getFondoId());
            oReq.setReqEstado(getReqEstado());
            oReq.setDvalor_bv(dvalor_bv);
            oReq.setReqEmision(reqEmision);
            oReq.setReqEmision2(reqEmision2);
            oReq.setReqEnvio(reqEnvio);
            oReq.setReqEnvio2(reqEnvio2);
            oReq.setReqRecepcion(reqRecepcion);
            oReq.setReqRecepcion2(reqRecepcion2);
            oReq.setTipoCarta(tipoCarta);
            
            maeInversion.setcPersonaId(maePersona);
            
            oReq.setInversion(maeInversion);
                        
            requerimientoList = requeriminetoServ.allRequerimientos(oReq);
                                    
            setCriterios(null);
                        
            System.out.println("   Lista de listarRequerimeintos = " + getRequerimientoList().size());

        } catch (Exception e) {
             System.out.println(e.getMessage());
        }
    }

    public void listarCriterios(String tipo, int numero) {
        try {
            
            System.out.println("RequerimientoBean.listarCriterios()");

            CobRequerimientoCriterios oCri = new CobRequerimientoCriterios();
            oCri.setReqTipo(tipo);
            oCri.setReqNumero(numero);
            
            setCriterios(requeriminetoServ.findCriterios(oCri));
                        
            System.out.println("   Lista de listarCriterios = " + getCriterios().size());

        } catch (Exception e) {
             System.out.println(e.getMessage());
        }
    }
    

    

    
    public String acciones(int opcion) {
        
        String url = null;
        
        try {
            switch(opcion) {
              case 1:
                                     
                  for(CobRequerimientoCartas oReq: requerimientoList){
                    
                    if(oReq.isSel()){
                        url = "rcustodiaGdoc?faces-redirect=true";

                        HttpSession session = SessionUtils.getSession();
                        session.setAttribute("oReqCar", oReq);
                    }
                    
                  }
                  
                  break;
              case 2:
                  
                  for(CobRequerimientoCartas oReq: requerimientoList){
                    
                    if(oReq.isSel()){
                        url = "Protestos_nuevo?faces-redirect=true";

                        HttpSession session = SessionUtils.getSession();
                        session.setAttribute("oReqCar", oReq);
                    }
                    
                  }                  
                  
                break;
              case 3: 
                                    
                  
                  for(CobRequerimientoCartas oReq: requerimientoList){
                    
                    if(oReq.isSel() && oReq.getReqEstado().equals("0002")){
                    
                        oReq.setcUsuarioMod(SessionUtils.getUserName());
                        oReq.setReqEstado("0003");
                        oReq.setReqComentario(reqComentario);
                        
                        requeriminetoServ.cambiaEstadoReq(oReq);
                        reqComentario = null;
                    }
                    
                  }

                  listarRequerimientos();
                  
                  
                break;                
              default:
                // code block
            }
            
        } catch (Exception e) {
            System.err.println("error: "+e.getMessage());
        }
        
     return url;          
    }     
    
    
    

    
    /**
     * @return the maeInversion
     */
    public MaeInversion getMaeInversion() {
        return maeInversion;
    }

    /**
     * @param maeInversion the maeInversion to set
     */
    public void setMaeInversion(MaeInversion maeInversion) {
        this.maeInversion = maeInversion;
    }

    /**
     * @return the fondoServ
     */
    public IFondoServ getFondoServ() {
        return fondoServ;
    }

    /**
     * @param fondoServ the fondoServ to set
     */
    public void setFondoServ(IFondoServ fondoServ) {
        this.fondoServ = fondoServ;
    }

    /**
     * @return the tablasServ
     */
    public ITablasServ getTablasServ() {
        return tablasServ;
    }

    /**
     * @param tablasServ the tablasServ to set
     */
    public void setTablasServ(ITablasServ tablasServ) {
        this.tablasServ = tablasServ;
    }

    /**
     * @return the maeFondo
     */
    public MaeFondo getMaeFondo() {
        return maeFondo;
    }

    /**
     * @param maeFondo the maeFondo to set
     */
    public void setMaeFondo(MaeFondo maeFondo) {
        this.maeFondo = maeFondo;
    }

    /**
     * @return the maeFondoList
     */
    public List<MaeFondo> getMaeFondoList() {
        return maeFondoList;
    }

    /**
     * @param maeFondoList the maeFondoList to set
     */
    public void setMaeFondoList(List<MaeFondo> maeFondoList) {
        this.maeFondoList = maeFondoList;
    }

    
    /**
     * @return the cobTablasEstados
     */
    public CobTablas getCobTablasEstados() {
        return cobTablasEstados;
    }

    /**
     * @param cobTablasEstados the cobTablasEstados to set
     */
    public void setCobTablasEstados(CobTablas cobTablasEstados) {
        this.cobTablasEstados = cobTablasEstados;
    }

    /**
     * @return the listaEstados
     */
    public List<CobTablas> getListaEstados() {
        return listaEstados;
    }

    /**
     * @param listaEstados the listaEstados to set
     */
    public void setListaEstados(List<CobTablas> listaEstados) {
        this.listaEstados = listaEstados;
    }

    /**
     * @return the maePersona
     */
    public MaePersona getMaePersona() {
        return maePersona;
    }

    /**
     * @param maePersona the maePersona to set
     */
    public void setMaePersona(MaePersona maePersona) {
        this.maePersona = maePersona;
    }

    /**
     * @return the requeriminetoServ
     */
    public IRequerimientoServ getRequeriminetoServ() {
        return requeriminetoServ;
    }

    /**
     * @param requeriminetoServ the requeriminetoServ to set
     */
    public void setRequeriminetoServ(IRequerimientoServ requeriminetoServ) {
        this.requeriminetoServ = requeriminetoServ;
    }

    /**
     * @return the requerimientoList
     */
    public List<CobRequerimientoCartas> getRequerimientoList() {
        return requerimientoList;
    }

    /**
     * @param requerimientoList the requerimientoList to set
     */
    public void setRequerimientoList(List<CobRequerimientoCartas> requerimientoList) {
        this.requerimientoList = requerimientoList;
    }

    /**
     * @return the param1
     */
    public Integer getParam1() {
        return param1;
    }

    /**
     * @param param1 the param1 to set
     */
    public void setParam1(Integer param1) {
        this.param1 = param1;
    }

    /**
     * @return the errorNewMod
     */
    public String getErrorNewMod() {
        return errorNewMod;
    }

    /**
     * @param errorNewMod the errorNewMod to set
     */
    public void setErrorNewMod(String errorNewMod) {
        this.errorNewMod = errorNewMod;
    }

    /**
     * @return the pagePrev
     */
    public String getPagePrev() {
        return pagePrev;
    }

    /**
     * @param pagePrev the pagePrev to set
     */
    public void setPagePrev(String pagePrev) {
        this.pagePrev = pagePrev;
    }

    /**
     * @return the pageNext
     */
    public String getPageNext() {
        return pageNext;
    }

    /**
     * @param pageNext the pageNext to set
     */
    public void setPageNext(String pageNext) {
        this.pageNext = pageNext;
    }

    /**
     * @return the listaTipoRequerimiento
     */
    public List<CobTablas> getListaTipoRequerimiento() {
        return listaTipoRequerimiento;
    }

    /**
     * @param listaTipoRequerimiento the listaTipoRequerimiento to set
     */
    public void setListaTipoRequerimiento(List<CobTablas> listaTipoRequerimiento) {
        this.listaTipoRequerimiento = listaTipoRequerimiento;
    }

   
    /**
     * @return the cuotaPagoServ
     */
    public ICuotaPagoServ getCuotaPagoServ() {
        return cuotaPagoServ;
    }

    /**
     * @param cuotaPagoServ the cuotaPagoServ to set
     */
    public void setCuotaPagoServ(ICuotaPagoServ cuotaPagoServ) {
        this.cuotaPagoServ = cuotaPagoServ;
    }

    /**
     * @return the seguimientoServ
     */
    public ISeguimientoServ getSeguimientoServ() {
        return seguimientoServ;
    }

    /**
     * @param seguimientoServ the seguimientoServ to set
     */
    public void setSeguimientoServ(ISeguimientoServ seguimientoServ) {
        this.seguimientoServ = seguimientoServ;
    }

    /**
     * @return the maeSeguimiento
     */
    /*
    public CobMaeSeguimiento getMaeSeguimiento() {
        return maeSeguimiento;
    }
    */

    /**
     * @param maeSeguimiento the maeSeguimiento to set
     */
    /*
    public void setMaeSeguimiento(CobMaeSeguimiento maeSeguimiento) {
        this.maeSeguimiento = maeSeguimiento;
    }
    */

    /**
     * @return the cartasList
     */
    public List<MovimientoCartas> getCartasList() {
        return cartasList;
    }

    /**
     * @param cartasList the cartasList to set
     */
    public void setCartasList(List<MovimientoCartas> cartasList) {
        this.cartasList = cartasList;
    }
    
   
}
