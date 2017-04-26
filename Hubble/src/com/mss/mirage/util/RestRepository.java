/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.mirage.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author miracle
 */
public class RestRepository {
     private static RestRepository _instance;
     public static RestRepository getInstance() throws ServiceLocatorException {
        try {
            if(_instance==null) {
                _instance = new RestRepository();
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
        }
        return _instance;
    }
     
     public String getSrviceUrl(String serviceName) throws ServiceLocatorException{
     //   System.out.println("serviceName---11--->"+serviceName);
        String serviceUrl = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT ServiceUrl FROM tblRestSrcReg WHERE STATUS = 'Active' AND ServiceName = '"+serviceName+"'";
      //  System.out.println("queryString---11--->"+queryString);
        try {
            String serviceBaseUrl=Properties.getProperty("WEB.REST.URL");
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
           if(resultSet.next()){
                serviceUrl =  serviceBaseUrl+resultSet.getString("ServiceUrl");
            }            
        }catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
            
            try {
                // resultSet Object Checking if it's null then close and set null
                if(resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                
                if(statement != null) {
                    statement.close();
                    statement = null;
                }
                
                if(connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        
      //  System.out.println("serviceUrl---11--->"+serviceUrl);
        return serviceUrl;
    }
}
