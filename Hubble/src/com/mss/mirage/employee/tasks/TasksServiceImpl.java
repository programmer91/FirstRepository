package com.mss.mirage.employee.tasks;

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

public class TasksServiceImpl implements TasksService {

    public TasksServiceImpl() {
    }

    public int addOrUpdateTask1(TasksAction tasksAction, HttpServletRequest httpServletRequest, String issueTitle, String issueType, String priority, String primaryAssignTo, String secondaryAssignTo, String comments, String secondaryAssignToLoginId, String secondaryAssignToLoginIdForHubble, String secondaryAssignToLoginIdForProject, String secondaryAssignToLoginIdForNetwork, String secondaryAssignToLoginIdForInfra,String secondaryAssignToLoginIdForOthers)
            throws ServiceLocatorException, SQLException {
        Connection connection = null;
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        int updatedRows = 0;
        int isSuccess = 0;
        int taskMaxId = 0;
        String result;
         String statusPrev=tasksAction.getStatusPrev();
        int flag=0;
         if(!statusPrev.equalsIgnoreCase(tasksAction.getStatusId())){
            flag=1;
        }
        try {

            DateUtility dateUtil = DateUtility.getInstance();
            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("{call spEmpTasks1("
                    + "?,?,?,?,?,"
                    + "?,?,?,?,?,"
                    + "?,?,?,?,?,"
                    + "?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?) }");

            if (tasksAction.getOperationType().equals("Ins")) {
                callableStatement.setInt(1, 0);

            } else {
                callableStatement.setInt(1, tasksAction.getId());

            }
            //  System.out.println("ID-->"+tasksAction.getId());
            callableStatement.setString(2, tasksAction.getCreatedBy());
            callableStatement.setString(3, primaryAssignTo);
            if (tasksAction.getOperationType().equals("Ins")) {
                callableStatement.setString(4, "Created");

            } else {
                callableStatement.setString(4, tasksAction.getStatusId());

            }
            callableStatement.setString(5, comments);
            callableStatement.setString(6, priority);
            callableStatement.setInt(7, tasksAction.getObjectid());
            // System.out.println("tasksAction.getObjectType()-->"+tasksAction.getObjectType());
            callableStatement.setString(8, tasksAction.getObjectType());
            callableStatement.setString(9, tasksAction.getAttachmentName());
            callableStatement.setString(10, tasksAction.getFileLocation());
            callableStatement.setString(11, tasksAction.getUploadFileName());
            //  System.out.println("beforfe type 14");
            if (tasksAction.getOperationType().equals("Ins")) {
                callableStatement.setString(12, "");
            } else {
                //    callableStatement.setString(14, httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                callableStatement.setString(12, tasksAction.getCreatedBy());
            }
            //  System.out.println("beforfe 15");
            callableStatement.setString(13, tasksAction.getOperationType());
            if (tasksAction.getType().equalsIgnoreCase("hr") || tasksAction.getType().equalsIgnoreCase("0")) {
                callableStatement.setString(14, secondaryAssignToLoginId);
            } else if (tasksAction.getType().equalsIgnoreCase("hubble") || tasksAction.getType().equalsIgnoreCase("1")) {
                callableStatement.setString(14, secondaryAssignToLoginIdForHubble);
            } else if (tasksAction.getType().equalsIgnoreCase("project") || tasksAction.getType().equalsIgnoreCase("2")) {
                callableStatement.setString(14, secondaryAssignToLoginIdForProject);
            } else if (tasksAction.getType().equalsIgnoreCase("network") || tasksAction.getType().equalsIgnoreCase("3")) {
                callableStatement.setString(14, secondaryAssignToLoginIdForNetwork);
            } else if (tasksAction.getType().equalsIgnoreCase("infra") || tasksAction.getType().equalsIgnoreCase("4")) {
                callableStatement.setString(14, secondaryAssignToLoginIdForInfra);
            }else if (tasksAction.getType().equalsIgnoreCase("others") || tasksAction.getType().equalsIgnoreCase("5")) {
                callableStatement.setString(14, secondaryAssignToLoginIdForOthers);
            }
            callableStatement.setString(15, issueTitle);
            if (tasksAction.getType().equalsIgnoreCase("project") || tasksAction.getType().equalsIgnoreCase("2")) {
                callableStatement.setInt(16, DataSourceDataProvider.getInstance().getCustomerIdByProjectId(Integer.parseInt(tasksAction.getProjectId())));
                callableStatement.setInt(17, Integer.parseInt(tasksAction.getProjectId()));
            } else {
                callableStatement.setInt(16, 0);
                callableStatement.setInt(17, 0);
            }
            if (tasksAction.getOperationType().equals("Ins")) {
                callableStatement.setString(18, "");
            } else if (tasksAction.getType().equalsIgnoreCase("hr") || tasksAction.getType().equalsIgnoreCase("0")) {
                //    callableStatement.setString(14, httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                callableStatement.setString(18, tasksAction.getResolution());
            } else if (tasksAction.getType().equalsIgnoreCase("hubble") || tasksAction.getType().equalsIgnoreCase("1")) {
                //    callableStatement.setString(14, httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                callableStatement.setString(18, tasksAction.getResolutionHubble());
            } else if (tasksAction.getType().equalsIgnoreCase("project") || tasksAction.getType().equalsIgnoreCase("2")) {
                //    callableStatement.setString(14, httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                callableStatement.setString(18, tasksAction.getResolutionProject());
            } else if (tasksAction.getType().equalsIgnoreCase("network") || tasksAction.getType().equalsIgnoreCase("3")) {
                //    callableStatement.setString(14, httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                callableStatement.setString(18, tasksAction.getResolutionNetwork());
            } else if (tasksAction.getType().equalsIgnoreCase("infra") || tasksAction.getType().equalsIgnoreCase("4")) {
                //    callableStatement.setString(14, httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                callableStatement.setString(18, tasksAction.getResolutionInfra());
            } else if (tasksAction.getType().equalsIgnoreCase("others") || tasksAction.getType().equalsIgnoreCase("5")) {
                //    callableStatement.setString(14, httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                callableStatement.setString(18, tasksAction.getResolutionOthers());
            }

            if (tasksAction.getResourceType().equalsIgnoreCase("c")) {
                callableStatement.setString(19, "c");
            } else if (tasksAction.getResourceType().equalsIgnoreCase("e")) {
                callableStatement.setString(19, "e");
            } else if (tasksAction.getResourceType().equalsIgnoreCase("v")) {
                callableStatement.setString(19, "v");
            }

            callableStatement.setString(20, DataSourceDataProvider.getInstance().getLoginIdTypeByLoginId(primaryAssignTo));
            if (tasksAction.getType().equalsIgnoreCase("hr") || tasksAction.getType().equalsIgnoreCase("0")) {
                callableStatement.setString(21, DataSourceDataProvider.getInstance().getLoginIdTypeByLoginId(secondaryAssignToLoginId));
            } else if (tasksAction.getType().equalsIgnoreCase("hubble") || tasksAction.getType().equalsIgnoreCase("1")) {
                callableStatement.setString(21, DataSourceDataProvider.getInstance().getLoginIdTypeByLoginId(secondaryAssignToLoginIdForHubble));
            } else if (tasksAction.getType().equalsIgnoreCase("project") || tasksAction.getType().equalsIgnoreCase("2")) {
                callableStatement.setString(21, DataSourceDataProvider.getInstance().getLoginIdTypeByLoginId(secondaryAssignToLoginIdForProject));
            } else if (tasksAction.getType().equalsIgnoreCase("network") || tasksAction.getType().equalsIgnoreCase("3")) {
                callableStatement.setString(21, DataSourceDataProvider.getInstance().getLoginIdTypeByLoginId(secondaryAssignToLoginIdForNetwork));
            } else if (tasksAction.getType().equalsIgnoreCase("infra") || tasksAction.getType().equalsIgnoreCase("4")) {
                callableStatement.setString(21, DataSourceDataProvider.getInstance().getLoginIdTypeByLoginId(secondaryAssignToLoginIdForInfra));
            }else if (tasksAction.getType().equalsIgnoreCase("others") || tasksAction.getType().equalsIgnoreCase("5")) {
                callableStatement.setString(21, DataSourceDataProvider.getInstance().getLoginIdTypeByLoginId(secondaryAssignToLoginIdForOthers));
            }
            if (tasksAction.getType().equalsIgnoreCase("hr") || tasksAction.getType().equalsIgnoreCase("0")) {
                callableStatement.setInt(22, 0);
            } else if (tasksAction.getType().equalsIgnoreCase("hubble") || tasksAction.getType().equalsIgnoreCase("1")) {
                callableStatement.setInt(22, 1);
            } else if (tasksAction.getType().equalsIgnoreCase("project") || tasksAction.getType().equalsIgnoreCase("2")) {
                callableStatement.setInt(22, 2);
            } else if (tasksAction.getType().equalsIgnoreCase("network") || tasksAction.getType().equalsIgnoreCase("3")) {
                callableStatement.setInt(22, 3);
            } else if (tasksAction.getType().equalsIgnoreCase("infra") || tasksAction.getType().equalsIgnoreCase("4")) {
                callableStatement.setInt(22, 4);
            } else if (tasksAction.getType().equalsIgnoreCase("others") || tasksAction.getType().equalsIgnoreCase("5")) {
                callableStatement.setInt(22, 5);
            }
            callableStatement.setInt(23, Integer.parseInt(issueType));
            if (tasksAction.getOperationType().equals("Ins")) {
                callableStatement.setInt(24, 0);
            } else {
                //    callableStatement.setString(14, httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                callableStatement.setInt(24, Integer.parseInt(tasksAction.getPerCompleted()));
            }
            if (tasksAction.getOperationType().equals("Ins")) {
                callableStatement.setTimestamp(25, dateUtil.getCurrentMySqlDateTime());
                callableStatement.setTimestamp(26, dateUtil.getCurrentMySqlDateTime());
            } else {
                callableStatement.setTimestamp(25, dateUtil.strToTimeStampObj(tasksAction.getDateAssigned()));
                callableStatement.setTimestamp(26, dateUtil.strToTimeStampObj(tasksAction.getDateClosed()));
            }
            callableStatement.setString(27, tasksAction.getDurationTotheTask());
          callableStatement.setString(28, tasksAction.getBridgeCode());
          callableStatement.setInt(29, flag);
            callableStatement.registerOutParameter(30, Types.INTEGER);

            updatedRows = callableStatement.executeUpdate();

            taskMaxId = callableStatement.getInt(30);

        } catch (SQLException se) {
            se.printStackTrace();
            throw new ServiceLocatorException(se.getMessage());
        } finally {
            try {
                if (callableStatement != null) {
                    callableStatement.close();
                    callableStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        //System.out.println("taskMaxId-->"+taskMaxId);
        return taskMaxId;
    }
    
      public int addOrUpdateTask(TasksAction tasksAction, HttpServletRequest httpServletRequest, String issueTitle, String issueType, String priority, String primaryAssignTo, String secondaryAssignTo, String comments, String secondaryAssignToLoginId)
            throws ServiceLocatorException, SQLException {
        Connection connection = null;
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        int updatedRows = 0;
        int isSuccess = 0;
        int taskMaxId = 0;
        String result;
        try {

            DateUtility dateUtil = DateUtility.getInstance();
            connection = ConnectionProvider.getInstance().getConnection();
           callableStatement = connection.prepareCall("{call spEmpTasks1("
                    + "?,?,?,?,?,"
                    + "?,?,?,?,?,"
                    + "?,?,?,?,?,"
                    + "?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?) }");
            if (tasksAction.getOperationType().equals("Ins")) {
                callableStatement.setInt(1, 0);

            } else {
                callableStatement.setInt(1, tasksAction.getId());

            }
            //  System.out.println("ID-->"+tasksAction.getId());
            callableStatement.setString(2, tasksAction.getCreatedBy());
            callableStatement.setString(3, primaryAssignTo);
            if (tasksAction.getOperationType().equals("Ins")) {
                callableStatement.setString(4, "Created");

            } else {
                callableStatement.setString(4, tasksAction.getStatusId());

            }
            callableStatement.setString(5, comments);
            callableStatement.setString(6, priority);
            callableStatement.setInt(7, tasksAction.getObjectid());
          //  System.out.println("tasksAction.getObjectType()-->"+tasksAction.getObjectType());
            callableStatement.setString(8, tasksAction.getObjectType());
           //  System.out.println("tasksAction.getObjectType()-->"+tasksAction.getAttachmentName());
            callableStatement.setString(9, tasksAction.getAttachmentName());
            callableStatement.setString(10, tasksAction.getFileLocation());
            callableStatement.setString(11, tasksAction.getUploadFileName());
            //  System.out.println("beforfe type 14");
            if (tasksAction.getOperationType().equals("Ins")) {
                callableStatement.setString(12, "");
            } else {
                //    callableStatement.setString(14, httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                callableStatement.setString(12, tasksAction.getCreatedBy());
            }
            callableStatement.setString(13, tasksAction.getOperationType());
                callableStatement.setString(14, secondaryAssignToLoginId);
            callableStatement.setString(15, issueTitle);
            // System.out.println("before if-->"+tasksAction.getType()); 
            if (tasksAction.getType().equalsIgnoreCase("project") || tasksAction.getType().equalsIgnoreCase("2")) {
              //  System.out.println("in if "+DataSourceDataProvider.getInstance().getCustomerIdByProjectId(Integer.parseInt(tasksAction.getProjectId())));
                callableStatement.setInt(16, DataSourceDataProvider.getInstance().getCustomerIdByProjectId(Integer.parseInt(tasksAction.getProjectId())));
                callableStatement.setInt(17, Integer.parseInt(tasksAction.getProjectId()));
            } else {
                callableStatement.setInt(16, 0);
                callableStatement.setInt(17, 0);
            }
            if (tasksAction.getOperationType().equals("Ins")) {
                callableStatement.setString(18, "");
            } else if (tasksAction.getType().equalsIgnoreCase("hr") || tasksAction.getType().equalsIgnoreCase("0")) {
                //    callableStatement.setString(14, httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                callableStatement.setString(18, tasksAction.getResolution());
            } else if (tasksAction.getType().equalsIgnoreCase("hubble") || tasksAction.getType().equalsIgnoreCase("1")) {
                //    callableStatement.setString(14, httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                callableStatement.setString(18, tasksAction.getResolutionHubble());
            } else if (tasksAction.getType().equalsIgnoreCase("project") || tasksAction.getType().equalsIgnoreCase("2")) {
                //    callableStatement.setString(14, httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                callableStatement.setString(18, tasksAction.getResolutionProject());
            } else if (tasksAction.getType().equalsIgnoreCase("network") || tasksAction.getType().equalsIgnoreCase("3")) {
                //    callableStatement.setString(14, httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                callableStatement.setString(18, tasksAction.getResolutionNetwork());
            } else if (tasksAction.getType().equalsIgnoreCase("infra") || tasksAction.getType().equalsIgnoreCase("4")) {
                //    callableStatement.setString(14, httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                callableStatement.setString(18, tasksAction.getResolutionInfra());
            }
        

            if (tasksAction.getResourceType().equalsIgnoreCase("c")) {
                callableStatement.setString(19, "c");
            } else if (tasksAction.getResourceType().equalsIgnoreCase("e")) {
                callableStatement.setString(19, "e");
            } else if (tasksAction.getResourceType().equalsIgnoreCase("v")) {
                callableStatement.setString(19, "v");
            }

            callableStatement.setString(20, DataSourceDataProvider.getInstance().getLoginIdTypeByLoginId(primaryAssignTo));
            //if (tasksAction.getType().equalsIgnoreCase("hr") || tasksAction.getType().equalsIgnoreCase("0")) {
                callableStatement.setString(21, DataSourceDataProvider.getInstance().getLoginIdTypeByLoginId(secondaryAssignToLoginId));
            /*} else if (tasksAction.getType().equalsIgnoreCase("hubble") || tasksAction.getType().equalsIgnoreCase("1")) {
                callableStatement.setString(21, DataSourceDataProvider.getInstance().getLoginIdTypeByLoginId(secondaryAssignToLoginIdForHubble));
            } else if (tasksAction.getType().equalsIgnoreCase("project") || tasksAction.getType().equalsIgnoreCase("2")) {
                callableStatement.setString(21, DataSourceDataProvider.getInstance().getLoginIdTypeByLoginId(secondaryAssignToLoginIdForProject));
            } else if (tasksAction.getType().equalsIgnoreCase("network") || tasksAction.getType().equalsIgnoreCase("3")) {
                callableStatement.setString(21, DataSourceDataProvider.getInstance().getLoginIdTypeByLoginId(secondaryAssignToLoginIdForNetwork));
            } else if (tasksAction.getType().equalsIgnoreCase("infra") || tasksAction.getType().equalsIgnoreCase("4")) {
                callableStatement.setString(21, DataSourceDataProvider.getInstance().getLoginIdTypeByLoginId(secondaryAssignToLoginIdForInfra));
            }
            else if (tasksAction.getType().equalsIgnoreCase("other") || tasksAction.getType().equalsIgnoreCase("5")) {
                callableStatement.setString(21, DataSourceDataProvider.getInstance().getLoginIdTypeByLoginId(secondaryAssignToLoginIdForOther));
            }*/
          //  if (tasksAction.getType().equalsIgnoreCase("hr") || tasksAction.getType().equalsIgnoreCase("0")) {
                callableStatement.setInt(22, Integer.parseInt(tasksAction.getType()));
          /*  } else if (tasksAction.getType().equalsIgnoreCase("hubble") || tasksAction.getType().equalsIgnoreCase("1")) {
                callableStatement.setInt(22, 1);
            } else if (tasksAction.getType().equalsIgnoreCase("project") || tasksAction.getType().equalsIgnoreCase("2")) {
                callableStatement.setInt(22, 2);
            } else if (tasksAction.getType().equalsIgnoreCase("network") || tasksAction.getType().equalsIgnoreCase("3")) {
                callableStatement.setInt(22, 3);
            } else if (tasksAction.getType().equalsIgnoreCase("infra") || tasksAction.getType().equalsIgnoreCase("4")) {
                callableStatement.setInt(22, 4);
            }
            else if (tasksAction.getType().equalsIgnoreCase("other") || tasksAction.getType().equalsIgnoreCase("5")) {
                callableStatement.setInt(22, 5);
            }*/
            callableStatement.setInt(23, Integer.parseInt(issueType));
            if (tasksAction.getOperationType().equals("Ins")) {
                callableStatement.setInt(24, 0);
            } else {
                //    callableStatement.setString(14, httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                callableStatement.setInt(24, Integer.parseInt(tasksAction.getPerCompleted()));
            }
            if (tasksAction.getOperationType().equals("Ins")) {
                callableStatement.setTimestamp(25, dateUtil.getCurrentMySqlDateTime());
                callableStatement.setTimestamp(26, dateUtil.getCurrentMySqlDateTime());
            } else {
                callableStatement.setTimestamp(25, dateUtil.strToTimeStampObj(tasksAction.getDateAssigned()));
                callableStatement.setTimestamp(26, dateUtil.strToTimeStampObj(tasksAction.getDateClosed()));
            }
             callableStatement.setString(27, tasksAction.getDurationTotheTask());
           callableStatement.setString(28, tasksAction.getBridgeCode());
           callableStatement.setInt(29, 0);
            callableStatement.registerOutParameter(30, Types.INTEGER);

            updatedRows = callableStatement.executeUpdate();

            taskMaxId = callableStatement.getInt(30);
        } catch (SQLException se) {
            se.printStackTrace();
            throw new ServiceLocatorException(se.getMessage());
        } finally {
            try {
                if (callableStatement != null) {
                    callableStatement.close();
                    callableStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        //System.out.println("taskMaxId-->"+taskMaxId);
        return taskMaxId;
    }


    public TasksVTO getTask(String TaskId, int objectId, String currentResourceType, String type)
            throws ServiceLocatorException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        DateUtility dateUtil;
        TasksVTO tasksVTO = new TasksVTO();
        String attachmentId = "";
        String sqlQuery = "select * from tblEmpTasks where Id=" + TaskId;
        //System.out.println("sqlQuery-->"+sqlQuery);
        dateUtil = DateUtility.getInstance();
        String uploadFileName = null;
        String category = null;
        int taskId = 0;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sqlQuery);
            resultSet = preparedStatement.executeQuery();
            tasksVTO.setId(Integer.parseInt(TaskId));
            resultSet.next();
            attachmentId = resultSet.getString("AttachmentId");
            if (attachmentId == null) {
                attachmentId = "0";
            }
            tasksVTO.setSeverityId(resultSet.getString("Severity"));
            tasksVTO.setStatusId(resultSet.getString("Status"));
            
          /*  tasksVTO.setAssigned(dateUtil.sqlTimeStampTousTimeStamp(resultSet.getString("CreatedDate")));
            tasksVTO.setClosed(dateUtil.sqlTimeStampTousTimeStamp(resultSet.getString("DueDate"))); */
            
        /*     if(resultSet.getString("CreatedDate")!=null && !"".equals(resultSet.getString("CreatedDate"))){
             tasksVTO.setAssigned(dateUtil.sqlTimeStampTousTimeStamp(resultSet.getString("CreatedDate")));
            } */
             String dbStratDate =   dateUtil.sqlTimeStampTousTimeStamp(resultSet.getString("Startdate"));
            if(dbStratDate.equals("01/31/1950 00:00:00") || dbStratDate==null || "".equals(dbStratDate)){
                  tasksVTO.setAssigned(dateUtil.sqlTimeStampTousTimeStamp(resultSet.getString("CreatedDate")));
            }
          else
            {
                tasksVTO.setAssigned(dateUtil.sqlTimeStampTousTimeStamp(resultSet.getString("Startdate")));
            }
             
             
             if(resultSet.getString("DueDate")!=null && !"".equals(resultSet.getString("DueDate"))){
           
            tasksVTO.setClosed(dateUtil.sqlTimeStampTousTimeStamp(resultSet.getString("DueDate")));
             }
             
            tasksVTO.setAttachmentId(resultSet.getInt("AttachmentId"));
            tasksVTO.setDurationTotheTask(resultSet.getString("Duration"));
            //tasksVTO.setResolution(resultSet.getString("Resolution"));
            tasksVTO.setPerCompleted(resultSet.getString("percentageCompleted"));
            // tasksVTO.setDays(String.valueOf(resultSet.getInt("Remainder")));
            tasksVTO.setCustomerId(resultSet.getString("CustomerId"));
            //System.out.println("before if2 -->");
            if (currentResourceType.equalsIgnoreCase("e")) {
                if (!resultSet.getString("CustomerId").equals("0")) {
                    tasksVTO.setCustomerName(DataSourceDataProvider.getInstance().getAccountName(Integer.parseInt(tasksVTO.getCustomerId())));
                }
            }

            //System.out.println("before if1 -->");


            if (currentResourceType.equalsIgnoreCase("e")) {
                tasksVTO.setProjectName(DataSourceDataProvider.getInstance().getProjectName(resultSet.getInt("Project_Id")));
            }
            //System.out.println("in infratype-->"+type);
            if (type.equalsIgnoreCase("0")) {
                tasksVTO.setResolution(resultSet.getString("Resolution"));
                tasksVTO.setPriority(resultSet.getString("Severity"));
                //System.out.println("in hr");
                tasksVTO.setIssueType(resultSet.getString("IssueType"));
                tasksVTO.setPrimaryAssignTo(resultSet.getString("PriAssignTO"));
                // System.out.println("in hr-->"+tasksVTO.getPrimaryAssignTo());

                tasksVTO.setSecondaryAssignToLoginId(resultSet.getString("SecAssignTo"));
               if (resultSet.getString("SecAssignTo") == null || resultSet.getString("SecAssignTo").equals("")) {		
                    tasksVTO.setSecondaryAssignTo("");
                } else {
                    tasksVTO.setSecondaryAssignTo(DataSourceDataProvider.getInstance().getFname_Lname(resultSet.getString("SecAssignTo")));
                }
                // System.out.println("in hr-->"+tasksVTO.getSecondaryAssignTo());
                tasksVTO.setComments(resultSet.getString("Description"));
                tasksVTO.setTitle(resultSet.getString("Title"));
            } else if (type.equalsIgnoreCase("2")) {
                tasksVTO.setResolutionProject(resultSet.getString("Resolution"));
                tasksVTO.setPriorityProject(resultSet.getString("Severity"));
                tasksVTO.setIssueTypeProject(resultSet.getString("IssueType"));
                tasksVTO.setPrimaryAssignToProject(resultSet.getString("PriAssignTO"));
                tasksVTO.setSecondaryAssignToLoginIdForProject(resultSet.getString("SecAssignTo"));
               if (resultSet.getString("SecAssignTo") == null || resultSet.getString("SecAssignTo").equals("")) {		
                    tasksVTO.setSecondaryAssignToProject("");
                } else {
                    tasksVTO.setSecondaryAssignToProject(DataSourceDataProvider.getInstance().getFname_Lname(resultSet.getString("SecAssignTo")));
                }

                //tasksVTO.setSecondaryAssignToProject(resultSet.getString("SecAssignTo"));
                if (resultSet.getInt("Project_Id") > 0) {
                    tasksVTO.setProjectId(String.valueOf(resultSet.getInt("Project_Id")));
                }
                tasksVTO.setCommentsProject(resultSet.getString("Description"));
                tasksVTO.setTitleProject(resultSet.getString("Title"));
            } else if (type.equalsIgnoreCase("3")) {
                tasksVTO.setResolutionNetwork(resultSet.getString("Resolution"));
                tasksVTO.setPriorityNetwork(resultSet.getString("Severity"));
                tasksVTO.setIssueTypeNetwork(resultSet.getString("IssueType"));
                tasksVTO.setPrimaryAssignToNetwork(resultSet.getString("PriAssignTO"));
                tasksVTO.setSecondaryAssignToLoginIdForNetwork(resultSet.getString("SecAssignTo"));
               if (resultSet.getString("SecAssignTo") == null || resultSet.getString("SecAssignTo").equals("")) {		
                    tasksVTO.setSecondaryAssignToNetwork("");
                } else {
                    tasksVTO.setSecondaryAssignToNetwork(DataSourceDataProvider.getInstance().getFname_Lname(resultSet.getString("SecAssignTo")));
                }
                // tasksVTO.setSecondaryAssignToNetwork(resultSet.getString("SecAssignTo"));
                tasksVTO.setCommentsNetwork(resultSet.getString("Description"));
                tasksVTO.setTitleNetwork(resultSet.getString("Title"));
            } else if (type.equalsIgnoreCase("1")) {
                tasksVTO.setResolutionHubble(resultSet.getString("Resolution"));
                tasksVTO.setPriorityHubble(resultSet.getString("Severity"));
                tasksVTO.setIssueTypeHubble(resultSet.getString("IssueType"));
                tasksVTO.setPrimaryAssignToHubble(resultSet.getString("PriAssignTO"));
                tasksVTO.setSecondaryAssignToLoginIdForHubble(resultSet.getString("SecAssignTo"));
               if (resultSet.getString("SecAssignTo") == null || resultSet.getString("SecAssignTo").equals("")) {		
                    tasksVTO.setSecondaryAssignToHubble("");
                } else {
                    tasksVTO.setSecondaryAssignToHubble(DataSourceDataProvider.getInstance().getFname_Lname(resultSet.getString("SecAssignTo")));
                }
                //  tasksVTO.setSecondaryAssignToHubble(resultSet.getString("SecAssignTo"));
                tasksVTO.setCommentsHubble(resultSet.getString("Description"));
                tasksVTO.setTitleHubble(resultSet.getString("Title"));
            } else if (type.equalsIgnoreCase("4")) {
                tasksVTO.setResolutionInfra(resultSet.getString("Resolution"));
                tasksVTO.setPriorityInfra(resultSet.getString("Severity"));
                // System.out.println("in infra");
                tasksVTO.setIssueTypeInfra(resultSet.getString("IssueType"));
                //  System.out.println("in infra-->"+resultSet.getString("PriAssignTO"));
                tasksVTO.setPrimaryAssignToInfra(resultSet.getString("PriAssignTO"));
                tasksVTO.setSecondaryAssignToLoginIdForInfra(resultSet.getString("SecAssignTo"));
              if (resultSet.getString("SecAssignTo") == null || resultSet.getString("SecAssignTo").equals("")) {		
                    tasksVTO.setSecondaryAssignToInfra("");
                } else {
                    tasksVTO.setSecondaryAssignToInfra(DataSourceDataProvider.getInstance().getFname_Lname(resultSet.getString("SecAssignTo")));
                }
                // System.out.println("in infra-->"+tasksVTO.getSecondaryAssignToInfra());
                // tasksVTO.setSecondaryAssignToInfra(resultSet.getString("SecAssignTo"));
                tasksVTO.setCommentsInfra(resultSet.getString("Description"));
                tasksVTO.setTitleInfra(resultSet.getString("Title"));

            }else if (type.equalsIgnoreCase("5")) {
                tasksVTO.setResolutionOthers(resultSet.getString("Resolution"));
                tasksVTO.setPriorityOthers(resultSet.getString("Severity"));
                // System.out.println("in infra");
                tasksVTO.setIssueTypeOthers(resultSet.getString("IssueType"));
                //  System.out.println("in infra-->"+resultSet.getString("PriAssignTO"));
                tasksVTO.setPrimaryAssignToLoginIdforOthers(resultSet.getString("PriAssignTO"));
                tasksVTO.setPrimaryAssignToforOthers(DataSourceDataProvider.getInstance().getFname_Lname(resultSet.getString("PriAssignTO")));
                tasksVTO.setSecondaryAssignToLoginIdForOthers(resultSet.getString("SecAssignTo"));
              if (resultSet.getString("SecAssignTo") == null || resultSet.getString("SecAssignTo").equals("")) {		
                    tasksVTO.setSecondaryAssignToOthers("");
                } else {
                    tasksVTO.setSecondaryAssignToOthers(DataSourceDataProvider.getInstance().getFname_Lname(resultSet.getString("SecAssignTo")));
                }
                // System.out.println("in infra-->"+tasksVTO.getSecondaryAssignToInfra());
                // tasksVTO.setSecondaryAssignToInfra(resultSet.getString("SecAssignTo"));
                tasksVTO.setCommentsOthers(resultSet.getString("Description"));
                tasksVTO.setTitleOthers(resultSet.getString("Title"));

            }
System.out.println("");
            tasksVTO.setType(resultSet.getString("IssueRel"));
tasksVTO.setBridgeCode(resultSet.getString("BridgeCode"));
            tasksVTO.setiFlag(1);

            // System.out.println("after c1--->"+tasksVTO.getType());

        } catch (SQLException se) {
            se.printStackTrace();
            throw new ServiceLocatorException(se);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }


        if (objectId != 0 && !attachmentId.equalsIgnoreCase("0")) {

            try {

                sqlQuery = "select * from tblEmpAttachments where Id=" + Integer.parseInt(attachmentId);
                //sqlQuery = "select * from tblEmpAttachments where ObjectId=" + objectId;


                connection = ConnectionProvider.getInstance().getConnection();
                preparedStatement = connection.prepareStatement(sqlQuery);
                Integer.parseInt(attachmentId);
                resultSet = preparedStatement.executeQuery();

                resultSet.next();
                tasksVTO.setAttachmentId(Integer.parseInt(attachmentId));
                tasksVTO.setObjectid(String.valueOf(resultSet.getInt("ObjectId")));
                tasksVTO.setObjectType(resultSet.getString("ObjectType"));
                tasksVTO.setFileLocation(resultSet.getString("AttachmentLocation"));
                uploadFileName = resultSet.getString("AttachmentFileName");
                //  tasksVTO.setResolution(resultSet.getString("Resolution"));
//                if( " ".equalsIgnoreCase(uploadFileName) || uploadFileName == null) {
//                    uploadFileName="No File Attached";
//                    issueVTO.setUploadFileName(uploadFileName);
//                } else{
//                    issueVTO.setUploadFileName(resultSet.getString("AttachmentFileName"));
//                }
                tasksVTO.setUploadFileName(resultSet.getString("AttachmentFileName"));
                tasksVTO.setDate(resultSet.getTimestamp("DateUploaded"));


            } catch (SQLException se) {
                throw new ServiceLocatorException(se);
            } finally {
                try {
                    if (resultSet != null) {
                        resultSet.close();
                        resultSet = null;
                    }
                    if (preparedStatement != null) {
                        preparedStatement.close();
                        preparedStatement = null;
                    }
                    if (connection != null) {
                        connection.close();
                        connection = null;
                    }
                } catch (SQLException sqle) {
                    throw new ServiceLocatorException(sqle);
                }
            }
        } else {
            uploadFileName = "No File Attached";
            tasksVTO.setUploadFileName(uploadFileName);
        }
        return tasksVTO;
    }

    public String getAttachmentLocation(int attachmentId) throws ServiceLocatorException {
        String attachmentLocation = null;
        Session session = HibernateServiceLocator.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String SQL_QUERY = "Select h.attachmentLocation from AttachmentAction as h where h.id=:attachmentId";

        Query query = session.createQuery(SQL_QUERY).setInteger("attachmentId", attachmentId);

        for (Iterator it = query.iterate(); it.hasNext();) {
            attachmentLocation = (String) it.next();
        }//end of the for loop

        session.close();

        return attachmentLocation;
    }

    //new
    public String addAttachmentLocation(TasksAction tasksAction) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int isInsert = 0;
        String resultMessage = "";
        try {

            String queryString = "INSERT INTO tblTaskAttachments ( ObjectId, ObjectType, AttachmentName, AttachmentLocation, AttachmentFileName, DateUploaded, UploadedBy) VALUES (?,?,?,?,?,?,?)";
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1, tasksAction.getObjectid());
            preparedStatement.setString(2, tasksAction.getObjectType());
            preparedStatement.setString(3, tasksAction.getTaskFileName());
            preparedStatement.setString(4, tasksAction.getFileLocation());
            preparedStatement.setString(5, tasksAction.getUploadFileName());
            preparedStatement.setTimestamp(6, DateUtility.getInstance().getCurrentMySqlDateTime());
            preparedStatement.setString(7, tasksAction.getCreatedBy());
            isInsert = preparedStatement.executeUpdate();

            //System.out.println("After insert-->"+isInsert);
        } catch (SQLException se) {
            throw new ServiceLocatorException(se);
        } finally {
            try {

                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }

        return resultMessage;
    }

    public String getTaskAttachmentLocation(int fileId) throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String fileLocation = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT AttachmentLocation FROM tblTaskAttachments WHERE Id=" + fileId);
            if (resultSet.next()) {
                fileLocation = resultSet.getString("AttachmentLocation");
            }

        } catch (SQLException se) {
            throw new ServiceLocatorException(se);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return fileLocation;
    }
    
 public String addNotes(TasksAction tasksAction) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int isInsert = 0;
        String resultMessage = "";
        try {

            String queryString = "INSERT INTO  tblTaskNotes ( Task_Id, Notes, CreatedDate, CreatedBy, ModifiedDate, ModifiedBy) VALUES (?,?,?,?,?,?)";
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1, tasksAction.getObjectid());
            preparedStatement.setString(2, tasksAction.getNotes());
           // preparedStatement.setString(3, tasksAction.getTaskFileName());
              preparedStatement.setTimestamp(3, DateUtility.getInstance().getCurrentMySqlDateTime());
            //callableStatement.setString(2, tasksAction.getCreatedBy());
            preparedStatement.setString(4, tasksAction.getCreatedBy());
            preparedStatement.setString(6, tasksAction.getModifiedBy());
            preparedStatement.setTimestamp(5, DateUtility.getInstance().getCurrentMySqlDateTime());
        
            isInsert = preparedStatement.executeUpdate();

            System.out.println("After insert-->"+isInsert);
        } catch (SQLException se) {
            throw new ServiceLocatorException(se);
        } finally {
            try {

                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }

        return resultMessage;
    }
   public String getTaskNotes(int id) throws ServiceLocatorException
           {
         Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String notes = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT Notes FROM tblTaskNotes WHERE Id=" +id);
            if (resultSet.next()) {
                notes = resultSet.getString("Notes");
            }

        } catch (SQLException se) {
            throw new ServiceLocatorException(se);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        return notes;
    }
   
               public String updateNotes(TasksAction tasksAction) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int isUpdate = 0;
        String resultMessage = "";
        try {

            String queryString = "Update  tblTaskNotes set Notes=? where Id=?";
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(2, tasksAction.getNotesId());
            preparedStatement.setString(1, tasksAction.getNotes());
    
            isUpdate = preparedStatement.executeUpdate();

            System.out.println("After update-->"+isUpdate);
        } catch (SQLException se) {
            throw new ServiceLocatorException(se);
        } finally {
            try {

                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }

        return resultMessage;
    }
           }
