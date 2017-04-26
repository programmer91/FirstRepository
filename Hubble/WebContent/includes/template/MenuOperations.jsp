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

 
 
 <%-- <script type="text/JavaScript" src="<s:url value="/includes/javascripts/jquery-latest.min.js"/>"></script>  --%>
     <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/jquery.min.js"/>"></script>
    <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/jquery.js"/>"></script>  
   <script src="script.js"></script><link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/menu/toggleMenu.css"/>">
 <script type="text/JavaScript" src="<s:url value="/includes/javascripts/menu/menuOperations.js?ver=1.0"/>"></script>
 <script type="text/javascript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script> 
 <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/ajaxfileupload.js"/>"></script> 

 
 

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
      </ul>
   </li>
    <s:hidden id="userManager" value="%{#session.isUserManager}"></s:hidden> 
    <s:hidden id="teamLead" value="%{#session.isUserTeamLead}"></s:hidden> 
  <%-- <s:if test="#session.isUserManager != 0"> --%>
   <s:if test="#session.isUserManager == 1 || #session.isUserTeamLead==1">
   <li class='has-sub'><a href='#' id="iconToggleTeam"><span id="teamAdmin">Team</span></a>
      <ul id="teamDisplay">
       <%--  <li><a id="userSearch" href="/<%=ApplicationConstants.CONTEXT_PATH%>/admin/userSearch.action"> <span>Assign Roles</span></a></li>  --%>
         <li class='last'><a id="myOperationsTeamTree" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/myOperationsTeamTree.action"><span>Hierarchy</span></a></li>
      </ul>
   </li>
   </s:if>
    <li class='has-sub'><a href='#' id="iconToggleServices"><span id="servicesAdmin">Services</span></a>
      <ul id="servicesDisplay">
          <li><a id="userSearch" href="/<%=ApplicationConstants.CONTEXT_PATH%>/admin/userSearch.action"> <span>Assign Roles</span></a></li>
          <li><a id="getAppraisalsList" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/Reviews/getAppraisalsList.action"> <span>Appraisals</span></a></li>
         <li><a id="teamReviewList"  href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/Reviews/teamReviewList.action"> <span>Perf.Review</span></a></li>
         <li><a id="perfDashboard" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/Reviews/perfDashboard.action"> <span>Perf. Dashboard</span></a></li>
         <%--<li><a id="newTeamteSearch"href="/<%=ApplicationConstants.CONTEXT_PATH%>/te/newTeamteSearch.action"> <span>Travel&nbsp;&&nbsp;Expenses</span></a></li>--%>
         <%--<li><a id="teExpensesSearch" href="/<%=ApplicationConstants.CONTEXT_PATH%>/te/teExpensesSearch.action"> <span>Expenses</span></a></li>--%>
         <li><a id="surveyFormList" href="/<%=ApplicationConstants.CONTEXT_PATH%>/marketing/surveyform/surveyFormList.action"> <span>Survey&nbsp;Form</span></a></li>
         <li><a id="empLeaveReport" href="/<%=ApplicationConstants.CONTEXT_PATH%>/reports/empLeaveReport.action"> <span>Emp Leaves</span></a></li>
         <li><a id="GenerateEmpLeaveReport" href="/<%=ApplicationConstants.CONTEXT_PATH%>/reports/GenerateEmpLeaveReport.action"> <span>Leaves Report</span></a></li>
     <%--    <li><a id="accountSearch" href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/accounts/accountSearch.action"> <span>Accounts Search</span></a></li> --%>
      <%--   <li><a id="accountAssign" href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/accounts/accountAssign.action"> <span>Assign Accounts</span></a></li> --%>
        
         <s:if test="#session.isAdminAccess == 1 || #session.workCountryList == 'USA'">
         <li><a id="greenSheet" href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/greensheets/greenSheet.action"> <span>Greensheets</span></a></li>
         <li><a id="empTimeSheets" href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/greensheets/greensheetReport.action"> <span>Greensheet Reports</span></a></li>
         </s:if> 
         
         <li><a id="newempTimeSheetSearch" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/timesheets/newempTimeSheetSearch.action"> <span>Emp TimeSheets</span></a></li>
         <li><a id="resetPassword" href="/<%=ApplicationConstants.CONTEXT_PATH%>/general/resetPassword.action"> <span>Reset My Pwd</span></a></li>
         <li><a id="changeCustPassword" href="/<%=ApplicationConstants.CONTEXT_PATH%>/admin/changeCustPassword.action"> <span>Reset Cust Pwd</span></a></li>
         <li><a id="empSearchAll" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/empSearchAll.action"> <span>Emp Search</span></a></li>
        <li><a  id="mailid" href="#" onclick="return win_open('/<%=ApplicationConstants.CONTEXT_PATH%>/services/mail/MailWindow.jsp');"><span>Mail Service</span></a></li>
         <li><a id="empEmailCheck" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/empEmailCheck.action"><span>Email Check</span></a></li>
        <li><a id="givePerformanceReview" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/performanceReviews/givePerformanceReview.action"><span>Performance Review</span></a></li>
         <li><a id="getDashBoardForEmpReports" href="/<%=ApplicationConstants.CONTEXT_PATH%>/reports/getDashBoardForEmpReports.action"><span>DashBoard</span></a></li>
         <s:hidden id="noDueRemainders" value="%{#session.noDueRemainders}"></s:hidden> 
   <s:hidden id="noDueApprovers" value="%{#session.noDueApprovers}"></s:hidden> 
         <s:if test="#session.noDueRemainders == 1||#session.noDueApprovers == 1">
            <li><a id="getEmployeeNoDuesOperations"  href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/payroll/getEmployeeNoDuesOperations.action"><span>No&nbsp;Dues</span></a></li>   
         </s:if>
         <li class='last'><a id="getMyAppreciation" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/appreciation/getMyAppreciation.action?searchflag=opt"><span>Appreciations</span></a></li>  
         <s:if test="#session.isAdminAccess == 1 || #session.userId == 'rijju' || #session.userId =='ukodati' || #session.userId =='rkalaga' || #session.userId =='vpusapati'">
              <li class='last'><a id="teamQuaterAppraisalSearch" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/appraisal/teamQuaterAppraisalSearch.action"><span>Q-Review</span></a></li>
         </s:if>
         <s:hidden id="bdmAssociationAccess" value="%{#session.bdmAssociationAccess}"></s:hidden> 
         <s:if test ="#session.bdmAssociationAccess">
         <li class='last'><a id="getBdmAssociates" href="/<%=ApplicationConstants.CONTEXT_PATH%>/admin/getBdmAssociates.action"><span>BDM Associations</span></a></li>
         </s:if>

      </ul>
    </li>
      </ul>
   
</div>

</body>
</html>