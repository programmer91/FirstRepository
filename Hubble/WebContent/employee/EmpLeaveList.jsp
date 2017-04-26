<!-- 
 *******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  November 20, 2007, 3:25 PM
 *
 * Author  : Rajasekhar Yenduva<ryenduva@miraclesoft.com>
 *
 * File    : LeaveList.jsp
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
-->
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
 <%@ taglib prefix="sx" uri="/struts-dojo-tags" %> 
<%@ page import="com.freeware.gridtag.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hubble Organization Portal :: Employee Leave List</title>
        <sx:head cache="true"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tooltip.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tooltip.js"/>"></script>   
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>   
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/employee/DateValidator.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <s:include value="/includes/template/headerScript.html"/>
    </head>
    <body class="bodyGeneral" oncontextmenu="return false;">
        <%!
        
        /* Declarations */
        Connection connection;
        String queryString;
        String strTmp;
        String strSortCol;
        String strSortOrd;
        String userId;
        String submittedFrom;
        String action;
        
        int role;
        int intSortOrd = 0;
        int intCurr;
        boolean blnSortAsc = true;
        %>
        <!--//START MAIN TABLE : Table for template Structure-->
        <table class="templateTableLogin" align="center" cellpadding="0" cellspacing="0">
            
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
                    <table class="innerTableLogin" cellpadding="0" cellspacing="0">
                        <tr>
                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                            <td width="150px;" class="leftMenuBgColor" valign="top"> 
                                <s:include value="/includes/template/LeftMenu.jsp"/> 
                            </td>
                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                            
                            <!--//START DATA COLUMN : Coloumn for Screen Content-->
                            <td width="850px" class="cellBorder" valign="top" style="padding:10px 5px 5px 5px;">
                                <!--//START TABBED PANNEL : -->
                                <ul id="accountTabs" class="shadetabs" >
                                    <li ><a href="#" class="selected" rel="leaveRequestsTab"  > Leaves List </a></li>
                                    <li ><a href="#" rel="IssuesSearchTab">Leave Search</a></li>
                                </ul>
                                <div  style="border:1px solid gray; width:845px;height: 500px; overflow:auto; margin-bottom: 1em;">
                                    <%--    <sx:tabbedpanel id="attachmentPannel2" cssStyle="width: 845px; height: 500px;padding:10px 5px 5px 5px" doLayout="true"> --%>
                            
                                    
                                    <!--//START TAB : -->
                                    <%--  <sx:div id="leaveRequestsTab" label="Leaves List" cssStyle="overflow:auto;"> --%>
                                    <div id="leaveRequestsTab" class="tabcontent"  >
                                        
                                        <%
                                        /* Declarations */
                                        
                                        %>
                                        
                                        <%
                                        /* String Variable for storing current position of records in dbgrid*/
                                        strTmp = request.getParameter("txtCurr");
                                        
                                        intCurr = 1;
                                        
                                        if (strTmp != null)
                                        intCurr = Integer.parseInt(strTmp);
                                        
                                        /* Specifing Shorting Column */
                                        strSortCol = request.getParameter("txtSortCol");
                                        
                                        if (strSortCol == null) strSortCol = "AttachmentName";
                                        
                                        strSortOrd = request.getParameter("txtSortAsc");
                                        if (strSortOrd == null) strSortOrd = "ASC";
                                        
                                        try{
                                        
                                        /* Getting DataSource using Service Locator */
                                        connection=ConnectionProvider.getInstance().getConnection();
                                        
                                        
                                        /* Sql query for retrieving resultset from DataBase */
                                        String  queryString  =null;
                                        String empLeaveAction = "../employee/leaveRequest.action";
                                        int  empId  = Integer.parseInt((String) session.getAttribute(ApplicationConstants.SESSION_EMP_ID));
                                        // out.println(request.getAttribute("leaveSearchList"));
                                        
                                        if(request.getAttribute("leaveSearchList")!=null){
                                        queryString = request.getAttribute("leaveSearchList").toString();
                                        // out.println(queryString);
                                        }else{
                                        
                                        queryString="SELECT  id,EmpId,StartDate,EndDate,leaveType,reportsTo,Status from tblEmpLeaves  where empId = " + empId + " order by  EndDate desc";
                                        
                                        }
                                       // out.println(1+queryString);
                                        %>
                                        
                                        
                                        <form method="post" id="frmDBGrid" name="frmDBGrid" action=""> 
                                            <table cellpadding="0" cellspacing="0" width="100%">
                                                <tr>
                                                    <td colspan="4" class="headerText">
                                                        <s:property value="#request.resultMessage"/>
                                                        <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <!-- DataGrid for list all activities -->
                                                        <grd:dbgrid id="tblEmpLeaves" name="tblEmpLeaves" width="100" pageSize="15" 
                                                                    currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                    dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                            
                                                            
                                                            <grd:gridpager imgFirst="../includes/images/DBGrid/First.gif" imgPrevious="../includes/images/DBGrid/Previous.gif" 
                                                                           imgNext="../includes/images/DBGrid/Next.gif" imgLast="../includes/images/DBGrid/Last.gif"
                                                                           addImage="../includes/images/DBGrid/Add.png"  addAction="<%=empLeaveAction%>"
                                                            />
                                                            <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" />
                                                            
                                                            <grd:imagecolumn  headerText="Edit" width="5"  HAlign="center" 
                                                                              imageSrc="../includes/images/DBGrid/Edit.gif" 
                                                                              linkUrl="getLeaves.action?id={id}" 
                                                                              imageBorder="0" imageWidth="16" imageHeight="16" 
                                                                              alterText="Click to edit" /> 
                                                            <grd:datecolumn dataField="StartDate"  headerText="StartDate"  dataFormat="MM-dd-yyyy" HAlign="right" width="18" sortable="true"/>
                                                            <grd:datecolumn dataField="EndDate"  headerText="EndDate"  dataFormat="MM-dd-yyyy" HAlign="right" width="18" sortable="true"/>
                                                            <grd:textcolumn dataField="leaveType" headerText="LeaveType"  width="16"  sortable="true"/>
                                                            <grd:textcolumn dataField="Status" headerText="Status"  width="16"  sortable="true"/>
                                                            <grd:textcolumn dataField="reportsTo" headerText="ReportsTo"  width="16"  sortable="true"/>       
                                                            
                                                        </grd:dbgrid>
                                                        
                                                        <!-- these components are DBGrid Specific  -->
                                                        <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                        <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                        <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                                                        
                                                        <!-- <input id="txtDBId" TYPE="hidden" NAME="txtDBId" VALUE="0">
                                                        <input id="txtTRId"TYPE="hidden" NAME="txtTRId" VALUE="1">  -->
                                                    </td>
                                                </tr>
                                            </table>
                                        </form>
                                        
                                        <%
                                        }catch(Exception ex){
                                        out.println(ex.toString());
                                        }finally{
                                        if(connection!= null){
                                        connection.close();
                                        }
                                        }
                                        %>
                                        
                                        
                                        <%--  </sx:div> --%>
                                    </div>
                                    <!--//END TAB : -->
                                    
                               
                                    <!--//END TAB : -->
                                    <!--//END TAB : -->
                                    
                                    
                                    <%--  <sx:div id="IssuesSearchTab" label="Leave Search"   > --%>
                                    <div id="IssuesSearchTab" class="tabcontent"  >
                                        
                                        <s:form name="frmSearch" action="leaveSearch" theme="simple" onsubmit="return compareDates(document.getElementById('startDate').value,document.getElementById('endDate').value);">
                                            
                                            <!--The components of searching issues -->
                                            <table border="0" cellpadding="1" cellspacing="1" width="100%">
                                                <tr>
                                                    <td class="headerText">
                                                        <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                    </td>
                                                </tr>
                                                <tr><td align="left"><table >                                                    
                                                            <td  class="fieldLabel">Status&nbsp;:</td>
                                                            <td><s:select name="statusId" list="{'created','approved','cancelled','applied'}"
                                                                              id="statusId"
                                                                              headerKey="" 
                                                                              headerValue="--Please Select--"
                                                                              value="%{currentIssue.statusId}"
                                                                          cssClass="inputSelect"/></td>
                                                            <td  class="fieldLabel">Start Date&nbsp;:</td>
                                                            <td><s:textfield name="startDate" id="startDate" cssClass="inputTextBlue"/><a class="underscore" href="javascript:cal1.popup();">
                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                 width="20" height="20" border="0"></a></td>
                                                            <td class="fieldLabel">End Date&nbsp;:</td>
                                                            <td><s:textfield name="endDate" id="endDate" cssClass="inputTextBlue"/><a  class="underscore" href="javascript:cal2.popup();">
                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                 width="20" height="20" border="0"></a></td>
                                                            <td>
                                                                <s:hidden name="submitFrom" value="Search"/>
                                                                <s:submit cssClass="buttonBg" value="Search"/>
                                                            </td> 
                                                            
                                                        </table>
                                                    </td>
                                                </tr> 
                                                
                                            </table>
                                            
                                        </s:form>
                                        <!-- this script for After loading Form it will instantiate Calender Objects as you require -->
                                        <script type="text/JavaScript">
                                                           var cal1 = new CalendarTime(document.forms['frmSearch'].elements['startDate']);
                                                           cal1.year_scroll = true;
                                                           //cal1.time_comp = true;
                                            
                                                           var cal2 = new CalendarTime(document.forms['frmSearch'].elements['endDate']);
                                                           cal2.year_scroll = true;
                                                           //cal2.time_comp = true;
                                        </script>
                                        
                                        <%--  </sx:div> --%>
                                    </div>
                                    
                                    <!--//END TAB : -->
                                    <%--</sx:tabbedpanel> --%>
                                </div>
                                <script type="text/javascript">

var countries=new ddtabcontent("accountTabs")
countries.setpersist(false)
countries.setselectedClassTarget("link") //"link" or "linkparent"
countries.init()

                                </script>
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
        
        
        
        
        
    </body>
</html>
