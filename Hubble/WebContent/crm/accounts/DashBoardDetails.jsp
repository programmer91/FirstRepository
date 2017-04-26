
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%-- <%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@page import="java.util.Map"%>
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
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/oppdashboardAjaxUtil.js?version=3.0"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/GreensheetListSearch.js?version=2.1"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/GreensheetListResultSearch.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EnableEnter.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ReusableContainer.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/employee/DateValidator.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmpStandardClientValidations.js"/>"></script>
        <script type="text/javascript" src="https://www.google.com/jsapi"></script> 
        <s:include value="/includes/template/headerScript.html"/>
        <script language="JavaScript">
            google.load("visualization", "1", {packages:["corechart"]});   
            animatedcollapse.addDiv('accountsummrepDiv', 'fade=1;speed=400;persist=1;group=app');
            animatedcollapse.addDiv('activitiesDiv', 'fade=1;persist=1;group=app');
            animatedcollapse.addDiv('accountDiv', 'fade=1;persist=1;group=app');
            animatedcollapse.addDiv('opportDiv', 'fade=1;persist=1;group=app');
            animatedcollapse.addDiv('greenSheetDiv', 'fade=1;persist=1;group=app');
            animatedcollapse.addDiv('accountListByPriorityDiv', 'fade=1;persist=1;group=app');
            animatedcollapse.addDiv('activitysummaryGraph', 'fade=1;persist=1;group=app');
                
            animatedcollapse.addDiv('accountRenewalDiv', 'fade=1;persist=1;group=app');
            animatedcollapse.addDiv('accountRenewalStateDiv', 'fade=1;persist=1;group=app');
            animatedcollapse.addDiv('requirementDiv', 'fade=1;persist=1;group=app');
            animatedcollapse.addDiv('monthlyReportDiv', 'fade=1;persist=1;group=app');
            // animatedcollapse.addDiv('accountListByPracticeDiv', 'fade=1;persist=1;group=app');

            //animatedcollapse.addDiv('mirageDiv', 'fade=1;speed=400;persist=1;group=app');
            //animatedcollapse.addDiv('hubbleDiv', 'fade=1;persist=1;group=app');
            animatedcollapse.init();
            function hideSelect(){
                //document.getElementById("priorityId").style.display = 'none';
                
            }
            
             
function enableCampaignName(res)
{
  //  alert(res);
   if(res=="Campaign")
       {         
            document.getElementById("campaignId").value="";
           document.getElementById("campaignId").disabled = false;                   
       } 
       else{
           document.getElementById("campaignId").value="";
           document.getElementById("campaignId").disabled = true;
           
       }
}
function disableCampaignName()
{
   // alert("test");
 
   // alert(document.getElementById("activityType").value)
    var activityType1=document.getElementById("activitytype1").value;
    //   alert(activityType1);
    if(activityType1=="Campaign"){
     document.getElementById("campaignId").disabled = false;
 }
 else{
     document.getElementById("campaignId").value="";
           document.getElementById("campaignId").disabled = true;
 }
 
}
        </script>
    </head>
    <!-- <body  class="bodyGeneral" onload="disableCampaignName();setDefaultOppDates(),defaultDates(),getaccountRenewalByTeamMember();hideSelect();" oncontextmenu="return false;"> -->
    <body  class="bodyGeneral" oncontextmenu="return false;">
