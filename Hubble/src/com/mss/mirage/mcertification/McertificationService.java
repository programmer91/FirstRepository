/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.mirage.mcertification;

import com.mss.mirage.cre.CreReviewVTO;
import com.mss.mirage.util.ServiceLocatorException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 *
 * @author miracle
 */
public interface McertificationService {
    public int doAddMcertSubTopics(List subTopicId,String examType,int minMarks,int noOfQuestions,int time,int totalQuestionsFromEachSub) throws ServiceLocatorException;
    public String getMcertExamDetails(McertificationAction mcertificationAction,int examId1) throws ServiceLocatorException;
    public boolean doMcertUpdateSubTopics(int examId,List subTopicId,String examType,int minMarks,int noOfQuestions,int time,int totalQuestionsFromEachSub) throws ServiceLocatorException;
    public boolean inActiveMcertExam(McertificationAction mcertificationAction,int examId1) throws ServiceLocatorException ;
    public Map getMcertExamTypeMap() throws ServiceLocatorException ;
    public String[] mcertLoginCheck(String loginId) throws ServiceLocatorException;
    
     public String[] mcertLoginCheck1(int empId,int examTypeId) throws ServiceLocatorException;
    
     public CreReviewVTO mcertStartExam(int creId,int examTypeId) throws ServiceLocatorException ;
     
      public void doSubmitMcertExam(int examKey,int creId,int totalQuest,int attemptedQuest,int minMarks,String questionVtoList,McertificationAction mcertificationAction ) throws ServiceLocatorException;
      
        public List  reviewMcertExam(int examKeyId) throws ServiceLocatorException;
        
            public Collection getMcertExamReviewCollection(int creId,int noOfRecords) throws ServiceLocatorException;
            public List getExamCompletedList(int actualId) throws ServiceLocatorException ;
}
