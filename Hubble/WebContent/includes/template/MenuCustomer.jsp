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
 <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/menu/toggleMenu.css"/>">
 <script type="text/JavaScript" src="<s:url value="/includes/javascripts/menu/menuCustomer.js"/>"></script>
  <script type="text/javascript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
</head>
<div id='cssmenu'>
        <%
                String actionName = ActionContext.getContext().getName();
                //System.out.println("action name"+actionName);
                %>
                <span id="action" style="display: none"><%=actionName%></span> 
<ul>
     <s:if test="#session.customerRole!=-1">
    <li class='has-sub' ><a href='#' id="iconToggleMy"><span id="myAdmin">My</span></a>
       
             <ul id="myDisplay">
           <li ><a  id="newCreateTask" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/tasks/newCreateTask.action"><span>Create Task</span></a></li>
           <li ><a  id="getTasks" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/tasks/getTasks.action?issueList=1"><span>Tasks</span></a></li>
           <li class='last'><a  id="newtimeSheet" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/timesheets/newtimeSheet.action?sheetType=c"><span>TimeSheets</span></a></li>
      </ul>
            
   </li>
    </s:if>
   <s:hidden id="customerRole" value="%{#session.customerRole}"></s:hidden> 
   <s:if test="#session.customerRole!=1 && #session.customerRole!=8 && #session.customerRole!=-1">
   <li class='has-sub'><a href='#' id="iconToggleTeam"><span id="teamAdmin">Team</span></a>
      <ul id="teamDisplay">
         <li><a id="getTeamTasks" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/tasks/getTeamTasks.action?issueList=1"> <span>Tasks</span></a></li>
         <li class='last'><a id="newCustomerTeamTimeSheets" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/timesheets/newCustomerTeamTimeSheets.action"><span>TimeSheets</span></a></li>
      </ul>
   </li>
       </s:if>
   <li class='has-sub'><a href='#' id="iconToggleServices"><span id="servicesAdmin">Services</span></a>
      <ul id="servicesDisplay">
          <s:if test="#session.customerRole == 8">
               <li><a id="newempTimeSheets" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/timesheets/newempTimeSheets.action"> <span>Emp TimeSheets</span></a></li>
          </s:if>
         <li><a id="resetCustPassword"  href="/<%=ApplicationConstants.CONTEXT_PATH%>/general/resetCustPassword.action"> <span>Change My Pwd</span></a></li>
         <s:if test="#session.opportunitySoftwareAccess == 1">
             <li class='last'><a id="myCustOpportunities" href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/opportunities/myCustOpportunities.action"><span>Opportunities</span></a></li>
    </s:if>

      </ul>
    </li>
      </ul>
</div>
</body>
</html>