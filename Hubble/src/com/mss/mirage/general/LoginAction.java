/*
 * @(#)LoginAction.java 1.0 Nov 01, 2007
 *
 * Copyright 2006 Miracle Software Systems(INDIA) Pvt Ltd. All rights reserved.
 *
 */
package com.mss.mirage.general;

import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.AuthorizationManager;
import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.PasswordUtility;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import java.util.Iterator;
import org.apache.struts2.interceptor.ServletResponseAware;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.Properties;
import java.sql.PreparedStatement;
import com.mss.mirage.util.ServiceLocatorException;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.apache.log4j.FileAppender;
import org.apache.log4j.PropertyConfigurator;
import com.mss.mirage.util.SecurityProperties;
import java.util.List;
import org.apache.struts2.ServletActionContext;

/**
 * The <code>LoginAction</code>  class is used for to enter in to MirageV2 from
 * <i>Login.jsp</i> page.
 *
 * @author RajaReddy.Andra <a href="mailto:randra@miraclesoft.com">randra@miraclesoft.com</a>
 *
 * @version 1.0 Nov 01, 2007
 *
 * @see  com.mss.mirage.util.ApplicationConstants
 * @see  com.mss.mirage.util.HibernateServiceLocator;
 * @see  com.mss.mirage.util.PasswordUtility;
 *
 */
