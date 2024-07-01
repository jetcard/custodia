/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.comun.dominio;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Jyoverar
 */
public class CobSeguimiento extends Base{
    
    private CobMaeSeguimiento cobMaeSeguimiento;
    private int ccobSeguimientoId;
    private Date ffecIni;
    private Date ffecFin;
    private String eestadoId;

    private ArrayList<CobSeguimientoDet> cobSeguimientoDetList;
    
    public CobMaeSeguimiento getCobMaeSeguimiento() {
        return cobMaeSeguimiento;
    }

    public void setCobMaeSeguimiento(CobMaeSeguimiento cobMaeSeguimiento) {
        this.cobMaeSeguimiento = cobMaeSeguimiento;
    }

    public int getCcobSeguimientoId() {
        return ccobSeguimientoId;
    }

    public void setCcobSeguimientoId(int ccobSeguimientoId) {
        this.ccobSeguimientoId = ccobSeguimientoId;
    }

    public Date getFfecIni() {
        return ffecIni;
    }

    public void setFfecIni(Date ffecIni) {
        this.ffecIni = ffecIni;
    }

    public Date getFfecFin() {
        return ffecFin;
    }

    public void setFfecFin(Date ffecFin) {
        this.ffecFin = ffecFin;
    }

    public String getEestadoId() {
        return eestadoId;
    }

    public void setEestadoId(String eestadoId) {
        this.eestadoId = eestadoId;
    }

    public ArrayList<CobSeguimientoDet> getCobSeguimientoDetList() {
        return cobSeguimientoDetList;
    }

    public void setCobSeguimientoDetList(ArrayList<CobSeguimientoDet> cobSeguimientoDetList) {
        this.cobSeguimientoDetList = cobSeguimientoDetList;
    }
    
    
}
