<!-- 
 *******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  july 24, 2013, 3:25 PM
 *
 * Author  : Prasad Kandregula<vkandregula@miraclesoft.com>
 *
 * File    : QuestionsList.jsp
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
        <title>Hubble Organization Portal :: Questions List</title>
        <sx:head cache="true"/>
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
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmpStandardClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/javascripts/IssueFill.js"/>"></script>
         <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tooltip.js"/>"></script> 
          <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ecertification/QuestionsAjaxList.js"/>"></script> 
        <s:include value="/includes/template/headerScript.html"/>
        <%-- for issue reminder popup --%>
        <script type="text/javascript">
            function win_open2(url,id,priemail){
             // alert(priemail.length);
                    if(priemail.length <=1)
                    {
                        alert("This task is not assigned to any person.Please assign the issue before sending reminder");
                    }else{
                        //alert("id-->"+id);
                        var values=document.getElementById('mailid').innerHTML;
                        url = url+"?issueid="+id;
                        newWindow=window.open(url,'issueid','height=230,width=540');
                    }
        }               
            

        
        </script>
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
   <style>
            * {margin:0; padding:0}
            body {font:11px/1.5 Verdana, Arial, Helvetica, sans-serif; background:#FFF}
            #text {margin:50px auto; width:500px}
            .hotspot {color:#900; padding-bottom:1px; border-bottom:1px dotted #900; cursor:pointer}
            
            #tt {position:absolute; display:block; background:url(images/tt_left.gif) top left no-repeat}
            #tttop {display:block; height:5px; margin-left:5px; background:url(images/tt_top.gif) top right no-repeat; overflow:hidden}
            #ttcont {display:block; padding:2px 12px 3px 7px; margin-left:5px; background:#666; color:#FFF}
            #ttbot {display:block; height:5px; margin-left:5px; background:url(images/tt_bottom.gif) top right no-repeat; overflow:hidden}
        </style>   
    </head>
    <% 
   
    String subTopicId = null;

if(request.getAttribute("subTopicId")!=null)
  subTopicId = request.getAttribute("subTopicId").toString();
   
    %> 
   
  <%--  <body class="bodyGeneral"  oncontextmenu="return false;" onload="getQuestionsList('<%=subTopicId%>');"> --%>
   <body class="bodyGeneral"  oncontextmenu="return false;">
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
                                 
                                <%--    <% if(request.getParameter("issueList")==null)
                                       {%> --%>
                                       
                                        <li ><a href="#" class="selected" rel="issuesTab"  > Questions List </a></li>
                                        
                                 <%--   <%}else{%>
                                    <li ><a href="#" class="selected" rel="IssuesSearchTab">Tasks Search</a></li>
                                     <% }%> --%>
                                    
                                </ul>
                                
                                <%-- <sx:tabbedpanel id="IssuesPanel" cssStyle="width: 845px; height: 500px;padding:5px 5px;" doLayout="true"> --%>
                                <div  style="border:1px solid gray; width:845px;height: 500px; overflow:auto; margin-bottom: 1em;">
                                    <!--//START TAB : -->
                                   
                                   <%--  <% if(request.getParameter("issueList")==null)
                                            {
                                                System.out.println("list");
                                            %> --%>
                                    <div id="issuesTab" class="tabcontent"  >
                                        
                                       <table width="100%" cellpadding="2" cellspacing="2" border="0" >
                        
                        <tr>
                            <td class="headerText" align="right">
                                                            <s:property value="#request.resultMessage || #session.resultMessage"/>
                                                            <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                        </td>
                        </tr>
                        <%
                        if(session.getAttribute("resultMessage")!=null)
                            session.removeAttribute("resultMessage");
                        %>
                        
                            <tr>
                            <td>
                                <div id="loadingMessage12" style="color:red;" align="center"><b><font size="4px" >Loading...Please Wait...</font></b></div>
                                <%--<div id="loadingMessage12" style="display:none;" class="error" align="center" ><b><font size="4px" >Loading...Please Wait</font></b></div>--%>
                            </td>
                        </tr>
                            <td colspan="3" align="center">
                                   <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                    <table id="tblUpdate1" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='700' align="center">
                                        <COLGROUP ALIGN="left" >
                                             <COL width="10">
                                             <COL width="10"> 
                                             <COL width="300">
                                             <COL width="100">
                                             <COL width="100">
                                             <COL width="100">
                                             <COL width="100"> 
                                             <COL width="10">
                                             
                                             
                                </table>
                                    <br>
                                    <center><span id="spnFast" class="activeFile"></span></center> 
                                
                            </td>
                        
                    </tr>
                </table>
                                    </div>
                                 <%--   <%}%> --%>
                                    <!--//END TAB : -->
                                  
                               
                                    
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
        
        
        <script>
$(document).ready(function() {
   getQuestionsList('<%=subTopicId%>');
     });
</script>	
        
        
    </body>
</html>
