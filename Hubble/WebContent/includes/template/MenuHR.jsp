 <!doctype html>
<html lang=''>
    <%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
 <%@ taglib prefix="s" uri="/struts-tags" %>
<head>
   <meta charset='utf-8'>
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta name="viewport" content="width=device-width, initial-scale=1">
 
   <%--  <script type="text/JavaScript" src="<s:url value="/includes/javascripts/jquery-latest.min.js"/>"></script>   --%>
  <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/jquery.min.js"/>"></script>
    <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/jquery.js"/>"></script>  
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/menu/toggleMenu.css"/>">
 <script type="text/JavaScript" src="<s:url value="/includes/javascripts/menu/menuHr.js"/>"></script>
   <script type="text/javascript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
 
  
</head>
<body>

<div id='cssmenu'>
        <%
                String actionName = ActionContext.getContext().getName();
                //System.out.println("action name"+actionName);
                %>
                <span id="action" style="display: none"><%=actionName%></span> 
<ul>
   
   <li class='has-sub' ><a href='#' id="iconToggleMy"><span id="myAdmin">My</span></a>
      <ul id="myDisplay">
          <li ><a  id="getTasks" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/tasks/getTasks.action?issueList=1"><span>Tasks</span></a></li>
         <li ><a id="newtimeSheet"  href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/timesheets/newtimeSheet.action"><span>Time Sheets</span></a></li>
         <li><a   id="leaveRequestList" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/leaveRequestList.action"><span>Apply Leave</span></a></li>
          <li class='last'><a id="leaveApprovalList" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/leaveApprovalList.action"><span>Leave Approvals</span></a></li>
      </ul>
   </li>
   <li class='has-sub'><a href='#' id="iconToggleTeam"><span id="teamAdmin">Team</span></a>
      <ul id="teamDisplay">
         <li class='last'><a id="myGDCTeamTree" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/myGDCTeamTree.action"><span>Hierarchy</span></a></li>
      </ul>
   </li>
    <li class='has-sub'><a href='#'  id="iconToggleServices"><span id="servicesAdmin">Services</span></a>
      <ul id="servicesDisplay">
        <li><a id="allReports" href="/<%=ApplicationConstants.CONTEXT_PATH%>/reports/allReports.action"><span>Reports</span></a></li>
    <%--     <li><a id="mycopreport" href="/<%=ApplicationConstants.CONTEXT_PATH%>/reports/mycopreport.action"><span>VenusReport</span></a></li> 
         <li><a id="venusReport" href="/<%=ApplicationConstants.CONTEXT_PATH%>/reports/venusReport.action"><span>VenusReport(Emp&nbsp;Base)</span></a></li>  --%>
         <li><a id="leavereport" href="/<%=ApplicationConstants.CONTEXT_PATH%>/reports/leavereport.action"><span>LeaveReports</span></a></li>
         <li><a id="empSearchAll" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/empSearchAll.action">Employee Search</span></a></li>
         <li><a id="getExamResultsList" href="/<%=ApplicationConstants.CONTEXT_PATH%>/ecertification/getExamResultsList.action"><span>Exam&nbsp;Result</span></a></li>
         <li><a id="createKeys" href="/<%=ApplicationConstants.CONTEXT_PATH%>/ecertification/createKeys.action"><span>Generate Keys</span></a></li>
         <li><a id="listKeys" href="/<%=ApplicationConstants.CONTEXT_PATH%>/ecertification/listKeys.action"><span>Key List</span></a></li>
         <li class='last'><a  id="mailid" href="#" onclick="return win_open('/<%=ApplicationConstants.CONTEXT_PATH%>/services/mail/MailWindow.jsp');"><span>Mail Service</span></a></li>
         
      </ul>
    </li>
      </ul>
   
   

</div>

</body>
</html>
