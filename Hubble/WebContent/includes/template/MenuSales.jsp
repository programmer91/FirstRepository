<!doctype html>
<html lang=''>
    <%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
 <%@ taglib prefix="s" uri="/struts-tags" %>
 <%@ page import="com.opensymphony.xwork2.ActionContext"%>
<head>
   <meta charset='utf-8'>
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/menu/toggleMenu.css"/>">
   <%--  <script type="text/JavaScript" src="<s:url value="/includes/javascripts/jquery-latest.min.js"/>"></script>   --%>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/jquery.min.js"/>"></script>
    <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/jquery.js"/>"></script>  
   <script type="text/JavaScript" src="<s:url value="/includes/javascripts/menu/menuSales.js"/>"></script>
       <script type="text/javascript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
 <script type="text/javascript" src="<s:url value="/includes/javascripts/GreenSheetClientValidation.js"/>"></script>
   
</head>
<body>
<div id='cssmenu'>
        <%
                String actionName = ActionContext.getContext().getName();
                  String greenSheets= request.getParameter("teamValue");
                //System.out.println("action name"+actionName);
                %>
                <span id="action" style="display: none"><%=actionName%></span> 
                  <span id="greenSheets" style="display: none"><%=greenSheets%></span> 
<ul>
   
    
   <li class='has-sub'><a href='#' id="iconToggleMy"><span id="myAdmin">My</span></a>
      <ul id="myDisplay">
          <li ><a  id="accountsListMy" href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/accounts/accountsListMy.action?accList=1"> <span>Accounts</span></a></li>
          <li ><a  id="untouchedAccounts" href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/accounts/untouchedAccounts.action"> <span>UntouchedAccounts</span></a></li>
         <li ><a id="myActivitiesInfo"  href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/activities/myActivitiesInfo.action"><span>Activities</span></a></li>
         <s:if test="#session.isUserManager == 0">
             <li><a   id="dashBoard" href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/accounts/dashBoard.action"><span>Dashboard</span></a></li>
         </s:if>
         <li><a   id="greenSheet" href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/greensheets/greenSheet.action?teamValue=notAvailable"><span>Greensheets</span></a></li>
          <li><a id="getCalendar" href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/calendar/getCalendar.action"><span>Calendar</span></a></li>
          <s:if test="#session.sessionEmpPractice == 'Practice'">
               <li><a id="myOpportunities" href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/opportunities/myOpportunities.action"><span>Opportunities</span></a></li>
          </s:if>
      </ul>
   </li>
   <s:hidden id="userManager" value="%{#session.isUserManager}"></s:hidden> 
   <s:hidden id="userTeamLead" value="%{#session.isUserTeamLead}"></s:hidden> 
    <s:if test="#session.isUserManager != 0  || #session.isUserTeamLead != 0">
   <li class='has-sub'><a href='#' id="iconToggleTeam"><span id="teamAdmin">Team</span></a>
      <ul id="teamDisplay">
         <li><a id="accountAssign" href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/accounts/accountAssign.action"><span>Account&nbsp;Operations</span></a></li>
         <li><a id="dashBoard"  href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/accounts/dashBoard.action"><span>Dashboard</span></a></li>
         <li><a id="accountsListMyTeam"  href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/accounts/accountsListMyTeam.action?accList=1"><span>Accounts</span></a></li>
         <li><a id="untouchedTeamAccounts"  href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/accounts/untouchedTeamAccounts.action"><span>UntouchedAccounts</span></a></li>
         <li><a id="teamActivitiesInfo"  href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/activities/teamActivitiesInfo.action"><span>Activities</span></a></li>
         
         <li><a id="greenSheetTeam"  href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/greensheets/greenSheet.action?teamValue=available"><span>Greensheets</span></a></li>
         <li><a id="accessTeamCalendar"  href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/calendar/accessTeamCalendar.action"><span>Calendar</span></a></li>
         <li><a id="myTeamTree"  href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/myTeamTree.action"><span>Hierarchy</span></a></li>
      </ul>
   </li>
    </s:if>
    <li class='has-sub'><a href='#' id="iconToggleServices"><span id="servicesAdmin">Services</span></a>
      <ul id="servicesDisplay">
     <%--   <li><a id="accessCalendar" href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/calendar/accessCalendar.action"><span>Access Calendar</span></a></li> --%>
         <li><a id="accountSearch"  href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/accounts/accountSearch.action"><span>Accounts Search</span></a></li>
         <li><a id="bdmDashBoard" href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/accounts/bdmDashBoard.action"><span>New&nbsp;Dashboard</span></a></li>
         <li><a id="availableEmployeeList" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/pmoDashBoard.action"><span>Available&nbsp;Employee&nbsp;list</span></a></li>
         <li><a id="contactSearch" href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/contacts/contactSearch.action"><span>Contacts</span></a></li>
         <li><a id="campaignSearchAction" href="/<%=ApplicationConstants.CONTEXT_PATH%>/marketing/campaignSearchAction.action"><span>Campaign</span></a></li>
         <li class='last'><a id="clientReqEngagementSearch"  href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/accounts/clientReqEngagementSearch.action?backToFlag=No"><span>PSCER/RFP</span></a></li>
      </ul>
    </li>
     <li class='has-sub'><a href='#' id="iconToggleRequirement"><span id="requirementAdmin">Requirement</span></a>
      <ul id="requirementDisplay">
        <li><a id="requirementList" href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/requirement/requirementList.action?ajaxId=-1"><span>List</span></a></li>         
      </ul>
    </li>
      </ul>
   </li>
   
</ul>
</div>
    

</body>
</html>

    
    
     