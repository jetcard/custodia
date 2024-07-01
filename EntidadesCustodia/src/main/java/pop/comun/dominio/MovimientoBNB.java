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
public class MovimientoBNB   extends Base  {
    
 
    private Integer nro_serie;
    private Date f_ingreso;
    private Date f_emision;
    private Date f_vencimiento;
    private Integer codigo;
    private String nombre;
    private String valor_nominal;
    private String registro_predio;
    private Double tasa;
    private Integer nro_cuenta;
    private String custodio;
    private String tipo;
    private Integer cantidad;
    private String precio;
    private String agencia;
    private String titulo;
    private String rut;
    private String sku;
    private Integer doc_numero;

    /**
     * @return the nro_serie
     */
    public Integer getNro_serie() {
        return nro_serie;
    }

    /**
     * @param nro_serie the nro_serie to set
     */
    public void setNro_serie(Integer nro_serie) {
        this.nro_serie = nro_serie;
    }

    /**
     * @return the f_ingreso
     */
    public Date getF_ingreso() {
        return f_ingreso;
    }

    /**
     * @param f_ingreso the f_ingreso to set
     */
    public void setF_ingreso(Date f_ingreso) {
        this.f_ingreso = f_ingreso;
    }

    /**
     * @return the f_emision
     */
    public Date getF_emision() {
        return f_emision;
    }

    /**
     * @param f_emision the f_emision to set
     */
    public void setF_emision(Date f_emision) {
        this.f_emision = f_emision;
    }

    /**
     * @return the f_vencimiento
     */
    public Date getF_vencimiento() {
        return f_vencimiento;
    }

    /**
     * @param f_vencimiento the f_vencimiento to set
     */
    public void setF_vencimiento(Date f_vencimiento) {
        this.f_vencimiento = f_vencimiento;
    }

    /**
     * @return the codigo
     */
    public Integer getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the valor_nominal
     */
    public String getValor_nominal() {
        return valor_nominal;
    }

    /**
     * @param valor_nominal the valor_nominal to set
     */
    public void setValor_nominal(String valor_nominal) {
        this.valor_nominal = valor_nominal;
    }

    /**
     * @return the registro_predio
     */
    public String getRegistro_predio() {
        return registro_predio;
    }

    /**
     * @param registro_predio the registro_predio to set
     */
    public void setRegistro_predio(String registro_predio) {
        this.registro_predio = registro_predio;
    }

    /**
     * @return the tasa
     */
    public Double getTasa() {
        return tasa;
    }

    /**
     * @param tasa the tasa to set
     */
    public void setTasa(Double tasa) {
        this.tasa = tasa;
    }

    /**
     * @return the nro_cuenta
     */
    public Integer getNro_cuenta() {
        return nro_cuenta;
    }

    /**
     * @param nro_cuenta the nro_cuenta to set
     */
    public void setNro_cuenta(Integer nro_cuenta) {
        this.nro_cuenta = nro_cuenta;
    }

    /**
     * @return the custodio
     */
    public String getCustodio() {
        return custodio;
    }

    /**
     * @param custodio the custodio to set
     */
    public void setCustodio(String custodio) {
        this.custodio = custodio;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the cantidad
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the precio
     */
    public String getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(String precio) {
        this.precio = precio;
    }

    /**
     * @return the agencia
     */
    public String getAgencia() {
        return agencia;
    }

    /**
     * @param agencia the agencia to set
     */
    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the rut
     */
    public String getRut() {
        return rut;
    }

    /**
     * @param rut the rut to set
     */
    public void setRut(String rut) {
        this.rut = rut;
    }

    /**
     * @return the sku
     */
    public String getSku() {
        return sku;
    }

    /**
     * @param sku the sku to set
     */
    public void setSku(String sku) {
        this.sku = sku;
    }

    /**
     * @return the doc_numero
     */
    public Integer getDoc_numero() {
        return doc_numero;
    }

    /**
     * @param doc_numero the doc_numero to set
     */
    public void setDoc_numero(Integer doc_numero) {
        this.doc_numero = doc_numero;
    }
    
    
}
