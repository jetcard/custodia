/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.bean;

import com.itextpdf.text.DocumentException;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
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
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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
import org.apache.commons.collections4.list.AbstractLinkedList;
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
import pop.comun.dominio.CobTablas;
import pop.comun.dominio.MaeDocOtros;

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
public class ClienteCustodiaBean implements Serializable {

    /**
     * @return the emision
     */
    public Date getEmision() {
        return emision;
    }

    /**
     * @param emision the emision to set
     */
    public void setEmision(Date emision) {
        this.emision = emision;
    }


    /**
     * @return the cobTablasEstaCodigo
     */
    public List<CobTablas> getCobTablasEstaCodigo() {
        return cobTablasEstaCodigo;
    }

    /**
     * @param cobTablasEstaCodigo the cobTablasEstaCodigo to set
     */
    public void setCobTablasEstaCodigo(List<CobTablas> cobTablasEstaCodigo) {
        this.cobTablasEstaCodigo = cobTablasEstaCodigo;
    }

    private static final long serialVersionUID = 1L;


    

    private MaeFondo maeFondo = new MaeFondo();
    private MaeFechaPor maeFechaPor = new MaeFechaPor();

    
   
    // Persona (busq por documento de la persona)
    private MaePersona maePersona = new MaePersona();
    private List<MaeInversion> maeInversionList2;
    private List<MaeInversion> maeInversionListDetalle;
    private List<CobTablas> cobtablasListSoli;
    private List<CobTablas> cobtablasListDocDeta;

   
   
    private List<MaeInversion> maeInversionListDoc;

  
    private List<MaeEmail> MaeEmailList;
    private MaeInversion maeInversion = new MaeInversion();
    // estado inversion
    private CobTablas cobTablasEstInv = new CobTablas();
    private CobTablas cobTablasDocuInv = new CobTablas();
    private CobTablas cobTablasEstInvEC = new CobTablas();
    private CobTablas cobTablasEstInvInd = new CobTablas();
    private CobTablas cobTablasEstInvMov = new CobTablas();
    
    private boolean mostrarAddButAddDocCust=false;
    private boolean mostrarformAddDocCust=false;
    private String codigoTCHN;
    private String codigoDocumentoCustodia;
    private String usuario;
    private String fondoId;
    private String fondoDescripcion;
    private Date emision;
    
//asesor busqueda

    public CobTablas getCobTablasEstInvMov() {
        return cobTablasEstInvMov;
    }

    public void setCobTablasEstInvMov(CobTablas cobTablasEstInvMov) {
        this.cobTablasEstInvMov = cobTablasEstInvMov;
    }

    private List<MaeInversion> maeInversionList;
    
    private List<MaeInversion> maeInversionList1;

   
   
    private List<MaeFondo> maeFondoList;
    
    
    // lista de fondos
    private List<MaeFechaPor> maeFechaPorList;

   
   
    // lista de estado de inversion
    private List<CobTablas> cobTablasEstInvList;

    private List<CobTablas> cobTablasEstInvList2;
    private List<CobTablas> cobTablasDocInvList;
    private List<CobTablas> cobTablasTipoMovList;

    
    private List<CobTablas> cobTablasMotInvList;

   
    
    private List<CobTablas> cobTablasCorrelaList;
    private List<CobTablas> cobTablasEstOrigenList;
    
    private List<CobTablas> cobTablasEstInvList3;

    
    private List<CobTablas> cobTablasEstInvListEC;
    
    private List<CobTablas> cobTablasEstaCodigo;

    
    private List<CobTablas> cobTablasEstInvListInd;

    
    
    // sericios de ubigeo
    private IUbigeoServ ubigeoServ = new UbigeoServ();
    // servicios de inversion
    private InversionServ inversionServ = new InversionServ();
    // servicios de fondos
    private  IFondoServ fondoServ = new FondoServ();
    
    private IFechaPorServ fechaPorServ = new FechaPorServ();
    
    private boolean xFlag= false;
    private boolean XViewDocu= false;
    private boolean XViewDocu4= false;

    private String XActiva="active";
    private String XActiva2="";
    private String XTamano="height: 900px;";
    ArrayList<MaeInversion> lstInversion = new ArrayList<>();

    public ArrayList<MaeInversion> getLstInversion() {
        return lstInversion;
    }

    public void setLstInversion(ArrayList<MaeInversion> lstInversion) {
        this.lstInversion = lstInversion;
    }
    
    
   
    public boolean isXViewDocu() {
        return XViewDocu;
    }

    public void setXViewDocu(boolean XViewDocu) {
        this.XViewDocu = XViewDocu;
    }

    private boolean showMsg = true;
    private String mensaje = "";
    private String tipoMsj = "";
    private boolean showMsg1 = true;
    private String mensaje1 = "";
    private String tipoMsj1 = "";
    private boolean showMsg2 = true;
    private String mensaje2 = "";
    private String tipoMsj2 = "";
    private boolean showMsg3 = true;
    private String mensaje3 = "";
    private String tipoMsj3 = "";
    private boolean showMsg4 = true;
    private String mensaje4 = "";
    private String tipoMsj4 = "";
    private boolean showMsg5 = true;
    private String mensaje5 = "";
    private String tipoMsj5 = "";
    private boolean showMsg6 = true;
    private String mensaje6 = "";
    private String tipoMsj6 = "";
    private boolean showMsg7 = true;
    private String mensaje7 = "";
    private String tipoMsj7 = "";

    
  
    public IUbigeoServ getUbigeoServ() {
        return ubigeoServ;
    }

    public void setUbigeoServ(IUbigeoServ ubigeoServ) {
        this.ubigeoServ = ubigeoServ;
    }

    public boolean isXViewDocu4() {
        return XViewDocu4;
    }

    public void setXViewDocu4(boolean XViewDocu4) {
        this.XViewDocu4 = XViewDocu4;
    }
    
    public IFechaPorServ getFechaPorServ() {
        return fechaPorServ;
    }

