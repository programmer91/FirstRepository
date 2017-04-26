<!-- 
 *******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  October 28, 2013, 3:25 PM
 *
 * Author  : Ajay Tummapala<atummapala@miraclesoft.com>
 *
 * File    : AddPractice.jsp
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
-->
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%-- <%@ taglib prefix="sx" uri="/struts-dojo-tags" %>--%>
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
    <title>Hubble Organization Portal :: Ecertification Add Practice</title>
  <%--  <sx:head cache="true"/> --%>
     <sj:head jqueryui="true"/>
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tooltip.css"/>">
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tooltip.js"/>"></script>   
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>   
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>   
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmpStandardClientValidations.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
     <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ecertification/EcertStandardClientValidation.js"/>"></script>

     

    
    
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
<body class="bodyGeneral" oncontextmenu="return false;"> 

<!--//START MAIN TABLE : Table for template Structure-->
<table class="templateTable1000x580" align="center" cellpadding="0" cellspacing="0">

<!--//START HEADER : Record for Header Background and Mirage Logo-->
<tr class="headerBg">
    <td valign="top">
        <s:include value="/includes/template/Header.jsp" />                    
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
                <ul id="accountTabs" class="shadetabs" >
                     
                        <li ><a href="#" class="selected" rel="issueTab"  >Add Practice </a></li>
                   
                    

                </ul>
                <!--//START TABBED PANNEL : -->
                <div  style="border:1px solid gray; width:845px;height: 500px; overflow:auto; margin-bottom: 1em;">
                <!--//START TAB : -->
                <div id="issueTab" class="tabcontent"  >
                   
                    <s:form name="addPracticeForm" id="addPracticeForm" action="%{currentAction}" method="POST" theme="simple" onsubmit="return practiceClientValidation1();">
                        <s:hidden name="topicId" value="%{topicId}"/>
                        <s:hidden value="%{currentAction}"/>
                        <table width="100%" cellpadding="1" cellspacing="1" border="0" >
                         <tr>
                    <td colspan="6">
                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                            <tr>
                                <td align="left" class="headerText"> </td>
                                
                                <td align="right" class="headerText">
                                    <table cellpadding="0" cellspacing="0" border="0">
                                        <tr>
                                            <td align="left">
                                              
                                            </td>
                                            
                                            
                                                <td valign="middle">
                                                  <%--  <INPUT type="button" CLASS="buttonBg" value="Back to List" onClick="history.go(-1)"> --%>
                                                    
                                         <a href="<s:url value="crmBackToList.action"/>" style="align:center;">
                                            <img alt="Back to List"
                                                 src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                 width="66px" 
                                                 height="19px"
                                             border="0" align="bottom"></a>
                                                </td>
                                          
                                            
                                            
                                        </tr>
                                        <tr>
                                            <td>
                                       <% if(request.getAttribute("resultMessage") != null){
                                            out.println(request.getAttribute("resultMessage"));
                                            session.removeAttribute("resultMessage");
                                        }%>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                              
                                <tr>
                                     
                                      
                                <td  class="fieldLabel">Practice Name :</td>                                                                  
                                <td  ><s:textfield name="domainName" id="domainName" value="%{domainName}" cssClass="inputTextBlue" onchange="practiceClientValidation();"/></td>
                               
                                  <td  class="fieldLabel">Topic Name : </td>
                                    <td >  
                                        <s:textfield name="topicName" id="topicName" value="%{topicName}" cssClass="inputTextBlue" theme="simple" onchange="practiceClientValidation();"/>
                                    </td>
                               <%--     <td  class="fieldLabel">Status : </td>
                                    <td>
                                        <s:select label="Select Status" id="status" 
                                                                       name="status" headerKey="Active"            
                                                                       headerValue="Active"
                                                                       list="%{'Active','Inactive'}" cssClass="inputSelect" value="%{status}" />
                                    </td> --%>
                               <s:hidden id="status" name="status" value="Active"/>
                               <td >
                                                <s:if test="%{currentAction == 'doAddPractice'}">
                                                <s:submit name="submit" value="Add" cssClass="buttonBg" />
                                                 
                                                </s:if>
                                                <s:else>
                                                    <s:submit name="submit" value="Update" cssClass="buttonBg" />
                                                </s:else>
                                                <%-- new --%>
                                                
                                                
                                              
                                            </td>
                                </tr>
                                
                                 
                              
        
                                        </table>
                                    </td>
                                </tr>
                          
                            
                        </table>
                      </s:form>
                    
                    <!-- this script for After loading Form it will instantiate Calender Objects as you require -->
    
                </div>                                       
    </div>                               
    <%--  </sx:tabbedpanel> --%>
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

 

<!--//END DATA RECORD : Record for LeftMenu and Body Content-->

<!--//START FOOTER : Record for Footer Background and Content-->
<tr class="footerBg">
    <td align="center"><s:include value="/includes/template/Footer.jsp"/>   </td>
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

</body>
</html>


