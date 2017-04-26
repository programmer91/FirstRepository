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
 * File    : ConsultantSearch.jsp
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
-->

<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.util.List" %>
<%@ page import="com.mss.mirage.recruitment.*"%>
<%@ page import="com.freeware.gridtag.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hubble Organization Portal :: Consultant Search123</title>
        
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <!-- issues Related JavaScript  -->
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ConsultantAjaxUtil.js"/>"></script>                 
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ConsultantSearch.js"/>"></script> 
        <%--<script type="text/JavaScript" src="<s:url value="/includes/javascripts/EnableEnter.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/StringUtility.js"/>"></script>--%>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>  
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ajax/AjaxPopup.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/LoadConsultantAjax.js"/>"></script> 
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ConsultantResumeSearch.js"/>"></script> 
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ConsultantResumeAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>   
        
        <script type="text/JavaScript">
            function helpPopup(url) {
                newwindow=window.open(url,'name','height=500,width=600');
                if (window.focus) {newwindow.focus()}
                return false;
            }
        </script>
        
        <s:include value="/includes/template/headerScript.html"/>        
    </head>
    
    <body class="bodyGeneral" oncontextmenu="return false;">
        
        <%!        
        String strStartGrid;
        String strEndGrid;
        String searchString;
        String pathName;
        
        int resultCount=0;
        int strIntStartGrid;
        int strIntEndGrid;
        
        
        /* Declarations */
        Connection connection;
        String queryString;
        String strTmp;
        String strSortCol;
        String strSortOrd;
        String userId;
        String submittedFrom;
        String action;
        
        int role;
        int intSortOrd = 0;
        int intCurr;
        boolean blnSortAsc = true;
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
                            <td width="850px" class="cellBorder" valign="top"  style="padding-left:10px;padding-top:5px;">
                                
                                <ul id="ConsultantSearchTabs" class="shadetabs" >
                                <!--    <li ><a href="#" rel="consultantSearchTab"  > Consultant Search </a></li> -->
                                    <li ><a href="#" rel="resumeSearchTab">Resume Search</a></li> 
                                </ul>
                                <%-- <sx:tabbedpanel id="consultantSearch" cssStyle="width: 845px; height: 500px;padding:10px 5px 5px 5px" useSelectedTabCookie="true" doLayout="true"> --%>
                                <%--    <sx:div id="consultantSearchTab" label="Consultant Search" cssStyle="overflow: auto;"> --%>
                                <div  style="border:1px solid gray; width:830px;height: 500px; overflow:auto; margin-bottom: 1em;">
                                    <div id="consultantSearchTab" class="tabcontent"  >
                                        
                                        <s:form action="searchConsultant" name="consultantsearchForm" theme="simple">
                                            <table cellpadding="0" border="0" cellspacing="0" width="100%">     
                                                
                                                <tr align="right">
                                                    <td class="headerText" colspan="6">
                                                        <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                        
                                                    </td>
                                                </tr>
                                                
                                                <tr>
                                                    <td>
                                                        
                                                        <table border="0" width="100%" cellpadding="1" cellspacing="0">
                                                            <tr>
                                                                <td class="fieldLabel"> Name :</td>
                                                                <td><s:textfield name="fiName" id="fiName" cssClass="inputTextBlueLarge" value="%{fiName}"/></td>                                        
                                                                <td class="fieldLabel">Skill Set :</td>
                                                                <td colspan="3"><s:textfield name="skills" id="skillSet" cssClass="inputTextBlueLarge" value="%{skills}"/></td>
                                                            </tr>
                                                            
                                                            <tr>
                                                                
                                                                <td class="fieldLabel">Email :</td>
                                                                <td><s:textfield name="email1" id="email1" value="%{email1}" cssClass="inputTextBlueLarge"/></td>
                                                                
                                                                <td class="fieldLabel">Comments :</td>
                                                                <td colspan="3"><s:textfield name="comments" id="comments" cssClass="inputTextBlueLarge" value="%{comments}"/></td>                                                               
                                                            </tr>
                                                            
                                                            <tr>
                                                                <td class="fieldLabel">State :</td>
                                                                <td>                                                                    
                                                                    <s:select list="statesList"  name="location" id="location" headerKey="" headerValue="-- Select --" cssClass="inputSelectLarge"  value="%{location}" />
                                                                </td>
                                                                <td class="fieldLabel">Work Authorization :</td>
                                                                <td colspan="3">                                                                    
                                                                    <s:select headerKey="" headerValue="--Please Select--" list="{'US Citizen','Green Card','EAD','H1','TN Permit Visa','Need H1B','Student Visa'}" 
                                                                              name="workAuthorization" id="workAuthorization" value="%{workAuthorization}" cssClass="inputSelectLarge" />
                                                                </td>                                                                
                                                            </tr>
                                                            
                                                            <tr>
                                                                <td class="fieldLabel">Source :</td>
                                                                <td>
                                                                    <s:textfield name="source" id="source" cssClass="inputTextBlueLarge" value="%{source}"/>
                                                                </td>
                                                                
                                                                <s:if test="#session.isUserManager == 1 || #session.isUserTeamLead==1">
                                                                    <td class="fieldLabel"> Created By :</td>
                                                                    <td>
                                                                        <s:select headerKey="" headerValue="--Please Select--" list="assignedMembers" name="assignedTo" id="assignedTo" value="%{assignedTo}" cssClass="inputSelectLarge"/>
                                                                    </td>
                                                                </s:if>
                                                                <s:else>
                                                                    <td class="fieldLabel"> Created By :</td>
                                                                    <td>
                                                                        <s:select headerKey="" headerValue="--Please Select--" list="assignedMembers" name="assignedTo" id="assignedTo" value="%{assignedTo}" cssClass="inputSelectLarge" disabled="true"/>
                                                                    </td>    
                                                                </s:else>
                                                                
                                                                <%--<td class="fieldLabel">Practice :</td> 
                                                                <td colspan="2">                                                                    
                                                                    <s:select headerKey="-1" headerValue="--Please Select--" list="practiceList" 
                                                                              id="practiceId" name="practiceId" cssClass="inputSelectLarge" onkeydown="return enableEnter(event);"/>
                                                                    
                                                                </td>--%>
                                                                
                                                                <%--<td style="padding-left:15px;"><input type="button" value="Search" class="buttonBg" onclick="load();"/></td>
                                                                <FONT color="red" SIZE="0.5"> (Add * for Efficient Search)</FONT>
                                                                --%>
                                                                <td style="padding-left:15px;"><input type="submit" value="Search" class="buttonBg"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td></td>
                                                                <td>
                                                                    <s:textfield cssStyle="display:none;" name="inputRowData" id="inputRowData" cssClass="inputTextBlue" readonly="true"/> 
                                                                </td>
                                                            </tr>
                                                            
                                                            
                                                            <tr>
                                                            <td class="fieldLabel">Last Updated Between</td></tr><tr>
                                                                <td class="fieldLabel">From Date:</td><td><s:textfield name="startDate" id="startDate" cssClass="inputTextBlueLarge" onchange="validateTimestamp(this);"/>
                                                                    <a href="javascript:cal1.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif"
                                                                         width="20" height="20" border="0"></a>
                                                                </td>
                                                                <td class="fieldLabel">To Date:<FONT color="red" SIZE="0.5"><em>*</em></FONT></td>
                                                                <td>
                                                                    <s:textfield name="endDate"  id="endDate" cssClass="inputTextBlueLarge" onchange="validateTimestamp(this);"/>
                                                                    <a href="javascript:cal2.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif"
                                                                         width="20" height="20" border="0"></a>
                                                                </td>
                                                                
                                                            </tr>
                                                            
                                                        </table>
                                                        
                                                    </td>
                                                </tr>
                                                
                                                <%--<tr>
                                                    <td>
                                                        
                                                        <br>
                                                        <div id="consultantList" style="display: block">
                                                            <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                            <table id="tblUpdate" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='90%' align="center">
                                                                <COLGROUP ALIGN="left" >
                                                                <COL width="3%">
                                                                <COL width="20%">
                                                                <COL width="20%">
                                                                <COL width="15%">
                                                                <COL width="30%">
                                                                <COL width="2%">
                                                            </table>
                                                            <br>
                                                            <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                        </div>
                                                    </td>
                                                </tr>--%>
                                                
                                                <tr>
                                                    <td>
                                                        <br>
                                                        <div id="resumeList" style="display:none" >
                                                            <input type="button" value="Back To List" class="buttonBg" onclick="backToList();" align="left">                                                        
                                                            <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                            <table id="tblResume" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='90%' align="center">
                                                                <COLGROUP ALIGN="left" >
                                                                <COL width="30%">
                                                                <COL width="30%">
                                                                <COL width="30%">
                                                                
                                                            </table>
                                                            <br>
                                                            <!--<center><span id="spnFast1" class="activeFile" style="display: none;"></span></center>-->
                                                        </div>
                                                    </td>
                                                </tr>
                                            </table>
                                            
                                        </s:form>
                                        
                                        
                                        
                                        <script type="text/JavaScript">
                                            var cal1 = new CalendarTime(document.forms['searchConsultant'].elements['startDate']);
				                 cal1.year_scroll = true;
				                   cal1.time_comp = true;

                                           var cal2 = new CalendarTime(document.forms['searchConsultant'].elements['endDate']);
				                 cal2.year_scroll = true;
				                   cal2.time_comp = true;

                                        </script>
                                        
                                        
                                        <s:if test="%{currentAction != searchConsultant}">
                                            <%
                                            
                                            
                                            /* String Variable for storing current position of records in dbgrid*/
                                            strTmp = request.getParameter("txtCurr");
                                            
                                            intCurr = 1;
                                            
                                            if (strTmp != null)
                                                intCurr = Integer.parseInt(strTmp);
                                            
                                            try{
                                                
                                                /* Getting DataSource using Service Locator */
                                                connection=ConnectionProvider.getInstance().getConnection();
                                                
                                                
                                                /* Sql query for retrieving resultset from DataBase */
                                                String  queryString  =null;
                                                
                                                queryString=session.getAttribute(ApplicationConstants.QUERY_STRING).toString();
                                                //out.println(queryString);
                                            %>
                                            <form method="post" id="frmDBGrid" name="frmDBGrid" action=""> 
                                                <div id="consultantList" style="display: block">
                                                    <table align="center" cellpadding="0" cellspacing="0" width="100%">
                                                        <tr>
                                                            <td>
                                                                <!-- DataGrid for list all activities -->
                                                                <grd:dbgrid id="tblRecConsultant" name="tblRecConsultant" width="100" pageSize="12" 
                                                                            currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                            dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                    
                                                                    
                                                                    <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                                   imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"/>
                                                                    
                                                                    <grd:anchorcolumn dataField="Name"  headerText="Name" linkUrl="../consultant/getConsultant.action?empId={Id}" linkText="{Name}" width="15" />
                                                                    <grd:anchorcolumn dataField="Email2"  headerText="Send E-Mail" linkUrl="mailto:{Email2}" linkText="Send Mail"  width="10" />
                                                                    <grd:textcolumn dataField="CellPhoneNo" headerText="Mobile No" width="12" />
                                                                    <grd:anchorcolumn dataField="SkillSet" linkUrl="javascript:getSkills('{Id}')" headerText="Skill Set"
                                                                                   linkText="Click To View"  width="10" />
                                                                    <grd:anchorcolumn dataField="Id" headerText="Resumes" linkText="List" linkUrl="javascript:getConsultantId('{Id}')" HAlign="center" width="6"/>
                                                                    <grd:textcolumn dataField="createdBy" headerText="Created By" width="12" />
                                                                     <grd:textcolumn dataField="LastContactDate" headerText="Last Contact" width="10" dataFormat="MM-dd-yyyy"/> 
                                                                    <grd:textcolumn dataField="ModifiedDate" headerText="Last Modified" width="10" dataFormat="MM-dd-yyyy"/>
                                                                   
                                                                                                         
                                                                </grd:dbgrid>
                                                                
                                                                <!-- these components are DBGrid Specific  -->
                                                                <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                                <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                                <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                                                                <input type="hidden" name="submitFrom" value="dbGrid">
                                                                <s:hidden name="fiName" value="%{fiName}"></s:hidden>
                                                                <s:hidden name="location" value="%{location}"></s:hidden>
                                                                <s:hidden name="skills"  value="%{skills}"></s:hidden>
                                                                <s:hidden name="comments" value="%{comments}"></s:hidden>
                                                                <s:hidden name="email1" value="%{email1}"></s:hidden>
                                                                <s:hidden name="workAuthorization" value="%{workAuthorization}"></s:hidden>
                                                                <s:hidden name="source" value="%{source}"></s:hidden>
                                                                <s:hidden name="assignedTo" value="%{assignedTo}"></s:hidden>
                                                                <s:hidden name="startDate" value="%{startDate}"></s:hidden>
                                                                <s:hidden name="endDate" value="%{endDate}"></s:hidden> 
                                                                
                                                                <!-- <input id="txtDBId" TYPE="hidden" NAME="txtDBId" VALUE="0">
                                                        <input id="txtTRId"TYPE="hidden" NAME="txtTRId" VALUE="1">  -->
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </form>
                                            <%
                                            }catch(Exception ex){
                                                out.println(ex.toString());
                                            }finally{
                                                if(connection!= null){
                                                    connection.close();
                                                    //session.setAttribute(ApplicationConstants.QUERY_STRING,null);
                                                }
                                            }
                                            %>
                                        </s:if>
                                        <%-- </sx:div> --%>
                                    </div>
                                    
                                    <%-- <sx:div id="resumeSearchTab" label="Resume Search" cssStyle="overflow: auto;"> --%>
                                    <div id="resumeSearchTab" class="tabcontent"  >
                                        
                                        <s:form action="searchConsultantResume" name="resumeSearchForm" theme="simple" onsubmit="return resumeSearchSubmit();">
                                            <table border="0" width="100%" cellpadding="1" cellspacing="0">
                                                <tr align="right" >
                                                    <td class="headerText" style="color:red" colspan="6">                                                        
                                                        <% if(request.getAttribute(ApplicationConstants.RESULT_MSG) != null){
                                                out.println(request.getAttribute(ApplicationConstants.RESULT_MSG));
                                                        }%>
                                                        <img alt="Home" 
                                                             src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" 
                                                             width="100%" 
                                                             height="13px" 
                                                             border="0">                                                                                                            
                                                    </td>
                                                </tr>      
                                                
                                                <tr>
                                                    <td class="fieldLabel"> Enter Some Text from Resume :</td>
                                                    <td>
                                                        
                                                        <%--<s:textfield name="resumeText" id="resumeText" cssClass="inputTextBlue" 
                                                                     value='<s:property value="#session.searchResult"/>'/>
                                                                     <s:textfield name="resumeText" id="resumeText" cssClass="inputTextBlue" value="#{searchString}"/>--%>
                                                        <input type="text" name="resumeText" id="resumeText" class="inputTextBlue"
                                                               value="<s:property value="#session.searchString"/>">                                                               
                                                        
                                                    </td> 
                                                    <td colspan="2">
                                                        <s:submit cssClass="buttonBg" value="Search"/>
                                                        <s:submit value="Help"  cssClass="buttonBg" onclick="return helpPopup('ResumeSearchHelp.jsp')" align="left"/>
                                                    </td>
                                                    
                                                </tr>
                                                
                                            </table>
                                            
                                            <br>
                                            <br>
                                            
                                            
                                            <table id="tblResSearch" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='90%' align="center">
                                                <tbody>
                                                    
                                                    <%
                                                    resultCount = 0;
                                                    if(session.getAttribute("searchResult") != null){
                                                        List searchResult = (List)session.getAttribute("searchResult");
                                                        if(null != searchResult) {
                                                            resultCount = searchResult.size();
                                                        }
                                                    }
                                                    %>
                                                    
                                                    <tr>
                                                        <td bgcolor="white" class="fieldLabelLeft">
                                                            Total Resumes : <%=resultCount%>
                                                        </td>
                                                        <td colspan="2" align="right" bgcolor="white">
                                                            <input type="button" name="First" id="First" value="First" class="buttonBg" 
                                                                   onclick="gridNext(this);" align="right">
                                                            <input type="button" name="Previous" id="Previous" value="Previous" class="buttonBg" 
                                                                   onclick="gridNext(this);" align="right">  
                                                            <input type="button" name="Next" id="Next" value="Next" class="buttonBg" 
                                                                   onclick="gridNext(this);" align="right">
                                                            <input type="button" name="Last" id="Last" value="Last" class="buttonBg" 
                                                                   onclick="gridNext(this);" align="right">
                                                        </td>
                                                    </tr>
                                                    
                                                    <tr class="gridHeader">
                                                        <td width="120" height="20px"><B>S.NO</B></td>
                                                        <td width="120" height="40px">Resume Name</td>
                                                        <td width="120" height="30px">Download</td>
                                                    </tr>
                                                    
                                                    <%     
                                                    
                                                    if(request.getAttribute("strStartGrid") != null){
                                                        strStartGrid = request.getAttribute("strStartGrid").toString();
                                                        strIntStartGrid = Integer.parseInt(strStartGrid);
                                                    }
                                                    //else{   strStartGrid = null;  }
                                                    
                                                    if(request.getAttribute("strEndGrid") != null){
                                                        strEndGrid = request.getAttribute("strEndGrid").toString();
                                                        strIntEndGrid = Integer.parseInt(strEndGrid);
                                                    }
                                                    //else{   strEndGrid = null;   }
                                                    %>                                                    
                                                    
                                                    
                                                    <%
                                                    if(session.getAttribute("searchResult") != null){
                                                    %>
                                                    
                                                    <%
                                                    List searchResult = (List)session.getAttribute("searchResult");
                                                    
                                                    
                                                    //resultCount = 0;
                                                    if(null != searchResult){
                                                        resultCount = searchResult.size();
                                                    }
                                                    
                                                    //if(request.getAttribute("strStartGrid") != "0"){
                                                    for(int i = strIntStartGrid,j=0; i < strIntEndGrid; i++,j++){
                                                        ConsultantSearchResultBean resultBean = (ConsultantSearchResultBean)searchResult.get(i);
                                                        // D:\ProjectFiles\resumes\2008\Sep\1\Ashvin_Veligandla.doc -- it looks
                                                        
                                                        String path = resultBean.getFilePath();
                                                        String[] pathArray = path.split("\\\\");
                                                        path="";
                                                        for(int index=0;index<pathArray.length;index++){
                                                            if(index+1 == pathArray.length)
                                                                path=path+pathArray[index];
                                                            else
                                                                path =path+pathArray[index]+"\\"+"\\";
                                                        }
                                                        
                                                        pathName = path.substring(path.lastIndexOf("\\")+1,path.length());
                                                        
                                                        //pathArray[j] = path;
                                                        
                                                        //out.println(pathArray[j]);
                                                    /*
                                                    String[] pathArray = path.split("\\");
                                                    for(int index = 0;index<pathArray.length;index++) {
                                                    path = pathArray[index]+"\\"+"\\";
                                                    out.println(path);
                                                    }*/
                                                        //out.println(path);
                                                    %>
                                                    <TR CLASS="gridRowEven">
                                                        <td class="title"><%=i+1%></td>
                                                        <TD class="title"><%=pathName.substring((pathName.lastIndexOf("/",pathName.length())+1),pathName.length())%></TD>
                                                        <%--
                                                        <td class="title"><A href="<%=path%>" ONCLICK="">Download</A></td>
                                                        <td class="title"><A href='javascript: getResumeDownload("+<%=path%> +");'>Download/View</A></td>
                                                        --%>
                                                        <td class="title"><A href='javascript: getResumeDownload( "<%=path%>");'>Download/View</A></td>
                                                        
                                                    </TR>
                                                    
                                                    <%
                                                    }
                                                    %>
                                                    
                                                    <%-- <%}  %>--%>
                                                    
                                                    <%
                                                    }
                                                    %>
                                                    
                                                    <input type="hidden" name="txtStartGrid" value="<%=strStartGrid%>"/>
                                                    <input type="hidden" name="txtEndGrid" value="<%=strEndGrid%>"/>
                                                    <input type="hidden" name="txtMaxGrid" value="<%=resultCount%>"/>
                                                    
                                                </tbody>
                                            </table>
                                            
                                        </s:form>   
                                        <%--  </sx:div>   --%>
                                    </div>
                                </div>
                                
                                <script type="text/javascript">

                                                    var countries=new ddtabcontent("ConsultantSearchTabs")
                                                    countries.setpersist(true)
                                                    countries.setselectedClassTarget("link") //"link" or "linkparent"
                                                    countries.init()

                                </script>
                                
                                <%-- </sx:tabbedpanel> --%>
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