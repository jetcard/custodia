
package pop.webcustodia.webservices;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para usuario complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="usuario">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bcontrasenia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contrasenia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contraseniaB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="correo" type="{http://webservices.webcustodia.pop/}correo" minOccurs="0"/>
 *         &lt;element name="estado" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="mensaje" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="menu" type="{http://webservices.webcustodia.pop/}menu" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="numLlamada" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="opcionesPermisosmenu" type="{http://webservices.webcustodia.pop/}menu" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="perApeMat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="perApePat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="perId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="perNom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rol" type="{http://webservices.webcustodia.pop/}rol" minOccurs="0"/>
 *         &lt;element name="telefono" type="{http://webservices.webcustodia.pop/}telefono" minOccurs="0"/>
 *         &lt;element name="usuarioId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "usuario", propOrder = {
    "bcontrasenia",
    "contrasenia",
    "contraseniaB",
    "correo",
    "estado",
    "mensaje",
    "menu",
    "numLlamada",
    "opcionesPermisosmenu",
    "perApeMat",
    "perApePat",
    "perId",
    "perNom",
    "rol",
    "telefono",
    "usuarioId"
})
public class Usuario {

    protected String bcontrasenia;
    protected String contrasenia;
    protected String contraseniaB;
    protected Correo correo;
    protected boolean estado;
    protected String mensaje;
    @XmlElement(nillable = true)
    protected List<Menu> menu;
    protected int numLlamada;
    @XmlElement(nillable = true)
    protected List<Menu> opcionesPermisosmenu;
    protected String perApeMat;
    protected String perApePat;
    protected int perId;
    protected String perNom;
    protected Rol rol;
    protected Telefono telefono;
    protected String usuarioId;

    /**
     * Obtiene el valor de la propiedad bcontrasenia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBcontrasenia() {
        return bcontrasenia;
    }

    /**
     * Define el valor de la propiedad bcontrasenia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBcontrasenia(String value) {
        this.bcontrasenia = value;
    }

    /**
     * Obtiene el valor de la propiedad contrasenia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * Define el valor de la propiedad contrasenia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContrasenia(String value) {
        this.contrasenia = value;
    }

    /**
     * Obtiene el valor de la propiedad contraseniaB.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContraseniaB() {
        return contraseniaB;
    }

    /**
     * Define el valor de la propiedad contraseniaB.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContraseniaB(String value) {
        this.contraseniaB = value;
    }

    /**
     * Obtiene el valor de la propiedad correo.
     * 
     * @return
     *     possible object is
     *     {@link Correo }
     *     
     */
    public Correo getCorreo() {
        return correo;
    }

    /**
     * Define el valor de la propiedad correo.
     * 
     * @param value
     *     allowed object is
     *     {@link Correo }
     *     
     */
    public void setCorreo(Correo value) {
        this.correo = value;
    }

    /**
     * Obtiene el valor de la propiedad estado.
     * 
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * Define el valor de la propiedad estado.
     * 
     */
    public void setEstado(boolean value) {
        this.estado = value;
    }

    /**
     * Obtiene el valor de la propiedad mensaje.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Define el valor de la propiedad mensaje.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMensaje(String value) {
        this.mensaje = value;
    }

    /**
     * Gets the value of the menu property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the menu property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMenu().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Menu }
     * 
     * 
     */
    public List<Menu> getMenu() {
        if (menu == null) {
            menu = new ArrayList<Menu>();
        }
        return this.menu;
    }

    /**
     * Obtiene el valor de la propiedad numLlamada.
     * 
     */
    public int getNumLlamada() {
        return numLlamada;
    }

    /**
     * Define el valor de la propiedad numLlamada.
     * 
     */
    public void setNumLlamada(int value) {
        this.numLlamada = value;
    }

    /**
     * Gets the value of the opcionesPermisosmenu property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the opcionesPermisosmenu property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOpcionesPermisosmenu().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Menu }
     * 
     * 
     */
    public List<Menu> getOpcionesPermisosmenu() {
        if (opcionesPermisosmenu == null) {
            opcionesPermisosmenu = new ArrayList<Menu>();
        }
        return this.opcionesPermisosmenu;
    }

    /**
     * Obtiene el valor de la propiedad perApeMat.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPerApeMat() {
        return perApeMat;
    }

    /**
     * Define el valor de la propiedad perApeMat.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPerApeMat(String value) {
        this.perApeMat = value;
    }

    /**
     * Obtiene el valor de la propiedad perApePat.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPerApePat() {
        return perApePat;
    }

    /**
     * Define el valor de la propiedad perApePat.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPerApePat(String value) {
        this.perApePat = value;
    }

    /**
     * Obtiene el valor de la propiedad perId.
     * 
     */
    public int getPerId() {
        return perId;
    }

    /**
     * Define el valor de la propiedad perId.
     * 
     */
    public void setPerId(int value) {
        this.perId = value;
    }

    /**
     * Obtiene el valor de la propiedad perNom.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPerNom() {
        return perNom;
    }

    /**
     * Define el valor de la propiedad perNom.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPerNom(String value) {
        this.perNom = value;
    }

    /**
     * Obtiene el valor de la propiedad rol.
     * 
     * @return
     *     possible object is
     *     {@link Rol }
     *     
     */
    public Rol getRol() {
        return rol;
    }

    /**
     * Define el valor de la propiedad rol.
     * 
     * @param value
     *     allowed object is
     *     {@link Rol }
     *     
     */
    public void setRol(Rol value) {
        this.rol = value;
    }

    /**
     * Obtiene el valor de la propiedad telefono.
     * 
     * @return
     *     possible object is
     *     {@link Telefono }
     *     
     */
    public Telefono getTelefono() {
        return telefono;
    }

    /**
     * Define el valor de la propiedad telefono.
     * 
     * @param value
     *     allowed object is
     *     {@link Telefono }
     *     
     */
    public void setTelefono(Telefono value) {
        this.telefono = value;
    }

    /**
     * Obtiene el valor de la propiedad usuarioId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuarioId() {
        return usuarioId;
    }

    /**
     * Define el valor de la propiedad usuarioId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuarioId(String value) {
        this.usuarioId = value;
    }

}
