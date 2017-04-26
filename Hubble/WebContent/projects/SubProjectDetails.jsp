<%--
/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  January 01, 2008, 12:35 PM
 *
 * Author  : Arjun Sanapathi<asanapathi@miraclesoft.com>
 *           Rajanikanth Teppala<rteppala@miraclesoft.com>
 *
 * File    : SubProjectDetails.jsp
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
 --%>

<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%-- <%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<%@ page import="com.freeware.gridtag.*" %> 
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd" %>

<html>
    <head>
        
        <title>Hubble Organization Portal :: SubProject Details</title>
        <%-- <sx:head cache="false"/> --%>
        
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGProjDetails.js"/>"></script>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/projectsValidations/SubProjectAddClientValidation.js"/>"></script>
        <!--<script type="text/JavaScript" src="<s:url value="/includes/javascripts/displayWindow.js"/>"></script>-->
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
    </head>
    
    <body class="bodyGeneral">
        
        <%!
        /* Declarations */
        Connection connection;
        String queryString;
        String currentAccountId;
        String currentProjectId;
        String currentSubProjectId;
        String strTmp;
        String strSortCol;
        String strSortOrd;
        String userRoleName;
        String operationProjectType;
        
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
                            <!--//END DATA COLUMN : Coloumn for LeftMenu-->
                            
                            <!--//START DATA COLUMN : Coloumn for Screen Content-->
                            <td width="850px" class="cellBorder" valign="top">
                                
                                
                                <table cellpadding="0" cellspacing="0" width="100%">
                                    <tr>
                                        <td>
                                            <%
                                            userRoleName = session.getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();                                            
                                            %>                    
                                            
                                            <%
                                            if (("Customer Manager".equalsIgnoreCase(userRoleName)) ){
                                            %>
                                            <span class="fieldLabel">Account Name :</span>&nbsp;
                                            <a href="<s:url action="../crm/accounts/getAccount">
                                                   <s:param name="id" value="%{accountId}"/>
                                               </s:url>" class="navigationText"><s:property value="#request.accountName"/>
                                            </a>&nbsp;&nbsp;
                                            <span class="fieldLabel">Project Name :</span>&nbsp;
                                            <a href="<s:url action="getProject">
                                                   <s:param name="id" value="%{id}"/>
                                               </s:url>" class="navigationText"><s:property value="#request.projectName"/>
                                            </a>
                                            <%  }else  {%>
                                            <span class="fieldLabel">Account Name :</span>&nbsp;
                                            <a href="<s:url action="../crm/accounts/getAccount">
                                                   <s:param name="id" value="%{accountId}"/>
                                               </s:url>" class="navigationText"><s:property value="#request.accountName"/>
                                            </a>&nbsp;&nbsp;
                                            <span class="fieldLabel">Project Name :</span>&nbsp;
                                            <a href="<s:url action="getProject">
                                                   <s:param name="id" value="%{id}"/>
                                               </s:url>" class="navigationText"><s:property value="#request.projectName"/>
                                            </a>
                                            <%}%>
                                            
                                        </td>
                                    </tr>
                                    
                                    <tr>
                                        <td valign="top" style="padding-left:10px;padding-top:5px;">
         
                                <ul id="subProjectPannel" class="shadetabs" >
                                    <li ><a href="#" class="selected" rel="subProjectTab">SubProject Details</a></li>                                    
                                </ul>
                                
                                            <div  style="border:1px solid gray; width:840px; overflow:auto; margin-bottom: 1em;">
                                                <!--//START TABBED PANNEL : -->
                                                <%-- <sx:tabbedpanel id="subProjectPannel" cssStyle="width: 840px;padding-left:10px;padding-top:5px;" doLayout="false"> --%>
                                                
                                                <div id="subProjectTab" class="tabcontent">
                                                    <!--//START TAB : -->
                                                    <%-- <sx:div id="subProjectTab" label="SubProject Details"> --%>
                                                    <s:form name="frmSubProjectAdd" action="editSubProject" theme="simple">
                                                        
                                                        <table cellpadding="1" cellspacing="1" border="0" width="100%">
                                                            
                                                            <tr align="right">
                                                                <td class="headerText" colspan="6">
                                                                    <% if(request.getAttribute(ApplicationConstants.RESULT_MSG) != null){
                                                                    out.println(request.getAttribute(ApplicationConstants.RESULT_MSG));
                                                                    }%>
                                                                    
                                                                    <s:submit cssClass="buttonBg" value="Save"/>
                                                                    <s:hidden name="accountId" id="accountId" value="%{accountId}"/>
                                                                    <s:hidden name="subProjectId" id="subProjectId" value="%{currentSubProject.subProjectId}"/>
                                                                    <s:hidden name="projectId" id="projectId" value="%{currentSubProject.projectId}"/>                                                                    
                                                                </td>
                                                            </tr>      
                                                            
                                                            
                                                            <tr>
                                                                <td class="fieldLabel">SubProject  Name :</td>                                                    
                                                                <td><s:textfield name="subPrjName" cssClass="inputTextBlueLarge" value="%{currentSubProject.subPrjName}" /></td> 
                                                                <%--
                                                                <td colspan="3"><s:textfield name="subPrjName" cssClass="inputTextBlueAddress1" value="%{currentSubProject.subPrjName}" onchange="subPrjNameValidate(document.frmSubProjectAdd.subPrjName.value);" /></td> 
                                                                
                                                                <td class="fieldLabel">Project Type :</td>
                                                                <td>
                                                                    <s:select list="prjectTypeList" name="projectType" cssClass="inputSelect" value="%{currentSubProject.projectType}" headerKey="" headerValue="--Please Select--" onkeypress="return handleEnter(this,event);" onchange="getWindow(document.frmSubProjectAdd.projectType.value);"/>
                                                                </td>
                                                                --%>
                                                                
                                                                <td class="fieldLabel">Current State :</td>                                                    
                                                                <td><s:select  list="projectStatusTypeList" name="currentState" cssClass="inputSelect" value="%{currentSubProject.currentState}" headerKey="" headerValue="--Please Select--" onkeypress="return handleEnter(this,event);"/></td>
                                                                
                                                            </tr>
                                                            
                                                            <tr>
                                                                <td class="fieldLabel" align="right">Start Date :</td>                                                    
                                                                <td align="left"><s:textfield name="startDate" cssClass="inputTextBlueSmall" value="%{currentSubProject.startDate}" onkeypress="return handleEnter(this,event);"/><a href="javascript:cal1.popup();">
                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                </td>
                                                                <td class="fieldLabel">End Date :</td>                                                    
                                                                <td><s:textfield name="endDate" cssClass="inputTextBlueSmall" value="%{currentSubProject.endDate}" onkeypress="return handleEnter(this,event);"/><a href="javascript:cal2.popup();">
                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                </td>
                                                            </tr>
                                                            
                                                            <tr>                 
                                                                <td class="fieldLabel">Team Lead :</td>                                                    
                                                                <td><s:select  list="teamLeadMap" name="teamLeadUID" cssClass="inputSelectLarge" headerKey="" headerValue="--Please Select--" value="%{currentSubProject.teamLeadUID}" onkeypress="return handleEnter(this,event);"/></td>
                                                                <td class="fieldLabel">Team Size :</td>
                                                                <td>
                                                                    <s:textfield name="totalResources" cssClass="inputTextBlue" value="%{currentSubProject.totalResources}" onchange="totalResourcesValidate(document.frmSubProjectAdd.totalResources.value);" onblur="return validatenumber(this)"/>
                                                                </td>
                                                            </tr>
                                                            
                                                            <tr>
                                                                <td class="fieldLabel">Descrption :</td>
                                                                <td colspan="3" ><s:textarea cssClass="inputTextarea" rows="2" cols="83" value="%{currentSubProject.description}" name="description" onchange="descriptionValidate(document.frmSubProjectAdd.description.value);" /></td>                                                    
                                                            </tr>
                                                        </table>
                                                        <script type="text/JavaScript">
                                                           var cal1 = new CalendarTime(document.forms['frmSubProjectAdd'].elements['startDate']);
                                                           cal1.year_scroll = true;
                                                           cal1.time_comp = false;
                                                           
                                                           var cal2 = new CalendarTime(document.forms['frmSubProjectAdd'].elements['endDate']);
                                                           cal2.year_scroll = true;
                                                           cal2.time_comp = false;
                                                        </script>
                                                    </s:form>
                                                    <%-- </sx:div> --%>
                                                    <!--//END TAB : -->
                                                </div>
                                                
                                                <%-- </sx:tabbedpanel> --%>
                                                <!--//END TABBED PANNEL : -->
                                            </div>
                                            
                                            <script type="text/javascript">

