<%-- 
    Document   : PerfDashboard
    Created on : Jun 19, 2015, 3:02:16 PM
    Author     : miracle
--%>

<%-- 
    Document   : PerfDashboard
    Created on : May 15, 2015, 5:18:47 PM
    Author     : miracle
--%>

<%@page import="com.mss.mirage.employee.Reviews.ReviewVTO"%>
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%-- <%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<html>
    <head>
        <title>Hubble Organization Portal :: DashBoard</title>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>"/>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/animatedcollapse.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/jquery-1.2.2.pack.js"/>"></script>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/AjaxReviewList.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/GridNavigation.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmpStandardClientValidations.js"/>"></script>
        <s:include value="/includes/template/headerScript.html"/>
        <script language="JavaScript">
                 
                animatedcollapse.addDiv('topPerformersDiv', 'fade=1;speed=400;persist=1;group=app');
                animatedcollapse.addDiv('competionBoardDiv', 'fade=1;speed=400;persist=1;group=app');
                animatedcollapse.addDiv('personalRecordDiv', 'fade=1;speed=400;persist=1;group=app');
                animatedcollapse.addDiv('pastReviewDiv', 'fade=1;speed=400;persist=1;group=app');
                animatedcollapse.init();
       </script>
        
    </head>
   <!-- <body class="bodyGeneral" onload="hideSelect();" oncontextmenu="return false;"> -->
    <body class="bodyGeneral" oncontextmenu="return false;">

        <%!
            /* Declarations */
            String queryString = null;
            Connection connection;
            Statement stmt;
            ResultSet rs;
            int userCounter = 0;
            int activityCounter = 0;
            int accountCounter = 0;
        %>

