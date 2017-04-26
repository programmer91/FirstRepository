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
        <%--<script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>--%>
        <script language="javascript" src="<s:url value="/includes/javascripts/TimeSheetWeekDays.js"/>"></script>
        <%--<script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/clientValidations/TimeSheetClientValidation.js"/>"></script>--%>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmpStandardClientValidations.js"/>"></script>
         <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <s:include value="/includes/template/headerScript.html"/>
        
        <script>
            function approvedDate() {
                
            /*  var currentDate = new Date();
                var day = currentDate.getDate();
                var month = currentDate.getMonth()+1;
                if(day<10) {
                    day = '0'+day;
                }
                if(month<10) {
                    month = '0'+month;
                }
                var year = currentDate.getYear();
                document.teamTimeSheetForm.approveDate.value = month+'/'+day+'/'+year;*/
                
  document.getElementById("approveButton").disabled = true;
        document.getElementById("rejectButton").disabled = true;
                document.teamTimeSheetForm.approveDate.value ='<%=com.mss.mirage.util.DateUtility.getInstance().getToDayDateToView()%>';
                
  document.getElementById("approveButton").disabled = false;
        document.getElementById("rejectButton").disabled = false;
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
    
       <s:if test="%{timeSheetStat != 'Approve'}">
        <body class="bodyGeneral"  oncontextmenu="return false;" onload="approvedDate();">
    </s:if>
            <s:else>
            <body class="bodyGeneral"  oncontextmenu="return false;">
            </s:else>
        
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
                                <div  style="border:1px solid gray; width:820px;height: 500px; overflow:auto; margin-bottom: 1em;">
                                    <%-- <sx:div id="timeSheetsApprovel" label="TimeSheet Approvel"> --%>
                                    <div id="timeSheetsApprovel" class="tabcontent"  >
                                        
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
                                                                <td width="27%" height="22" align="left"><s:property value="timeSheetVTO.empName"/></td>
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
                                                                </td>
                                                            </tr>
                                                        </table><br>
                                                        <table width="90%" border="0" cellspacing="0" cellpadding="0" align="center">
                                                            <tr class="headerText">
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
                                                                <td width="20%" class="headerText"><B>Tasks</B></td>
                                                                <td width="6%" class="headerText" ><B>SUN</B></td>
                                                                <td width="8%" class="headerText"><B>MON</B></td>
                                                                <td width="6%" class="headerText"><B>TUE</B></td>
                                                                <td width="7%" class="headerText"><B>WED</B></td>
                                                                <td width="7%" class="headerText"><B>THUR</B></td>
                                                                <td width="8%" class="headerText"><B>FRI</B></td>
                                                                <td width="6%" class="headerText"><B>SAT</B></td>
                                                                <td width="8%" class="headerText"><B>TOTALHOURS</B></td>
                                                            </tr>
                                                            <tr>
                                                                
                                                                <s:if test='#session.empType == "e"'>
                                                                
  <s:if test="%{#session.roleName == 'Operations'}">
                                                                         <td width="40%" class="fieldLabel">Project(N):</td>
                                                                     </s:if><s:else>
                                                                         <td width="40%" class="fieldLabel">Project1:</td>
                                                                     </s:else>
                                                                </s:if><s:else>
                                                                    <td width="40%" class="fieldLabel">Project(N):</td>
                                                                </s:else>
                                                                <td width="8%"><s:textfield id="proj1Sun" name="proj1Sun" onchange="checkBeforeSubmit();computeProj1(this);"  value="%{timeSheetVTO.proj1Sun}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  id="proj1Mon" name="proj1Mon"  onchange="computeProj1(this);"  value="%{timeSheetVTO.proj1Mon}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  id="proj1Tus" name="proj1Tus"  onchange="computeProj1(this);"  value="%{timeSheetVTO.proj1Tus}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  id="proj1Wed" name="proj1Wed"  onchange="computeProj1(this);"  value="%{timeSheetVTO.proj1Wed}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  id="proj1Thur" name="proj1Thur" onchange="computeProj1(this);" value="%{timeSheetVTO.proj1Thur}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  id="proj1Fri" name="proj1Fri" onchange="computeProj1(this);"  value="%{timeSheetVTO.proj1Fri}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  id="proj1Sat" name="proj1Sat" onchange="computeProj1(this);"  value="%{timeSheetVTO.proj1Sat}"  cssClass="inputTextHours"  /></td>
                                                            <td width="8%"><s:textfield readonly="true"  name="proj1TotalHrs" value="%{timeSheetVTO.proj1TotalHrs}" size="8" cssClass="inputTextHours"  /></td></tr>
                                                            <tr>
                                                                <s:if test='#session.empType == "e"'>
                                                                 <s:if test="%{#session.roleName == 'Operations'}">
                                                                         <td width="40%" class="fieldLabel">Project(N+1):</td>
                                                                     </s:if><s:else>
                                                                         <td width="40%" class="fieldLabel">Project2:</td>
                                                                     </s:else>
                                                                </s:if><s:else>
                                                                    <td width="40%" class="fieldLabel">Project(N+1):</td>
                                                                </s:else>
                                                                <td width="8%"><s:textfield  name="proj2Sun" onchange="computeProj2(this);"  value="%{timeSheetVTO.proj2Sun}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="proj2Mon" onchange="computeProj2(this);" value="%{timeSheetVTO.proj2Mon}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="proj2Tus" onchange="computeProj2(this);" value="%{timeSheetVTO.proj2Tus}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="proj2Wed"  onchange="computeProj2(this);" value="%{timeSheetVTO.proj2Wed}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="proj2Thur"   onchange="computeProj2(this);"  value="%{timeSheetVTO.proj2Thur}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="proj2Fri"  onchange="computeProj2(this);" value="%{timeSheetVTO.proj2Fri}" cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="proj2Sat"   onchange="computeProj2(this);" value="%{timeSheetVTO.proj2Sat}"  cssClass="inputTextHours" /></td>
                                                            <td width="8%"><s:textfield readonly="true"  name="proj2TotalHrs" value="%{timeSheetVTO.proj2TotalHrs}" size="8" cssClass="inputTextHours" /></td></tr>
                                                            <tr>
                                                                <td width="40%" class="fieldLabel">Internal:</td>
                                                                <td width="8%"><s:textfield  name="internalSun"  onchange="computeInternal(this);" value="%{timeSheetVTO.internalSun}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="internalMon"  onchange="computeInternal(this);" value="%{timeSheetVTO.internalMon}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="internalTus"  onchange="computeInternal(this);" value="%{timeSheetVTO.internalTus}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="internalWed"  onchange="computeInternal(this);" value="%{timeSheetVTO.internalWed}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="internalThur" onchange="computeInternal(this);" value="%{timeSheetVTO.internalThur}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="internalFri"  onchange="computeInternal(this);"  value="%{timeSheetVTO.internalFri}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="internalSat"  onchange="computeInternal(this);"  value="%{timeSheetVTO.internalSat}"  cssClass="inputTextHours" /></td>
                                                            <td width="8%"><s:textfield readonly="true"  name="internalTotalHrs" value="%{timeSheetVTO.internalTotalHrs}" size="8" cssClass="inputTextHours" /></td></tr>
                                                            <tr>
                                                                <td width="40%" class="fieldLabel">Vacation:</td>
                                                                <td width="8%"><s:textfield  name="vacationSun"  onchange="computeVacation(this)" value="%{timeSheetVTO.vacationSun}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="vacationMon"  onchange="computeVacation(this)" value="%{timeSheetVTO.vacationMon}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="vacationTus"  onchange="computeVacation(this)"  value="%{timeSheetVTO.vacationTus}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="vacationWed"  onchange="computeVacation(this)"  value="%{timeSheetVTO.vacationWed}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="vacationThur"  onchange="computeVacation(this)"  value="%{timeSheetVTO.vacationThur}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="vacationFri"   onchange="computeVacation(this)" value="%{timeSheetVTO.vacationFri}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="vacationSat"   onchange="computeVacation(this)"  value="%{timeSheetVTO.vacationSat}"  cssClass="inputTextHours" /></td>
                                                            <td width="8%"><s:textfield readonly="true"  name="vacationTotalHrs" value="%{timeSheetVTO.vacationTotalHrs}" size="8" cssClass="inputTextHours" /></td></tr>
                                                            <tr>
                                                                <td width="40%" class="fieldLabel">Holiday:</td>
                                                                <td width="8%"><s:textfield  name="holiSun"  onchange="computeHolidy(this);"  value="%{timeSheetVTO.holiSun}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="holiMon"  onchange="computeHolidy(this);"  value="%{timeSheetVTO.holiMon}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="holiTus"  onchange="computeHolidy(this);" value="%{timeSheetVTO.holiTus}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="holiWed"   onchange="computeHolidy(this);" value="%{timeSheetVTO.holiWed}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="holiThur" onchange="computeHolidy(this);"  value="%{timeSheetVTO.holiThur}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="holiFri"  onchange="computeHolidy(this);"  value="%{timeSheetVTO.holiFri}"  cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="holiSat"  onchange="computeHolidy(this);"  value="%{timeSheetVTO.holiSat}"  cssClass="inputTextHours" /></td>
                                                            <td width="8%"><s:textfield readonly="true"  name="holiTotalHrs" value="%{timeSheetVTO.holiTotalHrs}" size="8" cssClass="inputTextHours" /></td></tr>
                                                            <tr>
                                                                <td width="40%" class="fieldLabel">Total:</td>
                                                                <td width="8%"><s:textfield  name="totalSun" readonly="true" value="%{timeSheetVTO.totalSun}" cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="totalMon" readonly="true" value="%{timeSheetVTO.totalMon}" cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="totalTus" readonly="true" value="%{timeSheetVTO.totalTus}" cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="totalWed" readonly="true" value="%{timeSheetVTO.totalWed}" cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="totalThur" readonly="true" value="%{timeSheetVTO.totalThur}" cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield  name="totalFri" readonly="true" value="%{timeSheetVTO.totalFri}" cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield name="totalSat"  readonly="true" value="%{timeSheetVTO.totalSat}" cssClass="inputTextHours" /></td>
                                                                <td width="8%"><s:textfield readonly="true"  name="allWeekendTotalHors" value="%{timeSheetVTO.allWeekendTotalHors}" size="8" cssClass="inputTextHours" /></td>
                                                            </tr> 
                                                            <%--   <jsp:include page="./TimeSheetWeekDaysForm.jsp" flush="true" /> --%>
                                                        </table>
                                                        &nbsp&nbsp
                                                        <table width="90%" border="0" cellspacing="0" cellpadding="0" align="center" >
                                                            <TR>
                                                                <TD WIDTH=125 class="fieldLabel" >Total Billable Hours:</TD>
                                                                <TD Width=40 align=left ><s:textfield   readonly="true" cssClass="inputTextBlue" id="totalBillHrs" name="totalBillHrs" size="5"  value="%{timeSheetVTO.totalBillHrs}" /> </TD>
                                                                <TD WIDTH=250  class="fieldLabel" colspan=2 align=middle>Notes</TD>
                                                            </TR>
                                                            <TR>
                                                                <TD WIDTH=125 class="fieldLabel" >Total Holiday Hours:</TD>
                                                                <TD Width=40 align=left ><s:textfield   readonly="true" cssClass="inputTextBlue" id="totalHoliHrs" name="totalHoliHrs" size="5"  value="%{timeSheetVTO.holiTotalHrs}" /> </TD>
                                                                <TD colspan=2 rowspan=2 ALIGN="center" ><s:textarea id="txtNotes"  name="txtNotes" 
                                                                                                                    rows="3" cols="50" cssClass="inputTextarea" value="%{timeSheetVTO.notes}" onchange="fieldLengthValidator(this);"></s:textarea></TD>
                                                            </TR>
                                                            <TR>
                                                                <TD WIDTH=125 class="fieldLabel" >Total Vacation Hours:</TD>
                                                                <TD Width=40 align=left ><s:textfield   readonly="true" cssClass="inputTextBlue" id="totalVacationHrs" name="totalVacationHrs" size="5"  value="%{timeSheetVTO.vacationTotalHrs}" /> </TD>
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
                                                <s:elseif test="%{#session.designation == 'OR'}">
                                                      <p>
                                                    <table cellpadding="0" border="0" cellspacing="0" align="center">
                                                        <tr>
                                                            <td>
                                                                  <s:if test="%{timeSheetStat == 'Approve' }">
                                                            
                                                              <p style="color:green;">
                                                                *Approved
                                                            </p>
                                                            
                                                            </s:if>
                                                            <s:else>
                                                                  <s:submit value="Remind" cssClass="buttonBg" onclick='document.teamTimeSheetForm.action="remind.action?empName=%{timeSheetVTO.empName}"'/>
                                                            </s:else>
                                                            </td>
                                                             <td>
                                                <a href="empTimeSheets.action?check=null" style="align:center;">
                                                <img alt="Back to List"
                                                     src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                     width="66px" 
                                                     height="19px"
                                                 border="0" align="bottom" style="padding-left: 15px;"></a>
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

                                                <s:elseif test="%{timeSheetStat == 'Submit' }">
                                                <p>
                                                    <table cellpadding="0" border="0" cellspacing="0" align="center">
                                                        <tr>
                                                            <td>
                                                                <s:submit value="Approve" id="approveButton" cssClass="buttonBg" onclick='document.teamTimeSheetForm.action="approve.action"'/>  &nbsp;
                                                                <s:submit value="Reject" id="rejectButton" cssClass="buttonBg" onclick='document.teamTimeSheetForm.action="reject.action"'/>
                                                            </td>
                                                             <td>
                                                                  <a href="teamTimeSheets.action?check=null" style="align:center;">
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
                                                        <td align="center">
                                                           
                                                            Comments On Rejection:
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <s:textarea id="comment" rows="3" cols="50" cssClass="inputTextarea" name="comment" value="%{timeSheetVTO.comment}" onchange="checkComments(this);fieldLengthValidator(this);"/>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </s:elseif>
                                                <s:else>
                                                    
                                                      <table cellpadding="0" border="0" cellspacing="0" align="center">
                                                        <tr>
                                                            <td>
                                                               <s:submit cssClass="buttonBg" value="Back" onclick='document.teamTimeSheetForm.action="teamTimeSheets.action"'  /><br>
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
                                                
                                                <s:hidden name="resourceType" value="%{resourceType}"/>
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
    </body>
</html>
