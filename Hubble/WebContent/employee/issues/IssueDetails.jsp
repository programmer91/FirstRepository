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
 * File    : IssueDetails.jsp
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.`
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
-->
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>

<%@ taglib prefix="s" uri="/struts-tags" %>

<%-- <%@ taglib prefix="sx" uri="/struts-dojo-tags" %>--%>
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
    <title>Hubble Organization Portal :: Employee Issue Details</title>
  <%--  <sx:head cache="true"/> --%>
     <sj:head jqueryui="true"/>
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
    <script type="text/javascript">

function stopRKey(evt) {
  var evt = (evt) ? evt : ((event) ? event : null);
  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null);
  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;}
}

document.onkeypress = stopRKey;

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
<!-- <body class="bodyGeneral" onload="init1();" oncontextmenu="return false;">  -->
<body class="bodyGeneral" oncontextmenu="return false;"> 
<%--<body id="bodyGeneral" class="claro" onload="init1();" oncontextmenu="return false;"> --%>
    
    
   <%-- <%
   // String s=session.getAttribute(ApplicationConstants.ISSUE_TO_TASK).toString();
    //out.print("Nag::"+s);
    %>--%>
<!--//START MAIN TABLE : Table for template Structure-->
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
                   <%-- <% if(request.getAttribute("accessType").equals("Issue")){%>  --%>
                    <li ><a href="#" class="selected" rel="issueTab"  >Task Details </a></li>
                   <%-- <%}else{%>
                    <li ><a href="#" class="selected" rel="issueTab"  >TaskDetails </a></li>
                    <%}%> --%>
                </ul>
                <!--//START TABBED PANNEL : -->
                <div  style="border:1px solid gray; width:845px;height: 500px; overflow:auto; margin-bottom: 1em;">
                <!--//START TAB : -->
                <div id="issueTab" class="tabcontent"  >
                <s:form name="issuesForm" id="issuesForm" action="%{currentAction}" method="POST" enctype="multipart/form-data" theme="simple" onsubmit="return validateIssueForm();">
                <table width="100%" cellpadding="1" cellspacing="1" border="0">
                <tr align="right">
                    <td class="headerText" colspan="4">
                        <s:property value="#request.resultMessage"/>
                       <s:property value="%{resM}"/>
                        <s:if test="%{currentAction == 'addIssue'}">
                            <s:submit label="Submit" value="Save" cssClass="buttonBg" />
                        </s:if>
                        <s:if test="%{(currentAction == 'editIssue' && currentIssue.statusId != 'Closed')}">
                            <s:submit label="Submit" value="Update" cssClass="buttonBg" />
                            <s:if test="%{navTo!=''}">
                                <a href="<s:property value="%{navTo}"/>.action" style="align=center;">
                                            <img alt="Back to List"
                                                 src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                 width="66px" 
                                                 height="19px"
                                             border="0" align="bottom"></a>
                            </s:if>
                            <s:else>
                                 <a href="getIssues.action" style="align=center;">
                                            <img alt="Back to List"
                                                 src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                 width="66px" 
                                                 height="19px"
                                             border="0" align="bottom"></a>
                            </s:else>
                             
                        </s:if>
                       <s:hidden name="iFlag" value="%{currentIssue.iFlag}"/> 
                        <s:hidden name="a" value="%{currentAction}"/>
                       <s:hidden name="id" value="%{currentIssue.id}"/>
                       <s:hidden name="activityId" value="%{currentIssue.activityId}"/>
                        <s:hidden name="strAttachmentId" value="%{currentIssue.attachmentId}"/> 
                        <s:hidden name="navTo" value="%{navTo}"/>
                        <s:hidden name="issueId" value="%{issueId}"/>
                         <s:hidden value="%{currentSearch}"/> 
                         <s:hidden name="submitForm" value="search"/>
                         
                       
                    </td>
                </tr>
              <tr>
                   <%-- <td colspan="4">                                                        
                        <!-- for displaying validation Errors and Action Messages -->
                        <s:actionerror/>
                        <s:actionmessage/>
                        <s:fielderror/>
                    </td>
                </tr>  --%>
                <%--<tr>
                    <td colspan="4" class="headerText">
                        <s:property value="#request.resultMessage || #session.resultMessage"/>

                    </td>
                </tr>--%>
                
                <!-- New Fields -->
              
             <tr>
                    <td class="fieldLabel">Customer Name : <FONT color="red"  ><em>*</em></FONT></td>
                    <td>  <%-- onkeyup="fillCustomer();"   %{customerName || '%'}--%>
                        <s:textfield name="customerName" id="customerName"  cssClass="inputTextBlue"  value="%{currentIssue.customerName}"  theme="simple" onkeyup="fillCustomerInIssue();"/>
                        <div id="validationMessage"></div>
                        <s:hidden name="customerId" value="%{currentIssue.customerId}" id="customerId"/> 
                    </td>
                    <s:if test = "%{currentIssue.iFlag == 0}">
                        <td class="fieldLabel">Project Name : </td>
                        <td>  <%-- onkeyup="fillCustomer();"   %{customerName || '%'}--%>
                            <s:select list="%{projectNamesMap}" 
                                      name="projectName"
                                      id="projectName"
                                      headerKey="" 
                                      headerValue="--PleaseSelect--"
                                      value="%{currentIssue.projectName}"
                                      cssClass="inputSelect"  />
                        </td>
                    </s:if>
                </tr>  
                  <tr>
                
                       <%--<%}%> --%> 
                       <td class="fieldLabel">Task Title : <FONT color="red"  ><em>*</em></FONT></td>
                        <td width="20%"><s:textfield name="issueName" id="issueName"  cssClass="inputTextBlue"  value="%{currentIssue.issueName}"  theme="simple" onchange="fieldLengthValidator(this);"/></td>
                        
                        
                    <td class="fieldLabel">Task Type:</td>
               
                    <td><s:select list="ActivityTypeList" 
                                      name="typeId"
                                      headerKey=""
                                      headerValue="--PleaseSelect--"
                                      value="%{currentIssue.typeId}"
                                  cssClass="inputSelect"  /></td>
     
            </td> 
           </tr> 
                 <tr>
                    <td  class="fieldLabel">Category :</td>
                  <s:if test = "%{currentAction == 'editIssue'}">
               
                                  <td width="15%"><s:select label="Select Department" 
                                      name="categoryId"
                                      id="categoryId"
                                      headerKey=""
                                      headerValue="-- Please Select --"
                                   list="departmentIdList" cssClass="inputSelect" value="%{currentIssue.categoryId}" onchange="getPracticeDataV1();" disabled="false"/>            
                    
                    <%-- <s:hidden name="categoryId" id="categoryId" value="%{currentIssue.categoryId}"/>  --%>
                     </td>
                     </s:if>
                     <s:else>
                     
                                  <td width="15%"><s:select label="Select Department" 
                                      name="categoryId"
                                      id="categoryId"
                                      headerKey=""
                                      headerValue="-- Please Select --"
                                  list="departmentIdList" cssClass="inputSelect" value="%{currentIssue.categoryId}" onchange="getPracticeDataV1();"/></td>             
                    </s:else>  
                                  
                    <td class="fieldLabel"> Sub Category :</td>
                
                                   <td><s:select label="Select Practice Name" 
                                      name="subCategoryId"  id="subCategoryId"
                                      headerKey=""
                                      headerValue="-- Please Select --"
                                  list="practiceIdList" cssClass="inputSelect" value="%{currentIssue.subCategoryId}" /> <%--onchange="getTeamData();" --%></td>  
                </tr>
                
                <tr>
                    <td class="fieldLabel">Severity :</td>
                    <td><s:select list="{'Medium','High','Low'}"
                                      name="severityId"
                                      headerKey="" 
                                      headerValue="--PleaseSelect--"
                                      value="%{currentIssue.severityId}"
                                  cssClass="inputSelect"  /></td>
                    
                    
                    <td class="fieldLabel"> Status :</td>
                    <td><s:select list="{'Created','InProgress','Closed','Not Closed','Assigned','Not Started'}"
                                      name="statusId"
                                      id="statusId"
                                      value="%{currentIssue.statusId}"
                                  cssClass="inputSelect"  /></td>
                </tr>
              <%-- START OF PRIMARY & SEC ASSIGNTO --%>  
               <tr>
                   <%-- start of primary assignto --%>
                  
                   
          <%--    <s:if test="%{navTo=='create'}">  --%>
               <%--  <s:if test="#session.isUserManager == 1 || #session.isUserTeamLead==1 "> --%>
                     <td class="fieldLabel">Primary&nbsp;Assign&nbsp;To :</td>
                     <td ><s:textfield name="assignedToUID" id="assignedToUID" onkeyup="fillEmployee('pre');" cssClass="inputTextBlue" value="%{currentIssue.assignedToUID}" theme="simple" readonly="false"/>
                     <div id="assignEmpValidationMessage"></div>  
                     <s:hidden name="preAssignEmpId" value="%{currentIssue.preAssignEmpId}" id="preAssignEmpId"/> 
                     </td>   
                <%--  </s:if>--%>
           <%--   </s:if>   
              <s:else>
                  <s:if test="#session.isUserManager == 1 || #session.isUserTeamLead==1 "> 
                       <td class="fieldLabel">Primary&nbsp;Assign&nbsp;To :</td>
                       <td ><s:textfield name="assignedToUID" id="assignedToUID" onkeyup="fillEmployee('pre');" cssClass="inputTextBlue" value="%{currentIssue.assignedToUID}" theme="simple" readonly="false"/>
                 <div id="assignEmpValidationMessage"></div>  
                 <s:hidden name="preAssignEmpId" value="%{currentIssue.preAssignEmpId}" id="preAssignEmpId"/> 
             </td>   
                  </s:if>
                  <s:else>
                      <s:if test="%{currentIssue.preAssignEmpId != ''}">
                          <td class="fieldLabel">Primary&nbsp;Assign&nbsp;To : </td>
                          <td class="inputlablegreen">
                              <s:hidden id="preAssignEmpId" name="preAssignEmpId" value="%{currentIssue.preAssignEmpId}" />
                              <s:property value="currentIssue.assignedToUID"/>
                          </td>
                      </s:if>
                </s:else>
              </s:else> --%>
                 
              <%-- end of primary assignto --%>
              <%-- start of sec assignTo --%>
              
           <s:if test="%{navTo=='create'}">
               <%--    <s:if test="#session.isUserManager == 1 || #session.isUserTeamLead==1 "> --%>
                        <td class="fieldLabel">Secondary&nbsp;Assign&nbsp;To :</td>
                              <td ><s:textfield name="postAssignedToUID" id="postAssignedToUID" onkeyup="fillEmployee('post');" cssClass="inputTextBlue" value="%{currentIssue.postAssignedToUID}" theme="simple" readonly="false"/>
                                  <div id="postAssignEmpValidationMessage"></div>  
                                  <s:hidden name="postAssignEmpId" value="%{currentIssue.postAssignEmpId}" id="postAssignEmpId"/>
                              </td> 
                <%--   </s:if>--%>
             </s:if>   
              <s:else>
                    <s:if test="%{currentIssue.postAssignEmpId != '' && currentIssue.iFlag != 1 && currentIssue.iFlag != 2}">
                     <%--  <s:if test = "%{currentIssue.iFlag == 0}">--%>
                           <td class="fieldLabel">Secondary&nbsp;Assign&nbsp;To :</td>
                           <td ><s:textfield name="postAssignedToUID" id="postAssignedToUID" onkeyup="fillEmployee('post');" cssClass="inputTextBlue" value="%{currentIssue.postAssignedToUID}" theme="simple" readonly="false"/>
                               <div id="postAssignEmpValidationMessage"></div>  
                               <s:hidden name="postAssignEmpId" value="%{currentIssue.postAssignEmpId}" id="postAssignEmpId"/>
                           </td>
                     <%--  </s:if>--%>
                   </s:if>
                  <%-- <s:else>
                       <s:if test="%{currentIssue.postAssignEmpId != ''}">
                           <td class="fieldLabel">Secondary&nbsp;Assign&nbsp;To :</td>
                           <td class="inputlablegreen1"><s:property value="currentIssue.postAssignedToUID"/>
                           <s:hidden id="postAssignEmpId" name="postAssignEmpId" value="%{currentIssue.postAssignEmpId}" />
                          </td>
                       </s:if>
                   </s:else>--%>
               </s:else>  
              <%-- end of sec assign to --%>
        </tr> 
        <%-- END OF PRIMARY AND SEC ASSIGNTO --%>
             <tr>
            
            <td class="fieldLabel">Start&nbsp;Date&nbsp;:</td>
            <td>
              <%--  <s:if test="%{currentIssue.Assigned!=''}"> --%>
                    <s:textfield  name="dateAssigned"  value="%{dateAssigned}" cssClass="inputTextBlue"  />
                  <a href="javascript:cal2.popup();">
                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                         width="20" height="20" border="0"></a> 
           
            </td>
                <td class="fieldLabel">Due&nbsp;Date&nbsp;:</td>
                <td colspan="3">
                    
                    
                    <s:textfield name="dateClosed" value="%{dateClosed}"  cssClass="inputTextBlue"/>
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
               <s:if test="%{currentAction!='addIssue'}">  
                       <tr>
                           <td  class="fieldLabel" valign="top">Completed :</td>                                                                  
                           <td  colspan="3">
                               <s:textfield name="perCompleted" id="perCompleted" value="%{currentIssue.perCompleted}" onchange="fieldLengthValidator(this);" onkeypress="return isNumberKey(event)" size="2" cssClass="inputTextBlue2"/>%
                           </td>   
                       </tr>    
                   </s:if>
              
               <tr>
            <td  class="fieldLabel" valign="top">Description :</td>                                                                  
            <td  colspan="3"><s:textarea name="description" id="IssueDescription" rows="2" cols="65" value="%{currentIssue.description}" onchange="fieldLengthValidator(this);" cssClass="inputTextarea" /></td>
        </tr> 
        
         <tr>
            <td  class="fieldLabel" valign="top">Comments :</td>                                                                  
            <td  colspan="3"><s:textarea name="comments" id="IssueComments" rows="10" cols="65" 
                                             value="%{currentIssue.comments}" 
                                         cssClass="inputTextarea" onchange="fieldLengthValidator(this);"/></td>
        </tr>
         <s:if test="#session.isUserManager == 1 || #session.isUserTeamLead==1">
             <s:if test="%{currentAction=='editIssue'}"> 
             <tr>
                <td class="fieldLabel">Resolution :
                </td>
                <td colspan="4">
                <s:textarea name="resolution" id = "resolution" rows="5" cols="65" value="%{currentIssue.resolution}" onchange="fieldLengthValidator(this);" cssClass="inputTextarea"/></td>
            </tr>
            </s:if>
        </s:if>
        
        <tr>
            <s:if test="%{currentAction=='addIssue'}">
            <td class="fieldLabel">Attachments :</td>
            <td colspan="3">
                
                    <s:file name="upload" cssClass="inputTextBlueLargeAccount" theme="simple" size="65"/>
                   
            </td>
            </s:if>
            <s:if test="%{currentIssue.uploadFileName!='No File Attached' && currentAction !='addIssue'}">
               <td class="fieldLabel">Attachments :</td> 
            <td colspan="3"> 
                    <a class="navigationText" href="<s:url action="download">
                           <s:param name="Id" value="%{currentIssue.attachmentId}"/>
                       </s:url>"><s:property value="%{currentIssue.uploadFileName}"/>
                    </a>
            </td>
             </s:if>
        </tr>
       
    </table>
    </s:form>
    
    <!-- this script for After loading Form it will instantiate Calender Objects as you require -->
    
    </div>
    <%--   </sx:div > --%>
    <!--//END TAB : -->
                                                
                                              
    </div>                               
    <%--  </sx:tabbedpanel> --%>
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
        init1();
		});
		</script>
</body>
</html>
