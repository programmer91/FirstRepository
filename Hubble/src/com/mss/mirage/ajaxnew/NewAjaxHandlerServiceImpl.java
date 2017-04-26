/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.mirage.ajaxnew;

import com.mss.mirage.ecertification.QuestionsVTO;
import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.AuthorizationManager;
import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.Properties;
import com.mss.mirage.util.ServiceLocatorException;
import java.io.File;
import java.io.FileInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import jxl.Sheet;
import jxl.Workbook;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.json.JSONObject;

/**
 *
 * @author miracle
 */
public class NewAjaxHandlerServiceImpl implements NewAjaxHandlerService {

    public String getMcertRecordsList(String startDate, String toDate, String status) throws ServiceLocatorException {
        // System.out.println("hiiii");
        StringBuffer queryString = new StringBuffer();
        String resultString = "";
        String resultString1 = "";
        String state = "";
        String salesRepTerritory = "";
        //  CallableStatement callableStatement = null;
        //  DataSourceDataProvider dataSourceDataProvider = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionProvider.getInstance().getConnection();

            queryString.append("SELECT Id, LoginId,Email,concat(FName,' ',Mname,'.',LName) as consultantName,Status FROM tblMcertConsultant WHERE 1=1 ");

            /*   if(category != 0 ) {
            queryString.append("AND Category = "+category+" ");
            }
            if(consultantName != null && !"".equals(consultantName)) {
            queryString.append("AND (FName LIKE '%"+consultantName+"%' OR  LName LIKE '%"+consultantName+"%' OR MName LIKE '%"+consultantName+"%')");
            }*/
            if ((startDate != null && !"".equals(startDate)) && (toDate != null && !"".equals(toDate))) {
                queryString.append(" AND datediff(CreatedDate ,'" + DateUtility.getInstance().convertStringToMySQLDate(startDate) + "')>=0 and datediff(CreatedDate ,'" + DateUtility.getInstance().convertStringToMySQLDate(toDate) + "')<=0");
            }
            if (status != null && !"".equals(status)) {
                queryString.append(" AND Status='" + status + "'");

            }

            queryString.append(" ORDER BY Id");


            preparedStatement = connection.prepareStatement(queryString.toString());

            resultSet = preparedStatement.executeQuery();
            String strConsId = "";
            String strConsName = "";
            String strEmail = "";
            String strCategory = "";
            String strConsLoginId = "";
            String strStaus = "";
            String qualDetails = "";
            int count = 0;
            while (resultSet.next()) {

                strConsId = strConsId + resultSet.getString("Id") + "!";
                strConsLoginId = strConsLoginId + resultSet.getString("LoginId") + "!";
                strConsName = strConsName + resultSet.getString("consultantName") + "!";
                strEmail = strEmail + resultSet.getString("Email") + "!";
                strStaus = strStaus + resultSet.getString("Status") + "!";
            }
            //resultString1 = strConsId+"@"+strConsName+"@"+strStatus; 



            resultString1 = strConsId + "#^$" + strConsName + "#^$" + strEmail + "#^$" + strConsLoginId + "#^$" + strStaus;

            // System.out.println("resultString1-->"+resultString1);

        } catch (Exception e) {

            throw new ServiceLocatorException(e);

        } finally {
            try {
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

        // return resultString;
        return resultString1;
    }

    public String mcertRecordStatusUpdate(String consultantIds, String loginId, String status, String examNameIdList) throws ServiceLocatorException {

        System.out.println("consultantIds..." + consultantIds + "examNameIdList..." + examNameIdList + "status..." + status);
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;

        PreparedStatement preparedStatement3 = null;

        String activateRecords = "";
        String SubTopicIds = "";

        Connection connection1 = null;
        Connection connection2 = null;
        Connection connection3 = null;
        try {
            connection1 = ConnectionProvider.getInstance().getConnection();
            connection2 = ConnectionProvider.getInstance().getConnection();
            preparedStatement1 = connection1.prepareStatement("UPDATE tblMcertConsExamTopics SET Status = 'InActive' WHERE McertConsultantId = ?");
            preparedStatement2 = connection2.prepareStatement("UPDATE tblMcertConsultant SET STATUS =?, ModifiedBy = '" + loginId + "' , ModifiedDate = '" + DateUtility.getInstance().getCurrentMySqlDateTime() + "'  WHERE Id = ?");


            //   String consutantIsArray [] = consultantIds.split("!");
            if (status.equals("Active")) {
                connection3 = ConnectionProvider.getInstance().getConnection();
                // System.out.println("consultantIds-->"+consultantIds);
                String Str = examNameIdList;
                // System.out.println("Str-->"+Str);

                preparedStatement3 = connection3.prepareStatement("INSERT INTO tblMcertConsExamTopics(McertConsultantId,Status,Examtypeid) VALUES(?,?,?)");
                // for(int i=0;i<consutantIsArray.length;i++) {
                for (String cerID : consultantIds.split("!")) {
                    // System.out.println("cerID-->"+cerID);

                    preparedStatement1.setInt(1, Integer.parseInt(cerID));
                    preparedStatement1.executeUpdate();

                    preparedStatement2.setString(1, "Active");
                    preparedStatement2.setInt(2, Integer.parseInt(cerID));
                    preparedStatement2.executeUpdate();

                    for (String retval : Str.split(",")) {
                        // System.out.println("retval-->"+retval);

                        preparedStatement3.setInt(1, Integer.parseInt(cerID));
                        preparedStatement3.setString(2, "Active");
                        preparedStatement3.setInt(3, Integer.parseInt(retval));
                        preparedStatement3.executeUpdate();
                    }

                    activateRecords = activateRecords + " " + cerID;
                }
            } else {
                // preparedStatement = connection.prepareStatement("UPDATE tblCreConsultentDetails SET STATUS =?, ModifiedBy = '"+loginId+"' , ModifiedDate = '"+DateUtility.getInstance().getCurrentMySqlDateTime()+"'  WHERE Id = ?");
                //preparedStatement2 = connection2.prepareStatement("UPDATE tblCreConsExamTopics SET Status = 'InActive' WHERE CreId = ? ");
                //  for(int i=0;i<consutantIsArray.length;i++) {
                // System.out.println("status-->"+status);
                for (String cerID : consultantIds.split("!")) {

                    preparedStatement1.setInt(1, Integer.parseInt(cerID));
                    preparedStatement1.executeUpdate();

                    preparedStatement2.setString(1, status);
                    preparedStatement2.setInt(2, Integer.parseInt(cerID));
                    preparedStatement2.executeUpdate();

                    activateRecords = activateRecords + " " + cerID;
                }
            }


        } catch (Exception sqe) {
            sqe.printStackTrace();
        } finally {
            try {

                if (preparedStatement3 != null) {
                    preparedStatement3.close();
                    preparedStatement3 = null;
                }
                if (preparedStatement2 != null) {
                    preparedStatement2.close();
                    preparedStatement2 = null;
                }
                if (preparedStatement1 != null) {
                    preparedStatement1.close();
                    preparedStatement1 = null;
                }
                if (connection3 != null) {
                    connection3.close();
                    connection3 = null;
                }
                if (connection2 != null) {
                    connection2.close();
                    connection2 = null;
                }
                if (connection1 != null) {
                    connection1.close();
                    connection1 = null;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }

        return activateRecords;
    }

    public String getMcertQuestion(int questionNo, HttpServletRequest httpServletRequest, int selectedAns, String navigation, int remainingQuestions, int onClickStatus, int subTopicId, int specficQuestionNo) throws ServiceLocatorException {
        System.out.println("in getMcertQuestion impl ......" + questionNo + "selectedAns.." + selectedAns);

        StringBuffer stringBuffer = new StringBuffer();
        // QuestionsVTO questionVTO = null,nextQuestionVTO = null,previousQuestionVTO = null,startQuestionVTO = null;
        QuestionsVTO questionVTO = null, nextQuestionVTO = null, previousQuestionVTO = null, specificQuestionVTO = null, startQuestionVTO = null;
        int empId = 0, examKeyId = 0, answer = 0, attemptedQuestions = 0, questionId = 0;

        try {
            empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
            examKeyId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.MCERT_CURRENT_EXAM_KEY).toString());
            Map questionVtoMap = (Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.MCERT_QUESTIONS_MAP);
            /*In start Exam Start*/
            if (navigation.equalsIgnoreCase("I")) {
                int qId = 0;
                /*
                if(onClickStatus != 0 ){
                questionVTO = (QuestionsVTO)questionVtoMap.get(questionNo);
                qId = questionVTO.getId();
                
                attemptedQuestions = DataSourceDataProvider.getInstance().isQuestionAttempt(examKeyId,qId);
                }
                 * */

                /**  Display Question */
                startQuestionVTO = (QuestionsVTO) questionVtoMap.get(1);
                int startQId = startQuestionVTO.getId();

                int mapsize = questionVtoMap.size();
                remainingQuestions = mapsize;

                /** XML start **/
                int startQuestionNo = 1;
                stringBuffer.append("<xml version=\"1.0\">");
                stringBuffer.append("<QUESTIONDETAILS >");
                stringBuffer.append("<QUESTIONSTATUS>true</QUESTIONSTATUS>");
                stringBuffer.append("<QUESTIONID>" + startQId + "</QUESTIONID>");
                stringBuffer.append("<QUESTIONNAME><![CDATA[" + startQuestionVTO.getQuestion() + "]]></QUESTIONNAME>");
                stringBuffer.append("<OPTION1><![CDATA[" + startQuestionVTO.getOption1() + "]]></OPTION1>");
                stringBuffer.append("<OPTION2><![CDATA[" + startQuestionVTO.getOption2() + "]]></OPTION2>");
                stringBuffer.append("<OPTION3><![CDATA[" + startQuestionVTO.getOption3() + "]]></OPTION3>");
                stringBuffer.append("<OPTION4><![CDATA[" + startQuestionVTO.getOption4() + "]]></OPTION4>");

                stringBuffer.append("<MAPQUESTIONID>" + startQuestionNo + "</MAPQUESTIONID>");
                stringBuffer.append("<EMPANSWER>" + answer + "</EMPANSWER>");
                stringBuffer.append("<SUBTOPICID>" + startQuestionVTO.getSubtopicId() + "</SUBTOPICID>");
                stringBuffer.append("<REMAININGQUESTIONS>" + remainingQuestions + "</REMAININGQUESTIONS>");
                if (!"".equals(startQuestionVTO.getSubTopicName())) {
                    stringBuffer.append("<SECTION>" + startQuestionVTO.getSubTopicName() + "</SECTION>");
                }
                stringBuffer.append("</QUESTIONDETAILS>");
                stringBuffer.append("</xml>");


            } /*In start exam end*/ /*
             * Getting Specific Question start
             */ else if (navigation.equalsIgnoreCase("R")) {
                int qId = 0;
                /* Insert Question into db */
                // if(onClickStatus != 0 ){
                questionVTO = (QuestionsVTO) questionVtoMap.get(questionNo);
                qId = questionVTO.getId();
                /** answered by user or not */
                attemptedQuestions = DataSourceDataProvider.getInstance().isMcertQuestionAttempt(examKeyId, qId);
                // }
                /**  Display Question */
                // nextQuestionVTO = (QuestionsVTO)questionVtoMap.get(questionNo+1);
                // System.out.println("specficQuestionNo--->"+specficQuestionNo);
                specificQuestionVTO = (QuestionsVTO) questionVtoMap.get(specficQuestionNo);
                //int nextQId = nextQuestionVTO.getId();
                int specificQId = specificQuestionVTO.getId();
                // System.out.println("specificQId--->"+specificQId);
                //  if(onClickStatus == 0 ){
                //      int mapsize = questionVtoMap.size();
                //        remainingQuestions =  mapsize;
                //  }

                if (selectedAns != 0) {
                    if (attemptedQuestions == 0) {
                        insertAnswer(qId, selectedAns, empId, examKeyId, subTopicId);
                        remainingQuestions = remainingQuestions - 1;
                    } else {

                        updateAnswer(qId, selectedAns, empId, examKeyId);
                    }
                }
                //System.out.println("before getting ANSWER specificQId-->"+specificQId);

                // if(questionNo < questionVtoMap.size()){
                answer = DataSourceDataProvider.getInstance().getMcertAnswer(examKeyId, specificQId, empId);
                //}
                //  System.out.println("Answer-->"+answer);

                /** XML start **/
                //  int specificQuestionNo = questionNo + 1;
                stringBuffer.append("<xml version=\"1.0\">");
                stringBuffer.append("<QUESTIONDETAILS >");
                stringBuffer.append("<QUESTIONSTATUS>true</QUESTIONSTATUS>");
                stringBuffer.append("<QUESTIONID>" + specificQId + "</QUESTIONID>");
                stringBuffer.append("<QUESTIONNAME><![CDATA[" + specificQuestionVTO.getQuestion() + "]]></QUESTIONNAME>");
                stringBuffer.append("<OPTION1><![CDATA[" + specificQuestionVTO.getOption1() + "]]></OPTION1>");
                stringBuffer.append("<OPTION2><![CDATA[" + specificQuestionVTO.getOption2() + "]]></OPTION2>");
                stringBuffer.append("<OPTION3><![CDATA[" + specificQuestionVTO.getOption3() + "]]></OPTION3>");
                stringBuffer.append("<OPTION4><![CDATA[" + specificQuestionVTO.getOption4() + "]]></OPTION4>");

                stringBuffer.append("<MAPQUESTIONID>" + specficQuestionNo + "</MAPQUESTIONID>");
                stringBuffer.append("<EMPANSWER>" + answer + "</EMPANSWER>");
                stringBuffer.append("<SUBTOPICID>" + specificQuestionVTO.getSubtopicId() + "</SUBTOPICID>");
                stringBuffer.append("<REMAININGQUESTIONS>" + remainingQuestions + "</REMAININGQUESTIONS>");
                if (!"".equals(specificQuestionVTO.getSubTopicName())) {
                    stringBuffer.append("<SECTION>" + specificQuestionVTO.getSubTopicName() + "</SECTION>");
                }
                stringBuffer.append("</QUESTIONDETAILS>");
                stringBuffer.append("</xml>");


            } /*
             * 
             * Getting Specific Question end
             */ /** in Next if*/
            else if (navigation.equalsIgnoreCase("N")) {
                int qId = 0;
                nextQuestionVTO = (QuestionsVTO) questionVtoMap.get(questionNo + 1);
                int nextQId = nextQuestionVTO.getId();
                // System.out.println(nextQId);
                 /* Insert Question into db */
                // if(onClickStatus != 0 ){
                questionVTO = (QuestionsVTO) questionVtoMap.get(questionNo);
                qId = questionVTO.getId();
                /** answered by user or not */
                attemptedQuestions = DataSourceDataProvider.getInstance().isMcertQuestionAttempt(examKeyId, qId);
                // }
                /**  Display Question */
                //  else {
                //             int mapsize = questionVtoMap.size();
                //             remainingQuestions =  mapsize;
                //   }
                if (selectedAns != 0) {
                    if (attemptedQuestions == 0) {
                        insertAnswer(qId, selectedAns, empId, examKeyId, subTopicId);
                        remainingQuestions = remainingQuestions - 1;
                    } else {
                        updateAnswer(qId, selectedAns, empId, examKeyId);
                    }
                }

                //   if(questionNo < questionVtoMap.size()){
                answer = DataSourceDataProvider.getInstance().getMcertAnswer(examKeyId, nextQId, empId);
                // }

                /** XML start **/
                int nextQuestionNo = questionNo + 1;
                stringBuffer.append("<xml version=\"1.0\">");
                stringBuffer.append("<QUESTIONDETAILS >");
                stringBuffer.append("<QUESTIONSTATUS>true</QUESTIONSTATUS>");
                stringBuffer.append("<QUESTIONID>" + nextQId + "</QUESTIONID>");
                stringBuffer.append("<QUESTIONNAME><![CDATA[" + nextQuestionVTO.getQuestion() + "]]></QUESTIONNAME>");
                stringBuffer.append("<OPTION1><![CDATA[" + nextQuestionVTO.getOption1() + "]]></OPTION1>");
                stringBuffer.append("<OPTION2><![CDATA[" + nextQuestionVTO.getOption2() + "]]></OPTION2>");
                stringBuffer.append("<OPTION3><![CDATA[" + nextQuestionVTO.getOption3() + "]]></OPTION3>");
                stringBuffer.append("<OPTION4><![CDATA[" + nextQuestionVTO.getOption4() + "]]></OPTION4>");

                stringBuffer.append("<MAPQUESTIONID>" + nextQuestionNo + "</MAPQUESTIONID>");
                stringBuffer.append("<EMPANSWER>" + answer + "</EMPANSWER>");
                stringBuffer.append("<SUBTOPICID>" + nextQuestionVTO.getSubtopicId() + "</SUBTOPICID>");
                stringBuffer.append("<REMAININGQUESTIONS>" + remainingQuestions + "</REMAININGQUESTIONS>");
                if (!"".equals(nextQuestionVTO.getSubTopicName())) {
                    stringBuffer.append("<SECTION>" + nextQuestionVTO.getSubTopicName() + "</SECTION>");
                }
                stringBuffer.append("</QUESTIONDETAILS>");
                stringBuffer.append("</xml>");


            } /** End of next If */
            /* in prevoius if */ else if (navigation.equalsIgnoreCase("P")) {
                questionVTO = (QuestionsVTO) questionVtoMap.get(questionNo);
                int qId = questionVTO.getId();
                /** answered by user or not */
                attemptedQuestions = DataSourceDataProvider.getInstance().isMcertQuestionAttempt(examKeyId, qId);
                previousQuestionVTO = (QuestionsVTO) questionVtoMap.get(questionNo - 1);
                int prevoiusQId = previousQuestionVTO.getId();

                if (selectedAns != 0) {
                    if (attemptedQuestions == 0) {
                        insertAnswer(qId, selectedAns, empId, examKeyId, subTopicId);
                        remainingQuestions = remainingQuestions - 1;
                    } else {
                        updateAnswer(qId, selectedAns, empId, examKeyId);
                    }
                }
                answer = DataSourceDataProvider.getInstance().getMcertAnswer(examKeyId, prevoiusQId, empId);

                /** XML start **/
                int previousQuestionNo = questionNo - 1;
                stringBuffer.append("<xml version=\"1.0\">");
                stringBuffer.append("<QUESTIONDETAILS >");
                stringBuffer.append("<QUESTIONSTATUS>true</QUESTIONSTATUS>");
                stringBuffer.append("<QUESTIONID>" + prevoiusQId + "</QUESTIONID>");
                stringBuffer.append("<QUESTIONNAME><![CDATA[" + previousQuestionVTO.getQuestion() + "]]></QUESTIONNAME>");
                stringBuffer.append("<OPTION1><![CDATA[" + previousQuestionVTO.getOption1() + "]]></OPTION1>");
                stringBuffer.append("<OPTION2><![CDATA[" + previousQuestionVTO.getOption2() + "]]></OPTION2>");
                stringBuffer.append("<OPTION3><![CDATA[" + previousQuestionVTO.getOption3() + "]]></OPTION3>");
                stringBuffer.append("<OPTION4><![CDATA[" + previousQuestionVTO.getOption4() + "]]></OPTION4>");


                stringBuffer.append("<MAPQUESTIONID>" + previousQuestionNo + "</MAPQUESTIONID>");
                stringBuffer.append("<EMPANSWER>" + answer + "</EMPANSWER>");
                stringBuffer.append("<SUBTOPICID>" + previousQuestionVTO.getSubtopicId() + "</SUBTOPICID>");
                stringBuffer.append("<REMAININGQUESTIONS>" + remainingQuestions + "</REMAININGQUESTIONS>");
                if (!"".equals(previousQuestionVTO.getSubTopicName())) {
                    stringBuffer.append("<SECTION>" + previousQuestionVTO.getSubTopicName() + "</SECTION>");
                }
                stringBuffer.append("</QUESTIONDETAILS>");
                stringBuffer.append("</xml>");


            } /* end of Previous if */ /* in submit if */ else if (navigation.equalsIgnoreCase("S")) {
                // System.out.println("in sumbmit cond..");
                questionVTO = (QuestionsVTO) questionVtoMap.get(questionNo);
                int qId = questionVTO.getId();
                /** answered by user or not */
                attemptedQuestions = DataSourceDataProvider.getInstance().isMcertQuestionAttempt(examKeyId, qId);
                /*  if(onClickStatus == 0 ){
                int mapsize = questionVtoMap.size();
                remainingQuestions =  mapsize;
                }*/
                // System.out.println("attemptedQuestions-->"+attemptedQuestions);
                if (selectedAns != 0) {
                    if (attemptedQuestions == 0) {
                        insertAnswer(qId, selectedAns, empId, examKeyId, subTopicId);
                        remainingQuestions = remainingQuestions - 1;
                    } else {
                        updateAnswer(qId, selectedAns, empId, examKeyId);
                    }
                }
                stringBuffer.append("<xml version=\"1.0\">");
                stringBuffer.append("<QUESTIONDETAILS >");
                stringBuffer.append("<QUESTIONSTATUS>false</QUESTIONSTATUS>");
                stringBuffer.append("<REMAININGQUESTIONS>" + remainingQuestions + "</REMAININGQUESTIONS>");
                stringBuffer.append("</QUESTIONDETAILS>");
                stringBuffer.append("</xml>");
            }

            /* end of subbmit if */

        } catch (Exception sle) {
            sle.printStackTrace();
        }
        return stringBuffer.toString();

    }

    public void insertAnswer(int questionNo, int selectedAns, int empId, int examKeyId, int subTopicId) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int i = 0;
        try {
            connection = ConnectionProvider.getInstance().getConnection();

            preparedStatement = connection.prepareStatement("INSERT INTO tblMcertSummary (EmpId,ExamKeyId,QuestionId,EmpAns,DateSubmitted,SubtopicId) VALUES(?,?,?,?,?,? )");
            preparedStatement.setInt(1, empId);
            preparedStatement.setInt(2, examKeyId);
            preparedStatement.setInt(3, questionNo);
            preparedStatement.setInt(4, selectedAns);
            preparedStatement.setTimestamp(5, DateUtility.getInstance().getCurrentMySqlDateTime());
            preparedStatement.setInt(6, subTopicId);
            i = preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.err.println("Exception is-->" + e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (Exception sqle) {
                System.err.println("SQL Exception is-->" + sqle.getMessage());
            }
        }

    }

    public void updateAnswer(int questionNo, int selectedAns, int empId, int examKeyId) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int i = 0;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("UPDATE tblMcertSummary SET EmpAns=?  WHERE EmpId = ? AND ExamKeyId = ? AND QuestionId = ?");
            preparedStatement.setInt(1, selectedAns);
            preparedStatement.setInt(2, empId);
            preparedStatement.setInt(3, examKeyId);
            preparedStatement.setInt(4, questionNo);

            i = preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.err.println("Exception is-->" + e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (Exception sqle) {
                System.err.println("SQL Exception is-->" + sqle.getMessage());
            }
        }

    }

