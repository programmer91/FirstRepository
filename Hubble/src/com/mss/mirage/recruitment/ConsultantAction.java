/*******************************************************************************
 *
 * Project : Mirage V2doSearch
 *
 * Package :com.mss.mirage.recruitment
 *
 * Date    :  September 27, 2007, 4:05 PM
 *
 * Author  : Kondaiah Adapa<kadapa@miraclesoft.com>
 *
 * File    : ConsultantAction.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
package com.mss.mirage.recruitment;

import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.AuthorizationManager;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.DefaultDataProvider;
import com.mss.mirage.util.HibernateDataProvider;
import com.mss.mirage.util.Properties;
import com.mss.mirage.util.ServiceLocator;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.FileUploadUtility;
import com.mss.mirage.util.MailManager;
import com.mss.mirage.util.ServiceLocatorException;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.config.entities.ResultTypeConfig;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
//import java.util.Date;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 * The <code>ConsultantAction</code>class is use to getting new Consultant details from
 * <i>ConsultantAdd.jsp</i> page.
 * <p>
 * Then it invokes setter methods in <code>ConsultantAction</code> class and invokes doAdd() method
 * in <code>ConsultantAction</code> for inserting Consultant details in mirage.tblRecConsultant,doSearch()method in  <code>ConsultantAction</code> for search a particular consultant,
 *doSearch()method in  <code>ConsultantAction</code> for search a particular consultant
 * @author Kondaiah Adapa <a href="mailto:kadapa@miraclesoft.com">kadapa@miraclesoft.com</a>
 *
 * @version 1.0 November 01, 2006
 *
 *
 */

/*
 *
 * @author kondaiah Adapa <kadapa@miraclesoft.com>
 *
 * This Class.... ENTER THE DESCRIPTION HERE
 */
public class ConsultantAction extends ActionSupport implements ServletRequestAware {

    /* Creates a new instance of ConsultantAction */
    public ConsultantAction() {
    }
    /* variables for add a consultant */
    private String startDate;
    private String endDate;
    /** The id  is Useful to get the consultant Id*/
    private int id;
    /** The loginId  is Useful to get the consultant loginId*/
    private String loginId;
    /** The password  is Useful to get the consultant password*/
    private String password;
    /** The password  is Useful to get the consultant password*/
    private String country;
    /** The password  is Useful to get the consultant password*/
    private String practiceId;
    /** The titleTypeId  is Useful to get the consultant titleTypeId */
    private String titleTypeId;
    /** The consultantType  is Useful to get the consultant type*/
    private String consultantType;
    /** The work Authorization Field is useful to get the work Authorization of Consultant**/
    private String workAuthorization;
    private List workAuthorizationList;
    /** The currentStatus  is Useful to get the consultant currentStatus*/
    private String currentStatus;
    /** The recContactId  is Useful to get the consultant recContactId*/
    private int recContactId;
    /** The deletedFlag  is Useful to get the deletedFlag*/
    private int deletedFlag;
    /** The aliasName  is Useful to get the consultant aliasName*/
    private String aliasName;
    /** The ssn  is Useful to get the consultant Social Security Number*/
    private String ssn;
    /** The gender  is Useful to get the gender of the consultant */
    private String gender;
    /** The maritalStatus  is Useful to get the consultant maritalStatus*/
    private String maritalStatus;
    /** The email1  is Useful to get the consultant office E-mail*/
    private String email1;
    /** The email2  is Useful to get the consultant personal E-mail*/
    private String email2;
    /** The email3  is Useful to get the consultant other E-mail*/
    private String email3;
    /** The workPhoneNo  is Useful to get the consultant workPhoneNo*/
    private String workPhoneNo;
    /** The homePhoneNo  is Useful to get the consultant homePhoneNo*/
    private String homePhoneNo;
    /** The alterPhoneNo  is Useful to get the consultant alterPhoneNo*/
    private String alterPhoneNo;
    /** The cellPhoneNo  is Useful to get the consultant cellPhoneNo*/
    private String cellPhoneNo;
    /** The hotelPhoneNo  is Useful to get the consultant hotelPhoneNo*/
    private String hotelPhoneNo;
    /** The indiaPhoneNo  is Useful to get the consultant indiaPhoneNo*/
    private String indiaPhoneNo;
    /** The faxPhoneNo  is Useful to get the consultant faxPhoneNo*/
    private String faxPhoneNo;
    /** The refferedBy  is Useful to get the by whom the consultant reffered*/
    private String refferedBy;
    /** The refferalBonus  is Useful to get the refferalBonus*/
    private String refferalBonus;
    /** The offShoreAddressId  is Useful to get the consultant offShore Address Id*/
    private int offShoreAddressId;
    /** The curProjectAddressId  is Useful to get the consultant current Project Address Id*/
    private int curProjectAddressId;
    /** The  homeAddressId  is Useful to get the consultant  home Address Id*/
    private int homeAddressId;
    /** The  otherAddressId  is Useful to get the consultant  other Address Id*/
    private int otherAddressId;
    /** The  officialDob  is Useful to get the consultant  official date of birth*/
    private Date officialDob;
    /** The  dob  is Useful to get the consultant date of birth*/
    private Date dob;
    /** The hireDate  is Useful to get the consultant hire date*/
    private Date hireDate;
    /** The anniversaryDate  is Useful to get the consultant anniversary date*/
    private Date anniversaryDate;
    /** The firstContactDate  is Useful to get the consultant first contact date*/
    private Date firstContactDate;
    /** The preferedAnswer  is Useful to get the preferedAnswer*/
    private String preferedAnswer;
    /** The comments  is Useful to get the comments about consultant*/
    private String comments;
    /** The ratePerHour  is Useful to get the ratePerHour*/
    private String ratePerHour;
    /** The currentEmployer  is Useful to get the currentEmployer*/
    private String currentEmployer;
    /** The rateNegotiated is Useful to get the rateNegotiated. */
    private String rateNegotiated;
    /** The lastContactDate is Useful to get the last Contact Date of the Consultant. */
    private Date lastContactDate;
    /** The lastContactBy is Useful to get the by whome the Consultant last contacted. */
    private int lastContactBy;
    /** The createdBy  is Useful to identify by whom it was created. */
    private String createdBy;
    /** The createdDate  is useful to  identify on which date it was created. */
    private Timestamp createdDate;
    /** The modifiedBy  is useful to  identify by whom modified this screen */
    private String modifiedBy;
    /** The modifiedDate  is useful to  identify ModifiedDate. */
    private Timestamp modifiedDate;
    /** The othAddressLine1 is Useful to get consultant other address line1. */
    private String othAddressLine1;
    /** The othAddressLine2 is Useful to get consultant other address line2. */
    private String othAddressLine2;
    /** The othMailStop is Useful to get consultant other address mail stop. */
    private String othMailStop;
    /** The othCity is Useful to get consultant other address city. */
    private String othCity;
    /** The othState is Useful to get consultant other address state. */
    private String othState;
    /** The othCountry is Useful to get consultant other address country. */
    private String OtherCountry;
    /** The othZip is Useful to get consultant other address zip code. */
    private String othZip;
    private java.sql.Date availableFrom;
    private java.sql.Date availableTo;
    private String description;
    private String dueDate;
    /** The dueDateTimeStamp is used for storing dueDateTimeStamp. */
    private Timestamp dueDateTimeStamp;
    /**
     * A List object with an genderList object read from a
     * full List of data.
     */
    private List genderList;
    /**
     * A List object with an maritalStatusList object  read from a
     * full List of data.
     */
    private List maritalStatusList;
    /**
     * A List object with an countryList object  read from a
     * full List of  avilable Countries data.
     */
    private List countryList;
    /**
     * A Map object with an prefferedQuestionsList object   read from a
     * full List of prefferedQuestions.
     */
    private Map prefferedQuestionsList;
    /**
     * A List object with an consultantTypesList object  read from a
     * full List of data.
     */
    private List consultantTypesList;
    /**
     * A List object with an titleTypeIdList object  read from a
     * full List of data.
     */
    private List titleTypeIdList;
    /** The contactByMap is used  for storing contactBy. */
    private Map contactByMap;
    /** The recContactIdMap is used  for storing recContactId. */
    private Map recContactIdMap;
    /**
     * A List object with an currStatusList object  read from a
     * full List of data.
     */
    private List currStatusList;
    /**
     * A List object with an statesList object  read from a
     * full List of data.
     */
    private List statesList;
    // Variables for searching
    /** The searchName is Useful to search a consultant by name. */
    private String searchName;
    /** The searchPhone is Useful to search a consultant by phone. */
    private String searchPhone;
    /**
     * This variable httpServletRequest store the HttpServletRequest object reference
     * <code>{
     * @link    #HttpServletRequest
     * }</code>.
     */
    private HttpServletRequest httpServletRequest;
    /**
     * The resultType  is Useful to get the ResultType of an Action and depend on this
     * resultType the navigation of screens happens.
     */
    private String resultType;
    /**
     * The resultMessage  capture the resut and it is stored  as the request attribute.
     */
    private String resultMessage;
    /**
     *  The dataProvider
     * object provides  the data to Screens
     */
    private DataSourceDataProvider datasourceDataProvider;
    /**
     *  The currentConsultant is bean
     * object  to persist the data.
     */
    private ConsultantVTO currentConsultant;
    /**
     *  The httpSession
     * object representing the  HttpSession
     */
    private HttpSession httpSession;
    /** The curStatus is Useful to get the current status of the consultant . */
    private String curStatus;
    /** The laName is Useful to get the last name of the consultant . */
    private String laName;
    /** The fiName is Useful to get the first name of the consultant . */
    private String fiName;
    /** The miName is Useful to get the middile name of the consultant . */
    private String miName;
    /** The preferedQuestion  is Useful to get the prefered question . */
    private String preferedQuestion;
    /** The offAddressLine1 is Useful to get consultant offshoreproject  address line1. */
    private String offAddressLine1;
    /** The offAddressLine2  is Useful to get the consultant offshoreproject address line2 . */
    private String offAddressLine2;
    /** The offMailStop  is Useful to get  the consultant offshoreproject address mail stop. */
    private String offMailStop;
    /** The offCity  is Useful to get the consultant offshoreproject address city . */
    private String offCity;
    /** The offState  is Useful to get the offshore address offshoreproject address state . */
    private String offState;
    /** The offZip  is Useful to get the offshoreproject  address zipcode  . */
    private String offZip;
    /** The curAddressLine1  is Useful to get the current project address line1. */
    private String curAddressLine1;
    /** The curAddressLine2  is Useful to get the current project address line2 . */
    private String curAddressLine2;
    /** The curMailStop  is Useful to get the current project address mail stop . */
    private String curMailStop;
    /** The curCity  is Useful to get the current project address City . */
    private String curCity;
    /** The curState  is Useful to get the current project address state . */
    private String curState;
    /** The curZip  is Useful to get the current project address zip code . */
    private String curZip;
    /** The AddressLine1  is Useful to get the Home addressline1. */
    private String AddressLine1;
    /** The AddressLine2  is Useful to get the Home addressline2. */
    private String AddressLine2;
    /** The MailStop  is Useful to get the Home address mailstop . */
    private String MailStop;
    /** The City  is Useful to get the Home address City. */
    private String City;
    /** The Zip  is Useful to get the Home address Zip code . */
    private String Zip;
    /** The State  is Useful to get the Home address State . */
    private String State;
    /** The HomeCountry  is Useful to get the Home address Country . */
    private String HomeCountry;
    /** The ProjectCountry  is Useful to get the current project address country . */
    private String ProjectCountry;
    /** The OffShoreCountry  is Useful to get the offshore project address country . */
    private String OffShoreCountry;
    /** The consultantId  is Useful to get the id of the consultant . */
    private int consultantId;
    /** The homAddressId  is Useful to get the home address id of the consultant . */
    private int homAddressId;
    /** The offAddressId  is Useful to get the offshore address id  of the consultant . */
    private int offAddressId;
    /** The curAddressId  is Useful to get the current project address id  of the consultant . */
    private int curAddressId;
    /** The othAddressId  is Useful to get the other address id  of the consultant . */
    private int othAddressId;
    /**
     * A List object with an practiceList object  read from a
     * full List of data.
     */
    private List practiceList;
    private List industryList;
    private String submitFrom;
    private int userRoleId;
    private String skills;
    private String technology;
    private String attachmentName;
    private File upload;
    private String uploadContentType;
    private String uploadFileName;
    private String requestType;
    private String objectId;
    private int objectIdinInt;
    private String filepath;
    private String fileLocation;
    private Timestamp date;
    private String industryId;
    private Map assignedMembers;
    private String empId;
    private String currentAction;
    //Variables For Consultant Acivities.
    private String activityType;
    private String assignedTo;
    private String status;
    private Date interviewDate;
    private Date reportingDate;
    private String comments1;
    private String availability;
    private String consultantName;
    private String source;
    private String location;
    // New field for multiple selection in searching
    private List locationList;
    private String YrsExp;
    private HibernateDataProvider hibernateDataProvider;
    private List activityStatusList;
    // NEW ADD
    private Map orgMap;
    private String org;
    private DefaultDataProvider defaultDataProvider;
    //new 
    private String reqList;
    // for search results back to list..
    private String isSearch;
    private String all;
    //Added by Ajay Tummapala for TechReviews
    private String referTo;
    private String operationType;
    private String rateing;
    private String techName;
    private Map expMap;
    private String exp;
    private String techEval;
    private String available;
    private String preferredState;
    //new
    private String activityFromDate;
    private String activityToDate;
    private String recList;
    //new for activity graphs
    private String startDateSummaryGraph;
    private String endDateSummaryGraph;

    public void setReqList(String reqList) {
        this.reqList = reqList;
    }

    public String getReqList() {
        return reqList;
    }
//New For Recruitment DashBord 
    private String dashBoardStartDate;
    private String dashBoardEndDate;
    private String startDateClose;
    private String endDateClose;
    private String startDateSub;
    private String endDateSub;
    private String startdateSummary;
    private String enddateSummary;
    private int siteConsultants;
    private int requirementId=0;
    private int recConsultantId;
    private String requirementAdminFlag="";
    private int preSales1;
    private int preSales2;
    private String jobTitle;
    private Map preSalesMap;
    private String title;
    private String scheduledDate;
    private String scheduledTime;
    private String timeFormat;
    private String zone;
    private String interveiwType;
    private String skillSet;
    private int techSkills;
    private int domainskills;
    private int commskills;
    private int attachmentId;
    private String skill1;
    private String skill2;
    private String skill3;
    private String skill4;
    private String skill5;
    private String skill6;
    private String skill7;
    private String skill8;
    private String skill9;
    private String skill10;
    private int rating1 = 0;
    private int rating2 = 0;
    private int rating3 = 0;
    private int rating4 = 0;
    private int rating5 = 0;
    private int rating6 = 0;
    private int rating7 = 0;
    private int rating8 = 0;
    private int rating9 = 0;
    private int rating10 = 0;
    private Map skillSetMap;
    private int isReject;
    private List techLeadList;
    private Map clientMap;
    private Map teamLeadMap;
    private Map recruiterMap = new TreeMap();
    private String startDateReport;
    private String endDateReport;
    private String recruiterLoginId = "";
    private String consultId="";

    private String addedToDate;
      private String addedFromDate;
       private String skypeId;
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

    public String getEndDateClose() {
        return endDateClose;
    }

    public void setEndDateClose(String endDateClose) {
        this.endDateClose = endDateClose;
    }

    public String getStartDateClose() {
        return startDateClose;
    }

    public void setStartDateClose(String startDateClose) {
        this.startDateClose = startDateClose;
    }

    public String getEndDateSub() {
        return endDateSub;
    }

    public void setEndDateSub(String endDateSub) {
        this.endDateSub = endDateSub;
    }

    public String getStartDateSub() {
        return startDateSub;
    }

    public void setStartDateSub(String startDateSub) {
        this.startDateSub = startDateSub;
    }

    public String getEnddateSummary() {
        return enddateSummary;
    }

    public void setEnddateSummary(String enddateSummary) {
        this.enddateSummary = enddateSummary;
    }

    public String getStartdateSummary() {
        return startdateSummary;
    }

    public void setStartdateSummary(String startdateSummary) {
        this.startdateSummary = startdateSummary;
    }

