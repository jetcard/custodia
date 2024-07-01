/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.ejb;

import fr.opensagres.poi.xwpf.converter.core.XWPFConverterException;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import javax.ejb.Stateless;
import org.apache.poi.xwpf.usermodel.IRunBody;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MovimientoProtesto;
import pop.webcustodia.dao.FactoryDao;
import pop.webcustodia.negocio.INegProtesto;

/**
 *
 * @author RC433838
 */
@Stateless(mappedName = "ejbProtesto")
public class SessionProtesto implements INegProtesto{
    
    FactoryDao ofDao = new FactoryDao();    

      
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public String removerProtesto(MovimientoProtesto oProtesto) throws Exception {
        String Rspta;
        Rspta = ofDao.getProtesto().remProtesto(oProtesto);
        return Rspta;
    }
    
    @Override
    public String removerRegistroBNB(MovimientoProtesto oProtesto) throws Exception {
        String Rspta;
        Rspta = ofDao.getProtesto().remRegistroBNB(oProtesto);
        return Rspta;
    }    

    @Override
    public String agregarProtesto(MovimientoProtesto oProtesto) throws Exception {
        String Rspta;
        Rspta = ofDao.getProtesto().addProtesto(oProtesto);
        return Rspta;
    }

    @Override
    public List<MaeInversion> todosInversiones(MaeInversion oInversion) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MovimientoProtesto> todosProtestos(MovimientoProtesto oProtesto) throws Exception {        
        List<MovimientoProtesto> lisProtesto;
        lisProtesto=ofDao.getProtesto().allProtestos(oProtesto);        
        return lisProtesto;
    }
    
    @Override
    public List<MovimientoProtesto> todosRegistroBnb(MovimientoProtesto oProtesto) throws Exception {
        List<MovimientoProtesto> lisProtesto;
        System.out.println("List<MovimientoProtesto> todosRegistroBnb(MovimientoProtesto oProtesto).");
        lisProtesto=ofDao.getProtesto().allRegistroBnb(oProtesto);
        return lisProtesto;
    }

    @Override
    public List<MovimientoProtesto> todosMovimientosBnb(MovimientoProtesto oProtesto) throws Exception {
        List<MovimientoProtesto> lisProtesto;
        System.out.println("List<MovimientoProtesto> todosRegistroBnb(MovimientoProtesto oProtesto).");
        lisProtesto=ofDao.getProtesto().allMovimientosBnb(oProtesto);
        return lisProtesto;
    }
    
    @Override
    public List<MovimientoProtesto> buscaInversion(MaeInversion oInversion) throws Exception {        
        List<MovimientoProtesto> lisProtesto;
        lisProtesto=ofDao.getProtesto().buscaInversion(oInversion);        
        return lisProtesto;
    }
    
    @Override
    public Integer exportarRegistroBNB(List<MovimientoProtesto> oProtesto) throws Exception {
        Locale.setDefault(new Locale("es", "ES"));
        String newline = "\n";
        int respGen=0;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatterDia = new SimpleDateFormat("dd");
        SimpleDateFormat formatterMes = new SimpleDateFormat("MMMM");
        SimpleDateFormat formatterAnio = new SimpleDateFormat("yyyy");
    
        String ruta;
        String name;
        Date fecha = null;
        
        // 1) Load DOCX into XWPFDocument;
        List<MaeInversion> oListaCarta = null; 
        String lscarta="CARTA_PLANTILLA.docx";
          
        InputStream is = new FileInputStream("C:\\pop\\webcustodia\\resources\\template\\cumplimiento\\"+lscarta);
        XWPFDocument document = new XWPFDocument(is);
     
        for (XWPFTable tbl : document.getTables()) {  

            for(MovimientoProtesto pro : oProtesto){
                //create second row
               XWPFTableRow tableRowTwo = tbl.createRow();
               tableRowTwo.getCell(0).setText(pro.getBnb().getNro_serie().toString());
               tableRowTwo.getCell(1).setText(pro.getBnb().getCodigo().toString().trim());
               tableRowTwo.getCell(2).setText(pro.getBnb().getNombre().trim());
               tableRowTwo.getCell(3).setText(pro.getBnb().getValor_nominal());
               tableRowTwo.getCell(4).setText(pro.getBnb().getTipo().substring(0,4).replace("(", "").trim());  
               
               fecha = pro.getBnb().getF_ingreso();
            }
        }

        
        for (XWPFParagraph p : document.getParagraphs()) {
            List<XWPFRun> runs = p.getRuns();
            if (runs != null) {
                for (XWPFRun r : runs) {
                    String text = r.getText(0);
                    
                    
                    
                    Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/Lima"));
                    cal.setTime(fecha);
                    int year = cal.get(Calendar.YEAR);
                    int month = cal.get(Calendar.MONTH);
                    String mes="";
                    int day = cal.get(Calendar.DAY_OF_MONTH);
                    
                   // System.out.println("--------------: "+day+"-"+month+"-"+year);

                    switch(month){
                        case 0: mes="enero"; break;
                        case 1: mes="febrero"; break;
                        case 2: mes="marzo"; break;
                        case 3: mes="abril"; break;
                        case 4: mes="mayo"; break;
                        case 5: mes="junio"; break;
                        case 6: mes="julio"; break;
                        case 7: mes="agosto"; break;
                        case 8: mes="setiembre"; break;
                        case 9: mes="octubre"; break;
                        case 10: mes="noviembre"; break;
                        case 11: mes="diciembre"; break;
                    }
                    
                    String fe = "Lima, "+day+" de "+mes+" del "+year;
                    
                    if (text != null && text.contains("$fecha")) {
                        text = text.replace("$fecha",fe);
                        r.setText(text, 0);                        
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
                       
            ruta = "C:\\pop\\webcustodia\\files\\document\\cumplimiento\\carta.docx" ;
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
       
        return respGen;
    }     



}
