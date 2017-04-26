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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public class CustLoginAction1 extends ActionSupport implements ServletRequestAware {
    
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
    /** Creates a new instance of CustLoginAction */
    public CustLoginAction1() {
    }
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }
    
    public String execute() throws Exception {
        String resultType="";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String dbLoginId="";
        String dbPassword = "";
        String dbAccountId= "";
        String dbCustomerName="";
        String dbCustId="";
        String dbContactStatus="";
        String dbDesignation="";
        passwordUtility = new PasswordUtility();
        //new
          String roleId="";
         // System.out.println("execute()--->"+getCustomerId());
        String SQL_QUERY="SELECT Id,LoginId,AccountId,Password,LastName,FirstName,MiddleName,ContactStatus,Email1 from tblCrmContact where LoginId ='"+getCustomerId().toLowerCase().trim()+"'";
        String roleName = "Customer Manager";
          dataSourceDataProvider = DataSourceDataProvider.getInstance();
        try{
           // System.out.println("in try");
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_QUERY);
            HttpSession session = httpServletRequest.getSession(true);
            String accountType = "";
            
            while(resultSet.next()) {
                dbCustId=resultSet.getString("Id");
                dbPassword= resultSet.getString("Password");
                dbLoginId =resultSet.getString("LoginId");
                dbAccountId=resultSet.getString("AccountId");
                dbContactStatus=resultSet.getString("ContactStatus");
                dbCustomerName = resultSet.getString("FirstName")+" "+resultSet.getString("MiddleName")+"."+resultSet.getString("LastName");
               // dbDesignation = resultSet.getString("Designation");
                   accountType = DataSourceDataProvider.getInstance().getAccountTypeById(Integer.parseInt(resultSet.getString("AccountId")));
            }
             roleId = dataSourceDataProvider.getRoleByProjectContactId(dbCustId);
            String decPassword = passwordUtility.decryptPwd(dbPassword);
          //  System.out.println("getEmpType-->"+getEmpType());
            // accountType = DataSourceDataProvider.get
            if(accountType.equals(getEmpType())){
            if(decPassword.equalsIgnoreCase(getCustomerPwd().trim())) {
                if("Active".equalsIgnoreCase(dbContactStatus)){
                    session.setAttribute(ApplicationConstants.SESSION_CUST_ACC_ID,dbAccountId);
                    session.setAttribute(ApplicationConstants.SESSION_CUST_ID,dbCustId);
                    session.setAttribute(ApplicationConstants.SESSION_CUST_NAME,dbCustomerName);
                    session.setAttribute(ApplicationConstants.SESSION_CUST_USER_ID,dbLoginId);
                    session.setAttribute(ApplicationConstants.SESSION_CUST_DESIG,dbDesignation);
                    session.setAttribute(ApplicationConstants.SESSION_EMPTYPE,"c");
                    session.setAttribute(ApplicationConstants.SESSION_ROLE_ID,"9");
                    //new
                     roleId = dataSourceDataProvider.getRoleByProjectContactId(dbCustId);
                    // System.out.println("roleId -->"+roleId);
                    session.setAttribute(ApplicationConstants.SESSION_CUSTOMER_ROLE,roleId);
                    String resourceType = DataSourceDataProvider.getInstance().getResourceTypeForProject(Integer.parseInt(dbCustId));
                            //DataSourceDataProvider.getInstance().getResourceTypeForProject(int projectContactId);
                    session.setAttribute(ApplicationConstants.SESSION_RESOURCETYPE,resourceType);
                    
                            session.setAttribute(ApplicationConstants.WORKING_COUNTRY,"USA");
                    //SESSION_OFFICE_EMAIL,dboffieemail
                    session.setAttribute(ApplicationConstants.SESSION_ASSOCIATED_PROJECTSIDS,DataSourceDataProvider.getInstance().getAssociatedProjectsById(Integer.parseInt(dbCustId)));
                    //System.out.println("projctid-->"+DataSourceDataProvider.getInstance().getAssociatedProjectsById(Integer.parseInt(dbCustId)));
                    //Generating Team Members Map  for the valid manager users-- For Using on Team Menu Functionality
                    Map myTeamMembersMap = DataSourceDataProvider.getInstance().getCustomerTeamMap(Integer.parseInt(dbCustId));
                    session.setAttribute(ApplicationConstants.SESSION_CUSTOMER_PROJECTIDS,DataSourceDataProvider.getInstance().getProjectsListByContactId(Integer.parseInt(dbCustId)));
                    
                    session.setAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP,myTeamMembersMap);
                    session.setAttribute(ApplicationConstants.SESSION_IS_TEAM_LEAD,DataSourceDataProvider.getInstance().getRoleIdByContactId(Integer.parseInt(dbCustId)));
                    
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
                               // session.setAttribute(ApplicationConstants.SESSION_ROLE_ID,String.valueOf(DataSourceDataProvider.getInstance().getDefaultRoleId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID).toString())));
                                
                               // session.setAttribute("roleId",9);
                               // session.setAttribute("roleName",);
                                resultType = "timesheet";
                            }
                }else{
                    httpServletRequest.setAttribute("custErrorMessage","<font color=\"red\" size=\"1.5\">You Account is Inactive! </font>");
                    resultType = INPUT;
                }
            } else {
                httpServletRequest.setAttribute("custErrorMessage","<font color=\"red\" size=\"1.5\">Please Login with valid UserId and Password! </font>");
                resultType = INPUT;
            }
        }//account type and screen check close
            else{
                httpServletRequest.setAttribute("custErrorMessage","<font color=\"red\" size=\"1.5\">Invalid Credentials! </font>");
                resultType = INPUT;
            }
        } catch(Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            ex.printStackTrace();
            httpServletRequest.getSession(false).setAttribute("custErrorMessage",ex.toString());
            resultType =  ERROR;
        }finally{
            try{
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
        return resultType;
    }
    
    /**
     *doLogout() method is used to invalidate session
     */
    public String doCustomerLogout() throws Exception {
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
}
