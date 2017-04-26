/*
 * EcertificationAction.java
 *
 * Created on July 18, 2013, 6:30 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.ecertification;


import com.mss.mirage.ecertification.QuestionsVTO;
import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.AuthorizationManager;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.Properties;
import com.mss.mirage.util.ServiceLocator;
import com.mss.mirage.util.ServiceLocatorException;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import java.util.LinkedList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletResponseAware;

/**
 *
 * @author miracle
 */
public class EcertificationAction extends ActionSupport implements ServletRequestAware,ServletResponseAware  {
    
    
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    private String resultType;
    private int domainId;
    private Map domainMap;
     private int topicId;
    private Map topicsMap;
    private int eflag;
    private List<QuestionsVTO> questionsVtoList =null;
    private DataSourceDataProvider dataSourceDataProvider;

    private int userRoleId;
    /** Creates a new instance of EcertificationAction */
    public EcertificationAction() {
    }
    private String domainName;
    private String topicName;
    private int totalQuest;
    private int durationTime;
    private String option;
    private int insTopicId;
    private int insdomainId;
    private int hideremainingQuestions;
    private Map questionsVtoMap = null;
    private int attemptedQuestions;
    private String examStatus;
    private int examMarks;
    private String selectTab;
    private int insSerachFlag;
    
    
    //variables for examcreation
    //private String domainName;
    //private String topicName;
    private String subtopic;
    private int totQuestions;
    private int totDuration;
    private int totMarks;
    private int minMarks;
    /** Creates a new instance of EcertificationAction */
    
    //varibles for questions creation
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private int answer;
    private String description;
    private String resultMessage;
    private int quesCreateSuccess;
    private int counter;
    private int totalQues;
    private int totalCount;
    
    
    //new for update
    private int questionId;
    private QuestionsVTO currentQuestion;
    private String operationType;
    private int subTopicId;
    private String currentAction;
    //new for exam search
    //private String subTopicName;
    private String startDate;
    private String endDate;
    private String submitFrom;
    private StringBuilder stringBuilder;
    private String empName;
    private int examKeyId;
    private String examValidationKey;
    private String loginId;
    private String conId;
    
    /**
     *  To get number of keys
     */
     private int noOfKeys;
     
     private Map subTopicMap;
     
     private String authorName;
     private String status;
     private String preAssignEmpId;
     private int id;
     private String list;
     private String distinctSubTopicIds;
     private List examDetailInfoList;
     private int empType;
     private int sflag;
     private String checkDelete;
     /*
      * New Properties for Add Authors And Subtopics start
      */
      private Collection currentAuthorCollection;
     private Collection currentSubtopicCollection;
     private int authorId;
    private String authorLoginId;
    private String createdBy;
    private String createdDate;
    private String authorStatus;
    private int tempVar;
    private int navId;

   
    private TopicsVTO topicsVTO;
    private String subTopicName;
    private String subTopicStatus;
    private String subtopicResultMessage;
    private List authorList;
     /*
      * New Properties for Add Authors And Subtopics end
      */
    private Collection currentQuestionsCollection;
     private String itgBatch;
     /**
      *  Prepare method 
      *
      */
    public String prepare(){
        resultType = LOGIN;
        
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            int userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
           // if(AuthorizationManager.getInstance().isAuthorizedUser("PREPARE_GREENSHEET",userRoleId)){
                try{
                   dataSourceDataProvider = DataSourceDataProvider.getInstance();
                setDomainMap(dataSourceDataProvider.getExamDomainsMap());
                setTopicsMap(dataSourceDataProvider.getDomainTopicsMap(getDomainId()));
                setSubTopicMap(DataSourceDataProvider.getInstance().getSubTopicNamesMap(getTopicId()));
                    resultType = SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
          //  }//END-Authorization Checking
        }//Close for session checking
        return resultType;
    }
    
    
    
    public String getEcertification() throws Exception {
        try{
            resultType = LOGIN;
            if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
                
                prepare();
                setEflag(0);
                resultType = SUCCESS;
            }//Closing Session checking
        } catch(Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
            resultType =  ERROR;
        }
        
