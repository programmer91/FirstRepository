<!-- 
 /* ******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  November 20, 2007, 3:25 PM
 *
 * Author  : Rajanikanth Teppala<rteppala@miraclesoft.com>
 *
 * File    : ConsultantSearch.jsp
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
-->


<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.util.List" %>
<%@ page import="com.mss.mirage.recruitment.*"%>
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
        <title>Hubble Organization Portal :: Consultant Search</title>
        
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ConsultantAjaxUtil.js"/>"></script>                 
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ConsultantSearch.js"/>"></script> 
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>  
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ajax/AjaxPopup.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/LoadConsultantAjax.js"/>"></script> 
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ConsultantResumeSearch.js"/>"></script> 
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ConsultantResumeAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmpStandardClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AjaxDateToolTip.js"/>"></script>
        
        <script type="text/JavaScript">
            function helpPopup(url) {
                newwindow=window.open(url,'name','height=500,width=600');
                if (window.focus) {newwindow.focus()}
                return false;
            }
             
        </script>
        
        <s:include value="/includes/template/headerScript.html"/>        
    </head>
    
    <body class="bodyGeneral" oncontextmenu="return false;">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/wz_tooltip.js"/>"></script>
        <%!        
        String strStartGrid;
        String strEndGrid;
        String searchString;
        String pathName;
        
        int resultCount=0;
        int strIntStartGrid;
        int strIntEndGrid;
        String reviewURL = null;
	
        
        /* Declarations */
        Connection connection;
        String queryString;
        String strTmp;
        String strSortCol = null;
        String strSortOrd  = "ASC";
        String userId;
        String submittedFrom;
        String action;
        
        int role;
        int intSortOrd = 0;
        int intCurr;
        boolean blnSortAsc = true;
        String search = null;
        String url = null;
        
        
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
                            <td width="850px" class="cellBorder" valign="top"  style="padding-left:10px;padding-top:5px;">
                                
                                <ul id="ConsultantSearchTabs" class="shadetabs" >
                                  <% if(request.getParameter("recList")==null)
                                          
                                       {%>
                                          <li ><a href="#" rel="consultantSearchTab"> Consultant Search </a></li>
                                    <li><a href="#" rel="consultantListTab" class="selected"> Consultant List </a></li>
                                       <%}else{%>
                                     <li ><a href="#" rel="consultantSearchTab"> Consultant Search </a></li>
                                 
                                    <%}%>
                                </ul>
                                <%-- <sx:tabbedpanel id="consultantSearch" cssStyle="width: 845px; height: 500px;padding:10px 5px 5px 5px" useSelectedTabCookie="true" doLayout="true"> --%>
                                <%--    <sx:div id="consultantSearchTab" label="Consultant Search" cssStyle="overflow: auto;"> --%>
                                <div  style="border:1px solid gray; width:830px;height: 500px; overflow:auto; margin-bottom: 1em;">
                                    
                                    <div id="consultantListTab" class="tabcontent"  >
                                        <%--<s:if test="%{currentAction != searchConsultant}"> --%>
                                        <%
                                        
                                        
                                                    
                                        /* String Variable for storing current position of records in dbgrid*/
                                        strTmp = request.getParameter("txtCurr");
                                        
                                        intCurr = 1;
                                        
                                        if (strTmp != null)
                                            intCurr = Integer.parseInt(strTmp);
                                        
                                        try{
                                            //Added for Back to List
                                            
                                            if(session.getAttribute("isSearch") != null)
                                                {
                                                search = session.getAttribute("isSearch").toString();
                                                url = "../consultant/getConsultant.action?empId={Id}&requirement=-1&consultId=0&requirementId=0&requirementAdminFlag=&isSearch="+search;
                                            }else{
                                               url = "../consultant/getConsultant.action?empId={Id}&requirement=-1&consultId=0&requirementId=0&requirementAdminFlag=";
                                            }
                                            
                                           
	reviewURL = "../consultant/reviews.action?consultantId={Id}";
                                            //Add End
                                            /* Getting DataSource using Service Locator */
                                            connection=ConnectionProvider.getInstance().getConnection();
                                            
                                            /* Sql query for retrieving resultset from DataBase */
                                            String  queryString  =null;
                                            
                                            if(session.getAttribute(ApplicationConstants.QUERY_STRING)!=null && !"".equals(session.getAttribute(ApplicationConstants.QUERY_STRING)))
                                                {
                                            queryString=session.getAttribute(ApplicationConstants.QUERY_STRING).toString();
                                            }
                                            else
                                            {
                                               queryString=" SELECT CreatedBy,Id,FName,CONCAT(trim(FName),' ', trim(MName),' ',trim(LName))"
                                                       + " as Name,TitleTypeId,Email2,Country,CellPhoneNo,SkillSet,ModifiedBy,ModifiedDate,(CASE WHEN (`tblRecConsultant`.`LastContactDate` = '1950-01-31') THEN '' ELSE DATE_FORMAT(`tblRecConsultant`.`LastContactDate`,'%m-%d-%Y') END) AS `LastContactDate`,CreatedDate,ModifiedDate FROM tblRecConsultant ORDER BY modifiedDate DESC LIMIT 200 ";   
                                            }

                                            
                                          /*   queryStringBuffer.append("SELECT createdBy,Id,FName,CONCAT(TRIM(FName),' ', TRIM(MName),' ',TRIM(LName)) AS NAME,TitleTypeId,Email2,"
                                 + "Country,SkillSet ,CellPhoneNo,CreatedBy,ModifiedBy,LastContactDate,CreatedDate,ModifiedDate FROM tblRecConsultant ");
                    queryStringBuffer.append(" ORDER BY modifiedDate DESC LIMIT 200");*/

                                             /* Specifing Shorting Column*/
                                     

                                        //out.println(queryString);
                                        %>
                                        <form method="post" id="frmDBGrid" name="frmDBGrid" action=""> 
                                            <div id="consultantList" style="display: block" onmouseover="javascript:UnTip();">
                                                <table align="center" cellpadding="0" cellspacing="0" width="100%">
                                                    <tr align="right">
                                                        <td class="headerText">
                                                            <img alt="Home" 
                                                                 src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" 
                                                                 width="100%" 
                                                                 height="13px" 
                                                                 border="0">
                                                        </td>
                                                    </tr>    
                                                    <tr>
                                                        <td>
                                                            <!-- DataGrid for list all activities -->
                                                            <grd:dbgrid id="tblRecConsultant" name="tblRecConsultant" width="100" pageSize="18" 
                                                                        currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                        dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                
                                                                
                                                                <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                               imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"/>
                                                               <%-- <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>"  imageAscending="../../includes/images/DBGrid/ImgAsc.gif" imageDescending="../../includes/images/DBGrid/ImgDesc.gif" />  --%>
                                                                <%--<grd:anchorcolumn dataField="Name"  headerText="Name" linkUrl="../consultant/getConsultant.action?empId={Id}" linkText="{Name}" width="15" />--%>
                                                                <grd:anchorcolumn dataField="Name"  headerText="Name" linkUrl="<%=url%>" linkText="{Name}" width="15" />
                                                                <grd:textcolumn dataField="TitleTypeId" headerText="Title" width="12" />
                                                                <grd:textcolumn dataField="Country" headerText="State" width="4" HAlign="center"/>
                                                                <grd:anchorcolumn dataField="SkillSet" linkUrl="javascript:getSkills('{Id}')" headerText="Skills"
                                                                                  linkText="View"  width="6" HAlign="center"/>
                                                                <grd:textcolumn dataField="CellPhoneNo" headerText="Mobile No" width="10" HAlign="center"/>
                                                                <grd:ajaxpopup dataField="CreatedBy" id="{Id}" linkText="?" maxLength="20" 
                                                                               headerText="Created By" JSFunction="getCreatedDate" width="12" />
                                                                <%-- <grd:anchorcolumn dataField="Id" headerText="Resumes" linkText="List" linkUrl="javascript:getConsultantId('{Id}')" HAlign="center" width="6"/> --%>
                                                               <grd:ajaxpopup dataField="ModifiedBy" id="{Id}" linkText="?" maxLength="20" 
                                                                               headerText="Modified By" JSFunction="getModifiedDate" width="12" />
                                                               <%--LastContactDate--%>
                                                               <grd:textcolumn dataField="LastContactDate" headerText="	LastActivity" width="8" dataFormat="MM-dd-yyyy"/> 
                                                                
                                                              <%-- <grd:anchorcolumn dataField="Email2"  headerText="E-Mail" linkUrl="mailto:{Email2}" linkText="Send Mail"  width="6" />--%>
                                                               
                                                                <grd:imagecolumn  headerText="Email" width="4" HAlign="center" imageSrc="../../includes/images/DBGrid/reminder.jpg"
                                                                                      linkUrl="mailto:{Email2}"
                                                                                      imageBorder="0" 
                                                                                      imageWidth="16" imageHeight="16" alterText="Email"></grd:imagecolumn> 
                                                                
                                                                   <grd:imagecolumn  headerText="DownLoad" 
                                                              width="4"  
                                                              HAlign="center" 
                                                              imageSrc="../../includes/images/download_11x10.jpg" 
                                                              linkUrl="../getAttachment.action?id={Id}&consultantId=Download" 
                                                              imageBorder="0" 
                                                              imageWidth="11" 
                                                              imageHeight="10" 
                                                              alterText="download" /> 
                                                                    <%--<grd:anchorcolumn dataField="SkillSet" linkUrl="<%=reviewURL%>" headerText="Review"
                                                                                  linkText="Review"  width="6" HAlign="center"/> --%>
                                                                
                                                            </grd:dbgrid> 
                                                            
                                                            <!-- these components are DBGrid Specific  -->
                                                           <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                           <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                           <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                                                           <input type="hidden" name="submitFrom" value="dbGrid">
                                                           <s:hidden name="fiName" value="%{fiName}"></s:hidden>
                                                           
                                                           <s:hidden name="skills"  value="%{skills}"></s:hidden>
                                                           <s:hidden name="comments" value="%{comments}"></s:hidden>
                                                           <s:hidden name="email1" value="%{email1}"></s:hidden>
                                                           <s:hidden name="workAuthorization" value="%{workAuthorization}"></s:hidden>
                                                           <s:hidden name="source" value="%{source}"></s:hidden>
                                                           <s:hidden name="assignedTo" value="%{assignedTo}"></s:hidden>
                                                           <s:hidden name="startDate" value="%{startDate}"></s:hidden>
                                                           <s:hidden name="endDate" value="%{endDate}"></s:hidden>
                                                           <s:hidden name="titleTypeId" value="%{titleTypeId}"></s:hidden>
                                                           <s:hidden name="available" value="%{available}"></s:hidden>
                                                           <s:hidden name="locationList" value="%{locationList}"></s:hidden>
                                                           <s:hidden name="workAuthorizationList" value="%{workAuthorizationList}"></s:hidden>
                                                           <s:hidden name="YrsExp" value="%{YrsExp}"></s:hidden>
                                                           <s:hidden name="availableFrom" value="%{availableFrom}"></s:hidden>
                                                           <s:hidden name="availableTo" value="%{availableTo}"></s:hidden>
                                                           <s:hidden name="org" value="%{org}"></s:hidden>
                                                           
                                                            <!-- <input id="txtDBId" TYPE="hidden" NAME="txtDBId" VALUE="0">
                                                        <input id="txtTRId"TYPE="hidden" NAME="txtTRId" VALUE="1">  -->
                                                        </td>
                                                    </tr>
                                                </table>
                                            </div>
                                        </form>
                                        <%
                                        }catch(Exception ex){
                                            out.println(ex.toString());
                                        }finally{
                                            if(connection!= null){
                                                connection.close();
                                                //session.setAttribute(ApplicationConstants.QUERY_STRING,null);
                                            }
                                        }
                                        %>
                                        <%--</s:if>--%>
                                        <%-- </sx:div> --%>
                                    </div>
                                    
                                    
                                    <div id="consultantSearchTab" class="tabcontent"  >
                                        
                                        <s:form action="searchConsultant" name="consultantsearchForm" theme="simple">
                                            <table cellpadding="0" border="0" cellspacing="0" width="100%">     
                                                
                                                <tr align="right">
                                                    <td class="headerText" colspan="6">
                                                        <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                        
                                                    </td>
                                                </tr>
                                                
                                                <tr>
                                                    <td>
                                                        
                                                        <table border="0" width="100%" cellpadding="1" cellspacing="0">
                                                            <tr>
                                                                <td class="fieldLabel"> Name :</td>
                                                                <td><s:textfield name="fiName" id="fiName" cssClass="inputTextBlueLarge" value="%{fiName}"/></td>                                        
                                                                <td class="fieldLabel">Skill Set :</td>
                                                                <td colspan="3"><s:textfield name="skills" id="skillSet" cssClass="inputTextBlueLarge" value="%{skills}"/></td>
                                                            </tr>
                                                            
                                                            <tr>
                                                                
                                                                <td class="fieldLabel">Email :</td>
                                                                <td><s:textfield name="email1" id="email1" value="%{email1}" cssClass="inputTextBlueLarge"/></td>
                                                                
                                                                <td class="fieldLabel">Comments :</td>
                                                                <td colspan="3"><s:textfield name="comments" id="comments" cssClass="inputTextBlueLarge" value="%{comments}"/></td>                                                               
                                                            </tr>
                                                            
                                                            <tr>
                                                                <td class="fieldLabel">State :</td>
                                                                <td>      <%-- headerKey="" headerValue="-- Select --"  --%>                                                              
                                                                  <%--  <s:select headerKey="" headerValue="-- Select --" list="statesList"  name="location" id="location"  cssClass="inputSelectLarge"  value="%{location}" multiple="true" size="4"/>--%>
                                                                  <s:select multiple="true" list="statesList"  name="locationList" id="locationList"  cssClass="inputSelectLarge"  value="%{locationList}" />
                                                                </td>
                                                                <td class="fieldLabel">Work Authorization :</td>
                                                                <td colspan="3">                                                                    
                                                                    <s:select list="{'US Citizen','Green Card','EAD','H1','TN Permit Visa','Need H1B','Student Visa'}" 
                                                                              name="workAuthorizationList" id="workAuthorizationList" value="%{workAuthorizationList}" cssClass="inputSelectLarge" multiple="true" size="4"/>
                                                                </td>                                                                
                                                            </tr>
                                                            
                                                            <tr>
                                                                <td class="fieldLabel">Source :</td>
                                                                <td>
                                                                    <s:textfield name="source" id="source" cssClass="inputTextBlueLarge" value="%{source}"/>
                                                                </td>
                                                                
                                                              <%--  <s:if test="#session.isUserManager == 1 || #session.isUserTeamLead==1">
                                                                    <td class="fieldLabel"> Created By :</td>
                                                                    <td>
                                                                        <s:select headerKey="" headerValue="--Please Select--" list="assignedMembers" name="assignedTo" id="assignedTo" value="%{assignedTo}" cssClass="inputSelectLarge"/>
                                                                    </td>
                                                                </s:if>
                                                                <s:else>
                                                                    <td class="fieldLabel"> Created By :</td>
                                                                    <td>
                                                                        <s:select headerKey="" headerValue="--Please Select--" list="assignedMembers" name="assignedTo" id="assignedTo" value="%{assignedTo}" cssClass="inputSelectLarge" disabled="true"/>
                                                                    </td>    
                                                                </s:else> --%>
                                                              
                                                                 <td class="fieldLabel"> Created By :</td>
                                                                    <td>
                                                                        <s:select headerKey="" headerValue="--Please Select--" list="assignedMembers" name="assignedTo" id="assignedTo" value="%{assignedTo}" cssClass="inputSelectLarge"/>
                                                                 </td>
                                                                
                                                                <%--<td class="fieldLabel">Practice :</td> 
                                                                <td colspan="2">                                                                    
                                                                    <s:select headerKey="-1" headerValue="--Please Select--" list="practiceList" 
                                                                              id="practiceId" name="practiceId" cssClass="inputSelectLarge" onkeydown="return enableEnter(event);"/>
                                                                    
                                                                </td>--%>
                                                                
                                                                <%--<td style="padding-left:15px;"><input type="button" value="Search" class="buttonBg" onclick="load();"/></td>
                                                                <FONT color="red" SIZE="0.5"> (Add * for Efficient Search)</FONT>
                                                                --%>
                                                               <%-- <td style="padding-left:15px;"><input type="submit" value="Search" class="buttonBg"/></td>--%>
                                                            </tr>
                                                            
                                                            <tr>
                                                                <td class="fieldLabel">Years Of Exp :</td>
                                                                <td>
                                                                    <s:textfield name="YrsExp" id="YrsExp" cssClass="inputTextBlueLarge" value="%{YrsExp}"/>
                                                                </td>
                                                                 <td class="fieldLabel"> Availability :
                                                                    
                                                                </td>
                                                                 <td >
                             
                                                     <s:select  headerValue="--Please Select--" cssClass="inputSelectLarge" label="-Please Select-" 
                                                            name="available" 
                                                           headerKey="" id="available"
                                              
                                                      list="{'Yes','No'}" value="%{available}"/>
                               
                                
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td></td>
                                                                <td>
                                                                    <s:textfield cssStyle="display:none;" name="inputRowData" id="inputRowData" cssClass="inputTextBlue" readonly="true"/> 
                                                                </td>
                                                            </tr>
                                                            
                                                           <tr>
                                                                 <td class="fieldLabel">Available From Between:</td>
                                                           </tr>
                                                            <tr>
                                                                <td class="fieldLabel">From Date:</td>
                                                                <td>
                                                                     <s:textfield id="AvailableFrom" name="availableFrom" cssClass="inputTextBlueLarge" value="%{currentConsultant.availableFrom}" onchange="checkDates(this);" />
                                                                       <a href="javascript:cal3.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                          width="20" height="20" border="0"></a>
                                                                </td>
                                                                <td class="fieldLabel">To Date:<FONT color="red" SIZE="0.5"><em>*</em></FONT></td>
                                                                <td>
                                                                    <s:textfield id="availableTo" name="availableTo" cssClass="inputTextBlueLarge" value="%{currentConsultant.availableFrom}" onchange="checkDates(this);"/>
                                                                       <a href="javascript:cal4.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                          width="20" height="20" border="0"></a>
                                                                </td>
                                                                
                                                            </tr>
                                                                                                               
                                                            
                                                            
                                                            
                                                            
                                                            <tr>
                                                            <td class="fieldLabel">Last Updated Between</td></tr><tr>
                                                                <td class="fieldLabel">From Date:</td><td><s:textfield name="startDate" id="startDate" cssClass="inputTextBlueLarge" onchange="validateTimestamp(this);"/>
                                                                    <a href="javascript:cal1.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif"
                                                                         width="20" height="20" border="0"></a>
                                                                </td>
                                                                <td class="fieldLabel">To Date:<FONT color="red" SIZE="0.5"><em>*</em></FONT></td>
                                                                <td>
                                                                    <s:textfield name="endDate"  id="endDate" cssClass="inputTextBlueLarge" onchange="validateTimestamp(this);"/>
                                                                    <a href="javascript:cal2.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif"
                                                                         width="20" height="20" border="0"></a>
                                                                </td>
                                                                
                                                            </tr>
                                                            
                                                            <tr>
                                                                <td class="fieldLabel"> Org:
                                                                    
                                                                </td>
                                                                <td>
                                                                    <s:select cssClass="inputSelectLarge" label="Select Org" 
                                                                                  name="org" 
                                                                                  headerKey="All" id="org"
                                                                                  headerValue="--select--"
                                                                              list="orgMap"/>
                                                                </td>
                                                                
                                                                  <td class="fieldLabel">Job Title :</td>
                                                                 <td>
                                                                     <s:textfield id="titleTypeId" name="titleTypeId" value="%{currentConsultant.titleTypeId}" cssClass="inputTextBlueLarge"/>
                                                                 </td>
                                                                 </tr>
                                                              	 <tr><td class="fieldLabel">Last Activity Between</td></tr><tr>
                                                                <td class="fieldLabel">From Date:</td><td><s:textfield name="activityFromDate" id="activityFromDate" value="%{activityFromDate}" cssClass="inputTextBlueLarge" onchange="validateTimestamp(this);"/>
                                                                    <a href="javascript:cal5.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif"
                                                                         width="20" height="20" border="0"></a>
                                                                </td>
                                                                <td class="fieldLabel">To Date:<FONT color="red" SIZE="0.5"><em>*</em></FONT></td>
                                                                <td>
                                                                    <s:textfield name="activityToDate"  id="activityToDate" value="%{activityToDate}" cssClass="inputTextBlueLarge" onchange="validateTimestamp(this);"/>
                                                                    <a href="javascript:cal6.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif"
                                                                         width="20" height="20" border="0"></a>
                                                                </td>
                                                                
                                                            </tr> 	   
                                                                 <tr>
                                                                <td class="fieldLabel"></td>
                                                                <td class="fieldLabel"></td>
                                                                <td class="fieldLabel"></td>
                                                                <td style="padding-left:3px;"><input type="submit" value="Search" class="buttonBg"/>&nbsp;&nbsp;&nbsp;&nbsp;<s:reset cssClass="buttonBg" value="Reset" onclick="return resetvalues();"/></td>
                                                            </tr>
                                                            
                                                            
                                                        </table>
                                                        
                                                    </td>
                                                </tr>
                                                
                                                <%--<tr>
                                                    <td>
                                                        
                                                        <br>
                                                        <div id="consultantList" style="display: block">
                                                            <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                            <table id="tblUpdate" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='90%' align="center">
                                                                <COLGROUP ALIGN="left" >
                                                                <COL width="3%">
                                                                <COL width="20%">
                                                                <COL width="20%">
                                                                <COL width="15%">
                                                                <COL width="30%">
                                                                <COL width="2%">
                                                            </table>
                                                            <br>
                                                            <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                        </div>
                                                    </td>
                                                </tr>--%>
                                                
                                                <tr>
                                                    <td>
                                                        <br>
                                                        <div id="resumeList" style="display:none" >
                                                            <input type="button" value="Back To List" class="buttonBg" onclick="backToList();" align="left">                                                        
                                                            <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                            <table id="tblResume" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='90%' align="center">
                                                                <COLGROUP ALIGN="left" >
                                                                <COL width="30%">
                                                                <COL width="30%">
                                                                <COL width="30%">
                                                                
                                                            </table>
                                                            <br>
                                                            <!--<center><span id="spnFast1" class="activeFile" style="display: none;"></span></center>-->
                                                        </div>
                                                    </td>
                                                </tr>
                                            </table>
                                            
                                        </s:form>
                                        
                                        
                                        
                                        <script type="text/JavaScript">
                                            var cal1 = new CalendarTime(document.forms['searchConsultant'].elements['startDate']);
				                 cal1.year_scroll = true;
				                   cal1.time_comp = true;

                                           var cal2 = new CalendarTime(document.forms['searchConsultant'].elements['endDate']);
				                 cal2.year_scroll = true;
				                   cal2.time_comp = true;
                                                   
                                            var cal3 = new CalendarTime(document.forms['searchConsultant'].elements['availableFrom']);
				                 cal3.year_scroll = true;
				                 cal3.time_comp = false;  
                                                 
                                            var cal4 = new CalendarTime(document.forms['searchConsultant'].elements['availableTo']);
				                 cal4.year_scroll = true;
				                 cal4.time_comp = false;   
                                                 
                                             var cal5 = new CalendarTime(document.forms['searchConsultant'].elements['activityFromDate']);
				                 cal5.year_scroll = true;
				                 cal5.time_comp = true;  
                                                 
                                            var cal6 = new CalendarTime(document.forms['searchConsultant'].elements['activityToDate']);
				                 cal6.year_scroll = true;
				                 cal6.time_comp = true;        

                                        </script>
                                    </div>
                                    
                                    
                                    
                                    <%-- <sx:div id="resumeSearchTab" label="Resume Search" cssStyle="overflow: auto;"> --%>
                                   
                                </div>
                                
                                <script type="text/javascript">

                                                    var countries=new ddtabcontent("ConsultantSearchTabs")
                                                    countries.setpersist(false)
                                                    countries.setselectedClassTarget("link") //"link" or "linkparent"
                                                    countries.init()

                                </script>
                                
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
                <td align="center"><s:include value="/includes/template/Footer.jsp"/></td>
            </tr>
            <!--//END FOOTER : Record for Footer Background and Content-->
            
        </table>
        <!--//END MAIN TABLE : Table for template Structure-->
        
    </body>
</html>