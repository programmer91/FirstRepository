/*
 * CreLoginServiceImpl.java
 *
 * Created on August 27, 2013, 3:34 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.cre.general;

import com.mss.mirage.util.ConnectionProvider;
import java.sql.CallableStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import com.mss.mirage.util.ServiceLocatorException;
import java.sql.Types;
/**
 *
 * @author miracle
 */
public class CreLoginServiceImpl implements CreLoginService{
    
    /** Creates a new instance of CreLoginServiceImpl */
    public CreLoginServiceImpl() {
    }
    /* public String[] creLoginCheck(String consultantId) throws ServiceLocatorException {
         Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String consultantDetails[] = new String[9];
        String queryString = "SELECT * FROM tblCreConsultentDetails WHERE ConsultentId='"+consultantId+"'";
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while(resultSet.next()){
               // System.out.println("In while");
                //primaryAction = resultSet.getString("Action");
                consultantDetails[0] = resultSet.getString("ConsultentId");
                consultantDetails[1] = resultSet.getString("FName")+" "+resultSet.getString("LName");
                  consultantDetails[2] = resultSet.getString("Email1");
                  consultantDetails[3] = resultSet.getString("CellPhoneNo");
                  consultantDetails[4] = resultSet.getString("Id");
                  consultantDetails[5] = resultSet.getString("STATUS");
                  consultantDetails[6] = resultSet.getString("NoOfQuestions");
                   consultantDetails[7] = resultSet.getString("MinMarks");
                   consultantDetails[8] = resultSet.getString("Duration");
            }
            
        }catch (SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
                if(resultSet != null){
                    resultSet.close();
                    resultSet = null;
                }
                if(statement != null){
                    statement.close();
                    statement = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
        
        return consultantDetails;
     }*/
    /*Created By Santosh Kola
     * Created Date : 03/06/2014
     * 
     */
    public String[] creLoginCheck(String consultantId) throws ServiceLocatorException {
         Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        
        String consultantDetails[] = new String[7];
        String creDetailsInfo[] = null;
       // String queryString = "SELECT * FROM tblCreConsultentDetails WHERE ConsultentId='"+consultantId+"'";
        try{
            connection = ConnectionProvider.getInstance().getConnection();
         /*   statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while(resultSet.next()){
               // System.out.println("In while");
                //primaryAction = resultSet.getString("Action");
                consultantDetails[0] = resultSet.getString("ConsultentId");
                consultantDetails[1] = resultSet.getString("FName")+" "+resultSet.getString("LName");
                  consultantDetails[2] = resultSet.getString("Email1");
                  consultantDetails[3] = resultSet.getString("CellPhoneNo");
                  consultantDetails[4] = resultSet.getString("Id");
                  consultantDetails[5] = resultSet.getString("STATUS");
                  consultantDetails[6] = resultSet.getString("NoOfQuestions");
                   consultantDetails[7] = resultSet.getString("MinMarks");
                   consultantDetails[8] = resultSet.getString("Duration");
            }
            */
            //callableStatement = connection.prepareCall("{call spCreLoginProc(?,?,?)}");
            callableStatement = connection.prepareCall("{call spCreLoginProc(?,?,?)}");
            callableStatement.setString(1,consultantId);
                 callableStatement.registerOutParameter(2,Types.VARCHAR);
                 callableStatement.registerOutParameter(3,Types.VARCHAR);
                 
                 callableStatement.execute();
                 String  creDetails = callableStatement.getString(2);
                 String  creExamDetails = callableStatement.getString(3);
                // System.out.println("creDetails-->"+creDetails);
                // System.out.println("creExamDetails-->"+creExamDetails);
                if(creDetails!=null) {
                  creDetailsInfo = creDetails.split("\\!");
                  for(int i=0;i<creDetailsInfo.length;i++) {
                      consultantDetails[i] = creDetailsInfo[i];
                  }
                  consultantDetails[6] = creExamDetails;
                  }
                  
                  
                // creDetailsInfo[6] = creExamDetails;
                 /*creDetailsInfo[0] ->ID
                  * creDetailsInfo[1] ->ConsultentId
                  * creDetailsInfo[2] ->Full Name
                  * creDetailsInfo[3] ->Email
                  * creDetailsInfo[4] ->Status
                  */
            
        }catch (SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
             /*   if(resultSet != null){
                    resultSet.close();
                    resultSet = null;
                }
                if(statement != null){
                    statement.close();
                    statement = null;
                }*/
                if(callableStatement != null){
                    callableStatement.close();
                    callableStatement = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
        
        return consultantDetails;
     }


}