        return resultType;
    }
    
   
   public String startExam() throws Exception {
        try{
            resultType = LOGIN;
            if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
              
                setDomainName(getDomainName());
                setTopicName(getTopicName());
              
               // System.out.println("11111111111111111111111----------------------Stsrt exa----------------->"+getTotalQuest());
                setTotalQuest(getTotalQuest());
                setDurationTime(getDurationTime());
                
                ServiceLocator.getEcertificationService().doInactiveExamKey(getExamValidationKey(),httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
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
                
               maxExamKey =  ServiceLocator.getEcertificationService().insertExamKey(getInsdomainId(),getInsTopicId(),httpServletRequest);
               httpServletRequest.getSession(false).setAttribute(ApplicationConstants.ECERT_CURRENT_EXAM_KEY,maxExamKey);
              
               
               questionsVtoMap = ServiceLocator.getEcertificationService().getQuestions(getInsTopicId(),httpServletRequest,getTotalQuest(),maxExamKey);
               httpServletRequest.getSession(false).setAttribute(ApplicationConstants.ECERT_QUESTIONS_MAP,questionsVtoMap);
               setInsTopicId(getInsTopicId());
               setCurrentQuestionsCollection(questionsVtoMap.values());
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
     
   /*
    *Method Do Submit Exam and prints the Result
    *
    *Date : 07/23/2013
    */
    public String submitExam() throws Exception {
        try{
            resultType = LOGIN;
            if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
              QuestionsVTO questionVTO = null; 
              int attemptedQuestionsResult = 0;
              int examKey = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ECERT_CURRENT_EXAM_KEY).toString());
              int empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
              String EmpLoginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
             
                 // int attemptedQuestions = getTotalQuest()-getHideremainingQuestions();
                //  System.out.println("Attempted Questions-->"+attemptedQuestions);
                  
                  int result = 0;
                  
                  result = ServiceLocator.getEcertificationService().getResult(examKey);
                 // System.out.println("Before getting min marks"+getInsTopicId());
                  int minMarks  = DataSourceDataProvider.getInstance().getExamMinMarks(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ECERT_VALIDATE_KEY).toString());
                  setMinMarks(minMarks);
            String examStatus = null;
            if(result<minMarks) {
                examStatus = "FAIL";
            }else {
               examStatus = "PASS"; 
            }
           
           // (int examKeyId,int empId,int totalQuest, int attemptedQuest, int marks,String examStatus)
           // System.out.println("Before insert exam result");
          //  int isInsertExamResult = ServiceLocator.getEcertificationService().insertEmpExamResult(examKey,EmpLoginId,getTotalQuest(),attemptedQuestions,result,examStatus);
           // System.out.println("Before insert exam result-----------------------"+isInsertExamResult);
                
//            setDomainName(getDomainName());
//            setTopicName(getTopicName());
//            setTotalQuest(getTotalQuest());
//            setAttemptedQuestions(attemptedQuestions);
//            setExamStatus(examStatus);
//            setExamMarks(result);
            
            /***
             *   To check all question attempted or not
             *
             *  And insert not attempted question with ans 0
             *
             * 
             *
             */
            
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
            
             int isInsertExamResult = ServiceLocator.getEcertificationService().insertEmpExamResult(examKey,EmpLoginId,questionVtoMap.size(),(questionVtoMap.size()-i),result,examStatus);
            
          ServiceLocator.getEcertificationService().getEcertDetailResult(examKey,this);
            
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
    
    /** 
     *  Created by prasad 
     *  created on :
     *  To create axams with different topics and corresponding questions
     *  Updated create exam flow methods
     *  Updated on : 07-31-2013
     *  Updated by : ajay
     *
     */
    
     
    /**
     * Created by prasad
     * Modified by ajay
     * modified on : 07-31-2013
     * 
     *   DESCC: Exam list for admin ,it works when we click exam list in left menu(admin role).
     *
     * DESC; to list out the exams list in grid
     */
    public String examsList() {
        
         resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            String userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            
            resultType = "accessFailed";
           
                try{
                    StringBuilder stringBuilder = new StringBuilder();
                    
                    /* Search Query Based on SearchForm */
                   // if(getSubmitFrom() == null){
                        stringBuilder.append("SELECT tblEcertTopics.ID AS TopicId,DomainName,TopicName FROM "
                                + "tblEcertDomain LEFT OUTER JOIN tblEcertTopics "
                                + "ON(tblEcertDomain.Id= tblEcertTopics.DomainId) "
                                + "WHERE TopicName IS NOT NULL LIMIT 150");
                   // }
                    /*Removing Existed Session Object*/
                    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_AUTHORED_EXAM_LIST)!=null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QS_AUTHORED_EXAM_LIST);
                    }
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_AUTHORED_EXAM_LIST,stringBuilder.toString());
                    // httpServletRequest.getSession(false).setAttribute("query","2");
                    resultType = SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
           
        }//Close Session Checking
        return resultType;
    }
    
    public String getEcertBackToList(){
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            
            resultType = "accessFailed";
         //   if(AuthorizationManager.getInstance().isAuthorizedUser("GET_CRM_BACKLIST",userRoleId)){
                try{
                 //   String actionName = (String)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ACC_SEARCH_ACTION);
                    
                     httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_AUTHORED_EXAM_LIST,stringBuilder.toString());
                    // httpServletRequest.getSession(false).setAttribute("query","2");
                    
                    
                    resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
          //  }//END-Authorization Checking
        }//Close Session Checking
        prepare();
        return resultType;
    }
    
    
    /**
     * Author : to get the 
     *
     */
      public String doAddQuestions() {
        int isAdd=0;
        String createdBy="";
        try{
            resultType = LOGIN;
            if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
                createdBy=httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();

                String totalQues="";
                httpServletRequest.getSession(false).setAttribute(ApplicationConstants.ECERT_TOTAL_QUESTIONS,getTotQuestions());
                prepare();
                //setDomainId(getDomainId());
                setTopicId(getTopicId());
                //setTotQuestions(getTotQuestions());
                //setEflag(0);
                setCurrentAction("createQuestion");
                
                resultType = SUCCESS;
            }//Closing Session checking
        } catch(Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
            ex.printStackTrace();
            resultType =  ERROR;
        }
        
        return resultType;
    }
    
      
      /**
       * To create questions
       * updated on 07-31-2013
       *
       */
      
      public String doCreateQuestion() {
        int isCreate=0;
        
        try{
            resultType = LOGIN;
            String examList="EXAMLIST";
            if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
               
                String createdBy=httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                //System.out.println("1");
                int tqs = getTotQuestions();
                tqs = tqs - 1;
                
                setTotQuestions(tqs);
                
                prepare();
                setEflag(0);
                isCreate = ServiceLocator.getEcertificationService().doAddQuestion(createdBy,getQuestion(),getOption1(),getOption2(),getOption3(),getOption4(),getAnswer(),getDescription(),getTopicId(),getSubTopicId());
                
                setSubTopicId(getSubTopicId());
                if(isCreate > 0) {
                    resultMessage = "<font color=\"green\" size=\"1.5\">New question added successfully!</font>";
                    setQuesCreateSuccess(2);
                    setCurrentAction("createQuestion");
                    resultType = SUCCESS;
                } else  {
                    resultMessage = "<font color=\"red\" size=\"1.5\">Please try again!!</font>";
                 }
                
                httpServletRequest.getSession(false).setAttribute("resultMessage", resultMessage);
            }//Closing Session checking
        } catch(Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
            ex.printStackTrace();
            resultType =  ERROR;
        }
        
        return resultType;
    }

      
      /**
       *
       *  End grid and questions adding modified by ajay on 07-31-2013
       *
       */
 
 public String doSearchExamResultList() throws Exception {
         resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            int empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
            String EmpLoginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
           
                try{
                    StringBuilder stringBuilder = new StringBuilder();
                    /*stringBuilder.append("SELECT tblEcertResult.EmpId AS empId,TopicName,ROUND((Marks/TotalQuestions)*100,2) AS Percentage ,ExamKeyId,Marks,TotalQuestions,AttemptedQuestions,DateSubmitted,ExamStatus "+
                    "FROM tblEcertResult LEFT OUTER JOIN tblEcertKey ON (tblEcertResult.ExamKeyId=tblEcertKey.ID) "+
                    "LEFT OUTER JOIN tblEcertTopics ON (tblEcertKey.TopicId=tblEcertTopics.ID) ");*/
                    
                    stringBuilder.append("SELECT tblEcertResult.EmpId AS empId,TopicId,TopicName,ROUND((Marks/TotalQuestions)*100,2) AS Percentage ,ExamKeyId,Marks,TotalQuestions,AttemptedQuestions,DateSubmitted,ExamStatus "+
                    "FROM tblEcertResult LEFT OUTER JOIN tblEcertKey ON (tblEcertResult.ExamKeyId=tblEcertKey.ID) "+
                    "LEFT OUTER JOIN tblEcertTopics ON (tblEcertKey.TopicId=tblEcertTopics.ID) ");
                    
                     boolean andFlag = false;
                      if((getEmpType()!=0) || !"".equals(getLoginId()) || !"".equals(getStartDate()) || !"".equals(getEndDate()) || !"".equals(getConId()))
                            stringBuilder.append(" WHERE ");
                    //System.out.println("after null check!!");
                    
                    
                        // System.out.println("After loginId check!!!!");
                  if(!"".equals(getStartDate()) && !andFlag){
                            stringBuilder.append("date(DateSubmitted) >= '"+DateUtility.getInstance().convertStringToMySQLDate(getStartDate())+"' ");
                            andFlag = true;
                        }else if(!"".equals(getStartDate()) && andFlag){
                            stringBuilder.append("AND date(DateSubmitted) >= '"+DateUtility.getInstance().convertStringToMySQLDate(getStartDate())+"' ");
                        }
                     
                    // System.out.println("After startdate check!!!!");
                       if(!"".equals(getEndDate()) && !andFlag){
                            stringBuilder.append("date(DateSubmitted) <= '"+DateUtility.getInstance().convertStringToMySQLDate(getEndDate())+"' ");
                            andFlag = true;
                        }else if(!"".equals(getEndDate()) && andFlag){
                            stringBuilder.append("AND date(DateSubmitted) <= '"+DateUtility.getInstance().convertStringToMySQLDate(getEndDate())+"' ");
                        }
                     
                  // System.out.println("After end date check!!!!");
                     if( getEmpType()!=0){
                         if(!"".equals(getEmpType()) && !andFlag){
                            stringBuilder.append("tblEcertResult.EmpType =" + getEmpType()+" ");
                            andFlag = true;
                        }else if(!"".equals(getEmpType()) && andFlag){
                            stringBuilder.append("AND tblEcertResult.EmpType =" + getEmpType()+" ");
                        }
                     }
                     
                   // System.out.println("getEmpType()---->"+getEmpType()+"-------------------getLoginId()--------"+getLoginId());
                     
                     if(getEmpType()==1){
                       if(!"".equals(getLoginId()) && !andFlag){
                            stringBuilder.append("tblEcertResult.EmpId = '" + getLoginId() + "' ");
                            andFlag = true;
                        }else if(!"".equals(getLoginId()) && andFlag){
                            stringBuilder.append("AND tblEcertResult.EmpId = '" + getLoginId() + "' ");
                        }
                     }
                     if(getEmpType()==2){
                        if(!"".equals(getConId()) && !andFlag){
                            stringBuilder.append("tblEcertResult.EmpId LIKE '%" + getConId() + "%' ");
                            andFlag = true;
                        }else if(!"".equals(getConId()) && andFlag){
                            stringBuilder.append("AND tblEcertResult.EmpId LIKE '%" + getConId() + "%' ");
                        }
                     }
                      if(getDomainId() != 0 && !andFlag){
                     stringBuilder.append(" tblEcertKey.DomainId = "+getDomainId());
                     andFlag = true;
                     }else if(getDomainId() != 0){
                          stringBuilder.append(" AND tblEcertKey.DomainId = "+getDomainId());
                     }
                     if(getTopicId() != 0 && !andFlag){
                         stringBuilder.append(" tblEcertKey.TopicId = "+getTopicId());
                         andFlag = true;
                     }
                     else if(getTopicId() != 0){
                          stringBuilder.append(" AND tblEcertKey.TopicId = "+getTopicId());
                     }
                    stringBuilder.append(" ORDER BY ExamKeyId LIMIT 100");
                    setCurrentAction("searchExamResultList");
                    //System.out.println("Query-->"+stringBuilder.toString());
                    /*Removing Existed Session Object*/
                    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ECERT_MY_EXAM_RESULT_QUERY)!=null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.ECERT_MY_EXAM_RESULT_QUERY);
                    }
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.ECERT_MY_EXAM_RESULT_QUERY,stringBuilder.toString());
                     
                    setInsSerachFlag(1);
                    setStartDate(getStartDate());
                    setEndDate(getEndDate());
                    setEmpType(getEmpType());
                    setConId(getConId());
                    setEmpName(getEmpName());
                    setLoginId(getLoginId());
                   setDomainMap(DataSourceDataProvider.getInstance().getExamDomainsMap());
                setTopicsMap(DataSourceDataProvider.getInstance().getDomainTopicsMap(getDomainId()));
               
                    resultType = SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
           
        }//Close Session Checking
        return resultType;
    }
   

     
     /**
      * Method: getAllExamsList()
      * created by:
      * Modified by : ajay
      * modified date: 07-31-2013
      *
      */
     
     public String getExamResultsList() throws Exception {
         resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            int empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
            String EmpLoginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
           
                try{
                    StringBuilder stringBuilder = new StringBuilder();
                        
                   /* stringBuilder.append("SELECT tblEcertResult.EmpId AS empId,TopicName,(Marks/TotalQuestions)*100 AS Percentage,ExamKeyId,Marks,TotalQuestions,AttemptedQuestions,DateSubmitted,ExamStatus "+
                    "FROM tblEcertResult LEFT OUTER JOIN tblEcertKey ON (tblEcertResult.ExamKeyId=tblEcertKey.ID) "+
                    "LEFT OUTER JOIN tblEcertTopics ON (tblEcertKey.TopicId=tblEcertTopics.ID) WHERE date(DateSubmitted) >= '"+DateUtility.getInstance().PriviousSeventhDay()+"'  ORDER BY ExamKeyId DESC LIMIT 100");*/
                    
                    /*stringBuilder.append("SELECT tblEcertResult.EmpId AS empId,TopicName,ROUND((Marks/TotalQuestions)*100,2) AS Percentage,ExamKeyId,Marks,TotalQuestions,AttemptedQuestions,DateSubmitted,ExamStatus "+
                    "FROM tblEcertResult LEFT OUTER JOIN tblEcertKey ON (tblEcertResult.ExamKeyId=tblEcertKey.ID) "+
                    "LEFT OUTER JOIN tblEcertTopics ON (tblEcertKey.TopicId=tblEcertTopics.ID) WHERE date(DateSubmitted) >= '"+DateUtility.getInstance().PriviousSeventhDay()+"'  ORDER BY ExamKeyId DESC LIMIT 100");*/
                    
                    stringBuilder.append("SELECT tblEcertResult.EmpId AS empId,TopicId,TopicName,ROUND((Marks/TotalQuestions)*100,2) AS Percentage,ExamKeyId,Marks,TotalQuestions,AttemptedQuestions,DateSubmitted,ExamStatus "+
                    "FROM tblEcertResult LEFT OUTER JOIN tblEcertKey ON (tblEcertResult.ExamKeyId=tblEcertKey.ID) "+
                    "LEFT OUTER JOIN tblEcertTopics ON (tblEcertKey.TopicId=tblEcertTopics.ID) WHERE date(DateSubmitted) >= '"+DateUtility.getInstance().PriviousSeventhDay()+"'  ORDER BY ExamKeyId DESC LIMIT 100");
                    
                    /*Removing Existed Session Object*/
                    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ECERT_MY_EXAM_RESULT_QUERY)!=null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.ECERT_MY_EXAM_RESULT_QUERY);
                    }
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.ECERT_MY_EXAM_RESULT_QUERY,stringBuilder.toString());
                   
               setCurrentAction("searchExamResultList");
               setInsSerachFlag(0);
               setDomainMap(DataSourceDataProvider.getInstance().getExamDomainsMap());
                setTopicsMap(DataSourceDataProvider.getInstance().getDomainTopicsMap(getDomainId()));
                    resultType = SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
           
        }//Close Session Checking
        return resultType;
    }
     
     
     /*Method to get My Exam Result 
      *Date : 08/05/2013
      *getMyExamResultsList
      */
  public String getMyExamResultsList() throws Exception {
         resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            int empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
            String EmpLoginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
           
                try{
                    StringBuilder stringBuilder = new StringBuilder();
                        
                   /* stringBuilder.append("SELECT tblEcertResult.EmpId AS empId,TopicName,ExamKeyId,Marks,TotalQuestions,AttemptedQuestions,DateSubmitted,ExamStatus,ROUND((Marks/TotalQuestions)*100,2) AS Percentage "+
                    "FROM tblEcertResult LEFT OUTER JOIN tblEcertKey ON (tblEcertResult.ExamKeyId=tblEcertKey.ID) "+
                    "LEFT OUTER JOIN tblEcertTopics ON (tblEcertKey.TopicId=tblEcertTopics.ID) WHERE tblEcertResult.EmpId = '"+EmpLoginId+"' "+
                            " AND date(DateSubmitted) >= '"+DateUtility.getInstance().PriviousSeventhDay()+"' ORDER BY ExamKeyId DESC LIMIT 100");*/
                    
                    stringBuilder.append("SELECT tblEcertResult.EmpId AS empId,TopicId,TopicName,ExamKeyId,Marks,TotalQuestions,AttemptedQuestions,DateSubmitted,ExamStatus,ROUND((Marks/TotalQuestions)*100,2) AS Percentage "+
                    "FROM tblEcertResult LEFT OUTER JOIN tblEcertKey ON (tblEcertResult.ExamKeyId=tblEcertKey.ID) "+
                    "LEFT OUTER JOIN tblEcertTopics ON (tblEcertKey.TopicId=tblEcertTopics.ID) WHERE tblEcertResult.EmpId = '"+EmpLoginId+"' "+
                            " AND date(DateSubmitted) >= '"+DateUtility.getInstance().PriviousSeventhDay()+"' ORDER BY ExamKeyId DESC LIMIT 100");
                    
                    /*Removing Existed Session Object*/
                    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ECERT_MY_EXAM_RESULT_QUERY)!=null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.ECERT_MY_EXAM_RESULT_QUERY);
                    }
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.ECERT_MY_EXAM_RESULT_QUERY,stringBuilder.toString());
                     
                   setCurrentAction("searchMyExamResultList");
                   setInsSerachFlag(0);
                    resultType = SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
           
        }//Close Session Checking
        return resultType;
    }
     
  
  public String doSearchMyExamResultList() throws Exception {
         resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            int empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
            String EmpLoginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
           
                try{
                    StringBuilder stringBuilder = new StringBuilder();
                        
                   /* stringBuilder.append("SELECT tblEcertResult.EmpId AS empId,TopicName,ExamKeyId,Marks,TotalQuestions,AttemptedQuestions,DateSubmitted,ExamStatus,ROUND((Marks/TotalQuestions)*100,2) AS Percentage "+
                    "FROM tblEcertResult LEFT OUTER JOIN tblEcertKey ON (tblEcertResult.ExamKeyId=tblEcertKey.ID) "+
                    "LEFT OUTER JOIN tblEcertTopics ON (tblEcertKey.TopicId=tblEcertTopics.ID) ");*/
                    
                    stringBuilder.append("SELECT tblEcertResult.EmpId AS empId,TopicId,TopicName,ExamKeyId,Marks,TotalQuestions,AttemptedQuestions,DateSubmitted,ExamStatus,ROUND((Marks/TotalQuestions)*100,2) AS Percentage "+
                    "FROM tblEcertResult LEFT OUTER JOIN tblEcertKey ON (tblEcertResult.ExamKeyId=tblEcertKey.ID) "+
                    "LEFT OUTER JOIN tblEcertTopics ON (tblEcertKey.TopicId=tblEcertTopics.ID) ");
                    
                     boolean andFlag = false;
                      
                            stringBuilder.append(" WHERE tblEcertResult.EmpId = '"+EmpLoginId+"' ");
                    
                    andFlag = true;
                     
                         
                  if(!"".equals(getStartDate()) && !andFlag){
                            stringBuilder.append("date(DateSubmitted) >= '"+DateUtility.getInstance().convertStringToMySQLDate(getStartDate())+"' ");
                            andFlag = true;
                        }else if(!"".equals(getStartDate()) && andFlag){
                            stringBuilder.append("AND date(DateSubmitted) >= '"+DateUtility.getInstance().convertStringToMySQLDate(getStartDate())+"' ");
                        }
                     
                     
                       if(!"".equals(getEndDate()) && !andFlag){
                            stringBuilder.append("date(DateSubmitted) <= '"+DateUtility.getInstance().convertStringToMySQLDate(getEndDate())+"' ");
                            andFlag = true;
                        }else if(!"".equals(getEndDate()) && andFlag){
                            stringBuilder.append("AND date(DateSubmitted) <= '"+DateUtility.getInstance().convertStringToMySQLDate(getEndDate())+"' ");
                        }
                     
                   
                   setInsSerachFlag(1);
                    
                    stringBuilder.append(" ORDER BY ExamKeyId LIMIT 100");
                    setCurrentAction("searchMyExamResultList");
                    /*Removing Existed Session Object*/
                    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ECERT_MY_EXAM_RESULT_QUERY)!=null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.ECERT_MY_EXAM_RESULT_QUERY);
                    }
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.ECERT_MY_EXAM_RESULT_QUERY,stringBuilder.toString());
                     
                   setStartDate(getStartDate());
                   setEndDate(getEndDate());
               
                    resultType = SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
           
        }//Close Session Checking
        return resultType;
    }
   
  
     /***
     *New method for getting Questions list
     *Author :Prasad kandergula
     */
 
     public String questionsList() {
        
        try{
            resultType = LOGIN;
            if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
                 setAuthorList(DataSourceDataProvider.getInstance().getAuthorsByTopicId(getTopicId()));
                prepare();
                if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ECERT_SUBTOPIC_ID)!=null) {
                    httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.ECERT_SUBTOPIC_ID);
                }
                
                setSubTopicId(Integer.parseInt(httpServletRequest.getParameter("subTopicId").toString()));
               // System.out.println("SubTopic Id in myexam list -->"+getSubTopicId());
                httpServletRequest.getSession(false).setAttribute(ApplicationConstants.ECERT_SUBTOPIC_ID,getSubTopicId());
               
                
                resultType = SUCCESS;
            }//Closing Session checking
        } catch(Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
            ex.printStackTrace();
            resultType =  ERROR;
        }
        
        return resultType;
    } 
     
     /***
     *New method to get question details
     *Author :Prasad kandergula
      * Modified By : Ajay TUmmapala
     */

    public String populateQuestionDetails() {
        resultType = LOGIN;
        
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            
            try{
                
                if(httpServletRequest.getAttribute("submitFrom") != null) httpServletRequest.removeAttribute("submitFrom");
              //  String qId=httpServletRequest.getParameter("questionId").toString();
               // System.out.println("qId-------------------->"+qId);
                 if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ECERT_QUESTIONID_FORUPDATE)!=null) {
                    httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.ECERT_QUESTIONID_FORUPDATE);
                }
                
                httpServletRequest.getSession(false).setAttribute(ApplicationConstants.ECERT_QUESTIONID_FORUPDATE,getId());
                
                setCurrentQuestion(ServiceLocator.getEcertificationService().getQuestion(getId()));
                //  httpServletRequest.setAttribute("accessType",getAccessType());
                setOperationType("editQuestion");
                prepare();
                if(httpServletRequest.getSession(false).getAttribute("resultMessage")!=null){
                    httpServletRequest.getSession(false).removeAttribute("resultMessage");
                }
                setCurrentAction("updateQuestion");
                resultType=SUCCESS;
            }catch (Exception ex){
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                ex.printStackTrace();
                resultType =  ERROR;
            }
            
        }//Close Session Checking
        
        
        return resultType;
    }
    
     /***
     *New method to update question details
     *Author :Prasad kandergula
     * Modified By : Ajay Tummapala
     */
    
    public String doUpdateQuestion() {
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            int isUpdate=0;
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            
            try{

               String modifiedBy=httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString();

               // String qId=httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ECERT_QUESTIONID_FORUPDATE).toString();
                isUpdate=ServiceLocator.getEcertificationService().updateQuestion(modifiedBy,getQuestion(),getOption1(),getOption2(),getOption3(),getOption4(),getAnswer(),getDescription(),getSubTopicId(),getId());
                if(isUpdate>0) {
                    resultMessage = "Question has been successfully Updated!";
                    resultType = SUCCESS;
                } else {
                    
                    resultMessage = "Sorry! Please Try again!";
                    resultType = INPUT;
                }
                //  httpServletRequest.setAttribute("resultMessage", resultMessage);
                //httpServletRequest.setAttribute("resultMessage", resultMessage);
                httpServletRequest.getSession(false).setAttribute("resultMessage", resultMessage);
                //   httpServletRequest.setAttribute("accessType",getAccessType());
                
               // prepare();
            }catch (Exception ex){
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                ex.printStackTrace();
                resultType =  ERROR;
            }
            
        }//Close Session Checking
        return resultType;
    }
    
    
  
    
    /**
     * new method for exam list serach
     *Author:Prasad kandregula
     * modified by ajay
 */
  
    
    public String doSerachExamList() {
      //  System.out.println("in do serch");
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String roleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            resultType = "accessFailed";
           String userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                try{
                   
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("SELECT DomainName,TopicName,tblEcertTopics.ID AS TopicId "
                                + "FROM  tblEcertDomain LEFT OUTER JOIN tblEcertTopics ON(tblEcertDomain.Id= tblEcertTopics.DomainId) ");
                        if (roleName.equals("Employee")){
                            stringBuilder.append(" LEFT OUTER JOIN tblEcertTopicAuthors ON (tblEcertTopics.Id=tblEcertTopicAuthors.TopicId) ");
                        }
                        
                      stringBuilder.append(" WHERE TopicName IS NOT NULL");
                                
                       
                      // System.out.println("domain---->"+getDomainName()+"--- topic name---->"+getTopicName());
                        if(!"".equals(getTopicName())){
                            stringBuilder.append(" AND TopicName LIKE '" +getTopicName()+ "%' ");
                        }
                      
                         if(!"".equals(getDomainName())){
                            stringBuilder.append(" AND DomainName LIKE  '" +getDomainName()+ "%' ");
                        }
                      
                         
                     
                        if (roleName.equals("Employee")){
                           // tblEcertTopicAuthors.AuthorId = '"+userId+"' "
                            stringBuilder.append(" AND (tblEcertTopicAuthors.AuthorId = '"+userId+"')");// OR tblEcertTopics.CreatedBy='"+userId+"')");
                        }
                        stringBuilder.append(" LIMIT 150");
                        
                        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_AUTHORED_EXAM_LIST)!=null){
                            httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QS_AUTHORED_EXAM_LIST);
                        }
                        
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_AUTHORED_EXAM_LIST, stringBuilder.toString());
               
                        setTopicName(getTopicName());
                        setDomainName(getDomainName());

                    resultType = SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
          
        }//Close Session Checking
        return resultType;
    }



     
     
     
    
      /*
       *For Recalling Questions Add
       *Date : 07/29/2013
       */
      public String doReAddQuestions() {
        int isAdd=0;
        String createdBy="";
        try{
            resultType = LOGIN;
            if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
                createdBy=httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
               
               
                if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ECERT_TOTAL_QUESTIONS)!=null) {
                    httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.ECERT_TOTAL_QUESTIONS);
                }
                 
                String totalQues="";
                httpServletRequest.getSession(false).setAttribute(ApplicationConstants.ECERT_TOTAL_QUESTIONS,getTotQuestions());
                
                
                prepare();
                setDomainId(getDomainId());
                setTopicId(getTopicId());
                setTotQuestions(getTotQuestions());
                //setEflag(0);
                setCurrentAction("createQuestion");
               // isAdd = ServiceLocator.getEcertificationService().doCreateExam(createdBy,getDomainName(),getTopicName(),getSubtopic(),getTotQuestions(),getTotDuration(),getTotMarks(),getDomainId());
                if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ECERT_SUBTOPIC_ID)!=null) {
                    httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.ECERT_SUBTOPIC_ID);
                }
                int subTopicId=DataSourceDataProvider.getInstance().getMaxSubTopicId();
                httpServletRequest.getSession(false).setAttribute(ApplicationConstants.ECERT_SUBTOPIC_ID,subTopicId);
                
                
                resultType = SUCCESS;
            }//Closing Session checking
        } catch(Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
            ex.printStackTrace();
            resultType =  ERROR;
        }
        
        return resultType;
    }

    /*
     *Method to review the Exam
     *Date : 07/29/2013
     *
     */
      
      public String reviewExam() {
           resultType = LOGIN;
           List reviewList = null;
           try {
           reviewList = ServiceLocator.getEcertificationService().reviewExam(getExamKeyId());
              httpServletRequest.setAttribute(ApplicationConstants.ECERT_REVIEW_LIST,reviewList);
               resultType = SUCCESS; 
           } catch(Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
            ex.printStackTrace();
            resultType =  ERROR;
        }
           return resultType;
      }
      
      
      /**
       * DESC: Ajay tummapala
       *
       */  
       /*
       *For creating keys 
       *Author : ajay Tummapala
       */
      
     public String doCreateKey() throws Exception {
        try{
            resultType = LOGIN;
            if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
                
                 if((httpServletRequest.getSession(false).getAttribute("resultMessage"))!=null){
                        httpServletRequest.getSession(false).removeAttribute("resultMessage");
                   }
                 
                prepare();
                setEflag(0);
                resultType = SUCCESS;
            }//Closing Session checking
        } catch(Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
            resultType =  ERROR;
        }
        
        return resultType;
    }

   public String doAddExamKey() throws Exception {
        try{
            resultType = LOGIN;
            if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            List keysList = null;
               keysList = new ArrayList();
                // System.out.println("before createdby0");
                String createdBy=httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();                 
                 int totalRecords = 0;
                // while(keysList.size()!=getNoOfKeys()-1)
                  while(keysList.size()!=getNoOfKeys())
                 {
                     String key = generateRandomKeys();
                     while(!keysList.contains(key))
                     {
                         keysList.add(key);
                     }   
                 }
                // System.out.println("keysList size-->"+keysList.size());
                 for(int i=0;i<=keysList.size()-1;i++)
                 {
                    // System.out.println("Key"+i+"-->"+(String)keysList.get(i));
                  totalRecords += ServiceLocator.getEcertificationService().doAddKey(keysList.get(i),createdBy,getTopicId(),getNoOfKeys(),getTotDuration(),getTotalQues(),getMinMarks());
                 }
                // System.out.println("before createdby1");
                 
                prepare();
               // System.out.println("before createdby2");
                setEflag(0);
               // System.out.println("before createdby3");
                if(totalRecords>0)
                {
                    String resultMessage = "<font color=\"green\" size=\"1.5\">Exam Keys Sucessfully Created !!</font>";
                    httpServletRequest.getSession(false).setAttribute("resultMessage", resultMessage);
                }else
                {
                    String resultMessage = "<font color=\"red\" size=\"1.5\">Sorry Please try Again!!</font>";
                    httpServletRequest.getSession(false).setAttribute("resultMessage", resultMessage);
                }
              //  System.out.println("before createdby4");
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
      *For getting random keys
      *Ajay Tummapala 
      */
     
     public String generateRandomKeys() throws Exception
     {
          Random random = new Random((new Date()).getTime());
           char[] values = {'a','b','c','d','e','f','g','h','i','j',
               'k','l','m','n','o','p','q','r','s','t',
               'u','v','w','x','y','z','0','1','2','3',
               '4','5','6','7','8','9'};
      String out = "";
      for (int i=0;i<10;i++) {
         // System.out.println("before createdby6");
          int idx=random.nextInt(values.length);
        out += values[idx];
      }
      return out.toUpperCase();
     }
    /*
     *for getting exam keys List
     *
     */
       public String doListKeys() throws Exception {
         resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            int empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
            String EmpLoginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
           
                try{
                    StringBuilder stringBuilder = new StringBuilder();
                        
                    stringBuilder.append("SELECT tblEcertValidatorKeys.ID,tblEcertValidatorKeys.VKey, tblEcertValidatorKeys.STATUS, tblEcertTopics.TopicName,tblEcertValidatorKeys.CreatedBy, tblEcertValidatorKeys.CreatedDate, tblEcertValidatorKeys.ModifiedBy, tblEcertValidatorKeys.ModifiedDate FROM  tblEcertValidatorKeys LEFT OUTER JOIN  tblEcertTopics ON (tblEcertValidatorKeys.TopicId = tblEcertTopics.Id) WHERE tblEcertValidatorKeys.Status = 'Active' ORDER BY tblEcertValidatorKeys.CreatedDate DESC LIMIT 100");
                    setCreatedDate(DateUtility.getInstance().getCurrentMySqlDate()); 

                    /*Removing Existed Session Object*/
                    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ECERT_MY_EXAM_KEYS_QUERY)!=null){
                    httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.ECERT_MY_EXAM_KEYS_QUERY);
                    }
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.ECERT_MY_EXAM_KEYS_QUERY,stringBuilder.toString());
                     
                   prepare();
               // System.out.println("before createdby2");
                setEflag(0);
                setInsSerachFlag(0);
                    resultType = SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
           
        }//Close Session Checking
        return resultType;
    }
      
     /*
      *Desc : to search the exams key list 
      *Author : ajay tummapala
      *Email : atummapala@miraclesoft.com
      *
      */  
       
         public String doSearchExamKeyList() throws Exception {
         resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            int empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
            String EmpLoginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
           
                try{
                    StringBuilder stringBuilder = new StringBuilder();
                        
                    stringBuilder.append("SELECT tblEcertValidatorKeys.ID,tblEcertValidatorKeys.VKey, tblEcertValidatorKeys.STATUS, tblEcertTopics.TopicName,tblEcertValidatorKeys.CreatedBy, tblEcertValidatorKeys.CreatedDate, tblEcertValidatorKeys.ModifiedBy, tblEcertValidatorKeys.ModifiedDate FROM  tblEcertValidatorKeys LEFT OUTER JOIN  tblEcertTopics ON (tblEcertValidatorKeys.TopicId = tblEcertTopics.Id) WHERE tblEcertValidatorKeys.Status = 'Active' ");
                     boolean andFlag = false;
 
                    if(getTopicId()!=-1){
                            stringBuilder.append(" AND tblEcertValidatorKeys.TopicId =" + getTopicId()+"");
                        }
                         
              
                       if(!"".equals(getStartDate())){
                            stringBuilder.append(" AND date(tblEcertValidatorKeys.CreatedDate) >= '"+DateUtility.getInstance().convertStringToMySQLDate(getStartDate())+"' ");
                        }
           if(!"".equals(getEndDate())){
                            stringBuilder.append(" AND date(tblEcertValidatorKeys.CreatedDate) <= '"+DateUtility.getInstance().convertStringToMySQLDate(getEndDate())+"' ");
                        }
                     
                   
                        
                    
                    stringBuilder.append(" ORDER BY tblEcertValidatorKeys.CreatedDate DESC LIMIT 100");
                               // System.out.println("Query-->"+stringBuilder.toString());
                    /*Removing Existed Session Object*/
                    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ECERT_MY_EXAM_KEYS_QUERY)!=null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.ECERT_MY_EXAM_KEYS_QUERY);
                    }
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.ECERT_MY_EXAM_KEYS_QUERY,stringBuilder.toString());
                     
                   
               prepare();
               // System.out.println("before createdby2");
                setEflag(0);
                setInsSerachFlag(1);
                    resultType = SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
           
        }//Close Session Checking
        return resultType;
    }
