/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.bean;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.ContextCallback;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import org.omnifaces.util.Components;

/**
 *
 * @author Jyoverar
 */
@Named
@ViewScoped
public class CartaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private UIComponent found;

    /**
     * Creates a new instance of CartaBean
     */
    private String cadenaHTML;

    public CartaBean() {
        cadenaHTML = "<p>\n"
                + "<img style=\"width: 100px; float: right;\" src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAATcAAACZCAYAAABKQ30YAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyJpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMy1jMDExIDY2LjE0NTY2MSwgMjAxMi8wMi8wNi0xNDo1NjoyNyAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENTNiAoV2luZG93cykiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6ODQ4REU3QUY2NUZDMTFFNDgzRkNBODNEQ0RBODE1RDQiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6ODQ4REU3QjA2NUZDMTFFNDgzRkNBODNEQ0RBODE1RDQiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDo4NDhERTdBRDY1RkMxMUU0ODNGQ0E4M0RDREE4MTVENCIgc3RSZWY6ZG9jdW1lbnRJRD0ieG1wLmRpZDo4NDhERTdBRTY1RkMxMUU0ODNGQ0E4M0RDREE4MTVENCIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/PikzQWsAAB93SURBVHja7F0HlBXF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\" data-filename=\"logo-teamsoft.png\"/>\n"
                + "</p>\n"
                + "<p>\n"
                + "<br/>\n"
                + "</p>\n"
                + "<p><br/></p><p><br/></p>\n"
                + "<h4><u>PLAZO - 24 HORAS</u><br/></h4><p><br/></p><p>$NOMBRE</p><p>$DIRECCION</p><p>Código: $CODIGO</p><p>Fecha: $FECHA</p><p><br/></p><h4>CUOTA VENCIDA(S): $MES $ANIO</h4><p><br/></p><p>Estimado(a) cliente:</p><p><br/></p><p align=\"justify\">Considerando las&nbsp; comunicaciones realizadas y previos avisos para que usted cancele la deuda&nbsp;&nbsp; anteriormente referida, que&nbsp; hasta el momento no ha sido regularizada y encontrándose en&nbsp; MORA. <br/></p><p align=\"justify\">Nos vemos en la obligación de indicarle que en el plazo de <u><b>24 HORAS</b></u> de recibida la presente, actuaremos según lo estipulado en el Contrato de Financiamiento cele/brado con el <b>$FONDO</b>; que en su debida oportunidad Ud. suscribió.</p><p align=\"justify\">Sin perjuicio de lo anteriormente señalado y como es política de nuestra empresa, lo esperamos dentro del plazo indicado en la presente carta o comunicándose a los teléfonos suscritos al pie de la presente; para poder llegar a un acuerdo y encontrar la mejor solución a su problema de pago.</p><p align=\"justify\"><br/></p><p align=\"justify\"><br/></p><p align=\"justify\"><br/></p><p align=\"justify\"><br/></p><p align=\"center\">Si al recibir la presente, Ud. ya ha regularizado su adeudo, agradeceremos omitir su contenido.</p><p align=\"center\">El portador de la presente no está autorizado a recibir dinero.</p><p align=\"center\">Atención: De lunes a viernes de 09:00 am 12:45 pm y de 02:00 pm a 05:45 pm (PREVIA CITA)<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Av. Nicolás de Piérola N° 938 Oficina 306 – Lima <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Teléfonos: (01) 6277001 – (01) 6277002 – 652-3163<br/></p><p></p>";
        cadenaHTML="";
    }

    public void grabarCarta(){
        
        System.out.println("cadena "  + cadenaHTML);

//        HttpServletRequest origRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
//
//        Enumeration headerNames = origRequest.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String headerName = (String) headerNames.nextElement();
//            System.out.println("Header Name - " + headerName + ", Value - " + origRequest.getHeader(headerName));
//        }
//
//        FacesContext context = FacesContext.getCurrentInstance();
//        ResponseWriter originalWriter = context.getResponseWriter();
//        StringWriter writer = new StringWriter();
//        try {
//            //context.setResponseWriter(context.getRenderKit().createResponseWriter(writer, "text/html", "UTF-8"));
//            System.out.println("output a " + originalWriter.getContentType());
//            System.out.println("output a " + originalWriter.getCharacterEncoding());
//
//            originalWriter.cloneWithWriter(writer);
//
//            System.out.println("output a " + writer.getBuffer());
//            //component.encodeAll(context);
//            //found.encodeAll(context);
//        } finally {
//            if (originalWriter != null) {
//                //context.setResponseWriter(originalWriter);
//            }
//        }
//        String output = writer.toString();
//        System.out.println("output b " + output);


//        doFind(FacesContext.getCurrentInstance(), "formDatosComunTel");
//        System.out.println("pop.webcustodia.bean.CartaBean.grabarCarta()");

//        UIComponent panel = FacesContext.getCurrentInstance().getViewRoot().findComponent("ttttt");
//        try {
//            String encodedPanel = Components.encodeHtml(panel);
//            System.out.println("encodedPanel  - " + encodedPanel);
//        } catch (IOException ex) {
//            Logger.getLogger(CartaBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
//        String mailHtml="";
//        try {
//            mailHtml = Components.encodeHtml(found);
//        } catch (IOException ex) {
//            Logger.getLogger(CartaBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        System.out.println("mailHtml  - " + mailHtml);

    }

    private void doFind(FacesContext context, String clientId) {
//        FacesContext.getCurrentInstance().getViewRoot().invokeOnComponent(context, clientId, new ContextCallback() {
//            @Override
//            public void invokeContextCallback(FacesContext context,
//                    UIComponent component) {
//                found = component;
//            }
//        });
        found =  FacesContext.getCurrentInstance().getViewRoot().findComponent(clientId);
    }

    public String getCadenaHTML() {
        return cadenaHTML;
    }

    public void setCadenaHTML(String cadenaHTML) {
        this.cadenaHTML = cadenaHTML;
    }

}