public class LoginAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

    /*@param resultType used to store type of the result*/
    private String resultType;
    private PasswordUtility passwordUtility;
    /*@param userId used to store loginId of the employee*/
    private String userId;
    /*@param password used to store password of the employee*/
    private String password;
    /*@param id used to store id (this is primary key) */
    private String id;
    /*@param isManager used to store User is manager(if 1) or not(if 0) */
    private int isManager;
    /*@param curStatus is used to store  status of the employee*/
    private String curStatus;
    /*@param firstName is used to store firstname of the employee*/
    private String firstName;
    /*@param lastName is used to store lastname of the employee*/
    private String lastName;
    /*@param middleName is used to store middlename of the employee*/
    private String middleName;
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    private HttpSession httpSession;
    private int leaveId = 0;
    private Timestamp createdtime;
    private int timeSheetID;
    private int empId;
    private int issueId;
    private String resM;
    private int taskId;
    private String consultantId;
    private String resourceType;
    //private String statusValue;
    //private String secStatusValue;
    private String objectId;
    private String requirementAdminFlag;
    private String status;
    private String requirementId;
    private String recConsultantId;
    private String consultId;
  //  private String isAdmin="NO";
    
    /** Creates a new instance of LoginAction */
    public LoginAction() {
    }

    /**
     *execute() method ,in this method checking user details means he is authenticated persion or not,
     *user enter loginId and password in login page ,those details compare with database details,
     *loginId and password equal with database details then he can login success fully other wise
     *he will get some message.
     */
    public String execute() throws Exception {
        // System.out.println("1000");
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        passwordUtility = new PasswordUtility();
        String dbCurStatus = "";
        String dbLoginId = "";
        String dbDepartmentId = "";
        int dbIsManager = 0;
        int dbIsTeamLead = 0;
        String dbUserName = "";
        String dbPassword = "";
        int dbEmpId = 0;
        String dbReportsToId = "";
        String dbEmpWorkPhno = "";
        String dbEmpTitle = "";
        String password = null;
        int tempLeave = 0;
        String dbWorkingCountry = "";
        String dbLivingCountry = "";
        int dbIsCRETeam = 0;
        String dbTeamName = null;
        int dbIsPMO = 0;
        int dbReportsToFlag = 0;
        Map myRoles = null;
        String dbPractice = "";
         String dbLocation = "";
         String dbEmployeeType = "";
         String dbEmpNo = "";
         String dbEmail = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
         //   statement = connection.createStatement();
            HttpSession session = getHttpServletRequest().getSession(true);
            // System.out.println("getLoginAs-->"+getLoginAs());
            //if(getUserId()!=null && "E".equalsIgnoreCase(getLoginAs()))
            if (getUserId() != null) {
                //System.out.println("1001");
              // String SQL_QUERY = "SELECT Id,LoginId, TeamId,Password, IsManager,IsTeamLead,CurStatus,FName,LName,MName,ReportsTo,DepartmentId,WorkPhoneNo,TitleTypeId,WorkingCountry,Country,IsCreTeam,Email1,IsPMO,ReportsToFlag,PracticeId,Location,EmployeeTypeId,EmpNo from tblEmployee where loginId= '" + getUserId().toLowerCase().trim() + "'";
                 String SQL_QUERY = "SELECT Id,LoginId, TeamId,Password, IsManager,IsTeamLead,CurStatus,FName,LName,MName,ReportsTo,DepartmentId,WorkPhoneNo,TitleTypeId,WorkingCountry,Country,IsCreTeam,Email1,IsPMO,ReportsToFlag,PracticeId,Location,EmployeeTypeId,EmpNo from tblEmployee where loginId= ?";
                 preparedStatement = connection.prepareStatement(SQL_QUERY);
                 preparedStatement.setString(1, getUserId().toLowerCase().trim());
                 resultSet = preparedStatement.executeQuery();
               // resultSet = statement.executeQuery(SQL_QUERY.toString());
                while (resultSet.next()) {
                    //System.out.println("1002");
                    dbPassword = resultSet.getString("Password");
                    dbLoginId = resultSet.getString("LoginId");
                    dbCurStatus = resultSet.getString("CurStatus");
                    dbIsManager = resultSet.getInt("IsManager");
                    dbIsTeamLead = resultSet.getInt("IsTeamLead");
                    dbUserName = resultSet.getString("FName") + " " + resultSet.getString("MName") + "." + resultSet.getString("LName");
                    dbEmpId = resultSet.getInt("Id");
                    dbReportsToId = resultSet.getString("ReportsTo");
                    dbDepartmentId = resultSet.getString("DepartmentId");
                    dbEmpWorkPhno = resultSet.getString("WorkPhoneNo");
                    dbEmpTitle = resultSet.getString("TitleTypeId");
                    dbWorkingCountry = resultSet.getString("WorkingCountry");
                    dbLivingCountry = resultSet.getString("Country");
                    dbDepartmentId = resultSet.getString("DepartmentId");
                    dbIsCRETeam = resultSet.getInt("IsCreTeam");
                    dbTeamName = resultSet.getString("TeamId");
                    dbIsPMO = resultSet.getInt("IsPMO");
                    if (dbWorkingCountry.equals("All")) {
                        dbWorkingCountry = "%";
                    }
                    dbReportsToFlag = resultSet.getInt("ReportsToFlag");
                    dbPractice =  resultSet.getString("PracticeId");
                     dbLocation = resultSet.getString("Location");
                     dbEmployeeType = resultSet.getString("EmployeeTypeId");
                     dbEmpNo = resultSet.getString("EmpNo");
                     if(dbEmpNo==null||"".equals(dbEmpNo))
                         dbEmpNo = "0";
                     dbEmail = resultSet.getString("Email1");

                }

                //System.out.println("1003");
                String decPassword = passwordUtility.decryptPwd(dbPassword);
                //System.out.println("this is psswd "+decPassword);
                if ("Registered".equalsIgnoreCase(dbCurStatus)) {
                    getHttpServletRequest().setAttribute("errorMessage", "<font color=\"red\" size=\"1.5\">Sorry! Your account will be activated soon!</font>");
                    resultType = INPUT;
                } else {
                    if (decPassword.equals(getPassword().trim())) {
                        if ("Active".equalsIgnoreCase(dbCurStatus)) {
                            // System.out.println("1004");

                            session.setAttribute(ApplicationConstants.SESSION_USER_ID, dbLoginId);
                            session.setAttribute(ApplicationConstants.SESSION_USER_NAME, dbUserName);
                            session.setAttribute(ApplicationConstants.SESSION_IS_USER_MANAGER, dbIsManager);
                            session.setAttribute(ApplicationConstants.SESSION_IS_TEAM_LEAD, dbIsTeamLead);
                            session.setAttribute(ApplicationConstants.SESSION_EMP_ID, String.valueOf(dbEmpId));
                            session.setAttribute(ApplicationConstants.SESSION_REPORTSTO_ID, dbReportsToId);
                            // adding empWorkPhone number to session
                            session.setAttribute(ApplicationConstants.SESSION_EMP_WORKPHNO, dbEmpWorkPhno);
                            session.setAttribute(ApplicationConstants.SESSION_EMP_TITLE, dbEmpTitle);
                            session.setAttribute(ApplicationConstants.WORKING_COUNTRY, dbWorkingCountry);
                            session.setAttribute(ApplicationConstants.Living_COUNTRY, dbLivingCountry);
                            session.setAttribute(ApplicationConstants.SESSION_MY_DEPT_ID, dbDepartmentId);
                            session.setAttribute(ApplicationConstants.SESSION_IS_CRE_TEAM, dbIsCRETeam);
                            session.setAttribute(ApplicationConstants.SESSION_TEAM_NAME, dbTeamName);
                            //emptype
                            session.setAttribute(ApplicationConstants.SESSION_EMPTYPE, "e");
                            session.setAttribute(ApplicationConstants.SESSION_CUSTOMER_PROJECTIDS, null);
                            session.setAttribute(ApplicationConstants.SESSION_CUST_DESIG, null);
                            //new
                            session.setAttribute(ApplicationConstants.SESSION_REPORTSTOFLAG, dbReportsToFlag);
                            session.setAttribute(ApplicationConstants.SESSION_EMP_PRACTICE, dbPractice);
                            session.setAttribute(ApplicationConstants.SES_EMP_LOCATION, dbLocation);
                            session.setAttribute(ApplicationConstants.SES_EMP_NO, dbEmpNo);
                            
                            session.setAttribute(ApplicationConstants.SESSION_IS_PMO, dbIsPMO);
                            session.setAttribute(ApplicationConstants.JOB_POSTING_FLAG, DataSourceDataProvider.getInstance().isAuthorizedForJobPost(dbLoginId));
                            session.setAttribute(ApplicationConstants.SOURCING_FLAG, DataSourceDataProvider.getInstance().isSourcingTeam(dbEmpId));
                            session.setAttribute(ApplicationConstants.NO_DUES_REMAINDER, DataSourceDataProvider.getInstance().isAuthorizedForNoDueRemainders(dbLoginId));
                            session.setAttribute(ApplicationConstants.NO_DUES_APPROVALS, DataSourceDataProvider.getInstance().isAuthorizedForNoDueApprovals(dbLoginId));
                            
                            session.setAttribute(ApplicationConstants.EMEET_POSTING_FLAG, DataSourceDataProvider.getInstance().isAuthorizedForEmeets(dbLoginId));
                            session.setAttribute(ApplicationConstants.PF_EXCEL_ACCESS,DataSourceDataProvider.getInstance().isAuthorizedForPFExcelAccess(dbLoginId));
                             session.setAttribute(ApplicationConstants.SESSION_HIERARCHY_ACCESS,DataSourceDataProvider.getInstance().isAuthorizedForHierarchyAccess(dbLoginId));
                             session.setAttribute(ApplicationConstants.SESSION_HIERARCHY_ACCESS,DataSourceDataProvider.getInstance().isAuthorizedForHierarchyAccess(dbLoginId));
                             session.setAttribute(ApplicationConstants.REQUIREMENT_BY_CUSTOMER_ACCESS,AuthorizationManager.getInstance().isAuthorizedForSurveyForm("REQUIREMENT_SEARCH_BY_CUSTOMER_ACCESS", dbLoginId));
                             if(AuthorizationManager.getInstance().isAuthorizedForSurveyForm("PMOACTIVITY_ACCESS",dbLoginId))
                             session.setAttribute(ApplicationConstants.SESSION_PMO_ACTIVITY_ACCESS,1);
                             else
                                 session.setAttribute(ApplicationConstants.SESSION_PMO_ACTIVITY_ACCESS,0);
                           //  session.setAttribute(ApplicationConstants.PMO_ACTIVITY_ACCESS,AuthorizationManager.getInstance().isAuthorizedForSurveyForm("PMO_ACTIVITY_ACCESS", dbLoginId));
                             
                            session.setAttribute(ApplicationConstants.SESSION_EMPLOYEE_TYPE_ID, dbEmployeeType);
                            session.setAttribute(ApplicationConstants.SESSION_EMAIL, dbEmail);

                            if (dbLoginId.equalsIgnoreCase(Properties.getProperty("CEO"))) {
                                session.setAttribute(ApplicationConstants.SESSION_CEO, "yes");
                            } else {
                                session.setAttribute(ApplicationConstants.SESSION_CEO, "no");
                            }
                            if (dbReportsToId != "") {
                                String dbReportsToName = DataSourceDataProvider.getInstance().getFname_Lname(dbReportsToId);
                                session.setAttribute(ApplicationConstants.SESSION_REPORTSTO_NAME, dbReportsToName);
                            }

                            //Generating Team Members Map  for the valid manager users-- For Using on Team Menu Functionality
                            Map myTeamMembersMap = DataSourceDataProvider.getInstance().getMyTeamMembers(dbLoginId, dbDepartmentId);
                            Map getMyTeamForLeaveSearchMap = DataSourceDataProvider.getInstance().getMyTeamForLeaveSection(dbLoginId, dbDepartmentId);

                            session.setAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP, myTeamMembersMap);
                            Map secondaryTeamMap = DataSourceDataProvider.getInstance().getSecondaryTeamMap(dbEmpId);
                            // getSecondaryTeamMap();
                            session.setAttribute(ApplicationConstants.SESSION_SEC_TEAM, secondaryTeamMap);
                            //new
                            session.setAttribute(ApplicationConstants.SESSION_ASSOCIATED_PROJECTSIDS, DataSourceDataProvider.getInstance().getAssociatedProjectsById(dbEmpId));
                            session.setAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP_FOR_LEAVESEARCH, getMyTeamForLeaveSearchMap);
                            // System.out.println("before");
                            myRoles = DataSourceDataProvider.getInstance().getMyRoles(dbEmpId);
                             if(myRoles.get("1") != null){// If roles map contains Admin
                                   session.setAttribute(ApplicationConstants.SESSION_IS_ADMIN_ACCESS, 1);
                              }else{
                                     session.setAttribute(ApplicationConstants.SESSION_IS_ADMIN_ACCESS, 0);
                               }
                            
                            //System.out.println("after");
                            session.setAttribute(ApplicationConstants.SESSION_MY_ROLES, myRoles);
                            session.setAttribute(ApplicationConstants.SESSION_CC_ACCESS,DataSourceDataProvider.getInstance().isAuthorizedForSyncing(dbLoginId,myRoles));
                            session.setAttribute(ApplicationConstants.BDM_ASSOCIATIONS_ACCESS,AuthorizationManager.getInstance().isAuthorizedForSurveyForm("BDMASSOCIATIONS_ACCESS", dbLoginId));
                            if (DataSourceDataProvider.getInstance().isRequirementAdmin(dbEmpId)) {
                                session.setAttribute(ApplicationConstants.IS_REQUIREMENT_ADMIN, 1);
                            } else {
                                session.setAttribute(ApplicationConstants.IS_REQUIREMENT_ADMIN, 0);
                            }
                            if (Properties.getProperty("Network").contains(dbLoginId)) {
                                session.setAttribute(ApplicationConstants.ITTEAM, "yes");
                            } else {
                                session.setAttribute(ApplicationConstants.ITTEAM, "no");
                            }
                            
                          //  if(myRoles.containsValue("Admin")){
                            //    isAdmin = "YES";
                           // }
                           // session.setAttribute(ApplicationConstants.IS_ADMIN, isAdmin);
                            
                            String sessionvar = "E";
                            ServletActionContext.getContext().getApplication().put("sessionvar", sessionvar);
                            resultType = SUCCESS;

                            if (session.getAttribute("leaveId") != null) {
                                tempLeave = Integer.parseInt(session.getAttribute("leaveId").toString());
                            }
                            if (tempLeave > 0) {
                                setLeaveId(tempLeave);
                                session.setAttribute("roleId", 2);
                                session.setAttribute("roleName", "Employee");
                                resultType = "LEAVE";
                            }
                            int tempIssueId = 0;
                            String tempresM = null;
                            if (session.getAttribute("issueId") != null) {
                                tempIssueId = Integer.parseInt(session.getAttribute("issueId").toString());
                                if (session.getAttribute("resM") != null) {
                                    tempresM = session.getAttribute("resM").toString();
                                } else {
                                    tempresM = "";
                                }

                            }
                            if (tempIssueId > 0) {
                                setIssueId(tempIssueId);
                                setResM(tempresM);
                                session.setAttribute("roleId", 2);
                                session.setAttribute("roleName", "Employee");
                                resultType = "ISSUE";
                            }
                            int temptaskId = 0;
                            //  String tempresM=null;
                            if (session.getAttribute("taskId") != null) {
                                temptaskId = Integer.parseInt(session.getAttribute("taskId").toString());
                                if (session.getAttribute("resM") != null) {
                                    tempresM = session.getAttribute("resM").toString();
                                } else {
                                    tempresM = "";
                                }

                            }
                            if (temptaskId > 0) {
                                setTaskId(temptaskId);
                                setResM(tempresM);
                                session.setAttribute("roleId", 2);
                                session.setAttribute("roleName", "Employee");
                                resultType = "TASK";
                            }

                            if (session.getAttribute("objectId") != null && session.getAttribute("recruitmentRoleType") != null) {
                                //System.out.println("object not null");
                                //System.out.println("session.getAttribute(\"recruitmentRoleType\")-->"+session.getAttribute("recruitmentRoleType")+"asd");
                                System.out.println("requirement Add log");
                                resultType = "REQUIREMENTDETAILS";
                                List roleList = DataSourceDataProvider.getInstance().getRoleIdAndName(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString(), httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_DEPT_ID).toString());
                                //System.out.println("List2--->"+roleList);
                                if (roleList != null && roleList.size() > 0) {
                                    session.setAttribute("roleId", roleList.get(0));
                                    session.setAttribute("roleName", roleList.get(1));
                                    resultType = "REQUIREMENTDETAILS";
                                } else {
                                    session.setAttribute("roleId", 2);
                                    session.setAttribute("roleName", "Employee");
                                }
                                setObjectId(session.getAttribute("objectId").toString());
                                setRequirementAdminFlag(session.getAttribute("requirementAdminFlag").toString());
                                //setConsultId(session.getAttribute("consultId").toString());

                                setObjectId(session.getAttribute("objectId").toString());
                                setRequirementAdminFlag(session.getAttribute("requirementAdminFlag").toString());
                                //System.out.println("session.getAttribute(\"roleType\")---"+session.getAttribute("recruitmentRoleType"));


                            }


                            if (session.getAttribute("emptimeSheetID") != null) {
                                //  System.out.println("1004");
                                setTimeSheetID(Integer.parseInt(session.getAttribute("emptimeSheetID").toString()));
                                setEmpId(Integer.parseInt(session.getAttribute("employeeID").toString()));
                                setResourceType(session.getAttribute("resourceType").toString());
                                // setStatusValue(session.getAttribute("statusValue").toString());
                                //  setSecStatusValue(session.getAttribute("secStatusValue").toString());
                                session.setAttribute(ApplicationConstants.SESSION_ROLE_ID, String.valueOf(DataSourceDataProvider.getInstance().getDefaultRoleId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString())));

                                session.setAttribute("roleId", 2);
                                session.setAttribute("roleName", "Employee");
                                resultType = "TIMESHEET";
                            }

                            if (session.getAttribute("consultantId") != null && session.getAttribute("recConsultantId") != null) {

                                setId(session.getAttribute("id").toString());
                                setConsultantId(session.getAttribute("consultantId").toString());
                                setStatus(session.getAttribute("status").toString());
                                setRequirementId(session.getAttribute("requirementId").toString());
                                setRecConsultantId(session.getAttribute("recConsultantId").toString());
                                //session.setAttribute(ApplicationConstants.SESSION_ROLE_ID,String.valueOf(DataSourceDataProvider.getInstance().getDefaultRoleId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString())));
                                if (myRoles.get("10") != null) {
                                    session.setAttribute("roleId", 10);
                                    session.setAttribute("roleName", "Pre-Sales");
                                }
                                else{
                                     session.setAttribute("roleId", 2);
                                    session.setAttribute("roleName", "Employee");
                                }
                                resultType = "TECHREVIEW";
                            }
                            if (session.getAttribute("consultId") != null && session.getAttribute("objectId") != null) {
                                // resultType = "accessFailed";
                                resultType = "consultantDetails";
                                List roleList = DataSourceDataProvider.getInstance().getRoleIdAndName(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString(), httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_DEPT_ID).toString());
                                //System.out.println("List1--->"+roleList);
                                if (roleList != null && roleList.size() > 0) {
                                    session.setAttribute("roleId", roleList.get(0));
                                    session.setAttribute("roleName", roleList.get(1));
                                    resultType = "consultantDetails";
                                } else {
                                    session.setAttribute("roleId", 2);
                                    session.setAttribute("roleName", "Employee");
                                }
                                setObjectId(session.getAttribute("objectId").toString());
                                setRequirementAdminFlag(session.getAttribute("requirementAdminFlag").toString());
                                setConsultId(session.getAttribute("consultId").toString());

                            }

                        } else {
                            getHttpServletRequest().setAttribute("errorMessage", "<font color=\"red\" size=\"1.5\">Sorry Your Account is InActive! </font>");
                            resultType = INPUT;
                        }
                    } else {
                        //    getHttpServletRequest().setAttribute("errorMessage","<font color=\"red\" size=\"1.5\">Please Login with valid UserId and Password! </font>");

                        getHttpServletRequest().setAttribute("errorMessage", "<font color=\"red\" size=\"1.5\">Please Login with valid UserId and Password! if you have forgotten the password, click on the Forgot password button to reset your password  </font>");
                        resultType = INPUT;
                    }
                }


            } else {
                resultType = INPUT;
            }

            if (resultType == null) {
                getHttpServletRequest().setAttribute("errorMessage", "<font color=\"red\" size=\"1.5\">Please Login with valid UserId and Password! </font>");
                resultType = INPUT;
            }
        } catch (Exception ex) {
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            getHttpServletRequest().getSession(false).setAttribute("errorMessage", ex.toString());
            resultType = ERROR;
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
                throw new Exception(sqle);
            }
        }

        if (resultType.equalsIgnoreCase("SUCCESS")) {
            logUserAccess();
            resultType = checkUserAccess(myRoles, getHttpServletRequest().getSession(false));
            // logUserAccess_log();

        }
       // System.out.println("resultType---"+resultType);
        return resultType;
    }//end of the execute method

    /**
     *doLogout() method is used to invalidate session
     */
    public String doLogout() throws Exception {
        //  System.out.println("Employee Logout");
        try {
            if (getHttpServletRequest().getSession(false) != null) {
                getHttpServletRequest().getSession(false).invalidate();


            }
            resultType = SUCCESS;
        } catch (Exception ex) {
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            getHttpServletRequest().getSession(false).setAttribute("errorMessage", ex.toString());
            resultType = ERROR;
        }
        return resultType;
    }

    public void logUserAccess() throws Exception {
        try {
            if (getHttpServletRequest().getSession(false) != null) {
                if (getHttpServletRequest().getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                    String UserId = getHttpServletRequest().getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                    String forwarded = httpServletRequest.getHeader("X-FORWARDED-FOR");
                    String via = httpServletRequest.getHeader("VIA");
                    String remote = httpServletRequest.getRemoteAddr();
                    String agent = httpServletRequest.getHeader("User-Agent");
                    Timestamp accessedtime = (DateUtility.getInstance().getCurrentMySqlDateTime());
                    Connection connection = null;
                    PreparedStatement preparedStatement = null;
                    ResultSet resultSet = null;
                    boolean isInserted = false;
                    String query = null;
                    try {
                        connection = ConnectionProvider.getInstance().getConnection();
                        query = "insert into tblLogUserAccess(LoginId,X_FORWARDED_FOR1,VIA, REMOTE_ADDR,User_Agent,DateAccessed) values(?,?,?,?,?,?)";
                        preparedStatement = connection.prepareStatement(query);

                        preparedStatement.setString(1, UserId);
                        preparedStatement.setString(2, forwarded);
                        preparedStatement.setString(3, via);
                        preparedStatement.setString(4, remote);
                        preparedStatement.setString(5, agent);
                        preparedStatement.setTimestamp(6, accessedtime);
                        int x = preparedStatement.executeUpdate();
                        preparedStatement.close();
                        if (x > 0) {
                            isInserted = true;
                        }
                    } catch (SQLException sql) {
                        sql.printStackTrace();
                        throw new ServiceLocatorException(sql);
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

                }
            }
        } catch (Exception ex) {
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            ex.printStackTrace();
            getHttpServletRequest().getSession(false).setAttribute("errorMessage", ex.toString());
            resultType = ERROR;
        }
        //   return resultType;
    }

    public String checkUserAccess(Map rolesList, HttpSession session) throws Exception {
        try {

            if (getHttpServletRequest().getSession(false) != null) {

                String userId = getHttpServletRequest().getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();

                if (userId != null) {
                    String forwarded = httpServletRequest.getHeader("X-FORWARDED-FOR");
                    String incomingIpAddress = httpServletRequest.getRemoteAddr();
                    String actualIpAddress = SecurityProperties.getProperty(incomingIpAddress);
                    
                    String allowUser = SecurityProperties.getProperty(userId);

                    // System.out.println("incomingIpAddress------>"+incomingIpAddress+"-----forwarded--->"+forwarded+"----actualIpAddress---->"+actualIpAddress+"-----allowUser----->"+allowUser);
                    if (allowUser.contains("!")) {
                        // if(rolesList.containsValue("Admin") || rolesList.containsValue("Operations") || rolesList.containsValue("Sales") || rolesList.containsValue("Recruitment")) {
                        if (rolesList.containsValue("Admin") || rolesList.containsValue("Operations") || rolesList.containsValue("Sales") || rolesList.containsValue("Recruitment") || rolesList.containsValue("Marketing")|| rolesList.containsValue("Payroll")) {
                            if (incomingIpAddress != null) {

                                if (actualIpAddress.contains("!")) {
                                    //added for external access redirection
                                    session.setAttribute("roleId", 2);
                                    session.setAttribute("roleName", "Employee");
                                    //add end
                                    resultType = "accessFailed";
                                } else {
                                    resultType = "success";
                                }
                            } else {
                                resultType = "success";
                            }

                        }
                    }
                }
            }
        } catch (Exception ex) {
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            getHttpServletRequest().getSession(false).setAttribute("errorMessage", ex.toString());
            resultType = ERROR;
        }
        return resultType;
    }

    public void logUserAccess_log() throws Exception {
        try {
            if (getHttpServletRequest().getSession(false) != null) {
                if (getHttpServletRequest().getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                    String UserId = getHttpServletRequest().getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                    String forwarded = httpServletRequest.getHeader("X-FORWARDED-FOR");
                    String via = httpServletRequest.getHeader("VIA");
                    String remote = httpServletRequest.getRemoteAddr();
                    String agent = httpServletRequest.getHeader("User-Agent");
                    Timestamp accessedtime = (DateUtility.getInstance().getCurrentMySqlDateTime());

                    Logger logger = Logger.getLogger(LoginAction.class);



                    /*  FileAppender fileappender = new FileAppender(new HTMLLayout(),"/home/miracle/Desktop/Hubble.html");
                    logger.addAppender(fileappender);
                     */
                    SimpleLayout layout = new SimpleLayout();
                    FileAppender appender = null;
                    try {
                        //appender = new FileAppender(layout,"C:\\Documents and Settings\\miracle\\Desktop\\HubbleLog.txt",true);
                        appender = new FileAppender(layout, "/home/miracle/Hubble.log", true);

                    } catch (Exception e) {
                    }

                    logger.addAppender(appender);



                    logger.info("welcome");

                    logger.info(UserId);
                    logger.info(forwarded);
                    logger.info(via);
                    logger.info(remote);
                    logger.info(agent);
                    logger.info(accessedtime);

                    //System.out.println((UserId)+""+(forwarded)+via+remote+""+accessedtime);
                }




            }
        } catch (Exception ex) {
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            getHttpServletRequest().getSession(false).setAttribute("errorMessage", ex.toString());
            resultType = ERROR;
        }
        //   return resultType;
    }

    public String getResM() {
        return resM;
    }

    public void setResM(String resM) {
        this.resM = resM;
    }
//end of the doLogout method
    /*Form here bean section*/
    /*getUserId() used to get userId of the employee*/

    public String getUserId() {
        return userId;
    }

    /*setUserId() used to set userId of the employee*/
    public void setUserId(String userId) {
        this.userId = userId.toLowerCase().trim();
    }

    /*getPassword() used to get password of the employee*/
    public String getPassword() {
        return password;
    }

    /*setPassword() used to set password of the employee*/
    public void setPassword(String password) {
        this.password = password.trim();
    }

    /*getId() used to get id of the employee*/
    public String getId() {
        return id;
    }

    /*setId() used to set id of the employee*/
    public void setId(String id) {
        this.id = id.trim();
    }

    /*this is abstract method */
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.setHttpServletRequest(httpServletRequest);
    }

    /*getCurStatus() used to get curent status of the employee*/
    public String getCurStatus() {
        return curStatus;
    }

    /*setCurStatus() used to set curent status of the employee*/
    public void setCurStatus(String curStatus) {
        this.curStatus = curStatus;
    }

    /*getFirstName() used to get first name of the employee*/
    public String getFirstName() {
        return firstName;
    }

    /*setFirstName() used to set first name of the employee*/
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /*getLastName() used to get last name of the employee*/
    public String getLastName() {
        return lastName;
    }

    /*setLastName() used to set last name of the employee*/
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /*getMiddleName() used to get middle name of the employee*/
    public String getMiddleName() {
        return middleName;
    }

    /*setMiddleName() used to set middle name of the employee*/
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public int getIsManager() {
        return isManager;
    }

    public void setIsManager(int isManager) {
        this.isManager = isManager;
    }

    public int getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(int leaveId) {
        this.leaveId = leaveId;
    }

    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public Timestamp getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Timestamp createdtime) {
        this.createdtime = createdtime;
    }

    public int getTimeSheetID() {
        return timeSheetID;
    }

    public void setTimeSheetID(int timeSheetID) {
        this.timeSheetID = timeSheetID;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(String consultantId) {
        this.consultantId = consultantId;
    }

    /**
     * @return the resourceType
     */
    public String getResourceType() {
        return resourceType;
    }

    /**
     * @param resourceType the resourceType to set
     */
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getConsultId() {
        return consultId;
    }

    public void setConsultId(String consultId) {
        this.consultId = consultId;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getRecConsultantId() {
        return recConsultantId;
    }

    public void setRecConsultantId(String recConsultantId) {
        this.recConsultantId = recConsultantId;
    }

    public String getRequirementAdminFlag() {
        return requirementAdminFlag;
    }

    public void setRequirementAdminFlag(String requirementAdminFlag) {
        this.requirementAdminFlag = requirementAdminFlag;
    }

    public String getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(String requirementId) {
        this.requirementId = requirementId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
