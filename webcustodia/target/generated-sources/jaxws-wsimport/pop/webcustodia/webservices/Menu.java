
package pop.webcustodia.webservices;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para menu complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="menu">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cmenu" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="cmenuId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="cmenuPadre" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="listaEventos" type="{http://webservices.webcustodia.pop/}menuEventos" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="menuA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="menuAE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="menuAUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="simbolo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oMenuList" type="{http://webservices.webcustodia.pop/}menu" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="oMenuList2" type="{http://webservices.webcustodia.pop/}menu" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "menu", propOrder = {
    "cmenu",
    "cmenuId",
    "cmenuPadre",
    "listaEventos",
    "menuA",
    "menuAE",
    "menuAUrl",
    "simbolo",
    "oMenuList",
    "oMenuList2"
})
public class Menu {

    protected int cmenu;
    protected int cmenuId;
    protected int cmenuPadre;
    @XmlElement(nillable = true)
    protected List<MenuEventos> listaEventos;
    protected String menuA;
    protected String menuAE;
    protected String menuAUrl;
    protected String simbolo;
    @XmlElement(nillable = true)
    protected List<Menu> oMenuList;
    @XmlElement(nillable = true)
    protected List<Menu> oMenuList2;

    /**
     * Obtiene el valor de la propiedad cmenu.
     * 
     */
    public int getCmenu() {
        return cmenu;
    }

    /**
     * Define el valor de la propiedad cmenu.
     * 
     */
    public void setCmenu(int value) {
        this.cmenu = value;
    }

    /**
     * Obtiene el valor de la propiedad cmenuId.
     * 
     */
    public int getCmenuId() {
        return cmenuId;
    }

    /**
     * Define el valor de la propiedad cmenuId.
     * 
     */
    public void setCmenuId(int value) {
        this.cmenuId = value;
    }

    /**
     * Obtiene el valor de la propiedad cmenuPadre.
     * 
     */
    public int getCmenuPadre() {
        return cmenuPadre;
    }

    /**
     * Define el valor de la propiedad cmenuPadre.
     * 
     */
    public void setCmenuPadre(int value) {
        this.cmenuPadre = value;
    }

    /**
     * Gets the value of the listaEventos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaEventos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaEventos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MenuEventos }
     * 
     * 
     */
    public List<MenuEventos> getListaEventos() {
        if (listaEventos == null) {
            listaEventos = new ArrayList<MenuEventos>();
        }
        return this.listaEventos;
    }

    /**
     * Obtiene el valor de la propiedad menuA.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMenuA() {
        return menuA;
    }

    /**
     * Define el valor de la propiedad menuA.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMenuA(String value) {
        this.menuA = value;
    }

    /**
     * Obtiene el valor de la propiedad menuAE.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMenuAE() {
        return menuAE;
    }

    /**
     * Define el valor de la propiedad menuAE.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMenuAE(String value) {
        this.menuAE = value;
    }

    /**
     * Obtiene el valor de la propiedad menuAUrl.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMenuAUrl() {
        return menuAUrl;
    }

    /**
     * Define el valor de la propiedad menuAUrl.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMenuAUrl(String value) {
        this.menuAUrl = value;
    }

    /**
     * Obtiene el valor de la propiedad simbolo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSimbolo() {
        return simbolo;
    }

    /**
     * Define el valor de la propiedad simbolo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSimbolo(String value) {
        this.simbolo = value;
    }

    /**
     * Gets the value of the oMenuList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the oMenuList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOMenuList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Menu }
     * 
     * 
     */
    public List<Menu> getOMenuList() {
        if (oMenuList == null) {
            oMenuList = new ArrayList<Menu>();
        }
        return this.oMenuList;
    }

    /**
     * Gets the value of the oMenuList2 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the oMenuList2 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOMenuList2().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Menu }
     * 
     * 
     */
    public List<Menu> getOMenuList2() {
        if (oMenuList2 == null) {
            oMenuList2 = new ArrayList<Menu>();
        }
        return this.oMenuList2;
    }

}
