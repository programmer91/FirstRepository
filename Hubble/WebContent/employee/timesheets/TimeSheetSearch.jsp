<!--/*******************************************************************************
/*
 *Project: MirajeV2
 *
 *$Author: rteppala $
 *
 *$Date: 2009-05-11 13:28:44 $
 *
 *$Revision: 1.5 $
 *
 *$Source: /Hubble/Hubble_CVS/Hubble/web/employee/timesheets/TimeSheetSearch.jsp,v $
 *
 * @(#)TimeSheetSearch.jsp	September 24, 2007, 12:13 AM 
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/ -->
<%@ page contentType="text/html; charset=UTF-8" %>

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
        
        <title>Hubble Organization Portal :: TimeSheet Search</title>
        
        <sx:head cache="true"/>
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
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/employee/DateValidator.js"/>"></script>
        <s:include value="/includes/template/headerScript.html"/>
        
          <script>
        function checkDateForTimesheet()
        {
            var radios = document.getElementsByName('checkDate');
             var timesheetDate = document.getElementById("previousWeek").value;
            for (var i = 0, length = radios.length; i < length; i++) 
            {
                if (radios[i].checked) {
                      if(radios[i].value=="PreviousWeek"){
                              if(timesheetDate==null || timesheetDate=="")
                                 {
                                     alert("Please select Date from calender popup");
                                     return false;
                                 }
                                     }
                                        }
            }
                return true;
        }
        </script>
    </head>
    <%!
    Connection connection = null;
    String empId = null;
    String empType = null;
    String strSQL = null;
       //Logger log=null;
    %>
    <body class="bodyGeneral"  oncontextmenu="return false;">
        <table class="templateTable" align="center" cellpadding="0" cellspacing="0">
            <tr class="headerBg">
                <td valign="top">
                    <s:include value="/includes/template/Header.jsp"/>                    
                </td>
            </tr>
            <tr>
                <td>
                    <table class="innerTable" cellpadding="0" cellspacing="0">
                        <tr>
                            <td width="150px;" class="leftMenuBgColor" valign="top">
                                <s:include value="/includes/template/LeftMenu.jsp"/>                    
                            </td>
                            <td width="850px" class="cellBorder" valign="top">
                                <table cellpadding="0" width="100%" cellspacing="0" bordercolor="#efefef">  
                                    <tr>
                                        <td style="padding: 5px 5px;">
                                            <s:if test="%{isOnsite != 'Onsite'}">
                                                <ul id="accountTabs" class="shadetabs" >
                                                    <li ><a href="#" class="selected" rel="timeSheetsListTab"  > TimeSheet </a></li>
                                                </ul>
                                                
                                                <%-- <sx:tabbedpanel  id="timeSheetsListPannel" cssStyle="width: 845px; height: 400px;padding: 5px 5px;" doLayout="true"> --%>
                                                
                                                <div  style="border:1px solid gray; width:845px;height: 400px; overflow:auto; margin-bottom: 1em;">
                                                    <%-- <sx:div id="timeSheetsListTab" label="TimeSheet"> --%>
                                                    <div id="timeSheetsListTab" class="tabcontent"  >
                                                        <table cellpadding="0" width="100%" cellspacing="0" bordercolor="#efefef">  
                                                            <tr>
                                                                <td class="headerText" align="right">
                                                                    <!--    <input type="button" id="Clear The Search Result" value=" Hide " class="buttonBg"  onClick="clearSearchResult();"/> &nbsp;
                                                                <input type="button"  disabled="disabled" id="Show The Search Result" value=" Show " class="buttonBg"  onClick="showSearchResult();"/>            -->
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td>
                                                                    <table cellpadding="0" cellspacing="0" border="0" width="600px">
                                                                        <tr>
                                                                            <td>
                                                                                <!-- We have to implement Search login in backend-->
                                                                                <s:form name="search" action="" theme="simple" method="post" onsubmit="return (validateDates() && compareDates(document.getElementById('startDate').value,document.getElementById('endDate').value));">
                                                                                    <td class="fieldLabel">Start&nbsp;Date&nbsp;(mm/dd/yyyy)&nbsp;:</td>
                                                                                    <td><s:textfield name="startDate" id="startDate" cssClass="inputTextBlueSmall" value=""/><a href="javascript:cal1.popup();">
                                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                                    </td>
                                                                                    
                                                                                    <td class="fieldLabel">End&nbsp;Date&nbsp;(mm/dd/yyyy)&nbsp;:</td>
                                                                                    <td>  <s:textfield name="endDate" id="endDate" cssClass="inputTextBlueSmall" value=""/><a href="javascript:cal2.popup();">
                                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                                    </td>
                                                                                   <%-- <td> <INPUT type="image"  onclick='document.search.action="./TimeSheetSearch.jsp"' src="<s:url value="/includes/images/go_21x21.gif"/>" >
                                                                                    </td> --%>
                                                                                   <td> <INPUT type="image"  onclick='document.search.action="timeSheet.action"' src="<s:url value="/includes/images/go_21x21.gif"/>" ></td>
                                                                                </s:form>
                                                                                    
                                                                                
                                                                                <script type="text/JavaScript">
                                                                                    var cal1 = new CalendarTime(document.forms['search'].elements['startDate']);
                                                                                    cal1.year_scroll = true;
                                                                                    cal1.time_comp = false;
                                                                                    var cal2 = new CalendarTime(document.forms['search'].elements['endDate']);
                                                                                    cal2.year_scroll = true;
                                                                                    cal2.time_comp = false;
                                                                                </script>    
                                                                                
                                                                                
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
                                                                                   //     System.out.println("The First");
                                                                                        
                                                                                        
                                                                                        
                                                                                        
                                                                                        empType = (String)request.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE);
                                                                                        
                                                                                        
                                                                                       if(empType.equalsIgnoreCase("e")){
                                                                                        empId = (String)request.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID);
                                                                                        strSQL="SELECT * from vwTimeSheetList WHERE EmpId="+empId;
                                                                                       }else{
                                                                                        empId = (String)request.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID);
                                                                                        strSQL="SELECT * from vwCustTimeSheetList WHERE EmpId="+empId +" AND ProjectId!=0 AND ProjectId IN ("+request.getSession(false).getAttribute(ApplicationConstants.SESSION_ASSOCIATED_PROJECTSIDS).toString()+") ";
                                                                                       }
                                                                                        
                                                                                        //edit start
                                                                                      //  System.out.println(strSQL);
                                                                                        if((sDate==null && eDate==null) ){
                                                                                            
                                                                                            if(request.getSession().getAttribute("isFirst")==null){
                                                                                                request.getSession().setAttribute("isFirst","yes");
                                                                                            }
                                                                                            if(request.getSession().getAttribute("isFirst").toString().equals("yes")){
                                                                                                request.getSession().setAttribute("isFirst","no");
                                                                                                //   here we have to read get empid from session and then subsitute
                                                                                                strSQL = strSQL+" ORDER BY DateStart DESC";
                                                                                                request.getSession().setAttribute("Empquery",strSQL);
                                                                                            }
                                                                                            
                                                                                        } else{
                                                                                            
                                                                                            if(sDate.trim()!="" && eDate.trim()!="")   {
                                                                                                
                                                                                                //System.out.println("The dates ->"+sDate+" :: "+eDate);
                                                                                                
                                                                                                strSQL=strSQL+" AND date(SubmittedDate) between date('"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(sDate))+"') AND date('"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(eDate))+"')";
                                                                                                request.getSession().setAttribute("Empquery",strSQL+" ORDER BY DateStart DESC");
                                                                                            } else if((sDate.trim()=="" && eDate.trim()!="")) {
                                                                                                strSQL=strSQL+" AND date(DateEnd)=date('"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(eDate))+"')";
                                                                                                request.getSession().setAttribute("Empquery",strSQL+" ORDER BY DateStart DESC");
                                                                                            } else if(sDate.trim()!="" && eDate.trim()=="") {
                                                                                                strSQL=strSQL+" AND date(DateStart)=date('"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(sDate))+"')";
                                                                                                request.getSession().setAttribute("Empquery",strSQL+" ORDER BY DateStart DESC");
                                                                                            } else
                                                                                                if(sDate.trim()=="" && eDate.trim()=="")   {
                                                                                                strSQL = strSQL+" ORDER BY DateStart DESC";
                                                                                                request.getSession().setAttribute("Empquery",strSQL);
                                                                                                }
                                                                                        }
                                                                                        
                                                                                        strSQL=request.getSession().getAttribute("Empquery").toString();
                                                                                        
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
                                                                                        
                                                                                        //out.print(strSQL);
                                                                                    %>
                                                                                    <s:form method="post" id="frmDBGrid" name="frmDBGrid" theme="simple"  action="">           
                                                                                        
                                                                                        
                                                                                        <grd:dbgrid id="tblStat" name="tblStat" width="100" pageSize="10" 
                                                                                                    currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                                                    dataMember="<%=strSQL%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                                            <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                                                           imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"/>
                                                                                            <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="false"/>
                                                                                            <grd:imagecolumn  headerText="Edit" width="5"  HAlign="center"  imageSrc="../../includes/images/DBGrid/newEdit_17x18.png" linkUrl="getTimesheet.action?empID={empID}&timeSheetID={TimeSheetId}&projectId={ProjectId}" imageBorder="0" imageWidth="16" imageHeight="16" alterText="Click to edit" />
                                                                                             <grd:textcolumn dataField="ProjectName" headerText="ProjectName" width="10" />
                                                                                            <grd:textcolumn dataField="Description" headerText="Status"   	width="10" />
                                                                                            <grd:datecolumn dataField="DateStart" headerText="Start Date" width="10" dataFormat="MM-dd-yyyy" />
                                                                                            <grd:datecolumn dataField="DateEnd"	headerText="End Date" width="10" dataFormat="MM-dd-yyyy"/>
                                                                                            <grd:numbercolumn dataField="TotalHrs" headerText="Billable Hrs" dataFormat="   .0 " width="8" />
                                                                                            <grd:datecolumn dataField="SubmittedDate" headerText="Submitted Date" width="13" dataFormat="MM-dd-yyyy"/>
                                                                                            <grd:datecolumn dataField="ApprovedDate" headerText="Approved Date" width="13" dataFormat="MM-dd-yyyy"/>
                                                                                            <grd:imagecolumn headerText="Delete" width="4" HAlign="center" 
                                                                                                             imageSrc="../../includes/images/DBGrid/Delete.png"
                                                                                                             linkUrl="deleteTimeSheet.action?Id={Id}&empID={empID}&timeSheetID={TimeSheetId}&projectId={ProjectId}" imageBorder="0"
                                                                                                             imageWidth="51" imageHeight="20" alterText="Delete"></grd:imagecolumn>
                                                                                        </grd:dbgrid>
                                                                                        
                                                                                        <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                                                        <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                                                        <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                                                                                        
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
                                                                                    } catch(Exception ex) {
                                                                                        ex.printStackTrace();
                                                                                        // log.setLevel((Level)Level.ERROR);
                                                                                        //log.error("The Error @addTimeSheet()",ex);
                                                                                    } finally {
                                                                                        if(connection!=null){
                                                                                            connection.close();
                                                                                            connection = null;
                                                                                        }
                                                                                    } // finally
                                                                    %>
                                                                </td>      
                                                            </tr>
                                                        </table>
                                                        <%--</sx:div> --%>
                                                    </div>
                                                    <%-- </sx:tabbedpanel> --%>
                                                </div>
                                                <script type="text/javascript">

var countries=new ddtabcontent("accountTabs")
countries.setpersist(false)
countries.setselectedClassTarget("link") //"link" or "linkparent"
countries.init()

                                                </script>
                                            </s:if>
                                            <s:else>
                                                
                                                <%--<sx:tabbedpanel  id="timeSheetsListPannel" cssStyle="width: 845px; height: 400px;padding: 5px 5px;" doLayout="true"> --%>
                                                <ul id="accountTabs1" class="shadetabs" >
                                                    <li ><a href="#" class="selected" rel="timeSheetsListTab"  >TimeSheet</a></li>
                                                </ul>
                                                <div  style="border:1px solid gray; width:845px;height: 400px; overflow:auto; margin-bottom: 1em;">
                                                    <%--  <sx:div id="timeSheetsListTab" label="TimeSheet"> --%>
                                                    <div id="timeSheetsListTab" class="tabcontent"  >
                                                        <table cellpadding="0" width="100%" cellspacing="0" bordercolor="#efefef">  
                                                            <tr>
                                                                <td class="headerText" align="right">
                                                                    <!--    <input type="button" id="Clear The Search Result" value=" Hide " class="buttonBg"  onClick="clearSearchResult();"/> &nbsp;
                                                                <input type="button"  disabled="disabled" id="Show The Search Result" value=" Show " class="buttonBg"  onClick="showSearchResult();"/>            -->
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td>
                                                                    <table cellpadding="0" cellspacing="0" border="0" width="600px">
                                                                        <tr>
                                                                            <td>
                                                                                <!-- We have to implement Search login in backend-->
                                                                                <s:form name="search" action="" theme="simple">
                                                                                    <td class="fieldLabel">Start Date  (mm/dd/yyyy):</td>
                                                                                    <td><s:textfield name="startDate" cssClass="inputTextBlueSmall" value=""/><a href="javascript:cal1.popup();">
                                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                                    </td>
                                                                                    
                                                                                    <td class="fieldLabel">End Date  (mm/dd/yyyy):</td>
                                                                                    <td>  <s:textfield name="endDate" cssClass="inputTextBlueSmall" value=""/><a href="javascript:cal2.popup();">
                                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                                    </td>
                                                                                    <td> <INPUT type="image"  onclick='document.search.action="timeSheet.action"' src="<s:url value="/includes/images/go_21x21.gif"/>" >
                                                                                    </td>
                                                                                </s:form>
                                                                                
                                                                                <script type="text/JavaScript">
                                                                                    var cal1 = new CalendarTime(document.forms['search'].elements['startDate']);
                                                                                    cal1.year_scroll = true;
                                                                                    cal1.time_comp = false;
                                                                                    var cal2 = new CalendarTime(document.forms['search'].elements['endDate']);
                                                                                    cal2.year_scroll = true;
                                                                                    cal2.time_comp = false;
                                                                                </script>
                                                                                
                                                                                
                                                                                
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
                                                                                        //System.out.println("The First");
                                                                                        
                                                                                        
                                                                                        empId = (String)request.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID);
                                                                                        
                                                                                        String strSQL="SELECT * from tblEmpAttachments WHERE ObjectId='"+empId+"' AND ObjectType='Emp TimeSheets'";
                                                                                        
                                                                                        //edit start
                                                                                        if((sDate==null && eDate==null) ){
                                                                                            
                                                                                            if(request.getSession().getAttribute("isFirst")==null){
                                                                                                request.getSession().setAttribute("isFirst","yes");
                                                                                            }
                                                                                            if(request.getSession().getAttribute("isFirst").toString().equals("yes")){
                                                                                                request.getSession().setAttribute("isFirst","no");
                                                                                                //   here we have to read get empid from session and then subsitute
                                                                                                strSQL = strSQL;
                                                                                                request.getSession().setAttribute("Onsitequery",strSQL);
                                                                                            }
                                                                                            
                                                                                        } else{
                                                                                            
                                                                                            if(sDate.trim()!="" && eDate.trim()!="")   {
                                                                                                
                                                                                                //System.out.println("The dates ->"+sDate+" :: "+eDate);
                                                                                                
                                                                                                strSQL=strSQL+" AND date(DateUploaded) between date('"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(sDate))+"') AND date('"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(eDate))+"')";
                                                                                                request.getSession().setAttribute("Onsitequery",strSQL);
                                                                                            } else
                                                                                                if((sDate.trim()=="" && eDate.trim()!="")) {
                                                                                                strSQL=strSQL+" AND date(DateUploaded)=date('"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(eDate))+"')";
                                                                                                request.getSession().setAttribute("Onsitequery",strSQL);
                                                                                                } else
                                                                                                    if(sDate.trim()!="" && eDate.trim()=="") {
                                                                                                strSQL=strSQL+" AND date(DateUploaded)=date('"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(sDate))+"')";
                                                                                                request.getSession().setAttribute("Onsitequery",strSQL);
                                                                                                    } else
                                                                                                        if(sDate.trim()=="" && eDate.trim()=="")   {
                                                                                                strSQL = strSQL+" ORDER BY DateUploaded DESC";
                                                                                                request.getSession().setAttribute("Onsitequery",strSQL);
                                                                                                        }
                                                                                            
                                                                                        }
                                                                                        
                                                                                        
                                                                                        
                                                                                        strSQL=request.getSession().getAttribute("Onsitequery").toString();
                                                                                        
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
                                                                                        
                                                                                        //out.print(strSQL);
                                                                                    %>
                                                                                    <s:form method="post" id="frmDBGrid" name="frmDBGrid" theme="simple"  action="">           
                                                                                        
                                                                                        
                                                                                        <grd:dbgrid id="tblStat" name="tblStat" width="100" pageSize="10" 
                                                                                                    currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                                                    dataMember="<%=strSQL%>" dataSource="<%=connection%>" cssClass="gridTable" >
                                                                                            <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                                                           imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"/>
                                                                                            <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="false"/>                                                                                            
                                                                                            <grd:rownumcolumn headerText="Sno" width="5"/>
                                                                                            <grd:textcolumn dataField="AttachmentFileName" headerText="File Name" width="18"/>
                                                                                            <grd:datecolumn dataField="DateUploaded"	headerText="Uploaded Date" width="15" dataFormat="MM-dd-yyyy"/>
                                                                                            <grd:imagecolumn headerText="Download" width="6" HAlign="center" 
                                                                                                             imageSrc="../../includes/images/download_11x10.jpg"
                                                                                                             linkUrl="download.action?Id={Id}" imageBorder="0"
                                                                                                             imageWidth="11" imageHeight="10" alterText="Download"></grd:imagecolumn>
                                                                                            <grd:imagecolumn headerText="Delete" width="6" HAlign="center" 
                                                                                                             imageSrc="../../includes/images/DBGrid/Delete.png"
                                                                                                             linkUrl="deleteOnsiteTimeSheet.action?Id={Id}" imageBorder="0"
                                                                                                             imageWidth="51" imageHeight="20" alterText="Delete"></grd:imagecolumn>
                                                                                        </grd:dbgrid>
                                                                                        
                                                                                        <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                                                        <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                                                        <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                                                                                        
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
                                                                                    } catch(Exception ex) {
                                                                                        ex.printStackTrace();
                                                                                        // log.setLevel((Level)Level.ERROR);
                                                                                        //log.error("The Error @addTimeSheet()",ex);
                                                                                    } finally {
                                                                                        if(connection!=null){
                                                                                            connection.close();
                                                                                            connection = null;
                                                                                        }
                                                                                    } // finally
                                                                    %>
                                                                </td>      
                                                            </tr>
                                                        </table>
                                                        <%--   </sx:div> --%>
                                                    </div>
                                                    <%--  </sx:tabbedpanel> --%>
                                                </div>
                                                <script type="text/javascript">

var countries=new ddtabcontent("accountTabs1")
countries.setpersist(false)
countries.setselectedClassTarget("link") //"link" or "linkparent"
countries.init()

                                                </script>
                                            </s:else>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="padding: 5px 5px;"> 
                                            <ul id="accountTabs2" class="shadetabs" >
                                                <s:if test="%{isOnsite != 'Onsite'}">
                                                    <li ><a href="#" class="selected" rel="timeSheetsAddTab"  > AddTimeSheet </a></li>
                                                </s:if>
                                               <s:if test="%{isOnsite == 'Onsite'}">
                                                <li ><a href="#" rel="UploadTimeSheetId">Upload Time Sheet</a></li>
                                                 </s:if>
                                            </ul>
                                            
                                            <%-- <sx:tabbedpanel  id="timeSheetsAddPannel" cssStyle="width: 845px; height: 250px;padding: 5px 5px;" doLayout="true" >--%>
                                            <div  style="border:1px solid gray; width:845px;height: 250px; overflow:auto; margin-bottom: 1em;">
                                                <s:if test="%{isOnsite != 'Onsite'}">
                                                    <%-- <sx:div id="timeSheetsAddTab" label="AddTimeSheet"> --%>
                                                    <div id="timeSheetsAddTab" class="tabcontent"  >
                                                        <s:form name="timeSheetSubmit" id="timeSheetSubmit" action="fillTimesheet" theme="simple" onsubmit="return checkDateForTimesheet();"> <!--action="employee" -->
                                                            <table cellpadding="0" border="0" cellspacing="0" width="100%">
                                                                <tr>
                                                                    <td class="headerText">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        <table cellpadding="1" cellspacing="1" align="center" width="400px" class="cellBorder">
                                                                            <tr>
                                                                                <td colspan="2" class="headerText" align="center">
                                                                                    Please Select  any one choice
                                                                                </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td colspan="2">
                                                                                    <input type="radio" id="checkDate" name="checkDate"  value="ThisWeek" checked >
                                                                                        <font color="blue" size="2">Submit Time Sheet for this week </font>
                                                                                </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td><input type="radio" id="checkDate" name="checkDate" value="PreviousWeek" /> 
                                                                                <font color="blue" size="2">Previous week Starting On :</font></td>
                                                                                <td><s:textfield id="previousWeek" name="previousWeek" cssClass="inputTextBlueSmall"/><a href="javascript:cal3.popup();">
                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                                </td>
                                                                                <%--<td>
                                                                                    <sx:datetimepicker name="previousWeek" id="previousWeek"/>
                                                                                </td>--%>
                                                                            </tr>
                                                                            
 <tr>
                                                                                <td> 
                                                                                    <font color="blue" size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Projects :</font></td>
                                                                                <td>
                                                                                <s:if test='#session.empType == "e"'>    
                                                                                <s:select list="myProjects" name="projectId" id="projectId"  headerKey="0" headerValue="Internal" cssClass="inputLargeSelect" value="" disabled="false"/>         
                                                                                </s:if>
                                                                                <s:else>
                                                                                <s:select list="myProjects" name="projectId" id="projectId" cssClass="inputLargeSelect" value="" disabled="false"/>             
                                                                                </s:else>    
                                                                                </td>
                                                                                <%--<td>
                                                                                    <sx:datetimepicker name="previousWeek" id="previousWeek"/>
                                                                                </td>--%>
                                                                            </tr>
                                                                            
                                                                            <tr>
                                                                                <td colspan="2" align="center">
                                                                                    <br>
                                                                                </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td colspan="2" align="center">
                                                                                    
                                                                                    <s:submit value="Fill Time Sheet" name="fillTimeSheet" id="fillTimeSheet" onclick="return checkBlankFeilds();" cssClass="buttonBg"  />    <!-- document.timeSheetAdd.action="timeSheetAction.action";   -->
                                                                                </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td colspan="2" align="center">
                                                                                    <%
                                                                                    if(request.getAttribute(ApplicationConstants.RESULT_MSG)!=null){
                                                                                    out.println(request.getAttribute(ApplicationConstants.RESULT_MSG));
                                                                                    
                                                                                    }
                                                                                    %>   
                                                                                </td>
                                                                            </tr>
                                                                        </table>
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                            <script type="text/JavaScript">
                                                                var cal3 = new CalendarTime(document.forms['timeSheetSubmit'].elements['previousWeek']);
                                                                cal3.year_scroll = true;
                                                                cal3.time_comp = false;
                                                            </script>
                                                        </s:form>
                                                        <%-- </sx:div> --%>
                                                    </div>
                                                </s:if>
                                                <s:else>
                                                    <%-- <sx:div id="UploadTimeSheetId" label="Upload TimeSheet"> --%>
                                                    <div id="UploadTimeSheetId" class="tabcontent"  >
                                                        <s:form name="uploadForm" action="uploadTimeSheet" method="post" theme="simple" enctype="multipart/form-data">
                                                            <table cellpadding="0" border="0" cellspacing="0" width="100%">
                                                                <tr>
                                                                    <td class="headerText">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        <table cellpadding="1" cellspacing="1" border="0" align="center" width="500px" class="cellBorder">
                                                                            <tr>
                                                                                <td colspan="4" class="headerText" align="center">
                                                                                    Upload TimeSheet Document
                                                                                </td>
                                                                            </tr>
                                                                            <%--<tr class="fieldLabel">
                                                                                <td>
                                                                                    Start Date(mm/dd/yyyy):<sx:datetimepicker name="sdate"/>
                                                                                </td>
                                                                                <td>
                                                                                    End Date(mm/dd/yyyy):<sx:datetimepicker name="edate"/>
                                                                                </td>
                                                                            </tr>--%>
                                                                            <tr>
                                                                                <td colspan="2" align="center">
                                                                                    <br>
                                                                                </td>
                                                                            </tr>
                                                                            <tr class="fieldLabel">
                                                                                <td colspan="4" align="center">
                                                                                    <s:file cssClass="inputTextBlueLarge" name="upload" theme="simple"/><br>
                                                                                    <s:submit value="Upload" cssClass="ButtonBg"/>
                                                                                </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td colspan="2" align="center">
                                                                                    <%
                                                                                    if(request.getAttribute(ApplicationConstants.RESULT_MSG)!=null){
                                                                                    out.println(request.getAttribute(ApplicationConstants.RESULT_MSG));
                                                                                    
                                                                                    }
                                                                                    %>   
                                                                                </td>
                                                                            </tr>
                                                                        </table>
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </s:form>
                                                        <%-- </sx:div> --%>
                                                    </div>
                                                </s:else>
                                                <%-- </sx:tabbedpanel> --%>
                                            </div>
                                              <script type="text/javascript">

var countries=new ddtabcontent("accountTabs2")
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
