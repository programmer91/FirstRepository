<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%-- <%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<%@ page import="com.freeware.gridtag.*" %> 
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@page import="java.util.*"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd" %>

<html>
    <head>
        
        <title>Hubble Organization Portal :: Employee Vocation List</title>
         <sx:head cache="true"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
         <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script> 
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/employee/EmpVacation.js"/>"></script> 
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
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
                    <table class="innerTable1000x515" cellpadding="0" border="1" cellspacing="0">
                        <tr>
                            
                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                            <td width="150px;" class="leftMenuBgColor" valign="top">
                                <s:include value="/includes/template/LeftMenu.jsp"/>
                                
                            </td>
                            <!--//END DATA COLUMN : Coloumn for LeftMenu-->
                            
                            <!--//START DATA COLUMN : Coloumn for Screen Content-->
                            <td width="850px" class="cellBorder" valign="top">
                                
                                
                                <!--//START TABBED PANNEL : --> 
                               
                                <sx:tabbedpanel id="vacationListPannel" cssStyle="width: 840px; height: 500px;padding-left:10px;padding-top:5px;" doLayout="true">
                                    
                                    <!--//START TAB : -->
                                    <sx:div id="vacationsListTab" label="Vacation List"  >
                                        <s:form name="frmSearch" action="" theme="simple">
                                            <table border="0" cellpadding="1" cellspacing="1" border="0" width="100%">
                                                <tr>
                                                    <td class="headerText" colspan="4" align="right">
                                                        <input type="button" Class="buttonBg"  value="Retrive" onclick="getVacationList()">
                                                    </td>
                                                </tr>
                                                <tr>                                                    
                                                    <td  class="fieldLabel">Start Date:</td>
                                                    <td><s:textfield name="startDate" cssClass="inputTextBlue"/><a href="javascript:cal1.popup();">
                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                         width="20" height="20" border="0"></a></td>
                                                    
                                                    <td class="fieldLabel">End Date:</td>
                                                    <td><s:textfield name="endDate" cssClass="inputTextBlue"/><a href="javascript:cal2.popup();">
                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                         width="20" height="20" border="0"></a></td>
                                                </tr>
                                                <tr>
                                                    <td class="fieldLabel">Organization:</td>
                                                    <td>
                                                        <s:select name="organization"  cssClass="inputSelectExtraLarge" 
                                                                  headerKey="" 
                                                                  headerValue="Select"
                                                                  list="{'Miracle Software Systems(USA).Inc','Miracle Software Systems(India),Pvt.Ltd','Miracle Software Systems(Uk).Ltd','Chikiniki Enterprises Pvt.Ltd'}"/>
                                                    </td>
                                                    <td class="fieldLabel">Department:  </td> 
                                                    <td>
                                                        <s:select name="Department" 
                                                                  headerKey="" 
                                                                  headerValue="Select"
                                                                  list="{'Sales','Marketing','Operations','Recruiting','GDC','Executive Board'}" 
                                                                  cssClass="inputSelect"/>
                                                    </td>
                                                </tr>
                                            </table>
                                        </s:form>    
                                        <script type="text/JavaScript">
                                                           var cal1 = new CalendarTime(document.forms['frmSearch'].elements['startDate']);
                                                           cal1.year_scroll = true;
                                                           cal1.time_comp = true;
                                            
                                                           var cal2 = new CalendarTime(document.forms['frmSearch'].elements['endDate']);
                                                           cal2.year_scroll = true;
                                                           cal2.time_comp = true;
                                        </script>
                                        <div style="height:500px; 
                                             overflow:auto; border:3px; border-right-width: 0px;border-bottom-width: 0px; border-left-width: 0px; display: block;"  id="addlabel1">
                                            <table cellpadding="1" cellspacing="1" width="95%"  align="center" border="0" class="gridTable" id="RevenueSummery">
                                            </table>
                                        </div>
                                        
                                    </sx:div>
                                    
                                </sx:tabbedpanel>    
                                
                                <!--//END TABBED PANNEL : -->
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
