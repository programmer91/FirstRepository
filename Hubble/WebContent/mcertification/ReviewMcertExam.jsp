<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%-- <%@ taglib prefix="sx" uri="/struts-dojo-tags" %>--%>
<%@ page import="com.freeware.gridtag.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ page import="com.mss.mirage.ecertification.QuestionsVTO"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page import="java.util.List"%>
<%-- <%@ page import="org.apache.commons.lang.StringEscapeUtils"%> --%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Hubble Organization Portal :: Mcertification Exam Review</title>
  <%--  <sx:head cache="true"/> --%>
     <sj:head jqueryui="true"/>
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tooltip.css"/>">
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tooltip.js"/>"></script>   
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>   
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>   
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmpStandardClientValidations.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
     <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ecertification/EcertStandardClientValidation.js"/>"></script>
     <script type="text/JavaScript">
         function getquestions(val){
          // alert("init"+val);
           
          // alert("count---->"+document.getElementById("quescount").value);
           
           for(var i=0; i < parseInt(document.getElementById("quescount").value); i++){
                     var divObj = document.getElementById("questionAndAnswers"+i);
               if(val==i)
                    divObj.style.display='';
               else
                    divObj.style.display='none';
           }
         }
        
     </script>
    
    
     <style type="text/css">
            
            .popupItem:hover {
            background: #F2F5A9;
            font: arial;
            font-size:10px;
            color: black;
            }
            
            .popupRow {
            background: #3E93D4;
            }
            
            
            .popupItem {
            padding: 2px;
            width: 100%;
            border: black;
            font:normal 9px verdana;
            color: white;
            text-decoration: none;
            line-height:13px;
            z-index:100;
            }
            
        </style>
    
    
</head>
<body class="bodyGeneral" oncontextmenu="return false;" onload="getquestions(0);"> 

<!--//START MAIN TABLE : Table for template Structure-->
<table class="templateTable1000x580" align="center" cellpadding="0" cellspacing="0">

<!--//START HEADER : Record for Header Background and Mirage Logo-->
<tr class="headerBg">
    <td valign="top">
        <s:include value="/includes/template/Header.jsp" />                    
    </td>
</tr>
<!--//END HEADER : Record for Header Background and Mirage Logo-->

