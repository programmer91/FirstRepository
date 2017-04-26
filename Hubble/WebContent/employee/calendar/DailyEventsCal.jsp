<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="com.freeware.gridtag.*" %> 
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd" %>
<html>
<head>
    <title>Hubble Organization Portal :: Calendar </title>
    <sx:head cache="true"/>
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
    
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>   
    <%--<script type="text/JavaScript" src="<s:url value="/includes/javascripts/CurrentDateUtilplus2.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/clientValidations/LeaveRequestValidation.js"/>"></script>  --%>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/calendarGeneral/weeklyEvents.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/calendarGeneral/CalendarAjaxUtil.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/calendarGeneral/Calendar.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/calendarGeneral/EventPopupAjax.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/calendarGeneral/CalendarAccess.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/marketing/MarketingAjaxUtil.js"/>"></script>   
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/calendarGeneral/AccountTeam.js"/>"></script>   
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/calendarGeneral/centerPostion.js"/>"></script>   
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/calendarGeneral/timePicker.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
    
    
    <s:include value="/includes/template/headerScript.html"/>
    <style type="text/css">
        
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
        
        .popupItem:hover {
        background: #F2F5A9;
        font: arial;
        font-size:10px;
        color: black;
        }
        
        .popupRow {
        background: #3E93D4;
        }
        
    </style>
    
</head>
<!-- onload="doTime()" -->
<!-- <body class="bodyGeneral" oncontextmenu="return false;" onload="checkAccessCalendarAction()" > -->

<body class="bodyGeneral" oncontextmenu="return false;">
<%!
/* Declarations */
Connection connection;

String strTmp;
String strSortCol;
String strSortOrd;
String action;
int role;
int intSortOrd = 0;
int intCurr;
boolean blnSortAsc = true;
%>
<style type='text/css'>
    .dragme { cursor: move }
</style>
<script type='text/javascript'>
var ie = document.all;
var nn6 = document.getElementById &&! document.all;
var isdrag = false;
var x, y;
var dobj;

function movemouse( e ) {
  if( isdrag ) {
    dobj.style.left = nn6 ? tx + e.clientX -x: tx + event.clientX -x;
    dobj.style.top  = nn6 ? ty + e.clientY -y: ty + event.clientY-y;
    return false;
  }
}

function selectmouse( e ) {
  var fobj       = nn6 ? e.target : event.srcElement;
  var topelement = nn6 ? "HTML" : "BODY";
  while (fobj.tagName != topelement && fobj.className != "dragme") {
    fobj = nn6 ? fobj.parentNode : fobj.parentElement;
  }

  if (fobj.className=="dragme") {
    isdrag = true;
    dobj = document.getElementById("popupAddUser");
    tx = parseInt(dobj.style.left+0);
    ty = parseInt(dobj.style.top+0);
    x = nn6 ? e.clientX : event.clientX;
    y = nn6 ? e.clientY : event.clientY;
    document.onmousemove=movemouse;
    return false;
  }
}

