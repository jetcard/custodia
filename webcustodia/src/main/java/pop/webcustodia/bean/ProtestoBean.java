/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.bean;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pop.comun.dominio.CobRequerimientoCartas;
import pop.comun.dominio.CobTablas;
import pop.comun.dominio.MaeFondo;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MaePersona;
import pop.comun.dominio.MovimientoProtesto;
import pop.webcustodia.filter.SessionUtils;
import pop.webcustodia.iface.IFondoServ;
import pop.webcustodia.iface.IProtestoServ;
import pop.webcustodia.iface.ITablasServ;
import pop.webcustodia.servicio.FondoServ;
import pop.webcustodia.servicio.ProtestoServ;
import pop.webcustodia.servicio.TablasServ;

/**
 *
 * @author RC433838
 */
@Named
@ViewScoped
public class ProtestoBean  implements Serializable {


    private IFondoServ fondoServ = new FondoServ();
    private ITablasServ tablasServ = new TablasServ();
    private IProtestoServ protestoServ = new ProtestoServ();

    private MaeFondo maeFondo = new MaeFondo();
    private List<MaeFondo> maeFondoList;        
    private MovimientoProtesto protesto = new MovimientoProtesto();
    private List<MovimientoProtesto> protestoList;
    
    private MaeInversion maeInversion = new MaeInversion();
    private MaePersona  maePersona = new MaePersona();
    
    
    private CobTablas cobTablasEstados = new CobTablas();  
    private List<CobTablas> listaEstados;
    private List<CobTablas> listaTipos;
    
    private String nombreCompleto;
    
    private String errorNewMod ="";
    private Integer n;
    
    private boolean isLoad = false;    

    /**
     * @return the n
     */
    public Integer getN() {
        return n;
    }

    /**
     * @param n the n to set
     */
    public void setN(Integer n) {
        this.n = n;
    }
        
    
    /**
     * Creates a new instance of ProtestoBean
     */
    public ProtestoBean() {
    }
    
    
    
    public void initTipoCons(LoginBean login, String title, String prev, String next){
            
        try {
     
            login.setPageTitle(title);
            getMaeInversion().setPagePrev(prev);
            getMaeInversion().setPageNext(next);            
            
        } catch (Exception e) {

        }
      
    }
    
    public void postLoad(){
        if(n != null){
           buscaProtesto(n);
        }
    }
    
    public void postLoadReq(){

        if(!isLoad){
            
            try {
                HttpSession ses = SessionUtils.getSession();
                CobRequerimientoCartas reqCart = SessionUtils.getRequerimiento();
                                
                protesto.setCodfondo(reqCart.getFondoId().trim());
                protesto.setDvalor_bv(reqCart.getDvalor_bv().trim());
                protesto.setReq_tipo(reqCart.getReqTipo());
                protesto.setReq_numero(reqCart.getReqNumero());
                                
                isLoad = true;
                ses.removeAttribute("oReqCar");
            } catch (Exception e) {
            }     
            
        }
    }     
    
    public void listarFondos() {
        System.out.println("ProtestoBean().listarFondos");
        try {
          
            if (getMaeFondoList() == null) {
                setMaeFondoList(getFondoServ().listarFondos(new MaeFondo()));
            }
            System.out.println("cantidad de Fondos -> " + getMaeFondoList().size());
        } catch (Exception e) {

        }
    }

    public void listarEstados(){
            
        try {
            
            getCobTablasEstados().setCtablaId("0012");
            getCobTablasEstados().setCtablaDetId(null);
            setListaEstados(getTablasServ().listarTablas(getCobTablasEstados()));
            
        } catch (Exception e) {

        }
      
    } 

    public void listarDocumento(){
            
        try {
            
            getCobTablasEstados().setCtablaId("0014");
            getCobTablasEstados().setCtablaDetId(null);
            listaEstados= getTablasServ().listarTablas(getCobTablasEstados());
            
        } catch (Exception e) {

        }
      
    }     
    
    public void listarTipoBNB(){
            
        try {
            
            getCobTablasEstados().setCtablaId("0015");
            getCobTablasEstados().setCtablaDetId(null);
            setListaTipos(getTablasServ().listarTablas(getCobTablasEstados()));
            
        } catch (Exception e) {

        }
      
    }      
    
    public void buscaProtesto(Number pDoc_numero) {
        System.out.println(":ProtestoBean().buscaProtesto(Number pDoc_numero)"+pDoc_numero);
        try {
          
            protesto.setMaeInversion(maeInversion);
            protesto.setDoc_tipo("PN");
            protesto.setDoc_numero(pDoc_numero);
            protesto=getProtestoServ().allProtestos(protesto).get(0);
            nombreCompleto = protesto.getMaeInversion().getcPersonaId().getNombresCompleto();
            
            System.out.println("<-- PARA MODIFICAR-> " );
        } catch (Exception e) {
        }
    }
    