<%String contextPath = request.getContextPath();
                    %>
        <!--//START MAIN TABLE : Table for template Structure-->
        <table class="templateTable1000x580" align="center" cellpadding="0" cellspacing="0">

            <!--//START HEADER : Record for Header Background and Mirage Logo-->
            <tr class="headerBg">
                <td valign="top">
                    <s:include value="/includes/template/Header.jsp"/>                    
                </td>
            </tr>
            <!--//END HEADER : Record for Header Background and Mirage Logo-->

            <!--//START DATA RECORD : Record for LeftMenu and Screen Content-->
            <tr>
                <td>
                    <table class="innerTable1000x515" cellpadding="0" cellspacing="0">
                        <tr>
                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                            <td width="850px;" class="leftMenuBgColor" valign="top"> 
                                <s:include value="/includes/template/LeftMenu.jsp"/>
                            </td>
                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->

                            <!--//START DATA COLUMN : Coloumn for Screen Content-->
                            <td width="850px" class="cellBorder" valign="top" style="padding-left:10px;padding-top:5px;">
                                <!--//START TABBED PANNEL : -->
                                <%--      <sx:tabbedpanel id="resetPasswordPannel" cssStyle="width: 845px; height: 550px;padding-left:10px;padding-top:5px;" doLayout="true" useSelectedTabCookie="true" > 
                                    
                                    <!--//START TAB : -->
                                    <sx:div id="dashBoardTab" label="DashBoard Details" cssStyle="overflow:auto;"> --%>
                                <ul id="accountTabs" class="shadetabs" >
                                    <li><a href="#" rel="accountsListTab" class="selected"> Perf.DashBoard Details </a></li>

                                </ul>
                                <div  style="border:1px solid gray; width:840px;height: 550px;overflow:auto; margin-bottom: 1em;">    
                                    <div id="accountsListTab" class="tabcontent" >  
                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                            <tr>
                                                <td height="20px" >

                                                </td>
                                            </tr>
                                             <tr>
                                                <td class="homePortlet" valign="top">
                                                    <div class="portletTitleBar">
                                                        <div class="portletTitleLeft">Top Performers</div>
                                                        <div class="portletIcons">
                                                            <a href="javascript:animatedcollapse.hide('topPerformersDiv')" title="Minimize">
                                                                <img src="../../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                            <a href="javascript:animatedcollapse.show('topPerformersDiv')" title="Maximize">
                                                                <img src="../../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                            </a>                                                           
                                                        </div>
                                                        <div class="clear"></div>
                                                    </div>
                                                    <div id="topPerformersDiv" style="background-color:#ffffff">
                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                            <tr>
                                                                <td width="40%" valign="top" align="center">
                                                                    <s:form action="dashBoard" theme="simple" name="dashboardRep">   

                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                                            <tr align="right">
                                                                                <td class="headerText" colspan="9">
                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">                                                        
                                                                                </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td>
                                                                                    <table border="0" align="center" cellpadding="0" cellspacing="0">
                                                                                        <tr>                                                    
                                                                                            <td class="fieldLabel">StartDate:</td> <!--value="%{dateWithOutTime}" -->

                                                                                            <td><s:textfield name="startDate" id="startDate" cssClass="inputTextBlue" onchange="checkDates(this);"/> <a href="javascript:calRep.popup();">
                                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                         width="20" height="20" border="0"></td>    

                                                                                                    <td class="fieldLabel">EndDate</td>
                                                                                                    <td><s:textfield name="endDate" id="endDate" cssClass="inputTextBlue" onchange="checkDates(this);"/><a href="javascript:calRep1.popup();">
                                                                                                            <img  src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                                  width="20" height="20" border="0">  </td>
                                                                                                           
                                                                                                            </tr><tr>
                                                                                                            <td class="fieldLabel" align="right">Department :</td>
                                                                                                            <td>
                                                                                                                <s:select label="Select Department" 
                                                                                                                      name="departmentId"
                                                                                                                      id="departmentId2"
                                                                                                                      headerKey=""
                                                                                                                      headerValue="All"
                                                                                                                  list="{'Executive Board','Development','Marketing','Operations','Recruiting','Sales'}" cssClass="inputSelect" value="%{departmentId}"/> </td>

                                                                                                            <td class="fieldLabel">
                                                                                                                <s:checkbox name="isManagerInclude" id="isManagerInclude"></s:checkbox>
                                                                                                           </td> <td class="fieldLabelLeft">
                                                                                                                Include&nbsp;Mangers
                                                                                                            </td>

                                                                                                            <td colspan="4" align="right">
                                                                                                               

                                                                                                                <input type="button" value="Search" class="buttonBg" onclick="getTopPerformers();"/>
                                                                                                            </td> 
                                                                                                            </tr>

                                                                                                            </table>
                                                                                                    </td>
                                                                                        </tr>

                                                                                        <tr>
                                                                                            <td height="20px" align="center" colspan="9">
                                                                                                <div id="loadingMessage" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                            </td>
                                                                                        </tr>

                                                                                        <tr>
                                                                                            <td>
                                                                                                <br>
                                                                                                <table align="center" cellpadding="2" border="0" cellspacing="1" width="50%" >

                                                                                                    <tr>
                                                                                                        <td >
                                                                                                            <br>
                                                                                                            <div id="topPerfReviewsList" style="display: block">
                                                                                                                <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                                                                <table id="tblTopPerfReviews" align='center' cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750'>
                                                                                                                    <COLGROUP ALIGN="left" >
                                                                                                                        <COL width="5%">
                                                                                                                        <COL width="15%">
                                                                                                                        <COL width="10%">
                                                                                                                        <COL width="10%">
                                                                                                                        <COL width="13%">
                                                                                                                </table>
                                                                                                                <br>
                                                                                                                <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                                                                            </div>
                                                                                                        </td>
                                                                                                    </tr>
                                                                                                </table>    
                                                                                            </td>
                                                                                        </tr>

                                                                                    </table>
                                                                                </s:form>
                                                                            </td>
                                                                        </tr>
                                                                    </table>
                                                                    <script type="text/JavaScript">
                                                                        var calRep = new CalendarTime(document.forms['dashboardRep'].elements['startDate']);
                                                                        calRep.year_scroll = true;
                                                                        calRep.time_comp = false;
                                                                        calRep1 = new CalendarTime(document.forms['dashboardRep'].elements['endDate']);
                                                                        calRep1.year_scroll = true;
                                                                        calRep1.time_comp = false;
                                                                    </script>
                                                                    </div>
                                                                </td>
                                                            </tr>