document.onmousedown=selectmouse;
document.onmouseup=new Function("isdrag=false");
</script>
<!--//START MAIN TABLE : Table for template Structure-->
<table class="templateTableLogin" align="center" cellpadding="0" cellspacing="0" >

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
    <table class="innerTableLogin" style="z-index:901;" cellpadding="0" cellspacing="0">
    <tr>
        <!--//START DATA COLUMN : Coloumn for LeftMenu-->
        <td width="150px;" class="leftMenuBgColor" valign="top"> 
            <s:include value="/includes/template/LeftMenu.jsp"/>
        </td>
        <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                            
        <!--//START DATA COLUMN : Coloumn for Screen Content-->
        <td width="850px" class="cellBorder" valign="top">
        <!--//START TABBED PANNEL : --> 
        <sx:tabbedpanel id="Calendar" beforeSelectTabNotifyTopics="/accessCalUserList" cssStyle="width:850px; height:600px;padding-left:10px;padding-top:5px;" doLayout="true">
        
        <!--//START TAB : -->
        <sx:div id="calendarView" label="Calendar" cssStyle="overflow: auto;z-index:100;" >
        <table cellpadding="0" style="z-index:900;" cellspacing="0" width="100%" border="0">
            <tr>
            <td> 
            <input type="hidden" id="actualEventDate" name="actualEventDate" value="" >
            <input type="hidden" id="currentCaluserId" name="currentCaluserId" value="" >
            
            <s:hidden id="currentAction" name="currentAction" value="%{currentAction}" />
            <input type="hidden" id="currentuser" name="currentuser" value="<%=session.getAttribute("userId")%>">                                                    <!-- This Div is for daily view==== Start -->
            <div id="loadMessage" style="display: none" align="center" class="error" >
                <b>Loading.... Please Wait....</b>
            </div>
            <div id="accessUserCal" style="display:block" >
                <table cellpadding="0" cellspacing="0" width="100%" border="0">
                    <tr>
                        <td height="40px" class="headerText"  >
                            
                        </td>
                    </tr>
                    <s:if test ="%{currentAction == 'AccessCalendar'}">
                        <tr align="center">
                            <td class="fieldLabelCenter" height="20px" valign="middle" >
                                Please Select Team Member To Access the Calendar:
                            </td>
                        </tr>
                        <s:if test="%{currentTeamAction == 'AccessTeamCalendar'}">
                            <tr>
                                <td align="center" >
                                    <s:select  theme="simple" onchange="getAccessUserCalendar1()" list="TeamCalendar" cssClass="inputSelect" id="salesTeamUser" name="salesTeamUser" headerKey="1" headerValue="---Please Select---" ></s:select>
                                    <%--<sx:autocompleter list="TeamCalendar" forceValidOption="true"  cssClass="inputSelect" id="salesTeamUser" name="salesTeamUser" headerKey="1" onchange="getAccessUserCalendar1()"/>--%>
                                    <%--<s:select  onchange="getAccessUserCalendar()" list="{}" cssClass="inputSelect" id="salesTeamUser" name="salesTeamUser"  ></s:select>--%>
                                </td>
                            </tr>
                        </s:if>
                        <s:else>
                            <tr>
                                <td align="center" >
                                    <s:select theme="simple" onchange="getAccessUserCalendar()" list="salesTeamExceptAccountTeamMap" cssClass="inputSelect" id="salesTeamUser" name="salesTeamUser" headerKey="1" headerValue="---Please Select---" ></s:select>
                                    <%--<s:select  onchange="getAccessUserCalendar()" list="{}" cssClass="inputSelect" id="salesTeamUser" name="salesTeamUser"  ></s:select>--%>
                                </td>
                            </tr>  
                        </s:else>
                        <tr>
                            <td height="20px">
                                
                            </td>
                        </tr>
                    </s:if>
                </table>
            </div>
            
            <div id="monthlyView" style="display: none" >
            <form name="eventForm" action="" id="eventForm" >
                <table align="center" border="0" width="100%" cellpadding="0" cellspacing="0">
                    <tr>
                        <td class="headerText" colspan="8">
                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                        </td>
                    </tr>
                    <tr>
                        <td class="fieldLabel">Year :</td>
                        <td width="100">
                            <input type="text" class="inputTextBlueSmall" name="year" id="year" onchange="getDateInfo();"/>
                        </td>
                        <td class="fieldLabel">Month :</td>
                        <td>
                            <select id="month" name="month" onchange="getDateInfo();">
                                <option value="0">Jan</option>
                                <option value="1">Feb</option>
                                <option value="2">Mar</option>
                                <option value="3">Apr</option>
                                <option value="4">May</option>
                                <option value="5">Jun</option>
                                <option value="6">Jul</option>
                                <option value="7">Aug</option>
                                <option value="8">Sep</option>
                                <option value="9">Oct</option>
                                <option value="10">Nov</option>
                                <option value="11">Dec</option>
                            </select>
                        </td>
                        <s:if test ="%{currentAction != 'AccessCalendar'}">
                            <div style="display:none" id="noWriteAccess">
                                <td>
                                    <input type="button" name="addEvent" id="addEvent" value="Add Event" class="buttonBg" onclick="addCalendarEvent();">
                                </td>
                                <td>
                                    <input type="button" name="addStatus" id="addStatus" value="Day Status" class="buttonBg" onclick="dayStatus();">
                                </td>
                            </div>
                        </s:if>
                        
                        <s:if test ="%{currentAction == 'AccessCalendar'}">
                            
                            <td>
                                <div style="display:none" id="access" > <input type="button" name="addEvent" id="addEvent" value="Add Event" class="buttonBg" onclick="addCalendarEvent();"></div>
                            </td>
                            
                        </s:if>
                    </tr>
                </table>
                <br>
                <table id="formTable" align="center" class="gridTable" border="0" width="100%" cellpadding="1" cellspacing="1">
                    <tr class="gridHeader">
                        <td width="13%" class="gridHeader">Sunday </td>
                        <td width="13%" class="gridHeader">Monday</td>
                        <td width="13%" class="gridHeader">Tuesday</td>
                        <td width="13%" class="gridHeader">Wednesday</td>
                        <td width="13%" class="gridHeader">Thursday</td>
                        <td width="13%" class="gridHeader">Friday</td>
                        <td width="13%" class="gridHeader">Saturday</td>
                    </tr>
                    <tr class="gridRowEven">
                        <td id="td1" ><input type="text" name="text1" id="text1" class="calendarCol" align="left" value="" readonly onclick="doAdd(this);"></td>
                        <td id="td2" ><input type="text" name="text2"  id="text2" class="calendarCol" align="left" value="" readonly onclick="doAdd(this);"></td>
                        <td id="td3"><input type="text" name="text3"  id="text3" class="calendarCol" align="left" value="" readonly onclick="doAdd(this);"></td>
                        <td id="td4"><input type="text" name="text4"  id="text4" class="calendarCol" align="left" value="" readonly onclick="doAdd(this);"></td>
                        <td id="td5"><input type="text" name="text5"  id="text5" class="calendarCol" align="left" value="" readonly onclick="doAdd(this);"></td>
                        <td id="td6"><input type="text" name="text6"  id="text6" class="calendarCol" align="left" value="" readonly onclick="doAdd(this);"></td>
                        <td id="td7"><input type="text" name="text7"  id="text7" class="calendarCol" align="left" value="" readonly onclick="doAdd(this);"></td>
                    </tr>
                    <tr class="gridRowEven">
                        <td> <input type="text" name="text8"  id="text8" class="calendarCol" align="left" value="" readonly onclick="doAdd(this);"></td>
                        <td> <input type="text" name="text9"  id="text9" class="calendarCol" align="left" value="" readonly onclick="doAdd(this);"></td>
                        <td> <input type="text" name="text10" id="text10" class="calendarCol" align="left" value="" readonly onclick="doAdd(this);"></td>
                        <td> <input type="text" name="text11" id="text11" class="calendarCol" align="left" value="" readonly onclick="doAdd(this);"></td>
                        <td> <input type="text" name="text12" id="text12" class="calendarCol" align="left" value="" readonly onclick="doAdd(this);"></td>
                        <td> <input type="text" name="text13" id="text13" class="calendarCol" align="left" value="" readonly onclick="doAdd(this);"></td>
                        <td> <input type="text" name="text14" id="text14" class="calendarCol" align="left" value="" readonly onclick="doAdd(this);"></td>
                    </tr>
                    <tr class="gridRowEven">
                        <td> <input type="text" name="text15" id="text15" class="calendarCol" align="left" value="" readonly onclick="doAdd(this);"></td>
                        <td> <input type="text" name="text16" id="text16" class="calendarCol" align="left" value="" readonly onclick="doAdd(this);"></td>
                        <td> <input type="text" name="text17" id="text17" class="calendarCol" align="left" value="" readonly onclick="doAdd(this);"></td>
                        <td> <input type="text" name="text18" id="text18" class="calendarCol" align="left" value="" readonly onclick="doAdd(this);"></td>
                        <td> <input type="text" name="text19" id="text19" class="calendarCol" align="left" value="" readonly onclick="doAdd(this);"></td>
                        <td> <input type="text" name="text20" id="text20" class="calendarCol" align="left" value="" readonly onclick="doAdd(this);"></td>
                        <td> <input type="text" name="text21" id="text21" class="calendarCol" align="left" value="" readonly onclick="doAdd(this);"></td>
                    </tr>
                    <tr class="gridRowEven">
                        <td> <input type="text" name="text22" id="text22" class="calendarCol" align="left" value="" readonly onclick="doAdd(this);"></td>
                        <td> <input type="text" name="text23" id="text23" class="calendarCol" align="left" value="" readonly onclick="doAdd(this);"></td>
                        <td> <input type="text" name="text24" id="text24" class="calendarCol" align="left" value="" readonly onclick="doAdd(this);"></td>
                        <td> <input type="text" name="text25" id="text25" class="calendarCol" align="left" value="" readonly onclick="doAdd(this);"></td>
                        <td> <input type="text" name="text26" id="text26" class="calendarCol" align="left" value="" readonly onclick="doAdd(this);"></td>
                        <td> <input type="text" name="text27" id="text27" class="calendarCol" align="left" value="" readonly onclick="doAdd(this);"></td>
                        <td> <input type="text" name="text28" id="text28" class="calendarCol" align="left" value="" readonly onclick="doAdd(this);"></td>
                    </tr>
                    <tr class="gridRowEven">
                        <td> <input type="text" name="text29" id="text29" class="calendarCol" align="left" value="" readonly onclick="doAdd(this);"></td>
                        <td> <input type="text" name="text30" id="text30" class="calendarCol" align="left" value="" readonly onclick="doAdd(this);"></td>
                        <td> <input type="text" name="text31" id="text31" class="calendarCol" align="left" value="" readonly onclick="doAdd(this);"></td>
                        <td> <input type="text" name="text32" id="text32" class="calendarCol" align="left" value="" readonly onclick="doAdd(this);"></td>
                        <td> <input type="text" name="text33" id="text33" class="calendarCol" align="left" value="" readonly onclick="doAdd(this);"></td>
                        <td> <input type="text" name="text34" id="text34" class="calendarCol" align="left" value="" readonly onclick="doAdd(this);"></td>
                        <td> <input type="text" name="text35" id="text35" class="calendarCol" align="left" value="" readonly onclick="doAdd(this);"></td>
                    </tr>
                    <tr class="gridRowEven">
                        <td> <input type="text" name="text36" id="text36" class="calendarCol" align="left" value="" readonly onclick="doAdd(this);"></td>
                        <td> <input type="text" name="text37" id="text37" class="calendarCol" align="left" value="" readonly onclick="doAdd(this);"></td>
                        <td> <input type="text" name="text38" id="text38" class="calendarCol" align="left" value="" readonly></td>
                        <td> <input type="text" name="text39" id="text39" class="calendarCol" align="left" value="" readonly></td>
                        <td> <input type="text" name="text40" id="text40" class="calendarCol" align="left" value="" readonly></td>
                        <td> <input type="text" name="text41" id="text41" class="calendarCol" align="left" value="" readonly></td>
                        <td> <input type="text" name="text42" id="text42" class="calendarCol" align="left" value="" readonly></td>
                    </tr>
                </table>
                
                <table>
                    <tr>
                        <td>
                            <b><font color="#D8D8D8">Events;</b> </font>&nbsp;&nbsp;
                            <b><font color="pink">Time Off;</b> </font>&nbsp;&nbsp;
                            <b><font color="red">Vacation;</b> </font>&nbsp;&nbsp;
                            <b><font color="#8DC0F1">On Travel;</b> </font>&nbsp;&nbsp;
                            <b><font color="#AEB404">Busy In Meeting</b> </font>
                        </td>
                    </tr>
                </table>
            </form>
            
            </div>
            
            <div id="dailyView" style="display: none;z-index:999;" >
                <form action="" id="dailyView" name="dailyView"  >
                    <table align="center" cellpadding="0" cellspacing="0" width="100%" border="0" >
                        <input type="hidden" id="dailySelectedDate" name="dailySelectedDate" value="" >
                        <tr>
                            <td height="20px" colspan="3" class="headerText" >
                                
                            </td>
                        </tr>
                        <tr>
                            <td class="fieldLabelLeft">
                                Select date For Daily Events :
                            </td>
                            <td>
                                <input type="text" name="dailyEventDate" id="dailyEventDate" cssClass="inputTextBlueSmall" value=""/><a href="javascript:cal3.popup();">
                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                            </td>
                            <td>
                                <input type="button" class="buttonbg" onclick="getDailyEvents();" value="Get Events" >
                                <input type="button" class="buttonbg" id="weeklyViewBut" onclick="getWeeklyEvents();" value="Weekly View" >
                                <input type="button" class="buttonbg" id="monthlyViewBut" onclick="getMonthlyEvents();" value="Monthly View" >
                            </td>
                        </tr>
                        <tr>
                            <td height="40px" id="todayEventsId" class="fieldLabelLeft" >
                                
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3" >
                                <table id="dailyViewTable1" align="center"  
                                       cellpadding='1' cellspacing='1' border='0' width='750' >
                                    <tbody id="dailyViewTable"></tbody>
                                    <tr>
                                        <td class="calendarHourRow" width="50" valign="top" align="right" onclick="addDailyCalendarEvent(event)">0.00</td>
                                        <td id="00" class="calendarHourRowEvent">
                                            <div><table width="100%" cellspacing="0" cellpadding="0" ><tbody><tr id="00.1"></tr></tbody></table></div>
                                            <div><table width="100%"><tbody><tr id="00.2"></tr></tbody></table></div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="calendarHourRow" width="50" valign="top" align="right" onclick="addDailyCalendarEvent(event)">1.00</td>
                                        <td id="01" class="calendarHourRowEvent">
                                            <div><table width="100%" cellspacing="0" cellpadding="0" ><tbody><tr id="01.1"></tr></tbody></table></div>
                                            <div><table width="100%"><tbody><tr id="01.2"></tr></tbody></table></div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="calendarHourRow" width="50" valign="top" align="right"  onclick="addDailyCalendarEvent(event)">2.00</td>
                                        <td id="2" class="calendarHourRowEvent">
                                            <div><table width="100%" cellspacing="0" cellpadding="0" ><tbody><tr id="02.1"></tr></tbody></table></div>
                                            <div ><table width="100%"><tbody><tr id="02.2"></tr></tbody></table></div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="calendarHourRow" width="50" valign="top" align="right"  onclick="addDailyCalendarEvent(event)">3.00</td>
                                        <td id="3" class="calendarHourRowEvent">
                                            <div><table width="100%" cellspacing="0" cellpadding="0" ><tbody><tr id="03.1"></tr></tbody></table></div>
                                            <div ><table width="100%"><tbody><tr id="03.2"></tr></tbody></table></div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="calendarHourRow" width="50" valign="top" align="right"  onclick="addDailyCalendarEvent(event)">4.00</td>
                                        <td id="4" class="calendarHourRowEvent">
                                            <div><table width="100%" cellspacing="0" cellpadding="0" ><tbody><tr id="04.1"></tr></tbody></table></div>
                                            <div ><table width="100%"><tbody><tr id="04.2"></tr></tbody></table></div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="calendarHourRow" width="50" valign="top" align="right"  onclick="addDailyCalendarEvent(event)">5.00</td>
                                        <td id="5" class="calendarHourRowEvent">
                                            <div><table width="100%" cellspacing="0" cellpadding="0" ><tbody><tr id="05.1"></tr></tbody></table></div>
                                            <div ><table width="100%"><tbody><tr id="05.2"></tr></tbody></table></div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="calendarHourRow" width="50" valign="top" align="right"  onclick="addDailyCalendarEvent(event)">6.00</td>
                                        <td id="6" class="calendarHourRowEvent">
                                            <div><table width="100%" cellspacing="0" cellpadding="0" ><tbody><tr id="06.1"></tr></tbody></table></div>
                                            <div ><table width="100%"><tbody><tr id="06.2"></tr></tbody></table></div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="calendarHourRow" width="50" valign="top" align="right"  onclick="addDailyCalendarEvent(event)">7.00</td>
                                        <td id="7" class="calendarHourRowEvent">
                                            <div><table width="100%" cellspacing="0" cellpadding="0" ><tbody><tr id="07.1"></tr></tbody></table></div>
                                            <div ><table width="100%"><tbody><tr id="07.2"></tr></tbody></table></div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="calendarHourRow" width="50" valign="top" align="right"  onclick="addDailyCalendarEvent(event)">8.00</td>
                                        <td id="8" class="calendarHourRowEvent">
                                            <div><table width="100%" cellspacing="0" cellpadding="0" ><tbody><tr id="08.1"></tr></tbody></table></div>
                                            <div ><table width="100%"><tbody><tr id="08.2"></tr></tbody></table></div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="calendarHourRow" width="50" valign="top" align="right"  onclick="addDailyCalendarEvent(event)">9.00</td>
                                        <td id="9" class="calendarHourRowEvent">
                                            <div><table width="100%" cellspacing="0" cellpadding="0" ><tbody><tr id="09.1"></tr></tbody></table></div>
                                            <div ><table width="100%"><tbody><tr id="09.2"></tr></tbody></table></div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="calendarHourRow" width="50" valign="top" align="right"  onclick="addDailyCalendarEvent(event)">10.00</td>
                                        <td id="10" class="calendarHourRowEvent">
                                            <div><table width="100%" cellspacing="0" cellpadding="0" ><tbody><tr id="10.1"></tr></tbody></table></div>
                                            <div ><table width="100%"><tbody><tr id="10.2"></tr></tbody></table></div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="calendarHourRow" width="50" valign="top" align="right"  onclick="addDailyCalendarEvent(event)">11.00</td>
                                        <td id="11" class="calendarHourRowEvent">
                                            <div><table width="100%" cellspacing="0" cellpadding="0" ><tbody><tr id="11.1"></tr></tbody></table></div>
                                            <div ><table width="100%"><tbody><tr id="11.2"></tr></tbody></table></div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="calendarHourRow" width="50" valign="top" align="right"  onclick="addDailyCalendarEvent(event)">12.00</td>
                                        <td id="12" class="calendarHourRowEvent">
                                            <div><table width="100%" cellspacing="0" cellpadding="0" ><tbody><tr id="12.1"></tr></tbody></table></div>
                                            <div ><table width="100%"><tbody><tr id="12.2"></tr></tbody></table></div>
                                        </td>
                                    </tr>
                                    <tr id="13.00" >
                                        <td class="calendarHourRow" width="50" valign="top" align="right"  onclick="addDailyCalendarEvent(event)">13.00</td>
                                        <td id="13" class="calendarHourRowEvent">
                                            <div><table width="100%" cellspacing="0" cellpadding="0" ><tbody><tr id="13.1"></tr></tbody></table></div>
                                            <div ><table width="100%"><tbody><tr id="13.2"></tr></tbody></table></div>
                                        </td>
                                    </tr>
                                    <tr id="14.00">
                                        <td class="calendarHourRow" width="50" valign="top" align="right"  onclick="addDailyCalendarEvent(event)">14.00</td>
                                        <td id="14" class="calendarHourRowEvent">
                                            <div><table width="100%" cellspacing="0" cellpadding="0" ><tbody><tr id="14.1"></tr></tbody></table></div>
                                            <div ><table width="100%"><tbody><tr id="14.2"></tr></tbody></table></div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="calendarHourRow" width="50" valign="top" align="right"  onclick="addDailyCalendarEvent(event)">15.00</td>
                                        <td id="15" class="calendarHourRowEvent">
                                            <div><table width="100%" cellspacing="0" cellpadding="0"><tbody><tr id="15.1"></tr></tbody></table></div>
                                            <div ><table width="100%" width="100%" cellspacing="0" cellpadding="0"><tbody><tr id="15.2"></tr></tbody></table></div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="calendarHourRow" width="50" valign="top" align="right"  onclick="addDailyCalendarEvent(event)">16.00</td>
                                        <td id="16" class="calendarHourRowEvent">
                                            <div><table width="100%" cellspacing="0" cellpadding="0" ><tbody><tr id="16.1"></tr></tbody></table></div>
                                            <div ><table width="100%"><tbody><tr id="16.2"></tr></tbody></table></div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="calendarHourRow" width="50" valign="top" align="right"  onclick="addDailyCalendarEvent(event)">17.00</td>
                                        <td id="17" class="calendarHourRowEvent">
                                            <div><table width="100%" cellspacing="0" cellpadding="0" ><tbody><tr id="17.1"></tr></tbody></table></div>
                                            <div ><table width="100%"><tbody><tr id="17.2"></tr></tbody></table></div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="calendarHourRow" width="50" valign="top" align="right"  onclick="addDailyCalendarEvent(event)">18.00</td>
                                        <td id="18" class="calendarHourRowEvent">
                                            <div><table width="100%" cellspacing="0" cellpadding="0" ><tbody><tr id="18.1"></tr></tbody></table></div>
                                            <div ><table width="100%"><tbody><tr id="18.2"></tr></tbody></table></div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="calendarHourRow" width="50" valign="top" align="right"  onclick="addDailyCalendarEvent(event)">19.00</td>
                                        <td id="19" class="calendarHourRowEvent">
                                            <div><table width="100%" cellspacing="1" cellpadding="1" ><tbody><tr id="19.1"></tr></tbody></table></div>
                                            <div ><table width="100%"><tbody><tr id="19.2"></tr></tbody></table></div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="calendarHourRow" width="50" valign="top" align="right"  onclick="addDailyCalendarEvent(event)">20.00</td>
                                        <td id="20" class="calendarHourRowEvent">
                                            <div><table width="100%" cellspacing="0" cellpadding="0" ><tbody><tr id="20.1"></tr></tbody></table></div>
                                            <div ><table width="100%"><tbody><tr id="20.2"></tr></tbody></table></div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="calendarHourRow" width="50" valign="top" align="right"  onclick="addDailyCalendarEvent(event)">21.00</td>
                                        <td id="21" class="calendarHourRowEvent">
                                            <div><table width="100%" cellspacing="0" cellpadding="0" ><tbody><tr id="21.1"></tr></tbody></table></div>
                                            <div ><table width="100%"><tbody><tr id="21.2"></tr></tbody></table></div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="calendarHourRow" width="50" valign="top" align="right"  onclick="addDailyCalendarEvent(event)">22.00</td>
                                        <td id="22" class="calendarHourRowEvent">
                                            <div><table width="100%" cellspacing="0" cellpadding="0" ><tbody><tr id="22.1"></tr></tbody></table></div>
                                            <div ><table width="100%"><tbody><tr id="22.2"></tr></tbody></table></div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="calendarHourRow" width="50" valign="top" align="right"  onclick="addDailyCalendarEvent(event)">23.00</td>
                                        <td id="23" class="calendarHourRowEvent">
                                            <div><table width="100%" cellspacing="0" cellpadding="0" ><tbody><tr id="23.1"></tr></tbody></table></div>
                                            <div><table width="100%"><tbody><tr id="23.2"></tr></tbody></table></div>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </form>
                <script type="text/JavaScript">
                var cal3 = new CalendarTime(document.forms['dailyView'].elements['dailyEventDate']);
                cal3.year_scroll = true;                
                cal3.time_comp = false;
                </script> 
            </div>
            
            <!-- This Div is for daily view====  End -->
                                                    
            <!-- This Div is for Status Popup====  Start -->
            <div align="center" style="display:none; position: absolute;top: 50px; left: 150px;z-index: 1000;" id="popupAddStatus">
                <table align="center" cellpadding="0" style="background-image:url('/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/PopUp_Blue_500x250.gif'); background-repeat: no-repeat;" cellspacing="0" width="500px" border="0">
                    <tr>
                        <td height="8px">
                            
                        </td>
                    </tr>
                    <tr>
                        <td align="center">
                            <img align="center" width="477px" height="30px" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/PopUp_head3.gif">
                        </td>
                    </tr>
                    <tr>
                        <td align="center" >
                            <div align="center">
                                <s:form name="statusDetails" id="statusDetails" action="" theme="simple">
                                    <table align="center" border="0" width="63%" >
                                        <tr>
                                            <td colspan="2" align="right" class="fieldLabel" ><a name="close" style="cursor: pointer;" href="#" onclick="document.getElementById('popupAddStatus').style.display='none'">close[x]</a></td>
                                        </tr>
                                        <tr>
                                            <td height="7px">
                                                
                                            </td>
                                        </tr>
                                        <tr style="z-index: 8">
                                            <td class="fieldLabel">Date:</td>
                                            <td><s:textfield name="sdate"  id="sdate" cssClass="inputTextBlue" value=""/><a href="javascript:cal5.popup();">
                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="fieldLabel">
                                                Status :
                                            </td>
                                            <td>
                                                <s:select name="status" id="status" list="{'Time Off','Vacation','On Travel','Busy In Meeting','Remove Status'}" headerKey="-please select-" headerValue="-please select-" value="" cssClass="inputSelect"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" align="center">
                                                <input id="addStatusBtn" style="z-index: 4" onclick="saveStatus()"  type="button" class="buttonBg" value="save" >
                                            </td>
                                        </tr>
                                        <tr>
                                            <td height="50px">
                                                
                                            </td>
                                        </tr>
                                    </table>
                                </s:form>
                            </div>
                        </td>
                    </tr>
                </table>
            </div>
            <!-- This Div is for Status Popup====  Start -->
                                                    
            <!-- This div is for weekly view==== Start -->
            <div id="displayWeeklyResult" style="display: none;z-index:999" >  
                <form action="" name="weeklyEvents" id="weeklyEvents" >
                    <table align="center" cellpadding="0" cellspacing="0" border="0"  width="100%">
                        <tr>
                            <td height="20px" colspan="3" class="headerText">
                                
                            </td>
                        </tr>
                        <tr>
                            <td class="fieldLabelLeft">
                                Select date For weekly Events :
                            </td>
                            <td>
                                <input type="text" name="eventDate" id="eventDate" cssClass="inputTextBlueSmall" value=""/><a href="javascript:cal1.popup();">
                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                            </td>
                            <td>
                                <input type="button" class="buttonbg" onclick="getWeeklyEvents();" value="Get Events" >
                                <input type="button" class="buttonbg" onclick="getDailyview();" value="Daily View" >
                                <input type="button" class="buttonbg" id="monthlyViewBut" onclick="getMonthlyEvents();" value="Monthly View" >
                            </td>
                        </tr>
                        <tr>
                            <td height="20px">
                                
                            </td>
                        </tr>
                        <tr >
                            <td colspan="3" class="fieldLabelLeft" id="sunday" >
                                Sunday Date
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3" height="50px" id="sundayGrid" >
                                <table id="sundayGridTable1" align="center"  
                                       cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750' >
                                    <tbody id="sundayGridTable"></tbody>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3" class="fieldLabelLeft" id="monday">
                                Monday Date
                            </td>
                        </tr>
                        <tr>
                            <td height="50px" colspan="3" id="mondayGrid">
                                <table id="mondayGridTable1" align="center"  
                                       cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750' >
                                    <tbody id="mondayGridTable"></tbody>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td class="fieldLabelLeft" colspan="3" id="tuesday">
                                TuesDay Date
                            </td>
                        </tr>
                        <tr>
                            <td height="50px" colspan="3" id="tuesdayGrid">
                                <table id="tuesdayGridTable1" align="center"  
                                       cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750' >
                                    <tbody id="tuesdayGridTable"></tbody>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td class="fieldLabelLeft" colspan="3" id="wednesday">
                                Wednesday Date
                            </td>
                        </tr>
                        <tr>
                            <td height="50px" id="wednesdayGrid" colspan="3">
                                <table id="wednesdayGridTable1" align="center"  
                                       cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750' >
                                    <tbody id="wednesdayGridTable"></tbody>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td class="fieldLabelLeft" id="thursday" colspan="3">
                                Thursday Date
                            </td>
                        </tr>
                        <tr>
                            <td height="50px" id="thursdayGrid" colspan="3">
                                <table id="thursdayGridTable1" align="center"  
                                       cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750' >
                                    <tbody id="thursdayGridTable"></tbody>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td class="fieldLabelLeft" id="friday" colspan="3">
                                Friday Date
                            </td>
                        </tr>
                        <tr>
                            <td height="50px" id="fridayGrid" colspan="3">
                                <table id="fridayGridTable1" align="center"  
                                       cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750' >
                                    <tbody id="fridayGridTable" ></tbody>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3" class="fieldLabelLeft" id="saturday">
                                Saturday Date
                            </td>
                        </tr>
                        <tr>
                            <td height="50px" colspan="3" id="saturdayGrid">
                                <table id="saturdayGridTable1" align="center"  
                                       cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750' >
                                    <tbody id="saturdayGridTable"></tbody>
                                </table>
                            </td>
                        </tr>
                        <script type="text/JavaScript">
                                                                    var cal1 = new CalendarTime(document.forms['weeklyEvents'].elements['eventDate']);
                                                                    cal1.year_scroll = true;
                                                                    cal1.time_comp = false;
                        </script> 
                    </table>
                </form>
            </div>
            <!-- This div is for weekly view==== End -->
                                                    
            <!-- This Div is for Update Event as well as add Event==== End -->
                                                    
        </td>
    </tr>
