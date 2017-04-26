/*
 * AjaxHandlerAction.java
 *
 * Created on June 11, 2008, 12:22 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package com.mss.mirage.ajax;

import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.AuthorizationManager;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.ServiceLocator;
import com.mss.mirage.util.ServiceLocatorException;
import com.opensymphony.xwork2.ActionSupport;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import com.mss.mirage.crm.accounts.AccountService;
import com.mss.mirage.crm.attachments.AttachmentService;
import com.mss.mirage.util.MailManager;
import java.io.File;
import java.util.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author miracle
 */
public class AjaxHandlerAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

    /**
     * Creating a reference variable for HttpServletRequest.
     */
    private HttpServletRequest httpServletRequest;
    /**
     * Creating a reference variable for HttpServletResponse.
     */
    private HttpServletResponse httpServletResponse;
    /**
     * Creating a String image to get the Image of user
     */
    private String image;
    /**
     * Creating String to store Country in the database
     */
    private String country;
    /**
     * The resultType means the return type of the method.
     * whether the return type is Success or failure or other.
     */
    private String resultType;
    /**
     * Creating Byte Array get the response from the Ajax call
     */
    private byte[] responseBytes;
    /**
     * Creating String responseString to send Ajax response as String
     */
    private String responseString;
    /**
     * Creating String departmentName to store the value in Database
     */
    private String departmentName;
    /**
     * Creating String practiceName to store the value in Database
     */
    private String practiceName;
    /**
     * Creating String subPracticeName to store the value in Database
     */
    private String subPracticeName;
    /**
     * Creating String deptName to store the value in Database
     */
    private String deptName;
    /**
     * Creating String startDate to store the value in Database
     */
    private String startDate;
    /**
     * Creating String endDate to store the value in Database
     */
    private String endDate;
    /**
     * Creating String department to store the value in Database
     */
    private String department;
    /**
     * Creating String organization to store the value in Database
     */
    private String organization;
    //Hari Accounts
    /**
     * Creating String accountName to store the value in Database
     */
    private String accountName;
    private String accountName2;
    /**
     * Creating String state to store the value in Database
     */
    private String state;
    /**
     * Creating String loginId to store the value in Database
     */
    private String loginId;
    /**
     * Creating String secondState to store the value in Database
     */
    private String secondState;
    /**
     * Creating String accId to store the value in Database
     */
    private String accId;
    /**
     * Creating String oldTeamMember to store the value in Database
     */
    private String oldTeamMember;
    /**
     * Creating String newMember to store the value in Database
     */
    private String newMember;
    /**
     * Creating String optType to store the value in Database
     */
    private String optType;
    //Sagar Employee
    /**
     * Creating String fname to store the value in Database
     */
    private String fname;
    /**
     * Creating String lname to store the value in Database
     */
    private String lname;
    /**
     * Creating String catagoryName to store the value in Database
     */
    private String catagoryName;
    /**
     * Creating a String queryString used to store SQL Query
     */
    private String queryString;
    /**
     * Creating a String region used to store in Database
     */
    private String region;
    /**
     * Creating a String territory used to store in Database
     */
    private String territory;
    /**
     * Creating a String project used to store in Database
     */
    private String project;
//    private String accountName;
    /*** start - Marketing.jsp ***/
    private StringBuffer stringBuffer;
    /**
     * Creating a int accountId used to store in Database
     */
    private int accountId;
    /**
     * Creating a int contactId used to store in Database
     */
    private int contactId;
    /* start -- accounts*/
    //private String accId;
    //private String accountName;
    /**
     * Creating a String urlPath used to get the url path
     */
    private String urlPath;
    /**
     * Creating a String homePhone used to store in Database
     */
    private String homePhone;
    /**
     * Creating a String stockSymbol1 used to store in Database
     */
    private String stockSymbol1;
    /**
     * Creating a String lastModiyBy used to store in Database
     */
    private String lastModiyBy;
    /**
     * Creating a String modifiledDate used to store in Database
     */
    private Timestamp modifiedDate;
    /**
     * Creating a String gentran used to store in Database
     */
    private String gentran;
    /**
     * Creating a String harbinger used to store in Database
     */
    private String harbinger;
    /**
     * Creating a String mercator used to store in Database
     */
    private String mercator;
    /**
     * Creating a String seeBeyond used to store in Database
     */
    private String seeBeyond;
    /**
     * Creating a String webMethods used to store in Database
     */
    private String webMethods;
    /**
     * Creating a String WDI used to store in Database
     */
    private String WDI;
    /**
     * Creating a String ICS used to store in Database
     */
    private String ICS;
    /**
     * Creating a String messageBroker used to store in Database
     */
    private String messageBroker;
    /**
     * Creating a String tibco used to store in Database
     */
    private String tibco;
    /**
     * Creating a String vitria used to store in Database
     */
    private String vitria;
    /**
     * Creating a String WPS used to store in Database
     */
    private String WPS;
    /**
     * Creating a String biztalkServer used to store in Database
     */
    private String biztalkServer;
    /**
     * Creating a String jdEdwards used to store in Database
     */
    private String jdEdwards;
    /**
     * Creating a String oracleApps used to store in Database
     */
    private String oracleApps;
    /**
     * Creating a String peopleSoft used to store in Database
     */
    private String peopleSoft;
    /**
     * Creating a String SAP used to store in Database
     */
    private String SAP;
    /**
     * Creating a String siebel used to store in Database
     */
    private String siebel;
    /**
     * Creating a String baan used to store in Database
     */
    private String baan;
    /**
     * Creating a String beanPortals used to store in Database
     */
    private String beaPortals;
    /**
     * Creating a String oraclePortals used to store in Database
     */
    private String oraclePortals;
    /**
     * Creating a String ibmPortals used to store in Database
     */
    private String ibmPortals;
    /**
     * Creating a String sharePoint used to store in Database
     */
    private String sharePoint;
    /**
     * Creating a String sapPortals used to store in Database
     */
    private String sapPortals;
    /**
     * Creating a String microsoft used to store in Database
     */
    private String microsoft;
    /* end -- accounts*/
    /* start -- contacts*/
    /**
     * Creating a String contId used to store in Database
     */
    private String contId;
    /**
     * Creating a String firstName used to store in Database
     */
    private String firstName;
    /**
     * Creating a String lastName used to store in Database
     */
    private String lastName;
    /**
     * Creating a String middleName used to store in Database
     */
    private String middleName;
    /**
     * Creating a String email used to store in Database
     */
    private String email;
    /**
     * Creating a String phone used to store in Database
     */
    private String phone;
    /**
     * Creating a String source used to store in Database
     */
    private String source;
    /* end -- contacts*/
    /* start -- activities */
    /**
     * Creating a String activityType used to store in Database
     */
    private String activityType;
    /**
     * Creating a String priority used to store in Database
     */
    private String priority;
    /**
     * Creating a String campaignId used to store in Database
     */
    private String campaignId;
    /**
     * Creating a String assignedToId used to store in Database
     */
    private String assignedToId;
    /**
     * Creating a String status used to store in Database
     */
    private String status;
    /**
     * Creating a String alarm used to store in Database
     */
    private String alarm;
    /**
     * Creating a String description used to store in Database
     */
    private String description;
    /**
     * Creating a String comments used to store in Database
     */
    private String comments;
    //private String accId;
    //private String contId;
    /**
     * Creating a String activId used to store in Database
     */
    private String activId;
    /**
     * The createdDate is used for storing createdDate.
     */
    private Timestamp createdDate;
    /**
     * The modifiedDate is used for storing modifiedDate.
     */
    //private Timestamp modifiedDate;
    private Timestamp dueDates;
    /* start -- activities */
    /**
     * Creating a String customerName used to store in Database
     */
    private String customerName;
    /*** end - Marketing.jsp ***/
    private String consultantName;
    /**
     * Creating a String consultantEmail used to store in Database
     */
    private String consultantEmail;
    //Ajax PopUp
    /**
     * Creating a int activityId used to store in Database
     */
    private int activityId;
    /**
     * Creating a String vendorName used to store in Database
     */
    private String vendorName;
    //private String eventDate;
    /**
     * Creating a String skillset used to store in Database
     */
    private String skillset;
    //private String email;
    /**
     * Creating a int practiceid used to store in Database
     */
    private String practiceid;
    /**
     * Creating a String consultantMail used to store in Database
     */
    private String consultantMail;
    /**
     * Creating an int consultantId used to store in Database
     */
    private int consultantId;
    private int requirementId;
    private String recruiterId;
    private String personId;
    /**
     * Creating a String title used to store in Database
     */
    private String title;
    /**
     * Creating a String workAuthor used to store in Database
     */
    private String workAuthor;
    /**
     * Creating a String createdBy used to store in Database
     */
    private String createdBy;
    /*
    Calendar Variables
     */
    /**
     * Creating a String weekStartDate used to store in Database
     */
    private Date weekStartDate;
    /**
     * Creating a String weekEndDate used to store in Database
     */
    private Date weekEndDate;
    /**
     * Creating a String weekDate used to store in Database
     */
    private java.util.Date weekDate;
    /**
     * Creating a String eventDate used to store in Database
     */
    private Date eventDate;
    /**
     * Creating a String eventYearMonth used to store in Database
     */
    private String eventYearMonth;
    //Ajax PopUp
    //private int activityId;
    /**
     * Creating a int accTeamId used to store in Database
     */
    private int accTeamId;
    // Event Add Variables
    /**
     * Creating a String CalEventDate used to store in Database
     */
    private String CalEventDate;
    /**
     * Creating a String eventType used to store in Database
     */
    private String eventType;
    /**
     * Creating a String accName used to store in Database
     */
    private String accName;
    /**
     * Creating a String EveAccId used to store in Database
     */
    private String EveAccId;
    /**
     * Creating a String contactName used to store in Database
     */
    private String contactName;
    /**
     * Creating a String contactsId used to store in Database
     */
    private String contactsId;
    /**
     * Creating a String accTeam used to store in Database
     */
    private String accTeam;
    /**
     * Creating a String EventDesc used to store in Database
     */
    private String EventDesc;
    //private String createdBy;
    /**
     * Creating an int eventId used to store in Database
     */
    private int eventId;
    /**
     * Creating an int eventAssignedIds used to store in Database
     */
    private int eventAssigenedIds;
    /**
     * Creating a String saveType used to store in Database
     */
    private String saveType;
    /**
     * Creating an int calEveId1 used to store in Database
     */
    private int calEveId1;
    /**
     * Creating a String calEveId used to store in Database
     */
    private String calEveId;
    // Access User Calendar Variables
    /**
     * Creating a String currentCaluserId used to store in Database
     */
    private String currentCaluserId;
    /**
     * Creating a String skils used to store in Database
     */
    private String skils;
    /**
     * Creating an int userRoleId used to store in Database
     */
    private int userRoleId;
    /**
     * Creating a String calAccessUserId used to store in Database
     */
    private String calAccessUserId;
    /**
     * Creating a String username used to store in Database
     */
    private String userName;
    /**
     * Creating a String accessUserId used to store in Database
     */
    private String accessUserId;
    /**
     * Creating a String accessId used to store in Database
     */
    private String accessId;
    /**
     * Creating a String accessType used to store in Database
     */
    private String accessType;
//    private String saveType;
    /**
     * Creating a String calUserId used to store in Database
     */
    private String calUesrId;
    /**
     * Creating a String calEventDate1 used to store in Database
     */
    private String CalEventDate1;
    /**
     * Creating a String teamName used to store in Database
     */
    private String teamName;
    /**
     * Creating a Date sdate used to store in Database
     */
    private Date sdate;
    /**
     * Creating a String projectId used to store in Database
     */
    private String projectId;
    /**
     * Creating a String statusCode used to store in Database
     */
    private String statusCode;
    /**
     * Creating a String reason used to store in Database
     */
    private String reason;
    /**
     * Creating a String year used to store in Database
     */
    private String year;
    /**
     * Creating a String month used to store in Database
     */
    private String month;
    /**
     * Creating a String usableDays used to store in Database
     */
    private String usableDays;
    /**
     * Creating an int usableTeamHours used to store in Database
     */
    private int usableTeamHours = 0;
    /**
     * Creating a String poStatus used to store in Database
     */
    private String poStatus;
    /**
     * Creating a String poType used to store in Database
     */
    private String poType;
    /**
     * Creating a String empFname used to store in Database
     */
    private String empFname;
    /**
     * Creating a String empLname used to store in Database
     */
    private String empLname;
    //private String accountName;
    //private int accountId;
    /**
     * Creating a String poStartDateFrom used to store in Database
     */
    private String poStartDateFrom;
    /**
     * Creating a String poStartDateTo used to store in Database
     */
    private String poStartDateTo;
    /**
     * Creating a String poEndDateFrom used to store in Database
     */
    private String poEndDateFrom;
    /**
     * Creating a String poEndDateTo used to store in Database
     */
    private String poEndDateTo;
    /**
     * Creating a String empStartDateFrom used to store in Database
     */
    private String empStartDateFrom;
    /**
     * Creating a String empStartDateTo used to store in Database
     */
    private String empStartDateTo;
    /**
     * Creating a String empEndDateFrom used to store in Database
     */
    private String empEndDateFrom;
    /**
     * Creating a String empEndDateTo used to store in Database
     */
    private String empEndDateTo;
    /**
     * Creating a String queryType used to store in Database
     */
    private String queryType;
    //Venus Report Varibles
    /**
     * Creating a Date venusStaDate used to store in Database
     */
    private Date venusStaDate;
    /**
     * Creating a Date venusEndDate used to store in Database
     */
    private Date venusEndDate;
    /**
     * Creating a String venusDeptId used to store in Database
     */
    private String venusDeptId;
    /**
     * Creating a String venusEmpName used to store in Database
     */
    private String venusEmpName;
    /**
     * Creating a String type used to store in Database
     */
    private String type;
    /**
     * Creating a String stage used to store in Database
     */
    private String stage;
    /**
     * Creating a String dueStartDate used to store in Database
     */
    private String dueStartDate;
    /**
     * Creating a String dueEndDate used to store in Database
     */
    private String dueEndDate;
    /**
     * Creating a Map myTeamMembers used to get the Team Members of a user
     */
    private Map myTeamMembers = new TreeMap();
    /**
     * Creating a Date activityStaDate used to store in Database
     */
    private Date activityStaDate;
    /**
     * Creating a Date activityEndDate used to store in Database
     */
    private Date activityEndDate;
    /**
     * Creating an int userManager used to know whether user is Manager or not
     */
    private int userManager = 0;
    /**
     * Creating an int userTeamLead used to know whether user is Teamlead or not
     */
    private int userTeamLead = 0;
    /**
     * Creating a String location used to store in Database
     */
    private String location;
    private String departmentId;
    private String timeSheetWeekStartDate;
    private String timeSheetWeekEndDate;
    private String notApprovedDepartmentId;
    private String notApprovedStartDate;
    private String notApprovedEndDate;
    /** for contacts searching **/
    private String contactSearchName;
    private int conAccId;
    private String employeeId;
    private String dashBoardStartDateRep;
    private String dashBoardEndDateRep;
    private AccountService accountService;
    private String startdate;
    private String enddate;
    //New 
    private String activitySearchName;
    /** For Account Activities **/
    private int actAccId;
    private int curcontactId;
    /**
     *New variable which are used in eave details popup action
     */
    private int leaveId;
    private String empName;
    /**
     *New variable which are used in requirement list
     */
    // createdBy,assignedTo,title,postedDate1,postedDate2
    private String postedDate1;
    private String postedDate2;
    private String assignedTo;
    private int issueId;

    /* New States 1 - 5 for getAccounts in bdm*/
    public String state1, state2, state3, state4, state5;
    private String projectName;
    /** For ecertification */
    private int domainId;
    private int subtopidId;
    private int questionNo;
    private int selectedAns;
    private String navigation;
    private int remainingQuestions;
    private int onClickStatus;
    private int topicId;
    private String subTopic;
    private int subTopicId;
    private int totalQuestions;
    private String examValidationKey;
    private int questionId;
    /*
     *Cre Properties start
     */
    private String creConsultantId;
    private String creConsultantLevel;
    private String creConsultantStatus;
    private String creStartDate;
    private String creToDate;
    private String creConsultantName;
    private int techReviewId;
    private int hrReviewId;
    private int vpReviewId;
    private int category;
    private String subTopicsList;
    private int totalQues;
    private int totDuration;
    private int minMarks;
    private String examKeyId;
    private String empId;
    private String topicName;
    /*
     *Cre Properties End
     */
    /*
     * State Account deletion properties start
     */
    private String teamMemberId;
    private String delStateAcc;
    /*
     * State Account deletion properties end
     */
    /*
     * Feed back mail properties start
     */
    //empName
    //loginId
    // private String empName;
    //private String loginId;
    private String reportsTo;
    private int totalAccounts;
    private int noOfActivities;
    private int workedAccounts;
    /*
     * Feed back mail properties end
     */
    private String frmLoginId;
    private String toLoginId;
    private String lastActivityDate;
    private String level;
    private String interviewAt;
//New Cre
    private String creConsultantId1;
    private String examType;
    private int ExamNameId;
    private String ExamNameIdList;
    private int specficQuestionNo;
    private String empType;
//new
    private String techName;
    private int id;
    private String referTo;
//new
    private String issueRel;

    /*New Filed For Populating Onboard Comments Date : 08/15/2014
     * DAte : 08/15/2014
     */
    private int onboardReviewId;
    private String resourceType;
// New 
    private String dashBoardStartDate;
    private String dashBoardEndDate;
//new for performance reviews
    private String metricName;
    private String statusId;
    private String metricId;
    private String perfId;
    private String stringdata;
    private String titleId;
    private String minValue;
    private String maxValue;
    private String weightage;
//Dual ReportsTo Changes
    private int teamType;
    private int empCusType;
// review fields

    /*Add review changes
     * 
     * 
     */
    private File file;
    private String fileFileName;
    private String fileFileContentType;
    private String generatedPath;
    private AttachmentService attachmentService;
    private String fileLocation;
    private String filepath;
    private String objectType;
    private String attachmentLocation;
    private String overlayReviewType;
    private String overlayReviewName;
    private String overlayReviewDate;
    private String overlayDescription;
    private String empTitle;
    private String reviewStatusOverlay;
    private String overlayReviewCreatedDate;
    private String modifiedBy;
    private String reviewId;
    private String tlComments;
    private int reviewTypeId;
    private int leadRating;
    private int hrRating;
    private int maxPoints;
    private String hrComments;
    private String userId;
    private String addType;
    private String roleName;
    private String startDateSummaryGraph;
    private String endDateSummaryGraph;
//new for accountmerge
    private String original;
    private String duplicate;
    private String jobtitle;
    private String jobtagline;
    private String jobposition;
    private String jobqulification;
    private String jobcountry;
    private String jobshifits;
    private String jobstatus;
    private String jobdescription;
    private String jobtechnical;
    private String jobshiftskills;
    private String jobId;
    private int applicantId;
    private String currId;
    private String projectStartDate;
    private boolean isManagerInclude;
    private String empnameById;
    private String empNo;
    private String reviewOnsiteMgrStatusOverlay;
    private String reviewHrStatusOverlay;
    private String onsiteMgrComments;
    private int onsiteMgrRating;
    private int manager;
    private String managerCountry;
    private String ApproverComments;
    private String reviewStatus;
    private int rating;
    private double overlayReviewBilling;
    private int overlayReviewLogAdded;
    private String jobDepartment;
    private String jobHireType;
    // Event properties
    private String eventTitle;
//  private String eventType;
    private String eventStatus;
    private String timeZone;
    private String eventLocation;
    private String transportation;
    private String eventDescription;
    private String startTime;
    private String endTime;
    private String midDayFrom;
    private String midDayTo;
    private String eventBoldtitle;
    private String eventRegluarTitle;
    private String eventRegistrationLink;
    private String contactUsEmail;
    private String speakerName;
    private String designation;
    private String company;
    private String speakerType;
    private String speakerId;
    private String conferenceUrl;
    private String primaryTrack;
    private String secondaryTrack;
    private String eventDepartment;
    private String eventAfterVideoUrl;
    private String infoId;
    private String tableName;
    private String eventAfterDescription;
    private String isAssociated;
    private String associatedEventId;
    private String seriesTitle;
    private String seriesType;
    private String seriesTrack;
    private String seriesStatus;
    private String seriesId;
    private String peopleName;
    private String trackName;
    private String resourceTitle;
    private int trackId;
    private int pastMonths;
    private boolean external;
    private String assignedBy;
    /* Survey form fields */
    private String questionTitle;
    private String optionType;
    private String querySequence;
    private int optionCount;
    private String optionLabel1;
    private String optionLabel2;
    private String optionLabel3;
    private String optionLabel4;
    private String optionLabel5;
    private String optionLabel6;
    private int optionSequence1;
    private int optionSequence2;
    private int optionSequence3;
    private int optionSequence4;
    private int optionSequence5;
    private int optionSequence6;
    private String surveyId;
    private int surveyInfoId;
    private boolean isRequired;
    private String questionSequanceData;
    private String techMgrId;
    private String clientId;
    private String teamLeadLoginId;
    private String recruiterLoginId;
    private String modifiedStartDate;
    private String modifiedEndDate;
    private String preSalesPerson;
    private int fromMonth;
    private int fromYear;
    private int toMonth;
    private int toYear;
    /*********************************************************
    utilization Report in operations role
    
     *********************************************************/
    private String opsContactId;
    private String practiceId;
    private String dropdownOptions;
    private String placeHolder;
    private String questionStatus;
    private String expiryDate;
    private String opsContactIdForAcc;
    private String salesLeadId;
    private String salesStartDate;
    private String salesEndDate;
    private String pmoLoginId;
    private String recManagerId;
    private String hours;
    private String severity;
    private int taskId;
    private int issueTypeId;
    private String resolution;

    /* Bridge Management System  */
    private String bridgeDate;
    private String bridgeCode;
    private String bridgeNumber;
    private String bridgeName;
    private String bridgeStatus;
    private int duration;
    private String durationType;
    private String assignTo;
    private String internalAttendees;
    private String externalAttendees;
    private int passCode;
    private int emailFlag;
    /* Bridge Management System End  */
    
    
 private String contactEmailID;
       private String contactPhoneNo;
        private String contactCompany;
        
         private String investmentName;
    private String startDateInvestment;
    private String endDateInvestment;
    private String countryInvestment;
    private double totalExpenseAmount;
    private String locationInvestment;
    private String investmentDesc;
    private String attachInvestment;
    
    private File Invsfile;
    private String usd;
    
    private String campaignTitle;
    private String campaignStatus;
    private String campaignStartDate;
    private String campaignEndDate;
    private String campaignName;
	
      private String renewalState;
           private List resStatesList = new ArrayList();
    /*********************************************************
        Start Requirement dashboard  Report 
     *********************************************************/
     private String relatedTeam;
     private String requirementstatus;
     private String practice;
     private String reqStartDate;
     private String reqEndDate;
     private String reqJobTitle;
     
      private int fileFlag;
     private int timeSheetID;
     
     // Emeet Fields
      private String executiveMeetingType;
private String executiveMeetMonth;
private String registrationTextforExeMeet;
private String exeMeetcreatedDateTo;
private String executiveMeetingStatus;
private String exeMeetStartTime;
private String exeMeetEndTime;
private String exeMeetStartmidDayFrom;
private String exeMeetEndmidDayTo;
private String registrationLinkForEMeet;
private String eMeetAccessType;
private String emeetAttendeesEmail;
private String executiveMeetingAccessStatus;
private String executiveMeetAccessType;
private String executiveMeetingAttendeeEmail;
private String videoLinkForEMeet;
private String previousStatus;

private String contactStartDate;
private String contactEndDate;
  private String creCollegeName;   
  private String course;   
  private String creStream;
  private String dueDate;
   private String docType;
 private int opsContactIdForPF;
 private int preAssignEmpId;
 private String flag;
 private String investmentType;
  private Date dateOfTermination;
    private String reasonsForTerminate;
       private String opportunityState;
    private int opportunityId;
     private int missedField;
     private String contactTitle;
     
      private String sviNum;
private String sviList;
private int sviValue;


   private String leadTitle;
