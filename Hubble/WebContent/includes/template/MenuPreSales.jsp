<%-- 
    Document   : MenuPreSales
    Created on : Sep 26, 2015, 12:48:13 AM
    Author     : miracle
--%>


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
   <script type="text/JavaScript" src="<s:url value="/includes/javascripts/menu/menuPreSales.js"/>"></script>
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
   
   <li class='has-sub'><a href='#'  id="iconToggleMy"><span id="myAdmin">My</span></a>
      <ul id="myDisplay">
        
      <%--    <li ><a  id="requirementlist" href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/requirement/requirementList.action?ajaxId=-1"><span>Requirement&nbsp;List</span></a></li> --%>
       <li ><a  id="requirementListForPresalesMy" href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/requirement/requirementListForPresalesMy.action?ajaxId=-1"><span>Requirement&nbsp;List</span></a></li>
       
         <li class='last'><a id="techreviews"  href="/<%=ApplicationConstants.CONTEXT_PATH%>/recruitment/consultant/consultantTechReviews.action"><span>Tech Reviews</span></a></li>
        
      </ul>
   </li>
   <li class='has-sub'><a href='#' id="iconToggleTeam"><span id="teamAdmin">Team</span></a>
      <ul id="teamDisplay">
      </ul>
   </li>
    <li class='has-sub'><a href='#' id="iconToggleServices"><span id="servicesAdmin">Services</span></a>
      <ul id="servicesDisplay">
        <li><a id="servicesdashboard"  href="/<%=ApplicationConstants.CONTEXT_PATH%>/presales/myOpportunities.action"><span>DashBoard</span></a></li> 
        <li><a id="servicespmodashboard"  href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/pmoDashBoard.action"><span>Available&nbsp;Employee&nbsp;list</span></a></li> 
        
<%-- <li class='last'><a id="clientReqEngagementSearch"  href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/accounts/clientReqEngagementPreSalesSearch.action"><span>PSCER/RFP</span></a></li> --%>
 <li class='last'><a id="clientReqEngagementSearch"  href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/accounts/clientReqEngagementPreSalesSearch.action?backToFlag=No"><span>PSCER/RFP</span></a></li>

      </ul>
    </li>
      </ul>
</div>
</body>
</html>