<!-- Competion board start -->
   <tr>
                                                <td class="homePortlet" valign="top">
                                                    <div class="portletTitleBar">
                                                        <div class="portletTitleLeft">Competition Board </div>
                                                        <div class="portletIcons">
                                                            <a href="javascript:animatedcollapse.hide('competionBoardDiv')" title="Minimize">
                                                                <img src="../../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                            <a href="javascript:animatedcollapse.show('competionBoardDiv')" title="Maximize">
                                                                <img src="../../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                            </a>                                                           
                                                        </div>
                                                        <div class="clear"></div>
                                                    </div>
                                                    <div id="competionBoardDiv" style="background-color:#ffffff">
                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                            <tr>
                                                                <td width="40%" valign="top" align="center">
                                                                    <s:form action="getCompetitionBoard" theme="simple" name="competitionBoard" id="competitionBoard">   

                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                                            <tr align="right">
                                                                                <td class="headerText" colspan="9">
                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">                                                        
                                                                                </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td>
                                                                                    <table border="0" align="center" cellpadding="0" cellspacing="0">
                                                                                        <tr>                                                    
                                                                                            <td class="fieldLabel">StartDate:</td> <!--value="%{dateWithOutTime}" -->

                                                                                            <td><s:textfield name="startDate" id="CompetitionStartDate" cssClass="inputTextBlue" onchange="checkDates(this);"/> <a href="javascript:calCompetition.popup();">
                                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                         width="20" height="20" border="0"></td>    

                                                                                                    <td class="fieldLabel">EndDate</td>
                                                                                                    <td><s:textfield name="endDate" id="CompetitionEndDate" cssClass="inputTextBlue" onchange="checkDates(this);"/><a href="javascript:calCompetition1.popup();">
                                                                                                            <img  src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                                  width="20" height="20" border="0">  </td>
                                                                                                           
                                                                                                            </tr>

                                                                                        <tr>                                                    
                                                                                            <td class="fieldLabel">Name:</td> <!--value="%{dateWithOutTime}" -->

                                                                                            <td><s:textfield name="employeeName" id="employeeName" cssClass="inputTextBlue" value="%{employeeName}"/> </td>    

                                                                                                    
                                                                                                    <td class="fieldLabel" align="right">Department :</td>
                    
                                                                                                     <td><%-- <s:select label="Select Department" 
                                                                                                                      name="departmentId"
                                                                                                                      id="departmentId"
                                                                                                                      headerKey=""
                                                                                                                      headerValue="All"
                                                                                                                  list="departmentIdList" cssClass="inputSelect" value="%{departmentId}"/> --%>
                                                                                                         <s:select label="Select Department" 
                                                                                                                      name="departmentId"
                                                                                                                      id="departmentId"
                                                                                                                      headerKey=""
                                                                                                                      headerValue="All"
                                                                                                                  list="{'Executive Board','Development','Marketing','Operations','Recruiting','Sales'}" cssClass="inputSelect" value="%{departmentId}"/>


