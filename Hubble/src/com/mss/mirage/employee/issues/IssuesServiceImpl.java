

package com.mss.mirage.employee.issues;

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
import java.util.Iterator;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;



public class IssuesServiceImpl implements IssuesService {
    
    public IssuesServiceImpl() {
        
    }
    public IssuesVTO getIssue(String IssueId, int objectId)
    throws ServiceLocatorException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        DateUtility dateUtil;
        IssuesVTO issueVTO = new IssuesVTO();
        String attachmentId="";
        String sqlQuery = "select * from tblEmpIssues where Id=" + IssueId;
        dateUtil = DateUtility.getInstance();
        String uploadFileName = null;
        String category = null;
        int taskId=0;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sqlQuery);
            resultSet = preparedStatement.executeQuery();
            issueVTO.setId(Integer.parseInt(IssueId));
            resultSet.next();
            attachmentId =  resultSet.getString("AttachmentId");
            if(attachmentId == null) {
                attachmentId="0";
            }
            category = resultSet.getString("Category");
            issueVTO.setCategoryId(category);
            issueVTO.setCreatedBy(resultSet.getString("CreatedBy"));
            issueVTO.setSubCategoryId(resultSet.getString("SubCategory"));
            issueVTO.setSeverityId(resultSet.getString("Severity"));
            issueVTO.setStatusId(resultSet.getString("Status"));
            issueVTO.setTypeId(resultSet.getString("IssueType"));
            issueVTO.setCreated(dateUtil.sqlTimeStampTousTimeStamp(resultSet.getString("DateCreated")));
            issueVTO.setAssigned(dateUtil.sqlTimeStampTousTimeStamp(resultSet.getString("DateAssigned")));
            issueVTO.setClosed(dateUtil.sqlTimeStampTousTimeStamp(resultSet.getString("DateClosed")));
            
            issueVTO.setStatus(resultSet.getString("Status"));
            issueVTO.setComments(resultSet.getString("Comments"));
            issueVTO.setAttachmentId(resultSet.getInt("AttachmentId"));
            issueVTO.setDescription(resultSet.getString("Description"));
            issueVTO.setResolution(resultSet.getString("Resolution"));
            taskId = resultSet.getInt("TaskId");
            issueVTO.setTaskId(taskId);
            issueVTO.setIFlag(Integer.parseInt(resultSet.getString("iFlag")));
            issueVTO.setIssueName(resultSet.getString("IssueTitle"));
            //issueVTO.setPreAssignEmpId(resultSet.getString("IssueTitle"));
            
            issueVTO.setPerCompleted(resultSet.getString("PercentageComplted"));
            issueVTO.setCustomerId(resultSet.getString("TaskId"));
            if(!resultSet.getString("TaskId").equals("0") && resultSet.getInt("Iflag")!=2){ 
            issueVTO.setCustomerName(DataSourceDataProvider.getInstance().getAccountName(Integer.parseInt(issueVTO.getCustomerId())));
            }else{
             issueVTO.setCustomerName(DataSourceDataProvider.getInstance().getConsultantName(Integer.parseInt(issueVTO.getCustomerId()))); 
            }
            issueVTO.setProjectName(resultSet.getString("ProjectId"));
            
            
            issueVTO.setAssignedToUID(DataSourceDataProvider.getInstance().getFname_Lname(resultSet.getString("AssignedTo")));
            issueVTO.setPostAssignedToUID(DataSourceDataProvider.getInstance().getFname_Lname(resultSet.getString("SecAssignTO")));
            
          if(resultSet.getString("AssignedTo")!=null)
            issueVTO.setPreAssignEmpId(resultSet.getString("AssignedTo"));
            if(resultSet.getString("SecAssignTO")!=null)
            issueVTO.setPostAssignEmpId(resultSet.getString("SecAssignTO"));
            
            
            issueVTO.setActivityId(Integer.parseInt(resultSet.getString("ActivityId")));
            //New 
            //DataSourceDataProvider
           /* if(taskId > 0 ){
                   if(category.equalsIgnoreCase("Sales")){  
                        PreparedStatement PS = null;
                        ResultSet RS = null;
                       try{
                           String Query = "SELECT AccountId,contactID FROM tblCrmActivity WHERE Id = "+taskId;
                          // System.out.print("QQ:-->"+Query);
                           connection = ConnectionProvider.getInstance().getConnection();
                           PS = connection.prepareStatement(Query);
                           RS = PS.executeQuery();
                           RS.next();
                           int AccountId=RS.getInt("AccountId");
                           int ContactId=RS.getInt("contactID");
                           issueVTO.setContactId(ContactId);
                           issueVTO.setRefName(DataSourceDataProvider.getInstance().getAccountName(AccountId));
                           issueVTO.setContactName(DataSourceDataProvider.getInstance().getContactName(ContactId));
                           
                       }catch(SQLException se){
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
                     /*  String AccountName=DataSourceDataProvider.getInstance().getAccountName(taskId);
                       issueVTO.setRefName(AccountName);*/
                  
                 /*  }else if(category.equalsIgnoreCase("Recruiting")){
                    issueVTO.setRefName(DataSourceDataProvider.getInstance().getConsultantNameByActivityId(taskId));   
                   }
            }  */ 
        } catch(SQLException se){
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
        
        
        if(objectId != 0 && !attachmentId.equalsIgnoreCase("0")){
            
            try{
                
                sqlQuery = "select * from tblEmpAttachments where Id=" + Integer.parseInt(attachmentId);
                //sqlQuery = "select * from tblEmpAttachments where ObjectId=" + objectId;
                
                
                connection = ConnectionProvider.getInstance().getConnection();
                preparedStatement = connection.prepareStatement(sqlQuery);
                Integer.parseInt(attachmentId);
                resultSet = preparedStatement.executeQuery();
                
                resultSet.next();
                issueVTO.setAttachmentId(Integer.parseInt(attachmentId));
                issueVTO.setObjectid(String.valueOf(resultSet.getInt("ObjectId")));
                issueVTO.setObjectType(resultSet.getString("ObjectType"));
                issueVTO.setFileLocation(resultSet.getString("AttachmentLocation"));
                uploadFileName = resultSet.getString("AttachmentFileName");
//                if( " ".equalsIgnoreCase(uploadFileName) || uploadFileName == null) {
//                    uploadFileName="No File Attached";
//                    issueVTO.setUploadFileName(uploadFileName);
//                } else{
//                    issueVTO.setUploadFileName(resultSet.getString("AttachmentFileName"));
//                }
                issueVTO.setUploadFileName(resultSet.getString("AttachmentFileName"));
                issueVTO.setDate(resultSet.getTimestamp("DateUploaded"));
                
                
            }catch(SQLException se){
                throw new ServiceLocatorException(se);
            }finally {
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
        }else {
            uploadFileName="No File Attached";
            issueVTO.setUploadFileName(uploadFileName);
        }
        return issueVTO;
    }
    
    
    
    public int addOrUpdateIssue(IssuesAction issueAction)
    throws ServiceLocatorException, SQLException {
        Connection connection = null;
        ResultSet resultSet = null;
        CallableStatement callableStatement= null;
        int updatedRows=0;
        int isSuccess=0;
        int issueMaxId = 0;
        try {
            
            
            DateUtility dateUtil = DateUtility.getInstance();
            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("{call spEmpIssues(" +
                    "?,?,?,?,?," +
                    "?,?,?,?,?," +
                    "?,?,?,?,?," +
                    "?,?,?,?,?," +
                    "?, ?,?,?,?," +
                    "?,?,?) }");

            
            callableStatement.setInt(1, issueAction.getId());
            
            callableStatement.setString(2, issueAction.getCategoryId());
            
            callableStatement.setString(3, issueAction.getCreatedBy());
            callableStatement.setTimestamp(4, dateUtil.getCurrentMySqlDateTime());
            callableStatement.setTimestamp(5, dateUtil.strToTimeStampObj(issueAction.getDateAssigned()));
           if(issueAction.getDateClosed()!=null)
            callableStatement.setTimestamp(6, dateUtil.strToTimeStampObj(issueAction.getDateClosed()));
           else
            callableStatement.setTimestamp(6,dateUtil.strToTimeStampObj(issueAction.getDateAssigned()));
            
            //callableStatement.setString(7, issueAction.getAssignedToUID());  
          //  callableStatement.setString(7, issueAction.getPreAssignEmpId());  
            
            if(issueAction.getPreAssignEmpId() == null || "".equals(issueAction.getPreAssignEmpId())){
                 callableStatement.setString(7, "");
            }else{
               callableStatement.setString(7, issueAction.getPreAssignEmpId());
            }
            
            callableStatement.setString(8,issueAction.getStatusId());
            callableStatement.setString(9, issueAction.getComments());
            callableStatement.setInt(10, issueAction.getAttachmentId());
            callableStatement.setString(11, issueAction.getDescription());
            callableStatement.setString(12, issueAction.getSubCategoryId());
            callableStatement.setString(13, issueAction.getTypeId());
            callableStatement.setString(14, issueAction.getResolution());
            callableStatement.setString(15, issueAction.getSeverityId());
            callableStatement.setInt(16, issueAction.getObjectid());
            callableStatement.setString(17, issueAction.getObjectType());
            callableStatement.setString(18, issueAction.getAttachmentName());
            callableStatement.setString(19, issueAction.getFileLocation());
            callableStatement.setString(20, issueAction.getUploadFileName());
            callableStatement.setTimestamp(21, dateUtil.getCurrentMySqlDateTime());
            callableStatement.setString(22, issueAction.getOperationType());
            
            if(issueAction.getPostAssignEmpId() == null || "".equals(issueAction.getPostAssignEmpId())){
                 callableStatement.setString(23, "");
            }else{
               callableStatement.setString(23, issueAction.getPostAssignEmpId());
            }
            
            callableStatement.setString(24, issueAction.getIssueName());
            if(issueAction.getPerCompleted() == null || "".equals(issueAction.getPerCompleted())){
                callableStatement.setString(25, "0");
            }else{
              callableStatement.setString(25, issueAction.getPerCompleted()); 
            }
            
            callableStatement.setInt(26, Integer.parseInt(issueAction.getCustomerId()));
            
            if(issueAction.getProjectName() == null || "".equals(issueAction.getProjectName())){
                 callableStatement.setInt(27,0);
            } else{
                callableStatement.setInt(27,Integer.parseInt(issueAction.getProjectName()));
            }
            callableStatement.registerOutParameter(28,Types.INTEGER);
            updatedRows = callableStatement.executeUpdate();
            
            issueMaxId = callableStatement.getInt(28);
            if(updatedRows==1 && issueAction.getOperationType().equals("Upd")){
            if(issueAction.getCategoryId().equals("Sales") && issueAction.getIFlag() == 1){
                PreparedStatement preStmt=null;
                
                preStmt = connection.prepareStatement("UPDATE tblCrmActivity set ActivityType='"+issueAction.getTypeId()+"', Status='"+issueAction.getStatusId()+"', "+
                        " Comments='"+issueAction.getComments()+"',Description='"+issueAction.getDescription()+"', ModifiedDate='"+dateUtil.getCurrentMySqlDateTime()+"', ModifiedById='"+issueAction.getCreatedBy()+"' WHERE Id="+issueAction.getActivityId() );
                 isSuccess = preStmt.executeUpdate(); 
                  updatedRows = 1;
                
            }else if(issueAction.getCategoryId().equals("Recruiting") && issueAction.getIFlag() == 2){
                PreparedStatement preStatement=null;
             
                //new
                 preStatement = connection.prepareStatement("UPDATE tblRecActivity set "+
                                                  "Status='"+issueAction.getStatusId()+"', Comments='"+issueAction.getComments()+"', ModifiedDate='"+dateUtil.getCurrentMySqlDateTime()+"', ModifiedBy='"+issueAction.getCreatedBy()+"' WHERE Id="+issueAction.getActivityId());
                 
                 isSuccess = preStatement.executeUpdate();
                  updatedRows = 1;
            }else {
               updatedRows = 1; 
            }
            
            }
            
        }
        // Misplaced declaration of an exception variable
        catch(SQLException se) {
            throw new ServiceLocatorException(se.getMessage());
        }finally{
            try{
                if(callableStatement!=null){
                    callableStatement.close();
                    callableStatement = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            }catch(SQLException sqle){
                throw new ServiceLocatorException(sqle);
            }
        }
        
        
        if(issueAction.getOperationType().equals("Ins")) {
            return issueMaxId;
        }
        else {
            
        return updatedRows;
        }
    }
    
    
    public String getAttachmentLocation(int attachmentId) throws ServiceLocatorException {
        String attachmentLocation=null;
        Session session = HibernateServiceLocator.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String SQL_QUERY ="Select h.attachmentLocation from AttachmentAction as h where h.id=:attachmentId";
    
        Query query = session.createQuery(SQL_QUERY).setInteger("attachmentId",attachmentId);
    
        for(Iterator it=query.iterate();it.hasNext();){
            attachmentLocation = (String) it.next();
        }//end of the for loop
    
        session.close();
    
        return attachmentLocation;
    }
 

    public String getEmpTaskAttachmentLocation(int attachmentId) throws ServiceLocatorException {
       PreparedStatement preparedStatement=null;
       Connection con=null;
       ResultSet resultSet=null;
       String attachmentLocation="";
       try{
             con=ConnectionProvider.getInstance().getConnection();
             String query="SELECT AttachmentLocation FROM tblEmpAttachments WHERE Id=?";
             preparedStatement=con.prepareStatement(query);
             preparedStatement.setInt(1, attachmentId);
             resultSet=preparedStatement.executeQuery();
             while(resultSet.next()){
              attachmentLocation=resultSet.getString("AttachmentLocation")  ;
             }
             
       }catch(SQLException ex){
         ex.printStackTrace();
           System.out.println("SQLException thrown");
       }  
        return attachmentLocation;
                       
    }	
	      
}