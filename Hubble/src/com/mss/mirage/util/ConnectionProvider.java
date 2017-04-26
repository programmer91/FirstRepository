/*
 * ConnectionProvider.java
 *
 * Created on September 6, 2007, 10:26 PM
 *
 * MIRAGE V2
 *
 */

package com.mss.mirage.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import com.mss.mirage.util.ServiceLocatorException;
import com.mss.mirage.util.Properties;
/**
 *
 * @author Rajasekhar (Phno 9290842877)
 */
public class ConnectionProvider {
    
    private static ConnectionProvider _instance;
    
    private  DataSource dataSource;
    private  Connection connection;
    
    
    
    private ConnectionProvider(){
        
    }
    
    public static ConnectionProvider getInstance(){
        
        if(_instance == null){
            _instance = new ConnectionProvider();
        }
        return _instance;
    }
    
    /**
     *
     * @return returns Connection from IntialContext
     */
    public Connection getConnection() throws ServiceLocatorException{
        try{
            
            dataSource = DataServiceLocator.getInstance().getDataSource(Properties.getProperty("New.DataSource.Name"));
            connection = dataSource.getConnection();
        }catch(ServiceLocatorException se) {
            throw new ServiceLocatorException("Exception in Connection Provider");
        }catch(SQLException sqlEx) {
            throw new ServiceLocatorException(sqlEx);
        }
        return connection;
    }
    
//    public Connection getMysqlConnection() throws ServiceLocatorException, SQLException{
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//        } catch (ClassNotFoundException ex) {
//            ex.printStackTrace();
//        }
//            connection = DriverManager.getConnection("jdbc:mysql://172.17.4.167:3306/mirageteam","mirage","mirage");
//        return connection;
//    }
    
    
    
}
