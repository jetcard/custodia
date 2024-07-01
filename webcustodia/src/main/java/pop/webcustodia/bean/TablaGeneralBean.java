/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import pop.comun.dominio.CobTablas;
import pop.comun.dominio.MaeFondo;
import pop.webcustodia.properties.ManageProperties;
import pop.comun.dominio.MaeEstadoLegal;
import pop.webcustodia.filter.SessionUtils;
import pop.webcustodia.iface.ICobTablasServ;
import pop.webcustodia.iface.IFondoServ;
import pop.webcustodia.iface.ITablasServ;
import pop.webcustodia.servicio.CobTablasServ;
import pop.webcustodia.servicio.FondoServ;
import pop.webcustodia.servicio.TablasServ;
/**
 *
 * @author Jyoverar
 */
@ManagedBean(name = "tablaGeneralBean", eager = true)
@ViewScoped

public class TablaGeneralBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<MaeEstadoLegal> maeEstadoLegalList;

    public CobTablas getCobTablasDocuInv() {
        return cobTablasDocuInv;
    }

    public void setCobTablasDocuInv(CobTablas cobTablasDocuInv) {  
        this.cobTablasDocuInv = cobTablasDocuInv;
    }
    private CobTablas cobTablasDocuInv = new CobTablas();
    private List<MaeFondo> maeFondoList;
    private ManageProperties properties; 
    private IFondoServ fondoServ = new FondoServ();
    private List<CobTablas> cobTablasList;
    private ICobTablasServ cobTablasServ=new CobTablasServ();
    private CobTablas cobTablas = new CobTablas();
    private List<CobTablas> cobTablasDocInvList;

    public List<CobTablas> getCobTablasDocInvList() {
        return cobTablasDocInvList;
    }

    public void setCobTablasDocInvList(List<CobTablas> cobTablasDocInvList) {
        this.cobTablasDocInvList = cobTablasDocInvList;
    }
    private String mensajeRpta = "";
    private boolean mostrarAddTablaGeneral;
    private String descripcionTablaGeneral;
    private String descripcionCorta;
    private String  esdetDoc;
    private String  esContDoc;

    public String getEsContDoc() {
        return esContDoc;
    }

    public void setEsContDoc(String esContDoc) {
        this.esContDoc = esContDoc;
    }
    private String  idDocPadre;

    public String getIDDocPadre() {
        return idDocPadre;
    }

    public void setIDDocPadre(String idDocPadre) {
        this.idDocPadre = idDocPadre;
    }

    public String getEsdetDoc() {
        return esdetDoc;
    }

    public void setEsdetDoc(String esdetDoc) {
        this.esdetDoc = esdetDoc;
    }
    // servicios de tablas
    private ITablasServ tablasServ = new TablasServ();
    // servicio de cronograma

    public ITablasServ getTablasServ() {
        return tablasServ;
    }

    public void setTablasServ(ITablasServ tablasServ) {
        this.tablasServ = tablasServ;
    }


    private String usuario;
    private boolean showMsg = false;
    private String mensaje = "";
    private String tipoMsj = "";
    private boolean mostrarModiTablaGeneral;
    private String tablaId;
    private boolean mostrarLisDetalle;
    private List<CobTablas> cobDetaList;
    private String codigoDescripcion;
    private boolean mostrarAddTablaDetalle;
    private boolean mostrarModiTablaDetalle;

    
    private String tablaDetalleId;
    /**
     * Creates a new instance of EstadoCuentaBean
     */
    public TablaGeneralBean() {
        properties = new ManageProperties();
        properties.getProperties();
        //legSeguiTchn.setLnIntMora(0.00);
    }
   
    public void listarFondos() {
        try {

            if (maeFondoList == null) {
                maeFondoList = getFondoServ().listarFondos(new MaeFondo());
            }
            //System.out.println(" cantidad de Fondos -> " + maeFondoList.size());
        } catch (Exception e) {

        }
    }
    //public void buscarTchnLegal() {
    //public void insertarDemanda(LegalTchn olegTchn ){
    //public void nuevoetapa(LegalTchn olegTchn){
    public IFondoServ getFondoServ() {
        return fondoServ;
    }
    
 public void buscarTablasGenerales() {
        try {
            //legSeguiTchn.setFmostrarEje(false);  
            cobTablas.setDdescCorta("");
            setCobTablasList(cobTablasServ.listarTablasGenerales(cobTablas));
        } catch (Exception e) {
        }
    }

    public void modificarTabla(CobTablas ocobTablas){
        
      
        try {
              
              //legSeguiTchnList2 = legalServ.modificarSegLegal(olegSeguiTchn);
             //String idEtapa=legSeguiTchnList2.get(0).getLs_tipoEtapa();
              //maeEstadoLegalList = legalServ.listarEstadoTchnLegal(idEtapa);
         } catch (Exception e) {
             
        }
    }
    
    public void nuevaTablaGeneral(){
        try {
            mostrarAddTablaGeneral=true;
            mostrarModiTablaGeneral=false;
            mostrarLisDetalle=false;
            mostrarAddTablaDetalle=false;
            mostrarModiTablaDetalle=false;
            
            this.descripcionTablaGeneral="";
            this.descripcionCorta="";
            this.usuario=SessionUtils.getUserName();
              
        } catch (Exception e) {
        }
    }    
 
    public void insertarTablaGeneral(){
        int rep=1;//se setea error
        showMsg = true;
        System.out.println("insertarTablaGeneral");
        try {
            CobTablas ocobTablas = new CobTablas();
            ocobTablas.setDdescripcion(this.descripcionTablaGeneral);
            ocobTablas.setDdescCorta(this.descripcionCorta);
            
            if (!(this.descripcionTablaGeneral.trim().length()>0)){
                setTipoMsj("error");
                setMensaje("Error: debe llenar descripcion");
                return;
            }            
            
            if (!(this.descripcionCorta.trim().length()>0)){
                setTipoMsj("error");
                setMensaje("Error: debe llenar descripcion abreviada");
                return;
            }            
            
            if (this.descripcionTablaGeneral.trim().length()>0){
               System.out.println("seteando valores a ocobTablas");
               System.out.println(ocobTablas.toString());
               ocobTablas.setcUsuarioAdd(SessionUtils.getUserName());
               System.out.println("Usuario Add:"+ocobTablas.getcUsuarioAdd());
               rep = cobTablasServ.insertarCobTablas(ocobTablas);
            }
            else
            {
                setTipoMsj("error");
                setMensaje("Error no se grabo tabla general");
                return;
            }
            System.out.println("rep:"+rep);
            if (rep == 0) {
                setTipoMsj("success");
                setMensaje("Se grabo tabla general");
                rep =0;
                // se setea la pantalla de acuerdo a la respuesta
                //CobTablas oCobTablas=new CobTablas();
                //oCobTablas.setDdescripcion("");
                buscarTablasGenerales();
                setMostrarAddTablaGeneral(false);
                setMostrarModiTablaGeneral(false);
                setMostrarLisDetalle(false);
                setMostrarModiTablaDetalle(false);
            } else {
                setTipoMsj("error");
                setMensaje("Error no se grabo tabla general");
            }
        } catch (Exception e) {
            showMsg = true;
            setTipoMsj("error");
            setMensaje("Error al insertar seguimiento de Otro proceso judicial");
            System.out.println(e);
        }
     }
    
    public void cerrarPantallaAddTablaGeneral(){
         try {
            setMostrarAddTablaGeneral(false);
            setMostrarModiTablaGeneral(false);
            setMostrarLisDetalle(false);
            setMostrarAddTablaDetalle(false);
            setMostrarModiTablaDetalle(false);
        } catch (Exception e) {
                 
        }
    }
    
    public void cerrarPantallaModiTablaGeneral(){
         try {
            setMostrarAddTablaGeneral(false);
            setMostrarModiTablaGeneral(false);
            setMostrarLisDetalle(false);
            setMostrarAddTablaDetalle(false);
            setMostrarModiTablaDetalle(false);
        } catch (Exception e) {
                 
        }
    }
    
    public void cerrarPantallaAddTablaDetalle(){
         try {
            setMostrarAddTablaGeneral(false);
            setMostrarModiTablaGeneral(false);
            setMostrarLisDetalle(false);
            setMostrarAddTablaDetalle(false);
            setMostrarModiTablaDetalle(false);
        } catch (Exception e) {
                 
        }
    }    
    
    public void modificaTablaGeneral(CobTablas oCobTablas){
        try {
            setMostrarAddTablaGeneral(false);
            setMostrarModiTablaGeneral(true);
            setMostrarLisDetalle(false);
            setMostrarAddTablaDetalle(false);
            setMostrarModiTablaDetalle(false);
            
            this.descripcionTablaGeneral=oCobTablas.getDdescripcion();
            this.descripcionCorta = oCobTablas.getDdescCorta();
            this.usuario=SessionUtils.getUserName();
            this.tablaId=oCobTablas.getCtablaId();
            
        } catch (Exception e) {
            
        }
    }
    
    public void modificarTablaGeneral(){
        try {
              
             CobTablas ocobtablas= new CobTablas();
             ocobtablas.setDdescripcion(this.descripcionTablaGeneral);
             ocobtablas.setDdescCorta(this.descripcionCorta);
             ocobtablas.setCtablaId(this.tablaId);
             
             int resp=cobTablasServ.modificarCobTablas(ocobtablas);
             setMostrarAddTablaGeneral(false);
             setMostrarModiTablaGeneral(false);
             setMostrarLisDetalle(false);
             setMostrarAddTablaDetalle(false);
             setMostrarModiTablaDetalle(false);
             buscarTablasGenerales();
         } catch (Exception e) {
        }
    }    
    
    public void verDetalle(CobTablas oCobTablas){
        try {
            setMostrarAddTablaGeneral(false);
            setMostrarModiTablaGeneral(false);
            setMostrarLisDetalle(true);
            setMostrarAddTablaDetalle(false);
            setMostrarModiTablaDetalle(false);
            codigoDescripcion=oCobTablas.getCtablaId() + "-" + oCobTablas.getDdescripcion();
            tablaId = oCobTablas.getCtablaId();
            setCobDetaList(cobTablasServ.listarDetalle(oCobTablas));
            System.out.println("flagdetalla"+getCobDetaList().get(0).getFlg_detalle());
            System.out.println("flagdetalla"+getCobDetaList().get(0).getEstado());
            
  
        } catch (Exception e) {
            
        }
    }
    
    public void nuevoDetalle(){
        try {
            mostrarAddTablaGeneral=false;
            mostrarModiTablaGeneral=false;
            mostrarLisDetalle=true;
            mostrarAddTablaDetalle=true;
            mostrarModiTablaDetalle=false;
            
            this.descripcionTablaGeneral="";
            this.descripcionCorta="";
            this.usuario=SessionUtils.getUserName();
              
        } catch (Exception e) {
        }
    }
    
    public void insertarTablaDetalle(){
        int rep=1;//se setea error
        showMsg = true;
        System.out.println("insertarTablaDetalle");
        try {
            CobTablas ocobTablas = new CobTablas();
            ocobTablas.setDdescripcion(this.descripcionTablaGeneral);
            ocobTablas.setDdescCorta(this.descripcionCorta);
            ocobTablas.setCodpadre(this.idDocPadre);
            
            if (this.esContDoc.equals("S")) {
                ocobTablas.setFlg_detalle(1);
            }else{
                ocobTablas.setFlg_detalle(0);//detalle 
            }        
           
            if (this.esdetDoc.equals("N")) {
                ocobTablas.seteEstado("01");
            }else{
                ocobTablas.seteEstado("02");//detalle 
            }        
            if (!(this.descripcionTablaGeneral.trim().length()>0)){
                setTipoMsj("error");
                setMensaje("Error: debe llenar descripcion");
                return;
            }            
            
            if (!(this.descripcionCorta.trim().length()>0)){
                setTipoMsj("error");
                setMensaje("Error: debe llenar descripcion abreviada");
                return;
            }            
            
            if (this.descripcionTablaGeneral.trim().length()>0){
               System.out.println("seteando valores a ocobTablas");
               System.out.println(ocobTablas.toString());
               ocobTablas.setcUsuarioAdd(SessionUtils.getUserName());
               System.out.println("Usuario Add:"+ocobTablas.getcUsuarioAdd());
               ocobTablas.setCtablaId(this.tablaId);
               System.out.println("tablaId:"+ocobTablas.getCtablaId());
               System.out.println("cobTablasServ.insertarCobDetalle()");
               rep = cobTablasServ.insertarCobDetalle(ocobTablas);
            }
            else
            {
                setTipoMsj("error");
                setMensaje("Error no se grabo tabla general");
                return;
            }
            System.out.println("rep:"+rep);
            if (rep == 0) {
                setTipoMsj("success");
                setMensaje("Se grabo detalle");
                rep =0;
                // se setea la pantalla de acuerdo a la respuesta
                //CobTablas oCobTablas=new CobTablas();
                //oCobTablas.setDdescripcion("");
                buscarTablasGenerales();
                CobTablas oCobTablas = new CobTablas();
                oCobTablas.setCtablaId(this.tablaId);
                System.out.println("oCobTablas.getCtablaId:" + oCobTablas.getCtablaId());
                setCobDetaList(cobTablasServ.listarDetalle(oCobTablas));
                setMostrarAddTablaGeneral(false);
                setMostrarModiTablaGeneral(false);
                setMostrarLisDetalle(true);
                setMostrarAddTablaDetalle(false);
            } else {
                setTipoMsj("error");
                setMensaje("Error no se grabo tabla general");
            }
        } catch (Exception e) {
            showMsg = true;
            setTipoMsj("error");
            setMensaje("Error al insertar seguimiento de Otro proceso judicial");
            System.out.println(e);
        }
    }
    
    public void modificaDetalle(CobTablas oCobTablas){
        try {
            setMostrarAddTablaGeneral(false);
            setMostrarModiTablaGeneral(false);
            setMostrarLisDetalle(true);
            setMostrarAddTablaDetalle(false);
            setMostrarModiTablaDetalle(true);
            this.listarDocNew();
            this.descripcionTablaGeneral=oCobTablas.getDdescripcion();
            this.descripcionCorta = oCobTablas.getDdescCorta();
            this.usuario=SessionUtils.getUserName();
            this.tablaId=oCobTablas.getCtablaId();
            this.tablaDetalleId= oCobTablas.getCtablaDetId();
            System.out.println("getFlg_detalle()"+oCobTablas.getFlg_detalle());
            System.out.println("getEstado()"+oCobTablas.getEstado());
            if (oCobTablas.getFlg_detalle().equals(1) ){
                this.setEsContDoc("S");
            }else{
                this.setEsContDoc("N");
            }
            
             if (oCobTablas.geteEstado().equals("02") ){
                this.setEsdetDoc("S");
            }else{
                this.setEsdetDoc("N");
            }
              
            this.setIDDocPadre(oCobTablas.getCodpadre());
        } catch (Exception e) {
            
        }
    }    
    
    public void modificarTablaDetalle(){
        try {
              
             CobTablas ocobtablas= new CobTablas();
             ocobtablas.setDdescripcion(this.descripcionTablaGeneral);
             ocobtablas.setDdescCorta(this.descripcionCorta);
             ocobtablas.setCtablaId(this.tablaId);
             ocobtablas.setCtablaDetId(this.tablaDetalleId);
             ocobtablas.setcUsuarioAdd(SessionUtils.getUserName());
             ocobtablas.setCodpadre(this.idDocPadre);
             
            if (this.esContDoc.equals("S")) {
                ocobtablas.setFlg_detalle(1);
            }else{
                ocobtablas.setFlg_detalle(0);//detalle 
            }        
           
            if (this.esdetDoc.equals("N")) {
                ocobtablas.seteEstado("01");
            }else{
                ocobtablas.seteEstado("02");//detalle 
            }
             System.out.println("modificarTablaDetalle");
             System.out.println("ocobtablas:"+ocobtablas.toString());
             
             int resp=cobTablasServ.modificarCobDetalle(ocobtablas);
             buscarTablasGenerales();
             CobTablas oCobTablas = new CobTablas();
             oCobTablas.setCtablaId(this.tablaId);
             setCobDetaList(cobTablasServ.listarDetalle(oCobTablas));
             setMostrarAddTablaGeneral(false);
             setMostrarModiTablaGeneral(false);
             setMostrarLisDetalle(true);
             setMostrarModiTablaDetalle(false);
             
         } catch (Exception e) {
        }
    }        
    
    /**
     * @return the mensajeRpta
     */
    public String getMensajeRpta() {
        return mensajeRpta;
    }

    /**
     * @param mensajeRpta the mensajeRpta to set
     */
    public void setMensajeRpta(String mensajeRpta) {
        this.mensajeRpta = mensajeRpta;
    }
    /**
     * @return the cobTablasList
     */
    public List<CobTablas> getCobTablasList() {
        return cobTablasList;
    }

    /**
     * @param cobTablasList the cobTablasList to set
     */
    public void setCobTablasList(List<CobTablas> cobTablasList) {
        this.cobTablasList = cobTablasList;
    }    

    /**
     * @return the mostrarAddTablaGeneral
     */
    public boolean isMostrarAddTablaGeneral() {
        return mostrarAddTablaGeneral;
    }

    /**
     * @param mostrarAddTablaGeneral the mostrarAddTablaGeneral to set
     */
    public void setMostrarAddTablaGeneral(boolean mostrarAddTablaGeneral) {
        this.mostrarAddTablaGeneral = mostrarAddTablaGeneral;
    }

    /**
     * @return the descripcionTablaGeneral
     */
    public String getDescripcionTablaGeneral() {
        return descripcionTablaGeneral;
    }

    /**
     * @param descripcionTablaGeneral the descripcionTablaGeneral to set
     */
    public void setDescripcionTablaGeneral(String descripcionTablaGeneral) {
        this.descripcionTablaGeneral = descripcionTablaGeneral;
    }

    /**
     * @return the descripcionCorta
     */
    public String getDescripcionCorta() {
        return descripcionCorta;
    }

    /**
     * @param descripcionCorta the descripcionCorta to set
     */
    public void setDescripcionCorta(String descripcionCorta) {
        this.descripcionCorta = descripcionCorta;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the showMsg
     */
    public boolean isShowMsg() {
        return showMsg;
    }

    /**
     * @param showMsg the showMsg to set
     */
    public void setShowMsg(boolean showMsg) {
        this.showMsg = showMsg;
    }
    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }    

    /**
     * @return the tipoMsj
     */
    public String getTipoMsj() {
        return tipoMsj;
    }

    /**
     * @param tipoMsj the tipoMsj to set
     */
    public void setTipoMsj(String tipoMsj) {
        this.tipoMsj = tipoMsj;
    }    

    /**
     * @return the mostrarModiTablaGeneral
     */
    public boolean isMostrarModiTablaGeneral() {
        return mostrarModiTablaGeneral;
    }

    /**
     * @param mostrarModiTablaGeneral the mostrarModiTablaGeneral to set
     */
    public void setMostrarModiTablaGeneral(boolean mostrarModiTablaGeneral) {
        this.mostrarModiTablaGeneral = mostrarModiTablaGeneral;
    }

    /**
     * @return the tablaId
     */
    public String getTablaId() {
        return tablaId;
    }

    /**
     * @param tablaId the tablaId to set
     */
    public void setTablaId(String tablaId) {
        this.tablaId = tablaId;
    }

    /**
     * @return the mostrarLisDetalle
     */
    public boolean isMostrarLisDetalle() {
        return mostrarLisDetalle;
    }

    /**
     * @param mostrarLisDetalle the mostrarLisDetalle to set
     */
    public void setMostrarLisDetalle(boolean mostrarLisDetalle) {
        this.mostrarLisDetalle = mostrarLisDetalle;
    }

    /**
     * @return the cobDetaList
     */
    public List<CobTablas> getCobDetaList() {
        return cobDetaList;
    }

    /**
     * @param cobDetaList the cobDetaList to set
     */
    public void setCobDetaList(List<CobTablas> cobDetaList) {
        this.cobDetaList = cobDetaList;
    }

    /**
     * @return the codigoDescripcion
     */
    public String getCodigoDescripcion() {
        return codigoDescripcion;
    }

    /**
     * @param codigoDescripcion the codigoDescripcion to set
     */
    public void setCodigoDescripcion(String codigoDescripcion) {
        this.codigoDescripcion = codigoDescripcion;
    }

    /**
     * @return the mostrarAddTablaDetalle
     */
    public boolean isMostrarAddTablaDetalle() {
        return mostrarAddTablaDetalle;
    }

    /**
     * @param mostrarAddTablaDetalle the mostrarAddTablaDetalle to set
     */
    public void setMostrarAddTablaDetalle(boolean mostrarAddTablaDetalle) {
        this.mostrarAddTablaDetalle = mostrarAddTablaDetalle;
    }

    /**
     * @return the mostrarModiTablaDetalle
     */
    public boolean isMostrarModiTablaDetalle() {
        return mostrarModiTablaDetalle;
    }

    /**
     * @param mostrarModiTablaDetalle the mostrarModiTablaDetalle to set
     */
    public void setMostrarModiTablaDetalle(boolean mostrarModiTablaDetalle) {
        this.mostrarModiTablaDetalle = mostrarModiTablaDetalle;
    }

    /**
     * @return the tablaDetalleId
     */
    public String getTablaDetalleId() {
        return tablaDetalleId;
    }

    /**
     * @param tablaDetalleId the tablaDetalleId to set
     */
    public void setTablaDetalleId(String tablaDetalleId) {
        this.tablaDetalleId = tablaDetalleId;
    }

    
     public void listarDocNew() {
        try {
            System.out.println("listarDocNew");
             if (cobTablasDocInvList == null) {
                 cobTablasDocInvList = new ArrayList<CobTablas>();
              cobTablasDocuInv.setCtablaId("0004");
              //cobTablasDocInvList = getTablasServ().listarTablasDocusCustodia(cobTablasDocuInv); 
              
              for(CobTablas ct: getTablasServ().listarTablasDocusCustodia(cobTablasDocuInv,null)){
                  
                  int id = Integer.parseInt(ct.getCtablaDetId());
                  
                  if(id > 15){
                      cobTablasDocInvList.add(ct);
                  }
                  
              }
      
            }
                    System.out.println("cobTablasDocInvList"+cobTablasDocInvList.size());
     
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}