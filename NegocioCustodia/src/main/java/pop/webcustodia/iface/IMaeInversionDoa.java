/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.iface;

import java.util.ArrayList;
import pop.comun.dominio.MaeDocOtros;
import pop.comun.dominio.MaeEmail;
import pop.comun.dominio.MaeInversion;


/**
 *
 * @author Jyoverar
 */
public interface IMaeInversionDoa {

    ArrayList<MaeInversion> fetchDebtorsMovi(MaeInversion oMaeInversion);
    ArrayList<MaeInversion> fetchDebtors(MaeInversion oMaeInversion);
    ArrayList<MaeInversion> fetchDebtorsf2(MaeInversion oMaeInversion);
    
    ArrayList<MaeInversion> fetchDebtorsCons(MaeInversion oMaeInversion);
    
    ArrayList<MaeInversion> fetchCustodia(MaeInversion oMaeInversion);
    ArrayList<MaeInversion> fetchConsultaxDoc(MaeInversion oMaeInversion);
    ArrayList<MaeInversion> fetchConsultaxMov(MaeInversion oMaeInversion);
    
    ArrayList<MaeInversion> fetchMovDocumentos(MaeInversion oMaeInversion);
    
    ArrayList<MaeInversion> fetchCustodiaf2(MaeInversion oMaeInversion);
    ArrayList<MaeInversion> fetchDetalleCustodia(MaeInversion oMaeInversion);
    
    ArrayList<MaeInversion> fetchCustodiaMovi(MaeInversion oMaeInversion);
    ArrayList<MaeInversion> fetchCustodiaxDoc(MaeInversion oMaeInversion);
    ArrayList<MaeInversion> fetchListaSolicitante(String cArea);
    
    Integer validaCorreo(MaeInversion oMaeInversion);
    Integer InsCustodia(MaeInversion oMaeInversion);
    Integer InsCustodiaf2(MaeInversion oMaeInversion);
    Integer DelDocCustodia(MaeInversion oMaeInversion);
    Integer InsCustodiaMov(MaeInversion oMaeInversion);
    
    ArrayList<MaeEmail> fetchEmail();
    Integer DelCustodia(String cFondo,String cMaeInversion);
    Integer InsDocCustodia(MaeDocOtros oMaeDocOtros);
    Integer GrabarDocDetCustodia(MaeInversion oMaeInversion);
    
}
