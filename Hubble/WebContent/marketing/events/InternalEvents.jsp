<%-- 
    Document   : InternalEvents
    Created on : Jan 9, 2017, 3:37:32 PM
    Author     : miracle
--%>

<%-- 
    Document   : EventList
    Created on : Jul 15, 2015, 2:05:13 PM
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

        <title>Hubble Organization Portal :: Events List</title>

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
    <%-- <s:if test="#session.roleName == 'WebAdmin'">
        <body class="bodyGeneral" oncontextmenu="return false;" onload="getWebinarSeriesList('I')">
        </s:if><s:else>
            <body class="bodyGeneral" oncontextmenu="return false;">
        </s:else> --%>
    
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

                                    <li ><a href="#" class="selected" rel="qmeetsTab">Quarterly&nbsp;Meets</a></li>
                                    <s:if test="#session.roleName == 'WebAdmin'">
                                        <li ><a href="#" rel="ieeTab">IEE</a></li>
                                    <li ><a href="#" rel="internalWebinarsTab">Internal&nbsp;Webinars</a></li>
                                    </s:if>
                                    
                                    <s:if test="#session.emeetPostingFlag == 1">
                                         <li ><a href="#" rel="executiveMeetsTab">Executive&nbsp;Meets</a>
                                        <li ><a href="#" rel="eMeetAttendeesTab">EMeet&nbsp;Attendees</a></li>
                                    </s:if>
                                   
                                        <!--<li ><a href="#" rel="webinarsTab">Webinar&nbsp;Series</a></li>
                                       <li ><a href="#" rel="webinarsTab">Executive&nbsp;Meets</a></li>
                                        -->
                                        <%--   <%}else{%>
                                           <li ><a href="#" class="selected" rel="IssuesSearchTab">Tasks Search</a></li>
                                            <% }%> --%>

                                </ul>


                                <%-- <sx:tabbedpanel id="IssuesPanel" cssStyle="width: 845px; height: 500px;padding:5px 5px;" doLayout="true"> --%>
                                <div  style="border:1px solid gray; width:845px;height: 500px; overflow:auto; margin-bottom: 1em;">

                                    <div id="qmeetsTab" class="tabcontent" >
                                        <div id="overlay" ></div> 
                                        <div id="specialBox" >
                                            <s:form theme="simple" align="center" name="eventForm" id="eventForm">

                                                <s:hidden name="event_id" id="event_id"/>

                                                <table align="center" border="0" cellspacing="0" style="width:100%;" >
                                                    <tr>                               
                                                        <td colspan="2" style="background-color: #288AD1" >
                                                            <h3 style="color:darkblue;" align="left">
                                                                <span id="headerLabel"></span>


                                                            </h3></td>
                                                        <td colspan="2" style="background-color: #288AD1" align="right">

                                                            <a href="#" onmousedown="toggleQmeetOverlay()" >
                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> 

                                                            </a>  

                                                        </td></tr>
                                                    <tr>
                                                        <td colspan="4">
                                                            <div id="load" style="color: green;display: none;">Loading..</div>
                                                            <div id="resultMessage"></div>
                                                        </td></tr>
                                                    <tr style="display: none" id="depotUrlTr">
                                                        <td  class="fieldLabel">Redirection&nbsp;Link : </td>
                                                        <td colspan="3"><span id="depotLink"></span></td>
                                                    </tr>

                                                     
                                                    <tr><td colspan="4">
                                                            <table style="width:80%;" align="center">

                                                                <tr>
                                                                    <td align="left" class="fieldLabel" >Upcoming&nbsp;Page&nbsp;Title:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td colspan="3">
                                                                        <s:textfield name="eventtitle" id="eventtitle" cssClass="inputTextBlueLargeAccount" onchange="return eventsLengthValidator(this,'resultMessage');"/>
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
                                                                    <td ><s:select id="eventStatus"  name="eventStatus"  list="{'Active','InActive','Completed'}" cssClass="inputSelect" onchange="getVideoTr(this);"/>
                                                                    </td>

                                                                </tr>
                                                                <tr id="timeTr" >

                                                                    <td class="fieldLabel">Select&nbsp;Start&nbsp;Time:</td>
                                                                    <td><s:select id="startTime"  name="startTime"  list="timeList" cssClass="inputSelectGender" />
                                                                        <s:select id="midDayFrom"  name="midDayFrom"  list="{'AM','PM'}" cssClass="inputSelectSmall" />
                                                                    </td>
                                                                    <td class="fieldLabel">Select&nbsp;End&nbsp;Time:</td>
                                                                    <td><s:select id="endTime"  name="endTime"  list="timeList" cssClass="inputSelectGender" /><s:select id="midDayTo"  name="midDayTo"  list="{'AM','PM'}" cssClass="inputSelectSmall" />
                                                                    </td>

                                                                </tr>  
                                                                <tr> <td align="left" class="fieldLabel" >Location:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td>
                                                                        <s:textfield name="eventLocation" id="eventLocation" cssClass="inputTextBlue" onchange="return eventsLengthValidator(this,'resultMessage');" />
                                                                    </td> 
                                                                    <td class="fieldLabel"><div id="timeZoneLabelId" >Select&nbsp;TimeZone:</div></td>  
                                                                    <td ><div id="timeZoneTextId"><s:select id="timeZone"  name="timeZone"  list="{'EST','IST','MST','CST','PST'}" cssClass="inputSelect" /></div></td>


                                                                </tr>
                                                                <tr id="transportationTr" >
                                                                    <td class="fieldLabel" valign="top">Transportation:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td colspan="3" valign="top"><s:textarea rows="2" cols="65" name="transportation" cssClass="inputTextareaOverlay1" onchange="return eventsLengthValidator(this,'resultMessage');" id="transportation" /></td>
                                                                </tr>

                                                                <tr id="eventDescriptionTr">
                                                                    <td class="fieldLabel" valign="top">Event&nbsp;Description:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td colspan="3" valign="top"><s:textarea rows="5" cols="65" name="eventDescription" cssClass="inputTextareaOverlay1" onchange="checkDoubleQuotes(this);return eventsLengthValidator(this,'resultMessage');" id="eventDescription" />

                                                                        <font class="fieldLabel">
                                                                            <br> Please include p tag to every paragraph like &lt;p class='methodText'&gt;...&lt;/p&gt;...&lt;p class='methodText'&gt;...&lt;/p&gt;
                                                                        </font>




                                                                    </td>
                                                                </tr>




                                                                <tr id="createdTr" style="display: none">
                                                                    <td class="fieldLabel" valign="top">CreatedBy:<FONT color="red"  ><em>*</em></FONT> </td>
                                                                    <td><FONT color="green"  ><span id="createdBy"></span></FONT></td>
                                                                    <td class="fieldLabel" valign="top">CreatedDate:<FONT color="red"  ><em>*</em></FONT> </td>
                                                                    <td><FONT color="green"  ><span id="createdDate"></span></FONT></td>
                                                                </tr>
                                                                <tr id="addTr" style="display: none"> 


                                                                    <td  align="center" >
                                                                        <input type="button" value="Save" onclick="return doAddQmeetEventPost();" class="buttonBg" id="addButton"/> 



                                                                        <s:reset cssClass="buttonBg"  align="right" value="Reset" />

                                                                    </td>
                                                                </tr> <tr id="editTr" style="display: none"> 
                                                                    <td  align="center" >
                                                                        <input type="button" value="Update" onclick="return doUpdateQmeetEventPost();" class="buttonBg" id="updateButton"/> 
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </s:form>    
                                        </div>


                                        <s:form action="internalQMeetEventSearch.action" id="frmQMeetDBGrid" name="frmQMeetDBGrid" theme="simple"> 
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
                                                                    <td ><s:select  id="status"  name="status"  list="{'Active','InActive','Completed'}" cssClass="inputSelect"  disabled="False"/>
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
                                                                        <s:if test="#session.roleName == 'WebAdmin'">
                                                                            <input type="button" class="buttonBg"  align="right"  value="Add" onclick="addQmeetEventPost();" /> 
                                                                        </s:if>
                                                                        
                                                                        <s:submit name="search" value="Search" cssClass="buttonBg"/>
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </td>
                                                    </tr>




                                                    <s:if test="#session.qmeetList != null"> 
                                                        <tr>
                                                            <td>
                                                                <table id="results" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='90%' align="center">

                                                                    <%java.util.List mainList = (java.util.List) session.getAttribute("qmeetList");
                                                                        if (mainList.size() > 0) {


                                                                    %>

                                                                    <tr class="gridHeader">

                                                                        <th>EventTitle</th>
                                                                        <!-- <th>EventDesc</th> -->
                                                                        
                                                                        <th>EventDate</th>
                                                                        <th>Location</th>

                                                                        <th>Status</th>
                                                                        <th>RSVPs</th>
                                                                        

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


                                                                            <s:if test="#session.roleName == 'WebAdmin'">
                                                                                <a style="color:#C00067;" href="javascript:editQmeetEventPost('<%=subList.get("event_id")%>');">
                                                                                     <%
                                                                                    out.println(subList.get("event_title"));
                                                                                %></a>
                                                                                </s:if><s:else>
                                                                                     <%
                                                                                    out.println(subList.get("event_title"));
                                                                                %>
                                                                                </s:else>
                                                                            
                                                                               
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
                                                                                out.println(subList.get("STATUS"));

                                                                            %>
                                                                        </td>
                                                                        <td class="title" align="center">
                                                                           <a style="color:#C00067;" href="javascript:getQmeetAttendees('<%=subList.get("event_id")%>');">
                                                                                            <img src="../../includes/images/go_21x21.gif"/>
                                                                                        </a>
                                                                        </td>
                                                                      

                                                                       </tr><%

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

                                                        <% }
                                                        %>
                                                    </s:if>



                                                    <script type="text/JavaScript">
                                                        var cal1 = new CalendarTime(document.forms['frmQMeetDBGrid'].elements['createdDateFrom']);
                                                        cal1.year_scroll = true;
                                                        cal1.time_comp = false;

                                                        var cal2 = new CalendarTime(document.forms['frmQMeetDBGrid'].elements['createdDateTo']);
                                                        cal2.year_scroll = true;
                                                        cal2.time_comp = false;

                                                        var selectcal1 = new CalendarTime(document.forms['eventForm'].elements['selectDateFrom']);
                                                        selectcal1.year_scroll = true;
                                                        selectcal1.time_comp = false;
                                               
                                                    </script>                            
                                                    <script type="text/javascript">
                                                        var qmeetPager = new ReviewPager('results', 10); 
                                                        qmeetPager.init(); 
                                                        qmeetPager.showPageNav('qmeetPager', 'pageNavPosition'); 
                                                        qmeetPager.showPage(1);
                                                    </script>      
                                                </table>
                                            </div>    
                                        </s:form>  


                                        <%--  </sx:div > --%>
                                    </div>  
                                        <s:if test="#session.roleName == 'WebAdmin'">
                                    <div id="ieeTab" class="tabcontent" >
                                        <div id="overlayforIeeTab" class="overlay"></div> 
                                        <div id="specialBoxforIeeTab" class="specialBox">
                                            <s:form theme="simple" align="center" name="ieeEventForm" id="ieeEventForm">

                                                <s:hidden name="event_idforIee" id="event_idforIee"/>

                                                <table align="center" border="0" cellspacing="0" style="width:100%;" >
                                                    <tr>                               
                                                        <td colspan="2" style="background-color: #288AD1" >
                                                            <h3 style="color:darkblue;" align="left">
                                                                <span id="headerLabelForIee"></span>


                                                            </h3></td>
                                                        <td colspan="2" style="background-color: #288AD1" align="right">

                                                            <a href="#" onmousedown="toggleIEEOverlay()" >
                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> 

                                                            </a>  

                                                        </td></tr>
                                                    <tr>
                                                        <td colspan="4">
                                                            <div id="loadIee" style="color: green;display: none;">Loading..</div>
                                                            <div id="ieeResultMessage"></div>
                                                        </td></tr>
                                                    <tr style="display: none" id="depotUrlTr">
                                                        <td  class="fieldLabel">Redirection&nbsp;Link : </td>
                                                        <td colspan="3"><span id="depotLink"></span></td>
                                                    </tr>

                                                      
                                                    <tr><td colspan="4">
                                                            <table style="width:80%;" align="center" border="0">

                                                                <tr>
                                                                    <td align="left" class="fieldLabel" >Upcoming&nbsp;Page&nbsp;Title:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td colspan="3">
                                                                        <s:textfield name="ieeEventtitle" id="ieeEventtitle" cssClass="inputTextBlueLargeAccount" onchange="return eventsLengthValidator(this,'ieeResultMessage');" />
                                                                    </td> 
                                                                </tr>

                                                                <tr >
                                                                    <s:hidden name="tempIeeEventDate" id="tempIeeEventDate"/>
                                                                    <s:hidden name="currentDateIee" id="currentDateIee"/>
                                                                    <td class="fieldLabel"><span id="fromDateLabelId">Event&nbsp;Date&nbsp;:</span></td>
                                                                    <td><s:textfield name="ieeSelectDateFrom" id="ieeSelectDateFrom" cssClass="inputTextBlue" onchange="checkEventDate();validateTimestamp(this);"/>
                                                                        <a href="javascript:selectcal2.popup();">
                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif"
                                                                                 width="20" height="20" border="0"></a>
                                                                    </td> 
                                                                    <td  class="fieldLabel" >Status:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td ><s:select id="ieeEventStatus"  name="ieeEventStatus"  list="{'Active','InActive','Completed'}" cssClass="inputSelect" onchange="getVideoTr(this);"/>
                                                                    </td>

                                                                </tr>
                                                                <tr id="timeTr" >

                                                                    <td class="fieldLabel">Select&nbsp;Start&nbsp;Time:</td>
                                                                    <td><s:select id="ieeStartTime"  name="ieeStartTime"  list="timeList" cssClass="inputSelectGender" />
                                                                        <s:select id="ieeMidDayFrom"  name="ieeMidDayFrom"  list="{'AM','PM'}" cssClass="inputSelectSmall" />
                                                                    </td>
                                                                    <td class="fieldLabel">Select&nbsp;End&nbsp;Time:</td>
                                                                    <td><s:select id="ieeEndTime"  name="ieeEndTime"  list="timeList" cssClass="inputSelectGender" />
                                                                        <s:select id="ieeMidDayTo"  name="ieeMidDayTo"  list="{'AM','PM'}" cssClass="inputSelectSmall" />
                                                                    </td>

                                                                </tr>  
                                                                <tr> <td align="left" class="fieldLabel" >Location:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td>
                                                                        <s:textfield name="ieeEventLocation" id="ieeEventLocation" cssClass="inputTextBlue" onchange="return eventsLengthValidator(this,'ieeResultMessage');" />
                                                                    </td> 
                                                                    <td class="fieldLabel"><div id="timeZoneLabelId" >Select&nbsp;TimeZone:</div></td>  
                                                                    <td ><div id="timeZoneTextId"><s:select id="ieeTimeZone"  name="ieeTimeZone"  list="{'EST','IST','MST','CST','PST'}" cssClass="inputSelect" /></div></td>


                                                                </tr>
                                                                <tr id="createdTrIee" style="display: none">
                                                                    <td class="fieldLabel" valign="top">CreatedBy:<FONT color="red"  ><em>*</em></FONT> </td>
                                                                    <td><FONT color="green"  ><span id="createdByIEE"></span></FONT></td>
                                                                    <td class="fieldLabel" valign="top">CreatedDate:<FONT color="red"  ><em>*</em></FONT> </td>
                                                                    <td><FONT color="green"  ><span id="createdDateIEE"></span></FONT></td>
                                                                </tr>
                                                                <tr id="addTrIee" style="display: none;"> 

                                                                    <td  align="right" colspan="4">
                                                                        <input type="button" value="Save" onclick="return doAddIEEEventPost();" class="buttonBg" id="addButton"/> 
                                                                        <s:reset cssClass="buttonBg"  align="right" value="Reset" />

                                                                    </td>
                                                                </tr> <tr id="editTrIee" style="display: none;"> 
                                                                    <td  align="right" colspan="4" >
                                                                        <input type="button" value="Update" onclick="return doUpdateIEEEventPost();" class="buttonBg" id="updateButton"/> 
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </s:form>    
                                        </div>


                                        <s:form action="internalIeeEventSearch.action" id="frmIeeDBGrid" name="frmIeeDBGrid" theme="simple"> 
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
                                                                    <td><s:textfield name="ieeCreatedDateFrom" id="ieeCreatedDateFrom" cssClass="inputTextBlue" onchange="validateTimestamp(this);"/>
                                                                        <a href="javascript:cal3.popup();">
                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif"
                                                                                 width="20" height="20" border="0"></a>
                                                                    </td>
                                                                    <td class="fieldLabel">To:<FONT color="red" SIZE="0.5"><em>*</em></FONT></td>
                                                                    <td>
                                                                        <s:textfield name="ieeCreatedDateTo"  id="ieeCreatedDateTo" cssClass="inputTextBlue" onchange="validateTimestamp(this);"/>
                                                                        <a href="javascript:cal4.popup();">
                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif"
                                                                                 width="20" height="20" border="0"></a>
                                                                    </td>

                                                                </tr>
                                                                <tr>


                                                                    <td class="fieldLabel">Event&nbsp;Title :</td>
                                                                    <td>
                                                                        <s:textfield name="ieeTitle" id="ieeTitle" cssClass="inputTextBlue" theme="simple"/>
                                                                    </td>
                                                                    <td class="fieldLabel">Status:</td>
                                                                    <%--    <td ><s:select headerKey="" headerValue="--Please Select--" id="status"  name="status"  list="{'Active','InActive'}" cssClass="inputSelect"  disabled="False"/> --%>
                                                                    <td ><s:select  id="ieeStatus"  name="ieeStatus"  list="{'Active','InActive','Completed'}" cssClass="inputSelect"  disabled="False"/>
                                                                </tr>
                                                               


                                                                <tr>

                                                                    <td></td>
                                                                    <td colspan="2"></td>
                                                                    <td>
                                                                        <input type="button" class="buttonBg"  align="right"  value="Add" onclick="addIeeEventPost();" /> 
                                                                        <s:submit name="search" value="Search" cssClass="buttonBg"/>
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </td>
                                                    </tr>




                                                    <s:if test="#session.ieeEventslist != null"> 
                                                        <tr>
                                                            <td>
                                                                <table id="ieeResults" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='90%' align="center">

                                                                    <%java.util.List mainList1 = (java.util.List) session.getAttribute("ieeEventslist");
                                                                        if (mainList1.size() > 0) {


                                                                    %>

                                                                    <tr class="gridHeader">

                                                                        <th>EventTitle</th>
                                                                        
                                                                        
                                                                        <th>EventDate</th>
                                                                        <th>Location</th>

                                                                        <th>Status</th>
                                                                        

                                                                    </tr>
                                                                    <%

                                                                        for (int i = 0; i < mainList1.size(); i++) {
                                                                    %>
                                                                    <tr CLASS="gridRowEven">
                                                                        <%
                                                                            //java.util.List subList = (java.util.List)mainList.get(i);
                                                                            java.util.Map subList = (java.util.Map) mainList1.get(i);
                                                                            //  for (int j = 0; j < subList.size(); j++) {

                                                                        %>
                                                                        <td class="title">



                                                                            <a style="color:#C00067;" href="javascript:editIEEEventPost('<%=subList.get("event_id")%>');">
                                                                                <%
                                                                                    out.println(subList.get("event_title"));
                                                                                %></a>
                                                                        </td>    
                                                                        
                                                                        <td class="title">
                                                                            <%
                                                                                out.println(subList.get("event_startdate").toString().split("\\ ")[0]);
                                                                            %>
                                                                        </td>
                                                                       
                                                                         <td class="title">
                                                                            <%
                                                                                out.println(subList.get("location"));

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

                                                            if (mainList1.size() != 0) {
                                                        %>

                                                        <tr>

                                                            <td align="right" colspan="4" style="background-color:white;" >
                                                                <div align="right" id="ieepageNavPosition">hello</div>
                                                            </td>
                                                        </tr> 

                                                        <% }
                                                        %>
                                                    </s:if>



                                                    <script type="text/JavaScript">
                                                        var cal3 = new CalendarTime(document.forms['frmIeeDBGrid'].elements['ieeCreatedDateFrom']);
                                                        cal3.year_scroll = true;
                                                        cal3.time_comp = false;

                                                        var cal4 = new CalendarTime(document.forms['frmIeeDBGrid'].elements['ieeCreatedDateTo']);
                                                        cal4.year_scroll = true;
                                                        cal4.time_comp = false;

                                                        var selectcal2 = new CalendarTime(document.forms['ieeEventForm'].elements['ieeSelectDateFrom']);
                                                        selectcal2.year_scroll = true;
                                                        selectcal2.time_comp = false;
                                               
                                                    </script>                            
                                                    <script type="text/javascript">
                                                        var ieePager = new ReviewPager('ieeResults', 10); 
                                                        ieePager.init(); 
                                                        ieePager.showPageNav('ieePager', 'ieepageNavPosition'); 
                                                        ieePager.showPage(1);
                                                    </script>      
                                                </table>
                                            </div>    
                                        </s:form>  


                                        
                                    </div>


                                    <!-- Internal Webinars Tab Start -->
                                    <div id="internalWebinarsTab" class="tabcontent" >

                                        <div id="overlayIw" class="overlay"></div> 
                                        <div id="specialBoxIw" class="specialBox" style="top: 38px;">
                                            <s:form theme="simple" align="center" name="internalWebinarForm" id="internalWebinarForm">

                                                <s:hidden name="iwevent_id" id="iwevent_id"/>
                                                <s:hidden name="iwseriesId" id="iwseriesId"/>
                                                <table align="center" border="0" cellspacing="0" style="width:100%;" >
                                                    <tr>                               
                                                        <td colspan="2" style="background-color: #288AD1" >
                                                            <h3 style="color:darkblue;" align="left">
                                                                <span id="iwheaderLabel"></span>


                                                            </h3></td>
                                                        <td colspan="2" style="background-color: #288AD1" align="right">

                                                            <a href="#" onmousedown="closeInternalWebinarOverlay()" >
                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> 

                                                            </a>  

                                                        </td></tr>
                                                    <tr>
                                                        <td colspan="4">
                                                            <div id="iwload" style="color: green;display: none;">Loading..</div>
                                                            <div id="iwresultMessage"></div>
                                                        </td>
                                                    <tr style="display: none" id="iwdepotUrlTr">
                                                        <td  class="fieldLabel">Redirection&nbsp;Link : </td>
                                                        <td colspan="3"><span id="iwdepotLink"></span></td>
                                                    </tr>
                                                    <tr style="display: none" id="iwbeforeUrlTr">
                                                        <td  class="fieldLabel">Before&nbsp;Page&nbsp;Link : </td>
                                                        <td colspan="3"><span id="iwbeforeLink"></span></td>
                                                    </tr><tr style="display: none" id="iwafterUrlTr">
                                                        <td  class="fieldLabel">After&nbsp;Page&nbsp;Link : </td>
                                                        <td colspan="3"><span id="iwafterLink"></span></td>
                                                    </tr>



                                                    <tr><td colspan="4">
                                                            <table style="width:80%;" align="center">


                                                                <tr>
                                                                    
                                                                    <td align="left" class="fieldLabel" >Upcoming&nbsp;Page&nbsp;Title:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td colspan="3">
                                                                        <s:textfield name="iweventtitle" id="iweventtitle" cssClass="inputTextBlueLargeAccount" onchange="return eventsLengthValidator(this,'iwresultMessage');" value="Internal Webinar :"/>
                                                                    </td> 
                                                                </tr>
                                                                <tr >
                                                                    <td align="left" class="fieldLabel" >Page&nbsp;Title:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td colspan="3">
                                                                        <s:textfield name="iweventBoldtitle" id="iweventBoldtitle" cssClass="inputTextBlueLargeAccount" onchange="return eventsLengthValidator(this,'iwresultMessage');" />
                                                                    </td> 
                                                                </tr>


                                                                <tr >
                                                                    <s:hidden name="iwtempEventDate" id="iwtempEventDate"/>
                                                                    <s:hidden name="iwcurrentDate" id="iwcurrentDate"/>
                                                                    <td class="fieldLabel">Event&nbsp;Date&nbsp;:</td>
                                                                    <td><s:textfield name="iwinternalWebinarDateFrom" id="iwinternalWebinarDateFrom" cssClass="inputTextBlue" onchange="checkEventDate();validateTimestamp(this);"/>
                                                                        <a href="javascript:iwselectcal2.popup();">
                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif"
                                                                                 width="20" height="20" border="0"></a>
                                                                    </td> 
                                                                    <td  class="fieldLabel" >Status:<FONT color="red"  ><em>*</em></FONT></td>

                                                                    <td ><s:select id="iweventStatus"  name="iweventStatus"  list="{'Active','InActive','Completed','Published'}" cssClass="inputSelect" onchange="getVideoTr(this);"/>
                                                                    </td>
                                                                </tr>
                                                                <tr >

                                                                    <td class="fieldLabel">Select&nbsp;Start&nbsp;Time:</td>
                                                                    <td><s:select id="iwstartTime"  name="iwstartTime"  list="timeList" cssClass="inputSelectGender" />
                                                                        <s:select id="iwmidDayFrom"  name="iwmidDayFrom"  list="{'AM','PM'}" cssClass="inputSelectSmall" />
                                                                    </td>
                                                                    <td class="fieldLabel">Select&nbsp;End&nbsp;Time:</td>
                                                                    <td><s:select id="iwendTime"  name="iwendTime"  list="timeList" cssClass="inputSelectGender" /><s:select id="iwmidDayTo"  name="iwmidDayTo"  list="{'AM','PM'}" cssClass="inputSelectSmall" />
                                                                    </td>

                                                                </tr>  
                                                                <tr> <td align="left" class="fieldLabel" >Location:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td>
                                                                        <s:textfield name="iweventLocation" id="iweventLocation" cssClass="inputTextBlue" onchange="return eventsLengthValidator(this,'iwresultMessage');" />
                                                                    </td> 
                                                                    <td class="fieldLabel"><div >Select&nbsp;TimeZone:</div></td>  
                                                                    <td ><div ><s:select id="iwtimeZone"  name="iwtimeZone"  list="{'EST','IST','MST','CST','PST'}" cssClass="inputSelect" /></div></td>


                                                                </tr>


                                                                <tr  >
                                                                    <td class="fieldLabel" valign="top">Event&nbsp;Description:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td colspan="3" valign="top"><s:textarea rows="5" cols="65" name="iweventDescription" cssClass="inputTextareaOverlay1" onchange="checkDoubleQuotes(this);return eventsLengthValidator(this,'iwresultMessage');" id="iweventDescription" />

                                                                        <font class="fieldLabel">
                                                                            <br> Please include p tag to every paragraph like &lt;p class='methodText'&gt;...&lt;/p&gt;...&lt;p class='methodText'&gt;...&lt;/p&gt;
                                                                        </font>

                                                                    </td>
                                                                </tr>

                                                                <tr >
                                                                    <td align="left" class="fieldLabel" >Event&nbsp;Registration&nbsp;Link:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td colspan="3">
                                                                        <s:textfield name="iweventRegistrationLink" id="iweventRegistrationLink" cssClass="inputTextBlueLargeAccount" onchange="isValidUrl(this,'iwresultMessage');" />
                                                                    </td> 
                                                                </tr>

                                                                <tr >
                                                                    <td align="left" class="fieldLabel" >Primary&nbsp;Track:<FONT color="red"  ><em>*</em></FONT></td>  
                                                                    <td>

                                                                        <s:select headerKey="" headerValue="--Please Select--" id="iwprimaryTrack"  name="iwprimaryTrack"  list="trackNamesMap" cssClass="inputSelect" />

                                                                    </td>
                                                                    <td align="left" class="fieldLabel" >Secondary&nbsp;Track:</td>  
                                                                    <td >

                                                                        <s:select headerKey="" headerValue="--Please Select--" id="iwsecondaryTrack"  name="iwsecondaryTrack"  list="trackNamesMap" cssClass="inputSelect" />

                                                                    </td>
                                                                </tr>
                                                                <tr  >
                                                                    <td align="left" class="fieldLabel" >Department:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td colspan="3"><s:select headerKey="" headerValue="--Please Select--" id="iweventDepartment"  name="iweventDepartment"  list="{'Business Development','Marketing','Recruitment','Other','Development','Operations'}" cssClass="inputSelect" /></td>
                                                                </tr>

                                                                <tr >
                                                                    <td align="left" class="fieldLabel" >ContactUs Email:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td colspan="3">
                                                                        <s:textfield name="iwcontactUsEmail" id="iwcontactUsEmail" cssClass="inputTextBlueLargeAccount" onchange="checkEventsEmail(this,'iwresultMessage')" />
                                                                    </td> 
                                                                </tr>

                                                                <tr id="iwcreatedTr" style="display: none">
                                                                    <td class="fieldLabel" valign="top">CreatedBy:<FONT color="red"  ><em>*</em></FONT> </td>
                                                                    <td><FONT color="green"  ><span id="iwcreatedBy"></span></FONT></td>
                                                                    <td class="fieldLabel" valign="top">CreatedDate:<FONT color="red"  ><em>*</em></FONT> </td>
                                                                    <td><FONT color="green"  ><span id="iwcreatedDate"></span></FONT></td>
                                                                </tr>
                                                                <tr id="iwaddTr" style="display: none"> 
                                                                    <td  align="center" >
                                                                        <input type="button" value="Save" onclick="return doAddInternalWebinarEventPost();" class="buttonBg" /> 

                                                                        <s:reset cssClass="buttonBg"  align="right" value="Reset" />
                                                                    </td>
                                                                </tr> <tr id="iweditTr" style="display: none"> 
                                                                    <td  align="center" >
                                                                        <input type="button" value="Update" onclick="return doUpdateInternalWebinarEventPost();" class="buttonBg" /> 
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

                                                            <a href="#" onmousedown="closeCompletedWebinarToogle('frmInternalWebinarDBGrid')" >
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
                                                          
                                                            <FONT color="green" size="2px"><span id="webinarEventUpcomingPageLabel"></span></FONT>
                                                        </td> 
                                                    </tr>
                                                    <tr style="display: none" id="webinarEventBoldTr">
                                                        <td align="left" class="fieldLabel" >Page&nbsp;Title:<FONT color="red"  ><em>*</em></FONT></td>
                                                        <td colspan="3">
                                                            <FONT color="green" size="2px"><span id="webinarEventTitlePageLabel"></span></FONT>
                                                            
                                                        </td> 
                                                    </tr>
                                                    <tr id="webinarEventAfterDescriptionTr" style="display: none">
                                                        <td class="fieldLabel" valign="top">Event&nbsp;After&nbsp;Description:<FONT color="red"  ><em>*</em></FONT></td>
                                                        <td colspan="3" valign="top"><s:textarea rows="5" cols="65" name="webinarEventAfterDescription" cssClass="inputTextareaOverlay1" onchange="checkDoubleQuotes(this);return eventsLengthValidator(this,'resultMessageAfterVideo');" id="webinarEventAfterDescription"/>

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
                                                           

                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td  class="fieldLabel" >Status:<FONT color="red"  ><em>*</em></FONT></td>
                                                        <td >
                                                           
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
                                                            <input type="button" value="Update" style="margin-left: 500px;" onclick="return doUpdateWebinarAfterEventPost('I');" class="buttonBg" id="updateButton"/> 
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
                                              

                                        <s:form action="internalWebinarEventSearch.action" id="frmInternalWebinarDBGrid" name="frmInternalWebinarDBGrid" theme="simple"> 
                                            <div style="width:840px;"> 
                                                <s:hidden name="frmName" id="frmName" value="frmInternalWebinarDBGrid"/>
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
                                                                    <td><s:textfield name="internalWebinarCreatedDateFrom" id="internalWebinarCreatedDateFrom" cssClass="inputTextBlue" onchange="validateTimestamp(this);"/>
                                                                        <a href="javascript:iwcal3.popup();">
                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif"
                                                                                 width="20" height="20" border="0"></a>
                                                                    </td>
                                                                    <td class="fieldLabel">To:<FONT color="red" SIZE="0.5"><em>*</em></FONT></td>
                                                                    <td>
                                                                        <s:textfield name="internalWebinarCreatedDateTo"  id="internalWebinarCreatedDateTo" cssClass="inputTextBlue" onchange="validateTimestamp(this);"/>
                                                                        <a href="javascript:iwcal4.popup();">
                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif"
                                                                                 width="20" height="20" border="0"></a>
                                                                    </td>

                                                                </tr>
                                                                <tr>


                                                                    <td class="fieldLabel">Event&nbsp;Title :</td>
                                                                    <td>
                                                                        <s:textfield name="internalWebinarTitle" id="internalWebinarTitle" cssClass="inputTextBlue" theme="simple"/>
                                                                    </td>
                                                                    <td class="fieldLabel">Status:</td>
                                                                    
                                                                    <td ><s:select  id="internalWebinarStatus"  name="internalWebinarStatus"  list="{'Active','InActive','Completed','Published'}" cssClass="inputSelect"  disabled="False"/>
                                                                </tr>
                                                              


                                                                <tr>

                                                                    <td></td>
                                                                    <td colspan="2"></td>
                                                                    <td>
                                                                        <input type="button" class="buttonBg"  align="right"  value="Add" onclick="addInternalWebinarEventPost();" /> 
                                                                        <s:submit name="search" value="Search" cssClass="buttonBg"/>
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </td>
                                                    </tr>




                                                    <s:if test="#session.internalWebinarEventslist != null"> 
                                                        <tr>
                                                            <td>
                                                                <table id="internalWebinarResults" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='90%' align="center">

                                                                    <%java.util.List internalWebinarEventslist = (java.util.List) session.getAttribute("internalWebinarEventslist");
                                                                        if (internalWebinarEventslist.size() > 0) {


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

                                                                        for (int i = 0; i < internalWebinarEventslist.size(); i++) {
                                                                    %>
                                                                    <tr CLASS="gridRowEven">
                                                                        <%
                                                                            //java.util.List subList = (java.util.List)mainList.get(i);
                                                                            java.util.Map subList = (java.util.Map) internalWebinarEventslist.get(i);
                                                                            //  for (int j = 0; j < subList.size(); j++) {

                                                                        %>
                                                                        <td class="title">



                                                                            <a style="color:#C00067;" href="javascript:editInternalWebinarEventPost('<%=subList.get("event_id")%>');">
                                                                                <%
                                                                                    out.println(subList.get("event_title"));
                                                                                %></a>
                                                                        </td>    
                                                                       
                                                                        <td class="title">
                                                                            <%
                                                                                out.println(subList.get("event_startdate").toString().split("\\ ")[0]);
                                                                            %>
                                                                        </td>
                                                                       


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

                                                            if (internalWebinarEventslist.size() != 0) {
                                                        %>

                                                        <tr>

                                                            <td align="right" colspan="4" style="background-color:white;" >
                                                                <div align="right" id="iwpageNavPosition">hello</div>
                                                            </td>
                                                        </tr> 

                                                        <% }
                                                        %>
                                                    </s:if><s:else>
                                                        <tr><td><br><br><br><br><br><br><br><br></td></tr>
                                                    </s:else>



                                                </table>
                                            </div>    
                                        </s:form>  

                                        <script type="text/JavaScript">
                                            var iwcal3 = new CalendarTime(document.forms['frmInternalWebinarDBGrid'].elements['internalWebinarCreatedDateFrom']);
                                            iwcal3.year_scroll = true;
                                            iwcal3.time_comp = false;

                                            var iwcal4 = new CalendarTime(document.forms['frmInternalWebinarDBGrid'].elements['internalWebinarCreatedDateTo']);
                                            iwcal4.year_scroll = true;
                                            iwcal4.time_comp = false;

                                            var iwselectcal2 = new CalendarTime(document.forms['internalWebinarForm'].elements['iwinternalWebinarDateFrom']);
                                            iwselectcal2.year_scroll = true;
                                            iwselectcal2.time_comp = false;
                                               
                                        </script>  

                                        <script type="text/javascript">
                                            var iwPager = new ReviewPager('internalWebinarResults', 10); 
                                            iwPager.init(); 
                                            iwPager.showPageNav('iwPager', 'iwpageNavPosition'); 
                                            iwPager.showPage(1);
                                        </script>  
                                        
                                        
                                        
                                        
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

                                                        <a href="#" onmousedown="closeSeriesToggleOverlay('I');" >
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
                                                             <input type="hidden" id="seriesType" name="seriesType" value="I"/>
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

                                    <!-- Internal Webinars Tab end -->
</s:if>
                                    <!-- Emeets Div Start -->
                                    <s:if test="#session.emeetPostingFlag == 1">

                                    <div id="executiveMeetsTab" class="tabcontent" >
                                        <%if (request.getAttribute("emeetStatusByDate") != null && !request.getAttribute("emeetStatusByDate").toString().equals("U")) {%>
                                        <!-- completed emeet details start-->
                                        <div id="exeMeetoverlay" ></div> 
                                        <div id="exeMeetspecialBox" >
                                            <s:form theme="simple"  align="center" name="frmExeMeet" id="frmExeMeet" >

                                                <s:hidden name="exeMeetingId" id="exeMeetingId"/>

                                                <s:hidden name="id" id="id"/>
                                                


                                                <table align="center" border="0" cellspacing="0" style="width:100%;" >
                                                    <tr>                               
                                                        <td colspan="2" style="background-color: #288AD1" >
                                                            <h3 style="color:darkblue;" align="left">
                                                                <span id="emeetHeaderLabel"></span>


                                                            </h3></td>
                                                        <td colspan="2" style="background-color: #288AD1" align="right">

                                                            <a href="#" onmousedown="toggleOverlayForExeMeet()" >
                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> 

                                                            </a>  

                                                        </td></tr>
                                                    <tr>
                                                        <td colspan="">
                                                            <div id="emeetLoad" style="color: green;display: none;">Loading..</div>
                                                            <div id="emeetResultMessage"></div>
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
                                                                        <FONT color="green"  >  <span id="emeetTimeZone"></span></FONT>


                                                                    </td>

                                                                </tr>

                                                                <tr>
                                                                    <td class="fieldLabel" >Registration&nbsp;Link&nbsp;: </td>
                                                                    <td colspan="3"><FONT color="green"  ><span id="registrationLinkForEMeet"></span></FONT></td>
                                                                </tr>
                                                                <tr >


                                                                    <td class="fieldLabel" >Video&nbsp;Link&nbsp;: </td>
                                                                    <td colspan="3"><s:textfield name="videoLinkForEMeet" id="videoLinkForEMeet" style="width : 490px;" cssClass="inputTextBlueLargeAccount" onchange="return isValidUrl(this,'emeetResultMessage');" /></td>
                                                                    
                                                                </tr>
                                                                <tr id="emeetCreatedTr" style="display: none">
                                                                    <td class="fieldLabel" valign="top">CreatedBy: </td>
                                                                    <td><FONT color="green"  ><span id="emeetCreatedBy"></span></FONT></td>
                                                                    <td class="fieldLabel" valign="top">CreatedDate: </td>
                                                                    <td><FONT color="green"  ><span id="emeetCreatedDate"></span></FONT></td>
                                                                </tr>
                                                                <tr id="emmetAddTr" style="display: none"> 
                                                                    <td></td><td></td><td></td>
                                                                   
                                                                    <td  align="center" >
                                                                        <input type="button" value="Save" onclick="return doAddInternalExecutiveMeeting();" class="buttonBg" id="emeetAddButton"/> 


                                                                      
                                                                        <s:reset cssClass="buttonBg"  align="right" value="Reset" />

                                                                    </td>
                                                                </tr> <tr id="emeetEditTr" style="display: none"> 

                                                                    <td  align="center" colspan="4"style="text-align: right;padding-right: 50px;">
                                                                        <input type="button" value="Update" onclick="return doUpdateInternalCompletedExeMeeting();" class="buttonBg" id="emeetUpdateButton"/> &nbsp;&nbsp;
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
                                                


                                                <table align="center" border="0" cellspacing="0" style="width:100%;" >
                                                    <tr>                               
                                                        <td colspan="2" style="background-color: #288AD1" >
                                                            <h3 style="color:darkblue;" align="left">
                                                                <span id="emeetHeaderLabel"></span>


                                                            </h3></td>
                                                        <td colspan="2" style="background-color: #288AD1" align="right">

                                                            <a href="#" onmousedown="toggleOverlayForExeMeet()" >
                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> 

                                                            </a>  

                                                        </td></tr>
                                                    <tr>
                                                        <td colspan="">
                                                            <div id="emeetLoad" style="color: green;display: none;">Loading..</div>
                                                            <div id="emeetResultMessage"></div>
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

                                                                      
                                                                        <s:select headerKey="-1" headerValue="--Please Select--" list="{'Global Practice Meet','Global Sales Meet'}" name="executiveMeetingType" id="executiveMeetingType" cssClass="inputSelect"/>
                                                                    </td> 
                                                                    <td  class="fieldLabel" >Month:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td>
                                                                       
                                                                        <s:select headerKey="-1" headerValue="--Please Select--" list="{'January','February','March','April','May','June','July','August','September','October','November','December'}" name="executiveMeetMonth" id="executiveMeetMonth" cssClass="inputSelect"/>
                                                                    </td> 
                                                                </tr>
                                                                <tr>
                                                                    <td class="fieldLabel">Date:<FONT color="red" SIZE="0.5"><em>*</em></FONT></td>
                                                                    <td>
                                                                        <s:textfield name="exeMeetcreatedDateTo"  id="exeMeetcreatedDateTo" cssClass="inputTextBlue" onchange="validateTimestamp(this);" readonly="true"/>
                                                                        <a href="javascript:emeetcal3.popup();">
                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif"
                                                                                 width="20" height="20" border="0"></a>
                                                                    </td>
                                                                    <td align="left" class="fieldLabel" >Status:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td  id="statusId">
                                                                     
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
                                                                        <s:select id="emeetTimeZone" name="emeetTimeZone"  list="{'EST','IST','MST','CST','PST'}" cssClass="inputSelectSmall" />
                                                                    </td>

                                                                </tr>

                                                               
                                                                <tr>


                                                                    <td class="fieldLabel" >Registration&nbsp;Link&nbsp;:<FONT color="red"  ><em>*</em></FONT> </td>
                                                                    <td colspan="3"><s:textfield name="registrationLinkForEMeet" id="registrationLinkForEMeet" style="width : 484px;" cssClass="inputTextBlueLargeAccount" onchange="return isValidUrl(this,'emeetResultMessage');"  /></td>
                                                                    
                                                                </tr>
                                                                <tr id="emeetCreatedTr" style="display: none">
                                                                    <td class="fieldLabel" valign="top">CreatedBy:<FONT color="red"  ><em>*</em></FONT> </td>
                                                                    <td><FONT color="green"  ><span id="emeetCreatedBy"></span></FONT></td>
                                                                    <td class="fieldLabel" valign="top">CreatedDate:<FONT color="red"  ><em>*</em></FONT> </td>
                                                                    <td><FONT color="green"  ><span id="emeetCreatedDate"></span></FONT></td>
                                                                </tr>
                                                                <tr id="emmetAddTr" style="display: none"> 
                                                                    <td></td><td></td><td></td>
                                                                   
                                                                    <td  align="left" style="padding-left: 36px;">
                                                                        <input type="button" value="Save" onclick="return doAddInternalExecutiveMeeting();" class="buttonBg" id="emeetAddButton"/> 


                                                                        
                                                                        <s:reset cssClass="buttonBg"  align="right" value="Reset" />

                                                                    </td>
                                                                </tr> <tr id="emeetEditTr" style="display: none"> 

                                                                    <td  align="center" colspan="4"style="text-align: right;padding-right: 76px;">
                                                                        <input type="button" value="Update" onclick="return doUpdateInternalExeMeeting();" class="buttonBg" id="emeetUpdateButton"/> &nbsp;&nbsp;
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </s:form>    
                                        </div>

                                        <%}%>
                                        <s:form action="internalEmeetSearch.action" id="frmEmeetDBGrid" name="frmEmeetDBGrid" theme="simple"> 
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
                                                                        <a href="javascript:emeetcal1.popup();">
                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif"
                                                                                 width="20" height="20" border="0"></a>
                                                                    </td>
                                                                    <td class="fieldLabel"> Date&nbsp;To:</td>
                                                                    <td><s:textfield name="emeetToDate" id="emeetToDate" cssClass="inputTextBlue" onchange="validateTimestamp(this);"/>
                                                                        <a href="javascript:emeetcal2.popup();">
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
                                                                       
                                                                        <s:select headerKey="" headerValue="--Please Select--" list="{'January','February','March','April','May','June','July','August','September','October','November','December'}" name="executiveMeetMonthSearch" id="executiveMeetMonthSearch" cssClass="inputSelect"/>
                                                                    </td> 
                                                                    <td></td>
                                                                    <td>
                                                                     
                                                                        <%if (request.getAttribute("emeetStatusByDate") == null || request.getAttribute("emeetStatusByDate").toString().equals("U")) {%>
                                                                        <input type="button" class="buttonBg"  align="right"  value="Add" onclick="addInternalExecutiveMeet();"/> 
                                                                        <%}%>

                                                                        <s:submit name="search" value="Search" cssClass="buttonBg"/>
                                                                      
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </td>
                                                    </tr>





                                                    <s:if test="#session.executiveMeetingsList != null"> 

                                                        <tr>
                                                            <td style="padding-top: 30px;">
                                                                <table id="emeetResults" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='90%' align="center">

                                                                    <%java.util.List emmetsList = (java.util.List) session.getAttribute("executiveMeetingsList");
                                                                        if (emmetsList.size() > 0) {


                                                                    %>

                                                                    <tr class="gridHeader">
                                                                        
                                                                        <th>Type</th>
                                                                        <th>Date</th>
                                                                        <th>Status</th>

                                                                        <%if (request.getAttribute("emeetStatusByDate") != null && request.getAttribute("emeetStatusByDate").toString().equals("U")) {%>

                                                                        <th width="8%">Services</th>
                                                                        <%}%>


                                                                        

                                                                    </tr>
                                                                    <%

                                                                        for (int i = 0; i < emmetsList.size(); i++) {
                                                                    %>
                                                                    <tr CLASS="gridRowEven">
                                                                        <%
                                                                            //java.util.List subList = (java.util.List)mainList.get(i);
                                                                            java.util.Map subList = (java.util.Map) emmetsList.get(i);
                                                                            //  for (int j = 0; j < subList.size(); j++) {

                                                                        %>
                                                                        
                                                                        
                                                                        <td class="title">

                                                                            <%

                                                                                String TYPE = (String) subList.get("TYPE");

                                                                                // System.out.println("TYPE-->"+TYPE);
                                                                                if (request.getAttribute("emeetStatusByDate").toString().equals("U")) {%>

                                                                            <a style="color:#C00067;" href="javascript:editInternalExeMeet('<%=subList.get("Id")%>');">
                                                                                <%} else {%><a style="color:#C00067;" href="javascript:editCompletedInternalExeMeet('<%=subList.get("Id")%>');">
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
                                                                        <%if (request.getAttribute("emeetStatusByDate") != null && request.getAttribute("emeetStatusByDate").toString().equals("U")) {%>
                                                                        <td class="title">
                                                                            <%if (!"Published".equals(subList.get("STATUS").toString())) {%>
                                                                            <a herf="#" class="round-button" title="Publish" onclick="doPublishInternalExeMeet(this,'<%=subList.get("Id")%>','<%=subList.get("STATUS")%>')">P</a>
                                                                            <%}%>
                                                                            <%if (!"Active".equals(subList.get("STATUS").toString())) {%>
                                                                            <a herf="#" class="round-button" title="Active" onclick="doActiveInternalExeMeet(this,'<%=subList.get("Id")%>','<%=subList.get("STATUS")%>')">A</a>
                                                                            <%}%>
                                                                            </a>
                                                                        </td>
                                                                        <%}%>
                                                                        


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

                                                            if (emmetsList.size() != 0) {
                                                        %>

                                                        <tr>

                                                            <td align="right" colspan="4" style="background-color:white;" >
                                                                <div align="right" id="emmetPageNavPosition">hello</div>
                                                            </td>
                                                        </tr> 

                                                        <%}
                                                        %>
                                                    </s:if>



                                                    <script type="text/JavaScript">
                                                        var emeetcal1 = new CalendarTime(document.forms['frmEmeetDBGrid'].elements['emeetFromDate']);
                                                        emeetcal1.year_scroll = true;
                                                        emeetcal1.time_comp = false;
                                                        
                                                        var emeetcal2 = new CalendarTime(document.forms['frmEmeetDBGrid'].elements['emeetToDate']);
                                                        emeetcal2.year_scroll = true;
                                                        emeetcal2.time_comp = false;
                                                 
                                                        var emeetcal3 = new CalendarTime(document.forms['frmExeMeet'].elements['exeMeetcreatedDateTo']);
                                                        emeetcal3.year_scroll = true;
                                                        emeetcal3.time_comp = false;

						
                                                            
                                                    </script>    


                                                    <script type="text/javascript">
                                                        var emeetPager = new ReviewPager('emeetResults', 10); 
                                                        emeetPager.init(); 
                                                        emeetPager.showPageNav('emeetPager', 'emmetPageNavPosition'); 
                                                        emeetPager.showPage(1);
                                                    </script>      
                                                </table>
                                            </div>    
                                        </s:form>  


                                        
                                    </div>  

                                    <!-- Emeets Div End -->
                                    
                                    
                                    
                                    <!-- Emeet Attendees start-->
                                    <div id="eMeetAttendeesTab" class="tabcontent" style="display: block;">
                                        <div id="executeMeetAttendeesoverlay" class="overlay"></div> 
                                        <div id="executeMeetAttendeesspecialBox" class="specialBox">
                                            <s:form theme="simple"  align="center" name="frmExeMeet" id="frmExeMeet" >

                                                <s:hidden name="executiveMeetingId" id="executiveMeetingId"/>

                                                <s:hidden name="eMeetAttendeeId" id="eMeetAttendeeId"/>

                                                <s:hidden name="eMeetAttendeeloginId" id="eMeetAttendeeloginId"/>
                                                <s:hidden name="previousAttendeeStatus" id="previousAttendeeStatus"/>


                                                <table align="center" border="0" cellspacing="0" style="width:100%;" >
                                                    <tr>                               
                                                        <td colspan="2" style="background-color: #288AD1" >
                                                            <h3 style="color:darkblue;" align="left">
                                                                <span id="headerLabelforexecuteMeetAttendees"></span>


                                                            </h3></td>
                                                        <td colspan="2" style="background-color: #288AD1" align="right">

                                                            <a href="#" onmousedown="toggleOverlayForExecuteMeetAttendees()" >
                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> 

                                                            </a>  

                                                        </td></tr>
                                                    <tr>
                                                        <td colspan="">
                                                            <div id="loadforexecuteMeetAttendees" style="color: green;display: none;">Loading..</div>
                                                            <div id="resultMessageforexecuteMeetAttendees"></div>
                                                        </td>
                                                    </tr>    
                                                    <tr><td colspan="">
                                                            <table style="width:100%;" align="center" border="0">
                                                                <tr>
                                                                    <td align="left" class="fieldLabel" >E-Mail:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td >
                                                                       
                                                                        <s:select headerKey="-1" headerValue="--Please Select--" list="activeEmployeeMap" name="executiveMeetingAttendeeEmail" id="executiveMeetingAttendeeEmail" cssClass="inputSelect"/>
                                                                        <span id="emailSpanId"></span>
                                                                    </td> 
                                                                    <td  class="fieldLabel" >Access&nbsp;Type:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td >
                                                                       
                                                                        <s:select headerKey="" headerValue="--Please Select--" id="executiveMeetAccessType"  name="executiveMeetAccessType" list="{'Global Practice Meet','Global Sales Meet','Both'}" cssClass="inputSelect"  disabled="False"/>
                                                                    



                                                                    </td> 

                                                                </tr>
                                                                <tr>
                                                                    <td align="left" class="fieldLabel" >Status:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td >

                                                                        
                                                                        <s:select list="{'Active','InActive'}" name="executiveMeetingAccessStatus" id="executiveMeetingAccessStatus" cssClass="inputSelect"/>
                                                                    </td> 
                                                                </tr>
                                                                <tr id="eMeetAtndCreatedTr">
                                                                    <td class="fieldLabel" valign="top">CreatedBy:<FONT color="red"  ><em>*</em></FONT> </td>
                                                                    <td><FONT color="green"  ><span id="createdByEmeetAttnd"></span></FONT></td>
                                                                    <td class="fieldLabel" valign="top">CreatedDate:<FONT color="red"  ><em>*</em></FONT> </td>
                                                                    <td><FONT color="green"  ><span id="createdDateEmeetAttnd"></span></FONT></td>
                                                                </tr>
                                                                <tr id="eMeetAtndAddTr" style="display: none"> 
                                                                    <td></td><td></td><td></td>
                                                                   
                                                                    <td  align="center" >
                                                                        <input type="button" value="Save" onclick="return doAddAttendeesforExecutiveMeetAttendees();" class="buttonBg" id="addButtonEmeetAttendee"/> 


                                                                      
                                                                        <s:reset cssClass="buttonBg"  align="right" value="Reset" />

                                                                    </td>
                                                                </tr> <tr id="eMeetAtndEditTr" style="display: none"> 

                                                                    <td  align="center" colspan="4"style="text-align: right;padding-right: 50px;">
                                                                        <input type="button" value="Update" onclick="return doUpdateAttendeesDetailsforExecitiveMeeting();" class="buttonBg" id="updateButtonEmeetAttendee"/> &nbsp;&nbsp;
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </s:form>    
                                        </div>


                                        <s:form action="executeMeetAttendeesSearch.action" id="frmDBGrid" name="frmDBGrid" theme="simple"> 
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

                                                                    <td class="fieldLabel"> E-Mail:</td>
                                                                    <td><s:textfield name="emeetAttendeesEmail" id="emeetAttendeesEmail" cssClass="inputTextBlue" /></td>
                                                                    <td class="fieldLabel">Access:</td>
                                                                    <td >
                                                                       
                                                                        <s:select headerKey="" headerValue="--Please Select--" id="emeetAccessType"  name="emeetAccessType" list="{'Global Practice Meet','Global Sales Meet','Both'}" cssClass="inputSelect"  disabled="False"/></td>
                                                                </tr><tr>
                                                                    <td align="left" class="fieldLabel" >Status:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td >

                                                                        
                                                                        <s:select list="{'Active','InActive'}" name="emeetMeetingAccessStatus" id="emeetMeetingAccessStatus" cssClass="inputSelect"/>
                                                                    </td><td></td>
                                                                    <td>
                                                                       
                                                                        <input type="button" class="buttonBg"  align="right"  value="Add" onclick="addAttendeesForExecutiveMeet();" /> 
                                                                        <s:submit name="search" value="Search" cssClass="buttonBg"/>
                                                                       
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </td>
                                                    </tr>




                                                    <s:if test="#session.executiveMeetingsAttendeeslist != null"> 

                                                        <tr>
                                                            <td style="padding-top: 30px;">
                                                                <table id="executiveMeetingsAttendeesResults" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='90%' align="center">

                                                                    <%java.util.List executiveMeetingsAttendeesMainList = (java.util.List) session.getAttribute("executiveMeetingsAttendeeslist");
                                                                        if (executiveMeetingsAttendeesMainList.size() > 0) {


                                                                    %>

                                                                    <tr class="gridHeader">
                                                                        <th>Employee Name</th>
                                                                        <th>E-Mail</th>
                                                                        <th>Designation</th>
                                                                        <th>Access Type</th>
                                                                        

                                                                    </tr>
                                                                    <%

                                                                        for (int i = 0; i < executiveMeetingsAttendeesMainList.size(); i++) {
                                                                    %>
                                                                    <tr CLASS="gridRowEven">
                                                                        <%
                                                                            //java.util.List subList = (java.util.List)mainList.get(i);
                                                                            java.util.Map subList = (java.util.Map) executiveMeetingsAttendeesMainList.get(i);
                                                                            //  for (int j = 0; j < subList.size(); j++) {

                                                                        %>
                                                                        <td class="title">
                                                                            <a style="color:#C00067;" href="javascript:editExecuteMeetAttendees('<%=subList.get("Id")%>');">
                                                                                <%

                                                                                    out.println(subList.get("EmpName"));

                                                                                %>

                                                                            </a>  </td> 
                                                                        <td>
                                                                            <%
                                                                                out.println(subList.get("Email"));

                                                                            %>
                                                                        </td>       
                                                                        <td class="title">
                                                                            <%
                                                                                out.println(subList.get("TitleTypeId"));

                                                                            %>
                                                                        </td>         
                                                                        <td class="title">
                                                                            <%
                                                                                out.println(subList.get("AccessType"));

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

                                                            if (executiveMeetingsAttendeesMainList.size() != 0) {
                                                        %>

                                                        <tr>

                                                            <td align="right" colspan="4" style="background-color:white;" >
                                                                <div align="right" id="eattendeepageNavPosition">hello</div>
                                                            </td>
                                                        </tr> 

                                                        <%}
                                                        %>
                                                    </s:if>




                                                    <script type="text/javascript">
                                                        var eattendeePager = new ReviewPager('executiveMeetingsAttendeesResults', 10); 
                                                        eattendeePager.init(); 
                                                        eattendeePager.showPageNav('eattendeePager', 'eattendeepageNavPosition'); 
                                                        eattendeePager.showPage(1);
                                                    </script>      
                                                </table>
                                            </div>    
                                        </s:form>  


                                        
                                    </div>  
                                    <!-- Emeet Attendees End -->
                                    </s:if>
                                    
                                    

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
		getWebinarSeriesList('I');

		});
		</script>

    </body>
</html>

