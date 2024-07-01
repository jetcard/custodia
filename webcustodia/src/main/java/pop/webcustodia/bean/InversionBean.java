/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import pop.comun.dominio.CobTablas;

import pop.comun.dominio.MaeFondo;
import pop.comun.dominio.MaeInmueble;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MaePersona;
import pop.comun.dominio.MaeUbigeo;
import pop.webcustodia.filter.SessionUtils;

import pop.webcustodia.iface.ITablasServ;
import pop.webcustodia.iface.IUbigeoServ;

import pop.webcustodia.servicio.InversionServ;

import pop.webcustodia.servicio.TablasServ;
import pop.webcustodia.servicio.UbigeoServ;

/**
 *
 * @author Jyoverar
 */
@Named
@ViewScoped 
public class InversionBean implements Serializable {

    private static final long serialVersionUID = 1L;
    // clases
    private MaeInversion maeInversion = new MaeInversion();
    private MaeFondo maeFondo = new MaeFondo();
    private MaePersona maePersona = new MaePersona();
    private MaeInmueble maeInmueble = new MaeInmueble();
    private MaeUbigeo maeUbigeo = new MaeUbigeo();
    private MaeUbigeo maeUbigeoP = new MaeUbigeo();
    private MaeUbigeo maeUbigeoD = new MaeUbigeo();

    private CobTablas cobTablasAccion = new CobTablas();
    private CobTablas cobTablasFamilia = new CobTablas();
    
    // para enviar a grabar B
    private CobTablas cobTablasAccionB = new CobTablas();
    private CobTablas cobTablasFamiliaB = new CobTablas();
    //private CobSeguimientoDet cobSeguimientoDet = new CobSeguimientoDet();
    // calculos
    private String cuoPendiente = "0.00";
    private Date fechaCorte;
    private String cuoPendienteFC = "0.00";
    private String cuoPendienteOtros = "0.00";
    private String cuoPendienteTotal = "0.00";
    private String cuoPendienteDepMes = "0.00";
    // listas
    private List<MaeInversion> maeInversionList;
    private List<MaeUbigeo> ubigeoProv;
    private List<MaeUbigeo> ubigeoDist;

    private List<CobTablas> cobTablasAccionList;
    private List<CobTablas> cobTablasFamiliaList;

    private Number montoCuota;
    // servicios
    private InversionServ inversionServ = new InversionServ();
    private IUbigeoServ iUbigeoServ = new UbigeoServ();

    private ITablasServ tablasServ = new TablasServ();

    
    private MaeInversion maeInversionCd = new MaeInversion();

//    private CobMaeSeguimiento cobMaeSeguimiento = new CobMaeSeguimiento();
    
    
    private boolean contactado;

    /**
     * Creates a new instance of InversionBean
     */
    public InversionBean() {
        maeInversion.setMaeFondo(maeFondo);
        maeInversion.setcPersonaId(maePersona);
        maeInversion.setcPersonaId(maePersona);
        maeInversion.setMaeInmueble(maeInmueble);
    }

    public void buscarDeudores() {
        try {

            maeUbigeo.setCUbigeoId(maeUbigeoD.getCUbigeoId());
            maeUbigeo.setCUbigeoPad(maeUbigeoP.getCUbigeoId());

            //System.out.println("Ubigeo -> Distrito " + maeUbigeoD.getCUbigeoId());
            //System.out.println("Ubigeo -> Provincia " + maeUbigeoP.getCUbigeoId());

            maeInmueble.setMaeUbigeo(maeUbigeo);
            maeInversionList = getInversionServ().listarDeudor(maeInversion);
            //System.out.println("pop.webcustodia.bean.InversionBean.<init>()");
            //System.out.println("   Lista de Depositos = " + maeInversionList.size());

        } catch (Exception e) {
            System.out.println("pop.webcustodia.bean.InversionBean.buscarDeudores()");
        }
    }

    public void obtenerCabDet(MaeInversion oInversion) {
        try {
            System.out.println("["+SessionUtils.getUserName()+"] "+" InversionBean - obtenerCabDet - " + 
                    oInversion.getcMaeInversionId() + " - " + oInversion.getCInversion().trim() );
            
            maeInversionCd.setMaeFondo(oInversion.getMaeFondo());
            maeInversionCd.setcMaeInversionId(oInversion.getcMaeInversionId());
            maeInversionCd.setcTipoInv(oInversion.getcTipoInv());
            maeInversionCd.setCInversion(oInversion.getCInversion());
            maeInversionCd.setcPersonaId(oInversion.getcPersonaId());
           // maeInversionCd.setNCuotasAtrasadas(oInversion.getNCuotasAtrasadas());
            maeInversionCd.setcInversionId(oInversion.getcInversionId());

         //   MaeCuotaPago oMaeCuotaPago = new MaeCuotaPago();
           // oMaeCuotaPago.setMaeInversion(maeInversionCd);
          //  maeCuotaPagoList = getCuotaPagoServ().listarCuotaPago(oMaeCuotaPago);

            //maeCargo.setMaeInversion(maeInversionCd);
            //maeCargo = getCargoServ().calcularCargoAtrasado(maeCargo);
           // setCuoPendienteOtros(maeCargo.getIPendiente().toString());
            
   
            //System.out.println("   Lista de maeCuotaPagoList = " + maeCuotaPagoList.size());
        } catch (Exception e) {
            System.out.println("pop.webcustodia.bean.InversionBean.obtenerCabDet()");
        }
    }

    public MaeInversion getMaeInversion() {
        return maeInversion;
    }

    public void setMaeInversion(MaeInversion maeInversion) {
        this.maeInversion = maeInversion;
    }

    public MaeFondo getMaeFondo() {
        return maeFondo;
    }

