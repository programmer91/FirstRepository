<!doctype html>
<html lang=''>
    <%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
 <%@ taglib prefix="s" uri="/struts-tags" %>
<head>


 
 
 <%-- <script type="text/JavaScript" src="<s:url value="/includes/javascripts/jquery-latest.min.js"/>"></script>  --%>
     <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/jquery.min.js"/>"></script>
    <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/jquery.js"/>"></script>  
   <script src="script.js"></script><link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/menu/toggleMenu.css"/>">
 <script type="text/JavaScript" src="<s:url value="/includes/javascripts/menu/menuVendors.js"/>"></script>
 

 
 

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
          <li><a id="myTasks" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/tasks/getTasks.action?issueList=1"> <span>Tasks</span></a></li>
          <li><a id="myTimeSheets" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/timesheets/newtimeSheet.action"> <span>Time Sheets</span></a></li>
      </ul>
   </li>
    <s:hidden id="userManager" value="%{#session.isUserManager}"></s:hidden> 
    <s:hidden id="userTeamLead" value="%{#session.isUserTeamLead}"></s:hidden> 
   <s:if test="#session.isUserManager == 1 || #session.isUserTeamLead==1">
   <li class='has-sub'><a href='#' id="iconToggleTeam"><span id="teamAdmin">Team</span></a>
      <ul id="teamDisplay">
       <%--  <li><a id="userSearch" href="/<%=ApplicationConstants.CONTEXT_PATH%>/admin/userSearch.action"> <span>Assign Roles</span></a></li>  --%>
         <li class='last'><a id="teamHierarchy" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/myRecruitmentTeamTree.action"><span>Hierarchy</span></a></li>
      </ul>
   </li>
   </s:if>
    <li class='has-sub'><a href='#' id="iconToggleServices"><span id="servicesAdmin">Services</span></a>
      <ul id="servicesDisplay">
          <li><a id="servicesAddVendor" href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/accounts/account.action"> <span>Add Vendor</span></a></li>
          <li><a id="servicesAllVendors" href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/accounts/vendorsList.action"> <span>All Vendors</span></a></li>
         
      </ul>
    </li>
      </ul>
   
</div>

</body>
</html>