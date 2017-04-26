/*
 *******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package : com.mss.mirage.employee.genaral
 *
 * Date    :  September 24, 2007, 10:18 PM
 *
 * Author  : Praveen Kumar Ralla<pralla@miraclesoft.com>
 *
 * File    : EmployeeServiceImpl.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.employee.Reviews;


import com.mss.mirage.employee.general.StateVTO;
import com.mss.mirage.util.HibernateServiceLocator;
import com.mss.mirage.util.MailManager;
import com.mss.mirage.util.PasswordUtility;
import com.mss.mirage.util.ServiceLocatorException;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.Properties;
import java.sql.CallableStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * @(#)EmployeeService.java 1.0 November 01, 2007
 *
 * Copyright 2006 Miracle Software Systems(INDIA) Pvt Ltd. All rights reserved.
 *
 * @see com.mss.mirage.util.MailManager
 * @see com.mss.mirage.util.PasswordUtility
 * @see com.mss.mirage.util.ServiceLocatorException
 * @see com.mss.mirage.util.HibernateServiceLocator
 * @see com.mss.mirage.employee.general.EmployeeService
 *
 * @since 1.0
 */
public class ReviewsServiceImpl implements ReviewsService{
    
    /** Creates a new instance of EmployeeSeviceImpl */
    public ReviewsServiceImpl() {
        
    }
    
    
    
    public boolean getInsertReview(ReviewsAction reviewPojo) throws ServiceLocatorException {
        
          Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        boolean isInserted = false;
        String queryString = "insert into tblEmpReview (ReviewTypeId,EmpComments,AttachmentName,AttachmentLocation,AttachmentFileName,EmpId,CreatdBy,ReviewName) values(?,?,?,?,?,?,?,?)";
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1,reviewPojo.getReviewType());
            preparedStatement.setString(2,reviewPojo.getNotes());
            preparedStatement.setString(3,reviewPojo.getAttachmentName());
            preparedStatement.setString(4,reviewPojo.getAttachmentLocation());
            preparedStatement.setString(5,reviewPojo.getUploadFileName());
            preparedStatement.setInt(6,reviewPojo.getEmpId());
            preparedStatement.setString(7,reviewPojo.getCreatedBy());
             preparedStatement.setString(8,reviewPojo.getReviewName());
            