private String createdDateFrom;
private String createdDateTo;
private String inactiveDateFrom;
private String inactiveDateTo;
private int investmentId;
//CCFields
private String constantContactId;
private String requirement;
private String loactionName;
 private int teamMemberCheck;
 private String titleType; 
  private String empStatus;
   private int includeTeamFlag;
   
   private int requestId;
   
    private boolean allowDocuments;
   private boolean allowPictures;
   
   private String subPracticeId;
   private String sortedBy;
   
   private String bdeLoginId;
   
     private int investmentConferenceId;
   private String sessionTitleType;
   
    public String getDashBoardEndDate() {
        return dashBoardEndDate;
    }

    public void setDashBoardEndDate(String dashBoardEndDate) {
        this.dashBoardEndDate = dashBoardEndDate;
    }

    public String getDashBoardStartDate() {
        return dashBoardStartDate;
    }

    public void setDashBoardStartDate(String dashBoardStartDate) {
        this.dashBoardStartDate = dashBoardStartDate;
    }

    /** Creates a new instance of AjaxHandlerAction */
    public AjaxHandlerAction() {
    }

    public String getEmpLeavesOpp() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                responseString = ServiceLocator.getAjaxHandlerService().getEmpTotalLeaves(month, year, httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString());
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public String getEmpLeavesOppByDate() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {


                responseString = ServiceLocator.getAjaxHandlerService().getEmpTotalLeavesByDate(fname, lname, country, startdate, enddate, opsContactId);

                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public String getTotalLeaves() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                responseString = ServiceLocator.getAjaxHandlerService().getTotalLeaves(employeeId, year);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    /**
     * This method is used to get the Opportunities in Dashboard
     * @return null
     */
  /*  public String getOppDashBoard() {
        /*
         *This if loop is to check whether there is Session or not
         **
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            int isManager = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_USER_MANAGER).toString());
            int isTeamLead = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_TEAM_LEAD).toString());
            String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            // String curWorkCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString().trim();
            // curWorkCountry = curWorkCountry + "%";
            // System.out.println("COUNT IS  "+curWorkCountry);
            /*
             *This if loop is to check the Authentication
             **
            //  if(AuthorizationManager.getInstance().isAuthorizedUser("DASHBOARD_ACCESS",userRoleId) && ((isManager ==1) || (isTeamLead ==1)) ){
            if (AuthorizationManager.getInstance().isAuthorizedUser("DASHBOARD_ACCESS", userRoleId)) {
                try {
                    if ("All".equals(getCreatedBy())) {
                        //   setMyTeamMembers((Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
//                         if (loginId.equals("plokam")) {
//                        setMyTeamMembers(accountService.getAllSalesTeamExceptAccountTeam(new TreeMap()));
//                    } else {
//                        setMyTeamMembers((Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
//                    }
                        setMyTeamMembers((Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
                        myTeamMembers.put(loginId, loginId);
                    } else {
                        String department = DataSourceDataProvider.getInstance().getDepartmentName(getCreatedBy());
                        setMyTeamMembers(DataSourceDataProvider.getInstance().getMyTeamMembers(getCreatedBy(), department));
                        myTeamMembers.put(getCreatedBy(), getCreatedBy());

                    }

                    //  String myTeamMembers = getKeys(getMyTeamMembers(), ",");
                    String myTeamMembers = "";
                    myTeamMembers = DataSourceDataProvider.getInstance().getTeamLoginIdList(getMyTeamMembers());
                    myTeamMembers = myTeamMembers.replaceAll("'", "");
//                          if(myTeamMembers !=null && !"".equals(myTeamMembers)){
//                              myTeamMembers+=","+loginId;
//                          }
//                          else{
//                              myTeamMembers=loginId;
//                          }
                    setCreatedBy(myTeamMembers);

                   // responseString = ServiceLocator.getAjaxHandlerService().getOppDashBoard(getType(), getStage(), getDueStartDate(), getDueEndDate(), getCreatedBy(), httpServletRequest);
                   // responseString = ServiceLocator.getAjaxHandlerService().getOppDashBoard(getType(), getStage(), getDueStartDate(), getDueEndDate(), getCreatedBy(),getState(),getDueDate(), httpServletRequest);
                //    responseString = ServiceLocator.getAjaxHandlerService().getOppDashBoard(getType(), getStage(), getDueStartDate(), getDueEndDate(), getCreatedBy(),getState(),getDueDate(),getPracticeName(), httpServletRequest);
                    responseString = ServiceLocator.getAjaxHandlerService().getOppDashBoard(getType(), getStage(), getDueStartDate(), getDueEndDate(), getCreatedBy(),getState(),getDueDate(),getPracticeName(),getSviNum(),getSviList(),httpServletRequest);
                    httpServletResponse.setContentType("text");
                    httpServletResponse.getWriter().write(responseString);
                } catch (Exception ex) {
                }
            }
        }//Close Session Checking
        return null;
    }*/
 public String getOppDashBoard() {
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            int isManager = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_USER_MANAGER).toString());
            int isTeamLead = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_TEAM_LEAD).toString());
            String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
           String titleType = (String) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_TITLE);
            String empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();   
                    //  System.out.println("titleType is---->"+titleType);
           
            // String curWorkCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString().trim();
            // curWorkCountry = curWorkCountry + "%";
            // System.out.println("COUNT IS  "+curWorkCountry);
            /*
             *This if loop is to check the Authentication
             **/
            //  if(AuthorizationManager.getInstance().isAuthorizedUser("DASHBOARD_ACCESS",userRoleId) && ((isManager ==1) || (isTeamLead ==1)) ){
            if (AuthorizationManager.getInstance().isAuthorizedUser("DASHBOARD_ACCESS", userRoleId)) {
                try {
                    if ("All".equals(getCreatedBy())) {
                        //   setMyTeamMembers((Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
//                         if (loginId.equals("plokam")) {
//                        setMyTeamMembers(accountService.getAllSalesTeamExceptAccountTeam(new TreeMap()));
//                    } else {
//                        setMyTeamMembers((Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
//                    }
                           if(titleType.equals("BDM")){
                               setMyTeamMembers(DataSourceDataProvider.getInstance().getBdmAssociateList(empId));
                           }
                           else{
                            setMyTeamMembers((Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
                           }
                     
                        myTeamMembers.put(loginId, loginId);
                    } else {
                        if(!titleType.equals("BDM")){
                            String department = DataSourceDataProvider.getInstance().getDepartmentName(getCreatedBy());
                        setMyTeamMembers(DataSourceDataProvider.getInstance().getMyTeamMembers(getCreatedBy(), department));
                        }
                        
                        myTeamMembers.put(getCreatedBy(), getCreatedBy());
                       // System.out.println("myTeamMembers in individual case is---->"+myTeamMembers);
                    }

                    //  String myTeamMembers = getKeys(getMyTeamMembers(), ",");
                    String myTeamMembers = "";
                    myTeamMembers = DataSourceDataProvider.getInstance().getTeamLoginIdList(getMyTeamMembers());
                    myTeamMembers = myTeamMembers.replaceAll("'", "");
//                          if(myTeamMembers !=null && !"".equals(myTeamMembers)){
//                              myTeamMembers+=","+loginId;
//                          }
//                          else{
//                              myTeamMembers=loginId;
//                          }
                    setCreatedBy(myTeamMembers);

                   // responseString = ServiceLocator.getAjaxHandlerService().getOppDashBoard(getType(), getStage(), getDueStartDate(), getDueEndDate(), getCreatedBy(), httpServletRequest);
                   // responseString = ServiceLocator.getAjaxHandlerService().getOppDashBoard(getType(), getStage(), getDueStartDate(), getDueEndDate(), getCreatedBy(),getState(),getDueDate(), httpServletRequest);
                //    responseString = ServiceLocator.getAjaxHandlerService().getOppDashBoard(getType(), getStage(), getDueStartDate(), getDueEndDate(), getCreatedBy(),getState(),getDueDate(),getPracticeName(), httpServletRequest);
                    responseString = ServiceLocator.getAjaxHandlerService().getOppDashBoard(getType(), getStage(), getDueStartDate(), getDueEndDate(), getCreatedBy(),getState(),getDueDate(),getPracticeName(),getSviNum(),getSviList(),httpServletRequest);
                    httpServletResponse.setContentType("text");
                    httpServletResponse.getWriter().write(responseString);
                } catch (Exception ex) {
                }
            }
        }//Close Session Checking
        return null;
    }

    /**
     * This method is used to get the Greensheet List
     * @return null
     * @throws java.io.IOException
     */
   
 /*public String greensheetListSearch() throws IOException {
        //System.out.println("greensheetListSearch");
        /*
         *This if loop is to check whether there is Session or not
         **
        if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null)) {
            userManager = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_USER_MANAGER).toString());
            userTeamLead = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_TEAM_LEAD).toString());
            String curWorkCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString().trim();
            String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString().trim();
            try {
                /*
                 *This if loop is to check whether the user is 'Teamlead' or 'Team Manager'
                 **
           ////     System.out.println("createdBy"+createdBy);
                
                String title = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_TITLE).toString();
               if("BDM".equalsIgnoreCase(title.trim())){
                
               setTitleType("BDM");
               } if("Vice President".equalsIgnoreCase(title.trim())){
                     setTitleType("Vice President");
               }
               if("Sr. Vice President".equalsIgnoreCase(title.trim())){
                     setTitleType("Sr. Vice President");
               }
                //System.out.println("title"+title);
               
               if(getTitleType()==null||"".equals(getTitleType())){
                   setTitleType("");
               }
               if ((userManager == 1) || (userTeamLead == 1) || ("BDM".equalsIgnoreCase(getTitleType().trim())) || ("Vice President".equalsIgnoreCase(getTitleType().trim())) || ("Sr. Vice President".equalsIgnoreCase(getTitleType().trim()))){
                    if ("-1".equals(getCreatedBy())) {
                        //   setMyTeamMembers((Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
//                     if (loginId.equals("plokam")) {
//                        setMyTeamMembers(accountService.getAllSalesTeamExceptAccountTeam(new TreeMap()));
//                    } else {
//                        setMyTeamMembers((Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
//                    }
                        setMyTeamMembers((Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
                        myTeamMembers.put(loginId, loginId);
                        String myTeamMembers = DataSourceDataProvider.getInstance().getTeamLoginIdList(getMyTeamMembers());
                        myTeamMembers = myTeamMembers.replaceAll("'", "");
//                            if(myTeamMembers !=null && !"".equals(myTeamMembers)){
//                              myTeamMembers+=","+loginId;
//                          }
//                          else{
//                              myTeamMembers=loginId;
//                          }
                        setCreatedBy(myTeamMembers);
                    }
                  // System.out.println("createdBy"+createdBy);
                    
                    responseString = ServiceLocator.getAjaxHandlerService().greensheetListSearch(getPoType(), getPoStatus(), getCreatedBy(), getStartDate(), getEndDate(), getCountry(), title,getTitleType());
                    httpServletResponse.setContentType("text/html");
                    httpServletResponse.getWriter().write(responseString);
                } else {
                    httpServletResponse.getWriter().write("");
                }

            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            }
        }//Close Session Checking
        return null;
    }
*/
     public String greensheetListSearch() throws IOException {
        //System.out.println("greensheetListSearch");
        /*
         *This if loop is to check whether there is Session or not
         **/
        if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null)) {
            userManager = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_USER_MANAGER).toString());
            userTeamLead = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_TEAM_LEAD).toString());
            String curWorkCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString().trim();
            String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString().trim();
            String empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();   
                           // System.out.println("empId is------->"+empId);
                         
            try {
                /*
                 *This if loop is to check whether the user is 'Teamlead' or 'Team Manager'
                 **/
           ////     System.out.println("createdBy"+createdBy);
                
                String title = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_TITLE).toString();
               if("BDM".equalsIgnoreCase(title.trim())){
                
               setTitleType("BDM");
               } if("Vice President".equalsIgnoreCase(title.trim())){
                     setTitleType("Vice President");
               }
               if("Sr. Vice President".equalsIgnoreCase(title.trim())){
                     setTitleType("Sr. Vice President");
               }
                //System.out.println("title"+title);
               
               if(getTitleType()==null||"".equals(getTitleType())){
                   setTitleType("");
               }
               if ((userManager == 1) || (userTeamLead == 1) || ("BDM".equalsIgnoreCase(getTitleType().trim())) || ("Vice President".equalsIgnoreCase(getTitleType().trim())) || ("Sr. Vice President".equalsIgnoreCase(getTitleType().trim()))){
                    if ("-1".equals(getCreatedBy())) {
                        //   setMyTeamMembers((Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
//                     if (loginId.equals("plokam")) {
//                        setMyTeamMembers(accountService.getAllSalesTeamExceptAccountTeam(new TreeMap()));
//                    } else {
//                        setMyTeamMembers((Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
//                    }
                        if("BDM".equalsIgnoreCase(getTitleType().trim())){
                            setMyTeamMembers(DataSourceDataProvider.getInstance().getBdmAssociateList(empId));
                        }
                        else{
                             setMyTeamMembers((Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
                        }
                       
                        myTeamMembers.put(loginId, loginId);
                        String myTeamMembers = DataSourceDataProvider.getInstance().getTeamLoginIdList(getMyTeamMembers());
                        myTeamMembers = myTeamMembers.replaceAll("'", "");
//                            if(myTeamMembers !=null && !"".equals(myTeamMembers)){
//                              myTeamMembers+=","+loginId;
//                          }
//                          else{
//                              myTeamMembers=loginId;
//                          }
                        setCreatedBy(myTeamMembers);
                    }
                    
                    
                    
                    
                    
                  
                    
                    responseString = ServiceLocator.getAjaxHandlerService().greensheetListSearch(getPoType(), getPoStatus(), getCreatedBy(), getStartDate(), getEndDate(), getCountry(), title,getTitleType(),httpServletRequest);
                    httpServletResponse.setContentType("text/html");
                    httpServletResponse.getWriter().write(responseString);
                } else {
                    httpServletResponse.getWriter().write("");
                }

            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            }
        }//Close Session Checking
        return null;
    }

    /**
     * This method is used to get the Phone & WebAddress Details
     * @return null
     */
    public String greenSheet() {
        try {
            responseString = ServiceLocator.getAjaxHandlerService().greenSheet("SELECT Phone,WebAddress FROM tblCrmAccount where Name ='" + getVendorName() + "' and Type='Vendor' ");
            httpServletResponse.setContentType("text/xml");
            httpServletResponse.getWriter().write(responseString);

        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    /**
     *
     * This method is used to get the Consultant Details
     * @return null
     */
    public String gsConsultant() {
        try {
            responseString = ServiceLocator.getAjaxHandlerService().gsConsultant("SELECT FirstName,LastName,MiddleName,CellPhone  FROM tblCrmContact where FirstName LIKE  '" + getConsultantName() + "%' ");
            httpServletResponse.setContentType("text/xml");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    /**
     * This method is used to get the Green Sheet Details
     * @return null
     */
    public String getGreensheetDetails() {
        try {
            responseString = ServiceLocator.getAjaxHandlerService().getCustomerDetails("SELECT CustomerName, ConsultantId  FROM tblGreensheets where CustomerName LIKE '" + getCustomerName() + "%' and RecordType=1 LIMIT 30");
            httpServletResponse.setContentType("text/xml");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    /**
     * This method is used to get the Name & Id off the Account
     * @return null
     */
    public String getMarketAccount() {
        try {

            responseString = ServiceLocator.getAjaxHandlerService().getScreenAccount(accountName);
            httpServletResponse.setContentType("text/xml");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        }
        return null;
    }

    /**
     *
     * This method is used to get the Contact Details
     * @return null
     */
    public String getMarketContact() {
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {

                responseString = ServiceLocator.getAjaxHandlerService().getScreenContactDetails(accountId);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }//Close Session Checking
        return null;
    }

    /**
     *
     * This method is used to get the Account Details
     * @return null
     */
    public String getAccountDetails() {
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                responseString = ServiceLocator.getAjaxHandlerService().getAccountDetails(accountName);
                httpServletResponse.setContentType("text/html");
                httpServletResponse.getWriter().write(responseString);

            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }//Close Session Checking
        return null;
    }

    /**
     *
     * This method is used to get the Contact Details
     * @return null
     */
    public String getContactDetails() {
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                responseString = ServiceLocator.getAjaxHandlerService().getContactDetails(contactId);
                httpServletResponse.setContentType("text/html");
                httpServletResponse.getWriter().write(responseString);

            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }//Close Session Checking
        return null;
    }

    /**
     *
     * This method is used to save the Account Details
     * @return null
     */
    public String saveAccount() {
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {

                String lastModiyBy = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                setModifiedDate(DateUtility.getInstance().getCurrentMySqlDateTime());
                accId = httpServletRequest.getParameter("accId");
                /*
                 *This if loop is to checking the value of accId ---> if the value is 'space' or 'null' then replace with '0'
                 **/
                if (accId == null || accId == "") {
                    accId = "0";
                }
                accountId = Integer.parseInt(accId);

                accountName = httpServletRequest.getParameter("accountName");
                /*
                 *This if loop is to checking the value of accountName ---> if the value is 'space' or 'null' or 'undefined' then replace with ''
                 **/
                if (accountName == null || accountName == "" || accountName == "undefined") {
                    accountName = "";
                }

                setUrlPath(httpServletRequest.getParameter("urlPath"));
                /*
                 *This if loop is to checking the value of urlPath ---> if the value is 'space' or 'null' or 'undefined' then replace with ''
                 **/
                if (getUrlPath() == null || getUrlPath() == "" || getUrlPath() == "undefined") {
                    setUrlPath("");
                }

                setHomePhone(httpServletRequest.getParameter("homePhone"));
                /*
                 *This if loop is to checking the value of homePhone ---> if the value is 'space' or 'null' or 'undefined' then replace with ''
                 **/
                if (getHomePhone() == null || getHomePhone() == "" || getHomePhone() == "undefined") {
                    setHomePhone("");
                }

                setStockSymbol1(httpServletRequest.getParameter("stockSymbol1"));
                /*
                 *This if loop is to checking the value of stockSymbol ---> if the value is 'space' or 'null' or 'undefined' then replace with ''
                 **/
                if (getStockSymbol1() == null || getStockSymbol1() == "" || getStockSymbol1() == "undefined") {
                    setStockSymbol1("");
                }

                lastModiyBy = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();

                setModifiedDate(DateUtility.getInstance().getCurrentMySqlDateTime());

                setGentran(httpServletRequest.getParameter("gentran"));
                setHarbinger(httpServletRequest.getParameter("harbinger"));
                setMercator(httpServletRequest.getParameter("mercator"));
                setSeeBeyond(httpServletRequest.getParameter("seeBeyond"));
                setWebMethods(httpServletRequest.getParameter("webMethods"));
                setWDI(httpServletRequest.getParameter("WDI"));
                setICS(httpServletRequest.getParameter("ICS"));
                setMessageBroker(httpServletRequest.getParameter("messageBroker"));
                setTibco(httpServletRequest.getParameter("tibco"));
                setVitria(httpServletRequest.getParameter("vitria"));
                setWPS(httpServletRequest.getParameter("WPS"));

                setBiztalkServer(httpServletRequest.getParameter("biztalkServer"));
                setJdEdwards(httpServletRequest.getParameter("jdEdwards"));
                setOracleApps(httpServletRequest.getParameter("oracleApps"));
                setPeopleSoft(httpServletRequest.getParameter("peopleSoft"));
                setSAP(httpServletRequest.getParameter("SAP"));
                setSiebel(httpServletRequest.getParameter("siebel"));
                setBaan(httpServletRequest.getParameter("baan"));
                setBeaPortals(httpServletRequest.getParameter("beaPortals"));
                setOraclePortals(httpServletRequest.getParameter("oraclePortals"));
                setIbmPortals(httpServletRequest.getParameter("ibmPortals"));
                setSharePoint(httpServletRequest.getParameter("sharePoint"));
                setSapPortals(httpServletRequest.getParameter("sapPortals"));
                setMicrosoft(httpServletRequest.getParameter("microsoft"));


                String temp;
                temp = ServiceLocator.getAjaxHandlerService().saveAccount(accountId, accountName, getUrlPath(), getHomePhone(), getStockSymbol1(), lastModiyBy, getModifiedDate(), getGentran(), getHarbinger(), getMercator(), getSeeBeyond(), getWebMethods(), getWDI(), getICS(), getMessageBroker(), getTibco(), getVitria(), getWPS(), getBiztalkServer(), getJdEdwards(), getOracleApps(), getPeopleSoft(), getSAP(), getSiebel(), getBaan(), getBeaPortals(), getOraclePortals(), getIbmPortals(), getSharePoint(), getSapPortals(), getMicrosoft());

                stringBuffer = new StringBuffer(temp);
                httpServletResponse.setContentType("text/html");
                httpServletResponse.getWriter().write(stringBuffer.toString());

            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }//Close Session Checking
        return null;
    }

    /**
     *
     * This method is used to save the Contact Details
     * @return null
     */
    public String saveContact() {
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                accId = httpServletRequest.getParameter("accId");
                int accountId = Integer.parseInt(accId);

                setContId(httpServletRequest.getParameter("contId"));
                /*
                 *This if loop is to checking the value of contId ---> if the value is 'space' or 'null' or '0' then replace with '0'
                 **/
                if (getContId() == null || getContId() == "" || getContId() == "0") {
                    contactId = 0;
                } else {
                    contactId = Integer.parseInt(getContId());
                }

                setFirstName(httpServletRequest.getParameter("firstName"));
                /*
                 *This if loop is to checking the value of firstName ---> if the value is 'space' or 'null' or 'undefined' then replace with ''
                 **/
                if (getFirstName() == null || getFirstName() == "" || getFirstName() == "undefined") {
                    setFirstName("");
                }

                setLastName(httpServletRequest.getParameter("lastName"));
                /*
                 *This if loop is to checking the value of lastName ---> if the value is 'space' or 'null' or 'undefined' then replace with ''
                 **/
                if (getLastName() == null || getLastName() == "" || getLastName() == "undefined") {
                    setLastName("");
                }

                setMiddleName(httpServletRequest.getParameter("middleName"));
                /*
                 *This if loop is to checking the value of middleName ---> if the value is 'space' or 'null' or 'undefined' then replace with ''
                 **/
                if (getMiddleName() == null || getMiddleName() == "" || getMiddleName() == "undefined") {
                    setMiddleName("");
                }

                setEmail(httpServletRequest.getParameter("email"));
                /*
                 *This if loop is to checking the value of email ---> if the value is 'space' or 'null' or 'undefined' then replace with ''
                 **/
                if (getEmail() == null || getEmail() == "" || getEmail() == "undefined") {
                    setEmail("");
                }

                setPhone(httpServletRequest.getParameter("phone"));
                /*
                 *This if loop is to checking the value of phone ---> if the value is 'space' or 'null' or 'undefined' then replace with ''
                 **/
                if (getPhone() == null || getPhone() == "" || getPhone() == "undefined") {
                    setPhone("");
                }

                setSource(httpServletRequest.getParameter("source"));
                /*
                 *This if loop is to checking the value of source ---> if the value is 'space' or 'null' or 'undefined' then replace with ''
                 **/
                if (getSource() == null || getSource() == "" || getSource() == "undefined") {
                    setSource("");
                }

                String temp;
                temp = ServiceLocator.getAjaxHandlerService().saveContact(accountId, contactId, getFirstName(), getLastName(), getMiddleName(), getEmail(), getPhone(), getSource());
                stringBuffer = new StringBuffer(temp);
                httpServletResponse.setContentType("text/html");
                httpServletResponse.getWriter().write(stringBuffer.toString());

            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }//Close Session Checking
        return null;
    }

    /**
     *
     * This method is used to save the Activity Details
     * @return null
     */
    public String saveActivity() {
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                accId = httpServletRequest.getParameter("accId");
                accountId = Integer.parseInt(accId);

                contId = httpServletRequest.getParameter("contId");
                contactId = Integer.parseInt(contId);

                activityType = httpServletRequest.getParameter("activityType");
                /*
                 *This if loop is to checking the value of activityType ---> if the value is 'space' or 'null' then replace with ''
                 **/
                if (activityType == null || activityType == "") {
                    activityType = "";
                } //else concat with ''
                else {
                    activityType += "";
                }

                priority = httpServletRequest.getParameter("priority");
                /*
                 *This if loop is to checking the value of priority ---> if the value is 'space' or 'null' then replace with ''
                 **/
                if (priority == null || priority == "") {
                    priority = "";
                } //else concat with ''
                else {
                    priority += "";
                }

                campaignId = httpServletRequest.getParameter("campaignId");
                int cId = Integer.parseInt(campaignId);

                assignedToId = httpServletRequest.getParameter("assignedToId");
                /*
                 *This if loop is to checking the value of assignedToId ---> if the value is 'space' or 'null' then replace with ''
                 **/
                if (assignedToId == null || assignedToId == "") {
                    assignedToId = "";
                } //else Concat with ''
                else {
                    assignedToId += "";
                }

                status = httpServletRequest.getParameter("status");
                /*
                 *This if loop is to checking the value of status ---> if the value is 'space' or 'null' then replace with ''
                 **/
                if (status == null || status == "") {
                    status = "";
                } //else Concat with ''
                else {
                    status += "";
                }

                alarm = httpServletRequest.getParameter("alarm");
                alarm.trim();
                boolean alarmValue = false;
                // This if else is to check whether alarm is 'true' or 'false'
                if (alarm.equalsIgnoreCase("true")) {
                    alarmValue = true;
                } else if (alarm.equalsIgnoreCase("false")) {
                    alarmValue = false;
                }

                description = httpServletRequest.getParameter("description");
                /*
                 *This if loop is to checking the value of description ---> if the value is 'space' or 'null' then replace with ''
                 **/
                if (description == null || description == "") {
                    description = "";
                } //else Concat with ''
                else {
                    description += "";
                }

                String comments = httpServletRequest.getParameter("comments");
                /*
                 *This if loop is to checking the value of comments ---> if the value is 'space' or 'null' then replace with ''
                 **/
                if (comments == null || comments == "") {
                    comments = "";
                } //else Concat with ''
                else {
                    comments += "";
                }

                activId = httpServletRequest.getParameter("activId");
                int activityId;
                /*
                 *This if loop is to checking the value of activId ---> if the value is 'space' or 'null' or '0' then replace with '0'
                 **/
                if (activId == null || activId == "" || activId == "0") {
                    activityId = 0;
                } else {
                    activityId = Integer.parseInt(activId);
                }


                // convert string to timestamp
                String dueDate = httpServletRequest.getParameter("dueDate");
                setDueDates(DateUtility.getInstance().strToTimeStampObj(dueDate));
                String createdById = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                String modifiedById = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                String assignedById = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();

                setCreatedDate(DateUtility.getInstance().getCurrentMySqlDateTime());
                setModifiedDate(DateUtility.getInstance().getCurrentMySqlDateTime());

                String temp;
                temp = ServiceLocator.getAjaxHandlerService().saveActivityDetails(activityType, priority, cId, assignedToId,
                        status, getDueDates(), alarmValue, description, comments, accountId, contactId, createdById, modifiedById, assignedById,
                        getCreatedDate(), getModifiedDate(), activityId);
                stringBuffer = new StringBuffer(temp);
                httpServletResponse.setContentType("text/html");
                httpServletResponse.getWriter().write(stringBuffer.toString());

            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }//Close Session Checking
        return null;
    }

    /**
     *
     * This method is used to get the Image of User
     * @return null
     */
    public String getImage() {
        /*
         *This if loop is to check whether there is Session or not
         **/
        if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) || (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)) {
            try {
                responseBytes = ServiceLocator.getAjaxHandlerService().getEmployeeImage(image);
                httpServletResponse.getOutputStream().write(responseBytes);
                httpServletResponse.getOutputStream().close();
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }//Close session Checking
        return null;
    }

    /**
     *
     * This method is used to populate The states by Country
     * @return null
     */
    public String getStateData() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                responseString = ServiceLocator.getAjaxHandlerService().getStates(country);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     *
     * This method is used to get The Department of Employee
     * @return null
     */
    public String getEmpDepartment() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                responseString = ServiceLocator.getAjaxHandlerService().getEmployeeDepartment(departmentName);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     *
     * This method is used to get The Title of the Employee
     * @return null
     */
    public String getEmployeeForTitles() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                responseString = ServiceLocator.getAjaxHandlerService().getEmpForTitles(departmentName);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     *
     * This method is used to get The Practice of the Employee
     * @return null
     */
    public String getEmployeePractice() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                responseString = ServiceLocator.getAjaxHandlerService().getEmpPractice(practiceName);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     *
     * This method is used to get The Sub-Practice of the Employee
     * @return null
     */
    public String getEmployeeSubPractice() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                responseString = ServiceLocator.getAjaxHandlerService().getEmpSubPractice(subPracticeName);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     *
     * This method is used to get The Managers & Teamleads List of a Department
     * @return null
     */
    public String getEmployeeForReportsTo() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                responseString = ServiceLocator.getAjaxHandlerService().getEmpForReportsTo(getDeptName());
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     *
     * This method is used to get The Employee Vacation List
     * @return null
     */
    public String getEmployeeVacationList() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                responseString = ServiceLocator.getAjaxHandlerService().getEmpVacationList(startDate, endDate, department, organization);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }//CLose Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // End Of Praveen
    //Hari Starts
    /**
     *
     * This method is used to get The Account List
     * @return null
     */
    public String getAccount() {
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            String roleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
//            int isManager = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_USER_MANAGER).toString());
            /*
             *This if loop is to check role of user
             **/
            if (roleName.equals("Operations") || roleName.equals("Sales") || roleName.equals("Admin")) {
                try {
                    String out = "";
                    if (getAccountName().lastIndexOf("*") != -1) {
                        // System.out.println("getAccountName()"+getAccountName());
                        out = ServiceLocator.getAjaxHandlerService().getAccountList(getAccountName().replace("*", "%"));
                    } else {
                        // System.out.println("getAccountName()"+getAccountName());
                        out = ServiceLocator.getAjaxHandlerService().getAccountList(getAccountName());
                    }
                    if (out != null) {
                        httpServletResponse.getWriter().write(out);
                    }

                } catch (ServiceLocatorException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }//Close Session Checking
        return null;
    }

    public String mergeAccount() {
        String[] temp = null;
        String id = "";
        String id2 = "";
        temp = getAccountName().split("[$]");
        for (int i = 0; i < temp.length; i++) {
            if (i == 0) {
                id = temp[i];
            }
            if (i == 1) {
                id2 = temp[i];
            }
        }

        setAccountName(id);
        setAccountName2(id2);

        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            String roleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
//            int isManager = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_USER_MANAGER).toString());
            /*
             *This if loop is to check role of user
             **/

            if (roleName.equals("Operations") || roleName.equals("Sales") || roleName.equals("Admin")) {
                try {
                    String out = "";

                    if (getAccountName().lastIndexOf("*") != -1) {
                        out = ServiceLocator.getAjaxHandlerService().mergeAccounts(getAccountName().replace("*", "%"), getAccountName2().replace("*", "%"));
                    } else {
                        out = ServiceLocator.getAjaxHandlerService().mergeAccounts(getAccountName(), getAccountName2());
                    }
                    if (out != null) {
                        httpServletResponse.getWriter().write(out);
                    }

                } catch (ServiceLocatorException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }//Close Session Checking
        return null;
    }

    public String getNameOfAccount() {
        // System.err.println("Accout Name"+getAccountName());
        // System.err.println("Accout Name2"+getAccountName2());
        try {
            responseString = ServiceLocator.getAjaxHandlerService().getNameOfAccount(this);
            httpServletResponse.setContentType("text/xml");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    /**
     * Name   : getNameOfAccountbyLoginId()
     * Author : Nagireddy seerapu
     * Return : AccountNames existed for given login Id
     *
     */
    public String getNameOfAccountbyLoginId() {
        // System.err.println("Accout Name"+getAccountName());
        // System.err.println("Accout Name2"+getAccountName2());
        String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
        Map TeamMap = (Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP);

        try {

            responseString = ServiceLocator.getAjaxHandlerService().getNameOfAccountbyLoginId(this, loginId, TeamMap);
            httpServletResponse.setContentType("text/xml");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    /**
     *
     * This method is used to Assign an Account to user
     * @return null
     */
    public String assignAccount() {

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            String roleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            int isManager = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_USER_MANAGER).toString());

            String name = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME).toString();
            String loginId1 = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            // String loginId1 = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME).toString();
            if (roleName.equals("Operations") || (roleName.equals("Sales") && isManager == 1)) {

                try {
                    httpServletResponse.setContentType("text/html");
                    // httpServletResponse.getWriter().write(ServiceLocator.getAjaxHandlerService().assignAccount(getAccountName(),getState(),getSecondState(),getLoginId(),loginId1,httpServletRequest));
                    httpServletResponse.getWriter().write(ServiceLocator.getAjaxHandlerService().assignAccount(getAccountName(), getState(), getSecondState(), getLoginId(), name, httpServletRequest));
                } catch (ServiceLocatorException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }//Close Session Checking
        return null;
    }

    /**
     *
     * This method is used to Transfer the Account
     * @return null
     */
    public String accountSearchUpdate() {
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            String roleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            int isManager = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_USER_MANAGER).toString());
            //This if loop is to check role of user
            if (roleName.equals("Operations") || (roleName.equals("Sales") && isManager == 1)) {

                try {
                    Map TeamMap = (Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP);
                    //System.out.print("New team member-----"+getNewMember());
                    String loginid = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                    //TeamMap.put(loginid,loginid);
                    // System.out.println("map------"+TeamMap);
                    httpServletResponse.setContentType("text/html");  //TeamMap.containsKey(getOldTeamMember()) &&
                    if (TeamMap.containsKey(getNewMember()) || getNewMember().equals(loginid)) {
                        // System.err.println("Existed");
                        httpServletResponse.getWriter().write(ServiceLocator.getAjaxHandlerService().accountSearchUpdate(getAccId(), getOldTeamMember(), getNewMember(), getOptType()));
                    } else {
                        //  System.err.println(" Not   Existed");
                        httpServletResponse.getWriter().write("Not your team member");
                    }



                } catch (ServiceLocatorException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }//Close Session Checking
        return null;
    }

    // End Of Harry
    /**
     * This method is used to Serach the Greensheet
     * @return null
     * @throws java.io.IOException
     */
    public String greensheetSearch() throws IOException {
        /*
         *This if loop is to check whether there is Session or not
         **/
        if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null)) {
            try {
                httpServletResponse.setContentType("text/html");
                responseString = ServiceLocator.getAjaxHandlerService().getGreensheetSearch(poStatus, poType, accountName,
                        empFname, empLname, poStartDateFrom, poStartDateTo, poEndDateFrom, poEndDateTo, empStartDateFrom, empStartDateTo, empEndDateFrom, empEndDateTo, country, queryType);

                //System.out.println("responseString >>>>"+responseString);

                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            }
        }//Close Session Checking
        return null;
    }

    //Start Of Sagar
    /**
     *
     * This method is used to Search the Consultant
     * @return null
     * @throws java.io.IOException
     */
    public String consultantSearch() throws IOException {
        /*
         *This if loop is to check whether there is Session or not
         **/
        if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null)) {
            try {
                httpServletResponse.setContentType("text/html");
                //responseString = ServiceLocator.getAjaxHandlerService().getConsultantSearch(fname,skillset,email,practiceid,workAuthor,createdBy);
                responseString = ServiceLocator.getAjaxHandlerService().getConsultantSearch(fname, skillset, email, source, workAuthor, createdBy, location, comments);
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            }
        }//Close Session Checking
        return null;
    }

    /**
     *
     * This method is used to Search the Employee
     * @return null
     * @throws java.io.IOException
     */
    public String employeeSearch() throws IOException {
        /*
         *This if loop is to check whether there is Session or not
         **/
        if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) || (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)) {
            try {
                httpServletResponse.setContentType("text/html");
                responseString = ServiceLocator.getAjaxHandlerService().getEmployeeSearch(fname, lname, skils);
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            }
        }//Close Session Checking
        return null;
    }

    /**
     * This method is used to get the list of users to assign an Issue
     * @return null
     * @throws com.mss.mirage.util.ServiceLocatorException
     * @throws java.io.IOException
     */
    public String catagory() throws ServiceLocatorException, IOException {


        if (catagoryName.equals("Recruiting")) {
            queryString = "SELECT concat(FName,'.',LName) as Name FROM tblEmployee WHERE DepartmentId LIKE 'Recruiting%'"
                    + " AND CurStatus='Active' ORDER BY FName";

        } else if (catagoryName.equals("HR") || catagoryName.equals("SysAdmin")) {
            queryString = "select  concat(FirstName,'.',LastName) as Name  "
                    + "from tblCatagory where CatagoryId = '" + catagoryName + "'";
        } else {
            queryString = "SELECT CONCAT(FName,'.',LName) AS NAME FROM tblEmployee WHERE"
                    + " DepartmentId LIKE '" + catagoryName + "%' AND CurStatus='Active' ORDER BY FName";
            //queryString ="SELECT CONCAT(tblCatagory.FirstName,'.',tblCatagory.LastName) AS NAME FROM tblCatagory,tblEmployee  WHERE tblCatagory.`LoginId` = tblEmployee.`LoginId` AND tblCatagory.CatagoryId = '"+catagoryName+"' and tblEmployee.CurStatus like 'Active%' ORDER BY NAME";   
        }
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            httpServletResponse.setContentType("text/xml");
            responseString = ServiceLocator.getAjaxHandlerService().catagory(queryString, catagoryName);
            httpServletResponse.getWriter().write(responseString);
        }//Close Session Checking
        return null;
    }

    /**
     *
     * This method is used to populate the Territories
     * @return null
     * @throws com.mss.mirage.util.ServiceLocatorException
     * @throws java.io.IOException
     */
    public String getTerritoryData() throws ServiceLocatorException, IOException {
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            httpServletResponse.setContentType("text/xml");
            queryString = "SELECT TerritoryName,RegionName FROM vwRegionTerritory where RegionName = " + "'" + region + "'";
            responseString = ServiceLocator.getAjaxHandlerService().getTerritoryData(queryString, region);
            httpServletResponse.getWriter().write(responseString);
        }//Close Session Checking
        return null;
    }

    /**
     *
     * This method is used to get the Sub projects
     * @return null
     * @throws com.mss.mirage.util.ServiceLocatorException
     * @throws java.io.IOException
     */
    public String getSubProject() throws ServiceLocatorException, IOException {
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            httpServletResponse.setContentType("text/xml");
            setQueryString("Select ProjectName,SubProjectName from vwProjects where ProjectName = " + "'" + getProject() + "'");
            responseString = ServiceLocator.getAjaxHandlerService().getSubProject(queryString, project);
            httpServletResponse.getWriter().write(responseString);
        }//Close Session Checking
        return null;
    }

    /**
     *
     * This method is used to populate the Activities
     * @return null
     * @throws java.lang.Exception
     */
    public String popupActivity() throws Exception {
        //stringBuffer=new StringBuffer();
        String str;
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                str = ServiceLocator.getAjaxHandlerService().popupActivity(activityId);
                httpServletResponse.setContentType("text/html");
                //  httpServletResponse.getOutputStream().write(stringBuffer.toString());
                httpServletResponse.getWriter().write((str));
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }//Close Session Checking

        return null;
    }

    /**
     *
     * This method is used to populate the Comments
     * @return null
     * @throws java.lang.Exception
     */
    public String popupComments() throws Exception {
        String str;
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                str = ServiceLocator.getAjaxHandlerService().popupComments(activityId);
                httpServletResponse.setContentType("text/html");
                httpServletResponse.getWriter().write((str));
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }//Close Session Checking

        return null;
    }

    /**
     * DESC: New popup for issue comments
     * NAME : popupissueCommentsWindow()
     * Returns : Issue Comments
     *
     */
    public String popupissueCommentsWindow() throws Exception {
        String str;
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                str = ServiceLocator.getAjaxHandlerService().popupIssueComments(issueId);
                httpServletResponse.setContentType("text/html");
                httpServletResponse.getWriter().write((str));
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }//Close Session Checking

        return null;
    }
    // Project Issues Action

    /**
     *
     * This method is used to get The Sub-Projects
     * @return null
     * @throws java.io.IOException
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String getSubProjects() throws IOException, ServiceLocatorException {
        httpServletResponse.setContentType("text/xml");

        queryString = "Select ProjectName,SubProjectName from vwProjects where ProjectName = " + "'" + getProject() + "'";
        httpServletResponse.getWriter().write(ServiceLocator.getAjaxHandlerService().getSubProjects(queryString, getProject()));

        return null;
    }

    /**
     * This method is used to get the projects assigned Members
     * @return null
     * @throws java.io.IOException
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public String getProjEmployees() throws IOException, ServiceLocatorException {
        httpServletResponse.setContentType("text/xml");

        queryString = "Select ProjectName,EmpName from vwProjectTeamEmployees where ProjectName = " + "'" + getProject() + "'";
        httpServletResponse.getWriter().write(ServiceLocator.getAjaxHandlerService().getProjEmployees(queryString, getProject()));
        return null;
    }

    // End Of Project Issues
    /**
     *
     * This method is used to get the Consultant Details
     * @return null
     */
    public String getConsultantDetails() {
        try {

            //
            responseString = ServiceLocator.getAjaxHandlerService().getConsultantDetails(httpServletRequest, "SELECT Email2,Id,FName,LName,MName,TitleTypeId,Gender,"
                    + "WorkPhoneNo,HomePhoneNo,CellPhoneNo,WorkAuthorization,SkillSet,RatePerHour,PracticeId,Country,RefferedBy,Comments,Industry,Source,Description,AvailableFrom,Experience,TechnicallyEvaluated,Available,PreferredState FROM tblRecConsultant where Email2 LIKE '" + getConsultantEmail() + "%'");
            httpServletResponse.setContentType("text/xml");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    /**
     *
     * This method is used to get the Consultant Details
     * @return null
     */
    public String getConsultDetails() {
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                responseString = ServiceLocator.getAjaxHandlerService().getConsultDetails(consultantMail);
                httpServletResponse.setContentType("text/html");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }//Close Session Checking
        return null;
    }

    /**
     *
     * This method is used to get the Consultant Resumes
     * @return null
     * @throws java.io.IOException
     */
    public String consultantResume() throws IOException {
        /*
         *This if loop is to check whether there is Session or not
         **/
        if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null)) {
            try {

                httpServletResponse.setContentType("text/html");
                responseString = ServiceLocator.getAjaxHandlerService().getConsultantResume(consultantId);
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            }
        }//Close Session Checking
        return null;
    }

    /*public String getRequirementDetails() {
    try{
    responseString = ServiceLocator.getAjaxHandlerService().getRequirementDetails("SELECT * FROM tblRecRequirement where JobTitle LIKE '"+getTitle()+"%'");
    httpServletResponse.setContentType("text/xml");
    httpServletResponse.getWriter().write(responseString);
    }catch(ServiceLocatorException ex){
    System.err.println(ex);
    }catch(IOException ioe){
    System.err.println(ioe);
    }
    return null;
    }*/
    /**
     *
     * This method is used to get the Consultants List
     * @return null
     */
    public String getConsultantList() {
        try {
            // System.out.println("email--"+getEmail());
             responseString = ServiceLocator.getAjaxHandlerService().getConsultantList("SELECT Id,CONCAT(TRIM(FName),'.',TRIM(LName)) AS FullName,Email2,CellPhoneNo,AvailableFrom,IsReject,RatePerHour,WorkAuthorization FROM tblRecConsultant  WHERE (LName LIKE '" + consultantName + "%' OR FName LIKE '" + consultantName + "%' OR Id LIKE '" + consultantName + "%')");
           
            httpServletResponse.setContentType("text/xml");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    /**
     *
     * This method is used to get the Resume Id
     * @return null
     */
    public String getResumeId() {
        try {
              responseString = ServiceLocator.getAjaxHandlerService().getResumeId("SELECT Id,AttachmentName FROM tblRecAttachments where ObjectId=" + getConsultantId());
           
            httpServletResponse.setContentType("text/xml");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    /**
     *
     * This method is used to add the status for a day in calendar
     * @return null
     */
    public String addStatus() {
        int addStatus = 0;
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            int empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
            try {
                addStatus = ServiceLocator.getAjaxHandlerService().addStatus(empId, getStatus(), getSdate());
                responseString = "<xml version=\"1.0\"><valid>" + addStatus + "</valid></xml>";
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                responseString = "<xml version=\"1.0\"><valid>10</valid></xml>";
                System.err.println(ex);
            } catch (IOException ioe) {
                System.err.println(ioe);
            }
        }//Close Session Checking
        return null;
    }

    /**
     *
     * This method is used to get the status of a day in calendar
     * @return
     */
    public String getDayStatus() {
        int empId = 0;
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                //This if loop is to check the value of currentCaluserId
                if (currentCaluserId == null || currentCaluserId.equals("") || currentCaluserId.equals(" ")) {
                    empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
                } else {
                    empId = Integer.parseInt(currentCaluserId.trim());
                }
                responseString = ServiceLocator.getAjaxHandlerService().getDayStatus(eventYearMonth, empId);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write((responseString));
            } catch (ServiceLocatorException ex) {
                System.err.println(ex);
            } catch (IOException ioe) {
                System.err.println(ioe);
            }
        }//Close Session Checking
        return null;
    }

    /**
     *
     * This method is used to get the Venus reports
     * @return null
     */
    public String getVenusReport() {
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                responseString = ServiceLocator.getAjaxHandlerService().getVenusReport(getVenusStaDate(), getVenusEndDate(), getVenusDeptId(), getVenusEmpName());
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }//Close Session Checking
        return null;
    }

    public String getTimeSheetsReport() {

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            String tsheetStartDate[] = null;
            String tsheetEndDate[] = null;

            try {
                tsheetStartDate = getTimeSheetWeekStartDate().split("/");

                timeSheetWeekStartDate = tsheetStartDate[2] + "-" + tsheetStartDate[0] + "-" + tsheetStartDate[1];

                tsheetEndDate = getTimeSheetWeekEndDate().split("/");

                timeSheetWeekEndDate = tsheetEndDate[2] + "-" + tsheetEndDate[0] + "-" + tsheetEndDate[1];

                responseString = ServiceLocator.getAjaxHandlerService().getTimeSheetsReport(getDepartmentId(), timeSheetWeekStartDate, timeSheetWeekEndDate);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                if (tsheetStartDate != null) {
                    tsheetStartDate = null;
                }
                if (tsheetEndDate != null) {
                    tsheetEndDate = null;
                }
            }
        }//Close Session Checking

        return null;
    }

    /**
     *
     * This method is used to get the EmployeeNames
     * @return null
     */
    public String getNotApprovedReport() {

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            String tsheetStartDate[] = null;
            String tsheetEndDate[] = null;

            try {
                tsheetStartDate = getNotApprovedStartDate().split("/");

                timeSheetWeekStartDate = tsheetStartDate[2] + "-" + tsheetStartDate[0] + "-" + tsheetStartDate[1];

                tsheetEndDate = getNotApprovedEndDate().split("/");

                timeSheetWeekEndDate = tsheetEndDate[2] + "-" + tsheetEndDate[0] + "-" + tsheetEndDate[1];

                responseString = ServiceLocator.getAjaxHandlerService().getNotApprovedReport(getNotApprovedDepartmentId(), timeSheetWeekStartDate, timeSheetWeekEndDate);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                if (tsheetStartDate != null) {
                    tsheetStartDate = null;
                }
                if (tsheetEndDate != null) {
                    tsheetEndDate = null;
                }
            }
        }//Close Session Checking

        return null;
    }

    /**
     *
     * This method is used to get the Activities in Dashboard
     * @return null
     */
    public String getActivityDashBoard() {
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                setMyTeamMembers((Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
                String curWorkCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
                // Retrieve the List of all the Team Members belonging to the Logged in User
                String myTeamMembers = getKeys(getMyTeamMembers(), ",");
                responseString = ServiceLocator.getAjaxHandlerService().getActivityDashBoard(activityStaDate, activityEndDate, myTeamMembers, curWorkCountry);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }//Close Session Checking
        return null;
    }

    /**
     *
     * This method is used to send the keys with delimiters
     * @param map
     * @param delimiter
     * @return keys
     */
    private String getKeys(Map map, String delimiter) {
        Iterator tempIterator = map.entrySet().iterator();
        String keys = "";
        String key = "";
        int cnt = 0;

        while (tempIterator.hasNext()) {
            Map.Entry entry = (Map.Entry) tempIterator.next();
            key = entry.getKey().toString();
            entry = null;

            //  Add the Delimiter to the Keys Field for the Second Key onwards
            if (cnt > 0) {
                keys = keys + delimiter;
            }

            keys = keys + "'" + key + "'";
            cnt++;
        }
        tempIterator = null;
        return (keys);
    }

    /**
     *
     * This method is used to get the Absentee Report
     * @return null
     */
    public String getAbsanteeReport() {
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                responseString = ServiceLocator.getAjaxHandlerService().getAbsanteeReport(getVenusStaDate(), getVenusEndDate(), getVenusDeptId(), getVenusEmpName());
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }//Close Session Checking
        return null;
    }

    public String contactSearch() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                responseString = ServiceLocator.getAjaxHandlerService().getContactSearch(contactSearchName, getContactTitle(),conAccId);
               
               // responseString = ServiceLocator.getAjaxHandlerService().getContactSearch(contactSearchName, conAccId);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
    //New Action 

    public String getcontactList() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {

                responseString = ServiceLocator.getAjaxHandlerService().getContactList(conAccId);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public String getContactActivityList() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                responseString = ServiceLocator.getAjaxHandlerService().getActivityLists(curcontactId, actAccId);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public String getAddActivityList() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                responseString = ServiceLocator.getAjaxHandlerService().getAddSubActivityList(curcontactId, actAccId);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    /**
     *
     * This method is used to set the Servlet Request
     * @param httpServletRequest
     */
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    /**
     *
     * This method is used to set the Servlet Response
     * @param httpServletResponse
     */
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    /**
     *
     * @param image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /*
     *   Ajax Calls For Calendar  in CRM
     *
     *
     **/
    /*
     *   Ajax Calls For Calendar  in CRM
     *
     *
     **/
    public String getWeeklyEvents() {

        int empId = 0;
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            /*
             *This if loop is to check for Authentication
             **/
            if (AuthorizationManager.getInstance().isAuthorizedUser("CALENDAR_ACCESS", userRoleId)) {
                try {
                    //This if loop is to check the value of currentCaluserId
                    if (currentCaluserId == null || currentCaluserId.equals("") || currentCaluserId.equals(" ")) {

                        empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
                    } else {
                        empId = Integer.parseInt(currentCaluserId);//DataSourceDataProvider.getInstance().getEmpIdByLoginId(currentCaluserId);
                    }
                    queryString = "select Id,EventType,Description,CreatedById,EventCreatedDate,EventEndDate,ModifiedById,AccountId,ContactId from tblCrmCalendar where EventCreatedDate > '" + weekStartDate + "' and EventCreatedDate < '" + weekEndDate + "' and EmpId = '" + empId + "' order by EventCreatedDate ASC";
                    responseString = ServiceLocator.getAjaxHandlerService().getWeeklyEvents(queryString);
                    httpServletResponse.setContentType("text/xml");
                    httpServletResponse.getWriter().write(responseString);
                } catch (ServiceLocatorException ex) {
                    ex.printStackTrace();
                } catch (IOException io) {
                    io.printStackTrace();
                }
            }//Close Authentication Checking
        }//Close Session Checking
        return null;
    }

    public String getWeekDates() {
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            /*
             *This if loop is to check for Authentication
             **/
            if (AuthorizationManager.getInstance().isAuthorizedUser("CALENDAR_ACCESS", userRoleId)) {
                try {
                    String stortingDate = new java.text.SimpleDateFormat("MM/dd/yyyy").format(weekDate);
                    String splitDate[] = stortingDate.split("/");
                    int mon = Integer.parseInt(splitDate[0]);
                    int day = Integer.parseInt(splitDate[1]);
                    int year = Integer.parseInt(splitDate[2]);

                    /* for setting the date for the spliting filed */
                    Calendar previouseCalender = Calendar.getInstance();

                    previouseCalender.set(Calendar.YEAR, year);
                    previouseCalender.set(Calendar.MONTH, mon - 1); // becoz the index is starting 0
                    previouseCalender.set(Calendar.DAY_OF_MONTH, day);
                    httpServletResponse.setContentType("text/xml");
                    responseString = ServiceLocator.getAjaxHandlerService().getWeekDates(previouseCalender);
                    httpServletResponse.getWriter().write(responseString);
                    //System.err.println("Response is --"+responseString);
                } catch (ServiceLocatorException ex) {
                    ex.printStackTrace();
                } catch (IOException io) {
                    io.printStackTrace();
                }
            }//Close Authentication Checking

        }//Close Session Checking
        return null;
    }

    public String getDailyEventsView() {
        int empId = 0;
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            //if(AuthorizationManager.getInstance().isAuthorizedUser("CALENDAR_ACCESS",userRoleId)){
            try {
                //This if loop is o check the value of currentCaluserId
                if (currentCaluserId == null || currentCaluserId.equals("") || currentCaluserId.equals(" ")) {
                    empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
                    //  System.err.println("empIddddddddddddddd."+empId);
                } else {
                    empId = Integer.parseInt(currentCaluserId);//DataSourceDataProvider.getInstance().getEmpIdByLoginId(currentCaluserId);
                }
                //String empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
                queryString = "select Id,EventType,Description,CreatedById,EventCreatedDate,EventEndDate,ModifiedById,AccountId,ContactId from tblCrmCalendar where EventCreatedDate like '%" + eventDate + "%' and EmpId = '" + empId + "' order by EventCreatedDate ASC";
                responseString = ServiceLocator.getAjaxHandlerService().getDaillyEvents(queryString);
                httpServletResponse.setContentType("text/xml");
                //System.err.println("Respionse string ***Daily View****"+responseString);
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException io) {
                io.printStackTrace();
            }
            //}
        }//Close Session Checking

        return null;
    }

    /* Harry Monthly view Merthods-------
     *
     */
    /**
     *
     * This method is used to populate the Events
     * @return null
     * @throws java.lang.Exception
     */
    public String popupEvent() throws Exception {
        int empId = 0;
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                //empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
                //This if loop is o check the value of currentCaluserId
                if (currentCaluserId == null || currentCaluserId.equals("") || currentCaluserId.equals(" ")) {
                    empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
                } else {
                    empId = Integer.parseInt(currentCaluserId.trim());
                }
                responseString = ServiceLocator.getAjaxHandlerService().popupEvent(eventYearMonth, empId);
                httpServletResponse.setContentType("text/xml");
                //  httpServletResponse.getOutputStream().write(stringBuffer.toString());
                httpServletResponse.getWriter().write((responseString));
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }//Close Session Checking
        return null;
    }

    /* Venki Add Event Methods and Populate Account Team Memebers
     *
     *
     */
    /**
     *
     * This method is used to get The Account Team Id
     * @return null
     */
    public String getAccountTeamId() {
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                responseString = ServiceLocator.getAjaxHandlerService().getAccountTeamId(accTeamId);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }//Close Session Checking
        return null;
    }

    /**
     *
     * This method is used to Add the Event in Calendar
     * @return null
     */
    public String addEvent() {
        int updateEvent = 0;
        int empId;
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            String userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            //This if loop is o check the value of calAccessUserId
            if (calAccessUserId == null || calAccessUserId.equals("") || calAccessUserId.equals(" ")) {
                empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
            } else {
                empId = Integer.parseInt(calAccessUserId.trim());
            }
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            /*
             *This if loop is to check for Authentication
             **/
            if (AuthorizationManager.getInstance().isAuthorizedUser("CALENDAR_ACCESS", userRoleId)) {
                try {
                    setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                    updateEvent = ServiceLocator.getAjaxHandlerService().addEvent(empId, getCalEventDate(), getEventType(), getEventDesc(), getAccTeam(), getCreatedBy(), getEveAccId(), getContactsId(), getCalEveId1(), CalEventDate1);
                    responseString = "<xml version=\"1.0\"><valid>" + updateEvent + "</valid></xml>";
                    httpServletResponse.setContentType("text/xml");
                    httpServletResponse.getWriter().write(responseString);
                } catch (ServiceLocatorException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }//Close Authentication Checking
        }//Close Session Checking
        return null;
    }

    /**
     *
     * This method is used to get The Event Details in calendar
     * @return null
     */
    public String getEventDetails() {
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                responseString = ServiceLocator.getAjaxHandlerService().getEventDetails(getEventId());
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }//Close Session Checking
        return null;
    }

    /**
     *
     * This method is used to get the User list of Calendar
     * @return null
     */
    public String getCalUserList() {
        resultType = LOGIN;
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            /*
             *This if loop is to check for Authentication
             **/
            if (AuthorizationManager.getInstance().isAuthorizedUser("CALENDAR_ACCESS", userRoleId)) {
                try {
                    String empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
                    responseString = ServiceLocator.getAjaxHandlerService().getCalendarUsersList(empId);
                    httpServletResponse.setContentType("text/xml");
                    httpServletResponse.getWriter().write(responseString);
                    resultType = null;
                } catch (ServiceLocatorException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }//Close Authentication Checking
        }//Close Session Checking
        return resultType;
    }

    /**
     *
     * This method is used to populate the list of Calendar users
     * @return null
     */
    public String getUserPopupList() {
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            /*
             *This if loop is to check for Authentication
             **/
            if (AuthorizationManager.getInstance().isAuthorizedUser("CALENDAR_ACCESS", userRoleId)) {
                try {
                    String empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
                    responseString = ServiceLocator.getAjaxHandlerService().getUserPopupList(getUserName());
                    //System.err.println("response is ---"+responseString);
                    httpServletResponse.setContentType("text/xml");
                    httpServletResponse.getWriter().write(responseString);
                    //System.err.println("responseString--"+responseString);
                    resultType = null;
                } catch (ServiceLocatorException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }//Close Authentication Checking
        }//Close Session Checking
        return null;
    }

    /**
     *
     * This method is used to save the Calendar Access
     * @return
     */
    public String saveCalAccessUser() {
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            /*
             *This if loop is to check for Authentication
             **/
            if (AuthorizationManager.getInstance().isAuthorizedUser("CALENDAR_ACCESS", userRoleId)) {
                try {
                    String empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
                    loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                    responseString = ServiceLocator.getAjaxHandlerService().saveCalAccessUser(empId, getAccessUserId(), saveType, getAccessType(), loginId, getAccessId());
                    //System.err.println("response is ---"+responseString);
                    httpServletResponse.setContentType("text/xml");
                    httpServletResponse.getWriter().write(responseString);
                    //System.err.println("responseString--"+responseString);
                } catch (ServiceLocatorException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }//Close Authentication Checking
        }//Close Session Checking
        return null;
    }

    /**
     *
     * This method is used to Remove the Calendar Access
     * @return null
     */
    public String deleteCalAccessUser() {
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            /*
             *This if loop is to check for Authentication
             **/
            if (AuthorizationManager.getInstance().isAuthorizedUser("CALENDAR_ACCESS", userRoleId)) {
                try {
                    String empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
                    loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                    responseString = ServiceLocator.getAjaxHandlerService().deleteCalAccessUser(accessId);
                    //System.err.println("response is ---"+responseString);
                    httpServletResponse.setContentType("text/xml");
                    httpServletResponse.getWriter().write(responseString);
                    //System.err.println("responseString--"+responseString);
                } catch (ServiceLocatorException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }//Close Authentication Checking
        }//Close Session Checking
        return null;
    }

    /**
     *
     * This method is used to populate the Calendar Access list
     * @return null
     */
    public String populateCalendarAccess() { // this method is for checking the user access
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            // if(AuthorizationManager.getInstance().isAuthorizedUser("CALENDAR_ACCESS",userRoleId)){
            String empId;
            try {
                //System.err.println("currentCaluserId--"+currentCaluserId);
                //System.err.println("in if");
                empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
                //empId = currentCaluserId.trim();
                //System.err.println("empId--"+empId);
                //String empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
                responseString = ServiceLocator.getAjaxHandlerService().getAccessCalendar(empId, currentCaluserId);
                httpServletResponse.setContentType("text/xml");
                //System.err.println("response string is"+responseString);
                httpServletResponse.getWriter().write(responseString);

            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            // }
        }//Close Session Checking

        return null;
    }

    /**
     *
     * This method is used to populate a wimdow for comments
     * @return null
     */
    public String popupWindowForComments() {
        String str;
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                str = ServiceLocator.getAjaxHandlerService().popupComment(activityId);
                httpServletResponse.setContentType("text/html");
                httpServletResponse.getWriter().write((str));
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }//Close Session Checking

        return null;
    }

    /**
     *
     * This method is used to get the Team members
     * @return null
     */
    public String getEmployeeTeamNames() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                responseString = ServiceLocator.getAjaxHandlerService().getEmpTeamNames(getTeamName());
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     *
     * This method is used to get the Project Status
     * @return null
     */
    public String getProjectStatus() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                responseString = ServiceLocator.getAjaxHandlerService().getProjectsList(getProject());
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     *
     * This method is used to add the Project Status
     * @return null
     */
    public String addProjectStatus() {
        int isAdd = 0;
        String userId = null;
        try {
            userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                isAdd = ServiceLocator.getAjaxHandlerService().addProjectStatus(userId, projectId, statusCode, reason);
                responseString = "<xml version=\"1.0\"><valid>" + isAdd + "</valid></xml>";
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            responseString = "<xml version=\"1.0\"><valid>10</valid></xml>";
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     *
     * This method is used to get the Total Holidays
     * @return null
     */
    public String getTotalHolidays() {
        String userId = null;
        String userList = null;
        int holidays = 0;
        int teamStreangth = 0;
        try {
            userId = (String) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID);
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                holidays = ServiceLocator.getAjaxHandlerService().getTotalHolidays(getYear(), getMonth());
                DataSourceDataProvider dataSourceDataProvider = null;
                userList = dataSourceDataProvider.getInstance().getLeaveApprovalList(userId);
                teamStreangth = ServiceLocator.getAjaxHandlerService().getTeamStrangth(userId, userList);
                int totalHours = ServiceLocator.getGeneralService().getUsableTeamHours(getUsableDays(), holidays, teamStreangth);
                responseString = "<xml version=\"1.0\"><valid>" + totalHours + "</valid></xml>";
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     *
     * This method is used to get the Skillset of Consultant
     * @return null
     */
    public String popupSkillsWindow() {
        String str;
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                str = ServiceLocator.getAjaxHandlerService().popupSkills(consultantId);
                httpServletResponse.setContentType("text/html");
                if (str != null) {
                    httpServletResponse.getWriter().write((str));
                } else {
                    httpServletResponse.getWriter().write(("No Record Present"));
                }

            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }//Close Session Checking
        return null;
    }

    public String popupReqSkillsWindow() {
        String str;
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                str = ServiceLocator.getAjaxHandlerService().popupReqSkills(requirementId);
                httpServletResponse.setContentType("text/html");
                if (str != null) {
                    httpServletResponse.getWriter().write((str));
                } else {
                    httpServletResponse.getWriter().write(("No Record Present"));
                }

            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }//Close Session Checking
        return null;
    }

    public String popupReqPersonWindow() {
        String str;
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                str = ServiceLocator.getAjaxHandlerService().popupReqPersonDetails(getRecruiterId());
                httpServletResponse.setContentType("text/html");
                if (str != null) {
                    httpServletResponse.getWriter().write((str));
                } else {
                    httpServletResponse.getWriter().write(("No Record Present"));
                }

            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }//Close Session Checking
        return null;
    }

    /**
     *Leave details popwindow
     */
    public String popupLeaveDetailsWindow() {
        String str;
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                str = getEmpName() + "^";
                str = str + ServiceLocator.getAjaxHandlerService().popupLeaveDetails(getLeaveId());
                //str = str+"^"+getEmpName();
                httpServletResponse.setContentType("text/html");
                if (str != null) {
                    httpServletResponse.getWriter().write((str));
                } else {
                    httpServletResponse.getWriter().write(("No Record Present"));
                }
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        //Close Session Checking
        return null;
    }

    public String getAccountsByRep() {
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            int isManager = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_USER_MANAGER).toString());
            int isTeamLead = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_TEAM_LEAD).toString());
            // String curWorkCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString().trim();
            // curWorkCountry = curWorkCountry + "%";
            // System.out.println("COUNT IS  "+curWorkCountry);
            /*
             *This if loop is to check the Authentication
             **/
            if (AuthorizationManager.getInstance().isAuthorizedUser("DASHBOARD_ACCESS", userRoleId)) {//&& ((isManager ==1) || (isTeamLead ==1)) ){
                try {
                   // System.out.println("haiiii");
                    //  responseString = ServiceLocator.getAjaxHandlerService().getAccountsByRep(getDashBoardStartDateRep(), getDashBoardEndDateRep(), httpServletRequest);
                   // responseString = ServiceLocator.getAjaxHandlerService().getAccountsByRep(getDashBoardStartDateRep(), getDashBoardEndDateRep(), httpServletRequest, getPracticeName());
                    responseString = ServiceLocator.getAjaxHandlerService().getAccountsByRep(getDashBoardStartDateRep(), getDashBoardEndDateRep(), httpServletRequest, getPracticeName(),getTeamMemberId());
                  //System.out.println("Response-->"+responseString);
                    httpServletResponse.setContentType("text");
                    httpServletResponse.getWriter().write(responseString);
                } catch (Exception ex) {
                }
            }
        }//Close Session Checking
        return null;
    }

    /* New Method sfor Ajax cll to Activity list and All Activity List..*/
    //New Action for getting Account activities
    public String activitySearch() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {

                responseString = ServiceLocator.getAjaxHandlerService().getactivitySearch(actAccId);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
//New Action for getting Account AllActivities   

    public String allActivitySearch() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                responseString = ServiceLocator.getAjaxHandlerService().getAllActivitySearch(activitySearchName, actAccId);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    //New Action for getting Account activities
    public String requirementAjaxList() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {

                responseString = ServiceLocator.getAjaxHandlerService().getrequirementAjaxList(httpServletRequest);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Name : searchRequirementAjaxList
     *  
     */
    public String searchRequirementAjaxList() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            //AjaxHandlerAction ajaxHandlerAction = new AjaxHandlerAction();
            try {

                // System.out.println("values :::::::");
                //createdBy,assignedTo,title,postedDate1,postedDate2
                // System.out.println("createdBy----------"+getCreatedBy()+"---assignedTo-------------"+getAssignedTo()+"---title-------------"+getTitle()+"---postedDate1-------"+getPostedDate1()+"----postedDate2----"+getPostedDate2());

                responseString = ServiceLocator.getAjaxHandlerService().getsearchRequirementAjaxList(httpServletRequest, this);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    /**
     * New Action for getting territory And States List.
     *
     */
    public String getTeritoryAndStatesList() throws ServiceLocatorException {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            try {

                String emp_Id = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                //System.out.println("emp Id ----------"+emp_Id);

                responseString = ServiceLocator.getAjaxHandlerService().getTeritoryAndStatesList(emp_Id, httpServletRequest);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);


            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
    //new on 10162012 for assign Accounts for BDM  

    /**
     * Author :Nagireddy seerapu
     * Details : To get the List account which are not assigned to BDM and his team.
     *
     */
    public String getAccounts() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            String loginId1 = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();

            try {
                httpServletResponse.setContentType("text/html");
                httpServletResponse.getWriter().write(ServiceLocator.getAjaxHandlerService().getAccounts(this, httpServletRequest, loginId1));
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    //new on 10162012 for assign Accounts for BDM
    /**
     * Author : Nagireddy seerapu
     * DES: To set new team mamber for an account.
     */
    public String accountUpdateForBdm() {

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                String loginid = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                // String reportsTo = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_REPORTSTO_ID).toString();
                httpServletResponse.setContentType("text/html");
                //System.out.println("hi");
                // System.out.println("in ajax handler Action --->"+ServiceLocator.getAjaxHandlerService().accountSearchUpdatebdm(Integer.parseInt(getAccId()),loginid));
                // httpServletResponse.getWriter().write(ServiceLocator.getAjaxHandlerService().accountSearchUpdatebdm(Integer.parseInt(getAccId()),loginid));
                httpServletResponse.getWriter().write(ServiceLocator.getAjaxHandlerService().accountSearchUpdatebdm(getAccId(), loginid));
                //httpServletResponse.getWriter().write(ServiceLocator.getAjaxHandlerService().accountSearchUpdatebdm(getAccId(),loginid, getAccountName(), reportsTo));
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public String getEmpTerritory() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                responseString = ServiceLocator.getAjaxHandlerService().getEmpTerritory(practiceName);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /*
     *Dec : for getting the states associated with the territory
     *
     */
    public String getStatesbyTerritory() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                String result = ServiceLocator.getAjaxHandlerService().getStatesByTerritory(this);
                // System.out.println("in action-->"+result);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(result);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;



    }

    /**
     *
     * This method is used to get The ProjectNames List of a Account/Customer
     * @return null
     */
    public String getProjectNamesList() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                //responseString = ServiceLocator.getAjaxHandlerService().getProjectNamesList(getAccId());
                responseString = ServiceLocator.getAjaxHandlerService().getProjectNamesList(getAccId(), getProjectName());

                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     *Date : 06102013
     * This method is used to get the getEmployeeDetails
     * @return null
     */
    /*  public String getEmployeeDetails(){
    try{
    //select concat(FName,'.',LName) as Name from tblEmployee
    responseString = ServiceLocator.getAjaxHandlerService().getEmployeeDetails("SELECT concat(trim(FName),'.',trim(LName)) AS FullName,LoginId FROM tblEmployee WHERE (LName LIKE '"+getCustomerName()+"%' OR FName LIKE '"+getCustomerName()+"%') and CurStatus='Active' LIMIT 30");
    httpServletResponse.setContentType("text/xml");
    httpServletResponse.getWriter().write(responseString);
    }catch(ServiceLocatorException ex){
    System.err.println(ex);
    }catch(IOException ioe){
    System.err.println(ioe);
    }
    return null;
    }*/
    public String getEmployeeDetails() {
        try {
            //select concat(FName,'.',LName) as Name from tblEmployee
            String resourceType = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();

            if (resourceType.equalsIgnoreCase("e")) {
                responseString = ServiceLocator.getAjaxHandlerService().getEmployeeDetails("SELECT concat(trim(FName),'.',trim(LName)) AS FullName,LoginId FROM tblEmployee WHERE (LName LIKE '" + getCustomerName() + "%' OR FName LIKE '" + getCustomerName() + "%') and CurStatus='Active' LIMIT 30");
            }
            /*else if(resourceType.equalsIgnoreCase("c"))
            {
            String projectIds=null;
            List projectIdsList=(List)httpServletRequest.getSession(false).getAttribute( ApplicationConstants.SESSION_CUSTOMER_PROJECTIDS);
            if(projectIdsList != null) {
            if(projectIdsList.size()>0) {
            projectIds = projectIdsList.toString().replace("[", "");
            projectIds = projectIds.replace("]", "");
            }
            }
            responseString = ServiceLocator.getAjaxHandlerService().getEmployeeDetails("SELECT ResourceName ,ObjectId FROM tblProjectContacts WHERE ResourceName like '%"+getCustomerName()+"%' AND ProjectId in ("+projectIds+")  LIMIT 30");
            }
             */
            httpServletResponse.setContentType("text/xml");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    /**
     * getCustomer for Tasks
     *
     */
    public String getCustomerDetails() {
        try {
            responseString = ServiceLocator.getAjaxHandlerService().getCustomerDetails("SELECT Name, Id FROM tblCrmAccount where Name LIKE '" + getCustomerName() + "%' LIMIT 30");
            httpServletResponse.setContentType("text/xml");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    public String popuptasksDescWindow() throws Exception {
        String str;
        /*
         *This if loop is to check whether there is Session or not
         **/
        // if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
        if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) || (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)) {
            try {
                str = ServiceLocator.getAjaxHandlerService().popupTaskDesc(issueId);
                httpServletResponse.setContentType("text/html");
                httpServletResponse.getWriter().write((str));
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }//Close Session Checking

        return null;
    }

    /**
     * Ecertification Method to get the topic name based domain id
     * Dated :  07-0719-2013
     */
    /*
     *Ecertification Methods
     *Date : 07/18/2013
     *
     */
    public String getDomainTopics() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                responseString = ServiceLocator.getAjaxHandlerService().getTopicNamesBasedOnDomain(getDomainId());
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /*
     *Get Questions 07/23/2013
     */
    /*  public String getQuestion() {
    try {
    
    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
    
    responseString = ServiceLocator.getAjaxHandlerService().getQuestion(getQuestionNo(),httpServletRequest,getSelectedAns(),getNavigation(),getRemainingQuestions(),getOnClickStatus(),getSubTopicId());
    httpServletResponse.setContentType("text/xml");
    httpServletResponse.getWriter().write(responseString);
    }//Close Session Checking
    } catch (ServiceLocatorException ex) {
    ex.printStackTrace();
    }catch (IOException ex) {
    ex.printStackTrace();
    }
    return null;
    }*/
    /*
     *Get Questions 07/23/2013
     * Modified By santosh Kola
     * Modified Date : 03/06/2014
     */
    public String getQuestion() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

                // responseString = ServiceLocator.getAjaxHandlerService().getQuestion(getQuestionNo(),httpServletRequest,getSelectedAns(),getNavigation(),getRemainingQuestions(),getOnClickStatus(),getSubTopicId());
                responseString = ServiceLocator.getAjaxHandlerService().getQuestion(getQuestionNo(), httpServletRequest, getSelectedAns(), getNavigation(), getRemainingQuestions(), getOnClickStatus(), getSubTopicId(), getSpecficQuestionNo());
                httpServletResponse.setContentType("text/xml");
                //System.out.println("responseString-->"+responseString);
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /*
     *get Subtopics
     */
    /*New method to display questions list in admin section
     *Author:Prasad kandregula
     */
    public String questionsAjaxList() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {

                String userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                responseString = ServiceLocator.getAjaxHandlerService().getQuestionsAjaxList(getSubTopicId(), userId);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    /*
     *Method to get Questions Count and available Count
     *Date : 07/25/2013
     */
    public String getCurrentQuestionsCount() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {

                String userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                //  System.out.println("userId-->"+userId);

                responseString = ServiceLocator.getAjaxHandlerService().getCurrentQuestionsCount(getSubTopicId(), userId, getTotalQuestions());
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    /*
     *For getting time and count of questions
     *Date : 29/2013
     */
    public String validateExam() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

                responseString = ServiceLocator.getAjaxHandlerService().validateExam(getExamValidationKey(), httpServletRequest);
                httpServletResponse.setContentType("text");

                //  System.out.println("responseString---------------------->"+responseString);
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    /*Method to get Available questions count
     *Date : 08/05/2013
     *
     */

    public String getAvailableQuestionsCount() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

                responseString = ServiceLocator.getAjaxHandlerService().getAvailableQuestionsCount(getTopicId());
                httpServletResponse.setContentType("text");

                //  System.out.println("responseString---------------------->"+responseString);
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    /*
     **Ajay Changes 08072013
     **
     */
    /*
     *Ajay Tummapala
     *
     *for Popup window for questions search
     */

    public String popupQuestionsWindow() throws Exception {
        String str;
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                str = ServiceLocator.getAjaxHandlerService().popupQuestionsWindow(getQuestionId());
                httpServletResponse.setContentType("text/html");
                httpServletResponse.getWriter().write((str));
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }//Close Session Checking

        return null;
    }
    /*
     *Ajay Tummapala 
     *Desc : for getting emp which are not authors
     */

    public String getEmployeeDetailsForAuthors() {
        try {
            //select concat(FName,'.',LName) as Name from tblEmployee
            responseString = ServiceLocator.getAjaxHandlerService().getEmployeeDetails("SELECT concat(trim(FName),'.',trim(LName)) AS FullName,LoginId FROM tblEmployee WHERE (LName LIKE '" + getCustomerName() + "%' OR FName LIKE '" + getCustomerName() + "%') and CurStatus='Active' AND LoginId NOT IN(SELECT AuthorId FROM tblEcertTopicAuthors WHERE TopicId = " + getTopicId() + " AND tblEcertTopicAuthors.Status='Active') LIMIT 50");
            httpServletResponse.setContentType("text/xml");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    /*Cre Comments window 
     *Date : 08/28/2013
     *
     */
    public String popupCreCommentsWindow() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                String result = ServiceLocator.getAjaxHandlerService().popupCreCommentsWindow(getCreConsultantId(), getCreConsultantLevel());
                // System.out.println("in action-->"+result);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(result);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;

    }

    /**
     *
     *
     *
     *  CRE Actions
     *
     *
     *
     *
     *
     */
    public String getCreRecordsList() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            String loginId1 = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();

            try {

                httpServletResponse.setContentType("text/html");
                // httpServletResponse.getWriter().write(ServiceLocator.getAjaxHandlerService().getCreRecordsList(getCreConsultantId(),getCreConsultantName(),getCreStartDate(),getCreToDate(), getCreConsultantStatus(), getCategory(),getLevel(),getInterviewAt()));
                String response = ServiceLocator.getAjaxHandlerService().getCreRecordsList(getCreConsultantId(), getCreStartDate(), getCreToDate(), getCreConsultantStatus(), getInterviewAt(), getCreConsultantId1(),getCreCollegeName(),getCourse(),getCreStream());
                httpServletResponse.getWriter().write(response);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    /*Method to activate consultant records
     *Date : 08/29/2013
     */
    public String creRecordStatusUpdate() {

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                String loginid = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();

                httpServletResponse.setContentType("text/html");

                // httpServletRequest.getSession(false).setAttribute(ApplicationConstants.SESSION_CONSULTANT_IDS,getCreConsultantId());
                //  httpServletResponse.getWriter().write(ServiceLocator.getAjaxHandlerService().creRecordStatusUpdate(getCreConsultantId(),loginid, getCreConsultantStatus(),getSubTopicsList()));
                //httpServletResponse.getWriter().write("SUCCESS");
                // httpServletResponse.getWriter().write(ServiceLocator.getAjaxHandlerService().creRecordStatusUpdate(getCreConsultantId(),loginid, getCreConsultantStatus(),getSubTopicsList(), getTotalQues(),getTotDuration(),getMinMarks()));
                httpServletResponse.getWriter().write(ServiceLocator.getAjaxHandlerService().creRecordStatusUpdate(getCreConsultantId(), loginid, getCreConsultantStatus(), getExamNameIdList()));

            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    /*Populate Technical lead Comments
     *Date : 08/30/2013
     */
    public String popupTechLeadComments() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                String result = ServiceLocator.getAjaxHandlerService().popupTechLeadComments(getTechReviewId());
                // System.out.println("in action-->"+result);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(result);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;

    }

    /*Populate Hr lead Comments
     *Date : 08/30/2013
     */
    public String popupHrLeadComments() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                String result = ServiceLocator.getAjaxHandlerService().popupHrLeadComments(getHrReviewId());
                // System.out.println("in action-->"+result);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(result);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;

    }

    /*Populate Vp lead Comments
     *Date : 08/30/2013
     */
    public String popupVpComments() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                String result = ServiceLocator.getAjaxHandlerService().popupVpComments(getVpReviewId());
                // System.out.println("in action-->"+result);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(result);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;

    }

    public String ConsultentAsEmp() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            String result = null;
            try {
                result = ServiceLocator.getAjaxHandlerService().checkEmail(getEmail());
                //System.out.println("in action-->"+result);
                String empLoginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                //System.out.println("empLoginId---->"+empLoginId);
                if (result.equalsIgnoreCase("Existed")) {
                    httpServletResponse.setContentType("text");
                    httpServletResponse.getWriter().write("E");
                } else {
                    String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                    // System.out.println("getConsultantId(),getEmail()"+getConsultantId()+"-----------"+getEmail());
                    result = ServiceLocator.getAjaxHandlerService().addAsEmployee(getConsultantId(), getEmail(), empLoginId);

                    httpServletResponse.setContentType("text");
                    httpServletResponse.getWriter().write(result);

                }


            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 
     * Author : Ajay tummapala
     * @return 
     */
    public String popupWindowForCreatedDate() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                String result = ServiceLocator.getAjaxHandlerService().popupWindowForCreatedDate(getActivityId());
                // System.out.println("in action-->"+result);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(result);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;

    }

    public String popupWindowForModifiedDate() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                String result = ServiceLocator.getAjaxHandlerService().popupWindowForModifiedDate(getActivityId());
                // System.out.println("in action-->"+result);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(result);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;

    }

    public String getCreAvailCreQuestions() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                String result = ServiceLocator.getAjaxHandlerService().getCreAvailCreQuestions(getSubTopicsList());
                // System.out.println("in action-->"+result);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(result);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public String getCreDetailExamInfo() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                String result = ServiceLocator.getAjaxHandlerService().getCreDetailExamInfo(getExamKeyId());
                // System.out.println("in action-->"+result);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(result);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public String getExamCandidateName() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                String result = ServiceLocator.getAjaxHandlerService().getExamCandidateName(getEmpId(), getTopicName());
                // System.out.println("in action-->"+result);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(result);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public String popupTechCommentsWindow() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                String result = ServiceLocator.getAjaxHandlerService().popupCommentsWindow(getConsultantId());
                // System.out.println("in action-->"+result);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(result);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;

    }

    public String popupNewTechCommentsWindow() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                String result = ServiceLocator.getAjaxHandlerService().popupNewTechCommentsWindow(getConsultantId());
                // System.out.println("in action-->"+result);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(result);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;

    }

//Emp state account deletion start 
    //Date : 12/04/2013
    public String empStateAccountDelete() {

        int count = 0;
        String responseString = null;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {

                count = ServiceLocator.getAjaxHandlerService().empStateAccountDelete(getTeamMemberId(), getDelStateAcc());
                if (count > 0) {
                    responseString = "Sucessfully deleted assigned account(s) for " + getDelStateAcc() + " to given Id";
                } else {
                    responseString = "No Accounts assigned with given State or Invalid State given";
                }
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
        return null;
    }
    //Emp state account deletion end          
/*Method : sendRepFeedback
     * Author : Santosh Kola
     * Date : 01/02/2014
     * Description : To send feedback mail to sales Representative .
     * 
     */

    public String sendRepFeedback() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                String fromId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                String result = ServiceLocator.getAjaxHandlerService().sendRepFeedback(getEmpName(), getLoginId(), getReportsTo(), getTotalAccounts(), getNoOfActivities(), getWorkedAccounts(), fromId);
                // System.out.println("in action-->"+result);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(result);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;

    }

    public String sendPriorityEmail() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                String fromId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                String result = ServiceLocator.getAjaxHandlerService().sendPriorityEmail(getLoginId(), getReportsTo(), fromId, getAccountName(), getLastActivityDate(), httpServletRequest);
                // System.out.println("in action-->"+result);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(result);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;

    }

    public String transferAccounts() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                String fromId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                String result = "";
                // System.out.println("in action-->"+result);
                AccountService accountService = ServiceLocator.getAccountService();
                int count = accountService.updateAssignAccounts(getFrmLoginId(), getToLoginId());
                if (count > 0) {

                    result = "Accounts Transfered Successfully !";
                } else {
                    resultType = INPUT;
                    result = "Sorry! Please Try Again !";
                }
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(result);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return null;

    }
    /* New Method for acoounts summary by priority */

  /*  public String getAccountsByPriority() throws ServiceLocatorException {
       
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            int isManager = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_USER_MANAGER).toString());
            int isTeamLead = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_TEAM_LEAD).toString());
            DataSourceDataProvider dataSourceDataProvider = null;

            String teamId = dataSourceDataProvider.getInstance().getTeamNameByUserId(getTeamMemberId());
            //String teamId=getTeamMemberId();
            if (AuthorizationManager.getInstance().isAuthorizedUser("DASHBOARD_ACCESS", userRoleId)) {
                try {
                   

                    if (teamId.equals("") || teamId.equals("B2B") || teamId.equals("BPM") || teamId.equals("E-Commerce") || teamId.equals("SAP") || teamId.equals("QA")) {
                        responseString = ServiceLocator.getAjaxHandlerService().getAccountsByPriority(getTeamMemberId(), getTeamName(), httpServletRequest);

                    } else {
                        responseString = "others";
                    }
                    httpServletResponse.setContentType("text");
                    httpServletResponse.getWriter().write(responseString);
                } catch (Exception ex) {
                }
            }
        }//Close Session Checking
        return null;
    }*/

    //new action for to get name for aaccount search by priority
    public String getTeamName1() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

                responseString = ServiceLocator.getAjaxHandlerService().getTeamName(getLoginId(), httpServletRequest);
                httpServletResponse.setContentType("text");

                //System.out.println("responseString----test------------------>"+responseString);
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    //new action to populate exam name
    public String popupWindowForExamName() {
        String str;
        /*
         *This if loop is to check whether there is Session or not
         */
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                //System.out.println("Exam Name----->"+activityId);
                str = ServiceLocator.getAjaxHandlerService().popupExamName(activityId);
                httpServletResponse.setContentType("text/html");
                httpServletResponse.getWriter().write((str));
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }//Close Session Checking

        return null;
    }
    // Added by Aditya

    public String getExamTypeName() {
//    System.out.println("Into action");
        String result = "";
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {

                result = ServiceLocator.getAjaxHandlerService().getExamTypeName(getExamType());
//       System.out.println("in action-->"+result);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(result);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;

    }

    public String getCreDetailExamDetails() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                // String fromId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                String result = ServiceLocator.getAjaxHandlerService().getCreDetailExamDetails();
                // System.out.println("in action-->"+result);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(result);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;

    }

    public String getEmployeeDetailsForProject() {
        // System.out.println("in action-->");
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                // String fromId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                String result = ServiceLocator.getAjaxHandlerService().getEmployeeDetailsForProject(getCustomerName(), getEmpType(), getProjectId(), getAccountId());
                // System.out.println("in action-->"+result);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(result);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;

    }

    //new action for project dashboard
    public String getProjectTeam() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                /*
                 *This if loop is to check whether there is Session or not
                 **/
                if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

                    responseString = ServiceLocator.getAjaxHandlerService().getProjectTeamDetails(getProjectId(), httpServletRequest);
                    httpServletResponse.setContentType("text");

                    //System.out.println("responseString---------------------->"+responseString);
                    httpServletResponse.getWriter().write(responseString);
                }//Close Session Checking
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;


    }

    // For Customer tasks
    /*public String getProjectsForAccountId() {
    try {
    if((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) ||(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)){
    String resourceType = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
    responseString = ServiceLocator.getAjaxHandlerService().getProjectsForAccountId(getAccountId(),resourceType);
    //     System.out.println("responseString-->"+responseString);
    httpServletResponse.setContentType("text/xml");
    httpServletResponse.getWriter().write(responseString);
    }//Close Session Checking
    } catch (ServiceLocatorException ex) {
    ex.printStackTrace();
    }catch (IOException ex) {
    ex.printStackTrace();
    }
    return null;
    }*/
    public String getProjectsForAccountId() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) || (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)) {
                String resourceType = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
                String empId = "";
                if (resourceType.equalsIgnoreCase("c") || resourceType.equalsIgnoreCase("v")) {
                    empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString();
                } else {
                    empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
                }
                responseString = ServiceLocator.getAjaxHandlerService().getProjectsForAccountId(getAccountId(), resourceType, empId);
                //     System.out.println("responseString-->"+responseString);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    //new
    public String getEmployeeDetailsByProjectId() {
        try {
            //  System.out.println("in ajax method");
            /*
             *This if loop is to check whether there is Session or not
             **/
            if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) || (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)) {
                String resourceType = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
                //    System.out.println("in ajax method--->"+getProjectId());
                boolean isExists = DataSourceDataProvider.getInstance().checkProjectContactsByProjectId(getProjectId());
                //     System.out.println("in ajax method--->"+isExists);

                if (isExists) {
                    responseString = ServiceLocator.getAjaxHandlerService().getEmployeeDetails("SELECT ResourceName ,ObjectId FROM tblProjectContacts WHERE ResourceName like '%" + getCustomerName() + "%' AND ProjectId in (" + getProjectId() + ")  LIMIT 30");
                } else {
                    responseString = ServiceLocator.getAjaxHandlerService().getEmployeeDetails("SELECT concat(trim(FName),'.',trim(LName)) AS FullName,LoginId FROM tblEmployee WHERE (LName LIKE '" + getCustomerName() + "%' OR FName LIKE '" + getCustomerName() + "%') and CurStatus='Active' LIMIT 30");
                }

                //   System.out.println("responseString-->"+responseString);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String getEmployeePhoneNumber() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                // String fromId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                String result = ServiceLocator.getAjaxHandlerService().getEmployeePhoneNumber(getCustomerName(), getEmpType(), getProjectId(), getAccountId());
                // System.out.println("in action-->"+result);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(result);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;

    }

    /*Methods For Project based Timesheet search changes
     * Date : 07/31/2014
     * 
     */
    public String getProjectsByAccountId() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) || (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)) {

                // responseString = ServiceLocator.getAjaxHandlerService().getProjectsForAccountId(getAccountId(),resourceType,empId);
                responseString = ServiceLocator.getAjaxHandlerService().getProjectsByAccountId(getAccountId());
                //     System.out.println("responseString-->"+responseString);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String getEmployeesByProjectId() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) || (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)) {
                String workingFor = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
                // String livingCountry =    httpServletRequest.getSession(false).getAttribute(ApplicationConstants.Living_COUNTRY).toString();
                // responseString = ServiceLocator.getAjaxHandlerService().getProjectsForAccountId(getAccountId(),resourceType,empId);
               // responseString = ServiceLocator.getAjaxHandlerService().getEmployeesByProjectId(getProjectId(), workingCountry);
                  responseString = ServiceLocator.getAjaxHandlerService().getEmployeesByProjectId(getProjectId(), workingFor);
                //     System.out.println("responseString-->"+responseString);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String getEmployeesByAccountId() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) || (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)) {
                String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
                // responseString = ServiceLocator.getAjaxHandlerService().getProjectsForAccountId(getAccountId(),resourceType,empId);
                responseString = ServiceLocator.getAjaxHandlerService().getEmployeesByAccountId(getAccountId(), workingCountry);
                //     System.out.println("responseString-->"+responseString);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    //new

    public String getEmployeeDetailsForPMO() {
        try {
            //select concat(FName,'.',LName) as Name from tblEmployee
            responseString = ServiceLocator.getAjaxHandlerService().getEmployeeDetailsForPMO("SELECT concat(trim(FName),'.',trim(LName)) AS FullName,LoginId FROM tblEmployee WHERE (LName LIKE '" + getCustomerName() + "%' OR FName LIKE '" + getCustomerName() + "%') and CurStatus='Active' LIMIT 50");
            httpServletResponse.setContentType("text/xml");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    /*
     * Method For Populating Onboard Comments
     * Date : 08/15/2014
     * Author : Santosh Kola
     */
    public String popupOnboardComments() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                String result = ServiceLocator.getAjaxHandlerService().popupOnboardComments(getOnboardReviewId());
                // System.out.println("in action-->"+result);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(result);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;

    }

    public String getTechEmployeeDetails() {
        try {

            //
            responseString = ServiceLocator.getAjaxHandlerService().getTechEmployeeDetails(httpServletRequest, getTechName());
            httpServletResponse.setContentType("text/xml");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    public String sendTechMail() {
        try {

            //
            //responseString = ServiceLocator.getAjaxHandlerService().sendTechMail();
            responseString = "Sorry! network is down";
            StringTokenizer st = DataSourceDataProvider.getInstance().getReferredLoginIdsForGroups(getReferTo());
            if (com.mss.mirage.util.Properties.getProperty("Mail.Flag").equals("1")) {
                while (st.hasMoreTokens()) {
                    String email = st.nextToken() + "@miraclesoft.com";
                    // System.out.println("email0-->"+email);
                    int reviewId = DataSourceDataProvider.getInstance().getReviewIdbyEmail(email, getConsultantId());
                    //  MailManager.sendConsultantDetailsForTechReviews(getId(), getConsultantId(), email, getTechName());
                }
            }
            responseString = "Successfully sent!";
            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (IOException ex) {
            System.err.println(ex);
        } catch (Exception ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    /*
     * For Teamlead Timesheet Report
     * DAte : 09/02/2014
     * Author : Santosh Kola
     */
    public String getTeamByProjectId() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) || (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)) {
                String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
                String empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
                String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                // responseString = ServiceLocator.getAjaxHandlerService().getProjectsForAccountId(getAccountId(),resourceType,empId);
                responseString = ServiceLocator.getAjaxHandlerService().getTeamByProjectId(getProjectId(), empId, loginId, httpServletRequest);
                //     System.out.println("responseString-->"+responseString);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    //new method rec dashboard
    public String consultantActivitiesByRep() {

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            // System.out.println("COUNT IS  "+curWorkCountry);
            if (AuthorizationManager.getInstance().isAuthorizedUser("DASHBOARD_ACCESS", userRoleId)) {//&& ((isManager ==1) || (isTeamLead ==1)) ){
                try {
                    String activityType = httpServletRequest.getParameter("activityType");
                    String recruiterName = httpServletRequest.getParameter("createdBy");
                    //System.out.println("activityType--->"+activityType);
                    //System.out.println("recruiterName--->"+recruiterName);
                    responseString = ServiceLocator.getAjaxHandlerService().consultantActivitiesByRep(getDashBoardStartDateRep(), getDashBoardEndDateRep(), activityType, recruiterName);
                    httpServletResponse.setContentType("text");
                    httpServletResponse.getWriter().write(responseString);
                } catch (Exception ex) {
                }
            }
        }//Close Session Checking
        return null;
    }

    public String popupRecDashBoardComments() {
        String str;
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                str = getComments();
                if (str.equals("") || str.equals("null")) {
                    str = "No Comments Given";
                }

                // System.out.println("strr----------->"+str);
                httpServletResponse.setContentType("text/html");
                if (str != null) {
                    httpServletResponse.getWriter().write((str));
                } else {
                    httpServletResponse.getWriter().write(("No Record Present"));
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }//Close Session Checking
        return null;
    }

    public String getTaskEmpDetailsBasedOnIssueRel() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null || httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID) != null) {
                //    System.out.println("hr-->"+getIssueRel());
                responseString = ServiceLocator.getAjaxHandlerService().getTaskEmpDetailsBasedOnIssueRel(getIssueRel());
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String getTaskEmpDetailsBasedOnPrjIssue() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null || httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID) != null) {
                //    System.out.println("proj-->"+getIssueRel());
                responseString = ServiceLocator.getAjaxHandlerService().getTaskEmpDetailsBasedOnPrjIssue(getProjectId());
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String getTaskEmpDetailsBasedOnHubbleNetworkInfraIssue() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null || httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID) != null) {
                //   System.out.println("getIssueRel-->"+getIssueRel());
                String issueType = "";
                if (getIssueRel().equalsIgnoreCase("network")) {
                    issueType = "network";
                }
                if (getIssueRel().equalsIgnoreCase("hubble")) {
                    issueType = "hubble";
                }
                if (getIssueRel().equalsIgnoreCase("infra")) {
                    issueType = "infra";
                }
                responseString = ServiceLocator.getAjaxHandlerService().getTaskEmpDetailsBasedOnHubbleNetworkInfraIssue(issueType);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String getAllEmpNames() {
        try {

            //
            responseString = ServiceLocator.getAjaxHandlerService().getAllEmpNames(httpServletRequest, getTechName());
            httpServletResponse.setContentType("text/xml");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    /*New methods for employee associated projects
     * Date : 09/26/2014
     * Author : Santosh Kola
     */
    public String getEmpAssociatedProjectsList() {

        if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) || (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)) {

            try {
                if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString().equalsIgnoreCase("e")) {
                    responseString = ServiceLocator.getAjaxHandlerService().getEmpAssociatedProjectsList(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
                } else {
                    responseString = ServiceLocator.getAjaxHandlerService().getEmpAssociatedProjectsList(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString());
                }

                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            //}
        }//Close Session Checking
        return null;
    }

    public String checkPrimary() {

        if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) || (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)) {

            try {

                responseString = ServiceLocator.getAjaxHandlerService().checkPrimary(getProjectId(), getContactId());

                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            //}
        }//Close Session Checking
        return null;
    }

    public String getReportsToAccess() {

        if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) || (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)) {

            try {
                String empType = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
                if (empType.equalsIgnoreCase("e")) {
                    String currentEmpId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
                    String currentLoginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                    responseString = ServiceLocator.getAjaxHandlerService().getReportsToAccess(getEmpId(), currentEmpId, currentLoginId, getResourceType());
                } else {
                    String currentCustomerId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString();
                    String currentCustomerLoginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID).toString();
                    responseString = ServiceLocator.getAjaxHandlerService().getReportsToAccess(getEmpId(), currentCustomerId, currentCustomerLoginId, getResourceType());
                }

                //public String getReportsToAccess(String empId,String reportsToId,String reportsToLoginId) throws ServiceLocatorException


                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            //}
        }//Close Session Checking
        return null;
    }

    // Start For New Recruitment Dashbord
    public String getTotalProfilesByPractice() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                responseString = ServiceLocator.getAjaxHandlerService().getTotalProfilesByPractice(getDashBoardStartDate(), getDashBoardEndDate());
                httpServletResponse.setContentType("text");
                //System.out.println("responseString---------------------->"+responseString);
                httpServletResponse.getWriter().write(responseString);

            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (Exception ex) {
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
            }
        }
        return null;

    }

    public String getReqProfileSubmittedInfo() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {

                responseString = ServiceLocator.getAjaxHandlerService().getReqProfileSubmittedInfo(getPracticeName(), getDashBoardStartDateRep(), getDashBoardEndDateRep());
                httpServletResponse.setContentType("text");
                //System.out.println("responseString---------------------->"+responseString);
                httpServletResponse.getWriter().write(responseString);

            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;

    }

    public String getReqStatusInfo() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                responseString = ServiceLocator.getAjaxHandlerService().getReqStatusInfo(getDashBoardStartDateRep(), getDashBoardEndDateRep());
                httpServletResponse.setContentType("text");
