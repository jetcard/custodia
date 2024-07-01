/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.conn;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleResultSet;
import oracle.jdbc.OracleStatement;
import oracle.jdbc.pool.OracleDataSource;


/**
 *
 * @author Jyoverar
 */
public class Conexion {
    
    
    //ileInputStream fis = new FileInputStream("src/main/resources/settings.properties");
    
    private OracleConnection oracleConnection = null;
    private static String url = "";
    private static String urlctda = "";
    private static String urleva = "";
    private static String driver = "";
    private static String driverType = "";
    private static String databaseName = "";
    private static String serverName = "";
    private static String portNumber = "";
    private static String userCTDIA = "";
    private static String passwordCTDIA = "";    
    private static String userSEG = "";
    private static String passwordSEG = "";  
    private static String userEVA = "";
    private static String passwordEVA = "";

    public Conexion(){
      try 
      {
        Properties propiedades = new Properties();
        propiedades.load(new FileInputStream("custodia.properties"));   
        driver = propiedades.getProperty("driver");
        driverType= propiedades.getProperty("driverType");
        userCTDIA = propiedades.getProperty("userCTDIA");
        userSEG = propiedades.getProperty("userSEG");
        userEVA = propiedades.getProperty("userEVA");
        Seguridad sec = new Seguridad();
        sec.addKey("37WF234HFHKJHFKSHDFSHDFK328Y294FKFHKHFWHEW734294YDNKFWSK4454r4234regerljwlrwlrlwjro34u2ofsflksj");
        passwordCTDIA=sec.desencriptar(propiedades.getProperty("passwordCTDIA"));
        passwordSEG=sec.desencriptar(propiedades.getProperty("passwordSEG"));
        passwordEVA=sec.desencriptar(propiedades.getProperty("passwordEVA"));
        serverName=propiedades.getProperty("serverName");
        portNumber=propiedades.getProperty("portNumber");
        databaseName=propiedades.getProperty("databaseName");
        url=driver + ":" + driverType + ":" + userSEG + "/" + passwordSEG + "@" + serverName + ":" + portNumber + ":" + databaseName;
        urlctda=driver + ":" + driverType + ":" + userCTDIA + "/" + passwordCTDIA + "@" + serverName + ":" + portNumber + ":" + databaseName;
        urleva=driver + ":" + driverType + ":" + userEVA + "/" + passwordEVA + "@" + serverName + ":" + portNumber + ":" + databaseName;
      } 
      catch (FileNotFoundException e) {
            e.printStackTrace();
      } 
      catch (IOException e) {
            e.printStackTrace();
      }
    }
       
    public OracleConnection getOracleConnection() {
        return oracleConnection;
    }

    public void setOracleConnection(OracleConnection aOracleConnection) {
        oracleConnection = aOracleConnection;
    }
  
    /// <summary>
    /// Test de conexion
    /// </summary>
    /// <returns>true = conexion    false = sin conexion</returns>
    public boolean IsConnectionAlive() throws ClassNotFoundException, SQLException{
        boolean status = false;
        OracleDataSource ods = null;
        OracleConnection ocon = null;
        OracleStatement stmt = null;
        OracleResultSet rs = null;
        
        try {
            ods = this.setOracleDataSourceUrl("");
            // open the connection to the database
            ocon = (OracleConnection)ods.getConnection();            
            stmt = (OracleStatement) ocon.createStatement();
            rs = (OracleResultSet) stmt.executeQuery("SELECT C_USUARIO_ID, D_NOMBRES, E_ESTADO  FROM COB_USUARIO WHERE C_USUARIO_ID = 'MOTOYA'");    
            while (rs.next()) {
                System.out.print(rs.getString(1) + "|");
                System.out.print(rs.getString(2) + "|");
                System.out.println(rs.getString(3) + "|");
            }
            
            status = true;
            // close the connection the database and the close the datasource
            rs.close();
            stmt.close();
            ocon.close();
            ods.close();        
            
        } catch (SQLException e) {
            throw e;
        }        
        
        return status;
    }
    /*
    private OracleDataSource setOracleDataSourceProperty() throws SQLException{
        OracleDataSource ods = new OracleDataSource();
        System.out.println("Estuvo por aqui");
        ods.setDriverType(Conexion.driverType);
        ods.setNetworkProtocol(Conexion.networkProtocol);
        ods.setDatabaseName(Conexion.databaseName);
        ods.setServerName(Conexion.serverName);
        ods.setPortNumber(Conexion.portNumber);
        ods.setUser(Conexion.user);
        ods.setPassword(Conexion.password);
              
        return ods;
    }
    */
  /*  
    private OracleDataSource setOracleDataSourceUrl() throws SQLException {
           System.out.println("aca3");
        OracleDataSource ods = new OracleDataSource();
        ods.setURL(Conexion.url);
               
        return ods;
    }
*/
    