</td>                <td colspan="4" align="center">
                                                                                                               

                                                                                                                <input type="submit" value="Search" class="buttonBg"/>
                                                                                                            </td> 
                                                                                                            </tr>

                                                                                                            </table> 
                                                                                                    </td>
                                                                                        </tr>

                                                                                     

                     <s:if test="#session.competitionBoardList != null"> 
                    <tr>
                        <td>
                          <table id="results" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='90%' align="center">
                               
                              <%java.util.List mainList = (java.util.List) session.getAttribute("competitionBoardList");
                                if(mainList.size()>0){


%>
                              
                             <tr class="gridHeader">
                                                        <th>Rank</th>
                                                        <th>Employee&nbsp;Name</th>
                                                        <th>Designation</th>
                                                          <s:if test="%{departmentId == 'Sales'}">
                                                             <th>Practice</th>
                                                             <th>Region</th>
                                                        </s:if>
                                                        <th>Department</th>
                                                        <th>Score</th>
                                                        <th>Reviews&nbsp;Submitted</th>
                                                        <th>Reviews&nbsp;Approved</th>
                                                        <th>Review&nbsp;Approval&nbsp;Percentage</th>
                                                        
                                                    </tr>
                          <%
             
             for (int i = 0; i < mainList.size(); i++) {
                 %>
                 <tr CLASS="gridRowEven">
                            <%
                 //java.util.List subList = (java.util.List)mainList.get(i);
                            ReviewVTO reviewVTO = (ReviewVTO)mainList.get(i);
               //  for (int j = 0; j < subList.size(); j++) {
                     
                     %>
                     <td class="title">
                        
                         <%
                          out.println(reviewVTO.getRank());

%>
                     </td>       
                       <td class="title">
                         <%
                          out.println(reviewVTO.getEmployeeName());

%>
                     </td>     <td class="title">
                         <%
                     
                          out.println(reviewVTO.getDesignation());

%>
                     </td> 
                     
                     <s:if test="%{departmentId == 'Sales'}">
                         <td class="title">
                         <%
                          out.println(reviewVTO.getPractice());

%>
                     </td>   
                    <td class="title">
                         <%
                          out.println(reviewVTO.getRegion());

%>
                     </td>
                     </s:if>
                     
                     <td class="title">
                         <%
                          out.println(reviewVTO.getDepartment());

%>
                     </td>   
                    <td class="title">
                         <%
                          out.println(reviewVTO.getScore());

%>
                     </td>   <td class="title">
                         <%
                          out.println(reviewVTO.getReviewsSubmitted());

%>
                     </td>   <td class="title">
                         <%
                          out.println(reviewVTO.getReviewsApproved());

%>
                     </td>   <td class="title">
                         <%
                          out.println(reviewVTO.getReviewApprovalPercentage());

%>
                     </td>   
                     <%
                     
              //   }
                %></tr><% 
                 
             }
                          }else {
             %>
                     <tr><td>
                 <%
                // String contextPath = request.getContextPath();
           // out.println("<img  border='0' align='top'  src='"+contextPath+"/includes/images/alert.gif'/><b> No Records Found to Display!</b>");
                 
                  out.println("<img  border='0' align='top'  src='"+contextPath+"/includes/images/alert.gif'/> <font color='white'><b>No records found!</b></font>");
           // }

            %>
                     </td>
           </tr>
           <%}%>
               
                        </table>
                        </td>
                    </tr>
                    
                    
                    
                     <%
                                         
             if(mainList.size()!=0){
                %>
               
            <tr>
                
                <td align="right" colspan="4" style="background-color:white;" >
                    <div align="right" id="pageNavPosition">hello</div>
             </td>
            </tr> 
        
             <%}
                 %>
                     </s:if>

                                                                             </script>                            
       <script type="text/javascript">
        var pager = new ReviewPager('results', 20); 
        pager.init(); 
        pager.showPageNav('pager', 'pageNavPosition'); 
        pager.showPage(1);
    	</script>              </table>
                                                                                </s:form>
                                                                    <script type="text/JavaScript">
                                                                        var calCompetition = new CalendarTime(document.forms['competitionBoard'].elements['startDate']);
                                                                        calCompetition.year_scroll = true;
                                                                        calCompetition.time_comp = false;
                                                                        var calCompetition1 = new CalendarTime(document.forms['competitionBoard'].elements['endDate']);
                                                                        calCompetition1.year_scroll = true;
                                                                        calCompetition1.time_comp = false;
                                                                        
                                                                    </script>
                                                                            </td>
                                                                        </tr>
                                                                    </table>
                                                                 <%--   <script type="text/JavaScript">
                                                                        var calRep = new CalendarTime(document.forms['dashboardRep'].elements['startDate']);
                                                                        calRep.year_scroll = true;
                                                                        calRep.time_comp = fasle;
                                                                        calRep1 = new CalendarTime(document.forms['dashboardRep'].elements['endDate']);
                                                                        calRep1.year_scroll = true;
                                                                        calRep1.time_comp = fasle;
                                                                    </script> --%>
                                                                    </div>
                                                                </td>
                                                            </tr>
<!-- Competion bpard end -->
 <!-- Personal Record-->