//               System.out.println("responseString---------------------->"+responseString);
                httpServletResponse.getWriter().write(responseString);

            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (Exception ex) {
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                //resultType =  ERROR;
            }
        }
        return null;


    }

    public String getReqClosedInfo() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                responseString = ServiceLocator.getAjaxHandlerService().getReqClosedInfo(getDashBoardStartDateRep(), getDashBoardEndDateRep());
                httpServletResponse.setContentType("text");
                //System.out.println("responseString---------------------->"+responseString);
                httpServletResponse.getWriter().write(responseString);

            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;


    }

    // End For New Recruitment Dashbord 
    // Start For activeManagerDetailsByDates
    public String activeManagerDetailsByDates() {

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            // System.out.println("COUNT IS  "+curWorkCountry);
            if (AuthorizationManager.getInstance().isAuthorizedUser("DASHBOARD_ACCESS", userRoleId)) {//&& ((isManager ==1) || (isTeamLead ==1)) ){
                try {
                    responseString = ServiceLocator.getAjaxHandlerService().activeManagerDetailsByDates(getDashBoardStartDateRep(), getDashBoardEndDateRep());
                    httpServletResponse.setContentType("text");
                    httpServletResponse.getWriter().write(responseString);
                } catch (Exception ex) {
                }
            }
        }//Close Session Checking
        return null;
    }

