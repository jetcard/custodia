
package pop.webcustodia.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para telefono complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="telefono">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="telId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="telNumero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="telTipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "telefono", propOrder = {
    "telId",
    "telNumero",
    "telTipo"
})
public class Telefono {

    protected int telId;
    protected String telNumero;
    protected String telTipo;

    /**
     * Obtiene el valor de la propiedad telId.
     * 
     */
    public int getTelId() {
        return telId;
    }

    /**
     * Define el valor de la propiedad telId.
     * 
     */
    public void setTelId(int value) {
        this.telId = value;
    }

    /**
     * Obtiene el valor de la propiedad telNumero.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelNumero() {
        return telNumero;
    }

    /**
     * Define el valor de la propiedad telNumero.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelNumero(String value) {
        this.telNumero = value;
    }

    /**
     * Obtiene el valor de la propiedad telTipo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelTipo() {
        return telTipo;
    }

    /**
     * Define el valor de la propiedad telTipo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelTipo(String value) {
        this.telTipo = value;
    }

}
