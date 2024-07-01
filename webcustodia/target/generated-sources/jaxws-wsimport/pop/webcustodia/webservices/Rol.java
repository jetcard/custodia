
package pop.webcustodia.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para rol complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="rol">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="rolId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="rolNombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rolUsuarioId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rol", propOrder = {
    "rolId",
    "rolNombre",
    "rolUsuarioId"
})
public class Rol {

    protected int rolId;
    protected String rolNombre;
    protected int rolUsuarioId;

    /**
     * Obtiene el valor de la propiedad rolId.
     * 
     */
    public int getRolId() {
        return rolId;
    }

    /**
     * Define el valor de la propiedad rolId.
     * 
     */
    public void setRolId(int value) {
        this.rolId = value;
    }

    /**
     * Obtiene el valor de la propiedad rolNombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRolNombre() {
        return rolNombre;
    }

    /**
     * Define el valor de la propiedad rolNombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRolNombre(String value) {
        this.rolNombre = value;
    }

    /**
     * Obtiene el valor de la propiedad rolUsuarioId.
     * 
     */
    public int getRolUsuarioId() {
        return rolUsuarioId;
    }

    /**
     * Define el valor de la propiedad rolUsuarioId.
     * 
     */
    public void setRolUsuarioId(int value) {
        this.rolUsuarioId = value;
    }

}
