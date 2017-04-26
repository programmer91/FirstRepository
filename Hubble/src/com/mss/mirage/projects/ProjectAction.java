 /*
 * ProjectsAction.java
 *
 * Created on December 3, 2007, 3:16 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package com.mss.mirage.projects;

import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.HibernateDataProvider;
import com.mss.mirage.util.AuthorizationManager;
import com.mss.mirage.util.DataUtility;
import com.mss.mirage.util.FileUploadUtility;
import com.mss.mirage.util.ServiceLocator;
import com.mss.mirage.util.ServiceLocatorException;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import com.mss.mirage.util.Properties;
import java.util.*;
import org.apache.struts2.interceptor.ParameterAware;

/**
 *
 * @author miracle
 */
public class ProjectAction extends ActionSupport implements ServletRequestAware, ParameterAware {

    /** Creating a reference variable for HttpServletRequest. */
    private HttpServletRequest httpServletRequest;
    /** Creating a reference variable for HttpSession. */
    private HttpSession httpSession;
    //private Map sessionMap;
    /** Creating a reference variable for HibernateDataProvider. */
    private HibernateDataProvider dataProvider;
    /** Creating a reference variable for DataSourceDataProvider. */
    private DataSourceDataProvider dataSourceDataProvider;
    /* Creating insertedRows to check the ResultType of methods*/
    private int insertedRows;
    /* Creating updatedRows to check the ResultType of methods*/
    private int updatedRows;
    /**
     * The resultType means the return type of the method.
     * whether the return type is Success or failure or other.
     */
    private String resultType;
    /**
     * The resultMessage is used to display the stored info. in the form
     */
    private String resultMessage;
    /** The String submitFrom is used for getting the value from the form
     * whether the value is SEARCH OR NOT OR NAVIGATE
     */
    private String submitFrom;
    /* Creating userRoleId to store the userRoleId */
    private int userRoleId;
    /** Creating a reference variable for <code>ProjectService</code> class .
     *  { @link com.mss.mirage.projects.ProjectService }
     */
    private ProjectService projectService;
    /** Creating a reference variable for <code>ProjectVTO</code> class .
     *  { @link com.mss.mirage.projects.ProjectVTO }
     */
    private ProjectVTO currentProject;
    /** Creating a reference variable for <code>SubProjectVTO</code> class .
     *  { @link com.mss.mirage.projects.SubProjectVTO }
     */
    private SubProjectVTO currentSubProject;
    /** Creating a reference variable for <code>MapVTO</code> class .
     *  { @link com.mss.mirage.projects.MapVTO }
     */
    private MapVTO currentMapProject;
    /** Creating a reference variable for <code>IssueVTO</code> class .
     *  { @link com.mss.mirage.projects.IssueVTO }
     */
    private IssueVTO currentIssue;
    /* Start -- variables for project */
    private int customerId;
    private String prjName;
    private String prjManagerUID;
    /* End -- variables for project */
    /* Start -- variables for subproject */
    private String subPrjName;
    private String teamLeadUID; // Team Lead Role
    /* End -- variables for subproject */
    /* Start -- variables for Map */
    /* The String projectNames or projectNames is used to get the auto-generated path */
    private String projectNames;
    /* The String subProjectNames or subProjectNames is used to get the auto-generated path */
    private String subProjectNames;
    /* The String mapName is used for storing the MapName of Map */
    private String mapName;
    /* The String bussinessDomain or bussinessDomain is used to get the auto-generated path */
    private String bussinessDomain;
    /* The String mapTools is used for mapTools */
    private String mapTools;
    /* The String projectManager is used for projectManager */
    private String projectManager;
    /* The String techLead is used for techLead attachment*/
    private String techLead;
    /* The String mapper is used for mapper */
    private String mapper;
    /* The String tester is used for tester */
    private String tester;
    /* End -- variables for Map */
    /* Start -- variables for Issue */
    /* Creating issueId to store the issueId */
    private int issueId;
    /* The String issueNames is used for storing the issueNames name to which Issue belons to */
    private String issueNames;
    /* The String issueStates is used for storing the issueStates name to which Issue belons to */
    private String issueStates;
    /* The String issueTypes is used for storing the issueTypes name to which Issue belons to */
    private String issueTypes;
    /* The String assignedToUIDs is used for storing the assignedToUIDs name to which Issue belons to */
    private String assignedToUIDs;
    /* The Date datesCreated is used for storing the datesCreated name to which Issue belons to */
    private Date datesCreated;
    /* The String descriptions is used for storing the descriptions name to which Issue belons to */
    private String descriptions;
    /* The String createdBy is used for storing the createdBy of Issue */
    private String createdBy;
    /* The String datesCreatedOne is used for editing and convert from DATE to STRING */
    private String datesCreatedOne;
    /* End -- variables for Issue */
    /**START: Common Variables for Project,Sub Projects,Maps,Issue,Attachments*/
    private int id;
    private int accountId;
    private int projectId;
    private int subProjectId;
    /* Creating mapId to store the id for Map in the database*/
    private int mapId;
    private String projectType;
    private Date startDate;
    private Date endDate;
    private int totalResources;
    private String description;
    /* The String currentState is used for storing the currentState for SubProject, Map of the Project */
    private String currentState;
    /* The String accountName is used to store the name for partcular Account in the database*/
    private String accountName;
    /* The String projectName is used to store the name for partcular Project in the database*/
    private String projectName;
    /* The String projectName is used to store the name for partcular SubProject in the database*/
    private String subProjectName;
    /* The String issueName is used for storing the issueName for Issues */
    private String issueName;
    /* The String startDateOne is used for editing and convert from DATE to STRING */
    private String startDateOne;
    /* The String endDateOne is used for editing and convert from DATE to STRING */
    private String endDateOne;
    /**END: Common Variables for Project,Sub Projects,Maps*/
    /* Start -- variables for Attachment */
    /*Creating objectType to store the name of Object for project, Subproject, map,issue */
    private String objectType;
    /*Creating objectId to store the AccountId for project, Subproject, map,issue */
    private int objectId;
    /** The objectName is used to store the Name of the Object (i.e Project or SubProject)*/
    private String objectName;
    /* The String attachmentFileName or attachmentName is used to store the complete path in database*/
    private String attachmentName;
    /* The upload is used for storing the uploaded file */
    private File upload;
    /* The String Version is used for version attachment*/
    private String version;
    /* The upload is used for storing the uploadFileName file */
    private String uploadFileName;
    /* The String attachmentLocation or attachmentPath is used to get the auto-generated path */
    private String path;
    /* The String uploadedBy is used to store the user login*/
    private String uploadedBy;
    /* The dateOfUpload is used to store for particular attachment */
    private Timestamp dateOfUpload;
    /* End -- variables for Attachment */
    /* Start -- maps, Lists */
    /** Creating new instance for projectManagersMap. */
    private Map projectManagersMap;
    /** Creating new instance for teamLeadMap. */
    private Map teamLeadMap;
    /** Creating new instance for prjectTypeList. */
    private List prjectTypeList;
    /** Creating new instance for projectss. */
    private List projectss = new ArrayList();
    /** Creating new instance for subProjectsMaps. */
    private List subProjectsMaps = new ArrayList();
    /** Creating new instance for toolsListMaps. */
    private List toolsListMaps = new ArrayList();
    /** Creating new instance for techLeadMaps. */
    private Map techLeadMaps;
    /** Creating new instance for mapperMaps. */
    private Map mapperMaps;
    /** Creating new instance for testerMaps. */
    private Map testerMaps;
    /** Creating new instance for projectManagerLists. */
    private Map projectManagerLists;
    /** Creating new instance for projectStatusTypeList. */
    private List projectStatusTypeList;
    /** Creating new instance for projectStatusTypeList. */
    private List mapStatusTypeList;
    /** Creating new instance for issueTypeMaps. */
    private List issueTypeMaps = new ArrayList();
    /** Creating new instance for employeesMaps. */
    private List employeesMaps = new ArrayList();
    /** Creating new instance for attachmentsMaps. */
    private List attachmentsMaps = new ArrayList();
    /* End -- maps, Lists */
    /**
     * A Map object with an parameters object of  read from a
     * full List of    Submit Form  parameters .
     */
    private Map parameters;
    private List addedEmployeeList;
    private Map allDevelopmentMap = new TreeMap();
    private Map addedDevelopmentMap = new TreeMap();
    private Map addTeamDevelopmentMap = new TreeMap();
    private String userRoleName;
    private String operationProject;
    /** Creating new instance for projectAttachmentTypeMap. */
    private List projAttachTypeList;
    private String attachType;
    /* this typeId is used to store projectId,SubprojectId,MapId to differentate in tblPrjAttachments*/
    private int typeId;
    private String strMapId;
    private String strSubProjectId;
    private String strId;
    private String strAccountId;
    private String projects;
    private List projectsList;
    //For Project hierarch 03142014
    private int projectEmpId;
    private String currentAction;
    private List empTypeListReports;
    private Map projectReportsToMap;
    private Map projectEmployeeMap;
    private int projectReportsTo;
    private List projectEmployees;
    private String projectEmpName;
    private String projectReportsToName;
    //new by Ajay for project team 
    private String preAssignEmpId;
    private String resType;
    private String email;
    //private String customerId;
    // private String projectId;
    private String assignedToUID;
    private boolean isBillable;
    private String dateAssigned;
    private String dateClosed;
    private String status;
    private boolean isPrimary;
    private String resTitle;
    private String customerName;
    private Map clientCurrencyMap;
    private HibernateDataProvider hibernateDataProvider;
    private String utilisation;
    private String rateType;
    private String currency;
    //new
    private Map myProjects;
    private Map projectTeamReportsTo;
    private String workPhone;
    private String mobilePhone;
    //new
    private Map rolesMap;
    //new
    private Map myAccounts;
    //private int projectReportsTo;
    //new
    private boolean isPMO;
    private String modifiedBy;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    private String resultMessage1;
    // private String currentAction;
    // Dual reports to changes start
    private int secProjectReportsTo;
    private boolean isDualReportingRequired;
    private int temp;
 private Map preSalesMap=new HashMap();
    private String preSalesMgrId;
    
    
private String pmo;
      private Map pmomanagerMap=new HashMap();
      
       private List practiceList;
    private int onSitePlan;
    private int onSiteActual;
    private String customer;
    private int offShorePlan;
    private int offShoreActual;
    private String costModel;
    private int nearShorePlan;
    private int nearShoreActual;
    private String sector;
    private String startDatePlan;
    private String startDateActual;
    private String complexity;
    private String priority;
    private String endDatePlan;
    private String endDateActual;
    private String comments;
    private String state;
    private String software;
    private String practice;
    private Map riskAssignedTo = new HashMap();
    private String assignedTo;
    private String impact;
    private String closedDate;
    private String resolution;
    // Dual reports to changes end
    
 
     private Map taskAssignedTo = new HashMap();
       private String durationTotheTask;
       private String taskStartDate;
        private Map projectIssueType = new HashMap();
        private Map offshoreDelLeadMap=	new HashMap();
	private Map offshoreLeadMap=	new HashMap();
	private Map onsiteLeadMap=	new HashMap();
        private String offshoreDelLead;
        private String offshoreTechLead;
        private String onsiteLead;
        private String risk;
        private String resources;
        private String schedule;
		private Map pmoNamesMap;
                private int availableUtl;
                private String empId;
                private String projectStartDate;
                private String backFlag;
                
                private String projectEndDate;
    /** Creates a new instance of ProjectsAction */
                private String skillSet;
                
                 private String billableStartDate;
                private String billableEndDate;
                private String existedStatus;
                private String empProjectStatus;
                private List empProjectRolls;
                
    public ProjectAction() {
    }

