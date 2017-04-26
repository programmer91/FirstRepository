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
 * File    : AttachmentAddDetails.jsp
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
--%>
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%-- <%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<%@ page import="com.freeware.gridtag.*" %> 
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd" %>

<html>
    <head>
        
        <title>Hubble Organization Portal :: Attachment Adding</title>
         <%-- <sx:head cache="false"/> --%>
        
        <%--This link for ToolTip css --%>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tooltip.css"/>">
        <%--This is End for ToolTip css --%>
         
        <%--This link for ToolTip js --%>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tooltip.js"/>"></script>
        <%--This End for ToolTip js --%>
        
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CrmAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CommonAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/projectsValidations/IssueAttachmentClientValidation.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>        
    </head>
    
    <body>
        
        <%!
        /* Declarations */
        Connection connection;
        String queryString;
        String currentAccountId;
        String strTmp;
        String strSortCol;
        String strSortOrd;
        
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
                    <table class="templateTableLogin" cellpadding="0" cellspacing="0">
                        <tr>
                            
                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                            <td width="150px;" class="leftMenuBgColor" valign="top">
                                <s:include value="/includes/template/LeftMenu.jsp"/>
                            </td>
                            <!--//END DATA COLUMN : Coloumn for LeftMenu-->
                            
                            <!--//START DATA COLUMN : Coloumn for Screen Content-->
                            <td width="850px" class="cellBorder" valign="top">
                                <table cellpadding="0" cellspacing="0" width="100%">
                                    <tr>
                                        <td>
                                         <%--   <s:if test="objectType == 'Project'">
                                                
                                                <s:if test="#session.roleName == 'Employee'">
                                                    <span class="fieldLabel">Account Name :</span>&nbsp;
  <a class="navigationText" href="<s:url action="../employee/pmoActivity"></s:url>"><s:property value="#request.objectName"/></a>