    public void setFechaPorServ(IFechaPorServ fechaPorServ) {
        this.fechaPorServ = fechaPorServ;
    }
    // servicios de tablas
    private ITablasServ tablasServ = new TablasServ();
    // servicio de cronograma



   
    public ClienteCustodiaBean() {
        try {
            maeInversion.setMaeFondo(maeFondo);
            maeInversion.setcPersonaId(maePersona);
            
           
                
          
        } catch (Exception ex) {
            Logger.getLogger(ClienteCustodiaBean.class.getName()).log(Level.SEVERE, null, ex);
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
    
     public void listarFechapor() {
        try {
       
             if (cobTablasEstInvList == null) {
                cobTablasEstInv.setCtablaId("0001");
                cobTablasEstInvList = getTablasServ().listarTablas(cobTablasEstInv);
            }
            //System.out.println(" cantidad de Fondos -> " + maeFondoList.size());
        } catch (Exception e) {

        }
    }

     public void listarEstadoCustodia() {
        try {
       
             if (cobTablasEstInvListEC == null) {
                cobTablasEstInvEC.setCtablaId("0002");
                cobTablasEstInvListEC = getTablasServ().listarTablas(cobTablasEstInvEC);
            }
             
             if (cobTablasEstaCodigo == null) {
                cobTablasEstInvEC.setCtablaId("0013");
                cobTablasEstaCodigo = getTablasServ().listarTablas(cobTablasEstInvEC);
            }             
            //System.out.println(" cantidad de Fondos -> " + maeFondoList.size());
        } catch (Exception e) {

        }
    } 
    
     public void listarDocNew() {
        try {
       
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
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
     

     
    
     public void cambiarInfo(String tchn) {
        try {
            
            System.out.println("tchn"+tchn);
         //   if (cobTablasDocInvList == null) {
                 cobTablasDocInvList = new ArrayList<CobTablas>();
              cobTablasDocuInv.setCtablaId("0004");
              //cobTablasDocInvList = getTablasServ().listarTablasDocusCustodia(cobTablasDocuInv); 
              
              for(CobTablas ct: getTablasServ().listarTablasDocusCustodia(cobTablasDocuInv,tchn)){
                  
                  int id = Integer.parseInt(ct.getCtablaDetId());
                  
                  if(id > 15){
                      cobTablasDocInvList.add(ct);
                  }
              }
      
           // }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
     

      
     
     public void listarDocumento() {
        try {
       
             if (cobTablasDocInvList == null) {
              cobTablasDocuInv.setCtablaId("0004");
              cobTablasDocInvList = getTablasServ().listarTablasDocusCustodia(cobTablasDocuInv,null); 
      
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    } 
     
     
     public void listarDocumentoDetalle(String lsCodpadre) {
        try {
       
             if (cobtablasListDocDeta == null) {
              cobTablasDocuInv.setCodpadre(lsCodpadre);
              System.out.println(lsCodpadre);
              cobTablasDocuInv.setCtablaId("0004");
              
              cobtablasListDocDeta = getTablasServ().listarDocDetTablas(cobTablasDocuInv); 
              System.out.println("datos"+cobtablasListDocDeta.size());
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    } 
     
     public void listarTipoMov() {
        try {
       
             if (cobTablasTipoMovList == null) {
              cobTablasDocuInv.setCtablaId("0007");
              cobTablasTipoMovList = getTablasServ().listarTablas(cobTablasDocuInv);    
      
            }
            //System.out.println(" cantidad de Fondos -> " + maeFondoList.size());
        } catch (Exception e) {

        }
    }



     
    public void listarIndicadorCustodia() {
        try {
       
             if (cobTablasEstInvListInd == null) {
                cobTablasEstInvInd.setCtablaId("0003");
                cobTablasEstInvListInd = getTablasServ().listarTablas(cobTablasEstInvInd);
            }
            //System.out.println(" cantidad de Fondos -> " + maeFondoList.size());
        } catch (Exception e) {

        }
    } 

    public void listarEstadoInversion() {
        try {
            if (cobTablasEstInvList == null) {
                cobTablasEstInv.setCtablaId("0608");
                cobTablasEstInvList = getTablasServ().listarTablas(cobTablasEstInv);
            }
            //System.out.println(" cantidad de Provincias -> " + ubigeoProv.size());
        } catch (Exception e) {

        }
    }

    
    
    public void GenerarExcel(List<MaeInversion> maeInversionList2) {
    
        
               
        try {
             Calendar cal = Calendar.getInstance(); 
                
                cal.add(Calendar.DAY_OF_MONTH,0); 
                Date FechaAnterior =  cal.getTime();
                
                String ls_nombre="";
             
                ls_nombre= "Codigointerno " + maeInversionList2.get(0).getCInversion();
                String ruta_conf="C:\\TEMP\\";
                File archivo = new File(ruta_conf+"plancustodia.xlsx");

                // Creamos el libro de trabajo de Excel formato OOXML
                Workbook workbook = new XSSFWorkbook();
                DataFormat format = workbook.createDataFormat();
                // La hoja donde pondremos los datos
                Sheet pagina = workbook.createSheet("custodia");

                 System.out.println("sistema exportar"+maeInversionList2.size());
     
                // Creamos el estilo paga las celdas del encabezado
                CellStyle style = workbook.createCellStyle();
                // Indicamos que tendra un fondo azul aqua
                // con patron solido del color indicado

                int FilRow=0;    
                
                
                style.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());              
                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                style.setAlignment(HorizontalAlignment.CENTER);
                Row fila = pagina.createRow(0);
                
              
                Cell celda = null;
                //celda.setCellStyle(style);
               // celda.setCellValue("Estado de Cuenta de Saldo Deudor al " +  new SimpleDateFormat("dd/MM/yyyy").format(FechaAnterior)) ;
                

                String[] titulos = { "Seleccion","Documento", "F.Registro","Estado", "Comentarios","Subsanado","FSubsanado"};

                style = workbook.createCellStyle();
                style.setFillForegroundColor(IndexedColors.WHITE.getIndex());              
                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

                celda = fila.createCell(0);
                celda.setCellStyle(style);
                celda.setCellValue("Codigo" + maeInversionList2.get(0).getCInversion() );

                Font font = workbook.createFont();
                font.setBold(true);
                font.setFontHeightInPoints((short)14);
                style.setFont(font);

                 fila = pagina.createRow(1);
                 FilRow=FilRow+1;
              
                 // Creamos el encabezado
                for (int i = 0; i < titulos.length; i++) {
                    // Creamos una celda en esa fila, en la posicion 
                    // indicada por el contador del ciclo
                      celda = fila.createCell(i);
                    style.setBorderBottom(BorderStyle.DOUBLE);
                    style.setBorderLeft(BorderStyle.DOUBLE);
                    style.setBorderRight(BorderStyle.DOUBLE);
                    style.setBorderTop(BorderStyle.DOUBLE);                    
                    style.setAlignment(HorizontalAlignment.CENTER);
                    celda.setCellStyle(style);
                    celda.setCellValue(titulos[i]);
                }
                

                // Y colocamos los datos en esa fila
                for (int i = 0; i < maeInversionList2.size(); i++) {
                    fila = pagina.createRow(i+2); 
                    FilRow=FilRow+1;    
                    for (int e = 0; e < 7; e++) {
                    // Creamos una celda en esa fila, en la
                    // posicion indicada por el contador del ciclo
                    style = workbook.createCellStyle();
                    style.setFillForegroundColor(IndexedColors.WHITE.getIndex());              
                    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    font = workbook.createFont();
                    font.setBold(false);
                    celda = fila.createCell(e);
                     
                        if (e==0){ //SELECCION
                            celda.setCellStyle(style);
                            celda.setCellValue(maeInversionList2.get(i).getsSelec());
                        }  
                        if (e==1){//DESCRIPCION DEL DOCUMENTO
                            celda.setCellStyle(style);
                            celda.setCellValue(maeInversionList2.get(i).getDescripcion());
                         }
                        if (e==2){//FECHA DE REGISTRO
                            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd MMMM yyyy");
                            celda.setCellStyle(style);
                            celda.setCellValue( formatoFecha.format(maeInversionList2.get(i).getFRegistro())) ;
                         }
                        
                        if (e==3){//ESTADO
                            celda.setCellStyle(style);
                           if  (maeInversionList2.get(i).getSEstado()!= null){
                                if (maeInversionList2.get(i).getSEstado().equals("0001")) {
                                    celda.setCellValue("CONFORME");
                                }else{
                                    celda.setCellValue("OBSERVADO");
                                }
                           }
                        }
                        if (e==4){//COMENTARIO
                            celda.setCellStyle(style);
                           if  (maeInversionList2.get(i).getSComentario()!= null){
                            celda.setCellValue(maeInversionList2.get(i).getSComentario());
                           } 
                        }
                        if (e==5){//SUBSANADO
                            celda.setCellStyle(style);
                           if  (maeInversionList2.get(i).getSSubsanado()!= null){
                            celda.setCellValue(maeInversionList2.get(i).getSSubsanado());
                            }
                        }
                        if (e==6){//fecha de subsanacion
                            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd MMMM yyyy");
                            celda.setCellStyle(style);
                            if  (maeInversionList2.get(i).getFSubsanado()!= null){
                            celda.setCellValue( formatoFecha.format(maeInversionList2.get(i).getFSubsanado())) ;
                            }
                        }
                    }


                }
                
                for(int columnPosition = 0; columnPosition< 10; columnPosition++) {
                     pagina.autoSizeColumn((short) (columnPosition));
                }
                 
                   // Ahora guardaremos el archivo
                try {
                    // Creamos el flujo de salida de datos,
                    // apuntando al archivo donde queremos 
                    // almacenar el libro de Excel
                    FileOutputStream salida = new FileOutputStream(archivo);

                    // Almacenamos el libro de 
                    // Excel via ese 
                    // flujo de datos
                    workbook.write(salida);

                    // Cerramos el libro para concluir operaciones
                    workbook.close();

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                     
                    String filePath = ruta_conf+"plancustodia.xlsx";

                    byte[] asss = Files.readAllBytes(new File(filePath).toPath());
               
                    
                    baos.write(asss);

                    
                    FacesContext context = FacesContext.getCurrentInstance();
                    Object response = context.getExternalContext().getResponse();

                    if (response instanceof HttpServletResponse) {
                        HttpServletResponse hsr = (HttpServletResponse) response;
                        hsr.setContentType("application/vnd.ms-excel");
                        hsr.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");

                        hsr.setHeader("Content-disposition", "attachment;filename=Custodia_" + ls_nombre + ".xlsx");
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
                    
                    
                    
                     System.out.println( "Archivo creado existosamente en {0}");   
                     
                } catch (FileNotFoundException ex) {
                     System.out.println( "Archivo no localizable en sistema de archivos");

                } catch (IOException ex) {
                     System.out.println( "Error de entrada/salida");
                }

            
                
                
         } catch (Exception ex) {
            //Logger.getLogger(EstadoCuentaBean.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }
    
    public void buscarDeudores() {
        try {
            System.out.println("["+SessionUtils.getUserName()+"] "+" ClienteDeudorBean - buscarDeudores ");
            maeInversion.setcUsuarioAdd(SessionUtils.getUserName());
            maeInversionList = getInversionServ().listarDeudor(maeInversion);
            maeInversionList2.clear();
            maeInversionList1.clear();
            this.setXViewDocu4(false);
            System.out.println("ssss0"+maeInversionList.size());
            if (maeInversionList.size()==0)
            {
                 this.setXViewDocu4(true);
            }    
            
    
            
           showMsg = false;
          mensaje="";  
        } catch (Exception e) {
            System.err.println("pop.webcustodia.bean.InversionBean.buscarDeudores()");
        }
    }
    
    public void buscarDeudoresMov() {
        try {
           showMsg = true;
           //  setXViewDocu(true);    
            System.out.println("["+SessionUtils.getUserName()+"] "+" ClienteDeudorBean - buscarDeudores ");
            maeInversion.setcUsuarioAdd(SessionUtils.getUserName());
            if ( maeInversion.getCInversion().length()==0 && maeInversion.getcPersonaId().getANroDocumento().length()==0 && maeInversion.getMaeFondo().getCFondoId().length()==0  ){
                 maeInversionList.clear(); 
                System.out.println(maeInversion.getCInversion());
                tipoMsj = "error";
                 mensaje ="Error ingrese por lo menos un filtro"  ;
                 
            }else{
              cobTablasDocuInv.setCtablaId("0004");
              cobTablasDocInvList = getTablasServ().listarTablas(cobTablasDocuInv);    
               System.out.println(maeInversion.getCInversion());
               maeInversionList = getInversionServ().listarDeudorMov(maeInversion);
      
            }   
            this.setXViewDocu4(false);
            if (maeInversionList.size()==0)
            {
                 this.setXViewDocu4(true);
            }    
            
    
            XViewDocu=false;
           // maeInversionListDoc.clear();
            maeInversionList2.clear();
            maeInversionList1.clear();
        } catch (Exception e) {
            System.err.println("pop.webcustodia.bean.InversionBean.buscarDeudores()");
        }
    }
    
    public void buscarDeudoresf2() {
        try {
           showMsg = true;
           showMsg1 = false;
           showMsg2 = false;
           showMsg3 = false;
           showMsg4 = false;
           showMsg5 = false;
           showMsg6 = false;
           
            System.out.println("["+SessionUtils.getUserName()+"] "+" ClienteDeudorBean - buscarDeudores ");
            maeInversion.setcUsuarioAdd(SessionUtils.getUserName());
             
            if ( maeInversion.getCInversion().length()==0 && maeInversion.getcPersonaId().getANroDocumento().length()==0 && maeInversion.getMaeFondo().getCFondoId().length()==0  ){
               maeInversionList.clear();
                tipoMsj = "error";
                 mensaje ="Error ingrese por lo menos un filtro"  ;
                 
            }else{
                      
               maeInversionList = getInversionServ().listarDeudorf2(maeInversion);
      
            }    
            this.setXViewDocu4(false);
            if (maeInversionList.size()==0)
            {
                 this.setXViewDocu4(true);
            }    
            
    
            maeInversionList2.clear();
            maeInversionList1.clear();
        } catch (Exception e) {
            System.err.println("pop.webcustodia.bean.InversionBean.buscarDeudores()");
        }
    }
    
    
    public void buscarDeudoresConsulta() {
        try {
            System.out.println("["+SessionUtils.getUserName()+"] "+" ClienteDeudorBean - buscarDeudores ");
            maeInversion.setcUsuarioAdd(SessionUtils.getUserName());
            maeInversionList = getInversionServ().listarDeudorConsulta(maeInversion);
            this.setXViewDocu4(false);
            if (maeInversionList.size()==0)
            {
                 this.setXViewDocu4(true);
            }    
            
            maeInversionList2.clear();
            maeInversionList1.clear();
           showMsg = false;
          } catch (Exception e) {
            System.err.println("pop.webcustodia.bean.InversionBean.buscarDeudores()");
        }
    }
    
    public void buscarConsultaxDoc() {
        try {
            System.out.println("["+SessionUtils.getUserName()+"] "+" ClienteDeudorBean - buscarDeudores ");
            maeInversion.setcUsuarioAdd(SessionUtils.getUserName());
            maeInversionList = getInversionServ().listarConsultaxDoc(maeInversion);
               this.setXViewDocu4(false);
            if (maeInversionList.size()==0)
            {
                 this.setXViewDocu4(true);
            }    
            
            maeInversionList2.clear();
            maeInversionList1.clear();
           showMsg = false;
          } catch (Exception e) {
            System.err.println("pop.webcustodia.bean.InversionBean.buscarDeudores()");
        }
    }
    
    public void buscarConsultaxMov() {
        try {
            System.out.println("["+SessionUtils.getUserName()+"] "+" entro - buscarDeudores ");
            maeInversion.setcUsuarioAdd(SessionUtils.getUserName());
            maeInversionList = getInversionServ().listarConsultaxMov(maeInversion);
               this.setXViewDocu4(false);
           if (maeInversionList.size()==0){
             this.setXViewDocu4(true);
           } 
            maeInversionList2.clear();
            maeInversionList1.clear();
           showMsg = false;
          } catch (Exception e) {
            System.err.println("pop.webcustodia.bean.InversionBean.buscarDeudores()");
        }
    }
    
    
    public void validarRegistro() {
        try {
            int resp=0;
             showMsg = true;
         
            Date f1 =  null;
            Date f2 =  null;
            
              for (MaeInversion newInv : maeInversionList2) {
             
                newInv.setcUsuarioAdd(SessionUtils.getUserName());
                newInv.setcUsuarioMod(SessionUtils.getUserName());
                if (newInv.getXFlagSel().equals(true)){
                    newInv.setsSelec("S");
                }else{
                   newInv.setsSelec("N");
                }
                     f1 = newInv.getFRegistro();
                    f2 = newInv.getFSubsanado();
                    if (f1!=null && f2!=null){
                       if ( f1.after(f2)){
                          mensaje ="Revisar registro " + newInv.getDescripcion() ;
                          tipoMsj = "error";
                          
                      }
                    }else{
          
                        if ( newInv.getSSubsanado().contains("S")&& f2==null ){
                              resp=1;
                                mensaje ="Revisar registro " + newInv.getDescripcion() ;
                               tipoMsj = "error";
                         }
                    }
                    
              }
            } catch (Exception e) {
            System.err.println("pop.webcustodia.bean.InversionBean.buscarDeudores()");
        }
    }
    
    public void validarRegistrof2() {
        try {
            int resp=0;
             showMsg = true;
         
            Date f1 =  null;
            Date f2 =  null;
            for (MaeInversion newInv2 : maeInversionList2) {
              if (newInv2.getSCodOrigen().trim().equals(newInv2.getSDestino())){
                     mensaje ="Revisar registro no puede ser igual el origen y destino " + newInv2.getDescripcion() ;
                          tipoMsj = "error";
              }
            }     
            
            for (MaeInversion newInv : maeInversionList2) {
               
                newInv.setcUsuarioAdd(SessionUtils.getUserName());
                newInv.setcUsuarioMod(SessionUtils.getUserName());
                if (newInv.getXFlagSel().equals(true)){
                    newInv.setsSelec("S");
                }else{
                   newInv.setsSelec("N");
                }
                    f1 = newInv.getFRegistro();
                    f2 = newInv.getFSubsanado();
                    if (f1!=null && f2!=null){
                       if ( f1.after(f2)){
                          mensaje ="Revisar registro " + newInv.getDescripcion() ;
                          tipoMsj = "error";
                          
                      }
                    }else{
          
                        if ( newInv.getSSubsanado().contains("S")&& f2==null ){
                              resp=1;
                                mensaje ="Revisar registro " + newInv.getDescripcion() ;
                               tipoMsj = "error";
                         }
                    }
                    
              
              }
            } catch (Exception e) {
            System.err.println("pop.webcustodia.bean.InversionBean.buscarDeudores()");
        }
    }
    
    
    public void cargar() {
        try {
              xFlag  =true;
            
              System.out.println("cargando la info");
          } catch (Exception e) {
            System.err.println("pop.webcustodia.bean.InversionBean.InsertarCustodia()");
        }
    }    
   
    
      public void InsertarCustodiaCarga(MaeInversion oInversion) {
        try {
             showMsg = false;
             int resp=0;
            
             for (MaeInversion newInv1 : maeInversionList2) {
             
                newInv1.setcUsuarioAdd(SessionUtils.getUserName());
                newInv1.setcUsuarioMod(SessionUtils.getUserName());
                if (newInv1.getXFlagSel().equals(true)){
                    newInv1.setsSelec("S");
                }else{
                   newInv1.setsSelec("N");
                }
                resp = getInversionServ().insertarCustodia(newInv1);
            }
              maeInversionList2.clear();
              maeInversionList2 = getInversionServ().listarCustodia(oInversion);
             
                        
          } catch (Exception e) {
            System.err.println("pop.webcustodia.bean.InversionBean.InsertarCustodia()");
        }
    }
     
   
    
     public void InsertarCustodia(MaeInversion oInversion) {
        try {
             showMsg = true;
             int resp=0;
              
            Date f1 =  null;
            Date f2 =  null;
            
              for (MaeInversion newInv : maeInversionList2) {
             
                newInv.setcUsuarioAdd(SessionUtils.getUserName());
                newInv.setcUsuarioMod(SessionUtils.getUserName());
                if (newInv.getXFlagSel().equals(true)){
                    newInv.setsSelec("S");
                }else{
                   newInv.setsSelec("N");
                }
                
                     f1 = newInv.getFRegistro();
                    f2 = newInv.getFSubsanado();
                   
                 
                    if (f1!=null && f2!=null){
                      
                        if ( f1.after(f2)){
                           resp=1;
                          mensaje ="Revisar registro " + newInv.getDescripcion() ;
                          tipoMsj = "error";
                          
                      }
                    }else{
                        String sub=newInv.getSSubsanado();
                       
                        if (sub!=null && f2==null){
                          if (sub.contains("S")){      
                            resp=1;
                            mensaje ="Revisar registro " + newInv.getDescripcion() ;
                            tipoMsj = "error";

                         }
                        }
                       
                    }
            
                 
              }
            if(resp==0){  
              for (MaeInversion newInv1 : maeInversionList2) {
             
                  System.err.println("MaeInversion newInv1 : "+newInv1.getFGiro());
                  
                newInv1.setcUsuarioAdd(SessionUtils.getUserName());
                newInv1.setcUsuarioMod(SessionUtils.getUserName());
                if (newInv1.getXFlagSel().equals(true)){
                    newInv1.setsSelec("S");
                }else{
                   newInv1.setsSelec("N");
                }
                      resp = getInversionServ().insertarCustodia(newInv1);
                      
                
              }
           
            
             
                   
          //  maeInversionList2 = getInversionServ().listarCustodia(oInversion);
            tipoMsj = "success";
            mensaje = "Se grabo Correctamente";
            Thread.sleep(1*1000);
            
            }
                        
          } catch (Exception e) {
            System.err.println("pop.webcustodia.bean.InversionBean.InsertarCustodia()");
        }
    }
     
    public void buscarDetalleDocumentos(MaeInversion oInversion) {
        try {
            showMsg = false;
             showMsg1 = false;
             showMsg2 = false;
             showMsg3 = false;
             showMsg4 = false;
             showMsg5 = false;
             showMsg6 = false;
             showMsg7 = false;
          // maeInversionListDetalle.clear();
           int resp=0;
           setXViewDocu(true);
              cobTablasDocuInv.setCodpadre(oInversion.getsDocumentoId());
              System.out.println("lsCodpadre"+oInversion.getsDocumentoId());
              cobTablasDocuInv.setCtablaId("0004");
              
              cobtablasListDocDeta = getTablasServ().listarDocDetTablas(cobTablasDocuInv); 
              
              
           
           System.out.println(" documentoid "); 
           System.out.println(oInversion.getsDocumentoId()+" documentoid ");
           maeInversion.setDescripcion(oInversion.getDescripcion());
           maeInversion.setsDocumentoId(oInversion.getsDocumentoId());
            maeInversionListDetalle = getInversionServ().listarDetalleCustodia(oInversion);
            this.lstInversion = new ArrayList<>();
            this.lstInversion.clear();
            for (MaeInversion newInv2 : maeInversionListDetalle) {
            
                lstInversion.add(newInv2);
            }
            System.out.println(maeInversionListDetalle.size()+" MaeInversion ");
        } catch (Exception e) {
            System.err.println("pop.webcustodia.bean.InversionBean.buscarDeudores()");
        }
    }
    
     
     public void InsertarCustodiaf2(MaeInversion oInversion) {
        try {
             showMsg = true;
             showMsg1 = false;
             showMsg2 = false;
             showMsg3 = false;
             showMsg4 = false;
             showMsg5 = false;
             showMsg6 = false;
             
             int resp=0;
              
            Date f1 =  null;
            Date f2 =  null;
           for (MaeInversion newInv2 : maeInversionList2) {
               
               if ( newInv2.getSEstado() != null  ){ 
                if ( newInv2.getSEstado().equals("0001")  ){
                       /*  if (newInv2.getSDestino()== null){
                            showMsg = true;
                            mensaje ="Destino no puede quedar vacio "  ;
                            tipoMsj = "error";
                            resp=2;     
                        }
                         
                        if (newInv2.getSCodDigital()== null){
                            showMsg = true;
                             mensaje ="Llenar digital ....."  ;
                            tipoMsj = "error";

                        }
                        if (newInv2.getSCustodia() ==  null ){
                            showMsg = true;
                            mensaje ="Destino de documento no esta seleccionado ....."  ;
                            tipoMsj = "error";
                        }

                        if (newInv2.getSCodOrigen().trim().equals(newInv2.getSDestino())){
                            showMsg = true;
                            mensaje ="Revisar registro no puede ser igual el origen y destino "  ;
                            tipoMsj = "error";
                               resp=2;     
                        }
                        */
                        if (newInv2.getsDocumentoId().trim().equals("0012")){
                             if (newInv2.getSNotaria().length()==0){
                                 showMsg1 = true;
                                 mensaje1 ="Revisar notaria no puede quedar vacio"  +  newInv2.getDescripcion() ;
                                 tipoMsj1 = "error";
                               resp=2;
                              }
                          }

                        if (newInv2.getsDocumentoId().trim().equals("0013")){
                            if (newInv2.getSAsPoder().length()==0){
                                showMsg2 = true;
                                  mensaje2 ="Revisar Asiento no puede quedar vacio del documento "  +  newInv2.getDescripcion() ;
                                    tipoMsj2 = "error";
                               resp=2;
                             }
                            if (newInv2.getSPePoder().length()==0){
                                showMsg3 = true;
                                  mensaje3 ="Revisar Partida no puede quedar vacio del documento "  +  newInv2.getDescripcion() ;
                                    tipoMsj3 = "error";
                               resp=2;
                             }
                        }

                        
                        if (newInv2.getsDocumentoId().trim().equals("0014")){
                            if (newInv2.getSAsHipo().length()==0){
                             showMsg4 = true;
                                mensaje4 ="Revisar Asiento no puede quedar vacio del documento "  +  newInv2.getDescripcion() ;
                                    tipoMsj4 = "error";
                               resp=2;
                             }
                            if (newInv2.getSAsExpTchn().length()==0){
                                showMsg5 = true;
                                  mensaje5 ="Revisar expediente no puede quedar vacio del documento "  +  newInv2.getDescripcion() ;
                                    tipoMsj5 = "error";
                               resp=2;
                             }



                            if (newInv2.getSPeHipo().length()==0){
                                showMsg6 = true;
                                  mensaje6 ="Revisar Partida no puede quedar vacio del documento "  +  newInv2.getDescripcion() ;
                                    tipoMsj6 = "error";
                               resp=2;
                             }
                        }
                }
               }
               if ( newInv2.getSEstado() != null  ){ 
                    
                     if ( newInv2.getSEstado().equals("0002")  ){
                        
                      if ( newInv2.getSSubsanado() != null ) {
                         if (newInv2.getSSubsanado().equals("S")){
                     
                          
                             if (newInv2.getsDocumentoId().trim().equals("0012")){

                                  if (newInv2.getSNotaria().length()==0){
                                      showMsg1 = true;
                                       mensaje1 ="Revisar notaria no puede quedar vacio"  +  newInv2.getDescripcion() ;
                                         tipoMsj1 = "error";
                                    resp=2;
                                   }
                               }

                             if (newInv2.getsDocumentoId().trim().equals("0013")){
                                 if (newInv2.getSAsPoder().length()==0){
                                     showMsg2 = true;
                                       mensaje2 ="Revisar Asiento no puede quedar vacio del documento "  +  newInv2.getDescripcion() ;
                                         tipoMsj2 = "error";
                                    resp=2;
                                  }
                                 if (newInv2.getSPePoder().length()==0){
                                     showMsg3 = true;
                                       mensaje3 ="Revisar Partida no puede quedar vacio del documento "  +  newInv2.getDescripcion() ;
                                         tipoMsj3 = "error";
                                    resp=2;
                                  }
                             }

                             if (newInv2.getsDocumentoId().trim().equals("0014")){
                                 if (newInv2.getSAsHipo().length()==0){
                                  showMsg4 = true;
                                     mensaje4 ="Revisar Asiento no puede quedar vacio del documento "  +  newInv2.getDescripcion() ;
                                         tipoMsj4 = "error";
                                    resp=2;
                                  }
                                 if (newInv2.getSAsExpTchn().length()==0){
                                     showMsg5 = true;
                                       mensaje5 ="Revisar expediente no puede quedar vacio del documento "  +  newInv2.getDescripcion() ;
                                         tipoMsj5 = "error";
                                    resp=2;
                                  }



                                 if (newInv2.getSPeHipo().length()==0){
                                     showMsg6 = true;
                                       mensaje6 ="Revisar Partida no puede quedar vacio del documento "  +  newInv2.getDescripcion() ;
                                         tipoMsj6 = "error";
                                    resp=2;
                                  }
                             }
                           }
                      }else{
                          newInv2.setSSubsanado("N");
                      }
                    }
                     
                }
               }
                
                 
           int msgCorreo=0;   
              for (MaeInversion newInv : maeInversionList2) {
             
                newInv.setcUsuarioAdd(SessionUtils.getUserName());
                newInv.setcUsuarioMod(SessionUtils.getUserName());
                if (newInv.getXFlagSel().equals(true)){
                    newInv.setsSelec("S");
                }else{
                   newInv.setsSelec("N");
                }
                
                     f1 = newInv.getFRegistro();
                    f2 = newInv.getFSubsanado();
                   
                 
                    if (f1!=null && f2!=null){
                      
                        if ( f1.after(f2)){
                            showMsg = true;
                           resp=1;
                          mensaje ="Revisar registro " + newInv.getDescripcion() ;
                          tipoMsj = "error";
                          
                      }
                    }else{
                        String sub=newInv.getSSubsanado();
                       
                        if (sub!=null && f2==null){
                          if (sub.contains("S")){
                              showMsg = true;
                            resp=1;
                            mensaje ="Revisar registro " + newInv.getDescripcion() ;
                            tipoMsj = "error";

                         }
                        }
                       
                    }
            
                 
              }
              
             int msfinal = 0;
              for (MaeInversion newInv1 : maeInversionList2) {
             
                newInv1.setcUsuarioAdd(SessionUtils.getUserName());
                newInv1.setcUsuarioMod(SessionUtils.getUserName());
                if (newInv1.getXFlagSel().equals(true)){
                    newInv1.setsSelec("S");
                }else{
                   newInv1.setsSelec("N");
                }
                
                System.out.println("cuardando cambios " + newInv1.getCInversion()+ " "+ newInv1.getsDocumentoId());       
                resp = getInversionServ().insertarCustodiaf2(newInv1);
                
                if(resp==0){
                        showMsg = true;
                        msfinal = 1;
                        mensaje ="Se identificó error de datos en uno de las filas.";
                        tipoMsj = "error";   
                        break;
                }
                
                System.out.println("respuesta de cambios " + resp);
                
                if (newInv1.getsDocumentoId().equals("0015") && newInv1.getSTchn().length()>0){
                                  
                    msgCorreo = getInversionServ().validaCorreo(newInv1);
                    System.out.println("imprimiendo222 " + msgCorreo);
                   if  (msgCorreo == 0 ) {
                     Thread.sleep(1*1000);
                     sendEmail(newInv1);  
                   }
                }   
              }
            
              if(msfinal==0){
                showMsg = true;
                tipoMsj = "success";
                mensaje = "Se grabo Correctamente";
            }
            
            
             // maeInversionList2 = getInversionServ().listarCustodiaf2(oInversion);
             
              
            
                        
          } catch (Exception e) {
            System.err.println("pop.webcustodia.bean.InversionBean.InsertarCustodia()");
        }
    }

    
    
     
     public void InsertarCustodiaCargaf2(MaeInversion oInversion) {
        try {
            
            showMsg = false;
            int resp=0;
              
            Date f1 =  null;
            Date f2 =  null;
            for (MaeInversion newInv2 : maeInversionList2) {

               if ( newInv2.getSEstado() != null  ){ 
                    
                     if ( newInv2.getSEstado().equals("0002")  ){
                        
                      if ( newInv2.getSSubsanado() == null ) {
                          newInv2.setSSubsanado("N");
                      }
                    }
                     
                }
            }
                
                 
           int msgCorreo=0;   
              for (MaeInversion newInv : maeInversionList2) {
             
                newInv.setcUsuarioAdd(SessionUtils.getUserName());
                newInv.setcUsuarioMod(SessionUtils.getUserName());
                if (newInv.getXFlagSel().equals(true)){
                    newInv.setsSelec("S");
                }else{
                   newInv.setsSelec("N");
                }
                
                
              }
              for (MaeInversion newInv1 : maeInversionList2) {
             
                newInv1.setcUsuarioAdd(SessionUtils.getUserName());
                newInv1.setcUsuarioMod(SessionUtils.getUserName());
                if (newInv1.getXFlagSel().equals(true)){
                    newInv1.setsSelec("S");
                }else{
                   newInv1.setsSelec("N");
                }
                
                System.out.println("imprimiendo222 " + newInv1.getCInversion()+ " "+ newInv1.getsDocumentoId());       
                
                resp = getInversionServ().insertarCustodiaf2(newInv1);
                
                if (newInv1.getsDocumentoId().equals("0015") && newInv1.getSTchn().length()>0){
                                  
                    msgCorreo = getInversionServ().validaCorreo(newInv1);
                    System.out.println("imprimiendo222 " + msgCorreo);
                   if  (msgCorreo == 0 ) {
                     Thread.sleep(1*1000);
                     sendEmail(newInv1);  
                   }
                }   
              }
          
              
            
             // maeInversionList2 = getInversionServ().listarCustodiaf2(oInversion);
             
              
            
                        
          } catch (Exception e) {
            System.err.println("pop.webcustodia.bean.InversionBean.InsertarCustodia()");
        }
    }

     
     
    public void SalirDetalle(MaeInversion oInversion) {
        try {
           int resp=0;
         
           this.InsertarCustodiaCarga(oInversion);
            //resp = getInversionServ().eliminarCustodia(maeInversionList2.get(0).getMaeFondo().getCFondoId(),maeInversionList2.get(0).getCInversion());
             
            buscarDeudores();
          showMsg = false;
           maeInversionList2.clear(); 
          
            System.out.println("SALIENDO DEL FORMULARIO  InsertarCustodia - InsertarCustodia " + oInversion.getCInversion());
           
             
          } catch (Exception e) {
            System.err.println("pop.webcustodia.bean.InversionBean.InsertarCustodia()");
        }
    }
    
    public void SalirDetallef2(MaeInversion oInversion) {
        try {
           int resp=0;
            InsertarCustodiaCargaf2(oInversion);             
            buscarDeudoresf2();
             showMsg = false;
             showMsg1 = false;
             showMsg2 = false;
             showMsg3 = false;
             showMsg4 = false;
             showMsg5 = false;
             showMsg6 = false;
             showMsg7 = false;
             
          maeInversionList2.clear(); 
          
            System.out.println("SALIENDO DEL FORMULARIO  InsertarCustodia - InsertarCustodia " + oInversion.getCInversion());
           
             
          } catch (Exception e) {
            System.err.println("pop.webcustodia.bean.InversionBean.InsertarCustodia()");
        }
    }
    
    
    public void GenerarPDF(MaeInversion oInversion) {
        try {
            MaeReporte maeReporte = new MaeReporte();    
            maeReporte.setcUsuarioAdd(SessionUtils.getUserName());
            maeReporte.setUserName(SessionUtils.getUserName());
            maeReporte.setNumAsesor(SessionUtils.getNumAdviser());
            maeReporte.setMailAsesor(SessionUtils.getEmailAdviser());
            String ruta = null;
            
            ruta = inversionServ.imprimeMOV(oInversion);


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
                hsr.setContentType("application/pdf");
                hsr.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
                hsr.setHeader("Content-disposition", "inline;filename=\"" + "Custodia" + oInversion.getCInversion().trim() + ".pdf" + "\"");
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

            
             } catch (Exception e) {
            System.err.println("Error..."+e.getMessage());
        }   
        
    }
    
    
    
    public void GenerarWord(MaeInversion oInversion) {
        try {
           int resp=0;
             
           buscarDeudoresMov();
          showMsg = false;
           maeInversionList2.clear(); 
          setXViewDocu(false);    
            System.out.println("SALIENDO DEL FORMULARIO  InsertarCustodia - InsertarCustodia " + oInversion.getCInversion());
           
             
          } catch (Exception e) {
            System.err.println("pop.webcustodia.bean.InversionBean.InsertarCustodia()");
        }
    }
    
    
    public void SalirDetalleMov(MaeInversion oInversion) {
        try {
           int resp=0;
          showMsg = false;
          setXViewDocu(false);
          setXActiva("active");
          setXActiva2("");   
           maeInversionList2.clear(); 
           maeInversionList.clear();
          // maeInversionListDoc.clear();
          //buscarDeudoresMov();
    
            System.out.println("SALIENDO DEL FORMULARIO  InsertarCustodia - InsertarCustodia " + oInversion.getCInversion());
           
             
          } catch (Exception e) {
            System.err.println("pop.webcustodia.bean.InversionBean.InsertarCustodia()");
        }
    }
    
    
    
    public void SalirDetalleConsulta(MaeInversion oInversion) {
        try {
            int resp=0;
            resp = getInversionServ().eliminarCustodia(maeInversionList2.get(0).getMaeFondo().getCFondoId(),maeInversionList2.get(0).getCInversion());
            
            buscarDeudoresConsulta();
            showMsg = false;
            maeInversionList2.clear(); 
          
             
          } catch (Exception e) {
            System.err.println("pop.webcustodia.bean.InversionBean.InsertarCustodia()");
        }
    }
    
    public void CambiarFlag( ) {
        try { 
              xFlag  =true;
             
                System.out.println("ACA ESTA LA INFORMACION3333");
          } catch (Exception e) {
            System.err.println("pop.webcustodia.bean.InversionBean.InsertarCustodia()");
        }
    }
    
    public void CambiarArea(MaeInversion oInversion) {
        try {
              xFlag  =true;
             System.out.println("SSSS" +  oInversion.getSCustodia()); 
              if (oInversion.getSCustodia().equals("SI")){
                  oInversion.setSDestino("0001");
                   System.out.println("ACA ESTA LA entro");
              }else{
                     oInversion.setSDestino("0000"); 
              }
                System.out.println("ACA ESTA LA INFORMACION3333");
          } catch (Exception e) {
            System.err.println("pop.webcustodia.bean.InversionBean.InsertarCustodia()");
        }
    }
    
     public void AgregarDetalleDocumentos(MaeInversion oInversion) {
        try {
            
             showMsg = false;
             showMsg1 = false;
             showMsg2 = false;
             showMsg3 = false;
             showMsg4 = false;
             showMsg5 = false;
             showMsg6 = false;
             showMsg7 = false;
                System.out.println("oInversion.getCInversion())"+oInversion.getCInversion()+maeInversion.getsDocumentoId());
                    MaeInversion newInversion = new MaeInversion();
                    newInversion.setCInversion(maeInversionList2.get(0).getCInversion());
                    newInversion.setcPersonaId(oInversion.getcPersonaId()); 
                    newInversion.setiDoc_numero(0);
                    newInversion.getcPersonaId().setDApeMat(oInversion.getcPersonaId().getDApeMat());
                    newInversion.getcPersonaId().setDApePat(oInversion.getcPersonaId().getDApePat());
                    newInversion.getcPersonaId().setDNombres(oInversion.getcPersonaId().getDNombres());
                    newInversion.setMaeFondo(maeInversionList2.get(0).getMaeFondo());
                    newInversion.setsDocumentoId(maeInversion.getsDocumentoId());
                    maeInversionListDetalle.add(newInversion);
                    this.lstInversion.add(newInversion);
                
                 
                System.out.println("ACA ESTA LA AgregarDetalleDocumentos"+oInversion.getsDocumentoId());
          } catch (Exception e) {
            System.err.println("pop.webcustodia.bean.InversionBean.InsertarCustodia()");
        }
    }
    
    public void GrabaDetalleDocumentos() {
        try {
            
               showMsg7 = true;
               showMsg = false;
               showMsg1 = false;
               showMsg2 = false;
               showMsg3 = false; 
              showMsg4 = false;
              showMsg5 = false;
              showMsg6 = false;
               int respuesta=0;
                 for (MaeInversion delInv1 : maeInversionListDetalle) {
                   delInv1.setcUsuarioAdd(SessionUtils.getUserName());
                    respuesta =getInversionServ().grabarDocDetCustodia(delInv1);
                 }
                 
                     System.out.println("la GrabaDetalleDocumentos es"+respuesta);
                this.lstInversion.clear();
                    
                if (maeInversionList2.size()>0){
                         maeInversionList2 = getInversionServ().listarCustodiaf2(maeInversionList2.get(0));
                } 
                 if (respuesta==1){
                         tipoMsj = "success";
                        mensaje = "Se grabo Correctamente";
        
                 }
                 
        } catch (Exception e) {
            System.err.println("pop.webcustodia.bean.InversionBean.InsertarCustodia()");
        }
    }
    
    public void CerrarDetalleDocumentos() {
        try {
            lstInversion = new ArrayList<>();
            /*
            if (maeInversionList2.size()>0){
                     maeInversionList2 = getInversionServ().listarCustodiaf2(maeInversionList2.get(0));
            } */
            maeInversionListDetalle.clear();   
            
                     System.out.println("cerraando documento"+maeInversionListDetalle.size());
    
            showMsg = false;
             showMsg1 = false;
             showMsg2 = false;
             showMsg3 = false;
             showMsg4 = false;
             showMsg5 = false;
             showMsg6 = false;
             showMsg7 = false;
               /* for (MaeInversion delInv1 : maeInversionListDetalle) {
                    maeInversionListDetalle.remove(delInv1);
                }*/
                      System.out.println("la CerrarDetalleDocumentos es");
                this.lstInversion.clear();
            
                
                     System.out.println("cerraando documento"+maeInversionListDetalle.size());
        
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    
    
    public void EliminarDetalleDocumentos(MaeInversion oInversion) {
        try {
              showMsg = false;
             showMsg1 = false;
             showMsg2 = false;
             showMsg3 = false;
             showMsg4 = false;
             showMsg5 = false;
             showMsg6 = false;
             showMsg7 = true;
               
        
              int respuesta=0;
             System.out.println("Eliminar EliminarDetalleDocumentos"+oInversion.getsCODDocumento()+oInversion.getCInversion()+"PADRE "+ oInversion.getsDocumentoId()); 
                respuesta =getInversionServ().eliminarDocCustodia(oInversion);
                int a=0;
                for (MaeInversion delInv1 : maeInversionListDetalle) {
                    if (delInv1.getSCODDocumento().equals(oInversion.getSCODDocumento()) &&  delInv1.getCInversion().equals(oInversion.getCInversion()) 
                            &&  delInv1.getiDoc_numero()==oInversion.getiDoc_numero() ){
                        maeInversionListDetalle.remove(a);
                        this.lstInversion.remove(a);
              
                    }
                    a= a + 1;
                }
                 if (respuesta==1){
                         tipoMsj = "success";
                        mensaje = "Se grabo Correctamente";
        
                 }
    
                System.out.println("ACA ESTA LA EliminarDetalleDocumentos");
          } catch (Exception e) {
            System.err.println("pop.webcustodia.bean.InversionBean.InsertarCustodia()");
        }
    }
    

    
    public void mostrarSolicitante(String xsoli) {
        try {
              xFlag  =true;
              cobTablasEstInv.setCtablaDetId(xsoli);
              cobtablasListSoli = getTablasServ().listarSolicitante(cobTablasEstInv);
              
          } catch (Exception e) {
            System.err.println("pop.webcustodia.bean.InversionBean.InsertarCustodia()");
        }
    }

    
    public void guardarInformacion(MaeInversion oInversion) {
        try {
           showMsg = true;
             int resp=0;
            Date f1 =  null;
            Date f2 =  null;
            oInversion.setcUsuarioAdd(SessionUtils.getUserName());
            oInversion.setcUsuarioMod(SessionUtils.getUserName());
          if (oInversion.getXFlagSel()== true){
              oInversion.setsSelec("S");
              maeInversionList2 =getInversionServ().listarCustodiaMovi(oInversion);
              for (MaeInversion newInv1 : maeInversionList2) {
                  if (newInv1.getcTipoInv().equals("SI")  && oInversion.getSTipOpera().equals("0002") ){
                       resp=1;
                      mensaje ="Revisar no se pueden salir todos los documentos, hay alguno que esta prestado  " ;
                      tipoMsj = "error";
                  }
                  if (newInv1.getcTipoInv().equals("NO")  && oInversion.getSTipOpera().equals("0001") ){
                       resp=1;
                      mensaje ="Revisar se pueden ingresar todos los documentos hay alguno que esta en custodia" ;
                      tipoMsj = "error";
                      
                  }
              }
              
              
           //   System.out.println("PROBANDO  "+maeInversionList2.size());
          }else{    
                oInversion.setsSelec("N");
          }  
          if ( oInversion.getSTipOpera().equals("0002") ){
              f1 = oInversion.getDFechaIng();
              f2 = oInversion.getDFechaSal();
              if (f1!=null && f2!=null ){
                if ( f2.after(f1)){
                       resp=1;
                      mensaje ="Revisar registro fecha de salida no puede ser mayor a la de ingreso " ;
                      tipoMsj = "error";
                }
              };
              
              if (oInversion.getSSolicitante()==null || oInversion.getSDestino()==null ){
                        mensaje ="Revisar area o solicitante  " ;
                      tipoMsj = "error";
                       resp=1;
              }
              
              if (oInversion.getSComentario().length()==0 ){
                        mensaje ="Revisar informacion en comentario  " ;
                      tipoMsj = "error";
                       resp=1;
              }
              
               if (oInversion.getSMotivo().length()== 0){
                        mensaje ="Revisar motivo " ;
                      tipoMsj = "error";
                       resp=1;
              }
               
          }else{
              f1=oInversion.getDFechaIng();
                     System.out.println(oInversion.getSComentario().length());

                if (f1==null  ){
                
                       resp=1;
                      mensaje ="Revisar registro fecha de ingreso " ;
                      tipoMsj = "error";
              };
             
              if (oInversion.getSSolicitante()==null || oInversion.getSDestino()==null ){
                        mensaje ="Revisar area o solicitante  " ;
                      tipoMsj = "error";
                       resp=1;
              }
              
              if (oInversion.getSComentario().length()==0 ){
                        mensaje ="Revisar informacion en comentario  " ;
                      tipoMsj = "error";
                       resp=1;
              }
              
              
           }
          
          System.out.println(resp + "aca esta la info");
          
            if ( resp==0){
                showMsg = true;
                resp = getInversionServ().insertarCustodiaMov(oInversion);
                System.out.println("este es el resultado "  +  resp );
                if (resp==0){
                 mensaje ="Guardo informacion correctamente...  " ;
                    tipoMsj = "success";
                }else{
                       mensaje ="Revisar informacion de las fechas" ;
                      tipoMsj = "error";
               
                }
                
            }
            
          setXActiva("active");
          setXActiva2("");  
            
          } catch (Exception e) {
            System.err.println("pop.webcustodia.bean.InversionBean.InsertarCustodia()");
        }
    }

    
    

    
    public void obtenerSeguimientoDetalle(MaeInversion oInversion) {
       try {
              xFlag = false;
              mensaje="";  
              showMsg = false;
              
              maeInversionList2 = getInversionServ().listarCustodia(oInversion);
              Date fecha =new Date();     
              if ( maeInversionList2.size()==0){
                     cobTablasEstInv.setCtablaId("0004");
               
                     cobTablasEstInvList3 = getTablasServ().listarTablasInv(oInversion.getMaeFondo().getCFondoId(),oInversion.getCInversion(), cobTablasEstInv);
                     for (CobTablas anew : cobTablasEstInvList3) {
                          MaeInversion newInversion = new MaeInversion();
                          newInversion.setCInversion(oInversion.getCInversion());
                          newInversion.setcPersonaId(oInversion.getcPersonaId()); 
                          newInversion.getcPersonaId().setDApeMat(oInversion.getcPersonaId().getDApeMat());
                          newInversion.getcPersonaId().setDApePat(oInversion.getcPersonaId().getDApePat());
                          newInversion.getcPersonaId().setDNombres(oInversion.getcPersonaId().getDNombres());
                          newInversion.setMaeFondo(oInversion.getMaeFondo());
                          newInversion.setsSelec("S");
                          newInversion.setXFlagSel(true);
                          newInversion.setcUsuarioAdd(SessionUtils.getUserName());
                          newInversion.setFRegistro(fecha);
                          newInversion.setfUsuarioAdd(fecha);
                          newInversion.setsDocumentoId(anew.getCtablaDetId());
                          newInversion.setDescripcion(anew.getDdescripcion());
                          newInversion.setsCerrado("N");
                          newInversion.setXFlagCerrado(false);
                          maeInversionList2.add(newInversion);   
                          InsertarCustodiaCarga(newInversion);
                     }
                }
              
              cobTablasEstInv.setCtablaId("0002");
              cobTablasEstInv.seteEstado("01");
              cobTablasEstInvList2 = getTablasServ().listarTablas(cobTablasEstInv);
              cobTablasEstInv.setCtablaId("0005");
              cobTablasEstOrigenList = getTablasServ().listarTablas(cobTablasEstInv);
              
              
        } catch (Exception e) {
            System.err.println("pop.webcustodia.bean.InversionBean.obtenerSeguimientoDetalle()");
        }
    }
    
   public void sendEmail(MaeInversion oInversion){
      try {
                String body="El codigo"+ "\n"  + oInversion.getCInversion() + "perteneciente al fondo "  + oInversion.getMaeFondo().getDFondo() + "se encuentra en " + oInversion.getDescripcion()  ;
                String subject="Notificación de registro en modulo Custodia " + oInversion.getCInversion() + " en custodia";
                String from="";
                String pass="";
                String[] to=null;
                String  xdes="";
                String  xfondo="";
                MaeEmailList = getInversionServ().ListarEmail();
                  
                if (MaeEmailList.size()>0 ){
                    if (  oInversion.getSDestino().equals("0001") ){
                        xdes="CUSTODIA SAFI";
                    };
                    if (  oInversion.getSDestino().equals("0002") ){
                        xdes="PRESTABLUB";
                    };
                    if (  oInversion.getSDestino().equals("0003") ){
                        xdes="COBRANZA";
                    };
                    if (  oInversion.getSDestino().equals("0004") ){
                        xdes="LEGAL";
                    };
                    if (  oInversion.getSDestino().equals("0005") ){
                        xdes="CUSTODIA BNB";
                    };
                    if (oInversion.getMaeFondo().getCFondoId().equals("0001")){
                        xfondo="CAPITAL";
                    } 
                    if (oInversion.getMaeFondo().getCFondoId().equals("0002")){
                        xfondo="POPULAR";
                    } 
                    if (oInversion.getMaeFondo().getCFondoId().equals("0003")){
                        xfondo="MYPE";
                    } 
                    if (oInversion.getMaeFondo().getCFondoId().equals("0004")){
                        xfondo="PEREZ HIDALGO";
                    } 
                    body =  oInversion.getCInversion() + "perteneciente al fondo "  + xfondo + " se encuentra en " +
                            xdes + "\n\n\n\n" + "Saludos,    " + "\n"  + MaeEmailList.get(0).getDnombre() ;
                    subject = MaeEmailList.get(0).getCuerpo().trim()  + " -  " +  oInversion.getCInversion() + " en " +xdes;
                    from = MaeEmailList.get(0).getDusuario();
                    pass = MaeEmailList.get(0).getClave();
                    to  =  MaeEmailList.get(0).getDemail().split(",");
                    sendFromGMail(from,pass,to, subject,body);
                    
                }
                   
               } catch (Exception e) {
            System.err.println("pop.webcustodia.bean.InversionBean.buscarDeudores()");
        }        
   }
    public void obtenerSeguimientoDetallef2(MaeInversion oInversion) {
       try {
              xFlag = false;
              mensaje="";  
              showMsg = false;
              showMsg2 = false;
             MaeEmailList = getInversionServ().ListarEmail();
             maeInversionList2 = getInversionServ().listarCustodiaf2(oInversion);
             
              if ( maeInversionList2.size()== 0 ) {
                  
                     Date fecha =new Date();     
                     Boolean xflgDetalle=false;
                     cobTablasEstInv.setCtablaId("0004");
                     cobTablasEstInv.setEstado("01");
                     cobTablasEstInvList3 = getTablasServ().listarTablasInvf2(oInversion.getMaeFondo().getCFondoId(),oInversion.getCInversion(), cobTablasEstInv);
                   
                     for (CobTablas anew : cobTablasEstInvList3) {
                          MaeInversion newInversion = new MaeInversion();
                          newInversion.setCInversion(oInversion.getCInversion());
                          newInversion.setcPersonaId(oInversion.getcPersonaId()); 
                          newInversion.getcPersonaId().setDApeMat(oInversion.getcPersonaId().getDApeMat());
                          newInversion.getcPersonaId().setDApePat(oInversion.getcPersonaId().getDApePat());
                          newInversion.getcPersonaId().setDNombres(oInversion.getcPersonaId().getDNombres());
                          newInversion.setMaeFondo(oInversion.getMaeFondo());
                          
                          
                          if (anew.getFlg_detalle().equals(1)){
                             xflgDetalle= true; 
                          }else{
                             xflgDetalle= false;
                          }
                          
                          newInversion.setXFlagDetalle(xflgDetalle);
                          newInversion.setsSelec("S");
                          newInversion.setXFlagSel(true);
                          newInversion.setcUsuarioAdd(SessionUtils.getUserName());
                          newInversion.setFRegistro(fecha);
                          newInversion.setfInterno(fecha);
                          newInversion.setfUsuarioAdd(fecha);
                          newInversion.setsDocumentoId(anew.getCtablaDetId());
                          if (anew.getCtablaDetId().equals("0009") || anew.getCtablaDetId().equals("0010") || anew.getCtablaDetId().equals("0011")  ){
                            newInversion.setSCodOrigen("0003");      
                          }else{
                              newInversion.setSCodOrigen("0002");      
                            }  
                          newInversion.setsDocumentoId(anew.getCtablaDetId());      
                          newInversion.setDescripcion(anew.getDdescripcion());
                          newInversion.setsCerrado("N");
                          newInversion.setXFlagCerrado(false);
                          maeInversionList2.add(newInversion);   
                          InsertarCustodiaCargaf2(newInversion);
                     }
                }
              
              
              cobTablasEstInv.setCtablaId("0002");
              cobTablasEstInv.seteEstado("01");
              cobTablasEstInvList2 = getTablasServ().listarTablas(cobTablasEstInv);
              cobTablasEstInv.setCtablaId("0005");
              cobTablasEstOrigenList = getTablasServ().listarTablas(cobTablasEstInv);
              System.out.println("maeInversionList2 "+maeInversionList2.size());
              
              if(maeInversionList2.size()>0)
              {
                  mostrarAddButAddDocCust=true;
                  this.codigoTCHN=oInversion.getCInversion();
                  this.fondoId=oInversion.getMaeFondo().getCFondoId();
                  this.fondoDescripcion=oInversion.getMaeFondo().getDFondo();
                  int nval=0;
                  for (int i = 0; i < maeInversionList2.size(); i++) {
                          
                    
                      if ( maeInversionList2.get(i).getSEstado()!=null){ 
                      if ( maeInversionList2.get(i).getSEstado().equals("0001")   ){
                          System.out.println("ESTADO");
                          nval = nval + 1;
                       }
                     }
                      if ( maeInversionList2.get(i).getSSubsanado()!=null){
                        if ( maeInversionList2.get(i).getSSubsanado().equals("S")){
                            System.out.println("SUBSANADO");
                                nval = nval + 1;
                        }
                      } 
                }
                    System.out.println("nval "+nval);
                  maeInversion.setNDocNumero(nval);
              }
            
              
        } catch (Exception e) {
            System.err.println("pop.webcustodia.bean.InversionBean.obtenerSeguimientoDetalle()");
        }
    }

       public void  ConsultarSolicitante(){
        try {
            System.out.println("por aca esta pasando" ); 
         //    cobTablasEstInv.setCtablaId(codSoli);
           // cobtablasListSoli  = getTablasServ().listarSolicitante(cobTablasEstInv);
          //    System.out.println(cobtablasListSoli.size());
        } catch (Exception e) {
        }
 
    }
     

    public void obtenerSeguimientoDetalleMovi(MaeInversion oInversion) {
       try {
              xFlag = false;
              mensaje="";  
          showMsg = false;
          maeInversionList2 = getInversionServ().listarCustodiaMovi(oInversion);
              setXTamano("height: 650px;");  
         
              
              cobTablasEstInv.setCtablaId("0002");
              cobTablasEstInvList2 = getTablasServ().listarTablas(cobTablasEstInv);
              cobTablasEstInv.setCtablaId("0005");
              cobTablasEstOrigenList = getTablasServ().listarTablas(cobTablasEstInv);
           
              
              
        } catch (Exception e) {
            System.err.println("pop.webcustodia.bean.InversionBean.obtenerSeguimientoDetalle()");
        }
    }

    
    public void obtenerVerificaDoc(MaeInversion oInversion) {
       try {
           
           setXFlag(true);
           setXViewDocu(true);
           setXActiva("");
           setXActiva2("active");
           
           int correlativo=0;
           
 
           maeInversionListDoc = getInversionServ().listarCustodiaxDoc(oInversion);
         
           if (oInversion.getcTipoInv().equals("NO")){
              cobTablasEstInvMov.setCtablaId("0007");
              cobTablasEstInvMov.setCtablaDetId("0002");
              cobTablasCorrelaList = getTablasServ().listarTablas(cobTablasEstInvMov);
    
              
              correlativo = Integer.parseInt(cobTablasCorrelaList.get(0).getDdescCorta().trim().toString())+1;
               maeInversionListDoc.get(0).setcTipoInv("Orden de salida N° " + correlativo );
               maeInversionListDoc.get(0).setSTipOpera("0002");
               maeInversionListDoc.get(0).setNDocumento(correlativo);
               maeInversionListDoc.get(0).setSDestino("");
           }else{
              cobTablasEstInvMov.setCtablaId("0007");
              cobTablasEstInvMov.setCtablaDetId("0001");
         
              cobTablasCorrelaList = getTablasServ().listarTablas(cobTablasEstInvMov);
              
              
              correlativo = Integer.parseInt(cobTablasCorrelaList.get(0).getDdescCorta().trim().toString())+1;
               Date xfecha = new Date();
               maeInversionListDoc.get(0).setDFechaIng(xfecha);
               maeInversionListDoc.get(0).setcTipoInv("Orden de Ingreso N° " + correlativo );
               maeInversionListDoc.get(0).setSTipOpera("0001");
               maeInversionListDoc.get(0).setNDocumento(correlativo);
               maeInversionListDoc.get(0).setSDestino("");
           }
           
           cobTablasEstInvMov.setCtablaId("0005");
           cobTablasEstInvMov.setCtablaDetId(null);
           cobTablasEstInvList2 = getTablasServ().listarTablas(cobTablasEstInvMov);
           
           cobTablasEstInvMov.setCtablaId("0006");
           cobTablasEstInvMov.setCtablaDetId(null);
           cobTablasMotInvList = getTablasServ().listarTablas(cobTablasEstInvMov);
    
           
            
        } catch (Exception e) {
            System.err.println("pop.webcustodia.bean.InversionBean.obtenerSeguimientoDetalle()");
        }
    }

    public void addDocCust(){
        try {
            mostrarformAddDocCust=true;
           System.out.println("addDoccust"+this.codigoTCHN);
           //System.out.println("cobTablasDocInvList"+cobTablasDocInvList.size());
                    
           
         if (cobTablasDocInvList == null) {
              cobTablasDocInvList = new ArrayList<CobTablas>();
              cobTablasDocuInv.setCtablaId("0004");
               System.out.println("entrando a addDoccust"+this.codigoTCHN);
              //cobTablasDocInvList = getTablasServ().listarTablasDocusCustodia(cobTablasDocuInv); 
              
              for(CobTablas ct: getTablasServ().listarTablasDocusCustodia(cobTablasDocuInv,this.codigoTCHN)){
                  
                  int id = Integer.parseInt(ct.getCtablaDetId());
                  
                  if(id > 15){
                      cobTablasDocInvList.add(ct);
                  }
              }        
         }  
            this.codigoDocumentoCustodia="";
            this.setUsuario(SessionUtils.getUserName());
              
        } catch (Exception e) {
        }
    }
    
    public void cerrarPantallaAddDocCustodia(){
         try {
             setMostrarformAddDocCust(false);
        } catch (Exception e) {
                 
        }
    }    
    
    public void insertarDocumentoCustodia(){
        int rep=1;
        showMsg = true;
        System.out.println("insertarDocumentoCustodia");
        try {
            MaeDocOtros oMaeDocOtros = new MaeDocOtros();
            System.out.println("getCFondoId():"+this.fondoId);
            oMaeDocOtros.setCfondoId(this.fondoId);
            System.out.println("this.codigoTCHN:"+this.codigoTCHN);
            oMaeDocOtros.setDvalorBV(this.codigoTCHN);
            System.out.println("this.codigoDocumentoCustodia:"+this.codigoDocumentoCustodia);
            oMaeDocOtros.setDocumentoID(this.codigoDocumentoCustodia);
            
            if (!(this.fondoId.trim().length()>0)){
                setTipoMsj("error");
                setMensaje("Error: debe seleccionar un fondo");
                return;
            }            
            
            if (!(this.codigoTCHN.trim().length()>0)){
                setTipoMsj("error");
                setMensaje("Error: debe llenar TCHN");
                return;
            }
            
            if (!(this.codigoDocumentoCustodia.trim().length()>0)){
                setTipoMsj("error");
                setMensaje("Error: debe llenar TCHN");
                return;
            }
            
            System.out.println("seteando valores a oMaeDocOtros");
            System.out.println(oMaeDocOtros.toString());
            oMaeDocOtros.setcUsuarioAdd(SessionUtils.getUserName());
            System.out.println("Usuario Add:"+oMaeDocOtros.getcUsuarioAdd());
            rep = getInversionServ().insertarDocCustodia(oMaeDocOtros);
            System.out.println("rep:"+rep);
            if (rep == 0) {
                setTipoMsj("success");
                setMensaje("Se grabo tabla general");
                rep =0;
                
                MaeInversion oInversion = new MaeInversion();
                MaeFondo oMaeFondo = new MaeFondo();
                oMaeFondo.setCFondoId(this.fondoId);
                System.err.println("this.fondoId:"+this.fondoId);
                oInversion.setMaeFondo(oMaeFondo);
                oInversion.setCInversion(this.codigoTCHN);
                oInversion.setFEmision(emision);
                obtenerSeguimientoDetallef2(oInversion);
                setMostrarAddButAddDocCust(false);
                setMostrarformAddDocCust(false);
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
    
     public List<CobTablas> getCobTablasEstInvList2() {
        return cobTablasEstInvList2;
    }

    public void setCobTablasEstInvList2(List<CobTablas> cobTablasEstInvList2) {
        this.cobTablasEstInvList2 = cobTablasEstInvList2;
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
   
     public boolean isXFlag() {
        return xFlag;
    }

    public void setXFlag(boolean xFlag) {
        this.xFlag = xFlag;
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
    
     public List<MaeInversion> getMaeInversionListDetalle() {
        return maeInversionListDetalle;
    }

    public void setMaeInversionListDetalle(List<MaeInversion> maeInversionListDetalle) {
        this.maeInversionListDetalle = maeInversionListDetalle;
    }

    public List<CobTablas> getCobTablasEstOrigenList() {
        return cobTablasEstOrigenList;
    }

    public void setCobTablasEstOrigenList(List<CobTablas> cobTablasEstOrigenList) {
        this.cobTablasEstOrigenList = cobTablasEstOrigenList;
    }


private static void sendFromGMail(String from, String pass, String[] to, String subject, String body) 
 {
     
        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");//OK
        props.put("mail.transport.protocol", "smtp");//OK
        props.put("mail.smtp.starttls.enable", "true");//OK
        props.put("mail.smtp.port", "587");//OK
        //props.put("mail.smtp.port", "587");//OK
        //props.put("mail.smtp.user", from);
        //props.put("mail.smtp.password", pass);
        //props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);
        try 
        {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for( int i = 0; i < to.length; i++ ) {
                    toAddress[i] = new InternetAddress(to[i]);
                    System.out.println(to[i]);
            }

            for( int i = 0; i < toAddress.length; i++) {
                    message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject);
            message.setText(body);
            Transport.send(message,from, pass);
            //Transport transport = session.getTransport("smtp");
           // transport.connect("smtp.gmail.com", from, pass);
            //transport.sendMessage(message, message.getAllRecipients());
            //transport.close();
        }
        catch (AddressException ae) {
           System.out.println(ae);
        }
        catch (MessagingException me) {
                System.out.println(me);
        }
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
    
    public String getXActiva() {
        return XActiva;
    }

    public void setXActiva(String XActiva) {
        this.XActiva = XActiva;
    }

public String getXActiva2() {
        return XActiva2;
    }

    public void setXActiva2(String XActiva2) {
        this.XActiva2 = XActiva2;
    }

    public String getXTamano() {
        return XTamano;
    }

    public void setXTamano(String XTamano) {
        this.XTamano = XTamano;
    }

    /**
     * @return the mostrarAddButAddDocCust
     */
    public boolean isMostrarAddButAddDocCust() {
        return mostrarAddButAddDocCust;
    }

    /**
     * @param mostrarAddButAddDocCust the mostrarAddButAddDocCust to set
     */
    public void setMostrarAddButAddDocCust(boolean mostrarAddButAddDocCust) {
        this.mostrarAddButAddDocCust = mostrarAddButAddDocCust;
    }

    /**
     * @return the mostrarformAddDocCust
     */
    public boolean isMostrarformAddDocCust() {
        return mostrarformAddDocCust;
    }

    /**
     * @param mostrarformAddDocCust the mostrarformAddDocCust to set
     */
    public void setMostrarformAddDocCust(boolean mostrarformAddDocCust) {
        this.mostrarformAddDocCust = mostrarformAddDocCust;
    }

    /**
     * @return the codigoTCHN
     */
    public String getCodigoTCHN() {
        return codigoTCHN;
    }

    /**
     * @param codigoTCHN the codigoTCHN to set
     */
    public void setCodigoTCHN(String codigoTCHN) {
        this.codigoTCHN = codigoTCHN;
    }

    /**
     * @return the codigoDocumentoCustodia
     */
    public String getCodigoDocumentoCustodia() {
        return codigoDocumentoCustodia;
    }

    /**
     * @param codigoDocumentoCustodia the codigoDocumentoCustodia to set
     */
    public void setCodigoDocumentoCustodia(String codigoDocumentoCustodia) {
        this.codigoDocumentoCustodia = codigoDocumentoCustodia;
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
     * @return the fondoId
     */
    public String getFondoId() {
        return fondoId;
    }

    /**
     * @param fondoId the fondoId to set
     */
    public void setFondoId(String fondoId) {
        this.fondoId = fondoId;
    }

    /**
     * @return the fondoDescripcion
     */
    public String getFondoDescripcion() {
        return fondoDescripcion;
    }

    /**
     * @param fondoDescripcion the fondoDescripcion to set
     */
    public void setFondoDescripcion(String fondoDescripcion) {
        this.fondoDescripcion = fondoDescripcion;
    }

      public boolean isShowMsg1() {
        return showMsg1;
    }

    public void setShowMsg1(boolean showMsg1) {
        this.showMsg1 = showMsg1;
    }

    public String getMensaje1() {
        return mensaje1;
    }

    public void setMensaje1(String mensaje1) {
        this.mensaje1 = mensaje1;
    }

    public String getTipoMsj1() {
        return tipoMsj1;
    }

    public void setTipoMsj1(String tipoMsj1) {
        this.tipoMsj1 = tipoMsj1;
    }

    public boolean isShowMsg2() {
        return showMsg2;
    }

    public void setShowMsg2(boolean showMsg2) {
        this.showMsg2 = showMsg2;
    }

    public String getMensaje2() {
        return mensaje2;
    }

    public void setMensaje2(String mensaje2) {
        this.mensaje2 = mensaje2;
    }

    public String getTipoMsj2() {
        return tipoMsj2;
    }

    public void setTipoMsj2(String tipoMsj2) {
        this.tipoMsj2 = tipoMsj2;
    }

    public boolean isShowMsg3() {
        return showMsg3;
    }

    public void setShowMsg3(boolean showMsg3) {
        this.showMsg3 = showMsg3;
    }

    public String getMensaje3() {
        return mensaje3;
    }

    public void setMensaje3(String mensaje3) {
        this.mensaje3 = mensaje3;
    }

    public String getTipoMsj3() {
        return tipoMsj3;
    }

    public void setTipoMsj3(String tipoMsj3) {
        this.tipoMsj3 = tipoMsj3;
    }

    public boolean isShowMsg4() {
        return showMsg4;
    }

    public void setShowMsg4(boolean showMsg4) {
        this.showMsg4 = showMsg4;
    }

    public String getMensaje4() {
        return mensaje4;
    }

    public void setMensaje4(String mensaje4) {
        this.mensaje4 = mensaje4;
    }

    public String getTipoMsj4() {
        return tipoMsj4;
    }

    public void setTipoMsj4(String tipoMsj4) {
        this.tipoMsj4 = tipoMsj4;
    }

    public boolean isShowMsg5() {
        return showMsg5;
    }

    public void setShowMsg5(boolean showMsg5) {
        this.showMsg5 = showMsg5;
    }

    public String getMensaje5() {
        return mensaje5;
    }

    public void setMensaje5(String mensaje5) {
        this.mensaje5 = mensaje5;
    }

    public String getTipoMsj5() {
        return tipoMsj5;
    }

    public void setTipoMsj5(String tipoMsj5) {
        this.tipoMsj5 = tipoMsj5;
    }
 

 public boolean isShowMsg6() {
        return showMsg6;
    }

    public void setShowMsg6(boolean showMsg6) {
        this.showMsg6 = showMsg6;
    }

    public String getMensaje6() {
        return mensaje6;
    }

    public void setMensaje6(String mensaje6) {
        this.mensaje6 = mensaje6;
    }

    public String getTipoMsj6() {
        return tipoMsj6;
    }

    public void setTipoMsj6(String tipoMsj6) {
        this.tipoMsj6 = tipoMsj6;
    }

     public List<CobTablas> getCobtablasListDocDeta() {
        return cobtablasListDocDeta;
    }

    public void setCobtablasListDocDeta(List<CobTablas> cobtablasListDocDeta) {
        this.cobtablasListDocDeta = cobtablasListDocDeta;
    }


    public boolean isShowMsg7() {
        return showMsg7;
    }

    public void setShowMsg7(boolean showMsg7) {
        this.showMsg7 = showMsg7;
    }

    public String getMensaje7() {
        return mensaje7;
    }

    public void setMensaje7(String mensaje7) {
        this.mensaje7 = mensaje7;
    }

    public String getTipoMsj7() {
        return tipoMsj7;
    }

    public void setTipoMsj7(String tipoMsj7) {
        this.tipoMsj7 = tipoMsj7;
    }
   
}
