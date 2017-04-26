<%-- 
    Document   : EmpTimeSheetSerach
    Created on : Jul 22, 2014, 1:26:02 AM
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
    
      <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tooltip.css"/>">
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tooltip.js"/>"></script>   
    
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/timesheets/EmpTimeSheetSerach.js"/>"></script>
    
    <s:include value="/includes/template/headerScript.html"/>
        
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
    <script type="text/JavaScript">

            
            function checkDates()
            {
             // alert("hi");
           
            var fromDate = document.getElementById('printStartDate').value;
            var toDate = document.getElementById('printEndDate').value;
            var empname = document.getElementById('empnameById1').value;
            
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
     
    function getEmpTimesheetReport()
            {
              //alert("hi");
            
            
            var fromDate = document.getElementById('reportsStartDate').value;
            var toDate = document.getElementById('reportsEndDate').value;
            
            
           // alert("empname::"+empname);
            
            var status  = document.getElementById('description2').value;
            var projectId  = document.getElementById('projectId2').value;
            var empnameById  = document.getElementById('empnameById2').value;
           // alert("status->"+status);;
         //  alert("from-->"+fromDate+"to-->"+toDate+"reports-->"+reportsToManager+"status-->"+status);
            
            if(fromDate!=null && fromDate!="")
            {
 
                if(toDate!=null && toDate!="")
                {
                    if(projectId == -1)
                    {
                      alert("Please Select Project Name ");
                        return false;
                    }
                    else{
                        
                        if(projectId==0 && status==4)
                        {
                             alert("Please Select status except 'Not Entered' for Internal ");
                        return false;
                        }
                        else {
                             // if(projectId==0 && empnameById==-1)
                        //{
                        //     alert("Please Select employee for Internal ");
                      //  return false;
                       /// }else {
                          //  if(status==-1 && empnameById == -1) {
                           //      alert("Please Select employee for status ALL ");
                       // return false;
                         //   }else {
                                 window.location="getEmpTimesheetReport.action?startdate="+fromDate+"&enddate="+toDate+"&employeeId="+empnameById+"&status="+status+"&projectId="+projectId;
                          //  return true; 
                          //  }
                            
                        //}
                             
                        }
                          
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
   
        function clearTimeSheetSearch() {
                 
                 document.teamSearch.startDate.value = "";
                document.teamSearch.endDate.value = "";
           document.teamSearch.projectId.value = 0;
           document.teamSearch.description.value = "";
           getEmployeesByProjectId('search');
        }
       function clearPrintTimeSheet() {
                 
                 document.printTimeSeet.printStartDate.value = "";
                document.printTimeSeet.printEndDate.value = "";
         document.printTimeSeet.empnameById1.value = "-1";
           
          
        }
        
         function clearTimeSheetReports() {
                 
                 document.timeSeetReport.reportsStartDate.value = "";
                document.timeSeetReport.reportsEndDate.value = "";
           document.timeSeetReport.projectId2.value = 0;
           document.timeSeetReport.description2.value = -1;
           getEmployeesByProjectId('print');
        }
        
        
        function disableEmpList(descObj) {
            if(descObj.value=="4") {
                document.timeSeetReport.empnameById2.value = -1;
            }
        }
        function checkStaus() {
            if(document.timeSeetReport.description2.value==4) {
                alert("Please change status except 'Not Entered'");
                getEmployeesByProjectId('print');
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
%>


<body class="bodyGeneral"  oncontextmenu="return false;" onload="init();" >

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
                    
                        <li><a href="#" rel="printTimesheetsTab" class="selected"> Print TimeSheet </a></li>
                         <li><a href="#" rel="printProjectTimesheetsTab" class="selected">TimeSheet Reports</a></li>
                   
                     
                </ul>
                 
                <%--  <sx:tabbedpanel  id="teamTimeSheetsListPannel" cssStyle="width: 845px; height: 400px;padding: 5px 5px;" doLayout="true">--%>
                <div  style="border:1px solid gray; width:845px;height: 500px; overflow:auto; margin-bottom: 1em;">
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
                                    
                                        
                                           
                                                        
                                                    
                                                <!-- We have to implement Search login in backend-->
                                                <s:form name="teamSearch" action="empTimeSheetSearch" theme="simple" >
                                                    <table cellpadding="0" cellspacing="2" border="0" width="100%">
                                                    <tr>
                                                    <td class="fieldLabel" width="25%">Start&nbsp;Date&nbsp;(mm/dd/yyyy)&nbsp;:</td>
                                                    <td><s:textfield name="startDate" cssClass="inputTextBlueSmall" value="%{startDate}"/><a href="javascript:cal1.popup();">
                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                    </td>
                                                    
                                                    <td class="fieldLabel" width="25%">End&nbsp;Date&nbsp;(mm/dd/yyyy)&nbsp;:</td>
                                                    <td>  <s:textfield name="endDate" cssClass="inputTextBlueSmall" value="%{endDate}" /><a href="javascript:cal2.popup();">
                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                    </td>
 
                                                </tr>
                                                
                                                <tr>
                                                  <%--       <td width="20%" class="fieldLabel">Customer&nbsp;Name&nbsp;:</td>
                                    <td width="1%">  
                                        <s:textfield name="customerName" id="customerName"   cssClass="inputTextBlueCustomer"  value="%{customerName}"  theme="simple" onkeyup="fillCustomer();" />
                                        <div id="validationMessage"></div>
                                        <s:hidden name="customerId" value="%{customerId}" id="customerId"/> 
                                    </td>
                                    
                                    --%>
                                     <td class="fieldLabel" >Projects: </td>
                                        <td >
                                               <s:select list="projectMap" name="projectId" id="projectId" onchange="getEmployeesByProjectId('search');" headerKey="-1" headerValue="--Select Project--" cssClass="inputSelect" value="%{projectId}" disabled="false"/> 
                                             <%--  <s:select list="{}" name="projectId" id="projectId" onchange="getEmployeesByProjectId();" headerKey="-1" headerValue="--Select Project--" cssClass="inputSelect" value="%{projectId}" disabled="false"/> --%>
                                        </td>
                                               
                                                
                                                 <td class="fieldLabel">Select&nbsp;EmpName&nbsp;:</td>
                                                    <td><s:select list="empnamesList" id="empnameById" name="empnameById" headerKey="-1" headerValue="All" cssClass="inputSelectLarge" value="%{empnameById}" />
                                                     
                                                </tr>
                                                
                                                
                                                
                                                
                                                
                                                
                                                
                                                
                                                
                                                
                                                
                                                <tr>
                                                    
                                                   
                                                   
                                                     
                                                     
                                                     
                                                    <td class="fieldLabel">Select&nbsp;Status&nbsp;:</td>
                                                    <td >
                                                       
                                                          <%--  <s:select list="{'Submitted','Disapproved','Entered'}" id="description" name="description" cssClass="inputSelect" value="%{description}" headerKey="" headerValue="--Please Select--"/> --%>
                                                           <s:select list="{'Approved','Submitted','Disapproved','Entered'}" id="description" name="description" cssClass="inputSelect" value="%{description}" headerKey="" headerValue="--Please Select--"/>
                                                          
                                                      
                                                  <%--      <INPUT type="image"  onclick='document.teamSearch.action="empTimeSheetSearch.action"' src="<s:url value="/includes/images/go_21x21.gif"/>" > --%>
                                                    
                                                    </td>
                                                    
                                                    <td></td>
                                                    <td >
                                                        
                                                         <s:submit name="submit" value="Search"  cssClass="buttonBg"/>
                                                     <input type="button" class="buttonBg" value="Clear" onclick="clearTimeSheetSearch();"/>
                                                    </td>
                                                </tr>
                                          <%--      <tr>
                                                    
                                                    
                                                 
                                                  <td></td>
                                                   <td></td>
                                                   <td></td>
                                                   
                                                  <td align="right"> 
                                                            
                                                            
                                                        <INPUT type="image"  onclick='document.teamSearch.action="empTimeSheetSearch.action"' src="<s:url value="/includes/images/go_21x21.gif"/>" > 
                                                         
                                                        </td>
                                                  
                                                  
                                                             
                                        </tr> --%>
                                    </table>
                                                </s:form>
                                                
                                     
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
                                                        System.out.println("The First request");
                                                        String sDate=request.getParameter("startDate");
                                                       
                                                        request.getSession().setAttribute("sDate",sDate);
                                                        String eDate=request.getParameter("endDate");
                                                         System.out.println("The second request");
                                                        request.getSession().setAttribute("eDate",eDate);
                                                        String empName = request.getParameter("empName");
                                                        
                                                       // System.out.println("The First");
                                                        String strSQL = null;
                                                       // System.out.println("hii123-->"+strSQL);
                                                      //  System.out.println("hii321-->"+(String)session.getAttribute("queryTeam"));
                                                       // empType = (String)request.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE);
                                                       // designation = (String)request.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_DESIG);
                                                       
                                                          userId = (String)request.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID);
                                                        
                                                        roleId = request.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString();
                                                        
                                                        workingCountry = (String)request.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();  
                                                        
                                                        
                                                        // System.out.println("The First1");
                                                        //out.println(strSQL+"String Sql is\n");
                                                       //  System.out.println("roleId-->"+roleId);
                                                        
                                                         //   System.out.println("in or");
                                                          //  strSQL = (String)session.getAttribute(ApplicationConstants.QS_MY_TEAMTIMESHEETS_LIST);
                                                          strSQL = (String)session.getAttribute("queryTeam");
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
                                                       
                                                        
                                                          // System.out.println("The First3");
                                                        
                                                        strSQL=request.getSession(false).getAttribute("queryTeam").toString();
                                                        System.out.println("hii-->"+strSQL);
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
                                                        
                                                    //   out.println("Query-->"+strSQL);
                                                    
                                                    %>
                                                    
                                                        
                                                        <s:form method="post" id="frmDBGrid" name="frmDBGrid" theme="simple"  action="">           
                                                            
                                                            <grd:dbgrid id="tblStat" name="tblStat" width="100" pageSize="10" 
                                                                        currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                        dataMember="<%=strSQL%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                               imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"/>
                                                                <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="false"/>
                                                                <grd:rownumcolumn headerText="SNO" width="2"/>
                                                                <grd:imagecolumn  headerText="Remind" width="5"  HAlign="center"  
                                                                                  imageSrc="../../includes/images/DBGrid/newEdit_17x18.png" 
                                                                                  linkUrl="getEmpTimeSheet.action?empID={EmpID}&timeSheetID={TimeSheetId}&resourceType={ResourceType}" imageBorder="0" 
                                                                                  imageWidth="16" imageHeight="16" alterText="Click to edit" />
                                                                <grd:numbercolumn dataField="EmpName" headerText="EmpName" width="19" dataFormat=" "/>
                                                                <grd:textcolumn dataField="ProjectName" headerText="ProjectName" width="10" />
                                                                <grd:textcolumn dataField="Description" headerText="Status" width="10" />
                                                                <grd:datecolumn dataField="DateStart" headerText="Start Date" width="10" dataFormat="MM-dd-yyyy" />
                                                                <grd:datecolumn dataField="DateEnd"	headerText="End Date" width="10" dataFormat="MM-dd-yyyy"/>
                                                                <grd:numbercolumn dataField="TotalHrs" headerText="Billable Hrs" dataFormat="   .0 " width="8" />
                                                                <grd:datecolumn dataField="SubmittedDate"	headerText="Submitted Date" width="12" dataFormat="MM-dd-yyyy"/>                                                                                            
                                                                <grd:datecolumn dataField="ApprovedDate" headerText="ApprovedDate" width="12" dataFormat="MM-dd-yyyy"/>
                                                            </grd:dbgrid>
                                                            
                                                            <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                            <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                            <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                                                            <input type="hidden" name="submitFrom" value="dbGrid">     
                                                            <s:hidden name="empnameById" value="%{empnameById}"/>
                                                            <s:hidden name="startDate" value="%{startDate}"/>
                                                            <s:hidden name="endDate" value="%{endDate}"/>
                                                            <s:hidden name="description" value="%{description}"/>
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
                    
                    <div  id="printTimesheetsTab" class="tabcontent" >
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
                                                <!-- We have to implement Search login in backend-->
                                                
                                                
                                                <s:form name="printTimeSeet" action="getEmpTimeSheetsReport" onsubmit="return checkDates();" theme="simple" target="_blank">
                                                    
                                                    <tr>
                                                        <td class="fieldLabelLeft">Start Date(mm/dd/yyyy):</td>
                                                        <td  ><s:textfield name="printStartDate" id="printStartDate" cssClass="inputTextBlue" /><a style="text-decoration: none" href="javascript:cal3.popup();">
                                                            <img  src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                        </td>
                                                        
                                                        <td  class="fieldLabelLeft">End Date(mm/dd/yyyy):</td>
                                                        <td > <s:textfield name="printEndDate"  id="printEndDate" cssClass="inputTextBlue"/><a style="text-decoration: none" href="javascript:cal4.popup();">
                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                        </td>
                                                    </tr>
                                                    
                                                    
                                                    
                                                    <tr>
                                                         <td   class="fieldLabelLeft">Select EmpName :</td>
                                                     <td ><s:select list="printEmpnamesList" id="empnameById1" name="empnameById1" headerKey="-1" headerValue="All" cssClass="inputSelectLarge" value="%{empnameById1}"/></td> 
                                                        
                                                        <td></td>
                                                        <td> <s:submit name="submit" value="Generate Report"  cssClass="buttonBg"/><input type="button" class="buttonBg" value="Clear" onclick="clearPrintTimeSheet();"/>
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
                         var cal3 = new CalendarTime(document.forms['printTimeSeet'].elements['printStartDate']);
                                                           cal3.year_scroll = true;
                                                           cal3.time_comp = false;
                                                           
                        var cal4 = new CalendarTime(document.forms['printTimeSeet'].elements['printEndDate']);
                                                           cal4.year_scroll = true;
                                                           cal4.time_comp = false;
                        </script>
                        
                    </div>
                  
                                
                                
                                 
                    <div  id="printProjectTimesheetsTab" class="tabcontent" >
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
                                    
                                       
                                                <!-- We have to implement Search login in backend-->
                                                
                                                
                                                <s:form name="timeSeetReport" action="getEmpProjectTimeSheetsReport" theme="simple" target="_blank">
                                                    <table cellpadding="1" cellspacing="2" border="0" width="100%">
                                                    <tr>
                                                        <td class="fieldLabel">Start Date(mm/dd/yyyy):</td>
                                                        <td  ><s:textfield name="reportsStartDate" id="reportsStartDate" cssClass="inputTextBlue" /><a style="text-decoration: none" href="javascript:cal5.popup();">
                                                            <img  src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                        </td>
                                                        
                                                        <td  class="fieldLabel" width="20%">End Date(mm/dd/yyyy):</td>
                                                        <td > <s:textfield name="reportsEndDate"  id="reportsEndDate" cssClass="inputTextBlue"/><a style="text-decoration: none" href="javascript:cal6.popup();">
                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                        </td>
                                                    </tr>
                                                    
                                                                                        
                                                <tr>
                                                  <%--       <td width="20%" class="fieldLabel">Customer&nbsp;Name&nbsp;:</td>
                                    <td width="1%">  
                                        <s:textfield name="customerName" id="customerName"   cssClass="inputTextBlueCustomer"  value="%{customerName}"  theme="simple" onkeyup="fillCustomer();" />
                                        <div id="validationMessage"></div>
                                        <s:hidden name="customerId" value="%{customerId}" id="customerId"/> 
                                    </td>
                                    
                                    --%>
                                     <td class="fieldLabel" >Projects: </td>
                                        <td >
                                               <s:select list="projectMap" name="projectId2" id="projectId2" onchange="getEmployeesByProjectId('print');" headerKey="-1" headerValue="--Select Project--" cssClass="inputSelect" value="%{projectId2}" disabled="false"/> 
                                             <%--  <s:select list="{}" name="projectId" id="projectId" onchange="getEmployeesByProjectId();" headerKey="-1" headerValue="--Select Project--" cssClass="inputSelect" value="%{projectId}" disabled="false"/> --%>
                                        </td>
                                               
                                                
                                                 <td class="fieldLabel">Select&nbsp;EmpName&nbsp;:</td>
                                                 <td><s:select list="reportsEmpnamesList" id="empnameById2" name="empnameById2" headerKey="-1" headerValue="All" cssClass="inputSelectLarge" value="%{empnameById2}" onchange="checkStaus();"/>
                                                     
                                                </tr>
                                                
                                                
                                                      <tr>
                                                    
                                                   
                                                     
                                                    </td>
                                                      <td class="fieldLabel">Select&nbsp;Status&nbsp;:</td>
                                                    <td>
                                                       
                                                        <%--    <s:select list="{'Submitted','Disapproved','Entered','Approved','Not Entered'}" id="description2" name="description" cssClass="inputSelect" value="%{description}" headerKey="" headerValue="--Please Select--"/> --%>
                                                        <s:select list="#@java.util.LinkedHashMap@{'1':'Entered','2':'Submitted','3':'Approved','4':'Not entered'}" id="description2" name="description2" onchange="disableEmpList(this);" cssClass="inputSelect" value="%{description2}" headerKey="-1" headerValue="All"/>
                                                      
                                                        
                                                    
                                                    </td>
                                                      </tr>
                                                    
                                                    <tr>
                                                        <%-- <td   class="fieldLabelLeft">Select EmpName :</td>
                                                     <td ><s:select list="empnamesList" id="empnameById1" name="empnameById1" headerKey="-1" headerValue="All" cssClass="inputSelectLarge" value="%{empnameById}"/></td> 
                                                        
                                                        <td></td> --%>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td> 
                                                             <input type="button" class="buttonBg" value="Generate Excel" onclick="return getEmpTimesheetReport();"/>
                                                             <input type="button" class="buttonBg" value="Clear" onclick="clearTimeSheetReports();"/>
                                                          <%--  <s:submit name="submit" value="Generate Excel"  cssClass="buttonBg"/> --%>
                                                        </td>
                                                    </tr>
                                                    </table>
                                                </s:form>
                                                
                                                
                                         
                                    
                                </td>
                            </tr>
                            
                        </table>
                         <script type="text/JavaScript">
                         var cal5 = new CalendarTime(document.forms['timeSeetReport'].elements['reportsStartDate']);
                                                           cal5.year_scroll = true;
                                                           cal5.time_comp = false;
                                                           
                        var cal6 = new CalendarTime(document.forms['timeSeetReport'].elements['reportsEndDate']);
                                                           cal6.year_scroll = true;
                                                           cal6.time_comp = false;
                        </script>
                        
                    </div>
                                
                                
                                
                                
                                
                   <!-- customer print time sheet end -->


                    <%-- </sx:tabbedpanel> --%>
                </div>
                <script type="text/javascript">

var countries=new ddtabcontent("accountTabs")
countries.setpersist(true)
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
 <tr>
                <td>
                    
                    <div style="display: none; position: absolute; top:170px;left:320px;overflow:auto;" id="menu-popup">
                        <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" />
                    </div>
                    
                </td>
            </tr>
</table>
</body>
</html>
