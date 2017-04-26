/*
 * ProjectsServiceImpl.java
 *
 * Created on December 18, 2008, 10:34 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.employee.projects;

import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.ServiceLocatorException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.ParseException;

/**
 *
 * @author miracle
 */
public class ProjectsServiceImpl implements ProjectsService{
    
    /**
     * Creates a new instance of ProjectsServiceImpl
     */
    public ProjectsServiceImpl() {
    }
    
    public boolean addProjectDetails(ProjectAction projAction) throws ServiceLocatorException, ParseException {
        CallableStatement callableStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        int projType =0;
        String prjMng = null;
        String mode="Add";
        boolean flag=false;
        int x = 0;
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("{call spEmpProjectDetails(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            if(projAction.getProjId()!=0) mode="Edit";
            callableStatement.setString(1,mode);
            callableStatement.setString(2,projAction.getPrjName());
            if(projAction.getPrjManagerUID() == null) {
                statement = connection.createStatement();
                resultSet = statement.executeQuery("select ProjectManagerUID from tblProjects where Id="+projAction.getProjId());
                while(resultSet.next()) {
                    prjMng = resultSet.getString("ProjectManagerUID");
                }
                callableStatement.setString(3,prjMng);
            }else
                callableStatement.setString(3,projAction.getPrjManagerUID());
            callableStatement.setDate(4,DateUtility.getInstance().getMySqlDate1(projAction.getStartDate()));
            callableStatement.setDate(5,DateUtility.getInstance().getMySqlDate1(projAction.getEndDate()));
            if(projAction.getProjectType() == null) {
                statement = connection.createStatement();
                resultSet = statement.executeQuery("select ProjectType from tblProjects where Id="+projAction.getProjId());
                while(resultSet.next()) {
                    projType = resultSet.getInt("ProjectType");
                }
                callableStatement.setInt(6,projType);
            } else
                callableStatement.setInt(6,Integer.parseInt(projAction.getProjectType()));
            callableStatement.setInt(7,projAction.getAccountId());
            callableStatement.setInt(8,1);
            callableStatement.setDate(9,Date.valueOf(DateUtility.getInstance().getToDayDate()));
            
            callableStatement.setString(10,projAction.getEmpId());
            callableStatement.setDate(11,Date.valueOf(DateUtility.getInstance().getToDayDate()));
            callableStatement.setString(12,projAction.getEmpId());
            callableStatement.setInt(13,projAction.getProjId());
            x=callableStatement.executeUpdate();
            if(x==1){
                flag=true;
            }
        }catch(SQLException sqle){
            throw new ServiceLocatorException(sqle);}
        
        finally{
            try{
                if(resultSet!=null){
                    resultSet.close();
                    resultSet = null;
                }
                if(statement!=null){
                    statement.close();
                    statement = null;
                }
                
                if(callableStatement!=null){
                    callableStatement.close();
                    callableStatement = null;
                }
                if(connection!=null){
                    connection.close();
                    connection = null;
                }
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
            
            
        }
        return flag;
    }
    
    public ProjectVTO getProjectDetails(int Id) throws ServiceLocatorException {
        ProjectVTO prjVTO = new ProjectVTO();
        
        Connection connection = null;
       // PreparedStatement preparedStatement = null;
        Statement statement=null;
        ResultSet resultSet= null;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            statement=connection.createStatement();
            resultSet=statement.executeQuery("select Id,ProjectName,ProjectManagerUID,ProjectStartDate,ProjectEndDate,ProjectType,CustomerId from tblProjects where Id='"+Id+"'");
            while(resultSet.next()){
                prjVTO.setPrjName(resultSet.getString("ProjectName"));
                prjVTO.setPrjManagerUID(resultSet.getString("ProjectManagerUID"));
                prjVTO.setStartDate(DateUtility.getInstance().convertDateToString(resultSet.getDate("ProjectStartDate")));
                prjVTO.setEndDate(DateUtility.getInstance().convertDateToString(resultSet.getDate("ProjectEndDate")));
                //   prjVTO.setProjectType(DataSourceDataProvider.getInstance().getPrjDescById(resultSet.getInt("ProjectType")));
                prjVTO.setProjectType(Integer.toString(resultSet.getInt("ProjectType")));
                prjVTO.setAccName(DataSourceDataProvider.getInstance().getAccountName(resultSet.getInt("CustomerId")));
            }
        } catch(SQLException sqle){
            throw new ServiceLocatorException(sqle);
        } finally{
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
        return prjVTO;
    }
    
    public void getProjStatusCode(ProjectAction projAction) throws ServiceLocatorException {
        Connection connection = null;
       // PreparedStatement preparedStatement = null;
        Statement statement=null;
        ResultSet resultSet= null;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            statement=connection.createStatement();
            resultSet = statement.executeQuery("select StatusCode from tblPrjStatusTracker where ProjectId ="+projAction.getProjId());
            while(resultSet.next()) {
                projAction.setStatusCode(Integer.toString(resultSet.getInt("StatusCode")));
            }
        }catch(SQLException sqle){
            throw new ServiceLocatorException(sqle);
        } finally{
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
    }
}