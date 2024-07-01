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
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import javax.ejb.Stateless;
import oracle.jdbc.OracleConnection;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPicture;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import pop.comun.dominio.MaeDocOtros;
import pop.comun.dominio.MaeEmail;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MaePersonaInmueble;
import pop.comun.dominio.MaeReporte;
import pop.comun.dominio.TabArchivo;

import pop.webcustodia.conn.Conexion;
import pop.webcustodia.dao.FactoryDao;
import pop.webcustodia.negocio.INegInversion;
import pop.webcustodia.reporte.HeaderFooter;

/**
 *
 * @author Jyoverar
 */
@Stateless(mappedName = "ejbInversion")
public class SessionInversion implements INegInversion {

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
    public List<MaeInversion> listarDeudorMov(MaeInversion oMaeInversion) throws Exception {
        List<MaeInversion> oInvList = null;

        oInvList = ofDao.getMaeInversionDao().fetchDebtorsMovi(oMaeInversion);
        return oInvList;

    }
    
    @Override
    public List<MaeInversion> listarDeudorf2(MaeInversion oMaeInversion) throws Exception {
        List<MaeInversion> oInvList = null;

        oInvList = ofDao.getMaeInversionDao().fetchDebtorsf2(oMaeInversion);
        return oInvList;
    }
    
    
    @Override
    public List<MaeInversion> listarDeudor(MaeInversion oMaeInversion) throws Exception {
        List<MaeInversion> oInvList = null;

        oInvList = ofDao.getMaeInversionDao().fetchDebtors(oMaeInversion);
        return oInvList;
    }
    
    @Override
    public List<MaeInversion> listarDeudorCons(MaeInversion oMaeInversion) throws Exception {
        List<MaeInversion> oInvList = null;

        oInvList = ofDao.getMaeInversionDao().fetchDebtorsCons(oMaeInversion);
        return oInvList;
    }

    @Override
    public List<MaeInversion> listarCustodia(MaeInversion oMaeInversion) throws Exception {
        List<MaeInversion> oInvList = null;
        oInvList = ofDao.getMaeInversionDao().fetchCustodia(oMaeInversion);
        return oInvList;
    }

    @Override
    public List<MaeInversion> listarConsultaxDoc(MaeInversion oMaeInversion) throws Exception {
        List<MaeInversion> oInvList = null;
        oInvList = ofDao.getMaeInversionDao().fetchConsultaxDoc(oMaeInversion);
        return oInvList;
    }

    
    @Override
    public List<MaeInversion> listarCustodiaf2(MaeInversion oMaeInversion) throws Exception {
        List<MaeInversion> oInvList = null;
        oInvList = ofDao.getMaeInversionDao().fetchCustodiaf2(oMaeInversion);
        return oInvList;
    }

    @Override
    public List<MaeInversion> listarDetalleCustodia(MaeInversion oMaeInversion) throws Exception {
        List<MaeInversion> oInvList = null;
        oInvList = ofDao.getMaeInversionDao().fetchDetalleCustodia(oMaeInversion);
        return oInvList;
    }
    
    @Override
    public List<MaeInversion> listarCustodiaMovi(MaeInversion oMaeInversion) throws Exception {
        List<MaeInversion> oInvList = null;
        oInvList = ofDao.getMaeInversionDao().fetchCustodiaMovi(oMaeInversion);
        return oInvList;
    }
    
     
    
    @Override
    public List<MaeInversion> listarCustodiaxDoc(MaeInversion oMaeInversion) throws Exception {
        List<MaeInversion> oInvList = null;
        oInvList = ofDao.getMaeInversionDao().fetchCustodiaxDoc(oMaeInversion);
        return oInvList;
    }
    
    @Override
    public List<MaeInversion> listaSolicitante(String cArea) throws Exception {
        List<MaeInversion> oInvList = null;
        oInvList = ofDao.getMaeInversionDao().fetchListaSolicitante(cArea);
        return oInvList;
    }
    
    
    @Override
    public Integer insertarCustodia(MaeInversion oMaeInversion) throws Exception {
        Integer Rspta = 0;
        Rspta = ofDao.getMaeInversionDao().InsCustodia(oMaeInversion);
        return Rspta;
    }

