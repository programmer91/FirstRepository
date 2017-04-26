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
   <%--  <script type="text/JavaScript" src="<s:url value="/includes/javascripts/jquery-latest.min.js"/>"></script>  --%>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/jquery.min.js"/>"></script>
    <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/jquery.js"/>"></script>  
   <script type="text/JavaScript" src="<s:url value="/includes/javascripts/menu/menuMarketing.js?ver=1.2"/>"></script>
 <script type="text/javascript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
 <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/ajaxfileupload.js"/>"></script>
 <%-- <script type="text/JavaScript" src="<s:url value="/includes/javascripts/marketing/InvestmentScripts.js"/>"></script> --%>
 
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
   <li class='has-sub'><a href='#' id="iconToggleTeam"><span id="teamAdmin">Team</span></a>
      <ul id="teamDisplay">
      </ul>
   </li>
    <li class='has-sub'><a href='#' id="iconToggleServices"><span id="servicesAdmin">Services</span></a>
      <ul id="servicesDisplay">
        <li><a id="account" href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/accounts/account.action"> <span>Add Account</span></a></li>
         <li><a id="accountsListAll"  href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/accounts/accountsListAll.action?accList=1"> <span>All Accounts</span></a></li>
         <li><a id="activityManager" href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/activities/activityManager.action"> <span>Activity Manager</span></a></li>
         <li><a id="accountAssign" href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/accounts/accountAssign.action"> <span>Account&nbsp;Operations</span></a></li>
         <li><a id="contactSearch" href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/contacts/contactSearch.action"><span>Contacts</span></a></li>
         <li><a id="campaignSearchAction" href="/<%=ApplicationConstants.CONTEXT_PATH%>/marketing/campaignSearchAction.action"><span>Campaign</span></a></li>
         <li><a id="internalEvents" href="/<%=ApplicationConstants.CONTEXT_PATH%>/marketing/events/internalEvents.action"> <span>Internal&nbsp;Events</span></a></li>
         <%-- <s:if test="#session.emeetPostingFlag == 1">
             <li><a id="emeetList" href="/<%=ApplicationConstants.CONTEXT_PATH%>/marketing/emeetList.action"><span>Emeets</span></a></li>
             <li class='last'><a id="emeetAttendiesList" href="/<%=ApplicationConstants.CONTEXT_PATH%>/marketing/emeetAttendiesList.action"><span>EAttendees&nbsp;</span></a></li>
         </s:if> --%>
            <li  class='last'><a id="getInsideSalesStatus" href="/<%=ApplicationConstants.CONTEXT_PATH%>/marketing/getInsideSalesStatus.action"><span>Inside&nbsp;Sales&nbsp;Status</span></a></li>  
      </ul>
    </li>
    <li class='has-sub'><a href='#' id="iconToggleLeadGen"><span id="leadGenAdmin">LeadGen</span></a>
    <ul id="leadGenDisplay">
        <li><a id="leadGen" href="/<%=ApplicationConstants.CONTEXT_PATH%>/marketing/leadGen.action"><span>Investment</span></a></li>
         <li class='last'><a id="leadDashboard" href="/<%=ApplicationConstants.CONTEXT_PATH%>/marketing/leadDashboard.action"><span>Lead&nbsp;Dashboard</span></a></li>
      </ul>
    </li>
     <li class='has-sub'><a href='#' id="iconTogglewebSite"><span id="webSiteAdmin">Website&nbsp;Data</span></a>
         <ul id="webSiteDisplay">
             <li><a id="getContactUs" href="/<%=ApplicationConstants.CONTEXT_PATH%>/marketing/getContactUs.action"><span>ContactUs&nbsp;Data</span></a></li>
             <li><a id="getResourceDepot" href="/<%=ApplicationConstants.CONTEXT_PATH%>/marketing/getResourceDepot.action"><span>Resource&nbsp;Depot&nbsp;Data</span></a></li>
            <%-- <li class='last'><a id="getQuarterlyMeet" href="/<%=ApplicationConstants.CONTEXT_PATH%>/marketing/getQuarterlyMeet.action"><span>QuarterlyMeet&nbsp;Data</span></a></li> --%>
           
             
         </ul>
     </li>
     </ul>
  </div>
</body>
</html>