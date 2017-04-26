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
 * File    : IssueDetails.jsp
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
-->
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.freeware.gridtag.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hubble Organization Portal :: Account Search</title>
        <%-- <sx:head cache="true"/> --%>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        
        <!-- issues Related JavaScript  -->
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AjaxUtil.js"/>"></script> 
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AccountMerge.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <s:include value="/includes/template/headerScript.html"/>
        <style type="text/css">
            
            .popupItem:hover {
            background: #F2F5A9;
            font: arial;
            font-size:15px;
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
            font-size:15px;
            line-height:13px;
            z-index:100;
            }
            
        </style>
    </head>
  <!--  <body class="bodyGeneral" oncontextmenu="return false;" onload="init();"> -->
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
                                <%--   <sx:tabbedpanel id="accSearch" cssStyle="width: 845px; padding:10px 5px 5px 5px" doLayout="false">
                                <sx:div id="accSearchTab" label="Account Search"> --%>
                                <ul id="accountTabs" class="shadetabs" >
                                    <li ><a href="#" class="selected" rel="accountsListTab">Acounts Merging</a></li>
                                    
                                </ul>
                                
                                <div  style="border:1px solid gray; width:840px; margin-bottom: 1em;">
                                    <div id="accountsListTab" class="tabcontent"   >
                                        <form action="" name="searchForm">
                                            
                                            <table cellpadding="0" border="0" cellspacing="0" width="100%">
                                                <tr>
                                                    <td class="headerText">
                                                        <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                    </td>
                                                </tr>
                                                
                                                <tr><td>
                                                        <table align="center"  border="0" cellpadding="0" cellspacing="0">
                                                            <tr>
                                                                
                                                                <td class="fieldLabel">Original Account :</td>
                                                                <td><input type="text" size="50" name="accountName" autocomplete="off" id="accountName" class="inputTextBlueLarge" onkeyup="getAccountName(this);">                                        
                                                                    <!--<div style="display: none; position: absolute; overflow:auto;" id="menu-popup">
                                                                        <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" ></table>
                                                                    </div>-->
                                                                </td>
                                                                <td class="fieldLabel">Duplicate Account :</td>
                                                                <td>
                                                                    <input type="text" size="50" name="accountName2" autocomplete="off" id="accountName2" class="inputTextBlueLarge" onkeyup="getAccountName(this);">
                                                                    <!--<div style="display: none; position: absolute; overflow:auto;" id="menu-popup1">
                                                                        <table id="completeTable1" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" ></table>
                                                                    </div> -->
                                                                </td>                                                            
                                                                <td style="padding-left:15px;" colspan="4" align="center"><input type="button" value="Merge" class="buttonBg" onclick="mergeAccount();"/></td>                                                            
                                                            </tr>
                                                            <tr>
                                                                <td>&nbsp;</td>
                                                                <td><div style="display: none; position: absolute; overflow:auto;" id="menu-popup">
                                                                        <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" ></table>
                                                                    </div></td>
                                                                <td>&nbsp;</td>
                                                                <td><div style="display: none; position: absolute; overflow:auto;" id="menu-popup1">
                                                                        <table id="completeTable1" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" ></table>
                                                                    </div></td>
                                                            </tr>
                                                        </table>
                                                    </td>
                                                </tr>
                                                <tr><td>     <br>                               
                                                        <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                        <table id="tblUpdate" align="center" class="gridTable" cellpadding='1' cellspacing='1' border='0' width='750' >
                                                            <COLGROUP ALIGN="left">
                                                            <COL width="200">
                                                            <COL width="100">
                                                            <COL width="200">
                                                        </table>
                                                        <br>
                                                        <center><span id="spnFast" class="activeFile"></span></center> 
                                                    </td>
                                                </tr>
                                            </table>
                                        </form>
                                        <%-- </sx:div>
                                </sx:tabbedpanel> --%>
                                    </div>
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
                <td align="center"><s:include value="/includes/template/Footer.jsp"/>   </td>
            </tr>
            <!--//END FOOTER : Record for Footer Background and Content-->
            
        </table>
        <!--//END MAIN TABLE : Table for template Structure-->
           <script type="text/javascript">
		$(window).load(function(){
		
		   init();
		
                
		});
		</script>
    </body>
</html>