var countries=new ddtabcontent("subProjectPannel")
countries.setpersist(false)
countries.setselectedClassTarget("link") //"link" or "linkparent"
countries.init()

                                </script>
                                            
                                            
                                <ul id="SubProjectDetailsPannel" class="shadetabs">
                                    
                                   <%-- 
                                   <s:if test="#session.roleName == 'Admin'">
                                    <li ><a href="#" class="selected" rel="mapListTab">Admin Map List</a></li>    
                                    </s:if>
                                    
                                    <s:if test="#session.roleName == 'Customer Manager' && #session.projType=='B2B' ">
                                    <li ><a href="#" rel="mapListTab2">Map List</a></li>
                                    </s:if>
                                    
                                    <s:elseif test="#session.roleName == 'Customer Manager' && #session.projType!='B2B'">  
                                    <li ><a href="#" rel="accountsListTab">Issues List</a></li>
                                    </s:elseif>
                                    --%>
                                    
                                    <s:if test="#session.projType=='B2B'">                                    
                                    <li ><a href="#" class="selected" rel="mapListTab">Admin Map List</a></li>    
                                    </s:if>
                                    
                                    <s:elseif test="#session.projType!='B2B'">  
                                    <li ><a href="#" rel="accountsListTab">Issues List</a></li>
                                    </s:elseif>
                                    
                                    <li ><a href="#" rel="attachmentsList">SubProject Attachments</a></li>
                                </ul>       
                                
                                            
                                            <div  style="border:1px solid gray; width:840px;height: 300px; overflow:auto; margin-bottom: 1em;">
                                                <!--//START TABBED PANNEL : --> 
                                                <%--<sx:tabbedpanel id="SubProjectDetailsPannel" cssStyle="width: 840px; height: 300px;padding-left:10px;padding-top:5px;" doLayout="true"> --%>
                                                
                                                <% 
                                                if(request.getAttribute("currentAccountId") != null){
                                                currentAccountId = (String)request.getAttribute("currentAccountId");
                                                }
                                                
                                                if(request.getAttribute("currentProjectId") != null){
                                                currentProjectId = (String)request.getAttribute("currentProjectId");
                                                }
                                                
                                                if(request.getAttribute("currentSubProjectId") != null){
                                                currentSubProjectId = (String)request.getAttribute("currentSubProjectId");
                                                }
                                                
                                                
                                                operationProjectType = session.getAttribute(ApplicationConstants.OP_PROJECT_TYPE).toString();
                                                //out.println(operationProjectType);                                                
                                                
                                                try{%>
                                                
                                                
                                                    <div id="mapListTab" class="tabcontent">
                                                        <%-- <sx:div id="mapListTab" label="Admin Map List"  name="mapListTab"> --%>
                                                        
                                                        <% 
                                                        /* Getting DataSource using Service Locator */
                                                        
                                                        connection = ConnectionProvider.getInstance().getConnection();
                                                        
                                                        intCurr = 1;
                                                        /* String Variable for storing current position of records in dbgrid*/
                                                        strTmp = request.getParameter("txtMapCurr");
                                                        
                                                        if (strTmp != null)
                                                        intCurr = Integer.parseInt(strTmp);
                                                        
                                                        /* Specifing Shorting Column */
                                                        strSortCol = request.getParameter("txtMapSortCol");
                                                        
                                                        if (strSortCol == null) strSortCol = "MapName";
                                                        
                                                        strSortOrd = request.getParameter("txtMapSortAsc");
                                                        if (strSortOrd == null) strSortOrd = "DESC";
                                                        
                                                        /* Sql query for retrieving resultset from DataBase */
                                                        queryString  =null;
                                                        queryString = "Select Id,MapperUID,MapName,ProjectId,SubProjectId,MapStartDate,CustomerId from tblMaps";
                                                        queryString = queryString + " where ProjectId="+currentProjectId+" AND SubProjectId="+currentSubProjectId+" ORDER BY MapStartDate DESC";
                                                        
                                                        String addMapAction = "editMap.action?accountId="+currentAccountId+"&id="+currentProjectId+"&subProjectId="+currentSubProjectId;
                                                        
                                                        %>
                                                        
                                                        <s:form action="" theme="simple" name="frmDBMapGrid">   
                                                            
                                                            <table cellpadding="0" cellspacing="0" width="100%">
                                                                <tr align="right">
                                                                    <td class="headerText">
                                                                        <img alt="Home" 
                                                                             src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" 
                                                                             width="100%" 
                                                                             height="13px" 
                                                                             border="0">
                                                                    </td>
                                                                </tr>    
                                                                <!---BEGIN:: DBGrid Specific ---->  
                                                                <tr>
                                                                    <td width="100%">
                                                                        <grd:dbgrid id="tblMapList" name="tblMapList" width="100" pageSize="8" 
                                                                                    currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                                    dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                            
                                                                            <grd:gridpager imgFirst="../includes/images/DBGrid/First.gif" imgPrevious="../includes/images/DBGrid/Previous.gif" 
                                                                                           imgNext="../includes/images/DBGrid/Next.gif" imgLast="../includes/images/DBGrid/Last.gif" scriptFunction="getNextMapProjects"
                                                                                           addImage="../includes/images/DBGrid/Add.png" addAction="<%=addMapAction%>" scriptFunction="getNextMapProjects"/>
                                                                            <%--addImage="../includes/images/DBGrid/Add.png"   
                                                                                           addAction="<%=addMapAction%>"--%>
                                                                                           
                                                                            
                                                                            <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" 
                                                                                            imageAscending="../includes/images/DBGrid/ImgAsc.gif" 
                                                                                            imageDescending="../includes/images/DBGrid/ImgDesc.gif"/>    
                                                                            
                                                                            <grd:rownumcolumn headerText="SNo" width="4" HAlign="right"/>
                                                                            
                                                                            <grd:imagecolumn  headerText="Edit"  width="5"  HAlign="center"  
                                                                                              imageSrc="../includes/images/DBGrid/Edit.gif"  
                                                                                              linkUrl="getMap.action?mapId={Id}&accountId={CustomerId}&subProjectId={SubProjectId}&projectId={ProjectId}" 
                                                                                              imageBorder="0" imageWidth="16" imageHeight="16" alterText="Click to edit"/>
                                                                            
                                                                            <grd:textcolumn dataField="MapperUID"  headerText="MapperUID" width="8"/>
                                                                            <grd:textcolumn dataField="MapName"  	headerText="MapName" width="8"/>
                                                                            <grd:datecolumn dataField="MapStartDate" headerText="Map Start Date" width="8" dataFormat="MM-dd-yyyy" />
                                                                            
                                                                        </grd:dbgrid>
                                                                        
                                                                        <input TYPE="hidden" NAME="txtMapCurr" VALUE="<%=intCurr%>">
                                                                        <input TYPE="hidden" NAME="txtMapSortCol" VALUE="<%=strSortCol%>">
                                                                        <input TYPE="hidden" NAME="txtMapSortAsc" VALUE="<%=strSortOrd%>">
                                                                        <input type="hidden" id="accountId" name="accountId" value="<%=currentAccountId%>"/>
                                                                        <input type="hidden" id="subProjectId" name="subProjectId" value="<%=currentProjectId%>"/>
                                                                        <input type="hidden" id="projectId" name="projectId" value="<%=currentSubProjectId%>"/> 
                                                                        
                                                                        <input TYPE="hidden" NAME="txtAttachCurr" VALUE="">
                                                                        
                                                                    </td>
                                                                </tr>
                                                            </table>                                
                                                        </s:form>
                                                        <%-- </sx:div> --%>
                                                    </div>
                                                
                                                
                                                
                                                    <%-- <sx:div id="mapListTab" label="Map List"  name="mapListTab"> --%>                                                        
                                                    <%--<div id="mapListTab2" class="tabcontent">
                                                        
                                                        <% 
                                                        /* Getting DataSource using Service Locator */
                                                        
                                                        connection = ConnectionProvider.getInstance().getConnection();
                                                        
                                                        intCurr = 1;
                                                        /* String Variable for storing current position of records in dbgrid*/
                                                        strTmp = request.getParameter("txtMapCurr");
                                                        
                                                        if (strTmp != null)
                                                        intCurr = Integer.parseInt(strTmp);
                                                        
                                                        /* Specifing Shorting Column */
                                                        strSortCol = request.getParameter("txtMapSortCol");
                                                        
                                                        if (strSortCol == null) strSortCol = "MapName";
                                                        
                                                        strSortOrd = request.getParameter("txtMapSortAsc");
                                                        if (strSortOrd == null) strSortOrd = "DESC";
                                                        
                                                        /* Sql query for retrieving resultset from DataBase */
                                                        queryString  =null;
                                                        queryString = "Select Id,MapperUID,MapName,ProjectId,SubProjectId,MapStartDate,CustomerId from tblMaps";
                                                        queryString = queryString + " where ProjectId="+currentProjectId+" AND SubProjectId="+currentSubProjectId+" ORDER BY MapStartDate DESC";
                                                        
                                                        String addMapAction2 = "editMap.action?accountId="+currentAccountId+"&id="+currentProjectId+"&subProjectId="+currentSubProjectId;
                                                        
                                                        %>
                                                        
                                                        <s:form action="" theme="simple" name="frmDBMapGrid">   
                                                            
                                                            <table cellpadding="0" cellspacing="0" width="100%">
                                                                <tr align="right">
                                                                    <td class="headerText">
                                                                        <img alt="Home" 
                                                                             src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" 
                                                                             width="100%" 
                                                                             height="13px" 
                                                                             border="0">
                                                                    </td>
                                                                </tr>    
                                                                <!---BEGIN:: DBGrid Specific ---->  
                                                                <tr>
                                                                    <td width="100%">
                                                                        <grd:dbgrid id="tblMapList" name="tblMapList" width="100" pageSize="8" 
                                                                                    currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                                    dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                            
                                                                            <grd:gridpager imgFirst="../includes/images/DBGrid/First.gif" imgPrevious="../includes/images/DBGrid/Previous.gif" 
                                                                                           imgNext="../includes/images/DBGrid/Next.gif" imgLast="../includes/images/DBGrid/Last.gif"
                                                                                           addImage="../includes/images/DBGrid/Add.png"   
                                                                                           addAction="<%=addMapAction2%>"
                                                                                           scriptFunction="getNextMapProjects"/>
                                                                            
                                                                            <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" 
                                                                                            imageAscending="../includes/images/DBGrid/ImgAsc.gif" 
                                                                                            imageDescending="../includes/images/DBGrid/ImgDesc.gif"/>    
                                                                            
                                                                            <grd:rownumcolumn headerText="SNo" width="4" HAlign="right"/>
                                                                            
                                                                            <grd:imagecolumn  headerText="Edit"  width="5"  HAlign="center"  
                                                                                              imageSrc="../includes/images/DBGrid/Edit.gif"  
                                                                                              linkUrl="getMap.action?mapId={Id}&accountId={CustomerId}&subProjectId={SubProjectId}&projectId={ProjectId}" 
                                                                                              imageBorder="0" imageWidth="16" imageHeight="16" alterText="Click to edit"/>
                                                                            
                                                                            <grd:textcolumn dataField="MapperUID"  headerText="MapperUID" width="8"/>
                                                                            <grd:textcolumn dataField="MapName"  	headerText="MapName" width="8"/>
                                                                            <grd:datecolumn dataField="MapStartDate" headerText="Map Start Date" width="8" dataFormat="MM-dd-yyyy" />
                                                                            
                                                                        </grd:dbgrid>
                                                                        
                                                                        <input TYPE="hidden" NAME="txtMapCurr" VALUE="<%=intCurr%>">
                                                                        <input TYPE="hidden" NAME="txtMapSortCol" VALUE="<%=strSortCol%>">
                                                                        <input TYPE="hidden" NAME="txtMapSortAsc" VALUE="<%=strSortOrd%>">
                                                                        
                                                                        
                                                                        <input TYPE="hidden" NAME="txtAttachCurr" VALUE="">
                                                                        
                                                                    </td>
                                                                </tr>
                                                            </table>                                
                                                        </s:form>                                                        
                                                    </div> --%>
                                                <%-- </sx:div> --%>
                                                
                                                
                                                                                                  
                                                    <!--//START TAB : -->
                                                    <div id="accountsListTab" class="tabcontent">
                                                        <%-- <sx:div id="accountsListTab" label="Issues List"> --%>
                                                        
                                                        <%
                                                        /* String Variable for storing current position of records in dbgrid*/
                                                        
                                                        // String addAction = "getIssues.action";
                                                        
                                                        strTmp = request.getParameter("txtIssueCurr");
                                                        
                                                        intCurr = 1;
                                                        
                                                        if (strTmp != null)
                                                        intCurr = Integer.parseInt(strTmp);
                                                        
                                                        /* Specifing Shorting Column */
                                                        strSortCol = request.getParameter("txtIssueSortCol");
                                                        
                                                        if (strSortCol == null) strSortCol = "IssueName";
                                                        
                                                        strSortOrd = request.getParameter("txtIssueSortAsc");
                                                        if (strSortOrd == null) strSortOrd = "ASC";
                                                        
                                                        /* Getting DataSource using Service Locator */
                                                        
                                                        connection = ConnectionProvider.getInstance().getConnection();
                                                        
                                                        /* Sql query for retrieving resultset from DataBase */
                                                        queryString  =null;
                                                        
                                                        queryString = "Select Id,MapId,ProjectId,SubProjectId,CustomerId,IssueType,IssueName,DateCreated,DateClosed,Resolution,IssueState,Description from tblMapIssues";
                                                        queryString = queryString + " where ProjectId="+currentProjectId+" AND SubProjectId="+currentSubProjectId+" ORDER BY DateCreated DESC";
                                                        
                                                        //String addIssueAction = "mapIssue.action?accountId="+currentAccountId+"&id="+currentProjectId+"&subProjectId="+currentSubProjectId+"&mapId="+currentMapId;
                                                        
                                                        //queryString = "Select Id,MapId,ProjectId,SubProjectId,CustomerId,ProjectName,SubProjectName,MapName,IssueType,DateCreated,DateClosed,Resolution,Status,Description from tblPrjIssues";
                                                        //queryString = queryString + " where ProjectId="+currentProjectId+" AND SubProjectId="+currentSubProjectId+" ORDER BY DateCreated DESC";
                                                        
                                                        String addIssueAction = "mapIssue.action?accountId="+currentAccountId+"&id="+currentProjectId+"&subProjectId="+currentSubProjectId+"&mapId=0";
                                                        //String addIssueAction = "../projects/issues/issue1.action?accessType=Issue&navigate=true&accountId="+currentAccountId+"&projectId="+currentProjectId+"&subProjectId="+currentSubProjectId+"&mapId=0";
                                                        
                                                        //String addIssueAction = "../projects/issues/issue.action?accessType=Issue&navigate=true&accountId="+currentAccountId+"&projectId="+currentProjectId+"&subProjectId="+currentSubProjectId+"&mapId="+currentMapId;
                                                        //out.println(queryString);
                                                        //issue.action?accessType=Issue
                                                        %>
                                                        
                                                        
                                                        
                                                        <s:form action="" theme="simple" name="frmDBIssueGrid">   
                                                            
                                                            <table cellpadding="0" cellspacing="0" width="100%">
                                                                <tr align="right">
                                                                    <td class="headerText">
                                                                        <img alt="Home" 
                                                                             src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" 
                                                                             width="100%" 
                                                                             height="13px" 
                                                                             border="0">
                                                                    </td>
                                                                </tr>    
                                                                <!---BEGIN:: DBGrid Specific 
                                                                http://localhost:8090/<%=ApplicationConstants.CONTEXT_PATH%>/projects/issues/getProjIssueList.action?issueType=All
                                                                ---->  
                                                                <tr>
                                                                    <td class="subHeader" width="700"></td>
                                                                </tr>
                                                                <tr>
                                                                    <td width="100%">
                                                                        
                                                                        <grd:dbgrid id="tblAccountList" name="tblAccountList" width="100" pageSize="8" 
                                                                                    currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                                    dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable"
                                                                                    trAttributes="onClick=SelectCurrentRow('{Id}',2) ">
                                                                            
                                                                            <grd:gridpager imgFirst="../includes/images/DBGrid/First.gif" imgPrevious="../includes/images/DBGrid/Previous.gif" 
                                                                                           imgNext="../includes/images/DBGrid/Next.gif" imgLast="../includes/images/DBGrid/Last.gif"
                                                                                           addImage="../includes/images/DBGrid/Add.png" addAction="<%=addIssueAction%>" />
                                                                            
                                                                            <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" 
                                                                                            imageAscending="../includes/images/DBGrid/ImgAsc.gif" 
                                                                                            imageDescending="../includes/images/DBGrid/ImgDesc.gif"/>     
                                                                            
                                                                            <grd:rownumcolumn headerText="SNo" width="4" HAlign="right"/>
                                                                            <grd:imagecolumn  headerText="Edit"  width="5"  HAlign="center"  
                                                                                              imageSrc="../includes/images/DBGrid/Edit.gif"                                                                                                                                                                                            
                                                                                              linkUrl="../projects/issues/getIssue.action?issueId={Id}&projectId={ProjectId}&subProjectId={SubProjectId}&accountId={CustomerId}&mapId={MapId}&accessType=Issue&navigate=true" 
                                                                                              imageBorder="0" imageWidth="16" imageHeight="16" alterText="Click to edit"/>
                                                                            
                                                                            <grd:textcolumn dataField="ISSUETYPE" headerText="ISSUE TYPE" width="20"/>                                                                        
                                                                            <grd:textcolumn dataField="Description" headerText="Description" width="20"/>
                                                                            
                                                                            <grd:datecolumn dataField="DateCreated" headerText="Start Date" width="20" dataFormat="MM-dd-yyyy"/>
                                                                            <grd:datecolumn dataField="DateClosed" headerText="End Date" width="20" dataFormat="MM-dd-yyyy"/>
                                                                            
                                                                        </grd:dbgrid>
                                                                        
                                                                        <input TYPE="hidden" NAME="txtIssueCurr" VALUE="<%=intCurr%>">
                                                                        <input TYPE="hidden" NAME="txtIssueSortCol" VALUE="<%=strSortCol%>">
                                                                        <input TYPE="hidden" NAME="txtIssueSortAsc" VALUE="<%=strSortOrd%>">
                                                                        
                                                                        <input TYPE="hidden" NAME="submitFrom" VALUE="dbGrid">
                                                                        <input type="hidden" name="id" value="<%=currentAccountId%>">
                                                                        <input TYPE="hidden" NAME="txtAttachCurr" VALUE="">
                                                                        
                                                                    </td>
                                                                </tr>
                                                            </table>                                
                                                            
                                                        </s:form>
                                                        
                                                        <%-- </sx:div> --%>                                                    
                                                        <!--//END TAB : -->
                                                    </div>
                                                
                                                
                                                <div id="attachmentsList" class="tabcontent">
                                                    <%-- <sx:div id="attachmentsList" label="SubProject Attachments"> --%>
                                                    
                                                    <%
                                                    
                                                    /* String Variable for storing current position of records in dbgrid*/
                                                    strTmp = request.getParameter("txtAttachCurr");
                                                    
                                                    if (strTmp != null)
                                                    intCurr = Integer.parseInt(strTmp);
                                                    
                                                    /* Specifing Shorting Column */
                                                    strSortCol = request.getParameter("txtAttachSortCol");
                                                    
                                                    if (strSortCol == null) strSortCol = "AttachmentType";
                                                    
                                                    strSortOrd = request.getParameter("txtAttachSortAsc");
                                                    if (strSortOrd == null) strSortOrd = "ASC";
                                                    
                                                    /* Sql query for retrieving resultset from DataBase */
                                                    //queryString ="Select Id,AttachmentType,DateOfUpload,UploadedBy,Description,ObjectType from tblPrjAttachments WHERE ObjectType ='SubProject' ORDER BY DateOfUpload";
                                                    //queryString = queryString + "WHERE ObjectType ='SubProject' AND ObjectId="+currentAccountId+" ORDER BY DateOfUpload";
                                                    
                                                    queryString ="Select Id,AttachmentType,DateOfUpload,UploadedBy,Description,ObjectId,ObjectType from tblPrjAttachments WHERE ObjectType ='SubProject'";
                                                    queryString = queryString + "AND ObjectId ="+currentAccountId+" ORDER BY DateOfUpload DESC";
                                                    
                                                    String attachmentAction = "getAttachment.action";
                                                    
                                                    if(request.getAttribute("currentAccountId") != null){
                                                    attachmentAction = attachmentAction+"?objectId="+request.getAttribute("currentAccountId")+"&objectType=SubProject"+"&projectId="+currentProjectId+"&subProjectId="+currentSubProjectId;
                                                    }
                                                    %>
                                                    
                                                    <form action=""  method="post" name="frmDBAttachGrid"> 
                                                        <table cellpadding="0" cellspacing="0" width="100%">
                                                            <tr>
                                                                <td class="headerText">
                                                                    <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td>
                                                                    <!-- DataGrid for list all Attachments -->
                                                                    <grd:dbgrid id="tblCrmAttachments" name="tblCrmAttachments" width="100" pageSize="8" 
                                                                                currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                                dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                        
                                                                        <grd:gridpager imgFirst="../includes/images/DBGrid/First.gif" imgPrevious="../includes/images/DBGrid/Previous.gif" 
                                                                                       imgNext="../includes/images/DBGrid/Next.gif" imgLast="../includes/images/DBGrid/Last.gif"
                                                                                       addImage="../includes/images/DBGrid/Add.png" 
                                                                                       addAction="<%=attachmentAction%>"
                                                                                       scriptFunction="getSubProjAttachments"/>
                                                                        
                                                                        <%-- <grd:imagecolumn headerText="Delete" width="4" HAlign="center" 
                                                                             imageSrc="../includes/images/DBGrid/Delete.png"
                                                                             linkUrl="deleteAttachment.action?Id={Id}" imageBorder="0"
                                                                             imageWidth="51" imageHeight="20" alterText="Delete"></grd:imagecolumn>--%>
                                                            
                                                                        <grd:textcolumn dataField="AttachmentType"  headerText="Attachment Type"   width="30" sortable="true"/> 
                                                                        <grd:textcolumn dataField="ObjectType"  headerText="Object Type" width="30"/>  
                                                                        <grd:datecolumn dataField="DateOfUpload"  headerText="Date Uploaded" dataFormat="MM-dd-yyyy HH:mm:SS" width="20"/>
                                                                        <grd:imagecolumn headerText="Download" width="4" HAlign="center" 
                                                                                         imageSrc="../includes/images/download_11x10.jpg"
                                                                                         linkUrl="getDownload.action?Id={Id}" imageBorder="0"
                                                                                         imageWidth="11" imageHeight="10" alterText="Download"></grd:imagecolumn>
                                                                    </grd:dbgrid>
                                                                    <!-- these components are DBGrid Specific  -->
                                                                     
                                                                    <input TYPE="hidden" NAME="txtAttachCurr" VALUE="<%=intCurr%>">
                                                                    <input TYPE="hidden" NAME="txtAttachSortCol" VALUE="<%=strSortCol%>">
                                                                    <input TYPE="hidden" NAME="txtAttachSortAsc" VALUE="<%=strSortOrd%>">
                                                                    
                                                                    
                                                                    <input TYPE="hidden" NAME="txtMapCurr" VALUE="">
                                                                </td>
                                                            </tr>
                                                        </table>    
                                                    </form>  
                                                    
                                                    <%-- </sx:div> --%>
                                                </div>
                                                
                                                <%
                                                }catch(Exception ex){
                                                out.println(ex.toString());
                                                }finally{
                                                if(connection!= null){
                                                connection.close();
                                                }
                                                }
                                                %>
                                                
                                                <%--</sx:tabbedpanel>  --%>                                              
                                                <!--//END TABBED PANNEL : -->
                                            </div>
                                            
                                            
                                            <script type="text/javascript">

var countries=new ddtabcontent("SubProjectDetailsPannel")
countries.setpersist(false)
countries.setselectedClassTarget("link") //"link" or "linkparent"
countries.init()

                                </script>
                                            
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
                <td align="center"><s:include value="/includes/template/Footer.jsp"/></td>
            </tr>
            <!--//END FOOTER : Record for Footer Background and Content-->
            
        </table>
        <!--//END MAIN TABLE : Table for template Structure-->
     
        
        
    </body>
</html>
