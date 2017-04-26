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
* File    : IssuesList.jsp
*
* Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
* MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
* *****************************************************************************
*/
-->
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%--<%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<%@ page import="com.freeware.gridtag.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hubble Organization Portal :: Employee Tasks List</title>
    <sx:head cache="true"/>
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tooltip.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tooltip.js"/>"></script>   
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>   
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>   
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/employee/DateValidator.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ajax/AjaxPopup.js"/>"></script> 
    <script type="text/javascript" src="<s:url value="/includes/javascripts/searchIssue.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmpStandardClientValidations.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
    <script type="text/javascript" src="<s:url value="/includes/javascripts/IssueFill.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmployeeAjax.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/TaskManagementValidations.js"/>"></script>

    <s:include value="/includes/template/headerScript.html"/>
    <%-- for issue reminder popup --%>
    <script type="text/javascript">
        function win_open2(url,id,priemail){
            // alert(priemail.length);
            if(priemail.length <=1)
            {
                alert("This task is not assigned to any person.Please assign the issue before sending reminder");
            }else{
                //  alert("id-->"+id);
                // alert("url---->"+url);
                // var values=document.getElementById('mailid').innerHTML;
                url = url+"?issueid="+id;
                newWindow=window.open(url,'issueid','height=230,width=540');
            }
        }               
            
        function isNumeric(element){
            var val=element.value;
            if (isNaN(val)) {
                alert("Please enter numeric values");    
                element.value=val.substring(0, val.length-1);   
                element.value='';
                return false;
            }
            else
                return true;
        }
        
    </script>
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
    <%
        String check = null;

        if (request.getAttribute("check") != null) {
            check = request.getAttribute("check").toString();
        }
    %> 

    <script>
        function  showCustomerProjectNameDiv(element){
            if(element.value==2){
                document.getElementById('customerProjectNameTr').style.display='';
            }else{
                document.getElementById('customerProjectNameTr').style.display='none';
            }
        }
        
        
        
    
       
      

    </script>
</head>
<%-- <body class="bodyGeneral" onload="init1('<%=check%>');showCustomerProjectNameDiv(issueRelType);getIssueTypeBasedOnIssueRelType(issueRelType);" oncontextmenu="return false;"> --%>
<body class="bodyGeneral"  oncontextmenu="return false;">
    <%!
        /* Declarations */
        Connection connection;
        String queryString;
        String strTmp;
        String strSortCol;
        String strSortOrd;
        String action;
        int role;
        int intSortOrd = 0;
        int intCurr;
        String currentSearch = null;
        boolean blnSortAsc = true;
        HttpServletRequest httpServletRequest;
    %>


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
                        <td width="850px" class="cellBorder" valign="top" style="padding:5px 5px;">
                            <!--//START TABBED PANNEL : -->
                            <%
                                //out.print("naga::"+ request.getAttribute("subnavigateTo").toString());
                                /// session.setAttribute(ApplicationConstants.ISSUE_TO_TASK,request.getAttribute("subnavigateTo").toString());