</table>

</sx:div >
<!--//END TAB : -->
<%--
<s:if test="%{currentAction != 'AccessCalendar'}" >
    <sx:div  id="calendarUsers" label="Calendar Users" cssStyle="overflow: auto;" >
        <table cellpadding="0" cellspacing="0" width="100%" border="0">
            <tr>
                <td> 
                    <!-- This Div is for Update Event as well as add Event==== End -->
                    <div id="loadMessage2" style="display: none" align="center" class="error" >
                        <b>Loading.... Please Wait....</b>
                    </div>
                    <div>
                        <form action="" id="myCalUsers" name="myCalUsers"  >
                            <table align="center" cellpadding="0" cellspacing="0" width="100%" border="0" >
                                <tr>
                                    <td height="20px" colspan="3" class="headerText" >
                                        
                                    </td>
                                </tr>
                                <tr>
                                    <td height="40px" id="todayEventsId" class="fieldLabelLeft" >
                                        
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="3" >
                                        <table id="calendarAccess1" align="center" 
                                               cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750'>
                                            <tbody id="calendarAccess"></tbody>
                                            
                                        </table>
                                    </td>
                                </tr>
                            </table>
                            <input type="hidden" value="" id="accessCalUserCheck"  name="accessCalUserCheck">
                        </form>
                    </div>
                    <div>
                        <table align="center" cellpadding="0" cellspacing="0" width="750" border="0" >
                            <tr>
                                <td align="center">
                                    <input align="middle" type="button" class="buttonBg" value="Add User" onclick="addUserToList()" >
                                </td>
                            </tr>
                        </table>
                    </div>
                </td>
            </tr>
        </table>
        <div align="center" style="display:none; position: absolute;top: 50px; left: 150px;z-index: 1000;" id="popupAddUser">
            <table align="center" cellpadding="0" style="background-image:url('/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/PopUp_Blue_500x250.gif'); background-repeat: no-repeat;" cellspacing="0" width="500px" border="0">
                <tr>
                    <td height="8px">
                        
                    </td>
                </tr>
                <tr>
                    <td align="center">
                        <img class="dragme" align="center" width="477px" height="30px" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/PopUp_head3.gif">
                    </td>
                </tr>
                <tr>
                    <td align="center" >
                        <div align="center">
                            <form name="calUserDetails" id="calUserDetails" action="">
                                <table align="center" border="0" width="63%" >
                                    <tr>
                                        <td height="15px"></td> 
                                    </tr>
                                    <tr>
                                        <td colspan="2" align="right" class="fieldLabel" ><a name="close" style="cursor: pointer;" href="#" onclick="document.getElementById('popupAddUser').style.display='none'">close[x]</a></td>
                                    </tr>
                                    <tr>
                                        <td height="7px">
                                            
                                        </td>
                                    </tr>
                                    <tr  style="z-index: 9">
                                        <td class="fieldLabelLeft">
                                            First Name:
                                        </td>
                                        <td>
                                            <input type="text" onkeyup="fillUser()" style="z-index: 10" class="inputTextBlueLarge" id="accessUserFName">
                                            <div id="validationMessageAcc" class="fieldLabelLeft"></div>
                                            <input type="hidden" value="" id="accessUserId">
                                            <input type="hidden" value="" id="accessId">
                                            <input type="hidden" value="" id="testId">
                                            <div id="userListpopUp" style="display:none;z-index: 502" >
                                                <table id="completeTable3" border="1" bordercolor="#e5e4f2" style="position:absolute;z-index: 501;border: 1px dashed black;" cellpadding="0" class="cellBorder" cellspacing="0" ></table>
                                            </div>
                                        </td>
                                        <!--<td class="fieldLabelLeft">
                                                                        Last Name:
                                                                    </td>-->
                                    </tr>
                                    <!--<tr>
                                                                    <td><input type="text" onkeyup="fillUser()" class="inputTextBlue" id="accessUserFName"></td>
                                                                    <td><input type="text" class="inputTextBlue" onkeyup="fillUser()" id="accessUserLName"></td>
                                                                </tr>-->
                                    <tr style="z-index: 8">
                                        <td  class="fieldLabelLeft">
                                            Read Access
                                        </td>
                                        <td style="z-index: 7" class="fieldLabelLeft">
                                            Read/Write Access
                                        </td>
                                    </tr>
                                    <tr style="z-index: 8">
                                        <td>
                                            <input checked value="R" id="accessType" name="accessType" type="radio" >
                                        </td>
                                        <td>
                                            <input value="RW" id="accessType" name="accessType" type="radio" >
                                        </td>
                                    </tr>
                                    <tr>
                                        <td height="20px">
                                            
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" align="center">
                                            <input disabled id="addUserBtn" style="z-index: 4" onclick="saveCalAccessUser()"  type="button" class="buttonBg" value="save" >
                                        </td>
                                    </tr>
                                    <tr>
                                        <td height="50px">
                                            
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
        
    </sx:div>
