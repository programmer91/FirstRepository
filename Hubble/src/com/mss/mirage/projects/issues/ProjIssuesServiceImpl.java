/*
 * ProjIssuesServiceImpl.java
 *
 * Created on April 23, 2008, 8:52 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.projects.issues;

import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.HibernateServiceLocator;
import com.mss.mirage.util.ServiceLocatorException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author miracle
 */
public class ProjIssuesServiceImpl implements ProjIssuesService{
    
    /** Creates a new instance of ProjIssuesServiceImpl */
    public ProjIssuesServiceImpl() {
    }
    
    public int addOrUpdateIssue(ProjIssuesAction issueAction)
    throws ServiceLocatorException, SQLException {
        
        Connection connection = null;
        ResultSet resultSet = null;
        CallableStatement callableStatement= null;
        int updatedRows=0;
        
        try {
            
            
            DateUtility dateUtil = DateUtility.getInstance();
            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("{call spProjIssues(?,?,?, ?,?,?, ?,?,?, ?,?,?, ?,?,?, ?,?,?, ?,?,?, ?,?,?, ?,?,?) }");
            
            callableStatement.setInt(1, issueAction.getId()); //0
            
            callableStatement.setString(2, issueAction.getCategoryId());  
            callableStatement.setString(3, issueAction.getCreatedBy());
            
            callableStatement.setTimestamp(4, dateUtil.getCurrentMySqlDateTime());
            callableStatement.setTimestamp(5, dateUtil.strToTimeStampObj(issueAction.getDateAssigned()));
            callableStatement.setTimestamp(6, dateUtil.strToTimeStampObj(issueAction.getDateClosed()));
            
            callableStatement.setString(7, issueAction.getAssignedToUID()); 
            callableStatement.setString(8,issueAction.getStatusId());
            callableStatement.setString(9, issueAction.getComments());
            
            callableStatement.setInt(10, issueAction.getAttachmentId()); //0
            callableStatement.setString(11, issueAction.getDescription());
            callableStatement.setString(12, issueAction.getSubCategoryId());
            
            callableStatement.setString(13, issueAction.getTypeId()); 
            callableStatement.setString(14, issueAction.getResolution()); //null
            callableStatement.setString(15, issueAction.getSeverityId());
            
            callableStatement.setInt(16, issueAction.getObjectid());
            callableStatement.setString(17, issueAction.getObjectType()); 
            callableStatement.setString(18, issueAction.getAttachmentName()); 
            callableStatement.setString(19, issueAction.getFileLocation());
            
            //callableStatement.setString(20, issueAction.getUploadFileName());
            callableStatement.setTimestamp(20, dateUtil.getCurrentMySqlDateTime());
            callableStatement.setString(21, issueAction.getOperationType());
            
            callableStatement.setInt(22, issueAction.getProjectId());            
            callableStatement.setInt(23, issueAction.getSubProjectId());            
            callableStatement.setInt(24, issueAction.getProjectMapId());
            callableStatement.setInt(25, issueAction.getAccountId());
            
            callableStatement.setString(26, issueAction.getMapNameId());
            //callableStatement.setString(27, issueAction.getIssueName());
            
            callableStatement.registerOutParameter(27, 4);
            updatedRows = callableStatement.executeUpdate();
            
        }
        // Misplaced declaration of an exception variable
        catch(SQLException se) {
            throw new ServiceLocatorException(se);
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
        return updatedRows;
    }
    
    public ProjIssuesVTO getIssue(String IssueId, int objectId)
    throws ServiceLocatorException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        DateUtility dateUtil;
        ProjIssuesVTO issueVTO = new ProjIssuesVTO();
        String attachmentId="";
        String sqlQuery = "select * from tblPrjIssues where Id=" + IssueId;
        dateUtil = DateUtility.getInstance();
        
        //Select Id,MapId,ProjectId,SubProjectId,CustomerId,ProjectName,SubProjectName,MapName,CreatedBy
        //IssueName,IssueType,DateCreated,DateClosed,Resolution,Status,Description from tblPrjIssues
        
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sqlQuery);
            resultSet = preparedStatement.executeQuery();
            issueVTO.setId(Integer.parseInt(IssueId));
            resultSet.next();
            attachmentId =  resultSet.getString("AttachmentId");
            if(attachmentId.equalsIgnoreCase(" ")|| attachmentId == null) {
                attachmentId="0";
            }
            
