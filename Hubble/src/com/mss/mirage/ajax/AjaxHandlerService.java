/*
 * AjaxHandlerService.java
 *
 * Created on June 11, 2008, 12:57 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package com.mss.mirage.ajax;

import com.mss.mirage.util.ServiceLocatorException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import org.json.JSONObject;

/**
 *
 * @author miracle
 */
public interface AjaxHandlerService {

    /**
     * Creates a new instance of AjaxHandlerService
     */
    public byte[] getEmployeeImage(String image) throws ServiceLocatorException;

    /**
     *
     * This method is used to get The States List
     *
     * @param country
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String getStates(String country) throws ServiceLocatorException;

    /**
     *
     * This method is used to get The Department of user
     *
     * @param departmentName
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String getEmployeeDepartment(String departmentName) throws ServiceLocatorException;

    /**
     *
     * This method is used to get the Team By Department
     *
     * @param teamName
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String getEmpTeamNames(String teamName) throws ServiceLocatorException;

    /**
     *
     * This method is used to get the Title of user
     *
     * @param departmentName
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String getEmpForTitles(String departmentName) throws ServiceLocatorException;

    /**
     *
     * This method is used to get the Practice of user
     *
     * @param practiceName
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String getEmpPractice(String practiceName) throws ServiceLocatorException;

    /**
     *
     * This method is used to get the Sub-Practice of user
     *
     * @param subPracticeName
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String getEmpSubPractice(String subPracticeName) throws ServiceLocatorException;

    /**
     *
     * This method is used to get the Managers & Teamleads List of a Department
     *
     * @param deptName
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String getEmpForReportsTo(String deptName) throws ServiceLocatorException;

    /**
     *
     * This method is used to get the Vacation list of user
     *
     * @param startDate
     * @param endDate
     * @param department
     * @param organization
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String getEmpVacationList(String startDate, String endDate, String department, String organization) throws ServiceLocatorException;

    //Hari Accounts
    /**
     *
     * This method is used to get the Account List
     *
     * @param accountName
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String getAccountList(String accountName) throws ServiceLocatorException;

    public String mergeAccounts(String accountName, String accountName2) throws ServiceLocatorException;

    /**
     *
     * This method is used to Assign An Account to user
     *
     * @param accountName
     * @param state
     * @param secondState
     * @param loginId
     * @param loginEMpName
     * @return httpServletRequest
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String assignAccount(String accountName, String state, String secondState, String loginId, String loginEMpName, HttpServletRequest httpServletRequest) throws ServiceLocatorException;

    /**
     *
     * This method is used to Transfer Accounts to user
     *
     * @param accId
     * @param oldTeamMember
     * @param newMember
     * @param optType
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String accountSearchUpdate(String accId, String oldTeamMember, String newMember, String optType) throws ServiceLocatorException;

    //sagar Employee
    /**
     *
     * This method is used to Search the Consultant List
     *
     * @param fname
     * @param skillset
     * @param email
     * @param practiceid
     * @param workAuthor
     * @param createdBy
     * @param location
     * @param comments
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String getConsultantSearch(String fname, String skillset, String email, String practiceid, String workAuthor, String createdBy, String location, String comments) throws ServiceLocatorException;

    /**
     *
     * This method is used to Search the Employees
     *
     * @param fName
     * @param lName
     * @param skils
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String getEmployeeSearch(String fName, String lName, String skils) throws ServiceLocatorException;

    /**
     *
     * This method is used to get user by Catagory
     *
     * @param catagory
     * @param catagoryId
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String catagory(String catagory, String catagoryId) throws ServiceLocatorException;

    /**
     *
     * This method is used to get the Territories
     *
     * @param query
     * @param region
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String getTerritoryData(String query, String region) throws ServiceLocatorException;

    /**
     *
     * This method is used to get the Sub-Projects
     *
     * @param query
     * @param project
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String getSubProject(String query, String project) throws ServiceLocatorException;

    /**
     *
     * This method is used to get the Account Name by Acc.Id
     *
     * @param test
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String getScreenAccount(String test) throws ServiceLocatorException;

    /**
     *
     * This method is used to get the Contact Details
     *
     * @param accountId
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String getScreenContactDetails(int accountId) throws ServiceLocatorException;

    /**
     *
     * This method is used to get the Account Details
     *
     * @param accountName
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String getAccountDetails(String accountName) throws ServiceLocatorException;

    /**
     *
     * This method is used to get the Contact Details
     *
     * @param contactId
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String getContactDetails(int contactId) throws ServiceLocatorException;

    /**
     *
     * This method is used to Save the Account Details
     *
     * @param accountId
     * @param accountName
     * @param urlPath
     * @param homePhone
     * @param stockSymbol1
     * @param lastModiyBy
     * @param modifiedDate
     * @param gentran
     * @param harbinger
     * @param mercator
     * @param seeBeyond
     * @param webMethods
     * @param WDI
     * @param ICS
     * @param messageBroker
     * @param tibco
     * @param vitria
     * @param WPS
     * @param biztalkServer
     * @param jdEdwards
     * @param oracleApps
     * @param peopleSoft
     * @param SAP
     * @param siebel
     * @param baan
     * @param beaPortals
     * @param oraclePortals
     * @param ibmPortals
     * @param sharePoint
     * @param sapPortals
     * @param microsoft
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String saveAccount(int accountId, String accountName, String urlPath, String homePhone,
            String stockSymbol1, String lastModiyBy, Timestamp modifiedDate, String gentran, String harbinger,
            String mercator, String seeBeyond, String webMethods, String WDI, String ICS, String messageBroker,
            String tibco, String vitria, String WPS, String biztalkServer, String jdEdwards, String oracleApps,
            String peopleSoft, String SAP, String siebel, String baan, String beaPortals, String oraclePortals,
            String ibmPortals, String sharePoint, String sapPortals, String microsoft) throws ServiceLocatorException;

    /**
     *
     * This method is used to Save the Contact Details
     *
     * @param accountId
     * @param contactId
     * @param firstName
     * @param lastName
     * @param middleName
     * @param email
     * @param phone
     * @param source
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String saveContact(int accountId, int contactId, String firstName, String lastName, String middleName,
            String email, String phone, String source) throws ServiceLocatorException;

    /**
     *
     * This method is used to Save the Activity Details
     *
     * @param activityType
     * @param priority
     * @param cId
     * @param assignedToId
     * @param status
     * @param dueDate
     * @param alarmValue
     * @param description
     * @param comments
     * @param accountId
     * @param contactId
     * @param createdById
     * @param modifiedById
     * @param assignedById
     * @param createdDate
     * @param modifiedDate
     * @param activityId
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String saveActivityDetails(String activityType, String priority, int cId, String assignedToId, String status,
            Timestamp dueDate, boolean alarmValue, String description, String comments, int accountId,
            int contactId, String createdById, String modifiedById, String assignedById, Timestamp createdDate,
            Timestamp modifiedDate, int activityId) throws ServiceLocatorException;

    /**
     *
     * This method is used to populate The Activity
     *
     * @param accId
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String popupActivity(int accId) throws ServiceLocatorException;

    /**
     *
     * This method is used to populate The Comment
     *
     * @param accId
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String popupComments(int accId) throws ServiceLocatorException;

    /*
     * GreenSheets
     */
    /**
     *
     * This method is used to get the Customer Details
     *
     * @param query
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String getCustomerDetails(String query) throws ServiceLocatorException;

    //hari projects Starts
    /**
     *
     * This method is used to get the Sub-Projects
     *
     * @param query
     * @param project
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String getSubProjects(String query, String project) throws ServiceLocatorException;

    /**
     *
     * This method is used to get the emp. of a project
     *
     * @param query
     * @param project
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String getProjEmployees(String query, String project) throws ServiceLocatorException;

    //Hari projects Ends
    /**
     *
     * This method is used to get the Consultant Details
     *
     * @param query
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String gsConsultant(String query) throws ServiceLocatorException;

    /**
     *
     * This method is used to get the the Phone & WebAddress Details
     *
     * @param query
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String greenSheet(String query) throws ServiceLocatorException;

    /**
     *
     * This method is used to get the consultant Details
     *
     * @param query
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String getConsultantDetails(HttpServletRequest httpServletRequest, String query) throws ServiceLocatorException;

    /**
     *
     * This method is used to get the Consultant Details
     *
     * @param consultantMail
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String getConsultDetails(String consultantMail) throws ServiceLocatorException;

    /**
     *
     * This method is used to get the Resume List of Consultant
     *
     * @param consultantId
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String getConsultantResume(int consultantId) throws ServiceLocatorException;

    /*
     * public String getRequirementDetails(String query) throws ServiceLocatorException;
     */
    /**
     *
     * This method is used to get the List of Consultant
     *
     * @param query
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String getConsultantList(String query) throws ServiceLocatorException;

    /**
     *
     * This method is used to get the Id of consultant Resume
     *
     * @param query
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String getResumeId(String query) throws ServiceLocatorException;

    /*
     *
     *
     * Calendar Module in CRM
     *
     *
     */
    /**
     *
     * This method is used to get the WeekDates
     *
     * @param cal
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String getWeekDates(Calendar cal) throws ServiceLocatorException;

    /**
     *
     * This method is used to get the Weekly Events
     *
     * @param queryString
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String getWeeklyEvents(String queryString) throws ServiceLocatorException;

    /**
     *
     * This method is used to get the Daily Events
     *
     * @param queryString
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String getDaillyEvents(String queryString) throws ServiceLocatorException;

    /**
     *
     * This method is used to populate the Events
     *
     * @param eventDate
     * @param empId
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String popupEvent(String eventDate, int empId) throws ServiceLocatorException;

    /**
     *
     * This method is used to get the Account Team Id
     *
     * @param accTeamId
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String getAccountTeamId(int accTeamId) throws ServiceLocatorException;

    /**
     *
     * This method is used to add the Event
     *
     * @param empId
     * @param eventDate
     * @param eventType
     * @param EventDesc
     * @param accTeam
     * @param createdBy
     * @param EveAccId
     * @param contactsId
     * @param calEveId
     * @param eventDate1
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public int addEvent(int empId, String eventDate, String eventType, String EventDesc, String accTeam, String createdBy, String EveAccId, String contactsId, int calEveId, String eventDate1) throws ServiceLocatorException;

    /**
     *
     * This method is used to get the Event Details
     *
     * @param eventId
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String getEventDetails(int eventId) throws ServiceLocatorException;

    /**
     *
     * This method is used to get the List of calendar Users
     *
     * @param empId
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String getCalendarUsersList(String empId) throws ServiceLocatorException;

    /**
     *
     * This method is used to populate the users List
     *
     * @param userName
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String getUserPopupList(String userName) throws ServiceLocatorException;

    /**
     *
     * This method is used to save the calendar Access of user
     *
     * @param empId
     * @param accessUserId
     * @param saveType
     * @param accessType
     * @param loginId
     * @param accessId
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String saveCalAccessUser(String empId, String accessUserId, String saveType, String accessType, String loginId, String accessId) throws ServiceLocatorException;

    /**
     *
     * This method is used to remove the access to user
     *
     * @param accessId
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String deleteCalAccessUser(String accessId) throws ServiceLocatorException;

    /**
     *
     * This method is used to give the access to calendar
     *
     * @param empId
     * @param calUserId
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String getAccessCalendar(String empId, String calUserId) throws ServiceLocatorException;

    /**
     *
     * This method is used to populate the Comments
     *
     * @param Id
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String popupComment(int Id) throws ServiceLocatorException;

    /**
     *
     * This method is used to add the Status to day
     *
     * @param empId
     * @param status
     * @param date
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public int addStatus(int empId, String status, Date date) throws ServiceLocatorException;

    /**
     *
     * This method is used to get the Day Status
     *
     * @param eventDate
     * @param empId
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String getDayStatus(String eventDate, int empId) throws ServiceLocatorException;

    /**
     *
     * This method is used to get the List Projects
     *
     * @param project
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String getProjectsList(String project) throws ServiceLocatorException;

    /**
     *
     * This method is used to add the project status
     *
     * @param userId
     * @param projectId
     * @param statusCode
     * @param reason
     * @return
     * @throws com.mss.mirage.util.ServiceLocatorException String
     */
    public int addProjectStatus(String userId, String projectId, String statusCode, String reason) throws ServiceLocatorException;

