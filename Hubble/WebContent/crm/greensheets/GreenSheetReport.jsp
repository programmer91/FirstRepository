
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
 * File    : GreenSheetReport.jsp
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
-->

<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%-- <%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<%@ page import="java.util.List" %>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hubble Organization Portal :: GreenSheet Report</title>
        <sx:head cache="false"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/javascript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>  
        <script type="text/javascript" src="<s:url value="/includes/javascripts/GreenSheetSearch.js"/>"></script>  
        <script type="text/javascript" src="<s:url value="/includes/javascripts/GreenSheetAjaxUtil.js"/>"></script>  
        <script type="text/javascript" src="<s:url value="/includes/javascripts/GreenSheetUtil.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EnableEnter.js"/>"></script>
         <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <!-- issues Related JavaScript  -->
                
        <s:include value="/includes/template/headerScript.html"/>
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
  <!--  <body class="bodyGeneral" oncontextmenu="return false;" onload="defaultDates(),init1();"> -->
    <body class="bodyGeneral" oncontextmenu="return false;">
        
        <script type="text/JavaScript">
            document.onmousemove = showMousePos;
        </script>
        
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
                            <td width="850px" class="cellBorder" valign="top" style="padding:10px 5px 5px 5px;">
                              <ul id="accountTabs" class="shadetabs" >
                                    <li ><a href="#" class="selected" rel="GreenSheetSearch"  > GreenSheet Report </a></li>
                                   
                                </ul>
                                
                                <div  style="border:1px solid gray; width:840px;height: 500px; overflow:auto; margin-bottom: 1em;">
                                    <%-- <sx:tabbedpanel id="GreenSheetSearch" cssStyle="width: 845px; height: 500px;padding:10px 5px 5px 5px" useSelectedTabCookie="true" doLayout="true"> --%>
                                    <div id="GreenSheetSearch" class="tabcontent"  >  
                                        <%--  <sx:div id="greenSheetSearchTab" label="GreenSheet Report" cssStyle="overflow: auto;"> --%>
                                        
                                        <s:form action="" name="frmAddGreenSheet" theme="simple">
                                            
                                            <table border="0" cellspacing="0" cellpadding="0" width="100%">
                                                
                                                <tr align="right">
                                                    <td class="headerText" colspan="9">
                                                        <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">                                                        
                                                    </td>
                                                </tr>
                                                
                                                <tr>
                                                    <td>
                                                        <table border="1" width="100%" cellpadding="1" cellspacing="0">
                                                            <tr>
                                                                <td colspan="6" valign="top">
                                                                    <table border="0" width="100%" cellpadding="1" cellspacing="0">        
                                                                        <tr>
                                                                            <td class="fieldLabel">Query Type : </td>
                                                                            <td><s:select list="{'Active PO','Expiring PO','New PO','PO BY Account','Rolled Off'}"                                                                                             
                                                                                              name="poType" id="poType" value='Active PO' cssClass="inputSelect" onchange="checkPoType();"/>         
                                                                            </td>
                                                                            <td><input type ="button" value="Query Details" class="buttonBg" onclick="queryDetailsToggle()"/></td>
                                                                            <td>
                                                                                <input type="button" value="Search" class="buttonBg" onclick="load();" />   
                                                                            </td>    
                                                                            
                                                                        </tr>
                                                                        
                                                                    </table>
                                                                </td>    
                                                            </tr>
                                                            <tr>
                                                                <td colspan="6" valign="top">                                                        
                                                                    <div id="queryDetails" style="display: inline">
                                                                        <table border="0" width="100%" cellpadding="1" cellspacing="0">
                                                                            
                                                                            <tr class="headerText">            
                                                                                <td colspan="6" class="headerTextNormal">
                                                                                    Query Details:             
                                                                                </td>
                                                                            </tr>
                                                                            
                                                                            <tr>
                                                                                <td>
                                                                                    
                                                                                    <tr>
                                                                                        <td class="fieldLabel">PO Status :  </td>
                                                                                        <td colspan="5"><s:select list="{'Open','Received','Cancelled','Closed','All'}" name="poStatus" id="poStatus" value='Open'  cssClass="inputSelect" /></td>
                                                                                    </tr>
                                                                                    
                                                                                </td>
                                                                            </tr>
                                                                            
                                                                            <tr>
                                                                                <td class="fieldLabel">Account :</td>
                                                                                <td colspan="2">
                                                                                    <s:textfield name="customerName" id="customerName" cssClass="inputTextBlueEmail" onkeyup="fillCustomer();" onkeydown="return enableEnter(event);"/>
                                                                                    <s:hidden id="onlyGreensheet" name="onlyGreensheet" value="1"/>
                                                                                    <div id="validationMessage"></div>
                                                                                    
                                                                                    <s:hidden name="consultantId" id="consultantId"/>
                                                                                </td>    
                                                                                
                                                                                <td class="fieldLabel">Country :</td>                                                                                
                                                                                <td  colspan="2">
                                                                                    <s:select 
                                                                                        list="countryList" 
                                                                                        name="country" 
                                                                                        id="country" 
                                                                                        cssClass="inputSelect"
                                                                                        headerKey="-1"
                                                                                        headerValue="--Please Select--"
                                                                                        theme="simple"/>
                                                                                </td>
                                                                            </tr>
                                                                            
                                                                            
                                                                            <tr>
                                                                                <td class="fieldLabel">EmpFirst Name :</td>
                                                                                <td colspan="2"><s:textfield name="empFname" id="empFname" cssClass="inputTextBlue" onkeydown="return enableEnter(event);"/></td>
                                                                                
                                                                                <td class="fieldLabel">EmpLast Name :</td>
                                                                                <td colspan="2"><s:textfield name="empLname" id="empLname" cssClass="inputTextBlue" onkeydown="return enableEnter(event);"/></td>
                                                                            </tr>
                                                                            
                                                                            
                                                                            
                                                                            <tr>
                                                                                <td class="fieldLabel">PO StartDate :</td>
                                                                                <td><s:textfield id="poStartDateFrom" name="poStartDateFrom" cssClass="inputDate" onkeydown="return enableEnter(event);"/>        
                                                                                    <a href="javascript:cal1.popup();">
                                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                         width="20" height="20" border="0" ></a>
                                                                                    
                                                                                </td>
                                                                                <td><s:textfield id="poStartDateTo" name="poStartDateTo" cssClass="inputDate" onkeydown="return enableEnter(event);"/>        
                                                                                    <a href="javascript:cal2.popup();">
                                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                         width="20" height="20" border="0" ></a>
                                                                                </td>
                                                                                <td class="fieldLabel">PO EndDate :</td>
                                                                                <td><s:textfield id="poEndDateFrom" name="poEndDateFrom" cssClass="inputDate" onkeydown="return enableEnter(event);"/>
                                                                                    <a href="javascript:cal3.popup();">
                                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                         width="20" height="20" border="0" ></a>
                                                                                </td>
                                                                                <td><s:textfield id="poEndDateTo" name="poEndDateTo" cssClass="inputDate" onkeydown="return enableEnter(event);"/>
                                                                                    <a href="javascript:cal4.popup();">
                                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                         width="20" height="20" border="0" ></a>
                                                                                </td>
                                                                            </tr>  
                                                                            
                                                                            <tr>
                                                                                <td class="fieldLabel">Emp StartDate :</td>
                                                                                <td><s:textfield id="empStartDateFrom" name="empStartDateFrom" cssClass="inputDate" onkeydown="return enableEnter(event);"/>
                                                                                    <a href="javascript:cal5.popup();">
                                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                         width="20" height="20" border="0" ></a>
                                                                                </td>
                                                                                <td><s:textfield id="empStartDateTo" name="empStartDateTo" cssClass="inputDate" onkeydown="return enableEnter(event);"/>
                                                                                    <a href="javascript:cal6.popup();">
                                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                         width="20" height="20" border="0" ></a>
                                                                                </td>
                                                                                
                                                                                <td class="fieldLabel">Emp EndDate :</td>
                                                                                <td><s:textfield id="empEndDateFrom" name="empEndDateFrom" cssClass="inputDate" onkeydown="return enableEnter(event);"/>
                                                                                    <a href="javascript:cal7.popup();">
                                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                         width="20" height="20" border="0" ></a>
                                                                                </td>
                                                                                <td><s:textfield id="empEndDateTo" name="empEndDateTo" cssClass="inputDate" onkeydown="return enableEnter(event);"/>
                                                                                    <a href="javascript:cal8.popup();">
                                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                         width="20" height="20" border="0" ></a>
                                                                                </td>
                                                                            </tr>
                                                                            
                                                                        </table>   
                                                                    </div>
                                                                </td>    
                                                            </tr>
                                                        </table>
                                                    </td>
                                                </tr>
                                                
                                                <tr>
                                                    <td colspan="3">
                                                        <s:textfield cssStyle="display:none;" name="inputRowData" id="inputRowData" cssClass="inputTextBlue" readonly="true"/> 
                                                    </td>
                                                </tr>
                                                
                                                <tr>
                                                    <td>
                                                        
                                                        <br>
                                                        <div id="greensheetList" style="display: inline">
                                                            <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                            <table id="tblUpdate" cellpadding='0' cellspacing='0' border='0' class="gridTable" width='90%' align="center">
                                                                <COLGROUP ALIGN="left" >
                                                                <COL width="10%">
                                                                <COL width="20%">
                                                                <COL width="10%">
                                                                <COL width="10%">
                                                                
                                                                <COL width="10%">
                                                                <COL width="10%">
                                                                <COL width="10%">
                                                                <COL width="10%">
                                                            </table>
                                                            <br>
                                                            <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                        </div>
                                                        <div id="menuDiv" align="center" style="display:none;position: absolute;top: 120px; left: 180px;">
                                                            <table border="1" bgcolor="#FBFBB1" width="100">
                                                                <tr>
                                                                    <td>
                                                                        <a id="detailsLink"><font class="fieldLabel">Details</font></a>
                                                                        <br>
                                                                        <!--<a><font class="fieldLabel">Send Alert</font></a>
                                                                        <br>-->
                                                                        <a id="cancelLink"><font class="fieldLabel">Cancel</font></a>
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </div>
                                                    </td>
                                                </tr>
                                                
                                                
                                            </table>
                                            
                                        </s:form>
                                        
                                        
                                        <script type="text/JavaScript">
                        var cal1 = new CalendarTime(document.forms['frmAddGreenSheet'].elements['poStartDateFrom']);
	           cal1.year_scroll = true;
	            cal1.time_comp = false;
            var cal2 = new CalendarTime(document.forms['frmAddGreenSheet'].elements['poStartDateTo']);
	      cal2.year_scroll = true;
	   cal2.time_comp = false;
           
           var cal3 = new CalendarTime(document.forms['frmAddGreenSheet'].elements['poEndDateFrom']);
	           cal3.year_scroll = true;
	            cal3.time_comp = false;
            var cal4 = new CalendarTime(document.forms['frmAddGreenSheet'].elements['poEndDateTo']);
	      cal4.year_scroll = true;
	   cal4.time_comp = false;
           
           var cal5 = new CalendarTime(document.forms['frmAddGreenSheet'].elements['empStartDateFrom']);
	           cal5.year_scroll = true;
	            cal5.time_comp = false;
            var cal6 = new CalendarTime(document.forms['frmAddGreenSheet'].elements['empStartDateTo']);
	      cal6.year_scroll = true;
	   cal6.time_comp = false;
           
           var cal7 = new CalendarTime(document.forms['frmAddGreenSheet'].elements['empEndDateFrom']);
	           cal7.year_scroll = true;
	            cal7.time_comp = false;
            var cal8 = new CalendarTime(document.forms['frmAddGreenSheet'].elements['empEndDateTo']);
	      cal8.year_scroll = true;
	   cal8.time_comp = false;
                                        </script>      
                                        
                                        
                                        
                                        
                                        <%--    </sx:div> --%>
                                    </div>
                                    <%--  </sx:tabbedpanel> --%>
                                </div>
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
                <td align="center"><s:include value="/includes/template/Footer.jsp"/></td>
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
		
		defaultDates();
		init1();
		
                
		});
		</script>
    </body>

</html>