// End For activeManagerDetailsByDates
    public String marketingAccountAssignment() {

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            // System.out.println("COUNT IS  "+curWorkCountry);
            if (AuthorizationManager.getInstance().isAuthorizedUser("DASHBOARD_ACCESS", userRoleId)) {//&& ((isManager ==1) || (isTeamLead ==1)) ){
                try {
                    boolean check = DataSourceDataProvider.getInstance().checkEmployeeLoginIdExists(getLoginId());
                    if (check) {
                        responseString = ServiceLocator.getAjaxHandlerService().assignMarketingAcccounts(getState(), getLoginId(), httpServletRequest);
                    } else {
                        responseString = "Exists";
                    }
                    httpServletResponse.setContentType("text");
                    httpServletResponse.getWriter().write(responseString);
                } catch (Exception ex) {
                }
            }
        }//Close Session Checking
        return null;
    }

    public String checkEmail() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                responseString = ServiceLocator.getAjaxHandlerService().checkEmail(getEmail());
                // System.out.println(responseString);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }//Close Session Checking
        return null;
    }

    public String getAllEmpNamesAlongWithTitle() {
        try {

            //
            responseString = ServiceLocator.getAjaxHandlerService().getAllEmpNamesAlongWithTitle(httpServletRequest, getTechName());
            httpServletResponse.setContentType("text/xml");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    public String getMetricsList() {

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            if (AuthorizationManager.getInstance().isAuthorizedUser("DASHBOARD_ACCESS", userRoleId)) {//&& ((isManager ==1) || (isTeamLead ==1)) ){
                try {
                    responseString = ServiceLocator.getAjaxHandlerService().getMetricsList(getMetricName(), getStatusId(), httpServletRequest);
                    httpServletResponse.setContentType("text");
                    httpServletResponse.getWriter().write(responseString);
                } catch (Exception ex) {
                }
            }
        }//Close Session Checking
        return null;
    }

    public String getMetricComments() {
        String str;
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                str = getComments();
                if (str.equals("") || str.equals("null")) {
                    str = "No Comments Given";
                }

                //   System.out.println("strr----------->"+str);
                httpServletResponse.setContentType("text/html");
                if (str != null) {
                    httpServletResponse.getWriter().write((str));
                } else {
                    httpServletResponse.getWriter().write(("No Record Present"));
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }//Close Session Checking
        return null;
    }

    public String getAllMetricNames() {
        try {

            //
            responseString = ServiceLocator.getAjaxHandlerService().getAllMetricNames(httpServletRequest, getMetricName());
            httpServletResponse.setContentType("text/xml");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    public String getTitlesList() {

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            if (AuthorizationManager.getInstance().isAuthorizedUser("DASHBOARD_ACCESS", userRoleId)) {//&& ((isManager ==1) || (isTeamLead ==1)) ){
                try {
                    responseString = ServiceLocator.getAjaxHandlerService().getTitlesList(getMetricId(), getStatusId(), getDepartmentId(), getTitle(), httpServletRequest);
                    httpServletResponse.setContentType("text");
                    httpServletResponse.getWriter().write(responseString);
                } catch (Exception ex) {
                }
            }
        }//Close Session Checking
        return null;
    }

    public String getAllMetricNames1() {
        try {

            //
            responseString = ServiceLocator.getAjaxHandlerService().getAllMetricNames1(httpServletRequest, getMetricName(), getDepartmentId(), getTitle());
            httpServletResponse.setContentType("text/xml");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    public String setMetricRange() {
        try {
            // System.out.println("in ajax action------>"+getDepartmentId()+"--------"+getTitle());
            //
            responseString = ServiceLocator.getAjaxHandlerService().setMetricRange(httpServletRequest, getDepartmentId(), getTitle());
            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    public String getAllReviewedPerformances() {

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            if (AuthorizationManager.getInstance().isAuthorizedUser("DASHBOARD_ACCESS", userRoleId)) {//&& ((isManager ==1) || (isTeamLead ==1)) ){
                try {
                    responseString = ServiceLocator.getAjaxHandlerService().getAllReviewedPerformances(getLoginId(), getStartDate(), getEndDate(), getTitle(), getDepartmentId(), httpServletRequest);
                    httpServletResponse.setContentType("text");
                    httpServletResponse.getWriter().write(responseString);
                } catch (Exception ex) {
                }
            }
        }//Close Session Checking
        return null;
    }

    public String setMetricRange1() {
        try {
            //  System.out.println("in ajax action------>"+getDepartmentId()+"--------"+getTitle()+"-----------"+getId());
            //
            responseString = ServiceLocator.getAjaxHandlerService().setMetricRange1(httpServletRequest, getDepartmentId(), getTitle(), getPerfId());
            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    public String editPerformanceValues() {
        try {
            // System.out.println("in ajax action------>"+getPerfId());
            //
            responseString = ServiceLocator.getAjaxHandlerService().editPerformanceValues(httpServletRequest, getPerfId());
            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    public String updatePerformanceValues() throws JSONException {
        //     System.out.println("in ajax action------>");
        try {
            // System.out.println("in ajax action------>");
            // System.out.println("in ajax action------>"+getStringdata());
            JSONObject jsonObject = new JSONObject(getStringdata());
            JSONObject myResponse = jsonObject.getJSONObject("MyResponse");
            JSONArray tsmresponse = (JSONArray) myResponse.get("listTsm");
            String comments = (String) myResponse.get("comments");
            int perfId = (Integer) myResponse.get("perfId");
            String rating = "";
            String resultantWeightage = "";
            int metricId = 0;
            int perfLineId = 0;
            // ArrayList list = new ArrayList();
            //    System.out.println("response comments --> "+comments);
            //System.out.println("response Length --> "+tsmresponse.length());
            for (int i = 0; i < tsmresponse.length(); i++) {
                rating = tsmresponse.getJSONObject(i).getString("rating");
                resultantWeightage = tsmresponse.getJSONObject(i).getString("resultantWeightage");
                metricId = tsmresponse.getJSONObject(i).getInt("metricId");
                perfLineId = tsmresponse.getJSONObject(i).getInt("perfLineId");
                //System.out.println(rating+"-------"+resultantWeightage+"--------"+metricId+"------"+perfLineId);
                responseString = ServiceLocator.getAjaxHandlerService().updatePerformanceValues(httpServletRequest, rating, resultantWeightage, metricId, perfLineId, comments, perfId);
            }

            // System.out.println("list-->"+list);
            if (!"".equals(responseString)) {
                responseString = ServiceLocator.getAjaxHandlerService().editPerformanceValues(httpServletRequest, getPerfId());
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write("Performance Updated Successfully");
            }
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    public String getAllEmpNamesAlongWithTitle1() {
        try {

            //
            responseString = ServiceLocator.getAjaxHandlerService().getAllEmpNamesAlongWithTitle1(httpServletRequest, getTechName());
            httpServletResponse.setContentType("text/xml");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    public String editTitleValues() {
        try {
            //  System.out.println("in ajax action------>"+getTitleId());
            //
            responseString = ServiceLocator.getAjaxHandlerService().editTitleValues(httpServletRequest, getTitleId());
            // System.out.println(responseString);
            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;

    }

    public String addTitleValues() {
        try {
            //System.out.println("in ajax action------>"+getTitleId());
            //
            boolean check = DataSourceDataProvider.getInstance().checkForTitlesForDeptAndRole(getMetricId(), getDepartmentName(), getTitle());
            if (!check) {
                responseString = ServiceLocator.getAjaxHandlerService().addTitleValues(httpServletRequest, this);
                //System.out.println(responseString);
                if (!"".equals(responseString)) {
                    httpServletResponse.setContentType("text");
                    httpServletResponse.getWriter().write("Title Added Successfully");
                }
            } else {
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write("Title has been added already for this department and title");
            }

        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;


    }

    public String updateTitleValues() {
        try {
            //    System.out.println("in ajax action------>"+getTitleId());
            //

            responseString = ServiceLocator.getAjaxHandlerService().updateTitlleValues(httpServletRequest, this);
            //  System.out.println(responseString);
            if (!"".equals(responseString)) {
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write("Title Updated Successfully");
            }

        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;


    }

    public String editMetricValues() {
        try {
            //System.out.println("in ajax action------>"+getMetricId());
            //
            responseString = ServiceLocator.getAjaxHandlerService().editMetricValues(httpServletRequest, getMetricId());

            //  System.out.println(responseString);
            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;

    }

    public String updateMetricValues() {
        try {
            //  System.out.println("in ajax action------>"+getMetricId());
            //
            responseString = ServiceLocator.getAjaxHandlerService().updateMetricValues(httpServletRequest, this);
            //  System.out.println(responseString);
            if (!"".equals(responseString)) {
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write("Metrics Updated Successfully");
            }
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;

    }

    public String addMetricValues() {
        try {
            //System.out.println("in ajax action------>"+getTitleId());
            //
            boolean check = DataSourceDataProvider.getInstance().checkForMetricName(metricName);
            if (!check) {
                responseString = ServiceLocator.getAjaxHandlerService().addMetricValues(httpServletRequest, this);
                //   System.out.println(responseString);
                if (!"".equals(responseString)) {
                    httpServletResponse.setContentType("text");
                    httpServletResponse.getWriter().write("Metrics Added Successfully");
                }

            } else {
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write("Metrics already exists");
            }
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;


    }

    /*
     * Author : Santosh Kola
     * Date : 11/15/2014
     * Displaying Activity details by loginId
     */
    public String getActivityDetailsByLoginId() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                responseString = ServiceLocator.getAjaxHandlerService().getActivityDetailsByLoginId(getLoginId(), getActivityStaDate(), getActivityEndDate());

                // System.out.println(responseString);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }//Close Session Checking
        return null;
    }

// Dual change
    public String isDualReportsTo() {
        if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) || (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)) {
            try {

                responseString = ServiceLocator.getAjaxHandlerService().isDualReportsTo(getProjectId());
                httpServletResponse.setContentType("text");
//               System.out.println("responseString---------------------->"+responseString);
                httpServletResponse.getWriter().write(responseString);

            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;


    }

    // Dual reports To Changes
    public String getTeamByReportsToType() {

        if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) || (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)) {

            try {
                String empType = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
                if (empType.equalsIgnoreCase("e")) {
                    responseString = ServiceLocator.getAjaxHandlerService().getTeamByReportsToType(getTeamType(), httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString(), httpServletRequest);
                } else {
                    responseString = ServiceLocator.getAjaxHandlerService().getTeamByReportsToType(getTeamType(), httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString(), httpServletRequest);
                }
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            //}
        }//Close Session Checking
        return null;
    }

    // Review changes
    public String addReview() {

        try {


            //  setCurrentTask(tasksVTO);
            // String  generatedPath = null;
            if (getFileFileName() != null) {
                attachmentService = ServiceLocator.getAttachmentService();
                generatedPath = attachmentService.generatePath(com.mss.mirage.util.Properties.getProperty("Attachments.Path"), "Emp Reviews");
                File targetDirectory = new File(generatedPath + File.separator + getFileFileName());
                setAttachmentLocation(targetDirectory.toString());
                FileUtils.copyFile(getFile(), targetDirectory);
                //  setObjectType("Emp Reviews");
            } else {
                objectType = "NoFile";
                setAttachmentLocation("");
                //setFilepath("");
                //attachmentName = "";
            }

            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
            setRoleName(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString());
            setManager(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_USER_MANAGER).toString()));
            setManagerCountry(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.Living_COUNTRY).toString());
            if ("Team".equalsIgnoreCase(getAddType())) {
                setUserId(getUserId());
                setEmpTitle(DataSourceDataProvider.getInstance().getEmpTitleByLoginId(getUserId()));

                //setEmpTitle(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_TITLE).toString());

            } else {
                setEmpTitle(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_TITLE).toString());
                setUserId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
            }

            //setEmpId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());

            // System.out.println("filename"+getFileFileName());
            // System.out.println("file location"+getAttachmentLocation());
            // System.out.println("filename"+getUploadFileName());
                         /* if( !ServiceLocator.getReviewsService().getInsertReview(this)){
            setResultMessage("<font color=green size=2px>Review added successfully.</font>");
            }else {
            setResultMessage("<font color=red size=2px>Please try again.</font>");
            }*/
            if (!ServiceLocator.getAjaxHandlerService().addReview(this)) {
                responseString = "uploaded";
            } else {
                responseString = "Error";
            }
            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    public String updateReview() {

        try {

            setModifiedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
            setEmpId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
            setEmpTitle(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_TITLE).toString());
            String roleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            setManager(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_USER_MANAGER).toString()));
            setManagerCountry(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.Living_COUNTRY).toString());
            if (!ServiceLocator.getAjaxHandlerService().updateReview(this, roleName)) {
                responseString = "updated";
            } else {
                responseString = "Error";
            }
            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    public String getApprovedByDetails() {

        try {

            setModifiedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
            setEmpId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
            setEmpTitle(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_TITLE).toString());
            responseString = ServiceLocator.getAjaxHandlerService().getApprovedByDetails(this);


            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    public String getBasePoints() {

        try {


            responseString = ServiceLocator.getAjaxHandlerService().getBasePoints(getReviewTypeId());

            //System.out.println("responseString-->"+responseString);
            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    public String updateMyReview() {

        try {

            //  setModifiedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
            //  setEmpId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
            //  setEmpTitle(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_TITLE).toString());
            // String roleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            if (!ServiceLocator.getAjaxHandlerService().updateMyReview(this)) {
                responseString = "updated";
            } else {
                responseString = "Error";
            }
            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    public String consultantActivitiesAsGraph() {
        //System.out.println("recruiterNamexxwdwds--->");
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            // System.out.println("COUNT IS  "+curWorkCountry);
            if (AuthorizationManager.getInstance().isAuthorizedUser("DASHBOARD_ACCESS", userRoleId)) {//&& ((isManager ==1) || (isTeamLead ==1)) ){
                try {

                    String activityType = httpServletRequest.getParameter("activityType");
                    String recruiterName = httpServletRequest.getParameter("createdBy");
                    //responseString = ServiceLocator.getAjaxHandlerService().consultantActivitiesAsGraph(getStartDateSummaryGraph(),getEndDateSummaryGraph(),activityType,recruiterName);
                    responseString = ServiceLocator.getAjaxHandlerService().consultantActivitiesAsGraph(getStartDateSummaryGraph(), getEndDateSummaryGraph(), activityType, recruiterName, httpServletRequest);
                    httpServletResponse.setContentType("text");
                    httpServletResponse.getWriter().write(responseString);
                } catch (Exception ex) {
                }
            }
        }//Close Session Checking
        return null;
    }

    public String consultantActivitiesAsGraphInd() {
        // System.out.println("recruiterName IN INdividual--->");
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            // System.out.println("COUNT IS  "+curWorkCountry);
            if (AuthorizationManager.getInstance().isAuthorizedUser("DASHBOARD_ACCESS", userRoleId)) {//&& ((isManager ==1) || (isTeamLead ==1)) ){
                try {
                    String activityType = httpServletRequest.getParameter("activityType");
                    String recruiterName = httpServletRequest.getParameter("createdBy");
                    responseString = ServiceLocator.getAjaxHandlerService().consultantActivitiesAsGraphInd(getStartDateSummaryGraph(), getEndDateSummaryGraph(), activityType, recruiterName);

                    httpServletResponse.setContentType("text");
                    httpServletResponse.getWriter().write(responseString);
                } catch (Exception ex) {
                }
            }
        }//Close Session Checking
        return null;
    }
    //new methods recruitment account merege

    public String getMergeConsultants() {
        try {

            // System.out.println("duplicate-->"+getDuplicate());
            //System.out.println("original-->"+getOriginal());
            responseString = ServiceLocator.getAjaxHandlerService().getMergeConsultants(getOriginal(), getDuplicate());
            httpServletResponse.setContentType("text/xml");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////

    public String ConsultantAnalysis() {
        try {


            //System.out.println("This is consultant Analysis method");

            // System.out.println("original-->"+getOriginal());


            responseString = ServiceLocator.getAjaxHandlerService().ConsultantAnalysis(getOriginal(), getDuplicate());


            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    public String getIssueTypeBasedOnIssueRel() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null || httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID) != null) {
                //    System.out.println("hr-->"+getIssueRel());
                responseString = ServiceLocator.getAjaxHandlerService().getIssueTypeBasedOnIssueRel(getIssueRel());
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String getTaskEmpDetailsBasedOnIssueRel1() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null || httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID) != null) {
                //    System.out.println("hr-->"+getIssueRel());
                responseString = ServiceLocator.getAjaxHandlerService().getTaskEmpDetailsBasedOnIssueRel(getIssueRel());
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    //new actions for sales activity grapoh
    public String salesActivitiesAsGraph() {

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            if (AuthorizationManager.getInstance().isAuthorizedUser("DASHBOARD_ACCESS", userRoleId)) {//&& ((isManager ==1) || (isTeamLead ==1)) ){
                try {

                    String activityType = httpServletRequest.getParameter("activityType");
                    String recruiterName = httpServletRequest.getParameter("createdBy");



                   // responseString = ServiceLocator.getAjaxHandlerService().salesActivitiesAsGraph(getStartDateSummaryGraph(), getEndDateSummaryGraph(), activityType, recruiterName, httpServletRequest);
                    responseString = ServiceLocator.getAjaxHandlerService().salesActivitiesAsGraph(getStartDateSummaryGraph(), getEndDateSummaryGraph(), activityType, recruiterName, httpServletRequest,getCampaignId());

                    httpServletResponse.setContentType("text");
                    httpServletResponse.getWriter().write(responseString);
                } catch (Exception ex) {
                }
            }
        }//Close Session Checking
        return null;
    }

    /*public String salesActivitiesAsGraphInd() {

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            if (AuthorizationManager.getInstance().isAuthorizedUser("DASHBOARD_ACCESS", userRoleId)) {//&& ((isManager ==1) || (isTeamLead ==1)) ){
                try {

                    String activityType = httpServletRequest.getParameter("activityType");
                    String recruiterName = httpServletRequest.getParameter("createdBy");



                  //  responseString = ServiceLocator.getAjaxHandlerService().salesActivitiesAsGraphInd(getStartDateSummaryGraph(), getEndDateSummaryGraph(), activityType, recruiterName,getTeamMemberCheck());
                    //responseString = ServiceLocator.getAjaxHandlerService().salesActivitiesAsGraphInd(getStartDateSummaryGraph(), getEndDateSummaryGraph(), activityType, recruiterName,getTeamMemberCheck(),getTitleType());
                    responseString = ServiceLocator.getAjaxHandlerService().salesActivitiesAsGraphInd(getStartDateSummaryGraph(), getEndDateSummaryGraph(), activityType, recruiterName,getTeamMemberCheck(),getTitleType(),getCampaignId());
                    httpServletResponse.setContentType("text");
                    httpServletResponse.getWriter().write(responseString);
                } catch (Exception ex) {
                }
            }
        }//Close Session Checking
        return null;
    }*/
 public String salesActivitiesAsGraphInd() {

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            if (AuthorizationManager.getInstance().isAuthorizedUser("DASHBOARD_ACCESS", userRoleId)) {//&& ((isManager ==1) || (isTeamLead ==1)) ){
                try {

                    String activityType = httpServletRequest.getParameter("activityType");
                    String recruiterName = httpServletRequest.getParameter("createdBy");

                   // System.out.println("in salesActivitiesAsGraphInd(_)");

                  //  responseString = ServiceLocator.getAjaxHandlerService().salesActivitiesAsGraphInd(getStartDateSummaryGraph(), getEndDateSummaryGraph(), activityType, recruiterName,getTeamMemberCheck());
                    //responseString = ServiceLocator.getAjaxHandlerService().salesActivitiesAsGraphInd(getStartDateSummaryGraph(), getEndDateSummaryGraph(), activityType, recruiterName,getTeamMemberCheck(),getTitleType());
                    responseString = ServiceLocator.getAjaxHandlerService().salesActivitiesAsGraphInd(getStartDateSummaryGraph(), getEndDateSummaryGraph(), activityType, recruiterName,getTeamMemberCheck(),getTitleType(),getCampaignId(),getSessionTitleType(),httpServletRequest);
                    httpServletResponse.setContentType("text");
                    httpServletResponse.getWriter().write(responseString);
                } catch (Exception ex) {
                }
            }
        }//Close Session Checking
        return null;
    }

    public String getReviewDetails() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {


            try {


                responseString = ServiceLocator.getAjaxHandlerService().getReviewDetails(getReviewId());
                //     System.out.println("responseString---->"+responseString);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
            }

        }//Close Session Checking
        return null;
    }

    public String addJobposting() throws Exception {
        try {

            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
            responseString = ServiceLocator.getAjaxHandlerWebService().jobPosting(this);
            //    System.out.println("responseString-->"+responseString);

            //responseString = "updated";
            //}else {
            //responseString ="Error";
            //   }
            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    public String editJobposting() throws Exception {
        try {

            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
            responseString = ServiceLocator.getAjaxHandlerWebService().editPosting(getJobId());
            //  System.out.println("responseString-->"+responseString);

            //responseString = "updated";
            //}else {
            //responseString ="Error";
            //   }
            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    public String updateJobposting() throws Exception {
        try {
            // System.out.println("techincal action-->"+getJobtechnical());
            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
            responseString = ServiceLocator.getAjaxHandlerWebService().upadteJobPosting(this);
            // System.out.println("responseString-->"+responseString);

            //responseString = "updated";
            //}else {
            //responseString ="Error";
            //   }
            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    public String getApplicantDetails() throws Exception {
        try {

            //setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
            responseString = ServiceLocator.getAjaxHandlerWebService().getApplicantDetails(getApplicantId());

            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    /*Method for Review list
     * Date : 03/26/2015
     * Author : Santosh Kola
     */
    public String getEmpTotalReviews() throws Exception {
        try {
            // String name = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME).toString();
            //setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
            responseString = ServiceLocator.getAjaxHandlerService().getEmpTotalReviews(getUserId(), getReviewTypeId());
          //  System.out.println("responseString-->" + responseString);
            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    public String getEmpReviewComments() throws Exception {
        try {
            // String name = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME).toString();
            //setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
            responseString = ServiceLocator.getAjaxHandlerService().getEmpReviewComments(getReviewId());

            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    public String getTlReviewComments() throws Exception {
        try {
            // String name = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME).toString();
            //setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
            responseString = ServiceLocator.getAjaxHandlerService().getTlReviewComments(getReviewId());

            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    public String getHrReviewComments() throws Exception {
        try {
            // String name = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME).toString();
            //setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
            responseString = ServiceLocator.getAjaxHandlerService().getHrReviewComments(getReviewId());

            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    public String getEmployeesByDept() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                responseString = ServiceLocator.getAjaxHandlerService().getEmployeesByDept(getDeptName());
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String getEmployeeSkillSet() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                responseString = ServiceLocator.getAjaxHandlerService().getEmployeeSkillSet(getEmpId(), getCurrId());
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /*
     * Requirement Lists
     * Date : 05/22/2015
     * Author : Santosh Kola
     * 
     */
    public String getRequirementAdminAjaxList() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {

                responseString = ServiceLocator.getAjaxHandlerService().getRequirementAdminAjaxList(httpServletRequest);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public String searchAdminRequirementAjaxList() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            //AjaxHandlerAction ajaxHandlerAction = new AjaxHandlerAction();
            try {

                // System.out.println("values :::::::");
                //createdBy,assignedTo,title,postedDate1,postedDate2
                // System.out.println("createdBy----------"+getCreatedBy()+"---assignedTo-------------"+getAssignedTo()+"---title-------------"+getTitle()+"---postedDate1-------"+getPostedDate1()+"----postedDate2----"+getPostedDate2());

                //responseString = ServiceLocator.getAjaxHandlerService().getsearchRequirementAjaxList(httpServletRequest,this);
                responseString = ServiceLocator.getAjaxHandlerService().searchAdminRequirementAjaxList(httpServletRequest, this);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public String searchpmoActivityAjaxList() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            //AjaxHandlerAction ajaxHandlerAction = new AjaxHandlerAction();
            try {

             //   System.out.println("values :::::::");

                responseString = ServiceLocator.getAjaxHandlerService().searchpmoActivityAjaxList(httpServletRequest, this);

                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

     public String getMyProjectsByAccountId() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) || (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)) {
                String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
           
                  int isManager = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_USER_MANAGER).toString());
          
                if(isManager==1){
                       setMyTeamMembers((Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
                      String loginIdList = DataSourceDataProvider.getInstance().getTeamLoginIdList(getMyTeamMembers());
                      
                      if(loginIdList!=null && !"".equals(loginIdList)){
                       loginIdList="'"+loginId+"'"+","+loginIdList;
                      }else{
                           loginIdList="'"+loginId+"'";
                      }
                      //loginIdList = loginIdList.replaceAll("\"", " ");
                       responseString = ServiceLocator.getAjaxHandlerService().getProjectsForManagerByAccountId(getAccountId(), loginIdList);  
                     }else{
                     
                  String resourceType = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
                String empId = "";
                if (resourceType.equalsIgnoreCase("c") || resourceType.equalsIgnoreCase("v")) {
                    empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString();
                } else {
                    empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
                }
                responseString = ServiceLocator.getAjaxHandlerService().getMyProjectsByAccountId(getAccountId(), resourceType, empId);
                } //     System.out.println("responseString-->"+responseString);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }


    // Perf. dashboard methods 
    public String getTopPerformers() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                responseString = ServiceLocator.getAjaxHandlerService().getTopPerformers(getStartDate(), getEndDate(), getIsManagerInclude(), getDepartmentId());
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String getEmployeesForPerformers() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                responseString = ServiceLocator.getAjaxHandlerService().getEmployeesForPerformers(getStartDate(), getEndDate(), getIsManagerInclude(), getDepartmentId());
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String getPastReviewData() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {

//               int empId=0;
//                if("".equals(getEmpNo())){
//                    empId=DataSourceDataProvider.getInstance().getEmpIdByLoginId(getEmpnameById());
//                }
//                else{
//                    empId=Integer.parseInt(getEmpNo());
//                }
                responseString = ServiceLocator.getAjaxHandlerService().getPastReviewData(getEmpnameById());
                //   System.out.println("responseString---->"+responseString);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
                System.err.println(ex);
                ex.printStackTrace();
            }

        }
        return null;
    }

    public String getEmployeeReviewDetails() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                String roleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
                // System.out.println("roleName--------"+roleName);
                // if(roleName.equals("Employee")){
                if (roleName.equalsIgnoreCase("Employee")) {
                    setLoginId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());

                    responseString = ServiceLocator.getAjaxHandlerService().getEmployeeReviewDetails(getStartDate(), getEndDate(), getLoginId(), getIsManagerInclude());
                    httpServletResponse.setContentType("text");
                    httpServletResponse.getWriter().write(responseString);
                }
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String getEmployeePastReviewData() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {

//               int empId=0;
//                if("".equals(getEmpNo())){
//                    empId=DataSourceDataProvider.getInstance().getEmpIdByLoginId(getEmpnameById());
//                }
//                else{
//                    empId=Integer.parseInt(getEmpNo());
//                }
                setLoginId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                responseString = ServiceLocator.getAjaxHandlerService().getEmployeePastReviewData(getLoginId(), getStartDate(), getEndDate());
                //   System.out.println("responseString---->"+responseString);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
                System.err.println(ex);
                ex.printStackTrace();
            }

        }
        return null;
    }

    /*
     * Migrate Consultant
     * Date : 06/03/2015
     * 
     */
    public String addWebsiteConsultant() throws Exception {
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                //setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                responseString = ServiceLocator.getAjaxHandlerWebService().addWebsiteConsultant(getConsultantId(), httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());

                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            }
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    /*
     * Event Adding methods 
     * Date : 07/15/2015
     * Author : Santosh Kola
     * 
     */
    public String addEventposting() throws Exception {
        try {

            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());

            responseString = ServiceLocator.getAjaxHandlerWebService().addEventposting(this);

            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    public String editEventposting() throws Exception {
        try {

            //setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());

            responseString = ServiceLocator.getAjaxHandlerWebService().editEventposting(String.valueOf(getEventId()));

            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    public String updateEventposting() throws Exception {
        try {

            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());

            responseString = ServiceLocator.getAjaxHandlerWebService().updateEventposting(this);

            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    public String addEventSpeaker() throws Exception {
        try {

            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());

            responseString = ServiceLocator.getAjaxHandlerWebService().addEventSpeaker(this);

            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    //editEventSpeaker
    public String editEventSpeaker() throws Exception {
        try {

            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());

            responseString = ServiceLocator.getAjaxHandlerWebService().editEventSpeaker(getSpeakerId());

            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    public String updateEventSpeaker() throws Exception {
        try {

            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());

            responseString = ServiceLocator.getAjaxHandlerWebService().updateEventSpeaker(this);

            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    public String getWebsiteInfoDetails() throws Exception {
        try {

            //  setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());

            responseString = ServiceLocator.getAjaxHandlerWebService().getInfoDetails(getInfoId(), getTableName());

            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    public String updateAfterEvent() throws Exception {
        try {

            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());

            responseString = ServiceLocator.getAjaxHandlerWebService().updateAfterEvent(this);

            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    //getQmeetMapByYear
    public String getQmeetMapByYear() throws Exception {
        try {

            //  setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());

            responseString = ServiceLocator.getAjaxHandlerWebService().getQmeetMap(getYear());

            httpServletResponse.setContentType("text/xml");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }
    //getEventSeries
   /*  public String getEventSeries() throws Exception
    {
    try{
    
    //  setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
    
    responseString = ServiceLocator.getAjaxHandlerWebService().getEventSeries(String.valueOf(getEventId()),getEventType());
    
    httpServletResponse.setContentType("text/xml");
    httpServletResponse.getWriter().write(responseString);
    }catch(ServiceLocatorException ex){
    System.err.println(ex);
    }catch(IOException ioe){
    System.err.println(ioe);
    }
    return null;
    } 
     */

    public String doCreateWebinarSeries() throws Exception {
        try {

            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());

            responseString = ServiceLocator.getAjaxHandlerWebService().doCreateWebinarSeries(this);

            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    public String getSeriesDetails() throws Exception {
        try {

            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());

            responseString = ServiceLocator.getAjaxHandlerWebService().getSeriesDetails(getSeriesId());

            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }
    //doUpdateWebinarSeries

    public String doUpdateWebinarSeries() throws Exception {
        try {

            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());

            responseString = ServiceLocator.getAjaxHandlerWebService().doUpdateWebinarSeries(this);

            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    public String getAccountContactsDescription() {

        if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null)) {

            try {

                responseString = ServiceLocator.getAjaxHandlerService().getAccountContactsDescription(getId());

                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            //}
        }//Close Session Checking
        return null;
    }

    public String addTrackName() throws Exception {
        try {
            //System.out.println("addTrackName start");
            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());

            //responseString = ServiceLocator.getAjaxHandlerWebService().addEventSpeaker(this);
            responseString = ServiceLocator.getAjaxHandlerWebService().addTrackName(this);

            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }

        // System.out.println("addTrackName end");
        return null;
    }

    public String editTrackName() throws Exception {
        try {
            //System.out.println("addTrackName start");
            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());

            //responseString = ServiceLocator.getAjaxHandlerWebService().addEventSpeaker(this);
            responseString = ServiceLocator.getAjaxHandlerWebService().editTrackName(this);

            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }

        // System.out.println("addTrackName end");
        return null;
    }

    public String doAddPeople() throws Exception {
        try {

            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());

            responseString = ServiceLocator.getAjaxHandlerWebService().doAddPeople(this);

            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    public String getPeopleDetails() throws Exception {
        try {

            //setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());

            responseString = ServiceLocator.getAjaxHandlerWebService().getPeopleDetails(String.valueOf(getId()));

            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

//doUpdatePeopleDetails
    public String doUpdatePeopleDetails() throws Exception {
        try {

            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());

            responseString = ServiceLocator.getAjaxHandlerWebService().doUpdatePeopleDetails(this);

            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    public String doLibraryTitleCheck() throws Exception {
        try {
            //   System.out.println("doLibraryTitleCheck action");
            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());

            //responseString = ServiceLocator.getAjaxHandlerWebService().addEventSpeaker(this);
            responseString = ServiceLocator.getAjaxHandlerWebService().doLibraryTitleCheck(this);

            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }

        // System.out.println("addTrackName end");
        return null;
    }

    public String getEmpolyeeCount() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                responseString = ServiceLocator.getAjaxHandlerService().getEmpolyeeCount();
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String getRequirementCount() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                responseString = ServiceLocator.getAjaxHandlerService().getRequirementCount(getPastMonths());
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String getGreenSheetCount() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                responseString = ServiceLocator.getAjaxHandlerService().getGreenSheetCount(getPastMonths(), isExternal());
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String getOpportunitiesCounts() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                responseString = ServiceLocator.getAjaxHandlerService().getOpportunitiesCounts(getPastMonths());
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String getOpportunitiesCount() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
               // responseString = ServiceLocator.getAjaxHandlerService().getOpportunitiesCount(getPastMonths(), httpServletRequest);
                  responseString = ServiceLocator.getAjaxHandlerService().getOpportunitiesCount(getPastMonths(),getOpportunityState(), httpServletRequest);
              
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String getConsultantStatusDetails() {

        if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null)) {

            try {

                responseString = ServiceLocator.getAjaxHandlerService().getConsultantStatusDetails(this);

                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            //}
        }//Close Session Checking
        return null;
    }

    public String getSubmissionReport() {

        if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null)) {

            try {

                responseString = ServiceLocator.getAjaxHandlerService().getSubmissionReport(this, httpServletRequest);

                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            //}
        }//Close Session Checking
        return null;
    }

    public String getConsultantListForRequirement() {

        if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null)) {

            try {

                responseString = ServiceLocator.getAjaxHandlerService().getConsultantListForRequirement(this);

                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            //}
        }//Close Session Checking
        return null;
    }

    public String popupTechRatingsWindow() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                String result = ServiceLocator.getAjaxHandlerService().popupTechRatingsWindow(getConsultantId());
                // System.out.println("in action-->"+result);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(result);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;

    }
    /*Question Adding for SurveyForm
     * Date : 09/02/2015
     * Author : Santosh Kola
     */

    public String doAddQuestionnaire() {
        resultType = LOGIN;


        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {


                setSurveyId(getSurveyId());
                setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());



                responseString = ServiceLocator.getAjaxHandlerWebService().doAddQuestionnaire(this);


                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            }
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }

        // System.out.println("addTrackName end");
        return null;
    }

    public String editQuestionnaireDetails() throws Exception {


        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {


                setSurveyId(getSurveyId());
                setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());



                responseString = ServiceLocator.getAjaxHandlerWebService().editQuestionnaireDetails(this);


                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            }
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    public String doUpdateQuestionnaire() {
        resultType = LOGIN;


        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {


                setSurveyId(getSurveyId());
                setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());



                responseString = ServiceLocator.getAjaxHandlerWebService().doUpdateQuestionnaire(this);


                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            }
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }

        // System.out.println("addTrackName end");
        return null;
    }

    public String showReviewDetails() throws Exception {
        try {

            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
            // responseString = ServiceLocator.getAjaxHandlerWebService().editPosting(getJobId());
            responseString = ServiceLocator.getAjaxHandlerWebService().showReviewDetails(getSurveyInfoId());
            //  System.out.println("responseString-->"+responseString);

            //responseString = "updated";
            //}else {
            //responseString ="Error";
            //   }
            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    //getSearchQuestionInfo
    public String getSearchQuestionInfo() {
        resultType = LOGIN;


        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {


                // setSurveyId(getSurveyId());
                setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());


                List questionList = (List) httpServletRequest.getSession(false).getAttribute("surveyFormQuestionnaireList");
                // responseString = ServiceLocator.getAjaxHandlerWebService().editPosting(getJobId());
                responseString = ServiceLocator.getAjaxHandlerWebService().getSearchQuestionInfo(getQuestionId(), questionList);
                //    responseString = ServiceLocator.getAjaxHandlerWebService().getSearchQuestionInfo(this);


                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            }
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }

        // System.out.println("addTrackName end");
        return null;
    }

    //------------------
    public String doUpdateSequance() {
        resultType = LOGIN;


        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {


                // setSurveyId(getSurveyId());
                setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                responseString = ServiceLocator.getAjaxHandlerWebService().updateQuestionSequence(this);

                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            }
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }

        // System.out.println("addTrackName end");
        return null;
    }

    public String getMyTeam() {

        if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null)) {

            try {

                responseString = ServiceLocator.getAjaxHandlerService().getMyTeam(getLoginId(), getDepartmentName());

                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            //}
        }//Close Session Checking
        return null;
    }

    public String getRequirementStatusReport() {

        if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null)) {

            try {

                responseString = ServiceLocator.getAjaxHandlerService().getRequirementStatusReport(this, httpServletRequest);

                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            //}
        }//Close Session Checking
        return null;
    }

    public String getAccountsByPriorities() throws ServiceLocatorException {
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            int isManager = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_USER_MANAGER).toString());
            int isTeamLead = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_TEAM_LEAD).toString());
            DataSourceDataProvider dataSourceDataProvider = null;

            String teamId = dataSourceDataProvider.getInstance().getTeamNameByUserId(getTeamMemberId());
            //String teamId=getTeamMemberId();
            if (AuthorizationManager.getInstance().isAuthorizedUser("DASHBOARD_ACCESS", userRoleId)) {
                try {
                    /* if(teamId.equals("") || teamId.equals("All"))
                    {
                    responseString="no";   
                    }*/

                    // if (teamId.equals("") || teamId.equals("B2B") || teamId.equals("BPM") || teamId.equals("E-Commerce") || teamId.equals("SAP") || teamId.equals("QA")) {
                   responseString = ServiceLocator.getAjaxHandlerService().getAccountsByPriorities(getTeamMemberId(), getTeamName(), httpServletRequest);
                     //responseString = ServiceLocator.getAjaxHandlerService().getAccountsByPriorities(getTeamMemberId(), getTeamName(),getDashBoardStartDate(),getDashBoardEndDate(), httpServletRequest);

                    //   } else {
                    //     responseString = "others";
                    //  }
                    httpServletResponse.setContentType("text");
                    httpServletResponse.getWriter().write(responseString);
                } catch (Exception ex) {
                }
            }
        }//Close Session Checking
        return null;
    }

    public String getBDMDashboardInfo() {
        // System.out.println("method inside");
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            try {




                //responseString = ServiceLocator.getAjaxHandlerService().salesActivitiesAsGraph(getStartDateSummaryGraph(), getEndDateSummaryGraph(), activityType, recruiterName, httpServletRequest);
                responseString = ServiceLocator.getAjaxHandlerService().getBDMDashboardInfo(this, httpServletRequest);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
            }

        }//Close Session Checking
        return null;
    }

    public String getAccountNamesList() {
        try {
            // System.out.println("email--"+getEmail());
            // responseString = ServiceLocator.getAjaxHandlerService().getConsultantList("SELECT Id,Email2,CellPhoneNo,AvailableFrom,IsReject,RatePerHour,WorkAuthorization FROM tblRecConsultant where Email2 LIKE '" + getEmail() + "%'");
            responseString = ServiceLocator.getAjaxHandlerService().getAccountNamesList("SELECT DISTINCT tblCrmAccount.Id,tblCrmAccount.NAME  FROM tblCrmAccount LEFT JOIN tblCrmAccountTeam ON (tblCrmAccount.Id = tblCrmAccountTeam.AccountId) WHERE tblCrmAccount.NAME LIKE '" + getAccountName() + "%' ORDER BY tblCrmAccount.NAME");
            httpServletResponse.setContentType("text/xml");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    public String getBDMDashboardRevenueInfo() {
        // System.out.println("method inside");
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            try {
                //responseString = ServiceLocator.getAjaxHandlerService().salesActivitiesAsGraph(getStartDateSummaryGraph(), getEndDateSummaryGraph(), activityType, recruiterName, httpServletRequest);
                responseString = ServiceLocator.getAjaxHandlerService().getBDMDashboardRevenueInfo(this, httpServletRequest);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
            }

        }//Close Session Checking
        return null;
    }

    public String getBDMDashboardOppertunitiesInfo() {
        // System.out.println("method inside");
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            try {




                //responseString = ServiceLocator.getAjaxHandlerService().salesActivitiesAsGraph(getStartDateSummaryGraph(), getEndDateSummaryGraph(), activityType, recruiterName, httpServletRequest);
                responseString = ServiceLocator.getAjaxHandlerService().getBDMDashboardOppertunitiesInfo(this, httpServletRequest);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
            }

        }//Close Session Checking
        return null;
    }

    public String getOpportunitesByPractice() {
        //System.out.println("method inside");
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            try {




                //responseString = ServiceLocator.getAjaxHandlerService().salesActivitiesAsGraph(getStartDateSummaryGraph(), getEndDateSummaryGraph(), activityType, recruiterName, httpServletRequest);
                responseString = ServiceLocator.getAjaxHandlerService().getOpportunitesByPractice(this, httpServletRequest);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
            }

        }//Close Session Checking
        return null;
    }

    public String getRequirementsByPractice() {
        //System.out.println("method inside");
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            try {




                //responseString = ServiceLocator.getAjaxHandlerService().salesActivitiesAsGraph(getStartDateSummaryGraph(), getEndDateSummaryGraph(), activityType, recruiterName, httpServletRequest);
                responseString = ServiceLocator.getAjaxHandlerService().getRequirementsByPractice(this, httpServletRequest);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
            }

        }//Close Session Checking
        return null;
    }

    public String getgreenSheetsDetailsByPractice() {
        //System.out.println("method inside");
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            try {




                //responseString = ServiceLocator.getAjaxHandlerService().salesActivitiesAsGraph(getStartDateSummaryGraph(), getEndDateSummaryGraph(), activityType, recruiterName, httpServletRequest);
                responseString = ServiceLocator.getAjaxHandlerService().getgreenSheetsDetailsByPractice(this, httpServletRequest);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
            }

        }//Close Session Checking
        return null;
    }

    public String getOpportunitiesDetailsByPractice() {
        //System.out.println("method inside");
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            try {




                //responseString = ServiceLocator.getAjaxHandlerService().salesActivitiesAsGraph(getStartDateSummaryGraph(), getEndDateSummaryGraph(), activityType, recruiterName, httpServletRequest);
                responseString = ServiceLocator.getAjaxHandlerService().getOpportunitiesDetailsByPractice(this, httpServletRequest);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
            }

        }//Close Session Checking
        return null;
    }

    public String getRequirementDetailsByPractice() {
        // System.out.println("method inside");
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            try {




                //responseString = ServiceLocator.getAjaxHandlerService().salesActivitiesAsGraph(getStartDateSummaryGraph(), getEndDateSummaryGraph(), activityType, recruiterName, httpServletRequest);
                responseString = ServiceLocator.getAjaxHandlerService().getRequirementDetailsByPractice(this, httpServletRequest);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
            }

        }//Close Session Checking
        return null;
    }

    public String getGreenSheetDetailsByPractice() {
        //  System.out.println("method inside");
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            try {




                //responseString = ServiceLocator.getAjaxHandlerService().salesActivitiesAsGraph(getStartDateSummaryGraph(), getEndDateSummaryGraph(), activityType, recruiterName, httpServletRequest);
                responseString = ServiceLocator.getAjaxHandlerService().getGreenSheetDetailsByPractice(this, httpServletRequest);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
            }

        }//Close Session Checking
        return null;
    }

    public String getaccountRenewalByTeamMember() throws ServiceLocatorException {
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            int isManager = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_USER_MANAGER).toString());
            int isTeamLead = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_TEAM_LEAD).toString());
            DataSourceDataProvider dataSourceDataProvider = null;

            String teamId = dataSourceDataProvider.getInstance().getTeamNameByUserId(getTeamMemberId());
            //String teamId=getTeamMemberId();
            if (AuthorizationManager.getInstance().isAuthorizedUser("DASHBOARD_ACCESS", userRoleId)) {
                try {
                    /* if(teamId.equals("") || teamId.equals("All"))
                    {
                    responseString="no";   
                    }*/

                    // if (teamId.equals("") || teamId.equals("B2B") || teamId.equals("BPM") || teamId.equals("E-Commerce") || teamId.equals("SAP") || teamId.equals("QA")) {
                    // responseString = ServiceLocator.getAjaxHandlerService().getAccountsByPriorities(getTeamMemberId(), getTeamName(), httpServletRequest);
                    responseString = ServiceLocator.getAjaxHandlerService().getaccountRenewalByTeamMember(getTeamMemberId(), httpServletRequest);
                    // System.out.println("+++++++++++++++++"+responseString);
                    //   } else {
                    //     responseString = "others";
                    //  }
                    httpServletResponse.setContentType("text");
                    httpServletResponse.getWriter().write(responseString);
                } catch (Exception ex) {
                }
            }
        }//Close Session Checking
        return null;
    }

    public String getUtilizationReport() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {


            if (AuthorizationManager.getInstance().isAuthorizedUser("OPS_DASHBOARD_ACCESS", userRoleId)) {
                try {
                    responseString = ServiceLocator.getAjaxHandlerService().getUtilizationReport(this, httpServletRequest);
                    httpServletResponse.setContentType("text/xml");
                    httpServletResponse.getWriter().write(responseString);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }//Close Session Checking
        return null;
    }

   /* public String getActivitySummaryByLoginId() {

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            if (AuthorizationManager.getInstance().isAuthorizedUser("DASHBOARD_ACCESS", userRoleId)) {//&& ((isManager ==1) || (isTeamLead ==1)) ){
                try {

                    String activityType = httpServletRequest.getParameter("activityType");
                    String recruiterName = httpServletRequest.getParameter("createdBy");



                   // responseString = ServiceLocator.getAjaxHandlerService().getActivitySummaryByLoginId(getStartDateSummaryGraph(), getEndDateSummaryGraph(), activityType, recruiterName, httpServletRequest);
                   // responseString = ServiceLocator.getAjaxHandlerService().getActivitySummaryByLoginId(getStartDateSummaryGraph(), getEndDateSummaryGraph(), activityType, recruiterName, httpServletRequest,getTeamMemberCheck());
                  //  responseString = ServiceLocator.getAjaxHandlerService().getActivitySummaryByLoginId(getStartDateSummaryGraph(), getEndDateSummaryGraph(), activityType, recruiterName, httpServletRequest,getTeamMemberCheck(),getTitleType());
                    
  responseString = ServiceLocator.getAjaxHandlerService().getActivitySummaryByLoginId(getStartDateSummaryGraph(), getEndDateSummaryGraph(), activityType, recruiterName, httpServletRequest,getTeamMemberCheck(),getTitleType(),getCampaignId());
                    httpServletResponse.setContentType("text");
                    httpServletResponse.getWriter().write(responseString);
                } catch (Exception ex) {
                }
            }
        }//Close Session Checking
        return null;
    }*/
  public String getActivitySummaryByLoginId() {

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            if (AuthorizationManager.getInstance().isAuthorizedUser("DASHBOARD_ACCESS", userRoleId)) {//&& ((isManager ==1) || (isTeamLead ==1)) ){
                try {

                    String activityType = httpServletRequest.getParameter("activityType");
                    String recruiterName = httpServletRequest.getParameter("createdBy");



                   // responseString = ServiceLocator.getAjaxHandlerService().getActivitySummaryByLoginId(getStartDateSummaryGraph(), getEndDateSummaryGraph(), activityType, recruiterName, httpServletRequest);
                   // responseString = ServiceLocator.getAjaxHandlerService().getActivitySummaryByLoginId(getStartDateSummaryGraph(), getEndDateSummaryGraph(), activityType, recruiterName, httpServletRequest,getTeamMemberCheck());
                  //  responseString = ServiceLocator.getAjaxHandlerService().getActivitySummaryByLoginId(getStartDateSummaryGraph(), getEndDateSummaryGraph(), activityType, recruiterName, httpServletRequest,getTeamMemberCheck(),getTitleType());
                    
  responseString = ServiceLocator.getAjaxHandlerService().getActivitySummaryByLoginId(getStartDateSummaryGraph(), getEndDateSummaryGraph(), activityType, recruiterName, httpServletRequest,getTeamMemberCheck(),getCampaignId());
                    httpServletResponse.setContentType("text");
                    httpServletResponse.getWriter().write(responseString);
                } catch (Exception ex) {
                }
            }
        }//Close Session Checking
        return null;
    }
    //getActivityDecriptionById
    public String getActivityDecriptionById() {

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            if (AuthorizationManager.getInstance().isAuthorizedUser("DASHBOARD_ACCESS", userRoleId)) {//&& ((isManager ==1) || (isTeamLead ==1)) ){
                try {





                    responseString = ServiceLocator.getAjaxHandlerService().getActivityDecriptionById(getActivityId());

                    httpServletResponse.setContentType("text");
                    httpServletResponse.getWriter().write(responseString);
                } catch (Exception ex) {
                }
            }
        }//Close Session Checking
        return null;
    }

    public String updateSurveyFormExpiryDate() {
        resultType = LOGIN;
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                setSurveyId(getSurveyId());
                setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());

                responseString = ServiceLocator.getAjaxHandlerWebService().doUpdateSurveyFormExpiryDate(this);

                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            }
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }

        // System.out.println("addTrackName end");
        return null;
    }

    public String getRequirementDetailsByStatus() {
        // System.out.println("method inside");
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            try {




                //responseString = ServiceLocator.getAjaxHandlerService().salesActivitiesAsGraph(getStartDateSummaryGraph(), getEndDateSummaryGraph(), activityType, recruiterName, httpServletRequest);
                responseString = ServiceLocator.getAjaxHandlerService().getRequirementDetailsByStatus(this, httpServletRequest);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
            }

        }//Close Session Checking
        return null;
    }


    /* get My oopportunity details */
    public String getMyOppDashBoard() {
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            int isManager = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_USER_MANAGER).toString());
            int isTeamLead = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_TEAM_LEAD).toString());
            String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            // String curWorkCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString().trim();
            // curWorkCountry = curWorkCountry + "%";
            // System.out.println("COUNT IS  "+curWorkCountry);
            /*
             *This if loop is to check the Authentication
             **/
            //  if(AuthorizationManager.getInstance().isAuthorizedUser("DASHBOARD_ACCESS",userRoleId) && ((isManager ==1) || (isTeamLead ==1)) ){
            if (AuthorizationManager.getInstance().isAuthorizedUser("DASHBOARD_ACCESS", userRoleId)) {
                try {

                  //  responseString = ServiceLocator.getAjaxHandlerService().getMyOppDashBoard(getType(), getState(), getDueStartDate(), getDueEndDate(), httpServletRequest);
                    responseString = ServiceLocator.getAjaxHandlerService().getMyOppDashBoard(getType(), getState(), getDueStartDate(), getDueEndDate(),getPracticeName(), httpServletRequest);
                    httpServletResponse.setContentType("text");
                    httpServletResponse.getWriter().write(responseString);
                } catch (Exception ex) {
                }
            }
        }//Close Session Checking
        return null;
    }

    public String popupSkillSetWindow() {
        String str;
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {

                str = ServiceLocator.getAjaxHandlerService().popupSkillSetWindow(getEmpId());
                httpServletResponse.setContentType("text/html");
                if (str != null) {
                    httpServletResponse.getWriter().write((str));
                } else {
                    httpServletResponse.getWriter().write(("No Record Present"));
                }

            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }//Close Session Checking
        return null;
    }

    /*********************************************************
    Accomadation Report in operations role start
    
     *********************************************************/
    public String getAccomadationReport() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {


            if (AuthorizationManager.getInstance().isAuthorizedUser("OPS_DASHBOARD_ACCESS", userRoleId)) {
                try {
                    responseString = ServiceLocator.getAjaxHandlerService().getAccomadationReport(this, httpServletRequest);
                    httpServletResponse.setContentType("text/xml");
                    httpServletResponse.getWriter().write(responseString);
                   // System.out.println("the response in Action" + responseString);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }//Close Session Checking
        return null;
    }

    /**
     * @return the opsContactId1
     */
    /*********************************************************
    Accomadation Report in operations role end
    
     *********************************************************/
    /*=======================================================================
    presales opertunity dashboard start
    ========================================================================*/
    public String getPreSalesOppDashBoard() {

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            if (AuthorizationManager.getInstance().isAuthorizedUser("PRESALES_DASHBOARD_ACCESS", userRoleId)) {
                try {

                  //  responseString = ServiceLocator.getAjaxHandlerService().getPreSalesOppDashBoard(getType(), getState(), getDueStartDate(), getDueEndDate(), getTeamMemberId(), httpServletRequest);
                    responseString = ServiceLocator.getAjaxHandlerService().getPreSalesOppDashBoard(getType(), getState(), getDueStartDate(), getDueEndDate(), getTeamMemberId(),getPracticeName(), httpServletRequest);
                    httpServletResponse.setContentType("text");
                    httpServletResponse.getWriter().write(responseString);
                } catch (Exception ex) {
                }
            }
        }//Close Session Checking
        return null;
    }
    /*=======================================================================
    presales opertunity dashboard end
    ========================================================================*/
    /*=======================================================================
    presales project dashboard start
    ========================================================================*/

    public String getPreSalesProjectDashBoard() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            if (AuthorizationManager.getInstance().isAuthorizedUser("PRESALES_DASHBOARD_ACCESS", userRoleId)) {
                try {

                    responseString = ServiceLocator.getAjaxHandlerService().getPreSalesProjectDashBoard(getStartDate(), getEndDate(), getTeamMemberId(), getState(), httpServletRequest);
                    httpServletResponse.setContentType("text");
                    httpServletResponse.getWriter().write(responseString);
                } catch (Exception ex) {
                }
            }
        }//Close Session Checking
        return null;
    }
    /*=======================================================================
    presales project dashboard end
    ========================================================================*/
    /*===================================================================
    presales onsite/offshore people dashboard start
    ====================================================================== */

    public String getPresalesOnsiteOffshoreDashBoard() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            if (AuthorizationManager.getInstance().isAuthorizedUser("PRESALES_DASHBOARD_ACCESS", userRoleId)) {
                try {

                    responseString = ServiceLocator.getAjaxHandlerService().getPresalesOnsiteOffshoreDashBoard(getCountry(), getTeamMemberId(), httpServletRequest);
                    httpServletResponse.setContentType("text");
                    httpServletResponse.getWriter().write(responseString);
                } catch (Exception ex) {
                }
            }
        }
        return null;
    }
    /*===================================================================
    presales onsite/offshore people dashboard end
    ====================================================================== */

    /*********************************************************
    Start Sales KPI Report in operations role
    
     *********************************************************/
    public String getsalesKPIReport() {

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {


            if (AuthorizationManager.getInstance().isAuthorizedUser("OPS_DASHBOARD_ACCESS", userRoleId)) {
                try {
                    responseString = ServiceLocator.getAjaxHandlerService().getsalesKPIReport(this, httpServletRequest);
                    httpServletResponse.setContentType("text/xml");
                    httpServletResponse.getWriter().write(responseString);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }//Close Session Checking
        return null;
    }

    /*********************************************************
    end Sales KPI Report in operations role
    
     *********************************************************/
    public String popupTasksRiskDescWindow() throws Exception {
        String str;
        /*
         *This if loop is to check whether there is Session or not
         **/
        // if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
        if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) || (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)) {
            try {
                // str = ServiceLocator.getAjaxHandlerService().popupTaskDesc(issueId);
                str = ServiceLocator.getAjaxHandlerService().popupTasksRiskDescWindow(getId());
                httpServletResponse.setContentType("text/html");
                httpServletResponse.getWriter().write((str));
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }//Close Session Checking

        return null;
    }

    public String popupTasksRiskResolutionWindow() throws Exception {
        String str;
        /*
         *This if loop is to check whether there is Session or not
         **/
        // if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
        if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) || (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)) {
            try {
                //  str = ServiceLocator.getAjaxHandlerService().popupTaskDesc(issueId);
                str = ServiceLocator.getAjaxHandlerService().popupTasksRiskResolutionWindow(getId());
                httpServletResponse.setContentType("text/html");
                httpServletResponse.getWriter().write((str));
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }//Close Session Checking

        return null;
    }

    /*********************************************************
    Recruitment KPI Report in operations role
    
     *********************************************************/
    public String getRecruitmentKpiReport() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {


            if (AuthorizationManager.getInstance().isAuthorizedUser("OPS_DASHBOARD_ACCESS", userRoleId)) {
                try {
                    responseString = ServiceLocator.getAjaxHandlerService().getRecruitmentKpiReport(this, httpServletRequest);
                    httpServletResponse.setContentType("text/xml");
                    httpServletResponse.getWriter().write(responseString);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }//Close Session Checking
        return null;
    }

    /*********************************************************
    end Recruitment KPI Report in operations role
    
     *********************************************************/
    public String addProjectTaskDetails() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            try {
                setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                setCreatedDate(DateUtility.getInstance().getCurrentMySqlDateTime());
                setStatus("Created");
                setSeverity("Low");
                //responseString = ServiceLocator.getAjaxHandlerService().salesActivitiesAsGraph(getStartDateSummaryGraph(), getEndDateSummaryGraph(), activityType, recruiterName, httpServletRequest);
                responseString = ServiceLocator.getAjaxHandlerService().addProjectTaskDetails(this);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
            }

        }//Close Session Checking
        return null;
    }

    public String getProjectTaskDetails() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {


            try {


                responseString = ServiceLocator.getAjaxHandlerService().getProjectTaskDetails(getTaskId());
                //     System.out.println("responseString---->"+responseString);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
            }

        }//Close Session Checking
        return null;
    }

    public String updateProjectTaskDetails() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            try {
                setModifiedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                setModifiedDate(DateUtility.getInstance().getCurrentMySqlDateTime());
                //responseString = ServiceLocator.getAjaxHandlerService().salesActivitiesAsGraph(getStartDateSummaryGraph(), getEndDateSummaryGraph(), activityType, recruiterName, httpServletRequest);
                responseString = ServiceLocator.getAjaxHandlerService().doUpdateProjectTaskDetails(this);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
            }

        }//Close Session Checking
        return null;
    }

    /*********************************************************
    AvailableEmp  Report in operations role
    
     *********************************************************/
    public String getAvailableEmpReport() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {


            if (AuthorizationManager.getInstance().isAuthorizedUser("OPS_DASHBOARD_ACCESS", userRoleId)) {
                try {
                    responseString = ServiceLocator.getAjaxHandlerService().getAvailableEmpReport(this, httpServletRequest);
                    httpServletResponse.setContentType("text/xml");
                    httpServletResponse.getWriter().write(responseString);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }//Close Session Checking
        return null;
    }

    /*********************************************************
    end AvailableEmp Report in operations role
    
     *********************************************************/
    /* Bridge Management System   */
    public String addBMSBridgeDetails() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            try {
                setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                setCreatedDate(DateUtility.getInstance().getCurrentMySqlDateTime());
                //responseString = ServiceLocator.getAjaxHandlerService().salesActivitiesAsGraph(getStartDateSummaryGraph(), getEndDateSummaryGraph(), activityType, recruiterName, httpServletRequest);
                responseString = ServiceLocator.getAjaxHandlerService().addBMSBridgeDetails(this);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
            }

        }
        return null;
    }
    public String getBmsBridgeDetails() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {


            try {


                responseString = ServiceLocator.getAjaxHandlerService().getBmsBridgeDetails(getBridgeCode());
                //     System.out.println("responseString---->"+responseString);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
            }

        }//Close Session Checking
        return null;
    }
     public String doUpdateBMSBridgeDetails() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            try {
                setModifiedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                setModifiedDate(DateUtility.getInstance().getCurrentMySqlDateTime());
                //responseString = ServiceLocator.getAjaxHandlerService().salesActivitiesAsGraph(getStartDateSummaryGraph(), getEndDateSummaryGraph(), activityType, recruiterName, httpServletRequest);
                responseString = ServiceLocator.getAjaxHandlerService().doUpdateBMSBridgeDetails(this);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
            }

        }//Close Session Checking
        return null;
    }
     public String doAvailableBridgeCheck() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            try {


                String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();

                setCreatedBy(loginId);

                //responseString = ServiceLocator.getAjaxHandlerService().salesActivitiesAsGraph(getStartDateSummaryGraph(), getEndDateSummaryGraph(), activityType, recruiterName, httpServletRequest);
                responseString = ServiceLocator.getAjaxHandlerService().doAvailableBridgeCheck(this);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
            }

        }//Close Session Checking
        return null;
    }
     public String doAddBridgeEvent() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            try {


                String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();

                setCreatedBy(loginId);

                //responseString = ServiceLocator.getAjaxHandlerService().salesActivitiesAsGraph(getStartDateSummaryGraph(), getEndDateSummaryGraph(), activityType, recruiterName, httpServletRequest);
                responseString = ServiceLocator.getAjaxHandlerService().doAddBridgeEvent(this);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
            }

        }//Close Session Checking
        return null;
    }
     public String getBridgeList() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            try {


                 String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();

                //responseString = ServiceLocator.getAjaxHandlerService().salesActivitiesAsGraph(getStartDateSummaryGraph(), getEndDateSummaryGraph(), activityType, recruiterName, httpServletRequest);
                responseString = ServiceLocator.getAjaxHandlerService().getBridgeList(getBridgeDate(),loginId);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
            }

        }//Close Session Checking
        return null;
    }
     public String isActiveBridge() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            try {


               

                //responseString = ServiceLocator.getAjaxHandlerService().salesActivitiesAsGraph(getStartDateSummaryGraph(), getEndDateSummaryGraph(), activityType, recruiterName, httpServletRequest);
                responseString = ServiceLocator.getAjaxHandlerService().isActiveBridge(getBridgeCode());
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
            }

        }//Close Session Checking
        return null;
    }
     public String getAvailableBridges() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            try {


               

                //responseString = ServiceLocator.getAjaxHandlerService().salesActivitiesAsGraph(getStartDateSummaryGraph(), getEndDateSummaryGraph(), activityType, recruiterName, httpServletRequest);
                responseString = ServiceLocator.getAjaxHandlerService().getAvailableBridges(getBridgeDate(),getStartTime(),getMidDayFrom(),getTimeZone(),getDuration(),getDurationType());
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
            }

        }//Close Session Checking
        return null;
    }
     public String doCancelBridgeEvent() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            try {


               String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();

                //responseString = ServiceLocator.getAjaxHandlerService().salesActivitiesAsGraph(getStartDateSummaryGraph(), getEndDateSummaryGraph(), activityType, recruiterName, httpServletRequest);
                responseString = ServiceLocator.getAjaxHandlerService().doCancelBridgeEvent(getId(),getReason(),loginId);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
            }

        }//Close Session Checking
        return null;
    }
     public String getBridgeEventSearchDetails() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            try {


                 String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();

                //responseString = ServiceLocator.getAjaxHandlerService().salesActivitiesAsGraph(getStartDateSummaryGraph(), getEndDateSummaryGraph(), activityType, recruiterName, httpServletRequest);
                responseString = ServiceLocator.getAjaxHandlerService().getBridgeEventSearchDetails(this,loginId);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
            }

        }//Close Session Checking
        return null;
    }
     public String createBridgeIssue() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            try {


                 String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
setCreatedBy(loginId);
                //responseString = ServiceLocator.getAjaxHandlerService().salesActivitiesAsGraph(getStartDateSummaryGraph(), getEndDateSummaryGraph(), activityType, recruiterName, httpServletRequest);
                responseString = ServiceLocator.getAjaxHandlerService().createBridgeIssue(this);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
            }

        }//Close Session Checking
        return null;
    }
    /* Bridge Management System end  */


 public String addInvestmentdetails() {        
        if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null)) {
            try {                
            String loginId=httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            setCreatedBy(loginId);         
            setOptType("Ins");
            
          //  System.out.println("");
            
                if (getAttachInvestment() != null && !"".equals(getAttachInvestment())) {                   
                    attachmentService = ServiceLocator.getAttachmentService();                                                   
                    generatedPath = attachmentService.generatePath(com.mss.mirage.util.Properties.getProperty("Marketing.Investment.Path"), "Investment");
                   
                  //  System.out.println("File Path-->"+generatedPath +"/"+ getFileFileName());
                    File targetDirectory = new File(generatedPath +"/"+ getFileFileName());
                    setAttachmentLocation(targetDirectory.toString());                    
                    FileUtils.copyFile(getFile(), targetDirectory);
                } else {                     
                    setAttachmentLocation("");                    
                }

                //if (!ServiceLocator.getAjaxHandlerService().addInvestmentdetails(this)) {
                responseString=ServiceLocator.getAjaxHandlerService().addOrUpdateInvestmentdetails(this);
                           

                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                System.err.println(ex);
            } catch (IOException ioe) {
                System.err.println(ioe);
            }
        }
        return null;
    }
     public String updateInvestmentdetails() {  
           if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null)) {
        try {
             setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
             setOptType("Upd");
           /* if (!ServiceLocator.getAjaxHandlerService().updateInvestmentdetails(this)) {
                responseString = "updated";
            } else {  
                responseString = "Error";
            }   */       
                  // System.out.println("getAttachInvestment"+getAttachInvestment());
                if (getAttachInvestment() != null && !"".equals(getAttachInvestment())) {                   
                    attachmentService = ServiceLocator.getAttachmentService();                                                   
                    generatedPath = attachmentService.generatePath(com.mss.mirage.util.Properties.getProperty("Marketing.Investment.Path"), "Investment");
                   
                    //System.out.println("File Path-->"+generatedPath +"/"+ getFileFileName());
                    File targetDirectory = new File(generatedPath +"/"+ getFileFileName());
                    setAttachmentLocation(targetDirectory.toString()); 
                  //  System.out.println("targetDirectory.toString()"+targetDirectory.toString());
                   // System.out.println(getFile());
                    FileUtils.copyFile(getFile(), targetDirectory);
                } else {                     
                    setAttachmentLocation("");                    
                }
             
             
            responseString= responseString=ServiceLocator.getAjaxHandlerService().addOrUpdateInvestmentdetails(this);
            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
           }
        return null;
    }
       
       
       
     public String getInvestmentDetails() 
     {  
         if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                responseString = ServiceLocator.getAjaxHandlerService().getInvestmentDetails(this);                
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
            }

        }
         return null;
     }
     
     
