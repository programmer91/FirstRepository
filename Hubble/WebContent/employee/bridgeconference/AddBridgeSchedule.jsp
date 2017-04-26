<!-- 
 *******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  
 *
 * Author  : 
 *
 * File    :
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
-->
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="com.freeware.gridtag.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib  prefix="grd" uri="/WEB-INF/tlds/datagrid.tld" %>

<html>
    <head>
        <title>Hubble Organization Portal :: Employee AddBridge</title>
         <sx:head cache="true"/>
        
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/StringUtility.js"/>"></script>
        <script type="text/javaScript" src="<s:url value="/includes/javascripts/Bridge.js"/>"></script>
        <script type="text/javaScript" src="<s:url value="/includes/javascripts/clientValidations/BridgeConferenceAction.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <s:include value="/includes/template/headerScript.html"/>
        
    </head>
    <body class="bodyGeneral" oncontextmenu="return false;">
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
                            <td width="850px" class="cellBorder" valign="top">
                                <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                    
                                    <tr>
                                        <td valign="top">
                                            <!--//START TABBED PANNEL : -->
                                            <sx:tabbedpanel id="addBridge" cssStyle="width: 845px; height: 350px;padding:5px 5px 5px 5px;" doLayout="true">
                                                
                                                <!--//START TAB : -->
                                                <sx:div id="addBridgeTab" label="%{currentAction}" theme="ajax">
                                                    <s:form  action="%{currentAction}"  name="addBridgeForm"  theme="simple" method="post" onsubmit="return validation();">
                                                        <table width="100%" cellpadding="0" cellspacing="4" border="0">
                                                            <tr align="right">
                                                                <td class="headerText" colspan="4">
                                                                    <s:property value="#request.resultMessage"/>
                                                                    <s:submit label="Submit" value="Save" cssClass="buttonBg" />
                                                                    <!--HiddenFields START-->
                                                                   
                                                                    <s:hidden name="bridgeId" id="bridgeId" value="%{bridgeId}"/>
                                                                    
                                                                    <!--HiddenFields END-->
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td colspan="4">                                                        
                                                                    <!-- for displaying validation Errors and Action Messages -->
                                                                    <s:actionerror/>
                                                                    <s:actionmessage/>
                                                                    <s:fielderror/>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td width="130px"class="fieldLabel">ContactNumber :</td>
                                                                <td> <s:textfield  cssClass="inputTextBlue" id="contactNo" name="contactNo" value="%{bridgeConferenceVTO.contactNo}"/></td> 
                                                                <td class="fieldLabel">Title :</td>
                                                                <td colspan="1"><s:textfield cssClass="inputTextBlue" id="title" name="title" value="%{bridgeConferenceVTO.title}"/></td>
                                                                
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel">Engaged By :</td>
                                                                <td><s:textfield cssClass="inputTextBlue" id="engagedBy" name="engagedBy" value="%{bridgeConferenceVTO.engagedBy}"/></td>
                                                                <td class="fieldLabel">Date : </td>  
                                                                
                                                                <td><s:textfield cssClass="inputTextBlue" id="date" name="date"  value="%{bridgeConferenceVTO.date}" /></td>
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel">Start Time : </td> 
                                                                <td><s:textfield cssClass="inputTextBlue" id="startTime" name="startTime" value="%{bridgeConferenceVTO.startTime}"/>
                                                                    <a href="javascript:cal1.popup();">
                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a> 
                                                                </td> 
                                                                <td class="fieldLabel">End Time :</td>
                                                                <td><s:textfield cssClass="inputTextBlue" id="endTime" name="endTime"  value="%{bridgeConferenceVTO.endTime}"/>
                                                                    <a href="javascript:cal2.popup();">
                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a></td>
                                                            </tr>
                                                            
                                                            <tr>
                                                                <td class="fieldLabel">Listof Email Add :</td>
                                                                <td><s:textfield  cssClass="inputTextBlue" id="listOfEmailAdd" name="listOfEmailAdd" onchange="getnumber()" value="%{bridgeConferenceVTO.listOfEmailAdd}"/></td>
                                                                <s:if test="%{currentAction == 'AddBridgeSchedule'}">   
                                                                    <td class="fieldLabel"> senderEmailId</td>
                                                                    <td><s:textfield cssClass="inputTextBlue"  id="senderMail" name="senderMail"/></td>
                                                                </s:if> 
                                                                
                                                            </tr>
                                                            
                                                            <tr>
                                                                <td  class="fieldLabel" >Status :</td>                                                                  
                                                                <td><s:textfield  cssClass="inputTextBlue" id="status" name="status" value="%{bridgeConferenceVTO.status}"/></td>
                                                                <td class="fieldLabel">Bridge Number :</td>
                                                                <td><s:select list="bridgeNumbers" cssClass="inputSelect" value="%{bridgeConferenceVTO.bridgeNumber}"  name="bridgeNumber" id="bridgeNumber" headerKey=" " headerValue="--pleaseSelect--"/>
                                                            </tr>
                                                            
                                                        </table>
                                                    </s:form>
                                                    <%-- <s:else>
                                                        <s:form  action="%{currentAction}"  name="addBridgeForm"  theme="simple" method="post" validate="true" >
                                                            <table width="100%" cellpadding="0" cellspacing="4" border="0">
                                                                <tr align="right">
                                                                    <td class="headerText" colspan="7">
                                                                        <s:property value="#request.resultMessage"/>
                                                                        <s:submit label="Submit" value="Save" cssClass="buttonBg" />
                                                                        <!--HiddenFields START-->
                                                                   
                                                                        <s:hidden name="bridgeId" id="bridgeId" />
                                                                        
                                                                        <!--HiddenFields END-->
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td colspan="5">                                                        
                                                                        <!-- for displaying validation Errors and Action Messages -->
                                                                        <s:actionerror/>
                                                                        <s:actionmessage/>
                                                                        <s:fielderror/>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td width="130px"class="fieldLabel">ContactNumber :</td>
                                                                    <td> <s:textfield  id="contactNo" name="contactNo" value="%{bridgeConferenceVTO.contactNo}"/></td> 
                                                                    <td class="fieldLabel">Title :</td>
                                                                    <td colspan="1"><s:textfield  id="title" name="title" value="%{bridgeConferenceVTO.title}" /></td>
                                                                    
                                                                </tr>
                                                                <tr>
                                                                    <td class="fieldLabel">Engaged By :</td>
                                                                    <td><s:textfield id="engagedBy" name="engagedBy"  value="%{bridgeConferenceVTO.engagedBy}"/></td>
                                                                    <td class="fieldLabel">Date : </td>  
                                                                    
                                                                    <td><s:textfield  id="date" name="date" value="%{bridgeConferenceVTO.date}" />
                                                                </td></tr>
                                                                <tr>
                                                                    <td class="fieldLabel">Start Time : </td> 
                                                                    <td><s:textfield  id="startTime" name="startTime" value="%{bridgeConferenceVTO.startTime}"/>
                                                                        <a href="javascript:cal1.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a> 
                                                                    </td> 
                                                                    <td class="fieldLabel">End Time :</td>
                                                                    <td><s:textfield  id="endTime" name="endTime" value="%{bridgeConferenceVTO.endTime}" />
                                                                        <a href="javascript:cal2.popup();">
                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a></td>
                                                                </tr>
                                                                
                                                                <tr>
                                                                    <td class="fieldLabel">Listof Email Add :</td>
                                                                    <td ><s:textfield  id="listOfEmailAdd" name="listOfEmailAdd" value="%{bridgeConferenceVTO.listOfEmailAdd}" cssClass="inputTextBlue"  /></td>
                                                                    
                                                                    <td class="fieldLabel">Bridge Number :</td>
                                                                    <td colspan="1"><s:textfield id="bridgeNumber" name="bridgeNumber" value="%{bridgeConferenceVTO.bridgeNumber}" />
                                                                </tr>
                                                                
                                                                <tr>
                                                                    
                                                                    
                                                                    <td  class="fieldLabel" >Status :</td>                                                                  
                                                                    <td><s:textfield  id="status" name="status" value="%{bridgeConferenceVTO.status}"/></td>
                                                                    
                                                                    
                                                                    
                                                                </tr>
                                                                
                                                            </table>
                                                        </s:form>
                                                        
                                                    </s:else> --%>
                                                    <script type="text/JavaScript">
                                                           var cal1 = new CalendarTime(document.forms['addBridgeForm'].elements['startTime']);
                                                           cal1.year_scroll = true;
                                                           cal1.time_comp = true;
                                                           
                                                           var cal2 = new CalendarTime(document.forms['addBridgeForm'].elements['endTime']);
                                                           cal2.year_scroll = true;
                                                           cal2.time_comp = true;
                                                    </script>                                                  
                                                </sx:div >
                                                <!--//END TAB : -->
                                    
                                            </sx:tabbedpanel>
                                            <!--//END TABBED PANNEL : -->
                                
                                            <!--//START TABBED PANNEL : -->
                                            
                                        </td>
                                    </tr> 
                                </table>
                                
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
            
        </table>
        <!--//END MAIN TABLE : Table for template Structure-->
    </body>
</html>