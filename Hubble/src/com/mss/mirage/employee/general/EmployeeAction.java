/*
 * @(#)EmployeeAction.java 1.0 November 01, 2007
 *
 * Copyright 2006 Miracle Software Systems(INDIA) Pvt Ltd. All rights reserved.
 *
 *
 *******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package : com.mss.mirage.employee.genaralgetEmployee
 *
 * Date    :  September 24, 2007, 10:18 PM
 *
 * Author  : Praveen Kumar Ralla<pralla@miraclesoft.com>
 *
 * File    : EmployeeAction.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
package com.mss.mirage.employee.general;

import com.mss.mirage.employee.general.StateVTO;
import com.mss.mirage.employee.profile.EmpProfileVTO;
import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.DefaultDataProvider;
import com.mss.mirage.util.HibernateDataProvider;
import com.mss.mirage.util.AuthorizationManager;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.MailManager;
import com.mss.mirage.util.ServiceLocator;
import com.opensymphony.xwork2.ActionSupport;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.sql.Timestamp;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;

//new
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.FileUploadUtility;
import com.mss.mirage.util.Properties;
import com.mss.mirage.util.ServiceLocator;
import com.mss.mirage.util.ServiceLocatorException;
import com.opensymphony.xwork2.ActionSupport;
import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import org.apache.commons.io.FileUtils;

/**
 * The
 * <code>EmployeeAction</code> class is used for getting adding new Activity
 * from <i>ActivityAdd.jsp</i> page. <p> Then it invokes setter methods in
 * <code>EmployeeAction</code> class and invokes execute() method in
 * <code>EmployeeAction</code> for inserting activity details in
 * MIRAGE.tblCrmActivity table.
 *
 * @author Praveen Kumar Ralla<a
 * href="mailto:pralla@miraclesoft.com">pralla@miraclesoft.com</a>
 *
 * @version 1.0 November 01, 2007
 *
 * @see com.mss.mirage.util.ServiceLocator
 * @see com.mss.mirage.employee.general.EmployeeVTO
 * @see com.mss.mirage.util.ApplicationDataProvider
 *
 * @since 1.0
 */
public class EmployeeAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

    /**
     * The empId is used for storing employee id.
     */
    private int empId;
    /**
     * The resultType is used for storing type of result.
     */
    private String resultType;
    /**
     * The queryString is used for storing sqlQuery String.
     */
    private StringBuffer queryStringBuffer;
    private String queryString;
    private String deleteAction;
    private String loginId;
    private String previousEmpState;
    private Collection currentStateHistoryCollection;
    /**
     * The resultMessage is used for storing resultMessage.
     */
    private String resultMessage;
    /**
     * The httpServletRequest is used for storing httpServletRequest Object.
     */
    private HttpServletRequest httpServletRequest;
    /**
     * The hibernateDataProvider is used for storing reference of
     * ApplicationDataProvider methods.
     */
    private HibernateDataProvider hibernateDataProvider;
    private DefaultDataProvider defaultDataProvider;
    private DataSourceDataProvider dataSourceDataProvider;
    /**
     * The currentEmployee is used for storing currentEmployee Details.
     */
    private EmployeeVTO currentEmployee;
    /**
     * The employeeService is used for accessing methods of EmployeeService.
     */
    private EmployeeService employeeService;
    /**
     * The id is used for storing id of employee.
     */
    private int id;
    /**
     * The firstName is used for storing firstName of employee.
     */
    private String firstName;
    /**
     * The lastName is used for storing lastName of employee.
     */
    private String lastName;
    /**
     * The middleName is used for storing middleName of employee.
     */
    private String middleName;
    /**
     * The aliasName is used for storing aliasName of employee.
     */
    private String aliasName;
    /**
     * The gender is used for storing gender of employee.
     */
    private String gender;
    /**
     * The maritalStatus is used for storing maritalStatus of employee.
     */
    private String maritalStatus;
    /**
     * The country is used for storing contry of employee.
     */
    private String country;
    /**
     * The ssn is used for storing ssn of employee.
     */
    private String ssn;
    private String empno;
    private String nsrno;
    /**
     * The currStatus is used for storing currentStatus of employee.
     */
    private String currStatus = ApplicationConstants.DEFAULT_EMP_STATUS;
    /**
     * The preCurrStatus is used for storing previous current Status of
     * employee.
     */
    private String preCurrStatus;
    /**
     * The empTypeId is used for storing employeeTypeId of employee.
     */
    private String empTypeId;
    /**
     * The orgId is used for storing OraganizationId of employee.
     */
    private String orgId = ApplicationConstants.DEFAULT_ORG;
    /**
     * The opsContactId is used for storing ContactId
     */
    private String opsContactId;
    /**
     * The teamId is used for storing teamId.
     */
    private String teamId;
    /**
     * The practiceId is used for storing PracticeId.
     */
    private String practiceId;
    private String subPractice;
    //private int reportsToId;
    /**
     * The titleId is used for storing titleId.
     */
    private String titleId;
    /**
     * The industryId is used for storing industryId
     */
    private String industryId;
    /**
     * The departmentId is used for storing DepartmentId.
     */
    private String departmentId;
    /**
     * The birthDate is used for storing dateOfBirth of employee.
     */
    private Date birthDate;
    /**
     * The offBirthDate is used for storing official date of birth of employee.
     */
    private Date offBirthDate;
    /**
     * The hireDate is used for storing hiredate of employee.
     */
    private Date hireDate;
    /**
     * The anniversaryDate is used for storing anniversaryDate of employee.
     */
    private Date anniversaryDate;
    /**
     * The workPhoneNo is used for storing wormPhoneNo of employee.
     */
    private String workPhoneNo;
    /**
     * The altPhoneNo is used for storing alternatePhoneNo of employee.
     */
    private String altPhoneNo;
    /**
     * The homePhoneNo is used for storing homePhoneNo of employee.
     */
    private String homePhoneNo;
    /**
     * The cellPhoneNo is used for storing cellPhoneNo of employee.
     */
    private String cellPhoneNo;
    /**
     * The officeEmail is used for storing officeEmail of employee.
     */
    private String officeEmail;
    /**
     * The hotelPhoneNo is used for storing hotelPhoneNo of employee.
     */
    private String hotelPhoneNo;
    /**
     * The personalEmail is used for storing personal Email Of employee.
     */
    private String personalEmail;
    /**
     * The indiaPhoneNo is used for storing indiaPhoneNo of employee.
     */
    private String indiaPhoneNo;
    /**
     * The otherEmail is used for storing other Email of employee.
     */
    private String otherEmail;
    /**
     * The faxNo is used for storing Fax of employee.
     */
    private String faxNo;
    /**
     * The lastContactBy is used for storing lastContact Peroson User Id.
     */
    private String lastContactBy;
    /**
     * The modifiedBy is used for storing modified Person Id.
     */
    private String modifiedBy;
    /**
     * The modifiedDate is used for storing modifiedDate.
     */
    private Timestamp modifiedDate;
    /**
     * The createdBy is used for storing created Person id.
     */
    private String createdBy;
    /**
     * The createdDate is used for storing created Date.
     */
    private Timestamp createdDate;
    /**
     * The genderList is used for storing genderOptions.
     */
    private List genderList;
    /**
     * The maritalStatusList is used for storing maritalStatusOptions.
     */
    private List maritalStatusList;
    /**
     * The countryList is used for storing countryOptions.
     */
    private List countryList;
    /**
     * The currStatusList is used for storing currStatusListOptions.
     */
    private List currStatusList;
    /**
     * The empTypeIdList is used for storing employeeTypeId Options.
     */
    private List empTypeIdList;
    /**
     * The orgIdList is used for storing Organizationid Options.
     */
    private List orgIdList;
    /**
     * The opsContactIdMap is used for storing ContactId Options.
     */
    private Map opsContactIdMap;
    /**
     * The teamIdList is used for storing TeamId Options.
     */
    private List teamIdList;
    /**
     * The practiceIdList is used for storing practiceId Options.
     */
    private List practiceIdList;
    private List subPracticeList;
    private List empCurrentStatus;
    /**
     * The reportsToIdMap is used for storing reportsToId Options.
     */
    private Map reportsToIdMap;
    /**
     * titleIdList is used for storing titleId Options.
     */
    private List titleIdList;
    /**
     * The industryIdList is used for storing industryList Options.
     */
    private List industryIdList;
    /**
     * The departmentIdList is used for storing departmentId Options.
     */
    private List departmentIdList;
    /**
     * isManager is used to store the flag to identify user has manager role
     */
    private boolean isManager;
    /**
     * isTeamLead is used to store the flag to identify user has teamLead role
     */
    private boolean isTeamLead;
    /**
     * reportsTo is used to store the User Id of reporting person
     */
    private String reportsTo;
    /**
     * The pointOfContactMap is used for storing pointOfContact Options.
     */
    private Map pointOfContactMap;
    /**
     * The submitFrom is used to store the value of request from wich form on
     * the page
     */
    private String submitFrom;
    private String submitFromReportsTo;
    //private Map regionMap;
    private List regionList;
    private List territoryList;
    private String workingCountry;
    private String empState;
    private Date stateStartDate;
    private Date stateEndDate;
    // private float intRatePerHour;
    // private float invRatePerHour;
    private String skillSet;
    private int userRoleId;
    private String prjName;
    // private double ctcPerYear;
    private String itgBatch;
    private String isOnsite;
    private boolean isCreTeam;
    private StateVTO currentEmployeeState;
    private String tempName = null;
    private String tempPh = null;
    private String tempCurStatus = null;
    private String tempDeptId = null;
    private String tempOrgId = null;
    private int currId;
    private int tempVar;
    private Map reportingPersons;
    private String reportingpersonId;
    //new
    private File imagePath = null;
    //new varibble to allow either insert or update in Employee current statsus screen
    private int navId = 0;
    private String location;
    // For oprations team
    private boolean isOperationTeam;
    private String projectName;
    private String status;
    private String startDate;
    private String projectId;
    private String accountId;
    private Map myAccounts;
    private Map myProjects;
    private boolean isPMO;
    //New Fileds For Confedential Info Date : 08/19/2014
    private String bankName;
    private String accNum;
    private String nameAsPerAcc;
    private String ifscCode;
    private String phyChallenged;
    private String phyCategory;
    private String aadharNum;
    private String aadharName;
    private String nameAsPerPan;
    private String uanNo;
    private String pfno;
    private String techReviews;
    private String endDate;
