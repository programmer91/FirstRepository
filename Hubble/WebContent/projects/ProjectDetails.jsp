<%--
/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  January 01, 2008, 12:35 PM
 *
 * Author  : Arjun Sanapathi<asanapathi@miraclesoft.com>
 *           Rajanikanth Teppala<rteppala@miraclesoft.com>
 *
 * File    : ProjectDetails.jsp
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
--%>
<%@page import="com.mss.mirage.projects.ProjectAction"%>
<%@page import="com.mss.mirage.employee.projects.ProjectVTO"%>
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ page import="com.freeware.gridtag.*" %> 
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd" %>

<html>
    <head>

        <title>Hubble Organization Portal :: Project Details</title>
    <sx:head cache="false"/>
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>

    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGProjectTeam.js"/>"></script>
   <%-- <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBPMOTeam.js"/>"></script>
    <<script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGProjDetails.js"/>"></script> --%>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/projectsValidations/ProjectDetailsClientValidation.js"/>"></script> 
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
   
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/fillPMOList.js"/>"></script>
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tooltip.css"/>">
         <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tooltip.js"/>"></script>
          <script type="text/JavaScript" src="<s:url value="/includes/javascripts/projectsValidations/ProjectClientValidation.js"/>"></script> 
         <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmpStandardClientValidations.js"/>"></script>
      <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGPrjRisks.js"/>"></script>
      <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
      <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGPrjTasks.js"/>"></script>
