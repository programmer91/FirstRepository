/*
 * CreRegistrationAction.java
 *
 * Created on August 22, 2013, 1:53 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.cre;

import com.itextpdf.text.pdf.codec.Base64;
import com.mss.mirage.util.MailManager;
import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.HibernateDataProvider;
import com.mss.mirage.util.DefaultDataProvider;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.PasswordUtility;
import com.mss.mirage.util.ServiceLocator;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.List;
import java.util.Random;
import com.mss.mirage.util.HibernateServiceLocator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mss.mirage.ecertification.QuestionsVTO;
import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.Properties;
import com.mss.mirage.util.ServiceLocatorException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import javax.servlet.ServletException;
import org.apache.struts2.interceptor.ServletResponseAware;
/**
 *
 * @author miracle
 */
//public class CreAction extends ActionSupport implements ServletRequestAware{
public class CreAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
    
    /*@param resultMessage to store message and that will display in login page*/
    private String resultMessage;
    private String hrResultMessage;
    private String vpResultMessage;
    
    /*@param httpServletRequest to set resultMessage and that will get in login page*/
    private HttpServletRequest httpServletRequest;
    
    /*@param resultType to store type of results means login or success or failure etc...*/
    private String resultType;
    
    
    
    /*@param genderList is used to store applocation level constants of gender*/
    private List genderList = new ArrayList();
    
    /*@param maritalStatusList is used to store applicationn level constants of maritalstatus valus*/
    private List maritalStatusList = new ArrayList();
    
    /*@param countryList is used to store application level constants of Country*/
    private List coutryList = new ArrayList();
    
    /*@param prefferedQuestionMap is used to store application level constatns of prefferedQuestions */
    private Map prefferedQuestionsMap;
    
    private DefaultDataProvider defaultDataProvider;
    
    private String consultantId;
    private String consultantName;
    private String status;
    private String level;
    
    
            /**
             *  domainName
             *  topicName
             *  totalQuest
             *  durationTime
             *  insTopicId
             */
        private String domainName;
        private String topicName;
        private int totalQuest;
        private int durationTime;
        private int insTopicId;
        private int insdomainId;
        
        private Map questionsVtoMap = null;
        private int hideremainingQuestions;
        private int attemptedQuestions;
        private String examStatus;
        private int examMarks;
        private List examDetailInfoList;
        
        private DataSourceDataProvider dataSourceDataProvider;
        /***
         * Variable used to edit the consultent details
         *
         *
         *
         */
    private int id;
    private int attendingInterviewAt;
    private int category;
    private String pan;
    private String fatherName;
    private String fatherOccupation;
    private String fatherIncome;
    private String address;
    private String city;
    private String  pin;
    private String state;
    private String qualification;
    private String education;
    private String college;
    private String medium;
    private String yearOfPass;
    private String percentage;
    private String ipeCategory;
    private String ipeStream;
    private String ipeCollege;
    private String ipeMedium;
    private String ipeYearOfPass;
    private String ipePercentage;
    private String degreeQual;
    private String degreeBranch;
    private String degreeCollege;
    private String degreeMedium;
    private String degreeYearOfPass;
    private String degreePercentage;
    private String pgQual;
    private String pgStream;
    private String pgCollege;
    private String pgMedium;
    private String pgYearOfPass;
    private String pgPercentage;
    private String addInfo;
    private String skill1;
    private String scale1;
    private String skill2;
    private String scale2;
    private String skill3;
    private String scale3;
    private String skill4;
    private String scale4;
    private String skill5;
    private String scale5;
    private String skill6;
    private String scale6;
    private String company;
    private String designation;
    private Date fromDate;
    private Date toDate;
    private String location;
    private String company1;
    private String designation1;
    private Date fromDate1;
    private Date toDate1;
    private String location1;
    private String company2;
    private String designation2;
    private Date fromDate2;
    private Date toDate2;
    private String location2;
    private String refName;
    private String refEmail;
    private String refMobile;
    
    private int campusLocation;
    private String recLocation;
    private String jfairLocation;
    
    
        /*@param firstName to store firstname of the user*/
    private String firstName;
    
    /*@param lastName to store lastname of the user*/
    private String lastName;
    
    /*@param middleName to store middlename of the user*/
    private String middleName;
    
    /*@param gender to store gender of the user*/
    private String gender;
    
    /*@param maritalStatus to store  maritalstatus of the user*/
    private String maritalStatus;
    
       /*@param birthDate to store the date of birth of user*/
    private Date birthDate;
    
    /*@param cellPhoneNo to store mobile number of the user*/
    private String cellPhoneNo;
    
    /*@param altPhoneNo to store alternative phone number of the user*/
    private String altPhoneNo;
    
     /*@param otherEmail to store other email of the user*/
    private String otherEmail;
    
    /*@param personalEmail to store personal email of the user*/
    private String personalEmail;
    
   
    /**
     *  END of consultent details
     *
     */
    
     /* 
     *Tech lead Review Properties Start
     */
    private int techReviewId;
    private int navId;
    private String techReviewStatus;
    private String techCreatedDate;
    private String techLead;
    private Collection currentTechReviewCollection;
    private String techLeadComments;
    private String createdBy;
    private CreReviewVTO creReviewVTO;
    private int tempVar;
    private Collection currentHrReviewCollection;
    private String hrReviewStatus;
    private String hrComments;
    private String hrCreatedDate;
    private String hrLoginId;
    private int hrReviewId;
    private int tempHrVar;
    private int hrNavId;
    private String vpReviewStatus;
    private String vpComments;
    private String vpCreatedDate;
    private String vpLoginId;
    private int vpReviewId;
    private int tempVpVar;
    private int vpNavId;
    private Collection currentVpReviewCollection;
    private String officeEmail;
    String queryString = null;
    private Collection currentExamReviewCollection;
    private int totalQues;
    private int totDuration;
    private int minMarks;
    private Map subTopicsMap;
    
    
    private String interviewAt;
    //New fields for new Cre
    private String currentAction;
	private List subtopics1; 
	private String examType;
	private int subtopicId1;
	private Map examTypeMap;
    
        //Added By santosh
        

private int examNumber;
private String isLastExam;
private Collection currentQuestionsCollection;

    private HttpServletResponse httpServletResponse;
  private File imagePath = null;
  
  // New fields for Onboard comments Date : 08/15/2014
   private String onboardComments;
    private String onboardCreatedDate;
    private String onboardLoginId;
    private int onboardReviewId;
    private int tempOnBoardVar;
    private int onBoardNavId;
    private Collection currentOnboardReviewCollection;
    private String onboardResultMessage;
     private List recLocationList;
     private Map postAppliedMap;
     
     
     private String startDate;
     private String endDate;
     
     
 private String interviewAtConsultantReport;
 private String email;
private String mydata;

