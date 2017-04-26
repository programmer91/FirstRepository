 /*
 * CustLoginAction.java
 *
 * Created on March 13, 2008, 10:27 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.customer.general;
import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.PasswordUtility;
import com.mss.mirage.util.ServiceLocatorException;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import java.util.Iterator;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
/**
 *
 * @author miracle
 */
public class CustLoginAction extends ActionSupport implements ServletRequestAware {
    
    private String resultType;
    
    private String customerId;
    private String empType;
    private String  customerPwd;
    
    private PasswordUtility passwordUtility;
    private DataSourceDataProvider dataSourceDataProvider;
    private HttpServletRequest httpServletRequest;
    private HttpSession httpSession;
     //new for customer
    private int timeSheetID;
    private int empId;
    
    private String resourceType;
   //  private String statusValue;
    // private String secStatusValue;
    /** Creates a new instance of CustLoginAction */
    public CustLoginAction() {
    }
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }
    
    public String execute() throws Exception {
        String resultType="";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        String dbLoginId="";
        String dbPassword = "";
        String dbAccountId= "";
        String dbCustomerName="";
        String dbCustId="";
        String dbContactStatus="";
        String dbDesignation="";
        String dbEmail="";
        String dbIsPrimary = "";
        //String dbResourceTitle="";
        passwordUtility = new PasswordUtility();
        //new
          String roleId="";
         // System.out.println("execute()--->"+getCustomerId());
          
          String lId=getCustomerId().toLowerCase().trim();
          String encPassword = PasswordUtility.encryptPwd(getCustomerPwd());
          String etypy="c";
          
          
          String customerDetails = null;
          String oppCustomerDetails="";
       //   SET v_frmReportsToDetails =  CONCAT(v_id,'|',v_loginid,'|',v_accountid,'|',v_name,'|',v_contactstatus,'|',v_email,'|',v_resourceTitle,'|',v_reporttoType,'|',v_reportsToEmail);
          
         // CALL spCustomerLogin('C_5','@102@102@110@102',@test);
        //String SQL_QUERY="SELECT Id,LoginId,AccountId,Password,LastName,FirstName,MiddleName,ContactStatus,Email1 from tblCrmContact where LoginId ='"+getCustomerId().toLowerCase().trim()+"' iFlag=1";
           // String SQL_QUERY="SELECT ObjectId as Id,ObjectType,LoginId,AccountId,Password,LastName,FirstName,MiddleName,STATUS as ContactStatus,Email as Email1,ResourceTitle from tblCrmContactAlias where LoginId ='"+getCustomerId().toLowerCase().trim()+"'";
        String roleName = "Customer Manager";
          dataSourceDataProvider = DataSourceDataProvider.getInstance();
        try{
           // System.out.println("in try");
            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("{call spCustomerLogin(?,?,?,?)}");
            callableStatement.setString(1, lId);
            callableStatement.setString(2,encPassword);
            
            callableStatement.registerOutParameter(3, Types.VARCHAR);
            callableStatement.registerOutParameter(4, Types.VARCHAR);
            callableStatement.execute();
            
            customerDetails = callableStatement.getString(3);
            oppCustomerDetails = callableStatement.getString(4);
           // System.out.println("customerDetails-->"+customerDetails);
            
          //  statement = connection.createStatement();
           // resultSet = statement.executeQuery(SQL_QUERY);
            HttpSession session = httpServletRequest.getSession(true);
            String accountType = "";
            String resourceType = "";
         /*   while(resultSet.next()) {
                dbCustId=resultSet.getString("Id");
                //dbPassword= resultSet.getString("Password");
                dbLoginId =resultSet.getString("LoginId");
                dbAccountId=resultSet.getString("AccountId");
                dbContactStatus=resultSet.getString("ContactStatus");
                dbCustomerName = resultSet.getString("v_name");
                
                roleId = resultSet.getString("Designation");
                 //  accountType = DataSourceDataProvider.getInstance().getAccountTypeById(Integer.parseInt(resultSet.getString("AccountId")));
                   // roleId = String.valueOf(resultSet.getString("ResourceTitle"));
                    //resourceType = resultSet.getString("ObjectType");
            }*/
           //  roleId = dataSourceDataProvider.getRoleByProjectContactId(dbCustId);
           // System.out.println("resultSet-->"+resultSet.getString("ResourceTitle"));
             
            //String decPassword = passwordUtility.decryptPwd(dbPassword);
          //  System.out.println("getEmpType-->"+getEmpType());
            // accountType = DataSourceDataProvider.get
          //  if(accountType.equals(getEmpType())){
            
            //Invalid user!
            if(customerDetails!=null && !customerDetails.equalsIgnoreCase("Invalid user!")) {
                
                int opportunityAccess = DataSourceDataProvider.getInstance().isAuthorizedForOppSoftware(lId);
                
                  
                  
                  if(opportunityAccess==1&&customerDetails.equalsIgnoreCase("IsPrimaryError")){
                      session.setAttribute(ApplicationConstants.OPPORTUNITY_SOFTWARE_ACCESS,opportunityAccess);
                      
                                 String detailArray[] = oppCustomerDetails.split("\\|");
                                  dbCustId = detailArray[0];
                dbLoginId = detailArray[1];
                dbAccountId = detailArray[2];
                dbCustomerName = detailArray[3];
                dbContactStatus = detailArray[4];
                dbEmail = detailArray[5];
                 if("Active".equalsIgnoreCase(dbContactStatus)){
                      session.setAttribute(ApplicationConstants.SESSION_CUST_ACC_ID,dbAccountId);
                    session.setAttribute(ApplicationConstants.SESSION_CUST_ID,dbCustId);
                    session.setAttribute(ApplicationConstants.SESSION_CUST_NAME,dbCustomerName);
                    session.setAttribute(ApplicationConstants.SESSION_CUST_USER_ID,dbLoginId);
                     session.setAttribute(ApplicationConstants.SESSION_EMPTYPE,"c");
                      session.setAttribute(ApplicationConstants.WORKING_COUNTRY,"USA");
                      session.setAttribute(ApplicationConstants.SESSION_ROLE_ID,"9");
                      session.setAttribute(ApplicationConstants.SESSION_CUSTOMER_ROLE,"-1");
                       String sessionvar="C";
                            ServletActionContext.getContext().getApplication().put("sessionvar", sessionvar);
                            resultType = "opportunity";
                    
                 }else{
                    httpServletRequest.setAttribute("custErrorMessage","<font color=\"red\" size=\"1.5\">You Account is Inactive! </font>");
                    resultType = INPUT;
                }
                      
                  }
                  else if(!customerDetails.equalsIgnoreCase("IsPrimaryError")) {
                //CONCAT(v_id,'|',v_loginid,'|',v_accountid,'|',v_name,'|',v_contactstatus,'|',v_email,'|',v_resourceTitle,'|',v_reporttoType,'|',v_reportsToEmail)
                String detailArray[] = customerDetails.split("\\|");
                dbCustId = detailArray[0];
                dbLoginId = detailArray[1];
                dbAccountId = detailArray[2];
                dbCustomerName = detailArray[3];
                dbContactStatus = detailArray[4];
                dbEmail = detailArray[5];
                roleId = detailArray[6];
               // dbIsPrimary = detailArray[6];
                
                
                
           // if(decPassword.equalsIgnoreCase(getCustomerPwd().trim())) {
                
               // System.out.println("dbContactStatus-->"+dbContactStatus);
                if("Active".equalsIgnoreCase(dbContactStatus)){
                    session.setAttribute(ApplicationConstants.SESSION_CUST_ACC_ID,dbAccountId);
                    session.setAttribute(ApplicationConstants.SESSION_CUST_ID,dbCustId);
                    session.setAttribute(ApplicationConstants.SESSION_CUST_NAME,dbCustomerName);
                    session.setAttribute(ApplicationConstants.SESSION_CUST_USER_ID,dbLoginId);
                   // session.setAttribute(ApplicationConstants.SESSION_CUST_DESIG,dbDesignation);
                     session.setAttribute(ApplicationConstants.SESSION_CUST_DESIG,roleId);
                    session.setAttribute(ApplicationConstants.SESSION_EMPTYPE,"c");
                  //  session.setAttribute(ApplicationConstants.SESSION_ROLE_ID,"9");
                      session.setAttribute(ApplicationConstants.SESSION_ROLE_ID,roleId);
                    //new
                    // roleId = dataSourceDataProvider.getRoleByProjectContactId(dbCustId);
                    // System.out.println("roleId -->"+roleId);
                    session.setAttribute(ApplicationConstants.SESSION_CUSTOMER_ROLE,roleId);
                  //  String resourceType = DataSourceDataProvider.getInstance().getResourceTypeForProject(Integer.parseInt(dbCustId));
                     // String resourceType = resultSet.getString("ObjectType");
                            //DataSourceDataProvider.getInstance().getResourceTypeForProject(int projectContactId);
                 //   session.setAttribute(ApplicationConstants.SESSION_RESOURCETYPE,resourceType);
                    
                            session.setAttribute(ApplicationConstants.WORKING_COUNTRY,"USA");
                    //SESSION_OFFICE_EMAIL,dboffieemail
                    session.setAttribute(ApplicationConstants.SESSION_ASSOCIATED_PROJECTSIDS,DataSourceDataProvider.getInstance().getAssociatedProjectsById(Integer.parseInt(dbCustId)));
                    //System.out.println("projctid-->"+DataSourceDataProvider.getInstance().getAssociatedProjectsById(Integer.parseInt(dbCustId)));
                    //Generating Team Members Map  for the valid manager users-- For Using on Team Menu Functionality
                    Map myTeamMembersMap = DataSourceDataProvider.getInstance().getCustomerTeamMap(Integer.parseInt(dbCustId));
                    session.setAttribute(ApplicationConstants.SESSION_CUSTOMER_PROJECTIDS,DataSourceDataProvider.getInstance().getProjectsListByContactId(Integer.parseInt(dbCustId)));
                    
                    session.setAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP,myTeamMembersMap);
                   // session.setAttribute(ApplicationConstants.SESSION_IS_TEAM_LEAD,DataSourceDataProvider.getInstance().getRoleIdByContactId(Integer.parseInt(dbCustId)));
                      //Dual reportsTo
                    Map secondaryTeamMap = DataSourceDataProvider.getInstance().getSecondaryTeamMap(Integer.parseInt(dbCustId));
                           // getSecondaryTeamMap();
                    session.setAttribute(ApplicationConstants.SESSION_SEC_TEAM,secondaryTeamMap);
                  
                    
                     session.setAttribute(ApplicationConstants.SESSION_IS_TEAM_LEAD,roleId);
                    
                    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID) != null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.SESSION_ROLE_ID);
                    }
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.SESSION_ROLE_ID,String.valueOf(9));
                    
                    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME) != null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.SESSION_ROLE_NAME);
                    }
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.SESSION_ROLE_NAME,roleName);
                    String sessionvar="C";
                            ServletActionContext.getContext().getApplication().put("sessionvar", sessionvar);
                         //   System.out.println("roleId-->"+roleId);
                    if(!roleId.equals("8"))
                    resultType=SUCCESS;
                    else
                       resultType="operations"; 
                 //   System.out.println("after operations");
                    if(session.getAttribute("emptimeSheetID") != null) {
                                
                                setTimeSheetID(Integer.parseInt(session.getAttribute("emptimeSheetID").toString()));
                                setEmpId(Integer.parseInt(session.getAttribute("employeeID").toString()));
                              setResourceType(session.getAttribute("resourceType").toString());
                                //setStatusValue(session.getAttribute("statusValue").toString());
                                // setSecStatusValue(session.getAttribute("secStatusValue").toString());
                                // session.setAttribute(ApplicationConstants.SESSION_ROLE_ID,String.valueOf(DataSourceDataProvider.getInstance().getDefaultRoleId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID).toString())));
                                
                               // session.setAttribute("roleId",9);
                               // session.setAttribute("roleName",);
                                resultType = "timesheet";
                            }
                }else{
                    httpServletRequest.setAttribute("custErrorMessage","<font color=\"red\" size=\"1.5\">You Account is Inactive! </font>");
                    resultType = INPUT;
                }
                
                }
                
                
                else {
                httpServletRequest.setAttribute("custErrorMessage","<font color=\"red\" size=\"1.5\">Please contact PMO team for proper primary project updation!</font>");
                resultType = INPUT;
            }
            } else {
                httpServletRequest.setAttribute("custErrorMessage","<font color=\"red\" size=\"1.5\">Please Login with valid UserId and Password! </font>");
                resultType = INPUT;
            }
        //}//account type and screen check close
          /*  else{
                httpServletRequest.setAttribute("custErrorMessage","<font color=\"red\" size=\"1.5\">Invalid Credentials! </font>");
                resultType = INPUT;
            }*/
        } catch(Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            ex.printStackTrace();
            httpServletRequest.getSession(false).setAttribute("custErrorMessage",ex.toString());
            resultType =  ERROR;
        }finally{
            try{
             /*   if(resultSet!=null){
                    resultSet.close();
                    resultSet = null;
                }*/
                if(callableStatement!=null){
                    callableStatement.close();
                    callableStatement = null;
                }
                if(connection!=null){
                    connection.close();
                    connection = null;
                }
                
            }catch(SQLException sqle){
                throw new ServiceLocatorException(sqle);
            }
        }
        return resultType;
    }
    
    /**
     *doLogout() method is used to invalidate session
     */
    public String doCustomerLogout() throws Exception {
      //  System.out.println("Customer Logout");
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
    
    public String getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    
    public String getCustomerPwd() {
        return customerPwd;
    }
    
    public void setCustomerPwd(String customerPwd) {
        this.customerPwd = customerPwd;
    }

    /**
     * @return the timeSheetID
     */
    public int getTimeSheetID() {
        return timeSheetID;
    }

    /**
     * @param timeSheetID the timeSheetID to set
     */
    public void setTimeSheetID(int timeSheetID) {
        this.timeSheetID = timeSheetID;
    }

    /**
     * @return the empId
     */
    public int getEmpId() {
        return empId;
    }

    /**
     * @param empId the empId to set
     */
    public void setEmpId(int empId) {
        this.empId = empId;
    }

    /**
     * @return the empType
     */
    public String getEmpType() {
        return empType;
    }

    /**
     * @param empType the empType to set
     */
    public void setEmpType(String empType) {
        this.empType = empType;
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

  
}