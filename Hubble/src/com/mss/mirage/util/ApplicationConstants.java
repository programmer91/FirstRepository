/**
 * *****************************************************************************
 *
 * Project : Mirage V2
 *
 * Package : com.mss.mirage.util
 *
 * Date : September 21, 2007, 2:44 PM
 *
 * Author : Praveen Kumar Ralla<pralla@miraclesoft.com>
 *
 * File : ApplicationConstants.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved. MIRACLE
 * SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
package com.mss.mirage.util;

/**
 *
 * @author Praveen Kumar Ralla<pralla@miraclesoft.com>
 *
 * This Class.... ENTER THE DESCRIPTION HERE
 */
public class ApplicationConstants {

    public static final String TEAM_CACHE = "TeamMapKey";
    public static final String ATTACHMENTS_PATH = "Attachments.Path";
    public static final String DEFAULT_ORG = "All";
    public static final String DEFAULT_EMP_STATUS = "All";
    public static final String DEFAULT_EMP_REPORTS_TO = "dReportsToKey";
    public static final String EMP_CURRENT_STATUS = "empCurrentStatusKey";

    /*START:Session Variable for Customer*/
    public static final String SESSION_CUST_USER_ID = "custUserId";
    public static final String SESSION_CUST_NAME = "custName";
    public static final String SESSION_CUST_ID = "custId";
    public static final String SESSION_CUST_ACC_ID = "accountId";
    /*END:Session Variable for Customer*/
    //reports
    public static final String EMP_REPORT_TYPES_LIST = "empReportTypeKey";
    //Start: Projects
    //Start:Project Issues
    public static final String PROJ_QS_ISSUES_LIST = "projqsIssueList";
    public static final String ISSUE_NAVIGATE = "issueNavigate";
    public static final String SELECT_ISSUE_NAVIGATE = "selectNavigate";
    //End:Project Issues
    public static final String PROJ_ISSUE_ACCOUNT_ID = "projIssueAccId";
    //public static final String PROJECT_OPTIONS="projectOptions";
    public static final String PROJECT_ATTACH_TYPE_OPTIONS = "projectAttachTypeKey";
    public static final String PROJECT_TYPE_OPTIONS = "projectTypeKey";
    public static final String PROJECT_STATUS_TYPE_OPTIONS = "projectStatusTypeKey";
    public static final String PROJECT_OPTIONS = "projectOptions";
    public static final String PROJECTS_OPTIONS = "projectKey";
    public static final String SUBPROJECTS_OPTIONS = "SubProjectKey";
    public static final String TOOLS_OPTIONS = "toolKey";
    public static final String ISSUES_OPTIONS = "issueKey";
    public static final String EMP_OPTIONS = "employeeKey";
    public static final String ATTACHMENT_OPTIONS = "attachmentKey";
    //End: Projects
    //End: Projects
    //Start:Employee Issues
    public static final String ISSUE_CATEGORY_OPTIONS = "categoryKey";
    public static final String ISSUE_STATUS_OPTIONS = "issueStatusKey";
    public static final String MANAGERS_OPTIONS = "managersKey";
    public static final String QS_ISSUES_LIST = "qsIssueList";
    //End: Employee Issues
    // by rajani kanth
    public static final String EMP_HEALTH_INSURANCE_TYPES = "empHealthInsuranceKey";
    public static final String IMMIGRATION_STATUS_OPTIONS = "immigrationStatusKey";
    // vendor
    public static final String QS_ALL_VEND_LIST = "qsAllVendList";
    //Marketing
    public static final String TECH_LEAD_OPTIONS = "techLeadKey";
    public static final String QS_ACTIVITY_QUERY = "activeQuery";
    public static final String QUERY_STRING = "queryString";
    public static final String QS_EMP_LIST = "qsEmpList";
    public static final String QS_USER_LIST = "qsUserList";
    public static final String QS_USER_LIST_OPS = "qsUserListOps";
    public static final String QS_ALL_ACC_LIST = "qsAllAccList";
    public static final String QS_OTHER_ACC_LIST = "qsMyAccList";
    public static final String QS_ACTIVITY_LIST = "qsActivityList";
    public static final String QS_COUNT_DASHBOARDTERRITORY_LIST = "qsterritorylist";
    public static final String QS_COUNT_DASHBOARDACTIVITY_LIST = "qsdahboardactivitylist";
    public static final String QS_TEAM_ACTIVITIES_LIST = "qsTeamActivityList";
    public static final String QS_ACC_ADD = "qsAccAdd";
    public static final String QS_GS_LIST = "qsGreenSheetList";
    public static final String QS_CRM_CONTACT_LIST = "qsCrmContactList";
    public static final String QS_CRM_CONTACT_LIST_FROM = "qsCCLFrom";
    public static final String SUBMIT_FROM_ACC_LIST = "accountListSubmitFrom";
    //Addmin
    public static final String ASSINGNEDROLES_OPTIONS = "assignedRolesKey";
    public static final String NOTASSINGNEDROLES_OPTIONS = "notAssignedRolesKey";
    public static final String MODULE_OPTIONS = "moduleKey";
    public static final String OP_PROJECT_TYPE = "projType";
    public static final String RESULT_MSG = "resultMessage";
    public static final String SP_ADD = "Ins";
    public static final String SP_EDIT = "Upd";
    public static final String SESSION_IS_USER_MANAGER = "isUserManager";
    public static final String SESSION_IS_TEAM_LEAD = "isUserTeamLead";
    public static final String SESSION_USER_ID = "userId";
    public static final String SESSION_EMP_ID = "empId";
    // public static final String REQUEST_EMP_ID = "requestEmpId";
    public static final String SESSION_REPORTSTO_ID = "reportsToId";
    public static final String SESSION_REPORTSTO_NAME = "reportsToName";
    public static final String SESSION_ROLE_ID = "roleId";
    public static final String SESSION_ROLE_NAME = "roleName";
    public static final String SESSION_LEFT_MENU = "leftMenu";
    public static final String SESSION_USER_NAME = "userName";
    public static final String SESSION_DELETE_ACTION = "deleteAction";
    public static final String SESSION_ACC_SEARCH_ACTION = "crmAccSearchAction";
    public static final String SESSION_IS_REQ_FROM_SEARCH = "isRequestFromSearch";
    public static final String SESSION_MY_TEAM_MAP = "myTeamMap";
    public static final String SESSION_MY_DEPT_ID = "myDeptId";
    //new varible on 09/04/12 
    public static final String CRM_CAMPAIGN_OPTIONS = "campaignNamesKey";
    public static final String SALUTATION_OPTIONS = "salutationKey";
    public static final String GENDER_OPTIONS = "genderKey";
    public static final String MARITAL_STATUS_OPTIONS = "maritalStatusKey";
    public static final String PREFERED_QUESTION_OPTIONS = "preferedQuestionKey";
    public static final String COUNTRY_OPTIONS = "countryKey";
    public static final String STATES_OPTIONS = "statesKey";
    //Country code for USA
    public static final int USA_STATES = 6;
    public static final String CURRENT_STATUS_OPTIONS = "currrentStatusKey";
    public static final String CURRENT_ROLE_OPTIONS = "currrentRoleKey";
    public static final String ROLES_OPTIONS = "rolesKey";
    public static final String OFFSHORE_PRACTISE_MANAGER = "offshorePractiseManagerList";
    public static final String EMP_ACTIVITY_OPTIONS = "empActivityKey";
    public static final String ACTIVITY_PRIORITY_OPTIONS = "activityPriorityKey";
    public static final String TIME_OPTIONS = "timeKey";
    public static final String QS_MY_TEAMTIMESHEETS_LIST = "myTeamTimeSheetList";
    public static final String TIME_SHEET_STATUS_OPTION = "timeSheetStatusKey";
    public static final String LKORGANIZATION_OPTION = "lkOrganizationKey";
    public static final String DEPARTMENT_OPTION = "departmentKey";
    public static final String PRACTICE_OPTION = "practiceKey";
    public static final String INDUSTRY_OPTION = "industryKey";
    public static final String TEAM_OPTION = "teamKey";
    public static final String REC_CONSULTANT_OPTION = "recConsultantKey";
    public static final String CRM_ACCOUNT_TYPE_OPTION = "crmAccountKey";
    public static final String CRM_ACCOUNT_STATUS_OPTION = "crmAccountStatusKey";
    public static final String CRM_ACTIVITY_TYPE_OPTIONS = "crmActivityTypeKey";
    public static final String CRM_ACTIVITY_PRIORITY_OPTIONS = "crmActivityPriorityKey";
    public static final String CRM_ADDRESS_OPTIONS = "crmAddressKey";
    public static final String OBJECT_OPTIONS = "objectKey";
    public static final String EMP_TYPE_OPTIONS = "empTypeKey";
    public static final String CRM_OPS_STAGE_OPTIONS = "stageKey";
    public static final String CRM_OPS_TYPE_OPTIONS = "typeKey";
    public static final String TITLE_TYPE_OPTIONS = "titleTypeKey";
    public static final String PO_LINE_CATEGORY_OPTIONS = "PoLineCategoryKey";
    public static final String PO_LINE_OPTIONS = "PoLineKey";
    public static final String PO_STATUS_OPTIONS = "PoStatusKey";
    public static final String INVOICE_STATUS_OPTIONS = "invoiceStatusKey";
    public static final String CRM_NOTE_OPTIONS = "crmNoteKey";
    public static final String CRM_CAMPAIGN_CONTACTS_OPTIONS = "crmCampaignContactsKey";
    public static final String CRM_CAMPAIGN_STATUS_OPTIONS = "crmCampaignStatusKey";
    public static final String CRM_CAMPAIGN_TYPE_OPTIONS = "crmCampaignTypeKey";
    public static final String CRM_CONTACT_STATUS_OPTIONS = "crmContactStatusKey";
    public static final String CRM_CONTACT_TYPE_OPTIONS = "crmContactTypeKey";
    public static final String GREEN_SHEET_UNITS_OPTIONS = "greensheetUnitsKey";
    public static final String GREEN_SHEET_VP_SALES_OPTIONS = "greensheetVPSalesKey";
    public static final String GREEN_SHEET_EXPENSES_OPTIONS = "greenSheetExpensesKey";
    public static final String GREEN_SHEET_CREATION_STATUS_OPTIONS = "greensheetCreationStatusKey";
    public static final String HIBERNATE_SESSION_FACTORY_KEY = "hibernateSessionFactoryKey";
    public static final String CRM_BDM_OPTIONS = "crmBDMList";
    public static final String GEENSHEET_UNTIS_OPTIONS = "greenSheetUntiskey";
    public static final String GEENSHEET_EXPENCES_OPTIONS = "greenSheetExpenceskey";
    public static final String GEENSHEET_CURRENCY_OPTIONS = "greenSheetCurrencykey";
    public static final String GEENSHEET_STAUTS_OPTIONS = "greenSheetStatuskey";
    public static final String GEENSHEET_POSTAUTS_OPTIONS = "greenSheetPOStatuskey";
    public static final String GEENSHEET_SCOPEOFWORK_OPTIONS = "greenSheetScopeOfWorkkey";
    public static final String GEENSHEET_VPSALES_OPTIONS = "greenSheetVPSaleskey";
    public static final String GEENSHEET_PAYMENTTERMS_OPTIONS = "greenSheetPaymentTermskey";
    public static final String GEENSHEET_VENDORNAMES_OPTIONS = "greenSheetVendorNamesKey";
    public static final String GEENSHEET_CUSTOMERNAMES_OPTIONS = "greenSheetCustomerNamesKey";
    public static final String CRM_ACTIVITY_STATUS_OPTIONS = "crmActivityStatusKey";
    public static final String PRIORITY_OPTIONS = "priorityKey";
    public static final String REGION_OPTIONS = "regionKey";
    //phone log
    public static final String QS_PHONE_LIST = "qsPhoneList";
    public static final String QS_JOB_LIST = "qsJobList";
    public static final String SESSION_EMP_WORKPHNO = "workPhoneNo";
    public static final String SESSION_EMP_TITLE = "title";
    //Appraisal
    public static final String QS_EMP_APPRAISAL_LIST = "empAppraisalList";
    //Employee Leave Approvals List
    public static final String EMP_LEAVE_APPROVAL_LIST = "employeeLeaveList";
    //user Roles existing in Hubble
    public static final String SESSION_MY_ROLES = "myRoles";
    //Application Context Path
    public static final String CONTEXT_PATH = "Hubble";
    //Venus Report List;
    public static String EMP_VENUS_REPORT_LIST = "empVenusList";
    // Opportunities Dashboard Queries
    public static String QS_TEAM_OPPORTUNITY_LIST = "qsTeamOpplist";
    public static String QS_MY_ASSIGN_OPPORTUNITY_LIST = "qsMyAssignOpplist";
    public static String WORKING_COUNTRY = "workCountryList";
    public static String UNTOUCH_SEARCH = "unTouchAccountSearch";
    //New variable
    public static String AFTER_UPDATEPRIMARYTEAM = "afterupdateprimaryteam";
    public static final String ORG_MAP = "orgmap";
    public static String ISSUE_TO_TASK = "issuetotask";
    //New variable
    public static String GREENSHEETSTATUS_BEFORE = "greensheetstatusbefore";
    public static final String SESSION_MY_TEAM_MAP_FOR_LEAVESEARCH = "MyTeamMapForLeaveSearch";
    //new on 10162012 for assign Accounts for BDM
    // public static String TITLETYPEID="titleTypeID";
    public static String Living_COUNTRY = "livingCountryList";
    // Consultant search backtoList button......
    public static String IS_SEARCH = "isSearch";
    // For navigation 
    public static String ISSUENAVTO = "issuenavto";
    //new on 06122013 for issue reminder id
    public static String ISSUE_REMINDER_Id = "issuereminderid";
    public static String TASKNAVTO = "tasknavto";
    //new on 06122013 for issue reminder id
    public static String TASK_REMINDER_Id = "taskreminderid";
    public static final String QS_TASKS_LIST = "qsTaskList";
    //new on 07162013 for resumesubmission
    public static String RESUME_REC_ID = "resumeRecId";
    public static String RESUME_REQUIREMENT_ID = "resumeRequirementId";
    public static String RESUME_CONSULTANT_ID = "resumeConsultantId";
    public static String RESUME_ATTACHMENT_ID = "resumeAttachmentId";
    public static final String ECERT_QUESTIONS_MAP = "ecertQuestionsMap";
    public static final String ECERT_CURRENT_EXAM_KEY = "ecertCurrentExamKey";
    public static final String ECERT_MY_EXAM_RESULT_QUERY = "ecertMyExamResultQuery";
    //new for ecert exam creation 
    public static final String ECERT_SUBTOPIC_ID = "ecertSubTopicId";
    public static final String ECERT_TOTAL_QUESTIONS = "ecertTotalQuestions";
    public static final String ECERT_QUESTIONID_FORUPDATE = "ecertQuestionIdForUpdate";
    public static final String QS_EXAM_LIST = "qsExamList";
    public static final String ECERT_REVIEW_LIST = "ecertReviewList";
    public static final String ECERT_MY_EXAM_KEYS_QUERY = "ecertMyExamKeysQuery";
    public static final String ECERT_DISTINCT_SUBTOPICIDS = "ecertDistinctSubTopicIds";
    public static final String ECERT_VALIDATE_KEY = "ecertValidationKey";
    /*
     **Ajay chenages 08072013
     **/
    public static final String QS_AUTHORED_EXAM_LIST = "qsAuthoredExamList";
    public static final String QS_QUESTION_SEARCH_LIST = "qsQuestionSearchList";
    /*Cre App constants start
     */
    public static final String REQ_ERR_MESSAGE = "reqErrorMessage";
    // public static final String SES_CONSULTANT_ID="sesConsultantId";
    //  public static final String SES_CONSULTANT_LOGIN_ID="sesConsultantLoginId";
    public static final String SES_CONSULTANT_NAME = "sesConsultantName";
    /*Cre App constants End
     */
    /**
     * To mantain isCRETeam In session
     */
    public static final String SESSION_IS_CRE_TEAM = "isCRETeam";
    public static final String SESSION_IS_SFLAG = "sflag";
    public static final String SESSION_CONSULTANT_IDS = "consultantIsList";
    public static final String SESSION_CRE_MIN_MARKS = "creMinMarks";
    public static final String SESSION_CRE_TOTAL_QUESTIONS = "creTotalQuestions";
    public static final String SESSION_CRE_TOTAL_MARKS = "creTotalMarks";
    public static final String SESSION_CRE_SUBTOPICS = "creExamTopics";
    public static final String SESSION_CRE_DURATION = "creExamDuration";
    public static final String EXP_MAP = "expmap";
    public static final String SESSION_TEAM_NAME = "teamName";
    public static final String SESSION_CEO = "ceo";
    public static final String SESSION_CRE_ExamIds_List = "examIdsList";
    //Sudhakar ghandikota enhancement
    public static final String SESSION_CUST_DESIG = "designation";
    public static final String SESSION_EMPTYPE = "empType";
    public static final String SESSION_CUSTOMER_PROJECTIDS = "sessionCustomerProjectIds";
    public static final String SESSION_RESOURCETYPE = "resourceName";
    //new
    public static final String SESSION_CUSTOMER_ROLE = "customerRole";
    public static final String SESSION_ASSOCIATED_PROJECTSIDS = "associatedProjectIds";
    public static final String SESSION_VENDOR_USER_ID = "vendorUserId";
    public static final String SESSION_PMO_ACTIVITY = "empPmoActivity";
    public static final String QS_PROJECT_TEAM_LIST = "projectTeamList";
    //new
    public static final String SESSION_IS_PMO = "isPMO";
    //new
    public static final String EMP_TECH_REVIEWS = "empTechReviews";
    public static final String SESSION_REPORTSTOFLAG = "sessionReportsToFlag";
    // Dual changes
    public static final String SESSION_SEC_TEAM = "sessionSecondaryTeam";
    public static final String EMP_REVIEWS_LIST = "empReviewsList";
    public static final String IS_REQUIREMENT_ADMIN = "isRequirementAdmin";
    public static final String COMPETITION_BOARD_LIST = "competitionBoardList";
    public static final String JOB_POSTING_FLAG = "jobPostingFlag";
    public static final String SOURCING_FLAG = "sourcingFlag";
    public static final String NO_DUES_REMAINDER = "noDueRemainders";
    public static final String NO_DUES_APPROVALS = "noDueApprovers";
    public static final String APPRECIATION_SEARCH = "apprecaitionQuery";
    public static final String SESSION_EMP_PRACTICE = "sessionEmpPractice";
    public static final String ITTEAM = "itTeam";
    public static final String INVESTMENT_DETAILS = "investmentDetails";
    public static final String CRM_ACTIVITY_TYPE_OPTIONS_ALL = "crmAllActivityTypeKey";
    public static final String SES_EMP_LOCATION = "sessionEmpLocation";
    public static final String EMEET_POSTING_FLAG = "emeetPostingFlag";
    public static final String QS_CUSTOMER_PROJECTS_LIST = "customerProjectsList";
    public static final String SESSION_EMPLOYEE_TYPE_ID = "sessionEmployeeTypeId";
    public static final String SES_EMP_NO = "sessionEmpNo";
    public static final String PF_EXCEL_ACCESS = "pfExcelAccess";
    public static final String OPPORTUNITY_SOFTWARE_ACCESS = "opportunitySoftwareAccess";
    public static final String PAYROLL_AUTH_CHECK = "payrollAuthCheck";
    public static final String SESSION_EMAIL="email";
    public static final String SESSION_HIERARCHY_ACCESS="sessionHierarchyAccess";
    public static final String SESSION_CC_ACCESS="sessionCCAccess";
    
    
  public static final String SESSION_MCERT_ExamIds_List = "sessionMcertExamIdsList";
    public static final String SESSION_Mcert_MIN_MARKS = "sessionMcertMinMarks";
     public static final String MCERT_QUESTIONS_MAP = "sessionMcertQuestionsMap";
      public static final String MCERT_CURRENT_EXAM_KEY = "sessionMcertCurrentExamKey";
       public static final String MCERT_MY_EXAM_RESULT_QUERY = "mcertMyExamResultQuery";
          public static final String MCERT_REVIEW_LIST = "mcertReviewList";
              public static final String MCERT_REQ_ERR_MESSAGE = "mcertReqErrorMessage";
              public static final String REQUIREMENT_BY_CUSTOMER_ACCESS = "requirementByCustomerAccess";
               public static final String SESSION_PMO_ACTIVITY_ACCESS = "empPmoActivityAccess";
               public static final String SESSION_IS_ADMIN_ACCESS = "isAdminAccess";
                public static final String BDM_ASSOCIATIONS_ACCESS = "bdmAssociationAccess";

//              
//              public static final String IS_ADMIN = "isAdmin";
//              public static final String PMO_ACTIVITY_ACCESS = "pmoActivityAccess";
              
              
              
}