public String contactSearchAjaxList() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {

              //  responseString = ServiceLocator.getAjaxHandlerService().contactSearchAjaxList(httpServletRequest, this);
                responseString = ServiceLocator.getAjaxHandlerService().contactSearchAjaxList(httpServletRequest, this);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
   
     public String contactSearchAjax() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {

           //     responseString = ServiceLocator.getAjaxHandlerService().contactSearchAjaxList(httpServletRequest, this);
                responseString = ServiceLocator.getAjaxHandlerService().contactSearchAjaxList(httpServletRequest, this);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

	 public String getCampaignContactsList() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
              //  responseString = ServiceLocator.getAjaxHandlerService().getCampaignContactsList(httpServletRequest,getCampaignId());
                  responseString = ServiceLocator.getAjaxHandlerService().getCampaignContactsList(httpServletRequest,this);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
public String getCampaignSearch() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
             userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            try {
                
                 //setStartDate(DateUtility.getInstance().getLastYearFirstMonth());
               // setEndDate(DateUtility.getInstance().getCurrentMySqlDate());    
                responseString = ServiceLocator.getAjaxHandlerService().getCampaignSearch(this,userRoleId);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

 public String getLeadDetailsList() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                String roleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
                responseString = ServiceLocator.getAjaxHandlerService().getLeadDetailsList(getAccountId() ,roleName);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
/*********************************************************
        Start Account Renewal by State dashboard  Report 
     *********************************************************/
        
           public String getaccountRenewalByState() throws ServiceLocatorException {
               //System.out.println("entere in the getaccountRenewalByState action");
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
//           
            DataSourceDataProvider dataSourceDataProvider = null;

            String teamId = dataSourceDataProvider.getInstance().getTeamNameByUserId(getTeamMemberId());
            //String teamId=getTeamMemberId();
            if (AuthorizationManager.getInstance().isAuthorizedUser("DASHBOARD_ACCESS", userRoleId)) {
                try {
                   
                    responseString = ServiceLocator.getAjaxHandlerService().getaccountRenewalByState(getRenewalState(), httpServletRequest);
                  
                    httpServletResponse.setContentType("text");
                    httpServletResponse.getWriter().write(responseString);
                } catch (Exception ex) {
                }
            }
        }//Close Session Checking
        return null;
           }

           /*********************************************************
        End Account Renewal by State dashboard  Report 
     *********************************************************/

/*********************************************************
        start Requirement dashboard  Report 
     *********************************************************/

         
       /*   public String getReqDashBoard() {
        /*
         *This if loop is to check whether there is Session or not
         **
              //System.out.println("entered into the getReqDashBoard action");
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
           int isManager = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_USER_MANAGER).toString());
            int isTeamLead = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_TEAM_LEAD).toString());
            String practice = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_PRACTICE).toString();
            String title = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_TITLE).toString();
                           

            String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                if (AuthorizationManager.getInstance().isAuthorizedUser("DASHBOARD_ACCESS", userRoleId)) {
                try {
                    if ("All".equals(getRelatedTeam())) {
                             setMyTeamMembers((Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
                        myTeamMembers.put(loginId, loginId);
                    }
                    else {
                        String department = DataSourceDataProvider.getInstance().getDepartmentName(getRelatedTeam());
                        setMyTeamMembers(DataSourceDataProvider.getInstance().getMyTeamMembers(getRelatedTeam(), department));
                        myTeamMembers.put(getRelatedTeam(), getRelatedTeam());

                    }
                    //  String myTeamMembers = getKeys(getMyTeamMembers(), ",");
                    String myTeamMembers = "";
                    myTeamMembers = DataSourceDataProvider.getInstance().getTeamLoginIdList(getMyTeamMembers());
                    myTeamMembers = myTeamMembers.replaceAll("'", "");
//                    
                    
                    
                    
                  //  System.out.println("myTeamMembersmyTeamMembers in action"+myTeamMembers);
                    setRelatedTeam(myTeamMembers);

                    responseString = ServiceLocator.getAjaxHandlerService().getReqDashBoard(this,myTeamMembers, httpServletRequest);
                    httpServletResponse.setContentType("text");
                    httpServletResponse.getWriter().write(responseString);
                } catch (Exception ex) {
                }
            }
        }//Close Session Checking
        return null;
    }*/
 public String getReqDashBoard() {
        /*
         *This if loop is to check whether there is Session or not
         **/
          //    System.out.println("entered into the getReqDashBoard action");
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
           int isManager = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_USER_MANAGER).toString());
            int isTeamLead = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_TEAM_LEAD).toString());
            String practice = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_PRACTICE).toString();
            String title = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_TITLE).toString();
            String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            String empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
                if (AuthorizationManager.getInstance().isAuthorizedUser("DASHBOARD_ACCESS", userRoleId)) {
                try {
                    if ("All".equals(getRelatedTeam())) {
                        if(title.equals("BDM")){
                            setMyTeamMembers(DataSourceDataProvider.getInstance().getBdmAssociateList(empId));
                        }
                        else{
                                 setMyTeamMembers((Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
                        }
                        
                        myTeamMembers.put(loginId, loginId);
                    }
              
                    else {
                      //  System.out.println("in else case");
                      //  System.out.println("getRelatedTeam()---->"+getRelatedTeam());
                         if(!title.equals("BDM")){
                             
                             String department = DataSourceDataProvider.getInstance().getDepartmentName(getRelatedTeam());
                        setMyTeamMembers(DataSourceDataProvider.getInstance().getMyTeamMembers(getRelatedTeam(), department));
                        }
                        myTeamMembers.put(getRelatedTeam(), getRelatedTeam());
                      //  System.out.println("myTeamMembers----->"+myTeamMembers);
                    }
                       //   System.out.println("myTeamMembers----->"+myTeamMembers);
                    //  String myTeamMembers = getKeys(getMyTeamMembers(), ",");
                    String myTeamMembers = "";
                    myTeamMembers = DataSourceDataProvider.getInstance().getTeamLoginIdList(getMyTeamMembers());
                    myTeamMembers = myTeamMembers.replaceAll("'", "");
//                    
                    
                    
                    
                  //  System.out.println("myTeamMembersmyTeamMembers in action"+myTeamMembers);
                    setRelatedTeam(myTeamMembers);

                    responseString = ServiceLocator.getAjaxHandlerService().getReqDashBoard(this,myTeamMembers, httpServletRequest);
                    httpServletResponse.setContentType("text");
                    httpServletResponse.getWriter().write(responseString);
                } catch (Exception ex) {
                }
            }
        }//Close Session Checking
        return null;
    }

