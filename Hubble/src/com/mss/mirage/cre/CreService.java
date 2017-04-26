/*
 * CreService.java
 *
 * Created on August 29, 2013, 4:41 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.cre;

import com.mss.mirage.util.ServiceLocatorException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
/**
 *
 * @author miracle
 */
public interface CreService {
    
    
//    public void getCreDetailResult(int examKeyId, CreAction creAction) throws ServiceLocatorException;
    //public int insertEmpExamResult(int examKeyId,String EmpLoginId,int totalQuest, int attemptedQuest, int marks,String examStatus)  throws ServiceLocatorException;
    
    /**
     *  To edit consultent details
     */
    
    public void getConsultantDetails(CreAction creAction) throws ServiceLocatorException;
    public int updateConsultantDetails(CreAction creAction,String loginId) throws ServiceLocatorException;
    
    /** Creates a new instance of CreService */
    public Collection getCreTechReviewCollection(int creId,int noOfRecords) throws ServiceLocatorException;
    public boolean creTechReviewAdd(CreAction creAction) throws ServiceLocatorException ;
    public CreReviewVTO getTechLeadReview(int techReviewId, int creId) throws ServiceLocatorException;
    public boolean updateTechReview(CreAction creAction) throws ServiceLocatorException ;
    public int deleteTechLeadReview(int techReviewId) throws ServiceLocatorException;

    public Collection getCreHrReviewCollection(int creId,int noOfRecords) throws ServiceLocatorException;
    public boolean creHrReviewAdd(CreAction creAction) throws ServiceLocatorException;

    public CreReviewVTO getHrReview(int hrReviewId, int creId) throws ServiceLocatorException;
    public boolean updateHrReview(CreAction creAction) throws ServiceLocatorException;
    public int deleteHrReview(int hrReviewId) throws ServiceLocatorException;

    public Collection getCreVpReviewCollection(int creId,int noOfRecords) throws ServiceLocatorException;
    public boolean creVpReviewAdd(CreAction creAction) throws ServiceLocatorException;
    public CreReviewVTO getVpReview(int vpReviewId, int creId) throws ServiceLocatorException ;
    public boolean updateVpReview(CreAction creAction) throws ServiceLocatorException ;
    public int deleteVpReview(int vpReviewId) throws ServiceLocatorException;
    
     public void updateCREConsultantStatus(int creConsultantId,String status,String level,String loginId) throws ServiceLocatorException;
     
     public Collection getCreExamReviewCollection(int creId,int noOfRecords) throws ServiceLocatorException;
     
     //public void updateCREExamStatus(int creConsultantId,String status,String level) throws ServiceLocatorException;
     
     /*
      * Newly added methods For getquestions Start 11112013
      */
     //public CreReviewVTO doExamProcess(int creId, String creLoginId, int domainId, int topicId) throws ServiceLocatorException;
     //public Map getCreQuestions(int examKey) throws ServiceLocatorException;
     /*
      * Newly added methods For getquestions end 11112013
      */
     // Added by aditya
      public int doAddSubTopics(List subTopicId,String examType,int minMarks,int noOfQuestions,int time,int totalQuestionsFromEachSub) throws ServiceLocatorException;
      public String getCreExamDetails(CreAction creAction,int examId1) throws ServiceLocatorException;
     public boolean doUpdateSubTopics(int examId,List subTopicId,String examType,int minMarks,int noOfQuestions,int time,int totalQuestionsFromEachSub) throws ServiceLocatorException;
      public boolean inActiveCreExam(CreAction creAction,int examId1) throws ServiceLocatorException;
      /*
       * Created By Santosh Kola
       * Date : 03/06/2014
       */
      public CreReviewVTO doStartExam(int creId,int examTypeId) throws ServiceLocatorException ;
       public void doSubmitExam(int examKey,int creId,int totalQuest,int attemptedQuest,int minMarks,String questionVtoList,CreAction creAction ) throws ServiceLocatorException;
       /*
        * New methods for Onboard Comments
        * Date : 08/15/2014
        * Author : Santosh Kola
        */
         public Collection getCreOnboardReviewCollection(int creId,int noOfRecords) throws ServiceLocatorException;
    public boolean creOnboardReviewAdd(CreAction creAction) throws ServiceLocatorException ;
    public CreReviewVTO getOnboardReview(int onboardReviewId, int creId) throws ServiceLocatorException;
    public boolean updateOnboardReview(CreAction creAction) throws ServiceLocatorException ;
    public void updateCREOnboardLevel(int creConsultantId,String loginId) throws ServiceLocatorException;
    // public String generateExcelForCreConsultantSearch(CreAction creAction) throws ServiceLocatorException;
     public String generateExcelForCreConsultantSearchV2(CreAction creAction) throws ServiceLocatorException;
     
}
