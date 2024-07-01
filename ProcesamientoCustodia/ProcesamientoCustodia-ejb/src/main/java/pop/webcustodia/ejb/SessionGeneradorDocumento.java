/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.ejb;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;

import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import fr.opensagres.poi.xwpf.converter.core.XWPFConverterException;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import java.awt.Desktop;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import javax.ejb.Stateless;
import oracle.jdbc.OracleConnection;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPicture;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import pop.comun.dominio.DocPlantilla;
import pop.comun.dominio.MaeEmail;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MaePersonaInmueble;
import pop.comun.dominio.MaeReporte;
import pop.comun.dominio.TabArchivo;
import pop.comun.dominio.util.NumeroLetra;

import pop.webcustodia.conn.Conexion;
import pop.webcustodia.dao.FactoryDao;
import pop.webcustodia.negocio.INegGeneradorDocumento;
import pop.webcustodia.negocio.INegInversion;
import pop.webcustodia.reporte.HeaderFooter;

/**
 *
 * @author Jyoverar
 */
@Stateless(mappedName = "ejbInversion")
public class SessionGeneradorDocumento implements INegGeneradorDocumento {

    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);
    FactoryDao ofDao = new FactoryDao();
 




    @Override
    public List<MaeInversion> listarPropietarios(MaeInversion oMaeInversion) throws Exception {
        List<MaeInversion> oInvList = null;

        oInvList = ofDao.getGeneraDocumentoDao().fetchPropietarios(oMaeInversion);
        return oInvList;
    }
    
    @Override
    public List<MaeInversion> listarClientes(MaeInversion oMaeInversion) throws Exception {
        List<MaeInversion> oInvList = null;

        oInvList = ofDao.getGeneraDocumentoDao().fetchClientes(oMaeInversion);
        return oInvList;
    }    
    
    @Override
    public List<MaeInversion> listarAllPropietarios(MaeInversion oMaeInversion) throws Exception {
        List<MaeInversion> oInvList = null;

        oInvList = ofDao.getGeneraDocumentoDao().fetchAllPropietarios(oMaeInversion);
        return oInvList;
    }    

    
    @Override
    public Integer insertarGeneraDoc(MaeInversion oMaeInversion) throws Exception {
        Integer Rspta = 0;
        Rspta = ofDao.getGeneraDocumentoDao().fetchInsGeneraDoc(oMaeInversion);
        return Rspta;
    }
    
    @Override
    public Integer anulaGeneraDoc(MaeInversion oMaeInversion) throws Exception {
        Integer Rspta = 0;
        Rspta = ofDao.getGeneraDocumentoDao().fetchAnulaCarta(oMaeInversion);
        return Rspta;
    }    

    @Override
    public Integer insertarConstancias(MaeInversion oMaeInversion) throws Exception {
        Integer Rspta = 0;
        Rspta = ofDao.getGeneraDocumentoDao().fetchInsertaConstancia(oMaeInversion);
        return Rspta;
    }   
    
    @Override
    public MaeInversion anulaConstancia(MaeInversion oMaeInversion) throws Exception {
        MaeInversion Rspta = null;
        Rspta = ofDao.getGeneraDocumentoDao().anulaConstancia(oMaeInversion);
        return Rspta;
    }     
  

    @Override
    public Integer generaCarta1(MaeInversion oMaeInversion) throws Exception {
        Locale.setDefault(new Locale("es", "ES"));
        String newline = "\n";
        int respGen=0;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatterDia = new SimpleDateFormat("dd");
        SimpleDateFormat formatterMes = new SimpleDateFormat("MMMM");
        SimpleDateFormat formatterAnio = new SimpleDateFormat("yyyy");
    
        String ruta;
        String name;
        //fecha = formatter.parse("07/04/2017");
        // 1) Load DOCX into XWPFDocument;
        List<MaeInversion> oListaCarta = null; 
        oListaCarta =  ofDao.getGeneraDocumentoDao().fetchGeneraCarta(oMaeInversion);
        String lscarta="";
        System.out.println("generar carta "+ oListaCarta.size());
      if (oListaCarta.size()==1){
            if(oMaeInversion.getSTCarta().equals("0001")){
                lscarta = "1raCartaNotarial.docx";
            }
            if (oMaeInversion.getSTCarta().equals("0002")){
                lscarta = "2daCartaNotarial.docx"; 
            }
            if (oMaeInversion.getSTCarta().equals("0003")){
                lscarta = "3raCartaNotarialProtestado.docx";
            }
            if (oMaeInversion.getSTCarta().equals("0004")){
                lscarta = "4taultimaCarta.docx";
            }
            if (oMaeInversion.getSTCarta().equals("0005")){
                lscarta = "5taCartatransfrencia.docx";
            }
            if(oMaeInversion.getSTCarta().equals("0007")){
                lscarta = "1raCartaNotarialSintchn.docx";
            }
            if(oMaeInversion.getSTCarta().equals("0008")){
                lscarta = "1raCartaNotarialSintchnAsiento.docx";
            }
            if(oMaeInversion.getSTCarta().equals("0009")){
                lscarta = "2daCartaNotarialSintchn.docx";
            }
            if(oMaeInversion.getSTCarta().equals("0010")){
                lscarta = "2daCartaNotarialSintchnAsiento.docx";
            }
            if(oMaeInversion.getSTCarta().equals("0011")){
                lscarta = "6taComunicacionCesionPJ.docx";
            }            
          
        InputStream is = new FileInputStream("C:\\pop\\webcustodia\\resources\\template\\legal\\"+lscarta);
        XWPFDocument document = new XWPFDocument(is);
        // dato sueltos
        for (XWPFParagraph p : document.getParagraphs()) {
            List<XWPFRun> runs = p.getRuns();
            if (runs != null) {
                for (XWPFRun r : runs) {
                    String text = r.getText(0);
                    System.out.println(text);
                    if (text != null && text.contains("$CODIGOTCHN")) {
                        text = text.replace("$CODIGOTCHN",oListaCarta.get(0).getCInversion().trim());
                        r.setText(text, 0);                        
                    }
                                    // reemplaza la fecha
                    
                    if (text != null && text.contains("$FEMISION")) {
                        text = text.replace("$FEMISION", formatterDia.format(oMaeInversion.getFEmision()) + " de " + formatterMes.format(oMaeInversion.getFEmision()) + " del " + formatterAnio.format(oMaeInversion.getFEmision()));
                        r.setText(text, 0);
                    }
                    
                    
                    if (text != null && text.contains("$NOMBRES")) {
                        text = text.replace("$NOMBRES",oListaCarta.get(0).getSNombreSol().trim());
                        r.setText(text, 0);
                    }
                    if (text != null && text.contains("$DIRECCION")) {
                        text = text.replace("$DIRECCION",oListaCarta.get(0).getcInmueble().getADir1().trim());
                        r.setText(text, 0);
                    }
                    if (text != null && text.contains("$DISTRITO")) {
                        text = text.replace("$DISTRITO",oListaCarta.get(0).getcInmueble().getCUbigeoId().trim());
                        r.setText(text, 0);
                    }
                    if (text != null && text.toUpperCase().contains("$REFERENCIA")) {
                        text = text.replace("$REFERENCIA",oListaCarta.get(0).getcInmueble().getADir2().toLowerCase().trim());
                        r.setText(text, 0);
                    }
                    if (text != null && text.contains("$FONDOINV")) {
                        text = text.replace("$FONDOINV",oListaCarta.get(0).getMaeFondo().getDFondo().trim());
                        r.setText(text, 0);
                    }
                    if (text != null && text.contains("$FONDOABRV")) {
                        text = text.replace("$FONDOABRV",oListaCarta.get(0).getMaeFondo().getDFondocorto().trim());
                        r.setText(text, 0);
                    }
                    if (text != null && text.contains("$ASHIPO")) {
                        text = text.replace("$ASHIPO",oListaCarta.get(0).getsAsHipo().trim());
                        r.setText(text, 0);
                    }
                    if (text != null && text.contains("$PEHIPO")) {
                        text = text.replace("$PEHIPO",oListaCarta.get(0).getsPeHipo().trim());
                        r.setText(text, 0);
                    } 
                    if (text != null && text.contains("$FESCRITURA")) {
                        text = text.replace("$FESCRITURA",formatter.format(oListaCarta.get(0).getFEscritura()));
                        r.setText(text, 0);
                    }
                    
                    if (text != null && text.contains("$FEMITCHN")) {
                                                
                        text = text.replace("$FEMITCHN",formatter.format(oListaCarta.get(0).getfTchn()));
                        r.setText(text, 0);
                    }                   
                    
                    if (text != null && text.contains("$NOMNOTARIO")) {
                        text = text.replace("$NOMNOTARIO",oListaCarta.get(0).getSNotaria().trim());
                        r.setText(text, 0);
                    }
                    if (text != null && text.contains("$STCHN")) {
                        text = text.replace("$STCHN",oListaCarta.get(0).getsTchn().trim());
                        r.setText(text, 0);
                    }
                    
                    if (text != null && text.contains("$ASEXPTCHN")) {
                        text = text.replace("$ASEXPTCHN",oListaCarta.get(0).getsAsExpTchn().trim());
                        r.setText(text, 0);
                    }
                    
                    if (oMaeInversion.getSTCarta().equals("0005") || oMaeInversion.getSTCarta().equals("0011")){
                        
                                  
                         if (text != null && text.contains("$FONDOORIGEN")) {
                                text = text.replace("$FONDOORIGEN",oListaCarta.get(0).getSDfondoOri().trim());
                                r.setText(text, 0);
                         }
                         
                         if (text != null && text.contains("$FONDODES")) {
                                text = text.replace("$FONDODES",oListaCarta.get(0).getMaeFondo().getDFondo().trim());
                                r.setText(text, 0);
                         }  
                     
                         if (text != null && text.contains("$NRODOCORIGEN")) {
                                text = text.replace("$NRODOCORIGEN",oListaCarta.get(0).getSNro_ruc().trim());
                                r.setText(text, 0);
                         }
                         
                         if (text != null && text.contains("$DIRORI")) {
                                text = text.replace("$DIRORI",oListaCarta.get(0).getSDireccionOrigen().trim());
                                r.setText(text, 0);
                         }
                         
                         if (text != null && text.contains("$CTA")) {
                             
                                text = text.replace("$CTA",oListaCarta.get(0).getNRcuenta().trim());
                                r.setText(text, 0);
                         }
                         
                      
                         if (text != null && text.contains("$BCO")) {
                                text = text.replace("$BCO",oListaCarta.get(0).getSNombanco().trim());
                                r.setText(text, 0);
                         }
                         
                          if (text != null && text.contains("$DIRDES")) {
                                text = text.replace("$DIRDES",oListaCarta.get(0).getSDireccionDestino().trim());
                                r.setText(text, 0);
                         }
                    }
                    
                }
            }
        }
        
        // 2) Prepare Pdf options 
        //https://docs.oracle.com/javase/8/docs/technotes/guides/intl/encoding.doc.html
        PdfOptions options = PdfOptions.create().fontEncoding("windows-1252");
        
        //System.out.println("uuid = " + uuid);

        try {
            //name = maeInversion.getCcodigoIdent().trim() + "_PL24H_" + uuid + ".pdf";
            name = oMaeInversion.getSNombreArchivo();
           
            ruta = "C:\\pop\\webcustodia\\files\\document\\legal\\" + oMaeInversion.getMaeFondo().getCFondoId().trim() + "\\" + oMaeInversion.getCInversion().trim() + "\\" + name;
            // 3) Convert XWPFDocument to Pdf
            File fileO = new File(ruta);
            if (!fileO.exists()){
             fileO.getParentFile().mkdirs();
            }
            OutputStream out = new FileOutputStream(fileO);
            //PdfConverter.getInstance().convert(document, out, options);
            document.write( new FileOutputStream(fileO));
            out.close();
            //
           
           respGen= 0;
        } catch (XWPFConverterException | IOException e) {
            is.close();
            System.out.println("pop.webcobranzas.archivo.SessionArcDocumento.genInvNegociacion() - " + e.getMessage());
            respGen=-1;
        }
        
        
        is.close();
        }else{
           respGen= -2;
        }
        return respGen;

    }

    @Override
    public Integer generaConstancia(MaeInversion oMaeInversion) throws Exception {
        Locale.setDefault(new Locale("es", "ES"));
        String newline = "\n";
        int respGen=0;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatterDia = new SimpleDateFormat("dd");
        SimpleDateFormat formatterMes = new SimpleDateFormat("MMMM");
        SimpleDateFormat formatterAnio = new SimpleDateFormat("yyyy");
    
        String ruta;
        //String name;
        //fecha = formatter.parse("07/04/2017");
        // 1) Load DOCX into XWPFDocument;
        System.out.println("sistema 1222");
        System.out.println("A PUNTO DE GENERAR LA CONSTACIA, NECESITA DOS PARÁMETROS: ");
        System.out.println("MOVIMIENTO_CARTAS.PI_DOC_TIPO = "+oMaeInversion.getSTipoDoc());
        System.out.println("MOVIMIENTO_CARTAS.PI_DOC_NUMERO = "+oMaeInversion.getNDocNumero()+"     "+oMaeInversion.getnDocumento());

        
        
        MaeInversion newMaeInversion =  ofDao.getGeneraDocumentoDao().fetchGeneraConstancia(oMaeInversion);
        System.out.println("LA ISTA DEBE EXISTIR: "+newMaeInversion.getListaDocPlantilla());
        List<DocPlantilla> listax=newMaeInversion.getListaDocPlantilla();
        for(DocPlantilla d:listax){
            System.out.println("d.getTabla() = "+d.getTabla());
            System.out.println("d.getTabla_item() = "+d.getTabla_item());
            System.out.println("d.getIndice() = "+d.getIndice());
            System.out.println("d.getConcepto() = "+d.getConcepto());
            System.out.println("d.getValor() = "+d.getValor());
            System.out.println("-------------------------------------------------------------------");
        }
        String lscarta="";
        System.out.println("GENERA CONSTANCIA DE CANCELACION "+ newMaeInversion.getListaDocPlantilla().size());
        System.out.println("MOVIMIENTO_CARTAS.PI_DOC_NUMERO = "+oMaeInversion.getNDocNumero()+"     "+oMaeInversion.getnDocumento());
      if (newMaeInversion.getListaDocPlantilla().size()>=1){
          System.out.println("============================");
          System.out.println("============================");
          System.out.println("============================");
         System.out.println("newMaeInversion.getSNombreFormato() : "+newMaeInversion.getSNombreFormato());
               System.out.println("============================");
               System.out.println("============================");
               System.out.println("============================");
        InputStream is = new FileInputStream("C:\\pop\\webcustodia\\resources\\template\\constancia\\"+newMaeInversion.getSNombreFormato());
        System.out.println("C:\\pop\\webcustodia\\resources\\template\\constancia\\"+newMaeInversion.getSNombreFormato());
        XWPFDocument document = new XWPFDocument(is);
        System.out.println("caso1");
        // dato sueltos
        for (XWPFParagraph p : document.getParagraphs()) {
            
            List<XWPFRun> runs = p.getRuns(); 
            if (runs != null) {
                for (XWPFRun r : runs) {
                    String text = r.getText(0);
                    
                    
                    for(DocPlantilla dp : newMaeInversion.getListaDocPlantilla()){
                        if (text != null && text.contains(dp.getConcepto())) {
                            text = text.replace(dp.getConcepto(),dp.getValor());
                            r.setText(text, 0);
                        }                        
                    }                    
                }
            }
        }
        System.out.println("caso2");
        
        for(XWPFFooter footer: document.getFooterList()){

            for (XWPFParagraph p : footer.getParagraphs()) {

                List<XWPFRun> runs = p.getRuns(); 
                if (runs != null) {
                    for (XWPFRun r : runs) {

                        String text = r.getText(0);


                        for(DocPlantilla dp : newMaeInversion.getListaDocPlantilla()){
                            if (text != null && text.contains(dp.getConcepto())) {
                                text = text.replace(dp.getConcepto(),dp.getValor());
                                r.setText(text, 0);
                            }                        
                        }                    
                    }
                }
            }
        }
        
        System.out.println("caso3");
        // 2) Prepare Pdf options 
        //https://docs.oracle.com/javase/8/docs/technotes/guides/intl/encoding.doc.html
        PdfOptions options = PdfOptions.create().fontEncoding("windows-1252");
        
        //System.out.println("uuid = " + uuid);

        try {
            //name = maeInversion.getCcodigoIdent().trim() + "_PL24H_" + uuid + ".pdf";
            //name = newMaeInversion.getNDocNumero()+".docx";
           System.out.println("caso4");
            ruta = "C:\\pop\\webcustodia\\files\\document\\constancia\\" + oMaeInversion.getMaeFondo().getCFondoId().trim() + "\\" + oMaeInversion.getCInversion().trim() + "\\" + newMaeInversion.getSNombreArchivo();
            // 3) Convert XWPFDocument to Pdf
            File fileO = new File(ruta);
            System.out.println("caso5");
            if (!fileO.exists()){
             fileO.getParentFile().mkdirs();
            }
            System.out.println("caso6");
            OutputStream out = new FileOutputStream(fileO);
            //PdfConverter.getInstance().convert(document, out, options);
            System.out.println("caso7");
            document.write( new FileOutputStream(fileO));
            out.close();
            //
           System.out.println("caso8");
           respGen= 0;
        } catch (XWPFConverterException | IOException e) {
            is.close();
            System.out.println("pop.webcobranzas.archivo.SessionArcDocumento.genInvNegociacion() - " + e.getMessage());
            respGen=-1;
        }
        
        
        is.close();
        }else{
           respGen= -2;
        }
        return respGen;

    }
    
    
    @Override
    public List<MaeInversion> listarHistoricoCarta(MaeInversion oMaeInversion) throws Exception {
        List<MaeInversion> oInvList = null;
        oInvList = ofDao.getGeneraDocumentoDao().listarHistoricoCarta(oMaeInversion);
        return oInvList;
    }
    

    @Override
    public List<MaeInversion> listarCartas(MaeInversion oMaeInversion) throws Exception {
        List<MaeInversion> oInvList = null;
        oInvList = ofDao.getGeneraDocumentoDao().fetchlistarCartas(oMaeInversion);
        return oInvList;
    }
    
    @Override
    public List<MaeInversion> listarConstancias(MaeInversion oMaeInversion) throws Exception {
        List<MaeInversion> oInvList = null;
        oInvList = ofDao.getGeneraDocumentoDao().fetchlistarConstancias(oMaeInversion);
        return oInvList;
    }
    
    @Override
    public Integer notificarXEmail(MaeInversion oMaeInversion) throws Exception {
        return ofDao.getGeneraDocumentoDao().notificarXEmail(oMaeInversion);
    }

    @Override
    public ArrayList<MaeEmail> fetchAll() throws Exception {
        return ofDao.getGeneraDocumentoDao().fetchAll();
    }
    
}
