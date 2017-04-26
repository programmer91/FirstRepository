<!doctype html>
<html lang=''>
    <%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
    <%@ page import="com.mss.mirage.util.ApplicationConstants"%>
    <%@page import="com.opensymphony.xwork2.ActionContext"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
    <head>
        <meta charset='utf-8'>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
    
    <%--    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/jquery-latest.min.js"/>"></script>  --%>
         <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/jquery.min.js"/>"></script>
    <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/jquery.js"/>"></script>  
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/menu/toggleMenu.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/menu/menuWebAdmin.js"/>"></script>
         <script type="text/javascript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script> 
        
    </head>
    <body>
        <div id='cssmenu'>
            <%
                String actionName = ActionContext.getContext().getName();
                //System.out.println("action name"+actionName);
%>
            <span id="action" style="display: none"><%=actionName%></span> 
            <ul>

                <li class='has-sub' ><a href='#' id="iconToggleMy"><span id="myAdmin">My</span></a>
                    <ul id="myDisplay">
                    </ul>
                </li>
                <li class='has-sub'><a href='#' id="iconToggleTeam"><span id="teamAdmin">Team</span></a>
                    <ul id="teamDisplay">
                    </ul>
                </li>
                <li class='has-sub'><a href='#' id="iconToggleServices"><span id="servicesAdmin">Services</span></a>
                    <ul id="servicesDisplay">
                        <li><a id="servicesCampaign" href="/<%=ApplicationConstants.CONTEXT_PATH%>/marketing/campaignSearchAction.action"><span>Campaign</span></a></li>
                     <%--   <li><a id="emailCampaignList" href="/<%=ApplicationConstants.CONTEXT_PATH%>/marketing/emailCampaignList.action"> <span>Email&nbsp;Campaign&nbsp;List</span></a></li>  --%>
                        <li class='last'><a id="websiteJob"   href="/<%=ApplicationConstants.CONTEXT_PATH%>/marketing/websiteJob.action"><span>Website&nbsp;jobs</span></a></li>
                         <s:if test="#session.sessionCCAccess == 1">
                  <li class='last'><a id="getConstnatList" href="/<%=ApplicationConstants.CONTEXT_PATH%>/marketing/getConstnatList.action"><span>Constant&nbsp;Contact</span></a></li>
             </s:if>
                    </ul>
                </li>
                <li class='has-sub'><a href='#' id="iconToggleEvents"><span id="eventsAdmin">Events</span></a>
                    <ul id="EventsDisplay">
                        <%-- <li><a id="eventManagement" href="/<%=ApplicationConstants.CONTEXT_PATH%>/marketing/eventManagement.action"> <span>Upcoming&nbsp;Events</span></a></li>
                        <li><a id="completedEvents" href="/<%=ApplicationConstants.CONTEXT_PATH%>/marketing/completedEvents.action"> <span>Completed&nbsp;Events</span></a></li>
                        <li><a id="webinarSeries" href="/<%=ApplicationConstants.CONTEXT_PATH%>/marketing/webinarSeries.action"> <span>Webinar&nbsp;Series</span></a></li>
                        <s:if test="#session.emeetPostingFlag == 1">
                            <li class='last'><a id="eMeets" href="/<%=ApplicationConstants.CONTEXT_PATH%>/marketing/emeetList.action"><span>Emeets</span></a><</li>
                        </s:if> --%>
                            <li><a id="internalEvents" href="/<%=ApplicationConstants.CONTEXT_PATH%>/marketing/events/internalEvents.action"> <span>Internal&nbsp;Events</span></a></li>
                            <li><a id="externalEvents" href="/<%=ApplicationConstants.CONTEXT_PATH%>/marketing/events/externalEvents.action"><span>External&nbsp;Events</span></a></li>
                            
                    </ul>
                </li>
                <li class='has-sub'><a href='#' id="iconToggleResources"><span id="resourcesAdmin">Resources</span></a>
                    <ul id="ResourcesDisplay">
                        <li><a id="getLkpTrackNames" href="/<%=ApplicationConstants.CONTEXT_PATH%>/marketing/getLkpTrackNames.action"> <span>Tracks</span></a></li>
                        <li><a id="getResources" href="/<%=ApplicationConstants.CONTEXT_PATH%>/marketing/library/getResources.action"> <span>Library</span></a></li>
                        <li><a id="getPeople" href="/<%=ApplicationConstants.CONTEXT_PATH%>/marketing/getPeople.action"> <span>People</span></a></li>
                        <li><a id="surveyFormList" href="/<%=ApplicationConstants.CONTEXT_PATH%>/marketing/surveyform/surveyFormList.action"> <span>Survey&nbsp;Form</span></a></li>
                      <%--  <s:if test="#session.emeetPostingFlag == 1">
                            <li class='last'><a id="emeetAttendiesList" href="/<%=ApplicationConstants.CONTEXT_PATH%>/marketing/emeetAttendiesList.action"><span>EAttendees&nbsp;</span></a></li>
                        </s:if> --%>
                            
                    </ul>
                </li>
                <li class='has-sub'><a href='#' id="iconToggleWebsiteData"><span id="websitedataAdmin">Website&nbsp;Data</span></a>
                    <ul id="webdataDisplay">
                        <li><a id="getContactUs" href="/<%=ApplicationConstants.CONTEXT_PATH%>/marketing/getContactUs.action"> <span>ContactUs&nbsp;Data</span></a></li>
                        <li><a id="getEmpVerification" href="/<%=ApplicationConstants.CONTEXT_PATH%>/marketing/getEmpVerification.action"> <span> EmpVerification&nbsp;Data</span></a></li>
                        <li><a id="getResourceDepot" href="/<%=ApplicationConstants.CONTEXT_PATH%>/marketing/getResourceDepot.action"> <span> Resource&nbsp;Depot&nbsp;Data</span></a></li>
                      <%--  <li><a id="getQuarterlyMeet" href="/<%=ApplicationConstants.CONTEXT_PATH%>/marketing/getQuarterlyMeet.action"> <span> QuarterlyMeet&nbsp;Data</span></a></li> --%>
                        <li><a id="getIotDetails" href="/<%=ApplicationConstants.CONTEXT_PATH%>/marketing/getIOTDetails.action"><span>IOT&nbsp;Data</span></a></li>
                        <li><a id="getSignatureDetails" href="/<%=ApplicationConstants.CONTEXT_PATH%>/marketing/getSignatureDetails.action"><span>Signature&nbsp;Data</span></a></li>
                      
                    </ul>
                </li>
            </ul>
        </div>
    </body>
    </html>
