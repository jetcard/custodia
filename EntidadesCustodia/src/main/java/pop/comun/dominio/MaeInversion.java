


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package pop.comun.dominio;


import java.util.AbstractList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
/**
 *
 * @author Jyoverar
 */


public class MaeInversion extends Base {
    
    private static long serialVersionUID = 1L;
    
    private Number cMaeInversionId;
    private MaeFondo maeFondo;

    private String cInversionId;
    private String cTipoInv;
    private String cInversion;
    private MaePersona cPersonaId;
    private MaeInmueble cInmueble;    
    private Date fRegistro;
    private Date fEmision;
    private Date fInterno;
    private Date fVencimiento;
    private Date finicio;
    private Date fGiro;
    

    private Date ffin;
    private Number iInversion;
    
    private Number cInmuebleId;
    
    private String corigenId;
    private String descripcion;

    private String cgeneraDoc;
    private String sDocumentoId;
    private String sCODDocumento;
    private Date SFIngreso;
    private String STCarta;
    private String SNombreArchivo;
    private String SNombreFormato;
    private int iDoc_numero;
    private String sDoc_tipo; 
    private int docSituacion;    
    
    private String SNombanco;
    private String NRcuenta;
    private String SDfondoOri;
    private String SDireccionDestino;
    private String SDireccionOrigen;
    private String SNro_ruc;
    private List<DocPlantilla> listaDocPlantilla;
    private boolean selected = false;
    private String pageNext;
    private String pagePrev;
        
    private MaeInmueble maeInmueble;
    
    private List<MaePersona> maePersonaList; 
    private List<MovimientoProtesto> maeProtestoList;
    private List<CobRequerimientoCartas> requerimientoCartasList;

    private Boolean xFlagSel;


    private Boolean xFlagCerrado;
    private String sCerrado; 
    private String desDestino; 
    private int nMovIng;
    private int nMovSal;
    private String sEstado;
    private String sIndicador;        
    private String sComentario;
    private String sSubsanado;
    private Date fSubsanado;
    private int ndias;
    private String sSelec; 
    private String sTipOpera;
    private String esActivo;
    private String esjudicial;
    private String sEstadof2;
    private String sCodOrigen;
    private String sCodDigital;
    private String sCustodia;
    private String sDestino;
    private Date fEscritura;
    private String sNotaria;
    private String sAsHipo;
    private String sPeHipo;
    private String sAsExpTchn;
    private String sAsPoder;
    private String sPePoder;
    private String sTchn;
    private Date fTchn;
    private String sPrestamo;
    private Integer nDocumento;
    private String sFechaIng;
    private String sFechaSal;
    private String sFechaDevol;

    
    private Date dFechaIng;
    private Date dFechaSal;
    private String sMotivo;
    private String sSolicitante;
    private String sNombreSol;
    private boolean fMostrar;
    private String ampliado;
    private String refinanciado;
    private String cancelado;
    private String cobranza;


    private String judicial;
    private String transfAmpl;
    private String transfendosado;
    private String transfrefin;
    private int ncuotas;
    
    private String JefeCustodia;
    private String JefeLegal;  
    
    private String req_tipo;
    private Number req_numero;    
    private Boolean xFlagDetalle;
    
    private String estaNotificado;
    private String fechaNotificacion;
    
    /**
     * @return the fTchn
     */
    public Date getfTchn() {
        return fTchn;
    }

    /**
     * @param fTchn the fTchn to set
     */
    public void setfTchn(Date fTchn) {
        this.fTchn = fTchn;
    }

    /**
     * @return the fInterno
     */
    public Date getfInterno() {
        return fInterno;
    }

    /**
     * @param fInterno the fInterno to set
     */
    public void setfInterno(Date fInterno) {
        this.fInterno = fInterno;
    }

    /**
     * @return the fGiro
     */
    public Date getFGiro() {
        return getfGiro();
    }

    /**
     * @param fGiro the fGiro to set
     */
    public void setFGiro(Date fGiro) {
        this.setfGiro(fGiro);
    }

    /**
     * @return the docSituacion
     */
    public int getDocSituacion() {
        return docSituacion;
    }