    public String getMcertDetailExamInfo(String examKeyId) throws ServiceLocatorException {

        String subTopicDetailResult = "";
        // Connection connection = null;
        CallableStatement callableStatement = null;
        Connection connection = null;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("{call spMcertResult(?,?,?,?,?,?,?,?)}");
            callableStatement.setInt(1, Integer.parseInt(examKeyId));
            callableStatement.registerOutParameter(2, Types.VARCHAR);
            callableStatement.registerOutParameter(3, Types.VARCHAR);
            callableStatement.registerOutParameter(4, Types.INTEGER);
            callableStatement.registerOutParameter(5, Types.INTEGER);
            callableStatement.registerOutParameter(6, Types.INTEGER);
            callableStatement.registerOutParameter(7, Types.VARCHAR);
            callableStatement.registerOutParameter(8, Types.VARCHAR);
            callableStatement.execute();


            subTopicDetailResult = callableStatement.getString(8);

        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {

                if (callableStatement != null) {
                    callableStatement.close();
                    callableStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return subTopicDetailResult;
    }

    public String searchPreSalesRequirementList(HttpServletRequest httpServletRequest, NewAjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException {
        Connection connection = null;
        StringBuffer stringBuffer = new StringBuffer();
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        Statement statement1 = null;
        ResultSet resultSet = null;
        ResultSet resultSet1 = null;
        String createdBy = "";
        String totalStream = "";
        String queryString = "";
        int i = 0;
        int totalRecords = 0;


        //resultType = "accessFailed";

        DateUtility dateUtility = new DateUtility();

        // System.out.println(" impl  --- createdBy----------"+ajaxHandlerAction.getCreatedBy()+"--assignedTo-------------"+ajaxHandlerAction.getAssignedTo()+"--title-------------"+ajaxHandlerAction.getTitle()+"--postedDate1-------"+ajaxHandlerAction.getPostedDate1()+"---postedDate2----"+ajaxHandlerAction.getPostedDate2()+"---status----"+ajaxHandlerAction.getStatus());


        try {
            /*queryString ="SELECT Id,ActivityType,Status,Description,DateDue,CreatedDate,AssignedToId,Comments FROM tblCrmActivity";
            queryString = queryString + " WHERE AccountId ="+accId+" AND ContactId =0 GROUP BY ActivityType,STATUS,Description,AssignedToId ORDER BY CreatedDate DESC";*/


            //   System.out.println("ajaxHandlerAction.getTitle()==="+ajaxHandlerAction.getTitle());


            int columnCounter = 0;

            String territory = "";
            connection = ConnectionProvider.getInstance().getConnection();
            StringBuffer queryStringBuffer = new StringBuffer();;


            //  queryStringBuffer.append("SELECT tblRecRequirement.Id,tblCrmAccount.NAME,JobTitle,tblRecRequirement.STATUS,tblRecRequirement.CreatedBy,AssignedTo,AssignToTechLead,tblCrmAccount.Id AS AccountId,tblRecRequirement.Country,CONCAT(`tblRecRequirement`.`State`,',',`tblRecRequirement`.`Country`) AS Location,tblRecRequirement.Location As ReqLocation ,tblRecRequirement.Practice,tblRecRequirement.StartDate,tblRecRequirement.Duration,tblRecRequirement.TargetRate,tblRecRequirement.TaxTerm,tblRecRequirement.AssignedDate,COUNT(tblRec.Id) AS ConsultantsCount,SUM(CASE WHEN tblRec.STATUS='Submitted to Sales' THEN 1 ELSE 0 END) AS StatusCount FROM tblRecRequirement LEFT OUTER JOIN tblCrmAccount ON (tblRecRequirement.CustomerId=tblCrmAccount.Id) LEFT OUTER JOIN tblRec ON (tblRecRequirement.Id=tblRec.RequirementId) ");
            queryStringBuffer.append("SELECT tblRecRequirement.Id,tblCrmAccount.NAME,JobTitle,tblRecRequirement.STATUS,tblRecRequirement.CreatedBy,AssignedTo,AssignToTechLead,tblCrmAccount.Id AS AccountId,tblRecRequirement.Country,CONCAT(`tblRecRequirement`.`State`,',',`tblRecRequirement`.`Country`) AS Location,tblRecRequirement.Location As ReqLocation ,tblRecRequirement.Practice,tblRecRequirement.StartDate,tblRecRequirement.Duration,tblRecRequirement.TargetRate,tblRecRequirement.TaxTerm,tblRecRequirement.AssignedDate,COUNT(tblRec.Id) AS ConsultantsCount,"
                    + "SUM(CASE WHEN tblRec.STATUS='Assigned' THEN 1 ELSE 0 END) AS ACount,SUM(CASE WHEN tblRec.STATUS='Tech Screen  - Phone' THEN 1 ELSE 0 END) AS TSPCount,SUM(CASE WHEN tblRec.STATUS='Tech Screen shotlisted' THEN 1 ELSE 0 END) AS TSSCount,SUM(CASE WHEN tblRec.STATUS='Tech Screen Reject' THEN 1 ELSE 0 END) AS TSRCount,SUM(CASE WHEN tblRec.STATUS='Client Submission' THEN 1 ELSE 0 END) AS CSCount,SUM(CASE WHEN tblRec.STATUS='Client Interview' THEN 1 ELSE 0 END) AS CICount,SUM(CASE WHEN tblRec.STATUS='Client Reject' THEN 1 ELSE 0 END) AS CRCount,SUM(CASE WHEN tblRec.STATUS='Joined' THEN 1 ELSE 0 END) AS JCount,SUM(CASE WHEN tblRec.STATUS='Client Interview Reject' THEN 1 ELSE 0 END) AS CIRCount,"
                    + "SUM(CASE WHEN tblRec.STATUS='Submitted to Sales' THEN 1 ELSE 0 END) AS StatusCount FROM tblRecRequirement LEFT OUTER JOIN tblCrmAccount ON (tblRecRequirement.CustomerId=tblCrmAccount.Id) LEFT OUTER JOIN tblRec ON (tblRecRequirement.Id=tblRec.RequirementId) ");
            //    queryStringBuffer.append("WHERE tblRecRequirement.Country LIKE '"+userWorkCountry+"'  " );
            queryStringBuffer.append("WHERE 1=1  ");

            if (!"All".equals(ajaxHandlerAction.getCreatedBy())) {
                queryStringBuffer.append(" AND tblRecRequirement.CreatedBy='" + ajaxHandlerAction.getCreatedBy() + "' ");
            }
            if (ajaxHandlerAction.getTitle() != null && !"".equals(ajaxHandlerAction.getTitle())) {
                queryStringBuffer.append(" AND `tblRecRequirement`.`JobTitle` LIKE '%" + ajaxHandlerAction.getTitle() + "%'");
            }
            if (ajaxHandlerAction.getPostedDate1() != null && !"".equals(ajaxHandlerAction.getPostedDate1())) {
                queryStringBuffer.append(" AND DATE(`tblRecRequirement`.`DatePosted`) >= DATE('" + DateUtility.getInstance().convertStringToMySQLDate(ajaxHandlerAction.getPostedDate1()) + "')");
            }

            if (ajaxHandlerAction.getPostedDate2() != null && !"".equals(ajaxHandlerAction.getPostedDate2())) {
                queryStringBuffer.append(" AND DATE(`tblRecRequirement`.`DatePosted`) <= DATE('" + DateUtility.getInstance().convertStringToMySQLDate(ajaxHandlerAction.getPostedDate2()) + "')");
            }

            if (!"All".equals(ajaxHandlerAction.getStatus())) {
                queryStringBuffer.append("AND `tblRecRequirement`.`Status` LIKE '" + ajaxHandlerAction.getStatus() + "'");
            }
            if (!"-1".equals(ajaxHandlerAction.getCountry())) {
                queryStringBuffer.append(" AND `tblRecRequirement`.`Country` like '" + ajaxHandlerAction.getCountry() + "' ");
            }

            if (ajaxHandlerAction.getCustomerName() != null && !"".equals(ajaxHandlerAction.getCustomerName())) //                 queryStringBuffer.append(" AND `tblCrmAccount`.`Name` like '" + ajaxHandlerAction.getCustomerName() + "' ");
            {
                queryStringBuffer.append(" AND `tblCrmAccount`.`Name` LIKE '%" + ajaxHandlerAction.getCustomerName() + "%'");
            }

            if (ajaxHandlerAction.getState() != null && !"".equals(ajaxHandlerAction.getState())) {
                queryStringBuffer.append(" AND `tblRecRequirement`.`state` like '" + ajaxHandlerAction.getState() + "' ");
            }

            if (!"-1".equals(ajaxHandlerAction.getPracticeid())) {
                queryStringBuffer.append("  AND tblRecRequirement.Practice like '" + ajaxHandlerAction.getPracticeid() + "' ");
            }
            if (ajaxHandlerAction.getRequirementId() != 0) {
                queryStringBuffer.append("  AND tblRecRequirement .Id =" + ajaxHandlerAction.getRequirementId() + " ");
            }
            if (ajaxHandlerAction.getPreSalesPerson() != null && !"".equals(ajaxHandlerAction.getPreSalesPerson())) {
                queryStringBuffer.append("AND (tblRecRequirement.AssignToTechLead ='" + ajaxHandlerAction.getPreSalesPerson() + "' || tblRecRequirement.SecondaryTechLead ='" + ajaxHandlerAction.getPreSalesPerson() + "') ");
            } else {
                Map rolesMap = (Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_ROLES);
                List finalTeachLeadList;
                if (rolesMap.containsValue("Admin") || AuthorizationManager.getInstance().isAuthorizedForSurveyForm("PRESALES_REQUIREMENT_ACCESS", httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString())) {
                    finalTeachLeadList = DataSourceDataProvider.getInstance().getTechLead();
                } else {
                    Map teamMap = (Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP);
                    List TechLeadList = DataSourceDataProvider.getInstance().getTechLead();
                    List tempTechLeadList = new ArrayList();
                    for (int j = 0; j < TechLeadList.size(); j++) {
                        if (teamMap.containsValue(TechLeadList.get(j))) {
                            tempTechLeadList.add(TechLeadList.get(j));
                        }
                    }
                    tempTechLeadList.add(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME));

                    finalTeachLeadList = tempTechLeadList;
                }
                String teamList = DataSourceDataProvider.getInstance().getStringByList(finalTeachLeadList);

                queryStringBuffer.append("AND (FIND_IN_SET(tblRecRequirement.AssignToTechLead,'" + teamList + "' )  || FIND_IN_SET(tblRecRequirement.SecondaryTechLead,'" + teamList + "' ) )");

            }
            queryStringBuffer.append(" GROUP BY tblRecRequirement.Id ");
            queryStringBuffer.append(" ORDER BY `tblRecRequirement`.`DatePosted` DESC  LIMIT " + (ajaxHandlerAction.getPgNo() - 1) * 20 + ", 20");

            //  System.out.println("queryStringBuffer"+queryStringBuffer.toString());
// for getting total counts (for pagination) ...> Start
            if ("1".equals(ajaxHandlerAction.getPgFlag().trim())) {

                StringBuffer queryStringBuf = new StringBuffer();;


                queryStringBuf.append("Select COUNT(id) As count from (SELECT tblRecRequirement.Id As id FROM tblRecRequirement LEFT OUTER JOIN tblCrmAccount ON (tblRecRequirement.CustomerId=tblCrmAccount.Id) LEFT OUTER JOIN tblRec ON (tblRecRequirement.Id=tblRec.RequirementId) ");


                queryStringBuf.append("WHERE 1=1  ");

                if (!"All".equals(ajaxHandlerAction.getCreatedBy())) {
                    queryStringBuf.append(" AND tblRecRequirement.CreatedBy='" + ajaxHandlerAction.getCreatedBy() + "' ");
                }
                if (ajaxHandlerAction.getTitle() != null && !"".equals(ajaxHandlerAction.getTitle())) {
                    queryStringBuf.append(" AND `tblRecRequirement`.`JobTitle` LIKE '%" + ajaxHandlerAction.getTitle() + "%'");
                }


                if (ajaxHandlerAction.getPostedDate1() != null && !"".equals(ajaxHandlerAction.getPostedDate1())) {
                    queryStringBuf.append(" AND DATE(`tblRecRequirement`.`DatePosted`) >= DATE('" + DateUtility.getInstance().convertStringToMySQLDate(ajaxHandlerAction.getPostedDate1()) + "')");
                }

                if (ajaxHandlerAction.getPostedDate2() != null && !"".equals(ajaxHandlerAction.getPostedDate2())) {
                    queryStringBuf.append(" AND DATE(`tblRecRequirement`.`DatePosted`) <= DATE('" + DateUtility.getInstance().convertStringToMySQLDate(ajaxHandlerAction.getPostedDate2()) + "')");
                }

                if (!"All".equals(ajaxHandlerAction.getStatus())) {
                    queryStringBuf.append("AND `tblRecRequirement`.`Status` LIKE '" + ajaxHandlerAction.getStatus() + "'");
                }
                if (!"-1".equals(ajaxHandlerAction.getCountry())) {
                    queryStringBuf.append(" AND `tblRecRequirement`.`Country` like '" + ajaxHandlerAction.getCountry() + "' ");
                }

                if (ajaxHandlerAction.getCustomerName() != null && !"".equals(ajaxHandlerAction.getCustomerName())) {
                    queryStringBuf.append(" AND `tblCrmAccount`.`Name` LIKE '%" + ajaxHandlerAction.getCustomerName() + "%'");
                }

                if (ajaxHandlerAction.getState() != null && !"".equals(ajaxHandlerAction.getState())) {
                    queryStringBuf.append(" AND `tblRecRequirement`.`state` like '" + ajaxHandlerAction.getState() + "' ");
                }

                if (!"-1".equals(ajaxHandlerAction.getPracticeid())) {
                    queryStringBuf.append("  AND tblRecRequirement.Practice like '" + ajaxHandlerAction.getPracticeid() + "' ");
                }
                if (ajaxHandlerAction.getRequirementId() != 0) {
                    queryStringBuf.append("  AND tblRecRequirement .Id =" + ajaxHandlerAction.getRequirementId() + " ");
                }
                if (ajaxHandlerAction.getPreSalesPerson() != null && !"".equals(ajaxHandlerAction.getPreSalesPerson())) {
                    queryStringBuf.append("AND (tblRecRequirement.AssignToTechLead ='" + ajaxHandlerAction.getPreSalesPerson() + "' || tblRecRequirement.SecondaryTechLead ='" + ajaxHandlerAction.getPreSalesPerson() + "') ");
                } else {
                    Map rolesMap = (Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_ROLES);
                    List finalTeachLeadList;
                    if (rolesMap.containsValue("Admin") || AuthorizationManager.getInstance().isAuthorizedForSurveyForm("PRESALES_REQUIREMENT_ACCESS", httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString())) {

                        finalTeachLeadList = DataSourceDataProvider.getInstance().getTechLead();
                    } else {
                        Map teamMap = (Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP);
                        List TechLeadList = DataSourceDataProvider.getInstance().getTechLead();
                        List tempTechLeadList = new ArrayList();
                        for (int j = 0; j < TechLeadList.size(); j++) {
                            if (teamMap.containsValue(TechLeadList.get(j))) {
                                tempTechLeadList.add(TechLeadList.get(j));
                            }
                        }
                        tempTechLeadList.add(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME));

                        finalTeachLeadList = tempTechLeadList;
                    }
                    String teamList = DataSourceDataProvider.getInstance().getStringByList(finalTeachLeadList);


                    queryStringBuf.append("AND (FIND_IN_SET(tblRecRequirement.AssignToTechLead,'" + teamList + "' )  || FIND_IN_SET(tblRecRequirement.SecondaryTechLead,'" + teamList + "' ) )");

                }
                queryStringBuf.append(" GROUP BY tblRecRequirement.Id ");
                queryStringBuf.append(" ORDER BY `tblRecRequirement`.`DatePosted` DESC) As tbl");






                //   System.out.println("REQ_SEARCH_QUERY --->" + queryStringBuf.toString());

                statement1 = connection.createStatement();
                resultSet1 = statement1.executeQuery(queryStringBuf.toString());
                while (resultSet1.next()) {
                    totalRecords = resultSet1.getInt("count");
                }

            }

            // for getting total counts (for pagination) ...> End


            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryStringBuffer.toString());



            while (resultSet.next()) {
                int RequirementId = resultSet.getInt("Id");
                String accountName = resultSet.getString("NAME");
                String JobTitle = resultSet.getString("JobTitle");
                String status = resultSet.getString("STATUS");
                String CreatedBy = resultSet.getString("CreatedBy");
                String AccountId = resultSet.getString("AccountId");
                //AccountId     

                String Recruiter = "-";
                if (resultSet.getString("AssignedTo") != null && !"".equals(resultSet.getString("AssignedTo"))) {
                    Recruiter = resultSet.getString("AssignedTo");
                }

                String PreSales = "-";
                if (resultSet.getString("AssignToTechLead") != null && !"".equals(resultSet.getString("AssignToTechLead"))) {
                    PreSales = resultSet.getString("AssignToTechLead");
                }

                String Location = "-";
                if (resultSet.getString("Location") != null && !"".equals(resultSet.getString("Location"))) {
                    Location = resultSet.getString("Location");
                }

                String Practice = "-";
                if (resultSet.getString("Practice") != null && !"".equals(resultSet.getString("Practice"))) {
                    Practice = resultSet.getString("Practice");
                }

                String StartDate = "-";
                if (resultSet.getString("StartDate") != null && !"".equals(resultSet.getString("StartDate"))) {
                    StartDate = resultSet.getString("StartDate");
                }

                String Duration = "-";
                if (resultSet.getString("Duration") != null && !"".equals(resultSet.getString("Duration"))) {
                    Duration = resultSet.getString("Duration");
                }

                String TargetRate = "-";
                if (resultSet.getString("TargetRate") != null && !"".equals(resultSet.getString("TargetRate"))) {
                    TargetRate = resultSet.getString("TargetRate");
                }

                String TaxTerm = "-";
                if (resultSet.getString("TaxTerm") != null && !"".equals(resultSet.getString("TaxTerm").trim()) && !"-1".equals(resultSet.getString("TaxTerm").trim())) {
                    // System.out.println("resultSet.getString" + resultSet.getString("TaxTerm"));
                    TaxTerm = resultSet.getString("TaxTerm");
                }
                String AssignedDate = "-";
                if (resultSet.getString("AssignedDate") != null && !"".equals(resultSet.getString("AssignedDate"))) {
                    AssignedDate = resultSet.getString("AssignedDate");
                }


                String ConsultantsCount = "-";
                if (resultSet.getString("ConsultantsCount") != null && !"".equals(resultSet.getString("ConsultantsCount"))) {
                    ConsultantsCount = resultSet.getString("ConsultantsCount");
                }
                String ACount = "-";
                if (resultSet.getString("ACount") != null && !"".equals(resultSet.getString("ACount"))) {
                    ACount = resultSet.getString("ACount");
                }
                String TSPCount = "-";
                if (resultSet.getString("TSPCount") != null && !"".equals(resultSet.getString("TSPCount"))) {
                    TSPCount = resultSet.getString("TSPCount");
                }
                String TSSCount = "-";
                if (resultSet.getString("TSSCount") != null && !"".equals(resultSet.getString("TSSCount"))) {
                    TSSCount = resultSet.getString("TSSCount");
                }
                String TSRCount = "-";
                if (resultSet.getString("TSRCount") != null && !"".equals(resultSet.getString("TSRCount"))) {
                    TSRCount = resultSet.getString("TSRCount");
                }
                String CSCount = "-";
                if (resultSet.getString("CSCount") != null && !"".equals(resultSet.getString("CSCount"))) {
                    CSCount = resultSet.getString("CSCount");
                }
                String CICount = "-";
                if (resultSet.getString("CICount") != null && !"".equals(resultSet.getString("CICount"))) {
                    CICount = resultSet.getString("CICount");
                }
                String CRCount = "-";
                if (resultSet.getString("CRCount") != null && !"".equals(resultSet.getString("CRCount"))) {
                    CRCount = resultSet.getString("CRCount");
                }
                String JCount = "-";
                if (resultSet.getString("JCount") != null && !"".equals(resultSet.getString("JCount"))) {
                    JCount = resultSet.getString("JCount");
                }
                String CIRCount = "-";
                if (resultSet.getString("CIRCount") != null && !"".equals(resultSet.getString("CIRCount"))) {
                    CIRCount = resultSet.getString("CIRCount");
                }
                String StatusCount = "-";
                if (resultSet.getString("StatusCount") != null && !"".equals(resultSet.getString("StatusCount"))) {
                    StatusCount = resultSet.getString("StatusCount");
                }


                String ReqLocation = "-";
                if (resultSet.getString("ReqLocation") != null && !"".equals(resultSet.getString("ReqLocation"))) {


                    if ("1".equals(resultSet.getString("ReqLocation"))) {
                        ReqLocation = "Onsite";
                    }
                    if ("2".equals(resultSet.getString("ReqLocation"))) {
                        ReqLocation = "Off Site";
                    }
                    if ("3".equals(resultSet.getString("ReqLocation"))) {
                        ReqLocation = "Off Shore";
                    }


                }


                i++;
                //  totalStream = totalStream + i + "#^$" + RequirementId + "#^$" + accountName + "#^$" + JobTitle + "#^$" + status + "#^$" + CreatedBy + "#^$" + Recruiter + "#^$" + PreSales + "#^$" + AccountId + "#^$" + Location + "#^$" + ReqLocation + "#^$" + Practice + "#^$" + StartDate + "#^$" + AssignedDate + "#^$" + Duration + "#^$" + TargetRate + "#^$" + TaxTerm + "#^$" + ConsultantsCount + "#^$" + StatusCount + "*@!";
                totalStream = totalStream + i + "#^$" + RequirementId + "#^$" + accountName + "#^$" + JobTitle + "#^$" + status + "#^$" + CreatedBy + "#^$" + Recruiter + "#^$" + PreSales + "#^$" + AccountId + "#^$" + Location + "#^$" + ReqLocation + "#^$" + Practice + "#^$" + StartDate + "#^$" + AssignedDate + "#^$" + Duration + "#^$" + TargetRate + "#^$" + TaxTerm + "#^$" + ConsultantsCount + "#^$" + ACount + "#^$" + TSPCount + "#^$" + TSSCount + "#^$" + TSRCount + "#^$" + CSCount + "#^$" + CICount + "#^$" + CRCount + "#^$" + JCount + "#^$" + CIRCount + "#^$" + StatusCount + "*@!";
            }
            stringBuffer.append(totalStream + "###" + totalRecords);

// System.out.println(i+"totalRecords...."+totalRecords);
        } catch (Exception sqe) {
            sqe.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (resultSet1 != null) {
                    resultSet1.close();
                    resultSet1 = null;
                }
                if (statement1 != null) {
                    statement1.close();
                    statement1 = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
            }
        }

        return stringBuffer.toString();
    }

    public String searchPreSalesMyRequirementList(HttpServletRequest httpServletRequest, NewAjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException {
        Connection connection = null;
        StringBuffer stringBuffer = new StringBuffer();
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String createdBy = "";
        String totalStream = "";
        String queryString = "";
        int i = 0;


        //resultType = "accessFailed";

        DateUtility dateUtility = new DateUtility();

        // System.out.println(" impl  --- createdBy----------"+ajaxHandlerAction.getCreatedBy()+"--assignedTo-------------"+ajaxHandlerAction.getAssignedTo()+"--title-------------"+ajaxHandlerAction.getTitle()+"--postedDate1-------"+ajaxHandlerAction.getPostedDate1()+"---postedDate2----"+ajaxHandlerAction.getPostedDate2()+"---status----"+ajaxHandlerAction.getStatus());


        try {
            /*queryString ="SELECT Id,ActivityType,Status,Description,DateDue,CreatedDate,AssignedToId,Comments FROM tblCrmActivity";
            queryString = queryString + " WHERE AccountId ="+accId+" AND ContactId =0 GROUP BY ActivityType,STATUS,Description,AssignedToId ORDER BY CreatedDate DESC";*/


            //   System.out.println("ajaxHandlerAction.getTitle()==="+ajaxHandlerAction.getTitle());


            int columnCounter = 0;

            String territory = "";
            StringBuffer queryStringBuffer = new StringBuffer();;


            queryStringBuffer.append("SELECT tblRecRequirement.Id,tblCrmAccount.NAME,JobTitle,tblRecRequirement.STATUS,CONCAT(`tblRecRequirement`.`State`,',',`tblRecRequirement`.`Country`) AS Location,tblRecRequirement.AssignedDate as AssignedDate,tblRecRequirement.NoResumes AS noofresumes,CONCAT(FName,' ',MName,'.',LName) AS CreatedBy,AssignedTo,tblCrmAccount.Id AS AccountId FROM tblRecRequirement LEFT OUTER JOIN tblCrmAccount ON (tblRecRequirement.CustomerId=tblCrmAccount.Id)  LEFT OUTER JOIN tblEmployee ON (tblRecRequirement.CreatedBy=tblEmployee.LoginId)  ");

            //    queryStringBuffer.append("WHERE tblRecRequirement.Country LIKE '"+userWorkCountry+"'  " );
            queryStringBuffer.append("WHERE 1=1  ");
            if (ajaxHandlerAction.getAssignedBy() != null && !"All".equals(ajaxHandlerAction.getAssignedBy())) {
                queryStringBuffer.append(" AND tblRecRequirement.AssignedBy='" + ajaxHandlerAction.getAssignedBy() + "' ");
            }

            if (ajaxHandlerAction.getAssignedTo() != null && !"All".equals(ajaxHandlerAction.getAssignedTo())) {

                queryStringBuffer.append(" AND tblRecRequirement.AssignedTo='" + ajaxHandlerAction.getAssignedTo() + "' ");
            }


            if (ajaxHandlerAction.getCreatedBy() != null && !"All".equals(ajaxHandlerAction.getCreatedBy())) {
                queryStringBuffer.append(" AND tblRecRequirement.CreatedBy='" + ajaxHandlerAction.getCreatedBy() + "' ");
            }
            if (ajaxHandlerAction.getTitle() != null && !"".equals(ajaxHandlerAction.getTitle())) {
                queryStringBuffer.append("`tblRecRequirement`.`JobTitle` LIKE '%" + ajaxHandlerAction.getTitle() + "%'");
            }

            if (!"".equalsIgnoreCase(ajaxHandlerAction.getPostedDate1())) {
                ajaxHandlerAction.setPostedDate1(DateUtility.getInstance().convertStringToMySQLDate(ajaxHandlerAction.getPostedDate1()));
            }

            if (!"".equalsIgnoreCase(ajaxHandlerAction.getPostedDate2())) {
                ajaxHandlerAction.setPostedDate2(DateUtility.getInstance().convertStringToMySQLDate(ajaxHandlerAction.getPostedDate2()));
            }
            if (ajaxHandlerAction.getPostedDate1() != null && !"".equals(ajaxHandlerAction.getPostedDate1())) {
                queryStringBuffer.append("DATE(`tblRecRequirement`.`DatePosted`) >= DATE('" + DateUtility.getInstance().convertStringToMySQLDate(ajaxHandlerAction.getPostedDate1()) + "')");
            }

            if (ajaxHandlerAction.getPostedDate2() != null && !"".equals(ajaxHandlerAction.getPostedDate2())) {
                queryStringBuffer.append("DATE(`tblRecRequirement`.`DatePosted`) <= DATE('" + DateUtility.getInstance().convertStringToMySQLDate(ajaxHandlerAction.getPostedDate2()) + "')");
            }

            if (!"All".equals(ajaxHandlerAction.getStatus())) {
                queryStringBuffer.append("AND `tblRecRequirement`.`Status` LIKE '" + ajaxHandlerAction.getStatus() + "'");
            }
            if (!"-1".equals(ajaxHandlerAction.getCountry())) {
                queryStringBuffer.append(" AND `tblRecRequirement`.`Country` like '" + ajaxHandlerAction.getCountry() + "' ");
            }

            if (ajaxHandlerAction.getState() != null && !"".equals(ajaxHandlerAction.getState())) {
                queryStringBuffer.append(" AND `tblRecRequirement`.`state` like '" + ajaxHandlerAction.getState() + "' ");
            }

            if (!"-1".equals(ajaxHandlerAction.getPracticeid())) {
                queryStringBuffer.append("  AND tblRecRequirement.Practice like '" + ajaxHandlerAction.getPracticeid() + "' ");
            }
            if (ajaxHandlerAction.getRequirementId() != 0) {
                queryStringBuffer.append("  AND tblRecRequirement .Id =" + ajaxHandlerAction.getRequirementId() + " ");
            }

            queryStringBuffer.append(" AND (tblRecRequirement.AssignToTechLead ='" + httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME) + "' || tblRecRequirement.SecondaryTechLead ='" + httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME) + "') ");

            queryStringBuffer.append(" ORDER BY `tblRecRequirement`.`DatePosted` DESC Limit 100");


            //  System.out.println("REQ_SEARCH_QUERY --->"+queryStringBuffer.toString());
                     /*
             *
             *End of where 
             */


            //System.out.println("Search Query ---"+queryStringBuffer.toString());

            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryStringBuffer.toString());