            issueVTO.setProjectId(resultSet.getInt("ProjectId"));
            issueVTO.setSubProjectId(resultSet.getInt("SubProjectId"));
            issueVTO.setProjectMapId(resultSet.getInt("MapId"));
            issueVTO.setAccountId(resultSet.getInt("CustomerId"));
            
            issueVTO.setCategoryId(resultSet.getString("ProjectName"));
            issueVTO.setSubCategoryId(resultSet.getString("SubProjectName"));
            issueVTO.setMapNameId(resultSet.getString("MapName"));
//            issueVTO.setMapId(resultSet.getString("MapName"));
//            issueVTO.setIssueName(resultSet.getString("IssueName"));
            
            issueVTO.setCreatedBy(resultSet.getString("CreatedBy"));            
            issueVTO.setSeverityId(resultSet.getString("Severity"));
            issueVTO.setStatusId(resultSet.getString("Status"));
            issueVTO.setTypeId(resultSet.getString("IssueType"));
            
            issueVTO.setCreated(dateUtil.sqlTimeStampTousTimeStamp(resultSet.getString("DateCreated")));
            issueVTO.setAssigned(dateUtil.sqlTimeStampTousTimeStamp(resultSet.getString("DateAssigned")));
            issueVTO.setClosed(dateUtil.sqlTimeStampTousTimeStamp(resultSet.getString("DateClosed")));
            issueVTO.setAssignedToUID(resultSet.getString("AssignedTo"));
            
            //issueVTO.setStatus(resultSet.getString("Status"));
            issueVTO.setComments(resultSet.getString("Comments"));
            issueVTO.setAttachmentId(resultSet.getInt("AttachmentId"));
            issueVTO.setDescription(resultSet.getString("Description"));
            issueVTO.setResolution(resultSet.getString("Resolution"));
            
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
        
        
        if(objectId != 0){
            
            try{                
                sqlQuery = "select * from tblPrjAttachments where Id=" + Integer.parseInt(attachmentId);
                //sqlQuery = "select * from tblEmpAttachments where ObjectId=" + objectId;
                
                
                connection = ConnectionProvider.getInstance().getConnection();
                preparedStatement = connection.prepareStatement(sqlQuery);
                Integer.parseInt(attachmentId);
                resultSet = preparedStatement.executeQuery();
                
                resultSet.next();
                issueVTO.setAttachmentId(Integer.parseInt(attachmentId));
                issueVTO.setObjectid(String.valueOf(resultSet.getInt("ObjectId")));
                issueVTO.setObjectType(resultSet.getString("ObjectType"));
                issueVTO.setFileLocation(resultSet.getString("Path"));
                String uploadFileName = resultSet.getString("AttachmentType");
                if( " ".equalsIgnoreCase(uploadFileName) || uploadFileName == null) {
                    uploadFileName="No File Attached";
                    issueVTO.setUploadFileName(uploadFileName);
                } else{
                    issueVTO.setUploadFileName(resultSet.getString("AttachmentType"));
                }
                issueVTO.setDate(resultSet.getTimestamp("DateOfUpload"));
                
                
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
        }
        return issueVTO;
    }
    
    public String getAttachmentLocation(int attachmentId) throws ServiceLocatorException {
        String attachmentLocation=null;
        Session session = HibernateServiceLocator.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String SQL_QUERY ="Select h.attachmentLocation from ProjectIssuesData as h where h.id=:attachmentId";
        
        Query query = session.createQuery(SQL_QUERY).setInteger("attachmentId",attachmentId);
        
        for(Iterator it=query.iterate();it.hasNext();){
            attachmentLocation = (String) it.next();
        }//end of the for loop
        
        session.close();
        
        return attachmentLocation;
    }
    
    public ProjIssuesVTO getProject(ProjIssuesAction projIssuesAction)
    throws ServiceLocatorException{
        
        ProjIssuesVTO projIssuesVTO = new ProjIssuesVTO();
        projIssuesVTO.setCategoryId(projIssuesAction.getProjectName());
        projIssuesVTO.setSubCategoryId(projIssuesAction.getSubProjectName());
        projIssuesVTO.setMapNameId(projIssuesAction.getMapName());
        projIssuesVTO.setProjectMapId(projIssuesAction.getMapId());
        projIssuesVTO.setAssigned(projIssuesAction.getDateWithOutTime());
        
        
        return projIssuesVTO;
    }
    
    
}
