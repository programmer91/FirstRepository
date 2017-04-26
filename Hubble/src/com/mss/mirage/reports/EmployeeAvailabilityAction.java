 /*
  * EmployeeAvailability.java
  *
  * Created on November 22, 2007, 8:03 PM
  *
  * To change this template, choose Tools | Template Manager
  * and open the template in the editor.
  */

package com.mss.mirage.reports;

import com.mss.mirage.employee.general.EmployeeService;
import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.HibernateDataProvider;
import com.mss.mirage.util.AuthorizationManager;
import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.DataUtility;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.DefaultDataProvider;
import com.mss.mirage.util.Properties;
import com.mss.mirage.util.ReportProperties;
import com.mss.mirage.util.ServiceLocator;
import com.mss.mirage.util.ServiceLocatorException;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.ParameterNameAware;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.struts2.interceptor.ServletResponseAware;

/**
 *
 * @author miracle
 */
public class EmployeeAvailabilityAction extends ActionSupport implements ServletRequestAware,ParameterNameAware,ServletResponseAware {
    
    private HttpServletRequest httpServletRequest;
    private String resultType;
    
    /** The orgId is used for storing OraganizationId  of employee. */
    private String organization = ApplicationConstants.DEFAULT_ORG;
    
    private int id;
    
    private String empState;
    private List empCurrentStatus;
    
    /** The orgIdList is used for storing Organizationid Options. */
    private List organizationList;
    
    private String empReportType;
    private List empReportTypeList;
    
    /** The dataProvider is used for storing reference of ApplicationDataProvider methods. */
    private HibernateDataProvider dataProvider;
    
    private String sourceJrxmlFileLocation;
    private String destinationDirectory;
    private String basePath;
    private String fileLocationLastPart;
    
    private String organisationForm;
    
    private String organisationDesc_usa;
    private String organisationDesc_aus;
    private String organisationDesc_india;
    private String organisationDesc_sing;
    private String organisationDesc_cand;
    private String organisationDesc_uk;
    private String organisationDesc_itlokam;
    private String organisationDesc_chikiniki;
    
    private ReportsService reportsService;
    
    private String loginUserId;
    private String reportName;
    private Timestamp createdDate;
    
    private String description;
    
    private String submitFrom;
    
    private int userRoleId;
    
    private HttpSession httpSession;
    
    private List countryList = new ArrayList();
    
     /**
     *  startdate
     *  enddate
     */
    private String startdate;
    private String enddate;


        private DataSourceDataProvider dataSourceDataProvider;
        private HibernateDataProvider hibernateDataProvider;
        private DefaultDataProvider defaultDataProvider;
        private String departmentId;
        private int month;
        private String year;
        private List teamIdList;
        private List practiceIdList;
        private String subPractice;
        private String practiceId;
        private String country;
        private List departmentIdList;
        private Map empnamesList;

        private String resultMessage;
       
        private String file;
       
        private HttpServletResponse httpServletResponse;
        
        private String empnameById;
       
        private String fileName;
        public InputStream inputStream;
        public OutputStream outputStream;
        private String result;
       private boolean isClouserFlag;

    
    
    private InputStream fileInputStream;
    private String startDate;
    private String endDate;
    private int empType;
    private String empName;
    private String consultantId;
	private String loginId;
 private int examResultFalg = 0;
private EmployeeService employeeService;
    
    private Map reportsToIdMap;
    private Map opsContactIdMap;

     private Map salesLeadMap;
     private Map recmemebersMap;
    /** Creates a new instance of EmployeeAvailability */
     
      private List stateList;
      private boolean isEmpFlag;
      
      private int opsContactId;
private String docType;
private Map myProjects;
private String flag;  
     private String isAdminFlag;
     private Map locationsMap;
     
     private int activeProjectFlag;
     private List subPracticeList;
     
       private int domainId;
     private int topicId;

     
    public EmployeeAvailabilityAction() {
    }
    