/*********************************************************
        end Requirement dashboard  Report 
     *********************************************************/
/*=======================================================================
     presales project dashboard start
     ========================================================================*/

  
    
    public String getProjectMembers() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                responseString = ServiceLocator.getAjaxHandlerService().getProjectMembers(getProjectId());
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        }  catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    /*=======================================================================
     presales project dashboard end
     ========================================================================*/
public String timsheetAttachmentUpload() {

        try {

          //  System.out.println("teAttachemntUpload");
            //  setCurrentTask(tasksVTO);
            // String  generatedPath = null;
            if (getFileFileName() != null) {
                attachmentService = ServiceLocator.getAttachmentService();
               // System.out.println("DefaultPath-->"+com.mss.mirage.util.Properties.getProperty("Attachments.Path"));
                generatedPath = attachmentService.generatePath(com.mss.mirage.util.Properties.getProperty("Attachments.Path"), "TimeSheets");
              // System.out.println("generatedPath-->"+generatedPath);
                
                
                File targetDirectory = new File(generatedPath + "/" + getFileFileName());
                setAttachmentLocation(targetDirectory.getAbsolutePath());
                setFileFlag(1);
              //  System.out.println("getAttachmentLocation-->"+getAttachmentLocation());
                FileUtils.copyFile(getFile(), targetDirectory);
                //  setObjectType("Emp Reviews");
            } else {
                objectType = "NoFile";
                setAttachmentLocation("");
                //setFilepath("");
                //attachmentName = "";
            }

           

            if (ServiceLocator.getAjaxHandlerService().timeSheetAttachemntUpload(this)>0) {
                responseString = "uploaded";
            } else {
                responseString = "Error";
            }
            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }


public String removeTimeSheetAttachement() throws ServiceLocatorException {
        /*
         *This if loop is to check whether there is Session or not
         **/
          responseString="";
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                
                        ServiceLocator.getAjaxHandlerService().deleteTeAttachment(getEmpId(),getTimeSheetID());
                setFileFlag(0);
                setAttachmentLocation("");
                int updatedRows = ServiceLocator.getAjaxHandlerService().timeSheetAttachemntUpload(this);
                
              responseString = String.valueOf(updatedRows);
//responseString =String.valueOf(lastId);
               // System.out.println("responseString-->"+responseString);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
            }

        }//Close Session Checking
        return null;
    }   