// New ReportstoFlag
    private boolean reportsToFlag;
    private Date lastRevisedDate;
    private Date revisedDate;
    private HttpServletResponse httpServletResponse;
    private Map myTeamMembers;
    private List stateList;
    private Map managerTeamMembersList;
    private String managerTeamMember;
    private String currentAction;
    private String customerName;
    private int utilization;
    private String comments;
    private Map projectTeamReportsTo;
    private String isBillable;
    private boolean isInternationalWorker;
    //private List locationsList;
    private Map locationsMap;
    private List practiceList = new ArrayList();
    private String empStatus;
    private Map myPMOTeamMembers;
    private Date dateOfTermination;
    private String reasonsForTerminate;
    private String prvexpMnths;
    private String prvexpYears;
    private File upload;
    private String uploadContentType;
    private String uploadFileName;
    private String filepath;
    private InputStream inputStream;
    private OutputStream outputStream;
    private int projectContactId;
    private boolean lateralFlag;
    private Map clientMap = new LinkedHashMap();
    private String empName;
    private String empDateOFBirth;
    private String isLateral;
    private String quarterAppraisalDetails;
    private int rowCount;
    private String curretRole;
    private int appraisalId;
    private int lineId;
    private int year;
    private String quarterly;
    private String shortTermGoal;
    private String shortTermGoalComments;
    private String longTermGoal;
    private String longTermGoalComments;
    private String strength;
    private String strengthsComments;
    private String improvements;
    private String improvementsComments;
    private String rejectedComments;
    private String operationTeamStatus;
    private String managerRejectedComments;
    private String operationRejectedComments;
    private int dayCount;
    private int accessCount;
    private String empProjectStatus="Active";	

    public boolean getReportsToFlag() {
        return reportsToFlag;
    }

    public void setReportsToFlag(boolean reportsToFlag) {
        this.reportsToFlag = reportsToFlag;
    }

    /**
     * Creates a new instance of EmployeeAction
     */
    public EmployeeAction() {
    }

    /**
     * The prepare() is used for executing block of code which is required
     * before execute method get's executed.
     *
     * @throws Exception
     * @return string variable.
     */
    public String prepare() throws Exception {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            String roleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            //System.out.println("roleName---->"+roleName);
            hibernateDataProvider = HibernateDataProvider.getInstance();
            defaultDataProvider = DefaultDataProvider.getInstance();
            employeeService = ServiceLocator.getEmployeeService();
            dataSourceDataProvider = dataSourceDataProvider.getInstance();

            setGenderList(defaultDataProvider.getGender(ApplicationConstants.GENDER_OPTIONS));

            setMaritalStatusList(defaultDataProvider.getMaritalStatus(ApplicationConstants.MARITAL_STATUS_OPTIONS));

            setCountryList(hibernateDataProvider.getContries(ApplicationConstants.COUNTRY_OPTIONS));

            //setTerritoryList(hibernateDataProvider.getTerritoryList(ApplicationConstants.QS_COUNT_DASHBOARDTERRITORY_LIST));

            setCurrStatusList(defaultDataProvider.getCurrentStatus(ApplicationConstants.CURRENT_STATUS_OPTIONS));

            setEmpTypeIdList(hibernateDataProvider.getEmployeeType(ApplicationConstants.EMP_TYPE_OPTIONS));

            setOrgIdList(hibernateDataProvider.getLkOrganization(ApplicationConstants.LKORGANIZATION_OPTION));

            //setOpsContactIdMap(hibernateDataProvider.getEmployeeNames());
            //setRegionList(hibernateDataProvider.getRegion(ApplicationConstants.REGION_OPTIONS));

            setPointOfContactMap(dataSourceDataProvider.getPointOfContact());



            //setPracticeIdList(hibernateDataProvider.getPractice(ApplicationConstants.PRACTICE_OPTION));
            setPracticeIdList(dataSourceDataProvider.getPracticeByDepartment(getDepartmentId()));

            setSubPracticeList(dataSourceDataProvider.getSubPracticeByPractice(getPracticeId()));

            setTeamIdList(dataSourceDataProvider.getTeamBySubPractice(getSubPractice()));

            setEmpCurrentStatus(hibernateDataProvider.getEmpCurrentState(ApplicationConstants.EMP_CURRENT_STATUS));

            setReportsToIdMap(dataSourceDataProvider.getReportsToByDepartment(getDepartmentId()));
            setReportingPersons(dataSourceDataProvider.getEmployeeNamesByReportingPerson());

            //setReportsToIdMap(hibernateDataProvider.getReportsToDefault());

//            if("Registered".equalsIgnoreCase(getCurrentEmployee().getCurrStatus())){
//                reportsToIdMap = new TreeMap();
//                reportsToIdMap.put("plokam","Prasad Venkata.Lokam");
//                reportsToIdMap.put("mlokam","Madhavi Naga.Loakam");
//            }


            //setTitleIdList(hibernateDataProvider.getTitleType(ApplicationConstants.TITLE_TYPE_OPTIONS));
            setTitleIdList(dataSourceDataProvider.getTitleTypeByDepartment(getDepartmentId()));

            setIndustryIdList(hibernateDataProvider.getIndustry(ApplicationConstants.INDUSTRY_OPTION));

            setDepartmentIdList(hibernateDataProvider.getDepartment(ApplicationConstants.DEPARTMENT_OPTION));

            setCurrentStateHistoryCollection(employeeService.getStateHistoryCollection(getLoginId(), 100));

            //setRegionMap(hibernateDataProvider.getRegion(ApplicationConstants.REGION_OPTIONS));

            String Country = (String) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.Living_COUNTRY);
            setOpsContactIdMap(dataSourceDataProvider.getOpsContactId(Country, roleName));
            setLocationsMap(dataSourceDataProvider.getEmployeeLocationsList(getCurrentEmployee().getCountry()));
            resultType = SUCCESS;
        }
        return resultType;
    }

    /**
     * The searchPrepare() is used for displaying selectBox options from
     * database server.
     *
     * @throws Exception
     * @return String variable for navigation.
     */
    public String searchPrepare() throws Exception {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            hibernateDataProvider = HibernateDataProvider.getInstance();
            defaultDataProvider = DefaultDataProvider.getInstance();
            setDepartmentIdList(hibernateDataProvider.getDepartment(ApplicationConstants.DEPARTMENT_OPTION));
            setPracticeIdList(DataSourceDataProvider.getInstance().getPracticeByDepartment(getDepartmentId()));
            setCurrStatusList(defaultDataProvider.getCurrentStatus(ApplicationConstants.CURRENT_STATUS_OPTIONS));
            setOrgIdList(hibernateDataProvider.getLkOrganization(ApplicationConstants.LKORGANIZATION_OPTION));
            setReportingPersons(DataSourceDataProvider.getInstance().getEmployeeNamesByReportingPerson());
            setCountryList(hibernateDataProvider.getContries(ApplicationConstants.COUNTRY_OPTIONS));
            String Country = (String) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.Living_COUNTRY);
            String roleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();

            setOpsContactIdMap(DataSourceDataProvider.getInstance().getOpsContactId(Country, roleName));
            dataSourceDataProvider = DataSourceDataProvider.getInstance();
            if ("Admin".equals(roleName)) {
                setLocationsMap(dataSourceDataProvider.getEmployeeLocationsList("%"));
            } else {
                setLocationsMap(dataSourceDataProvider.getEmployeeLocationsList(Country));
            }
            resultType = SUCCESS;
        }
        return resultType;
    }

    public String getEmpBackToList() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                searchPrepare();
                resultType = SUCCESS;
            } catch (Exception ex) {
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }
        }
        return resultType;
    }

    public String getEmpSearchAll() {
        resultType = LOGIN;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            // String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String livingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.Living_COUNTRY).toString();
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();

            //System.out.println("workingCountry----->"+workingCountry);
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("GET_EMP_SEARCH_ALL", userRoleId)) {
                try {
                    if (getSubmitFrom() == null) {
                        if ("Admin".equals(userRoleName)) {

                            queryString = "SELECT Id, DepartmentId, LName, FName, MName,EmpNo, Email1, WorkPhoneNo, AlterPhoneNo, CellPhoneNo, CurStatus ,LoginId ";
                            queryString = queryString + " FROM tblEmployee WHERE CurStatus='Active' and DeletedFlag != 1 ORDER BY trim(FName)";

                        } /*else {
                        queryString = "SELECT Id, DepartmentId, LName, FName, MName,EmpNo, Email1, WorkPhoneNo, AlterPhoneNo, CellPhoneNo, CurStatus ,LoginId ";
                        queryString = queryString + " FROM tblEmployee WHERE CurStatus='Active' and Country like '" + livingCountry + "' and DeletedFlag != 1 ORDER BY trim(FName)";
                        }*/ else {
                            queryString = "SELECT Id, DepartmentId, LName, FName, MName,EmpNo, Email1, WorkPhoneNo, AlterPhoneNo, CellPhoneNo, CurStatus ,LoginId ";
                            queryString = queryString + " FROM tblEmployee WHERE CurStatus='Active' and ";
                            if (livingCountry.equalsIgnoreCase("USA")) {
                                queryString = queryString + "Country != 'India' and ";
                            } else {
                                queryString = queryString + "Country like 'India' and ";
                            }

                            queryString = queryString + " DeletedFlag != 1 ORDER BY trim(FName)";
                        }
                        dataSourceDataProvider = DataSourceDataProvider.getInstance();
                        setSubmitFrom("searchFormAll");
                        setCurrStatus("Active");
                        //System.err.println("Before");
                        setReportingPersons(dataSourceDataProvider.getEmployeeNamesByReportingPerson());
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.SESSION_DELETE_ACTION, "empSearchAll");
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_EMP_LIST, queryString);
                    }
                    searchPrepare();
                    //  prepare();
                    resultType = SUCCESS;
                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }

    public String getEmpSearchMyTeam() {
        resultType = LOGIN;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("GET_EMP_SEARCH_MY_TEAM", userRoleId)) {
                try {
                    if (getSubmitFrom() == null) {
                        queryString = "SELECT Id, DepartmentId, LName, FName, MName, Email1, WorkPhoneNo, AlterPhoneNo, CellPhoneNo, CurStatus ";
                        queryString = queryString + " FROM tblEmployee WHERE ReportsTo='" + httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) + "' AND DeletedFlag != 1 ORDER BY CurStatus,LName";
                        setSubmitFrom("searchFormMyTeam");
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.SESSION_DELETE_ACTION, "empSearchMyTeam");
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_EMP_LIST, queryString);
                    }
                    searchPrepare();
                    resultType = SUCCESS;
                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }

    /**
     * The getSearchQuery() is used for retrieving SQLquery .
     *
     * @throws Exception
     * @return String variable for navigation.
     */
    /*Modifed By : Aditya MAlla
     * Modified Date : 08/04/2014
     * Reason : Added IsTeamLead and Is MAnager search fields
     */
    /*
    
    
    public String getSearchQuery() {
    
    // System.out.println("I am in getSearchQuery() +getOrgId());
    resultType = LOGIN;
    
    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
    
    userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
    String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
    resultType = "accessFailed";
    String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
    if(AuthorizationManager.getInstance().isAuthorizedUser("GET_SEARCH_QUERY_EMP",userRoleId)){
    try{
    // setPracticeIdList(dataSourceDataProvider.getPracticeByDepartment(getDepartmentId()));
    // setPracticeId(getCurrentEmployee().getPracticeId());
    // setSubPractice(getCurrentEmployee().getSubPractice());
    
    //System.err.println(getSubmitFrom());
    
    if("searchFormAll".equalsIgnoreCase(getSubmitFrom()) || "searchFormMyTeam".equalsIgnoreCase(getSubmitFrom())){
    queryStringBuffer = new StringBuffer();
    
    queryStringBuffer.append("SELECT Id, DepartmentId, LName, FName, MName, Email1, WorkPhoneNo, AlterPhoneNo, CellPhoneNo, CurStatus,SubPractice,TeamId");
    queryStringBuffer.append(" FROM tblEmployee ");
    
    // queryStringBuffer.append(" tblLKSubPractice");
    
    if(null == getFirstName()) setFirstName("");
    if(null == getLastName()) setLastName("");
    if(null == getWorkPhoneNo()) setWorkPhoneNo("");
    if(null == getCurrStatus()) setCurrStatus("");
    if("All".equalsIgnoreCase(getCurrStatus())) setCurrStatus("");
    if(null == getOrgId()) setOrgId("");
    if("All".equalsIgnoreCase(getOrgId())) setOrgId("");
    if(null == getReportingpersonId()) setReportingpersonId("");
    if("All".equalsIgnoreCase(getReportingpersonId())) setReportingpersonId("");
    if(null == getOrgId()) setOrgId("");
    if("All".equalsIgnoreCase(getOrgId())) setOrgId("");
    if(null== getDepartmentId()) setDepartmentId("");
    if(null==getSubPractice()) setSubPractice("");
    if("All".equalsIgnoreCase(getDepartmentId())) setDepartmentId("");
    if(null == getTeamId()) setTeamId("");
    if("All".equalsIgnoreCase(getTeamId())) setTeamId("");
    
    
    
    
    //                if((!"".equals(getFirstName()))
    //                || (!"".equals(getLastName()))
    //                || (!"".equals(getWorkPhoneNo()))
    //                || (!"".equals(getCurrStatus()))
    //                ||(!"".equals(getOrgId()))){
    //                    queryStringBuffer.append(" WHERE ");
    //                }
    if("Admin".equals(userRoleName)) {
    queryStringBuffer.append("where ");
    }else {
    queryStringBuffer.append(" WHERE  Country like '"+workingCountry+"' AND ");
    }
    int columnCounter = 0;
    
    
    if(!"".equals(getFirstName()) && columnCounter==0){
    setTempName(getFirstName());
    if((getFirstName().indexOf("*") == -1)&&(getFirstName().indexOf("%") == -1)) setFirstName(getFirstName()+"*");
    setFirstName(getFirstName().replace("*","%"));
    queryStringBuffer.append("(FName LIKE '" + getFirstName() + "%' OR LName LIKE '" + getFirstName() + "') ");
    columnCounter ++;
    }else if(!"".equals(getFirstName()) && columnCounter!=0){
    setTempName(getFirstName());
    if((getFirstName().indexOf("*") == -1)&&(getFirstName().indexOf("%") == -1)) setFirstName(getFirstName()+"*");
    setFirstName(getFirstName().replace("*","%"));
    queryStringBuffer.append("AND (FName LIKE '" + getFirstName() + "' OR LName LIKE '" + getFirstName() + "') ");
    }
    //                if(!"".equals(getLastName()) && columnCounter==0){
    //                    queryStringBuffer.append("LName LIKE '" + getLastName() + "%' ");
    //                    columnCounter ++;
    //                }else if(!"".equals(getLastName()) && columnCounter!=0){
    //                    queryStringBuffer.append("AND LName LIKE '" + getLastName() + "%' ");
    //                }
    
    if(!"".equals(getWorkPhoneNo()) && columnCounter==0){
    setTempPh(getWorkPhoneNo());
    if((getWorkPhoneNo().indexOf("*") ==  -1)&&(getWorkPhoneNo().indexOf("%") ==  -1)) setWorkPhoneNo(getWorkPhoneNo().replace("*","%"));
    setWorkPhoneNo(getWorkPhoneNo().replace("*","%"));
    queryStringBuffer.append("WorkPhoneNo LIKE '" + getWorkPhoneNo() + "' ");
    columnCounter ++;
    }else if(!"".equals(getWorkPhoneNo()) && columnCounter!=0){
    setTempPh(getWorkPhoneNo());
    if((getWorkPhoneNo().indexOf("*") ==  -1)&&(getWorkPhoneNo().indexOf("%") ==  -1)) setWorkPhoneNo(getWorkPhoneNo().replace("*","%"));
    queryStringBuffer.append("AND WorkPhoneNo LIKE '" + getWorkPhoneNo() + "' ");
    }
    
    
    if(!"".equals(getCurrStatus()) && columnCounter==0){
    queryStringBuffer.append("CurStatus ='" + getCurrStatus() + "' ");
    columnCounter ++;
    setTempCurStatus(getCurrStatus());
    }else if(!"".equals(getCurrStatus()) && columnCounter!=0){
    queryStringBuffer.append("AND CurStatus ='" + getCurrStatus() + "' ");
    setTempCurStatus(getCurrStatus());
    } else {
    setTempCurStatus("All");
    }
    if(!"".equals(getOrgId()) && columnCounter==0){
    queryStringBuffer.append("OrgId='" + getOrgId() + "' ");
    columnCounter ++;
    setTempOrgId(getOrgId());
    }else if(!"".equals(getOrgId()) && columnCounter!=0){
    queryStringBuffer.append("AND OrgId='" + getOrgId() + "' ");
    setTempOrgId(getOrgId());
    }
    
    
    if(!"".equals(getReportingpersonId()) && columnCounter==0){
    queryStringBuffer.append("reportsTo='" + getReportingpersonId() + "' ");
    columnCounter ++;
    
    }else if(!"".equals(getReportingpersonId()) && columnCounter!=0){
    queryStringBuffer.append("AND reportsTo='" + getReportingpersonId() + "' ");
    
    }
    
    if(!"".equals(getDepartmentId()) && columnCounter==0){
    queryStringBuffer.append("DepartmentId='" + getDepartmentId() + "' ");
    columnCounter ++;
    }else if(!"".equals(getDepartmentId()) && columnCounter!=0){
    queryStringBuffer.append("AND DepartmentId='" + getDepartmentId() + "' ");
    setTempDeptId(getDepartmentId());
    }
    
    if(!"".equals(getSubPractice()) && columnCounter==0){
    queryStringBuffer.append("SubPractice='" + getSubPractice() + "' ");
    columnCounter ++;
    }else if(!"".equals(getSubPractice()) && columnCounter!=0){
    queryStringBuffer.append("AND SubPractice='" + getSubPractice() + "' ");
    }
    if(!"".equals(getTeamId()) && columnCounter==0){
    queryStringBuffer.append("TeamId='" + getTeamId() + "' ");
    columnCounter ++;
    }else if(!"".equals(getTeamId()) && columnCounter!=0){
    queryStringBuffer.append("AND TeamId='" + getTeamId() + "' ");
    }
    
    
    if("searchFormMyTeam".equalsIgnoreCase(getSubmitFrom()) && columnCounter==0){
    queryStringBuffer.append(" ReportsTo='"+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID)+"' ");
    columnCounter ++;;
    }else if("searchFormMyTeam".equalsIgnoreCase(getSubmitFrom()) && columnCounter!=0){
    queryStringBuffer.append(" AND ReportsTo='"+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID)+"' ");
    columnCounter ++;
    }
    
    if(columnCounter == 0){
    queryStringBuffer.append(" DeletedFlag != 1 ORDER BY trim(FName)");
    }else if(columnCounter != 0){
    queryStringBuffer.append(" AND DeletedFlag != 1 ORDER BY trim(FName)");
    }
    
    
    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_EMP_LIST,queryStringBuffer.toString());
    //System.err.println(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_EMP_LIST));
    queryStringBuffer.delete(0,queryStringBuffer.capacity());
    }
    
    if("dbGrid".equalsIgnoreCase(getSubmitFrom())){
    queryStringBuffer = new StringBuffer();
    
    queryStringBuffer.append("SELECT Id, DepartmentId, LName, FName, MName, Email1, WorkPhoneNo, AlterPhoneNo, CellPhoneNo, CurStatus,SubPractice,TeamId");
    queryStringBuffer.append(" FROM tblEmployee  ");
    // queryStringBuffer.append(" tblLKSubPractice");
    
    if(null == getFirstName()) setFirstName("");
    if(null == getLastName()) setLastName("");
    if(null == getWorkPhoneNo()) setWorkPhoneNo("");
    if(null == getCurrStatus()) setCurrStatus("");
    if("All".equalsIgnoreCase(getCurrStatus())) setCurrStatus("");
    if(null == getOrgId()) setOrgId("");
    if("All".equalsIgnoreCase(getOrgId())) setOrgId("");
    if(null == getReportingpersonId()) setReportingpersonId("");
    if("All".equalsIgnoreCase(getReportingpersonId())) setReportingpersonId("");
    if(null== getDepartmentId()) setDepartmentId("");
    if(null==getSubPractice()) setSubPractice("");
    if("All".equalsIgnoreCase(getDepartmentId())) setDepartmentId("");
    if(null == getTeamId()) setTeamId("");
    if("All".equalsIgnoreCase(getTeamId())) setTeamId("");
    
    
    
    
    //                if((!"".equals(getFirstName()))
    //                || (!"".equals(getLastName()))
    //                || (!"".equals(getWorkPhoneNo()))
    //                || (!"".equals(getCurrStatus()))
    //                ||(!"".equals(getOrgId()))){
    //                    queryStringBuffer.append(" WHERE ");
    //                }
    
    // queryStringBuffer.append(" WHERE ");
    
    if("Admin".equals(userRoleName)) {
    queryStringBuffer.append("where ");
    }else {
    queryStringBuffer.append(" WHERE  Country like '"+workingCountry+"' AND ");
    }
    int columnCounter = 0;
    
    
    if(!"".equals(getFirstName()) && columnCounter==0){
    setTempName(getFirstName());
    if((getFirstName().indexOf("*") == -1)&&(getFirstName().indexOf("%") == -1)) setFirstName(getFirstName()+"*");
    setFirstName(getFirstName().replace("*","%"));
    queryStringBuffer.append("(FName LIKE '" + getFirstName() + "%' OR LName LIKE '" + getFirstName() + "') ");
    columnCounter ++;
    }else if(!"".equals(getFirstName()) && columnCounter!=0){
    setTempName(getFirstName());
    if((getFirstName().indexOf("*") == -1)&&(getFirstName().indexOf("%") == -1)) setFirstName(getFirstName()+"*");
    setFirstName(getFirstName().replace("*","%"));
    queryStringBuffer.append("AND (FName LIKE '" + getFirstName() + "' OR LName LIKE '" + getFirstName() + "') ");
    }
    //                if(!"".equals(getLastName()) && columnCounter==0){
    //                    queryStringBuffer.append("LName LIKE '" + getLastName() + "%' ");
    //                    columnCounter ++;
    //                }else if(!"".equals(getLastName()) && columnCounter!=0){
    //                    queryStringBuffer.append("AND LName LIKE '" + getLastName() + "%' ");
    //                }
    
    if(!"".equals(getWorkPhoneNo()) && columnCounter==0){
    setTempPh(getWorkPhoneNo());
    
    
    if((getWorkPhoneNo().indexOf("*") ==  -1)&&(getWorkPhoneNo().indexOf("%") ==  -1)) setWorkPhoneNo(getWorkPhoneNo().replace("*","%"));
    setWorkPhoneNo(getWorkPhoneNo().replace("*","%"));
    queryStringBuffer.append("WorkPhoneNo LIKE '" + getWorkPhoneNo() + "' ");
    
    columnCounter ++;
    }else if(!"".equals(getWorkPhoneNo()) && columnCounter!=0){
    setTempPh(getWorkPhoneNo());
    if((getWorkPhoneNo().indexOf("*") ==  -1)&&(getWorkPhoneNo().indexOf("%") ==  -1)) setWorkPhoneNo(getWorkPhoneNo().replace("*","%"));
    queryStringBuffer.append("AND WorkPhoneNo LIKE '" + getWorkPhoneNo() + "' ");
    }
    
    
    if(!"".equals(getCurrStatus()) && columnCounter==0){
    queryStringBuffer.append("CurStatus ='" + getCurrStatus() + "' ");
    columnCounter ++;
    setTempCurStatus(getCurrStatus());
    }else if(!"".equals(getCurrStatus()) && columnCounter!=0){
    queryStringBuffer.append("AND CurStatus ='" + getCurrStatus() + "' ");
    setTempCurStatus(getCurrStatus());
    } else {
    setTempCurStatus("All");
    }
    if(!"".equals(getOrgId()) && columnCounter==0){
    queryStringBuffer.append("OrgId='" + getOrgId() + "' ");
    columnCounter ++;
    setTempOrgId(getOrgId());
    }else if(!"".equals(getOrgId()) && columnCounter!=0){
    queryStringBuffer.append("AND OrgId='" + getOrgId() + "' ");
    setTempOrgId(getOrgId());
    }
    
    
    if(!"".equals(getReportingpersonId()) && columnCounter==0){
    queryStringBuffer.append("reportsTo='" + getReportingpersonId() + "' ");
    columnCounter ++;
    
    }else if(!"".equals(getReportingpersonId()) && columnCounter!=0){
    queryStringBuffer.append("AND reportsTo='" + getReportingpersonId() + "' ");
    
    }
    
    if(!"".equals(getDepartmentId()) && columnCounter==0){
    queryStringBuffer.append("DepartmentId='" + getDepartmentId() + "' ");
    columnCounter ++;
    }else if(!"".equals(getDepartmentId()) && columnCounter!=0){
    queryStringBuffer.append("AND DepartmentId='" + getDepartmentId() + "' ");
    setTempDeptId(getDepartmentId());
    }
    
    if(!"".equals(getSubPractice()) && columnCounter==0){
    queryStringBuffer.append("SubPractice='" + getSubPractice() + "' ");
    columnCounter ++;
    }else if(!"".equals(getSubPractice()) && columnCounter!=0){
    queryStringBuffer.append("AND SubPractice='" + getSubPractice() + "' ");
    }
    if(!"".equals(getTeamId()) && columnCounter==0){
    queryStringBuffer.append("TeamId='" + getTeamId() + "' ");
    columnCounter ++;
    }else if(!"".equals(getTeamId()) && columnCounter!=0){
    queryStringBuffer.append("AND TeamId='" + getTeamId() + "' ");
    }
    
    
    if("searchFormMyTeam".equalsIgnoreCase(getSubmitFrom()) && columnCounter==0){
    queryStringBuffer.append(" ReportsTo='"+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID)+"' ");
    columnCounter ++;
    }else if("searchFormMyTeam".equalsIgnoreCase(getSubmitFrom()) && columnCounter!=0){
    queryStringBuffer.append(" AND ReportsTo='"+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID)+"' ");
    columnCounter ++;
    }
    }
    
    if(columnCounter == 0){
    queryStringBuffer.append(" DeletedFlag != 1 ORDER BY trim(FName)");
    }else if(columnCounter != 0){
    queryStringBuffer.append(" AND DeletedFlag != 1 ORDER BY trim(FName)");
    }
    
    //System.out.println("queryStringBuffer------>"+queryStringBuffer);
    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_EMP_LIST,queryStringBuffer.toString());
    
    queryStringBuffer.delete(0,queryStringBuffer.capacity());
    
    }
    //Calling searchPrepare() method to populate select components
    searchPrepare();
    prepare();
    setFirstName(getTempName());
    setWorkPhoneNo(getTempPh());
    setCurrStatus(getTempCurStatus());
    setDepartmentId(getTempDeptId());
    setOrgId(getTempOrgId());
    setReportingpersonId(getReportingpersonId());
    // System.err.println(getReportingpersonId());
    
    resultType = SUCCESS;
    }catch (Exception ex){
    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
    resultType =  ERROR;
    }
    }//END-Authorization Checking
    }//Close Session Checking
    //System.err.println("resultType"+resultType);
    return resultType;
    }
    
     */
    public String getSearchQuery() {

        // System.out.println("I am in getSearchQuery() +getOrgId());
        resultType = LOGIN;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String livingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.Living_COUNTRY).toString();
            resultType = "accessFailed";
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            if (AuthorizationManager.getInstance().isAuthorizedUser("GET_SEARCH_QUERY_EMP", userRoleId)) {
                try {
                    // setPracticeIdList(dataSourceDataProvider.getPracticeByDepartment(getDepartmentId()));
                    // setPracticeId(getCurrentEmployee().getPracticeId());
                    // setSubPractice(getCurrentEmployee().getSubPractice());

                    //System.err.println(getSubmitFrom());

                    if ("searchFormAll".equalsIgnoreCase(getSubmitFrom()) || "searchFormMyTeam".equalsIgnoreCase(getSubmitFrom())) {
                        queryStringBuffer = new StringBuffer();

                        queryStringBuffer.append("SELECT Id, DepartmentId, LName, FName, MName,EmpNo, Email1, WorkPhoneNo, AlterPhoneNo, CellPhoneNo, CurStatus,SubPractice,TeamId ,LoginId ");
                        queryStringBuffer.append(" FROM tblEmployee ");

                        // queryStringBuffer.append(" tblLKSubPractice");

                        if (null == getFirstName()) {
                            setFirstName("");
                        }
                        if (null == getLastName()) {
                            setLastName("");
                        }
                        if (null == getWorkPhoneNo()) {
                            setWorkPhoneNo("");
                        }
                        if (null == getCurrStatus()) {
                            setCurrStatus("");
                        }
                        if (null == getEmpno()) {
                            setEmpno("");
                        }
                        if ("All".equalsIgnoreCase(getCurrStatus())) {
                            setCurrStatus("");
                        }
                        if (null == getOrgId()) {
                            setOrgId("");
                        }
                        if ("All".equalsIgnoreCase(getOrgId())) {
                            setOrgId("");
                        }
                        if (null == getReportingpersonId()) {
                            setReportingpersonId("");
                        }
                        if ("All".equalsIgnoreCase(getReportingpersonId())) {
                            setReportingpersonId("");
                        }
                        if (null == getOrgId()) {
                            setOrgId("");
                        }
                        if ("All".equalsIgnoreCase(getOrgId())) {
                            setOrgId("");
                        }
                        if (null == getDepartmentId()) {
                            setDepartmentId("");
                        }
                        if (null == getSubPractice()) {
                            setSubPractice("");
                        }
                        if ("All".equalsIgnoreCase(getDepartmentId())) {
                            setDepartmentId("");
                        }
                        if (null == getTeamId()) {
                            setTeamId("");
                        }
                        if ("All".equalsIgnoreCase(getTeamId())) {
                            setTeamId("");
                        }
                        if (null == getCountry()) {
                            setCountry("");
                        }
                        if (null == getPracticeId()) {
                            setPracticeId("");
                        }
                        if ("All".equalsIgnoreCase(getLocation())) {
                            setLocation("");
                        }
                        if (null == getItgBatch()) {
                            setItgBatch("");
                        }



//                if((!"".equals(getFirstName()))
//                || (!"".equals(getLastName()))
//                || (!"".equals(getWorkPhoneNo()))
//                || (!"".equals(getCurrStatus()))
//                ||(!"".equals(getOrgId()))){
//                    queryStringBuffer.append(" WHERE ");
//                }
                        if ("Admin".equals(userRoleName)) {
                            queryStringBuffer.append("where ");
                        } //                        else {
                        //                            queryStringBuffer.append(" WHERE  Country like '" + livingCountry + "' AND ");
                        //                        }
                        else {
                            if (livingCountry.equalsIgnoreCase("USA")) {
                                queryStringBuffer.append(" WHERE  Country != 'India' AND ");
                            } else {
                                queryStringBuffer.append(" WHERE  Country like 'India' AND ");
                            }

                        }
                        int columnCounter = 0;
                        if (getIsManager() && columnCounter == 0) {
                            setIsManager(getIsManager());

                            queryStringBuffer.append("IsManager =1 ");
                            columnCounter++;
                        } else if (getIsManager() && columnCounter != 0) {
                            setIsManager(getIsManager());
                            queryStringBuffer.append("AND IsManager =1 ");
                        }
                        if (isIsTeamLead() && columnCounter == 0) {
                            setIsTeamLead(isIsTeamLead());

                            queryStringBuffer.append("IsTeamLead =1 ");
                            columnCounter++;
                        } else if (isIsTeamLead() && columnCounter != 0) {
                            setIsTeamLead(isIsTeamLead());
                            queryStringBuffer.append("AND IsTeamLead =1 ");
                        }
                        if (!"".equals(getFirstName()) && columnCounter == 0) {
                            setTempName(getFirstName());
                            if ((getFirstName().indexOf("*") == -1) && (getFirstName().indexOf("%") == -1)) {
                                setFirstName(getFirstName() + "*");
                            }
                            setFirstName(getFirstName().replace("*", "%"));
                            queryStringBuffer.append("(FName LIKE '" + getFirstName() + "%' OR LName LIKE '" + getFirstName() + "') ");
                            columnCounter++;
                        } else if (!"".equals(getFirstName()) && columnCounter != 0) {
                            setTempName(getFirstName());
                            if ((getFirstName().indexOf("*") == -1) && (getFirstName().indexOf("%") == -1)) {
                                setFirstName(getFirstName() + "*");
                            }
                            setFirstName(getFirstName().replace("*", "%"));
                            queryStringBuffer.append("AND (FName LIKE '" + getFirstName() + "' OR LName LIKE '" + getFirstName() + "') ");
                        }

//                if(!"".equals(getLastName()) && columnCounter==0){
//                    queryStringBuffer.append("LName LIKE '" + getLastName() + "%' ");
//                    columnCounter ++;
//                }else if(!"".equals(getLastName()) && columnCounter!=0){
//                    queryStringBuffer.append("AND LName LIKE '" + getLastName() + "%' ");
//                }

                        if (!"".equals(getWorkPhoneNo()) && columnCounter == 0) {
                            setTempPh(getWorkPhoneNo());
                            if ((getWorkPhoneNo().indexOf("*") == -1) && (getWorkPhoneNo().indexOf("%") == -1)) {
                                setWorkPhoneNo(getWorkPhoneNo().replace("*", "%"));
                            }
                            setWorkPhoneNo(getWorkPhoneNo().replace("*", "%"));
                            queryStringBuffer.append("WorkPhoneNo LIKE '" + getWorkPhoneNo() + "' ");
                            columnCounter++;
                        } else if (!"".equals(getWorkPhoneNo()) && columnCounter != 0) {
                            setTempPh(getWorkPhoneNo());
                            if ((getWorkPhoneNo().indexOf("*") == -1) && (getWorkPhoneNo().indexOf("%") == -1)) {
                                setWorkPhoneNo(getWorkPhoneNo().replace("*", "%"));
                            }
                            queryStringBuffer.append("AND WorkPhoneNo LIKE '" + getWorkPhoneNo() + "' ");
                        }


                        if (!"".equals(getCurrStatus()) && columnCounter == 0) {
                            queryStringBuffer.append("CurStatus ='" + getCurrStatus() + "' ");
                            columnCounter++;
                            setTempCurStatus(getCurrStatus());
                        } else if (!"".equals(getCurrStatus()) && columnCounter != 0) {
                            queryStringBuffer.append("AND CurStatus ='" + getCurrStatus() + "' ");
                            setTempCurStatus(getCurrStatus());
                        } else {
                            setTempCurStatus("All");
                        }
                        if (!"".equals(getOrgId()) && columnCounter == 0) {
                            queryStringBuffer.append("OrgId='" + getOrgId() + "' ");
                            columnCounter++;
                            setTempOrgId(getOrgId());
                        } else if (!"".equals(getOrgId()) && columnCounter != 0) {
                            queryStringBuffer.append("AND OrgId='" + getOrgId() + "' ");
                            setTempOrgId(getOrgId());
                        }


                        if (!"".equals(getReportingpersonId()) && columnCounter == 0) {
                            queryStringBuffer.append("reportsTo='" + getReportingpersonId() + "' ");
                            columnCounter++;

                        } else if (!"".equals(getReportingpersonId()) && columnCounter != 0) {
                            queryStringBuffer.append("AND reportsTo='" + getReportingpersonId() + "' ");

                        }

                        if (!"".equals(getDepartmentId()) && columnCounter == 0) {
                            queryStringBuffer.append("DepartmentId='" + getDepartmentId() + "' ");
                            columnCounter++;
                        } else if (!"".equals(getDepartmentId()) && columnCounter != 0) {
                            queryStringBuffer.append("AND DepartmentId='" + getDepartmentId() + "' ");
                            setTempDeptId(getDepartmentId());
                        }

                        if (!"".equals(getSubPractice()) && columnCounter == 0) {
                            queryStringBuffer.append("SubPractice='" + getSubPractice() + "' ");
                            columnCounter++;
                        } else if (!"".equals(getSubPractice()) && columnCounter != 0) {
                            queryStringBuffer.append("AND SubPractice='" + getSubPractice() + "' ");
                        }
                        if (!"".equals(getTeamId()) && columnCounter == 0) {
                            queryStringBuffer.append("TeamId='" + getTeamId() + "' ");
                            columnCounter++;
                        } else if (!"".equals(getTeamId()) && columnCounter != 0) {
                            queryStringBuffer.append("AND TeamId='" + getTeamId() + "' ");
                        }
                        if (!"".equals(getEmpno()) && columnCounter == 0) {
                            queryStringBuffer.append("EmpNo=" + getEmpno() + " ");
                            columnCounter++;

                        } else if (!"".equals(getEmpno()) && columnCounter != 0) {
                            queryStringBuffer.append("AND EmpNo=" + getEmpno() + " ");

                        }


                        if ("searchFormMyTeam".equalsIgnoreCase(getSubmitFrom()) && columnCounter == 0) {
                            queryStringBuffer.append(" ReportsTo='" + httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) + "' ");
                            columnCounter++;;
                        } else if ("searchFormMyTeam".equalsIgnoreCase(getSubmitFrom()) && columnCounter != 0) {
                            queryStringBuffer.append(" AND ReportsTo='" + httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) + "' ");
                            columnCounter++;
                        }

                        if (columnCounter == 0) {
                            queryStringBuffer.append(" DeletedFlag != 1 ");
                            columnCounter++;
                        } else if (columnCounter != 0) {
                            queryStringBuffer.append(" AND DeletedFlag != 1 ");
                        }

                        if (getSkillSet() != null && !"".equals(getSkillSet())) {
                            System.out.println("getSkillSet---" + getSkillSet());
                            String skillsREsult = "";
                            String skills = getSkillSet();
                            if (!skills.contains("-")) {
                                skills += "-";
                            }
                            String add = skills.substring(0, skills.indexOf("-"));
                            String remove = skills.substring(skills.indexOf("-") + 1, skills.length());
                            String orCaseSkills[] = add.split(Pattern.quote("|"));
                            queryStringBuffer.append("AND ((");
                            for (int i = 0; i < orCaseSkills.length; i++) {
                                String string = orCaseSkills[i];
                                String skill[] = orCaseSkills[i].split(Pattern.quote("&"));
                                //System.out.println(string);
                                queryStringBuffer.append("(");
                                for (int j = 0; j < skill.length; j++) {
                                    // String string1 = skill[j];
                                    skillsREsult = DataSourceDataProvider.getInstance().getEmpIDsBySkill(skill[j]);
                                    if (skillsREsult.length() > 0) {
                                        queryStringBuffer.append("tblEmployee.Id IN (" + skillsREsult + ")");
                                        if (j < skill.length - 1) {
                                            queryStringBuffer.append(" AND ");
                                        }
                                    }
                                    if (j == skill.length - 1) {
                                        queryStringBuffer.append(")");
                                    }

                                }
                                if (i < orCaseSkills.length - 1) {
                                    queryStringBuffer.append(" OR ");
                                }
                                if (i == orCaseSkills.length - 1) {
                                    queryStringBuffer.append(")");
                                }
                            }

                            String removedSkills[] = remove.split(Pattern.quote("-"));

                            if (removedSkills.length > 0 && !removedSkills[0].equals("")) {
                                queryStringBuffer.append(" AND (");
                                for (int i = 0; i < removedSkills.length; i++) {
                                    String string = removedSkills[i];
                                    //    System.out.print(string);
                                    skillsREsult = DataSourceDataProvider.getInstance().getEmpIDsBySkill(removedSkills[i]);
                                    if (skillsREsult.length() > 0) {
                                        queryStringBuffer.append("tblEmployee.Id NOT IN (" + skillsREsult + ")");
                                        if (i < removedSkills.length - 1) {
                                            queryStringBuffer.append(" AND ");
                                        }
                                    }
                                    if (i == removedSkills.length - 1) {
                                        queryStringBuffer.append(")");
                                    }
                                }
                            }
                            queryStringBuffer.append(")");



                            // queryStringBuffer.append(" " + skillsREsult);
                        }
                        if (getOpsContactId() != null && !"".equals(getOpsContactId()) && columnCounter == 0) {
                            queryStringBuffer.append(" OpsContactId= " + getOpsContactId());
                            columnCounter++;
                        } else if (getOpsContactId() != null && !"1".equals(getOpsContactId()) && columnCounter != 0) {
                            queryStringBuffer.append(" AND OpsContactId= " + getOpsContactId());
                        }
                        if (getCountry() != null && !"".equals(getCountry()) && columnCounter == 0) {
                            queryStringBuffer.append(" Country='" + getCountry() + "' ");
                            columnCounter++;
                        } else if (getCountry() != null && !"".equals(getCountry()) && columnCounter != 0) {
                            queryStringBuffer.append(" AND Country='" + getCountry() + "' ");
                            //  setTempDeptId(getCountry());
                        }
                        if (getPracticeId() != null && !"".equals(getPracticeId()) && columnCounter == 0) {
                            queryStringBuffer.append(" PracticeId='" + getPracticeId() + "' ");
                            columnCounter++;
                        } else if (getPracticeId() != null && !"".equals(getPracticeId()) && columnCounter != 0) {
                            queryStringBuffer.append(" AND PracticeId='" + getPracticeId() + "' ");
                            // setPracticeId(getPracticeId());
                        }
                        if (getLocation() != null && !"".equals(getLocation()) && columnCounter == 0) {
                            queryStringBuffer.append(" Location='" + getLocation() + "' ");
                            columnCounter++;
                        } else if (getLocation() != null && !"".equals(getLocation()) && columnCounter != 0) {
                            queryStringBuffer.append(" AND Location='" + getLocation() + "' ");

                        }

                        if (!"".equals(getItgBatch()) && columnCounter == 0) {
                            queryStringBuffer.append(" Itgbatch='" + getItgBatch() + "' ");
                            columnCounter++;

                        } else if (!"".equals(getItgBatch()) && columnCounter != 0) {
                            queryStringBuffer.append(" AND Itgbatch='" + getItgBatch() + "' ");

                        }
                        queryStringBuffer.append("  ORDER BY trim(FName)");

                        //  System.out.println("queryStringBuffer.toString()---"+queryStringBuffer.toString());
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_EMP_LIST, queryStringBuffer.toString());
                        //System.err.println(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_EMP_LIST));
                        queryStringBuffer.delete(0, queryStringBuffer.capacity());
                    }

                    if ("dbGrid".equalsIgnoreCase(getSubmitFrom())) {
                        queryStringBuffer = new StringBuffer();

                        queryStringBuffer.append("SELECT Id, DepartmentId, LName, FName, MName,EmpNo, Email1, WorkPhoneNo, AlterPhoneNo, CellPhoneNo, CurStatus,SubPractice,TeamId ,LoginId ");
                        queryStringBuffer.append(" FROM tblEmployee  ");
                        // queryStringBuffer.append(" tblLKSubPractice");

                        if (null == getFirstName()) {
                            setFirstName("");
                        }
                        if (null == getLastName()) {
                            setLastName("");
                        }
                        if (null == getWorkPhoneNo()) {
                            setWorkPhoneNo("");
                        }
                        if (null == getCurrStatus()) {
                            setCurrStatus("");
                        }
                        if (null == getEmpno()) {
                            setEmpno("");
                        }

                        if ("All".equalsIgnoreCase(getCurrStatus())) {
                            setCurrStatus("");
                        }
                        if (null == getOrgId()) {
                            setOrgId("");
                        }
                        if ("All".equalsIgnoreCase(getOrgId())) {
                            setOrgId("");
                        }
                        if (null == getReportingpersonId()) {
                            setReportingpersonId("");
                        }
                        if ("All".equalsIgnoreCase(getReportingpersonId())) {
                            setReportingpersonId("");
                        }
                        if (null == getDepartmentId()) {
                            setDepartmentId("");
                        }
                        if (null == getSubPractice()) {
                            setSubPractice("");
                        }
                        if ("All".equalsIgnoreCase(getDepartmentId())) {
                            setDepartmentId("");
                        }
                        if (null == getTeamId()) {
                            setTeamId("");
                        }
                        if ("All".equalsIgnoreCase(getTeamId())) {
                            setTeamId("");
                        }
                        if (null == getCountry()) {
                            setCountry("");
                        }
                        if (null == getPracticeId()) {
                            setPracticeId("");
                        }
                        if ("All".equalsIgnoreCase(getLocation())) {
                            setLocation("");
                        }

                        if (null == getItgBatch()) {
                            setItgBatch("");
                        }


//                if((!"".equals(getFirstName()))
//                || (!"".equals(getLastName()))
//                || (!"".equals(getWorkPhoneNo()))
//                || (!"".equals(getCurrStatus()))
//                ||(!"".equals(getOrgId()))){
//                    queryStringBuffer.append(" WHERE ");
//                }

                        // queryStringBuffer.append(" WHERE ");

                        if ("Admin".equals(userRoleName)) {
                            queryStringBuffer.append("where ");
                        } //                        else {
                        //                            queryStringBuffer.append(" WHERE  Country like '" + livingCountry + "' AND ");
                        //                        }
                        else {
                            if (livingCountry.equalsIgnoreCase("USA")) {
                                queryStringBuffer.append(" WHERE  Country != 'India' AND ");
                            } else {
                                queryStringBuffer.append(" WHERE  Country like 'India' AND ");
                            }

                        }
                        int columnCounter = 0;
                        if (getIsManager() && columnCounter == 0) {
                            setIsManager(getIsManager());

                            queryStringBuffer.append("IsManager =1 ");
                            columnCounter++;
                        } else if (getIsManager() && columnCounter != 0) {
                            setIsManager(getIsManager());
                            queryStringBuffer.append("AND IsManager =1 ");
                        }
                        if (isIsTeamLead() && columnCounter == 0) {
                            setIsTeamLead(isIsTeamLead());

                            queryStringBuffer.append("IsTeamLead =1 ");
                            columnCounter++;
                        } else if (isIsTeamLead() && columnCounter != 0) {
                            setIsTeamLead(isIsTeamLead());
                            queryStringBuffer.append("AND IsTeamLead =1 ");
                        }
                        if (!"".equals(getFirstName()) && columnCounter == 0) {
                            setTempName(getFirstName());
                            if ((getFirstName().indexOf("*") == -1) && (getFirstName().indexOf("%") == -1)) {
                                setFirstName(getFirstName() + "*");
                            }
                            setFirstName(getFirstName().replace("*", "%"));
                            queryStringBuffer.append("(FName LIKE '" + getFirstName() + "%' OR LName LIKE '" + getFirstName() + "') ");
                            columnCounter++;
                        } else if (!"".equals(getFirstName()) && columnCounter != 0) {
                            setTempName(getFirstName());
                            if ((getFirstName().indexOf("*") == -1) && (getFirstName().indexOf("%") == -1)) {
                                setFirstName(getFirstName() + "*");
                            }
                            setFirstName(getFirstName().replace("*", "%"));
                            queryStringBuffer.append("AND (FName LIKE '" + getFirstName() + "' OR LName LIKE '" + getFirstName() + "') ");
                        }
//                if(!"".equals(getLastName()) && columnCounter==0){
//                    queryStringBuffer.append("LName LIKE '" + getLastName() + "%' ");
//                    columnCounter ++;
//                }else if(!"".equals(getLastName()) && columnCounter!=0){
//                    queryStringBuffer.append("AND LName LIKE '" + getLastName() + "%' ");
//                }

                        if (!"".equals(getWorkPhoneNo()) && columnCounter == 0) {
                            setTempPh(getWorkPhoneNo());


                            if ((getWorkPhoneNo().indexOf("*") == -1) && (getWorkPhoneNo().indexOf("%") == -1)) {
                                setWorkPhoneNo(getWorkPhoneNo().replace("*", "%"));
                            }
                            setWorkPhoneNo(getWorkPhoneNo().replace("*", "%"));
                            queryStringBuffer.append("WorkPhoneNo LIKE '" + getWorkPhoneNo() + "' ");

                            columnCounter++;
                        } else if (!"".equals(getWorkPhoneNo()) && columnCounter != 0) {
                            setTempPh(getWorkPhoneNo());
                            if ((getWorkPhoneNo().indexOf("*") == -1) && (getWorkPhoneNo().indexOf("%") == -1)) {
                                setWorkPhoneNo(getWorkPhoneNo().replace("*", "%"));
                            }
                            queryStringBuffer.append("AND WorkPhoneNo LIKE '" + getWorkPhoneNo() + "' ");
                        }


                        if (!"".equals(getCurrStatus()) && columnCounter == 0) {
                            queryStringBuffer.append("CurStatus ='" + getCurrStatus() + "' ");
                            columnCounter++;
                            setTempCurStatus(getCurrStatus());
                        } else if (!"".equals(getCurrStatus()) && columnCounter != 0) {
                            queryStringBuffer.append("AND CurStatus ='" + getCurrStatus() + "' ");
                            setTempCurStatus(getCurrStatus());
                        } else {
                            setTempCurStatus("All");
                        }
                        if (!"".equals(getOrgId()) && columnCounter == 0) {
                            queryStringBuffer.append("OrgId='" + getOrgId() + "' ");
                            columnCounter++;
                            setTempOrgId(getOrgId());
                        } else if (!"".equals(getOrgId()) && columnCounter != 0) {
                            queryStringBuffer.append("AND OrgId='" + getOrgId() + "' ");
                            setTempOrgId(getOrgId());
                        }


                        if (!"".equals(getReportingpersonId()) && columnCounter == 0) {
                            queryStringBuffer.append("reportsTo='" + getReportingpersonId() + "' ");
                            columnCounter++;

                        } else if (!"".equals(getReportingpersonId()) && columnCounter != 0) {
                            queryStringBuffer.append("AND reportsTo='" + getReportingpersonId() + "' ");

                        }

                        if (!"".equals(getDepartmentId()) && columnCounter == 0) {
                            queryStringBuffer.append("DepartmentId='" + getDepartmentId() + "' ");
                            columnCounter++;
                        } else if (!"".equals(getDepartmentId()) && columnCounter != 0) {
                            queryStringBuffer.append("AND DepartmentId='" + getDepartmentId() + "' ");
                            setTempDeptId(getDepartmentId());
                        }

                        if (!"".equals(getSubPractice()) && columnCounter == 0) {
                            queryStringBuffer.append("SubPractice='" + getSubPractice() + "' ");
                            columnCounter++;
                        } else if (!"".equals(getSubPractice()) && columnCounter != 0) {
                            queryStringBuffer.append("AND SubPractice='" + getSubPractice() + "' ");
                        }
                        if (!"".equals(getTeamId()) && columnCounter == 0) {
                            queryStringBuffer.append("TeamId='" + getTeamId() + "' ");
                            columnCounter++;
                        } else if (!"".equals(getTeamId()) && columnCounter != 0) {
                            queryStringBuffer.append("AND TeamId='" + getTeamId() + "' ");
                        }

                        if (!"".equals(getEmpno()) && columnCounter == 0) {
                            queryStringBuffer.append("EmpNo=" + getEmpno() + " ");
                            columnCounter++;

                        } else if (!"".equals(getEmpno()) && columnCounter != 0) {
                            queryStringBuffer.append("AND EmpNo=" + getEmpno() + " ");

                        }
                        if ("searchFormMyTeam".equalsIgnoreCase(getSubmitFrom()) && columnCounter == 0) {
                            queryStringBuffer.append(" ReportsTo='" + httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) + "' ");
                            columnCounter++;
                        } else if ("searchFormMyTeam".equalsIgnoreCase(getSubmitFrom()) && columnCounter != 0) {
                            queryStringBuffer.append(" AND ReportsTo='" + httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) + "' ");
                            columnCounter++;
                        }
                        /*        else if("dbGrid".equalsIgnoreCase(getSubmitFromReportsTo()) && columnCounter==0){
                        queryStringBuffer.append(" ReportsTo='"+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID)+"' ");
                        columnCounter ++;
                        }
                        else if("dbGrid".equalsIgnoreCase(getSubmitFromReportsTo()) && columnCounter!=0){
                        queryStringBuffer.append(" AND ReportsTo='"+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID)+"' ");
                        columnCounter ++;
                        }
                         */
                        if (columnCounter == 0) {
                            queryStringBuffer.append(" DeletedFlag != 1 ");
                            columnCounter++;
                        } else if (columnCounter != 0) {
                            queryStringBuffer.append(" AND DeletedFlag != 1 ");
                        }

                        if (getSkillSet() != null && !"".equals(getSkillSet())) {
                            // System.out.println("getSkillSet---" + getSkillSet());
                            String skillsREsult = "";
                            String skills = getSkillSet();
                            if (!skills.contains("-")) {
                                skills += "-";
                            }
                            String add = skills.substring(0, skills.indexOf("-"));
                            String remove = skills.substring(skills.indexOf("-") + 1, skills.length());
                            String orCaseSkills[] = add.split(Pattern.quote("|"));
                            queryStringBuffer.append("AND ((");
                            for (int i = 0; i < orCaseSkills.length; i++) {
                                String string = orCaseSkills[i];
                                String skill[] = orCaseSkills[i].split(Pattern.quote("&"));
                                //System.out.println(string);
                                queryStringBuffer.append("(");
                                for (int j = 0; j < skill.length; j++) {
                                    // String string1 = skill[j];
                                    skillsREsult = DataSourceDataProvider.getInstance().getEmpIDsBySkill(skill[j]);
                                    if (skillsREsult.length() > 0) {
                                        queryStringBuffer.append("tblEmployee.Id IN (" + skillsREsult + ")");
                                        if (j < skill.length - 1) {
                                            queryStringBuffer.append(" AND ");
                                        }
                                    }
                                    if (j == skill.length - 1) {
                                        queryStringBuffer.append(")");
                                    }

                                }
                                if (i < orCaseSkills.length - 1) {
                                    queryStringBuffer.append(" OR ");
                                }
                                if (i == orCaseSkills.length - 1) {
                                    queryStringBuffer.append(")");
                                }
                            }

                            String removedSkills[] = remove.split(Pattern.quote("-"));

                            if (removedSkills.length > 0 && !removedSkills[0].equals("")) {
                                queryStringBuffer.append(" AND (");
                                for (int i = 0; i < removedSkills.length; i++) {
                                    String string = removedSkills[i];
                                    //    System.out.print(string);
                                    skillsREsult = DataSourceDataProvider.getInstance().getEmpIDsBySkill(removedSkills[i]);
                                    if (skillsREsult.length() > 0) {
                                        queryStringBuffer.append("tblEmployee.Id NOT IN (" + skillsREsult + ")");
                                        if (i < removedSkills.length - 1) {
                                            queryStringBuffer.append(" AND ");
                                        }
                                    }
                                    if (i == removedSkills.length - 1) {
                                        queryStringBuffer.append(")");
                                    }
                                }
                            }
                            queryStringBuffer.append(")");



                            // queryStringBuffer.append(" " + skillsREsult);
                        }
                        if (getOpsContactId() != null && !"".equals(getOpsContactId()) && columnCounter == 0) {
                            queryStringBuffer.append(" OpsContactId= " + getOpsContactId());
                            columnCounter++;
                        } else if (getOpsContactId() != null && !"1".equals(getOpsContactId()) && columnCounter != 0) {
                            queryStringBuffer.append(" AND OpsContactId= " + getOpsContactId());
                        }
                        if (getCountry() != null && !"".equals(getCountry()) && columnCounter == 0) {
                            queryStringBuffer.append(" Country='" + getCountry() + "' ");
                            columnCounter++;
                        } else if (getCountry() != null && !"".equals(getCountry()) && columnCounter != 0) {
                            queryStringBuffer.append(" AND Country='" + getCountry() + "' ");
                            //  setTempDeptId(getCountry());
                        }
                        if (getPracticeId() != null && !"".equals(getPracticeId()) && columnCounter == 0) {
                            queryStringBuffer.append(" PracticeId='" + getPracticeId() + "' ");
                            columnCounter++;
                        } else if (getPracticeId() != null && !"".equals(getPracticeId()) && columnCounter != 0) {
                            queryStringBuffer.append(" AND PracticeId='" + getPracticeId() + "' ");
                            // setPracticeId(getPracticeId());
                        }
                        if (getLocation() != null && !"".equals(getLocation()) && columnCounter == 0) {
                            queryStringBuffer.append(" Location='" + getLocation() + "' ");
                            columnCounter++;
                        } else if (getLocation() != null && !"".equals(getLocation()) && columnCounter != 0) {
                            queryStringBuffer.append(" AND Location='" + getLocation() + "' ");

                        }
                        if (!"".equals(getItgBatch()) && columnCounter == 0) {
                            queryStringBuffer.append(" Itgbatch='" + getItgBatch() + "' ");
                            columnCounter++;

                        } else if (!"".equals(getItgBatch()) && columnCounter != 0) {
                            queryStringBuffer.append(" AND Itgbatch='" + getItgBatch() + "' ");

                        }

                        queryStringBuffer.append("  ORDER BY trim(FName)");

                        // System.out.println("queryStringBuffer------>"+queryStringBuffer.toString());
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_EMP_LIST, queryStringBuffer.toString());

                        queryStringBuffer.delete(0, queryStringBuffer.capacity());

                    }
                    //Calling searchPrepare() method to populate select components

                    // prepare();

                    setFirstName(getTempName());
                    setWorkPhoneNo(getTempPh());
                    setCurrStatus(getTempCurStatus());
                    setDepartmentId(getTempDeptId());
                    setOrgId(getTempOrgId());
                    setReportingpersonId(getReportingpersonId());
                    setIsManager(getIsManager());
                    setIsTeamLead(isIsTeamLead());
                    setEmpno(getEmpno());
                    setCountry(getCountry());
                    setPracticeId(getPracticeId());
                    setLocation(getLocation());
                    setItgBatch(getItgBatch());
                    searchPrepare();
                    // System.err.println(getReportingpersonId());
                    /*httpServletRequest.getSession(false).setAttribute("currStatus",getTempCurStatus());
                    //httpServletRequest.getSession(false).setAttribute("FName",getTempName());
                    httpServletRequest.getSession(false).setAttribute("DeptId",getTempDeptId());
                    httpServletRequest.getSession(false).setAttribute("orgId",getTempOrgId()); */
                    resultType = SUCCESS;
                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        //System.err.println("resultType"+resultType);
        return resultType;
    }

    public String getTeamSearchQuery() {

        // System.out.println("I am in getSearchQuery() +getOrgId());
        resultType = LOGIN;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            resultType = "accessFailed";
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            if (AuthorizationManager.getInstance().isAuthorizedUser("GET_SEARCH_QUERY_EMP", userRoleId)) {
                try {
                    // setPracticeIdList(dataSourceDataProvider.getPracticeByDepartment(getDepartmentId()));
                    // setPracticeId(getCurrentEmployee().getPracticeId());
                    // setSubPractice(getCurrentEmployee().getSubPractice());

                    if ("searchFormAll".equalsIgnoreCase(getSubmitFrom()) || "searchFormMyTeam".equalsIgnoreCase(getSubmitFrom())) {
                        queryStringBuffer = new StringBuffer();

                        queryStringBuffer.append("SELECT Id, DepartmentId, LName, FName, MName, Email1, WorkPhoneNo, AlterPhoneNo, CellPhoneNo, CurStatus,SubPractice,TeamId");
                        queryStringBuffer.append(" FROM tblEmployee ");

                        // queryStringBuffer.append(" tblLKSubPractice");

                        if (null == getFirstName()) {
                            setFirstName("");
                        }
                        if (null == getLastName()) {
                            setLastName("");
                        }
                        if (null == getWorkPhoneNo()) {
                            setWorkPhoneNo("");
                        }
                        if (null == getCurrStatus()) {
                            setCurrStatus("");
                        }
                        if ("All".equalsIgnoreCase(getCurrStatus())) {
                            setCurrStatus("");
                        }
                        if (null == getOrgId()) {
                            setOrgId("");
                        }
                        if ("All".equalsIgnoreCase(getOrgId())) {
                            setOrgId("");
                        }
                        if (null == getDepartmentId()) {
                            setDepartmentId("");
                        }
                        if (null == getSubPractice()) {
                            setSubPractice("");
                        }
                        if ("All".equalsIgnoreCase(getDepartmentId())) {
                            setDepartmentId("");
                        }
                        if (null == getTeamId()) {
                            setTeamId("");
                        }
                        if ("All".equalsIgnoreCase(getTeamId())) {
                            setTeamId("");
                        }




//                if((!"".equals(getFirstName()))
//                || (!"".equals(getLastName()))
//                || (!"".equals(getWorkPhoneNo()))
//                || (!"".equals(getCurrStatus()))
//                ||(!"".equals(getOrgId()))){
//                    queryStringBuffer.append(" WHERE ");
//                }
                        if ("Admin".equals(userRoleName)) {
                            queryStringBuffer.append("where ");
                        } else {
                            queryStringBuffer.append(" WHERE  Country like '" + workingCountry + "' AND ");
                        }
                        int columnCounter = 0;


                        if (!"".equals(getFirstName()) && columnCounter == 0) {
                            setTempName(getFirstName());
                            if ((getFirstName().indexOf("*") == -1) && (getFirstName().indexOf("%") == -1)) {
                                setFirstName(getFirstName() + "*");
                            }
                            setFirstName(getFirstName().replace("*", "%"));
                            queryStringBuffer.append("(FName LIKE '" + getFirstName() + "%' OR LName LIKE '" + getFirstName() + "') ");
                            columnCounter++;
                        } else if (!"".equals(getFirstName()) && columnCounter != 0) {
                            setTempName(getFirstName());
                            if ((getFirstName().indexOf("*") == -1) && (getFirstName().indexOf("%") == -1)) {
                                setFirstName(getFirstName() + "*");
                            }
                            setFirstName(getFirstName().replace("*", "%"));
                            queryStringBuffer.append("AND (FName LIKE '" + getFirstName() + "' OR LName LIKE '" + getFirstName() + "') ");
                        }
//                if(!"".equals(getLastName()) && columnCounter==0){
//                    queryStringBuffer.append("LName LIKE '" + getLastName() + "%' ");
//                    columnCounter ++;
//                }else if(!"".equals(getLastName()) && columnCounter!=0){
//                    queryStringBuffer.append("AND LName LIKE '" + getLastName() + "%' ");
//                }

                        if (!"".equals(getWorkPhoneNo()) && columnCounter == 0) {
                            setTempPh(getWorkPhoneNo());
                            if ((getWorkPhoneNo().indexOf("*") == -1) && (getWorkPhoneNo().indexOf("%") == -1)) {
                                setWorkPhoneNo(getWorkPhoneNo().replace("*", "%"));
                            }
                            setWorkPhoneNo(getWorkPhoneNo().replace("*", "%"));
                            queryStringBuffer.append("WorkPhoneNo LIKE '" + getWorkPhoneNo() + "' ");
                            columnCounter++;
                        } else if (!"".equals(getWorkPhoneNo()) && columnCounter != 0) {
                            setTempPh(getWorkPhoneNo());
                            if ((getWorkPhoneNo().indexOf("*") == -1) && (getWorkPhoneNo().indexOf("%") == -1)) {
                                setWorkPhoneNo(getWorkPhoneNo().replace("*", "%"));
                            }
                            queryStringBuffer.append("AND WorkPhoneNo LIKE '" + getWorkPhoneNo() + "' ");
                        }


                        if (!"".equals(getCurrStatus()) && columnCounter == 0) {
                            queryStringBuffer.append("CurStatus ='" + getCurrStatus() + "' ");
                            columnCounter++;
                            setTempCurStatus(getCurrStatus());
                        } else if (!"".equals(getCurrStatus()) && columnCounter != 0) {
                            queryStringBuffer.append("AND CurStatus ='" + getCurrStatus() + "' ");
                            setTempCurStatus(getCurrStatus());
                        } else {
                            setTempCurStatus("All");
                        }
                        if (!"".equals(getOrgId()) && columnCounter == 0) {
                            queryStringBuffer.append("OrgId='" + getOrgId() + "' ");
                            columnCounter++;
                            setTempOrgId(getOrgId());
                        } else if (!"".equals(getOrgId()) && columnCounter != 0) {
                            queryStringBuffer.append("AND OrgId='" + getOrgId() + "' ");
                            setTempOrgId(getOrgId());
                        }

                        if (!"".equals(getDepartmentId()) && columnCounter == 0) {
                            queryStringBuffer.append("DepartmentId='" + getDepartmentId() + "' ");
                            columnCounter++;
                        } else if (!"".equals(getDepartmentId()) && columnCounter != 0) {
                            queryStringBuffer.append("AND DepartmentId='" + getDepartmentId() + "' ");
                            setTempDeptId(getDepartmentId());
                        }

                        if (!"".equals(getSubPractice()) && columnCounter == 0) {
                            queryStringBuffer.append("SubPractice='" + getSubPractice() + "' ");
                            columnCounter++;
                        } else if (!"".equals(getSubPractice()) && columnCounter != 0) {
                            queryStringBuffer.append("AND SubPractice='" + getSubPractice() + "' ");
                        }
                        if (!"".equals(getTeamId()) && columnCounter == 0) {
                            queryStringBuffer.append("TeamId='" + getTeamId() + "' ");
                            columnCounter++;
                        } else if (!"".equals(getTeamId()) && columnCounter != 0) {
                            queryStringBuffer.append("AND TeamId='" + getTeamId() + "' ");
                        }


                        if ("searchFormMyTeam".equalsIgnoreCase(getSubmitFrom()) && columnCounter == 0) {
                            queryStringBuffer.append(" ReportsTo='" + httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) + "' ");
                            columnCounter++;;
                        } else if ("searchFormMyTeam".equalsIgnoreCase(getSubmitFrom()) && columnCounter != 0) {
                            queryStringBuffer.append(" AND ReportsTo='" + httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) + "' ");
                            columnCounter++;
                        }

                        if (columnCounter == 0) {
                            queryStringBuffer.append(" DeletedFlag != 1 ORDER BY trim(FName)");
                        } else if (columnCounter != 0) {
                            queryStringBuffer.append(" AND DeletedFlag != 1 ORDER BY trim(FName)");
                        }


                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_EMP_LIST, queryStringBuffer.toString());

                        queryStringBuffer.delete(0, queryStringBuffer.capacity());
                    }

                    if ("dbGrid".equalsIgnoreCase(getSubmitFrom())) {
                        queryStringBuffer = new StringBuffer();

                        queryStringBuffer.append("SELECT Id, DepartmentId, LName, FName, MName, Email1, WorkPhoneNo, AlterPhoneNo, CellPhoneNo, CurStatus,SubPractice,TeamId");
                        queryStringBuffer.append(" FROM tblEmployee  ");
                        // queryStringBuffer.append(" tblLKSubPractice");

                        if (null == getFirstName()) {
                            setFirstName("");
                        }
                        if (null == getLastName()) {
                            setLastName("");
                        }
                        if (null == getWorkPhoneNo()) {
                            setWorkPhoneNo("");
                        }
                        if (null == getCurrStatus()) {
                            setCurrStatus("");
                        }
                        if ("All".equalsIgnoreCase(getCurrStatus())) {
                            setCurrStatus("");
                        }
                        if (null == getOrgId()) {
                            setOrgId("");
                        }
                        if ("All".equalsIgnoreCase(getOrgId())) {
                            setOrgId("");
                        }
                        if (null == getDepartmentId()) {
                            setDepartmentId("");
                        }
                        if (null == getSubPractice()) {
                            setSubPractice("");
                        }
                        if ("All".equalsIgnoreCase(getDepartmentId())) {
                            setDepartmentId("");
                        }
                        if (null == getTeamId()) {
                            setTeamId("");
                        }
                        if ("All".equalsIgnoreCase(getTeamId())) {
                            setTeamId("");
                        }




//                if((!"".equals(getFirstName()))
//                || (!"".equals(getLastName()))
//                || (!"".equals(getWorkPhoneNo()))
//                || (!"".equals(getCurrStatus()))
//                ||(!"".equals(getOrgId()))){
//                    queryStringBuffer.append(" WHERE ");
//                }

                        // queryStringBuffer.append(" WHERE ");

                        if ("Admin".equals(userRoleName)) {
                            queryStringBuffer.append("where ReportsTo='" + httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) + "' AND ");
                        } else {
                            queryStringBuffer.append(" WHERE ReportsTo='" + httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) + "' AND Country like '" + workingCountry + "' AND ");
                        }
                        int columnCounter = 0;


                        if (!"".equals(getFirstName()) && columnCounter == 0) {
                            setTempName(getFirstName());
                            if ((getFirstName().indexOf("*") == -1) && (getFirstName().indexOf("%") == -1)) {
                                setFirstName(getFirstName() + "*");
                            }
                            setFirstName(getFirstName().replace("*", "%"));
                            queryStringBuffer.append("(FName LIKE '" + getFirstName() + "%' OR LName LIKE '" + getFirstName() + "') ");
                            columnCounter++;
                        } else if (!"".equals(getFirstName()) && columnCounter != 0) {
                            setTempName(getFirstName());
                            if ((getFirstName().indexOf("*") == -1) && (getFirstName().indexOf("%") == -1)) {
                                setFirstName(getFirstName() + "*");
                            }
                            setFirstName(getFirstName().replace("*", "%"));
                            queryStringBuffer.append("AND (FName LIKE '" + getFirstName() + "' OR LName LIKE '" + getFirstName() + "') ");
                        }