%>

                            <ul id="accountTabs" class="shadetabs" >

                                <%--    <% if(request.getParameter("issueList")==null)
                                       {%> --%>

                                <li ><a href="#" class="selected" rel="issuesTab"  > Tasks List </a></li>
                                <li ><a href="#" rel="IssuesSearchTab">Task Search</a></li>
                                <%--   <%}else{%>
                                   <li ><a href="#" class="selected" rel="IssuesSearchTab">Tasks Search</a></li>
                                    <% }%> --%>

                            </ul>

                            <%-- <sx:tabbedpanel id="IssuesPanel" cssStyle="width: 845px; height: 500px;padding:5px 5px;" doLayout="true"> --%>
                            <div  style="border:1px solid gray; width: 857px;height: 500px; overflow:auto; margin-bottom: 1em;">
                                <!--//START TAB : -->

                                <%--  <% if(request.getParameter("issueList")==null)
                                         {
                                             System.out.println("list");
                                         %> --%>
                                <div id="issuesTab" class="tabcontent"  >

                                    <%

                                        try {
                                            queryString = "";
                                            /* Getting DataSource using Service Locator */
                                            connection = ConnectionProvider.getInstance().getConnection();

                                            // System.out.println("list1");
                                        /* String Variable for storing current position of records in dbgrid*/
                                            strTmp = request.getParameter("txtCurr");
                                            intCurr = 1;
                                            if (strTmp != null) {
                                                intCurr = Integer.parseInt(strTmp);
                                            }

                                            /* Specifing Shorting Column */
                                            strSortCol = request.getParameter("txtSortCol");

                                            if (strSortCol == null) {
                                                strSortCol = "CreatedDate";
                                            }

                                            strSortOrd = request.getParameter("txtSortAsc");
                                            if (strSortOrd == null) {
                                                strSortOrd = "ASC";
                                            }
                                            blnSortAsc = (strSortOrd.equals("ASC"));
                                            queryString = session.getAttribute(ApplicationConstants.QS_TASKS_LIST).toString();
                                            /* Sql query for retrieving resultset from DataBase */
                                            // out.println(queryString);
                                            String userRoleName = session.getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
                                            String issueAction = "../../employee/tasks/issue.action?accessType=Issue";

                                    %>
                                    <s:form action="" name="frmDBGrid" theme="simple"> 
                                        <div style="width:840px;"> 
                                            <table cellpadding="0" border="0" cellspacing="0" width="100%">
                                                <tr>
                                                    <td class="headerText">
                                                        <s:property value="#request.resultMessage || #session.resultMessage"/>
                                                        <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <s:if test="#session.isUserManager == 1 || #session.isUserTeamLead==1">     
                                                            <grd:dbgrid id="tblCrmAttachments" name="tblCrmAttachments" width="100" pageSize="15" 
                                                            currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                            dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                               imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                               />
                                                                <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>"  imageAscending="../../includes/images/DBGrid/ImgAsc.gif" imageDescending="../../includes/images/DBGrid/ImgDesc.gif" />

                                                                <grd:rownumcolumn headerText="SNo" width="2" HAlign="center"/>                 


                                                                <grd:anchorcolumn dataField="Title" 
                                                                                  headerText="Title" 
                                                                                  linkUrl="getTask.action?taskId={Id}&type={IssueRel}&projectId={Project_Id}&resM=" linkText="{Title}" width="30"/>
                                                                <grd:textcolumn dataField="CreatedBy"  headerText="CreatedBy" width="8" sortable="true"/> 

                                                                <grd:datecolumn dataField="CreatedDate"  headerText="CreatedDate"  HAlign="right" dataFormat="MM-dd-yyyy" width="20" sortable="true"/>
                                                                <grd:datecolumn dataField="DueDate"  headerText="DueDate"  HAlign="right" dataFormat="MM-dd-yyyy" width="18" sortable="true"/>
                                                                <grd:numbercolumn dataField="percentageCompleted" width="3" HAlign="right" headerText="Progress(%)" />    

                                                                <grd:textcolumn dataField="Status"  headerText="Status" width="8" sortable="true"/> 
                                                                <grd:textcolumn dataField="Severity"  headerText="Severity"   width="8" sortable="true"/> 
                                                                <%-- <grd:textcolumn dataField="Comments"  headerText="Comments" width="10" sortable="true"/>   --%>
                                                                <grd:anchorcolumn dataField="Description" linkUrl="javascript:getIssueDescription('{Id}')" headerText="Description"
                                                                                  linkText="Click To View"  width="8" />
                                                                <%--    <grd:textcolumn dataField="CreatedBy"  headerText="CreatedBy" width="12" sortable="true"/> --%>
                                                                <grd:textcolumn dataField="PriAssignTO"  headerText="PriAssignTo" width="8" sortable="true"/> 
                                                                <%--  <grd:textcolumn dataField="SecAssignTO"  headerText="Sec-AssignedTo" width="12" sortable="true"/> --%>
                                                                <%--  <grd:imagecolumn headerText="Alert" width="4" HAlign="center" imageSrc="../../includes/images/DBGrid/Edit.gif"
                                                                                 linkUrl="getIssue.action?issueId={Id}&accessType=Issue" imageBorder="0"
                                                                                 imageWidth="16" imageHeight="16" alterText="Click to edit"></grd:imagecolumn>  --%>
                                                                <%--  <grd:imagecolumn  headerText="reminder" width="4" HAlign="center" imageSrc="../../includes/images/DBGrid/reminder.jpg"
                                                                                    linkUrl="javascript:win_open2('/Hubble/employee/tasks/TaskReminderWindow.jsp','{Id}','{PriAssignTO}')"
                                                                                    imageBorder="0" 
                                                                                    imageWidth="16" imageHeight="16" alterText="Click to edit"></grd:imagecolumn>  --%>

                                                            </grd:dbgrid>
                                                        </s:if>
                                                        <s:else>
                                                            <grd:dbgrid id="tblCrmAttachments" name="tblCrmAttachments" width="100" pageSize="15" 
                                                            currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                            dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">

                                                                <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                               imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                               />
                                                                <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>"  imageAscending="../../includes/images/DBGrid/ImgAsc.gif" imageDescending="../../includes/images/DBGrid/ImgDesc.gif" />

                                                                <grd:rownumcolumn headerText="SNo" width="2" HAlign="center"/>                 


                                                                <grd:anchorcolumn dataField="Title" 
                                                                                  headerText="Title" 
                                                                                  linkUrl="getTask.action?taskId={Id}&type={IssueRel}&projectId={Project_Id}&resM=" linkText="{Title}" width="30"/>
                                                                <grd:textcolumn dataField="CreatedBy"  headerText="CreatedBy" width="8" sortable="true"/> 

                                                                <grd:datecolumn dataField="CreatedDate"  headerText="CreatedDate"  HAlign="right" dataFormat="MM-dd-yyyy" width="20" sortable="true"/>
                                                                <grd:datecolumn dataField="DueDate"  headerText="DueDate"  HAlign="right" dataFormat="MM-dd-yyyy" width="18" sortable="true"/>


                                                                <grd:numbercolumn dataField="percentageCompleted" width="3" HAlign="right" headerText="Progress(%)"/>    

                                                                <grd:textcolumn dataField="Status"  headerText="Status" width="8" sortable="true"/> 
                                                                <grd:textcolumn dataField="Severity"  headerText="Severity"   width="8" sortable="true"/> 
                                                                <%-- <grd:textcolumn dataField="Comments"  headerText="Comments" width="10" sortable="true"/>   --%>
                                                                <grd:anchorcolumn dataField="Description" linkUrl="javascript:getIssueDescription('{Id}')" headerText="Description"
                                                                                  linkText="Click To View"  width="8" />
                                                                <%--   <grd:textcolumn dataField="CreatedBy"  headerText="CreatedBy" width="12" sortable="true"/> --%>
                                                                <grd:textcolumn dataField="PriAssignTO"  headerText="PriAssignTo" width="8" sortable="true"/> 
                                                                <%--  <grd:textcolumn dataField="SecAssignTO"  headerText="Sec-AssignedTo" width="12" sortable="true"/>--%>

                                                            </grd:dbgrid>


                                                        </s:else>

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
                                            // connection.close();
                                            // connection = null;
                                    /* For Exception handling mechanism */
                                        } catch (Exception se) {
                                            out.println(se.toString());
                                            System.out.println("Exception in IssuesList " + se);
                                        } finally {
                                            if (connection != null) {
                                                connection.close();
                                                connection = null;
                                            }
                                            if (session != null) {
                                                session.removeAttribute("resultMessage");
                                            }
                                        }
                                    %>
                                    <%--  </sx:div > --%>
                                </div>
                                <%--   <%}%> --%>
                                <!--//END TAB : -->

                                <%--  <sx:div id="IssuesSearchTab" label="Issues Search" > --%>
                                <div id="IssuesSearchTab" class="tabcontent"  >   
                                    <s:form name="frmSearch" action="%{currentSearch}" theme="simple" method="post" onsubmit="return (validateDates() && compareDates(document.getElementById('startDate').value,document.getElementById('endDate').value));">
                                        <table border="0" cellpadding="1" cellspacing="1" width="100%">


                                            <tr align="right">
                                                <td class="headerText">
                                                    <img alt="Home" 
                                                         src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" 
                                                         width="100%" 
                                                         height="13px" 
                                                         border="0">
                                                </td>
                                            </tr></table>
                                        <!--The components of searching issues -->
                                        <table border="0" cellpadding="1" cellspacing="1" width="100%">




                                            <%-- Issue name based search  --%>
                                            <tr>
                                                <td class="fieldLabel" align="left">Title&nbsp;:</td>
                                                <s:hidden id="taskId" name="taskId" value=""/>
                                                <td width="20%"><s:textfield name="issueName" placeholder="Enter Title" id="issueName"  cssClass="inputTextBlue"  value="%{issueName}"  theme="simple"/></td>
                                                <td  class="fieldLabel" align="left">Status&nbsp;:</td>
                                                <%--  <td><s:select list="{'Created','InProgress','Closed','Not Closed','Assigned','Not Started' }"--%>
                                                <td><s:select list="{'Created','Assigned','InProgress','OnHold','Closed'}" 

                                                          name="statusId"
                                                          headerKey="" 
                                                          headerValue="--Please Select--"
                                                          value="%{statusId}"
                                                          cssClass="inputSelect"  /></td>
                                            </tr> 

                                            <tr>
                                                <td  class="fieldLabel" align="left">IssueRelatedTo&nbsp;:</td>
                                                <td ><s:select id="issueRelType" name="issueRelType" headerKey="-1" headerValue="--Please Select--" list="#@java.util.LinkedHashMap@{'0':'HR','1':'Hubble','2':'Projects','3':'Systems','4':'Facilities'}" value="%{issueRelType}" cssClass="inputSelect" onchange="showCustomerProjectNameDiv(this);getIssueTypeBasedOnIssueRelType(this);getAssignedToBasedOnIssueType(this);"/></td>

                                                <td  class="fieldLabel" align="left">IssueType&nbsp;:</td>
                                                <s:hidden id="issueTypehidden"  value="%{issueType}"/>
                                                <td>  
                                                    <s:select id="issueType" name="issueType"  list="{}" headerKey="" headerValue="--Please Select--" value="%{issueType}" cssClass="inputSelect" />
                                                </td>

                                            </tr> 
                                            <%-- end of issue name based search ---%>
                                            <tr><td class="fieldLabel">Created Between</td></tr>
                                            <tr>
                                            
                                                <td  class="fieldLabel" align="left">From Date&nbsp;:</td>

                                                <td width="40%"><s:textfield name="startDate" value="%{startDate}" id="startDate" cssClass="inputTextBlue" onchange="isValidDate(this)"/><a href="javascript:cal1.popup();" style="text-decoration: none;">
                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                             width="20" height="20" border="0" style="margin-bottom: -5px"></a></td>
                                                <td class="fieldLabel" align="left">To Date&nbsp;:</td>
                                                <td><s:textfield name="endDate" id="endDate" value="%{endDate}" cssClass="inputTextBlue" onchange="isValidDate(this)"/><a href="javascript:cal2.popup();" style="text-decoration: none;">
                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                             width="20" height="20" border="0" style="margin-bottom: -5px"></a></td>

                                            </tr>

                                            <tr id="customerProjectNameTr" style="display:none;">

                                                <%
                                                    if (session.getAttribute(ApplicationConstants.SESSION_EMPTYPE).equals("e")) {

                                                %>                                


                                                <td class="fieldLabel" align="left">Customer&nbsp;Name&nbsp;: </td>
                                                <td width="200px">  
                                                    <s:textfield name="customerName" id="customerName"   cssClass="inputTextBlue"  value="%{customerName}"  theme="simple" onkeyup="fillCustomerInIssue();"/>
                                                    <div id="validationMessage"></div>
                                                    <s:hidden name="customerId" value="%{customerId}" id="customerId"/> 
                                                </td>

                                                <td width="1px" class="fieldLabel" align="left">Project&nbsp;Name&nbsp;: </td>
                                                <td>

                                                    <s:textfield name="projectName" id="projectName"   cssClass="inputTextBlue"  value="%{projectName}"  theme="simple" onkeyup="fillProject();"/>
                                                    <s:hidden name="projectId" value="%{projectId}" id="projectId"/>
                                                    <div id="ProjectvalidationMessage"></div>
                                                </td> 






                                                <%} else if (session.getAttribute(ApplicationConstants.SESSION_EMPTYPE).equals("c") || session.getAttribute(ApplicationConstants.SESSION_EMPTYPE).equals("v")) {
                                                %>
                                                <td class="fieldLabel" align="left">Customers&nbsp;: </td>
                                                <td width="25%">
                                                    <s:select list="myAccounts" name="customerId" id="customerId" onchange="getProjectsForAccountId();" headerKey="-1" headerValue="--Select Project--" cssClass="inputLargeSelect" value="" disabled="false"/> 
                                                </td>
                                                <td class="fieldLabel" align="left">Projects&nbsp;: </td>
                                                <td width="25%">
                                                    <s:select list="{}" name="projectId" id="projectId" onchange="hidePriority();" headerKey="-1" headerValue="--Select Project--" cssClass="inputLargeSelect" value="" disabled="false"/> 
                                                </td>
                                                <%              }
                                                %>                              

                                            </tr> 


                                            <%--    <tr>
    <td class="fieldLabel">Customer Name : </td>
    <td>  
        <s:textfield name="customerName" id="customerName"  cssClass="inputTextBlue"  value="%{currentIssue.customerName}"  theme="simple" onkeyup="fillCustomerInIssue();"/>
      
    </td>
    
       <td class="fieldLabel">Project Name : </td>
        <td>  
            <s:select list="%{projectNamesMap}" 
                      name="projectName"
                      id="projectName"
                      headerKey="" 
                      headerValue="--PleaseSelect--"
                      value="%{currentIssue.projectName}"
                      cssClass="inputSelect"  />
        </td>
     
