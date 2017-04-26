<!doctype html>
<html lang=''>
    <%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
 <%@ taglib prefix="s" uri="/struts-tags" %>
 <%@page import="java.util.Map"%>
<head>
   <meta charset='utf-8'>
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta name="viewport" content="width=device-width, initial-scale=1">
   
   <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/menu/toggleMenu.css"/>">
 <%--<script type="text/JavaScript" src="<s:url value="/includes/javascripts/jquery-latest.min.js"/>"></script> --%>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/jquery.min.js"/>"></script>
    <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/jquery.js"/>"></script>  
   <script type="text/JavaScript" src="<s:url value="/includes/javascripts/menu/menuEmployeeNew.js?version=2.2"/>"></script>
    <script type="text/javascript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
    <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/ajaxfileupload.js"/>"></script>  
 <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/FileUpload.js"/>"></script> 
  

   
</head>

<body>
<div id='cssmenu'>
        <%
                String actionName = ActionContext.getContext().getName();
                 String searchflag= request.getParameter("searchflag");
                //System.out.println("action name"+actionName);
//                        System.out.println("search flag name"+searchflag);
                %>
                <span id="actionForLeftMenu" style="display: none"><%=actionName%></span> 
                  <span id="searchFlagForLeftMenu" style="display: none"><%=searchflag%></span> 
         <ul>
   <s:hidden id="EmployeeTypeId" value="%{#session.sessionEmployeeTypeId}"></s:hidden> 
   <s:if test="#session.sessionEmployeeTypeId =='Contractor'">
              <li class='has-sub' ><a href='#' id="iconToggleMy"><span id="myAdmin">My</span></a>
               <ul id="myDisplay">
              <li><a  id="myCreateTask"  href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/tasks/newCreateTask.action" onClick="leftMenuToggleId(this,'myAdmin');"><span>Create Task</span></a></li>
              <li><a id="myTasks"  href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/tasks/getTasks.action?issueList=1" onClick="leftMenuToggleId(this,'myAdmin');"><span>Tasks&nbsp;List</span></a></li>
              <li class='last'><a   id="myTimeSheets" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/timesheets/newtimeSheet.action" onClick="leftMenuToggleId(this,'myAdmin');"><span>Time Sheets</span></a></li>
          </ul>
          </li>
          <li class='has-sub'><a href='#' id="iconToggleServices"><span id="servicesAdmin">Services</span></a>
       
      <ul id="servicesDisplay">
              <li class='last'><a id="servicesResetMyPwd" href="/<%=ApplicationConstants.CONTEXT_PATH%>/general/resetPassword.action" onClick="leftMenuToggleId(this,'servicesAdmin');"><span>Reset My Pwd</span></a></li>    
                  </ul>
              </li>
          </s:if><s:else>
              <li class='has-sub' ><a href='#' id="iconToggleMy"><span id="myAdmin">My</span></a>
               <ul id="myDisplay">
                   <li><a  id="myCreateTask" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/tasks/newCreateTask.action" onClick="leftMenuToggleId(this,'myAdmin');"><span>Create Task</span></a></li>
                   <li><a id="myTasks"  href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/tasks/getTasks.action?issueList=1" onClick="leftMenuToggleId(this,'myAdmin');"><span>Tasks&nbsp;List</span></a></li>
                   <li><a   id="myTimeSheets" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/timesheets/newtimeSheet.action" onClick="leftMenuToggleId(this,'myAdmin');"><span>Time Sheets</span></a></li>
                   <li><a   id="myApplyLeave" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/leaveRequestList.action" onClick="leftMenuToggleId(this,'myAdmin');"><span>Apply Leave</span></a></li>
         <li><a   id="myProfile" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/getProfile.action" onClick="leftMenuToggleId(this,'myAdmin');"><span>Profile</span></a></li>
         <li><a   id="myAuthoredTasks" href="/<%=ApplicationConstants.CONTEXT_PATH%>/ecertification/getMyAuthoredExamsList.action" onClick="leftMenuToggleId(this,'myAdmin');"><span>Authored Exams</span></a></li>
         <li><a   id="myECertification" href="/<%=ApplicationConstants.CONTEXT_PATH%>/ecertification/getEcertification.action" onClick="leftMenuToggleId(this,'myAdmin');"><span>E&nbsp;Certification</span></a></li>
        <%-- <li><a   id="myCalendar" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/calendar/empCalendar.action"><span>Calendar</span></a></li> --%>
         <%-- <li><a   id="myProjectDashboard" href="/<%=ApplicationConstants.CONTEXT_PATH%>/projects/projectDashBoard.action"><span>Projects&nbsp;Dashboard</span></a></li> --%>
             <%
