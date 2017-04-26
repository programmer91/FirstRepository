<%-- 
    Document   : optDashboardForReportsGeneration
    Created on : Jul 3, 2015, 12:38:45 AM
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
        <title>Hubble Organization Portal :: Project DashBoard</title>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
         
       <link rel="stylesheet" type="text/css" href="<s:url value="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css"/>">

        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/animatedcollapse.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/jquery-1.2.2.pack.js"/>"></script>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/Activity.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EnableEnter.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ReusableContainer.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/employee/DateValidator.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/OperationsDashBoardAjax.js?version=3.5"/>"></script>
          <script type="text/JavaScript" src="<s:url value="/includes/javascripts/crm/BDMDashBoard.js"/>"></script>
          
          <%-- <script type="text/javascript" src="<s:url value="/includes/javascripts/IssueFill.js"/>"></script> --%>
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tooltip.css"/>">

    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ajax/AjaxPopup.js"/>"></script> 

<script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmpStandardClientValidations.js"/>"></script>
       <%-- <script type="text/JavaScript" src="<s:url value="/includes/javascripts/payroll/payrollajaxscript.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/payroll/payrollclientvalidations.js"/>"></script> --%>
        <s:include value="/includes/template/headerScript.html"/>
        <script language="JavaScript">
                
           
//            animatedcollapse.addDiv('LeavesCountExcelReportDiv', 'fade=1;persist=1;group=app');
//            animatedcollapse.addDiv('GenerateTimeSeetReport', 'fade=1;persist=1;group=app');
//            animatedcollapse.addDiv('GenerateHierarchyReport', 'fade=1;persist=1;group=app');
             animatedcollapse.addDiv('SalesClosuresExcelReportDiv', 'fade=1;persist=1;group=app');
              animatedcollapse.addDiv('utilizationReport', 'fade=1;persist=1;group=app');
              animatedcollapse.addDiv('AccomadationReport', 'fade=1;persist=1;group=app');
               animatedcollapse.addDiv('salesKPIReportdiv', 'fade=1;persist=1;group=app');
               animatedcollapse.addDiv('recruitmentReportdiv', 'fade=1;persist=1;group=app');
               animatedcollapse.addDiv('AvailableEmpList', 'fade=1;persist=1;group=app');
               animatedcollapse.addDiv('projectSheetReports', 'fade=1;persist=1;group=app');
                         animatedcollapse.addDiv('MissingInfoReport', 'fade=1;persist=1;group=app');
                              animatedcollapse.addDiv('PFPOrtalGeneration', 'fade=1;persist=1;group=app');
                         
                animatedcollapse.addDiv('projectDetailsByCustomer', 'fade=1;persist=1;group=app');
            animatedcollapse.addDiv('OnboardReports', 'fade=1;persist=1;group=app');
               
            animatedcollapse.init();
            function hideSelect(){
                //document.getElementById("priorityId").style.display = 'none';
                
            }
            function getSalesClosuresReport(){
  
    var year = document.getElementById('yearForSalesClosuresReport').value;
    var clouserFlag = document.getElementById('isClouserFlag').checked;
    //alert(clouserFlag);
    
   // var practiceId = document.getElementById('practiceId').value;
    //var teamId = document.getElementById('teamId').value;
    if(year.length==''){
        alert("Please Enter year");
        return false;
    }
    //var empnameById = document.getElementById('empnameById').value;
   // window.location ="generateSalesClosuresReport.action?teamId="+teamId+"&practiceId="+practiceId+"&year="+year;  
    window.location ="generateSalesClosuresReport.action?year="+year+"&isClouserFlag="+clouserFlag;  
    return true;
}

function loadAvailabilityList(){
     var state=document.getElementById("state").value;
      if(state!="-1")
    {
        getAvailableList();
    }
}

