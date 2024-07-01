
package pop.webcustodia.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para correo complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="correo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="corDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="corId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="corTipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "correo", propOrder = {
    "corDesc",
    "corId",
    "corTipo"
})
public class Correo {

    protected String corDesc;
    protected int corId;
    protected String corTipo;

    /**
     * Obtiene el valor de la propiedad corDesc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorDesc() {
        return corDesc;
    }

    /**
     * Define el valor de la propiedad corDesc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorDesc(String value) {
        this.corDesc = value;
    }

    /**
     * Obtiene el valor de la propiedad corId.
     * 
     */
    public int getCorId() {
        return corId;
    }

    /**
     * Define el valor de la propiedad corId.
     * 
     */
    public void setCorId(int value) {
        this.corId = value;
    }

    /**
     * Obtiene el valor de la propiedad corTipo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorTipo() {
        return corTipo;
    }

    /**
     * Define el valor de la propiedad corTipo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorTipo(String value) {
        this.corTipo = value;
    }

}