</s:if>--%>
<s:if test="%{currentAction != 'AccessCalendar' && currentTeamAction != 'AccessTeamCalendar'}">
    <sx:div name="allActivities" label="All Activities" cssStyle="overflow:auto;">
        <%
        
        try{
            String queryString="";
            /* Getting DataSource using Service Locator */
            connection = ConnectionProvider.getInstance().getConnection();
            
            
            /* String Variable for storing current position of records in dbgrid*/
            strTmp = request.getParameter("txtCurr");
            intCurr = 1;
            if (strTmp != null)
                intCurr = Integer.parseInt(strTmp);
            
            /* Specifing Shorting Column */
            strSortCol = request.getParameter("txtSortCol");
            
            if (strSortCol == null) strSortCol = "EventCreatedDate";
            
            strSortOrd = request.getParameter("txtSortAsc");
            if (strSortOrd == null) strSortOrd = "ASC";
            String empId = session.getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
            queryString = "select * from tblCrmCalendar where EmpId ='"+empId+"' ORDER BY EventCreatedDate";
            /* Sql query for retrieving resultset from DataBase */
            //out.println(queryString);
        %>
        <s:form action="" name="frmDBGrid" theme="simple"> 
            <div style="width=1000px;">
                <table cellpadding="0" border="0" cellspacing="0" width="100%">
                    <tr>
                        <td height="20px" colspan="3" class="headerText" >
                            
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <!-- DataGrid for list all Attachments -->
                            <grd:dbgrid id="tblCrmCalendar" name="tblCrmCalendar" width="100" pageSize="18" 
                                        currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                        dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                
                                <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                               imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                               addImage="../../includes/images/DBGrid/Add.png"/>
                                <grd:rownumcolumn headerText="SNo" width="2" HAlign="center"/>
                                
                                <grd:textcolumn dataField="EventType"  headerText="Event Type"   width="10" sortable="true"/> 
                                
                                <grd:textcolumn dataField="Description"  headerText="Description" width="30" sortable="true"/>  
                                <grd:datecolumn dataField="EventCreatedDate"  headerText="Event Start Time"  dataFormat="MM-dd-yyyy HH:mm:SS" width="15" sortable="true"/>
                                <grd:datecolumn dataField="EventEndDate"  headerText="Event End Time"  dataFormat="MM-dd-yyyy HH:mm:SS" width="15" sortable="true"/>
                            </grd:dbgrid>
                            <!-- these components are DBGrid Specific  -->
                            <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                            <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                            <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                            
                        </td>
                    </tr>
                </table>    
            </div>
        </s:form>  
        <%
        connection.close();
        connection = null;
        /* For Exception handling mechanism */
        }catch(Exception se){
            se.printStackTrace();
        }finally{
            if(connection!= null){
                connection.close();
                connection = null;
            }
            if(session!=null){
                session.removeAttribute("resultMessage");
            }
        }
        %>
    </sx:div>