/*
 * Emeet Methods 
 * Date : 01/29/2016
 * Author : Phani Kanuri
 * 
 */
 public String addExecitiveMeeting() throws Exception {
        try {

            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
            responseString = ServiceLocator.getAjaxHandlerWebService().doAddExecitiveMeeting(this);
            //    System.out.println("responseString-->"+responseString);

            //responseString = "updated";
            //}else {
            //responseString ="Error";
            //   }
            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

 public String editExeMeeting() throws Exception {
        try {

            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
            responseString = ServiceLocator.getAjaxHandlerWebService().editExeMeeting(getId());
            //  System.out.println("responseString-->"+responseString);

            //responseString = "updated";
            //}else {
            //responseString ="Error";
            //   }
            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }
 
 public String updateExecitiveMeeting() throws Exception {
        try {
            // System.out.println("techincal action-->"+getJobtechnical());
            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
            responseString = ServiceLocator.getAjaxHandlerWebService().updateExecitiveMeeting(this);
            // System.out.println("responseString-->"+responseString);

            //responseString = "updated";
            //}else {
            //responseString ="Error";
            //   }
            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }
public String addExecitiveMeetAttendees() throws Exception {
        try {

            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
            responseString = ServiceLocator.getAjaxHandlerService().doAddExecitiveMeetAttendees(this);
            //    System.out.println("responseString-->"+responseString);

            //responseString = "updated";
            //}else {
            //responseString ="Error";
            //   }
            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

 public String editExeMeetingAttendees() throws Exception {
        try {

            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
          
            JSONObject jobsMap  = ServiceLocator.getAjaxHandlerService().doEditExeMeetingAttendees(getId());
            // System.out.println("jobsMap-->"+jobsMap);
//System.out.println("jsonss-->"+jobsMap.toString());
            //responseString = "updated";
            //}else {
            //responseString ="Error";
            //   }
            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(jobsMap.toString());
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }
 public String updateExecitiveMeetingAttendeesDetails() throws Exception {
        try {

            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
            responseString = ServiceLocator.getAjaxHandlerService().doUpdateExecitiveMeetingAttendeesDetails(this);
            //    System.out.println("responseString-->"+responseString);

            //responseString = "updated";
            //}else {
            //responseString ="Error";
            //   }
            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }
 
 
 
 
 
 public String updateCompletedExecitiveMeetDetails() throws Exception {
        try {
            // System.out.println("techincal action-->"+getJobtechnical());
            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
            responseString = ServiceLocator.getAjaxHandlerWebService().updateCompletedExecitiveMeetDetails(this);
            // System.out.println("responseString-->"+responseString);

            //responseString = "updated";
            //}else {
            //responseString ="Error";
            //   }
            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }
 
  public String doActiveEmmet() throws Exception {
        try {

            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
            responseString = ServiceLocator.getAjaxHandlerWebService().doActiveEmmet(this);
                //System.out.println("responseString-->"+responseString);

            //responseString = "updated";
            //}else {
            //responseString ="Error";
            //   }
            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }
  
/*
 * current status management start
 */
  public String searchCustomerProjectsAjaxList() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            //AjaxHandlerAction ajaxHandlerAction = new AjaxHandlerAction();
            try {
                responseString = ServiceLocator.getAjaxHandlerService().searchCustomerProjectsAjaxList(httpServletRequest, this);

                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
      
     public String isExistedProjectName() throws ServiceLocatorException{
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            try {
                //System.out.println("getProjectName"+getProjectName());
                //responseString = ServiceLocator.getAjaxHandlerService().salesActivitiesAsGraph(getStartDateSummaryGraph(), getEndDateSummaryGraph(), activityType, recruiterName, httpServletRequest);
                responseString = ServiceLocator.getAjaxHandlerService().isExistedProjectName(getAccountId(),getProjectName());
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
            }

        }//Close Session Checking
        return null;
    }
    
     public String employeeAvailableProjects() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            try {  String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
setCreatedBy(loginId);
                //responseString = ServiceLocator.getAjaxHandlerService().salesActivitiesAsGraph(getStartDateSummaryGraph(), getEndDateSummaryGraph(), activityType, recruiterName, httpServletRequest);
//responseString = ServiceLocator.getAjaxHandlerService().employeeAvailableProjects(getEmpId(),getStatus(),getStartDate());
responseString = ServiceLocator.getAjaxHandlerService().employeeAvailableProjects(getEmpId(),getStatus(),getStartDate());

                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
            }

        }//Close Session Checking
      return null;
       }
  /*
 * current status management end
 */
      public String popupOpertunitiesWindow() {
        String str;
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {

                str = ServiceLocator.getAjaxHandlerService().popupOpertunitiesWindow(getEmpId());
                httpServletResponse.setContentType("text/html");
                if (str != null) {
                    httpServletResponse.getWriter().write((str));
                } else {
                    httpServletResponse.getWriter().write(("No Record Present"));
                }

            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }//Close Session Checking
        return null;
    }
/***********************************************************
 *PF Portal start 
 * ********************************************************/
  public String getPFPortalReport() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {


            if (AuthorizationManager.getInstance().isAuthorizedUser("OPS_DASHBOARD_ACCESS", userRoleId)) {
                try {
                    responseString = ServiceLocator.getAjaxHandlerService().getPFPortalReport(this, httpServletRequest);
                    httpServletResponse.setContentType("text/xml");
                    httpServletResponse.getWriter().write(responseString);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }//Close Session Checking
        return null;
    }

 /***********************************************************
 *PF Portal  end
 * ********************************************************/
  
  
   
public String ajaxAccountExcelFileUpload() { 
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
        try {
            loginId=httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();

            if (getFileFileName() != null)
            {
                //responseString = "uploaded";
              responseString=  ServiceLocator.getAjaxHandlerService().ajaxAccountExcelFileUpload(this);
            }
            else
            {
                 responseString = "Error";
            }            

          //  responseString = "uploaded";
            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        }
        return null;
    }


public String getEmployeesBasedOnCustomer() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {


            if (AuthorizationManager.getInstance().isAuthorizedUser("OPS_DASHBOARD_ACCESS", userRoleId)) {
                try {
                    responseString = ServiceLocator.getAjaxHandlerService().getEmployeesBasedOnCustomer(this, httpServletRequest);
                    httpServletResponse.setContentType("text/xml");
                    httpServletResponse.getWriter().write(responseString);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }//Close Session Checking
        return null;
    }

public String getAllProjectsByAccountId() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
          
               // String resourceType = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
               
                responseString = ServiceLocator.getAjaxHandlerService().getAllProjectsByAccountId(getAccountId());
                //     System.out.println("responseString-->"+responseString);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
        
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

/***********************************************************
 *PMO suggestion list start
 * ********************************************************/
   public String getEmployeeDetailforPMOActivity() {
        // System.out.println("in action-->");
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                // String fromId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                String result = ServiceLocator.getAjaxHandlerService().getEmployeeDetailforPMOActivity(getCustomerName());
                // System.out.println("in action-->"+result);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(result);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;

    }
 /***********************************************************
 *PMO suggestion list end
 * ********************************************************/
   public String getOnboardReport() throws Exception {
        try {
             
            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
            responseString = ServiceLocator.getAjaxHandlerService().getOnboardReport(this,httpServletRequest);
            //    System.out.println("responseString-->"+responseString);

            //responseString = "updated";
            //}else {
            //responseString ="Error";
              //}
            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }
   

   public String getTerminationDetails() throws ServiceLocatorException{
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            try {
                responseString = ServiceLocator.getAjaxHandlerService().getTerminationDetails(getEmpId(),getLoginId());
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
            }

        }//Close Session Checking
        return null;
    }
 public String doUpdateTerminationDetails() throws ServiceLocatorException{
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            try {
                responseString = ServiceLocator.getAjaxHandlerService().doUpdateTerminationDetails(getEmpId(),getDateOfTermination(),getReasonsForTerminate());;
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
            }

        }//Close Session Checking
        return null;
    } 

 /*
  * Employee Locations
  * Date : 04/06/2016
  * Author : Santosh Kola
  */
 
     public String getEmployeeLocations() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                //System.out.println("getCountry-->"+getCountry());
                responseString = ServiceLocator.getAjaxHandlerService().getEmployeeLocations(getCountry());
                httpServletResponse.setContentType("text/xml");
                
               // System.out.println("-->"+responseString);
                httpServletResponse.getWriter().write(responseString);
                //System.out.println("-->"+responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
 
 
 public String getOpportunityList() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {

                responseString = ServiceLocator.getAjaxHandlerService().getOpportunityList(getOpportunityState(), conAccId);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
   
    public String popupOpportunityDescription() throws Exception {
        String str;
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                str = ServiceLocator.getAjaxHandlerService().popupOpportunityDescription(getOpportunityId());
                httpServletResponse.setContentType("text/html");
                httpServletResponse.getWriter().write((str));
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }//Close Session Checking

        return null;
    }
	public String getMissingDataReport() throws Exception {
        try {
             
            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
        //    responseString = ServiceLocator.getAjaxHandlerService().getOnboardReport(this,httpServletRequest);
            responseString = ServiceLocator.getAjaxHandlerService().getMissingDataReport(this,httpServletRequest);
            //    System.out.println("responseString-->"+responseString);

            //responseString = "updated";
            //}else {
            //responseString ="Error";
              //}
            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }
        
          public String getCustOppDashBoard() {
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null) {
         
                try {
                    //  System.out.println("getCustOppDashBoard method");
                  //  responseString = ServiceLocator.getAjaxHandlerService().getMyOppDashBoard(getType(), getState(), getDueStartDate(), getDueEndDate(), httpServletRequest);
                    responseString = ServiceLocator.getAjaxHandlerService().getCustOppDashBoard(getState(), getDueStartDate(), getDueEndDate(),getPracticeName(),getSviValue(), httpServletRequest);
                    httpServletResponse.setContentType("text");
                    httpServletResponse.getWriter().write(responseString);
                } catch (Exception ex) {
                }
            
        }//Close Session Checking
        return null;
    }

 public String getRequirementList() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {

            //    System.out.println("getRequirementstatus..........." + getRequirementstatus());
                responseString = ServiceLocator.getAjaxHandlerService().getRequirementList(getRequirementstatus(), conAccId);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
 
 
    /*Lead search
     * Date : 04/20/2016
     * Author : Santosh Kola
     */
    
     public String getLeadSearch() throws Exception{
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
              //  System.out.println("In Ajax");
               // responseString = ServiceLocator.getAjaxHandlerService().getLeadSearch(getLeadTitle(), getStatus(),getCreatedDateFrom(),getCreatedDateTo(),getInactiveDateFrom(),getInactiveDateTo(),getInvestmentId(),getCreatedBy(),getState());
                String userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
 int AdminFlag=0;
 Map rolesMap=(Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_ROLES);
if(rolesMap.containsValue("Admin"))
   {
      AdminFlag=1;
      }
 //responseString = ServiceLocator.getAjaxHandlerService().getLeadSearch(getLeadTitle(), getStatus(),getCreatedDateFrom(),getCreatedDateTo(),getInactiveDateFrom(),getInactiveDateTo(),getInvestmentId(),getCreatedBy(),getState(),userId,AdminFlag,httpServletRequest);
responseString = ServiceLocator.getAjaxHandlerService().getLeadSearch(getLeadTitle(), getStatus(),getCreatedDateFrom(),getCreatedDateTo(),getInactiveDateFrom(),getInactiveDateTo(),getInvestmentId(),getCreatedBy(),getState(),getPriority(),userId,AdminFlag,httpServletRequest);
	
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
     
        public String getAccountDetailsPopup() throws Exception{
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
              //  System.out.println("In Ajax");
                responseString = ServiceLocator.getAjaxHandlerService().getAccountDetailsById(getAccountId());
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
    public String getContactNamesList() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                //responseString = ServiceLocator.getAjaxHandlerService().getProjectNamesList(getAccId());
                responseString = ServiceLocator.getAjaxHandlerService().getContactNamesList(getAccId());

                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public String getOppurtunitySearch() throws Exception{
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
//                System.out.println("In Ajax");
//                System.out.println("investment Id---->"+getInvestmentId());
                responseString = ServiceLocator.getAjaxHandlerService().getOppurtunitySearch(getInvestmentId(),getAccountName(),getCreatedBy());
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
public String getOppurtunityDetailsPopup() throws Exception{
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
              //  System.out.println("In Ajax");
                responseString = ServiceLocator.getAjaxHandlerService().getOppurtunityAccountDetails(getOpportunityId());
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
	
 public String doConstantContactListSearch() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
             userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            try {
                
                 //setStartDate(DateUtility.getInstance().getLastYearFirstMonth());
               // setEndDate(DateUtility.getInstance().getCurrentMySqlDate());    
                responseString = ServiceLocator.getAjaxHandlerService().doConstantContactListSearch(this);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
 

 public String doCampaignContactListSearch() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
             userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            try {
                
             
                responseString = ServiceLocator.getAjaxHandlerService().doCampaignContactListSearch(this);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
 
   public String doGetCampaignEmailsList() {
        String str;
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                str = ServiceLocator.getAjaxHandlerService().doGetCampaignEmailsList(getCampaignId(),getRequirement());
                httpServletResponse.setContentType("text/html");
             httpServletResponse.getWriter().write(str);

            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }//Close Session Checking
        return null;
    }
   
   public String doSynchronizeToHubble() {
        String str;
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
              //  System.out.println("getConstantContactId.."+getConstantContactId());
                str = ServiceLocator.getAjaxHandlerService().doSynchronizeToHubble(getConstantContactId());
                httpServletResponse.setContentType("text/html");
             httpServletResponse.getWriter().write(str);

            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }//Close Session Checking
        return null;
    }
     public String doConstantContactEmailList() {
        String str;
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                str = ServiceLocator.getAjaxHandlerService().doConstantContactEmailList(getConstantContactId(),getContactEmailID());
                httpServletResponse.setContentType("text/html");
             httpServletResponse.getWriter().write(str);

            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }//Close Session Checking
        return null;
    }
     
     
 public String doGetSyncListSearch() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
             userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            try {
                 
                responseString = ServiceLocator.getAjaxHandlerService().doGetSyncListSearch(getLoactionName());
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
    
 public String dotrackingEmailCampaign() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
             userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            try {
                 
                responseString = ServiceLocator.getAjaxHandlerService().dotrackingEmailCampaign(getCampaignId());
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
 
public String getLostClosedOpportunities() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                responseString = ServiceLocator.getAjaxHandlerService().getLostClosedOpportunities(getPastMonths(), httpServletRequest);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
        
    }

public String getEmployeeTypeDetails() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                responseString = ServiceLocator.getAjaxHandlerService().getEmployeeTypeDetails(getCountry(), httpServletRequest);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
        
    }


public String getResourceTypeDetails() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                responseString = ServiceLocator.getAjaxHandlerService().getResourceTypeDetails(getProjectId(), httpServletRequest);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
        
    }	


public String doGetTitleType() {

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            if (AuthorizationManager.getInstance().isAuthorizedUser("DASHBOARD_ACCESS", userRoleId)) {
                try {

                    responseString = ServiceLocator.getAjaxHandlerService().doGetTitleType(getLoginId());

                    httpServletResponse.setContentType("text");
                    httpServletResponse.getWriter().write(responseString);
                }  
                catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
            }
        }//Close Session Checking}
        return null;
    }

 public String projectDetailsDashboard() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            //AjaxHandlerAction ajaxHandlerAction = new AjaxHandlerAction();
            try {

             //   System.out.println("values :::::::");

                responseString = ServiceLocator.getAjaxHandlerService().projectDetailsDashboard(this,httpServletRequest);
               //   System.out.println("responseString "+responseString);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
              
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
public String getProjectEmployeeDetails() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            //AjaxHandlerAction ajaxHandlerAction = new AjaxHandlerAction();
            try {

             //   System.out.println("values :::::::");

                responseString = ServiceLocator.getAjaxHandlerService().getProjectEmployeeDetails(this,httpServletRequest);
                //  System.out.println("responseString "+responseString);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
              
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }


 public String monthlyStatusReport() throws ServiceLocatorException {
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
        
            DataSourceDataProvider dataSourceDataProvider = null;

            if (AuthorizationManager.getInstance().isAuthorizedUser("DASHBOARD_ACCESS", userRoleId)) {
                try {
                  responseString = ServiceLocator.getAjaxHandlerService().getMonthlyStatusReport(this, httpServletRequest);
                    // System.out.println("+++++++++++++++++"+responseString);
                
                    httpServletResponse.setContentType("text");
                    httpServletResponse.getWriter().write(responseString);
                } catch (Exception ex) {
                }
            }
        }//Close Session Checking
        return null;
    }
 public String getIsTeamLead() throws ServiceLocatorException {
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
        
          //  DataSourceDataProvider dataSourceDataProvider = null;

            if (AuthorizationManager.getInstance().isAuthorizedUser("DASHBOARD_ACCESS", userRoleId)) {
                try {
                  responseString = DataSourceDataProvider.getInstance().checkIsTeamLead(getTeamMemberId());
                   //  System.out.println("+++++++++++++++++"+responseString);
                
                    httpServletResponse.setContentType("text");
                    httpServletResponse.getWriter().write(responseString);
                } catch (Exception ex) {
                }
            }
        }//Close Session Checking
        return null;
    }

public String getCerCommnets() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                //responseString = ServiceLocator.getAjaxHandlerService().getProjectNamesList(getAccId());
                responseString = ServiceLocator.getAjaxHandlerService().getCerCommnets(getId());

                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
  
  public String getStageRelatedComments() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                //responseString = ServiceLocator.getAjaxHandlerService().getProjectNamesList(getAccId());
                responseString = ServiceLocator.getAjaxHandlerService().getStageRelatedComments(getRequestId(),getStage());

                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
  
  public String getOpportunityNamesList() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                //responseString = ServiceLocator.getAjaxHandlerService().getProjectNamesList(getAccId());
                responseString = ServiceLocator.getAjaxHandlerService().getOpportunityNamesList(getAccId());

                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
   public String getTerminationDetailsForInActivePerson() throws ServiceLocatorException{
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            try {
                responseString = ServiceLocator.getAjaxHandlerService().getTerminationDetailsForInActivePerson(getEmpId(),getLoginId());
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
            }

        }//Close Session Checking
        return null;
    }
  
   /*Account suggestion list for Inside Sales status
    * Author : Teja
    * Date : 01/19/2017
    */
 
 public String getCustomerDetailsByBde() {
        try {
            responseString = ServiceLocator.getAjaxHandlerService().getCustomerDetails("SELECT DISTINCT tblCrmAccount.NAME,tblCrmAccount.Id FROM tblCrmAccountTeam  LEFT OUTER JOIN tblCrmAccount ON tblCrmAccount.Id=tblCrmAccountTeam.AccountId WHERE Name LIKE '" + getCustomerName() + "%' AND TeamMemberId='"+getBdeLoginId()+"'");
            httpServletResponse.setContentType("text/xml");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }  
  
 /*Events functionality new enhancements start
  * Date : 01/23/2017
  * Author : Santosh Kola/Phani Kanuri
  */
 
 
  
    public String doAddQmeetEventPost() throws Exception {
        try {

            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());

            responseString = ServiceLocator.getAjaxHandlerWebService().doAddQmeetEventPost(this);

            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    public String editEventpost() throws Exception {
        try {

            //setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());

            responseString = ServiceLocator.getAjaxHandlerWebService().editEventpost(String.valueOf(getEventId()));

            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    public String updateQmeetEventPost() throws Exception {
        try {

            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());

            responseString = ServiceLocator.getAjaxHandlerWebService().updateQmeetEventPost(this);

            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }
   
   
   public String doAddExternalWebinarEventPost() throws Exception {
        try {

            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());

            responseString = ServiceLocator.getAjaxHandlerWebService().doAddExternalWebinarEventPost(this);

            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }
   
    public String editExternalEventpost() throws Exception {
        try {

            //setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());

            responseString = ServiceLocator.getAjaxHandlerWebService().editExternalEventpost(String.valueOf(getEventId()));

            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    public String updateExternalWebinarEventPost() throws Exception {
        try {

            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());

            responseString = ServiceLocator.getAjaxHandlerWebService().updateExternalWebinarEventposting(this);

            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }
    
    
    public String doAddIEEEventPost() throws Exception {
        try {

            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());

            responseString = ServiceLocator.getAjaxHandlerWebService().doAddIEEEventPost(this);

            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }
   
    public String updateIEEEventPost() throws Exception {
        try {

            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());

            responseString = ServiceLocator.getAjaxHandlerWebService().updateIEEEventPost(this);

            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }
    
   public String doAddInternalWebinarEventPost() throws Exception {
        try {

            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());

            responseString = ServiceLocator.getAjaxHandlerWebService().doAddInternalWebinarEventPost(this);

            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }
   public String updateInternalWebinarEvent() throws Exception {
        try {

            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());

            responseString = ServiceLocator.getAjaxHandlerWebService().updateInternalWebinarEvent(this);

            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }
    public String updateExternalConferenceEventPost() throws Exception {
        try {
          //  System.out.println("in updateExternalConferenceEventPost method");
            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());

           // responseString = ServiceLocator.getAjaxHandlerWebService().updateExternalWebinarEventposting(this);
            setEventType("C");
            responseString = ServiceLocator.getAjaxHandlerWebService().updateExternalConferenceEventposting(this);

            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }
    
     public String doAddExternalConferenceEventPost() throws Exception {
        try {

            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());

           // responseString = ServiceLocator.getAjaxHandlerWebService().doAddExternalWebinarEventPost(this);
              setEventType("C");
            responseString = ServiceLocator.getAjaxHandlerWebService().doAddExternalConferenceEventPost(this);

            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }
     
     
  /*    public String editWebinarEventposting() throws Exception {
        try {

            //setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());

            responseString = ServiceLocator.getAjaxHandlerWebService().editWebianarEventposting(String.valueOf(getEventId()));

            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }
     */
     
    public String updateWebinarAfterEvent() throws Exception {
        try {

            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());

            responseString = ServiceLocator.getAjaxHandlerWebService().updateWebinarAfterEvent(this);

            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

     public String getWebinarSeriesList() throws Exception {
        try {

            //setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());

            responseString = ServiceLocator.getAjaxHandlerWebService().getWebinarSeriesList(getSeriesType());

            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }
 public String doAddWebinarSeries() throws Exception {
        try {

            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());

            responseString = ServiceLocator.getAjaxHandlerWebService().doCreateWebinarSeries(this);

            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }
 
 
 public String getWebinarSeriesDetails() throws Exception {
        try {

            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());

            responseString = ServiceLocator.getAjaxHandlerWebService().getWebinarSeriesDetails(getSeriesId());

            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }
    //doUpdateWebinarSeries

    public String doUpdateWebinarSeriesDetails() throws Exception {
        try {

            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());

            responseString = ServiceLocator.getAjaxHandlerWebService().doUpdateWebinarSeriesDetails(this);

            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }
    
    
    public String getQmeetAttendeeDetails() throws Exception {
        try {

            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());

            responseString = ServiceLocator.getAjaxHandlerWebService().getQmeetAttendeeDetails(httpServletRequest,getInfoId());

            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }
     
   public String addAttendeesForExecitiveMeet() throws Exception {
        try {

            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
            responseString = ServiceLocator.getAjaxHandlerService().doAddAttendeesforExecitiveMeet(this);
            //    System.out.println("responseString-->"+responseString);

            //responseString = "updated";
            //}else {
            //responseString ="Error";
            //   }
            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

 public String editAttendeesforExeMeeting() throws Exception {
        try {

            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
          
            JSONObject jobsMap  = ServiceLocator.getAjaxHandlerService().doEditExecutiveMeetingAttendees(getId());
            // System.out.println("jobsMap-->"+jobsMap);
//System.out.println("jsonss-->"+jobsMap.toString());
            //responseString = "updated";
            //}else {
            //responseString ="Error";
            //   }
            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(jobsMap.toString());
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }
 public String updateAttendeesDetailsforExecitiveMeeting() throws Exception {
        try {
         //   System.out.println("updateAttendeesDetailsForExecitiveMeeting  action");
            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
            responseString = ServiceLocator.getAjaxHandlerService().doUpdateAttendeesDetailsforExecitiveMeeting(this);
            //    System.out.println("responseString-->"+responseString);

            //responseString = "updated";
            //}else {
            //responseString ="Error";
            //   }
            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }
  //getWebinarsListBySeriesId
 
 
 
 
 public String getAjaxWebinarsListBySeriesId() throws Exception {
        try {

           // System.out.println("getWebinarsListBySeriesId-->");

//            responseString = ServiceLocator.getAjaxHandlerWebService().getWebinarSeriesList(getSeriesType());
responseString =  ServiceLocator.getAjaxHandlerWebService().getWebinarsListBySeriesId(getSeriesId());
            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }
  public String addOrRemoveWebinarToSeries() throws Exception {
        try {

         //   System.out.println("getWebinarsListBySeriesId-->");

//            responseString = ServiceLocator.getAjaxHandlerWebService().getWebinarSeriesList(getSeriesType());
responseString =  ServiceLocator.getAjaxHandlerWebService().addOrRemoveWebinarToSeries(getSeriesId(),String.valueOf(getEventId()));
            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }
 
 /*Events functionality new enhancements end
  * Date : 01/23/2017
  * Author : Santosh Kola/Phani Kanuri
  */
 
   
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getPracticeName() {
        return practiceName;
    }

    public void setPracticeName(String practiceName) {
        this.practiceName = practiceName;
    }

    public String getSubPracticeName() {
        return subPracticeName;
    }

    public void setSubPracticeName(String subPracticeName) {
        this.subPracticeName = subPracticeName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getSecondState() {
        return secondState;
    }

    public void setSecondState(String secondState) {
        this.secondState = secondState;
    }

    public String getAccId() {
        return accId;
    }

    public void setAccId(String accId) {
        this.accId = accId;
    }

    public String getOldTeamMember() {
        return oldTeamMember;
    }

    public void setOldTeamMember(String oldTeamMember) {
        this.oldTeamMember = oldTeamMember;
    }

    public String getNewMember() {
        return newMember;
    }

    public void setNewMember(String newMember) {
        this.newMember = newMember;
    }

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getCatagoryName() {
        return catagoryName;
    }

    public void setCatagoryName(String catagoryName) {
        this.catagoryName = catagoryName;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getTerritory() {
        return territory;
    }

    public void setTerritory(String territory) {
        this.territory = territory;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getStockSymbol1() {
        return stockSymbol1;
    }

    public void setStockSymbol1(String stockSymbol1) {
        this.stockSymbol1 = stockSymbol1;
    }

    public String getLastModiyBy() {
        return lastModiyBy;
    }

    public void setLastModiyBy(String lastModiyBy) {
        this.lastModiyBy = lastModiyBy;
    }

    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getGentran() {
        return gentran;
    }

    public void setGentran(String gentran) {
        this.gentran = gentran;
    }

    public String getHarbinger() {
        return harbinger;
    }

    public void setHarbinger(String harbinger) {
        this.harbinger = harbinger;
    }

    public String getMercator() {
        return mercator;
    }

    public void setMercator(String mercator) {
        this.mercator = mercator;
    }

    public String getSeeBeyond() {
        return seeBeyond;
    }

    public void setSeeBeyond(String seeBeyond) {
        this.seeBeyond = seeBeyond;
    }

    public String getWebMethods() {
        return webMethods;
    }

    public void setWebMethods(String webMethods) {
        this.webMethods = webMethods;
    }

    public String getWDI() {
        return WDI;
    }

    public void setWDI(String WDI) {
        this.WDI = WDI;
    }

    public String getICS() {
        return ICS;
    }

    public void setICS(String ICS) {
        this.ICS = ICS;
    }

    public String getMessageBroker() {
        return messageBroker;
    }

    public void setMessageBroker(String messageBroker) {
        this.messageBroker = messageBroker;
    }

    public String getTibco() {
        return tibco;
    }

    public void setTibco(String tibco) {
        this.tibco = tibco;
    }

    public String getVitria() {
        return vitria;
    }

    public void setVitria(String vitria) {
        this.vitria = vitria;
    }

    public String getWPS() {
        return WPS;
    }

    public void setWPS(String WPS) {
        this.WPS = WPS;
    }

    public String getBiztalkServer() {
        return biztalkServer;
    }

    public void setBiztalkServer(String biztalkServer) {
        this.biztalkServer = biztalkServer;
    }

    public String getJdEdwards() {
        return jdEdwards;
    }

    public void setJdEdwards(String jdEdwards) {
        this.jdEdwards = jdEdwards;
    }

    public String getOracleApps() {
        return oracleApps;
    }

    public void setOracleApps(String oracleApps) {
        this.oracleApps = oracleApps;
    }

    public String getPeopleSoft() {
        return peopleSoft;
    }

    public void setPeopleSoft(String peopleSoft) {
        this.peopleSoft = peopleSoft;
    }

    public String getSAP() {
        return SAP;
    }

    public void setSAP(String SAP) {
        this.SAP = SAP;
    }

    public String getSiebel() {
        return siebel;
    }

    public void setSiebel(String siebel) {
        this.siebel = siebel;
    }

    public String getBaan() {
        return baan;
    }

    public void setBaan(String baan) {
        this.baan = baan;
    }

    public String getBeaPortals() {
        return beaPortals;
    }

    public void setBeaPortals(String beaPortals) {
        this.beaPortals = beaPortals;
    }

    public String getOraclePortals() {
        return oraclePortals;
    }

    public void setOraclePortals(String oraclePortals) {
        this.oraclePortals = oraclePortals;
    }

    public String getIbmPortals() {
        return ibmPortals;
    }

    public void setIbmPortals(String ibmPortals) {
        this.ibmPortals = ibmPortals;
    }

    public String getSharePoint() {
        return sharePoint;
    }

    public void setSharePoint(String sharePoint) {
        this.sharePoint = sharePoint;
    }

    public String getSapPortals() {
        return sapPortals;
    }

    public void setSapPortals(String sapPortals) {
        this.sapPortals = sapPortals;
    }

    public String getMicrosoft() {
        return microsoft;
    }

    public void setMicrosoft(String microsoft) {
        this.microsoft = microsoft;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getContId() {
        return contId;
    }

    public void setContId(String contId) {
        this.contId = contId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public String getAssignedToId() {
        return assignedToId;
    }

    public void setAssignedToId(String assignedToId) {
        this.assignedToId = assignedToId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAlarm() {
        return alarm;
    }

    public void setAlarm(String alarm) {
        this.alarm = alarm;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getActivId() {
        return activId;
    }

    public void setActivId(String activId) {
        this.activId = activId;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getDueDates() {
        return dueDates;
    }

    public void setDueDates(Timestamp dueDates) {
        this.dueDates = dueDates;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getConsultantName() {
        return consultantName;
    }

    public void setConsultantName(String consultantName) {
        this.consultantName = consultantName;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /*  public String getEventDate() {
    return eventDate;
    }
    
    public void setEventDate(String eventDate) {
    this.eventDate = eventDate;
    }*/
    public String getSkillset() {
        return skillset;
    }

    public void setSkillset(String skillset) {
        this.skillset = skillset;
    }

    public String getPracticeid() {
        return practiceid;
    }

    public void setPracticeid(String practiceid) {
        this.practiceid = practiceid;
    }

    public String getConsultantMail() {
        return consultantMail;
    }

    public void setConsultantMail(String consultantMail) {
        this.consultantMail = consultantMail;
    }

    public String getConsultantEmail() {
        return consultantEmail;
    }

    public void setConsultantEmail(String consultantEmail) {
        this.consultantEmail = consultantEmail;
    }

    public int getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(int consultantId) {
        this.consultantId = consultantId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWorkAuthor() {
        return workAuthor;
    }

    public void setWorkAuthor(String workAuthor) {
        this.workAuthor = workAuthor;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getWeekStartDate() {
        return weekStartDate;
    }

    public void setWeekStartDate(Date weekStartDate) {
        this.weekStartDate = weekStartDate;
    }

    public Date getWeekEndDate() {
        return weekEndDate;
    }

    public void setWeekEndDate(Date weekEndDate) {
        this.weekEndDate = weekEndDate;
    }

    public java.util.Date getWeekDate() {
        return weekDate;
    }

    public void setWeekDate(java.util.Date weekDate) {
        this.weekDate = weekDate;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventYearMonth() {
        return eventYearMonth;
    }

    public void setEventYearMonth(String eventYearMonth) {
        this.eventYearMonth = eventYearMonth;
    }

    public int getAccTeamId() {
        return accTeamId;
    }

    public void setAccTeamId(int accTeamId) {
        this.accTeamId = accTeamId;
    }

    public String getCalEventDate() {
        return CalEventDate;
    }

    public void setCalEventDate(String CalEventDate) {
        this.CalEventDate = CalEventDate;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    /*   public String getAccName() {
    return accName;
    }
    
    public void setAccName(String accName) {
    this.accName = accName;
    }*/
    /*   public String getContactName() {
    return contactName;
    }
    
    public void setContactName(String contactName) {
    this.contactName = contactName;
    }*/
    public String getAccTeam() {
        return accTeam;
    }

    public void setAccTeam(String accTeam) {
        this.accTeam = accTeam;
    }

    public String getEventDesc() {
        return EventDesc;
    }

    public void setEventDesc(String EventDesc) {
        this.EventDesc = EventDesc;
    }

//    public String getCreatedBy() {
//        return createdBy;
//    }
//
//    public void setCreatedBy(String createdBy) {
//        this.createdBy = createdBy;
//    }
    public String getCurrentCaluserId() {
        return currentCaluserId;
    }

    public void setCurrentCaluserId(String currentCaluserId) {
        this.currentCaluserId = currentCaluserId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getSaveType() {
        return saveType;
    }

    public void setSaveType(String saveType) {
        this.saveType = saveType;
    }

    public int getCalEveId1() {
        return calEveId1;
    }

    public void setCalEveId1(int calEveId1) {

        this.calEveId1 = calEveId1;

    }

    public String getCalEveId() {
        return calEveId;
    }

    public void setCalEveId(String calEveId) {
        this.calEveId = calEveId;
        this.setCalEveId1(Integer.parseInt(calEveId));
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public String getEveAccId() {
        return EveAccId;
    }

    public void setEveAccId(String EveAccId) {
        this.EveAccId = EveAccId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactsId() {
        return contactsId;
    }

    public void setContactsId(String contactsId) {
        this.contactsId = contactsId;
    }

    public int getEventAssigenedIds() {
        return eventAssigenedIds;
    }

    public void setEventAssigenedIds(int eventAssigenedIds) {
        this.eventAssigenedIds = eventAssigenedIds;
    }

    public String getSkils() {
        return skils;
    }

    public void setSkils(String skils) {
        this.skils = skils;
    }

    public String getCalAccessUserId() {
        return calAccessUserId;
    }

    public void setCalAccessUserId(String calAccessUserId) {
        this.calAccessUserId = calAccessUserId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccessUserId() {
        return accessUserId;
    }

    public void setAccessUserId(String accessUserId) {
        this.accessUserId = accessUserId;
    }

    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public String getCalUesrId() {
        return calUesrId;
    }

    public void setCalUesrId(String calUesrId) {
        this.calUesrId = calUesrId;
    }

    public String getCalEventDate1() {
        return CalEventDate1;
    }

    public void setCalEventDate1(String CalEventDate1) {
        this.CalEventDate1 = CalEventDate1;
    }

    public Date getSdate() {
        return sdate;
    }

    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getUsableDays() {
        return usableDays;
    }

    public void setUsableDays(String usableDays) {
        this.usableDays = usableDays;
    }

    public int getUsableTeamHours() {
        return usableTeamHours;
    }

    public void setUsableTeamHours(int usableTeamHours) {
        this.usableTeamHours = usableTeamHours;
    }

    public String getPoStatus() {
        return poStatus;
    }

    public void setPoStatus(String poStatus) {
        this.poStatus = poStatus;
    }

    public String getPoType() {
        return poType;
    }

    public void setPoType(String poType) {
        this.poType = poType;
    }

    public String getEmpFname() {
        return empFname;
    }

    public void setEmpFname(String empFname) {
        this.empFname = empFname;
    }

    public String getEmpLname() {
        return empLname;
    }

    public void setEmpLname(String empLname) {
        this.empLname = empLname;
    }

    public String getPoStartDateFrom() {
        return poStartDateFrom;
    }

    public void setPoStartDateFrom(String poStartDateFrom) {
        this.poStartDateFrom = poStartDateFrom;
    }

    public String getPoStartDateTo() {
        return poStartDateTo;
    }

    public void setPoStartDateTo(String poStartDateTo) {
        this.poStartDateTo = poStartDateTo;
    }

    public String getPoEndDateFrom() {
        return poEndDateFrom;
    }

    public void setPoEndDateFrom(String poEndDateFrom) {
        this.poEndDateFrom = poEndDateFrom;
    }

    public String getPoEndDateTo() {
        return poEndDateTo;
    }

    public void setPoEndDateTo(String poEndDateTo) {
        this.poEndDateTo = poEndDateTo;
    }

    public String getEmpStartDateFrom() {
        return empStartDateFrom;
    }

    public void setEmpStartDateFrom(String empStartDateFrom) {
        this.empStartDateFrom = empStartDateFrom;
    }

    public String getEmpStartDateTo() {
        return empStartDateTo;
    }

    public void setEmpStartDateTo(String empStartDateTo) {
        this.empStartDateTo = empStartDateTo;
    }

    public String getEmpEndDateFrom() {
        return empEndDateFrom;
    }

    public void setEmpEndDateFrom(String empEndDateFrom) {
        this.empEndDateFrom = empEndDateFrom;
    }

    public String getEmpEndDateTo() {
        return empEndDateTo;
    }

    public void setEmpEndDateTo(String empEndDateTo) {
        this.empEndDateTo = empEndDateTo;
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public Date getVenusStaDate() {
        return venusStaDate;
    }

    public void setVenusStaDate(Date venusStaDate) {
        this.venusStaDate = venusStaDate;
    }

    public Date getVenusEndDate() {
        return venusEndDate;
    }

    public void setVenusEndDate(Date venusEndDate) {
        this.venusEndDate = venusEndDate;
    }

    public String getVenusDeptId() {
        return venusDeptId;
    }

    public void setVenusDeptId(String venusDeptId) {
        this.venusDeptId = venusDeptId;
    }

    public String getVenusEmpName() {
        return venusEmpName;
    }

    public void setVenusEmpName(String venusEmpName) {
        this.venusEmpName = venusEmpName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getDueStartDate() {
        return dueStartDate;
    }

    public void setDueStartDate(String dueStartDate) {
        this.dueStartDate = dueStartDate;
    }

    public String getDueEndDate() {
        return dueEndDate;
    }

    public void setDueEndDate(String dueEndDate) {
        this.dueEndDate = dueEndDate;
    }

    public Map getMyTeamMembers() {
        return myTeamMembers;
    }

    public void setMyTeamMembers(Map myTeamMembers) {
        this.myTeamMembers = myTeamMembers;
    }

    public Date getActivityStaDate() {
        return activityStaDate;
    }

    public void setActivityStaDate(Date activityStaDate) {
        this.activityStaDate = activityStaDate;
    }

    public Date getActivityEndDate() {
        return activityEndDate;
    }

    public void setActivityEndDate(Date activityEndDate) {
        this.activityEndDate = activityEndDate;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getTimeSheetWeekStartDate() {
        return timeSheetWeekStartDate;
    }

    public void setTimeSheetWeekStartDate(String timeSheetWeekStartDate) {
        this.timeSheetWeekStartDate = timeSheetWeekStartDate;
    }

    public String getTimeSheetWeekEndDate() {
        return timeSheetWeekEndDate;
    }

    public void setTimeSheetWeekEndDate(String timeSheetWeekEndDate) {
        this.timeSheetWeekEndDate = timeSheetWeekEndDate;
    }

    public String getNotApprovedDepartmentId() {
        return notApprovedDepartmentId;
    }

    public void setNotApprovedDepartmentId(String notApprovedDepartmentId) {
        this.notApprovedDepartmentId = notApprovedDepartmentId;
    }

    public String getNotApprovedStartDate() {
        return notApprovedStartDate;
    }

    public void setNotApprovedStartDate(String notApprovedStartDate) {
        this.notApprovedStartDate = notApprovedStartDate;
    }

    public String getNotApprovedEndDate() {
        return notApprovedEndDate;
    }

    public void setNotApprovedEndDate(String notApprovedEndDate) {
        this.notApprovedEndDate = notApprovedEndDate;
    }

    public String getContactSearchName() {
        return contactSearchName;
    }

    public void setContactSearchName(String contactSearchName) {
        this.contactSearchName = contactSearchName;
    }

    public int getConAccId() {
        return conAccId;
    }

    public void setConAccId(int conAccId) {
        this.conAccId = conAccId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getAccountName2() {
        return accountName2;
    }

    public void setAccountName2(String accountName2) {
        this.accountName2 = accountName2;
    }

    public int getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(int requirementId) {
        this.requirementId = requirementId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(int String) {
        this.personId = personId;
    }

    public String getRecruiterId() {
        return recruiterId;
    }

    public void setRecruiterId(String recruiterId) {
        this.recruiterId = recruiterId;
    }

    public String getDashBoardStartDateRep() {
        return dashBoardStartDateRep;
    }

    public void setDashBoardStartDateRep(String dashBoardStartDateRep) {
        this.dashBoardStartDateRep = dashBoardStartDateRep;
    }

    public String getDashBoardEndDateRep() {
        return dashBoardEndDateRep;
    }

    public void setDashBoardEndDateRep(String dashBoardEndDateRep) {
        this.dashBoardEndDateRep = dashBoardEndDateRep;
    }

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
    //New Getters and Setters for AccountId 

    public void setActAccId(int actAccId) {
        this.actAccId = actAccId;
    }

    public int getActAccId() {
        return actAccId;
    }

    public String getActivitySearchName() {
        return activitySearchName;
    }

    public void setActivitySearchName(String activitySearchName) {
        this.activitySearchName = activitySearchName;
    }
    //New 

    public void setCurcontactId(int curcontactId) {
        this.curcontactId = curcontactId;
    }

    public int getCurcontactId() {
        return curcontactId;
    }

    //New 
    public void setLeaveId(int leaveId) {
        this.leaveId = leaveId;
    }

    public int getLeaveId() {
        return leaveId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    //New for ReqList search
    public String getPostedDate1() {
        return postedDate1;
    }

    public void setPostedDate1(String postedDate1) {
        this.postedDate1 = postedDate1;
    }

    public String getPostedDate2() {
        return postedDate2;
    }

    public void setPostedDate2(String postedDate2) {
        this.postedDate2 = postedDate2;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    /**
     *setters and getters for states 1 - 5 for assign accounts for BDM
     */
    public String getState1() {
        return state1;
    }

    public void setState1(String state1) {
        this.state1 = state1;
    }

    public String getState2() {
        return state2;
    }

    public void setState2(String state2) {
        this.state2 = state2;
    }

    public String getState3() {
        return state3;
    }

    public void setState3(String state3) {
        this.state3 = state3;
    }

    public String getState4() {
        return state4;
    }

    public void setState4(String state4) {
        this.state4 = state4;
    }

    public String getState5() {
        return state5;
    }

    public void setState5(String state5) {
        this.state5 = state5;
    }

    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getDomainId() {
        return domainId;
    }

    public void setDomainId(int domainId) {
        this.domainId = domainId;
    }

    public int getSubtopidId() {
        return subtopidId;
    }

    public void setSubtopidId(int subtopidId) {
        this.subtopidId = subtopidId;
    }

    public int getQuestionNo() {
        return questionNo;
    }

    public void setQuestionNo(int questionNo) {
        this.questionNo = questionNo;
    }

    public int getSelectedAns() {
        return selectedAns;
    }

    public void setSelectedAns(int selectedAns) {
        this.selectedAns = selectedAns;
    }

    public String getNavigation() {
        return navigation;
    }

    public void setNavigation(String navigation) {
        this.navigation = navigation;
    }

    public int getRemainingQuestions() {
        return remainingQuestions;
    }

    public void setRemainingQuestions(int remainingQuestions) {
        this.remainingQuestions = remainingQuestions;
    }

    public int getOnClickStatus() {
        return onClickStatus;
    }

    public void setOnClickStatus(int onClickStatus) {
        this.onClickStatus = onClickStatus;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public String getSubTopic() {
        return subTopic;
    }

    public void setSubTopic(String subTopic) {
        this.subTopic = subTopic;
    }

    public int getSubTopicId() {
        return subTopicId;
    }

    public void setSubTopicId(int subTopicId) {
        this.subTopicId = subTopicId;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public String getExamValidationKey() {
        return examValidationKey;
    }

    public void setExamValidationKey(String examValidationKey) {
        this.examValidationKey = examValidationKey;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getCreConsultantId() {
        return creConsultantId;
    }

    public void setCreConsultantId(String creConsultantId) {
        this.creConsultantId = creConsultantId;
    }

    public String getCreConsultantLevel() {
        return creConsultantLevel;
    }

    public void setCreConsultantLevel(String creConsultantLevel) {
        this.creConsultantLevel = creConsultantLevel;
    }

    public String getCreConsultantStatus() {
        return creConsultantStatus;
    }

    public void setCreConsultantStatus(String creConsultantStatus) {
        this.creConsultantStatus = creConsultantStatus;
    }

    public String getCreStartDate() {
        return creStartDate;
    }

    public void setCreStartDate(String creStartDate) {
        this.creStartDate = creStartDate;
    }

    public String getCreToDate() {
        return creToDate;
    }

    public void setCreToDate(String creToDate) {
        this.creToDate = creToDate;
    }

    public String getCreConsultantName() {
        return creConsultantName;
    }

    public void setCreConsultantName(String creConsultantName) {
        this.creConsultantName = creConsultantName;
    }

    public int getTechReviewId() {
        return techReviewId;
    }

    public void setTechReviewId(int techReviewId) {
        this.techReviewId = techReviewId;
    }

    public int getHrReviewId() {
        return hrReviewId;
    }

    public void setHrReviewId(int hrReviewId) {
        this.hrReviewId = hrReviewId;
    }

    public int getVpReviewId() {
        return vpReviewId;
    }

    public void setVpReviewId(int vpReviewId) {
        this.vpReviewId = vpReviewId;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getSubTopicsList() {
        return subTopicsList;
    }

    public void setSubTopicsList(String subTopicsList) {
        this.subTopicsList = subTopicsList;
    }

    /**
     * @return the totalQues
     */
    public int getTotalQues() {
        return totalQues;
    }

    /**
     * @param totalQues the totalQues to set
     */
    public void setTotalQues(int totalQues) {
        this.totalQues = totalQues;
    }

    /**
     * @return the totDuration
     */
    public int getTotDuration() {
        return totDuration;
    }

    /**
     * @param totDuration the totDuration to set
     */
    public void setTotDuration(int totDuration) {
        this.totDuration = totDuration;
    }

    /**
     * @return the minMarks
     */
    public int getMinMarks() {
        return minMarks;
    }

    /**
     * @param minMarks the minMarks to set
     */
    public void setMinMarks(int minMarks) {
        this.minMarks = minMarks;
    }

    /**
     * @return the examKeyId
     */
    public String getExamKeyId() {
        return examKeyId;
    }

    /**
     * @param examKeyId the examKeyId to set
     */
    public void setExamKeyId(String examKeyId) {
        this.examKeyId = examKeyId;
    }

    /**
     * @return the empId
     */
    public String getEmpId() {
        return empId;
    }

    /**
     * @param empId the empId to set
     */
    public void setEmpId(String empId) {
        this.empId = empId;
    }

    /**
     * @return the topicName
     */
    public String getTopicName() {
        return topicName;
    }

    /**
     * @param topicName the topicName to set
     */
    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    /**
     * @return the teamMemberId
     */
    public String getTeamMemberId() {
        return teamMemberId;
    }

    /**
     * @param teamMemberId the teamMemberId to set
     */
    public void setTeamMemberId(String teamMemberId) {
        this.teamMemberId = teamMemberId;
    }

    /**
     * @return the delStateAcc
     */
    public String getDelStateAcc() {
        return delStateAcc;
    }

    /**
     * @param delStateAcc the delStateAcc to set
     */
    public void setDelStateAcc(String delStateAcc) {
        this.delStateAcc = delStateAcc;
    }

    /**
     * @return the reportsTo
     */
    public String getReportsTo() {
        return reportsTo;
    }

    /**
     * @param reportsTo the reportsTo to set
     */
    public void setReportsTo(String reportsTo) {
        this.reportsTo = reportsTo;
    }

    /**
     * @return the totalAccounts
     */
    public int getTotalAccounts() {
        return totalAccounts;
    }

    /**
     * @param totalAccounts the totalAccounts to set
     */
    public void setTotalAccounts(int totalAccounts) {
        this.totalAccounts = totalAccounts;
    }

    /**
     * @return the noOfActivities
     */
    public int getNoOfActivities() {
        return noOfActivities;
    }

    /**
     * @param noOfActivities the noOfActivities to set
     */
    public void setNoOfActivities(int noOfActivities) {
        this.noOfActivities = noOfActivities;
    }

    /**
     * @return the workedAccounts
     */
    public int getWorkedAccounts() {
        return workedAccounts;
    }

    /**
     * @param workedAccounts the workedAccounts to set
     */
    public void setWorkedAccounts(int workedAccounts) {
        this.workedAccounts = workedAccounts;
    }

    /**
     * @return the frmLoginId
     */
    public String getFrmLoginId() {
        return frmLoginId;
    }

    /**
     * @param frmLoginId the frmLoginId to set
     */
    public void setFrmLoginId(String frmLoginId) {
        this.frmLoginId = frmLoginId;
    }

    /**
     * @return the toLoginId
     */
    public String getToLoginId() {
        return toLoginId;
    }

    /**
     * @param toLoginId the toLoginId to set
     */
    public void setToLoginId(String toLoginId) {
        this.toLoginId = toLoginId;
    }

    /**
     * @return the level
     */
    public String getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * @return the interviewAt
     */
    public String getInterviewAt() {
        return interviewAt;
    }

    /**
     * @param interviewAt the interviewAt to set
     */
    public void setInterviewAt(String interviewAt) {
        this.interviewAt = interviewAt;
    }

    /**
     * @return the lastActivityDate
     */
    public String getLastActivityDate() {
        return lastActivityDate;
    }

    /**
     * @param lastActivityDate the lastActivityDate to set
     */
    public void setLastActivityDate(String lastActivityDate) {
        this.lastActivityDate = lastActivityDate;
    }

    /**
     * @return the teamName
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * @param teamName the teamName to set
     */
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    /**
     * @return the examType
     */
    public String getExamType() {
        return examType;
    }

    /**
     * @param examType the examType to set
     */
    public void setExamType(String examType) {
        this.examType = examType;
    }

    /**
     * @return the ExamNameId
     */
    public int getExamNameId() {
        return ExamNameId;
    }

    /**
     * @param ExamNameId the ExamNameId to set
     */
    public void setExamNameId(int ExamNameId) {
        this.ExamNameId = ExamNameId;
    }

    /**
     * @return the ExamNameIdList
     */
    public String getExamNameIdList() {
        return ExamNameIdList;
    }

    /**
     * @param ExamNameIdList the ExamNameIdList to set
     */
    public void setExamNameIdList(String ExamNameIdList) {
        this.ExamNameIdList = ExamNameIdList;
    }

    /**
     * @return the specficQuestionNo
     */
    public int getSpecficQuestionNo() {
        return specficQuestionNo;
    }

    /**
     * @param specficQuestionNo the specficQuestionNo to set
     */
    public void setSpecficQuestionNo(int specficQuestionNo) {
        this.specficQuestionNo = specficQuestionNo;
    }

    /**
     * @return the creConsultantId1
     */
    public String getCreConsultantId1() {
        return creConsultantId1;
    }

    /**
     * @param creConsultantId1 the creConsultantId1 to set
     */
    public void setCreConsultantId1(String creConsultantId1) {
        this.creConsultantId1 = creConsultantId1;
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
     * @return the onboardReviewId
     */
    public int getOnboardReviewId() {
        return onboardReviewId;
    }

    /**
     * @param onboardReviewId the onboardReviewId to set
     */
    public void setOnboardReviewId(int onboardReviewId) {
        this.onboardReviewId = onboardReviewId;
    }

    public String getTechName() {
        return techName;
    }

    public void setTechName(String techName) {
        this.techName = techName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReferTo() {
        return referTo;
    }

    public void setReferTo(String referTo) {
        this.referTo = referTo;
    }

    public String getIssueRel() {
        return issueRel;
    }

    public void setIssueRel(String issueRel) {
        this.issueRel = issueRel;
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

    public String getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(String maxValue) {
        this.maxValue = maxValue;
    }

    public String getMetricId() {
        return metricId;
    }

    public void setMetricId(String metricId) {
        this.metricId = metricId;
    }

    public String getMetricName() {
        return metricName;
    }

    public void setMetricName(String metricName) {
        this.metricName = metricName;
    }

    public String getMinValue() {
        return minValue;
    }

    public void setMinValue(String minValue) {
        this.minValue = minValue;
    }

    public String getPerfId() {
        return perfId;
    }

    public void setPerfId(String perfId) {
        this.perfId = perfId;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getTitleId() {
        return titleId;
    }

    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }

    public String getWeightage() {
        return weightage;
    }

    public void setWeightage(String weightage) {
        this.weightage = weightage;
    }

    public String getStringdata() {
        return stringdata;
    }

    public void setStringdata(String stringdata) {
        this.stringdata = stringdata;
    }

    /**
     * @return the teamType
     */
    public int getTeamType() {
        return teamType;
    }

    /**
     * @param teamType the teamType to set
     */
    public void setTeamType(int teamType) {
        this.teamType = teamType;
    }

    /**
     * @return the empCusType
     */
    public int getEmpCusType() {
        return empCusType;
    }

    /**
     * @param empCusType the empCusType to set
     */
    public void setEmpCusType(int empCusType) {
        this.empCusType = empCusType;
    }

    /**
     * @return the file
     */
    public File getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(File file) {
        this.file = file;
    }

    /**
     * @return the fileFileName
     */
    public String getFileFileName() {
        return fileFileName;
    }

    /**
     * @param fileFileName the fileFileName to set
     */
    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    /**
     * @return the fileFileContentType
     */
    public String getFileFileContentType() {
        return fileFileContentType;
    }

    /**
     * @param fileFileContentType the fileFileContentType to set
     */
    public void setFileFileContentType(String fileFileContentType) {
        this.fileFileContentType = fileFileContentType;
    }

    /**
     * @return the generatedPath
     */
    public String getGeneratedPath() {
        return generatedPath;
    }

    /**
     * @param generatedPath the generatedPath to set
     */
    public void setGeneratedPath(String generatedPath) {
        this.generatedPath = generatedPath;
    }

    /**
     * @return the attachmentService
     */
    public AttachmentService getAttachmentService() {
        return attachmentService;
    }

    /**
     * @param attachmentService the attachmentService to set
     */
    public void setAttachmentService(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    /**
     * @return the fileLocation
     */
    public String getFileLocation() {
        return fileLocation;
    }

    /**
     * @param fileLocation the fileLocation to set
     */
    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
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
     * @return the objectType
     */
    public String getObjectType() {
        return objectType;
    }

    /**
     * @param objectType the objectType to set
     */
    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    /**
     * @return the attachmentLocation
     */
    public String getAttachmentLocation() {
        return attachmentLocation;
    }

    /**
     * @param attachmentLocation the attachmentLocation to set
     */
    public void setAttachmentLocation(String attachmentLocation) {
        this.attachmentLocation = attachmentLocation;
    }

    /**
     * @return the overlayReviewType
     */
    public String getOverlayReviewType() {
        return overlayReviewType;
    }

    /**
     * @param overlayReviewType the overlayReviewType to set
     */
    public void setOverlayReviewType(String overlayReviewType) {
        this.overlayReviewType = overlayReviewType;
    }

    /**
     * @return the overlayReviewName
     */
    public String getOverlayReviewName() {
        return overlayReviewName;
    }

    /**
     * @param overlayReviewName the overlayReviewName to set
     */
    public void setOverlayReviewName(String overlayReviewName) {
        this.overlayReviewName = overlayReviewName;
    }

    /**
     * @return the overlayReviewDate
     */
    public String getOverlayReviewDate() {
        return overlayReviewDate;
    }

    /**
     * @param overlayReviewDate the overlayReviewDate to set
     */
    public void setOverlayReviewDate(String overlayReviewDate) {
        this.overlayReviewDate = overlayReviewDate;
    }

    /**
     * @return the overlayDescription
     */
    public String getOverlayDescription() {
        return overlayDescription;
    }

    /**
     * @param overlayDescription the overlayDescription to set
     */
    public void setOverlayDescription(String overlayDescription) {
        this.overlayDescription = overlayDescription;
    }

    /**
     * @return the empTitle
     */
    public String getEmpTitle() {
        return empTitle;
    }

    /**
     * @param empTitle the empTitle to set
     */
    public void setEmpTitle(String empTitle) {
        this.empTitle = empTitle;
    }

    /**
     * @return the reviewStatusOverlay
     */
    public String getReviewStatusOverlay() {
        return reviewStatusOverlay;
    }

    /**
     * @param reviewStatusOverlay the reviewStatusOverlay to set
     */
    public void setReviewStatusOverlay(String reviewStatusOverlay) {
        this.reviewStatusOverlay = reviewStatusOverlay;
    }

    /**
     * @return the overlayReviewCreatedDate
     */
    public String getOverlayReviewCreatedDate() {
        return overlayReviewCreatedDate;
    }

    /**
     * @param overlayReviewCreatedDate the overlayReviewCreatedDate to set
     */
    public void setOverlayReviewCreatedDate(String overlayReviewCreatedDate) {
        this.overlayReviewCreatedDate = overlayReviewCreatedDate;
    }

    /**
     * @return the modifiedBy
     */
    public String getModifiedBy() {
        return modifiedBy;
    }

    /**
     * @param modifiedBy the modifiedBy to set
     */
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    /**
     * @return the reviewId
     */
    public String getReviewId() {
        return reviewId;
    }

    /**
     * @param reviewId the reviewId to set
     */
    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    /**
     * @return the tlComments
     */
    public String getTlComments() {
        return tlComments;
    }

    /**
     * @param tlComments the tlComments to set
     */
    public void setTlComments(String tlComments) {
        this.tlComments = tlComments;
    }

    /**
     * @return the reviewTypeId
     */
    public int getReviewTypeId() {
        return reviewTypeId;
    }

    /**
     * @param reviewTypeId the reviewTypeId to set
     */
    public void setReviewTypeId(int reviewTypeId) {
        this.reviewTypeId = reviewTypeId;
    }

    /**
     * @return the leadRating
     */
    public int getLeadRating() {
        return leadRating;
    }

    /**
     * @param leadRating the leadRating to set
     */
    public void setLeadRating(int leadRating) {
        this.leadRating = leadRating;
    }

    /**
     * @return the hrRating
     */
    public int getHrRating() {
        return hrRating;
    }

    /**
     * @param hrRating the hrRating to set
     */
    public void setHrRating(int hrRating) {
        this.hrRating = hrRating;
    }

    /**
     * @return the maxPoints
     */
    public int getMaxPoints() {
        return maxPoints;
    }

    /**
     * @param maxPoints the maxPoints to set
     */
    public void setMaxPoints(int maxPoints) {
        this.maxPoints = maxPoints;
    }

    /**
     * @return the hrComments
     */
    public String getHrComments() {
        return hrComments;
    }

    /**
     * @param hrComments the hrComments to set
     */
    public void setHrComments(String hrComments) {
        this.hrComments = hrComments;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the addType
     */
    public String getAddType() {
        return addType;
    }

    /**
     * @param addType the addType to set
     */
    public void setAddType(String addType) {
        this.addType = addType;
    }

    /**
     * @return the roleName
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * @param roleName the roleName to set
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getEndDateSummaryGraph() {
        return endDateSummaryGraph;
    }

    public void setEndDateSummaryGraph(String endDateSummaryGraph) {
        this.endDateSummaryGraph = endDateSummaryGraph;
    }

    public String getStartDateSummaryGraph() {
        return startDateSummaryGraph;
    }

    public void setStartDateSummaryGraph(String startDateSummaryGraph) {
        this.startDateSummaryGraph = startDateSummaryGraph;
    }

    public String getDuplicate() {
        return duplicate;
    }

    public void setDuplicate(String duplicate) {
        this.duplicate = duplicate;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    /**
     * @return the jobtitle
     */
    public String getJobtitle() {
        return jobtitle;
    }

    /**
     * @param jobtitle the jobtitle to set
     */
    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    /**
     * @return the jobtagline
     */
    public String getJobtagline() {
        return jobtagline;
    }

    /**
     * @param jobtagline the jobtagline to set
     */
    public void setJobtagline(String jobtagline) {
        this.jobtagline = jobtagline;
    }

    /**
     * @return the jobposition
     */
    public String getJobposition() {
        return jobposition;
    }

    /**
     * @param jobposition the jobposition to set
     */
    public void setJobposition(String jobposition) {
        this.jobposition = jobposition;
    }

    /**
     * @return the jobqulification
     */
    public String getJobqulification() {
        return jobqulification;
    }

    /**
     * @param jobqulification the jobqulification to set
     */
    public void setJobqulification(String jobqulification) {
        this.jobqulification = jobqulification;
    }

    /**
     * @return the jobcountry
     */
    public String getJobcountry() {
        return jobcountry;
    }

    /**
     * @param jobcountry the jobcountry to set
     */
    public void setJobcountry(String jobcountry) {
        this.jobcountry = jobcountry;
    }

    /**
     * @return the jobshifits
     */
    public String getJobshifits() {
        return jobshifits;
    }

    /**
     * @param jobshifits the jobshifits to set
     */
    public void setJobshifits(String jobshifits) {
        this.jobshifits = jobshifits;
    }

    /**
     * @return the jobstatus
     */
    public String getJobstatus() {
        return jobstatus;
    }

    /**
     * @param jobstatus the jobstatus to set
     */
    public void setJobstatus(String jobstatus) {
        this.jobstatus = jobstatus;
    }

    /**
     * @return the jobdescription
     */
    public String getJobdescription() {
        return jobdescription;
    }

    /**
     * @param jobdescription the jobdescription to set
     */
    public void setJobdescription(String jobdescription) {
        this.jobdescription = jobdescription;
    }

    /**
     * @return the jobtechnical
     */
    public String getJobtechnical() {
        return jobtechnical;
    }

    /**
     * @param jobtechnical the jobtechnical to set
     */
    public void setJobtechnical(String jobtechnical) {
        this.jobtechnical = jobtechnical;
    }

    /**
     * @return the jobshiftskills
     */
    public String getJobshiftskills() {
        return jobshiftskills;
    }

    /**
     * @param jobshiftskills the jobshiftskills to set
     */
    public void setJobshiftskills(String jobshiftskills) {
        this.jobshiftskills = jobshiftskills;
    }

    /**
     * @return the jobId
     */
    public String getJobId() {
        return jobId;
    }

    /**
     * @param jobId the jobId to set
     */
    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    /**
     * @return the applicantId
     */
    public int getApplicantId() {
        return applicantId;
    }

    /**
     * @param applicantId the applicantId to set
     */
    public void setApplicantId(int applicantId) {
        this.applicantId = applicantId;
    }

    /**
     * @return the currId
     */
    public String getCurrId() {
        return currId;
    }

    /**
     * @param currId the currId to set
     */
    public void setCurrId(String currId) {
        this.currId = currId;
    }

    /**
     * @return the projectStartDate
     */
    public String getProjectStartDate() {
        return projectStartDate;
    }

    /**
     * @param projectStartDate the projectStartDate to set
     */
    public void setProjectStartDate(String projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    /**
     * @return the isManagerInclude
     */
    public boolean getIsManagerInclude() {
        return isManagerInclude;
    }

    /**
     * @param isManagerInclude the isManagerInclude to set
     */
    public void setIsManagerInclude(boolean isManagerInclude) {
        this.isManagerInclude = isManagerInclude;
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
     * @return the empNo
     */
    public String getEmpNo() {
        return empNo;
    }

    /**
     * @param empNo the empNo to set
     */
    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    /**
     * @return the reviewOnsiteMgrStatusOverlay
     */
    public String getReviewOnsiteMgrStatusOverlay() {
        return reviewOnsiteMgrStatusOverlay;
    }

    /**
     * @param reviewOnsiteMgrStatusOverlay the reviewOnsiteMgrStatusOverlay to set
     */
    public void setReviewOnsiteMgrStatusOverlay(String reviewOnsiteMgrStatusOverlay) {
        this.reviewOnsiteMgrStatusOverlay = reviewOnsiteMgrStatusOverlay;
    }

    /**
     * @return the reviewHrStatusOverlay
     */
    public String getReviewHrStatusOverlay() {
        return reviewHrStatusOverlay;
    }

    /**
     * @param reviewHrStatusOverlay the reviewHrStatusOverlay to set
     */
    public void setReviewHrStatusOverlay(String reviewHrStatusOverlay) {
        this.reviewHrStatusOverlay = reviewHrStatusOverlay;
    }

    /**
     * @return the onsiteMgrComments
     */
    public String getOnsiteMgrComments() {
        return onsiteMgrComments;
    }

    /**
     * @param onsiteMgrComments the onsiteMgrComments to set
     */
    public void setOnsiteMgrComments(String onsiteMgrComments) {
        this.onsiteMgrComments = onsiteMgrComments;
    }

    /**
     * @return the onsiteMgrRating
     */
    public int getOnsiteMgrRating() {
        return onsiteMgrRating;
    }

    /**
     * @param onsiteMgrRating the onsiteMgrRating to set
     */
    public void setOnsiteMgrRating(int onsiteMgrRating) {
        this.onsiteMgrRating = onsiteMgrRating;
    }

    /**
     * @return the manager
     */
    public int getManager() {
        return manager;
    }

    /**
     * @param manager the manager to set
     */
    public void setManager(int manager) {
        this.manager = manager;
    }

    /**
     * @return the managerCountry
     */
    public String getManagerCountry() {
        return managerCountry;
    }

    /**
     * @param managerCountry the managerCountry to set
     */
    public void setManagerCountry(String managerCountry) {
        this.managerCountry = managerCountry;
    }

    /**
     * @return the ApproverComments
     */
    public String getApproverComments() {
        return ApproverComments;
    }

    /**
     * @param ApproverComments the ApproverComments to set
     */
    public void setApproverComments(String ApproverComments) {
        this.ApproverComments = ApproverComments;
    }

    /**
     * @return the reviewStatus
     */
    public String getReviewStatus() {
        return reviewStatus;
    }

    /**
     * @param reviewStatus the reviewStatus to set
     */
    public void setReviewStatus(String reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    /**
     * @return the rating
     */
    public int getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * @return the overlayReviewBilling
     */
    public double getOverlayReviewBilling() {
        return overlayReviewBilling;
    }

    /**
     * @param overlayReviewBilling the overlayReviewBilling to set
     */
    public void setOverlayReviewBilling(double overlayReviewBilling) {
        this.overlayReviewBilling = overlayReviewBilling;
    }

    /**
     * @return the overlayReviewLogAdded
     */
    public int getOverlayReviewLogAdded() {
        return overlayReviewLogAdded;
    }

    /**
     * @param overlayReviewLogAdded the overlayReviewLogAdded to set
     */
    public void setOverlayReviewLogAdded(int overlayReviewLogAdded) {
        this.overlayReviewLogAdded = overlayReviewLogAdded;
    }

    /**
     * @return the jobDepartment
     */
    public String getJobDepartment() {
        return jobDepartment;
    }

    /**
     * @param jobDepartment the jobDepartment to set
     */
    public void setJobDepartment(String jobDepartment) {
        this.jobDepartment = jobDepartment;
    }

    /**
     * @return the jobHireType
     */
    public String getJobHireType() {
        return jobHireType;
    }

    /**
     * @param jobHireType the jobHireType to set
     */
    public void setJobHireType(String jobHireType) {
        this.jobHireType = jobHireType;
    }

    /**
     * @return the eventTitle
     */
    public String getEventTitle() {
        return eventTitle;
    }

    /**
     * @param eventTitle the eventTitle to set
     */
    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    /**
     * @return the eventStatus
     */
    public String getEventStatus() {
        return eventStatus;
    }

    /**
     * @param eventStatus the eventStatus to set
     */
    public void setEventStatus(String eventStatus) {
        this.eventStatus = eventStatus;
    }

    /**
     * @return the timeZone
     */
    public String getTimeZone() {
        return timeZone;
    }

    /**
     * @param timeZone the timeZone to set
     */
    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    /**
     * @return the eventLocation
     */
    public String getEventLocation() {
        return eventLocation;
    }

    /**
     * @param eventLocation the eventLocation to set
     */
    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    /**
     * @return the transportation
     */
    public String getTransportation() {
        return transportation;
    }

    /**
     * @param transportation the transportation to set
     */
    public void setTransportation(String transportation) {
        this.transportation = transportation;
    }

    /**
     * @return the eventDescription
     */
    public String getEventDescription() {
        return eventDescription;
    }

    /**
     * @param eventDescription the eventDescription to set
     */
    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    /**
     * @return the startTime
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the midDayFrom
     */
    public String getMidDayFrom() {
        return midDayFrom;
    }

    /**
     * @param midDayFrom the midDayFrom to set
     */
    public void setMidDayFrom(String midDayFrom) {
        this.midDayFrom = midDayFrom;
    }

    /**
     * @return the midDayTo
     */
    public String getMidDayTo() {
        return midDayTo;
    }

    /**
     * @param midDayTo the midDayTo to set
     */
    public void setMidDayTo(String midDayTo) {
        this.midDayTo = midDayTo;
    }

    /**
     * @return the eventBoldtitle
     */
    public String getEventBoldtitle() {
        return eventBoldtitle;
    }

    /**
     * @param eventBoldtitle the eventBoldtitle to set
     */
    public void setEventBoldtitle(String eventBoldtitle) {
        this.eventBoldtitle = eventBoldtitle;
    }

    /**
     * @return the eventRegluarTitle
     */
    public String getEventRegluarTitle() {
        return eventRegluarTitle;
    }

    /**
     * @param eventRegluarTitle the eventRegluarTitle to set
     */
    public void setEventRegluarTitle(String eventRegluarTitle) {
        this.eventRegluarTitle = eventRegluarTitle;
    }

    /**
     * @return the eventRegistrationLink
     */
    public String getEventRegistrationLink() {
        return eventRegistrationLink;
    }

    /**
     * @param eventRegistrationLink the eventRegistrationLink to set
     */
    public void setEventRegistrationLink(String eventRegistrationLink) {
        this.eventRegistrationLink = eventRegistrationLink;
    }

    /**
     * @return the contactUsEmail
     */
    public String getContactUsEmail() {
        return contactUsEmail;
    }

    /**
     * @param contactUsEmail the contactUsEmail to set
     */
    public void setContactUsEmail(String contactUsEmail) {
        this.contactUsEmail = contactUsEmail;
    }

    /**
     * @return the speakerName
     */
    public String getSpeakerName() {
        return speakerName;
    }

    /**
     * @param speakerName the speakerName to set
     */
    public void setSpeakerName(String speakerName) {
        this.speakerName = speakerName;
    }

    /**
     * @return the designation
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * @param designation the designation to set
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * @return the company
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * @return the speakerType
     */
    public String getSpeakerType() {
        return speakerType;
    }

    /**
     * @param speakerType the speakerType to set
     */
    public void setSpeakerType(String speakerType) {
        this.speakerType = speakerType;
    }

    /**
     * @return the speakerId
     */
    public String getSpeakerId() {
        return speakerId;
    }

    /**
     * @param speakerId the speakerId to set
     */
    public void setSpeakerId(String speakerId) {
        this.speakerId = speakerId;
    }

    /**
     * @return the conferenceUrl
     */
    public String getConferenceUrl() {
        return conferenceUrl;
    }

    /**
     * @param conferenceUrl the conferenceUrl to set
     */
    public void setConferenceUrl(String conferenceUrl) {
        this.conferenceUrl = conferenceUrl;
    }

    /**
     * @return the primaryTrack
     */
    public String getPrimaryTrack() {
        return primaryTrack;
    }

    /**
     * @param primaryTrack the primaryTrack to set
     */
    public void setPrimaryTrack(String primaryTrack) {
        this.primaryTrack = primaryTrack;
    }

    /**
     * @return the secondaryTrack
     */
    public String getSecondaryTrack() {
        return secondaryTrack;
    }

    /**
     * @param secondaryTrack the secondaryTrack to set
     */
    public void setSecondaryTrack(String secondaryTrack) {
        this.secondaryTrack = secondaryTrack;
    }

    /**
     * @return the eventDepartment
     */
    public String getEventDepartment() {
        return eventDepartment;
    }

    /**
     * @param eventDepartment the eventDepartment to set
     */
    public void setEventDepartment(String eventDepartment) {
        this.eventDepartment = eventDepartment;
    }

    /**
     * @return the eventAfterVideoUrl
     */
    public String getEventAfterVideoUrl() {
        return eventAfterVideoUrl;
    }

    /**
     * @param eventAfterVideoUrl the eventAfterVideoUrl to set
     */
    public void setEventAfterVideoUrl(String eventAfterVideoUrl) {
        this.eventAfterVideoUrl = eventAfterVideoUrl;
    }

    /**
     * @return the infoId
     */
    public String getInfoId() {
        return infoId;
    }

    /**
     * @param infoId the infoId to set
     */
    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    /**
     * @return the tableName
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * @param tableName the tableName to set
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * @return the eventAfterDescription
     */
    public String getEventAfterDescription() {
        return eventAfterDescription;
    }

    /**
     * @param eventAfterDescription the eventAfterDescription to set
     */
    public void setEventAfterDescription(String eventAfterDescription) {
        this.eventAfterDescription = eventAfterDescription;
    }

    /**
     * @return the isAssociated
     */
    public String getIsAssociated() {
        return isAssociated;
    }

    /**
     * @param isAssociated the isAssociated to set
     */
    public void setIsAssociated(String isAssociated) {
        this.isAssociated = isAssociated;
    }

    /**
     * @return the associatedEventId
     */
    public String getAssociatedEventId() {
        return associatedEventId;
    }

    /**
     * @param associatedEventId the associatedEventId to set
     */
    public void setAssociatedEventId(String associatedEventId) {
        this.associatedEventId = associatedEventId;
    }

    /**
     * @return the seriesTitle
     */
    public String getSeriesTitle() {
        return seriesTitle;
    }

    /**
     * @param seriesTitle the seriesTitle to set
     */
    public void setSeriesTitle(String seriesTitle) {
        this.seriesTitle = seriesTitle;
    }

    /**
     * @return the seriesType
     */
    public String getSeriesType() {
        return seriesType;
    }

    /**
     * @param seriesType the seriesType to set
     */
    public void setSeriesType(String seriesType) {
        this.seriesType = seriesType;
    }

    /**
     * @return the seriesTrack
     */
    public String getSeriesTrack() {
        return seriesTrack;
    }

    /**
     * @param seriesTrack the seriesTrack to set
     */
    public void setSeriesTrack(String seriesTrack) {
        this.seriesTrack = seriesTrack;
    }

    /**
     * @return the seriesStatus
     */
    public String getSeriesStatus() {
        return seriesStatus;
    }

    /**
     * @param seriesStatus the seriesStatus to set
     */
    public void setSeriesStatus(String seriesStatus) {
        this.seriesStatus = seriesStatus;
    }

    /**
     * @return the seriesId
     */
    public String getSeriesId() {
        return seriesId;
    }

    /**
     * @param seriesId the seriesId to set
     */
    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId;
    }

    /**
     * @return the peopleName
     */
    public String getPeopleName() {
        return peopleName;
    }

    /**
     * @param peopleName the peopleName to set
     */
    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }

    /**
     * @return the trackName
     */
    public String getTrackName() {
        return trackName;
    }

    /**
     * @param trackName the trackName to set
     */
    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    /**
     * @return the resourceTitle
     */
    public String getResourceTitle() {
        return resourceTitle;
    }

    /**
     * @param resourceTitle the resourceTitle to set
     */
    public void setResourceTitle(String resourceTitle) {
        this.resourceTitle = resourceTitle;
    }

    /**
     * @return the trackId
     */
    public int getTrackId() {
        return trackId;
    }

    /**
     * @param trackId the trackId to set
     */
    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public boolean isExternal() {
        return external;
    }

    public void setExternal(boolean external) {
        this.external = external;
    }

    public int getPastMonths() {
        return pastMonths;
    }

    public void setPastMonths(int pastMonths) {
        this.pastMonths = pastMonths;
    }

    public String getAssignedBy() {
        return assignedBy;
    }

    public void setAssignedBy(String assignedBy) {
        this.assignedBy = assignedBy;
    }

    /**
     * @return the questionTitle
     */
    public String getQuestionTitle() {
        return questionTitle;
    }

    /**
     * @param questionTitle the questionTitle to set
     */
    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    /**
     * @return the optionType
     */
    public String getOptionType() {
        return optionType;
    }

    /**
     * @param optionType the optionType to set
     */
    public void setOptionType(String optionType) {
        this.optionType = optionType;
    }

    /**
     * @return the querySequence
     */
    public String getQuerySequence() {
        return querySequence;
    }

    /**
     * @param querySequence the querySequence to set
     */
    public void setQuerySequence(String querySequence) {
        this.querySequence = querySequence;
    }

    /**
     * @return the optionCount
     */
    public int getOptionCount() {
        return optionCount;
    }

    /**
     * @param optionCount the optionCount to set
     */
    public void setOptionCount(int optionCount) {
        this.optionCount = optionCount;
    }

    /**
     * @return the optionLabel1
     */
    public String getOptionLabel1() {
        return optionLabel1;
    }

    /**
     * @param optionLabel1 the optionLabel1 to set
     */
    public void setOptionLabel1(String optionLabel1) {
        this.optionLabel1 = optionLabel1;
    }

    /**
     * @return the optionLabel2
     */
    public String getOptionLabel2() {
        return optionLabel2;
    }

    /**
     * @param optionLabel2 the optionLabel2 to set
     */
    public void setOptionLabel2(String optionLabel2) {
        this.optionLabel2 = optionLabel2;
    }

    /**
     * @return the optionLabel3
     */
    public String getOptionLabel3() {
        return optionLabel3;
    }

    /**
     * @param optionLabel3 the optionLabel3 to set
     */
    public void setOptionLabel3(String optionLabel3) {
        this.optionLabel3 = optionLabel3;
    }

    /**
     * @return the optionLabel4
     */
    public String getOptionLabel4() {
        return optionLabel4;
    }

    /**
     * @param optionLabel4 the optionLabel4 to set
     */
    public void setOptionLabel4(String optionLabel4) {
        this.optionLabel4 = optionLabel4;
    }

    /**
     * @return the optionLabel5
     */
    public String getOptionLabel5() {
        return optionLabel5;
    }

    /**
     * @param optionLabel5 the optionLabel5 to set
     */
    public void setOptionLabel5(String optionLabel5) {
        this.optionLabel5 = optionLabel5;
    }

    /**
     * @return the optionLabel6
     */
    public String getOptionLabel6() {
        return optionLabel6;
    }

    /**
     * @param optionLabel6 the optionLabel6 to set
     */
    public void setOptionLabel6(String optionLabel6) {
        this.optionLabel6 = optionLabel6;
    }

    /**
     * @return the optionSequence1
     */
    public int getOptionSequence1() {
        return optionSequence1;
    }

    /**
     * @param optionSequence1 the optionSequence1 to set
     */
    public void setOptionSequence1(int optionSequence1) {
        this.optionSequence1 = optionSequence1;
    }

    /**
     * @return the optionSequence2
     */
    public int getOptionSequence2() {
        return optionSequence2;
    }

    /**
     * @param optionSequence2 the optionSequence2 to set
     */
    public void setOptionSequence2(int optionSequence2) {
        this.optionSequence2 = optionSequence2;
    }

    /**
     * @return the optionSequence3
     */
    public int getOptionSequence3() {
        return optionSequence3;
    }

    /**
     * @param optionSequence3 the optionSequence3 to set
     */
    public void setOptionSequence3(int optionSequence3) {
        this.optionSequence3 = optionSequence3;
    }

    /**
     * @return the optionSequence4
     */
    public int getOptionSequence4() {
        return optionSequence4;
    }

    /**
     * @param optionSequence4 the optionSequence4 to set
     */
    public void setOptionSequence4(int optionSequence4) {
        this.optionSequence4 = optionSequence4;
    }

    /**
     * @return the optionSequence5
     */
    public int getOptionSequence5() {
        return optionSequence5;
    }

    /**
     * @param optionSequence5 the optionSequence5 to set
     */
    public void setOptionSequence5(int optionSequence5) {
        this.optionSequence5 = optionSequence5;
    }

    /**
     * @return the optionSequence6
     */
    public int getOptionSequence6() {
        return optionSequence6;
    }

    /**
     * @param optionSequence6 the optionSequence6 to set
     */
    public void setOptionSequence6(int optionSequence6) {
        this.optionSequence6 = optionSequence6;
    }

    /**
     * @return the surveyId
     */
    public String getSurveyId() {
        return surveyId;
    }

    /**
     * @param surveyId the surveyId to set
     */
    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }

    /**
     * @return the surveyInfoId
     */
    public int getSurveyInfoId() {
        return surveyInfoId;
    }

    /**
     * @param surveyInfoId the surveyInfoId to set
     */
    public void setSurveyInfoId(int surveyInfoId) {
        this.surveyInfoId = surveyInfoId;
    }

    /**
     * @return the isRequired
     */
    public boolean isIsRequired() {
        return isRequired;
    }

    /**
     * @param isRequired the isRequired to set
     */
    public void setIsRequired(boolean isRequired) {
        this.isRequired = isRequired;
    }

    /**
     * @return the questionSequanceData
     */
    public String getQuestionSequanceData() {
        return questionSequanceData;
    }

    /**
     * @param questionSequanceData the questionSequanceData to set
     */
    public void setQuestionSequanceData(String questionSequanceData) {
        this.questionSequanceData = questionSequanceData;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getRecruiterLoginId() {
        return recruiterLoginId;
    }

    public void setRecruiterLoginId(String recruiterLoginId) {
        this.recruiterLoginId = recruiterLoginId;
    }

    public String getTeamLeadLoginId() {
        return teamLeadLoginId;
    }

    public void setTeamLeadLoginId(String teamLeadLoginId) {
        this.teamLeadLoginId = teamLeadLoginId;
    }

    public String getTechMgrId() {
        return techMgrId;
    }

    public void setTechMgrId(String techMgrId) {
        this.techMgrId = techMgrId;
    }

    /**
     * @return the modifiedStartDate
     */
    public String getModifiedStartDate() {
        return modifiedStartDate;
    }

    /**
     * @param modifiedStartDate the modifiedStartDate to set
     */
    public void setModifiedStartDate(String modifiedStartDate) {
        this.modifiedStartDate = modifiedStartDate;
    }

    /**
     * @return the modifiedEndDate
     */
    public String getModifiedEndDate() {
        return modifiedEndDate;
    }

    /**
     * @param modifiedEndDate the modifiedEndDate to set
     */
    public void setModifiedEndDate(String modifiedEndDate) {
        this.modifiedEndDate = modifiedEndDate;
    }

    /**
     * @return the preSalesPerson
     */
    public String getPreSalesPerson() {
        return preSalesPerson;
    }

    /**
     * @param preSalesPerson the preSalesPerson to set
     */
    public void setPreSalesPerson(String preSalesPerson) {
        this.preSalesPerson = preSalesPerson;
    }

    /**
     * @return the fromMonth
     */
    public int getFromMonth() {
        return fromMonth;
    }

    /**
     * @param fromMonth the fromMonth to set
     */
    public void setFromMonth(int fromMonth) {
        this.fromMonth = fromMonth;
    }

    /**
     * @return the fromYear
     */
    public int getFromYear() {
        return fromYear;
    }

    /**
     * @param fromYear the fromYear to set
     */
    public void setFromYear(int fromYear) {
        this.fromYear = fromYear;
    }

    /**
     * @return the toMonth
     */
    public int getToMonth() {
        return toMonth;
    }

    /**
     * @param toMonth the toMonth to set
     */
    public void setToMonth(int toMonth) {
        this.toMonth = toMonth;
    }

    /**
     * @return the toYear
     */
    public int getToYear() {
        return toYear;
    }

    /**
     * @param toYear the toYear to set
     */
    public void setToYear(int toYear) {
        this.toYear = toYear;
    }

    /**
     * @return the opsContactId
     */
    public String getOpsContactId() {
        return opsContactId;
    }

    /**
     * @param opsContactId the opsContactId to set
     */
    public void setOpsContactId(String opsContactId) {
        this.opsContactId = opsContactId;
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

    public String getDropdownOptions() {
        return dropdownOptions;
    }

    public void setDropdownOptions(String dropdownOptions) {
        this.dropdownOptions = dropdownOptions;
    }

    public String getPlaceHolder() {
        return placeHolder;
    }

    public void setPlaceHolder(String placeHolder) {
        this.placeHolder = placeHolder;
    }

    public String getQuestionStatus() {
        return questionStatus;
    }

    public void setQuestionStatus(String questionStatus) {
        this.questionStatus = questionStatus;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * @return the opsContactIdForAcc
     */
    public String getOpsContactIdForAcc() {
        return opsContactIdForAcc;
    }

    /**
     * @param opsContactIdForAcc the opsContactIdForAcc to set
     */
    public void setOpsContactIdForAcc(String opsContactIdForAcc) {
        this.opsContactIdForAcc = opsContactIdForAcc;
    }

    /**
     * @return the salesLeadId
     */
    public String getSalesLeadId() {
        return salesLeadId;
    }

    /**
     * @param salesLeadId the salesLeadId to set
     */
    public void setSalesLeadId(String salesLeadId) {
        this.salesLeadId = salesLeadId;
    }

    /**
     * @return the salesStartDate
     */
    public String getSalesStartDate() {
        return salesStartDate;
    }

    /**
     * @param salesStartDate the salesStartDate to set
     */
    public void setSalesStartDate(String salesStartDate) {
        this.salesStartDate = salesStartDate;
    }

    /**
     * @return the salesEndDate
     */
    public String getSalesEndDate() {
        return salesEndDate;
    }

    /**
     * @param salesEndDate the salesEndDate to set
     */
    public void setSalesEndDate(String salesEndDate) {
        this.salesEndDate = salesEndDate;
    }

    /**
     * @return the pmoLoginId
     */
    public String getPmoLoginId() {
        return pmoLoginId;
    }

    /**
     * @param pmoLoginId the pmoLoginId to set
     */
    public void setPmoLoginId(String pmoLoginId) {
        this.pmoLoginId = pmoLoginId;
    }

    /**
     * @return the recManagerId
     */
    public String getRecManagerId() {
        return recManagerId;
    }

    /**
     * @param recManagerId the recManagerId to set
     */
    public void setRecManagerId(String recManagerId) {
        this.recManagerId = recManagerId;
    }

    /**
     * @return the hours
     */
    public String getHours() {
        return hours;
    }

    /**
     * @param hours the hours to set
     */
    public void setHours(String hours) {
        this.hours = hours;
    }

    /**
     * @return the severity
     */
    public String getSeverity() {
        return severity;
    }

    /**
     * @param severity the severity to set
     */
    public void setSeverity(String severity) {
        this.severity = severity;
    }

    /**
     * @return the taskId
     */
    public int getTaskId() {
        return taskId;
    }

    /**
     * @param taskId the taskId to set
     */
    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    /**
     * @return the issueTypeId
     */
    public int getIssueTypeId() {
        return issueTypeId;
    }

    /**
     * @param issueTypeId the issueTypeId to set
     */
    public void setIssueTypeId(int issueTypeId) {
        this.issueTypeId = issueTypeId;
    }

    /**
     * @return the resolution
     */
    public String getResolution() {
        return resolution;
    }

    /**
     * @param resolution the resolution to set
     */
    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    /**
     * @return the bridgeDate
     */
    public String getBridgeDate() {
        return bridgeDate;
    }

    /**
     * @param bridgeDate the bridgeDate to set
     */
    public void setBridgeDate(String bridgeDate) {
        this.bridgeDate = bridgeDate;
    }

    /**
     * @return the bridgeCode
     */
    public String getBridgeCode() {
        return bridgeCode;
    }

    /**
     * @param bridgeCode the bridgeCode to set
     */
    public void setBridgeCode(String bridgeCode) {
        this.bridgeCode = bridgeCode;
    }

    /**
     * @return the bridgeNumber
     */
    public String getBridgeNumber() {
        return bridgeNumber;
    }

    /**
     * @param bridgeNumber the bridgeNumber to set
     */
    public void setBridgeNumber(String bridgeNumber) {
        this.bridgeNumber = bridgeNumber;
    }

    /**
     * @return the bridgeName
     */
    public String getBridgeName() {
        return bridgeName;
    }

    /**
     * @param bridgeName the bridgeName to set
     */
    public void setBridgeName(String bridgeName) {
        this.bridgeName = bridgeName;
    }

    /**
     * @return the bridgeStatus
     */
    public String getBridgeStatus() {
        return bridgeStatus;
    }

    /**
     * @param bridgeStatus the bridgeStatus to set
     */
    public void setBridgeStatus(String bridgeStatus) {
        this.bridgeStatus = bridgeStatus;
    }

    /**
     * @return the duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * @return the durationType
     */
    public String getDurationType() {
        return durationType;
    }

    /**
     * @param durationType the durationType to set
     */
    public void setDurationType(String durationType) {
        this.durationType = durationType;
    }

    /**
     * @return the assignTo
     */
    public String getAssignTo() {
        return assignTo;
    }

    /**
     * @param assignTo the assignTo to set
     */
    public void setAssignTo(String assignTo) {
        this.assignTo = assignTo;
    }

    /**
     * @return the internalAttendees
     */
    public String getInternalAttendees() {
        return internalAttendees;
    }

    /**
     * @param internalAttendees the internalAttendees to set
     */
    public void setInternalAttendees(String internalAttendees) {
        this.internalAttendees = internalAttendees;
    }

    /**
     * @return the externalAttendees
     */
    public String getExternalAttendees() {
        return externalAttendees;
    }

    /**
     * @param externalAttendees the externalAttendees to set
     */
    public void setExternalAttendees(String externalAttendees) {
        this.externalAttendees = externalAttendees;
    }

    /**
     * @return the passCode
     */
    public int getPassCode() {
        return passCode;
    }

    /**
     * @param passCode the passCode to set
     */
    public void setPassCode(int passCode) {
        this.passCode = passCode;
    }

    /**
     * @return the emailFlag
     */
    public int getEmailFlag() {
        return emailFlag;
    }

    /**
     * @param emailFlag the emailFlag to set
     */
    public void setEmailFlag(int emailFlag) {
        this.emailFlag = emailFlag;
    }

    /**
     * @return the contactEmailID
     */
    public String getContactEmailID() {
        return contactEmailID;
    }

    /**
     * @param contactEmailID the contactEmailID to set
     */
    public void setContactEmailID(String contactEmailID) {
        this.contactEmailID = contactEmailID;
    }

    /**
     * @return the contactPhoneNo
     */
    public String getContactPhoneNo() {
        return contactPhoneNo;
    }

    /**
     * @param contactPhoneNo the contactPhoneNo to set
     */
    public void setContactPhoneNo(String contactPhoneNo) {
        this.contactPhoneNo = contactPhoneNo;
    }

    /**
     * @return the contactCompany
     */
    public String getContactCompany() {
        return contactCompany;
    }

    /**
     * @param contactCompany the contactCompany to set
     */
    public void setContactCompany(String contactCompany) {
        this.contactCompany = contactCompany;
    }

    /**
     * @return the investmentName
     */
    public String getInvestmentName() {
        return investmentName;
    }

    /**
     * @param investmentName the investmentName to set
     */
    public void setInvestmentName(String investmentName) {
        this.investmentName = investmentName;
    }

    /**
     * @return the startDateInvestment
     */
    public String getStartDateInvestment() {
        return startDateInvestment;
    }

    /**
     * @param startDateInvestment the startDateInvestment to set
     */
    public void setStartDateInvestment(String startDateInvestment) {
        this.startDateInvestment = startDateInvestment;
    }

    /**
     * @return the endDateInvestment
     */
    public String getEndDateInvestment() {
        return endDateInvestment;
    }

    /**
     * @param endDateInvestment the endDateInvestment to set
     */
    public void setEndDateInvestment(String endDateInvestment) {
        this.endDateInvestment = endDateInvestment;
    }

    /**
     * @return the countryInvestment
     */
    public String getCountryInvestment() {
        return countryInvestment;
    }

    /**
     * @param countryInvestment the countryInvestment to set
     */
    public void setCountryInvestment(String countryInvestment) {
        this.countryInvestment = countryInvestment;
    }

    /**
     * @return the totalExpenseAmount
     */
    public double getTotalExpenseAmount() {
        return totalExpenseAmount;
    }

    /**
     * @param totalExpenseAmount the totalExpenseAmount to set
     */
    public void setTotalExpenseAmount(double totalExpenseAmount) {
        this.totalExpenseAmount = totalExpenseAmount;
    }

    /**
     * @return the locationInvestment
     */
    public String getLocationInvestment() {
        return locationInvestment;
    }

    /**
     * @param locationInvestment the locationInvestment to set
     */
    public void setLocationInvestment(String locationInvestment) {
        this.locationInvestment = locationInvestment;
    }

    /**
     * @return the investmentDesc
     */
    public String getInvestmentDesc() {
        return investmentDesc;
    }

    /**
     * @param investmentDesc the investmentDesc to set
     */
    public void setInvestmentDesc(String investmentDesc) {
        this.investmentDesc = investmentDesc;
    }

    /**
     * @return the attachInvestment
     */
    public String getAttachInvestment() {
        return attachInvestment;
    }

    /**
     * @param attachInvestment the attachInvestment to set
     */
    public void setAttachInvestment(String attachInvestment) {
        this.attachInvestment = attachInvestment;
    }

    /**
     * @return the Invsfile
     */
    public File getInvsfile() {
        return Invsfile;
    }

    /**
     * @param Invsfile the Invsfile to set
     */
    public void setInvsfile(File Invsfile) {
        this.Invsfile = Invsfile;
    }

    /**
     * @return the usd
     */
    public String getUsd() {
        return usd;
    }

    /**
     * @param usd the usd to set
     */
    public void setUsd(String usd) {
        this.usd = usd;
    }

    /**
     * @return the campaignTitle
     */
    public String getCampaignTitle() {
        return campaignTitle;
    }

    /**
     * @param campaignTitle the campaignTitle to set
     */
    public void setCampaignTitle(String campaignTitle) {
        this.campaignTitle = campaignTitle;
    }

    /**
     * @return the campaignStatus
     */
    public String getCampaignStatus() {
        return campaignStatus;
    }

    /**
     * @param campaignStatus the campaignStatus to set
     */
    public void setCampaignStatus(String campaignStatus) {
        this.campaignStatus = campaignStatus;
    }

    /**
     * @return the campaignStartDate
     */
    public String getCampaignStartDate() {
        return campaignStartDate;
    }

    /**
     * @param campaignStartDate the campaignStartDate to set
     */
    public void setCampaignStartDate(String campaignStartDate) {
        this.campaignStartDate = campaignStartDate;
    }

    /**
     * @return the campaignEndDate
     */
    public String getCampaignEndDate() {
        return campaignEndDate;
    }

    /**
     * @param campaignEndDate the campaignEndDate to set
     */
    public void setCampaignEndDate(String campaignEndDate) {
        this.campaignEndDate = campaignEndDate;
    }

    /**
     * @return the campaignName
     */
    public String getCampaignName() {
        return campaignName;
    }

    /**
     * @param campaignName the campaignName to set
     */
    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    /**
     * @return the renewalState
     */
    public String getRenewalState() {
        return renewalState;
    }

    /**
     * @param renewalState the renewalState to set
     */
    public void setRenewalState(String renewalState) {
        this.renewalState = renewalState;
    }

    /**
     * @return the resStatesList
     */
    public List getResStatesList() {
        return resStatesList;
    }

    /**
     * @param resStatesList the resStatesList to set
     */
    public void setResStatesList(List resStatesList) {
        this.resStatesList = resStatesList;
    }

    /**
     * @return the relatedTeam
     */
    public String getRelatedTeam() {
        return relatedTeam;
    }

    /**
     * @param relatedTeam the relatedTeam to set
     */
    public void setRelatedTeam(String relatedTeam) {
        this.relatedTeam = relatedTeam;
    }

    /**
     * @return the requirementstatus
     */
    public String getRequirementstatus() {
        return requirementstatus;
    }

    /**
     * @param requirementstatus the requirementstatus to set
     */
    public void setRequirementstatus(String requirementstatus) {
        this.requirementstatus = requirementstatus;
    }

    /**
     * @return the practice
     */
    public String getPractice() {
        return practice;
    }

    /**
     * @param practice the practice to set
     */
    public void setPractice(String practice) {
        this.practice = practice;
    }

    /**
     * @return the reqStartDate
     */
    public String getReqStartDate() {
        return reqStartDate;
    }

    /**
     * @param reqStartDate the reqStartDate to set
     */
    public void setReqStartDate(String reqStartDate) {
        this.reqStartDate = reqStartDate;
    }

    /**
     * @return the reqEndDate
     */
    public String getReqEndDate() {
        return reqEndDate;
    }

    /**
     * @param reqEndDate the reqEndDate to set
     */
    public void setReqEndDate(String reqEndDate) {
        this.reqEndDate = reqEndDate;
    }

    /**
     * @return the reqJobTitle
     */
    public String getReqJobTitle() {
        return reqJobTitle;
    }

    /**
     * @param reqJobTitle the reqJobTitle to set
     */
    public void setReqJobTitle(String reqJobTitle) {
        this.reqJobTitle = reqJobTitle;
    }

    /**
     * @return the fileFlag
     */
    public int getFileFlag() {
        return fileFlag;
    }

    /**
     * @param fileFlag the fileFlag to set
     */
    public void setFileFlag(int fileFlag) {
        this.fileFlag = fileFlag;
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
     * @return the executiveMeetingType
     */
    public String getExecutiveMeetingType() {
        return executiveMeetingType;
    }

    /**
     * @param executiveMeetingType the executiveMeetingType to set
     */
    public void setExecutiveMeetingType(String executiveMeetingType) {
        this.executiveMeetingType = executiveMeetingType;
    }

    /**
     * @return the executiveMeetMonth
     */
    public String getExecutiveMeetMonth() {
        return executiveMeetMonth;
    }

    /**
     * @param executiveMeetMonth the executiveMeetMonth to set
     */
    public void setExecutiveMeetMonth(String executiveMeetMonth) {
        this.executiveMeetMonth = executiveMeetMonth;
    }

    /**
     * @return the registrationTextforExeMeet
     */
    public String getRegistrationTextforExeMeet() {
        return registrationTextforExeMeet;
    }

    /**
     * @param registrationTextforExeMeet the registrationTextforExeMeet to set
     */
    public void setRegistrationTextforExeMeet(String registrationTextforExeMeet) {
        this.registrationTextforExeMeet = registrationTextforExeMeet;
    }

    /**
     * @return the exeMeetcreatedDateTo
     */
    public String getExeMeetcreatedDateTo() {
        return exeMeetcreatedDateTo;
    }

    /**
     * @param exeMeetcreatedDateTo the exeMeetcreatedDateTo to set
     */
    public void setExeMeetcreatedDateTo(String exeMeetcreatedDateTo) {
        this.exeMeetcreatedDateTo = exeMeetcreatedDateTo;
    }

    /**
     * @return the executiveMeetingStatus
     */
    public String getExecutiveMeetingStatus() {
        return executiveMeetingStatus;
    }

    /**
     * @param executiveMeetingStatus the executiveMeetingStatus to set
     */
    public void setExecutiveMeetingStatus(String executiveMeetingStatus) {
        this.executiveMeetingStatus = executiveMeetingStatus;
    }

    /**
     * @return the exeMeetStartTime
     */
    public String getExeMeetStartTime() {
        return exeMeetStartTime;
    }

    /**
     * @param exeMeetStartTime the exeMeetStartTime to set
     */
    public void setExeMeetStartTime(String exeMeetStartTime) {
        this.exeMeetStartTime = exeMeetStartTime;
    }

    /**
     * @return the exeMeetEndTime
     */
    public String getExeMeetEndTime() {
        return exeMeetEndTime;
    }

    /**
     * @param exeMeetEndTime the exeMeetEndTime to set
     */
    public void setExeMeetEndTime(String exeMeetEndTime) {
        this.exeMeetEndTime = exeMeetEndTime;
    }

    /**
     * @return the exeMeetStartmidDayFrom
     */
    public String getExeMeetStartmidDayFrom() {
        return exeMeetStartmidDayFrom;
    }

    /**
     * @param exeMeetStartmidDayFrom the exeMeetStartmidDayFrom to set
     */
    public void setExeMeetStartmidDayFrom(String exeMeetStartmidDayFrom) {
        this.exeMeetStartmidDayFrom = exeMeetStartmidDayFrom;
    }

    /**
     * @return the exeMeetEndmidDayTo
     */
    public String getExeMeetEndmidDayTo() {
        return exeMeetEndmidDayTo;
    }

    /**
     * @param exeMeetEndmidDayTo the exeMeetEndmidDayTo to set
     */
    public void setExeMeetEndmidDayTo(String exeMeetEndmidDayTo) {
        this.exeMeetEndmidDayTo = exeMeetEndmidDayTo;
    }

    /**
     * @return the registrationLinkForEMeet
     */
    public String getRegistrationLinkForEMeet() {
        return registrationLinkForEMeet;
    }

    /**
     * @param registrationLinkForEMeet the registrationLinkForEMeet to set
     */
    public void setRegistrationLinkForEMeet(String registrationLinkForEMeet) {
        this.registrationLinkForEMeet = registrationLinkForEMeet;
    }

    /**
     * @return the eMeetAccessType
     */
    public String geteMeetAccessType() {
        return eMeetAccessType;
    }

    /**
     * @param eMeetAccessType the eMeetAccessType to set
     */
    public void seteMeetAccessType(String eMeetAccessType) {
        this.eMeetAccessType = eMeetAccessType;
    }

    /**
     * @return the emeetAttendeesEmail
     */
    public String getEmeetAttendeesEmail() {
        return emeetAttendeesEmail;
    }

    /**
     * @param emeetAttendeesEmail the emeetAttendeesEmail to set
     */
    public void setEmeetAttendeesEmail(String emeetAttendeesEmail) {
        this.emeetAttendeesEmail = emeetAttendeesEmail;
    }

    /**
     * @return the executiveMeetingAccessStatus
     */
    public String getExecutiveMeetingAccessStatus() {
        return executiveMeetingAccessStatus;
    }

    /**
     * @param executiveMeetingAccessStatus the executiveMeetingAccessStatus to set
     */
    public void setExecutiveMeetingAccessStatus(String executiveMeetingAccessStatus) {
        this.executiveMeetingAccessStatus = executiveMeetingAccessStatus;
    }

    /**
     * @return the executiveMeetAccessType
     */
    public String getExecutiveMeetAccessType() {
        return executiveMeetAccessType;
    }

    /**
     * @param executiveMeetAccessType the executiveMeetAccessType to set
     */
    public void setExecutiveMeetAccessType(String executiveMeetAccessType) {
        this.executiveMeetAccessType = executiveMeetAccessType;
    }

    /**
     * @return the executiveMeetingAttendeeEmail
     */
    public String getExecutiveMeetingAttendeeEmail() {
        return executiveMeetingAttendeeEmail;
    }

    /**
     * @param executiveMeetingAttendeeEmail the executiveMeetingAttendeeEmail to set
     */
    public void setExecutiveMeetingAttendeeEmail(String executiveMeetingAttendeeEmail) {
        this.executiveMeetingAttendeeEmail = executiveMeetingAttendeeEmail;
    }

    /**
     * @return the videoLinkForEMeet
     */
    public String getVideoLinkForEMeet() {
        return videoLinkForEMeet;
    }

    /**
     * @param videoLinkForEMeet the videoLinkForEMeet to set
     */
    public void setVideoLinkForEMeet(String videoLinkForEMeet) {
        this.videoLinkForEMeet = videoLinkForEMeet;
    }

    /**
     * @return the previousStatus
     */
    public String getPreviousStatus() {
        return previousStatus;
    }

    /**
     * @param previousStatus the previousStatus to set
     */
    public void setPreviousStatus(String previousStatus) {
        this.previousStatus = previousStatus;
    }

    /**
     * @return the contactStartDate
     */
    public String getContactStartDate() {
        return contactStartDate;
    }

    /**
     * @param contactStartDate the contactStartDate to set
     */
    public void setContactStartDate(String contactStartDate) {
        this.contactStartDate = contactStartDate;
    }

    /**
     * @return the contactEndDate
     */
    public String getContactEndDate() {
        return contactEndDate;
    }

    /**
     * @param contactEndDate the contactEndDate to set
     */
    public void setContactEndDate(String contactEndDate) {
        this.contactEndDate = contactEndDate;
    }

    /**
     * @return the creCollegeName
     */
    public String getCreCollegeName() {
        return creCollegeName;
    }

    /**
     * @param creCollegeName the creCollegeName to set
     */
    public void setCreCollegeName(String creCollegeName) {
        this.creCollegeName = creCollegeName;
    }

    /**
     * @return the course
     */
    public String getCourse() {
        return course;
    }

    /**
     * @param course the course to set
     */
    public void setCourse(String course) {
        this.course = course;
    }

    /**
     * @return the creStream
     */
    public String getCreStream() {
        return creStream;
    }

    /**
     * @param creStream the creStream to set
     */
    public void setCreStream(String creStream) {
        this.creStream = creStream;
    }

    /**
     * @return the dueDate
     */
    public String getDueDate() {
        return dueDate;
    }

    /**
     * @param dueDate the dueDate to set
     */
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
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
     * @return the opsContactIdForPF
     */
    public int getOpsContactIdForPF() {
        return opsContactIdForPF;
    }

    /**
     * @param opsContactIdForPF the opsContactIdForPF to set
     */
    public void setOpsContactIdForPF(int opsContactIdForPF) {
        this.opsContactIdForPF = opsContactIdForPF;
    }

    /**
     * @return the preAssignEmpId
     */
    public int getPreAssignEmpId() {
        return preAssignEmpId;
    }

    /**
     * @param preAssignEmpId the preAssignEmpId to set
     */
    public void setPreAssignEmpId(int preAssignEmpId) {
        this.preAssignEmpId = preAssignEmpId;
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
     * @return the investmentType
     */
    public String getInvestmentType() {
        return investmentType;
    }

    /**
     * @param investmentType the investmentType to set
     */
    public void setInvestmentType(String investmentType) {
        this.investmentType = investmentType;
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
     * @return the opportunityState
     */
    public String getOpportunityState() {
        return opportunityState;
    }

    /**
     * @param opportunityState the opportunityState to set
     */
    public void setOpportunityState(String opportunityState) {
        this.opportunityState = opportunityState;
    }

    /**
     * @return the opportunityId
     */
    public int getOpportunityId() {
        return opportunityId;
    }

    /**
     * @param opportunityId the opportunityId to set
     */
    public void setOpportunityId(int opportunityId) {
        this.opportunityId = opportunityId;
    }

    /**
     * @return the missedField
     */
    public int getMissedField() {
        return missedField;
    }

    /**
     * @param missedField the missedField to set
     */
    public void setMissedField(int missedField) {
        this.missedField = missedField;
    }

    /**
     * @return the contactTitle
     */
    public String getContactTitle() {
        return contactTitle;
    }

    /**
     * @param contactTitle the contactTitle to set
     */
    public void setContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
    }

    /**
     * @return the sviNum
     */
    public String getSviNum() {
        return sviNum;
    }

    /**
     * @param sviNum the sviNum to set
     */
    public void setSviNum(String sviNum) {
        this.sviNum = sviNum;
    }

    /**
     * @return the sviList
     */
    public String getSviList() {
        return sviList;
    }

    /**
     * @param sviList the sviList to set
     */
    public void setSviList(String sviList) {
        this.sviList = sviList;
    }

    /**
     * @return the sviValue
     */
    public int getSviValue() {
        return sviValue;
    }

    /**
     * @param sviValue the sviValue to set
     */
    public void setSviValue(int sviValue) {
        this.sviValue = sviValue;
    }

    /**
     * @return the leadTitle
     */
    public String getLeadTitle() {
        return leadTitle;
    }

    /**
     * @param leadTitle the leadTitle to set
     */
    public void setLeadTitle(String leadTitle) {
        this.leadTitle = leadTitle;
    }

    /**
     * @return the createdDateFrom
     */
    public String getCreatedDateFrom() {
        return createdDateFrom;
    }

    /**
     * @param createdDateFrom the createdDateFrom to set
     */
    public void setCreatedDateFrom(String createdDateFrom) {
        this.createdDateFrom = createdDateFrom;
    }

    /**
     * @return the createdDateTo
     */
    public String getCreatedDateTo() {
        return createdDateTo;
    }

    /**
     * @param createdDateTo the createdDateTo to set
     */
    public void setCreatedDateTo(String createdDateTo) {
        this.createdDateTo = createdDateTo;
    }

    /**
     * @return the inactiveDateFrom
     */
    public String getInactiveDateFrom() {
        return inactiveDateFrom;
    }

    /**
     * @param inactiveDateFrom the inactiveDateFrom to set
     */
    public void setInactiveDateFrom(String inactiveDateFrom) {
        this.inactiveDateFrom = inactiveDateFrom;
    }

    /**
     * @return the inactiveDateTo
     */
    public String getInactiveDateTo() {
        return inactiveDateTo;
    }

    /**
     * @param inactiveDateTo the inactiveDateTo to set
     */
    public void setInactiveDateTo(String inactiveDateTo) {
        this.inactiveDateTo = inactiveDateTo;
    }

    /**
     * @return the investmentId
     */
    public int getInvestmentId() {
        return investmentId;
    }

    /**
     * @param investmentId the investmentId to set
     */
    public void setInvestmentId(int investmentId) {
        this.investmentId = investmentId;
    }

    /**
     * @param constantContactId the constantContactId to set
     */
    public void setConstantContactId(String constantContactId) {
        this.constantContactId = constantContactId;
    }

    /**
     * @return the requirement
     */
    public String getRequirement() {
        return requirement;
    }

    /**
     * @param requirement the requirement to set
     */
    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    /**
     * @return the loactionName
     */
    public String getLoactionName() {
        return loactionName;
    }

    /**
     * @param loactionName the loactionName to set
     */
    public void setLoactionName(String loactionName) {
        this.loactionName = loactionName;
    }

    /**
     * @return the constantContactId
     */
    public String getConstantContactId() {
        return constantContactId;
    }

    /**
     * @return the teamMemberCheck
     */
    public int getTeamMemberCheck() {
        return teamMemberCheck;
    }

    /**
     * @param teamMemberCheck the teamMemberCheck to set
     */
    public void setTeamMemberCheck(int teamMemberCheck) {
        this.teamMemberCheck = teamMemberCheck;
    }

    /**
     * @return the titleType
     */
    public String getTitleType() {
        return titleType;
    }

    /**
     * @param titleType the titleType to set
     */
    public void setTitleType(String titleType) {
        this.titleType = titleType;
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
     * @return the includeTeamFlag
     */
    public int getIncludeTeamFlag() {
        return includeTeamFlag;
    }

    /**
     * @param includeTeamFlag the includeTeamFlag to set
     */
    public void setIncludeTeamFlag(int includeTeamFlag) {
        this.includeTeamFlag = includeTeamFlag;
    }

    /**
     * @return the requestId
     */
    public int getRequestId() {
        return requestId;
    }

    /**
     * @param requestId the requestId to set
     */
    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    /**
     * @return the allowDocuments
     */
    public boolean getAllowDocuments() {
        return allowDocuments;
    }

    /**
     * @param allowDocuments the allowDocuments to set
     */
    public void setAllowDocuments(boolean allowDocuments) {
        this.allowDocuments = allowDocuments;
    }

    /**
     * @return the allowPictures
     */
    public boolean getAllowPictures() {
        return allowPictures;
    }

    /**
     * @param allowPictures the allowPictures to set
     */
    public void setAllowPictures(boolean allowPictures) {
        this.allowPictures = allowPictures;
    }

    /**
     * @return the subPracticeId
     */
    public String getSubPracticeId() {
        return subPracticeId;
    }

    /**
     * @param subPracticeId the subPracticeId to set
     */
    public void setSubPracticeId(String subPracticeId) {
        this.subPracticeId = subPracticeId;
    }

    /**
     * @return the sortedBy
     */
    public String getSortedBy() {
        return sortedBy;
    }

    /**
     * @param sortedBy the sortedBy to set
     */
    public void setSortedBy(String sortedBy) {
        this.sortedBy = sortedBy;
    }

    /**
     * @return the bdeLoginId
     */
    public String getBdeLoginId() {
        return bdeLoginId;
    }

    /**
     * @param bdeLoginId the bdeLoginId to set
     */
    public void setBdeLoginId(String bdeLoginId) {
        this.bdeLoginId = bdeLoginId;
    }

    /**
     * @return the investmentConferenceId
     */
    public int getInvestmentConferenceId() {
        return investmentConferenceId;
    }

    /**
     * @param investmentConferenceId the investmentConferenceId to set
     */
    public void setInvestmentConferenceId(int investmentConferenceId) {
        this.investmentConferenceId = investmentConferenceId;
    }

    /**
     * @return the sessionTitleType
     */
    public String getSessionTitleType() {
        return sessionTitleType;
    }

    /**
     * @param sessionTitleType the sessionTitleType to set
     */
    public void setSessionTitleType(String sessionTitleType) {
        this.sessionTitleType = sessionTitleType;
    }
}