//                if(!"".equals(getLastName()) && columnCounter==0){
//                    queryStringBuffer.append("LName LIKE '" + getLastName() + "%' ");
//                    columnCounter ++;
//                }else if(!"".equals(getLastName()) && columnCounter!=0){
//                    queryStringBuffer.append("AND LName LIKE '" + getLastName() + "%' ");
//                }

                        if (!"".equals(getWorkPhoneNo()) && columnCounter == 0) {
                            setTempPh(getWorkPhoneNo());


                            if ((getWorkPhoneNo().indexOf("*") == -1) && (getWorkPhoneNo().indexOf("%") == -1)) {
                                setWorkPhoneNo(getWorkPhoneNo().replace("*", "%"));
                            }
                            setWorkPhoneNo(getWorkPhoneNo().replace("*", "%"));
                            queryStringBuffer.append("WorkPhoneNo LIKE '" + getWorkPhoneNo() + "' ");

                            columnCounter++;
                        } else if (!"".equals(getWorkPhoneNo()) && columnCounter != 0) {
                            setTempPh(getWorkPhoneNo());
                            if ((getWorkPhoneNo().indexOf("*") == -1) && (getWorkPhoneNo().indexOf("%") == -1)) {
                                setWorkPhoneNo(getWorkPhoneNo().replace("*", "%"));
                            }
                            queryStringBuffer.append("AND WorkPhoneNo LIKE '" + getWorkPhoneNo() + "' ");
                        }


                        if (!"".equals(getCurrStatus()) && columnCounter == 0) {
                            queryStringBuffer.append("CurStatus ='" + getCurrStatus() + "' ");
                            columnCounter++;
                            setTempCurStatus(getCurrStatus());
                        } else if (!"".equals(getCurrStatus()) && columnCounter != 0) {
                            queryStringBuffer.append("AND CurStatus ='" + getCurrStatus() + "' ");
                            setTempCurStatus(getCurrStatus());
                        } else {
                            setTempCurStatus("All");
                        }
                        if (!"".equals(getOrgId()) && columnCounter == 0) {
                            queryStringBuffer.append("OrgId='" + getOrgId() + "' ");
                            columnCounter++;
                            setTempOrgId(getOrgId());
                        } else if (!"".equals(getOrgId()) && columnCounter != 0) {
                            queryStringBuffer.append("AND OrgId='" + getOrgId() + "' ");
                            setTempOrgId(getOrgId());
                        }

                        if (!"".equals(getDepartmentId()) && columnCounter == 0) {
                            queryStringBuffer.append("DepartmentId='" + getDepartmentId() + "' ");
                            columnCounter++;
                        } else if (!"".equals(getDepartmentId()) && columnCounter != 0) {
                            queryStringBuffer.append("AND DepartmentId='" + getDepartmentId() + "' ");
                            setTempDeptId(getDepartmentId());
                        }

                        if (!"".equals(getSubPractice()) && columnCounter == 0) {
                            queryStringBuffer.append("SubPractice='" + getSubPractice() + "' ");
                            columnCounter++;
                        } else if (!"".equals(getSubPractice()) && columnCounter != 0) {
                            queryStringBuffer.append("AND SubPractice='" + getSubPractice() + "' ");
                        }
                        if (!"".equals(getTeamId()) && columnCounter == 0) {
                            queryStringBuffer.append("TeamId='" + getTeamId() + "' ");
                            columnCounter++;
                        } else if (!"".equals(getTeamId()) && columnCounter != 0) {
                            queryStringBuffer.append("AND TeamId='" + getTeamId() + "' ");
                        }
                        if (columnCounter == 0) {
                            queryStringBuffer.append(" DeletedFlag != 1 ORDER BY trim(FName)");
                        } else if (columnCounter != 0) {
                            queryStringBuffer.append(" AND DeletedFlag != 1 ORDER BY trim(FName)");
                        }

                        //System.out.println("queryStringBuffer------>"+queryStringBuffer);
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_EMP_LIST, queryStringBuffer.toString());

                        queryStringBuffer.delete(0, queryStringBuffer.capacity());

                    }
                    //Calling searchPrepare() method to populate select components
                    searchPrepare();
                    prepare();
                    setFirstName(getTempName());
                    setWorkPhoneNo(getTempPh());
                    setCurrStatus(getTempCurStatus());
                    setDepartmentId(getTempDeptId());
                    setOrgId(getTempOrgId());
                    /*httpServletRequest.getSession(false).setAttribute("currStatus",getTempCurStatus());
                    //httpServletRequest.getSession(false).setAttribute("FName",getTempName());
                    httpServletRequest.getSession(false).setAttribute("DeptId",getTempDeptId());
                    httpServletRequest.getSession(false).setAttribute("orgId",getTempOrgId()); */
                    resultType = SUCCESS;
                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }

    /**
     * The getEmployee() is used for retrieving the employee details .
     *
     * @throws Exception
     * @return String variable for navigation.
     */
    public String getEmployee() {
        resultType = LOGIN;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("GET_EMPLOYEE", userRoleId)) {
                try {
                    setCurrentEmployee(ServiceLocator.getEmployeeService().getEmployee(getEmpId(), getCurrId()));
                    setId(getCurrentEmployee().getId());
                    setLoginId(getCurrentEmployee().getLoginId());
                    //Calling prepare() method to populate select components
                    setDepartmentId(getCurrentEmployee().getDepartmentId());
                    setPracticeId(getCurrentEmployee().getPracticeId());
                    setSubPractice(getCurrentEmployee().getSubPractice());
                    prepare();
                    resultType = SUCCESS;
                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());


                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }

    /**
     * The deleteEmployee() is used for deleting purticular the employee details
     * .
     *
     * @throws Exception
     * @return String variable for navigation.
     */
    public String deleteEmployee() {
        resultType = LOGIN;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("DELETE_EMPLOYEE", userRoleId)) {
                try {
                    int deletedRows = ServiceLocator.getEmployeeService().deleteEmployee(this.getEmpId());

                    if (deletedRows == 1) {
                        setDeleteAction(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_DELETE_ACTION).toString());
                        resultType = SUCCESS;
                        resultMessage = "<font color=\"green\" size=\"1.5\">Employee has been successfully Deleted!</font>";
                    } else {
                        resultType = INPUT;
                        resultMessage = "<font color=\"red\" size=\"1.5\">Sorry! Please Try again!</font>";
                    }
                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, resultMessage);
                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }

    /**
     * The doEdit() is used for editing purticular the employee details .
     *
     * @throws Exception
     * @return String variable for navigation.
     */
    public String doEdit() {
        resultType = LOGIN;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("DO_EDIT_EMPLOYEE", userRoleId)) {
                try {
                    employeeService = ServiceLocator.getEmployeeService();

                    String userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                    setModifiedBy(userId);

                    setModifiedDate(DateUtility.getInstance().getCurrentMySqlDateTime());
                    //System.out.println("Working Onsite------>"+getIsOnsite().toString());
                    if ("Registered".equalsIgnoreCase(getPreCurrStatus())) {
                        setCreatedBy(userId);
                        setCreatedDate(DateUtility.getInstance().getCurrentMySqlDateTime());
                    }
                    if (!"".equals(getPrvexpYears().trim())) {
                        setPrvexpYears(getPrvexpYears() + "yr");
                    } else {
                        setPrvexpYears("0yr");

                    }
                    if (!"".equals(getPrvexpMnths().trim())) {
                        setPrvexpMnths(getPrvexpMnths() + "m");
                    } else {
                        setPrvexpMnths("0m");

                    }
                    // System.out.println("Location---->"+getLocation());
                    boolean isUpdateUser = employeeService.updateEmployee(this);

                    if (isUpdateUser) {
                        boolean isSendMail = false;
                        if ("Registered".equalsIgnoreCase(getPreCurrStatus()) && !("Registered".equalsIgnoreCase(getCurrStatus()))) {
                            isSendMail = employeeService.sendMail(this.getId());
                            int isertedRows = employeeService.insertDefaultRoles(this.getId(), this.getDepartmentId());
                        }
                        setEmpId(getId());
                        resultMessage = "<font color=\"green\" size=\"1.5\">Employee has been successfully updated!</font>";
                        resultType = SUCCESS;
                    } else {
                        resultType = INPUT;
                        resultMessage = "<font color=\"red\" size=\"1.5\">Sorry! Please Try again!</font>";
                    }

                    httpServletRequest.setAttribute("resultMessage", resultMessage);
                    setCurrentEmployee(employeeService.getEmployeeVTO(this));
                    prepare();
                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }

    /**
     * Updated by vkandregula on 08132013
     * 
     * Updated By Teja Kadamanti on 09/23/2016
     */
    public String doUpdateState() {
        resultType = LOGIN;
        int avaiable = 0;
        int stateCount = 0;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";

            if (AuthorizationManager.getInstance().isAuthorizedUser("DO_UPDATE_STATE_EMP", userRoleId)) {


                try {

                    int result = DataSourceDataProvider.getInstance().getStatusDateCheck(getId(), getStateStartDate());


                    String flagVar = "";
                    java.sql.Date hireDate = null;
                    hireDate = dataSourceDataProvider.getInstance().getHireDateOfEmployeeBeforeAddingCurrentStatus(getId());
                    if (hireDate == null || ("1950-01-31".equals(DateUtility.getInstance().convertDateToStringPayroll(hireDate)))) {
                        setNavId(0);
                        resultType = SUCCESS;
                        resultMessage = "<font size='2' color='red'> Employee HireDate is not updated. Please update it !</font>";
                        httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, resultMessage);
                        flagVar = "add";

                    }
                    if (hireDate != null && "".equals(flagVar)) {
                        if (getStateStartDate().compareTo(hireDate) < 0) {
                            setNavId(0);
                            resultType = SUCCESS;
                            resultMessage = "<font size='2' color='red'>  Start Date should be greater than or equal to Employee HireDate'" + com.mss.mirage.util.DateUtility.getInstance().convertToviewFormat(hireDate.toString()) + "' !</font>";
                            httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, resultMessage);
                            flagVar = "add";
                        }
                    }


                    if ("Available".equals(getEmpState())) {
                        stateCount = DataSourceDataProvider.getInstance().getAvailableState(getId());
                        //  System.out.println("availableState"+availableState);
                    }
                    employeeService = ServiceLocator.getEmployeeService();
                    boolean isHistoryInserted = false;
                    int updatedRows = 0;
                    setCurrentEmployeeState(employeeService.getStateVTO(this));
                    if ("".equals(flagVar)) {
                        if (getTempVar() == 2) {
                            setCreatedDate(DateUtility.getInstance().getCurrentMySqlDateTime());
                            if (stateCount > 0) {
                                // resultMessage = "<font color=\"red\" size=\"2\">Sorry! Available record is already in Active state for this employee you can add new record by completing the prevoius one. </font>";
                                resultMessage = "<font color=\"red\" size=\"2\">Sorry! Available record is already in Active state for this employee you can add only one Active Available record . </font>";
                                httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, resultMessage);
                            } else {
                                if (result > 0 && "Available".equals(getEmpState())) {
                                    resultMessage = "<font color=\"red\" size=\"2\">Start Date must be greater than previous status dates</font>";
                                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, resultMessage);
                                } else {
                                    setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                                    if ("Available".equals(getEmpState())) {
                                        List projectList = DataSourceDataProvider.getInstance().getProjectsListByContactId(getId());
                                        avaiable = projectList.size();
                                        String loginIdEmp = DataSourceDataProvider.getInstance().getLoginIdByEmpId(getId());

                                        if (getUpload() != null) {
                                            if (getUploadFileName() != null && !getUploadFileName().equals("")) {

                                                String basePath = Properties.getProperty("EmpResume.Attachments");
                                                //  File createPath = new File(basePath);
                                                String theFilePath = FileUploadUtility.getInstance().filePathGeneration(basePath);
                                                String theFileName = FileUploadUtility.getInstance().fileNameGeneration(getUploadFileName());
                                                File theFile = new File(theFilePath + "//" + loginIdEmp + "//" + theFileName);
                                                setFilepath(theFile.toString());
                                                /*copies the file to the destination*/
                                                //   System.err.println("Here..."+getUpload());
                                                FileUtils.copyFile(getUpload(), theFile);
                                                //   System.err.println("Here...1");
                                                //boolean isInsert=ServiceLocator.getConsultantService().attachResume1(this);


                                            }
                                        }



                                        if (avaiable == 0) {
                                            isHistoryInserted = employeeService.insertStateHistory(this);
                                        }
                                    } else {
                                        isHistoryInserted = employeeService.insertStateHistory(this);
                                    }

                                    resultMessage = "<font color=\"green\" size=\"2\">Employee State has been successfully updated!</font>";
                                    setProjectName("");
                                    setSkillSet("");
                                    setComments("");
                                    if (avaiable > 0) {
                                        resultMessage = "<font color=\"red\" size=\"2\">this employee already allocated to project please contact to PMO!</font>";
                                    }
                                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, resultMessage);
                                }
                            }
                            resultType = SUCCESS;
                        } else if (getTempVar() == 1) {

                            setModifiedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                            setModifiedDate(DateUtility.getInstance().getCurrentMySqlDateTime());
                            getCurrentEmployeeState().setModifiedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                            getCurrentEmployeeState().setModifiedDate(DateUtility.getInstance().getCurrentMySqlDateTime());
                            if (getCurrId() == 0) {
                                boolean isUpdateState = employeeService.updateEmployeeState(getCurrentEmployeeState());

                                if (isUpdateState) {
                                    resultMessage = "<font color=\"green\" size=\"2\">Employee State has been successfully updated!</font>";
                                    setNavId(0);
                                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, resultMessage);
                                    resultType = SUCCESS;

                                    setCreatedDate(DateUtility.getInstance().getCurrentMySqlDateTime());
                                    //int recordId = employeeService.getRecentStateHistoryId(getLoginId());
                                    //if(recordId == 0) {
                                    isHistoryInserted = employeeService.insertStateHistory(this);
                                    //}else {
                                    //    updatedRows = employeeService.updateStateHistory(this,recordId);
                                    //}
                                } else {
                                    setNavId(0);
                                    resultType = INPUT;
                                    resultMessage = "<font color=\"red\" size=\"2\">Sorry! Please Try again!</font>";
                                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, resultMessage);
                                }
                            } else {
                                setCreatedDate(DateUtility.getInstance().getCurrentMySqlDateTime());

                                if ("Available".equals(getEmpState())) {
                                    String loginIdEmp = DataSourceDataProvider.getInstance().getLoginIdByEmpId(getId());
                                    if (getUpload() != null) {
                                        if (getUploadFileName() != null && !getUploadFileName().equals("")) {

                                            String basePath = Properties.getProperty("EmpResume.Attachments");
                                            //  File createPath = new File(basePath);
                                            String theFilePath = FileUploadUtility.getInstance().filePathGeneration(basePath);
                                            String theFileName = FileUploadUtility.getInstance().fileNameGeneration(getUploadFileName());
                                            File theFile = new File(theFilePath + "//" + loginIdEmp + "//" + theFileName);
                                            setFilepath(theFile.toString());
                                            /*copies the file to the destination*/
                                            //   System.err.println("Here..."+getUpload());
                                            FileUtils.copyFile(getUpload(), theFile);
                                            //   System.err.println("Here...1");
                                            //boolean isInsert=ServiceLocator.getConsultantService().attachResume1(this);


                                        }
                                    }

                                }



                                updatedRows = employeeService.updateStateHistory(this, getCurrId());
                                if (updatedRows == 1) {
                                    setNavId(0);
                                    resultMessage = "<font color=\"green\" size=\"2\">Employee State has been successfully updated!</font>";
                                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, resultMessage);
                                    resultType = SUCCESS;
                                } else {
                                    setNavId(0);
                                    resultType = INPUT;
                                    resultMessage = "<font color=\"red\" size=\"2\">Sorry! Please Try again!</font>";
                                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, resultMessage);
                                }
                            }
                        }
                    }
                    setCurrId(0);
                    setCurrentEmployee(employeeService.getEmployee(getCurrentEmployeeState().getEmpId(), getCurrId()));
                    setDepartmentId(getCurrentEmployee().getDepartmentId());
                    setEmpId(getId());


                    setResultMessage(resultMessage);
                    prepare();

                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }

    public String deleteEmpStatus() {
        resultType = LOGIN;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("DO_UPDATE_STATE_EMP", userRoleId)) {
                try {
                    employeeService = ServiceLocator.getEmployeeService();
                    setCurrentEmployeeState(employeeService.getStateVTO(this));
                    int isDelete = employeeService.deleteEmpStatus(getEmpId(), getCurrId());
                    if (isDelete == 1) {
                        resultMessage = "<font color=\"green\" size=\"1.5\">Employee State has been successfully deleted!</font>";
                        httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, resultMessage);
                        resultType = SUCCESS;
                    } else {
                        resultType = INPUT;
                        resultMessage = "<font color=\"red\" size=\"1.5\">Sorry! Please Try again!</font>";
                        httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, resultMessage);
                    }
                    /*setCurrId(0);
                    setCurrentEmployee(employeeService.getEmployee(getEmpId(),getCurrId()));
                    setDepartmentId(getCurrentEmployee().getDepartmentId());
                    prepare();*/
                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }

            }
        }
        return resultType;
    }

    /**
     * The execute() is used for executing business logic.
     *
     * @throws Exception
     * @return Success string
     */
    //modification
   /* public String execute()throws FileNotFoundException,ServletException {
    InputStream imageFile = new FileInputStream(getImagePath());
    Connection connection = null;
    PreparedStatement statement = null; 
    try {
    
    connection = ConnectionProvider.getInstance().getConnection();
    statement = connection.prepareStatement("update tblEmployee set Image=? where LoginId='vkandreula'");
    System.err.print("Updating iamge");
    statement.setBinaryStream(1,imageFile, (int) imagePath.length());
    //statement.setString(2, (String) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID));
    
    //statement.setString(2,getCurrentEmployee());
    int update = statement.executeUpdate();
    //getSkills();
    getEmployee();
    if(update==1){
    resultMessage = "Employee Image Updated Successfully!";
    }else{
    resultMessage = "Please Try again!";
    }
    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
    resultType = SUCCESS;
    } catch (Exception ex) {
    httpServletRequest.getSession(false).setAttribute("errorMessage",ex);
    resultType=ERROR;
    }finally {
    try{
    if(statement!=null){
    statement.close();
    statement=null;
    }
    if(connection!=null){
    connection.close();
    connection=null;
    }
    }catch(SQLException sqle){
    httpServletRequest.getSession(false).setAttribute("errorMessage",sqle);
    }
    }
    return resultType;
    //return SUCCESS;
    }*/
    //Original
    /**
     * The execute() is used for executing business logic.
     *
     * @throws Exception
     * @return Success string
     */
    //new
    public String doImageUpdate() throws FileNotFoundException, ServletException {
// String basePath = Properties.getProperty("EmpImages.path");
//            File createPath = new File(basePath);
//            System.out.println(getImagePath()+"........"+createPath);


        Connection connection = null;
        PreparedStatement statement = null;
        String fileName = "";
        int userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());

        try {
            employeeService = ServiceLocator.getEmployeeService();
            connection = ConnectionProvider.getInstance().getConnection();

//             int length = (int) imagePath.length();
//            System.out.println("....."+length);

            InputStream imageFile = new FileInputStream(getImagePath());
            String basePath = Properties.getProperty("EmpImages.path");
            File createPath = new File(basePath);
            System.out.println("createPath..." + createPath);
            File newFile = new File(createPath.getAbsolutePath() + "//" + getLoginId() + ".png");
            //  newFile.mkdirs();
            String filepath = newFile.toString();
            fileName = getLoginId() + ".png";
            //  System.out.println("filepath--->" + filepath);
            //  System.out.println("fileName--->" + fileName);
            OutputStream fileOutputStream = new FileOutputStream(
                    newFile);
            int bufferSize;
            byte[] bufffer = new byte[512];
            while ((bufferSize = imageFile.read(bufffer)) > 0) {
                fileOutputStream.write(bufffer, 0, bufferSize);
            }
            String ImageDetails = Properties.getProperty("EmpImages.path").trim() + "/" + getLoginId() + ".png";
            statement = connection.prepareStatement("update tblEmployee SET FileName = ? , FilePath = ? where LoginId=?");
            statement.setString(1, fileName);
            statement.setString(2, ImageDetails);
            String lid = getLoginId();
            statement.setString(3, lid);
            int update = statement.executeUpdate();
            fileOutputStream.close();
//                    int blobLength = (int) test.length();
//                    byte[] blobAsBytes = test.getBytes(1, blobLength);


            setEmpId(getId());
            setCurrId(0);
            setCurrentEmployee(employeeService.getEmployee(getEmpId(), getCurrId()));
            setDepartmentId(getCurrentEmployee().getDepartmentId());

            prepare();
            if (update == 1) {
                resultMessage = "<font color=\"green\" size=\"1.5\">Employee Image Updated Successfully! </font>";
            } else {
                resultMessage = "<font color=\"green\" size=\"1.5\">Please Try again! </font>";
            }
            // httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
            httpServletRequest.setAttribute("resultMessage", resultMessage);

            resultType = SUCCESS;

        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage", ex);
            resultType = ERROR;
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                httpServletRequest.getSession(false).setAttribute("errorMessage", sqle);
            }
        }
        return resultType;
    }

    public String execute() {

        return SUCCESS;
    }
    /*   public String getPMOActivity() {
    resultType = LOGIN;
    
    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
    userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
    resultType = "accessFailed";
    System.out.println("role"+userRoleId);
    String objectId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
    if(AuthorizationManager.getInstance().isAuthorizedUser("GET_PMO_ACTIVITY",userRoleId)){
    try{
    setMyProjects(new HashMap());
    // setMyProjects(dataSourceDataProvider.getInstance().getMyProjectList(Integer.parseInt(objectId)));
    setMyAccounts(dataSourceDataProvider.getInstance().getMyCrmAccountList(Integer.parseInt(objectId)));
    String empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
    
    // String empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
    if(getSubmitFrom()==null){
    queryString = "SELECT ProjectName,AccountId,StartDate,tblProjectContacts.STATUS,TotalResources,NAME,ProjectId "
    + "FROM tblProjectContacts LEFT OUTER JOIN tblProjects ON "
    + "(tblProjectContacts.ProjectId = tblProjects.Id) "
    + "LEFT OUTER JOIN tblCrmAccount "
    + "ON (tblCrmAccount.Id = tblProjectContacts.AccountId) "
    + "WHERE ObjectId = "+empId+" AND IsPMO =1 AND ResourceTitle!=8";
    
    // queryString = queryString+" FROM tblEmployee WHERE ReportsTo='"+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID)+"' AND DeletedFlag != 1 ORDER BY CurStatus,LName";
    //setSubmitFrom("searchFormMyTeam");
    // httpServletRequest.getSession(false).setAttribute(ApplicationConstants.SESSION_PMO_ACTIVITY,"empSearchMyTeam");
    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.SESSION_PMO_ACTIVITY,queryString);
    }
    // searchPrepare();
    resultType = SUCCESS;
    }catch (Exception ex){
    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
    resultType =  ERROR;
    }
    }//END-Authorization Checking
    }//Close Session Checking
    return resultType;
    }
    
    
    
    public String pmoActivitySearch() {
    resultType = LOGIN;
    
    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
    userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
    resultType = "accessFailed";
    //  System.out.println("role"+userRoleId);
    if(AuthorizationManager.getInstance().isAuthorizedUser("GET_PMO_ACTIVITY",userRoleId)){
    try{
    String empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
    if(getSubmitFrom()==null){
    queryString = "SELECT ProjectName,StartDate,AccountId,tblProjectContacts.STATUS,TotalResources,NAME,ProjectId FROM tblProjectContacts LEFT OUTER JOIN tblProjects ON (tblProjectContacts.ProjectId = tblProjects.Id) LEFT OUTER JOIN tblCrmAccount ON (tblCrmAccount.Id = tblProjectContacts.AccountId)  ";
    
    // queryString = queryString+" FROM tblEmployee WHERE ReportsTo='"+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID)+"' AND DeletedFlag != 1 ORDER BY CurStatus,LName";
    //setSubmitFrom("searchFormMyTeam");
    // httpServletRequest.getSession(false).setAttribute(ApplicationConstants.SESSION_PMO_ACTIVITY,"empSearchMyTeam");
    
    //if((getProjectName()!=null && !"".equals(getProjectName())) || (getStatus()!=null && !"".equals(getStatus())) || (getStartDate()!=null && !"".equals(getStartDate())))
    
    queryString = queryString + "  WHERE ObjectId = "+empId+" AND IsPMO =1 AND ResourceTitle!=8 ";
    
    //    int count = 0;
    
    if(getProjectName()!=null && !"".equals(getProjectName()))
    {
    queryString = queryString + "AND ProjectName LIKE '"+getProjectName()+"%' ";
    // count++;
    }
    if(getStatus()!=null && !"".equals(getStatus()))
    {
    //  if(count==0)
    //  queryString = queryString + "Status LIKE '"+getStatus()+"%' ";
    //  else
    queryString = queryString + "AND tblProjectContacts.Status LIKE '"+getStatus()+"%' ";
    //  count++;
    }
    
    if(getStartDate()!=null && !"".equals(getStartDate()))
    {
    // if(count==0)
    // queryString = queryString + "date(StartDate) = '"+DateUtility.getInstance().convertStringToMySQLDate(getStartDate())+"' ";
    // else
    queryString = queryString + "AND date(StartDate) = '"+DateUtility.getInstance().convertStringToMySQLDate(getStartDate())+"' ";
    }
    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.SESSION_PMO_ACTIVITY,queryString);
    }
    // searchPrepare();
    resultType = SUCCESS;
    }catch (Exception ex){
    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
    resultType =  ERROR;
    }
    }//END-Authorization Checking
    }//Close Session Checking
    return resultType;
    }*/

    public String getPMOActivity() {
        resultType = LOGIN;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            System.out.println("role" + userRoleId);

            String objectId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
            if (AuthorizationManager.getInstance().isAuthorizedUser("GET_PMO_ACTIVITY", userRoleId)) {
                try {
                    setMyProjects(new HashMap());
                    // setMyProjects(dataSourceDataProvider.getInstance().getMyProjectList(Integer.parseInt(objectId)));
                    setMyAccounts(dataSourceDataProvider.getInstance().getMyCrmAccountList(Integer.parseInt(objectId)));
                    String empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
                    setPracticeList(DataSourceDataProvider.getInstance().getPracticeByDepartment("GDC"));
                    setStatus("Active");
                    setClientMap(DataSourceDataProvider.getInstance().getCustomerMap());
                    if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_ADMIN_ACCESS).toString().equals("1")) || (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_PMO_ACTIVITY_ACCESS).toString().equals("1"))) {
                        setMyTeamMembers(DataSourceDataProvider.getInstance().getPmoMap());
                    } else if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_TEAM_LEAD).toString().equals("1") || httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_USER_MANAGER).toString().equals("1")) {
                        setMyTeamMembers((Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
                        getMyTeamMembers().put(loginId, httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME).toString());

                    }
                    // searchPrepare();
                    resultType = SUCCESS;
                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }

    public String pmoActivitySearch() {
        resultType = LOGIN;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            //  System.out.println("role"+userRoleId);
            if (AuthorizationManager.getInstance().isAuthorizedUser("GET_PMO_ACTIVITY", userRoleId)) {
                try {

                    String empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
                    if (getSubmitFrom() == null) {
                        //change  queryString = "SELECT ProjectName,StartDate,AccountId,tblProjectContacts.STATUS,TotalResources,NAME,ProjectId FROM tblProjectContacts LEFT OUTER JOIN tblProjects ON (tblProjectContacts.ProjectId = tblProjects.Id) LEFT OUTER JOIN tblCrmAccount ON (tblCrmAccount.Id = tblProjectContacts.AccountId)  ";

                        // queryString = queryString+" FROM tblEmployee WHERE ReportsTo='"+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID)+"' AND DeletedFlag != 1 ORDER BY CurStatus,LName";
                        //setSubmitFrom("searchFormMyTeam");
                        // httpServletRequest.getSession(false).setAttribute(ApplicationConstants.SESSION_PMO_ACTIVITY,"empSearchMyTeam");

                        //if((getProjectName()!=null && !"".equals(getProjectName())) || (getStatus()!=null && !"".equals(getStatus())) || (getStartDate()!=null && !"".equals(getStartDate())))

                        //chaneg queryString = queryString + "  WHERE ObjectId = "+empId+" AND IsPMO =1 AND ResourceTitle!=8 ";
                        queryString = "SELECT ProjectName,AccountId,tblProjects.ProjectStartDate,tblPmoAuthors.Status,TotalResources,NAME,ProjectId "
                                + "FROM tblPmoAuthors LEFT OUTER JOIN tblProjects ON (tblPmoAuthors.ProjectId = tblProjects.Id) "
                                + "LEFT OUTER JOIN tblCrmAccount ON (tblCrmAccount.Id = tblPmoAuthors.AccountId) WHERE AuthorId = '" + loginId + "'";

                        //    int count = 0;

                        if (getProjectName() != null && !"".equals(getProjectName())) {
                            queryString = queryString + "AND ProjectName LIKE '%" + getProjectName() + "%' ";
                            // count++;
                        }
                        if (getStatus() != null && !"".equals(getStatus())) {
                            //  if(count==0)
                            //  queryString = queryString + "Status LIKE '"+getStatus()+"%' ";
                            //  else
                            queryString = queryString + "AND tblProjects.Status LIKE '" + getStatus() + "%' ";
                            //  count++;
                        }

                        if (getStartDate() != null && !"".equals(getStartDate())) {
                            // if(count==0)
                            // queryString = queryString + "date(StartDate) = '"+DateUtility.getInstance().convertStringToMySQLDate(getStartDate())+"' ";
                            // else
                            queryString = queryString + "AND date(ProjectStartDate) = '" + DateUtility.getInstance().convertStringToMySQLDate(getStartDate()) + "' ";
                        }
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.SESSION_PMO_ACTIVITY, queryString);
                    }
                    // searchPrepare();
                    resultType = SUCCESS;
                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }

    public String viewProjectTeam() {
        resultType = LOGIN;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            //System.out.println("workingCountry----->"+workingCountry);
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("GET_EMP_SEARCH_ALL", userRoleId)) {
                try {
                    if (getSubmitFrom() == null) {

                        queryString = "SELECT tblProjects.ProjectName,tblProjectContacts.Id AS Id,AccountId,ProjectId,ObjectId,ResourceName AS EmpName ,Email,ResourceTitle,ObjectType,CASE WHEN (Billable=1) THEN  'Yes' ELSE 'No' END AS Billable,tblProjectContacts.Status ,"
                                + "tblProjectContacts.StartDate,tblProjectContacts.Utilization FROM tblProjectContacts LEFT OUTER JOIN tblProjects ON (tblProjectContacts.ProjectId=tblProjects.Id)";
                        queryString = queryString + " WHERE tblProjectContacts.ProjectId =" + getProjectId() + " AND tblProjectContacts.STATUS IN('Active')";


                        //System.err.println("Before");
                        //  setReportingPersons(dataSourceDataProvider.getEmployeeNamesByReportingPerson());
                        // httpServletRequest.getSession(false).setAttribute(ApplicationConstants.SESSION_DELETE_ACTION,"empSearchAll");
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_PROJECT_TEAM_LIST, queryString);
                    }
                    setProjectTeamReportsTo(DataSourceDataProvider.getInstance().getProjectReportsTo(Integer.parseInt(getAccountId()), Integer.parseInt(getProjectId())));
                    // dataSourceDataProvider = DataSourceDataProvider.getInstance();
                    //  setSubmitFrom("searchFormAll");
                    setCurrStatus("Active");
                    setCustomerName(customerName);
                    // searchPrepare();
                    // prepare();
                    resultType = SUCCESS;
                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }

    public String getProjectTeamQuery() {

        // System.out.println("I am in getSearchQuery() +getOrgId());
        resultType = LOGIN;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            resultType = "accessFailed";
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            if (AuthorizationManager.getInstance().isAuthorizedUser("GET_SEARCH_QUERY_EMP", userRoleId)) {
                try {
                    // setPracticeIdList(dataSourceDataProvider.getPracticeByDepartment(getDepartmentId()));
                    // setPracticeId(getCurrentEmployee().getPracticeId());
                    // setSubPractice(getCurrentEmployee().getSubPractice());

                    //System.err.println(getSubmitFrom());
                    setCustomerName(customerName);
                    setProjectName(DataSourceDataProvider.getInstance().getProjectName(Integer.parseInt(getProjectId())));
                    //  if("searchFormAll".equalsIgnoreCase(getSubmitFrom()) || "searchFormMyTeam".equalsIgnoreCase(getSubmitFrom())){
                    queryStringBuffer = new StringBuffer();
                    setProjectTeamReportsTo(DataSourceDataProvider.getInstance().getProjectReportsTo(Integer.parseInt(getAccountId()), Integer.parseInt(getProjectId())));
                    // queryStringBuffer.append("SELECT tblProjects.ProjectName,tblProjectContacts.Id AS Id,AccountId,ProjectId,ObjectId,ResourceName AS EmpName ,Email,ResourceTitle,ObjectType,CASE WHEN (Billable=1) THEN  'Yes' ELSE 'No' END AS Billable,tblProjectContacts.Status     FROM tblProjectContacts LEFT OUTER JOIN tblProjects ON (tblProjectContacts.ProjectId=tblProjects.Id)");
                    queryStringBuffer.append("SELECT tblProjects.ProjectName,tblProjectContacts.Id AS Id,AccountId,ProjectId,ObjectId,ResourceName AS EmpName ,Email,ResourceTitle,ObjectType,CASE WHEN (Billable=1) THEN  'Yes' ELSE 'No' END AS Billable,tblProjectContacts.Status,tblProjectContacts.StartDate,tblProjectContacts.Utilization,tblEmployee.Country AS ResourceCountry,tblProjectContacts.EndDate  FROM tblProjectContacts LEFT OUTER JOIN tblProjects ON (tblProjectContacts.ProjectId=tblProjects.Id) LEFT OUTER JOIN tblEmployee ON (tblEmployee.Id=tblProjectContacts.ObjectId) ");
                    if (!"".equals(getProjectId()) && getProjectId() != null) {
                        queryStringBuffer.append(" where tblProjectContacts.ProjectId=" + getProjectId() + " ");

                    }
                    if (!"".equals(getFirstName()) && getFirstName() != null) {
                        queryStringBuffer.append(" and tblProjectContacts.ResourceName like '%" + getFirstName() + "%' ");

                    }
                     if (!"".equals(getEmpProjectStatus()) && getEmpProjectStatus() != null && !("All".equals(getEmpProjectStatus()))) {
                        queryStringBuffer.append(" and tblProjectContacts.Status = '" + getEmpProjectStatus() + "' ");
						
                     }

//                    if (!"".equals(getCurrStatus()) && getCurrStatus() != null && !("All".equals(getCurrStatus()))) {
//                        queryStringBuffer.append(" and tblProjectContacts.Status = '" + getCurrStatus() + "' ");
//
//                    }
                    if (getReportsTo() != null && !"-1".equals(getReportsTo()) && !"".equals(getReportsTo())) {
                        queryStringBuffer.append(" and tblProjectContacts.ReportsTo = '" + getReportsTo() + "' ");

                    }
                    //  System.out.println("getIsBillable"+getIsBillable());
                    if ("true".equals(getIsBillable())) {
                        queryStringBuffer.append(" and tblProjectContacts.Billable = 1 ");

                    }


                    //  setReportsTo(getReportsTo());
                    //setIsBillable(getIsBillable());
                    // System.out.println("queryStringBuffer--->" + queryStringBuffer.toString());

                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_PROJECT_TEAM_LIST, queryStringBuffer.toString());

                    queryStringBuffer.delete(0, queryStringBuffer.capacity());

                    //   }                    
                    resultType = SUCCESS;
                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        //System.err.println("resultType"+resultType);
        return resultType;
    }
    //new

    public String consultantTechReviews() {
        resultType = LOGIN;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            //System.out.println("workingCountry----->"+workingCountry);
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("GET_EMP_SEARCH_ALL", userRoleId)) {
                try {
                    setTechReviews("1");
                    setStatus("Assigned");
                    resultType = SUCCESS;
                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }

    public String searchTechReviews() {
        resultType = LOGIN;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            String empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
            //System.out.println("workingCountry----->"+workingCountry);
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("GET_EMP_SEARCH_ALL", userRoleId)) {
                try {
                    // System.out.println("status-->"+getStatus()); 
                    setStatus(getStatus());
                    String startDate;
                    String endDate;
                    queryStringBuffer = new StringBuffer();
                    String email = DataSourceDataProvider.getInstance().getEmailIdForEmployee(Integer.parseInt(empId));

                    queryStringBuffer.append("SELECT tblRecConsultant.Id as conId,tblRecConsultantActivity.Id AS id,CONCAT(FName,' ',MName,' ',LName) AS NAME,tblRecConsultantActivity.Rateing,"
                            + "tblRecConsultantActivity.ForwardedDate,tblRecConsultantActivity.LastModifiedDate,tblRecConsultantActivity.Status "
                            + "FROM tblRecConsultantActivity LEFT OUTER JOIN tblRecConsultant ON "
                            + "(tblRecConsultantActivity.ConsultantId=tblRecConsultant.Id) WHERE ForwardedTo='" + email + "' ");
                    if (getStartDate() != null && getEndDate() != null) {
                        startDate = DateUtility.getInstance().convertStringToMySQLDate(getStartDate());
                        endDate = DateUtility.getInstance().convertStringToMySQLDate(getEndDate());

                        queryStringBuffer.append("AND ForwardedDate >='" + startDate + "' AND ForwardedDate<='" + endDate + "' ");
                    }
                    if (getSkillSet() != null) {
                        queryStringBuffer.append("AND SkillSet LIKE '%" + getSkillSet() + "%'");
                    }
                    if (getStatus().equalsIgnoreCase("Assigned") || getStatus().equalsIgnoreCase("A")) {
                        queryStringBuffer.append("AND tblRecConsultantActivity.Status LIKE 'A'");
                    } else if (getStatus().equalsIgnoreCase("Forward") || getStatus().equalsIgnoreCase("F")) {
                        queryStringBuffer.append("AND tblRecConsultantActivity.Status LIKE 'F'");
                    } else if (getStatus().equalsIgnoreCase("Reviewed") || getStatus().equalsIgnoreCase("R")) {
                        queryStringBuffer.append("AND tblRecConsultantActivity.Status LIKE 'R'");
                    } else if (getStatus().equalsIgnoreCase("Reviewed&Forwarded") || getStatus().equalsIgnoreCase("RF")) {
                        queryStringBuffer.append("AND tblRecConsultantActivity.Status LIKE 'RF'");
                    }
                    //  System.out.println(queryStringBuffer.toString());
                    httpServletRequest.setAttribute("resultMessage", getResultMessage());
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.EMP_TECH_REVIEWS, queryStringBuffer.toString());
                    resultType = SUCCESS;
                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }

    public String getCurrentEmployeeReport() {
        //  System.out.println("getCurrentEmployeeReport method");
        resultType = INPUT;
        OutputStream outputStream = null;
        InputStream inputStream = null;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            // resultType = "accessFailed";
            // System.out.println("if block");
            if (AuthorizationManager.getInstance().isAuthorizedUser("GET_EMPLOYEE", userRoleId)) {


                String responseString = "";
                try {
                    String fileLocation = "";

                    // fileLocation = generateEmployeeList(getLoginId());

                    fileLocation = ServiceLocator.getEmployeeService().generateEmployeeList(getLoginId());
                    httpServletResponse.setContentType("application/force-download");

                    if (!"".equals(fileLocation)) {
                        File file = new File(fileLocation);

                        String fileName = "";

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
                    } else {

                        resultMessage = "<font color=\"red\" size=\"1.5\">Sorry! No records for this Employee!</font>";
                        httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, resultMessage);

                    }


                } catch (FileNotFoundException ex) {
                    try {
                        httpServletResponse.sendRedirect("../general/exception.action?exceptionMessage='No File found'");
                    } catch (IOException ex1) {
                        Logger.getLogger(EmployeeAction.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        // System.out.println("finally resultType "+resultType);
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



            }//END-Authorization Checking
        }//Close Session Checking
        resultType = SUCCESS;


        setEmpId(getEmpId());
        return resultType;
    }

    public String getPMODashBoard() {

        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                Map map;
                String roleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
                hibernateDataProvider = HibernateDataProvider.getInstance();
                defaultDataProvider = DefaultDataProvider.getInstance();
                dataSourceDataProvider = dataSourceDataProvider.getInstance();
                setPracticeIdList(dataSourceDataProvider.getPracticeByDepartment(getDepartmentId()));
                setCountryList(hibernateDataProvider.getContries(ApplicationConstants.COUNTRY_OPTIONS));
                String Country = (String) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.Living_COUNTRY);
                setStateList(hibernateDataProvider.getEmpCurrentState(ApplicationConstants.EMP_CURRENT_STATUS));
                loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                setStatus("Active");
                setEmpStatus("Active");
                setSubPracticeList(dataSourceDataProvider.getSubPracticeByPractice(getPracticeId()));
                setClientMap(DataSourceDataProvider.getInstance().getCustomerMap());
                if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_ADMIN_ACCESS).toString().equals("1")) || (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_PMO_ACTIVITY_ACCESS).toString().equals("1"))) {
                    setMyPMOTeamMembers(DataSourceDataProvider.getInstance().getPmoMap());
                } else if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_TEAM_LEAD).toString().equals("1") || httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_USER_MANAGER).toString().equals("1")) {
                    map = (Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP);
                    map.put(loginId, DataSourceDataProvider.getInstance().getemployeenamebyloginId(loginId));
                    setMyPMOTeamMembers(map);
                }
                setPracticeList(DataSourceDataProvider.getInstance().getPracticeByDepartment("GDC"));
                resultType = SUCCESS;
            } catch (Exception ex) {
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }
        }
        return resultType;

    }

    public String getManagerDashBoard() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            String objectId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
            // System.out.println("objectId-->"+objectId);
            resultType = "accessFailed";
            //  if (AuthorizationManager.getInstance().isAuthorizedUser("GET_PROJECT_DASH_BOARD", userRoleId)) {
            try {
                //  setMyProjects(new HashMap());
                // String departmentId = DataSourceDataProvider.getInstance().getDepartmentName(loginId);
                // setManagerTeamMembersList(dataSourceDataProvider.getInstance().getMyTeamMembers(loginId, departmentId));


                setManagerTeamMembersList((Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));



                resultType = SUCCESS;
            } catch (Exception ex) {
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }

            //  }//END-Authorization Checking
        }//Close Session Checking
        resultType = SUCCESS;
        return resultType;
    }

    public String getCustomerProjectsList() {

        resultType = LOGIN;


        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            //String objectId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();

            if (AuthorizationManager.getInstance().isAuthorizedUser("GET_PMO_ACTIVITY", userRoleId)) {

                try {
                    setCurrentAction("getCustomerProjectsList");;
                    // String empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();

                    resultType = SUCCESS;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }

            }
        }
        return resultType;
    }

    public String getCustomerProjectsDetailsList() {

        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            // String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            System.out.println("role" + userRoleId);
            //String objectId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
            setCustomerName(getCustomerName());
            setAccountId(accountId);
            if (AuthorizationManager.getInstance().isAuthorizedUser("GET_PMO_ACTIVITY", userRoleId)) {

                try {
                    // System.out.println("in getCustomerProjectsList try");
                    setCurrentAction("getCustomerProjectsList");
                    if (getSubmitFrom() == null) {

                        queryString = "SELECT tblCrmAccount.NAME,tblProjects.CustomerId,tblProjects.Id,tblProjects.ProjectName,tblProjects.STATUS,"
                                + "tblProjects.TotalResources,tblProjects.ProjectStartDate,tblProjects.ProjectEndDate,tblProjects.Comments,CASE WHEN (tblProjects.PMO= -1)"
                                + " THEN  '' ELSE tblProjects.PMO END AS PMO,CASE WHEN (tblProjects.PreSalesMgrId= -1) "
                                + "THEN  '' ELSE tblProjects.PreSalesMgrId END AS preSalesMgr FROM tblCrmAccount JOIN tblProjects  "
                                + "ON tblCrmAccount.Id = tblProjects.CustomerId WHERE tblCrmAccount.Id =" + getAccountId();


                        if (getPrjName() != null && !"".equalsIgnoreCase(getPrjName())) {
                            queryString = queryString + " and  tblProjects.ProjectName like '%" + getPrjName() + "%'";
                        }
                        if (!"".equals(getStatus()) && getStatus() != null) {
                            queryString = queryString + " and tblProjects.STATUS='" + getStatus() + "' ";
                        }
                        // System.out.println("queryString..getCustomerProjectsDetailsList----" + queryString);
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_CUSTOMER_PROJECTS_LIST, queryString);

                    }
                    resultType = SUCCESS;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }

            }
        }
        return resultType;
    }

    /*
     * Employee Resume Download Start
     * 
     */
    public String downloadEmployeeResume() {
        try {
            resultType = INPUT;
            // this.setId(Integer.parseInt(httpServletRequest.getParameter("Id").toString()));

            // this.setAttachmentLocation(ServiceLocator.getProjIssuesService().getAttachmentLocation(this.getId()));
            // setResultMessage();
            String location = ServiceLocator.getEmployeeService().getEmployeeResumeLocation(getId());

            httpServletResponse.setContentType("application/force-download");

            File file = new File(location);
            if (file.exists()) {


                String fileName = file.getName();
                inputStream = new FileInputStream(file);
                outputStream = httpServletResponse.getOutputStream();
                httpServletResponse.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
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
                resultMessage = "<font color=\"red\" size=\"1.5\">Sorry! File Does not Exist!</font>";
                httpServletRequest.getSession().setAttribute(ApplicationConstants.RESULT_MSG, resultMessage);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                    outputStream.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return resultType;
    }

    public String projectPortfolioReport() {

        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            String objectId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
            // System.out.println("objectId-->"+objectId);
            resultType = "accessFailed";
            //  if (AuthorizationManager.getInstance().isAuthorizedUser("GET_PROJECT_DASH_BOARD", userRoleId)) {
            try {
                dataSourceDataProvider = dataSourceDataProvider.getInstance();
                //  setMyProjects(new HashMap());
                // String departmentId = DataSourceDataProvider.getInstance().getDepartmentName(loginId);
                // setManagerTeamMembersList(dataSourceDataProvider.getInstance().getMyTeamMembers(loginId, departmentId));
                //    setManagerTeamMembersList((Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
                setStatus("Active");
                setClientMap(dataSourceDataProvider.getClientMap());
                resultType = SUCCESS;
            } catch (Exception ex) {
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }

            //  }//END-Authorization Checking
        }//Close Session Checking
        resultType = SUCCESS;
        return resultType;
    }

    /*
     * Employee Resume Download end
     * 
     */

    /*
     * Quarterly Appraisal Start
     */
    public String getMyQuarterlyAppraisalSearch() {
        resultType = LOGIN;

        if (httpServletRequest.getSession(false) != null && httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            // String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();

              if (AuthorizationManager.getInstance().isAuthorizedUser("QUARTERLY_APPRAISAL", userRoleId)) {
            try {
                GregorianCalendar cal = new GregorianCalendar();
                int year = cal.get(Calendar.YEAR);


                if (getYear() == 0) {
                    setYear(year);
                }

                queryString = "SELECT * FROM vwQuarterlyAppraisalsList  where EmpId=" + empId + " and year(CreatedDate)=" + getYear();

                if (getQuarterly() != null && !"".equals(quarterly)) {
                    queryString = queryString + " AND Quarterly='" + getQuarterly() + "' ";
                }
                //  queryString=queryString+" ORDER BY yer,Quarterly";

                httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_EMP_APPRAISAL_LIST, queryString);



                resultType = SUCCESS;
            } catch (Exception ex) {
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }
                }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }

    public String teamQuaterAppraisalSearch() {
        resultType = LOGIN;

        if (httpServletRequest.getSession(false) != null && httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            // String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
            String loginId1 = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
              if (AuthorizationManager.getInstance().isAuthorizedUser("QUARTERLY_APPRAISAL", userRoleId)) {
            try {

                GregorianCalendar cal = new GregorianCalendar();
                int year = cal.get(Calendar.YEAR);

                if (getYear() == 0) {
                    setYear(year);
                }
Map rolesMap = (Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_ROLES);
           //     queryString = "SELECT tblQuarterlyAppraisals.Id,EmpId,AppraisalId,CONCAT(fname,' ',mName,'.',lName) AS empName,DATE_FORMAT(tblQuarterlyAppraisals.CreatedDate,'%M') AS mnth, YEAR(tblQuarterlyAppraisals.CreatedDate) AS yer,tblQuarterlyAppraisals.CreatedDate,Quarterly,STATUS,SubmittedDate,ApprovedDate,OpperationTeamStatus,CONCAT(emp2.fname,'.',emp2.lName) AS approvedBy FROM tblQuarterlyAppraisals LEFT JOIN tblEmployee emp1 ON(tblQuarterlyAppraisals.EmpId=emp1.Id) LEFT JOIN tblEmployee emp2 ON(tblQuarterlyAppraisals.ApprovedBy=emp2.LoginId) where year(tblQuarterlyAppraisals.CreatedDate)=" + getYear();
                queryString = "SELECT * FROM vwQuarterlyAppraisalsList where year(CreatedDate)=" + getYear();
                Map myTeamMemebrs = new HashMap();
               // System.out.println("httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString()===" + httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString());
                if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString().equalsIgnoreCase("Employee")) {
                    if(loginId1.equals("rkalaga")){
                      //  myTeamMemebrs = DataSourceDataProvider.getInstance().getAllEmployees();
                        myTeamMemebrs = DataSourceDataProvider.getInstance().getAllEmployeesByCountry("India");
                   }else{
                        myTeamMemebrs = (Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP);
                   }
                } else if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString().equalsIgnoreCase("Operations")) {
                    
                    String department = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_DEPT_ID).toString();
                    int isManager = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_USER_MANAGER).toString());
                    String access[] = Properties.getProperty("QuarterlyAppraisal.Access").split(Pattern.quote(","));

                    List accessList = Arrays.asList(access);
                    if (accessList.contains(loginId1) || (department.equals("Operations") && isManager == 1) || rolesMap.containsValue("Admin")) {
                        setAccessCount(1);
                    }
                    if (getAccessCount() == 1) {
                       //  myTeamMemebrs = DataSourceDataProvider.getInstance().getAllEmployees();
                        myTeamMemebrs = DataSourceDataProvider.getInstance().getAllEmployeesByCountry("India");

                    } else {
                        myTeamMemebrs = DataSourceDataProvider.getInstance().getInstance().getEmployeeNamesByOperationsContactId((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString()));

                    }
                    //  myTeamMemebrs = (Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP);
                    
                }
                String teamList = DataSourceDataProvider.getInstance().getTeamLoginIdList(myTeamMemebrs);
                setMyTeamMembers(myTeamMemebrs);
                if (loginId != null && !"".equals(loginId)) {
                    queryString = queryString + " AND loginId ='" + loginId + "'";
                } else {
                    if (!"".equals(teamList)) {
                        queryString = queryString + " AND loginId IN(" + teamList + ")";
                     }else{
                        queryString = queryString + " AND loginId IN('')";
                    }
                }
                if (getQuarterly() != null && !"".equals(quarterly)) {
                    queryString = queryString + " AND Quarterly='" + getQuarterly() + "' ";
                }

                if (status != null && !"".equals(status)) {
                    queryString = queryString + " AND status='" + status + "' ";
                }
                
                if(departmentId!=null && !"".equals(departmentId)){
                   queryString = queryString + " AND DepartmentId='" + departmentId + "' "; 
                }
                if(practiceId!=null && !"".equals(practiceId) && !"All".equals(practiceId)){
                   queryString = queryString + " AND Practice='" + practiceId + "' "; 
                }
                if(subPractice!=null && !"".equals(subPractice) && !"All".equals(subPractice)){
                   queryString = queryString + " AND SubPractice='" + subPractice + "' "; 
                }
                if(opsContactId!=null && !"".equals(opsContactId)){
                   queryString = queryString + " AND OpsContactId=" + opsContactId + " "; 
                }
                if(location!=null && !"".equals(location)){
                   queryString = queryString + " AND Location='" + location + "' "; 
                }
                if(titleId!=null && !"".equals(titleId)){
                    
                   String ManagerList= Properties.getProperty("Management.Quarter");
                   if(titleId.equals("Management")){
                    queryString = queryString + " AND FIND_IN_SET(Title,'" + ManagerList + "') "; 
                   }else{
                        queryString = queryString + " AND !FIND_IN_SET(Title,'" + ManagerList + "') "; 
                   }
                }
                
