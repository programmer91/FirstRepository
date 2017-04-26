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
    
    <sx:head cache="true"/>
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
            var teamType  = document.getElementById('teamType').value;
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
                }else{
                     document.getElementById("custnameById").value="All";
                    document.getElementById("custnameById").style.display='none';
                    document.getElementById("empnameById").style.display='block';
                }
            }
            function showEmpType(value)
            {
             //   alert(value);
                if(value == null || value == "" || value == "null"){
                     
                    document.getElementById("custnameById").style.display='none';
                    document.getElementById("empnameById").style.display='block';
                }else if(value == 'Internal'){
                     //document.getElementById("custnameById").value="All";
                    document.getElementById("custnameById").style.display='none';
                    document.getElementById("empnameById").style.display='block';
                }
                else if(value == 'External'){
                     document.getElementById("custnameById").style.display='block';
                    
                    document.getElementById("empnameById").style.display='none';
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
%>

<%
if(request.getAttribute("empCusType")!=null)
{
    empType1 = request.getAttribute("empCusType").toString();
}
%>
<%-- <body class="bodyGeneral"  oncontextmenu="return false;" onload="showEmpType('<%=empType1%>');"> --%>
<body class="bodyGeneral"  oncontextmenu="return false;" >

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
                
                 
<li ><a href="#" rel="printCustTimesheetsTab" >Print TimeSheet</a></li>
                   
                </ul>
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
 
                                                </tr><tr>
                                                    
                                                    <td class="fieldLabelLeft">Select&nbsp;EmpName&nbsp;:</td>
                                                    <td><s:select list="empnamesList" id="empnameById" name="empnameById" headerKey="All" headerValue="All" cssClass="inputSelectLarge" value="%{empnameById}" />
                                                     <%-- <% if(session.getAttribute("empType").toString().equalsIgnoreCase("e")){%>
                                                        <s:select list="custnamesList" id="custnameById" name="custnameById" headerKey="All" headerValue="All" cssClass="inputSelectLarge" value="%{custnameById}" />
                                                            <%} %> --%>
                                                    </td>
                                                     
                                                     
                                                     
                                                    <td class="fieldLabelLeft">Select&nbsp;Status&nbsp;Level1:</td>
                                                    <td>
                                                      <%--  <s:if test="%{#session.roleName == 'Operations'|| #session.customerRole == 8}">
                                                            <s:select list="{'Submitted','Disapproved','Entered'}" id="description" name="description" cssClass="inputSelect" value="%{description}" headerKey="" headerValue="--Please Select--"/>
                                                        </s:if>
                                                        <s:else> --%>
                                                           <s:select list="{'Submitted','Approved','Disapproved','Entered'}" id="description" name="description" cssClass="inputSelect" value="%{description}" headerKey="" headerValue="--Please Select--"/> 
                                                  <%--      </s:else> --%>
                                                        
                                                    
                                                    </td>
                                                </tr>
                                                <tr>
                                                    
                                                      <td class="fieldLabelLeft">Select&nbsp;Status&nbsp;Level2:</td>
                                                    <td>
                                                      <%--  <s:if test="%{#session.roleName == 'Operations'|| #session.customerRole == 8}">
                                                            <s:select list="{'Submitted','Disapproved','Entered'}" id="description" name="description" cssClass="inputSelect" value="%{description}" headerKey="" headerValue="--Please Select--"/>
                                                        </s:if>
                                                        <s:else> --%>
                                                           <s:select list="{'Submitted','Approved','Disapproved','Entered'}" id="secDescription" name="secDescription" cssClass="inputSelect" value="%{secDescription}" headerKey="" headerValue="--Please Select--"/> 
                                                  <%--      </s:else> --%>
                                                        
                                                    
                                                    </td>
                                                   
                                              
                                                   
                                                        <td> <INPUT type="image"  onclick='document.teamSearch.action="newempTimeSheets.action"' src="<s:url value="/includes/images/go_21x21.gif"/>" >
                                                        </td>
                                                   
                                                    
                                                        
                                                </s:form>
                                                </tr>
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
                                                        empType = (String)request.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE);
                                                       // designation = (String)request.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_DESIG);
                                                       
                                                        userId = (String)request.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID);
                                                        
                                                        roleId = request.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString();
                                                        
                                                       roleCusVen= request.getSession(false).getAttribute(ApplicationConstants.SESSION_CUSTOMER_ROLE).toString();
                                                       
                                                        
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
                                                        
                                                      // out.println("---->"+strSQL);
                                                    
                                                    %>
                                                <%--    <s:if test="%{#session.roleName == 'Operations'|| #session.customerRole == 8}"> --%>
                                                        
                                                        <s:form method="post" id="frmDBGrid" name="frmDBGrid" theme="simple"  action="newempTimeSheets.action">           
                                                            
                                                            <grd:dbgrid id="tblStat" name="tblStat" width="100" pageSize="10" 
                                                                        currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                        dataMember="<%=strSQL%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                               imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"/>
                                                                <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="false"/>
                                                                <grd:rownumcolumn headerText="SNO" width="2"/>
                                                            <%--    <grd:imagecolumn  headerText="Remind" width="5"  HAlign="center"  
                                                                                  imageSrc="../../includes/images/DBGrid/newEdit_17x18.png" 
                                                                                  linkUrl="newgetEmpTimeSheet.action?empID={EmpID}&timeSheetID={TimeSheetId}&resourceType={ResourceType}&val=1&statusValue={Description}" imageBorder="0" 
                                                                                  imageWidth="16" imageHeight="16" alterText="Click to edit" /> --%>
                                                            
   <grd:imagecolumn  headerText="Remind" width="5"  HAlign="center"  
                                                                                  imageSrc="../../includes/images/DBGrid/newEdit_17x18.png" 
                                                                                  linkUrl="newgetEmpTimeSheet.action?empID={EmpID}&timeSheetID={TimeSheetId}&resourceType={ResourceType}&val=1&statusValue={Description}&secStatusValue={SecDescription}" imageBorder="0" 
                                                                                  imageWidth="16" imageHeight="16" alterText="Click to edit" />
                                                                <grd:numbercolumn dataField="EmpName" headerText="EmpName" width="19" dataFormat=" "/>
                                                             <%--   <grd:textcolumn dataField="ProjectName" headerText="ProjectName" width="10" /> --%>
                                                               <%-- <grd:textcolumn dataField="Description" headerText="Status" width="10" /> --%>
                                                               																  
 <grd:anchorcolumn dataField="Description" linkUrl="javascript:getDescriptions('{Description}','{SecDescription}')" headerText="Status"
                                                                                      linkText="Click To View"  width="10" />
							
                                                                <grd:datecolumn dataField="DateStart" headerText="Start Date" width="10" dataFormat="MM-dd-yyyy" />
                                                                <grd:datecolumn dataField="DateEnd"	headerText="End Date" width="10" dataFormat="MM-dd-yyyy"/>
                                                                <grd:numbercolumn dataField="TotalHrs" headerText="Billable Hrs" dataFormat="   .0 " width="8" />
                                                                <grd:datecolumn dataField="SubmittedDate"	headerText="Submitted Date" width="12" dataFormat="MM-dd-yyyy"/>                                                                                            
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
                                                <%--    </s:if> --%>
                                                  
                                                    
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
                    
                   
                                <!-- customer print time sheet start -->
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
                                        <tr>
                                            <td>
                                                <!-- We have to implement Search login in backend-->
                                                
                                            
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
                                                           <td class="fieldLabelLeft" id="teamTypeTd" >ReportsTo&nbsp;Level&nbsp;:</td>
                                                    
                                                    <td id="teamTypeListTd" >  <s:select list="#@java.util.LinkedHashMap@{'0':'All','1':'Lower Level','2':'Higher Level'}" id="teamType" name="teamType" cssClass="inputSelect" value="%{teamType}" /></td>
                                                        
                                                        
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
												
												
                                                
                                            </td>
                                        </tr>
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
<script type="text/javascript">
		$(window).load(function(){
		showEmpType('<%=empType1%>');
		});
		</script>
</body>
</html>
