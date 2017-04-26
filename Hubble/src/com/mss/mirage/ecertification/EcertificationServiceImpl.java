/*
 * EcertificationServiceImpl.java
 *
 * Created on July 18, 2013, 7:09 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.ecertification;

import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.ServiceLocatorException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @author miracle
 */
public class EcertificationServiceImpl implements EcertificationService{
    
    /** Creates a new instance of EcertificationServiceImpl */
    public EcertificationServiceImpl() {
    }
    private List<QuestionsVTO> questionsVtoList = null;
    
    
   
     
     /*
      *Method to insert Examkey when employee start exam
      */
      public int insertExamKey(int insDomainId,int insTopicId,HttpServletRequest httpServletRequest)  throws ServiceLocatorException {
          Connection connection = null;
        ResultSet resultSet = null;
        CallableStatement callableStatement= null;
        int updatedRows=0;
        int isSuccess=0;
        int maxExamKey = 0;
        String result;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("{call spEcertKeys(" +
                    "?,?,?,?)" +
                    
                    " }"); 
            
            callableStatement.setInt(1, Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString())); 
            callableStatement.setInt(2, insDomainId);
            callableStatement.setInt(3, insTopicId);
            callableStatement.registerOutParameter(4,Types.INTEGER);
            updatedRows = callableStatement.executeUpdate();
            maxExamKey = callableStatement.getInt(4);
    
        }
        catch(SQLException se) {
            System.out.println("Error-->"+se.getMessage());
            se.printStackTrace();
            throw new ServiceLocatorException(se.getMessage());
        }finally{
            try{
                if(callableStatement!=null){
                    callableStatement.close();
                    callableStatement = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            }catch(SQLException sqle){
                throw new ServiceLocatorException(sqle);
            }
        }
        
  return maxExamKey;
      }
      
      
      public Map getQuestions(int topicId,HttpServletRequest httpServletRequest,int totalQuestions,int maxKeyId) throws ServiceLocatorException {
            ResultSet resultSet = null;
        Connection connection = null;
        CallableStatement callableStatement = null;
        Statement statement = null;
        String queryString = null;
        QuestionsVTO questionVTO = null;
        Map  questionMap = null;
        List subTopicList = null;
        List distinctSubTopicIds  = null;
            try{
            connection = ConnectionProvider.getInstance().getConnection();
            
          /*  String sqlQuery = "SELECT ID,Question,option1,option2,option3,option4,SubtopicId FROM tblEcertQuestion where TopicId="+topicId+" and STATUS ='Active' ORDER BY RAND() LIMIT "+totalQuestions;
            System.out.println("GetQquestions Query-->"+sqlQuery);
            */
            //resultSet= statement.executeQuery("SELECT ID,Question,option1,option2,option3,option4 FROM tblEcertQuestion WHERE TopicId ="+topicId);
            callableStatement = connection.prepareCall("{call spEcertQuestion(?,?,?)}");
                
                callableStatement.setInt(1,topicId);
                callableStatement.setInt(2,maxKeyId);
                callableStatement.setInt(3,totalQuestions);
                
            resultSet= callableStatement.executeQuery();
           // resultSet= statement.executeQuery();
            questionsVtoList = new LinkedList<QuestionsVTO>();
            List optionList = null;
            questionMap = new TreeMap();
            
            int i=1;
            
            while(resultSet.next())
            {
               // optionList = new ArrayList();
                questionVTO = new QuestionsVTO();
                questionVTO.setId(resultSet.getInt("ID"));
                questionVTO.setQuestion(resultSet.getString("Question"));
                questionVTO.setOption1(resultSet.getString("option1"));
                questionVTO.setOption2(resultSet.getString("option2"));
                questionVTO.setOption3(resultSet.getString("option3"));
                questionVTO.setOption4(resultSet.getString("option4"));
                questionVTO.setSubtopicId(resultSet.getInt("SubtopicId"));
                
                questionMap.put(i,questionVTO);
                i++;
            }
            
       
            
            }catch (SQLException sqle){
            throw new ServiceLocatorException(sqle);
            }finally{
            try{
                if(resultSet!=null){
                    resultSet.close();
                    resultSet = null;
                }
                if(callableStatement!=null){
                    callableStatement.close();
                    callableStatement = null;
                }
                 if(connection != null){
                    connection.close();
                    connection = null;
                }
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
        return questionMap;
     }
      public int getResult(int examKeyId)  throws ServiceLocatorException {
            ResultSet resultSet = null;
        Connection connection = null;
        Statement statement = null;
        String queryString = null;
        int result=0;
        
            try{
            connection = ConnectionProvider.getInstance().getConnection();
            statement=connection.createStatement();
            resultSet= statement.executeQuery("SELECT EmpId,ExamKeyId,QuestionId,EmpAns,tblEcertQuestion.Id,AnwerOption " +
                    "FROM tblEcertSummary LEFT OUTER JOIN tblEcertQuestion ON (QuestionId = tblEcertQuestion.Id) " +
                    "WHERE ExamKeyId="+examKeyId+" ORDER BY QuestionId");
            int count = 0;
            while(resultSet.next())
            {
               if(resultSet.getInt("EmpAns")==resultSet.getInt("AnwerOption")) {
                   count++;
               }
                
            }
            
            result = count;
            }catch (SQLException sqle){
            throw new ServiceLocatorException(sqle);
            }finally{
            try{
                if(resultSet!=null){
                    resultSet.close();
                    resultSet = null;
                }
                if(statement!=null){
                    statement.close();
                    statement = null;
                }
                 if(connection != null){
                    connection.close();
                    connection = null;
                }
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
        return result;
      }
      
       public int insertEmpExamResult(int examKeyId,String EmpLoginId,int totalQuest, int attemptedQuest, int marks,String examStatus)  throws ServiceLocatorException {
            ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String queryString = null;
        int result=0;
            try{
            connection = ConnectionProvider.getInstance().getConnection();
            
            preparedStatement=connection.prepareStatement("INSERT INTO tblEcertResult (EmpId,ExamKeyId,Marks,TotalQuestions,AttemptedQuestions,DateSubmitted,ExamStatus,EmpType) " +
                    "VALUES(?,?,?,?,?,?,?,?)");
        
           preparedStatement.setString(1,EmpLoginId);
           preparedStatement.setInt(2,examKeyId);
           preparedStatement.setInt(3,marks);
           preparedStatement.setInt(4,totalQuest);
           preparedStatement.setInt(5,attemptedQuest);
           preparedStatement.setTimestamp(6,DateUtility.getInstance().getCurrentMySqlDateTime());
           preparedStatement.setString(7,examStatus);
           preparedStatement.setInt(8,1);
           
           result =  preparedStatement.executeUpdate();
            }catch (SQLException sqle){
            throw new ServiceLocatorException(sqle);
            }finally{
            try{
               
                if(preparedStatement!=null){
                    preparedStatement.close();
                    preparedStatement = null;
                }
                 if(connection != null){
                    connection.close();
                    connection = null;
                }
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
        return result;
      }
       
      /*
       *New methods
       */
       
        /*New Impl for create exam
      *Author:prasad kandregula
      */
    /* public int doCreateExam(String createdBy,String domainName,String topicName,String subtopic,int totQuestions,int totDuration,int totMarks,int domainId,int minMarks) throws ServiceLocatorException
     {
         int isSuccess = 0;
        Connection connection= null;
        
      
        CallableStatement callableStatement = null;
        
        
        PreparedStatement preStmt=null,preStmtTemp=null;
        
        
        String queryString="";
        Statement statement=null;
        
      
        ResultSet resultSet=null;
        try{
            
            connection=ConnectionProvider.getInstance().getConnection();
            preStmt = connection.prepareStatement("insert into tblLKSubTopics(SubtopicName,TopicId," +
                    "CountOfQuestions,DurationInMin,MaxMarks,MinMarks,DomainId,CreatedBy,CreatedDate) values(?,?,?,?,?,?,?,?,?)");
            //preStmt.setInt(1,id);
           
                preStmt.setString(1,subtopic);
                preStmt.setInt(2,Integer.parseInt(topicName));
                preStmt.setInt(3,totQuestions);
                preStmt.setInt(4,totDuration);
                preStmt.setInt(5,totMarks);
                preStmt.setInt(6,minMarks);
                preStmt.setInt(7,Integer.parseInt(domainName));
                preStmt.setString(8,createdBy);
                preStmt.setTimestamp(9,new Timestamp(new java.util.Date().getTime()));
                isSuccess = preStmt.executeUpdate();
         
        }catch(SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
                if(resultSet != null){
                    resultSet.close();
                    resultSet = null;
                }
                if(statement != null){
                    statement.close();
                    statement = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            } catch(SQLException se){
                se.printStackTrace();
            }
        }
        return isSuccess;
    }*/
     
      /** New Impl for AddQuestions
       *  Author:prasad kandregula
       *  Modified by ajay
       *  Modified on 07-31-2013
       */
     public int doAddQuestion(String createdBy,String question,String option1,String option2,String option3,String option4,int answer,String description,int topicID,int subTopicId) throws ServiceLocatorException
     {
         
        int isSuccess = 0;
        Connection connection= null;
        
        /** callableStatement is a reference variable for CallableStatement . */
        CallableStatement callableStatement = null;
        
        /** preStmt,preStmtTemp are reference variable for PreparedStatement . */
        PreparedStatement preStmt=null,preStmtTemp=null;
        
        /** The queryString is useful to get  queryString result to the particular jsp page */
        String queryString="";
        Statement statement=null;
        
        /** The statement is useful  to execute the above queryString */
        ResultSet resultSet=null;
        try{
            
            connection=ConnectionProvider.getInstance().getConnection();
            preStmt = connection.prepareStatement("insert into tblEcertQuestion(Question,option1,option2,option3,option4," +
                    "AnwerOption,Explanation,TopicId,CreatedBy,CreatedDate,Status,SubtopicId) values(?,?,?,?,?,?,?,?,?,?,?,?)");
            //preStmt.setInt(1,id);
           
            preStmt.setString(1,question);
            preStmt.setString(2,option1);
            preStmt.setString(3,option2);
            preStmt.setString(4,option3);
            preStmt.setString(5,option4);
            preStmt.setInt(6,answer);
            preStmt.setString(7,description);
            preStmt.setInt(8,topicID);
            preStmt.setString(9,createdBy);
            
          //  System.out.println("tm--->"+new Timestamp(new java.util.Date().getTime()));
            
           // preStmt.setTimestamp(10,new Timestamp(new java.util.Date().getTime()));
            preStmt.setTimestamp(10, DateUtility.getInstance().getCurrentMySqlDateTime());
            preStmt.setString(11,"Active");
            preStmt.setInt(12,subTopicId);

            isSuccess = preStmt.executeUpdate();
           
        }catch(SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
            
                if(preStmt != null){
                    preStmt.close();
                    preStmt = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            } catch(SQLException se){
                se.printStackTrace();
            }
        }
        return isSuccess;
    }
     
     /*New Impl method for doUpdateQuestions
      *Author:prasad kandregula
      * Edited By : Ajay Tummapala
      */
    public int updateQuestion(String modifiedBy,String question,String option1,String option2,String option3,String option4,int answer,String description,int subTopicId,int questionId) throws ServiceLocatorException
     {
         
        int isSuccess = 0;
        Connection connection= null;
        
        /** callableStatement is a reference variable for CallableStatement . */
        CallableStatement callableStatement = null;
        
        /** preStmt,preStmtTemp are reference variable for PreparedStatement . */
        PreparedStatement preStmt=null,preStmtTemp=null;
        
        /** The queryString is useful to get  queryString result to the particular jsp page */
        String queryString="";
        Statement statement=null;
        
        /** The statement is useful  to execute the above queryString */
        ResultSet resultSet=null;
        try{
            int qId=questionId;
            connection=ConnectionProvider.getInstance().getConnection();
           queryString = "UPDATE tblEcertQuestion SET Question=?,option1=?,option2=?,option3=?,option4=?,AnwerOption=?,Explanation=?,SubtopicId=? WHERE ID=?";
            
            preStmt = connection.prepareStatement(queryString);
            //preStmt.setInt(1,id);
            preStmt.setString(1,question);
            preStmt.setString(2,option1);
            preStmt.setString(3,option2);
            preStmt.setString(4,option3);
            preStmt.setString(5,option4);
            preStmt.setInt(6,answer);
            preStmt.setString(7,description);
            preStmt.setInt(8,subTopicId);
            preStmt.setInt(9,qId);
            
            isSuccess = preStmt.executeUpdate();
           
        }catch(SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
              
                if(preStmt != null){
                    preStmt.close();
                    preStmt = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            } catch(SQLException se){
                se.printStackTrace();
            }
        }
        return isSuccess;
    }
     
      /*new Impl method for poulate questions
       *Author:Prasad Kandregula
     * Modified : Ajay Tummapala
       */
     
      public QuestionsVTO getQuestion(int questionId) throws ServiceLocatorException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        DateUtility dateUtil;
        QuestionsVTO questionsVTO = new QuestionsVTO();
        String attachmentId="";
        String sqlQuery = "select * from tblEcertQuestion where Id="+questionId;
        
        dateUtil = DateUtility.getInstance();
        String uploadFileName = null;
        String category = null;
        int taskId=0;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sqlQuery);
            resultSet = preparedStatement.executeQuery();
            questionsVTO.setId(questionId);
            resultSet.next();
            
            questionsVTO.setQuestion(resultSet.getString("Question"));
            questionsVTO.setOption1(resultSet.getString("option1"));
            questionsVTO.setOption2(resultSet.getString("option2"));
            questionsVTO.setOption3(resultSet.getString("option3"));
            questionsVTO.setOption4(resultSet.getString("option4"));
            questionsVTO.setAnswer(resultSet.getInt("AnwerOption"));
            questionsVTO.setDescription(resultSet.getString("Explanation"));
            questionsVTO.setSubtopicId(resultSet.getInt("SubtopicId"));
            
        } catch(SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
                if(resultSet != null){
                    resultSet.close();
                    resultSet = null;
                }
                if(preparedStatement != null){
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if(connection!=null){
                    connection.close();
                    connection = null;
                }
            }catch(SQLException sqle){
                throw new ServiceLocatorException(sqle);
            }
        }
        return questionsVTO;
    }
       
       public List  reviewExam(int examKeyId) throws ServiceLocatorException {
       QuestionsVTO questionsVTO  = null;
       List questionsVtoList = null;
       Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sqlQuery = "SELECT tblEcertQuestion.ID,Question,option1,option2,option3,option4,Explanation,AnwerOption,empAns FROM tblEcertQuestion LEFT OUTER JOIN tblEcertSummary ON (tblEcertQuestion.ID = tblEcertSummary.QUESTIONID) WHERE ExamKeyId ="+examKeyId;
       try {
           connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sqlQuery);
            resultSet = preparedStatement.executeQuery();
            questionsVtoList = new ArrayList();
            while(resultSet.next()) {
                questionsVTO = new QuestionsVTO();
                questionsVTO.setQuestion(resultSet.getString("Question"));
                questionsVTO.setOption1(resultSet.getString("option1"));
                questionsVTO.setOption2(resultSet.getString("option2"));
                questionsVTO.setOption3(resultSet.getString("option3"));
                questionsVTO.setOption4(resultSet.getString("option4"));
                questionsVTO.setDescription(resultSet.getString("Explanation"));
                questionsVTO.setAnswer(resultSet.getInt("AnwerOption"));
                questionsVTO.setUserAnswer(resultSet.getInt("empAns"));
                questionsVtoList.add(questionsVTO);
                
            }
            
       }catch(SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
                if(resultSet != null){
                    resultSet.close();
                    resultSet = null;
                }
                if(preparedStatement != null){
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if(connection!=null){
                    connection.close();
                    connection = null;
                }
            }catch(SQLException sqle){
                throw new ServiceLocatorException(sqle);
            }
        }
       return questionsVtoList;
   }    
   
      public int doInactiveExamKey(String examvalidationKey, String userId) throws ServiceLocatorException{
          Connection connection = null;
          PreparedStatement preparedStatement = null;
          
          int i=0;
          try {
              connection = ConnectionProvider.getInstance().getConnection();
              preparedStatement = connection.prepareStatement("UPDATE tblEcertValidatorKeys SET Status=? , ModifiedBy = ?, ModifiedDate = ? WHERE vkey = ? ");
              preparedStatement.setString(1,"Inactive");
              preparedStatement.setString(2,userId);
              preparedStatement.setTimestamp(3,DateUtility.getInstance().getCurrentMySqlDateTime());
              preparedStatement.setString(4,examvalidationKey);
              i = preparedStatement.executeUpdate();
          }catch (Exception e){
              System.err.println("Exception is-->"+e.getMessage());
          }finally {
              try {
               if(preparedStatement!=null){
                  preparedStatement.close();
                  preparedStatement = null;
              }if(connection!=null){
                  connection.close();
                  connection = null;
              } 
              }catch(Exception sqle){
                  System.err.println("SQL Exception is-->"+sqle.getMessage());
              }
          }
          return i;
      }
   
      /**
       * Addkeys service impl
       */
     public int doAddKey(Object key,String createdBy,int topicId,int noOfKeys,int duration,int totalQuestions,int minMarks) throws ServiceLocatorException
     {
          String key1 = (String)key;
        
         int isSuccess = 0;
        Connection connection= null;
        
        /** callableStatement is a reference variable for CallableStatement . */
        CallableStatement callableStatement = null;
        
        /** preStmt,preStmtTemp are reference variable for PreparedStatement . */
        PreparedStatement preStmt=null,preStmtTemp=null;
        
        /** The queryString is useful to get  queryString result to the particular jsp page */
        String queryString="";
        Statement statement=null;
        
        /** The statement is useful  to execute the above queryString */
        ResultSet resultSet=null;
        try{
           // System.out.println("before createdby5");
             
         //   System.out.println("before createdby7");
            connection=ConnectionProvider.getInstance().getConnection();
            preStmt = connection.prepareStatement("insert into tblEcertValidatorKeys(VKey,status," +
                    "TopicId,CreatedBy,CreatedDate,Duration,MinMarks,NoOfQuestions) values(?,?,?,?,?,?,?,?)");
            //preStmt.setInt(1,id);
           
                preStmt.setString(1,key1);
                preStmt.setString(2,"Active");
                preStmt.setInt(3,topicId);
                preStmt.setString(4,createdBy);
                preStmt.setTimestamp(5,DateUtility.getInstance().getCurrentMySqlDateTime());
                preStmt.setInt(6,duration);
                preStmt.setInt(7,minMarks);
                preStmt.setInt(8,totalQuestions);
                
                
          // System.out.println("before createdby8");
                isSuccess = preStmt.executeUpdate();
         
        }catch(SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
              
                if(preStmt != null){
                    preStmt.close();
                    preStmt = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            } catch(SQLException se){
                se.printStackTrace();
            }
        }
        return isSuccess;
    }
/*
 **Ajay Changes
 *08072013
 **
 */
   /**
       *Author : Ajay Tummapala
       *Desc : to Add the author
       * ModifiedBy : Santosh Kola
    *   Modified Date : 08/22/2103   
       *
       */
       public int doAddAuthor(String createdBy,int topicID,String authorId,String status) throws ServiceLocatorException
     {
         
        int isSuccess = 0;
        Connection connection= null;
        
        /** callableStatement is a reference variable for CallableStatement . */
        CallableStatement callableStatement = null;
        
        /** preStmt,preStmtTemp are reference variable for PreparedStatement . */
        PreparedStatement preStmt=null,preStmtTemp=null;
        
        /** The queryString is useful to get  queryString result to the particular jsp page */
        String queryString="";
        Statement statement=null;
        
        /** The statement is useful  to execute the above queryString */
        ResultSet resultSet=null;
        try{
            int isAuthorExistId = isAuthorExist(topicID,authorId);
            connection=ConnectionProvider.getInstance().getConnection();
            if(isAuthorExistId == 0) {
            preStmt = connection.prepareStatement("insert into tblEcertTopicAuthors(TopicId,AuthorId,CreatedBy,CreatedDate,STATUS) values(?,?,?,?,?)");
            //preStmt.setInt(1,id);
           
            preStmt.setInt(1,topicID);
            preStmt.setString(2,authorId);
            preStmt.setString(3,createdBy);
           preStmt.setTimestamp(4,DateUtility.getInstance().getCurrentMySqlDateTime());
            preStmt.setString(5,status);
            isSuccess = preStmt.executeUpdate();
            }else {
                preStmt = connection.prepareStatement("UPDATE tblEcertTopicAuthors SET Status = 'Active', ModifiedBy = ?, ModifiedDate = ?  WHERE ID = ?");
                preStmt.setString(1,createdBy);
                preStmt.setTimestamp(2,DateUtility.getInstance().getCurrentMySqlDateTime());
                preStmt.setInt(3,isAuthorExistId);
                isSuccess = preStmt.executeUpdate();
            }
        }catch(SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
              
                if(preStmt != null){
                    preStmt.close();
                    preStmt = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            } catch(SQLException se){
                se.printStackTrace();
            }
        }
        return isSuccess;
    }

       public int isAuthorExist(int topicID,String authorId) throws ServiceLocatorException{
                
        int id = 0;
        Connection connection= null;
        
        /** callableStatement is a reference variable for CallableStatement . */
        CallableStatement callableStatement = null;
        
        /** preStmt,preStmtTemp are reference variable for PreparedStatement . */
        PreparedStatement prepareStatement=null;
        
        /** The queryString is useful to get  queryString result to the particular jsp page */
       
        
        /** The statement is useful  to execute the above queryString */
        ResultSet resultSet=null;
        try{
             connection=ConnectionProvider.getInstance().getConnection();
            prepareStatement = connection.prepareStatement("SELECT ID FROM tblEcertTopicAuthors WHERE AuthorId=? AND TopicId = ?");
            //preStmt.setInt(1,id);
            prepareStatement.setString(1,authorId);
            prepareStatement.setInt(2,topicID);
           resultSet = prepareStatement.executeQuery();
           if(resultSet.next())
           id = resultSet.getInt("ID");
            //isSuccess = preStmt.executeUpdate();
         }catch(SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
                if(resultSet != null){
                    resultSet.close();
                    resultSet = null;
                }
                if(prepareStatement != null){
                    prepareStatement.close();
                    prepareStatement = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            } catch(SQLException se){
                se.printStackTrace();
            }
        }
        return id;
       }
       
        /**
       *Author : Ajay Tummapala
       *Desc : to Add the subtopic
       *
       */
       public int doAddSubTopic(String subTopic,int topicId,String status) throws ServiceLocatorException
     {
         
        int isSuccess = 0;
        Connection connection= null;
        
        /** callableStatement is a reference variable for CallableStatement . */
        CallableStatement callableStatement = null;
        
        /** preStmt,preStmtTemp are reference variable for PreparedStatement . */
        PreparedStatement preStmt=null,preStmtTemp=null;
        
        /** The queryString is useful to get  queryString result to the particular jsp page */
        String queryString="";
        Statement statement=null;
        
        /** The statement is useful  to execute the above queryString */
        ResultSet resultSet=null;
        try{
            
            connection=ConnectionProvider.getInstance().getConnection();
            preStmt = connection.prepareStatement("insert into tblEcertSubTopics(SubTopicName,TopicId,STATUS) values(?,?,?)");
            //preStmt.setInt(1,id);
           
            preStmt.setString(1,subTopic);
            preStmt.setInt(2,topicId);
            preStmt.setString(3,status);
           
            isSuccess = preStmt.executeUpdate();
           
        }catch(SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
              
                if(preStmt != null){
                    preStmt.close();
                    preStmt = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            } catch(SQLException se){
                se.printStackTrace();
            }
        }
        return isSuccess;
    }

    
                /**
       *Author : Ajay Tummapala
       *Desc : to Add the subtopic
       *
       */
       public int doDeleteAuthor(int id, String modifiedBy) throws ServiceLocatorException
     {
         
        int isSuccess = 0;
        Connection connection= null;
        
        /** callableStatement is a reference variable for CallableStatement . */
        CallableStatement callableStatement = null;
        
        /** preStmt,preStmtTemp are reference variable for PreparedStatement . */
        PreparedStatement preStmt=null,preStmtTemp=null;
        
        /** The queryString is useful to get  queryString result to the particular jsp page */
        String queryString="";
        Statement statement=null;
        
        /** The statement is useful  to execute the above queryString */
        ResultSet resultSet=null;
        try{
            
            connection=ConnectionProvider.getInstance().getConnection();
            //preStmt = connection.prepareStatement("delete from tblEcertTopicAuthors where ID=?");
            preStmt = connection.prepareStatement("UPDATE tblEcertTopicAuthors SET Status = 'InActive' , ModifiedBy = ?, ModifiedDate = ?  WHERE ID = ? ");
            //preStmt.setInt(1,id);
           preStmt.setString(1,modifiedBy);
                preStmt.setTimestamp(2,DateUtility.getInstance().getCurrentMySqlDateTime());
            
            preStmt.setInt(3,id);
             
           
            isSuccess = preStmt.executeUpdate();
           
        }catch(SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
               
                if(preStmt != null){
                    preStmt.close();
                    preStmt = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            } catch(SQLException se){
                se.printStackTrace();
            }
        }
        return isSuccess;
    }
   public void getEcertDetailResult(int examKeyId, EcertificationAction ecertificationAction) throws ServiceLocatorException {
       
        Connection connection= null;
        
        /** callableStatement is a reference variable for CallableStatement . */
        CallableStatement callableStatement = null;
        List subTopicDetailsList = null;
        try{
            
            connection=ConnectionProvider.getInstance().getConnection();
           callableStatement = connection.prepareCall("{call spEcertResult(?,?,?,?,?,?,?,?)}");
           callableStatement.setInt(1,examKeyId);
            callableStatement.registerOutParameter(2,Types.VARCHAR);
            callableStatement.registerOutParameter(3,Types.VARCHAR);
            callableStatement.registerOutParameter(4,Types.INTEGER);
            callableStatement.registerOutParameter(5,Types.INTEGER);
            callableStatement.registerOutParameter(6,Types.INTEGER);
            callableStatement.registerOutParameter(7,Types.VARCHAR);
            callableStatement.registerOutParameter(8,Types.VARCHAR);
            callableStatement.execute();
            
            ecertificationAction.setDomainName(callableStatement.getString(2));
            ecertificationAction.setTopicName(callableStatement.getString(3));
            ecertificationAction.setTotalQuest(callableStatement.getInt(4));
            ecertificationAction.setAttemptedQuestions(callableStatement.getInt(5));
            ecertificationAction.setExamMarks(callableStatement.getInt(6));
            ecertificationAction.setExamStatus(callableStatement.getString(7));
           // System.out.println("Result-->"+callableStatement.getString(8));
            String subTopicDetailResult = "";
           subTopicDetailResult = callableStatement.getString(8);
            
            String subTopics [] = subTopicDetailResult.split("@");
            //System.out.println("Main Subtopic String-->"+subTopics);
            subTopicDetailsList = new ArrayList();
            ExamDetailInfoVTO examDetailInfoVTO = null;
            for(int i=0;i<subTopics.length;i++) {
               // System.out.println("Subtopic String-->"+subTopics[i]);
                String subTopicInfo[] = subTopics[i].split("!");
               // for(int j=0;j<subTopicInfo.length;j++) {
                //System.out.println("=============Sub Topic Info Start=============");
                 examDetailInfoVTO = new ExamDetailInfoVTO();
               //  System.out.println("=============Sub Topic Info Start=============");
                examDetailInfoVTO.setSubtopicName(subTopicInfo[0]);
               // System.out.println("=============Sub Topic Info Start=============");
                examDetailInfoVTO.setSubtopictotalQuestions(Integer.parseInt(subTopicInfo[1]));
               // System.out.println("=============Sub Topic Info Start=============");
                examDetailInfoVTO.setAttemptedQuestion(Integer.parseInt(subTopicInfo[3]));
               // System.out.println("=============Sub Topic Info Start=============");
                examDetailInfoVTO.setSubtopicMarks(Integer.parseInt(subTopicInfo[2]));
               // System.out.println("=============Sub Topic Info Start=============");
                subTopicDetailsList.add(examDetailInfoVTO);
               // System.out.println("Sub Topic Name-->"+subTopicInfo[0]);
               // System.out.println("SubTopic Questions-->"+subTopicInfo[1]);
               // System.out.println("Correct Answers-->"+subTopicInfo[2]);
              //  System.out.println("Attempted Questions-->"+subTopicInfo[3]);
              //  System.out.println("=============Sub Topic Info End=============");
           // }
            }
            ecertificationAction.setExamDetailInfoList(subTopicDetailsList);
                    
//                    setDomainName(getDomainName());
//            setTopicName(getTopicName());
//            setTotalQuest(getTotalQuest());
//            setAttemptedQuestions(attemptedQuestions);
//            setExamStatus(examStatus);
//            setExamMarks(result);
            
            
        }catch(SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
               
                if(callableStatement != null){
                    callableStatement.close();
                    callableStatement = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            } catch(SQLException se){
                se.printStackTrace();
            }
        }
        
       
       
   }

   
       /**
       *Author : Ajay Tummapala
       *Desc : to inactivate the system
       *
       */
       public int doDeleteQuestion(int qid) throws ServiceLocatorException
     {
         
        int isSuccess = 0;
        Connection connection= null;
        
        /** callableStatement is a reference variable for CallableStatement . */
        CallableStatement callableStatement = null;
        
        /** preStmt,preStmtTemp are reference variable for PreparedStatement . */
        PreparedStatement preStmt=null,preStmtTemp=null;
        
        /** The queryString is useful to get  queryString result to the particular jsp page */
        String queryString="";
        Statement statement=null;
        
        /** The statement is useful  to execute the above queryString */
        ResultSet resultSet=null;
        try{
            
            connection=ConnectionProvider.getInstance().getConnection();
            preStmt = connection.prepareStatement("update tblEcertQuestion set Status='InActive' where ID=?");
            //preStmt.setInt(1,id);
           
            
            preStmt.setInt(1,qid);
             
           
            isSuccess = preStmt.executeUpdate();
           
        }catch(SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
               
                if(preStmt != null){
                    preStmt.close();
                    preStmt = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            } catch(SQLException se){
                se.printStackTrace();
            }
        }
        return isSuccess;
    }
   
 /*
        * New Methods For Add sub topic and authors start
        */
   
   /*
        * 
        * For Displaying Authors Grid.
        */
   public Collection getAuthorsCollection(int topicId) throws ServiceLocatorException {
        Map authorsMap = new TreeMap();
        int counter = 1;
        String tempCreatedDate ="";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            String queryString = "SELECT ID,TopicId,AuthorId,CreatedBy,CreatedDate,Status FROM tblEcertTopicAuthors WHERE TopicId="+topicId+" AND Status = 'Active' ORDER BY CreatedDate DESC";
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while(resultSet.next()){
                /* private int authorId;
    private String authorLoginId;
    private String createdBy;
    private String createdDate;
    private String authorStatus;*/
                    TopicsVTO topicsVTO = new TopicsVTO();
                    topicsVTO.setAuthorId(resultSet.getInt("ID"));
                    topicsVTO.setAuthorLoginId(resultSet.getString("AuthorId"));
                    topicsVTO.setCreatedBy(resultSet.getString("createdBy"));
                    topicsVTO.setCreatedDate(resultSet.getString("CreatedDate"));
                    topicsVTO.setAuthorStatus(resultSet.getString("Status"));
                    topicsVTO.setTopicId(resultSet.getInt("TopicId"));
                    authorsMap.put("stateVTO"+counter,topicsVTO);
                    counter++;
                
            }
            
        }catch (SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
                if(resultSet != null){
                    resultSet.close();
                    resultSet = null;
                }
                if(statement != null){
                    statement.close();
                    statement = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
        return authorsMap.values();
    }
    
   public Collection getSubTopicsCollection(int topicId) throws ServiceLocatorException {
        Map subTopicsMap = new TreeMap();
        int counter = 1;
        String tempCreatedDate ="";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            String queryString = "SELECT ID,SubTopicName,TopicId,Status FROM tblEcertSubTopics WHERE TopicId="+topicId+" AND Status='Active'";
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while(resultSet.next()){
                /* private int subTopicId;
    private String subTopicName;
    private String subTopicStatus;*/
                    TopicsVTO topicsVTO = new TopicsVTO();
                    topicsVTO.setSubTopicId(resultSet.getInt("ID"));
                    topicsVTO.setSubtopic(resultSet.getString("SubTopicName"));
                    
                    topicsVTO.setSubTopicStatus(resultSet.getString("Status"));
                    topicsVTO.setTopicId(resultSet.getInt("TopicId"));
                    subTopicsMap.put("stateVTO"+counter,topicsVTO);
                    counter++;
                
            }
            
        }catch (SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
                if(resultSet != null){
                    resultSet.close();
                    resultSet = null;
                }
                if(statement != null){
                    statement.close();
                    statement = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
        return subTopicsMap.values();
    }
   
   public int deleteSubTopic(int subTopicId) throws ServiceLocatorException
     {
         
        int isSuccess = 0;
        Connection connection= null;
        
       
        
        /** preStmt,preStmtTemp are reference variable for PreparedStatement . */
        PreparedStatement preparedStatement=null;
     
        try{
            
            connection=ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("UPDATE tblEcertSubTopics SET Status='InActive' WHERE ID=?");
            //preStmt.setInt(1,id);
           
            
            preparedStatement.setInt(1,subTopicId);
             
           
            isSuccess = preparedStatement.executeUpdate();
           
        }catch(SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
               
                if(preparedStatement != null){
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            } catch(SQLException se){
                se.printStackTrace();
            }
        }
        return isSuccess;
    }
   



public int doUpdateSubTopic(String subTopic, int subTopicId) throws ServiceLocatorException {
     
         
        int isSuccess = 0;
        Connection connection= null;
        
        /** callableStatement is a reference variable for CallableStatement . */
        CallableStatement callableStatement = null;
        
        /** preStmt,preStmtTemp are reference variable for PreparedStatement . */
        PreparedStatement preStmt=null,preStmtTemp=null;
        
        /** The queryString is useful to get  queryString result to the particular jsp page */
        String queryString="";
        Statement statement=null;
        
        /** The statement is useful  to execute the above queryString */
        ResultSet resultSet=null;
        try{
            
            connection=ConnectionProvider.getInstance().getConnection();
           // preStmt = connection.prepareStatement("insert into tblEcertSubTopics(SubTopicName,TopicId,STATUS) values(?,?,?)");
             preStmt = connection.prepareStatement("UPDATE tblEcertSubTopics SET SubTopicName=? WHERE ID = ?");
            //preStmt.setInt(1,id);
           
            preStmt.setString(1,subTopic);
            preStmt.setInt(2,subTopicId);
            
           
            isSuccess = preStmt.executeUpdate();
           
        }catch(SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
              
                if(preStmt != null){
                    preStmt.close();
                    preStmt = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            } catch(SQLException se){
                se.printStackTrace();
            }
        }
        return isSuccess;
    }
 public TopicsVTO getSubTopic(int subTopicId) throws ServiceLocatorException
     {
       
        Connection connection= null;
      
        /** preStmt,preStmtTemp are reference variable for PreparedStatement . */
        PreparedStatement preparedStatement=null;
        TopicsVTO topicsVTO = null;
        /** The statement is useful  to execute the above queryString */
        ResultSet resultSet=null;
        try{
           connection=ConnectionProvider.getInstance().getConnection();
           // preStmt = connection.prepareStatement("insert into tblEcertSubTopics(SubTopicName,TopicId,STATUS) values(?,?,?)");
             preparedStatement = connection.prepareStatement("SELECT ID,SubTopicName,TopicId,Status FROM tblEcertSubTopics WHERE ID = ?");
            //preStmt.setInt(1,id);
             preparedStatement.setInt(1,subTopicId);
           resultSet = preparedStatement.executeQuery();
         topicsVTO = new TopicsVTO();
           
           if(resultSet.next()) {
               topicsVTO.setSubTopicId(resultSet.getInt("ID"));
               topicsVTO.setSubtopic(resultSet.getString("SubTopicName"));
               topicsVTO.setTopicId(resultSet.getInt("TopicId"));
           }
        }catch(SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
              if(resultSet != null){
                    resultSet.close();
                    resultSet = null;
                }
                if(preparedStatement != null){
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            } catch(SQLException se){
                se.printStackTrace();
            }
        }
        return topicsVTO;
    }
/*
  * 
  * Ajay Tummapala 
  * Practice Creation
  * 
  */
  @Override
    public String doAddOrUpdatePractice(String practiceName,String topicName,int topicId,String Status,String loginId) throws ServiceLocatorException{
        
     Connection connection= null;
        String resultMsg = null;
        /** callableStatement is a reference variable for CallableStatement . */
        CallableStatement callableStatement = null;
       
        try{
           connection=ConnectionProvider.getInstance().getConnection();
           callableStatement = connection.prepareCall("{call spEcertPractice(?,?,?,?,?,?,?)}");
           callableStatement.setString(1,practiceName.trim());
           callableStatement.setString(2,topicName.trim());
           if(topicId == 0 ){
           callableStatement.setString(3,"Ins");
           callableStatement.setInt(5,0);
           
           }else{
           callableStatement.setString(3,"Upd");
           callableStatement.setInt(5,topicId);
           }
           callableStatement.setString(6,loginId);
           callableStatement.setString(4,Status.trim());
            callableStatement.registerOutParameter(7,Types.VARCHAR);
           
            callableStatement.execute();
            
           
        // System.out.println("Result-->"+callableStatement.getString(3));
            
           resultMsg = callableStatement.getString(7);
       
            
        }catch(SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
               
                if(callableStatement != null){
                    callableStatement.close();
                    callableStatement = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            } catch(SQLException se){
                se.printStackTrace();
            }
        }   
        
        return resultMsg;
        
    }
    /*
     * 
     * Ajay Tummapala
     * for getting practoce and domain name
     */
    public String getPractice(EcertificationAction ecertificationAction,int topicId) throws ServiceLocatorException
    {
        
      Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
      
        String sqlQuery = "SELECT tblEcertDomain.DomainName as DomainName,tblEcertTopics.TopicName as TopicName"
                + ",tblEcertTopics.Id as TopicId,tblEcertTopics.Status as status FROM tblEcertDomain LEFT OUTER JOIN tblEcertTopics "
                + "ON (tblEcertDomain.Id = tblEcertTopics.DomainId) WHERE tblEcertTopics.Id = "+topicId;
        
       
        String uploadFileName = null;
        String category = null;
        int taskId=0;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sqlQuery);
            resultSet = preparedStatement.executeQuery();
           // questionsVTO.setId(questionId);
            resultSet.next();
            ecertificationAction.setDomainName(resultSet.getString("DomainName"));
            ecertificationAction.setTopicName(resultSet.getString("TopicName"));
            ecertificationAction.setTopicId(resultSet.getInt("TopicId"));
            ecertificationAction.setStatus(resultSet.getString("status"));
            
        
            
        } catch(SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
                if(resultSet != null){
                    resultSet.close();
                    resultSet = null;
                }
                if(preparedStatement != null){
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if(connection!=null){
                    connection.close();
                    connection = null;
                }
            }catch(SQLException sqle){
                throw new ServiceLocatorException(sqle);
            }
        }
       // return questionsVTO;   
        
        
        return null;
    }
   
}
