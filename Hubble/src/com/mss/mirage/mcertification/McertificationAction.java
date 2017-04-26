/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.mirage.mcertification;
import com.mss.mirage.cre.CreReviewVTO;
import com.mss.mirage.ecertification.QuestionsVTO;
import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.PasswordUtility;
import com.mss.mirage.util.Properties;
import com.mss.mirage.util.ServiceLocator;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

/**
 *
 * @author miracle
 */
public class McertificationAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

    private String resultMessage;
    private HttpServletRequest httpServletRequest;
    private String resultType;
    private HttpServletResponse httpServletResponse;
     private String currentAction;
     private Map mcertTopicsMap = new HashMap();
    
     private List subtopics1; 
	private String examType;
	private int subtopicId1;
	 private int totalQues;
    private int totDuration;
    private int minMarks;
     private int totalQuest;
     private Map examTypeMap;
     private List recLocationList; 
     private String consultantId;
     private String consultantName;
     private String email;
     private String phoneNumber;
     
     
     private String loginId;
     private String password;
     
     
       private String domainName;
        private String topicName;
        
        private int durationTime;
        private int insTopicId;
        private int insdomainId;
        
        private Map questionsVtoMap = null;
        private int hideremainingQuestions;
        private int attemptedQuestions;
        private String examStatus;
        private int examMarks;
        private List examDetailInfoList;
        private int examNumber;
private String isLastExam;
private Collection currentQuestionsCollection;
 private CreReviewVTO creReviewVTO;
 private int insSearchFlag;
 private int empType;
 private String startDate;
 private String endDate;
 private String conId;
 private String empName;
 private int examKeyId;
 private int id;
 private int empId;
 private int examTypeId;
  private Collection currentExamReviewCollection;
     public String getMcertRecords() throws Exception{
        resultType = LOGIN;
        String queryString = null;
        try {
            
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            // queryString = "SELECT Id,ConsultentId,CONCAT(LName,'.',FName) AS ConsultentName,STATUS,Level FROM tblCreConsultentDetails";
               //httpServletRequest.setAttribute(ApplicationConstants.QUERY_STRING, queryString);
            //setSubTopicsMap(DataSourceDataProvider.getInstance().getSubTopicNamesMap(Integer.parseInt(Properties.getProperty("CRE.TopicId"))));    
            setExamTypeMap(ServiceLocator.getMcertificationService().getMcertExamTypeMap());    
           //setRecLocationList(DataSourceDataProvider.getInstance().getCollegeList());	
            resultType = SUCCESS;
            }//Closing Session checking
        } catch(Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
            resultType =  ERROR;
        }
        
        
        return resultType;
    }  
    
      public String setMcertExam() throws Exception{
    resultType = LOGIN;
     String queryString = null;
    try {

    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
         queryString = "SELECT Id,ExamType,MinMarks,Time,NoOfQuestions from tblMcertSetExam where Status='Active'";
        
            if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QUERY_STRING)!=null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QUERY_STRING);
                    }
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QUERY_STRING,queryString);
                    setResultMessage(getResultMessage());
            resultType = SUCCESS;
            
        }
    } catch(Exception ex){
        
        httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
        resultType =  ERROR;
    }
    return resultType;
    } 
     public String addMcertExamDetails() throws Exception
         {
            // System.out.println("into addExamDetails ");
             resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            //setCurrentAction("doAddSubTopics");
            setCurrentAction("doAddMcertSubTopics");
            setMcertTopicsMap(DataSourceDataProvider.getInstance().getSubTopicNamesMap(Integer.parseInt(Properties.getProperty("MCERT.TopicId"))));    
            
            resultType= SUCCESS;
        }
        return resultType;
         }