private String consultentId;

    /** Creates a new instance of CreRegistrationAction */
    public CreAction() {
    }
    public String prepare()throws Exception{

    setDefaultDataProvider(DefaultDataProvider.getInstance());

    setGenderList(getDefaultDataProvider().getGender(ApplicationConstants.GENDER_OPTIONS));
    setMaritalStatusList(getDefaultDataProvider().getMaritalStatus(ApplicationConstants.MARITAL_STATUS_OPTIONS));
    setCoutryList(HibernateDataProvider.getInstance().getContries(ApplicationConstants.COUNTRY_OPTIONS));
setRecLocationList(DataSourceDataProvider.getInstance().getCollegeList());
setPostAppliedMap(DataSourceDataProvider.getInstance().getCrePositions());
 
    return SUCCESS;
    }

    public String creConsultantList() throws Exception{
    resultType = LOGIN;
    
    try {

    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
         queryString = "SELECT Id,ConsultentId,CONCAT(LName,'.',FName) AS ConsultentName,STATUS,Level FROM tblCreConsultentDetails WHERE STATUS!='Joined'";
          // httpServletRequest.setAttribute(ApplicationConstants.QUERY_STRING, queryString);
            
          //  System.out.println("Query-->"+queryString);
         setExamTypeMap(DataSourceDataProvider.getInstance().getExamTypeMap()); 
            setRecLocationList(DataSourceDataProvider.getInstance().getCollegeList());
            if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QUERY_STRING)!=null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QUERY_STRING);
                    }
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QUERY_STRING,queryString);
                     //httpServletRequest.getSession(true).setAttribute("query","2");
            resultType = SUCCESS;
            
        }//Closing Session checking
    } catch(Exception ex){
        //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
        httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
        resultType =  ERROR;
    }
    return resultType;
    }  

    /**
     * * @return
     * @throws Exception 
     */
 public String getCreBackToList(){
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
           // userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            
            resultType = "accessFailed";
         //   if(AuthorizationManager.getInstance().isAuthorizedUser("GET_CRM_BACKLIST",userRoleId)){
                try{
                 //   String actionName = (String)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ACC_SEARCH_ACTION);
                    
                     httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_EXAM_LIST,queryString);
                  //   httpServletRequest.getSession(true).setAttribute("query","2");
                    setExamTypeMap(DataSourceDataProvider.getInstance().getExamTypeMap()); 
                    setRecLocationList(DataSourceDataProvider.getInstance().getCollegeList());
                    resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
          //  }//END-Authorization Checking
        }//Close Session Checking
      //  prepare();
        return resultType;
    }
        
    public String searchCreConsultants() throws Exception{
    resultType = LOGIN;
    StringBuffer queryString = new StringBuffer();
    try {

    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
         queryString.append("SELECT Id,ConsultentId,CONCAT(LName,'.',FName) AS ConsultentName,STATUS,Level FROM tblCreConsultentDetails WHERE STATUS!='Joined' ");
         if(getConsultantId()!=null && !"".equals(getConsultantId())) {
            queryString.append("And ConsultentId LIKE '"+getConsultantId()+"%' ");
         }
          if(getConsultantName()!=null && !"".equals(getConsultantName())) {
            queryString.append("And (FName LIKE '%"+getConsultantName()+"%' OR LName LIKE '%"+getConsultantName()+"%' OR MName LIKE '%"+getConsultantName()+"%') ");
         }
         if(getStatus()!=null && !"".equals(getStatus())) {
            queryString.append("And STATUS = '"+getStatus()+"' ");
         }
          if(getLevel()!=null && !"".equals(getLevel())) {
            queryString.append("And Level = '"+getLevel()+"' ");
         }
          if(getInterviewAt()!=null && !"".equals(getInterviewAt())) {
            queryString.append("And AttendedAt = "+getInterviewAt()+" ");
         }
           if(getRecLocation()!=null && !"".equals(getRecLocation())) {
            queryString.append("And recLocation = '"+getRecLocation()+"' ");
         }if(getEmail()!=null)
                queryString.append(" And (Email1 LIKE '%"+getEmail()+"%' OR Email2 LIKE '%"+getEmail()+"%') ");
				
        // System.out.println("Query-->"+queryString.toString());
 
            if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QUERY_STRING)!=null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QUERY_STRING);
                    }
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QUERY_STRING,queryString.toString());
           //httpServletRequest.setAttribute(ApplicationConstants.QUERY_STRING, queryString.toString());
           setConsultantName(getConsultantName());
           setConsultantId(getConsultantId());
           setStatus(getStatus());
           setLevel(getLevel());
           setRecLocationList(DataSourceDataProvider.getInstance().getCollegeList());
           setExamTypeMap(DataSourceDataProvider.getInstance().getExamTypeMap()); 
            resultType = SUCCESS;
        }//Closing Session checking
    } catch(Exception ex){
        //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
        httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
        resultType =  ERROR;
    }


    return resultType;
    } 
     
     
     /**
      *  To get Questions from exams list
      *  
      */
    /* 
    public String startExam() throws Exception {
        try{
            resultType = LOGIN;
            
            if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
              
               
                
                setDomainName("CRE");
                setTopicName("CRE");
             setCreReviewVTO(ServiceLocator.getCreService().getExamPatternDetails(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString()));
                
                
               
               setTotalQuest(getCreReviewVTO().getTotalQues());
               
               setDurationTime(getCreReviewVTO().getTotDuration());
              
             
                httpServletRequest.getSession(false).setAttribute(ApplicationConstants.SESSION_CRE_MIN_MARKS,getCreReviewVTO().getMinMarks());
              
              setInsTopicId(Integer.parseInt(Properties.getProperty("CRE.TopicId")));
              setInsdomainId(Integer.parseInt(Properties.getProperty("CRE.DomainId")));
           
               if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ECERT_QUESTIONS_MAP)!=null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.ECERT_QUESTIONS_MAP);
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.ECERT_QUESTIONS_MAP,null);
                    }
            if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ECERT_CURRENT_EXAM_KEY)!=null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.ECERT_CURRENT_EXAM_KEY);
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.ECERT_CURRENT_EXAM_KEY,null);
                    }
               questionsVtoMap = null;
               int maxExamKey = 0; 
                
               maxExamKey =  ServiceLocator.getCreService().insertExamKey(getInsdomainId(),getInsTopicId(),httpServletRequest);
               
               httpServletRequest.getSession(false).setAttribute(ApplicationConstants.ECERT_CURRENT_EXAM_KEY,maxExamKey);
              
               
                
               questionsVtoMap = ServiceLocator.getCreService().getQuestions(httpServletRequest,maxExamKey,getTotalQuest());
               
               httpServletRequest.getSession(false).setAttribute(ApplicationConstants.ECERT_QUESTIONS_MAP,questionsVtoMap);
               
               
            
               
               resultType = SUCCESS;
            }
        } catch(Exception ex){
            System.out.println("ExamkeyError-->"+ex.getMessage());
            
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.getMessage());
            resultType =  ERROR;
        }
        
        return resultType;
    }

*/
   /* public String startExam() throws Exception {
        try{
            resultType = LOGIN;
            //ApplicationConstants.SES_CONSULTANT_ID
            if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
              
               
                              setInsTopicId(Integer.parseInt(Properties.getProperty("CRE.TopicId")));
                              setInsdomainId(Integer.parseInt(Properties.getProperty("CRE.DomainId")));
                setDomainName("CRE");
                setTopicName("CRE");
                int creId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
                String creLoginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                
                setCreReviewVTO(ServiceLocator.getCreService().doExamProcess(creId, creLoginId,getInsdomainId(),getInsTopicId()));
                
                
            // setCreReviewVTO(ServiceLocator.getCreService().getExamPatternDetails(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString()));
                //System.out.println("11111111111111111111111----------------------Stsrt exa----------------->"+getTotalQuest());
                
               //setTotalQuest(Integer.parseInt(Properties.getProperty("CRE.TotalQuestion")));
               setTotalQuest(getCreReviewVTO().getTotalQues());
               // setDurationTime(Integer.parseInt(Properties.getProperty("CRE.DurationOfTime")));
               setDurationTime(getCreReviewVTO().getTotDuration());
              // SESSION_CRE_MIN_MARKS
             
                httpServletRequest.getSession(false).setAttribute(ApplicationConstants.SESSION_CRE_MIN_MARKS,getCreReviewVTO().getMinMarks());
                //setInsdomainId(5);
                //setInsTopicId(25);

           
               if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ECERT_QUESTIONS_MAP)!=null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.ECERT_QUESTIONS_MAP);
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.ECERT_QUESTIONS_MAP,null);
                    }
            if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ECERT_CURRENT_EXAM_KEY)!=null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.ECERT_CURRENT_EXAM_KEY);
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.ECERT_CURRENT_EXAM_KEY,null);
                    }
               questionsVtoMap = null;
               //int maxExamKey = 0; 
                
               //maxExamKey =  ServiceLocator.getCreService().insertExamKey(getInsdomainId(),getInsTopicId(),httpServletRequest);
               
               httpServletRequest.getSession(false).setAttribute(ApplicationConstants.ECERT_CURRENT_EXAM_KEY,getCreReviewVTO().getExamKey());
              
               // System.out.println("maxExamKey------>"+maxExamKey);
                
               //questionsVtoMap = ServiceLocator.getCreService().getQuestions(httpServletRequest,maxExamKey,getTotalQuest());
               questionsVtoMap = ServiceLocator.getCreService().getCreQuestions(getCreReviewVTO().getExamKey());
               httpServletRequest.getSession(false).setAttribute(ApplicationConstants.ECERT_QUESTIONS_MAP,questionsVtoMap);
               
               
            //  System.out.println("Map length------>"+questionsVtoMap.size());
               
               resultType = SUCCESS;
            }//Closing Session checking
        } catch(Exception ex){
            System.out.println("ExamkeyError-->"+ex.getMessage());
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.getMessage());
            resultType =  ERROR;
        }
        
        return resultType;
    }
*/
    /*
     * Modified By Santosh Kola
     * Modified Date : 03/06/2014
     */
    public String startExam() throws Exception {
        try{
            resultType = LOGIN;
            //ApplicationConstants.SES_CONSULTANT_ID
            if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
              
                setInsTopicId(Integer.parseInt(Properties.getProperty("CRE.TopicId")));
                setInsdomainId(Integer.parseInt(Properties.getProperty("CRE.DomainId")));
                setDomainName("CRE");
                setTopicName("CRE");
                
                int creId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
                String creLoginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                List examsList = (List)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CRE_ExamIds_List);
                CreReviewVTO creReviewVTO = (CreReviewVTO)examsList.get(getExamNumber());
                int examTypeId = creReviewVTO.getExamTypeId();
                setExamType(creReviewVTO.getExamTypeName());
                setCreReviewVTO(ServiceLocator.getCreService().doStartExam(creId,examTypeId));
                setTotalQuest(getCreReviewVTO().getTotalQues());
                setDurationTime(getCreReviewVTO().getTotDuration());
                httpServletRequest.getSession(false).setAttribute(ApplicationConstants.SESSION_CRE_MIN_MARKS,getCreReviewVTO().getMinMarks());
                
               if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ECERT_QUESTIONS_MAP)!=null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.ECERT_QUESTIONS_MAP);
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.ECERT_QUESTIONS_MAP,null);
                    }
            if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ECERT_CURRENT_EXAM_KEY)!=null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.ECERT_CURRENT_EXAM_KEY);
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.ECERT_CURRENT_EXAM_KEY,null);
                    }
           
             httpServletRequest.getSession(false).setAttribute(ApplicationConstants.ECERT_CURRENT_EXAM_KEY,getCreReviewVTO().getExamKey());
              httpServletRequest.getSession(false).setAttribute(ApplicationConstants.ECERT_QUESTIONS_MAP,getCreReviewVTO().getQuestionVtoMap()); 
               setExamNumber(getExamNumber()+1);
               setCurrentQuestionsCollection(getCreReviewVTO().getQuestionVtoMap().values());
               
               resultType = SUCCESS;
            }//Closing Session checking
        } catch(Exception ex){
            System.out.println("ExamkeyError-->"+ex.getMessage());
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.getMessage());
            resultType =  ERROR;
        }
        
        return resultType;
    }

     
     /**
      *  To get final result of an exam
      * 
      *
      */
     
     
      /*
    *Method Do Submit Exam and prints the Result
    *
    *Date : 07/23/2013
    */
    /*
   public String submitExam() throws Exception {
        try{
            resultType = LOGIN;
            if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
                
                String ConsultantId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
              QuestionsVTO questionVTO = null; 
              int attemptedQuestionsResult = 0;
              int examKey = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ECERT_CURRENT_EXAM_KEY).toString());
              int empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
              String EmpLoginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
             
                  int attemptedQuestions = getTotalQuest()-getHideremainingQuestions();
                //  System.out.println("Attempted Questions-->"+attemptedQuestions);
                  
                  int result = 0;
                  
                  result = ServiceLocator.getEcertificationService().getResult(examKey);
                 // System.out.println("Before getting min marks"+getInsTopicId());
                  
                 // int minMarks  = DataSourceDataProvider.getInstance().getExamMinMarks(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ECERT_VALIDATE_KEY).toString());
                  //int minMarks = Integer.parseInt(Properties.getProperty("CRE.TotalQuestion"));
                   int minMarks = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CRE_MIN_MARKS).toString());
                   setMinMarks(minMarks);
            String examStatus = null;
            if(result<minMarks) {
                examStatus = "FAIL";
            }else {
               examStatus = "PASS"; 
            }
           
           // (int examKeyId,int empId,int totalQuest, int attemptedQuest, int marks,String examStatus)
           // System.out.println("Before insert exam result");
            int isInsertExamResult = ServiceLocator.getCreService().insertEmpExamResult(examKey,EmpLoginId,getTotalQuest(),attemptedQuestions,result,examStatus);
           // System.out.println("Before insert exam result-----------------------"+isInsertExamResult);
                
//            setDomainName(getDomainName());
//            setTopicName(getTopicName());
//            setTotalQuest(getTotalQuest());
//            setAttemptedQuestions(attemptedQuestions);
//            setExamStatus(examStatus);
//            setExamMarks(result);
            
        
            
          //  DataSourceDataProvider.getInstance().isQuestionAttempt(examKeyId,qId);
            Map questionVtoMap = (Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ECERT_QUESTIONS_MAP);
            
            Iterator iterator = questionVtoMap.entrySet().iterator();
            int i=0;
                while (iterator.hasNext()) {
                        Map.Entry mapEntry = (Map.Entry) iterator.next();
                       // System.out.println("The key is: " + mapEntry.getKey() + ",value is :" + mapEntry.getValue());
                        
                        questionVTO = (QuestionsVTO)questionVtoMap.get(mapEntry.getKey());
                        
                        int ExamQuestionId = questionVTO.getId();
                        attemptedQuestionsResult = DataSourceDataProvider.getInstance().isQuestionAttempt(examKey,ExamQuestionId);
                        
                        if(attemptedQuestionsResult == 0){
                            ServiceLocator.getAjaxHandlerService().insertAnswer(ExamQuestionId,0,empId,examKey,questionVTO.getSubtopicId());
                            
                            i++;
                        }       
                }
            
              ServiceLocator.getCreService().getCreDetailResult(examKey,this);
              
              //ServiceLocator.getCreService().updateCREConsultantStatus(empId,"Process","Exam","");
              ServiceLocator.getCreService().updateCREExamStatus(empId,examStatus,"Exam");
            
            if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ECERT_VALIDATE_KEY)!=null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.ECERT_VALIDATE_KEY);
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.ECERT_VALIDATE_KEY,null);
                    }
             if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ECERT_QUESTIONS_MAP)!=null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.ECERT_QUESTIONS_MAP);
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.ECERT_QUESTIONS_MAP,null);
                    }
            if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ECERT_CURRENT_EXAM_KEY)!=null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.ECERT_CURRENT_EXAM_KEY);
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.ECERT_CURRENT_EXAM_KEY,null);
                    }
               resultType = SUCCESS;
            }//Closing Session checking
        } catch(Exception ex){
            System.out.println("ExamkeyError-->"+ex.getMessage());
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.getMessage());
            resultType =  ERROR;
        }
        
        return resultType;
    }
*/
    /*
    *Method Do Submit Exam and prints the Result
    *
    *Date : 07/23/2013
    * Modified By Santosh Kola
    * Modified Date : 03/06/2014
    */
  public String submitExam() throws Exception {
        try{
            resultType = LOGIN;
            if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
                
                String ConsultantId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
              QuestionsVTO questionVTO = null; 
              int attemptedQuestionsResult = 0;
              int examKey = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ECERT_CURRENT_EXAM_KEY).toString());
              int empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
              String EmpLoginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
             
                 // int attemptedQuestions = getTotalQuest()-getHideremainingQuestions();
               int noOfAttempted = 0;
                   int minMarks = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CRE_MIN_MARKS).toString());
                   setMinMarks(minMarks);
                   setExamNumber(getExamNumber());
                   
                   List examList = (List)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CRE_ExamIds_List);
                  if(examList.size()>getExamNumber())
                   setIsLastExam("NO");
                  else
                      setIsLastExam("YES");
                  
                  
                  
             
            Map questionVtoMap = (Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ECERT_QUESTIONS_MAP);
            String questionVtoList = "";
            Iterator iterator = questionVtoMap.entrySet().iterator();
            int i=0;
                while (iterator.hasNext()) {
                        Map.Entry mapEntry = (Map.Entry) iterator.next();
                        //System.out.println("The key is: " + mapEntry.getKey() + ",value is :" + mapEntry.getValue());
                        
                        questionVTO = (QuestionsVTO)questionVtoMap.get(mapEntry.getKey());
                        
                        int ExamQuestionId = questionVTO.getId();
                        questionVtoList =questionVtoList + ExamQuestionId+"!"+questionVTO.getSubtopicId()+"@";
                        
                       // System.out.println("questionVtoList-->"+questionVtoList);
                       // System.out.println("questionVtoList length-->"+questionVtoList.length());
                        attemptedQuestionsResult = DataSourceDataProvider.getInstance().isQuestionAttempt(examKey,ExamQuestionId);
                      //  System.out.println("attemptedQuestionsResult"+attemptedQuestionsResult);
                      //  if(attemptedQuestionsResult == 0){
                        //    ServiceLocator.getAjaxHandlerService().insertAnswer(ExamQuestionId,0,empId,examKey,questionVTO.getSubtopicId());
                         //   System.out.println("attempted question =0");
                            
                           // i++;
                       // }  
                       
                      
 if(attemptedQuestionsResult != 0){
                           noOfAttempted++;
                       }  
 attemptedQuestionsResult = 0;
                }
             questionVtoList = questionVtoList.substring(0, questionVtoList.length()-1);
            // ServiceLocator.getCreService().doSubmitExam(examKey,empId,getTotalQuest(),attemptedQuestions,minMarks,questionVtoList,this);
             ServiceLocator.getCreService().doSubmitExam(examKey,empId,questionVtoMap.size(),noOfAttempted,minMarks,questionVtoList,this);
             //System.out.println("questionVtoList-->"+questionVtoList);
       
          setExamType(getExamType());
             if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ECERT_QUESTIONS_MAP)!=null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.ECERT_QUESTIONS_MAP);
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.ECERT_QUESTIONS_MAP,null);
                    }
            if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ECERT_CURRENT_EXAM_KEY)!=null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.ECERT_CURRENT_EXAM_KEY);
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.ECERT_CURRENT_EXAM_KEY,null);
                    }
            //System.out.println("resultType-------------------------->");
               resultType = SUCCESS;
            }//Closing Session checking
        } catch(Exception ex){
            System.out.println("ExamkeyError-->"+ex.getMessage());
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.getMessage());
            resultType =  ERROR;
        }
        
        return resultType;
    }


    /**
     * To edit the Consultent
     */
    public String getConsultantDetails() throws Exception
 {
      resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            try{
               // accountService = ServiceLocator.getAccountService();
               // setContactEmailId(accountService.getContactEmailId(getId()));
                //this.setAccountId(getAccountId());
                prepare();
                ServiceLocator.getCreService().getConsultantDetails(this);
                
                setId(getId());
                resultType = SUCCESS;
                
            }catch (Exception ex){
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                ex.printStackTrace();
                httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                resultType =  ERROR;
            }
        }
        return resultType;
     
 }
    
    /**
     *To update Consultent details
     *  
     */
    
   public String updateConsultantDetails() throws Exception
 {
       String resultMsg = null;
      resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            try{
               // accountService = ServiceLocator.getAccountService();
               // setContactEmailId(accountService.getContactEmailId(getId()));
                //this.setAccountId(getAccountId());
                prepare();
               
             //   System.out.println("Consultant id to update ---->"+getId());
                
                int result = ServiceLocator.getCreService().updateConsultantDetails(this,httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                
               // System.out.println("result----->"+result);
                
                if(result>0){
                       resultMsg = "Consultant Details has been Updated successfully!";
                       resultType = SUCCESS; 
                 }
                   httpServletRequest.getSession(false).setAttribute("resultMessage", resultMsg); 
                  
            }catch (Exception ex){
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                ex.printStackTrace();
                httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                resultType =  ERROR;
            }
        }
        return resultType;
     
 }

   
   
    /*
     public String getCreRecords() throws Exception{
        resultType = LOGIN;
        String queryString = null;
        try {
            
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            // queryString = "SELECT Id,ConsultentId,CONCAT(LName,'.',FName) AS ConsultentName,STATUS,Level FROM tblCreConsultentDetails";
               //httpServletRequest.setAttribute(ApplicationConstants.QUERY_STRING, queryString);
            setSubTopicsMap(DataSourceDataProvider.getInstance().getSubTopicNamesMap(Integer.parseInt(Properties.getProperty("CRE.TopicId"))));    
            resultType = SUCCESS;
            }//Closing Session checking
        } catch(Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
            resultType =  ERROR;
        }
        
        
        return resultType;
    }  
   
       */
   public String getCreRecords() throws Exception{
        resultType = LOGIN;
        String queryString = null;
        try {
            
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            // queryString = "SELECT Id,ConsultentId,CONCAT(LName,'.',FName) AS ConsultentName,STATUS,Level FROM tblCreConsultentDetails";
               //httpServletRequest.setAttribute(ApplicationConstants.QUERY_STRING, queryString);
            //setSubTopicsMap(DataSourceDataProvider.getInstance().getSubTopicNamesMap(Integer.parseInt(Properties.getProperty("CRE.TopicId"))));    
            setExamTypeMap(DataSourceDataProvider.getInstance().getExamTypeMap());    
            setRecLocationList(DataSourceDataProvider.getInstance().getCollegeList());	
            resultType = SUCCESS;
            }//Closing Session checking
        } catch(Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
            resultType =  ERROR;
        }
        
        
        return resultType;
    }  
   

        
        /*
         *Method for getReview
         *Date : 08/30/2013
         */
      
       public String getReview() throws Exception{
        resultType = LOGIN;
        String queryString = null;
        try {
            
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            
            setConsultantName(DataSourceDataProvider.getInstance().getCreConsultantNameById(getId()));
            setCurrentTechReviewCollection(ServiceLocator.getCreService().getCreTechReviewCollection(getId(),15));
            setCurrentHrReviewCollection(ServiceLocator.getCreService().getCreHrReviewCollection(getId(),15));
            setCurrentVpReviewCollection(ServiceLocator.getCreService().getCreVpReviewCollection(getId(),15));
               setCurrentExamReviewCollection(ServiceLocator.getCreService().getCreExamReviewCollection(getId(), 15));
               setCurrentOnboardReviewCollection(ServiceLocator.getCreService().getCreOnboardReviewCollection(getId(), 15));
                resultType = SUCCESS;
            }//Closing Session checking
        } catch(Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
            resultType =  ERROR;
        }
        
        
        return resultType;
    } 
        
               
     public String creTechReviewUpdate() throws Exception{
        resultType = LOGIN;
        String queryString = null;
        try {
            
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            
            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
            if(getTempVar() == 2) {
             ServiceLocator.getCreService().creTechReviewAdd(this);
             resultMessage = "<font color=\"green\" size=\"1.5\">Consultant Technical Review has been successfully Added!</font>";
            }else if(getTempVar() == 1){
                
             ServiceLocator.getCreService().updateTechReview(this);
             resultMessage = "<font color=\"green\" size=\"1.5\">Consultant Technical Review has been successfully Updated!</font>";
            }
            
                        ServiceLocator.getCreService().updateCREConsultantStatus(getId(),getTechReviewStatus(),"Tech Level",getCreatedBy());
                        httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
                        resultType = SUCCESS;
          // setCurrentTechReviewCollection(ServiceLocator.getCreService().getCreTechReviewCollection(getId(),15));
                        getReview();
             setId(getId());
               setNavId(0);
                resultType = SUCCESS;
            }//Closing Session checking
        } catch(Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
            resultType =  ERROR;
        }
        
        
        return resultType;
    }
     
     
             
      public String getTechLeadReview() throws Exception{
        resultType = LOGIN;
        String queryString = null;
        try {
            
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            setNavId(getNavId());
                
           setCreReviewVTO(ServiceLocator.getCreService().getTechLeadReview(getTechReviewId(),getId()));
             getReview();
              // setCurrentTechReviewCollection(ServiceLocator.getCreService().getCreTechReviewCollection(getId(),15));
                resultType = SUCCESS;
            }//Closing Session checking
        } catch(Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
            resultType =  ERROR;
        }
        
        return resultType;
    }
      
      
        public String deleteTechLeadReview() throws Exception{
        resultType = LOGIN;
        String queryString = null;
        try {
            
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            setNavId(0);
                  
            String empLoginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
           //setCreReviewVTO(ServiceLocator.getCreService().getTechLeadReview(getTechReviewId(),getId()));
                ServiceLocator.getCreService().deleteTechLeadReview(getTechReviewId());
              // setCurrentTechReviewCollection(ServiceLocator.getCreService().getCreTechReviewCollection(getId(),15));
                getReview();
                ServiceLocator.getCreService().updateCREConsultantStatus(getId(),"Recored Deleted","Tech Level",empLoginId);
                resultType = SUCCESS;
            }//Closing Session checking
        } catch(Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
            resultType =  ERROR;
        }
        
        
        return resultType;
    }
   
   /*Hr Review Add/Update
    *Date : 08/30/3013
    */     
         public String creHrReviewUpdate() throws Exception{
        resultType = LOGIN;
        String queryString = null;
        try {
            
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            
            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
          //  System.out.println("Hrr-->getTempVar-->"+getTempHrVar());
            if(getTempHrVar() == 2) {
            ServiceLocator.getCreService().creHrReviewAdd(this);
             hrResultMessage = "<font color=\"green\" size=\"1.5\">Consultant Hr Review has been successfully Added!</font>";
            }else if(getTempHrVar() == 1){
                
                        ServiceLocator.getCreService().updateHrReview(this);
             hrResultMessage = "<font color=\"green\" size=\"1.5\">Consultant Hr Review has been successfully Updated!</font>";
            }
                        //httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,htResultMessage);
                        ServiceLocator.getCreService().updateCREConsultantStatus(getId(),getHrReviewStatus(),"HR Level",getCreatedBy());
                        resultType = SUCCESS;
           //setCurrentTechReviewCollection(ServiceLocator.getCreService().getCreTechReviewCollection(getId(),15));
                        getReview();
             setId(getId());
               setHrNavId(0);
                resultType = SUCCESS;
            }//Closing Session checking
        } catch(Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
            resultType =  ERROR;
        }
        
        
        return resultType;
    }
      public String getHrReview() throws Exception{
        resultType = LOGIN;
        String queryString = null;
        try {
            
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            setHrNavId(1);
                
           setCreReviewVTO(ServiceLocator.getCreService().getHrReview(getHrReviewId(),getId()));
             getReview();
              // setCurrentTechReviewCollection(ServiceLocator.getCreService().getCreTechReviewCollection(getId(),15));
                resultType = SUCCESS;
            }//Closing Session checking
        } catch(Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
            resultType =  ERROR;
        }
        
        return resultType;
    }
     
   
        public String deleteHrReview() throws Exception{
        resultType = LOGIN;
        String queryString = null;
        try {
            
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            setHrNavId(0);
                  String empLoginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();      
           //setCreReviewVTO(ServiceLocator.getCreService().getTechLeadReview(getTechReviewId(),getId()));
                ServiceLocator.getCreService().deleteHrReview(getHrReviewId());
              // setCurrentTechReviewCollection(ServiceLocator.getCreService().getCreTechReviewCollection(getId(),15));
                getReview();
                ServiceLocator.getCreService().updateCREConsultantStatus(getId(),"Recored Deleted","HR Level",empLoginId);
                resultType = SUCCESS;
            }//Closing Session checking
        } catch(Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
            resultType =  ERROR;
        }
        
        
        return resultType;
    }
      
    /*
     *Vp review Add
     *Date : 08/30/2013
     */  
    public String creVpReviewUpdate() throws Exception{
        resultType = LOGIN;
        String queryString = null;
        try {
            
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            
            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
           // System.out.println("Hrr-->getTempVar-->"+getTempHrVar());
            if(getTempVpVar() == 2) {
            ServiceLocator.getCreService().creVpReviewAdd(this);
             vpResultMessage = "<font color=\"green\" size=\"1.5\">Consultant Vp Review has been successfully Added!</font>";
            }else if(getTempVpVar() == 1){
                
                        ServiceLocator.getCreService().updateVpReview(this);
             vpResultMessage = "<font color=\"green\" size=\"1.5\">Consultant Vp Review has been successfully Updated!</font>";
            }
                        //httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,htResultMessage);
                        ServiceLocator.getCreService().updateCREConsultantStatus(getId(),getVpReviewStatus(),"VP Level",getCreatedBy());
                        resultType = SUCCESS;
           //setCurrentTechReviewCollection(ServiceLocator.getCreService().getCreTechReviewCollection(getId(),15));
                        getReview();
             setId(getId());
               setVpNavId(0);
                resultType = SUCCESS;
            }//Closing Session checking
        } catch(Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
            resultType =  ERROR;
        }
        
        
        return resultType;
    }
     
    public String getVpReview() throws Exception{
        resultType = LOGIN;
        String queryString = null;
        try {
            
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            setVpNavId(1);
              //  System.out.println("hiii");
           setCreReviewVTO(ServiceLocator.getCreService().getVpReview(getVpReviewId(),getId()));
             getReview();
              // setCurrentTechReviewCollection(ServiceLocator.getCreService().getCreTechReviewCollection(getId(),15));
                resultType = SUCCESS;
            }//Closing Session checking
        } catch(Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
            resultType =  ERROR;
        }
        
        return resultType;
    }     
    
    public String deleteVpReview() throws Exception{
        resultType = LOGIN;
        String queryString = null;
        try {
            
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            setVpNavId(0);
                   String empLoginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();     
           //setCreReviewVTO(ServiceLocator.getCreService().getTechLeadReview(getTechReviewId(),getId()));
                ServiceLocator.getCreService().deleteVpReview(getVpReviewId());
              // setCurrentTechReviewCollection(ServiceLocator.getCreService().getCreTechReviewCollection(getId(),15));
                getReview();
                ServiceLocator.getCreService().updateCREConsultantStatus(getId(),"Deleted","VP Level",empLoginId);
                resultType = SUCCESS;
            }//Closing Session checking
        } catch(Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
            resultType =  ERROR;
        }
        
        
        return resultType;
    }
    // Added by aditya
     //new
    public String setCreExam() throws Exception{
    resultType = LOGIN;
    
    try {

    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
         queryString = "SELECT tblCreSetExam.Id,tblCreSetExam.ExamType,tblCreSetExam.MinMarks,tblCreSetExam.Time,tblCreSetExam.NoOfQuestions from tblCreSetExam where Status='Active'";
        
            if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QUERY_STRING)!=null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QUERY_STRING);
                    }
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QUERY_STRING,queryString);
                    
            resultType = SUCCESS;
            
        }
    } catch(Exception ex){
        
        httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
        resultType =  ERROR;
    }
    return resultType;
    } 
    public String addExamDetails() throws Exception
         {
            // System.out.println("into addExamDetails ");
             resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            setCurrentAction("doAddSubTopics");
            setSubTopicsMap(DataSourceDataProvider.getInstance().getSubTopicNamesMap(Integer.parseInt(Properties.getProperty("CRE.TopicId"))));    
            
            resultType= SUCCESS;
        }
        return resultType;
         }
      
     public String doAddSubTopics() throws Exception {
        // System.out.println("into doAddSubTopics ");
         resultType = LOGIN;
         int result=0;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            int empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
            String EmpLoginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                try{
               
              if(getSubtopics1().size()==0)
              setCurrentAction("doAddSubTopics");
                        else
                            setCurrentAction("doUpdateSubTopics");
             // System.out.println("subtopics---->"+getSubtopics1());
                  result = ServiceLocator.getCreService().doAddSubTopics(getSubtopics1(),getExamType(),getMinMarks(),getTotalQuest(),getTotDuration(),getTotalQues());
                
                   if(result>0)
                    resultMessage = "<font color=\"green\" size=\"1.5\">SubTopics added successfully!</font>";
                   else
                        resultMessage = "<font color=\"red\" size=\"1.5\">please try again</font>";
                httpServletRequest.getSession(false).setAttribute("resultMessage", resultMessage);
                    resultType = SUCCESS;
                }catch (Exception ex){
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
           
        }
        return resultType;
    }
 public String getCreExamDetails() throws Exception {
    // System.out.println("Into cre exam details-------->");
         resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
                try{
                   setSubTopicsMap(DataSourceDataProvider.getInstance().getSubTopicNamesMap(Integer.parseInt(Properties.getProperty("CRE.TopicId"))));    
                   ServiceLocator.getCreService().getCreExamDetails(this,getSubtopicId1());              
                   setCurrentAction("doUpdateSubTopics");
                    resultType = SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
             }
        return resultType;
    }   
