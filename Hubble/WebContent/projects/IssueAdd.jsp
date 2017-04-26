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
 * File    : IssueAdd.jsp
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
--%>

<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%-- <%@ taglib prefix="sx" uri="/struts-dojo-tags"%> --%>
<%@ page import="com.freeware.gridtag.*" %> 
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd" %>

<html>
    <head>
        
        <title>Hubble Organization Portal ::  Issue Adding Details</title>
         <%-- <sx:head cache="false"/> --%>
        
        <%--This link for ToolTip css --%>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tooltip.css"/>">
        <%--This is End for ToolTip css --%>
         
        <%--This link for ToolTip js --%>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tooltip.js"/>"></script>
        <%--This End for ToolTip js --%>
        
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CrmAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CommonAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/projectsValidations/IssueAddClientValidation.js"/>"></script> 
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        
    </head>
    
    <body>
        
        <%!
        /* Declarations */
        Connection connection;
        String queryString;
        String currentAccountId;
        String currentProjectId;
        String currentSubProjectId;
        String currentMapId;
        String strTmp;
        String strSortCol;
        String strSortOrd = "ASC";
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
                                            
                                            operationProjectType = session.getAttribute(ApplicationConstants.OP_PROJECT_TYPE).toString();
                                            //out.println(operationProjectType);
                                            %>                    
                                            
                                            <%
                                            if (("Customer Manager".equalsIgnoreCase(userRoleName)) ){
                                            %>
                                            <span class="fieldLabel">Account Name :</span>&nbsp;
                                            <label class="navigationText"><s:property value="#request.accountName"/>
                                            </label>&nbsp;&nbsp;
                                            <span class="fieldLabel">Project Name :</span>&nbsp;
                                            <a href="<s:url action="getProject">
                                                   <s:param name="id" value="%{id}"/>
                                               </s:url>" class="navigationText"><s:property value="#request.projectName"/>
                                            </a>
                                            <span class="fieldLabel">SubProject Name :</span>&nbsp;
                                            <a href="<s:url action="getSubProject">
                                                   <s:param name="subProjectId" value="%{subProjectId}"/>
                                                   <s:param name="projectId" value="%{id}"/>
                                               </s:url>" class="navigationText"><s:property value="#request.subProjectName"/>
                                            </a>          
                                            
                                            <s:if test="mapId != 0">
                                                <span class="fieldLabel">Map Name :</span>&nbsp;
                                                <a href="<s:url action="getMap">
                                                       <s:param name="mapId" value="%{mapId}"/>
                                                       <s:param name="accountId" value="%{accountId}"/>
                                                       <s:param name="projectId" value="%{id}"/>
                                                       <s:param name="subProjectId" value="%{subProjectId}"/>
                                                   </s:url>" class="navigationText"><s:property value="#request.mapName"/>
                                                </a>
                                            </s:if>
                                            
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
                                            <span class="fieldLabel">SubProject Name :</span>&nbsp;
                                            <a href="<s:url action="getSubProject">
                                                   <s:param name="subProjectId" value="%{subProjectId}"/>
                                                   <s:param name="projectId" value="%{id}"/>
                                               </s:url>" class="navigationText"><s:property value="#request.subProjectName"/>
                                            </a>      
                                            
                                            <s:if test="mapId != 0">
                                                <span class="fieldLabel">Map Name :</span>&nbsp;
                                                <a href="<s:url action="getMap">
                                                       <s:param name="mapId" value="%{mapId}"/>
                                                       <s:param name="accountId" value="%{accountId}"/>
                                                       <s:param name="projectId" value="%{id}"/>
                                                       <s:param name="subProjectId" value="%{subProjectId}"/>
                                                   </s:url>" class="navigationText"><s:property value="#request.mapName"/>
                                                </a>
                                            </s:if>
                                            <%}%>
                                            
                                        </td>
                                    </tr>
                                    <tr>
                                        <td valign="top" style="padding-left:10px;padding-top:5px;">
                                            
                                            <ul id="issueAddPannel" class="shadetabs">
                                            <li ><a href="#" class="selected" rel="issueAddTab">Issue Add</a></li>     
                                            </ul>                                            
                                            
                                            <div  style="border:1px solid gray; width:840px; height: 250px; overflow:auto; margin-bottom: 1em;">
                                                <!--//START TABBED PANNEL : -->
                                                <%-- <sx:tabbedpanel id="issueAddPannel" cssStyle="width: 840px; height: 250px;padding-left:10px;padding-top:5px;" doLayout="true"> --%>
                                                
                                                <!--//START TAB : -->
                                                <%-- <sx:div id="issueAddTab" label="Issue Add"> --%>
                                                <div id="issueAddTab" class="tabcontent">
                                                    
                                                    <s:form name="frmAccountAdd" action="addIssue" theme="simple">
                                                        
                                                        <table cellpadding="1" cellspacing="1" border="0" width="100%">
                                                            
                                                            <tr align="right">
                                                                <td class="headerText" colspan="6">
                                                                    <% if(request.getAttribute(ApplicationConstants.RESULT_MSG) != null){
                                                                    out.println(request.getAttribute(ApplicationConstants.RESULT_MSG));
                                                                    }%>                                                    
                                                                    <s:submit cssClass="buttonBg" value="Add"/>
                                                                    <s:reset cssClass="buttonBg" value="Reset"/>
                                                                    <s:hidden name="accountId" value="%{accountId}"/>
                                                                    <s:hidden name="projectId" value="%{id}"/>
                                                                    <s:hidden name="subProjectId" value="%{subProjectId}"/>
                                                                    <s:hidden name="mapId" value="%{mapId}"/>
                                                                </td>
                                                            </tr>      
                                                            
                                                            <tr>  
                                                                <td class="fieldLabel">Project :</td>                                                    
                                                                <td><s:textfield name="projectName" value="%{projectName}" cssClass="inputTextBlue"/></td>                                                     
                                                                <td class="fieldLabel">SubProject :</td>                                                    
                                                                <td><s:textfield name="subProjectName" value="%{subProjectName}" cssClass="inputTextBlue"/></td>
                                                            </tr>
                                                            
                                                            <tr>
                                                                <td class="fieldLabel">Issue Title :</td>                                                    
                                                                <td><s:textfield name="issueNames" cssClass="inputTextBlue"  onchange="issueNamesValidate(document.frmAccountAdd.issueNames.value);" /></td>
                                                                <td class="fieldLabel">Status :</td>                                                    
                                                                <td><s:textfield name="issueStates" cssClass="inputTextBlue" onchange="issueStatesValidate(document.frmAccountAdd.issueStates.value);" /></td>
                                                            </tr>
                                                            
                                                            <tr>
                                                                <td class="fieldLabel">Issue Type :</td>                                                    
                                                                <td> <s:select list="issueTypeMaps" name="issueTypes" cssClass="inputTextBlue"/></td>
                                                                <td class="fieldLabel" align="left">Map:</td>
                                                                <td><s:textfield name="mapName" value="%{mapName}" cssClass="inputTextBlue"/></td>
                                                            </tr>
                                                            
                                                            <tr>
                                                                <td class="fieldLabel">AssignedTo :</td>                                                    
                                                                <td><s:select list="employeesMaps" name="assignedToUIDs" cssClass="inputSelect"/></td> 
                                                                <td class="fieldLabel">Date Created :</td>                                                    
                                                                <td><s:textfield name="datesCreated" cssClass="inputTextBlueSmall" value=""/><a href="javascript:cal1.popup();">
                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                </td>
                                                            </tr>
                                                            
                                                            <tr>
                                                                <td class="fieldLabel">description :</td>                                                    
                                                                <td colspan="3"><s:textarea name="descriptions" cssClass="inputTextarea" rows="3" cols="73" onchange="descriptionsValidate(document.frmAccountAdd.descriptions.value);" /></td>
                                                            </tr>
                                                            
                                                        </table>
                                                        <script type="text/JavaScript">
                                                           var cal1 = new CalendarTime(document.forms['frmAccountAdd'].elements['datesCreated']);
                                                           cal1.year_scroll = true;
                                                           cal1.time_comp = false;
                                                        </script>
                                                    </s:form>
                                                    
                                                </div>
                                                <%-- </sx:div> --%>
                                                <!--//END TAB : -->
                                     
                                                <%-- </sx:tabbedpanel> --%>
                                                <!--//END TABBED PANNEL : -->
                                            </div>
                                            
                                            <script type="text/javascript">

