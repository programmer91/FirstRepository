/*
 * EcertificationService.java
 *
 * Created on July 18, 2013, 6:31 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.ecertification;

import com.mss.mirage.util.ServiceLocatorException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author miracle
 */
public interface EcertificationService {
    
    /** Creates a new instance of EcertificationService */
    
    
        /*public List<QuestionVTO> getQuestions(int subTopicId) throws ServiceLocatorException;*/
        public Map getQuestions(int topicId,HttpServletRequest httpServletRequest,int totalQuestions,int maxKeyId) throws ServiceLocatorException;
        public int insertExamKey(int insDomainId,int insTopicId, HttpServletRequest httpServletRequest)  throws ServiceLocatorException;
        public int getResult(int examKeyId)  throws ServiceLocatorException;
        public int insertEmpExamResult(int examKeyId,String EmpLoginId,int totalQuest, int attemptedQuest, int marks,String examStatus)  throws ServiceLocatorException ;

        //new service methods

        // public int doCreateExam(String createdBy,String domainName,String topicName,String subtopic,int totQuestions,int totDuration,int totMarks,int domainId,int minMarks) throws ServiceLocatorException;
        public int doAddQuestion(String createdBy,String question,String option1,String option2,String option3,String option4,int answer,String description,int TopicID,int subTopicId) throws ServiceLocatorException;


        public int updateQuestion(String modifiedBy,String question,String option1,String option2,String option3,String option4,int answer,String description,int topicId,int questionId) throws ServiceLocatorException;


        public QuestionsVTO getQuestion(int questionId) throws ServiceLocatorException,SQLException ;
        public List  reviewExam(int examKeyId) throws ServiceLocatorException;

        public int doInactiveExamKey(String examvalidationKey, String userId) throws ServiceLocatorException;


        /**
        * To addkeys service
        */
       // public int doAddKey(String createdBy,int topicId,int noOfKeys,int duration,int totalQuestions,int minMarks) throws ServiceLocatorException;
       public int doAddKey(Object key,String createdBy,int topicId,int noOfKeys,int duration,int totalQuestions,int minMarks) throws ServiceLocatorException;
        /*Ajay methods 08072013
        **/
        public int doAddAuthor(String createdBy,int topicID,String authorId,String status) throws ServiceLocatorException;

        public int doAddSubTopic(String subTopic,int topicId,String status) throws ServiceLocatorException;
        public int doDeleteAuthor(int id,String createdBy) throws ServiceLocatorException;

        public int doDeleteQuestion(int id) throws ServiceLocatorException;
        public void getEcertDetailResult(int examKeyId, EcertificationAction ecertificationAction) throws ServiceLocatorException;
        
         public Collection getAuthorsCollection(int topicId) throws ServiceLocatorException;
        public Collection getSubTopicsCollection(int topicId) throws ServiceLocatorException;
         public int doUpdateSubTopic(String subTopic,int subTopicId) throws ServiceLocatorException;
         public TopicsVTO getSubTopic(int subTopicId) throws ServiceLocatorException;
          public int deleteSubTopic(int subTopicId) throws ServiceLocatorException;
          public String doAddOrUpdatePractice(String practice,String topic,int topicId,String status,String loginId) throws ServiceLocatorException;
        public String getPractice(EcertificationAction ecertificationAction,int topicId) throws ServiceLocatorException;
}