   public String empLeaveReport() {
        resultType = LOGIN;
        httpSession = httpServletRequest.getSession(false);
        if (httpSession.getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpSession.getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("EMP_LEAVE_REPORT", userRoleId)) {

                try {

                    dataProvider = HibernateDataProvider.getInstance();
                      dataSourceDataProvider=DataSourceDataProvider.getInstance();
                    setCountryList(dataProvider.getContries(ApplicationConstants.COUNTRY_OPTIONS));

                    String roleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
                    String Country = (String) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.Living_COUNTRY);
                    setOpsContactIdMap(dataSourceDataProvider.getOpsContactId(Country, roleName));
                    DateUtility dateUtility = new DateUtility();
                    setEnddate(dateUtility.LastMonthLastDate());
                    setStartdate(dateUtility.FirstDateOfLastMonth());
                    resultType = SUCCESS;
                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }


            }
        }
        // System.out.println("result->"+resultType);
        return resultType;
    }
    /*
     * new aaction for empleave report on 21st nov by vkandregula
     * 
     */
       public String GenerateEmpLeaveReport() {
        resultType = LOGIN;
        httpSession =  httpServletRequest.getSession(false);
        if(httpSession.getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpSession.getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("EMP_LEAVE_REPORT",userRoleId)){
                try{
                    
                    dataProvider = HibernateDataProvider.getInstance();
                    setCountryList(dataProvider.getContries(ApplicationConstants.COUNTRY_OPTIONS));
                    DateUtility dateUtility = new DateUtility();
                    setEnddate(dateUtility.LastMonthLastDate());
                    setStartdate(dateUtility.FirstDateOfLastMonth());
                     resultType = SUCCESS;
                 }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
                
               
            }
        }
       // System.out.println("result->"+resultType);
        return resultType;
    }
            
    public String prepareEmpAvailability() {
        resultType = LOGIN;
        httpSession =  httpServletRequest.getSession(false);
        if(httpSession.getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            
            userRoleId = Integer.parseInt(httpSession.getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("PREPARE_EMP_AVAILABILTY",userRoleId)){
                try{
                    dataProvider = HibernateDataProvider.getInstance();
                    
                    setOrganizationList(dataProvider.getLkOrganization(ApplicationConstants.LKORGANIZATION_OPTION));
                    setEmpCurrentStatus(dataProvider.getEmpCurrentState(ApplicationConstants.EMP_CURRENT_STATUS));
                    setEmpReportTypeList(dataProvider.getEmpReportsType(ApplicationConstants.EMP_REPORT_TYPES_LIST));
                    resultType = SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpSession.setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }
    public String execute() throws Exception {
        return SUCCESS;
    }
    public String generate(){
        resultType = LOGIN;
        httpSession =  httpServletRequest.getSession(false);
        if(httpSession.getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            
            userRoleId = Integer.parseInt(httpSession.getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("GENERATE_EMP_AVAILABILTY",userRoleId)){
                try{
                    if(getSubmitFrom().equalsIgnoreCase("reportGeneration")){
                        //Date Format: MM_dd_yyyy_HH_mm_ss_a (12_06_2007_14_14_00_PM)
                        String currentDate = DateUtility.getInstance().getCurrentDate();
                        boolean isInsert = false;
                        reportsService = ServiceLocator.getReportsService();
                        boolean isReportGenerated = false;
                        
                        organisationForm =getOrganization();
                        organisationDesc_usa = ReportProperties.getProperty("Org_USA");
                        organisationDesc_aus = ReportProperties.getProperty("Org_AUSTRALIA");
                        organisationDesc_india = ReportProperties.getProperty("Org_INDIA");
                        organisationDesc_sing = ReportProperties.getProperty("Org_SINGAPORE");
                        organisationDesc_cand = ReportProperties.getProperty("Org_CANADA");
                        organisationDesc_uk = ReportProperties.getProperty("Org_UK");
                        organisationDesc_itlokam = ReportProperties.getProperty("Org_ITLOKAM");
                        organisationDesc_chikiniki = ReportProperties.getProperty("Org_CHIKINIKI");
                        
                        
                        if(organisationForm.equalsIgnoreCase(organisationDesc_usa)) {
                            fileLocationLastPart="USA";
                        }else if(organisationForm.equalsIgnoreCase(organisationDesc_aus)) {
                            fileLocationLastPart="Australia";
                        }else if(organisationForm.equalsIgnoreCase(organisationDesc_india)) {
                            fileLocationLastPart="India";
                        }else if(organisationForm.equalsIgnoreCase(organisationDesc_sing)) {
                            fileLocationLastPart="Singapore";
                        }else if(organisationForm.equalsIgnoreCase(organisationDesc_cand)) {
                            fileLocationLastPart="Canada";
                        }else if(organisationForm.equalsIgnoreCase(organisationDesc_uk)) {
                            fileLocationLastPart="UK";
                        }else if(organisationForm.equalsIgnoreCase(organisationDesc_itlokam)) {
                            fileLocationLastPart="ITlokam";
                        }else if(organisationForm.equalsIgnoreCase(organisationDesc_chikiniki)) {
                            fileLocationLastPart="Chikiniki";
                        }
                        
                        
                        
                        
                        sourceJrxmlFileLocation = ReportProperties.getProperty("EmpAvailabilityBaseDirectory")+getEmpState()+"In"+fileLocationLastPart+".jrxml";
                        destinationDirectory = ReportProperties.getProperty("EmpAvailabilityDestPDFDirectory");
                        
                        if(getEmpReportType().equalsIgnoreCase("pdf")) {
                            setReportName(getEmpState()+"In"+fileLocationLastPart+"_"+currentDate+".pdf");
                            isReportGenerated = reportsService.generateReportToPdfFile(sourceJrxmlFileLocation,destinationDirectory+getReportName());
                        }else if(getEmpReportType().equalsIgnoreCase("html")) {
                            setReportName(getEmpState()+"In"+fileLocationLastPart+"_"+currentDate+".html");
                            isReportGenerated = reportsService.generateReportToHtmlFile(sourceJrxmlFileLocation,destinationDirectory+getReportName());
                        }else if(getEmpReportType().equalsIgnoreCase("xml")) {
                            setReportName(getEmpState()+"In"+fileLocationLastPart+"_"+currentDate+".xml");
                            isReportGenerated = reportsService.generateReportToXmlFile(sourceJrxmlFileLocation,destinationDirectory+getReportName());
                        }
                        
                        // start -- add to tblReports
                        setLoginUserId(httpSession.getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                        
                        setDescription(getEmpState()+"In"+fileLocationLastPart);
                        
                        setCreatedDate(DateUtility.getInstance().getCurrentMySqlDateTime());
                        
                        if(isReportGenerated){
                            isInsert= reportsService.addEmpReportToDatabase(this);
                        }
                        // end -- add to tblReports
                        
                        
                        resultType = SUCCESS;
                    }//Close Submit From value checking
                    if(LOGIN.equals(resultType)) resultType = SUCCESS;
                    prepareEmpAvailability();
                    
                    
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpSession.setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
                
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }
    
    public String doDelete() {
        resultType = LOGIN;
        httpSession =  httpServletRequest.getSession(false);
        if(httpSession.getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            
            
            userRoleId = Integer.parseInt(httpSession.getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("DO_DELETE_REPORT",userRoleId)){
                try{
                    if(getSubmitFrom() == null){
                        boolean isFileDeleted = false;
                        try{
                            destinationDirectory = ReportProperties.getProperty("EmpAvailabilityDestPDFDirectory");
                            File sourceFile = new File(destinationDirectory+ServiceLocator.getReportsService().getReportName(getId()));
                            isFileDeleted = sourceFile.delete();
                        }catch(SecurityException se){
                            se.printStackTrace();
                        }
                        if(isFileDeleted){
                            boolean isDelete=ServiceLocator.getReportsService().deleteReport(this);
                        }
                        
                        
                        resultType = SUCCESS;
                    }//Closing Submit From value checking
                    if(LOGIN.equals(resultType)) resultType = SUCCESS;
                    prepareEmpAvailability();
                    
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpSession.setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
                
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }
    
     public String getDashBoardForEmpReports() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
           // System.out.println("workingCountry----->" + userRoleId);
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("SALES_ACTIVITY", userRoleId)) {
                try {
                //    System.out.println("userRoleName----->" + userRoleName);
                    if ("Payroll".equals(userRoleName) || "Operations".equals(userRoleName)) {
                      //  System.out.println("if block");
                        dataSourceDataProvider = DataSourceDataProvider.getInstance();
                        hibernateDataProvider = HibernateDataProvider.getInstance();
                        defaultDataProvider = DefaultDataProvider.getInstance();
                        setDepartmentIdList(hibernateDataProvider.getDepartment(ApplicationConstants.DEPARTMENT_OPTION));
                        setEmpnamesList(dataSourceDataProvider.getInstance().getAllIndianEmployees());
                        //getAllIndianEmployees
                        setDepartmentId("Sales");
                        setSubPractice("GreaterNewYork");
                        setSubPracticeList(dataSourceDataProvider.getSubPracticeByPractice(getPracticeId()));
                        setPracticeIdList(dataSourceDataProvider.getPracticeByDepartment(getDepartmentId()));
                        setMyProjects(new HashMap());
                        // setSubPracticeList(dataSourceDataProvider.getSubPracticeByPractice(getPracticeId()));
                        setTeamIdList(dataSourceDataProvider.getTeamBySubPractice(getSubPractice()));
                        setCountryList(hibernateDataProvider.getContries(ApplicationConstants.COUNTRY_OPTIONS));
                        setCountry("India");
                        Calendar now = Calendar.getInstance();
                        setMonth(now.get(Calendar.MONTH));
                        setYear(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
                        setCountryList(hibernateDataProvider.getContries(ApplicationConstants.COUNTRY_OPTIONS));
                        setLocationsMap(dataSourceDataProvider.getEmployeeLocationsList(getCountry()));
                        resultType = SUCCESS;
                    }


                    prepareList();
                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }
 /************************************************************
    Utilization Report prepare list
    
************************************************************/    
    
    public String prepareList() throws Exception {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            String roleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            //System.out.println("roleName---->"+roleName);
            hibernateDataProvider = HibernateDataProvider.getInstance();
            defaultDataProvider = DefaultDataProvider.getInstance();
            setEmployeeService(ServiceLocator.getEmployeeService());
            dataSourceDataProvider = dataSourceDataProvider.getInstance();
 Map rolesMap=(Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_ROLES);
            if(rolesMap.containsValue("Admin"))
                setIsAdminFlag("YES");
                        else
                setIsAdminFlag("NO");

            setPracticeIdList(dataSourceDataProvider.getPracticeByDepartment(getDepartmentId()));
            setReportsToIdMap(dataSourceDataProvider.getReportsToByDepartment(getDepartmentId()));
            setCountryList(hibernateDataProvider.getContries(ApplicationConstants.COUNTRY_OPTIONS));
            String Country = (String) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.Living_COUNTRY);
            setOpsContactIdMap(DataUtility.getInstance().getMapSortByValue(dataSourceDataProvider.getOpsContactId(Country, getIsAdminFlag())));
            setSalesLeadMap(DataUtility.getInstance().getMapSortByValue(dataSourceDataProvider.getAllSalesLeads()));
            setRecmemebersMap(DataUtility.getInstance().getMapSortByValue(dataSourceDataProvider.getRecrutimentLeadsAndManagers()));
            
            setStateList(hibernateDataProvider.getEmpCurrentState(ApplicationConstants.EMP_CURRENT_STATUS));
            resultType = SUCCESS;
        }
        return resultType;
    }

   /************************************************************
    end Utilization Report prepare list
    
    ************************************************************/ 
    

   public String getSalesClosuresReport() {
       // System.out.println("getSalesClosuresReport");

        result = SUCCESS;
        String responseString = "";
        try {
            httpServletRequest.getSession(false).removeAttribute("resultMessage");
            String fileLocation = "";
            //For creating Excel grind from Search result Grid
            // System.out.println("StartDate" + getStartdate());
            //  System.out.println("EndDate" + getEnddate());
            // fileLocation = generateEmpTimesheetList(getStartdate(), getEnddate(), getReportsToId(),getStatus());
            //setDepartmentId(getDepartmentId());
            //setEmpnameById(getEmpnameById());
            setYear(getYear());

            //setEmpnameById(getEmpnameById());

            //System.out.println("getYear-->" + getYear() + "getPracticeId-->" + getPracticeId() + "getTeamId-->" + getTeamId());

            // fileLocation = generateSalesClosuresSheet(getPracticeId(), getTeamId(), getYear());
            fileLocation = generateSalesClosuresSheet(getYear());
           // System.out.println("fileLocation-------->" + fileLocation);
            if (!"".equals(fileLocation)) {
                httpServletResponse.setContentType("application/force-download");
                File file = new File(fileLocation);
                Date date = new Date();
                fileName = file.getName();
                if (file.exists()) {
                    inputStream = new FileInputStream(file);
                    outputStream = httpServletResponse.getOutputStream();
                    httpServletResponse.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
                    int noOfBytesRead = 0;
                    byte[] byteArray = null;
                    while (true) {
                        byteArray = new byte[1024];
                        noOfBytesRead = inputStream.read(byteArray);
                        if (noOfBytesRead == -1) {
                            break;
                        }
                        outputStream.write(byteArray, 0, noOfBytesRead);
                    }

                } else {
                    throw new FileNotFoundException("File not found");
                }

                inputStream.close();
                outputStream.close();

            } else {
                setResultMessage("No records exists !!");
                httpServletRequest.getSession(false).setAttribute("resultMessageForSalesClosure", "<font style='color:red;font-size:15px;'>No records exists for the given Year !!</font>");
                result = INPUT;

            }
        } catch (FileNotFoundException ex) {
            try {
                httpServletResponse.sendRedirect("../general/exception.action?exceptionMessage='No File found'");
            } catch (IOException ex1) {
                System.out.println(ex1);
               // Logger.getLogger(DownloadExcelPayrollReport.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }
public String generateSalesClosuresSheet(String year) {
  //  System.out.println("isIsClouserFlag"+getIsClouserFlag());
        DateUtility dateutility = new DateUtility();
        String filePath = "";
        StringBuffer sb = null;
        Connection connection = null;

        /** callableStatement is a reference variable for CallableStatement . */
        CallableStatement callableStatement = null;

        /** preStmt,preStmtTemp are reference variable for PreparedStatement . */
        PreparedStatement preStmt = null, preStmtTemp = null;

        /** The queryString is useful to get  queryString result to the particular jsp page */
        String queryString = "";
        Statement statement = null;

        /** The statement is useful  to execute the above queryString */
        ResultSet resultSet = null;
        String timeSheetStatus = "";
        HashMap map = null;
        HashMap map1 = null;
        List finalList = new ArrayList();
        List finalClosuresList = new ArrayList();
        try {


            File file = new File(Properties.getProperty("Emp.SalesClouserReport.Path"));

            if (!file.exists()) {
                file.mkdirs();
            }

            FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath() + Properties.getProperty("OS.Compatabliliy.Download") + "US IT SALES CLOSURES For " + getYear() + ".xls");
//Class.forName("com.mysql.jdbc.Driver");

            connection = ConnectionProvider.getInstance().getConnection();
            //   connection =  DriverManager.getConnection("jdbc:mysql://172.17.0.150:3306/mirage?autoReconnect=true","AppAdmin","lokam001");
            String query = null;


//            query = "SELECT tblEmployee.Id,tblEmployee.LoginId,CONCAT(tblEmployee.FName,'.',tblEmployee.LName) AS NAME,tblEmployee.PracticeId,"
//                    + "tblEmployee.TeamId,HireDate FROM tblEmployee LEFT OUTER JOIN tblEmpReview ON(tblEmpReview.EmpId=tblEmployee.EmpNo)"
//                    + " WHERE DepartmentId='Sales' AND PracticeId='" + practiceId + "' AND "
//                    + "TeamId='" + teamId + "' GROUP BY tblEmployee.Id";
            query = "SELECT tblEmployee.Id,tblEmployee.LoginId,CONCAT(tblEmployee.FName,'.',tblEmployee.LName) AS NAME,tblEmployee.PracticeId,tblEmployee.TeamId,HireDate FROM tblEmployee LEFT OUTER JOIN tblEmpReview ON(tblEmpReview.EmpId=tblEmployee.EmpNo) WHERE DepartmentId='Sales' AND CurStatus='Active' GROUP BY tblEmployee.Id";


           // System.out.println("query123-->" + query);

            int j = 1;
            preStmt = connection.prepareStatement(query);

            resultSet = preStmt.executeQuery();


            while (resultSet.next()) {
               // System.out.println("while loop");

                int Id = resultSet.getInt("Id");
              //  System.out.println("id-------" + Id);
                String loginId = resultSet.getString("LoginId");
                callableStatement = connection.prepareCall("{call spSalesClousers(?,?,?)}");
                //System.out.println("afetr procedure call");
                callableStatement.setString(1, loginId);
                callableStatement.setString(2, year);
                callableStatement.registerOutParameter(3, Types.VARCHAR);
                callableStatement.execute();
                String resultMessage = callableStatement.getString(3);
                String Name = resultSet.getString("Name");
                Name=Name.replaceAll(" ", "");
                String PracticeId = resultSet.getString("PracticeId");
                String TeamId = resultSet.getString("TeamId");
                String HireDate = resultSet.getString("HireDate");

                System.out.println("result->" + resultMessage);
                //   System.out.println("result->" + resultMessage.split(Pattern.quote("#^$")));
                if (resultMessage != null) {
                    String QuaterlyBasedResults[] = resultMessage.split(Pattern.quote("*@!"));
                    String Q1[] = QuaterlyBasedResults[0].split(Pattern.quote("#^$"));
                    String Q2[] = QuaterlyBasedResults[1].split(Pattern.quote("#^$"));
                    String Q3[] = QuaterlyBasedResults[2].split(Pattern.quote("#^$"));
                    String Q4[] = QuaterlyBasedResults[3].split(Pattern.quote("#^$"));
                    String ConferenceCallsExecutedinQ1 = Q1[0];
                    String VendorRegistrationinQ1 = Q1[1];
                    String SVIClosedinQ1 = Q1[2];
                    String ONsiteVisitExecutedinQ1 = Q1[3];
                    String LogoCountQ1 = Q1[4];
                    String AwardsQ1 = Q1[5];
                    String BillingAmountQ1 = Q1[6];
                    String ClousersQ1 = Q1[7];
                    String RequirementGotQ1 = Q1[8];
                    String RecNoOfRequirementsQ1 = Q1[9];


                    String ConferenceCallsExecutedinQ2 = Q2[0];
                    String VendorRegistrationinQ2 = Q2[1];
                    String SVIClosedinQ2 = Q2[2];
                    String ONsiteVisitExecutedinQ2 = Q2[3];
                    String LogoCountQ2 = Q2[4];
                    String AwardsQ2 = Q2[5];
                    String BillingAmountQ2 = Q2[6];
                    String ClousersQ2 = Q2[7];
                    String RequirementGotQ2 = Q2[8];
                    String RecNoOfRequirementsQ2 = Q2[9];

                    String ConferenceCallsExecutedinQ3 = Q3[0];
                    String VendorRegistrationinQ3 = Q3[1];
                    String SVIClosedinQ3 = Q3[2];
                    String ONsiteVisitExecutedinQ3 = Q3[3];
                    String LogoCountQ3 = Q3[4];
                    String AwardsQ3 = Q3[5];
                    String BillingAmountQ3 = Q3[6];
                    String ClousersQ3 = Q3[7];
                    String RequirementGotQ3 = Q3[8];
                    String RecNoOfRequirementsQ3 = Q3[9];

                    String ConferenceCallsExecutedinQ4 = Q4[0];
                    String VendorRegistrationinQ4 = Q4[1];
                    String SVIClosedinQ4 = Q4[2];
                    String ONsiteVisitExecutedinQ4 = Q4[3];
                    String LogoCountQ4 = Q4[4];
                    String AwardsQ4 = Q4[5];
                    String BillingAmountQ4 = Q4[6];
                    String ClousersQ4 = Q4[7];
                    String RequirementGotQ4 = Q4[8];
                    String RecNoOfRequirementsQ4 = Q4[9];

                  


                    // String Description      = timeSheetStatus;
                    map = new HashMap();
                    //   System.out.println("innn_->"+EmpName+" -"+Description+" "+WorkDate+" "+P1Hrs+" "+P2Hrs);
                    map.put("SNO", String.valueOf(j));
                    map.put("Id", Id);
                    map.put("Name", Name);
                    map.put("PracticeId", PracticeId);
                    map.put("TeamId", TeamId);
                    map.put("HireDate", HireDate);

                    map.put("ConferenceCallsExecutedinQ1", ConferenceCallsExecutedinQ1);
                    map.put("SVIClosedinQ1", SVIClosedinQ1);
                    map.put("ONsiteVisitExecutedinQ1", ONsiteVisitExecutedinQ1);
                    map.put("VendorRegistrationinQ1", VendorRegistrationinQ1);
                    map.put("LogoCountQ1", LogoCountQ1);
                    map.put("AwardsQ1", AwardsQ1);
                    map.put("BillingAmountQ1", BillingAmountQ1);
                    map.put("ClousersQ1", ClousersQ1);
                    map.put("RequirementGotQ1", RequirementGotQ1);
                    map.put("RecNoOfRequirementsQ1", RecNoOfRequirementsQ1);

                    map.put("ConferenceCallsExecutedinQ2", ConferenceCallsExecutedinQ2);
                    map.put("SVIClosedinQ2", SVIClosedinQ2);
                    map.put("ONsiteVisitExecutedinQ2", ONsiteVisitExecutedinQ2);
                    map.put("VendorRegistrationinQ2", VendorRegistrationinQ2);
                    map.put("LogoCountQ2", LogoCountQ2);
                    map.put("AwardsQ2", AwardsQ2);
                    map.put("BillingAmountQ2", BillingAmountQ2);
                    map.put("ClousersQ2", ClousersQ2);
                    map.put("RequirementGotQ2", RequirementGotQ2);
                    map.put("RecNoOfRequirementsQ2", RecNoOfRequirementsQ2);

                    map.put("ConferenceCallsExecutedinQ3", ConferenceCallsExecutedinQ3);
                    map.put("SVIClosedinQ3", SVIClosedinQ3);
                    map.put("ONsiteVisitExecutedinQ3", ONsiteVisitExecutedinQ3);
                    map.put("VendorRegistrationinQ3", VendorRegistrationinQ3);
                    map.put("LogoCountQ3", LogoCountQ3);
                    map.put("AwardsQ3", AwardsQ3);
                    map.put("BillingAmountQ3", BillingAmountQ3);
                    map.put("ClousersQ3", ClousersQ3);
                    map.put("RequirementGotQ3", RequirementGotQ3);
                    map.put("RecNoOfRequirementsQ3", RecNoOfRequirementsQ3);

                    map.put("ConferenceCallsExecutedinQ4", ConferenceCallsExecutedinQ4);
                    map.put("SVIClosedinQ4", SVIClosedinQ4);
                    map.put("ONsiteVisitExecutedinQ4", ONsiteVisitExecutedinQ4);
                    map.put("VendorRegistrationinQ4", VendorRegistrationinQ4);
                    map.put("LogoCountQ4", LogoCountQ4);
                    map.put("AwardsQ4", AwardsQ4);
                    map.put("BillingAmountQ4", BillingAmountQ4);
                    map.put("ClousersQ4", ClousersQ4);
                    map.put("RequirementGotQ4", RequirementGotQ4);
                    map.put("RecNoOfRequirementsQ4", RecNoOfRequirementsQ4);

                    if(getIsClouserFlag()){
                        if(Integer.parseInt(ClousersQ1)>0 || Integer.parseInt(ClousersQ2)>0 || Integer.parseInt(ClousersQ3)>0 || Integer.parseInt(ClousersQ4)>0){
                         finalClosuresList.add(map);
                          j++;
                        }
                    }else{
                    finalList.add(map);
                     j++;
                    }
                   
                }
            }
if(getIsClouserFlag()){
    finalList=finalClosuresList;
}

            if (finalList.size() > 0) {
                filePath = file.getAbsolutePath() + Properties.getProperty("OS.Compatabliliy.Download") + "US IT SALES CLOSURES For " + getYear() + ".xls";
                // HSSFWorkbook workbook = new HSSFWorkbook();
                //  HSSFSheet worksheet = workbook.createSheet("USITSalesClosuresDoc");
                HSSFRow row1;
                //LogisticsDocBean logisticsDocBean = null;
                // index from 0,0... cell A1 is cell(0,0)

                // if(list.size()!=0){//
                //System.out.println("list size-->"+list.size());
                HSSFWorkbook workbook = new HSSFWorkbook();
                System.out.println("filePath " + filePath);
                HSSFSheet worksheet = workbook.createSheet("US IT Sales Closures Doc");
                for (int i = 0; i < 42; i++) {
                    worksheet.setColumnWidth(i, 10 * 256);
                   
                }
                HSSFCellStyle cs = workbook.createCellStyle();
                cs.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
                cs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cs.setBorderTop((short) 1); // single line border
                cs.setBorderBottom((short) 1); // single line border

                HSSFCellStyle cs1 = workbook.createCellStyle();

                cs1.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cs1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                cs1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cs1.setBorderTop((short) 1); // single line border
                cs1.setBorderBottom((short) 1); // single line border



                HSSFCellStyle headercs = workbook.createCellStyle();
                headercs.setFillForegroundColor(HSSFColor.AQUA.index);
                headercs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                headercs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                headercs.setBorderTop((short) 1); // single line border
                headercs.setBorderBottom((short) 1); // single line border

                HSSFFont timesBoldFont = workbook.createFont();
                timesBoldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                timesBoldFont.setColor(HSSFColor.WHITE.index);
                timesBoldFont.setFontName("Arial");
                headercs.setFont(timesBoldFont);

                HSSFFont footerFont = workbook.createFont();
                footerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                footerFont.setFontName("Arial");

                HSSFCellStyle footercs = workbook.createCellStyle();
                footercs.setFont(footerFont);
                footercs.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
                footercs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                footercs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                footercs.setBorderTop((short) 1); // single line border
                footercs.setBorderBottom((short) 1); // single line border

                HSSFCellStyle footercs1 = workbook.createCellStyle();
                footercs1.setFont(footerFont);
                footercs1.setFillForegroundColor(HSSFColor.YELLOW.index);
                footercs1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                footercs1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                footercs1.setBorderTop((short) 1); // single line border
                footercs1.setBorderBottom((short) 1); // single line border
                
                 HSSFCellStyle footercs2 = workbook.createCellStyle();
                footercs2.setFont(footerFont);
                footercs2.setFillForegroundColor(HSSFColor.ROYAL_BLUE.index);
                 footercs2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                footercs2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                footercs2.setBorderTop((short) 1); // single line border
                footercs2.setBorderBottom((short) 1); // single line border

                // HSSFRow row1;
                // LogisticsDocBean logisticsDocBean = null;
                // index from 0,0... cell A1 is cell(0,0)

                // if(list.size()!=0){//
                //System.out.println("list size-->"+list.size());
                HSSFCellStyle cellStyle = workbook.createCellStyle();
                HSSFCellStyle cellStyle1 = workbook.createCellStyle();
                HSSFCellStyle cellStyle2 = workbook.createCellStyle();
                HSSFCellStyle cellStyle3 = workbook.createCellStyle();
                HSSFCellStyle cellStyleHead = workbook.createCellStyle();
                HSSFFont font1 = workbook.createFont();
                HSSFFont font2 = workbook.createFont();
                HSSFFont font3 = workbook.createFont();
                HSSFFont font4 = workbook.createFont();
                HSSFFont fontHead = workbook.createFont();
                fontHead.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                // fontHead.setFontHeightInPoints((short)15);  //for font Size
                font4.setColor(HSSFColor.BLACK.index);

                cellStyle.setFillForegroundColor(HSSFColor.YELLOW.index);
                cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

                cellStyle.setFont(font4);
                //start	
                Date date = new Date();
                row1 = worksheet.createRow((short) 0);

                HSSFCell cellpo0 = row1.createCell((short) 0);
                // cellpo0.setCellValue("Purchase Order");
                HSSFCell cellpo1 = row1.createCell((short) 1);
                HSSFCell cellpo2 = row1.createCell((short) 2);
                // cellpo2.setCellValue("Created Date");
                HSSFCell cellpo3 = row1.createCell((short) 3);
                //cellpo3.setCellValue((date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+date.getDate());

                HSSFCell cellpo4 = row1.createCell((short) 4);
                HSSFCell cellpo5 = row1.createCell((short) 5);
                HSSFCell cellpo6 = row1.createCell((short) 6);
                HSSFCell cellpo7 = row1.createCell((short) 7);
                HSSFCell cellpo8 = row1.createCell((short) 8);
                HSSFCell cellpo9 = row1.createCell((short) 9);
                HSSFCell cellpo10 = row1.createCell((short) 10);
                HSSFCell cellpo11 = row1.createCell((short) 11);
                row1 = worksheet.createRow((short) 0);
                Cell cell[] = new Cell[46];
                for (int i = 0; i < 42; i++) {
                    cell[i] = row1.createCell((short) i);
                }

                // cell.setCellValue("Logistics Document :-Created Date : "+(date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+date.getDate());
                cell[0].setCellValue("US IT SALES CLOSURES YEAR:" + getYear());
                cellStyleHead.setFont(fontHead);
                cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                cellStyle.setFillBackgroundColor(HSSFColor.AQUA.index);
                 cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cell[0].setCellStyle(cellStyle);


                worksheet.addMergedRegion(CellRangeAddress.valueOf("A1:AP1"));

                //sno
                row1 = worksheet.createRow((short) 1);
                cell[0] = row1.createCell((short) 0);
                cell[0].setCellValue("SNo");
                cellStyleHead.setFont(fontHead);
                
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
               // cell[0].setCellStyle(cellStyleHead);
                cell[0].setCellStyle(footercs2);
                
                worksheet.addMergedRegion(CellRangeAddress.valueOf("A2:A3"));

                cell[0] = row1.createCell((short) 1);
                cell[0].setCellValue("EmpNo");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
               // cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(footercs2);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("B2:B3"));

                cell[0] = row1.createCell((short) 2);
                cell[0].setCellValue("EmpName");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                //cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(footercs2);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("C2:C3"));

                cell[0] = row1.createCell((short) 3);
                cell[0].setCellValue("Date of Joining");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
               // cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(footercs2);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("D2:D3"));

                cell[0] = row1.createCell((short) 4);
                cell[0].setCellValue("Domain");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
              //  cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(footercs2);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("E2:E3"));

                cell[0] = row1.createCell((short) 5);
                cell[0].setCellValue("Region");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
             //   cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(footercs2);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("F2:F3"));

                cell[0] = row1.createCell((short) 6);
                cell[0].setCellValue("Conference Calls Executed");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
              //  cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(footercs2);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("G2:J2"));

                cell[0] = row1.createCell((short) 10);
                cell[0].setCellValue("Onsite Meeting");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
              //  cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(footercs2);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("K2:N2"));

                cell[0] = row1.createCell((short) 14);
                cell[0].setCellValue("SVI Closed");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
             //   cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(footercs2);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("O2:R2"));

                cell[0] = row1.createCell((short) 18);
                cell[0].setCellValue("Vendor Registration");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            //    cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(footercs2);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("S2:V2"));
                
                cell[0] = row1.createCell((short) 22);
                cell[0].setCellValue("Requirement Got");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            //    cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(footercs2);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("W2:Z2"));
                
                cell[0] = row1.createCell((short) 26);
                cell[0].setCellValue("Closures");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            //    cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(footercs2);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("AA2:AD2"));

                cell[0] = row1.createCell((short) 30);
                cell[0].setCellValue("Logo's added");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
              //  cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(footercs2);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("AE2:AH2"));

                cell[0] = row1.createCell((short) 34);
                cell[0].setCellValue("Billing Amount");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
              //  cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(footercs2);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("AI2:AL2"));

                cell[0] = row1.createCell((short) 38);
                cell[0].setCellValue("Awards");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
              //   cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(footercs2);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("AM2:AP2"));

//                cell[0] = row1.createCell((short) 42);
//                cell[0].setCellValue("Awards");
//                cellStyleHead.setFont(fontHead);
//                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//              //  cell[0].setCellStyle(cellStyleHead);
//                 cell[0].setCellStyle(footercs2);
//                worksheet.addMergedRegion(CellRangeAddress.valueOf("AQ2:AT2"));

                row1 = worksheet.createRow((short) 2);
                row1.setRowStyle(cellStyleHead);
                cell[0] = row1.createCell((short) 6);
                cell[0].setCellValue("Q1");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
               //cell[0].setCellStyle(cellStyleHead);
                cell[0].setCellStyle(cs);

                cell[0] = row1.createCell((short) 7);
                cell[0].setCellValue("Q2");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
               // cell[0].setCellStyle(cellStyleHead);
                cell[0].setCellStyle(cs);

                cell[0] = row1.createCell((short) 8);
                cell[0].setCellValue("Q3");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                //cell[0].setCellStyle(cellStyleHead);
                cell[0].setCellStyle(cs);

                cell[0] = row1.createCell((short) 9);
                cell[0].setCellValue("Q4");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                //cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs);

                cell[0] = row1.createCell((short) 10);
                cell[0].setCellValue("Q1");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                //cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(footercs1);

                cell[0] = row1.createCell((short) 11);
                cell[0].setCellValue("Q2");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                //cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(footercs1);

                cell[0] = row1.createCell((short) 12);
                cell[0].setCellValue("Q3");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                //cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(footercs1);

                cell[0] = row1.createCell((short) 13);
                cell[0].setCellValue("Q4");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                //cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(footercs1);

                cell[0] = row1.createCell((short) 14);
                cell[0].setCellValue("Q1");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                //cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs);

                cell[0] = row1.createCell((short) 15);
                cell[0].setCellValue("Q2");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                //cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs);

                cell[0] = row1.createCell((short) 16);
                cell[0].setCellValue("Q3");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                //cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs);

                cell[0] = row1.createCell((short) 17);
                cell[0].setCellValue("Q4");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
              //  cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs);

                cell[0] = row1.createCell((short) 18);
                cell[0].setCellValue("Q1");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
             //   cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(footercs1);

                cell[0] = row1.createCell((short) 19);
                cell[0].setCellValue("Q2");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
               // cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(footercs1);

                cell[0] = row1.createCell((short) 20);
                cell[0].setCellValue("Q3");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
               // cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(footercs1);

                cell[0] = row1.createCell((short) 21);
                cell[0].setCellValue("Q4");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
               // cell[0].setCellStyle(cellStyleHead);
                cell[0].setCellStyle(footercs1);

                cell[0] = row1.createCell((short) 22);
                cell[0].setCellValue("Q1");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                //cell[0].setCellStyle(cellStyleHead);
                cell[0].setCellStyle(footercs);

                cell[0] = row1.createCell((short) 23);
                cell[0].setCellValue("Q2");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                //cell[0].setCellStyle(cellStyleHead);
                cell[0].setCellStyle(footercs);

                cell[0] = row1.createCell((short) 24);
                cell[0].setCellValue("Q3");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                //cell[0].setCellStyle(cellStyleHead);
                cell[0].setCellStyle(footercs);

                cell[0] = row1.createCell((short) 25);
                cell[0].setCellValue("Q4");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
               // cell[0].setCellStyle(cellStyleHead);
                cell[0].setCellStyle(footercs);

                cell[0] = row1.createCell((short) 26);
                cell[0].setCellValue("Q1");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
               // cell[0].setCellStyle(cellStyleHead);
                cell[0].setCellStyle(footercs1);

                cell[0] = row1.createCell((short) 27);
                cell[0].setCellValue("Q2");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                //cell[0].setCellStyle(cellStyleHead);
                cell[0].setCellStyle(footercs1);

                cell[0] = row1.createCell((short) 28);
                cell[0].setCellValue("Q3");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                //cell[0].setCellStyle(cellStyleHead);
                cell[0].setCellStyle(footercs1);

                cell[0] = row1.createCell((short) 29);
                cell[0].setCellValue("Q4");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                //cell[0].setCellStyle(cellStyleHead);
                cell[0].setCellStyle(footercs1);

                cell[0] = row1.createCell((short) 30);
                cell[0].setCellValue("Q1");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                //cell[0].setCellStyle(cellStyleHead);
                cell[0].setCellStyle(footercs);

                cell[0] = row1.createCell((short) 31);
                cell[0].setCellValue("Q2");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                //cell[0].setCellStyle(cellStyleHead);
                cell[0].setCellStyle(footercs);

                cell[0] = row1.createCell((short) 32);
                cell[0].setCellValue("Q3");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                //cell[0].setCellStyle(cellStyleHead);
                cell[0].setCellStyle(footercs);

                cell[0] = row1.createCell((short) 33);
                cell[0].setCellValue("Q4");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                //cell[0].setCellStyle(cellStyleHead);
                cell[0].setCellStyle(footercs);

                cell[0] = row1.createCell((short) 34);
                cell[0].setCellValue("Q1");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                //cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(footercs1);

                cell[0] = row1.createCell((short) 35);
                cell[0].setCellValue("Q2");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                //cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(footercs1);

                cell[0] = row1.createCell((short) 36);
                cell[0].setCellValue("Q3");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                //cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(footercs1);

                cell[0] = row1.createCell((short) 37);
                cell[0].setCellValue("Q4");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                //cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(footercs1);
                 
                 cell[0] = row1.createCell((short) 38);
                cell[0].setCellValue("Q1");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                //cell[0].setCellStyle(cellStyleHead);
                cell[0].setCellStyle(footercs);

                cell[0] = row1.createCell((short) 39);
                cell[0].setCellValue("Q2");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                //cell[0].setCellStyle(cellStyleHead);
                cell[0].setCellStyle(footercs);

                cell[0] = row1.createCell((short) 40);
                cell[0].setCellValue("Q3");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                //cell[0].setCellStyle(cellStyleHead);
                cell[0].setCellStyle(footercs);

                cell[0] = row1.createCell((short) 41);
                cell[0].setCellValue("Q4");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                //cell[0].setCellStyle(cellStyleHead);
                cell[0].setCellStyle(footercs);
                
//                cell[0] = row1.createCell((short) 42);
//                cell[0].setCellValue("Q1");
//                cellStyleHead.setFont(fontHead);
//                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//                //cell[0].setCellStyle(cellStyleHead);
//                 cell[0].setCellStyle(footercs1);
//
//                cell[0] = row1.createCell((short) 43);
//                cell[0].setCellValue("Q2");
//                cellStyleHead.setFont(fontHead);
//                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//                //cell[0].setCellStyle(cellStyleHead);
//                 cell[0].setCellStyle(footercs1);
//
//                cell[0] = row1.createCell((short) 44);
//                cell[0].setCellValue("Q3");
//                cellStyleHead.setFont(fontHead);
//                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//                //cell[0].setCellStyle(cellStyleHead);
//                 cell[0].setCellStyle(footercs1);
//
//                cell[0] = row1.createCell((short) 45);
//                cell[0].setCellValue("Q4");
//                cellStyleHead.setFont(fontHead);
//                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//                //cell[0].setCellStyle(cellStyleHead);
//                 cell[0].setCellStyle(footercs1);
                 
//                 cell[0] = row1.createCell((short) 46);
//                cell[0].setCellValue("Q1");
//                cellStyleHead.setFont(fontHead);
//                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//                //cell[0].setCellStyle(cellStyleHead);
//                cell[0].setCellStyle(footercs);
//
//                cell[0] = row1.createCell((short) 47);
//                cell[0].setCellValue("Q2");
//                cellStyleHead.setFont(fontHead);
//                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//                //cell[0].setCellStyle(cellStyleHead);
//                cell[0].setCellStyle(footercs);
//
//                cell[0] = row1.createCell((short) 48);
//                cell[0].setCellValue("Q3");
//                cellStyleHead.setFont(fontHead);
//                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//                //cell[0].setCellStyle(cellStyleHead);
//                cell[0].setCellStyle(footercs);
//
//                cell[0] = row1.createCell((short) 49);
//                cell[0].setCellValue("Q4");
//                cellStyleHead.setFont(fontHead);
//                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//                //cell[0].setCellStyle(cellStyleHead);
//                cell[0].setCellStyle(footercs);

                // cell7.setCellValue("Leaves");
//                for (int i = 0; i < 38; i++) {
//                    cell[i].setCellStyle(headercs);
//                }

                int count = 3;
                //   while (resultSet.next()) {
                if (finalList.size() > 0) {
                    Map salesClousersMap = null;
                    for (int i = 0; i < finalList.size(); i++) {
                        salesClousersMap = (Map) finalList.get(i);
                        row1 = worksheet.createRow((short) count++);
                        // row1 = sheet.createRow((short) count++);
                        for (int c = 0; c < 42; c++) {
                            cell[c] = row1.createCell((short) c);
                        }


                        cell[0].setCellValue((String) salesClousersMap.get("SNO"));
                        cell[1].setCellValue((Integer) salesClousersMap.get("Id"));
                        cell[2].setCellValue((String) salesClousersMap.get("Name"));
                        cell[3].setCellValue((String) salesClousersMap.get("HireDate"));
                        cell[4].setCellValue((String) salesClousersMap.get("PracticeId"));
                        cell[5].setCellValue((String) salesClousersMap.get("TeamId"));

                        cell[6].setCellValue((String) salesClousersMap.get("ConferenceCallsExecutedinQ1"));
                        cell[7].setCellValue((String) salesClousersMap.get("ConferenceCallsExecutedinQ2"));
                        cell[8].setCellValue((String) salesClousersMap.get("ConferenceCallsExecutedinQ3"));
                        cell[9].setCellValue((String) salesClousersMap.get("ConferenceCallsExecutedinQ4"));

                        cell[10].setCellValue((String) salesClousersMap.get("ONsiteVisitExecutedinQ1"));
                        cell[11].setCellValue((String) salesClousersMap.get("ONsiteVisitExecutedinQ2"));
                        cell[12].setCellValue((String) salesClousersMap.get("ONsiteVisitExecutedinQ3"));
                        cell[13].setCellValue((String) salesClousersMap.get("ONsiteVisitExecutedinQ4"));

                        cell[14].setCellValue((String) salesClousersMap.get("SVIClosedinQ1"));
                        cell[15].setCellValue((String) salesClousersMap.get("SVIClosedinQ2"));
                        cell[16].setCellValue((String) salesClousersMap.get("SVIClosedinQ3"));
                        cell[17].setCellValue((String) salesClousersMap.get("SVIClosedinQ4"));

                        cell[18].setCellValue((String) salesClousersMap.get("VendorRegistrationinQ1"));
                        cell[19].setCellValue((String) salesClousersMap.get("VendorRegistrationinQ2"));
                        cell[20].setCellValue((String) salesClousersMap.get("VendorRegistrationinQ3"));
                        cell[21].setCellValue((String) salesClousersMap.get("VendorRegistrationinQ4"));

                        cell[22].setCellValue((String) salesClousersMap.get("RequirementGotQ1"));
                        cell[23].setCellValue((String) salesClousersMap.get("RequirementGotQ2"));
                        cell[24].setCellValue((String) salesClousersMap.get("RequirementGotQ3"));
                        cell[25].setCellValue((String) salesClousersMap.get("RequirementGotQ4"));

                        cell[26].setCellValue((String) salesClousersMap.get("ClousersQ1"));
                        cell[27].setCellValue((String) salesClousersMap.get("ClousersQ2"));
                        cell[28].setCellValue((String) salesClousersMap.get("ClousersQ3"));
                        cell[29].setCellValue((String) salesClousersMap.get("ClousersQ4"));

                        cell[30].setCellValue((String) salesClousersMap.get("LogoCountQ1"));
                        cell[31].setCellValue((String) salesClousersMap.get("LogoCountQ2"));
                        cell[32].setCellValue((String) salesClousersMap.get("LogoCountQ3"));
                        cell[33].setCellValue((String) salesClousersMap.get("LogoCountQ4"));

                        cell[34].setCellValue((String) salesClousersMap.get("BillingAmountQ1"));
                        cell[35].setCellValue((String) salesClousersMap.get("BillingAmountQ2"));
                        cell[36].setCellValue((String) salesClousersMap.get("BillingAmountQ3"));
                        cell[37].setCellValue((String) salesClousersMap.get("BillingAmountQ4"));
                        
                        cell[38].setCellValue((String) salesClousersMap.get("AwardsQ1"));
                        cell[39].setCellValue((String) salesClousersMap.get("AwardsQ2"));
                        cell[40].setCellValue((String) salesClousersMap.get("AwardsQ3"));
                        cell[41].setCellValue((String) salesClousersMap.get("AwardsQ4"));
                        
//                        cell[42].setCellValue((String) salesClousersMap.get("AwardsQ1"));
//                        cell[43].setCellValue((String) salesClousersMap.get("AwardsQ2"));
//                        cell[44].setCellValue((String) salesClousersMap.get("AwardsQ3"));
//                        cell[45].setCellValue((String) salesClousersMap.get("AwardsQ4"));
                        




                        for (int h = 0; h < 42; h++) {
                            if (count % 2 == 0) {
                                cell[h].setCellStyle(cs);
                            } else {
                                cell[h].setCellStyle(cs1);
                            }

                        }
                        for (int h = 6; h < 10; h++) {
                            cell[h].setCellStyle(footercs);
                        }
                        for (int h = 10; h < 14; h++) {
                            cell[h].setCellStyle(headercs);
                        }
                        for (int h = 14; h < 18; h++) {
                            cell[h].setCellStyle(footercs);
                        }
                        for (int h = 18; h < 22; h++) {
                            cell[h].setCellStyle(headercs);
                        }
                        for (int h = 22; h < 26; h++) {
                             cell[h].setCellStyle(footercs);
                        }
                        for (int h = 26; h < 30; h++) {
                            cell[h].setCellStyle(headercs);
                        }
                        for (int h = 30; h < 34; h++) {
                            cell[h].setCellStyle(footercs1);
                        }
                        for (int h = 34; h < 38; h++) {
                            cell[h].setCellStyle(footercs);
                        }
                         for (int h = 38; h < 42; h++) {
                            cell[h].setCellStyle(headercs);
                        }
//                          for (int h = 42; h < 46; h++) {
//                            cell[h].setCellStyle(footercs);
//                        }

                    }
                    row1 = worksheet.createRow((short) count++);
                    for (int y = 0; y < 42; y++) {
                        cell[y] = row1.createCell((short) y);
                    }
                    cell[0].setCellValue("This US IT SALES CLOSURES Report is for  " + getYear());
                    cellStyleHead.setFont(fontHead);
                    cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                    cellStyle.setFillBackgroundColor(HSSFColor.AQUA.index);
                    cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                    cell[0].setCellStyle(cellStyle);
                     worksheet.addMergedRegion(CellRangeAddress.valueOf("A"+count+":AL"+count));

//                    cell[0].setCellValue("");
//                    cell[1].setCellValue("");
//                    cell[2].setCellValue("");
//                    cell[3].setCellValue("This");
//                    cell[4].setCellValue("US IT SALES");
//                    cell[5].setCellValue(" CLOSURES");
//                    cell[6].setCellValue("Report is for");
//                    cell[7].setCellValue("year " + year);
//                    cell[8].setCellValue("");
//                    cell[9].setCellValue("");
//                    cell[10].setCellValue("");
//                    cell[11].setCellValue("");


                    for (int f = 0; f < 42; f++) {
                        cell[f].setCellStyle(footercs1);
                    }

                }
//                for (int f = 0; f < 38; f++) {
//                    worksheet.autoSizeColumn((short) f);
//
//                }
                worksheet.autoSizeColumn((short) 2);
                worksheet.autoSizeColumn((short) 5);
                worksheet.setColumnWidth(3, 15 * 256);

                workbook.write(fileOut);
                fileOut.flush();
                fileOut.close();

            }


        } catch (FileNotFoundException fne) {
            //   System.out.println("FileNotFoundException-->"+fne.getMessage());
            fne.printStackTrace();
        } catch (IOException ioe) {
            //  System.out.println("IOException-->"+ioe.getMessage());
            ioe.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
            //System.out.println("Exception-->"+ex.getMessage());
            //e.printStackTrace();
        } finally {

            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (preStmt != null) {
                    preStmt.close();
                    preStmt = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (Exception se) {
                se.printStackTrace();
            }
        }

        return filePath;
    }

 /* Exam result Excel sheet */
// =======================================
    public String getExamReport() {

        result = SUCCESS;
        String responseString = "";
        try {
            httpServletRequest.getSession(false).removeAttribute("resultMessage");
            String fileLocation = "";
            setExamResultFalg(1);
            setStartDate(getStartDate());
            setEndDate(getEndDate());
            getEmpType();
            getEmpName();
            getConsultantId();
            //System.out.println("getStartDate-->" + getStartDate() + "getEndDate-->" + getEndDate() + "getEmpType-->" + getEmpType() + "getEmpName-->" + getEmpName() + "getConsultantId-->" + getConsultantId());

            fileLocation = generateExamReportsheetList(getStartDate(), getEndDate(), getEmpType(), getEmpName(), getConsultantId());
            //System.out.println("fileLocation-------->" + fileLocation);
            if (!"".equals(fileLocation)) {
                httpServletResponse.setContentType("application/force-download");
                File file = new File(fileLocation);
                Date date = new Date();
                fileName = file.getName();
                if (file.exists()) {
                    inputStream = new FileInputStream(file);
                    outputStream = httpServletResponse.getOutputStream();
                    httpServletResponse.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
                    int noOfBytesRead = 0;
                    byte[] byteArray = null;
                    while (true) {
                        byteArray = new byte[1024];
                        noOfBytesRead = inputStream.read(byteArray);
                        if (noOfBytesRead == -1) {
                            break;
                        }
                        outputStream.write(byteArray, 0, noOfBytesRead);
                    }

                } else {
                    throw new FileNotFoundException("File not found");
                }

                inputStream.close();
                outputStream.close();

            } else {
                System.out.println("no records");
                setResultMessage("No records exists !!");
                httpServletRequest.getSession(false).setAttribute("resultMessage1", "<font style='color:red;font-size:15px;'>No records exists for the employee between given dates!!</font>");
                result = INPUT;
                httpServletRequest.getSession(false).setAttribute("ExamResultFalg", "1");
            }
        } catch (FileNotFoundException ex) {
            try {
                httpServletResponse.sendRedirect("../general/exception.action?exceptionMessage='No File found'");
            } catch (IOException ex1) {
                ex1.printStackTrace();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public String generateExamReportsheetList(String startDate, String endDate, int empType, String empName, String consultantId) {

        DateUtility dateutility = new DateUtility();
        String filePath = "";
        StringBuffer sb = null;
        Connection connection = null;
        CallableStatement callableStatement = null;
        PreparedStatement preStmt = null, preStmtTemp = null;
        String queryString = "";
        Statement statement = null;
        ResultSet resultSet = null;
        String timeSheetStatus = "";
        HashMap map = null;
        HashMap map1 = null;

        List finalList = new ArrayList();
        try {
            File file = new File(Properties.getProperty("Exam.Report.Path"));

            if (!file.exists()) {
                file.mkdirs();
            }
            //System.out.println("test");
            FileOutputStream fileOut =null;
            if(!"".equals(empName)){
            fileOut = new FileOutputStream(file.getAbsolutePath() + Properties.getProperty("OS.Compatabliliy.Download") + "Exams Report of " + empName + ".xls");
            }
            else{
            fileOut = new FileOutputStream(file.getAbsolutePath() + Properties.getProperty("OS.Compatabliliy.Download") + "Exams Report of All Employees.xls");
            }
            connection = ConnectionProvider.getInstance().getConnection();
            String query = null;
            query = "SELECT tblEcertResult.EmpId AS empId,TopicId,TopicName,ROUND((Marks/TotalQuestions)*100,2) AS Percentage ,ExamKeyId,Marks,TotalQuestions,AttemptedQuestions,DateSubmitted,ExamStatus "
                    + "FROM tblEcertResult LEFT OUTER JOIN tblEcertKey ON (tblEcertResult.ExamKeyId=tblEcertKey.ID) "
                    + "LEFT OUTER JOIN tblEcertTopics ON (tblEcertKey.TopicId=tblEcertTopics.ID) ";

            boolean andFlag = false;
            if ((getEmpType() != 0) || !"".equals(getEmpName()) || !"".equals(getStartDate()) || !"".equals(getEndDate()) || !"".equals(getConsultantId())) {
                query = query + " WHERE ";
            }
            if (!"".equals(getStartDate()) && !andFlag) {
                query = query + "date(DateSubmitted) >= '" + DateUtility.getInstance().convertStringToMySQLDate(getStartDate()) + "' ";
                andFlag = true;
            } else if (!"".equals(getStartDate()) && andFlag) {
                query = query + "AND date(DateSubmitted) >= '" + DateUtility.getInstance().convertStringToMySQLDate(getStartDate()) + "' ";
            }
            if (!"".equals(getEndDate()) && !andFlag) {
                query = query + "date(DateSubmitted) <= '" + DateUtility.getInstance().convertStringToMySQLDate(getEndDate()) + "' ";
                andFlag = true;
            } else if (!"".equals(getEndDate()) && andFlag) {
                query = query + "AND date(DateSubmitted) <= '" + DateUtility.getInstance().convertStringToMySQLDate(getEndDate()) + "' ";
            }
            if (getEmpType() != 0) {
                if (!"".equals(getEmpType()) && !andFlag) {
                    query = query + "tblEcertResult.EmpType =" + getEmpType() + " ";
                    andFlag = true;
                } else if (!"".equals(getEmpType()) && andFlag) {
                    query = query + "AND tblEcertResult.EmpType =" + getEmpType() + " ";
                }
            }
            if (getEmpType() == 1) {
                if (!"".equals(getLoginId()) && !andFlag) {
                    query = query + "tblEcertResult.EmpId = '" + getLoginId() + "' ";
                    andFlag = true;
                } else if (!"".equals(getLoginId()) && andFlag) {
                    query = query + "AND tblEcertResult.EmpId = '" + getLoginId() + "' ";
                }
            }
            if (getEmpType() == 2) {
                if (!"".equals(getConsultantId()) && !andFlag) {
                    query = query + "tblEcertResult.EmpId = '" + getConsultantId() + "' ";
                    andFlag = true;
                } else if (!"".equals(getConsultantId()) && andFlag) {
                    query = query + "AND tblEcertResult.EmpId = '" + getConsultantId() + "' ";
                }
            }
 if(getDomainId() != 0 && !andFlag){
                      query = query + " tblEcertKey.DomainId = "+getDomainId();
                     andFlag = true;
                     }else if(getDomainId() != 0){
                           query = query +" AND tblEcertKey.DomainId = "+getDomainId();
                     }
                     if(getTopicId() != 0 && !andFlag){
                          query = query +" tblEcertKey.TopicId = "+getTopicId();
                         andFlag = true;
                     }
                     else if(getTopicId() != 0){
                           query = query +" AND tblEcertKey.TopicId = "+getTopicId();
                     }
            query = query + " ORDER BY ExamKeyId LIMIT 500";

          //  System.out.println("query-->" + query);

            String reportToName = "";
            List teamList = null;

            int j = 1;
            preStmt = connection.prepareStatement(query);
            resultSet = preStmt.executeQuery();

            while (resultSet.next()) {
                String EmpId = resultSet.getString("EmpId");
                int TopicId = resultSet.getInt("TopicId");
                String TopicName = resultSet.getString("TopicName");
                float Percentage = Math.round(resultSet.getFloat("Percentage"));
                int ExamKeyId = resultSet.getInt("ExamKeyId");
                int Marks = resultSet.getInt("Marks");
                int TotalQuestions = resultSet.getInt("TotalQuestions");
                int AttemptedQuestions = resultSet.getInt("AttemptedQuestions");
                String DateSubmitted = resultSet.getString("DateSubmitted");
                String ExamStatus = resultSet.getString("ExamStatus");

                map = new HashMap();

                map.put("SNO", String.valueOf(j));
                map.put("EmpId", EmpId);
                map.put("Topic Id", TopicId);
                map.put("Topic Name", TopicName);
                map.put("Percentage", Percentage);
                map.put("Exam KeyId", ExamKeyId);
                map.put("Marks", Marks);
                map.put("Total Questions", TotalQuestions);
                map.put("Attempted Questions", AttemptedQuestions);
                map.put("Date Submitted", DateSubmitted);
                map.put("Exam Status", ExamStatus);

                finalList.add(map);
                j++;
            }

            //System.out.println("finalList.size()  " + finalList.size());
            if (finalList.size() > 0) {
                if(!"".equals(empName)){
            filePath = file.getAbsolutePath() + Properties.getProperty("OS.Compatabliliy.Download") + "Exams Report of " + empName + ".xls";
            }
            else{
            filePath = file.getAbsolutePath() + Properties.getProperty("OS.Compatabliliy.Download") + "Exams Report of All Employees.xls";
            }
                
                HSSFWorkbook hssfworkbook = new HSSFWorkbook();
                HSSFSheet sheet = hssfworkbook.createSheet("Exam result Excel Sheet");

                HSSFCellStyle cs = hssfworkbook.createCellStyle();
                cs.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
                cs.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cs.setBorderTop((short) 1); // single line border
                cs.setBorderBottom((short) 1); // single line border

                HSSFCellStyle cs1 = hssfworkbook.createCellStyle();
                cs1.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
                cs1.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                cs1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cs1.setBorderTop((short) 1); // single line border
                cs1.setBorderBottom((short) 1); // single line border

                HSSFCellStyle cs2 = hssfworkbook.createCellStyle();
                cs2.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
                cs2.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                cs2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cs2.setBorderTop((short) 1); // single line border
                cs2.setBorderBottom((short) 1); // single line border

                HSSFCellStyle cs3 = hssfworkbook.createCellStyle();
                cs3.setFillForegroundColor(HSSFColor.LIME.index);
                cs3.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                cs3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cs3.setBorderTop((short) 1); // single line border
                cs3.setBorderBottom((short) 1); // single line border

                HSSFCellStyle cs4 = hssfworkbook.createCellStyle();
                cs4.setFillForegroundColor(HSSFColor.YELLOW.index);
                cs4.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                cs4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cs4.setBorderTop((short) 1); // single line border
                cs4.setBorderBottom((short) 1); // single line border

                HSSFCellStyle headercs = hssfworkbook.createCellStyle();
                headercs.setFillForegroundColor(HSSFColor.AQUA.index);
                headercs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                headercs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                headercs.setBorderTop((short) 1); // single line border
                headercs.setBorderBottom((short) 1); // single line border

                HSSFFont timesBoldFont = hssfworkbook.createFont();
                timesBoldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                timesBoldFont.setColor(HSSFColor.WHITE.index);
                timesBoldFont.setFontName("Arial");
                headercs.setFont(timesBoldFont);

                HSSFFont footerFont = hssfworkbook.createFont();
                footerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                footerFont.setFontName("Arial");

                HSSFCellStyle footercs = hssfworkbook.createCellStyle();
                footercs.setFont(footerFont);
                footercs.setFillForegroundColor(HSSFColor.TAN.index);
                footercs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                footercs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                footercs.setBorderTop((short) 1); // single line border
                footercs.setBorderBottom((short) 1); // single line border

                HSSFCellStyle footercs1 = hssfworkbook.createCellStyle();
                footercs1.setFont(footerFont);
                footercs1.setFillForegroundColor(HSSFColor.AQUA.index);
                footercs1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                footercs1.setBorderTop((short) 1); // single line border
                footercs1.setBorderBottom((short) 1); // single line border



                HSSFDataFormat df = hssfworkbook.createDataFormat();
                HSSFRow row = sheet.createRow((short) 0);
                HSSFCell cell = row.createCell((short) 0);
                // HSSFRow row1 = sheet.createRow((short)0);
                HSSFCell cell1 = row.createCell((short) 1);

                HSSFCell cell2 = row.createCell((short) 2);
                HSSFCell cell3 = row.createCell((short) 3);

                HSSFCell cell4 = row.createCell((short) 4);
                HSSFCell cell5 = row.createCell((short) 5);
                HSSFCell cell6 = row.createCell((short) 6);
                HSSFCell cell7 = row.createCell((short) 7);

                HSSFCell cell8 = row.createCell((short) 8);
                HSSFCell cell9 = row.createCell((short) 9);


                cell.setCellValue("SNO");
                cell1.setCellValue("Candidate Name");
                cell2.setCellValue("Exam Name");
                cell3.setCellValue("Percentage");
                cell4.setCellValue("Marks");
                cell5.setCellValue("Total Questions");
                cell6.setCellValue("Attempted Questions");
                cell7.setCellValue("Date taken");
                cell8.setCellValue("Exam Status");


                cell.setCellStyle(headercs);
                cell1.setCellStyle(headercs);
                cell2.setCellStyle(headercs);
                cell3.setCellStyle(headercs);
                cell4.setCellStyle(headercs);
                cell5.setCellStyle(headercs);
                cell6.setCellStyle(headercs);
                cell7.setCellStyle(headercs);
                cell8.setCellStyle(headercs);

                int count = 1;
                //   while (resultSet.next()) {
                if (finalList.size() > 0) {
                    Map examResultMap = null;
                    for (int i = 0; i < finalList.size(); i++) {
                        examResultMap = (Map) finalList.get(i);
                        row = sheet.createRow((short) count++);
                        cell = row.createCell((short) 0);
                        //  HSSFRow row2 = sheet.createRow((short)0);
                        cell1 = row.createCell((short) 1);
                        cell2 = row.createCell((short) 2);
                        cell3 = row.createCell((short) 3);
                        cell4 = row.createCell((short) 4);
                        cell5 = row.createCell((short) 5);
                        cell6 = row.createCell((short) 6);
                        cell7 = row.createCell((short) 7);
                        cell8 = row.createCell((short) 8);


                        cell.setCellValue((String) examResultMap.get("SNO"));
                        cell1.setCellValue((String) examResultMap.get("EmpId"));
                        cell2.setCellValue((String) examResultMap.get("Topic Name"));
                        cell3.setCellValue((Float) examResultMap.get("Percentage"));
                        cell4.setCellValue((Integer) examResultMap.get("Marks"));
                        cell5.setCellValue((Integer) examResultMap.get("Total Questions"));
                        cell6.setCellValue((Integer) examResultMap.get("Attempted Questions"));
                        cell7.setCellValue((String) examResultMap.get("Date Submitted"));
                        cell8.setCellValue((String) examResultMap.get("Exam Status"));

                        String ExamStatus = (String) examResultMap.get("Exam Status");

                        cell.setCellStyle(cs);
                        cell1.setCellStyle(cs1);
                        cell2.setCellStyle(cs);
                        cell3.setCellStyle(cs1);
                        cell4.setCellStyle(cs);
                        cell5.setCellStyle(cs1);
                        cell6.setCellStyle(cs);
                        cell7.setCellStyle(cs1);
                        if (ExamStatus.equalsIgnoreCase("FAIL")) {
                            cell8.setCellStyle(cs2);
                        } else if (ExamStatus.equalsIgnoreCase("PASS")) {
                            cell8.setCellStyle(cs3);
                        } else {
                            cell8.setCellStyle(cs4);
                        }

                    }
                    row = sheet.createRow((short) count++);
                    cell = row.createCell((short) 0);
                    //  HSSFRow row2 = sheet.createRow((short)0);
                    cell1 = row.createCell((short) 1);
                    cell2 = row.createCell((short) 2);
                    cell3 = row.createCell((short) 3);
                    cell4 = row.createCell((short) 4);
                    cell5 = row.createCell((short) 5);
                    cell6 = row.createCell((short) 6);
                    cell7 = row.createCell((short) 7);
                    cell8 = row.createCell((short) 8);

                    cell.setCellValue("");
                    cell1.setCellValue("");
                    cell2.setCellValue("");
                    cell3.setCellValue("Exams Report of");
                    cell4.setCellValue(empName);
                    cell5.setCellValue("from " + startDate);
                    cell6.setCellValue("to " + endDate + "  ");
                    cell7.setCellValue("");
                    cell8.setCellValue("");


                    cell.setCellStyle(footercs1);
                    cell1.setCellStyle(footercs1);
                    cell2.setCellStyle(footercs1);
                    cell3.setCellStyle(footercs);
                    cell4.setCellStyle(footercs);
                    cell5.setCellStyle(footercs);
                    cell6.setCellStyle(footercs);
                    cell7.setCellStyle(footercs1);
                    cell8.setCellStyle(footercs1);
                }
                sheet.autoSizeColumn((short) 0);
                sheet.autoSizeColumn((short) 1);
                sheet.autoSizeColumn((short) 2);
                sheet.autoSizeColumn((short) 3);
                sheet.autoSizeColumn((short) 4);
                sheet.autoSizeColumn((short) 5);
                sheet.autoSizeColumn((short) 6);
                sheet.autoSizeColumn((short) 7);
                sheet.autoSizeColumn((short) 8);
                hssfworkbook.write(fileOut);
                fileOut.flush();
                fileOut.close();
            }

        } catch (FileNotFoundException fne) {
            //   System.out.println("FileNotFoundException-->"+fne.getMessage());
            fne.printStackTrace();
        } catch (IOException ioe) {
            //  System.out.println("IOException-->"+ioe.getMessage());
            ioe.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
            //System.out.println("Exception-->"+ex.getMessage());
            //e.printStackTrace();
        } finally {

            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (preStmt != null) {
                    preStmt.close();
                    preStmt = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (Exception se) {
                se.printStackTrace();
            }
        }
      //  System.out.println("path=" + filePath);
        return filePath;

    }
public String generateProjectXls() throws ServiceLocatorException {

        result = SUCCESS;
        String roleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
        try {
            httpServletRequest.getSession(false).removeAttribute("resultMessage");
            String fileLocation = "";
            setYear(getYear());
            Map rolesMap = (Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_ROLES);
            if (!rolesMap.containsValue("Admin")) {

                setCountry(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.Living_COUNTRY).toString());
            } else if ("-1".equals(getCountry())) {
                setCountry("%");

            }
            fileLocation = generateProjectReport(getStartDate(), getEndDate(), getCountry(), getFlag() ,getDepartmentId() ,getActiveProjectFlag());
            if (!"".equals(fileLocation)) {
                httpServletResponse.setContentType("application/force-download");
                File file = new File(fileLocation);
                Date date = new Date();
                fileName = file.getName();
                if (file.exists()) {
                    inputStream = new FileInputStream(file);
                    outputStream = httpServletResponse.getOutputStream();
                    httpServletResponse.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
                    int noOfBytesRead = 0;
                    byte[] byteArray = null;
                    while (true) {
                        byteArray = new byte[1024];
                        noOfBytesRead = inputStream.read(byteArray);
                        if (noOfBytesRead == -1) {
                            break;
                        }
                        outputStream.write(byteArray, 0, noOfBytesRead);
                    }

                } else {
                    throw new FileNotFoundException("File not found");
                }

                inputStream.close();
                outputStream.close();

            } else {
                setResultMessage("No records exists !!");
                httpServletRequest.getSession(false).setAttribute("resultMessageForProjectSheet", "<font style='color:red;font-size:15px;'>No records exists for between the given dates !!</font>");
                result = INPUT;

            }
        } catch (FileNotFoundException ex) {
            try {
                httpServletResponse.sendRedirect("../general/exception.action?exceptionMessage='No File found'");
            } catch (IOException ex1) {
                System.out.println(ex1);
                // Logger.getLogger(DownloadExcelPayrollReport.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }

 
  private String generateProjectReport(String startDate, String endDate, String country, String Flag, String departmentid, int activeProjectFlag) {
        String filePath = "";
        Connection connection = null;

        CallableStatement callableStatement = null;
        HashMap map = null;

        SimpleDateFormat formatter = new SimpleDateFormat("MM/DD/yyyy");
        //String dateInString = "07/06/2013";
        List finalList = new ArrayList();
        try {
            File file = new File(Properties.getProperty("Emp.ProjectReport.Path"));


            String date2[] = startDate.split("/");

            String date1[] = endDate.split("/");


            if (!file.exists()) {
                file.mkdirs();
            }
            String fileName1 = "";
        /*    if ("1".equals(Flag) && activeProjectFlag == 0) {
                fileName1 = "Total_Project_Report_Sheet_Of_Employees";
            }
            if ("1".equals(Flag) && activeProjectFlag == 1) {
                fileName1 = "Active_Project_Report_Sheet_Of_Employees";
            }
            if ("2".equals(Flag)) {
                fileName1 = "Project_Report_Sheet_Of_Employees_between_" + date2[0] + "_" + date2[1] + "_" + date2[2] + " and " + date1[0] + "_" + date1[1] + "_" + date1[2];
            }
            if ("3".equals(Flag)) {
                fileName1 = "Available_Employee_Report_Sheet";
            }
            if ("4".equals(Flag)) {
                fileName1 = "Available_Employee_Report_Sheet_between_" + date2[0] + "_" + date2[1] + "_" + date2[2] + " and " + date1[0] + "_" + date1[1] + "_" + date1[2];
            }
            if ("5".equals(Flag)) {
                fileName1 = "closed_employees_report_beetween_" + date2[0] + "_" + date2[1] + "_" + date2[2] + " and " + date1[0] + "_" + date1[1] + "_" + date1[2];
            }
            if ("6".equals(Flag)) {
                fileName1 = "Total_Employees_Except_OnProject_Status";
            } */
            
            
             if ("1".equals(Flag) && activeProjectFlag == 0) {
                fileName1 = "Total_Project_Report_Sheet_Of_Employees";
            }
            if ("1".equals(Flag) && activeProjectFlag == 1) {
                fileName1 = "Active_Project_Report_Sheet_Of_Employees";
            }
            if ("2".equals(Flag) && (activeProjectFlag == 1 || activeProjectFlag == 0)) {
                fileName1 = "Project_Report_Sheet_Of_Employees_between_" + date2[0] + "_" + date2[1] + "_" + date2[2] + " and " + date1[0] + "_" + date1[1] + "_" + date1[2];
            }
            if ("3".equals(Flag)) {
                fileName1 = "Available_Employee_Report_Sheet";
            }
            if ("4".equals(Flag)) {
                fileName1 = "Available_Employee_Report_Sheet_between_" + date2[0] + "_" + date2[1] + "_" + date2[2] + " and " + date1[0] + "_" + date1[1] + "_" + date1[2];
            }
            if ("5".equals(Flag)) {
                fileName1 = "closed_employees_report_beetween_" + date2[0] + "_" + date2[1] + "_" + date2[2] + " and " + date1[0] + "_" + date1[1] + "_" + date1[2];
            }
            if ("6".equals(Flag)) {
                fileName1 = "Total_Employees_Except_OnProject_Status";
            }
              if ("1".equals(Flag) && activeProjectFlag == 2) {
                fileName1 = "Active_Project_Report_Of_Employee_Utilization(Max)";
            }
                if ("2".equals(Flag) && activeProjectFlag == 2 ) {
                fileName1 = "Active_Project_Report_Sheet_Of_Employees_Utilization(Max)_between_" + date2[0] + "_" + date2[1] + "_" + date2[2] + " and " + date1[0] + "_" + date1[1] + "_" + date1[2];
                
            }

            FileOutputStream fileOut = new FileOutputStream(Properties.getProperty("Emp.ProjectReport.Path") + "/" + fileName1 + ".xls");

            connection = ConnectionProvider.getInstance().getConnection();

            int j = 1;

            callableStatement = connection.prepareCall("{call spProjectSheetDashboard(?,?,?,?,?,?,?)}");
            callableStatement.setString(1, DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(startDate)));
            callableStatement.setString(2, DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(endDate)));
            callableStatement.setString(3, country);
            callableStatement.setString(4, Flag);
            callableStatement.setString(5, departmentid);
            callableStatement.setInt(6, activeProjectFlag);
            callableStatement.registerOutParameter(7, Types.VARCHAR);

            callableStatement.execute();
            String resultMessage = callableStatement.getString(7);
           // System.out.println("resultMessage " + resultMessage);
            if (resultMessage != null && !(resultMessage.equals(""))) {
                String QuaterlyBasedResults[] = resultMessage.split(Pattern.quote("*@!"));
                for (int i = 0; i < QuaterlyBasedResults.length; i++) {
                    String Q[] = QuaterlyBasedResults[i].split(Pattern.quote("#^$"));
                   // System.out.println("QuaterlyBasedResults["+i+"]"+QuaterlyBasedResults[i]);
                    String DojDate = "";
                    String DoEDate = "";
                    String startDateProjectDate = "";
                    String endDateProjectDate = "";
                    String BillindStartDate1 = "";
                    String BillingEndDate1 = "";

                    String Sno = Q[0];
                    String orgId = Q[1];
                    String EmpId = Q[2];
                    String LName = Q[3];
                    String FName = Q[4];
                    String Doj = Q[5];
                    String Doe = Q[6];
                    String CustomerName = Q[7];
                    String ProjectName = Q[8];
                    String ProjectType = Q[9];
                    String Practice = Q[10];
                    String SubPractice = Q[11];
                    String SecondarySkill = Q[12];
                    String startDateProject = Q[13];
                    String endDateProject = Q[14];
                    String Status = Q[15];
                    String utilization = Q[16];
                    String CostModel = Q[17];
                    String BillindStartDate = Q[18];
                    String BillingEndDate = Q[19];
                    String BillingRemarks = Q[20];
                    String reportsTo = Q[21];
                    String Location = Q[22];
                    String operationalContact = Q[23];
                    String TlOrTm = Q[24];
                    String leavesInMonth = Q[25];
                    String compOrTimeoff = Q[26];
                    String itgBatch = Q[27];
                    // String projectAllow = Q[21];
                    if ("Miracle Software Systems(India), Pvt. Ltd".equals(orgId)) {
                        orgId = "MSS";
                    }
                    if ("Chikiniki Enterprises(India) Pvt. Ltd".equals(orgId)) {
                        orgId = "CNE";
                    }
//
//                    System.out.println("startDateProject " + startDateProject);
//                    System.out.println("endDateProject " + endDateProject);

                    if (!Doj.equals("-")) {
                        DojDate = DateUtility.getInstance().convertToviewFormat(Doj);
                    } else {
                        DojDate = "-";
                    }

                    if (!Doe.equals("-")) {
                        DoEDate = DateUtility.getInstance().convertToviewFormat(Doe);
                    } else {
                        DoEDate = "-";
                    }

                    if (!startDateProject.equals("-")) {
                        String tempDate = null;
                        String startDates[] = startDateProject.split("\\n");
                        if (!startDates[0].equals("-")) {
                            startDateProjectDate = DateUtility.getInstance().convertToviewFormat(startDates[0]);
                        } else {
                            startDateProjectDate = startDates[0];
                        }
                        // System.out.println("startDates array "+startDates);
                        for (int k = 1; startDates.length > k; k++) {
                            //   System.out.println("startDates array "+startDates[k]);
                            String startDateProjects = startDates[k];
                            if (!startDateProjects.equals("")) {
                                tempDate = DateUtility.getInstance().convertToviewFormat(startDateProjects);
                                startDateProjectDate = startDateProjectDate + "\n" + tempDate;
                            }

                        }
                    } else {
                        startDateProjectDate = "-";
                    }
                    //  System.out.println("startDateProjectDate " + startDateProjectDate);
                    if (!endDateProject.equals("-")) {
                        String tempDate = null;
                        String endDates[] = endDateProject.split("\\n");

                        if (!endDates[0].equals("-")) {
                            endDateProjectDate = DateUtility.getInstance().convertToviewFormat(endDates[0]);
                        } else {
                            endDateProjectDate = endDates[0];
                        }

                        for (int k = 1; endDates.length > k; k++) {
                            // System.out.println("startDates array "+endDates[k]);
                           String  endDateProjects = endDates[k];
                            if (endDateProjects.equals("-")) {
                                endDateProjectDate = endDateProjectDate + "\n" + endDateProjects;
                            }
                            else if (!endDateProjects.equals(" ")) {
                                tempDate = DateUtility.getInstance().convertToviewFormat(endDateProjects);
                                endDateProjectDate = endDateProjectDate + "\n" + tempDate;
                            } 
                        }
                        //  endDateProjectDate=DateUtility.getInstance().convertToviewFormat(endDateProject);
                    } else {
                        endDateProjectDate = "-";
                    }
                  /*  if (!CustomerName.equals("-")) {
                        CustomerName = removeLastChar(CustomerName);
                    }
                    if (!ProjectName.equals("-")) {
                        ProjectName = removeLastChar(ProjectName);
                    }
                    if (!ProjectType.equals("-")) {
                        ProjectType = removeLastChar(ProjectType);
                    }
                    if (!CostModel.equals("-")) {
                        CostModel = removeLastChar(CostModel);
                    } */
                    
                    if ((!"1".equals(Flag) && activeProjectFlag != 2) && (!"2".equals(Flag) && activeProjectFlag != 2)) {
                    if (!CustomerName.equals("-")) {
                        CustomerName = removeLastChar(CustomerName);
                    }
                    if (!ProjectName.equals("-")) {
                        ProjectName = removeLastChar(ProjectName);
                    }
                    if (!ProjectType.equals("-")) {
                        ProjectType = removeLastChar(ProjectType);
                    }
                    if (!CostModel.equals("-")) {
                        CostModel = removeLastChar(CostModel);
                    }
                     }
	

                    // System.out.println("endDateProjectDate " + endDateProjectDate);
                    if (!BillindStartDate.equals("-")) {
                        BillindStartDate1 = DateUtility.getInstance().convertToviewFormat(BillindStartDate);
                    } else {
                        BillindStartDate1 = "-";
                    }

                    if (!BillingEndDate.equals("-")) {
                        BillingEndDate1 = DateUtility.getInstance().convertToviewFormat(BillingEndDate);
                    } else {
                        BillingEndDate1 = "-";
                    }


                    map = new HashMap();

                    map.put("Sno", Sno);
                    map.put("orgId", orgId);
                    map.put("EmpId", EmpId);
                    map.put("LName", LName);
                    map.put("FName", FName);
                    map.put("Doj", DojDate);
                    map.put("Doe", DoEDate);
                    map.put("CustomerName", CustomerName);
                    map.put("ProjectName", ProjectName);
                    map.put("ProjectType", ProjectType);
                    map.put("Practice", Practice);
                    map.put("SubPractice", SubPractice);
                    map.put("SecondarySkill", SecondarySkill);
                    map.put("startDateProject", startDateProjectDate);
                    map.put("endDateProject", endDateProjectDate);
                    map.put("Status", Status);
                    map.put("utilization", utilization);
                    map.put("CostModel", CostModel);
                    map.put("BillindStartDate", BillindStartDate1);
                    map.put("BillingEndDate", BillingEndDate1);
                    map.put("BillingRemarks", BillingRemarks);
                    map.put("reportsTo", reportsTo);
                    map.put("Location", Location);
                    map.put("operationalContact", operationalContact);
                    map.put("TlOrTm", TlOrTm);
                    map.put("leavesInMonth", leavesInMonth);
                    map.put("compOrTimeoff", compOrTimeoff);
                    map.put("itgBatch", itgBatch);


                    //   map.put("projectAllow", projectAllow);

                    finalList.add(map);
                    j++;
                }
            }

            if (finalList.size() > 0) {
                filePath = file.getAbsolutePath() + Properties.getProperty("OS.Compatabliliy.Download") + fileName1 + ".xls";
                HSSFRow row1;
                HSSFWorkbook workbook = new HSSFWorkbook();
                HSSFSheet worksheet = workbook.createSheet(fileName1);
                for (int i = 0; i < 27; i++) {
                    worksheet.setColumnWidth(i, 10 * 256);
                }
                HSSFFont timesBoldFont = workbook.createFont();
                timesBoldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                timesBoldFont.setColor(HSSFColor.WHITE.index);

                timesBoldFont.setFontName("Calibri");
                HSSFCellStyle headercs = workbook.createCellStyle();
                headercs.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                headercs.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                headercs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                headercs.setBorderTop((short) 1); // single line border
                headercs.setBorderBottom((short) 1); // single line border
                headercs.setFont(timesBoldFont);

                HSSFFont footerFont = workbook.createFont();
                footerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                footerFont.setFontName("Calibri");

                HSSFFont font4 = workbook.createFont();
                font4.setColor(HSSFColor.WHITE.index);
                font4.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                font4.setFontHeightInPoints((short) 14);
                font4.setFontName("Calibri");

                HSSFCellStyle cellStyle = workbook.createCellStyle();
                cellStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
                cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellStyle.setFont(font4);

                HSSFFont font1 = workbook.createFont();
                font1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                font1.setFontHeightInPoints((short) 14);
                font1.setFontName("Calibri");

                HSSFCellStyle cs = workbook.createCellStyle();
                cs.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
                cs.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cs.setBorderTop((short) 1); // single line border
                cs.setBorderBottom((short) 1); // single line border
                cs.setFont(font1);

                HSSFCellStyle cs1 = workbook.createCellStyle();
                cs1.setFillForegroundColor(HSSFColor.ROYAL_BLUE.index);
                cs1.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                cs1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cs1.setFont(font4);
                cs1.setBorderTop((short) 1); // single line border
                cs1.setBorderBottom((short) 1); // single line border

                HSSFCellStyle cs2 = workbook.createCellStyle();
                cs2.setFillForegroundColor(HSSFColor.WHITE.index);
                cs2.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                cs2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cs2.setBorderTop((short) 1); // single line border
                cs2.setBorderBottom((short) 1); // single line border
                cs2.setFont(font1);

                HSSFFont font3 = workbook.createFont();
                font3.setFontHeightInPoints((short) 14);
                font3.setFontName("Calibri");

                HSSFCellStyle cs3 = workbook.createCellStyle();
                cs3.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
                cs3.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                cs3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cs3.setFont(font3);
                cs3.setBorderTop((short) 1); // single line border 
                cs3.setBorderBottom((short) 1); // single line border

                HSSFCellStyle cellStyle1 = workbook.createCellStyle();
                cellStyle1.setFillForegroundColor(HSSFColor.ROYAL_BLUE.index);
                cellStyle1.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                cellStyle1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellStyle1.setBorderTop((short) 1); // single line border
                cellStyle1.setBorderBottom((short) 1); // single line border
                cellStyle1.setFont(font4);

                HSSFCellStyle cs4 = workbook.createCellStyle();
                cs4.setFillForegroundColor(HSSFColor.WHITE.index);
                cs4.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                cs4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cs4.setBorderTop((short) 1); // single line border
                cs4.setBorderBottom((short) 1); // single line border
                cs4.setFont(font3);



                //start	
                SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
                Date today = new Date();
                String date = DATE_FORMAT.format(today);
                row1 = worksheet.createRow((short) 0);

                HSSFCell cellpo0 = row1.createCell((short) 0);
                HSSFCell cellpo1 = row1.createCell((short) 1);
                HSSFCell cellpo2 = row1.createCell((short) 2);
                HSSFCell cellpo3 = row1.createCell((short) 3);
                HSSFCell cellpo4 = row1.createCell((short) 4);
                HSSFCell cellpo5 = row1.createCell((short) 5);
                HSSFCell cellpo6 = row1.createCell((short) 6);
                HSSFCell cellpo7 = row1.createCell((short) 7);
                HSSFCell cellpo8 = row1.createCell((short) 8);
                HSSFCell cellpo9 = row1.createCell((short) 9);
                HSSFCell cellpo10 = row1.createCell((short) 10);
                HSSFCell cellpo11 = row1.createCell((short) 11);

                row1 = worksheet.createRow((short) 0);
                Cell cell[] = new Cell[28];
                for (int i = 0; i < 28; i++) {
                    cell[i] = row1.createCell((short) i);
                }



                 cell[0].setCellValue(fileName1);
                HSSFCellStyle cellStyleHead = workbook.createCellStyle();
                cellStyleHead.setFont(timesBoldFont);
                cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                cellStyle.setFillBackgroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cell[0].setCellStyle(cellStyle);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("A1:AB1"));

                worksheet.addMergedRegion(CellRangeAddress.valueOf("A1:AA1"));
                row1 = worksheet.createRow((short) 1);

                cell[0] = row1.createCell((short) 0);
                cell[0].setCellValue("Sno");
                cellStyleHead.setFont(timesBoldFont);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("A2:A3"));

                cell[0] = row1.createCell((short) 1);
                cell[0].setCellValue("MSS/CNE");
                cellStyleHead.setFont(timesBoldFont);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("B2:B3"));

                cell[0] = row1.createCell((short) 2);
                cell[0].setCellValue("EmpId");
                cellStyleHead.setFont(timesBoldFont);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("C2:C3"));

                cell[0] = row1.createCell((short) 3);
                cell[0].setCellValue("LName");
                cellStyleHead.setFont(timesBoldFont);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("D2:D3"));

                cell[0] = row1.createCell((short) 4);
                cell[0].setCellValue("FName");
                cellStyleHead.setFont(timesBoldFont);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("E2:E3"));


                 cell[0] = row1.createCell((short) 5);
                cell[0].setCellValue("ITG Batch");
                cellStyleHead.setFont(timesBoldFont);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("F2:F3"));
                
                
                CreationHelper createHelper = workbook.getCreationHelper();
                cellStyle1.setDataFormat(
                        createHelper.createDataFormat().getFormat("m/d/yy"));
                cell[1] = row1.createCell((short) 6);
                cell[1].setCellValue("DoJ");
                cell[1].setCellStyle(cellStyle1);
                cellStyleHead.setFont(timesBoldFont);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                // cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("G2:G3"));


                cell[1] = row1.createCell((short) 7);
                cell[1].setCellValue("DoE");
                //   cell[0].setCellValue("DoE");
                cellStyleHead.setFont(timesBoldFont);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                cell[1].setCellStyle(cellStyle1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("H2:H3"));

                cell[0] = row1.createCell((short) 8);
                cell[0].setCellValue("Customer Name");
                cellStyleHead.setFont(timesBoldFont);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("I2:I3"));

                cell[0] = row1.createCell((short) 9);
                cell[0].setCellValue("Project Name");
                cellStyleHead.setFont(timesBoldFont);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("J2:J3"));

                cell[0] = row1.createCell((short) 10);
                cell[0].setCellValue("Project Type");
                cellStyleHead.setFont(timesBoldFont);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("K2:K3"));

                cell[0] = row1.createCell((short) 11);
                cell[0].setCellValue("Practice");
                cellStyleHead.setFont(timesBoldFont);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("L2:L3"));

                cell[0] = row1.createCell((short) 12);
                cell[0].setCellValue("Sub-Practice");
                cellStyleHead.setFont(timesBoldFont);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("M2:M3"));

                cell[0] = row1.createCell((short) 13);
                cell[0].setCellValue("Secondary Skills");
                cellStyleHead.setFont(timesBoldFont);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("N2:N3"));

                cell[1] = row1.createCell((short) 14);
                cell[1].setCellValue("Start Date");
                cellStyleHead.setFont(timesBoldFont);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                cell[1].setCellStyle(cellStyle1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("O2:O3"));

                cell[1] = row1.createCell((short) 15);
                cell[1].setCellValue("End Date");
                cellStyleHead.setFont(timesBoldFont);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                cell[1].setCellStyle(cellStyle1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("P2:P3"));

                cell[0] = row1.createCell((short) 16);
                cell[0].setCellValue("Current Status");
                cellStyleHead.setFont(timesBoldFont);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                //   cell[0].setCellStyle(cellStyleHead);
                cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("Q2:Q3"));

                cell[0] = row1.createCell((short) 17);
                cell[0].setCellValue("% Utilization");
                cellStyleHead.setFont(timesBoldFont);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("R2:R3"));

                cell[0] = row1.createCell((short) 18);
                cell[0].setCellValue("Cost Model");
                cellStyleHead.setFont(timesBoldFont);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                //   cell[0].setCellStyle(cellStyleHead);
                cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("S2:S3"));

                cell[1] = row1.createCell((short) 19);
                cell[1].setCellValue("Billing Start Date");
                cellStyleHead.setFont(timesBoldFont);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                cell[1].setCellStyle(cellStyle1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("T2:T3"));

                cell[1] = row1.createCell((short) 20);
                cell[1].setCellValue("Billing End Date");
                cellStyleHead.setFont(timesBoldFont);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                cell[1].setCellStyle(cellStyle1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("U2:U3"));

                cell[0] = row1.createCell((short) 21);
                cell[0].setCellValue("Billing Remarks");
                cellStyleHead.setFont(timesBoldFont);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("V2:V3"));

                cell[0] = row1.createCell((short) 22);
                cell[0].setCellValue("Reporting Person");
                cellStyleHead.setFont(timesBoldFont);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("W2:W3"));

                cell[0] = row1.createCell((short) 23);
                cell[0].setCellValue("Current Location");
                cellStyleHead.setFont(timesBoldFont);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("X2:X3"));
                cell[0] = row1.createCell((short) 24);
                cell[0].setCellValue("Hr Contact");
                cellStyleHead.setFont(timesBoldFont);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("Y2:Y3"));

                cell[0] = row1.createCell((short) 25);
                cell[0].setCellValue("TL/TM");
                cellStyleHead.setFont(timesBoldFont);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("Z2:Z3"));

                cell[0] = row1.createCell((short) 26);
                cell[0].setCellValue("Leaves in month");
                cellStyleHead.setFont(timesBoldFont);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("AA2:AA3"));

                cell[0] = row1.createCell((short) 27);
                cell[0].setCellValue("ot/comp time");
                cellStyleHead.setFont(timesBoldFont);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("AB2:AB3"));


                int count = 3;
                if (finalList.size() > 0) {
                    Map salesClousersMap = null;
                    for (int i = 0; i < finalList.size(); i++) {
                        salesClousersMap = (Map) finalList.get(i);
                        row1 = worksheet.createRow((short) count++);
                        for (int c = 0; c < 28; c++) {
                            cell[c] = row1.createCell((short) c);
                        }
                        int indexVal=0;
                        cell[indexVal++].setCellValue((String) salesClousersMap.get("Sno"));
                        cell[indexVal++].setCellValue((String) salesClousersMap.get("orgId"));
                        cell[indexVal++].setCellValue((String) salesClousersMap.get("EmpId"));
                        cell[indexVal++].setCellValue((String) salesClousersMap.get("LName"));
                        cell[indexVal++].setCellValue((String) salesClousersMap.get("FName"));
                        
                        cell[indexVal++].setCellValue((String) salesClousersMap.get("itgBatch"));
                        cell[indexVal++].setCellValue((String) salesClousersMap.get("Doj"));

                        cell[indexVal++].setCellValue((String) salesClousersMap.get("Doe"));
                        cell[indexVal++].setCellValue((String) salesClousersMap.get("CustomerName"));
                        cell[indexVal++].setCellValue((String) salesClousersMap.get("ProjectName"));

                        cell[indexVal++].setCellValue((String) salesClousersMap.get("ProjectType"));
                        cell[indexVal++].setCellValue((String) salesClousersMap.get("Practice"));
                        cell[indexVal++].setCellValue((String) salesClousersMap.get("SubPractice"));
                        cell[indexVal++].setCellValue((String) salesClousersMap.get("SecondarySkill"));

                        cell[indexVal++].setCellValue((String) salesClousersMap.get("startDateProject"));
                        cell[indexVal++].setCellValue((String) salesClousersMap.get("endDateProject"));
                        cell[indexVal++].setCellValue((String) salesClousersMap.get("Status"));
                        cell[indexVal++].setCellValue((String) salesClousersMap.get("utilization"));

                        cell[indexVal++].setCellValue((String) salesClousersMap.get("CostModel"));
                        cell[indexVal++].setCellValue((String) salesClousersMap.get("BillindStartDate"));
                        cell[indexVal++].setCellValue((String) salesClousersMap.get("BillingEndDate"));
                        cell[indexVal++].setCellValue((String) salesClousersMap.get("BillingRemarks"));

                        cell[indexVal++].setCellValue((String) salesClousersMap.get("reportsTo"));
                        cell[indexVal++].setCellValue((String) salesClousersMap.get("Location"));
                        cell[indexVal++].setCellValue((String) salesClousersMap.get("operationalContact"));
                        cell[indexVal++].setCellValue((String) salesClousersMap.get("TlOrTm"));

                        cell[indexVal++].setCellValue((String) salesClousersMap.get("leavesInMonth"));
                        cell[indexVal++].setCellValue((String) salesClousersMap.get("compOrTimeoff"));
                        //     cell[27].setCellValue((String) salesClousersMap.get("reportsTo"));

                        //       cell[21].setCellValue((String) salesClousersMap.get("projectAllow"));
                        for (int h = 0; h < 28; h++) {
                            if (count % 2 == 0) {
                                cell[h].setCellStyle(cs3);
                            } else {
                                cell[h].setCellStyle(cs4);
                            }
                        }
                    }
                    row1 = worksheet.createRow((short) count++);
                    for (int y = 0; y < 28; y++) {
                        cell[y] = row1.createCell((short) y);
                    }

                    for (int f = 0; f < 28; f++) {
                        cell[f].setCellStyle(cs1);
                        cs.setWrapText(true);
                    }

                }

                worksheet.autoSizeColumn((short) 2);
                worksheet.autoSizeColumn((short) 5);
                worksheet.setColumnWidth(0, 15 * 256);
                worksheet.setColumnWidth(1, 45 * 256);
                worksheet.setColumnWidth(2, 20 * 256);
                worksheet.setColumnWidth(3, 25 * 256);
                worksheet.setColumnWidth(4, 25 * 256);
                worksheet.setColumnWidth(5, 25 * 256);
                worksheet.setColumnWidth(6, 25 * 256);
                worksheet.setColumnWidth(7, 70 * 256);
                for (int i = 6; i < 28; i++) {
                    worksheet.setColumnWidth(i, 35 * 256);
                }
                workbook.write(fileOut);
                fileOut.flush();
                fileOut.close();
            }
        } catch (FileNotFoundException fne) {
            fne.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
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
            } catch (Exception se) {
                se.printStackTrace();
            }
        }

        return filePath;
    }


