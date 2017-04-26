<%@page import="java.util.Map"%>
<%@page import="com.mss.mirage.cre.CreReviewVTO"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>




<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    
    <%-- START HEAD SECTION --%>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <title>Hubble Organization Portal :: Cre Instructions</title>
        
        <script type="text/JavaScript">
            function checkFields() {
                
                var loginId = document.getElementById("loginId").value;
                var answer = document.getElementById("prefAnswer").value;
                var question = document.getElementById("prefQuestionId").value;
                if(question == '-1' || answer == '' || loginId == '') {
                    alert('Enter Mandatory Fields');
                    return false;
                }else return true;
            }
             window.history.forward();
             function noBack() { 
                  window.history.forward(); 
             }
              function disableStart(){
                 
                 document.getElementById("startExamButton").disabled = true;
                 document.getElementById("loadMsg").disabled = false;
                 document.getElementById("loadMsg").style.display = 'block';
                 
                 document.forms["startExamForm"].submit();
             }
        </script>
    </head>
    <%-- END HEAD SECTION --%>
    
    <%-- START BODY SECTION --%>
    <body class="bodyGeneral" oncontextmenu="return false;" onload="noBack();" onpageshow="if (event.persisted) noBack();">
        
        <%--//START MAIN TABLE : Table for template Structure--%>
        <table class="templateTable1000x580" align="center" cellpadding="0" cellspacing="0" border="0">
            
            <%--//START HEADER : Record for Header Background and Mirage Logo--%>
            <tr class="headerBg">
                <td valign="top">
                    <s:include value="/includes/template/McertHeader.jsp"/>
                </td>
            </tr>
            <%--//END HEADER : Record for Header Background and Mirage Logo--%>
         
            <%--//START DATA RECORD : Record for LeftMenu and Screen Content--%>
            <tr>
                <td>
                    <table class="innerTable1000x515" cellpadding="0" cellspacing="0" border="0">
                    <%--    <tr>
                            <!-- START Animated Header -->
                            <td valign="top" height="150px" colspan="2">
                                <object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0" width="1000" height="150" id="header" align="middle">
                                    <param name="allowScriptAccess" value="sameDomain" />
                                    <param name="movie" value="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/flash/mirageBanner.swf" />
                                    <param name="quality" value="high" />
                                    <param name="bgcolor" value="#000000" />
                                    <embed src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/flash/mirageBanner.swf" quality="high" bgcolor="#000000" width="1000" height="150" name="header" align="middle" allowScriptAccess="sameDomain" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />
                                </object>    
                            </td>
                            <!-- END Animated Header -->
                        </tr> --%>
                        
                      <%--  <tr>
                            <!-- START Horizonatla Bar Below the Animated Header -->
                            <td class="footerBg" colspan="2">
                                <img alt="border" 
                                     src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" 
                                     width="1000px" 
                                     height="13px" border="0">
                            </td>
                            <!-- END Horizonatla Bar Below the Animated Header -->
                        </tr> --%>
                        <tr>
                            <td class="topBorder" height="370px" valign="top" background="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/miragewatermark_850X600.gif">
                                <br><br>
                                <table cellpadding="0" cellspacing="0" border="0" width="500px" align="center"  class="cellBorder">
                                    <tr>
                                        <td valign="top">
                                            <table width="500px" cellpadding="2" cellspacing="2" border="0" align="center">
                                                <!--START ANIMATED TEXT ROW -->
                                                <tr>
                                                    <td colspan="2"><h3 class="bgColor" align="center"><script type="text/JavaScript" src="<s:url value="/includes/javascripts/mcertification/McertInstructionsAnimation.js"/>"></script>
                                                            
                                                        </h3>
                                                    </td>
                                                </tr>
                                                <!--END ANIMATED TEXT ROW -->
                                               <tr>
                                                    <td align="right"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/rightArrow_16x6.gif"></td>
                                                    <td ><font size="2px" color="#1B425E">Please don't refresh the page. !  </font></td>
                                                </tr>
                                               <%-- <tr>
                                                    <td align="right"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/rightArrow_16x6.gif"></td>
                                                    <td ><font size="2px" color="#1B425E">Test contains <%=session.getAttribute(ApplicationConstants.SESSION_CRE_TOTAL_QUESTIONS)%> questions.</font></td>
                                                </tr> --%>
                                                 <tr>
                                                    <td align="right"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/rightArrow_16x6.gif"></td>
                                                    <td ><font size="2px" color="#1B425E">Each question carry 1 mark, no negative marks. ! </font></td>
                                                </tr>
                                           <%--      <tr> --%>
                                                     <%!
                                                     List subtopocNamesList = null;
                                                     int subtopicwiseQuestCount;
                                                     List examsList=null;
                                                      %>
                                                  <%--  <td align="right"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/rightArrow_16x6.gif"></td>
                                                    <td ><font size="2px" color="#1B425E">Time Allowed <%=session.getAttribute(ApplicationConstants.SESSION_CRE_DURATION)%> minutes from the time of exam starts.</font></td> --%>
                                                     <%     //subtopocNamesList = (List)session.getAttribute(ApplicationConstants.SESSION_CRE_SUBTOPICS);
                                                            //subtopicwiseQuestCount = Integer.parseInt(session.getAttribute(ApplicationConstants.SESSION_CRE_TOTAL_QUESTIONS).toString())/subtopocNamesList.size();
                                                            examsList=(List)session.getAttribute(ApplicationConstants.SESSION_MCERT_ExamIds_List);
                                                            System.out.println("examsList-->"+examsList.size());
                                                            %>
                                             <%--   </tr> --%>
                                                
                                             <%--   <tr>
                                                    <td align="right"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/rightArrow_16x6.gif"></td>
                                                    <td ><font size="2px" color="#1B425E">There will be <%=subtopocNamesList.size()%> Sections. Each will have <%=subtopicwiseQuestCount%> questions. </font></td>

                                                </tr> --%>
                                                 
                                                <tr>
                                                    <td align="right"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/rightArrow_16x6.gif"></td>
                                                    <td ><font size="2px" color="#1B425E">There will be test on below <%out.println(examsList.size()); %> exam's. </font>
                                                        
                                                       </td>
                                                </tr>
                                                <tr>
                                                    <td></td><td>      
                                                           <%
                                                           CreReviewVTO creReviewVTO = null;
                                                           for(int i=0;i<examsList.size();i++) {
                                                               //System.out.println("hiiii");
                                                               creReviewVTO = (CreReviewVTO)examsList.get(i);
                                                                //out.println((i+1)+" "+creReviewVTO.getExamTypeName());
    %>                                                
                                                          <ul> 
                                                           <li><font size="2px" color="#1B425E"><%out.println(creReviewVTO.getExamTypeName()+" contains "+creReviewVTO.getTotalQuestions()+" questions.");%></font></li>
                                                           <li><font size="2px" color="#1B425E"><%out.println("Minimum marks "+creReviewVTO.getMinMarks());%></font></li>
                                                           <li><font size="2px" color="#1B425E"><%out.println("Total Duration for the "+creReviewVTO.getExamTypeName()+" will be "+creReviewVTO.getTotDuration()+" minutes");%></font></li>
                                                           <li><font size="2px" color="#1B425E"><%out.println("Maximum marks "+creReviewVTO.getTotalQuestions());%></font></li>
                                                           <li>
                                                               <font size="2px" color="#1B425E"><%out.println("The questions in "+creReviewVTO.getExamTypeName()+" will be based on below topics");%></font>
                                                               <ul>
                                                                   <%
                                                                   Map subTopicsMap = creReviewVTO.getSubTopicsMap();
                                                                  // for(int j=0;j<subTopicsMap.size();j++) {
                                                                   int j=0;
                                                                   for (Object key : subTopicsMap.keySet()) {
                                                                       %>
                                                                       <li><font size="1.5px" color="#1B425E"><%out.println((String)subTopicsMap.get(key)+" contains "+(creReviewVTO.getTotalQuestions()/subTopicsMap.size())+" questions.");%></font></li>
                                                                   <%j++;
                                                                       
}
%>
                                                               </ul>
                                                           
                                                           </li>
                                                          </ul> 
                                                           <%
                                                           //System.out.println("i--->"+i);
                                                           //System.out.println("examsList i--->"+(examsList.size()-1));
                                                            if(i<(examsList.size()-1)){%>
                                                      <%--  <font size="2px" color="#1B425E"><%out.println("Mandatory to PASS "+creReviewVTO.getExamTypeName()+" So that you can able to move next exam!");%></font>  --%>
                                                      <font size="2px" color="#1B425E"><%out.println("Once the "+creReviewVTO.getExamTypeName()+" passed then you will get the next exam link on the result page.");%></font> 
                                                        
                                                        <%}}%>
                                                        </td>
                                                </tr>
                                                <tr>
                                                    <td align="right"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/rightArrow_16x6.gif"></td>
                                                    <td ><font size="2px" color="#1B425E">All questions are OBJECTIVE multiple choice. Each question will have atleast 4 choices.</font></td>
                                                 </tr>
                                               
                                              <%--   <tr>
                                                    <td align="right"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/rightArrow_16x6.gif"></td>
                                                    <td ><font size="2px" color="#1B425E">Minimum marks of this test is <%=session.getAttribute(ApplicationConstants.SESSION_CRE_MIN_MARKS)%> </font></td>
                                                </tr>
                                                  <tr>
                                                    <td align="right"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/rightArrow_16x6.gif"></td>
                                                    <td ><font size="2px" color="#1B425E">Maximum marks of this test is <%=session.getAttribute(ApplicationConstants.SESSION_CRE_TOTAL_MARKS)%> </font></td>
                                                </tr>  --%>
                                                <tr>
                                                    <td align="right"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/rightArrow_16x6.gif"></td>
                                                    <td ><font size="2px" color="#1B425E">Test will be submitted automatically if the time expired. ! </font></td>
                                                </tr>
                                                <tr>
                                                    <td align="right"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/rightArrow_16x6.gif"></td>
                                                    <td ><font size="2px" color="#1B425E">Please navigate through all the questions for proper updation of your answers . </font></td>
                                                </tr>
                                                <tr>
                                                    <td align="right"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/rightArrow_16x6.gif"></td>
                                                    <td ><font size="2px" color="#1B425E">After attempting the last question do go to the previous question for proper updation of your answer.</font></td>
                                                </tr>
                                                 <tr>
                                                    <td align="right"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/rightArrow_16x6.gif"></td>
                                                    <td ><font size="2px" color="#1B425E">Please don't close the window until you complete all exams.</font></td>
                                                </tr>
                                            </table>
                                        </td>
                                        
                                    </tr>
                                    <tr>
                                        <td valign="top">
                                            <br>
                                            <s:form action="mcertStartExam" theme="simple" id="startExamForm" name="startExamForm">
                                                <table border="0" cellpadding="1" cellspacing="1" align="center" width="500px">
                                                    <tr>
                                                       <th class="headerText" colspan="2" align="left">
                                                        </th>  
                                                    </tr>
                                                       
                                                    
                                                    
                                                     <tr>
                                                        <td colspan="2" style="padding-left:218px;">
                                                           <%-- <s:submit cssClass="buttonBg" value="Start Exam" id="startExamButton" onclick="javascript:disableStart();"/> 
                                                            <s:submit cssClass="buttonBg" value="Start Exam" id="startExamButton" onclick="disableStart();"/>--%>
                                                           <input type="button" class="buttonBg" value="Start Exam" id="startExamButton" onclick="disableStart();"/> 
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <div id="loadMsg" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                        <%--    <b id="loadMsg" style="display: none;" class="error" ><font color="red">Loading Please wait....</font></b> --%>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                            <td colspan="2"></td>
                                                    </tr>
                                                </table>
                                            </s:form>
                                        </td>
                                    </tr> 
                                </table>
                            </td>
                        </tr>
                        
                    </table>
                </td>  
            </tr>
            <%--//START FOOTER : Record for Footer Background and Content--%>
            <tr class="footerBg">
                <td align="center"><s:include value="/includes/template/Footer.jsp"/>   </td>
            </tr>
            <%--//END FOOTER : Record for Footer Background and Content--%>

        </table>
        <%--//START MAIN TABLE : Table for template Structure--%>

    </body>
    <%-- END BODY SECTION --%>
    
</html>