/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.bean;

import com.itextpdf.text.DocumentException;
import java.awt.Desktop;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.glassfish.tyrus.core.RequestContext;
import pop.comun.dominio.CobRequerimientoCartas;
import pop.comun.dominio.CobTablas;

import pop.comun.dominio.MaeEmail;
import pop.comun.dominio.MaeEstadoCuenta;
import pop.comun.dominio.MaeFechaPor;

import pop.comun.dominio.MaeFondo;

import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MaePersona;
import pop.comun.dominio.MaeReporte;
import pop.comun.dominio.MaeTelefono;
import pop.comun.dominio.MaeUbigeo;
import pop.webcustodia.filter.SessionUtils;

import pop.webcustodia.iface.IFondoServ;
import pop.webcustodia.iface.ITablasServ;
import pop.webcustodia.iface.IUbigeoServ;

import pop.webcustodia.iface.IFechaPorServ;

import pop.webcustodia.servicio.FechaPorServ;
import pop.webcustodia.servicio.FondoServ;
import pop.webcustodia.servicio.GeneradorDocumentoServ;
import pop.webcustodia.servicio.InversionServ;
import pop.webcustodia.servicio.TablasServ;
import pop.webcustodia.servicio.UbigeoServ;
import pop.webcustodia.util.MailSender;

/**
 *
 * @author Jyoverar
 */
@Named
@ViewScoped
public class GeneradorDocumentoBean implements Serializable {

    private static final long serialVersionUID = 1L;


    

    private MaeFondo maeFondo = new MaeFondo();
    private MaeFechaPor maeFechaPor = new MaeFechaPor();

    
   
    // Persona (busq por documento de la persona)
    private MaePersona maePersona = new MaePersona();
    private List<MaeInversion> maeInversionList2;
    
    private List<CobTablas> cobtablasListSoli;

    
   
    private List<MaeInversion> maeInversionListDoc;

  
    private List<MaeEmail> MaeEmailList;
    private MaeInversion maeInversion = new MaeInversion();
    private MaeInversion maeInversion2 = new MaeInversion();

    public MaeInversion getMaeInversion2() {
        return maeInversion2;
    }

    public void setMaeInversion2(MaeInversion maeInversion2) {
        this.maeInversion2 = maeInversion2;
    }
    private InversionServ inversionServ = new InversionServ();
    private GeneradorDocumentoServ generadorDocumentoServ = new GeneradorDocumentoServ();

    
    // estado inversion
    private CobTablas cobTablasEstInv = new CobTablas();
    private CobTablas cobTablasDocuInv = new CobTablas();
    private CobTablas cobTablasEstInvEC = new CobTablas();
    private CobTablas cobTablasEstInvInd = new CobTablas();
    private CobTablas cobTablasEstInvMov = new CobTablas();    
//asesor busqueda

    public CobTablas getCobTablasEstInvMov() {
        return cobTablasEstInvMov;
    }

    public void setCobTablasEstInvMov(CobTablas cobTablasEstInvMov) {
        this.cobTablasEstInvMov = cobTablasEstInvMov;
    }

    private List<MaeInversion> maeInversionList;
    
    private List<MaeInversion> maeInversionList1;
    private List<MaeInversion> maeInversionList3;
    private List<MaeInversion> maeInversionList4;

    
    ///private List<TuRegistro> listaDeRegistros;  // Asegúrate de tener una lista de registros en tu bean
    private List<MaeInversion> registrosSeleccionados;       
   
   
    private List<MaeFondo> maeFondoList;
    
    
    // lista de fondos
    private List<MaeFechaPor> maeFechaPorList;

   
   
    // lista de estado de inversion
    private List<CobTablas> cobTablasEstInvList;

    private List<CobTablas> cobTablasEstInvList2;
    private List<CobTablas> cobTablasDocInvList;
    private List<CobTablas> cobTablasTipoMovList;

    
    private List<CobTablas> cobTablasMotInvList;

   
    
    private List<CobTablas> cobTablasEstOrigenList;
    
    private List<CobTablas> cobTablasEstInvList3;

    
    private List<CobTablas> cobTablasEstInvListEC;

    
    private List<CobTablas> cobTablasEstInvListInd;
    
    private List<CobTablas> listaTipoConsLiqui;
    private List<CobTablas> listaFormaConsLiqui;    

    
    
    // sericios de ubigeo
    private IUbigeoServ ubigeoServ = new UbigeoServ();
    // servicios de inversion

  
    // servicios de fondos
    private  IFondoServ fondoServ = new FondoServ();
    
    
    
    
    private boolean showMsg = false;
    private String mensaje = "";
    private String tipoMsj = "";
 
    private boolean XViewDocu1= false;
    private boolean XViewDocu2= false;
    private boolean XViewDocu3= false;
    private boolean XViewDocu4= false;
    private boolean XViewDocu5= false;

        
    // servicios de tablas
    private ITablasServ tablasServ = new TablasServ();
    // servicio de cronograma

    private boolean isLoad = false;
  
    private ArrayList<MaeEmail> emailsDestino;
   