    public void setMaeFondo(MaeFondo maeFondo) {
        this.maeFondo = maeFondo;
    }

    public MaePersona getMaePersona() {
        return maePersona;
    }

    public void setMaePersona(MaePersona maePersona) {
        this.maePersona = maePersona;
    }

    public MaeInmueble getMaeInmueble() {
        return maeInmueble;
    }

    public void setMaeInmueble(MaeInmueble maeInmueble) {
        this.maeInmueble = maeInmueble;
    }

    public MaeUbigeo getMaeUbigeo() {
        return maeUbigeo;
    }

    public void setMaeUbigeo(MaeUbigeo maeUbigeo) {
        this.maeUbigeo = maeUbigeo;
    }

    public List<MaeInversion> getMaeInversionList() {
        return maeInversionList;
    }

    public void setMaeInversionList(List<MaeInversion> maeInversionList) {
        this.maeInversionList = maeInversionList;
    }

    public InversionServ getInversionServ() {
        return inversionServ;
    }

    public void setInversionServ(InversionServ inversionServ) {
        this.inversionServ = inversionServ;
    }

    public IUbigeoServ getiUbigeoServ() {
        return iUbigeoServ;
    }

    public void setiUbigeoServ(IUbigeoServ iUbigeoServ) {
        this.iUbigeoServ = iUbigeoServ;
    }

    public List<MaeUbigeo> getUbigeoProv() {
        return ubigeoProv;
    }

    public void setUbigeoProv(List<MaeUbigeo> ubigeoProv) {
        this.ubigeoProv = ubigeoProv;
    }

    public List<MaeUbigeo> getUbigeoDist() {
        return ubigeoDist;
    }

    public void setUbigeoDist(List<MaeUbigeo> ubigeoDist) {
        this.ubigeoDist = ubigeoDist;
    }

    public MaeUbigeo getMaeUbigeoP() {
        return maeUbigeoP;
    }

    public void setMaeUbigeoP(MaeUbigeo maeUbigeoP) {
        this.maeUbigeoP = maeUbigeoP;
    }

    public MaeUbigeo getMaeUbigeoD() {
        return maeUbigeoD;
    }

    public void setMaeUbigeoD(MaeUbigeo maeUbigeoD) {
        this.maeUbigeoD = maeUbigeoD;
    }

    public MaeInversion getMaeInversionCd() {
        return maeInversionCd;
    }

    public void setMaeInversionCd(MaeInversion maeInversionCd) {
        this.maeInversionCd = maeInversionCd;
    }



    public Number getMontoCuota() {
        return montoCuota;
    }

    public void setMontoCuota(Number montoCuota) {
        this.montoCuota = montoCuota;
    }

  

    public String getCuoPendiente() {
        return cuoPendiente;
    }

    public void setCuoPendiente(String cuoPendiente) {
        this.cuoPendiente = cuoPendiente;
    }

    public String getCuoPendienteFC() {
        return cuoPendienteFC;
    }

    public void setCuoPendienteFC(String cuoPendienteFC) {
        this.cuoPendienteFC = cuoPendienteFC;
    }

    public String getCuoPendienteOtros() {
        return cuoPendienteOtros;
    }

    public void setCuoPendienteOtros(String cuoPendienteOtros) {
        this.cuoPendienteOtros = cuoPendienteOtros;
    }

    public String getCuoPendienteDepMes() {
        return cuoPendienteDepMes;
    }

    public void setCuoPendienteDepMes(String cuoPendienteDepMes) {
        this.cuoPendienteDepMes = cuoPendienteDepMes;
    }

    public Date getFechaCorte() {
        return fechaCorte;
    }

    public void setFechaCorte(Date fechaCorte) {
        this.fechaCorte = fechaCorte;
    }

    
    public String getCuoPendienteTotal() {
        return cuoPendienteTotal;
    }

    public void setCuoPendienteTotal(String cuoPendienteTotal) {
        this.cuoPendienteTotal = cuoPendienteTotal;
    }
    
    public ITablasServ getTablasServ() {
        return tablasServ;
    }

    public void setTablasServ(ITablasServ tablasServ) {
        this.tablasServ = tablasServ;
    }

    public boolean isContactado() {
        return contactado;
    }

    public void setContactado(boolean contactado) {
        this.contactado = contactado;
    }

    public CobTablas getCobTablasAccion() {
        return cobTablasAccion;
    }

    public void setCobTablasAccion(CobTablas cobTablasAccion) {
        this.cobTablasAccion = cobTablasAccion;
    }

    public CobTablas getCobTablasFamilia() {
        return cobTablasFamilia;
    }

    public void setCobTablasFamilia(CobTablas cobTablasFamilia) {
        this.cobTablasFamilia = cobTablasFamilia;
    }

    public List<CobTablas> getCobTablasAccionList() {
        return cobTablasAccionList;
    }

    public void setCobTablasAccionList(List<CobTablas> cobTablasAccionList) {
        this.cobTablasAccionList = cobTablasAccionList;
    }

    public List<CobTablas> getCobTablasFamiliaList() {
        return cobTablasFamiliaList;
    }

    public void setCobTablasFamiliaList(List<CobTablas> cobTablasFamiliaList) {
        this.cobTablasFamiliaList = cobTablasFamiliaList;
    }

    public CobTablas getCobTablasAccionB() {
        return cobTablasAccionB;
    }

    public void setCobTablasAccionB(CobTablas cobTablasAccionB) {
        this.cobTablasAccionB = cobTablasAccionB;
    }

    public CobTablas getCobTablasFamiliaB() {
        return cobTablasFamiliaB;
    }

    public void setCobTablasFamiliaB(CobTablas cobTablasFamiliaB) {
        this.cobTablasFamiliaB = cobTablasFamiliaB;
    }

  

    
    
}

