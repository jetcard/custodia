/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.comun.dominio;

import java.util.List;
/**
 *
 * @author HH38092
 */
public class MaeDocOtros extends Base{

    private static long serialVersionUID = 1L;
    
    private String cfondoId;
    private String dvalorBV;
    private String documentoID;

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }

    /**
     * @return the cfondoId
     */
    public String getCfondoId() {
        return cfondoId;
    }

    /**
     * @param cfondoId the cfondoId to set
     */
    public void setCfondoId(String cfondoId) {
        this.cfondoId = cfondoId;
    }

    /**
     * @return the dvalorBV
     */
    public String getDvalorBV() {
        return dvalorBV;
    }

    /**
     * @param dvalorBV the dvalorBV to set
     */
    public void setDvalorBV(String dvalorBV) {
        this.dvalorBV = dvalorBV;
    }

    /**
     * @return the documentoID
     */
    public String getDocumentoID() {
        return documentoID;
    }

    /**
     * @param documentoID the documentoID to set
     */
    public void setDocumentoID(String documentoID) {
        this.documentoID = documentoID;
    }

    @Override
    public String toString() {
        return "MaeDocOtros{" + "cfondoId=" + cfondoId + ", dvalorBV=" + dvalorBV + ", documentoID=" + documentoID + '}';
    }
    
}