    @Override
    public Integer validaCorreo(MaeInversion oMaeInversion) throws Exception {
        Integer Rspta = 0;
        Rspta = ofDao.getMaeInversionDao().validaCorreo(oMaeInversion);
        return Rspta;
    }

    
    @Override
    public Integer insertarCustodiaf2(MaeInversion oMaeInversion) throws Exception {
        Integer Rspta = 0;
        Rspta = ofDao.getMaeInversionDao().InsCustodiaf2(oMaeInversion);
        return Rspta;
    }
    
    
    
    @Override
    public Integer eliminarDocCustodia(MaeInversion oMaeInversion) throws Exception {
        Integer Rspta = 0;
        Rspta = ofDao.getMaeInversionDao().DelDocCustodia(oMaeInversion);
        return Rspta;
    }


    @Override
    public Integer insertarCustodiaMov(MaeInversion oMaeInversion) throws Exception {
        Integer Rspta = 0;
        Rspta = ofDao.getMaeInversionDao().InsCustodiaMov(oMaeInversion);
        return Rspta;
    }

    
    @Override
    public Integer deleteCustodia(String pFondo, String pInv) throws Exception {
        Integer Rspta = 0;
        Rspta = ofDao.getMaeInversionDao().DelCustodia(pFondo,pInv);
        return Rspta;
    }
    

    @Override
    public List<MaeEmail> ListarEmail() throws Exception {
        List<MaeEmail> oInvEmail = null; 
        oInvEmail = ofDao.getMaeInversionDao().fetchEmail();
        return oInvEmail;
    }

