<!--
 *******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  December 15, 2015, 3:25 PM
 *
 * Author  :  Anand
 *
 * File    : ProjectRisks.jsp
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



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Hubble Organization Portal :: Project Risk</title>
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
   
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmpStandardClientValidations.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
    <script type="text/javascript" src="<s:url value="/includes/javascripts/IssueFill.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/projectsValidations/ProjectClientValidation.js"/>"></script> 
    <%--<script type="text/JavaScript" src="<s:url value="/includes/javascripts/fillPMOList.js"/>"></script> --%>

  


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


%>
<%



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
        String currentProjectId;
        String strTmp;
        String strSortCol;
        String strSortOrd;

        int intSortOrd = 0;
        int intCurr;
        boolean blnSortAsc = true;
        %>
<!-- <body class="bodyGeneral" onload="init1('0');" oncontextmenu="return false;"> -->
<body class="bodyGeneral" oncontextmenu="return false;">

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
               
                  <span class="fieldLabel">Account Name :</span>&nbsp;
                                       <%
                userRoleName = session.getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
                //System.out.println("1");
                if (("Employee".equalsIgnoreCase(userRoleName))){
                if(request.getParameter("accountId") != null){
                    currentAccountId = (String)request.getParameter("accountId");
                    }
                    
                    if(request.getParameter("projectId") != null){
                    currentProjectId = (String)request.getParameter("projectId");
                    }
                
                               }
                //System.out.println("2");
                //System.out.println(request.getParameter("accountId"));
                //System.out.println(currentProjectId);
                %> 
                <%
                if (("Employee".equalsIgnoreCase(userRoleName))){
                %>
                <a class="navigationText" href="<s:url action="../employee/pmoActivity"></s:url>"><s:property value="#request.accountName"/></a>
                                
                                &nbsp;&nbsp;
                                <span class="fieldLabel">Project Name :</span>&nbsp;
                                                       <a class="navigationText" href="<s:url action="../employee/getCustomerProjectDetails"></s:url>?accountId=<s:property value="#request.accountId"/>&projectId=<s:property value="#request.projectId"/>&accountName=<s:property value="#request.accountName"/>&backFlag=<s:property value="#request.backFlag"/>"><s:property value="#request.projectName"/></a>    
                                            
                  <% }else{%>
                     <a href="<s:url action="../crm/accounts/getAccount">
                                                   <s:param name="id" value="%{accountId}"/>
                                               </s:url>" class="navigationText"><s:property value="#request.accountName"/>
                                            </a>&nbsp;&nbsp;
                                            <span class="fieldLabel">Project Name :</span>&nbsp;
                                            <a href="<s:url action="getProject">
                                                   <s:param name="id" value="%{projectId}"/>
                                               </s:url>" class="navigationText"><s:property value="#request.projectName"/>
                                            </a>
                  <% } %>
                
                <ul id="accountTabs" class="shadetabs" >
                    <s:if test="%{currentAction=='doAddProjectRisk'}">
                        <li ><a href="#" class="selected" rel="issueTab"  >Add Project Risk </a></li>
                    </s:if>
                    <s:else>
                         <li ><a href="#" class="selected" rel="issueTab"  >Edit Project Risk </a></li>
                    </s:else>

                </ul>
                <!--//START TABBED PANNEL : -->
               <div  style="border:1px solid gray; width:845px;height: 400px; overflow:auto; margin-bottom: 1em;">
                <!--//START TAB : -->
 
                     <div id="issueTab" class="tabcontent" style="width: 77%;" >
                         <%
                         if(request.getParameter("resultMessage")!=null){
                             out.print(request.getParameter("resultMessage"));
                         }
                         %>
                    <s:form name="riskForm" id="riskForm" action="%{currentAction}" method="POST"  theme="simple" onsubmit="return validateProjectRisk();">
                        <table width="100%" cellpadding="2" cellspacing="2" border="0" >
                            <tr align="right">
                                <td class="headerTextForDualReportsToProjectAdd" colspan="4" >
                                  
                                    <s:hidden name="accountId" value="%{accountId}" id="accountId"/>
                                    <s:hidden name="id" value="%{id}"/>
                                    <s:hidden name="projectId" value="%{projectId}" id="projectId"/>
                                    <s:hidden value="%{currentAction}"/>
                                    <s:hidden name="temp" id="temp" value="%{temp}"/>
                                    <input type="hidden" name="backFlag" id="backFlag" value="<%=request.getParameter("backFlag")%>"/>

                                    <%
                            if(session.getAttribute("resultMsg") != null) {
                                out.println(session.getAttribute("resultMsg"));
                            session.removeAttribute("resultMsg");
                                    }
                                    %>
                                      
                                     
                                         				
                                </td>
                            </tr>
                           
                            <tr>

                             
                                <tr>
                                    <%--<td class="fieldLabel" valign="top">Resource Type : <FONT color="red" ><em>*</em></FONT></td>
                                    <td  colspan="4" class="fieldLabelLeft"><s:select list="{'Employee','Consultant','Customer/Vendor Contact'}"
                                                  name="severityId"
                                                    value="%{currentTask.severityId}"
                                                    cssClass="inputSelect"  /></td>
                                    <td class="fieldLabel">Resource Title :</td> --%>
                                     <td width="200px" class="fieldLabel">Description: <FONT color="red"  ><em>*</em></FONT></td>
                                     <td colspan="6"><s:textarea cols="74" rows="2" name="description"  cssClass="inputTextarea" onchange="fieldLengthValidatorforProjectRisk(this);"  id="description"/></td>                                                    
                                </tr>
                                <tr>
                                     <td class="fieldLabel">Assigned&nbsp;to&nbsp;:&nbsp;<FONT color="red"  ><em>*</em></FONT></td>
                                     <td ><s:select list="riskAssignedTo" headerKey="-1" headerValue="--Please Select--"
                                                  name="assignedTo"
                                                  id="assignedTo"
                                                    value="%{assignedTo}"
                                                    cssClass="inputSelect"  />  </td>
                                     
                                </td> 
 <td class="fieldLabel">Impact&nbsp;:&nbsp;<FONT color="red"  ><em>*</em></FONT></td>
                                     <td ><s:select list="{'Show Stopper','Impact Timeline','Might Impact','Wont Impact'}" headerKey="-1" headerValue="--Please Select--"
                                                  name="impact"
                                                  id="impact"
                                                    value="%{impact}"
                                                cssClass="inputSelect"  /> 
                                     
                                </tr>
                               
                                
                                
                            
                            
                            <tr>
                                 <td class="fieldLabel" >Closed&nbsp;Date&nbsp;:</td>
                                <td>

                                    <s:textfield name="closedDate" id="closedDate" value="%{closedDate}" cssClass="inputTextBlue"  />
                                    <a href="javascript:cal2.popup();"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif"
                                         width="20" height="20" border="0"></a>
                                </td>
                                   <td class="fieldLabel">Status :<FONT color="red"  ><em>*</em></FONT></td>
                                <td><s:select list="{'Active','Closed'}"
                                                  name="status"
                                                    value="%{status}"
                                                cssClass="inputSelect"  />  </td>
                                <td>
                            </tr>
                                           
                                     <tr>
                                    
                                     
                                    
                                     <td class="fieldLabel">Resolution&nbsp;:</td>
                                     <td colspan="3"><s:textarea cols="74" rows="2" name="resolution" id="resolution" value="%{resolution}" cssClass="inputTextarea" onchange="fieldLengthValidatorforProjectRisk(this);"/>  </td>
                                
                                </td>
                                

                                </tr>  
                                <tr align="right">
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td>
                                        <s:if test="%{currentAction == 'doAddProjectRisk'}">
                                        
                                        <s:submit label="Submit" value="Save" cssClass="buttonBg" />
                                    </s:if>
                                    <s:if test="%{currentAction == 'doEditProjectRisk'}">
                                        
                                        <s:submit label="Submit" value="Update" cssClass="buttonBg" />
                                          <!--  <a href="getProject.action?id=" style="align:center;">
                                                <img alt="Back to List"
                                                     src=" <%-- <s:url value="/includes/images/backToList_66x19.gif"/>" --%>
                                                     width="66px"
                                                     height="19px"
                                                 border="0" align="bottom"></a> -->
                                    </s:if> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </td>
                                </tr>
                            
                                
                             
                                
                                    
                                    
                        </table>
                    </s:form>
					
                    <!-- this script for After loading Form it will instantiate Calender Objects as you require -->

                </div>
    </div>
    <script type="text/JavaScript">
                                                         /*  var cal1 = new CalendarTime(document.forms['issuesForm'].elements['dateCreated']);
cal1.year_scroll = true;
cal1.time_comp = true;

                                                           var cal2 = new CalendarTime(document.forms['issuesForm'].elements['dateAssigned']);
cal2.year_scroll = true;
cal2.time_comp = false;

                                                           var cal3 = new CalendarTime(document.forms['issuesForm'].elements['dateClosed']);
cal3.year_scroll = true;
cal3.time_comp = false;
*/

  var cal2 = new CalendarTime(document.forms['riskForm'].elements['closedDate']);
                                                           cal2.year_scroll = true;
                                                           cal2.time_comp = false;
                                                          
                                </script>
    <script type="text/javascript">

var countries=new ddtabcontent("accountTabs")
countries.setpersist(false)
countries.setselectedClassTarget("link") //"link" or "linkparent"
countries.init()

                                </script>
                                 <!-- Attachments Grid Start -->
  

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
		});
		</script>
</body>
</html>