<!--//START DATA RECORD : Record for LeftMenu and Screen Content-->
<tr>
<td>
    <table class="innerTable1000x515" cellpadding="0" cellspacing="0">
        <tr>
            <!--//START DATA COLUMN : Coloumn for LeftMenu-->
            <td width="150px;" class="leftMenuBgColor" valign="top"> 
                <s:include value="/includes/template/LeftMenu.jsp"/> 
            </td>
            <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                            
            <!--//START DATA COLUMN : Coloumn for Screen Content-->
            <td width="850px" class="cellBorder" valign="top" style="padding:5px 5px;">
                <ul id="accountTabs" class="shadetabs" >
                     
                    
                        <li ><a href="#" class="selected" rel="issueTab"  >Review Exam</a></li>
                    
                    

                </ul>
                <!--//START TABBED PANNEL : -->
                <div  style="border:1px solid gray; width:845px;height: 500px; overflow:auto; margin-bottom: 1em;">
                <!--//START TAB : -->
                <div id="issueTab" class="tabcontent"  >
                   
                    <table cellpadding="0" cellspacing="0" border="0" width="100%" >
                        <tr>
                            <td align="right" class="headerText"> <INPUT type="button" CLASS="buttonBg" value="Back to List" onClick="history.go(-1)"></td>
                        </tr>
                        <%
                        List reviewList = (List)request.getAttribute(ApplicationConstants.MCERT_REVIEW_LIST);
                        
                        %>
                        <input type="hidden" id="quescount" id="quescount" value="<%=reviewList.size()%>"/>
                        <%
                        for(int i=0;i<reviewList.size();i++) {
                            QuestionsVTO questionsVTO = (QuestionsVTO)reviewList.get(i);
                            String src = request.getContextPath()+"/includes/images/ecertification/correcticon.gif";
                            String checkicon = request.getContextPath()+"/includes/images/ecertification/index.jpg";
                        %>
                        <tr>
                                <td>
                                    <!-- Table to display question and answers -->
                                    <div id="questionAndAnswers<%=i%>">
                                        <table cellpadding="0" cellspacing="0" border="0" width="100%" height="400px">
                                                 <tr>
                                                     <td  valign="top">
                                                         <span>
                                                             <%out.println("<p class='fieldLabeforQuestion'> Q"+(i+1)+")"+questionsVTO.getQuestion()+"</p>");%>
                                                         </span>
                                                         <!-- Question options  start-->
                                                         <span class="fieldLabeforAns">
                                                             <% 
                                                             if(questionsVTO.getAnswer()==1){
                                                             out.println("<img src='"+src+"' alt='image'/>"+" "+questionsVTO.getOption1());
                                                             } else {
                                                             out.println("<img src='"+checkicon+"' alt='image'/>"+" "+questionsVTO.getOption1()); 
                                                             }%>
                                                         </span>
                                                         <br>
                                                         <span class="fieldLabeforAns">
                                                             <% 
                                                             if(questionsVTO.getAnswer()==2){
                                                             out.println("<img src='"+src+"' alt='image'/>"+" "+questionsVTO.getOption2()); 
                                                             } else {
                                                             out.println("<img src='"+checkicon+"' alt='image'/>"+" "+questionsVTO.getOption2()); 
                                                             }%>
                                                         </span>
                                                         <br>
                                                         <span class="fieldLabeforAns">
                                                             <% 
                                                             if(questionsVTO.getAnswer()==3){
                                                             out.println("<img src='"+src+"' alt='image'/>"+" "+questionsVTO.getOption3()); 
                                                             } else {
                                                             out.println("<img src='"+checkicon+"' alt='image'/>"+" "+questionsVTO.getOption3()); 
                                                             }%>
                                                         </span>
                                                         <br>
                                                         <span class="fieldLabeforAns">
                                                             <% 
                                                             if(questionsVTO.getAnswer()==4){
                                                             out.println("<img src='"+src+"' alt='image'/>"+" "+questionsVTO.getOption4()); 
                                                             } else {
                                                             out.println("<img src='"+checkicon+"' alt='image'/>"+" "+questionsVTO.getOption4()); 
                                                             }%>
                                                         </span>
                                                         <!-- Question options end -->  
                                                 <%--    <span style="padding-left:1%">
                                                    <%out.println("<font color='green'>Ans. "+questionsVTO.getAnswer()+"</font>"); %>
                                                </span>  --%>
                                                
                                            </td>
                                        </tr>
                                        <tr valign="top">
                                            <td>
                                            <span class='fieldLabeforQuestion'>
                                            <%
                                            if(questionsVTO.getAnswer() == questionsVTO.getUserAnswer())
                                            out.println("<font color='green'>Candidate Answer: "+questionsVTO.getUserAnswer()+"</font>"); 
                                            else {
                                            if(questionsVTO.getUserAnswer() != 0)
                                            out.println("<font color='red'>Candidate Answer: "+questionsVTO.getUserAnswer()+"</font>"); 
                                            else {
                                            out.println("<font color='red'>Candidate Answer: Not Attempted</font>"); 
                                            }
                                            }
                                            %>
                                            </span>
                                            <br><br>
                                                <span class='fieldLabeforQuestion'>
                                                    <%out.println("Explanation. "+questionsVTO.getDescription()); %>
                                                </span>
                                            </td>
                                         </tr>
                                            <tr>
                                            <td align="right">
                                                <%
                                                if(i>0) {
                                                %>
                                            <input type="button" value="Previous" Class="buttonBg" onclick="getquestions('<%=(i-1)%>')" id="previous"/>
                                            <%}if(i<(reviewList.size()-1)) {%>
                                            <input type="button" value="Next" Class="buttonBg" onclick="getquestions('<%=(i+1)%>')" id="next"/> &nbsp;&nbsp;
                                            <%}%>
                                            </td>
                                            </tr>
                                        </table>
                                    </div>
                                    <!-- End -->
                                </td>
                            </tr>  
                        <%}%>
                    </table>
                    
                    <!-- this script for After loading Form it will instantiate Calender Objects as you require -->
    
                </div>                                       
    </div>                               
    <%--  </sx:tabbedpanel> --%>
    <!--//END TABBED PANNEL : -->
    <script type="text/javascript">

var countries=new ddtabcontent("accountTabs")
countries.setpersist(false)
countries.setselectedClassTarget("link") //"link" or "linkparent"
countries.init()

                                </script>
    
</td>
<!--//END DATA COLUMN : Coloumn for Screen Content-->
</tr>
</table>
</td>
</tr>

<!--//END DATA RECORD : Record for LeftMenu and Body Content-->

<!--//START FOOTER : Record for Footer Background and Content-->
<tr class="footerBg">
    <td align="center"><s:include value="/includes/template/Footer.jsp"/>   </td>
</tr>
<!--//END FOOTER : Record for Footer Background and Content-->
 <tr>
                <td>
                    
                    <div style="display: none; position: absolute; top:170px;left:320px;overflow:auto;" id="menu-popup">
                        <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" />
                    </div>
                    
                </td>
            </tr>
</table>
<!--//END MAIN TABLE : Table for template Structure-->

</body>
</html>