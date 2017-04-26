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
        <%--   <script type="text/JavaScript" src="<s:url value="/includes/javascripts/jquery-latest.min.js"/>"></script>  --%>
         <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/jquery.min.js"/>"></script>
    <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/jquery.js"/>"></script>  
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/menu/MenuAdmin.js?ver=1.0"/>"></script>
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
                        <li ><a  id="empSearchTeam" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/empSearchMyTeam.action"><span>Team</span></a></li>

                        <li ><a id="accountList"  href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/accounts/accountsListMy.action?accList=1"><span>Accounts</span></a></li>
                        <li><a   id="newtimeSheet"  href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/timesheets/newtimeSheet.action"><span>Time Sheets</span></a></li>
                        <li class='last'><a id="calendarId" href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/activities/calendar.action"><span>Calendar</span></a></li>
                    </ul>
                </li>
                <li class='has-sub' ><a href='#' id="iconToggleTeam"><span id="teamAdmin">Team</span></a>
                    <ul id="teamDisplay">
                        <li><a id="teamAccounts" href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/accounts/accountsListMyTeam.action?accList=1"><span>Accounts</span></a></li>
                        <li class='last'><a id="teamreAssign" href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/accounts/reAssign.action"><span>Asign Accounts</span></a></li>
                    </ul>
                </li>
                <li class='has-sub'  ><a href='#' id="iconToggleServices"> <span id="servicesAdmin">Services</span></a>
                    <ul id="servicesDisplay">
                        <li><a id="servicesaddAccount" href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/accounts/account.action"><span>Add Account</span></a></li>
                        <li><a id="servicesallAccounts"  href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/accounts/accountsListAll.action?accList=1"><span>All Accounts</span></a></li>
                        <li><a id="servicesaccountsSearch" href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/accounts/accountSearch.action"><span>Accounts Search</span></a></li>
                        <li><a id="servicesmergeAccounts" href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/accounts/mergeAccount.action"><span>Merge Accounts</span></a></li>
                        <li><a id="servicesGreensheets" href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/greensheets/greenSheet.action"><span>Greensheets</span></a></li>
                        <li><a id="servicesResetMyPwd" href="/<%=ApplicationConstants.CONTEXT_PATH%>/general/resetPassword.action"><span>Reset My Pwd</span></a></li>
                        <li><a id="servicesResetUserPwd" href="/<%=ApplicationConstants.CONTEXT_PATH%>/admin/changePassword.action"><span>Reset User Pwd</span></a></li>
                        <li><a id="servicesResetCustPwd" href="/<%=ApplicationConstants.CONTEXT_PATH%>/admin/changeCustPassword.action"><span>Reset Cust Pwd</span></a></li>
                        <li><a id="servicesEmpSearch" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/empSearchAll.action"><span>Emp Search</span></a></li>
                     <%--   <li><a id="servicesEmpReport" href="/<%=ApplicationConstants.CONTEXT_PATH%>/reports/employeeReports.action"><span>Emp Report</span></a></li> --%>
                        <li><a id="servicesExamsList" href="/<%=ApplicationConstants.CONTEXT_PATH%>/ecertification/examsList.action"><span>Exams List</span></a></li>
                        <li><a  id="mailid" href="#" onclick="return win_open('/<%=ApplicationConstants.CONTEXT_PATH%>/services/mail/MailWindow.jsp');"><span>Mail Service</span></a></li>
                        <li><a id="servicesContactsSummary" href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/activities/accountsContacts.action"><span>Contacts Summary</span></a></li>
                        <li><a id="servicesExecutiveDashboard" href="/<%=ApplicationConstants.CONTEXT_PATH%>/admin/getExecutiveDashBoard.action"><span>Executive&nbsp;Dashboard</span></a></li>
                        <li ><a id="servicesExcelUpload" href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/accounts/excelUpload.action"><span>Accounts&nbsp;Excel&nbsp;Upload</span></a></li>
                        <li class='last'><a id="getNewsLetters" href="/<%=ApplicationConstants.CONTEXT_PATH%>/admin/getNewsLetters.action"><span>Newsletters</span></a></li>
                     <li class='last'><a id="getBdmAssociates" href="/<%=ApplicationConstants.CONTEXT_PATH%>/admin/getBdmAssociates.action"><span>BDM Associations</span></a></li>
                    </ul>
                     </li>
            </ul>
       

    
</div>

</body>
</html>