public String generatePFPortalReport() throws ServiceLocatorException {
             //System.out.println("enter into the action");
        resultType = INPUT;
        OutputStream outputStream = null;
        InputStream inputStream = null;
        
                if (AuthorizationManager.getInstance().isAuthorizedUser("OPS_DASHBOARD_ACCESS", userRoleId)) {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            //userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
           // String responseString = "";
            try {
                String fileLocation = "";
               
             
                fileLocation = ServiceLocator.getReportsService().generatePFPortalReport(getOpsContactId(),getDocType());
                //System.out.println("the file location"+fileLocation);
                httpServletResponse.setContentType("application/force-download");
Date date = new Date();
                if (!"".equals(fileLocation)) {
                    File file = new File(fileLocation);

                    String fileName = "";
                    
                   // fileName = file.getName();
                     fileName =(date.getYear()+1900)+"_"+(date.getMonth()+1)+"_"+date.getDate()+"_"+file.getName();
                    if (file.exists()) {
                        inputStream = new FileInputStream(file);
                        outputStream = httpServletResponse.getOutputStream();
                        httpServletResponse.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
                        int noOfBytesRead = 0;
                        byte[] byteArray = null;
                        while (true) {
                            byteArray = new byte[1024];
                            noOfBytesRead = inputStream.read(byteArray);
                            if (noOfBytesRead == -1) {
                                break;
                            }
                            outputStream.write(byteArray, 0, noOfBytesRead);
                        }

                    } else {
                        throw new FileNotFoundException("File not found");
                    }
                } else {

                    resultMessage = "<font color=\"red\" size=\"1.5\">Sorry! No records for this Employee!</font>";
                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, resultMessage);

                }


            } catch (FileNotFoundException ex) {
                try {
                    httpServletResponse.sendRedirect("../general/exception.action?exceptionMessage='No File found'");
                } catch (IOException ex1) {
                    // Logger.getLogger(MarketingAction.class.getName()).log(Level.SEVERE, null, ex1);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                try {
                   
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (outputStream != null) {
                        outputStream.close();
                    }

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }




        }
                }//Close Session Checking
        resultType = SUCCESS;
             System.out.println("the result type "+resultType);
        return resultType;
    }
	 public String removeLastChar(String str) {
        return str.substring(0, str.length() - 1);
    }
	
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }
    
    public String getOrganization() {
        return organization;
    }
    
    public void setOrganization(String organization) {
        this.organization = organization;
    }
    
    public String getEmpState() {
        return empState;
    }
    
    public void setEmpState(String empState) {
        this.empState = empState;
    }
    
    public List getEmpCurrentStatus() {
        return empCurrentStatus;
    }
    
    public void setEmpCurrentStatus(List empCurrentStatus) {
        this.empCurrentStatus = empCurrentStatus;
    }
    
    public List getOrganizationList() {
        return organizationList;
    }
    
    public void setOrganizationList(List organizationList) {
        this.organizationList = organizationList;
    }
    
    public List getEmpReportTypeList() {
        return empReportTypeList;
    }
    
    public void setEmpReportTypeList(List empReportTypeList) {
        this.empReportTypeList = empReportTypeList;
    }
    
    public String getEmpReportType() {
        return empReportType;
    }
    
    public void setEmpReportType(String empReportType) {
        this.empReportType = empReportType;
    }
    
    public String getLoginUserId() {
        return loginUserId;
    }
    
    public void setLoginUserId(String loginUserId) {
        this.loginUserId = loginUserId;
    }
    
    public String getReportName() {
        return reportName;
    }
    
    public void setReportName(String reportName) {
        this.reportName = reportName;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    
    public Timestamp getCreatedDate() {
        return createdDate;
    }
    
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getSubmitFrom() {
        return submitFrom;
    }
    
    public void setSubmitFrom(String submitFrom) {
        this.submitFrom = submitFrom;
    }
    
    public List getCountryList() {
        return countryList;
    }
    
    public void setCountryList(List countryList) {
        this.countryList = countryList;
    }
    
    /**
     *setters and getters for the start date and enddate
     */
    
    public String getStartdate() {
        return startdate;
    }
    
    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }
     public String getEnddate() {
        return enddate;
    }
    
    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }
    
    
    public boolean acceptableParameterName(String inputParamName) {
        boolean isParamAcceptable = true;
        if(inputParamName.equals("txtCurr") ||
                inputParamName.equals("txtSortCol") ||
                inputParamName.equals("txtSortAsc")) {
            isParamAcceptable = false;
        }
        
        return isParamAcceptable;
    }

    /**
     * @return the departmentId
     */
    public String getDepartmentId() {
        return departmentId;
    }

    /**
     * @param departmentId the departmentId to set
     */
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * @return the month
     */
    public int getMonth() {
        return month;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * @return the year
     */
    public String getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * @return the teamIdList
     */
    public List getTeamIdList() {
        return teamIdList;
    }

    /**
     * @param teamIdList the teamIdList to set
     */
    public void setTeamIdList(List teamIdList) {
        this.teamIdList = teamIdList;
    }

    /**
     * @return the practiceIdList
     */
    public List getPracticeIdList() {
        return practiceIdList;
    }

    /**
     * @param practiceIdList the practiceIdList to set
     */
    public void setPracticeIdList(List practiceIdList) {
        this.practiceIdList = practiceIdList;
    }

    /**
     * @return the subPractice
     */
    public String getSubPractice() {
        return subPractice;
    }

    /**
     * @param subPractice the subPractice to set
     */
    public void setSubPractice(String subPractice) {
        this.subPractice = subPractice;
    }

    /**
     * @return the practiceId
     */
    public String getPracticeId() {
        return practiceId;
    }

    /**
     * @param practiceId the practiceId to set
     */
    public void setPracticeId(String practiceId) {
        this.practiceId = practiceId;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the departmentIdList
     */
    public List getDepartmentIdList() {
        return departmentIdList;
    }

    /**
     * @param departmentIdList the departmentIdList to set
     */
    public void setDepartmentIdList(List departmentIdList) {
        this.departmentIdList = departmentIdList;
    }

    /**
     * @return the empnamesList
     */
    public Map getEmpnamesList() {
        return empnamesList;
    }

    /**
     * @param empnamesList the empnamesList to set
     */
    public void setEmpnamesList(Map empnamesList) {
        this.empnamesList = empnamesList;
    }

    /**
     * @return the resultMessage
     */
    public String getResultMessage() {
        return resultMessage;
    }

    /**
     * @param resultMessage the resultMessage to set
     */
    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    /**
     * @return the file
     */
    public String getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(String file) {
        this.file = file;
    }

    /**
     * @return the empnameById
     */
    public String getEmpnameById() {
        return empnameById;
    }

    /**
     * @param empnameById the empnameById to set
     */
    public void setEmpnameById(String empnameById) {
        this.empnameById = empnameById;
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the result
     */
    public String getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(String result) {
        this.result = result;
    }
    
     public boolean getIsClouserFlag() {
        return isClouserFlag;
    }

    public void setIsClouserFlag(boolean isClouserFlag) {
        this.isClouserFlag = isClouserFlag;
    }
    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {

        this.httpServletResponse = httpServletResponse;

    }

    public String getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(String consultantId) {
        this.consultantId = consultantId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public int getEmpType() {
        return empType;
    }

    public void setEmpType(int empType) {
        this.empType = empType;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    public void setFileInputStream(InputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getExamResultFalg() {
        return examResultFalg;
    }

    public void setExamResultFalg(int examResultFalg) {
        this.examResultFalg = examResultFalg;
    }

    /**
     * @return the employeeService
     */
    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    /**
     * @param employeeService the employeeService to set
     */
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * @return the reportsToIdMap
     */
    public Map getReportsToIdMap() {
        return reportsToIdMap;
    }

    /**
     * @param reportsToIdMap the reportsToIdMap to set
     */
    public void setReportsToIdMap(Map reportsToIdMap) {
        this.reportsToIdMap = reportsToIdMap;
    }

    /**
     * @return the opsContactIdMap
     */
    public Map getOpsContactIdMap() {
        return opsContactIdMap;
    }

    /**
     * @param opsContactIdMap the opsContactIdMap to set
     */
    public void setOpsContactIdMap(Map opsContactIdMap) {
        this.opsContactIdMap = opsContactIdMap;
    }

    /**
     * @return the salesLeadMap
     */
    public Map getSalesLeadMap() {
        return salesLeadMap;
    }

    /**
     * @param salesLeadMap the salesLeadMap to set
     */
    public void setSalesLeadMap(Map salesLeadMap) {
        this.salesLeadMap = salesLeadMap;
    }

    /**
     * @return the recmemebersMap
     */
    public Map getRecmemebersMap() {
        return recmemebersMap;
    }

    /**
     * @param recmemebersMap the recmemebersMap to set
     */
    public void setRecmemebersMap(Map recmemebersMap) {
        this.recmemebersMap = recmemebersMap;
    }

    /**
     * @return the stateList
     */
    public List getStateList() {
        return stateList;
    }

    /**
     * @param stateList the stateList to set
     */
    public void setStateList(List stateList) {
        this.stateList = stateList;
    }

    /**
     * @return the isEmpFlag
     */
    public boolean getIsEmpFlag() {
        return isEmpFlag;
    }

    /**
     * @param isEmpFlag the isEmpFlag to set
     */
    public void setIsEmpFlag(boolean isEmpFlag) {
        this.isEmpFlag = isEmpFlag;
    }

    /**
     * @return the opsContactId
     */
    public int getOpsContactId() {
        return opsContactId;
    }

    /**
     * @param opsContactId the opsContactId to set
     */
    public void setOpsContactId(int opsContactId) {
        this.opsContactId = opsContactId;
    }

    /**
     * @return the docType
     */
    public String getDocType() {
        return docType;
    }

    /**
     * @param docType the docType to set
     */
    public void setDocType(String docType) {
        this.docType = docType;
    }

    /**
     * @return the myProjects
     */
    public Map getMyProjects() {
        return myProjects;
    }

    /**
     * @param myProjects the myProjects to set
     */
    public void setMyProjects(Map myProjects) {
        this.myProjects = myProjects;
    }

    /**
     * @return the flag
     */
    public String getFlag() {
        return flag;
    }

    /**
     * @param flag the flag to set
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }

    /**
     * @return the isAdminFlag
     */
    public String getIsAdminFlag() {
        return isAdminFlag;
    }

    /**
     * @param isAdminFlag the isAdminFlag to set
     */
    public void setIsAdminFlag(String isAdminFlag) {
        this.isAdminFlag = isAdminFlag;
    }

    /**
     * @return the locationsMap
     */
    public Map getLocationsMap() {
        return locationsMap;
    }

    /**
     * @param locationsMap the locationsMap to set
     */
    public void setLocationsMap(Map locationsMap) {
        this.locationsMap = locationsMap;
    }

    /**
     * @return the activeProjectFlag
     */
    public int getActiveProjectFlag() {
        return activeProjectFlag;
    }

    /**
     * @param activeProjectFlag the activeProjectFlag to set
     */
    public void setActiveProjectFlag(int activeProjectFlag) {
        this.activeProjectFlag = activeProjectFlag;
    }

    /**
     * @return the subPracticeList
     */
    public List getSubPracticeList() {
        return subPracticeList;
    }

    /**
     * @param subPracticeList the subPracticeList to set
     */
    public void setSubPracticeList(List subPracticeList) {
        this.subPracticeList = subPracticeList;
    }

    /**
     * @return the domainId
     */
    public int getDomainId() {
        return domainId;
    }

    /**
     * @param domainId the domainId to set
     */
    public void setDomainId(int domainId) {
        this.domainId = domainId;
    }

    /**
     * @return the topicId
     */
    public int getTopicId() {
        return topicId;
    }

    /**
     * @param topicId the topicId to set
     */
    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

}