    /**
     * @param docSituacion the docSituacion to set
     */
    public void setDocSituacion(int docSituacion) {
        this.docSituacion = docSituacion;
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
     * @return the SNombreFormato
     */
    public String getSNombreFormato() {
        return SNombreFormato;
    }

    /**
     * @param SNombreFormato the SNombreFormato to set
     */
    public void setSNombreFormato(String SNombreFormato) {
        this.SNombreFormato = SNombreFormato;
    }



    public Boolean getXFlagDetalle() {
        return xFlagDetalle;
    }

    public void setXFlagDetalle(Boolean xFlagDetalle) {
        this.xFlagDetalle = xFlagDetalle;
    }

    
    public Date getSFIngreso() {
        return SFIngreso;
    }

    public void setSFIngreso(Date SFIngreso) {
        this.SFIngreso = SFIngreso;
    }

    public String getSTCarta() {
        return STCarta;
    }

    public void setSTCarta(String STCarta) {
        this.STCarta = STCarta;
    }    

    public String getAmpliado() {
        return ampliado;
    }

    public void setAmpliado(String ampliado) {
        this.ampliado = ampliado;
    }

    public String getRefinanciado() {
        return refinanciado;
    }

    public void setRefinanciado(String refinanciado) {
        this.refinanciado = refinanciado;
    }

    public String getCancelado() {
        return cancelado;
    }

    public void setCancelado(String cancelado) {
        this.cancelado = cancelado;
    }

    public String getJudicial() {
        return judicial;
    }

    public void setJudicial(String judicial) {
        this.judicial = judicial;
    }

    public String getTransfAmpl() {
        return transfAmpl;
    }

    public void setTransfAmpl(String transfAmpl) {
        this.transfAmpl = transfAmpl;
    }

    public String getTransfendosado() {
        return transfendosado;
    }

    public void setTransfendosado(String transfendosado) {
        this.transfendosado = transfendosado;
    }

    public String getTransfrefin() {
        return transfrefin;
    }

    public void setTransfrefin(String transfrefin) {
        this.transfrefin = transfrefin;
    }

    
    
    public boolean getFMostrar() {
        return isfMostrar();
    }

    public void setFMostrar(boolean fMostrar) {
        this.setfMostrar(fMostrar);
    }

   
    
    public String getSCodOrigen() {
        return getsCodOrigen();
    }

    public void setSCodOrigen(String sCodOrigen) {
        this.setsCodOrigen(sCodOrigen);
    }

    public String getSCodDigital() {
        return getsCodDigital();
    }

    public void setSCodDigital(String sCodDigital) {
        this.setsCodDigital(sCodDigital);
    }

    public String getSCustodia() {
        return getsCustodia();
    }

    public void setSCustodia(String sCustodia) {
        this.setsCustodia(sCustodia);
    }

    public String getSDestino() {
        return getsDestino();
    }

    public void setSDestino(String sDestino) {
        this.setsDestino(sDestino);
    }

    public Date getFEscritura() {
        return getfEscritura();
    }

    public void setFEscritura(Date fEscritura) {
        this.setfEscritura(fEscritura);
    }

    
    public String getSNotaria() {
        return getsNotaria();
    }

    public void setSNotaria(String sNotaria) {
        this.setsNotaria(sNotaria);
    }
    

    public String getSAsHipo() {
        return getsAsHipo();
    }

    public void setSAsHipo(String sAsHipo) {
        this.setsAsHipo(sAsHipo);
    }

    public String getSAsExpTchn() {
        return getsAsExpTchn();
    }

    public void setSAsExpTchn(String sAsExpTchn) {
        this.setsAsExpTchn(sAsExpTchn);
    }

    public String getSAsPoder() {
        return getsAsPoder();
    }

    public void setSAsPoder(String sAsPoder) {
        this.setsAsPoder(sAsPoder);
    }

    public String getSPePoder() {
        return getsPePoder();
    }

    public void setSPePoder(String sPePoder) {
        this.setsPePoder(sPePoder);
    }

    public String getSTchn() {
        return getsTchn();
    }

    public void setSTchn(String sTchn) {
        this.setsTchn(sTchn);
    }

    public String getSTipOpera() {
        return getsTipOpera();
    }

    public void setSTipOpera(String sTipOpera) {
        this.setsTipOpera(sTipOpera);
    }
    
    public String getESActivo() {
        return getEsActivo();
    }

    public void setESActivo(String esActivo) {
        this.setEsActivo(esActivo);
    }

    public String getESjudicial() {
        return getEsjudicial();
    }

    public void setESjudicial(String esjudicial) {
        this.setEsjudicial(esjudicial);
    }

    public String getSEstadof2() {
        return getsEstadof2();
    }

    public void setSEstadof2(String sEstadof2) {
        this.setsEstadof2(sEstadof2);
    }

   
    public MaeInversion() {       
    }

    public String getCInversion() {
        return getcInversion();
    }

    public void setCInversion(String cInversion) {
        this.setcInversion(cInversion);
    }


    public Date getFRegistro() {
        return getfRegistro();
    }

    public void setFRegistro(Date fRegistro) {
        this.setfRegistro(fRegistro);
    }

    
    public Date getFEmision() {
        return getfEmision();
    }

    public void setFEmision(Date fEmision) {
        this.setfEmision(fEmision);
    }

    public Date getFVencimiento() {
        return getfVencimiento();
    }

    public void setFVencimiento(Date fVencimiento) {
        this.setfVencimiento(fVencimiento);
    }

    public Number getIInversion() {
        return getiInversion();
    }

    public void setIInversion(Number iInversion) {
        this.setiInversion(iInversion);
    }

    public String getSPrestamo() {
        return getsPrestamo();
    }

    public void setSPrestamo(String sPrestamo) {
        this.setsPrestamo(sPrestamo);
    }

    public Integer getNDocumento() {
        return getnDocumento();
    }

    public void setNDocumento(Integer nDocumento) {
        this.setnDocumento(nDocumento);
    }
    
     public Number getCInmuebleId() {
        return getcInmuebleId();
    }

    public void setCInmuebleId(Number cInmuebleId) {
        this.setcInmuebleId(cInmuebleId);
        
        
    }
    
    public String getsCodOrigen() {
        return sCodOrigen;
    }

    public void setsCodOrigen(String sCodOrigen) {
        this.sCodOrigen = sCodOrigen;
    }

    public String getsCodDigital() {
        return sCodDigital;
    }

    public void setsCodDigital(String sCodDigital) {
        this.sCodDigital = sCodDigital;
    }    
    
    public String getsCustodia() {
        return sCustodia;
    }

    public void setsCustodia(String sCustodia) {
        this.sCustodia = sCustodia;
    }

    public String getsDestino() {
        return sDestino;
    }

    public void setsDestino(String sDestino) {
        this.sDestino = sDestino;
    }    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getcMaeInversionId() != null ? getcMaeInversionId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaeInversion)) {
            return false;
        }
        MaeInversion other = (MaeInversion) object;
        if ((this.getcMaeInversionId() == null && other.getcMaeInversionId() != null) || (this.getcMaeInversionId() != null && !this.cMaeInversionId.equals(other.cMaeInversionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.popular.web.webcustodia.entities.MaeInversion[ maeInversionPK=" + getcMaeInversionId() + " ]";
    }

 
    /**
     * @return the cInversionId
     */
    public String getcInversionId() {
        return cInversionId;
    }

    /**
     * @param cInversionId the cInversionId to set
     */
    public void setcInversionId(String cInversionId) {
        this.cInversionId = cInversionId;
    }


    /**
     * @return the cPersonaId
     */
    public MaePersona getcPersonaId() {
        return cPersonaId;
    }

    /**
     * @param cPersonaId the cPersonaId to set
     */
    public void setcPersonaId(MaePersona cPersonaId) {
        this.cPersonaId = cPersonaId;
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


    
    public String getSComentario() {
        return getsComentario();
    }

    public void setSComentario(String sComentario) {
        this.setsComentario(sComentario);
    }

    public String getSSubsanado() {
        return getsSubsanado();
    }

    public void setSSubsanado(String sSubsanado) {
        this.setsSubsanado(sSubsanado);
    }

    public Date getFSubsanado() {
        return getfSubsanado();
    }

    public void setFSubsanado(Date fSubsanado) {
        this.setfSubsanado(fSubsanado);
    }

    /**
     * @return the cMaeInversionId
     */
    public Number getcMaeInversionId() {
        return cMaeInversionId;
    }

    /**
     * @param cMaeInversionId the cMaeInversionId to set
     */
    public void setcMaeInversionId(Number cMaeInversionId) {
        this.cMaeInversionId = cMaeInversionId;
    }

   
    /**
     * @return the maeInmueble
     */
    public MaeInmueble getMaeInmueble() {
        return maeInmueble;
    }

    /**
     * @param maeInmueble the maeInmueble to set
     */
    public void setMaeInmueble(MaeInmueble maeInmueble) {
        this.maeInmueble = maeInmueble;
    }

    public String getcTipoInv() {
        return cTipoInv;
    }

    public void setcTipoInv(String cTipoInv) {
        this.cTipoInv = cTipoInv;
    }

    public String getCorigenId() {
        return corigenId;
    }

    public void setCorigenId(String corigenId) {
        this.corigenId = corigenId;
    }

   

    public List<MaePersona> getMaePersonaList() {
        return maePersonaList;
    }

    public void setMaePersonaList(List<MaePersona> maePersonaList) {
        this.maePersonaList = maePersonaList;
    }

    public String getCgeneraDoc() {
        return cgeneraDoc;
    }

    public void setCgeneraDoc(String cgeneraDoc) {
        this.cgeneraDoc = cgeneraDoc;
    }

    
     

    public String getSEstado() {
        return getsEstado();
    }

    public void setSEstado(String sEstado) {
        this.setsEstado(sEstado);
    }

    public String getSIndicador() {
        return getsIndicador();
    }

    public void setSIndicador(String sIndicador) {
        this.setsIndicador(sIndicador);
    }

    public int getNDias() {
        return getNdias();
    }

    public void setNDias(int ndias) {
        this.setNdias(ndias);
    }

    public String getsSelec() {
        return sSelec;
    }

    public void setsSelec(String sSelec) {
        this.sSelec = sSelec;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getsDocumentoId() {
        return sDocumentoId;
    }

    public void setsDocumentoId(String sDocumentoId) {
        this.sDocumentoId = sDocumentoId;
    }
    

 
    public Date getFinicio() {
        return finicio;
    }

    public void setFinicio(Date finicio) {
        this.finicio = finicio;
    }

    public Date getFfin() {
        return ffin;
    }

    public void setFfin(Date ffin) {
        this.ffin = ffin;
    }
    
    public Boolean getXFlagSel() {
        return getxFlagSel();
    }

    public void setXFlagSel(Boolean xFlagSel) {
        this.setxFlagSel(xFlagSel);
    }
    public Boolean getXFlagCerrado() {
        return getxFlagCerrado();
    }

    public void setXFlagCerrado(Boolean xFlagCerrado) {
        this.setxFlagCerrado(xFlagCerrado);
    }
    
    public String getsCerrado() {
        return sCerrado;
    }

    public void setsCerrado(String sCerrado) {
        this.sCerrado = sCerrado;
    }
    
        public Date getfRegistro() {
        return fRegistro;
    }

    public void setfRegistro(Date fRegistro) {
        this.fRegistro = fRegistro;
    }


     public String getSPeHipo() {
        return getsPeHipo();
    }

    public void setSPeHipo(String sPeHipo) {
        this.setsPeHipo(sPeHipo);
    }

 public String getSFechaIng() {
        return getsFechaIng();
    }

    public void setSFechaIng(String sFechaIng) {
        this.setsFechaIng(sFechaIng);
    }

    public String getSFechaSal() {
        return getsFechaSal();
    }

    public void setSFechaSal(String sFechaSal) {
        this.setsFechaSal(sFechaSal);
    }

    public Date getDFechaIng() {
        return getdFechaIng();
    }

    public void setDFechaIng(Date dFechaIng) {
        this.setdFechaIng(dFechaIng);
    }

    public Date getDFechaSal() {
        return getdFechaSal();
    }
    public String getSFechaDevol() {
        return getsFechaDevol();
    }

    public void setSFechaDevol(String sFechaDevol) {
        this.setsFechaDevol(sFechaDevol);
    }
    
    public void setDFechaSal(Date dFechaSal) {
        this.setdFechaSal(dFechaSal);
    }

    public String getSMotivo() {
        return getsMotivo();
    }

    public void setSMotivo(String sMotivo) {
        this.setsMotivo(sMotivo);
    }
   

  public String getSSolicitante() {
        return getsSolicitante();
    }

    public void setSSolicitante(String sSolicitante) {
        this.setsSolicitante(sSolicitante);
    }

    public String getSNombreSol() {
        return getsNombreSol();
    }

    public void setSNombreSol(String sNombreSol) {
        this.setsNombreSol(sNombreSol);
    }

    public String getCobranza() {
        return cobranza;
    }

    public void setCobranza(String cobranza) {
        this.cobranza = cobranza;
    }

    public String getDesDestino() {
        return desDestino;
    }

    public void setDesDestino(String desDestino) {
        this.desDestino = desDestino;
    }    

    public String getSCODDocumento() {         
        return getsCODDocumento();
    }

    public void setSCODDocumento(String SCODDocumento) {          
        this.setsCODDocumento(SCODDocumento);
    }


    public int getnMovIng() {
        return nMovIng;
    }

    public void setnMovIng(int nMovIng) {
        this.nMovIng = nMovIng;
    }

    public int getnMovSal() {
        return nMovSal;
    }

    public void setnMovSal(int nMovSal) {
        this.nMovSal = nMovSal;
    }
    
    public int getNCuotas() {
        return getNcuotas();
    }

    public void setNCuotas(int ncuotas) {
        this.setNcuotas(ncuotas);
    }

    public String getSNombreArchivo() {
        return SNombreArchivo;
    }

    public void setSNombreArchivo(String SNombreArchivo) {
        this.SNombreArchivo = SNombreArchivo;
    }
    
    
    
    public int getNDocNumero() {
        return getiDoc_numero();
    }

    public void setNDocNumero(int pDocNumero) {
        this.setiDoc_numero(pDocNumero);
    }

    public String getSTipoDoc() {
        return getsDoc_tipo();
    }

    public void setSTipoDoc(String pDocTipo) {
        this.setsDoc_tipo(pDocTipo);
    }
    
    
    public String getsEstadof2() {
        return sEstadof2;
    }

    public void setsEstadof2(String sEstadof2) {
        this.sEstadof2 = sEstadof2;
    }
    
    public MaeInmueble getcInmueble() {
        return cInmueble;
    }

    public void setcInmueble(MaeInmueble cInmueble) {
        this.cInmueble = cInmueble;
    }    


    public String getSNombanco() {
        return SNombanco;
    }

    public void setSNombanco(String SNombanco) {
        this.SNombanco = SNombanco;
    }

    public String getNRcuenta() {
        return NRcuenta;
    }

    public void setNRcuenta(String NRcuenta) {
        this.NRcuenta = NRcuenta;
    }

    public String getSDfondoOri() {
        return SDfondoOri;
    }

    public void setSDfondoOri(String SDfondoOri) {
        this.SDfondoOri = SDfondoOri;
    }

    public String getSDireccionDestino() {
        return SDireccionDestino;
    }

    public void setSDireccionDestino(String SDireccionDestino) {
        this.SDireccionDestino = SDireccionDestino;
    }

    public String getSDireccionOrigen() {
        return SDireccionOrigen;
    }

    public void setSDireccionOrigen(String SDireccionOrigen) {
        this.SDireccionOrigen = SDireccionOrigen;
    }

    public String getSNro_ruc() {
        return SNro_ruc;
    }

    public void setSNro_ruc(String SNro_ruc) {
        this.SNro_ruc = SNro_ruc;
    }    
    
    public String getEsjudicial() {
        return esjudicial;
    }

    public String getEsActivo() {
        return esActivo;
    }

    public void setEsActivo(String esActivo) {
        this.esActivo = esActivo;
    }
    
    public void setEsjudicial(String esjudicial) {
        this.esjudicial = esjudicial;
    }
    
    public String getsTipOpera() {
        return sTipOpera;
    }

    public void setsTipOpera(String sTipOpera) {
        this.sTipOpera = sTipOpera;
    }   
    
    public String getsAsHipo() {
        return sAsHipo;
    }

    public void setsAsHipo(String sAsHipo) {
        this.sAsHipo = sAsHipo;
    }

    public String getsPeHipo() {
        return sPeHipo;
    }

    public void setsPeHipo(String sPeHipo) {
        this.sPeHipo = sPeHipo;
    }

    public String getsAsExpTchn() {
        return sAsExpTchn;
    }

    public void setsAsExpTchn(String sAsExpTchn) {
        this.sAsExpTchn = sAsExpTchn;
    }

    public String getsAsPoder() {
        return sAsPoder;
    }

    public void setsAsPoder(String sAsPoder) {
        this.sAsPoder = sAsPoder;
    }

    public String getsPePoder() {
        return sPePoder;
    }

    public void setsPePoder(String sPePoder) {
        this.sPePoder = sPePoder;
    }

    public String getsTchn() {
        return sTchn;
    }

    public void setsTchn(String sTchn) {
        this.sTchn = sTchn;
    } 
    
    public String getJefeCustodia() {
        return JefeCustodia;
    }

    public void setJefeCustodia(String JefeCustodia) {
        this.JefeCustodia = JefeCustodia;
    }

    public String getJefeLegal() {
        return JefeLegal;
    }

    public void setJefeLegal(String JefeLegal) {
        this.JefeLegal = JefeLegal;
    }    

    /**
     * @return the listaDocPlantilla
     */
    public List<DocPlantilla> getListaDocPlantilla() {
        return listaDocPlantilla;
    }

    /**
     * @param listaDocPlantilla the listaDocPlantilla to set
     */
    public void setListaDocPlantilla(List<DocPlantilla> listaDocPlantilla) {
        this.listaDocPlantilla = listaDocPlantilla;
    }

    /**
     * @return the selected
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * @param selected the selected to set
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    /**
     * @return the maeProtestoList
     */
    public List<MovimientoProtesto> getMaeProtestoList() {
        return maeProtestoList;
    }

    /**
     * @param maeProtestoList the maeProtestoList to set
     */
    public void setMaeProtestoList(List<MovimientoProtesto> maeProtestoList) {
        this.maeProtestoList = maeProtestoList;
    }

    /**
     * @return the RequerimientoCartasList
     */
    public List<CobRequerimientoCartas> getRequerimientoCartasList() {
        return requerimientoCartasList;
    }

    /**
     * @param requerimientoCartasList the requerimientoCartasList to set
     */
    public void setRequerimientoCartasList(List<CobRequerimientoCartas> requerimientoCartasList) {
        this.requerimientoCartasList = requerimientoCartasList;
    }

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }

    /**
     * @return the cInversion
     */
    public String getcInversion() {
        return cInversion;
    }

    /**
     * @param cInversion the cInversion to set
     */
    public void setcInversion(String cInversion) {
        this.cInversion = cInversion;
    }

    /**
     * @return the fEmision
     */
    public Date getfEmision() {
        return fEmision;
    }

    /**
     * @param fEmision the fEmision to set
     */
    public void setfEmision(Date fEmision) {
        this.fEmision = fEmision;
    }

    /**
     * @return the fVencimiento
     */
    public Date getfVencimiento() {
        return fVencimiento;
    }

    /**
     * @param fVencimiento the fVencimiento to set
     */
    public void setfVencimiento(Date fVencimiento) {
        this.fVencimiento = fVencimiento;
    }

    /**
     * @return the fGiro
     */
    public Date getfGiro() {
        return fGiro;
    }

    /**
     * @param fGiro the fGiro to set
     */
    public void setfGiro(Date fGiro) {
        this.fGiro = fGiro;
    }

    /**
     * @return the iInversion
     */
    public Number getiInversion() {
        return iInversion;
    }

    /**
     * @param iInversion the iInversion to set
     */
    public void setiInversion(Number iInversion) {
        this.iInversion = iInversion;
    }

    /**
     * @return the cInmuebleId
     */
    public Number getcInmuebleId() {
        return cInmuebleId;
    }

    /**
     * @param cInmuebleId the cInmuebleId to set
     */
    public void setcInmuebleId(Number cInmuebleId) {
        this.cInmuebleId = cInmuebleId;
    }

    /**
     * @return the sCODDocumento
     */
    public String getsCODDocumento() {
        return sCODDocumento;
    }

    /**
     * @param sCODDocumento the sCODDocumento to set
     */
    public void setsCODDocumento(String sCODDocumento) {
        this.sCODDocumento = sCODDocumento;
    }

    /**
     * @return the iDoc_numero
     */
    public int getiDoc_numero() {
        return iDoc_numero;
    }

    /**
     * @param iDoc_numero the iDoc_numero to set
     */
    public void setiDoc_numero(int iDoc_numero) {
        this.iDoc_numero = iDoc_numero;
    }

    /**
     * @return the sDoc_tipo
     */
    public String getsDoc_tipo() {
        return sDoc_tipo;
    }

    /**
     * @param sDoc_tipo the sDoc_tipo to set
     */
    public void setsDoc_tipo(String sDoc_tipo) {
        this.sDoc_tipo = sDoc_tipo;
    }

    /**
     * @return the xFlagSel
     */
    public Boolean getxFlagSel() {
        return xFlagSel;
    }

    /**
     * @param xFlagSel the xFlagSel to set
     */
    public void setxFlagSel(Boolean xFlagSel) {
        this.xFlagSel = xFlagSel;
    }

    /**
     * @return the xFlagCerrado
     */
    public Boolean getxFlagCerrado() {
        return xFlagCerrado;
    }

    /**
     * @param xFlagCerrado the xFlagCerrado to set
     */
    public void setxFlagCerrado(Boolean xFlagCerrado) {
        this.xFlagCerrado = xFlagCerrado;
    }

    /**
     * @return the sEstado
     */
    public String getsEstado() {
        return sEstado;
    }

    /**
     * @param sEstado the sEstado to set
     */
    public void setsEstado(String sEstado) {
        this.sEstado = sEstado;
    }

    /**
     * @return the sIndicador
     */
    public String getsIndicador() {
        return sIndicador;
    }

    /**
     * @param sIndicador the sIndicador to set
     */
    public void setsIndicador(String sIndicador) {
        this.sIndicador = sIndicador;
    }

    /**
     * @return the sComentario
     */
    public String getsComentario() {
        return sComentario;
    }

    /**
     * @param sComentario the sComentario to set
     */
    public void setsComentario(String sComentario) {
        this.sComentario = sComentario;
    }

    /**
     * @return the sSubsanado
     */
    public String getsSubsanado() {
        return sSubsanado;
    }

    /**
     * @param sSubsanado the sSubsanado to set
     */
    public void setsSubsanado(String sSubsanado) {
        this.sSubsanado = sSubsanado;
    }

    /**
     * @return the fSubsanado
     */
    public Date getfSubsanado() {
        return fSubsanado;
    }

    /**
     * @param fSubsanado the fSubsanado to set
     */
    public void setfSubsanado(Date fSubsanado) {
        this.fSubsanado = fSubsanado;
    }

    /**
     * @return the ndias
     */
    public int getNdias() {
        return ndias;
    }

    /**
     * @param ndias the ndias to set
     */
    public void setNdias(int ndias) {
        this.ndias = ndias;
    }

    /**
     * @return the fEscritura
     */
    public Date getfEscritura() {
        return fEscritura;
    }

    /**
     * @param fEscritura the fEscritura to set
     */
    public void setfEscritura(Date fEscritura) {
        this.fEscritura = fEscritura;
    }

    /**
     * @return the sNotaria
     */
    public String getsNotaria() {
        return sNotaria;
    }

    /**
     * @param sNotaria the sNotaria to set
     */
    public void setsNotaria(String sNotaria) {
        this.sNotaria = sNotaria;
    }

    /**
     * @return the sPrestamo
     */
    public String getsPrestamo() {
        return sPrestamo;
    }

    /**
     * @param sPrestamo the sPrestamo to set
     */
    public void setsPrestamo(String sPrestamo) {
        this.sPrestamo = sPrestamo;
    }

    /**
     * @return the nDocumento
     */
    public Integer getnDocumento() {
        return nDocumento;
    }

    /**
     * @param nDocumento the nDocumento to set
     */
    public void setnDocumento(Integer nDocumento) {
        this.nDocumento = nDocumento;
    }

    /**
     * @return the sFechaIng
     */
    public String getsFechaIng() {
        return sFechaIng;
    }

    /**
     * @param sFechaIng the sFechaIng to set
     */
    public void setsFechaIng(String sFechaIng) {
        this.sFechaIng = sFechaIng;
    }

    /**
     * @return the sFechaSal
     */
    public String getsFechaSal() {
        return sFechaSal;
    }

    /**
     * @param sFechaSal the sFechaSal to set
     */
    public void setsFechaSal(String sFechaSal) {
        this.sFechaSal = sFechaSal;
    }

    /**
     * @return the sFechaDevol
     */
    public String getsFechaDevol() {
        return sFechaDevol;
    }

    /**
     * @param sFechaDevol the sFechaDevol to set
     */
    public void setsFechaDevol(String sFechaDevol) {
        this.sFechaDevol = sFechaDevol;
    }

    /**
     * @return the dFechaIng
     */
    public Date getdFechaIng() {
        return dFechaIng;
    }

    /**
     * @param dFechaIng the dFechaIng to set
     */
    public void setdFechaIng(Date dFechaIng) {
        this.dFechaIng = dFechaIng;
    }

    /**
     * @return the dFechaSal
     */
    public Date getdFechaSal() {
        return dFechaSal;
    }

    /**
     * @param dFechaSal the dFechaSal to set
     */
    public void setdFechaSal(Date dFechaSal) {
        this.dFechaSal = dFechaSal;
    }

    /**
     * @return the sMotivo
     */
    public String getsMotivo() {
        return sMotivo;
    }

    /**
     * @param sMotivo the sMotivo to set
     */
    public void setsMotivo(String sMotivo) {
        this.sMotivo = sMotivo;
    }

    /**
     * @return the sSolicitante
     */
    public String getsSolicitante() {
        return sSolicitante;
    }

    /**
     * @param sSolicitante the sSolicitante to set
     */
    public void setsSolicitante(String sSolicitante) {
        this.sSolicitante = sSolicitante;
    }

    /**
     * @return the sNombreSol
     */
    public String getsNombreSol() {
        return sNombreSol;
    }

    /**
     * @param sNombreSol the sNombreSol to set
     */
    public void setsNombreSol(String sNombreSol) {
        this.sNombreSol = sNombreSol;
    }

    /**
     * @return the fMostrar
     */
    public boolean isfMostrar() {
        return fMostrar;
    }

    /**
     * @param fMostrar the fMostrar to set
     */
    public void setfMostrar(boolean fMostrar) {
        this.fMostrar = fMostrar;
    }

    /**
     * @return the ncuotas
     */
    public int getNcuotas() {
        return ncuotas;
    }

    /**
     * @param ncuotas the ncuotas to set
     */
    public void setNcuotas(int ncuotas) {
        this.ncuotas = ncuotas;
    }

    /**
     * @return the req_tipo
     */
    public String getReq_tipo() {
        return req_tipo;
    }

    /**
     * @param req_tipo the req_tipo to set
     */
    public void setReq_tipo(String req_tipo) {
        this.req_tipo = req_tipo;
    }

    /**
     * @return the req_numero
     */
    public Number getReq_numero() {
        return req_numero;
    }

    /**
     * @param req_numero the req_numero to set
     */
    public void setReq_numero(Number req_numero) {
        this.req_numero = req_numero;
    }

    public String getEstaNotificado() {
        return estaNotificado;
    }

    public void setEstaNotificado(String estaNotificado) {
        this.estaNotificado = estaNotificado;
    }

    public String getFechaNotificacion() {
        return fechaNotificacion;
    }

    public void setFechaNotificacion(String fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }
    
}


///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package pop.comun.dominio;
//
//import java.util.Date;
//import java.util.List;
//
//
//
///**
// *
// * @author Jyoverar
// */
//public class MaeInversion extends Base{
//
//    private static final long serialVersionUID = 1L;
//    
//    private Number cMaeInversionId;
//    private MaeFondo maeFondo;
//    private String cInversionId;
//    private String cTipoInv;
//    private String cInversion;
//    private MaePersona cPersonaId;
//    private MaeInmueble cInmueble;
//
//   
//    private Date fRegistro;
//    
//
//
//    private Date fEmision;
//    private Date fVencimiento;
//    private Date finicio;
//    private Date ffin;
//    
//    private Number iCuota;
//    private String corigenId;
//    private String ccodigoIdent;
//    private String descripcion;
//
//   
//    private String sDocumentoId;
//    private String sCODDocumento;
//
//    private int nIniDiaBusq;
//    private int nFinDiaBusq;
//    
//    
//    private int nMovIng;
//    private int nMovSal;
//
//    
//   
//    private List<MaePersona> maePersonaList;
//    
//
//
//  
//    private String sEstado;
//
//    private String sIndicador;
//    private String sComentario;
//    private String sSubsanado;
//    private Date fSubsanado;
//
//   
//    private int ndias;
//    private String sSelec; 
//    private Boolean xFlagSel;
//    private String sCerrado; 
//    private String sTipOpera;
//    private String esActivo;
//    private String esjudicial;
//    private String sEstadof2;
//
//    private Boolean xFlagCerrado;
//
//    private String sCodOrigen;
//    private String sCodDigital;
//    private String sCustodia;
//    private String sDestino;
//    private Date fEscritura;
//    private String sNotaria;
//    private String sAsHipo;
//    private String sPeHipo;
//    private String sAsExpTchn;
//    private String sAsPoder;
//    private String sPePoder;
//    private String sTchn;
//    private String sPrestamo;
//    private Integer nDocumento;
//    private String sFechaIng;
//    private String sFechaSal;
//    private Date dFechaIng;
//    private Date dFechaSal;
//    private String sFechaDevol;
//
//    private String sMotivo;
//
//    private String sSolicitante;
//    private String sNombreSol;
//    private String ampliado;
//    private String refinanciado;
//    private String cancelado;
//    private String judicial;
//    private String cobranza;
//    private String desDestino;
//
//    
//  
//
//  
//    private String transfAmpl;
//    private String transfendosado;
//    private String transfrefin;
//    private String JefeCustodia;
//    private String JefeLegal;
//    private int ncuotas;
//    private Date SFIngreso;
//    private String STCarta;
//    private String SNombreArchivo;
//    
//    private String SNombanco;
//    private String NRcuenta;
//    private String SDfondoOri;
//    private String SDireccionDestino;
//    private String SDireccionOrigen;
//    private String SNro_ruc;
//
//   
//   
//   
//    
//}
