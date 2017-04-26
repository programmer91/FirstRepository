/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.mirage.marketing.events;

import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.AuthorizationManager;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.ServiceLocator;
import com.mss.mirage.util.ServiceLocatorException;
import com.opensymphony.xwork2.ActionSupport;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

/**
 *
 * @author miracle
 */
public class EventAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {

    /** The resultType is used for storing type of message. */
    private String resultType;
    /** The resultMessage is used for storing resulted message. */
    private String resultMessage;
    /** The httpServletRequest is used for binding request object to this class. */
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    
    /**
     * 
     * This Object dataSourceDataProvider provides the reference variable for DataSourceDataProvider
     */
    private DataSourceDataProvider dataSourceDataProvider;
    private List timeList;
    private String startTime;
    private String endTime;
    private String midDayFrom;
    private String midDayTo;
    private String eventId;
    private String eventSearchType;
    private String primarySpeakerExist;
    private String tableName;
    private List trackNamesList;
    private String trackName;
    private String qmeetYear;
    private Map qmeetMap;
    private String qmeetTitleId;
    private Map associatedEventMap = new HashMap();
    private String dateOfPublish;
    private String searchSeriesTitle;
    private String searchSeriesStatus;
    private String eventType;
    private String searchSeriesTrack;
    private String seriesId;
    private Map unassociatedEventMap = new HashMap();
    private String associatedEventId;
    private String primarySpeakerId;
//-------------
    private Map eventSpeakerMap = new TreeMap();
    //   private Map salesTeamExceptAccountTeamMap = new TreeMap();
    private Map speakersMapExceptEventSpeakerMap = new TreeMap();
    //private String primarySpeakerId;
    //private Map accountTeamParameters;
    private Map speakersParameters;
    private String eventTitle;
    private String objectType;
    private Map trackNamesMap;
    
 /**
     * 
     * This variable userRoleId is to get/store userRoleId
     */
    private int userRoleId;
     private String createdDateFrom;
    private String createdDateTo;
    private String title;
    
    private String status;
     private String ieeCreatedDateFrom;
     private String ieeEventSearchType;
	 private String ieeCreatedDateTo;
    private String ieeTitle;
    
    private String ieeStatus;
    
    
    private String  internalWebinarCreatedDateFrom;
private String  internalWebinarCreatedDateTo;
private String  internalWebinarTitle;
private String  internalWebinarStatus;

private String createdDateFromConference;
    private String createdDateToConference;
    private String titleConference;
    private String statusConference;
    
    
    
      private Map activeEmployeeMap;
    private List accessTypeList;
    private String emeetAccessType;
     private String emeetAttendeesEmail;
     private String emeetMeetingAccessStatus;
     private String emeetStatusByDate;
     private String executiveMeetMonthSearch;
     private String emeetType;
    private String emeetToDate;
    private String emeetFromDate;
    private int id;
private String createdBy;
    