Map sesrolesMap = (Map)session.getAttribute("myRoles");
if(sesrolesMap.containsValue("Admin")){
%>
<%-- <li><a id="myPmoActivity" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/pmoActivity.action"><span>PMO Activity</span></a></li> --%>
<li><a id="myCreateProject" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/getCustomerProjectsList.action" onClick="leftMenuToggleId(this,'myAdmin');"onClick="leftMenuToggleId(this,'myAdmin');"><span>Create&nbsp;Project</span></a></li>
<%-- <li><a   id="mypmoDashBoard" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/pmoDashBoard.action"><span>PMO DashBoard</span></a></li> --%>
    <%} else {%>
    
         <s:if test="(#session.sessionEmpPractice == 'PMO' || #session.empPmoActivityAccess==1) ">
         <%-- <li><a   id="myPmoActivity" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/pmoActivity.action"><span>PMO Activity</span></a></li> --%>
         <%-- <li><a   id="mypmoDashBoard" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/pmoDashBoard.action"><span>PMO DashBoard</span></a></li> --%>
           </s:if>
           <s:if test="#session.sessionEmpPractice =='PMO' && #session.isUserManager == 1">
               <li><a id="myCreateProject" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/getCustomerProjectsList.action" onClick="leftMenuToggleId(this,'myAdmin');"><span>Create&nbsp;Project</span></a></li>  
           </s:if>
               <%}%>
         <li><a   id="myPerfReview" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/Reviews/giveMyReview.action" onClick="leftMenuToggleId(this,'myAdmin');"><span>Perf.Review</span></a></li>
      <%--   <li><a   id="myNoDues" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/payroll/getEmployeeNoDues.action"><span>No&nbsp;Dues</span></a></li> --%>
         

          <li><a id="myAppreciations" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/appreciation/getMyAppreciation.action?searchflag=my" onClick="leftMenuToggleId(this,'myAdmin');"><span>Appreciations</span></a></li>
    <%-- <li  class='last'><a id="myPayroll"  href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/payroll/getEmployeePayRollDashBoard.action">Payroll</a></li>  --%>
     <s:if test="#session.livingCountryList == 'India'">
          <li  class='last'><a id="myPayroll"  href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/payroll/getEmployeePayRollDashBoard.action" onClick="leftMenuToggleId(this,'myAdmin');">Payroll</a></li> 
          
    </s:if>
          <s:if test="#session.livingCountryList == 'India' && (#session.myDeptId == 'GDC' || #session.myDeptId == 'SSG')">
                                    <li><a id="myAppriasal" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/appraisal/myQuarterlyAppraisalSearch.action" onClick="leftMenuToggleId(this,'myAdmin');"><span>Q-Review</span></a></li>
                                </s:if>
               </ul>
   </li>
  <s:hidden id="userManager" value="%{#session.isUserManager}"></s:hidden> 
   <s:hidden id="teamLead" value="%{#session.isUserTeamLead}"></s:hidden> 
    <s:if test="#session.isUserManager == 1 || #session.isUserTeamLead==1">
   <li class='has-sub' ><a href='#' id="iconToggleTeam"><span id="teamAdmin">Team</span></a>
      <ul id="teamDisplay">
         <li><a id="teamTasks" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/tasks/getTeamTasks.action?issueList=1" onClick="leftMenuToggleId(this,'teamAdmin');"><span>Tasks</span></a></li>
         <li><a id="teamLeaveApprovals" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/leaveApprovalList.action" onClick="leftMenuToggleId(this,'teamAdmin');"><span>Leave Approvals</span></a></li>
         <li><a id="teamLeavesApplied" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/employeeLeave.action" onClick="leftMenuToggleId(this,'teamAdmin');"><span>Leaves Applied</span></a></li>
         <li><a id="teamTimeSheets" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/timesheets/newEmployeeteamTimeSheets.action" onClick="leftMenuToggleId(this,'teamAdmin');"><span>Time Sheets</span></a></li>
         <li><a id="teamHierarchy" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/myGDCTeamTree.action" onClick="leftMenuToggleId(this,'teamAdmin');"><span>Hierarchy</span></a></li>
         <li><a id="teamPerfReview" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/Reviews/teamReviewList.action" onClick="leftMenuToggleId(this,'teamAdmin');"><span>Perf.Review</span></a></li>
         <li><a id="teamAppreciations" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/appreciation/getMyAppreciation.action?searchflag=team" onClick="leftMenuToggleId(this,'teamAdmin');"><span>Appreciations</span></a></li>
         <s:if test="#session.title == 'Delivery Manager'">
             <li class='last'><a id="teamDashboard" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/getManagerDashBoard.action" onClick="leftMenuToggleId(this,'teamAdmin');"><span>Dashboard</span></a></li>
         </s:if>
          <s:if test="#session.isUserManager == 1  && (#session.myDeptId == 'GDC' || #session.myDeptId == 'SSG')">
                                <li><a id="teamQuaterAppraisalSearch" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/appraisal/teamQuaterAppraisalSearch.action" onClick="leftMenuToggleId(this,'teamAdmin');"><span>Q-Review</span></a></li>
                                </s:if>   
             
      </ul>
   </li>
       </s:if>
   <li class='has-sub'  ><a href='#' id="iconToggleServices"> <span id="servicesAdmin">Services</span></a>
       
      <ul id="servicesDisplay">
          <s:if test="#session.userId == 'mlokam' || #session.userId == 'clokam' || #session.userId == 'rijju' || #session.userId == 'vkandregula' || #session.userId =='ukodati'">
        <li><a id="servicesSuggestionBoxData" href="/<%=ApplicationConstants.CONTEXT_PATH%>/marketing/getSuggestionBox.action" onClick="leftMenuToggleId(this,'servicesAdmin');"><span>Suggestion&nbsp;Box&nbsp;Data</span></a></li>
        </s:if>
        
        <s:if test="#session.isUserManager == 1">
      <%--   <li><a id="servicesMyCopReport"  href="/<%=ApplicationConstants.CONTEXT_PATH%>/reports/mycopreport.action"><span>MyCopReport</span></a></li> --%>
             </s:if>