function showResourceTypeDiv(){
    
    var state=document.getElementById('state').value;
    if(state=='OnProject'){
       document.getElementById('resTypeTr').style.display='';
   }else{
        document.getElementById('resTypeTr').style.display='none';
   }
    
}
        </script>
        <style type="text/css">

        .popupItem:hover {
            background: #F2F5A9;
            font: arial;
            font-size:10px;
            color: black;
        }

        .popupRow {
            background: #3E93D4;
        }

        .popupItem {
            padding: 2px;
            width: 100%;
            border: black;
            font:normal 9px verdana;
            color: white;
            text-decoration: none;
            line-height:13px;
            z-index:100;
        }

    </style>

    </head>
  <!--  <body  class="bodyGeneral" onload="getLocationsByCountryOnload();hideSelect();javascript:animatedcollapse.show('SalesClosuresExcelReportDiv');getdatesforProject();defaultDates();loadAvailabilityList();getPracticeDataV2();init();" oncontextmenu="return false;"> -->
    <body  class="bodyGeneral" oncontextmenu="return false;" onload="showResourceTypeDiv();">

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
                                <ul id="reportsTab" class="shadetabs" >
                                    <li><a href="#" rel="EmpReportsTab" class="selected"> Employee Reports DashBoard</a></li>

                                </ul>
                                <div  style="border:1px solid gray; width:840px;height:675px;overflow:auto; margin-bottom: 1em;">    
                                    <br><br>
                                    <div id="EmpReportsTab" class="tabcontent" >  
                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">


                                            <!-- new div for account list by priority start -->
                                        <%--    <tr>
                                                <td class="homePortlet" valign="top">
                                                    <div class="portletTitleBar">
                                                        <div class="portletTitleLeft">Generate Leaves Report</div>
                                                        <div class="portletIcons">
                                                            <a href="javascript:animatedcollapse.hide('LeavesCountExcelReportDiv')" title="Minimize">
                                                                <img src="../../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                            <a href="javascript:animatedcollapse.show('LeavesCountExcelReportDiv')" title="Maximize">
                                                                <img src="../../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                            </a>
                                                        </div>
                                                        <div class="clear"></div>
                                                    </div>
                                                    <div id="LeavesCountExcelReportDiv" style="background-color:#ffffff">
                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                            <tr>
                                                                <td width="40%" valign="top" align="center">
                                                                    <s:form action="generateLeavesCountReport" theme="simple" name="leaveReports" id="leaveReports">  

                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                                            <tr align="right">
                                                                                <td class="headerText" colspan="9">
                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">                                                        
                                                                                </td>
                                                                            </tr>

                                                                            <tr>
                                                                                <td>
                                                                                    <%
                                                                                        if (session.getAttribute("resultMessage") != null) {
                                                                                            out.println(session.getAttribute("resultMessage"));
                                                                                            session.removeAttribute("resultMessage");
                                                                                        }

                                                                                    %>
                                                                                </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td>
                                                                                    <table border="0" align="center" cellpadding="0" cellspacing="0">
                                                                                        <tr> 

                                                                                            <td class="fieldLabel" >Department :</td>
                                                                                            <td><s:select label="Select Department" 
                                                                                                      name="departmentId"
                                                                                                      id="departmentId"
                                                                                                      headerKey=""
                                                                                                      headerValue="All"
                                                                                                      list="departmentIdList" cssClass="inputSelect" value="%{departmentId}" onchange="getEmployeesByDept();"/></td>

                                                                                            <td class="fieldLabel">EmpName :</td>
                                                                                            <td ><s:select list="empnamesList" id="empnameById" name="empnameById" headerKey="" headerValue="All" cssClass="inputSelect" value="%{empnameById}"/></td> 
                                                                                            <td class="fieldLabel" >Country :</td>                           

                                                                                            <td><s:select label="Select Country" 
                                                                                                      name="country" id="country" headerKey=""            
                                                                                                      headerValue="-- Please Select --"
                                                                                                      list="countryList" cssClass="inputSelect" value="%{country}" /></td> 
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td class="fieldLabel">Year(YYYY):</td>
                                                                                            <td>

                                                                                                <s:textfield name="year" id="year" maxlength="4" cssClass="inputTextBlue" value="%{year}" onblur="yearValidation(this.value,event)" onkeypress="yearValidation(this.value,event)"/>
                                                                                            </td>
                                                                                            <td class="fieldLabel">Month:</td>
                                                                                            <td><s:select list="#@java.util.LinkedHashMap@{'1':'Jan','2':'Feb','3':'Mar','4':'Apr','5':'May','6':'June','7':'July','8':'Aug','9':'Sept','10':'Oct','11':'Nov','12':'Dec'}" name="month" id="month" onchange="" headerValue="select" headerKey="" value="%{month}"/></td>


                                                                                            <td colspan="2" width="200px" align="right">
                                                                                                <input type="button"  value="Generate" Class="buttonBg" onClick="checkValidationsForLeavesReports();"/>
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

                                                    </div>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td class="homePortlet" valign="top">
                                                    <div class="portletTitleBar">
                                                        <div class="portletTitleLeft">Generate Time Sheet Report</div>
                                                        <div class="portletIcons">
                                                            <a href="javascript:animatedcollapse.hide('GenerateTimeSeetReport')" title="Minimize">
                                                                <img src="../../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                            <a href="javascript:animatedcollapse.show('GenerateTimeSeetReport')" title="Maximize">
                                                                <img src="../../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                            </a>
                                                        </div>
                                                        <div class="clear"></div>
                                                    </div>
                                                    <div id="GenerateTimeSeetReport" style="background-color:#ffffff">
                                                        <%
                                                            if (session.getAttribute("resultMessageForTimeSheets") != null) {
                                                                out.println(session.getAttribute("resultMessageForTimeSheets"));
                                                                session.removeAttribute("resultMessageForTimeSheets");
                                                            }

                                                        %>
                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                            <tr>
                                                                <td width="40%" valign="top" align="center">
                                                                    <s:form action="" theme="simple" name="TimeSheetsReports">  
                                                                        <div id="resultMessageForFreeze" style="font-size: 15px;"></div>
                                                                        <div id="loadingMessageForFreeze" style="color: red;font-size: 15px;display: none;">Loading please wait..</div>
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
                                                                                            <td class="overlayFieldLabels">Year(YYYY):</td>
                                                                                            <td>

                                                                                                <s:textfield name="year" id="yearForTimeSheetReport" maxlength="4" cssClass="inputTextBlue" value="%{year}" />
                                                                                            </td>
                                                                                            <td class="overlayFieldLabels">Month:</td>
                                                                                            <td><s:select list="#@java.util.LinkedHashMap@{'1':'Jan','2':'Feb','3':'Mar','4':'Apr','5':'May','6':'June','7':'July','8':'Aug','9':'Sept','10':'Oct','11':'Nov','12':'Dec'}" name="month" id="monthForTimeSheetReport" onchange="load(event)" cssClass="inputSelectSmall" headerValue="select" headerKey="0" value="%{month}" /></td>

                                                                                        </tr>
                                                                                        <tr> 

                                                                                            <td class="fieldLabel" >Department :</td>
                                                                                            <td><s:select label="Select Department" 
                                                                                                      name="departmentId"
                                                                                                      id="departmenttId"
                                                                                                      headerKey=""
                                                                                                      headerValue="All"
                                                                                                      list="departmentIdList" cssClass="inputSelect" value="%{departmenttId}" onchange="getEmployeeByDept();"/></td>

                                                                                            <td class="fieldLabel">EmpName :</td>
                                                                                            <td ><s:select list="empnamesList" id="empnameByIdd" name="empnameById" headerKey="" headerValue="All" cssClass="inputSelect" value="%{empnameByIdd}"/></td> 
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td class="fieldLabel" >Country :</td>                           

                                                                                            <td><s:select label="Select Country" 
                                                                                                      name="country" id="countryy" headerKey=""            
                                                                                                      headerValue="-- Please Select --"
                                                                                                      list="countryList" cssClass="inputSelect" value="%{country}" /></td> 

                                                                                            <td colspan="2" width="200px" align="right">
                                                                                                <input type="button" value="Generate" cssClass="buttonBg" onclick="getTimeSheetDetails();"/>
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

                                                    </div>
                                                </td>
                                            </tr>

                                            <!-- new div for account list by priority end -->
                                            <tr>
                                                <td class="homePortlet" valign="top">
                                                    <div class="portletTitleBar">
                                                        <div class="portletTitleLeft">Generate Hierarchy Report</div>
                                                        <div class="portletIcons">
                                                            <a href="javascript:animatedcollapse.hide('GenerateHierarchyReport')" title="Minimize">
                                                                <img src="../../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                            <a href="javascript:animatedcollapse.show('GenerateHierarchyReport')" title="Maximize">
                                                                <img src="../../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                            </a>
                                                        </div>
                                                        <div class="clear"></div>
                                                    </div>
                                                    <div id="GenerateHierarchyReport" style="background-color:#ffffff">
                                                        <%
                                                            if (session.getAttribute("resultMessageForHierarchy") != null) {
                                                                out.println(session.getAttribute("resultMessageForHierarchy"));
                                                                session.removeAttribute("resultMessageForHierarchy");
                                                            }

                                                        %>
                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                            <tr>
                                                                <td width="40%" valign="top" align="center">
                                                                    <s:form action="" theme="simple" name="HierarchyReports">  
                                                                        <div id="resultMessageForFreeze" style="font-size: 15px;"></div>
                                                                        <div id="loadingMessageForFreeze" style="color: red;font-size: 15px;display: none;">Loading please wait..</div>
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




                                                                                        </tr>
                                                                                        <tr> 

                                                                                            <td class="fieldLabel" >Department :</td>
                                                                                            <td><s:select label="Select Department" 
                                                                                                      name="departmentId"
                                                                                                      id="departmenttIdd"
                                                                                                      headerKey=""
                                                                                                      headerValue="All"
                                                                                                      list="departmentIdList" cssClass="inputSelect" value="%{departmenttId}" onchange="getHierarchyEmployeeByDept();"/></td>

                                                                                            <td class="fieldLabel">EmpName :</td>
                                                                                            <td ><s:select list="empnamesList" 
                                                                                                      id="empnameByIddd" 
                                                                                                      name="empnameById" 
                                                                                                      headerKey="" 
                                                                                                      headerValue="All" 
                                                                                                      cssClass="inputSelect" value="%{empnameById}"/></td> 
                                                                                        </tr>
                                                                                        <tr>




                                                                                            <td colspan="2" width="200px" align="right">
                                                                                                <input type="button" value="Generate" cssClass="buttonBg" onclick="getHierarchyDetails();"/>
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

                                                    </div>
                                                </td>
                                            </tr>--%>
                                            <tr>
                                                <td class="homePortlet" valign="top">
                                                    <div class="portletTitleBar">
                                                        <div class="portletTitleLeft">Sales Closures</div>
                                                        <div class="portletIcons">
                                                            <a href="javascript:animatedcollapse.hide('SalesClosuresExcelReportDiv')" title="Minimize">
                                                                <img src="../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                            <a href="javascript:animatedcollapse.show('SalesClosuresExcelReportDiv')" title="Maximize">
                                                                <img src="../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                            </a>
                                                        </div>
                                                        <div class="clear"></div>
                                                    </div>
                                                    <div id="SalesClosuresExcelReportDiv" style="background-color:#ffffff">
                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                            <tr>
                                                                <td width="40%" valign="top" align="center">
                                                                    <s:form action="generateSalesClosuresReport" theme="simple" name="salesClosuresReports" id="salesClosuresReports">  

                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                                            <tr align="right">
                                                                                <td class="headerText" colspan="9">
                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">                                                        
                                                                                </td>
                                                                            </tr>

                                                                            <tr>
                                                                                <td>
                                                                                    <%
                                                                                        if (session.getAttribute("resultMessageForSalesClosure") != null) {
                                                                                            out.println(session.getAttribute("resultMessageForSalesClosure"));
                                                                                            session.removeAttribute("resultMessageForSalesClosure");
                                                                                        }

                                                                                    %>
                                                                                </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td>
                                                                                    <table border="0" align="center" cellpadding="0" cellspacing="0">
                                                                                        <%--<tr> 

                                                                                            <td class="fieldLabel" width="200px" align="right">Practice Name :</td>

                                                                                            <td><s:select label="Select Practice Name" 
                                                                                                      name="practiceId"  id="practiceId"
                                                                                                      headerKey=""
                                                                                                      headerValue="-- Please Select --"
                                                                                                      list="practiceIdList" cssClass="inputSelect" value="%{currentEmployee.practiceId}" onchange="getSubPracticeData();"/>

                                                                                            <td class="fieldLabel" width="200px" align="right">Team Name :</td>

                                                                                            <td><s:select label="Select Team Name" 
                                                                                                      name="teamId"  id="teamId"
                                                                                                      headerKey=""
                                                                                                      headerValue="-- Please Select --"
                                                                                                      list="teamIdList" cssClass="inputSelect" value="%{currentEmployee.teamId}" onchange=""/></td>
                                                                                                <%--<td class="fieldLabel">Region :</td>
                                                                                                <td ><s:select list="regionNamesList" id="regionNameById" name="regionNameById" headerKey="" headerValue="All" cssClass="inputSelect" value="%{regionNameById}"/></td>  

                                                                                        </tr>--%>
                                                                                        <tr>
                                                                                            <td class="fieldLabel" align="right" style="padding-left: 58px">Year(YYYY):</td>
                                                                                            <td>

                                                                                                <s:textfield name="year" id="yearForSalesClosuresReport" maxlength="4" cssClass="inputTextBlue" value="%{year}" onblur="yearValidation(this.value,event)" onkeypress="yearValidation(this.value,event)"/>
                                                                                            </td> 
                                                                                            

                                                                                            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                                                                            <td class="fieldLabel" align="right" style="padding: 20px"> <s:checkbox name="isClouserFlag" id="isClouserFlag"
                                                                                                        value="%{isClouserFlag}" 
                                                                                                        theme="simple" tabindex="11" onchange=""/>: Closures&nbsp;Closed</td> 
                                                                                            
                                                                                            <td  width="200px" align="center">
                                                                                                <input type="button" value="Generate" class="buttonBg" onclick="getSalesClosuresReport();"/>
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

                                                    </div>
                                                </td>
                                            </tr>
                                            
                                             <%-- UtiliZation Report  --%>

                                            <tr>
                                                <td class="homePortlet" valign="top">
                                                    <div class="portletTitleBar">
                                                        <div class="portletTitleLeft">Utilization Report</div>
                                                        <div class="portletIcons">
                                                            <a href="javascript:animatedcollapse.hide('utilizationReport')" title="Minimize">
                                                                <img src="../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                            <a href="javascript:animatedcollapse.show('utilizationReport')" title="Maximize">
                                                                <img src="../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                            </a>
                                                        </div>
                                                        <div class="clear"></div>
                                                    </div>
                                                    <div id="utilizationReport" style="background-color:#ffffff">
                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                            <tr>
                                                                <td width="40%" valign="top" align="center">
                                                                    <s:form theme="simple" name="utilizationReports" id="utilizationReports">  

                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                                            <tr align="right">
                                                                                <td class="headerText" colspan="9">
                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">                                                        
                                                                                </td>
                                                                            </tr>

                                                                            <tr>
                                                                                <td>
                                                                                    <%
                                                                                        if (session.getAttribute("resultMessageForSalesClosure") != null) {
                                                                                            out.println(session.getAttribute("resultMessageForSalesClosure"));
                                                                                            session.removeAttribute("resultMessageForSalesClosure");
                                                                                        }

                                                                                    %>
                                                                                </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td>
                                                                                    <table border="0" align="center" cellpadding="0" cellspacing="0">

                                                                                        <tr>
                                                                                            <td class="fieldLabel" width="200px" align="right">Operation Contact :<FONT color="red"  ><em>*</em></FONT> </td>                           
                                                                                            <td>

                                                                                                <s:select label="Select Point of Contact" name="opsContactId" id="opsContactId" headerKey="-1" headerValue="-- Please Select --" list="opsContactIdMap" cssClass="inputSelect" value="%{opsContactId}"  />    

                                                                                            </td>


                                                                                            <td class="fieldLabel" width="200px" align="right">Country :</td>

                                                                                            <td colspan="3"><s:select label="Select Country" name="country" id="country" headerKey="-1" headerValue="-- Please Select --" list="countryList" cssClass="inputSelect" value="%{country}"/></td>                                                                                                                                                                                                                   

                                                                                            <td></td>
                                                                                            <td></td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td class="fieldLabel" width="200px" align="right">Department :</td>

                                                                                            <td><s:select headerKey="-1" headerValue="--Please Select--" list="{'SSG','GDC'}" name="departmentId" id="departmentId" cssClass="inputSelect" value="%{departmentId}" onchange="getPracticeDataV1();"/></td>


                                                                                            <td class="fieldLabel" width="200px" align="right">Practice Name :</td>

                                                                                            <td><s:select label="Select Practice Name" name="practiceId"  id="practiceId" headerKey="-1" headerValue="--Please Select--" list="practiceIdList" cssClass="inputSelect" value="%{practiceId}" /></td>
                                                                                            <td></td>
                                                                                            <td width="200px" align="center">
                                                                                                <input type="button" value="Search" class="buttonBg" onclick="getUtilizationReport()"/>
                                                                                            </td> 


                                                                                        </tr>
                                                                                        <tr>

                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td class="fieldLabel" >Total&nbsp;Records:</td>
                                                                                            <td class="userInfoLeft" id="totalState" ></td>   
                                                                                            <td class="fieldLabel" >Available&nbsp;: </td>
                                                                                            <td class="userInfoLeft"  id="totalAvailable" ></td>

                                                                                            <td class="fieldLabel" >OverHead&nbsp;: </td>
                                                                                            <td class="userInfoLeft"  id="totalOnBench" ></td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td class="fieldLabel" > OnProject&nbsp;:  </td>
                                                                                            <td class="userInfoLeft"  id="totalOnProject" ></td>

                                                                                            <td class="fieldLabel" > R&D/POC&nbsp;: </td>
                                                                                            <td class="userInfoLeft"  id="totalRP" ></td>
                                                                                            <td class="fieldLabel" > Training&nbsp;: </td>
                                                                                            <td class="userInfoLeft"  id="totalTraining" ></td>
                                                                                        </tr>
                                                                                    </table>
                                                                                </td>
                                                                            </tr>

                                                                            <%-- table grid --%>
                                                                            <tr>
                                                                                <td>
                                                                                    <br>
                                                                                    <table align="center" cellpadding="2" border="0" cellspacing="1" width="50%" >

                                                                                        <tr>
                                                                                            <td height="20px" align="center" colspan="9">
                                                                                                <div id="utilityReport" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                            </td>
                                                                                        </tr>


                                                                                        <tr>
                                                                                            <td ><br>
                                                                                                <div id="utilityReport" style="display: block">
                                                                                                    <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                                                    <table id="tblutilityReport" align='center' i cellpadding='1' cellspacing='1' border='0' class="gridTable" width='700'>
                                                                                                        <COLGROUP ALIGN="left" >
                                                                                                            <COL width="5%">
                                                                                                            <COL width="10%">
                                                                                                            <COL width="10%">
                                                                                                            <COL width="10%">
                                                                                                            <COL width="10%">
                                                                                                            <COL width="10%">
                                                                                                            <COL width="10%"></td>
                                                                                                    </table> <br>
                                                                                                    <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                                                                </div>
                                                                                            </td>
                                                                                        </tr>
                                                                                    </table>    
                                                                                </td>
                                                                            </tr>
                                                                            <%-- table grid  end--%>
                                                                        </table>
                                                                    </s:form>
                                                                </td>
                                                            </tr>
                                                        </table>

                                                    </div>
                                                </td>
                                            </tr>


                                            <%-- UtiliZation Report End --%>                                           


                                            <%-- Accomadation report start --%> 
                                            <tr>
                                                <td class="homePortlet" valign="top">
                                                    <div class="portletTitleBar">
                                                        <div class="portletTitleLeft">Accomadation Report</div>
                                                        <div class="portletIcons">
                                                            <a href="javascript:animatedcollapse.hide('AccomadationReport')" title="Minimize">
                                                                <img src="../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                            <a href="javascript:animatedcollapse.show('AccomadationReport')" title="Maximize">
                                                                <img src="../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                            </a>
                                                        </div>
                                                        <div class="clear"></div>
                                                    </div>
                                                    <div id="AccomadationReport" style="background-color:#ffffff">
                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                            <tr>
                                                                <td width="40%" valign="top" align="center">
                                                                    <s:form theme="simple" name="accomadationReport1" id="accomadationReport1">  

                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                                            <tr align="right">
                                                                                <td class="headerText" colspan="9">
                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">                                                        
                                                                                </td>
                                                                            </tr>

                                                                            <tr>
                                                                               </tr>
                                                                            <tr>


                                                                                <td>
                                                                                    <table border="0" align="center" cellpadding="0" cellspacing="0">
                                                                                        <tr>
                                                                                            <td class="fieldLabel" width="200px" align="right">Operation Contact :<FONT color="red"  ><em>*</em></FONT> </td>                           
                                                                                            <td> <s:select label="Select Point of Contact" name="opsContactIdForAcc" id="opsContactIdForAcc" headerKey="-1" headerValue="-- Please Select --" list="opsContactIdMap" cssClass="inputSelect"   /> </td> 
                                                                                            <td width="200px" align="center">
                                                                                                <input type="button" value="Search" class="buttonBg" onclick="getAccomadationReport();"/>
                                                                                            </td> 

                                                                                        </tr>
                                                                                    </table>
                                                                                </td>
                                                                            </tr>

                                                                            <%-- table grid --%>
                                                                            <tr>
                                                                                <td>
                                                                                    <br>
                                                                                    <table align="center" cellpadding="2" border="0" cellspacing="1" width="50%" >

                                                                                        <tr>
                                                                                            <td height="20px" align="center" colspan="9">
                                                                                                <div id="accomadationReport" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                            </td>
                                                                                        </tr>


                                                                                        <tr>
                                                                                            <td ><br>
                                                                                                <div id="accomadationReport" style="display: block">
                                                                                                    <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                                                    <table id="tblAccomadationReport" align='center' i cellpadding='1' cellspacing='1' border='0' class="gridTable" width='800'>
                                                                                                        <COLGROUP ALIGN="left" >
                                                                                                            <COL width="5%">
                                                                                                            <COL width="20%">
                                                                                                            <COL width="15%">
                                                                                                            <COL width="15%">
                                                                                                            <COL width="5%">
                                                                                                            <COL width="5%">
                                                                                                            <COL width="5%">
                                                                                                            <COL width="5%">
                                                                                                            <COL width="5%">
                                                                                                            <COL width="15%">
                                                                                                    </table>
 <br>
                                                                                                    <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                                                                </div>
                                                                                            </td>
                                                                                        </tr>
                                                                                    </table>    
                                                                                </td>
                                                                            </tr>
                                                                            <%-- table grid  end--%>
                                                                        </table>
                                                                    </s:form>
                                                                </td>
                                                            </tr>
                                                        </table>

                                                    </div>
                                                </td>
                                            </tr>

                                            <%-- Accomadation report End --%> 

                                            
 <%--Sales KPI starts--%>
                                            <tr>
                                                <td class="homePortlet" valign="top">
                                                    <div class="portletTitleBar">
                                                        <div class="portletTitleLeft">Sales KPI Report</div>
                                                        <div class="portletIcons">
                                                            <a href="javascript:animatedcollapse.hide('salesKPIReportdiv')" title="Minimize">
                                                                <img src="../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                            <a href="javascript:animatedcollapse.show('salesKPIReportdiv')" title="Maximize">
                                                                <img src="../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                            </a>
                                                        </div>
                                                        <div class="clear"></div>
                                                    </div>
                                                    <div id="salesKPIReportdiv" style="background-color:#ffffff">
                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                            <tr>
                                                                <td width="40%" valign="top" align="center">
                                                                    <s:form theme="simple" name="salesKPIReports" id="salesKPIReports" >  

                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                                            <tr align="right">
                                                                                <td class="headerText" colspan="9">
                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">                                                        
                                                                                </td>
                                                                            </tr>

                                                                            <tr>
                                                                                <td>
                                                                                    <%
                                                                                        if (session.getAttribute("resultMessageForSales") != null) {
                                                                                            out.println(session.getAttribute("resultMessageForSales"));
                                                                                            session.removeAttribute("resultMessageForSales");
                                                                                        }

                                                                                    %>
                                                                                </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td>
                                                                                    <table border="0" align="center" cellpadding="0" cellspacing="0">

                                                                                        <tr>

                                                                                            <td class="fieldLabel">Start&nbsp;Date&nbsp;(mm/dd/yyyy)&nbsp;:<FONT color="red"  ><em>*</em></FONT></td>
                                                                                            <td><s:textfield name="salesStartDate" id="salesStartDate" cssClass="inputTextBlueSmall" value=""/><a href="javascript:cal1.popup();">
                                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                                            </td>

                                                                                            <td class="fieldLabel">End&nbsp;Date&nbsp;(mm/dd/yyyy)&nbsp;:<FONT color="red"  ><em>*</em></FONT></td>
                                                                                            <td>  <s:textfield name="salesEndDate" id="salesEndDate" cssClass="inputTextBlueSmall" value=""/><a href="javascript:cal2.popup();">
                                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                                            </td>

                                                                                        </tr>
                                                                                        <tr></tr>
                                                                                        <tr>
                                                                                            <td class="fieldLabel" width="200px" align="right">Sales Lead :<FONT color="red"  ><em>*</em></FONT> </td>                           
                                                                                            <td>

                                                                                                <s:select label="Select Sales Lead" name="salesLeadId" id="salesLeadId" headerKey="-1" headerValue="-- Please Select --" list="salesLeadMap" cssClass="inputSelect" value="%{salesLeadId}"  />    

                                                                                            </td>
                                                                                            <td width="200px" align="center">
                                                                                                <input type="button" value="Search" class="buttonBg" onclick="getSalesKPIReport()"/>
                                                                                            </td> 

                                                                                        </tr>


                                                                                    </table>

                                                                                </td>
                                                                            </tr>

                                                                            <%-- table grid --%>
                                                                            <tr>
                                                                                <td>
                                                                                    <br>
                                                                                    <table align="center" cellpadding="2" border="0" cellspacing="1" width="50%" >
                                                                                        <tr>
                                                                                            <td height="20px" align="center" colspan="9">
                                                                                                <div id="salesKPIReport" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td ><br>
                                                                                                <div id="salesKPIReport" style="display: block">
                                                                                                    <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                                                    <table id="tblsalesReport" align='center' i cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750'>
                                                                                                        <COLGROUP ALIGN="left" >
                                                                                                            <COL width="10%">
                                                                                                            <COL width="25%">
                                                                                                            <COL width="15%">
                                                                                                            <COL width="15%">
                                                                                                    </table> <br>
                                                                                                    <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                                                                </div>
                                                                                            </td>
                                                                                        </tr>
                                                                                    </table>    
                                                                                </td>
                                                                            </tr>
                                                                            <%-- table grid  end--%>
                                                                        </table>
                                                                    </s:form>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                        <script type="text/JavaScript">
                                                            var cal1 = new CalendarTime(document.forms['salesKPIReports'].elements['salesStartDate']);
                                                            cal1.year_scroll = true;
                                                            cal1.time_comp = false;
                                                            var cal2 = new CalendarTime(document.forms['salesKPIReports'].elements['salesEndDate']);
                                                            cal2.year_scroll = true;
                                                            cal2.time_comp = false;
                                                        </script> 
                                                    </div>
                                                </td>
                                            </tr>


                                            <%--Sales KPI End--%>
                                            
                                            <%-- Recruitment KPI Report start--%>

                                            <tr>
                                                <td class="homePortlet" valign="top">
                                                    <div class="portletTitleBar">
                                                        <div class="portletTitleLeft">Recruitment KPI Report</div>
                                                        <div class="portletIcons">
                                                            <a href="javascript:animatedcollapse.hide('recruitmentReportdiv')" title="Minimize">
                                                                <img src="../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                            <a href="javascript:animatedcollapse.show('recruitmentReportdiv')" title="Maximize">
                                                                <img src="../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                            </a>
                                                        </div>
                                                        <div class="clear"></div>
                                                    </div>
                                                    <div id="recruitmentReportdiv" style="background-color:#ffffff">
                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                            <tr>
                                                                <td width="40%" valign="top" align="center">
                                                                    <s:form theme="simple" name="recruitmentReports" id="recruitmentReports">  

                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                                            <tr align="right">
                                                                                <td class="headerText" colspan="9">
                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">                                                        
                                                                                </td>
                                                                            </tr>

                                                                            <tr>
                                                                                <td>
                                                                                    <%
                                                                                        if (session.getAttribute("resultMessageForRecritment") != null) {
                                                                                            out.println(session.getAttribute("resultMessageForRecritment"));
                                                                                            session.removeAttribute("resultMessageForRecritment");
                                                                                        }

                                                                                    %>
                                                                                </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td>
                                                                                    <table border="0" align="center" cellpadding="0" cellspacing="0">

                                                                                        <tr>
                                                                                            <td class="fieldLabel">Start&nbsp;Date&nbsp;(mm/dd/yyyy)&nbsp;:</td>
                                                                                            <td><s:textfield name="startDateKPI" id="startDateKPI" cssClass="inputTextBlueSmall" value=""/><a href="javascript:cal3.popup();">
                                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                                            </td>

                                                                                            <td class="fieldLabel">End&nbsp;Date&nbsp;(mm/dd/yyyy)&nbsp;:</td>
                                                                                            <td>  <s:textfield name="endDateKPI" id="endDateKPI" cssClass="inputTextBlueSmall" value=""/><a href="javascript:cal4.popup();">
                                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td class="fieldLabel" width="200px" align="right">Recruitment Members:<FONT color="red"  ><em>*</em></FONT></td>                           
                                                                                            <td>

                                                                                                <s:select label="Select Point of Contact" name="recManagerId" id="recManagerId" headerKey="-1" headerValue="-- Please Select --" list="recmemebersMap" cssClass="inputSelect" value="%{recManagerId}"  />    

                                                                                            </td>
                                                                                          
                                                                                            <td width="200px" align="center">
                                                                                                <input type="button" value="Search" class="buttonBg" onclick="getRecruitmentKpiReport()"/>
                                                                                            </td> 


                                                                                        </tr>



                                                                                    </table>

                                                                                </td>
                                                                            </tr>

                                                                            <%-- table grid --%>
                                                                            <tr>
                                                                                <td>
                                                                                    <br>
                                                                                    <table align="center" cellpadding="2" border="0" cellspacing="1" width="50%" >

                                                                                        <tr>
                                                                                            <td height="20px" align="center" colspan="9">
                                                                                                <div id="recruitmentReport" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                            </td>
                                                                                        </tr>


                                                                                        <tr>
                                                                                            <td ><br>
                                                                                                <div id="recruitmentReport" style="display: block">
                                                                                                    <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                                                    <table id="tblrecruitmentReport" align='center' i cellpadding='1' cellspacing='1' border='0' class="gridTable" width='700'>
                                                                                                        <COLGROUP ALIGN="left" >
                                                                                                            <COL width="5%">
                                                                                                            <COL width="20%">
                                                                                                            <COL width="15%">
                                                                                                            <COL width="10%">
                                                                                                            <COL width="15%">
                                                                                                            <COL width="15%">
                                                                                                            <COL width="10%">
                                                                                                            <COL width="30%"></td>
                                                                                                    </table> <br>
                                                                                                    <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                                                                </div>
                                                                                            </td>
                                                                                        </tr>
                                                                                    </table>    
                                                                                </td>
                                                                            </tr>
                                                                            <%-- table grid  end--%>
                                                                        </table>
                                                                    </s:form>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                        <script type="text/JavaScript">
                                                            var cal3 = new CalendarTime(document.forms['recruitmentReports'].elements['startDateKPI']);
                                                            cal3.year_scroll = true;
                                                            cal3.time_comp = false;
                                                            var cal4 = new CalendarTime(document.forms['recruitmentReports'].elements['endDateKPI']);
                                                            cal4.year_scroll = true;
                                                            cal4.time_comp = false;
                                                        </script>  
                                                    </div>
                                                </td>
                                            </tr>
                                            <%-- Recruitment KPI Report End --%> 
                                             <%-- Available Employees  List Report  --%>

                                            <tr>
                                                <td class="homePortlet" valign="top">
                                                    <div class="portletTitleBar">
                                                        <div class="portletTitleLeft">Available Employees List</div>
                                                        <div class="portletIcons">
                                                            <a href="javascript:animatedcollapse.hide('AvailableEmpList')" title="Minimize">
                                                                <img src="../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                            <a href="javascript:animatedcollapse.show('AvailableEmpList')" title="Maximize">
                                                                <img src="../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                            </a>
                                                        </div>
                                                        <div class="clear"></div>
                                                    </div>
                                                    <div id="AvailableEmpList" style="background-color:#ffffff">
                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                            <tr>
                                                                <td width="40%" valign="top" align="center">
                                                                    <s:form theme="simple" name="availableEmpList" id="availableEmpList">  

                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                                            <tr align="right">
                                                                                <td class="headerText" colspan="9">
                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">                                                        
                                                                                </td>
                                                                            </tr>

                                                                            <tr>
                                                                                <td>
                                                                                    <%
                                                                                        if (session.getAttribute("resultMessageForUtility") != null) {
                                                                                            out.println(session.getAttribute("resultMessageForUtility"));
                                                                                            session.removeAttribute("resultMessageForUtility");
                                                                                        }

                                                                                    %>
                                                                                </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td>
                                                                                    <table border="0" align="center" cellpadding="0" cellspacing="0">


                                                                                        <tr>
                                                                                            <td class="fieldLabel" width="200px" align="right">Department :</td>

                                                                                            <td><s:select headerKey="-1" headerValue="--Please Select--" list="{'SSG','GDC'}" name="departmentId1" id="departmentId1" cssClass="inputSelect" onchange="getPracticeDataV2();"/></td>
                                                                                            <td class="fieldLabel" width="200px" align="right">Practice Name :</td>

                                                                                            <td><s:select name="practiceId1"  id="practiceId1" headerKey="-1" headerValue="--Please Select--" list="practiceIdList" cssClass="inputSelect" onchange="getSubPracticeData2();"/></td>

                                                                                        </tr>
                                                                                        <tr>

                                                                                            <td class="fieldLabel" width="200px" align="right">Country :</td>

                                                                                            <td><s:select label="Select Country" name="country1" id="country1" headerKey="-1" headerValue="-- Please Select --" list="countryList" cssClass="inputSelect" /></td>        

                                                                                            <td class="fieldLabel" width="200px" align="right">State :<FONT color="red"  ><em>*</em></FONT> </td>
                                                                                            <td><s:select name="state"  id="state" headerKey="-1" headerValue="--Please Select--" list="stateList" cssClass="inputSelect" onchange="showResourceTypeDiv()"/></td>

                                                                                        </tr>
                                                                                       <tr>
                                                                                             <td class="fieldLabel" width="200px" align="right">SubPractice&nbsp;Name&nbsp:</td>
                                                                                             <td><s:select name="subPractice1"  id="subPractice1" headerKey="-1" headerValue="--Please Select--" list="subPracticeList" cssClass="inputSelect" /></td>
                                                                                            <td class="fieldLabel" width="200px" align="right">Sorting&nbsp;Based&nbspOn:</td>
                                                                                             <td><s:select name="sortedBy"  id="sortedBy" list="{'EmployeeName','Practice'}" cssClass="inputSelect" /></td>
                                                                                            

                                                                                        </tr>
                                                                                       <tr id="resTypeTr" style="display:none;">
                                                                                             <td class="fieldLabel" width="200px" align="right">Resource&nbsp;Type:</td>
                                                                                             <td><s:select name="resourceType"  id="resourceType" headerKey="-1" headerValue="--Please Select--" list="{'Main(Billable)','Main','Shadow','Training','Overhead'}" cssClass="inputSelect" /></td>
                                                                                         </tr>
                                                                                        <tr>
                                                                                       
                                                                                                   <td colspan="3"></td>
                                                                                            <td width="200px" align="center">
                                                                                                <input type="button" value="Search" class="buttonBg" onclick="getAvailableList()"/>
                                                                                            </td> 
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td class="fieldLabel" >Total&nbsp;Records:</td>
                                                                                            <td class="userInfoLeft" id="totalState1" ></td>   
                                                                                            <%--  <td class="fieldLabel" >Available&nbsp;Count: </td>
                                                                                              <td class="userInfoLeft"  id="totalAvailable" ></td>

                                                                                            <td class="fieldLabel" > OnBench&nbsp;Count: </td>
                                                                                            <td class="userInfoLeft"  id="totalOnBench" ></td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td class="fieldLabel" > OnProject&nbsp;Count:  </td>
                                                                                            <td class="userInfoLeft"  id="totalOnProject" ></td>

                                                                                            <td class="fieldLabel" > R&D/POC&nbsp;Count: </td>
                                                                                            <td class="userInfoLeft"  id="totalRP" ></td>
                                                                                            <td class="fieldLabel" > Training&nbsp;Count: </td>
                                                                                            <td class="userInfoLeft"  id="totalTraining" ></td> --%>
                                                                                        </tr>
                                                                                    </table>
                                                                                </td>
                                                                            </tr>

                                                                            <%-- table grid --%>
                                                                            <tr>
                                                                                <td>
                                                                                    <br>
                                                                                    <table align="center" cellpadding="2" border="0" cellspacing="1" width="50%" >

                                                                                        <tr>
                                                                                            <td height="20px" align="center" colspan="9">
                                                                                                <div id="availableReport" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                            </td>
                                                                                        </tr>


                                                                                        <tr>
                                                                                            <td ><br>
                                                                                                <div id="availableReport" style="display: block">
                                                                                                    <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                                                    <table id="tblAvailableReport" align='center' i cellpadding='1' cellspacing='1' border='0' class="gridTable" width='700'>
                                                                                                        <COLGROUP ALIGN="left" >
                                                                                                            <COL width="5%">
                                                                                                            <COL width="15%">
                                                                                                            <COL width="10%">
                                                                                                            <COL width="10%">
                                                                                                            <COL width="10%">
                                                                                                            <COL width="50%">
                                                                                                    </table> <br>
                                                                                                    <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                                                                </div>
                                                                                            </td>
                                                                                        </tr>
                                                                                    </table>    
                                                                                </td>
                                                                            </tr>
                                                                            <%-- table grid  end--%>
                                                                        </table>
                                                                    </s:form>
                                                                </td>
                                                            </tr>
                                                        </table>

                                                    </div>
                                                </td>
                                            </tr>
                                            <s:hidden id="isAdminFlag" value="%{isAdminFlag}"></s:hidden>
                                             <s:hidden id="userId" value="%{#session.userId}"></s:hidden>

                                            <%-- Available Employees List End --%>  
                                             <s:if test="#session.userId == 'rijju' || isAdminFlag=='YES'">
                                            <%-- Project sheet Start--%>
                                            <tr>
                                                <td class="homePortlet" valign="top">
                                                    <div class="portletTitleBar">
                                                        <div class="portletTitleLeft">Project sheet</div>
                                                        <div class="portletIcons">
                                                            <a href="javascript:animatedcollapse.hide('projectSheetReports')" title="Minimize">
                                                                <img src="../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                            <a href="javascript:animatedcollapse.show('projectSheetReports')" title="Maximize">
                                                                <img src="../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                            </a>
                                                        </div>
                                                        <div class="clear"></div>
                                                    </div>
                                                    <div id="projectSheetReports" style="background-color:#ffffff">
                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                            <tr>
                                                                <td width="40%" valign="top" align="center">
                                                                    <s:form theme="simple" name="projectReports" id="projectReports">  

                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                                            <tr align="right">
                                                                                <td class="headerText" colspan="9">
                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">                                                        
                                                                                </td>
                                                                            </tr>

                                                                            <tr>
                                                                                <td>
                                                                                    <%
                                                                                        if (session.getAttribute("resultMessageForProjectSheet") != null) {
                                                                                            out.println(session.getAttribute("resultMessageForProjectSheet"));
                                                                                            session.removeAttribute("resultMessageForProjectSheet");
                                                                                        }

                                                                                    %>
                                                                                </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td>
                                                                                    <table border="0" align="center" cellpadding="0" cellspacing="0" width="800;">

                                                                                         <s:if test="%{isAdminFlag=='YES'}">
                                                                                         <td class="fieldLabel" >Country :<FONT color="red"  ><em>*</em></FONT></td>

                                                                                            <td colspan=""><s:select name="country2" id="country2" headerKey="-1" headerValue="-- Please Select --" list="countryList" cssClass="inputSelect"/></td>                                                                                                                                                                                                                   
                                                                                    </s:if><s:else>
                                                                                        <s:hidden name="country2" id="country2" value="-1"/>
                                                                                    </s:else>
                                                                                            <td class="fieldLabel" >Report Type :<FONT color="red"  ><em>* </em></FONT></td><td><s:select cssClass="inputSelect" list="#@java.util.LinkedHashMap@{'1':'Total OnProject Employees','2':'OnProject Employees Between Dates','3':'Total Available Employees','4':'Available Employees Between Dates','5':'Closed Employees List','6':'Total Employees List Except OnProject Status'}" name="flog" id="flog"  headerValue="--Please Select--" headerKey="-1" value="%{flog}" onchange="disbaleDates();"/></td> 
                                                                                           
                                                                                    </tr>
                                                                                        
                                                                                        
                                                                                            <tr>
                                                                                                
                                                                                            <td class="fieldLabel">Start&nbsp;Date&nbsp;(mm/dd/yyyy)&nbsp;:<FONT color="red"  ><em>*</em></FONT></td>
                                                                                            <td><s:textfield name="startDateProj" id="startDateProj" cssClass="inputTextBlueSmall"  onchange="checkDates(this);"/><a id="startDateTag" href="javascript:cal7.popup();">
                                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                                            </td>

                                                                                            <td class="fieldLabel">End&nbsp;Date&nbsp;(mm/dd/yyyy)&nbsp;:<FONT color="red"  ><em>*</em></FONT></td>
                                                                                            <td>  <s:textfield name="endDateProj" id="endDateProj" cssClass="inputTextBlueSmall" onfocus="changeStartDateByEnddate();" onchange="checkDates(this);changeStartDateByEnddate();"/><a href="javascript:cal8.popup();" >
                                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                                                    
                                                                                            </td>
                                                                                             </tr>
                                                                                    <tr>
                                                                                        <td class="fieldLabel" width="200px" align="right">Department :</td>

                                                                                            <td><s:select headerKey="-1" headerValue="All" list="{'SSG','GDC'}" name="departmentId2" id="departmentId2" cssClass="inputSelect"/></td>
                                                                                          <td class="fieldLabel" width="200px" align="right">Project&nbsp;Report&nbsp;Type :</td>

                                                                                            <td><s:select headerKey="0" headerValue="---Please Select---" list="#@java.util.LinkedHashMap@{'1':'Only ActiveProjects','2':'ActiveProjects With highest Utilization'}" name="ActiveProjects" id="ActiveProjects" cssClass="inputSelect"/></td>
                                                           
	

                                                                                             <td width="200px"  align="center">
                                                                                                <input type="button" value="Generate" class="buttonBg" onclick="getProjectSheets()"/>
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
                                                            var cal7 = new CalendarTime(document.forms['projectReports'].elements['startDateProj']);
                                                            cal7.year_scroll = true;
                                                            cal7.time_comp = false;
                                                            var cal8 = new CalendarTime(document.forms['projectReports'].elements['endDateProj']);
                                                            cal8.year_scroll = true;
                                                            cal8.time_comp = false;
                                                        </script>
                                                    </div>
                                                </td>
                                            </tr>
											

                                            <%-- Project sheet End--%>
                                </s:if>
                                            
                                            
                                            <%-- PF Portal Generation  Start --%>
                                            <s:if test="#session.pfExcelAccess == 1">
                                            <tr>
                                                <td class="homePortlet" valign="top">
                                                    <div class="portletTitleBar">
                                                        <div class="portletTitleLeft">Employee Details for PF office</div>
                                                        <div class="portletIcons">
                                                            <a href="javascript:animatedcollapse.hide('PFPOrtalGeneration')" title="Minimize">
                                                                <img src="../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                            <a href="javascript:animatedcollapse.show('PFPOrtalGeneration')" title="Maximize">
                                                                <img src="../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                            </a>
                                                        </div>
                                                        <div class="clear"></div>
                                                    </div>
                                                    <div id="PFPOrtalGeneration" style="background-color:#ffffff">
                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                            <tr>
                                                                <td width="40%" valign="top" align="center">
                                                                    <s:form theme="simple" name="pFPOrtalGeneration" id="pFPOrtalGeneration">  

                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                                            <tr align="right">
                                                                                <td class="headerText" colspan="9">
                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">                                                        
                                                                                </td>
                                                                            </tr>

                                                                            <tr>
                                                                                <td>
                                                                                    <%
                                                                                        if (session.getAttribute("resultMessageForUtility") != null) {
                                                                                            out.println(session.getAttribute("resultMessageForUtility"));
                                                                                            session.removeAttribute("resultMessageForUtility");
                                                                                        }

                                                                                    %>
                                                                                </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td>
                                                                                    <table border="0" align="center" cellpadding="0" cellspacing="0">


                                                                                        <tr>
                                                                                            <td class="fieldLabel" width="200px" align="right">Employee Name : </td>                           
                                                                                            <td>

                                                                                                <s:select name="opsContactId1" id="opsContactId1" headerKey="-1" headerValue="-- Please Select --" list="empnamesList" cssClass="inputSelect"   />    

                                                                                            </td> 
                                                                                             <td class="fieldLabel" width="200px" align="right">Report Based On :<FONT color="red"  ><em>*</em></FONT></td>

                                                                                            <td><s:select headerKey="-1"  list="#@java.util.LinkedHashMap@{'Aadhar no':'Aadhar no','Bank Account no':'Bank Account no','SSN':'PAN'}" name="docTypeId" id="docTypeId" cssClass="inputSelect" value="%{docTypeId}" /></td>
                                                                                            
                                                                                                
                                                                                            
                                                                                           
                                                                             </tr>
                                                                                                                                          <tr>
                                                                                            <td colspan="2"><br></td>
                                                                                            <td align="right">
                                                                                                <input type="button" value="Search" class="buttonBg" onclick="getPFPortalReport();"/>
                                                                                            </td>
                                                                                            <td align="right">
                                                                                                <input type="button" value="Generate" class="buttonBg" onclick="getPFPortalXLReport();"/>
                                                                                            </td>

                                                                                        </tr>
                                                                                                                                  </table>
                                                                                </td>
                                                                            </tr>

                                                                            <%-- table grid --%>
                                                                            <tr>
                                                                                <td>
                                                                                    <br>
                                                                                    <table align="center" cellpadding="2" border="0" cellspacing="1" width="50%" >

                                                                                        <tr>
                                                                                            <td height="20px" align="center" colspan="9">
                                                                                                <div id="PFPortal" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                            </td>
                                                                                        </tr>


                                                                                        <tr>
                                                                                            <td ><br>
                                                                                                <div id="availablePFReport" style="display: block">
                                                                                                    <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                                                    <table id="tblPFPortalReport" align='center' i cellpadding='1' cellspacing='1' border='0' class="gridTable" width='700'>
                                                                                                        <COLGROUP ALIGN="left" >
                                                                                                            <COL width="5%">
                                                                                                            <COL width="15%">
                                                                                                            <COL width="10%">
                                                                                                            <COL width="10%">
                                                                                                            <COL width="10%">
                                                                                                            <COL width="50%">
                                                                                                    </table> <br>
                                                                                                    <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                                                                </div>
                                                                                            </td>
                                                                                        </tr>
                                                                                    </table>    
                                                                                </td>
                                                                            </tr>
                                                                            <%-- table grid  end--%>
                                                                        </table>
                                                                    </s:form>
                                                                </td>
                                                            </tr>
                                                        </table>

                                                    </div>
                                                </td>
                                            </tr>

                                            </s:if>
                                            <%-- PF Portal Generation End --%> 
                                            <%-- Project Details by Customer Start  --%>
                                          <%--   <s:if test="%{#session.livingCountryList == 'India' || #session.livingCountryList == 'USA' || isAdminFlag=='YES'}">  --%>
                                               
                                            <tr>
                                                <td class="homePortlet" valign="top">
                                                    <div class="portletTitleBar">
                                                        <div class="portletTitleLeft">Project Details By Customer</div>
                                                        <div class="portletIcons">
                                                            <a href="javascript:animatedcollapse.hide('projectDetailsByCustomer')" title="Minimize">
                                                                <img src="../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                            <a href="javascript:animatedcollapse.show('projectDetailsByCustomer')" title="Maximize">
                                                                <img src="../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                            </a>
                                                        </div>
                                                        <div class="clear"></div>
                                                    </div>
                                                    <div id="projectDetailsByCustomer" style="background-color:#ffffff">
                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                            <tr>
                                                                <td width="40%" valign="top" align="center">
                                                                    <s:form theme="simple" name="frmAddGreenSheet" id="frmAddGreenSheet">  

                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                                            <tr align="right">
                                                                                <td class="headerText" colspan="9">
                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">                                                        
                                                                                </td>
                                                                            </tr>

                                                                            <tr>
                                                                                <td>
                                                                                    <%
                                                                                        if (session.getAttribute("resultMessageForUtility") != null) {
                                                                                            out.println(session.getAttribute("resultMessageForUtility"));
                                                                                            session.removeAttribute("resultMessageForUtility");
                                                                                        }

                                                                                    %>
                                                                                </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td>
                                                                                    <table border="0" align="center" cellpadding="0" cellspacing="0">
                                                                                      
                                                                                         <tr>
                                                                                            <td class="fieldLabel">Customer Name :</td>
                                                                                             <td>

                                                                                             <s:textfield name="accountName" id="accountName" value="" autocomplete="off" headerKey="-1" cssClass="inputTextBlue" onkeyup="getAccountNames();"/><label id="rejectedId"></label><div class="fieldLabelLeft" id="validationMessage"></div>

                                                                                                <div style="display: none; position: absolute; overflow:auto; z-index: 500;" id="menu-popup">
                                                                                                    <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" ></table>
                                                                                                </div>
                                                                                                <s:hidden name="accountId" id="accountId" value="-1"/>
                                                                                              
                                                                                            </td>
                                                                                            <td class="fieldLabel">Projects: </td>

                                                                                            <td>
                                                                                                   <s:select list="myProjects" name="projectId" id="projectId" headerKey="-1" headerValue="--Select Project--" cssClass="inputSelectLarge" disabled="false" onchange="getProjectStatus();"/> 
                                                                                            </td>
                                                                                            <td rowspan="3">&nbsp;&nbsp;&nbsp;<span id="projectStatusSpan"></span></td>
                                                                                             </tr>
                                                                                             
                                                                                        <tr>
                                                                                             <td  class="fieldLabel">Status:</td>
                                                                                            <td><s:select list="{'All','Active','InActive'}" id="currStatus" name="currStatus"  cssClass="inputSelect"/></td>   
                                                                                                 <td class="fieldLabel" align="right">Country :</td>

                                                                                            <td><s:select label="Select Country" name="resourceCountry" id="resourceCountry" headerKey="-1" headerValue="-- Please Select --" list="countryList" cssClass="inputSelectLarge"/></td>  
                                                                                            <td></td>

                                                                                        </tr>
                                                                                        <tr>
                                                                                        <td colspan="3"><br></td>
                                                                                             <td align="right">
                                                                                                <input type="button" value="Search" class="buttonBg" onclick="getProjectDetailsByCustomer()"/>
                                                                                            </td> 
                                                                                            <td></td>
                                                                                        </tr>
                                                                                        <tr>

                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td class="fieldLabel" >Total&nbsp;Records:</td>
                                                                                            <td class="userInfoLeft" >
                                                                                            <span id="totalCount"></span>
                                                                                                
                                                                                                
                                                                                            </td>   
                                                                                         
                                                                                        </tr>
                                                                                    </table>
                                                                                </td>
                                                                            </tr>

                                                                            <%-- table grid --%>
                                                                            <tr>
                                                                                <td>
                                                                                    <br>
                                                                                    <table align="center" cellpadding="2" border="0" cellspacing="1" width="50%" >

                                                                                        <tr>
                                                                                            <td height="20px" align="center" colspan="9">
                                                                                                <div id="employeeReport" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                            </td>
                                                                                        </tr>


                                                                                        <tr>
                                                                                            <td ><br>
                                                                                                <div id="employeeReport" style="display: block">
                                         
                                                                                                    <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                                                    <table id="tblEmpBasedOnCustomerReport" align='center' i cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750'>
                                                                                                        <COLGROUP ALIGN="left" >
                                                                                                            <COL width="5%">
                                                                                                            <COL width="15%">
                                                                                                            <COL width="10%">
                                                                                                            <COL width="15%">
                                                                                                            <COL width="10%">
                                                                                                            <COL width="15%">
                                                                                                    </table> <br>
                                                                                                    <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                                                                </div>
                                                                                            </td>
                                                                                        </tr>
                                                                                    </table>    
                                                                                </td>
                                                                            </tr>
                                                                            <%-- table grid  end--%>
                                                                        </table>
                                                                    </s:form>
                                                                </td>
                                                            </tr>
                                                        </table>

                                                    </div>
                                                </td>
                                            </tr>
                                            
                                 <%--    </s:if>  --%>
                                    
                                               <%-- Project Details by Customer End  --%>
