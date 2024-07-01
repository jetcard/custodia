/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.comun.dominio;

import java.util.Date;

/**
 *
 * @author hha
 */
public class CobTablas extends Base{

    private static final long serialVersionUID = 1L;
    
    private String ctablaId;
    private String ctablaDetId;
    private String ddescripcion;
    private String ddescCorta;
    private Integer prioridad;
    private String estado;
    private String criterio1;
    private String criterio2;
    private String criterio3;
    private Integer criterio4;
    private Integer criterio5;
    private Integer criterio6;
    private Date criterio7;
    private Date criterio8;
    private Date criterio9;    
    private Integer flg_detalle;
    private String codpadre;

    public String getCodpadre() {
        return codpadre;
    }

    public void setCodpadre(String codpadre) {
        this.codpadre = codpadre;
    }
    
    
    public CobTablas() {
    }

    public String getCtablaId() {
        return ctablaId;
    }

    public void setCtablaId(String ctablaId) {
        this.ctablaId = ctablaId;
    }

    public String getCtablaDetId() {
        return ctablaDetId;
    }

    public void setCtablaDetId(String ctablaDetId) {
        this.ctablaDetId = ctablaDetId;
    }

    public String getDdescripcion() {
        return ddescripcion;
    }

    public void setDdescripcion(String ddescripcion) {
        this.ddescripcion = ddescripcion;
    }

    public String getDdescCorta() {
        return ddescCorta;
    }

    public void setDdescCorta(String ddescCorta) {
        this.ddescCorta = ddescCorta;
    }

    public Integer getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
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

    @Override
    public String toString() {
        return "CobTablas{" + "ctablaId=" + ctablaId + ", ctablaDetId=" + ctablaDetId + ", ddescripcion=" + ddescripcion + ", ddescCorta=" + ddescCorta + ", prioridad=" + prioridad + ", estado=" + estado + '}';
    }

    /**
     * @return the criterio1
     */
    public String getCriterio1() {
        return criterio1;
    }

    /**
     * @param criterio1 the criterio1 to set
     */
    public void setCriterio1(String criterio1) {
        this.criterio1 = criterio1;
    }

    /**
     * @return the criterio2
     */
    public String getCriterio2() {
        return criterio2;
    }

    /**
     * @param criterio2 the criterio2 to set
     */
    public void setCriterio2(String criterio2) {
        this.criterio2 = criterio2;
    }

    /**
     * @return the criterio3
     */
    public String getCriterio3() {
        return criterio3;
    }

    /**
     * @param criterio3 the criterio3 to set
     */
    public void setCriterio3(String criterio3) {
        this.criterio3 = criterio3;
    }

    /**
     * @return the criterio4
     */
    public Integer getCriterio4() {
        return criterio4;
    }

    /**
     * @param criterio4 the criterio4 to set
     */
    public void setCriterio4(Integer criterio4) {
        this.criterio4 = criterio4;
    }

    /**
     * @return the criterio5
     */
    public Integer getCriterio5() {
        return criterio5;
    }

    /**
     * @param criterio5 the criterio5 to set
     */
    public void setCriterio5(Integer criterio5) {
        this.criterio5 = criterio5;
    }

    /**
     * @return the criterio6
     */
    public Integer getCriterio6() {
        return criterio6;
    }

    /**
     * @param criterio6 the criterio6 to set
     */
    public void setCriterio6(Integer criterio6) {
        this.criterio6 = criterio6;
    }

    /**
     * @return the criterio7
     */
    public Date getCriterio7() {
        return criterio7;
    }

    /**
     * @param criterio7 the criterio7 to set
     */
    public void setCriterio7(Date criterio7) {
        this.criterio7 = criterio7;
    }

    /**
     * @return the criterio8
     */
    public Date getCriterio8() {
        return criterio8;
    }

    /**
     * @param criterio8 the criterio8 to set
     */
    public void setCriterio8(Date criterio8) {
        this.criterio8 = criterio8;
    }

    /**
     * @return the criterio9
     */
    public Date getCriterio9() {
        return criterio9;
    }

    /**
     * @param criterio9 the criterio9 to set
     */
    public void setCriterio9(Date criterio9) {
        this.criterio9 = criterio9;
    }

    public Integer getFlg_detalle() {
        return flg_detalle;
    }

    public void setFlg_detalle(Integer flg_detalle) {
        this.flg_detalle = flg_detalle;
    }
	
    
}
