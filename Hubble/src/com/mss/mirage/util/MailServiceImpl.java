/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.mirage.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author miracle
 */
public class MailServiceImpl implements MailServices {
     private Connection connection;
    /**
     *
     * Creating a reference variable for preparedStatement
     */
    private PreparedStatement preparedStatement;
    /**
     *
     * Creating a reference variable for Resultset
     */
    private ResultSet resultSet;
    
  public MailServiceImpl() {
    }
public String doAddEmailLog(String toAddress, String cCAddress, String subject,String bodyContent,String createdDate,String bCCAddress) throws ServiceLocatorException{

     String sqlString = "INSERT INTO tblEmailLog(ToAddress,CCAddress,SUBJECT,BodyContent,CreatedDate,BCCAddress) VALUES (?,?,?,?,?,?)";
          String returnStmt="";   
          int updatedRows=0;
     try{
            connection = ConnectionProvider.getInstance().getConnection();
           
            preparedStatement = connection.prepareStatement(sqlString);
                
              
                preparedStatement.setString(1,toAddress);
                preparedStatement.setString(2,cCAddress);
                preparedStatement.setString(3,subject);
                preparedStatement.setString(4,bodyContent);
                preparedStatement.setTimestamp(5,DateUtility.getInstance().getCurrentMySqlDateTime());
               
                preparedStatement.setString(6,bCCAddress);
                
                
                updatedRows = preparedStatement.executeUpdate();
                
                if(updatedRows == 0){
                    returnStmt = "FAILURE";
                }else if(updatedRows == 1){
                    returnStmt = "SUCCESS";
                }
}catch (SQLException ex) {
            ex.printStackTrace();
        }catch (ServiceLocatorException sle){
            sle.printStackTrace();
        } finally{
            try{
                
                if(resultSet!= null){
                    resultSet.close();
                    resultSet = null;
                }
                if(preparedStatement!= null){
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if(connection!= null){
                    connection.close();
                    connection = null;
                }
            }catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return returnStmt;
    }
 public String doAddEmailLogNew(String toAddress, String cCAddress, String subject,String bodyContent,String createdDate,String bCCAddress,String Category) throws ServiceLocatorException{

     String sqlString = "INSERT INTO tblEmailLog(ToAddress,CCAddress,SUBJECT,BodyContent,CreatedDate,BCCAddress,Category) VALUES (?,?,?,?,?,?,?)";
          String returnStmt="";   
          int updatedRows=0;
     try{
            connection = ConnectionProvider.getInstance().getConnection();
           
            preparedStatement = connection.prepareStatement(sqlString);
                
              
                preparedStatement.setString(1,toAddress);
                preparedStatement.setString(2,cCAddress);
                preparedStatement.setString(3,subject);
                preparedStatement.setString(4,bodyContent);
                preparedStatement.setTimestamp(5,DateUtility.getInstance().getCurrentMySqlDateTime());
               
                preparedStatement.setString(6,bCCAddress);
                preparedStatement.setString(7,Category);
                
                
                updatedRows = preparedStatement.executeUpdate();
                
                if(updatedRows == 0){
                    returnStmt = "FAILURE";
                }else if(updatedRows == 1){
                    returnStmt = "SUCCESS";
                }
}catch (SQLException ex) {
            ex.printStackTrace();
        }catch (ServiceLocatorException sle){
            sle.printStackTrace();
        } finally{
            try{
                
                if(resultSet!= null){
                    resultSet.close();
                    resultSet = null;
                }
                if(preparedStatement!= null){
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if(connection!= null){
                    connection.close();
                    connection = null;
                }
            }catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return returnStmt;
    }

//appreciation email start
 public String doAddEmailLogWithFromAddress(String toAddress, String cCAddress, String subject,String bodyContent,String createdDate,String bCCAddress,String Category,String fromAddress) throws ServiceLocatorException{

     
     
     
     
     
     String sqlString = "INSERT INTO tblEmailLog(ToAddress,CCAddress,SUBJECT,BodyContent,CreatedDate,BCCAddress,Category ,FromAddress) VALUES (?,?,?,?,?,?,?,?)";
          String returnStmt="";   
          int updatedRows=0;
     try{
            connection = ConnectionProvider.getInstance().getConnection();
           
            preparedStatement = connection.prepareStatement(sqlString);
                
              
                preparedStatement.setString(1,toAddress);
                preparedStatement.setString(2,cCAddress);
                preparedStatement.setString(3,subject);
                preparedStatement.setString(4,bodyContent);
                preparedStatement.setTimestamp(5,DateUtility.getInstance().getCurrentMySqlDateTime());
               
                preparedStatement.setString(6,bCCAddress);
                preparedStatement.setString(7,Category);
                preparedStatement.setString(8, fromAddress);
                
                
                updatedRows = preparedStatement.executeUpdate();
                
                if(updatedRows == 0){
                    returnStmt = "FAILURE";
                }else if(updatedRows == 1){
                    returnStmt = "SUCCESS";
                }
}catch (SQLException ex) {
            ex.printStackTrace();
        }catch (ServiceLocatorException sle){
            sle.printStackTrace();
        } finally{
            try{
                
                if(resultSet!= null){
                    resultSet.close();
                    resultSet = null;
                }
                if(preparedStatement!= null){
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if(connection!= null){
                    connection.close();
                    connection = null;
                }
            }catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return returnStmt;
    }



 
 //apprecotion email end

}