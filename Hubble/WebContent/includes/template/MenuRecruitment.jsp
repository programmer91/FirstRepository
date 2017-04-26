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
  
   <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/menu/toggleMenu.css"/>">
  <%-- <script type="text/JavaScript" src="<s:url value="/includes/javascripts/jquery-latest.min.js"/>"></script>  --%>
      <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/jquery.min.js"/>"></script>
    <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/jquery.js"/>"></script>
   <script type="text/JavaScript" src="<s:url value="/includes/javascripts/menu/menuRecruitment.js?version=1.0"/>"></script>
  <script type="text/javascript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
 
 <%--     <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AjaxRequirement.js"/>"></script>
      <script type="text/JavaScript" src="<s:url value="/includes/javascripts/LoadConsultantAjax.js"/>"></script> 
          <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AjaxPopup.js"/>"></script>    --%>
  
</head>
<body>

<div id='cssmenu'>
        <%
                String actionName = ActionContext.getContext().getName();
                //System.out.println("action name"+actionName);
                %>
                <span id="action" style="display: none"><%=actionName%></span> 
<ul>
   
   <li class='has-sub'><a href='#' id="iconToggleMy"><span id="myAdmin">My</span></a>
      <ul id="myDisplay">
          <li ><a  id="newtimeSheet"  href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/timesheets/newtimeSheet.action"><span>Time Sheets</span></a></li>
          <li class='last' ><a id="popUpConsultant"  href="/<%=ApplicationConstants.CONTEXT_PATH%>/recruitment/popUpConsultant.action"> <span>Consultant Details</span></a></li>
      </ul>
   </li>
   <s:hidden id="userManager" value="%{#session.isUserManager}"></s:hidden> 
   <s:hidden id="teamLead" value="%{#session.isUserTeamLead}"></s:hidden> 
    <s:if test="#session.isUserManager == 1 || #session.isUserTeamLead==1">
   <li class='has-sub'><a href='#' id="iconToggleTeam"><span id="teamAdmin">Team</span></a>
      <ul id="teamDisplay">
         <li><a id="myRecruitmentTeamTree" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/myRecruitmentTeamTree.action"><span>Hierarchy</span></a></li>
         <li><a id="recruitmentDashboard" href="/<%=ApplicationConstants.CONTEXT_PATH%>/recruitment/consultant/recruitmentDashboard.action"><span>Dashboard</span></a></li>
         <li class='last'><a id="getConsultantMerge" href="/<%=ApplicationConstants.CONTEXT_PATH%>/recruitment/consultant/getConsultantMerge.action"><span>Consultant Merge</span></a></li>
      </ul>
   </li>
   </s:if>
    <li class='has-sub'><a href='#' id="iconToggleServices"><span id="servicesAdmin">Services</span></a>
      <ul id="servicesDisplay">
      <%--  <li><a id="employeeSearch" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/employeeSearch.action"><span>Employee Search</span></a></li> --%>
         <li><a id="resetPassword" href="/<%=ApplicationConstants.CONTEXT_PATH%>/general/resetPassword.action"><span>Reset My Pwd</span></a></li>
         <li><a  id="mailid" href="#" onclick="return win_open('/<%=ApplicationConstants.CONTEXT_PATH%>/services/mail/MailWindow.jsp');"><span>Mail Service</span></a></li>
         <li><a id="surveyFormList" href="/<%=ApplicationConstants.CONTEXT_PATH%>/marketing/surveyform/surveyFormList.action"><span>Survey&nbsp;Form</span></a></li>
         <s:if test="#session.isUserManager == 1">
         <li><a id="executiveDashboardForRequirement"  href="/<%=ApplicationConstants.CONTEXT_PATH%>/recruitment/consultant/executiveDashboardForRequirement.action"><span>Executive&nbsp;Dashboard</span></a></li>
 </s:if>
          <li class='last'><a id="pmoDashBoard"  href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/pmoDashBoard.action"><span>Available&nbsp;Employee&nbsp;list</span></a></li>
      </ul>
    </li>
    <li class='has-sub'><a href='#' id="iconToggleRecruitment"><span id="recruitmentAdmin">Recruitment</span></a>
      <ul id="recruitmentDisplay">
        <li><a id="getConsultant" href="/<%=ApplicationConstants.CONTEXT_PATH%>/recruitment/consultant/getConsultant.action?requirement=-1"><span>Consultant Add</span></a></li>
         <li><a id="consultantSearch" href="/<%=ApplicationConstants.CONTEXT_PATH%>/recruitment/consultant/consultantSearch.action?all=1"><span>Consultant Search</span></a></li>
          <li><a id="untouchedConsultantsSearch" href="/<%=ApplicationConstants.CONTEXT_PATH%>/recruitment/consultant/untouchedConsultantsSearch.action"><span>Untouched&nbsp;Consultants</span></a></li>
         <li class='last'><a id="resumeSearch" href="/<%=ApplicationConstants.CONTEXT_PATH%>/recruitment/consultant/resumeSearch.action"><span>Resume Search</span></a></li>
      </ul>
    </li>
    <li class='has-sub'><a href='#' id="iconToggleRequirement"><span id="requirementAdmin">Requirement</span></a>
      <ul id="requirementDisplay">
        <li><a id="requirementList" href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/requirement/requirementList.action?ajaxId=-1"><span>List</span></a></li>
         <s:if test="#session.isRequirementAdmin == 1">
        <li class='last'><a id="requirementAdminList" href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/requirement/requirementAdminList.action?ajaxId=-1">All Requirements</span></a></li>
        </s:if>
      </ul>
    </li> 
    <s:hidden id="jobPostingFlag" value="%{#session.jobPostingFlag}"></s:hidden> 
     <s:if test="#session.jobPostingFlag == 1">
    <li class='has-sub'><a href='#' id="iconToggleWebsite"><span id="websiteAdmin">Website</span></a>
      <ul id="websiteDisplay">
         <li><a id="websiteJob" href="/<%=ApplicationConstants.CONTEXT_PATH%>/marketing/websiteJob.action"><span>Publish&nbsp;Position</span></a></li>     
         </s:if>
          
          <s:elseif test="#session.sourcingFlag == 'YES'">
              <li class='has-sub'><a href='#'id="iconToggleWebsite"><span id="websiteAdmin">Website</span></a>
              <ul id="websiteDisplay">
        <li><a id="websiteJob" href="/<%=ApplicationConstants.CONTEXT_PATH%>/marketing/websiteJob.action"><span>Open&nbsp;Positions</span></a></li>
        <li><a id="websiteLatestJobApplications" href="/<%=ApplicationConstants.CONTEXT_PATH%>/marketing/websiteLatestJobApplications.action"><span>Job&nbsp;Applicants</span></a></li>
        <li class='last'><a id="consultantsFromWebsite" href="/<%=ApplicationConstants.CONTEXT_PATH%>/recruitment/consultantsFromWebsite.action"><span>ConsultantList</span></a></li>
        </s:elseif>
        </ul>
      </ul>
    </li>
      </ul>
  
   
</div>

</body>
</html>
