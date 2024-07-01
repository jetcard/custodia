
package pop.webcustodia.webservices;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the pop.webcustodia.webservices package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AuthTestResponse_QNAME = new QName("http://webservices.webcustodia.pop/", "authTestResponse");
    private final static QName _AuthTest_QNAME = new QName("http://webservices.webcustodia.pop/", "authTest");
    private final static QName _Exception_QNAME = new QName("http://webservices.webcustodia.pop/", "Exception");
    private final static QName _CambiarUsuarioResponse_QNAME = new QName("http://webservices.webcustodia.pop/", "cambiarUsuarioResponse");
    private final static QName _CambiarUsuario_QNAME = new QName("http://webservices.webcustodia.pop/", "cambiarUsuario");
    private final static QName _ObtenerUsuarioResponse_QNAME = new QName("http://webservices.webcustodia.pop/", "obtenerUsuarioResponse");
    private final static QName _ObtenerUsuario_QNAME = new QName("http://webservices.webcustodia.pop/", "obtenerUsuario");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: pop.webcustodia.webservices
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AuthTest }
     * 
     */
    public AuthTest createAuthTest() {
        return new AuthTest();
    }

    /**
     * Create an instance of {@link AuthTestResponse }
     * 
     */
    public AuthTestResponse createAuthTestResponse() {
        return new AuthTestResponse();
    }

    /**
     * Create an instance of {@link ObtenerUsuario }
     * 
     */
    public ObtenerUsuario createObtenerUsuario() {
        return new ObtenerUsuario();
    }

    /**
     * Create an instance of {@link ObtenerUsuarioResponse }
     * 
     */
    public ObtenerUsuarioResponse createObtenerUsuarioResponse() {
        return new ObtenerUsuarioResponse();
    }

    /**
     * Create an instance of {@link CambiarUsuario }
     * 
     */
    public CambiarUsuario createCambiarUsuario() {
        return new CambiarUsuario();
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link CambiarUsuarioResponse }
     * 
     */
    public CambiarUsuarioResponse createCambiarUsuarioResponse() {
        return new CambiarUsuarioResponse();
    }

    /**
     * Create an instance of {@link MenuEventos }
     * 
     */
    public MenuEventos createMenuEventos() {
        return new MenuEventos();
    }

    /**
     * Create an instance of {@link Menu }
     * 
     */
    public Menu createMenu() {
        return new Menu();
    }

    /**
     * Create an instance of {@link Rol }
     * 
     */
    public Rol createRol() {
        return new Rol();
    }

    /**
     * Create an instance of {@link Correo }
     * 
     */
    public Correo createCorreo() {
        return new Correo();
    }

    /**
     * Create an instance of {@link Usuario }
     * 
     */
    public Usuario createUsuario() {
        return new Usuario();
    }

    /**
     * Create an instance of {@link Telefono }
     * 
     */
    public Telefono createTelefono() {
        return new Telefono();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthTestResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.webcustodia.pop/", name = "authTestResponse")
    public JAXBElement<AuthTestResponse> createAuthTestResponse(AuthTestResponse value) {
        return new JAXBElement<AuthTestResponse>(_AuthTestResponse_QNAME, AuthTestResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthTest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.webcustodia.pop/", name = "authTest")
    public JAXBElement<AuthTest> createAuthTest(AuthTest value) {
        return new JAXBElement<AuthTest>(_AuthTest_QNAME, AuthTest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.webcustodia.pop/", name = "Exception")
    public JAXBElement<Exception> createException(Exception value) {
        return new JAXBElement<Exception>(_Exception_QNAME, Exception.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CambiarUsuarioResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.webcustodia.pop/", name = "cambiarUsuarioResponse")
    public JAXBElement<CambiarUsuarioResponse> createCambiarUsuarioResponse(CambiarUsuarioResponse value) {
        return new JAXBElement<CambiarUsuarioResponse>(_CambiarUsuarioResponse_QNAME, CambiarUsuarioResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CambiarUsuario }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.webcustodia.pop/", name = "cambiarUsuario")
    public JAXBElement<CambiarUsuario> createCambiarUsuario(CambiarUsuario value) {
        return new JAXBElement<CambiarUsuario>(_CambiarUsuario_QNAME, CambiarUsuario.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerUsuarioResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.webcustodia.pop/", name = "obtenerUsuarioResponse")
    public JAXBElement<ObtenerUsuarioResponse> createObtenerUsuarioResponse(ObtenerUsuarioResponse value) {
        return new JAXBElement<ObtenerUsuarioResponse>(_ObtenerUsuarioResponse_QNAME, ObtenerUsuarioResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerUsuario }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.webcustodia.pop/", name = "obtenerUsuario")
    public JAXBElement<ObtenerUsuario> createObtenerUsuario(ObtenerUsuario value) {
        return new JAXBElement<ObtenerUsuario>(_ObtenerUsuario_QNAME, ObtenerUsuario.class, null, value);
    }

}