            //System.err.println("Account Activities:"+queryString);
            while (resultSet.next()) {
//tblRecRequirement.Id,tblCrmAccount.NAME,JobTitle,tblRecRequirement.STATUS,tblRecRequirement.CreatedBy,AssignedTo,AssignToTechLead

                int RequirementId = resultSet.getInt("Id");
                String accountName = resultSet.getString("NAME");
                String JobTitle = resultSet.getString("JobTitle");
                String status = resultSet.getString("STATUS");
                String Location = resultSet.getString("Location");
                String AssignedDate = resultSet.getString("AssignedDate");
                String noOfPos = resultSet.getString("noofresumes");
                String CreatedBy = resultSet.getString("CreatedBy");

                String AccountId = resultSet.getString("AccountId");
                //AccountId     

                String Recruiter = "-";
                if (resultSet.getString("AssignedTo") != null || resultSet.getString("AssignedTo") != "") {
                    Recruiter = resultSet.getString("AssignedTo");
                }









//#^$
                //*@!              

                i++;
                /*createdBy=resultSet.getString("CreatedById");
                count =resultSet.getInt("total");*/
                //totalStream=totalStream+i+"|"+createdDate+"|"+actType+"|"+description+"|"+comments+"|"+assignedToId+"|"+status+"|"+datedue+"|"+contactId+"|"+accountId+"|"+"^";
                //totalStream=totalStream+i+"|"+createdDate+"|"+actType+"|"+description+"|"+comments+"|"+assignedToId+"|"+status+"|"+datedue+"|"+"^";
                totalStream = totalStream + i + "#^$" + RequirementId + "#^$" + accountName + "#^$" + JobTitle + "#^$" + status + "#^$" + Location + "#^$" + AssignedDate + "#^$" + noOfPos + "#^$" + CreatedBy + "#^$" + Recruiter + "#^$" + AccountId + "*@!";
                //  totalActivities=totalActivities+count;
            }
            stringBuffer.append(totalStream);
            stringBuffer.append("addto");

