/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.bean;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException; 
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.print.DocFlavor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceRef;
import javax.xml.ws.handler.MessageContext;
import pop.webcustodia.filter.SessionUtils;
import pop.webcustodia.properties.MangProperties;
//import org.apache.cxf.configuration.security.AuthorizationPolicy;
//import pop.webcustodia.filter.SessionUtils;
//import pop.webcustodia.ws.Menu;
//import pop.webcustodia.ws.SeguridadWS;
//import pop.webcustodia.ws.SeguridadWS_Service;
//import pop.webcustodia.ws.Usuario;

import pop.webcustodia.webservices.Menu;
import pop.webcustodia.webservices.SeguridadWS;
import pop.webcustodia.webservices.SeguridadWS_Service;
import pop.webcustodia.webservices.Usuario;


/**
 *
 * @author Jyoverar
 */
@Named
@SessionScoped
public class LoginBean implements Serializable {
    @WebServiceRef
    private SeguridadWS_Service service;
    private static final long serialVersionUID = 1L;
    // usuario web service
    private Usuario usuario = new Usuario();
    private Usuario usuarioSession = new Usuario();
    // flat de msj de error
    private String flgError;

    private String username;

    private boolean fnavBar;

    private int nllamada;
    private String numAsesor;
    private String mailAsesor;
    private String lastName;
    private String name;
    private String urlServidorWeb;
    private String pageTitle;
	