public String doAddMcertSubTopics() throws Exception {
        // System.out.println("into doAddSubTopics ");
         resultType = LOGIN;
         int result=0;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            int empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
            String EmpLoginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                try{
               
              if(getSubtopics1().size()==0)
              setCurrentAction("doAddMcertSubTopics");
                        else
                            setCurrentAction("doMcertUpdateSubTopics");
             // System.out.println("subtopics---->"+getSubtopics1());
                  result = ServiceLocator.getMcertificationService().doAddMcertSubTopics(getSubtopics1(),getExamType(),getMinMarks(),getTotalQuest(),getTotDuration(),getTotalQues());
                
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

public String getMcertExamDetails() throws Exception {
    // System.out.println("Into cre exam details-------->");
         resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
                try{
                   setMcertTopicsMap(DataSourceDataProvider.getInstance().getSubTopicNamesMap(Integer.parseInt(Properties.getProperty("MCERT.TopicId"))));    
                   ServiceLocator.getMcertificationService().getMcertExamDetails(this,getSubtopicId1());              
                   setCurrentAction("doMcertUpdateSubTopics");
                    resultType = SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
             }
        return resultType;
    }   
public String doMcertUpdateSubTopics() throws Exception {
       //  System.out.println("into doUpdateSubTopics ");
         resultType = LOGIN;
         boolean result=false;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            int empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
            String EmpLoginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                try{
               
            //  System.out.println("id----------------->"+getSubtopicId1());
                   setMcertTopicsMap(DataSourceDataProvider.getInstance().getSubTopicNamesMap(Integer.parseInt(Properties.getProperty("MCERT.TopicId"))));    
                  result = ServiceLocator.getMcertificationService().doMcertUpdateSubTopics(getSubtopicId1(),getSubtopics1(),getExamType(),getMinMarks(),getTotalQuest(),getTotDuration(),getTotalQues());
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
    public String inActiveMcertExam() throws Exception{
      resultType = LOGIN;
      boolean result=false;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
                try{
                  
                 result= ServiceLocator.getMcertificationService().inActiveMcertExam(this,getSubtopicId1());              
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

 public String mcertConsultantList() throws Exception{
    resultType = LOGIN;
    String queryString="";
    try {

    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
        
        queryString = "SELECT Id,LoginId,CONCAT(Fname,' ',Mname,'.',Lname) AS NAME ,Email,Phone,CreatedBy  FROM tblMcertConsultant";
          // httpServletRequest.setAttribute(ApplicationConstants.QUERY_STRING, queryString);
            
           System.out.println("Query-->"+queryString);
        // setExamTypeMap(DataSourceDataProvider.getInstance().getExamTypeMap()); 
          //  setRecLocationList(DataSourceDataProvider.getInstance().getCollegeList());
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

 
    public String searchMcertConsultants() throws Exception{
    resultType = LOGIN;
    StringBuffer queryString = new StringBuffer();
    try {

    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
         queryString.append("SELECT Id,LoginId,CONCAT(Fname,' ',Mname,'.',Lname) AS NAME ,Email,Phone,CreatedBy  FROM tblMcertConsultant where 1=1 ");
         if(getConsultantId()!=null && !"".equals(getConsultantId())) {
            queryString.append(" And LoginId = '"+getConsultantId()+"' ");
         }
          if(getConsultantName()!=null && !"".equals(getConsultantName())) {
            queryString.append(" And (FName LIKE '%"+getConsultantName()+"%' OR LName LIKE '%"+getConsultantName()+"%' OR MName LIKE '%"+getConsultantName()+"%') ");
         }
          if(getEmail()!=null && !"".equals(getEmail())) {
            queryString.append(" And (Email LIKE '%"+getEmail()+"%' OR Email LIKE '%"+getEmail()+"%' OR Email LIKE '%"+getEmail()+"%') ");
         }
           if(getPhoneNumber()!=null && !"".equals(getPhoneNumber())) {
            queryString.append(" And (Phone LIKE '%"+getPhoneNumber()+"%' OR Phone LIKE '%"+getPhoneNumber()+"%' OR Phone LIKE '%"+getPhoneNumber()+"%') ");
         }
         
     System.out.println("Query-->"+queryString.toString());
 
            if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QUERY_STRING)!=null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QUERY_STRING);
                    }
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QUERY_STRING,queryString.toString());
           //httpServletRequest.setAttribute(ApplicationConstants.QUERY_STRING, queryString.toString());
           setConsultantName(getConsultantName());
           setConsultantId(getConsultantId());
           setEmail(getEmail());
           setPhoneNumber(getPhoneNumber());
            resultType = SUCCESS;
        }//Closing Session checking
    } catch(Exception ex){
        //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
        httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
        resultType =  ERROR;
    }


    return resultType;
    } 
 public String execute() throws Exception {
         resultType = LOGIN;
        // System.out.println("Consultant Id--->"+getConsultantId());
         String consultantDetails[] = null;
         try{
         HttpSession session = httpServletRequest.getSession(true);
        // System.out.println("consultantDetails before-->"+consultantDetails);
         consultantDetails = ServiceLocator.getMcertificationService().mcertLoginCheck(getLoginId());
          System.out.println("consultantDetails-->"+consultantDetails.toString());
         if(consultantDetails[1] != null) {
             if("Active".equals(consultantDetails[4])){
                 //String creId =  getConsultantId().substring(6, getConsultantId().length());
                 String creId =  consultantDetails[0];
                 //String status = DataSourceDataProvider.getInstance().getExamKeyIdStatus(creId);
                 String status = consultantDetails[6];
                 System.out.println("status...."+status);
                 if(status.equals("Pending") || status.equals("InActive")){
                     resultType = LOGIN;
           httpServletRequest.setAttribute(ApplicationConstants.MCERT_REQ_ERR_MESSAGE, "<font color='red' size='2px'> Your exam terminated abruptly.Please,Contact Operations team.</font>");
                 } else{ 
                    // System.out.println("before eqials--->"+getConsultantEmail() + " gffdgdfg"+consultantDetails[2]);
               if(getPassword().equals(PasswordUtility.decryptPwd( consultantDetails[5]))) {
                  
                   // session.setAttribute(ApplicationConstants.SES_CONSULTANT_LOGIN_ID, consultantDetails[0]);
                   session.setAttribute(ApplicationConstants.SESSION_USER_ID, consultantDetails[1]);
                  session.setAttribute(ApplicationConstants.SES_CONSULTANT_NAME, consultantDetails[2]);
                  //session.setAttribute(ApplicationConstants.SES_CONSULTANT_ID, consultantDetails[4]);
                  session.setAttribute(ApplicationConstants.SESSION_EMP_ID, consultantDetails[0]);
                 // session.setAttribute(ApplicationConstants.SESSION_CRE_MIN_MARKS, consultantDetails[7]);
                 // session.setAttribute(ApplicationConstants.SESSION_CRE_TOTAL_QUESTIONS, consultantDetails[6]);
                 // session.setAttribute(ApplicationConstants.SESSION_CRE_TOTAL_MARKS, consultantDetails[6]);
                 // session.setAttribute(ApplicationConstants.SESSION_CRE_DURATION, consultantDetails[8]);
                 // session.setAttribute(ApplicationConstants.SESSION_CRE_ExamIds_List, DataSourceDataProvider.getInstance().getExamsList(Integer.parseInt(creId)));
                  
                System.out.println("Exams List-->.................."+consultantDetails[7]);
                if("".equals(consultantDetails[7])){
                    System.out.println("in Exams List if.......");
                    resultType = LOGIN;
           httpServletRequest.setAttribute(ApplicationConstants.MCERT_REQ_ERR_MESSAGE, "<font color='red' size='2px'> Sorry , none of the exams are assigned to you .Please,Contact Operations team.</font>");
               
                }else{
                   session.setAttribute(ApplicationConstants.SESSION_MCERT_ExamIds_List, DataSourceDataProvider.getInstance().getExamsListByListString(consultantDetails[7]));
                   System.out.println("Exams List-->"+123);
                  
                  //session.setAttribute(ApplicationConstants.SESSION_CRE_SUBTOPICS, DataSourceDataProvider.getInstance().getSubTopicNamesByCreId(Integer.parseInt(consultantDetails[4])));
                   resultType = SUCCESS;
                }
               }else {
                   resultType = LOGIN;
           httpServletRequest.setAttribute(ApplicationConstants.MCERT_REQ_ERR_MESSAGE, "<font color='red' size='2px'> Invalid Password!</font>");
               }
             }
             }else {
                 resultType = LOGIN;
                 System.out.println("consultantDetails[4]..."+consultantDetails[4]);
                 if(consultantDetails[4].equals("Registered")) {
                httpServletRequest.setAttribute(ApplicationConstants.MCERT_REQ_ERR_MESSAGE, "<font color='red' size='1px'> Oops your details are not activated. Please contact HR Team!</font>"); 
             }else  if(consultantDetails[4].equals("Rejected")){
                 httpServletRequest.setAttribute(ApplicationConstants.MCERT_REQ_ERR_MESSAGE, "<font color='red' size='1px'>Sorry, you have Rejected!</font>"); 
             }
             else  if(consultantDetails[4].equals("PASS") || consultantDetails[4].equals("FAIL")){
                 httpServletRequest.setAttribute(ApplicationConstants.MCERT_REQ_ERR_MESSAGE, "<font color='red' size='1px'>Oops your exam completed!</font>"); 
             }  
         }
         }else {
           resultType = LOGIN;
           httpServletRequest.setAttribute(ApplicationConstants.MCERT_REQ_ERR_MESSAGE, "<font color='red' size='2px'>Invalid Login Id!</font>");
         }
         
         
         }catch(Exception ex){
             ex.printStackTrace();
             System.out.println("Error-->"+ex.getMessage());
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
            resultType =  ERROR;
        }
         System.out.println("resultType-->"+resultType);
         return resultType;
     }
 
 
  public String getLoginCheckDetails() throws Exception {
         resultType = LOGIN;
        // System.out.println("Consultant Id--->"+getConsultantId());
         String consultantDetails[] = null;
         try{
         HttpSession session = httpServletRequest.getSession(true);
        // System.out.println("consultantDetails before-->"+consultantDetails);
         
         List completedList=ServiceLocator.getMcertificationService().getExamCompletedList(getEmpId());
         boolean isCompleted=false;
          for(int i=0;i<completedList.size();i++)
        {
            if((Integer)completedList.get(i)==getExamTypeId())
            {
              // System.out.println("isexam -->"+examCompleted.get(i));
                isCompleted= true;
            }
        }
        if(!isCompleted){
         consultantDetails = ServiceLocator.getMcertificationService().mcertLoginCheck1(getEmpId(),getExamTypeId());
          
         if(consultantDetails[1] != null) {
         
                 //String creId =  getConsultantId().substring(6, getConsultantId().length());
                 String creId =  consultantDetails[0];
                 //String status = DataSourceDataProvider.getInstance().getExamKeyIdStatus(creId);
               
                 
                    // System.out.println("before eqials--->"+getConsultantEmail() + " gffdgdfg"+consultantDetails[2]);
                  // session.setAttribute(ApplicationConstants.SES_CONSULTANT_LOGIN_ID, consultantDetails[0]);
                   session.setAttribute(ApplicationConstants.SESSION_USER_ID, consultantDetails[1]);
                  session.setAttribute(ApplicationConstants.SES_CONSULTANT_NAME, consultantDetails[2]);
                  //session.setAttribute(ApplicationConstants.SES_CONSULTANT_ID, consultantDetails[4]);
                  session.setAttribute(ApplicationConstants.SESSION_EMP_ID, consultantDetails[0]);
                 // session.setAttribute(ApplicationConstants.SESSION_CRE_MIN_MARKS, consultantDetails[7]);
                 // session.setAttribute(ApplicationConstants.SESSION_CRE_TOTAL_QUESTIONS, consultantDetails[6]);
                 // session.setAttribute(ApplicationConstants.SESSION_CRE_TOTAL_MARKS, consultantDetails[6]);
                 // session.setAttribute(ApplicationConstants.SESSION_CRE_DURATION, consultantDetails[8]);
                 // session.setAttribute(ApplicationConstants.SESSION_CRE_ExamIds_List, DataSourceDataProvider.getInstance().getExamsList(Integer.parseInt(creId)));
                  
               
                   session.setAttribute(ApplicationConstants.SESSION_MCERT_ExamIds_List, DataSourceDataProvider.getInstance().getExamsListByListString(consultantDetails[5]));
                   
                  
                  //session.setAttribute(ApplicationConstants.SESSION_CRE_SUBTOPICS, DataSourceDataProvider.getInstance().getSubTopicNamesByCreId(Integer.parseInt(consultantDetails[4])));
                   resultType = SUCCESS;
              
               
           
             
         }else {
           resultType = LOGIN;
           httpServletRequest.setAttribute(ApplicationConstants.MCERT_REQ_ERR_MESSAGE, "<font color='red' size='2px'>Invalid Login Id!</font>");
         }
        }else {
            resultType = LOGIN;
           httpServletRequest.setAttribute(ApplicationConstants.MCERT_REQ_ERR_MESSAGE, "<font color='red' size='2px'>Opps! Already you have completed this exam!</font>");
        }
         
         }catch(Exception ex){
             ex.printStackTrace();
             System.out.println("Error-->"+ex.getMessage());
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
            resultType =  ERROR;
        }
         
         return resultType;
     }
 
 
     public String doMcertLogout() throws Exception {
        System.out.println("CRE Logout");
        try{
            if(httpServletRequest.getSession(false) != null){
                httpServletRequest.getSession(false).invalidate();
              }
            resultType =SUCCESS;
        }catch(Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
            resultType =  ERROR;
        }
        return resultType;
    }
     
     
     public String mcertStartExam() throws Exception {
        try{
            resultType = LOGIN;
            //ApplicationConstants.SES_CONSULTANT_ID
            if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
                System.out.println("in mcertStartExam...");
                setInsTopicId(Integer.parseInt(Properties.getProperty("MCERT.TopicId")));
                setInsdomainId(Integer.parseInt(Properties.getProperty("MCERT.DomainId")));
                setDomainName("Mcertification");
                setTopicName("Mcertification");
                
                int creId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
                String creLoginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                List examsList = (List)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MCERT_ExamIds_List);
                CreReviewVTO creReviewVTO = (CreReviewVTO)examsList.get(getExamNumber());
                int examTypeId = creReviewVTO.getExamTypeId();
                setExamType(creReviewVTO.getExamTypeName());
                setCreReviewVTO(ServiceLocator.getMcertificationService().mcertStartExam(creId,examTypeId));
                setTotalQuest(getCreReviewVTO().getTotalQues());
                setDurationTime(getCreReviewVTO().getTotDuration());
                httpServletRequest.getSession(false).setAttribute(ApplicationConstants.SESSION_Mcert_MIN_MARKS,getCreReviewVTO().getMinMarks());
                
               if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.MCERT_QUESTIONS_MAP)!=null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.MCERT_QUESTIONS_MAP);
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.MCERT_QUESTIONS_MAP,null);
                    }
            if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.MCERT_CURRENT_EXAM_KEY)!=null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.MCERT_CURRENT_EXAM_KEY);
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.MCERT_CURRENT_EXAM_KEY,null);
                    }
           
             httpServletRequest.getSession(false).setAttribute(ApplicationConstants.MCERT_CURRENT_EXAM_KEY,getCreReviewVTO().getExamKey());
              httpServletRequest.getSession(false).setAttribute(ApplicationConstants.MCERT_QUESTIONS_MAP,getCreReviewVTO().getQuestionVtoMap()); 
               setExamNumber(getExamNumber()+1);
               setCurrentQuestionsCollection(getCreReviewVTO().getQuestionVtoMap().values());
               
               resultType = SUCCESS;
            }//Closing Session checking
        } catch(Exception ex){
            ex.printStackTrace();
            System.out.println("ExamkeyError-->"+ex.getMessage());
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.getMessage());
            resultType =  ERROR;
        }
        
        return resultType;
    }
     
     
      public String submitMcertExam() throws Exception {
        try{
            System.out.println("in submitMcertExam...");
            resultType = LOGIN;
            if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
                
                String ConsultantId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
              QuestionsVTO questionVTO = null; 
              int attemptedQuestionsResult = 0;
              int examKey = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.MCERT_CURRENT_EXAM_KEY).toString());
              int empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
              String EmpLoginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
             
                 // int attemptedQuestions = getTotalQuest()-getHideremainingQuestions();
               int noOfAttempted = 0;
                   int minMarks = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_Mcert_MIN_MARKS).toString());
                   setMinMarks(minMarks);
                   setExamNumber(getExamNumber());
                   
                   List examList = (List)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MCERT_ExamIds_List);
                  if(examList.size()>getExamNumber())
                   setIsLastExam("NO");
                  else
                      setIsLastExam("YES");
                  
                  
                  
             
            Map questionVtoMap = (Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.MCERT_QUESTIONS_MAP);
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
                        attemptedQuestionsResult = DataSourceDataProvider.getInstance().isMcertQuestionAttempt(examKey,ExamQuestionId);
                        
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
             ServiceLocator.getMcertificationService().doSubmitMcertExam(examKey,empId,questionVtoMap.size(),noOfAttempted,minMarks,questionVtoList,this);
             //System.out.println("questionVtoList-->"+questionVtoList);
       
          setExamType(getExamType());
             if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.MCERT_QUESTIONS_MAP)!=null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.MCERT_QUESTIONS_MAP);
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.MCERT_QUESTIONS_MAP,null);
                    }
            if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.MCERT_CURRENT_EXAM_KEY)!=null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.MCERT_CURRENT_EXAM_KEY);
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.MCERT_CURRENT_EXAM_KEY,null);
                    }
            //System.out.println("resultType-------------------------->");
               resultType = SUCCESS;
            }//Closing Session checking
        } catch(Exception ex){
            ex.printStackTrace();
            System.out.println("ExamkeyError-->"+ex.getMessage());
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.getMessage());
            resultType =  ERROR;
        }
        
        return resultType;
    }
    
       public String getMcertExamResultsList() throws Exception {
           System.out.println("in getMcertExamResultsList....");
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
                    
                  /*  stringBuilder.append("SELECT tblMcertResult.EmpId AS empId,TopicId,TopicName,ROUND((Marks/TotalQuestions)*100,2) AS Percentage,ExamKeyId,Marks,TotalQuestions,AttemptedQuestions,DateSubmitted,ExamStatus "+
                    "FROM tblMcertResult LEFT OUTER JOIN tblMcertKey ON (tblMcertResult.ExamKeyId=tblMcertKey.ID) "+
                    "LEFT OUTER JOIN tblEcertTopics ON (tblMcertKey.TopicId=tblEcertTopics.ID) WHERE date(DateSubmitted) >= '"+DateUtility.getInstance().PriviousSeventhDay()+"'  ORDER BY ExamKeyId DESC LIMIT 100"); */
                  
                     stringBuilder.append("SELECT tblMcertResult.EmpId AS empId,CONCAT(Fname,' ',Mname,'.',Lname) AS NAME,tblMcertSetExam.ExamType AS examType,ROUND((Marks/TotalQuestions)*100,2) AS Percentage,ExamKeyId,Marks,TotalQuestions,AttemptedQuestions,DateSubmitted,ExamStatus "+
                    "FROM tblMcertResult LEFT  JOIN tblMcertKey ON (tblMcertResult.ExamKeyId=tblMcertKey.ID) "+
                    "LEFT JOIN tblMcertSetExam  ON tblMcertSetExam.Id=tblMcertKey.ExamTypeId LEFT JOIN tblMcertConsultant ON tblMcertResult.EmpId=tblMcertConsultant.LoginId WHERE date(DateSubmitted) >= '"+DateUtility.getInstance().PriviousSeventhDay()+"'  ORDER BY ExamKeyId DESC LIMIT 100");
                    
                    System.out.println("query......"+stringBuilder.toString());
                    /*Removing Existed Session Object*/
                    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.MCERT_MY_EXAM_RESULT_QUERY)!=null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.MCERT_MY_EXAM_RESULT_QUERY);
                    }
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.MCERT_MY_EXAM_RESULT_QUERY,stringBuilder.toString());
                   
               setCurrentAction("searchMcertExamResultList");
               setInsSearchFlag(0);
                    resultType = SUCCESS;
                }catch (Exception ex){
                    ex.printStackTrace();
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
           
        }//Close Session Checking
        return resultType;
    }
   
    public String doSearchMcertExamResultList() throws Exception {
        System.out.println("in doSearchMcertExamResultList.....");
         resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            int empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
            String EmpLoginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
           
                try{
                    StringBuilder stringBuilder = new StringBuilder();
                    /*stringBuilder.append("SELECT tblEcertResult.EmpId AS empId,TopicName,ROUND((Marks/TotalQuestions)*100,2) AS Percentage ,ExamKeyId,Marks,TotalQuestions,AttemptedQuestions,DateSubmitted,ExamStatus "+
                    "FROM tblEcertResult LEFT OUTER JOIN tblEcertKey ON (tblEcertResult.ExamKeyId=tblEcertKey.ID) "+
                    "LEFT OUTER JOIN tblEcertTopics ON (tblEcertKey.TopicId=tblEcertTopics.ID) ");*/
                    
                    stringBuilder.append("SELECT tblMcertResult.EmpId AS empId,TopicId,TopicName,ROUND((Marks/TotalQuestions)*100,2) AS Percentage ,ExamKeyId,Marks,TotalQuestions,AttemptedQuestions,DateSubmitted,ExamStatus "+
                    "FROM tblMcertResult LEFT OUTER JOIN tblMcertKey ON (tblMcertResult.ExamKeyId=tblMcertKey.ID) "+
                    "LEFT OUTER JOIN tblEcertTopics ON (tblMcertKey.TopicId=tblEcertTopics.ID) ");
                    
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
                            stringBuilder.append("tblMcertResult.EmpType =" + getEmpType()+" ");
                            andFlag = true;
                        }else if(!"".equals(getEmpType()) && andFlag){
                            stringBuilder.append("AND tblMcertResult.EmpType =" + getEmpType()+" ");
                        }
                     }
                     
                   // System.out.println("getEmpType()---->"+getEmpType()+"-------------------getLoginId()--------"+getLoginId());
                     
                     if(getEmpType()==1){
                       if(!"".equals(getLoginId()) && !andFlag){
                            stringBuilder.append("tblMcertResult.EmpId = '" + getLoginId() + "' ");
                            andFlag = true;
                        }else if(!"".equals(getLoginId()) && andFlag){
                            stringBuilder.append("AND tblMcertResult.EmpId = '" + getLoginId() + "' ");
                        }
                     }
                     if(getEmpType()==2){
                        if(!"".equals(getConId()) && !andFlag){
                            stringBuilder.append("tblMcertResult.EmpId LIKE '%" + getConId() + "%' ");
                            andFlag = true;
                        }else if(!"".equals(getConId()) && andFlag){
                            stringBuilder.append("AND tblMcertResult.EmpId LIKE '%" + getConId() + "%' ");
                        }
                     }
                     
                    stringBuilder.append(" ORDER BY ExamKeyId LIMIT 100");
                    setCurrentAction("searchMcertExamResultList");
                      System.out.println("query......"+stringBuilder.toString());
                    //System.out.println("Query-->"+stringBuilder.toString());
                    /*Removing Existed Session Object*/
                    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.MCERT_MY_EXAM_RESULT_QUERY)!=null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.MCERT_MY_EXAM_RESULT_QUERY);
                    }
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.MCERT_MY_EXAM_RESULT_QUERY,stringBuilder.toString());
                     
                    setInsSearchFlag(1);
                    setStartDate(getStartDate());
                    setEndDate(getEndDate());
                    setEmpType(getEmpType());
                    setConId(getConId());
                    setEmpName(getEmpName());
                    setLoginId(getLoginId());
                   
               
                    resultType = SUCCESS;
                }catch (Exception ex){
                    ex.printStackTrace();
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
           
        }//Close Session Checking
        return resultType;
    }
    
    
     public String reviewMcertExam() {
           resultType = LOGIN;
           List reviewList = null;
           try {
           reviewList = ServiceLocator.getMcertificationService().reviewMcertExam(getExamKeyId());
              httpServletRequest.setAttribute(ApplicationConstants.MCERT_REVIEW_LIST,reviewList);
               resultType = SUCCESS; 
           } catch(Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
            ex.printStackTrace();
            resultType =  ERROR;
        }
           return resultType;
      }
    
        public String getMcertConsultantReview() throws Exception{
        resultType = LOGIN;
        String queryString = null;
        try {
            
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
             setConsultantName(DataSourceDataProvider.getInstance().getMcertConsultantNameById(getId()));
           // setConsultantName(DataSourceDataProvider.getInstance().getCreConsultantNameById(getId()));
           // setCurrentTechReviewCollection(ServiceLocator.getCreService().getCreTechReviewCollection(getId(),15));
           // setCurrentHrReviewCollection(ServiceLocator.getCreService().getCreHrReviewCollection(getId(),15));
          //  setCurrentVpReviewCollection(ServiceLocator.getCreService().getCreVpReviewCollection(getId(),15));
               setCurrentExamReviewCollection(ServiceLocator.getMcertificationService().getMcertExamReviewCollection(getId(), 15));
           //    setCurrentOnboardReviewCollection(ServiceLocator.getCreService().getCreOnboardReviewCollection(getId(), 15));
                resultType = SUCCESS;
            }//Closing Session checking
        } catch(Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
            resultType =  ERROR;
        }
        
        
        return resultType;
    } 
     
     
    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
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
     * @return the mcertTopicsMap
     */
    public Map getMcertTopicsMap() {
        return mcertTopicsMap;
    }

    /**
     * @param mcertTopicsMap the mcertTopicsMap to set
     */
    public void setMcertTopicsMap(Map mcertTopicsMap) {
        this.mcertTopicsMap = mcertTopicsMap;
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
     * @return the totalQuest
     */
    public int getTotalQuest() {
        return totalQuest;
    }

    /**
     * @param totalQuest the totalQuest to set
     */
    public void setTotalQuest(int totalQuest) {
        this.totalQuest = totalQuest;
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
     * @return the consultantId
     */
    public String getConsultantId() {
        return consultantId;
    }

    /**
     * @param consultantId the consultantId to set
     */
    public void setConsultantId(String consultantId) {
        this.consultantId = consultantId;
    }

    /**
     * @return the consultantName
     */
    public String getConsultantName() {
        return consultantName;
    }

    /**
     * @param consultantName the consultantName to set
     */
    public void setConsultantName(String consultantName) {
        this.consultantName = consultantName;
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
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return the loginId
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * @param loginId the loginId to set
     */
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the domainName
     */
    public String getDomainName() {
        return domainName;
    }

    /**
     * @param domainName the domainName to set
     */
    public void setDomainName(String domainName) {
        this.domainName = domainName;
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
     * @return the durationTime
     */
    public int getDurationTime() {
        return durationTime;
    }

    /**
     * @param durationTime the durationTime to set
     */
    public void setDurationTime(int durationTime) {
        this.durationTime = durationTime;
    }

    /**
     * @return the insTopicId
     */
    public int getInsTopicId() {
        return insTopicId;
    }

    /**
     * @param insTopicId the insTopicId to set
     */
    public void setInsTopicId(int insTopicId) {
        this.insTopicId = insTopicId;
    }

    /**
     * @return the insdomainId
     */
    public int getInsdomainId() {
        return insdomainId;
    }

    /**
     * @param insdomainId the insdomainId to set
     */
    public void setInsdomainId(int insdomainId) {
        this.insdomainId = insdomainId;
    }

    /**
     * @return the questionsVtoMap
     */
    public Map getQuestionsVtoMap() {
        return questionsVtoMap;
    }

    /**
     * @param questionsVtoMap the questionsVtoMap to set
     */
    public void setQuestionsVtoMap(Map questionsVtoMap) {
        this.questionsVtoMap = questionsVtoMap;
    }

    /**
     * @return the hideremainingQuestions
     */
    public int getHideremainingQuestions() {
        return hideremainingQuestions;
    }

    /**
     * @param hideremainingQuestions the hideremainingQuestions to set
     */
    public void setHideremainingQuestions(int hideremainingQuestions) {
        this.hideremainingQuestions = hideremainingQuestions;
    }

    /**
     * @return the attemptedQuestions
     */
    public int getAttemptedQuestions() {
        return attemptedQuestions;
    }

    /**
     * @param attemptedQuestions the attemptedQuestions to set
     */
    public void setAttemptedQuestions(int attemptedQuestions) {
        this.attemptedQuestions = attemptedQuestions;
    }

    /**
     * @return the examStatus
     */
    public String getExamStatus() {
        return examStatus;
    }

    /**
     * @param examStatus the examStatus to set
     */
    public void setExamStatus(String examStatus) {
        this.examStatus = examStatus;
    }

    /**
     * @return the examMarks
     */
    public int getExamMarks() {
        return examMarks;
    }

    /**
     * @param examMarks the examMarks to set
     */
    public void setExamMarks(int examMarks) {
        this.examMarks = examMarks;
    }

    /**
     * @return the examDetailInfoList
     */
    public List getExamDetailInfoList() {
        return examDetailInfoList;
    }

    /**
     * @param examDetailInfoList the examDetailInfoList to set
     */
    public void setExamDetailInfoList(List examDetailInfoList) {
        this.examDetailInfoList = examDetailInfoList;
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
     * @return the creReviewVTO
     */
    public CreReviewVTO getCreReviewVTO() {
        return creReviewVTO;
    }

    /**
     * @param creReviewVTO the creReviewVTO to set
     */
    public void setCreReviewVTO(CreReviewVTO creReviewVTO) {
        this.creReviewVTO = creReviewVTO;
    }

    /**
     * @return the insSearchFlag
     */
    public int getInsSearchFlag() {
        return insSearchFlag;
    }

    /**
     * @param insSearchFlag the insSearchFlag to set
     */
    public void setInsSearchFlag(int insSearchFlag) {
        this.insSearchFlag = insSearchFlag;
    }

    /**
     * @return the empType
     */
    public int getEmpType() {
        return empType;
    }

    /**
     * @param empType the empType to set
     */
    public void setEmpType(int empType) {
        this.empType = empType;
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
     * @return the conId
     */
    public String getConId() {
        return conId;
    }

    /**
     * @param conId the conId to set
     */
    public void setConId(String conId) {
        this.conId = conId;
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
     * @return the examKeyId
     */
    public int getExamKeyId() {
        return examKeyId;
    }

    /**
     * @param examKeyId the examKeyId to set
     */
    public void setExamKeyId(int examKeyId) {
        this.examKeyId = examKeyId;
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
     * @return the empId
     */
    public int getEmpId() {
        return empId;
    }

    /**
     * @param empId the empId to set
     */
    public void setEmpId(int empId) {
        this.empId = empId;
    }

    /**
     * @return the examTypeId
     */
    public int getExamTypeId() {
        return examTypeId;
    }

    /**
     * @param examTypeId the examTypeId to set
     */
    public void setExamTypeId(int examTypeId) {
        this.examTypeId = examTypeId;
    }
}