    /**
     *  Populates user required options to the Screens depending on the  options.
     *   @see com.mss.mirage.ApplicationConstants
     *
     * @throws NullPointerException
     *  If a NullPointerException exists and its <code>{
     * @link    java.lang.NullPointerException
     * }</code>
     *
     * @return  String SUCCESS-The Result type is returned after complete the code of prepare
     * Method.
     *
     */
    public String prepare() {

        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";

            if (AuthorizationManager.getInstance().isAuthorizedUser("PREPARE_RECRUITMENT", userRoleId)) {
                try {

                    //Calling lookUpDate() method to populate select components
                    lookUpData();

                    //Calling setAddAction() method to populate the login details of the consultant
                    setAddAction();
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
     * This method is invoke when the user wants to populates login details of
     * consultant on jsp page
     *
     */
    public void setAddAction() {
        ConsultantVTO conVTO = new ConsultantVTO();
        // set the action name for doAdd method.
        conVTO.setActionName("addConsultant");
        setCurrentConsultant(conVTO);
    }

    /**
     * This method is invoke when the user wants to edit the consultant details
     * and updated consultant details will be stored into database.
     *
     *
     * @throws NullPointerException
     *         If a NullPointerException exists and its <code>{
     * @link java.lang.NullPointerException
     * }</code>
     * @return  String SUCCESS-its returns SUCCESS when all the login details of the consultant edited successfully.
     */
    public String doEdit() {
        boolean isEdit = false;
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";

            if (AuthorizationManager.getInstance().isAuthorizedUser("DO_EDIT_RECRUITMENT", userRoleId)) {
                try {
                    if (!("dbGrid".equalsIgnoreCase(getSubmitFrom()))) {


                        setModifiedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                        isEdit = ServiceLocator.getConsultantService().editConsultant(this);

                    }

                    //Calling lookUpDate() method to populate select components
                    lookUpData();

                    //Calling setAddAction() method to populate the login details of the consultant
                    setAddAction();

                    resultType = SUCCESS;

                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }

            }//END-Authorization Checking
        }//Close Session Checking
        //Calling setAddAction() method to populate the login details of the consultant

        return resultType;
    }

    /**
     * This method is invoke when the user wants to get the consultant details
     *  from the datebase ,populate it on jsp page
     *
     *  @throws NullPointerException
     *         If a NullPointerException exists and its <code>{
     * @link java.lang.NullPointerException
     * }</code>
     *
     * @return String SUCCESS-its returns SUCCESS when all the login details of the consultant getting from data base successfully.
     *
     */
    public String getConsultantEditDetails() throws Exception {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";

            if (AuthorizationManager.getInstance().isAuthorizedUser("GET_DETAILS_RECRUITMENT", userRoleId)) {
                try {
                    /**
                     *   Populates user required options to the Screens depending on the  options.
                     *
                     *   @param   Taking  getConsultantId() method  from  ApplicationConstants
                     *
                     *   @return    The CurrentConsultant returned  depends on the getConsultantId() method.
                     *
                     */
                    setCurrentConsultant(ServiceLocator.getConsultantService().getConsultant(getConsultantId()));

                    /** Calling lookUpDate() method to populate select components */
                    lookUpData();
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
     *  Populates user required options to the Screens depending on the  options.
     *@see com.mss.mirage.ApplicationConstants
     *
     *@throws NullPointerException
     *         If a NullPointerException exists and its <code>{
     *@link    java.lang.NullPointerException
     * }</code>
     *
     *
     * @return String SUCCESS-The  Result type is returned after complete the code of lookUpData
     * Method.
     *
     *
     */
    public String lookUpData() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";

            if (AuthorizationManager.getInstance().isAuthorizedUser("LOOKUP_DATA_RECRUITMENT", userRoleId)) {
                try {
                    datasourceDataProvider = DataSourceDataProvider.getInstance();
                    DefaultDataProvider defaultDataProvider = DefaultDataProvider.getInstance();
                    HibernateDataProvider hibernateDataProvider = HibernateDataProvider.getInstance();
                    httpSession = httpServletRequest.getSession(false);

                    /**
                     *   Populates user required options to the Screens depending on the  options.
                     *
                     *   @see com.mss.mirage.ApplicationConstants
                     *   @param   Taking GENDER_OPTIONS key from  ApplicationConstants
                     *   @return   The gender list  returned  depends on the GENDER_OPTIONS
                     *
                     *
                     */
                    setGenderList(defaultDataProvider.getGender(ApplicationConstants.GENDER_OPTIONS));
                    /**
                     *  Populates user required options to the Screens depending on the  options.
                     *   @see com.mss.mirage.ApplicationConstants
                     *   @param   Taking COUNTRY_OPTIONS key from  ApplicationConstants
                     *   @return   The CountryList  returned  depends on the COUNTRY_OPTIONS
                     *
                     *
                     */
                    setCountryList(hibernateDataProvider.getContries(ApplicationConstants.COUNTRY_OPTIONS));
                    /**
                     *   Populates user required options to the Screens depending on the  options.
                     *   @see com.mss.mirage.ApplicationConstants
                     *   @param   Taking MARITAL_STATUS_OPTIONS key from  ApplicationConstants
                     *   @return   The MaritalStatusList  returned  depends on the  MARITAL_STATUS_OPTIONS
                     *
                     *
                     */
                    setMaritalStatusList(defaultDataProvider.getMaritalStatus(ApplicationConstants.MARITAL_STATUS_OPTIONS));
                    /**
                     *   Populates user required options to the Screens depending on the  options.
                     *   @see com.mss.mirage.ApplicationConstants
                     *   @param   Taking PREFERED_QUESTION_OPTIONS key from  ApplicationConstants
                     *   @return   The MaritalStatusList  returned  depends on the  PREFERED_QUESTION_OPTIONS
                     *
                     *
                     */
                    setPrefferedQuestionsList(defaultDataProvider.getPrefferedQuestions(ApplicationConstants.PREFERED_QUESTION_OPTIONS));
                    /**
                     *   Populates user required options to the Screens depending on the  options.
                     *   @see com.mss.mirage.ApplicationConstants
                     *   @param   Taking REC_CONSULTANT_OPTION key from  ApplicationConstants
                     *   @return   The MaritalStatusList  returned  depends on the  REC_CONSULTANT_OPTION
                     *
                     *
                     */
                    setConsultantTypesList(hibernateDataProvider.getRecConsultant(ApplicationConstants.REC_CONSULTANT_OPTION));


                    /**
                     *   Populates user required options to the Screens depending on the  options.
                     *   @see com.mss.mirage.ApplicationConstants
                     *   @param   Taking TITLE_TYPE_OPTIONS key from  ApplicationConstants
                     *   @return   The TitleIdList  returned  depends on the TITLE_TYPE_OPTIONS
                     *
                     *
                     */
                    setTitleTypeIdList(hibernateDataProvider.getTitleType(ApplicationConstants.TITLE_TYPE_OPTIONS));
                    /**
                     *  Populates user required options to the Screens depending on the  options.
                     *   @see com.mss.mirage.ApplicationConstants
                     *   @param   Taking CURRENT_STATUS_OPTIONS  key from  ApplicationConstants
                     *   @return   The CurrentStatusList  returned  depends on the  CURRENT_STATUS_OPTIONS
                     *
                     *
                     */
                    setCurrStatusList(defaultDataProvider.getCurrentStatus(ApplicationConstants.CURRENT_STATUS_OPTIONS));

                    /**
                     *   Populates user required options to the Screens depending on the  options.
                     *   @see com.mss.mirage.ApplicationConstants
                     *   @param   Taking PRACTICE_OPTION key from  ApplicationConstants
                     *   @return   The PracticeIdList returned  depends on the PRACTICE_OPTION
                     *
                     *
                     */
                    setPracticeList(hibernateDataProvider.getPractice(ApplicationConstants.PRACTICE_OPTION));
                    /**
                     *   Populates user required options to the Screens depending on the  options.
                     *   @see com.mss.mirage.util.DataSourceDataProvider
                     *   @return   contactByMap
                     */
                    setContactByMap(datasourceDataProvider.getLastContactBy());
                    /**
                     *   Populates user required options to the Screens depending on the  options.
                     *    @see com.mss.mirage.util.DataSourceDataProvider
                     *   @return   recContactIdMap
                     */
                    setRecContactIdMap(datasourceDataProvider.getRecContactId());

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
     * The Add method is used to store all the values submitted in the form to database.
     * Here all the values are being first initalized to a method addConsultant from the
     * ConsultantService.java.
     * And from their the values are stored to the database.
     *
     * @throws NullPointerException
     * If a NullPointerException exists and its <code>{
     * @link    java.lang.NullPointerException}</code>
     *
     * @return String SUCCESS-its return SUCCESS when all the login details of the consultant
     * inserted into a data base successfully.
     *
     */
    public String doAdd() {

        /*   resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){*/

        boolean isAdd = false;
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";

            if (AuthorizationManager.getInstance().isAuthorizedUser("DO_ADD_RECRUITMENT", userRoleId)) {
                try {

                    if (!("dbGrid".equalsIgnoreCase(getSubmitFrom()))) {
                        setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                        isAdd = ServiceLocator.getConsultantService().addConsultant(this);
                    }//closing submit from

                    //Calling lookUpDate() method to populate select components
                    lookUpData();

                    //Calling setAddAction() method to populate the login details of the consultant
                    setAddAction();
                    resultType = SUCCESS;

                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }

            }//END-Authorization Checking
        }//Close Session Checking
        //Calling setAddAction() method to populate the login details of the consultant
        return resultType;

    }

    /**
     *   Populates user required options to the Grid depends on the serach queryString.
     *
     * @throws NullPointerException
     * If a NullPointerException exists and its <code>{
     * @link    java.lang.NullPointerException}</code>
     *
     *@return String SUCCESS-its returns SUCCESS when the required consultant searched by
     * name or phone number of the consultant.
     *
     */
    public String doSearch() {

        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";

            if (AuthorizationManager.getInstance().isAuthorizedUser("DO_SEARCH_RECRUITMENT", userRoleId)) {
                try {
                    String strSQL = "select Id,LName,FName,MName,Email2,CellPhoneNo from tblRecConsultant where CreatedBy='" + httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) + "' ";
                    //  If Consultant First Name,Middile Name,Last Name,and Phone number are blank
                    if (getSearchName() == null && getSearchPhone() == null) {
                        strSQL = strSQL;

                        //If Consultant First Name,Last Name,Middile Names are not blank and phone number is not blank
                    } else {
                        if (getSearchName().trim() != "" && !getSearchPhone().trim().equals("")) {

                            strSQL += "  and (FName like '%" + getSearchName() + "%' OR MName like '%" + getSearchName() + "%' OR LName like '%" + getSearchName() + "%')  AND CellPhoneNo like '%" + getSearchPhone() + "%'";

                            //If Consultant First Name,Last Name,Middile Names are not blank and phone number is blank
                        } else if (getSearchName().trim() != "" && getSearchPhone().trim().equals("")) {

                            strSQL += "  and (FName like '%" + getSearchName() + "%' OR MName like '%" + getSearchName() + "%' OR LName like '%" + getSearchName() + "%') ";

                            //If Consultant First Name,Last Name,Middile Names are blank and phone number is not blank

                        } else if (getSearchName().trim().equals("") && getSearchPhone().trim() != "") {
                            strSQL += "  and CellPhoneNo like '%" + getSearchPhone() + "%'";

                            //If Consultant First Name,Last Name,Middile Names are blank and phone number is  blank
                        } else if (getSearchName().trim() == "" && getSearchPhone().trim() == "") {
                            strSQL = strSQL;

                        }
                    }
                    getHttpServletRequest().setAttribute("strSQL", strSQL);

                    //Calling lookUpDate() method to populate select components.
                    lookUpData();

                    //Calling setAddAction() method to populate the login details of the consultant.
                    setAddAction();
                    resultType = SUCCESS;

                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }

            }//END-Authorization Checking
        }//Close Session Checking
        //Calling setAddAction() method to populate the login details of the consultant
        return resultType;
    }

    /**
     *   Populates user required options to the Grid depends on the serach queryString.
     *
     * @throws NullPointerException
     * If a NullPointerException exists and its <code>{
     * @link    java.lang.NullPointerException}</code>
     *
     *@return String SUCCESS-its returns SUCCESS when the required consultant searched by
     * Skills of the consultant.
     *
     */
    public String doSkillSearch() {

        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";

            if (AuthorizationManager.getInstance().isAuthorizedUser("DO_SKILL_SEARCH", userRoleId)) {
                try {
                    String strSQL = "select Id,LName,FName,MName,Email1,CellPhoneNo from tblRecConsultant ";
                    //  If Consultant First Name,Middile Name,Last Name,and Phone number are blank
                    if (getSkills() == null) {
                        strSQL = strSQL;

                        //If Consultant First Name,Last Name,Middile Names are not blank and phone number is not blank
                    } else {
                        if (!getSkills().trim().equals("")) {

                            strSQL += "WHERE Id IN (select EmpId from tblRecSkills  where MATCH(SkillsetUtilized,SkillSet,ProjectInfo)  AGAINST ('" + getSkills() + "' IN BOOLEAN MODE))";

                            //If Consultant First Name,Last Name,Middile Names are not blank and phone number is blank
                        } else {
                            strSQL = strSQL;
                        }
                    }
                    getHttpServletRequest().setAttribute("strSQL", strSQL);

                    //Calling lookUpDate() method to populate select components.
                    lookUpData();

                    //Calling setAddAction() method to populate the login details of the consultant.
                    setAddAction();
                    resultType = SUCCESS;

                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }

            }//END-Authorization Checking
        }//Close Session Checking
        //Calling setAddAction() method to populate the login details of the consultant
        return resultType;
    }

    /**
     *This method is use to delete a particular consultant.
     *
     * @throws NullPointerException
     *         If a NullPointerException exists and its <code>{
     * @link    java.lang.NullPointerException}</code>
     *
     * @return  String SUCCESS .
     *
     */
    public String doDelete() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";

            if (AuthorizationManager.getInstance().isAuthorizedUser("DO_DELETE_RECRUITMENT", userRoleId)) {
                try {
                    ServiceLocator.getConsultantService().deleteConsultant(getConsultantId());

                    //Calling lookUpDate() method to populate select components.
                    lookUpData();

                    //Calling setAddAction() method to populate the login details of the consultant.
                    setAddAction();
                    resultType = SUCCESS;
                    setResultMessage("Consultant Deleted Successfully !");
                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, getResultMessage());
                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }

            }//END-Authorization Checking
        }//Close Session Checking
        //Calling setAddAction() method to populate the login details of the consultant

        return resultType;
    }

    /*   public String doConsultantSearch() {
    
    //        String strStartGrid =null;
    //        String strEndGrid=null;
    
    int strStartGrid =0;
    int strEndGrid=0;
    resultType = LOGIN;
    StringBuffer queryStringBuffer = null;
    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
    userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
    resultType = "accessFailed";
    
    if(AuthorizationManager.getInstance().isAuthorizedUser("PREPARE_CONSULTANT_SEARCH",userRoleId)){
    try {
    HibernateDataProvider hibernateDataProvider = HibernateDataProvider.getInstance();
    datasourceDataProvider = DataSourceDataProvider.getInstance();
    //setPracticeList(hibernateDataProvider.getPractice(ApplicationConstants.PRACTICE_OPTION));
    setStatesList(hibernateDataProvider.getStatesList(ApplicationConstants.STATES_OPTIONS));
    // setAssignedMembers(datasourceDataProvider.getEmployeeNamesByUserId("Recruiting"));
    setAssignedMembers(datasourceDataProvider.getEmployeeNamesByRecruitingRole(httpServletRequest));
    
    defaultDataProvider = DefaultDataProvider.getInstance();
    //  NEW 
    setOrgMap(defaultDataProvider.getOrgMap(ApplicationConstants.ORG_MAP));
    
    setExpMap(defaultDataProvider.getExpMap(ApplicationConstants.EXP_MAP)); 
    queryStringBuffer =new StringBuffer();
    
    //  String valueSession = (String) httpServletRequest.getSession(false).getAttribute("isSearch");
    if(getAll()!=null){
    
    
    // queryStringBuffer.append("SELECT createdBy,Id,FName,CONCAT(trim(FName),' ', trim(MName),' ',trim(LName)) as Name, Email2," +
    //    "CellPhoneNo,SkillSet,ModifiedDate,LastContactDate FROM tblRecConsultant");
    queryStringBuffer.append("SELECT createdBy,Id,FName,CONCAT(TRIM(FName),' ', TRIM(MName),' ',TRIM(LName)) AS NAME,TitleTypeId,Email2,"
    + "Country,SkillSet ,CellPhoneNo,CreatedBy,ModifiedBy,LastContactDate,CreatedDate,ModifiedDate FROM tblRecConsultant ");
    queryStringBuffer.append(" ORDER BY modifiedDate DESC LIMIT 200");
    
    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.IS_SEARCH,null);
    
    }
    else{
    
    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.IS_SEARCH) != null)
    {
    
    //   if(!(httpServletRequest.getSession(false).getAttribute("search_Query_BackToList") == null))
    
    if(httpServletRequest.getSession(false).getAttribute("search_Query_BackToList") != null){
    queryStringBuffer = (StringBuffer)httpServletRequest.getSession(false).getAttribute("search_Query_BackToList");
    }
    }else
    {
    
    // queryStringBuffer.append("SELECT createdBy,Id,FName,CONCAT(trim(FName),' ', trim(MName),' ',trim(LName)) as Name, Email2," +
    //   "CellPhoneNo,SkillSet,ModifiedDate,LastContactDate FROM tblRecConsultant");
    queryStringBuffer.append("SELECT createdBy,Id,FName,CONCAT(TRIM(FName),' ', TRIM(MName),' ',TRIM(LName)) AS NAME,TitleTypeId,Email2,"
    + "Country,SkillSet ,CellPhoneNo,CreatedBy,ModifiedBy,LastContactDate,CreatedDate,ModifiedDate FROM tblRecConsultant ");
    queryStringBuffer.append(" ORDER BY modifiedDate DESC LIMIT 200");
    
    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.IS_SEARCH,null);
    }
    }
    
    
    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QUERY_STRING) != null){
    httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QUERY_STRING);
    }
    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QUERY_STRING,queryStringBuffer.toString());
    
    
    httpServletRequest.removeAttribute("strStartGrid");
    httpServletRequest.removeAttribute("strEndGrid");
    
    httpServletRequest.setAttribute("strStartGrid",strStartGrid);
    httpServletRequest.setAttribute("strEndGrid",strEndGrid);
    
    HttpSession session = httpServletRequest.getSession(true);
    session.removeAttribute("searchString");
    session.removeAttribute("gridSize");
    session.removeAttribute("searchResult");
    
    resultType = SUCCESS;
    
    }catch (Exception ex){
    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
    resultType =  ERROR;
    }
    }
    }
    return resultType;
    }
     */
    public String doConsultantSearch() {

//        String strStartGrid =null;
//        String strEndGrid=null;

        int strStartGrid = 0;
        int strEndGrid = 0;
        int isUserManager = 0;
        int isUserTeamLead = 0;
        String loginId = "";
        Map employeeMap = new TreeMap();
        // int empId=0;
        resultType = LOGIN;
        StringBuffer queryStringBuffer = null;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            //  empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());

            resultType = "accessFailed";
            loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            isUserManager = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_USER_MANAGER).toString());
            isUserTeamLead = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_TEAM_LEAD).toString());
            if (AuthorizationManager.getInstance().isAuthorizedUser("PREPARE_CONSULTANT_SEARCH", userRoleId)) {
                try {
                    HibernateDataProvider hibernateDataProvider = HibernateDataProvider.getInstance();
                    datasourceDataProvider = DataSourceDataProvider.getInstance();
                    //setPracticeList(hibernateDataProvider.getPractice(ApplicationConstants.PRACTICE_OPTION));
                    setStatesList(hibernateDataProvider.getStatesList(ApplicationConstants.STATES_OPTIONS));
                    // setAssignedMembers(datasourceDataProvider.getEmployeeNamesByUserId("Recruiting"));
                    if (isUserManager == 1 || isUserTeamLead == 1) {
                        setAssignedMembers(datasourceDataProvider.getEmployeeNamesByRecruitingRole());
                    } else {
                        String empName = datasourceDataProvider.getFname_Lname(loginId);
                        employeeMap.put(loginId, empName);
                        setAssignedMembers(employeeMap);
                    }


                    defaultDataProvider = DefaultDataProvider.getInstance();
                    //  NEW 
                    setOrgMap(defaultDataProvider.getOrgMap(ApplicationConstants.ORG_MAP));

                    setExpMap(defaultDataProvider.getExpMap(ApplicationConstants.EXP_MAP));
                    queryStringBuffer = new StringBuffer();

                    //  String valueSession = (String) httpServletRequest.getSession(false).getAttribute("isSearch");
                    if (getAll() != null) {


                        // queryStringBuffer.append("SELECT createdBy,Id,FName,CONCAT(trim(FName),' ', trim(MName),' ',trim(LName)) as Name, Email2," +
                        //    "CellPhoneNo,SkillSet,ModifiedDate,LastContactDate FROM tblRecConsultant");
                        queryStringBuffer.append("SELECT createdBy,Id,FName,CONCAT(TRIM(FName),' ', TRIM(MName),' ',TRIM(LName)) AS NAME,TitleTypeId,Email2,"
                                + "Country,SkillSet ,CellPhoneNo,CreatedBy,ModifiedBy,LastContactDate,CreatedDate,ModifiedDate FROM tblRecConsultant ");
                        queryStringBuffer.append(" ORDER BY modifiedDate DESC LIMIT 200");

                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.IS_SEARCH, null);

                    } else {

                        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.IS_SEARCH) != null) {

                            //   if(!(httpServletRequest.getSession(false).getAttribute("search_Query_BackToList") == null))

                            if (httpServletRequest.getSession(false).getAttribute("search_Query_BackToList") != null) {
                                queryStringBuffer = (StringBuffer) httpServletRequest.getSession(false).getAttribute("search_Query_BackToList");
                            }
                        } else {

                            // queryStringBuffer.append("SELECT createdBy,Id,FName,CONCAT(trim(FName),' ', trim(MName),' ',trim(LName)) as Name, Email2," +
                            //   "CellPhoneNo,SkillSet,ModifiedDate,LastContactDate FROM tblRecConsultant");
                            queryStringBuffer.append("SELECT createdBy,Id,FName,CONCAT(TRIM(FName),' ', TRIM(MName),' ',TRIM(LName)) AS NAME,TitleTypeId,Email2,"
                                    + "Country,SkillSet ,CellPhoneNo,CreatedBy,ModifiedBy,LastContactDate,CreatedDate,ModifiedDate FROM tblRecConsultant ");
                            queryStringBuffer.append(" ORDER BY modifiedDate DESC LIMIT 200");

                            httpServletRequest.getSession(false).setAttribute(ApplicationConstants.IS_SEARCH, null);
                        }
                    }


                    if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QUERY_STRING) != null) {
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QUERY_STRING);
                    }
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QUERY_STRING, queryStringBuffer.toString());


                    httpServletRequest.removeAttribute("strStartGrid");
                    httpServletRequest.removeAttribute("strEndGrid");

                    httpServletRequest.setAttribute("strStartGrid", strStartGrid);
                    httpServletRequest.setAttribute("strEndGrid", strEndGrid);

                    HttpSession session = httpServletRequest.getSession(true);
                    session.removeAttribute("searchString");
                    session.removeAttribute("gridSize");
                    session.removeAttribute("searchResult");

                    resultType = SUCCESS;

                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }
        }
        return resultType;
    }

    public String execute() {

        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";

            if (AuthorizationManager.getInstance().isAuthorizedUser("PREPARE_RECRUITMENT", userRoleId)) {
                try {
                    if (httpServletRequest.getParameter("requirement") == null) {
                        // if(getReqList()== null){
                        setReqList(getReqList());

                    } else {
                        setReqList(httpServletRequest.getParameter("requirement").toString());

                    }

                    defaultDataProvider = DefaultDataProvider.getInstance();


                    //  NEW 
                    setOrgMap(defaultDataProvider.getOrgMap(ApplicationConstants.ORG_MAP));
                    setExpMap(defaultDataProvider.getExpMap(ApplicationConstants.EXP_MAP));
                    //  ConsultantVTO consultantVTO=new ConsultantVTO();

                    HibernateDataProvider hibernateDataProvider = HibernateDataProvider.getInstance();
                    setPracticeList(hibernateDataProvider.getPractice(ApplicationConstants.PRACTICE_OPTION));
                    //setTitleTypeIdList(hibernateDataProvider.getTitleType(ApplicationConstants.TITLE_TYPE_OPTIONS));
                    setStatesList(hibernateDataProvider.getStatesList(ApplicationConstants.STATES_OPTIONS));
                    setIndustryList(hibernateDataProvider.getIndustry(ApplicationConstants.INDUSTRY_OPTION));
                    currentConsultant = new ConsultantVTO();
                    currentConsultant.setCurrentAction("addingConsultant");
                    currentConsultant.setActionName("getConsultant");
                    currentConsultant.setOrg("2USA");
                    setConsultId(getConsultId());
                    setRequirementAdminFlag(getRequirementAdminFlag());
                    setRequirementId(getRequirementId());
                    if (getEmpId() != null) {
                        setCurrentConsultant(ServiceLocator.getConsultantService().getConsultantDetails(getEmpId()));
                        //httpServletRequest.getSession(false).setAttribute("consultantId",getEmpId());
                    }
                    if (getConsultantId() != 0) {
                        setCurrentConsultant(ServiceLocator.getConsultantService().getConsultantDetails(String.valueOf(getConsultantId())));
                        //httpServletRequest.getSession(false).setAttribute("consultantId",getEmpId());

                    }
                    setSiteConsultants(getSiteConsultants());
                    httpServletRequest.getSession(false).setAttribute("consultantFirstName", getCurrentConsultant().getFiName());
                    httpServletRequest.getSession(false).setAttribute("consultantLastName", getCurrentConsultant().getLaName());
                    httpServletRequest.getSession(false).setAttribute("consultId", getConsultId());
                    httpServletRequest.getSession(false).setAttribute("requirementId", getRequirementId());
                    httpServletRequest.getSession(false).setAttribute("requirementAdminFlag", getRequirementAdminFlag());
                    
                    // httpServletRequest.getSession(false).setAttribute("orgName",getCurrentConsultant().getOrg());
                    /*if(getEmail1() == "none"){
                    setEmail2("");
                    }else{
                    setEmail2(getEmail1());
                    }*/
                    //setAddAction();

                    // if(getIsSearch()!=null)
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.IS_SEARCH, getIsSearch());

                    resultType = SUCCESS;

                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }
        }
        return resultType;
    }

    public String doAddConsultant() {

        int isAdd;
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            //    System.err.println("---"+upload+" *******--"+uploadContentType);
            if (AuthorizationManager.getInstance().isAuthorizedUser("DO_ADD_RECRUITMENT", userRoleId)) {
                try {

                    defaultDataProvider = DefaultDataProvider.getInstance();
                    setSiteConsultants(getSiteConsultants());
                    setReqList("-1");
                    //  NEW 
                    setOrgMap(defaultDataProvider.getOrgMap(ApplicationConstants.ORG_MAP));
                    setExpMap(defaultDataProvider.getExpMap(ApplicationConstants.EXP_MAP));
                    // currentConsultant = new ConsultantVTO();


                    setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                    isAdd = ServiceLocator.getConsultantService().doAddConsultant(this);
                    HibernateDataProvider hibernateDataProvider = HibernateDataProvider.getInstance();
                    setPracticeList(hibernateDataProvider.getPractice(ApplicationConstants.PRACTICE_OPTION));
                    //setTitleTypeIdList(hibernateDataProvider.getTitleType(ApplicationConstants.TITLE_TYPE_OPTIONS));
                    setStatesList(hibernateDataProvider.getStatesList(ApplicationConstants.STATES_OPTIONS));
                    //setIndustryList(hibernateDataProvider.getIndustry(ApplicationConstants.INDUSTRY_OPTION));
                    currentConsultant = new ConsultantVTO();
                    currentConsultant.setCurrentAction("addingConsultant");

                    currentConsultant.setActionName("getConsultant");
                    currentConsultant.setOrg("2USA");
                    resultType = SUCCESS;
                    if (isAdd == 1) {
                        setResultMessage("<font color=\"green\" size=\"1.5\">Consultant Details Added Successfully!</font>");
                    } else {
                        setResultMessage("<font color=\"red\" size=\"1.5\">Please Try Again</font>");
                    }
                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, getResultMessage());
                    //   System.err.println("---"+uploadFileName);
                    if (!getUploadFileName().equals("")) {
                        // System.err.println("In Resume Attach");
                        attachResume();

                    }


                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    ex.printStackTrace();
                    //httpServletRequest.getSession(false).setAttribute("errorMessage",resultMessage);
                    resultType = ERROR;
                }

            }//END-Authorization Checking
        }
        return resultType;
    }

    public String attachResume() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";

            if (AuthorizationManager.getInstance().isAuthorizedUser("INPUT_RECRUITMENT_ATTACHMENT", userRoleId)) {
                try {
                    //  System.err.println("Method called...");
                    /*The path which is created in the drive and used as a home directroy*/
                    String basePath = Properties.getProperty("Resume.Attachments")+ "//MirageV2//" + getRequestType();
                 //   File createPath = new File(basePath);

                   // createPath = new File(createPath.getAbsolutePath() + "//MirageV2//" + getRequestType());

                    String theFilePath = FileUploadUtility.getInstance().filePathGeneration(basePath);
                    String fileName = FileUploadUtility.getInstance().fileNameGeneration(getUploadFileName());
                    File theFile = new File(theFilePath + "//" + fileName);
                    //System.out.println("theFile..." + theFile.getAbsolutePath());

                    setFilepath(theFile.toString());
                    FileUtils.copyFile(getUpload(), theFile);
                    //   System.err.println("Here...1");
                    //boolean isInsert=ServiceLocator.getConsultantService().attachResume1(this);
                    int isInsert = ServiceLocator.getConsultantService().newAttachResume(this);
                    //     System.err.println("Here...2");
                    /**
                     * To view the consultant details on
                     * consultantDetailsView.jsp after attaching a resume of
                     * consultant.
                     *
                     * @see
                     * com.mss.mirage.recruitment.consultantdetails.ConsultantVTO.
                     */
                    //setConsultantDetails( (ConsultantVTO)request.getSession(false).getAttribute("ConsultVTO"));
                    resultType = SUCCESS;
                    if (isInsert == 1) {
                        //createResumeIndex(getFilepath());
                        setResultMessage("<font color=\"green\" size=\"1.5\">Consultant Details Added Successfully!</font>");
                    } else {
                        setResultMessage("<font color=\"red\" size=\"1.5\">Please Try Again</font>");
                    }
                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, getResultMessage());
                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    ex.printStackTrace();
                    //System.err.println("In Action Class Catch"+ex.getMessage());
                    //httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType = ERROR;
                }

            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }	

    public static void createResumeIndex(String fileSavedPath) throws ServiceLocatorException, IOException {
        //System.out.println("file save path :::::::"+fileSavedPath);

        File indexDir = new File(Properties.getProperty("Lucene.Index.Path"));

        //File dataDir = new File(fileSavedPath);
        // only for the uploaded resume only we are creating index.
        File dataDir = new File(Properties.getProperty("Lucene.Resume.Attachments"));

        // in the above line we are creating indexes for all the files in the above Location

        //System.out.println("indexdir is ::::"+indexDir);
        //System.out.println("dataDir is ::::"+dataDir);

        long start = new java.util.Date().getTime();
        int numIndexed = index(indexDir, dataDir);
        long end = new java.util.Date().getTime();
        //System.out.println("Indexing " + numIndexed + " files took "
        //+ (end - start) + " milliseconds");

        //return "abc";
    }

    // open an index and start file directory traversal
    public static int index(File indexDir, File dataDir) throws IOException {

// Listing 1.1 Indexer: traverses a file system and indexes .txt files
// Create Lucene index in this directory
// Index files in this directory

        /*
        if (!dataDir.exists() || !dataDir.isDirectory()) {
        throw new IOException(dataDir
        + " does not exist or is not a directory");
        }*/

        IndexWriter writer = new IndexWriter(indexDir, new StandardAnalyzer(), true);
        writer.setUseCompoundFile(false);
        indexDirectory(writer, dataDir);
        int numIndexed = writer.docCount();
        writer.optimize();
        writer.close();
        return numIndexed;
    }

    // recursive method that calls itself when it finds a directory
    private static void indexDirectory(IndexWriter writer, File dir)
            throws IOException {

        /*
        File f = dir;
        if (f.isDirectory()) {
        indexDirectory(writer, f);
        } else if (f.getName().endsWith(".txt")) {
        indexFile(writer, f);
        }else if (f.getName().endsWith(".doc")) {
        indexFile(writer, f);
        }*/


        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; i++) {
            File f = files[i];
            if (f.isDirectory()) {
                indexDirectory(writer, f);
            } else if (f.getName().endsWith(".txt")) {
                indexFile(writer, f);
            } else if (f.getName().endsWith(".doc")) {
                indexFile(writer, f);
            }
        }
    }

