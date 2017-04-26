<%-- 
   Document   : External Events
   Created on : Jan 9, 2017, 3:37:32 PM
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

        <title>Hubble Organization Portal :: External Events</title>

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">

        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>   
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>




        <script type="text/JavaScript" src='<s:url value="/includes/javascripts/GridNavigation.js"/>'></script>
        <%--  <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmployeeAjax.js"/>"></script> --%>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/jquery.min.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/jquery.js"/>"></script>   

        <%--   <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/ajaxfileupload.js"/>"></script>   --%>



        <%-- for issue reminder popup --%>

<script>
            function eventSpeakerPopup(url) {
                //newwindow=window.open(url,'name','height=350,width=200,top=200,left=250');
                newwindow=window.open(url,'name','height=300,width=350,top=200,left=250');
                if (window.focus) {newwindow.focus()}
            }
        </script>

        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/marketing/EventsPosting.js?ver=1.0"/>"></script>  

        <s:include value="/includes/template/headerScript.html"/>
    </head>
   <%-- <body class="bodyGeneral" oncontextmenu="return false;" onload="getWebinarSeriesList('E')"> --%>
    <body class="bodyGeneral" oncontextmenu="return false;">


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

                                    <li ><a href="#" class="selected" rel="webinarsTab">External&nbsp;Webinars</a></li>
                                    <!--   <li ><a href="#" rel="webinarSeriesTab">Webinar&nbsp;Series</a></li>-->
                                    <li ><a href="#" rel="conferenceTab">Conferences</a></li>

                                    <%--   <%}else{%>
                                       <li ><a href="#" class="selected" rel="IssuesSearchTab">Tasks Search</a></li>
                                        <% }%> --%>

                                </ul>


                                <%-- <sx:tabbedpanel id="IssuesPanel" cssStyle="width: 845px; height: 500px;padding:5px 5px;" doLayout="true"> --%>
                                <div  style="border:1px solid gray; width:845px;height: 500px; overflow:auto; margin-bottom: 1em;">

                                    <div id="webinarsTab" class="tabcontent" >
                                        <div id="overlay" ></div> 
                                        <div id="specialBox" >
                                            <s:form theme="simple" align="center" name="eventForm" id="eventForm">

                                                <s:hidden name="event_id" id="event_id"/>
                                                <s:hidden name="seriesId" id="seriesId"/>
                                                <table align="center" border="0" cellspacing="0" style="width:100%;" >
                                                    <tr>                               
                                                        <td colspan="2" style="background-color: #288AD1" >
                                                            <h3 style="color:darkblue;" align="left">
                                                                <span id="headerLabel"></span>


                                                            </h3></td>
                                                        <td colspan="2" style="background-color: #288AD1" align="right">

                                                            <a href="#" onmousedown="closeExternalWebinarOverlay()" >
                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> 

                                                            </a>  

                                                        </td></tr>
                                                    <tr>
                                                        <td colspan="4">
                                                            <div id="load" style="color: green;display: none;">Loading..</div>
                                                            <div id="resultMessage"></div>
                                                        </td>
                                                    <tr style="display: none" id="depotUrlTr">
                                                        <td  class="fieldLabel">Redirection&nbsp;Link : </td>
                                                        <td colspan="3"><span id="depotLink"></span></td>
                                                    </tr>
                                                    <tr style="display: none" id="beforeUrlTr">
                                                        <td  class="fieldLabel">Before&nbsp;Page&nbsp;Link : </td>
                                                        <td colspan="3"><span id="beforeLink"></span></td>
                                                    </tr><tr style="display: none" id="ewafterUrlTr">
                                                        <td  class="fieldLabel">After&nbsp;Page&nbsp;Link : </td>
                                                        <td colspan="3"><span id="ewafterLink"></span></td>
                                                    </tr>


                                                    </tr>    
                                                    <tr><td colspan="4">
                                                            <table style="width:80%;" align="center">
                                                                
                                                                <tr>
                                                                    <td align="left" class="fieldLabel" >Upcoming&nbsp;Page&nbsp;Title:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td colspan="3">
                                                                        <s:textfield name="eventtitle" id="eventtitle" cssClass="inputTextBlueLargeAccount" onchange="return eventsLengthValidator(this,'resultMessage');" value="Technical Webinar :"/>
                                                                    </td> 
                                                                </tr>
                                                                <tr id="eventBoldTr">
                                                                    <td align="left" class="fieldLabel" >Page&nbsp;Title:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td colspan="3">
                                                                        <s:textfield name="eventBoldtitle" id="eventBoldtitle" cssClass="inputTextBlueLargeAccount" onchange="return eventsLengthValidator(this,'resultMessage');" />
                                                                    </td> 
                                                                </tr>


                                                                <tr >
                                                                    <s:hidden name="tempEventDate" id="tempEventDate"/>
                                                                    <s:hidden name="currentDate" id="currentDate"/>
                                                                    <td class="fieldLabel"><span id="fromDateLabelId">Event&nbsp;Date&nbsp;:</span></td>
                                                                    <td><s:textfield name="selectDateFrom" id="selectDateFrom" cssClass="inputTextBlue" onchange="checkEventDate();validateTimestamp(this);"/>
                                                                        <a href="javascript:selectcal1.popup();">
                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif"
                                                                                 width="20" height="20" border="0"></a>
                                                                    </td> 
                                                                    <td  class="fieldLabel" >Status:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td ><s:select id="eventStatus"  name="eventStatus"  list="{'Active','InActive','Completed','Published'}" cssClass="inputSelect" onchange="getVideoTr(this);" headerKey="" headerValue="Select"/>
                                                                    </td>

                                                                </tr>
                                                                <tr id="timeTr" >

                                                                    <td class="fieldLabel">Select&nbsp;Start&nbsp;Time:</td>
                                                                    <td><s:select id="startTime" name="startTime" list="timeList" cssClass="inputSelectGender" headerKey="" headerValue="Select"/>
                                                                        <s:select id="midDayFrom" name="midDayFrom" list="{'AM','PM'}" cssClass="inputSelectSmall" headerKey="" headerValue="Select"/>
                                                                    </td>
                                                                    <td class="fieldLabel">Select&nbsp;End&nbsp;Time:</td>
                                                                    <td><s:select id="endTime"  name="endTime"  list="timeList" cssClass="inputSelectGender" headerKey="" headerValue="Select"/><s:select id="midDayTo"  name="midDayTo"  list="{'AM','PM'}" cssClass="inputSelectSmall" headerKey="" headerValue="Select"/>
                                                                    </td>

                                                                </tr>  
                                                                <tr> <td align="left" class="fieldLabel" >Location:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td>
                                                                        <s:textfield name="eventLocation" id="eventLocation" cssClass="inputTextBlue" onchange="return eventsLengthValidator(this,'resultMessage');" />
                                                                    </td> 
                                                                    <td class="fieldLabel"><div id="timeZoneLabelId">Select&nbsp;TimeZone:</div></td>  
                                                                    <td ><div id="timeZoneTextId"><s:select id="timeZone"  name="timeZone"  list="{'EST','IST','MST','CST','PST'}" headerKey="" headerValue="Select" cssClass="inputSelect" /></div></td>


                                                                </tr>


                                                                <tr id="eventDescriptionTr">
                                                                    <td class="fieldLabel" valign="top">Event&nbsp;Description:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td colspan="3" valign="top"><s:textarea rows="5" cols="65" name="eventDescription" cssClass="inputTextareaOverlay1" onchange="checkDoubleQuotes(this);return eventsLengthValidator(this,'resultMessage');" id="eventDescription" />

                                                                        <font class="fieldLabel">
                                                                            <br> Please include p tag to every paragraph like &lt;p class='methodText'&gt;...&lt;/p&gt;...&lt;p class='methodText'&gt;...&lt;/p&gt;
                                                                        </font>




                                                                    </td>
                                                                </tr>

                                                                <tr id="eventRegistrationLinkTr">
                                                                    <td align="left" class="fieldLabel" >Event&nbsp;Registration&nbsp;Link:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td colspan="3">
                                                                        <s:textfield name="eventRegistrationLink" id="eventRegistrationLink" cssClass="inputTextBlueLargeAccount" onchange="isUrl(this);" />
                                                                    </td> 
                                                                </tr>



                                                                <tr  id="eventTrackTr">
                                                                    <td align="left" class="fieldLabel" >Primary&nbsp;Track:<FONT color="red"  ><em>*</em></FONT></td>  
                                                                    <td >
                                                                        <%-- <s:select headerKey="" headerValue="--Please Select--" id="primaryTrack"  name="primaryTrack"  list="{'Cloud-Services','SOA and Connectivity','B2B Integration and File Transfer','SAP/ERP Services','Application Management','Business Process Management','Application Development','Quality Assurance and Testing','Digital Experience and Commerce' ,'Internal Technical Enablement','Internal Sales Enablement','Internal Team Enablement'}" cssClass="inputSelect" /> --%>
                                                                        <s:select headerKey="" headerValue="--Please Select--" id="primaryTrack"  name="primaryTrack"  list="trackNamesMap" cssClass="inputSelect" />

                                                                    </td>
                                                                    <td align="left" class="fieldLabel" >Secondary&nbsp;Track:</td>  
                                                                    <td >
                                                                        <%-- <s:select headerKey="" headerValue="--Please Select--" id="secondaryTrack"  name="secondaryTrack"  list="{'Cloud-Services','SOA and Connectivity','B2B Integration and File Transfer','SAP/ERP Services','Application Management','Business Process Management','Application Development','Quality Assurance and Testing','Digital Experience and Commerce' ,'Internal Technical Enablement','Internal Sales Enablement','Internal Team Enablement'}" cssClass="inputSelect" /> --%>
                                                                        <s:select headerKey="" headerValue="--Please Select--" id="secondaryTrack"  name="secondaryTrack"  list="trackNamesMap" cssClass="inputSelect" />

                                                                    </td>
                                                                </tr>



                                                                <tr id="contactUsTr">
                                                                    <td align="left" class="fieldLabel" >ContactUs Email:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td colspan="3">
                                                                        <s:textfield name="contactUsEmail" id="contactUsEmail" cssClass="inputTextBlueLargeAccount" onchange="checkEventsEmail(this,'iwresultMessage')" />
                                                                    </td> 
                                                                </tr>

                                                                <%-- <tr id="seriesTr" style="display: none">
                                                                  <td class="fieldLabel" valign="top">Is Associated:<FONT color="red"  ><em>*</em></FONT> </td>
                                                                  <td ><s:select id="isAssociated"  name="isAssociated"  list="{'No','YES'}" cssClass="inputSelect" onchange="getEventSeries('');"/></td>
                                                                   <td class="fieldLabel">Associated&nbsp;Event:</td>
                                           <td ><s:select id="associatedEventId"  name="associatedEventId"  list="associatedEventMap" headerKey="" headerValue="--Please Select--" cssClass="inputSelect"/></td> 
                                                              </tr> --%>


                                                                <tr id="createdTr" style="display: none">
                                                                    <td class="fieldLabel" valign="top">CreatedBy:<FONT color="red"  ><em>*</em></FONT> </td>
                                                                    <td><FONT color="green"  ><span id="createdBy"></span></FONT></td>
                                                                    <td class="fieldLabel" valign="top">CreatedDate:<FONT color="red"  ><em>*</em></FONT> </td>
                                                                    <td><FONT color="green"  ><span id="createdDate"></span></FONT></td>
                                                                </tr>
                                                                <tr id="addTr" style="display: none"> 


                                                                    <td  align="center" >
                                                                        <input type="button" value="Save" onclick="return doAddExternalWebinarEventPost();" class="buttonBg" id="addButton"/> 



                                                                        <s:reset cssClass="buttonBg"  align="right" value="Reset" />

                                                                    </td>
                                                                </tr> <tr id="editTr" style="display: none"> 
                                                                    <td  align="center" >
                                                                        <input type="button" value="Update" onclick="return doUpdateExternalWebinarEventPost();" class="buttonBg" id="updateButton"/> 
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </s:form>    
                                        </div>
                                        <div id="overlayForPublishButton" class="overlay"></div> 
                                        <div id="specialBoxForPublishButton" class="specialBox">
                                            <s:form theme="simple" align="center" name="eventPublishForm" id="eventPublishForm">

                                                <table align="center" border="0" cellspacing="0" style="width:100%;" >
                                                    <tr>                               
                                                        <td colspan="2" style="background-color: #288AD1" >
                                                            <h3 style="color:darkblue;" align="left">
                                                                <span id="headerLabelForPublishButton"></span>


                                                            </h3></td>
                                                        <td colspan="2" style="background-color: #288AD1" align="right">

                                                            <a href="#" onmousedown="closeCompletedWebinarToogle('frmDBGrid')" >
                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> 

                                                            </a>  

                                                        </td></tr>
                                                    <tr>
                                                        <td colspan="4">
                                                            <div id="loadAfterVideo" style="color: green;display: none;">Loading..</div>
                                                            <div id="resultMessageAfterVideo"></div>
                                                        </td></tr>
                                                    <tr style="display: none" id="webinarDepotUrlTr">
                                                        <td  class="fieldLabel" style="text-align: right">After&nbsp;Page&nbsp;Link&nbsp;: </td>
                                                        <td colspan="3"><span id="webinarDepotLink"></span></td>
                                                        <s:hidden name="webinar_event_id" id="webinar_event_id"/>
                                                    </tr>
                                                    
                                                           
                                                                
                                                                <tr>
                                                                    <td align="left" class="fieldLabel" >Upcoming&nbsp;Page&nbsp;Title:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td colspan="3">
                                                                        <%--    <s:textfield name="eventtitle" id="eventtitle" cssClass="inputTextBlueLargeAccount" onchange="return fieldLengthValidator2(this);" /> --%>
                                                                        <FONT color="green" size="2px"><span id="webinarEventUpcomingPageLabel"></span></FONT>
                                                                    </td> 
                                                                </tr>
                                                                <tr style="display: none" id="webinarEventBoldTr">
                                                                    <td align="left" class="fieldLabel" >Page&nbsp;Title:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td colspan="3">
                                                                        <FONT color="green" size="2px"><span id="webinarEventTitlePageLabel"></span></FONT>
                                                                        <%--   <s:textfield name="eventBoldtitle" id="eventBoldtitle" cssClass="inputTextBlueLargeAccount" onchange="return fieldLengthValidator2(this);" /> --%>
                                                                    </td> 
                                                                </tr>
                                                                <tr id="webinarEventAfterDescriptionTr" style="display: none">
                                                                    <td class="fieldLabel" valign="top">Event&nbsp;After&nbsp;Description:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td colspan="3" valign="top"><s:textarea rows="5" cols="65" name="webinarEventAfterDescription" cssClass="inputTextareaOverlay1"  onchange="checkDoubleQuotes(this);return eventsLengthValidator(this,'resultMessage');" id="webinarEventAfterDescription"/>

                                                                        <font class="fieldLabel">
                                                                            <br> Please include p tag to every paragraph like &lt;p class='methodText'&gt;...&lt;/p&gt;...&lt;p class='methodText'&gt;...&lt;/p&gt;
                                                                        </font>
                                                                    </td>
                                                                </tr>
                                                                <tr style="display: none" id="webinarAfterVideoTr">
                                                                    <td align="left" class="fieldLabel" >After&nbsp;Video&nbsp;Link:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td colspan="3">
                                                                        <s:textfield name="webinarEventAfterVideoUrl" id="webinarEventAfterVideoUrl" cssClass="inputTextBlueLargeAccount" onchange="return isValidUrl(this,'resultMessageAfterVideo');" />
                                                                    </td> 
                                                                </tr>
                                                                <tr style="display: none" id="webinarEventDepartmentTr">
                                                                    <td align="left" class="fieldLabel" >Department:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td colspan="3">
                                                                        <FONT color="green" size="2px"><span id="webinarDepartmentLabel"></span></FONT>
                                                                        <%-- <s:select headerKey="" headerValue="--Please Select--" id="eventDepartment"  name="eventDepartment"  list="{'Business Development','Marketing','Recruitment','Other','Development','Operations'}" cssClass="inputSelect" /> --%>

                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td  class="fieldLabel" >Status:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td >
                                                                        <%--     <s:select id="eventStatus"  name="eventStatus"  list="{'Active','InActive','Completed','Published'}" cssClass="inputSelect" onchange="checkAfterFields(this);" /> --%>
                                                                        <s:select id="webinarEventStatus"  name="webinarEventStatus"  list="{'Completed','Published'}" cssClass="inputSelect" onchange="checkAfterFields(this);" />
                                                                        <FONT color="green" size="2px"><span id="webinarEventStatusLabel" style="display: none"></span></FONT>

                                                                    </td>
                                                                </tr>
                                                                <tr id="webinarEventCreatedTr" style="display: none">
                                                                    <td class="fieldLabel" valign="top">CreatedBy:<FONT color="red"  ><em>*</em></FONT> </td>
                                                                    <td><FONT color="green"  ><span id="webinarEventCreatedBy"></span></FONT></td>
                                                                    <td class="fieldLabel" valign="top">CreatedDate:<FONT color="red"  ><em>*</em></FONT> </td>
                                                                    <td><FONT color="green"  ><span id="webinarEventCreatedDate"></span></FONT></td>
                                                                </tr>
                                                                <tr id="webinarEditTr" style="display: none"> 
                                                                    <td colspan="4" >
                                                                        <input type="button" value="Update" style="margin-left: 500px;" onclick="return doUpdateWebinarAfterEventPost('E');" class="buttonBg" id="updateButton"/> 
                                                                    </td>
                                                                </tr>
                                                            
                                                        </td>
                                                    </tr>
                                                </table>
                                            </s:form>    
                                        </div>


                                        <s:form action="externalWebinarSearch.action" id="frmDBGrid" name="frmDBGrid" theme="simple"> 
                                            <div style="width:840px;"> 
                                                <s:hidden name="frmName" id="frmName" value="frmDBGrid"/>

                                                <table cellpadding="0" cellspacing="0" align="left" width="100%">

                                                    <tr>
                                                        <td>
                                                            <table border="0" cellpadding="0" cellspacing="2" align="left" width="100%">
                                                                <tr>
                                                                    <td class="headerText" colspan="11">
                                                                        <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                                    </td>
                                                                </tr>
                                                                <tr>

                                                                    <td class="fieldLabel">Start Date From:</td>
                                                                    <td><s:textfield name="createdDateFrom" id="createdDateFrom" cssClass="inputTextBlue" onchange="validateTimestamp(this);"/>
                                                                        <a href="javascript:cal1.popup();">
                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif"
                                                                                 width="20" height="20" border="0"></a>
                                                                    </td>
                                                                    <td class="fieldLabel">To:<FONT color="red" SIZE="0.5"><em>*</em></FONT></td>
                                                                    <td>
                                                                        <s:textfield name="createdDateTo"  id="createdDateTo" cssClass="inputTextBlue" onchange="validateTimestamp(this);"/>
                                                                        <a href="javascript:cal2.popup();">
                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif"
                                                                                 width="20" height="20" border="0"></a>
                                                                    </td>

                                                                </tr>
                                                                <tr>


                                                                    <td class="fieldLabel">Event&nbsp;Title :</td>
                                                                    <td>
                                                                        <s:textfield name="title" id="title" cssClass="inputTextBlue" theme="simple"/>
                                                                    </td>
                                                                    <td class="fieldLabel">Status:</td>
                                                                    <%--    <td ><s:select headerKey="" headerValue="--Please Select--" id="status"  name="status"  list="{'Active','InActive'}" cssClass="inputSelect"  disabled="False"/> --%>
                                                                    <td ><s:select  id="status"  name="status"  list="{'Active','InActive','Completed','Published'}" cssClass="inputSelect"  disabled="False"/>
                                                                </tr>
                                                                <%-- <tr>
                                                                     <td class="fieldLabel">Event Type:</td>
                                                                     <td >
                                                                       <s:select headerKey="" headerValue="--Please Select--" list="#@java.util.LinkedHashMap@{'Q':'Quarterly Meet','IEE':'Internal Employee Experience','I':'Internal Webinar','E':'External Webinar','C':'Conferences'}" name="eventSearchType" id="eventSearchType" cssClass="inputSelect" disabled="False"/>
                                                                 </tr> --%>

                                                                
                                                                <tr>

                                                                    <td></td>
                                                                    <td colspan="2"></td>
                                                                    <td>
                                                                        <input type="button" class="buttonBg"  align="right"  value="Add" onclick="addExternalWebinarEventPost();" /> 
                                                                        <s:submit name="search" value="Search" cssClass="buttonBg"/>
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </td>
                                                    </tr>




                                                    <s:if test="#session.externalWebinarList != null"> 
                                                        <tr>
                                                            <td>
                                                                <table id="webinarResults" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='90%' align="center">

                                                                    <%java.util.List mainList = (java.util.List) session.getAttribute("externalWebinarList");
                                                                        if (mainList.size() > 0) {


                                                                    %>

                                                                    <tr class="gridHeader">

                                                                        <th>EventTitle</th>
                                                                        <!-- <th>EventDesc</th> -->
                                                                        
                                                                        <th>EventDate</th>

                                                                        <th>Status</th>
                                                                        <th>Speakers</th>
                                                                        <th>Publish</th>
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



                                                                            <a style="color:#C00067;" href="javascript:editExternalWebinarEventPost('<%=subList.get("event_id")%>');">
                                                                                <%
                                                                                    out.println(subList.get("event_title"));
                                                                                %></a>
                                                                        </td>    
                                                                       
                                                                        <td class="title">
                                                                            <%
                                                                                out.println(subList.get("event_startdate").toString().split("\\ ")[0]);
                                                                            %>
                                                                        </td>
                                                                        <%--   <td class="title">
                                                                         <%
                                                                       
                                                                         StringEscapeUtils seu = new StringEscapeUtils();
                                                                                  
                                                                         String EventDescription = (String)subList.get("event_description");
                                                                      //    out.println(seu.unescapeJavaScript(jobDesc));

%>
<A href="javascript: populateDescriptions('<%=EventDescription%>');">Click to view</A>
                     </td>    --%>


                                                                        <td class="title">
                                                                            <%
                                                                                out.println(subList.get("STATUS"));

                                                                            %>
                                                                        </td>

                                                                        <td class="title">
                                                                             <%
                              //if(subList.get("WebinarType").toString().equalsIgnoreCase("I")||subList.get("WebinarType").toString().equalsIgnoreCase("E")){
                              if (("Completed".equalsIgnoreCase(subList.get("STATUS").toString()) || "Published".equalsIgnoreCase(subList.get("STATUS").toString()))) {
    
    
    %>
                         <a style="color:#C00067;" href="javaScript:eventSpeakerPopup('../eventSpeakerDetailsList.action?eventId=<%=subList.get("event_id")%>&objectType=EV');">
                             
                             
                             <img src="../../includes/images/go_21x21.gif"/>
                        </a>
                             <% } else { %>
                                                                            <a style="color:#C00067;" href="javascript:getWebinarSpeakers('<%=subList.get("event_id")%>','EV');">
                                                                                <img src="../../includes/images/go_21x21.gif"/>
                                                                            </a>
                                                                                <% } %>
                                                                        </td>
                                                                        <td>
                                                                        <%
                                                                            //if(subList.get("WebinarType").toString().equalsIgnoreCase("I")||subList.get("WebinarType").toString().equalsIgnoreCase("E")){
                                                                            if (("Completed".equalsIgnoreCase(subList.get("STATUS").toString()) || "Published".equalsIgnoreCase(subList.get("STATUS").toString()))) {


                                                                        %>
                                                                    <a style="color:#C00067;" href="javascript:getCompletedWebinarEventDetails('<%=subList.get("event_id")%>','publish');">
                                                                        <img src="../../includes/images/publish-button.jpg" height="30" width="50"/>
                                                                    </a>
                                                                    <% }%>

                                                                        </td>
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
                                                            <div align="right" id="webinarPageNavPosition">hello</div>
                                                        </td>
                                                    </tr> 

                                                    <% }
                                                    %>
                                                </s:if><s:else>
                                                        <tr><td><br><br><br><br><br><br><br><br></td></tr>
                                                    </s:else>



                                                <script type="text/JavaScript">
                                                    var cal1 = new CalendarTime(document.forms['frmDBGrid'].elements['createdDateFrom']);
                                                    cal1.year_scroll = true;
                                                    cal1.time_comp = false;

                                                    var cal2 = new CalendarTime(document.forms['frmDBGrid'].elements['createdDateTo']);
                                                    cal2.year_scroll = true;
                                                    cal2.time_comp = false;

                                                    var selectcal1 = new CalendarTime(document.forms['eventForm'].elements['selectDateFrom']);
                                                    selectcal1.year_scroll = true;
                                                    selectcal1.time_comp = false;
                                               
                                                </script>                            
                                                <script type="text/javascript">
                                                    var pager = new ReviewPager('webinarResults', 10); 
                                                    pager.init(); 
                                                    pager.showPageNav('pager', 'webinarPageNavPosition'); 
                                                    pager.showPage(1);
                                                </script>      
                                                </table>
                                            </div>    
                                        </s:form>  


                                        <%--  </sx:div > --%>
                                        
                                        <!-- Webinar Series tab start -->
                                        
                                         <ul id="accountTabs1" class="shadetabs" >
                                            <li ><a href="#" class="selected" rel="webinarSeriesTab"  >Webinar Series </a></li>
                                            
                                        </ul>
                                       
                                        <div  style="border:1px solid gray; width:840px;height: 255px; overflow:auto; margin-bottom: 1em;">
                                            
                                           
                                            <div id="webinarSeriesTab" class="tabcontent"  >  
                                                 
                                              <div id="overlayForWebinarSeries" class="overlay"></div> 
                                                  <div id="specialBoxWebinarSeries" class="specialBox">
                                                      <s:form theme="simple"  align="center" name="frmResource">
                                                           
                                                          <s:hidden name="webinarSerieId" id="webinarSerieId"/>
                                                           
                                                           <table align="center" border="0" cellspacing="0" style="width:100%;">
                                                                  <tr>                               
                                                    <td colspan="2" style="background-color: #288AD1" >
                                                        <h3 style="color:darkblue;" align="left">
                                                            <span id="seriesHeaderLabel"></span>


                                                        </h3></td>
                                                    <td colspan="2" style="background-color: #288AD1" align="right">

                                                        <a href="#" onmousedown="closeSeriesToggleOverlay('E');" >
                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> 

                                                        </a>  

                                                    </td></tr>
                                                               <tr>
                                                    <td colspan="4">
                                                        <div id="seriesLoad" style="color: green;display: none;">Loading..</div>
                                                        <div id="seriesResultMessage"></div>
                                                    </td>
                                                </tr>    
                                                        <tr><td colspan="4">
                                                         <table style="width:80%;" align="center">
                                                             
                                                             
                                                            
                                                              <tr>
                                                     <td class="fieldLabel" >Series&nbsp;Title:<FONT color="red"  ><em>*</em></FONT> </td>
                                                     <td colspan="3"><s:textfield name="seriesTitle" id="seriesTitle" cssClass="inputTextBlueLargeAccount" onchange="return eventsLengthValidator(this,'seriesResultMessage');"/></td>
                                                       
                                                       
                                                             </tr>
                                                             <input type="hidden" id="seriesType" name="seriesType" value="E"/>
                                                       <tr>
                                                          
                                     <td class="fieldLabel">Track:<FONT color="red"  ><em>*</em></FONT> </td>
                                                        <td ><s:select headerKey="" headerValue="--Please Select--" id="seriesTrack"  name="seriesTrack"  list="trackNamesMap" cssClass="inputSelect" /></td>
                                                        <td class="fieldLabel">Status:<FONT color="red"  ><em>*</em></FONT> </td>
                                    <td ><s:select id="seriesStatus" name="seriesStatus" list="{'Active','InActive'}" cssClass="inputSelect" /></td> 
                                                       </tr>
                                                       
                                                         <tr id="seriesCreatedTr" style="display: none">
                                                      <td class="fieldLabel" valign="top">CreatedBy:<FONT color="red"  ><em>*</em></FONT> </td>
                                                      <td><FONT color="green"  ><span id="createdBySeries"></span></FONT></td>
                                                       <td class="fieldLabel" valign="top">CreatedDate:<FONT color="red"  ><em>*</em></FONT> </td>
                                                      <td><FONT color="green"  ><span id="createdDateSeries"></span></FONT></td>
                                                  </tr>
                                                        <tr id="seriesAddTr" style="display: none"> 

                                                  
                                                     <td  align="center" >
                                                         <input type="button" value="Create" onclick="return doAddWebinarSeries();" class="buttonBg" id="addButtonSeries"/> 


                                                        
                                                        <s:reset cssClass="buttonBg"  align="right" value="Reset" />
                                                        
                                                    </td>
                                                        </tr> <tr id="seriesEditTr" style="display: none"> 
                                                    <td  align="center" >
                                                         <input type="button" value="Update" onclick="return doUpdateWebinarSeriesDetails();" class="buttonBg" id="updateButtonSeries"/> 
                                                    </td>
                                                </tr>
                                                               </table>
                                                           </td>
                                                       </tr>
                                                           </table>
                                                       </s:form>    
                                                         </div>
                                     
                                     
                                              <!-- Associate Webinar to Series Start -->
                                              
                                              
                                              
                                               <div id="overlayforAssociateWebinatToSeriesTab" class="overlay"></div> 
                                        <div id="specialBoxforAssociateWebinatToSeriesTab" class="specialBox">
                                            <s:form theme="simple" align="center" name="associateWebinarToSeriesForm" id="associateWebinarToSeriesForm">

                                               
                                                <table align="center" border="0" cellspacing="0" style="width:100%;" >
                                                    <tr>                               
                                                        <td colspan="2" style="background-color: #288AD1" >
                                                            <h3 style="color:darkblue;" align="left">
                                                                <span id="associateWebiarHeaderLabel"></span>


                                                            </h3></td>
                                                        <td colspan="2" style="background-color: #288AD1" align="right">

                                                            <a href="#" onmousedown="closeOverlayForWebinarsToSeries()" >
                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> 

                                                            </a>  

                                                        </td></tr>
                                                    <tr>
                                                    <td colspan="4">
                                                        <div id="seriesWebiarLoad" style="color: green;display: none;">Loading Please wait..</div>
                                                        <div id="seriesWebinarResultMessage"></div>
                                                    </td>
                                                </tr>    
                                                    <tr><td colspan="4">
                                                            <table style="width:80%;" align="center">

                                                               <s:hidden name="associateSeriesId" id="associateSeriesId" />
                                                               <tr id="seriesTitleTr" style="display: none">
                                                                   <td class="fieldLabel">Series&nbsp;Name:</td>
                                                                   <td colspan="3"><span id="webinarSeriesTitle"></span></td>
                                                               </tr>
                                
                                <tr id="associateTr" style="display: none">
                                     <td class="fieldLabel">Associate&nbsp;Event:</td>
                               <td ><s:select id="associatedEventId"  name="associatedEventId"  list="unassociatedEventMap" headerKey="" headerValue="--Please Select--" cssClass="inputSelect" /></td> 
                                    <td></td>
                                    <td colspan="2"></td>
                                    <td>
                                
                                        
                                          <input type="button" value="Add" onclick="return addOrRemoveWebinarToSeries('add','0');" class="buttonBg" id="associateAddButton"/> 
                                       
                                    </td>
                                  
                                </tr>
                                
                                
                                
                                <tr><td colspan="4">
                                        
                                        <table id="tblWebinarsList" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='100%' align="center">
                                                                            <COLGROUP ALIGN="left" >
                                                                                <COL width="5%">
                                                                                <COL width="15%">
                                                                                <COL width="15%">
                                                                                <COL width="15%">
                                                                                
                                                                                
                                                                                <%-- <COL width="20%">--%>
                                                                                <%-- <COL width="10%">
                                                                                 <COL width="10%"> --%>
                                                                        </table>
                                                                        <br>
                                    </td></tr>
 
                                                            </table>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </s:form>    
                                        </div>
                                              
                                              <!-- Associate Webinar to Series End -->
                                              
                                              
                                              
                                              
                                              
                                                           
                                            <div style="width:840px;"> 
                                               
                                                <table cellpadding="0" cellspacing="0" align="left" width="100%">
                    
                 <tr>
                        <td>
                            <table border="0" cellpadding="0" cellspacing="2" align="left" width="100%">
                                <tr>
                                    <td class="headerText" colspan="11">
                                        <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                    </td>
                                </tr>
                              
                                <tr>
                                  
                                    <td>
                                        <input type="button" class="buttonBg"  align="right"  value="Add" onclick="addWebinarSeries();" />
                                         
                                    </td>
                                  
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                             <table width="100%" cellpadding="2" cellspacing="2" border="0">
                                 
                                 <tr><td colspan="3" align="center"><div id="seriesLoading" style="display: none"><b><font color="red" size='3px;'>Loading please wait..</font></b></div></td></tr>
                                                            <tr>
                                                                <td colspan="3" >

                                                                    
                                                                        <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                        <table id="tblSeriesList" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='100%' align="center">
                                                                            <COLGROUP ALIGN="left" >
                                                                                <COL width="5%">
                                                                                <COL width="15%">
                                                                                <COL width="15%">
                                                                                <COL width="15%">
                                                                                <COL width="15%">
                                                                                <COL width="15%">
                                                                                
                                                                                <%-- <COL width="20%">--%>
                                                                                <%-- <COL width="10%">
                                                                                 <COL width="10%"> --%>
                                                                        </table>
                                                                        <br>
                                                                       
                                                                    
                                                                </td>
                                                            </tr>
                                                </tr>
                                            </table>
                        </td>
                    </tr>       
                       
            
           
                   
                                                </table>
                                            </div>    
                           
                                                       
                                        
                                           </div>
                                        </div> 
                                        
                                         <script type="text/javascript">

                    var countries=new ddtabcontent("accountTabs1")
                    countries.setpersist(false)
                    countries.setselectedClassTarget("link") //"link" or "linkparent"
                    countries.init()

                                            </script>
                                        
                                        <!-- Webinar Series tab end -->
                                        
                                    </div>  
                                    <%--   <%}%> --%>
                                    <!--//END TAB : -->
                                    <div id="conferenceTab" class="tabcontent" >
                                        <div id="overlayforConferenceTab" class="overlay"></div> 
                                        <div id="specialBoxforConferenceTab" class="specialBox">
                                            <s:form theme="simple" align="center" name="conferenceEventForm" id="conferenceEventForm">

                                                <s:hidden name="conferenceEvent_id" id="conferenceEvent_id"/>
                                                <s:hidden name="conferenceSeriesId" id="conferenceSeriesId"/>
                                                <table align="center" border="0" cellspacing="0" style="width:100%;" >
                                                    <tr>                               
                                                        <td colspan="2" style="background-color: #288AD1" >
                                                            <h3 style="color:darkblue;" align="left">
                                                                <span id="conferenceHeaderLabel"></span>


                                                            </h3></td>
                                                        <td colspan="2" style="background-color: #288AD1" align="right">

                                                            <a href="#" onmousedown="closeExternalConferenceOverlay()" >
                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> 

                                                            </a>  

                                                        </td></tr>
                                                    <tr>
                                                        <td colspan="4">
                                                            <div id="loadConference" style="color: green;display: none;">Loading..</div>
                                                            <div id="conferenceResultMessage"></div>
                                                        </td>
                                                    <tr style="display: none" id="depotUrlTr">
                                                        <td  class="fieldLabel">Redirection&nbsp;Link : </td>
                                                        <td colspan="3"><span id="depotLink"></span></td>
                                                    </tr>
                                                    <tr style="display: none" id="beforeUrlTr">
                                                        <td  class="fieldLabel">Before&nbsp;Page&nbsp;Link : </td>
                                                        <td colspan="3"><span id="beforeLink"></span></td>
                                                    </tr>


                                                    </tr>    
                                                    <tr><td colspan="4">
                                                            <table style="width:80%;" align="center">

                                                                <tr>
                                                                    <td align="left" class="fieldLabel" >Upcoming&nbsp;Page&nbsp;Title:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td colspan="3">
                                                                        <s:textfield name="conferenceEventtitle" id="conferenceEventtitle" cssClass="inputTextBlueLargeAccount" onchange="return eventsLengthValidator(this,'conferenceResultMessage');" />
                                                                    </td> 
                                                                </tr>
                                                                <tr >
                                                                    <s:hidden name="tempEventDate" id="tempEventDate"/>
                                                                    <s:hidden name="currentDate" id="currentDate"/>
                                                                    <td class="fieldLabel"><span id="fromDateLabelId">Event&nbsp;Date&nbsp;From:<FONT color="red"  ><em>*</em></FONT></span></td>
                                                                    <td><s:textfield name="selectConferenceDateFrom" id="selectConferenceDateFrom" cssClass="inputTextBlue" onchange="checkEventDate();validateTimestamp(this);"/>
                                                                        <a href="javascript:cal5.popup();">
                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif"
                                                                                 width="20" height="20" border="0"></a>
                                                                    </td> 
                                                                    <td class="fieldLabel" ><div id="toDateLabelId">Event&nbsp;Date&nbsp;To:<FONT color="red"  ><em>*</em></FONT></div></td>
                                                                    <td ><s:textfield name="selectConferenceDateTo" id="selectConferenceDateTo" cssClass="inputTextBlue" onchange="validateTimestamp(this);"/>
                                                                        <a href="javascript:cal6.popup();">
                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif"
                                                                                 width="20" height="20" border="0"></a>
                                                                    </td></tr>



                                                                <tr >
                                                                    <td align="left" class="fieldLabel" >Location:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td>
                                                                        <s:textfield name="conferenceEventLocation" id="conferenceEventLocation" cssClass="inputTextBlue" onchange="return eventsLengthValidator(this,'conferenceResultMessage');" />
                                                                    </td> 
                                                                    <td  class="fieldLabel" >Status:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td ><s:select id="conferenceEventStatus"  name="conferenceEventStatus"  list="{'Active','InActive','Completed'}" cssClass="inputSelect" onchange="getVideoTr(this);"/>
                                                                    </td>

                                                                </tr>
                                                                <tr id="conferenceLinkTr">
                                                                    <td align="left" class="fieldLabel" >Conference&nbsp;Website&nbsp;Url:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td colspan="3">
                                                                        <s:textfield name="conferenceUrl" id="conferenceUrl" cssClass="inputTextBlueLargeAccount" onchange="isValidUrl(this,'conferenceResultMessage');return fieldLengthValidator2(this);" />
                                                                    </td> 
                                                                </tr>

                                                                <tr id="createdTrConference" style="display: none">
                                                                    <td class="fieldLabel" valign="top">CreatedBy:<FONT color="red"  ><em>*</em></FONT> </td>
                                                                    <td><FONT color="green"  ><span id="createdByConference"></span></FONT></td>
                                                                    <td class="fieldLabel" valign="top">CreatedDate:<FONT color="red"  ><em>*</em></FONT> </td>
                                                                    <td><FONT color="green"  ><span id="createdDateConference"></span></FONT></td>
                                                                </tr>
                                                                <tr id="addTrConference" style="display: none"> 


                                                                    <td  align="right" colspan="4" >
                                                                        <input type="button" value="Save" onclick="return doAddExternalConferenceEventPost();" class="buttonBg" id="addButton"/> 



                                                                        <s:reset cssClass="buttonBg"  align="right" value="Reset" />

                                                                    </td>
                                                                </tr> <tr id="editTrConference" style="display: none"> 
                                                                    <td  align="right" colspan="4" >
                                                                        <input type="button" value="Update" onclick="return doUpdateExternalConferenceEventPost();" class="buttonBg" id="updateButton"/> 
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </s:form>    
                                        </div>


                                        <s:form action="externalConferenceEventSearch.action" id="frmDBConferenceGrid" name="frmDBConferenceGrid" theme="simple"> 
                                            <div style="width:840px;"> 

                                                <table cellpadding="0" cellspacing="0" align="left" width="100%">

                                                    <tr>
                                                        <td>
                                                            <table border="0" cellpadding="0" cellspacing="2" align="left" width="100%">
                                                                <tr>
                                                                    <td class="headerText" colspan="11">
                                                                        <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                                    </td>
                                                                </tr>
                                                                <tr>

                                                                    <td class="fieldLabel">Start Date From:</td>
                                                                    <td><s:textfield name="createdDateFromConference" id="createdDateFromConference" cssClass="inputTextBlue" onchange="validateTimestamp(this);"/>
                                                                        <a href="javascript:cal3.popup();">
                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif"
                                                                                 width="20" height="20" border="0"></a>
                                                                    </td>
                                                                    <td class="fieldLabel">To:<FONT color="red" SIZE="0.5"><em>*</em></FONT></td>
                                                                    <td>
                                                                        <s:textfield name="createdDateToConference"  id="createdDateToConference" cssClass="inputTextBlue" onchange="validateTimestamp(this);"/>
                                                                        <a href="javascript:cal4.popup();">
                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif"
                                                                                 width="20" height="20" border="0"></a>
                                                                    </td>

                                                                </tr>
                                                                <tr>


                                                                    <td class="fieldLabel">Event&nbsp;Title :</td>
                                                                    <td>
                                                                        <s:textfield name="titleConference" id="titleConference" cssClass="inputTextBlue" theme="simple"/>
                                                                    </td>
                                                                    <td class="fieldLabel">Status:</td>
                                                                    <%--    <td ><s:select headerKey="" headerValue="--Please Select--" id="status"  name="status"  list="{'Active','InActive'}" cssClass="inputSelect"  disabled="False"/> --%>
                                                                    <td ><s:select  id="statusConference"  name="statusConference"  list="{'Active','InActive','Completed'}" cssClass="inputSelect"  disabled="False"/>
                                                                </tr>


                                                                <tr>

                                                                    <td></td>
                                                                    <td colspan="2"></td>
                                                                    <td>
                                                                        <input type="button" class="buttonBg"  align="right"  value="Add" onclick="addExternalConferenceEventPost();" /> 
                                                                        <s:submit name="search" value="Search" cssClass="buttonBg"/>
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </td>
                                                    </tr>




                                                    <s:if test="#session.conferenceEventslist != null"> 
                                                        <tr>
                                                            <td>
                                                                <table id="conferenceResults" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='90%' align="center">

                                                                    <%java.util.List conferenceMainList = (java.util.List) session.getAttribute("conferenceEventslist");
                                                                        if (conferenceMainList.size() > 0) {


                                                                    %>

                                                                    <tr class="gridHeader">
                                                                        <th>Sno</th>
                                                                        <th>EventTitle</th>
                                                                        <!-- <th>EventDesc</th> -->
                                                                        
                                                                        <th>EventDate</th>
                                                                        <th>Location</th>
                                                                         <th>CreatedBy</th>
                                                                        <th>Status</th>
                                                                        

                                                                    </tr>
                                                                    <%

                                                                        for (int i = 0; i < conferenceMainList.size(); i++) {
                                                                    %>
                                                                    <tr CLASS="gridRowEven">
                                                                        <%
                                                                            //java.util.List subList = (java.util.List)mainList.get(i);
                                                                            java.util.Map subList = (java.util.Map) conferenceMainList.get(i);
                                                                            //  for (int j = 0; j < subList.size(); j++) {

                                                                        %>
                                                                        <td class="title">
                                                                            <%
                                                                                    out.println(i+1);
                                                                                %>
                                                                        </td>


                                                                            
                                                                        <td class="title">
                                                                            <a style="color:#C00067;" href="javascript:editExternalConferenceEventPost('<%=subList.get("event_id")%>');">
                                                                                <%
                                                                                    out.println(subList.get("event_title"));
                                                                                %></a>
                                                                        </td>    
                                                                       
                                                                        <td class="title">
                                                                            <%
                                                                                out.println(subList.get("event_startdate").toString().split("\\ ")[0]);
                                                                            %>
                                                                        </td>
                                                                        <%--   <td class="title">
                                                                         <%
                                                                       
                                                                         StringEscapeUtils seu = new StringEscapeUtils();
                                                                                  
                                                                         String EventDescription = (String)subList.get("event_description");
                                                                      //    out.println(seu.unescapeJavaScript(jobDesc));

%>
<A href="javascript: populateDescriptions('<%=EventDescription%>');">Click to view</A>
                     </td>    --%>
                                                                         <td class="title">
                                                                            <%
                                                                                out.println(subList.get("location"));

                                                                            %>
                                                                        </td>
                                                                         <td class="title">
                                                                            <%
                                                                                out.println(subList.get("createdby"));

                                                                            %>
                                                                        </td>

                                                                        <td class="title">
                                                                            <%
                                                                                out.println(subList.get("STATUS"));

                                                                            %>
                                                                        </td>

                                                                       

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

                                                            if (conferenceMainList.size() != 0) {
                                                        %>

                                                        <tr>

                                                            <td align="right" colspan="4" style="background-color:white;" >
                                                                <div align="right" id="conferencePageNavPosition">hello</div>
                                                            </td>
                                                        </tr> 

                                                        <% }
                                                        %>
                                                    </s:if>



                                                    <script type="text/JavaScript">
                                                        var cal3 = new CalendarTime(document.forms['frmDBConferenceGrid'].elements['createdDateFromConference']);
                                                        cal3.year_scroll = true;
                                                        cal3.time_comp = false;

                                                        var cal4 = new CalendarTime(document.forms['frmDBConferenceGrid'].elements['createdDateToConference']);
                                                        cal4.year_scroll = true;
                                                        cal4.time_comp = false;
                                                        var cal5 = new CalendarTime(document.forms['conferenceEventForm'].elements['selectConferenceDateFrom']);
                                                        cal5.year_scroll = true;
                                                        cal5.time_comp = false;

                                                        var cal6 = new CalendarTime(document.forms['conferenceEventForm'].elements['selectConferenceDateTo']);
                                                        cal6.year_scroll = true;
                                                        cal6.time_comp = false;

                                                        //                                                        var selectcal1 = new CalendarTime(document.forms['eventForm'].elements['selectDateFrom']);
                                                        //                                                        selectcal1.year_scroll = true;
                                                        //                                                        selectcal1.time_comp = false;
                                               
                                                    </script>                            
                                                    <script type="text/javascript">
                                                        var conferencePager = new ReviewPager('conferenceResults', 10); 
                                                        conferencePager.init(); 
                                                        conferencePager.showPageNav('conferencePager', 'conferencePageNavPosition'); 
                                                        conferencePager.showPage(1);
                                                    </script>      
                                                </table>
                                            </div>    
                                        </s:form>  


                                        <%--  </sx:div > --%>
                                    </div>  
                                    <%--  <sx:div id="IssuesSearchTab" label="Issues Search" > --%>

                                </div>
                                <!--//END TAB : -->
                                <%--  </sx:tabbedpanel> --%>

                                <!--//END TABBED PANNEL : -->
                                <script type="text/javascript">

                                    var countries=new ddtabcontent("accountTabs")
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



            <!--//END DATA RECORD : Record for LeftMenu and Body Content-->

            <!--//START FOOTER : Record for Footer Background and Content-->
            <tr class="footerBg">
                <td align="center"><s:include value="/includes/template/Footer.jsp"/>   </td>
            </tr>
            <tr>
                <td>

                    <div style="display: none; position: absolute; top:170px;left:320px;overflow:auto;" id="menu-popup">
                        <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" ></table>
                    </div>

                </td>
            </tr>

            <!--//END FOOTER : Record for Footer Background and Content-->

        </table>
        <!--//END MAIN TABLE : Table for template Structure-->



<script type="text/javascript">
		$(window).load(function(){
		getWebinarSeriesList('E');

		});
		</script>

    </body>
</html>