    /**
     *
     * This method is used to get the Total Holidays
     *
     * @param year
     * @param month
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public int getTotalHolidays(String year, String month) throws ServiceLocatorException;

    /**
     *
     * This method is used to get the Team Streangth
     *
     * @param userId
     * @param userList
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public int getTeamStrangth(String userId, String userList) throws ServiceLocatorException;

    /**
     *
     * This method is used to search the Greensheet list
     *
     * @param poStatus
     * @param poType
     * @param accountName
     * @param empFname
     * @param empLname
     * @param poStartDateFrom
     * @param poStartDateTo
     * @param poEndDateFrom
     * @param poEndDateTo
     * @param empStartDateFrom
     * @param empStartDateTo
     * @param empEndDateFrom
     * @param empEndDateTo
     * @param queryType
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String getGreensheetSearch(String poStatus, String poType, String accountName, String empFname, String empLname,
            String poStartDateFrom, String poStartDateTo, String poEndDateFrom, String poEndDateTo, String empStartDateFrom, String empStartDateTo,
            String empEndDateFrom, String empEndDateTo, String country, String queryType) throws ServiceLocatorException;

    /**
     *
     * This method is used to get the Venus Report
     *
     * @param startDate
     * @param endDate
     * @param empName
     * @param deptId
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String getVenusReport(Date startDate, Date endDate, String empName, String deptId) throws ServiceLocatorException;

    /**
     *
     * This method is used to get the list of Absentee Report
     *
     * @param startDate
     * @param endDate
     * @param empName
     * @param deptId
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String getAbsanteeReport(Date startDate, Date endDate, String empName, String deptId) throws ServiceLocatorException;

    /**
     *
     * This method is used to get the Dashboard Opportunities
     *
     * @param type
     * @param stage
     * @param dueStartDate
     * @param dueEndDate
     * @param createdBy
     * @param httpServletRequest
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    //public String getOppDashBoard(String type, String stage, String dueStartDate, String dueEndDate, String createdBy, HttpServletRequest httpServletRequest) throws ServiceLocatorException;
  //  public String getOppDashBoard(String type, String stage, String dueStartDate, String dueEndDate, String createdBy,String state,String dueDate, HttpServletRequest httpServletRequest) throws ServiceLocatorException;
   // public String getOppDashBoard(String type, String stage, String dueStartDate, String dueEndDate, String createdBy,String state,String dueDate,String practice,  HttpServletRequest httpServletRequest) throws ServiceLocatorException;
    public String getOppDashBoard(String type, String stage, String dueStartDate, String dueEndDate, String createdBy,String state,String dueDate,String practice,String SVINum,String SViStage,HttpServletRequest httpServletRequest) throws ServiceLocatorException;

    /**
     *
     * This method is used to Search The Greensheet list
     *
     * @param poType
     * @param poStatus
     * @param createdBy
     * @param startDate
     * @param endDate
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
   // public String greensheetListSearch(String poType, String poStatus, String createdBy, String startDate, String endDate, String country, String title) throws ServiceLocatorException;
 //   public String greensheetListSearch(String poType, String poStatus, String createdBy, String startDate, String endDate, String country, String title,String titleType) throws ServiceLocatorException;
 public String greensheetListSearch(String poType, String poStatus, String createdBy, String startDate, String endDate, String country, String title,String titleType,HttpServletRequest httpServletRequest) throws ServiceLocatorException;
    /**
     *
     * This method is used to get the Dashboard Activities
     *
     * @param startDate
     * @param endDate
     * @param team
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String getActivityDashBoard(Date startDate, Date endDate, String team, String curWorkCountry) throws ServiceLocatorException;

    /**
     *
     * This method is used to populate The Skills of Consultant
     *
     * @param consultantId
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String popupSkills(int consultantId) throws ServiceLocatorException;

    public String popupReqSkills(int requirementId) throws ServiceLocatorException;

    public String popupReqPersonDetails(String personId) throws ServiceLocatorException;

    public String getTimeSheetsReport(String dept, String weekStartDate, String weekEndDate) throws ServiceLocatorException;

    public String getNotApprovedReport(String dept, String weekStartDate, String weekEndDate) throws ServiceLocatorException;

   // public String getContactSearch(String name, int accId) throws ServiceLocatorException;
    public String getContactSearch(String name, String title,int accId) throws ServiceLocatorException;

    public String getTotalLeaves(String employeeId, String year) throws ServiceLocatorException;

    public String getEmpTotalLeaves(String month, String year, String userWorkCountry) throws ServiceLocatorException;

    public String getEmpTotalLeavesByDate(String fname, String lname, String country, String startdate, String enddate, String opsContactId) throws ServiceLocatorException;

    public String getNameOfAccount(AjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException;

    //public String getAccountsByRep(String StartDate, String EndDate, HttpServletRequest httpServletRequest) throws ServiceLocatorException;
    //public String getAccountsByRep(String StartDate, String EndDate, HttpServletRequest httpServletRequest, String practiceName) throws ServiceLocatorException;
    public String getAccountsByRep(String StartDate, String EndDate, HttpServletRequest httpServletRequest, String practiceName ,String teamMemberId) throws ServiceLocatorException;

    //New Methods for Ajax Handlings
    public String getactivitySearch(int accActivityId) throws ServiceLocatorException;

    // public String getAllActivitySearch(String name,int accActivityId) throws ServiceLocatorException;
    public String getAllActivitySearch(String name, int accActivityId) throws ServiceLocatorException;

    //New for contact list in contactdetails page
    public String getContactList(int AccId) throws ServiceLocatorException;

    public String getActivityLists(int contactId, int actAccId) throws ServiceLocatorException;

    public String getAddSubActivityList(int curcontactId, int actAccId) throws ServiceLocatorException;

    /**
     * New service for get the total leave of mployee.
     */
    public String popupLeaveDetails(int leaveId) throws ServiceLocatorException;