&nbsp;&nbsp;
                                                <span class="fieldLabel"><s:property value="objectTitle"/>Project Name :</span>&nbsp;
 <a class="navigationText" href="<s:url action="../employee/getCustomerProjectDetails"></s:url>?accountId=<s:property value="#request.objectId"/>&projectId=<s:property value="#request.projectId"/>&accountName=<s:property value="#request.accountName"/>&backFlag=<s:property value="#request.backFlag"/>"><s:property value="#request.projectName"/></a>    

                                                </s:if><s:else>
                                                     <span class="fieldLabel">Account Name :</span>&nbsp;
                                                <a class="navigationText" href="<s:url action="../crm/accounts/getAccount"></s:url>?id=<s:property value="#request.objectId"/>"><s:property value="#request.objectName"/></a>
                                                
                                                &nbsp;&nbsp;
                                                <span class="fieldLabel"><s:property value="objectTitle"/>Project Name :</span>&nbsp;
                                                <a class="navigationText" href="<s:url action="getProject"><s:param name="id" value="%{projectId}"/></s:url>"><s:property value="#request.projectName"/></a>    
                                                </s:else>
                                               
                                            </s:if> --%>
                                         <s:if test="objectType == 'Project'">

                                <s:if test="#session.roleName == 'Employee'">
                                <span class="fieldLabel">Account Name :</span>&nbsp;
                                <a class="navigationText" href="<s:url action="../employee/pmoActivity"></s:url>"><s:property value="#request.objectName"/></a>
                                    &nbsp;&nbsp;
                                    <span class="fieldLabel"><s:property value="objectTitle"/>Project Name :</span>&nbsp;
                                <a class="navigationText" href="<s:url action="../employee/getCustomerProjectDetails"></s:url>?accountId=<s:property value="#request.accountId"/>&projectId=<s:property value="#request.projectId"/>&accountName=<s:property value="#request.accountName"/>&backFlag=<s:property value="#request.backFlag"/>"><s:property value="#request.projectName"/></a>    

                            </s:if><s:else>
                                <span class="fieldLabel">Account Name :</span>&nbsp;
                                <a class="navigationText" href="<s:url action="../crm/accounts/getAccount"></s:url>?id=<s:property value="#request.accountId"/>"><s:property value="#request.objectName"/></a>

                                    &nbsp;&nbsp;
                                    <span class="fieldLabel"><s:property value="objectTitle"/>Project Name :</span>&nbsp;
                                <a class="navigationText" href="<s:url action="getProject"><s:param name="id" value="%{projectId}"/></s:url>"><s:property value="#request.projectName"/></a>    
                            </s:else>

                        </s:if>

                                            
                                            <s:elseif test="objectType == 'SubProject'">
                                                <span class="fieldLabel">Account Name :</span>&nbsp;
                                                <label class="navigationText"><s:property value="#request.objectName"/>
                                                </label>                                                
                                                &nbsp;&nbsp;
                                                <span class="fieldLabel">Project Name :</span>&nbsp;
                                                <a class="navigationText" href="<s:url action="getProject"><s:param name="id" value="%{ProjectId}"/></s:url>"><s:property value="#request.projectName"/></a>    
                                                &nbsp;&nbsp;
                                                <span class="fieldLabel">SubProject Name :</span>&nbsp;
                                                <a class="navigationText" href="<s:url action="getSubProject"> 
                                                       <s:param name="subProjectId" value="%{subProjectId}"/>
                                                       <s:param name="projectId" value="%{projectId}"/>
                                                   </s:url>">
                                                <s:property value="#request.subProjectName"/></a>
                                            </s:elseif> 
                                            
                                            <s:elseif test="objectType == 'Vendor'">
                                                
                                                <span class="fieldLabel"><s:property value="objectTitle"/><s:if test="#session.roleName == 'Vendor'">Vendor</s:if><s:else>Account</s:else> Name :</span>&nbsp;
                                                <a class="navigationText" href="<s:url action="../crm/accounts/getAccount">
                                                    <s:param name="id" value="%{objectId}"/>
                                                </s:url>">
                                                    <s:property value="#request.objectName"/></a>    
                                            </s:elseif> 
                                            
                                        </td>
                                    </tr>
                                    
                                    <tr>                    
                                        <td>
                                            <s:elseif test="objectType == 'Map'">
                                                <span class="fieldLabel">Account Name :</span>&nbsp;
                                                <label class="navigationText"><s:property value="#request.objectName"/>
                                                </label>                                                
                                                &nbsp;&nbsp;
                                                <span class="fieldLabel">Project Name :</span>&nbsp;
                                                <a class="navigationText" href="<s:url action="getProject"><s:param name="id" value="%{ProjectId}"/></s:url>"><s:property value="#request.projectName"/></a>    
                                                &nbsp;&nbsp;
                                                <span class="fieldLabel">SubProject Name :</span>&nbsp;
                                                <a class="navigationText" href="<s:url action="getSubProject"> 
                                                       <s:param name="subProjectId" value="%{subProjectId}"/>
                                                       <s:param name="projectId" value="%{projectId}"/>
                                                   </s:url>">
                                                <s:property value="#request.subProjectName"/></a>
                                                &nbsp;&nbsp;
                                                <span class="fieldLabel">Map Name :</span>&nbsp;
                                                <a class="navigationText" href="<s:url action="getMap">                                   
                                                       <s:param name="accountId" value="%{objectId}"/>
                                                       <s:param name="projectId" value="%{projectId}"/>
                                                       <s:param name="subProjectId" value="%{subProjectId}"/>
                                                       <s:param name="mapId" value="%{mapId}"/>
                                                   </s:url>"><s:property value="#request.mapName"/></a>    
                                            </s:elseif>
                                            
                                            
                                            
                                            <s:elseif test="objectType == 'Issue'">
                                                <span class="fieldLabel">Account Name :</span>&nbsp;
                                                <label class="navigationText"><s:property value="#request.objectName"/>
                                                </label>                                                
                                                &nbsp;&nbsp;
                                                <span class="fieldLabel">Project Name :</span>&nbsp;
                                                <a class="navigationText" href="<s:url action="getProject"><s:param name="id" value="%{ProjectId}"/></s:url>"><s:property value="#request.projectName"/></a>    
                                                &nbsp;&nbsp;
                                                <span class="fieldLabel">SubProject Name :</span>&nbsp;
                                                <a class="navigationText" href="<s:url action="getSubProject"> 
                                                       <s:param name="subProjectId" value="%{subProjectId}"/>
                                                       <s:param name="projectId" value="%{projectId}"/>
                                                   </s:url>">
                                                <s:property value="#request.subProjectName"/></a>
                                                &nbsp;&nbsp;
                                                <span class="fieldLabel">Map Name :</span>&nbsp;
                                                <a class="navigationText" href="<s:url action="getMap">                                   
                                                       <s:param name="accountId" value="%{objectId}"/>
                                                       <s:param name="projectId" value="%{projectId}"/>
                                                       <s:param name="subProjectId" value="%{subProjectId}"/>
                                                       <s:param name="mapId" value="%{mapId}"/>
                                                   </s:url>"><s:property value="#request.mapName"/></a>    
                                                &nbsp;&nbsp;
                                                <span class="fieldLabel">Issue Name :</span>&nbsp;
                                                <a class="navigationText" href="<s:url action="getIssues">                                     
                                                       <s:param name="accountId" value="%{objectId}"/>
                                                       <s:param name="projectId" value="%{projectId}"/>
                                                       <s:param name="subProjectId" value="%{subProjectId}"/>
                                                       <s:param name="mapId" value="%{mapId}"/>
                                                       <s:param name="issueId" value="%{issueId}"/>
                                                   </s:url>">
                                                <s:property value="#request.issueName"/></a>
                                            </s:elseif>
                                            
                                            
                                        </td>
                                    </tr>
                                    <tr>
                                        <td valign="top" style="padding-left:10px;padding-top:5px;">
                                            
                                            <ul id="attachmentPannel" class="shadetabs">
                                            <li><a href="#" class="selected" rel="attachmentTab">Attachment Details</a></li>                                             
                                            </ul>
                                            
                                            <!--//START TABBED PANNEL : -->
                                            <%-- <sx:tabbedpanel id="attachmentPannel" cssStyle="width: 840px; height: 420px;padding-left:10px;padding-top:5px;" doLayout="true"> --%>
                                            <div  style="border:1px solid gray; width:840px; height: 420px; overflow:auto; margin-bottom: 1em;">
                                                
                                                <!--//START TAB : -->
                                                <%-- <sx:div id="attachmentTab" label="Attachment Details"> --%> 
                                                <div id="attachmentTab" class="tabcontent">
                                                    
                                                    <s:form name="attachmentForm" action="addAttachment" method="POST" enctype="multipart/form-data" theme="simple" onsubmit="return addAttachmentValidation();">
                                                        
                                                        <s:hidden name="objectType" value="%{objectType}"/>
                                                        <s:hidden name="objectId" value="%{objectId}"/>
                                                        
                                                        <table cellpadding="1" cellspacing="1" border="0" width="100%">
                                                            
                                                            <tr align="right">
                                                                <td class="headerText" colspan="6">
                                                                    <% if(request.getAttribute(ApplicationConstants.RESULT_MSG) != null){
                                                                    out.println(request.getAttribute(ApplicationConstants.RESULT_MSG));
                                                                    }%>
                                                                    <s:hidden name="accountId" value="%{accountId}"/>
                                                                    <s:hidden name="projectId" value="%{projectId}"/>
                                                                    <s:hidden name="subProjectId" value="%{subProjectId}"/>
                                                                    <s:hidden name="issueId" value="%{issueId}"/>
                                                                    <s:hidden name="mapId" value="%{mapId}"/>
                                                                    
                                                                    
                                                                    <s:submit cssClass="buttonBg" value="Submit"/>
                                                                    <s:reset cssClass="buttonBg" value="Reset"/>                                                        
                                                                </td>
                                                            </tr>      
                                                            
                                                            
                                                            <s:if test="#session.roleName == 'Vendor'">
                                                                <tr>
                                                                    <td class="fieldLabel">Attachment Title :<FONT color="red"  ><em>*</em></FONT></td>                                                    
                                                                    <td colspan="3"><s:textfield name="attachmentName" cssClass="inputTextarea" onchange="attachmentNameValidate(document.attachmentForm.attachmentName.value);"/></td> 
                                                                </tr>
                                                                
                                                                <tr>
                                                                    <td class="fieldLabel">File:<FONT color="red"  ><em>*</em></FONT></td>
                                                                   <td align="left"><s:file name="upload" label="File" cssClass="inputTextBlueLarge"  id="attachmentFileName" onchange="validateFileSize(this);attachmentFileNameValidate();" theme="simple" />
                                                                        <s:hidden name="attachType"/>
                                                                    </td>
                                                                </tr> 
                                                            </s:if>
                                                            
                                                            <s:else>
                                                                <s:hidden name="backFlag" id="backFlag" value="%{#request.backFlag}"/>
                                                                        <s:hidden name="accountName" id="accountName" value="%{#request.accountName}"/>
                                                                <tr>
                                                                    <td class="fieldLabel">Attachment Name :<FONT color="red"  ><em>*</em></FONT></td>                                                    
                                                                    <td colspan="3"><s:textfield name="attachmentName" cssClass="inputTextarea" onchange="attachmentNameValidate(document.attachmentForm.attachmentName.value);"/></td> 
                                                                </tr>
                                                                
                                                                <tr>
                                                                    <td class="fieldLabel">File:<FONT color="red"  ><em>*</em></FONT></td>
                                                                   <td align="left"><s:file name="upload" label="File" cssClass="inputTextBlueLarge"  id="attachmentFileName" onchange="validateFileSize(this);attachmentFileNameValidate();" theme="simple" /></td>
                                                                </tr> 
                                                                
                                                                <tr>                                                                
                                                                    <td class="fieldLabel">Attachment Type :<FONT color="red"  ><em>*</em></FONT></td>                                                    
                                                                    <td><s:select list="projAttachTypeList" name="attachType" cssClass="inputSelect" headerKey="" headerValue="--Please Select--" /></td>                                                                
                                                                </tr>
                                                                
                                                                <tr>
                                                                    <td class="fieldLabel" align="left">Version:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td><s:textfield name="version" cssClass="inputTextarea" value="" onchange="versionValidate(document.attachmentForm.version.value);"/></td>    
                                                                </tr>
                                                                
                                                                <tr>
                                                                    <td class="fieldLabel">Description:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td colspan="3"><s:textarea rows="3" cols="73" name="description" theme="simple" cssClass="inputTextarea" onchange="descriptionValidate(document.attachmentForm.description.value);"/></td> 
                                                                </tr>
                                                                
                                                            </s:else>
                                                            
                                                            
                                                            
                                                        </table>
                                                        
                                                    </s:form>
                                                    
                                                </div>
                                                <%-- </sx:div> --%>
                                                <!--//END TAB : -->
                                     
                                            </div>    
                                            <%-- </sx:tabbedpanel> --%>
                                            <!--//END TABBED PANNEL : -->
                                            
                                            <script type="text/javascript">

var countries=new ddtabcontent("attachmentPannel")
countries.setpersist(false)
countries.setselectedClassTarget("link") //"link" or "linkparent"
countries.init()

</script>
                                            
                                        </td>
                                    </tr>
                                </table>            
                                
                                
                                <!--//START TABBED PANNEL : --> 
                                
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
                <td align="center"><s:include value="/includes/template/Footer.jsp"/></td>
            </tr>
            <!--//END FOOTER : Record for Footer Background and Content-->
            
        </table>
        <!--//END MAIN TABLE : Table for template Structure-->
     
        
        
    </body>
</html>
