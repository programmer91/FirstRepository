/*
 * TestConnection.java
 *
 * Created on December 28, 2009, 3:26 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/*
 * @(#)LoginAction.java 1.0 Nov 01, 2007
 *
 * Copyright 2006 Miracle Software Systems(INDIA) Pvt Ltd. All rights reserved.
 *
 */

package com.mss.demo;
import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.PasswordUtility;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import java.util.Iterator;
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
public class TestConnection extends ActionSupport implements ServletRequestAware{
    
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
    
    private HttpSession httpSession;
    
    private int leaveId = 0;
    /** Creates a new instance of LoginAction */
  
    
    /**
     *execute() method ,in this method checking user details means he is authenticated persion or not,
     *user enter loginId and password in login page ,those details compare with database details,
     *loginId and password equal with database details then he can login success fully other wise
     *he will get some message.
     */
    public String execute() throws Exception {
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        passwordUtility = new PasswordUtility();
        String resultType="";
        String dbCurStatus ="";
        String dbLoginId="";
        String dbDepartmentId="";
        int dbIsManager=0;
        int dbIsTeamLead=0;
        String dbUserName="";
        String dbPassword = "";
        int dbEmpId=0;
        String dbReportsToId="";
        String dbEmpWorkPhno="";
        String dbEmpTitle = "";
        String password=null;
        int tempLeave = 0;
        String dbWorkingCountry="";
        String SQL_QUERY = "SELECT Id,LoginId, Password, IsManager,IsTeamLead,CurStatus,FName,LName,MName,ReportsTo,DepartmentId,WorkPhoneNo,TitleTypeId,WorkingCountry from tblEmployee where loginId= '"+ getUserId().toLowerCase().trim() + "'";
        
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_QUERY);
            HttpSession session = httpServletRequest.getSession(true);
            
            while(resultSet.next()) {
                dbPassword= resultSet.getString("Password");
                dbLoginId =resultSet.getString("LoginId");
                dbCurStatus =resultSet.getString("CurStatus");
                dbIsManager = resultSet.getInt("IsManager");
                dbIsTeamLead = resultSet.getInt("IsTeamLead");
                dbUserName = resultSet.getString("FName")+" "+resultSet.getString("MName")+"."+resultSet.getString("LName");
                dbEmpId = resultSet.getInt("Id");
                dbReportsToId = resultSet.getString("ReportsTo");
                dbDepartmentId = resultSet.getString("DepartmentId");
                dbEmpWorkPhno = resultSet.getString("WorkPhoneNo");
                dbEmpTitle = resultSet.getString("TitleTypeId");                        
                dbWorkingCountry = resultSet.getString("WorkingCountry");
                if(dbWorkingCountry.equals("All")) {
                    dbWorkingCountry = "%";
                }
            }
            
            
            String decPassword = passwordUtility.decryptPwd(dbPassword);
            //System.out.println("this is psswd "+decPassword);
            if("Registered".equalsIgnoreCase(dbCurStatus)) {
                httpServletRequest.setAttribute("errorMessage","<font color=\"red\" size=\"1.5\">Sorry! Your account will be activated soon!</font>");
                resultType = INPUT;
            } else {
                if(decPassword.equalsIgnoreCase(getPassword().trim())) {
                    if("Active".equalsIgnoreCase(dbCurStatus)){
                        
                        
                        session.setAttribute(ApplicationConstants.SESSION_USER_ID,dbLoginId);
                        session.setAttribute(ApplicationConstants.SESSION_USER_NAME,dbUserName);
                        session.setAttribute(ApplicationConstants.SESSION_IS_USER_MANAGER,dbIsManager);
                        session.setAttribute(ApplicationConstants.SESSION_IS_TEAM_LEAD,dbIsTeamLead);
                        session.setAttribute(ApplicationConstants.SESSION_EMP_ID,String.valueOf(dbEmpId));
                        session.setAttribute(ApplicationConstants.SESSION_REPORTSTO_ID,dbReportsToId);
                        // adding empWorkPhone number to session
                        session.setAttribute(ApplicationConstants.SESSION_EMP_WORKPHNO,dbEmpWorkPhno);
                        session.setAttribute(ApplicationConstants.SESSION_EMP_TITLE,dbEmpTitle);
                        session.setAttribute(ApplicationConstants.WORKING_COUNTRY,dbWorkingCountry);
                        if(dbReportsToId!=""){
                            String dbReportsToName = DataSourceDataProvider.getInstance().getFname_Lname(dbReportsToId);
                            session.setAttribute(ApplicationConstants.SESSION_REPORTSTO_NAME,dbReportsToName);
                        }
                        
                        //Generating Team Members Map  for the valid manager users-- For Using on Team Menu Functionality
                        Map myTeamMembersMap = DataSourceDataProvider.getInstance().getMyTeamMembers(dbLoginId,dbDepartmentId);
                        
                        session.setAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP,myTeamMembersMap);
                        Map myRoles = DataSourceDataProvider.getInstance().getMyRoles(dbEmpId);
                        session.setAttribute(ApplicationConstants.SESSION_MY_ROLES,myRoles);
                        
                        if(session.getAttribute("leaveId") != null) {
                            tempLeave = Integer.parseInt(session.getAttribute("leaveId").toString());
                        }
                        if(tempLeave > 0){
                            setLeaveId(tempLeave);
                            session.setAttribute("roleId",2);
                            session.setAttribute("roleName","employee");
                            resultType = "LEAVE";
                        }else if(tempLeave == 0){
                            resultType = SUCCESS;
                        }
                        
                    } else {
                        httpServletRequest.setAttribute("errorMessage","<font color=\"red\" size=\"1.5\">Sorry Your Account is InActive! </font>");
                        resultType = INPUT;
                    }
                }else{
                    httpServletRequest.setAttribute("errorMessage","<font color=\"red\" size=\"1.5\">Please Login with valid UserId and Password! </font>");
                    resultType = INPUT;
                }
            }
            
            if(resultType == null){
                httpServletRequest.setAttribute("errorMessage","<font color=\"red\" size=\"1.5\">Please Login with valid UserId and Password! </font>");
                resultType = INPUT;
            }
        }catch(Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
            resultType =  ERROR;
        }finally{
            try{
                if(resultSet!=null){
                    resultSet.close();
                    resultSet = null;
                }
                if(statement!=null){
                    statement.close();
                    statement = null;
                }
                if(connection!=null){
                    connection.close();
                    connection=null;
                }
            }catch(SQLException sqle){
                throw new Exception(sqle);
            }
        }
        
        return resultType;
    }//end of the execute method
    
    /**
     *doLogout() method is used to invalidate session
     */
    public String doLogout() throws Exception {
        try{
            if(httpServletRequest.getSession(false) != null){
                httpServletRequest.getSession(false).invalidate();
                
            }
            resultType = SUCCESS;
        }catch(Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
            resultType =  ERROR;
        }
        return resultType;
    }//end of the doLogout method
    
    
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
        this.httpServletRequest = httpServletRequest;
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
    
    
}



/**
 *
 * @author miracle
 */




