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
public class CobRequerimientoCriterios extends Base{
 
    private String reqTipo;
    private Integer reqNumero;
    private Integer criItem;

    
    private Integer cuota_ven_tot;
    private Date ult_pago;
    private Integer dias_sinpago;
    private Integer cant_2cuota_atras;
    private Integer frecuencia_pago;
    private String con_tchn;
    private String con_ashipo;
    private Date f_1ra_cn;
    private String o_1ra_cn;
    private Date f_rei_cn;
    private String o_rei_cn;
    private Date f_tra_pn;
    private String o_tra_pn;
    private Date f_pro_cn;
    private String o_pro_cn;
    private Date f_preje_cn;
    private String o_preje_cn;
    
    
    private CobRequerimientoCartas requerimiento;
    
    private boolean sel=false;    
    

    /**
     * @return the reqTipo
     */
    public String getReqTipo() {
        return reqTipo;
    }

    /**
     * @param reqTipo the reqTipo to set
     */
    public void setReqTipo(String reqTipo) {
        this.reqTipo = reqTipo;
    }

    /**
     * @return the reqNumero
     */
    public Integer getReqNumero() {
        return reqNumero;
    }

    /**
     * @param reqNumero the reqNumero to set
     */
    public void setReqNumero(Integer reqNumero) {
        this.reqNumero = reqNumero;
    }

    /**
     * @return the criItem
     */
    public Integer getCriItem() {
        return criItem;
    }

    /**
     * @param criItem the criItem to set
     */
    public void setCriItem(Integer criItem) {
        this.criItem = criItem;
    }

    /**
     * @return the requerimiento
     */
    public CobRequerimientoCartas getRequerimiento() {
        return requerimiento;
    }

    /**
     * @param requerimiento the requerimiento to set
     */
    public void setRequerimiento(CobRequerimientoCartas requerimiento) {
        this.requerimiento = requerimiento;
    }

    /**
     * @return the sel
     */
    public boolean isSel() {
        return sel;
    }

    /**
     * @param sel the sel to set
     */
    public void setSel(boolean sel) {
        this.sel = sel;
    }

    /**
     * @return the cuota_ven_tot
     */
    public Integer getCuota_ven_tot() {
        return cuota_ven_tot;
    }

    /**
     * @param cuota_ven_tot the cuota_ven_tot to set
     */
    public void setCuota_ven_tot(Integer cuota_ven_tot) {
        this.cuota_ven_tot = cuota_ven_tot;
    }

    /**
     * @return the ult_pago
     */
    public Date getUlt_pago() {
        return ult_pago;
    }

    /**
     * @param ult_pago the ult_pago to set
     */
    public void setUlt_pago(Date ult_pago) {
        this.ult_pago = ult_pago;
    }

    /**
     * @return the dias_sinpago
     */
    public Integer getDias_sinpago() {
        return dias_sinpago;
    }

    /**
     * @param dias_sinpago the dias_sinpago to set
     */
    public void setDias_sinpago(Integer dias_sinpago) {
        this.dias_sinpago = dias_sinpago;
    }

    /**
     * @return the cant_2cuota_atras
     */
    public Integer getCant_2cuota_atras() {
        return cant_2cuota_atras;
    }

    /**
     * @param cant_2cuota_atras the cant_2cuota_atras to set
     */
    public void setCant_2cuota_atras(Integer cant_2cuota_atras) {
        this.cant_2cuota_atras = cant_2cuota_atras;
    }

    /**
     * @return the frecuencia_pago
     */
    public Integer getFrecuencia_pago() {
        return frecuencia_pago;
    }

    /**
     * @param frecuencia_pago the frecuencia_pago to set
     */
    public void setFrecuencia_pago(Integer frecuencia_pago) {
        this.frecuencia_pago = frecuencia_pago;
    }

    /**
     * @return the con_tchn
     */
    public String getCon_tchn() {
        return con_tchn;
    }

    /**
     * @param con_tchn the con_tchn to set
     */
    public void setCon_tchn(String con_tchn) {
        this.con_tchn = con_tchn;
    }

    /**
     * @return the con_ashipo
     */
    public String getCon_ashipo() {
        return con_ashipo;
    }

    /**
     * @param con_ashipo the con_ashipo to set
     */
    public void setCon_ashipo(String con_ashipo) {
        this.con_ashipo = con_ashipo;
    }

    /**
     * @return the f_1ra_cn
     */
    public Date getF_1ra_cn() {
        return f_1ra_cn;
    }

    /**
     * @param f_1ra_cn the f_1ra_cn to set
     */
    public void setF_1ra_cn(Date f_1ra_cn) {
        this.f_1ra_cn = f_1ra_cn;
    }

    /**
     * @return the o_1ra_cn
     */
    public String getO_1ra_cn() {
        return o_1ra_cn;
    }

    /**
     * @param o_1ra_cn the o_1ra_cn to set
     */
    public void setO_1ra_cn(String o_1ra_cn) {
        this.o_1ra_cn = o_1ra_cn;
    }

    /**
     * @return the f_rei_cn
     */
    public Date getF_rei_cn() {
        return f_rei_cn;
    }

    /**
     * @param f_rei_cn the f_rei_cn to set
     */
    public void setF_rei_cn(Date f_rei_cn) {
        this.f_rei_cn = f_rei_cn;
    }

    /**
     * @return the o_rei_cn
     */
    public String getO_rei_cn() {
        return o_rei_cn;
    }

    /**
     * @param o_rei_cn the o_rei_cn to set
     */
    public void setO_rei_cn(String o_rei_cn) {
        this.o_rei_cn = o_rei_cn;
    }

    /**
     * @return the f_tra_pn
     */
    public Date getF_tra_pn() {
        return f_tra_pn;
    }

    /**
     * @param f_tra_pn the f_tra_pn to set
     */
    public void setF_tra_pn(Date f_tra_pn) {
        this.f_tra_pn = f_tra_pn;
    }

    /**
     * @return the o_tra_pn
     */
    public String getO_tra_pn() {
        return o_tra_pn;
    }

    /**
     * @param o_tra_pn the o_tra_pn to set
     */
    public void setO_tra_pn(String o_tra_pn) {
        this.o_tra_pn = o_tra_pn;
    }

    /**
     * @return the f_pro_cn
     */
    public Date getF_pro_cn() {
        return f_pro_cn;
    }

    /**
     * @param f_pro_cn the f_pro_cn to set
     */
    public void setF_pro_cn(Date f_pro_cn) {
        this.f_pro_cn = f_pro_cn;
    }

    /**
     * @return the o_pro_cn
     */
    public String getO_pro_cn() {
        return o_pro_cn;
    }

    /**
     * @param o_pro_cn the o_pro_cn to set
     */
    public void setO_pro_cn(String o_pro_cn) {
        this.o_pro_cn = o_pro_cn;
    }

    /**
     * @return the f_preje_cn
     */
    public Date getF_preje_cn() {
        return f_preje_cn;
    }

    /**
     * @param f_preje_cn the f_preje_cn to set
     */
    public void setF_preje_cn(Date f_preje_cn) {
        this.f_preje_cn = f_preje_cn;
    }

    /**
     * @return the o_preje_cn
     */
    public String getO_preje_cn() {
        return o_preje_cn;
    }

    /**
     * @param o_preje_cn the o_preje_cn to set
     */
    public void setO_preje_cn(String o_preje_cn) {
        this.o_preje_cn = o_preje_cn;
    }
    
}