    /**
     * The prepare method used for getting navigation on forms using
     * a class ApplicationConstants.
     * And these variables are getting initailised when we see the form
     * everytime because prepare method is also called everytime.
     *
     * @throws Exception
     *         If a required property cannot be accessed.
     *
     * @return The return type of this method is a String
     *         which depends on the action being performed.
     *
     */
    public String prepare() throws Exception {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null || httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID) != null) {
            dataProvider = HibernateDataProvider.getInstance();


            if ("Project".equalsIgnoreCase(getObjectType())) {
                setObjectName(dataProvider.getAccountName(getAccountId()));
               // setObjectName(dataProvider.getAccountName(getObjectId()));
                setProjectName(dataProvider.getProjectName(getProjectId()));
            } else if ("SubProject".equalsIgnoreCase(getObjectType())) {
                setObjectName(dataProvider.getAccountName(getObjectId()));
                setProjectName(dataProvider.getProjectName(getProjectId()));
                setSubProjectName(dataProvider.getSubProjectName(getSubProjectId()));
            } else if ("Map".equalsIgnoreCase(getObjectType())) {
                setObjectName(dataProvider.getAccountName(getObjectId()));
                setProjectName(dataProvider.getProjectName(getProjectId()));
                setSubProjectName(dataProvider.getSubProjectName(getSubProjectId()));
                setMapName(dataProvider.getMapName(getMapId()));
            } else if ("Issue".equalsIgnoreCase(getObjectType())) {
                setObjectName(dataProvider.getAccountName(getObjectId()));
                setProjectName(dataProvider.getProjectName(getProjectId()));
                setSubProjectName(dataProvider.getSubProjectName(getSubProjectId()));
                setMapName(dataProvider.getMapName(getMapId()));
                setIssueName(dataProvider.getIssueName(getIssueId()));
            } else if ("Vendor".equalsIgnoreCase(getObjectType())) {
                setObjectName(dataProvider.getAccountName(getObjectId()));
            }/*else if("Account".equalsIgnoreCase(getObjectType())){
            setObjectName(dataProvider.getAccountName(getObjectId()));
            }*/
            setProjAttachTypeList(dataProvider.getProjectAttachType(ApplicationConstants.PROJECT_ATTACH_TYPE_OPTIONS));

            resultType = SUCCESS;
        }
        return resultType;
    }

    /**
     * The prepareProjectAdd method used for getting all variables in the
     * select box and getting these values from a class called ApplicationConstants.
     * And these variables are getting initailised when we see the form
     * everytime because prepareProjectAdd method is also called everytime.
     *
     * @throws Exception
     *         If a required property cannot be accessed.
     *
     * @return The return type of this method is a String
     *         which depends on the action being performed.
     *
     */
    public String prepareProjectAdd() throws Exception {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null || httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID) != null) {
            dataSourceDataProvider = DataSourceDataProvider.getInstance();
            dataProvider = HibernateDataProvider.getInstance();
            setProjectManagersMap(dataSourceDataProvider.getEmployeeNamesUserIdByTitle("Project Manager"));
            setPrjectTypeList(dataProvider.getProjectType(ApplicationConstants.PROJECT_TYPE_OPTIONS));
            // setAllDevelopmentMap(dataSourceDataProvider.getEmployeeNamesWithoutAccount("GDC"));
            setAllDevelopmentMap(dataSourceDataProvider.getProjectTeamWithoutAccount(getId(), getAccountId()));
            setAddTeamDevelopmentMap(dataSourceDataProvider.getProjectEmployeeNames(getId()));
            setPreSalesMap(DataUtility.getInstance().getMapSortByValue(dataSourceDataProvider.getPresalesListByLoginId()));
            // setAddedDevelopmentMap(dataSourceDataProvider.getProjectEmployeeNames(getAccountId()));
            setPmomanagerMap(DataUtility.getInstance().getMapSortByValue(dataSourceDataProvider.getPmoMap()));
            setAccountName(dataProvider.getAccountName(getAccountId()));
            setPracticeList(dataSourceDataProvider.getPracticeByDepartment("GDC"));
            httpServletRequest.setAttribute("currentAccountId", String.valueOf(getAccountId()));
            setOffshoreDelLeadMap(DataSourceDataProvider.getInstance().getDeliveryLeadsByCountry("India"));
            setOffshoreLeadMap(DataSourceDataProvider.getInstance().getLeadNamesByCountry("India"));
            setOnsiteLeadMap(DataSourceDataProvider.getInstance().getLeadNamesByCountry("USA"));
            resultType = SUCCESS;
        }
        return resultType;
    }

    public String assignTeam() throws Exception {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null || httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID) != null) {
            String[] rightParams = (String[]) parameters.get("addedEmployeeList");
            int result = ServiceLocator.getProjectService().assignTeamForProject(rightParams, getAccountId());
            resultType = SUCCESS;
            prepareProjectAdd();
            resultMessage = "Team Has been successfully Added!";
            httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, resultMessage);
        }

        return resultType;
    }

    public String updateTeam() throws Exception {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null || httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID) != null) {
            //String[] rightParams =(String[])parameters.get("addedEmployeeList");
            dataProvider = HibernateDataProvider.getInstance();
            setProjectName(dataProvider.getProjectName(getId()));
            // int result =  ServiceLocator.getProjectService().upDateTeamForProject(rightParams,getAccountId(),getId(),getProjectName());
            int result = ServiceLocator.getProjectService().upDateTeamForProject(getAddedEmployeeList(), getAccountId(), getId(), getProjectName());
            prepareProjectAdd();
            resultMessage = "Team Assigned successfully !";
            httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, resultMessage);
            this.setId(getId());
            this.setAccountId(getAccountId());
            resultType = SUCCESS;
        }
        return resultType;
    }

    /**
     * The prepareMapAdd method used for getting all variables in the
     * select box and getting these values from a class called ApplicationConstants.
     * And these variables are getting initailised when we see the form
     * everytime because prepareMapAdd method is also called everytime.
     *
     * @throws Exception
     *         If a required property cannot be accessed.
     *
     * @return The return type of this method is a String
     *         which depends on the action being performed.
     *
     */
    public String prepareMapAdd() throws Exception {

        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null || httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID) != null) {

            dataSourceDataProvider = DataSourceDataProvider.getInstance();
            dataProvider = HibernateDataProvider.getInstance();

            setProjectss(dataProvider.getmarsProjects(ApplicationConstants.PROJECTS_OPTIONS));
            setSubProjectsMaps(dataProvider.getSubProjectsMap(ApplicationConstants.SUBPROJECTS_OPTIONS));
            setToolsListMaps(dataProvider.getToolsMap(ApplicationConstants.TOOLS_OPTIONS));

            setTechLeadMaps(dataSourceDataProvider.getEmployeeNamesUserIdByTitle("Tech Lead"));
            setMapperMaps(dataSourceDataProvider.getEmployeeNames1("Mapper"));
            setTesterMaps(dataSourceDataProvider.getEmployeeNames2("Tester"));
            setProjectManagerLists(dataSourceDataProvider.getEmployeeNames3("Project Manager"));

            setAccountName(dataProvider.getAccountName(getAccountId()));
            setProjectName(dataProvider.getProjectName(getId()));
            setSubProjectName(dataProvider.getSubProjectName(getSubProjectId()));

            setMapStatusTypeList(dataProvider.getProjectStatusType(ApplicationConstants.PROJECT_STATUS_TYPE_OPTIONS));

            httpServletRequest.setAttribute("currentAccountId", String.valueOf(getAccountId()));
            httpServletRequest.setAttribute("currentProjectId", String.valueOf(getId()));
            httpServletRequest.setAttribute("currentSubProjectId", String.valueOf(getSubProjectId()));
            httpServletRequest.setAttribute("currentMapId", String.valueOf(getMapId()));

            resultType = SUCCESS;
        }
        return resultType;
    }

    /**
     * The prepareSubProjectAdd method used for getting all variables in the
     * select box and getting these values from a class called ApplicationConstants.
     * And these variables are getting initailised when we see the form
     * everytime because prepareSubProjectAdd method is also called everytime.
     *
     * @throws Exception
     *         If a required property cannot be accessed.
     *
     * @return The return type of this method is a String
     *         which depends on the action being performed.
     *
     */
    public String prepareSubProjectAdd() throws Exception {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null || httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID) != null) {

            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("PREPARE_SUB_PRJ_ADD", userRoleId)) {
                dataSourceDataProvider = DataSourceDataProvider.getInstance();
                dataProvider = HibernateDataProvider.getInstance();

                setTeamLeadMap(dataSourceDataProvider.getEmployeeNamesUserIdByTitle("Team Lead"));
                setPrjectTypeList(dataProvider.getProjectType(ApplicationConstants.PROJECT_TYPE_OPTIONS));
                setProjectStatusTypeList(dataProvider.getProjectStatusType(ApplicationConstants.PROJECT_STATUS_TYPE_OPTIONS));

                setAccountName(dataProvider.getAccountName(getAccountId()));
                setProjectName(dataProvider.getProjectName(getId()));

                httpServletRequest.setAttribute("currentAccountId", String.valueOf(getAccountId()));
                httpServletRequest.setAttribute("currentProjectId", String.valueOf(getId()));

                resultType = SUCCESS;
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }

    /**
     * The prepareIssueAdd method used for getting all variables in the
     * select box and getting these values from a class called ApplicationConstants.
     * And these variables are getting initailised when we see the form
     * everytime because prepareIssueAdd method is also called everytime.
     *
     * @throws Exception
     *         If a required property cannot be accessed.
     *
     * @return The return type of this method is a String
     *         which depends on the action being performed.
     *
     */
    public String prepareIssueAdd() throws Exception {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null || httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID) != null) {

            userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();

            dataSourceDataProvider = DataSourceDataProvider.getInstance();
            dataProvider = HibernateDataProvider.getInstance();

            //setProjectss(dataProvider.getmarsProjects(ApplicationConstants.PROJECTS_OPTIONS));
            //setSubProjectsMaps(dataProvider.getSubProjectsMap(ApplicationConstants.SUBPROJECTS_OPTIONS));

            setIssueTypeMaps(dataProvider.getIssueNameMap(ApplicationConstants.ISSUES_OPTIONS));
            setEmployeesMaps(dataProvider.getEmployeesMap(ApplicationConstants.EMP_OPTIONS));

            setAccountName(dataProvider.getAccountName(getAccountId()));
            setProjectName(dataProvider.getProjectName(getId()));
            setSubProjectName(dataProvider.getSubProjectName(getSubProjectId()));
            if (getMapId() != 0) {
                setMapName(dataProvider.getMapName(getMapId()));
            } else {
                setMapName("");
            }



            httpServletRequest.setAttribute("currentAccountId", String.valueOf(getAccountId()));
            httpServletRequest.setAttribute("currentProjectId", String.valueOf(getId()));
            httpServletRequest.setAttribute("currentSubProjectId", String.valueOf(getSubProjectId()));
            httpServletRequest.setAttribute("currentMapId", String.valueOf(getMapId()));

            resultType = SUCCESS;
        }
        return resultType;
    }

    /**
     * The getProject method is called to edit the values from each particular project.
     * Here editing means when grid gets selected on the form and each particular project values
     * are to be displayed so that it can be used to edit.
     *
     * @param
     *
     *
     * @throws Exception
     *         If a required Project value cannot be edited or not from the database.
     *
     * @return The return type of this method is a String
     *         which depends on whether all the values are successfully edited or not.
     *
     */
    public String getProject() {


        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null || httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID) != null) {

            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";

            if (AuthorizationManager.getInstance().isAuthorizedUser("GET_PROJECT", userRoleId)) {
                try {
                    projectService = ServiceLocator.getProjectService();
                    dataSourceDataProvider = dataSourceDataProvider.getInstance();
                    setCurrentProject(projectService.getProject(getId()));
                    setAccountId(getCurrentProject().getCustomerId());

                    // setAllDevelopmentMap(dataSourceDataProvider.getEmployeeNamesWithoutAccount("GDC"));
                    setAllDevelopmentMap(dataSourceDataProvider.getProjectTeamWithoutAccount(getId(), getAccountId()));
                    setAddTeamDevelopmentMap(dataSourceDataProvider.getProjectEmployeeNames(getId()));


                    //setAddedDevelopmentMap(dataSourceDataProvider.getProjectEmployeeNames(getId()));

                    //setAddedDevelopmentMap(projectService.getProjectTeamByProjectId(getId()));
//                    setAllDevelopmentMap(dataSourceDataProvider.getEmployeeNamesWithoutAccount("GDC"));
//                    setAddedDevelopmentMap(dataSourceDataProvider.getProjectEmployeeNames(getId()));
setTaskAssignedTo(dataSourceDataProvider.getProjectRiskAssignedTo(getAccountId(), getId()));
                    setProjectIssueType(dataSourceDataProvider.getIssueTypeList());
                    
                   // System.out.println("listsize-->"+getTaskAssignedTo().size());
                    setDurationTotheTask("8");
                    setTaskStartDate(DateUtility.getInstance().getCurrentMySqlDate());
                    setDateClosed(DateUtility.getInstance().getCurrentMySqlDate());
                    prepareProjectAdd();

                    // start -- here we are setting attribute value for  project type
                    operationProject = dataSourceDataProvider.getProjectType(getId());

                    if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.OP_PROJECT_TYPE) != null) {
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.OP_PROJECT_TYPE);
                    }
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.OP_PROJECT_TYPE, operationProject);


                    httpServletRequest.setAttribute("currentAccountId", String.valueOf(getAccountId()));
                    httpServletRequest.setAttribute("currentProjectId", String.valueOf(getCurrentProject().getId()));

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
     * The getSubProject method is called to edit the values of SubProject from each particular SubProject.
     * Here editing means when grid gets selected on the form and each particular SubProject values
     * are to be displayed so that it can be used to edit.
     *
     * @param
     *
     *
     * @throws Exception
     *         If a required SubProject value cannot be edited or not from the database.
     *
     * @return The return type of this method is a String
     *         which depends on whether all the values are successfully edited or not.
     *
     */
    public String getSubProject() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null || httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID) != null) {

            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("GET_SUB_PROJECT", userRoleId)) {
                try {

                    dataProvider = HibernateDataProvider.getInstance();
                    dataSourceDataProvider = DataSourceDataProvider.getInstance();

                    projectService = ServiceLocator.getProjectService();
                    setCurrentSubProject(projectService.getSubProject(getSubProjectId()));
                    // Setting values to SubProjectVTO (currentSubProject).



                    setAccountId(dataProvider.getAccountIdByProjectId(getProjectId()));
                    setId(getCurrentSubProject().getProjectId());
                    setProjectId(getCurrentSubProject().getSubProjectId());



                    /*
                    // start -- here we are setting attribute value for  project type
                    operationProject = getProjectType();
                    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.OP_PROJECT_TYPE) != null){
                    httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.OP_PROJECT_TYPE);
                    }
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.OP_PROJECT_TYPE,operationProject);
                    // System.out.println("im in padd"+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.OP_PROJECT_TYPE).toString());
                     */

                    String accouId = String.valueOf(getAccountId());
                    // Converting int to String

                    if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.PROJ_ISSUE_ACCOUNT_ID) != null) {
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.PROJ_ISSUE_ACCOUNT_ID);
                    }
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.PROJ_ISSUE_ACCOUNT_ID, accouId);
                    //System.out.println("get account id from session > "+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.PROJ_ISSUE_ACCOUNT_ID));

                    prepareSubProjectAdd();

                    httpServletRequest.setAttribute("currentAccountId", String.valueOf(getAccountId()));
                    httpServletRequest.setAttribute("currentProjectId", String.valueOf(getId()));
                    httpServletRequest.setAttribute("currentSubProjectId", String.valueOf(getProjectId()));
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
     * The getMap method is called to edit the values of Map from each particular Map.
     * Here editing means when grid gets selected on the form and each particular map values
     * are to be displayed so that it can be used to edit.
     *
     * @param
     *
     *
     * @throws Exception
     *         If a required Map value cannot be edited or not from the database.
     *
     * @return The return type of this method is a String
     *         which depends on whether all the values are successfully edited or not.
     *
     */
    public String getMap() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null || httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID) != null) {

            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("GET_MAP", userRoleId)) {
                try {
                    dataProvider = HibernateDataProvider.getInstance();
                    projectService = ServiceLocator.getProjectService();

                    setCurrentMapProject(projectService.getMapProject(getMapId()));

                    setAccountId(dataProvider.getAccountIdByMapId(getMapId()));
                    setId(getProjectId());
                    setSubProjectId(getSubProjectId());

                    prepareMapAdd();
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
     * The getIssues method is called to edit the values of Issues from each particular Issue.
     * Here editing means when grid gets selected on the form and each particular Issue values
     * are to be displayed so that it can be used to edit.
     *
     * @param
     *
     *
     * @throws Exception
     *         If a required Issue value cannot be edited or not from the database.
     *
     * @return The return type of this method is a String
     *         which depends on whether all the values are successfully edited or not.
     *
     */
    public String getIssues() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null || httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID) != null) {

            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("GET_ISSUES", userRoleId)) {
                try {

                    dataProvider = HibernateDataProvider.getInstance();
                    projectService = ServiceLocator.getProjectService();

                    setCurrentIssue(projectService.getIssues(getIssueId()));

                    setAccountId(dataProvider.getAccountIdByIssueId(getIssueId()));
                    setId(getProjectId());
                    prepareIssueAdd();

                    httpServletRequest.setAttribute("currentAccountId", String.valueOf(getAccountId()));
                    httpServletRequest.setAttribute("currentProjectId", String.valueOf(getId()));
                    httpServletRequest.setAttribute("currentSubProjectId", String.valueOf(getSubProjectId()));
                    httpServletRequest.setAttribute("currentMapId", String.valueOf(getMapId()));
                    httpServletRequest.setAttribute("currentIssueId", String.valueOf(getIssueId()));

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
     * The getAttachment method is called to edit the values from each particular attachment.
     *
     * @param
     *
     *
     * @throws Exception
     *         If a required Account value cannot be edited or not from the database.
     *
     * @return The return type of this method is a String
     *         which depends on whether all the values are successfully edited or not.
     *
     */
    public String getAttachment() throws Exception {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null || httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID) != null) {

            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("GET_ATTACHMENT", userRoleId)) {
                try {


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

    /* Default method of ActionSupport Class*/
    public String execute() throws Exception {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null || httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID) != null) {

            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("PROJECT", userRoleId)) {
                resultType = prepareProjectAdd();
            }//END-Authorization Checking
        }//Close Sessoin Checking
        return resultType;
    }

    /**
     * The doProjectAdd method is used to store all the values submitted in the form to database.
     * Here all the values are being first initalized to a method addOrUpdateProject from the
     * ProjectService Class.
     * And from their the values are stored to the database.
     *
     * @throws Exception
     *         If the required Project value cannot be added to the database.
     *
     * @return The return type of this method is a String
     *         which depends on the action being completely
     *         performed or not.
     *
     */
    public String doProjectAdd() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null || httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("DO_PROJECT_ADD", userRoleId)) {
                try {
                    if (!("dbGrid".equalsIgnoreCase(getSubmitFrom()))) {
                        if (userRoleId == 4) {
                            setStatus("Active");
                        }
                        setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                        setCreatedDate(DateUtility.getInstance().getCurrentMySqlDateTime());

                        projectService = ServiceLocator.getProjectService();
                        insertedRows = projectService.addOrUpdateProject(this, "add");

                        if (insertedRows == 1) {
                            resultType = SUCCESS;
                            resultMessage = "<font color=\"green\" size=\"1.5\">Project has been successfully inserted!</font>";
                        } else {
                            resultType = INPUT;
                            resultMessage = "<font color=\"red\" size=\"1.5\">Sorry! Please Try again!</font>";
                        }

                        // start -- here we are setting attribute value for  project type
                        operationProject = getProjectType();
                        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.OP_PROJECT_TYPE) != null) {
                            httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.OP_PROJECT_TYPE);
                        }
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.OP_PROJECT_TYPE, operationProject);


                        httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, resultMessage);

                        setAccountId(getCustomerId());
                    }//Clsoe checking Submit From

                    prepareProjectAdd();

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
     * The doSubProjectAdd method is used to store all the values submitted in the form to database.
     * Here all the values are being first initalized to a method addOrUpdateSubProject from the
     * ProjectService Class.
     * And from their the values are stored to the database.
     *
     * @throws Exception
     *         If the required SubProject value cannot be added to the database.
     *
     * @return The return type of this method is a String
     *         which depends on the action being completely
     *         performed or not.
     *
     */
    public String doSubProjectAdd() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null || httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID) != null) {

            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            // Due to the above result after adding the values when u rotate the grid u will get accessfailed result type as default
            // for which u will get accessFailed (HAND) image
            if (AuthorizationManager.getInstance().isAuthorizedUser("DO_SUB_PROJECT_ADD", userRoleId)) {
                try {
                    if (!("dbGrid".equalsIgnoreCase(getSubmitFrom()))) {
                        projectService = ServiceLocator.getProjectService();

                        insertedRows = projectService.addOrUpdateSubProject(this, "add");

                        // System.out.println("insertedRows----->"+insertedRows);


                        if (insertedRows == 1) {
                            resultType = SUCCESS;
                            resultMessage = "<font color=\"green\" size=\"1.5\">Sub Project has been successfully inserted!</font>";
                        } else {
                            resultType = INPUT;
                            resultMessage = "<font color=\"red\" size=\"1.5\">Sorry! Please Try again!</font>";
                        }

                        httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, resultMessage);

                        setId(getProjectId());
                    }//Clsoe checking Submit From
                    prepareSubProjectAdd();
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
     * The doMapAdd method is used to store all the values submitted in the form to database.
     * Here all the values are being first initalized to a method addMap from the
     * ProjectService Class.
     * And from their the values are stored to the database.
     *
     * @throws Exception
     *         If the required Map value cannot be added to the database.
     *
     * @return The return type of this method is a String
     *         which depends on the action being completely
     *         performed or not.
     *
     */
    public String doMapAdd() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null || httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("DO_MAP_ADD", userRoleId)) {
                try {
                    if (!("dbGrid".equalsIgnoreCase(getSubmitFrom()))) {
                        int insertedRows = 0;

                        projectService = ServiceLocator.getProjectService();
                        insertedRows = projectService.addMap(this);

                        if (insertedRows == 1) {
                            resultType = "SUCCESS";
                            resultMessage = "<font color=\"green\" size=\"1.5\">Map has been successfully inserted!</font>";
                        } else {
                            resultType = "INPUT";
                            resultMessage = "<font color=\"red\" size=\"1.5\">Sorry! Please Try again!</font>";
                        }

                        httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, resultMessage);
                        httpServletRequest.setAttribute("resultMessage", resultMessage);
                    }//close checking submit form
                    prepareMapAdd();

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
     * The doIssueAdd method is used to store all the values submitted in the form to database.
     * Here all the values are being first initalized to a method addIssue from the
     * ProjectService Class.
     * And from their the values are stored to the database.
     *
     * @throws Exception
     *         If the required Issue value cannot be added to the database.
     *
     * @return The return type of this method is a String
     *         which depends on the action being completely
     *         performed or not.
     *
     */
    public String doIssueAdd() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null || httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("DO_ISSUE_ADD", userRoleId)) {
                try {

                    if (!("dbGrid".equalsIgnoreCase(getSubmitFrom()))) {
                        int isInserted = 0;

                        if ("Customer Manager".equalsIgnoreCase(userRoleName)) {
                            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID).toString());
                        } else {
                            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                        }

                        //setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());

                        projectService = ServiceLocator.getProjectService();
                        isInserted = projectService.addIssue(this);

                        if (isInserted == 1) {
                            resultType = "SUCCESS";
                            resultMessage = "<font color=\"green\" size=\"1.5\">Issue has been successfully inserted!</font>";
                        } else {
                            resultType = "INPUT";
                            resultMessage = "<font color=\"red\" size=\"1.5\">Sorry! Please Try again!</font>";
                        }

                        httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, resultMessage);
                        httpServletRequest.setAttribute("resultMessage", resultMessage);

                        setId(getProjectId());
                    }//checking submit form
                    prepareIssueAdd();

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

    public String prepareAttachmentAdd() throws Exception {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null || httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID) != null) {

            dataSourceDataProvider = DataSourceDataProvider.getInstance();
            dataProvider = HibernateDataProvider.getInstance();

            if ("Project".equalsIgnoreCase(getObjectType())) {
                setObjectName(dataProvider.getAccountName(getObjectId()));
                setProjectName(dataProvider.getProjectName(getProjectId()));
            } else if ("SubProject".equalsIgnoreCase(getObjectType())) {
                setObjectName(dataProvider.getAccountName(getObjectId()));
                setProjectName(dataProvider.getProjectName(getProjectId()));
                setSubProjectName(dataProvider.getSubProjectName(getSubProjectId()));
            } else if ("Map".equalsIgnoreCase(getObjectType())) {
                setObjectName(dataProvider.getAccountName(getObjectId()));
                setProjectName(dataProvider.getProjectName(getProjectId()));
                setSubProjectName(dataProvider.getSubProjectName(getSubProjectId()));
                setMapName(dataProvider.getMapName(getMapId()));
            } else if ("Issue".equalsIgnoreCase(getObjectType())) {
                setObjectName(dataProvider.getAccountName(getObjectId()));
                setProjectName(dataProvider.getProjectName(getProjectId()));
                setSubProjectName(dataProvider.getSubProjectName(getSubProjectId()));
                setMapName(dataProvider.getMapName(getMapId()));
                setIssueName(dataProvider.getIssueName(getIssueId()));
            }
            httpServletRequest.setAttribute("currentAccountId", String.valueOf(getAccountId()));
            resultType = SUCCESS;
        }
        return resultType;
    }

    /**
     * The doAttachmentAdd method is used to store all the values submitted in the form to database.
     * Here all the values are being first initalized to a method insertAttachment from the
     * ProjectService Class.
     * And from their the values are stored to the database.
     *
     * @throws Exception
     *         If the required Attachment value cannot be added to the database.
     *
     * @return The return type of this method is a String
     *         which depends on the action being completely
     *         performed or not.
     *
     */
    public String doAttachmentAdd() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null || httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("DO_ATTACHMENT_ADD", userRoleId)) {
                try {
                    if (!("dbGrid".equalsIgnoreCase(getSubmitFrom()))) {


                        boolean isInserted = false;
                        String generatedPath = null;

                        httpServletRequest.setAttribute("currentObjectId", String.valueOf(getObjectId()));
                        httpServletRequest.setAttribute("currentObjectType", String.valueOf(getObjectType()));

                        projectService = ServiceLocator.getProjectService();
                        String basePath = Properties.getProperty(ApplicationConstants.ATTACHMENTS_PATH) + "//MirageV2//" + getObjectType();
                         String theFilePath = FileUploadUtility.getInstance().filePathGeneration(basePath);
                        String fileName = FileUploadUtility.getInstance().fileNameGeneration(getUploadFileName());
                        File targetDirectory = new File(theFilePath + "//" + fileName);
                         setPath(targetDirectory.toString());
                        if ("Customer Manager".equalsIgnoreCase(userRoleName)) {
                            setUploadedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID).toString());
                        } else {
                            setUploadedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                        }

                        setDateOfUpload(DateUtility.getInstance().getCurrentMySqlDateTime());

                        //to copy the uploaded file to filesystem
                        FileUtils.copyFile(getUpload(), targetDirectory);

                        //to insert the attachment information to the tblPrjAttachments table
                        isInserted = projectService.insertAttachment(this);

                        if (isInserted) {
                            resultType = SUCCESS;
                            resultMessage = "<font color=\"green\" size=\"1.5\">Attachment has been successfully uploaded!</font>";
                              if ("Project".equalsIgnoreCase(getObjectType())) {
                                System.out.println("getAccountId..." + getAccountId());
                                 dataProvider = HibernateDataProvider.getInstance();
                                setObjectName(dataProvider.getAccountName(getAccountId()));
                                setAccountId(accountId);
                            }

                            //resultMessage = "Attachment "+getUploadFileName()+" has been successfully uploaded!";
                        } else {
                            resultType = INPUT;
                            resultMessage = "<font color=\"red\" size=\"1.5\">Sorry! Please Try again!</font>";
                        }

                        httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, resultMessage);
                        httpServletRequest.setAttribute("resultMessage", resultMessage);

                        prepare();
                    }//close checking submit form
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

    /**
     * The doProjectEdit method is called to update the values related to that particular project.
     *
     * @throws Exception
     *         If a required Project value cannot be updated to the database.
     *
     * @return The return type of this method is a String
     *         which depends on whether the values are successfully
     *         updated or not.
     *
     */
    public String doProjectEdit() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null || httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID) != null) {

            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            String flagVar="";
            if (AuthorizationManager.getInstance().isAuthorizedUser("DO_PROJECT_EDIT", userRoleId)) {
                try {
                    if (!("dbGrid".equalsIgnoreCase(getSubmitFrom()))) {
                      /*  if (userRoleId == 4) {
                            setStatus("Active");
                        } */
                        setModifiedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                        setModifiedDate(DateUtility.getInstance().getCurrentMySqlDateTime());
  if ("Completed".equals(getStatus()) && (getId() > 0)) {
                    int anyActiveResourcesInProjects = DataSourceDataProvider.getInstance().getProjectActiveResources(getId());
                    if (anyActiveResourcesInProjects > 0) {
                         flagVar = "add";
                           resultMessage = "This project still having active resources,Please inactive the resources before completing the project";
                       
                    }
                }

                        projectService = ServiceLocator.getProjectService();
                        if ("".equals(flagVar)) {  
                        insertedRows = projectService.addOrUpdateProject(this, "update");

                        if (insertedRows > 0) {
                            resultType = SUCCESS;
                            resultMessage = "Project has been successfully updated!";
                        } else {
                            resultType = INPUT;
                            resultMessage = "Sorry! Please Try again!";
                        }
                        }
                        setCurrentProject(projectService.getProjectVTO(this));

                        httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, resultMessage);

                        setAccountId(getCurrentProject().getCustomerId());

                        // start -- here we are setting attribute value for  project type
                        operationProject = getProjectType();
                        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.OP_PROJECT_TYPE) != null) {
                            httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.OP_PROJECT_TYPE);
                        }
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.OP_PROJECT_TYPE, operationProject);


                        httpServletRequest.setAttribute("currentAccountId", String.valueOf(getAccountId()));
                        httpServletRequest.setAttribute("currentProjectId", String.valueOf(getCurrentProject().getId()));
                    }//checking submit form
                    prepareProjectAdd();
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
     * The doSubProjectEdit method is called to update the values related to that particular SubProject.
     * Here all the values are being first initalized to a method addOrUpdateSubProject from the
     * ProjectService Class.
     *
     * @throws Exception
     *         If a required SubProject value cannot be updated to the database.
     *
     * @return The return type of this method is a String
     *         which depends on whether the values are successfully
     *         updated or not.
     *
     */
    public String doSubProjectEdit() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null || httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID) != null) {

            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("DO_SUB_PROJECT_EDIT", userRoleId)) {
                try {
                    if (!("dbGrid".equalsIgnoreCase(getSubmitFrom()))) {

                        projectService = ServiceLocator.getProjectService();

                        insertedRows = projectService.addOrUpdateSubProject(this, "update");

                        if (insertedRows == 1) {
                            resultType = SUCCESS;
                            resultMessage = "<font color=\"green\" size=\"1.5\">Sub Project has been successfully updated!</font>";
                        } else {
                            resultType = INPUT;
                            resultMessage = "<font color=\"red\" size=\"1.5\">Sorry! Please Try again!</font>";
                        }

                        httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, resultMessage);

                        setCurrentSubProject(projectService.getSubProjectVTO(this));

                        setId(getProjectId());
                    }//checking submit form
                    prepareSubProjectAdd();
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
     * The doMapEdit method is called to update the values related to that particular Map.
     * Here all the values are being first initalized to a method updateMap from the
     * ProjectService Class.
     *
     * @throws Exception
     *         If a required Map value cannot be updated to the database.
     *
     * @return The return type of this method is a String
     *         which depends on whether the values are successfully
     *         updated or not.
     *
     */
    public String doMapEdit() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null || httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID) != null) {

            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("DO_MAP_EDIT", userRoleId)) {
                try {
                    if (!("dbGrid".equalsIgnoreCase(getSubmitFrom()))) {

                        int updatedRows = 0;

                        projectService = ServiceLocator.getProjectService();
                        updatedRows = projectService.updateMap(this);

                        if (updatedRows == 1) {
                            resultType = "SUCCESS";
                            resultMessage = "<font color=\"green\" size=\"1.5\">Map has been successfully updated!</font>";
                        } else {
                            resultType = "INPUT";
                            resultMessage = "<font color=\"red\" size=\"1.5\">Sorry! Please Try again!</font>";
                        }

                        httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, resultMessage);
                        httpServletRequest.setAttribute("resultMessage", resultMessage);

                        setCurrentMapProject(projectService.getMapVTO(this));

                        setId(getProjectId());
                    }//checking submit form
                    prepareMapAdd();
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
     * The doIssueEdit method is called to update the values related to that particular Map.
     * Here all the values are being first initalized to a method updateIssue from the
     * ProjectService Class.
     *
     * @throws Exception
     *         If a required Issue value cannot be updated to the database.
     *
     * @return The return type of this method is a String
     *         which depends on whether the values are successfully
     *         updated or not.
     *
     */
    public String doIssueEdit() throws Exception {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null || httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID) != null) {

            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("DO_ISSUE_EDIT", userRoleId)) {
                try {
                    if (!("dbGrid".equalsIgnoreCase(getSubmitFrom()))) {
                        int updatedRows = 0;


                        projectService = ServiceLocator.getProjectService();
                        updatedRows = projectService.updateIssue(this);

                        if (updatedRows == 1) {
                            resultType = "SUCCESS";
                            resultMessage = "<font color=\"green\" size=\"1.5\">Map has been successfully updated!</font>";
                        } else {
                            resultType = "INPUT";
                            resultMessage = "<font color=\"red\" size=\"1.5\">Sorry! Please Try again!</font>";
                        }

                        httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, resultMessage);
                        httpServletRequest.setAttribute("resultMessage", resultMessage);

                        setCurrentIssue(projectService.getIssueVTO(this));
                        //setCurrentMapProject(projectService.getMapVTO(this));

                        setAccountId(getId());
                        setId(getProjectId());
                    }//checking submit form
                    prepareIssueAdd();
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

    public String projectList() {
        String empId;
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            //empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
            empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            try {
                dataSourceDataProvider = DataSourceDataProvider.getInstance();
                setProjectsList(dataSourceDataProvider.getProjectsList(empId));
                resultType = SUCCESS;
            } catch (Exception ex) {
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;

            }
        }
        return resultType;
    }
    /*For getProjectHierarch list
     * Date : 03/14/2014
     * Author : Santosh Kola
     */

    public String getProjectHeirarchy() throws ServiceLocatorException {
        String empId;
        String projectId = null;

        String queryString = null;
        int isTeamLead = 0;
        int isManager = 0;
        String isDes = "";
        //String projectEmpId = "";
        dataSourceDataProvider = DataSourceDataProvider.getInstance();
        dataProvider = HibernateDataProvider.getInstance();
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            //empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
            empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());

            try {
                setCurrentAction("saveProjectHeirarchy");
                projectService = ServiceLocator.getProjectService();
                setEmpTypeListReports(new ArrayList<String>());
                //System.out.println("getAccountId------->"+getAccountId()); 
                // System.out.println("getProjectId------->"+getProjectId()); 
                setAccountName(dataProvider.getAccountName(getAccountId()));
                setProjectName(dataProvider.getProjectName(getProjectId()));
                setProjectEmployeeMap(projectService.getProjectResourceMap(getProjectId()));
                setProjectReportsToMap(projectService.getProjectReportsToMap(getProjectId()));

                //System.out.println("emp MAp-->"+getProjectEmployeeMap());
                //System.out.println("reports MAp-->"+getProjectReportsToMap());

                httpServletRequest.setAttribute("currentProjectId", String.valueOf(getProjectId()));
                resultType = SUCCESS;
            } catch (Exception ex) {
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;

            }

        }

        return resultType;
    }

    public String saveProjectHeirarchy() throws ServiceLocatorException {
        String empId = "";
        int reportsToId = 0;
        String reportsToIdType = "";
        String[] tempReportsToId = null;
        //String projectEmpId="";
        int updated = 0;
        dataSourceDataProvider = DataSourceDataProvider.getInstance();
        dataProvider = HibernateDataProvider.getInstance();
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            //empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
            empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());

            try {
                setCurrentAction("saveProjectHeirarchy");
                // System.out.println("get1---------->"+getProjectReportsTo()+"---------->"+getProjectId());
                //reportsToId = getProjectReportsTo();
                //  reportsToId=tempReportsToId[0];
                // reportsToIdType = tempReportsToId[1];
                //System.out.println("get1---------->"+getProjectEmployees());
                //     System.out.println("resourceId-->"+getProjectReportsTo());
                for (int i = 0; i < getProjectEmployees().size(); i++) {
                    int resourceId = Integer.parseInt(getProjectEmployees().get(i).toString());
                    // System.out.println("resourceId-->"+resourceId);
                    //   String[] temp1 =temp.split("-");
                    // projectEmpId=temp1[0];
                    // String item = temp1[1];
                    updated = ServiceLocator.getProjectService().updateProjectResourceReportsTo(resourceId, getProjectReportsTo(), getProjectId());
                }
                // System.out.println("getProjectId-->"+getProjectId());
                getProjectHeirarchy();


                setProjectId(getProjectId());
                setAccountId(getAccountId());
                if (updated > 0) {
                    // System.out.println("Updated---->"+updated);
                    resultMessage = "Hierarchy Has been successfully Saved!";
                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, resultMessage);
                    resultType = SUCCESS;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;

            }

        }

        return resultType;
    }

    public String editProjectReportsTo() throws ServiceLocatorException {
        // System.out.println("projectempId-->"+getProjectEmpId());
        //  System.out.println("projectempType-->"+getEmpType());


        String empId;
        String projectId = null;

        int isTeamLead = 0;
        int isManager = 0;
        String isDes = "";
        // String projectEmpId = "";
        dataSourceDataProvider = DataSourceDataProvider.getInstance();
        dataProvider = HibernateDataProvider.getInstance();
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            String tempEmpDetails = null;
            try {
                setCurrentAction("updateProjectReportsTo");
                // System.out.println("list------------------>");
                setAccountName(dataProvider.getAccountName(getAccountId()));
                setProjectName(dataProvider.getProjectName(getProjectId()));

                Map projectsEmployeesMap = new TreeMap();
                projectsEmployeesMap.put(getProjectEmpId(), getProjectEmpName());
                setProjectReportsToMap(ServiceLocator.getProjectService().getProjectReportsToMap(getProjectId()));
                setProjectEmployeeMap(projectsEmployeesMap);
                List currentProjectEmployee = new ArrayList();
                currentProjectEmployee.add(getProjectEmpId());
                setProjectEmployees(currentProjectEmployee);
                //System.out.println("-->"+getProjectReportsTo()+"-"+getProjectReportsToType());
                setProjectReportsTo(getProjectReportsTo());
                setProjectEmpName(getProjectEmpName());
                // System.out.println("getProject-->"+getProjectReportsTo());
                resultType = SUCCESS;
            } catch (Exception ex) {
                ex.printStackTrace();
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;

            }
        }

        return resultType;

    }

    public String updateProjectReportsTo() throws ServiceLocatorException {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                setId(getProjectId());
                setAccountId(getAccountId());

                // System.out.println("in updateProjectReportsTo");
                // System.out.println("getProjectEmpId-->"+Integer.parseInt(getProjectEmployees().get(0).toString()));
                int projectEmpId = Integer.parseInt(getProjectEmployees().get(0).toString());
                //  System.out.println("getProjectReportsTo-->"+getProjectReportsTo());
                //  System.out.println("getProjectId-->"+getProjectId());
                int updated = ServiceLocator.getProjectService().updateProjectResourceReportsTo(projectEmpId, getProjectReportsTo(), getProjectId());

                if (updated > 0) {
                    //  System.out.println("Updated---->"+updated);
                    resultMessage = "Hierarchy Has been successfully Updated!";
                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, resultMessage);
                    resultType = SUCCESS;
                }
                // setProjectEmpId(getProjectEmpId());
                setProjectId(getProjectId());
                setAccountId(getAccountId());
                // setEmpType(getEmpType());
                setProjectEmpName(getProjectEmpName());
                setProjectEmpId(projectEmpId);
                resultType = editProjectReportsTo();
                //resultType = SUCCESS;
                // setId(getProjectId());
                //setAccountId(getAccountId());

            } catch (Exception ex) {
                ex.printStackTrace();
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;

            }
        }
        return resultType;
    }

    public String addProjectTeam() throws Exception {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null || httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID) != null) {

            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("PREPARE_SUB_PRJ_ADD", userRoleId)) {
                dataSourceDataProvider = DataSourceDataProvider.getInstance();
                dataProvider = HibernateDataProvider.getInstance();
                hibernateDataProvider = HibernateDataProvider.getInstance();
                setRolesMap(dataSourceDataProvider.getRolesForProjectTeam());
                setClientCurrencyMap(hibernateDataProvider.getGreenSheetClientBillingRate(ApplicationConstants.GEENSHEET_CURRENCY_OPTIONS));
                setEmpProjectRolls(DataSourceDataProvider.getInstance().getProjectRollsOfEmpoyee());	
                //   setTeamLeadMap(dataSourceDataProvider.getEmployeeNamesUserIdByTitle("Team Lead"));
                //  setPrjectTypeList(dataProvider.getProjectType(ApplicationConstants.PROJECT_TYPE_OPTIONS));
                //  setProjectStatusTypeList(dataProvider.getProjectStatusType(ApplicationConstants.PROJECT_STATUS_TYPE_OPTIONS));
                setCurrentAction("doAddProjectTeam");
                setAccountName(dataProvider.getAccountName(getAccountId()));
                setProjectName(dataProvider.getProjectName(getProjectId()));
                setId(getProjectId());
                //   setIsPrimary(true);
                //setIsPMO(true);
                setDateAssigned(DateUtility.getInstance().getCurrentMySqlDate());

                //  setDateAssigned(DateUtility.getInstance().getCurrentMySqlDate());
             //   setDateClosed(DateUtility.getInstance().getCurrentMySqlEndDate());
                // setDateClosed(DateUtility.getInstance().getCurrentMySqlDate());
                //setUtilisation("100");
                setProjectTeamReportsTo(dataSourceDataProvider.getProjectReportsTo(getAccountId(), getProjectId()));
                httpServletRequest.setAttribute("currentAccountId", String.valueOf(getAccountId()));
                httpServletRequest.setAttribute("currentProjectId", String.valueOf(getProjectId()));
                setIsDualReportingRequired(dataSourceDataProvider.isDualReportsRequired(getProjectId()));

                if (getIsDualReportingRequired()) {
                    setTemp(1);
                } else {
                    setTemp(0);
                }
                resultType = SUCCESS;
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }

    public String doAddProjectTeam() {
        resultType = LOGIN;
        // System.out.println("workingCountry----->");
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
             resultType = "accessFailed";
            String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            if (AuthorizationManager.getInstance().isAuthorizedUser("PREPARE_SUB_PRJ_ADD", userRoleId)) {
                try {
                    setBackFlag(getBackFlag());
                    String flagVar = "";
                    java.sql.Date hireDate=null;
                      if (getResType().equals("E")) {  
                    hireDate=dataSourceDataProvider.getInstance().getHireDateOfEmployeeBeforeAddingCurrentStatus(Integer.parseInt(getPreAssignEmpId()))  ;
                    Date assignedStartDate = DateUtility.getInstance().getMysqlDate(getDateAssigned());
                   
                     if (hireDate == null || ("1950-01-31".equals(DateUtility.getInstance().convertDateToStringPayroll(hireDate)))) {
                       setResultMessage("<font size='2' color='red'> Employee HireDate is not updated. Contact HR Team to update  !</font>");
                            resultType = "add";
                            flagVar = "add";  
                     }
                 
                    if(hireDate!=null && "".equals(flagVar)){
                    if(assignedStartDate.compareTo(hireDate)<0){
                   setResultMessage("<font size='2' color='red'>  Start Date should be greater than or equal to Employee HireDate'" + com.mss.mirage.util.DateUtility.getInstance().convertToviewFormat(hireDate.toString()) + "' !</font>");
                                    resultType = "add";
                            flagVar = "add";     
                    }
                    }
                    
                    
                    List list = new ArrayList();
                    list = dataSourceDataProvider.getInstance().getProjectStartDate(getProjectId());
                    
                    if(list.get(0) != null && !"".equalsIgnoreCase(list.get(0).toString()))
                    {
                    setProjectStartDate(list.get(0).toString());
                    }
                    if(list.get(1) != null && !"".equalsIgnoreCase(list.get(0).toString()))
                    {
                    setProjectEndDate(list.get(1).toString());
                    }
                    
                    String response = DataSourceDataProvider.getInstance().getAvailableStartDates(getPreAssignEmpId(),assignedStartDate);

                    if (getProjectStartDate() != null && !"".equalsIgnoreCase(projectStartDate.toString()) && "".equals(flagVar)) {
                        if (assignedStartDate.compareTo(DateUtility.getInstance().getMysqlDate(DateUtility.getInstance().convertToviewFormat(getProjectStartDate()))) < 0) {
                            setResultMessage("<font size='2' color='red'> This project starts from '" + com.mss.mirage.util.DateUtility.getInstance().convertToviewFormat(getProjectStartDate().toString()) + "'. So,Start Date should be greater than or equal to '" + com.mss.mirage.util.DateUtility.getInstance().convertToviewFormat(getProjectStartDate().toString()) + "' !</font>");
                            resultType = "add";
                            flagVar = "add";
                        }

                        if (getProjectEndDate() != null && !"".equalsIgnoreCase(projectEndDate.toString()) && "".equals(flagVar)) {

                            if (assignedStartDate.compareTo(DateUtility.getInstance().getMysqlDate(DateUtility.getInstance().convertToviewFormat(getProjectEndDate()))) >=0) {
                                setResultMessage("<font size='2' color='red'> This project ends with '" + com.mss.mirage.util.DateUtility.getInstance().convertToviewFormat(getProjectEndDate().toString()) + "'. So,Start Date should be less than'" + com.mss.mirage.util.DateUtility.getInstance().convertToviewFormat(getProjectEndDate().toString()) + "' !</font>");
                                resultType = "add";
                                flagVar = "add";
                            }
                        }
                    }

                    if (getDateClosed() != null && !"".equalsIgnoreCase(getDateClosed()) && "".equals(flagVar)) {

                        Date dateClosed = DateUtility.getInstance().getMysqlDate(getDateClosed());

                        if (getProjectEndDate() != null && !"".equalsIgnoreCase(projectEndDate.toString())) {
                            if (dateClosed.compareTo(DateUtility.getInstance().getMysqlDate(DateUtility.getInstance().convertToviewFormat(getProjectEndDate()))) > 0) {
                                setResultMessage("<font size='2' color='red'> This project ends with '" + com.mss.mirage.util.DateUtility.getInstance().convertToviewFormat(getProjectEndDate().toString()) + "'. So,End Date should be less than or equal to '" + com.mss.mirage.util.DateUtility.getInstance().convertToviewFormat(getProjectEndDate().toString()) + "' !</font>");
                                resultType = "add";
                                flagVar = "add";
                            }

                        }
                    }

                    if(!"InActive".equals(getStatus())){
                    if (!"".equals(response) && "".equals(flagVar)) {
                        System.out.println("response...."+response);
                        setResultMessage(response);
                            resultType = "add";
                            flagVar = "add";
                    }
                    }
                      }
                    
                    if (flagVar.equalsIgnoreCase("")) {
                        hibernateDataProvider = HibernateDataProvider.getInstance();
                        setClientCurrencyMap(hibernateDataProvider.getGreenSheetClientBillingRate(ApplicationConstants.GEENSHEET_CURRENCY_OPTIONS));
                        // String email = null;
                        if (getResType().equals("E")) {
                            setEmail(DataSourceDataProvider.getInstance().getEmailIdForEmployee(Integer.parseInt(getPreAssignEmpId())));
                        } else if (getResType().equals("V") || getResType().equals("C")) {
                            String email = dataSourceDataProvider.getInstance().getContactOfficeMail(Integer.parseInt(getPreAssignEmpId()));
                            setEmail(email.substring(0, email.length() - 2));
                            //setEmail(dataSourceDataProvider.getInstance().getContactOfficeMail(Integer.parseInt(getPreAssignEmpId())));
                        } else {
                            setEmail(dataSourceDataProvider.getInstance().getConsultantEmail(getPreAssignEmpId()));
                        }
                        setProjectTeamReportsTo(dataSourceDataProvider.getInstance().getProjectReportsTo(getAccountId(), getProjectId()));
                        setTester("add");
                        // System.out.println("before add employee");
                        String flag = ServiceLocator.getProjectService().doAddEmployeeProject(this, loginId);
                        //String temp = dataSourceDataProvider.getInstance().updateNoOfResources(getProjectId());
                        if (flag .equalsIgnoreCase("Success")) {
                             int flag1 = ServiceLocator.getProjectService().doInsertProjectContactHistory(this, loginId, "I");
                             setResultMessage("<font size='2' color='green'>Project team member added successfully !</font>");
                             resultType = "update";
                        }else if(flag.equalsIgnoreCase("NotAllowed")){
                           setResultMessage(" <font size='2' color='red'>Start date should be greater than prev end date!</font>");  
                            resultType = "add";
                        } else {

                            setResultMessage("<font size='2' color='red'>Sorry! Please Try again!</font>");
                             resultType = "update";
                        }

                       
                    }

                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, getResultMessage());
                    //prepare();

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


    public String getProjectTeamDetails() {
        resultType = LOGIN;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            //     System.out.println("workingCountry----->"+workingCountry);
            resultType = "accessFailed";
            String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            if (AuthorizationManager.getInstance().isAuthorizedUser("PREPARE_SUB_PRJ_ADD", userRoleId)) {
                try {
                    dataSourceDataProvider = DataSourceDataProvider.getInstance();
                    int projUtilization = dataSourceDataProvider.getAvailableUtilization(getEmpId());
                    setAvailableUtl(projUtilization);
                    setRolesMap(dataSourceDataProvider.getRolesForProjectTeam());
                    setEmpProjectRolls(DataSourceDataProvider.getInstance().getProjectRollsOfEmpoyee());
                    // String email = null;
                    hibernateDataProvider = HibernateDataProvider.getInstance();
                    setClientCurrencyMap(hibernateDataProvider.getGreenSheetClientBillingRate(ApplicationConstants.GEENSHEET_CURRENCY_OPTIONS));
                    setCurrentAction("doUpdateProjectTeam");
                    //  System.out.println("email"+email);
                    //  System.out.println();
                    ServiceLocator.getProjectService().getProjectTeamDetails(getId(), this);
                    // searchPrepare();
                    dataProvider = HibernateDataProvider.getInstance();
                    setAccountName(dataProvider.getAccountName(getAccountId()));
                    setProjectName(dataProvider.getProjectName(getProjectId()));
                    setProjectTeamReportsTo(dataSourceDataProvider.getInstance().getProjectReportsTo(getAccountId(), getProjectId()));
                    //prepare();
                    setIsDualReportingRequired(dataSourceDataProvider.isDualReportsRequired(getProjectId()));

                    if (getIsDualReportingRequired()) {
                        setTemp(1);
                    } else {
                        setTemp(0);
                    }
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

    public String doUpdateProjectTeam() {
         resultType = LOGIN;
       
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            resultType = "accessFailed";
            String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            if (AuthorizationManager.getInstance().isAuthorizedUser("PREPARE_SUB_PRJ_ADD", userRoleId)) {
                try {
                    String flagVar = "";
                    java.sql.Date hireDate=null;
                    setBackFlag(getBackFlag());
                    if (getResType().equals("E")) {  
                    Date assignedStartDate = DateUtility.getInstance().getMysqlDate(getDateAssigned());
                   String response = DataSourceDataProvider.getInstance().getAvailableStartDates(getPreAssignEmpId(),assignedStartDate);
                      hireDate=dataSourceDataProvider.getInstance().getHireDateOfEmployeeBeforeAddingCurrentStatus(Integer.parseInt(getPreAssignEmpId()))  ; 
                      
                    
                    if (hireDate == null || ("1950-01-31".equals(DateUtility.getInstance().convertDateToStringPayroll(hireDate)))) {
                   
                        setResultMessage("<font size='2' color='red'> Employee HireDate is not updated. Please Contact HR Team to update  !</font>");
                            resultType = "add";
                            flagVar = "add";  
                     }
                    
                       if(hireDate!=null && "".equals(flagVar)){
                    if(assignedStartDate.compareTo(hireDate)<0){
                            setResultMessage("<font size='2' color='red'>  Start Date should be greater than or equal to Employee HireDate'" + com.mss.mirage.util.DateUtility.getInstance().convertToviewFormat(hireDate.toString()) + "' !</font>");
                            resultType = "add";
                            flagVar = "add";     
                    }
                       }
                      
                    List list = new ArrayList();
                    list = DataSourceDataProvider.getInstance().getProjectStartDate(getProjectId());
                     
                    if(list.get(0) != null && !"".equalsIgnoreCase(list.get(0).toString()))
                    {
                    setProjectStartDate(list.get(0).toString());
                    }
                    if(list.get(1)!= null && !"".equalsIgnoreCase(list.get(0).toString()))
                    {
                    setProjectEndDate(list.get(1).toString());
                    }
                    
                    
                    
                    if (projectStartDate != null && !"".equalsIgnoreCase(projectStartDate) && "".equals(flagVar)) {
                        if (assignedStartDate.compareTo(DateUtility.getInstance().getMysqlDate(DateUtility.getInstance().convertToviewFormat(projectStartDate))) < 0) {
                            setResultMessage("<font size='2' color='red'> Start date should be greater than  project start date'" + com.mss.mirage.util.DateUtility.getInstance().convertToviewFormat(projectStartDate.toString()) + "' !</font>");
                            resultType = "add";
                            flagVar = "add";
                        }

                        if (getProjectEndDate() != null && !"".equalsIgnoreCase(projectEndDate) && "".equals(flagVar)) {
                            if (assignedStartDate.compareTo(DateUtility.getInstance().getMysqlDate(DateUtility.getInstance().convertToviewFormat(getProjectEndDate()))) >=0) {
                                setResultMessage("<font size='2' color='red'> This project ends with '" + com.mss.mirage.util.DateUtility.getInstance().convertToviewFormat(getProjectEndDate().toString()) + "'. So,Start Date should be less than  '" + com.mss.mirage.util.DateUtility.getInstance().convertToviewFormat(getProjectEndDate().toString()) + "' !</font>");
                                resultType = "add";
                                flagVar = "add";
                            }

                        }

                    }

                    if (getDateClosed() != null && !"".equalsIgnoreCase(getDateClosed()) && "".equals(flagVar)) {

                        Date dateClosed = DateUtility.getInstance().getMysqlDate(getDateClosed());
                        if (getProjectEndDate() != null && !"".equalsIgnoreCase(projectEndDate.toString())) {
                            if (dateClosed.compareTo(DateUtility.getInstance().getMysqlDate(DateUtility.getInstance().convertToviewFormat(getProjectEndDate()))) > 0) {
                                setResultMessage("<font size='2' color='red'> This project ends with '" + com.mss.mirage.util.DateUtility.getInstance().convertToviewFormat(getProjectEndDate().toString()) + "'. So,End Date should be less than or equal to '" + com.mss.mirage.util.DateUtility.getInstance().convertToviewFormat(getProjectEndDate().toString()) + "' !</font>");
                                resultType = "add";
                                flagVar = "add";
                            }

                        }
                    }

                    
                  if("Active".equals(getStatus()) && "Active".equals(getExistedStatus()) && "".equals(flagVar)){
                    if (!"".equals(response) && "".equals(flagVar)) {
                        setResultMessage(response);
                            resultType = "add";
                            flagVar = "add";
                       
                    }
                    }          
                    
                    }
                    if (flagVar.equalsIgnoreCase("")) {
                        hibernateDataProvider = HibernateDataProvider.getInstance();
                        setClientCurrencyMap(hibernateDataProvider.getGreenSheetClientBillingRate(ApplicationConstants.GEENSHEET_CURRENCY_OPTIONS));
                        // String email = null;
                        //System.out.println("e");
                        if (getResType().equals("E")) {
                            //   System.out.println("e"+getPreAssignEmpId());
                            setEmail(DataSourceDataProvider.getInstance().getEmailIdForEmployee(Integer.parseInt(getPreAssignEmpId())));
                        } else if (getResType().equals("V") || getResType().equals("C")) {
                            //  System.out.println("v"+getPreAssignEmpId());
                            String email = dataSourceDataProvider.getInstance().getContactOfficeMail(Integer.parseInt(getPreAssignEmpId()));
                            setEmail(email.substring(0, email.length() - 2));
                            // setEmail(dataSourceDataProvider.getInstance().getContactOfficeMail(Integer.parseInt(getPreAssignEmpId())));
                        } else {
                            //  System.out.println("con"+getPreAssignEmpId());
                            setEmail(dataSourceDataProvider.getInstance().getConsultantEmail(getPreAssignEmpId()));
                        }
                        setProjectTeamReportsTo(dataSourceDataProvider.getInstance().getProjectReportsTo(getAccountId(), getProjectId()));
                        setTester("update");

                        // System.out.println("email"+getEmail());  
                        // int flag = ServiceLocator.getProjectService().doUpdateProjectTeam(this, loginId);
                        String flag = ServiceLocator.getProjectService().doAddEmployeeProject(this, loginId);

                        //  System.out.println("email"+email);  
                        // searchPrepare();
                        if (flag.equalsIgnoreCase("Success")) {
                              int flag1 = ServiceLocator.getProjectService().doInsertProjectContactHistory(this, loginId, "U");
                      
                            //   setDeleteAction(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_DELETE_ACTION).toString());
                            //  resultType = SUCCESS;
                            setResultMessage(" <font size='2' color='green'>Project has been successfully Updated!</font>");
                        }else if(flag.equalsIgnoreCase("NotAllowed")){
                           setResultMessage(" <font size='2' color='red'>If the start date is same as before then only you can update employee state from  inactive to active  otherwise you need to add new record!</font>");  
                        }
                        else {

                            setResultMessage(" <font size='2' color='red'>Sorry! Please Try again!</font>");
                        }
                        httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, getResultMessage());
                    }

                    //prepare();
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


    //new method for project details dashboard
    public String getProjectDashBoard() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            String objectId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
            // System.out.println("objectId-->"+objectId);
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("GET_PROJECT_DASH_BOARD", userRoleId)) {
                try {
                    setMyProjects(new HashMap());
                    int isManager = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_USER_MANAGER).toString());
    if(isManager==1){
                      // setMyTeamMembers((Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
                      String loginIdList = DataSourceDataProvider.getInstance().getTeamLoginIdList((Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
                      if(loginIdList!=null && !"".equals(loginIdList)){
                       loginIdList="'"+loginId+"'"+","+loginIdList;
                      }else{
                           loginIdList="'"+loginId+"'";
                      }
                      System.out.println("loginIdList..."+loginIdList);
                      //loginIdList = loginIdList.replaceAll("\"", " ");
                        setMyAccounts(DataSourceDataProvider.getInstance().getManagerProjectDashBoardAccountList(loginIdList));
                     }else{
                     setMyAccounts(DataSourceDataProvider.getInstance().getProjectDashBoardAccountList(Integer.parseInt(objectId)));
                     }
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
//new for pmo team

    public String doAddPMOTeam() {
        int isCreate = 0;

        try {
            resultType = LOGIN;

            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

                String createdBy = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                //    System.out.println("getPreAssignEmpId-->"+getPreAssignEmpId());
                //     System.out.println("getaccount-->"+getAccountId());
                //     System.out.println("getproject-->"+getProjectId());
                dataSourceDataProvider = DataSourceDataProvider.getInstance();
                setAccountId(getAccountId());
                // setProjectId(getProjectId());
                setId(getProjectId());
                httpServletRequest.setAttribute("currentAccountId", String.valueOf(getAccountId()));
                httpServletRequest.setAttribute("currentProjectId", String.valueOf(getProjectId()));

                setAccountName(dataSourceDataProvider.getAccountName(getAccountId()));
                //  System.out.println("accname-->"+getAccountName());
                isCreate = ServiceLocator.getProjectService().doAddPMOTeam(createdBy, getPreAssignEmpId(), getStatus(), getAccountId(), getProjectId());
                //  getProject();
                if (isCreate > 0) {
                    resultMessage1 = "<font color=\"green\" size=\"1.8\">PMO Author added successfully!</font>";

                    resultType = SUCCESS;
                } else {
                    resultType = INPUT;
                    resultMessage1 = "<font color=\"red\" size=\"1.8\">PMO Name already exists!!Please try again with different name</font>";
                }

                //httpServletRequest.getSession(false).setAttribute("resultMessage", resultMessage);
            }//Closing Session checking
        } catch (Exception ex) {
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
            ex.printStackTrace();
            resultType = ERROR;
        }

        return resultType;
    }

    public String deletePMOTeamMember() {
        int isCreate = 0;

        try {
            resultType = LOGIN;

            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

                String modifiedBy = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                //   System.out.println("getPreAssignEmpId-->"+getPreAssignEmpId());
                //   System.out.println("getaccount-->"+getAccountId());
                //   System.out.println("getproject-->"+getProjectId());
                dataSourceDataProvider = DataSourceDataProvider.getInstance();
                setAccountId(getAccountId());
                // setProjectId(getProjectId());
                setId(getProjectId());
                httpServletRequest.setAttribute("currentAccountId", String.valueOf(getAccountId()));
                httpServletRequest.setAttribute("currentProjectId", String.valueOf(getProjectId()));

                setProjectName(dataSourceDataProvider.getProjectName(getProjectId()));
                // System.out.println("accname-->"+getProjectName());
                isCreate = ServiceLocator.getProjectService().doDeletePMOTeamMember(modifiedBy, getPreAssignEmpId(), getAccountId(), getProjectId());
                //  getProject();
                if (isCreate > 0) {
                    resultMessage1 = "<font color=\"green\" size=\"1.8\">PMO Author deleted successfully!</font>";

                    resultType = SUCCESS;
                } else {
                    resultType = INPUT;
                    resultMessage1 = "<font color=\"red\" size=\"1.5\">Please try again!!</font>";
                }

                //httpServletRequest.getSession(false).setAttribute("resultMessage", resultMessage);
            }//Closing Session checking
        } catch (Exception ex) {
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
            ex.printStackTrace();
            resultType = ERROR;
        }

        return resultType;
    }

    public String getProjectRisks() {
        resultType = LOGIN;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            //     System.out.println("workingCountry----->"+workingCountry);
            resultType = "accessFailed";
            String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            if (AuthorizationManager.getInstance().isAuthorizedUser("PREPARE_PRJ_RISK", userRoleId)) {
                try {
                    dataSourceDataProvider = DataSourceDataProvider.getInstance();
                    // String email = null;
                    setCurrentAction("doAddProjectRisk");
                    setRiskAssignedTo(dataSourceDataProvider.getProjectRiskAssignedTo(getAccountId(), getProjectId()));
                    dataProvider = HibernateDataProvider.getInstance();
                    setAccountName(dataProvider.getAccountName(getAccountId()));
                    setProjectName(dataProvider.getProjectName(getProjectId()));
                    if (getId() > 0) {
                        ServiceLocator.getProjectService().getProjectRiskDetails(this);
                         setCurrentAction("doEditProjectRisk");
                    }

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

    public String doAddProjectRisk() {
        resultType = LOGIN;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            //     System.out.println("workingCountry----->"+workingCountry);
            resultType = "accessFailed";
            String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            if (AuthorizationManager.getInstance().isAuthorizedUser("PREPARE_PRJ_RISK", userRoleId)) {
                try {
                    dataSourceDataProvider = DataSourceDataProvider.getInstance();
                    dataProvider = HibernateDataProvider.getInstance();
                    setBackFlag(getBackFlag());
                    setAccountName(dataProvider.getAccountName(getAccountId()));
                    setProjectName(dataProvider.getProjectName(getProjectId()));
                    setCreatedBy(loginId);
                    int count = ServiceLocator.getProjectService().doAddProjectRisk(this);
                    if (count > 0) {
                        resultMessage = "<font color=\"green\" size=\"1.8\">Details added successfully!</font>";

                        resultType = SUCCESS;
                    } else {
                        resultType = INPUT;
                        resultMessage = "<font color=\"red\" size=\"1.5\">Please try again!!</font>";
                    }
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
    public String doEditProjectRisk() {
        resultType = LOGIN;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            //     System.out.println("workingCountry----->"+workingCountry);
            resultType = "accessFailed";
            String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            if (AuthorizationManager.getInstance().isAuthorizedUser("PREPARE_PRJ_RISK", userRoleId)) {
                try {
                    dataSourceDataProvider = DataSourceDataProvider.getInstance();
                    dataProvider = HibernateDataProvider.getInstance();
                    setBackFlag(getBackFlag());
                    setAccountName(dataProvider.getAccountName(getAccountId()));
                    setProjectName(dataProvider.getProjectName(getProjectId()));
                    setCreatedBy(loginId);
                    int count = ServiceLocator.getProjectService().doEditProjectRisk(this);
                    if (count > 0) {
                        resultMessage = "<font color=\"green\" size=\"1.8\">Details updated successfully!</font>";

                        resultType = SUCCESS;
                    } else {
                        resultType = INPUT;
                        resultMessage = "<font color=\"red\" size=\"1.5\">Please try again!!</font>";
                    }
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
    /*
     * Current status managemet start
     */
    public String getAddProject() throws Exception {
        try {

            resultType = LOGIN;
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null || httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID) != null) {
                //String[] rightParams =(String[])parameters.get("addedEmployeeList");
                setPmoNamesMap(DataSourceDataProvider.getInstance().getPmoMap());
                setAccountId(accountId);
                setAccountName(accountName);
                setPracticeList(DataSourceDataProvider.getInstance().getPracticeByDepartment("GDC"));
                setPreSalesMap(DataUtility.getInstance().getMapSortByValue(DataSourceDataProvider.getInstance().getPresalesListByLoginId()));
                setPmomanagerMap(DataUtility.getInstance().getMapSortByValue(DataSourceDataProvider.getInstance().getPmoMap()));
                setOffshoreDelLeadMap(DataSourceDataProvider.getInstance().getDeliveryLeadsByCountry("India"));
                setOffshoreLeadMap(DataSourceDataProvider.getInstance().getLeadNamesByCountry("India"));
                setOnsiteLeadMap(DataSourceDataProvider.getInstance().getLeadNamesByCountry("USA"));
                resultType = SUCCESS;
            }
        } catch (Exception ex) {
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
            ex.printStackTrace();
            resultType = ERROR;
        }
        return resultType;
    }

    public String doAddProjectToCustomer() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null || httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            String flagVar="";
            //  if (AuthorizationManager.getInstance().isAuthorizedUser("DO_PROJECT_ADD", userRoleId)) {
            try {
                // if (!("dbGrid".equalsIgnoreCase(getSubmitFrom()))) {
                if (userRoleId == 4) {
                    setStatus("Active");
                }
                setModifiedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                setModifiedDate(DateUtility.getInstance().getCurrentMySqlDateTime());
                setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                setCreatedDate(DateUtility.getInstance().getCurrentMySqlDateTime());

                 if ("Completed".equals(getStatus()) && (getProjectId() > 0)) {
                    int anyActiveResourcesInProjects = DataSourceDataProvider.getInstance().getProjectActiveResources(getProjectId());
                    if (anyActiveResourcesInProjects > 0) {
                        flagVar = "add";
                        setProjectId(projectId);
                        setAccountId(getCustomerId());
                        prepareProjectAdd();
                          resultMessage = "<font color=\"red\" size=\"2\">This project still having active resources,Please inactive the resources before completing the project</font>";
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.RESULT_MSG, resultMessage);
                      
                        resultType = "update";
                    }
                }

                if ("".equals(flagVar)) {
                
                projectService = ServiceLocator.getProjectService();
                if (getProjectId() == 0) {
                    insertedRows = projectService.doAddProjectToCustomer(this, "add");
                } else {
                    insertedRows = projectService.doAddProjectToCustomer(this, "update");
                }
                if (insertedRows == 1) {
                    //  resultType = "add";
                    resultMessage = "<font color=\"green\" size=\"1.5\">Project has been successfully inserted!</font>";
                } else {
                    resultType = INPUT;
                    resultMessage = "<font color=\"red\" size=\"1.5\">Sorry! Please Try again!</font>";
                }
                setProjectId(projectId);
                // start -- here we are setting attribute value for  project type
                operationProject = getProjectType();
                if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.OP_PROJECT_TYPE) != null) {
                    httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.OP_PROJECT_TYPE);
                }
                httpServletRequest.getSession(false).setAttribute(ApplicationConstants.OP_PROJECT_TYPE, operationProject);


                httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, resultMessage);

                setAccountId(getCustomerId());
                // }//Clsoe checking Submit From

                prepareProjectAdd();
                if (getProjectId() == 0) {
                    resultType = "add";
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.RESULT_MSG, "Project Added Successfully");
                } else {
                   
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.RESULT_MSG, "Project Updated Successfully");
                    resultType = "update";
                }
            }
            } catch (Exception ex) {
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }
            // }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }

    public String getCustomerProjectDetails() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null || httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            try {
                if (userRoleId == 4) {
                    setStatus("Active");
                }
               // System.out.println("in getCustomerProjectDetails action...." + getProjectId());
                setPmoNamesMap(DataSourceDataProvider.getInstance().getPmoMap());
                setPracticeList(DataSourceDataProvider.getInstance().getPracticeByDepartment("GDC"));
                setPreSalesMap(DataUtility.getInstance().getMapSortByValue(DataSourceDataProvider.getInstance().getPresalesListByLoginId()));
                setPmomanagerMap(DataUtility.getInstance().getMapSortByValue(DataSourceDataProvider.getInstance().getPmoMap()));
                setOffshoreDelLeadMap(DataSourceDataProvider.getInstance().getDeliveryLeadsByCountry("India"));
                setOffshoreLeadMap(DataSourceDataProvider.getInstance().getLeadNamesByCountry("India"));
                setOnsiteLeadMap(DataSourceDataProvider.getInstance().getLeadNamesByCountry("USA"));
                setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                setCreatedDate(DateUtility.getInstance().getCurrentMySqlDateTime());
                setAccountName(accountName);
                setProjectIssueType(DataSourceDataProvider.getInstance().getIssueTypeList());
                setTaskAssignedTo(DataSourceDataProvider.getInstance().getProjectRiskAssignedTo(getAccountId(), getProjectId()));
               // System.out.println("accountName..." + accountName);
                projectService = ServiceLocator.getProjectService();
                projectService.getCustomerProjectDetails(this, getProjectId());
                resultType = SUCCESS;
            } catch (Exception ex) {
                ex.printStackTrace();
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }
            // }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }

    public String getInActiveCustomerProject() {
        int isCreate = 0;

        try {
            resultType = LOGIN;

            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
               // System.out.println("getInActiveCustomerProject projectId..." + getProjectId());
                setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                setCreatedDate(DateUtility.getInstance().getCurrentMySqlDateTime());
                setAccountId(accountId);
                setAccountName(accountName);
                setProjectId(projectId);
            //  isCreate = ServiceLocator.getProjectService().getInActiveCustomerProject(getProjectId(), getCreatedBy(), getCreatedDate(),getEndDateActual());
                 isCreate = ServiceLocator.getProjectService().getInActiveCustomerProject(getProjectId(), getCreatedBy(), getCreatedDate(),getEndDateActual(),getComments());
              
                if (isCreate > 0) {
                    resultMessage1 = "<font color=\"green\" size=\"1.8\">Project InActivated successfully!</font>";

                    resultType = SUCCESS;
                } else {
                    resultType = SUCCESS;
                    resultMessage1 = "<font color=\"red\" size=\"1.5\">Please try again!!</font>";
                }

                //httpServletRequest.getSession(false).setAttribute("resultMessage", resultMessage);
            }//Closing Session checking
        } catch (Exception ex) {
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
            ex.printStackTrace();
            resultType = ERROR;
        }

        return resultType;
    }
 /*
     * Current status managemet end
     */
    
    

    
    /**
     * The method setServletRequest is used to set the HttpServletRequest
     */
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    
//START:Setters and Getters for Project
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getPrjName() {
        return prjName;
    }

    public void setPrjName(String prjName) {
        this.prjName = prjName;
    }

    public String getPrjManagerUID() {
        return prjManagerUID;
    }

    public void setPrjManagerUID(String prjManagerUID) {
        this.prjManagerUID = prjManagerUID;
    }

    public String getSubPrjName() {
        return subPrjName;
    }

    public void setSubPrjName(String subPrjName) {
        this.subPrjName = subPrjName;
    }

    public String getTeamLeadUID() {
        return teamLeadUID;
    }

    public void setTeamLeadUID(String teamLeadUID) {
        this.teamLeadUID = teamLeadUID;
    }

    public String getProjectNames() {
        return projectNames;
    }

    public void setProjectNames(String projectNames) {
        this.projectNames = projectNames;
    }

    public String getSubProjectNames() {
        return subProjectNames;
    }

    public void setSubProjectNames(String subProjectNames) {
        this.subProjectNames = subProjectNames;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public String getBussinessDomain() {
        return bussinessDomain;
    }

    public void setBussinessDomain(String bussinessDomain) {
        this.bussinessDomain = bussinessDomain;
    }

    public String getMapTools() {
        return mapTools;
    }

    public void setMapTools(String mapTools) {
        this.mapTools = mapTools;
    }

    public String getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    public String getTechLead() {
        return techLead;
    }

    public void setTechLead(String techLead) {
        this.techLead = techLead;
    }

    public String getMapper() {
        return mapper;
    }

    public void setMapper(String mapper) {
        this.mapper = mapper;
    }

    public String getTester() {
        return tester;
    }

    public void setTester(String tester) {
        this.tester = tester;
    }

    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }

    public String getIssueNames() {
        return issueNames;
    }

    public void setIssueNames(String issueNames) {
        this.issueNames = issueNames;
    }

    public String getIssueStates() {
        return issueStates;
    }

    public void setIssueStates(String issueStates) {
        this.issueStates = issueStates;
    }

    public String getIssueTypes() {
        return issueTypes;
    }

    public void setIssueTypes(String issueTypes) {
        this.issueTypes = issueTypes;
    }

    public String getAssignedToUIDs() {
        return assignedToUIDs;
    }

    public void setAssignedToUIDs(String assignedToUIDs) {
        this.assignedToUIDs = assignedToUIDs;
    }

    public Date getDatesCreated() {
        return datesCreated;
    }

    public void setDatesCreated(Date datesCreated) {
        this.datesCreated = datesCreated;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getSubProjectId() {
        return subProjectId;
    }

    public void setSubProjectId(int subProjectId) {
        this.subProjectId = subProjectId;
    }

    public int getMapId() {
        return mapId;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getTotalResources() {
        return totalResources;
    }

    public void setTotalResources(int totalResources) {
        this.totalResources = totalResources;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getSubProjectName() {
        return subProjectName;
    }

    public void setSubProjectName(String subProjectName) {
        this.subProjectName = subProjectName;
    }

    public String getIssueName() {
        return issueName;
    }

    public void setIssueName(String issueName) {
        this.issueName = issueName;
    }

    public Map getProjectManagersMap() {
        return projectManagersMap;
    }

    public void setProjectManagersMap(Map projectManagersMap) {
        this.projectManagersMap = projectManagersMap;
    }

    public Map getTeamLeadMap() {
        return teamLeadMap;
    }

    public void setTeamLeadMap(Map teamLeadMap) {
        this.teamLeadMap = teamLeadMap;
    }

    public List getPrjectTypeList() {
        return prjectTypeList;
    }

    public void setPrjectTypeList(List prjectTypeList) {
        this.prjectTypeList = prjectTypeList;
    }

    public List getProjectss() {
        return projectss;
    }

    public void setProjectss(List projectss) {
        this.projectss = projectss;
    }

    public List getSubProjectsMaps() {
        return subProjectsMaps;
    }

    public void setSubProjectsMaps(List subProjectsMaps) {
        this.subProjectsMaps = subProjectsMaps;
    }

    public List getToolsListMaps() {
        return toolsListMaps;
    }

    public void setToolsListMaps(List toolsListMaps) {
        this.toolsListMaps = toolsListMaps;
    }

    public List getProjectStatusTypeList() {
        return projectStatusTypeList;
    }

    public void setProjectStatusTypeList(List projectStatusTypeList) {
        this.projectStatusTypeList = projectStatusTypeList;
    }

    public List getIssueTypeMaps() {
        return issueTypeMaps;
    }

    public void setIssueTypeMaps(List issueTypeMaps) {
        this.issueTypeMaps = issueTypeMaps;
    }

    public List getEmployeesMaps() {
        return employeesMaps;
    }

    public void setEmployeesMaps(List employeesMaps) {
        this.employeesMaps = employeesMaps;
    }

    public List getAttachmentsMaps() {
        return attachmentsMaps;
    }

    public void setAttachmentsMaps(List attachmentsMaps) {
        this.attachmentsMaps = attachmentsMaps;
    }

    public Map getTechLeadMaps() {
        return techLeadMaps;
    }

    public void setTechLeadMaps(Map techLeadMaps) {
        this.techLeadMaps = techLeadMaps;
    }

    public Map getMapperMaps() {
        return mapperMaps;
    }

    public void setMapperMaps(Map mapperMaps) {
        this.mapperMaps = mapperMaps;
    }

    public Map getTesterMaps() {
        return testerMaps;
    }

    public void setTesterMaps(Map testerMaps) {
        this.testerMaps = testerMaps;
    }

    public Map getProjectManagerLists() {
        return projectManagerLists;
    }

    public void setProjectManagerLists(Map projectManagerLists) {
        this.projectManagerLists = projectManagerLists;
    }

    public ProjectVTO getCurrentProject() {
        return currentProject;
    }

    public void setCurrentProject(ProjectVTO currentProject) {
        this.currentProject = currentProject;
    }

    public SubProjectVTO getCurrentSubProject() {
        return currentSubProject;
    }

    public void setCurrentSubProject(SubProjectVTO currentSubProject) {
        this.currentSubProject = currentSubProject;
    }

    public MapVTO getCurrentMapProject() {
        return currentMapProject;
    }

    public void setCurrentMapProject(MapVTO currentMapProject) {
        this.currentMapProject = currentMapProject;
    }

    public IssueVTO getCurrentIssue() {
        return currentIssue;
    }

    public void setCurrentIssue(IssueVTO currentIssue) {
        this.currentIssue = currentIssue;
    }

    public String getSubmitFrom() {
        return submitFrom;
    }

    public void setSubmitFrom(String submitFrom) {
        this.submitFrom = submitFrom;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    public Timestamp getDateOfUpload() {
        return dateOfUpload;
    }

    public void setDateOfUpload(Timestamp dateOfUpload) {
        this.dateOfUpload = dateOfUpload;
    }

    public String getStartDateOne() {
        return startDateOne;
    }

    public void setStartDateOne(String startDateOne) {
        this.startDateOne = startDateOne;
    }

    public String getEndDateOne() {
        return endDateOne;
    }

    public void setEndDateOne(String endDateOne) {
        this.endDateOne = endDateOne;
    }

    public String getDatesCreatedOne() {
        return datesCreatedOne;
    }

    public void setDatesCreatedOne(String datesCreatedOne) {
        this.datesCreatedOne = datesCreatedOne;
    }

    public void setParameters(Map map) {
        this.parameters = map;
    }

    public List getAddedEmployeeList() {
        return addedEmployeeList;
    }

    public void setAddedEmployeeList(List addedEmployeeList) {
        this.addedEmployeeList = addedEmployeeList;
    }

    public Map getAllDevelopmentMap() {
        return allDevelopmentMap;
    }

    public void setAllDevelopmentMap(Map allDevelopmentMap) {
        this.allDevelopmentMap = allDevelopmentMap;
    }

    public Map getAddedDevelopmentMap() {
        return addedDevelopmentMap;
    }

    public void setAddedDevelopmentMap(Map addedDevelopmentMap) {
        this.addedDevelopmentMap = addedDevelopmentMap;
    }

    public List getMapStatusTypeList() {
        return mapStatusTypeList;
    }

    public void setMapStatusTypeList(List mapStatusTypeList) {
        this.mapStatusTypeList = mapStatusTypeList;
    }

    public List getProjAttachTypeList() {
        return projAttachTypeList;
    }

    public void setProjAttachTypeList(List projAttachTypeList) {
        this.projAttachTypeList = projAttachTypeList;
    }

    public String getAttachType() {
        return attachType;
    }

    public void setAttachType(String attachType) {
        this.attachType = attachType;
    }

    public Map getAddTeamDevelopmentMap() {
        return addTeamDevelopmentMap;
    }

    public void setAddTeamDevelopmentMap(Map addTeamDevelopmentMap) {
        this.addTeamDevelopmentMap = addTeamDevelopmentMap;
    }

    public String getStrMapId() {
        return strMapId;
    }

    public void setStrMapId(String strMapId) {
        this.strMapId = strMapId;
        this.setMapId(Integer.parseInt(strMapId));
    }

    public String getStrSubProjectId() {
        return strSubProjectId;

    }

    public void setStrSubProjectId(String strSubProjectId) {
        this.strSubProjectId = strSubProjectId;
        this.setSubProjectId(Integer.parseInt(strSubProjectId));
    }

    public String getStrId() {
        return strId;
    }

    public void setStrId(String strId) {
        this.strId = strId;
        this.setId(Integer.parseInt(strId));
    }

    public String getStrAccountId() {
        return strAccountId;
    }

    public void setStrAccountId(String strAccountId) {
        this.strAccountId = strAccountId;
        this.setAccountId(Integer.parseInt(strAccountId));
    }

    public String getProjects() {
        return projects;
    }

    public void setProjects(String projects) {
        this.projects = projects;
    }

    public List getProjectsList() {
        return projectsList;
    }

    public void setProjectsList(List projectsList) {
        this.projectsList = projectsList;
    }

    /**
     * @return the projectEmpId
     */
    public int getProjectEmpId() {
        return projectEmpId;
    }

    /**
     * @param projectEmpId the projectEmpId to set
     */
    public void setProjectEmpId(int projectEmpId) {
        this.projectEmpId = projectEmpId;
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
     * @return the empTypeListReports
     */
    public List getEmpTypeListReports() {
        return empTypeListReports;
    }

    /**
     * @param empTypeListReports the empTypeListReports to set
     */
    public void setEmpTypeListReports(List empTypeListReports) {
        this.empTypeListReports = empTypeListReports;
    }

    /**
     * @return the projectReportsToMap
     */
    public Map getProjectReportsToMap() {
        return projectReportsToMap;
    }

    /**
     * @param projectReportsToMap the projectReportsToMap to set
     */
    public void setProjectReportsToMap(Map projectReportsToMap) {
        this.projectReportsToMap = projectReportsToMap;
    }

    /**
     * @return the projectEmployeeMap
     */
    public Map getProjectEmployeeMap() {
        return projectEmployeeMap;
    }

    /**
     * @param projectEmployeeMap the projectEmployeeMap to set
     */
    public void setProjectEmployeeMap(Map projectEmployeeMap) {
        this.projectEmployeeMap = projectEmployeeMap;
    }

    /**
     * @return the projectReportsTo
     */
    public int getProjectReportsTo() {
        return projectReportsTo;
    }

    /**
     * @param projectReportsTo the projectReportsTo to set
     */
    public void setProjectReportsTo(int projectReportsTo) {
        this.projectReportsTo = projectReportsTo;
    }

    /**
     * @return the projectEmployees
     */
    public List getProjectEmployees() {
        return projectEmployees;
    }

    /**
     * @param projectEmployees the projectEmployees to set
     */
    public void setProjectEmployees(List projectEmployees) {
        this.projectEmployees = projectEmployees;
    }

    /**
     * @return the projectEmpName
     */
    public String getProjectEmpName() {
        return projectEmpName;
    }

    /**
     * @param projectEmpName the projectEmpName to set
     */
    public void setProjectEmpName(String projectEmpName) {
        this.projectEmpName = projectEmpName;
    }

    /**
     * @return the projectReportsToName
     */
    public String getProjectReportsToName() {
        return projectReportsToName;
    }

    /**
     * @param projectReportsToName the projectReportsToName to set
     */
    public void setProjectReportsToName(String projectReportsToName) {
        this.projectReportsToName = projectReportsToName;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    /**
     * @return the preAssignEmpId
     */
    public String getPreAssignEmpId() {
        return preAssignEmpId;
    }

    /**
     * @return the resType
     */
    public String getResType() {
        return resType;
    }

    /**
     * @param preAssignEmpId the preAssignEmpId to set
     */
    public void setPreAssignEmpId(String preAssignEmpId) {
        this.preAssignEmpId = preAssignEmpId;
    }

    /**
     * @param resType the resType to set
     */
    public void setResType(String resType) {
        this.resType = resType;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the assignedToUID
     */
    public String getAssignedToUID() {
        return assignedToUID;
    }

    /**
     * @return the isBillable
     */
    public boolean getIsBillable() {
        return isBillable;
    }

    public String getDateAssigned() {
        return dateAssigned;
    }

    public void setDateAssigned(String dateAssigned) {
        this.dateAssigned = dateAssigned;
    }

    /**
     * @return the dateAssigned
     */
    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @return the isPrimary
     */
    public boolean getIsPrimary() {
        return isPrimary;
    }

    /**
     * @return the resTitle
     */
    public String getResTitle() {
        return resTitle;
    }

    /**
     * @param assignedToUID the assignedToUID to set
     */
    public void setAssignedToUID(String assignedToUID) {
        this.assignedToUID = assignedToUID;
    }

    /**
     * @param isBillable the isBillable to set
     */
    public void setIsBillable(boolean isBillable) {
        this.isBillable = isBillable;
    }

    public String getDateClosed() {
        return dateClosed;
    }

    public void setDateClosed(String dateClosed) {
        this.dateClosed = dateClosed;
    }

    /**
     * @param dateAssigned the dateAssigned to set
     */
    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @param isPrimary the isPrimary to set
     */
    public void setIsPrimary(boolean isPrimary) {
        this.isPrimary = isPrimary;
    }

    /**
     * @param resTitle the resTitle to set
     */
    public void setResTitle(String resTitle) {
        this.resTitle = resTitle;
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
     * @return the clientCurrencyMap
     */
    public Map getClientCurrencyMap() {
        return clientCurrencyMap;
    }

    /**
     * @param clientCurrencyMap the clientCurrencyMap to set
     */
    public void setClientCurrencyMap(Map clientCurrencyMap) {
        this.clientCurrencyMap = clientCurrencyMap;
    }

    /**
     * @return the utilisation
     */
    public String getUtilisation() {
        return utilisation;
    }

    /**
     * @return the rateType
     */
    public String getRateType() {
        return rateType;
    }

    /**
     * @return the currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * @param utilisation the utilisation to set
     */
    public void setUtilisation(String utilisation) {
        this.utilisation = utilisation;
    }

    /**
     * @param rateType the rateType to set
     */
    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    /**
     * @param currency the currency to set
     */
    public void setCurrency(String currency) {
        this.currency = currency;
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
     * @return the workPhone
     */
    public String getWorkPhone() {
        return workPhone;
    }

    /**
     * @return the mobilePhone
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * @param workPhone the workPhone to set
     */
    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    /**
     * @param mobilePhone the mobilePhone to set
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public Map getRolesMap() {
        return rolesMap;
    }

    public void setRolesMap(Map rolesMap) {
        this.rolesMap = rolesMap;
    }

    public Map getMyAccounts() {
        return myAccounts;
    }

    public void setMyAccounts(Map myAccounts) {
        this.myAccounts = myAccounts;
    }

    public boolean getIsPMO() {
        return isPMO;
    }

    public void setIsPMO(boolean isPMO) {
        this.isPMO = isPMO;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getResultMessage1() {
        return resultMessage1;
    }

    public void setResultMessage1(String resultMessage1) {
        this.resultMessage1 = resultMessage1;
    }

    /**
     * @return the secProjectReportsTo
     */
    public int getSecProjectReportsTo() {
        return secProjectReportsTo;
    }

    /**
     * @param secProjectReportsTo the secProjectReportsTo to set
     */
    public void setSecProjectReportsTo(int secProjectReportsTo) {
        this.secProjectReportsTo = secProjectReportsTo;
    }

    /**
     * @return the isDualReportingRequired
     */
    public boolean getIsDualReportingRequired() {
        return isDualReportingRequired;
    }

    /**
     * @param isDualReportingRequired the isDualReportingRequired to set
     */
    public void setIsDualReportingRequired(boolean isDualReportingRequired) {
        this.isDualReportingRequired = isDualReportingRequired;
    }

    /**
     * @return the temp
     */
    public int getTemp() {
        return temp;
    }

    /**
     * @param temp the temp to set
     */
    public void setTemp(int temp) {
        this.temp = temp;
    }

    /**
     * @return the preSalesMap
     */
    public Map getPreSalesMap() {
        return preSalesMap;
    }

    /**
     * @param preSalesMap the preSalesMap to set
     */
    public void setPreSalesMap(Map preSalesMap) {
        this.preSalesMap = preSalesMap;
    }

    /**
     * @return the preSalesMgrId
     */
    public String getPreSalesMgrId() {
        return preSalesMgrId;
    }

    /**
     * @param preSalesMgrId the preSalesMgrId to set
     */
    public void setPreSalesMgrId(String preSalesMgrId) {
        this.preSalesMgrId = preSalesMgrId;
    }

    /**
     * @return the pmo
     */
    public String getPmo() {
        return pmo;
    }

    /**
     * @param pmo the pmo to set
     */
    public void setPmo(String pmo) {
        this.pmo = pmo;
    }

    /**
     * @return the pmomanagerMap
     */
    public Map getPmomanagerMap() {
        return pmomanagerMap;
    }

    /**
     * @param pmomanagerMap the pmomanagerMap to set
     */
    public void setPmomanagerMap(Map pmomanagerMap) {
        this.pmomanagerMap = pmomanagerMap;
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
     * @return the onSitePlan
     */
    public int getOnSitePlan() {
        return onSitePlan;
    }

    /**
     * @param onSitePlan the onSitePlan to set
     */
    public void setOnSitePlan(int onSitePlan) {
        this.onSitePlan = onSitePlan;
    }

    /**
     * @return the onSiteActual
     */
    public int getOnSiteActual() {
        return onSiteActual;
    }

    /**
     * @param onSiteActual the onSiteActual to set
     */
    public void setOnSiteActual(int onSiteActual) {
        this.onSiteActual = onSiteActual;
    }

    /**
     * @return the customer
     */
    public String getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(String customer) {
        this.customer = customer;
    }

    /**
     * @return the offShorePlan
     */
    public int getOffShorePlan() {
        return offShorePlan;
    }

    /**
     * @param offShorePlan the offShorePlan to set
     */
    public void setOffShorePlan(int offShorePlan) {
        this.offShorePlan = offShorePlan;
    }

    /**
     * @return the offShoreActual
     */
    public int getOffShoreActual() {
        return offShoreActual;
    }

    /**
     * @param offShoreActual the offShoreActual to set
     */
    public void setOffShoreActual(int offShoreActual) {
        this.offShoreActual = offShoreActual;
    }

    /**
     * @return the costModel
     */
    public String getCostModel() {
        return costModel;
    }

    /**
     * @param costModel the costModel to set
     */
    public void setCostModel(String costModel) {
        this.costModel = costModel;
    }

    /**
     * @return the nearShorePlan
     */
    public int getNearShorePlan() {
        return nearShorePlan;
    }

    /**
     * @param nearShorePlan the nearShorePlan to set
     */
    public void setNearShorePlan(int nearShorePlan) {
        this.nearShorePlan = nearShorePlan;
    }

    /**
     * @return the nearShoreActual
     */
    public int getNearShoreActual() {
        return nearShoreActual;
    }

    /**
     * @param nearShoreActual the nearShoreActual to set
     */
    public void setNearShoreActual(int nearShoreActual) {
        this.nearShoreActual = nearShoreActual;
    }

    /**
     * @return the sector
     */
    public String getSector() {
        return sector;
    }

    /**
     * @param sector the sector to set
     */
    public void setSector(String sector) {
        this.sector = sector;
    }

    /**
     * @return the startDatePlan
     */
    public String getStartDatePlan() {
        return startDatePlan;
    }

    /**
     * @param startDatePlan the startDatePlan to set
     */
    public void setStartDatePlan(String startDatePlan) {
        this.startDatePlan = startDatePlan;
    }

    /**
     * @return the startDateActual
     */
    public String getStartDateActual() {
        return startDateActual;
    }

    /**
     * @param startDateActual the startDateActual to set
     */
    public void setStartDateActual(String startDateActual) {
        this.startDateActual = startDateActual;
    }

    /**
     * @return the complexity
     */
    public String getComplexity() {
        return complexity;
    }

    /**
     * @param complexity the complexity to set
     */
    public void setComplexity(String complexity) {
        this.complexity = complexity;
    }

    /**
     * @return the priority
     */
    public String getPriority() {
        return priority;
    }

    /**
     * @param priority the priority to set
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * @return the endDatePlan
     */
    public String getEndDatePlan() {
        return endDatePlan;
    }

    /**
     * @param endDatePlan the endDatePlan to set
     */
    public void setEndDatePlan(String endDatePlan) {
        this.endDatePlan = endDatePlan;
    }

    /**
     * @return the endDateActual
     */
    public String getEndDateActual() {
        return endDateActual;
    }

    /**
     * @param endDateActual the endDateActual to set
     */
    public void setEndDateActual(String endDateActual) {
        this.endDateActual = endDateActual;
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
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the software
     */
    public String getSoftware() {
        return software;
    }

    /**
     * @param software the software to set
     */
    public void setSoftware(String software) {
        this.software = software;
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
     * @return the riskAssignedTo
     */
    public Map getRiskAssignedTo() {
        return riskAssignedTo;
    }

    /**
     * @param riskAssignedTo the riskAssignedTo to set
     */
    public void setRiskAssignedTo(Map riskAssignedTo) {
        this.riskAssignedTo = riskAssignedTo;
    }

    /**
     * @return the assignedTo
     */
    public String getAssignedTo() {
        return assignedTo;
    }

    /**
     * @param assignedTo the assignedTo to set
     */
    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    /**
     * @return the impact
     */
    public String getImpact() {
        return impact;
    }

    /**
     * @param impact the impact to set
     */
    public void setImpact(String impact) {
        this.impact = impact;
    }

    /**
     * @return the closedDate
     */
    public String getClosedDate() {
        return closedDate;
    }

    /**
     * @param closedDate the closedDate to set
     */
    public void setClosedDate(String closedDate) {
        this.closedDate = closedDate;
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
     * @return the taskAssignedTo
     */
    public Map getTaskAssignedTo() {
        return taskAssignedTo;
    }

    /**
     * @param taskAssignedTo the taskAssignedTo to set
     */
    public void setTaskAssignedTo(Map taskAssignedTo) {
        this.taskAssignedTo = taskAssignedTo;
    }

    /**
     * @return the durationTotheTask
     */
    public String getDurationTotheTask() {
        return durationTotheTask;
    }

    /**
     * @param durationTotheTask the durationTotheTask to set
     */
    public void setDurationTotheTask(String durationTotheTask) {
        this.durationTotheTask = durationTotheTask;
    }

    /**
     * @return the taskStartDate
     */
    public String getTaskStartDate() {
        return taskStartDate;
    }

    /**
     * @param taskStartDate the taskStartDate to set
     */
    public void setTaskStartDate(String taskStartDate) {
        this.taskStartDate = taskStartDate;
    }

    /**
     * @return the projectIssueType
     */
    public Map getProjectIssueType() {
        return projectIssueType;
    }

    /**
     * @param projectIssueType the projectIssueType to set
     */
    public void setProjectIssueType(Map projectIssueType) {
        this.projectIssueType = projectIssueType;
    }

    /**
     * @return the offshoreDelLeadMap
     */
    public Map getOffshoreDelLeadMap() {
        return offshoreDelLeadMap;
    }

    /**
     * @param offshoreDelLeadMap the offshoreDelLeadMap to set
     */
    public void setOffshoreDelLeadMap(Map offshoreDelLeadMap) {
        this.offshoreDelLeadMap = offshoreDelLeadMap;
    }

    /**
     * @return the offshoreLeadMap
     */
    public Map getOffshoreLeadMap() {
        return offshoreLeadMap;
    }

    /**
     * @param offshoreLeadMap the offshoreLeadMap to set
     */
    public void setOffshoreLeadMap(Map offshoreLeadMap) {
        this.offshoreLeadMap = offshoreLeadMap;
    }

    /**
     * @return the onsiteLeadMap
     */
    public Map getOnsiteLeadMap() {
        return onsiteLeadMap;
    }

    /**
     * @param onsiteLeadMap the onsiteLeadMap to set
     */
    public void setOnsiteLeadMap(Map onsiteLeadMap) {
        this.onsiteLeadMap = onsiteLeadMap;
    }

    /**
     * @return the offshoreDelLead
     */
    public String getOffshoreDelLead() {
        return offshoreDelLead;
    }

    /**
     * @param offshoreDelLead the offshoreDelLead to set
     */
    public void setOffshoreDelLead(String offshoreDelLead) {
        this.offshoreDelLead = offshoreDelLead;
    }

    /**
     * @return the offshoreTechLead
     */
    public String getOffshoreTechLead() {
        return offshoreTechLead;
    }

    /**
     * @param offshoreTechLead the offshoreTechLead to set
     */
    public void setOffshoreTechLead(String offshoreTechLead) {
        this.offshoreTechLead = offshoreTechLead;
    }

    /**
     * @return the onsiteLead
     */
    public String getOnsiteLead() {
        return onsiteLead;
    }

    /**
     * @param onsiteLead the onsiteLead to set
     */
    public void setOnsiteLead(String onsiteLead) {
        this.onsiteLead = onsiteLead;
    }

    /**
     * @return the risk
     */
    public String getRisk() {
        return risk;
    }

    /**
     * @param risk the risk to set
     */
    public void setRisk(String risk) {
        this.risk = risk;
    }

    /**
     * @return the resources
     */
    public String getResources() {
        return resources;
    }

    /**
     * @param resources the resources to set
     */
    public void setResources(String resources) {
        this.resources = resources;
    }

    /**
     * @return the schedule
     */
    public String getSchedule() {
        return schedule;
    }

    /**
     * @param schedule the schedule to set
     */
    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    /**
     * @return the pmoNamesMap
     */
    public Map getPmoNamesMap() {
        return pmoNamesMap;
    }

    /**
     * @param pmoNamesMap the pmoNamesMap to set
     */
    public void setPmoNamesMap(Map pmoNamesMap) {
        this.pmoNamesMap = pmoNamesMap;
    }

    /**
     * @return the availableUtl
     */
    public int getAvailableUtl() {
        return availableUtl;
    }

    /**
     * @param availableUtl the availableUtl to set
     */
    public void setAvailableUtl(int availableUtl) {
        this.availableUtl = availableUtl;
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
     * @return the backFlag
     */
    public String getBackFlag() {
        return backFlag;
    }

    /**
     * @param backFlag the backFlag to set
     */
    public void setBackFlag(String backFlag) {
        this.backFlag = backFlag;
    }

    /**
     * @return the projectEndDate
     */
    public String getProjectEndDate() {
        return projectEndDate;
    }

    /**
     * @param projectEndDate the projectEndDate to set
     */
    public void setProjectEndDate(String projectEndDate) {
        this.projectEndDate = projectEndDate;
    }

    /**
     * @return the skillSet
     */
    public String getSkillSet() {
        return skillSet;
    }

    /**
     * @param skillSet the skillSet to set
     */
    public void setSkillSet(String skillSet) {
        this.skillSet = skillSet;
    }

    /**
     * @return the billableStartDate
     */
    public String getBillableStartDate() {
        return billableStartDate;
    }

    /**
     * @param billableStartDate the billableStartDate to set
     */
    public void setBillableStartDate(String billableStartDate) {
        this.billableStartDate = billableStartDate;
    }

    /**
     * @return the billableEndDate
     */
    public String getBillableEndDate() {
        return billableEndDate;
    }

    /**
     * @param billableEndDate the billableEndDate to set
     */
    public void setBillableEndDate(String billableEndDate) {
        this.billableEndDate = billableEndDate;
    }

    /**
     * @return the existedStatus
     */
    public String getExistedStatus() {
        return existedStatus;
    }

    /**
     * @param existedStatus the existedStatus to set
     */
    public void setExistedStatus(String existedStatus) {
        this.existedStatus = existedStatus;
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

    /**
     * @return the empProjectRolls
     */
    public List getEmpProjectRolls() {
        return empProjectRolls;
    }

    /**
     * @param empProjectRolls the empProjectRolls to set
     */
    public void setEmpProjectRolls(List empProjectRolls) {
        this.empProjectRolls = empProjectRolls;
    }
}
