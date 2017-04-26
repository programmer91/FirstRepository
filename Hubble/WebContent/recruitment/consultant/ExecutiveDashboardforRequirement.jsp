<%-- 
    Document   : ExecutiveDashboardforRequirement
    Created on : Sep 29, 2015, 2:41:06 PM
    Author     : miracle
--%>

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
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/animatedcollapse.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/jquery-1.2.2.pack.js"/>"></script>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AccountDetailsClientValidation.js"/>"></script>   
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/Activity.js"/>"></script>
        <%--  <script type="text/JavaScript" src="<s:url value="/includes/javascripts/oppdashboardAjaxUtil.js"/>"></script> --%>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/GreensheetListSearch.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/GreensheetListResultSearch.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EnableEnter.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ReusableContainer.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/employee/DateValidator.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmpStandardClientValidations.js"/>"></script>        
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/recruitment/ExecutiveDashboardforRequirement.js"/>"></script>
        <script type="text/javascript" src="https://www.google.com/jsapi"></script> 
        <s:include value="/includes/template/headerScript.html"/>
        <script language="JavaScript">
            google.load("visualization", "1", {packages:["corechart"]});
            animatedcollapse.addDiv('recReports', 'fade=1;speed=400;persist=1;group=app');
            animatedcollapse.addDiv('recStatusReports', 'fade=1;speed=400;persist=1;group=app');
            animatedcollapse.init();
            
          
        </script>
        <style>
            #parent {
                height: auto;

                width:780px;
                max-height: 400px;
                overflow: visible;
            }
           
        </style>
    </head>
    <body  class="bodyGeneral" onload="" oncontextmenu="return false;">
        <%!
            /*
             * Declarations
             */
            String queryString = null;
            Connection connection;
            Statement stmt;
            ResultSet rs;
            int userCounter = 0;
            int activityCounter = 0;
            int accountCounter = 0;
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
                        <li><a href="#" rel="accountsListTab" class="selected"> DashBoard Details </a></li>
                    </ul>

                    <div  style="border:1px solid gray; width:840px;height: 550px;overflow:auto; margin-bottom: 1em;">    
                        <div id="accountsListTab" class="tabcontent" >  

                            <!-- Over lay start -->

                            <div id="overlayRecruitment"></div>              <!-- Start Overlay -->

                            <div id="specialBoxRecruitment">

                                <div id="addedConultantDiv" style="display: none;" >
                                    <table align="center" border="0" cellspacing="0" style="width:100%;">
                                        <tr>                               
                                        <td colspan="2" style="background-color: #288AD1" >
                                            <h3 style="color:darkblue;" align="left">
                                                <span id="headerLabel"></span>


                                            </h3></td>
                                        <td colspan="2" style="background-color: #288AD1" align="right">

                                            <a href="#" onmousedown="closeConsultantList()" >
                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> 

                                            </a>  

                                        </td></tr>
                                        <tr>
                                        <td colspan="4">
                                            <div id="load" style="color: green;display: none;">Loading..</div>
                                            <div id="resultMessage"></div>
                                        </td>
                                        </tr>

                                        <tr>
                                        <td>
                                            <table id="tblAddedConsultant" align='center' cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750'>
                                                <%--   <script type="text/JavaScript" src="<s:url value="/includes/javascripts/wz_tooltip.js"/>"></script> --%>
                                                <COLGROUP ALIGN="left" >
                                                    <COL width="15%">
                                                        <COL width="15%">
                                                            <COL width="10%">
                                                                <COL width="5%">
                                                                    <COL width="15%">
                                                                        <COL width="15%">
                                                                            <COL width="10%">
                                                                                <COL width="5%">
                                                                                    <COL width="15%">
                                                                                        <COL width="15%">
                                                                                            <COL width="10%">
                                                                                                <COL width="5%">
                                                                                                    <COL width="5%">

                                                                                                        </table> 
                                                                                                        </td>
                                                                                                        </tr>

                                                                                                        </table>    

                                                                                                        </div>
                                                                                                        </div>

                                                                                                        <!-- Overlay end -->

                                                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                                                                            <tr>
                                                                                                            <td height="20px" >

                                                                                                            </td>
                                                                                                            </tr>                                            



                                                                                                            <!-- Reports Div start -->                        


                                                                                                            <tr>
                                                                                                            <td class="homePortlet" valign="top">
                                                                                                                <div class="portletTitleBar">
                                                                                                                    <div class="portletTitleLeft">Reports</div>
                                                                                                                    <div class="portletIcons">
                                                                                                                        <a href="javascript:animatedcollapse.hide('recReports')" title="Minimize">
                                                                                                                            <img src="../../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                                                                                        <a href="javascript:animatedcollapse.show('recReports')" title="Maximize">
                                                                                                                            <img src="../../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                                                                                        </a>                                                           
                                                                                                                    </div>
                                                                                                                    <div class="clear"></div>
                                                                                                                </div>
                                                                                                                <div id="recReports" style="background-color:#ffffff">
                                                                                                                    <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                                                                                        <tr>
                                                                                                                        <td width="40%" valign="top" align="center">
                                                                                                                            <s:form action="#" theme="simple" name="dashboardReport" >   

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
                                                                                                                                                <s:hidden name="departmentId" id="departmentId" value="Recruiting"/>
                                                                                                                                            <td class="fieldLabel">Practice:</td> <!--value="%{dateWithOutTime}" -->

                                                                                                                                            <td><s:select headerKey="" headerValue="All" list="{'SAP','J2EE','Integration','Portals','Commerce','B2B','QA','BI','Microsoft','Others'}" name="practiceId" id="practiceId" cssClass="inputSelect" /></td>    

                                                                                                                                            <td class="fieldLabel">Requirement&nbsp;Status:</td> <!--value="%{dateWithOutTime}" -->
                                                                                                                                            <td> <s:select headerKey="" headerValue="All" list="{'Forecast','open','InProgress','Hold','Withdrawn','won','lost'}" name="status" id="status" cssClass="inputSelect" value="%{status}"/></td>
                                                                                                                                            <td class="fieldLabel">SalesManager/TechnicalManager:</td>
                                                                                                                                            <td><s:select headerKey="" headerValue="All" list="techLeadList" name="techMgrId" id="techMgrId" cssClass="inputSelect" value="%{techMgrId}"/></td>

                                                                                                                                            </tr>                                                                                                          
                                                                                                                                            <tr>
                                                                                                                                            <td class="fieldLabel">Client:</td>
                                                                                                                                            <td><s:select headerKey="" headerValue="All" list="clientMap" name="clientId" id="clientId" cssClass="inputSelect" value="%{clientId}"/></td>

                                                                                                                                            <td class="fieldLabel">Team&nbsp;Lead:</td>
                                                                                                                                            <td>   <s:select headerKey="" headerValue="All" list="teamLeadMap" name="teamLeadLoginId" id="teamLeadLoginId" cssClass="inputSelect" value="%{teamLeadLoginId}" onchange="getTeamMap();"/></td>
                                                                                                                                            <td class="fieldLabel">Recruiter:</td>
                                                                                                                                            <td><s:select headerKey="" headerValue="All" list="recruiterMap" name="recruiterLoginId" id="recruiterLoginId" cssClass="inputSelect" value="%{recruiterLoginId}"/></td>
                                                                                                                                            </tr> 
                                                                                                                                            <tr>
                                                                                                                                            <td class="fieldLabel">PostedFromDate:<FONT color="red"  ><em>*</em></FONT></td> <!--value="%{dateWithOutTime}" -->


                                                                                                                                            <td><s:textfield name="startDateReport" id="startDateReport" cssClass="inputTextBlue" onchange="checkDates(this);"/><a href="javascript:calReport1.popup();"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                                                                                            </td>
                                                                                                                                            <td class="fieldLabel">EndDate:<FONT color="red"  ><em>*</em></FONT></td>

                                                                                                                                            <td><s:textfield name="endDateReport" id="endDateReport" cssClass="inputTextBlue" onchange="checkDates(this);"/><a href="javascript:calReport2.popup();"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                                                                                            </td>
                                                                                                                                            <td class="fieldLabel">Region:</td>
                                                                                                                                            <td><s:select headerKey="" headerValue="All" list="{'Central','East','Enterprise','West'}" name="region" id="region" cssClass="inputSelect" value="%{currentRequirement.region}" /></td>

                                                                                                                                            </tr>
                                                                                                                                            <tr>
                                                                                                                                            <td class="fieldLabel">ModifiedFromDate:</td> <!--value="%{dateWithOutTime}" -->


                                                                                                                                            <td><s:textfield name="modifiedStartDate" id="modifiedStartDate" cssClass="inputTextBlue" onchange="checkDates(this);"/><a href="javascript:calReport3.popup();"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                                                                                            </td>
                                                                                                                                            <td class="fieldLabel">EndDate:</td>

                                                                                                                                            <td><s:textfield name="modifiedEndDate" id="modifiedEndDate" cssClass="inputTextBlue" onchange="checkDates(this);"/><a href="javascript:calReport4.popup();"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                                                                                            </td>
                                                                                                                                            <td colspan="2" align="right"><br>
                                                                                                                                                <input type="button" value="Search" class="buttonBg" onclick="getSubmissionReport('1');"/>
                                                                                                                                            </td> 
                                                                                                                                            </tr>

                                                                                                                                            <%--   <tr>  
                                                                                                                                                     <td class="fieldLabel">Activity Type : </td>
                                                                                                                                                     <td><s:select name="activityTypeGraph" id="activityTypeGraph" value="%{currentConsultant.activityType}"  list="{'Email','Call-Inbound','Call-Outbound'}" cssClass="inputSelect" headerKey="-1" headerValue="All"/></td>
                                                                                                                                                     <td class="fieldLabel">Recruiter Name :</td>
                                                                                                                                                     <td>
                                                                                                                                                         <s:select headerKey="-1" headerValue="--Please Select--" name="createdByGraph" id="createdByGraph" list="assignedMembers" cssClass="inputSelect" />
                                                                                                                                                     </td>
                                                                                                                                                      <td colspan="4" align="center">
                                                                                                                                                         <input type="button" value="Search" class="buttonBg" onclick="getRecActivitiesAsGraph();"/>
                                                                                                                                                      </td> 
                                                                                                                                                </tr> --%>

                                                                                                                                        </table>
                                                                                                                                    </td>
                                                                                                                                    </tr>

                                                                                                                                    <tr>
                                                                                                                                    <td align="center" colspan="6">
                                                                                                                                        <div id="loadActMessageSubmitReport" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                                                                    </td>
                                                                                                                                    </tr>
                                                                                                                                    <tr>
                                                                                                                                    <td height="20px" >
                                                                                                                                        <s:hidden name="pgNo" id="pgNo" cssClass="inputTextBlue" theme="simple"/>
                                                                                                                                        <s:hidden name="totalRecords" id="totalRecords" cssClass="inputTextBlue" theme="simple"/>
                                                                                                                                    </td>
                                                                                                                                    </tr>

                                                                                                                                    <tr>

                                                                                                                                    <td colspan="2" >
                                                                                                                                        <div id="submitReportDiv" style="display: none;">

                                                                                                                                            <div id="parent">

                                                                                                                                                <div class="fieldLabelLeft">Total Positions : <span id="totalPositions"></span></div>

                                                                                                                                                <table id="tblSubmitReport" class="dashBoardGridTable" align="center">
                                                                                                                                                    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/wz_tooltip.js"/>"></script>
                                                                                                                                                    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/jquery-2.1.3.js"/>"></script>
                                                                                                                                                    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tableHeadFixer.js"/>"></script>    
                                                                                                                                                </table>
                                                                                                                                            </div>
                                                                                                                                        </div>
                                                                                                                                        <div id="button_pageNation"></div>
                                                                                                                                    </td>
                                                                                                                                    </tr>



                                                                                                                                    <%--<tr>
                                                                                                                                        <td>

                                                                                    <table align="center" cellpadding="2" border="0" cellspacing="1" width="50%" >
                                                                                      

                                                                                        <tr>
                                                                                            <td colspan="2">
                                                                                                <br>
                                                                                                <div id="submitReportDiv" style="display: none;">
                                                                                                    <div class="fieldLabelLeft">Total Positions : <span id="totalPositions"></span></div>
                                                                                                    <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->

                                                                                                    <table id="tblSubmitReport" align='center' cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750'>
                                                                                                        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/wz_tooltip.js"/>"></script>
                                                                                                        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/wz_tooltipNew.js"/>"></script>
                                                                                                        <COLGROUP ALIGN="left" >
                                                                                                            <COL width="15%">
                                                                                                            <COL width="15%">
                                                                                                            <COL width="10%">
                                                                                                            <COL width="5%">



                                                                                                    </table>
                                                                                                    <br>
                                                                                                    <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                                                                </div>
                                                                                            </td>
                                                                                        </tr>

                                                                                    </table>
                                                                                </td></tr> --%>
                                                                                                                                </table>
                                                                                                                            </s:form>
                                                                                                                        </td>
                                                                                                                        </tr>
                                                                                                                    </table>

                                                                                                                    <script type="text/JavaScript">
                                                                                                                        var calReport1 = new CalendarTime(document.forms['dashboardReport'].elements['startDateReport']);
                                                                                                                        calReport1.year_scroll = true;
                                                                                                                        calReport1.time_comp = false;
                                                                                                                        var calReport2 = new CalendarTime(document.forms['dashboardReport'].elements['endDateReport']);
                                                                                                                        calReport2.year_scroll = true;
                                                                                                                        calReport2.time_comp = false;
                                                                                                                        var calReport3 = new CalendarTime(document.forms['dashboardReport'].elements['modifiedStartDate']);
                                                                                                                        calReport3.year_scroll = true;
                                                                                                                        calReport3.time_comp = false;
                                                                                                                        var calReport4 = new CalendarTime(document.forms['dashboardReport'].elements['modifiedEndDate']);
                                                                                                                        calReport4.year_scroll = true;
                                                                                                                        calReport4.time_comp = false;
                                                                        
                                                                                                                    </script>
                                                                                                                </div>
                                                                                                            </td>
                                                                                                            </tr>



                                                                                                            <!-- Reports Div End -->
                                                                                                            <!-- Requirement status report -->

                                                                                                            <tr>
                                                                                                            <td class="homePortlet" valign="top">
                                                                                                                <div class="portletTitleBar">
                                                                                                                    <div class="portletTitleLeft">Requirement Status Report</div>
                                                                                                                    <div class="portletIcons">
                                                                                                                        <a href="javascript:animatedcollapse.hide('recStatusReports')" title="Minimize">
                                                                                                                            <img src="../../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                                                                                        <a href="javascript:animatedcollapse.show('recStatusReports')" title="Maximize">
                                                                                                                            <img src="../../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                                                                                        </a>                                                           
                                                                                                                    </div>
                                                                                                                    <div class="clear"></div>
                                                                                                                </div>
                                                                                                                <div id="recStatusReports" style="background-color:#ffffff">
                                                                                                                    <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                                                                                        <tr>
                                                                                                                        <td width="40%" valign="top" align="center">
                                                                                                                            <s:form action="#" theme="simple" name="dashboardStatusReport" >   

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
                                                                                                                                                <s:hidden name="departmentId1" id="departmentId1" value="Recruiting"/>
                                                                                                                                            <td class="fieldLabel">Practice:</td> <!--value="%{dateWithOutTime}" -->

                                                                                                                                            <td><s:select headerKey="" headerValue="All" list="{'SAP','J2EE','Integration','Portals','Commerce','B2B','QA','BI','Microsoft','Others'}" name="practiceId1" id="practiceId1" cssClass="inputSelect" /></td>    

                                                                                                                                            <td class="fieldLabel">Requirement&nbsp;Status:</td> <!--value="%{dateWithOutTime}" -->
                                                                                                                                            <td> <s:select headerKey="" headerValue="All" list="{'Forecast','open','InProgress','Hold','Withdrawn','won','lost'}" name="status1" id="status1" cssClass="inputSelect" value="%{status}"/></td>
                                                                                                                                            <td class="fieldLabel">SalesManager/TechnicalManager:</td>
                                                                                                                                            <td><s:select headerKey="" headerValue="All" list="techLeadList" name="techMgrId1" id="techMgrId1" cssClass="inputSelect" value="%{techMgrId}"/></td>

                                                                                                                                            </tr>                                                                                                          
                                                                                                                                            <tr>
                                                                                                                                            <td class="fieldLabel">Client:</td>
                                                                                                                                            <td><s:select headerKey="" headerValue="All" list="clientMap" name="clientId1" id="clientId1" cssClass="inputSelect" value="%{clientId}"/></td>

                                                                                                                                            <td class="fieldLabel">Team&nbsp;Lead:</td>
                                                                                                                                            <td>   <s:select headerKey="" headerValue="All" list="teamLeadMap" name="teamLeadLoginId1" id="teamLeadLoginId1" cssClass="inputSelect" value="%{teamLeadLoginId}" onchange="getTeamMapInStatus();"/></td>
                                                                                                                                            <td class="fieldLabel">Recruiter:</td>
                                                                                                                                            <td><s:select headerKey="" headerValue="All" list="recruiterMap" name="recruiterLoginId1" id="recruiterLoginId1" cssClass="inputSelect" value="%{recruiterLoginId}"/></td>
                                                                                                                                            </tr> 
                                                                                                                                            <tr>
                                                                                                                                            <td class="fieldLabel">PostedFromDate:<FONT color="red"  ><em>*</em></FONT></td> <!--value="%{dateWithOutTime}" -->


                                                                                                                                            <td><s:textfield name="startDateReport1" id="startDateReport1" cssClass="inputTextBlue" onchange="checkDates(this);"/><a href="javascript:calReport5.popup();"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                                                                                            </td>
                                                                                                                                            <td class="fieldLabel">EndDate:<FONT color="red"  ><em>*</em></FONT></td>

                                                                                                                                            <td><s:textfield name="endDateReport1" id="endDateReport1" cssClass="inputTextBlue" onchange="checkDates(this);"/><a href="javascript:calReport6.popup();"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                                                                                            </td>
                                                                                                                                            <td class="fieldLabel">Region:</td>
                                                                                                                                            <td><s:select headerKey="" headerValue="All" list="{'Central','East','Enterprise','West'}" name="region1" id="region1" cssClass="inputSelect" value="%{currentRequirement.region}" /></td>

                                                                                                                                            </tr>
                                                                                                                                            <tr>
                                                                                                                                            <td class="fieldLabel">ModifiedFromDate:</td> <!--value="%{dateWithOutTime}" -->


                                                                                                                                            <td><s:textfield name="modifiedStartDate1" id="modifiedStartDate1" cssClass="inputTextBlue" onchange="checkDates(this);"/><a href="javascript:calReport7.popup();"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                                                                                            </td>
                                                                                                                                            <td class="fieldLabel">EndDate:</td>

                                                                                                                                            <td><s:textfield name="modifiedEndDate1" id="modifiedEndDate1" cssClass="inputTextBlue" onchange="checkDates(this);"/><a href="javascript:calReport8.popup();"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                                                                                            </td>
                                                                                                                                            <td colspan="2" align="right"><br>
                                                                                                                                                <input type="button" value="Search" class="buttonBg" onclick="getStatusReport();"/>
                                                                                                                                            </td> 
                                                                                                                                            </tr>

                                                                                                                                            <%--   <tr>  
                                                                                                                                                     <td class="fieldLabel">Activity Type : </td>
                                                                                                                                                     <td><s:select name="activityTypeGraph" id="activityTypeGraph" value="%{currentConsultant.activityType}"  list="{'Email','Call-Inbound','Call-Outbound'}" cssClass="inputSelect" headerKey="-1" headerValue="All"/></td>
                                                                                                                                                     <td class="fieldLabel">Recruiter Name :</td>
                                                                                                                                                     <td>
                                                                                                                                                         <s:select headerKey="-1" headerValue="--Please Select--" name="createdByGraph" id="createdByGraph" list="assignedMembers" cssClass="inputSelect" />
                                                                                                                                                     </td>
                                                                                                                                                      <td colspan="4" align="center">
                                                                                                                                                         <input type="button" value="Search" class="buttonBg" onclick="getRecActivitiesAsGraph();"/>
                                                                                                                                                      </td> 
                                                                                                                                                </tr> --%>

                                                                                                                                        </table>
                                                                                                                                    </td>
                                                                                                                                    </tr>

                                                                                                                                    <tr>
                                                                                                                                    <td align="center" colspan="6">
                                                                                                                                        <div id="loadActMessageStatusReport" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                                                                    </td>
                                                                                                                                    </tr>




                                                                                                                                    <tr>
                                                                                                                                    <td>

                                                                                                                                        <table align="center" cellpadding="2" border="0" cellspacing="1" width="50%" >
                                                                                                                                            <%--      <tr>
                                                                                                                                                          <td class="fieldLabelLeft" width="200px">
                                                                                                                                                              Total Requirements in System :
                                                                                                                                                          </td>
                                                                                                                                                          <td class="fieldLabelLeft" id="totalRequirementsFound">

                                                                                                                        </td>
                                                                                                                    </tr> --%>

                                                                                                                                            <tr>
                                                                                                                                            <td colspan="2">
                                                                                                                                                <br>
                                                                                                                                                <div id="submitStatusDiv" style="display: none;">
                                                                                                                                                    <div class="fieldLabelLeft">Total Requirements : <span id="totalRequirememts"></span></div>
                                                                                                                                                    <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->

                                                                                                                                                    <table id="tblstatusReport" align='center' cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750'>
                                                                                                                                                        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/wz_tooltip.js"/>"></script>
                                                                                                                                                        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/wz_tooltipNew.js"/>"></script>
                                                                                                                                                        <COLGROUP ALIGN="left" >
                                                                                                                                                            <COL width="15%">
                                                                                                                                                                <COL width="15%">
                                                                                                                                                                    <COL width="10%">
                                                                                                                                                                        <COL width="5%">



                                                                                                                                                                            </table>
                                                                                                                                                                            <br>
                                                                                                                                                                            <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                                                                                                                                            </div>
                                                                                                                                                                            </td>
                                                                                                                                                                            </tr>

                                                                                                                                                                            </table>
                                                                                                                                                                            </td></tr>
                                                                                                                                                                            </table>
                                                                                                                                                                        </s:form>
                                                                                                                                                                        </td>
                                                                                                                                                                        </tr>
                                                                                                                                                                        </table>

                                                                                                                                                                        <script type="text/JavaScript">
                                                                                                                                                                            var calReport5 = new CalendarTime(document.forms['dashboardStatusReport'].elements['startDateReport1']);
                                                                                                                                                                            calReport5.year_scroll = true;
                                                                                                                                                                            calReport5.time_comp = false;
                                                                                                                                                                            var calReport6 = new CalendarTime(document.forms['dashboardStatusReport'].elements['endDateReport1']);
                                                                                                                                                                            calReport6.year_scroll = true;
                                                                                                                                                                            calReport6.time_comp = false;
                                                                                                                                                                            var calReport7 = new CalendarTime(document.forms['dashboardStatusReport'].elements['modifiedStartDate1']);
                                                                                                                                                                            calReport7.year_scroll = true;
                                                                                                                                                                            calReport7.time_comp = false;
                                                                                                                                                                            var calReport8 = new CalendarTime(document.forms['dashboardStatusReport'].elements['modifiedEndDate1']);
                                                                                                                                                                            calReport8.year_scroll = true;
                                                                                                                                                                            calReport8.time_comp = false;
                                                                        
                                                                                                                                                                        </script>
                                                                                                                                                                        </div>
                                                                                                                                                                        </td>
                                                                                                                                                                        </tr>
                                                                                                                                                                        <!-- Requirement status report end -->

                                                                                                                                                                        <!-- new div for account list by priority end -->

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
                                                                                                                                                                        <script type="text/javascript">
                                                                                                                                                                                                       
                                                                                                                                                                                                          
                                                                                                                                                                                                        
                                                                                                                                                                            function pagerOption(){

                                                             
                                                                                                                                                                                var  recordPage=20;
                                                                                                                                                                                $('#tblSubmitReport').tablePaginate({navigateType:'navigator'},recordPage,tblSubmitReport);

                                                                                                                                                                                                                 
      

                                                                                                                                                                            }
                                                                                                                                                                                                                                                       </script>
                                                                                                                                                                        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DynamicPagination.js"/>"></script>

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

                                                                                                                                                                        </html>
