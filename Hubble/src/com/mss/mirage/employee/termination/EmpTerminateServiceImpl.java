/*
 * EmpTerminateServiceImpl.java
 *
 * Created on July 7, 2008, 7:02 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.employee.termination;
import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.DateUtility;
import java.sql.*;
import com.mss.mirage.util.ServiceLocatorException;

/**
 *
 * @author miracle
 */
public class EmpTerminateServiceImpl implements EmpTerminateService{
    
    /** Creates a new instance of EmpTerminateServiceImpl */
    public EmpTerminateServiceImpl() {
    }
    
    /**
     * 
     * This method is used to get the Primary Details of Employee
     * @param empId 
     * @return EmpTerminateVTO
     * @throws com.mss.mirage.util.ServiceLocatorException 
     */
    public EmpTerminateVTO getTermination(int empId) throws ServiceLocatorException {
        EmpTerminateVTO empTerminateVTO=new EmpTerminateVTO();
        Connection connection = null;
        //PreparedStatement preparedStatement = null;
        Statement statement=null;
        ResultSet resultSet= null;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            statement=connection.createStatement();
            resultSet=statement.executeQuery("select TitleTypeId,Itgbatch,HireDate,SubPractice,TerminationDate,CurStatus,Termination " +
                    " from tblEmployee where id='"+empId+"'");
            while(resultSet.next()){
                empTerminateVTO.setDesignation(resultSet.getString("TitleTypeId"));
                empTerminateVTO.setTeamName(resultSet.getString("SubPractice"));
                empTerminateVTO.setDateOfJoin(resultSet.getDate("HireDate"));
                empTerminateVTO.setItgBatch(resultSet.getString("Itgbatch"));
                empTerminateVTO.setDateOfTermination(resultSet.getDate("TerminationDate"));
                empTerminateVTO.setCurrStatus(resultSet.getString("CurStatus"));
                empTerminateVTO.setResonsForTerminate(resultSet.getString("Termination"));
            }
            empTerminateVTO.setEmpId(empId);
        }catch(SQLException sqle){
            throw new ServiceLocatorException(sqle);
        }finally{
            try{
                 if(resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if(statement!=null){
                    statement.close();
                    statement = null;
                }
                if(connection!=null){
                    connection.close();
                    connection = null;
                }
                
            }catch(SQLException sqle){
                throw new ServiceLocatorException(sqle);
            }
        }
        
        
        return empTerminateVTO;
    }
    
    /**
     * 
     * This method is used to update the Primary Details of Employee
     * @param empAction 
     * @param empId 
     * @return boolean
     * @throws com.mss.mirage.util.ServiceLocatorException 
     */
    public boolean updateTermination(EmpTerminateAction empAction,int empId) throws ServiceLocatorException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        boolean isUpdated=false;
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement=connection.prepareStatement("update tblEmployee set TitleTypeId = ?,Itgbatch= ?,HireDate= ?,SubPractice =?," +
                    "TerminationDate = ?,CurStatus = ?,Termination = ? where Id='"+empId+"'");
            preparedStatement.setString(1,empAction.getDesignation());
            preparedStatement.setString(2,empAction.getItgBatch());
            preparedStatement.setDate(3,empAction.getDateOfJoin());
            preparedStatement.setString(4,empAction.getTeamName());
            preparedStatement.setDate(5,empAction.getDateOfTermination());
            preparedStatement.setString(6,empAction.getCurrStatus());
            preparedStatement.setString(7,empAction.getResonsForTerminate());
            
            int x=preparedStatement.executeUpdate();
             preparedStatement.close();
            if(x==0){
                isUpdated=false;
                
            }else isUpdated=true;
            
            
            
            
            
            
        }catch(SQLException sql){
            throw new ServiceLocatorException(sql);
        }finally{
            try{
                if(preparedStatement!=null){
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if(connection!=null){
                    connection.close();
                    connection = null;
                }
                
            }catch(SQLException sqle){
                throw new ServiceLocatorException(sqle);
            }
        }
        
        return isUpdated;
    }
    
}
