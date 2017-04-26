<!--/*******************************************************************************
/*
 *Project: MirajeV2
 *
 *$Author: vnukala $
 *
 *$Date: 2009-04-07 13:09:26 $
 *
 *$Revision: 1.4 $
 *
 *$Source: /Hubble/Hubble_CVS/Hubble/web/employee/timesheets/TimeSheetApprovels.jsp,v $
 *
 * @(#)TimeSheetApprovels.java	May 05, 2008, 06:13 PM 
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
        
        <title> Hubble Organization Portal :: TimeSheet Approvals </title> 
        
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link REL="StyleSheet" HREF="<s:url value="/includes/css/leftMenu.css"/>" type="text/css">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/helpText.css"/>"></link>
        <%--<script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>--%>
        <script language="javascript" src="<s:url value="/includes/javascripts/TimeSheetWeekDays.js"/>"></script>
        <%--<script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/clientValidations/TimeSheetClientValidation.js"/>"></script>--%>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmpStandardClientValidations.js"/>"></script>
         <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
         <script type="text/JavaScript" src="<s:url value="/includes/javascripts/timesheets/newtimesheetValidations.js"/>"></script>
         
          <script src="http://code.jquery.com/jquery-1.10.2.js"></script> 
        <script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
        <script> 
            $(function() { $( document ).tooltip(); }); 
        </script> 
        <s:include value="/includes/template/headerScript.html"/>
        
        <script>
            function approvedDate() {
              //   document.getElementById("approveButton").disabled = true;
       // document.getElementById("rejectButton").disabled = true;
                document.teamTimeSheetForm.approveDate.value ='<%=com.mss.mirage.util.DateUtility.getInstance().getToDayDateToView()%>';
                
  // document.getElementById("approveButton").disabled = false;
       // document.getElementById("rejectButton").disabled = false;
    
                
 
            }
                        function checkComments(element)
            {
                //alert(element.value);
                 var strResult = "";
    var strInputString = element.value;

    strInputString = strInputString.replace(/'/g, "'\'");
   // alert("strInputString------->"+strInputString);
   document.getElementById('comment').value=strInputString;
               // alert("");
            }
        </script>
        
    </head>
    
    
    
     <%--  <s:if test="%{timeSheetStat != 'Approve'}">
           <s:if test="%{(timeSheetStat == 'Submit') || (timeSheetSecStat == 'Submit')}">
        <body class="bodyGeneral"  oncontextmenu="return false;" onload="approvedDate();getProjectsDetails();showTlAttachmentFun();displayBiometricHrs();isReportsTo('<%=session.getAttribute("roleName")%>','<%=session.getAttribute("designation")%>');">
        </s:if>  <s:else>
                <body class="bodyGeneral"  oncontextmenu="return false;" onload="approvedDate();getProjectsDetails();showTlAttachmentFun();displayBiometricHrs();">
            </s:else>
    </s:if>
            <s:else>
            <body class="bodyGeneral"  oncontextmenu="return false;" onload="getProjectsDetails();showTlAttachmentFun();displayBiometricHrs();">
            </s:else> --%>
     <body class="bodyGeneral"  oncontextmenu="return false;">
        
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
                                    <li ><a href="#" class="selected" rel="timeSheetsApprovel"  >TimeSheet Approval </a></li>
                                </ul>
                                <%--   <sx:tabbedpanel  id="timeSheetsApprovelPannel" cssStyle="width: 820px;padding: 5px 5px;" doLayout="false"> --%>
                                <div  style="border:1px solid gray; width:820px;height: 550px; overflow:auto; margin-bottom: 1em;">
                                    <%-- <sx:div id="timeSheetsApprovel" label="TimeSheet Approvel"> --%>
                                    <div id="timeSheetsApprovel" class="tabcontent"  >
                                        
                <s:hidden name="iflag" id="iflag" value="%{iflag}"/>
                <s:hidden name="proj1Name" id="proj1Name" value="%{timeSheetVTO.proj1Name}"/>
                <s:hidden name="proj2Name" id="proj2Name" value="%{timeSheetVTO.proj2Name}"/>
                <s:hidden name="proj3Name" id="proj3Name" value="%{timeSheetVTO.proj3Name}"/>
                <s:hidden name="proj4Name" id="proj4Name" value="%{timeSheetVTO.proj4Name}"/>
                <s:hidden name="proj5Name" id="proj5Name" value="%{timeSheetVTO.proj5Name}"/>
                
              
                <s:hidden name="empType" id="empType" value="%{#session.empType}"/>
                <s:hidden name="employeeLocation" id="employeeLocation" value="%{employeeLocation}"/>
                  
                          
                <s:form name="teamTimeSheetForm" action="" theme="simple">
                                            <%-- <s:property value="%{TimeSheetStat}"/> --%>
                                            <table cellpadding="0" border="0" cellspacing="0" width="100%">
                                                 <tr>
                 
                                                     <td><s:hidden cssClass="inputTextBlue" name="projectId" value="%{timeSheetVTO.projectId}" readonly="true"/></td> 
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
                                                                <td width="19%" height="22">Employee Name:</td>
                                                                <td width="27%" height="22" align="left"><font size="2px" color="green">&nbsp;&nbsp;<s:property value="timeSheetVTO.empName"/></font></td>
                                                            </tr>
                                                            <tr> 
                                                                <td width="30%" height="22" class="fieldLabel">Week Start Date :</td>
                                                                <td width="24%" height="22"><s:textfield  id="wstDate" name="wstDate" readonly="true" value="%{timeSheetVTO.wstDate}" cssClass="inputTextBlue"/></td>
                                                                <td width="30%" height="22" class="fieldLabel">Week End Date :</td>
                                                                <td width="24%" height="22"><s:textfield  id="wenDate" name="wenDate" value="%{timeSheetVTO.wenDate}" cssClass="inputTextBlue" readonly="true" /></td>
                                                            </tr>
                                                            <tr> 
                                                                   <s:if test="%{#session.roleName == 'Operations' && timeSheetStat != 'Approve'}">                                                                    
                                                                    <s:hidden id="approveDate" cssClass="inputTextBlue" value="" name="approveDate"/>
                                                                </s:if>
                                                                <s:else>
                                                                    <td width="19%" height="22" class="fieldLabel">Date Approved :</td>
                                                                    <td width="27%" height="22"><s:textfield id="approveDate" cssClass="inputTextBlue" value="%{timeSheetVTO.approveDate}" name="approveDate" readonly="true"/>
                                                                        <!-- <a href="javascript:cal1.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                         width="20" height="20" border="0" ></a>-->
                                                                    </td> 
                                                                </s:else>
                                                                <td width="30%" height="22" class="fieldLabel">Date Submitted :</td>
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
                                                        <table width="90%" border="0" cellspacing="0" cellpadding="0" align="center">
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
                                                                
                                                            <%--    <s:if test='#session.empType == "e"'>
                                                                
                                                                <s:if test="%{#session.roleName == 'Operations'}">
                                                                         <td width="40%" class="fieldLabel">Project(N):</td>
                                                                     </s:if><s:else>
                                                                         <td width="40%" class="fieldLabel">Project1:</td>
                                                                     </s:else>
                                                                </s:if><s:else>
                                                                    <td width="40%" class="fieldLabel">Project(N):</td>
                                                                </s:else> --%>
                                                            
                                                            <td width="40%" class="fieldLabel"><span id="proj1Label"></span><s:hidden name="proj1Id" id="proj1Id" value="%{timeSheetVTO.proj1Id}"/></td>
                                                                <td width="8%"><s:textfield id="proj1Sun" name="proj1Sun" onchange="checkBeforeSubmit();computeProj1(this);"  value="%{timeSheetVTO.proj1Sun}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  id="proj1Mon" name="proj1Mon"  onchange="computeProj1(this);"  value="%{timeSheetVTO.proj1Mon}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  id="proj1Tus" name="proj1Tus"  onchange="computeProj1(this);"  value="%{timeSheetVTO.proj1Tus}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  id="proj1Wed" name="proj1Wed"  onchange="computeProj1(this);"  value="%{timeSheetVTO.proj1Wed}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  id="proj1Thur" name="proj1Thur" onchange="computeProj1(this);" value="%{timeSheetVTO.proj1Thur}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  id="proj1Fri" name="proj1Fri" onchange="computeProj1(this);"  value="%{timeSheetVTO.proj1Fri}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  id="proj1Sat" name="proj1Sat" onchange="computeProj1(this);"  value="%{timeSheetVTO.proj1Sat}"  cssClass="inputTextHours"  /></td>
                                                            <td width="8%"><s:textfield readonly="true"  name="proj1TotalHrs" value="%{timeSheetVTO.proj1TotalHrs}" size="8" cssClass="inputTextHours"  /></td></tr>
                                                            <tr style="display:none" id ="project2" align="center">
                                                              <%--  <s:if test='#session.empType == "e"'>
                                                                 <s:if test="%{#session.roleName == 'Operations'}">
                                                                         <td width="40%" class="fieldLabel">Project(N+1):</td>
                                                                     </s:if><s:else>
                                                                         <td width="40%" class="fieldLabel">Project2:</td>
                                                                     </s:else>
                                                                </s:if><s:else>
                                                                    <td width="40%" class="fieldLabel">Project(N+1):</td>
                                                                </s:else> --%>
                                                                <td width="40%" class="fieldLabel"><span id="proj2Label"></span><s:hidden name="proj2Id" id="proj2Id" value="%{timeSheetVTO.proj2Id}"/></td>
                                                                <td width="8%"><s:textfield  name="proj2Sun" onchange="computeProj2(this);"  value="%{timeSheetVTO.proj2Sun}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="proj2Mon" onchange="computeProj2(this);" value="%{timeSheetVTO.proj2Mon}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="proj2Tus" onchange="computeProj2(this);" value="%{timeSheetVTO.proj2Tus}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="proj2Wed"  onchange="computeProj2(this);" value="%{timeSheetVTO.proj2Wed}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="proj2Thur"   onchange="computeProj2(this);"  value="%{timeSheetVTO.proj2Thur}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="proj2Fri"  onchange="computeProj2(this);" value="%{timeSheetVTO.proj2Fri}" cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="proj2Sat"   onchange="computeProj2(this);" value="%{timeSheetVTO.proj2Sat}"  cssClass="inputTextHours" /></td>
                                                            <td width="8%"><s:textfield readonly="true"  name="proj2TotalHrs" value="%{timeSheetVTO.proj2TotalHrs}" size="8" cssClass="inputTextHours" /></td></tr>
                                                            
                                                             <tr style="display:none" id ="project3" align="center">
                                                                <td width="40%" class="fieldLabel"><span id="proj3Label"></span><s:hidden name="proj3Id" id="proj3Id" value="%{timeSheetVTO.proj3Id}"/></td>
                                                                <td width="8%"><s:textfield id="proj3Sun" name="proj3Sun" onchange="computeProj3(this);"  value="%{timeSheetVTO.proj3Sun}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield id="proj3Mon" name="proj3Mon" onchange="computeProj3(this);" value="%{timeSheetVTO.proj3Mon}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield id="proj3Tus" name="proj3Tus" onchange="computeProj3(this);" value="%{timeSheetVTO.proj3Tus}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield id="proj3Wed" name="proj3Wed"  onchange="computeProj3(this);" value="%{timeSheetVTO.proj3Wed}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield id="proj3Thur" name="proj3Thur"   onchange="computeProj3(this);"  value="%{timeSheetVTO.proj3Thur}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield id="proj3Fri" name="proj3Fri"  onchange="computeProj3(this);" value="%{timeSheetVTO.proj3Fri}" cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield id="proj3Sat" name="proj3Sat"   onchange="computeProj3(this);" value="%{timeSheetVTO.proj3Sat}"  cssClass="inputTextHours" /></td>
                                                            <td width="8%"><s:textfield readonly="true"  name="proj3TotalHrs" value="%{timeSheetVTO.proj3TotalHrs}" size="8" cssClass="inputTextHours" /></td>
                                                            </tr>
                                                            <tr style="display:none" id ="project4" align="center">
                                                                <td width="40%" class="fieldLabel"><span id="proj4Label"></span><s:hidden name="proj4Id" id="proj4Id" value="%{timeSheetVTO.proj4Id}"/></td>
                                                                <td width="8%"><s:textfield id="proj4Sun" name="proj4Sun" onchange="computeProj4(this);"  value="%{timeSheetVTO.proj4Sun}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield id="proj4Mon" name="proj4Mon" onchange="computeProj4(this);" value="%{timeSheetVTO.proj4Mon}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield id="proj4Tus" name="proj4Tus" onchange="computeProj4(this);" value="%{timeSheetVTO.proj4Tus}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield id="proj4Wed" name="proj4Wed"  onchange="computeProj4(this);" value="%{timeSheetVTO.proj4Wed}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield id="proj4Thur" name="proj4Thur"   onchange="computeProj4(this);"  value="%{timeSheetVTO.proj4Thur}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield id="proj4Fri" name="proj4Fri"  onchange="computeProj4(this);" value="%{timeSheetVTO.proj4Fri}" cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield id="proj4Sat" name="proj4Sat"   onchange="computeProj4(this);" value="%{timeSheetVTO.proj4Sat}"  cssClass="inputTextHours" /></td>
                                                            <td width="8%"><s:textfield readonly="true"  name="proj4TotalHrs" value="%{timeSheetVTO.proj4TotalHrs}" size="8" cssClass="inputTextHours" /></td>
                                                            </tr>
                                                             <tr style="display:none" id ="project5" align="center">
                                                                <td width="40%" class="fieldLabel"><span id="proj5Label"></span><s:hidden name="proj5Id" id="proj5Id" value="%{timeSheetVTO.proj5Id}"/></td>
                                                                <td width="8%"><s:textfield id="proj5Sun" name="proj5Sun" onchange="computeProj5(this);"  value="%{timeSheetVTO.proj5Sun}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield id="proj5Mon" name="proj5Mon" onchange="computeProj5(this);" value="%{timeSheetVTO.proj5Mon}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield id="proj5Tus" name="proj5Tus" onchange="computeProj5(this);" value="%{timeSheetVTO.proj5Tus}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield id="proj5Wed" name="proj5Wed"  onchange="computeProj5(this);" value="%{timeSheetVTO.proj5Wed}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield id="proj5Thur" name="proj5Thur"   onchange="computeProj5(this);"  value="%{timeSheetVTO.proj5Thur}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield id="proj5Fri" name="proj5Fri"  onchange="computeProj5(this);" value="%{timeSheetVTO.proj5Fri}" cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield id="proj5Sat" name="proj5Sat"   onchange="computeProj5(this);" value="%{timeSheetVTO.proj5Sat}"  cssClass="inputTextHours" /></td>
                                                            <td width="8%"><s:textfield readonly="true"  name="proj5TotalHrs" value="%{timeSheetVTO.proj5TotalHrs}" size="8" cssClass="inputTextHours" /></td>
                                                            </tr>
                                                            
                                                            
                                                            
                                                            <tr align="center">
                                                                <td width="40%" class="fieldLabelText">Internal:</td>
                                                                <td width="8%"><s:textfield  name="internalSun"  onchange="computeInternal(this);" value="%{timeSheetVTO.internalSun}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="internalMon"  onchange="computeInternal(this);" value="%{timeSheetVTO.internalMon}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="internalTus"  onchange="computeInternal(this);" value="%{timeSheetVTO.internalTus}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="internalWed"  onchange="computeInternal(this);" value="%{timeSheetVTO.internalWed}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="internalThur" onchange="computeInternal(this);" value="%{timeSheetVTO.internalThur}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="internalFri"  onchange="computeInternal(this);"  value="%{timeSheetVTO.internalFri}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="internalSat"  onchange="computeInternal(this);"  value="%{timeSheetVTO.internalSat}"  cssClass="inputTextHours" /></td>
                                                            <td width="8%"><s:textfield readonly="true"  name="internalTotalHrs" value="%{timeSheetVTO.internalTotalHrs}" size="8" cssClass="inputTextHours" /></td></tr>
                                                            <tr align="center">
                                                                <td width="40%" class="fieldLabelText">Vacation:</td>
                                                                <td width="8%"><s:textfield  name="vacationSun"  onchange="computeVacation(this)" value="%{timeSheetVTO.vacationSun}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="vacationMon"  onchange="computeVacation(this)" value="%{timeSheetVTO.vacationMon}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="vacationTus"  onchange="computeVacation(this)"  value="%{timeSheetVTO.vacationTus}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="vacationWed"  onchange="computeVacation(this)"  value="%{timeSheetVTO.vacationWed}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="vacationThur"  onchange="computeVacation(this)"  value="%{timeSheetVTO.vacationThur}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="vacationFri"   onchange="computeVacation(this)" value="%{timeSheetVTO.vacationFri}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="vacationSat"   onchange="computeVacation(this)"  value="%{timeSheetVTO.vacationSat}"  cssClass="inputTextHours" /></td>
                                                            <td width="8%"><s:textfield readonly="true"  name="vacationTotalHrs" value="%{timeSheetVTO.vacationTotalHrs}" size="8" cssClass="inputTextHours" /></td></tr>
                                                            <tr align="center">
                                                                <td width="40%" class="fieldLabelText">Holiday:</td>
                                                                <td width="8%"><s:textfield  name="holiSun"  onchange="computeHolidy(this);"  value="%{timeSheetVTO.holiSun}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="holiMon"  onchange="computeHolidy(this);"  value="%{timeSheetVTO.holiMon}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="holiTus"  onchange="computeHolidy(this);" value="%{timeSheetVTO.holiTus}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="holiWed"   onchange="computeHolidy(this);" value="%{timeSheetVTO.holiWed}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="holiThur" onchange="computeHolidy(this);"  value="%{timeSheetVTO.holiThur}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="holiFri"  onchange="computeHolidy(this);"  value="%{timeSheetVTO.holiFri}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="holiSat"  onchange="computeHolidy(this);"  value="%{timeSheetVTO.holiSat}"  cssClass="inputTextHours" /></td>
                                                            <td width="8%"><s:textfield readonly="true"  name="holiTotalHrs" value="%{timeSheetVTO.holiTotalHrs}" size="8" cssClass="inputTextHours" /></td></tr>
                                                            
                                                             <tr align="center">
                                                                <td width="40%" class="fieldLabelText">Comptime:</td>
                                                                <td width="8%"><s:textfield id="compSun" name="compSun"  onchange="computeComptime(this);"  value="%{timeSheetVTO.compSun}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield id="compMon" name="compMon"  onchange="computeComptime(this);"  value="%{timeSheetVTO.compMon}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield id="compTus" name="compTus"  onchange="computeComptime(this);" value="%{timeSheetVTO.compTus}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield id="compWed" name="compWed"   onchange="computeComptime(this);" value="%{timeSheetVTO.compWed}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield id="compThur" name="compThur" onchange="computeComptime(this);"  value="%{timeSheetVTO.compThur}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield id="compFri" name="compFri"  onchange="computeComptime(this);"  value="%{timeSheetVTO.compFri}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield id="compSat" name="compSat"  onchange="computeComptime(this);"  value="%{timeSheetVTO.compSat}"  cssClass="inputTextHours" /></td>
                                                            <td width="8%"><s:textfield readonly="true"  name="compTotalHrs" value="%{timeSheetVTO.compTotalHrs}" size="8" cssClass="inputTextHours" /></td></tr>
                                                            
                                                            <tr align="center">
                                                                <td width="40%" class="fieldLabelText">Total:</td>
                                                                <td width="8%"><s:textfield  name="totalSun" readonly="true" value="%{timeSheetVTO.totalSun}" cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="totalMon" readonly="true" value="%{timeSheetVTO.totalMon}" cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="totalTus" readonly="true" value="%{timeSheetVTO.totalTus}" cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="totalWed" readonly="true" value="%{timeSheetVTO.totalWed}" cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="totalThur" readonly="true" value="%{timeSheetVTO.totalThur}" cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="totalFri" readonly="true" value="%{timeSheetVTO.totalFri}" cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield name="totalSat"  readonly="true" value="%{timeSheetVTO.totalSat}" cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield readonly="true"  name="allWeekendTotalHors" value="%{timeSheetVTO.allWeekendTotalHors}" size="8" cssClass="inputTextHours" /></td>
                                                            </tr> 
                                                            <tr align="center" id="biometricDailyTr" style="display: none">
                                                                <td width="40%" class="fieldLabelText">Biometric:</td>
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
                                                            <%--
                                                            <tr align="center">
                                                                <td width="40%" class="fieldLabelText">Biometric:</td>
                                                                <td width="8%" >
                                                                    <s:set name="jspbmSunStatus" value="%{timeSheetVTO.bmSunStatus}"/>
                                                                  <s:if test='%{#jspbmSunStatus=="P" || #jspbmSunStatus=="WOP"}'> 
                                                                      <s:textfield id="biometricSun" name="biometricSun"  value="%{timeSheetVTO.biometricSun}"  cssClass="inputTextHours" readonly="true"/>
                                                                   </s:if><s:else>
                                                                        <s:textfield id="bmSunStatus" name="bmSunStatus"  value="%{timeSheetVTO.bmSunStatus}"  cssClass="inputTextHours" readonly="true"/>
                                                                   </s:else>
                                                                </td>
                                                                <td width="8%" >
                                                                     <s:set name="jspbmMonStatus" value="%{timeSheetVTO.bmMonStatus}"/>
                                                                     
                                                                     <s:if test='%{#jspbmMonStatus=="P" || #jspbmMonStatus=="WOP"}'> 
                                                                         
                                                                        
                                                                      <s:textfield id="biometricMon" name="biometricMon"  value="%{timeSheetVTO.biometricMon}"  cssClass="inputTextHours" readonly="true"/>
                                                                   </s:if><s:else>
                                                                       
                                                                       
                                                                        <s:textfield id="bmMonStatus" name="bmMonStatus"  value="%{timeSheetVTO.bmMonStatus}"  cssClass="inputTextHours" readonly="true"/>
                                                                   </s:else>
                                                               
                                                                </td>
                                                                <td width="8%" >
                                                                     <s:set name="jspbmTusStatus" value="%{timeSheetVTO.bmTusStatus}"/>
                                                                    <s:if test='%{#jspbmTusStatus=="P" || #jspbmTusStatus=="WOP"}'> 
                                                                           <s:textfield id="biometricTus" name="biometricTus"  value="%{timeSheetVTO.biometricTus}"  cssClass="inputTextHours" readonly="true"/>
                                                                        
                                                                    </s:if><s:else>
                                                                        <s:textfield id="bmTusStatus" name="bmTusStatus"  value="%{timeSheetVTO.bmMonStatus}"  cssClass="inputTextHours" readonly="true"/>
                                                                        
                                                                    </s:else>
                                                                 
                                                                </td>
                                                                <td width="8%" >
                                                                    <s:set name="jspbmWedStatus" value="%{timeSheetVTO.bmWedStatus}"/>
                                                                     <s:if test='%{#jspbmWedStatus=="P" || #jspbmWedStatus=="WOP"}'> 
                                                                       <s:textfield id="biometricWed" name="biometricWed"  value="%{timeSheetVTO.biometricWed}"  cssClass="inputTextHours" readonly="true"/>
                                                                         
                                                                    </s:if><s:else>
                                                                        <s:textfield id="bmWedStatus" name="bmWedStatus"  value="%{timeSheetVTO.bmWedStatus}"  cssClass="inputTextHours" readonly="true"/>
                                                                        
                                                                    </s:else>
                                                                 
                                                                </td>
                                                                <td width="8%" >
                                                                    <s:set name="jspbmThurStatus" value="%{timeSheetVTO.bmThurStatus}"/>
                                                                     <s:if test='%{#jspbmThurStatus=="P" || #jspbmThurStatus=="WOP"}'> 
                                                                        
                                                                         <s:textfield id="biometricThur" name="biometricThur"  value="%{timeSheetVTO.biometricThur}"  cssClass="inputTextHours" readonly="true"/>
                                                                    </s:if><s:else>
                                                                         <s:textfield id="bmThurStatus" name="bmThurStatus"  value="%{timeSheetVTO.bmThurStatus}"  cssClass="inputTextHours" readonly="true"/>
                                                                        
                                                                    </s:else>
                                                                 
                                                                </td>
                                                                <td width="8%" >
                                                                    <s:set name="jspbmFriStatus" value="%{timeSheetVTO.bmFriStatus}"/>
                                                                     <s:if test='%{#jspbmFriStatus=="P" || #jspbmFriStatus=="WOP"}'> 
                                                                         <s:textfield id="biometricFri" name="biometricFri"  value="%{timeSheetVTO.biometricFri}"  cssClass="inputTextHours" readonly="true"/>
                                                                        
                                                                    </s:if><s:else>
                                                                         <s:textfield id="bmFriStatus" name="bmFriStatus"  value="%{timeSheetVTO.bmFriStatus}"  cssClass="inputTextHours" readonly="true"/>
                                                                        
                                                                    </s:else>
                                                                  
                                                                </td>
                                                                <td width="8%" >
                                                                     <s:set name="jspbmSatStatus" value="%{timeSheetVTO.bmSatStatus}"/>
                                                                     <s:if test='%{#jspbmSatStatus=="P" || #jspbmSatStatus=="WOP"}'> 
                                                                          <s:textfield id="biometricSat" name="biometricSat"  value="%{timeSheetVTO.biometricSat}"  cssClass="inputTextHours" readonly="true"/>
                                                                        
                                                                    </s:if><s:else>
                                                                         <s:textfield id="bmSatStatus" name="bmSatStatus"  value="%{timeSheetVTO.bmSatStatus}"  cssClass="inputTextHours" readonly="true"/>
                                                                        
                                                                    </s:else>
                                                                
                                                                </td>
                                                               <td width="8%" >
                                                                   <s:textfield readonly="true"  name="biometricTotalHrs" value="%{timeSheetVTO.biometricTotalHrs}" size="8" cssClass="inputTextHours" />
                                                                   
                                                               </td> 
                                                             
                                                            </tr>
                                                            --%>
                                                            
                                                            <%--   <jsp:include page="./TimeSheetWeekDaysForm.jsp" flush="true" /> --%>
                                                        </table></div>
                                                        &nbsp&nbsp
                                                        <table width="90%" border="0" cellspacing="0" cellpadding="0" align="center" >
                                                            <TR >
                                                                <TD WIDTH=125 class="fieldLabel" >Total Billable Hrs:</TD>
                                                                <TD Width=40 align=left ><s:textfield   readonly="true" cssClass="inputTextHours" id="totalBillHrs" name="totalBillHrs" size="5"  value="%{timeSheetVTO.totalBillHrs}" /> </TD>
                                                                <TD colspan=2 rowspan=4 ALIGN="left" class="fieldLabelLeft" ><lebel class="formfield">Notes:</label><s:textarea id="txtNotes"  name="txtNotes" 
                                                                                                                    rows="4" cols="70" cssClass="inputTextarea" placeholder="Enter the notes that Related to your daily activity here..." value="%{timeSheetVTO.notes}" onchange="fieldLengthValidator(this);" style="width: 490px;"></s:textarea></TD>
                                                            </TR>
                                                            <TR>
                                                                <TD WIDTH=125 class="fieldLabel" >Total Holiday Hrs:</TD>
                                                                <TD Width=40 align=left ><s:textfield   readonly="true" cssClass="inputTextHours" id="totalHoliHrs" name="totalHoliHrs" size="5"  value="%{timeSheetVTO.holiTotalHrs}" /> </TD>
                                                            </TR>
                                                            <TR>
                                                                <TD WIDTH=125 class="fieldLabel" >Total Vacation Hrs:</TD>
                                                                <TD Width=40 align=left ><s:textfield   readonly="true" cssClass="inputTextHours" id="totalVacationHrs" name="totalVacationHrs" size="5"  value="%{timeSheetVTO.vacationTotalHrs}" /> </TD>
                                                            </TR>
                                                            <TR>
                                                                <TD WIDTH=125 class="fieldLabel" >Total Comptime Hrs:</TD>
                                                                <TD Width=40 align=left ><s:textfield   readonly="true" cssClass="inputTextHours" id="totalComptimeHrs" name="totalComptimeHrs" size="5"  value="%{timeSheetVTO.compTotalHrs}" /> </TD>
                                                            </TR>  
                                                            <%-- <TR>
                                                                <TD WIDTH=125 class="fieldLabel" >Total Biometric Hrs:</TD>
                                                                <TD Width=40 align=left >
                                                                    <s:textfield   readonly="true" cssClass="inputTextHours" id="biometricTotalHrs" name="biometricTotalHrs" size="5"  value="%{timeSheetVTO.biometricTotalHrs}" />
                                                           </TR>   --%>
                                                             <TR id="biometricTotalTr" style="display: none">
                                                                <TD WIDTH=125 class="fieldLabel" >Total Biometric Hrs:</TD>
                                                                <TD Width=40 align=left >
                                                                    <s:textfield   readonly="true" cssClass="inputTextHours" id="totalBiometricHrs" name="totalBiometricHrs" size="5"  value="%{timeSheetVTO.biometricTotalHrs}" /></td>
                                                           </TR> 
                                                        </TABLE>
                                                    </td>
                                                </tr>
                                            </table>
                                       <%--     <s:if test="%{#session.roleName == 'Operations' || #session.designation == 'OR' }">
                                              
                                                      <p>
                                                    <table cellpadding="0" border="0" cellspacing="0" align="center">
                                                        <tr>
                                                            <td>
                                                                  <s:if test="%{timeSheetStat == 'Approve' }">
                                                            
                                                              <p style="color:green;">
                                                                *Approved
                                                            </p>
                                                            
                                                            </s:if><s:else>
                                                                  <s:submit value="Remind" cssClass="buttonBg" onclick='document.teamTimeSheetForm.action="remind.action?empName=%{timeSheetVTO.empName}"'/>
                                                            </s:else>
                                                            </td>
                                                             <td>
                                                                  <a href="empTimeSheetSearch.action?check=null" style="align:center;">
                                                <img alt="Back to List"
                                                     src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                     width="66px" 
                                                     height="19px"
                                                 border="0" align="bottom" style="padding-left: 15px;"></a>
                                                            </td>

                                                        </tr>
                                                    </table>
                                                </p>
                                           
                                              
                                                <table align="center" border="0" cellpadding="0" cellspacing="0">
                                                    <tr class="fieldLabel">
                                                        <td>
                                                            
                                                            Comment:
                                                        </td>
                                                        <td align="center">
                                                            <p style="color:red;">
                                                                <s:property value="%{timeSheetVTO.comment}"/>
                                                            </p>
                                                        </td>
                                                    </tr>                                                    
                                                </table>
                                            </s:if> --%>
                                       <s:if test="%{#session.roleName == 'Operations'}">
                                                <%-- <s:if test="%{timeSheetStat != 'Approve'}"> --%>
                                                      <p>
                                                    <table cellpadding="0" border="0" cellspacing="0" align="center">
                                                        <tr>
                                                            <td> <s:if test="%{isDualReportingRequired == 'false'}">
                                                                     <s:if test="%{timeSheetStat == 'Approve' }">
                                                            
                                                              <p style="color:green;">
                                                                *Approved
                                                            </p>
                                                            
                                                            </s:if><s:else>
                                                                  <s:submit value="Remind" cssClass="buttonBg" onclick='document.teamTimeSheetForm.action="newremind.action?empName=%{timeSheetVTO.empName}&timeSheetStat=%{timeSheetStat}&timeSheetSecStat=%{timeSheetSecStat}"'/>
                                                            </s:else>
                                                                </s:if>
                                                                 <s:else>
                                                                 <%--     <s:if test="%{timeSheetStat == 'Approve' &&  timeSheetSecStat == 'Approve'}"> --%>
                                                                    <s:if test="%{(timeSheetStat == 'Approve' &&  timeSheetSecStat == 'Approve')|| (timeSheetStat == 'Approve' && timeSheetSecStat == 'Enter')}">
                                                            
                                                              <p style="color:green;">
                                                                *Approved
                                                            </p>
                                                            
                                                            </s:if>
                                                            <s:else>
                                                                  <s:submit value="Remind" cssClass="buttonBg" onclick='document.teamTimeSheetForm.action="newremind.action?empName=%{timeSheetVTO.empName}&timeSheetStat=%{timeSheetStat}&timeSheetSecStat=%{timeSheetSecStat}"'/>
                                                            </s:else>
                                                                </s:else>
                                                            </td>
                                                             <td>
                                                                  <a href="newempTimeSheetSearch.action?check=null" style="align:center;">
                                                <img alt="Back to List"
                                                     src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                     width="66px" 
                                                     height="19px"
                                                 border="0" align="bottom" style="padding-left: 15px;"></a>
                                                            </td><td>
                                                            <div id="attachmentTR" style="display: none;">
                                                               
                                                               
                                                                    <s:if test="%{fileFlagValue==1}">
                                                                    <a style='color:#C00067;' href='#' onclick='getTimeSheetAttachementDownload();'><img SRC='../../includes/images/DownloadFile.jpg' WIDTH=30 HEIGHT=30 BORDER=0 ALTER=''  title="To Download Attachment click on this button" style=" margin-bottom:-5px;"/></a>&nbsp;&nbsp;
                                                                    </s:if>
                                                            </div>
                                                        </td>
                                                          
                                                        </tr>
                                                    </table>
                                                </p>
                                               <%-- </s:if> --%>
                                              
                                                <table align="center" border="0" cellpadding="0" cellspacing="0">
                                                    <tr class="fieldLabel">
                                                        <td>
                                                            
                                                            Comment:
                                                        </td>
                                                        <td align="center">
                                                            <p style="color:red;">
                                                                <s:property value="%{timeSheetVTO.comment}"/>
                                                            </p>
                                                        </td>
                                                    </tr>                                                    
                                                </table>
                                            </s:if>
                                                <s:elseif test="%{#session.designation == 8}"> <!-- 8 means customer operaions -->
                                                      <p>
                                                    <table cellpadding="0" border="0" cellspacing="0" align="center">
                                                        <tr>
                                                            <td><s:if test="%{isDualReportingRequired == 'false' }">
                                                                
                                                                     <s:if test="%{timeSheetStat == 'Approve' }">
                                                            
                                                              <p style="color:green;">
                                                                *Approved
                                                            </p>
                                                            
                                                            </s:if>
                                                            <s:else>
                                                                  <s:submit value="Remind" cssClass="buttonBg" onclick='document.teamTimeSheetForm.action="newremind.action?empName=%{timeSheetVTO.empName}&timeSheetStat=%{timeSheetStat}&timeSheetSecStat=%{timeSheetSecStat}"'/>
                                                            </s:else>
                                                                </s:if><s:else>
                                                                      <s:if test="%{(timeSheetStat == 'Approve' &&  timeSheetSecStat == 'Approve')|| (timeSheetStat == 'Approve' && timeSheetSecStat == 'Enter')}">
                                                            
                                                              <p style="color:green;">
                                                                *Approved
                                                            </p>
                                                            
                                                            </s:if>
                                                            <s:else>
                                                                  <s:submit value="Remind" cssClass="buttonBg" onclick='document.teamTimeSheetForm.action="newremind.action?empName=%{timeSheetVTO.empName}&timeSheetStat=%{timeSheetStat}&timeSheetSecStat=%{timeSheetSecStat}"'/>
                                                            </s:else>
                                                                </s:else>
                                                                 
                                                            </td>
                                                             <td>
                                                <a href="newempTimeSheets.action?check=null" style="align:center;">
                                                <img alt="Back to List"
                                                     src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                     width="66px" 
                                                     height="19px"
                                                 border="0" align="bottom" style="padding-left: 15px;"></a>
                                                            </td><td>
                                                            <div id="attachmentTR" style="display: none;">
                                                               
                                                               
                                                                    <s:if test="%{fileFlagValue==1}">
                                                                    <a style='color:#C00067;' href='#' onclick='getTimeSheetAttachementDownload();'><img SRC='../../includes/images/DownloadFile.jpg' WIDTH=30 HEIGHT=30 BORDER=0 ALTER=''  title="To Download Attachment click on this button" style=" margin-bottom:-5px;"/></a>&nbsp;&nbsp;
                                                                    </s:if>
                                                            </div>
                                                        </td>

                                                        </tr>
                                                    </table>
                                                </p>
                                               <%-- </s:if> --%>
                                              
                                                <table align="center" border="0" cellpadding="0" cellspacing="0">
                                                    <tr class="fieldLabel">
                                                        <td>
                                                          Comment:
                                                        </td>
                                                        <td align="center">
                                                            <p style="color:red;">
                                                                <s:property value="%{timeSheetVTO.comment}"/>
                                                            </p>
                                                        </td>
                                                    </tr>                                                    
                                                </table>
                                       </s:elseif>

                                                <%-- <s:elseif test="%{timeSheetStat == 'Submit' }"> --%>
                                                 <%-- <s:elseif test="%{(timeSheetStat == 'Submit' && teamType==0) || (timeSheetSecStat == 'Submit' && teamType==1) }"> --%>
                                                 <s:elseif test="%{(timeSheetStat == 'Submit' && teamType==1) || (timeSheetSecStat == 'Submit' && teamType==2) }">
                                                    
                                                <p>
                                                    <table cellpadding="0" border="0" cellspacing="0" align="center">
                                                        <tr>
                                                            <td>
                                                                <s:submit value="Approve" id="approveButton" cssClass="buttonBg" onclick='document.teamTimeSheetForm.action="newapprove.action?empName=%{timeSheetVTO.empName}&teamType=%{teamType}"'/>  &nbsp;
                                                                <s:submit value="Reject" id="rejectButton" cssClass="buttonBg" onclick='document.teamTimeSheetForm.action="newreject.action?empName=%{timeSheetVTO.empName}&teamType=%{teamType}"'/>
                                                            </td>
                                                             <td>
                                                                 <s:if test='#session.empType == "e"'>
                                                                     <a href="newEmployeeteamTimeSheets.action?check=null" style="align:center;">
                                                <img alt="Back to List"
                                                     src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                     width="66px" 
                                                     height="19px"
                                                 border="0" align="bottom" style="padding-left: 15px;"></a>
                                                                 </s:if><s:else>
                                                                      <a href="newCustomerTeamTimeSheets.action?check=null" style="align:center;">
                                                <img alt="Back to List"
                                                     src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                     width="66px" 
                                                     height="19px"
                                                 border="0" align="bottom" style="padding-left: 15px;"></a>
                                                                 </s:else>
                                                                  
                                                            </td><td>
                                                            <div id="attachmentTR" style="display: none;">
                                                               
                                                               
                                                                    <s:if test="%{fileFlagValue==1}">
                                                                    <a style='color:#C00067;' href='#' onclick='getTimeSheetAttachementDownload();'><img SRC='../../includes/images/DownloadFile.jpg' WIDTH=30 HEIGHT=30 BORDER=0 ALTER=''  title="To Download Attachment click on this button" style=" margin-bottom:-5px;"/></a>&nbsp;&nbsp;
                                                                    </s:if>
                                                            </div>
                                                        </td>

                                                        </tr>
                                                    </table>
                                                </p>
                                                <table align="left" border="0" cellpadding="0" cellspacing="0" style="margin-left: 144px;">
                                                    <tr class="fieldLabel">
                                                        <td class="formfield">
                                                           
                                                            Comments On Rejection:
                                                        </td>
                                                   
                                                        <td>
                                                            <s:textarea id="comment" rows="3" cols="50" cssClass="inputTextarea" placeholder="Enter the Comments On Rejection here..." name="comment" value="%{timeSheetVTO.comment}" onchange="checkComments(this);fieldLengthValidator(this);" style="width :490px;"/>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </s:elseif>
                                                <s:else>
                                                    
                                                      <table cellpadding="0" border="0" cellspacing="0" align="center">
                                                        <tr>
                                                            <td>
                                                                
                                                                <s:if test='#session.empType == "e"'>
                                                                     <s:submit cssClass="buttonBg" value="Back" onclick='document.teamTimeSheetForm.action="newEmployeeteamTimeSheets.action"'  /><br>
                                                                     <s:if test="%{timeSheetStat == 'Approve' }">
                                                            
                                                              <p style="color:green;">
                                                                *Approved
                                                            </p>
                                                            
                                                            </s:if>
                                                                </s:if><s:else>
                                                                   <s:submit cssClass="buttonBg" value="Back" onclick='document.teamTimeSheetForm.action="newCustomerTeamTimeSheets.action"'  /><br>  
                                                                </s:else>
                                                              
                                                            </td> <td>
                                                            <div id="attachmentTR" style="display: none;">
                                                               
                                                               
                                                                    <s:if test="%{fileFlagValue==1}">
                                                                    <a style='color:#C00067;' href='#' onclick='getTimeSheetAttachementDownload();'><img SRC='../../includes/images/DownloadFile.jpg' WIDTH=30 HEIGHT=30 BORDER=0 ALTER=''  title="To Download Attachment click on this button" style=" margin-bottom:-5px;"/></a>&nbsp;&nbsp;
                                                                    </s:if>
                                                            </div>
                                                        </td>
                                                        </tr>
                                                    </table>  
                                                    <table align="center" border="0" cellpadding="0" cellspacing="0">
                                                    <tr class="fieldLabel">
                                                        <td>
                                                            
                                                            Comment:
                                                        </td>
                                                        <td align="center">
                                                            <p style="color:red;">
                                                                <font size="4">  <s:property value="%{timeSheetVTO.comment}"/></font>
                                                            </p>
                                                        </td>
                                                    </tr>                                                    
                                                </table>
                                                </s:else>
                                                <s:hidden name="isDualReportingRequired" id="isDualReportingRequired" value="%{timeSheetVTO.isDualReportingRequired}"/>
                                             <%--       <s:textfield name="isDualReportingRequired" id="isDualReportingRequired" value="%{timeSheetVTO.isDualReportingRequired}"/> 
                <s:textfield name="timeSheetSecStat" id="timeSheetSecStat" value="%{timeSheetSecStat}"/>
                <s:textfield name="timeSheetStat" id="timeSheetStat" value="%{timeSheetStat}"/>  
                 <s:textfield name="priReportsToId" id="priReportsToId" value="%{timeSheetVTO.priReportsToId}"/>
                <s:textfield name="secReportsToId" id="secReportsToId" value="%{timeSheetVTO.secReportsToId}"/> --%>
                                                <s:hidden name="resourceType" id="resourceType" value="%{resourceType}"/>
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
                                    <%--</sx:tabbedpanel> --%>
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
            
            
                    
                    <s:if test="%{timeSheetStat != 'Approve'}">
                        <s:if test="%{(timeSheetStat == 'Submit') || (timeSheetSecStat == 'Submit')}">
                            <script type="text/javascript">
                            $(window).load(function(){
                                approvedDate();getProjectsDetails();showTlAttachmentFun();displayBiometricHrs();isReportsTo('<%=session.getAttribute("roleName")%>','<%=session.getAttribute("designation")%>');
                                });
                                </script>
                        </s:if>
                        <s:else>
                            <script type="text/javascript">
                             $(window).load(function(){
                                approvedDate();getProjectsDetails();showTlAttachmentFun();displayBiometricHrs();
                                 });
                                  </script>
                        </s:else>
                    </s:if>
                    <s:else>
                        <script type="text/javascript">
                         $(window).load(function(){
                            getProjectsDetails();showTlAttachmentFun();displayBiometricHrs();
                             });
                             </script>
                    </s:else>
                        
                
    </body>
</html>
