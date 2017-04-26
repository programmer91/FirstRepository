<%-- 
    Document   : MyOpportunitiesDashBoard
    Created on : Dec 3, 2015, 10:33:55 PM
    Author     : miracle
--%>

<%@page import="java.util.Map"%>
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
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css?version=1.0"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/animatedcollapse.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/jquery-1.2.2.pack.js"/>"></script>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EnableEnter.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/PreSalesDashBoard.js"/>"></script>

 <script type="text/javascript" src="https://www.google.com/jsapi"></script> 
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/employee/DateValidator.js"/>"></script>
        <s:include value="/includes/template/headerScript.html"/>
        <script language="JavaScript">
             
            google.load("visualization", "1", {packages:["corechart"]});
            animatedcollapse.addDiv('presalesopportDiv', 'fade=1;persist=1;group=app');
            animatedcollapse.addDiv('presalesprojectsDiv', 'fade=1;persist=1;group=app');
            animatedcollapse.addDiv('presalesOnsiteOffshoreDiv', 'fade=1;persist=1;group=app');
         //   animatedcollapse.addDiv('requirementListDiv', 'fade=1;persist=1;group=app');
          animatedcollapse.addDiv('requirementListDivType', 'fade=1;persist=1;group=app');
            animatedcollapse.init();
           
        </script>
        <style>

            div#overlayProjects {
                display: none;
                z-index: 2;
                background: #000;
                position: fixed;
                width: 100%;
                height: 100%;
                top: 0px;
                left: 0px;
                overflow: auto;
                text-align: center;
            }
            div#specialBoxProject {
                display: none;

                position: absolute;
                z-index: 3;
                margin: 100px auto 0px auto;
                width: 800px; 
                height: auto;
                background: #FFF;
                overflow: auto;
                overflow-y: auto;
                color: #000;
            }

            #parent {
                height: auto;

                width:780px;
                max-height: 400px;
                overflow: visible;
            }

            #tblPresalesRequirementList {

                width: 750px !important;
            }
            #parent1 {
                height: auto;

                width:780px;
                max-height: 400px;
                overflow: visible;
            }

            #tblRequirementOverviewDetails {

                width: 750px !important;
            }
        </style>

    </head>
    <!-- <body  class="bodyGeneral"  oncontextmenu="return false;" onload="defaultDates();"> -->
    <body  class="bodyGeneral"  oncontextmenu="return false;">

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
                        <li><a href="#" rel="accountsListTab" class="selected">&nbsp;DashBoards</a></li>

                    </ul>
                    <div  style="border:1px solid gray; width:840px;height: 550px;overflow:auto; margin-bottom: 1em;">    
                        <div id="accountsListTab" class="tabcontent" >  
                            <!-- Overlay start -->
                            <div id="overlayProjects"></div>              <!-- Start Overlay -->

                            <div id="specialBoxProject" style="align: center" >

                                <div id="addedProjects" style="display: none;align: center">
                                    <table align="center" border="0" cellspacing="0" style="width:100%;">
                                        <tr>                               
                                        <td colspan="1" style="background-color: #288AD1" >
                                            <h3 style="color:darkblue;" align="left">
                                                <span id="headerLabel"></span>


                                            </h3></td>
                                        <td colspan="2" style="background-color: #288AD1" align="right">

                                            <a href="#" onmousedown="closeProjectMembers()" >
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
                                            <table id="tblProjectMembers" align='center' cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750'>
                                                <%--   <script type="text/JavaScript" src="<s:url value="/includes/javascripts/wz_tooltip.js"/>"></script> --%>
                                                <COLGROUP ALIGN="left" >
                                                    <COL width="20%">
                                                        <COL width="20%">
                                                            <COL width="20%">
                                                                <COL width="20%">
                                                                    <COL width="20%">
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


                                                                            <!-- PreSAles Opportunities Summary start -->
                                                                            <tr>
                                                                            <td class="homePortlet" valign="top">
                                                                                <div class="portletTitleBar">
                                                                                    <div class="portletTitleLeft"> Opportunities</div>
                                                                                    <div class="portletIcons">
                                                                                        <a href="javascript:animatedcollapse.hide('presalesopportDiv')" title="Minimize">
                                                                                            <img src="../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                                                        <a href="javascript:animatedcollapse.show('presalesopportDiv')" title="Maximize">
                                                                                            <img src="../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                                                        </a>
                                                                                    </div>
                                                                                    <div class="clear"></div>
                                                                                </div>
                                                                                <div id="presalesopportDiv" style="background-color:#ffffff">


                                                                                    <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                                                        <tr>
                                                                                        <td width="80%" valign="top" align="center">
                                                                                            <s:form action="" theme="simple" id="oppDashboard" name="oppDashboard" onsubmit="return compareDates(document.getElementById('dueStartDate').value,document.getElementById('dueEndDate').value);">   

                                                                                                <table cellpadding="0" cellspacing="0" border="0" width="100%">

                                                                                                    <tr>
                                                                                                    <td>
                                                                                                        <table border="0" align="center" cellpadding="0" cellspacing="0" >
                                                                                                            <tr><td colspan="4"><br></td></tr>
                                                                                                            <tr>
                                                                                                            <td class="fieldLabel">Opportunity Type:</td>
                                                                                                            <td>
                                                                                                                <%-- <s:select name="type" id="type" cssClass="inputSelect" list="{'All','Project','Software','Staffing'}" value="All"/> --%>
                                                                                                                <s:select name="type" id="type" cssClass="inputSelect" list="typeList" headerKey="All" headerValue="All"/>
                                                                                                            </td>
                                                                                                            <td  class="fieldLabel">State:&nbsp;</td>
                                                                                                            <td> 
                                                                                                                <s:select name="state" id="state" cssClass="inputSelect" list="opportunityStateList" headerKey="" headerValue="All"/>
                                                                                                            </td>


                                                                                                            </tr>

                                                                                                            <tr>

                                                                                                            <td class="fieldLabel">Start Date:</td> <!--value="%{dateWithOutTime}" -->
                                                                                                            <td><s:textfield id="dueStartDate" name="dueStartDate" cssClass="inputTextBlue" value="" onchange="checkDates(this);"/> <a href="javascript:cal1.popup();">
                                                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                                         width="20" height="20" border="0"></td>    
                                                                                                                    <td class="fieldLabel">End Date</td>
                                                                                                                    <td><s:textfield name="dueEndDate" id="dueEndDate" cssClass="inputTextBlue" value="%{dateWithOutTime}" onchange="checkDates(this);"/><a href="javascript:cal2.popup();">
                                                                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                                                 width="20" height="20" border="0">  </td>
                                                                                                                            </tr>

                                                                                                                            <tr>
                                                                                                                            <td class="fieldLabel" width="200px" align="right">Team&nbsp;Members: </td>                           
                                                                                                                            <td>

                                                                                                                                <s:select name="teamMemberId1" id="teamMemberId1" headerKey="-1" headerValue="--please select--" list="myTeamMembersList" cssClass="inputSelect" value="%{teamMemberId1}"  />    

                                                                                                                            </td>
                                                                                                                            <td class="fieldLabel" style="width: 10px;">Practice&nbsp;:</td>
                                                                                                                            <td style="width: 156px;">
                                                                                                                                <s:select list="practiceIdOppList" name="practice" id="practice" cssClass="inputSelect" value="All" headerKey="All" headerValue="All" />
                                                                                                                            </td>
                                                                                                                            </tr>

                                                                                                                            <tr>

                                                                                                                            <td></td><td></td><td></td>
                                                                                                                            <td>
                                                                                                                                <input type="button" id="opportunitySearchBUtton" class="buttonBg" onclick="getpreSalesOpportunities()" value="Search"/>

                                                                                                                            </td>
                                                                                                                            </tr>

                                                                                                                            <tr>
                                                                                                                            <td height="20px" align="center" colspan="4" >
                                                                                                                                <div id="loadOppMessage" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                                                            </td>
                                                                                                                            </tr>
                                                                                                                            <tr>
                                                                                                                            <td class="fieldLabel" >
                                                                                                                                Total Records Found :
                                                                                                                            </td>
                                                                                                                            <td class="fieldLabelLeft" id="totalOppRec" >

                                                                                                                            </td>
                                                                                                                            <td class="fieldLabel"  >
                                                                                                                                Total Value($): 
                                                                                                                            </td>
                                                                                                                            <td class="fieldLabelLeft" id="totalOppSum" >

                                                                                                                            </td>
                                                                                                                            </tr>


                                                                                                                            <tr>
                                                                                                                            <td height="20px" >

                                                                                                                            </td>
                                                                                                                            </tr>
                                                                                                                            <tr>

                                                                                                                            <td colspan="4" >
                                                                                                                            <lable id="noteLableForOpportunity" style="display:none;color:red;font-size: 80%; font-style: italic;">Note :The details are order by  Due date ascending.</lable>
                                                                                                                            <table id="tblOppUpdate" align="center"  
                                                                                                                                   cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750'>
                                                                                                                                <COLGROUP ALIGN="left" >
                                                                                                                                    <COL width="5%">
                                                                                                                                        <COL width="20%">
                                                                                                                                            <COL width="25%">
                                                                                                                                                <COL width="7%">
                                                                                                                                                    <COL width="7%">
                                                                                                                                                        <COL width="7%">
                                                                                                                                                            <COL width="7%">
                                                                                                                                                                <COL width="7%">
                                                                                                                                                                    </table>
                                                                                                                                                                    </td>
                                                                                                                                                                    </tr>
                                                                                                                                                                    </table>
                                                                                                                                                                    </td>
                                                                                                                                                                    </tr>


                                                                                                                                                                    </table>
                                                                                                                                                                </s:form>
                                                                                                                                                                <script type="text/JavaScript">
                                                                                                                                                                    var cal1 = new CalendarTime(document.forms['oppDashboard'].elements['dueStartDate']);
                                                                                                                                                                    cal1.year_scroll = true;
                                                                                                                                                                    cal1.time_comp = false;
                                                                                                                                                                    cal2 = new CalendarTime(document.forms['oppDashboard'].elements['dueEndDate']);
                                                                                                                                                                    cal2.year_scroll = true;
                                                                                                                                                                    cal2.time_comp = false;
                                                                                                                                                                </script>
                                                                                                                                                                </td>
                                                                                                                                                                </tr>
                                                                                                                                                                <!--2nd td in First table -->
                                                                                                                                                                </table>
                                                                                                                                                                </div>
                                                                                                                                                                </td>
                                                                                                                                                                </tr>

                                                                                                                                                                <!-- PreSAles Opportunities Summary End -->

                                                                                                                                                                <!-- PreSAles Projects start start -->

                                                                                                                                                                <tr>
                                                                                                                                                                <td class="homePortlet" valign="top">
                                                                                                                                                                    <div class="portletTitleBar">
                                                                                                                                                                        <div class="portletTitleLeft">Projects</div>
                                                                                                                                                                        <div class="portletIcons">
                                                                                                                                                                            <a href="javascript:animatedcollapse.hide('presalesprojectsDiv')" title="Minimize">
                                                                                                                                                                                <img src="../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                                                                                                                                            <a href="javascript:animatedcollapse.show('presalesprojectsDiv')" title="Maximize">
                                                                                                                                                                                <img src="../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                                                                                                                                            </a>
                                                                                                                                                                        </div>
                                                                                                                                                                        <div class="clear"></div>
                                                                                                                                                                    </div>
                                                                                                                                                                    <div id="presalesprojectsDiv" style="background-color:#ffffff">


                                                                                                                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                                                                                                                                            <tr>
                                                                                                                                                                            <td width="80%" valign="top" align="center">
                                                                                                                                                                                <s:form action="" theme="simple" id="projectDashboard" name="projectDashboard" onsubmit="return compareDates(document.getElementById('startDate').value,document.getElementById('endDate').value);">   

                                                                                                                                                                                    <table cellpadding="0" cellspacing="0" border="0" width="100%">

                                                                                                                                                                                        <tr>
                                                                                                                                                                                        <td>
                                                                                                                                                                                            <table border="0" align="center" cellpadding="0" cellspacing="0" >
                                                                                                                                                                                                <tr><td colspan="4"><br></td></tr>

                                                                                                                                                                                                <tr>   

                                                                                                                                                                                                <td class="fieldLabel">Start Date:</td> <!--value="%{dateWithOutTime}" -->
                                                                                                                                                                                                <td><s:textfield id="startDate" name="startDate" cssClass="inputTextBlue" value="" onchange="checkDates(this);"/> <a href="javascript:cal3.popup();">
                                                                                                                                                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                                                                                                                        width="20" height="20" border="0"></td>    
                                                                                                                                                                                                        <td class="fieldLabel">End Date</td>
                                                                                                                                                                                                        <td><s:textfield name="endDate" id="endDate" cssClass="inputTextBlue" value="%{dateWithOutTime}" onchange="checkDates(this);"/><a href="javascript:cal4.popup();">
                                                                                                                                                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                                                                                                                        width="20" height="20" border="0">  </td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td class="fieldLabel" width="200px" align="right">Team&nbsp;Members: </td>                           
                                                                                                                                                                                                        <td>

                                                                                                                                                                                                        <s:select name="teamMemberId2" id="teamMemberId2" headerKey="-1" headerValue="--please select--" list="myTeamMembersList" cssClass="inputSelect" value="%{teamMemberId2}"  />    

                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        <td class="fieldLabel" width="200px" align="right">State: </td>     
                                                                                                                                                                                                        <td>
                                                                                                                                                                                                        <s:select  list="{'Active','Completed','Terminated','Initiated'}" name="status" id="status" cssClass="inputSelect"  value="%{state}"/>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr><tr>
                                                                                                                                                                                                        <td colspan="3"></td><td>
                                                                                                                                                                                                        <input type="button" id="ProjectSearchBUtton" class="buttonBg" onclick="getProjects()" value="Search"/>

                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>

                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td height="20px" align="center" colspan="4" >
                                                                                                                                                                                                        <div id="loadProjectMessage" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td height="20px" >

                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        <tr>

                                                                                                                                                                                                        <td colspan="4" >
                                                                                                                                                                                                        <lable id="noteLableForProject" style="display:none;color:red;font-size: 80%; font-style: italic;">Note :The details are order by  Due date ascending.</lable>
                                                                                                                                                                                                        <table id="tblProjects" align="center"  
                                                                                                                                                                                                        cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750'>
                                                                                                                                                                                                        <COLGROUP ALIGN="left" >
                                                                                                                                                                                                        <COL width="5%">
                                                                                                                                                                                                        <COL width="25%">
                                                                                                                                                                                                        <COL width="15%">
                                                                                                                                                                                                        <COL width="10%">
                                                                                                                                                                                                        <COL width="10%">
                                                                                                                                                                                                        <COL width="15%">
                                                                                                                                                                                                        <COL width="15%">    
                                                                                                                                                                                                        </table>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        </table>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>


                                                                                                                                                                                                        </table>
                                                                                                                                                                                                        </s:form>
                                                                                                                                                                                                        <script type="text/JavaScript">
                                                                                                                                                                                                            var cal3 = new CalendarTime(document.forms['projectDashboard'].elements['startDate']);
                                                                                                                                                                                                            cal3.year_scroll = true;
                                                                                                                                                                                                            cal3.time_comp = false;
                                                                                                                                                                                                            cal4 = new CalendarTime(document.forms['projectDashboard'].elements['endDate']);
                                                                                                                                                                                                            cal4.year_scroll = true;
                                                                                                                                                                                                            cal4.time_comp = false;
                                                                                                                                                                                                        </script>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        <!--2nd td in First table -->
                                                                                                                                                                                                        </table>
                                                                                                                                                                                                        </div>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>

                                                                                                                                                                                                        <!-- PreSAles Projects start end -->
                                                                                                                                                                                                        <!-- Dashboard for dispalying the persons reporting to presales person off shore-->


                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td class="homePortlet" valign="top">
                                                                                                                                                                                                        <div class="portletTitleBar">
                                                                                                                                                                                                        <div class="portletTitleLeft"> Offshore/Onsite Persons</div>
                                                                                                                                                                                                        <div class="portletIcons">
                                                                                                                                                                                                        <a href="javascript:animatedcollapse.hide('presalesOnsiteOffshoreDiv')" title="Minimize">
                                                                                                                                                                                                        <img src="../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                                                                                                                                                                        <a href="javascript:animatedcollapse.show('presalesOnsiteOffshoreDiv')" title="Maximize">
                                                                                                                                                                                                        <img src="../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                                                                                                                                                                        </a>
                                                                                                                                                                                                        </div>
                                                                                                                                                                                                        <div class="clear"></div>
                                                                                                                                                                                                        </div>
                                                                                                                                                                                                        <div id="presalesOnsiteOffshoreDiv" style="background-color:#ffffff">


                                                                                                                                                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td width="80%" valign="top" align="center">
                                                                                                                                                                                                        <s:form action="" theme="simple" id="PresalesOffshore" name="PresalesOffshore" >   

                                                                                                                                                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">

                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td>
                                                                                                                                                                                                        <table border="0" align="center" cellpadding="0" cellspacing="0" >
                                                                                                                                                                                                        <tr><td><br></td></tr>
                                                                                                                                                                                                        <tr>

                                                                                                                                                                                                        <td class="fieldLabel" width="200px" align="right">Team&nbsp;Leads: </td>                           
                                                                                                                                                                                                        <td>

                                                                                                                                                                                                        <s:select name="teamMemberId" id="teamMemberId" headerKey="-1" headerValue="All" list="myTeamMembers" cssClass="inputSelect" value="%{teamMemberId}"  />    

                                                                                                                                                                                                        </td>

                                                                                                                                                                                                        <td class="fieldLabel">Country:</td>
                                                                                                                                                                                                        <td>
                                                                                                                                                                                                        <s:select name="country" id="country" cssClass="inputSelect" list="{'India','USA'}" headerKey="-1" headerValue="All"/>
                                                                                                                                                                                                        </td>                                                                                 
                                                                                                                                                                                                        <td> 
                                                                                                                                                                                                        <input type="button" id="presalesOffshoreOnshore" class="buttonBg" onclick="getPresalesOffshoreOnshoreList()" value="Search"/>

                                                                                                                                                                                                        </td>

                                                                                                                                                                                                        </tr>



                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td height="20px" align="center" colspan="4" >
                                                                                                                                                                                                        <div id="loadEmpMessage" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td height="20px" >

                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        <tr>

                                                                                                                                                                                                        <td colspan="4" >

                                                                                                                                                                                                        <table id="tblOnsiteOffshorePeople" align="center"  
                                                                                                                                                                                                        cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750'>
                                                                                                                                                                                                        <COLGROUP ALIGN="left" >
                                                                                                                                                                                                        <COL width="5%">
                                                                                                                                                                                                        <COL width="15%">
                                                                                                                                                                                                        <COL width="25%">
                                                                                                                                                                                                        <COL width="10%">
                                                                                                                                                                                                        <COL width="15%">


                                                                                                                                                                                                        </table>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>

                                                                                                                                                                                                        </table>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>


                                                                                                                                                                                                        </table>
                                                                                                                                                                                                        </s:form>
                                                                                                                                                                                                        <script type="text/JavaScript">
                                                                                                                                                                                                            var cal1 = new CalendarTime(document.forms['oppDashboard'].elements['dueStartDate']);
                                                                                                                                                                                                            cal1.year_scroll = true;
                                                                                                                                                                                                            cal1.time_comp = false;
                                                                                                                                                                                                            cal2 = new CalendarTime(document.forms['oppDashboard'].elements['dueEndDate']);
                                                                                                                                                                                                            cal2.year_scroll = true;
                                                                                                                                                                                                            cal2.time_comp = false;
                                                                                                                                                                                                        </script>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        <!--2nd td in First table -->
                                                                                                                                                                                                        </table>
                                                                                                                                                                                                        </div>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>




                                                                                                                                                                                                        <!-- presales offshore dshboard ended here -->



                                                                                                                                                                                                        <!-- Requirement Div Type Start -->
                                                                                                                                                                                                         <tr>
                                                                                                                                                                                                        <td class="homePortlet" valign="top">
                                                                                                                                                                                                        <div class="portletTitleBar">
                                                                                                                                                                                                        <div class="portletTitleLeft"> Requirement List</div>
                                                                                                                                                                                                        <div class="portletIcons">
                                                                                                                                                                                                        <a href="javascript:animatedcollapse.hide('requirementListDivType')" title="Minimize">
                                                                                                                                                                                                        <img src="../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                                                                                                                                                                        <a href="javascript:animatedcollapse.show('requirementListDivType')" title="Maximize">
                                                                                                                                                                                                        <img src="../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                                                                                                                                                                        </a>
                                                                                                                                                                                                        </div>
                                                                                                                                                                                                        <div class="clear"></div>
                                                                                                                                                                                                        </div>
                                                                                                                                                                                                        <div id="requirementListDivType" style="background-color:#ffffff;">
                                                                                                                                                                                                        

                                                                                                                                                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td width="80%" valign="top" align="center">
                                                                                                                                                                                                        <s:form action="" theme="simple" id="requieremntListFormType" name="requieremntListForm" >   
                                                                                                                                                                                                        

                                                                                                                                                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                                                                                                                                                                        

                                                                                                                                                                                                        
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td>
                                                                                                                                                                                                        <table border="0" align="center" cellpadding="0" cellspacing="0">

                                                                                                                                                                                                       <tr>
                                                                                                                                                                                                        <td>
                                                                                                                                                                                                        <!-- Requirement List Div Start -->
                                                                                                                                                                                                       <div id="requirementListDiv" style="background-color:#ffffff;">
                                                                                                                                                                                                        
                                                                                                                                                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td width="80%" valign="top" align="center">
                                                                                                                                                                                                        <s:form action="" theme="simple" id="requieremntListForm" name="requieremntListForm" >   

                                                                                                                                                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">

                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td>
                                                                                                                                                                                                        <table border="0" align="center" cellpadding="0" cellspacing="0"  width="50%">
                                                                                                                                                                                                        <tr><td><br><span id="typevalue"></span>