//--------------
        
      /*
         * Author : Ajay Tummapala 
         * Desc : To add The author into the DB
         *
         */

         public String doAddAuthors() {
        int isCreate=0;
        
        try{
            resultType = LOGIN;
            String examList="EXAMLIST";
            if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
               
                String createdBy=httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                //System.out.println("1");
   
             
                isCreate = ServiceLocator.getEcertificationService().doAddAuthor(createdBy,getTopicId(),getPreAssignEmpId(),getStatus());
                
                if(isCreate > 0) {
                    resultMessage = "<font color=\"green\" size=\"1.5\">Author added successfully!</font>";
                    
                    resultType = SUCCESS;
                } else  {
                        resultType = INPUT;
                    resultMessage = "<font color=\"red\" size=\"1.5\">Please try again!!</font>";
                 }
                viewAuthorsAndSubtopics();
                //httpServletRequest.getSession(false).setAttribute("resultMessage", resultMessage);
            }//Closing Session checking
        } catch(Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
            ex.printStackTrace();
            resultType =  ERROR;
        }
        
        return resultType;
    }
         
         /*
         * Author : Ajay Tummapala 
         * Desc : To add The subtopic into the DB
         *
         */

         public String doAddSubTopic() {
        int isCreate=0;
        int isUpdate = 0;
        try{
            resultType = LOGIN;
            String examList="EXAMLIST";
            if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
               
                if(getNavId()==0) {
           boolean flag =  DataSourceDataProvider.getInstance().checkSubTopicName(getSubtopic(),getTopicId());
               if(!flag){
                  // System.out.println("navid-->"+getNavId());
                   setStatus("Active");
                isCreate = ServiceLocator.getEcertificationService().doAddSubTopic(getSubtopic(),getTopicId(),getStatus());
                
                if(isCreate > 0) {
                            setSubtopicResultMessage("<font color=\"green\" size=\"1.5\">SubTopic added successfully!</font>");
                     
                    
                    resultType = SUCCESS;
                } else  {
                            setSubtopicResultMessage("<font color=\"red\" size=\"1.5\">Please try again!!</font>");
                    resultType = INPUT;
                 }
               }
               else{
                        setSubtopicResultMessage("<font color=\"red\" size=\"1.5\">Sorry!! The SubTopic Already Exists!!</font>");
               }
                }else {
                  //  System.out.println("getSubtopic->"+getSubtopic());
                   // System.out.println("getSubTopicId->"+getSubTopicId());
                    isUpdate = ServiceLocator.getEcertificationService().doUpdateSubTopic(getSubtopic(),getSubTopicId());
                    setNavId(0);
                    if(isUpdate > 0) {
                        setSubtopicResultMessage("<font color=\"green\" size=\"1.5\">SubTopic updated successfully!</font>");
                     
                    
                    resultType = SUCCESS;
                } else  {
                        setSubtopicResultMessage("<font color=\"red\" size=\"1.5\">Please try again!!</font>");
                    resultType = INPUT;
                 }
                }
               viewAuthorsAndSubtopics();
                
                
            }//Closing Session checking
        } catch(Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
            ex.printStackTrace();
            resultType =  ERROR;
        }
        
        return resultType;
    }

 /*
         * Author : Ajay Tummapala 
         * Desc : To delete author
         *
         */

         public String doDeleteAuthor() {
        int isCreate=0;
        
        try{
            resultType = LOGIN;
            String examList="EXAMLIST";
            if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
               
                String createdBy=httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                //System.out.println("1");
   
                
                isCreate = ServiceLocator.getEcertificationService().doDeleteAuthor(getId(),createdBy);
                
                if(isCreate > 0) {
                    resultMessage = "<font color=\"green\" size=\"1.5\">Author Deleted successfully!</font>";
                     
                   
                    resultType = SUCCESS;
                } else  {
                    resultType = INPUT;
                    resultMessage = "<font color=\"red\" size=\"1.5\">Please try again!!</font>";
                 }
                
               viewAuthorsAndSubtopics();
            }//Closing Session checking
        } catch(Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
            ex.printStackTrace();
            resultType =  ERROR;
        }
        
        return resultType;
    }
