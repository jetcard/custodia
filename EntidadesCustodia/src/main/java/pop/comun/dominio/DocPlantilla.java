/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.comun.dominio;

/**
 *
 * @author xxxxx
 */
public class DocPlantilla {

    private String tabla;
    private String tabla_item;
    private Number indice;
    private String concepto;
    private String valor;
    private MaeInversion inversion;

    /**
     * @return the tabla
     */
    public String getTabla() {
        return tabla;
    }

    /**
     * @param tabla the tabla to set
     */
    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    /**
     * @return the tabla_item
     */
    public String getTabla_item() {
        return tabla_item;
    }

    /**
     * @param tabla_item the tabla_item to set
     */
    public void setTabla_item(String tabla_item) {
        this.tabla_item = tabla_item;
    }

    /**
     * @return the indice
     */
    public Number getIndice() {
        return indice;
    }

    /**
     * @param indice the indice to set
     */
    public void setIndice(Number indice) {
        this.indice = indice;
    }

    /**
     * @return the concepto
     */
    public String getConcepto() {
        return concepto;
    }

    /**
     * @param concepto the concepto to set
     */
    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    /**
     * @return the valor
     */
    public String getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(String valor) {
        this.valor = valor;
    }

    /**
     * @return the inversion
     */
    public MaeInversion getInversion() {
        return inversion;
    }

    /**
     * @param inversion the inversion to set
     */
    public void setInversion(MaeInversion inversion) {
        this.inversion = inversion;
    }
    

    
}