</td></tr>
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td class="fieldLabel">Requirement&nbsp;Id :</td>
                                                                                                                                                                                                        <td>
                                                                                                                                                                                                        <s:textfield name="reqId"  id="requirementId" cssClass="inputTextBlue" theme="simple"/>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        <td class="fieldLabel">Status Of Requirement :</td>
                                                                                                                                                                                                        <td>   
                                                                                                                                                                                                        <%-- <s:select headerKey="All" headerValue="All" list="{'Open','InProgress','Closed','Lost'}" name="status" id="status" cssClass="inputSelect"/> --%>
                                                                                                                                                                                                        <s:select headerKey="All" headerValue="All" list="{'Forecast','Open','InProgress','Hold','Withdrawn','Won','Lost','Closed'}" name="requirementStatus" id="requirementStatus" cssClass="inputSelect" theme="simple" />
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        <tr>

                                                                                                                                                                                                        <td class="fieldLabel">Job Title :</td>
                                                                                                                                                                                                        <td>
                                                                                                                                                                                                        <s:textfield name="title" id="title" cssClass="inputTextBlue" theme="simple"/>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        <td class="fieldLabel">Practice :&nbsp;</td>
                                                                                                                                                                                                        <td>
                                                                                                                                                                                                        <s:select headerKey="-1" headerValue="--Please Select--" list="{'SAP','J2EE','Integration','Portals','Commerce','B2B','QA','BI','Microsoft','Others'}" name="practiceId" id="practiceId" cssClass="inputSelect" value="%{currentRequirement.practiceId}"/>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        <%--  <tr>
                                                                                                                                                                                                        <td class="fieldLabel">Assigned By :</td>
                                                                                                                                                                                                        <td>
                                                                                                                                                                                                        <s:select headerKey="All" headerValue="All" list="assignedByMap" name="assignedBy" id="assignedBy" cssClass="inputSelect" theme="simple"/>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        <td class="fieldLabel">Assigned To :</td>
                                                                                                                                                                                                        <td>
                                                                                                                                                                                                        <s:select headerKey="All" headerValue="All" list="assignedMembers" name="assignedTo" id="assignedTo" cssClass="inputSelect" theme="simple"/>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr> --%>
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td class="fieldLabel">PreSales Person :</td>
                                                                                                                                                                                                        <td>
                                                                                                                                                                                                        <s:select headerKey="" headerValue="All" list="techLeadList" name="preSalesPerson" id="preSalesPerson"  cssClass="inputSelect" theme="simple" />
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        <%--<td><s:select list="#session.myTeamMap" name="newloginId" id="newloginId"  cssClass="inputSelect" headerValue="select" headerKey="newloginId" theme="simple" /> </td> --%>   


                                                                                                                                                                                                        <td class="fieldLabel">Created By :</td>
                                                                                                                                                                                                        <td>
                                                                                                                                                                                                        <s:select headerKey="All" headerValue="All" name="createdBy" id="createdBy" list="createdMemebers" cssClass="inputSelect" theme="simple" />
                                                                                                                                                                                                        </td>


                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        </tr>

                                                                                                                                                                                                        <tr>




                                                                                                                                                                                                        <td class="fieldLabel">Country :&nbsp;</td>
                                                                                                                                                                                                        <td>
                                                                                                                                                                                                        <s:select headerKey="-1" headerValue="--Please Select--" name="requirementCountry" id="requirementCountry" list="{'USA','India','UK','Australia'}" cssClass="inputSelect" value="%{currentRequirement.country}" onchange="getStates(this.form, this.value);"/>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        <td class="fieldLabel">State :</td>
                                                                                                                                                                                                        <td>
                                                                                                                                                                                                        <%-- <select name="state" id="state" class="inputSelect"><option value="<s:property value="%{currentRequirement.state}"/>"><s:property value="%{currentRequirement.state}"/></option></select> --%>
                                                                                                                                                                                                        <select name="requirementState" id="requirementState" class="inputSelect"><option value="">--Please Select--</option></select>


                                                                                                                                                                                                        </td>

                                                                                                                                                                                                        </tr>


                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td class="fieldLabel">Date Posted (Start Date):</td>
                                                                                                                                                                                                        <td><s:textfield name="postedDate1" id="postedDate1" cssClass="inputTextBlue" theme="simple" /><a href="javascript:cal5.popup();">
                                                                                                                                                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        <td class="fieldLabel">Date Posted (End Date):</td>
                                                                                                                                                                                                        <td><s:textfield name="postedDate2" id="postedDate2" cssClass="inputTextBlue" theme="simple" /><a href="javascript:cal6.popup();">
                                                                                                                                                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td class="fieldLabel">Customer&nbsp;Name :</td>
                                                                                                                                                                                                        <td>
                                                                                                                                                                                                        <s:textfield name="customerName"  id="customerName" cssClass="inputTextBlue" theme="simple"/>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        <td class="fieldLabel">Type :&nbsp;</td>
                                                                                                                                                                                                        <td>
                                                                                                                                                                                                        <s:select  name="requirementListType" id="requirementListTypeDiv1" list="{'Requirement List','Req List OverView By Consultant Status'}" cssClass="inputSelect" value="%{requirementListType}"  onchange="getRequirementDiv(this.value);"/>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td colspan="3"></td>
                                                                                                                                                                                                        <td  align="right"> <br><input type="button" value="Search" class="buttonBg" onclick="getPresalesRequiremntList('1');"/></td></tr>
                                                                                                                                                                                                        <%--tr>
                                <td colspan="3"><br><br>
                                     <input type="button" id="reqListInfoDiv1" style="display:block" class="buttonBg" onclick="requirementListInfo()" value="Info"/>
                                    
                                </td>
                                </tr></tr--%>
                                                                                                                                                                                                        
                                                                                                                                                                                                       


                                                                                                                                                                                                         <tr>
                                                                                                                                                                                                        <td height="20px" align="center" colspan="4" >
                                                                                                                                                                                                        <div id="loadReqMessage" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                                                                                                                                        </td>

                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td height="20px" >
                                                                                                                                                                                                        <s:hidden name="pgNo" id="pgNo" cssClass="inputTextBlue" theme="simple"/>
                                                                                                                                                                                                        <s:hidden name="totalRecords" id="totalRecords" cssClass="inputTextBlue" theme="simple"/>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>

                                                                                                                                                                                                        <tr>

                                                                                                                                                                                                        <td colspan="4" >


                                                                                                                                                                                                        <div id="parent">
                                                                                                                                                                                                        <table id="tblPresalesRequirementList" class="dashBoardGridTable">
                                                                                                                                                                                                        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/wz_tooltip.js"/>"></script>
                                                                                                                                                                                                        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/jquery-2.1.3.js"/>"></script>
                                                                                                                                                                                                        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tableHeadFixer.js"/>"></script>    
                                                                                                                                                                                                        </table>

                                                                                                                                                                                                        </div>
                                                                                                                                                                                                        <div id="button_pageNation"></div>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>

                                                                                                                                                                                                        </table>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>


                                                                                                                                                                                                        </table>
                                                                                                                                                                                                        </s:form>
                                                                                                                                                                                                        <script type="text/JavaScript">
                                                                                                                                                                                                        var cal5 = new CalendarTime(document.forms['requieremntListForm'].elements['postedDate1']);
                                                                                                                                                                                                        cal5.year_scroll = true;
                                                                                                                                                                                                        cal5.time_comp = false;
                                                                                                                                                                                                        cal6 = new CalendarTime(document.forms['requieremntListForm'].elements['postedDate2']);
                                                                                                                                                                                                        cal6.year_scroll = true;
                                                                                                                                                                                                        cal6.time_comp = false;
                                                                                                                                                                                                        </script>
                                                                                                                                                                                                        </td></tr></table>
                                                                                                                                                                                                       </div>
                                                                                                                                                                                                        <!-- Requirement List Div End -->
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        
                                                                                                                                                                                                       
                                                                                                                                                                                                        
                                                                                                                                                                                                       

                                                                                                                                                                                                        
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td>
<!-- Requirement Overview Div Start -->
                                                                                                                                                                                                        <div id="requirementListOverviewDiv" style="background-color:#ffffff;display: none">
                                                                                                                                                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td width="80%" valign="top" align="center">
                                                                                                                                                                                                        <s:form action="" theme="simple" id="requieremntListOverviewForm" name="requieremntListOverviewForm" >   

                                                                                                                                                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">

                                                                                                                                                                                                        
                                                                                                                                                                                                        <%--table border="0" align="center" cellpadding="0" cellspacing="0" --%>
                                                                                                                                                                                                        <tr><td><br></td></tr>
                                                                                                                                                                                                        <tr>

                                                                                                                                                                                                        <td class="fieldLabel">Requirement&nbsp;Id :</td>
                                                                                                                                                                                                        <td>
                                                                                                                                                                                                        <s:textfield name="reqId"  id="requirementIdOverview" cssClass="inputTextBlue" theme="simple"/>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        <td class="fieldLabel">Status Of Requirement :</td>
                                                                                                                                                                                                        <td>   
                                                                                                                                                                                                        <%-- <s:select headerKey="All" headerValue="All" list="{'Open','InProgress','Closed','Lost'}" name="status" id="status" cssClass="inputSelect"/> --%>
                                                                                                                                                                                                        <s:select headerKey="All" headerValue="All" list="{'Forecast','Open','InProgress','Hold','Withdrawn','Won','Lost','Closed'}" name="requirementStatus" id="requirementStatusOverview" cssClass="inputSelect" theme="simple" />
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        <tr>

                                                                                                                                                                                                        <td class="fieldLabel">Job Title :</td>
                                                                                                                                                                                                        <td>
                                                                                                                                                                                                        <s:textfield name="title" id="titleOverview" cssClass="inputTextBlue" theme="simple"/>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        <td class="fieldLabel">Practice :&nbsp;</td>
                                                                                                                                                                                                        <td>
                                                                                                                                                                                                        <s:select headerKey="-1" headerValue="--Please Select--" list="{'SAP','J2EE','Integration','Portals','Commerce','B2B','QA','BI','Microsoft','Others'}" name="practiceId" id="practiceIdOverview" cssClass="inputSelect" value="%{currentRequirement.practiceId}"/>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        <%--  <tr>
                                                                                                                                                                                                        <td class="fieldLabel">Assigned By :</td>
                                                                                                                                                                                                        <td>
                                                                                                                                                                                                        <s:select headerKey="All" headerValue="All" list="assignedByMap" name="assignedBy" id="assignedBy" cssClass="inputSelect" theme="simple"/>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        <td class="fieldLabel">Assigned To :</td>
                                                                                                                                                                                                        <td>
                                                                                                                                                                                                        <s:select headerKey="All" headerValue="All" list="assignedMembers" name="assignedTo" id="assignedTo" cssClass="inputSelect" theme="simple"/>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr> --%>
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td class="fieldLabel">PreSales Person :</td>
                                                                                                                                                                                                        <td>
                                                                                                                                                                                                        <s:select headerKey="" headerValue="All" list="techLeadList" name="preSalesPerson" id="preSalesPersonOverview"  cssClass="inputSelect" theme="simple" />
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        <%--<td><s:select list="#session.myTeamMap" name="newloginId" id="newloginId"  cssClass="inputSelect" headerValue="select" headerKey="newloginId" theme="simple" /> </td> --%>   


                                                                                                                                                                                                        <td class="fieldLabel">Created By :</td>
                                                                                                                                                                                                        <td>
                                                                                                                                                                                                        <s:select headerKey="All" headerValue="All" name="createdBy" id="createdByOverview" list="createdMemebers" cssClass="inputSelect" theme="simple" />
                                                                                                                                                                                                        </td>


                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        

                                                                                                                                                                                                        <tr>




                                                                                                                                                                                                        <td class="fieldLabel">Country :&nbsp;</td>
                                                                                                                                                                                                        <td>
                                                                                                                                                                                                        <s:select headerKey="-1" headerValue="--Please Select--" name="requirementCountry" id="requirementCountryOverview" list="{'USA','India','UK','Australia'}" cssClass="inputSelect" value="%{currentRequirement.country}" onchange="getStates(this.form, this.value);"/>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        <td class="fieldLabel">State :</td>
                                                                                                                                                                                                        <td>
                                                                                                                                                                                                        <%-- <select name="state" id="state" class="inputSelect"><option value="<s:property value="%{currentRequirement.state}"/>"><s:property value="%{currentRequirement.state}"/></option></select> --%>
                                                                                                                                                                                                        <select name="requirementState" id="requirementStateOverview" class="inputSelect"><option value="">--Please Select--</option></select>


                                                                                                                                                                                                        </td>

                                                                                                                                                                                                        </tr>


                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td class="fieldLabel">Date Posted (Start Date):</td>
                                                                                                                                                                                                        <td><s:textfield name="postedDate1" id="postedDate1Overview" cssClass="inputTextBlue" theme="simple" /><a href="javascript:cal8.popup();">
                                                                                                                                                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        <td class="fieldLabel">Date Posted (End Date):</td>
                                                                                                                                                                                                        <td><s:textfield name="postedDate2" id="postedDate2Overview" cssClass="inputTextBlue" theme="simple" /><a href="javascript:cal7.popup();">
                                                                                                                                                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td class="fieldLabel">Customer&nbsp;Name :</td>
                                                                                                                                                                                                        <td>
                                                                                                                                                                                                        <s:textfield name="customerName"  id="customerNameOverview" cssClass="inputTextBlue" theme="simple"/>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        <td class="fieldLabel">Type :&nbsp;</td>
                                                                                                                                                                                                        <td>
                                                                                                                                                                                                        <s:select name="requirementListType" id="requirementListTypeDiv2" list="{'Requirement List','Req List OverView By Consultant Status'}" cssClass="inputSelect" value="%{requirementListType}"  onchange="getRequirementDiv(this.value);"/>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td colspan="3"></td>
                                                                                                                                                                                                        <td  align="right"> <br><input type="button" value="Search" class="buttonBg" onclick="requirementOverviewPieChartDetails('1');"/></td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                         <%--tr>
                                <td colspan="3"><br><br>
                                     <input type="button" id="reqListInfoDiv2" style="display:none" class="buttonBg" onclick="requirementListInfo()" value="Info"/>
                                    
                                </td>
                                </tr--%>
                                                                                                                                                                                                        