</tr> --%>
                                            <s:if test="%{currentSearch == 'doSearchTeamTasks' }">
                                                <tr>


                                                    <td class="fieldLabel" align="left">Created&nbsp;By&nbsp;:</td>
                                                    <s:if test="#session.isUserManager == 1 || #session.isUserTeamLead==1 ">

                                                        <td ><s:textfield name="assignedToUID" id="assignedToUID" onkeyup="fillEmployee('pre');" cssClass="inputTextBlue" value="%{assignedToUID}" theme="simple" readonly="false"/>

                                                            <div id="assignEmpValidationMessage"></div>  
                                                            <s:hidden name="preAssignEmpId" value="%{preAssignEmpId}" id="preAssignEmpId"/> 
                                                        </td>                


                                                        <!-- sec assign to start-->

                                                        <td class="fieldLabel" align="left">Assign&nbsp;To&nbsp;:</td>
                                                        <td ><s:textfield name="postAssignedToUID" id="postAssignedToUID" onkeyup="fillEmployee('post');" cssClass="inputTextBlue" value="%{postAssignedToUID}" theme="simple" readonly="false"/>
                                                            <div id="postAssignEmpValidationMessage"></div>  
                                                            <s:hidden name="postAssignEmpId" value="%{postAssignEmpId}" id="postAssignEmpId"/>
                                                        </td>                


                                                    </s:if>  
                                                    <!-- sec assignto End -->
                                                </s:if>
                                            </tr>
                                            <tr>
                                                <td class="fieldLabel" align="left">Progress&nbsp;:</td>
                                                <td width="20%"><s:textfield name="progress" id="progress"  cssClass="inputTextBlue"  value="%{progress}" onchange="isNumeric(this)"  theme="simple"/></td>
                                                <td></td>

                                                <td>

                                                    <s:hidden name="submitFrom" value="Search"/>
                                                    <s:submit cssClass="buttonBg" value="Search" style="margin-left: 79px;"/>
                                                </td>
                                            </tr>
                                            <%--   <tr align="right">
                                                   <td></td>
                                                   <td></td>
                                                   <td></td>
                                                   
                                                   <td align="center">

                                                    <s:hidden name="submitFrom" value="Search"/>
                                                    <s:submit cssClass="buttonBg" value="Search"/>

                                                </td>
                                                        </tr>  --%>


                                        </table>

                                    </s:form>
                                    <!-- this script for After loading Form it will instantiate Calender Objects as you require -->
                                    <script type="text/JavaScript">
                                        var cal1 = new CalendarTime(document.forms['frmSearch'].elements['startDate']);
                                        cal1.year_scroll = true;
                                        cal1.time_comp = false;
                                            
                                        var cal2 = new CalendarTime(document.forms['frmSearch'].elements['endDate']);
                                        cal2.year_scroll = true;
                                        cal2.time_comp = false;
                                    </script>

                                    <%--  </sx:div> --%>
                                </div>

                                <!--//END TAB : -->
                                <%--  </sx:tabbedpanel> --%>
                            </div>
                            <!--//END TABBED PANNEL : -->
                            <script type="text/javascript">

                                var countries=new ddtabcontent("accountTabs")
                                countries.setpersist(false)
                                countries.setselectedClassTarget("link") //"link" or "linkparent"
                                countries.init()

                            </script>
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
        <tr>
            <td>

                <div style="display: none; position: absolute; top:170px;left:320px;overflow:auto;" id="menu-popup">
                    <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" />
                </div>

            </td>
        </tr>

        <!--//END FOOTER : Record for Footer Background and Content-->

    </table>
    <!--//END MAIN TABLE : Table for template Structure-->


<script type="text/javascript">
		$(window).load(function(){
		init1('<%=check%>');
		showCustomerProjectNameDiv(issueRelType);
		getIssueTypeBasedOnIssueRelType(issueRelType);

		});
		</script>


</body>
</html>


