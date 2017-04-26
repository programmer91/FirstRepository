<%-- 
    Document   : MenuPayroll
    Created on : Mar 26, 2015, 10:22:43 PM
    Author     : miracle
--%>


<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<head>
    <meta charset='utf-8'>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">


    <%--    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/jquery-latest.min.js"/>"></script>  --%>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/jquery.min.js"/>"></script>
    <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/jquery.js"/>"></script>  
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/menu/toggleMenu.css"/>">
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/menu/menuPayroll.js"/>"></script>
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
            

          
            <li class='has-sub'><a href='#' id="iconToggleServices"><span id="servicesAdmin">Services</span></a>
                <ul id="servicesDisplay">
                    <li><a id="servicesgetAllEmployeePayroll" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/payroll/getAllEmployeePayroll.action"><span>Employee&nbsp;List</span></a></li>
                    <li><a id="servicesCampaigngetPayRollReports" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/payroll/getPayRollReports.action"><span>PayRoll&nbsp;SwitchBoard</span></a></li>
                    <li><a id="servicesgetPayRollDashBoard" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/payroll/getPayRollDashBoard.action"><span>Employee&nbsp;TED</span></a></li>
                     <li><a id="serviceschangeTDSGeneration" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/payroll/tdsGeneration.action"><span>TDS&nbsp;Generation</span></a></li>
                    <li><a id="serviceschangeMyPayrollPassword" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/payroll/changeMyPayrollPassword.action"><span>Reset Payroll Pwd</span></a></li>
                   <%-- <li><a id="servicesgetDeductionsAndCommissions" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/payroll/getDeductionsAndCommissions.action"><span>Deduction&Commissions</span></a></li> --%>
                </ul>
            </li>
              <li class='has-sub'><a href='#' id="iconToggleTeam"><span id="teamAdmin">Dashboards</span></a>
                <ul id="teamDisplay">
                     <li><a id="servicespayrollCheck" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/payroll/payrollCheck.action"><span>Payroll&nbsp;Check</span></a></li>
                   
                </ul>
            </li>
        </ul>
    </div>
</body>
</html>