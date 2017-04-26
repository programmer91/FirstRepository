/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.mirage.employee.performanceReviews;

import com.mss.mirage.util.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.HibernateServiceLocator;
import com.mss.mirage.util.Properties;
import com.mss.mirage.util.ServiceLocatorException;
import com.mss.mirage.util.DataSourceDataProvider;
import java.util.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author miracle
 */
public class PerformanceServiceImpl implements PerformanceService{
       public PerformanceServiceImpl() {
        
    }  
      /*  public  int addMetrics(PerformanceReviewsAction performanceReviewsAction,HttpServletRequest httpServletRequest,String metricName,String minvalue,String maxValue,String status,String desc)throws ServiceLocatorException, SQLException{
         
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        int isInserted = 0;
        //String queryString = "INSERT INTO tblEmpMetrics(MetricName,MinValue,MaxValue,Status,CreatedBy,Description) VALUES(?,?,?,?,?,?)";
        String queryString="INSERT INTO `tblEmpMetrics` ( `MetricName`, `MinValue`, `MaxValue`, `Status`, `CreatedBy`,`Description`)"
                + " VALUES(?,?,?,?,?,?)";
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1,performanceReviewsAction.getMetricName());
            preparedStatement.setInt(2,Integer.parseInt(performanceReviewsAction.getMinValue()));
            preparedStatement.setInt(3,Integer.parseInt(performanceReviewsAction.getMaxValue()));
            preparedStatement.setString(4,performanceReviewsAction.getStatusId());
            preparedStatement.setString(5,performanceReviewsAction.getCreatedBy());
            preparedStatement.setString(6,performanceReviewsAction.getComments());
           
            isInserted = preparedStatement.executeUpdate();
            
        }catch (SQLException se){
            se.printStackTrace();
            throw new ServiceLocatorException(se);
        }finally{
            try{
                if(preparedStatement != null){
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            }catch (SQLException se){
                  se.printStackTrace();
                throw new ServiceLocatorException(se);
            }
        }
        return isInserted;
        }
       public PerformanceVTO getMetrics(int id,String metricNme)
    throws ServiceLocatorException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        DateUtility dateUtil;
        PerformanceVTO performanceVTO = new PerformanceVTO();
        String attachmentId="";
        String sqlQuery = "select * from tblEmpMetrics where Id=" + id;
        //System.out.println("sqlQuery-->"+sqlQuery);
        dateUtil = DateUtility.getInstance();
        String uploadFileName = null;
        String category = null;
        int taskId=0;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sqlQuery);
            resultSet = preparedStatement.executeQuery();
            performanceVTO.setId(id);
            resultSet.next();
         
            performanceVTO.setMetricName(resultSet.getString("MetricName"));
            performanceVTO.setStatusId(resultSet.getString("Status"));
            performanceVTO.setComments(resultSet.getString("Description"));
            StringBuilder sb = new StringBuilder();
            sb.append("");
            sb.append(resultSet.getInt("MinValue"));
            String min = sb.toString();
            performanceVTO.setMinValue(min);
              StringBuilder sb1 = new StringBuilder();
            sb1.append("");
            sb1.append(resultSet.getInt("MaxValue"));
            String max = sb1.toString();
             performanceVTO.setMaxValue(max);
          

        } catch(SQLException se){
            se.printStackTrace();
            throw new ServiceLocatorException(se);
        }finally{
            try{
                if(resultSet != null){ 
                    resultSet.close();
                    resultSet = null;
                }
                if(preparedStatement != null){
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
    return performanceVTO;
  }
       
   public  int updateMetrics(PerformanceReviewsAction performanceReviewsAction,HttpServletRequest httpServletRequest,String metricName,String minvalue,String maxValue,String status,String desc)throws ServiceLocatorException, SQLException{
         
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        int isUpdated = 0;
        String queryString="UPDATE `tblEmpMetrics` SET `MetricName` = ? , `MinValue` = ? , `MaxValue` = ? , `Status` = ? , `ModifiedDate` = ? , `ModifiedBy` = ? , `Description` = ? WHERE `Id` = ?" ;        
        

        
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1,performanceReviewsAction.getMetricName());
            preparedStatement.setInt(2,Integer.parseInt(performanceReviewsAction.getMinValue()));
            preparedStatement.setInt(3,Integer.parseInt(performanceReviewsAction.getMaxValue()));
            preparedStatement.setString(4,performanceReviewsAction.getStatusId());
            preparedStatement.setTimestamp(5,DateUtility.getInstance().getCurrentMySqlDateTime());
            preparedStatement.setString(6,httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
            preparedStatement.setString(7,performanceReviewsAction.getComments());
            preparedStatement.setInt(8,performanceReviewsAction.getId());
            isUpdated = preparedStatement.executeUpdate();
            
        }catch (SQLException se){
            se.printStackTrace();
            throw new ServiceLocatorException(se);
        }finally{
            try{
                if(preparedStatement != null){
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            }catch (SQLException se){
                  se.printStackTrace();
                throw new ServiceLocatorException(se);
            }
        }
        return isUpdated;
        }  
   
   public  int addTitle(PerformanceReviewsAction performanceReviewsAction,HttpServletRequest httpServletRequest,String metricId,String status,String desc,String deptId,String titleId,String weightage)throws ServiceLocatorException, SQLException{
     Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        int isInserted = 0;
        //String queryString = "INSERT INTO tblEmpMetrics(MetricName,MinValue,MaxValue,Status,CreatedBy,Description) VALUES(?,?,?,?,?,?)";
        String queryString="INSERT INTO `mirage`.`tblTitleMetrics` (`DepartmentId`, `TitleTypeId`, `MetricId`, `Weightage`,`CreatedBy`, `Status`, `Description`)"
                + " VALUES(?,?,?,?,?,?,?)";
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1,deptId);
            preparedStatement.setString(2,titleId);
            preparedStatement.setInt(3,Integer.parseInt(metricId));
            preparedStatement.setInt(4,Integer.parseInt(weightage));
            preparedStatement.setString(5,performanceReviewsAction.getCreatedBy());
            preparedStatement.setString(6,status);
            preparedStatement.setString(7,desc);
            isInserted = preparedStatement.executeUpdate();
            
        }catch (SQLException se){
            se.printStackTrace();
            throw new ServiceLocatorException(se);
        }finally{
            try{
                if(preparedStatement != null){
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            }catch (SQLException se){
                  se.printStackTrace();
                throw new ServiceLocatorException(se);
            }
        }
        return isInserted;
   
   }
    public PerformanceVTO getTitleMetrics(int id)throws ServiceLocatorException, SQLException{
     Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        DateUtility dateUtil;
        PerformanceVTO performanceVTO = new PerformanceVTO();
        String attachmentId="";
        String sqlQuery = "select * from tblTitleMetrics where Id=" + id;
        //System.out.println("sqlQuery-->"+sqlQuery);
        dateUtil = DateUtility.getInstance();
        String uploadFileName = null;
        String category = null;
        int taskId=0;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sqlQuery);
            resultSet = preparedStatement.executeQuery();
            performanceVTO.setId(id);
            resultSet.next();
         performanceVTO.setDepartmentId(resultSet.getString("DepartmentId"));
         performanceVTO.setTitleId(resultSet.getString("TitleTypeId"));
         //System.out.println(performanceVTO.getTitleId());
            StringBuilder sb = new StringBuilder();
            sb.append("");
            sb.append(resultSet.getInt("MetricId"));
            String metricId = sb.toString();
            performanceVTO.setMetricId(metricId);
            String metricDetails=DataSourceDataProvider.getInstance().getMetricDetailsByMetricId(resultSet.getInt("MetricId"));
            performanceVTO.setMetricNameForTitle(metricDetails.split(",")[0]);
            performanceVTO.setMinValueForTitle(metricDetails.split(",")[1]);
            performanceVTO.setMaxValueForTitle(metricDetails.split(",")[2]);
            performanceVTO.setStatusIdForTitle(resultSet.getString("Status"));
            performanceVTO.setCommentsForTitle(resultSet.getString("Description"));
            performanceVTO.setWeightage(resultSet.getString("Weightage"));
           
          

        } catch(SQLException se){
            se.printStackTrace();
            throw new ServiceLocatorException(se);
        }finally{
            try{
                if(resultSet != null){ 
                    resultSet.close();
                    resultSet = null;
                }
                if(preparedStatement != null){
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
    return performanceVTO;
    
    }
    
     public  int updateTitles(PerformanceReviewsAction performanceReviewsAction,HttpServletRequest httpServletRequest,String metricId,String minvalue,String maxValue,String status,String desc,String deptId,String titleId,String weightage)throws ServiceLocatorException, SQLException{
      Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        int isUpdated = 0;
        String queryString="UPDATE `mirage`.`tblTitleMetrics` SET `DepartmentId` = ? , `TitleTypeId` = ? , `MetricId` = ? , `Weightage` = ? , `Status` = ? , `ModifiedBy` = ? , `ModifiedDate` = ? , `Description` = ? WHERE `Id` = ?" ;        
        

        
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1,deptId);
            preparedStatement.setString(2,titleId);
            preparedStatement.setInt(3,Integer.parseInt(metricId));
            preparedStatement.setInt(4,Integer.parseInt(weightage));
            preparedStatement.setString(5,status);
            preparedStatement.setString(6,httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
            preparedStatement.setTimestamp(7,DateUtility.getInstance().getCurrentMySqlDateTime());
            preparedStatement.setString(8,desc);
            preparedStatement.setInt(9,performanceReviewsAction.getId());
            isUpdated = preparedStatement.executeUpdate();
            
        }catch (SQLException se){
            se.printStackTrace();
            throw new ServiceLocatorException(se);
        }finally{
            try{
                if(preparedStatement != null){
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            }catch (SQLException se){
                  se.printStackTrace();
                throw new ServiceLocatorException(se);
            }
        }
        return isUpdated;
     
     }
   */  
     public  int addPerformance(PerformanceReviewsAction performanceReviewsAction,HttpServletRequest httpServletRequest,String empLoginId,String dept,String title,int callOutBound,int appointment,int conferenceCalls,int meeting,int oppurtunity,int requirement,String commentsForPerformance)throws ServiceLocatorException, SQLException{
     
     Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        int isInserted = 0;
        //String queryString = "INSERT INTO tblEmpMetrics(MetricName,MinValue,MaxValue,Status,CreatedBy,Description) VALUES(?,?,?,?,?,?)";
        String queryString="INSERT INTO `mirage`.`tblEmpPerformance` (`EmpId`, `DepartmentName`, `Title`, `StartDate`,`EndDate`, `CreatedBy`,`CallOutbound`,`Appointment`,`ConferenceCall`,`Meeting`,`Oppurtunity`,`Requirement`,Comments)"
                + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1,empLoginId);
            preparedStatement.setString(2,dept);
            preparedStatement.setString(3,title);
            preparedStatement.setTimestamp(4,performanceReviewsAction.getFromDate());
            preparedStatement.setTimestamp(5,performanceReviewsAction.getToDate());
            preparedStatement.setString(6,performanceReviewsAction.getCreatedBy());
             preparedStatement.setInt(7,callOutBound);
              preparedStatement.setInt(8,appointment);
              preparedStatement.setInt(9,conferenceCalls);
              preparedStatement.setInt(10,meeting);
              preparedStatement.setInt(11,oppurtunity);
              preparedStatement.setInt(12,requirement);
              preparedStatement.setString(13,commentsForPerformance);
            isInserted = preparedStatement.executeUpdate();
            
        }catch (SQLException se){
            se.printStackTrace();
            throw new ServiceLocatorException(se);
        }finally{
            try{
                if(preparedStatement != null){
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            }catch (SQLException se){
                  se.printStackTrace();
                throw new ServiceLocatorException(se);
            }
        }
        return isInserted;
   
     
     }
     public  int addPerformanceLines(PerformanceReviewsAction performanceReviewsAction,HttpServletRequest httpServletRequest,String metricId,String rangeValue,String weightage)throws ServiceLocatorException, SQLException{
     
     
     Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
             Connection connection1 = null;
        PreparedStatement preparedStatement1 = null;
        ResultSet resultSet1 = null;
        int isInserted = 0;
        int performanceId=0;
        //String queryString = "INSERT INTO tblEmpMetrics(MetricName,MinValue,MaxValue,Status,CreatedBy,Description) VALUES(?,?,?,?,?,?)";
        String queryString="INSERT INTO `tblEmpPerformanceLines` (`PerformanceId`, `MetricId`, `Value`, `Weightage`)"
                + " VALUES(?,?,?,?)";
           String queryString1="select max(Id) as Id from tblEmpPerformance";
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString1);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
            performanceId=resultSet.getInt("Id");
            }
            //System.out.println("performanceId-->"+performanceId);
              connection1 = ConnectionProvider.getInstance().getConnection();
            preparedStatement1 = connection1.prepareStatement(queryString);
             preparedStatement1.setInt(1,performanceId);
            preparedStatement1.setInt(2,Integer.parseInt(metricId));
            preparedStatement1.setString(3,rangeValue);
            preparedStatement1.setString(4,weightage);
             isInserted = preparedStatement1.executeUpdate();
        }catch (SQLException se){
            se.printStackTrace();
            throw new ServiceLocatorException(se);
        }finally{
            try{
                if(preparedStatement != null){
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
                  if(preparedStatement1 != null){
                    preparedStatement1.close();
                    preparedStatement1 = null;
                }
                if(connection1 != null){
                    connection1.close();
                    connection1 = null;
                }
                 if(resultSet != null){ 
                    resultSet.close();
                    resultSet = null;
                }
            }catch (SQLException se){
                  se.printStackTrace();
                throw new ServiceLocatorException(se);
            }
        }
        return isInserted;
   
     
     }
     
   /* public PerformanceVTO getPerformanceValues(int id)throws ServiceLocatorException, SQLException{
      Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        DateUtility dateUtil;
        int count=1;
        PerformanceVTO performanceVTO = new PerformanceVTO();
     
   //     String sqlQuery = "SELECT * AS val FROM tblEmpPerformance LEFT OUTER JOIN tblEmpPerformanceLines ON(tblEmpPerformance.Id=tblEmpPerformanceLines.PerformanceId) WHERE tblEmpPerformance.Id=" + id;
        //System.out.println("sqlQuery-->"+sqlQuery);
        dateUtil = DateUtility.getInstance();
        try {
          connection = ConnectionProvider.getInstance().getConnection();
            
            if(connection!=null) {
                
                statement = connection.createStatement();
                  resultSet = statement.executeQuery("SELECT * from tblEmpPerformance where Id="+id);
   
            performanceVTO.setId(id);
               while(resultSet.next()) {
                   performanceVTO.setEmpNameLoginId(resultSet.getString("EmpId"));
           performanceVTO.setEmpName(DataSourceDataProvider.getInstance().getFname_Lname(resultSet.getString("EmpId")));        
           performanceVTO.setDepartmentName(resultSet.getString("DepartmentName"));
           performanceVTO.setEmpTitle(resultSet.getString("Title"));
           performanceVTO.setFromDate(DateUtility.getInstance().convertDateToView(resultSet.getDate("StartDate")));
           performanceVTO.setToDate(DateUtility.getInstance().convertDateToView(resultSet.getDate("EndDate")));
               }
                resultSet.close(); // clsoing connection
                resultSet = null;
                
                   
            
            }

        } catch(SQLException se){
            se.printStackTrace();
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
                if(connection!=null){
                    connection.close();
                    connection = null;
                }
            }catch(SQLException sqle){
                throw new ServiceLocatorException(sqle);
            }
    }
    return performanceVTO;
    
    }  */
     
}