</table>
                                                                                                                                                                                                        
                                                                                                                                                                                                        <table align="center" cellpadding="2" border="0" cellspacing="1" width="50%" >
                                                                                                                                                                                                        
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td colspan="3">


                                                                                                                                                                                                        <div id="resultMessage1" align="center" style=" color: red;
                                                                                                                                                                                                        font-family: lucida-sans;
                                                                                                                                                                                                        font-size: 15px;
                                                                                                                                                                                                        font-style: normal;
                                                                                                                                                                                                        font-variant: normal;
                                                                                                                                                                                                        font-weight: bold; display: none;"></div>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td colspan="3" align="center">
                                                                                                                                                                                                        <div class="rcorners2" id="reqOverviewGraph" style="display:none;">

                                                                                                                                                                                                        <div id="requirementOverviewChart" style="width: 400px; height: 200px;" ></div>

                                                                                                                                                                                                        <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->



                                                                                                                                                                                                        <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                                                                                                                                                                        </div>

                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        <tr>
                                                                                                <td height="20px" align="center" colspan="4">
                                                                                                    <div id="loadActMessageASh" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                                </td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                                                                                                                        <td height="20px" align="center" colspan="4" >
                                                                                                                                                                                                        <div id="loadReqMessage" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                                                                                                                                        </td>

                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td height="20px" >
                                                                                                                                                                                                        <s:hidden name="reqState" id="reqState" cssClass="inputTextBlue" theme="simple"/>
                                                                                                                                                                                                        <s:hidden name="pgFlag" id="pgFlag1" cssClass="inputTextBlue" theme="simple"/>
                                                                                                                                                                                                        <s:hidden name="totalRecords" id="totalRecords1" cssClass="inputTextBlue" theme="simple"/>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>

                                                                                                                                                                                                        <tr>

                                                                                                                                                                                                        <td colspan="4" >


                                                                                                                                                                                                        <div id="parent1">
                                                                                                                                                                                                        <table id="tblRequirementOverviewDetails" class="dashBoardGridTable">
                                                                                                                                                                                                        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/wz_tooltip.js"/>"></script>
                                                                                                                                                                                                        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/jquery-2.1.3.js"/>"></script>
                                                                                                                                                                                                        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tableHeadFixer.js"/>"></script>    
                                                                                                                                                                                                        </table>

                                                                                                                                                                                                        </div>
                                                                                                                                                                                                        <div id="button_pageNation1"></div>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>

                                                                                                                                                                                                        </table>
                                                                                                                                                                                                       


                                                                                                                                                                                                        
                                                                                                                                                                                                        </s:form>
                                                                                                                                                                                                        <script type="text/JavaScript">
                                                                                                                                                                                                        var cal5 = new CalendarTime(document.forms['requieremntListForm'].elements['postedDate1']);
                                                                                                                                                                                                        cal5.year_scroll = true;
                                                                                                                                                                                                        cal5.time_comp = false;
                                                                                                                                                                                                        cal6 = new CalendarTime(document.forms['requieremntListForm'].elements['postedDate2']);
                                                                                                                                                                                                        cal6.year_scroll = true;
                                                                                                                                                                                                        cal6.time_comp = false;
                                                                                                                                                                                                        cal7 = new CalendarTime(document.forms['requieremntListOverviewForm'].elements['postedDate2Overview']);
                                                                                                                                                                                                        cal7.year_scroll = true;
                                                                                                                                                                                                        cal7.time_comp = false;
                                                                                                                                                                                                        var cal8 = new CalendarTime(document.forms['requieremntListOverviewForm'].elements['postedDate1Overview']);
                                                                                                                                                                                                        cal8.year_scroll = true;
                                                                                                                                                                                                        cal8.time_comp = false;
                                                                                                                                                                                                        </script>
                                                                                                                                                                                                        
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        <!--2nd td in First table -->
                                                                                                                                                                                                        


                                                                                                                                                                                                        </table>
                                                                                                                                                                                                        </div>
                                                                                                                                                                                                        <!-- Requirement Overview Div End --></td></tr>

                                                                                                                                                                                                        
                                                                                                                                                                                                        
                                                                                                                                                                                                        
                                                                                                                                                                                                        
                                                                                                                                                                                                        
                                                                                                                                                                                                        </table>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        </table>
                                                                                                                                                                                                        </s:form>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        </table>
                                                                                                                                                                                                        </div>
                                                                                                                                                                                            <!-- Requirement Div Type End -->


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


                                                                                                                                                                                                        <script type="text/javascript">
                                                                                                                                                                                                       
                                                                                                                                                                                                          
                                                                                                                                                                                                        
                                                                                                                                                                                                            function pagerOption(){

                                                             
                                                                                                                                                                                                                var  recordPage=20;
                                                                                                                                                                                                  
                                                                                                                                                                                                                //$('#tblPresalesRequirementList').tablePaginate({navigateType:'navigator'},recordPage);
                                                                                                                                                                                                                $('#tblPresalesRequirementList').tablePaginate({navigateType:'navigator'},recordPage,tblPresalesRequirementList);

                                                                                                                                                                                                            }
                                                                                                                                                                                                              function pagerOption1(){
                                                                                                                                                                                                            var  recordPage=20;
                                                                                                                                                                                                          //  alert("....")
                                                                                                                                                                                                             $('#tblRequirementOverviewDetails').tablePaginate({navigateType:'navigator'},recordPage,tblRequirementOverviewDetails);
                                                                                                                                                                                                        }
                                                                                                                                                                                                        </script>
                                                                                                                                                                                                        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DynamicPagination.js?version=1.0"/>"></script>
                                                                                                                                                                                                                <script type="text/javascript">
		$(window).load(function(){
	defaultDates();
		});
		</script>
                                                                                                                                                                                                        </body>

                                                                                                                                                                                                        </html>

