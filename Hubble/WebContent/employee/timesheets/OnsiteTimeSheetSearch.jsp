<!--/*******************************************************************************
/*
 *Project: MirajeV2
 *
 *$Author: rmenda $
 *
 *$Date: 2009-05-20 12:32:49 $
 *
 *$Revision: 1.5 $
 *
 *$Source: /Hubble/Hubble_CVS/Hubble/web/employee/timesheets/OnsiteTimeSheetSearch.jsp,v $
 *
 * @(#)OnsiteTimeSheetSearch.jsp	May 19, 2008, 04:13 PM 
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
<%@ taglib prefix="s" uri="/struts-tags" %>
<%-- <%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hubble Organization Portal :: Onsite TimeSheet Search</title>
        
        <sx:head cache="true"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link REL="StyleSheet" HREF="<s:url value="/includes/css/GridStyle.css"/>" type="text/css">
        <link REL="StyleSheet" HREF="<s:url value="/includes/css/leftMenu.css"/>" type="text/css">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script language="javascript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
        <script language="javascript" src="<s:url value="/includes/javascripts/TimeSheetWeekDays.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/employee/DateValidator.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/javascripts/timesheets/DBGTimesheets.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>       
        <s:include value="/includes/template/headerScript.html"/>
    </head>
    <%!
    Connection connection = null;
    %>
    <body class="bodyGeneral" oncontextmenu="return false;">
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
                            <td width="150px;" class="leftMenuBgColor" valign="top"> 
                                <s:include value="/includes/template/LeftMenu.jsp"/> 
                            </td>
                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                            
                            <!--//START DATA COLUMN : Coloumn for Screen Content-->
                            <td width="850px" class="cellBorder" valign="top">
                                <!--<form action="updateAccounts" name="searchForm">-->
                                <table cellpadding="0" width="100%" cellspacing="0" bordercolor="#efefef">  
                                    <tr>
                                        <td style="padding: 5px 5px;">    
                                            <ul id="accountTabs" class="shadetabs" >
                                                <li ><a href="#" class="selected" rel="onsiteTimeSheetsList"  >OnsiteTimeSheets </a></li>
                                                <li ><a href="#" rel="offshoreTimeSheetsList">OffshoreTimeSheets</a></li>
                                            </ul>
                                            <div  style="border:1px solid gray; width:845px;height: 400px; overflow:auto; margin-bottom: 1em;">
                                                <%-- <sx:tabbedpanel id="TimeSheet" cssStyle="width: 845px; height: 400px;padding: 5px 5px;" doLayout="true" useSelectedTabCookie="true"> --%>
                                                <div id="onsiteTimeSheetsList" class="tabcontent"  > 
                                                    <%-- <sx:div id="onsiteTimeSheetsList" label="OnsiteTimeSheets"> --%>
                                                    <table cellpadding="0" width="100%" cellspacing="0" bordercolor="#efefef">
                                                        <tr>
                                                            <td class="headerText" align="right">
                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <table cellpadding="0" cellspacing="0" border="0" width="750px">
                                                                    <tr>
                                                                        <td>
                                                                            <!-- We have to implement Search login in backend-->
                                                                            <s:form name="search" action="" theme="simple" onsubmit="return compareDates(document.getElementById('startDate').value,document.getElementById('endDate').value);">
                                                                                <td class="fieldLabel">Start Date  (mm/dd/yyyy):</td>
                                                                                <td><s:textfield name="startDate" id="startDate" cssClass="inputTextBlueSmall" value=""/><a href="javascript:cal1.popup();">
                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                                </td>
                                                                                
                                                                                <td class="fieldLabel">End Date  (mm/dd/yyyy):</td>
                                                                                <td>  <s:textfield name="endDate" id="endDate" cssClass="inputTextBlueSmall" value=""/> <a href="javascript:cal2.popup();">
                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                                </td>
                                                                                <td class="fieldLabel">Emp Name :</td>
                                                                                <td>
                                                                                    <s:textfield name="empName" id="empName" cssClass="inputTextBlue"/>&nbsp;&nbsp;
                                                                                </td>
                                                                                <td> <INPUT type="image"  onclick='document.search.action="./OnsiteTimeSheetSearch.jsp"' src="<s:url value="/includes/images/go_21x21.gif"/>" >
                                                                                </td>
                                                                            </s:form>
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
                                                                                    //System.out.println("The First");
                                                                                    
                                                                                    
                                                                                    //empId = (String)request.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID);
                                                                                    
                                                                                    String strSQL="SELECT * from vwOnsiteTimeSheetList WHERE ObjType='Emp TimeSheets'";
                                                                                    
                                                                                    //edit start
                                                                                    if((sDate==null && eDate==null) ){
                                                                                        
                                                                                        if(request.getSession().getAttribute("isFirstOnsite")==null){
                                                                                            request.getSession().setAttribute("isFirstOnsite","yes");
                                                                                        }
                                                                                        if(request.getSession().getAttribute("isFirstOnsite").toString().equals("yes")){
                                                                                            request.getSession().setAttribute("isFirstOnsite","no");
                                                                                            //   here we have to read get empid from session and then subsitute
                                                                                            strSQL = strSQL;
                                                                                            request.getSession().setAttribute("queryOnsite",strSQL);
                                                                                        }
                                                                                        
                                                                                    } else{
                                                                                        
                                                                                        if(sDate.trim()!="" && eDate.trim()!=""  && empName.trim()=="")   {
                                                                                            
                                                                                            strSQL=strSQL+" AND date(DateUpload) between date('"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(sDate))+"') AND date('"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(eDate))+"')";// AND CurStatus = 'Active'";
                                                                                            request.getSession().setAttribute("queryOnsite",strSQL);
                                                                                            
                                                                                        }else if(sDate.trim()=="" && eDate.trim()=="" && empName.trim()!="")   {
                                                                                            strSQL = strSQL+" AND EmpName like '"+empName+"%'";
                                                                                            request.getSession().setAttribute("queryOnsite",strSQL);
                                                                                            
                                                                                        }else if(sDate.trim()!="" && eDate.trim()!="" && empName.trim()!="")   {
                                                                                            strSQL=strSQL+" AND date(DateUpload) between date('"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(sDate))+"') AND date('"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(eDate))+"')";
                                                                                            strSQL = strSQL+" AND EmpName like '"+empName+"%'";
                                                                                            request.getSession().setAttribute("queryOnsite",strSQL);
                                                                                            
                                                                                        }else if((sDate.trim()=="" && eDate.trim()!="")) {
                                                                                            strSQL=strSQL+" AND date(DateUpload)=date('"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(eDate))+"')";
                                                                                            request.getSession().setAttribute("queryOnsite",strSQL);
                                                                                            
                                                                                        }else if(sDate.trim()!="" && eDate.trim()=="") {
                                                                                            strSQL=strSQL+" AND date(DateUpload)=date('"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(sDate))+"')";
                                                                                            request.getSession().setAttribute("queryOnsite",strSQL);
                                                                                            
                                                                                        }else if(sDate.trim()=="" && eDate.trim()=="")   {
                                                                                            strSQL = strSQL+" ORDER BY DateUpload DESC";
                                                                                            request.getSession().setAttribute("queryOnsite",strSQL);
                                                                                        }
                                                                                        
                                                                                    }
                                                                                    
                                                                                    
                                                                                    
                                                                                    strSQL=request.getSession().getAttribute("queryOnsite").toString();
                                                                                    
                                                                                    int intCurr = 1;
                                                                                    int intSortOrd = 0;
                                                                                    String strTmp = null;
                                                                                    boolean blnSortAsc = true;
                                                                                    String strSortCol = null;
                                                                                    String strSortOrd = "DSC";
                                                                                    
                                                                                    strTmp = request.getParameter("txtOnSiteCurr");
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
                                                                                    strSortOrd = request.getParameter("txtOnSiteSortAsc");
                                                                                    if (strSortCol == null) strSortCol = "ObjectId";
                                                                                    if (strSortOrd == null) strSortOrd = "DSC";
                                                                                    blnSortAsc = (strSortOrd.equals("ASC"));
                                                                                    
                                                                                  //  out.print("oncite--->"+strSQL);
                                                                                %>
                                                                                <s:form method="post" id="frmDBGridOnsite" name="frmDBGridOnsite" theme="simple"  action="">           
                                                                                    
                                                                                    
                                                                                    <grd:dbgrid id="tblStat" name="tblStat" width="100" pageSize="10" 
                                                                                                currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                                                dataMember="<%=strSQL%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                                        <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                                                       imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                                                       scriptFunction="getNextOnsite"/>
                                                                                        <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="false"/>
                                                                                        <grd:numbercolumn dataField="ObjId" headerText="EmpId" dataFormat=" " width="10" />
                                                                                        <grd:textcolumn dataField="EmpName" headerText="EmpName" width="15"/>
                                                                                        <grd:textcolumn dataField="FileName" headerText="File Name" width="15"/>
                                                                                        <grd:datecolumn dataField="DateUpload"	headerText="Submitted Date" width="13" dataFormat="MM-dd-yyyy"/>
                                                                                        <grd:textcolumn dataField="Email" headerText="Email" width="15"/>
                                                                                        <grd:imagecolumn headerText="Download" width="4" HAlign="center" 
                                                                                                         imageSrc="../../includes/images/download_11x10.jpg"
                                                                                                         linkUrl="download.action?Id={Id}" imageBorder="0"
                                                                                                         imageWidth="10" imageHeight="10" alterText="Download"></grd:imagecolumn>
                                                                                    </grd:dbgrid>
                                                                                    
                                                                                    <input TYPE="hidden" NAME="txtOnSiteCurr" VALUE="<%=intCurr%>">
                                                                                    <input TYPE="hidden" NAME="txtOnSiteSortCol" VALUE="<%=strSortCol%>">
                                                                                    <input TYPE="hidden" NAME="txtOnSiteSortAsc" VALUE="<%=strSortOrd%>">
                                                                                    <input TYPE="hidden" NAME="txtOffShoreCurr" VALUE="">
                                                                                    
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
                                                    <script type="text/JavaScript">
                                                           var cal1 = new CalendarTime(document.forms['search'].elements['startDate']);
                                                           cal1.year_scroll = true;
                                                           cal1.time_comp = false;
                                                           
                                                           var cal2 = new CalendarTime(document.forms['search'].elements['endDate']);
                                                           cal2.year_scroll = true;
                                                           cal2.time_comp = false;
                                                    </script>
                                                    <%-- </sx:div> --%>
                                                </div>
                                                
                                                <%--    <sx:div id="offshoreTimeSheetsList" label="OffshoreTimeSheets"> --%>
                                                <div id="offshoreTimeSheetsList" class="tabcontent"  >
                                                    <table cellpadding="0" width="100%" cellspacing="0" bordercolor="#efefef">
                                                        <tr>
                                                            <td class="headerText">
                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <table cellpadding="0" cellspacing="0" border="0" width="750px">
                                                                    <tr>
                                                                        <td>
                                                                            <!-- We have to implement Search login in backend-->
                                                                            <s:form name="search1" action="" theme="simple" onsubmit="return compareDates(document.getElementById('startDate1').value,document.getElementById('endDate1').value);">
                                                                                <td class="fieldLabel">Start Date  (mm/dd/yyyy):</td>
                                                                                <td><s:textfield name="startDate1" id="startDate1" cssClass="inputTextBlueSmall" value=""/><a href="javascript:cal3.popup();">
                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                                </td>
                                                                                
                                                                                <td class="fieldLabel">End Date  (mm/dd/yyyy):</td>
                                                                                <td>  <s:textfield name="endDate1" id="endDate1" cssClass="inputTextBlueSmall" value=""/><a href="javascript:cal4.popup();">
                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                                </td>
                                                                                <td class="fieldLabel">Emp Name :</td>
                                                                                <td>
                                                                                    <s:textfield name="empName1" id="empName1" cssClass="inputTextBlue"/>&nbsp;&nbsp;
                                                                                </td>
                                                                                <td> <INPUT type="image"  onclick='document.search1.action="./OnsiteTimeSheetSearch.jsp"' src="<s:url value="/includes/images/go_21x21.gif"/>" >
                                                                                </td>
                                                                            </s:form>
                                                                            
                                                                            
                                                                            
                                                                            
                                                                            
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
                                                                                    String sDate=request.getParameter("startDate1");
                                                                                    
                                                                                    String eDate=request.getParameter("endDate1");
                                                                                    String empName = request.getParameter("empName1");
                                                                                    //System.out.println("The First");
                                                                                    
                                                                                    
                                                                                    //empId = (String)request.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID);
                                                                                    
                                                                                    String strSQL="SELECT * from vwTimeSheetList";
                                                                                    //edit start
                                                                                    if((sDate==null && eDate==null) ){
                                                                                        
                                                                                        if(request.getSession().getAttribute("isFirstOffshore")==null){
                                                                                            request.getSession().setAttribute("isFirstOffshore","yes");
                                                                                            
                                                                                        }
                                                                                        if(request.getSession().getAttribute("isFirstOffshore").toString().equals("yes")){
                                                                                            request.getSession().setAttribute("isFirstOffshore","no");
                                                                                            //   here we have to read get empid from session and then subsitute
                                                                                            strSQL = strSQL;
                                                                                            request.getSession(false).setAttribute("queryOffshore",strSQL);
                                                                                        }
                                                                                        
                                                                                    } else{
                                                                                        
                                                                                        if(sDate.trim()!="" && eDate.trim()!="" && empName.trim()=="")   {
                                                                                            
                                                                                            //System.out.println("The dates ->"+sDate+" :: "+eDate);
                                                                                            
                                                                                            strSQL=strSQL+" WHERE SubmittedDate between '"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(sDate))+"' AND '"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(eDate))+"'";
                                                                                            request.getSession().setAttribute("queryOffshore",strSQL);
                                                                                        }else if(sDate.trim()=="" && eDate.trim()=="" && empName.trim()!="")   {
                                                                                            
                                                                                            strSQL = strSQL+" WHERE EmpName like '"+empName+"%'";
                                                                                            request.getSession().setAttribute("queryOffshore",strSQL);
                                                                                        }else if(sDate.trim()!="" && eDate.trim()!="" && empName.trim()!="")   {
                                                                                            strSQL=strSQL+" WHERE SubmittedDate between '"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(sDate))+"' AND '"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(eDate))+"'";
                                                                                            strSQL = strSQL+" AND EmpName like '"+empName+"%'";
                                                                                            request.getSession().setAttribute("queryOffshore",strSQL);
                                                                                        }else if((sDate.trim()=="" && eDate.trim()!="")) {
                                                                                            strSQL=strSQL+" WHERE DateEnd='"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(eDate))+"'";
                                                                                            request.getSession().setAttribute("queryOffshore",strSQL);
                                                                                        }else if(sDate.trim()!="" && eDate.trim()=="") {
                                                                                            strSQL=strSQL+" WHERE DateStart='"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(sDate))+"'";
                                                                                            request.getSession().setAttribute("queryOffshore",strSQL);
                                                                                        }else if(sDate.trim()=="" && eDate.trim()=="")   {
                                                                                            strSQL = strSQL+" ORDER BY SubmittedDate DESC";
                                                                                            request.getSession().setAttribute("queryOffshore",strSQL);
                                                                                        }
                                                                                        
                                                                                    }
                                                                                    
                                                                                    strSQL=request.getSession(false).getAttribute("queryOffshore").toString();
                                                                                    int intCurr = 1;
                                                                                    int intSortOrd = 0;
                                                                                    String strTmp = null;
                                                                                    boolean blnSortAsc = true;
                                                                                    String strSortCol = null;
                                                                                    String strSortOrd = "DSC";
                                                                                    
                                                                                    strTmp = request.getParameter("txtOffShoreCurr");
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
                                                                                    strSortOrd = request.getParameter("txtOffShoreSortAsc");
                                                                                    if (strSortCol == null) strSortCol = "DateStart";
                                                                                    if (strSortOrd == null) strSortOrd = "DSC";
                                                                                    blnSortAsc = (strSortOrd.equals("ASC"));
                                                                                    
                                                                                    //out.print(strSQL);
                                                                                %>
                                                                                <s:form method="post" id="frmDBGridOffShore" name="frmDBGridOffShore" theme="simple"  action="">           
                                                                                    
                                                                                    
                                                                                    <grd:dbgrid id="tblStat" name="tblStat" width="100" pageSize="10" 
                                                                                                currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                                                dataMember="<%=strSQL%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                                        <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                                                       imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                                                       scriptFunction="getNextOffShore"/>
                                                                                        <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="false"/>
                                                                                        <grd:textcolumn dataField="EmpName" headerText="EmpName" width="15"/>
                                                                                        <grd:textcolumn dataField="Description" headerText="Status"   	width="10" />
                                                                                        <grd:datecolumn dataField="DateStart" headerText="Start Date" width="10" dataFormat="MM-dd-yyyy" />
                                                                                        <grd:datecolumn dataField="DateEnd"	headerText="End Date" width="10" dataFormat="MM-dd-yyyy"/>
                                                                                        <grd:numbercolumn dataField="TotalHrs" headerText="Billable Hrs" dataFormat="   .0 " width="8" />
                                                                                        <grd:datecolumn dataField="SubmittedDate"	headerText="Submitted Date" width="13" dataFormat="MM-dd-yyyy"/>
                                                                                        <grd:datecolumn dataField="ApprovedDate"	headerText="Approved Date" width="13" dataFormat="MM-dd-yyyy"/>                                                                            
                                                                                    </grd:dbgrid>
                                                                                    
                                                                                    <input TYPE="hidden" NAME="txtOffShoreCurr" VALUE="<%=intCurr%>">
                                                                                    <input TYPE="hidden" NAME="txtOffShoreSortCol" VALUE="<%=strSortCol%>">
                                                                                    <input TYPE="hidden" NAME="txtOffShoreSortAsc" VALUE="<%=strSortOrd%>">
                                                                                    <input TYPE="hidden" NAME="txtOnSiteCurr" VALUE="">
                                                                                    
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
                                                    <script type="text/JavaScript">
                                                        var cal3 = new CalendarTime(document.forms['search1'].elements['startDate1']);
                                                        cal3.year_scroll = true;
                                                        cal3.time_comp = false;
                                                        var cal4 = new CalendarTime(document.forms['search1'].elements['endDate1']);
                                                        cal4.year_scroll = true;
                                                        cal4.time_comp = false;
                                                    </script>
                                                    <%-- </sx:div> --%>
                                                </div>
                                                <%--  </sx:tabbedpanel> --%>
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
                                <!--</form>-->
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
    </body>
</html>