    private OracleDataSource setOracleDataSourceUrl(String con) throws SQLException {
        
       OracleDataSource ods = new OracleDataSource();
        
       
       if (con.contains("segur")){
             ods.setURL(Conexion.url);
        } 
        else if (con.contains("ctdia"))
        {
            ods.setURL(Conexion.urlctda);
        }else  if(con.contains("eva")){
            ods.setURL(Conexion.urleva);
        }else{
            ods.setURL(Conexion.url);
        }    
            
        return ods;
    }


    /// <summary>
    /// Abrir y devuelve un conexion
    /// </summary>    
    public OracleConnection ConexionOpen(String con) throws SQLException{
        OracleConnection cn;
    
        try {
            cn = (OracleConnection) this.setOracleDataSourceUrl(con).getConnection();
            //oracleConnection.setAutoClose(true);
            //cn.setAutoClose(true);
            //System.out.println("Estado de conexion : " + !cn.isClosed());
            
        } catch (SQLException e) {
            if (oracleConnection != null)
                oracleConnection.close();
            throw e;
        }
        return cn;
    }
    
    /*
    public OracleConnection ConexionOpen() throws SQLException{
        System.out.println("aca1");
        OracleConnection cn;
        try {
               System.out.println("aca2");
            cn = (OracleConnection) this.setOracleDataSourceUrl().getConnection();
            //oracleConnection.setAutoClose(true);
            //cn.setAutoClose(true);
            //System.out.println("Estado de conexion : " + !cn.isClosed());
            
        } catch (SQLException e) {
            if (oracleConnection != null)
                oracleConnection.close();
            throw e;
        }
        return cn;
    }
    */
//    /// <summary>
//    /// Abrir una conexion
//    /// </summary>    
//    public void ConexionOpen() throws SQLException{        
//        try {
//            oracleConnection = (OracleConnection) this.setOracleDataSourceUrl().getConnection();
//            //oracleConnection.setAutoClose(true);            
//            System.out.println("Estado de conexion : " + !oracleConnection.isClosed());
//            
//        } catch (SQLException e) {
//            if (oracleConnection != null)
//                oracleConnection.close();
//            throw e;
//        }
//    }
    
    /// <summary>
    /// Cerrar una conexion
    /// </summary>
    public void ConexionClose() throws SQLException {
        try {
            if (oracleConnection != null && !oracleConnection.isClosed()) {
                oracleConnection.close();                
                //System.err.println("Estado de conexion : " + !oracleConnection.isClosed());
            }
        } catch (SQLException e) {
            throw e;
        }
    }
    
    /// <summary>
    /// Agregar una transaccion a la conexion
    /// </summary>
    public void AddTransaction() throws SQLException {
        try {
            // Disable auto-commit mode -> http://www.informit.com/articles/article.aspx?p=26251&seqNum=4
            oracleConnection.setAutoCommit(false);
        } catch (SQLException e) {
            throw e;
        }
    }
    
    /// <summary>
    //  Guardar los cambios de la transaccion
    /// </summary>
    public void SaveChanges() throws SQLException{
        try {
            oracleConnection.commit();
        } catch (SQLException e) {
            throw e;
        }
    }
    
    /// <summary>
    /// Cancelar los cambios de la transaccion
    /// </summary>
    public void CancelChanges() throws SQLException{
        try {
            oracleConnection.rollback();
        } catch (SQLException e) {
            throw e;
        }
    }
    
    //terminar el objeto creado
    public void Dispose() throws SQLException {
        try {
            if (oracleConnection != null && !oracleConnection.isClosed()) {
                oracleConnection.close();
            }
            
        } catch (SQLException e) {
            throw e;
        }
    }
    
}
