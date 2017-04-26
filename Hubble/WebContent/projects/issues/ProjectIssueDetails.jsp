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
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
-->
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%-- <%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<%@ page import="com.freeware.gridtag.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Hubble Organization Portal :: Project Issue Details</title>
    <%-- <sx:head cache="false"/> --%>
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>   
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>   
    <!-- issues Related JavaScript  -->
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ProjectAjax.js"/>"></script> 
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
    <%--
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ProjectEmployees.js"/>"></script>    
    --%>
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>    
</head>



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
        <!--//START DATA COLUMN : Column for LeftMenu-->
        <td width="150px;" class="leftMenuBgColor" valign="top"> 
            <s:include value="/includes/template/LeftMenu.jsp"/> 
        </td>
        <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                            
        <!--//START DATA COLUMN : Coloumn for Screen Content-->
        <td width="850px" class="cellBorder" valign="top">
            
            <table cellpadding="0" cellspacing="0" width="100%">
                <tr>
                    <td>
                        
                        <s:if test="#session.issueNavigate == 'true'">
                            <span class="fieldLabel">Account Name :</span>&nbsp;
                            <label class="navigationText"><s:property value="#request.accountName"/>
                            </label>&nbsp;&nbsp;
                            <span class="fieldLabel">Project Name :</span>&nbsp;
                            <a href="<s:url action="../getProject">
                                   <s:param name="id" value="%{projectId}"/>
                               </s:url>" class="navigationText"><s:property value="#request.projectName"/>
                            </a>
                            <span class="fieldLabel">SubProject Name :</span>&nbsp;
                            <a href="<s:url action="../getSubProject">
                                   <s:param name="subProjectId" value="%{subProjectId}"/>
                                   <s:param name="projectId" value="%{projectId}"/>
                               </s:url>" class="navigationText"><s:property value="#request.subProjectName"/>
                            </a>
                            
                            <s:if test="mapId != 0">
                                <span class="fieldLabel">Map Name :</span>&nbsp;
                                <a href="<s:url action="../getMap">
                                       <s:param name="mapId" value="%{mapId}"/>
                                       <s:param name="accountId" value="%{accountId}"/>
                                       <s:param name="projectId" value="%{projectId}"/>
                                       <s:param name="subProjectId" value="%{subProjectId}"/>
                                   </s:url>" class="navigationText"><s:property value="#request.mapName"/>
                                </a>
                            </s:if>
                        </s:if>
                        
                        
                    </td>
                </tr>
                <tr>
                    <td valign="top" style="padding-left:10px;padding-top:5px;">
                        
                        <ul id="IssuesPanel" class="shadetabs">
                            <li ><a href="#" class="selected" rel="activityTab">Issue Details</a></li>     
                        </ul>
                        
                        <!--//START TABBED PANNEL : -->
                        <%-- <sx:tabbedpanel id="IssuesPanel" cssStyle="width: 845px; padding:5px 5px;" doLayout="false"> --%>
                        <div  style="border:1px solid gray; width:845px; overflow:auto; margin-bottom: 1em;">
                        
                        <!--//START TAB : -->
                        <%-- <sx:div id="activityTab" label="IssueDetails"> --%>
                        <div id="activityTab" class="tabcontent">
                        
                        <s:form name="issuesForm" action="%{currentAction}" method="POST" enctype="multipart/form-data" theme="simple">
                        <table width="100%" cellpadding="0" cellspacing="1" border="0">
                        <tr align="right">
                            <td class="headerText" colspan="4">
                                
                                <!--for Display of Result message  -->
                                <s:property value="#request.resultMessage"/>
                                <s:submit label="Submit" value="Save" cssClass="buttonBg" />
                                
                                <!-- Hidden components for BusinessFunctionality -->
                                <s:hidden name="issueId" value="%{currentIssue.id}"/>
                                <s:hidden name="navigateTo" value="%{navigateTo}"/>
                                <s:hidden name="id" value="%{currentIssue.id}"/>
                                <s:hidden name="attachmentId" value="%{currentIssue.attachmentId}"/>
                                
                                <s:hidden name="projectId" value="%{currentIssue.projectId}"/>                                
                                <s:hidden name="subProjectId" value="%{currentIssue.subProjectId}"/>
                                <s:hidden name="projectMapId" value="%{currentIssue.projectMapId}"/>                                
                                <s:hidden name="accountId" value="%{currentIssue.accountId}"/>
                                
                                <s:hidden name="mapNameId" value="%{currentIssue.mapNameId}"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4">                                                        
                                <!-- for displaying validation Errors and Action Messages -->
                                <s:actionerror/>
                                <s:actionmessage/>
                                <s:fielderror/>
                            </td>
                        </tr>
                        <tr>
                            <td width="130px"class="fieldLabel">Project :</td>
                            <td><s:select list="projectsList" 
                                              name="categoryId" id="categoryId"
                                              headerKey="" 
                                              headerValue="--PleaseSelect--" 
                                              value="%{currentIssue.categoryId}"
                                          cssClass="inputSelect" onchange="getSubProject();" /></td>
                            <%--
                                  cssClass="inputSelect"   onchange="getCatagory();getSubCatagories(this,this.value);" /></td>
                                  --%>
                            <td class="fieldLabel"> Sub Project :</td>
                            <td><s:select list="subProjectsList" 
                                              name="subCategoryId" id="subCategoryId"
                                              headerKey="" 
                                              headerValue="--PleaseSelect--"
                                              value="%{currentIssue.subCategoryId}"
                                          cssClass="inputSelect" onchange="getProjEmployees();"/></td>
                        </tr>
                        
                        
                        <tr>
                            <td width="130px"class="fieldLabel">Severity :</td>
                            <td><s:select list="{'Medium','High','Low'}"
                                              name="severityId"
                                              headerKey="Low" 
                                              headerValue="Low"
                                              value="%{currentIssue.severityId}"
                                          cssClass="inputSelect"  /></td>
                            
                            
                            <td class="fieldLabel"> Status :</td>
                            <td><s:select list="{'Assigned','InProgress','Closed','Not Closed','Created'}"
                                              name="statusId"
                                              headerKey="Created" 
                                              headerValue="Created"
                                              value="%{currentIssue.statusId}"
                                          cssClass="inputSelect"  /></td>
                        </tr>
                        
                        <tr>
                        <td class="fieldLabel">Assigned To :</td>
                        <td><s:select list="managersList"
                                          name="assignedToUID"
                                          id="assignedToUID"   
                                          headerKey="" 
                                          headerValue="--PleaseSelect--" 
                                          value="%{currentIssue.assignedToUID}"
                                      cssClass="inputSelectLarge"  /></td>
                        
                        <td class="fieldLabel"> Type:</td>
                        <td><s:select list="{'Suggestion','Enhancement','New Development','Bug'}" 
                                          name="typeId"
                                          headerKey=""
                                          headerValue="--Select--"
                                          value="%{currentIssue.typeId}"
                                      cssClass="inputSelect"  /></td>
                    </td>
                </tr>
                
                <tr>
                    
                    <td class="fieldLabel">Date Assigned :</td>
                    <td>
                        <s:if test="%{currentIssue.Assigned!=''}">
                            <s:textfield  name="dateAssigned"  value="%{currentIssue.Assigned}" cssClass="inputTextBlue"  />
                            <a href="javascript:cal2.popup();">
                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                 width="20" height="20" border="0"></a>
                        </s:if>
                        <s:else>
                            <s:textfield name="dateAssigned" value="%{dateWithOutTime}" cssClass="inputTextBlue"/>
                        </s:else>
                    </td>
                    
                    <td class="fieldLabel">Date Closed :</td>
                    <td colspan="3">
                        <s:textfield name="dateClosed" value="%{currentIssue.Closed}"  cssClass="inputTextBlue"/>
                        <a href="javascript:cal3.popup();">
                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                             width="20" height="20" border="0"></a>
                    </td>            
                    <!-- value="%{currentIssue.closed}" value="%{currentIssue.assigned}" -->
                </tr>
                
                <tr>
                    <td  class="fieldLabel" valign="top">Description :</td>                                                                  
                    <td  colspan="3"><s:textfield name="description" value="%{currentIssue.description}" onchange="descriptionValidate(document.issuesForm.description.value);" cssClass="inputTextBlueExtraLarge" /></td>
                </tr>
                
                <tr>
                    <td  class="fieldLabel" valign="top">Comments :</td>                                                                  
                    <td  colspan="3"><s:textarea name="comments" rows="10" cols="80" 
                                                     value="%{currentIssue.comments}" 
                                                 cssClass="inputTextarea" onchange="commentsValidate(document.issuesForm.comments.value);"/></td>
                </tr>
                
                <tr>
                    <td class="fieldLabel">Attachments :</td>
                    <td colspan="2">
                        <s:if test="%{currentAction=='addIssue'}">
                            <s:file name="upload" cssClass="inputTextBlueLargeAccount" theme="simple"/>
                        </s:if>
                        
                    </td>
                    <td> 
                        
                        <s:if test="%{currentIssue.uploadFileName!=''}">
                            <a class="navigationText" 
                               href="<s:url action="download">
                                   <s:param name="Id" value="%{currentIssue.attachmentId}"/>
                               </s:url>"><s:property value="%{currentIssue.uploadFileName}"/>
                            </a>
                        </s:if>
                        
                    </td>
                </tr>
                
                <s:if test="#request.accessType=='Task'">            
                    <tr>
                        <td class="fieldLabel">Resolution :
                        </td>
                        <td colspan="4">
                        <s:textarea name="resolution" rows="5" cols="80" value="%{currentIssue.resolution}" cssClass="inputTextarea"/></td>
                    </tr>
                </s:if>
                
                <s:elseif test="#request.accessType=='Issue'">
                    <s:if test="%{currentIssue.statusId=='Closed'}">
                        <tr>
                            <td class="fieldLabel">Resolution : 
                            </td>
                            <td colspan="4">
                            <s:textarea name="resolution" rows="5" cols="80" value="%{currentIssue.resolution}" cssClass="inputTextarea"/></td>
                        </tr>
                    </s:if>
                </s:elseif>
                
            </table>
            </s:form>
            
            <!-- this script for After loading Form it will instantiate Calender Objects as you require -->
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
            </div>
            </div>
            
            <script type="text/javascript">

var countries=new ddtabcontent("IssuesPanel")
countries.setpersist(false)
countries.setselectedClassTarget("link") //"link" or "linkparent"
countries.init()

</script>
            
            <%--
            </sx:div >
            <!--//END TAB : -->
            </sx:tabbedpanel>
            <!--//END TABBED PANNEL : --> --%>
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
    <td align="center"><s:include value="/includes/template/Footer.jsp"/>   </td>
</tr>
<!--//END FOOTER : Record for Footer Background and Content-->

</table>
<!--//END MAIN TABLE : Table for template Structure-->

</body>
</html>