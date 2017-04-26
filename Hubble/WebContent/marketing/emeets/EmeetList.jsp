<%-- 
    Document   : EmeetList
    Created on : Jan 30, 2016, 12:12:22 AM
    Author     : miracle
--%>

 <%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.lang.String"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
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

        <title>Hubble Organization Portal :: Executive Meets</title>
       
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tooltip.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/helpText.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tooltip.js"/>"></script>   
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">

        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>   
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>   
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/javascripts/searchIssue.js"/>"></script>

        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>

        <script type="text/JavaScript" src='<s:url value="/includes/javascripts/GridNavigation.js"/>'></script>

        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/jquery.min.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/jquery.js"/>"></script>   
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/StandardClientValidations.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/ajaxfileupload.js"/>"></script>  
        <%-- for issue reminder popup --%>

        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
      
        <link rel="stylesheet" href="/resources/demos/style.css">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/marketing/EmeetScripts.js"/>"></script> 
        <style>
            .round-button {
                display:inline-block;
                width:20px;
                height:20px;
                line-height:20px;
                border: 0px solid #f5f5f5;
                border-radius: 50%;
                color:#f5f5f5;
                text-align:center;
                text-decoration:none;
                background: #4679BD;
                box-shadow: 0 0 3px gray;
                font-size:8px;
                font-weight:bold;
                cursor: pointer;
            }
            .round-button:hover {
                background:#30588e;
            }
        </style>
        



        <s:include value="/includes/template/headerScript.html"/>
    </head>
    <body class="bodyGeneral" oncontextmenu="return false;">
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

        <%String contextPath = request.getContextPath();
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
                            <td width="850px" class="cellBorder" valign="top" style="padding:5px 5px ;">
                                <!--//START TABBED PANNEL : -->
                                <%
                                    //out.print("naga::"+ request.getAttribute("subnavigateTo").toString());
                                    /// session.setAttribute(ApplicationConstants.ISSUE_TO_TASK,request.getAttribute("subnavigateTo").toString());
                                %>

                                <ul id="accountTabs" class="shadetabs" >

                                    <%--    <% if(request.getParameter("issueList")==null)
                                           {%> --%>

                                    <li ><a href="#" class="selected" rel="issuesTab">Executive Meets</a></li>

                                    <%--   <%}else{%>
                                       <li ><a href="#" class="selected" rel="IssuesSearchTab">Tasks Search</a></li>
                                        <% }%> --%>

                                </ul>


                                <%-- <sx:tabbedpanel id="IssuesPanel" cssStyle="width: 845px; height: 500px;padding:5px 5px;" doLayout="true"> --%>
                                <div  style="border:1px solid gray; width:845px;height: 500px; overflow:auto; margin-bottom: 1em;">

                                    <div id="issuesTab" class="tabcontent" >
                                         <%if(request.getAttribute("emeetStatusByDate")!=null&&!request.getAttribute("emeetStatusByDate").toString().equals("U")){%>
                                        <!-- completed emeet details start-->
                                        <div id="exeMeetoverlay" ></div> 
                                        <div id="exeMeetspecialBox" >
                                            <s:form theme="simple"  align="center" name="frmExeMeet" id="frmExeMeet" >

                                                <s:hidden name="exeMeetingId" id="exeMeetingId"/>

                                                <s:hidden name="id" id="id"/>
                                                <%--  <s:hidden name="teamId" id="teamId" value="%{#session.teamName}"/> --%>


                                                <table align="center" border="0" cellspacing="0" style="width:100%;" >
                                                    <tr>                               
                                                        <td colspan="2" style="background-color: #288AD1" >
                                                            <h3 style="color:darkblue;" align="left">
                                                                <span id="headerLabel"></span>


                                                            </h3></td>
                                                        <td colspan="2" style="background-color: #288AD1" align="right">

                                                            <a href="#" onmousedown="toggleOverlayForExeMeet()" >
                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> 

                                                            </a>  

                                                        </td></tr>
                                                    <tr>
                                                        <td colspan="">
                                                            <div id="load" style="color: green;display: none;">Loading..</div>
                                                            <div id="resultMessage"></div>
                                                        </td>
                                                    </tr>    
                                                    <tr><td colspan="">
                                                            <table style="width:100%;" align="center" border="0">
                                                               <tr style="display: none" id="emeetUrlTr">
                                                                   <td  class="fieldLabel">After&nbsp;Page&nbsp;Link&nbsp;: </td>
                                                                   <td colspan="3"><span id="emeetLink"></span></td>
                                                               </tr>
                                                                <tr>
                                                                    <td align="left" class="fieldLabel" >Type:</td>
                                                                     <td><FONT color="green"  ><span id="executiveMeetingType"></span></FONT></td>
                                                                    <td  class="fieldLabel" >Month:</td>
                                                                     <td><FONT color="green"  ><span id="executiveMeetMonth"></span></FONT></td>
                                                                </tr>
                                                                <tr>
                                                                    <td class="fieldLabel">Date:</td>
                                                                     <td><FONT color="green"  ><span id="exeMeetcreatedDateTo"></span></FONT></td>
                                                                    
                                                                    <td align="left" class="fieldLabel" >Status:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td id="statusId">
                                                                        <%--  <s:textfield name="jobDepartment" id="jobDepartment" cssClass="inputTextBlue" onchange="return fieldLengthValidator2(this);"/> --%>
                                                                        <s:select list="{'Active','Completed','InActive','Executed','Published'}" name="executiveMeetingStatus" id="executiveMeetingStatus" cssClass="inputSelect"/>
                                                                        
                                                                    </td> 
                                                                    
                                                                </tr>
                                                                <s:hidden name="exeMeetcreatedDateTo" />
                                                                <tr>
                                                                    <td class="fieldLabel" >Start&nbsp;Time:</td>
                                                                    <td colspan="3">
                                                                        <FONT color="green"  ><span id="exeMeetStartTime"></span>
                                                                            <span id="exeMeetStartmidDayFrom"></span></FONT>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                            <label class="fieldLabel">End&nbsp;Time:</label>
                                                                            <FONT color="green"  ><span id="exeMeetEndTime"></span>
                                                                            <span id="exeMeetEndmidDayTo"></span></FONT>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                             <label class="fieldLabel">End&nbsp;Time:</label>
                                                                          <FONT color="green"  >  <span id="timeZone"></span></FONT>
                                                                            
                                                                        
                                                                    </td>

                                                                </tr>
                                                               
                                                                <tr>
                                                                    <td class="fieldLabel" >Registration&nbsp;Link&nbsp;: </td>
                                                                    <td colspan="3"><FONT color="green"  ><span id="registrationLinkForEMeet"></span></FONT></td>
                                                                </tr>
                                                                <tr >


                                                                    <td class="fieldLabel" >Video&nbsp;Link&nbsp;: </td>
                                                                    <td colspan="3"><s:textfield name="videoLinkForEMeet" id="videoLinkForEMeet" style="width : 490px;" cssClass="inputTextBlueLargeAccount" onchange="isUrl(this);return fieldLengthValidatorr(this);" /></td>
                                                                    <%--    <td align="left" class="fieldLabel" >Job&nbsp;Tagline:<FONT color="red"  ><em>*</em></FONT></td>
                                                                      <td ><s:textfield name="jobtagline" id="jobtagline" cssClass="inputTextBlue" onchange="return fieldLengthValidator2(this);" />
                                                                          </td> --%>
                                                                </tr>
                                                                <tr id="createdTr">
                                                                    <td class="fieldLabel" valign="top">CreatedBy: </td>
                                                                    <td><FONT color="green"  ><span id="createdBy"></span></FONT></td>
                                                                    <td class="fieldLabel" valign="top">CreatedDate: </td>
                                                                    <td><FONT color="green"  ><span id="createdDate"></span></FONT></td>
                                                                </tr>
                                                                <tr id="addTr" style="display: none"> 
                                                                    <td></td><td></td><td></td>
                                                                    <%--   <td class="fieldLabel">Job&nbsp;Logo:</td>
                                                                     <td colspan="2" ><s:file name="file" label="file" cssClass="inputTextarea" id="file" onchange="attachmentFileNameValidate();"/></td>  --%>
                                                                    <td  align="center" >
                                                                        <input type="button" value="Save" onclick="return doAddExecutiveMeeting();" class="buttonBg" id="addButton"/> 


                                                                        <%-- <s:submit cssClass="buttonBg"  align="right" id="save" value="Save" /> --%>
                                                                        <s:reset cssClass="buttonBg"  align="right" value="Reset" />

                                                                    </td>
                                                                </tr> <tr id="editTr" style="display: none"> 

                                                                    <td  align="center" colspan="4"style="text-align: right;padding-right: 50px;">
                                                                        <input type="button" value="Update" onclick="return doUpdateCompletedExeMeeting();" class="buttonBg" id="updateButton"/> &nbsp;&nbsp;
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </s:form>    
                                        </div>

                                        <!-- completed emeet details end -->
                                        <%} else {%>
                                        
                                        
                                        
                                        
                                        <div id="exeMeetoverlay" ></div> 
                                        <div id="exeMeetspecialBox" >
                                            <s:form theme="simple"  align="center" name="frmExeMeet" id="frmExeMeet" >

                                                <s:hidden name="exeMeetingId" id="exeMeetingId"/>

                                                <s:hidden name="id" id="id"/>
                                                <%--  <s:hidden name="teamId" id="teamId" value="%{#session.teamName}"/> --%>


                                                <table align="center" border="0" cellspacing="0" style="width:100%;" >
                                                    <tr>                               
                                                        <td colspan="2" style="background-color: #288AD1" >
                                                            <h3 style="color:darkblue;" align="left">
                                                                <span id="headerLabel"></span>


                                                            </h3></td>
                                                        <td colspan="2" style="background-color: #288AD1" align="right">

                                                            <a href="#" onmousedown="toggleOverlayForExeMeet()" >
                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> 

                                                            </a>  

                                                        </td></tr>
                                                    <tr>
                                                        <td colspan="">
                                                            <div id="load" style="color: green;display: none;">Loading..</div>
                                                            <div id="resultMessage"></div>
                                                        </td>
                                                    </tr>    
                                                    <tr><td colspan="">
                                                            <table style="width:100%;" align="center" border="0">
                                                                <tr style="display: none" id="emeetUrlTr">
                                                                   <td  class="fieldLabel">Redirection&nbsp;Link&nbsp;:</td>
                                                                   <td colspan="3"><span id="emeetLink"></span></td>
                                                               </tr>
                                                                <tr>
                                                                    <td align="left" class="fieldLabel" >Type:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td >

                                                                        <%--  <s:textfield name="jobDepartment" id="jobDepartment" cssClass="inputTextBlue" onchange="return fieldLengthValidator2(this);"/> --%>
                                                                        <s:select headerKey="-1" headerValue="--Please Select--" list="{'Global Practice Meet','Global Sales Meet'}" name="executiveMeetingType" id="executiveMeetingType" cssClass="inputSelect"/>
                                                                    </td> 
                                                                    <td  class="fieldLabel" >Month:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td>
                                                                        <%-- <s:textfield name="jobHireType" id="jobHireType" cssClass="inputTextBlue" onchange="return fieldLengthValidator2(this);"/> --%>
                                                                        <s:select headerKey="-1" headerValue="--Please Select--" list="{'January','February','March','April','May','June','July','August','September','October','November','December'}" name="executiveMeetMonth" id="executiveMeetMonth" cssClass="inputSelect"/>
                                                                    </td> 
                                                                </tr>
                                                                <tr>
                                                                    <td class="fieldLabel">Date:<FONT color="red" SIZE="0.5"><em>*</em></FONT></td>
                                                                    <td>
                                                                        <s:textfield name="exeMeetcreatedDateTo"  id="exeMeetcreatedDateTo" cssClass="inputTextBlue" onchange="validateTimestamp(this);" readonly="true"/>
                                                                        <a href="javascript:cal3.popup();">
                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif"
                                                                                 width="20" height="20" border="0"></a>
                                                                    </td>
                                                                    <td align="left" class="fieldLabel" >Status:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td  id="statusId">
                                                                        <%--  <s:textfield name="jobDepartment" id="jobDepartment" cssClass="inputTextBlue" onchange="return fieldLengthValidator2(this);"/> --%>
                                                                        <s:select list="{'Active','InActive'}" name="executiveMeetingStatus" id="executiveMeetingStatus" cssClass="inputSelect"/>
                                                                        
                                                                    </td> 
                                                                    <td style="display: none;" id="publishedId"><FONT color="green"  ><span id="publishedStatus"></span></FONT></td>
                                                                </tr>
                                                                <tr>
                                                                    <td class="fieldLabel" >Start&nbsp;Time:</td>
                                                                    <td colspan="3"><s:textfield name="exeMeetStartTime" id="exeMeetStartTime" maxLength="5" placeholder="HH:MM" Class="inputSelectSmall" onchange="timeValidator(this);" onkeyup="enterdTime(this);"/>
                                                                        <s:select id="exeMeetStartmidDayFrom"  name="exeMeetStartmidDayFrom"  list="{'AM','PM'}" cssClass="inputSelectSmall"  />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label class="fieldLabel">End&nbsp;Time:</label>
                                                                        <s:textfield name="exeMeetEndTime" id="exeMeetEndTime" maxLength="5" placeholder="HH:MM" Class="inputSelectSmall" onchange="timeValidator(this);" onkeyup="enterdTime(this);"/>
                                                                        <s:select id="exeMeetEndmidDayTo"  name="exeMeetEndmidDayTo"  list="{'AM','PM'}" cssClass="inputSelectSmall" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label class="fieldLabel">TimeZone:</label>
                                                                        <s:select id="timeZone" name="timeZone"  list="{'EST','IST','MST','CST','PST'}" cssClass="inputSelectSmall" />
                                                                    </td>

                                                                </tr>

                                                              <!--  <tr>
                                                                    <td class="fieldLabel" valign="top">Registration&nbsp;Text&nbsp;for&nbsp;Web&nbsp;Page:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td colspan="3" valign="top"><%--<s:textarea rows="5" cols="65" style="width : 450px;" name="registrationTextforExeMeet" cssClass="inputTextareaOverlay1" onchange="return fieldLengthValidatorr(this);" id="registrationTextforExeMeet"/>--%></td>
                                                                </tr> -->
                                                                <tr>


                                                                    <td class="fieldLabel" >Registration&nbsp;Link&nbsp;:<FONT color="red"  ><em>*</em></FONT> </td>
                                                                    <td colspan="3"><s:textfield name="registrationLinkForEMeet" id="registrationLinkForEMeet" style="width : 484px;" cssClass="inputTextBlueLargeAccount" onchange="isUrl(this);return fieldLengthValidatorr(this);" /></td>
                                                                    <%--    <td align="left" class="fieldLabel" >Job&nbsp;Tagline:<FONT color="red"  ><em>*</em></FONT></td>
                                                                      <td ><s:textfield name="jobtagline" id="jobtagline" cssClass="inputTextBlue" onchange="return fieldLengthValidator2(this);" />
                                                                          </td> --%>
                                                                </tr>
                                                                <tr id="createdTr">
                                                                    <td class="fieldLabel" valign="top">CreatedBy:<FONT color="red"  ><em>*</em></FONT> </td>
                                                                    <td><FONT color="green"  ><span id="createdBy"></span></FONT></td>
                                                                    <td class="fieldLabel" valign="top">CreatedDate:<FONT color="red"  ><em>*</em></FONT> </td>
                                                                    <td><FONT color="green"  ><span id="createdDate"></span></FONT></td>
                                                                </tr>
                                                                <tr id="addTr" style="display: none"> 
                                                                    <td></td><td></td><td></td>
                                                                    <%--   <td class="fieldLabel">Job&nbsp;Logo:</td>
                                                                     <td colspan="2" ><s:file name="file" label="file" cssClass="inputTextarea" id="file" onchange="attachmentFileNameValidate();"/></td>  --%>
                                                                    <td  align="left" style="padding-left: 36px;">
                                                                        <input type="button" value="Save" onclick="return doAddExecutiveMeeting();" class="buttonBg" id="addButton"/> 


                                                                        <%-- <s:submit cssClass="buttonBg"  align="right" id="save" value="Save" /> --%>
                                                                        <s:reset cssClass="buttonBg"  align="right" value="Reset" />

                                                                    </td>
                                                                </tr> <tr id="editTr" style="display: none"> 

                                                                    <td  align="center" colspan="4"style="text-align: right;padding-right: 76px;">
                                                                        <input type="button" value="Update" onclick="return doUpdateExeMeeting();" class="buttonBg" id="updateButton"/> &nbsp;&nbsp;
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </s:form>    
                                        </div>

<%}%>
                                        <s:form action="emeetSearch.action" id="frmDBGrid" name="frmDBGrid" theme="simple"> 
                                            <div style="width:840px;"> 

                                                <table cellpadding="0" cellspacing="0" align="left" width="100%">

                                                    <tr>
                                                        <td>
                                                            <table border="0" cellpadding="0" cellspacing="2" align="left" width="100%">
                                                                <tr>
                                                                    <td class="headerText" colspan="11" align="right">
                                                                        <div id="publishMessage"></div>
                                                                        <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                                   <%
                                                                            if (request.getAttribute("resultMessage") != null) {
                                                                                out.println(request.getAttribute("resultMessage"));
                                                                            }

                                                                        %>
                                                                    </td>
                                                                </tr>

                                                                <tr>

                                                                    <td class="fieldLabel"> Date&nbsp;From:</td>
                                                                    <td><s:textfield name="emeetFromDate" id="emeetFromDate" cssClass="inputTextBlue" onchange="validateTimestamp(this);"/>
                                                                        <a href="javascript:cal1.popup();">
                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif"
                                                                                 width="20" height="20" border="0"></a>
                                                                    </td>
                                                                    <td class="fieldLabel"> Date&nbsp;To:</td>
                                                                    <td><s:textfield name="emeetToDate" id="emeetToDate" cssClass="inputTextBlue" onchange="validateTimestamp(this);"/>
                                                                        <a href="javascript:cal2.popup();">
                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif"
                                                                                 width="20" height="20" border="0"></a>
                                                                    </td>

                                                                </tr><tr>

                                                                    <td class="fieldLabel">Type:</td>
                                                                    <td ><s:select headerKey="" headerValue="--Please Select--" id="eMeetType"  name="emeetType" list="{'Global Practice Meet','Global Sales Meet'}" cssClass="inputSelect"  disabled="False"/>

                                                                    <td class="fieldLabel">Status:</td>
                                                                    <td ><s:select name="emeetStatusByDate" id="emeetStatusByDate" list="#@java.util.LinkedHashMap@{'U':'Upcoming Emeets','E':'Completed Emeets'}" cssClass="inputSelect" value="%{emeetStatusByDate}" />
                                                                </tr><tr>
                                                                    <td  class="fieldLabel" >Month:</td>
                                                                    <td>
                                                                        <%-- <s:textfield name="jobHireType" id="jobHireType" cssClass="inputTextBlue" onchange="return fieldLengthValidator2(this);"/> --%>
                                                                        <s:select headerKey="" headerValue="--Please Select--" list="{'January','February','March','April','May','June','July','August','September','October','November','December'}" name="executiveMeetMonthSearch" id="executiveMeetMonthSearch" cssClass="inputSelect"/>
                                                                    </td> 
                                                                    <td></td>
                                                                    <td>
                                                                        <!-- <input type="button" value="Search" class="buttonBg" onclick="getSearchReqList();"/> -->
                                                                        <%-- <s:if test="#session.jobPostingFlag == 1 || #session.roleId == 12">
                                                                           <input type="button" class="buttonBg"  align="right"  value="Add" onclick="addExecutiveMeet();" /> 
                                                                         </s:if> --%>
                                                                        <%if(request.getAttribute("emeetStatusByDate")==null|| request.getAttribute("emeetStatusByDate").toString().equals("U")){%>
                                                                        <input type="button" class="buttonBg"  align="right"  value="Add" onclick="addExecutiveMeet();"/> 
                                                                        <%} %>
                                                                        
                                                                        <s:submit name="search" value="Search" cssClass="buttonBg"/>
                                                                        <!--<input type="button" align="right" id="search" value="Search" cssClass="buttonBg" onclick="getSearchReqList();"/>-->
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </td>
                                                    </tr>





                                                    <s:if test="#session.ExecutiveMeetingslist != null"> 

                                                        <tr>
                                                            <td style="padding-top: 30px;">
                                                                <table id="results" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='90%' align="center">

                                                                    <%java.util.List mainList = (java.util.List) session.getAttribute("ExecutiveMeetingslist");
                                                                        if (mainList.size() > 0) {


                                                                    %>

                                                                    <tr class="gridHeader">
                                                                        <th>Type</th>
                                                                        <th>Date</th>
                                                                        <th>Status</th>
                                                                        
                                                                            <%if(request.getAttribute("emeetStatusByDate")!=null&&request.getAttribute("emeetStatusByDate").toString().equals("U")){%>
                                                                            
                                                                            <th width="8%">Services</th>
                                                                            <%}%>
                                                                        
                                                                        
                                                                        <%--   <s:if test="#session.teamName == 'Sourcing'"> --%>

                                                                    </tr>
                                                                    <%

                                                                        for (int i = 0; i < mainList.size(); i++) {
                                                                    %>
                                                                    <tr CLASS="gridRowEven">
                                                                        <%
                                                                            //java.util.List subList = (java.util.List)mainList.get(i);
                                                                            java.util.Map subList = (java.util.Map) mainList.get(i);
                                                                            //  for (int j = 0; j < subList.size(); j++) {

                                                                        %>
                                                                        <td class="title">

                                                                            <%

                                                                                String TYPE = (String) subList.get("TYPE");
                                                                                 if(request.getAttribute("emeetStatusByDate").toString().equals("U")){%>
                                                                          
                                                                            <a style="color:#C00067;" href="javascript:editExeMeet('<%=subList.get("Id")%>');">
                                                                                <%}else {%><a style="color:#C00067;" href="javascript:editCompletedExeMeet('<%=subList.get("Id")%>');">
                                                                                <%}
                                                                                    out.println(subList.get("TYPE"));

                                                                                %></a>
                                                                        </td>       
                                                                        <td class="title">
                                                                            <%
                                                                                out.println(subList.get("EMeetDate"));

                                                                            %>
                                                                        </td>         <td class="title">
                                                                            <%
                                                                                out.println(subList.get("STATUS"));

                                                                            %>
                                                                        </td>
                                                                       <%if(request.getAttribute("emeetStatusByDate")!=null&&request.getAttribute("emeetStatusByDate").toString().equals("U")){%>
                                                                        <td class="title">
                                                                            <%if (!"Published".equals(subList.get("STATUS").toString())) {%>
                                                                            <a herf="#" class="round-button" title="Publish" onclick="doPublishExeMeet(this,'<%=subList.get("Id")%>','<%=subList.get("STATUS")%>')">P</a>
                                                                            <%}%>
                                                                            <%if (!"Active".equals(subList.get("STATUS").toString())) {%>
                                                                            <a herf="#" class="round-button" title="Active" onclick="doActiveExeMeet(this,'<%=subList.get("Id")%>','<%=subList.get("STATUS")%>')">A</a>
                                                                            <%}%>
                                                                            </a>
                                                                        </td>
                                                                        <%}%>
                                                                        <%--    <s:if test="#session.teamName == 'Sourcing'"> --%>


                                                                        <%

                                                                            //   }
                                                                        %></tr><%

        }
    } else {
                                                                        %>
                                                                    <tr><td>
                                                                            <%
                                                                                // String contextPath = request.getContextPath();
                                                                                // out.println("<img  border='0' align='top'  src='"+contextPath+"/includes/images/alert.gif'/><b> No Records Found to Display!</b>");

                                                                                out.println("<img  border='0' align='top'  src='" + contextPath + "/includes/images/alert.gif'/> <font color='white'><b>No records found!</b></font>");
                                                                                // }

                                                                            %>
                                                                        </td>
                                                                    </tr>
                                                                    <%}%>

                                                                </table>
                                                            </td>
                                                        </tr>



                                                        <%

                                                            if (mainList.size() != 0) {
                                                        %>

                                                        <tr>

                                                            <td align="right" colspan="4" style="background-color:white;" >
                                                                <div align="right" id="pageNavPosition">hello</div>
                                                            </td>
                                                        </tr> 

                                                        <%}
                                                        %>
                                                    </s:if>



                                                    <script type="text/JavaScript">
                                                        var cal1 = new CalendarTime(document.forms['frmDBGrid'].elements['emeetFromDate']);
                                                        cal1.year_scroll = true;
                                                        cal1.time_comp = false;
                                                        
                                                        var cal2 = new CalendarTime(document.forms['frmDBGrid'].elements['emeetToDate']);
                                                        cal2.year_scroll = true;
                                                        cal2.time_comp = false;
                                                 
                                                        var cal3 = new CalendarTime(document.forms['frmExeMeet'].elements['exeMeetcreatedDateTo']);
                                                        cal3.year_scroll = true;
                                                        cal3.time_comp = false;

						

                                                    </script>    
                                                        
                                                        
                                                    <script type="text/javascript">
                                                        var pager = new Pager('results', 10); 
                                                        pager.init(); 
                                                        pager.showPageNav('pager', 'pageNavPosition'); 
                                                        pager.showPage(1);
                                                    </script>      
                                                </table>
                                            </div>    
                                        </s:form>  


                                        <%--  </sx:div > --%>
                                    </div>  
                                    <%--   <%}%> --%>
                                    <!--//END TAB : -->

                                    <%--  <sx:div id="IssuesSearchTab" label="Issues Search" > --%>

                                </div>
                                <!--//END TAB : -->
                                <%--  </sx:tabbedpanel> --%>

                                <!--//END TABBED PANNEL : -->
                                <script type="text/javascript">

                                    var countries=new ddtabcontent("accountTabs")
                                    countries.setpersist(false)
                                    countries.setselectedClassTarget("link") //"link" or "linkparent"
                                    countries.init()

                                </script>            
                            </td>
                        </tr>
                    </table>
                </td>
                <!--//END DATA COLUMN : Coloumn for Screen Content-->
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




     
        <script src="http://code.jquery.com/jquery-1.10.2.js"></script>
        <script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
        <script>
            $(function() {
                $( document ).tooltip();
            });
        </script>

    </body>
</html>