/**
     * Created by Ajay Tummapala
     
     *
     * DESC; to get list of exam authored by user
     */
    public String getMyAuthoredExamsList() {
        
         resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            String userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            
            resultType = "accessFailed";
           
                try{
                    StringBuilder stringBuilder = new StringBuilder();
                    
                    /* Search Query Based on SearchForm */
                   // if(getSubmitFrom() == null){
                        stringBuilder.append("SELECT tblEcertTopics.Id AS topicId,DomainName,TopicName FROM tblEcertDomain " +
                                "LEFT OUTER JOIN tblEcertTopics ON (tblEcertDomain.Id = tblEcertTopics.DomainId) " +
                                "LEFT OUTER JOIN tblEcertTopicAuthors ON (tblEcertTopics.Id=tblEcertTopicAuthors.TopicId) " +
                                "WHERE (tblEcertTopicAuthors.AuthorId = '"+userId+"') LIMIT 100");   // OR tblEcertTopics.CreatedBy ='"+userId+"') LIMIT 100");
                    //}
                    /*Removing Existed Session Object*/
                    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_AUTHORED_EXAM_LIST)!=null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QS_AUTHORED_EXAM_LIST);
                    }
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_AUTHORED_EXAM_LIST,stringBuilder.toString());
                    
                    resultType = SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
           
        }//Close Session Checking
        return resultType;
    }