var countries=new ddtabcontent("issueAddPannel")
countries.setpersist(false)
countries.setselectedClassTarget("link") //"link" or "linkparent"
countries.init()

</script>
                                            <ul id="IssueListPannel" class="shadetabs">
                                            <li ><a href="#" class="selected" rel="issuesListTab">Issues List</a></li>     
                                            </ul>
                                            
                                            
                                                <%-- <sx:tabbedpanel id="IssueListPannel" cssStyle="width: 840px; height: 250px;padding-left:10px;padding-top:5px;" doLayout="true"> --%>
                                            <div  style="border:1px solid gray; width:840px; height: 250px; overflow:auto; margin-bottom: 1em;">    
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
                                                if(request.getAttribute("currentMapId") != null){
                                                currentMapId = (String)request.getAttribute("currentMapId");
                                                }
                                                try{%>
                                                
                                                <!--//START TAB : -->
                                                <%-- <sx:div id="issuesListTab" label="Issues List"> --%>
                                                <div id="issuesListTab" class="tabcontent">    
                                                    <%
                                                    /* String Variable for storing current position of records in dbgrid*/
                                                    
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
                                                    
                                                    strSortCol = "IssueName";
                                                    
                                                    /* Getting DataSource using Service Locator */
                                                    
                                                    connection = ConnectionProvider.getInstance().getConnection();
                                                    
                                                    /* Sql query for retrieving resultset from DataBase */
                                                    queryString  =null;
                                                    
                                                    queryString = "Select Id,MapId,ProjectId,SubProjectId,CustomerId,IssueType,IssueName,DateCreated,DateClosed,Resolution,IssueState,Description from tblMapIssues";
                                                    queryString = queryString + " where ProjectId="+currentProjectId+" AND SubProjectId="+currentSubProjectId+" ORDER BY DateCreated DESC";
                                                    
                                                    String addAction = "getIssues.action";                                                    
                                                    %>
                                                    
                                                    <form action="" method="post" name="frmDBGrid">   
                                                        
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
                                                                <td class="subHeader" width="700"></td>
                                                            </tr>
                                                            <tr>
                                                                <td width="100%">
                                                                    
                                                                    <grd:dbgrid id="tblAccountList" name="tblAccountList" width="100" pageSize="6" 
                                                                                currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                                dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable"
                                                                                trAttributes="onClick=SelectCurrentRow('{Id}',2) ">
                                                                        
                                                                        <grd:gridpager imgFirst="../includes/images/DBGrid/First.gif" imgPrevious="../includes/images/DBGrid/Previous.gif" 
                                                                                       imgNext="../includes/images/DBGrid/Next.gif" imgLast="../includes/images/DBGrid/Last.gif"
                                                                                       addImage="../includes/images/DBGrid/Add.png"/>
                                                                        
                                                                        <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" 
                                                                                        imageAscending="../includes/images/DBGrid/ImgAsc.gif" 
                                                                                        imageDescending="../includes/images/DBGrid/ImgDesc.gif"/>     
                                                                        
                                                                        <grd:rownumcolumn headerText="SNo" width="4" HAlign="right"/>
                                                                        <grd:imagecolumn  headerText="Edit"  width="5"  HAlign="center"  
                                                                                          imageSrc="../includes/images/DBGrid/Edit.gif"
                                                                                          linkUrl="getIssues.action?issueId={Id}&mapId={MapId}&projectId={ProjectId}&subProjectId={SubProjectId}&accId={CustomerId}" 
                                                                                          imageBorder="0" imageWidth="16" imageHeight="16" alterText="Click to edit"/>
                                                                        <grd:textcolumn dataField="ISSUETYPE" headerText="ISSUE TYPE" width="20"/>                                                                        
                                                                        <grd:textcolumn dataField="Description" headerText="Description" width="20"/>
                                                                        <grd:textcolumn dataField="IssueState" headerText="Issue State" width="20"/>
                                                                        <grd:datecolumn dataField="DateCreated" headerText="Start Date" width="20" dataFormat="MM-dd-yyyy"/>
                                                                        <grd:datecolumn dataField="DateClosed" headerText="End Date" width="20" dataFormat="MM-dd-yyyy"/>
                                                                        
                                                                    </grd:dbgrid>
                                                                    
                                                                    <!-- these components are DBGrid Specific  -->
                                                                    <input type="hidden" name="txtCurr" value="<%=intCurr%>">
                                                                    <input type="hidden" id="txtSortCol" name="txtSortCol" value="<%=strSortCol%>">
                                                                    <input type="hidden" id="txtSortAsc" name="txtSortAsc" value="<%=strSortOrd%>">
                                                                    
                                                                    <input type="hidden" id="submitFrom" name="submitFrom" value="dbGrid">
                                                                    <input type="hidden" id="accountId" name="accountId" value="<%=currentAccountId%>"/>
                                                                    <input type="hidden" id="id" name="id" value="<%=currentProjectId%>"/>
                                                                    <input type="hidden" id="subProjectId" name="subProjectId" value="<%=currentSubProjectId%>"/>
                                                                    <input type="hidden" id="mapId" name="mapId" value="<%=currentMapId%>"/>
                                                                    
                                                                </td>
                                                            </tr>
                                                        </table>                                
                                                        
                                                    </form>
                                                    
                                                </div>
                                                <%-- </sx:div> --%>
                                                <!--//END TAB : -->
                                                
                                                <%
                                                }catch(Exception ex){
                                                out.println(ex.toString());
                                                }finally{
                                                if(connection!= null){
                                                connection.close();
                                                }
                                                }
                                                %>
                                                
                                            </div>    
                                                <%-- </sx:tabbedpanel> --%> 
                                            <script type="text/javascript">

var countries=new ddtabcontent("IssueListPannel")
countries.setpersist(false)
countries.setselectedClassTarget("link") //"link" or "linkparent"
countries.init()

</script>
                                        </td>
                                    </tr>
                                </table>
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