public String doUpdateSubTopics() throws Exception {
       //  System.out.println("into doUpdateSubTopics ");
         resultType = LOGIN;
         boolean result=false;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            int empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
            String EmpLoginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                try{
               
            //  System.out.println("id----------------->"+getSubtopicId1());
                   setSubTopicsMap(DataSourceDataProvider.getInstance().getSubTopicNamesMap(Integer.parseInt(Properties.getProperty("CRE.TopicId"))));    
                  result = ServiceLocator.getCreService().doUpdateSubTopics(getSubtopicId1(),getSubtopics1(),getExamType(),getMinMarks(),getTotalQuest(),getTotDuration(),getTotalQues());
              //  System.out.println(result);
                   if(result==false)
                    resultMessage = "<font color=\"green\" size=\"1.5\">Exam Details updated successfully!</font>";
                   else
                        resultMessage = "<font color=\"red\" size=\"1.5\">please try again</font>";
                httpServletRequest.getSession(false).setAttribute("resultMessage", resultMessage);
                    resultType = SUCCESS;
                }catch (Exception ex){
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
           
        }
        return resultType;
    } 
    public String inActiveCreExam() throws Exception{
      resultType = LOGIN;
      boolean result=false;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
                try{
                  
                 result= ServiceLocator.getCreService().inActiveCreExam(this,getSubtopicId1());              
                 //  System.out.println(result);
                    resultType = SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
             }
        return resultType;
    } 
    /*Method For Image uploading
        * Date : 08/11/2014
        * Author : Santosh Kola
     * Modified by Triveni Nidra
     * Modified Date : 03/07/2017
        */
        public String doUploadImage() throws FileNotFoundException,ServletException{
     //   System.out.println("in updateImage");
  //  System.out.println("in getImagePath-->"+getImagePath());
       // InputStream imageFile = new FileInputStream(getImagePath());
//        Connection connection = null;
//        PreparedStatement statement = null;
         String fileName = "";
         InputStream imageFile = new FileInputStream(getImagePath());
             resultType = LOGIN;
 
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
               
        try {
         setId(Integer.parseInt(getConsultantId()));
           getConsultantDetails();
           // connection = ConnectionProvider.getInstance().getConnection();
//            statement = connection.prepareStatement("update tblCreConsultentDetails set Image=? where Id=?");
//            statement.setBinaryStream(1,imageFile, (int) getImagePath().length());
//            
//            statement.setInt(2, Integer.parseInt(getConsultantId()));
//            
//            //statement.setString(2, (String) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID));
//            int update = statement.executeUpdate();
//            setId(Integer.parseInt(getConsultantId()));
         //   System.out.println(getConsultentId()+"getConsultantId()......"+getConsultantId());
             
             String basePath = Properties.getProperty("CreConsultantImages.path");
                    File createPath = new File(basePath);
                 //   System.out.println("createPath..." + createPath);
                    File newFile = new File(createPath.getAbsolutePath() + "//" + getConsultentId() + ".png");
                  //  newFile.mkdirs();
                    String filepath = newFile.toString();
                    fileName = getConsultentId() + ".png";
                  //  System.out.println("filepath--->" + filepath);
                  //  System.out.println("fileName--->" + fileName);
                    OutputStream fileOutputStream = new FileOutputStream(
                                        newFile);
                    int bufferSize;
                        byte[] bufffer = new byte[512];
                        while ((bufferSize = imageFile.read(bufffer)) > 0) {
                                fileOutputStream.write(bufffer, 0, bufferSize);
                        }
                      String ImageDetails = Properties.getProperty("CreConsultantImages.path").trim() + "/" + getConsultentId() + ".png";
//                         statement = connection.prepareStatement("update tblCreConsultentDetails SET FileName = ? , FilePath = ? where Id=?");
//            statement.setString(1, fileName);
//             statement.setString(2, ImageDetails);
//             //int id = Integer.parseInt(getConsultantId());
//            statement.setString(3, getConsultantId());
//             int update = statement.executeUpdate();
                        fileOutputStream.close();
            
         
          //  if(update==1){
                resultMessage = "Consultant Image Updated Successfully!";
//            }else{
//                resultMessage = "Please Try again!";
//            }
           // httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
           // resultType = SUCCESS;
          httpServletRequest.getSession(false).setAttribute("resultMessage", resultMessage); 
            resultType = SUCCESS;
            
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex);
            resultType=ERROR;
        }finally {
            try{
//                if(statement!=null){
//                    statement.close();
//                    statement=null;
//                }
//                if(connection!=null){
//                    connection.close();
//                    connection=null;
//                }if(imageFile != null) {
                imageFile.close();
                imageFile = null;
                }
//            }catch(SQLException sqle){
//                httpServletRequest.getSession(false).setAttribute("errorMessage",sqle);
    //       }
catch(IOException ioe){
                httpServletRequest.getSession(false).setAttribute("errorMessage",ioe);
            }
        }
        }
        return resultType;
    }
    

    public void renderConsultantImage() {
//        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        byte[] image = null;
        ResultSet resultSet = null;
        String creId = "";
        File imageFile = null;
        InputStream is = null;
        try {
            setId(getId());
          //  System.out.println("getId()..." + getId());
             connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("SELECT ConsultentId FROM tblCreConsultentDetails WHERE id = '" + getId() + "'");
         //   resultSet = statement.executeQuery("select image from tblEmployee where loginId='" + image + "'" );
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
             //   imgByteArray = resultSet.getBytes("image");
                creId = resultSet.getString("ConsultentId");
            }
            String basePath = Properties.getProperty("CreConsultantImages.path");
            File createPath = new File(basePath);
                    // System.out.println("createPath..." + createPath);
                     imageFile = new File(createPath.getAbsolutePath() + "//" + creId + ".png");
           //  System.out.println("......."+imageFile);
           
            httpServletResponse.setContentType("text/html");
//            String basePath = Properties.getProperty("CreConsultantImages.path");
//            File createPath = new File(basePath);
//                    System.out.println("createPath..." + createPath);
                   
           //  System.out.println("......."+imageFile);
            if (!imageFile.exists()) {
                
                       imageFile = new File(Properties.getProperty("No.Image"));
                    
              //  imageFile = new File(Properties.getProperty("No.Image"));
              //  bytes = imgByteArray;
            }
           
//            if(image != null) {
//
//                httpServletResponse.getOutputStream().write(image);
//                //Closing output Stream it's high priority
//                httpServletResponse.getOutputStream().close();
//
//            }else {

//                  File imageFile = new File(Properties.getProperty("No.Image"));
                   is = new FileInputStream(imageFile);

//                Get the size of the file
                  long length = imageFile.length();

//                Create the byte array to hold the data
                  //byte[] bytes = new byte[(int)length];
                  image = new byte[(int)length];

//                Read in the bytes
                  int offset = 0;
                  int numRead = 0;
                  while (offset < image.length && (numRead=is.read(image, offset, image.length-offset)) >= 0) {
                    offset += numRead;
                  }

//                Ensure all the bytes have been read in
                  if (offset < image.length) {
                    throw new IOException("Could not completely read file ");
                  }

           
                  httpServletResponse.getOutputStream().write(image);

//                Close the input stream and return bytes                                
                  is.close();
                  httpServletResponse.getOutputStream().close();

        }catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex);
            resultType=ERROR;
        }finally {
            try{
                if(resultSet!=null){
                    resultSet.close();
                    resultSet=null;
                }
                if(preparedStatement!=null){
                    preparedStatement.close();
                    preparedStatement=null;
                }
                
                if(connection!=null){
                    connection.close();
                    connection=null;
                }
                if(image != null){
                    // image.close();
                    image = null;
                }
            }catch(Exception sqle){
                httpServletRequest.getSession(false).setAttribute("errorMessage",sqle);
            }
        }

      //  return SUCCESS;
    }
    

    
        /*
      * Adding Onboard Review 
      * Date : 08/15/2014
      * Author : Santosh Kola
      */
     public String creOnboardReviewUpdate() throws Exception{
        resultType = LOGIN;
        String queryString = null;
        try {
            
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            
            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
           // System.out.println("Hrr-->getTempVar-->"+getTempHrVar());
            if(getTempOnBoardVar() == 2) {
            ServiceLocator.getCreService().creOnboardReviewAdd(this);
             onboardResultMessage = "<font color=\"green\" size=\"1.5\">Consultant Onboard Comments has been successfully Added!</font>";
            }else if(getTempOnBoardVar() == 1){
                
                        ServiceLocator.getCreService().updateOnboardReview(this);
             onboardResultMessage = "<font color=\"green\" size=\"1.5\">Consultant Onboard Comments has been successfully Updated!</font>";
            }
                        //httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,htResultMessage);
                        ServiceLocator.getCreService().updateCREOnboardLevel(getId(),getCreatedBy());
                        resultType = SUCCESS;
           //setCurrentTechReviewCollection(ServiceLocator.getCreService().getCreTechReviewCollection(getId(),15));
                        getReview();
             setId(getId());
               setOnBoardNavId(0);
                resultType = SUCCESS;
            }//Closing Session checking
        } catch(Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
            resultType =  ERROR;
        }
        
        
        return resultType;
    }
     public String getOnboardReview() throws Exception{
        resultType = LOGIN;
        String queryString = null;
        try {
            
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            setVpNavId(1);
              //  System.out.println("hiii");
           setCreReviewVTO(ServiceLocator.getCreService().getOnboardReview(getOnboardReviewId(),getId()));
             getReview();
              // setCurrentTechReviewCollection(ServiceLocator.getCreService().getCreTechReviewCollection(getId(),15));
                resultType = SUCCESS;
            }//Closing Session checking
        } catch(Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
            resultType =  ERROR;
        }
        
        return resultType;
    }     
     /* Excel generate for ConsultantList */
     //generateCreConsultantsReports
     
    /* public String generateCreConsultantsReports() throws ServiceLocatorException {
        
        resultType = INPUT;
        OutputStream outputStream = null;
        InputStream inputStream = null;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
           // userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String responseString = "";
            try {
                String fileLocation = "";
                //fileLocation = ServiceLocator.getMarketingService().getCampaignContactsExcel(httpServletRequest,getCampaignId());
                fileLocation = ServiceLocator.getCreService().generateExcelForCreConsultantSearch(this);
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

                    resultMessage = "<font color=\"red\" size=\"1.5\">Sorry! No records for this Search!</font>";
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




        }//Close Session Checking
        resultType = SUCCESS;
        return resultType;
    }*/
     public String generateCreConsultantsReports() throws ServiceLocatorException {
        
        resultType = INPUT;
        OutputStream outputStream = null;
        InputStream inputStream = null;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
           // userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String responseString = "";
            try {
                String fileLocation = "";
                //fileLocation = ServiceLocator.getMarketingService().getCampaignContactsExcel(httpServletRequest,getCampaignId());
                fileLocation = ServiceLocator.getCreService().generateExcelForCreConsultantSearchV2(this);
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
resultType = SUCCESS;
                    } else {
                        resultType = ERROR;
                     //   throw new FileNotFoundException("File not found");
                    }
                } else {
resultType = ERROR;
                    resultMessage = "<font color=\"red\" size=\"1.5\">Sorry! No records for this Search!</font>";
                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, resultMessage);

                }


            } catch (FileNotFoundException ex) {
                try {
                    resultType = ERROR;
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




        }//Close Session Checking
        //resultType = SUCCESS;
        return resultType;
    }
     
     public String doCaptureImage() {
      
            String byteStr = getMydata();
            setConsultantId(getConsultantId());
            String fileName = "";
         
        byte[] bytes = Base64.decode(byteStr);
        
              InputStream imageFile = new ByteArrayInputStream(bytes);
       
             resultType = LOGIN;
    
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
               
        try {
            getConsultantDetails();
    setId(Integer.parseInt(getConsultantId()));
     setConsultentId(getConsultentId());
              // System.out.println(getConsultentId()+"getConsultantId()......"+getConsultantId());
             
             String basePath = Properties.getProperty("CreConsultantImages.path");
                    File createPath = new File(basePath);
                //    System.out.println("createPath..." + createPath);
                    File newFile = new File(createPath.getAbsolutePath() + "//" + getConsultentId() + ".png");
                  //  newFile.mkdirs();
                    String filepath = newFile.toString();
                    fileName = getConsultentId() + ".png";
                //    System.out.println("filepath--->" + filepath);
                 //   System.out.println("fileName--->" + fileName);
                    OutputStream fileOutputStream = new FileOutputStream(
                                        newFile);
                    int bufferSize;
                        byte[] bufffer = new byte[512];
                        while ((bufferSize = imageFile.read(bufffer)) > 0) {
                                fileOutputStream.write(bufffer, 0, bufferSize);
                        }
                   //   String ImageDetails = Properties.getProperty("CreConsultantImages.path").trim() + "/" + getConsultentId() + ".png";
                      
      //      connection = ConnectionProvider.getInstance().getConnection();
//            statement = connection.prepareStatement("update tblCreConsultentDetails set Image=? where Id=?");
//            statement.setBinaryStream(1,imageFile, (int) bytes.length);
//            
//            statement.setInt(2, Integer.parseInt(getConsultantId()));
//             statement = connection.prepareStatement("update tblCreConsultentDetails SET FileName = ? , FilePath = ? where Id=?");
//            statement.setString(1, fileName);
//             statement.setString(2, ImageDetails);
//             //int id = Integer.parseInt(getConsultantId());
//            statement.setString(3, getConsultantId());
//             int update = statement.executeUpdate();
//                        fileOutputStream.close();
            //statement.setString(2, (String) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID));
           // int update = statement.executeUpdate();
            
          
          //  if(update==1){
                resultMessage = "Consultant Image Updated Successfully!";
//            }else{
//                resultMessage = "Please Try again!";
//            }
           // httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
           // resultType = SUCCESS;
          httpServletRequest.getSession(false).setAttribute("resultMessage", resultMessage); 
            resultType = SUCCESS;
            
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex);
            resultType=ERROR;
        }finally {
            try{
//                if(statement!=null){
//                    statement.close();
//                    statement=null;
//                }
//                if(connection!=null){
//                    connection.close();
//                    connection=null;
//                }
                if(imageFile != null) {
                imageFile.close();
                imageFile = null;
                }
          }
//  catch(SQLException sqle){
//                httpServletRequest.getSession(false).setAttribute("errorMessage",sqle);
//            }
            catch(IOException ioe){
                httpServletRequest.getSession(false).setAttribute("errorMessage",ioe);
            }
        }
        }
        return resultType;
    }
    

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public List getGenderList() {
        return genderList;
    }

    public void setGenderList(List genderList) {
        this.genderList = genderList;
    }

    public List getMaritalStatusList() {
        return maritalStatusList;
    }

    public void setMaritalStatusList(List maritalStatusList) {
        this.maritalStatusList = maritalStatusList;
    }

    public List getCoutryList() {
        return coutryList;
    }

    public void setCoutryList(List coutryList) {
        this.coutryList = coutryList;
    }

    public Map getPrefferedQuestionsMap() {
        return prefferedQuestionsMap;
    }

    public void setPrefferedQuestionsMap(Map prefferedQuestionsMap) {
        this.prefferedQuestionsMap = prefferedQuestionsMap;
    }

    public DefaultDataProvider getDefaultDataProvider() {
        return defaultDataProvider;
    }

    public void setDefaultDataProvider(DefaultDataProvider defaultDataProvider) {
        this.defaultDataProvider = defaultDataProvider;
    }


    public String getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(String consultantId) {
        this.consultantId = consultantId;
    }

    public String getConsultantName() {
        return consultantName;
    }

    public void setConsultantName(String consultantName) {
        this.consultantName = consultantName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public int getTotalQuest() {
        return totalQuest;
    }

    public void setTotalQuest(int totalQuest) {
        this.totalQuest = totalQuest;
    }

    public int getDurationTime() {
        return durationTime;
    }

    public void setDurationTime(int durationTime) {
        this.durationTime = durationTime;
    }

    public int getInsTopicId() {
        return insTopicId;
    }

    public void setInsTopicId(int insTopicId) {
        this.insTopicId = insTopicId;
    }

    public int getInsdomainId() {
        return insdomainId;
    }

    public void setInsdomainId(int insdomainId) {
        this.insdomainId = insdomainId;
    }

    public int getHideremainingQuestions() {
        return hideremainingQuestions;
    }

    public void setHideremainingQuestions(int hideremainingQuestions) {
        this.hideremainingQuestions = hideremainingQuestions;
    }

    public int getAttemptedQuestions() {
        return attemptedQuestions;
    }

    public void setAttemptedQuestions(int attemptedQuestions) {
        this.attemptedQuestions = attemptedQuestions;
    }

    public String getExamStatus() {
        return examStatus;
    }

    public void setExamStatus(String examStatus) {
        this.examStatus = examStatus;
    }

    public int getExamMarks() {
        return examMarks;
    }

    public void setExamMarks(int examMarks) {
        this.examMarks = examMarks;
    }

    public List getExamDetailInfoList() {
        return examDetailInfoList;
    }

    public void setExamDetailInfoList(List examDetailInfoList) {
        this.examDetailInfoList = examDetailInfoList;
    }

    public int getAttendingInterviewAt() {
        return attendingInterviewAt;
    }

    public void setAttendingInterviewAt(int attendingInterviewAt) {
        this.attendingInterviewAt = attendingInterviewAt;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getFatherOccupation() {
        return fatherOccupation;
    }

    public void setFatherOccupation(String fatherOccupation) {
        this.fatherOccupation = fatherOccupation;
    }

    public String getFatherIncome() {
        return fatherIncome;
    }

    public void setFatherIncome(String fatherIncome) {
        this.fatherIncome = fatherIncome;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getYearOfPass() {
        return yearOfPass;
    }

    public void setYearOfPass(String yearOfPass) {
        this.yearOfPass = yearOfPass;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getIpeCategory() {
        return ipeCategory;
    }

    public void setIpeCategory(String ipeCategory) {
        this.ipeCategory = ipeCategory;
    }

    public String getIpeStream() {
        return ipeStream;
    }

    public void setIpeStream(String ipeStream) {
        this.ipeStream = ipeStream;
    }

    public String getIpeCollege() {
        return ipeCollege;
    }

    public void setIpeCollege(String ipeCollege) {
        this.ipeCollege = ipeCollege;
    }

    public String getIpeMedium() {
        return ipeMedium;
    }

    public void setIpeMedium(String ipeMedium) {
        this.ipeMedium = ipeMedium;
    }

    public String getIpeYearOfPass() {
        return ipeYearOfPass;
    }

    public void setIpeYearOfPass(String ipeYearOfPass) {
        this.ipeYearOfPass = ipeYearOfPass;
    }

    public String getIpePercentage() {
        return ipePercentage;
    }

    public void setIpePercentage(String ipePercentage) {
        this.ipePercentage = ipePercentage;
    }

    public String getDegreeQual() {
        return degreeQual;
    }

    public void setDegreeQual(String degreeQual) {
        this.degreeQual = degreeQual;
    }

    public String getDegreeBranch() {
        return degreeBranch;
    }

    public void setDegreeBranch(String degreeBranch) {
        this.degreeBranch = degreeBranch;
    }

    public String getDegreeCollege() {
        return degreeCollege;
    }

    public void setDegreeCollege(String degreeCollege) {
        this.degreeCollege = degreeCollege;
    }

    public String getDegreeMedium() {
        return degreeMedium;
    }

    public void setDegreeMedium(String degreeMedium) {
        this.degreeMedium = degreeMedium;
    }

    public String getDegreeYearOfPass() {
        return degreeYearOfPass;
    }

    public void setDegreeYearOfPass(String degreeYearOfPass) {
        this.degreeYearOfPass = degreeYearOfPass;
    }

    public String getDegreePercentage() {
        return degreePercentage;
    }

    public void setDegreePercentage(String degreePercentage) {
        this.degreePercentage = degreePercentage;
    }

    public String getPgQual() {
        return pgQual;
    }

    public void setPgQual(String pgQual) {
        this.pgQual = pgQual;
    }

    public String getPgStream() {
        return pgStream;
    }

    public void setPgStream(String pgStream) {
        this.pgStream = pgStream;
    }

    public String getPgCollege() {
        return pgCollege;
    }

    public void setPgCollege(String pgCollege) {
        this.pgCollege = pgCollege;
    }

    public String getPgMedium() {
        return pgMedium;
    }

    public void setPgMedium(String pgMedium) {
        this.pgMedium = pgMedium;
    }

    public String getPgYearOfPass() {
        return pgYearOfPass;
    }

    public void setPgYearOfPass(String pgYearOfPass) {
        this.pgYearOfPass = pgYearOfPass;
    }

    public String getPgPercentage() {
        return pgPercentage;
    }

    public void setPgPercentage(String pgPercentage) {
        this.pgPercentage = pgPercentage;
    }

    public String getAddInfo() {
        return addInfo;
    }

    public void setAddInfo(String addInfo) {
        this.addInfo = addInfo;
    }

    public String getSkill1() {
        return skill1;
    }

    public void setSkill1(String skill1) {
        this.skill1 = skill1;
    }

    public String getScale1() {
        return scale1;
    }

    public void setScale1(String scale1) {
        this.scale1 = scale1;
    }

    public String getSkill2() {
        return skill2;
    }

    public void setSkill2(String skill2) {
        this.skill2 = skill2;
    }

    public String getScale2() {
        return scale2;
    }

    public void setScale2(String scale2) {
        this.scale2 = scale2;
    }

    public String getSkill3() {
        return skill3;
    }

    public void setSkill3(String skill3) {
        this.skill3 = skill3;
    }

    public String getScale3() {
        return scale3;
    }

    public void setScale3(String scale3) {
        this.scale3 = scale3;
    }

    public String getSkill4() {
        return skill4;
    }

    public void setSkill4(String skill4) {
        this.skill4 = skill4;
    }

    public String getScale4() {
        return scale4;
    }

    public void setScale4(String scale4) {
        this.scale4 = scale4;
    }

    public String getSkill5() {
        return skill5;
    }

    public void setSkill5(String skill5) {
        this.skill5 = skill5;
    }

    public String getScale5() {
        return scale5;
    }

    public void setScale5(String scale5) {
        this.scale5 = scale5;
    }

    public String getSkill6() {
        return skill6;
    }

    public void setSkill6(String skill6) {
        this.skill6 = skill6;
    }

    public String getScale6() {
        return scale6;
    }

    public void setScale6(String scale6) {
        this.scale6 = scale6;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCompany1() {
        return company1;
    }

    public void setCompany1(String company1) {
        this.company1 = company1;
    }

    public String getDesignation1() {
        return designation1;
    }

    public void setDesignation1(String designation1) {
        this.designation1 = designation1;
    }

    public Date getFromDate1() {
        return fromDate1;
    }

    public void setFromDate1(Date fromDate1) {
        this.fromDate1 = fromDate1;
    }

    public Date getToDate1() {
        return toDate1;
    }

    public void setToDate1(Date toDate1) {
        this.toDate1 = toDate1;
    }

    public String getLocation1() {
        return location1;
    }

    public void setLocation1(String location1) {
        this.location1 = location1;
    }

    public String getCompany2() {
        return company2;
    }

    public void setCompany2(String company2) {
        this.company2 = company2;
    }

    public String getDesignation2() {
        return designation2;
    }

    public void setDesignation2(String designation2) {
        this.designation2 = designation2;
    }

    public Date getFromDate2() {
        return fromDate2;
    }

    public void setFromDate2(Date fromDate2) {
        this.fromDate2 = fromDate2;
    }

    public Date getToDate2() {
        return toDate2;
    }

    public void setToDate2(Date toDate2) {
        this.toDate2 = toDate2;
    }

    public String getLocation2() {
        return location2;
    }

    public void setLocation2(String location2) {
        this.location2 = location2;
    }

    public String getRefName() {
        return refName;
    }

    public void setRefName(String refName) {
        this.refName = refName;
    }

    public String getRefEmail() {
        return refEmail;
    }

    public void setRefEmail(String refEmail) {
        this.refEmail = refEmail;
    }

    public String getRefMobile() {
        return refMobile;
    }

    public void setRefMobile(String refMobile) {
        this.refMobile = refMobile;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getCellPhoneNo() {
        return cellPhoneNo;
    }

    public void setCellPhoneNo(String cellPhoneNo) {
        this.cellPhoneNo = cellPhoneNo;
    }

    public String getAltPhoneNo() {
        return altPhoneNo;
    }

    public void setAltPhoneNo(String altPhoneNo) {
        this.altPhoneNo = altPhoneNo;
    }

    public String getOtherEmail() {
        return otherEmail;
    }

    public void setOtherEmail(String otherEmail) {
        this.otherEmail = otherEmail;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTechReviewId() {
        return techReviewId;
    }

    public void setTechReviewId(int techReviewId) {
        this.techReviewId = techReviewId;
    }

    public int getNavId() {
        return navId;
    }

    public void setNavId(int navId) {
        this.navId = navId;
    }

    public String getTechReviewStatus() {
        return techReviewStatus;
    }

    public void setTechReviewStatus(String techReviewStatus) {
        this.techReviewStatus = techReviewStatus;
    }

    public String getTechCreatedDate() {
        return techCreatedDate;
    }

    public void setTechCreatedDate(String techCreatedDate) {
        this.techCreatedDate = techCreatedDate;
    }

    public String getTechLead() {
        return techLead;
    }

    public void setTechLead(String techLead) {
        this.techLead = techLead;
    }

    public Collection getCurrentTechReviewCollection() {
        return currentTechReviewCollection;
    }

    public void setCurrentTechReviewCollection(Collection currentTechReviewCollection) {
        this.currentTechReviewCollection = currentTechReviewCollection;
    }

    public String getTechLeadComments() {
        return techLeadComments;
    }

    public void setTechLeadComments(String techLeadComments) {
        this.techLeadComments = techLeadComments;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public CreReviewVTO getCreReviewVTO() {
        return creReviewVTO;
    }

    public void setCreReviewVTO(CreReviewVTO creReviewVTO) {
        this.creReviewVTO = creReviewVTO;
    }

    public int getTempVar() {
        return tempVar;
    }

    public void setTempVar(int tempVar) {
        this.tempVar = tempVar;
    }

    public Collection getCurrentHrReviewCollection() {
        return currentHrReviewCollection;
    }

    public void setCurrentHrReviewCollection(Collection currentHrReviewCollection) {
        this.currentHrReviewCollection = currentHrReviewCollection;
    }

    public String getHrReviewStatus() {
        return hrReviewStatus;
    }

    public void setHrReviewStatus(String hrReviewStatus) {
        this.hrReviewStatus = hrReviewStatus;
    }

    public String getHrComments() {
        return hrComments;
    }

    public void setHrComments(String hrComments) {
        this.hrComments = hrComments;
    }

    public String getHrCreatedDate() {
        return hrCreatedDate;
    }

    public void setHrCreatedDate(String hrCreatedDate) {
        this.hrCreatedDate = hrCreatedDate;
    }

    public String getHrLoginId() {
        return hrLoginId;
    }

    public void setHrLoginId(String hrLoginId) {
        this.hrLoginId = hrLoginId;
    }

    public int getHrReviewId() {
        return hrReviewId;
    }

    public void setHrReviewId(int hrReviewId) {
        this.hrReviewId = hrReviewId;
    }

    public int getTempHrVar() {
        return tempHrVar;
    }

    public void setTempHrVar(int tempHrVar) {
        this.tempHrVar = tempHrVar;
    }

    public int getHrNavId() {
        return hrNavId;
    }

    public void setHrNavId(int hrNavId) {
        this.hrNavId = hrNavId;
    }

    public String getVpReviewStatus() {
        return vpReviewStatus;
    }

    public void setVpReviewStatus(String vpReviewStatus) {
        this.vpReviewStatus = vpReviewStatus;
    }

    public String getVpComments() {
        return vpComments;
    }

    public void setVpComments(String vpComments) {
        this.vpComments = vpComments;
    }

    public String getVpCreatedDate() {
        return vpCreatedDate;
    }

    public void setVpCreatedDate(String vpCreatedDate) {
        this.vpCreatedDate = vpCreatedDate;
    }

    public String getVpLoginId() {
        return vpLoginId;
    }

    public void setVpLoginId(String vpLoginId) {
        this.vpLoginId = vpLoginId;
    }

    public int getVpReviewId() {
        return vpReviewId;
    }

    public void setVpReviewId(int vpReviewId) {
        this.vpReviewId = vpReviewId;
    }

    public int getTempVpVar() {
        return tempVpVar;
    }

    public void setTempVpVar(int tempVpVar) {
        this.tempVpVar = tempVpVar;
    }

    public int getVpNavId() {
        return vpNavId;
    }

    public void setVpNavId(int vpNavId) {
        this.vpNavId = vpNavId;
    }

    public Collection getCurrentVpReviewCollection() {
        return currentVpReviewCollection;
    }

    public void setCurrentVpReviewCollection(Collection currentVpReviewCollection) {
        this.currentVpReviewCollection = currentVpReviewCollection;
    }

    public String getOfficeEmail() {
        return officeEmail;
    }

    public void setOfficeEmail(String officeEmail) {
        this.officeEmail = officeEmail;
    }

    public String getHrResultMessage() {
        return hrResultMessage;
    }

    public void setHrResultMessage(String hrResultMessage) {
        this.hrResultMessage = hrResultMessage;
    }

    public String getVpResultMessage() {
        return vpResultMessage;
    }

    public void setVpResultMessage(String vpResultMessage) {
        this.vpResultMessage = vpResultMessage;
    }

    /**
     * @return the currentExamReviewCollection
     */
    public Collection getCurrentExamReviewCollection() {
        return currentExamReviewCollection;
    }

    /**
     * @param currentExamReviewCollection the currentExamReviewCollection to set
     */
    public void setCurrentExamReviewCollection(Collection currentExamReviewCollection) {
        this.currentExamReviewCollection = currentExamReviewCollection;
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
     * @return the subTopicsMap
     */
    public Map getSubTopicsMap() {
        return subTopicsMap;
    }

    /**
     * @param subTopicsMap the subTopicsMap to set
     */
    public void setSubTopicsMap(Map subTopicsMap) {
        this.subTopicsMap = subTopicsMap;
    }

    /**
     * @return the campusLocation
     */
    public int getCampusLocation() {
        return campusLocation;
    }

    /**
     * @param campusLocation the campusLocation to set
     */
    public void setCampusLocation(int campusLocation) {
        this.campusLocation = campusLocation;
    }

    /**
     * @return the recLocation
     */
    public String getRecLocation() {
        return recLocation;
    }

    /**
     * @param recLocation the recLocation to set
     */
    public void setRecLocation(String recLocation) {
        this.recLocation = recLocation;
    }

    /**
     * @return the jFairLocation
     */
    public String getJfairLocation() {
        return jfairLocation;
    }

    /**
     * @param jFairLocation the jFairLocation to set
     */
    public void setJfairLocation(String jfairLocation) {
        this.jfairLocation = jfairLocation;
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
     * @return the subtopics1
     */
    public List getSubtopics1() {
        return subtopics1;
    }

    /**
     * @param subtopics1 the subtopics1 to set
     */
    public void setSubtopics1(List subtopics1) {
        this.subtopics1 = subtopics1;
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
     * @return the subtopicId1
     */
    public int getSubtopicId1() {
        return subtopicId1;
    }

    /**
     * @param subtopicId1 the subtopicId1 to set
     */
    public void setSubtopicId1(int subtopicId1) {
        this.subtopicId1 = subtopicId1;
    }

    /**
     * @return the examTypeMap
     */
    public Map getExamTypeMap() {
        return examTypeMap;
    }

    /**
     * @param examTypeMap the examTypeMap to set
     */
    public void setExamTypeMap(Map examTypeMap) {
        this.examTypeMap = examTypeMap;
    }

    /**
     * @return the examNumber
     */
    public int getExamNumber() {
        return examNumber;
    }

    /**
     * @param examNumber the examNumber to set
     */
    public void setExamNumber(int examNumber) {
        this.examNumber = examNumber;
    }

    /**
     * @return the isLastExam
     */
    public String getIsLastExam() {
        return isLastExam;
    }

    /**
     * @param isLastExam the isLastExam to set
     */
    public void setIsLastExam(String isLastExam) {
        this.isLastExam = isLastExam;
    }

    /**
     * @return the currentQuestionsCollection
     */
    public Collection getCurrentQuestionsCollection() {
        return currentQuestionsCollection;
    }

    /**
     * @param currentQuestionsCollection the currentQuestionsCollection to set
     */
    public void setCurrentQuestionsCollection(Collection currentQuestionsCollection) {
        this.currentQuestionsCollection = currentQuestionsCollection;
    }

    /**
     * @return the imagePath
     */
    public File getImagePath() {
        return imagePath;
    }

    /**
     * @param imagePath the imagePath to set
     */
    public void setImagePath(File imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    /**
     * @return the onboardComments
     */
    public String getOnboardComments() {
        return onboardComments;
    }

    /**
     * @param onboardComments the onboardComments to set
     */
    public void setOnboardComments(String onboardComments) {
        this.onboardComments = onboardComments;
    }

    /**
     * @return the onboardCreatedDate
     */
    public String getOnboardCreatedDate() {
        return onboardCreatedDate;
    }

    /**
     * @param onboardCreatedDate the onboardCreatedDate to set
     */
    public void setOnboardCreatedDate(String onboardCreatedDate) {
        this.onboardCreatedDate = onboardCreatedDate;
    }

    /**
     * @return the onboardLoginId
     */
    public String getOnboardLoginId() {
        return onboardLoginId;
    }

    /**
     * @param onboardLoginId the onboardLoginId to set
     */
    public void setOnboardLoginId(String onboardLoginId) {
        this.onboardLoginId = onboardLoginId;
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

    /**
     * @return the tempOnBoardVar
     */
    public int getTempOnBoardVar() {
        return tempOnBoardVar;
    }

    /**
     * @param tempOnBoardVar the tempOnBoardVar to set
     */
    public void setTempOnBoardVar(int tempOnBoardVar) {
        this.tempOnBoardVar = tempOnBoardVar;
    }

    /**
     * @return the onBoardNavId
     */
    public int getOnBoardNavId() {
        return onBoardNavId;
    }

    /**
     * @param onBoardNavId the onBoardNavId to set
     */
    public void setOnBoardNavId(int onBoardNavId) {
        this.onBoardNavId = onBoardNavId;
    }

    /**
     * @return the currentOnboardReviewCollection
     */
    public Collection getCurrentOnboardReviewCollection() {
        return currentOnboardReviewCollection;
    }

    /**
     * @param currentOnboardReviewCollection the currentOnboardReviewCollection to set
     */
    public void setCurrentOnboardReviewCollection(Collection currentOnboardReviewCollection) {
        this.currentOnboardReviewCollection = currentOnboardReviewCollection;
    }

    /**
     * @return the onboardResultMessage
     */
    public String getOnboardResultMessage() {
        return onboardResultMessage;
    }

    /**
     * @param onboardResultMessage the onboardResultMessage to set
     */
    public void setOnboardResultMessage(String onboardResultMessage) {
        this.onboardResultMessage = onboardResultMessage;
    }

    /**
     * @return the recLocationList
     */
    public List getRecLocationList() {
        return recLocationList;
    }

    /**
     * @param recLocationList the recLocationList to set
     */
    public void setRecLocationList(List recLocationList) {
        this.recLocationList = recLocationList;
    }

    /**
     * @return the postAppliedMap
     */
    public Map getPostAppliedMap() {
        return postAppliedMap;
    }

    /**
     * @param postAppliedMap the postAppliedMap to set
     */
    public void setPostAppliedMap(Map postAppliedMap) {
        this.postAppliedMap = postAppliedMap;
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
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the interviewAtConsultantReport
     */
    public String getInterviewAtConsultantReport() {
        return interviewAtConsultantReport;
    }

    /**
     * @param interviewAtConsultantReport the interviewAtConsultantReport to set
     */
    public void setInterviewAtConsultantReport(String interviewAtConsultantReport) {
        this.interviewAtConsultantReport = interviewAtConsultantReport;
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
     * @return the mydata
     */
    public String getMydata() {
        return mydata;
    }

    /**
     * @param mydata the mydata to set
     */
    public void setMydata(String mydata) {
        this.mydata = mydata;
    }

    /**
     * @return the consultentId
     */
    public String getConsultentId() {
        return consultentId;
    }

    /**
     * @param consultentId the consultentId to set
     */
    public void setConsultentId(String consultentId) {
        this.consultentId = consultentId;
    }
}