<script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/jquery.min.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/jquery.js"/>"></script> 
         <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/jquery.min.js"/>"></script>
    <script>
     function setActionForPMO(){
         var projectId=document.getElementById("projectId").value;
         var accountId=document.getElementById("accountId").value;
         var preAssignEmpId =  document.getElementById("preAssignEmpId").value;
         if(preAssignEmpId==null||preAssignEmpId=="")
             {
                 alert("Please select PMO Name from Suggestion List");
                 return false;
                 
             }
         var status = document.getElementById("status").value;
         window.location="doAddPMO.action?projectId="+projectId+"&accountId="+accountId+"&preAssignEmpId="+preAssignEmpId+"&status="+status;
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
</head>

<!-- <body class="bodyGeneral"  onload="init1('0');"> -->
<body class="bodyGeneral" oncontextmenu="return false;">

    <%!
    /* Declarations */
    Connection connection;
        String queryString;
        String currentAccountId;
        String currentProjectId;
        String strTmp;
        String strSortCol;
        String strSortOrd;
        int intSortOrd = 0;
        int intCurr;
        boolean blnSortAsc = true;
        String userRoleName;
        String strTmp1;
        String strSortCol1;
        String strSortOrd1;
        int intSortOrd1 = 0;
        int intCurr1;
        boolean blnSortAsc1 = true;
        
        String strTmp2;
        String strSortCol2;
        String strSortOrd2;
        int intSortOrd2 = 0;
        int intCurr2=1;
        boolean blnSortAsc2 = true;
       
      
        String strTmp3;
        String strSortCol3;
        String strSortOrd3;
        int intSortOrd3 = 0;
        int intCurr3=1;
        boolean blnSortAsc3 = true;
        
         String strTmp4;
        String strSortCol4;
        String strSortOrd4;
        int intSortOrd4 = 0;
        int intCurr4=1;
        boolean blnSortAsc4 = true;

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
                        <!--//END DATA COLUMN : Coloumn for LeftMenu-->

                        <!--//START DATA COLUMN : Coloumn for Screen Content-->
                        <td width="850px" class="cellBorder" valign="top">
                            <!--//START TABBED PANNEL : -->

                            <table cellpadding="0" cellspacing="0" width="100%">
                                <tr>
                                    <%
                                    userRoleName = session.getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
                                    %> 
                                    <%
                                    if (("Customer Manager".equalsIgnoreCase(userRoleName)) ){
                                    %>
                                    <td><span class="fieldLabel">Account Name :</span>&nbsp;
                                        <label class="navigationText"><s:property value="#request.accountName"/></label></td>                                        
                                        <%  }else  {%>
                                    <td><span class="fieldLabel">Account Name :</span>&nbsp;
                                        <a href="<s:url action="../crm/accounts/getAccount"><s:param name="id" value="%{accountId}"/></s:url>" class="navigationText"><s:property value="#request.accountName"/></a></td>
                                        <%}%>


                                </tr>
                                <input type="hidden" name="editProject_id" id="editProject_id" value="<%=request.getAttribute("currentProjectId")%>">
                                <input type="hidden" name="editProject_customerId" id="editProject_customerId" value="<%=request.getAttribute("currentAccountId")%>">
                                <tr>
                                    <td valign="top" style="padding-left:10px;padding-top:5px;">
                                        <!-- //START TABBED PANNEL : -->
                                        <ul id="projectTabs" class="shadetabs" >
                                            <li ><a href="#" class="selected" rel="ProjectDetailsTab"> Project Details </a></li>   
                                            <%--   <li ><a href="#" rel="AssignTeamTab">Assign Team</a></li> --%>
                                        </ul>

                                        <%-- <sx:tabbedpanel id="projectPannel" cssStyle="width: 840px; padding-left:10px; padding-top:5px;" doLayout="false"> --%>
                                        <div  style="border:1px solid gray; width:898px; overflow:auto; margin-bottom: 1em;">  
                                            <div id="ProjectDetailsTab" class="tabcontent" >
                                                <div id="overlayForProjectAdd"></div>              <!-- Start Overlay -->
                                                <div id="specialBoxForProjectAdd" style="width: 806px;">
                                                    <s:form theme="simple"  align="center" name="addProject" action="%{currentAction}" method="post" enctype="multipart/form-data" onsubmit="return validateForm();"   >
                                                        <table align="center" border="0" cellspacing="0" style="width:100%;">
                                                            <tr>                               
                                                                <td colspan="2" style="background-color: #288AD1" >
                                                                    <h3 style="color:darkblue;" align="left">
                                                                        <span id="headerLabel"></span>


                                                                    </h3></td>
                                                                <td colspan="2" style="background-color: #288AD1" align="right">

                                                                    <a href="#" onmousedown="mytoggleOverlayForTaskAdd()" >
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
                                                                    <table style="width:90%;" align="center" border="0" >
                                                                        <tr>


                                                                            <td class="fieldLabel" style="width: 145px;">Title&nbsp;: </td>
                                                                            <td  colspan="3"> 
                                                                                <s:textfield id="taskTitle" name="taskTitle" cssClass="inputTextarea3" style="height: 22px;width:459px;" value="%{taskTitle}" onchange="fieldLengthValidatorforProject(this);"/>
                                                                            </td>

                                                                            


                                                                        </tr>
                                                                        <tr>
                                                                            <td class="fieldLabel" >Start&nbsp;Date&nbsp;:<FONT color="red"  ><em>*</em></FONT></td>
                                                                            <td>

                                                                                <s:textfield name="taskStartDate" id="startDate" value="%{taskStartDate}" cssClass="inputTextBlue"  />
                                                                                <a href="javascript:cal5.popup();"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif"
                                                                                                                        width="20" height="20" border="0"></a>
                                                                            </td>
                                                                            <td class="fieldLabel">Due&nbsp;Date&nbsp;:</td>
                                                                        <td >

                                                                            <s:textfield name="dateClosed" id="dateClosed" value="%{dateClosed}"  cssClass="inputTextBlue"/>
                                                                            <a href="javascript:cal6.popup();">
                                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                 width="20" height="20" border="0"></a>

                                                                        </td></tr>
                                                                        <tr>
                                                                            <td class="fieldLabel">Assigned&nbsp;to&nbsp;:&nbsp;<FONT color="red"  ><em>*</em></FONT></td>
                                                                            <td ><s:select list="taskAssignedTo" headerKey="-1" headerValue="--Please Select--"
                                                                                      name="assignedTo"
                                                                                      id="assignedTo"
                                                                                      value="%{assignedTo}"
                                                                                      cssClass="inputSelect"  />  </td>
                                                                            <td class="fieldLabel">Hours:</td>
                                                                            <td >
                                                                                <s:textfield name="durationTotheTask" cssClass="inputTextBlue2" style="width:48px;" id="durationTotheTask"  value="%{durationTotheTask}" onkeypress="return isNumberKey(event)"/><s:label cssClass="fieldLabel" value="(Hrs)"/>
                                                                            </td>
                                                                        </tr>
                                                                        <tr id="severityTR">
                                                                            <td class="fieldLabel" >Severity :<FONT color="red"  ><em>*</em></FONT></td> 
                                                                            <td ><s:select id="priority" name="priority"  list="{'High','Medium','Low'}" value="%{currentTask.priority}" cssClass="inputSelect" /></td>
                                                                            <td class="fieldLabel"> Status :</td>
                                                                            <td><s:select list="{'Created','InProgress','Closed','Not Closed','Assigned','Not Started'}"
                                                                                      name="statusId"
                                                                                      id="statusId"
                                                                                      value="%{currentTask.statusId}"
                                                                                      cssClass="inputSelect"  /></td>
                                                                        </tr>
                                                                        <tr id="typeId"> <td class="fieldLabel">Type :<FONT color="red"><em>*</em></FONT></td> 
                                                                <td >
                                                                  
                                                                    <s:select id="issueType" name="issueType"  list="projectIssueType" value="%{issueType}" cssClass="inputSelect"/>
                                                                
                                                                </td>
                                                                <tr id="commentsTR">
                                                                    <td class="fieldLabel">Comments :</td>
                                                                    <td colspan="4"><s:textarea rows="3" cols="65"  style="width: 464px;" name="comments" cssClass="inputTextarea3" id="comments" value="%{comments}" onchange="fieldLengthValidatorforProject(this);"/></td>
                                                                </tr> 
                                                                 <tr id="resolutionTR">
                                        <td class="fieldLabel">Resolution :
                                        </td>
                                        <td colspan="4">
                                            <%-- <s:textarea name="resolution" id = "resolution" rows="5" cols="82" value="%{currentTask.resolution}" onchange="fieldLengthValidator(this);" cssClass="inputTextarea"/>
               --%>
                                            <s:textarea name="resolution" id = "resolution" cssClass="inputTextarea3"  style="width: 464px;" value="%{resolution}" onchange="fieldLengthValidatorforProject(this);" />
                                        </td> 
                                                                       

                                                                        <tr id="addingTr">   
                                                                            <td  align="right" colspan="4" style="padding-right:140px;">
                                                                                <s:reset name="reset" value="Reset" cssClass="buttonBg"/>
                                                                                <input type="button" value="Add" onclick="return addProjectTaskDetails();" class="buttonBg" id="addButton"/> 

                                                                            </td>
                                                                            
                                                                        </tr>
                                                                        <tr id="updatingTr">
                                                                    <td align="right"  colspan="4" style="padding-right: 92px;"> <table><tr><td><div id="update" ><input type="button"  value="Update" onclick="return upadteProjectTaskDetails();" class="buttonBg"/> </div></td>
                                                                            </tr></table></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        <s:hidden name="taskId" id="taskId"/>
                                                                    </td>
                                                                </tr>
                                                                    </table>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </s:form>              
                                                </div>
                                                <!--//START TAB : -->
                                                <%-- <sx:div id="ProjectDetailsTab" label="Project Details"> --%>
                                                <s:form name="frmProjectEdit" action="editProject" theme="simple" onsubmit="return projectValidation();">

                                                    <table cellpadding="1" cellspacing="1" border="0" width="100%">
                                                        <tr align="right">
                                                            <td class="headerText" colspan="6">
                                                                <s:property value="#request.resultMessage"/>
                                                                <s:hidden name="id" value="%{currentProject.id}"/>
                                                            </td>
                                                        </tr>       

                                                        <tr style="color:#00008B">
                                                            <th colspan="4"><center>Project Details</center></th><th></th><th style="text-align: left;">Plan</th><th style="text-align: left;">Actual</th>
                                                        </tr>
                                                        <tr>
                                                            <td class="fieldLabel" >Project Name:<FONT color="red"  ><em>*</em></FONT></td>                                                    
                                                               <td style="width: 219px;"><s:textfield name="prjName" cssClass="inputTextBlueLarge"  onchange="fieldLengthValidatorforProject(this);isExistedProjectName();"  id="projectName" size="25" value="%{currentProject.prjName}" />&nbsp;<img id="correctImg" style="display: none;" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/ecertification/checkSelect.jpg" 
                                                                                                                                                                                                        width="15" height="15" border="0"><img id="wrongImg" style="display: none;" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/ecertification/wrong.jpg"> 
                                                            </td> 
                                                            <td class="fieldLabel" style="width: 10px;">Practice :<FONT color="red"  ><em>*</em></FONT></td>
                                                            <td style="width: 156px;">
                                                                <s:select list="practiceList" name="practice" id="practice" cssClass="inputSelect"  headerKey="" headerValue="--Please Select--" value="%{currentProject.practice}"/>
                                                            </td>

                                                            <td class="fieldLabel" style="width: 10px;">OnSite :</td>
                                                            <td>
                                                                <s:textfield name="onSitePlan" cssClass="inputTextBlueSmall"   id="onSitePlan" value="%{currentProject.onSitePlan}" onkeypress="return isNumberKey(event)"/>
                                                            </td>
                                                            <td>
                                                                <s:textfield name="onSiteActual" cssClass="inputTextBlueSmall"    id="onSiteActual" value="%{currentProject.onSiteActual}" onkeypress="return isNumberKey(event)"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td class="fieldLabel">Customer :</td>
                                                            <td>
                                                                <s:textfield name="customer" cssClass="inputTextBlueLarge"    id="customer" size="25" value="%{accountName}" readonly="true" />
                                                            </td>
                                                            <td class="fieldLabel">Project&nbsp;Type&nbsp;:</td>
                                                            <td>
                                                                <s:select list="{'Support','Development'}" name="projectType" cssClass="inputSelect" value="%{currentProject.projectType}" headerKey="" headerValue="--Please Select--"/>
                                                            </td>
                                                            <td class="fieldLabel">OffShore :</td>
                                                            <td>
                                                                <s:textfield name="offShorePlan" cssClass="inputTextBlueSmall"    id="offShorePlan"  value="%{currentProject.offShorePlan}" onkeypress="return isNumberKey(event)"/>
                                                            </td>
                                                            <td>
                                                                <s:textfield name="offShoreActual" cssClass="inputTextBlueSmall"    id="offShoreActual" value="%{currentProject.offShoreActual}" onkeypress="return isNumberKey(event)"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td class="fieldLabel">Pre-Sales :&nbsp;</td>
                                                            <td > 
                                                                <s:select name="preSalesMgrId" id="preSalesMgrId" cssClass="inputSelect" list="preSalesMap" emptyOption="false" headerKey="-1" headerValue="--Please Select--" value="%{currentProject.preSalesMgrId}"/><%-- <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/info.jpg" onmouseover="fixedtooltip('Dispalys employees list who are having the Role \'Pre-Sales\'.',this,event, 200,-20,40)" onmouseout="delayhidetip()" height="18" width="20"> --%>
                                                            </td>
                                                            <td class="fieldLabel">Offshore&nbsp;Del.&nbsp;Lead:&nbsp;</td>
                                                            <td> 
                                                                <s:select name="offshoreDelLead" id="offshoreDelLead" cssClass="inputSelect" list="offshoreDelLeadMap" emptyOption="false" headerKey="" headerValue="--Please Select--" value="%{currentProject.offshoreDelLead}" />
                                                            </td>
                                                            
                                                            <td class="fieldLabel">Near&nbsp;Shore&nbsp;:</td>
                                                            <td>
                                                                <s:textfield name="nearShorePlan" cssClass="inputTextBlueSmall"   id="nearShorePlan" value="%{currentProject.nearShorePlan}" onkeypress="return isNumberKey(event)"/>
                                                            </td>
                                                            <td>
                                                                <s:textfield name="nearShoreActual" cssClass="inputTextBlueSmall"  id="nearShoreActual" value="%{currentProject.nearShoreActual}" onkeypress="return isNumberKey(event)"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                             <td class="fieldLabel">Offshore&nbsp;Tech.&nbsp;Lead:&nbsp;</td>
                                                            <td > 
                                                                <s:select name="offshoreTechLead" id="offshoreTechLead" cssClass="inputSelect" list="offshoreLeadMap" emptyOption="false" headerKey="" headerValue="--Please Select--" value="%{currentProject.offshoreTechLead}" />
                                                            </td>
                                                            <td class="fieldLabel">Onsite&nbsp;Lead:&nbsp;</td>
                                                            <td > 
                                                                <s:select name="onsiteLead" id="onsiteLead" cssClass="inputSelect" list="onsiteLeadMap" emptyOption="false" headerKey="" headerValue="--Please Select--" value="%{currentProject.onsiteLead}" />
                                                            </td>
                                                            
                                                           
                                                            <td class="fieldLabel" align="right">Start&nbsp;Date&nbsp;:</td>                                                    
                                                            <td align="left"><s:textfield name="startDatePlan" id="startDatePlan" cssClass="inputTextBlueSmall" onchange="checkDates(this);" value="%{currentProject.startDatePlan}"/><a style="text-decoration: none;" href="javascript:cal1.popup();">
                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                            </td> 
                                                            <td align="left"><s:textfield name="startDateActual" id="startDateActual" cssClass="inputTextBlueSmall" onchange="checkDates(this);" value="%{currentProject.startDateActual}"/><a style="text-decoration: none;" href="javascript:cal2.popup();">
                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td class="fieldLabel">Cost&nbsp;Model :&nbsp;</td>
                                                            <td > 
                                                                <s:select name="costModel" id="costModel" cssClass="inputSelect" list="{'Fixed','Time & Material','Internal'}" emptyOption="false" headerKey="-1" headerValue="--Please Select--" value="%{currentProject.costModel}" />
                                                            </td>
                                                            <td class="fieldLabel">PMO :&nbsp;<FONT color="red"  ><em>*</em></FONT></td>
                                                            <td> 
                                                                <s:select name="pmo" id="pmo" cssClass="inputSelect" list="pmomanagerMap" emptyOption="false" headerKey="-1" headerValue="--Please Select--" value="%{currentProject.pmo}" /><%-- <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/info.jpg" onmouseover="fixedtooltip('Dispalys employees list who are having the practice \'PMO\'.',this,event, 200,-20,40)" onmouseout="delayhidetip()" height="18" width="20"> --%>
                                                            </td>
                                                             
                                                            <td class="fieldLabel">End&nbsp;Date&nbsp;:</td>                                                    
                                                            <td><s:textfield name="endDatePlan" id="endDatePlan" cssClass="inputTextBlueSmall" onchange="checkDates(this);" value="%{currentProject.endDatePlan}"/><a style="text-decoration: none;" href="javascript:cal3.popup();">
                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                            </td> 
                                                            <td><s:textfield name="endDateActual" id="endDateActual" cssClass="inputTextBlueSmall" onchange="checkDates(this);" value="%{currentProject.endDateActual}"/><a style="text-decoration: none;" href="javascript:cal4.popup();">
                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                            </td> 
                                                        </tr>
                                                        <tr>
                                                            <td class="fieldLabel">Sector&nbsp;:</td>
                                                            <td > 
                                                                <s:select name="sector" id="sector" cssClass="inputSelect" list="{'Manufacturing','HealthCare','Logistics','Retail','Life Sciences','CPG','Banks','Insurance'}" emptyOption="false" headerKey="-1" headerValue="--Please Select--" value="%{currentProject.sector}" />
                                                            </td>
                                                            <td class="fieldLabel">Complexity&nbsp;:</td>
                                                            <td> 
                                                                <s:select name="complexity" id="complexity" cssClass="inputSelect" list="{'Low','Medium','High','Critical'}"  emptyOption="false" headerKey="-1" headerValue="--Please Select--" value="%{currentProject.complexity}" />
                                                            </td>
                                                            <td class="fieldLabel" colspan="3">Overall&nbsp;State:<s:select name="state" id="state" list="{'Green','Amber','Red'}" value="%{currentProject.state}" cssClass="inputSelectSmall1" />Schedule&nbsp;:<s:select name="schedule" id="schedule" list="{'Green','Amber','Red'}" cssClass="inputSelectSmall1" value="%{currentProject.schedule}" /></td> 
                                                                
                                                             
                                                        </tr>
                                                        <tr>
                                                            <td class="fieldLabel">Priority&nbsp;:</td>
                                                            <td > 
                                                                <s:select name="priority" id="priority" cssClass="inputSelect" list="{'Low','Medium','High','Critical'}" emptyOption="false" headerKey="-1" headerValue="--Please Select--" value="%{currentProject.priority}" />
                                                            </td>
                                                            <td class="fieldLabel">Status:</td>         
                                                                <s:if test="#session.roleName == 'Admin'">   
                                                                <td class="inputOptionText"><s:select name="status" id="projectStatus" list="{'Active','Completed','Terminated','Initiated'}" cssClass="inputSelectSmall1" value="%{currentProject.status}"/></td> 
                                                                </s:if>
                                                                <s:else>                                                                            
                                                                <td class="inputOptionText"><s:select name="status" id="projectStatus" list="{'Active','Completed','Terminated','Initiated'}" contentEditable="true" cssClass="inputSelectSmall1" value="%{currentProject.status}"/></td> 
                                                                     </s:else> 
                                                                 <td class="fieldLabel" colspan="3">Risk&nbsp;:<s:select name="risk" id="risk" list="{'Green','Amber','Red'}" cssClass="inputSelectSmall1" value="%{currentProject.risk}"/>Resources&nbsp;:<s:select name="resources" id="resources" list="{'Green','Amber','Red'}" cssClass="inputSelectSmall1" value="%{currentProject.resources}"/></td> 
                                                            
                                                        </tr>
                                                        <tr>
                                                            <td class="fieldLabel">Software :</td>
                                                            <td colspan="6"><s:textarea cols="145" rows="2" name="software" onchange="fieldLengthValidatorforProject(this);" cssClass="inputTextarea"   id="software" value="%{currentProject.software}"/></td>                                                    
                                                            

                                                        </tr>
                                                         <tr>
                                                            <td class="fieldLabel">Descrption :</td>
                                                            <td colspan="6"><s:textarea cols="145" rows="2" name="description" value="%{currentProject.description}" cssClass="inputTextarea" onchange="fieldLengthValidatorforProject(this);"  id="description"/></td>                                                    
                                                        </tr>
                                                        <tr>
                                                            <td class="fieldLabel">Comments :</td>
                                                            <td colspan="6"><s:textarea cols="145" rows="2" name="comments" onchange="fieldLengthValidatorforProject(this);" cssClass="inputTextarea"  id="comments" value="%{currentProject.comments}"/></td>                                                    
                                                            
                                                        </tr>
                                                        
                                                        <tr>


                                                        </tr>
                                                        <%--<tr>
                                                             
                                                            <td class="fieldLabel">No.Of ReSources :</td>                                                    
                                                            <td><s:textfield name="totalResources" cssClass="inputTextBlue" onchange="totalResourcesValidate(document.frmProjectAdd.totalResources.value);"  onblur="return validatenumber(this)"  id="totalResources" size="10" readonly="true"/></td>
                                                        </tr>--%>
                                                        <tr>


                                                        </tr>
                                                       
                                                        <tr>
                                                                                                                            <td align="right"><s:checkbox name="isDualReportingRequired" id="isDualReportingRequired" value="%{currentProject.isDualReportingRequired}"/></td>                                                    
                                                                                                                            <td class="fieldLabelLeft" colspan="3">Is this project requires dual reporting</td>      
                                                                                                                            
                                                                                                                        </tr>
                                                        <tr align="right">
                                                            <td  colspan="7">
                                                                <s:hidden name="customerId" id="accountId" value="%{currentProject.customerId}" />   
                                                                <s:submit cssClass="buttonBg" value="Save"/>
                                                                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                            </td>
                                                        </tr> 

                                                        
                                                                                                                         
                                                    </table>

                                                    <script type="text/JavaScript">
                                                       
                                                        var cal1 = new CalendarTime(document.forms['frmProjectEdit'].elements['startDatePlan']);
                                                        cal1.year_scroll = true;
                                                        cal1.time_comp = false;
                                                           
                                                        var cal2 = new CalendarTime(document.forms['frmProjectEdit'].elements['startDateActual']);
                                                        cal2.year_scroll = true;
                                                        cal2.time_comp = false;
                                                           
                                                        var cal3 = new CalendarTime(document.forms['frmProjectEdit'].elements['endDatePlan']);
                                                        cal3.year_scroll = true;
                                                        cal3.time_comp = false;
                                                           
                                                        var cal4 = new CalendarTime(document.forms['frmProjectEdit'].elements['endDateActual']);
                                                        cal4.year_scroll = true;
                                                        cal4.time_comp = false;
                                                        
                                                        var cal5 = new CalendarTime(document.forms['addProject'].elements['startDate']);
                                                        cal5.year_scroll = true;
                                                        cal5.time_comp = false;
                                                        
                                                         var cal6 = new CalendarTime(document.forms['addProject'].elements['dateClosed']);
                                                        cal6.year_scroll = true;
                                                        cal6.time_comp = false;
                                                    </script>

                                                </s:form>
                                                <%--</sx:div >--%>
                                            </div>

                                            <%--  <div id="AssignTeamTab" class="tabcontent" >
                                                     
                                                       <s:form name="frmProjectTeamAdd" action="projectTeamUpdate" theme="simple" >
                                                           <table cellpadding="1" cellspacing="1" border="0" width="100%">
                                                               <tr align="right">
                                                                   <td class="headerText" colspan="2">
                                                                       
                                                                       <s:property value="#request.resultMessage"/>
                                                                       <s:hidden name="accountId" value="%{currentProject.customerId}" />
                                                                       <s:hidden name="id" value="%{currentProject.id}"/>
                                                                       <s:submit cssClass="buttonBg" value="Save"/>
                                                                   </td>
                                                               </tr>      
                                                               
                                                               
                                                               <tr>
                                                                   
                                                                   <td align="center">
                                                                       <s:optiontransferselect
                                                                           label="Employee Roles"
                                                                           name="leftSideEmployee"
                                                                           leftTitle="Avilable Resources"
                                                                           rightTitle="Project Resources"
                                                                           list="allDevelopmentMap"
                                                                           headerKey="headerKey"
                                                                           
                                                                           doubleName="addedEmployeeList"
                                                                           doubleList="addTeamDevelopmentMap"
                                                                           doubleHeaderKey="doubleHeaderKey"
                                                                           doubleValue=""
                                                                           cssClass="inputTextarea"
                                                                       />
                                                                   </td>
                                                               </tr>
                                                               
                                                               
                                                           </table>
                                                       </s:form>
                                                       
                                                       
                                                       
                                                   </div> --%>
                                            <%-- </sx:div > --%>
                                            <!--//END TAB : -->

                                        </div>
                                        <%-- </sx:tabbedpanel> --%>
                                        <!--//END TABBED PANNEL : -->

                                        <script type="text/javascript">

                                            var countries=new ddtabcontent("projectTabs")
                                            countries.setpersist(false)
                                            countries.setselectedClassTarget("link") //"link" or "linkparent"
                                            countries.init()

                                        </script>
                                    </td>
                                </tr>

                                <tr>
                                    <td valign="top" style="padding-left:10px;padding-top:5px;">

                                        <ul id="ProjectDetailsTabs" class="shadetabs" >
                                            <%--  <li ><a href="#" class="selected" rel="subProjectListTab"  > SubProject List </a></li> --%>
                                            <li ><a href="#" rel="attachmentsList">Attachments</a></li>

                                            <li ><a href="#" rel="projectTeamList">Resources</a></li>

                                            <li ><a href="#" rel="risksList">Risks</a></li>
                                            <li ><a href="#" rel="tasks">Tasks</a></li>
                                        </ul>

                                        <!--//END TABBED PANNEL : -->

                                        <div  style="border:1px solid gray; width:898px;height: 300px; overflow:auto; margin-bottom: 1em;">
                                            <!--//START TABBED PANNEL : --> 
                                            <%-- <sx:tabbedpanel id="ProjectDetailsPannel" cssStyle="width: 840px; height: 300px;padding-left:10px;padding-top:5px;" doLayout="true" useSelectedTabCookie="true"> --%>

                                            <%

                                                if (request.getAttribute("currentAccountId") != null) {
                                                    currentAccountId = (String) request.getAttribute("currentAccountId");
                                                }

                                                if (request.getAttribute("currentProjectId") != null) {
                                                    currentProjectId = (String) request.getAttribute("currentProjectId");
                                                }
                                                try {%>

                                            <%-- <sx:div id="subProjectListTab" label="SubProject List">  --%>

                                            <div id="subProjectListTab" class="tabcontent">
                                                <%


                                                    /* Getting DataSource using Service Locator */

                                                    connection = ConnectionProvider.getInstance().getConnection();

                                                    intCurr = 1;
                                                    /* String Variable for storing current position of records in dbgrid*/
                                                  /*  strTmp = request.getParameter("txtSubProjCurr");

                                                    if (strTmp != null) {
                                                        intCurr = Integer.parseInt(strTmp);
                                                    }

                                                    
                                                    strSortCol = request.getParameter("txtSubProjSortCol");

                                                    if (strSortCol == null) {
                                                        strSortCol = "SubProjectName";
                                                    }

                                                    strSortOrd = request.getParameter("txtSubProjSortAsc");
                                                    if (strSortOrd == null) {
                                                        strSortOrd = "DESC";
                                                    }

                                                    
                                                    queryString = null;
                                                    queryString = " Select Id,SubProjectName,CurStatus,TeamSize,StartDate,EndDate,ProjectId  from tblSubProjects";
                                                    queryString = queryString + " where ProjectId=" + currentProjectId + " and Status=Active ORDER BY StartDate DESC";

                                                    String addSubProjectsAction = "subProject.action?accountId=" + currentAccountId + "&id=" + currentProjectId;
                                                    */

                                                %>
                                                <%--  <s:form action="" theme="simple" name="frmDBSubProjGrid">   
                                                      
                                                      <table cellpadding="0" cellspacing="0" width="100%">
                                                          <tr align="right">
                                                              <td class="headerText">
                                                                  <img alt="Home" 
                                                                       src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" 
                                                                       width="100%" 
                                                                       height="13px" 
                                                                       border="0">
                                                              </td>
                                                          </tr>    
                                                          <!---BEGIN:: DBGrid Specific ---->  
                                                          <tr>
                                                              <td width="100%">
                                                                  <grd:dbgrid id="tblSubProjectsList" name="tblSubProjectsList" width="100" pageSize="8" 
                                                                              currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                              dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                      
                                                                      <grd:gridpager imgFirst="../includes/images/DBGrid/First.gif" imgPrevious="../includes/images/DBGrid/Previous.gif" 
                                                                                     imgNext="../includes/images/DBGrid/Next.gif" imgLast="../includes/images/DBGrid/Last.gif"
                                                                                     addImage="../includes/images/DBGrid/Add.png"   
                                                                                     addAction="<%=addSubProjectsAction%>"
                                                                                     scriptFunction="getNextSubProjects"/>
                                                                      
                                                                      <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" 
                                                                                      imageAscending="../includes/images/DBGrid/ImgAsc.gif" 
                                                                                      imageDescending="../includes/images/DBGrid/ImgDesc.gif"/>    
                                                                      
                                                                      <grd:rownumcolumn headerText="SNo" width="4" HAlign="right"/>
                                                                      <grd:imagecolumn  headerText="Edit"  width="5"  HAlign="center"  
                                                                                        imageSrc="../includes/images/DBGrid/Edit.gif"  
                                                                                        linkUrl="getSubProject.action?subProjectId={Id}&projectId={ProjectId}" 
                                                                                        imageBorder="0" imageWidth="16" imageHeight="16" alterText="Click to edit" />
                                                                      
                                                                      <grd:textcolumn dataField="SubProjectName"  	headerText="SubProject Name" width="8"/>
                                                                      <grd:textcolumn dataField="CurStatus"  	headerText="Status" width="8"/>
                                                                      <grd:datecolumn dataField="StartDate"  	headerText="Start Date" width="8" dataFormat="MM-dd-yyyy" />
                                                                      <grd:datecolumn dataField="EndDate" headerText="End Date" width="8" dataFormat="MM-dd-yyyy" />
                                                                  </grd:dbgrid>
                                                                  
                                                                  <input TYPE="hidden" NAME="txtSubProjCurr" VALUE="<%=intCurr%>">
                                                                  <input TYPE="hidden" NAME="txtSubProjSortCol" VALUE="<%=strSortCol%>">
                                                                  <input TYPE="hidden" NAME="txtSubProjSortAsc" VALUE="<%=strSortOrd%>">
                                                                  <input TYPE="hidden" NAME="txtAttachCurr" VALUE="">    
                                                                  <input type="hidden" name="id" value="<%=currentAccountId%>">
                                                                  <input type="hidden" name="submitFrom" value="dbGrid">
                                                                  <input type="hidden" name="isRequestFromGrid" value="YES">
                                                              </td>
                                                          </tr>
                                                      </table>                                
                                                  </s:form> --%>

                                            </div>



                                            <div id="attachmentsList" class="tabcontent">
                                                <%-- <sx:div id="attachmentsList" label="Project Attachments"> --%>

                                                <%

                                                    /* String Variable for storing current position of records in dbgrid*/
                                                    strTmp = request.getParameter("txtCurr");

                                                    if (strTmp != null) {
                                                        intCurr = Integer.parseInt(strTmp);
                                                    }

                                                    /* Specifing Shorting Column */
                                                    strSortCol = request.getParameter("txtSortCol");

                                                    if (strSortCol == null) {
                                                        strSortCol = "ObjectType";
                                                    }

                                                    strSortOrd = request.getParameter("txtSortAsc");
                                                    if (strSortOrd == null) {
                                                        strSortOrd = "ASC";
                                                    }

                                                    /* Sql query for retrieving resultset from DataBase */
                                                  //  queryString = "Select Id,AttachmentType,DateOfUpload,UploadedBy,Description,ObjectId,ObjectType from tblPrjAttachments WHERE ObjectType ='Project'";
                                                  //  queryString = queryString + "AND ObjectId =" + currentAccountId + " ORDER BY DateOfUpload DESC";
                                                     queryString = "Select Id,AttachmentType,DateOfUpload,UploadedBy,Description,ObjectId,ObjectType from tblPrjAttachments WHERE ObjectType ='Project'";
                                                    queryString = queryString + " AND ObjectId =" + currentProjectId + " ORDER BY DateOfUpload DESC";
                                                    String attachmentAction = "getAttachment.action";

                                                    if (request.getAttribute("currentAccountId") != null) {
                                                      //  attachmentAction = attachmentAction + "?objectId=" + request.getAttribute("currentAccountId") + "&objectType=Project" + "&projectId=" + currentProjectId;
                                                         attachmentAction = attachmentAction + "?objectId=" + currentProjectId + "&objectType=Project" + "&projectId=" + currentProjectId+"&accountId="+request.getAttribute("currentAccountId");
                                                    }
                                                %>


                                               <form action="getProject.action"  method="post" name="frmDBGrid"> 
                                                    <table cellpadding="0" cellspacing="0" width="100%">
                                                        <tr>
                                                            <td class="headerText">
                                                                <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <!-- DataGrid for list all Attachments -->
                                                                <grd:dbgrid id="tblprjAttachments" name="tblprjAttachments" width="100" pageSize="8" 
                                                                currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">

                                                                    <grd:gridpager imgFirst="../includes/images/DBGrid/First.gif" imgPrevious="../includes/images/DBGrid/Previous.gif" 
                                                                                   imgNext="../includes/images/DBGrid/Next.gif" imgLast="../includes/images/DBGrid/Last.gif"
                                                                                   addImage="../includes/images/DBGrid/Add.png" 
                                                                    addAction="<%=attachmentAction%>"
                                                                                   scriptFunction="doNavigate"/>

                                                                    <grd:textcolumn dataField="AttachmentType"  headerText="Attachment Type"   width="30" sortable="true"/> 
                                                                    <grd:textcolumn dataField="ObjectType"  headerText="Object Type" width="30"/>  
                                                                    <grd:datecolumn dataField="DateOfUpload"  headerText="Date Uploaded" dataFormat="MM-dd-yyyy HH:mm:SS" width="20"/>
                                                                    <grd:imagecolumn headerText="Download" width="4" HAlign="center" 
                                                                                     imageSrc="../includes/images/download_11x10.jpg"
                                                                                     linkUrl="getDownload.action?Id={Id}" imageBorder="0"
                                                                                     imageWidth="11" imageHeight="10" alterText="Download"></grd:imagecolumn>
                                                                </grd:dbgrid>
                                                                <!-- these components are DBGrid Specific  -->

                                                               <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                                <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                                <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                                                                <input TYPE="hidden" NAME="txtSubProjCurr" VALUE="">
                                                                <input TYPE="hidden" NAME="submitFrom" VALUE="dbGrid">
                                                                <input type="hidden" name="id" value="<%=currentProjectId%>">
                                                                <input type="hidden" name="accountId" value="<%=currentAccountId%>">

                                                            </td>
                                                        </tr>
                                                    </table>    
                                                </form>  

                                                <%-- </sx:div> --%>
                                            </div>


                                            <!-- project team start -->

                                            <div id="projectTeamList" class="tabcontent">
                                                <%

                                                    /* String Variable for storing current position of records in dbgrid*/
                                                    strTmp2 = request.getParameter("txtProjTeamCurr");

                                                    if (strTmp2 != null) {
                                                        intCurr2 = Integer.parseInt(strTmp2);
                                                    }

                                                    /* Specifing Shorting Column */
                                                    strSortCol2 = request.getParameter("txtProjSortCol");

                                                    if (strSortCol2 == null) {
                                                        strSortCol2 = "ObjectType";
                                                    }

                                                    strSortOrd2 = request.getParameter("txtProjSortAsc");
                                                    if (strSortOrd2 == null) {
                                                        strSortOrd2 = "DESC";
                                                    }
                                                    blnSortAsc2 = (strSortOrd2.equals("ASC"));
                                                    /* Sql query for retrieving resultset from DataBase */
                                                    queryString = "SELECT tblProjectContacts.Id AS Id,AccountId,ProjectId,ResourceName AS EmpName ,Email,ResourceTitle,ObjectType,CASE WHEN (Billable=1) THEN  'Yes' ELSE 'No' END AS Billable,tblProjectContacts.StartDate,tblProjectContacts.Utilization,tblProjectContacts.Status as ResourceStatus,tblEmployee.Country AS ResourceCountry,tblProjectContacts.EndDate "
                        + "FROM tblProjectContacts LEFT OUTER JOIN tblProjects ON (tblProjectContacts.ProjectId=tblProjects.Id) LEFT OUTER JOIN tblEmployee ON (tblEmployee.Id=tblProjectContacts.ObjectId) ";
                                                    queryString = queryString + " WHERE tblProjectContacts.AccountId = " + currentAccountId + " AND tblProjectContacts.ProjectId = " + currentProjectId ;
                                                    //  out.println("queryString-->"+queryString);
                                                    String heirarchyAction = "addProjectTeam.action?accountId=" + currentAccountId + "&projectId=" + currentProjectId;
                                                    //String gridaction = "getProject.action?id="+currentProjectId+"&accountId="+currentAccountId;
%>
                                                <form action="getProject.action"  method="post" name="frmDBProjectTeamGrid"> 
                                                    <table cellpadding="0" cellspacing="0" width="100%">
                                                        <tr>
                                                            <td class="headerText">
                                                                <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <!-- DataGrid for list all Attachments -->
                                                                <grd:dbgrid id="tblProjectGrid" name="tblProjectGrid" width="98" pageSize="10"
                                                                currentPage="<%=intCurr2%>" border="0" cellSpacing="1" cellPadding="2"
                                                                dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">

                                                                    <grd:gridpager imgFirst="../includes/images/DBGrid/First.gif" imgPrevious="../includes/images/DBGrid/Previous.gif" 
                                                                                   imgNext="../includes/images/DBGrid/Next.gif" imgLast="../includes/images/DBGrid/Last.gif"
                                                                    addImage="../includes/images/DBGrid/Add.png"  addAction="<%=heirarchyAction%>" 
                                                                                   scriptFunction="getNextProjectsTeam"/>

                                                                    <grd:gridsorter sortColumn="<%=strSortCol2%>" sortAscending="<%=blnSortAsc2%>" 
                                                                                    imageAscending="../includes/images/DBGrid/ImgAsc.gif" 
                                                                                    imageDescending="../includes/images/DBGrid/ImgDesc.gif"/>   



                                                                    <grd:imagecolumn headerText="Edit" width="4" HAlign="center" imageSrc="../includes/images/DBGrid/newEdit_17x18.png"
                                                                                     linkUrl="getProjectTeamDetails.action?id={Id}&accountId={AccountId}&projectId={ProjectId}" imageBorder="0"
                                                                                     imageWidth="16" imageHeight="16" alterText="Click to edit"></grd:imagecolumn>

                                                                    <grd:textcolumn dataField="EmpName" headerText="ResourceName" width="18"/>
                                                                    <grd:textcolumn dataField="ResourceCountry" headerText="Country" width="8"/>
                                                                    <grd:datecolumn dataField="StartDate" headerText="StartDate"  width="10" dataFormat="MM/dd/yyyy"/>
                                                                    <grd:datecolumn dataField="EndDate" headerText="EndDate"  width="10" dataFormat="MM/dd/yyyy"/>
                                                                    <grd:datecolumn dataField="Utilization" headerText="Utilization" width="10"/>
                                                                   <%-- <grd:textcolumn dataField="Email" headerText="EmailId" width="20"/> --5>
                                                                    <%-- <grd:textcolumn dataField="ResourceTitle" headerText="Role" width="12"/> --%>
                                                                  <%--  <grd:decodecolumn dataField="ResourceTitle" decodeValues="1,2,3,4,5,6,7" displayValues="Team Member,Team Lead,PM Offshore,PM Onsite,PM Customer,Sponsor,Delivery Manager" headerText="Role" valueSeperator="," width="10"/> --%>
                                                                    <grd:decodecolumn dataField="ObjectType" decodeValues="E,C,V" displayValues="Employee,Customer Contact,Vendor Contact" headerText="ResourceType" valueSeperator="," width="10"/>
                                                                    <grd:numbercolumn dataField="Billable" headerText="Billable" width="10"/>
                                                                    <grd:numbercolumn dataField="ResourceStatus" headerText="Status" width="10"/>
                                                                </grd:dbgrid>
                                                                <!-- these components are DBGrid Specific  -->

                                                                <input TYPE="hidden" NAME="txtProjTeamCurr" VALUE="<%=intCurr2%>">
                                                                <input TYPE="hidden" NAME="txtProjSortCol" VALUE="<%=strSortCol2%>">
                                                                <input TYPE="hidden" NAME="txtProjSortAsc" VALUE="<%=strSortOrd2%>">
                                                                <input TYPE="hidden" NAME="txtAttachCurr1" VALUE="">
                                                                <input TYPE="hidden" NAME="submitFrom" VALUE="dbGrid">
                                                                <input type="hidden" name="id" value="<%=currentProjectId%>">
                                                                <input type="hidden" name="accountId" value="<%=currentAccountId%>">

                                                            </td>
                                                        </tr>
                                                    </table>    
                                                </form>  

                                            </div>

                                            <!-- project team end -->
                                            <!-- PMO Team Start -->


                                            <div id="risksList" class="tabcontent">
                                                <%

                                                    /* String Variable for storing current position of records in dbgrid*/
                                                    strTmp3 = request.getParameter("txtRiskCurr");

                                                    if (strTmp3 != null) {
                                                        intCurr3 = Integer.parseInt(strTmp3);
                                                    }

                                                    /* Specifing Shorting Column */
                                                    strSortCol3 = request.getParameter("txtRiskSortCol");

                                                    if (strSortCol3 == null) {
                                                        strSortCol3 = "ObjectType";
                                                    }

                                                    strSortOrd3 = request.getParameter("txtRiskSortAsc");
                                                    if (strSortOrd3 == null) {
                                                        strSortOrd3 = "ASC";
                                                    }


                                                    /* Sql query for retrieving resultset from DataBase */
                                                    queryString = "SELECT Id,AccountId,ProjectId,Description,AssignedTo,STATUS,RiskImpact,DateClosed,Resolution FROM tblProjectRisks";
                                                    queryString = queryString + " where  accountId =" + currentAccountId + " and projectId=" + currentProjectId + " ORDER BY CreatedDate DESC";
//out.println(queryString);
                                                    String projectAction = "getProjectRisks.action";

                                                    if (request.getAttribute("currentAccountId") != null) {
                                                        projectAction = projectAction + "?accountId=" + request.getAttribute("currentAccountId") + "&projectId=" + currentProjectId;
                                                    }
                                                %>

                                                <form action="getProject.action"  method="post" name="frmDBPrjRiskGrid">
                                                    <table cellpadding="0" cellspacing="0" width="100%">
                                                        <tr>
                                                            <td class="headerText">
                                                                <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <!-- DataGrid for list all Attachments -->
                                                                <grd:dbgrid id="tblprjRisks" name="tblprjRisks" width="100" pageSize="8" 
                                                                currentPage="<%=intCurr3%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">

                                                                    <grd:gridpager imgFirst="../includes/images/DBGrid/First.gif" imgPrevious="../includes/images/DBGrid/Previous.gif" 
                                                                                   imgNext="../includes/images/DBGrid/Next.gif" imgLast="../includes/images/DBGrid/Last.gif"
                                                                    addImage="../includes/images/DBGrid/Add.png"  addAction="<%=projectAction%>" 
                                                                               scriptFunction="getNextRisk"    />
                                                                    <grd:imagecolumn headerText="Edit" width="4" HAlign="center" imageSrc="../includes/images/DBGrid/newEdit_17x18.png"
                                                                                     linkUrl="getProjectRisks.action?id={Id}&accountId={AccountId}&projectId={ProjectId}" imageBorder="0"
                                                                                     imageWidth="16" imageHeight="16" alterText="Click to edit"></grd:imagecolumn>

                                                                    <grd:textcolumn dataField="RiskImpact"  headerText="Impact"   width="30" sortable="true"/> 
                                                                     <grd:anchorcolumn dataField="Description" linkUrl="javascript:getRiskDescription('{Id}')" headerText="Description"
                                                                                      linkText="Click To View"  width="30" HAlign="center"/>
                                                                    <%--   <grd:textcolumn dataField="Resolution"  headerText="Resolution" width="30"/>  --%>
                                                                    <grd:anchorcolumn dataField="Resolution" linkUrl="javascript:getRiskResolution('{Id}')" headerText="Resolution"
                                                                                      linkText="Click To View"  width="30" HAlign="center" />
                                                                      <grd:textcolumn dataField="STATUS"  headerText="Status" width="30"/>  

                                                                    <grd:textcolumn dataField="AssignedTo"  headerText="AssignedTo" width="30"/>  
                                                                    <grd:datecolumn dataField="DateClosed"  headerText="DateClosed" dataFormat="MM-dd-yyyy" width="20"/>
                                                                    
                                                                </grd:dbgrid>
                                                                <!-- these components are DBGrid Specific  -->

                                                                <input TYPE="hidden" NAME="txtRiskCurr" VALUE="<%=intCurr3%>">
                                                                <input TYPE="hidden" NAME="txtRiskSortCol" VALUE="<%=strSortCol3%>">
                                                                <input TYPE="hidden" NAME="txtRiskSortAsc" VALUE="<%=strSortOrd3%>">
                                                                <input TYPE="hidden" NAME="submitFrom" VALUE="dbGrid">
                                                                <input type="hidden" name="id" value="<%=currentProjectId%>">
                                                                <input type="hidden" name="accountId" value="<%=currentAccountId%>">
                                                            </td>
                                                        </tr>
                                                    </table>    
                                                </form>  

                                            </div>
                                            <div id="tasks" class="tabcontent">
                                                <%

                                                    /* String Variable for storing current position of records in dbgrid*/
                                                   strTmp4 = request.getParameter("txtPrjTaskCurr");

                                                    if (strTmp4 != null) {
                                                        intCurr4 = Integer.parseInt(strTmp4);
                                                    }

                                                    /* Specifing Shorting Column */
                                                    strSortCol4 = request.getParameter("txtPrjTaskSortCol");

                                                    if (strSortCol4 == null) {
                                                        strSortCol4 = "ObjectType";
                                                    }

                                                    strSortOrd4 = request.getParameter("txtPrjTaskSortAsc");
                                                    if (strSortOrd4 == null) {
                                                        strSortOrd4 = "ASC";
                                                    }


                                                    /* Sql query for retrieving resultset from DataBase */
                                                    queryString = "SELECT tblEmpTasks.Id AS TaskId,ProjectName,tblEmpTasks.Title,tblLKIssueType.Description,tblEmpTasks.Severity,tblEmpTasks.createdBy,Date(tblEmpTasks.createdDate) as createdDate,tblProjects.Id AS ProjectId FROM tblProjects LEFT OUTER JOIN tblEmpTasks ON (tblProjects.Id=tblEmpTasks.Project_id) ";
                                                    queryString = queryString + "LEFT JOIN tblLKIssueType ON(tblEmpTasks.IssueRel=tblLKIssueType.IsIssueRelatedToID) WHERE tblLKIssueType.IssueTypeId=tblEmpTasks.IssueType AND tblEmpTasks.IssueRel =2 AND tblProjects.Id =" + currentProjectId + " ORDER BY tblEmpTasks.createdDate DESC";
                                                    //   out.println(queryString);
                                                    attachmentAction = "getAttachment.action";

                                                    if (request.getAttribute("currentAccountId") != null) {
                                                        attachmentAction = attachmentAction + "?objectId=" + request.getAttribute("currentAccountId") + "&objectType=Project" + "&projectId=" + currentProjectId;
                                                    }
                                                %>

                                                <form action="getProject.action"  method="post" name="frmDBPrjTasksGrid"> 
                                                    <table cellpadding="0" cellspacing="0" width="100%">
                                                        <tr>
                                                            <td class="headerText">
                                                                <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <!-- DataGrid for list all Attachments -->
                                                                <grd:dbgrid id="tblProjectTasks" name="tblProjectTasks" width="100" pageSize="8" 
                                                                currentPage="<%=intCurr4%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable" >

                                                                    <grd:gridpager imgFirst="../includes/images/DBGrid/First.gif" imgPrevious="../includes/images/DBGrid/Previous.gif" 
                                                                                   imgNext="../includes/images/DBGrid/Next.gif" imgLast="../includes/images/DBGrid/Last.gif"
                                                                                   addImage="../includes/images/DBGrid/Add.png"  addAction="javascript:mytoggleOverlayForTaskAdd()"  
                                                                                  scriptFunction="getNextTask"  />

                                                                   <grd:anchorcolumn dataField="Title"  headerText="Title" linkUrl="javascript:toggleEditOverlayForProjectTask('{TaskId}')" linkText="{Title}" width="30" sortable="true"/> 
                                                                    <grd:textcolumn dataField="Description"  headerText="Type"   width="30" sortable="true"/> 
                                                                    <grd:textcolumn dataField="Severity"  headerText="Severity" width="30"/>  
                                                                    <grd:textcolumn dataField="createdBy"  headerText="CreatedBy" width="30"/>  
                                                                    <grd:datecolumn dataField="createdDate"  headerText="CreatedDate" dataFormat="MM-dd-yyyy" width="20"/>

                                                                </grd:dbgrid>
                                                                <!-- these components are DBGrid Specific  -->

                                                               <input TYPE="hidden" NAME="txtPrjTaskCurr" VALUE="<%=intCurr4%>">
                                                                <input TYPE="hidden" NAME="txtPrjTaskSortCol" VALUE="<%=strSortCol4%>">
                                                                <input TYPE="hidden" NAME="txtPrjTaskSortAsc" VALUE="<%=strSortOrd4%>">
                                                                <input TYPE="hidden" NAME="submitFrom" VALUE="dbGrid">
                                                                  <input type="hidden" name="id" value="<%=currentProjectId%>">
                                                                <input type="hidden" name="accountId" value="<%=currentAccountId%>">

                                                            </td>
                                                        </tr>
                                                    </table>    
                                                </form>  

                                            </div>
                                            <%
                                            }catch(Exception ex){
                                            out.println(ex.toString());
                                            }finally{
                                            if(connection!= null){
                                            connection.close();
                                            }
                                            }
                                            %>

                                            <%-- </sx:tabbedpanel> --%>
                                            <!--//END TABBED PANNEL : -->
                                        </div>

                                        <script type="text/javascript">

                                            var countries=new ddtabcontent("ProjectDetailsTabs")
                                            countries.setpersist(true)
                                            countries.setselectedClassTarget("link") //"link" or "linkparent"
                                            countries.init()

                                        </script>

                                    </td>
                                </tr>
                            </table>
                        </td>
                        <!--//END DATA COLUMN : Coloumn for Screen Content-->
                    </tr>
                </table>
            </td>
        </tr>
        <!--//END DATA RECORD : Record for LeftMenu and Body Content-->

        <!--//START FOOTER : Record for Footer Background and Content-->
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
        <!--//END FOOTER : Record for Footer Background and Content-->
    </table>
    <!--//END MAIN TABLE : Table for template Structure-->
    <script type="text/javascript">
		$(window).load(function(){
	init1('0');
		});
		</script>
</body>
</html>