    public void buscaInversion() {
        System.out.println(":ProtestoBean().buscaProtesto(Number pDoc_numero)");
        try {
            nombreCompleto = null;
            
            MovimientoProtesto movpro = new MovimientoProtesto();
            MaeFondo fondo = new MaeFondo();
            MaeInversion inv = new MaeInversion();
            
            fondo.setCFondoId(protesto.getCodfondo());
            inv.setMaeFondo(fondo);
            inv.setCInversion(protesto.getDvalor_bv());
            movpro=getProtestoServ().buscaInversion(inv).get(0);
            
            nombreCompleto = movpro.getMaeInversion().getcPersonaId().getNombresCompleto();
            protesto.setDvalor_bv(movpro.getDvalor_bv());
            
            /*
            maePersona.setDApePat(movpro.getMaeInversion().getcPersonaId().getDApePat());
            maePersona.setDApeMat(movpro.getMaeInversion().getcPersonaId().getDApeMat());
            maePersona.setDNombres(movpro.getMaeInversion().getcPersonaId().getDNombres());
            */
            
            System.out.println("<-- PARA MODIFICAR-> " );
        } catch (Exception e) {
        }
    }    
    
    public void listarProtestos() {
        System.out.println("::::ProtestoBean().listarProtestos");
        try {
            protesto.setDoc_tipo("PN");
            protesto.setMaeInversion(maeInversion);
            setProtestoList(getProtestoServ().allProtestos(protesto));
            
            System.out.println("cantidad de protestos -> " + getProtestoList().size());
        } catch (Exception e) {
        }
    }
    
    public void listarRegistrobnb() {
        System.out.println("::::ProtestoBean().listarProtestos");
        try {
            protesto.setDoc_tipo("CP");
            protesto.setMaeInversion(maeInversion);
            setProtestoList(getProtestoServ().allRegistroBNB(protesto));
            
            System.out.println("cantidad de bnb -> " + getProtestoList().size());
        } catch (Exception e) {
            System.out.println("ror "+e.getMessage());
        }
    }
    
    public void listarMovimientosbnb() {
        System.out.println("::::ProtestoBean().listarProtestos");
        try {
            protesto.setDoc_tipo("CP");
            protesto.setMaeInversion(maeInversion);
            setProtestoList(getProtestoServ().allMovimientosBNB(protesto));
            
            System.out.println("cantidad de bnb -> " + getProtestoList().size());
        } catch (Exception e) {
            System.out.println("ror "+e.getMessage());
        }
    }    
    
    public String grabarProtesto() {
        setErrorNewMod(null);
        
        try {
          
          String vdoc_num;
          protesto.setDoc_tipo("PN");          

          if(protesto.getCodfondo() == null ){
              setErrorNewMod("Ingrese el fondo de inversiones");              
            return null;
          }
                    
          if(protesto.getDvalor_bv() == null ){
              setErrorNewMod("Ingrese el código para continuar");              
            return null;
          }
          
          if(protesto.getFemision() == null ){
              setErrorNewMod("Falta fecha de emisión del protesto");              
            return null;
          }

          if(protesto.getEstado() == null ){
              setErrorNewMod("Falta seleccionar el estado");              
            return null;
          }
          
          if(nombreCompleto == null){
              setErrorNewMod("El codigo ingresado es incorrecto");              
            return null;
          }
          
          
            System.out.println("Creando nuevo protesto");
            protesto.setcUsuarioAdd(SessionUtils.getUserName());
            vdoc_num=getProtestoServ().addProtesto(protesto);
            
            if(vdoc_num.equals("EXITO")){
                return maeInversion.getPagePrev()+"?faces-redirect=true&n="+vdoc_num;
            }
            else{
                System.err.println("error: "+vdoc_num);
                if(vdoc_num.contains("MOVIMIENTO_PROTESTO_INDEX1")){
                    setErrorNewMod("Está duplicando el registro");              
                    return null;
                }else{
                    setErrorNewMod("No se guardaron los cambios");              
                    return null;
                }
            }
          
        } catch (Exception e) {
            System.err.println("error grabarProtesto(): "+e.getMessage());
        }
        
                
        return null;
    }   
    