    // UsuarioBean oUsuarioBean= new UsuarioBean();
    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
        //System.out.println("pop.webcustodia.bean.LoginBean.<init>()");
        flgError = "";
    }

    //validate login
    public String validateUsernamePassword() {

        try { // Call Web Service Operation      

			//recuperando la ruta actual del web service
            MangProperties properties=new MangProperties();
            setUrlServidorWeb(properties.getUrl_servidorweb());
            
            System.out.println("   pop.webcustodia.bean.LoginBean.validateUsernamePassword()");

//            System.out.println("   pop.webcustodia.bean.LoginBean.validateUsernamePassword()");
//            UsuariosPort port = service.getUsuariosPortSoap11();
//            
//            ((BindingProvider) port).getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "user1");
//            ((BindingProvider) port).getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "secret1");
//                        
//            // TODO initialize WS operation arguments here
//            GetUsuarioRequest getUsuarioRequest = new GetUsuarioRequest();
//            usuario.setUsuarioid(0);
//            getUsuarioRequest.setUsuario(usuario);
//            
//            // TODO process result here
//            GetUsuarioResponse result = port.getUsuario(getUsuarioRequest);
//            usuario.setUsuarioid(result.getUsuario().getUsuarioid());

            try {
//                // TODO code application logic here
//                InetAddress ip = InetAddress.getlocalhost();
//                NetworkInterface netInt = NetworkInterface.getByInetAddress(ip);
//                byte[] macAdd = netInt.getHardwareAddress();
//                //
//                StringBuilder macAddBuil = new StringBuilder();
//                //
//                for (int macAdByInd = 0; macAdByInd < macAdd.length; macAdByInd++) {
//                    String macAddByHex = String.format("%02X", macAdd[macAdByInd]);
//                    macAddBuil.append(macAddByHex);
//                    if (macAdByInd != macAdd.length - 1) {
//                        macAddBuil.append(":");
//                    }
//                }
//
//                System.out.println("MAC ADDRESS -->" + macAddBuil.toString());

                HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

                String ipAddress = request.getHeader("X-FORWARDED-FOR");
                if (ipAddress == null) {
                    ipAddress = request.getRemoteAddr();  // IP
                }
                InetAddress addr = InetAddress.getByName(ipAddress);  // DOMAIN NAME from IP
                String host = addr.getHostName();
                System.out.println("   MAC ipAddress -->" + ipAddress);
                System.out.println("   MAC host -->" + host);

                String userAgent = request.getHeader("User-Agent");
                String os = "";
                String browser = "";
                System.out.println("   USERAGENT -> " + userAgent);
                if (userAgent.toLowerCase().indexOf("windows") >= 0) {
                    os = "windows";
                } else if (userAgent.toLowerCase().indexOf("android") >= 0) {
                    os = "android";
                } else if (userAgent.toLowerCase().indexOf("iphone") >= 0) {
                    os = "iphone";
                } else if (userAgent.toLowerCase().indexOf("x11") >= 0) {
                    os = "Unix";
                } else {
                    os = "Desconocido";
                }
                System.out.println("   OS -> " + os);
                String  user            =   userAgent.toLowerCase();
                if (user.contains("msie")) {
                    String substring = userAgent.substring(userAgent.indexOf("MSIE")).split(";")[0];
                    browser = substring.split(" ")[0].replace("MSIE", "IE") + "-" + substring.split(" ")[1];
                } else if (user.contains("safari") && user.contains("version")) {
                    browser = (userAgent.substring(userAgent.indexOf("Safari")).split(" ")[0]).split("/")[0] + "-" + (userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
                } else if (user.contains("opr") || user.contains("opera")) {
                    if (user.contains("opera")) {
                        browser = (userAgent.substring(userAgent.indexOf("Opera")).split(" ")[0]).split("/")[0] + "-" + (userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
                    } else if (user.contains("opr")) {
                        browser = ((userAgent.substring(userAgent.indexOf("OPR")).split(" ")[0]).replace("/", "-")).replace("OPR", "Opera");
                    }
                } else if (user.contains("chrome")) {
                    browser = (userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
                } else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1) || (user.indexOf("mozilla/4.7") != -1) || (user.indexOf("mozilla/4.78") != -1) || (user.indexOf("mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1)) {
                    //browser=(userAgent.substring(userAgent.indexOf("MSIE")).split(" ")[0]).replace("/", "-");
                    browser = "Netscape-?";

                } else if (user.contains("firefox")) {
                    browser = (userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
                } else if (user.contains("rv")) {
                    browser = "IE-" + user.substring(user.indexOf("rv") + 3, user.indexOf(")"));
                } else {
                    browser = "UnKnown, More-Info: " + userAgent;
                }
                System.out.println("   BROWSER -> " + browser);

            } catch (UnknownHostException ex) {
                Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (SocketException ex) {
//                Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            }

            // nuevo ws
            try { // Call Web Service Operation
                SeguridadWS port = service.getSeguridadWSPort();
                Map<String, Object> reqMap = ((BindingProvider) port).getRequestContext();
                Map<String, List<String>> header = new HashMap<>();
                header.put("usernamews", Collections.singletonList("wsValUseEVA"));
                header.put("passwordws", Collections.singletonList("1055c7dc7cc40630f99875b72082ce33243951396b91081685ccdab3c9114d135739561abc38291be26c25b8bc1490e07036bf8512e16a27ea58ee0b12b10f56"));
                reqMap.put(MessageContext.HTTP_REQUEST_HEADERS, header);
                //AuthorizationPolicy authzPolicy = new AuthorizationPolicy();
                //authzPolicy.setUserName("jhon");
                //authzPolicy.setPassword("12345");
                //((BindingProvider) port).getRequestContext().put(AuthorizationPolicy.class.getName(), authzPolicy);
                //((BindingProvider) port).getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "jhon");
                //((BindingProvider) port).getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "12345");

                //((BindingProvider) port).getRequestContext().put("usernamews", "jhon2");
                // TODO initialize WS operation arguments here
                //Usuario oUsuario = new Usuario();
                // TODO process result here
                usuarioSession = port.obtenerUsuario(usuario);
                System.out.println(" UsuarioId --> " + usuarioSession.getUsuarioId());
                System.out.println(" Nom     --> " + usuarioSession.getPerNom());
                System.out.println(" ApePat  -->" + usuarioSession.getPerApePat());
                System.out.println(" ApeMat  -->" + usuarioSession.getPerApeMat());
            } catch (Exception ex) {
                // TODO handle custom exceptions here
                System.out.println(ex);
            }

//            System.out.println("Result = "+result.getUsuario().getUsuarioid());
        } catch (Exception ex) {
            // TODO handle custom exceptions here
            System.out.println(ex);
        }

        //if (usuario.getUsuarioid()!=0) {
        if (usuarioSession.isEstado()) {
            HttpSession session = SessionUtils.getSession();
            //oUsuarioBean.setUserId(usuario.getUsuario());

            session.setAttribute("username", usuarioSession.getUsuarioId().toUpperCase().trim());
            username = usuarioSession.getUsuarioId().toUpperCase().trim();

            nllamada = 0 ;//usuarioSession.getNumLlamada();
            numAsesor = "99999998"; //usuarioSession.getTelefono().getTelNumero();
            mailAsesor = "rcruz@popular-safi.com";// usuarioSession.getCorreo().getCorDesc();
            lastName = usuarioSession.getPerApePat() + " " + usuarioSession.getPerApeMat();
            name = usuarioSession.getPerNom();

         //   System.out.println( "[" + username + "] --> " + usuarioSession.getNumLlamada());
           // System.out.println( "[" + username + "] --> " + usuarioSession.getTelefono().getTelNumero());
           // System.out.println( "[" + username + "] --> " + usuarioSession.getCorreo().getCorDesc());

            for (Menu arg : usuarioSession.getMenu()) {
                System.out.println("[" + username + "] ---- " + arg.getMenuA());
                for (Menu argb : arg.getOMenuList()) {
                    System.out.println("[" + username + "] ------------ " + argb.getMenuA());
                }
            }

            session.setAttribute("numAdviser", numAsesor);
            session.setAttribute("emailAdviser", mailAsesor);
            session.setAttribute("lastName", lastName);
            session.setAttribute("name", name);
            session.setAttribute("menulist", usuarioSession.getMenu());
            session.setAttribute("opcionespermisomenulist", usuarioSession.getOpcionesPermisosmenu());
            
            if (usuarioSession.getBcontrasenia().equals("S")) {
                return "/pages/sistema/cambiouser?faces-redirect=true";
            } else {
                return "dashboard?faces-redirect=true";
            }

        } else {
            username = "";
            setFlgError("E");
            FacesContext.getCurrentInstance().addMessage(
                    "btnIngresar",
                    new FacesMessage("Error de usuario o contraseña"));
            return "login";
        }

    }

    //logout event, invalidate session
    public String logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "login?faces-redirect=true";
    }

    public void cambiarNavBar() {
        System.out.println("pop.webcustodia.bean.LoginBean.cambiarNavBar()");
        if (isFnavBar()) {
            setFnavBar(false);
        } else {
            setFnavBar(true);
        }

    }
    
    public void limpiaTitle() {
        System.out.println("pop.webcustodia.bean.LoginBean.limpiaTitle()");
        pageTitle = null;

    }    

//    public Usuario getUsuario() {
//        return usuario;
//    }
//
//    public void setUsuario(Usuario usuario) {
//        this.usuario = usuario;
//    }
    public String getFlgError() {
        return flgError;
    }

    public void setFlgError(String flgError) {
        this.flgError = flgError;
    }

    public String getUsername() {
        return username.toUpperCase();
    }

    public void setUsername(String username) {
        this.username = username.toUpperCase();
    }

    public boolean isFnavBar() {
        return fnavBar;
    }

    public void setFnavBar(boolean fnavBar) {
        this.fnavBar = fnavBar;
    }

    public int getNllamada() {
        return nllamada;
    }

    public void setNllamada(int nllamada) {
        this.nllamada = nllamada;
    }

    public String getNumAsesor() {
        return numAsesor;
    }

    public void setNumAsesor(String numAsesor) {
        this.numAsesor = numAsesor;
    }

    public String getMailAsesor() {
        return mailAsesor;
    }

    public void setMailAsesor(String mailAsesor) {
        this.mailAsesor = mailAsesor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuarioSession() {
        return usuarioSession;
    }

    public void setUsuarioSession(Usuario usuarioSession) {
        this.usuarioSession = usuarioSession;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
	
	 /**
     * @return the urlServidorWeb
     */
    public String getUrlServidorWeb() {
        return urlServidorWeb;
    }

    /**
     * @param urlServidorWeb the urlServidorWeb to set
     */
    public void setUrlServidorWeb(String urlServidorWeb) {
        this.urlServidorWeb = urlServidorWeb;
    }    

    /**
     * @return the pageTitle
     */
    public String getPageTitle() {
        return pageTitle;
    }

    /**
     * @param pageTitle the pageTitle to set
     */
    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

}