/*
      *Desc : to search the exams key list 
      *Author : ajay tummapala
      *Email : atummapala@miraclesoft.com
      *
      */  
       
         public String doSearchMyAuthoredExamList() throws Exception {
         resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            int empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
            String EmpLoginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
           
                try{
                    StringBuilder stringBuilder = new StringBuilder();
                        
                    stringBuilder.append("SELECT tblEcertTopics.Id AS topicId,DomainName,tblEcertTopics.TopicName " +
                            "FROM tblEcertDomain LEFT OUTER JOIN tblEcertTopics ON (tblEcertDomain.Id = tblEcertTopics.DomainId) " +
                            "LEFT OUTER JOIN tblEcertTopicAuthors ON (tblEcertTopics.Id=tblEcertTopicAuthors.TopicId) " +
                            "WHERE  (tblEcertTopicAuthors.AuthorId = '"+EmpLoginId+"')");  // OR tblEcertTopics.CreatedBy ='"+EmpLoginId+"')");
                           // "AND tblEcertTopicAuthors.STATUS = 'Active'");
                     boolean andFlag = false;
 
                    if(getTopicName()!=null){
                            stringBuilder.append(" AND tblEcertTopics.TopicName LIKE '"+getTopicName()+"%'");
                        }
                         
              
                  /*     if(!"".equals(getStartDate())){
                            stringBuilder.append(" AND date(tblEcertValidatorKeys.CreatedDate) >= '"+DateUtility.getInstance().convertStringToMySQLDate(getStartDate())+"' ");
                        }
           if(!"".equals(getEndDate())){
                            stringBuilder.append(" AND date(tblEcertValidatorKeys.CreatedDate) <= '"+DateUtility.getInstance().convertStringToMySQLDate(getEndDate())+"' ");
                        }
                     
                   */
                        
                    
                    stringBuilder.append("  LIMIT 100");
                                //System.out.println("Query-->"+stringBuilder.toString());
                    /*Removing Existed Session Object*/
                    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_AUTHORED_EXAM_LIST)!=null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QS_AUTHORED_EXAM_LIST);
                    }
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_AUTHORED_EXAM_LIST,stringBuilder.toString());
                     
                   
               prepare();
               // System.out.println("before createdby2");
                setEflag(0);
                setTopicName(getTopicName());
                    resultType = SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
           
        }//Close Session Checking
        return resultType;
    }
 /*
 *Ajay tummapala
 *for back to list of authors page
 */
         public String examsBackToList() throws Exception{
              
             return SUCCESS;
         }
         /*
      *Desc : to search the questions
      *Author : ajay tummapala
      *Email : atummapala@miraclesoft.com
      *
      */  
       
    public String doSearchQuestions() throws Exception {
         resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
             StringBuilder stringBuilder = new StringBuilder();
            //  String EmpLoginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            // System.out.println("Sfalg----->"+getSflag());
                setAuthorList(DataSourceDataProvider.getInstance().getAuthorsByTopicId(getTopicId()));
                stringBuilder.append("SELECT Question,tblEcertQuestion.SubtopicId As SubTopicId,tblEcertQuestion.TopicId AS TopicId,tblEcertQuestion.ID AS Id,tblEcertQuestion.CreatedBy,tblEcertQuestion.CreatedDate " +
                            "FROM tblEcertQuestion LEFT OUTER JOIN tblEcertSubTopics " +
                            "ON(tblEcertQuestion.SubtopicId=tblEcertSubTopics.ID) " +
                            "WHERE tblEcertQuestion.Status='Active' AND tblEcertQuestion.TopicId = "+getTopicId()+" ");
                     
                    //stringBuilder.append("  LIMIT 100");
                   
                    /*Removing Existed Session Object*/
                    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_QUESTION_SEARCH_LIST)!=null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QS_QUESTION_SEARCH_LIST);
                    }
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_QUESTION_SEARCH_LIST,stringBuilder.toString());
                     setTopicId(getTopicId());
                     setCurrentAction("searchQuestions");
                     setSubTopicMap(DataSourceDataProvider.getInstance().getSubTopicNamesMap(getTopicId()));
             return SUCCESS;
        }//Close Session Checking
        return resultType;
    }
