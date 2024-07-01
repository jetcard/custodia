/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.comun.dominio;

import java.util.List;



public class MaeFechaPor extends Base{

    private static final long serialVersionUID = 1L;
    
    private String cMaeFechaPorId;
    private String dMaeFechaPorId;
    private String aDireccion;
    
    private List<MaeInversion> maeInversionList;
    private List<MaeBanco> maeBancoList;
    
       
    public MaeFechaPor() {
    }

    public MaeFechaPor(String cMaeFechaPorId) {
        this.cMaeFechaPorId = cMaeFechaPorId;
    }

    public String getCMaeFechaPorId() {
        return cMaeFechaPorId;
    }

    public void setCMaeFechaPorId(String cFondoId) {
        this.cMaeFechaPorId = cMaeFechaPorId;
    }

    public String getDMaeFechaPorId() {
        return dMaeFechaPorId;
    }

    public void setDMaeFechaPorId(String dMaeFechaPorId) {
        this.dMaeFechaPorId = dMaeFechaPorId;
    }

    public String getADireccion() {
        return aDireccion;
    }

    public void setADireccion(String aDireccion) {
        this.aDireccion = aDireccion;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cMaeFechaPorId != null ? cMaeFechaPorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaeFechaPor)) {
            return false;
        }
        MaeFechaPor other = (MaeFechaPor) object;
        if ((this.cMaeFechaPorId == null && other.cMaeFechaPorId != null) || (this.cMaeFechaPorId != null && !this.cMaeFechaPorId.equals(other.cMaeFechaPorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.popular.web.webcustodia.entities.MaeFechaPor[ cMaeFechaPorId=" + cMaeFechaPorId + " ]";
    }

    /**
     * @return the maeInversionList
     */
    public List<MaeInversion> getMaeInversionList() {
        return maeInversionList;
    }

    /**
     * @param maeInversionList the maeInversionList to set
     */
    public void setMaeInversionList(List<MaeInversion> maeInversionList) {
        this.maeInversionList = maeInversionList;
    }

    public List<MaeBanco> getMaeBancoList() {
        return maeBancoList;
    }

    public void setMaeBancoList(List<MaeBanco> maeBancoList) {
        this.maeBancoList = maeBancoList;
    }
    
}