            stringBuffer.append(i);

        } catch (Exception sqe) {
            sqe.printStackTrace();
        } finally {
            try {
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
            } catch (SQLException sqle) {
            }
        }
        // System.err.println("response string is"+stringBuffer.toString());
        return stringBuffer.toString();
    }

    public String doPopulateAccountDetails(int accId) throws ServiceLocatorException {
        StringBuffer reportsToBuffer = new StringBuffer("");
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();


            String query = "Select Revenues,NoOfEmployees from tblCrmAccount where Id=" + accId;

            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {

                reportsToBuffer.append(resultSet.getString("Revenues") + "@#" + resultSet.getString("NoOfEmployees"));

            }



        } catch (SQLException sle) {
            sle.printStackTrace();
        } catch (ServiceLocatorException sle) {
            sle.printStackTrace();
        } finally {
            try {
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
                ex.printStackTrace();
            }
        }
        //  System.out.println("Team: "+reportsToBuffer.toString());
        return reportsToBuffer.toString();
    }

    public String popupContactsWindow(String empId) throws ServiceLocatorException {

        String phoneNo = null;
        String emailaddr = null;
        String empDetails = null;
        String reportsTo = null;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            //SELECT Comments FROM tblEmpIssues WHERE Id=810
            preparedStatement = connection.prepareStatement("SELECT Email1,WorkPhoneNo,ReportsTo FROM tblEmployee WHERE Id =?");
            preparedStatement.setInt(1, Integer.parseInt(empId));
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                emailaddr = resultSet.getString("Email1");
                phoneNo = resultSet.getString("WorkPhoneNo");
                reportsTo = resultSet.getString("ReportsTo");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ServiceLocatorException sle) {
            sle.printStackTrace();
        } finally {
            try {

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
                ex.printStackTrace();
            }
        }

        // System.out.println("reportsTo"+reportsTo);
        empDetails = emailaddr + "^" + phoneNo + "^" + reportsTo;
        //  System.out.println("empDetails"+empDetails);
        return empDetails;
    }

    public String getEmployeeProjectDetailsBasedOnStatus(String projectId, int accountId, String empType, int empId, HttpServletRequest httpServletRequest) throws ServiceLocatorException {
        //String qsTitle = "";
        StringBuffer stringBuffer = new StringBuffer();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String query = "";
        DateUtility dateUtil;
        dateUtil = DateUtility.getInstance();
        String response = "";
        String actStartDate = "";
        String actEndDate = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            query = "SELECT Id,ObjectType,Email,Billable,StartDate,EndDate,STATUS,ResourceType,ResourceTitle,Utilization,RateType,Rate,ReportsTo,SecondReportTo,Comments,SkillSet FROM tblProjectContacts WHERE ObjectId='" + empId + "' AND ProjectId='" + projectId + "' AND AccountId='" + accountId + "' AND STATUS='Active'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {


                if (resultSet.getString("StartDate") != null) {
                    String startDate = dateUtil.sqlTimeStampTousTimeStamp(resultSet.getString("StartDate"));
                    StringTokenizer st = new StringTokenizer(startDate, " ");
                    actStartDate = st.nextToken();
                }
                if (resultSet.getString("EndDate") != null) {
                    String endDate = dateUtil.sqlTimeStampTousTimeStamp(resultSet.getString("EndDate"));
                    StringTokenizer st = new StringTokenizer(endDate, " ");
                    actEndDate = st.nextToken();
                }
                response = response + resultSet.getInt("Id") + "#^$" + resultSet.getString("ObjectType") + "#^$" + resultSet.getString("Email") + "#^$" + resultSet.getBoolean("Billable") + "#^$" + actStartDate + "#^$" + actEndDate + "#^$" + resultSet.getString("STATUS") + "#^$" + resultSet.getBoolean("ResourceType") + "#^$" + resultSet.getBoolean("ResourceTitle") + "#^$" + resultSet.getInt("Utilization") + "#^$" + resultSet.getString("RateType") + "#^$" + resultSet.getString("Rate") + "#^$" + resultSet.getInt("ReportsTo") + "#^$" + resultSet.getInt("SecondReportTo") + "#^$" + resultSet.getString("Comments") + "#^$" + resultSet.getString("SkillSet");
            }
            if ("".equals(response)) {

                response = "StatusNotActive";
            }

        } catch (Exception sqe) {
            sqe.printStackTrace();
        } finally {
            try {
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
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }



        return response;
    }

    public String getProjectStatusById(String projectId) throws ServiceLocatorException {



        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        String response = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            //SELECT Comments FROM tblEmpIssues WHERE Id=810
            preparedStatement = connection.prepareStatement("Select Status,ProjectStartDate,ProjectEndDate from tblProjects where Id=?");
            preparedStatement.setInt(1, Integer.parseInt(projectId));
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String startDate = "-", endDate = "-";
                if (resultSet.getString("ProjectStartDate") != null) {
                    // startDate = resultSet.getString("ProjectStartDate");
                    startDate = DateUtility.getInstance().convertDateToView(resultSet.getDate("ProjectStartDate"));
                }

                if (resultSet.getString("ProjectEndDate") != null) {
                    endDate = DateUtility.getInstance().convertDateToView(resultSet.getDate("ProjectEndDate"));
                }

                response = resultSet.getString("Status") + "#^$" + startDate + "#^$" + endDate;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ServiceLocatorException sle) {
            sle.printStackTrace();
        } finally {
            try {

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
                ex.printStackTrace();
            }
        }


        return response;
    }

    public String getProjectProtfolioReport(String customerName, String startDate, String orderBy, String status, HttpServletRequest httpServletRequest) {

        // System.out.println("getProjectProtfolioReport() in NewAjaxHandlerServiceImpl");    
        Connection connection = null;
        StringBuffer stringBuffer = new StringBuffer();
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        DataSourceDataProvider dataSourceDataProvider = null;

        String responseString = "";

        int i = 0;
        //System.err.println(days+"Diff in Dyas...");
        try {
            String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();

            //  
            // System.out.println("customerName---->"+customerName);
            //   System.out.println("startDate---->"+startDate);
            //   System.out.println("orderBy---->"+orderBy);
            //   System.out.println("status---->"+status);
            StringBuffer queryStringBuffer = new StringBuffer();;
            //queryString = "SELECT Id,CONCAT(FName,'.',LName) AS employeeName,ReportsTo, TitleTypeId,Email1,WorkPhoneNo FROM tblEmployee WHERE CurStatus='Active' AND LoginId IN (" + TeamLoginIdList + ") ";


            queryStringBuffer.append("SELECT tblProjects.Id,tblCrmAccount.NAME,tblProjects.ProjectName,tblProjects.CostModel,tblProjects.Sector,tblProjects.Practice,tblProjects.ProjectType,tblProjects.ProjectStartDate,tblProjects.ProjectEndDate,tblProjects.State,tblProjects.Software,tblProjects.ProjectDescription,tblProjects.Comments FROM tblCrmAccount LEFT OUTER JOIN tblProjects ON (tblCrmAccount.Id=tblProjects.CustomerId)");
            // queryString = queryString + " AND Country like '" + country + "' ORDER BY TRIM(ReportsTo),TRIM(employeeName) LIMIT 250";
            queryStringBuffer.append("WHERE 1=1  ");
            if (!"All".equals(customerName)) {
                queryStringBuffer.append(" AND tblCrmAccount.Id='" + customerName + "' ");
            }
            if (status != null && !"".equals(status)) {
                queryStringBuffer.append(" AND tblProjects.Status='" + status + "' ");
            }
            if (startDate != null && !"".equals(startDate)) {
                queryStringBuffer.append(" AND DATE(`tblProjects`.`ProjectStartDate`) >= DATE('" + DateUtility.getInstance().convertStringToMySQLDate(startDate) + "')");
            }
//queryStringBuffer.append("ORDER BY `tblCrmAccount`.`Name` DESC LIMIT 100");
            if (orderBy.equals("CustomerName")) {
                queryStringBuffer.append("ORDER BY `tblCrmAccount`.`NAME`  LIMIT 100");
            }
            if (orderBy.equals("ProjectName")) {
                queryStringBuffer.append("ORDER BY `tblProjects`.`ProjectName`  LIMIT 100");
            }
            if (orderBy.equals("CostModel")) {
                queryStringBuffer.append("ORDER BY  `tblProjects`.`CostModel`  LIMIT 100");
            }
            if (orderBy.equals("Sector")) {
                queryStringBuffer.append("ORDER BY `tblProjects`.`Sector`  LIMIT 100");
            }
            if (orderBy.equals("Practice")) {
                queryStringBuffer.append("ORDER BY `tblProjects`.`Practice`  LIMIT 100");
            }
            if (orderBy.equals("ProjectType")) {
                queryStringBuffer.append("ORDER BY `tblProjects`.`ProjectType`  LIMIT 100");
            }
            if (orderBy.equals("StartDate")) {
                queryStringBuffer.append("ORDER BY `tblProjects`.`ProjectStartDate`  LIMIT 100");
            }
            if (orderBy.equals("EndDate")) {
                queryStringBuffer.append("ORDER BY `tblProjects`.`ProjectEndDate`  LIMIT 100");
            }
            if (orderBy.equals("OverallState")) {
                queryStringBuffer.append("ORDER BY `tblProjects`.`State`  LIMIT 100");
            }
            if (orderBy.equals("Software")) {
                queryStringBuffer.append("ORDER BY `tblProjects`.`Software`  LIMIT 100");
            }
            if (orderBy.equals("Description")) {
                queryStringBuffer.append("ORDER BY `tblProjects`.`ProjectDescription`  LIMIT 100");
            }
            if (orderBy.equals("Comments")) {
                queryStringBuffer.append("ORDER BY `tblProjects`.`Comments`  LIMIT 100");
            }
            //   System.out.println("query is---->"+queryStringBuffer.toString());
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryStringBuffer.toString());

            int count = 0;
            while (resultSet.next()) {
                count++;
                responseString = responseString + resultSet.getString("Id") + "#^$";
                if (resultSet.getString("NAME") != null && !"".equals(resultSet.getString("NAME"))) {
                    responseString = responseString + resultSet.getString("NAME") + "#^$";

                } else {
                    responseString = responseString + "-" + "#^$";
                }
                if (resultSet.getString("ProjectName") != null && !"".equals(resultSet.getString("ProjectName"))) {
                    responseString = responseString + resultSet.getString("ProjectName") + "#^$";

                } else {
                    responseString = responseString + "-" + "#^$";
                }

                if (resultSet.getString("CostModel") != null && !"".equals(resultSet.getString("CostModel")) && !"-1".equals(resultSet.getString("CostModel"))) {
                    responseString = responseString + resultSet.getString("CostModel") + "#^$";

                } else {
                    responseString = responseString + "-" + "#^$";
                }
                if (resultSet.getString("Sector") != null && !"".equals(resultSet.getString("Sector")) && !"-1".equals(resultSet.getString("Sector"))) {
                    responseString = responseString + resultSet.getString("Sector") + "#^$";

                } else {
                    responseString = responseString + "-" + "#^$";
                }
                if (resultSet.getString("Practice") != null && !"".equals(resultSet.getString("Practice"))) {
                    responseString = responseString + resultSet.getString("Practice") + "#^$";

                } else {
                    responseString = responseString + "-" + "#^$";
                }
                if (resultSet.getString("ProjectType") != null && !"".equals(resultSet.getString("ProjectType"))) {
                    responseString = responseString + resultSet.getString("ProjectType") + "#^$";

                } else {
                    responseString = responseString + "-" + "#^$";
                }
                if (resultSet.getString("ProjectStartDate") != null && !"".equals(resultSet.getString("ProjectStartDate"))) {
                    responseString = responseString + resultSet.getString("ProjectStartDate") + "#^$";

                } else {
                    responseString = responseString + "-" + "#^$";
                }
                if (resultSet.getString("ProjectEndDate") != null && !"".equals(resultSet.getString("ProjectEndDate"))) {
                    responseString = responseString + resultSet.getString("ProjectEndDate") + "#^$";

                } else {
                    responseString = responseString + "-" + "#^$";
                }
                if (resultSet.getString("State") != null && !"".equals(resultSet.getString("State"))) {
                    responseString = responseString + resultSet.getString("State") + "#^$";

                } else {
                    responseString = responseString + "-" + "#^$";
                }
                if (resultSet.getString("Software") != null && !"".equals(resultSet.getString("Software"))) {
                    responseString = responseString + resultSet.getString("Software") + "#^$";

                } else {
                    responseString = responseString + "-" + "#^$";
                }
                if (resultSet.getString("ProjectDescription") != null && !"".equals(resultSet.getString("ProjectDescription"))) {
                    responseString = responseString + resultSet.getString("ProjectDescription") + "#^$";

                } else {
                    responseString = responseString + "-" + "#^$";
                }
                if (resultSet.getString("Comments") != null && !"".equals(resultSet.getString("Comments"))) {
                    responseString = responseString + resultSet.getString("Comments") + "#^$";

                } else {
                    responseString = responseString + "-" + "#^$";
                }
//                responseString = responseString + resultSet.getString("Email1") + "#^$";
//                responseString = responseString + "view" + "#^$";
//                if (resultSet.getString("WorkPhoneNo") != null && !"".equals(resultSet.getString("WorkPhoneNo"))) {
//                    responseString = responseString + resultSet.getString("WorkPhoneNo");
//                } else {
//                    responseString = responseString + "-";
//                }
                responseString = responseString + "*@!";
            }
            // System.out.println("response is: " + responseString);
        } catch (Exception sqe) {
            sqe.printStackTrace();
        } finally {
            try {


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

            } catch (SQLException sqle) {
            }
        }
        return responseString;


    }

    public String getProjectDescriptionDetails(int prjId) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String totalStream = "";
        int i = 0;
        try {
            String query = "SELECT tblProjects.ProjectDescription FROM tblProjects  WHERE tblProjects.Id=  ?";

            //  System.out.println("query-->"+query);
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, prjId);
            resultSet = preparedStatement.executeQuery();

            String PrjDescription = "-";

            while (resultSet.next()) {

//                  if(resultSet.getString("NoOfResumesSubmitted")!=null)
//                    NoOfResumesSubmitted = resultSet.getString("NoOfResumesSubmitted");
                if (resultSet.getString("ProjectDescription") != null) {
                    PrjDescription = resultSet.getString("ProjectDescription");
                    i++;
                }

            }

            totalStream = PrjDescription;
        } catch (Exception e) {
            System.err.println("Exception is-->" + e.getMessage());
        } finally {
            try {
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
            } catch (Exception sqle) {
                System.err.println("SQL Exception is-->" + sqle.getMessage());
            }
        }
        return totalStream;
    }

    public String getProjectCommentDetails(int prjId) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String totalStream = "";
        int i = 0;
        try {
            String query = "SELECT Comments FROM tblProjects  WHERE Id=  ?";

            //System.out.println("query-->"+query);
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, prjId);
            resultSet = preparedStatement.executeQuery();

            String PrjComments = "-";

            while (resultSet.next()) {

//                  if(resultSet.getString("NoOfResumesSubmitted")!=null)
//                    NoOfResumesSubmitted = resultSet.getString("NoOfResumesSubmitted");
                if (resultSet.getString("Comments") != null) {
                    PrjComments = resultSet.getString("Comments");
                    i++;
                }

            }

            totalStream = PrjComments;
        } catch (Exception e) {
            System.err.println("Exception is-->" + e.getMessage());
        } finally {
            try {
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
            } catch (Exception sqle) {
                System.err.println("SQL Exception is-->" + sqle.getMessage());
            }
        }
        return totalStream;
    }

    public String getPSCERDetailsForPresales(HttpServletRequest httpServletRequest, NewAjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException {
        StringBuffer stringBuffer = new StringBuffer();
        String totalStream = "";
        Statement statement = null;
        Statement statement1 = null;
        Connection connection = null;
        ResultSet resultSet = null;
        ResultSet resultSet1 = null;
        PreparedStatement preparedStatement = null;
        int totalRecords = 0;

        String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
        Map rolesMap = (Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_ROLES);
        connection = ConnectionProvider.getInstance().getConnection();

        httpServletRequest.getSession(false).setAttribute("tempRequestType", ajaxHandlerAction.getRequestType());
        httpServletRequest.getSession(false).setAttribute("tempStartDate", ajaxHandlerAction.getStartDate());
        httpServletRequest.getSession(false).setAttribute("tempEndDate", ajaxHandlerAction.getEndDate());
        httpServletRequest.getSession(false).setAttribute("temPgNo", ajaxHandlerAction.getPgNo());

        //    query = "SELECT tblProjects.ProjectName,tblPmoAuthors.AccountId,tblProjects.ProjectStartDate,tblProjects.STATUS, tblCrmAccount.NAME,tblPmoAuthors.ProjectId FROM tblPmoAuthors JOIN tblProjects ON (tblProjects.Id=tblPmoAuthors.ProjectId) JOIN tblCrmAccount ON (tblCrmAccount.Id = tblPmoAuthors.AccountId) WHERE tblPmoAuthors.AuthorId = '" + ajaxHandlerAction.getLoginId() + "'  AND tblPmoAuthors.STATUS =  'Active' ";
        StringBuffer queryStringBuffer = new StringBuffer("SELECT tblPSCER.Id AS Id,AccountName AS CustomerName,tblPSCER.Stage AS State,IF(MeetingType = '' ,'N/A',MeetingType ) AS MeetingType,IF(MeetingDate IS NULL ,'N/A',DATE_FORMAT(MeetingDate,'%m/%d/%Y') ) AS MeetingDate,tblPSCER.Comments,DATE_FORMAT(tblPSCER.CreatedDate,'%m/%d/%Y') AS CreatedDate,tblPSCER.RequestorId,tblPSCER.RequestType,tblPSCER.ResourceIds,CONCAT(FName,' ',MName,'.',LName) AS EmpName FROM tblPSCER LEFT OUTER JOIN tblCrmAccount ON tblPSCER.AccountId = tblCrmAccount.Id LEFT OUTER JOIN tblEmployee ON tblPSCER.RequestorId=tblEmployee.LoginId WHERE RecordType=1  ");
        if (!rolesMap.containsValue("Admin")) {
            queryStringBuffer.append(" AND FIND_IN_SET(ResourceIds,'" + loginId + "') ");
        }

        if (ajaxHandlerAction.getStartDate() != null && !"".equals(ajaxHandlerAction.getStartDate())) {
            queryStringBuffer.append(" AND DATE(tblPSCER.CreatedDate)>=DATE('" + DateUtility.getInstance().convertStringToMySQLDate(ajaxHandlerAction.getStartDate()) + "') ");
        }

        if (ajaxHandlerAction.getEndDate() != null && !"".equals(ajaxHandlerAction.getEndDate())) {
            queryStringBuffer.append(" AND DATE(tblPSCER.CreatedDate)<=DATE('" + DateUtility.getInstance().convertStringToMySQLDate(ajaxHandlerAction.getEndDate()) + "') ");
        }
        if (!"".equals(ajaxHandlerAction.getRequestType()) && ajaxHandlerAction.getRequestType() != null) {
            queryStringBuffer.append(" AND  RequestType='" + ajaxHandlerAction.getRequestType() + "'");

        }
        queryStringBuffer.append(" ORDER BY tblPSCER.CreatedDate DESC  LIMIT " + (ajaxHandlerAction.getPgNo() - 1) * 10 + ", 10");


        // System.out.println("queryStringBuffer..."+queryStringBuffer.toString());
        try {

            int index = (ajaxHandlerAction.getPgNo() - 1) * 10;

            if ("1".equals(ajaxHandlerAction.getPgFlag().trim())) {
                StringBuffer queryStringBufferCount = new StringBuffer("SELECT COUNT(tblPSCER.Id) AS count FROM tblPSCER LEFT OUTER JOIN tblCrmAccount ON tblPSCER.AccountId = tblCrmAccount.Id  WHERE  RecordType=1 ");
                if (!rolesMap.containsValue("Admin")) {
                    queryStringBufferCount.append(" AND FIND_IN_SET(ResourceIds,'" + loginId + "') ");
                }

                if (ajaxHandlerAction.getStartDate() != null && !"".equals(ajaxHandlerAction.getStartDate())) {
                    queryStringBufferCount.append(" AND DATE(CreatedDate)>=DATE('" + DateUtility.getInstance().convertStringToMySQLDate(ajaxHandlerAction.getStartDate()) + "') ");
                }

                if (ajaxHandlerAction.getEndDate() != null && !"".equals(ajaxHandlerAction.getEndDate())) {
                    queryStringBufferCount.append(" AND DATE(CreatedDate)<=DATE('" + DateUtility.getInstance().convertStringToMySQLDate(ajaxHandlerAction.getEndDate()) + "') ");
                }
                if (!"".equals(ajaxHandlerAction.getRequestType()) && ajaxHandlerAction.getRequestType() != null) {
                    queryStringBufferCount.append(" AND  RequestType='" + ajaxHandlerAction.getRequestType() + "'");

                }

                statement1 = connection.createStatement();
                resultSet1 = statement1.executeQuery(queryStringBufferCount.toString());
                while (resultSet1.next()) {
                    totalRecords = resultSet1.getInt("count");
                }
            }

            // System.out.println("Query -->"+query);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryStringBuffer.toString());

            String QUERY_STRING = "SELECT CONCAT(FName,'',MName,'.',LName) AS NAME FROM tblEmployee WHERE FIND_IN_SET(LoginId,?) ";
            preparedStatement = connection.prepareStatement(QUERY_STRING);

            while (resultSet.next()) {
                int Id = resultSet.getInt("Id");

                String CustomerName = resultSet.getString("CustomerName");

                String State = resultSet.getString("State");

                String MeetingType = resultSet.getString("MeetingType");

                index = index + 1;
                String MeetingDate = resultSet.getString("MeetingDate");
                String Comments = resultSet.getString("Comments");
                String CreatedDate = resultSet.getString("CreatedDate");
                String EmpName = resultSet.getString("EmpName");
                String RequestType = resultSet.getString("RequestType");
                String ResourceIds = resultSet.getString("ResourceIds");

                String ResourceIdsNames = com.mss.mirage.util.DataSourceDataProvider.getInstance().getEmployeeNamesByLoginIdList(preparedStatement, ResourceIds);
                // totalStream = totalStream + "#^$" + Id + "#^$" + CustomerName + "#^$" + State + "#^$" + MeetingType + "#^$" + MeetingDate + "#^$" + Comments + "#^$" + CreatedDate + "#^$" + EmpName +"#^$" + RequestType + "#^$" + ResourceIdsNames + "*@!";
                totalStream = totalStream + "#^$" + Id + "#^$" + CustomerName + "#^$" + State + "#^$" + MeetingType + "#^$" + MeetingDate + "#^$" + Comments + "#^$" + CreatedDate + "#^$" + EmpName + "#^$" + RequestType + "#^$" + ResourceIdsNames + "#^$" + index + "*@!";
            }

            stringBuffer.append(totalStream + "###" + totalRecords);
            // System.out.println("stringBuffer.toString()...."+stringBuffer.toString());

        } catch (Exception sqe) {
            sqe.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (resultSet1 != null) {
                    resultSet1.close();
                    resultSet1 = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (statement1 != null) {
                    statement1.close();
                    statement1 = null;
                }

                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
            }
        }
        return stringBuffer.toString();
    }

    public String getRequestInSightDetails(int leadId) throws ServiceLocatorException {



        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        String response = "-";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            //SELECT Comments FROM tblEmpIssues WHERE Id=810
            preparedStatement = connection.prepareStatement("Select InSightDetails from tblPSCER WHERE RequestId=? AND RecordType=1");
            preparedStatement.setInt(1, leadId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {

                if (resultSet.getString("InSightDetails") != null) {
                    // startDate = resultSet.getString("ProjectStartDate");
                    response = resultSet.getString("InSightDetails");
                }


            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ServiceLocatorException sle) {
            sle.printStackTrace();
        } finally {
            try {

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
                ex.printStackTrace();
            }
        }


        return response;
    }

    public String getPSCERDetailsForEmployee(HttpServletRequest httpServletRequest, NewAjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException {

        StringBuffer stringBuffer = new StringBuffer();
        String totalStream = "";
        Statement statement = null;
        Statement statement1 = null;
        Connection connection = null;
        ResultSet resultSet = null;
        ResultSet resultSet1 = null;
        PreparedStatement preparedStatement = null;
        int index = (ajaxHandlerAction.getPgNo() - 1) * 10;
        int totalRecords = 0;

        String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
        connection = ConnectionProvider.getInstance().getConnection();

        httpServletRequest.getSession(false).setAttribute("tempRequestType", ajaxHandlerAction.getRequestType());
        httpServletRequest.getSession(false).setAttribute("tempStartDate", ajaxHandlerAction.getStartDate());
        httpServletRequest.getSession(false).setAttribute("tempEndDate", ajaxHandlerAction.getEndDate());
        httpServletRequest.getSession(false).setAttribute("temPgNo", ajaxHandlerAction.getPgNo());
        httpServletRequest.getSession(false).setAttribute("tempState", ajaxHandlerAction.getState());


        //    query = "SELECT tblProjects.ProjectName,tblPmoAuthors.AccountId,tblProjects.ProjectStartDate,tblProjects.STATUS, tblCrmAccount.NAME,tblPmoAuthors.ProjectId FROM tblPmoAuthors JOIN tblProjects ON (tblProjects.Id=tblPmoAuthors.ProjectId) JOIN tblCrmAccount ON (tblCrmAccount.Id = tblPmoAuthors.AccountId) WHERE tblPmoAuthors.AuthorId = '" + ajaxHandlerAction.getLoginId() + "'  AND tblPmoAuthors.STATUS =  'Active' ";
        StringBuffer queryStringBuffer = new StringBuffer("SELECT tblPSCER.Id AS Id,AccountName AS CustomerName,tblPSCER.Stage AS State,IF(MeetingType = '' ,'N/A',MeetingType ) AS MeetingType,IF(MeetingDate IS NULL ,'N/A',DATE_FORMAT(MeetingDate,'%m/%d/%Y') ) AS MeetingDate,tblPSCER.Comments,DATE_FORMAT(tblPSCER.CreatedDate,'%m/%d/%Y') AS CreatedDate,tblPSCER.RequestorId,tblPSCER.RequestType,tblPSCER.ResourceIds,CONCAT(FName,' ',MName,'.',LName) AS EmpName FROM tblPSCER LEFT OUTER JOIN tblCrmAccount ON tblPSCER.AccountId = tblCrmAccount.Id LEFT OUTER JOIN tblEmployee ON tblPSCER.RequestorId=tblEmployee.LoginId WHERE Stage NOT IN('Creation') AND RecordType=1 ");


        if (ajaxHandlerAction.getStartDate() != null && !"".equals(ajaxHandlerAction.getStartDate())) {
            queryStringBuffer.append(" AND DATE(tblPSCER.CreatedDate)>=DATE('" + DateUtility.getInstance().convertStringToMySQLDate(ajaxHandlerAction.getStartDate()) + "') ");
        }

        if (ajaxHandlerAction.getEndDate() != null && !"".equals(ajaxHandlerAction.getEndDate())) {
            queryStringBuffer.append(" AND DATE(tblPSCER.CreatedDate)<=DATE('" + DateUtility.getInstance().convertStringToMySQLDate(ajaxHandlerAction.getEndDate()) + "') ");
        }

        if (!"".equals(ajaxHandlerAction.getState()) && ajaxHandlerAction.getState() != null) {
            queryStringBuffer.append(" AND  Stage ='" + ajaxHandlerAction.getState() + "' ");

        }

        if (!"".equals(ajaxHandlerAction.getRequestType()) && ajaxHandlerAction.getRequestType() != null) {
            queryStringBuffer.append(" AND  RequestType='" + ajaxHandlerAction.getRequestType() + "'");

        }
        queryStringBuffer.append(" ORDER BY tblPSCER.CreatedDate DESC  LIMIT " + (ajaxHandlerAction.getPgNo() - 1) * 10 + ", 10");


        try {



            if ("1".equals(ajaxHandlerAction.getPgFlag().trim())) {
                StringBuffer queryStringBufferCount = new StringBuffer("SELECT COUNT(tblPSCER.Id) AS count FROM tblPSCER LEFT OUTER JOIN tblCrmAccount ON tblPSCER.AccountId = tblCrmAccount.Id  WHERE Stage NOT IN('Creation') AND RecordType=1 ");

                if (ajaxHandlerAction.getStartDate() != null && !"".equals(ajaxHandlerAction.getStartDate())) {
                    queryStringBufferCount.append(" AND DATE(CreatedDate)>=DATE('" + DateUtility.getInstance().convertStringToMySQLDate(ajaxHandlerAction.getStartDate()) + "') ");
                }

                if (ajaxHandlerAction.getEndDate() != null && !"".equals(ajaxHandlerAction.getEndDate())) {
                    queryStringBufferCount.append(" AND DATE(CreatedDate)<=DATE('" + DateUtility.getInstance().convertStringToMySQLDate(ajaxHandlerAction.getEndDate()) + "') ");
                }

                if (!"".equals(ajaxHandlerAction.getState()) && ajaxHandlerAction.getState() != null) {
                    queryStringBufferCount.append(" AND  Stage ='" + ajaxHandlerAction.getState() + "'");

                }

                if (!"".equals(ajaxHandlerAction.getRequestType()) && ajaxHandlerAction.getRequestType() != null) {
                    queryStringBufferCount.append(" AND  RequestType='" + ajaxHandlerAction.getRequestType() + "'");

                }




                statement1 = connection.createStatement();
                resultSet1 = statement1.executeQuery(queryStringBufferCount.toString());
                while (resultSet1.next()) {
                    totalRecords = resultSet1.getInt("count");
                }
            }

            // System.out.println("Query -->"+query);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryStringBuffer.toString());

            String QUERY_STRING = "SELECT CONCAT(FName,'',MName,'.',LName) AS NAME FROM tblEmployee WHERE FIND_IN_SET(LoginId,?) ";
            preparedStatement = connection.prepareStatement(QUERY_STRING);

            while (resultSet.next()) {
                index = index + 1;
                int Id = resultSet.getInt("Id");

                String CustomerName = resultSet.getString("CustomerName");

                String State = resultSet.getString("State");

                String MeetingType = resultSet.getString("MeetingType");


                String MeetingDate = resultSet.getString("MeetingDate");
                String Comments = resultSet.getString("Comments");
                String CreatedDate = resultSet.getString("CreatedDate");
                String EmpName = resultSet.getString("EmpName");
                String RequestType = resultSet.getString("RequestType");
                String ResourceIds = resultSet.getString("ResourceIds");

                String ResourceIdsNames = com.mss.mirage.util.DataSourceDataProvider.getInstance().getEmployeeNamesByLoginIdList(preparedStatement, ResourceIds);
                totalStream = totalStream + "#^$" + Id + "#^$" + CustomerName + "#^$" + State + "#^$" + MeetingType + "#^$" + MeetingDate + "#^$" + Comments + "#^$" + CreatedDate + "#^$" + EmpName + "#^$" + RequestType + "#^$" + ResourceIdsNames + "#^$" + index + "*@!";
            }

            stringBuffer.append(totalStream + "###" + totalRecords);

        } catch (Exception sqe) {
            sqe.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (resultSet1 != null) {
                    resultSet1.close();
                    resultSet1 = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (statement1 != null) {
                    statement1.close();
                    statement1 = null;
                }

                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
            }
        }
        return stringBuffer.toString();
    }

    public String getPSCERDetailsForSales(HttpServletRequest httpServletRequest, NewAjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException {
        StringBuffer stringBuffer = new StringBuffer();
        String totalStream = "";
        Statement statement = null;
        Statement statement1 = null;
        Connection connection = null;
        ResultSet resultSet = null;
        ResultSet resultSet1 = null;
        PreparedStatement preparedStatement = null;
        int index = (ajaxHandlerAction.getPgNo() - 1) * 10;
        int totalRecords = 0;

        String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
        connection = ConnectionProvider.getInstance().getConnection();

        httpServletRequest.getSession(false).setAttribute("tempRequestType", ajaxHandlerAction.getRequestType());
        httpServletRequest.getSession(false).setAttribute("tempStartDate", ajaxHandlerAction.getStartDate());
        httpServletRequest.getSession(false).setAttribute("tempEndDate", ajaxHandlerAction.getEndDate());
        httpServletRequest.getSession(false).setAttribute("temPgNo", ajaxHandlerAction.getPgNo());
        httpServletRequest.getSession(false).setAttribute("tempState", ajaxHandlerAction.getState());


        //    query = "SELECT tblProjects.ProjectName,tblPmoAuthors.AccountId,tblProjects.ProjectStartDate,tblProjects.STATUS, tblCrmAccount.NAME,tblPmoAuthors.ProjectId FROM tblPmoAuthors JOIN tblProjects ON (tblProjects.Id=tblPmoAuthors.ProjectId) JOIN tblCrmAccount ON (tblCrmAccount.Id = tblPmoAuthors.AccountId) WHERE tblPmoAuthors.AuthorId = '" + ajaxHandlerAction.getLoginId() + "'  AND tblPmoAuthors.STATUS =  'Active' ";
        StringBuffer queryStringBuffer = new StringBuffer("SELECT tblPSCER.Id AS Id,AccountName AS CustomerName,tblPSCER.Stage AS State,IF(MeetingType = '' ,'N/A',MeetingType ) AS MeetingType,IF(MeetingDate IS NULL ,'N/A',DATE_FORMAT(MeetingDate,'%m/%d/%Y') ) AS MeetingDate,tblPSCER.Comments,DATE_FORMAT(tblPSCER.CreatedDate,'%m/%d/%Y') AS CreatedDate,tblPSCER.RequestorId,tblPSCER.RequestType,tblPSCER.ResourceIds,CONCAT(FName,' ',MName,'.',LName) AS EmpName FROM tblPSCER LEFT OUTER JOIN tblCrmAccount ON tblPSCER.AccountId = tblCrmAccount.Id LEFT OUTER JOIN tblEmployee ON tblPSCER.RequestorId=tblEmployee.LoginId WHERE tblPSCER.CreatedBy='" + httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString() + "' AND RecordType=1");


        if (ajaxHandlerAction.getStartDate() != null && !"".equals(ajaxHandlerAction.getStartDate())) {
            queryStringBuffer.append(" AND DATE(tblPSCER.CreatedDate)>=DATE('" + DateUtility.getInstance().convertStringToMySQLDate(ajaxHandlerAction.getStartDate()) + "') ");
        }

        if (ajaxHandlerAction.getEndDate() != null && !"".equals(ajaxHandlerAction.getEndDate())) {
            queryStringBuffer.append(" AND DATE(tblPSCER.CreatedDate)<=DATE('" + DateUtility.getInstance().convertStringToMySQLDate(ajaxHandlerAction.getEndDate()) + "') ");
        }

        if (!"".equals(ajaxHandlerAction.getState()) && ajaxHandlerAction.getState() != null) {
            queryStringBuffer.append(" AND  Stage ='" + ajaxHandlerAction.getState() + "' ");

        }

        if (!"".equals(ajaxHandlerAction.getRequestType()) && ajaxHandlerAction.getRequestType() != null) {
            queryStringBuffer.append(" AND  RequestType='" + ajaxHandlerAction.getRequestType() + "'");

        }
        queryStringBuffer.append(" ORDER BY tblPSCER.CreatedDate DESC  LIMIT " + (ajaxHandlerAction.getPgNo() - 1) * 10 + ", 10");


        try {



            if ("1".equals(ajaxHandlerAction.getPgFlag().trim())) {
                StringBuffer queryStringBufferCount = new StringBuffer("SELECT COUNT(tblPSCER.Id) AS count FROM tblPSCER LEFT OUTER JOIN tblCrmAccount ON tblPSCER.AccountId = tblCrmAccount.Id  WHERE tblPSCER.CreatedBy='" + httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString() + "' AND RecordType=1 ");

                if (ajaxHandlerAction.getStartDate() != null && !"".equals(ajaxHandlerAction.getStartDate())) {
                    queryStringBufferCount.append(" AND DATE(CreatedDate)>=DATE('" + DateUtility.getInstance().convertStringToMySQLDate(ajaxHandlerAction.getStartDate()) + "') ");
                }

                if (ajaxHandlerAction.getEndDate() != null && !"".equals(ajaxHandlerAction.getEndDate())) {
                    queryStringBufferCount.append(" AND DATE(CreatedDate)<=DATE('" + DateUtility.getInstance().convertStringToMySQLDate(ajaxHandlerAction.getEndDate()) + "') ");
                }

                if (!"".equals(ajaxHandlerAction.getState()) && ajaxHandlerAction.getState() != null) {
                    queryStringBufferCount.append(" AND  Stage ='" + ajaxHandlerAction.getState() + "'");

                }

                if (!"".equals(ajaxHandlerAction.getRequestType()) && ajaxHandlerAction.getRequestType() != null) {
                    queryStringBufferCount.append(" AND  RequestType='" + ajaxHandlerAction.getRequestType() + "'");

                }




                statement1 = connection.createStatement();
                resultSet1 = statement1.executeQuery(queryStringBufferCount.toString());
                while (resultSet1.next()) {
                    totalRecords = resultSet1.getInt("count");
                }
            }

            // System.out.println("Query -->"+query);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryStringBuffer.toString());

            String QUERY_STRING = "SELECT CONCAT(FName,'',MName,'.',LName) AS NAME FROM tblEmployee WHERE FIND_IN_SET(LoginId,?) ";
            preparedStatement = connection.prepareStatement(QUERY_STRING);

            while (resultSet.next()) {
                index = index + 1;
                int Id = resultSet.getInt("Id");

                String CustomerName = resultSet.getString("CustomerName");

                String State = resultSet.getString("State");

                String MeetingType = resultSet.getString("MeetingType");


                String MeetingDate = resultSet.getString("MeetingDate");
                String Comments = resultSet.getString("Comments");
                String CreatedDate = resultSet.getString("CreatedDate");
                String EmpName = resultSet.getString("EmpName");
                String RequestType = resultSet.getString("RequestType");
                String ResourceIds = resultSet.getString("ResourceIds");

                String ResourceIdsNames = com.mss.mirage.util.DataSourceDataProvider.getInstance().getEmployeeNamesByLoginIdList(preparedStatement, ResourceIds);
                totalStream = totalStream + "#^$" + Id + "#^$" + CustomerName + "#^$" + State + "#^$" + MeetingType + "#^$" + MeetingDate + "#^$" + Comments + "#^$" + CreatedDate + "#^$" + EmpName + "#^$" + RequestType + "#^$" + ResourceIdsNames + "#^$" + index + "*@!";
            }

            stringBuffer.append(totalStream + "###" + totalRecords);

        } catch (Exception sqe) {
            sqe.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (resultSet1 != null) {
                    resultSet1.close();
                    resultSet1 = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (statement1 != null) {
                    statement1.close();
                    statement1 = null;
                }

                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
            }
        }
        return stringBuffer.toString();
    }

    public String getDomainByExamType(int flag) {
        StringBuffer stringBuffer = new StringBuffer();
        String queryString = null;
        Connection connection = null;
        queryString = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean isGetting = false;
        String domainName = null;
        int count = 0;
        try {
            connection = ConnectionProvider.getInstance().getConnection();

            System.out.println("getPgNo values :::::::" + flag);
            if (flag == 1) {
                queryString = "SELECT Id,DomainName FROM tblEcertDomain WHERE status='Active' AND Id != " + Properties.getProperty("CRE.DomainId") + " ORDER BY DomainName";
            } else {
                queryString = "SELECT Id,DomainName FROM tblEcertDomain WHERE status='Active' AND Id = " + Properties.getProperty("CRE.DomainId") + " ORDER BY DomainName";
            }
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();


            //stringBuffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            stringBuffer.append("<xml version=\"1.0\">");
            stringBuffer.append("<TOPICS>");
            stringBuffer.append("<TOPIC TOPICID=\"-1\">");
            stringBuffer.append("--Please select--");
            stringBuffer.append("</TOPIC>");
            while (resultSet.next()) {
                domainName = resultSet.getString("DomainName");
                if (domainName != null && !"".equals(domainName)) {
                    stringBuffer.append("<TOPIC TOPICID=\"" + resultSet.getInt("Id") + "\">");

                    if (domainName.contains("&")) {
                        domainName = domainName.replaceAll("&", "&amp;");
                    }
                    stringBuffer.append(domainName);
                    stringBuffer.append("</TOPIC>");

                }
                isGetting = true;
                count++;
            }
            if (!isGetting) {
                //sb.append("<EMPLOYEES>" + sb.toString() + "</EMPLOYEES>");
                //} else {
                isGetting = false;
                //nothing to show
                //  response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                //stringBuffer.append("<TOPIC TOPICID=\" \"><VALID>false</VALID></TOPIC>");
            }
            stringBuffer.append("</TOPICS>");
            stringBuffer.append("</xml>");

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ServiceLocatorException sle) {
            sle.printStackTrace();
        } finally {
            try {
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
                ex.printStackTrace();
            }

        }
        //System.out.println("Practice List: "+stringBuffer.toString());
        return stringBuffer.toString();
    }

    public String ajaxQuestionsFileUpload(NewAjaxHandlerAction ajaxHandlerAction) {

        Connection connection = null;
        PreparedStatement prestatement = null;
        ResultSet resultSet = null;
        int flag = 0;

        String resultMessage = "";

        int rowsInserted = 0;

        try {
            connection = ConnectionProvider.getInstance().getConnection();

            prestatement = connection.prepareStatement("INSERT INTO tblEcertQuestion(Question,option1,option2,option3,option4,AnwerOption,Explanation,TopicId,CreatedBy,CreatedDate,STATUS,SubtopicId) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
            FileInputStream fs = new FileInputStream(ajaxHandlerAction.getFile());
            //    System.out.println("fs FileInputStream........."+fs);
            Workbook wb = Workbook.getWorkbook(fs);
            int sheet1Rows = 0;
            int sheet2Rows = 0;
            //for (int i = 0; i < 2; i++) {
            Sheet sh = wb.getSheet(0);
            sheet1Rows = sh.getRows();

            int totalRowsInTheUploadedExcel = sheet1Rows + sheet2Rows - 1;

            sh = wb.getSheet(0);



            // To get the number of rows present in sheet
            int totalNoOfRows = sh.getRows();

            int totalNoOfCols = sh.getColumns();
            if (ajaxHandlerAction.getFileFileName() != null) {

                if (totalNoOfCols != 8 || !"Question".equals(sh.getCell(1, 0).getContents()) || !"Option1".equals(sh.getCell(2, 0).getContents())
                        || !"Option2".equals(sh.getCell(3, 0).getContents()) || !"Option3".equals(sh.getCell(4, 0).getContents())
                        || !"Option4".equals(sh.getCell(5, 0).getContents()) || !"AnswerOption".equals(sh.getCell(6, 0).getContents())
                        || !"Explanation".equals(sh.getCell(7, 0).getContents())) {

                    resultMessage = "InvalidFormat";
                } else {

                    for (int row1 = 1; row1 < totalNoOfRows; row1++) {

                        if (sh.getCell(1, row1).getContents() == null || "".equals(sh.getCell(1, row1).getContents())
                                || sh.getCell(2, row1).getContents() == null || "".equals(sh.getCell(2, row1).getContents())
                                || sh.getCell(3, row1).getContents() == null || "".equals(sh.getCell(3, row1).getContents())
                                || sh.getCell(4, row1).getContents() == null || "".equals(sh.getCell(4, row1).getContents())
                                || sh.getCell(5, row1).getContents() == null || "".equals(sh.getCell(5, row1).getContents())
                                || sh.getCell(6, row1).getContents() == null || "".equals(sh.getCell(6, row1).getContents())
                                || sh.getCell(7, row1).getContents() == null || "".equals(sh.getCell(7, row1).getContents())) {
                            flag = 1;
                            resultMessage = "Empty";
                        }

                    }

                    if (flag != 1) {
                        for (int row = 1; row < totalNoOfRows; row++) {

                            String question = sh.getCell(1, row).getContents();
                            // String code = sh.getCell(1, row).getContents();

                            String option1 = sh.getCell(2, row).getContents();
                            String option2 = sh.getCell(3, row).getContents();
                            String option3 = sh.getCell(4, row).getContents();
                            String option4 = sh.getCell(5, row).getContents();
                            String anwerOption = sh.getCell(6, row).getContents();
                            String explanation = sh.getCell(7, row).getContents();


                            //     System.out.println("---->question........."+question);
                            //        System.out.println("---->Integer.parseInt(\"5\"+empId)"+Integer.parseInt("5"+empId));
                            //   System.out.println("---->anwerOption........."+anwerOption);
                            prestatement.setString(1, question);
                            prestatement.setString(2, option1);
                            prestatement.setString(3, option2);
                            prestatement.setString(4, option3);
                            prestatement.setString(5, option4);
                            prestatement.setString(6, anwerOption);
                            prestatement.setString(7, explanation);
                            prestatement.setInt(8, ajaxHandlerAction.getTopicId());
                            prestatement.setString(9, ajaxHandlerAction.getCreatedBy());
                            prestatement.setTimestamp(10, DateUtility.getInstance().getCurrentMySqlDateTime());
                            prestatement.setString(11, "Active");
                            prestatement.setInt(12, ajaxHandlerAction.getSubTopicId());
                            //    System.out.println(".................."+DateUtility.getInstance().getCurrentMySqlDateTime());
                            prestatement.executeUpdate();
                            rowsInserted++;
                            /*for (int col = 0; col < totalNoOfCols; col++) {
                            System.out.print(sh.getCell(col, row).getContents() + "\t");
                            }*/

                            //   statement.addBatch("UPDATE tblEmpWages SET DaysVacationFromBiometric ="+vacations+" WHERE PayRoll_Id ="+payrollId+" AND MONTH(PayPeriodStartDate) = 4 AND YEAR(PayPeriodStartDate)=2015 AND IsBlock=0");
                        }

                        resultMessage = "uploaded^" + rowsInserted;

                        //  FileUtils.copyFile(getFile(), targetDirectory);
                        //  setObjectType("Emp Reviews");

                    }
                }
            } else {

                resultMessage = "Error";
            }


        } catch (Exception ioe) {
            resultMessage = "<font style='color:red;'>Please try again later</font>";
            ioe.printStackTrace();
            System.err.println(ioe);
        } finally {
            try {


                if (prestatement != null) {
                    prestatement.close();
                    prestatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return resultMessage;


    }

    /** Created By :Tirumala Teja Kadamanti
    Purpose : Newsletters Deployment Automation
    Use : To add and search Newsletters and images in a directory
     **/
    public String getAllFilesInDirectory(HttpServletRequest httpServletRequest, NewAjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException {
        String response = "";
        String basePath = Properties.getProperty("NewsLetters.Path");
        String monthName = "";
        if (Integer.parseInt(ajaxHandlerAction.getMonth()) == 0) {
            monthName = "January";
        } else if (Integer.parseInt(ajaxHandlerAction.getMonth()) == 1) {
            monthName = "February";
        } else if (Integer.parseInt(ajaxHandlerAction.getMonth()) == 2) {
            monthName = "March";
        } else if (Integer.parseInt(ajaxHandlerAction.getMonth()) == 3) {
            monthName = "April";
        } else if (Integer.parseInt(ajaxHandlerAction.getMonth()) == 4) {
            monthName = "May";
        } else if (Integer.parseInt(ajaxHandlerAction.getMonth()) == 5) {
            monthName = "June";
        } else if (Integer.parseInt(ajaxHandlerAction.getMonth()) == 6) {
            monthName = "July";
        } else if (Integer.parseInt(ajaxHandlerAction.getMonth()) == 7) {
            monthName = "August";
        } else if (Integer.parseInt(ajaxHandlerAction.getMonth()) == 8) {
            monthName = "September";
        } else if (Integer.parseInt(ajaxHandlerAction.getMonth()) == 9) {
            monthName = "October";
        } else if (Integer.parseInt(ajaxHandlerAction.getMonth()) == 10) {
            monthName = "November";
        } else if (Integer.parseInt(ajaxHandlerAction.getMonth()) == 11) {
            monthName = "December";
        }

        basePath = basePath + "//" + ajaxHandlerAction.getYear() + "//" + monthName + "//" + ajaxHandlerAction.getType() + "//" + ajaxHandlerAction.getCategory();
        File file = new File(basePath);
        File[] listFiles = file.listFiles();

        if (listFiles != null) {
            Arrays.sort(listFiles, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
            for (File files : listFiles) {
                response = response + files.getName() + "*@!";
            }
        }
        return response;
    }

    public String getAllFilesInImagesDirectory(HttpServletRequest httpServletRequest, NewAjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException {
        String response = "";
        String basePath = Properties.getProperty("Images.NewsLetters.Path");
        String monthName = "";
        if (Integer.parseInt(ajaxHandlerAction.getMonth()) == 0) {
            monthName = "January";
        } else if (Integer.parseInt(ajaxHandlerAction.getMonth()) == 1) {
            monthName = "February";
        } else if (Integer.parseInt(ajaxHandlerAction.getMonth()) == 2) {
            monthName = "March";
        } else if (Integer.parseInt(ajaxHandlerAction.getMonth()) == 3) {
            monthName = "April";
        } else if (Integer.parseInt(ajaxHandlerAction.getMonth()) == 4 && ("2016".equalsIgnoreCase(ajaxHandlerAction.getYear()))) {
            monthName = "may";
        } else if (Integer.parseInt(ajaxHandlerAction.getMonth()) == 4 && (!"2016".equalsIgnoreCase(ajaxHandlerAction.getYear()))) {
            monthName = "May";
        } else if (Integer.parseInt(ajaxHandlerAction.getMonth()) == 5 && ("2016".equalsIgnoreCase(ajaxHandlerAction.getYear()))) {
            monthName = "june";
        } else if (Integer.parseInt(ajaxHandlerAction.getMonth()) == 5 && (!"2016".equalsIgnoreCase(ajaxHandlerAction.getYear()))) {
            monthName = "June";
        } else if (Integer.parseInt(ajaxHandlerAction.getMonth()) == 6) {
            monthName = "July";
        } else if (Integer.parseInt(ajaxHandlerAction.getMonth()) == 7) {
            monthName = "August";
        } else if (Integer.parseInt(ajaxHandlerAction.getMonth()) == 8) {
            monthName = "September";
        } else if (Integer.parseInt(ajaxHandlerAction.getMonth()) == 9) {
            monthName = "October";
        } else if (Integer.parseInt(ajaxHandlerAction.getMonth()) == 10 && ("2016".equalsIgnoreCase(ajaxHandlerAction.getYear()))) {
            monthName = "november";
        } else if (Integer.parseInt(ajaxHandlerAction.getMonth()) == 10 && (!"2016".equalsIgnoreCase(ajaxHandlerAction.getYear()))) {
            monthName = "November";
        } else if (Integer.parseInt(ajaxHandlerAction.getMonth()) == 11 && ("2016".equalsIgnoreCase(ajaxHandlerAction.getYear()))) {
            monthName = "december";
        } else if (Integer.parseInt(ajaxHandlerAction.getMonth()) == 11 && (!"2016".equalsIgnoreCase(ajaxHandlerAction.getYear()))) {
            monthName = "December";
        }
        basePath = basePath + "//" + ajaxHandlerAction.getYear() + "//" + monthName;
        File file = new File(basePath);
        File[] listFiles = file.listFiles();

        if (listFiles != null) {
            Arrays.sort(listFiles, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
            for (File files : listFiles) {
                response = response + files.getName() + "*@!";
            }
        }
        return response;
    }

    /* News Letters Deployment End */
    public String getBDMStatistics(String bdmId, String startDate, String endDate) throws ServiceLocatorException {

        String response = "";
        // Connection connection = null;
        CallableStatement callableStatement = null;
        Connection connection = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("{call spBDMStatisticsByActivityType(?,?,?,?)}");
            Map bdmLoginIdsMap = new HashMap();
            if (bdmId.equalsIgnoreCase("All")) {
                bdmLoginIdsMap = DataSourceDataProvider.getInstance().getAllBDMLoginIds();
                bdmId = getKeys(bdmLoginIdsMap, ",");
                bdmId = bdmId.replaceAll("'", "");

            }
            callableStatement.setString(1, bdmId);

            if (!"".equalsIgnoreCase(startDate)) {
                callableStatement.setString(2, com.mss.mirage.util.DateUtility.getInstance().convertStringToMySQLDate(startDate));
            } else {
                callableStatement.setString(2, "");
            }
            if (!"".equalsIgnoreCase(endDate)) {
                callableStatement.setString(3, com.mss.mirage.util.DateUtility.getInstance().convertStringToMySQLDate(endDate));
            } else {
                callableStatement.setString(3, "");
            }
            callableStatement.registerOutParameter(4, Types.VARCHAR);
            callableStatement.execute();


            response = callableStatement.getString(4);


        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {

                if (callableStatement != null) {
                    callableStatement.close();
                    callableStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return response;
    }

    public String getBdmStatisticsDetailsByLoginId(String bdmId, String startDate, String endDate, String activityType) throws ServiceLocatorException {

        String response = "";
        // Connection connection = null;
        CallableStatement callableStatement = null;
        Connection connection = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("{call spBDMStatisticsDetailsByActivityType(?,?,?,?,?)}");
            Map bdmLoginIdsMap = new HashMap();
            if (bdmId.equalsIgnoreCase("All")) {
                bdmLoginIdsMap = DataSourceDataProvider.getInstance().getAllBDMLoginIds();
                bdmId = getKeys(bdmLoginIdsMap, ",");
                bdmId = bdmId.replaceAll("'", "");

            }
            callableStatement.setString(1, bdmId);
            if (!"".equalsIgnoreCase(startDate)) {
                callableStatement.setString(2, com.mss.mirage.util.DateUtility.getInstance().convertStringToMySQLDate(startDate));
            } else {
                callableStatement.setString(2, "");
            }
            if (!"".equalsIgnoreCase(endDate)) {
                callableStatement.setString(3, com.mss.mirage.util.DateUtility.getInstance().convertStringToMySQLDate(endDate));
            } else {
                callableStatement.setString(3, "");
            }

            callableStatement.setString(4, activityType);
            callableStatement.registerOutParameter(5, Types.VARCHAR);
            callableStatement.execute();


            response = callableStatement.getString(5);


        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {

                if (callableStatement != null) {
                    callableStatement.close();
                    callableStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return response;
    }

    private String getKeys(Map map, String delimiter) {
        Iterator tempIterator = map.entrySet().iterator();
        String keys = "";
        String key = "";
        int cnt = 0;

        while (tempIterator.hasNext()) {
            Map.Entry entry = (Map.Entry) tempIterator.next();
            key = entry.getKey().toString();
            entry = null;

            //  Add the Delimiter to the Keys Field for the Second Key onwards
            if (cnt > 0) {
                keys = keys + delimiter;
            }

            keys = keys + "'" + key + "'";
            cnt++;
        }
        tempIterator = null;
        return (keys);
    }

    public String getActivityContacts(String activityId) throws ServiceLocatorException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        String ContactNames = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            //SELECT Comments FROM tblEmpIssues WHERE Id=810
            preparedStatement = connection.prepareStatement("SELECT tblCrmActivity.ContactId FROM tblCrmActivity WHERE Id=?");
            preparedStatement.setString(1, activityId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                ContactNames = DataSourceDataProvider.getInstance().getContactNamesByIdsForBdm(resultSet.getString("ContactId"));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ServiceLocatorException sle) {
            sle.printStackTrace();
        } finally {
            try {

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
                ex.printStackTrace();
            }
        }


        return ContactNames;
    }

    public String getRequirementOtherDetails(int reqId) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String totalStream = "";
        int i = 0;
        try {
            String query = "SELECT AssignedDate,NoResumes AS NoOfPos,tblRec.CreatedDate,RequirementId,CONCAT(FName,' ',MName,'.',LName) AS CreatedBy,AssignedTo  FROM tblRecRequirement LEFT OUTER JOIN tblRec ON (tblRecRequirement.Id=tblRec.RequirementId)  LEFT OUTER JOIN tblEmployee ON (tblRecRequirement.CreatedBy=tblEmployee.LoginId) WHERE tblRecRequirement.Id=  " + reqId;

            // System.out.println("query-->"+query);
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            String AssignedDate = "-";
            String NoOfPos = "-";
            String NoOfResumesSubmitted = "-";
            String CreatedDate = "-";
            String CreatedBy = "-";
            String AssignedTo = "-";
            while (resultSet.next()) {
                if (resultSet.getString("AssignedDate") != null) {
                    AssignedDate = resultSet.getString("AssignedDate");
                }
                if (resultSet.getString("NoOfPos") != null) {
                    NoOfPos = resultSet.getString("NoOfPos");
                }
//                  if(resultSet.getString("NoOfResumesSubmitted")!=null)
//                    NoOfResumesSubmitted = resultSet.getString("NoOfResumesSubmitted");
                if (resultSet.getString("CreatedDate") != null) {
                    CreatedDate = resultSet.getString("CreatedDate");
                    i++;
                }

                if (resultSet.getString("CreatedBy") != null) {
                    CreatedBy = resultSet.getString("CreatedBy");
                }

                if (resultSet.getString("AssignedTo") != null) {
                    AssignedTo = resultSet.getString("AssignedTo");
                }

            }

            totalStream = AssignedDate + "@" + NoOfPos + "@" + i + "@" + CreatedDate + "@" + CreatedBy + "@" + AssignedTo;
        } catch (Exception e) {
            System.err.println("Exception is-->" + e.getMessage());
        } finally {
            try {
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
            } catch (Exception sqle) {
                System.err.println("SQL Exception is-->" + sqle.getMessage());
            }
        }
        return totalStream;
    }

    /*
     * author: harsha
     * this method is for getting number of contacts related to activities of related accounts
     * 
     * used: in admin role {Executive dashbords under customer contacts}
     */
    public String getCustomerContacts(String customerName, String teamMemberId, String startDate, String endDate, int teamMemberCheck, String titleType) throws ServiceLocatorException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement1 = null;
        ResultSet resultSet1 = null;
        Connection connection = null;
        String result = "";
        String query = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();

            String empNamesList = "'" + teamMemberId + "'";
            if (teamMemberCheck == 1) {
                String departmentId = DataSourceDataProvider.getInstance().getDepartmentName(teamMemberId);
                Map teamMap = DataSourceDataProvider.getInstance().getMyTeamMembers(teamMemberId, departmentId);
                String teamKeys = getKeys(teamMap, ",");

                //if(empNamesList.equalsIgnoreCase(""))
                if (!"".equals(teamKeys)) {
                    empNamesList = empNamesList + ',' + getKeys(teamMap, ",");
                }

            }

            if ("BDM".equals(titleType) || "Vice President".equalsIgnoreCase(titleType)) {
                query = "Select tblCrmAccount.Id AS Id,tblCrmAccount.Name As Name from tblCrmAccount where tblCrmAccount.Name LIKE ? And tblCrmAccount.Id in (Select AccountId from tblCrmAccountTeam Where tblCrmAccountTeam.TeamMemberId In(" + empNamesList + ") )  ORDER BY tblCrmAccount.Name Limit 50;";
            } else {
                query = "Select tblCrmAccount.Id AS Id,tblCrmAccount.Name As Name from tblCrmAccount where  tblCrmAccount.Name LIKE ?  ORDER BY tblCrmAccount.Name Limit 50";
            }


            preparedStatement1 = connection.prepareStatement(query);

            preparedStatement1.setString(1, customerName + "%");
            resultSet1 = preparedStatement1.executeQuery();

            Map accountMap = new HashMap();


            while (resultSet1.next()) {
                accountMap.put(resultSet1.getString("Name"), resultSet1.getString("Id"));

            }

            List accountList = new ArrayList(accountMap.values());
            String customerId = "";
            for (int n = 0; n < accountList.size(); n++) {
                customerId = customerId + accountList.get(n) + ",";

            }


            if (customerId.trim().length() > 0) {


                customerId = customerId.substring(0, customerId.length() - 1);


                StringBuffer queryStringBuffer = new StringBuffer("Select AccountId,ContactId,tblCrmAccount.Name As Name from tblCrmAccount right join tblCrmActivity  on (tblCrmActivity.AccountId=tblCrmAccount.Id) where  tblCrmAccount.Id in(" + customerId + ")");


                if (!"BDM".equals(titleType) && !"Vice President".equalsIgnoreCase(titleType)) {


                    if (teamMemberId != null && !"All".equals(teamMemberId)) {
                        queryStringBuffer.append(" AND tblCrmActivity.CreatedById IN (" + empNamesList + ") ");
                    }
                }

                if (startDate != null && !"".equals(startDate)) {
                    queryStringBuffer.append(" AND DATE(tblCrmActivity.CreatedDate)>=DATE('" + DateUtility.getInstance().convertStringToMySQLDate(startDate) + "') ");
                }

                if (endDate != null && !"".equals(endDate)) {
                    queryStringBuffer.append(" AND DATE(tblCrmActivity.CreatedDate)<=DATE('" + DateUtility.getInstance().convertStringToMySQLDate(endDate) + "') ");
                }
                queryStringBuffer.append(" And ContactId!=0  ORDER BY tblCrmAccount.Name");

                //  System.out.println("queryStringBuffer.toString()"+queryStringBuffer.toString());
                preparedStatement = connection.prepareStatement(queryStringBuffer.toString());

                resultSet = preparedStatement.executeQuery();

                Map map = new HashMap();
                Set s = null;


                while (resultSet.next()) {


                    String con[] = resultSet.getString("ContactId").split(",");

                    for (int j = 0; j < con.length; j++) {

                        if (!"0".equalsIgnoreCase(con[j])) {


                            if (map.containsKey(resultSet.getString("Name"))) {


                                if (s.add(con[j])) {
                                    int i = (Integer) map.get(resultSet.getString("Name"));

                                    map.put(resultSet.getString("Name"), (i + 1));

                                }

                            } else {

                                s = new HashSet();
                                if (s.add(con[j])) {


                                    map.put(resultSet.getString("Name"), 1);

                                }

                            }
                        }
                    }


                }
                //    System.out.println("ContactNames..res.."+map.entrySet());





                Map activeContactMap = DataSourceDataProvider.getInstance().getActiveIndividualCustomerContacts(customerId);

                Iterator iterator = accountMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry entry = (Map.Entry) iterator.next();
                    result = result + entry.getKey() + "#^$";
                    if (map.containsKey(entry.getKey())) {
                        result = result + map.get(entry.getKey().toString()) + "#^$";
                    } else {
                        result = result + 0 + "#^$";
                    }
                    if (activeContactMap.containsKey(entry.getKey())) {
                        result = result + activeContactMap.get(entry.getKey().toString()) + "#^$";
                    } else {
                        result = result + 0 + "#^$";
                    }
                    result = result + entry.getValue() + "*@!";
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ServiceLocatorException sle) {
            sle.printStackTrace();
        } finally {
            try {

                if (resultSet1 != null) {
                    resultSet1.close();
                    resultSet1 = null;
                }
                if (preparedStatement1 != null) {
                    preparedStatement1.close();
                    preparedStatement1 = null;
                }
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
                ex.printStackTrace();
            }
        }


        return result;
    }

    /* author: harsha
     * this method is for getting all activities for account
     * 
     * used : in admin role {Executive DashBords under customer contacts}
     */
    public String getContactActivities(String customerId, String teamMemberId, String startDate, String endDate, int teamMemberCheck, String titleType) throws ServiceLocatorException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Connection connection = null;
        String result = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();

            StringBuffer queryStringBuffer = new StringBuffer("Select ActivityType,Priority,ContactId,Status,Id As ActivityId,CreatedDate,CreatedById from tblCrmActivity where AccountId=" + customerId);


            if (!"BDM".equalsIgnoreCase(titleType) && !"Vice President".equalsIgnoreCase(titleType)) {

                String empNamesList = "'" + teamMemberId + "'";
                if (teamMemberCheck == 1) {
                    String departmentId = DataSourceDataProvider.getInstance().getDepartmentName(teamMemberId);
                    Map teamMap = DataSourceDataProvider.getInstance().getMyTeamMembers(teamMemberId, departmentId);
                    String teamKeys = getKeys(teamMap, ",");

                    //if(empNamesList.equalsIgnoreCase(""))
                    if (!"".equals(teamKeys)) {
                        empNamesList = empNamesList + ',' + getKeys(teamMap, ",");
                    }

                }

                //System.out.println("empNamesList"+empNamesList);


                if (teamMemberId != null && !"All".equals(teamMemberId)) {
                    queryStringBuffer.append(" AND tblCrmActivity.CreatedById IN (" + empNamesList + ") ");
                }
            }



            if (startDate != null && !"".equals(startDate)) {
                queryStringBuffer.append(" AND DATE(tblCrmActivity.CreatedDate)>=DATE('" + DateUtility.getInstance().convertStringToMySQLDate(startDate) + "') ");
            }

            if (endDate != null && !"".equals(endDate)) {
                queryStringBuffer.append(" AND DATE(tblCrmActivity.CreatedDate)<=DATE('" + DateUtility.getInstance().convertStringToMySQLDate(endDate) + "') ");
            }
            queryStringBuffer.append(" And ContactId!=0 ORDER BY tblCrmActivity.CreatedDate");

            //  System.out.println("queryStringBuffer.toString()"+queryStringBuffer.toString());
            preparedStatement = connection.prepareStatement(queryStringBuffer.toString());


            resultSet = preparedStatement.executeQuery();

            String ContactNames = "";
            while (resultSet.next()) {

                if (!"".equals(resultSet.getString("ContactId"))) {
                    ContactNames = DataSourceDataProvider.getInstance().getContactNamesByIds(resultSet.getString("ContactId"));
                } else {
                    ContactNames = "-";
                }

                result = result + "#^$" + resultSet.getString("CreatedById") + "#^$" + resultSet.getString("ActivityType") + "#^$" + resultSet.getString("Status") + "#^$" + resultSet.getString("Priority") + "#^$" + resultSet.getString("CreatedDate") + "#^$" + ContactNames + "#^$" + resultSet.getString("ActivityId") + "*@!";

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ServiceLocatorException sle) {
            sle.printStackTrace();
        } finally {
            try {

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
                ex.printStackTrace();
            }
        }


        return result;
    }

    public String reqOverviewPieChart(HttpServletRequest httpServletRequest, NewAjaxHandlerAction ajaxHandlerAction) {



        PreparedStatement preparedStatement = null;
        //CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        String response = "";
        String query = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();


            query = "SELECT COUNT(DISTINCT(tblRec.ConsultantId)) AS COUNT,tblRec.STATUS AS STATUS FROM tblRecRequirement LEFT OUTER JOIN tblCrmAccount ON (tblRecRequirement.CustomerId=tblCrmAccount.Id)"
                    + "LEFT OUTER JOIN tblRec ON (tblRecRequirement.Id=tblRec.RequirementId)"
                    + " LEFT OUTER JOIN tblRecConsultantActivity ON"
                    + "(tblRecRequirement.Id = tblRecConsultantActivity.RequirementId)";

            query = query + "WHERE 1=1 ";


            if (!"All".equals(ajaxHandlerAction.getCreatedBy())) {
                query = query + " AND tblRecRequirement.CreatedBy='" + ajaxHandlerAction.getCreatedBy() + "' ";
            }
            if (ajaxHandlerAction.getTitle() != null && !"".equals(ajaxHandlerAction.getTitle())) {
                query = query + " AND `tblRecRequirement`.`JobTitle` LIKE '%" + ajaxHandlerAction.getTitle() + "%'";
            }


            if (ajaxHandlerAction.getPostedDate1() != null && !"".equals(ajaxHandlerAction.getPostedDate1())) {
                query = query + " AND DATE(`tblRecRequirement`.`DatePosted`) >= DATE('" + DateUtility.getInstance().convertStringToMySQLDate(ajaxHandlerAction.getPostedDate1()) + "')";
            }

            if (ajaxHandlerAction.getPostedDate2() != null && !"".equals(ajaxHandlerAction.getPostedDate2())) {
                query = query + " AND DATE(`tblRecRequirement`.`DatePosted`) <= DATE('" + DateUtility.getInstance().convertStringToMySQLDate(ajaxHandlerAction.getPostedDate2()) + "')";
            }

            if (!"All".equals(ajaxHandlerAction.getStatus())) {
                query = query + " AND `tblRecRequirement`.`Status` LIKE '" + ajaxHandlerAction.getStatus() + "'";
            }
            if (!"-1".equals(ajaxHandlerAction.getCountry())) {
                query = query + " AND `tblRecRequirement`.`Country` like '" + ajaxHandlerAction.getCountry() + "' ";
            }

            if (ajaxHandlerAction.getCustomerName() != null && !"".equals(ajaxHandlerAction.getCustomerName())) {
                query = query + " AND `tblCrmAccount`.`Name` LIKE '%" + ajaxHandlerAction.getCustomerName() + "%'";
            }

            if (ajaxHandlerAction.getState() != null && !"".equals(ajaxHandlerAction.getState())) {
                query = query + " AND `tblRecRequirement`.`state` like '" + ajaxHandlerAction.getState() + "' ";
            }

            if (!"-1".equals(ajaxHandlerAction.getPracticeid())) {
                query = query + "  AND tblRecRequirement.Practice like '" + ajaxHandlerAction.getPracticeid() + "' ";
            }
            //if (ajaxHandlerAction.getRequirementId() != 0) {
            if (ajaxHandlerAction.getReqId() != null && !"".equals(ajaxHandlerAction.getReqId())) {
                query = query + "  AND tblRecRequirement .Id =" + ajaxHandlerAction.getReqId() + " ";
            }
            if (ajaxHandlerAction.getPreSalesPerson() != null && !"".equals(ajaxHandlerAction.getPreSalesPerson())) {
                query = query + " AND (tblRecRequirement.AssignToTechLead ='" + ajaxHandlerAction.getPreSalesPerson() + "' || tblRecRequirement.SecondaryTechLead ='" + ajaxHandlerAction.getPreSalesPerson() + "') ";
            } else {
                Map rolesMap = (Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_ROLES);
                List finalTeachLeadList;
                if (rolesMap.containsValue("Admin") || AuthorizationManager.getInstance().isAuthorizedForSurveyForm("PRESALES_REQUIREMENT_ACCESS", httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString())) {

                    finalTeachLeadList = DataSourceDataProvider.getInstance().getTechLead();
                } else {
                    Map teamMap = (Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP);
                    List TechLeadList = DataSourceDataProvider.getInstance().getTechLead();
                    List tempTechLeadList = new ArrayList();
                    for (int j = 0; j < TechLeadList.size(); j++) {
                        if (teamMap.containsValue(TechLeadList.get(j))) {
                            tempTechLeadList.add(TechLeadList.get(j));
                        }
                    }
                    tempTechLeadList.add(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME));

                    finalTeachLeadList = tempTechLeadList;
                }
                String teamList = DataSourceDataProvider.getInstance().getStringByList(finalTeachLeadList);


                query = query + " AND (FIND_IN_SET(tblRecRequirement.AssignToTechLead,'" + teamList + "' )  || FIND_IN_SET(tblRecRequirement.SecondaryTechLead,'" + teamList + "' ) )";

            }

            query = query + " AND tblRec.STATUS IS NOT NULL AND tblRec.STATUS !='' AND tblRec.STATUS != '-1' GROUP BY tblRec.STATUS";


            //   System.out.println("query........"+query);
            preparedStatement = connection.prepareStatement(query);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                response = response + resultSet.getString("status") + "#^$";
                response = response + resultSet.getInt("count") + "*@!";

            }
            if (response.equals("")) {
                response = "NoData";
            }
            //  System.out.println("resposnse...."+response);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ServiceLocatorException sle) {
            sle.printStackTrace();
        } finally {
            try {

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
                ex.printStackTrace();
            }
        }
        return response;
    }

    @Override
    public String getRequirementOverviewDetails(HttpServletRequest httpServletRequest, NewAjaxHandlerAction ajaxHandlerAction) {
        Connection connection = null;
        StringBuffer stringBuffer = new StringBuffer();
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        Statement statement1 = null;
        ResultSet resultSet = null;
        ResultSet resultSet1 = null;
        String createdBy = "";
        String totalStream = "";
        String queryString = "";
        int i = 0;
        int totalRecords = 0;

        String response = "";

        //resultType = "accessFailed";

        DateUtility dateUtility = new DateUtility();

        // System.out.println(" impl  --- createdBy----------"+ajaxHandlerAction.getCreatedBy()+"--assignedTo-------------"+ajaxHandlerAction.getAssignedTo()+"--title-------------"+ajaxHandlerAction.getTitle()+"--postedDate1-------"+ajaxHandlerAction.getPostedDate1()+"---postedDate2----"+ajaxHandlerAction.getPostedDate2()+"---status----"+ajaxHandlerAction.getStatus());


        try {
            connection = ConnectionProvider.getInstance().getConnection();
            if ("1".equals(ajaxHandlerAction.getPgFlag().trim())) {

                StringBuffer queryStringBuf = new StringBuffer();;

                statement1 = connection.createStatement();
                queryStringBuf.append("Select COUNT(id) As count from (SELECT DISTINCT(tblRec.ConsultantId) As id FROM tblRecRequirement LEFT OUTER JOIN tblCrmAccount ON (tblRecRequirement.CustomerId=tblCrmAccount.Id) LEFT OUTER JOIN tblRec ON (tblRecRequirement.Id=tblRec.RequirementId)  LEFT OUTER JOIN tblRecConsultantActivity ON (tblRecRequirement.Id = tblRecConsultantActivity.RequirementId) ");


                queryStringBuf.append(" WHERE tblRec.STATUS = '" + ajaxHandlerAction.getState() + "' ");

                if (!"All".equals(ajaxHandlerAction.getCreatedBy())) {
                    queryStringBuf.append(" AND tblRecRequirement.CreatedBy='" + ajaxHandlerAction.getCreatedBy() + "' ");
                }
                if (ajaxHandlerAction.getTitle() != null && !"".equals(ajaxHandlerAction.getTitle())) {
                    queryStringBuf.append(" AND `tblRecRequirement`.`JobTitle` LIKE '%" + ajaxHandlerAction.getTitle() + "%'");
                }


                if (ajaxHandlerAction.getPostedDate1() != null && !"".equals(ajaxHandlerAction.getPostedDate1())) {
                    queryStringBuf.append(" AND DATE(`tblRecRequirement`.`DatePosted`) >= DATE('" + DateUtility.getInstance().convertStringToMySQLDate(ajaxHandlerAction.getPostedDate1()) + "')");
                }

                if (ajaxHandlerAction.getPostedDate2() != null && !"".equals(ajaxHandlerAction.getPostedDate2())) {
                    queryStringBuf.append(" AND DATE(`tblRecRequirement`.`DatePosted`) <= DATE('" + DateUtility.getInstance().convertStringToMySQLDate(ajaxHandlerAction.getPostedDate2()) + "')");
                }

                if (!"All".equals(ajaxHandlerAction.getStatus())) {
                    queryStringBuf.append(" AND `tblRecRequirement`.`Status` LIKE '" + ajaxHandlerAction.getStatus() + "'");
                }
                if (!"-1".equals(ajaxHandlerAction.getCountry())) {
                    queryStringBuf.append(" AND `tblRecRequirement`.`Country` like '" + ajaxHandlerAction.getCountry() + "' ");
                }

                if (ajaxHandlerAction.getCustomerName() != null && !"".equals(ajaxHandlerAction.getCustomerName())) {
                    queryStringBuf.append(" AND `tblCrmAccount`.`Name` LIKE '%" + ajaxHandlerAction.getCustomerName() + "%'");
                }

                if (ajaxHandlerAction.getStatesFromOrg() != null && !"".equals(ajaxHandlerAction.getStatesFromOrg())) {
                    queryStringBuf.append(" AND `tblRecRequirement`.`state` like '" + ajaxHandlerAction.getStatesFromOrg() + "' ");
                }

                if (!"-1".equals(ajaxHandlerAction.getPracticeid())) {
                    queryStringBuf.append("  AND tblRecRequirement.Practice like '" + ajaxHandlerAction.getPracticeid() + "' ");
                }
                // if (ajaxHandlerAction.getRequirementId() != 0) {
                if (ajaxHandlerAction.getReqId() != null && !"".equals(ajaxHandlerAction.getReqId())) {

                    queryStringBuf.append("  AND tblRecRequirement .Id =" + ajaxHandlerAction.getReqId() + " ");
                }
                if (ajaxHandlerAction.getPreSalesPerson() != null && !"".equals(ajaxHandlerAction.getPreSalesPerson())) {
                    queryStringBuf.append(" AND (tblRecRequirement.AssignToTechLead ='" + ajaxHandlerAction.getPreSalesPerson() + "' || tblRecRequirement.SecondaryTechLead ='" + ajaxHandlerAction.getPreSalesPerson() + "') ");
                } else {
                    Map rolesMap = (Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_ROLES);
                    List finalTeachLeadList;
                    if (rolesMap.containsValue("Admin") || AuthorizationManager.getInstance().isAuthorizedForSurveyForm("PRESALES_REQUIREMENT_ACCESS", httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString())) {

                        finalTeachLeadList = DataSourceDataProvider.getInstance().getTechLead();
                    } else {
                        Map teamMap = (Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP);
                        List TechLeadList = DataSourceDataProvider.getInstance().getTechLead();
                        List tempTechLeadList = new ArrayList();
                        for (int j = 0; j < TechLeadList.size(); j++) {
                            if (teamMap.containsValue(TechLeadList.get(j))) {
                                tempTechLeadList.add(TechLeadList.get(j));
                            }
                        }
                        tempTechLeadList.add(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME));

                        finalTeachLeadList = tempTechLeadList;
                    }
                    String teamList = DataSourceDataProvider.getInstance().getStringByList(finalTeachLeadList);


                    queryStringBuf.append(" AND (FIND_IN_SET(tblRecRequirement.AssignToTechLead,'" + teamList + "' )  || FIND_IN_SET(tblRecRequirement.SecondaryTechLead,'" + teamList + "' ) )");

                }
                queryStringBuf.append(" GROUP BY tblRec.ConsultantId  ");
                queryStringBuf.append(" ORDER BY `tblRecRequirement`.`DatePosted` DESC) As tbl");






                //    System.out.println("REQ_SEARCH_QUERY --->" + queryStringBuf.toString());
                //preparedStatement = connection.prepareStatement(queryStringBuf.toString());

                //      resultSet = preparedStatement.executeQuery();

                resultSet = statement1.executeQuery(queryStringBuf.toString());
                while (resultSet.next()) {
                    totalRecords = resultSet.getInt("count");
                }

            }

            // for getting total counts (for pagination) ...> End




            callableStatement = connection.prepareCall("{call spRequirementListDetails(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");




            if (!"All".equals(ajaxHandlerAction.getCreatedBy())) {
                callableStatement.setString(1, ajaxHandlerAction.getCreatedBy());
            } else {
                callableStatement.setString(1, "%");
            }
            if (ajaxHandlerAction.getTitle() != null && !"".equals(ajaxHandlerAction.getTitle())) {
                callableStatement.setString(2, "%" + ajaxHandlerAction.getTitle() + "%");
            } else {
                callableStatement.setString(2, "%");
            }
            if (ajaxHandlerAction.getPostedDate1() != null && !"".equals(ajaxHandlerAction.getPostedDate1())) {
                callableStatement.setString(3, DateUtility.getInstance().convertStringToMySQLDate(ajaxHandlerAction.getPostedDate1()));
            } else {
                callableStatement.setString(3, "1950-01-31");
            }
            if (ajaxHandlerAction.getPostedDate1() != null && !"".equals(ajaxHandlerAction.getPostedDate1())) {
                callableStatement.setString(4, DateUtility.getInstance().convertStringToMySQLDate(ajaxHandlerAction.getPostedDate2()));
            } else {
                callableStatement.setString(4, "2030-01-31");
            }
            if (!"All".equals(ajaxHandlerAction.getStatus())) {
                callableStatement.setString(5, ajaxHandlerAction.getStatus());
            } else {
                callableStatement.setString(5, "%");
            }
            if (!"-1".equals(ajaxHandlerAction.getCountry())) {
                callableStatement.setString(6, ajaxHandlerAction.getCountry());
            } else {
                callableStatement.setString(6, "%");
            }
            if (ajaxHandlerAction.getCustomerName() != null && !"".equals(ajaxHandlerAction.getCustomerName())) {
                callableStatement.setString(7, "%" + ajaxHandlerAction.getCustomerName() + "%");
            } else {
                callableStatement.setString(7, "%");
            }
            if (ajaxHandlerAction.getStatesFromOrg() != null && !"".equals(ajaxHandlerAction.getStatesFromOrg())) {
                callableStatement.setString(8, ajaxHandlerAction.getStatesFromOrg());
            } else {
                callableStatement.setString(8, "%");
            }
            if (!"-1".equals(ajaxHandlerAction.getPracticeid())) {
                callableStatement.setString(9, ajaxHandlerAction.getPracticeid());
            } else {
                callableStatement.setString(9, "%");
            }
            if (ajaxHandlerAction.getReqId() != null && !"".equals(ajaxHandlerAction.getReqId())) {
                callableStatement.setString(10, ajaxHandlerAction.getReqId());
            } else {
                callableStatement.setString(10, "%");
            }
            if (ajaxHandlerAction.getPreSalesPerson() != null && !"".equals(ajaxHandlerAction.getPreSalesPerson())) {
                callableStatement.setString(11, ajaxHandlerAction.getPreSalesPerson());
            } else {
                Map rolesMap = (Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_ROLES);
                List finalTeachLeadList;
                if (rolesMap.containsValue("Admin") || AuthorizationManager.getInstance().isAuthorizedForSurveyForm("PRESALES_REQUIREMENT_ACCESS", httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString())) {

                    finalTeachLeadList = DataSourceDataProvider.getInstance().getTechLead();
                } else {
                    Map teamMap = (Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP);
                    List TechLeadList = DataSourceDataProvider.getInstance().getTechLead();
                    List tempTechLeadList = new ArrayList();
                    for (int j = 0; j < TechLeadList.size(); j++) {
                        if (teamMap.containsValue(TechLeadList.get(j))) {
                            tempTechLeadList.add(TechLeadList.get(j));
                        }
                    }
                    tempTechLeadList.add(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME));

                    finalTeachLeadList = tempTechLeadList;
                }
                String teamList = DataSourceDataProvider.getInstance().getStringByList(finalTeachLeadList);
                callableStatement.setString(11, teamList);
            }
            if (ajaxHandlerAction.getState() != null && !"".equals(ajaxHandlerAction.getState())) {
                callableStatement.setString(12, ajaxHandlerAction.getState());
            } else {
                callableStatement.setString(12, "%");
            }

            //  callableStatement.setString(13,ajaxHandlerAction.getPgFlag());
            callableStatement.setInt(13, (ajaxHandlerAction.getPgNo() - 1) * 20);
            //  System.out.println("pgNo...."+(ajaxHandlerAction.getPgNo() - 1) * 20); 
            callableStatement.registerOutParameter(14, java.sql.Types.VARCHAR);


            callableStatement.execute();
            //     response = callableStatement.getString(15);
            totalStream = callableStatement.getString(14);
            //   System.out.println("totalStream....."+totalStream);
            //      System.out.println("totalRecords....."+totalRecords);
            response = totalStream + "###" + totalRecords;





        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ServiceLocatorException sle) {
            sle.printStackTrace();
        } finally {
            try {

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
                ex.printStackTrace();
            }
        }
        return response;
    }

    public String getInsideSalesStatusList(String bdeLoginId) throws ServiceLocatorException {

        String response = "";
        // Connection connection = null;
        CallableStatement callableStatement = null;
        Connection connection = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("{call spInsideSalesStatusDashBoard(?,?)}");
            Map bdeLoginIdsMap = new HashMap();
            if (bdeLoginId.equalsIgnoreCase("All")) {
                bdeLoginIdsMap = DataSourceDataProvider.getInstance().getInsideSalesBDEByLoginId();
                bdeLoginId = getKeys(bdeLoginIdsMap, ",");
                bdeLoginId = bdeLoginId.replaceAll("'", "");

            }
            callableStatement.setString(1, bdeLoginId);

            callableStatement.registerOutParameter(2, Types.VARCHAR);

            callableStatement.execute();


            response = callableStatement.getString(2);

        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {

                if (callableStatement != null) {
                    callableStatement.close();
                    callableStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return response;
    }

    public String getInsideSalesAccountDetailsList(HttpServletRequest httpServletRequest, NewAjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException {

        String response = "";
        // Connection connection = null;
        CallableStatement callableStatement = null;
        Connection connection = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();

            httpServletRequest.getSession(false).setAttribute("tempBdeLoginId", ajaxHandlerAction.getBdeLoginId());
            httpServletRequest.getSession(false).setAttribute("tempEmployeeName", ajaxHandlerAction.getEmployeeName());

            httpServletRequest.getSession(false).setAttribute("tempAccId", ajaxHandlerAction.getAccountId());
            if (!"".equalsIgnoreCase(ajaxHandlerAction.getLastActivityFrom())) {
                httpServletRequest.getSession(false).setAttribute("tempLastActivityFrom", ajaxHandlerAction.getLastActivityFrom());
            } else {
                httpServletRequest.getSession(false).setAttribute("tempLastActivityFrom", "");
            }
            if (!"".equalsIgnoreCase(ajaxHandlerAction.getLastActivityTo())) {
                httpServletRequest.getSession(false).setAttribute("tempLastActivityTo", ajaxHandlerAction.getLastActivityTo());
            } else {
                httpServletRequest.getSession(false).setAttribute("tempLastActivityTo", "");
            }


            httpServletRequest.getSession(false).setAttribute("tempOpportunity", ajaxHandlerAction.getOpportunity());
            httpServletRequest.getSession(false).setAttribute("tempTouched", ajaxHandlerAction.getTouched());

            callableStatement = connection.prepareCall("{call spInsideSalesAccountDetailsSearch(?,?,?,?,?,?,?)}");

            callableStatement.setString(1, ajaxHandlerAction.getBdeLoginId());
            callableStatement.setInt(2, ajaxHandlerAction.getAccountId());
            if (!"".equalsIgnoreCase(ajaxHandlerAction.getLastActivityFrom())) {
                callableStatement.setString(3, com.mss.mirage.util.DateUtility.getInstance().convertStringToMySQLDate(ajaxHandlerAction.getLastActivityFrom()));
            } else {
                callableStatement.setString(3, "");
            }
            if (!"".equalsIgnoreCase(ajaxHandlerAction.getLastActivityTo())) {
                callableStatement.setString(4, com.mss.mirage.util.DateUtility.getInstance().convertStringToMySQLDate(ajaxHandlerAction.getLastActivityTo()));
            } else {
                callableStatement.setString(4, "");
            }
            callableStatement.setString(5, ajaxHandlerAction.getOpportunity());
            callableStatement.setString(6, ajaxHandlerAction.getTouched());
            callableStatement.registerOutParameter(7, Types.VARCHAR);
            callableStatement.execute();


            response = callableStatement.getString(7);

        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {

                if (callableStatement != null) {
                    callableStatement.close();
                    callableStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return response;
    }

    public String getInvestmentType(int invId) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String responseString = "";
        try {

            // System.out.println("invId......."+invId);
            String queryString1 = "SELECT InvestmentType FROM tblInvestments Where Inv_Id=?";
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString1);
            preparedStatement.setInt(1, invId);


            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                responseString = resultSet.getString("InvestmentType");
            }
            //  System.out.println("responseString"+responseString);

        } catch (SQLException sql) {
            sql.printStackTrace();
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
        return responseString; // returning the object.
    }

    public String addLeadHystory(String loginId, Timestamp remainderDate, String followUpComments, String nextfollowUpComments, int leadId, String opt, int id, int reminderFlag) throws ServiceLocatorException {


        Connection connection = null;
        PreparedStatement preparedStatement = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        String resutMessage = "";
        boolean isInserted = false;




        // String queryString = "INSERT INTO `tblInvestments` ( `Inv_Name`, `Country`, `StartDate`, `EndDate`, `TotalExpenses`,`Currency`,`Location`,`Description`,`AttachmentFileName`,`AttachmentLocation`,`CreatedBy`,InvestmentType)"
        //      + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        String queryString = "{call spAddOrUpdateLeadHystory(?,?,?,?,?,?,?,?,?)}";

        try {
            connection = ConnectionProvider.getInstance().getConnection();

            callableStatement = connection.prepareCall(queryString);
            callableStatement.setInt(1, leadId);
            callableStatement.setString(2, loginId);
            callableStatement.setString(3, opt);
            callableStatement.setTimestamp(4, remainderDate);
            callableStatement.setString(5, followUpComments);
            callableStatement.setString(6, nextfollowUpComments);
            System.out.println("iddd......." + id);
            callableStatement.setInt(7, id);
            callableStatement.setInt(8, reminderFlag);


            callableStatement.registerOutParameter(9, Types.VARCHAR);
            callableStatement.executeUpdate();
            resutMessage = "<font color=green>" + callableStatement.getString(9) + "</font>";


        } catch (SQLException se) {
            se.printStackTrace();
            resutMessage = "<font color=red>" + se.getMessage() + "</font>";
            throw new ServiceLocatorException(se);
        } finally {
            try {
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
            } catch (SQLException se) {
                se.printStackTrace();
                throw new ServiceLocatorException(se);
            }
        }
        return resutMessage;
    }

    public String getLeadFollowUpHistoryDetails(int id) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        String queryString = "";
        JSONObject subJson = null;
        try {
            queryString = "SELECT * FROM tblCrmLeadFollowupHistory WHERE Id=" + id;
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                subJson = new JSONObject();
                subJson.put("LeadId", resultSet.getString("LeadId"));
                subJson.put("Id", resultSet.getString("Id"));
                subJson.put("FollowUpComments", resultSet.getString("FollowUpComments"));
                subJson.put("FollowUpBy", resultSet.getString("FollowUpBy"));
                subJson.put("ReminderFlag", resultSet.getString("ReminderFlag"));
                // System.out.println("resultSet.getString(\"FollowUpBy\")"+resultSet.getString("FollowUpBy"));
                if (resultSet.getString("ReminderDate") != null && !"".equals(resultSet.getString("ReminderDate"))) {
                    subJson.put("ReminderDate", DateUtility.getInstance().convertToviewFormat(resultSet.getString("ReminderDate")));
                } else {
                    subJson.put("ReminderDate", "");
                }
                subJson.put("NextFollowUpSteps", resultSet.getString("NextFollowUpSteps"));


            }


        } catch (Exception sqe) {
            sqe.printStackTrace();
        } finally {
            try {
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
            } catch (SQLException sqle) {
            }
        }
        return subJson.toString();
    }

    public String customerProjectDetailsDashboard(NewAjaxHandlerAction ajaxHandlerAction, HttpServletRequest httpServletRequest) throws ServiceLocatorException {
        String totalStream = "";
        Statement statement = null;
        String query = "";
        Connection connection = null;
        ResultSet resultSet = null;
        int i = 0;
        ajaxHandlerAction.setLoginId((String) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID));


        //    query = "SELECT tblProjects.ProjectName,tblPmoAuthors.AccountId,tblProjects.ProjectStartDate,tblProjects.STATUS, tblCrmAccount.NAME,tblPmoAuthors.ProjectId FROM tblPmoAuthors JOIN tblProjects ON (tblProjects.Id=tblPmoAuthors.ProjectId) JOIN tblCrmAccount ON (tblCrmAccount.Id = tblPmoAuthors.AccountId) WHERE tblPmoAuthors.AuthorId = '" + ajaxHandlerAction.getLoginId() + "'  AND tblPmoAuthors.STATUS =  'Active' ";
        query = "SELECT DISTINCT tblProjects.CustomerId AS AccountId, tblCrmAccount.NAME FROM tblProjects JOIN tblCrmAccount ON (tblCrmAccount.Id = tblProjects.CustomerId) LEFT JOIN tblProjectContacts ON (tblProjects.Id = tblProjectContacts.ProjectId ) WHERE 1=1 ";

        Map rolesMap = (Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_ROLES);
        if (rolesMap.containsValue("Admin") || httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_PMO_ACTIVITY_ACCESS).toString().equals("1")) {


            if (ajaxHandlerAction.getPmoLoginId() != null && !"".equalsIgnoreCase(ajaxHandlerAction.getPmoLoginId())) {
                query = query + " AND tblProjects.PMO = '" + ajaxHandlerAction.getPmoLoginId() + "'";
            }
        } else {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_TEAM_LEAD).toString().equals("1") || httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_USER_MANAGER).toString().equals("1")) {
                Map teamMap = (Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP);
                teamMap.put(ajaxHandlerAction.getLoginId(), httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME).toString());

                if (!"".equalsIgnoreCase(ajaxHandlerAction.getPmoLoginId())) {
                    query = query + " AND tblProjects.PMO = '" + ajaxHandlerAction.getPmoLoginId() + "'";
                } else {
                    query = query + " AND tblProjects.PMO IN (" + DataSourceDataProvider.getInstance().getTeamLoginIdList(teamMap) + ") ";
                }


            } else {
                query = query + " AND tblProjects.PMO = '" + ajaxHandlerAction.getLoginId() + "'";
            }
        }

        if (!"".equalsIgnoreCase(ajaxHandlerAction.getCustomerName()) && !"-1".equals(ajaxHandlerAction.getCustomerName())) {
            query = query + " AND tblCrmAccount.Id=" + ajaxHandlerAction.getCustomerName() + " ";
        }

        if (ajaxHandlerAction.getProjectName() != null && !"".equals(ajaxHandlerAction.getProjectName()) && !"-1".equals(ajaxHandlerAction.getProjectName())) {
            query = query + "AND tblProjects.Id =" + ajaxHandlerAction.getProjectName() + " ";

        }
        if (!"".equalsIgnoreCase(ajaxHandlerAction.getStatus())) {
            query = query + " AND tblProjects.STATUS like '%" + ajaxHandlerAction.getStatus() + "%'";
        }

        if (!"".equalsIgnoreCase(ajaxHandlerAction.getProjectStartDate())) {
            ajaxHandlerAction.setProjectStartDate(DateUtility.getInstance().convertStringToMySQLDate(ajaxHandlerAction.getProjectStartDate()));
            query = query + " AND date(tblProjects.ProjectStartDate) like '%" + ajaxHandlerAction.getProjectStartDate() + "%'";

        }
        if (((!"".equals(ajaxHandlerAction.getPreAssignEmpId()))) && (ajaxHandlerAction.getPreAssignEmpId() != 0)) {

            query = query + " AND tblProjectContacts.ObjectId= " + ajaxHandlerAction.getPreAssignEmpId();

        }
        if (ajaxHandlerAction.getPracticeId() != null && !"".equalsIgnoreCase(ajaxHandlerAction.getPracticeId())) {
            query = query + " AND tblProjects.Practice = '" + ajaxHandlerAction.getPracticeId() + "'";
        }
        query = query + " ORDER BY tblCrmAccount.NAME,tblProjects.ProjectName  LIMIT 100";
        try {

            //   System.out.println("Query -->" + query);

            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);


            while (resultSet.next()) {
                i = i + 1;
                String NAME = resultSet.getString("NAME");
                int AccountId = resultSet.getInt("AccountId");
                int resources = 0;
                //    int resources = DataSourceDataProvider.getInstance().getProjectActiveResourcesByCustomer(resultSet.getInt("AccountId"));
                if (ajaxHandlerAction.getProjectName() != null && !"".equals(ajaxHandlerAction.getProjectName()) && !"-1".equals(ajaxHandlerAction.getProjectName())) {
                    resources = DataSourceDataProvider.getInstance().getProjectActiveResources(Integer.parseInt(ajaxHandlerAction.getProjectName()));
                } else {
                    resources = DataSourceDataProvider.getInstance().getProjectActiveResourcesByCustomer(resultSet.getInt("AccountId"), ajaxHandlerAction.getStatus());
                }
                //  totalStream = totalStream + "|" + ProjectId + "|" + ProjectName +"|" + NAME + "|" + ProjectStartDate + "|" + resources + "|" + status + "|"+ resultSet.getString("PMO") + "|" + AccountId + "^";
                totalStream = totalStream + i + "#^$" + NAME + "#^$" + resources + "#^$" + AccountId + "*@!";

            }
            //System.out.println("totalStream..."+totalStream);
        } catch (Exception sqe) {
            sqe.printStackTrace();
        } finally {
            try {
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
            } catch (SQLException sqle) {
            }
        }
        return totalStream;
    }

    public String getResourceTypeDetailsByCustomer(int accId, String projectId, HttpServletRequest httpServletRequest) throws ServiceLocatorException {
        //String qsTitle = "";
        Statement statement = null;
        ResultSet resultSet = null;
        String query = "";
        Connection connection = null;
        String response = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            query = " SELECT SUM(CASE WHEN tblProjectContacts.EmpProjStatus='Main' THEN 1 ELSE 0 END) AS Main ,"
                    + "SUM(CASE WHEN tblProjectContacts.EmpProjStatus='Shadow' THEN 1 ELSE 0 END) AS Shadow,"
                    + "SUM(CASE WHEN tblProjectContacts.EmpProjStatus='Training' THEN 1 ELSE 0 END) AS Training,"
                    + "SUM(CASE WHEN tblProjectContacts.EmpProjStatus='Overhead' THEN 1 ELSE 0 END) AS Overhead,"
                    + "SUM(CASE WHEN tblProjectContacts.EmpProjStatus='Main' AND tblEmployee.Country='USA' THEN 1 ELSE 0 END) AS MainOnSite,"
                    + "SUM(CASE WHEN tblProjectContacts.EmpProjStatus='Main' AND tblEmployee.Country='INDIA' THEN 1 ELSE 0 END) AS MainOffShore, "
                    + "SUM(CASE WHEN tblProjectContacts.EmpProjStatus='Shadow' AND tblEmployee.Country='USA' THEN 1 ELSE 0 END) AS ShadowOnSite,"
                    + "SUM(CASE WHEN tblProjectContacts.EmpProjStatus='Shadow' AND tblEmployee.Country='INDIA' THEN 1 ELSE 0 END) AS ShadowOffShore,"
                    + "SUM(CASE WHEN tblProjectContacts.EmpProjStatus='Training' AND tblEmployee.Country='USA' THEN 1 ELSE 0 END) AS TrainingOnSite,"
                    + "SUM(CASE WHEN tblProjectContacts.EmpProjStatus='Training' AND tblEmployee.Country='INDIA' THEN 1 ELSE 0 END) AS TrainingOffShore,"
                    + "SUM(CASE WHEN tblProjectContacts.EmpProjStatus='Overhead' AND tblEmployee.Country='USA' THEN 1 ELSE 0 END) AS OverheadOnSite,"
                    + "SUM(CASE WHEN tblProjectContacts.EmpProjStatus='Overhead' AND tblEmployee.Country='INDIA' THEN 1 ELSE 0 END) AS OverheadOffShore,"
                    + "COUNT(tblProjectContacts.Id) AS Total FROM tblProjectContacts LEFT  JOIN tblEmployee ON tblEmployee.Id=tblProjectContacts.ObjectId WHERE  AccountId=" + accId + "  AND STATUS='Active' AND ObjectType='E' AND CurStatus='Active' ";

            if (projectId != null && !"".equals(projectId.trim()) && !"-1".equals(projectId)) {
                query = query + " AND ProjectId=" + projectId;
            }
            // System.out.println("query.."+query);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                response = response + resultSet.getInt("Main") + "#^$" + resultSet.getInt("Shadow") + "#^$" + resultSet.getInt("Training") + "#^$" + resultSet.getInt("Overhead") + "#^$" + resultSet.getInt("MainOnSite") + "#^$" + resultSet.getInt("MainOffShore") + "#^$" + resultSet.getInt("ShadowOnSite") + "#^$" + resultSet.getInt("ShadowOffShore") + "#^$" + resultSet.getInt("TrainingOnSite") + "#^$" + resultSet.getInt("TrainingOffShore") + "#^$" + resultSet.getInt("OverheadOnSite") + "#^$" + resultSet.getInt("OverheadOffShore") + "#^$" + resultSet.getInt("Total") + "*@!";
            }
            // System.out.println("query..."+query);
            //   System.out.println("response.."+response);
        } catch (Exception sqe) {
            sqe.printStackTrace();
        } finally {
            try {
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
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }



        return response;
    }

    public String getProjectListByCustomer(NewAjaxHandlerAction ajaxHandlerAction, HttpServletRequest httpServletRequest) throws ServiceLocatorException {
        //String qsTitle = "";
        Statement statement = null;
        ResultSet resultSet = null;
        String query = "";
        Connection connection = null;
        String response = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            // query="SELECT DISTINCT tblProjects.ProjectName,ProjectStartDate,STATUS,Practice FROM tblProjects WHERE  CustomerId="+ajaxHandlerAction.getAccId()+" ";

            query = "SELECT DISTINCT tblProjects.ProjectName,tblProjects.ProjectStartDate,tblProjects.STATUS,tblProjects.Practice FROM tblProjects LEFT JOIN tblProjectContacts ON (tblProjects.Id = tblProjectContacts.ProjectId ) WHERE  CustomerId=" + ajaxHandlerAction.getAccId() + " ";
            Map rolesMap = (Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_ROLES);
            /* if (!rolesMap.containsValue("Admin")) {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_TEAM_LEAD).toString().equals("1") || httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_USER_MANAGER).toString().equals("1")) {
            Map teamMap = (Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP);
            teamMap.put(ajaxHandlerAction.getLoginId(), httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME).toString());
            
            if (!"".equalsIgnoreCase(ajaxHandlerAction.getPmoLoginId())) {
            query = query + " AND tblProjects.PMO = '" + ajaxHandlerAction.getPmoLoginId() + "'";
            } else {
            query = query + " AND tblProjects.PMO IN (" + DataSourceDataProvider.getInstance().getTeamLoginIdList(teamMap) + ") ";
            }
            
            
            } else {
            query = query + " AND tblProjects.PMO = '" + ajaxHandlerAction.getLoginId() + "'";
            }
            } else {
            if (ajaxHandlerAction.getPmoLoginId() != null && !"".equalsIgnoreCase(ajaxHandlerAction.getPmoLoginId())) {
            query = query + " AND tblProjects.PMO = '" + ajaxHandlerAction.getPmoLoginId() + "'";
            }
            }*/
            if (rolesMap.containsValue("Admin") || httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_PMO_ACTIVITY_ACCESS).toString().equals("1")) {


                if (ajaxHandlerAction.getPmoLoginId() != null && !"".equalsIgnoreCase(ajaxHandlerAction.getPmoLoginId())) {
                    query = query + " AND tblProjects.PMO = '" + ajaxHandlerAction.getPmoLoginId() + "'";
                }
            } else {
                if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_TEAM_LEAD).toString().equals("1") || httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_USER_MANAGER).toString().equals("1")) {
                    Map teamMap = (Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP);
                    teamMap.put(ajaxHandlerAction.getLoginId(), httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME).toString());

                    if (!"".equalsIgnoreCase(ajaxHandlerAction.getPmoLoginId())) {
                        query = query + " AND tblProjects.PMO = '" + ajaxHandlerAction.getPmoLoginId() + "'";
                    } else {
                        query = query + " AND tblProjects.PMO IN (" + DataSourceDataProvider.getInstance().getTeamLoginIdList(teamMap) + ") ";
                    }


                } else {
                    query = query + " AND tblProjects.PMO = '" + ajaxHandlerAction.getLoginId() + "'";
                }
            }

            if (ajaxHandlerAction.getProjectName() != null && !"".equals(ajaxHandlerAction.getProjectName()) && !"-1".equals(ajaxHandlerAction.getProjectName())) {
                query = query + "AND tblProjects.Id =" + ajaxHandlerAction.getProjectName() + " ";

            }

            if (!"".equalsIgnoreCase(ajaxHandlerAction.getStatus())) {
                query = query + " AND tblProjects.STATUS like '%" + ajaxHandlerAction.getStatus() + "%'";
            }

            if (!"".equalsIgnoreCase(ajaxHandlerAction.getProjectStartDate())) {
                ajaxHandlerAction.setProjectStartDate(DateUtility.getInstance().convertStringToMySQLDate(ajaxHandlerAction.getProjectStartDate()));
                query = query + " AND date(tblProjects.ProjectStartDate) like '%" + ajaxHandlerAction.getProjectStartDate() + "%'";

            }

            if (ajaxHandlerAction.getPracticeId() != null && !"".equalsIgnoreCase(ajaxHandlerAction.getPracticeId())) {
                query = query + " AND tblProjects.Practice = '" + ajaxHandlerAction.getPracticeId() + "'";
            }


            if (((!"".equals(ajaxHandlerAction.getPreAssignEmpId()))) && (ajaxHandlerAction.getPreAssignEmpId() != 0)) {

                query = query + " AND tblProjectContacts.ObjectId= " + ajaxHandlerAction.getPreAssignEmpId();

            }
            // System.out.println("query.."+query);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                String Name = resultSet.getString("ProjectName");
                String ProjectStartDate = resultSet.getString("ProjectStartDate");
                String Practice = resultSet.getString("Practice");
                String projectStatus = resultSet.getString("STATUS");
                if ("".equalsIgnoreCase(Name) || Name == null) {
                    Name = "-";
                }
                if ("".equalsIgnoreCase(Practice) || Practice == null) {
                    Practice = "-";
                }
                if ("".equalsIgnoreCase(projectStatus) || projectStatus == null) {
                    projectStatus = "-";
                }
                response = response + Name + "#^$" + ProjectStartDate + "#^$" + projectStatus + "#^$" + Practice + "*@!";

            }
            // System.out.println("query..."+query);
            //  System.out.println("response.."+response);
        } catch (Exception sqe) {
            sqe.printStackTrace();
        } finally {
            try {
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
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }



        return response;
    }

    public String getTaskNotes(int notesId) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String notes = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT Notes FROM tblTaskNotes WHERE Id=" + notesId);
            if (resultSet.next()) {
                notes = resultSet.getString("Notes");
            }

        } catch (SQLException se) {
            se.printStackTrace();
            throw new ServiceLocatorException(se);
        } finally {
            try {
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
            } catch (SQLException sqle) {
                sqle.printStackTrace();
                throw new ServiceLocatorException(sqle);
            }
        }
        return notes;
    }

    public String getIssuesDashBoardByStatus(String taskStartDate, String taskEndDate, String reportsTo, int graphId, HttpServletRequest httpServletRequest) throws ServiceLocatorException {

        String response = "";
        // Connection connection = null;
        CallableStatement callableStatement = null;
        Connection connection = null;
        String teamMembersKeys = "";
        Map teamMembersMapByLoginid = null;
        Map teamMembersMapByReportsTo = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            teamMembersMapByLoginid = (Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP);
            //   System.out.println("teamMembersMapByLoginid---"+teamMembersMapByLoginid);
            String loginId = (String) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID);
            //     System.out.println("reportsTo----" + reportsTo);
            if ("-1".equals(reportsTo)) {

                teamMembersKeys = getKeys(teamMembersMapByLoginid, ",");
                teamMembersKeys = teamMembersKeys.replaceAll("'", "");
                // System.out.println("teamMembersKeys main---"+teamMembersKeys);
                if (!"".equals(teamMembersKeys)) {
                    teamMembersKeys = teamMembersKeys + ',' + loginId;
                } else {
                    teamMembersKeys = loginId;
                }
                //   System.out.println("reportsTo-teamMembersKeys --- in if---" + teamMembersKeys);
            } else {
                String department = DataSourceDataProvider.getInstance().getDepartmentName(reportsTo);
                teamMembersMapByReportsTo = DataSourceDataProvider.getInstance().getMyTeamMembers(reportsTo, department);
                teamMembersKeys = getKeys(teamMembersMapByReportsTo, ",");
                teamMembersKeys = teamMembersKeys.replaceAll("'", "");
                if (!"".equals(teamMembersKeys)) {
                    teamMembersKeys = teamMembersKeys + ',' + reportsTo;
                } else {
                    teamMembersKeys = reportsTo;
                }
                // System.out.println("reportsTo-teamMembersKeys --- in else---" + teamMembersKeys);
            }
