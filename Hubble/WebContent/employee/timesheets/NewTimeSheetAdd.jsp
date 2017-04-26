<!--/*******************************************************************************
/*
 *Project: MirajeV2
 *
 *$Author: hkondala $
 *
 *$Date: 2009-05-01 06:00:48 $
 *
 *$Revision: 1.7 $
 *
 *$Source: /Hubble/Hubble_CVS/Hubble/web/employee/timesheets/TimeSheetAdd.jsp,v $
 *
 * @(#)TimeSheetAdd.java	September 24, 2007, 12:13 AM 
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/ -->
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.*,java.util.*" %>
<%@ page import="com.mss.mirage.employee.timesheets.TimeSheetVTO" %>
<%@ page import="com.freeware.gridtag.*" %> 
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%-- <%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>

<html>
    <head>
        <title> Hubble Organization Portal :: TimeSheet Add </title> 
        <%--  <sx:head cache="true"/> --%>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link REL="StyleSheet" HREF="<s:url value="/includes/css/leftMenu.css"/>" type="text/css">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/helpText.css"/>"></link>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
        <script language="javascript" src="<s:url value="/includes/javascripts/NewTimeSheetWeekDays.js"/>"></script>
        <%--<script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/clientValidations/TimeSheetClientValidation.js"/>"></script>--%>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmpStandardClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/timesheets/newtimesheetValidations.js?ver=1.1"/>"></script>

        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/jquery.min.js"/>"></script> 
        <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/jquery.js"/>"></script> 
        <script src="http://code.jquery.com/jquery-1.10.2.js"></script> 
        <script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
        <script> 
            $(function() { $( document ).tooltip(); }); 
        </script> 
        <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/ajaxfileupload.js"/>"></script> 

        <s:include value="/includes/template/headerScript.html"/>
        <script type="text/JavaScript">
            function setTempVar1() {
                document.frmAddTimeSheet.tempVar.value = 1;
                
            }
            function setTempVar2() {
                document.frmAddTimeSheet.tempVar.value = 2;
            }
            
        </script>
    </head>
    <%-- <s:if test="%{associatedProjectsCount <= 1}">
         <body class="bodyGeneral" oncontextmenu="return false;" onload="checkDates();">
         </s:if><s:else> --%>
    <%--  <body class="bodyGeneral" oncontextmenu="return false;" onload="checkDates();getProjectsDetails();"> --%>
  <!--  <body class="bodyGeneral" oncontextmenu="return false;" onload="getProjectsDetails();showAttachmentFun();displayBiometricHrs();"> -->
    <body class="bodyGeneral" oncontextmenu="return false;">
        <%--     </s:else> --%>

        <%--  <s:hidden name="empID" id="empID" value="%{empID}"/> --%>
        <s:hidden name="iflag" id="iflag" value="%{iflag}"/>
        <s:hidden name="proj1Name" id="proj1Name" value="%{timeSheetVTO.proj1Name}"/>
        <s:hidden name="proj2Name" id="proj2Name" value="%{timeSheetVTO.proj2Name}"/>
        <s:hidden name="proj3Name" id="proj3Name" value="%{timeSheetVTO.proj3Name}"/>
        <s:hidden name="proj4Name" id="proj4Name" value="%{timeSheetVTO.proj4Name}"/>
        <s:hidden name="proj5Name" id="proj5Name" value="%{timeSheetVTO.proj5Name}"/>
        <s:hidden name="associatedProjectsCount" id="associatedProjectsCount" value="%{associatedProjectsCount}"/>

        <s:hidden name="employeeLocation" id="employeeLocation" value="%{#session.sessionEmpLocation}"/>
        <s:hidden name="empType" id="empType" value="%{#session.empType}"/>
        <table class="templateTable1000x580" align="center" cellpadding="0" cellspacing="0" border="0">
            <tr class="headerBg">
            <td valign="top">
                <s:include value="/includes/template/Header.jsp"/>                         
            </td>
        </tr>
        <tr>
        <td>
            <table class="innerTable1000x515" cellpadding="0" cellspacing="0" border="0">
                <tr>
                <td width="150px;" class="leftMenuBgColor" valign="top">
                    <s:include value="/includes/template/LeftMenu.jsp"/>                    
                </td>
                <td width="850px" class="cellBorder" valign="top" style="padding: 5px 5px;">
                    <ul id="accountTabs" class="shadetabs" >
                        <s:if test="%{timeSheetVTO.modeType == 'newAddTimeSheet'}">
                            <li ><a href="#" class="selected" rel="timeSheetsAddTab" >Add Time Sheet</a></li>
                        </s:if>
                            <s:elseif test="%{timeSheetVTO.modeType == 'NewEditTimesheet'}">
                                <li ><a href="#" class="selected" rel="timeSheetsAddTab" >Edit Time Sheet</a></li>
                            </s:elseif>
                        
                    </ul>
                    <div  style="border:1px solid gray; width:820px;overflow:auto; margin-bottom: 1em;">
                        <%-- <sx:tabbedpanel  id="timeSheetsAddPannel" cssStyle="width: 820px; padding: 5px 5px;" doLayout="false"> --%>

                        <%-- <sx:div id="timeSheetsAddTab" label="%{timeSheetVTO.modeType}" cssStyle="overflow:auto;"> --%>
                        <div id="timeSheetsAddTab" class="tabcontent"  >    


                            <!-- overlaystart -->
                            <div id="overlay"></div> 
                            <div id="specialBox">
                                <s:form theme="simple"  align="center" >

                                    <s:hidden id="timeSheetSecStat" name="timeSheetSecStat"  value="%{timeSheetSecStat}" /> 
                                    <s:hidden id="timeSheetStat" name="timeSheetStat"  value="%{timeSheetStat}" /> 

                                    <s:hidden id="fileFlagValue" name="fileFlagValue"  value="%{fileFlagValue}" /> 
                                    <table align="center" border="0" cellspacing="0" style="width:100%;">
                                        <tr>                               
                                        <td colspan="2" style="background-color: #288AD1" >
                                            <h3 style="color:darkblue;" align="left">
                                                <span id="headerLabel"></span>


                                            </h3></td>
                                        <td colspan="2" style="background-color: #288AD1" align="right">

                                            <a href="#" onmousedown="toggleCloseTimeSheetAttachmentOverlay()" >
                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> 

                                            </a>  

                                        </td></tr>
                                        <tr>
                                        <td colspan="4">
                                            <div id="load" style="color: green;display: none;">Loading..</div>
                                            <div id="resultMessage"></div>
                                        </td>
                                        </tr>    
                                        <tr><td colspan="4">
                                            <table style="width:80%;" align="center">
                                                <tr id="uploadTr" > 
                                                <td class="fieldLabel"><font color="red" style="font-weight: bold;">*</font>Upload File :</td>
                                                <td colspan="2" ><s:file name="file" label="file" id="file" onchange="timeSheetFileValidation();"/></td> 
                                                <td  align="center" >
                                                    <input type="button" value="Upload" onclick="return timeSheetAttachmentUpload();" class="buttonBg" id="addButton" /> 
                                                    <%-- <s:submit cssClass="buttonBg"  align="right" id="save" value="Save" /> --%>
                                                    <s:reset cssClass="buttonBg"  align="right" value="Reset" />

                                                </td>
                                                </tr>
                                            </table>
                                        </td>
                                        </tr>
                                    </table>
                                </s:form>    
                            </div>
                            <!-- overlayend -->
                            <s:form name="frmAddTimeSheet" action="%{timeSheetVTO.ModeType}" theme="simple">
                                <table cellpadding="0" border="0" cellspacing="0" width="100%">
                                    <tr>
                                        <s:hidden  cssClass="inputTextBlue" value="%{timeSheetVTO.ModeType}" readonly="true"/>      
                                        <s:hidden  cssClass="inputTextBlue" value="%{projectId}" readonly="true"/>    
                                        <s:hidden id="leaveDates" name="leaveDates" value="%{leaveDates}"/>
                                    </tr>
                                    <tr>
                                    <td class="headerText">
                                        <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                    </td>
                                    </tr>
                                    <tr>
                                    <td>
                                        <table width="80%" border="0" cellspacing="0" cellpadding="0"  align="center">      
                                            <tr> 
                                            <td width="19%"> </td>
                                            <td width="27%"> </td>
                                            <td width="30%"> </td>
                                            <td width="24%"> </td>
                                            </tr>
                                            <tr class="fieldLabel"> 
                                            <td width="19%" height="22">Employee Name&nbsp;:</td>

                                            <td width="27%" height="22" align="left" colspan="4"><font color="green" size="2px;"><s:property value="%{empName}"/></font>

                                            </td>
                                            </tr>
                                            <tr> 
                                            <td width="30%" height="22" class="fieldLabel">Week Start Date&nbsp;:</td>
                                            <td width="24%" height="22"><s:textfield  id="wstDate" name="wstDate" readonly="true" value="%{timeSheetVTO.wstDate}" cssClass="inputTextBlue"  onblur="calltsheet();"/></td>
                                            <td width="30%" height="22" class="fieldLabel">Week End Date&nbsp;:</td>
                                            <td width="24%" height="22"><s:textfield  id="wenDate" name="wenDate" value="%{timeSheetVTO.wenDate}" cssClass="inputTextBlue" readonly="true" /></td>
                                            </tr>
                                            <tr>
                                                <s:if test="%{timeSheetStat == 'Approve'}">
                                                <td width="19%" height="22" class="fieldLabel">Date Approved&nbsp;:</td>
                                                <td width="27%" height="22"><s:textfield id="approveDate" cssClass="inputTextBlue" value="%{timeSheetVTO.approveDate}" name="approveDate" readonly="true"/>
                                                    <!-- <a href="javascript:cal1.popup();">
                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                     width="20" height="20" border="0" ></a>-->
                                                </td>
                                            </s:if>
                                            <s:else>
                                                <!--<td width="19%" height="22" class="fieldLabel">Date Approved :</td>-->
                                                <s:hidden id="approveDate" cssClass="inputTextBlue" value="%{timeSheetVTO.approveDate}" name="approveDate" onchange="check();" />
                                                <%--<a href="javascript:cal1.popup();">
                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                     width="20" height="20" border="0" ></a>--%>

                                            </s:else>
                                            <td width="30%" height="22" class="fieldLabel">Date Submitted&nbsp;:</td>
                                            <td width="24%" height="22"><s:textfield id="submittedDate" name="submittedDate" readonly="true" cssClass="inputTextBlue" value="%{timeSheetVTO.submittedDate}" />
                                                <%-- used for updation purpose --%>
                                                <s:hidden id="empID" name="empID"  value="%{empID}" /> 
                                                <s:hidden id="timeSheetID" name="timeSheetID"  value="%{timeSheetVTO.timeSheetID}" /> 
                                                <s:hidden name="priProjId" id="priProjId" value="%{timeSheetVTO.priProjId}"/>
                                            </td>
                                            </tr>
                                        </table><br>
                                        <div id="loadingMessage" style="display:none;" class="error" align="center" ><b><font size="2px" >Loading...Please Wait</font></b></div>
                                        <div id="timeSheetDetails" style="display:none">
                                            <table width="90%" border="0" cellspacing="0" cellpadding="0"   align="center">
                                                <tr class="headerTextNormal">
                                                <td width="20%"></td>
                                                <td width="9%" class="fieldLabel" ><s:hidden id="weekDate1" name="weekDate1" value="%{timeSheetVTO.weekDate1}" /><B><s:property value="%{timeSheetVTO.WeekDate1}" /></B></td>
                                                <td width="7%" class="fieldLabel" ><s:hidden id="weekDate2" name="weekDate2"  value="%{timeSheetVTO.weekDate2}" /><B><s:property value="%{timeSheetVTO.WeekDate2}" /></B></td>
                                                <td width="8%" class="fieldLabel" ><s:hidden id="weekDate3" name="weekDate3"  value="%{timeSheetVTO.weekDate3}" /><B><s:property value="%{timeSheetVTO.WeekDate3}" /></B></td>
                                                <td width="8%" class="fieldLabel" ><s:hidden id="weekDate4" name="weekDate4"  value="%{timeSheetVTO.weekDate4}" /><B><s:property value="%{timeSheetVTO.WeekDate4}" /></B></td>
                                                <td width="8%" class="fieldLabel"><s:hidden id="weekDate5" name="weekDate5"  value="%{timeSheetVTO.weekDate5}"/><B><s:property value="%{timeSheetVTO.WeekDate5}" /></B></td>
                                                <td width="7%"class="fieldLabel" ><s:hidden id="weekDate6" name="weekDate6"  value="%{timeSheetVTO.weekDate6}"/><B><s:property value="%{timeSheetVTO.WeekDate6}" /></B></td>
                                                <td width="7%"class="fieldLabel" ><s:hidden id="weekDate7" name="weekDate7"  value="%{timeSheetVTO.weekDate7}"/><B><s:property value="%{timeSheetVTO.WeekDate7}" /></B></td>
                                                <td width="26%"></td>
                                                </tr>
                                                <tr>
                                                <td width="20%" class="headerTextNormal" align="right"><B>Projects</B></td>
                                                <td width="6%" class="headerTextNormal" align="center"><B>SUN</B></td>
                                                <td width="8%" class="headerTextNormal" align="center"><B>MON</B></td>
                                                <td width="6%" class="headerTextNormal" align="center"><B>TUE</B></td>
                                                <td width="7%" class="headerTextNormal" align="center"><B>WED</B></td>
                                                <td width="7%" class="headerTextNormal" align="center"><B>THU</B></td>
                                                <td width="8%" class="headerTextNormal" align="center"><B>FRI</B></td>
                                                <td width="6%" class="headerTextNormal" align="center"><B>SAT</B></td>
                                                <td width="8%" class="headerTextNormal" align="center"><B>TOTAL</B></td>
                                                </tr>
                                                <tr style="display:none" id ="project1" align="center">

                                                    <%--  <td width="40%" class="fieldLabel">Project1:</td> --%>
                                                    <%--  <s:if test='#session.empType == "e"'>
                                                          <s:if test="%{associatedProjectsCount == 0}">
                                                        <td width="40%" class="fieldLabel">Project1:</td>
                                                          </s:if><s:else>
                                                              <td width="40%" class="fieldLabel"><span id="proj1Label"></span></td>
                                                          </s:else>
                                                       </s:if><s:else>
                                                           <td width="40%" class="fieldLabel">Project(N):</td>
                                                       </s:else> --%>
                                               <td width="40%" class="fieldLabel"><span id="proj1Label"></span><s:hidden name="proj1Id" id="proj1Id" value="%{timeSheetVTO.proj1Id}"/></td>
                                                <td width="8%" ><s:textfield id="proj1Sun" name="proj1Sun" onchange="checkBeforeSubmit();computeProj1(this);getAlertOnLeave(this,weekDate1);"  value="%{timeSheetVTO.proj1Sun}"  cssClass="inputTextHours" /></td>
                                                <td width="8%" ><s:textfield  id="proj1Mon" name="proj1Mon"  onchange="computeProj1(this);getAlertOnLeave(this,weekDate2);"  value="%{timeSheetVTO.proj1Mon}"  cssClass="inputTextHours" /></td>
                                                <td width="8%" ><s:textfield  id="proj1Tus" name="proj1Tus"  onchange="computeProj1(this);getAlertOnLeave(this,weekDate3);"  value="%{timeSheetVTO.proj1Tus}"  cssClass="inputTextHours" /></td>
                                                <td width="8%" ><s:textfield  id="proj1Wed" name="proj1Wed"  onchange="computeProj1(this);getAlertOnLeave(this,weekDate4);"  value="%{timeSheetVTO.proj1Wed}"  cssClass="inputTextHours" /></td>
                                                <td width="8%" ><s:textfield  id="proj1Thur" name="proj1Thur" onchange="computeProj1(this);getAlertOnLeave(this,weekDate5);" value="%{timeSheetVTO.proj1Thur}"  cssClass="inputTextHours" /></td>
                                                <td width="8%" ><s:textfield  id="proj1Fri" name="proj1Fri" onchange="computeProj1(this);getAlertOnLeave(this,weekDate6);"  value="%{timeSheetVTO.proj1Fri}"  cssClass="inputTextHours" /></td>
                                                <td width="8%" ><s:textfield  id="proj1Sat" name="proj1Sat" onchange="computeProj1(this);getAlertOnLeave(this,weekDate7);"  value="%{timeSheetVTO.proj1Sat}"  cssClass="inputTextHours"  /></td>
                                                <td width="8%" ><s:textfield readonly="true"  name="proj1TotalHrs" value="%{timeSheetVTO.proj1TotalHrs}" size="8" cssClass="inputTextHours"  /></td></tr>
                                                <tr style="display:none" id ="project2" align="center">


                                                    <%--   <td width="40%" class="fieldLabel">Project2:</td> --%>
                                                    <%--   <s:if test='#session.empType == "e"'>
                                                       <s:if test="%{associatedProjectsCount == 0}">
                                                        <td width="40%" class="fieldLabel">Project2:</td>
                                                          </s:if><s:else>
                                                              <td width="40%" class="fieldLabel"><span id="proj2Label"></span></td>
                                                          </s:else>
                                                       </s:if><s:else>
                                                           <td width="40%" class="fieldLabel">Project(N+1):</td>
                                                       </s:else> --%>
                                                <td width="40%" class="fieldLabel"><span id="proj2Label"></span><s:hidden name="proj2Id" id="proj2Id" value="%{timeSheetVTO.proj2Id}"/></td>
                                                <td width="8%" ><s:textfield id="proj2Sun" name="proj2Sun" onchange="computeProj2(this);getAlertOnLeave(this,weekDate1);"  value="%{timeSheetVTO.proj2Sun}"  cssClass="inputTextHours" /></td>
                                                <td width="8%" ><s:textfield id="proj2Mon" name="proj2Mon" onchange="computeProj2(this);getAlertOnLeave(this,weekDate2);" value="%{timeSheetVTO.proj2Mon}"  cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield id="proj2Tus" name="proj2Tus" onchange="computeProj2(this);getAlertOnLeave(this,weekDate3);" value="%{timeSheetVTO.proj2Tus}"  cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield id="proj2Wed" name="proj2Wed"  onchange="computeProj2(this);getAlertOnLeave(this,weekDate4);" value="%{timeSheetVTO.proj2Wed}"  cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield id="proj2Thur" name="proj2Thur"   onchange="computeProj2(this);getAlertOnLeave(this,weekDate5);"  value="%{timeSheetVTO.proj2Thur}"  cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield id="proj2Fri" name="proj2Fri"  onchange="computeProj2(this);getAlertOnLeave(this,weekDate6);" value="%{timeSheetVTO.proj2Fri}" cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield id="proj2Sat" name="proj2Sat"   onchange="computeProj2(this);getAlertOnLeave(this,weekDate7);" value="%{timeSheetVTO.proj2Sat}"  cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield readonly="true"  name="proj2TotalHrs" value="%{timeSheetVTO.proj2TotalHrs}" size="8" cssClass="inputTextHours" /></td></tr>

                                                <tr style="display:none" id ="project3" align="center">
                                                <td width="40%" class="fieldLabel"><span id="proj3Label"></span><s:hidden name="proj3Id" id="proj3Id" value="%{timeSheetVTO.proj3Id}"/></td>
                                                <td width="8%" ><s:textfield id="proj3Sun" name="proj3Sun" onchange="computeProj3(this);getAlertOnLeave(this,weekDate1);"  value="%{timeSheetVTO.proj3Sun}"  cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield id="proj3Mon" name="proj3Mon" onchange="computeProj3(this);getAlertOnLeave(this,weekDate2);" value="%{timeSheetVTO.proj3Mon}"  cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield id="proj3Tus" name="proj3Tus" onchange="computeProj3(this);getAlertOnLeave(this,weekDate3);" value="%{timeSheetVTO.proj3Tus}"  cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield id="proj3Wed" name="proj3Wed"  onchange="computeProj3(this);getAlertOnLeave(this,weekDate4);" value="%{timeSheetVTO.proj3Wed}"  cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield id="proj3Thur" name="proj3Thur"   onchange="computeProj3(this);getAlertOnLeave(this,weekDate5);"  value="%{timeSheetVTO.proj3Thur}"  cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield id="proj3Fri" name="proj3Fri"  onchange="computeProj3(this);getAlertOnLeave(this,weekDate6);" value="%{timeSheetVTO.proj3Fri}" cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield id="proj3Sat" name="proj3Sat"   onchange="computeProj3(this);getAlertOnLeave(this,weekDate7);" value="%{timeSheetVTO.proj3Sat}"  cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield readonly="true"  name="proj3TotalHrs" value="%{timeSheetVTO.proj3TotalHrs}" size="8" cssClass="inputTextHours" /></td>
                                                </tr>
                                               <tr style="display:none" id ="project4" align="center">
                                                <td width="40%" class="fieldLabel"><span id="proj4Label"></span><s:hidden name="proj4Id" id="proj4Id" value="%{timeSheetVTO.proj4Id}"/></td>
                                                <td width="8%" ><s:textfield id="proj4Sun" name="proj4Sun" onchange="computeProj4(this);getAlertOnLeave(this,weekDate1);"  value="%{timeSheetVTO.proj4Sun}"  cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield id="proj4Mon" name="proj4Mon" onchange="computeProj4(this);getAlertOnLeave(this,weekDate2);" value="%{timeSheetVTO.proj4Mon}"  cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield id="proj4Tus" name="proj4Tus" onchange="computeProj4(this);getAlertOnLeave(this,weekDate3);" value="%{timeSheetVTO.proj4Tus}"  cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield id="proj4Wed" name="proj4Wed"  onchange="computeProj4(this);getAlertOnLeave(this,weekDate4);" value="%{timeSheetVTO.proj4Wed}"  cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield id="proj4Thur" name="proj4Thur"   onchange="computeProj4(this);getAlertOnLeave(this,weekDate5);"  value="%{timeSheetVTO.proj4Thur}"  cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield id="proj4Fri" name="proj4Fri"  onchange="computeProj4(this);getAlertOnLeave(this,weekDate6);" value="%{timeSheetVTO.proj4Fri}" cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield id="proj4Sat" name="proj4Sat"   onchange="computeProj4(this);getAlertOnLeave(this,weekDate7);" value="%{timeSheetVTO.proj4Sat}"  cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield readonly="true"  name="proj4TotalHrs" value="%{timeSheetVTO.proj4TotalHrs}" size="8" cssClass="inputTextHours" /></td>
                                                </tr>
                                                <tr style="display:none" id ="project5" align="center">
                                                <td width="40%" class="fieldLabel"><span id="proj5Label"></span><s:hidden name="proj5Id" id="proj5Id" value="%{timeSheetVTO.proj5Id}"/></td>
                                                <td width="8%" ><s:textfield id="proj5Sun" name="proj5Sun" onchange="computeProj5(this);getAlertOnLeave(this,weekDate1);"  value="%{timeSheetVTO.proj5Sun}"  cssClass="inputTextHours" /></td>
                                                <td width="8%" ><s:textfield id="proj5Mon" name="proj5Mon" onchange="computeProj5(this);getAlertOnLeave(this,weekDate2);" value="%{timeSheetVTO.proj5Mon}"  cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield id="proj5Tus" name="proj5Tus" onchange="computeProj5(this);getAlertOnLeave(this,weekDate3);" value="%{timeSheetVTO.proj5Tus}"  cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield id="proj5Wed" name="proj5Wed"  onchange="computeProj5(this);getAlertOnLeave(this,weekDate4);" value="%{timeSheetVTO.proj5Wed}"  cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield id="proj5Thur" name="proj5Thur"   onchange="computeProj5(this);getAlertOnLeave(this,weekDate5);"  value="%{timeSheetVTO.proj5Thur}"  cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield id="proj5Fri" name="proj5Fri"  onchange="computeProj5(this);getAlertOnLeave(this,weekDate6);" value="%{timeSheetVTO.proj5Fri}" cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield id="proj5Sat" name="proj5Sat"   onchange="computeProj5(this);getAlertOnLeave(this,weekDate7);" value="%{timeSheetVTO.proj5Sat}"  cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield readonly="true"  name="proj5TotalHrs" value="%{timeSheetVTO.proj5TotalHrs}" size="8" cssClass="inputTextHours" /></td>
                                                </tr>

                                                <tr align="center">
                                                <td width="40%" class="fieldLabelText">Internal&nbsp;:</td>
                                                <td width="8%" ><s:textfield id="internalSun" name="internalSun"  onchange="computeInternal(this);getAlertOnLeave(this,weekDate1);" value="%{timeSheetVTO.internalSun}"  cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield id="internalMon" name="internalMon"  onchange="computeInternal(this);getAlertOnLeave(this,weekDate2);" value="%{timeSheetVTO.internalMon}"  cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield id="internalTus" name="internalTus"  onchange="computeInternal(this);getAlertOnLeave(this,weekDate3);" value="%{timeSheetVTO.internalTus}"  cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield id="internalWed" name="internalWed"  onchange="computeInternal(this);getAlertOnLeave(this,weekDate4);" value="%{timeSheetVTO.internalWed}"  cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield id="internalThur" name="internalThur" onchange="computeInternal(this);getAlertOnLeave(this,weekDate5);" value="%{timeSheetVTO.internalThur}"  cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield id="internalFri" name="internalFri"  onchange="computeInternal(this);getAlertOnLeave(this,weekDate6);"  value="%{timeSheetVTO.internalFri}"  cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield id="internalSat" name="internalSat"  onchange="computeInternal(this);getAlertOnLeave(this,weekDate7);"  value="%{timeSheetVTO.internalSat}"  cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield readonly="true"  name="internalTotalHrs" value="%{timeSheetVTO.internalTotalHrs}" size="8" cssClass="inputTextHours" /></td></tr>
                                                <tr align="center">
                                                <td width="40%" class="fieldLabelText">Vacation&nbsp;:</td>
                                                <td width="8%" ><s:textfield id="vacationSun" name="vacationSun"  onchange="computeVacation(this)" value="%{timeSheetVTO.vacationSun}"  cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield id="vacationMon" name="vacationMon"  onchange="computeVacation(this)" value="%{timeSheetVTO.vacationMon}"  cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield id="vacationTus" name="vacationTus"  onchange="computeVacation(this)"  value="%{timeSheetVTO.vacationTus}"  cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield id="vacationWed" name="vacationWed"  onchange="computeVacation(this)"  value="%{timeSheetVTO.vacationWed}"  cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield id="vacationThur" name="vacationThur"  onchange="computeVacation(this)"  value="%{timeSheetVTO.vacationThur}"  cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield id="vacationFri" name="vacationFri"   onchange="computeVacation(this)" value="%{timeSheetVTO.vacationFri}"  cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield id="vacationSat" name="vacationSat"   onchange="computeVacation(this)"  value="%{timeSheetVTO.vacationSat}"  cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield readonly="true"  name="vacationTotalHrs" value="%{timeSheetVTO.vacationTotalHrs}" size="8" cssClass="inputTextHours" /></td></tr>
                                                <tr align="center">
                                                <td width="40%" class="fieldLabelText">Holiday&nbsp;:</td>
                                                <td width="8%" ><s:textfield id="holiSun" name="holiSun"  onchange="computeHolidy(this);"  value="%{timeSheetVTO.holiSun}"  cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield id="holiMon" name="holiMon"  onchange="computeHolidy(this);"  value="%{timeSheetVTO.holiMon}"  cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield id="holiTus" name="holiTus"  onchange="computeHolidy(this);" value="%{timeSheetVTO.holiTus}"  cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield id="holiWed" name="holiWed"   onchange="computeHolidy(this);" value="%{timeSheetVTO.holiWed}"  cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield id="holiThur" name="holiThur" onchange="computeHolidy(this);"  value="%{timeSheetVTO.holiThur}"  cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield id="holiFri" name="holiFri"  onchange="computeHolidy(this);"  value="%{timeSheetVTO.holiFri}"  cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield id="holiSat" name="holiSat"  onchange="computeHolidy(this);"  value="%{timeSheetVTO.holiSat}"  cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield readonly="true"  name="holiTotalHrs" value="%{timeSheetVTO.holiTotalHrs}" size="8" cssClass="inputTextHours" /></td></tr>


                                               
                                                <tr align="center">
                                                <td width="40%" class="fieldLabelText">Comptime&nbsp;:</td>
                                                <td width="8%" ><s:textfield id="compSun" name="compSun"  onchange="computeComptime(this);getAlertOnLeave(this,weekDate1);"  value="%{timeSheetVTO.compSun}"  cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield id="compMon" name="compMon"  onchange="computeComptime(this);getAlertOnLeave(this,weekDate2);"  value="%{timeSheetVTO.compMon}"  cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield id="compTus" name="compTus"  onchange="computeComptime(this);getAlertOnLeave(this,weekDate3);" value="%{timeSheetVTO.compTus}"  cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield id="compWed" name="compWed"   onchange="computeComptime(this);getAlertOnLeave(this,weekDate4);" value="%{timeSheetVTO.compWed}"  cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield id="compThur" name="compThur" onchange="computeComptime(this);getAlertOnLeave(this,weekDate5);"  value="%{timeSheetVTO.compThur}"  cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield id="compFri" name="compFri"  onchange="computeComptime(this);getAlertOnLeave(this,weekDate6);"  value="%{timeSheetVTO.compFri}"  cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield id="compSat" name="compSat"  onchange="computeComptime(this);getAlertOnLeave(this,weekDate7);"  value="%{timeSheetVTO.compSat}"  cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield readonly="true"  name="compTotalHrs" value="%{timeSheetVTO.compTotalHrs}" size="8" cssClass="inputTextHours" /></td></tr>
                                                <tr align="center">
                                                <td width="40%" class="fieldLabelText">Total&nbsp;:</td>
                                                <td width="8%" ><s:textfield  name="totalSun" readonly="true" value="%{timeSheetVTO.totalSun}" cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield  name="totalMon" readonly="true" value="%{timeSheetVTO.totalMon}" cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield  name="totalTus" readonly="true" value="%{timeSheetVTO.totalTus}" cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield  name="totalWed" readonly="true" value="%{timeSheetVTO.totalWed}" cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield  name="totalThur" readonly="true" value="%{timeSheetVTO.totalThur}" cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield  name="totalFri" readonly="true" value="%{timeSheetVTO.totalFri}" cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield name="totalSat"  readonly="true" value="%{timeSheetVTO.totalSat}" cssClass="inputTextHours" /></td>
                                                <td width="8%"><s:textfield readonly="true"  name="allWeekendTotalHors" value="%{timeSheetVTO.allWeekendTotalHors}" size="8" cssClass="inputTextHours" /></td>
                                                </tr> 

                                                <tr align="center" id="biometricDailyTr" style="display: none">
                                                <td width="40%" class="fieldLabelText">Biometric&nbsp;:</td>
                                                <td width="8%" >
                                                    <s:textfield id="biometricSun" name="biometricSun"  value="%{timeSheetVTO.biometricSun}"  cssClass="inputTextHours" readonly="true"/>
                                                </td>
                                                <td width="8%" >
                                                    <s:textfield id="biometricMon" name="biometricMon"  value="%{timeSheetVTO.biometricMon}"  cssClass="inputTextHours" readonly="true"/>
                                                </td>
                                                <td width="8%" >
                                                    <s:textfield id="biometricTus" name="biometricTus"  value="%{timeSheetVTO.biometricTus}"  cssClass="inputTextHours" readonly="true"/>
                                                </td>
                                                <td width="8%" >
                                                    <s:textfield id="biometricWed" name="biometricWed"  value="%{timeSheetVTO.biometricWed}"  cssClass="inputTextHours" readonly="true"/>
                                                </td>
                                                <td width="8%" >
                                                    <s:textfield id="biometricThur" name="biometricThur"  value="%{timeSheetVTO.biometricThur}"  cssClass="inputTextHours" readonly="true"/>
                                                </td>
                                                <td width="8%" >
                                                    <s:textfield id="biometricFri" name="biometricFri"  value="%{timeSheetVTO.biometricFri}"  cssClass="inputTextHours" readonly="true"/>
                                                </td>
                                                <td width="8%" >
                                                    <s:textfield id="biometricSat" name="biometricSat"  value="%{timeSheetVTO.biometricSat}"  cssClass="inputTextHours" readonly="true"/>
                                                </td>
                                                <td width="8%" >
                                                    <s:textfield readonly="true"  name="biometricTotalHrs" value="%{timeSheetVTO.biometricTotalHrs}" size="8" cssClass="inputTextHours" />
                                                </td> 

                                                </tr> 


                                                <%--   <jsp:include page="./TimeSheetWeekDaysForm.jsp" flush="true" /> --%>
                                            </table></div>
                                        &nbsp&nbsp
                                        <table width="90%" border="0" cellspacing="0" cellpadding="0"  align="center" >
                                            <TR>
                                            <TD WIDTH=125 class="fieldLabel" >Total Billable Hrs&nbsp;:</TD>
                                            <TD Width=40 align=left ><s:textfield   readonly="true" cssClass="inputTextHours" id="totalBillHrs" name="totalBillHrs" size="5"  value="%{timeSheetVTO.totalBillHrs}" /> </TD>
                                            <TD colspan=2 rowspan=4 ALIGN="left" class="fieldLabelLeft" ><lebel class="formfield">Notes:</label><s:textarea id="txtNotes"  name="txtNotes" 
                                                        rows="4" cols="70" cssClass="inputTextarea" value="%{timeSheetVTO.notes}" placeholder="Enter the notes that related to your daily activity here..." onchange="fieldLengthValidator(this);" style="width: 507px;"></s:textarea></TD>
                                                    </TR>
                                                    <TR>
                                                    <TD WIDTH=125 class="fieldLabel" >Total Holiday Hrs&nbsp;:</TD>
                                                    <TD Width=40 align=left ><s:textfield   readonly="true" cssClass="inputTextHours" id="totalHoliHrs" name="totalHoliHrs" size="5"  value="%{timeSheetVTO.holiTotalHrs}" /> </TD>

                                                </TR>
                                                <TR>
                                                <TD WIDTH=125 class="fieldLabel" >Total Vacation Hrs&nbsp;:</TD>
                                                <TD Width=40 align=left ><s:textfield   readonly="true" cssClass="inputTextHours" id="totalVacationHrs" name="totalVacationHrs" size="5"  value="%{timeSheetVTO.vacationTotalHrs}" /> </TD>
                                                </TR>  
                                                <TR>
                                                <TD WIDTH=125 class="fieldLabel" >Total Comptime Hrs&nbsp;:</TD>
                                                <TD Width=40 align=left ><s:textfield   readonly="true" cssClass="inputTextHours" id="totalComptimeHrs" name="totalComptimeHrs" size="5"  value="%{timeSheetVTO.compTotalHrs}" /> </TD>
                                                </TR>  
                                                <TR id="biometricTotalTr" style="display: none">
                                                <TD WIDTH=125 class="fieldLabel" >Total Biometric Hrs&nbsp;:</TD>
                                                <TD Width=40 align=left >
                                                    <s:textfield   readonly="true" cssClass="inputTextHours" id="totalBiometricHrs" name="totalBiometricHrs" size="5"  value="%{timeSheetVTO.biometricTotalHrs}" />
                                                    </TR> 
                                                    </TABLE>                                                        
                                                </td>
                                                </tr>
                                        </table>    
                                        <s:if test="%{timeSheetStat == 'Enter'}">
                                            <p>
                                            <table align="center" border="0" cellpadding="1" cellspacing="1">
                                                <tr>
                                                <td align="center">
                                                    <s:hidden name="projectId" value="%{projectId}" />
                                                    <s:submit name="saveName" id="saveId" value="Save Changes" cssClass="buttonBg" onclick="setTempVar1()"/>  &nbsp;
                                                    <s:submit name="submitName" id="submitId" value="Submit" cssClass="buttonBg" onclick="setTempVar2()"/>  &nbsp;        
                                                    <%--  <s:submit cssClass="buttonBg" value="Back" onclick='document.frmAddTimeSheet.action="./TimeSheetSearch.jsp"'  /> --%>
                                                    <s:submit cssClass="buttonBg" value="Back" onclick='document.frmAddTimeSheet.action="newtimeSheet.action"'  />
                                                    <s:hidden name="tempVar" id="tempVar" value=""/> 
                                                </td>
                                                <td> <div id="attachmentTR" style="display: none;"> <s:if test="%{fileFlagValue==0}"> <a href='#' onclick="uploadTimeSheetAttachement();"><img SRC='../../includes/images/Browse.png' WIDTH=25 HEIGHT=25 BORDER=0 ALTER='' title="Upload Customer Time Sheet" style="margin-bottom: -5px;"/></a> </s:if> <s:elseif test="%{fileFlagValue==1}"> <a style='color:#C00067;' href='#' onclick='getTimeSheetAttachementDownload();'><img SRC='../../includes/images/DownloadFile.jpg' WIDTH=30 HEIGHT=30 BORDER=0 ALTER='' title="To Download Attachment click on this button" style=" margin-bottom:-5px;"/></a>  <a style='color:#C00067;' href='#' onclick='removeTimeSheetAttachement();'><img SRC='../../includes/images/FileRemove.png' WIDTH=18 HEIGHT=15 BORDER=0 ALTER='' title="To Remove Attachment click on this button"/></a> </s:elseif> </div> </td>
                                                    </tr>
                                                    <tr>
                                                    <td><font color="red" size="2px" face="arial">*Note: After submitting this time sheet you can't edit again.</font></td>
                                                    </tr>
                                                </table>
                                                </p>
                                        </s:if>              <s:elseif test="%{timeSheetStat == 'Reject' || timeSheetSecStat == 'Reject' }">
                                            <p>
                                            <table align="center" border="0" cellpadding="0" cellspacing="0">
                                                <tr>
                                                <td align="center">
                                                    <s:submit name="saveName" id="saveId" value="Save Changes" cssClass="buttonBg" onclick="setTempVar1()"/>  &nbsp;
                                                    <s:submit name="submitName" id="submitId" value="Submit" cssClass="buttonBg" onclick="setTempVar2()"/>  &nbsp;        
                                                    <%-- <s:submit cssClass="buttonBg" value="Back" onclick='document.frmAddTimeSheet.action="./TimeSheetSearch.jsp"'  /> --%>
                                                    <s:submit cssClass="buttonBg" value="Back" onclick='document.frmAddTimeSheet.action="newtimeSheet.action"'  />
                                                    <s:hidden name="tempVar" id="tempVar" value=""/>
                                                </td><td> <div id="attachmentTR" style="display: none;"> <s:if test="%{fileFlagValue==0}"> <a href='#' onclick="uploadTimeSheetAttachement();"><img SRC='../../includes/images/Browse.png' WIDTH=25 HEIGHT=25 BORDER=0 ALTER='' title="Upload Customer Time Sheet" style="margin-bottom: -5px;"/></a> </s:if> <s:elseif test="%{fileFlagValue==1}"> <a style='color:#C00067;' href='#' onclick='getTimeSheetAttachementDownload();'><img SRC='../../includes/images/DownloadFile.jpg' WIDTH=30 HEIGHT=30 BORDER=0 ALTER='' title="To Download Attachment click on this button" style=" margin-bottom:-5px;"/></a>  <a style='color:#C00067;' href='#' onclick='removeTimeSheetAttachement();'><img SRC='../../includes/images/FileRemove.png' WIDTH=18 HEIGHT=15 BORDER=0 ALTER='' title="To Remove Attachment click on this button"/></a> </s:elseif> </div> </td>
                                                    </tr>
                                                    <tr>
                                                    <td><font color="red" size="2px" face="arial">*Note: After submitting this time sheet you can't edit again.</font></td>
                                                    </tr>
                                                </table>
                                                </p>    
                                                <table align="center" border="0" cellpadding="0" cellspacing="0">
                                                    <tr class="fieldLabel">
                                                    <td align="center">                                                            
                                                        <p style="color:red;">*Rejected: &nbsp;
                                                        <s:property value="%{timeSheetVTO.comment}"/>
                                                    </p>
                                                </td>
                                                </tr>
                                            </table>
                                        </s:elseif>
                                        <s:elseif test="%{timeSheetStat == 'Submit' || timeSheetSecStat == 'Submit' }">
                                            <p>
                                            <table align="center" border="0" cellpadding="0" cellspacing="0">
                                                <tr>
                                                <td align="center">                                                              
                                                    <%--    <s:submit cssClass="buttonBg" value="Back" onclick='document.frmAddTimeSheet.action="./TimeSheetSearch.jsp"'  /><br> --%>
                                                    <s:submit cssClass="buttonBg" value="Back" onclick='document.frmAddTimeSheet.action="newtimeSheet.action"'  /><br><br>
                                                    <p class="fieldLabel">
                                                        *To Be Approved
                                                    </p>
                                                </td><td> <div id="attachmentTR" style="display: none;">  <s:if test="%{fileFlagValue==1}"> <a style='color:#C00067;' href='#' onclick='getTimeSheetAttachementDownload();'><img SRC='../../includes/images/DownloadFile.jpg' WIDTH=30 HEIGHT=30 BORDER=0 ALTER='' title="To Download Attachment click on this button" style=" margin-bottom:-5px;"/></a>   </s:if> </div> </td>
                                                    </tr>                                                        
                                                </table>
                                                </p>    
                                        </s:elseif>
                                        <s:elseif test="%{timeSheetStat == 'Approve'}">
                                            <p>
                                            <table align="center" border="0" cellpadding="0" cellspacing="0">
                                                <tr>
                                                <td align="center">                                                              
                                                    <%--    <s:submit cssClass="buttonBg" value="Back" onclick='document.frmAddTimeSheet.action="./TimeSheetSearch.jsp"'  />--%>
                                                    <s:submit cssClass="buttonBg" value="Back" onclick='document.frmAddTimeSheet.action="newtimeSheet.action"'  /><br><br>

                                                    <p style="color:green;" class="fieldLabel">
                                                        <font size="4">  *Approved</font>
                                                    </p>
                                                </td><td> <div id="attachmentTR" style="display: none;">  <s:if test="%{fileFlagValue==1}"> <a style='color:#C00067;' href='#' onclick='getTimeSheetAttachementDownload();'><img SRC='../../includes/images/DownloadFile.jpg' WIDTH=30 HEIGHT=30 BORDER=0 ALTER='' title="To Download Attachment click on this button" style=" margin-bottom:-5px;"/></a>   </s:if> </div> </td>
                                                    </tr>                                                        
                                                </table>
                                                </p>    
                                        </s:elseif>

                                        <s:else>
                                            <p>
                                            <table align="center" border="0" cellpadding="0" cellspacing="0">
                                                <tr>
                                                <td align="center">
                                                    <s:hidden name="projectId" value="%{projectId}" /> 
                                                    <s:submit name="submitName" id="submitId" value="Save" cssClass="buttonBg" onclick="checkBeforeSubmit();"  />  &nbsp;        
                                                    <%-- <s:submit cssClass="buttonBg" value="Back" onclick='document.frmAddTimeSheet.action="./TimeSheetSearch.jsp"'  /> --%>

                                                    <s:submit cssClass="buttonBg" value="Back" onclick='document.frmAddTimeSheet.action="newtimeSheet.action"'  />
                                                </td>
                                                </tr>
                                            </table>
                                            </p>
                                        </s:else>                                            
                                    </s:form>

                                    <!--<script type="text/JavaScript">
                                     var cal1 = new CalendarTime(document.forms['frmAddTimeSheet'].elements['approveDate']);
                                    cal1.year_scroll = true;
                                    cal1.time_comp = false;
                                    var cal2 = new CalendarTime(document.forms['frmAddTimeSheet'].elements['submittedDate']);
                                    cal2.year_scroll = true;
                                   cal2.time_comp = false;
                                        
                                    </script>-->
                                    <%--   </sx:div> --%>
                                    </div>

                                    <%-- </sx:tabbedpanel> --%>
                                    </div>
                                    <script type="text/javascript">

                                        var countries=new ddtabcontent("accountTabs")
                                        countries.setpersist(false)
                                        countries.setselectedClassTarget("link") //"link" or "linkparent"
                                        countries.init()

                                    </script>
                                </td>
                                </tr>
                            </table>
                            </td>
                            </tr>            
                            <tr class="footerBg">
                            <td align="center"><s:include value="/includes/template/Footer.jsp"/> </td>
                            </tr>
                            </table>
                            
                            <script type="text/javascript">
		$(window).load(function(){
		getProjectsDetails();
                showAttachmentFun();
                displayBiometricHrs();
		});
		</script>
                            </body>
                            </html>
