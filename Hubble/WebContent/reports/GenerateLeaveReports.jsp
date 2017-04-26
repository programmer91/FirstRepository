<!-- 
 *******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  Nov21, 2013, 3:25 PM
 *
 * Author  : Prasad Kandregula<vkandregula@miraclesoft.com>
 *
 * File    : GenerateLeavesReports.jsp
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
        <title>Hubble Organization Portal :: GenerateLeaves Report</title>
        
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tooltip.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tooltip.js"/>"></script>   
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>   
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>   
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/employee/DateValidator.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ajax/AjaxPopup.js"/>"></script> 
        <script type="text/javascript" src="<s:url value="/includes/javascripts/searchIssue.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ecertification/ecertificationAjax.js"/>"></script>
        <s:include value="/includes/template/headerScript.html"/>
        <%-- for issue reminder popup --%>
       
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
         <script type="text/javascript">

    function getLeavesReport()
{
        
//var practice = document.getElementById("domainIdReport").value;
//var topic = document.getElementById("topicIdReport").value;
//alert(practice);
var fname = document.getElementById("fname").value;
var lname = document.getElementById("lname").value;
var country = document.getElementById("country").value;
var leaveType = document.getElementById("leaveType").value;
var  sDate = document.getElementById("startdate").value;
var  eDate =document.getElementById("enddate").value;
//alert(topic);
if( country == "-1"){
 alert("Please select Country!");
return false;
}
if( sDate == "" || sDate ==null){
 alert("Please select the start date from calender !");
return false;
}

if(eDate == "" || eDate ==null){
 alert("Please select the start date from calender !");
return false;
}

if(leaveType == "-1" )
{
    alert("Please Select Leave Type!");
    return false;
}



else
{
 window.location="../reports/generateExcelReportForLeaves.action?country="+country+"&leaveType="+leaveType+"&startdate="+sDate+"&enddate="+eDate+"&fname="+fname+"&lname="+lname;
 return true;
}

}        
        </script>
    </head>
    	

    
    <body class="bodyGeneral" oncontextmenu="return false;" >
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
        String currentSearch=null;
        boolean blnSortAsc = true;
        HttpServletRequest httpServletRequest;
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
                            <td width="850px" class="cellBorder" valign="top" style="padding:5px 5px;">
                                <!--//START TABBED PANNEL : -->
                                <%
                                    //out.print("naga::"+ request.getAttribute("subnavigateTo").toString());
                                 /// session.setAttribute(ApplicationConstants.ISSUE_TO_TASK,request.getAttribute("subnavigateTo").toString());
                                %>
                                
                                <ul id="accountTabs" class="shadetabs" >
                                  <%--  <li ><a href="#" class="selected" rel="issuesTab"  > IssuesList </a></li>
                                    <li ><a href="#" rel="IssuesSearchTab">Issues Search</a></li> --%>
                                   
                                    
                                     <li ><a href="#" class="selected" rel="ReportSearchTab">Generate Leaves Report</a></li>
                                </ul>
                                
                                <%-- <sx:tabbedpanel id="IssuesPanel" cssStyle="width: 845px; height: 500px;padding:5px 5px;" doLayout="true"> --%>
                                <div  style="border:1px solid gray; width:845px;height: 500px; overflow:auto; margin-bottom: 1em;">
                                    <!--//START TAB : -->

                                    <div id="ReportSearchTab" class="tabcontent"  >   
                                        <s:form name="frmSearch" action="searchExamKeyList" theme="simple" method="post" >
                                        
                                            
                                            <!--The components of searching issues -->
                                            <table border="0" cellpadding="1" cellspacing="1" width="100%" align="center">
                                                   <tr>
                                                    <td class="headerText" align="right">
                                                        
                                                         <input type="button" class="buttonBg" value="Generate Excel" onclick="return getLeavesReport();"/>
                                                    </td>
                                                </tr>
                                              
                                                <tr><td align="center"> 
                                                       <table align="center" border="0" width="80%" cellpadding="0" cellspacing="0">
                                                            <tr>
                                                                <td class="fieldLabel">FirstName:</td>
                                                                <td><input type="text" name="fname" maxlength="15" id="fname" class="inputTextBlue" value=""/></td>
                                                                <td class="fieldLabel">LastName:</td>
                                                                <td><input type="text" name="lname" maxlength="15" id="lname" class="inputTextBlue" value=""/></td>
                                                                <td class="fieldLabel">Live-in Country:<font color="red">*</font></td>
                                                                <td><s:select list="countryList" name="country" id="country" cssClass="inputSelect" headerKey="-1" headerValue="--Please Select--" value="%{country}" onchange="checkCountry();" theme="simple" onkeypress="return handleEnter(this,event);"/>
                                                                </td>
                                                             </tr>  
                                                             <tr>
                                                               
                                                                <td class="fieldLabel">From Date:<font color="red">*</font></td>
                                                                <td><s:textfield id="startdate" name="startdate" value="%{startdate}" cssClass="inputDate" onkeydown="return enableEnter(event);"/>
                                                                    <a href="javascript:cal1.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                         width="20" height="20" border="0" ></a>
                                                                </td>
                                                                <td class="fieldLabel">To Date:<font color="red">*</font></td>
                                                                <td><s:textfield id="enddate" name="enddate" value="%{enddate}" cssClass="inputDate" onkeydown="return enableEnter(event);"/>
                                                                    <a href="javascript:cal2.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                         width="20" height="20" border="0" ></a>
                                                                </td>
                                                                 <td class="fieldLabel">Leave Type:<FONT color="red" SIZE="0.5"><em>*</em></FONT></td>
                                                                 <td>
                                                             <s:select
                                                             name="leaveType"
                                                             id="leaveType"
                                                             headerKey="-1" 
                                                             headerValue="--Please Select--"
                                                             list="{'All','Post Approval','Comptime','Vacation','Timeoff','Cancel-Leave'}" cssClass="inputSelect"  value="%{currentLeave.leaveType}" />  </td> 
                                                               
                                                            </tr>
                                                        </table> 
                                                    </td>
                                                </tr> 
                                                <tr>
                                                    <td>
                                                        <br>
                                                        <div align="center" ><b><font size="2px" color="green" >Note: Total leaves generated by excluding "Saturday" and "Sunday". It has no information about Holidays. So Total Leaves includes Holidays between the Leave days.</font></b></div>
                                                    </td>
                                                </tr>
                                            </table>
                                            
                                        </s:form>
                                        <!-- this script for After loading Form it will instantiate Calender Objects as you require -->
                                        <script type="text/JavaScript">
                                                           var cal1 = new CalendarTime(document.forms['frmSearch'].elements['startdate']);
                                                           cal1.year_scroll = true;
                                                           cal1.time_comp = false;
                                            
                                                           var cal2 = new CalendarTime(document.forms['frmSearch'].elements['enddate']);
                                                           cal2.year_scroll = true;
                                                           cal2.time_comp = false;
                                        </script>
                                        
                                       
                                    </div> 
                                    
                                    <!--//END TAB : -->
                                  
                                    <%--  <sx:div id="IssuesSearchTab" label="Issues Search" > --%>
                                   
                                    
                                    <!--//END TAB : -->
                                    <%--  </sx:tabbedpanel> --%>
                                </div>
                                <!--//END TABBED PANNEL : -->
                                <script type="text/javascript">

var countries=new ddtabcontent("accountTabs")
countries.setpersist(false)
countries.setselectedClassTarget("link") //"link" or "linkparent"
countries.init()

                                </script>
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