//            System.out.println("sd--" + DateUtility.getInstance().convertStringToMySQLDate(taskStartDate));
//            System.out.println("ed--" + DateUtility.getInstance().convertStringToMySQLDate(taskEndDate));

            callableStatement = connection.prepareCall("{call spTaskDashBoard(?,?,?,?,?,?)}");
            callableStatement.setInt(1, graphId);
            callableStatement.setString(2, "-1");
            callableStatement.setString(3, teamMembersKeys);
            callableStatement.setString(4, DateUtility.getInstance().convertStringToMySQLDate(taskStartDate));
            callableStatement.setString(5, DateUtility.getInstance().convertStringToMySQLDate(taskEndDate));
//            System.out.println("graphId--" + graphId);
            callableStatement.registerOutParameter(6, Types.VARCHAR);
            callableStatement.execute();


            response = callableStatement.getString(6);
//            System.out.println("response--" + response);




        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {

                if (callableStatement != null) {
                    callableStatement.close();
                    callableStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return response;
    }

    public String getTaskListByStatus(String taskStartDate, String taskEndDate, String reportsTo, String activityType, int graphId, HttpServletRequest httpServletRequest) throws ServiceLocatorException {

        String response = "";
        // Connection connection = null;
        CallableStatement callableStatement = null;
        Connection connection = null;
        String teamMembersKeys = "";

        Map teamMembersMapByLoginid = null;
        Map teamMembersMapByReportsTo = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();

            teamMembersMapByLoginid = (Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP);
            String loginId = (String) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID);
//             System.out.println("teamMembersMapByLoginid---"+teamMembersMapByLoginid);
//            System.out.println("reportsTo----" + reportsTo);
            if ("-1".equals(reportsTo)) {
                teamMembersKeys = getKeys(teamMembersMapByLoginid, ",");
                teamMembersKeys = teamMembersKeys.replaceAll("'", "");
                if (!"".equals(teamMembersKeys)) {
                    teamMembersKeys = teamMembersKeys + ',' + loginId;
                } else {
                    teamMembersKeys = loginId;
                }
                //   System.out.println("reportsTo-teamMembersKeys --- in if---" + teamMembersKeys);
            } else {
                String department = DataSourceDataProvider.getInstance().getDepartmentName(reportsTo);
                teamMembersMapByReportsTo = DataSourceDataProvider.getInstance().getMyTeamMembers(reportsTo, department);
                teamMembersKeys = getKeys(teamMembersMapByReportsTo, ",");
                teamMembersKeys = teamMembersKeys.replaceAll("'", "");
                if (!"".equals(teamMembersKeys)) {
                    teamMembersKeys = teamMembersKeys + ',' + reportsTo;
                } else {
                    teamMembersKeys = reportsTo;
                }
                //System.out.println("reportsTo-teamMembersKeys --- in else---" + teamMembersKeys);
            }

            //    System.out.println("teamMembersKeys-------1---" + teamMembersKeys);


            callableStatement = connection.prepareCall("{call spTaskDashBoard(?,?,?,?,?,?)}");

            callableStatement.setInt(1, graphId);
            callableStatement.setString(2, activityType);
            callableStatement.setString(3, teamMembersKeys);
            // System.out.println("555---activityType---" + activityType);
            callableStatement.setString(4, DateUtility.getInstance().convertStringToMySQLDate(taskStartDate));
            callableStatement.setString(5, DateUtility.getInstance().convertStringToMySQLDate(taskEndDate));
//            System.out.println("sd--" + DateUtility.getInstance().convertStringToMySQLDate(taskStartDate));
//            System.out.println("ed--" + DateUtility.getInstance().convertStringToMySQLDate(taskEndDate));
            callableStatement.registerOutParameter(6, Types.VARCHAR);
            callableStatement.execute();


            response = callableStatement.getString(6);

        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {

                if (callableStatement != null) {
                    callableStatement.close();
                    callableStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return response;
    }

    public String doPopulateTaskDetails(int taskId) throws ServiceLocatorException {
        StringBuffer reportsToBuffer = new StringBuffer("");
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        String response = "";

        try {
            connection = ConnectionProvider.getInstance().getConnection();


            String query = "SELECT Description FROM tblEmpTasks WHERE Id=" + taskId;

            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {


                response = response + resultSet.getString("Description");

            }
            if (response.equals("")) {
                response = "NoData";
            }



        } catch (SQLException sle) {
            sle.printStackTrace();
        } catch (ServiceLocatorException sle) {
            sle.printStackTrace();
        } finally {
            try {
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
                ex.printStackTrace();
            }
        }
        //  System.out.println("Team: "+reportsToBuffer.toString());
        return response;
    }
    /*
     * q-reviews
     */

    public int doSetQReviewManagerComments(int Id, String comments) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int count = 0;
        try {
            //  System.out.println("Id==="+Id);
            //  System.out.println("comments==="+comments);
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("UPDATE tblQuarterlyAppraisalsLines SET MgrCommnets=?  WHERE Id = ?");

            preparedStatement.setString(1, comments);
            preparedStatement.setInt(2, Id);
            count = preparedStatement.executeUpdate();
            //   System.out.println("count==="+count);
        } catch (Exception e) {
            System.err.println("Exception is-->" + e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (Exception sqle) {
                System.err.println("SQL Exception is-->" + sqle.getMessage());
            }
        }
        return count;

    }

    public int doSetQReviewPersonalityComments(int Id, String strengthsComments, String improvementsComments, String strengths, String improvements) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int count = 0;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("UPDATE tblQuarterlyAppraisals SET StrengthsComments=?,ImprovementsComments=?,Strength=?,Improvements=?  WHERE Id = ?");

            preparedStatement.setString(1, strengthsComments);
            preparedStatement.setString(2, improvementsComments);
            preparedStatement.setString(3, strengths);
            preparedStatement.setString(4, improvements);
            preparedStatement.setInt(5, Id);
            count = preparedStatement.executeUpdate();
            // System.out.println("count==="+count);
        } catch (Exception e) {
            System.err.println("Exception is-->" + e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (Exception sqle) {
                System.err.println("SQL Exception is-->" + sqle.getMessage());
            }
        }
        return count;

    }

    public int doSetQReviewGoalsComments(int Id, String shortTermGoalComments, String longTermGoalComments, String shortTermGoal, String longTermGoal) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int count = 0;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("UPDATE tblQuarterlyAppraisals SET ShortTeamGoalComments=?,LongTeamGoalComments=?,ShortTeamGoal=?,LongTeamGoal=?  WHERE Id = ?");

            preparedStatement.setString(1, shortTermGoalComments);
            preparedStatement.setString(2, longTermGoalComments);
            preparedStatement.setString(3, shortTermGoal);
            preparedStatement.setString(4, longTermGoal);
            preparedStatement.setInt(5, Id);
            count = preparedStatement.executeUpdate();
            // System.out.println("count==="+count);
        } catch (Exception e) {
            System.err.println("Exception is-->" + e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (Exception sqle) {
                System.err.println("SQL Exception is-->" + sqle.getMessage());
            }
        }
        return count;

    }

    public String empQuarterlyAppraisalSave(NewAjaxHandlerAction newAjaxHandlerAction, HttpServletRequest httpServletRequest) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        String resutMessage = "";

        String queryString = "{call spQuarterlyAppraisalSave(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

        try {
            String roleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            //  System.out.println("getRowCount==="+rowcount);
            // System.out.println("loginId==="+loginId);
            String id = httpServletRequest.getParameter("ids");
            String measurementId = httpServletRequest.getParameter("measurementId");
            String keyFactorId = httpServletRequest.getParameter("keyFactorId");
            String keyFactorWeightage = httpServletRequest.getParameter("keyFactorWeightage");
            String empDescription = httpServletRequest.getParameter("empDescription");
            String empRating = httpServletRequest.getParameter("empRating");
            String shortTermGoal = httpServletRequest.getParameter("shortTermGoal");
            String longTermGoal = httpServletRequest.getParameter("longTermGoal");
            String strength = httpServletRequest.getParameter("strength");
            String improvements = httpServletRequest.getParameter("improvements");
            int rowcount = newAjaxHandlerAction.getRowCount();
//                System.out.println("measurementId=="+measurementId);
//                    System.out.println("keyFactorId=="+keyFactorId);
//                    System.out.println("keyFactorWeightage=="+keyFactorWeightage);
//                    System.out.println("empDescription=="+empDescription);
//                    System.out.println("empRating=="+empRating);
//                System.out.println("shortTermGoal=="+shortTermGoal);
//                System.out.println("longTermGoal=="+longTermGoal);
//                System.out.println("strength=="+strength);
//                System.out.println("improvements=="+improvements);


            connection = ConnectionProvider.getInstance().getConnection();

            callableStatement = connection.prepareCall(queryString);
            callableStatement.setString(1, id);
            callableStatement.setInt(2, 0);
            callableStatement.setInt(3, Integer.parseInt(newAjaxHandlerAction.getEmpId()));
            callableStatement.setString(4, measurementId);
            callableStatement.setString(5, keyFactorId);
            callableStatement.setString(6, keyFactorWeightage);
            callableStatement.setString(7, empDescription);
            callableStatement.setString(8, empRating);
            callableStatement.setString(9, loginId);
            callableStatement.setTimestamp(10, DateUtility.getInstance().getCurrentMySqlDateTime());
            callableStatement.setString(11, newAjaxHandlerAction.getStatus());
            callableStatement.setDouble(12, 0);
            callableStatement.setDouble(13, 0);
            callableStatement.setInt(14, rowcount);
            callableStatement.setString(15, "");
            callableStatement.setString(16, "");
            callableStatement.setString(17, "");
            callableStatement.setDouble(18, 0);
            callableStatement.setDouble(19, 0);
            callableStatement.setInt(20, 0);
            callableStatement.setString(21, shortTermGoal);
            callableStatement.setString(22, "");
            callableStatement.setString(23, longTermGoal);
            callableStatement.setString(24, "");
            callableStatement.setString(25, strength);
            callableStatement.setString(26, "");
            callableStatement.setString(27, improvements);
            callableStatement.setString(28, "");
            callableStatement.setString(29, newAjaxHandlerAction.getQuarterly());
            callableStatement.setString(30, roleName);
            callableStatement.setString(31, "");
            callableStatement.registerOutParameter(32, Types.VARCHAR);
            callableStatement.executeUpdate();
            resutMessage = callableStatement.getString(32);
            // System.out.println("resutMessage==="+resutMessage);

        } catch (SQLException se) {
            se.printStackTrace();
            // resutMessage = "<font color=red>" + se.getMessage() + "</font>";
            throw new ServiceLocatorException(se);
        } finally {
            try {
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
            } catch (SQLException se) {
                se.printStackTrace();
                throw new ServiceLocatorException(se);
            }
        }
        return resutMessage;
    }

    public String quarterlyAppraisalEdit(int empId, int appraisalId, int lineId) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        String resutMessage = "";
        boolean isInserted = false;




        // String queryString = "INSERT INTO `tblInvestments` ( `Inv_Name`, `Country`, `StartDate`, `EndDate`, `TotalExpenses`,`Currency`,`Location`,`Description`,`AttachmentFileName`,`AttachmentLocation`,`CreatedBy`,InvestmentType)"
        //      + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        String queryString = "{call spGetFactoryDetailsEdit(?,?,?,?)}";

        try {
            connection = ConnectionProvider.getInstance().getConnection();

            callableStatement = connection.prepareCall(queryString);
            callableStatement.setInt(1, empId);
            callableStatement.setInt(2, appraisalId);
            callableStatement.setInt(3, lineId);
            callableStatement.registerOutParameter(4, Types.VARCHAR);
            callableStatement.executeUpdate();
            resutMessage = callableStatement.getString(4);


        } catch (SQLException se) {
            se.printStackTrace();
            // resutMessage = "<font color=red>" + se.getMessage() + "</font>";
            throw new ServiceLocatorException(se);
        } finally {
            try {
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
            } catch (SQLException se) {
                se.printStackTrace();
                throw new ServiceLocatorException(se);
            }
        }
        return resutMessage;
    }

    public int setQReviewEmpDescriptions(int Id, String comments) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int count = 0;
        try {
            // System.out.println("Id==="+Id);
            // System.out.println("comments==="+comments);
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("UPDATE tblQuarterlyAppraisalsLines SET EmpDescription=?  WHERE Id = ?");

            preparedStatement.setString(1, comments);
            preparedStatement.setInt(2, Id);
            count = preparedStatement.executeUpdate();
            // System.out.println("count==="+count);
        } catch (Exception e) {
            System.err.println("Exception is-->" + e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (Exception sqle) {
                System.err.println("SQL Exception is-->" + sqle.getMessage());
            }
        }
        return count;

    }
    /*
     * BDM Association start
     */
    
      public String getBdmReport(NewAjaxHandlerAction ajaxHandlerAction,HttpServletRequest httpServletRequest) throws ServiceLocatorException {
        //String qsTitle = "";
        Statement statement = null;
        ResultSet resultSet = null;
        String query="";
     Connection connection=null;
          String response = "";
           int count = 0;
  try {
    //  System.out.println("in service impl ajax");
           connection=ConnectionProvider.getInstance().getConnection();
                 //   query="SELECT DISTINCT tblProjects.ProjectName,ProjectStartDate,STATUS,Practice FROM tblProjects WHERE  CustomerId="+ajaxHandlerAction.getAccId()+" ";
//            String endDate = DateUtility.getInstance().getDateByMonthYear(ajaxHandlerAction.getToMonth(), ajaxHandlerAction.getToYear());
//            String startDate = DateUtility.getInstance().getDateLastMonthsByMonthYear(ajaxHandlerAction.getFromMonth(), ajaxHandlerAction.getFromYear(), 0);
//          query="SELECT DISTINCT Id,LoginId FROM tblLogUserAccess WHERE ";
         
           query="SELECT t1.Id,CONCAT(TRIM(t1.FName),'.',TRIM(t1.LName)) AS EmployeeName,t1.Email1 AS EmailId,t1.WorkPhoneNo AS PhoneNo,CONCAT(TRIM(t2.FName),'.',TRIM(t2.LName)) AS ReportsTo"
           + " FROM tblEmployee t1, tblEmployee t2 WHERE t1.ReportsTo = t2.LoginId AND t1.CurStatus='Active' AND t1.TitleTypeId='BDM' ";
           
//           query="SELECT Id,CONCAT(TRIM(FName),'.',TRIM(LName)) AS EmployeeName,Email1 AS EmailId,WorkPhoneNo AS PhoneNo,ReportsTo AS ReportsTo FROM"
//                    + " tblEmployee WHERE CurStatus='Active' AND TitleTypeId='BDM' ";
           
           
//SELECT COUNT(Id),DateAccessed FROM tblLogUserAccess WHERE  DateAccessed  >= '2017-02-24'AND  DateAccessed  <= '2017-02-28' GROUP BY DATE(DateAccessed);
        if (ajaxHandlerAction.getBdmId() != null && !"".equalsIgnoreCase(ajaxHandlerAction.getBdmId())) {
           
            query = query + " AND t1.Id  = '" + ajaxHandlerAction.getBdmId() + "'";

        }
        
//        
//        if (ajaxHandlerAction.getEndDate() != null && !"".equalsIgnoreCase(ajaxHandlerAction.getEndDate())) {
//            ajaxHandlerAction.setEndDate(DateUtility.getInstance().convertStringToMySQLDate(ajaxHandlerAction.getEndDate()));
//            query = query + "AND  DateAccessed  <= '" + ajaxHandlerAction.getEndDate() + "'";
//
//        }
//        
          query = query + "  ORDER BY EmployeeName";
        
       
        //  System.out.println("query.."+query);
          statement=connection.createStatement();
           resultSet=statement.executeQuery(query);
           while(resultSet.next()){
                count++;
               int Id=resultSet.getInt("Id");
               String employeeName=resultSet.getString("EmployeeName");
               String EmailId=resultSet.getString("EmailId");
               String PhoneNo=resultSet.getString("PhoneNo");
               String ReportsTo=resultSet.getString("ReportsTo");
//         String ReportsToName= DataSourceDataProvider.getInstance().getemployeenamebyloginId(ReportsTo);
             //  System.out.println("ReportsToName is----->"+ReportsToName);
               response=response+count+ "#^$" +Id+ "#^$"+employeeName+ "#^$"+EmailId+ "#^$"+PhoneNo+ "#^$"+ReportsTo+"*@!";
                
          
//               response=response+employeeName+"*@!";
           //    System.out.println("count is----->"+count);
             }
              response=response+"addto";

         response=response+count;
    //    System.out.println("query..."+query);
   //  System.out.println("response.."+response);
        } catch (Exception sqe) {
            sqe.printStackTrace();
        } finally {
            try {
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
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }



        return response;
    }
    
    
    
    public String getSalesTeamforBDMAssociate(String salesName) throws ServiceLocatorException {
        boolean isGetting = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        StringBuffer sb = new StringBuffer();
        String query = null;
        try {

            query = "SELECT CONCAT(TRIM(FName),'.',TRIM(LName)) AS FullName,Id FROM tblEmployee "
                    + "WHERE (LName LIKE '" + salesName + "%' OR FName LIKE '" + salesName + "%') AND DepartmentId='Sales'"
                    + "AND CurStatus='Active'";

            System.out.println("query-->"+query);
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            int count = 0;
            sb.append("<xml version=\"1.0\">");
            sb.append("<EMPLOYEES>");
            while (resultSet.next()) {
                sb.append("<EMPLOYEE><VALID>true</VALID>");

                if (resultSet.getString(1) == null || resultSet.getString(1).equals("")) {
                    sb.append("<NAME>NoRecord</NAME>");
                } else {
                    String title = resultSet.getString(1);
                    if (title.contains("&")) {
                        title = title.replace("&", "&amp;");
                    }
                    sb.append("<NAME>" + title + "</NAME>");
                }
                //sb.append("<NAME>" +resultSet.getString(1) + "</NAME>");
                sb.append("<EMPID>" + resultSet.getInt(2) + "</EMPID>");
                sb.append("</EMPLOYEE>");
                isGetting = true;
                count++;
            }

            if (!isGetting) {
                //sb.append("<EMPLOYEES>" + sb.toString() + "</EMPLOYEES>");
                //} else {
                isGetting = false;
                //nothing to show
                //  response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                sb.append("<EMPLOYEE><VALID>false</VALID></EMPLOYEE>");
            }
            sb.append("</EMPLOYEES>");
            sb.append("</xml>");

            //  System.out.println(sb.toString());
        } catch (SQLException sqle) {
            throw new ServiceLocatorException(sqle);
        } finally {
            try {
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
            } catch (SQLException sql) {
                //System.err.print("Error :"+sql);
            }

        }
        // System.out.println("the string "+sb.toString());
        return sb.toString();
    }
    
    
    
    
    public String getSalesTeamDetails(String teamMemberId,String bdmId) throws ServiceLocatorException {
     StringBuffer stringBuffer  = new StringBuffer();
     Connection connection = null;
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String activityDetails = "";
        String queryString;
        int i = 0;
        JSONObject subJson = null;;
        try {
//            queryString = "SELECT  CONCAT(tblEmployee.FName,' ',tblEmployee.MName,'.',tblEmployee.LName) AS EmployeeName,ReviewType,EmpId,ReviewTypeId,tblEmpReview.Status,EmpComments,tblEmpReview.CreatdBy,tblEmpReview.CreatedDate,AttachmentName,AttachmentLocation,ReviewName,tblEmpReview.Id AS Id,HrComments,TLComments,ReviewDate,TLRating,HRRating,MaxPoints,ApprovedBy1,Approver1Date,Approver2Date,ApprovedBy2,Approver3Date,ApprovedBy3,OnSiteMgrRating,OnSiteMgrComments,OnSiteMgrStatus,HRStatus,BillingAmount,LogosCount,tblEmpReview.UserId as UserId FROM tblEmpReview JOIN tblLkReviews ON (ReviewTypeId = tblLkReviews.Id) JOIN tblEmployee ON (tblEmpReview.UserId = tblEmployee.LoginId) WHERE tblEmpReview.Id=" + reviewId;
            
          queryString =  "SELECT CONCAT(TRIM(FName),'.',TRIM(LName)) AS EmployeeName,tblBDMAssociates.Id AS Id,tblBDMAssociates.STATUS AS Status,tblBDMAssociates.TeamMemberId AS TeamMemberId"
                      + " FROM tblEmployee LEFT JOIN tblBDMAssociates ON(tblEmployee.Id=tblBDMAssociates.TeamMemberId) WHERE tblBDMAssociates.TeamMemberId='"+teamMemberId+"' AND tblBDMAssociates.BdmId='"+bdmId+"'";

//System.out.println("queryString-->"+queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            if (resultSet.next()) {
                subJson = new JSONObject();
                subJson.put("Id", resultSet.getInt("Id"));
                subJson.put("EmployeeName", resultSet.getString("EmployeeName"));
                subJson.put("Status", resultSet.getString("Status"));
                subJson.put("TeamMemberId", resultSet.getString("TeamMemberId"));
               
            }



        } catch (Exception sqe) {
            sqe.printStackTrace();
        } finally {
            try {
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
            } catch (SQLException sqle) {
            }
        }
        return subJson.toString();
    }
    
    
    
    public int addSalesToBdm(NewAjaxHandlerAction newAjaxHandlerAction) throws ServiceLocatorException {
      //  System.out.println("in addSalesToBdm");
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            CallableStatement callableStatement = null;
            int isInserted = 0;
         
         
            // queryString = "insert into tblEmpReview (ReviewTypeId,EmpComments,AttachmentName,AttachmentLocation,EmpId,CreatdBy,ReviewName,ReviewDate,Status,TLRating,HRRating,MaxPoints,UserId,TLComments,HrComments,ApprovedBy2,Approver2Date) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            try {
                 String emailId=DataSourceDataProvider.getInstance().getEmailIdForEmployee(Integer.parseInt(newAjaxHandlerAction.getPreAssignSalesId()));
                String workPhn=DataSourceDataProvider.getInstance().getWorkPhNoById(newAjaxHandlerAction.getPreAssignSalesId());
             connection = ConnectionProvider.getInstance().getConnection();
               callableStatement = connection.prepareCall("{call spBDMAssociates(?,?,?,?,?,?,?)}");
              callableStatement.setString(1,newAjaxHandlerAction.getPreAssignSalesId());
                callableStatement.setString(2,newAjaxHandlerAction.getBdmId());
                callableStatement.setString(3,emailId);
                callableStatement.setString(4,"Active");
                callableStatement.setString(5,workPhn);
                callableStatement.setString(6,newAjaxHandlerAction.getCreatedBy());
                callableStatement.setString(7,newAjaxHandlerAction.getCreatedBy());
                 isInserted = callableStatement.executeUpdate();

                // System.out.println("isInserted  " + isInserted);
            } catch (SQLException se) {
                se.printStackTrace();
                throw new ServiceLocatorException(se);
            } finally {
                try {
                    if (preparedStatement != null) {
                        preparedStatement.close();
                        preparedStatement = null;
                    }
                    if (connection != null) {
                        connection.close();
                        connection = null;
                    }
                } catch (SQLException se) {
                    throw new ServiceLocatorException(se);
                }
            }

           
        
        return isInserted;

    }
    
   
    public boolean checkSalesAgainstBdm(NewAjaxHandlerAction newAjaxHandlerAction) throws ServiceLocatorException {
   // System.out.println("in impl of resourcename");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
 String strConsId = "";
 String responseString="";
          
         
     //       String strCategory = "";
        boolean isInserted = true;
        String teamMemberId = null;
        String Status = null;
        String BdmId = null;
        String queryString = "SELECT TeamMemberId,Status,BdmId FROM tblBDMAssociates";
       // System.out.println("queryString----->"+queryString);
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
                  //   System.out.println("getResource Id isss---->"+newAjaxHandlerAction.getBdmId());
            while (resultSet.next()) {
                 teamMemberId=resultSet.getString("TeamMemberId");
                 Status=resultSet.getString("Status");
                 BdmId=resultSet.getString("BdmId");
              //  System.out.println("empName in while loop is---->"+teamMemberId); 
              //  System.out.println("Status in while loop is---->"+Status); 
              //  System.out.println("BdmId in while loop is---->"+BdmId); 
                
               // System.out.println("final member is--->"+teamMemberId);
              //  System.out.println("adminAction.getPreAssignSalesId() is--->"+newAjaxHandlerAction.getPreAssignSalesId());
              //  System.out.println("adminAction.getPreAssignSalesId() is--->"+newAjaxHandlerAction.getPreAssignSalesId());
               // System.out.println("adminAction.getPreAssignSalesId() is--->"+newAjaxHandlerAction.getBdmId());
                      if(teamMemberId.equals(newAjaxHandlerAction.getPreAssignSalesId()) && Status.equals("Active") && BdmId.equals(newAjaxHandlerAction.getBdmId()) ){
//                          if(Status.equals("Active")){
//                              if(BdmId.equals(newAjaxHandlerAction.getBdmId())){
                         // System.out.println("in equals case");
                  responseString = "sorry! the Sales has been already associated";
                  isInserted = false;
                break;
//              }
//                          }
                      }
            }
          
             

          //  System.out.println("empName is---->"+teamMemberId);
 
        preparedStatement.execute();
         //   System.out.println("isInserted for resourcename is---->"+isInserted);
        } catch (SQLException se) {
            se.printStackTrace();
            throw new ServiceLocatorException(se);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException se) {
                throw new ServiceLocatorException(se);
            }
        }

        return isInserted;

    }
    
    
    
    public String checkBdmAddedName(NewAjaxHandlerAction newAjaxHandlerAction) throws ServiceLocatorException {
        //String qsTitle = "";
        Statement statement = null;
        ResultSet resultSet = null;
        String query="";
     Connection connection=null;
         PreparedStatement preparedStatement = null;
          String  assignedBdmId = null;
          String response = "";
           int count = 0;
  try {
      
     // System.out.println("in else case");
                       String queryString = "SELECT TeamMemberId,BdmId,Status FROM tblBDMAssociates";
                       connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
          String  teamMemberId=resultSet.getString("TeamMemberId");
          assignedBdmId=resultSet.getString("BdmId");
           String  status=resultSet.getString("Status");
          
              //  System.out.println("empName in while loop is---->"+teamMemberId); 
                
              //  System.out.println("final member is--->"+teamMemberId);
              //  System.out.println("adminAction.getPreAssignSalesId() is--->"+newAjaxHandlerAction.getPreAssignSalesId());
                      if(teamMemberId.equals(newAjaxHandlerAction.getPreAssignSalesId()) && status.equals("Active")){
                         // System.out.println("in equals case");
                 
               
                break;
              }
                     
            }
             //System.out.println("assignedBdmId final is---->"+assignedBdmId);
      String assignedBdmName =  DataSourceDataProvider.getInstance().getEmployeeNameByEmpNo(Integer.parseInt(assignedBdmId));     
                   response=     "<font size='2' color='red'>Resource Name has been alerady assocaited with BDM '"+assignedBdmName+"'</font>";
                        // System.out.println("getResponseString is---->"+response);
      
      
    //  System.out.println("in service impl ajax");
  
    //    System.out.println("query..."+query);
   //  System.out.println("response.."+response);
        } catch (Exception sqe) {
            sqe.printStackTrace();
        } finally {
            try {
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
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }



        return response;
    }
    
    
    public boolean updateBdmTeam(NewAjaxHandlerAction newAjaxHandlerAction) throws ServiceLocatorException {
        //System.out.println("in updateBdmTeam------");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        boolean isUpdated = false;
        String queryString = "";

       // System.out.println("newAjaxHandlerAction.getTeamMemberId()----->"+newAjaxHandlerAction.getTeamMemberId());
       // System.out.println("newAjaxHandlerAction.getTeamMemberStatus()----->"+newAjaxHandlerAction.getTeamMemberStatus());
      //  System.out.println("newAjaxHandlerAction.getBdmId()----->"+newAjaxHandlerAction.getBdmId());
        //queryString = "update tblEmpReview set ReviewTypeId=?,EmpComments=?,ModifiedBy=?,ModifiedDate=?,ReviewName=?,ReviewDate=?,Status=?,TLComments=?,TLRating=?,HRRating=?,HrComments=? where Id=?";
        //if(roleName.equalsIgnoreCase("Employee"))
        String emailId=DataSourceDataProvider.getInstance().getEmailIdForEmployee(Integer.parseInt(newAjaxHandlerAction.getTeamMemberId()));
                String workPhn=DataSourceDataProvider.getInstance().getWorkPhNoById(newAjaxHandlerAction.getTeamMemberId());
               // System.out.println("emailId----->"+emailId);
                //System.out.println("workPhn----->"+workPhn);
        queryString = "update tblBDMAssociates set TeamMemberId=?,BdmId=?,EmailId=?,STATUS=?,PhoneNumber=?,ModifiedBy=?,ModifiedDate=? where Id=?";
       // System.out.println("queryString---->"+queryString);
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            //  preparedStatement.setString(1,ajaxHandlerAction.getOverlayReviewType());
            preparedStatement.setString(1, newAjaxHandlerAction.getTeamMemberId());
            preparedStatement.setString(2, newAjaxHandlerAction.getBdmId());
            preparedStatement.setString(3, emailId);
            preparedStatement.setString(4, newAjaxHandlerAction.getTeamMemberStatus());
            preparedStatement.setString(5, workPhn);
            preparedStatement.setString(6,newAjaxHandlerAction.getCreatedBy());
            preparedStatement.setTimestamp(7,DateUtility.getInstance().getCurrentMySqlDateTime());
            preparedStatement.setInt(8, newAjaxHandlerAction.getId());
          

            isUpdated = preparedStatement.execute();
             //System.out.println("queryString---->"+queryString);
           // System.out.println("isUpdated");
        } catch (SQLException se) {
            se.printStackTrace();
            throw new ServiceLocatorException(se);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException se) {
                throw new ServiceLocatorException(se);
            }
        }

        return isUpdated;

    }
    
    
     public boolean checkBdmStatus(NewAjaxHandlerAction newAjaxHandlerAction) throws ServiceLocatorException {
    System.out.println("in impl of checkBdmStatus");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
 String strConsId = "";
 String responseString="";
          
         
     //       String strCategory = "";
        boolean isInserted = true;
        String teamMemberId = null;
        String Status = null;
        String BdmId = null;
        String queryString = "SELECT TeamMemberId,Status,BdmId FROM tblBDMAssociates where BdmId='"+newAjaxHandlerAction.getBdmId()+"'";
      //  System.out.println("queryString----->"+queryString);
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
                    // System.out.println("newAjaxHandlerAction.getPreAssignSalesId() Id isss---->"+newAjaxHandlerAction.getPreAssignSalesId());
                    // System.out.println("newAjaxHandlerAction.getBdmId() Id isss---->"+newAjaxHandlerAction.getBdmId());
            while (resultSet.next()) {
                 teamMemberId=resultSet.getString("TeamMemberId");
                 Status=resultSet.getString("Status");
                 BdmId=resultSet.getString("BdmId");
                      if(teamMemberId.equals(newAjaxHandlerAction.getPreAssignSalesId()) && BdmId.equals(newAjaxHandlerAction.getBdmId()) && Status.equals("Inactive")){
//                          if(Status.equals("Active")){
//                              if(BdmId.equals(newAjaxHandlerAction.getBdmId())){
                         // System.out.println("in equals case of checkBdmStatus");
                  responseString = "sorry! the Sales has been already associated";
                  isInserted = false;
                break;
//              }
//                          }
                      }
            }
          
             

           // System.out.println("empName is---->"+teamMemberId);
 
        preparedStatement.execute();
           // System.out.println("isInserted for resourcename is---->"+isInserted);
        } catch (SQLException se) {
            se.printStackTrace();
            throw new ServiceLocatorException(se);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException se) {
                throw new ServiceLocatorException(se);
            }
        }

        return isInserted;

    }

    /*
     * BDM Association end
     */
     
       /*Author :Teja Kadamanti
     * Date : 04/20/2017
     * Description: Multiple Projects Employee Details 
     * start
     * 
     */
     
 public String getMultipleProjectsEmployeeList(NewAjaxHandlerAction newAjaxHandlerAction,HttpServletRequest httpServletRequest) throws ServiceLocatorException {
        //String qsTitle = "";
        Statement statement = null;
        ResultSet resultSet = null;
        String query = "";
        Connection connection = null;
        String response = "";
        String department="";
        String Practice="";
        String subPractice="";
                try {
            connection = ConnectionProvider.getInstance().getConnection();
            query = "SELECT ObjectId,CONCAT(FName,'',MName,'.',LName) AS NAME,DepartmentId,PracticeId,SubPractice,COUNT(tblProjectContacts.Id) AS projectCount FROM tblProjectContacts LEFT JOIN tblEmployee ON tblProjectContacts.ObjectId=tblEmployee.Id WHERE STATUS='Active' AND  CurStatus='Active'  ";
     
             if (((!"".equals(newAjaxHandlerAction.getPreAssignEmpId()))) && (newAjaxHandlerAction.getPreAssignEmpId() != 0)) {

            query = query + " AND tblProjectContacts.ObjectId= '" + newAjaxHandlerAction.getPreAssignEmpId()+"'";

        }
                if (((!"".equals(newAjaxHandlerAction.getDepartmentId()))) && (!"-1".equals(newAjaxHandlerAction.getDepartmentId()) )) {

            query = query + " AND tblEmployee.DepartmentId = '" + newAjaxHandlerAction.getDepartmentId()+"'";

        }
                
                 if (((!"".equals(newAjaxHandlerAction.getPracticeId()))) && (!"-1".equals(newAjaxHandlerAction.getPracticeId()) )) {

            query = query + " AND tblEmployee.PracticeId = '" + newAjaxHandlerAction.getPracticeId()+"'";

        }
                    if (((!"".equals(newAjaxHandlerAction.getSubPracticeId()))) && (!"-1".equals(newAjaxHandlerAction.getSubPracticeId()) )) {

            query = query + " AND tblEmployee.SubPractice = '" + newAjaxHandlerAction.getSubPracticeId()+"'";

        }
             
               query = query +" GROUP BY ObjectId HAVING COUNT(tblProjectContacts.Id)>1 ORDER BY  FName";
              // System.out.println("query..."+query);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                if("".equals(resultSet.getString("DepartmentId"))){
                    department="-";
                }else{
                  department= resultSet.getString("DepartmentId"); 
                }
                  if("".equals(resultSet.getString("PracticeId"))){
                    Practice="-";
                }else{
                  Practice= resultSet.getString("PracticeId"); 
                }
                   if("".equals(resultSet.getString("SubPractice"))){
                    subPractice="-";
                }else{
                  subPractice= resultSet.getString("SubPractice"); 
                }
                response = response + resultSet.getInt("ObjectId") + "#^$" + resultSet.getString("NAME") + "#^$" +  department + "#^$" +  Practice + "#^$" +subPractice + "#^$"+resultSet.getInt("projectCount") + "*@!";
             }
         
        } catch (Exception sqe) {
            sqe.printStackTrace();
        } finally {
            try {
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
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }



        return response;
    }
    
    
       public String multipleProjectsEmployeeListDetails(int preAssignedEmpId,HttpServletRequest httpServletRequest) throws ServiceLocatorException {
        //String qsTitle = "";
        Statement statement = null;
        ResultSet resultSet = null;
        String query = "";
        Connection connection = null;
        int totalUtilization=0;
        String response = "";
                try {
            connection = ConnectionProvider.getInstance().getConnection();
            query = "SELECT tblCrmAccount.NAME,tblProjects.ProjectName,DATE(StartDate) AS StartDate,CASE WHEN EndDate IS NOT NULL THEN DATE(EndDate) ELSE '-' END AS EndDate,CASE WHEN EmpProjStatus!='-1' THEN EmpProjStatus ELSE '-' END AS EmpProjStatus,Billable,Utilization FROM tblProjectContacts JOIN  tblEmployee ON tblProjectContacts.ObjectId=tblEmployee.Id JOIN tblCrmAccount ON tblCrmAccount.Id=tblProjectContacts.AccountId JOIN tblProjects ON tblProjects.Id=tblProjectContacts.ProjectId  WHERE tblProjectContacts.STATUS='Active' AND  CurStatus='Active' AND ObjectId="+preAssignedEmpId;
          statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
              totalUtilization = totalUtilization + Integer.parseInt(resultSet.getString("Utilization"));
                response = response + resultSet.getString("NAME") + "#^$" + resultSet.getString("ProjectName") + "#^$" +  resultSet.getString("StartDate") + "#^$" +  resultSet.getString("EndDate") + "#^$" +  resultSet.getString("EmpProjStatus") + "#^$" +  resultSet.getString("Billable") + "#^$" +  resultSet.getString("Utilization") + "*@!";
             }
            response=response+"addTo"+totalUtilization;
       } catch (Exception sqe) {
            sqe.printStackTrace();
        } finally {
            try {
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
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }



        return response;
    }
	
        /*Author :Teja Kadamanti
     * Date : 04/20/2017
     * Description: Multiple Projects Employee Details 
     * end
     * 
     */
       
       
       
        /*
        * Author :Triveni
        */
       public String getConsultantNames(String query) throws ServiceLocatorException {
        boolean isGetting = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        StringBuffer sb = new StringBuffer();
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            int count = 0;
            sb.append("<xml version=\"1.0\">");
            sb.append("<CONSULTANTS>");
            while (resultSet.next()) {
                sb.append("<CONSULTANT><VALID>true</VALID>");

                if (resultSet.getString(1) == null || resultSet.getString(1).equals("")) {
                    sb.append("<NAME>NoRecord</NAME>");
                } else {
                    String title = resultSet.getString(1);
                    if (title.contains("&")) {
                        title = title.replace("&", "&amp;");
                    }
                    sb.append("<NAME>" + title + "</NAME>");
                }
                //sb.append("<NAME>" +resultSet.getString(1) + "</NAME>");
               // sb.append("<EMPLOGINID>" + resultSet.getString(2) + "</EMPLOGINID>");
                sb.append("</CONSULTANT>");
                isGetting = true;
                count++;
            }

            if (!isGetting) {
                //sb.append("<EMPLOYEES>" + sb.toString() + "</EMPLOYEES>");
                //} else {
                isGetting = false;
                //nothing to show
                //  response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                sb.append("<CONSULTANT><VALID>false</VALID></CONSULTANT>");
            }
            sb.append("</CONSULTANTS>");
            sb.append("</xml>");
        } catch (SQLException sqle) {
            throw new ServiceLocatorException(sqle);
        } finally {
            try {
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
            } catch (SQLException sql) {
                //System.err.print("Error :"+sql);
            }

        }
        // System.out.println(sb.toString());
        return sb.toString();
    }