// method to actually index a file using Lucene
    private static void indexFile(IndexWriter writer, File f) throws IOException {

        if (f.isHidden() || !f.exists() || !f.canRead()) {
            return;
        }
        //System.out.println("Indexing " + f.getCanonicalPath());
        Document doc = new Document();
        doc.add(Field.Text("contents", new FileReader(f)));
        //doc.add(Field.Keyword("filename", f..getCanonicalPath()));
        doc.add(Field.Keyword("filename", f.getAbsolutePath()));
        writer.addDocument(doc);

    }

    public String editConsultant() throws ServiceLocatorException {
        int isEdit;
        resultType = LOGIN;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            //   System.err.println("---"+upload+" *******--"+uploadContentType);
            if (AuthorizationManager.getInstance().isAuthorizedUser("DO_EDIT_RECRUITMENT", userRoleId)) {
                try {

                    defaultDataProvider = DefaultDataProvider.getInstance();
                    setSiteConsultants(getSiteConsultants());
                    setReqList(getReqList());
                    //  NEW 
                    setOrgMap(defaultDataProvider.getOrgMap(ApplicationConstants.ORG_MAP));
                    setExpMap(defaultDataProvider.getExpMap(ApplicationConstants.EXP_MAP));
                    setModifiedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());

                    //System.out.println("before doedit()");
                    isEdit = ServiceLocator.getConsultantService().doEdit(this);
                    HibernateDataProvider hibernateDataProvider = HibernateDataProvider.getInstance();
                    setPracticeList(hibernateDataProvider.getPractice(ApplicationConstants.PRACTICE_OPTION));
                    setStatesList(hibernateDataProvider.getStatesList(ApplicationConstants.STATES_OPTIONS));
                    //setIndustryList(hibernateDataProvider.getIndustry(ApplicationConstants.INDUSTRY_OPTION));
                    setCurrentConsultant(ServiceLocator.getConsultantService().getConsultantDetails(getObjectId()));
                    setConsultId(getConsultId());
                    setRequirementAdminFlag(getRequirementAdminFlag());
                    setRequirementId(getRequirementId());
                    resultType = SUCCESS;

                    if (!getUploadFileName().equals("")) {
                        attachResume();
                    }

                    if (isEdit == 1) {
                        setResultMessage("<font color=\"green\" size=\"1.5\">Consultant Details Modified Successfully!</font>");
                    } else {
                        setResultMessage("<font color=\"red\" size=\"1.5\">Please Try Again</font>");
                    }
                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, getResultMessage());
                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    //ex.printStackTrace();
                    //httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType = "exception";
                    throw new ServiceLocatorException(ex);
                }

            }//END-Authorization Checking
        }//Close Session Checking
        //Calling setAddAction() method to populate the login details of the consultant

        return resultType;
    }

    public String consultantActivity() throws ServiceLocatorException {

        resultType = LOGIN;
        String queryString;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";

            try {
                datasourceDataProvider = DataSourceDataProvider.getInstance();
                hibernateDataProvider = HibernateDataProvider.getInstance();
                setActivityStatusList(hibernateDataProvider.getCrmAccountStatus(ApplicationConstants.CRM_ACCOUNT_STATUS_OPTION));

                setConsultantName(datasourceDataProvider.getInstance().getConsultantName(getConsultantId()));
                // setAssignedMembers(datasourceDataProvider.getEmployeeNamesByUserId("Recruiting"));
                setAssignedMembers(datasourceDataProvider.getEmployeeNamesByRecruitingRole());
                // setDueDateTimeStamp(DateUtility.getInstance().strToTimeStampObj(getDueDate()));
                currentConsultant = new ConsultantVTO();
                //currentConsultant.setCurrentAction("addActivity");
                if (getConsultantId() != 0) {
                    setCurrentConsultant(ServiceLocator.getConsultantService().getConsultantActivity(getConsultantId(), getId()));
                    queryString = "SELECT Id,ConsultantId,ActivityType,Status,Availability,Comments,InterviewDate,ReportingDate,CreatedBy,CreatedDate,AssignedTo FROM tblRecActivity"
                            + " WHERE ConsultantId='" + getConsultantId() + "' ORDER BY CreatedDate DESC";
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QUERY_STRING, queryString);

                    //httpServletRequest.getSession(false).setAttribute("consultantId",getConsultantId());
                }
                currentConsultant.setConsultantId(getConsultantId());
                resultType = SUCCESS;
            } catch (Exception ex) {
                resultType = "exception";
                throw new ServiceLocatorException(ex);
            }
        }

        return resultType;
    }

    public String consultantActivityAdd() throws ServiceLocatorException {
        int isAdd;
        resultType = LOGIN;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";

            try {
                hibernateDataProvider = HibernateDataProvider.getInstance();
                setActivityStatusList(hibernateDataProvider.getCrmAccountStatus(ApplicationConstants.CRM_ACCOUNT_STATUS_OPTION));

                setConsultantName(datasourceDataProvider.getInstance().getConsultantName(getConsultantId()));
                setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                setDueDateTimeStamp(DateUtility.getInstance().strToTimeStampObj(getDueDate()));
                isAdd = ServiceLocator.getConsultantService().doAddConsultantActivity(this, httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                datasourceDataProvider = DataSourceDataProvider.getInstance();
                // setAssignedMembers(datasourceDataProvider.getEmployeeNamesByUserId("Recruiting"));
                setAssignedMembers(datasourceDataProvider.getEmployeeNamesByRecruitingRole());
                currentConsultant = new ConsultantVTO();
                currentConsultant.setCurrentAction("addActivity");
                currentConsultant.setConsultantId(getConsultantId());

                if (httpServletRequest.getSession(false).getAttribute("skillUniqueID") != null) {
                    //if(getReqList()!=null){
                    setReqList(httpServletRequest.getSession(false).getAttribute("skillUniqueID").toString());

                    //  request.getSession(false).removeAttribute("skillEdit");
                    //System.out.println("ConsultantDetailsAction reqList --->"+);
                } else {
                    setReqList("-1");
                }


                if (isAdd == 1) {
                    resultType = SUCCESS;
                    setResultMessage("<font color=\"green\" size=\"1.5\">Activity has been Added Successfully!</font>");
                } else {
                    resultType = "error";
                    setResultMessage("<font color=\"red\" size=\"1.5\">Please Try Again</font>");
                }
                httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, getResultMessage());
            } catch (Exception ex) {
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                ex.printStackTrace();
                //httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                //resultType="exception";
                throw new ServiceLocatorException(ex);
            }
        }

        //  System.out.println("resultType---->"+resultType);
        return resultType;
    }

    public String consultantActivityEdit() throws ServiceLocatorException {
        int isUpdate;
        resultType = LOGIN;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";

            try {
                hibernateDataProvider = HibernateDataProvider.getInstance();
                setActivityStatusList(hibernateDataProvider.getCrmAccountStatus(ApplicationConstants.CRM_ACCOUNT_STATUS_OPTION));
                setConsultantName(datasourceDataProvider.getInstance().getConsultantName(getConsultantId()));
                setModifiedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                //  setModifiedDate(new Timestamp(new java.util.Date().getTime()));
                setModifiedDate(DateUtility.getInstance().getCurrentMySqlDateTime());
                setDueDateTimeStamp(DateUtility.getInstance().strToTimeStampObj(getDueDate()));
                isUpdate = ServiceLocator.getConsultantService().doEditConsultantActivity(this);
                datasourceDataProvider = DataSourceDataProvider.getInstance();
                // setAssignedMembers(datasourceDataProvider.getEmployeeNamesByUserId("Recruiting"));
                setAssignedMembers(datasourceDataProvider.getEmployeeNamesByRecruitingRole());
                currentConsultant = new ConsultantVTO();
                currentConsultant.setCurrentAction("addActivity");
                currentConsultant.setConsultantId(getConsultantId());
                if (isUpdate == 1) {
                    resultType = SUCCESS;
                    setResultMessage("<font color=\"green\" size=\"1.5\">Activity has been Modified Successfully!</font>");
                } else {
                    resultType = "error";
                    setResultMessage("<font color=\"red\" size=\"1.5\">Please Try Again</font>");
                }
                httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, getResultMessage());
            } catch (Exception ex) {
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                ex.printStackTrace();
                //httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                //resultType="exception";
                throw new ServiceLocatorException(ex);
            }
        }
        return resultType;
    }

    public String doNextGridResume() {

        int strStartGrid = 0;
        int strEndGrid = 0;
        int pageSize = 0;
        int gridSplit = 0;

        try {

            int gettxtStartGrid = Integer.parseInt(httpServletRequest.getParameter("startValue"));
            int gettxtEndGrid = Integer.parseInt(httpServletRequest.getParameter("endValue"));


            String buttonValue = httpServletRequest.getParameter("button");
            //System.out.println("button :::::::"+buttonValue);

            HttpSession session = httpServletRequest.getSession(true);
            String gridSize = session.getAttribute("gridSize").toString();
            int gridLength = Integer.parseInt(gridSize);

            /*
            if(buttonValue.equalsIgnoreCase("Next")){
            strStartGrid = 151;
            strEndGrid = 175;
            
            httpServletRequest.setAttribute("strStartGrid",strStartGrid);
            httpServletRequest.setAttribute("strEndGrid",strEndGrid);
            }*/


            if (buttonValue.equalsIgnoreCase("Next")) {

                if (gridLength != gettxtEndGrid) {

                    gridSplit = gettxtEndGrid;
                    gridSplit = gridLength - gridSplit;

                    if (gridSplit >= 30) {
                        strStartGrid = gettxtStartGrid + 30;
                        strEndGrid = gettxtEndGrid + 30;
                    } else {
                        strStartGrid = gettxtStartGrid + 30;
                        strEndGrid = gettxtEndGrid + gridSplit;
                    }
                } else {
                    strStartGrid = 0;
                    strEndGrid = 0;
                }

                httpServletRequest.setAttribute("strStartGrid", strStartGrid);
                httpServletRequest.setAttribute("strEndGrid", strEndGrid);
            }

            if (buttonValue.equalsIgnoreCase("Previous")) {

                gridSplit = gettxtStartGrid;
                //System.out.println("gridSplit11:::::::"+gridSplit);
                gridSplit = gridLength - gridSplit;
                //System.out.println("gridSplit22:::::::"+gridSplit);

                /*
                if(gridSplit >30){
                strStartGrid = gettxtStartGrid - 30;
                strEndGrid = gettxtEndGrid - 30;
                }else{
                strStartGrid = 0;
                strEndGrid = 0;
                }*/

                if (gridSplit > 0) {
                    strStartGrid = gettxtStartGrid - 30;
                    //strEndGrid = gettxtEndGrid - 30;
                    strEndGrid = strStartGrid + 30;
                }


                httpServletRequest.setAttribute("strStartGrid", strStartGrid);
                httpServletRequest.setAttribute("strEndGrid", strEndGrid);
            }

            if (buttonValue.equalsIgnoreCase("First")) {

                strStartGrid = 0;
                strEndGrid = strStartGrid + 30;

                httpServletRequest.setAttribute("strStartGrid", strStartGrid);
                httpServletRequest.setAttribute("strEndGrid", strEndGrid);
            }

            if (buttonValue.equalsIgnoreCase("Last")) {

                strStartGrid = gridLength - 30;
                strEndGrid = gridLength;

                httpServletRequest.setAttribute("strStartGrid", strStartGrid);
                httpServletRequest.setAttribute("strEndGrid", strEndGrid);
            }

            HibernateDataProvider hibernateDataProvider;
            hibernateDataProvider = HibernateDataProvider.getInstance();
            datasourceDataProvider = DataSourceDataProvider.getInstance();
            setPracticeList(hibernateDataProvider.getPractice(ApplicationConstants.PRACTICE_OPTION));
            setStatesList(hibernateDataProvider.getStatesList(ApplicationConstants.STATES_OPTIONS));
            // setAssignedMembers(datasourceDataProvider.getEmployeeNamesByUserId("Recruiting"));
            setAssignedMembers(datasourceDataProvider.getEmployeeNamesByRecruitingRole());

        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        }


        return "success";
    }

    public String doResumeSearch() {
        String searchString;
        int gridSize;
        int strStartGrid = 0;
        int strEndGrid = 0;

        searchString = httpServletRequest.getParameter("resumeText");

        // System.out.println("seacrh:::::::"+searchString);

        /*
        File indexDir = new File(args[0]);
        String q = args[1];
        if (!indexDir.exists() || !indexDir.isDirectory()) {
        throw new Exception(indexDir +
        " does not exist or is not a directory.");
        }
        search(indexDir, q);
         */

        File indexDir = new File(Properties.getProperty("Lucene.Index.Path"));
        String q = searchString;

        try {
            /*if (!indexDir.exists() || !indexDir.isDirectory()) {
            throw new Exception(indexDir +
            " does not exist or is not a directory.");
            }*/


            HibernateDataProvider hibernateDataProvider = HibernateDataProvider.getInstance();
            datasourceDataProvider = DataSourceDataProvider.getInstance();
            setPracticeList(hibernateDataProvider.getPractice(ApplicationConstants.PRACTICE_OPTION));
            setStatesList(hibernateDataProvider.getStatesList(ApplicationConstants.STATES_OPTIONS));
            //  setAssignedMembers(datasourceDataProvider.getEmployeeNamesByUserId("Recruiting"));
            setAssignedMembers(datasourceDataProvider.getEmployeeNamesByRecruitingRole());
            HttpSession session = httpServletRequest.getSession(true);

            session.removeAttribute("searchResult");
            session.removeAttribute("searchString");
            session.removeAttribute("gridSize");

            //search(indexDir, q);
            List searchResult = null;
            searchResult = search(indexDir, q);
            //System.out.println("search result is::::::"+searchResult);

            session.setAttribute("searchResult", searchResult);
            session.setAttribute("searchString", searchString);

            if (searchResult.size() > 0) {
                gridSize = searchResult.size();
                //System.out.println("searchResult list size:::"+gridSize);
                session.setAttribute("gridSize", gridSize);
                //System.out.println("grid size::::::::"+session.getAttribute("gridSize"));

                if (searchResult.size() < 30) {
                    strStartGrid = 0;
                    httpServletRequest.setAttribute("strStartGrid", strStartGrid);
                    strEndGrid = searchResult.size();
                    httpServletRequest.setAttribute("strEndGrid", strEndGrid);
                } else {
                    strStartGrid = 0;
                    httpServletRequest.setAttribute("strStartGrid", strStartGrid);
                    strEndGrid = 30;
                    httpServletRequest.setAttribute("strEndGrid", strEndGrid);
                }
                resultType = SUCCESS;
            } else {
                strStartGrid = 0;
                httpServletRequest.setAttribute("strStartGrid", strStartGrid);
                strEndGrid = 0;
                httpServletRequest.setAttribute("strEndGrid", strEndGrid);
                resultType = INPUT;
                setResultMessage("<font color=\"red\" size=\"1.5\">Sorry! Please Try Search Again !</font>");
            }
            /*
            int gettxtStartGrid = Integer.parseInt(httpServletRequest.getParameter("txtStartGrid"));
            int gettxtEndGrid = Integer.parseInt(httpServletRequest.getParameter("txtEndGrid"));
            
            if( gettxtStartGrid != 0){
            strEndGrid = Integer.parseInt(httpServletRequest.getParameter("txtEndGrid"));
            strStartGrid = strEndGrid + 1;
            httpServletRequest.setAttribute("strStartGrid",strStartGrid);
            }else{
            strStartGrid = 1;
            httpServletRequest.setAttribute("strStartGrid",strStartGrid);
            }
            
            if( gettxtEndGrid != 0){
            strEndGrid = Integer.parseInt(httpServletRequest.getParameter("txtEndGrid"));
            strEndGrid = strEndGrid + 30;
            }else{
            strEndGrid = 30;
            httpServletRequest.setAttribute("strEndGrid",strEndGrid);
            }*/
            httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, getResultMessage());
        } catch (Exception ex) {
            //ex.printStackTrace();
            httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
            resultType = ERROR;
        }
        return resultType;
    }

    public List search(File indexDir, String q)
            throws Exception {

        List searchResult = new ArrayList();

        Directory fsDir = FSDirectory.getDirectory(indexDir, false);
        IndexSearcher is = new IndexSearcher(fsDir);
        Query query = QueryParser.parse(q, "contents", new StandardAnalyzer());
        long start = new java.util.Date().getTime();
        Hits hits = is.search(query);
        long end = new java.util.Date().getTime();
        /*System.err.println("Found " + hits.length() +
        " document(s) (in " + (end - start) +
        " milliseconds) that matched query '" +
        q + "':");*/


        for (int i = 0; i < hits.length(); i++) {
            ConsultantSearchResultBean resultBean = new ConsultantSearchResultBean();

            Document doc = hits.doc(i);
            resultBean.setFilePath(doc.get("filename"));
            //System.out.println(doc.get("filename"));

            searchResult.add(resultBean);

        }
        is.close();

        return searchResult;
    }

    /*public String getConsultantList() throws ServiceLocatorException {
    resultType = LOGIN;
    StringBuffer queryStringBuffer = null;
    DateUtility dateUtil = new DateUtility();
    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
    userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
    resultType = "accessFailed";
    
    if(AuthorizationManager.getInstance().isAuthorizedUser("DO_SKILL_SEARCH",userRoleId)){
    try{
    String workAuthorizationList =  "";
    String states =  "";
    defaultDataProvider = DefaultDataProvider.getInstance();
    //  NEW 
    setOrgMap(defaultDataProvider.getOrgMap(ApplicationConstants.ORG_MAP));
    setExpMap(defaultDataProvider.getExpMap(ApplicationConstants.EXP_MAP));      
    if(getSubmitFrom() == null) {
    queryStringBuffer =new StringBuffer();
    
    if(!"".equals(getYrsExp())) {
    //                             queryStringBuffer.append("SELECT createdBy,tblRecConsultant.Id,FName," +
    //                             "CONCAT(TRIM(FName),' ', TRIM(MName),' ',TRIM(LName)) AS NAME, Email2,CellPhoneNo," +
    //                             "tblRecConsultant.SkillSet,ModifiedDate,LastContactDate FROM tblRecConsultant,tblRecSkills ");
    queryStringBuffer.append("SELECT createdBy,tblRecConsultant.Id,FName," +
    "CONCAT(TRIM(FName),' ', TRIM(MName),' ',TRIM(LName)) AS NAME,TitleTypeId, Email2,CellPhoneNo," +
    "tblRecConsultant.SkillSet,ModifiedDate,Country,LastContactDate,ModifiedBy,CreatedDate,ModifiedDate FROM tblRecConsultant,tblRecSkills ");
    } else{
    //                            queryStringBuffer.append("SELECT createdBy,Id,FName,CONCAT(trim(FName),' ', trim(MName),' ',trim(LName)) as Name, Email2," +
    //                            "CellPhoneNo,SkillSet,ModifiedDate,LastContactDate FROM tblRecConsultant");
    queryStringBuffer.append("SELECT createdBy,Id,FName,CONCAT(trim(FName),' ', trim(MName),' ',trim(LName)) as Name,TitleTypeId, Email2," +
    "CellPhoneNo,SkillSet,ModifiedDate,Country,LastContactDate,ModifiedBy,CreatedDate,ModifiedDate FROM tblRecConsultant");
    }
    
    
    if(null == getFiName()) setFiName("");
    if(null == getSkills()) setSkills("");
    if(null == getComments()) setComments("");
    //if(null == getLocation()) setLocation("");
    //  if(null == getLocation()) setLocation("");
    if(null == getEmail1()) setEmail1("");
    // if(null == getWorkAuthorization()) setWorkAuthorization("");
    if(null == getTechnology()) setTechnology("");
    //Added by Ajay
    if(null == getAvailable()) setAvailable("");
    //  if(null == getWorkAuthorization()) setWorkAuthorization("");
    if(null == getSource()) setSource("");
    if(null == getAssignedTo()) setAssignedTo("");
    if(null == getAvailableFrom()) {
    
    }
    if(null == getTitleTypeId()) setTitleTypeId("");
    
    //   if("-1".equals(getAssignedTo())) setAssignedTo("");
    
    
    if((!"".equals(getFiName()))
    || (!"".equals(getSkills()))
    || (!getLocationList().isEmpty())
    || (!"".equals(getComments()))
    || (!"".equals(getEmail1()))
    || (!getWorkAuthorizationList().isEmpty())
    || (!"".equals(getTechnology()))
    || (!"".equals(getAvailable()))         
    || (!"".equals(getSource()))
    || (!"".equals(getYrsExp()))
    || (!"".equals(getAssignedTo()))
    ||(!"".equals(getTitleTypeId()))
    || (!"".equals(getStartDate())
    || (getAvailableFrom() != null)
    || (!"All".equals(getOrg()))
    || (!"".equals(getEndDate())))){
    queryStringBuffer.append(" WHERE ");
    
    }
    
    int columnCounter = 0;
    
    if(!"".equals(getFiName()) && columnCounter==0){
    //tempName = getFiName();
    if((getFiName().indexOf("*") == -1)&&(getFiName().indexOf("%") == -1)) setFiName(getFiName()+"*");
    setFiName(getFiName().replace("*","%"));
    queryStringBuffer.append("(FName LIKE '" + getFiName() + "' or LName LIKE '" + getFiName()+ "' or MName LIKE '" + getFiName()+ "') ");
    columnCounter ++;
    }else if(!"".equals(getFiName()) && columnCounter!=0){
    //tempName = getFiName();
    if((getFiName().indexOf("*") == -1)&&(getFiName().indexOf("%") == -1)) setFiName(getFiName()+"*");
    setFiName(getFiName().replace("*","%"));
    queryStringBuffer.append("AND (FName LIKE '" + getFiName() + "' or LName LIKE '" + getFiName()+ "' or MName LIKE '" + getFiName()+ "') ");
    }
    
    if(!"".equals(getSkills()) && columnCounter==0){
    //tempName = getFiName();
    if((getSkills().indexOf("*") == -1)&&(getSkills().indexOf("%") == -1)) setSkills(getSkills()+"*");
    setSkills(getSkills().replace("%","*"));
    queryStringBuffer.append("MATCH(tblRecConsultant.SkillSet) AGAINST ('" + getSkills() + "' IN BOOLEAN MODE) ");
    columnCounter ++;
    }else if(!"".equals(getSkills()) && columnCounter!=0){
    //tempName = getFiName();
    if((getSkills().indexOf("*") == -1)&&(getSkills().indexOf("%") == -1)) setSkills(getSkills()+"*");
    setSkills(getSkills().replace("%","*"));
    queryStringBuffer.append("AND MATCH(tblRecConsultant.SkillSet) AGAINST ('" + getSkills() + "' IN BOOLEAN MODE) ");
    }
    
    
    
    if(!getLocationList().isEmpty() && columnCounter==0){
    
    for(int i=0;i<getLocationList().size();i++)
    {
    states += "'"+getLocationList().get(i).toString().trim()+"',";
    }
    states = states.substring(0,states.length()-1);
    queryStringBuffer.append(" Country IN (" + states + ") ");
    columnCounter ++;
    }else if(!getLocationList().isEmpty() && columnCounter!=0){
    
    for(int i=0;i<getLocationList().size();i++)
    {
    states += "'"+getLocationList().get(i).toString().trim()+"',";
    }
    states = states.substring(0,states.length()-1);
    queryStringBuffer.append("AND Country IN (" + states + ") ");
    
    }
    
    
    if(!getWorkAuthorizationList().isEmpty() && columnCounter==0){
    
    for(int i=0;i<getWorkAuthorizationList().size();i++)
    {
    workAuthorizationList += "'"+getWorkAuthorizationList().get(i).toString().trim()+"',";
    }
    workAuthorizationList = workAuthorizationList.substring(0,workAuthorizationList.length()-1);
    //   System.out.println("staates -- > "+ states);
    queryStringBuffer.append(" WorkAuthorization IN (" + workAuthorizationList + ") ");
    columnCounter ++;
    }else if(!getWorkAuthorizationList().isEmpty() && columnCounter!=0){
    
    for(int i=0;i<getWorkAuthorizationList().size();i++)
    {
    workAuthorizationList += "'"+getWorkAuthorizationList().get(i).toString().trim()+"',";
    
    }
    workAuthorizationList = workAuthorizationList.substring(0,workAuthorizationList.length()-1);
    queryStringBuffer.append("AND WorkAuthorization IN (" + workAuthorizationList + ") ");
    
    }
    
    
    
    
    if(!"".equals(getComments()) && columnCounter==0){
    //tempName = getFiName();
    if((getComments().indexOf("*") == -1)&&(getComments().indexOf("%") == -1)) setComments(getComments()+"*");
    setComments(getComments().replace("*","%"));
    queryStringBuffer.append("MATCH(Comments) AGAINST ('" + getComments() + "' IN BOOLEAN MODE) ");
    columnCounter ++;
    }else if(!"".equals(getComments()) && columnCounter!=0){
    //tempName = getFiName();
    if((getComments().indexOf("*") == -1)&&(getComments().indexOf("%") == -1)) setComments(getComments()+"*");
    setComments(getComments().replace("*","%"));
    queryStringBuffer.append("AND MATCH(Comments) AGAINST ('" + getComments() + "' IN BOOLEAN MODE) ");
    }
    
    if(!"".equals(getEmail1()) && columnCounter==0){
    //tempName = getFiName();
    if((getEmail1().indexOf("*") == -1)&&(getEmail1().indexOf("%") == -1)) setEmail1(getEmail1()+"*");
    setEmail1(getEmail1().replace("*","%"));
    queryStringBuffer.append("Email2 LIKE '" + getEmail1() + "' ");
    columnCounter ++;
    }else if(!"".equals(getEmail1()) && columnCounter!=0){
    //tempName = getFiName();
    if((getEmail1().indexOf("*") == -1)&&(getEmail1().indexOf("%") == -1)) setEmail1(getEmail1()+"*");
    setEmail1(getEmail1().replace("*","%"));
    queryStringBuffer.append("AND Email2 LIKE '" + getEmail1() + "' ");
    }
    
    
    if(!"".equals(getSource()) && columnCounter==0){
    //tempName = getFiName();
    if((getSource().indexOf("*") == -1)&&(getSource().indexOf("%") == -1)) setSource(getSource()+"*");
    setSource(getSource().replace("*","%"));
    queryStringBuffer.append("Source LIKE '" + getSource() + "' ");
    columnCounter ++;
    }else if(!"".equals(getSource()) && columnCounter!=0){
    //tempName = getFiName();
    if((getSource().indexOf("*") == -1)&&(getSource().indexOf("%") == -1)) setSource(getSource()+"*");
    setSource(getSource().replace("*","%"));
    queryStringBuffer.append("AND Source LIKE '" + getSource() + "' ");
    }
    if(!"".equals(getAssignedTo()) && columnCounter==0){
    //tempName = getFiName();
    if((getAssignedTo().indexOf("*") == -1)&&(getAssignedTo().indexOf("%") == -1)) setAssignedTo(getAssignedTo()+"*");
    setAssignedTo(getAssignedTo().replace("*","%"));
    queryStringBuffer.append("CreatedBy LIKE '" + getAssignedTo() + "' ");
    columnCounter ++;
    }else if(!"".equals(getAssignedTo()) && columnCounter!=0){
    //tempName = getFiName();
    if((getAssignedTo().indexOf("*") == -1)&&(getAssignedTo().indexOf("%") == -1)) setAssignedTo(getAssignedTo()+"*");
    setAssignedTo(getAssignedTo().replace("*","%"));
    queryStringBuffer.append("AND CreatedBy LIKE '" + getAssignedTo() + "' ");
    }
    
    if(!"".equals(getYrsExp()) && columnCounter==0){
    //tempName = getFiName();
    if((getYrsExp().indexOf("*") == -1)&&(getYrsExp().indexOf("%") == -1)) setYrsExp(getYrsExp()+"*");
    setYrsExp(getYrsExp().replace("*","%"));
    queryStringBuffer.append("YearsOfExperience LIKE '" + getYrsExp() + "' ");
    columnCounter ++;
    }else if(!"".equals(getYrsExp()) && columnCounter!=0){
    //tempName = getFiName();
    if((getYrsExp().indexOf("*") == -1)&&(getYrsExp().indexOf("%") == -1)) setYrsExp(getYrsExp()+"*");
    setYrsExp(getYrsExp().replace("*","%"));
    queryStringBuffer.append("AND YearsOfExperience LIKE '" + getYrsExp() + "' ");
    }
    
    if(getAvailableFrom() !=null && columnCounter==0){
    queryStringBuffer.append(" AvailableFrom >= '" + DateUtility.getInstance().convertDateToMySql(getAvailableFrom()) + "' ");
    columnCounter ++;
    }else if(getAvailableFrom() !=null && columnCounter!=0){
    queryStringBuffer.append(" AND AvailableFrom >= '" + DateUtility.getInstance().convertDateToMySql(getAvailableFrom()) + "' ");
    }
    
    if(getAvailableTo() !=null && columnCounter==0){
    queryStringBuffer.append(" AvailableFrom <= '" + DateUtility.getInstance().convertDateToMySql(getAvailableTo()) + "' ");
    columnCounter ++;
    }else if(getAvailableTo() !=null && columnCounter!=0){
    queryStringBuffer.append(" AND AvailableFrom <= '" + DateUtility.getInstance().convertDateToMySql(getAvailableTo()) + "' ");
    }
    
    if(!"".equals(getStartDate()) && columnCounter==0){
    queryStringBuffer.append(" CreatedDate >= '"+dateUtil.strToTimeStampObj(getStartDate())+"' ");
    columnCounter ++;
    }else if(!"".equals(getStartDate()) && columnCounter!=0){
    queryStringBuffer.append("AND CreatedDate >='"+dateUtil.strToTimeStampObj(getStartDate())+"' ");
    }
    
    if(!"".equals(getEndDate()) && columnCounter==0){
    queryStringBuffer.append(" CreatedDate <='"+dateUtil.strToTimeStampObj(getEndDate())+"' ");
    columnCounter ++;
    } else if(!"".equals(getEndDate()) && columnCounter!=0){
    queryStringBuffer.append("AND CreatedDate <='"+dateUtil.strToTimeStampObj(getEndDate())+"' ");
    }
    
    if(!"".equals(getYrsExp())){
    queryStringBuffer.append(" and tblRecConsultant.Id=tblRecSkills.EmpId ");
    }
    //New Add
    if(!"All".equals(getOrg()) && columnCounter==0){
    queryStringBuffer.append(" tblRecConsultant.OrgId = '"+getOrg()+"' ");
    columnCounter ++;
    }
    else if(!"All".equals(getOrg()) && columnCounter!=0)
    {
    queryStringBuffer.append("AND tblRecConsultant.OrgId = '"+getOrg()+"' "); 
    }
    
    //Added newly Ajay for Job title 
    
    if(!"".equals(getTitleTypeId()) && columnCounter==0){
    //tempName = getFiName();
    if((getTitleTypeId().indexOf("*") == -1)&&(getTitleTypeId().indexOf("%") == -1)) setTitleTypeId(getTitleTypeId()+"*");
    setTitleTypeId(getTitleTypeId().replace("*","%"));
    queryStringBuffer.append("TitleTypeId LIKE '" + getTitleTypeId() + "' ");
    columnCounter ++;
    }else if(!"".equals(getTitleTypeId()) && columnCounter!=0){
    //tempName = getFiName();
    if((getTitleTypeId().indexOf("*") == -1)&&(getTitleTypeId().indexOf("%") == -1)) setTitleTypeId(getTitleTypeId()+"*");
    setTitleTypeId(getTitleTypeId().replace("*","%"));
    queryStringBuffer.append("AND TitleTypeId LIKE '" + getTitleTypeId() + "' ");
    }
    
    if(!"".equals(getAvailable()) && columnCounter==0){
    //tempName = getFiName();
    if((getAvailable().indexOf("*") == -1)&&(getAvailable().indexOf("%") == -1)) setAvailable(getAvailable()+"*");
    setAvailable(getAvailable().replace("*","%"));
    queryStringBuffer.append("Available LIKE '" + getAvailable() + "' ");
    columnCounter ++;
    }else if(!"".equals(getAvailable()) && columnCounter!=0){
    //tempName = getFiName();
    if((getAvailable().indexOf("*") == -1)&&(getAvailable().indexOf("%") == -1)) setAvailable(getAvailable()+"*");
    setAvailable(getAvailable().replace("*","%"));
    queryStringBuffer.append("AND Available LIKE '" + getAvailable() + "' ");
    }
    
    
    queryStringBuffer.append(" ORDER BY modifiedDate DESC ");
    
    //  System.out.println("Query----->"+queryStringBuffer.toString());
    
    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QUERY_STRING) != null){
    httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QUERY_STRING);
    }
    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QUERY_STRING,queryStringBuffer.toString());
    }
    
    //     System.out.println("getLocation getWorkAuthorization getCreatedBy"+getLocation()+getWorkAuthorization()+getCreatedBy());
    //Added for BACK TO lIST CONSULTANT
    
    httpServletRequest.getSession(false).setAttribute("isSearch","true");
    httpSession = httpServletRequest.getSession(false);
    httpSession.setAttribute("search_Query_BackToList",queryStringBuffer);
    httpSession.setAttribute("search_Value","true");
    
    //aDD END
    // System.out.println("Query----->"+queryStringBuffer.toString());
    
    // setLocation(getLocation().replace("%",""));
    
    //  setLocation(getLocation());
    // System.out.println("Location--->"+getLocation());
    
    setWorkAuthorizationList(getWorkAuthorizationList());
    // System.out.println("getWorkAuthorization--->"+getWorkAuthorization());
    setLocationList(getLocationList());
    setAssignedTo(getAssignedTo().replace("%",""));
    //System.out.println("getTechnology-->"+getTechnology());
    //setTechnology(getTechnology().replace("%",""));
    
    setAvailable(getAvailable().replace("%",""));
    setCurrentAction("searchConsultant");
    HibernateDataProvider hibernateDataProvider = HibernateDataProvider.getInstance();
    datasourceDataProvider = DataSourceDataProvider.getInstance();
    //setPracticeList(hibernateDataProvider.getPractice(ApplicationConstants.PRACTICE_OPTION));
    setStatesList(hibernateDataProvider.getStatesList(ApplicationConstants.STATES_OPTIONS));
    //  setAssignedMembers(datasourceDataProvider.getEmployeeNamesByUserId("Recruiting"));
    setAssignedMembers(datasourceDataProvider.getEmployeeNamesByRecruitingRole());
    resultType = SUCCESS;
    
    }catch (Exception ex){
    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
    ex.printStackTrace();
    //httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
    resultType="exception";
    throw new ServiceLocatorException(ex);
    }
    }
    }
    
    // System.out.println("queryStringBuffer.toString()"+queryStringBuffer.toString());
    
    return resultType;
    } */
    public String getConsultantList() throws ServiceLocatorException {
        resultType = LOGIN;
        StringBuffer queryStringBuffer = null;
        DateUtility dateUtil = new DateUtility();
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";

            if (AuthorizationManager.getInstance().isAuthorizedUser("DO_SKILL_SEARCH", userRoleId)) {
                try {
                    String workAuthorizationList = "";
                    String states = "";
                    defaultDataProvider = DefaultDataProvider.getInstance();
                    //  NEW 
                    setOrgMap(defaultDataProvider.getOrgMap(ApplicationConstants.ORG_MAP));
                    setExpMap(defaultDataProvider.getExpMap(ApplicationConstants.EXP_MAP));
                    if (getSubmitFrom() == null) {
                        queryStringBuffer = new StringBuffer();

                        if (!"".equals(getYrsExp())) {
//                             queryStringBuffer.append("SELECT createdBy,tblRecConsultant.Id,FName," +
//                             "CONCAT(TRIM(FName),' ', TRIM(MName),' ',TRIM(LName)) AS NAME, Email2,CellPhoneNo," +
//                             "tblRecConsultant.SkillSet,ModifiedDate,LastContactDate FROM tblRecConsultant,tblRecSkills ");
                             /*queryStringBuffer.append("SELECT createdBy,tblRecConsultant.Id,FName," +
                            "CONCAT(TRIM(FName),' ', TRIM(MName),' ',TRIM(LName)) AS NAME,TitleTypeId, Email2,CellPhoneNo," +
                            "tblRecConsultant.SkillSet,ModifiedDate,Country,LastContactDate,ModifiedBy,CreatedDate,ModifiedDate FROM tblRecConsultant,tblRecSkills ");*/
                            queryStringBuffer.append("SELECT createdBy,tblRecConsultant.Id,FName,"
                                    + "CONCAT(TRIM(FName),' ', TRIM(MName),' ',TRIM(LName)) AS NAME,TitleTypeId, Email2,CellPhoneNo,"
                                    + "tblRecConsultant.SkillSet,ModifiedDate,Country,(CASE WHEN (`tblRecConsultant`.`LastContactDate` = '1950-01-31') THEN '' ELSE DATE_FORMAT(`tblRecConsultant`.`LastContactDate`,'%m-%d-%Y') END) AS `LastContactDate`,ModifiedBy,CreatedDate,ModifiedDate FROM tblRecConsultant,tblRecSkills ");
                        } else {
//                            queryStringBuffer.append("SELECT createdBy,Id,FName,CONCAT(trim(FName),' ', trim(MName),' ',trim(LName)) as Name, Email2," +
//                            "CellPhoneNo,SkillSet,ModifiedDate,LastContactDate FROM tblRecConsultant");
                           /* queryStringBuffer.append("SELECT createdBy,Id,FName,CONCAT(trim(FName),' ', trim(MName),' ',trim(LName)) as Name,TitleTypeId, Email2," +
                            "CellPhoneNo,SkillSet,ModifiedDate,Country,LastContactDate,ModifiedBy,CreatedDate,ModifiedDate FROM tblRecConsultant");*/
                            queryStringBuffer.append("SELECT createdBy,Id,FName,CONCAT(trim(FName),' ', trim(MName),' ',trim(LName)) as Name,TitleTypeId, Email2,"
                                    + "CellPhoneNo,SkillSet,ModifiedDate,Country,(CASE WHEN (`tblRecConsultant`.`LastContactDate` = '1950-01-31') THEN '' ELSE DATE_FORMAT(`tblRecConsultant`.`LastContactDate`,'%m-%d-%Y') END) AS `LastContactDate`,ModifiedBy,CreatedDate,ModifiedDate FROM tblRecConsultant");
                        }


                        if (null == getFiName()) {
                            setFiName("");
                        }
                        if (null == getSkills()) {
                            setSkills("");
                        }
                        if (null == getComments()) {
                            setComments("");
                        }
                        //if(null == getLocation()) setLocation("");
                        //  if(null == getLocation()) setLocation("");
                        if (null == getEmail1()) {
                            setEmail1("");
                        }
                        // if(null == getWorkAuthorization()) setWorkAuthorization("");
                        if (null == getTechnology()) {
                            setTechnology("");
                        }
                        //Added by Ajay
                        if (null == getAvailable()) {
                            setAvailable("");
                        }
                        //  if(null == getWorkAuthorization()) setWorkAuthorization("");
                        if (null == getSource()) {
                            setSource("");
                        }
                        if (null == getAssignedTo()) {
                            setAssignedTo("");
                        }
                        if (null == getAvailableFrom()) {
                        }
                        if (null == getTitleTypeId()) {
                            setTitleTypeId("");
                        }

                        //   if("-1".equals(getAssignedTo())) setAssignedTo("");


                        if ((!"".equals(getFiName()))
                                || (!"".equals(getSkills()))
                                || (!getLocationList().isEmpty())
                                || (!"".equals(getComments()))
                                || (!"".equals(getEmail1()))
                                || (!getWorkAuthorizationList().isEmpty())
                                || (!"".equals(getTechnology()))
                                || (!"".equals(getAvailable()))
                                || (!"".equals(getSource()))
                                || (!"".equals(getYrsExp()))
                                || (!"".equals(getAssignedTo()))
                                || (!"".equals(getTitleTypeId()))
                                || (!"".equals(getStartDate())
                                || (getAvailableFrom() != null)
                                || (!"All".equals(getOrg()))
                                || (!"".equals(getEndDate())) || (!"".equals(getActivityFromDate())) || (!"".equals(getActivityToDate())))) {
                            queryStringBuffer.append(" WHERE ");

                        }

                        int columnCounter = 0;

                        if (!"".equals(getFiName()) && columnCounter == 0) {
                            //tempName = getFiName();
                            if ((getFiName().indexOf("*") == -1) && (getFiName().indexOf("%") == -1)) {
                                setFiName(getFiName() + "*");
                            }
                            setFiName(getFiName().replace("*", "%"));
                            queryStringBuffer.append("(FName LIKE '" + getFiName() + "' or LName LIKE '" + getFiName() + "' or MName LIKE '" + getFiName() + "') ");
                            columnCounter++;
                        } else if (!"".equals(getFiName()) && columnCounter != 0) {
                            //tempName = getFiName();
                            if ((getFiName().indexOf("*") == -1) && (getFiName().indexOf("%") == -1)) {
                                setFiName(getFiName() + "*");
                            }
                            setFiName(getFiName().replace("*", "%"));
                            queryStringBuffer.append("AND (FName LIKE '" + getFiName() + "' or LName LIKE '" + getFiName() + "' or MName LIKE '" + getFiName() + "') ");
                        }

                        if (!"".equals(getSkills()) && columnCounter == 0) {
                            //tempName = getFiName();
                            // if((getSkills().indexOf("*") == -1)&&(getSkills().indexOf("%") == -1)) setSkills(getSkills()+"*");
                            setSkills(getSkills().replace("%", "*"));
                            queryStringBuffer.append("MATCH(tblRecConsultant.SkillSet) AGAINST ('" + getSkills() + "' IN BOOLEAN MODE) ");
                            columnCounter++;
                        } else if (!"".equals(getSkills()) && columnCounter != 0) {
                            //tempName = getFiName();
                            //  if((getSkills().indexOf("*") == -1)&&(getSkills().indexOf("%") == -1)) setSkills(getSkills()+"*");
                            setSkills(getSkills().replace("%", "*"));
                            queryStringBuffer.append("AND MATCH(tblRecConsultant.SkillSet) AGAINST ('" + getSkills() + "' IN BOOLEAN MODE) ");
                        }



                        if (!getLocationList().isEmpty() && columnCounter == 0) {

                            for (int i = 0; i < getLocationList().size(); i++) {
                                states += "'" + getLocationList().get(i).toString().trim() + "',";
                            }
                            states = states.substring(0, states.length() - 1);
                            queryStringBuffer.append(" Country IN (" + states + ") ");
                            columnCounter++;
                        } else if (!getLocationList().isEmpty() && columnCounter != 0) {

                            for (int i = 0; i < getLocationList().size(); i++) {
                                states += "'" + getLocationList().get(i).toString().trim() + "',";
                            }
                            states = states.substring(0, states.length() - 1);
                            queryStringBuffer.append("AND Country IN (" + states + ") ");

                        }


                        if (!getWorkAuthorizationList().isEmpty() && columnCounter == 0) {

                            for (int i = 0; i < getWorkAuthorizationList().size(); i++) {
                                workAuthorizationList += "'" + getWorkAuthorizationList().get(i).toString().trim() + "',";
                            }
                            workAuthorizationList = workAuthorizationList.substring(0, workAuthorizationList.length() - 1);
                            //   System.out.println("staates -- > "+ states);
                            queryStringBuffer.append(" WorkAuthorization IN (" + workAuthorizationList + ") ");
                            columnCounter++;
                        } else if (!getWorkAuthorizationList().isEmpty() && columnCounter != 0) {

                            for (int i = 0; i < getWorkAuthorizationList().size(); i++) {
                                workAuthorizationList += "'" + getWorkAuthorizationList().get(i).toString().trim() + "',";

                            }
                            workAuthorizationList = workAuthorizationList.substring(0, workAuthorizationList.length() - 1);
                            queryStringBuffer.append("AND WorkAuthorization IN (" + workAuthorizationList + ") ");

                        }




                        if (!"".equals(getComments()) && columnCounter == 0) {
                            //tempName = getFiName();
                            if ((getComments().indexOf("*") == -1) && (getComments().indexOf("%") == -1)) {
                                setComments(getComments() + "*");
                            }
                            setComments(getComments().replace("*", "%"));
                            queryStringBuffer.append("MATCH(Comments) AGAINST ('" + getComments() + "' IN BOOLEAN MODE) ");
                            columnCounter++;
                        } else if (!"".equals(getComments()) && columnCounter != 0) {
                            //tempName = getFiName();
                            if ((getComments().indexOf("*") == -1) && (getComments().indexOf("%") == -1)) {
                                setComments(getComments() + "*");
                            }
                            setComments(getComments().replace("*", "%"));
                            queryStringBuffer.append("AND MATCH(Comments) AGAINST ('" + getComments() + "' IN BOOLEAN MODE) ");
                        }

                        if (!"".equals(getEmail1()) && columnCounter == 0) {
                            //tempName = getFiName();
                            if ((getEmail1().indexOf("*") == -1) && (getEmail1().indexOf("%") == -1)) {
                                setEmail1(getEmail1() + "*");
                            }
                            setEmail1(getEmail1().replace("*", "%"));
                            queryStringBuffer.append("Email2 LIKE '" + getEmail1() + "' ");
                            columnCounter++;
                        } else if (!"".equals(getEmail1()) && columnCounter != 0) {
                            //tempName = getFiName();
                            if ((getEmail1().indexOf("*") == -1) && (getEmail1().indexOf("%") == -1)) {
                                setEmail1(getEmail1() + "*");
                            }
                            setEmail1(getEmail1().replace("*", "%"));
                            queryStringBuffer.append("AND Email2 LIKE '" + getEmail1() + "' ");
                        }


                        if (!"".equals(getSource()) && columnCounter == 0) {
                            //tempName = getFiName();
                            if ((getSource().indexOf("*") == -1) && (getSource().indexOf("%") == -1)) {
                                setSource(getSource() + "*");
                            }
                            setSource(getSource().replace("*", "%"));
                            queryStringBuffer.append("Source LIKE '" + getSource() + "' ");
                            columnCounter++;
                        } else if (!"".equals(getSource()) && columnCounter != 0) {
                            //tempName = getFiName();
                            if ((getSource().indexOf("*") == -1) && (getSource().indexOf("%") == -1)) {
                                setSource(getSource() + "*");
                            }
                            setSource(getSource().replace("*", "%"));
                            queryStringBuffer.append("AND Source LIKE '" + getSource() + "' ");
                        }
                        if (!"".equals(getAssignedTo()) && columnCounter == 0) {
                            //tempName = getFiName();
                            if ((getAssignedTo().indexOf("*") == -1) && (getAssignedTo().indexOf("%") == -1)) {
                                setAssignedTo(getAssignedTo() + "*");
                            }
                            setAssignedTo(getAssignedTo().replace("*", "%"));
                            queryStringBuffer.append("CreatedBy LIKE '" + getAssignedTo() + "' ");
                            columnCounter++;
                        } else if (!"".equals(getAssignedTo()) && columnCounter != 0) {
                            //tempName = getFiName();
                            if ((getAssignedTo().indexOf("*") == -1) && (getAssignedTo().indexOf("%") == -1)) {
                                setAssignedTo(getAssignedTo() + "*");
                            }
                            setAssignedTo(getAssignedTo().replace("*", "%"));
                            queryStringBuffer.append("AND CreatedBy LIKE '" + getAssignedTo() + "' ");
                        }

                        if (!"".equals(getYrsExp()) && columnCounter == 0) {
                            //tempName = getFiName();
                            if ((getYrsExp().indexOf("*") == -1) && (getYrsExp().indexOf("%") == -1)) {
                                setYrsExp(getYrsExp() + "*");
                            }
                            setYrsExp(getYrsExp().replace("*", "%"));
                            queryStringBuffer.append("YearsOfExperience LIKE '" + getYrsExp() + "' ");
                            columnCounter++;
                        } else if (!"".equals(getYrsExp()) && columnCounter != 0) {
                            //tempName = getFiName();
                            if ((getYrsExp().indexOf("*") == -1) && (getYrsExp().indexOf("%") == -1)) {
                                setYrsExp(getYrsExp() + "*");
                            }
                            setYrsExp(getYrsExp().replace("*", "%"));
                            queryStringBuffer.append("AND YearsOfExperience LIKE '" + getYrsExp() + "' ");
                        }

                        if (getAvailableFrom() != null && columnCounter == 0) {
                            queryStringBuffer.append(" AvailableFrom >= '" + DateUtility.getInstance().convertDateToMySql(getAvailableFrom()) + "' ");
                            columnCounter++;
                        } else if (getAvailableFrom() != null && columnCounter != 0) {
                            queryStringBuffer.append(" AND AvailableFrom >= '" + DateUtility.getInstance().convertDateToMySql(getAvailableFrom()) + "' ");
                        }

                        if (getAvailableTo() != null && columnCounter == 0) {
                            queryStringBuffer.append(" AvailableFrom <= '" + DateUtility.getInstance().convertDateToMySql(getAvailableTo()) + "' ");
                            columnCounter++;
                        } else if (getAvailableTo() != null && columnCounter != 0) {
                            queryStringBuffer.append(" AND AvailableFrom <= '" + DateUtility.getInstance().convertDateToMySql(getAvailableTo()) + "' ");
                        }

                        if (!"".equals(getStartDate()) && columnCounter == 0) {
                            queryStringBuffer.append(" CreatedDate >= '" + dateUtil.strToTimeStampObj(getStartDate()) + "' ");
                            columnCounter++;
                        } else if (!"".equals(getStartDate()) && columnCounter != 0) {
                            queryStringBuffer.append("AND CreatedDate >='" + dateUtil.strToTimeStampObj(getStartDate()) + "' ");
                        }

                        if (!"".equals(getEndDate()) && columnCounter == 0) {
                            queryStringBuffer.append(" CreatedDate <='" + dateUtil.strToTimeStampObj(getEndDate()) + "' ");
                            columnCounter++;
                        } else if (!"".equals(getEndDate()) && columnCounter != 0) {
                            queryStringBuffer.append("AND CreatedDate <='" + dateUtil.strToTimeStampObj(getEndDate()) + "' ");
                        }
                        if (!"".equals(getActivityFromDate()) && columnCounter == 0) {
                            setActivityFromDate(getActivityFromDate());
                            queryStringBuffer.append(" LastContactDate >= '" + dateUtil.strToTimeStampObj(getActivityFromDate()) + "' ");
                            columnCounter++;
                        } else if (!"".equals(getActivityFromDate()) && columnCounter != 0) {
                            setActivityFromDate(getActivityFromDate());
                            queryStringBuffer.append("AND LastContactDate >='" + dateUtil.strToTimeStampObj(getActivityFromDate()) + "' ");
                        }

                        if (!"".equals(getActivityToDate()) && columnCounter == 0) {
                            setActivityToDate(getActivityToDate());
                            queryStringBuffer.append(" LastContactDate <='" + dateUtil.strToTimeStampObj(getActivityToDate()) + "' ");
                            columnCounter++;
                        } else if (!"".equals(getActivityToDate()) && columnCounter != 0) {
                            setActivityToDate(getActivityToDate());
                            queryStringBuffer.append("AND LastContactDate <='" + dateUtil.strToTimeStampObj(getActivityToDate()) + "' ");
                        }

                        if (!"".equals(getYrsExp())) {
                            queryStringBuffer.append(" and tblRecConsultant.Id=tblRecSkills.EmpId ");
                        }
                        //New Add
                        if (!"All".equals(getOrg()) && columnCounter == 0) {
                            queryStringBuffer.append(" tblRecConsultant.OrgId = '" + getOrg() + "' ");
                            columnCounter++;
                        } else if (!"All".equals(getOrg()) && columnCounter != 0) {
                            queryStringBuffer.append("AND tblRecConsultant.OrgId = '" + getOrg() + "' ");
                        }

                        //Added newly Ajay for Job title 

                        if (!"".equals(getTitleTypeId()) && columnCounter == 0) {
                            //tempName = getFiName();
                            if ((getTitleTypeId().indexOf("*") == -1) && (getTitleTypeId().indexOf("%") == -1)) {
                                setTitleTypeId(getTitleTypeId() + "*");
                            }
                            setTitleTypeId(getTitleTypeId().replace("*", "%"));
                            queryStringBuffer.append("TitleTypeId LIKE '" + getTitleTypeId() + "' ");
                            columnCounter++;
                        } else if (!"".equals(getTitleTypeId()) && columnCounter != 0) {
                            //tempName = getFiName();
                            if ((getTitleTypeId().indexOf("*") == -1) && (getTitleTypeId().indexOf("%") == -1)) {
                                setTitleTypeId(getTitleTypeId() + "*");
                            }
                            setTitleTypeId(getTitleTypeId().replace("*", "%"));
                            queryStringBuffer.append("AND TitleTypeId LIKE '" + getTitleTypeId() + "' ");
                        }

                        if (!"".equals(getAvailable()) && columnCounter == 0) {
                            //tempName = getFiName();
                            if ((getAvailable().indexOf("*") == -1) && (getAvailable().indexOf("%") == -1)) {
                                setAvailable(getAvailable() + "*");
                            }
                            setAvailable(getAvailable().replace("*", "%"));
                            queryStringBuffer.append("Available LIKE '" + getAvailable() + "' ");
                            columnCounter++;
                        } else if (!"".equals(getAvailable()) && columnCounter != 0) {
                            //tempName = getFiName();
                            if ((getAvailable().indexOf("*") == -1) && (getAvailable().indexOf("%") == -1)) {
                                setAvailable(getAvailable() + "*");
                            }
                            setAvailable(getAvailable().replace("*", "%"));
                            queryStringBuffer.append("AND Available LIKE '" + getAvailable() + "' ");
                        }


                        queryStringBuffer.append(" ORDER BY modifiedDate DESC ");

                        //  System.out.println("Query----->"+queryStringBuffer.toString());

                        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QUERY_STRING) != null) {
                            httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QUERY_STRING);
                        }
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QUERY_STRING, queryStringBuffer.toString());
                    }

                    //     System.out.println("getLocation getWorkAuthorization getCreatedBy"+getLocation()+getWorkAuthorization()+getCreatedBy());
                    //Added for BACK TO lIST CONSULTANT

                    httpServletRequest.getSession(false).setAttribute("isSearch", "true");
                    httpSession = httpServletRequest.getSession(false);
                    httpSession.setAttribute("search_Query_BackToList", queryStringBuffer);
                    httpSession.setAttribute("search_Value", "true");

                    //aDD END
                    // System.out.println("Query----->"+queryStringBuffer.toString());

                    // setLocation(getLocation().replace("%",""));

                    //  setLocation(getLocation());
                    // System.out.println("Location--->"+getLocation());


                    // System.out.println("activitydates-->"+getActivityFromDate());
                    setWorkAuthorizationList(getWorkAuthorizationList());
                    // System.out.println("getWorkAuthorization--->"+getWorkAuthorization());
                    setLocationList(getLocationList());
                    setAssignedTo(getAssignedTo().replace("%", ""));
                    //System.out.println("getTechnology-->"+getTechnology());
                    //setTechnology(getTechnology().replace("%",""));

                    setAvailable(getAvailable().replace("%", ""));
                    setCurrentAction("searchConsultant");
                    HibernateDataProvider hibernateDataProvider = HibernateDataProvider.getInstance();
                    datasourceDataProvider = DataSourceDataProvider.getInstance();
                    //setPracticeList(hibernateDataProvider.getPractice(ApplicationConstants.PRACTICE_OPTION));
                    setStatesList(hibernateDataProvider.getStatesList(ApplicationConstants.STATES_OPTIONS));
                    //  setAssignedMembers(datasourceDataProvider.getEmployeeNamesByUserId("Recruiting"));
                    setAssignedMembers(datasourceDataProvider.getEmployeeNamesByRecruitingRole());
                    resultType = SUCCESS;

                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    ex.printStackTrace();
                    //httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType = "exception";
                    throw new ServiceLocatorException(ex);
                }
            }
        }

        // System.out.println("queryStringBuffer.toString()"+queryStringBuffer.toString());

        return resultType;
    }

    public String getCrmBackToList() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            // userRoleName= httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("GET_CRM_BACKLIST", userRoleId)) {
                try {

                    defaultDataProvider = DefaultDataProvider.getInstance();
                    //  NEW 
                    setOrgMap(defaultDataProvider.getOrgMap(ApplicationConstants.ORG_MAP));
                    setExpMap(defaultDataProvider.getExpMap(ApplicationConstants.EXP_MAP));
                    //String actionName = (String)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ACC_SEARCH_ACTION);
                    HibernateDataProvider hibernateDataProvider = HibernateDataProvider.getInstance();
                    datasourceDataProvider = DataSourceDataProvider.getInstance();
                    //setPracticeList(hibernateDataProvider.getPractice(ApplicationConstants.PRACTICE_OPTION));
                    setStatesList(hibernateDataProvider.getStatesList(ApplicationConstants.STATES_OPTIONS));
                    //  setAssignedMembers(datasourceDataProvider.getEmployeeNamesByUserId("Recruiting"));
                    setAssignedMembers(datasourceDataProvider.getEmployeeNamesByRecruitingRole());
                    setCurrentAction("searchConsultant");
                    resultType = SUCCESS;

                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        prepare();
        return resultType;
    }
    //Methods Added By Ajay Tummapala for Tech Reviews
