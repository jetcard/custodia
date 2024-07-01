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

import pop.comun.dominio.MaeFechaPor;
import pop.webcustodia.conn.Conexion;
import pop.webcustodia.dao.FactoryDao;
import pop.webcustodia.negocio.INegFechaPor;
import pop.webcustodia.negocio.INegFondo;

/**
 *
 * @author Jyoverar
 */
@Stateless(mappedName = "ejbFechaServ")
public class SessionFechaPor implements INegFechaPor {

    FactoryDao ofDao = new FactoryDao();

  

    @Override
    public List<MaeFechaPor> listarFechaPor() throws Exception {
         List<MaeFechaPor> oListFechaPor;
        
        oListFechaPor = ofDao.getMaeFechaPor().fetchAllFecha();
        return oListFechaPor;
    }

}