</s:if>
</sx:tabbedpanel>
<!--//END TABBED PANNEL : -->
</td>
<!--//END DATA COLUMN : Coloumn for Screen Content-->
</tr>
</table>
</td>
</tr>



<!--//END DATA RECORD : Record for LeftMenu and Body Content-->

<!--//START FOOTER : Record for Footer Background and Content-->
<tr class="footerBg">
    <td align="center"><s:include value="/includes/template/Footer.jsp"/>   </td>
</tr>
<!--//END FOOTER : Record for Footer Background and Content-->

</table>
<!--//END MAIN TABLE : Table for template Structure-->
        

<div id="editEvent" align="center" style="background-image: url(/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/popUP1.jpg);height:235;position: absolute;z-index:1000;display: none;overflow: auto;background-color: #ACCFF2;">
    <s:form name="addEventForm" id="addEventForm" action="" theme="simple" >
        <s:hidden name="calEveId" id="calEveId" cssClass="inputTextBlue" value=""/>
        <table width="760" cellspacing="1"  cellpadding="1" >
        <tr>
            <td height="20px" colspan="2" align="right"><span style="color:white;cursor:pointer;" class="fieldLabel" onclick="getDailyview()" >close[x]&nbsp;&nbsp;&nbsp;&nbsp;</span></td>
        </tr>
        <tr>
        <td width="30" ></td>
        <td>
        <table cellpadding="1" cellspacing="1" border="0" width="700">
            <input type="hidden" name="eventId" id="eventId" value="" >
            <tr align="right">
                <!--<td class="headerText">
                                <input type="button" class="buttonbg" onclick="backtoWeeklyView();" value="Back To Weekly View" >
                            </td>
                            <td class="headerText">
                                <input type="button" class="buttonbg" onclick="getDailyview();" value="Back To Daily View" >
                            </td>
                            <td class="headerText">
                                <input type="button" class="buttonbg" id="monthlyViewBut" onclick="getMonthlyEvents();" value="Back To Monthly View" >
                            </td>-->
                <s:if test ="%{currentAction != 'AccessCalendar'}">
                    <td colspan="4">
                        <input type="button" id="saveEventButton" class="buttonBg" value="Save" onclick="save();"/>
                    </td>
                </s:if>
                <s:if test ="%{currentAction == 'AccessCalendar'}">
                    
                    <td>
                        <div style="display:none" id="access1" ><input type="button" id="saveEventButton" class="buttonBg" value="Save" onclick="save();"/></div>
                    </td>
                </s:if>
            </tr>
            <tr>
                <td height="10px" colspan="4">
                    
                </td>
            </tr>
            <tr>
                <td class="fieldLabel"> Subject:</td>   <!--eventTypeList-->
                <td colspan="3" ><s:textfield name="eventType"
                                                  id="eventType"
                                                  value=""
                                                  cssStyle="width:550px" 
                                              cssClass="inputTextBlueAddressEmp"/></td>
            </tr>
            <tr>
                <td class="fieldLabel">Start Date:</td>
                <td><s:textfield name="eventAddDate"  id="eventAddDate" cssClass="inputTextBlue" value="" /><a href="javascript:cal2.popup();">
                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                </td>
                <td class="fieldLabel">Start Time:(HH:MM:SS)</td>
                <td><s:textfield name="eventStartTime"  id="eventStartTime" onfocus="openTimePicker(event,'popUpTime')" cssClass="inputTextBlue" value="" />
                    
                </td>
            </tr>
            <tr>
                <td class="fieldLabel">End Date:</td>
                <td><s:textfield name="eventEndDate"  id="eventEndDate" cssClass="inputTextBlue" value="" /><a href="javascript:cal4.popup();">
                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                </td>
                <td class="fieldLabel">Start Time:(HH:MM:SS)</td>
                <td><s:textfield name="eventEndTime"  id="eventEndTime" onfocus="openTimePicker(event,'popUpTime')" cssClass="inputTextBlue" value="" />
                </td>
                <%--<td class="fieldLabel">Day Status:</td>
                                                                    <td>
                                                                        <s:select name="dayStatus" id="dayStatus" list="{'Time Off','Vacation','On Travel','Busy In Meeting'}" headerKey="-please select-" headerValue="-please select-" value="" cssClass="inputSelect"/>
                                                                    </td>--%>
            </tr>
            
            <%--<tr>
                <td colspan="4" >
                <div id="checkTeamCalAdd" style="display: block" >
                    <table cellpadding="1"  cellspacing="1" border="0">
                        <tr>
                            <td width="150px" class="fieldLabel">
                                Add To Team Calendar
                            </td>
                            
                            <td>
                                <s:checkbox id="checkTeamAdd" onclick="displayTeamCalAdd();" name="checkTeamAdd" value="false" fieldValue="true" theme="simple" />
                            </td>
                        </tr>
                    </table>
                </div>
                <div id="teamCalAdd1" style="display:none">
                    <table cellpadding="1"  cellspacing="3" border="0" width="100%">
                        <tr>
                            <td width="170px"  class="fieldLabel">Account Name:</td>
                            <td width="170px" ><s:textfield name="accName" id="accName" cssClass="inputTextBlue" value="" onkeyup="fillAccount(),clearTable();" />
                                <div style="z-index:9000; display: none; position: absolute; overflow: auto;" id="menu-popup">
                                    <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed black;opacity: .8;" cellpadding="0" class="cellBorder" cellspacing="0" ></table>
                                </div>
                                <div id="validationMessage"></div>
                                <s:hidden name="accountId" id="accountId" value="" cssClass="inputTextBlue"/> 
                            </td>
                            
                            <td width="150px" class="fieldLabel"> Contact Name:  </td>
                            <td><s:textfield name="contactName" id="contactName" size="15" onclick="fillContact(event);" cssClass="inputTextBlue" theme="simple"  value=""/>
                                <div id="validationMessage"></div>
                                <div style="z-index:5; display: none; position: absolute; overflow: auto;" id="menu-popup1">
                                    <table id="completeTable1" border="1" bordercolor="#e5e4f2" style="border: 1px dashed black;opacity: .8;" cellpadding="0" class="cellBorder" cellspacing="0"></table>
                                </div>
                                <s:hidden name="contactsId" id="contactsId" cssClass="inputTextBlue" value=""/>                                                                     
                                <s:hidden name="getAccountId" id="getAccountId" cssClass="inputTextBlue" value=""/>
                                <s:hidden name="calEveId" id="calEveId" cssClass="inputTextBlue" value=""/>
                            </td>  
                            
                        </tr>
                        
                        <tr>
                            <td class="fieldLabel">Team Members :</td>
                            <td><s:select list="{}" name="accTeamMembers" id="accTeamMembers" multiple="true" cssClass="inputSelectLarge" /></td>
                            <%--<td class="fieldLabel">
                                                                                    Don't Add :
                                                                                </td>
                                                                                <td align="left">
                                                                                    <s:checkbox id="checkTeamAdd1" onclick="dontDisplayTeamCalAdd();" name="checkTeamAdd1" value="false" fieldValue="true" theme="simple" />
                                                                                </td>
                        </tr>
                    </table>
                </div>
                
            </tr>--%>
            
            <tr>
                <td class="fieldLabel"> Description: </td>
                <td colspan="4">  <s:textarea name="description" id="description" cssClass="inputTextarea" cols="85" rows="3"/>  </td>
            </tr>                                                       
            <s:hidden name="lastModifiedBy" id="lastModifiedBy" cssClass="inputTextBlue" value=""/>
            <s:hidden name="createdBy" id="createdBy" cssClass="inputTextBlue" value=""/>
            
            <%--
                                                <tr>                                                                  
                                                    <td  class="fieldLabel">Send Request:</td> 
                                                    <td><s:checkbox  name="sendRequest" value="false" fieldValue="true" theme="simple" onclick="hideLoad();"/></td>
                                                    
                                                    <td  class="fieldLabel">RemindMe:</td> 
                                                    <td><s:checkbox  name="remindMe" value="false" fieldValue="true" theme="simple"/></td>
                                                </tr>
                                                
                                                <tr>                                                  
                                                    <td colspan="4" style="padding-left:128px;">
                                                        <table id="addlabel1" style="display:none;" cellpadding="1"  cellspacing="1" border="0" width="100%">
                                                            <tr>
                                                                <td class="fieldLabel" >Assign Members: </td>
                                                                <td colspan="3"><s:optiontransferselect
                                                                        label="Favourite Cartoons Characters"
                                                                        name="leftSideCartoonCharacters"
                                                                        list="{'Popeye', 'He-Man', 'Spiderman'}"
                                                                        doubleName="rightSideCartoonCharacters"
                                                                    doubleList="{'Superman', 'Mickey Mouse', 'Donald Duck'}"/></td>    
                                                            </tr>
                                                        </table>
                                                    </td>
                                                </tr> 
                                                --%>
           
            
        </table> 
    </s:form>
    </td>
    </tr>
    </table>
    
</div>
<div id="popUpTime" style="display:none; position:absolute;z-index:10001"></div>
<script type="text/javascript">
		$(window).load(function(){
         checkAccessCalendarAction()
		});
		</script>

<script type="text/JavaScript">
                var cal2 = new CalendarTime(document.forms['addEventForm'].elements['eventAddDate']);
                cal2.year_scroll = true;
                cal2.time_comp = false;
                
                var cal4 = new CalendarTime(document.forms['addEventForm'].elements['eventEndDate']);
                cal4.year_scroll = true;
                cal4.time_comp = false;
                
                var cal5 = new CalendarTime(document.forms['statusDetails'].elements['sdate']);
                cal5.year_scroll = true;
                cal5.time_comp = false;
</script>

</body>
</html>


