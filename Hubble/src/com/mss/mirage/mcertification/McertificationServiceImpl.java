/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.mirage.mcertification;

import com.mss.mirage.cre.CreAction;
import com.mss.mirage.cre.CreReviewVTO;
import com.mss.mirage.ecertification.ExamDetailInfoVTO;
import com.mss.mirage.ecertification.QuestionsVTO;
import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.ServiceLocatorException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 *
 * @author miracle
 */
public class McertificationServiceImpl implements McertificationService{
    public int doAddMcertSubTopics(List subTopicId,String examType,int minMarks,int noOfQuestions,int time,int totalQuestionsFromEachSub) throws ServiceLocatorException{
       // System.out.println("into doAddOrUpdateSubTopics ");
     Connection connection= null;
        
        /** callableStatement is a reference variable for CallableStatement . */
       PreparedStatement preparedStatement = null;
       int result=0;
        try{
          connection = ConnectionProvider.getInstance().getConnection();
            
            preparedStatement=connection.prepareStatement("INSERT INTO tblMcertSetExam (ExamType,MinMarks,Time,NoOfQuestions,SubTopicIds,NoOfSubTopicQues) " +
                    "VALUES(?,?,?,?,?,?)");
        
           preparedStatement.setString(1,examType);
           preparedStatement.setInt(2,minMarks);
           preparedStatement.setInt(3,time);
           preparedStatement.setInt(4,noOfQuestions);
           //subTopicId
           
                //   StringTokenizer st = new StringTokenizer(examType, examType)
           //String subTopicLists = (String)subTopicId.get(0)+(String)subTopicId.get(1)+""+(String)subTopicId.get(2);
       /*    String subTopicLists ="";
           for(int i =0;i<subTopicId.size();i++)
           {
               subTopicLists = subTopicLists+(String)subTopicId.get(i)+",";
             
           }
             System.out.println("subTopicsList----------->"+subTopicLists);
             
           String subTopicLists1=subTopicLists.substring(0,subTopicLists.length()-1);*/
           String subTopicLists1=DataSourceDataProvider.getInstance().getSubTopicIdString(subTopicId);
           
           preparedStatement.setString(5,subTopicLists1);
           preparedStatement.setInt(6,totalQuestionsFromEachSub);
           
           result =  preparedStatement.executeUpdate();
       
            
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
        
        return result;
        
    }
    
    public String getMcertExamDetails(McertificationAction mcertificationAction,int examId1) throws ServiceLocatorException
 {   Map idsMap = new TreeMap();
    // System.out.println("into creexam details impl----------->"+examId1);
 Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
      
        String sqlQuery = "SELECT Id,ExamType,MinMarks,Time,NoOfQuestions,SubTopicIds,NoOfSubTopicQues from tblMcertSetExam where Id="+examId1;
        
       
        String uploadFileName = null;
        String category = null;
        int taskId=0;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sqlQuery);
            resultSet = preparedStatement.executeQuery();
           // questionsVTO.setId(questionId);
            resultSet.next();
            mcertificationAction.setExamType(resultSet.getString("ExamType"));
            mcertificationAction.setMinMarks(resultSet.getInt("MinMarks"));
            mcertificationAction.setTotDuration(resultSet.getInt("Time"));
            mcertificationAction.setTotalQuest(resultSet.getInt("NoOfQuestions"));
           String subtopicsID=resultSet.getString("SubTopicIds");
          
        /* String replace = subtopicsID.replace("[","");
         System.out.println(replace);
         String subtopicsID1 = replace.replace("]","");
         idsMap  = DataSourceDataProvider.getInstance().getSubTopicIds(subtopicsID1);
         System.out.println(idsMap);*/
   StringTokenizer stk = new StringTokenizer(subtopicsID,",");  
   List list = new ArrayList();  
       
    while ( stk.hasMoreTokens() ) {  
    list.add(stk.nextToken());  
    }   
   // System.out.println("list----------->"+list);
       //  List list = new ArrayList(idsMap.keySet());
         
         mcertificationAction.setSubtopics1(list);
         mcertificationAction.setTotalQues(resultSet.getInt("NoOfSubTopicQues"));
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
 public boolean doMcertUpdateSubTopics(int examId,List subTopicId,String examType,int minMarks,int noOfQuestions,int time,int totalQuestionsFromEachSub) throws ServiceLocatorException{
       // System.out.println("into doUpdateSubTopics ");
       // System.out.println("id------------>"+examId);
     Connection connection= null;
        boolean isUpdated = false;
        /** callableStatement is a reference variable for CallableStatement . */
       PreparedStatement preparedStatement = null;
       //int result=0;
        try{
          connection = ConnectionProvider.getInstance().getConnection();
            
            preparedStatement=connection.prepareStatement("update tblMcertSetExam set ExamType=?,MinMarks=?,Time=?,NoOfQuestions=?,SubTopicIds=?,NoOfSubTopicQues=? where Id=?");
        
           preparedStatement.setString(1,examType);
           preparedStatement.setInt(2,minMarks);
           preparedStatement.setInt(3,time);
           preparedStatement.setInt(4,noOfQuestions);
          /* String subTopicLists ="";
           for(int i =0;i<subTopicId.size();i++)
           {
               subTopicLists = subTopicLists+(String)subTopicId.get(i)+",";
             
           }
             System.out.println("subTopicsList----------->"+subTopicLists);
             
           String subTopicLists1=subTopicLists.substring(0,subTopicLists.length()-1);*/
           String subTopicLists1=DataSourceDataProvider.getInstance().getSubTopicIdString(subTopicId);
           preparedStatement.setString(5,subTopicLists1);
           preparedStatement.setInt(6,totalQuestionsFromEachSub);
           preparedStatement.setInt(7,examId);
           
           
           isUpdated =  preparedStatement.execute();
      // System.out.println("booolean------>"+isUpdated);
            
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
        
        return isUpdated;
        
    }
public boolean inActiveMcertExam(McertificationAction mcertificationAction,int examId1) throws ServiceLocatorException 
 {  
        Connection connection = null;
        PreparedStatement preparedStatement = null;
       // ResultSet resultSet = null;
        String sqlQuery = "update tblMcertSetExam set Status='InActive' where Id="+examId1;
        boolean isUpdated = false;
       
        String uploadFileName = null;
        String category = null;
        int taskId=0;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sqlQuery);
            isUpdated = preparedStatement.execute();
           // questionsVTO.setId(questionId);
           
        } catch(SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
               
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
        
        
        return isUpdated;
}

public Map getMcertExamTypeMap() throws ServiceLocatorException {

        Map subtopicsMap = new TreeMap();//key-Description
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT Id,ExamType FROM tblMcertSetExam WHERE Status = 'Active'";
            preparedStatement = connection.prepareStatement(queryString);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                subtopicsMap.put(new Integer(resultSet.getInt("Id")),
                        resultSet.getString("ExamType"));
            }


        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }

                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }

                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }

        }

        return subtopicsMap; // returning the object.
    } //closing the method.


 public String[] mcertLoginCheck(String loginId) throws ServiceLocatorException {
         Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        
        String consultantDetails[] = new String[8];
        String creDetailsInfo[] = null;
       // String queryString = "SELECT * FROM tblCreConsultentDetails WHERE ConsultentId='"+consultantId+"'";
        try{
            connection = ConnectionProvider.getInstance().getConnection();
         /*   statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while(resultSet.next()){
               // System.out.println("In while");
                //primaryAction = resultSet.getString("Action");
                consultantDetails[0] = resultSet.getString("ConsultentId");
                consultantDetails[1] = resultSet.getString("FName")+" "+resultSet.getString("LName");
                  consultantDetails[2] = resultSet.getString("Email1");
                  consultantDetails[3] = resultSet.getString("CellPhoneNo");
                  consultantDetails[4] = resultSet.getString("Id");
                  consultantDetails[5] = resultSet.getString("STATUS");
                  consultantDetails[6] = resultSet.getString("NoOfQuestions");
                   consultantDetails[7] = resultSet.getString("MinMarks");
                   consultantDetails[8] = resultSet.getString("Duration");
            }
            */
            //callableStatement = connection.prepareCall("{call spCreLoginProc(?,?,?)}");
            callableStatement = connection.prepareCall("{call spMcertLoginProc(?,?,?)}");
            callableStatement.setString(1,loginId);
                 callableStatement.registerOutParameter(2,Types.VARCHAR);
                 callableStatement.registerOutParameter(3,Types.VARCHAR);
                 
                 callableStatement.execute();
                 String  creDetails = callableStatement.getString(2);
                 String  creExamDetails = callableStatement.getString(3);
                // System.out.println("creDetails-->"+creDetails);
                // System.out.println("creExamDetails-->"+creExamDetails);
                if(creDetails!=null) {
                  creDetailsInfo = creDetails.split("\\!");
                  System.out.println("creDetailsInfo length-->"+creDetailsInfo.length);
                  for(int i=0;i<creDetailsInfo.length;i++) {
                      consultantDetails[i] = creDetailsInfo[i];
                      System.out.println(creDetailsInfo[i]);
                  }
                  System.out.println(creExamDetails+"-->"+consultantDetails.length);
                  consultantDetails[7] = creExamDetails;
                  }
                  
                  
                // creDetailsInfo[6] = creExamDetails;
                 /*creDetailsInfo[0] ->ID
                  * creDetailsInfo[1] ->ConsultentId
                  * creDetailsInfo[2] ->Full Name
                  * creDetailsInfo[3] ->Email
                  * creDetailsInfo[4] ->Status
                  */
            
        }catch (SQLException se){
            se.printStackTrace();
            //throw new ServiceLocatorException(se);
        }catch (Exception se){
            //throw new ServiceLocatorException(se);
            se.printStackTrace();
        }finally{
            try{
             /*   if(resultSet != null){
                    resultSet.close();
                    resultSet = null;
                }
                if(statement != null){
                    statement.close();
                    statement = null;
                }*/
                if(callableStatement != null){
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
        
        return consultantDetails;
     }
 
 public String[] mcertLoginCheck1(int empId,int examtypeId) throws ServiceLocatorException {
         Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        String queryString="";
        String consultantDetails[] = new String[8];
        String creDetailsInfo[] = null;
       // String queryString = "SELECT * FROM tblCreConsultentDetails WHERE ConsultentId='"+consultantId+"'";
        try{
            connection = ConnectionProvider.getInstance().getConnection();
         
            queryString = "SELECT Id,PASSWORD,LoginId,CONCAT(Fname,' ',Mname,'.',Lname) AS Name,Email,STATUS   FROM tblMcertConsultant WHERE ActualId="+empId;
             statement = connection.createStatement();
             resultSet = statement.executeQuery(queryString);
             
               if(resultSet.next()){
                consultantDetails[0] = resultSet.getString("Id");
                consultantDetails[1] = resultSet.getString("LoginId");
                consultantDetails[2] = resultSet.getString("Name");
                  consultantDetails[3] = resultSet.getString("Email");
                   consultantDetails[4] = resultSet.getString("STATUS");
               } 
             consultantDetails[5]=getMcertExamDetails(examtypeId);      
            System.out.println("consultantDetails[5]..."+consultantDetails[5]);
            
        }catch (SQLException se){
            se.printStackTrace();
            //throw new ServiceLocatorException(se);
        }catch (Exception se){
            //throw new ServiceLocatorException(se);
            se.printStackTrace();
        }finally{
            try{
             /*   if(resultSet != null){
                    resultSet.close();
                    resultSet = null;
                }
                if(statement != null){
                    statement.close();
                    statement = null;
                }*/
                if(callableStatement != null){
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
        
        return consultantDetails;
     }
 
 
 
 public String getMcertExamDetails(int examtypeId) throws ServiceLocatorException {
         Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        String queryString="";
        String consultantDetails = "";
      
       // String queryString = "SELECT * FROM tblCreConsultentDetails WHERE ConsultentId='"+consultantId+"'";
        try{
            connection = ConnectionProvider.getInstance().getConnection();
         
            System.out.println("examtypeId..."+examtypeId);
            callableStatement = connection.prepareCall("{call spMcertGetExamDetails(?,?)}");
            callableStatement.setInt(1,examtypeId);
                 callableStatement.registerOutParameter(2,Types.VARCHAR);
                 callableStatement.execute();
                  consultantDetails= callableStatement.getString(2);
                System.out.println("consultantDetails..."+consultantDetails);
                 
                 
        }catch (SQLException se){
            se.printStackTrace();
            //throw new ServiceLocatorException(se);
        }catch (Exception se){
            //throw new ServiceLocatorException(se);
            se.printStackTrace();
        }finally{
            try{
             /*   if(resultSet != null){
                    resultSet.close();
                    resultSet = null;
                }
                if(statement != null){
                    statement.close();
                    statement = null;
                }*/
                if(callableStatement != null){
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
        
        return consultantDetails;
     }
 
public CreReviewVTO mcertStartExam(int creId,int examTypeId) throws ServiceLocatorException  {
    System.out.println("in mcertStartExam impl.......creId..."+creId+"examTypeId..."+examTypeId);
     CreReviewVTO creReviewVTO = new CreReviewVTO();
     Connection connection = null;
     CallableStatement callableStatement = null;
     ResultSet resultSet = null;
     QuestionsVTO questionVTO = null;
            Map  questionMap = null;
      try {
            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("{call spMcertDoStartExam(?,?,?,?,?,?)}");
            callableStatement.setInt(1,creId);
            callableStatement.setInt(2,examTypeId);
            callableStatement.registerOutParameter(3,Types.INTEGER);
            callableStatement.registerOutParameter(4,Types.INTEGER);
            callableStatement.registerOutParameter(5,Types.INTEGER);
            callableStatement.registerOutParameter(6,Types.INTEGER);
            
            resultSet = callableStatement.executeQuery();

            creReviewVTO.setTotalQues(callableStatement.getInt(3));
            creReviewVTO.setMinMarks(callableStatement.getInt(4));
            creReviewVTO.setTotDuration(callableStatement.getInt(5));
            creReviewVTO.setExamKey(callableStatement.getInt(6));
            System.out.println("setTotalQues..."+callableStatement.getInt(3)+"setMinMarks"+callableStatement.getInt(4)+"setTotDuration.."+callableStatement.getInt(5)+"setExamKey..."+callableStatement.getInt(6));
             questionMap = new TreeMap();
              int i=1;
            while(resultSet.next()){
             questionVTO = new QuestionsVTO();
                questionVTO.setId(resultSet.getInt("Id"));
                questionVTO.setQuestion(resultSet.getString("Question"));
                questionVTO.setOption1(resultSet.getString("option1"));
                questionVTO.setOption2(resultSet.getString("option2"));
                questionVTO.setOption3(resultSet.getString("option3"));
                questionVTO.setOption4(resultSet.getString("option4"));
                questionVTO.setSubtopicId(resultSet.getInt("SubtopicId"));
                questionVTO.setSubTopicName(resultSet.getString("SubtopicName"));
                questionMap.put(i,questionVTO);
                
                i++;
            }
            creReviewVTO.setQuestionVtoMap(questionMap);
            
            deleteMcertQuestions(creReviewVTO.getExamKey());
            
     } catch(SQLException se){
         se.printStackTrace();
            throw new ServiceLocatorException(se);
        }finally{
            try{
                 if(resultSet != null){
                    resultSet.close();
                    resultSet = null;
                }
                if(callableStatement != null){
                    callableStatement.close();
                    callableStatement = null;
                }
                if(connection!=null){
                    connection.close();
                    connection = null;
                }
            }catch(SQLException sqle){
                sqle.printStackTrace();
                throw new ServiceLocatorException(sqle);
            }
        }
           
     return creReviewVTO;
}
public int deleteMcertQuestions(int examKey) throws ServiceLocatorException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        ResultSet resultSet = null;
        String subtopicslist = null;
        int i=0;
        try{
              preparedStatement = connection.prepareStatement("DELETE FROM tblCretemp_EQ WHERE ExmKeyId = "+examKey);
              i = preparedStatement.executeUpdate();
           
           
        }catch (Exception ex) {
            throw new ServiceLocatorException(ex);
        }finally {     
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
                
            }catch(SQLException sqle){
                throw new ServiceLocatorException(sqle);
            }
        }
        return i;
  }


public void doSubmitMcertExam(int examKey,int creId,int totalQuest,int attemptedQuest,int minMarks,String questionVtoList,McertificationAction mcertificationAction ) throws ServiceLocatorException  {
    System.out.println("in doSubmitMcertExam...examKey.."+examKey+"creId..."+creId+"attemptedQuest..."+attemptedQuest); 
    CreReviewVTO creReviewVTO = new CreReviewVTO();
     Connection connection = null;
     CallableStatement callableStatement = null;
     ResultSet resultSet = null;
     QuestionsVTO questionVTO = null;
            Map  questionMap = null;
            List subTopicDetailsList = null;
      try {
            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("{call spMcertSubmitExam(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            callableStatement.setInt(1,examKey);
            callableStatement.setInt(2,creId);
            callableStatement.setInt(3,totalQuest);
            callableStatement.setInt(4,attemptedQuest);
            callableStatement.setInt(5,minMarks);
            callableStatement.setString(6,questionVtoList);
            callableStatement.registerOutParameter(7,Types.VARCHAR);
            callableStatement.registerOutParameter(8,Types.VARCHAR);
            callableStatement.registerOutParameter(9,Types.INTEGER);
            callableStatement.registerOutParameter(10,Types.INTEGER);
            callableStatement.registerOutParameter(11,Types.INTEGER);
            callableStatement.registerOutParameter(12,Types.VARCHAR);
            callableStatement.registerOutParameter(13,Types.VARCHAR);
            callableStatement.execute();
            
            mcertificationAction.setDomainName(callableStatement.getString(7));
            mcertificationAction.setTopicName(callableStatement.getString(8));
            mcertificationAction.setTotalQuest(callableStatement.getInt(9));
            mcertificationAction.setAttemptedQuestions(callableStatement.getInt(10));
            mcertificationAction.setExamMarks(callableStatement.getInt(11));
            mcertificationAction.setExamStatus(callableStatement.getString(12));
            
            String subTopicDetailResult = "";
           subTopicDetailResult = callableStatement.getString(13);
            
            String subTopics [] = subTopicDetailResult.split("@");
            
            subTopicDetailsList = new ArrayList();
            ExamDetailInfoVTO examDetailInfoVTO = null;
            for(int i=0;i<subTopics.length;i++) {
                
                String subTopicInfo[] = subTopics[i].split("!");
               
                
                 examDetailInfoVTO = new ExamDetailInfoVTO();
            
                examDetailInfoVTO.setSubtopicName(subTopicInfo[0]);
          
                examDetailInfoVTO.setSubtopictotalQuestions(Integer.parseInt(subTopicInfo[1]));
               
                examDetailInfoVTO.setAttemptedQuestion(Integer.parseInt(subTopicInfo[3]));
               
                examDetailInfoVTO.setSubtopicMarks(Integer.parseInt(subTopicInfo[2]));
               
                subTopicDetailsList.add(examDetailInfoVTO);
             
           
            }
            mcertificationAction.setExamDetailInfoList(subTopicDetailsList);
            
//            callableStatement.registerOutParameter(3,Types.INTEGER);
//            callableStatement.registerOutParameter(4,Types.INTEGER);
//            callableStatement.registerOutParameter(5,Types.INTEGER);
//            callableStatement.registerOutParameter(6,Types.INTEGER);
            
            //resultSet = callableStatement.executeQuery();

//            creReviewVTO.setTotalQues(callableStatement.getInt(3));
//            creReviewVTO.setMinMarks(callableStatement.getInt(4));
//            creReviewVTO.setTotDuration(callableStatement.getInt(5));
//            creReviewVTO.setExamKey(callableStatement.getInt(6));
          /*   questionMap = new TreeMap();
              int i=1;
            while(resultSet.next()){
             questionVTO = new QuestionsVTO();
                questionVTO.setId(resultSet.getInt("Id"));
                questionVTO.setQuestion(resultSet.getString("Question"));
                questionVTO.setOption1(resultSet.getString("option1"));
                questionVTO.setOption2(resultSet.getString("option2"));
                questionVTO.setOption3(resultSet.getString("option3"));
                questionVTO.setOption4(resultSet.getString("option4"));
                questionVTO.setSubtopicId(resultSet.getInt("SubtopicId"));
                questionVTO.setSubTopicName(resultSet.getString("SubtopicName"));
                questionMap.put(i,questionVTO);
                
                i++;
            }
            creReviewVTO.setQuestionVtoMap(questionMap);
            
            deleteCreQuestions(creReviewVTO.getExamKey());
            */
     } catch(SQLException se){
         se.printStackTrace();
            throw new ServiceLocatorException(se);
        }finally{
            try{
                 if(resultSet != null){
                    resultSet.close();
                    resultSet = null;
                }
                if(callableStatement != null){
                    callableStatement.close();
                    callableStatement = null;
                }
                if(connection!=null){
                    connection.close();
                    connection = null;
                }
            }catch(SQLException sqle){
                sqle.printStackTrace();
                throw new ServiceLocatorException(sqle);
            }
        }
           
     
}


public List  reviewMcertExam(int examKeyId) throws ServiceLocatorException {
       QuestionsVTO questionsVTO  = null;
       List questionsVtoList = null;
       Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sqlQuery = "SELECT tblEcertQuestion.ID,Question,option1,option2,option3,option4,Explanation,AnwerOption,empAns FROM tblEcertQuestion LEFT OUTER JOIN tblMcertSummary ON (tblEcertQuestion.ID = tblMcertSummary.QUESTIONID) WHERE ExamKeyId ="+examKeyId;
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


public Collection getMcertExamReviewCollection(int creId,int noOfRecords) throws ServiceLocatorException {
        Map creExamReviewMap = new TreeMap();
        int counter = 1;
        String tempCreatedDate ="";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            //String queryString = "SELECT * FROM tblCreVPComments WHERE CreId="+creId+" AND  DeletedFlag != 1 ORDER BY CretedDate DESC";
            String queryString = "SELECT tblMcertResult.ExamKeyId AS examKeyId,tblMcertResult.EmpId AS empId,Marks,TotalQuestions,AttemptedQuestions,DateSubmitted,ExamStatus,tblMcertKey.ExamTypeId,tblMcertSetExam.ExamType AS examType FROM tblMcertResult LEFT JOIN tblMcertKey ON tblMcertKey.ID=tblMcertResult.ExamKeyId LEFT JOIN tblMcertSetExam  ON tblMcertSetExam.Id=tblMcertKey.ExamTypeId WHERE tblMcertResult.EmpId = 'MSSMCERT"+creId+"'";
           // System.out.println("Cre Exam Review Query-->"+queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while(resultSet.next()){
                if(counter <= noOfRecords){
                    CreReviewVTO creExamReviewVTO = new CreReviewVTO();
                   creExamReviewVTO.setCreLoginId(resultSet.getString("empId"));
                   creExamReviewVTO.setExamTypeName(resultSet.getString("examType"));
                   creExamReviewVTO.setMarks(resultSet.getInt("Marks"));
                   creExamReviewVTO.setTotalQuestions(resultSet.getInt("TotalQuestions"));
                   creExamReviewVTO.setAttemptedQuestions(resultSet.getInt("AttemptedQuestions"));
                   creExamReviewVTO.setDateSubmitted(resultSet.getString("DateSubmitted"));
                   creExamReviewVTO.setExamStatus(resultSet.getString("ExamStatus"));
                   creExamReviewVTO.setExamKeyId(String.valueOf(resultSet.getInt("examKeyId")));
                    creExamReviewMap.put("stateVTO"+counter,creExamReviewVTO);
                    counter++;
                }
            }
            
        }catch (SQLException se){
            se.printStackTrace();
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
                se.printStackTrace();
                throw new ServiceLocatorException(se);
            }
        }
        return creExamReviewMap.values();
    }
   
    public List getExamCompletedList(int actualId) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        List<Integer> examCompleted=new ArrayList<Integer>();
        //int mcertId = 0;
        //   Map collegeNameMap = new LinkedHashMap();
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT ExamTypeId FROM tblMcertKey JOIN tblMcertResult ON (tblMcertKey.id=ExamKeyId)"
                + " WHERE tblMcertKey.EMPID=(SELECT id FROM tblMcertConsultant WHERE ActualId="+ actualId+")";
       // System.out.println("-------------->"+queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
               examCompleted.add(resultSet.getInt("ExamTypeId"));
               // System.out.println("exam id--->"+resultSet.getInt("ExamTypeId"));
               //return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }

        return examCompleted;
    }
}
