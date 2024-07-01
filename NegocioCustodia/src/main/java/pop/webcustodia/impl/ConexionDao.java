/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.impl;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleConnection;
import pop.webcustodia.conn.Conexion;
import pop.webcustodia.iface.IConexionDao;

/**
 *
 * @author Jyoverar
 */
public class ConexionDao implements IConexionDao {

    private static volatile ConexionDao instance;
    // instancia a la seaConn
    private Conexion dllCnxOracle = null;
    // conector Oracle
    private static OracleConnection oracleConnection;

    // singleton
    public static ConexionDao Instance() {
        if (instance == null) {
            instance = new ConexionDao();
        }
        return instance;
    }

    // constructor
    private ConexionDao() {
        dllCnxOracle = new Conexion();
    }

    // obtener el objeto OracleConnection
    public static OracleConnection getOracleConnection() {
        return oracleConnection;
    }

    /// <summary>
    /// abrir la conexion de la dll 
    /// </summary>
    @Override
    public void ConexionOpen() {
        try {
            //dllCnxOracle.ConexionOpen();
            oracleConnection = dllCnxOracle.ConexionOpen("segur");

        } catch (SQLException ex) {
            Logger.getLogger(ConexionDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /// <summary>
    /// Cerrar la conexion oracle
    /// </summary>    
    @Override
    public void ConexionClose() {
        try {
            //dllCnxOracle.ConexionClose();
            oracleConnection.close();
            //System.err.println("Estado de conexion : " + !oracleConnection.isClosed());

        } catch (SQLException ex) {
            Logger.getLogger(ConexionDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /// <summary>
    /// agregar una transaccion a la conexion
    /// </summary>    
    @Override
    public void AddTransaction() {
        try {
            //dllCnxOracle.AddTransaction();            
            oracleConnection.setAutoCommit(false);

        } catch (SQLException ex) {
            Logger.getLogger(ConexionDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /// <summary>
    /// guardar los cambios de la transaccion
    /// </summary>  
    @Override
    public void SaveChanges() {
        try {
            //dllCnxOracle.SaveChanges();
            oracleConnection.commit();

        } catch (SQLException ex) {
            Logger.getLogger(ConexionDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /// <summary>
    /// cancelar los cambios de la transaccion
    /// </summary> 
    @Override
    public void CancelChanges() {
        try {
            //dllCnxOracle.CancelChanges();
            oracleConnection.rollback();

        } catch (SQLException ex) {
            Logger.getLogger(ConexionDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // terminar el objeto creado
    private void Dispose() throws SQLException {
        oracleConnection.close();
        //dllCnxOracle.Dispose();
    }

}