    /**
     * New action for displaying team accounts list
     */
    public String getNameOfAccountbyLoginId(AjaxHandlerAction ajaxHandlerAction, String loginId, Map teamMap) throws ServiceLocatorException;

    //New Methods for requirement list
    public String getrequirementAjaxList(HttpServletRequest httpServletRequest) throws ServiceLocatorException;

    public String getsearchRequirementAjaxList(HttpServletRequest httpServletRequest, AjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException;

    // New Action for getting territory and states List for Employee Id
    public String getTeritoryAndStatesList(String empId, HttpServletRequest httpServletRequest) throws ServiceLocatorException;
    //new on 10162012 for assign Accounts for BDM

    public String getAccounts(AjaxHandlerAction ajaxHandlerAction, HttpServletRequest httpServletRequest, String bdmLoginId) throws ServiceLocatorException;

    public String accountSearchUpdatebdm(String accId, String loginId) throws ServiceLocatorException;

    //public String accountSearchUpdatebdm(String accId, String loginId, String accountNames, String reportsTo)throws ServiceLocatorException;
    public String getEmpTerritory(String practiceName) throws ServiceLocatorException;

    public String getStatesByTerritory(AjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException;

    /**
     *
     * This method is used to get the ProjectNamesList of a Account/Customer
     *
     * @param accId
     * @return String
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String getProjectNamesList(String accId, String projectName) throws ServiceLocatorException;

    //newly added on 06072013
    public String getEmployeeDetails(String query) throws ServiceLocatorException;

    /**
     * New service to get issue comments.
     */
    public String popupIssueComments(int issueId) throws ServiceLocatorException;

    public String popupTaskDesc(int issueId) throws ServiceLocatorException;

    /**
     *
     *
     */
    public String getTopicNamesBasedOnDomain(int domainId) throws ServiceLocatorException;

    // public String getQuestion(int questionNo,HttpServletRequest httpServletRequest,int selectedAns,String navigation, int remainingQuestions,int onClickStatus,int subTopicId) throws ServiceLocatorException;
    public String getQuestion(int questionNo, HttpServletRequest httpServletRequest, int selectedAns, String navigation, int remainingQuestions, int onClickStatus, int subTopicId, int specficQuestionNo) throws ServiceLocatorException;
    //  public String getExamTimeAndCount(int topicId) throws ServiceLocatorException;

    public String getQuestionsAjaxList(int subTopicId, String userId) throws ServiceLocatorException;

    public String getCurrentQuestionsCount(int subTopicId, String userId, int totalQuestions) throws ServiceLocatorException;

    public String validateExam(String examValidationKey, HttpServletRequest httpServletRequest) throws ServiceLocatorException;

    public String getAvailableQuestionsCount(int topicId) throws ServiceLocatorException;
    /*
     * Ajay Tummapala
     */

    public String popupQuestionsWindow(int questionId) throws ServiceLocatorException;

    /*
     * Cre Comments
     */
    public String popupCreCommentsWindow(String creConsultantId, String level) throws ServiceLocatorException;

    //public String getCreRecordsList(String consultantId,String consultantName, String startDate, String toDate, String status, int category,String level,String interviewAt) throws ServiceLocatorException;
    public String getCreRecordsList(String consultantId, String startDate, String toDate, String status, String interviewAt, String consultantId1,String creCollegename,String course,String stream) throws ServiceLocatorException;
    // public String creRecordStatusUpdate(String consultantIds, String loginId, String status,String subtopicsList,int totalQues,int totDuration, int minMarks)throws ServiceLocatorException;

    public String creRecordStatusUpdate(String consultantIds, String loginId, String status, String examNameIdList) throws ServiceLocatorException;

    public String popupTechLeadComments(int techReviewId) throws ServiceLocatorException;

    public String popupHrLeadComments(int hrReviewId) throws ServiceLocatorException;

    public String popupVpComments(int vpReviewId) throws ServiceLocatorException;

    public String checkEmail(String email) throws ServiceLocatorException;

    public void insertAnswer(int questionNo, int selectedAns, int empId, int examKeyId, int subTopicId) throws ServiceLocatorException;

    public void updateAnswer(int questionNo, int selectedAns, int empId, int examKeyId) throws ServiceLocatorException;

    //public void insertCREConsultantSubTopics(int ConsultantId,String SubtopicList) throws ServiceLocatorException;
    /**
     * To add as an employee
     */
    public String addAsEmployee(int consultantId, String officeEmail, String empLoginId) throws ServiceLocatorException;

    public String popupWindowForCreatedDate(int Id) throws ServiceLocatorException;

    public String popupWindowForModifiedDate(int Id) throws ServiceLocatorException;

    public String getCreAvailCreQuestions(String subTopicsList) throws ServiceLocatorException;

    public String getCreDetailExamInfo(String examKeyId) throws ServiceLocatorException;

    public String getExamCandidateName(String empId, String topicName) throws ServiceLocatorException;

    public String popupCommentsWindow(int Id) throws ServiceLocatorException;

    public int empStateAccountDelete(String teamMemberId, String state) throws ServiceLocatorException;

    public String sendRepFeedback(String empName, String loginId, String reportsTo, int totalAccs, int noOfActivities, int workedAccs, String fromId) throws ServiceLocatorException;

    String sendPriorityEmail(String loginId, String reportsTo, String fromId, String accountName, String lastActivityDate, HttpServletRequest httpServletRequest) throws ServiceLocatorException;
    //new method for account summary by priority

  //  public String getAccountsByPriority(String teamMemberId, String teamName, HttpServletRequest httpServletRequest) throws ServiceLocatorException;

    public String getTeamName(String loginId, HttpServletRequest httpServletRequest) throws ServiceLocatorException;

    //new service to populate exam name  
    public String popupExamName(int examID) throws ServiceLocatorException;
    //Added by aditya

    public String getExamTypeName(String examType) throws ServiceLocatorException;

    public String getCreDetailExamDetails() throws ServiceLocatorException;

    //public String getEmployeeDetailsForProject(String customerName,String empType) throws ServiceLocatorException ;
    public String getEmployeeDetailsForProject(String customerName, String empType, String projectId, int accountId) throws ServiceLocatorException;
    //New service for project details

    public String getProjectTeamDetails(String projectId, HttpServletRequest httpServletRequest) throws ServiceLocatorException;

    // public String getProjectsForAccountId(int accountId,String resourceType) throws ServiceLocatorException;
    public String getProjectsForAccountId(int accountId, String resourceType, String empId) throws ServiceLocatorException;

    public String getEmployeePhoneNumber(String customerName, String empType, String projectId, int accountId) throws ServiceLocatorException;
    /*
     * Methods For Project Based timesheet search changes Date : 07/31/2014
     *
     */

    public String getProjectsByAccountId(int accountId) throws ServiceLocatorException;

   // public String getEmployeesByProjectId(String projectId, String workingCountry) throws ServiceLocatorException;
     public String getEmployeesByProjectId(String projectId, String livingCountry) throws ServiceLocatorException;

    public String getEmployeesByAccountId(int accountId, String workingCountry) throws ServiceLocatorException;
    //new

    public String getEmployeeDetailsForPMO(String query) throws ServiceLocatorException;

    /*
     * New Method For Populating Onboard Comments Date : 08/15/2014 Author :
     * Santosh Kola
     */
    public String popupOnboardComments(int onboardReviewId) throws ServiceLocatorException;

    //new
    public String getTechEmployeeDetails(HttpServletRequest httpServletRequest, String query) throws ServiceLocatorException;

//For Team Lead Timesheet Report Date : 09/02/2014
    public String getTeamByProjectId(String projectId, String empId, String loginId, HttpServletRequest httpServletRequest) throws ServiceLocatorException;
    //new rec dashboard

    public String consultantActivitiesByRep(String StartDate, String EndDate, String activityType, String recruiterName) throws ServiceLocatorException;

    //new
    public String getTaskEmpDetailsBasedOnIssueRel(String issueRel) throws ServiceLocatorException;

    public String getTaskEmpDetailsBasedOnPrjIssue(String projectId) throws ServiceLocatorException;

    public String getTaskEmpDetailsBasedOnHubbleNetworkInfraIssue(String issueRel) throws ServiceLocatorException;

    public String getAllEmpNames(HttpServletRequest httpServletRequest, String query) throws ServiceLocatorException;

//09262014--newtimesheets
    public String getEmpAssociatedProjectsList(String empId) throws ServiceLocatorException;

    public String checkPrimary(String projectId, int contactId) throws ServiceLocatorException;

    public String getReportsToAccess(String empId, String reportsToId, String reportsToLoginId, String resourceType) throws ServiceLocatorException;

    // New methods for Recruitment Dashbord 
    public String getTotalProfilesByPractice(String startDate, String endDate) throws ServiceLocatorException;

    public String getReqProfileSubmittedInfo(String practice, String startDate, String endDate) throws ServiceLocatorException;

    public String getReqClosedInfo(String StartDate, String EndDate) throws ServiceLocatorException;

    public String getReqStatusInfo(String StartDate, String EndDate) throws ServiceLocatorException;

    // New methods for activeManagerDetails    
    public String activeManagerDetailsByDates(String StartDate, String EndDate) throws ServiceLocatorException;

//new  for marketing account operations
    public String assignMarketingAcccounts(String state, String loginId, HttpServletRequest httpServletRequest) throws ServiceLocatorException;

//new methods for performance reviews
    public String getAllEmpNamesAlongWithTitle(HttpServletRequest httpServletRequest, String query) throws ServiceLocatorException;

    public String getMetricsList(String metricName, String status, HttpServletRequest httpServletRequest) throws ServiceLocatorException;

    public String getAllMetricNames(HttpServletRequest httpServletRequest, String metricName) throws ServiceLocatorException;

    public String getTitlesList(String metricName, String status, String deptId, String title, HttpServletRequest httpServletRequest) throws ServiceLocatorException;

    public String getAllMetricNames1(HttpServletRequest httpServletRequest, String metricName, String deptName, String title) throws ServiceLocatorException;

    public String setMetricRange(HttpServletRequest httpServletRequest, String deptName, String title) throws ServiceLocatorException;

    public String getAllReviewedPerformances(String loginId, String startDate, String endDate, String title, String dept, HttpServletRequest httpServletRequest) throws ServiceLocatorException;

    public String setMetricRange1(HttpServletRequest httpServletRequest, String deptName, String title, String performanceId) throws ServiceLocatorException;

    public String editPerformanceValues(HttpServletRequest httpServletRequest, String performanceId) throws ServiceLocatorException;

    public String updatePerformanceValues(HttpServletRequest httpServletRequest, String rating, String resWeightage, int metricId, int performanceLineId, String comments, int perfId) throws ServiceLocatorException;

    public String getAllEmpNamesAlongWithTitle1(HttpServletRequest httpServletRequest, String query) throws ServiceLocatorException;

    public String editTitleValues(HttpServletRequest httpServletRequest, String titleId) throws ServiceLocatorException;

    public String updateTitlleValues(HttpServletRequest httpServletRequest, AjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException;

    public String addTitleValues(HttpServletRequest httpServletRequest, AjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException;

    public String editMetricValues(HttpServletRequest httpServletRequest, String metricId) throws ServiceLocatorException;

    public String updateMetricValues(HttpServletRequest httpServletRequest, AjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException;

    public String addMetricValues(HttpServletRequest httpServletRequest, AjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException;

    public String getActivityDetailsByLoginId(String loginId, Date startDate, Date endDate) throws ServiceLocatorException;
    //Dual reports to changes

    public String getTeamByReportsToType(int teamType, String reportsToId, HttpServletRequest httpServletRequest) throws ServiceLocatorException;

    public String isDualReportsTo(String projectId) throws ServiceLocatorException;

    public boolean addReview(AjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException;

    public boolean updateReview(AjaxHandlerAction ajaxHandlerAction, String roleName) throws ServiceLocatorException;

    public String getApprovedByDetails(AjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException;

    public String getBasePoints(int reviewTypeId) throws ServiceLocatorException;

    public boolean updateMyReview(AjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException;

//new methods for recruitment activity graphs
    public String consultantActivitiesAsGraph(String StartDate, String EndDate, String activityType, String recruiterName, HttpServletRequest httpServletRequest) throws ServiceLocatorException;

//public String consultantActivitiesAsGraph(String StartDate,String EndDate,String activityType,String recruiterName) throws ServiceLocatorException;
    public String consultantActivitiesAsGraphInd(String StartDate, String EndDate, String activityType, String recruiterName) throws ServiceLocatorException;

    //new changes for account nmerge
    public String getMergeConsultants(String original, String duplicate) throws ServiceLocatorException;

    public String ConsultantAnalysis(String original, String duplicate) throws ServiceLocatorException;

    public String getIssueTypeBasedOnIssueRel(String issueRel) throws ServiceLocatorException;

    // new changes for sales activity graph
   //public String salesActivitiesAsGraphInd(String startDateSummaryGraph, String endDateSummaryGraph, String activityType, String recruiterName,int teamMemberCheck,String titleType) throws ServiceLocatorException;	
   // public String salesActivitiesAsGraphInd(String startDateSummaryGraph, String endDateSummaryGraph, String activityType, String recruiterName,int teamMemberCheck,String titleType,String campaignId) throws ServiceLocatorException;	
public String salesActivitiesAsGraphInd(String startDateSummaryGraph, String endDateSummaryGraph, String activityType, String recruiterName,int teamMemberCheck,String titleType,String campaignId,String sessionTitleType,HttpServletRequest httpServletRequest) throws ServiceLocatorException;	
    //public String salesActivitiesAsGraph(String startDateSummaryGraph, String endDateSummaryGraph, String activityType, String recruiterName, HttpServletRequest httpServletRequest) throws ServiceLocatorException;
    
public String salesActivitiesAsGraph(String startDateSummaryGraph, String endDateSummaryGraph, String activityType, String recruiterName, HttpServletRequest httpServletRequest,String campaignId) throws ServiceLocatorException;

    public String getReviewDetails(String reviewId) throws ServiceLocatorException;

    public String getEmpTotalReviews(String userId, int reviewTypeId) throws ServiceLocatorException;

    public String getEmpReviewComments(String reviewId) throws ServiceLocatorException;

    public String getTlReviewComments(String reviewId) throws ServiceLocatorException;

    public String getHrReviewComments(String reviewId) throws ServiceLocatorException;

    public String getEmployeesByDept(String deptName) throws ServiceLocatorException;

    public String getEmployeeSkillSet(String empId, String currId) throws ServiceLocatorException;

    public String getRequirementAdminAjaxList(HttpServletRequest httpServletRequest) throws ServiceLocatorException;

    public String searchAdminRequirementAjaxList(HttpServletRequest httpServletRequest, AjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException;

    public String searchpmoActivityAjaxList(HttpServletRequest httpServletRequest, AjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException;

    public String getMyProjectsByAccountId(int accountId, String resourceType, String empId) throws ServiceLocatorException;

    //perf. Dashboard 
    public String getTopPerformers(String startDate, String endDate, boolean isManagerInclude, String department) throws ServiceLocatorException;

    public String getEmployeesForPerformers(String startDate, String endDate, boolean isManagerInclude, String department) throws ServiceLocatorException;

    public String getPastReviewData(String loginId) throws ServiceLocatorException;

    public String getEmployeeReviewDetails(String startDate, String endDate, String userId, boolean isManager) throws ServiceLocatorException;

    public String getEmployeePastReviewData(String loginId, String startDate, String endDate) throws ServiceLocatorException;

    public String getAccountContactsDescription(int id) throws ServiceLocatorException;

    public String getEmpolyeeCount() throws ServiceLocatorException;

    public String getRequirementCount(int pastMonths) throws ServiceLocatorException;

    public String getGreenSheetCount(int pastMonths, boolean external) throws ServiceLocatorException;

    public String getOpportunitiesCounts(int pastMonths) throws ServiceLocatorException;

     public String getOpportunitiesCount(int pastMonths,String OpportunityState, HttpServletRequest httpServletRequest) throws ServiceLocatorException;

    public String getConsultantStatusDetails(AjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException;

    public String getSubmissionReport(AjaxHandlerAction ajaxHandlerAction, HttpServletRequest httpServletRequest) throws ServiceLocatorException;

    public String getConsultantListForRequirement(AjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException;

    public String popupTechRatingsWindow(int Id) throws ServiceLocatorException;

    public String popupNewTechCommentsWindow(int Id) throws ServiceLocatorException;

    public String getMyTeam(String loginId, String departmentName) throws ServiceLocatorException;

    public String getRequirementStatusReport(AjaxHandlerAction ajaxHandlerAction, HttpServletRequest httpServletRequest) throws ServiceLocatorException;

    public String getAccountsByPriorities(String teamMemberId, String teamName, HttpServletRequest httpServletRequest) throws ServiceLocatorException;
   // public String getAccountsByPriorities(String teamMemberId, String teamName,String dashBoardStartDate,String dashBoardEndDate,  HttpServletRequest httpServletRequest) throws ServiceLocatorException;


    public String getBDMDashboardInfo(AjaxHandlerAction ajaxHandlerAction, HttpServletRequest httpServletRequest) throws ServiceLocatorException;

    public String getAccountNamesList(String query) throws ServiceLocatorException;

    public String getBDMDashboardRevenueInfo(AjaxHandlerAction ajaxHandlerAction, HttpServletRequest httpServletRequest) throws ServiceLocatorException;

    public String getBDMDashboardOppertunitiesInfo(AjaxHandlerAction ajaxHandlerAction, HttpServletRequest httpServletRequest) throws ServiceLocatorException;

    public String getOpportunitesByPractice(AjaxHandlerAction ajaxHandlerAction, HttpServletRequest httpServletRequest) throws ServiceLocatorException;

    public String getRequirementsByPractice(AjaxHandlerAction ajaxHandlerAction, HttpServletRequest httpServletRequest) throws ServiceLocatorException;

    public String getgreenSheetsDetailsByPractice(AjaxHandlerAction ajaxHandlerAction, HttpServletRequest httpServletRequest) throws ServiceLocatorException;

    public String getOpportunitiesDetailsByPractice(AjaxHandlerAction ajaxHandlerAction, HttpServletRequest httpServletRequest) throws ServiceLocatorException;

    public String getRequirementDetailsByPractice(AjaxHandlerAction ajaxHandlerAction, HttpServletRequest httpServletRequest) throws ServiceLocatorException;

    public String getGreenSheetDetailsByPractice(AjaxHandlerAction ajaxHandlerAction, HttpServletRequest httpServletRequest) throws ServiceLocatorException;

    public String getaccountRenewalByTeamMember(String teamMemberId, HttpServletRequest httpServletRequest) throws ServiceLocatorException;

    /********** Utilization Report mehod *****/
    public String getUtilizationReport(AjaxHandlerAction ajaxHandlerAction, HttpServletRequest httpServletRequest) throws ServiceLocatorException;

    public String popupSkillSetWindow(String personId) throws ServiceLocatorException;
    //public String getEmpSkillSet(String empId) throws ServiceLocatorException;

  //  public String getActivitySummaryByLoginId(String startDateSummaryGraph, String endDateSummaryGraph, String activityType, String recruiterName, HttpServletRequest httpServletRequest) throws ServiceLocatorException;
 //   public String getActivitySummaryByLoginId(String startDateSummaryGraph, String endDateSummaryGraph, String activityType, String recruiterName, HttpServletRequest httpServletRequest,int teamMemberCheck) throws ServiceLocatorException;
 //   public String getActivitySummaryByLoginId(String startDateSummaryGraph, String endDateSummaryGraph, String activityType, String recruiterName, HttpServletRequest httpServletRequest,int teamMemberCheck,String titleType) throws ServiceLocatorException;
 // public String getActivitySummaryByLoginId(String startDateSummaryGraph, String endDateSummaryGraph, String activityType, String recruiterName, HttpServletRequest httpServletRequest,int teamMemberCheck,String titleType,String campaignId) throws ServiceLocatorException;
    public String getActivitySummaryByLoginId(String startDateSummaryGraph, String endDateSummaryGraph, String activityType, String recruiterName, HttpServletRequest httpServletRequest,int teamMemberCheck,String campaignId) throws ServiceLocatorException;

    public String getActivityDecriptionById(int activityId) throws ServiceLocatorException;

    public String getRequirementDetailsByStatus(AjaxHandlerAction ajaxHandlerAction, HttpServletRequest httpServletRequest) throws ServiceLocatorException;

   // public String getMyOppDashBoard(String type, String state, String dueStartDate, String dueEndDate, HttpServletRequest httpServletRequest) throws ServiceLocatorException;
    public String getMyOppDashBoard(String type, String state, String dueStartDate, String dueEndDate,String practice, HttpServletRequest httpServletRequest) throws ServiceLocatorException;

    public String getAccomadationReport(AjaxHandlerAction ajaxHandlerAction, HttpServletRequest httpServletRequest) throws ServiceLocatorException;

  //  public String getPreSalesOppDashBoard(String type, String state, String dueStartDate, String dueEndDate, String teamMemberId, HttpServletRequest httpServletRequest) throws ServiceLocatorException;
    public String getPreSalesOppDashBoard(String type, String state, String dueStartDate, String dueEndDate, String teamMemberId,String practice, HttpServletRequest httpServletRequest) throws ServiceLocatorException;

    public String getPresalesOnsiteOffshoreDashBoard(String country, String teamMemberId, HttpServletRequest httpServletRequest) throws ServiceLocatorException;

    public String getPreSalesProjectDashBoard(String startDate, String endDate, String teamMemberId, String state, HttpServletRequest httpServletRequest) throws ServiceLocatorException;

    ;


/********** Sales KPI Report mehod *****/

    public String getsalesKPIReport(AjaxHandlerAction ajaxHandlerAction, HttpServletRequest httpServletRequest) throws ServiceLocatorException;

    public String popupTasksRiskDescWindow(int riskId) throws ServiceLocatorException;

    public String popupTasksRiskResolutionWindow(int riskId) throws ServiceLocatorException;

    /********** Recruitment KPI Report mehod *****/
    public String getRecruitmentKpiReport(AjaxHandlerAction ajaxHandlerAction, HttpServletRequest httpServletRequest) throws ServiceLocatorException;

    public String addProjectTaskDetails(AjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException;

    public String getProjectTaskDetails(int taskId) throws ServiceLocatorException;

    public String doUpdateProjectTaskDetails(AjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException;

    public String getAvailableEmpReport(AjaxHandlerAction aThis, HttpServletRequest httpServletRequest) throws ServiceLocatorException;
    
    /* Bridge Management System  */
    public String addBMSBridgeDetails(AjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException;
    public String getBmsBridgeDetails(String bCode) throws ServiceLocatorException;
    public String doUpdateBMSBridgeDetails(AjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException;
    public String doAvailableBridgeCheck(AjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException;
    public String doAddBridgeEvent(AjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException;
    public String getBridgeList(String bridgeDate,String loginId) throws ServiceLocatorException;
    public String isActiveBridge(String bridgeCode) throws ServiceLocatorException;
    public String getAvailableBridges(String bridgeDate,String startTime,String midDayFrom,String timeZone,int duration,String durationType) throws ServiceLocatorException;
    public String doCancelBridgeEvent(int id,String reason,String loginId) throws ServiceLocatorException;
    public String getBridgeEventSearchDetails(AjaxHandlerAction ajaxHandlerAction,String loginId) throws ServiceLocatorException;
    public String createBridgeIssue(AjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException;
    /* Bridge Management System End  */
    
    
// public boolean addInvestmentdetails(AjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException;
 //   public boolean updateInvestmentdetails(AjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException;
    
     public String getInvestmentDetails(AjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException;
	 public String contactSearchAjaxList(HttpServletRequest httpServletRequest, AjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException;
         public String getCampaignContactsList( HttpServletRequest httpServletRequest,AjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException;
 public String getCampaignSearch(AjaxHandlerAction ajaxHandlerAction,int userRoleId) throws ServiceLocatorException;
public String getLeadDetailsList(int accountId,String roleName) throws ServiceLocatorException;
public String getaccountRenewalByState(String State, HttpServletRequest httpServletRequest) throws ServiceLocatorException;
public String getReqDashBoard(AjaxHandlerAction ajaxHandlerAction ,String myTeamMembers, HttpServletRequest httpServletRequest) throws ServiceLocatorException;
 public String getProjectMembers(String projectId) throws ServiceLocatorException;

public int timeSheetAttachemntUpload(AjaxHandlerAction ajaxHandlerAction) ;
public void deleteTeAttachment(String empId,int timesheetId);

// Emeet Actions
 public String doAddExecitiveMeetAttendees(AjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException;
public JSONObject doEditExeMeetingAttendees(int Id) throws ServiceLocatorException;
public String doUpdateExecitiveMeetingAttendeesDetails(AjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException;

//currnet status management start
 public String searchCustomerProjectsAjaxList(HttpServletRequest httpServletRequest, AjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException;
        public String isExistedProjectName(int accountId,String projectName) throws ServiceLocatorException;
public String employeeAvailableProjects(String empId,String status,String startDate) throws ServiceLocatorException;
//currnet status management end
 public String popupOpertunitiesWindow(String Id)throws ServiceLocatorException;
 public String getPFPortalReport(AjaxHandlerAction aThis, HttpServletRequest httpServletRequest) throws ServiceLocatorException;
 public String ajaxAccountExcelFileUpload(AjaxHandlerAction ajaxHandlerAction)throws ServiceLocatorException;
   public String getEmployeesBasedOnCustomer(AjaxHandlerAction aThis, HttpServletRequest httpServletRequest);
 public String getAllProjectsByAccountId(int accountId) throws ServiceLocatorException;

        public String getEmployeeDetailforPMOActivity(String customerName) throws ServiceLocatorException;
         public String getOnboardReport(AjaxHandlerAction aThis,HttpServletRequest httpServletRequest) throws ServiceLocatorException;
public String addOrUpdateInvestmentdetails(AjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException ;

 public String getTerminationDetails(String empId,String loginId) throws ServiceLocatorException;
 public String doUpdateTerminationDetails(String  empId,Date dateOfTermination,String reasonsForTerminate) throws ServiceLocatorException;
public String getEmployeeLocations(String country) throws ServiceLocatorException ;


public String getOpportunityList(String state, int accId) throws ServiceLocatorException;
public String popupOpportunityDescription(int Id) throws ServiceLocatorException;
public String getMissingDataReport(AjaxHandlerAction aThis,HttpServletRequest httpServletRequest) throws ServiceLocatorException;
   public String getCustOppDashBoard(String state, String dueStartDate, String dueEndDate,String practice,int sviValue, HttpServletRequest httpServletRequest) throws ServiceLocatorException;
 public String getRequirementList(String requirementState, int conAccId);
 

//public String getLeadSearch(String leadTitle, String leadStatus,String createdDateFrom,String createdDateTo,String inactiveDateFrom,String inactiveDateTo,int investmentId,String analystId,String state) throws ServiceLocatorException;
 //public String getLeadSearch(String leadTitle, String leadStatus,String createdDateFrom,String createdDateTo,String inactiveDateFrom,String inactiveDateTo,int investmentId,String analystId,String state,String userId,int AdminFlag,HttpServletRequest httpServletRequest) throws ServiceLocatorException;
 public String getLeadSearch(String leadTitle, String leadStatus,String createdDateFrom,String createdDateTo,String inactiveDateFrom,String inactiveDateTo,int investmentId,String analystId,String state,String priority,String userId,int AdminFlag,HttpServletRequest httpServletRequest) throws ServiceLocatorException;
public String getAccountDetailsById(int accountId)  throws ServiceLocatorException;
public String getContactNamesList(String accId) throws ServiceLocatorException;
 public String getOppurtunitySearch(int investmentId,String oppurtunityAccountName,String oppurtunitiesPersonId) throws ServiceLocatorException;
 public String getOppurtunityAccountDetails(int oppurtunityId)  throws ServiceLocatorException;
 
 public String doConstantContactListSearch(AjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException;
public String doCampaignContactListSearch(AjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException;
public String doGetCampaignEmailsList(String campaignId,String requirement) throws ServiceLocatorException;
public String doSynchronizeToHubble(String contactList) throws ServiceLocatorException;
public String doConstantContactEmailList(String contactId,String contactEmailID) throws ServiceLocatorException;
public String doGetSyncListSearch(String locationName) throws ServiceLocatorException;
public String dotrackingEmailCampaign(String campaignId) throws ServiceLocatorException;
public String getLostClosedOpportunities(int pastMonths, HttpServletRequest httpServletRequest) throws ServiceLocatorException;


public String getEmployeeTypeDetails(String country,HttpServletRequest httpServletRequest) throws ServiceLocatorException;

public String getResourceTypeDetails(String projectId,HttpServletRequest httpServletRequest) throws ServiceLocatorException;	
public String doGetTitleType(String loginId) throws ServiceLocatorException;

   public String projectDetailsDashboard( AjaxHandlerAction ajaxHandlerAction,HttpServletRequest httpServletRequest) throws ServiceLocatorException;
    public String getProjectEmployeeDetails( AjaxHandlerAction ajaxHandlerAction,HttpServletRequest httpServletRequest) throws ServiceLocatorException;
    
public String getMonthlyStatusReport(AjaxHandlerAction ajaxHandlerAction,HttpServletRequest httpServletRequest) throws ServiceLocatorException;	

public String getProjectsForManagerByAccountId(int accountId, String empId) throws ServiceLocatorException;



public String getCerCommnets(int id) throws ServiceLocatorException ;
public String getStageRelatedComments(int requestId,String stage) throws ServiceLocatorException ;
public String getOpportunityNamesList(String accId) throws ServiceLocatorException;

public String getTerminationDetailsForInActivePerson(String empId,String loginId) throws ServiceLocatorException;


 /*Events functionality new enhancements start
     * Date : 01/23/2017
     * Author : Santosh Kola/Phani Kanuri
     */
public String doAddAttendeesforExecitiveMeet(AjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException;
public JSONObject doEditExecutiveMeetingAttendees(int Id) throws ServiceLocatorException;
public String doUpdateAttendeesDetailsforExecitiveMeeting(AjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException;
/*Events functionality new enhancements end
     * Date : 01/23/2017
     * Author : Santosh Kola/Phani Kanuri
     */

}