    /*Event posting
     * Author : Santosh Kola
     * Date : 01/09/2017
     * 
     */
    public String internalEvents() {

        setResultType(LOGIN);
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            setUserRoleId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString()));
            setResultType("accessFailed");
            if (AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY", getUserRoleId())) {
                try {
                    setTimeList(DataSourceDataProvider.getInstance().getTimeList());
                    setTrackNamesMap(ServiceLocator.getMarketingService().getLkpTrackNamesMap());
                    setActiveEmployeeMap(DataSourceDataProvider.getInstance().getActiveEmployeesList());
                    if (httpServletRequest.getSession(false).getAttribute("qmeetList") != null) {
                        httpServletRequest.getSession(false).removeAttribute("qmeetList");
                    } if (httpServletRequest.getSession(false).getAttribute("ieeEventslist") != null) {
                        httpServletRequest.getSession(false).removeAttribute("ieeEventslist");
                    } if (httpServletRequest.getSession(false).getAttribute("internalWebinarEventslist") != null) {
                        httpServletRequest.getSession(false).removeAttribute("internalWebinarEventslist");
                    }if (httpServletRequest.getSession(false).getAttribute("executiveMeetingsList") != null) {
                        httpServletRequest.getSession(false).removeAttribute("executiveMeetingsList");
                    }
                    if (httpServletRequest.getSession(false).getAttribute("executiveMeetingsAttendeeslist") != null) {
                        httpServletRequest.getSession(false).removeAttribute("executiveMeetingsAttendeeslist");
                    }
                    
                    
                    setResultType(SUCCESS);

                } catch (Exception ex) {
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    setResultType(ERROR);
                }
            }
        }//Close Session Checking
        return getResultType();
    }

   
 
    /*
     * phani methods
     * 
     */
     public String internalQMeetEventSearch() {

        setResultType(LOGIN);
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            setUserRoleId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString()));
            setResultType("accessFailed");
            if (AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY", getUserRoleId())) {
                try {
                    setTimeList(DataSourceDataProvider.getInstance().getTimeList());
                    List mainList = null;
                    setEventSearchType("Q");
                    setTrackNamesMap(ServiceLocator.getMarketingService().getLkpTrackNamesMap());
                    //  System.out.println("Eventtype11--->"+getEventSearchType());
                   setActiveEmployeeMap(DataSourceDataProvider.getInstance().getActiveEmployeesList());
                    mainList = ServiceLocator.getEventService().doInternalEventSearch(this);
                    
                    if (httpServletRequest.getSession(false).getAttribute("qmeetList") != null) {
                        httpServletRequest.getSession(false).removeAttribute("qmeetList");
                    } if (httpServletRequest.getSession(false).getAttribute("ieeEventslist") != null) {
                        httpServletRequest.getSession(false).removeAttribute("ieeEventslist");
                    } if (httpServletRequest.getSession(false).getAttribute("internalWebinarEventslist") != null) {
                        httpServletRequest.getSession(false).removeAttribute("internalWebinarEventslist");
                    }if (httpServletRequest.getSession(false).getAttribute("executiveMeetingsList") != null) {
                        httpServletRequest.getSession(false).removeAttribute("executiveMeetingsList");
                    }
                    if (httpServletRequest.getSession(false).getAttribute("executiveMeetingsAttendeeslist") != null) {
                        httpServletRequest.getSession(false).removeAttribute("executiveMeetingsAttendeeslist");
                    }
                    
                    // System.out.println("mainList size-->"+mainList.size());
                    httpServletRequest.getSession(false).setAttribute("qmeetList", mainList);
                    setResultType(SUCCESS);

                } catch (Exception ex) {
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    setResultType(ERROR);
                }
            }
        }//Close Session Checking
        return getResultType();
    }
    public String internalIeeEventSearch() {

        setResultType(LOGIN);
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            setUserRoleId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString()));
            setResultType("accessFailed");
            if (AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY", getUserRoleId())) {
                try {
                    setTimeList(DataSourceDataProvider.getInstance().getTimeList());
                    setTrackNamesMap(ServiceLocator.getMarketingService().getLkpTrackNamesMap());
                    setActiveEmployeeMap(DataSourceDataProvider.getInstance().getActiveEmployeesList());
                    List ieeList = null;
                    setEventSearchType("IEE");
                     setCreatedDateFrom(getIeeCreatedDateFrom());
                    setCreatedDateTo(getIeeCreatedDateTo());
                    setStatus(getIeeStatus());
                    setTitle(getIeeTitle());
                    //  System.out.println("Eventtype11--->"+getEventSearchType());
                    ieeList = ServiceLocator.getEventService().doInternalEventSearch(this);
                    
                   if (httpServletRequest.getSession(false).getAttribute("qmeetList") != null) {
                        httpServletRequest.getSession(false).removeAttribute("qmeetList");
                    } if (httpServletRequest.getSession(false).getAttribute("ieeEventslist") != null) {
                        httpServletRequest.getSession(false).removeAttribute("ieeEventslist");
                    } if (httpServletRequest.getSession(false).getAttribute("internalWebinarEventslist") != null) {
                        httpServletRequest.getSession(false).removeAttribute("internalWebinarEventslist");
                    }if (httpServletRequest.getSession(false).getAttribute("executiveMeetingsList") != null) {
                        httpServletRequest.getSession(false).removeAttribute("executiveMeetingsList");
                    }
                    if (httpServletRequest.getSession(false).getAttribute("executiveMeetingsAttendeeslist") != null) {
                        httpServletRequest.getSession(false).removeAttribute("executiveMeetingsAttendeeslist");
                    }
                    
                    // System.out.println("mainList size-->"+mainList.size());
                    httpServletRequest.getSession(false).setAttribute("ieeEventslist", ieeList);
                    setResultType(SUCCESS);

                } catch (Exception ex) {
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    setResultType(ERROR);
                }
            }
        }//Close Session Checking
        return getResultType();
    }
    
    
    public String internalWebinarEventSearch() {

        setResultType(LOGIN);
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            setUserRoleId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString()));
            setResultType("accessFailed");
            if (AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY", getUserRoleId())) {
                try {
                    setTimeList(DataSourceDataProvider.getInstance().getTimeList());
                    setActiveEmployeeMap(DataSourceDataProvider.getInstance().getActiveEmployeesList());
                    List ieeList = null;
                    setEventSearchType("I");
                    //  System.out.println("Eventtype11--->"+getEventSearchType());
                    setCreatedDateFrom(getInternalWebinarCreatedDateFrom());
                    setCreatedDateTo(getInternalWebinarCreatedDateTo());
                    setStatus(getInternalWebinarStatus());
                    setTitle(getInternalWebinarTitle());
                    setTrackNamesMap(ServiceLocator.getMarketingService().getLkpTrackNamesMap());
                    ieeList = ServiceLocator.getEventService().doInternalEventSearch(this);
                    
                   if (httpServletRequest.getSession(false).getAttribute("qmeetList") != null) {
                        httpServletRequest.getSession(false).removeAttribute("qmeetList");
                    } if (httpServletRequest.getSession(false).getAttribute("ieeEventslist") != null) {
                        httpServletRequest.getSession(false).removeAttribute("ieeEventslist");
                    } if (httpServletRequest.getSession(false).getAttribute("internalWebinarEventslist") != null) {
                        httpServletRequest.getSession(false).removeAttribute("internalWebinarEventslist");
                    }if (httpServletRequest.getSession(false).getAttribute("executiveMeetingsList") != null) {
                        httpServletRequest.getSession(false).removeAttribute("executiveMeetingsList");
                    }
                    if (httpServletRequest.getSession(false).getAttribute("executiveMeetingsAttendeeslist") != null) {
                        httpServletRequest.getSession(false).removeAttribute("executiveMeetingsAttendeeslist");
                    }
                    
                    // System.out.println("mainList size-->"+mainList.size());
                    httpServletRequest.getSession(false).setAttribute("internalWebinarEventslist", ieeList);
                    setResultType(SUCCESS);

                } catch (Exception ex) {
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    setResultType(ERROR);
                }
            }
        }//Close Session Checking
        return getResultType();
    }
    
    
     public String internalEmeetSearch()
    {
          resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("EMEET_ACCESS",userRoleId)){
                try{ 
                    List mainList =null;
                   setTimeList(DataSourceDataProvider.getInstance().getTimeList());
                    setTrackNamesMap(ServiceLocator.getMarketingService().getLkpTrackNamesMap());
                    setActiveEmployeeMap(DataSourceDataProvider.getInstance().getActiveEmployeesList());
          // setLocationList(ServiceLocator.getMarketingService().getLocationList());

//mainList = ServiceLocator.getMarketingService().doJobSearch(this);
mainList = ServiceLocator.getEventService().doInternalEmeetSearch(this);

if (httpServletRequest.getSession(false).getAttribute("qmeetList") != null) {
                        httpServletRequest.getSession(false).removeAttribute("qmeetList");
                    } if (httpServletRequest.getSession(false).getAttribute("ieeEventslist") != null) {
                        httpServletRequest.getSession(false).removeAttribute("ieeEventslist");
                    } if (httpServletRequest.getSession(false).getAttribute("internalWebinarEventslist") != null) {
                        httpServletRequest.getSession(false).removeAttribute("internalWebinarEventslist");
                    }if (httpServletRequest.getSession(false).getAttribute("executiveMeetingsList") != null) {
                        httpServletRequest.getSession(false).removeAttribute("executiveMeetingsList");
                    }
                    if (httpServletRequest.getSession(false).getAttribute("executiveMeetingsAttendeeslist") != null) {
                        httpServletRequest.getSession(false).removeAttribute("executiveMeetingsAttendeeslist");
                    }
                    
        httpServletRequest.getSession(false).setAttribute("executiveMeetingsList", mainList);
                    resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
    }
  
   
   
    
    public String doPublishInternalExeMeet() {
        resultType = LOGIN;
           
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("EMEET_ACCESS", userRoleId)) {
                try {
                    setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                    
                    setResultMessage(ServiceLocator.getEventService().doPublishInternalExeMeet(this));

                    // Map eventSpeakerMap = new TreeMap();
                    //  setSpeakersMapExceptEventSpeakerMap(ServiceLocator.getMarketingService().getAllPeaopleExceptEventSpeakers(eventSpeakerMap));
                    resultType = SUCCESS;
                } catch (Exception ex) {
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
    }
    
    
    
    public String doActiveInternalExeMeet() {
        resultType = LOGIN;
           
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("EMEET_ACCESS", userRoleId)) {
                try {
                    setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                   
                    setResultMessage(ServiceLocator.getEventService().doActiveInternalExeMeet(this));

                    // Map eventSpeakerMap = new TreeMap();
                    //  setSpeakersMapExceptEventSpeakerMap(ServiceLocator.getMarketingService().getAllPeaopleExceptEventSpeakers(eventSpeakerMap));
                    resultType = SUCCESS;
                } catch (Exception ex) {
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
    }
    
    
    
    
    
    public String externalEvents() {

        setResultType(LOGIN);
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            setUserRoleId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString()));
            setResultType("accessFailed");
            if (AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY", getUserRoleId())) {
                try {
                    setTimeList(DataSourceDataProvider.getInstance().getTimeList());
                    setTrackNamesMap(ServiceLocator.getMarketingService().getLkpTrackNamesMap());
                    //setUnassociatedEventMap(ServiceLocator.getEventService().getUnAssociatedWebinars(getSeriesId()));
                    if (httpServletRequest.getSession(false).getAttribute("externalWebinarList") != null) {
                        httpServletRequest.getSession(false).removeAttribute("externalWebinarList");
                    }if (httpServletRequest.getSession(false).getAttribute("conferenceEventslist") != null) {
                        httpServletRequest.getSession(false).removeAttribute("conferenceEventslist");
                    }

                    setResultType(SUCCESS);

                } catch (Exception ex) {
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    setResultType(ERROR);
                }
            }
        }//Close Session Checking
        return getResultType();
    }

    public String externalWebinarSearch() {

        setResultType(LOGIN);
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            setUserRoleId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString()));
            setResultType("accessFailed");
            if (AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY", getUserRoleId())) {
                try {
                    setTimeList(DataSourceDataProvider.getInstance().getTimeList());
                    List mainList = null;
setEventSearchType("E");
 //setUnassociatedEventMap(ServiceLocator.getEventService().getUnAssociatedWebinars(getSeriesId()));
                    setTrackNamesMap(ServiceLocator.getMarketingService().getLkpTrackNamesMap());
                    //  System.out.println("Eventtype11--->"+getEventSearchType());
                    mainList = ServiceLocator.getEventService().doExternalEventSearch(this);
                    // System.out.println("mainList size-->"+mainList.size());
                    
                     if (httpServletRequest.getSession(false).getAttribute("externalWebinarList") != null) {
                        httpServletRequest.getSession(false).removeAttribute("externalWebinarList");
                    }if (httpServletRequest.getSession(false).getAttribute("conferenceEventslist") != null) {
                        httpServletRequest.getSession(false).removeAttribute("conferenceEventslist");
                    }
                    
                    httpServletRequest.getSession(false).setAttribute("externalWebinarList", mainList);
                    setResultType(SUCCESS);

                } catch (Exception ex) {
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    setResultType(ERROR);
                }
            }
        }//Close Session Checking
        return getResultType();
    }
      public String externalConferenceEventSearch() {

        setResultType(LOGIN);
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            setUserRoleId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString()));
            setResultType("accessFailed");
            if (AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY", getUserRoleId())) {
                try {
                    setTimeList(DataSourceDataProvider.getInstance().getTimeList());
                    List mainList = null;

                    setTrackNamesMap(ServiceLocator.getMarketingService().getLkpTrackNamesMap());
                  //  setUnassociatedEventMap(ServiceLocator.getEventService().getUnAssociatedWebinars(getSeriesId()));
                    //  System.out.println("Eventtype11--->"+getEventSearchType());
                    setEventSearchType("C");
                    setCreatedDateFrom(getCreatedDateFromConference());
                    setCreatedDateTo(getCreatedDateToConference());
                    setTitle(getTitleConference());
                    setStatus(getStatusConference());
                    mainList = ServiceLocator.getEventService().doExternalEventSearch(this);
                    
                     if (httpServletRequest.getSession(false).getAttribute("externalWebinarList") != null) {
                        httpServletRequest.getSession(false).removeAttribute("externalWebinarList");
                    }if (httpServletRequest.getSession(false).getAttribute("conferenceEventslist") != null) {
                        httpServletRequest.getSession(false).removeAttribute("conferenceEventslist");
                    }
                    // System.out.println("mainList size-->"+mainList.size());
                    httpServletRequest.getSession(false).setAttribute("conferenceEventslist", mainList);
                    setResultType(SUCCESS);

                } catch (Exception ex) {
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    setResultType(ERROR);
                }
            }
        }//Close Session Checking
        return getResultType();
    }
      
      
      
              public String getQmeetAttendees(){
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY",userRoleId)){
                try{ //setJobId(getJobId());
                    
                    
                    if(httpServletRequest.getSession(false).getAttribute("qmeetAttendeesList")!=null)
                        httpServletRequest.getSession(false).removeAttribute("qmeetAttendeesList");
                                
                     List mainList = null;
                    setEventId(getEventId());
                   mainList = ServiceLocator.getEventService().getQmeetAttendees(this);
                   // setEventId(getEventId());
        httpServletRequest.getSession(false).setAttribute("qmeetAttendeesList", mainList);
                resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
   } 
   public String executeMeetAttendeesSearch() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("EMEET_ACCESS", userRoleId)) {
                try {
                    List mainList = null;
                     setTimeList(DataSourceDataProvider.getInstance().getTimeList());
                    setTrackNamesMap(ServiceLocator.getMarketingService().getLkpTrackNamesMap());
                    setActiveEmployeeMap(DataSourceDataProvider.getInstance().getActiveEmployeesList());
                    //   setAccessTypeList(DataSourceDataProvider.getInstance().getExecuteMeetAccessTyoes());
                    
//mainList = ServiceLocator.getMarketingService().doJobSearch(this);
                    mainList = ServiceLocator.getEventService().doExecuteMeetAttendeesSearch(this);
                    if (httpServletRequest.getSession(false).getAttribute("qmeetList") != null) {
                        httpServletRequest.getSession(false).removeAttribute("qmeetList");
                    } if (httpServletRequest.getSession(false).getAttribute("ieeEventslist") != null) {
                        httpServletRequest.getSession(false).removeAttribute("ieeEventslist");
                    } if (httpServletRequest.getSession(false).getAttribute("internalWebinarEventslist") != null) {
                        httpServletRequest.getSession(false).removeAttribute("internalWebinarEventslist");
                    }if (httpServletRequest.getSession(false).getAttribute("executiveMeetingsList") != null) {
                        httpServletRequest.getSession(false).removeAttribute("executiveMeetingsList");
                    }
                    if (httpServletRequest.getSession(false).getAttribute("executiveMeetingsAttendeeslist") != null) {
                        httpServletRequest.getSession(false).removeAttribute("executiveMeetingsAttendeeslist");
                    }
                    httpServletRequest.getSession(false).setAttribute("executiveMeetingsAttendeeslist", mainList);



                    resultType = SUCCESS;

                } catch (Exception ex) {
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
    }  
   
   
   public String getQmeetAttendiesExcelSheet() throws ServiceLocatorException {
        
        resultType = INPUT;
        OutputStream outputStream = null;
        InputStream inputStream = null;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String responseString = "";
            try {
                String fileLocation = "";
                String queryString="";
                if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.INVESTMENT_DETAILS)!=null)
              queryString=httpServletRequest.getSession(false).getAttribute(ApplicationConstants.INVESTMENT_DETAILS).toString();
              if(httpServletRequest.getSession(false).getAttribute("qmeetAttendeesList")!=null){
                   List mainList=(java.util.List)httpServletRequest.getSession(false).getAttribute("qmeetAttendeesList");
                    String filePath = "";
        StringBuffer sb = null;

        
        HashMap map = null;
        double totalAmount = 0.0;
        double totalOpprtunity = 0.0;
        String generatedPath = "";
        List finalList = mainList;

            
                
            int j = 1;
          //  System.out.println("query...."+query);
            
         

            if (finalList.size() > 0) {
                HSSFWorkbook hssfworkbook = new HSSFWorkbook();
                HSSFSheet sheet = hssfworkbook.createSheet("Qmeet Attendies");

                HSSFFont timesBoldFont1 = hssfworkbook.createFont();               
                timesBoldFont1.setFontHeightInPoints((short) 13);
                timesBoldFont1.setColor(HSSFColor.BLACK.index);
                timesBoldFont1.setFontName("Arial");
               
                HSSFCellStyle cellColor = hssfworkbook.createCellStyle();
                cellColor.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
                cellColor.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                cellColor.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellColor.setBorderTop((short) 1); // single line border
                cellColor.setBorderBottom((short) 1); // single line border
                cellColor.setFont(timesBoldFont1);

                HSSFCellStyle cellColor1 = hssfworkbook.createCellStyle();

                cellColor1.setFillForegroundColor(HSSFColor.WHITE.index);
                cellColor1.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                cellColor1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellColor1.setBorderTop((short) 1); // single line border
                cellColor1.setBorderBottom((short) 1); // single line border
                cellColor1.setFont(timesBoldFont1);


                HSSFCellStyle cs = hssfworkbook.createCellStyle();

                HSSFCellStyle headercs = hssfworkbook.createCellStyle();
                headercs.setFillForegroundColor(HSSFColor.BLUE.index);
                headercs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                headercs.setBorderTop((short) 1); // single line border
                headercs.setBorderBottom((short) 1); // single line border
                // cs.setFont(timesBoldFont1);

                HSSFFont timesBoldFont = hssfworkbook.createFont();
                timesBoldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                timesBoldFont.setFontHeightInPoints((short) 13);
                timesBoldFont.setColor(HSSFColor.WHITE.index);
                timesBoldFont.setFontName("Calibri");
                headercs.setFont(timesBoldFont);
                // cs.setFont(timesBoldFont);
                HSSFFont footerFont = hssfworkbook.createFont();
                footerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                timesBoldFont.setFontHeightInPoints((short) 13);
                footerFont.setFontName("Calibri");

                HSSFCellStyle footercs = hssfworkbook.createCellStyle();
                footercs.setFont(footerFont);


                HSSFDataFormat df = hssfworkbook.createDataFormat();
                HSSFRow row = sheet.createRow((short) 0);
                HSSFCell cell = row.createCell((short) 0);

                HSSFCell cell1 = row.createCell((short) 1);

                HSSFCell cell2 = row.createCell((short) 2);
                HSSFCell cell3 = row.createCell((short) 3);

                HSSFCell cell4 = row.createCell((short) 4);
                HSSFCell cell5 = row.createCell((short) 5);
                HSSFCell cell6 = row.createCell((short) 6);
                HSSFCell cell7 = row.createCell((short) 7);
                HSSFCell cell8 = row.createCell((short) 8);
                HSSFCell cell9 = row.createCell((short) 9);
                HSSFCell cell10 = row.createCell((short) 10);
                HSSFCell cell11 = row.createCell((short) 11);

                cell.setCellValue("SNO");
                cell1.setCellValue("First Name");
                cell2.setCellValue("Event Name");
                cell3.setCellValue("Email_Id");
                cell4.setCellValue("Phone_No");
                cell5.setCellValue("Department");
                cell6.setCellValue("Location");
                cell7.setCellValue("Food Pref");
                cell8.setCellValue("Along Member");
                cell9.setCellValue("Cor_Transport");
                cell10.setCellValue("Drop Point");
                cell11.setCellValue("Created Date");





                cell.setCellStyle(headercs);
                 cell1.setCellStyle(headercs);
                cell2.setCellStyle(headercs);
                cell3.setCellStyle(headercs);
                cell4.setCellStyle(headercs);
                cell5.setCellStyle(headercs);
                cell6.setCellStyle(headercs);
                cell7.setCellStyle(headercs);
                cell8.setCellStyle(headercs);
                cell9.setCellStyle(headercs);
                cell10.setCellStyle(headercs);
                cell11.setCellStyle(headercs);

                int count = 1;

                if (finalList.size() > 0) {
                    Map stateHistorylMap = null;
                    for (int i = 0; i < finalList.size(); i++) {
                        stateHistorylMap = (Map) finalList.get(i);
                        row = sheet.createRow((short) count++);
                        cell = row.createCell((short) 0);

                        cell1 = row.createCell((short) 1);
                        cell2 = row.createCell((short) 2);
                        cell3 = row.createCell((short) 3);
                        cell4 = row.createCell((short) 4);
                        cell5 = row.createCell((short) 5);
                        cell6 = row.createCell((short) 6);
                        cell7 = row.createCell((short) 7);
                        cell8 = row.createCell((short) 8);
                        cell9 = row.createCell((short) 9);
                        cell10 = row.createCell((short) 10);
                        cell11 = row.createCell((short) 11);



                        cell.setCellValue((int) i+1);
                        cell1.setCellValue((String) stateHistorylMap.get("firstname"));
//                        HSSFCellStyle css1 = hssfworkbook.createCellStyle();
//                        HSSFCellStyle css2 = hssfworkbook.createCellStyle();
//                        css1.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
//                        css1.setAlignment(HSSFCellStyle.ALIGN_LEFT);
//                        css1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//                        css1.setBorderTop((short) 1); // single line border
//                        css1.setBorderBottom((short) 1); // single line border
//                        css1.setFont(timesBoldFont1);
//
//                        HSSFDataFormat df1 = hssfworkbook.
//                                createDataFormat();
//                        css1.setDataFormat(df1.getFormat("#,##0.0"));
//                        css2.setDataFormat(df1.getFormat("#,##0.0"));
//                        css2.setAlignment(HSSFCellStyle.ALIGN_LEFT);                        
//                        css2.setFont(timesBoldFont1);
                        cell2.setCellValue((String)stateHistorylMap.get("EventName"));                   
                        cell3.setCellValue((String) stateHistorylMap.get("email_id"));
                        cell4.setCellValue((String) stateHistorylMap.get("phone_no"));
                        cell5.setCellValue((String) stateHistorylMap.get("department"));
                        cell6.setCellValue((String) stateHistorylMap.get("location"));
                        cell7.setCellValue((String) stateHistorylMap.get("foodpref"));
                        cell8.setCellValue((String) stateHistorylMap.get("alongmember"));
                        cell9.setCellValue((String) stateHistorylMap.get("cor_transport"));
                        cell10.setCellValue((String) stateHistorylMap.get("DropPoint"));
                        cell11.setCellValue((String) stateHistorylMap.get("CreatedDate"));



                        if (count % 2 == 0) {
                            cell.setCellStyle(cellColor1);
                            cell1.setCellStyle(cellColor1);
                            cell2.setCellStyle(cellColor1);
                            cell3.setCellStyle(cellColor1);
                            cell4.setCellStyle(cellColor1);
                            cell5.setCellStyle(cellColor1);
                            cell6.setCellStyle(cellColor1);
                            cell7.setCellStyle(cellColor1);
                            cell8.setCellStyle(cellColor1);
                            cell9.setCellStyle(cellColor1);
                            cell10.setCellStyle(cellColor1);
                            cell11.setCellStyle(cellColor1);

                        } else {
                            cell.setCellStyle(cellColor);
                            cell1.setCellStyle(cellColor);
                            cell2.setCellStyle(cellColor);
                            cell3.setCellStyle(cellColor);
                            cell4.setCellStyle(cellColor);
                            cell5.setCellStyle(cellColor);
                            cell6.setCellStyle(cellColor);
                            cell7.setCellStyle(cellColor);
                            cell8.setCellStyle(cellColor);
                            cell9.setCellStyle(cellColor);
                            cell10.setCellStyle(cellColor);
                            cell11.setCellStyle(cellColor);
                        }
                    }
                    row = sheet.createRow((short) count++);
                    cell = row.createCell((short) 0);

                    cell1 = row.createCell((short) 1);
                    cell2 = row.createCell((short) 2);
                    cell3 = row.createCell((short) 3);
                    cell4 = row.createCell((short) 4);
                    cell5 = row.createCell((short) 5);
                    cell6 = row.createCell((short) 6);
                    cell7 = row.createCell((short) 7);
                    cell8 = row.createCell((short) 8);
                    cell9 = row.createCell((short) 9);
                    cell10 = row.createCell((short) 10);
                    cell11 = row.createCell((short) 11);

                    cell.setCellStyle(footercs);
                    cell1.setCellStyle(footercs);
                    cell2.setCellStyle(footercs);
                    cell3.setCellStyle(footercs);

                    cell4.setCellStyle(footercs);
                    cell5.setCellStyle(footercs);
                    cell6.setCellStyle(footercs);
                    cell7.setCellStyle(footercs);
                    cell8.setCellStyle(footercs);
                    cell9.setCellStyle(footercs);
                    cell10.setCellStyle(footercs);
                    cell11.setCellStyle(footercs);
                }
              
              

                HSSFDataFormat totalSumdf1 = hssfworkbook.createDataFormat(); 
                
                HSSFCellStyle test = hssfworkbook.createCellStyle();
                HSSFDataFormat testdf = hssfworkbook.createDataFormat();
               
                sheet.autoSizeColumn((int) 0);               
                sheet.autoSizeColumn((int) 2);
                sheet.autoSizeColumn((int) 4);
                sheet.autoSizeColumn((int) 6);
                sheet.autoSizeColumn((int) 7);
                sheet.autoSizeColumn((int) 8);
                sheet.autoSizeColumn((int) 10);
                sheet.autoSizeColumn((int) 11);
                sheet.setColumnWidth(1, 50 * 256);
                sheet.setColumnWidth(3, 35 * 256);
                sheet.setColumnWidth(5, 25 * 256);
                sheet.setColumnWidth(7, 25 * 256);
                sheet.setColumnWidth(9, 10 * 256);
                 BigDecimal bb,bc,cc,cd;
                 bb = new BigDecimal(totalAmount);
                 bc = bb.setScale(2, RoundingMode.CEILING);  
                 cc = new BigDecimal(totalOpprtunity);
                 cd = cc.setScale(2, RoundingMode.CEILING); 
               
             
                
                
                

                ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
hssfworkbook.write(outByteStream);
byte [] outArray = outByteStream.toByteArray();
httpServletResponse.setContentType("application/ms-excel");
httpServletResponse.setContentLength(outArray.length);
httpServletResponse.setHeader("Expires:", "0"); // eliminates browser caching


httpServletResponse.setHeader("Content-Disposition", "attachment; filename=QmeetAttendees.xls");
OutputStream outStream = httpServletResponse.getOutputStream();
outStream.write(outArray);
outStream.flush();
outStream.close();


            }
              }
               
                
                

               


            }  catch (IOException ex) {
              //  ex.printStackTrace();
            } finally {
                try {
                   
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (outputStream != null) {
                        outputStream.close();
                    }

                } catch (IOException ex) {
                   // ex.printStackTrace();
                }
            }




        }//Close Session Checking
        resultType = SUCCESS;
        return resultType;
    }
    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    /**
     * @return the resultType
     */
    public String getResultType() {
        return resultType;
    }

    /**
     * @param resultType the resultType to set
     */
    public void setResultType(String resultType) {
        this.resultType = resultType;
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
     * @return the timeList
     */
    public List getTimeList() {
        return timeList;
    }

    /**
     * @param timeList the timeList to set
     */
    public void setTimeList(List timeList) {
        this.timeList = timeList;
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
     * @return the eventId
     */
    public String getEventId() {
        return eventId;
    }

    /**
     * @param eventId the eventId to set
     */
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    /**
     * @return the eventSearchType
     */
    public String getEventSearchType() {
        return eventSearchType;
    }

    /**
     * @param eventSearchType the eventSearchType to set
     */
    public void setEventSearchType(String eventSearchType) {
        this.eventSearchType = eventSearchType;
    }

    /**
     * @return the primarySpeakerExist
     */
    public String getPrimarySpeakerExist() {
        return primarySpeakerExist;
    }

    /**
     * @param primarySpeakerExist the primarySpeakerExist to set
     */
    public void setPrimarySpeakerExist(String primarySpeakerExist) {
        this.primarySpeakerExist = primarySpeakerExist;
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
     * @return the trackNamesList
     */
    public List getTrackNamesList() {
        return trackNamesList;
    }

    /**
     * @param trackNamesList the trackNamesList to set
     */
    public void setTrackNamesList(List trackNamesList) {
        this.trackNamesList = trackNamesList;
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
     * @return the qmeetYear
     */
    public String getQmeetYear() {
        return qmeetYear;
    }

    /**
     * @param qmeetYear the qmeetYear to set
     */
    public void setQmeetYear(String qmeetYear) {
        this.qmeetYear = qmeetYear;
    }

    /**
     * @return the qmeetMap
     */
    public Map getQmeetMap() {
        return qmeetMap;
    }

    /**
     * @param qmeetMap the qmeetMap to set
     */
    public void setQmeetMap(Map qmeetMap) {
        this.qmeetMap = qmeetMap;
    }

    /**
     * @return the qmeetTitleId
     */
    public String getQmeetTitleId() {
        return qmeetTitleId;
    }

    /**
     * @param qmeetTitleId the qmeetTitleId to set
     */
    public void setQmeetTitleId(String qmeetTitleId) {
        this.qmeetTitleId = qmeetTitleId;
    }

    /**
     * @return the associatedEventMap
     */
    public Map getAssociatedEventMap() {
        return associatedEventMap;
    }

    /**
     * @param associatedEventMap the associatedEventMap to set
     */
    public void setAssociatedEventMap(Map associatedEventMap) {
        this.associatedEventMap = associatedEventMap;
    }

    /**
     * @return the dateOfPublish
     */
    public String getDateOfPublish() {
        return dateOfPublish;
    }

    /**
     * @param dateOfPublish the dateOfPublish to set
     */
    public void setDateOfPublish(String dateOfPublish) {
        this.dateOfPublish = dateOfPublish;
    }

    /**
     * @return the searchSeriesTitle
     */
    public String getSearchSeriesTitle() {
        return searchSeriesTitle;
    }

    /**
     * @param searchSeriesTitle the searchSeriesTitle to set
     */
    public void setSearchSeriesTitle(String searchSeriesTitle) {
        this.searchSeriesTitle = searchSeriesTitle;
    }

    /**
     * @return the searchSeriesStatus
     */
    public String getSearchSeriesStatus() {
        return searchSeriesStatus;
    }

    /**
     * @param searchSeriesStatus the searchSeriesStatus to set
     */
    public void setSearchSeriesStatus(String searchSeriesStatus) {
        this.searchSeriesStatus = searchSeriesStatus;
    }

    /**
     * @return the eventType
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * @param eventType the eventType to set
     */
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    /**
     * @return the searchSeriesTrack
     */
    public String getSearchSeriesTrack() {
        return searchSeriesTrack;
    }

    /**
     * @param searchSeriesTrack the searchSeriesTrack to set
     */
    public void setSearchSeriesTrack(String searchSeriesTrack) {
        this.searchSeriesTrack = searchSeriesTrack;
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
     * @return the unassociatedEventMap
     */
    public Map getUnassociatedEventMap() {
        return unassociatedEventMap;
    }

    /**
     * @param unassociatedEventMap the unassociatedEventMap to set
     */
    public void setUnassociatedEventMap(Map unassociatedEventMap) {
        this.unassociatedEventMap = unassociatedEventMap;
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
     * @return the primarySpeakerId
     */
    public String getPrimarySpeakerId() {
        return primarySpeakerId;
    }

    /**
     * @param primarySpeakerId the primarySpeakerId to set
     */
    public void setPrimarySpeakerId(String primarySpeakerId) {
        this.primarySpeakerId = primarySpeakerId;
    }

    /**
     * @return the eventSpeakerMap
     */
    public Map getEventSpeakerMap() {
        return eventSpeakerMap;
    }

    /**
     * @param eventSpeakerMap the eventSpeakerMap to set
     */
    public void setEventSpeakerMap(Map eventSpeakerMap) {
        this.eventSpeakerMap = eventSpeakerMap;
    }

    /**
     * @return the speakersMapExceptEventSpeakerMap
     */
    public Map getSpeakersMapExceptEventSpeakerMap() {
        return speakersMapExceptEventSpeakerMap;
    }

    /**
     * @param speakersMapExceptEventSpeakerMap the speakersMapExceptEventSpeakerMap to set
     */
    public void setSpeakersMapExceptEventSpeakerMap(Map speakersMapExceptEventSpeakerMap) {
        this.speakersMapExceptEventSpeakerMap = speakersMapExceptEventSpeakerMap;
    }

    /**
     * @return the speakersParameters
     */
    public Map getSpeakersParameters() {
        return speakersParameters;
    }

    /**
     * @param speakersParameters the speakersParameters to set
     */
    public void setSpeakersParameters(Map speakersParameters) {
        this.speakersParameters = speakersParameters;
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
     * @return the trackNamesMap
     */
    public Map getTrackNamesMap() {
        return trackNamesMap;
    }

    /**
     * @param trackNamesMap the trackNamesMap to set
     */
    public void setTrackNamesMap(Map trackNamesMap) {
        this.trackNamesMap = trackNamesMap;
    }

    /**
     * @return the userRoleId
     */
    public int getUserRoleId() {
        return userRoleId;
    }

    /**
     * @param userRoleId the userRoleId to set
     */
    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
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
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
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
     * @return the ieeCreatedDateFrom
     */
    public String getIeeCreatedDateFrom() {
        return ieeCreatedDateFrom;
    }

    /**
     * @param ieeCreatedDateFrom the ieeCreatedDateFrom to set
     */
    public void setIeeCreatedDateFrom(String ieeCreatedDateFrom) {
        this.ieeCreatedDateFrom = ieeCreatedDateFrom;
    }

    /**
     * @return the internalWebinarCreatedDateFrom
     */
    public String getInternalWebinarCreatedDateFrom() {
        return internalWebinarCreatedDateFrom;
    }

    /**
     * @param internalWebinarCreatedDateFrom the internalWebinarCreatedDateFrom to set
     */
    public void setInternalWebinarCreatedDateFrom(String internalWebinarCreatedDateFrom) {
        this.internalWebinarCreatedDateFrom = internalWebinarCreatedDateFrom;
    }

    /**
     * @return the internalWebinarCreatedDateTo
     */
    public String getInternalWebinarCreatedDateTo() {
        return internalWebinarCreatedDateTo;
    }

    /**
     * @param internalWebinarCreatedDateTo the internalWebinarCreatedDateTo to set
     */
    public void setInternalWebinarCreatedDateTo(String internalWebinarCreatedDateTo) {
        this.internalWebinarCreatedDateTo = internalWebinarCreatedDateTo;
    }

    /**
     * @return the internalWebinarTitle
     */
    public String getInternalWebinarTitle() {
        return internalWebinarTitle;
    }

    /**
     * @param internalWebinarTitle the internalWebinarTitle to set
     */
    public void setInternalWebinarTitle(String internalWebinarTitle) {
        this.internalWebinarTitle = internalWebinarTitle;
    }

    /**
     * @return the internalWebinarStatus
     */
    public String getInternalWebinarStatus() {
        return internalWebinarStatus;
    }

    /**
     * @param internalWebinarStatus the internalWebinarStatus to set
     */
    public void setInternalWebinarStatus(String internalWebinarStatus) {
        this.internalWebinarStatus = internalWebinarStatus;
    }

    /**
     * @return the createdDateFromConference
     */
    public String getCreatedDateFromConference() {
        return createdDateFromConference;
    }

    /**
     * @param createdDateFromConference the createdDateFromConference to set
     */
    public void setCreatedDateFromConference(String createdDateFromConference) {
        this.createdDateFromConference = createdDateFromConference;
    }

    /**
     * @return the createdDateToConference
     */
    public String getCreatedDateToConference() {
        return createdDateToConference;
    }

    /**
     * @param createdDateToConference the createdDateToConference to set
     */
    public void setCreatedDateToConference(String createdDateToConference) {
        this.createdDateToConference = createdDateToConference;
    }

    /**
     * @return the titleConference
     */
    public String getTitleConference() {
        return titleConference;
    }

    /**
     * @param titleConference the titleConference to set
     */
    public void setTitleConference(String titleConference) {
        this.titleConference = titleConference;
    }

    /**
     * @return the statusConference
     */
    public String getStatusConference() {
        return statusConference;
    }

    /**
     * @param statusConference the statusConference to set
     */
    public void setStatusConference(String statusConference) {
        this.statusConference = statusConference;
    }

    /**
     * @return the activeEmployeeMap
     */
    public Map getActiveEmployeeMap() {
        return activeEmployeeMap;
    }

    /**
     * @param activeEmployeeMap the activeEmployeeMap to set
     */
    public void setActiveEmployeeMap(Map activeEmployeeMap) {
        this.activeEmployeeMap = activeEmployeeMap;
    }

    /**
     * @return the accessTypeList
     */
    public List getAccessTypeList() {
        return accessTypeList;
    }

    /**
     * @param accessTypeList the accessTypeList to set
     */
    public void setAccessTypeList(List accessTypeList) {
        this.accessTypeList = accessTypeList;
    }

    /**
     * @return the emeetAccessType
     */
    public String getEmeetAccessType() {
        return emeetAccessType;
    }

    /**
     * @param emeetAccessType the emeetAccessType to set
     */
    public void setEmeetAccessType(String emeetAccessType) {
        this.emeetAccessType = emeetAccessType;
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
     * @return the emeetMeetingAccessStatus
     */
    public String getEmeetMeetingAccessStatus() {
        return emeetMeetingAccessStatus;
    }

    /**
     * @param emeetMeetingAccessStatus the emeetMeetingAccessStatus to set
     */
    public void setEmeetMeetingAccessStatus(String emeetMeetingAccessStatus) {
        this.emeetMeetingAccessStatus = emeetMeetingAccessStatus;
    }

    /**
     * @return the emeetStatusByDate
     */
    public String getEmeetStatusByDate() {
        return emeetStatusByDate;
    }

    /**
     * @param emeetStatusByDate the emeetStatusByDate to set
     */
    public void setEmeetStatusByDate(String emeetStatusByDate) {
        this.emeetStatusByDate = emeetStatusByDate;
    }

    /**
     * @return the executiveMeetMonthSearch
     */
    public String getExecutiveMeetMonthSearch() {
        return executiveMeetMonthSearch;
    }

    /**
     * @param executiveMeetMonthSearch the executiveMeetMonthSearch to set
     */
    public void setExecutiveMeetMonthSearch(String executiveMeetMonthSearch) {
        this.executiveMeetMonthSearch = executiveMeetMonthSearch;
    }

    /**
     * @return the emeetType
     */
    public String getEmeetType() {
        return emeetType;
    }

    /**
     * @param emeetType the emeetType to set
     */
    public void setEmeetType(String emeetType) {
        this.emeetType = emeetType;
    }

    /**
     * @return the emeetToDate
     */
    public String getEmeetToDate() {
        return emeetToDate;
    }

    /**
     * @param emeetToDate the emeetToDate to set
     */
    public void setEmeetToDate(String emeetToDate) {
        this.emeetToDate = emeetToDate;
    }

    /**
     * @return the emeetFromDate
     */
    public String getEmeetFromDate() {
        return emeetFromDate;
    }

    /**
     * @param emeetFromDate the emeetFromDate to set
     */
    public void setEmeetFromDate(String emeetFromDate) {
        this.emeetFromDate = emeetFromDate;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return the ieeEventSearchType
     */
    public String getIeeEventSearchType() {
        return ieeEventSearchType;
    }

    /**
     * @param ieeEventSearchType the ieeEventSearchType to set
     */
    public void setIeeEventSearchType(String ieeEventSearchType) {
        this.ieeEventSearchType = ieeEventSearchType;
    }

    /**
     * @return the ieeCreatedDateTo
     */
    public String getIeeCreatedDateTo() {
        return ieeCreatedDateTo;
    }

    /**
     * @param ieeCreatedDateTo the ieeCreatedDateTo to set
     */
    public void setIeeCreatedDateTo(String ieeCreatedDateTo) {
        this.ieeCreatedDateTo = ieeCreatedDateTo;
    }

    /**
     * @return the ieeTitle
     */
    public String getIeeTitle() {
        return ieeTitle;
    }

    /**
     * @param ieeTitle the ieeTitle to set
     */
    public void setIeeTitle(String ieeTitle) {
        this.ieeTitle = ieeTitle;
    }

    /**
     * @return the ieeStatus
     */
    public String getIeeStatus() {
        return ieeStatus;
    }

    /**
     * @param ieeStatus the ieeStatus to set
     */
    public void setIeeStatus(String ieeStatus) {
        this.ieeStatus = ieeStatus;
    }

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }
}