/*
        * Author :Triveni
        */
    @Override
    public boolean fileUploadWorkAuthorizationCopy(NewAjaxHandlerAction newAjaxHandlerAction) throws ServiceLocatorException {
      Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
       // System.out.println("start addTaxAssumption");
        boolean isInserted = false;
        // String queryString = "INSERT INTO tblEmpTaxExemptionDetails(EmpId,ExemptionId,AppliedBy,SavingsAmount,AttachmentName,AttachmentLocation,PaymentDate) values(?,?,?,?,?,?,?)";
       String queryString = "INSERT INTO tblRec(WorkAuthAttachName,WorkAuthAttachLocation) values(?,?) WHERE RequirementId = ? AND ConsultantId = ?";

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
             preparedStatement.setString(1, newAjaxHandlerAction.getFileFileName());
             preparedStatement.setString(2, newAjaxHandlerAction.getAttachmentLocation());

            preparedStatement.setInt(3, newAjaxHandlerAction.getRequirementId());
            preparedStatement.setString(4, newAjaxHandlerAction.getConsultantId());
            // preparedStatement.setString(3, ajaxHandlerAction.getOverLayStatus());
           
          
            isInserted = preparedStatement.execute();
           // System.out.println("end addTaxAssumption");
        } catch (SQLException se) {
            se.printStackTrace();
            throw new ServiceLocatorException(se);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException se) {
                throw new ServiceLocatorException(se);
            }
        }

        return isInserted;

    }
    /*
        * Author :Triveni
        */
      @Override
    public boolean fileUploadDlCopyAttached(NewAjaxHandlerAction newAjaxHandlerAction) throws ServiceLocatorException {
      Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
       // System.out.println("start addTaxAssumption");
        boolean isInserted = false;
        // String queryString = "INSERT INTO tblEmpTaxExemptionDetails(EmpId,ExemptionId,AppliedBy,SavingsAmount,AttachmentName,AttachmentLocation,PaymentDate) values(?,?,?,?,?,?,?)";
      String queryString = "INSERT INTO tblRec(DlCopyAttachName,DlCopyAttachLocation) values(?,?) WHERE RequirementId = ? AND ConsultantId = ?";

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
             preparedStatement.setString(1, newAjaxHandlerAction.getFileFileName());
             preparedStatement.setString(2, newAjaxHandlerAction.getAttachmentLocation());

            preparedStatement.setInt(3, newAjaxHandlerAction.getRequirementId());
            preparedStatement.setString(4, newAjaxHandlerAction.getConsultantId());
            // preparedStatement.setString(3, ajaxHandlerAction.getOverLayStatus());
           
            isInserted = preparedStatement.execute();
           // System.out.println("end addTaxAssumption");
        } catch (SQLException se) {
            se.printStackTrace();
            throw new ServiceLocatorException(se);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException se) {
                throw new ServiceLocatorException(se);
            }
        }

        return isInserted;

    }

}
