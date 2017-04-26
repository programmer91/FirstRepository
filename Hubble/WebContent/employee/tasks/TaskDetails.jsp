<!-- 
 *******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  November 20, 2007, 3:25 PM
 *
 * Author  :  Ajay Tummapala
 *
 * File    : TaskDetails.jsp
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.`
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
-->
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>

<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ page import="com.freeware.gridtag.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="com.mss.mirage.employee.tasks.TasksVTO"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Hubble Organization Portal :: Employee Issue Details</title>
  <%--  <sx:head cache="true"/> 
     <sj:head jqueryui="true"/>--%>
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tooltip.css"/>">
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tooltip.js"/>"></script>   
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>   
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>   
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmpIssueAjax.js"/>"></script> 
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/IssueCatagoryUtil.js"/>"></script> 
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmpStandardClientValidations.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
    <script type="text/javascript" src="<s:url value="/includes/javascripts/IssueFill.js"/>"></script>
     <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmployeeAjax.js"/>"></script>
    <script type="text/javascript">
        
        
function hideShowDate34()
{
//alert("hello");
    var repeat = document.getElementById("repeat").value;
   // alert("repeat-->"+repeat);
    if(repeat == "0"){
    document.getElementById("datesTask").style.display = 'table-row';
   // document.getElementById("alertId").style.display = 'visible';
   document.getElementById("checkAlert").disabled=false;
   document.getElementById("days").disabled=false;
    document.getElementById("days").value = "0";
     }
     else if(repeat == "7" || repeat == "30" || repeat == "60"){
       document.getElementById("datesTask").style.display = 'none';
      // document.getElementById("alertId").style.display = 'none';
       document.getElementById("checkAlert").disabled=true;
        document.getElementById("days").disabled=true;
        document.getElementById("days").value = "0";
       
     }
}



function stopRKey(evt) {
  var evt = (evt) ? evt : ((event) ? event : null);
  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null);
  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;}
}

document.onkeypress = stopRKey;


function changeVisibility()
{
   // alert("hello");
    
    if(document.getElementById("checkAlert").checked)
    {
    document.getElementById("days").disabled=false ;
     
    }else
    {
    document.getElementById("days").disabled=true ;
    document.getElementById("days").value="" ;
    }
    
}
function checkedAlert()
{
    document.getElementById("checkAlert").checked = true;
   
}
function hideShowRepeat(valueAction,repeatValue)
{
     var repeat = valueAction;
    // alert("repeat-->"+repeat);
   //  alert("repeatValue--->"+repeatValue);
    if(repeat == "editTask" && (repeatValue == "7" || repeatValue == "30" || repeatValue == "60")){
   // document.getElementById("datesTask").style.display = 'table-row';
  // document.getElementById("checkAlert").disabled=false;
  // document.getElementById("days").disabled=false;
   // document.getElementById("days").value = "0";
    document.getElementById("datesTask").style.display = 'none';
       document.getElementById("checkAlert").disabled=true;
        document.getElementById("days").disabled=true;
     }
    
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
<%!
String currentAction =null;
int repeat = 0;
TasksVTO tasksVTO =null;

%>
<%
currentAction = request.getAttribute("currentAction").toString();
tasksVTO = (TasksVTO)request.getAttribute("currentTask");
repeat = tasksVTO.getRepeat();

%>
 <%!
        /* Declarations */
        Connection connection;

        String accountPrimaryTeamMember;
        String userId;
        String userRoleName;
        int isUserManager;

        String queryString;
        String currentAccountId;
        String strTmp;
        String strSortCol;
        String strSortOrd;

        int intSortOrd = 0;
        int intCurr;
        boolean blnSortAsc = true;
        %>
   <%-- <s:if test="%{currentAction=='addTask'}">     --%>
<%-- <body class="bodyGeneral" onload="init1('0');hideShowRepeat('<%=currentAction%>','<%=repeat%>');" oncontextmenu="return false;">  --%>
<body class="bodyGeneral" oncontextmenu="return false;"> 
<%-- </s:if>
    <s:else>
        <body class="bodyGeneral" onload="init1('0');hideShowRepeat('<%=currentAction%>','<%=repeat%>');getProjectsForAccountId();" oncontextmenu="return false;"> 
    </s:else> --%>
<!-//START MAIN TABLE : Table for template Structure-->
<table class="templateTable1000x580" align="center" cellpadding="0" cellspacing="0">

<!--//START HEADER : Record for Header Background and Mirage Logo-->
<tr class="headerBg">
    <td valign="top">
        <s:include value="/includes/template/Header.jsp" />                    
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
                <ul id="accountTabs" class="shadetabs" >
                    <s:if test="%{currentAction=='addTask'}">
                        <li ><a href="#" class="selected" rel="issueTab"  >Create Task </a></li>
                    </s:if>
                    <s:else>
                         <li ><a href="#" class="selected" rel="issueTab"  >Edit Task </a></li>
                    </s:else>

                </ul>
                <!--//START TABBED PANNEL : -->
               <div  style="border:1px solid gray; width:845px;height: 400px; overflow:auto; margin-bottom: 1em;">
                <!--//START TAB : -->
                <div id="issueTab" class="tabcontent"  >
                    <s:form name="issuesForm" id="issuesForm" action="%{currentAction}" method="POST" enctype="multipart/form-data" theme="simple" onsubmit="return validateTaskForm();">
                        <table width="100%" cellpadding="2" cellspacing="2" border="0" >
                            <tr align="right">
                                <td class="headerText1" colspan="4" >
                                    <s:property value="#request.resultMessage"/>
                                    <s:property value="%{resM}"/>
                                    <s:property value="#session.resultMsg"/>
                                    <%
                                    if(session.getAttribute("resultMsg") != null) {
                                        session.removeAttribute("resultMsg");
                                    }
                                    %>
                                    <s:if test="%{currentAction == 'addTask'}">
                                        <s:submit label="Submit" value="Save" cssClass="buttonBg" />
                                    </s:if>
                                    <s:if test="%{(currentAction == 'editTask' && currentTask.statusId != 'Closed')}">
                                        <s:submit label="Submit" value="Update" cssClass="buttonBg" />
                                        
                                        <s:if test="%{navTo!=''}">
                                            <a href="<s:property value="%{navTo}"/>.action?check=null" style="align:center;">
                                                <img alt="Back to List"
                                                     src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                     width="66px" 
                                                     height="19px"
                                                 border="0" align="bottom"></a>
                                        </s:if>
                                        <s:else>
                                            <a href="getTasks.action?check=null" style="align:center;">
                                                <img alt="Back to List"
                                                     src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                     width="66px" 
                                                     height="19px"
                                                 border="0" align="bottom"></a>
                                        </s:else>
                                        
                                    </s:if>
                                    <s:hidden name="iFlag" value="%{currentTask.iFlag}"/> 
                                    <s:hidden name="a" value="%{currentAction}"/>
                                    <s:hidden name="id" value="%{currentTask.id}"/>
                                    <s:hidden name="activityId" value="%{currentTask.activityId}"/>
                                    <s:hidden name="strAttachmentId" value="%{currentTask.attachmentId}"/> 
                                    <s:hidden name="navTo" value="%{navTo}"/>
                                    <s:hidden name="issueId" value="%{issueId}"/>
                                    <s:hidden value="%{currentSearch}"/> 
                                    <s:hidden name="submitForm" value="search"/>
                                    
                                </td>
                            </tr>
                           
                                
                              <%--  <tr>
                                    <td width="200px" class="fieldLabel">Customer&nbsp;Name&nbsp;: <FONT color="red"  ><em>*</em></FONT></td>
                                    <td width="1%">  
                                        <s:textfield name="customerName" id="customerName"   cssClass="inputTextBlueCustomer"  value="%{currentTask.customerName}"  theme="simple" onkeyup="fillCustomerInIssue();"/>
                                        <div id="validationMessage"></div>
                                        <s:hidden name="customerId" value="%{currentTask.customerId}" id="customerId"/> 
                                    </td>
                                    
                                    <td  width="1%" class="fieldLabel">Project&nbsp;Name&nbsp;: </td>
                                    <td>
                                        
                                        <s:textfield name="projectName" id="projectName"   cssClass="inputTextBlue"  value="%{currentTask.projectName}"  theme="simple" onkeyup="fillProject();"/>
                                        <s:hidden name="projectId" value="%{currentTask.projectId}" id="projectId"/>
                                        <div id="ProjectvalidationMessage"></div>
                                    </td>     
                                </tr>  --%>
                               <tr>
                               <%
 if(session.getAttribute(ApplicationConstants.SESSION_EMPTYPE).equals("e")){ 

%>                                
                               
                                    <td width="20%" class="fieldLabel">Customer&nbsp;Name&nbsp;: <FONT color="red"  ><em>*</em></FONT></td>
                                    <td width="1%">  
                                        <s:textfield name="customerName" id="customerName"   cssClass="inputTextBlueCustomer"  value="%{currentTask.customerName}"  theme="simple" onkeyup="fillCustomerInIssue();" />
                                        <div id="validationMessage"></div>
                                        <s:hidden name="customerId" value="%{currentTask.customerId}" id="customerId"/> 
                                    </td>
                                    
                                    <%--  <td  width="1%" class="fieldLabel">Project&nbsp;Name&nbsp;: </td>
                                  <td>
                                        
                                        <s:textfield name="projectName" id="projectName"   cssClass="inputTextBlue"  value="%{currentTask.projectName}"  theme="simple" onkeyup="fillProject();"  />
                                        <s:hidden name="projectId" value="%{currentTask.projectId}" id="projectId"/>
                                        <div id="ProjectvalidationMessage"></div>
                                    </td>   --%>   
                                
<%
 }
else if(session.getAttribute(ApplicationConstants.SESSION_EMPTYPE).equals("c")||session.getAttribute(ApplicationConstants.SESSION_EMPTYPE).equals("v"))
//else
       {
%>      
                                
                                        <td class="fieldLabel" width="20%">Customers: </td>
                                        <td width="1%">
                                             
                                               <s:select list="myAccounts" name="customerId" id="customerId" onchange="getProjectsForAccountId();" headerKey="-1" headerValue="--Select Project--" cssClass="inputSelectForProjects" value="%{currentTask.customerId}"  disabled="false"/>  
                                        </td>
                                       
                               
<%
}
%>
 <td class="fieldLabel" width="1%">Projects: </td>
                                        <td width="25%">
                                               <s:select list="projectNamesMap" name="projectId" id="projectId" onchange="hidePriority();" headerKey="-1" headerValue="--Select Project--" cssClass="inputSelect" value="%{currentTask.projectId}" disabled="false"/> 
                                        </td>
 </tr>
                                <tr>
                                    <td  class="fieldLabel" valign="top">Title : <FONT color="red"  ><em>*</em></FONT></td>                                                                  
                                    <td  colspan="4"><s:textfield name="title" id="title"  value="%{currentTask.title}" onchange="fieldLengthValidator(this);"  cssClass="inputTextBlueTitle" /></td>
                                </tr> 
                           
                            
                            <tr>
                                
                                <td class="fieldLabel">Severity :</td>
                                <td><s:select list="{'Low','Medium','High'}"
                                                  name="severityId"
                                                  value="%{currentTask.severityId}"
                                              cssClass="inputSelect"  /></td>
                                
                                <td class="fieldLabel"> Status :</td>
                                <td><s:select list="{'Created','InProgress','Closed','Not Closed','Assigned','Not Started'}"
                                                  name="statusId"
                                                  id="statusId"
                                                  value="%{currentTask.statusId}"
                                              cssClass="inputSelect"  /></td>
                            </tr>
                            
                            <tr><s:hidden name="resourceType" id="resourceType" value="%{#session.empType}"/>
                                                               <%
 if(session.getAttribute(ApplicationConstants.SESSION_EMPTYPE).equals("e")){ 

%>  
<td class="fieldLabel">Primary&nbsp;Assign&nbsp;To&nbsp;:</td>
                                <td ><s:textfield name="assignedToUID" id="assignedToUID" onkeyup="fillEmployee('pre');"  cssClass="inputTextBlue" value="%{currentTask.assignedToUID}" theme="simple" readonly="false"/>
                                    <div id="assignEmpValidationMessage"></div>  
                                    <s:hidden name="preAssignEmpId" value="%{currentTask.preAssignEmpId}" id="preAssignEmpId"/> 
                                </td>   
                                
                                <td class="fieldLabel">Secondary&nbsp;Assign&nbsp;To&nbsp;:</td>
                                <td ><s:textfield name="postAssignedToUID" id="postAssignedToUID" onkeyup="fillEmployee('post');" cssClass="inputTextBlue" value="%{currentTask.postAssignedToUID}" theme="simple" readonly="false"/>
                                    <div id="postAssignEmpValidationMessage"></div>  
                                    <s:hidden name="postAssignEmpId" value="%{currentTask.postAssignEmpId}" id="postAssignEmpId"/>
                                </td> 
<%
 }
else if(session.getAttribute(ApplicationConstants.SESSION_EMPTYPE).equals("c")||session.getAttribute(ApplicationConstants.SESSION_EMPTYPE).equals("v"))
//else
       {
%>          
<td class="fieldLabel">Primary&nbsp;Assign&nbsp;To&nbsp;:</td>
                                <td ><s:textfield name="assignedToUID" id="assignedToUID" onkeyup="fillEmployeeForCustomerLogin('pre');"  cssClass="inputTextBlue" value="%{currentTask.assignedToUID}" theme="simple" readonly="false"/>
                                    <div id="assignEmpValidationMessage"></div>  
                                    <s:hidden name="preAssignEmpId" value="%{currentTask.preAssignEmpId}" id="preAssignEmpId"/> 
                                </td>   
                                
                                <td class="fieldLabel">Secondary&nbsp;Assign&nbsp;To&nbsp;:</td>
                                <td ><s:textfield name="postAssignedToUID" id="postAssignedToUID" onkeyup="fillEmployeeForCustomerLogin('post');" cssClass="inputTextBlue" value="%{currentTask.postAssignedToUID}" theme="simple" readonly="false"/>
                                    <div id="postAssignEmpValidationMessage"></div>  
                                    <s:hidden name="postAssignEmpId" value="%{currentTask.postAssignEmpId}" id="postAssignEmpId"/>
                                </td>
                                <%
}
%>
                               
                            </tr> 
                            <tr>
                                <td class="fieldLabel"> Repeat :</td>
                                <td><s:select list="repeatMap"
                                                  name="repeat"
                                                  id="repeat"
                                                  value="%{currentTask.repeat}"
                                              cssClass="inputSelect" onchange="hideShowDate34();" /></td>
                                              <div id="alertId">
                                                  <td  class="fieldLabel" valign="top">Alert&nbsp;:                                                                 
                                                      
                                                      <s:if test="%{currentAction=='addTask'}">
                                                          <s:checkbox  value="0" id="checkAlert"  name="checkAlert" onclick="changeVisibility();"/>
                                                      </s:if>
                                                      <s:if test="%{currentAction=='editTask'}">
                                                          <s:if test="%{currentTask.days != null}">
                                                              <s:checkbox  value=""  fieldValue="true" id="checkAlert" name="checkAlert" onclick="changeVisibility();"/>&nbsp;
                                                          </s:if>
                                                          <s:else>
                                                              <s:checkbox  value=""  fieldValue="false" id="checkAlert" name="checkAlert" onclick="changeVisibility();"/>&nbsp;
                                                          </s:else>
                                                      </s:if>
                                                  </td> <td>
                                                      <s:label cssClass="fieldLabel" valign="top" value="NoOfDays:"/>
                                                      <s:if test="%{currentAction=='addTask'}">
                                                          <s:textfield name="days" id="days" value="0" onchange="fieldLengthValidator(this);"  onkeypress="return isNumberKey(event)" size="1" cssClass="inputTextBlue2"/><%--<s:label cssClass="fieldLabel" value="(Ex:7)"/> --%>
                                                      </s:if>
                                                      <s:if test="%{currentAction=='editTask'}">
                                                          <s:if test="%{currentTask.days != null}">
                                                              <s:textfield name="days" id="days" value="%{currentTask.days}" onchange="fieldLengthValidator(this);" disabled="false" onkeypress="return isNumberKey(event)" size="1" cssClass="inputTextBlue2"/><%--<s:label cssClass="fieldLabel" value="(Ex:7)"/>  --%>
                                                          </s:if>
                                                          <s:else>
                                                              <s:textfield name="days" id="days" value="%{currentTask.days}" onchange="fieldLengthValidator(this);"  onkeypress="return isNumberKey(event)" size="1" cssClass="inputTextBlue2"/><%--<s:label cssClass="fieldLabel" value="(Ex:7)"/>    --%>
                                                          </s:else>
                                                      </s:if>
                                                      
                                                  </td>
                                              </div>
                            </tr>
                            <tr id="datesTask">
                                
                                <td class="fieldLabel">Start&nbsp;Date&nbsp;:</td>
                                <td>
                                    
                                    <s:textfield  name="dateAssigned" id="dateAssigned" value="%{dateAssigned}" cssClass="inputTextBlue"  />
                                    <a href="javascript:cal2.popup();"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                         width="20" height="20" border="0"></a> 
                                </td>
                                <td class="fieldLabel">Due&nbsp;Date&nbsp;:</td>
                                <td colspan="3">
                                    
                                    <s:textfield name="dateClosed" id="dateClosed" value="%{dateClosed}"  cssClass="inputTextBlue"/>
                                    <a href="javascript:cal3.popup();">
                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                         width="20" height="20" border="0"></a>
                                    
                                </td>
                                <script type="text/JavaScript">
                                                         /*  var cal1 = new CalendarTime(document.forms['issuesForm'].elements['dateCreated']);
                                                           cal1.year_scroll = true;
                                                           cal1.time_comp = true;*/                                                           
                                            
                                                           var cal2 = new CalendarTime(document.forms['issuesForm'].elements['dateAssigned']);
                                                           cal2.year_scroll = true;
                                                           cal2.time_comp = true;
                                            
                                                           var cal3 = new CalendarTime(document.forms['issuesForm'].elements['dateClosed']);
                                                           cal3.year_scroll = true;
                                                           cal3.time_comp = true;
                                </script>
                                
                            </tr>
                            <tr>
                                
                                
                                <s:if test="%{currentAction=='editTask'}">
                                    <td class="fieldLabel" valign="top">Completed&nbsp;:</td>
                                    <td valign="top">
                                        <s:textfield name="perCompleted" id="perCompleted" value="%{currentTask.perCompleted}" onchange="fieldLengthValidator(this);" onkeypress="return isNumberKey(event)" size="2" cssClass="inputTextBlue2"/><s:label cssClass="fieldLabel" value="%"/><s:label cssClass="fieldLabel" value="(Ex:50)"/>
                                    </td>    
                                </s:if>
                                
                            </tr>
                            
                            
                            
                            <tr>
                                <td  class="fieldLabel">Description :</td>                                                                  
                                <td  colspan="3"><s:textarea name="description" id="description"  
                                                                 value="%{currentTask.description}" 
                                                             cssClass="inputTextareaDesc" onchange="fieldLengthValidator(this);"/></td>
                            </tr>
                            <s:if test="#session.isUserManager == 1 || #session.isUserTeamLead==1">
                                <s:if test="%{currentAction=='editTask'}"> 
                                    <tr>
                                        <td class="fieldLabel">Resolution :
                                        </td>
                                        <td colspan="4">
                                            <%-- <s:textarea name="resolution" id = "resolution" rows="5" cols="82" value="%{currentTask.resolution}" onchange="fieldLengthValidator(this);" cssClass="inputTextarea"/>
               --%>
                                            <s:textarea name="resolution" id = "resolution" cssClass="inputTextareaDesc" value="%{currentTask.resolution}" onchange="fieldLengthValidator(this);" />
                                        </td> 
                                        
                                    </tr>
                                </s:if>
                            </s:if>
                            
                            <tr>
                                <s:if test="%{currentAction=='addTask'}">
                                    <td class="fieldLabel">Attachments :</td>
                                    <td colspan="3">
                                        
                                        <s:file name="upload"  theme="simple" size="60"/>
                                        
                                    </td>
                                </s:if>
                                <s:if test="%{currentTask.uploadFileName!='No File Attached' && currentAction !='addTask'}">
                                    <td class="fieldLabel">Attachments :</td> 
                                    <td colspan="3"> 
                                        <a class="navigationText" href="<s:url action="download">
                                               <s:param name="Id" value="%{currentTask.attachmentId}"/>
                                           </s:url>"><s:property value="%{currentTask.uploadFileName}"/>
                                        </a>
                                    </td>
                                </s:if>
                            </tr> 
                            
                            
                            
                        </table>
                    </s:form>
                    
                    <!-- this script for After loading Form it will instantiate Calender Objects as you require -->
    
                </div>                                       
    </div>                               
    <%--  </sx:tabbedpanel> --%>
    <!--//END TABBED PANNEL : -->
    <script type="text/javascript">

var countries=new ddtabcontent("accountTabs")
countries.setpersist(false)
countries.setselectedClassTarget("link") //"link" or "linkparent"
countries.init()

                                </script>
                                 <!-- Attachments Grid Start -->
   <s:if test="%{currentAction!='addTask'}">
   
   <ul id="taskAttachments" class="shadetabs" >
       
       <li ><a href="#" class="selected" rel="accountsListTab1" >Task&nbsp;Attachments</a></li>
       
       
   </ul> 
   <div  style="border:1px solid grey; width:840px; overflow:auto; margin-bottom: 1em;">
       <%--    <sx:div id="accountsListTab" label="Accounts List" cssStyle="overflow:auto;"> --%>
       <div id="accountsListTab1" class="tabcontent" >
           
           <!--//START TAB : -->
           <%--   <sx:div id="accountsListTab" label="%{title}s List" cssStyle="overflow:auto;"> --%>
                                        
           <%
           /* String Variable for storing current position of records in dbgrid*/
           strTmp = request.getParameter("txtCurr");
           
           intCurr = 1;
           
           if (strTmp != null)
           intCurr = Integer.parseInt(strTmp);
           
           /* Specifing Shorting Column */
           strSortCol = request.getParameter("Colname");
           
           if (strSortCol == null) strSortCol = "DateUploaded";
           
           strSortOrd = request.getParameter("txtSortAsc");
           if (strSortOrd == null) strSortOrd = "ASC";
           
           try{
           
           /* Getting DataSource using Service Locator */
           
           connection = ConnectionProvider.getInstance().getConnection();
           
           //Retrieving Users Rolename from Session Attributes.
           
           
           /* Sql query for retrieving resultset from DataBase */
           queryString  =null;
           
           //int authorTopicId = (Integer)request.getAttribute("topicId");
           int objectId = Integer.parseInt(request.getAttribute("taskObjectId").toString());
           queryString = "SELECT Id, ObjectId, ObjectType, AttachmentName, AttachmentLocation, AttachmentFileName , DateUploaded,UploadedBy FROM tblTaskAttachments WHERE  ObjectId = "+objectId;
           String addAttachmentAction = "../tasks/addTaskAttachment.action?taskId="+objectId;
           
           
           %>
           
           <table cellpadding="0" cellspacing="0" width="100%" border="0" >
               
               
               <tr align="right">
                   <%--  <td class="headerText">
                                                              
                                                               <img alt="Home" 
                                                                    src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" 
                                                                    width="100%" 
                                                                    height="13px" 
                                                                    border="0">
                                                           </td> --%>
                   <tr>
                       <td class="headerText"> <a href="<%=addAttachmentAction%>" style="align:left;">
                               <img alt="Add Attachment"
                                    src="<s:url value="/includes/images/add.gif"/>" 
                                    width="33px" 
                                    height="19px"
                                border="0" align="left"></a>&nbsp;&nbsp;
                           
                       </td>
                   </tr>
               </tr> 
               
               <!-- BEGIN:: DBGrid Specific -->  
                                               
               <tr>
                   <td>
                       <div style="width:840px;">
                           <s:form action="" theme="simple" name="frmDBGrid" method="post">   
                           <grd:dbgrid id="tblTaskAttachments" name="tblTaskAttachments" width="100" pageSize="6" 
                                       currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                       dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                               
                               <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                              imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"/>
                               
                               <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" 
                                               imageAscending="../includes/images/DBGrid/ImgAsc.gif" 
                                               imageDescending="../includes/images/DBGrid/ImgDesc.gif"/>  
                               <grd:rownumcolumn headerText="SNo" width="2" HAlign="center"/>       
                               <grd:textcolumn dataField="AttachmentName" headerText="AttachmentName" width="10"/>
                               <grd:textcolumn dataField="AttachmentFileName" headerText="AttachmentFileName" width="10"/>
                               <grd:textcolumn dataField="UploadedBy" headerText="Uploaded By" width="10"/>
                               <grd:datecolumn dataField="DateUploaded" headerText="Date Uploaded" dataFormat="MM-dd-yyyy" width="10"/> 
                               <grd:imagecolumn headerText="Download" width="4" HAlign="center" 
                                                imageSrc="../../includes/images/download_11x10.jpg"
                                                linkUrl="taskAttachmentDownload?id={Id}&fileName={AttachmentFileName}" imageBorder="0"
                                                imageWidth="11" imageHeight="10" alterText="Download"></grd:imagecolumn>
                               
                           </grd:dbgrid>
                           
                           
                           <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                           <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                           <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                           
                       </div>
                   </td>
               </tr>
           </table>                                
           
           </s:form>
           <%
           connection.close();
           connection = null;
           }catch(Exception ex){
           out.println(ex.toString());
           }finally{
           if(connection!= null){
           connection.close();
           connection = null;
           }
           }
           %>
           
       </div>
   </div>
   <!--//END TABBED PANNEL : -->
   <script type="text/javascript">
var countries=new ddtabcontent("taskAttachments")
countries.setpersist(false)
countries.setselectedClassTarget("link") //"link" or "linkparent"
countries.init()

   </script>
   
   <!-- Attachments Grid End -->
                                
                                
   </s:if>                       
    
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
 <tr>
                <td>
                    
                    <div style="display: none; position: absolute; top:170px;left:320px;overflow:auto;" id="menu-popup">
                        <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" />
                    </div>
                    
                </td>
            </tr>
</table>
<!--//END MAIN TABLE : Table for template Structure-->
<script type="text/javascript">
		$(window).load(function(){
		
		init1('0');
		hideShowRepeat('<%=currentAction%>','<%=repeat%>');

		});
		</script>
</body>
</html>