    public GeneradorDocumentoBean() {
        try {
            maeInversion.setMaeFondo(maeFondo);
            maeInversion.setcPersonaId(maePersona);
          
        } catch (Exception ex) {
            Logger.getLogger(GeneradorDocumentoBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    public void postLoad(){

        if(!isLoad){
            try {
                HttpSession ses = SessionUtils.getSession();
                CobRequerimientoCartas reqCart = SessionUtils.getRequerimiento();
                
                maeInversion.setCInversion(reqCart.getDvalor_bv());
                maeInversion.setReq_tipo(reqCart.getReqTipo());
                maeInversion.setReq_numero(reqCart.getReqNumero());
                
                buscarDeudores();
                isLoad = true;
                ses.removeAttribute("oReqCar");
            } catch (Exception e) {
            }           
        }
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
    
    
    public void listarFormato(){
            
        try {
          
             cobTablasEstInv.setCtablaId("0008");
              cobTablasEstInv.setCtablaDetId(null);
              cobTablasEstInvList2 = getTablasServ().listarTablas(cobTablasEstInv);
            //System.out.println(" cantidad de Fondos -> " + maeFondoList.size());
        } catch (Exception e) {

        }
      
    }
    
    public void listarCartas(){
        try {
            
             cobTablasEstInv.setCtablaId("0009");
             cobTablasEstInv.setCtablaDetId(maeInversion.getSCODDocumento());
              cobTablasEstInvList = getTablasServ().listarTablasCartasDet(cobTablasEstInv);
        } catch (Exception e) {

        }

    }
    /*
    public void listarTipoConstLiquidacion(){
            
        try {
            
        getMaeInversion().setSCODDocumento("0003");
          
             cobTablasEstInv.setCtablaId("0010");
              cobTablasEstInv.setCtablaDetId(null);
              listaTipoConsLiqui = getTablasServ().listarTablas(cobTablasEstInv);
            //System.out.println(" cantidad de Fondos -> " + maeFondoList.size());
        } catch (Exception e) {

        }
      
    }
    */

    public void initTipoCons(LoginBean login, String title){
            
        try {
     
            login.setPageTitle(title);
            
        } catch (Exception e) {

        }
      
    }
    
    public void initTipoCons(String SCODDocumento, String prev, String next){
            
        try {
     
            getMaeInversion().setPagePrev(prev);
            getMaeInversion().setPageNext(next);
            
          
            getMaeInversion().setSCODDocumento(SCODDocumento);
            
        } catch (Exception e) {

        }
      
    }
    
    
    public void listarFormaConstLiquidacion(String SCODDocumento){
            
        try {
            
            cobTablasEstInv.setCtablaId("0011");
            cobTablasEstInv.setCtablaDetId(null);
            cobTablasEstInv.setCriterio2(SCODDocumento);
            listaFormaConsLiqui = getTablasServ().listarTablaDetalle(cobTablasEstInv);
            
        } catch (Exception e) {

        }
      
    }    
    
    public void buscarNombreFormato(String STCarta){
            
        try {
        
            for(CobTablas formato:listaFormaConsLiqui){
                
                if(formato.getCtablaDetId().equals(STCarta)){
                    maeInversion.setSNombreFormato(formato.getCriterio3());
                    break;
                }
            }
            
        } catch (Exception e) {

        }
      
    }    
    
    public void buscarDeudores() {
        try {
            this.setXViewDocu1(false);
            this.setXViewDocu2(false);
            this.setXViewDocu3(false);
            this.setXViewDocu4(false);
            
              showMsg = true;
        
              cobTablasEstInv.setCtablaId("0008");
              cobTablasEstInv.setCtablaDetId(null);
              cobTablasEstInvList2 = getTablasServ().listarTablas(cobTablasEstInv);

            maeInversion.setcUsuarioAdd(SessionUtils.getUserName());
            maeInversionList = getGeneradorDocumentoServ().listarPropietarios(maeInversion);
            if (maeInversionList.size()==0){
                 showMsg = true;
                this.setXViewDocu4(true);
                    mensaje = "No hay informacion en custodia, Verificar ";
                    tipoMsj = "error";
 
            }
            
            maeInversionList2.clear();
            maeInversionList1.clear();
              
        } catch (Exception e) {
            System.err.println("pop.webcustodia.bean.InversionBean.buscarDeudores()");
        }
    }
    
   public void buscarDeudor() {
        try {
                        
            if (maeInversion.getCInversion()==null || maeInversion.getCInversion().isEmpty()){
                this.setXViewDocu3(true);
                showMsg = false;
                this.setXViewDocu4(false);                
            }else{
                this.setXViewDocu1(false);
                this.setXViewDocu2(false);
                this.setXViewDocu3(false);
                this.setXViewDocu4(false); 
            }
             
           if(this.isXViewDocu3()){
           }else{
            showMsg = true;

            cobTablasEstInv.setCtablaId("0008");
            cobTablasEstInv.setCtablaDetId(null);
            cobTablasEstInvList2 = getTablasServ().listarTablas(cobTablasEstInv);

            maeInversion.setcUsuarioAdd(SessionUtils.getUserName());
            maeInversionList = getGeneradorDocumentoServ().listarClientes(maeInversion);
              
            if (maeInversionList.size()==0){
                showMsg = true;
                this.setXViewDocu4(true);
                mensaje = "No se identificó ninguna coincidencia";
                tipoMsj = "error"; 
            }else{                
                this.setMaeInversion(maeInversionList.get(0));
                maeInversionList4 = getGeneradorDocumentoServ().listarAllPropietarios(maeInversion);
            }
           }
            
        } catch (Exception e) {
            System.err.println("ERRO 11114: "+e.getMessage());
        }
    }    
    
    
    public void buscarCartas() {
        try {
            maeInversion.setcUsuarioAdd(SessionUtils.getUserName());
            maeInversionList = getGeneradorDocumentoServ().listarCartas(maeInversion);
            showMsg = false;
            this.setXViewDocu4(false);
            if (maeInversionList.size()==0)
            {
               this.setXViewDocu4(true);
                showMsg = true;
    
            }     
           showMsg = false;    
          mensaje="";  
        } catch (Exception e) {
            System.err.println("pop.webcustodia.bean.InversionBean.buscarDeudores()");
        }
    }


    public void buscarConstancias() {
        try {
            

            maeInversion.setcUsuarioAdd(SessionUtils.getUserName());
            maeInversionList = getGeneradorDocumentoServ().listarConstancias(maeInversion);
            
           
            showMsg = false;
            this.setXViewDocu4(false);
            if (maeInversionList.size()==0)
            {
               this.setXViewDocu4(true);
                showMsg = true;
            }
           showMsg = false;    
           mensaje="";  
        } catch (Exception e) {
            System.err.println("pop.webcustodia.bean.InversionBean.buscarConstancias()");
        }
    }
    
    public String acciones(int opcion) {
        
        MaeInversion newMaeInversion = null;
        String url = null;
        
        try {
            switch(opcion) {
              case 1:
                    url = maeInversion.getPageNext()+"?faces-redirect=true";  
                break;
              case 2:
                // code block
                break;
              case 3:
                for(MaeInversion in: maeInversionList){
                    System.err.println("MaeInversion in: maeInversionList "+in.getNDocNumero()+"  "+in.isSelected());
                     in.setcUsuarioMod(SessionUtils.getUserName());                     
                    if(in.isSelected()){
                        newMaeInversion = getGeneradorDocumentoServ().anulaConstancia(in);                    
                        buscarConstancias();
                    }
                }
                
                break;    
              default:
                // code block
            }
            
           showMsg = false;    
           mensaje="";  
        } catch (Exception e) {
            System.err.println("pop.webcustodia.bean.InversionBean.buscarConstancias()");
        }
        
     return url;          
    }   

    public void generarCarta(MaeInversion oMaeInversion) {
        try {
          showMsg = false;
          int respGen =0;
          
          
          System.out.println("****: --"+oMaeInversion.getSTCarta()+ " || " + oMaeInversion.getcPersonaId().getANroDocumento());
          
          if (oMaeInversion.getSTCarta()!= null &&   oMaeInversion.getSCODDocumento().equals("0001") ) {
           if (oMaeInversion.getFEmision()!= null)  
            {
                 int resp=0;
                    String lsArchivo="";
                    String lscarta="";
                    if (oMaeInversion.getSTCarta().equals("0001")){
                        lscarta = "1raCarta";
                    }
                    if (oMaeInversion.getSTCarta().equals("0002")){
                        lscarta = "2daCarta"; 
                    }
                    if (oMaeInversion.getSTCarta().equals("0003")){
                        lscarta = "3raCarta";
                    }
                    if (oMaeInversion.getSTCarta().equals("0004")){
                        lscarta = "4taCarta";
                    }
                    if (oMaeInversion.getSTCarta().equals("0005")){
                        lscarta = "5taCarta";
                    }
                    if (oMaeInversion.getSTCarta().equals("0007")){
                        lscarta = "1raCartaSinTchn";
                    }
                    if (oMaeInversion.getSTCarta().equals("0008")){
                        lscarta = "1raCartaSinTchnAsiento";
                    }
                    if (oMaeInversion.getSTCarta().equals("0009")){
                        lscarta = "2daCartaNotarialSintchn";
                    }
                    if (oMaeInversion.getSTCarta().equals("0010")){
                        lscarta = "2daCartaNotarialSintchnAsiento";
                    }    
                    if (oMaeInversion.getSTCarta().equals("0011")){
                        lscarta = "6taComunicacionCesionPJ";
                    }   
                    
                            
                    MaeInversion newMaeInversion=new MaeInversion();
                    MaeFondo newFondo = new MaeFondo();
                    newFondo.setCFondoId(maeInversion.getCorigenId());
                    newMaeInversion.setMaeFondo(newFondo);
                    newMaeInversion.setCInversion(maeInversion.getCgeneraDoc());
                    newMaeInversion.setFEmision(oMaeInversion.getFEmision());
                    newMaeInversion.setSTCarta(oMaeInversion.getSTCarta());
                    newMaeInversion.setSCODDocumento(oMaeInversion.getSCODDocumento());
                    newMaeInversion.setcUsuarioAdd(SessionUtils.getUserName());
                    newMaeInversion.setReq_tipo(oMaeInversion.getReq_tipo());
                    newMaeInversion.setReq_numero(oMaeInversion.getReq_numero());
                    
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                    String fechaComoCadena = sdf.format(oMaeInversion.getFEmision());
                    lsArchivo= lscarta+"_"+fechaComoCadena+"_"+newMaeInversion.getMaeFondo().getCFondoId().trim()+"_"+newMaeInversion.getCInversion().trim() + ".docx";
                    newMaeInversion.setSNombreArchivo(lsArchivo);
                
                   
                    respGen = getGeneradorDocumentoServ().generaCarta1(newMaeInversion);
                    if (respGen==0){ 
                        resp= getGeneradorDocumentoServ().insertarGeneraDoc(newMaeInversion);
                        maeInversionList2 = getGeneradorDocumentoServ().listarHistoricoCarta(newMaeInversion);

                        if (resp==0) {
                           showMsg = true;
                             tipoMsj = "success";
                             mensaje = "se genero  Informacion a correctamente";
                        }else{
                             showMsg = true;
                             tipoMsj = "error";
                             mensaje = "se produjo error en registrar informacion";
                        }   
                    }else{
                      if (newMaeInversion.getSTCarta().equals("0005")){
                        showMsg = true;
                        tipoMsj = "error";
                        mensaje = "se produjo error en registrar informacion, Tchn no esta endosado";
                      }else{
                            showMsg = true;
                          tipoMsj = "error";
                            mensaje = "se produjo error en registrar informacion";
                      
                      }  
                    }
                         this.setXViewDocu4(true);
                          maeInversion.setFEmision(null);
                          maeInversion.setSTCarta(null);
                    
                                 showMsg = false;
      
            }else{
                        this.setXViewDocu4(true);
                         this.setXViewDocu3(false);

                        showMsg = true;
                        tipoMsj = "error";
                        mensaje = "Ingresar Informacion a registrar1";
                                showMsg = false;
           }
          }else { if (oMaeInversion.getSTCarta()!= null &&  oMaeInversion.getSCODDocumento().equals("0002") )
                    {   
                               int resp=0;
                               String lsArchivo="";
                               String lscarta="";
                                   lscarta = " ";
                               java.util.Date fecha = new Date();    
                 
                               MaeInversion newMaeInversion=new MaeInversion();
                               MaeFondo newFondo = new MaeFondo();
                               newFondo.setCFondoId(maeInversion.getCorigenId());
                               newMaeInversion.setMaeFondo(newFondo);
                               newMaeInversion.setCInversion(maeInversion.getCgeneraDoc());
                               newMaeInversion.setFEmision(fecha);
                               newMaeInversion.setSTCarta(oMaeInversion.getSTCarta());
                               newMaeInversion.setSCODDocumento(oMaeInversion.getSCODDocumento());
                               newMaeInversion.setcUsuarioAdd(SessionUtils.getUserName());
                               SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                               newMaeInversion.setSNombreArchivo("");

                               resp= getGeneradorDocumentoServ().insertarGeneraDoc(newMaeInversion);
                               maeInversionList2 = getGeneradorDocumentoServ().listarHistoricoCarta(newMaeInversion);

                               if (resp==0) {
                                  showMsg = true;
                                    tipoMsj = "success";
                                    mensaje = "se genero  Informacion a correctamente";
                               }else{
                                    showMsg = true;
                                    tipoMsj = "error";
                                    mensaje = "se produjo error en registrar informacion1";
                               }   

                                    this.setXViewDocu4(true);
                                     maeInversion.setFEmision(null);
                                     maeInversion.setSTCarta(null);
                                            showMsg = false;
                    }else{
                       if (oMaeInversion.getSCODDocumento().equals("0003") )
                    {   
                               int resp=0;
                               String lsArchivo="";
                               String lscarta="";
                                   lscarta = " ";
                                   
                               MaeInversion newMaeInversion=new MaeInversion();
                               MaeFondo newFondo = new MaeFondo();
                               newFondo.setCFondoId(maeInversion.getCorigenId());
                               newMaeInversion.setMaeFondo(newFondo);
                               newMaeInversion.setCInversion(maeInversion.getCgeneraDoc());
                               
                               newMaeInversion.setFEmision(oMaeInversion.getFEmision());
                               newMaeInversion.setSTCarta(null);
                               newMaeInversion.setSCODDocumento(oMaeInversion.getSCODDocumento());
                               newMaeInversion.setcUsuarioAdd(SessionUtils.getUserName());
                               SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                               newMaeInversion.setSNombreArchivo("");
                               resp= getGeneradorDocumentoServ().insertarGeneraDoc(newMaeInversion);
                               maeInversionList2 = getGeneradorDocumentoServ().listarHistoricoCarta(newMaeInversion);
                               
                               if (resp==0) {
                                  showMsg = true;
                                    tipoMsj = "success";
                                    mensaje = "se genero  Informacion a correctamente";
                               }else{
                                    showMsg = true;
                                    tipoMsj = "error";
                                    mensaje = "se produjo error en registrar informacion1";
                               }   

                                    this.setXViewDocu4(true);
                                     maeInversion.setFEmision(null);
                                     maeInversion.setSTCarta(null);
                                            showMsg = false;
                    }else{ 
                    
                       this.setXViewDocu4(true);
                         this.setXViewDocu3(false);

                        showMsg = true;
                        tipoMsj = "error";
                        mensaje = "Ingresar Informacion a registrar2";
                                showMsg = false;
                    }
                }
            }
                
                    
            
        } catch (Exception e) {
            System.err.println("pop.webcustodia.bean.InversionBean.buscarDeudores()");
        }
    }
    
    public String generarConstancia(MaeInversion oMaeInversion) {
        try {
          showMsg = false;
          int vdoc_num = 0;
          int respGen =0;
          
          if(oMaeInversion.getcPersonaId().getANroDocumento() == null ){
            this.setXViewDocu4(true);
            this.setXViewDocu3(false);              
              
            showMsg = true;
            tipoMsj = "error";
            mensaje = "Debe ingresar los datos del cliente";
            return null;
          }
          
          if(oMaeInversion.getSCODDocumento() == null){
            this.setXViewDocu4(true);
            this.setXViewDocu3(false);              
              
            showMsg = true;
            tipoMsj = "error";
            mensaje = "Debe ingresar el tipo de constancia";
            return null;
          }           
          
          if(oMaeInversion.getSTCarta() == null ){
            this.setXViewDocu4(true);
            this.setXViewDocu3(false);              
              
            showMsg = true;
            tipoMsj = "error";
            mensaje = "Debe ingresar el formato de constancia";
            return null; 
          }
          
          if(oMaeInversion.getFEmision() == null ){
            this.setXViewDocu4(true);
            this.setXViewDocu3(false);              
              
            showMsg = true;
            tipoMsj = "error";
            mensaje = "Debe ingresar la fecha de emisión";
            return null;        
          }          
          
          
                 int resp=0;
                    String lsArchivo="";
                    String lscarta="";
               
                    
                    MaeInversion newMaeInversion=new MaeInversion();
                    MaeFondo newFondo = new MaeFondo();
                    newFondo.setCFondoId(oMaeInversion.getMaeFondo().getCFondoId());
                    newMaeInversion.setMaeFondo(newFondo);
                    newMaeInversion.setCInversion(oMaeInversion.getCInversion());
                    newMaeInversion.setFEmision(oMaeInversion.getFEmision());
                    newMaeInversion.setSTCarta(oMaeInversion.getSTCarta());
                    newMaeInversion.setSCODDocumento(oMaeInversion.getSCODDocumento());
                    newMaeInversion.setcUsuarioAdd(SessionUtils.getUserName());
                    newMaeInversion.setSComentario(oMaeInversion.getSComentario());
                    
                    System.out.println("inta1");
                    
                    vdoc_num = getGeneradorDocumentoServ().insertarConstLiqui(newMaeInversion);
                    
                    
                    newMaeInversion.setNDocNumero(vdoc_num);
                    newMaeInversion.setSTipoDoc("CL");
                    
                      System.out.println("RESULTADO");
                    
                    
                    respGen = getGeneradorDocumentoServ().generaConstancia(newMaeInversion);
                    
                    
                    if (respGen==0){ 
                        //resp= getGeneradorDocumentoServ().insertarConstLiqui(newMaeInversion);
                        
                        System.err.println("resp= getGeneradorDocumentoServ().insertarConstLiqui(newMaeInversion)");
                        
                        return oMaeInversion.getPagePrev()+"?faces-redirect=true&num"+vdoc_num;  
                        
                    }
                    
                    this.setXViewDocu4(true);
                    maeInversion.setFEmision(null);
                    maeInversion.setSTCarta(null);
                    showMsg = false;
      
                      
        } catch (Exception e) {
            System.out.println("============================================== ERROR 0 =================================================");
            System.err.println("error 286556: "+e.getMessage());
            e.printStackTrace();
        }
        
        return null;
    }
        
    
    public String anulaConstancia(MaeInversion oMaeInversion) {
        try {
          showMsg = false;
          int vdoc_num = 0;
          int respGen =0;
          
          if(oMaeInversion.getcPersonaId().getANroDocumento() == null ){
            this.setXViewDocu4(true);
            this.setXViewDocu3(false);              
              
            showMsg = true;
            tipoMsj = "error";
            mensaje = "Debe ingresar los datos del cliente";
            return null;
          }
          
          if(oMaeInversion.getSCODDocumento() == null){
            this.setXViewDocu4(true);
            this.setXViewDocu3(false);              
              
            showMsg = true;
            tipoMsj = "error";
            mensaje = "Debe ingresar el tipo de constancia";
            return null;
          }           
          
          if(oMaeInversion.getSTCarta() == null ){
            this.setXViewDocu4(true);
            this.setXViewDocu3(false);              
              
            showMsg = true;
            tipoMsj = "error";
            mensaje = "Debe ingresar el formato de constancia";
            return null; 
          }
          
          if(oMaeInversion.getFEmision() == null ){
            this.setXViewDocu4(true);
            this.setXViewDocu3(false);              
              
            showMsg = true;
            tipoMsj = "error";
            mensaje = "Debe ingresar la fecha de emisión";
            return null;        
          }          
          
          
          
                 System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°Campos requeridos fueron superados");
          
          
                 int resp=0;
                    String lsArchivo="";
                    String lscarta="";
               
                    
                    MaeInversion newMaeInversion=new MaeInversion();
                    MaeFondo newFondo = new MaeFondo();
                    newFondo.setCFondoId(oMaeInversion.getMaeFondo().getCFondoId());
                    newMaeInversion.setMaeFondo(newFondo);
                    newMaeInversion.setCInversion(oMaeInversion.getCInversion());
                    newMaeInversion.setFEmision(oMaeInversion.getFEmision());
                    newMaeInversion.setSTCarta(oMaeInversion.getSTCarta());
                    newMaeInversion.setSCODDocumento(oMaeInversion.getSCODDocumento());
                    newMaeInversion.setcUsuarioAdd(SessionUtils.getUserName());
                    newMaeInversion.setSComentario(oMaeInversion.getSComentario());
                    
                    
                    
                    vdoc_num = getGeneradorDocumentoServ().insertarConstLiqui(newMaeInversion);
                    
                    System.err.println("vdoc_num +++++++: "+vdoc_num);
                    
                    newMaeInversion.setNDocNumero(vdoc_num);
                    newMaeInversion.setSTipoDoc("CL");
                    
                    respGen = getGeneradorDocumentoServ().generaConstancia(newMaeInversion);
                    
                    
                    if (respGen==0){ 
                        //resp= getGeneradorDocumentoServ().insertarConstLiqui(newMaeInversion);
                        
                        System.err.println("resp= getGeneradorDocumentoServ().insertarConstLiqui(newMaeInversion)");
                        
                        return oMaeInversion.getPagePrev()+"?faces-redirect=true&num"+vdoc_num;  
                        
                    }
                    
                    this.setXViewDocu4(true);
                    maeInversion.setFEmision(null);
                    maeInversion.setSTCarta(null);
                    showMsg = false;
      
                      
        } catch (Exception e) {
            System.err.println("error 286556: "+e.getMessage());
        }
        
        return null;
    }    
    
    public void registraInformacion( MaeInversion oMaeInversion) {
        try {
            showMsg = false;
            this.setXViewDocu4(false);
            ArrayList<MaeInversion> lstMae = new ArrayList<>();
            MaeInversion newMaeInversion=new MaeInversion();
            MaeFondo newFondo = new MaeFondo();
            newFondo.setCFondoId(maeInversion.getCorigenId());
            newMaeInversion.setMaeFondo(newFondo);
            newMaeInversion.setCInversion(maeInversion.getCgeneraDoc());
            newMaeInversion.setFEmision(oMaeInversion.getFEmision());
            newMaeInversion.setSTCarta(oMaeInversion.getSTCarta());
            newMaeInversion.setSCODDocumento(oMaeInversion.getSCODDocumento());
            if (oMaeInversion.getSCODDocumento().equals("0002")){
                newMaeInversion.setSEstado("1");   
              }else{
                newMaeInversion.setSEstado(oMaeInversion.getSEstado());
             }
            newMaeInversion.setFRegistro(oMaeInversion.getDFechaIng());
            newMaeInversion.setSNombreSol(oMaeInversion.getSNombreSol());
            newMaeInversion.setSComentario(oMaeInversion.getSComentario());
            newMaeInversion.setcUsuarioAdd(SessionUtils.getUserName());
            lstMae.add(newMaeInversion);
            
            maeInversionList3=lstMae;

            maeInversion2.setMaeFondo(newFondo);
            maeInversion2.setCInversion(maeInversion.getCgeneraDoc());
            maeInversion2.setFEmision(oMaeInversion.getFEmision());
            maeInversion2.setSTCarta(oMaeInversion.getSTCarta());
            maeInversion2.setSCODDocumento(oMaeInversion.getSCODDocumento());
            maeInversion2.setSEstado(oMaeInversion.getSEstado());
            maeInversion2.setSNombreSol("");
            maeInversion2.setSComentario("");
            maeInversion2.setcUsuarioAdd(SessionUtils.getUserName());
            maeInversion2.setFRegistro(oMaeInversion.getDFechaIng());
            
            
         
            //this.setXViewDocu1(false);
             //this.setXViewDocu2(false);
             this.setXViewDocu3(true);
            
          
        } catch (Exception e) {
            System.err.println("pop.webcustodia.bean.InversionBean.buscarDeudores()"+e.getMessage());
        }
    }
    
    
    
    public void obtenerinfo(MaeInversion oMaeInversion) {
        try {
            maeInversion.setcUsuarioAdd(SessionUtils.getUserName());
            maeInversion.setSCODDocumento(null);
            //maeInversion.getMaeFondo().setCFondoId(oMaeInversion.getMaeFondo().getCFondoId());
           // maeInversion.setCInversion(oMaeInversion.getCInversion());
            //maeInversionList2 = getGeneradorDocumentoServ().listarHistoricoCarta(oMaeInversion);
            maeInversion.setCgeneraDoc(oMaeInversion.getCInversion());  // tchn
            maeInversion.setCorigenId(oMaeInversion.getMaeFondo().getCFondoId()); //fondo
            
            cobTablasEstInv.setCtablaId("0008");
            cobTablasEstInv.setCtablaDetId(null);
            cobTablasEstInvList2 = getTablasServ().listarTablas(cobTablasEstInv);
            
            cobTablasEstInv.setCtablaId("0009");
            cobTablasEstInv.setCtablaDetId(null); 
            cobTablasDocInvList = getTablasServ().listarTablas(cobTablasEstInv);
            setXViewDocu1(true); 
            setXViewDocu2(true); 
     
           showMsg = false;
          mensaje="";  
        } catch (Exception e) {
            System.err.println("pop.webcustodia.bean.InversionBean.buscarDeudores()");
        }
    }

    public void obtenerHistorico(String sCODDocumento) {
        try {
            maeInversion.setcUsuarioAdd(SessionUtils.getUserName());
            //maeInversion.getMaeFondo().setCFondoId(oMaeInversion.getMaeFondo().getCFondoId());
           // maeInversion.setCInversion(oMaeInversion.getCInversion());
     
           if   (sCODDocumento.equals("0002")){
                               int resp=0;
                               String lsArchivo="";
                               String lscarta="";
                                   lscarta = " ";
                              
                               MaeInversion newMaeInversion=new MaeInversion();
                               MaeFondo newFondo = new MaeFondo();
                               newFondo.setCFondoId(maeInversion.getCorigenId());
                               newMaeInversion.setMaeFondo(newFondo);
                               newMaeInversion.setCInversion(maeInversion.getCgeneraDoc());
                               newMaeInversion.setFEmision(null);
                               newMaeInversion.setSTCarta(null);
                               newMaeInversion.setSCODDocumento(sCODDocumento);
                               newMaeInversion.setcUsuarioAdd(SessionUtils.getUserName());
                               SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                               newMaeInversion.setSNombreArchivo("");

                               resp= getGeneradorDocumentoServ().insertarGeneraDoc(newMaeInversion);
        
                               if (resp==0) {
                                  showMsg = true;
                                    tipoMsj = "success";
                                    mensaje = "se genero  Informacion a correctamente";
                               }else{
                                    showMsg = true;
                                    tipoMsj = "error";
                                    mensaje = "se produjo error en registrar informacion1";
                               }   

                                    this.setXViewDocu4(true);
                                     maeInversion.setFEmision(null);
                                     maeInversion.setSTCarta(null);
                                            showMsg = false;
                    }else{
                    
                       this.setXViewDocu4(true);
                         this.setXViewDocu3(false);

                        showMsg = true;
                        tipoMsj = "error";
                        mensaje = "Ingresar Informacion a registrar2";
                                showMsg = false;
                    }
              
            
           maeInversion.getMaeFondo().setCFondoId(maeInversion.getCorigenId());
           maeInversion.setsDocumentoId(sCODDocumento);
           maeInversion.setCInversion(maeInversion.getCgeneraDoc() );
            maeInversionList2 = getGeneradorDocumentoServ().listarHistoricoCarta(maeInversion);
            cobTablasEstInv.setCtablaId("0008");
            cobTablasEstInv.setCtablaDetId(null);
              cobTablasEstInvList2 = getTablasServ().listarTablas(cobTablasEstInv);
             cobTablasEstInv.setCtablaId("0009");
             cobTablasEstInv.setCtablaDetId(sCODDocumento);
              cobTablasDocInvList = getTablasServ().listarTablasCartasDet(cobTablasEstInv);
         
            setXViewDocu1(true); 
            setXViewDocu2(true); 
     
           showMsg = false;
          mensaje="";  
        } catch (Exception e) {
            System.err.println("pop.webcustodia.bean.InversionBean.buscarDeudores()");
        }
    }

    
    public void guardarInformacion(MaeInversion oMaeInversion){
        try { 
        showMsg = true;
        setXViewDocu4(true); 
        setXViewDocu3(false); 
          Integer resp=0;
          String lsArchivo="";
          if ( maeInversion.getSCODDocumento().equals("0001")){
                    if ( oMaeInversion.getFRegistro()!= null && oMaeInversion.getSEstado()!= null && oMaeInversion.getSComentario() != null && oMaeInversion.getSNombreSol()!= null){

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                        String fechaComoCadena = sdf.format(oMaeInversion.getFRegistro());
                        String lscarta="";
                        String lsTipoop="";
                        if (oMaeInversion.getSTCarta().equals("0001")){
                            lscarta = "1raCarta";
                        }
                        if (oMaeInversion.getSTCarta().equals("0002")){
                            lscarta = "2daCarta"; 
                        }
                        if (oMaeInversion.getSTCarta().equals("0003")){
                            lscarta = "3raCarta";
                        }
                        if (oMaeInversion.getSTCarta().equals("0004")){
                            lscarta = "4taCarta";
                        }
                        if (oMaeInversion.getSTCarta().equals("0005")){
                            lscarta = "5taCarta";
                        }

                        if (oMaeInversion.getSEstado().equals("1")){
                            lsTipoop="Rec";
                        }        

                        if (oMaeInversion.getSEstado().equals("2")){
                            lsTipoop="Bajo";
                        }        

                        if (oMaeInversion.getSEstado().equals("3")){
                            lsTipoop="SinNot";
                        }        

                        resp=getGeneradorDocumentoServ().insertarGeneraDoc(oMaeInversion);

                        maeInversionList2 = getGeneradorDocumentoServ().listarHistoricoCarta(oMaeInversion);
                        maeInversionList3.clear();

                        if (resp==0) {
                           showMsg = true;
                             tipoMsj = "success";
                             mensaje = "se guardo  Informacion a correctamente";
                        }else{
                             showMsg = true;
                             tipoMsj = "error";
                             mensaje = "se produjo error en registrar informacion";

                        }   

                     }else{
                             showMsg = true;
                             tipoMsj = "error";
                             mensaje = "Ingresar Informacion a registrar";

                     }
          }else{
           if ( maeInversion.getSCODDocumento().equals("0002")){
              if ( oMaeInversion.getFRegistro()!= null && oMaeInversion.getSEstado().equals("1") && oMaeInversion.getSComentario() != null   ){

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                        String fechaComoCadena = sdf.format(oMaeInversion.getFRegistro());
                        String lscarta="";
                        String lsTipoop="";
                        if (oMaeInversion.getSTCarta().equals("0001")){
                            lscarta = "1raCarta";
                        }
                        if (oMaeInversion.getSTCarta().equals("0002")){
                            lscarta = "2daCarta"; 
                        }
                        if (oMaeInversion.getSTCarta().equals("0003")){
                            lscarta = "3raCarta";
                        }
                        if (oMaeInversion.getSTCarta().equals("0004")){
                            lscarta = "4taCarta";
                        }
                        if (oMaeInversion.getSTCarta().equals("0005")){
                            lscarta = "5taCarta";
                        }

                        if (oMaeInversion.getSEstado().equals("1")){
                            lsTipoop="Rec";
                        }        

                        if (oMaeInversion.getSEstado().equals("2")){
                            lsTipoop="Bajo";
                        }        

                        if (oMaeInversion.getSEstado().equals("3")){
                            lsTipoop="SinNot";
                        }        

                        resp=getGeneradorDocumentoServ().insertarGeneraDoc(oMaeInversion);

                        maeInversionList2 = getGeneradorDocumentoServ().listarHistoricoCarta(oMaeInversion);
                        maeInversionList3.clear();

                        if (resp==0) {
                           showMsg = true;
                             tipoMsj = "success";
                             mensaje = "se guardo  Informacion a correctamente";
                        }else{
                             showMsg = true;
                             tipoMsj = "error";
                             mensaje = "se produjo error en registrar informacion";

                        }   
                     }      
                     }else{
                        if ( oMaeInversion.getFRegistro()!= null && oMaeInversion.getSComentario() != null   ){

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                        String fechaComoCadena = sdf.format(oMaeInversion.getFRegistro());
                        String lscarta="";
                        
                        oMaeInversion.setSEstado("1");
                        resp=getGeneradorDocumentoServ().insertarGeneraDoc(oMaeInversion);
                       
                        maeInversionList2 = getGeneradorDocumentoServ().listarHistoricoCarta(oMaeInversion);
                        maeInversionList3.clear();

                        if (resp==0) {
                           showMsg = true;
                             tipoMsj = "success";
                             mensaje = "se guardo  Informacion a correctamente";
                        }else{
                             showMsg = true;
                             tipoMsj = "error";
                             mensaje = "se produjo error en registrar informacion";

                        }
                       }else{ 
                             showMsg = true;
                             tipoMsj = "error";
                             mensaje = "Ingresar Informacion a registrar";
                         }       
                     }
          
          }     
            
        } catch (Exception e) {
            System.err.println("pop.webcustodia.bean.InversionBean.InsertarCustodia()"+e.getMessage());
        }
        
    }
    
    public void anulaInformacion(MaeInversion oMaeInversion){
        try { 
        showMsg = true;
        setXViewDocu4(true); 
        setXViewDocu3(false); 
          Integer resp=0;          
        
          System.err.println("anulaInformacion "+maeInversion.getSCODDocumento());
          
           if ( maeInversion.getSCODDocumento().equals("0001")){

                resp=getGeneradorDocumentoServ().anularGeneraDoc(oMaeInversion);

                maeInversionList2 = getGeneradorDocumentoServ().listarHistoricoCarta(oMaeInversion);
                maeInversionList3.clear();

                if (resp==0) {
                   showMsg = true;
                     tipoMsj = "success";
                     mensaje = "se elimino Informacion a correctamente";
                }else{
                     showMsg = true;
                     tipoMsj = "error";
                     mensaje = "se produjo error en registrar informacion";
                }   
            }              
            
        } catch (Exception e) {
            System.err.println("pop.webcustodia.bean.InversionBean.anulaInformacion()"+e.getMessage());
        }
        
    }    

    public void salirDetalle() {
        try {
           setXViewDocu3(false); 
           setXViewDocu4(false); 
           setXViewDocu2(true); 
           setXViewDocu1(true); 
    
           maeInversion2.setFEmision(null);
           maeInversion2.setSTCarta("");
           maeInversion2.setSCODDocumento("");
           maeInversion2.setSNombreSol("");
           maeInversion2.setSComentario("");
           maeInversion2.setSEstado("");
           maeInversion2.setFRegistro(null);
           
           maeInversionList3.clear();
           
           showMsg = false;
          mensaje="";  
        } catch (Exception e) {
            System.err.println("pop.webcustodia.bean.InversionBean.buscarDeudores()");
        }
    }
    

    public void exportarPDF(String  Archivo) {
        try {
           setXViewDocu3(false); 
           setXViewDocu4(false); 
           
          System.out.println("nombre de Archivo:  " + Archivo);
          
          
          String[] Datos = Archivo.split("_");
          String lsfondo = null;
          String lsinversion = null;
          
            lsfondo= Datos[0];          
            lsinversion= Datos[1];

           
            String ruta="C:\\pop\\webcustodia\\files\\document\\constancia\\" + lsfondo.trim() + "\\"+lsinversion+"\\"+Archivo;
           
            File file = new File(ruta);

            FileInputStream fis = new FileInputStream(file);
            //System.out.println(file.exists() + "!!");
            //InputStream in = resource.openStream();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            try {
                for (int readNum; (readNum = fis.read(buf)) != -1;) {
                    bos.write(buf, 0, readNum); //no doubt here is 0
                    //Writes len bytes from the specified byte array starting at offset off to this byte array output stream.
                }
            } catch (IOException ex) {
            }
            byte[] bytes = bos.toByteArray();
            //below is the different part
            File someFile = new File(ruta);
            FileOutputStream fos = new FileOutputStream(someFile);
            fos.write(bytes);
            fos.flush();
            fos.close();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] asss = null;
            asss = bytes;
        
            baos.write(asss);

            FacesContext context = FacesContext.getCurrentInstance();
            Object response = context.getExternalContext().getResponse();
     
            if (response instanceof HttpServletResponse) {
                HttpServletResponse hsr = (HttpServletResponse) response;
                //hsr.setContentType("application/pdf");
                hsr.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                hsr.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
                hsr.setHeader("Content-disposition", "inline;filename=\"" + Archivo.substring(0, Archivo.length() - 4 ) );
                hsr.setContentLength(baos.size());
                try {
                    ServletOutputStream out = hsr.getOutputStream();
                    baos.writeTo(out);
                    out.flush();
                } catch (IOException ex) {
                   System.out.println("Error:  " + ex.getMessage());
                }
                context.responseComplete();
            }
            
           
          
          showMsg = true;
          tipoMsj = "success";
          mensaje = "Generando archivo WORD";
          showMsg = false;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void exportarDocx(String  Archivo) {
        try {
           setXViewDocu3(false); 
           setXViewDocu4(false); 
           
          System.out.println("nombre de carta notarial:  " + Archivo);
          
          
            String[] Datos = Archivo.split("_");
            String lsfondo = null;
            String lsinversion = null;
          
            lsfondo= Datos[2];          
            lsinversion= Datos[3];
            lsinversion= lsinversion.replace(".docx", "").trim();

           //ruta = "C:\\pop\\webcustodia\\files\\document\\legal\\" + oMaeInversion.getMaeFondo().getCFondoId().trim() + "\\" + oMaeInversion.getCInversion().trim() + "\\" + name;
           
            String ruta="C:\\pop\\webcustodia\\files\\document\\legal\\" + lsfondo.trim() + "\\"+lsinversion+"\\"+Archivo;
           
            
            File file = new File(ruta);

            FileInputStream fis = new FileInputStream(file);
            //System.out.println(file.exists() + "!!");
            //InputStream in = resource.openStream();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            try {
                for (int readNum; (readNum = fis.read(buf)) != -1;) {
                    bos.write(buf, 0, readNum); //no doubt here is 0
                    //Writes len bytes from the specified byte array starting at offset off to this byte array output stream.
                }
            } catch (IOException ex) {
            }
            byte[] bytes = bos.toByteArray();
            //below is the different part
            File someFile = new File(ruta);
            FileOutputStream fos = new FileOutputStream(someFile);
            fos.write(bytes);
            fos.flush();
            fos.close();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] asss = null;
            asss = bytes;
        
            baos.write(asss);

            FacesContext context = FacesContext.getCurrentInstance();
            Object response = context.getExternalContext().getResponse();
     
            if (response instanceof HttpServletResponse) {
                HttpServletResponse hsr = (HttpServletResponse) response;
                //hsr.setContentType("application/pdf");
                hsr.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                hsr.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
                hsr.setHeader("Content-disposition", "inline;filename=\"" + Archivo.substring(0, Archivo.length() - 4 ) );
                hsr.setContentLength(baos.size());
                try {
                    ServletOutputStream out = hsr.getOutputStream();
                    baos.writeTo(out);
                    out.flush();
                } catch (IOException ex) {
                   System.out.println("Error:  " + ex.getMessage());
                }
                context.responseComplete();
            }
            
           
          
          showMsg = true;
          tipoMsj = "success";
          mensaje = "Generando archivo WORD";
          showMsg = false;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    // Método que se llama cuando se presiona el botón
    public void obtenerSeleccionados(String tipoConstancia) {
        maeInversion.setcUsuarioAdd(SessionUtils.getUserName());
        Integer resp=0;
        registrosSeleccionados = new ArrayList<>();

        for (MaeInversion registro : maeInversionList) {
            if (registro.isSelected()) {
                registrosSeleccionados.add(registro);
            }
        }
        
        StringBuilder body = new StringBuilder();
        // Líneas de texto
        body.append("<p style=\"font-size: 16px;\">LISTA DE "+tipoConstancia.toUpperCase()+" PARA LA ENTREGA</p>");
        body.append("<p>Estimados,</p>");
        body.append("<p>Se informa que los siguientes códigos ya se encuentran en oficina:</p>");

        // Tabla
        body.append("<table border='1'>");
        body.append("<tr><th>NRO</th><th>CÓDIGO TCHN</th><th>CLIENTE</th><th>FONDO</th></tr>");
        // Filas de la tabla
        int nro = 1;
        // Construye el contenido del correo con los elementos de la lista
        for (MaeInversion registro : registrosSeleccionados) {
            body.append("<tr>");
            body.append("<td>").append(nro++).append("</td>");
            body.append("<td>").append(registro.getcInversion()).append("</td>");
            body.append("<td>").append(registro.getcPersonaId().getDNombres()).append("</td>");
            body.append("<td>").append(registro.getMaeFondo().getDFondo()).append("</td>");
            body.append("</tr>");
            registro.setEstaNotificado("SI");
            Date date=new Date();
            SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy HH:mm aa");
            registro.setFechaNotificacion(sdf.format(date));            
            registro.setcUsuarioMod(SessionUtils.getUserName());
            try {
                resp=getGeneradorDocumentoServ().notificarXEmail(registro);
            } catch (Exception ex) {
                ex.printStackTrace();
                Logger.getLogger(GeneradorDocumentoBean.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
        body.append("</table>");
        
        // Configuración de las propiedades del servidor de correo
        Properties propiedades = new Properties();
        propiedades.put("mail.smtp.host", "smtp.gmail.com");
        propiedades.put("mail.smtp.port", "587");
        propiedades.put("mail.smtp.starttls.enable", "true");
        propiedades.put("mail.transport.protocol", "smtp");//OK

        // Configuración de la sesión de correo
        Session sesion = Session.getInstance(propiedades);
        try {
            // Crear mensaje
            MimeMessage mensaje = new MimeMessage(sesion);
            try {
                this.emailsDestino = getGeneradorDocumentoServ().fetchAll();
                for(MaeEmail maeEmail:emailsDestino){
                    mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(maeEmail.getDemail()));
                }
            } catch (Exception ex) {
                Logger.getLogger(GeneradorDocumentoBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            mensaje.setSubject(tipoConstancia);
            mensaje.setContent(body.toString(), "text/html");

            // Enviar mensaje
            Transport transport = sesion.getTransport("smtp");
            try {
                MaeEmailList = getInversionServ().ListarEmail();
                mensaje.setFrom(new InternetAddress(MaeEmailList.get(0).getDusuario()));
                transport.connect(MaeEmailList.get(0).getDusuario(), MaeEmailList.get(0).getClave());
            } catch (Exception ex) {
                Logger.getLogger(GeneradorDocumentoBean.class.getName()).log(Level.SEVERE, null, ex);
            }        
            transport.sendMessage(mensaje, mensaje.getAllRecipients());
            transport.close();

            System.out.println("Correo enviado con éxito.");
            if(registrosSeleccionados.size()>1){
                FacesMessage doneMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Notificaciones enviadas.", " Se enviaron los correos con éxito.");
                FacesContext.getCurrentInstance().addMessage(null, doneMessage);               
            }else{
                FacesMessage doneMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Notificación enviada.", " Se envió el correo con éxito.");
                FacesContext.getCurrentInstance().addMessage(null, doneMessage);   
            }
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Error al enviar el correo.");
        }
    }
    
    public List<MaeInversion> getMaeInversionList() {
        return maeInversionList;
    }

    public void setMaeInversionList(List<MaeInversion> maeInversionList) {
        this.maeInversionList = maeInversionList;
    }

    public InversionServ getInversionServ() {
        return inversionServ;
    }

    public void setInversionServ(InversionServ inversionServ) {
        this.inversionServ = inversionServ;
    }

    public MaeInversion getMaeInversion() {
        return maeInversion;
    }

    public void setMaeInversion(MaeInversion maeInversion) {
        this.maeInversion = maeInversion;
    }

    public MaeFondo getMaeFondo() {
        return maeFondo;
    }

    public void setMaeFondo(MaeFondo maeFondo) {
        this.maeFondo = maeFondo;
    }

    public MaeFechaPor getMaeFechaPor() {
        return maeFechaPor;
    }

    public void setMaeFechaPor(MaeFechaPor maeFechaPor) {
        this.maeFechaPor = maeFechaPor;
    }

    
    public MaePersona getMaePersona() {
        return maePersona;
    }

    public void setMaePersona(MaePersona maePersona) {
        this.maePersona = maePersona;
    }

  
    public IFondoServ getFondoServ() {
        return fondoServ;
    }

    public void setFondoServ(IFondoServ fondoServ) {
        this.fondoServ = fondoServ;
    }
 
    public List<MaeFondo> getMaeFondoList() {
        return maeFondoList;
    }

    public void setMaeFondoList(List<MaeFondo> maeFondoList) {
        this.maeFondoList = maeFondoList;
    }
    
    public CobTablas getCobTablasEstInv() {
        return cobTablasEstInv;
    }

    public void setCobTablasEstInv(CobTablas cobTablasEstInv) {
        this.cobTablasEstInv = cobTablasEstInv;
    }

    public List<CobTablas> getCobTablasEstInvList() {
        return cobTablasEstInvList;
    }

    public void setCobTablasEstInvList(List<CobTablas> cobTablasEstInvList) {
        this.cobTablasEstInvList = cobTablasEstInvList;
    }

    public ITablasServ getTablasServ() {
        return tablasServ;
    }

    public void setTablasServ(ITablasServ tablasServ) {
        this.tablasServ = tablasServ;
    }
 
    
  
    public List<MaeFechaPor> getMaeFechaPorList() {
        return maeFechaPorList;
    }

    public void setMaeFechaPorList(List<MaeFechaPor> maeFechaPorList) {
        this.maeFechaPorList = maeFechaPorList;
    }
    
    public List<MaeInversion> getMaeInversionList2() {
        return maeInversionList2;
    }

    public void setMaeInversionList2(List<MaeInversion> maeInversionList2) {
        this.maeInversionList2 = maeInversionList2;
    }
    
    public List<MaeInversion> getMaeInversionList3() {
        return maeInversionList3;
    }

    public void setMaeInversionList3(List<MaeInversion> maeInversionList3) {
        this.maeInversionList3 = maeInversionList3;
    }    
    
     public List<CobTablas> getCobTablasEstInvList2() {
        return cobTablasEstInvList2;
    }

    public void setCobTablasEstInvList2(List<CobTablas> cobTablasEstInvList2) {
        this.cobTablasEstInvList2 = cobTablasEstInvList2;
    }
    
    
    public List<CobTablas> getListaTipoConsLiqui() {
        return listaTipoConsLiqui;
    }

    public void setListaConsTipoLiqui(List<CobTablas> plistaConsLiqui) {
        this.listaTipoConsLiqui = plistaConsLiqui;
    }
    
    public List<CobTablas> getListaFormaConsLiqui() {
        return listaFormaConsLiqui;
    }

    public void setListaConsFormaLiqui(List<CobTablas> plistaConsLiqui) {
        this.listaFormaConsLiqui = plistaConsLiqui;
    }    
    
    public List<MaeInversion> getMaeInversionList1() {
        return maeInversionList1;
    }

    public void setMaeInversionList1(List<MaeInversion> maeInversionList1) {
        this.maeInversionList1 = maeInversionList1;
    }
    
    public List<CobTablas> getCobTablasEstInvList3() {
        return cobTablasEstInvList3;
    }

    public void setCobTablasEstInvList3(List<CobTablas> cobTablasEstInvList3) {
        this.cobTablasEstInvList3 = cobTablasEstInvList3;
    }
   
     public boolean isShowMsg() {
        return showMsg;
    }

    public void setShowMsg(boolean showMsg) {
        this.showMsg = showMsg;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getTipoMsj() {
        return tipoMsj;
    }

    public void setTipoMsj(String tipoMsj) {
        this.tipoMsj = tipoMsj;
    }
    
    public CobTablas getCobTablasEstInvEC() {
        return cobTablasEstInvEC;
    }

    public void setCobTablasEstInvEC(CobTablas cobTablasEstInvEC) {
        this.cobTablasEstInvEC = cobTablasEstInvEC;
    }

    public CobTablas getCobTablasEstInvInd() {
        return cobTablasEstInvInd;
    }

    public void setCobTablasEstInvInd(CobTablas cobTablasEstInvInd) {
        this.cobTablasEstInvInd = cobTablasEstInvInd;
    }

    public List<CobTablas> getCobTablasEstInvListEC() {
        return cobTablasEstInvListEC;
    }

    public void setCobTablasEstInvListEC(List<CobTablas> cobTablasEstInvListEC) {
        this.cobTablasEstInvListEC = cobTablasEstInvListEC;
    }

    public List<CobTablas> getCobTablasEstInvListInd() {
        return cobTablasEstInvListInd;
    }

    public void setCobTablasEstInvListInd(List<CobTablas> cobTablasEstInvListInd) {
        this.cobTablasEstInvListInd = cobTablasEstInvListInd;
    }
    
 

    public List<CobTablas> getCobTablasEstOrigenList() {
        return cobTablasEstOrigenList;
    }

    public void setCobTablasEstOrigenList(List<CobTablas> cobTablasEstOrigenList) {
        this.cobTablasEstOrigenList = cobTablasEstOrigenList;
    }



    
    public List<CobTablas> getCobTablasDocInvList() {
        return cobTablasDocInvList;
    }

    public void setCobTablasDocInvList(List<CobTablas> cobTablasDocInvList) {
        this.cobTablasDocInvList = cobTablasDocInvList;
    }

    
    public List<MaeInversion> getMaeInversionListDoc() {
        return maeInversionListDoc;
    }

    public void setMaeInversionListDoc(List<MaeInversion> maeInversionListDoc) {
        this.maeInversionListDoc = maeInversionListDoc;
    } 
     
 public List<CobTablas> getCobTablasMotInvList() {
        return cobTablasMotInvList;
    }

    public void setCobTablasMotInvList(List<CobTablas> cobTablasMotInvList) {
        this.cobTablasMotInvList = cobTablasMotInvList;
    }

    public List<CobTablas> getCobtablasListSoli() {
        return cobtablasListSoli;
    }

    public void setCobtablasListSoli(List<CobTablas> cobtablasListSoli) {
        this.cobtablasListSoli = cobtablasListSoli;
    }

    
    public List<CobTablas> getCobTablasTipoMovList() {
        return cobTablasTipoMovList;
    }

    public void setCobTablasTipoMovList(List<CobTablas> cobTablasTipoMovList) {
        this.cobTablasTipoMovList = cobTablasTipoMovList;
    }    
 
    public boolean isXViewDocu1() {
        return XViewDocu1;
    }

    public void setXViewDocu1(boolean XViewDocu1) {
        this.XViewDocu1 = XViewDocu1;
    }

    public boolean isXViewDocu2() {
        return XViewDocu2;
    }

    public void setXViewDocu2(boolean XViewDocu2) {
        this.XViewDocu2 = XViewDocu2;
    }

    public boolean isXViewDocu3() {
        return XViewDocu3;
    }

    public void setXViewDocu3(boolean XViewDocu3) {
        this.XViewDocu3 = XViewDocu3;
    }
   
    public boolean isXViewDocu4() {
        return XViewDocu4;
    }

    public void setXViewDocu4(boolean XViewDocu4) {
        this.XViewDocu4 = XViewDocu4;
    }

    public boolean isXViewDocu5() {
        return XViewDocu5;
    }

    public void setXViewDocu5(boolean XViewDocu5) {
        this.XViewDocu5 = XViewDocu5;
    }

    
    public GeneradorDocumentoServ getGeneradorDocumentoServ() {
        return generadorDocumentoServ;
    }

    public void setGeneradorDocumentoServ(GeneradorDocumentoServ generadorDocumentoServ) {
        this.generadorDocumentoServ = generadorDocumentoServ;
    }    

    public List<MaeInversion> getMaeInversionList4() {
        return maeInversionList4;
    }

    public void setMaeInversionList4(List<MaeInversion> maeInversionList4) {
        this.maeInversionList4 = maeInversionList4;
    }

    public ArrayList<MaeEmail> getEmailsDestino() {
        return emailsDestino;
    }

    public void setEmailsDestino(ArrayList<MaeEmail> emailsDestino) {
        this.emailsDestino = emailsDestino;
    }

}
