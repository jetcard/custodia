
package pop.webcustodia.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para cambiarUsuario complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="cambiarUsuario">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="oUsuarioB" type="{http://webservices.webcustodia.pop/}usuario" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cambiarUsuario", propOrder = {
    "oUsuarioB"
})
public class CambiarUsuario {

    protected Usuario oUsuarioB;

    /**
     * Obtiene el valor de la propiedad oUsuarioB.
     * 
     * @return
     *     possible object is
     *     {@link Usuario }
     *     
     */
    public Usuario getOUsuarioB() {
        return oUsuarioB;
    }

    /**
     * Define el valor de la propiedad oUsuarioB.
     * 
     * @param value
     *     allowed object is
     *     {@link Usuario }
     *     
     */
    public void setOUsuarioB(Usuario value) {
        this.oUsuarioB = value;
    }

}