            isInserted = preparedStatement.execute();
            
        }catch (SQLException se){
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
                throw new ServiceLocatorException(se);
            }
        }
        
        return isInserted;
        
    }
    
   
     public ReviewVTO getReview(int reviewId) throws ServiceLocatorException{
         Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ReviewVTO reviewVTO = new ReviewVTO();
        boolean isInserted = false;
        String  queryString="SELECT * FROM tblEmpReview  where Id="+reviewId;
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
           
            
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                reviewVTO.setReviewId(resultSet.getInt("Id"));
                reviewVTO.setReviewType(resultSet.getString("ReviewTypeId"));
                reviewVTO.setNotes(resultSet.getString("EmpComments"));
                reviewVTO.setAttachmentLocation(resultSet.getString("AttachmentLocation"));
                reviewVTO.setAttachmentFileName(resultSet.getString("AttachmentFileName"));
                reviewVTO.setReviewName(resultSet.getString("ReviewName"));
                reviewVTO.setHrComments(resultSet.getString("HrComments"));
                reviewVTO.setTlComments(resultSet.getString("TLComments"));
            }
            
        }catch (SQLException se){
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
                throw new ServiceLocatorException(se);
            }
        }
        
        return reviewVTO;
        
     }
    
     public boolean doEditReview(ReviewsAction reviewsAction) throws ServiceLocatorException
     {
          Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        boolean isInserted = false;
        String queryString = "UPDATE tblEmpReview SET ReviewTypeId =?,EmpComments=?,ModifiedBy=?,ModifiedDate=?,ReviewName=?,HrComments=?,TLComments=? WHERE Id=?";
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1,reviewsAction.getReviewType());
            preparedStatement.setString(2,reviewsAction.getNotes());
           
            preparedStatement.setString(3,reviewsAction.getCreatedBy());
            preparedStatement.setTimestamp(4,DateUtility.getInstance().getCurrentMySqlDateTime());
             preparedStatement.setString(5,reviewsAction.getReviewName());
               preparedStatement.setString(6,reviewsAction.getHrComments());
                 preparedStatement.setString(7,reviewsAction.getTlComments());
            preparedStatement.setInt(8, reviewsAction.getReviewId());
            isInserted = preparedStatement.execute();
            
        }catch (SQLException se){
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
                throw new ServiceLocatorException(se);
            }
        }
        
        return isInserted;
     }
   
    public boolean deleteReview(int reviewId) throws ServiceLocatorException{
          Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        boolean isDeleted = false;
        String queryString = "DELETE FROM tblEmpReview WHERE Id=?";
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
         
            preparedStatement.setInt(1, reviewId);
            isDeleted = preparedStatement.execute();
            
        }catch (SQLException se){
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
                throw new ServiceLocatorException(se);
            }
        }
        
        return isDeleted;
    }
  public List getCompetitionBoard(String empName,String department,String startDate,String endDate) throws ServiceLocatorException{
       Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ReviewVTO reviewVTO = null;
        List competitionList = new ArrayList();
        boolean isInserted = false;
     //   String  queryString="SELECT * FROM tblEmpReview  where Id="+reviewId;
        StringBuffer queryString = new StringBuffer();
        try{
          queryString.append("SELECT tblEmployee.Id AS Id,CONCAT(tblEmployee.FName,' ',tblEmployee.LName) AS EmpName,COUNT(tblEmpReview.Id) AS ReviwCount,tblEmployee.PracticeId AS Region ,tblEmployee.TeamId AS Practice,");
            queryString.append("tblEmployee.DepartmentId AS DepartmentId,tblEmpReview.UserId  AS UserId,tblEmployee.TitleTypeId     AS TitleTypeId ,");
            queryString.append("(SUM(IF((tblEmpReview.STATUS = 'Approved' AND tblEmpReview.HRStatus = 'Approved'), TLRating, 0)) +SUM(IF((tblEmpReview.STATUS = 'Approved' AND tblEmpReview.HRStatus = 'Approved'), HRRating, 0))) AS Score,");
            queryString.append("tblEmployee.ReportsTo AS ReportingManager, SUM(CASE WHEN (tblEmpReview.STATUS = 'Approved' AND tblEmpReview.HRStatus = 'Approved')  THEN 1 ELSE 0 END) AS totalApproved,");
            queryString.append("SUM(CASE WHEN ((tblEmpReview.STATUS = 'Opened' AND tblEmpReview.HRStatus = 'Opened') OR (tblEmpReview.STATUS = 'Approved' AND tblEmpReview.HRStatus = 'Opened') OR (tblEmpReview.STATUS = 'Opened' AND tblEmpReview.HRStatus = 'Approved')) THEN 1 ELSE 0 END) AS totalPending ,");
            queryString.append("SUM(CASE WHEN (tblEmpReview.STATUS = 'Denied' OR  tblEmpReview.HRStatus = 'Denied') THEN 1 ELSE 0 END) AS totalDenied ,");
            queryString.append("COUNT(tblEmpReview.Id) AS totalSubmitted ,tblEmployee.isManager ,tblEmployee.isTeamLead ");
        queryString.append("FROM (tblEmployee LEFT JOIN tblEmpReview ");
        queryString.append("ON ((tblEmployee.LoginId = CONVERT(tblEmpReview.UserId USING utf8)))) ");

            queryString.append("WHERE CurStatus = 'Active' AND ((tblEmpReview.ReviewDate >= '" + DateUtility.getInstance().convertStringToMySQLDate(startDate) + "') ");
        queryString.append("AND (tblEmpReview.ReviewDate <= '" + DateUtility.getInstance().convertStringToMySQLDate(endDate) + "')) ");

//            if(empName!=null&&!"".equals(empName)){
//                queryString.append(" AND (FName LIKE '"+empName+"%' OR LName LIKE '"+empName+"%') ");
//            }
              if(department!=null&&!"".equals(department)){
                 if(department.equals("Development")){
                    queryString.append(" AND (DepartmentId='GDC'OR DepartmentId= 'SSG')"); 
                 }else{
                  queryString.append(" AND DepartmentId='"+department+"' ");
                 }
             }
            queryString.append("  GROUP BY tblEmployee.Id ORDER BY Score DESC");
            
            
            System.out.println("Query-->"+queryString.toString());
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString.toString());
           
            
            resultSet = preparedStatement.executeQuery();
            int rank = 0;
            int i=0;
             ArrayList alist = new ArrayList();
            while (resultSet.next()) {
                  if(resultSet.getInt("totalSubmitted")!=0  && resultSet.getInt("Score")>1){
                int score = resultSet.getInt("Score")/2;
                 reviewVTO = new ReviewVTO();
               
                        if (resultSet.getInt("isManager") == 1 || resultSet.getInt("isTeamLead") == 1) {
                                System.out.println("score of ===" + resultSet.getString("UserId"));
                                  //  System.out.println("own score-->" + score);
                                score += DataSourceDataProvider.getInstance().getScore(resultSet.getString("UserId"), startDate, endDate);
                               // System.out.println("own score2-->" + score);
                }
                
                
                double percent = ((double) (resultSet.getInt("totalApproved")) / resultSet.getInt("totalSubmitted")) * 100;
                reviewVTO.setEmployeeName(resultSet.getString("EmpName"));
                reviewVTO.setDesignation(resultSet.getString("TitleTypeId"));
                reviewVTO.setDepartment(resultSet.getString("DepartmentId"));
               

                reviewVTO.setReportingManager(resultSet.getString("ReportingManager"));
                reviewVTO.setReviewsSubmitted(resultSet.getInt("totalSubmitted"));
                reviewVTO.setReviewsApproved(resultSet.getInt("totalApproved"));
                reviewVTO.setScore(score);
                reviewVTO.setUserId(resultSet.getString("UserId"));
                reviewVTO.setReviewsPending(resultSet.getInt("totalPending"));
                reviewVTO.setReviewsRejected(resultSet.getInt("totalDenied"));
                reviewVTO.setRegion(resultSet.getString("Region"));
                    reviewVTO.setPractice(resultSet.getString("Practice"));
                reviewVTO.setReviewApprovalPercentage(Double.parseDouble(new DecimalFormat("##.##").format(percent)));
                competitionList.add(reviewVTO);
                //response = response+resultSet.getString("EmpName")+"#^$"+ resultSet.getString("TitleTypeId")+"#^$"+ resultSet.getString("DepartmentId")+"#^$"+i+"#^$"+ score+"*@!";

                  }
            }

            
                System.out.println("test check");
                Collections.sort(competitionList);
                for (Object obj : competitionList) {
                i++;
                 reviewVTO = (ReviewVTO) obj;
                reviewVTO.setRank(i);
                
              //  response =  reviewVTO.getRank() + "#^$" + reviewVTO.getScore() + "#^$" + reviewVTO.getReportingManager() + "#^$" + reviewVTO.getReviewsSubmitted() + "#^$" + reviewVTO.getReviewsApproved() + "#^$" + reviewVTO.getReviewApprovalPercentage()+"#^$" +reviewVTO.getReviewsRejected()+"#^$" +reviewVTO.getReviewsPending();
                 
            
            }
             if(empName!=null&&!"".equals(empName)){
                 ArrayList newcompetitionList=new ArrayList();
                  for(Object obj:competitionList){
               
                ReviewVTO rVTO=(ReviewVTO)obj;
                     
                if(rVTO.getEmployeeName().toLowerCase().contains(empName.toLowerCase())){
                    newcompetitionList.add(rVTO);
                }
                  }
                  competitionList=newcompetitionList;
             }
            
        }catch (SQLException se){
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
                throw new ServiceLocatorException(se);
            }
        }
        
        return competitionList;
   }


}
