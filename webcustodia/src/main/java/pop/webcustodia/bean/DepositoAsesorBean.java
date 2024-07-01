/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.bean;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import pop.comun.dominio.MaeDeposito;
import pop.comun.dominio.MaeFondo;
import pop.comun.dominio.MaeInversion;


import pop.webcustodia.iface.IDepositoServ;
import pop.webcustodia.iface.IFondoServ;
import pop.webcustodia.servicio.DepositoServ;
import pop.webcustodia.servicio.FondoServ;

/**
 *
 * @author Jyoverar
 */
@Named
@ViewScoped
public class DepositoAsesorBean implements Serializable {

    private static final long serialVersionUID = 1L;

    // CobCompromiso por busqueda
    // CobMaeSeguimiento por busqueda
    private MaeInversion maeInversion = new MaeInversion();
    // fondo (busq por fondo)
    private MaeFondo maeFondo = new MaeFondo();
    // deposito para busqueda
    private MaeDeposito maeDeposito = new MaeDeposito();
    // asesor para busqueda
            
    // lista de fondos
    private List<MaeFondo> maeFondoList;
    // lista Depositos
    private List<MaeDeposito> maeDepositoList;
    // lista Depositos

    // servicios de fondos
    private IFondoServ fondoServ = new FondoServ();
    // servicios de depositos
    private IDepositoServ depositoServ = new DepositoServ();
    // servicios de asesor

    
    /**
     * Creates a new instance of CompromisoBean
     */
    public DepositoAsesorBean() {
        maeInversion.setMaeFondo(maeFondo);
        maeDeposito.setMaeInversion(maeInversion);
    }

    public void listarFondos() {
        try {
            maeFondoList = getFondoServ().listarFondos(new MaeFondo());
            System.out.println(" cantidad de Fondos -> " + maeFondoList.size());
        } catch (Exception e) {

        }
    }

    public void buscarDepositosComp(){
        
        try {            
            maeDepositoList = this.getDepositoServ().listarDepositoCompromiso(maeDeposito);
        } catch (Exception ex) {
            Logger.getLogger(DepositoAsesorBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("pop.webcustodia.bean.CompromisoBean.buscarCompromisos()");
    }
        
  

    
    public MaeFondo getMaeFondo() {
        return maeFondo;
    }

    public void setMaeFondo(MaeFondo maeFondo) {
        this.maeFondo = maeFondo;
    }

    public List<MaeFondo> getMaeFondoList() {
        return maeFondoList;
    }

    public void setMaeFondoList(List<MaeFondo> maeFondoList) {
        this.maeFondoList = maeFondoList;
    }
    
    public IFondoServ getFondoServ() {
        return fondoServ;
    }

    public void setFondoServ(IFondoServ fondoServ) {
        this.fondoServ = fondoServ;
    }

    

    
    public MaeInversion getMaeInversion() {
        return maeInversion;
    }

    public void setMaeInversion(MaeInversion maeInversion) {
        this.maeInversion = maeInversion;
    }

    public List<MaeDeposito> getMaeDepositoList() {
        return maeDepositoList;
    }

    public void setMaeDepositoList(List<MaeDeposito> maeDepositoList) {
        this.maeDepositoList = maeDepositoList;
    }

    public IDepositoServ getDepositoServ() {
        return depositoServ;
    }

    public void setDepositoServ(IDepositoServ depositoServ) {
        this.depositoServ = depositoServ;
    }

   

    public MaeDeposito getMaeDeposito() {
        return maeDeposito;
    }

    public void setMaeDeposito(MaeDeposito maeDeposito) {
        this.maeDeposito = maeDeposito;
    }

    
    
}