/* if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString().equalsIgnoreCase("Operations") || loginId1.equals("rkalaga")) {
      queryString = queryString + " AND STATUS NOT IN ('Submitted','Entered') ";
    
    
 }else{
     queryString = queryString + " AND STATUS NOT IN ('Entered') ";
 }*/
                
                //    queryString=queryString+" ORDER BY yer,Quarterly";
String Country = (String) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.Living_COUNTRY);
           if(rolesMap.containsValue("Admin")){
                setOpsContactIdMap(DataSourceDataProvider.getInstance().getOpsContactId(Country, "Yes"));
            }else{
               setOpsContactIdMap(DataSourceDataProvider.getInstance().getOpsContactId(Country, "No"));
           }

            
			
			 if (rolesMap.containsValue("Admin")) {
                setLocationsMap(DataSourceDataProvider.getInstance().getEmployeeLocationsList("%"));
            } else {
                setLocationsMap(DataSourceDataProvider.getInstance().getEmployeeLocationsList(Country));
            }
                httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_EMP_APPRAISAL_LIST, queryString);



                resultType = SUCCESS;
            } catch (Exception ex) {
                ex.printStackTrace();
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }
                }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }

    public String myQuarterlyAppraisalAdd() {
        resultType = LOGIN;

        if (httpServletRequest.getSession(false) != null && httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            //  int isManager = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_USER_MANAGER).toString());
            // String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();


            if (AuthorizationManager.getInstance().isAuthorizedUser("QUARTERLY_APPRAISAL", userRoleId)) {
                try {
                    int isManager = 0;
                    String title = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_TITLE).toString();

                    String Factortype[] = Properties.getProperty("Management.Quarter").split(Pattern.quote(","));

                    List ManagementQuarter = Arrays.asList(Factortype);
                    // System.out.println("ManagementQuarter===" + ManagementQuarter);
                    // System.out.println("title===" + title);
                    if (ManagementQuarter.contains(title)) {
                        isManager = 1;
                    }
                    String empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
                    setEmpId(Integer.parseInt(empId));
                    int count = ServiceLocator.getEmployeeService().isExistedAppraisal(Integer.parseInt(empId), getYear(), getQuarterly());

                    if (count == 0) {
                        //  String quarterAppraisalDetails = ServiceLocator.getEmployeeService().getEmployeeResumeLocation(getId());
                        String result = (ServiceLocator.getEmployeeService().getQuarterAppraisalDetails(isManager, Integer.parseInt(empId)));
                        setQuarterAppraisalDetails(result.split(Pattern.quote("@^$"))[0]);
                        String currentStatus = result.split(Pattern.quote("@^$"))[1];
                        if (currentStatus != null && !"".equals(currentStatus) && !"_".equals(currentStatus)) {
                            setCurrStatus(currentStatus);
                        } else {
                            setCurrStatus("");
                        }


//                setDeliveryKeyFactorsList(DataSourceDataProvider.getInstance().getDeliveryFactors());
//                setTrainingKeyFactorsList(DataSourceDataProvider.getInstance().getTrainingFactors());
                        List empdetails = DataSourceDataProvider.getInstance().getEmployeeInfoById(empId);
                        if (empdetails.size() > 0) {
                            setEmpName((String) empdetails.get(0));
                            setItgBatch((String) empdetails.get(1));
                            setEmpDateOFBirth((String) empdetails.get(2));
                            setPracticeId((String) empdetails.get(4));
                            setTitleId((String) empdetails.get(5));
                            //System.out.println("setPracticeId===" + getPracticeId());
                            isManager = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_USER_MANAGER).toString());
                            if (isManager == 1) {
                                setIsManager(true);
                            } else {
                                setIsManager(false);
                            }
                        }

                        // System.out.println("getCurretRole===" + getCurretRole());

                        setCurretRole("my");

                        // System.out.println("getCurretRole1===" + getCurretRole());
                        // searchPrepare();
                        //  prepare();
                        resultType = SUCCESS;
                    } else {
                        resultMessage = "<font color=\"red\" size=\"2.5\">Sorry! Quarterly  Review  already added for this quarter</font>";
                        httpServletRequest.getSession().setAttribute(ApplicationConstants.RESULT_MSG, resultMessage);
                        resultType = INPUT;
                    }
                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }

    public String empQuarterlyAppraisalSave() {
        resultType = LOGIN;

        if (httpServletRequest.getSession(false) != null && httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            int isManager = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_USER_MANAGER).toString());
            // String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String livingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.Living_COUNTRY).toString();
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();

            if (AuthorizationManager.getInstance().isAuthorizedUser("QUARTERLY_APPRAISAL", userRoleId)) {
                try {
                    //  System.out.println("shortTermGoalComments===" + getShortTermGoalComments());

                    String result = ServiceLocator.getEmployeeService().empQuarterlyAppraisalSave(httpServletRequest, getRowCount(), getCurretRole(), getAppraisalId(), getStatus(), getEmpId(), getLineId(), getShortTermGoal(), getShortTermGoalComments(), getLongTermGoal(), getLongTermGoalComments(), getStrength(), getStrengthsComments(), getImprovements(), getImprovementsComments(), getQuarterly(), getRejectedComments());
                    // System.out.println("result===" + result);
                    if (result.contains("added")) {

                        setAppraisalId(Integer.parseInt(result.split(Pattern.quote("#^$"))[1]));
                        setLineId(Integer.parseInt(result.split(Pattern.quote("#^$"))[2]));
                        result = result.split(Pattern.quote("#^$"))[0];
                    }
                    if (result.equals("added") && getStatus().equals("Entered")) {
                        resultType = "added";
                        resultMessage = "<font color=\"green\" size=\"2.5\">Quarterly  Review " + getStatus().toLowerCase() + " successfully!</font>";
                        httpServletRequest.getSession().setAttribute(ApplicationConstants.RESULT_MSG, resultMessage);
                    } else if (result.equals("updated") && getStatus().equals("Entered")) {
                        resultType = "added";
                        resultMessage = "<font color=\"green\" size=\"2.5\">Quarterly  Review " + getStatus().toLowerCase() + " successfully!</font>";
                        httpServletRequest.getSession().setAttribute(ApplicationConstants.RESULT_MSG, resultMessage);
                    } else if (result.equals("updated") || result.equals("added")) {
                        resultMessage = "<font color=\"green\" size=\"2.5\">Quarterly  Review " + getStatus().toLowerCase() + " successfully!</font>";
                        httpServletRequest.getSession().setAttribute(ApplicationConstants.RESULT_MSG, resultMessage);
                        resultType = SUCCESS;
                    } else {
                        resultMessage = "<font color=\"red\" size=\"2.5\">please try again later!</font>";
                        httpServletRequest.getSession().setAttribute(ApplicationConstants.RESULT_MSG, resultMessage);
                        resultType = SUCCESS;
                    }

                    if (getCurretRole().equals("team")) {
                        resultType = "team";
                    }
                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                   // httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                  //  ex.printStackTrace();
                   // resultType = ERROR;
                      resultMessage = "<font color=\"red\" size=\"2.5\">Oops!please try again once!</font>";
                        httpServletRequest.getSession().setAttribute(ApplicationConstants.RESULT_MSG, resultMessage);
                        resultType = SUCCESS;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }

    public String myQuarterlyAppraisalEdit() {
        resultType = LOGIN;

        if (httpServletRequest.getSession(false) != null && httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            int isManager = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_USER_MANAGER).toString());
            // String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String livingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.Living_COUNTRY).toString();
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("QUARTERLY_APPRAISAL", userRoleId)) {
                try {
                    boolean accessAllow = false;
                    if (getEmpId() != 0 && getAppraisalId() != 0 && getLineId() != 0) {
                        String emploginId = DataSourceDataProvider.getInstance().getLoginIdByEmpId(getEmpId());

                        Map myTeamMemebrs = new HashMap();
                        Map rolesMap = (Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_ROLES);
                        String department = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_DEPT_ID).toString();
                        String access[] = Properties.getProperty("QuarterlyAppraisal.Access").split(Pattern.quote(","));
                        List accessList = Arrays.asList(access);

                        int sessionEmpId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
                        if (getCurretRole() != null && getCurretRole().equals("my")) {

                            if (sessionEmpId == getEmpId()) {
                                accessAllow = true;
                            }
                        } else if (getCurretRole() != null && getCurretRole().equals("team")) {
                            String roleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
                            if (loginId.equals("rkalaga")) {
                                accessAllow = true;
                            } else if ((roleName.equals("Operations") && accessList.contains(loginId)) || (department.equals("Operations") && isManager == 1) || rolesMap.containsValue("Admin")) {
                                accessAllow = true;
                            } else {
                                if (roleName.equalsIgnoreCase("Employee") && isManager == 1) {
                                    myTeamMemebrs = (Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP);

                                    if (myTeamMemebrs.containsKey(emploginId)) {
                                        accessAllow = true;
                                    }
                                }
                                if (roleName.equalsIgnoreCase("Operations")) {
                                    myTeamMemebrs = DataSourceDataProvider.getInstance().getInstance().getEmployeeNamesByOperationsContactId((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString()));

                                    if (myTeamMemebrs.containsKey(emploginId)) {
                                        accessAllow = true;
                                    }
                                }

                            }
                        }
                        if (accessAllow) {
                            //  String quarterAppraisalDetails = ServiceLocator.getEmployeeService().getEmployeeResumeLocation(getId());
                            String result = ServiceLocator.getEmployeeService().quarterlyAppraisalEdit(getEmpId(), getAppraisalId(), getLineId());
                            setQuarterAppraisalDetails(result.split(Pattern.quote("@^$"))[0]);
                            String remainingData[] = (result.split(Pattern.quote("@^$"))[1]).split(Pattern.quote("#^$"));
                            if (remainingData[0] != null && !"".equals(remainingData[0]) && !"_".equals(remainingData[0])) {
                                setShortTermGoal(remainingData[0]);
                            }
                            if (remainingData[1] != null && !"".equals(remainingData[1]) && !"_".equals(remainingData[1])) {
                                setShortTermGoalComments(remainingData[1]);
                            }
                            if (remainingData[2] != null && !"".equals(remainingData[2]) && !"_".equals(remainingData[2])) {
                                setLongTermGoal(remainingData[2]);
                            }
                            if (remainingData[3] != null && !"".equals(remainingData[3]) && !"_".equals(remainingData[3])) {
                                setLongTermGoalComments(remainingData[3]);
                            }
                            if (remainingData[4] != null && !"".equals(remainingData[4]) && !"_".equals(remainingData[4])) {
                                setStrength(remainingData[4]);
                            }
                            if (remainingData[5] != null && !"".equals(remainingData[5]) && !"_".equals(remainingData[5])) {
                                setStrengthsComments(remainingData[5]);
                            }
                            if (remainingData[6] != null && !"".equals(remainingData[6]) && !"_".equals(remainingData[6])) {
                                setImprovements(remainingData[6]);
                            }
                            if (remainingData[7] != null && !"".equals(remainingData[7]) && !"_".equals(remainingData[7])) {
                                setImprovementsComments(remainingData[7]);
                            }
                            if (remainingData[8] != null && !"".equals(remainingData[8]) && !"_".equals(remainingData[8])) {
                                setOperationTeamStatus(remainingData[8]);
                            }

                            if (remainingData[9] != null && !"".equals(remainingData[9]) && !"_".equals(remainingData[9])) {
                                setManagerRejectedComments(remainingData[9]);
                            }

                            if (remainingData[10] != null && !"".equals(remainingData[10]) && !"_".equals(remainingData[10])) {
                                setOperationRejectedComments(remainingData[10]);
                            }

                            java.util.Date approvedDate = null;
                            java.util.Date opeartionApprovedDate = null;

                            if (remainingData[11] != null && !"".equals(remainingData[11]) && !"_".equals(remainingData[11])) {
                                approvedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(remainingData[11]);
                            }
                            if (remainingData[12] != null && !"".equals(remainingData[12]) && !"_".equals(remainingData[12])) {
                                opeartionApprovedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(remainingData[12]);
                            }

                            if (remainingData[13] != null && !"".equals(remainingData[13]) && !"_".equals(remainingData[13])) {
                                setQuarterly(remainingData[13]);
                            }

                            if (remainingData[14] != null && !"".equals(remainingData[14]) && !"_".equals(remainingData[14])) {
                                setStatus(remainingData[14]);
                            }
                            if (remainingData[15] != null && !"".equals(remainingData[15]) && !"_".equals(remainingData[15])) {
                                setCurrStatus(remainingData[15]);
                            } else {
                                setCurrStatus("");
                            }



                            if (approvedDate == null && opeartionApprovedDate == null) {
                                setDayCount(0);
                            } else {
                                if (opeartionApprovedDate == null) {
                                    setDayCount(1);
                                } else {
                                    if (approvedDate.compareTo(opeartionApprovedDate) > 0) {
                                        setDayCount(1);
                                    } else {
                                        setDayCount(2);
                                    }
                                }
                            }

//                setDeliveryKeyFactorsList(DataSourceDataProvider.getInstance().getDeliveryFactors());
//                setTrainingKeyFactorsList(DataSourceDataProvider.getInstance().getTrainingFactors());
                            List empdetails = DataSourceDataProvider.getInstance().getEmployeeInfoById(String.valueOf(empId));
                            if (empdetails.size() > 0) {
                                setEmpName((String) empdetails.get(0));
                                setItgBatch((String) empdetails.get(1));
                                setEmpDateOFBirth((String) empdetails.get(2));
                                setPracticeId((String) empdetails.get(4));
                                setTitleId((String) empdetails.get(5));
                                if (isManager == 1) {
                                    setIsManager(true);
                                } else {
                                    setIsManager(false);
                                }
                            }

                            if (getCurretRole() == null || !getCurretRole().equals("team")) {
                                setCurretRole("my");
                            }


                            if (accessList.contains(loginId) || (department.equals("Operations") && isManager == 1) || rolesMap.containsValue("Admin")) {
                                setAccessCount(1);
                            }

                            // searchPrepare();
                            //  prepare();
                            resultType = SUCCESS;
                        }
                    }

                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking

        /*  if (httpServletRequest.getParameter("quarterly") != null) {
        String empId = httpServletRequest.getParameter("empId");
        String lineId = httpServletRequest.getParameter("lineId");
        String appraisalId = httpServletRequest.getParameter("appraisalId");
        String quarterly = httpServletRequest.getParameter("quarterly");
        setEmpId(Integer.parseInt(empId));
        setLineId(Integer.parseInt(lineId));
        setAppraisalId(Integer.parseInt(appraisalId));
        setQuarterly(quarterly);
        } */

        return resultType;
    }
    /*
     * Quarterly Appraisal End
     */

    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    /**
     * The getFirstName() is used for firstName of employee.
     *
     * @ return String variable
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * The setFirstName(String firstName) is used for setting firstName of
     * employee.
     *
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * The getLastName() is used for lastName of employee.
     *
     * @ return String variable
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * The setLastName(String lastName) is used for setting lastName of
     * employee.
     *
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * The getMiddleName() is used for middleName of employee.
     *
     * @ return String variable
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * The setMiddleName(String middleName) is used for setting middleName of
     * employee.
     *
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * The getAliasName() is used for aliasName of employee.
     *
     * @ return String variable
     */
    public String getAliasName() {
        return aliasName;
    }

    /**
     * The setAliasName(String aliasName) is used for setting middleName of
     * employee.
     *
     */
    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    /**
     * The getGender() is used for gender of employee.
     *
     * @ return String variable
     */
    public String getGender() {
        return gender;
    }

    /**
     * The setGender(String gender) is used for setting gender of employee.
     *
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * The getMaritalStatus() is used for maritalStatus of employee.
     *
     * @ return String variable
     */
    public String getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * The setMaritalStatus(String maritalStatus) is used for setting
     * maritalStatus of employee.
     *
     */
    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    /**
     * The getCountry() is used for country of employee.
     *
     * @ return String variable
     */
    public String getCountry() {
        return country;
    }

    /**
     * The setCountry(String country) is used for setting country of employee.
     *
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * The getSsn() is used for ssn of employee.
     *
     * @ return String variable
     */
    public String getSsn() {
        return ssn;
    }

    /**
     * The setSsn(String ssn) is used for setting ssn of employee.
     *
     */
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    /**
     * The getCurrStatus() is used for currStatus of employee.
     *
     * @ return String variable
     */
    public String getCurrStatus() {
        return currStatus;
    }

    /**
     * The setCurrStatus(String currStatus) is used for setting currStatus of
     * employee.
     *
     */
    public void setCurrStatus(String currStatus) {
        this.currStatus = currStatus;
    }

    /**
     * The getEmpTypeId() is used for empTypeId of employee.
     *
     * @ return String variable
     */
    public String getEmpTypeId() {
        return empTypeId;
    }

    /**
     * The setEmpTypeId(String empTypeId) is used for setting empTypeId of
     * employee.
     *
     */
    public void setEmpTypeId(String empTypeId) {
        this.empTypeId = empTypeId;
    }

    /**
     * The getOrgId() is used for orgId of employee.
     *
     * @ return String variable
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * The setOrgId(String orgId) is used for setting orgId of employee.
     *
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    /**
     * The getOpsContactId() is used for opsContactId of employee.
     *
     * @ return int variable
     */
    public String getOpsContactId() {
        return opsContactId;
    }

    /**
     * The setOpsContactId(int opsContactId) is used for setting opsContactId of
     * employee.
     *
     */
    public void setOpsContactId(String opsContactId) {
        this.opsContactId = opsContactId;
    }

    /**
     * The getTeamId() is used for teamId of employee.
     *
     * @ return String variable
     */
    public String getTeamId() {
        return teamId;
    }

    /**
     * The setTeamId(String teamId) is used for setting teamId of employee.
     *
     */
    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    /**
     * The getPracticeId() is used for practiceId of employee.
     *
     * @ return String variable
     */
    public String getPracticeId() {
        return practiceId;
    }

    /**
     * The setPracticeId(String practiceId) is used for setting practiceId of
     * employee.
     *
     */
    public void setPracticeId(String practiceId) {
        this.practiceId = practiceId;
    }

    /* public int getReportsToId() {
    return reportsToId;
    }
    
    public void setReportsToId(int reportsToId) {
    this.reportsToId = reportsToId;
    }*/
    /**
     * The getTitleId() is used for titleId of employee.
     *
     * @ return String variable
     */
    public String getTitleId() {
        return titleId;
    }

    /**
     * The setTitleId(String titleId) is used for setting titleId of employee.
     *
     */
    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }

    /**
     * The getIndustryId() is used for industryId of employee.
     *
     * @ return String variable
     */
    public String getIndustryId() {
        return industryId;
    }

    /**
     * The setIndustryId(String industryId) is used for setting industryId of
     * employee.
     *
     */
    public void setIndustryId(String industryId) {
        this.industryId = industryId;
    }

    /**
     * The getDepartmentId() is used for departmentId of employee.
     *
     * @ return String variable
     */
    public String getDepartmentId() {
        return departmentId;
    }

    /**
     * The setDepartmentId(String departmentId) is used for setting departmentId
     * of employee.
     *
     */
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * The getBirthDate() is used for birthDate of employee.
     *
     * @ return Date variable
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * The setBirthDate(Date birthDate) is used for setting birthDate of
     * employee.
     *
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * The getOffBirthDate() is used for offBirthDate of employee.
     *
     * @ return Date variable
     */
    public Date getOffBirthDate() {
        return offBirthDate;
    }

    /**
     * The setOffBirthDate(Date offBirthDate) is used for setting offBirthDate
     * of employee.
     *
     */
    public void setOffBirthDate(Date offBirthDate) {
        this.offBirthDate = offBirthDate;
    }

    /**
     * The getHireDate() is used for hireDate of employee.
     *
     * @ return Date variable
     */
    public Date getHireDate() {
        return hireDate;
    }

    /**
     * The setHireDate(Date hireDate) is used for setting hireDate of employee.
     *
     */
    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    /**
     * The getAnniversaryDate() is used for anniversaryDate of employee.
     *
     * @ return Date variable
     */
    public Date getAnniversaryDate() {
        return anniversaryDate;
    }

    /**
     * The setAnniversaryDate(Date anniversaryDate) is used for setting
     * anniversaryDate of employee.
     *
     */
    public void setAnniversaryDate(Date anniversaryDate) {
        this.anniversaryDate = anniversaryDate;
    }

    /**
     * The getWorkPhoneNo() is used for workPhoneNo of employee.
     *
     * @ return String variable
     */
    public String getWorkPhoneNo() {
        return workPhoneNo;
    }

    /**
     * The setWorkPhoneNo(String workPhoneNo) is used for setting workPhoneNo of
     * employee.
     *
     */
    public void setWorkPhoneNo(String workPhoneNo) {
        this.workPhoneNo = workPhoneNo;
    }

    /**
     * The getAltPhoneNo() is used for altPhoneNo of employee.
     *
     * @ return String variable
     */
    public String getAltPhoneNo() {
        return altPhoneNo;
    }

    /**
     * The setAltPhoneNo(String altPhoneNo) is used for setting altPhoneNo of
     * employee.
     *
     */
    public void setAltPhoneNo(String altPhoneNo) {
        this.altPhoneNo = altPhoneNo;
    }

    /**
     * The getHomePhoneNo() is used for homePhoneNo of employee.
     *
     * @ return String variable
     */
    public String getHomePhoneNo() {
        return homePhoneNo;
    }

    /**
     * The setHomePhoneNo(String homePhoneNo) is used for setting homePhoneNo of
     * employee.
     *
     */
    public void setHomePhoneNo(String homePhoneNo) {
        this.homePhoneNo = homePhoneNo;
    }

    /**
     * The getCellPhoneNo() is used for cellPhoneNo of employee.
     *
     * @ return String variable
     */
    public String getCellPhoneNo() {
        return cellPhoneNo;
    }

    /**
     * The setCellPhoneNo(String cellPhoneNo) is used for setting cellPhoneNo of
     * employee.
     *
     */
    public void setCellPhoneNo(String cellPhoneNo) {
        this.cellPhoneNo = cellPhoneNo;
    }

    /**
     * The getOfficeEmail() is used for officeEmail of employee.
     *
     * @ return String variable
     */
    public String getOfficeEmail() {
        return officeEmail;
    }

    /**
     * The setOfficeEmail(String officeEmail) is used for setting officeEmail of
     * employee.
     *
     */
    public void setOfficeEmail(String officeEmail) {
        this.officeEmail = officeEmail;
    }

    /**
     * The getHotelPhoneNo() is used for hotelPhoneNo of employee.
     *
     * @ return String variable
     */
    public String getHotelPhoneNo() {
        return hotelPhoneNo;
    }

    /**
     * The setHotelPhoneNo(String hotelPhoneNo) is used for setting hotelPhoneNo
     * of employee.
     *
     */
    public void setHotelPhoneNo(String hotelPhoneNo) {
        this.hotelPhoneNo = hotelPhoneNo;
    }

    /**
     * The getPersonalEmail() is used for personalEmail of employee.
     *
     * @ return String variable
     */
    public String getPersonalEmail() {
        return personalEmail;
    }

    /**
     * The setPersonalEmail(String personalEmail) is used for setting
     * personalEmail of employee.
     *
     */
    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    /**
     * The getIndiaPhoneNo() is used for indiaPhoneNo of employee.
     *
     * @ return String variable
     */
    public String getIndiaPhoneNo() {
        return indiaPhoneNo;
    }

    /**
     * The setIndiaPhoneNo(String indiaPhoneNo) is used for setting indiaPhoneNo
     * of employee.
     *
     */
    public void setIndiaPhoneNo(String indiaPhoneNo) {
        this.indiaPhoneNo = indiaPhoneNo;
    }

    /**
     * The getOtherEmail() is used for otherEmail of employee.
     *
     * @ return String variable
     */
    public String getOtherEmail() {
        return otherEmail;
    }

    /**
     * The setOtherEmail(String otherEmail) is used for setting otherEmail of
     * employee.
     *
     */
    public void setOtherEmail(String otherEmail) {
        this.otherEmail = otherEmail;
    }

    /**
     * The setFaxNo(String faxNo) is used for setting faxNo of employee.
     *
     */
    public void setFaxNo(String faxNo) {
        this.faxNo = faxNo;
    }

    /**
     * The getFaxNo() is used for otherEmail of employee.
     *
     * @ return String variable
     */
    public String getFaxNo() {
        return faxNo;
    }

    /**
     * The getMaritalStatusList() is used for maritalStatusList of employee.
     *
     * @ return List variable
     */
    public List getMaritalStatusList() {
        return maritalStatusList;
    }

    /**
     * The setMaritalStatusList(List maritalStatusList) is used for setting
     * maritalStatusList of employee.
     *
     */
    public void setMaritalStatusList(List maritalStatusList) {
        this.maritalStatusList = maritalStatusList;
    }

    /**
     * The getCountryList() is used for maritalStatusList of employee.
     *
     * @ return List variable
     */
    public List getCountryList() {
        return countryList;
    }

    /**
     * The setCountryList(List coutryList) is used for coutryList of employee.
     *
     * @ return List variable
     */
    public void setCountryList(List coutryList) {
        this.countryList = coutryList;
    }

    /**
     * The getCurrStatusList() is used for currStatusList of employee.
     *
     * @ return List variable
     */
    public List getCurrStatusList() {
        return currStatusList;
    }

    /**
     * The setCurrStatusList(List currStatusList) is used for currStatusList of
     * employee.
     *
     * @ return List variable
     */
    public void setCurrStatusList(List currStatusList) {
        this.currStatusList = currStatusList;
    }

    /**
     * The getEmpTypeIdList() is used for empTypeIdList of employee.
     *
     * @ return List variable
     */
    public List getEmpTypeIdList() {
        return empTypeIdList;
    }

    /**
     * The setEmpTypeIdList(List empTypeIdList) is used for empTypeIdList of
     * employee.
     *
     */
    public void setEmpTypeIdList(List empTypeIdList) {
        this.empTypeIdList = empTypeIdList;
    }

    /**
     * The getOrgIdList() is used for orgIdList of employee.
     *
     * @ return List variable
     */
    public List getOrgIdList() {
        return orgIdList;
    }

    /**
     * The setOrgIdList(List orgIdList) is used for orgIdList of employee.
     *
     */
    public void setOrgIdList(List orgIdList) {
        this.orgIdList = orgIdList;
    }

    /**
     * The getOpsContactIdMap() is used for opsContactIdMap of employee.
     *
     * @ return Map variable
     */
    public Map getOpsContactIdMap() {
        return opsContactIdMap;
    }

    /**
     * The setOpsContactIdMap(Map opsContactIdMap) is used for opsContactIdMap
     * of employee.
     *
     */
    public void setOpsContactIdMap(Map opsContactIdMap) {
        this.opsContactIdMap = opsContactIdMap;
    }

    /**
     * The getTeamIdList() is used for teamIdList of employee.
     *
     * @ return List variable
     */
    public List getTeamIdList() {
        return teamIdList;
    }

    /**
     * The setTeamIdList(List teamIdList) is used for teamIdList of employee.
     *
     */
    public void setTeamIdList(List teamIdList) {
        this.teamIdList = teamIdList;
    }

    /**
     * The getPracticeIdList() is used for practiceIdList of employee.
     *
     * @ return List variable
     */
    public List getPracticeIdList() {
        return practiceIdList;
    }

    /**
     * The setPracticeIdList(List practiceIdList) is used for practiceIdList of
     * employee.
     *
     */
    public void setPracticeIdList(List practiceIdList) {
        this.practiceIdList = practiceIdList;
    }

    /**
     * The getReportsToIdMap() is used for reportsToIdMap of employee.
     *
     * @ return Map variable
     */
    public Map getReportsToIdMap() {
        return reportsToIdMap;
    }

    /**
     * The setReportsToIdMap(Map reportsToIdMap) is used for reportsToIdMap of
     * employee.
     *
     */
    public void setReportsToIdMap(Map reportsToIdMap) {
        this.reportsToIdMap = reportsToIdMap;
    }

    /**
     * The getTitleIdList() is used for titleIdList of employee.
     *
     * @ return List variable
     */
    public List getTitleIdList() {
        return titleIdList;
    }

    /**
     * The setTitleIdList(List titleIdList) is used for titleIdList of employee.
     *
     */
    public void setTitleIdList(List titleIdList) {
        this.titleIdList = titleIdList;
    }

    /**
     * The getIndustryIdList() is used for industryIdList of employee.
     *
     * @ return List variable
     */
    public List getIndustryIdList() {
        return industryIdList;
    }

    /**
     * The setIndustryIdList(List industryIdList) is used for industryIdList of
     * employee.
     *
     */
    public void setIndustryIdList(List industryIdList) {
        this.industryIdList = industryIdList;
    }

    /**
     * The getDepartmentIdList() is used for departmentIdList of employee.
     *
     * @ return List variable
     */
    public List getDepartmentIdList() {
        return departmentIdList;
    }

    /**
     * The setDepartmentIdList(List departmentIdList) is used for
     * departmentIdList of employee.
     *
     */
    public void setDepartmentIdList(List departmentIdList) {
        this.departmentIdList = departmentIdList;
    }

    /**
     * The getGenderList() is used for genderList of employee.
     *
     * @ return List variable
     */
    public List getGenderList() {
        return genderList;
    }

    /**
     * The setGenderList(List genderList) is used for genderList of employee.
     *
     */
    public void setGenderList(List genderList) {
        this.genderList = genderList;
    }

    /**
     * The getId() is used for id of employee.
     *
     * @ return int variable
     */
    public int getId() {
        return id;
    }

    /**
     * The setId(int id) is used for id of employee.
     *
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * The getLastContactBy() is used for lastContactBy of employee.
     *
     * @ return int variable
     */
    public String getLastContactBy() {
        return lastContactBy;
    }

    /**
     * The setLastContactBy(int lastContactBy) is used for lastContactBy of
     * employee.
     *
     */
    public void setLastContactBy(String lastContactBy) {
        this.lastContactBy = lastContactBy;
    }

    /**
     * The getQueryString() is used for queryString of employee.
     *
     * @ return StringBuffer variable
     */
    public StringBuffer getQueryStringBuffer() {
        return queryStringBuffer;
    }

    /**
     * The setQueryString(StringBuffer queryString) is used for queryString of
     * employee.
     *
     */
    public void setQueryStringBuffer(StringBuffer queryStringBuf) {
        this.queryStringBuffer = queryStringBuf;
    }

    /**
     * The setServletRequest(HttpServletRequest httpServletRequest) is used for
     * httpServletRequest of employee.
     *
     */
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    /**
     * The getModifiedBy() is used for modifiedBy of employee.
     *
     * @ return String variable
     */
    public String getModifiedBy() {
        return modifiedBy;
    }

    /**
     * The setModifiedBy(String modifiedBy) is used for modifiedBy of employee.
     *
     */
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    /**
     * The getModifiedDate() is used for modifiedDate of employee.
     *
     * @ return Timestamp variable
     */
    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    /**
     * The setModifiedDate(Timestamp modifiedDate) is used for modifiedDate of
     * employee.
     *
     */
    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    /**
     * The getCreatedBy() is used for createdBy of employee.
     *
     * @ return String variable
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * The setCreatedBy(String createdBy) is used for createdBy of employee.
     *
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * The getCreatedDate() is used for createdDate of employee.
     *
     * @ return Timestamp variable
     */
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    /**
     * The setCreatedDate(Timestamp createdDate) is used for createdDate of
     * employee.
     *
     */
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * The setEmpId(int empId) is used for empId of employee.
     *
     */
    public void setEmpId(int empId) {
        this.empId = empId;
    }

    /**
     * The getEmpId() is used for empId of employee.
     *
     * @ return int variable
     */
    public int getEmpId() {
        return empId;
    }

    /**
     * EmployeeVTO getCurrentEmployee() is used for currentEmployee of employee.
     *
     * @see com.mss.miracle.employee.general.EmployeeVTO
     *
     */
    public EmployeeVTO getCurrentEmployee() {
        return currentEmployee;
    }

    /**
     * setCurrentEmployee(EmployeeVTO currentEmployee) is used for
     * currentEmployee of employee.
     *
     * @see com.mss.miracle.employee.general.EmployeeVTO
     *
     */
    public void setCurrentEmployee(EmployeeVTO currentEmployee) {
        this.currentEmployee = currentEmployee;
    }

    /**
     * The getPreCurrStatus() is used for preCurrStatus of employee.
     *
     * @ return String variable
     */
    public String getPreCurrStatus() {
        return preCurrStatus;
    }

    /**
     * setPreCurrStatus(String preCurrStatus) is used for preCurrStatus of
     * employee.
     *
     */
    public void setPreCurrStatus(String preCurrStatus) {
        this.preCurrStatus = preCurrStatus;
    }

    public boolean getIsManager() {
        return isManager;
    }

    public void setIsManager(boolean isManager) {
        this.isManager = isManager;
    }

    public String getReportsTo() {
        return reportsTo;
    }

    public void setReportsTo(String reportsTo) {
        this.reportsTo = reportsTo;
    }

    public Map getPointOfContactMap() {
        return pointOfContactMap;
    }

    public void setPointOfContactMap(Map pointOfContactMap) {
        this.pointOfContactMap = pointOfContactMap;
    }

    public String getSubmitFrom() {
        return submitFrom;
    }

    public void setSubmitFrom(String submitFrom) {
        this.submitFrom = submitFrom;
    }

    public String getEmpState() {
        return empState;
    }

    public void setEmpState(String empState) {
        this.empState = empState;
    }

    public Date getStateStartDate() {
        return stateStartDate;
    }

    public void setStateStartDate(Date stateStartDate) {
        this.stateStartDate = stateStartDate;
    }

    public Date getStateEndDate() {
        return stateEndDate;
    }

    public void setStateEndDate(Date stateEndDate) {
        this.stateEndDate = stateEndDate;
    }

