<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<TABLE cellpadding="2" cellspacing="2"  border="0" width="150" bgcolor="#3E93D4">
    
    <COL width="30">
    <COL width="120" >
    
   <%--  <TR valign="top"><td colspan=3 class="LeftMenuHead"> <B>My</B></td></TR>
    <TR valign="top"><td/><td><a class="LeftMenuItem" href="/<%=ApplicationConstants.CONTEXT_PATH%>/projects/issues/getProjIssueList.action?issueType=My">Issues</a></td></TR>
    <TR valign="top"><td/><td><a class="LeftMenuItem" href="/<%=ApplicationConstants.CONTEXT_PATH%>/projects/issues/getMyTasks.action">Tasks</a></td></TR>    
    --%>
    
   <%-- <TR valign="top"><td colspan=3 class="LeftMenuHead"><B>Project</B></td></TR>
    <TR valign="top"><td/><td><a class="LeftMenuItem" href="/<%=ApplicationConstants.CONTEXT_PATH%>/customer/custHome.action">Projects</a></td></TR>
    <TR valign="top"><td/><td><a class="LeftMenuItem" href="/<%=ApplicationConstants.CONTEXT_PATH%>/projects/issues/getProjIssueList.action?issueType=All">Issues</a></td></TR>    
    <TR valign="top"><td/><td><a class="LeftMenuItem" href="/<%=ApplicationConstants.CONTEXT_PATH%>/projects/issues/getProjIssueList.action?issueType=Bug">Bugs</a></td></TR>
    --%>
    <%--
    <TR valign="top"><td/><td><a class="LeftMenuItem" href="#">Status Reports</a></td></TR>
    <TR valign="top"><td/><td><a class="LeftMenuItem" href="#">Meeting Minutes</a></td></TR>
    <TR valign="top"><td/><td><a class="LeftMenuItem" href="#">Project Plans</a></td></TR>
    <TR valign="top"><td/><td><a class="LeftMenuItem" href="#">Task Tracking</a></td></TR>
    <TR valign="top"><td/><td><a class="LeftMenuItem" href="#">Requirement Spec</a></td></TR>
    <TR valign="top"><td/><td><a class="LeftMenuItem" href="#">Functional Spec</a></td></TR>
    <TR valign="top"><td/><td><a class="LeftMenuItem" href="#">Design Spec</a></td></TR>
    <TR valign="top"><td/><td><a class="LeftMenuItem" href="#">Test Data</a></td></TR>
    <TR valign="top"><td/><td><a class="LeftMenuItem" href="#">Test Results</a></td></TR>
    --%>
   
     <TR valign="top"><td colspan=3 class="LeftMenuHead"><B>My</B></td></TR>
     <TR valign="top"><td/><td><a class="LeftMenuItem" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/tasks/createTask.action">Create Task</a></td></TR>
     <TR valign="top"><td/><td><a class="LeftMenuItem" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/tasks/getTasks.action?issueList=1">Tasks</a></td></TR>
    <%--<TR valign="top"><td/><td><a class="LeftMenuItem" href="#">Attachment Search</a></td></TR>
    <TR valign="top"><td/><td><a class="LeftMenuItem" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/employeeSearch.action">Employee Search</a></td></TR> --%>
    <TR valign="top"><td/><td><a class="LeftMenuItem" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/timesheets/newtimeSheet.action?sheetType=c">TimeSheets</a></td></TR>
    
    <TR/>
 
    <s:if test="#session.customerRole!=1 && #session.customerRole != 8">
     <TR valign="top"><td colspan=3 class="LeftMenuHead"><B>Team</B></td></TR>
       <TR valign="top"><td/><td><a class="LeftMenuItem" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/tasks/getTeamTasks.action?issueList=1">Tasks</a></td></TR>
    <%--<TR valign="top"><td/><td><a class="LeftMenuItem" href="#">Attachment Search</a></td></TR>
    <TR valign="top"><td/><td><a class="LeftMenuItem" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/employeeSearch.action">Employee Search</a></td></TR> --%>
    <TR valign="top"><td/><td><a class="LeftMenuItem" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/timesheets/newteamTimeSheets.action">TimeSheets</a></td></TR>
    
    <TR/>
    </s:if>
    <TR valign="top"><td colspan=3 class="LeftMenuHead"><B>Services</B></td></TR>
    <%--<TR valign="top"><td/><td><a class="LeftMenuItem" href="#">Attachment Search</a></td></TR>
    <TR valign="top"><td/><td><a class="LeftMenuItem" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/employeeSearch.action">Employee Search</a></td></TR> --%>
    <s:if test="#session.customerRole == 8 ">
   <%-- <TR valign="top"><td/><td><a class="LeftMenuItem" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/timesheets/empTimeSheets.action">Emp TimeSheets</a></td></TR> --%>
    <TR valign="top"><td/><td><a class="LeftMenuItem" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/timesheets/newempTimeSheets.action">Emp TimeSheets</a></td></TR>
    </s:if>
    <TR valign="top"><td/><td><a class="LeftMenuItem" href="/<%=ApplicationConstants.CONTEXT_PATH%>/general/resetCustPassword.action">Change My Pwd</a></td></TR>
    <TR/>
</TABLE>