<%-- onboarded people Start--%>
                                        <tr>
                                                <td class="homePortlet" valign="top">
                                                    <div class="portletTitleBar">
                                                        <div class="portletTitleLeft">Onboarding/Exit Report</div>
                                                        <div class="portletIcons">
                                                            <a href="javascript:animatedcollapse.hide('OnboardReports')" title="Minimize">
                                                                <img src="../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                            <a href="javascript:animatedcollapse.show('OnboardReports')" title="Maximize">
                                                                <img src="../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                            </a>
                                                        </div>
                                                        <div class="clear"></div>
                                                    </div>
                                                    <div id="OnboardReports" style="background-color:#ffffff">
                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                            <tr>
                                                                <td width="40%" valign="top" align="center">
                                                                    <s:form theme="simple" name="onboardReports" id="onboardReports">  

                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                                            <tr align="right">
                                                                                <td class="headerText" colspan="9">
                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">                                                        
                                                                                </td>
                                                                            </tr>

                                                                            <tr>
                                                                                <td>
                                                                                    <%
                                                                                        if (session.getAttribute("resultMessageForProjectSheet") != null) {
                                                                                            out.println(session.getAttribute("resultMessageForProjectSheet"));
                                                                                            session.removeAttribute("resultMessageForProjectSheet");
                                                                                        }

                                                                                    %>
                                                                                </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td>
                                                                                    <table border="0" align="center" cellpadding="0" cellspacing="0" width="800;">

                                                                                            <tr>
                                                                                            <td class="fieldLabel">Start&nbsp;Date&nbsp;(mm/dd/yyyy)&nbsp;:<FONT color="red"  ><em>*</em></FONT></td>
                                                                                            <td><s:textfield name="startDateOnboard" id="startDateOnboard" cssClass="inputTextBlueSmall"  onchange="checkDates(this);"/><a href="javascript:cal9.popup();">
                                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                                            </td>

                                                                                            <td class="fieldLabel">End&nbsp;Date&nbsp;(mm/dd/yyyy)&nbsp;:<FONT color="red"  ><em>*</em></FONT></td>
                                                                                            <td>  <s:textfield name="endDateOnboard" id="endDateOnboard" cssClass="inputTextBlueSmall"  onchange="checkDates(this);"/><a href="javascript:ca20.popup();">
                                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                                            </td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                         <s:if test="%{isAdminFlag=='YES'}">
                                                                                         <td class="fieldLabel" >Country :</td>

                                                                                            <td colspan=""><s:select name="country3" id="country3" headerKey="-1" headerValue="-- Please Select --" list="countryList" cssClass="inputSelect"/></td>                                                                                                                                                                                                                   
                                                                                    </s:if><s:else>
                                                                                        <s:hidden name="country3" id="country3" value="-1"/><td></td>
                                                                                    </s:else>
                                                                                            
                                                                                        <td class="fieldLabel" >Report Type :<FONT color="red"  ><em>* </em></FONT></td>
                                                                                        <td><s:select cssClass="inputSelect" list="#@java.util.LinkedHashMap@{'1':'Onboard Employees','2':'Exit Employees'}" name="flog1" id="flog1" headerValue="--Please Select--" headerKey="-1" /></td>
                                                                                       
                                                                                           
                                                                                       
                                                                                          
                                                                                    </tr>
                                                                                    <tr>
                                                                                          <td  class="fieldLabel" width="200px" align="right">Department :</td>
                                                                   <td><s:select
                                                  name="departmentId3" 
                                                  id="departmentId3"
                                                  headerKey="-1"
                                                  headerValue="--Please Select--"
                                              list="departmentIdList" cssClass="inputSelect"/></td>
                                                                                         <td align="right">
                                                                                                <input type="button" value="Search" class="buttonBg" onclick="getOnBoardReport()"/>
                                                                                            </td>
                                                                                        
                                                                                    </tr>
                                                                                       
                                                                                    </table>
                                                                                </td>
                                                                            </tr>
                                                                            <%-- table grid  start--%>
                                                                             <tr>
                                                                                <td>
                                                                                    <br>
                                                                                    <table align="center" cellpadding="2" border="0" cellspacing="1" width="50%" >

                                                                                        <tr>
                                                                                            <td height="20px" align="center" colspan="9">
                                                                                                <div id="onBoardReport" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                            </td>

                                                                                        </tr>


                                                                                        <tr>
                                                                                            <td ><br>
                                                                                                <div id="onBoardReport" style="display: block">
                                                                                                    <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                                                    <table id="tblOnboardData" align='center' i cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750'>
                                                                                                        <COLGROUP ALIGN="left" >
                                                                                                            <COL width="5%">
                                                                                                            <COL width="10%">
                                                                                                            <COL width="10%">
                                                                                                            <COL width="10%">
                                                                                                            <COL width="10%">
                                                                                                           
                                                                                                    </table> <br>
                                                                                                    <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                                                                </div>
                                                                                            </td>
                                                                                        </tr>
                                                                                    </table>    
                                                                                </td>
                                                                            </tr>
                                                                            <%-- table grid  end--%>
                                                                           </table>
                                                                    </s:form>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                        <script type="text/JavaScript">
                                                            var cal9 = new CalendarTime(document.forms['onboardReports'].elements['startDateOnboard']);
                                                            cal9.year_scroll = true;
                                                            cal9.time_comp = false;
                                                            var ca20 = new CalendarTime(document.forms['onboardReports'].elements['endDateOnboard']);
                                                            ca20.year_scroll = true;
                                                            ca20.time_comp = false;
                                                        </script>
                                                    </div>
                                                </td>
                                            </tr>
											

                                            <%-- onboarded people End--%>
                                          <%-- missing informationList Start--%>

                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td class="homePortlet" valign="top">
                                                                                                                                                                                                        <div class="portletTitleBar">
                                                                                                                                                                                                        <div class="portletTitleLeft">Resource Missing Information</div>
                                                                                                                                                                                                        <div class="portletIcons">
                                                                                                                                                                                                        <a href="javascript:animatedcollapse.hide('MissingInfoReport')" title="Minimize">
                                                                                                                                                                                                        <img src="../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                                                                                                                                                                        <a href="javascript:animatedcollapse.show('MissingInfoReport')" title="Maximize">
                                                                                                                                                                                                        <img src="../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                                                                                                                                                                        </a>
                                                                                                                                                                                                        </div>
                                                                                                                                                                                                        <div class="clear"></div>
                                                                                                                                                                                                        </div>
                                                                                                                                                                                                        <div id="MissingInfoReport" style="background-color:#ffffff">
                                                                                                                                                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td width="40%" valign="top" align="center">
                                                                                                                                                                                                        <s:form theme="simple" name="frmMissingInfoReport" id="missingInfoReport">  

                                                                                                                                                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                                                                                                                                                                        <tr align="right">
                                                                                                                                                                                                        <td class="headerText" colspan="9">
                                                                                                                                                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">                                                        
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>

                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td>
                                                                                                                                                                                                        <%
                                                                                                                                                                                                        if (session.getAttribute("resultMessageForMissingInfoReport") != null) {
                                                                                                                                                                                                        out.println(session.getAttribute("resultMessageForMissingInfoReport"));
                                                                                                                                                                                                        session.removeAttribute("resultMessageForMissingInfoReport");
                                                                                                                                                                                                        }

                                                                                                                                                                                                        %>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td>
                                                                                                                                                                                                        <table border="0" align="center" cellpadding="0" cellspacing="0" width="800;">
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td class="fieldLabel" width="200px" align="right">Live in :</td> <td>
                                                                                                                                                                                                        <%
                                                                                                                                                                                                        Map roleMap = (Map) session.getAttribute("myRoles");
                                                                                                                                                                                                        if (roleMap.containsValue("Admin")) {
                                                                                                                                                                                                        %>




                                                                                                                                                                                                        <s:select label="Select Country" 
                                                                                                                                                                                                        name="country" id="countryForMissingData" headerKey=""            
                                                                                                                                                                                                        headerValue="-- Please Select --"
                                                                                                                                                                                                        list="countryList" cssClass="inputSelect" value="" onchange="getLocationsByCountry(this);"/>
                                                                                                                                                                                                        <%} else {%>
                                                                                                                                                                                                        <s:select label="Select Country" 
                                                                                                                                                                                                        name="country" id="" headerKey=""            
                                                                                                                                                                                                        headerValue="-- Please Select --"
                                                                                                                                                                                                        list="countryList" cssClass="inputSelect" value="#session.livingCountryList" onchange="getLocationsByCountry(this);" disabled="true"/>
                                                                                                                                                                                                        <input type="hidden"  id="countryForMissingData" value="<%=session.getAttribute("livingCountryList")%>"/>
                                                                                                                                                                                                        <%}%>

                                                                                                                                                                                                        </td>




                                                                                                                                                                                                        <td class="fieldLabel" align="left">Location:</td>
                                                                                                                                                                                                        <td>

                                                                                                                                                                                                        <s:select name="location" id="location" 
                                                                                                                                                                                                        cssClass="inputSelect" headerKey="-1" headerValue="--Please Select--" 
                                                                                                                                                                                                        list="{'All'}" value=""></s:select> 
                                                                                                                                                                                                        </td>


                                                                                                                                                                                                        </tr> 

                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td class="fieldLabel" >Based On :<FONT color="red"  ><em>* </em></FONT></td><td><s:select cssClass="inputSelect" list="#@java.util.LinkedHashMap@{'1':'Bank Name','2':'Bank Account No','3':'Name As Per Account','4':'IFSC Code','5':'Aadhar No','6':'Name As Per Aadhar','7':'UAN No','8':'PF No','9':'Is International worker','10':'Reporting Person','11':'Location','12':'profile Picture','13':'Mobile No','14':'PAN No','15':'WorkPhoneNo','16':'NameAsPerPan','17':'AlterPhoneNo','18':'Title'}" name="missingField" id="missingField" headerValue="--Please Select--" headerKey="-1" value="%{missingField}"/></td>
                                                                                                                                                                                                        <td class="fieldLabel" width="200px" align="right">Operation Contact :</td>                           
                                                                                                                                                                                                        <td>

                                                                                                                                                                                                        <s:select name="opsContactId" id="opsContactIdRMD" headerKey="-1" headerValue="-- Please Select --" list="opsContactIdMap" cssClass="inputSelect" value="%{opsContactId}"  />    

                                                                                                                                                                                                        </td></tr>
                                                                                                                                                                                                        <tr><td></td><td></td>
                                                                                                                                                                                                        <td></td> <td width="200px"  align="left">
                                                                                                                                                                                                        <input type="button" value="Search" class="buttonBg" onclick="getMissingFileldsReport();"/>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>

                                                                                                                                                                                                        </table>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        <tr id="availableTr" style="display:none;"><td  class="fieldLabel" colspan="4">

                                                                                                                                                                                                        <table id="tblEmpMissingData" align='center' cellpadding='1' cellspacing='1' border='0'  width='100%' style="width: 812px;">



                                                                                                                                                                                                        </table>  

                                                                                                                                                                                                        <div id="resultData"></div>

                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        <%-- table grid  start--%>
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td>
                                                                                                                                                                                                        <br>
                                                                                                                                                                                                        <table align="center" cellpadding="2" border="0" cellspacing="1" width="50%" >

                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td height="20px" align="center" colspan="9">
                                                                                                                                                                                                        <div id="missingFileldReport" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                                                                                                                                        </td>

                                                                                                                                                                                                        </tr>


                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td ><br>
                                                                                                                                                                                                        <div id="missingFileldReport" style="display: block">

                                                                                                                                                                                                        <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                                                                                                                                                        <table id="tblMissingData" align='center' i cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750'>
                                                                                                                                                                                                        <COLGROUP ALIGN="left" >
                                                                                                                                                                                                        <COL width="5%">
                                                                                                                                                                                                        <COL width="30%">
                                                                                                                                                                                                        <COL width="35%">
                                                                                                                                                                                                        <COL width="25%">
                                                                                                                                                                                                        <label style="top: 10%; position: relative;display: none;" id="recordDisplay"> Display <select id="paginationOption" class="disPlayRecordsCss" onchange="pagerOption()" style="width: auto">

                                                                                                                                                                                                        <option>10</option>
                                                                                                                                                                                                        <option>15</option>
                                                                                                                                                                                                        <option>20</option>
                                                                                                                                                                                                        </select> Rows</label>

                                                                                                                                                                                                        </table> <br>

                                                                                                                                                                                                        <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                                                                                                                                                                        </div>

                                                                                                                                                                                                        </td>  
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        </table>    
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        <%-- table grid  end--%>
                                                                                                                                                                                                        </table>
                                                                                                                                                                                                        </s:form>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        </table>

                                                                                                                                                                                                        </div>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        <%-- missing informationList End--%>

                                            
                                        </table>

                                        <%--     </sx:div>
                                </sx:tabbedpanel> --%>
                                        <!--//END TABBED PANNEL : --> 
                                    </div>
                                </div>
                                <script type="text/javascript">

                                    var countries=new ddtabcontent("reportsTab")
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
                                                                                                                                                                                                        var recordPage=10;
                                                                                                                                                                                                        function pagerOption(){

                                                                                                                                                                                                        var paginationSize = document.getElementById("paginationOption").value;
                                                                                                                                                                                                        if(isNaN(paginationSize))
                                                                                                                                                                                                        {
                       
                                                                                                                                                                                                        }
                                                                                                                                                                                                        recordPage=paginationSize;
                                                                                                                                                                                                        // alert(recordPage)
                                                                                                                                                                                                        $('#tblMissingData').tablePaginate({navigateType:'navigator'},recordPage);

                                                                                                                                                                                                        };
                                                                                                                                                                                                        $('#tblMissingData').tablePaginate({navigateType:'navigator'},recordPage);
                                                                                                                                                                                                        </script>
                <script type="text/javascript">
		$(window).load(function(){
	getLocationsByCountryOnload();
	hideSelect();
	javascript:animatedcollapse.show('SalesClosuresExcelReportDiv');
	getdatesforProject();
	defaultDates();
	loadAvailabilityList();
	getPracticeDataV2();
	init();
		});
		</script>                                                                                                                                                                                        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/pagination.js"/>"></script>
    </body>

</html>