    @Override
    public String imprimeMOV(MaeInversion oMaeInversion) throws FileNotFoundException, DocumentException, IOException {
        System.out.println("imprimeMOV() ");
       MaeReporte oMaeReporte = new  MaeReporte();
       oMaeReporte.setUserName(oMaeInversion.getcUsuarioAdd());
        // para los numeros 
        Locale.setDefault(new Locale("en", "US"));
        // configurando la pagina
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        String ruta;
        String uuid;
        uuid = oMaeInversion.getCInversion().trim() + "_" + UUID.randomUUID().toString() + ".pdf";
        //ruta = "E:\\webcobranzas\\files\\debit_balance\\" + localDate.getYear()
        ruta = "C:\\pop\\webcustodia\\files\\" + localDate.getYear()+"\\"
                 + uuid;
        // El OutPutStream para el fichero donde crearemos el PDF
        FileOutputStream ficheroPDF = new FileOutputStream(ruta);
        Document   document;
          DecimalFormat formatterNum = new DecimalFormat("###,###,##0.00");
          DecimalFormat formatterNum1 = new DecimalFormat("##0");
          SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        
        List<MaeInversion> oInversionNEW = null; 
        oInversionNEW = null;
        if (oMaeInversion.getsTipOpera().equals("0001") ){
                document = new Document(PageSize.A4 , 18   , 36, 210, 16);
           
                oInversionNEW =  ofDao.getMaeInversionDao().fetchMovDocumentos(oMaeInversion);
                
                PdfWriter writer = PdfWriter.getInstance(document, ficheroPDF);
                
               if ( oInversionNEW.size()>0 ){
        
                // obteniendo las fuentes
                String fontCalibriPath = Paths.get("/pop/webcobranzas/resources/font", "calibri.ttf").toString();
                String fontCalibriPathB = Paths.get("/pop/webcobranzas/resources/font", "calibrib.ttf").toString();
                BaseFont bf = BaseFont.createFont(fontCalibriPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                BaseFont bfb = BaseFont.createFont(fontCalibriPathB, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

                Font font;
                Font fontB;
                BaseColor bColor = new BaseColor(28, 50, 93);
                BaseColor bColorBorde;
         
                // celda universal
                PdfPCell cell;
                
                PdfPTable table = new PdfPTable(3);
                table.setTotalWidth(500);
                table.setWidths(new float[]{50, 10, 40});

                
                HeaderFooter headerFooter = new HeaderFooter(table);
            
                oMaeReporte.setNameLogo("logosafi.png");
               
                // datos del reporte
                //oMaeReporte.setNameReport("  ESTADO DE CUENTA A LA FECHA " + formatter.format(oMaeReporte.getfIniBusq()));
                oMaeReporte.setNameReport("");
                //headerFooter.setNameReport("SALDO DEUDOR DEL " + formatter.format(oSaldoDeudor.getFactual()) + "  AL " + formatter.format(oSaldoDeudor.getFfutura()));
                //headerFooter.setFecha(formatter.format(oMaeReporte.getfIniBusq()));
                //headerFooter.setUserName(oMaeReporte.getcUsuarioAdd());
                headerFooter.setMaeReporte(oMaeReporte);

                // aumentando la cabecera y detalle
                writer.setPageEvent(headerFooter);
                
                // Image image1 = Image.getInstance("..\\pop\\webcustodia\\resources\\jpg\\logoemprendedor.png");
                // document.add(image1);

                document.open();

                PdfPTable tableDetalle = new PdfPTable(5);
                tableDetalle.setWidthPercentage(90);
                tableDetalle.setWidths(new float[]{20,20,20, 20, 20});

                font = new Font(bf, 9, Font.BOLD, BaseColor.BLACK);
                bColor = new BaseColor(28, 50, 93);
                bColorBorde = new BaseColor(28, 50, 93);
                cell = new PdfPCell(new Paragraph(11, "Area Legal - Custodia", font));
                
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                cell = new PdfPCell(new Paragraph(11, "", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                
                cell = new PdfPCell(new Paragraph(11, "", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                
                font = new Font(bf, 10, Font.BOLD, BaseColor.BLACK);
                cell = new PdfPCell(new Paragraph(11, "Fecha de Registro:", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                
                Date date1 = new Date();
                System.out.println("probando"+oInversionNEW.get(0).getFRegistro());
                font = new Font(bf, 10, Font.NORMAL, BaseColor.BLACK);
                
                cell = new PdfPCell(new Paragraph(11, formatter.format(oInversionNEW.get(0).getFRegistro()), font));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(0);
                tableDetalle.addCell(cell);

                
                
                font = new Font(bf, 9, Font.NORMAL, BaseColor.BLACK);
                bColor = new BaseColor(28, 50, 93);
                bColorBorde = new BaseColor(28, 50, 93);
                cell = new PdfPCell(new Paragraph(11, "", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                cell = new PdfPCell(new Paragraph(11, "", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                
                cell = new PdfPCell(new Paragraph(11, "", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                
                cell = new PdfPCell(new Paragraph(11, "", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                
                
                cell = new PdfPCell(new Paragraph(11, "", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);

                document.add(tableDetalle);
                document.add(new Paragraph(" ", font));
 
               // ---
                 
                tableDetalle = new PdfPTable(5);
                tableDetalle.setWidthPercentage(90);
                tableDetalle.setWidths(new float[]{20,10,40, 10, 20});

                font = new Font(bf, 9, Font.NORMAL, BaseColor.BLACK);
                bColor = new BaseColor(28, 50, 93);
                bColorBorde = new BaseColor(28, 50, 93);
                cell = new PdfPCell(new Paragraph(11, "", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                
                cell = new PdfPCell(new Paragraph(11, "", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
               
                font = new Font(bf, 10, Font.BOLD, BaseColor.BLACK);
                cell = new PdfPCell(new Paragraph(11, "Orden de Ingreso N° " + oInversionNEW.get(0).getNDocumento().toString() , font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                
                cell = new PdfPCell(new Paragraph(11, "", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                
                
                cell = new PdfPCell(new Paragraph(11, "", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);

                
                document.add(tableDetalle);
                document.add(new Paragraph(" ", font));
 
                
                 
                tableDetalle = new PdfPTable(5);
                tableDetalle.setWidthPercentage(90);
                tableDetalle.setWidths(new float[]{20,20,20, 20, 20});

                
                font = new Font(bf, 9, Font.BOLD, BaseColor.BLACK);
                cell = new PdfPCell(new Paragraph(11, "Area Solicitante:", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                //
                
                
                font = new Font(bf, 10, Font.NORMAL, BaseColor.BLACK);
                
                cell = new PdfPCell(new Paragraph(11,oInversionNEW.get(0).getDesDestino(), font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                
                cell = new PdfPCell(new Paragraph(11, "", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                
                font = new Font(bf, 10, Font.BOLD, BaseColor.BLACK);
                cell = new PdfPCell(new Paragraph(11, "Solicitante:", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);

                font = new Font(bf, 10, Font.NORMAL, BaseColor.BLACK);
                
                cell = new PdfPCell(new Paragraph(11,oInversionNEW.get(0).getSSolicitante(), font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);

                
                
                
                font = new Font(bf, 9, Font.BOLD, BaseColor.BLACK);
                cell = new PdfPCell(new Paragraph(11, "F.Devolución:", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                //
                
                font = new Font(bf, 10, Font.NORMAL, BaseColor.BLACK);
                
                cell = new PdfPCell(new Paragraph(11, formatter.format(oInversionNEW.get(0).getDFechaIng()), font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                
                cell = new PdfPCell(new Paragraph(11, "", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                
                font = new Font(bf, 10, Font.BOLD, BaseColor.BLACK);
                cell = new PdfPCell(new Paragraph(12, oInversionNEW.get(0).getCInversion() , font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);

                font = new Font(bf, 10, Font.NORMAL, BaseColor.BLACK);
                
                cell = new PdfPCell(new Paragraph(11,"", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                

                document.add(new Paragraph(" ", font));
                

//
                
                font = new Font(bf, 9, Font.BOLD, BaseColor.BLACK);
                cell = new PdfPCell(new Paragraph(11, "Doc. Prestado : ", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                //
                cell = new PdfPCell(new Paragraph(11,"", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                //
            
                System.out.println("paso 4");
                cell = new PdfPCell(new Paragraph(11, "", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);

                cell = new PdfPCell(new Paragraph(11, "", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);

                cell = new PdfPCell(new Paragraph(11, "", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);

                document.add(tableDetalle);
                
                
                 
                tableDetalle = new PdfPTable(2);
                tableDetalle.setWidthPercentage(90);
                tableDetalle.setWidths(new float[]{60,40});

                 
                for (MaeInversion mc : oInversionNEW) {
                
                    font = new Font(bf, 10, Font.NORMAL, BaseColor.BLACK);
                
                    cell = new PdfPCell(new Paragraph(12, "" + mc.getDescripcion(), font));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(0);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    tableDetalle.addCell(cell);
                  
                    cell = new PdfPCell(new Paragraph(12, "" , font));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(0);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    tableDetalle.addCell(cell);
                }
                
                
                
                document.add(tableDetalle);
                document.add(new Paragraph(" ", font));
                document.add(new Paragraph(" ", font));

                //------------------------------------------------- detalle
                tableDetalle = new PdfPTable(2);
                tableDetalle.setWidthPercentage(90);
                tableDetalle.setWidths(new float[]{30, 70});
                // a favor del fondo
                font = new Font(bf, 10, Font.NORMAL, BaseColor.BLACK);
                
                cell = new PdfPCell(new Paragraph(12, "______________________", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                //
        

                
                cell = new PdfPCell(new Paragraph(12, " ", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);

                
                
                cell = new PdfPCell(new Paragraph(12, "SOLICITANTE", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                //

                cell = new PdfPCell(new Paragraph(12, " ", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                
                document.add(tableDetalle);
                document.add(new Paragraph(" ", font));
                document.add(new Paragraph(" ", font));
                document.add(new Paragraph(" ", font));

                
                tableDetalle = new PdfPTable(5);
                tableDetalle.setWidthPercentage(90);
                tableDetalle.setWidths(new float[]{25,20, 10,25,20});
             
                font = new Font(bf, 9, Font.BOLD, BaseColor.BLACK);
                bColor = new BaseColor(28, 50, 93);
                bColorBorde = new BaseColor(28, 50, 93);
                cell = new PdfPCell(new Paragraph(11, "Responsable Custodia:", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                font = new Font(bf, 10, Font.NORMAL, BaseColor.BLACK);
              
                cell = new PdfPCell(new Paragraph(11,oInversionNEW.get(0).getJefeCustodia(), font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
               
                font = new Font(bf, 10, Font.BOLD, BaseColor.BLACK);
                cell = new PdfPCell(new Paragraph(11, "" , font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                
                cell = new PdfPCell(new Paragraph(11,"Jefe Area Legal:", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                
                font = new Font(bf, 10, Font.NORMAL, BaseColor.BLACK);
                cell = new PdfPCell(new Paragraph(11, oInversionNEW.get(0).getJefeLegal(), font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                
                document.add(tableDetalle);
                document.add(new Paragraph(" ", font));
               }
        }else{      // formato antiguo
            
            oInversionNEW =  ofDao.getMaeInversionDao().fetchMovDocumentos(oMaeInversion);
                document = new Document(PageSize.A4, 36, 36, 170, 36);
                PdfWriter writer = PdfWriter.getInstance(document, ficheroPDF);
               
             if ( oInversionNEW.size()>0 ){
                // obteniendo las fuentes
                String fontCalibriPath = Paths.get("/pop/webcobranzas/resources/font", "calibri.ttf").toString();
                String fontCalibriPathB = Paths.get("/pop/webcobranzas/resources/font", "calibrib.ttf").toString();
           
                BaseFont bf = BaseFont.createFont(fontCalibriPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                BaseFont bfb = BaseFont.createFont(fontCalibriPathB, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

                Font font;
                Font fontB;
                BaseColor bColor = new BaseColor(28, 50, 93);
                BaseColor bColorBorde;
         
                // celda universal
                PdfPCell cell;
                
                PdfPTable table = new PdfPTable(3);
                table.setTotalWidth(500);
                table.setWidths(new float[]{50, 10, 40});

                
                HeaderFooter headerFooter = new HeaderFooter(table);
            
                oMaeReporte.setNameLogo("logosafi.png");
               
                // datos del reporte
                //oMaeReporte.setNameReport("  ESTADO DE CUENTA A LA FECHA " + formatter.format(oMaeReporte.getfIniBusq()));
                oMaeReporte.setNameReport("");
                //headerFooter.setNameReport("SALDO DEUDOR DEL " + formatter.format(oSaldoDeudor.getFactual()) + "  AL " + formatter.format(oSaldoDeudor.getFfutura()));
                //headerFooter.setFecha(formatter.format(oMaeReporte.getfIniBusq()));
                //headerFooter.setUserName(oMaeReporte.getcUsuarioAdd());
                headerFooter.setMaeReporte(oMaeReporte);

                // aumentando la cabecera y detalle
                writer.setPageEvent(headerFooter);
                
                // Image image1 = Image.getInstance("..\\pop\\webcustodia\\resources\\jpg\\logoemprendedor.png");
                // document.add(image1);

                document.open();

                PdfPTable tableDetalle = new PdfPTable(5);
                tableDetalle.setWidthPercentage(90);
                tableDetalle.setWidths(new float[]{20,20,20, 20, 20});

                font = new Font(bf, 9, Font.BOLD, BaseColor.BLACK);
                bColor = new BaseColor(28, 50, 93);
                bColorBorde = new BaseColor(28, 50, 93);
                cell = new PdfPCell(new Paragraph(11, "Area Legal - Custodia", font));
                
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                cell = new PdfPCell(new Paragraph(11, "", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                
                cell = new PdfPCell(new Paragraph(11, "", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                
                font = new Font(bf, 10, Font.BOLD, BaseColor.BLACK);
                cell = new PdfPCell(new Paragraph(11, "Fecha de Registro:", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                
                Date date1 = new Date();
                font = new Font(bf, 10, Font.NORMAL, BaseColor.BLACK);
                
                cell = new PdfPCell(new Paragraph(11, formatter.format(oInversionNEW.get(0).getFRegistro()), font));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(0);
                tableDetalle.addCell(cell);

                
                
                font = new Font(bf, 9, Font.NORMAL, BaseColor.BLACK);
                bColor = new BaseColor(28, 50, 93);
                bColorBorde = new BaseColor(28, 50, 93);
                cell = new PdfPCell(new Paragraph(11, "", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                cell = new PdfPCell(new Paragraph(11, "", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                
                cell = new PdfPCell(new Paragraph(11, "", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                
                cell = new PdfPCell(new Paragraph(11, "", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                
                
                cell = new PdfPCell(new Paragraph(11, "", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);

                document.add(tableDetalle);
                document.add(new Paragraph(" ", font));
 
               // ---
                 
                tableDetalle = new PdfPTable(5);
                tableDetalle.setWidthPercentage(90);
                tableDetalle.setWidths(new float[]{20,10,40, 10, 20});

                font = new Font(bf, 9, Font.NORMAL, BaseColor.BLACK);
                bColor = new BaseColor(28, 50, 93);
                bColorBorde = new BaseColor(28, 50, 93);
                cell = new PdfPCell(new Paragraph(11, "", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                
                cell = new PdfPCell(new Paragraph(11, "", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
               
                font = new Font(bf, 10, Font.BOLD, BaseColor.BLACK);
                cell = new PdfPCell(new Paragraph(11, "Orden de Salida N° " + oInversionNEW.get(0).getNDocumento().toString() , font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                
                cell = new PdfPCell(new Paragraph(11, "", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                
                
                cell = new PdfPCell(new Paragraph(11, "", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);

                
                document.add(tableDetalle);
                document.add(new Paragraph(" ", font));
 
                
                 
                tableDetalle = new PdfPTable(5);
                tableDetalle.setWidthPercentage(90);
                tableDetalle.setWidths(new float[]{20,20,20, 20, 20});

                
               font = new Font(bf, 10, Font.BOLD, BaseColor.BLACK);
                
               cell = new PdfPCell(new Paragraph(11, "Fecha Salida:", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                //
                
               
                font = new Font(bf, 10, Font.NORMAL, BaseColor.BLACK);
                
                cell = new PdfPCell(new Paragraph(11,formatter.format(oInversionNEW.get(0).getDFechaSal()).trim(), font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                //
             
                cell = new PdfPCell(new Paragraph(11, "", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                
                
                font = new Font(bf, 9, Font.BOLD, BaseColor.BLACK);
                cell = new PdfPCell(new Paragraph(11,oInversionNEW.get(0).getCInversion() , font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);

                cell = new PdfPCell(new Paragraph(11, "", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);

                
                                
                font = new Font(bf, 9, Font.BOLD, BaseColor.BLACK);
                cell = new PdfPCell(new Paragraph(11, "Area Solicitante:", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                // 
                
                font = new Font(bf, 10, Font.NORMAL, BaseColor.BLACK);
                
                cell = new PdfPCell(new Paragraph(11,oInversionNEW.get(0).getDesDestino(), font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                //
            
                cell = new PdfPCell(new Paragraph(11, "", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                
                font = new Font(bf, 10, Font.BOLD, BaseColor.BLACK);
                cell = new PdfPCell(new Paragraph(11, "Solicitante:", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);

                font = new Font(bf, 10, Font.NORMAL, BaseColor.BLACK);
                
                cell = new PdfPCell(new Paragraph(11,oInversionNEW.get(0).getSSolicitante(), font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);

                 ///----
                 
                 
                 
                                
                font = new Font(bf, 9, Font.BOLD, BaseColor.BLACK);
                cell = new PdfPCell(new Paragraph(11, "Días de préstamo:", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                
                
                font = new Font(bf, 10, Font.NORMAL, BaseColor.BLACK);
                
                cell = new PdfPCell(new Paragraph(11,formatterNum1.format(oInversionNEW.get(0).getNDias()), font));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                
                
                cell = new PdfPCell(new Paragraph(11, "", font));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(0);
                tableDetalle.addCell(cell);

                font = new Font(bf, 10, Font.BOLD, BaseColor.BLACK);
                
                cell = new PdfPCell(new Paragraph(11, "Fecha Est.Devolucion:", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);

                font = new Font(bf, 10, Font.NORMAL, BaseColor.BLACK);
                
                cell = new PdfPCell(new Paragraph(11,formatter.format(oInversionNEW.get(0).getDFechaIng()), font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                
                ///-----
                
                
                
                
                font = new Font(bf, 9, Font.BOLD, BaseColor.BLACK);
                cell = new PdfPCell(new Paragraph(11, "Motivo: ", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                //
                font = new Font(bf, 10, Font.NORMAL, BaseColor.BLACK);
                
                cell = new PdfPCell(new Paragraph(11,oInversionNEW.get(0).getSMotivo(), font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                //
            
                cell = new PdfPCell(new Paragraph(11, "", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);

                cell = new PdfPCell(new Paragraph(11, "", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);

                cell = new PdfPCell(new Paragraph(11, "", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);

                
                ///---
                
                
                font = new Font(bf, 9, Font.BOLD, BaseColor.BLACK);
                cell = new PdfPCell(new Paragraph(11, "Doc. Prestado : ", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                //
                cell = new PdfPCell(new Paragraph(11,"", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                //
            
                cell = new PdfPCell(new Paragraph(11, "", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);

                cell = new PdfPCell(new Paragraph(11, "", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);

                cell = new PdfPCell(new Paragraph(11, "", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);

                document.add(tableDetalle);
                
                
                 
                tableDetalle = new PdfPTable(2);
                tableDetalle.setWidthPercentage(90);
                tableDetalle.setWidths(new float[]{60,40});

                 
                for (MaeInversion mc : oInversionNEW) {
                
                    font = new Font(bf, 10, Font.NORMAL, BaseColor.BLACK);
                
                    cell = new PdfPCell(new Paragraph(12, "" + mc.getDescripcion(), font));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(0);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    tableDetalle.addCell(cell);
                  
                    cell = new PdfPCell(new Paragraph(12, "" , font));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(0);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    tableDetalle.addCell(cell);
                }
                
                document.add(tableDetalle);
                document.add(new Paragraph(" ", font));
                document.add(new Paragraph(" ", font));

                //------------------------------------------------- detalle
                tableDetalle = new PdfPTable(2);
                tableDetalle.setWidthPercentage(90);
                tableDetalle.setWidths(new float[]{30, 70});
                // a favor del fondo
                font = new Font(bf, 10, Font.NORMAL, BaseColor.BLACK);
                
                cell = new PdfPCell(new Paragraph(12, "______________________", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                //
        
               
                cell = new PdfPCell(new Paragraph(12, " ", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);

                
                
                cell = new PdfPCell(new Paragraph(12, "SOLICITANTE", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                //
        

                cell = new PdfPCell(new Paragraph(12, " ", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                
                document.add(tableDetalle);
                document.add(new Paragraph(" ", font));
                document.add(new Paragraph(" ", font));
                document.add(new Paragraph(" ", font));

                
                tableDetalle = new PdfPTable(5);
                tableDetalle.setWidthPercentage(90);
                tableDetalle.setWidths(new float[]{25,20, 10,25,20});
             
                font = new Font(bf, 9, Font.BOLD, BaseColor.BLACK);
                bColor = new BaseColor(28, 50, 93);
                bColorBorde = new BaseColor(28, 50, 93);
                cell = new PdfPCell(new Paragraph(11, "Responsable Custodia:", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                font = new Font(bf, 10, Font.NORMAL, BaseColor.BLACK);
              
                cell = new PdfPCell(new Paragraph(11,oInversionNEW.get(0).getJefeCustodia(), font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
               
                font = new Font(bf, 10, Font.BOLD, BaseColor.BLACK);
                cell = new PdfPCell(new Paragraph(11, "" , font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                
                cell = new PdfPCell(new Paragraph(11,"Jefe Area Legal:", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                
                font = new Font(bf, 10, Font.NORMAL, BaseColor.BLACK);
                cell = new PdfPCell(new Paragraph(11, oInversionNEW.get(0).getJefeLegal(), font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                tableDetalle.addCell(cell);
                
                document.add(tableDetalle);
                document.add(new Paragraph(" ", font));
               
                
             }  

        //System.out.println("pop.webcobranzas.procesos.SessionSaldoDeudor.imprimirSaldoDeudor() f");
        }
        
         document.addAuthor(oMaeReporte.getUserName());
         
        document.addCreationDate();
        document.addCreator("popular-safi.com");
        document.addTitle("Custodia") ;
        
       
        document.close();
        
        return ruta;
        
    


    }

    
    
    private static void createTable(Section subCatPart)
            throws BadElementException {
        PdfPTable table = new PdfPTable(3);

        // t.setBorderColor(BaseColor.GRAY);
        // t.setPadding(4);
        // t.setSpacing(4);
        // t.setBorderWidth(1);

        PdfPCell c1 = new PdfPCell(new Phrase("Table Header 1"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Table Header 2"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Table Header 3"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);

        table.addCell("1.0");
        table.addCell("1.1");
        table.addCell("1.2");
        table.addCell("2.1");
        table.addCell("2.2");
        table.addCell("2.3");

        subCatPart.add(table);

    }

    
    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
    
    @Override
    public List<MaeInversion> listarDocumentosMovi(MaeInversion oMaeInversion) throws Exception {
           List<MaeInversion> oInvList = null;

        oInvList = ofDao.getMaeInversionDao().fetchMovDocumentos(oMaeInversion);
        return oInvList;
    }

    @Override
    public List<MaeInversion> listarConsultaxMov(MaeInversion oMaeInversion) throws Exception {
        List<MaeInversion> oInvList = null;
        oInvList = ofDao.getMaeInversionDao().fetchConsultaxMov(oMaeInversion);
        return oInvList;
    }

    @Override
    public Integer insertarDocCustodia(MaeDocOtros oMaeDocOtros) throws Exception {
        Integer Rspta = 0;
        Rspta = ofDao.getMaeInversionDao().InsDocCustodia(oMaeDocOtros);
        return Rspta;
    }   

        
    @Override
    public Integer grabarDocDetCustodia(MaeInversion oMaeInversion) throws Exception {
        Integer Rspta = 0;
        Rspta = ofDao.getMaeInversionDao().GrabarDocDetCustodia(oMaeInversion);
        return Rspta;
    }   
        
}
