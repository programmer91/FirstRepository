/*
 * MycopServiceImpl.java
 *
 * Created on March 11, 2008, 10:14 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.mycop;

import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.ServiceLocatorException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 *
 * @author Venumadhav Yelamanchili<vyelamanchili@miraclesoft.com>
 *
 *  This class is used to get data for which report is to be generated.
 */
public class MycopServiceImpl implements MycopService{
    
    private List mycopList;
    private List abcentiesList;
    
    /*This method will get the datafrom database and set it to UserDTO.java*/
    
    public List getUserData(String user,String startDate, String endDate,String dbLoginId) throws ServiceLocatorException{
        UserDTO userdto=null;
        mycopList=new ArrayList();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sqlQuery=null;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            if(user.equals("all")){
          /*Query to generate report for team */
                //sqlQuery = "select EmployeeName,LoginTime,LogoutTime, TotalBreakTime, TotalWorkedTime,TotalMeetingTime,IPAddress,HostName from vwMyCopReport where LoginId in ("+dbLoginId+") and Date(LoginTime) between '"+startDate+"' and '"+endDate+"' order by Date(LoginTime)";
                sqlQuery = "select EmployeeName,LoginTime,LogoutTime,Department, TotalBreakTime, TotalWorkedTime,TotalAttendanceTime,TotalMeetingTime,IPAddress,HostName from vwMyCopReport where LoginId in ("+dbLoginId+") and Date(LoginTime) between '"+startDate+"' and '"+endDate+"' order by Department,EmployeeName,Date(LoginTime)";
            }else if(user.equals("all_emp")){
                /*Query to generate report for all employees ---HR role */
                //sqlQuery="select EmployeeName,LoginTime,LogoutTime, TotalBreakTime, TotalWorkedTime,TotalMeetingTime,IPAddress,HostName from vwMyCopReport where Date(LoginTime) between '"+startDate+"' and '"+endDate+"' order by Date(LoginTime)";
                sqlQuery="select EmployeeName,LoginTime,LogoutTime,Department, TotalBreakTime, TotalWorkedTime,TotalAttendanceTime,TotalMeetingTime,IPAddress,HostName from vwMyCopReport where Date(LoginTime) between '"+startDate+"' and '"+endDate+"' order by Department,EmployeeName,Date(LoginTime)";
            }
            else {
                /*Query to generate report for particular employee */
                //sqlQuery = "select EmployeeName,LoginTime,LogoutTime, TotalBreakTime, TotalWorkedTime,TotalMeetingTime,IPAddress,HostName from vwMyCopReport where LoginId='"+user+"' and Date(LoginTime) between '"+startDate+"' and '"+endDate+"' order by Date(LoginTime)";
                sqlQuery = "select EmployeeName,LoginTime,LogoutTime,Department, TotalBreakTime, TotalWorkedTime,TotalAttendanceTime,TotalMeetingTime,IPAddress,HostName from vwMyCopReport where LoginId='"+user+"' and Date(LoginTime) between '"+startDate+"' and '"+endDate+"' order by Department,EmployeeName,Date(LoginTime)";
            }
            preparedStatement =connection.prepareStatement(sqlQuery);
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                userdto=new UserDTO();
                userdto.setEmployeeName(resultSet.getString("EmployeeName"));
                userdto.setLoginTime(resultSet.getTimestamp("LoginTime").toString());
                userdto.setLogoutTime(resultSet.getTimestamp("LogoutTime").toString());
                userdto.setDepartment(resultSet.getString("Department"));
                userdto.setTotalBreakTime(resultSet.getTime("TotalBreakTime").toString());
                userdto.setTotalMeetingTime(resultSet.getTime("TotalMeetingTime").toString());
                userdto.setTotalWorkedTime(resultSet.getTime("TotalWorkedTime").toString());
                userdto.setTotalAttendanceTime(resultSet.getTime("TotalAttendanceTime").toString());
                userdto.setHostName(resultSet.getString("HostName"));
                userdto.setIPAddress(resultSet.getString("IPAddress"));
                mycopList.add(userdto);
            }            
            resultSet.close();
            preparedStatement.close();
            connection.close();
            
        } catch (SQLException ex) {
            throw new ServiceLocatorException(ex);
        }finally{
            try{
                if(resultSet!=null){
                    resultSet.close();
                    resultSet = null;
                }
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
        
        return mycopList;
    }

    
  /*  public List getAbcentiesData(String startDate, String endDate,String departmentId)throws ServiceLocatorException {
        
        UserDTO userdto=null;
        mycopList=new ArrayList();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sqlQuery=null;
        String deptName = null;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            if(departmentId.equals("all")){
                deptName = "all%";
            }
            else {
                deptName = departmentId;
            }
          /*Query to generate report for team */
                //sqlQuery = "select EmployeeName,LoginTime,LogoutTime, TotalBreakTime, TotalWorkedTime,TotalMeetingTime,IPAddress,HostName from vwMyCopReport where LoginId in ("+dbLoginId+") and Date(LoginTime) between '"+startDate+"' and '"+endDate+"' order by Date(LoginTime)";
               // sqlQuery = "select EmployeeName,Department,TotalWorkedTime,TotalAttendanceTime from vwMyCopReport where LoginId in ("+dbLoginId+") and Date(LoginTime) between '"+startDate+"' and '"+endDate+"' order by Department,EmployeeName,Date(LoginTime)";            
          /*  preparedStatement =connection.prepareStatement(sqlQuery);
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                userdto=new UserDTO();
                userdto.setEmployeeName(resultSet.getString("EmployeeName"));
                userdto.setLoginTime(resultSet.getTimestamp("LoginTime").toString());
                userdto.setLogoutTime(resultSet.getTimestamp("LogoutTime").toString());
                userdto.setDepartment(resultSet.getString("Department"));
                userdto.setTotalBreakTime(resultSet.getTime("TotalBreakTime").toString());
                userdto.setTotalMeetingTime(resultSet.getTime("TotalMeetingTime").toString());
                userdto.setTotalWorkedTime(resultSet.getTime("TotalWorkedTime").toString());
                userdto.setTotalAttendanceTime(resultSet.getTime("TotalAttendanceTime").toString());
                userdto.setHostName(resultSet.getString("HostName"));
                userdto.setIPAddress(resultSet.getString("IPAddress"));
                mycopList.add(userdto);
            }            
            resultSet.close();
            preparedStatement.close();
            connection.close();
            
        } catch (SQLException ex) {
            throw new ServiceLocatorException(ex);
        }finally{
            try{
                if(resultSet!=null){
                    resultSet.close();
                    resultSet = null;
                }
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
        
        
        return abcentiesList;
    }  */
    
    
}
