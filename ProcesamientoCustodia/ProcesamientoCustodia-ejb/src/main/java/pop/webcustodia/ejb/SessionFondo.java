/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.ejb;

import java.util.List;
import javax.ejb.Stateless;
import oracle.jdbc.OracleConnection;
import pop.comun.dominio.MaeFondo;

import pop.webcustodia.conn.Conexion;
import pop.webcustodia.dao.FactoryDao;
import pop.webcustodia.negocio.INegFondo;

/**
 *
 * @author Jyoverar
 */
@Stateless(mappedName = "ejbFondo")
public class SessionFondo implements INegFondo {

    FactoryDao ofDao = new FactoryDao();

    @Override
    public List<MaeFondo> listarFondos(MaeFondo oMaeFondo) throws Exception {
        System.out.println("<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        List<MaeFondo> oListFondos;
        
        oListFondos = ofDao.getFondo().fetchAll(oMaeFondo);
        return oListFondos;
    }


}
