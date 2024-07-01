/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import pop.comun.dominio.MaeBanco;
import pop.comun.dominio.MaeBancoCuenta;
import pop.comun.dominio.MaeDeposito;
import pop.comun.dominio.MaeFondo;

import pop.webcustodia.iface.IFondoServ;

import pop.webcustodia.servicio.FondoServ;

/**
 *
 * @author Jyoverar
 */
@Named
@ViewScoped
public class ProcesarDepositoBean implements Serializable {

    private static final long serialVersionUID = 1L;
    // fondo (busq por fondo)
    private MaeFondo maeFondo = new MaeFondo();
    // banco vista
    private MaeBanco maeBanco = new MaeBanco();
    // cuenta vista
    private MaeBancoCuenta maeCuenta = new MaeBancoCuenta();

    // lista de fondos
    private List<MaeFondo> maeFondoList;
    // lista de bancos
    private List<MaeBanco> maeBancoList;
    // lista de cuenta bancos
    private List<MaeBancoCuenta> maeCuentaList;
    // lista de depositos a mostrar
    private List<MaeDeposito> maeDepositoList = new ArrayList<>();

    // servicios de fondos
    private IFondoServ fondoServ = new FondoServ();
 
    
    public void listarFondos() {
        try {
            maeFondoList = getFondoServ().listarFondos(new MaeFondo());
            System.out.println(" cantidad de Fondos -> " + maeFondoList.size());
        } catch (Exception e) {

        }
    }




    public void buscarDepositosPend() {
        try {


        } catch (Exception e) {
            System.out.println("pop.webcustodia.bean.InversionBean.buscarDeudores()");
        }
    }

    public MaeFondo getMaeFondo() {
        return maeFondo;
    }

    public void setMaeFondo(MaeFondo maeFondo) {
        this.maeFondo = maeFondo;
    }

    public MaeBanco getMaeBanco() {
        return maeBanco;
    }

    public void setMaeBanco(MaeBanco maeBanco) {
        this.maeBanco = maeBanco;
    }

    public MaeBancoCuenta getMaeCuenta() {
        return maeCuenta;
    }

    public void setMaeCuenta(MaeBancoCuenta maeCuenta) {
        this.maeCuenta = maeCuenta;
    }

    public List<MaeFondo> getMaeFondoList() {
        return maeFondoList;
    }

    public void setMaeFondoList(List<MaeFondo> maeFondoList) {
        this.maeFondoList = maeFondoList;
    }

    public List<MaeBanco> getMaeBancoList() {
        return maeBancoList;
    }

    public void setMaeBancoList(List<MaeBanco> maeBancoList) {
        this.maeBancoList = maeBancoList;
    }

    public List<MaeBancoCuenta> getMaeCuentaList() {
        return maeCuentaList;
    }

    public void setMaeCuentaList(List<MaeBancoCuenta> maeCuentaList) {
        this.maeCuentaList = maeCuentaList;
    }

    public IFondoServ getFondoServ() {
        return fondoServ;
    }

    public void setFondoServ(IFondoServ fondoServ) {
        this.fondoServ = fondoServ;
    }

   
    public List<MaeDeposito> getMaeDepositoList() {
        return maeDepositoList;
    }

    public void setMaeDepositoList(List<MaeDeposito> maeDepositoList) {
        this.maeDepositoList = maeDepositoList;
    }
    
    
}
