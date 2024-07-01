
package pop.webcustodia.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para menuEventos complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="menuEventos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cmenu" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="cmenuId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="menuA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="menuAUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="menus" type="{http://webservices.webcustodia.pop/}menu" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "menuEventos", propOrder = {
    "cmenu",
    "cmenuId",
    "menuA",
    "menuAUrl",
    "menus"
})
public class MenuEventos {

    protected int cmenu;
    protected int cmenuId;
    protected String menuA;
    protected String menuAUrl;
    protected Menu menus;

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
     * Obtiene el valor de la propiedad menus.
     * 
     * @return
     *     possible object is
     *     {@link Menu }
     *     
     */
    public Menu getMenus() {
        return menus;
    }

    /**
     * Define el valor de la propiedad menus.
     * 
     * @param value
     *     allowed object is
     *     {@link Menu }
     *     
     */
    public void setMenus(Menu value) {
        this.menus = value;
    }

}