    public String grabarBNB() {
        setErrorNewMod(null);
        
        try {
          
          String vdoc_num;
          protesto.setDoc_tipo("CP");

          if(protesto.getCodfondo() == null ){
              setErrorNewMod("Ingrese el fondo de inversiones");              
            return null;
          }
                    
          if(protesto.getDvalor_bv() == null ){
              setErrorNewMod("Ingrese el código para continuar");              
            return null;
          }
          
          if(protesto.getFemision() == null ){
              setErrorNewMod("Falta fecha de emisión");              
            return null;
          }
          
          if(protesto.getReq_tipo()== null ){
              setErrorNewMod("Falta seleccionar el tipo");
            return null;
          }          

          if(protesto.getEstado() == null ){
              setErrorNewMod("Falta seleccionar el documento");              
            return null;
          }
          
          if(nombreCompleto == null){
              setErrorNewMod("El codigo ingresado es incorrecto");              
            return null;
          }
          
          
            System.out.println("Creando nuevo bnb");
            protesto.setcUsuarioAdd(SessionUtils.getUserName());
            vdoc_num=getProtestoServ().addProtesto(protesto);
            
            if(vdoc_num.equals("EXITO")){
                return maeInversion.getPagePrev()+"?faces-redirect=true&n="+vdoc_num;
            }
            else{
                System.err.println("error: "+vdoc_num);
                if(vdoc_num.contains("MOVIMIENTO_PROTESTO_INDEX1")){
                    setErrorNewMod("Está duplicando el registro");              
                    return null;
                }else{
                    setErrorNewMod("No se guardaron los cambios");              
                    return null;
                }
            }
          
        } catch (Exception e) {
            System.err.println("error grabarProtesto(): "+e.getMessage());
        }
        
                
        return null;
    }       
    
    public String acciones(int opcion) {
        
        String url = null;
        
        try {
            switch(opcion) {
              case 1:
                    url = maeInversion.getPageNext()+"?faces-redirect=true";  
                break;
              case 2:
                for(MovimientoProtesto in: protestoList){
                    System.err.println("MaeInversion in: maeInversionList "+in.getDoc_numero()+"  "+in.isSelected());
                     in.setcUsuarioMod(SessionUtils.getUserName());                     
                    if(in.isSelected()){
                        url = maeInversion.getPageNext()+"?faces-redirect=true&includeViewParams=true&n="+in.getDoc_numero();
                    }
                }
                break;
              case 3:  
                for(MovimientoProtesto in: protestoList){
                    //System.err.println("MaeInversion in: maeInversionList "+in.getNDocNumero()+"  "+in.isSelected());
                     
                    if(in.isSelected()){   
                        MovimientoProtesto movpro = in;
                        movpro.setDoc_numero(in.getDoc_numero());
                        movpro.setcUsuarioMod(SessionUtils.getUserName());
                        getProtestoServ().remProtesto(movpro);                
                    }
                }
                
                listarProtestos();
                
                            
                break; 
              case 4:  
                for(MovimientoProtesto in: protestoList){
                    //System.err.println("MaeInversion in: maeInversionList "+in.getNDocNumero()+"  "+in.isSelected());
                     
                    if(in.isSelected()){   
                        MovimientoProtesto movpro = in;
                        movpro.setDoc_numero(in.getBnb().getDoc_numero());
                        movpro.setcUsuarioMod(SessionUtils.getUserName());
                        getProtestoServ().remRegistroBNB(movpro);                
                    }
                }
                
                listarRegistrobnb();
                
                            
                break;      
                case 5:
                    
                getProtestoServ().exportarBNB(protestoList);
                exportarDoc();
                            
                break;  
                // code block
            }
            
        } catch (Exception e) {
            System.err.println("pop.webcustodia.bean.InversionBean.buscarConstancias()");
        }
        
     return url;          
    }  
    
    
    private void exportarDoc() {
        try {
           
          
          
          //String[] Datos = Archivo.split("_");
          //String lsfondo = null;
          //String lsinversion = null;
          
            //lsfondo= Datos[0];          
            //lsinversion= Datos[1];

           
            String ruta="C:\\pop\\webcustodia\\files\\document\\cumplimiento\\carta.docx";
           
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
                hsr.setHeader("Content-disposition", "inline;filename=\"" + "carta_bnb.docx" );
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
            System.out.println(e.getMessage());
        }
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
     * @return the protesto
     */
    public MovimientoProtesto getProtesto() {
        return protesto;
    }

    /**
     * @param protesto the protesto to set
     */
    public void setProtesto(MovimientoProtesto protesto) {
        this.protesto = protesto;
    }

    /**
     * @return the protestoList
     */
    public List<MovimientoProtesto> getProtestoList() {
        return protestoList;
    }

    /**
     * @param protestoList the protestoList to set
     */
    public void setProtestoList(List<MovimientoProtesto> protestoList) {
        this.protestoList = protestoList;
    }

    /**
     * @return the protestoServ
     */
    public IProtestoServ getProtestoServ() {
        return protestoServ;
    }

    /**
     * @param protestoServ the protestoServ to set
     */
    public void setProtestoServ(IProtestoServ protestoServ) {
        this.protestoServ = protestoServ;
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
     * @return the nombreCompleto
     */
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    /**
     * @param nombreCompleto the nombreCompleto to set
     */
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    /**
     * @return the listaTipos
     */
    public List<CobTablas> getListaTipos() {
        return listaTipos;
    }

    /**
     * @param listaTipos the listaTipos to set
     */
    public void setListaTipos(List<CobTablas> listaTipos) {
        this.listaTipos = listaTipos;
    }

    
}