<!--         <li><a id="servicesBridgeConference" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/bridgeconference/bridgeScheduleList.action"><span>Bridge Conference</span></a></li>-->
         <li><a id="servicesEmployeeSearch" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/employeeSearch.action" onClick="leftMenuToggleId(this,'servicesAdmin');"><span>Employee Search</span></a></li>
         <li><a id="servicesResetMyPwd" href="/<%=ApplicationConstants.CONTEXT_PATH%>/general/resetPassword.action" onClick="leftMenuToggleId(this,'servicesAdmin');"><span>Reset My Pwd</span></a></li>
         <%--<li><a href="#" id="mailid" onclick="return win_open('/<%=ApplicationConstants.CONTEXT_PATH%>/services/mail/MailWindow.jsp');"><span>Mail Service</span></a></li>--%>
      <%--   <li><a id="servicesTechReviews" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/consultantTechReviews.action"><span>Tech Reviews</span></a></li> --%>
        <%-- <li><a id="servicesReleaseNotes" href="/<%=ApplicationConstants.CONTEXT_PATH%>/general/releaseNotes.action"><span>Release&nbsp;Notes</span></a></li> --%>
         <%--  <s:textfield id="email2"  name="email2"  value="%{#session.itTeam}" /> --%>
         <s:if test="#session.itTeam == 'yes' ||  #session.isAdminAccess == 1">
             <li><a id="servicesBridge" href="/<%=ApplicationConstants.CONTEXT_PATH%>/bms/bridgeSearch.action" onClick="leftMenuToggleId(this,'servicesAdmin');"><span>Bridge</span></a></li>
         </s:if>
             <li class='last'><a id="servicesBMS" href="/<%=ApplicationConstants.CONTEXT_PATH%>/bms/bmsEvent.action" onClick="leftMenuToggleId(this,'servicesAdmin');"><span>Bridge&nbsp;Mgmt&nbsp;System</span></a></li>
             
             <s:if test="#session.sessionEmpPractice != 'PMO' && #session.empPmoActivityAccess!=1 && #session.isAdminAccess != 1 && #session.isUserManager == 1 && (#session.myDeptId=='GDC' || #session.myDeptId=='SSG')">
         <li><a   id="servicepmoDashBoard" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/pmoDashBoard.action" onClick="leftMenuToggleId(this,'servicesAdmin');"><span>Available&nbsp;Employee</span></a></li>
           </s:if>
             <s:if test="#session.sessionHierarchyAccess == 1">
                 <li class='last'><a id="getTeamHirearchy" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/hierarchy/getTeamHirearchy.action" onClick="leftMenuToggleId(this,'servicesAdmin');"><span>Employee&nbsp;Hierarchy</span></a></li>  
             </s:if>
                 <s:if test="#session.userId == 'ddean' || #session.userId == 'ssoni' || #session.userId == 'vmada' || #session.userId == 'vkandregula' ||  #session.isAdminAccess == 1">
                      <li class='last'><a id="clientReqEngagementApprovalsSearch"  href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/accounts/clientReqEngagementApprovalsSearch.action?backToFlag=No" onClick="leftMenuToggleId(this,'servicesAdmin');"><span>PSCER/RFP</span></a></li>
                 </s:if>
                     
      </ul>
      
   </li>
   <s:hidden id="isCRETeam" value="%{#session.isCRETeam}"></s:hidden>
   <s:if test="#session.isCRETeam == 1">
       <li class='has-sub' ><a href='#' id="iconToggleMile"><span id="mileAdmin">Mile</span></a>
           <ul id="mileDisplay">
               <li><a id="mileCreConsultents" href="/<%=ApplicationConstants.CONTEXT_PATH%>/cre/creConsultantList.action" onClick="leftMenuToggleId(this,'mileAdmin');"><span>CRE&nbsp;Consultents</span></a></li>
               <li><a id="mileGetConsultant" href="/<%=ApplicationConstants.CONTEXT_PATH%>/cre/getCreRecords.action" onClick="leftMenuToggleId(this,'mileAdmin');"><span>Get&nbsp;Consultant</span></a><li>
               <li class='last'><a id="mileSetCreExam" href="/<%=ApplicationConstants.CONTEXT_PATH%>/cre/setCreExam.action" onClick="leftMenuToggleId(this,'mileAdmin');"><span>Set&nbsp;CRE&nbsp;Exam</span></a></li> 
           </ul>
       </li>
       
       <!-- mcert menu satrt -->
         <li class='has-sub' ><a href='#' id="iconToggleMcert"><span id="mcertAdmin">Mcertification</span></a>
           <ul id="mcertDisplay">
               <li><a id="mcertConsultantList" href="/<%=ApplicationConstants.CONTEXT_PATH%>/mcertification/mcertConsultantList.action" onClick="leftMenuToggleId(this,'mcertAdmin');"><span>Mcert&nbsp;Consultents</span></a></li>
          <%--     <li><a id="getMcertRecords" href="/<%=ApplicationConstants.CONTEXT_PATH%>/mcertification/getMcertRecords.action"><span>Get&nbsp;Mcert&nbsp;Consultant</span></a><li> --%>
               <li class='last'><a id="setMcertExam" href="/<%=ApplicationConstants.CONTEXT_PATH%>/mcertification/setMcertExam.action" onClick="leftMenuToggleId(this,'mcertAdmin');"><span>Set&nbsp;MCERT&nbsp;Exam</span></a></li> 
              <li><a id="getMcertExamResultsList" href="/<%=ApplicationConstants.CONTEXT_PATH%>/mcertification/getMcertExamResultsList.action" onClick="leftMenuToggleId(this,'mcertAdmin');"><span>Exam&nbsp;Result</span></a></li>
       
           </ul>
       </li>
       <!-- mcert menu end -->
   </s:if>
       
        <li class='has-sub' ><a href='#' id="iconToggleDashboards"><span id="dasboardAdmin">DashBoards&amp;Reports</span></a>
                <ul id="dashboardDisplay">
                            
                            <%
                                Map sesirolesMap = (Map) session.getAttribute("myRoles");
                                if (sesirolesMap.containsValue("Admin")) {
                            %>
                            <li><a   id="pmoDashBoard" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/pmoDashBoard.action"  onClick="leftMenuToggleId(this,'dasboardAdmin');"><span>PMO&nbsp;</span></a></li>
                            <li><a   id="myPmoActivity" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/pmoActivity.action"  onClick="leftMenuToggleId(this,'dasboardAdmin');"><span>PMO Activity</span></a></li>
                            <%} else {%>

                            <s:if test="(#session.sessionEmpPractice == 'PMO' || #session.empPmoActivityAccess==1) ">
                                <li><a   id="pmoDashBoard" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/pmoDashBoard.action"  onClick="leftMenuToggleId(this,'dasboardAdmin');"><span>PMO&nbsp;</span></a></li>
                                <li><a   id="myPmoActivity" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/pmoActivity.action"  onClick="leftMenuToggleId(this,'dasboardAdmin');"><span>PMO Activity</span></a></li>
                            </s:if>
                            <%}%>
                             <s:if test="#session.sessionEmpPractice == 'PMO' || #session.empPmoActivityAccess == 1 ||  #session.isAdminAccess == 1">
                      <li class='last'><a id="projectPortfolioReport"  href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/projectPortfolioReport.action"  onClick="leftMenuToggleId(this,'dasboardAdmin');"><span>Project&nbsp;Portfolio&nbsp;Report</span></a></li>
                       </s:if>
                            <li><a   id="projectDashBoard" href="/<%=ApplicationConstants.CONTEXT_PATH%>/projects/projectDashBoard.action"  onClick="leftMenuToggleId(this,'dasboardAdmin');"><span>Projects&nbsp;</span></a></li>
                            <li><a id="getTaskDashBoard" href="/<%=ApplicationConstants.CONTEXT_PATH%>/employee/tasks/getTaskDashBoard.action"  onClick="leftMenuToggleId(this,'dasboardAdmin');"><span>Tasks&nbsp;</span></a></li>

                            


                        </ul>
                    </li>
       
</s:else>
       
       
</ul>
</div>

</body>
</html>
