<%--
/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :com.mss.mirage.recruitment.consultantresume
 *
 * Date    :  October 17, 2007, 10:58 PM
 *
 * Author  : Kondaiah Adapa<kadapa@miraclesoft.com>
 *
 * File    : ConsultantResume.jsp
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
 --%>

<%@page contentType="text/html;charset=UTF-8" errorPage="../../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.freeware.gridtag.*" %> 
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="com.mss.mirage.util.*"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ page import="java.sql.*,java.lang.*,java.util.*" %>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd" %>

<html>
<head>
    
    <title>Hubble Organization Portal :: Recruitment</title>
    
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/recruitment/ConsultantResumeClientValidation.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
    
</head>

<body class="bodyGeneral">
<%
String objId= request.getSession(false).getAttribute("consultantId").toString();
String consultId;
            String requirementId;
            String requirementAdminFlag;
            
                            consultId = request.getSession(false).getAttribute("consultId").toString();
            requirementId = request.getSession(false).getAttribute("requirementId").toString();
            requirementAdminFlag = request.getSession(false).getAttribute("requirementAdminFlag").toString();
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
        <td width="850px" class="cellBorder" valign="top" style="padding-left:10px;padding-top:5px;">
            <!--//START TABBED PANNEL : -->
            <span class="fieldLabel">Consultant Name :</span>&nbsp;
            <%
            String fname=request.getSession(false).getAttribute("consultantFirstName").toString();
            String lname=request.getSession(false).getAttribute("consultantLastName").toString();
           
           
            
            //request.getSession(false).removeAttribute("consultantFirstName");
            //request.getSession(false).removeAttribute("consultantLastName");
            %>
           <%-- <a class="navigationText" href="<s:url action="consultant/getConsultant"><s:param name="consultantId" value="%{empId}"/></s:url>"><%=fname%>.<%=lname%></a> --%>
              <s:if test="skillBean == null">
                                    <a class="navigationText" href="<s:url action="consultant/getConsultant"><s:param name="consultantId" value="%{empId}"/><s:param name="requirement" value="-1"/></s:url>&consultId=<%=consultId%>&requirementId=<%=requirementId%>&requirementAdminFlag=<%=requirementAdminFlag%>"><s:property value="%{consultantName}"/></a>
                                    
                                </s:if>
                                <s:else>
                                     <a class="navigationText" href="<s:url action="consultant/getConsultant"><s:param name="consultantId" value="%{skillBean.empId}"/><s:param name="requirement" value="-1"/></s:url>&consultId=<%=consultId%>&requirementId=<%=requirementId%>&requirementAdminFlag=<%=requirementAdminFlag%>"><s:property value="%{consultantName}"/></a>
                                    
                                </s:else>
            <%--<s:property value="%{consultantName}"/>--%>
            <ul id="resumeAttachmentDetailsTabs" class="shadetabs" >
                <li ><a href="#" class="selected" rel="resumeAttachmentTab"  > Resume Attachment Details </a></li>                                    
            </ul>
            
            <%-- <sx:tabbedpanel id="resumeAttachmentDetails" cssStyle="width: 840px; height: 310px;padding-left:10px;padding-top:5px;" doLayout="true"> --%>
    
            <!--//START TAB : -->
            <%-- <sx:div id="resumeAttachmentTab"  label="Resume Attachment Details"  > --%>
    
            <div  style="border:1px solid gray; width:830px;height: 500px; overflow:auto; margin-bottom: 1em;">
                
                <s:form name="frmAttach" action="resumeAttachment" theme="simple" method="POST" enctype="multipart/form-data" onsubmit="return checkManadatory();">
                    
                    <div id="resumeAttachmentTab" class="tabcontent"  >
                    
                    <s:hidden name="id" value="" />
                    <input type="hidden" name="objectId" id="objectId" value="<%=request.getSession(false).getAttribute("consultantId").toString()%>" /> 
                    
                    <input type="hidden" name="reqList" value="<%=request.getParameter("reqList").toString()%>"/>
                    
                    <s:hidden name="requestType" value="A"/>
                    <s:hidden name="uploadFileName"  />  
                    <s:hidden name="filepath"  /> 
                    <s:hidden name="date" value="" /> 
                    <table cellpadding="2" cellspacing="0" border="0" width="100%">                                                
                        <tr align="right">
                            <td class="headerText" colspan="6">
                             <% if(request.getParameter("reqList").toString() == "-1"){%>   
                            <a href="<s:url value="consultant/crmBackToList.action"/>" style="align:center;">
                                <img alt="Back to List"
                                     src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                     width="66px" 
                                     height="19px"
                                 border="0" align="bottom"></a>
                                 
                                <%}else {%>
                                   <INPUT type="button" CLASS="buttonBg" value="Back to List" onClick="history.go(-1)">&nbsp;&nbsp;
                                <%}%>
                                 
                            <s:submit cssClass="buttonBg" value="Save"/>
                            
                        </tr>    
                        <tr>
                            <td colspan="6">&nbsp;</td>
                        </tr>
                        <tr>
                            <td colspan="6">&nbsp;</td>
                        </tr>
                        <tr>
                            <td colspan="6">&nbsp;</td>
                        </tr>
                        <tr>
                            <td colspan="6">&nbsp;</td>
                        </tr>
                        <tr>
                            <td colspan="6">&nbsp;</td>
                        </tr>
                        <tr> 
                            <td class="fieldLabel">Attach Resume Name :<font style="color:red;">*</font></td>
                            <td><s:textfield name="attachmentName" cssClass="inputTextarea" id="attachmentName" onchange="attachmentNameValidate();" /></td> 
                            
                            <td class="fieldLabel">Attach Resume :<font style="color:red;">*</font></td>
                            <td colspan="2" ><s:file name="upload" label="File" cssClass="inputTextarea" id="attachmentFileName" onchange="validateFileSize(this);attachmentFileNameValidate();" /></td>                             
                        </tr>
                        
                    </table>
                </s:form>
            </div>
            </div>
            
            <script type="text/javascript">

              var countries=new ddtabcontent("resumeAttachmentDetailsTabs")
              countries.setpersist(false)
              countries.setselectedClassTarget("link") //"link" or "linkparent"
              countries.init()

            </script>
            <%-- </sx:div > --%>
            
            <%-- </sx:tabbedpanel> --%>

        </td>
        <!--//END DATA COLUMN : Coloumn for Screen Content-->
    </tr>
    </table>
</td>
</tr>
<!--//END DATA RECORD : Record for LeftMenu and Body Content-->

<!--//START FOOTER : Record for Footer Background and Content-->
<tr class="footerBg">
    <td align="center">&reg; 2007  Mirage - All Rights Reserved.</td>
</tr>
<!--//END FOOTER : Record for Footer Background and Content-->

</table>
<!--//END MAIN TABLE : Table for template Structure-->
</body>
</html>