/*
      *Desc : to search the question and generate user search query,,
      *Author : ajay tummapala
      *Email : atummapala@miraclesoft.com
      *
      */  
       
         public String doSearchQuestionList() throws Exception {
         resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
           // int empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
            //String EmpLoginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
           setAuthorList(DataSourceDataProvider.getInstance().getAuthorsByTopicId(getTopicId()));
                try{
                    StringBuilder stringBuilder = new StringBuilder();
                      if("yes".equals(getCheckDelete()))
                    {
                        if(httpServletRequest.getSession(false).getAttribute("question")!=null && !"".equals(getQuestion())){
                        setQuestion(httpServletRequest.getSession(false).getAttribute("question").toString());
                        httpServletRequest.getSession(false).removeAttribute("question");
                                    }
                    }
                    setCheckDelete("");
                    
                    stringBuilder.append("SELECT Question,tblEcertQuestion.TopicId AS TopicId,tblEcertQuestion.ID AS Id,tblEcertQuestion.CreatedBy,tblEcertQuestion.CreatedDate " +
                            "FROM tblEcertQuestion LEFT OUTER JOIN tblEcertSubTopics " +
                            "ON(tblEcertQuestion.SubtopicId=tblEcertSubTopics.ID) " +
                            "WHERE tblEcertQuestion.Status='Active' AND tblEcertQuestion.TopicId = "+getTopicId()+" AND tblEcertQuestion.Question LIKE '%"+getQuestion()+"%'");
                     
                        if(!"".equals(getAuthorLoginId())){
                         stringBuilder.append(" AND CreatedBy = '"+getAuthorLoginId()+"'");  
                        }
                        
                          if(getSubTopicId() != 0){
                         stringBuilder.append(" AND tblEcertQuestion.SubtopicId = "+getSubTopicId());  
                        }     
                       
                  //  stringBuilder.append("  LIMIT 100");
                  
                    /*Removing Existed Session Object*/
                    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_QUESTION_SEARCH_LIST)!=null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QS_QUESTION_SEARCH_LIST);
                    }
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_QUESTION_SEARCH_LIST,stringBuilder.toString());
                     setTopicId(getTopicId());
                 setCurrentAction("searchQuestionList");
                 setSubTopicMap(DataSourceDataProvider.getInstance().getSubTopicNamesMap(getTopicId()));
                    resultType = SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
           
        }//Close Session Checking
        return resultType;
    }

         
         
         /*
      *Desc : to delete the question i.e inactivating the question,,
      *Author : ajay tummapala
      *Email : atummapala@miraclesoft.com
      *
      */  
       
         public String doDeleteQuestion() throws Exception {
         resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            int empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
            String EmpLoginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
           
                try{
                  // StringBuilder stringBuilder = new StringBuilder();
                        
              
                   int isCreate = ServiceLocator.getEcertificationService().doDeleteQuestion(getQuestionId());
                setCheckDelete("yes");
                if(isCreate > 0) {
                    resultMessage = "<font color=\"green\" size=\"1.5\">Question inactivated successfully!</font>";
                     
                    setCurrentAction("createQuestion");
                    resultType = SUCCESS;
                } else  {
                    resultMessage = "<font color=\"red\" size=\"1.5\">Please try again!!</font>";
                 }
                setCurrentAction("doAddAuthors");
                httpServletRequest.getSession(false).setAttribute("resultMessage1", resultMessage);
             //  prepare();
               // System.out.println("before createdby2");
              //  setEflag(0);
                    resultType = SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
           
        }//Close Session Checking
        return resultType;
    }

    /*
          * Authors and subtopic methods start
          */
         
         
         public String viewAuthorsAndSubtopics() {
        int isAdd=0;
        String createdBy="";
        try{
            resultType = LOGIN;
            if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
                createdBy=httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();

             
                setTopicId(getTopicId());
                String topicDetails = DataSourceDataProvider.getInstance().getTopicNameById(getTopicId());
                String topicArr[] = topicDetails.split("@");
                setDomainName(topicArr[0]);
                setTopicName(topicArr[1]);
                setCurrentAuthorCollection(ServiceLocator.getEcertificationService().getAuthorsCollection(getTopicId()));
                setCurrentSubtopicCollection(ServiceLocator.getEcertificationService().getSubTopicsCollection(getTopicId()));
                
                
                resultType = SUCCESS;
            }//Closing Session checking
        } catch(Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
            ex.printStackTrace();
            resultType =  ERROR;
        }
        
        return resultType;
    }

 public String getSubTopic() throws Exception{
        resultType = LOGIN;
        String queryString = null;
        try {
            
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            setNavId(getNavId());
                
           setTopicsVTO(ServiceLocator.getEcertificationService().getSubTopic(getSubTopicId()));
           setTopicId(getTopicsVTO().getTopicId());
             viewAuthorsAndSubtopics();
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

public String deleteSubTopic() {
        int isDelete=0;
        
        try{
            resultType = LOGIN;
            String examList="EXAMLIST";
            if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
               
                String createdBy=httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                //System.out.println("1");
   
               
                isDelete = ServiceLocator.getEcertificationService().deleteSubTopic(getSubTopicId());
                
                if(isDelete > 0) {
                    resultMessage = "<font color=\"green\" size=\"1.5\">Subtopic Deleted successfully!</font>";
                     
                    
                    resultType = SUCCESS;
                } else  {
                    resultMessage = "<font color=\"red\" size=\"1.5\">Please try again!!</font>";
                 }
                viewAuthorsAndSubtopics();
                
                
            }//Closing Session checking
        } catch(Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
            ex.printStackTrace();
            resultType =  ERROR;
        }
        
        return resultType;
    }

      /*
    * Author : Ajay Tummapala
    * Desc : to get Add practice Screen
    */
         public String addPractice() throws Exception
         {
             resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
             setCurrentAction("doAddPractice");
             resultType= SUCCESS;
        }
        return resultType;
         }
     /*
      * Author Ajay Tummapala
      * Desc : too add practice and topic names
      * 
      */
         
         public String doAddOrUpdatePractice() throws Exception {
         resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            int empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
            String EmpLoginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
           
                try{
                  // StringBuilder stringBuilder = new StringBuilder();
                        
              if(getTopicId()==0)
              setCurrentAction("doAddPractice");
                        else
                            setCurrentAction("doUpdatePractice");
                   String resultMsg = ServiceLocator.getEcertificationService().doAddOrUpdatePractice(getDomainName(),getTopicName(),getTopicId(),getStatus(),EmpLoginId);
                
                   if(resultMsg.length()<50)
                    resultMessage = "<font color=\"green\" size=\"1.5\">"+resultMsg+"</font>";
                   else
                        resultMessage = "<font color=\"red\" size=\"1.5\">"+resultMsg+"</font>";
                     
                 //   setCurrentAction("createQuestion");
                   // resultType = SUCCESS;
               
               // setCurrentAction("doAddAuthors");
                httpServletRequest.getSession(false).setAttribute("resultMessage", resultMessage);
             //  prepare();
               // System.out.println("before createdby2");
              //  setEflag(0);
                    resultType = SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
           
        }//Close Session Checking
        return resultType;
    }

          public String getPractice() throws Exception {
         resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            int empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
            String EmpLoginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
           
                try{
                   ServiceLocator.getEcertificationService().getPractice(this,getTopicId());
                             //   System.out.println("Query-->"+stringBuilder.toString());
                    /*Removing Existed Session Object*/
            setCurrentAction("doUpdatePractice");
                   
             //  prepare();
               // System.out.println("before createdby2");
              //  setEflag(0);
                    resultType = SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
           
        }//Close Session Checking
        return resultType;
    }   
         
          
      /*
           * Questions back to List Ajay tummapala
           */
           public String questionsBackToList() throws Exception {
         resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
             StringBuilder stringBuilder = new StringBuilder();
              String EmpLoginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            // System.out.println("Sfalg----->"+getSflag());
          //  httpServletRequest.getSession(false).setAttribute(ApplicationConstants.SESSION_IS_SFLAG,getSflag());
              setAuthorList(DataSourceDataProvider.getInstance().getAuthorsByTopicId(getTopicId()));
              setTopicId(getTopicId());
              setCurrentAction("searchQuestionList");
              setSubTopicMap(DataSourceDataProvider.getInstance().getSubTopicNamesMap(getTopicId()));
                             //   System.out.println("Query-->"+stringBuilder.toString());
                    /*Removing Existed Session Object*/
           // String query= httpServletRequest.getSession(false).getAttribute("questionsQuery").toString();
            //httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_QUESTION_SEARCH_LIST,query);
                     
             return SUCCESS;
        }//Close Session Checking
        return resultType;
    }
           
           
     public String getSampleQuestionExcel() throws ServiceLocatorException {
          resultType = INPUT;
        OutputStream outputStream = null;
        InputStream inputStream = null;
       
           


                String responseString = "";
                try {
                    String fileLocation = "";

                    // fileLocation = generateEmployeeList(getLoginId());

                    fileLocation = Properties.getProperty("SAMPLE.ADD.QUESTIONS.EXCEL");
                    //System.out.println("----------->location...."+fileLocation);
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
                        Logger.getLogger(EcertificationAction.class.getName()).log(Level.SEVERE, null, ex1);
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



           
      
        resultType = SUCCESS;


      
        return resultType;
    }
 
      
           
           
           
         /*
          * Authors and subtopic methods end
          */
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public int getDomainId() {
        return domainId;
    }

    public void setDomainId(int domainId) {
        this.domainId = domainId;
    }

    public Map getDomainMap() {
        return domainMap;
    }

    public void setDomainMap(Map domainMap) {
        this.domainMap = domainMap;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public Map getTopicsMap() {
        return topicsMap;
    }

    public void setTopicsMap(Map topicsMap) {
        this.topicsMap = topicsMap;
    }

    public int getEflag() {
        return eflag;
    }

    public void setEflag(int eflag) {
        this.eflag = eflag;
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



    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
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
    
    //new setters and getters
    
     public String getSubtopic() {
        return subtopic;
    }

    public void setSubtopic(String subtopic) {
        this.subtopic = subtopic;
    }

    public int getTotQuestions() {
        return totQuestions;
    }

    public void setTotQuestions(int totQuestions) {
        this.totQuestions = totQuestions;
    }

    public int getTotDuration() {
        return totDuration;
    }

    public void setTotDuration(int totDuration) {
        this.totDuration = totDuration;
    }

    public int getTotMarks() {
        return totMarks;
    }

    public void setTotMarks(int totMarks) {
        this.totMarks = totMarks;
    }

    public int getMinMarks() {
        return minMarks;
    }

    public void setMinMarks(int minMarks) {
        this.minMarks = minMarks;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public int getQuesCreateSuccess() {
        return quesCreateSuccess;
    }

    public void setQuesCreateSuccess(int quesCreateSuccess) {
        this.quesCreateSuccess = quesCreateSuccess;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getTotalQues() {
        return totalQues;
    }

    public void setTotalQues(int totalQues) {
        this.totalQues = totalQues;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public QuestionsVTO getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(QuestionsVTO currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public int getSubTopicId() {
        return subTopicId;
    }

    public void setSubTopicId(int subTopicId) {
        this.subTopicId = subTopicId;
    }

    public String getCurrentAction() {
        return currentAction;
    }

    public void setCurrentAction(String currentAction) {
        this.currentAction = currentAction;
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

    public String getSubmitFrom() {
        return submitFrom;
    }

    public void setSubmitFrom(String submitFrom) {
        this.submitFrom = submitFrom;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public int getExamKeyId() {
        return examKeyId;
    }

    public void setExamKeyId(int examKeyId) {
        this.examKeyId = examKeyId;
    }

    public String getExamValidationKey() {
        return examValidationKey;
    }

    public void setExamValidationKey(String examValidationKey) {
        this.examValidationKey = examValidationKey;
    }

    public int getNoOfKeys() {
        return noOfKeys;
    }

    public void setNoOfKeys(int noOfKeys) {
        this.noOfKeys = noOfKeys;
    }

    public Map getSubTopicMap() {
        return subTopicMap;
    }

    public void setSubTopicMap(Map subTopicMap) {
        this.subTopicMap = subTopicMap;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPreAssignEmpId() {
        return preAssignEmpId;
    }

    public void setPreAssignEmpId(String preAssignEmpId) {
        this.preAssignEmpId = preAssignEmpId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    public String getDistinctSubTopicIds() {
        return distinctSubTopicIds;
    }

    public void setDistinctSubTopicIds(String distinctSubTopicIds) {
        this.distinctSubTopicIds = distinctSubTopicIds;
    }

    public List getExamDetailInfoList() {
        return examDetailInfoList;
    }

    public void setExamDetailInfoList(List examDetailInfoList) {
        this.examDetailInfoList = examDetailInfoList;
    }

    public String getSelectTab() {
        return selectTab;
    }

    public void setSelectTab(String selectTab) {
        this.selectTab = selectTab;
    }

    public int getEmpType() {
        return empType;
    }

    public void setEmpType(int empType) {
        this.empType = empType;
    }

    public int getSflag() {
        return sflag;
    }

    public void setSflag(int sflag) {
        this.sflag = sflag;
    }

    public String getConId() {
        return conId;
    }

    public void setConId(String conId) {
        this.conId = conId;
    }

    public int getInsSerachFlag() {
        return insSerachFlag;
    }

    public void setInsSerachFlag(int insSerachFlag) {
        this.insSerachFlag = insSerachFlag;
    }

    public String getCheckDelete() {
        return checkDelete;
    }

    public void setCheckDelete(String checkDelete) {
        this.checkDelete = checkDelete;
    }

    /**
     * @return the currentAuthorCollection
     */
    public Collection getCurrentAuthorCollection() {
        return currentAuthorCollection;
    }

    /**
     * @param currentAuthorCollection the currentAuthorCollection to set
     */
    public void setCurrentAuthorCollection(Collection currentAuthorCollection) {
        this.currentAuthorCollection = currentAuthorCollection;
    }

    /**
     * @return the currentSubtopicCollection
     */
    public Collection getCurrentSubtopicCollection() {
        return currentSubtopicCollection;
    }

    /**
     * @param currentSubtopicCollection the currentSubtopicCollection to set
     */
    public void setCurrentSubtopicCollection(Collection currentSubtopicCollection) {
        this.currentSubtopicCollection = currentSubtopicCollection;
    }

    /**
     * @return the authorId
     */
    public int getAuthorId() {
        return authorId;
    }

    /**
     * @param authorId the authorId to set
     */
    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    /**
     * @return the authorLoginId
     */
    public String getAuthorLoginId() {
        return authorLoginId;
    }

    /**
     * @param authorLoginId the authorLoginId to set
     */
    public void setAuthorLoginId(String authorLoginId) {
        this.authorLoginId = authorLoginId;
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
     * @return the createdDate
     */
    public String getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @return the authorStatus
     */
    public String getAuthorStatus() {
        return authorStatus;
    }

    /**
     * @param authorStatus the authorStatus to set
     */
    public void setAuthorStatus(String authorStatus) {
        this.authorStatus = authorStatus;
    }

    /**
     * @return the tempVar
     */
    public int getTempVar() {
        return tempVar;
    }

    /**
     * @param tempVar the tempVar to set
     */
    public void setTempVar(int tempVar) {
        this.tempVar = tempVar;
    }

    /**
     * @return the navId
     */
    public int getNavId() {
        return navId;
    }

    /**
     * @param navId the navId to set
     */
    public void setNavId(int navId) {
        this.navId = navId;
    }

    /**
     * @return the topicsVTO
     */
    public TopicsVTO getTopicsVTO() {
        return topicsVTO;
    }

    /**
     * @param topicsVTO the topicsVTO to set
     */
    public void setTopicsVTO(TopicsVTO topicsVTO) {
        this.topicsVTO = topicsVTO;
    }

    /**
     * @return the subTopicName
     */
    public String getSubTopicName() {
        return subTopicName;
    }

    /**
     * @param subTopicName the subTopicName to set
     */
    public void setSubTopicName(String subTopicName) {
        this.subTopicName = subTopicName;
    }

    /**
     * @return the subTopicStatus
     */
    public String getSubTopicStatus() {
        return subTopicStatus;
    }

    /**
     * @param subTopicStatus the subTopicStatus to set
     */
    public void setSubTopicStatus(String subTopicStatus) {
        this.subTopicStatus = subTopicStatus;
    }

    /**
     * @return the subtopicResultMessage
     */
    public String getSubtopicResultMessage() {
        return subtopicResultMessage;
    }

    /**
     * @param subtopicResultMessage the subtopicResultMessage to set
     */
    public void setSubtopicResultMessage(String subtopicResultMessage) {
        this.subtopicResultMessage = subtopicResultMessage;
    }

    /**
     * @return the authorList
     */
    public List getAuthorList() {
        return authorList;
    }

    /**
     * @param authorList the authorList to set
     */
    public void setAuthorList(List authorList) {
        this.authorList = authorList;
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
     * @return the itgBatch
     */
    public String getItgBatch() {
        return itgBatch;
    }

    /**
     * @param itgBatch the itgBatch to set
     */
    public void setItgBatch(String itgBatch) {
        this.itgBatch = itgBatch;
    }

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }
    
    
}
