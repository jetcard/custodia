/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.comun.dominio;

import java.util.Date;

/**
 *
 * @author RC433838
 */
public class MovimientoProtesto  extends Base{
    
    private static long serialVersionUID = 1L;    

    private String doc_tipo;
    private Number doc_numero;
    private String codfondo;
    private String dvalor_bv;
    private Date femision;
    private Date fprotesto;
    private String estado;
    private String comentario;
    private MaeInversion maeInversion;
    private CobTablas mae_estado;
    private MaeFondo maeFondo;
    private String req_tipo;
    private Number req_numero;
    
    
    private Date femision2;
    private Date fprotesto2;  
    private boolean selected=false;
    
    private MovimientoBNB bnb;
    
    public MovimientoProtesto(){
        this.maeInversion = new MaeInversion();
        this.mae_estado = new CobTablas();
    }

    
    /**
     * @return the mae_estado
     */
    public CobTablas getMae_estado() {
        return mae_estado;
    }

    /**
     * @param mae_estado the mae_estado to set
     */
    public void setMae_estado(CobTablas mae_estado) {
        this.mae_estado = mae_estado;
    }


    
    /**
     * @return the doc_tipo
     */
    public String getDoc_tipo() {
        return doc_tipo;
    }

    /**
     * @param doc_tipo the doc_tipo to set
     */
    public void setDoc_tipo(String doc_tipo) {
        this.doc_tipo = doc_tipo;
    }

    /**
     * @return the doc_numero
     */
    public Number getDoc_numero() {
        return doc_numero;
    }

    /**
     * @param doc_numero the doc_numero to set
     */
    public void setDoc_numero(Number doc_numero) {
        this.doc_numero = doc_numero;
    }

    /**
     * @return the codfondo
     */
    public String getCodfondo() {
        return codfondo;
    }

    /**
     * @param codfondo the codfondo to set
     */
    public void setCodfondo(String codfondo) {
        this.codfondo = codfondo;
    }

    /**
     * @return the dvalor_bv
     */
    public String getDvalor_bv() {
        return dvalor_bv;
    }

    /**
     * @param dvalor_bv the dvalor_bv to set
     */
    public void setDvalor_bv(String dvalor_bv) {
        this.dvalor_bv = dvalor_bv;
    }

    /**
     * @return the femision
     */
    public Date getFemision() {
        return femision;
    }

    /**
     * @param femision the femision to set
     */
    public void setFemision(Date femision) {
        this.femision = femision;
    }

    /**
     * @return the fprotesto
     */
    public Date getFprotesto() {
        return fprotesto;
    }

    /**
     * @param fprotesto the fprotesto to set
     */
    public void setFprotesto(Date fprotesto) {
        this.fprotesto = fprotesto;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }


    /**
     * @return the comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * @param comentario the comentario to set
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
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
     * @return the femision2
     */
    public Date getFemision2() {
        return femision2;
    }

    /**
     * @param femision2 the femision2 to set
     */
    public void setFemision2(Date femision2) {
        this.femision2 = femision2;
    }

    /**
     * @return the fprotesto2
     */
    public Date getFprotesto2() {
        return fprotesto2;
    }

    /**
     * @param fprotesto2 the fprotesto2 to set
     */
    public void setFprotesto2(Date fprotesto2) {
        this.fprotesto2 = fprotesto2;
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

    /**
     * @return the bnb
     */
    public MovimientoBNB getBnb() {
        return bnb;
    }

    /**
     * @param bnb the bnb to set
     */
    public void setBnb(MovimientoBNB bnb) {
        this.bnb = bnb;
    }
   

    
}
