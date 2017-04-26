<%-- 
    Document   : NewCustomerTeamTimeSheetSearch
    Created on : Jan 27, 2015, 4:47:36 AM
    Author     : miracle
--%>

<!--/*******************************************************************************
/*
 *Project: MirageV2
 * 
 *$Author: vnukala $
 *
 *$Date: 2009-04-07 13:09:16 $
 *
 *$Revision: 1.4 $
 *
 *$Source: /Hubble/Hubble_CVS/Hubble/web/employee/timesheets/TeamTimeSheetSearch.jsp,v $
 *
 * @(#)TeamTimeSheetSearch.jsp	May 03, 2008, 2:17 AM 
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/ -->

<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>

<%@ page import="java.sql.*" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.*,java.util.Date" %>
<%@ page import="com.freeware.gridtag.*" %> 
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%--<%@ page  import="org.apache.log4j.Level" %>--%>
<%--<%@ page import="org.apache.log4j.Logger" %>--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%-- <%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd" %>

<html>
<head>
    
    <title>Hubble Organization Portal :: Team TimeSheet Search</title>
    
   <%-- <sx:head cache="true"/> --%>
    <link rel="stylesheet" type="text/css" href="<s:url value="/struts/tabs.css"/>">
          <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
          <link REL="StyleSheet" HREF="<s:url value="/includes/css/GridStyle.css"/>" type="text/css">
          <link REL="StyleSheet" HREF="<s:url value="/includes/css/leftMenu.css"/>" type="text/css">
          <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
          <script language="javascript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
    <script language="javascript" src="<s:url value="/includes/javascripts/TimeSheetWeekDays.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/timesheets/EmpTimeSheetSerach.js"/>"></script>
    <s:include value="/includes/template/headerScript.html"/>
    <script type="text/JavaScript">
            
            function checkDates()
            {
             // alert("hi");
            
            var fromDate = document.getElementById('startDate').value;
            var toDate = document.getElementById('endDate').value;
            var empname = document.getElementById('empnameById1').value;
            
           // alert("empname::"+empname);
            
            
           
            
            if(fromDate!=null && fromDate!="")
            {
 
                if(toDate!=null && toDate!="")
                {
                     if(empname == -1)
                     {
                       alert("Please Select Employee Name ");
                        return false;
                     }
                     else{
                        return true;       
                     }
                  
                }
                else{
                alert("Please Enter End Date ");
                return false;
                }
            }
            else
            {
            alert("Please Eneter Start Date ");
            return false;
            }
            
            
            return true;
           
           
            
            }
     
    function getCustomerTimesheetReport()
            {
              //alert("hi");
            
            var fromDate = document.getElementById('custStartDate').value;
            var toDate = document.getElementById('custEndDate').value;
            var reportsToManager = document.getElementById('reportsTo').value;
            
           // alert("empname::"+empname);
            
            var status  = document.getElementById('status').value;
            var teamType  = document.getElementById('custPrintTeamType').value;
            //custPrintTeamType
           // alert("status->"+status);;
         //  alert("from-->"+fromDate+"to-->"+toDate+"reports-->"+reportsToManager+"status-->"+status);
            
            if(fromDate!=null && fromDate!="")
            {
 
                if(toDate!=null && toDate!="")
                {
                   //  if(reportsToManager == -1)
                  //   {
                 //      alert("Please Select reportsTo Name ");
                 //       return false;
                 //    }
                 //    else{
                        // window.location="getCustomerTimesheetReport.action?startdate="+fromDate+"&enddate="+toDate+"&reportsToId="+reportsToManager+"&status="+status;
                        window.location="newGetCustomerTimesheetReport.action?startdate="+fromDate+"&enddate="+toDate+"&reportsToId="+reportsToManager+"&status="+status+"&teamType="+teamType;
                        
                            return true;    
                  //   }
                  
                }
                else{
                alert("Please Enter End Date ");
                return false;
                }
            }
            else 
            {
            alert("Please Eneter Start Date ");
            return false;
            }

            }
            function getEmpTypeList(element)
            {
                var type = element.value;
                if(type == 'External'){
                    document.getElementById("custnameById").style.display='block';
                    document.getElementById("empnameById").value="All";
                    document.getElementById("empnameById").style.display='none';
                    
                  //   document.getElementById("primaryTeam").style.display='';
                  //  document.getElementById("internalTeam").style.display='none';
                  //   document.getElementById("secondaryTeam").style.display='';
                  //  document.getElementById("secondaryTeamList").style.display='';
                 //   document.getElementById("secCustnameById").style.display='';
                    
                    document.getElementById("teamTypeTd").style.display='';
                    document.getElementById("teamTypeListTd").style.display='';
                   // document.getElementById("internalTeam").style.display='none';
                    document.getElementById("empListDropDownTd").style.display='';
                    document.getElementById("teamType").value='0';
                    
                    
                }else{
                     document.getElementById("custnameById").value="All";
                    document.getElementById("custnameById").style.display='none';
                    document.getElementById("empnameById").style.display='block';
                    
                    // document.getElementById("primaryTeam").style.display='none';
                    //document.getElementById("internalTeam").style.display='';
                  //   document.getElementById("secondaryTeam").style.display='none';
                  //  document.getElementById("secondaryTeamList").style.display='none';
                   //  document.getElementById("secCustnameById").style.display='none';
                    
                    document.getElementById("teamTypeTd").style.display='none';
                    document.getElementById("teamTypeListTd").style.display='none';
                    document.getElementById("empListDropDownTd").style.display='';
                }
            }
           // function showEmpType(value1,currentResourceType1)
            function showEmpType()
            {
            
                var value = document.getElementById("empCusType").value;
                var currentResourceType = document.getElementById("currentEmpType").value;
            //alert(value);
                if(value == null || value == "" || value == "null"){
                     if(currentResourceType!='c') {
                       
                       document.getElementById("teamTypeTd").style.display='none';
                    document.getElementById("teamTypeListTd").style.display='none';
                    //document.getElementById("custnameById").style.display='block';
                 
                 }else {
                     document.getElementById("custnameById").style.display='none';
                document.getElementById("empnameById").style.display='block';
                 }
                   // document.getElementById("empnameById").style.display='block';
                    
                     
                }else if(value == 'Internal'){
                    
                    document.getElementById("custnameById").style.display='none';
                    document.getElementById("empnameById").style.display='block';
                  
                     document.getElementById("teamTypeTd").style.display='none';
                    document.getElementById("teamTypeListTd").style.display='none';
                }
                else if(value == 'External'){
                     document.getElementById("custnameById").style.display='block';
                    document.getElementById("empnameById").style.display='none';
                     document.getElementById("teamTypeTd").style.display='';
                    document.getElementById("teamTypeListTd").style.display='';
               
                    
                }
                
            }
            
             function disableReporting(element)
            {
                var status = element.value;
               // alert(status);
                
                if(status == '4'){
                    document.getElementById("reportsTo").value = "-1";
                    document.getElementById("reportsTo").disabled=true;
                }else{
                    document.getElementById("reportsTo").disabled=false;
                }
                
                
            }
            
            // Team Lead timesheet report date : 09/02/2014
            function getTeamTimesheetReport() {
                 var fromDate = document.getElementById('empStartDate').value;
            var toDate = document.getElementById('empEndDate').value;
            var projectId = document.getElementById('projectId').value;
            var teamMemberId = document.getElementById('teamMemberId').value;
            var empStatus = document.getElementById('empStatus').value;
            var printTeamType = document.getElementById('printTeamType').value;
            
           // alert("empname::"+empname);
            
            
           // alert("status->"+status);;
         //  alert("from-->"+fromDate+"to-->"+toDate+"reports-->"+reportsToManager+"status-->"+status);
            
            if(fromDate!=null && fromDate!="")
            {
 
                if(toDate!=null && toDate!="")
                {
                     if(projectId == -1 && empStatus==4)
                     {
                     alert("Please Select project for Status 'Not Entered'");
                        return false;
                     }
                     else{
                        // window.location="getTeamTimesheetReport.action?startdate="+fromDate+"&enddate="+toDate+"&projectId="+projectId+"&status="+empStatus+"&empId="+teamMemberId;
                         window.location="newGetTeamTimesheetReport.action?startdate="+fromDate+"&enddate="+toDate+"&projectId="+projectId+"&status="+empStatus+"&empId="+teamMemberId+"&printTeamType="+printTeamType;
                            return true;    
                     }
                  
                }
                else{
                alert("Please Enter End Date ");
                return false;
                }
            }
            else 
            {
            alert("Please Eneter Start Date ");
            return false;
            }
            }
            
            
            function resetFileds(element)
            {
                var status = element.value;
               // alert(status);
                
                if(status == '4'){
                    document.getElementById("teamMemberId").value = "-1";
                    document.getElementById("teamMemberId").disabled=true;
                }else{
                    document.getElementById("teamMemberId").disabled=false;
                }
                
                
            }
            function showFileds(val) {
                var currentResourceType = document.getElementById("currentEmpType").value;
                
                if(currentResourceType=='c') {
                    document.getElementById("teamTypeTd").style.display='';
                    document.getElementById("teamTypeListTd").style.display='';
                     document.getElementById("primaryTeam").style.display='';
                    document.getElementById("internalTeam").style.display='none';
                }
               // alert(document.getElementById("currentEmpType").value);
            }
            
            function getTeamTypeList(event) {
                var teamType = event.value;
              /*  if(teamType=='Primary') {
                    document.getElementById("primaryTeam").style.display='';
                      document.getElementById("custnameById").style.display='block';
                    document.getElementById("empnameById").style.display='none';
                     document.getElementById("secCustnameById").style.display='none';
                     
                     document.getElementById("secondaryTeam").style.display='none';
                    //document.getElementById("secondaryTeamList").style.display='none';
                }else {
                    document.getElementById("primaryTeam").style.display='none';
                      document.getElementById("custnameById").style.display='none';
                    document.getElementById("empnameById").style.display='none';
                     document.getElementById("secCustnameById").style.display='';
                     
                     document.getElementById("secondaryTeam").style.display='';
                    //document.getElementById("secondaryTeamList").style.display='';
                }*/
            }
            
            
            
             function getDescriptions(priStatus,secStatus) {
              var background = "#3E93D4";
    var title = "Timesheet Status";
   // var text1 = text; 
   // var size = text1.length;
    
    
    var statusConetent = "Status:&nbsp;&nbsp;"+priStatus;
    if(secStatus=='Approved'||secStatus=='Disapproved'||secStatus=='Submitted'){
        statusConetent = statusConetent+"<br>"+"Secondary Status:&nbsp;&nbsp;"+secStatus;
    }
    var size = statusConetent.length;
    
    //Now create the HTML code that is required to make the popup
    var content = "<html><head><title>"+title+"</title></head>\
    <body bgcolor='"+background +"' style='color:white;'><h4>"+title+"</h4>"+statusConetent+"<br />\
    </body></html>";
    
    if(size < 50){
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=300,height=150,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
    else if(size < 100){
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=400,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
    else if(size < 260){
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=400,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    } else {
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=400,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
        }
           
    </script>
</head>
<%!
Connection connection = null;
String userId = null;
String roleId = null;
String roleCusVen = null;
String workingCountry=null;
String empType = "";
String designation = "";
//Logger log=null;
//String empType = null;

String empType1 = null;
String currentEmpType = null;
%>


<%-- <body class="bodyGeneral" oncontextmenu="return false;" onload="showEmpType();"> --%>

<body class="bodyGeneral" oncontextmenu="return false;">

    <s:hidden id="currentEmpType" value="%{#session.empType}"/>
<table class="templateTable1000x580" align="center" cellpadding="0" cellspacing="0">

<tr class="headerBg">
    <td valign="top">
        <s:include value="/includes/template/Header.jsp"/>                    
    </td>
</tr>
<tr>
<td>
    <table class="innerTable1000x515" cellpadding="0" cellspacing="0">
    <tr>
        <td width="150px;" class="leftMenuBgColor" valign="top">
            <s:include value="/includes/template/LeftMenu.jsp"/>                    
        </td>
        <td width="850px" class="cellBorder" valign="top">
        <table cellpadding="0" width="100%" cellspacing="0" bordercolor="#efefef">  
            <tr>
            <td style="padding: 5px 5px;">
                <ul id="accountTabs" class="shadetabs" >
                    <li ><a href="#" class="selected" rel="teamTimeSheetsListTab" >TimeSheet Search</a></li>
              
               
          <%--      <s:if test="#session.roleName == 'Employee'">
                       <li ><a href="#" rel="printEmpTeamTimeSheets" >Print Timesheets</a></li> 
                    </s:if> --%>
                    
                      
<%--                       <s:if test="#session.roleId == 9"> --%>
                           <li ><a href="#" rel="printCustTimesheetsTab" >Print TimeSheet</a></li>
                     <%--  </s:if>   --%>
                </ul>
                
                <%--  <sx:tabbedpanel  id="teamTimeSheetsListPannel" cssStyle="width: 845px; height: 400px;padding: 5px 5px;" doLayout="true">--%>
                <div  style="border:1px solid gray; width:845px;height: 450px; overflow:auto; margin-bottom: 1em;">
                    <div id="teamTimeSheetsListTab" class="tabcontent"  >
                        <%-- <sx:div id="teamTimeSheetsListTab" label="TimeSheet" theme="ajax" > --%>
                        <table cellpadding="0" width="100%" cellspacing="0" bordercolor="#efefef">  
                            <tr class="headerText">
                                <td align="right">
                                    <%
                                    if(request.getAttribute(ApplicationConstants.RESULT_MSG)!=null){
                                        out.println(request.getAttribute(ApplicationConstants.RESULT_MSG));
                                    }
                                    %>   
                                </td>
                                <td align="right">
                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">                                    
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <table cellpadding="0" cellspacing="0" border="0" width="750px">
                                        <tr>
                                            <td align="center"> 
                                                <table border="0" ><tr>
                                                        
                                                    
                                                <!-- We have to implement Search login in backend-->
                                                <s:form name="teamSearch" action="" theme="simple">
                                                    <td class="fieldLabel">Start&nbsp;Date&nbsp;(mm/dd/yyyy)&nbsp;:</td>
                                                    <td><s:textfield name="startDate" cssClass="inputTextBlueSmall" value="%{startDate}"/><a href="javascript:cal1.popup();">
                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                    </td>
                                                    
                                                    <td class="fieldLabel">End&nbsp;Date&nbsp;(mm/dd/yyyy)&nbsp;:</td>
                                                    <td>  <s:textfield name="endDate" cssClass="inputTextBlueSmall" value="%{endDate}" /><a href="javascript:cal2.popup();">
                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                    </td>
 
                                                </tr>
                                                
                                                
                                                <%--    <tr>
                                                    
                                                    
                                                   
                                                    <% if(session.getAttribute("empType").toString().equalsIgnoreCase("e")){%>
                                                             <td class="fieldLabelLeft">Select&nbsp;EmpType&nbsp;:</td>
                                                             <td>  <s:select list="{'Internal','External'}" id="empCusType" name="empCusType" cssClass="inputSelect" value="%{empCusType}" onchange="getEmpTypeList(this);"/></td>
                                              <td class="fieldLabelLeft" id="teamTypeTd" >ReportsTo&nbsp;Level&nbsp;:</td>
                                                    
                                                    <td id="teamTypeListTd" >  <s:select list="#@java.util.LinkedHashMap@{'2':'All','0':'Lower Level','1':'Higher Level'}" id="teamType" name="teamType" cssClass="inputSelect" value="%{teamType}" onchange="getTeamByReportsToType(this);"/></td>
                                                     <%} %>
                                                    
                                                   
                                                       
                                                        
                                                    </tr> --%>
                                                
                                                
                                                <tr>
                                                    
                                                  <%--    <% if(session.getAttribute("empType").toString().equalsIgnoreCase("c")){%> --%>
                                                    
                                                    <td class="fieldLabelLeft" id="teamTypeTd">ReportsTo&nbsp;Level&nbsp;:</td>
                                                    
                                                    <td id="teamTypeListTd">  <s:select list="#@java.util.LinkedHashMap@{'0':'All','1':'Lower Level','2':'Higher Level'}" id="teamType" name="teamType" cssClass="inputSelect" value="%{teamType}" onchange="getTeamByReportsToType(this);"/></td>
                                                  <%--  <%} %> --%>
                                                <%--    <td class="fieldLabelLeft" id="primaryTeam" style="display: none">Primary&nbsp;Team&nbsp;:</td>
                                                    <td class="fieldLabelLeft" id="secondaryTeam" style="display: none">Secondary&nbsp;Team&nbsp;:</td> --%>
                                                <td class="fieldLabelLeft" id="internalTeam">Select&nbsp;EmpName&nbsp;:</td>    
                                                <td id="empListDropDownTd">
                                                   <%--   <% if(session.getAttribute("empType").toString().equalsIgnoreCase("e")){%>
                                                      
                                                      <s:select list="empnamesList" id="empnameById" name="empnameById" headerKey="All" headerValue="All" cssClass="inputSelectLarge" value="%{empnameById}" />   
                                                      
                                                      <s:select list="custnamesList" id="custnameById" name="custnameById" headerKey="All" headerValue="All" cssClass="inputSelectLarge" value="%{custnameById}" />     
                                                    <%} else {%> --%>
                                                            
                                                           <s:select list="custnamesList" id="custnameById" name="custnameById" headerKey="All" headerValue="All" cssClass="inputSelectLarge" value="%{custnameById}" />
                                                             
                                                              
                                                      <%--        <%}%> --%>
                                                              
                                                         <%--  <s:select list="secCustnamesList" id="secCustnameById" name="secCustnameById" headerKey="All" headerValue="All" cssClass="inputSelectLarge" value="%{secCustnameById}" cssStyle="display: none"/> --%>
                                                    </td>
                                                    
                                                 <%--     <% if(session.getAttribute("empType").toString().equalsIgnoreCase("e")){%>
                                                       <td class="fieldLabelLeft">Select&nbsp;Status&nbsp;:</td>
                                                    <td>
                                                           <s:select list="{'Submitted','Approved','Disapproved','Entered'}" id="description" name="description" cssClass="inputSelect" value="%{description}" /> 
                                                       
                                                    </td>
                                                  
                                                       <td> <INPUT type="image"  onclick='document.teamSearch.action="newteamTimeSheets.action"' src="<s:url value="/includes/images/go_21x21.gif"/>" >
                                                        </td>
                                                       <%} %> --%>
                                                </tr>
                                               <%--  <% if(session.getAttribute("empType").toString().equalsIgnoreCase("c")){%> --%>
                                                <tr>
                                                     <td class="fieldLabelLeft">Select&nbsp;Status&nbsp;:</td>
                                                    <td>
                                                           <s:select list="{'Submitted','Approved','Disapproved','Entered'}" id="description" name="description" cssClass="inputSelect" value="%{description}" /> 
                                                       
                                                    </td>
                                                  
                                                       <td> 
                                                           <%-- <INPUT type="image"  onclick='document.teamSearch.action="newteamTimeSheets.action"' src="<s:url value="/includes/images/go_21x21.gif"/>" > --%>
                                                           <INPUT type="image"  onclick='document.teamSearch.action="newCustomerTeamTimeSheetsSearch.action"' src="<s:url value="/includes/images/go_21x21.gif"/>" >
                                                        </td>
                                                </tr>
                                       <%--     <%} %> --%>
                                                        
                                                </s:form>
                                                
                                                </table>
                                           </td> 
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td> 
                                    <div id="displayResult">  
                                        <table align="center" cellpadding="0" cellspacing="0"  width="98%">
                                            <tr>
                                                <td align="center">
                                                    <br>
                                                                                                        
                                                    <%
                                                    
                                                    try{
                                                        //to get the dates from user
                                                        //System.out.println("The First request");
                                                        String sDate=request.getParameter("startDate");
                                                        request.getSession().setAttribute("sDate",sDate);
                                                        String eDate=request.getParameter("endDate");
                                                        request.getSession().setAttribute("eDate",eDate);
                                                        String empName = request.getParameter("empName");
                                                        
                                                       // System.out.println("The First");
                                                        String strSQL = null;
                                                       // System.out.println("hii123-->"+strSQL);
                                                      //  System.out.println("hii321-->"+(String)session.getAttribute("queryTeam"));
                                                      //  empType = (String)request.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE);
                                                       // designation = (String)request.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_DESIG);
                                                       // if(empType.equalsIgnoreCase("e")){
                                                       //   userId = (String)request.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID);
                                                       // 
                                                       // roleId = request.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString();
                                                      //  
                                                       // workingCountry = (String)request.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();  
                                                      //  }else{
                                                        userId = (String)request.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID);
                                                        
                                                        roleId = request.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString();
                                                        
                                                       roleCusVen= request.getSession(false).getAttribute(ApplicationConstants.SESSION_CUSTOMER_ROLE).toString();
                                                      //  }
                                                        
                                                        // System.out.println("The First1");
                                                        //out.println(strSQL+"String Sql is\n");
                                                       //  System.out.println("roleId-->"+roleId);
                                                        if( !roleId.equals("3")&&!"8".equalsIgnoreCase(roleCusVen)) {
                                                         //   System.out.println("in or");
                                                            strSQL = (String)session.getAttribute(ApplicationConstants.QS_MY_TEAMTIMESHEETS_LIST);
                                                            // strSQL="SELECT * FROM vwTimeSheetList where EmpId IN ( SELECT Id FROM tblEmployee where ReportsTo ='"+userId+"')AND  Description='Submitted' ";
                                                            if(request.getSession(false).getAttribute("isFirstTeam")==null){
                                                           //      System.out.println("in or2");
                                                                request.getSession(false).setAttribute("isFirstTeam","yes");
                                                            }
                                                            if(request.getSession(false).getAttribute("isFirstTeam").toString().equals("yes")){
                                                             //    System.out.println("in or3");
                                                                request.getSession(false).setAttribute("isFirstTeam","no");
                                                                //   here we have to read get empid from session and then subsitute
                                                                strSQL = strSQL;
                                                                request.getSession(false).setAttribute("queryTeam",strSQL);
                                                            }
                                                        }
                                                        
                                                          // System.out.println("The First3");
                                                        
                                                        strSQL=request.getSession(false).getAttribute("queryTeam").toString();
                                                      //  System.out.println("hii-->"+strSQL);
                                                        int intCurr = 1;
                                                        int intSortOrd = 0;
                                                        String strTmp = null;
                                                        boolean blnSortAsc = true;
                                                        String strSortCol = null;
                                                        String strSortOrd = "DSC";
                                                        
                                                        strTmp = request.getParameter("txtCurr");
                                                        try {
                                                            if (strTmp != null)
                                                                intCurr = Integer.parseInt(strTmp);
                                                        } catch (NumberFormatException NFEx) {
                                                            NFEx.printStackTrace();
                                                            //log.setLevel((Level)Level.ERROR);
                                                            //log.error("The Error In grid)",NFEx);
                                                        } //catch
                                                        
                                                        // for lookup connection
                                                        connection = ConnectionProvider.getInstance().getConnection();
                                                        
                                                        strSortCol = request.getParameter("Colname");
                                                        strSortOrd = request.getParameter("txtSortAsc");
                                                        if (strSortCol == null) strSortCol = "DateStart";
                                                        if (strSortOrd == null) strSortOrd = "DSC";
                                                        blnSortAsc = (strSortOrd.equals("ASC"));
                                                        
                                                    //  out.println(strSQL);
                                                    
                                                    %>
                                                    
                                                        <s:form method="post" id="frmDBGrid" name="frmDBGrid" theme="simple"  action="newCustomerTeamTimeSheetsSearch.action">           
                                                          
                                                                      <grd:dbgrid id="tblStat" name="tblStat" width="100" pageSize="10" 
                                                                        currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                        dataMember="<%=strSQL%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                               imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"/>
                                                                <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="false"/>
                                                                <grd:imagecolumn  headerText="Approve/Reject" width="10"  HAlign="center"  
                                                                                  imageSrc="../../includes/images/DBGrid/newEdit_17x18.png" 
                                                                                  linkUrl="newGetTeamTimeSheet.action?empID={EmpID}&timeSheetID={TimeSheetId}&resourceType={ResourceType}&statusValue={Description}&secStatusValue={SecDescription}" imageBorder="0" 
                                                                                  imageWidth="16" imageHeight="16" alterText="Click to edit" />
                                                                <grd:numbercolumn dataField="EmpName" headerText="EmpName" width="15" dataFormat=" "/>                  
                                                          <%--    <grd:textcolumn dataField="ProjectName" headerText="ProjectName" width="10" /> --%>
                                                          
                                                          <%--    <grd:textcolumn dataField="SecDescription" headerText="Status" width="6" /> --%>
                                                          
                                                                <grd:anchorcolumn dataField="Description" linkUrl="javascript:getDescriptions('{Description}','{SecDescription}')" headerText="Status"
                                                                                      linkText="Click To View"  width="10" />
                                                          
                                                                
                                                                <grd:datecolumn dataField="DateStart" headerText="Start Date" width="8" dataFormat="MM-dd-yyyy" />
                                                                <grd:datecolumn dataField="DateEnd"	headerText="End Date" width="8" dataFormat="MM-dd-yyyy"/>
                                                                <grd:numbercolumn dataField="TotalHrs" headerText="Billable Hrs" dataFormat="   .0 " width="8" />
                                                                <grd:datecolumn dataField="SubmittedDate" headerText="Submitted Date" width="10" dataFormat="MM-dd-yyyy"/>
                                                                <%--  <grd:datecolumn dataField="ApprovedDate"	headerText="Approved Date" width="10" dataFormat="MM-dd-yyyy"/> --%>
                                                            </grd:dbgrid>
                                                         
                                                                  
                                                            <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                            <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                            <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                                                            <input type="hidden" name="submitFrom" value="dbGrid">     
                                                            <s:hidden name="empnameById" value="%{empnameById}"/>
                                                            <s:hidden name="startDate" value="%{startDate}"/>
                                                            <s:hidden name="endDate" value="%{endDate}"/>
                                                               <s:hidden name="description" value="%{description}"/>
                                                               <s:hidden name="teamType" value="%{teamType}"/>
                                                               <s:hidden name="empCusType" value="%{empCusType}"/>
                                                            <!-- Used for Storing the Current Database Primary Key Id Eg. AccountId/ContactId. -->
                                                            <input id="txtDBId" TYPE="hidden" NAME="txtDBId"	VALUE="0">
                                                            
                                                            <!-- Used for Storing the Current Row No. in the Table -->
                                                            <input id="txtTRId"TYPE="hidden" NAME="txtTRId"	VALUE="1">
                                                            
                                                        </s:form>
                                                    
                                                </td>
                                            </tr>
                                        </table>
                                    </div>
                                    
                                    <%
                                    connection.close();
                                    connection = null;
                                                    }catch(Exception ex) {
                                                        ex.printStackTrace();
                                                        // log.setLevel((Level)Level.ERROR);
                                                        //log.error("The Error @addTimeSheet()",ex);
                                                    }finally {
                                                        if(connection!=null)
                                                            connection.close();
                                                        connection = null;
                                                    } // finally
                                    %>
                                </td>      
                            </tr>
                        </table>
                        <script type="text/JavaScript">
                                                           var cal1 = new CalendarTime(document.forms['teamSearch'].elements['startDate']);
                                                           cal1.year_scroll = true;
                                                           cal1.time_comp = false;
                                                           
                                                           var cal2 = new CalendarTime(document.forms['teamSearch'].elements['endDate']);
                                                           cal2.year_scroll = true;
                                                           cal2.time_comp = false;
                        </script>
                        <%-- </sx:div> --%>
                    </div>
                    
               <%--     <div id="printTimesheetsTab" class="tabcontent" >
                        <table cellpadding="0" width="100%" cellspacing="0" bordercolor="#efefef">  
                            <tr class="headerText">
                                <td align="right">
                                    <%
                                   
                                    if(request.getAttribute(ApplicationConstants.RESULT_MSG)!=null){
                                                        out.println(request.getAttribute(ApplicationConstants.RESULT_MSG));
                                    }
                                    %>   
                                </td>
                                <td align="right">
                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">                                    
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <table cellpadding="1" cellspacing="2" border="0" width="100%">
                                        <tr>
                                            <td>
                                                
                                                
                                                
                                                <s:form name="printTimeSeet" action="getEmpTimeSheetsReport" onsubmit="return checkDates();" theme="simple" target="_blank">
                                                    
                                                    <tr>
                                                        <td class="fieldLabelLeft">Start Date(mm/dd/yyyy):</td>
                                                        <td  ><s:textfield name="startDate" id="startDate" cssClass="inputTextBlue" /><a style="text-decoration: none" href="javascript:cal3.popup();">
                                                            <img  src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                        </td>
                                                        
                                                        <td  class="fieldLabelLeft">End Date(mm/dd/yyyy):</td>
                                                        <td > <s:textfield name="endDate"  id="endDate" cssClass="inputTextBlue"/><a style="text-decoration: none" href="javascript:cal4.popup();">
                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td   class="fieldLabelLeft">Select EmpName :</td>
                                                     <td ><s:select list="empnamesList" id="empnameById1" name="empnameById1" headerKey="-1" headerValue="All" cssClass="inputSelectLarge" value="%{empnameById}"/></td> 
                                                        
                                                        <td></td>
                                                        <td> <s:submit name="submit" value="Generate Report"  cssClass="buttonBg"/>
                                                        </td>
                                                    </tr>
                                                </s:form>
                                                
                                                
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            
                        </table>
                         <script type="text/JavaScript">
                         var cal3 = new CalendarTime(document.forms['printTimeSeet'].elements['startDate']);
                                                           cal1.year_scroll = true;
                                                           cal1.time_comp = false;
                                                           
                        var cal4 = new CalendarTime(document.forms['printTimeSeet'].elements['endDate']);
                                                           cal2.year_scroll = true;
                                                           cal2.time_comp = false;
                        </script>
                        
                    </div>--%>
                                <!-- customer print time sheet start -->
                           <%--     <s:if test="#session.roleId == 9"> --%>
                    <div  id="printCustTimesheetsTab" class="tabcontent" >
                        <table cellpadding="0" width="100%" cellspacing="0" bordercolor="#efefef">  
                            <tr class="headerText">
                                <td align="right">
                                    <%
                                     
                                    if(request.getAttribute(ApplicationConstants.RESULT_MSG)!=null){
                                                        out.println(request.getAttribute(ApplicationConstants.RESULT_MSG));
                                    }
                                    %>   
                                </td>
                                <td align="right">
                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">                                    
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <table cellpadding="1" cellspacing="2" border="0" width="100%">
                                        
                                                <!-- We have to implement Search login in backend-->
                                                
                                                
                                             <%--   <s:form name="printCustTimeSeet" action="getCustTimeSheetsReport" onsubmit="return checkDates();" theme="simple" target="_blank"> --%>
                                          <%--   <s:form name="printCustTimeSeet" action="getCustTimeSheetsReport" onsubmit="return checkCustomerPrintDates();" theme="simple" target="_blank"> --%>
                                             <s:form name="printCustTimeSeet" action="newGetCustomerTimesheetReport" onsubmit="return checkCustomerPrintDates();" theme="simple" target="_blank">
                                                    
                                                    <tr>
                                                        <td class="fieldLabelLeft">Start Date(mm/dd/yyyy):</td>
                                                        <td  ><s:textfield name="startDate" id="custStartDate" cssClass="inputTextBlue" /><a style="text-decoration: none" href="javascript:cal5.popup();">
                                                            <img  src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                        </td>
                                                        
                                                        <td  class="fieldLabelLeft">End Date(mm/dd/yyyy):</td>
                                                        <td > <s:textfield name="endDate"  id="custEndDate" cssClass="inputTextBlue"/><a style="text-decoration: none" href="javascript:cal6.popup();">
                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                        </td>
                                                    </tr>
                                                    
                                                    <tr>
                                                           <td class="fieldLabelLeft" >ReportsTo&nbsp;Level&nbsp;:</td>
                                                    
                                                    <td > <%-- <s:select list="#@java.util.LinkedHashMap@{'2':'All','0':'Lower Level','1':'Higher Level'}" id="custPrintTeamType" name="custPrintTeamType" cssClass="inputSelect" value="%{custPrintTeamType}" /></td> --%>
                                                         <s:select list="#@java.util.LinkedHashMap@{'0':'All','1':'Lower Level','2':'Higher Level'}" id="custPrintTeamType" name="custPrintTeamType" cssClass="inputSelect" value="%{custPrintTeamType}" /></td>
                                                    </tr>
                                                    <tr>
                                                        <td   class="fieldLabelLeft">Reporting&nbsp;Manager&nbsp;:</td>
                                                   <td ><s:select list="projectReportsToMap" id="reportsTo" name="empnameById1" headerKey="-1" headerValue="All" cssClass="inputSelectLarge" value="%{empnameById}"/></td> 
                                                        <td   class="fieldLabelLeft">Status&nbsp;:</td>
                                                        <td><s:select list="#@java.util.LinkedHashMap@{'1':'Entered','2':'Submitted','3':'Approved','4':'Not entered'}" id="status" name="description" onchange="disableReporting(this);" cssClass="inputSelect" value="%{description}" /></td>
                                                    </tr>
                                                       <tr>
                                                        <td><br></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td >
                                                            <%--<s:submit name="submit" value="Generate Report"  cssClass="buttonBg"/> --%>
                                                            <input type="button" class="buttonBg" value="Generate Excel" onclick="return getCustomerTimesheetReport();"/>
                                                        </td>
                                                    </tr>
                                                </s:form>
                                                
                                                
                                          
                                    </table>
                                </td>
                            </tr>
                            
                        </table>
                         <script type="text/JavaScript">
                         var cal5 = new CalendarTime(document.forms['printCustTimeSeet'].elements['startDate']);
                                                           cal5.year_scroll = true;
                                                           cal5.time_comp = false;
                                                           
                        var cal6 = new CalendarTime(document.forms['printCustTimeSeet'].elements['endDate']);
                                                           cal6.year_scroll = true;
                                                           cal6.time_comp = false;
                        </script>
                        
                    </div>
                   <!-- customer print time sheet end -->
<%--</s:if> --%>


<!-- Employee team timesheet reports start -->
               <%--     <s:if test="#session.roleName == 'Employee'">

<div  id="printEmpTeamTimeSheets" class="tabcontent" >
                        <table cellpadding="0" width="100%" cellspacing="0" bordercolor="#efefef">  
                            <tr class="headerText">
                                <td align="right">
                                    <%
                                     
                                    if(request.getAttribute(ApplicationConstants.RESULT_MSG)!=null){
                                                        out.println(request.getAttribute(ApplicationConstants.RESULT_MSG));
                                    }
                                    %>   
                                </td>
                                <td align="right">
                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">                                    
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <table cellpadding="1" cellspacing="2" border="0" width="100%">
                                        
                                             
                                             <s:form name="printEmpTimeSeet" action="#"  theme="simple" target="_blank">
                                                    
                                                    <tr>
                                                        <td class="fieldLabel">Start Date(mm/dd/yyyy):</td>
                                                        <td  ><s:textfield name="startDate" id="empStartDate" cssClass="inputTextBlue" /><a style="text-decoration: none" href="javascript:cal6.popup();">
                                                            <img  src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                        </td>
                                                        
                                                        <td  class="fieldLabel">End Date(mm/dd/yyyy):</td>
                                                        <td > <s:textfield name="endDate"  id="empEndDate" cssClass="inputTextBlue"/><a style="text-decoration: none" href="javascript:cal7.popup();">
                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td   class="fieldLabel">Projects&nbsp;:</td>
                                                   <td ><s:select list="projectMap" id="projectId" name="projectId" headerKey="-1" headerValue="Internal" cssClass="inputSelectLarge" value="%{projectId}" onchange="isDualReportsTo(this);getTeamByProjectId();"/></td> 
                                                   <td class="fieldLabelLeft" id="printTeamTypeTd" style="display:none;">ReportsTo&nbsp;Level&nbsp;:</td>
                                                    
                                                    <td id="printTeamTypeListTd" style="display:none;">  <s:select list="#@java.util.LinkedHashMap@{'2':'All','0':'Lower Level','1':'Higher Level'}" id="printTeamType" name="printTeamType" cssClass="inputSelect" value="%{printTeamType}" onchange="getPrintTeamByReportsToType(this);"/></td>
                                                   </tr>
                                                    <tr>
                                                   <td class="fieldLabel">Select&nbsp;EmpName&nbsp;:</td>
                                                    <td><s:select list="empnamesList" id="teamMemberId" name="teamMemberId" headerKey="-1" headerValue="All" cssClass="inputSelectLarge" value="%{teamMemberId}" />
                                                    
                                                    <td   class="fieldLabel">Status&nbsp;:</td>
                                                    <td><s:select list="#@java.util.LinkedHashMap@{'1':'Entered','2':'Submitted','3':'Approved','4':'Not entered'}" id="empStatus" name="empStatus"  cssClass="inputSelect" value="%{description}" onchange="resetFileds(this);"/></td>
                                                    </tr>
                                                       <tr>
                                                        <td><br></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td >
                                                            
                                                            <input type="button" class="buttonBg" value="Generate Excel" onclick="return getTeamTimesheetReport();"/>
                                                        </td>
                                                    </tr>
                                                </s:form>
                                                
                                                
                                           
                                    </table>
                                </td>
                            </tr>
                            
                        </table>
                         <script type="text/JavaScript">
                         var cal6 = new CalendarTime(document.forms['printEmpTimeSeet'].elements['startDate']);
                                                           cal6.year_scroll = true;
                                                           cal6.time_comp = false;
                                                           
                        var cal7 = new CalendarTime(document.forms['printEmpTimeSeet'].elements['endDate']);
                                                           cal7.year_scroll = true;
                                                           cal7.time_comp = false;
                        </script>
                        
                    </div>
                    </s:if> --%>
<!-- Employee team timesheet reports end -->


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
</table>
</td>
</tr>
<tr class="footerBg">
    <td align="center"><s:include value="/includes/template/Footer.jsp"/></td>
</tr>
</table>
</body>
</html>