//    public float getIntRatePerHour() {
//        return intRatePerHour;
//    }
//
//    public void setIntRatePerHour(float intRatePerHour) {
//        this.intRatePerHour = intRatePerHour;
//    }
//
//    public float getInvRatePerHour() {
//        return invRatePerHour;
//    }
//
//    public void setInvRatePerHour(float invRatePerHour) {
//        this.invRatePerHour = invRatePerHour;
//    }
    public String getSkillSet() {
        return skillSet;
    }

    public void setSkillSet(String skillSet) {
        this.skillSet = skillSet;
    }

    public List getEmpCurrentStatus() {
        return empCurrentStatus;
    }

    public void setEmpCurrentStatus(List empCurrentStatus) {
        this.empCurrentStatus = empCurrentStatus;
    }

    public String getDeleteAction() {
        return deleteAction;
    }

    public void setDeleteAction(String deleteAction) {
        this.deleteAction = deleteAction;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPreviousEmpState() {
        return previousEmpState;
    }

    public void setPreviousEmpState(String previousEmpState) {
        if (previousEmpState == null) {
            previousEmpState = "";
        }
        this.previousEmpState = previousEmpState;
    }

    public StateVTO getCurrentEmployeeState() {
        return currentEmployeeState;
    }

    public void setCurrentEmployeeState(StateVTO currentEmployeeState) {
        this.currentEmployeeState = currentEmployeeState;
    }

    public Collection getCurrentStateHistoryCollection() {
        return currentStateHistoryCollection;
    }

    public void setCurrentStateHistoryCollection(Collection currentStateHistoryCollection) {
        this.currentStateHistoryCollection = currentStateHistoryCollection;
    }

    public List getSubPracticeList() {
        return subPracticeList;
    }

    public void setSubPracticeList(List subPracticeList) {
        this.subPracticeList = subPracticeList;
    }

    public String getSubPractice() {
        return subPractice;
    }

    public void setSubPractice(String subPractice) {
        this.subPractice = subPractice;
    }

    public boolean isIsTeamLead() {
        return isTeamLead;
    }

    public void setIsTeamLead(boolean isTeamLead) {
        this.isTeamLead = isTeamLead;
    }

    public String getEmpno() {
        return empno;
    }

    public void setEmpno(String empno) {
        this.empno = empno;
    }

    public String getNsrno() {
        return nsrno;
    }

    public void setNsrno(String nsrno) {
        this.nsrno = nsrno;
    }

    public String getPrjName() {
        return prjName;
    }

    public void setPrjName(String prjName) {
        this.prjName = prjName;
    }

//    public double getCtcPerYear() {
//        return ctcPerYear;
//    }
//
//    public void setCtcPerYear(double ctcPerYear) {
//        this.ctcPerYear = ctcPerYear;
//    }
    public String getItgBatch() {
        return itgBatch;
    }

    public void setItgBatch(String itgBatch) {
        this.itgBatch = itgBatch;
    }

    public List getTerritoryList() {
        return territoryList;
    }

    public void setTerritoryList(List territoryList) {
        this.territoryList = territoryList;
    }

    public List getRegionList() {
        return regionList;
    }

    public void setRegionList(List regionList) {
        this.regionList = regionList;
    }

    public String getWorkingCountry() {
        return workingCountry;
    }

    public void setWorkingCountry(String workingCountry) {
        this.workingCountry = workingCountry;
    }

    public String getIsOnsite() {
        return isOnsite;
    }

    public void setIsOnsite(String isOnsite) {
        this.isOnsite = isOnsite;
    }

    public String getTempName() {
        return tempName;
    }

    public void setTempName(String tempName) {
        this.tempName = tempName;
    }

    public String getTempPh() {
        return tempPh;
    }

    public void setTempPh(String tempPh) {
        this.tempPh = tempPh;
    }

    public String getTempCurStatus() {
        return tempCurStatus;
    }

    public void setTempCurStatus(String tempCurStatus) {
        this.tempCurStatus = tempCurStatus;
    }

    public String getTempDeptId() {
        return tempDeptId;
    }

    public void setTempDeptId(String tempDeptId) {
        this.tempDeptId = tempDeptId;
    }

    public String getTempOrgId() {
        return tempOrgId;
    }

    public void setTempOrgId(String tempOrgId) {
        this.tempOrgId = tempOrgId;
    }

    public int getCurrId() {
        return currId;
    }

    public void setCurrId(int currId) {
        this.currId = currId;
    }

    public int getTempVar() {
        return tempVar;
    }

    public void setTempVar(int tempVar) {
        this.tempVar = tempVar;
    }

    public String getSubmitFromReportsTo() {
        return submitFromReportsTo;
    }

    public void setSubmitFromReportsTo(String submitFromReportsTo) {
        this.submitFromReportsTo = submitFromReportsTo;
    }

    public Map getReportingPersons() {
        return reportingPersons;
    }

    public void setReportingPersons(Map reportingPersons) {
        this.reportingPersons = reportingPersons;
    }

    public String getReportingpersonId() {
        return reportingpersonId;
    }

    public void setReportingpersonId(String reportingpersonId) {
        this.reportingpersonId = reportingpersonId;
    }

    //new
    public File getImagePath() {
        return imagePath;
    }

    public void setImagePath(File imagePath) {
        this.imagePath = imagePath;
    }

    public int getNavId() {
        return navId;
    }

    public void setNavId(int navId) {
        this.navId = navId;
    }

    public boolean getIsCreTeam() {
        return isCreTeam;
    }

    public void setIsCreTeam(boolean isCreTeam) {
        this.isCreTeam = isCreTeam;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the isOperationTeam
     */
    public boolean getIsOperationTeam() {
        return isOperationTeam;
    }

    /**
     * @param isOperationTeam the isOperationTeam to set
     */
    public void setIsOperationTeam(boolean isOperationTeam) {
        this.isOperationTeam = isOperationTeam;
    }

    /**
     * @return the projectName
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * @param projectName the projectName to set
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the projectId
     */
    public String getProjectId() {
        return projectId;
    }

    /**
     * @param projectId the projectId to set
     */
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    /**
     * @return the accountId
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * @param accountId the accountId to set
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    /**
     * @return the myAccounts
     */
    public Map getMyAccounts() {
        return myAccounts;
    }

    /**
     * @param myAccounts the myAccounts to set
     */
    public void setMyAccounts(Map myAccounts) {
        this.myAccounts = myAccounts;
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

    public boolean getIsPMO() {
        return isPMO;
    }

    public void setIsPMO(boolean isPMO) {
        this.isPMO = isPMO;
    }

    /**
     * @return the bankName
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * @param bankName the bankName to set
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    /**
     * @return the accNum
     */
    public String getAccNum() {
        return accNum;
    }

    /**
     * @param accNum the accNum to set
     */
    public void setAccNum(String accNum) {
        this.accNum = accNum;
    }

    /**
     * @return the nameAsPerAcc
     */
    public String getNameAsPerAcc() {
        return nameAsPerAcc;
    }

    /**
     * @param nameAsPerAcc the nameAsPerAcc to set
     */
    public void setNameAsPerAcc(String nameAsPerAcc) {
        this.nameAsPerAcc = nameAsPerAcc;
    }

    /**
     * @return the ifscCode
     */
    public String getIfscCode() {
        return ifscCode;
    }

    /**
     * @param ifscCode the ifscCode to set
     */
    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    /**
     * @return the phyChallenged
     */
    public String getPhyChallenged() {
        return phyChallenged;
    }

    /**
     * @param phyChallenged the phyChallenged to set
     */
    public void setPhyChallenged(String phyChallenged) {
        this.phyChallenged = phyChallenged;
    }

    /**
     * @return the phyCategory
     */
    public String getPhyCategory() {
        return phyCategory;
    }

    /**
     * @param phyCategory the phyCategory to set
     */
    public void setPhyCategory(String phyCategory) {
        this.phyCategory = phyCategory;
    }

    /**
     * @return the aadharNum
     */
    public String getAadharNum() {
        return aadharNum;
    }

    /**
     * @param aadharNum the aadharNum to set
     */
    public void setAadharNum(String aadharNum) {
        this.aadharNum = aadharNum;
    }

    /**
     * @return the aadharName
     */
    public String getAadharName() {
        return aadharName;
    }

    /**
     * @param aadharName the aadharName to set
     */
    public void setAadharName(String aadharName) {
        this.aadharName = aadharName;
    }

    /**
     * @return the nameAsPerPan
     */
    public String getNameAsPerPan() {
        return nameAsPerPan;
    }

    /**
     * @param nameAsPerPan the nameAsPerPan to set
     */
    public void setNameAsPerPan(String nameAsPerPan) {
        this.nameAsPerPan = nameAsPerPan;
    }

    /**
     * @return the uanNo
     */
    public String getUanNo() {
        return uanNo;
    }

    /**
     * @param uanNo the uanNo to set
     */
    public void setUanNo(String uanNo) {
        this.uanNo = uanNo;
    }

    /**
     * @return the pfno
     */
    public String getPfno() {
        return pfno;
    }

    /**
     * @param pfno the pfno to set
     */
    public void setPfno(String pfno) {
        this.pfno = pfno;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getTechReviews() {
        return techReviews;
    }

    public void setTechReviews(String techReviews) {
        this.techReviews = techReviews;
    }

    /**
     * @return the lastRevisedDate
     */
    public Date getLastRevisedDate() {
        return lastRevisedDate;
    }

    /**
     * @param lastRevisedDate the lastRevisedDate to set
     */
    public void setLastRevisedDate(Date lastRevisedDate) {
        this.lastRevisedDate = lastRevisedDate;
    }

    /**
     * @return the revisedDate
     */
    public Date getRevisedDate() {
        return revisedDate;
    }

    /**
     * @param revisedDate the revisedDate to set
     */
    public void setRevisedDate(Date revisedDate) {
        this.revisedDate = revisedDate;
    }

    /**
     * @return the myTeamMembers
     */
    public Map getMyTeamMembers() {
        return myTeamMembers;
    }

    /**
     * @param myTeamMembers the myTeamMembers to set
     */
    public void setMyTeamMembers(Map myTeamMembers) {
        this.myTeamMembers = myTeamMembers;
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
     * @return the managerTeamMembersList
     */
    public Map getManagerTeamMembersList() {
        return managerTeamMembersList;
    }

    /**
     * @param managerTeamMembersList the managerTeamMembersList to set
     */
    public void setManagerTeamMembersList(Map managerTeamMembersList) {
        this.managerTeamMembersList = managerTeamMembersList;
    }

    /**
     * @return the managerTeamMember
     */
    public String getManagerTeamMember() {
        return managerTeamMember;
    }

    /**
     * @param managerTeamMember the managerTeamMember to set
     */
    public void setManagerTeamMember(String managerTeamMember) {
        this.managerTeamMember = managerTeamMember;
    }

    /**
     * @return the currentAction
     */
    public String getCurrentAction() {
        return currentAction;
    }

    /**
     * @param currentAction the currentAction to set
     */
    public void setCurrentAction(String currentAction) {
        this.currentAction = currentAction;
    }

    /**
     * @return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return the utilization
     */
    public int getUtilization() {
        return utilization;
    }

    /**
     * @param utilization the utilization to set
     */
    public void setUtilization(int utilization) {
        this.utilization = utilization;
    }

    /**
     * @return the comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * @return the projectTeamReportsTo
     */
    public Map getProjectTeamReportsTo() {
        return projectTeamReportsTo;
    }

    /**
     * @param projectTeamReportsTo the projectTeamReportsTo to set
     */
    public void setProjectTeamReportsTo(Map projectTeamReportsTo) {
        this.projectTeamReportsTo = projectTeamReportsTo;
    }

    /**
     * @return the isBillable
     */
    public String getIsBillable() {
        return isBillable;
    }

    /**
     * @param isBillable the isBillable to set
     */
    public void setIsBillable(String isBillable) {
        this.isBillable = isBillable;
    }

    /**
     * @return the isInternationalWorker
     */
    public boolean getIsInternationalWorker() {
        return isInternationalWorker;
    }

    /**
     * @param isInternationalWorker the isInternationalWorker to set
     */
    public void setIsInternationalWorker(boolean isInternationalWorker) {
        this.isInternationalWorker = isInternationalWorker;
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
     * @return the practiceList
     */
    public List getPracticeList() {
        return practiceList;
    }

    /**
     * @param practiceList the practiceList to set
     */
    public void setPracticeList(List practiceList) {
        this.practiceList = practiceList;
    }

    /**
     * @return the empStatus
     */
    public String getEmpStatus() {
        return empStatus;
    }

    /**
     * @param empStatus the empStatus to set
     */
    public void setEmpStatus(String empStatus) {
        this.empStatus = empStatus;
    }

    /**
     * @return the myPMOTeamMembers
     */
    public Map getMyPMOTeamMembers() {
        return myPMOTeamMembers;
    }

    /**
     * @param myPMOTeamMembers the myPMOTeamMembers to set
     */
    public void setMyPMOTeamMembers(Map myPMOTeamMembers) {
        this.myPMOTeamMembers = myPMOTeamMembers;
    }

    /**
     * @return the dateOfTermination
     */
    public Date getDateOfTermination() {
        return dateOfTermination;
    }

    /**
     * @param dateOfTermination the dateOfTermination to set
     */
    public void setDateOfTermination(Date dateOfTermination) {
        this.dateOfTermination = dateOfTermination;
    }

    /**
     * @return the reasonsForTerminate
     */
    public String getReasonsForTerminate() {
        return reasonsForTerminate;
    }

    /**
     * @param reasonsForTerminate the reasonsForTerminate to set
     */
    public void setReasonsForTerminate(String reasonsForTerminate) {
        this.reasonsForTerminate = reasonsForTerminate;
    }

    /**
     * @return the prvexpMnths
     */
    public String getPrvexpMnths() {
        return prvexpMnths;
    }

    /**
     * @param prvexpMnths the prvexpMnths to set
     */
    public void setPrvexpMnths(String prvexpMnths) {
        this.prvexpMnths = prvexpMnths;
    }

    /**
     * @return the prvexpYears
     */
    public String getPrvexpYears() {
        return prvexpYears;
    }

    /**
     * @param prvexpYears the prvexpYears to set
     */
    public void setPrvexpYears(String prvexpYears) {
        this.prvexpYears = prvexpYears;
    }

    /**
     * @return the upload
     */
    public File getUpload() {
        return upload;
    }

    /**
     * @param upload the upload to set
     */
    public void setUpload(File upload) {
        this.upload = upload;
    }

    /**
     * @return the uploadContentType
     */
    public String getUploadContentType() {
        return uploadContentType;
    }

    /**
     * @param uploadContentType the uploadContentType to set
     */
    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    /**
     * @return the uploadFileName
     */
    public String getUploadFileName() {
        return uploadFileName;
    }

    /**
     * @param uploadFileName the uploadFileName to set
     */
    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    /**
     * @return the filepath
     */
    public String getFilepath() {
        return filepath;
    }

    /**
     * @param filepath the filepath to set
     */
    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    /**
     * @return the projectContactId
     */
    public int getProjectContactId() {
        return projectContactId;
    }

    /**
     * @param projectContactId the projectContactId to set
     */
    public void setProjectContactId(int projectContactId) {
        this.projectContactId = projectContactId;
    }

    /**
     * @return the lateralFlag
     */
    public boolean getLateralFlag() {
        return lateralFlag;
    }

    /**
     * @param lateralFlag the lateralFlag to set
     */
    public void setLateralFlag(boolean lateralFlag) {
        this.lateralFlag = lateralFlag;
    }

    /**
     * @return the clientMap
     */
    public Map getClientMap() {
        return clientMap;
    }

    /**
     * @param clientMap the clientMap to set
     */
    public void setClientMap(Map clientMap) {
        this.clientMap = clientMap;
    }

    /**
     * @return the empName
     */
    public String getEmpName() {
        return empName;
    }

    /**
     * @param empName the empName to set
     */
    public void setEmpName(String empName) {
        this.empName = empName;
    }

    /**
     * @return the empDateOFBirth
     */
    public String getEmpDateOFBirth() {
        return empDateOFBirth;
    }

    /**
     * @param empDateOFBirth the empDateOFBirth to set
     */
    public void setEmpDateOFBirth(String empDateOFBirth) {
        this.empDateOFBirth = empDateOFBirth;
    }

    /**
     * @return the isLateral
     */
    public String getIsLateral() {
        return isLateral;
    }

    /**
     * @param isLateral the isLateral to set
     */
    public void setIsLateral(String isLateral) {
        this.isLateral = isLateral;
    }

    /**
     * @return the quarterAppraisalDetails
     */
    public String getQuarterAppraisalDetails() {
        return quarterAppraisalDetails;
    }

    /**
     * @param quarterAppraisalDetails the quarterAppraisalDetails to set
     */
    public void setQuarterAppraisalDetails(String quarterAppraisalDetails) {
        this.quarterAppraisalDetails = quarterAppraisalDetails;
    }

    /**
     * @return the rowCount
     */
    public int getRowCount() {
        return rowCount;
    }

    /**
     * @param rowCount the rowCount to set
     */
    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    /**
     * @return the curretRole
     */
    public String getCurretRole() {
        return curretRole;
    }

    /**
     * @param curretRole the curretRole to set
     */
    public void setCurretRole(String curretRole) {
        this.curretRole = curretRole;
    }

    /**
     * @return the appraisalId
     */
    public int getAppraisalId() {
        return appraisalId;
    }

    /**
     * @param appraisalId the appraisalId to set
     */
    public void setAppraisalId(int appraisalId) {
        this.appraisalId = appraisalId;
    }

    /**
     * @return the lineId
     */
    public int getLineId() {
        return lineId;
    }

    /**
     * @param lineId the lineId to set
     */
    public void setLineId(int lineId) {
        this.lineId = lineId;
    }

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @return the quarterly
     */
    public String getQuarterly() {
        return quarterly;
    }

    /**
     * @param quarterly the quarterly to set
     */
    public void setQuarterly(String quarterly) {
        this.quarterly = quarterly;
    }

    /**
     * @return the shortTermGoal
     */
    public String getShortTermGoal() {
        return shortTermGoal;
    }

    /**
     * @param shortTermGoal the shortTermGoal to set
     */
    public void setShortTermGoal(String shortTermGoal) {
        this.shortTermGoal = shortTermGoal;
    }

    /**
     * @return the shortTermGoalComments
     */
    public String getShortTermGoalComments() {
        return shortTermGoalComments;
    }

    /**
     * @param shortTermGoalComments the shortTermGoalComments to set
     */
    public void setShortTermGoalComments(String shortTermGoalComments) {
        this.shortTermGoalComments = shortTermGoalComments;
    }

    /**
     * @return the longTermGoal
     */
    public String getLongTermGoal() {
        return longTermGoal;
    }

    /**
     * @param longTermGoal the longTermGoal to set
     */
    public void setLongTermGoal(String longTermGoal) {
        this.longTermGoal = longTermGoal;
    }

    /**
     * @return the longTermGoalComments
     */
    public String getLongTermGoalComments() {
        return longTermGoalComments;
    }

    /**
     * @param longTermGoalComments the longTermGoalComments to set
     */
    public void setLongTermGoalComments(String longTermGoalComments) {
        this.longTermGoalComments = longTermGoalComments;
    }

    /**
     * @return the strength
     */
    public String getStrength() {
        return strength;
    }

    /**
     * @param strength the strength to set
     */
    public void setStrength(String strength) {
        this.strength = strength;
    }

    /**
     * @return the strengthsComments
     */
    public String getStrengthsComments() {
        return strengthsComments;
    }

    /**
     * @param strengthsComments the strengthsComments to set
     */
    public void setStrengthsComments(String strengthsComments) {
        this.strengthsComments = strengthsComments;
    }

    /**
     * @return the improvements
     */
    public String getImprovements() {
        return improvements;
    }

    /**
     * @param improvements the improvements to set
     */
    public void setImprovements(String improvements) {
        this.improvements = improvements;
    }

    /**
     * @return the improvementsComments
     */
    public String getImprovementsComments() {
        return improvementsComments;
    }

    /**
     * @param improvementsComments the improvementsComments to set
     */
    public void setImprovementsComments(String improvementsComments) {
        this.improvementsComments = improvementsComments;
    }

    /**
     * @return the rejectedComments
     */
    public String getRejectedComments() {
        return rejectedComments;
    }

    /**
     * @param rejectedComments the rejectedComments to set
     */
    public void setRejectedComments(String rejectedComments) {
        this.rejectedComments = rejectedComments;
    }

    /**
     * @return the operationTeamStatus
     */
    public String getOperationTeamStatus() {
        return operationTeamStatus;
    }

    /**
     * @param operationTeamStatus the operationTeamStatus to set
     */
    public void setOperationTeamStatus(String operationTeamStatus) {
        this.operationTeamStatus = operationTeamStatus;
    }

    /**
     * @return the managerRejectedComments
     */
    public String getManagerRejectedComments() {
        return managerRejectedComments;
    }

    /**
     * @param managerRejectedComments the managerRejectedComments to set
     */
    public void setManagerRejectedComments(String managerRejectedComments) {
        this.managerRejectedComments = managerRejectedComments;
    }

    /**
     * @return the operationRejectedComments
     */
    public String getOperationRejectedComments() {
        return operationRejectedComments;
    }

    /**
     * @param operationRejectedComments the operationRejectedComments to set
     */
    public void setOperationRejectedComments(String operationRejectedComments) {
        this.operationRejectedComments = operationRejectedComments;
    }

    /**
     * @return the dayCount
     */
    public int getDayCount() {
        return dayCount;
    }

    /**
     * @param dayCount the dayCount to set
     */
    public void setDayCount(int dayCount) {
        this.dayCount = dayCount;
    }

    /**
     * @return the accessCount
     */
    public int getAccessCount() {
        return accessCount;
    }

    /**
     * @param accessCount the accessCount to set
     */
    public void setAccessCount(int accessCount) {
        this.accessCount = accessCount;
    }

    /**
     * @return the empProjectStatus
     */
    public String getEmpProjectStatus() {
        return empProjectStatus;
    }

    /**
     * @param empProjectStatus the empProjectStatus to set
     */
    public void setEmpProjectStatus(String empProjectStatus) {
        this.empProjectStatus = empProjectStatus;
    }
}
