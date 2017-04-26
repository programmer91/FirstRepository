<%--
/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :com.mss.mirage.recruitment.consultanttechreviews
 *
 * Date    :  October 16, 2007, 2007, 8:00 PM
 *
 * Author  : Kondaiah Adapa<kadapa@miraclesoft.com>
 *
 * File    : ConsultantSubmittals.jsp
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
 --%>

<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>

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
        <sx:head cache="false"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
        
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <script type="text/JavaScript">
            
            function attachmentNameValidate(){
    
   var attachmentName= document.frmAttach.taskFileName;
   
       
    if (attachmentName.value != null && (attachmentName.value != "")) {
        if(attachmentName.value.replace(/^\s+|\s+$/g,"").length>50){
                       
                 str = new String(document.frmAttach.taskFileName.value);
             document.frmAttach.taskFileName.value=str.substring(0,50);
             
               alert("The FileName must be less than 50 characters");
           
              }
       document.frmAttach.taskFileName.focus();
        return (false);
    }
  
    return (true);
};


function attachmentFileNameValidate(){
    //alert("attachmentFileNameValidate");
   var attachmentFileName= document.frmAttach.attachmentFileName;
   
       
    if (attachmentFileName.value != null && (attachmentFileName.value != "")) {
        if(attachmentFileName.value.length>40){
                       
                 
               document.getElementById('attachmentFileName').value = "";
               alert("The Resume file name must be less than 40 characters.Please rename the resume file name to less than 40 characters.");
           
              }
       document.frmAttach.attachmentFileName.focus();
        return (false);
    }
  
    return (true);
};


function checkManadatory() {
 var attachmentFileName= document.getElementById('attachmentFileName').value;
   if(attachmentFileName.length<=0) {
  alert("Please browse file!");
    document.frmAttach.attachmentFileName.focus();
    return (false);
    }
  else {
 
    return (true);
    }

}


        </script>
        
    </head>
    
    <body class="bodyGeneral">
              
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
                            <%--    <span class="fieldLabel">Task Name :</span>&nbsp;
                             <s:url id="myUrl" action="getTask.action">
                            <s:param name="resM" value="0"/>
                            <s:param name="taskId" value="%{taskId}"/>
                            </s:url>
                           
                                <s:a href="%{myUrl}" cssClass="navigationText"><s:property value="%{title}"/></s:a> --%>
                                
                                <ul id="consultantTechReviewDetailsTabs" class="shadetabs" >
                                    <li ><a href="#" class="selected" rel="consultantTechReviewTab"  > Task Attachments</a></li>                                    
                                </ul>
                                <%-- <sx:tabbedpanel id="consultantTechReviewDetails" cssStyle="width: 840px; height: 310px;padding-left:10px;padding-top:5px;" doLayout="true"> --%>
                                <div  style="border:1px solid gray; width:840px;height: 500px; overflow:auto; margin-bottom: 1em;">   
                                    <!--//START TAB : -->
                                    <%--   <sx:div id="consultantTechReviewTab"  label="Consultant Tech Reviews"  > --%>
                                    <div id="consultantTechReviewTab" class="tabcontent"  >
                                        <s:form name="frmAttach" action="doAddTaskAttachment" theme="simple" method="POST" enctype="multipart/form-data" onsubmit="return checkManadatory();">
                                            <s:hidden name="taskId" value="%{taskId}" />
                                             <s:hidden name="type" value="%{type}"/>
                                              <s:hidden name="projectId" value="%{projectId}"/>
                                           <%-- <input type="hidden" name="reqList" value="<%=request.getParameter("reqList").toString()%>"/>
                                            
                                            <input type="hidden" name="objectId" id="objectId" value="<%=request.getSession(false).getAttribute("consultantId").toString()%>" />  --%>
                                           
                                            <table cellpadding="2" cellspacing="0" border="0" width="100%">                                                
                                                <tr align="right">
                                                    <td class="headerText" colspan="6">
                                                        
                                                       
                                                    <s:submit cssClass="buttonBg" value="Save"/>
                                                    
                                                </tr>    
                                                <tr>
                                                    <td colspan="6">&nbsp;
                                                
                                                 <span class="fieldLabel">Task Name :</span>&nbsp;
                             <s:url id="myUrl" action="getTask.action">
                           
                            <s:param name="taskId" value="%{taskId}"/>
                            <s:param name="type" value="%{type}"/>
                             <s:param name="projectId" value="%{projectId}"/>
                            </s:url>
                             <%--  <a class="navigationText" href="<s:url action="%{myUrl}"></s:url>"><s:property value="%{title}"/></a>  --%>
                                <s:a href="%{myUrl}" cssClass="navigationText"><s:property value="%{title}"/></s:a>
                                                </td>
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
                                                    <td class="fieldLabel">Task File Name :</td>
                                                    <td><s:textfield name="taskFileName" cssClass="inputTextarea"   onchange="attachmentNameValidate(document.frmAttach.taskFileName.value);"/></td> 
                                                    
                                                    <td class="fieldLabel">Task File :</td>
                                                    <td colspan="2" ><s:file name="upload" label="file" cssClass="inputTextarea" id="attachmentFileName" onchange="attachmentFileNameValidate();"/></td> 
                                                    
                                                </tr>
                                            </table>
                                        </s:form>
                                    </div>
                                </div>
                                <script type="text/javascript">

                                                    var countries=new ddtabcontent("consultantTechReviewDetailsTabs")
                                                    countries.setpersist(false)
                                                    countries.setselectedClassTarget("link") //"link" or "linkparent"
                                                    countries.init()

                                </script>
                                <%-- </sx:div >--%>
                                    
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