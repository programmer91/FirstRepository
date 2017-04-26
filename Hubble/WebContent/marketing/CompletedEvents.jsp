<%-- 
    Document   : CompletedEvents
    Created on : Jul 27, 2015, 6:13:57 PM
    Author     : miracle
--%>

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
      
      <script>
            function eventSpeakerPopup(url) {
                //newwindow=window.open(url,'name','height=350,width=200,top=200,left=250');
                newwindow=window.open(url,'name','height=300,width=350,top=200,left=250');
                if (window.focus) {newwindow.focus()}
            }
        </script>
     
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/marketing/EventsPosting.js"/>"></script>  
   
      <s:include value="/includes/template/headerScript.html"/>
    </head>
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
                                       
                                        <li ><a href="#" class="selected" rel="issuesTab">Completed&nbsp;Events&nbsp;List</a></li>
                                       
                                 <%--   <%}else{%>
                                    <li ><a href="#" class="selected" rel="IssuesSearchTab">Tasks Search</a></li>
                                     <% }%> --%>
                                    
                                </ul>
                                 
                                
                                <%-- <sx:tabbedpanel id="IssuesPanel" cssStyle="width: 845px; height: 500px;padding:5px 5px;" doLayout="true"> --%>
                                <div  style="border:1px solid gray; width:845px;height: 500px; overflow:auto; margin-bottom: 1em;">
                                
                            <div id="issuesTab" class="tabcontent" >
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

                                                        <a href="#" onmousedown="closeCompletedToogle()" >
                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> 

                                                        </a>  

                                                    </td></tr>
                                                               <tr>
                                                    <td colspan="4">
                                                        <div id="load" style="color: green;display: none;">Loading..</div>
                                                        <div id="resultMessage"></div>
                                                    </td>
                                                               <tr style="display: none" id="depotUrlTr">
                                                                   <td  class="fieldLabel">After&nbsp;Page&nbsp;Link&nbsp;: </td>
                                                                   <td colspan="3"><span id="depotLink"></span></td>
                                                               </tr>
                                                    
                                                    
                                                </tr>    
                                                        <tr><td colspan="0">
                                                         <table style="width:80%;" align="center">
                                                                       <tr>
                                                                             <td align="left" class="fieldLabel" >EventType:<FONT color="red"  ><em>*</em></FONT></td>
                                                                             <td >
                                                                               <s:select headerKey="" headerValue="--Please Select--" list="#@java.util.LinkedHashMap@{'Q':'Quarterly Meet','IEE':'Internal Employee Experience','I':'Internal Webinar','E':'External Webinar','C':'Conferences'}" name="eventType" id="eventType" cssClass="inputSelect" onchange="getEventType(this);" cssStyle="display:none"/> 
                                                                                 <span id="eventTypeLabel" style="display: none"></span>
                                                                            </td> 
                                                   
                                                            <td  class="fieldLabel" >Status:<FONT color="red"  ><em>*</em></FONT></td>
                                                            <td >
                                                           <%--     <s:select id="eventStatus"  name="eventStatus"  list="{'Active','InActive','Completed','Published'}" cssClass="inputSelect" onchange="checkAfterFields(this);" /> --%>
                                                                <s:select id="eventStatus"  name="eventStatus"  list="{'Completed','Published'}" cssClass="inputSelect" onchange="checkAfterFields(this);" />
                                                                <FONT color="green" size="2px"><span id="eventStatusLabel" style="display: none"></span></FONT>
                                                                
                                                     </td>
                                                                     </tr>
                                                       
                                                      
                                                     
                                                        <tr>
                                                     <td align="left" class="fieldLabel" >Upcoming&nbsp;Page&nbsp;Title:<FONT color="red"  ><em>*</em></FONT></td>
                                                     <td colspan="3">
                                                      <%--    <s:textfield name="eventtitle" id="eventtitle" cssClass="inputTextBlueLargeAccount" onchange="return fieldLengthValidator2(this);" /> --%>
                                                          <FONT color="green" size="2px"><span id="eventUpcomingPageLabel"></span></FONT>
                                                     </td> 
                                                     </tr>
                                                     <tr style="display: none" id="eventBoldTr">
                                                     <td align="left" class="fieldLabel" >Page&nbsp;Title:<FONT color="red"  ><em>*</em></FONT></td>
                                                     <td colspan="3">
                                                         <FONT color="green" size="2px"><span id="eventTitlePageLabel"></span></FONT>
                                                       <%--   <s:textfield name="eventBoldtitle" id="eventBoldtitle" cssClass="inputTextBlueLargeAccount" onchange="return fieldLengthValidator2(this);" /> --%>
                                                     </td> 
                                                     </tr>
                                                     <%--  <tr style="display: none" id="eventRegularTr">
                                                     <td align="left" class="fieldLabel" >Event Regular Title:<FONT color="red"  ><em>*</em></FONT></td>
                                                     <td colspan="3">
                                                          <s:textfield name="eventRegluarTitle" id="eventRegluarTitle" cssClass="inputTextBlueLargeAccount" onchange="return fieldLengthValidator2(this);" />
                                                     </td> 
                                                     </tr>  --%>
                                                       
                                                            
                                            
                                                          <%--  <tr>
                                                     <td align="left" class="fieldLabel" >Event Title:<FONT color="red"  ><em>*</em></FONT></td>
                                                     <td colspan="3">
                                                          <s:textfield name="eventtitle" id="eventtitle" cssClass="inputTextBlueLargeAccount" onchange="return fieldLengthValidator2(this);" />
                                                     </td> 
                                                     </tr>
                                                     <tr style="display: none" id="eventBoldTr">
                                                     <td align="left" class="fieldLabel" >Event Bold Title:<FONT color="red"  ><em>*</em></FONT></td>
                                                     <td colspan="3">
                                                          <s:textfield name="eventBoldtitle" id="eventBoldtitle" cssClass="inputTextBlueLargeAccount" onchange="return fieldLengthValidator2(this);" />
                                                     </td> 
                                                     </tr>
                                                       <tr style="display: none" id="eventRegularTr">
                                                     <td align="left" class="fieldLabel" >Event Regular Title:<FONT color="red"  ><em>*</em></FONT></td>
                                                     <td colspan="3">
                                                          <s:textfield name="eventRegluarTitle" id="eventRegluarTitle" cssClass="inputTextBlueLargeAccount" onchange="return fieldLengthValidator2(this);" />
                                                     </td> 
                                                     </tr> 
                                                        
                                                         --%>
                                                     
                                                     <tr id="datesTr">
                                                       <td class="fieldLabel"><span id="fromDateLabelId">Event&nbsp;Date&nbsp;:</span></td>
                                                    <td>
                                                        <%--<s:textfield name="selectDateFrom" id="selectDateFrom" cssClass="inputTextBlue" onchange="validateTimestamp(this);"/>
                                                      <a href="javascript:selectcal1.popup();">
                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif"
                                                             width="20" height="20" border="0"></a> --%>
                                                             
                                                        <FONT color="green" size="2px"><span id="eventDateFromLabel"></span></FONT>
                                                        </td> 
                                                        <td class="fieldLabel" ><div id="toDateLabelId" style="display: none">Event&nbsp;Date&nbsp;To:</div></td>
                                                    <td ><div id="toDateTextId" style="display: none"><s:textfield name="selectDateTo" id="selectDateTo" cssClass="inputTextBlue" onchange="validateTimestamp(this);"/>
                                                     <%-- <a href="javascript:selectcal2.popup();">
                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif"
                                                             width="20" height="20" border="0"></a> --%>
                                                        </div>
                                                    
                                                        <FONT color="green" size="2px"><span id="eventDateToLabel"></span></FONT>
                                                    </td></tr>
                                                       <tr id="timeTr">
                                                           
                                                           <td class="fieldLabel">Select&nbsp;Start&nbsp;Time:</td>
                                                    <td>
                                                        
                                                     <%--   <s:select id="startTime"  name="startTime"  list="timeList" cssClass="inputSelectGender" />
                                                      <s:select id="midDayFrom"  name="midDayFrom"  list="{'AM','PM'}" cssClass="inputSelectSmall" /> --%>
                                                      
                                                      <FONT color="green" size="2px"><span id="startTimeLabel"></span></FONT>
                                                      
                                                        </td>
                                                         <td class="fieldLabel">Select&nbsp;End&nbsp;Time:</td>
                                                    <td>
                                                        <%-- <s:select id="endTime"  name="endTime"  list="timeList" cssClass="inputSelectGender" />
                                                        <s:select id="midDayTo"  name="midDayTo"  list="{'AM','PM'}" cssClass="inputSelectSmall" /> --%>
                                                           <FONT color="green" size="2px"><span id="endTimeLabel"></span></FONT>
                                                        </td>
                                                           
                                                         </tr>  
                                                     <tr id="zoneTr">
                                                        <td class="fieldLabel">Select&nbsp;TimeZone:</td>  
                                                        <td >
                                                            <%-- <s:select id="timeZone"  name="timeZone"  list="{'EST','IST','MST','CST','PST'}" cssClass="inputSelect" /> --%>
                                                        
                                                         <FONT color="green" size="2px"><span id="timeZoneLabel"></span></FONT>
                                                        </td>
                                                         <td align="left" class="fieldLabel" >Location:<FONT color="red"  ><em>*</em></FONT></td>
                                                         <td>
                                                      <%--    <s:textfield name="eventLocation" id="eventLocation" cssClass="inputTextBlue" onchange="return fieldLengthValidator2(this);" /> --%>
                                                           <FONT color="green" size="2px"><span id="locationLabel"></span></FONT>
                                                     </td> 
                                                     </tr>
                                                     <tr id="transportationTr" style="display: none">
                                                           <td class="fieldLabel" valign="top">Transportation:<FONT color="red"  ><em>*</em></FONT></td>
                                                     <td colspan="3" valign="top"><s:textarea rows="2" cols="65" name="transportation" cssClass="inputTextareaOverlay1" onchange="return fieldLengthValidator2(this);" id="transportation" /></td>
                                                     </tr>
                                                       
                                                     <tr id="eventDescriptionTr" style="display: none">
                                                        <td class="fieldLabel" valign="top">Event&nbsp;Description:<FONT color="red"  ><em>*</em></FONT></td>
                                                        <td colspan="3" valign="top"><s:textarea rows="5" cols="65" name="eventDescription" cssClass="inputTextareaOverlay1" onchange="return fieldLengthValidator2(this);" id="eventDescription" readonly="true"/>
                                                        
                                                      <!--   <font class="fieldLabel">
                                                             <br> Please include p tag to every paragraph like &lt;p class='methodText'&gt;...&lt;/p&gt;...&lt;p class='methodText'&gt;...&lt;/p&gt;
                                                         </font>
                                                         -->
                                                        
                                                     
                                                     
                                                     </td>
                                                            </tr> <tr id="eventAfterDescriptionTr" style="display: none">
                                                        <td class="fieldLabel" valign="top">Event&nbsp;After&nbsp;Description:<FONT color="red"  ><em>*</em></FONT></td>
                                                        <td colspan="3" valign="top"><s:textarea rows="5" cols="65" name="eventAfterDescription" cssClass="inputTextareaOverlay1" onchange="checkDoubleQuotes(this);return fieldLengthValidator2(this);" id="eventAfterDescription"/>
                                                        
                                                    <font class="fieldLabel">
                                                             <br> Please include p tag to every paragraph like &lt;p class='methodText'&gt;...&lt;/p&gt;...&lt;p class='methodText'&gt;...&lt;/p&gt;
                                                         </font>
                                                      </td>
                                                            </tr>
                                                      <tr style="display: none" id="afterVideoTr">
                                                          <td align="left" class="fieldLabel" >After&nbsp;Video&nbsp;Link:<FONT color="red"  ><em>*</em></FONT></td>
                                                     <td colspan="3">
                                                          <s:textfield name="eventAfterVideoUrl" id="eventAfterVideoUrl" cssClass="inputTextBlueLargeAccount" onchange="return fieldLengthValidator2(this);" />
                                                     </td> 
                                                     </tr>
                                                              <tr style="display: none" id="eventRegistrationLinkTr">
                                                     <td align="left" class="fieldLabel" >Event&nbsp;Registration&nbsp;Link:<FONT color="red"  ><em>*</em></FONT></td>
                                                     <td colspan="3">
                                                         <FONT color="green" size="2px"><span id="registrationLinkLabel"></span></FONT>
                                                       <%--   <s:textfield name="eventRegistrationLink" id="eventRegistrationLink" cssClass="inputTextBlueLargeAccount" onchange="return fieldLengthValidator2(this);" /> --%>
                                                     </td> 
                                                     </tr>
                                                     <tr style="display: none" id="conferenceLinkTr">
                                                     <td align="left" class="fieldLabel" >Conference&nbsp;Website&nbsp;Url:<FONT color="red"  ><em>*</em></FONT></td>
                                                     <td colspan="3">
                                                          <s:textfield name="conferenceUrl" id="conferenceUrl" cssClass="inputTextBlueLargeAccount" onchange="return fieldLengthValidator2(this);" />
                                                     </td> 
                                                     </tr>
                                                     
                                                     
                                                 <%--    <tr style="display: none" id="eventTrackTr">
                                                       <td align="left" class="fieldLabel" >Primary&nbsp;Track:<FONT color="red"  ><em>*</em></FONT></td>  
                                                       <td >
                                                            <FONT color="green" size="2px"><span id="primaryTrackLabel"></span></FONT>
                                                           
                                                            <s:select headerKey="" headerValue="--Please Select--" id="primaryTrack"  name="primaryTrack"  list="{'Cloud-Services','SOA and Connectivity','B2B Integration and File Transfer','SAP/ERP Services','Application Management','Business Process Management','Application Development','Quality Assurance and Testing','Digital Experience and Commerce'}" cssClass="inputSelect" cssStyle="display:none"/>
                                                       
                                                       </td>
                                                          <td align="left" class="fieldLabel" >Secondary&nbsp;Track:<FONT color="red"  ><em>*</em></FONT></td>  
                                                         <td >
                                                             <FONT color="green" size="2px"><span id="secondaryTrackLabel"></span></FONT>
                                                             <s:select headerKey="" headerValue="--Please Select--" id="secondaryTrack"  name="secondaryTrack"  list="{'Cloud-Services','SOA and Connectivity','B2B Integration and File Transfer','SAP/ERP Services','Application Management','Business Process Management','Application Development','Quality Assurance and Testing','Digital Experience and Commerce'}" cssClass="inputSelect" cssStyle="display:none"/> 
                                                         
                                                         </td>
                                                     </tr> --%>
                                                  <tr style="display: none" id="eventTrackTr">
                                                       <td align="left" class="fieldLabel" >Primary&nbsp;Track:<FONT color="red"  ><em>*</em></FONT></td>  
                                                       <td >
                                                            <FONT color="green" size="2px"><span id="primaryTrackLabel"></span></FONT>
                                                           
                                                            <s:select headerKey="" headerValue="--Please Select--" id="primaryTrack"  name="primaryTrack"  list="trackNamesMap" cssClass="inputSelect" />
                                                       
                                                       </td>
                                                          <td align="left" class="fieldLabel" >Secondary&nbsp;Track:<FONT color="red"  ><em>*</em></FONT></td>  
                                                         <td >
                                                             <FONT color="green" size="2px"><span id="secondaryTrackLabel"></span></FONT>
                                                             <s:select headerKey="" headerValue="--Please Select--" id="secondaryTrack"  name="secondaryTrack"  list="trackNamesMap" cssClass="inputSelect" /> 
                                                         
                                                         </td>
                                                     </tr>
                                                     <tr style="display: none" id="eventDepartmentTr">
                                                         <td align="left" class="fieldLabel" >Department:<FONT color="red"  ><em>*</em></FONT></td>
                                                         <td colspan="3">
                                                             <FONT color="green" size="2px"><span id="departmentLabel"></span></FONT>
                                                            <%-- <s:select headerKey="" headerValue="--Please Select--" id="eventDepartment"  name="eventDepartment"  list="{'Business Development','Marketing','Recruitment','Other','Development','Operations'}" cssClass="inputSelect" /> --%>
                                                         
                                                         </td>
                                                     </tr>
                                                     
                                                     
                                                     
                                                          <tr style="display: none" id="contactUsTr">
                                                     <td align="left" class="fieldLabel" >ContactUs Email:<FONT color="red"  ><em>*</em></FONT></td>
                                                     <td colspan="3">
                                                          <s:textfield name="contactUsEmail" id="contactUsEmail" cssClass="inputTextBlueLargeAccount" onchange="return fieldLengthValidator2(this);" />
                                                     </td> 
                                                     </tr>
                                                     
                                                         <tr id="createdTr" style="display: none">
                                                      <td class="fieldLabel" valign="top">CreatedBy:<FONT color="red"  ><em>*</em></FONT> </td>
                                                      <td><FONT color="green"  ><span id="createdBy"></span></FONT></td>
                                                       <td class="fieldLabel" valign="top">CreatedDate:<FONT color="red"  ><em>*</em></FONT> </td>
                                                      <td><FONT color="green"  ><span id="createdDate"></span></FONT></td>
                                                  </tr>
                                                 <%--       <tr id="addTr" style="display: none"> 
                                                        <td  align="center" >
                                                         <input type="button" value="Save" onclick="return doEventPost();" class="buttonBg" id="addButton"/> 
                                                          <s:reset cssClass="buttonBg"  align="right" value="Reset" />
                                                        
                                                    </td>
                                                        </tr> 
                                                        --%>
                                                        
                                                        
                                                        <tr id="editTr" style="display: none"> 
                                                    <td  align="center" >
                                                         <input type="button" value="Update" onclick="return doUpdateAfterEventPost();" class="buttonBg" id="updateButton"/> 
                                                    </td>
                                                </tr>
                                                               </table>
                                                           </td>
                                                       </tr>
                                                           </table>
                                                       </s:form>    
                                                         </div>
                                     
                                     
                                        <s:form action="completedEventSearch.action" id="frmDBGrid" name="frmDBGrid" theme="simple"> 
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
                                <%--    <td ><s:select headerKey="" headerValue="--Please Select--" id="status"  name="status"  list="{'Active','InActive','Completed','Published'}" cssClass="inputSelect"  disabled="False"/> --%>
                                    <td ><s:select id="status"  name="status"  list="{'Completed','Published'}" cssClass="inputSelect"  disabled="False"/>
                                </tr>
                                <tr>
                                    <td class="fieldLabel">Event Type:</td>
                                    <td >
                                      <s:select headerKey="" headerValue="--Please Select--" list="#@java.util.LinkedHashMap@{'Q':'Quarterly Meet','IEE':'Internal Employee Experience','I':'Internal Webinar','E':'External Webinar','C':'Conferences'}" name="eventSearchType" id="eventSearchType" cssClass="inputSelect" disabled="False"/>
                                </tr>
                                <tr>
                                    
                                    <td></td>
                                    <td colspan="2"></td>
                                    <td>
                                       <!-- <input type="button" class="buttonBg"  align="right"  value="Add" onclick="addEventPost();" />  -->
                                       <s:submit name="search" value="Search" cssClass="buttonBg"/>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                                                
                       
            
         
                     <s:if test="#session.completedEventslist != null"> 
                    <tr>
                        <td>
                          <table id="results" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='90%' align="center">
                               
                              <%java.util.List mainList = (java.util.List) session.getAttribute("completedEventslist");
                                if(mainList.size()>0){


%>
                              
                             <tr class="gridHeader">
                                                       
                                                        <th>EventTitle</th>
                                                       <!-- <th>EventDesc</th> -->
                                                       <th>EventType</th>
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
                            java.util.Map subList = (java.util.Map)mainList.get(i);
               //  for (int j = 0; j < subList.size(); j++) {
                     
                     %>
                     <td class="title">
                         
                        
                         
                         <a style="color:#C00067;" href="javascript:getCompletedEventDetails('<%=subList.get("event_id")%>','readonly');">
                         <%
                          out.println(subList.get("event_title"));
                         %></a>
                     </td>    
                     <td class="title">
                          <%
                          String type = "";
                          if("Q".equalsIgnoreCase(subList.get("WebinarType").toString())){
                            type = "Quarterly Meet"  ;
                          }else if("I".equalsIgnoreCase(subList.get("WebinarType").toString())){
                               type = "Internal Webinar"  ;
                          }else if("E".equalsIgnoreCase(subList.get("WebinarType").toString())){
                               type = "External Webinar"  ;
                          }else if("IEE".equalsIgnoreCase(subList.get("WebinarType").toString())){
                               type = "IEE"  ;
                          }else if("C".equalsIgnoreCase(subList.get("WebinarType").toString())){
                               type = "Conference"  ;
                          }
                          
                          out.println(type);
                         %>
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
                              if(subList.get("WebinarType")!=null&&("I".equalsIgnoreCase(subList.get("WebinarType").toString())||"E".equalsIgnoreCase(subList.get("WebinarType").toString()))){
    
    
    %>
                         <a style="color:#C00067;" href="javaScript:eventSpeakerPopup('eventSpeakerDetailsList.action?eventId=<%=subList.get("event_id")%>&objectType=EV');">
                             
                             
                             <img src="../includes/images/go_21x21.gif"/>
                        </a>
                             <% } %>
                     </td>
                      <td class="title">
                              <%
                              //if(subList.get("WebinarType").toString().equalsIgnoreCase("I")||subList.get("WebinarType").toString().equalsIgnoreCase("E")){
                              if(subList.get("WebinarType")!=null&&("I".equalsIgnoreCase(subList.get("WebinarType").toString())||"E".equalsIgnoreCase(subList.get("WebinarType").toString()))){
    
    
    %>
                         <a style="color:#C00067;" href="javascript:getCompletedEventDetails('<%=subList.get("event_id")%>','publish');">
                             <img src="../includes/images/publish-button.jpg" height="30" width="50"/>
                        </a>
                             <% } %>
                     </td>
                     <%
                     
              //   }
                %></tr><% 
                 
             }
                          }else {
             %>
                     <tr><td>
                 <%
                // String contextPath = request.getContextPath();
           // out.println("<img  border='0' align='top'  src='"+contextPath+"/includes/images/alert.gif'/><b> No Records Found to Display!</b>");
                 
                  out.println("<img  border='0' align='top'  src='"+contextPath+"/includes/images/alert.gif'/> <font color='white'><b>No records found!</b></font>");
           // }

            %>
                     </td>
           </tr>
           <%}%>
               
                        </table>
                        </td>
                    </tr>
                    
                    
                    
                     <%
                                         
             if(mainList.size()!=0){
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
                                             var cal1 = new CalendarTime(document.forms['frmDBGrid'].elements['createdDateFrom']);
				                 cal1.year_scroll = true;
				                 cal1.time_comp = true;

                                             var cal2 = new CalendarTime(document.forms['frmDBGrid'].elements['createdDateTo']);
				                 cal2.year_scroll = true;
				                 cal2.time_comp = true;

						/*var selectcal1 = new CalendarTime(document.forms['eventForm'].elements['selectDateFrom']);
				                 selectcal1.year_scroll = true;
				                 selectcal1.time_comp = false;
                                                 var selectcal2 = new CalendarTime(document.forms['eventForm'].elements['selectDateTo']);
				                 selectcal2.year_scroll = true;
				                 selectcal2.time_comp = false;*/
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
        
        
        
        
        
    </body>
</html>