<tr>
                                                <td class="homePortlet" valign="top">
                                                    <div class="portletTitleBar">
                                                        <div class="portletTitleLeft">Personal Record</div>
                                                        <div class="portletIcons">
                                                            <a href="javascript:animatedcollapse.hide('personalRecordDiv')" title="Minimize">
                                                                <img src="../../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                            <a href="javascript:animatedcollapse.show('personalRecordDiv')" title="Maximize">
                                                                <img src="../../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                            </a>                                                           
                                                        </div>
                                                        <div class="clear"></div>
                                                    </div>
                                                    <div id="personalRecordDiv" style="background-color:#ffffff">
                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                            <tr>
                                                                <td width="40%" valign="top" align="center">
                                                                    <s:form action="personal" theme="simple" name="personalRecord">   

                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                                            <tr align="right">
                                                                                <td class="headerText" colspan="9">
                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">                                                        
                                                                                </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td>
                                                                                    <table border="0" align="center" cellpadding="0" cellspacing="0">
                                                                                        <tr>                                                    
                                                                                            <td class="fieldLabel">StartDate:</td> <!--value="%{dateWithOutTime}" -->

                                                                                            <td><s:textfield name="startDate" id="personalRecordStartDate" cssClass="inputTextBlue" onchange="checkDates(this);"/> <a href="javascript:calper.popup();">
                                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                         width="20" height="20" border="0"></td>    

                                                                                                    <td class="fieldLabel">EndDate</td>
                                                                                                    <td><s:textfield name="endDate" id="personalRecordEndDate" cssClass="inputTextBlue" onchange="checkDates(this);"/><a href="javascript:calper1.popup();">
                                                                                                            <img  src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                                  width="20" height="20" border="0">  </td>
                                                                                                           
                                                                                                            </tr><tr>
                                                                                                             <td class="fieldLabel" align="right">Department :</td>
                                                                                                            <td>
                                                                                                                <s:select label="Select Department" 
                                                                                                                      name="departmentId"
                                                                                                                      id="departmentId3"
                                                                                                                      headerKey=""
                                                                                                                      headerValue="All"
                                                                                                                  list="{'Executive Board','Development','Marketing','Operations','Recruiting','Sales'}" cssClass="inputSelect" value="%{departmentId}"/> </td>

                                                                                                            
                                                                                                            <td class="fieldLabel">
                                                                                                                <s:checkbox name="isManagerInclude" id="isManagerinclude"></s:checkbox>
                                                                                                           </td> <td class="fieldLabelLeft">
                                                                                                                Include&nbsp;Mangers
                                                                                                            </td>

                                                                                                            <td colspan="4" align="right">
                                                                                                               

                                                                                                                <input type="button" value="Search" class="buttonBg" onclick="getEmployees();"/>
                                                                                                            </td> 
                                                                                                            </tr>

                                                                                                            </table>
                                                                                                    </td>
                                                                                        </tr>

                                                                                        <tr>
                                                                                            <td height="20px" align="center" colspan="9">
                                                                                                <div id="prloadingMessage" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                            </td>
                                                                                        </tr>

                                                                                        <tr>
                                                                                            <td>
                                                                                                <br>
                                                                                                <table align="center" cellpadding="2" border="0" cellspacing="1" width="50%" >

                                                                                                    <tr>
                                                                                                        <td >
                                                                                                            <br>
                                                                                                            <div id="topPerfReviewsList" style="display: block">
                                                                                                                <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                                                                <table id="tblPersonalRecord" align='center' cellpadding='1' cellspacing='1' border='0' class="gridTable" width='800'>
                                                                                                                    <COLGROUP ALIGN="left" >
                                                                                                                        <COL width="20%">
                                                                                                                        <COL width="15%">
                                                                                                                        <COL width="10%">
                                                                                                                        <COL width="7%">
                                                                                                                        <COL width="7%">
                                                                                                                        <COL width="7%">
                                                                                                                </table>
                                                                                                                <br>
                                                                                                                <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                                                                            </div>
                                                                                                        </td>
                                                                                                    </tr>
                                                                                                </table>    
                                                                                            </td>
                                                                                        </tr>

                                                                                    </table>
                                                                                </s:form>
                                                                            </td>
                                                                        </tr>
                                                                    </table>
                                                                    <script type="text/JavaScript">
                                                                        var calper = new CalendarTime(document.forms['personalRecord'].elements['startDate']);
                                                                        calper.year_scroll = true;
                                                                        calper.time_comp = false;
                                                                        var calper1 = new CalendarTime(document.forms['personalRecord'].elements['endDate']);
                                                                        calper1.year_scroll = true;
                                                                        calper1.time_comp = false;
                                                                        
                                                                    </script>
                                                                    </div>
                                                                </td>
                                                            </tr>
    <!--Past Review Data-->
                                                            <tr>
                                                <td class="homePortlet" valign="top">
                                                    <div class="portletTitleBar">
                                                        <div class="portletTitleLeft">Past Review Data</div>
                                                        <div class="portletIcons">
                                                            <a href="javascript:animatedcollapse.hide('pastReviewDiv')" title="Minimize">
                                                                <img src="../../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                            <a href="javascript:animatedcollapse.show('pastReviewDiv')" title="Maximize">
                                                                <img src="../../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                            </a>                                                           
                                                        </div>
                                                        <div class="clear"></div>
                                                    </div>
                                                    <div id="pastReviewDiv" style="background-color:#ffffff">
                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                            <tr>
                                                                <td width="40%" valign="top" align="center">
                                                                    <s:form action="personal" theme="simple" name="personalRecord">   

                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                                            <tr align="right">
                                                                                <td class="headerText" colspan="9">
                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">                                                        
                                                                                </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td>
                                                                                    <table border="0" align="center" cellpadding="0" cellspacing="0">

                                                                                        <tr>
                                                                                            <td class="fieldLabel" width="200px" align="right">Department :</td>
                                                                                            <td><s:select label="Select Department" 
                                                                                                      name="departmentId"
                                                                                                      id="departmentIdd"
                                                                                                      headerKey=""
                                                                                                      headerValue="All"
                                                                                                      list="departmentIdList" cssClass="inputSelect" value="%{departmentId}" onchange="getEmployeeByDept();"/></td>
                                                                                            <td class="fieldLabel">EmpName :</td>
                                                                                            <td ><s:select list="empnamesList" id="empnameById" name="empnameById" headerKey="-1" headerValue="All" cssClass="inputSelect" value="%{empnameById}"/></td> 
                                                                                            <td >
                                                                                                
                                                                                                <input type="button"  value="Search" Class="buttonBg" onClick="getPastReviewData();"/>
                                                                                                
                                                                                            </td>
                                                                                        </tr>
                                                                                      


                                                                                    </table>
                                                                                </td>
                                                                            </tr>

                                                                                        <tr>
                                                                                            <td height="20px" align="center" colspan="9">
                                                                                                <div id="pastloadingMessage" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                            </td>
                                                                                        </tr>

                                                                                        <tr>
                                                                                            <td>
                                                                                                <br>
                                                                                                <table align="center" cellpadding="2" border="0" cellspacing="1" width="50%" >

                                                                                                    <tr>
                                                                                                        <td >
                                                                                                            <br>
                                                                                                            <div id="topPerfReviewsList" style="display: block">
                                                                                                                <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                                                                <table id="tblPastReviewRecord" align='center' cellpadding='1' cellspacing='1' border='0' class="gridTable" width='800'>
                                                                                                                    <COLGROUP ALIGN="left" >
                                                                                                                       <COL width="5%">
                                                                                                                        <COL width="15%">
                                                                                                                        <COL width="20%">
                                                                                                                        <COL width="7%">
                                                                                                                        <COL width="7%">
                                                                                                                        <COL width="7%">
                                                                                                                        <COL width="7%">
                                                                                                                        <COL width="10%">
                                                                                                                        <COL width="10%">
                                                                                                                </table>
                                                                                                                <br>
                                                                                                                <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                                                                            </div>
                                                                                                        </td>
                                                                                                    </tr>
                                                                                                </table>    
                                                                                            </td>
                                                                                        </tr>

                                                                                    </table>
                                                                                </s:form>
                                                                            </td>
                                                                        </tr>
                                                                    </table>
                                                                    <script type="text/JavaScript">
                                                                        var calper = new CalendarTime(document.forms['personalRecord'].elements['startDate']);
                                                                        calper.year_scroll = true;
                                                                        calper.time_comp = false;
                                                                        var calper1 = new CalendarTime(document.forms['personalRecord'].elements['endDate']);
                                                                        calper1.year_scroll = true;
                                                                        calper1.time_comp = false;
                                                                        
                                                                    </script>
                                                                    </div>
                                                                </td>
                                                            </tr>

                                                          

                                                                                        </table>

                                                                                        <%--     </sx:div>
                                                                                </sx:tabbedpanel> --%>
                                                                                        <!--//END TABBED PANNEL : --> 
                                                                                    </div>
                                                                                    </div>
                                                                                    <script type="text/javascript">

                                                                                        var countries=new ddtabcontent("accountTabs")
                                                                                        countries.setpersist(false)
                                                                                        countries.setselectedClassTarget("link") //"link" or "linkparent"
                                                                                        countries.init()

                                                                                    </script>

                                                                                </td>
                                                                                <!--//END DATA COLUMN : Coloumn for Screen Content-->
                                                                            </tr>
                                                                        </table>
                                                                </td>
                                                            </tr>
                                                            <!--//END DATA RECORD : Record for LeftMenu and Body Content-->

                                                            <!--//START FOOTER : Record for Footer Background and Content-->
                                                            <tr class="footerBg">
                                                                <td align="center"><s:include value="/includes/template/Footer.jsp"/></td>
                                                            </tr>
                                                            <!--//END FOOTER : Record for Footer Background and Content-->
                                                        </table>
                                                        <!--//END MAIN TABLE : Table for template Structure-->
                                                        </body>
<script type="text/javascript">
		$(window).load(function(){
           hideSelect();
		});
		</script>
                                                        </html>