//       public String doReviewConsultant() throws Exception {
//        resultType = LOGIN;
//        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
//            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
//            resultType = "accessFailed";
//            
//            if(AuthorizationManager.getInstance().isAuthorizedUser("GET_DETAILS_RECRUITMENT",userRoleId)){
//                try{
//                    /**
//                     *   Populates user required options to the Screens depending on the  options.
//                     *
//                     *   @param   Taking  getConsultantId() method  from  ApplicationConstants
//                     *
//                     *   @return    The CurrentConsultant returned  depends on the getConsultantId() method.
//                     *
//                     */
//                  
//                    ServiceLocator.getConsultantService().getReviewConsultantDetails(this,getConsultantId());
//                    
//                    /** Calling lookUpDate() method to populate select components */
//                   // lookUpData();
//                   // System.out.println("hello ");
//                    resultType = SUCCESS;
//                    
//                }catch (Exception ex){
//                    System.out.println(ex);
//                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
//                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
//                    resultType =  ERROR;
//                }
//                
//            }//END-Authorization Checking
//        }//Close Session Checking
//        return resultType;
//    }

    public String doReviewConsultant() throws Exception {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            // System.out.println("doReviewConsultant");
            if (AuthorizationManager.getInstance().isAuthorizedUser("GET_DETAILS_RECRUITMENT", userRoleId)) {
                try {
                    /**
                     *   Populates user required options to the Screens depending on the  options.
                     *
                     *   @param   Taking  getConsultantId() method  from  ApplicationConstants
                     *
                     *   @return    The CurrentConsultant returned  depends on the getConsultantId() method.
                     *
                     */
                    setConsultantId(getConsultantId());
                    setRecConsultantId(getRecConsultantId());
                    setRequirementId(getRequirementId());
                    setRequirementAdminFlag(getRequirementAdminFlag());
                    setJobTitle(DataSourceDataProvider.getInstance().getRequirementTitle(String.valueOf(getRequirementId())));
                    setPreSalesMap(DataSourceDataProvider.getInstance().getPresalesMap());
                    //   String assignedTo3=httpServletRequest.getParameter("assignedTo3");
                    //  String assignedTo2=httpServletRequest.getParameter("assignedTo2");
                    Map assignedMap = DataSourceDataProvider.getInstance().getRequirementAssignToMap(String.valueOf(getRequirementId()));
                    String assignedTo2 = (String) assignedMap.get("PreSales1");
                    String assignedTo3 = (String) assignedMap.get("PreSales2");
                    //      System.out.println("doReviewConsultant 1");
                    if (!assignedTo2.equals("")) {
                        setPreSales1(DataSourceDataProvider.getInstance().getEmpIdByName(assignedTo2));
                    } else {
                        setPreSales1(0);
                    }
                    if (!assignedTo3.equals("")) {
                        setPreSales2(DataSourceDataProvider.getInstance().getEmpIdByName(assignedTo3));
                    } else {
                        setPreSales2(0);
                    }
                    //  System.out.println(getPreSales1() + " value " + getPreSales2());
                    ServiceLocator.getConsultantService().getReviewConsultantDetails(this, getConsultantId());
                    //   System.out.println("after");
                    /** Calling lookUpDate() method to populate select components */
                    // lookUpData();
                    // System.out.println("hello ");
                    resultType = SUCCESS;

                } catch (Exception ex) {
                    //   System.out.println("test exception");
                    System.out.println(ex);
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }

            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }

    public String doReferToReview() throws Exception {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";

            if (AuthorizationManager.getInstance().isAuthorizedUser("GET_DETAILS_RECRUITMENT", userRoleId)) {
                try {
                    String userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                    /**
                     *   Populates user required options to the Screens depending on the  options.
                     *
                     *   @param   Taking  getConsultantId() method  from  ApplicationConstants
                     *
                     *   @return    The CurrentConsultant returned  depends on the getConsultantId() method.
                     *
                     */
                    setModifiedBy(userId);
                    setConsultantId(getConsultantId());
                    setRecConsultantId(getRecConsultantId());
                    setRequirementId(getRequirementId());
                    setRequirementAdminFlag(getRequirementAdminFlag());
                    String title = "";
                    setTitle(title);
                    // StringTokenizer st =  DataSourceDataProvider.getInstance().getReferredLoginIdsForGroups(getReferTo());
                    //int maxId=ServiceLocator.getConsultantService().doReferToReview(this,userId,st);
                    String[] skills = DataSourceDataProvider.getInstance().getSkillSet(getRequirementId());
                    int count=0;
                    for (int i = 0; i < skills.length; i++) {
                        if (skills[i].length() > 35) {
                            count=1;
                        }
                    }
                    if(count==0){
                    int maxId = ServiceLocator.getConsultantService().doReferToReview(this, userId);

                    //  System.out.println("After insertion id-->"+maxId+"Consultent id -->"+getConsultantId());

                    if (maxId > 0) {
                        // resultType = SUCCESS;
                        //send mail with the maxId
                        if (Properties.getProperty("Mail.Flag").equals("1")) {
                            //  StringTokenizer st1 =  DataSourceDataProvider.getInstance().getReferredLoginIdsForGroups(getReferTo());
//                          while(st1.hasMoreTokens())
                            //                       {
//                               String email=st1.nextToken()+"@miraclesoft.com";
                            //    int reviewId=DataSourceDataProvider.getInstance().getReviewIdbyEmail(email,getConsultantId());

                            int reviewId = DataSourceDataProvider.getInstance().getReviewIdbyEmail(getPreSales1(), getPreSales2(), getConsultantId());

                            //  System.out.println("reviewId-->"+reviewId+"--->"+email);
                            // if(Properties.getProperty("Mail.Flag").equals("1")) { 

                            // MailManager.sendConsultantDetailsForTechReviews(reviewId,getConsultantId(),email,getTechName());
                            MailManager.sendConsultantDetailsForTechReviews(reviewId, getConsultantId(), getPreSales1(), getPreSales2(), getTechName(), getStatus(), getRequirementId(), getRecConsultantId(), getTitleTypeId(), getScheduledDate(), getScheduledTime(), getTimeFormat(), getZone());

                            //  }}
                        }
                        setResultMessage("<font color=\"green\" size=\"1.5\"> Consultant details successfully forward to Presales persons for tech review!</font>");

                    } else {
                        // resultType = "error";
                        setResultMessage("<font color=\"red\" size=\"1.5\">Please Try Again</font>");
                    }
                    }
                    else{
                         setResultMessage("<font color=\"red\" style=\"font-size: 12px\">One of the skill in primary (or) Secondary skill is more than 35 characters.Please redefine skill set in primary (or) secondary skills of requirement.<br>Skill set values should be separated by comma.</font>");
                         httpServletRequest.getSession(false).setAttribute("updateLink", "1");
                    }
                    //System.out.println("getResultMessage--->"+getResultMessage());
                    httpServletRequest.setAttribute("resultMessage", getResultMessage());
                    /** Calling lookUpDate() method to populate select components */
                    // lookUpData();
                    //  System.out.println("hello ");
                    resultType = SUCCESS;

                } catch (Exception ex) {
                    System.out.println(ex);
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }

            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }

    public String doReviewTechnical() throws Exception {
        //System.out.println("doReviewTechnical-->");
        resultType = LOGIN;
        // if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
        if ((httpServletRequest.getSession(false) != null) && (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null)) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";

            if (AuthorizationManager.getInstance().isAuthorizedUser("GET_TECH_REVIEW_SUBMIT", userRoleId)) {
                //String status1 = DataSourceDataProvider.getInstance().getConsultantLinkStatus(getId());

                try {

                    setModifiedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                    setRequirementId(getRequirementId());
                    String empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
                    setEmpId(empId);
                    ServiceLocator.getConsultantService().doReviewTechnical(this);
                    //setStatus(getStatus());
                    if (getStatus().equals("denied")) {
                        resultType = "accessFailed";
                    } else {
                        resultType = SUCCESS;
                    }
                    /** Calling lookUpDate() method to populate select components */
                    // lookUpData();
                    // System.out.println("hello ");
                } catch (Exception ex) {
                    //System.out.println(ex);
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
                /* }
                else
                {
                httpServletRequest.setAttribute("resultMessage","Sorry! The link got expired.");
                resultType = INPUT;
                }*/

            }//END-Authorization Checking
        }//Close Session Checking
        else {
            if (httpServletRequest.getParameter("consultantId") != null) {
                String consultantId = httpServletRequest.getParameter("consultantId");
                String id = httpServletRequest.getParameter("id");
                String status = httpServletRequest.getParameter("status");
                int requirementId = Integer.parseInt(httpServletRequest.getParameter("requirementId"));
                int recConsultantId = Integer.parseInt(httpServletRequest.getParameter("recConsultantId"));

                httpServletRequest.setAttribute("consultantId", consultantId);
                httpServletRequest.setAttribute("id", id);
                setConsultantId(Integer.parseInt(httpServletRequest.getAttribute("consultantId").toString()));
                setId(Integer.parseInt(httpServletRequest.getAttribute("id").toString()));
                setStatus(status);
                setRequirementId(requirementId);
                setRecConsultantId(recConsultantId);
            }
        }
        return resultType;
    }

//    public String doAddTechincalReview() throws Exception {
//        resultType = LOGIN;
//       // System.out.println("uin methopd");
//        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
//            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
//           // resultType = "accessFailed";
//         //    System.out.println("uin methopd11");
//            if(AuthorizationManager.getInstance().isAuthorizedUser("GET_TECH_REVIEWS_RECRUITMENT",userRoleId)){
//        //String status1 = DataSourceDataProvider.getInstance().getConsultantLinkStatus(getId());
//         // System.out.println("uin methopd11-->"+status1);
//                loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
//                setModifiedBy(loginId);
//                try{
//           //         System.out.println("referto-->"+getId());
//                   StringTokenizer st =  DataSourceDataProvider.getInstance().getReferredLoginIdsForGroups(getReferTo());
//                   int maxId =ServiceLocator.getConsultantService().doAddTechincalReview(this,st);
//                 // System.out.println("After insertion id-->"+maxId+"Consultent id -->"+getConsultantId()+"ID-->"+getId()+"-----operationType-->"+getOperationType()+"---> techname-->"+getTechName());
//                   setStatus(getStatus());
//                    if(!(getOperationType().contains("Review")))
//                    {
//             //           System.out.println("in assined and forwarded");
//                         StringTokenizer st1 =  DataSourceDataProvider.getInstance().getReferredLoginIdsForGroups(getReferTo());
//                           while(st1.hasMoreTokens()){
//                               String email=st1.nextToken()+"@miraclesoft.com";
//               //               System.out.println("email0-->"+email);
//                               int reviewId=DataSourceDataProvider.getInstance().getReviewIdbyEmail(email,getConsultantId());
//                 //                System.out.println("reviewId12-->"+reviewId+"--->"+email);
//                                if(Properties.getProperty("Mail.Flag").equals("1")) { 
//                                    
//                       // MailManager.sendConsultantDetailsForTechReviews(reviewId,getConsultantId(),email,getTechName());
//                    }}
//                    }
//                    else if((getOperationType().equalsIgnoreCase("Review & Forward")))
//                    {
//                   //      System.out.println("in Review & Forward");
//                               String recruiterAndForwardedTo=DataSourceDataProvider.getInstance().getForwardedByEmail(getId(),getConsultantId());
//                     //          System.out.println("recruiterAndForwardedTo-->"+recruiterAndForwardedTo);
//                               String recruiter=recruiterAndForwardedTo.split(",")[0];
//                       //         System.out.println("recruiter-->"+recruiter);
//                               String forwardedToName=recruiterAndForwardedTo.split(",")[1];
//                         //        System.out.println("forwardedToName-->"+forwardedToName);
//                                  String forwardedTo=recruiterAndForwardedTo.split(",")[2];
//                           //      System.out.println("forwardedTo-->"+forwardedTo);
//                                  String comments = recruiterAndForwardedTo.split(",")[3];
//                                  String rating = recruiterAndForwardedTo.split(",")[4];
//                               String recruiterName=DataSourceDataProvider.getInstance().getFname_Lname(recruiter);
//                             //  System.out.println("recruiterName-->"+recruiterName);
//                               String consultantName=DataSourceDataProvider.getInstance().getConsultantName(getConsultantId());
//                               //System.out.println("consultantName-->"+consultantName);
//                               //System.out.println("techname"+getTechName());
//                               String recruiterEmail = recruiter+"@miraclesoft.com";
//                                if(Properties.getProperty("Mail.Flag").equals("1")) { 
//                                    
//                               //   MailManager.sendConsultantDetailsForTechReviews1(getId(),getConsultantId(),recruiterEmail,recruiterName,consultantName,forwardedToName,getOperationType(),forwardedTo,getReferTo(),comments,rating);
//                                }
//                                StringTokenizer st1 =  DataSourceDataProvider.getInstance().getReferredLoginIdsForGroups(getReferTo());
//                           while(st1.hasMoreTokens()){
//                               String email=st1.nextToken()+"@miraclesoft.com";
//                               //System.out.println("email01-->"+email);
//                               int reviewId=DataSourceDataProvider.getInstance().getReviewIdbyEmail(email,getConsultantId());
//                                 //System.out.println("maxreviewId123-->"+reviewId+"--->"+email);
//                                if(Properties.getProperty("Mail.Flag").equals("1")) { 
//                                    
//                     //   MailManager.sendConsultantDetailsForTechReviews(reviewId,getConsultantId(),email,getTechName());
//                    }}
//                           
//                        //MailManager.sendConsultantDetailsForTechReviews(reviewId,getConsultantId(),email,getTechName());
//                    }
//                       else
//                    {
//                            //System.out.println("in Reviewed by");
//                               String recruiterAndForwardedTo=DataSourceDataProvider.getInstance().getForwardedByEmail(getId(),getConsultantId());
//                             //   System.out.println("recruiterAndForwardedTo1-->"+recruiterAndForwardedTo);
//                               String recruiter=recruiterAndForwardedTo.split(",")[0];
//                             //   System.out.println("recruiter1-->"+recruiter);
//                               String forwardedToName=recruiterAndForwardedTo.split(",")[1];
//                            //   System.out.println("forwardedToName1-->"+forwardedToName);
//                                String forwardedTo=recruiterAndForwardedTo.split(",")[2];
//                             //    System.out.println("forwardedTo1-->"+forwardedTo);
//                                 String comments = recruiterAndForwardedTo.split(",")[3];
//                                  String rating = recruiterAndForwardedTo.split(",")[4];
//                               String recruiterName=DataSourceDataProvider.getInstance().getFname_Lname(recruiter);
//                              //  System.out.println("recruiterName1-->"+recruiterName);
//                               String consultantName=DataSourceDataProvider.getInstance().getConsultantName(getConsultantId());
//                                //System.out.println("consultantName1-->"+consultantName);
//                            //   System.out.println("reviewId1234-->"+recruiter+"techname"+getTechName());
//                               String recruiterEmail = recruiter+"@miraclesoft.com";
//                                if(Properties.getProperty("Mail.Flag").equals("1")) {
//                              //    MailManager.sendConsultantDetailsForTechReviews1(getId(),getConsultantId(),recruiterEmail,recruiterName,consultantName,forwardedToName,getOperationType(),forwardedTo,getReferTo(),comments,rating);
//                                }
//                  }
//                    /** Calling lookUpDate() method to populate select components */
//                   // lookUpData();
//                   // System.out.println("hello ");
//                    if(maxId>0){
//                          // System.out.println("hello 1");
//                           setResultMessage("The review is successfully submitted");
//                         httpServletRequest.setAttribute("resultMessage","The review is successfully submitted.<br>Thank you! For reviewing the consultant.");
//                        resultType = SUCCESS;
//                    }
//                    else{
//                         //  System.out.println("hello 2");
//                         httpServletRequest.setAttribute("resultMessage","Sorry! Network is down. Please try Again!.");
//                        resultType = INPUT;
//                    }
//                    
//                }catch (Exception ex){
//                    System.out.println(ex);
//                    ex.printStackTrace();
//                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
//                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
//                    resultType =  ERROR;
//                }
//              
//                
//            }//END-Authorization Checking
//        }//Close Session Checking
//        return resultType;
//    }
    public String doAddTechincalReview() throws Exception {
        resultType = LOGIN;
        // System.out.println("uin methopd");
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            // resultType = "accessFailed";
            //    System.out.println("uin methopd11");

            if (AuthorizationManager.getInstance().isAuthorizedUser("GET_TECH_REVIEW_SUBMIT", userRoleId)) {


                loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                setModifiedBy(loginId);
                try {
                    StringTokenizer st = DataSourceDataProvider.getInstance().getReferredLoginIdsForGroups(getReferTo());
                    int maxId = ServiceLocator.getConsultantService().doAddTechincalReview(this, st);

                    if (maxId > 0) {

                        // System.out.println("hello 1");
                        setResultMessage("The review is successfully submitted");
                        httpServletRequest.setAttribute("resultMessage", "The review is successfully submitted.<br>Thank you! For reviewing the consultant.");
                        resultType = SUCCESS;
                    } else {
                        //  System.out.println("hello 2");
                        httpServletRequest.setAttribute("resultMessage", "Sorry! Network is down. Please try Again!.");
                        resultType = INPUT;
                    }


                } catch (Exception ex) {
                    System.out.println(ex);
                    ex.printStackTrace();
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }


            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }

//     // New Field for RecDashbord
//   
//     public String getRecDashBoard() {
//         resultType = LOGIN;
//        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
//            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
//            String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
//            resultType = "accessFailed";
//            if(AuthorizationManager.getInstance().isAuthorizedUser("GET_DASH_BOARD",userRoleId)){
//                try{
//                   // System.out.println("In side GetRec>>>>>>>>>>>");
//                    datasourceDataProvider = DataSourceDataProvider.getInstance();
//                    setAssignedMembers(datasourceDataProvider.getEmployeeNamesByRecruitingRole());
//                    
//                    resultType = SUCCESS;
//                }catch (Exception ex){ 
//                    ex.printStackTrace();
//                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
//                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
//                    resultType =  ERROR;
//                }
//                
//            }//END-Authorization Checking
//        }//Close Session Checking
//        return resultType;
//    }
    /*New method for Recruitment Dashboard Details */
    public String getRecruitmentDashboard() {

        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";

            if (AuthorizationManager.getInstance().isAuthorizedUser("PREPARE_RECRUITMENT", userRoleId)) {
                try {

                    DateUtility dateUtility = new DateUtility();
                    setEndDate(dateUtility.CurrentMonthLastDate());
                    setStartDate(dateUtility.FirstDateOfCurrentMonth());
                    setDashBoardEndDate(dateUtility.CurrentMonthLastDate());
                    setDashBoardStartDate(dateUtility.FirstDateOfCurrentMonth());
                    setStartDateClose(dateUtility.FirstDateOfCurrentMonth());
                    setEndDateClose(dateUtility.CurrentMonthLastDate());
                    setStartDateSub(dateUtility.FirstDateOfCurrentMonth());
                    setEndDateSub(dateUtility.CurrentMonthLastDate());
                    setStartdateSummary(dateUtility.FirstDateOfCurrentMonth());
                    setEnddateSummary(dateUtility.CurrentMonthLastDate());
                    setStartDateSummaryGraph(dateUtility.FirstDateOfCurrentMonth());
                    setEndDateSummaryGraph(dateUtility.CurrentMonthLastDate());
                    datasourceDataProvider = DataSourceDataProvider.getInstance();

//               setTechLeadList(datasourceDataProvider.getTechLead());
//                    setClientMap(datasourceDataProvider.getClientMap());
//                    setTeamLeadMap(datasourceDataProvider.getTeamLeadMapByDept("Recruiting"));
//                    setRecruiterMap(datasourceDataProvider.getMyTeamMembers(getRecruiterLoginId(), "Recruiting"));

                    // setAssignedMembers(datasourceDataProvider.getEmployeeNamesByRecruitingRole());                 
                    if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CEO).toString().equalsIgnoreCase("yes")) {
                        setAssignedMembers(DataSourceDataProvider.getInstance().getRecruitmentEmployeeMap());
                    } else {
                        setAssignedMembers((Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
                    }
//                    Calendar cal = Calendar.getInstance();
//                    setStartDateReport("01/01/" + cal.get(Calendar.YEAR));
//                    setEndDateReport((cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DATE) + "/" + cal.get(Calendar.YEAR));

                    resultType = SUCCESS;

                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }
        }
        return resultType;
    }

    public String getExecutiveDashboardForRequirement() {

        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";

            if (AuthorizationManager.getInstance().isAuthorizedUser("PREPARE_RECRUITMENT", userRoleId)) {
                try {


                    datasourceDataProvider = DataSourceDataProvider.getInstance();

                    setTechLeadList(datasourceDataProvider.getTechLead());
                    setClientMap(datasourceDataProvider.getClientMap());
                    setTeamLeadMap(datasourceDataProvider.getTeamLeadMapByDept("Recruiting"));
                    setRecruiterMap(datasourceDataProvider.getMyTeamMembers(getRecruiterLoginId(), "Recruiting"));
                    setRecruiterMap(datasourceDataProvider.getRecruitmentEmployeeMap());
                    //getRecruitmentEmployeeMap
                    // System.out.println(getRecruiterMap().size());
                    // setAssignedMembers(datasourceDataProvider.getEmployeeNamesByRecruitingRole());                 
//                if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CEO).toString().equalsIgnoreCase("yes")){
//                   setAssignedMembers(DataSourceDataProvider.getInstance().getRecruitmentEmployeeMap());
//               }else{
//                setAssignedMembers((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));                 
//               }
                    Calendar cal = Calendar.getInstance();
                    setStartDateReport("01/01/" + cal.get(Calendar.YEAR));
                    setEndDateReport((cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DATE) + "/" + cal.get(Calendar.YEAR));

                    resultType = SUCCESS;

                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }
        }
        return resultType;
    }
    //new method for account merge

    public String getConsultantMerge() {
        setResultType(LOGIN);

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("PREPARE_RECRUITMENT", userRoleId)) {

                try {

                    setResultType(SUCCESS);
                } catch (Exception ex) {

                    ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    setResultType(ERROR);
                }

            }
        }
        return getResultType();
    }

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
                    // setTechReviews("1");
                    setStatus("Assigned");
                    StringBuffer queryStringBuffer = new StringBuffer();
                    int empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
                    queryStringBuffer.append("SELECT tblRecConsultant.Id AS conId,tblRecConsultantActivity.Id AS id,CONCAT(FName,' ',MName,' ',LName) AS NAME,tblRecConsultantActivity.Rateing,tblRecConsultantActivity.ForwardedDate,CASE WHEN (tblRecConsultantActivity.Rateing='Reviewed') THEN  tblRecConsultantActivity.LastModifiedDate ELSE ' ' END AS LastModifiedDate,tblRecConsultantActivity.STATUS,tblRecConsultantActivity.RequirementId,tblRecConsultantActivity.RecConsultantId AS RecConsultantId,tblRecRequirement.JobTitle FROM tblRecConsultantActivity LEFT OUTER JOIN tblRecConsultant ON (tblRecConsultantActivity.ConsultantId=tblRecConsultant.Id) JOIN tblRecRequirement ON(tblRecConsultantActivity.RequirementId=tblRecRequirement.Id) WHERE (PreSales1=" + empId + " OR Presales2=" + empId + ") ORDER BY  Rateing ASC,tblRecConsultantActivity.ForwardedDate DESC");
                    // System.out.println("queryStringBuffer.toString()--" + queryStringBuffer.toString());
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.EMP_TECH_REVIEWS, queryStringBuffer.toString());
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
                    StringBuffer queryStringBuffer = new StringBuffer();
                    //   String email = DataSourceDataProvider.getInstance().getEmailIdForEmployee(Integer.parseInt(empId));
                    queryStringBuffer.append("SELECT tblRecConsultant.Id as conId,tblRecConsultantActivity.Id AS id,CONCAT(FName,' ',MName,' ',LName) AS NAME,tblRecConsultantActivity.Rateing,"
                            + "tblRecConsultantActivity.ForwardedDate,CASE WHEN (tblRecConsultantActivity.Rateing='Reviewed') THEN  tblRecConsultantActivity.LastModifiedDate ELSE ' ' END AS LastModifiedDate,tblRecConsultantActivity.Status,tblRecConsultantActivity.RequirementId,tblRecConsultantActivity.RecConsultantId AS RecConsultantId,tblRecRequirement.JobTitle "
                            + "FROM tblRecConsultantActivity LEFT OUTER JOIN tblRecConsultant ON "
                            + "(tblRecConsultantActivity.ConsultantId=tblRecConsultant.Id) JOIN tblRecRequirement ON (tblRecConsultantActivity.RequirementId=tblRecRequirement.Id) WHERE (PreSales1=" + empId + " OR Presales2=" + empId + ") ");
                    if (getStartDate() != null && getEndDate() != null) {
                        startDate = DateUtility.getInstance().convertStringToMySQLDate(getStartDate());
                        endDate = DateUtility.getInstance().convertStringToMySQLDate(getEndDate());

                        queryStringBuffer.append("AND ForwardedDate >='" + startDate + "' AND ForwardedDate<='" + endDate + "' ");
                    }
                    if (!"".equals(getSkillSet()) && getSkillSet() != null) {
                        queryStringBuffer.append("AND SkillSet LIKE '%" + getSkillSet() + "%'");
                    }
                    if (!"".equals(getStatus()) && getStatus() != null) {
                        queryStringBuffer.append(" AND tblRecConsultantActivity.STATUS ='" + getStatus() + "' ");
                    }
                    if (!"".equals(getRateing()) && getRateing() != null) {
                        queryStringBuffer.append(" AND tblRecConsultantActivity.Rateing ='" + getRateing() + "' ");
                    }
                    if (!"".equals(getInterveiwType()) && getInterveiwType() != null) {
                        queryStringBuffer.append(" AND tblRecConsultantActivity.InterveiwType ='" + getInterveiwType() + "' ");
                    }
                    queryStringBuffer.append(" ORDER BY  Rateing ASC,tblRecConsultantActivity.ForwardedDate DESC");
                    /* if(getStatus().equalsIgnoreCase("Assigned")||getStatus().equalsIgnoreCase("A")) 
                    {
                    queryStringBuffer.append("AND tblRecConsultantActivity.Status LIKE 'A'");
                    }
                    else if(getStatus().equalsIgnoreCase("Forward")||getStatus().equalsIgnoreCase("F")) 
                    {
                    queryStringBuffer.append("AND tblRecConsultantActivity.Status LIKE 'F'");
                    }
                    else if(getStatus().equalsIgnoreCase("Reviewed")||getStatus().equalsIgnoreCase("R")) 
                    {
                    queryStringBuffer.append("AND tblRecConsultantActivity.Status LIKE 'R'");
                    }
                    else if(getStatus().equalsIgnoreCase("Reviewed&Forwarded")||getStatus().equalsIgnoreCase("RF")) 
                    {
                    queryStringBuffer.append("AND tblRecConsultantActivity.Status LIKE 'RF'");
                    }   */
                    // System.out.println(queryStringBuffer.toString());
                    httpServletRequest.setAttribute("resultMessage", getResultMessage());
                    // System.out.println("queryStringBuffer.toString()--" + queryStringBuffer.toString());
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

      /*Created By :Tirumala Teja Kadamanti
     * Usage : To get Untouched consultants list based on activity date 
     * 
     * untouched Consultants start */
    public String untouchedConsultantSearch()
 {
     int isUserManager = 0;
        int isUserTeamLead = 0;
        String loginId = "";
        Map employeeMap = new TreeMap();
        resultType = LOGIN;
        StringBuffer queryStringBuffer = null;
         DateUtility dateUtil = new DateUtility();
       
         if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
             resultType = "accessFailed";
            loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            isUserManager = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_USER_MANAGER).toString());
            isUserTeamLead = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_TEAM_LEAD).toString());
            
            if (AuthorizationManager.getInstance().isAuthorizedUser("PREPARE_CONSULTANT_SEARCH", userRoleId)) {
                try {
                    HibernateDataProvider hibernateDataProvider = HibernateDataProvider.getInstance();
                    datasourceDataProvider = DataSourceDataProvider.getInstance();
                    setStatesList(hibernateDataProvider.getStatesList(ApplicationConstants.STATES_OPTIONS));
                    
                    if (isUserManager == 1 || isUserTeamLead == 1) {
                        setAssignedMembers(datasourceDataProvider.getEmployeeNamesByRecruitingRole());
                    } else {
                        String empName = datasourceDataProvider.getFname_Lname(loginId);
                        employeeMap.put(loginId, empName);
                        setAssignedMembers(employeeMap);
                    }
                    defaultDataProvider = DefaultDataProvider.getInstance();
                    setOrgMap(defaultDataProvider.getOrgMap(ApplicationConstants.ORG_MAP));
                    
                    
                    queryStringBuffer = new StringBuffer();
                        queryStringBuffer.append("SELECT createdBy,tblRecConsultant.Id,"
                                    + " CONCAT(TRIM(FName),' ', TRIM(MName),' ',TRIM(LName)) AS NAME,TitleTypeId, Email2,CellPhoneNo,"
                                    + " tblRecConsultant.SkillSet,ModifiedDate,Country,(CASE WHEN (`tblRecConsultant`.`LastContactDate` = '1950-01-31') THEN '-' ELSE DATE_FORMAT(`tblRecConsultant`.`LastContactDate`,'%m-%d-%Y') END) AS `LastContactDate`,ModifiedBy,CreatedDate,ModifiedDate FROM tblRecConsultant WHERE LastContactDate='1950-01-31' ");
                         queryStringBuffer.append(" ORDER BY FName ,MName ,LName ,modifiedDate DESC ");

                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.IS_SEARCH, null);
                    if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QUERY_STRING) != null) {
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QUERY_STRING);
                    }
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QUERY_STRING, queryStringBuffer.toString());
                   resultType = SUCCESS;
                } catch (Exception ex) {
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }
        }
        return resultType;
}
    
 public String getUnTouchedConsultantList() throws ServiceLocatorException {
        resultType = LOGIN;
        StringBuffer queryStringBuffer = null;
         DateUtility dateUtil = new DateUtility();
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("DO_SKILL_SEARCH", userRoleId)) {
                try {
                    String workAuthorizationList = "";
                      defaultDataProvider = DefaultDataProvider.getInstance();
                    setOrgMap(defaultDataProvider.getOrgMap(ApplicationConstants.ORG_MAP));
                    setExpMap(defaultDataProvider.getExpMap(ApplicationConstants.EXP_MAP));
                     if (getSubmitFrom() == null) {
                        queryStringBuffer = new StringBuffer();
                     
                                   queryStringBuffer.append("SELECT createdBy,tblRecConsultant.Id,"
                                    + " CONCAT(TRIM(FName),' ', TRIM(MName),' ',TRIM(LName)) AS NAME,TitleTypeId, Email2,CellPhoneNo,"
                                    + " tblRecConsultant.SkillSet,ModifiedDate,Country,(CASE WHEN (`tblRecConsultant`.`LastContactDate` = '1950-01-31') THEN '-' ELSE DATE_FORMAT(`tblRecConsultant`.`LastContactDate`,'%m-%d-%Y') END) AS `LastContactDate`,ModifiedBy,CreatedDate,ModifiedDate FROM tblRecConsultant WHERE LastContactDate='1950-01-31'  ");
                       
                      
              if (getFiName() != null && (!"".equals(getFiName()))) {
                           setFiName(getFiName().replace("%", "*"));
                            queryStringBuffer.append("AND MATCH(tblRecConsultant.FName,tblRecConsultant.MName,tblRecConsultant.LName)  AGAINST ('" + getFiName() + "' IN BOOLEAN MODE) ");
                        
                        }
                if(getSkills() != null && (!"".equals(getSkills())))
                        {
                            queryStringBuffer.append("AND MATCH(tblRecConsultant.SkillSet) AGAINST ('" + getSkills() + "' IN BOOLEAN MODE) ");
                       }
                  if (getEmail1() != null && (!"".equals(getEmail1()))) {
                            queryStringBuffer.append("AND Email2 LIKE '" + getEmail1() + "' ");
                        }
                       if (getComments() != null && (!"".equals(getComments()))) 
                       {
                          queryStringBuffer.append("AND (tblRecConsultant.Comments) LIKE '%" + getComments() + "%' ");
                      }
                           String states1="";
                       if (!getLocationList().isEmpty())
                       {
                       for (int i = 0; i < getLocationList().size(); i++) {
                                states1 += "'" + getLocationList().get(i).toString().trim() + "',";
                              //  System.out.println("stateee---->"+getLocationList().get(i).toString().trim());
                            }
                          //  System.out.println("states1-->"+states1);
                            states1 = states1.substring(0, states1.length() - 1);
                            queryStringBuffer.append("AND Country IN (" + states1 + ") ");
                       }
                       if (!getWorkAuthorizationList().isEmpty()) {

                            for (int i = 0; i < getWorkAuthorizationList().size(); i++) {
                                workAuthorizationList += "'" + getWorkAuthorizationList().get(i).toString().trim() + "',";

                            }
                            workAuthorizationList = workAuthorizationList.substring(0, workAuthorizationList.length() - 1);
                            queryStringBuffer.append("AND WorkAuthorization IN (" + workAuthorizationList + ") ");
                           System.out.println("in wrk authorization");
                        }
                       if (getSource() != null && (!"".equals(getSource()))) {
                       queryStringBuffer.append("AND Source LIKE '" + getSource() + "' ");
                     }
                       if (getAssignedTo() != null && (!"".equals(getAssignedTo()))) {
                        queryStringBuffer.append("AND CreatedBy LIKE '" + getAssignedTo() + "' ");
                       }
                       if (getYrsExp() != null && (!"".equals(getYrsExp()))) {
                       queryStringBuffer.append("AND Experience LIKE '" + getYrsExp() + "' ");
                       }
                       if (getAvailable() != null && (!"".equals(getAvailable()))) {
                       queryStringBuffer.append("AND Available LIKE '" + getAvailable() + "' ");
                       }
                       if (getAvailableFrom() != null && (!"".equals(getAvailableFrom()))) {
                       queryStringBuffer.append("AND AvailableFrom >= '" + DateUtility.getInstance().convertDateToMySql(getAvailableFrom()) + "' ");
                       }
                       if (getAvailableTo() != null && (!"".equals(getAvailableTo()))) {
                       queryStringBuffer.append("AND AvailableFrom <= '" + DateUtility.getInstance().convertDateToMySql(getAvailableTo()) + "' ");
                       }
                       if (getStartDate() != null && (!"".equals(getStartDate()))) {
                       queryStringBuffer.append("AND ModifiedDate >='" + dateUtil.convertStringToMySQLDate(getStartDate()) + "' ");
                       }
                       if (getEndDate() != null && (!"".equals(getEndDate()))) {
                       queryStringBuffer.append("AND ModifiedDate <='" + dateUtil.convertStringToMySQLDate(getEndDate()) + "' ");
                       }
                        if (!"All".equals(getOrg())) {
                            queryStringBuffer.append("AND tblRecConsultant.OrgId = '" + getOrg() + "' ");
                        }
                        if (getTitleTypeId() != null && (!"".equals(getTitleTypeId()))) {
                        queryStringBuffer.append("AND TitleTypeId LIKE '" + getTitleTypeId() + "' ");
                        }
                        if(getCellPhoneNo()!=null && (!"".equals(getCellPhoneNo() ))){
                         queryStringBuffer.append("AND CellPhoneNo LIKE '" + getCellPhoneNo() + "' ");
                                 }
                        
                        if (getAddedFromDate() != null && (!"".equals(getAddedFromDate()))) {
                       queryStringBuffer.append("AND CreatedDate >='" + dateUtil.convertStringToMySQLDate(getAddedFromDate()) + "' ");
                       }
                           if (getAddedToDate() != null && (!"".equals(getAddedToDate()))) {
                       queryStringBuffer.append("AND CreatedDate <='" + dateUtil.convertStringToMySQLDate(getAddedToDate()) + "' ");
                       }
                      // queryStringBuffer.append(" ORDER BY modifiedDate DESC LIMIT 200");
                       if (("".equals(getFiName()))){
                       queryStringBuffer.append(" ORDER BY FName ,MName ,LName ,modifiedDate DESC ");
                        }
                        System.out.println("nsc"+queryStringBuffer.toString());
                        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QUERY_STRING) != null) {
                            httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QUERY_STRING);
                        }
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QUERY_STRING, queryStringBuffer.toString());
                        
                     }
                     httpSession = httpServletRequest.getSession(false);
                    httpSession.setAttribute("search_Query_BackToList", queryStringBuffer);
                    httpSession.setAttribute("search_Value", "true");
                   setLocationList(getLocationList());
                     HibernateDataProvider hibernateDataProvider = HibernateDataProvider.getInstance();
                    datasourceDataProvider = DataSourceDataProvider.getInstance();
                    setStatesList(hibernateDataProvider.getStatesList(ApplicationConstants.STATES_OPTIONS));
                    setAssignedMembers(datasourceDataProvider.getEmployeeNamesByRecruitingRole());
                    resultType = SUCCESS;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    resultType = "exception";
                    throw new ServiceLocatorException(ex);
                }
            }
        }

        return resultType;
    }

 
  /* untouched Consultants End  */
    
    
    
    /**
     * setter and getter methods for select tables
     */
    public List getMaritalStatusList() {
        return maritalStatusList;
    }

    public void setMaritalStatusList(List maritalStatusList) {
        this.maritalStatusList = maritalStatusList;
    }

    public List getCountryList() {
        return countryList;
    }

    public void setCountryList(List countryList) {
        this.countryList = countryList;
    }

    public Map getPrefferedQuestionsList() {
        return prefferedQuestionsList;
    }

    public void setPrefferedQuestionsList(Map prefferedQuestionsList) {
        this.prefferedQuestionsList = prefferedQuestionsList;
    }

    public List getConsultantTypesList() {
        return consultantTypesList;
    }

    public void setConsultantTypesList(List consultantTypesList) {
        this.consultantTypesList = consultantTypesList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPracticeId() {
        return practiceId;
    }

    public void setPracticeId(String practiceId) {
        this.practiceId = practiceId;
    }

    public String getTitleTypeId() {
        return titleTypeId;
    }

    public void setTitleTypeId(String titleTypeId) {
        this.titleTypeId = titleTypeId;
    }

    public String getConsultantType() {
        return consultantType;
    }

    public void setConsultantType(String consultantType) {
        this.consultantType = consultantType;
    }

    public String getCurStatus() {
        return curStatus;
    }

    public void setCurStatus(String curStatus) {
        this.curStatus = curStatus;
    }

    public int getRecContactId() {
        return recContactId;
    }

    public void setRecContactId(int recContactId) {
        this.recContactId = recContactId;
    }

    public int getDeletedFlag() {
        return deletedFlag;
    }

    public void setDeletedFlag(int deletedFlag) {
        this.deletedFlag = deletedFlag;
    }

    public String getLaName() {
        return laName;
    }

    public void setLaName(String laName) {
        this.laName = laName;
    }

    public String getFiName() {
        return fiName;
    }

    public void setFiName(String fiName) {
        this.fiName = fiName;
    }

    public String getMiName() {
        return miName;
    }

    public void setMiName(String miName) {
        this.miName = miName;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getEmail3() {
        return email3;
    }

    public void setEmail3(String email3) {
        this.email3 = email3;
    }

    public String getWorkPhoneNo() {
        return workPhoneNo;
    }

    public void setWorkPhoneNo(String workPhoneNo) {
        this.workPhoneNo = workPhoneNo;
    }

    public String getHomePhoneNo() {
        return homePhoneNo;
    }

    public void setHomePhoneNo(String homePhoneNo) {
        this.homePhoneNo = homePhoneNo;
    }

    public String getAlterPhoneNo() {
        return alterPhoneNo;
    }

    public void setAlterPhoneNo(String alterPhoneNo) {
        this.alterPhoneNo = alterPhoneNo;
    }

    public String getCellPhoneNo() {
        return cellPhoneNo;
    }

    public void setCellPhoneNo(String cellPhoneNo) {
        this.cellPhoneNo = cellPhoneNo;
    }

    public String getHotelPhoneNo() {
        return hotelPhoneNo;
    }

    public void setHotelPhoneNo(String hotelPhoneNo) {
        this.hotelPhoneNo = hotelPhoneNo;
    }

    public String getIndiaPhoneNo() {
        return indiaPhoneNo;
    }

    public void setIndiaPhoneNo(String indiaPhoneNo) {
        this.indiaPhoneNo = indiaPhoneNo;
    }

    public String getFaxPhoneNo() {
        return faxPhoneNo;
    }

    public void setFaxPhoneNo(String faxPhoneNo) {
        this.faxPhoneNo = faxPhoneNo;
    }

    public String getRefferedBy() {
        return refferedBy;
    }

    public void setRefferedBy(String refferedBy) {
        this.refferedBy = refferedBy;
    }

    public String getRefferalBonus() {
        return refferalBonus;
    }

    public void setRefferalBonus(String refferalBonus) {
        this.refferalBonus = refferalBonus;
    }

    public int getOffShoreAddressId() {
        return offShoreAddressId;
    }

    public void setOffShoreAddressId(int offShoreAddressId) {
        this.offShoreAddressId = offShoreAddressId;
    }

    public int getCurProjectAddressId() {
        return curProjectAddressId;
    }

    public void setCurProjectAddressId(int curProjectAddressId) {
        this.curProjectAddressId = curProjectAddressId;
    }

    public int getHomeAddressId() {
        return homeAddressId;
    }

    public void setHomeAddressId(int homeAddressId) {
        this.homeAddressId = homeAddressId;
    }

    public int getOtherAddressId() {
        return otherAddressId;
    }

    public void setOtherAddressId(int otherAddressId) {
        this.otherAddressId = otherAddressId;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Date getAnniversaryDate() {
        return anniversaryDate;
    }

    public void setAnniversaryDate(Date anniversaryDate) {
        this.anniversaryDate = anniversaryDate;
    }

    public Date getFirstContactDate() {
        return firstContactDate;
    }

    public void setFirstContactDate(Date firstContactDate) {
        this.firstContactDate = firstContactDate;
    }

    public String getPreferedQuestion() {
        return preferedQuestion;
    }

    public void setPreferedQuestion(String preferedQuestion) {
        this.preferedQuestion = preferedQuestion;
    }

    public String getPreferedAnswer() {
        return preferedAnswer;
    }

    public void setPreferedAnswer(String preferedAnswer) {
        this.preferedAnswer = preferedAnswer;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getRatePerHour() {
        return ratePerHour;
    }

    public void setRatePerHour(String ratePerHour) {
        this.ratePerHour = ratePerHour;
    }

    public String getCurrentEmployer() {
        return currentEmployer;
    }

    public void setCurrentEmployer(String currentEmployer) {
        this.currentEmployer = currentEmployer;
    }

    public String getRateNegotiated() {
        return rateNegotiated;
    }

    public void setRateNegotiated(String rateNegotiated) {
        this.rateNegotiated = rateNegotiated;
    }

    public Date getLastContactDate() {
        return lastContactDate;
    }

    public void setLastContactDate(Date lastContactDate) {
        this.lastContactDate = lastContactDate;
    }

    public int getLastContactBy() {
        return lastContactBy;
    }

    public void setLastContactBy(int lastContactBy) {
        this.lastContactBy = lastContactBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
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

    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.setHttpServletRequest(httpServletRequest);
    }

    public Date getOfficialDob() {
        return officialDob;
    }

    public void setOfficialDob(Date officialDob) {
        this.officialDob = officialDob;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    /**
     * setter and getter methods for seach a consultant
     */
    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public String getSearchPhone() {
        return searchPhone;
    }

    public void setSearchPhone(String searchPhone) {
        this.searchPhone = searchPhone;
    }

    public List getTitleTypeIdList() {
        return titleTypeIdList;
    }

    public void setTitleTypeIdList(List titleTypeIdList) {
        this.titleTypeIdList = titleTypeIdList;
    }

    public List getCurrStatusList() {
        return currStatusList;
    }

    public void setCurrStatusList(List currStatusList) {
        this.currStatusList = currStatusList;
    }

    //setters and getter methods for address table of the consultant
    public String getOffAddressLine1() {
        return offAddressLine1;
    }

    public void setOffAddressLine1(String offAddressLine1) {
        this.offAddressLine1 = offAddressLine1;
    }

    public String getOffAddressLine2() {
        return offAddressLine2;
    }

    public void setOffAddressLine2(String offAddressLine2) {
        this.offAddressLine2 = offAddressLine2;
    }

    public String getOffMailStop() {
        return offMailStop;
    }

    public void setOffMailStop(String offMailStop) {
        this.offMailStop = offMailStop;
    }

    public String getOffCity() {
        return offCity;
    }

    public void setOffCity(String offCity) {
        this.offCity = offCity;
    }

    public String getOffState() {
        return offState;
    }

    public void setOffState(String offState) {
        this.offState = offState;
    }

    public String getOffZip() {
        return offZip;
    }

    public void setOffZip(String offZip) {
        this.offZip = offZip;
    }

    public String getCurAddressLine1() {
        return curAddressLine1;
    }

    public void setCurAddressLine1(String curAddressLine1) {
        this.curAddressLine1 = curAddressLine1;
    }

    public String getCurAddressLine2() {
        return curAddressLine2;
    }

    public void setCurAddressLine2(String curAddressLine2) {
        this.curAddressLine2 = curAddressLine2;
    }

    public String getCurMailStop() {
        return curMailStop;
    }

    public void setCurMailStop(String curMailStop) {
        this.curMailStop = curMailStop;
    }

    public String getCurCity() {
        return curCity;
    }

    public void setCurCity(String curCity) {
        this.curCity = curCity;
    }

    public String getCurState() {
        return curState;
    }

    public void setCurState(String curState) {
        this.curState = curState;
    }

    public String getCurZip() {
        return curZip;
    }

    public void setCurZip(String curZip) {
        this.curZip = curZip;
    }

    public String getAddressLine1() {
        return AddressLine1;
    }

    public void setAddressLine1(String AddressLine1) {
        this.AddressLine1 = AddressLine1;
    }

    public String getAddressLine2() {
        return AddressLine2;
    }

    public void setAddressLine2(String AddressLine2) {
        this.AddressLine2 = AddressLine2;
    }

    public String getMailStop() {
        return MailStop;
    }

    public void setMailStop(String MailStop) {
        this.MailStop = MailStop;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
    }

    public String getZip() {
        return Zip;
    }

    public void setZip(String Zip) {
        this.Zip = Zip;
    }

    public String getOthAddressLine1() {
        return othAddressLine1;
    }

    public void setOthAddressLine1(String othAddressLine1) {
        this.othAddressLine1 = othAddressLine1;
    }

    public String getOthAddressLine2() {
        return othAddressLine2;
    }

    public void setOthAddressLine2(String othAddressLine2) {
        this.othAddressLine2 = othAddressLine2;
    }

    public String getOthMailStop() {
        return othMailStop;
    }

    public void setOthMailStop(String othMailStop) {
        this.othMailStop = othMailStop;
    }

    public String getOthCity() {
        return othCity;
    }

    public void setOthCity(String othCity) {
        this.othCity = othCity;
    }

    public String getOthState() {
        return othState;
    }

    public void setOthState(String othState) {
        this.othState = othState;
    }

    public String getOthZip() {
        return othZip;
    }

    public void setOthZip(String othZip) {
        this.othZip = othZip;
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public String getHomeCountry() {
        return HomeCountry;
    }

    public void setHomeCountry(String HomeCountry) {
        this.HomeCountry = HomeCountry;
    }

    public String getProjectCountry() {
        return ProjectCountry;
    }

    public void setProjectCountry(String ProjectCountry) {
        this.ProjectCountry = ProjectCountry;
    }

    public String getOffShoreCountry() {
        return OffShoreCountry;
    }

    public void setOffShoreCountry(String OffShoreCountry) {
        this.OffShoreCountry = OffShoreCountry;
    }

    public String getOtherCountry() {
        return OtherCountry;
    }

    public void setOtherCountry(String OtherCountry) {
        this.OtherCountry = OtherCountry;
    }

    public List getGenderList() {
        return genderList;
    }

    public void setGenderList(List genderList) {
        this.genderList = genderList;
    }

    public int getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(int consultantId) {
        this.consultantId = consultantId;
    }

    public ConsultantVTO getCurrentConsultant() {
        return currentConsultant;
    }

    public void setCurrentConsultant(ConsultantVTO currentConsultant) {
        this.currentConsultant = currentConsultant;
    }

    public int getHomAddressId() {
        return homAddressId;
    }

    public void setHomAddressId(int homAddressId) {
        this.homAddressId = homAddressId;
    }

    public int getOffAddressId() {
        return offAddressId;
    }

    public void setOffAddressId(int offAddressId) {
        this.offAddressId = offAddressId;
    }

    public int getCurAddressId() {
        return curAddressId;
    }

    public void setCurAddressId(int curAddressId) {
        this.curAddressId = curAddressId;
    }

    public int getOthAddressId() {
        return othAddressId;
    }

    public void setOthAddressId(int othAddressId) {
        this.othAddressId = othAddressId;
    }

    public List getStatesList() {
        return statesList;
    }

    public void setStatesList(List statesList) {
        this.statesList = statesList;
    }

    public List getPracticeList() {
        return practiceList;
    }

    public void setPracticeList(List practiceList) {
        this.practiceList = practiceList;
    }

    public Map getContactByMap() {
        return contactByMap;
    }

    public void setContactByMap(Map contactByMap) {
        this.contactByMap = contactByMap;
    }

    public Map getRecContactIdMap() {
        return recContactIdMap;
    }

    public void setRecContactIdMap(Map recContactIdMap) {
        this.recContactIdMap = recContactIdMap;
    }

    /*public Map getSessionMap() {
    return sessionMap;
    }
    
    public void setSessionMap(Map sessionMap) {
    this.sessionMap = sessionMap;
    }*/
    public String getSubmitFrom() {
        return submitFrom;
    }

    public void setSubmitFrom(String submitFrom) {
        this.submitFrom = submitFrom;
    }

    public String getWorkAuthorization() {
        return workAuthorization;
    }

    public void setWorkAuthorization(String workAuthorization) {
        this.workAuthorization = workAuthorization;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public String getUploadContentType() {
        return uploadContentType;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public int getObjectIdinInt() {
        return objectIdinInt;
    }

    public void setObjectIdinInt(int objectIdinInt) {
        this.objectIdinInt = objectIdinInt;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public List getIndustryList() {
        return industryList;
    }

    public void setIndustryList(List industryList) {
        this.industryList = industryList;
    }

    public String getIndustryId() {
        return industryId;
    }

    public void setIndustryId(String industryId) {
        this.industryId = industryId;
    }

    public Map getAssignedMembers() {
        return assignedMembers;
    }

    public void setAssignedMembers(Map assignedMembers) {
        this.assignedMembers = assignedMembers;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getCurrentAction() {
        return currentAction;
    }

    public void setCurrentAction(String currentAction) {
        this.currentAction = currentAction;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(Date interviewDate) {
        this.interviewDate = interviewDate;
    }

    public Date getReportingDate() {
        return reportingDate;
    }

    public void setReportingDate(Date reportingDate) {
        this.reportingDate = reportingDate;
    }

    public String getComments1() {
        return comments1;
    }

    public void setComments1(String comments1) {
        this.comments1 = comments1;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getConsultantName() {
        return consultantName;
    }

    public void setConsultantName(String consultantName) {
        this.consultantName = consultantName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    private void setActivityStatusList(List activityStatusList) {
        this.activityStatusList = activityStatusList;
    }

    public List getActivityStatusList() {
        return activityStatusList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Timestamp getDueDateTimeStamp() {
        return dueDateTimeStamp;
    }

    public void setDueDateTimeStamp(Timestamp dueDateTimeStamp) {
        this.dueDateTimeStamp = dueDateTimeStamp;
    }

    public java.sql.Date getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(java.sql.Date availableFrom) {
        this.availableFrom = availableFrom;
    }

    public String getYrsExp() {
        return YrsExp;
    }

    public void setYrsExp(String YrsExp) {
        this.YrsExp = YrsExp;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public java.sql.Date getAvailableTo() {
        return availableTo;
    }

    public void setAvailableTo(java.sql.Date availableTo) {
        this.availableTo = availableTo;
    }

    // NEW ADD
    public Map getOrgMap() {
        return orgMap;
    }

    public void setOrgMap(Map orgMap) {
        this.orgMap = orgMap;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getIsSearch() {
        return isSearch;
    }

    public void setIsSearch(String isSearch) {
        this.isSearch = isSearch;
    }

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }

    /**
     * @return the locationList
     */
    public List getLocationList() {
        return locationList;
    }

    /**
     * @param locationList the locationList to set
     */
    public void setLocationList(List locationList) {
        this.locationList = locationList;
    }

    /**
     * @return the workAuthorizationList
     */
    public List getWorkAuthorizationList() {
        return workAuthorizationList;
    }

    /**
     * @param workAuthorizationList the workAuthorizationList to set
     */
    public void setWorkAuthorizationList(List workAuthorizationList) {
        this.workAuthorizationList = workAuthorizationList;
    }

    /**
     * @return the referTo
     */
    public String getReferTo() {
        return referTo;
    }

    /**
     * @param referTo the referTo to set
     */
    public void setReferTo(String referTo) {
        this.referTo = referTo;
    }

    /**
     * @return the operationType
     */
    public String getOperationType() {
        return operationType;
    }

    /**
     * @param operationType the operationType to set
     */
    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    /**
     * @return the rateing
     */
    public String getRateing() {
        return rateing;
    }

    /**
     * @param rateing the rateing to set
     */
    public void setRateing(String rateing) {
        this.rateing = rateing;
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
     * @return the techName
     */
    public String getTechName() {
        return techName;
    }

    /**
     * @param techName the techName to set
     */
    public void setTechName(String techName) {
        this.techName = techName;
    }

    /**
     * @return the expMap
     */
    public Map getExpMap() {
        return expMap;
    }

    /**
     * @param expMap the expMap to set
     */
    public void setExpMap(Map expMap) {
        this.expMap = expMap;
    }

    /**
     * @return the exp
     */
    public String getExp() {
        return exp;
    }

    /**
     * @param exp the exp to set
     */
    public void setExp(String exp) {
        this.exp = exp;
    }

    /**
     * @return the techEval
     */
    public String getTechEval() {
        return techEval;
    }

    /**
     * @param techEval the techEval to set
     */
    public void setTechEval(String techEval) {
        this.techEval = techEval;
    }

    /**
     * @return the available
     */
    public String getAvailable() {
        return available;
    }

    /**
     * @param available the available to set
     */
    public void setAvailable(String available) {
        this.available = available;
    }

    /**
     * @return the preferredState
     */
    public String getPreferredState() {
        return preferredState;
    }

    /**
     * @param preferredState the preferredState to set
     */
    public void setPreferredState(String preferredState) {
        this.preferredState = preferredState;
    }

    public String getActivityFromDate() {
        return activityFromDate;
    }

    public void setActivityFromDate(String activityFromDate) {
        this.activityFromDate = activityFromDate;
    }

    public String getActivityToDate() {
        return activityToDate;
    }

    public void setActivityToDate(String activityToDate) {
        this.activityToDate = activityToDate;
    }

    public String getRecList() {
        return recList;
    }

    public void setRecList(String recList) {
        this.recList = recList;
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

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    /**
     * @return the siteConsultants
     */
    public int getSiteConsultants() {
        return siteConsultants;
    }

    /**
     * @param siteConsultants the siteConsultants to set
     */
    public void setSiteConsultants(int siteConsultants) {
        this.siteConsultants = siteConsultants;
    }

    public int getPreSales1() {
        return preSales1;
    }

    public void setPreSales1(int preSales1) {
        this.preSales1 = preSales1;
    }

    public int getPreSales2() {
        return preSales2;
    }

    public void setPreSales2(int preSales2) {
        this.preSales2 = preSales2;
    }

    public int getRecConsultantId() {
        return recConsultantId;
    }

    public void setRecConsultantId(int recConsultantId) {
        this.recConsultantId = recConsultantId;
    }

    public String getRequirementAdminFlag() {
        return requirementAdminFlag;
    }

    public void setRequirementAdminFlag(String requirementAdminFlag) {
        this.requirementAdminFlag = requirementAdminFlag;
    }

    public int getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(int requirementId) {
        this.requirementId = requirementId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Map getPreSalesMap() {
        return preSalesMap;
    }

    public void setPreSalesMap(Map preSalesMap) {
        this.preSalesMap = preSalesMap;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInterveiwType() {
        return interveiwType;
    }

    public void setInterveiwType(String interveiwType) {
        this.interveiwType = interveiwType;
    }

    public String getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(String scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public String getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(String scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public String getTimeFormat() {
        return timeFormat;
    }

    public void setTimeFormat(String timeFormat) {
        this.timeFormat = timeFormat;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getSkillSet() {
        return skillSet;
    }

    public void setSkillSet(String skillSet) {
        this.skillSet = skillSet;
    }

    public int getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(int attachmentId) {
        this.attachmentId = attachmentId;
    }

    public int getCommskills() {
        return commskills;
    }

    public void setCommskills(int commskills) {
        this.commskills = commskills;
    }

    public int getDomainskills() {
        return domainskills;
    }

    public void setDomainskills(int domainskills) {
        this.domainskills = domainskills;
    }

    public int getRating1() {
        return rating1;
    }

    public void setRating1(int rating1) {
        this.rating1 = rating1;
    }

    public int getRating10() {
        return rating10;
    }

    public void setRating10(int rating10) {
        this.rating10 = rating10;
    }

    public int getRating2() {
        return rating2;
    }

    public void setRating2(int rating2) {
        this.rating2 = rating2;
    }

    public int getRating3() {
        return rating3;
    }

    public void setRating3(int rating3) {
        this.rating3 = rating3;
    }

    public int getRating4() {
        return rating4;
    }

    public void setRating4(int rating4) {
        this.rating4 = rating4;
    }

    public int getRating5() {
        return rating5;
    }

    public void setRating5(int rating5) {
        this.rating5 = rating5;
    }

    public int getRating6() {
        return rating6;
    }

    public void setRating6(int rating6) {
        this.rating6 = rating6;
    }

    public int getRating7() {
        return rating7;
    }

    public void setRating7(int rating7) {
        this.rating7 = rating7;
    }

    public int getRating9() {
        return rating9;
    }

    public void setRating9(int rating9) {
        this.rating9 = rating9;
    }

    public String getSkill1() {
        return skill1;
    }

    public void setSkill1(String skill1) {
        this.skill1 = skill1;
    }

    public String getSkill10() {
        return skill10;
    }

    public void setSkill10(String skill10) {
        this.skill10 = skill10;
    }

    public String getSkill2() {
        return skill2;
    }

    public void setSkill2(String skill2) {
        this.skill2 = skill2;
    }

    public String getSkill3() {
        return skill3;
    }

    public void setSkill3(String skill3) {
        this.skill3 = skill3;
    }

    public String getSkill4() {
        return skill4;
    }

    public void setSkill4(String skill4) {
        this.skill4 = skill4;
    }

    public String getSkill5() {
        return skill5;
    }

    public void setSkill5(String skill5) {
        this.skill5 = skill5;
    }

    public String getSkill6() {
        return skill6;
    }

    public void setSkill6(String skill6) {
        this.skill6 = skill6;
    }

    public String getSkill7() {
        return skill7;
    }

    public void setSkill7(String skill7) {
        this.skill7 = skill7;
    }

    public String getSkill8() {
        return skill8;
    }

    public void setSkill8(String skill8) {
        this.skill8 = skill8;
    }

    public String getSkill9() {
        return skill9;
    }

    public void setSkill9(String skill9) {
        this.skill9 = skill9;
    }

    public Map getSkillSetMap() {
        return skillSetMap;
    }

    public void setSkillSetMap(Map skillSetMap) {
        this.skillSetMap = skillSetMap;
    }

    public int getTechSkills() {
        return techSkills;
    }

    public void setTechSkills(int techSkills) {
        this.techSkills = techSkills;
    }

    public int getRating8() {
        return rating8;
    }

    public void setRating8(int rating8) {
        this.rating8 = rating8;
    }

    public int getIsReject() {
        return isReject;
    }

    public void setIsReject(int isReject) {
        this.isReject = isReject;
    }

    public Map getClientMap() {
        return clientMap;
    }

    public void setClientMap(Map clientMap) {
        this.clientMap = clientMap;
    }

    public String getEndDateReport() {
        return endDateReport;
    }

    public void setEndDateReport(String endDateReport) {
        this.endDateReport = endDateReport;
    }

    public Map getRecruiterMap() {
        return recruiterMap;
    }

    public void setRecruiterMap(Map recruiterMap) {
        this.recruiterMap = recruiterMap;
    }

    public String getStartDateReport() {
        return startDateReport;
    }

    public void setStartDateReport(String startDateReport) {
        this.startDateReport = startDateReport;
    }

    public Map getTeamLeadMap() {
        return teamLeadMap;
    }

    public void setTeamLeadMap(Map teamLeadMap) {
        this.teamLeadMap = teamLeadMap;
    }

    public List getTechLeadList() {
        return techLeadList;
    }

    public void setTechLeadList(List techLeadList) {
        this.techLeadList = techLeadList;
    }

    public String getRecruiterLoginId() {
        return recruiterLoginId;
    }

    public void setRecruiterLoginId(String recruiterLoginId) {
        this.recruiterLoginId = recruiterLoginId;
    }

    public String getConsultId() {
        return consultId;
    }

    public void setConsultId(String consultId) {
        this.consultId = consultId;
    }

    /**
     * @return the addedToDate
     */
    public String getAddedToDate() {
        return addedToDate;
    }

    /**
     * @param addedToDate the addedToDate to set
     */
    public void setAddedToDate(String addedToDate) {
        this.addedToDate = addedToDate;
    }

    /**
     * @return the addedFromDate
     */
    public String getAddedFromDate() {
        return addedFromDate;
    }

    /**
     * @param addedFromDate the addedFromDate to set
     */
    public void setAddedFromDate(String addedFromDate) {
        this.addedFromDate = addedFromDate;
    }

    /**
     * @return the skypeId
     */
    public String getSkypeId() {
        return skypeId;
    }

    /**
     * @param skypeId the skypeId to set
     */
    public void setSkypeId(String skypeId) {
        this.skypeId = skypeId;
    }
}