<script type="text/JavaScript" src="<s:url value="/includes/javascripts/wz_tooltip_contactnames.js"/>"></script>
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
                                    <li><a href="#" rel="accountsListTab" class="selected"> DashBoard Details </a></li>

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
                                                        <div class="portletTitleLeft">Account Summary By Rep</div>
                                                        <div class="portletIcons">
                                                            <a href="javascript:animatedcollapse.hide('accountsummrepDiv')" title="Minimize">
                                                                <img src="../../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                            <a href="javascript:animatedcollapse.show('accountsummrepDiv')" title="Maximize">
                                                                <img src="../../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                            </a>                                                           
                                                        </div>
                                                        <div class="clear"></div>
                                                    </div>
                                                    <div id="accountsummrepDiv" style="background-color:#ffffff">
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

                                                                                            <td><s:textfield name="dashBoardStartDateRep" id="dashBoardStartDateRep" cssClass="inputTextBlue" onchange="checkDates(this);"/> <a href="javascript:calRep.popup();">
                                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                         width="20" height="20" border="0"></td>    

                                                                                                    <td class="fieldLabel">EndDate</td>
                                                                                                    <td><s:textfield name="dashBoardEndDateRep" id="dashBoardEndDateRep" cssClass="inputTextBlue" onchange="checkDates(this);"/><a href="javascript:calRep1.popup();">
                                                                                                            <img  src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                                  width="20" height="20" border="0">  </td>




                                                                                                            <s:hidden id="isTeamLead" name="isTeamLead" value="%{#session.isUserTeamLead}"/>
                                                                                                            <s:hidden id="isUserManager" name="isUserManager" value="%{#session.isUserManager}"/>



                                                                                                            </tr>
                                                                                                            <tr>
                                                                                                                <td class="fieldLabel">Team Member: </td>

                                                                                                            <td width="25%">
                                                                                                                <s:select list="myTeamMembersForRep" name="teamMemberIdRep" id="teamMemberIdRep" onchange="hidePriority();" headerKey="-1" headerValue="All" cssClass="inputSelectLarge" disabled="false"/>
                                                                                                            </td>
                                                                                                                
                                                                                                                
                                                                                                                <td class="fieldLabel">Practice&nbsp;:&nbsp;</td>
                                                                                                                <td><%--<s:select id="practiceName" name="practiceName" list="practiceList" cssClass="inputSelect" value="%{practiceName}"/>--%>
                                                                                                                <s:select id="practiceName" name="practiceName" list="{'All','Field','Inside','Practice'}" cssClass="inputSelect" value="%{practiceName}"/>
                                                                                                                </td>
                                                                                                        </tr><tr><td colspan="3"></td>
                                                                                                                <td> <br><input type="button" value="Search" id="RepSearchButton" class="buttonBg" onclick="getAccountsByRep();"/></td>
                                                                                                            </tr>
                                                                                                            </table>

                                                                                                    </td>
                                                                                        </tr>

                                                                                        <tr>
                                                                                            <td height="20px" align="center" colspan="9">
                                                                                                <div id="loadActMessage" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                            </td>
                                                                                        </tr>

                                                                                        <tr>
                                                                                            <td>
                                                                                                <br>
                                                                                                <table align="center" cellpadding="2" border="0" cellspacing="1" width="50%" >

                                                                                                    <tr>
                                                                                                        <td >
                                                                                                            <br>
                                                                                                            <div id="accountsByRepList" style="display: block">
                                                                                                                <lable id="noteLableForRep" style="display:none;color:red;font-size: 80%; font-style: italic;">Note :The details are order by Reports to ascending and Employee Name ascending.</lable>
                                                                                                                <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                                                                <table id="tblAccountSummRep" align='center' cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750'>
                                                                                                                    <COLGROUP ALIGN="left" >
                                                                                                                        <COL width="5%">
                                                                                                                        <COL width="15%">
                                                                                                                        <COL width="10%">
                                                                                                                        <COL width="10%">
                                                                                                                        <COL width="13%">
                                                                                                                        <COL width="10%">
                                                                                                                        <COL width="10%">
                                                                                                                        <s:if test="#session.isUserTeamLead == 1 || #session.isUserManager == 1">
                                                                                                                            <COL width="10%">
                                                                                                                        </s:if>
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
                                                                        var calRep = new CalendarTime(document.forms['dashboardRep'].elements['dashBoardStartDateRep']);
                                                                        calRep.year_scroll = true;
                                                                        calRep.time_comp = false;
                                                                        calRep1 = new CalendarTime(document.forms['dashboardRep'].elements['dashBoardEndDateRep']);
                                                                        calRep1.year_scroll = true;
                                                                        calRep1.time_comp = false;
                                                                    </script>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td class="homePortlet" valign="top">
                                                                    <div class="portletTitleBar">
                                                                        <div class="portletTitleLeft">Accounts Summary By Priority</div>
                                                                        <div class="portletIcons">
                                                                            <a href="javascript:animatedcollapse.hide('accountListByPriorityDiv')" title="Minimize">
                                                                                <img src="../../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                                            <a href="javascript:animatedcollapse.show('accountListByPriorityDiv')" title="Maximize">
                                                                                <img src="../../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                                            </a>
                                                                        </div>
                                                                        <div class="clear"></div>
                                                                    </div>
                                                                    <div id="accountListByPriorityDiv" style="background-color:#ffffff">
                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                                            <tr>
                                                                                <td width="40%" valign="top" align="center">
                                                                                    <s:form action="" theme="simple" name="loadAccountListByPriority">  

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
                                                                                                            <td class="fieldLabel">Team Member: </td>

                                                                                                            <td width="25%">
                                                                                                                <s:select list="myTeamMembers" name="teamMemberId" id="teamMemberId1" onchange="hidePriority();" headerKey="-1" headerValue="All" cssClass="inputSelectLarge" value="%{teamMemberId}" disabled="false"/>
                                                                                                            </td>

                                                                                                            <td class="fieldLabel">Priority:</td>

                                                                                                            <td>
                                                                                                                <s:select id="teamNameList1"  list="{'Main','B2B','BPM','E-Commerce','SAP','QA'}" cssClass="inputSelect"/>
                                                                                                                &nbsp;&nbsp;&nbsp;<input  type="button" id="prioritySearchbutton" value="Search"  class="buttonBg"  onclick="getAccountsByPriorities();"/>
                                                                                                            </td>


                                                                                                            <s:hidden  id="isCeo" value="#session.ceo" />
                                                                                                            <%-- <td style="padding-left:0px;"><input type="button" value="Search"  class="buttonBg"  onclick="getAccountsByPriority();"/></td> --%>
                                                                                                            <td></td>

                                                                                                        </tr>
                                                                                                       
                                                                                                       <%-- <tr>
                                                                                                <td class="fieldLabel">StartDate:</td>
                                                                                            <td><s:textfield name="dashBoardAccountsStartDatePriority" id="dashBoardAccountsStartDatePriority" cssClass="inputTextBlue" onchange="checkDates(this);" /> <a href="javascript:calRepp.popup();">
                                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                         width="20" height="20" border="0">
                                                                                                </a></td>    

                                                                                                    <td class="fieldLabel">EndDate:</td>
                                                                                                    <td><s:textfield name="dashBoardAccountsEndDatePriority" id="dashBoardAccountsEndDatePriority" cssClass="inputTextBlue" onchange="checkDates(this);"/><a href="javascript:calRepp1.popup();">
                                                                                                            <img  src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                                  width="20" height="20" border="0"> </a> &nbsp;&nbsp;&nbsp;<input  type="button" id="prioritySearchbutton" value="Search"  class="buttonBg"  onclick="getAccountsByPriorities();"/></td> 
                                                                                                        </tr> --%>

                                                                                                    </table>
                                                                                                </td>
                                                                                            </tr>

                                                                                            <tr>
                                                                                                <td height="20px" align="center" colspan="9">
                                                                                                    <div id="loadActMessagePriority1" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                                </td>
                                                                                            </tr>

 <%-- <script type="text/JavaScript">
                                                                        var calRepp = new CalendarTime(document.forms['loadAccountListByPriority'].elements['dashBoardAccountsStartDatePriority']);
                                                                        calRepp.year_scroll = true;
                                                                        calRepp.time_comp = false;
                                                                        calRepp1 = new CalendarTime(document.forms['loadAccountListByPriority'].elements['dashBoardAccountsEndDatePriority']);
                                                                        calRepp1.year_scroll = true;
                                                                        calRepp1.time_comp = false;
                                                                    </script> --%>

                                                                                            <!-- end of message row -->

                                                                                            <tr>
                                                                                                <td>
                                                                                                    <br>
                                                                                                    <table align="center" cellpadding="2" border="0" cellspacing="1" width="50%" >

                                                                                                        <tr>
                                                                                                            <td >
                                                                                                                <br>
                                                                                                                <div id="AccountSummaryByPriorityList" style="display: block">
                                                                                                                    <lable id="noteLableForproirity" style="display: none;color:red;font-size: 80%; font-style: italic;">Note :The details are order by  Priority ascending.</lable>
                                                                                                                    <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                                                                    <table id="tblUpdateForAccountsListByPriority1" align='center' cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750'>
                                                                                                                        <COLGROUP ALIGN="left" >
                                                                                                                            <COL width="5%">
                                                                                                                            <COL width="30%">
                                                                                                                            <COL width="10%">
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

                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- new div for account list by priority end -->








                                                            <%--  <tr>
                                                                  <td class="homePortlet" valign="top">
                                                                      <div class="portletTitleBar">
                                                                          <div class="portletTitleLeft">Activities Summary</div>
                                                                          <div class="portletIcons">
                                                                              <a href="javascript:animatedcollapse.hide('activitiesDiv')" title="Minimize">
                                                                                  <img src="../../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                                              <a href="javascript:animatedcollapse.show('activitiesDiv')" title="Maximize">
                                                                                  <img src="../../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                                              </a>                                                           
                                                                          </div>
                                                                          <div class="clear"></div>
                                                                      </div>
                                                                      <div id="activitiesDiv" style="background-color:#ffffff">
                                                                          <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                                              <tr>
                                                                                  <td width="40%" valign="top" align="center">
                                                                                      <s:form action="dashBoard" theme="simple" name="dashboard">   

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

                                                                                                            <td><s:textfield name="dashBoardStartDate" id="dashBoardStartDate" cssClass="inputTextBlue" onchange="checkDates(this);"/> <a href="javascript:cal5.popup();">
                                                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                                         width="20" height="20" border="0"></td>    

                                                                                                                    <td class="fieldLabel">EndDate</td>
                                                                                                                    <td><s:textfield name="dashBoardEndDate" id="dashBoardEndDate" cssClass="inputTextBlue" onchange="checkDates(this);"/><a href="javascript:cal6.popup();">
                                                                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                                                 width="20" height="20" border="0">  </td>

                                                                                                                            <td colspan="4" align="center">
                                                                                                                                <input type="button" value="Search" class="buttonBg" onclick="getActivityList();"/>
                                                                                                                            </td> 
                                                                                                                            </tr>

                                                                                                                            </table>
                                                                                                                    </td>
                                                                                                        </tr>

                                                                                                        <tr>
                                                                                                            <td height="20px" align="center" colspan="9">
                                                                                                                <div id="loadActivityMessage" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                                            </td>
                                                                                                        </tr>

                                                                                                        <tr>
                                                                                                            <td>
                                                                                                                <br>
                                                                                                                <table align="center" cellpadding="2" border="0" cellspacing="1" width="50%" >
                                                                                                                    <tr>
                                                                                                                        <td class="fieldLabelLeft" width="150px">
                                                                                                                            Total Activities Found :
                                                                                                                        </td>
                                                                                                                        <td class="fieldLabelLeft" id="totalActivityRec">

                                                                                                                        </td>
                                                                                                                    </tr>

                                                                                                                    <tr>
                                                                                                                        <td colspan="2">
                                                                                                                            <br>
                                                                                                                            <div id="activityList" style="display: block">
                                                                                                                                <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                                                                                <table id="tblActUpdate" align='center' cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750'>
                                                                                                                                    <COLGROUP ALIGN="left" >
                                                                                                                                        <COL width="10%">
                                                                                                                                        <COL width="15%">
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
                                                                                        var cal5 = new CalendarTime(document.forms['dashboard'].elements['dashBoardStartDate']);
                                                                                        cal5.year_scroll = true;
                                                                                        cal5.time_comp = false;
                                                                                        cal6 = new CalendarTime(document.forms['dashboard'].elements['dashBoardEndDate']);
                                                                                        cal6.year_scroll = true;
                                                                                        cal6.time_comp = false;
                                                                                    </script>
                                                                                    </div>
                                                                                </td>
                                                                            </tr>--%>

                                                            <%--   <tr>
                                                                   <td class="homePortlet" valign="top">
                                                                       <div class="portletTitleBar">
                                                                           <div class="portletTitleLeft">Account Summary</div>
                                                                           <div class="portletIcons">
                                                                               <a href="javascript:animatedcollapse.hide('accountDiv')" title="Minimize">
                                                                                   <img src="../../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                                               <a href="javascript:animatedcollapse.show('accountDiv')" title="Maximize">
                                                                                   <img src="../../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                                               </a>
                                                                           </div>
                                                                           <div class="clear"></div>
                                                                       </div>
                                                                       <div id="accountDiv" style="background-color:#ffffff">

                                                                                        <table cellpadding="2" cellspacing="1" width="100%" class="gridTable">

                                                                                            <%
                                                                                                try {
                                                                                                    queryString = session.getAttribute(ApplicationConstants.QS_COUNT_DASHBOARDTERRITORY_LIST).toString();

                                                                                                    connection = ConnectionProvider.getInstance().getConnection();

                                                                                                    stmt = connection.createStatement();
                                                                                                    rs = stmt.executeQuery(queryString);
                                                                                                    //out.print(queryString);
                                                                                                    accountCounter = 0;

                                                                                            %>
                                                                                            <tr class="gridHeader">
                                                                                                <td width="30%" class="gridHeader" ALIGN="left">Territory1</td>
                                                                                                <td width="24%" class="gridHeader" ALIGN="left">Status</td>
                                                                                                <td width="26%" class="gridHeader" ALIGN="left">Region</td>
                                                                                                <td width="10%" class="gridHeader" align="left">Count</td>
                                                                                            </tr>
                                                                                            <% while (rs.next()) {
                                                                                                    int accCount = rs.getInt("count");%>    
                                                                                                    <% 
                                                                                          
if(rs.getString("teritory")!=null && !rs.getString("teritory").equals("-1") && !rs.getString("teritory").equals("")){%>
                                                                                            <tr class="gridRowEven">   
                                                                                                <td class="gridColumn" align="left"><%=rs.getString("teritory")%></td> <td><%=rs.getString("status")%></td> 
                                                                                                <td><%=rs.getString("Region")%></td><td><%=accCount%></td> 
                                                                                            </tr>
                                                                                            <%}%>
                                                                                            <% accountCounter = accountCounter + accCount;
                                                                                                }%>
                                                                                            <%
                                                                                                    connection.close();
                                                                                                    connection = null;
                                                                                                } catch (Exception e) {
                                                                                                    System.out.println("DashBoard Territory :-" + e);

                                                                                                } finally {
                                                                                                    if (connection != null) {
                                                                                                        try {
                                                                                                            connection.close();
                                                                                                            connection = null;
                                                                                                        } catch (Exception e) {
                                                                                                            e.printStackTrace();
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            %>
                                                                                            <tr class="gridPager">
                                                                                                <td align="left" class="gridFooter">Total&nbsp;<%=accountCounter%></td>
                                                                                                <td colspan="3" class="gridFooter"></td>
                                                                                            </tr>
                                                                                        </table>
                                                                                    </div>
                                                                                </td>
                                                                            </tr> --%>
                                                            <!--2nd td in First table -->





                                                            <%--                        </sx:div >
                                        <!--//END TAB : -->
                                        <sx:div id="OppDashBoard" label="Opportunities DashBoard" cssStyle="overflow:auto;">--%>


<tr>
                                                                <td class="homePortlet" valign="top">
                                                                    <div class="portletTitleBar">
                                                                        <div class="portletTitleLeft">Opportunities Summary</div>
                                                                        <div class="portletIcons">
                                                                            <a href="javascript:animatedcollapse.hide('opportDiv')" title="Minimize">
                                                                                <img src="../../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                                            <a href="javascript:animatedcollapse.show('opportDiv')" title="Maximize">
                                                                                <img src="../../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                                            </a>
                                                                        </div>
                                                                        <div class="clear"></div>
                                                                    </div>
                                                                    <div id="opportDiv" style="background-color:#ffffff">


                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                                            <tr>
                                                                                <td width="80%" valign="top" align="center">
                                                                                    <s:form action="" theme="simple" id="oppDashboard" name="oppDashboard" onsubmit="return compareDates(document.getElementById('dueStartDate').value,document.getElementById('dueEndDate').value);">   

                                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                                                           
                                                                                            <tr>
                                                                                                <td>
                                                                                                    <table border="0" align="center" cellpadding="0" cellspacing="0" >
                                                                                                        <tr>
                                                                                                            <td class="fieldLabel">Opportunity Type:</td>
                                                                                                            <td>
                                                                                                                <s:select name="type" id="type" cssClass="inputSelect" list="typeList" value="All" headerKey="All" headerValue="All"/>
                                                                                                            </td>
                                                                                                            <td class="fieldLabel">Opportunity Stage: </td>
                                                                                                            <td>
                                                                                                                <s:select name="stage" id="stage" cssClass="inputSelect" list="{'All','Active','30 Days','60 Days','90 Days','Future'}"  value="All"/>
                                                                                                            </td>

                                                                                                        </tr>
                                                                                                       
                                                                                                    <%--    <tr>
                                                                                                            <td class="fieldLabel">
                                                                                                                Specify Created Date Range:
                                                                                                            </td>
                                                                                                            <td colspan="3"  >
                                                                                                                <input id="dateClearbtn" type="button" class="buttonBg" value="clear Dates" name="dateClearbtn" onclick="clearDatesOppDash(this);" >
                                                                                                            </td>
                                                                                                        </tr>--%>
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
                                                                                                                                <td class="fieldLabel">Related To:</td>
                                                                                                                                <td>
                                                                                                                                    <s:select name="createdBy1" id="createdBy1" cssClass="inputSelectLarge" list="myTeamMembers"
                                                                                                                                              headerKey="-1" headerValue="All"/>
                                                                                                                                </td>
                                                                                                                                <td  class="fieldLabel">Opportunity State:</td>
                                                                                                                                
                                                                                                                                 <td> 
                                                                                                                                     <s:select name="state" id="state" cssClass="inputSelect" list="opportunityStateList" multiple="true" headerKey="All" headerValue="All"/>
                                                                                                                                    </td>
                                                                                                                                     <s:hidden name="state1" id="state1"/> 


                                                                                                                        </tr><tr>
                                                                                                                            <td class="fieldLabel" style="width: 10px;">Practice :</td>
                                                                                                                                  <td style="width: 156px;">
                                                                                                                                  <s:select list="practiceIdList" name="practice" id="practice" cssClass="inputSelect" value="All" headerKey="All" headerValue="All" />
                                                                                                                                 </td>
                                                                                                                               <td class="fieldLabel">Due Date</td>
                                                                                                                        <td><s:textfield id="dueDate" name="dueDate" cssClass="inputTextBlue" /> <a href="javascript:cal7.popup();">
                                                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                                         width="20" height="20" border="0"></a></td> 
                                                                                                                    </tr>
                                                                                                                     <tr> <td class="fieldLabel">SVI Only</td><td>
                                                                                                                             <s:checkbox id="sviNum" name="sviNum"/>
                                                                                                                         </td>
                                                                                                                        
                                                                                                                               <td  class="fieldLabel forRemove"> SVI Stage: </td>    
                                                                                                                               <td>
                                                                                                                            <s:select id="sviList" name="sviList" list="{'Identifying/Validating','Validated/Qualifing','Won/Implementing','Lost'}" cssClass="inputSelect" headerKey="" headerValue="--Please Select--"/>
                                                           
                                                                                                                         </td>
                                                                                                                    </tr>
                                                                                                                    <tr>
                                                                                                                    <td colspan="3"></td>
                                                                                                                         <td>
                                                                                                                             <br>
                                                                                                                                    <input type="button" id="opportunitySearchBUtton" class="buttonBg" onclick="getOpportunities()" value="Search"/>
                                                                                                                                </td>
                                                                                                                            </tr>


                                                                                                                            <tr>
                                                                                                                                <td height="20px" align="center" colspan="4" >
                                                                                                                                    <div id="loadOppMessage" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                                                                </td>
                                                                                                                            </tr>

                                                                                                                            <tr>
                                                                                                                                 <td class="fieldLabel" >
                                                                                                                                    Total&nbsp;Records&nbsp;found&nbsp:
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
                                                                                        cal7 = new CalendarTime(document.forms['oppDashboard'].elements['dueDate']);
                                                                                        cal7.year_scroll = true;
                                                                                        cal7.time_comp = false;
                                                                                    </script>
                                                                                </td>
                                                                            </tr>
                                                                            <!--2nd td in First table -->
                                                                        </table>
                                                                    </div>
                                                                </td>
                                                            </tr>
 <!--Requirment Summary -->
                                                            <tr>
                                                                <td class="homePortlet" valign="top">
                                                                    <div class="portletTitleBar">
                                                                        <div class="portletTitleLeft">Requirement Summary</div>
                                                                        <div class="portletIcons">
                                                                            <a href="javascript:animatedcollapse.hide('requirementDiv')" title="Minimize">
                                                                                <img src="../../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                                            <a href="javascript:animatedcollapse.show('requirementDiv')" title="Maximize">
                                                                                <img src="../../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                                            </a>
                                                                        </div>
                                                                        <div class="clear"></div>
                                                                    </div>
                                                                    <div id="requirementDiv" style="background-color:#ffffff">


                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                                            <tr>
                                                                                <td width="80%" valign="top" align="center">
                                                                                    <s:form action="" theme="simple" id="recDashboard" name="recDashboard" onsubmit="return compareDates(document.getElementById('dueStartDate').value,document.getElementById('dueEndDate').value);">   

                                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">

                                                                                            <tr>
                                                                                                <td>
                                                                                                    <table border="0" align="center" cellpadding="0" cellspacing="0" >
                                                                                                        <tr>          
                                                                                                            <td class="fieldLabel">Start Date:</td> <!--value="%{dateWithOutTime}" -->
                                                                                                            <td><s:textfield id="reqStartDate" name="reqStartDate" cssClass="inputTextBlue" value="%{dateWithOutTime}" onchange="checkDates(this);"/> <a href="javascript:cal5.popup();">
                                                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                                         width="20" height="20" border="0"></td>    
                                                                                                                    <td class="fieldLabel">End Date</td>
                                                                                                                    <td><s:textfield name="reqEndDate" id="reqEndDate" cssClass="inputTextBlue" value="%{dateWithOutTime}" onchange="checkDates(this);"/><a href="javascript:cal6.popup();">
                                                                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                                                 width="20" height="20" border="0">  </td>
                                                                                                                            </tr>
                                                                                                                            <tr>
                                                                                                                                <td class="fieldLabel">JobTitle:</td> <!--value="%{dateWithOutTime}" -->

                                                                                                                                <td><s:textfield name="jobTitle" id="jobTitle" cssClass="inputTextBlue" value=""/> </td> 
                                                                                                                                <td class="fieldLabel">Status Of Requirement :</td>
                                                                                                                                <td>   
                                                                                                                                  <s:select headerKey="All" headerValue="All" list="{'Forecast','Open','InProgress','Hold','Withdrawn','Won','Lost','Closed'}" name="reqstatus" id="reqstatus" cssClass="inputSelect" theme="simple" multiple="true" onchange="getRequirementSetatus();"/>
                                                                                                                                </td>


                                                                                                                            </tr>

                                                                                                                            <s:hidden id="reqstatushide" name="reqstatushide"/>
                                                                                                                            <tr>
                                                                                                                                <td class="fieldLabel">Practice :&nbsp;</td>
                                                                                                                                <td>
                                                                                                                                    <s:select headerKey="-1" headerValue="--Please Select--" list="{'SAP','J2EE','Integration','Portals','Commerce','B2B','QA','BI','Microsoft','Others'}" name="reqpracticeId" id="reqpracticeId" cssClass="inputSelect" value="%{currentRequirement.practiceId}"/>
                                                                                                                                </td>
                                                                                                                                <td class="fieldLabel">Related To:</td>
                                                                                                                                <td>
                                                                                                                                    <s:select name="createdByTeam" id="createdByTeam" cssClass="inputSelectLarge" list="myTeamMembers"
                                                                                                                                              headerKey="-1" headerValue="All"/>
                                                                                                                                </td>

                                                                                                                            </tr>
                                                                                                                            <tr><td colspan="3"></td>
                                                                                                                                <td> <br>
                                                                                                                                    <input type="button" id="requirementSearchBUtton" class="buttonBg" onclick="getRequirement()" value="Search"/>
                                                                                                                                </td>
                                                                                                                            </tr>


                                                                                                                            <tr>
                                                                                                                                <td height="20px" align="center" colspan="4" >
                                                                                                                                    <div id="loadreqMessage" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                                                                </td>
                                                                                                                            </tr>

                                                                                                                            <tr>
                                                                                                                                <td height="20px" >

                                                                                                                                </td>
                                                                                                                            </tr>
                                                                                                                            <tr>

                                                                                                                                <td colspan="4" >
                                                                                                                            <lable id="noteLableForReq" style="display:none;color:red;font-size: 80%; font-style: italic;">Note :The details are order by  Due date ascending.</lable>
                                                                                                                            <table id="tblrecUpdate" align="center"  
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
                                                                                        var cal5= new CalendarTime(document.forms['recDashboard'].elements['reqStartDate']);
                                                                                        cal5.year_scroll = true;
                                                                                        cal5.time_comp = false;
                                                                                        cal6 = new CalendarTime(document.forms['recDashboard'].elements['reqEndDate']);
                                                                                        cal6.year_scroll = true;
                                                                                        cal6.time_comp = false;
                                                                                    </script>
                                                                                </td>
                                                                            </tr>
                                                                            <!--2nd td in First table -->
                                                                        </table>
                                                                    </div>
                                                                </td>
                                                            </tr>

                                                            <!--requirement summary end -->

                                                            <tr>
                                                                <td class="homePortlet" valign="top">
                                                                    <div class="portletTitleBar">
                                                                        <div class="portletTitleLeft">GreenSheets Summary</div>
                                                                        <div class="portletIcons">
                                                                            <a href="javascript:animatedcollapse.hide('greenSheetDiv')" title="Minimize">
                                                                                <img src="../../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                                            <a href="javascript:animatedcollapse.show('greenSheetDiv')" title="Maximize">
                                                                                <img src="../../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                                            </a>
                                                                        </div>
                                                                        <div class="clear"></div>
                                                                    </div>
                                                                    <div id="greenSheetDiv" style="background-color:#ffffff">
                                                                        <s:form action="" theme="simple" name="greensheetDashBoard" >
                                                                            <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                                                <tr align="right">
                                                                                    <td class="headerText" colspan="9">
                                                                                        <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">                                                        
                                                                                    </td>
                                                                                </tr>

                                                                                <tr>
                                                                                    <td>
                                                                                        <table border="0" width="100%" cellpadding="1" cellspacing="0">
                                                                                            <tr>
                                                                                                <td class="fieldLabel">Start Date: </td>
                                                                                                <td><s:textfield name="greensheetStartDate" id="greensheetStartDate" cssClass="inputTextBlue" onchange="checkDates(this);" onkeydown="return enableEnter(event);"/>
                                                                                                    <a href="javascript:cal3.popup();">
                                                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                             width="20" height="20" border="0" ></a>
                                                                                                </td>  

                                                                                                <td class="fieldLabel">End Date: </td>
                                                                                                <td><s:textfield name="greensheetEndDate" id="greensheetEndDate" cssClass="inputTextBlue" onchange="checkDates(this);" onkeydown="return enableEnter(event);"/>
                                                                                                    <a href="javascript:cal4.popup();">
                                                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                             width="20" height="20" border="0" ></a>
                                                                                                </td>

                                                                                                <td class="fieldLabel">PO Type: </td>
                                                                                                <td>
                                                                                                    <s:select list="{'Services','Expenses','FixedBid','Software', 'Support','Others'}" name="poType" id="poType" 
                                                                                                              cssClass="inputSelect" onkeydown="return enableEnter(event);" headerKey="-1" headerValue="--Please Select--"/>
                                                                                                </td>

                                                                                            </tr>

                                                                                                <tr>
                                                                                                <td class="fieldLabel">PO Status :</td>
                                                                                                <td><s:select list="{'Open','Received','Cancelled','Closed'}" name="poStatus" id="poStatus" 
                                                                                                          headerKey="-1" headerValue="-- Please Select --" cssClass="inputSelect" /></td>

                                                                                                <td class="fieldLabel">Created By: </td>
                                                                                                <td>
                                                                                                    <s:select name="createdBy" id="createdBy" cssClass="inputSelectLarge" list="myTeamMembers"
                                                                                                              headerKey="-1" headerValue="-- Please Select --"
                                                                                                              onkeydown="return enableEnter(event);" onchange="titleTypeCheck1(this);"/></td>
                                                                                                
                                                                                                <td> <s:hidden name="titleType1" id="titleType1" cssClass="inputTextBlue" value=""/>
                                                                                                                    <div id="displayTitleType1"></div>
                                                                                                            </td>
                                                                                                  </tr>
                                                                                                  <tr>
                                                                                                  
                                                                                                <td class="fieldLabel">Country :</td>     
                                                                                                  
                                                                                                <td>
                                                                                                    <s:select 
                                                                                                        list="countryList" 
                                                                                                        name="country" 
                                                                                                        id="country" 
                                                                                                        cssClass="inputSelect"
                                                                                                        headerKey="-1"
                                                                                                        headerValue="--Please Select--"
                                                                                                        value="USA"
                                                                                                        theme="simple"/>
                                                                                                </td> 
                                                                                                 <td class="fieldLabel" colspan="1"></td> 
                                                                                                <td style="padding-left:15px;"><input id="greensheetSearchButton" type="button" value="Search" class="buttonBg" onclick="load();"/></td>        
                                                                                            </tr>

                                                                                            <%--
                                                                                            <tr>
                                                                                                <td style="padding-left:15px;"><input type="button" value="Search" class="buttonBg" onclick="load();"/></td>        
                                                                                            </tr> --%>

                                                                                            <tr>
                                                                                                <td height="20px" align="center" colspan="4" >
                                                                                                    <div id="loadGreenMessage" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                                </td>
                                                                                            </tr>

                                                                                            <tr>
                                                                                                <td class="fieldLabel" >
                                                                                                    Total Records:
                                                                                                </td>
                                                                                                <td class="userInfoLeft" id="totalGreenRec" >

                                                                                                </td>
                                                                                                <td class="fieldLabel" >
                                                                                                    Total Value: 
                                                                                                </td>
                                                                                                <td class="userInfoLeft" colspan="4" id="totalGreenSum" >

                                                                                                </td>
                                                                                            </tr>
                                                                                            


                                                                                        </table>    
                                                                                    </td>
                                                                                </tr>  



                                                                                <tr>
                                                                                    <td>

                                                                                        <br>
                                                                                        <div id="greensheetDashBoardList" style="display: block">
                                                                                            <lable id="noteLableForGreenSheet" style="display: none;color:red;font-size: 80%; font-style: italic;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Note :The details are order by Account Name,PO Status and PO End Date ascending.</lable>
                                                                                            <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                                            <table id="tblUpdate" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='90%' align="center">
                                                                                               <COLGROUP ALIGN="left" >
                                                                                                    <COL width="3%">
                                                                                                    <COL width="20%">
                                                                                                    <COL width="20%">
                                                                                                    <COL width="15%">
                                                                                                    <COL width="10%">
                                                                                                    <COL width="10%">
                                                                                                    <COL width="15%">
                                                                                                    <COL width="15%">
                                                                                                    <COL width="2%">
                                                                                            </table>
                                                                                            <br>
                                                                                            <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                                                        </div>
                                                                                    </td>
                                                                                </tr>

                                                                            </table>
                                                                        </s:form>
                                                                        <script type="text/JavaScript">
                                                                            var cal3 = new CalendarTime(document.forms['greensheetDashBoard'].elements['greensheetStartDate']);
                                                                            cal3.year_scroll = true;
                                                                            cal3.time_comp = false;
                                                                            var cal4 = new CalendarTime(document.forms['greensheetDashBoard'].elements['greensheetEndDate']);
                                                                            cal4.year_scroll = true;
                                                                            cal4.time_comp = false;
                                                                        </script>
                                                                    </div>
                                                                </td>
                                                            </tr> 

                                                            <!-- new div for account list by priority start -->
                                                            <%--  <tr>
                                                                  <td class="homePortlet" valign="top">
                                                                      <div class="portletTitleBar">
                                                                          <div class="portletTitleLeft">Accounts Summary By Practice </div>
                                                                          <div class="portletIcons">
                                                                              <a href="javascript:animatedcollapse.hide('accountListByPracticeDiv')" title="Minimize">
                                                                                  <img src="../../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                                              <a href="javascript:animatedcollapse.show('accountListByPracticeDiv')" title="Maximize">
                                                                                  <img src="../../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                                              </a>
                                                                          </div>
                                                                          <div class="clear"></div>
                                                                      </div>
                                                                      <div id="accountListByPracticeDiv" style="background-color:#ffffff">
                                                                          <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                                              <tr>
                                                                                  <td width="40%" valign="top" align="center">
                                                                                      <s:form action="" theme="simple" name="loadAccountListByPriority">  

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
                                                                                                                                            <td class="fieldLabel">Team Member: </td>

                                                                                                                                            <td width="25%">
                                                                                                                                                <s:select list="myTeamMembers" name="teamMemberId" id="teamMemberId" onchange="hidePriority();" headerKey="-1" headerValue="--Select TeamMember--" cssClass="inputLargeSelect" value="%{teamMemberId}" disabled="false"/>
                                                                                                                                            </td>
                                                                                                                                            <td class="fieldLabel">&nbsp;</td>
                                                                                                                                            <td width="25%">   
                                                                                                                                                <b class="hiddenLabel" id="teamName" ></b>
                                                                                                                                            </td>
                                                                                                                                            <s:hidden  id="isCeo" value="#session.ceo" />
                                                                                                                                           
                                                                                                                                            <td style="padding-left:0px;"><input id="normalUserButton" type="button" value="Search"  class="buttonBg"  onclick="getTeamName();"/></td>

                                                                                                                                        </tr>

                                                                                                                                        <tr id="priorityId">

                                                                                                                                            <td class="fieldLabel">Priority:</td>

                                                                                                                                            <td>
                                                                                                                                                <s:select id="teamNameList"   headerKey="-1" headerValue="Select Priority" list="{'B2B','BPM','E-Commerce','SAP','QA'}" cssClass="inputLargeSelect"/>
                                                                                                                                            </td>
                                                                                                                                        </tr> 

                                                                                                                                    </table>
                                                                                                                                </td>
                                                                                                                            </tr>

                                                                                                                            <tr>
                                                                                                                                <td height="20px" align="center" colspan="9">
                                                                                                                                    <div id="loadActMessagePriority" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                                                                </td>
                                                                                                                            </tr>



                                                                                                                            <!-- end of message row -->

                                                                                                                            <tr>
                                                                                                                                <td>
                                                                                                                                    <br>
                                                                                                                                    <table align="center" cellpadding="2" border="0" cellspacing="1" width="50%" >

                                                                                                                                        <tr>
                                                                                                                                            <td >
                                                                                                                                                <br>
                                                                                                                                                <div id="AccountSummaryByPriorityList" style="display: block">
                                                                                                                                                    <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                                                                                                    <table id="tblUpdateForAccountsListByPriority" align='center' cellpadding='1' cellspacing='1' border='0' class="gridTable" width='550'>
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

                                                                                                    </div>
                                                                                                </td>
                                                                                            </tr>--%>

                                                            <!--new for sales activity graphs-->
                                                            <tr>
                                                                <td class="homePortlet" valign="top">
                                                                    <div class="portletTitleBar">
                                                                        <div class="portletTitleLeft">Activities Summary Graph</div>
                                                                        <div class="portletIcons">
                                                                            <a href="javascript:animatedcollapse.hide('activitysummaryGraph')" title="Minimize">
                                                                                <img src="../../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                                            <a href="javascript:animatedcollapse.show('activitysummaryGraph')" title="Maximize">
                                                                                <img src="../../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                                            </a>                                                           
                                                                        </div>
                                                                        <div class="clear"></div>
                                                                    </div>
                                                                    <div id="activitysummaryGraph" style="background-color:#ffffff">
                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                                            <tr>
                                                                                <td width="40%" valign="top" align="center">
                                                                                    <s:form action="" theme="simple" name="dashboardSummaryRepGraph" >   

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
                                                                                                            <td class="fieldLabel">StartDate:<FONT color="red"  ><em>*</em></FONT></td> <!--value="%{dateWithOutTime}" -->


                                                                                                            <td><s:textfield name="startDateSummaryGraph" id="startDateSummaryGraph" cssClass="inputTextBlue" onchange="checkDates(this);"/><a href="javascript:cal11.popup();">
                                                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                                                            </td>
                                                                                                            <td class="fieldLabel">EndDate:<FONT color="red"  ><em>*</em></FONT></td>

                                                                                                            <td><s:textfield name="endDateSummaryGraph" id="endDateSummaryGraph" cssClass="inputTextBlue" onchange="checkDates(this);"/><a href="javascript:cal22.popup();">
                                                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                                                            </td>
                                                                                                        </tr>                                                                                                          

                                                                                                        <tr>  
                                                                                                            <td class="fieldLabel">Activity Type : </td>
                                                                                                            <td><s:select list="activityTypeList" id="activitytype1" name="activityType"  cssClass="inputSelect" headerKey="-1" headerValue="--Please Select--" onchange="enableCampaignName(this.value);"/></td>    
                                                                                                            <td class="fieldLabel">Team Member :</td>
         <td>
<s:select list="myTeamMembers" name="teamMemberId" id="teamMemberId2" headerKey="-1" headerValue="--Select TeamMember--" cssClass="inputSelect" onchange="toogleTeamCheck(this);titleTypeCheck(this);"/>
         </td>
		 
		 <td> <s:hidden name="titleType" id="titleType" cssClass="inputTextBlue"/>
              <s:hidden id="TitleTypeId" value="%{#session.title}"></s:hidden> 
			  <div id="displayTitleType"></div>
          
		  </td>
                                                                                                          
                                                                                                        </tr>
                                                                                                        
  <tr>
                                                                                                             <td class="fieldLabel">Campaign Name :</td>
                                                                                                                <td><s:select list="campaignIdMap" headerKey="" headerValue="-- Please Select --" name="campaignId"  id="campaignId" cssClass="inputSelect"/></td>
                                                                                                        </tr>
                                                                                                        <tr>
                                                                                                         <td></td>
                                                                                                             <td class="fieldLabel" align="right" style="padding: 20px"> <div id="checkDiv" style="display: none"><s:checkbox name="teamMemberCheck" id="teamMemberCheck" theme="simple" tabindex="11" />: Include Team Members&nbsp;</div></td> 
                                                                                                             <td></td>
                                                                                                            <td colspan="4" align="center">
                                                                                                                <input type="button" value="Search" id="activitygraphSearch" class="buttonBg" onclick="getsalesRecActivitiesAsGraph();"/>
                                                                                                            </td> 
                                                                                                        </tr>
                                                                                                    </table>
                                                                                                </td>
                                                                                            </tr>

                                                                                            <tr>
                                                                                                <td height="20px" align="center" colspan="9">
                                                                                                    <div id="loadActMessageASh" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                                </td>
                                                                                            </tr>

                                                                                            <tr>
                                                                                                <td>

                                                                                                    <table align="center" cellpadding="2" border="0" cellspacing="1" width="50%" >

                                                                                                        <tr>
                                                                                                            <td >

                                                                                                                <div id="resultMessage" align="center" style=" color: red;
                                                                                                                     font-family: lucida-sans;
                                                                                                                     font-size: 15px;
                                                                                                                     font-style: normal;
                                                                                                                     font-variant: normal;
                                                                                                                     font-weight: bold; display: none;"></div>
                                                                                                                <div id="recDashBoardActivitygraph" style="">

                                                                                                                    <div id="piechart" style="width: 400px; height: 400px;"></div>

                                                                                                                    <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->



                                                                                                                    <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                                                                                </div>
                                                                                                            </td>
                                                                                                        </tr>
                                                                                                    </table>    
                                                                                                </td>
                                                                                            </tr>
                                                                                            <!-- new graph details start -->

                                                                                            <tr>
                                                                                                <td colspan="3">
                                                                                                    <div id="tableHeading" style="display: none;" align='center'>Activities</div>
                                                                                                </td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td colspan="3">




                                                                                                    <table id="tblActivitySummaryByLoginId" align='center' cellpadding='1' cellspacing='1' border='0' class="gridTable" width='600'>
                                                                                                        <COLGROUP ALIGN="left" >
                                                                                                            <COL width="7%">
                                                                                                            <COL width="15%">
                                                                                                            <COL width="20%">
                                                                                                            <COL width="7%">
                                                                                                            <COL width="7%">
                                                                                                            <COL width="7%">
                                                                                                            <COL width="7%">

                                                                                                    </table>  



                                                                                                </td>
                                                                                            </tr>
                                                                                            <!-- new graph details end -->

                                                                                            <tr>

                                                                                                <td>


                                                                                                    <div id="greensheetDashBoardList" style="display: block">
                                                                                                        <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                                                        <table id="tblUpdate" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='90%' align="center">
                                                                                                            <COLGROUP ALIGN="left" >
                                                                                                                <COL width="3%">
                                                                                                                <COL width="20%">
                                                                                                                <COL width="20%">
                                                                                                                <COL width="15%">
                                                                                                                <COL width="15%">
                                                                                                                <COL width="15%">
                                                                                                                <COL width="2%">
                                                                                                        </table>
                                                                                                        <br>
                                                                                                        <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                                                                    </div>
                                                                                                </td>
                                                                                            </tr> 


                                                                                        </table>
                                                                                    </s:form>
                                                                                </td>
                                                                            </tr>
                                                                        </table>

                                                                        <script type="text/JavaScript">
                                                                            var cal11 = new CalendarTime(document.forms['dashboardSummaryRepGraph'].elements['startDateSummaryGraph']);
                                                                            cal11.year_scroll = true;
                                                                            cal11.time_comp = false;
                                                                            var cal22 = new CalendarTime(document.forms['dashboardSummaryRepGraph'].elements['endDateSummaryGraph']);
                                                                            cal22.year_scroll = true;
                                                                            cal22.time_comp = false;
                                                                        
                                                                        </script>
                                                                    </div>
                                                                </td>
                                                            </tr>

                                                            <%

                                                                Map rolesMap = (Map) session.getAttribute(ApplicationConstants.SESSION_MY_ROLES);
                                                                String teamName = session.getAttribute("teamName").toString();
                                                                if (rolesMap.containsKey("1") || teamName.equals("Software Sales")) {
                                                            %>

                                                            <%-- <s:if test="%{#session.teamName=='Software Sales'}"> --%>
                                                            <!-- Account Renewal Dashbord changes satrt -->
                                                            <tr>
                                                                <td class="homePortlet" valign="top">
                                                                    <div class="portletTitleBar">
                                                                        <div class="portletTitleLeft">Account Renewal Details By Month</div>
                                                                        <div class="portletIcons">
                                                                            <a href="javascript:animatedcollapse.hide('accountRenewalDiv')" title="Minimize">
                                                                                <img src="../../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                                            <a href="javascript:animatedcollapse.show('accountRenewalDiv')" title="Maximize">
                                                                                <img src="../../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                                            </a>
                                                                        </div>
                                                                        <div class="clear"></div>
                                                                    </div>
                                                                    <div id="accountRenewalDiv" style="background-color:#ffffff">
                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                                            <tr>
                                                                                <td width="40%" valign="top" align="center">
                                                                                    <s:form action="" theme="simple" name="accountRenewal">  

                                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">

                                                                                            <tr>
                                                                                                <td>
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
                                                                                                                        <td class="fieldLabel">Team Member: </td>
                                                                                                                        <td>
                                                                                                                            <s:select  list="myTeamMembers" name="teamMemberIdRenewal" id="teamMemberIdRenewal" headerKey="-1" headerValue="All" cssClass="inputLargeSelect" value="%{teamMemberId}" disabled="false"/>
                                                                                                                        </td>
                                                                                                                        <td class="fieldLabel"></td>
                                                                                                                        <td>
                                                                                                                            &nbsp;&nbsp;&nbsp;<input id="normalUserButton" type="button" value="Search"  class="buttonBg"  onclick="getaccountRenewalByTeamMember()"/> 
                                                                                                                        </td>
                                                                                                                        <td></td>
                                                                                                                    </tr>
                                                                                                                </table>
                                                                                                            </td>
                                                                                                        </tr>
                                                                                                        <!-- end of message row -->
                                                                                                        <tr>
                                                                                                            <td>
                                                                                                                <br>
                                                                                                                <table align="center" cellpadding="2" border="0" cellspacing="1" width="50%" >
                                                                                                                    <tr>
                                                                                                                        <td height="20px" align="center" colspan="9">
                                                                                                                            <div id="loadActMessageRenewal1" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                                                        </td>
                                                                                                                    </tr>
                                                                                                                    <tr>
                                                                                                                        <td ><br>
                                                                                                                            <div id="AccountRenewal" style="display: block">
                                                                                                                                <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                                                                                <table id="AccountRenewal1" align='center' i cellpadding='1' cellspacing='1' border='0' class="gridTable" width='550'>
                                                                                                                                    <COLGROUP ALIGN="left" >
                                                                                                                                        <COL width="5%">
                                                                                                                                        <COL width="15%">
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
                                                                                                    </table>
                                                                                                </s:form>
                                                                                            </td>
                                                                                        </tr>
                                                                                    </table>
                                                                                    </div>
                                                                                </td>
                                                                            </tr>
                                                                        </table>
                                                                        <%--  </sx:tabbedpanel> --%>
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
                                                            <%-- </s:if> --%>
                                                         
                                                            <!-- Account renewal dashbord changes end -->
<!-- Account Renewal Dashbord changes satrt -->
                                                                        <tr>
                                                                            <td class="homePortlet" valign="top">
                                                                                <div class="portletTitleBar">
                                                                                    <div class="portletTitleLeft">Account Renewal Details By state</div>
                                                                                    <div class="portletIcons">
                                                                                        <a href="javascript:animatedcollapse.hide('accountRenewalStateDiv')" title="Minimize">
                                                                                            <img src="../../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                                                        <a href="javascript:animatedcollapse.show('accountRenewalStateDiv')" title="Maximize">
                                                                                            <img src="../../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                                                        </a>
                                                                                    </div>
                                                                                    <div class="clear"></div>
                                                                                </div>
                                                                                <div id="accountRenewalStateDiv" style="background-color:#ffffff">
                                                                                    <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                                                        <tr>
                                                                                            <td width="40%" valign="top" align="center">
                                                                                                <s:form action="" theme="simple" name="accountRenewalState">  

                                                                                                    <table cellpadding="0" cellspacing="0" border="0" width="100%">

                                                                                                        <tr>
                                                                                                            <td>
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
                                                                                                                                    <td class="fieldLabel">State:</td>                                                                                
                                                                                                                                    <td>
                                                                                                                                        <s:select 
                                                                                                                                            list="statesList"   
                                                                                                                                            name="statesList1" 
                                                                                                                                            id="statesList1"
                                                                                                                                            cssClass="inputSelect" 
                                                                                                                                            headerKey="-1"
                                                                                                                                            headerValue="--Please Select--"
                                                                                                                                            theme="simple"/>

                                                                                                                                    </td>
                                                                                                                                    <td class="fieldLabel"></td>
                                                                                                                                    <td>
                                                                                                                                        &nbsp;&nbsp;&nbsp;<input id="normalUserButton" type="button" value="Search"  class="buttonBg"  onclick="getaccountRenewalByState()"/> 
                                                                                                                                    </td>
                                                                                                                                    <td></td>
                                                                                                                                </tr>
                                                                                                                            </table>
                                                                                                                        </td>
                                                                                                                    </tr>
                                                                                                                    <!-- end of message row -->
                                                                                                                    <tr>
                                                                                                                        <td>
                                                                                                                            <br>
                                                                                                                            <table align="center" cellpadding="2" border="0" cellspacing="1" width="50%" >
                                                                                                                                <tr>
                                                                                                                                    <td height="20px" align="center" colspan="9">
                                                                                                                                        <div id="loadActMessageRenewalByState" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                                                                    </td>
                                                                                                                                </tr>
                                                                                                                                <tr>
                                                                                                                                    <td ><br>
                                                                                                                                        <div id="AccountRenewal" style="display: block">
                                                                                                                                            <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                                                                                            <table id="AccountRenewalState" align='center' i cellpadding='1' cellspacing='1' border='0' class="gridTable" width='550'>
                                                                                                                                                <COLGROUP ALIGN="left" >
                                                                                                                                                    <COL width="5%">
                                                                                                                                                    <COL width="15%">
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
                                                                                                                </table>
                                                                                                            </s:form>
                                                                                                        </td>
                                                                                                    </tr>
                                                                                                </table>
                                                                                                </div>
                                                                                            </td>
                                                                                        </tr>
                                                                                    </table>
                                                                                    <%--  </sx:tabbedpanel> --%>
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
                                                            <%-- </s:if> --%>
                                                            <%}%>
                                                            <!-- Account renewal dashbord changes end -->
                                                            
                                                            <!-- Monthy status report start -->
                                                            
<tr>
                                                                <td class="homePortlet" valign="top">
                                                                    <div class="portletTitleBar">
                                                                        <div class="portletTitleLeft">Monthly Status Report</div>
                                                                        <div class="portletIcons">
                                                                            <a href="javascript:animatedcollapse.hide('monthlyReportDiv')" title="Minimize">
                                                                                <img src="../../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                                            <a href="javascript:animatedcollapse.show('monthlyReportDiv')" title="Maximize">
                                                                                <img src="../../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                                            </a>
                                                                        </div>
                                                                        <div class="clear"></div>
                                                                    </div>
                                                                    <div id="monthlyReportDiv" style="background-color:#ffffff">
                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                                            <tr>
                                                                                <td width="40%" valign="top" align="center">
                                                                                    <s:form action="" theme="simple" name="monthlyReport">  

                                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">

                                                                                            <tr>
                                                                                                <td>
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
                                                                                                                        <td class="fieldLabel">Team Member: </td>
                                                                                                                        <td>
                                                                                                                            <s:select  list="myTeamMembers" name="teamMemberIdReport" id="teamMemberIdReport" headerKey="-1" headerValue="All" cssClass="inputLargeSelect" value="%{teamMemberId}" onchange="inclueTeamMembers();"/>
                                                                                                                        </td>
                                                                                                                         <td class="fieldLabel">Year: </td>
                                                                                                                          <td><s:textfield name="year" id="year" cssClass="inputTextBlue" value=""/> </td> 
                                                                                                                </tr>
                                                                                                                <tr>
                                                                                                                     <td></td>
                                                                                                                     <td class="fieldLabel" align="right" style="padding: 20px"> <s:checkbox name="includeTeam" id="includeTeam"
                                                                                                                                 accesskey="" value="%{includeTeam}" theme="simple" tabindex="11" disabled="true"/>: Include all Team Members&nbsp;</td> 
                                                                                                                    
                                                                                                                      <td></td>
                                                                                                                        <td>
                                                                                                                            &nbsp;&nbsp;&nbsp;<input id="normalUserButton" type="button" value="Search"  class="buttonBg"  onclick="getMonthlyStatusReport()"/> 
                                                                                                                        </td>
                                                                                                                       
                                                                                                                    </tr>
                                                                                                                </table>
                                                                                                            </td>
                                                                                                        </tr>
                                                                                                        <!-- end of message row -->
                                                                                                        <tr>
                                                                                                            <td>
                                                                                                                <br>
                                                                                                                <table align="center" cellpadding="2" border="0" cellspacing="1" width="50%" >
                                                                                                                    <tr>
                                                                                                                        <td height="20px" align="center" colspan="9">
                                                                                                                            <div id="loadActMessageStatus" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                                                        </td>
                                                                                                                    </tr>
                                                                                                                    <tr>
                                                                                                                        <td ><br>
                                                                                                                            <div id="statusReport" style="display: block">
                                                                                                                                <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                                                                                <table id="tblStatusReport" align='center' i cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750'>
                                                                                                                                    <COLGROUP ALIGN="left" >
                                                                                                                                        <COL width="40%">
                                                                                                                                        <COL width="5%">
                                                                                                                                        <COL width="5%">
                                                                                                                                        <COL width="5%">
                                                                                                                                </table> <br>
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
                                                                                    </div>
                                                                                </td>
                                                                            </tr>
                                                                        </table>
                                                                        <%--  </sx:tabbedpanel> --%>
                                                                        <!--//END TABBED PANNEL : --> 
                                                                    </div>
                                                                    </div>
                                                                    
                                                                </td>
                                                                <!--//END DATA COLUMN : Coloumn for Screen Content-->
                                                            </tr>
															
	
                                                            <!-- Monthly status report end -->
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
		
		disableCampaignName();
                setDefaultOppDates();
                defaultDates();
                getaccountRenewalByTeamMember();
                hideSelect();
		
                
		});
		</script>
                                        </html>
