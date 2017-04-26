<!--
*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  January 25, 2008, 6:13 PM
 *
 * Author  : Rajanikanth Teppala<rteppala@miraclesoft.com>
 *
 * File    : Mscreen.jsp
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 -->


<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="com.freeware.gridtag.*" %> 
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd" %>

<html>
    <head>
        <title>Hubble Organization Portal :: Marketing Screen</title>
        <sx:head cache="true"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
        <%--<link rel="stylesheet" href="<s:url value="/includes/css/displaytag.css"/>">--%>
        
        <%--Start--Retrieve the Accounts--%>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/marketing/MarketingAjaxUtil.js"/>"></script>   
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/marketing/LoadAccountContactAjax.js"/>"></script>   
        <%--End--Retrieve the Accounts--%>   
        
        <%--Start--Retrieve the Contacts--%>        
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/marketing/SaveAccountAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/marketing/SaveContactAjax.js"/>"></script>
        <%--End--Retrieve the Contacts--%>
        
        <%--Start--Store the Activities--%>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/marketing/SaveActivityAjax.js"/>"></script>   
        <%--End--Store the Activities--%>
        
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/marketing/loadMarketing.js"/>"></script>   
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <style type="text/css">
            
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
            
            .popupItem:hover {
            background: #F2F5A9;
            font: arial;
            font-size:10px;
            color: black;
            }
            
            .popupRow {
            background: #3E93D4;
            }
            
        </style>
        
        <s:include value="/includes/template/headerScript.html"/>
        
    </head>
    
   <!-- <body class="bodyGeneral" oncontextmenu="return false;" onload="init(); hideLoad();"> -->
    <body class="bodyGeneral" oncontextmenu="return false;">
        
        <%!
        /* Declarations */
        Connection connection;
        String queryString = null;
        String strTmp;
        String strSortCol;
        String strSortOrd = "ASC";
        boolean blnSortAsc = true;
        int intCurr = 1;
        
        String accountSearchBy;
        
        HttpServletRequest request;
        
        String currentAccountId;
        String currentContactId;
        String currentActivityType;        
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
                            <td width="850px" class="cellBorder" valign="top">
                                <table cellpadding="0" cellspacing="0" width="100%" border="0">
                                    
                                    <tr>
                                        <td valign="top"> 
                                            <!--//START TABBED PANNEL :
                                            
                                            ActivityTab
                                            -->
                                            <sx:tabbedpanel id="activityPannel" cssStyle="width:850px; height:550px;padding: 5x 5px 5px 5px;" doLayout="true" useSelectedTabCookie="true" >
                                                
                                                <!--//START TAB : -->
                                                <sx:div id="accountsListTab" label="Accounts List" name="one" cssStyle="overflow:auto;" >
                                                    
                                                    <s:form name="frmAccount" action="" theme="simple">
                                                        <table cellpadding="0" cellspacing="0" width="100%" border="0">
                                                            <tr align="right">
                                                                <td class="headerText">
                                                                    <img alt="Home" 
                                                                         src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" 
                                                                         width="100%" 
                                                                         height="13px" 
                                                                         border="0">
                                                                    
                                                                </td>
                                                                
                                                            </tr>  
                                                            
                                                            <tr>                                
                                                                <td>
                                                                    <table cellpadding="1" cellspacing="1" border="0" width="100%">
                                                                        <tr>
                                                                            <td class="fieldLabel">Name :</td>
                                                                            <td><s:textfield name="accName" id="accName" cssClass="inputTextBlueExtraLarge" value="%{accName || currentMarketing.accName}" onkeyup="fillAccount();"/>
                                                                                <div id="validationMessage"></div>
                                                                                <div style="display: none; position: absolute; overflow:auto;" id="menu-popup">
                                                                                    <table id="completeTable" border="1" bordercolor="#e5e4f2" style="opacity: .8;border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" ></table>
                                                                                </div>
                                                                                <s:hidden name="accountId" id="accountId" value="%{accountId || currentMarketing.accountId}" cssClass="inputTextBlue"/> 
                                                                                
                                                                            </td>
                                                                            <%--
                                                                            <td><input type="button" value="Match" class="buttonBg" onclick="fillAccount();"/></td>   
                                                                            --%>
                                                                            <td><input type="button" value="Retrieve" class="buttonBg" onclick="getAccDetailsData();"/>
                                                                            </td> 
                                                                        </tr> 
                                                                        
                                                                        <tr>
                                                                            <td id="auto-row" colspan="2">&nbsp;<td/>   
                                                                        </tr>    
                                                                        
                                                                        
                                                                        
                                                                    </table>
                                                                </td>
                                                            </tr> 
                                                            
                                                            <tr>
                                                                <td>
                                                                    
                                                                    <table cellpadding="0" cellspacing="0" width="100%" border="0" style="display: none;" id="addlabel1">
                                                                        <tr align="right">
                                                                            <td class="headerText">  
                                                                                <input type="button" value="Save" name="Submit" class="buttonBg" onclick="getAccountDetails();"/>                                                                                
                                                                            </td>
                                                                        </tr>
                                                                        
                                                                        <tr>                                
                                                                            <td>
                                                                                <table cellpadding="1" cellspacing="1" border="0" width="100%">
                                                                                    <tr> 
                                                                                        <td class="fieldLabel">Account Name :</td>
                                                                                        <td>
                                                                                            <s:textfield name="accountName" id="accountName" value="%{currentMarketing.accountName}" cssClass="inputTextBlue"/>                                                                             
                                                                                        </td>
                                                                                        
                                                                                        <td class="fieldLabel">URL :</td>
                                                                                        <td>
                                                                                            <s:textfield name="urlPath" id="urlPath" value="%{currentMarketing.urlPath}" cssClass="inputTextBlue"/>                                                                             
                                                                                        </td>
                                                                                    </tr>
                                                                                    <tr> 
                                                                                        <td class="fieldLabel">Phone :</td>
                                                                                        <td>
                                                                                            <s:textfield name="homePhone" id="homePhone" value="%{currentMarketing.homePhone}" cssClass="inputTextBlue"/>                                                                             
                                                                                        </td>
                                                                                        
                                                                                        <td class="fieldLabel">Stock :</td>
                                                                                        <td>
                                                                                            <s:textfield name="stockSymbol" id="stockSymbol" value="%{currentMarketing.stockSymbol}" cssClass="inputTextBlue"/>                                                                              
                                                                                        </td>
                                                                                    </tr>
                                                                                    
                                                                                    <tr> 
                                                                                        <td class="fieldLabel">LastModified By :</td>
                                                                                        <td>
                                                                                            <s:textfield name="lastModifyBy" id="lastModifyBy" value="%{currentMarketing.lastModifyBy}" cssClass="inputTextBlue" readonly="true"/>                                                                             
                                                                                        </td>
                                                                                        
                                                                                        <td class="fieldLabel">LastModified Date :</td>
                                                                                        <td>
                                                                                            <s:textfield name="lastModifyDate" id="lastModifyDate" value="%{currentMarketing.lastModifyDate}" cssClass="inputTextBlue" readonly="true"/>                                                                        
                                                                                        </td>
                                                                                    </tr>
                                                                                    
                                                                                    <tr>                                                                    
                                                                                        <td> 
                                                                                            <table cellpadding="1" cellspacing="1" width="100%" border="0">
                                                                                                <tr>
                                                                                                    <td class="fieldCheckLabel">
                                                                                                        <fieldset class="tableBorder">
                                                                                                            <legend>B2B</legend><P>                   
                                                                                                            <label><s:checkbox  name="gentran" id="gentran" value="false" fieldValue="true" theme="simple"/>Gentran</label><br>
                                                                                                            <label><s:checkbox  name="harbinger" id="harbinger" value="false" fieldValue="true" theme="simple"/>Harbinger</label><br>
                                                                                                            <label><s:checkbox  name="mercator" id="mercator" value="false" fieldValue="true" theme="simple"/>Mercator</label><br>                                                                                                            
                                                                                                            <label><s:checkbox  name="seeBeyond" id="seeBeyond" value="false" fieldValue="true" theme="simple"/>See Beyond</label><br>
                                                                                                            <label><s:checkbox  name="webMethods" id="webMethods" value="false" fieldValue="true" theme="simple"/>WebMethods</label><br>
                                                                                                            <label><s:checkbox  name="wdi" id="wdi" value="false" fieldValue="true" theme="simple"/>WDI</label><br>
                                                                                                        </fieldset>
                                                                                                    </td>
                                                                                                </tr>
                                                                                            </table>  
                                                                                        </td>
                                                                                        
                                                                                        <td> 
                                                                                            <table cellpadding="1" cellspacing="1" width="100%" border="0">
                                                                                                <tr>
                                                                                                    <td class="fieldCheckLabel">
                                                                                                        <fieldset class="tableBorder">
                                                                                                            <legend>INTEGRATION</legend><P>           
                                                                                                            <label><s:checkbox  name="ics" id="ics" value="false" fieldValue="true" theme="simple"/>ICS</label><br>                                                                                                            
                                                                                                            <label><s:checkbox  name="messageBroker" id="messageBroker" value="false" fieldValue="true" theme="simple"/>Message Broker</label><br>                                                                                                            
                                                                                                            <label><s:checkbox  name="tibco" id="tibco" value="false" fieldValue="true" theme="simple"/>Tibco</label><br>
                                                                                                            <label><s:checkbox  name="vitria" id="vitria" value="false" fieldValue="true" theme="simple"/>Vitria</label><br>
                                                                                                            <label><s:checkbox  name="wps" id="wps" value="false" fieldValue="true" theme="simple"/>WPS</label><br>
                                                                                                            <label><s:checkbox  name="biztalkServer" id="biztalkServer" value="false" fieldValue="true" theme="simple"/>Biztalk Server</label><br>
                                                                                                        </fieldset>
                                                                                                    </td>
                                                                                                </tr>
                                                                                            </table>  
                                                                                        </td>
                                                                                        
                                                                                        
                                                                                        <td> 
                                                                                            <table cellpadding="1" cellspacing="1" width="100%" border="0">
                                                                                                <tr>
                                                                                                    <td class="fieldCheckLabel">
                                                                                                        <fieldset class="tableBorder">
                                                                                                            <legend>ERP</legend><P>        
                                                                                                            <label><s:checkbox  name="jdEdwards" id="jdEdwards" value="false" fieldValue="true" theme="simple"/>JD Edwards</label><br>
                                                                                                            <label><s:checkbox  name="oracleApps" id="oracleApps" value="false" fieldValue="true" theme="simple"/>Oracle Apps</label><br>
                                                                                                            <label><s:checkbox  name="peopleSoft" id="peopleSoft" value="false" fieldValue="true" theme="simple"/>People Soft</label><br>
                                                                                                            <label><s:checkbox  name="sap" id="sap" value="false" fieldValue="true" theme="simple"/>SAP</label><br>
                                                                                                            <label><s:checkbox  name="siebel" id="siebel" value="false" fieldValue="true" theme="simple"/>Siebel</label><br>
                                                                                                            <label><s:checkbox  name="baan" id="baan" value="false" fieldValue="true" theme="simple"/>Baan</label><br>
                                                                                                        </fieldset>
                                                                                                    </td>
                                                                                                </tr>
                                                                                            </table>  
                                                                                        </td>
                                                                                        
                                                                                        <td> 
                                                                                            <table cellpadding="1" cellspacing="1" width="100%" border="0">
                                                                                                <tr>
                                                                                                    <td class="fieldCheckLabel">
                                                                                                        <fieldset class="tableBorder">
                                                                                                            <legend>PORTALS</legend><P>   
                                                                                                            <label><s:checkbox  name="beaPortals" id="beaPortals" value="false" fieldValue="true" theme="simple"/>BEA Portals</label><br>
                                                                                                            <label><s:checkbox  name="oraclePortals" id="oraclePortals" value="false" fieldValue="true" theme="simple"/>Oracle Portals</label><br>
                                                                                                            <label><s:checkbox  name="ibmPortals" id="ibmPortals" value="false" fieldValue="true" theme="simple"/>IBM Portals</label><br>                                                                                                            
                                                                                                            <label><s:checkbox  name="sharePoint" id="sharePoint" value="false" fieldValue="true" theme="simple"/>SharePoint</label><br>
                                                                                                            <label><s:checkbox  name="sapPortals" id="sapPortals" value="false" fieldValue="true" theme="simple"/>SAP Portals</label><br>                                                                                                            
                                                                                                            <label><s:checkbox  name="microsoft" id="microsoft" value="false" fieldValue="true" theme="simple"/>Microsoft</label><br>    
                                                                                                        </fieldset>
                                                                                                    </td>
                                                                                                </tr>
                                                                                            </table>  
                                                                                        </td>
                                                                                    </tr>
                                                                                    
                                                                                </table>                                                            
                                                                            </td>
                                                                        </tr>
                                                                    </table>
                                                                    
                                                                </td>
                                                            </tr>
                                                            
                                                        </table>
                                                        
                                                    </s:form>
                                                </sx:div>
                                                <!--//END TAB : -->
                                                
                                                
                                                <!--//START TAB : -->
                                                <sx:div id="contactTab" label="Contacts" cssStyle="overflow:auto;" > 
                                                    
                                                    <s:form name="frmContact" action="" theme="simple">
                                                        
                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                            
                                                            <tr align="right">
                                                                
                                                                <td class="headerText">
                                                                    <img alt="Home" 
                                                                         src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" 
                                                                         width="100%" 
                                                                         height="13px" 
                                                                         border="0">                                                       
                                                                </td>
                                                                
                                                            </tr>
                                                            
                                                            <tr>                                
                                                                <td>
                                                                    <table cellpadding="1" cellspacing="1" border="0" width="100%">
                                                                        <tr> 
                                                                            <td class="fieldLabel">Name :</td>
                                                                            <td><s:textfield name="contactName" id="contactName" size="15" onclick="fillContact();" cssClass="inputTextBlueExtraLarge" theme="simple"  value="%{contactName || currentMarketing.contactName}"/>
                                                                                <div id="validationMessage"></div>
                                                                                <div style="display: none; position: absolute; overflow:auto;" id="menu-popup1">
                                                                                    <table id="completeTable1" border="1" bordercolor="#e5e4f2" style="opacity: .8;border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" ></table>
                                                                                </div>
                                                                                <s:hidden name="contactsId" id="contactsId" cssClass="inputTextBlue" value="%{contactsId || currentMarketing.contactsId}"/>                                                                     
                                                                                <s:hidden name="getAccountId" id="getAccountId" cssClass="inputTextBlue" value="%{getAccountId || currentMarketing.getAccountId}"/>
                                                                            </td>                                                                              
                                                                            <%--
                                                                            <td><input type="button" value="Match" class="buttonBg" onclick="fillContact();" /></td>
                                                                            --%>
                                                                            <td><input type="button" value="Retrieve" class="buttonBg" onclick="getContDetailsData();"/></td>    
                                                                            
                                                                        </tr>
                                                                        
                                                                        <tr>
                                                                            <td height="10px"></td>
                                                                            <td id="auto-row1" colspan="2">&nbsp; </td>
                                                                        </tr>
                                                                        
                                                                    </table>
                                                                </td>
                                                            </tr> 
                                                            <tr>                                
                                                                <td>     
                                                                    <table cellpadding="1" cellspacing="1" border="0" width="100%"  style="display:none;" id="addlabel2">
                                                                        <tr align="right">
                                                                            <td class="headerText" colspan="6">  
                                                                                <input type="button" value="Save" name="Submit" class="buttonBg" onclick="getContactDetails();"/>                                                                                
                                                                            </td>
                                                                        </tr>
                                                                        <tr>  
                                                                            <td class="fieldLabel">First Name :</td>                                                    
                                                                            <td>
                                                                                <s:textfield name="firstNames" id="firstNames" value="%{currentMarketing.firstNames}" cssClass="inputTextBlue"/>                                                                           
                                                                            </td>                                                     
                                                                            
                                                                            <td class="fieldLabel">Last Name :</td>                                                    
                                                                            <td>
                                                                                <s:textfield name="lastNames" id="lastNames" value="%{currentMarketing.lastNames}" cssClass="inputTextBlue"/>                                                                           
                                                                            </td>     
                                                                        </tr>
                                                                        
                                                                        <tr>
                                                                            <td class="fieldLabel">Middle Name :</td>                                                    
                                                                            <td>
                                                                                <s:textfield name="middleName" id="middleName" value="%{currentMarketing.middleName}" cssClass="inputTextBlue"/>                                                                           
                                                                            </td>
                                                                            
                                                                            <td class="fieldLabel">Email :</td>                                                    
                                                                            <td>
                                                                                <s:textfield name="emails" id="emails" value="%{currentMarketing.emails}" cssClass="inputTextBlue"/>                                                                           
                                                                            </td>   
                                                                        </tr>
                                                                        
                                                                        <tr>
                                                                            <td class="fieldLabel">Phone :</td>                                                    
                                                                            <td>
                                                                                <s:textfield name="homePhoneNo" id="homePhoneNo" value="%{currentMarketing.homePhoneNo}" cssClass="inputTextBlue"/>                                                                           
                                                                            </td>  
                                                                            
                                                                            <td class="fieldLabel">Source :</td>                                                    
                                                                            <td>
                                                                                <s:textfield name="source" id="source" value="%{currentMarketing.source}" cssClass="inputTextBlue"/>                                                                           
                                                                            </td>      
                                                                        </tr> 
                                                                        
                                                                        
                                                                    </table>
                                                                    
                                                                </td>          
                                                            </tr>    
                                                            
                                                        </table>
                                                        
                                                    </s:form>
                                                </sx:div>                                                
                                                
                                                <!--//START TAB : -->
                                                
                                                <sx:div id="ActivityTab" label="Activities" > 
                                                    
                                                    <%
                                                    strTmp = request.getParameter("txtCurr");
                                                    if (strTmp != null){
                                                        try {
                                                            intCurr = Integer.parseInt(strTmp);
                                                        } catch (NumberFormatException NFEx) {
                                                            NFEx.printStackTrace();
                                                        }
                                                    }else{
                                                        intCurr = 1;
                                                    }
                                                    
                                                    strSortCol = "ActivityType";
                                                    try{
                                                        connection = ConnectionProvider.getInstance().getConnection();
                                                        
                                                        if(request.getAttribute("accountSearchBy") == "myAccounts"){
                                                            queryString = session.getAttribute(ApplicationConstants.QS_ACTIVITY_QUERY).toString();
                                                        }
                                                    
                                                    %>
                                                    
                                                    
                                                    <s:form name="frmDBGrid" action="" theme="simple" method="POST">
                                                        <table cellpadding="0" cellspacing="0" width="100%" border="0">
                                                            <tr align="right">
                                                                <td class="headerText" colspan="6">                                                                     
                                                                    <input type="button" value="Save" class="buttonBg" onclick="getActivityDetailsData();"/>                                                                    
                                                                </td>                                                   
                                                            </tr>    
                                                            
                                                            <tr>
                                                                <td>
                                                                    <table cellpadding="1" cellspacing="1" border="0" width="100%">   
                                                                        
                                                                        <tr>
                                                                            <s:hidden name="contactActivId" id="contactActivId" value="%{contactActivId || currentMarketing.contactActivId}" cssClass="inputTextBlue"/>   
                                                                            <s:hidden name="accountActivId" id="accountActivId" value="%{accountActivId || currentMarketing.accountActivId}" cssClass="inputTextBlue"/>
                                                                            <s:hidden name="activityId" id="activityId" value="%{activityId || currentMarketing.activityId}" cssClass="inputTextBlue"/>
                                                                            
                                                                            <td width="130px" class="fieldLabel">Activity Type :</td>
                                                                            <td><s:select list="activityTypeList" name="activityType" id="activityType" value="%{currentMarketing.activityType}" cssClass="inputSelect" /></td>
                                                                            <td class="fieldLabel">Priority :</td>
                                                                            <td><s:select list="priorityList" name="priority" id="priority" value="%{currentMarketing.priority}" cssClass="inputSelect" /></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td class="fieldLabel">Campaign Name :</td>
                                                                            <td><s:select list="campaignIdMap" name="campaignId" id="campaignId" value="%{currentMarketing.campaignId}" cssClass="inputSelect"  size="5"/></td>
                                                                            <td class="fieldLabel">Assigned To :</td>
                                                                            <td><s:select list="assignedToIdMap" 
                                                                                              headerKey="-1"
                                                                                              headerValue="--Please Select--" 
                                                                                              name="assignedToId" id="assignedToId"
                                                                                              value="%{currentMarketing.assignedToId}" 
                                                                                          cssClass="inputSelectLarge"  /></td>                                                                            
                                                                        </tr>
                                                                        <tr>
                                                                            
                                                                            <td class="fieldLabel">Status :</td>
                                                                            <td><s:select list="activityStatusList" name="status" id="status" value="%{currentMarketing.status}" cssClass="inputSelect"/></td>
                                                                            <td class="fieldLabel">Due Date :</td>
                                                                            <td>
                                                                                <s:textfield name="dueDate" id="dueDate" value="%{currentMarketing.dueDate}" cssClass="inputTextBlue"/>
                                                                                <a href="javascript:cal1.popup();">
                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                     width="20" height="20" border="0"></a>
                                                                            </td>
                                                                        </tr>
                                                                        
                                                                        <script type="text/JavaScript">
                                                var cal1 = new CalendarTime(document.forms['frmDBGrid'].elements['dueDate']);
				                cal1.year_scroll = true;
				                cal1.time_comp = true;                                            
                                                                        </script>
                                                                        
                                                                        <tr>
                                                                            
                                                                            <td class="fieldLabel">Alarm :</td>
                                                                            <td colspan="3" align="left"><s:checkbox name="alarm" id="alarm" value="%{currentMarketing.alarm}" fieldValue="true" theme="simple"/> </td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td  class="fieldLabel" valign="top">Description :</td>                                                                  
                                                                            <td  colspan="3"><s:textfield name="description" id="description" value="%{currentMarketing.description}" cssClass="inputTextBlueExtraLarge"  size="255"/></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td  class="fieldLabel" valign="top">Comments :</td>                                                                  
                                                                            <td  colspan="3">
                                                                                <s:textarea name="comments" id="comments" rows="3" cols="80" value="%{currentMarketing.comments}" cssClass="inputTextarea"/>
                                                                            </td>
                                                                        </tr>
                                                                        
                                                                        <tr align="left">
                                                                            <td class="headerText" colspan="20">                                                                                                                                                 
                                                                                Search Activities For This Contact
                                                                            </td>
                                                                        </tr>
                                                                        
                                                                        <tr>
                                                                            <td width="130px"class="fieldLabel">Activity Type :</td>
                                                                            <td><s:select list="activityTypeList" name="activityTypeName" id="activityTypeName" cssClass="inputSelect" /></td>
                                                                            <td>
                                                                                <input type="button" value="Search" class="buttonBg" onclick="getActivityForm();"/>
                                                                                <input type="button" value="List All" class="buttonBg" onclick="getActivityListAll();"/>
                                                                            </td>
                                                                        </tr>
                                                                    </table>    
                                                                </td>
                                                            </tr>
                                                            
                                                            <tr>
                                                                <td>
                                                                    
                                                                    <div style="width:700px;" id="addlabel3">
                                                                        
                                                                        <s:if test="%{showGrid == 'Visible'}">
                                                                            <grd:dbgrid id="tblAccountList" name="tblAccountList" width="100" pageSize="8" 
                                                                                        currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                                        dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                                
                                                                                <grd:gridpager imgFirst="../includes/images/DBGrid/First.gif" imgPrevious="../includes/images/DBGrid/Previous.gif" 
                                                                                               imgNext="../includes/images/DBGrid/Next.gif" imgLast="../includes/images/DBGrid/Last.gif"/>
                                                                                
                                                                                <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" 
                                                                                                imageAscending="../includes/images/DBGrid/ImgAsc.gif" 
                                                                                                imageDescending="../includes/images/DBGrid/ImgDesc.gif"/>    
                                                                                
                                                                                <grd:imagecolumn headerText="Edit" width="4" HAlign="center" imageSrc="../includes/images/DBGrid/newEdit_17x18.png"                                                                            
                                                                                                 linkUrl="getActivity.action?id={Id}&accountId={AccountId}&contactId={ContactId}" imageBorder="0"
                                                                                                 imageWidth="16" imageHeight="16" alterText="Click to edit"></grd:imagecolumn>
                                                                                
                                                                                <grd:textcolumn dataField="Description"      headerText="Description" width="30"/>
                                                                                <grd:textcolumn dataField="ActivityType"      headerText="ActivityType" width="20"/>
                                                                                <grd:textcolumn dataField="AssignedToId"   headerText="AssignedTo"  width="20"/>  
                                                                                <grd:textcolumn dataField="Status"       headerText="Status"     width="15"/>     
                                                                                <grd:datecolumn dataField="DateDue" headerText="Due Date" dataFormat="MM-dd-yyyy" width="15" />
                                                                                
                                                                            </grd:dbgrid>
                                                                        </s:if>
                                                                        
                                                                        <input type="hidden" name="txtCurr" value="<%=intCurr%>">
                                                                        <input type="hidden" id="txtSortCol" name="txtSortCol" value="<%=strSortCol%>">
                                                                        <input type="hidden" id="txtSortAsc" name="txtSortAsc" value="<%=strSortOrd%>">
                                                                        
                                                                        <input type="hidden" name="submitFrom" value="dbGrid">     
                                                                        
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            
                                                        </table>     
                                                        
                                                    </s:form>
                                                    <%
                                                    connection.close();
                                                    connection = null;
                                                    }catch(Exception ex){
                                                        ex.printStackTrace();
                                                    }finally{
                                                        if(connection!= null){
                                                            connection.close();
                                                            connection = null;
                                                        }
                                                    }
                                                    %>
                                                    
                                                </sx:div>
                                                
                                            </sx:tabbedpanel>
                                            <!--//END TABBED PANNEL : -->
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
                <td align="center">&reg; 2007  Mirage2 - All Rights Reserved.</td>
            </tr>
            <!--//END FOOTER : Record for Footer Background and Content-->
            
            <tr>
                <td>
                    
                    <div style="z-index:5; display: none; position: absolute; left:320px;overflow: auto;" id="menu-popup">
                        <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed black;opacity: .8;" cellpadding="0" class="cellBorder" cellspacing="0" />
                    </div>
                    
                </td>
            </tr>
            
        </table>
        <!--//END MAIN TABLE : Table for template Structure-->
        <script type="text/javascript">
		$(window).load(function(){
	init(); 
	hideLoad();

		});
		</script>
    </body>
</html>
