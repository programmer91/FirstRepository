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
 * File    : SubProjectAdd.jsp
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
        
        <title>Hubble Organization Portal :: SubProject Adding</title>
        <%-- <sx:head cache="false"/> --%>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>        
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/projectsValidations/SubProjectAddClientValidation.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
    </head>
    
    <body class="bodyGeneral">        
        
        <%!
        /* Declarations */
        Connection connection;
        String queryString;
        String currentAccountId;
        String currentProjectId;
        String strTmp;
        String strSortCol;
        String strSortOrd = "DESC";
        String userRoleName;
        
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
                                            <label class="navigationText"><s:property value="#request.accountName"/>
                                            </label>&nbsp;&nbsp;
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
                                                <li ><a href="#" class="selected" rel="subProjectTab"> Add SubProject </a></li>                                                
                                            </ul>
                                            
                                            <!--//START TABBED PANNEL : -->
                                            <%-- <sx:tabbedpanel id="subProjectPannel" cssStyle="width: 840px; height: 200px;padding-left:10px;padding-top:5px;" doLayout="true"> --%>
                                            <div  style="border:1px solid gray; width:840px;height: 200px; overflow:auto; margin-bottom: 1em;">
                                                
                                                <!--//START TAB : -->                                                
                                                <%-- <sx:div id="subProjectTab" label="Add SubProject"> --%>
                                                <div id="subProjectTab" class="tabcontent">
                                                    <s:form name="frmSubProjectAdd" action="addSubProject" theme="simple">
                                                        
                                                        <table cellpadding="1" cellspacing="1" border="0" width="100%">
                                                            
                                                            <tr align="right">
                                                                <td class="headerText" colspan="6">
                                                                    <% if(request.getAttribute(ApplicationConstants.RESULT_MSG) != null){
                                                                    out.println(request.getAttribute(ApplicationConstants.RESULT_MSG));
                                                                    }%>
                                                                    
                                                                    <s:submit cssClass="buttonBg" value="Save"/>
                                                                    <s:hidden name="accountId" value="%{accountId}"/>
                                                                    <s:hidden name="projectId" value="%{id}"/>
                                                                </td>
                                                            </tr>      
                                                            
                                                            <tr>
                                                                <td class="fieldLabel">SubProject  Name :</td>                                                    
                                                                <td><s:textfield name="subPrjName" cssClass="inputTextBlueLarge" onchange="subPrjNameValidate(document.frmSubProjectAdd.subPrjName.value);" /></td> 
                                                                <td class="fieldLabel">Current State :</td>                                                    
                                                                <td><s:select  list="projectStatusTypeList" name="currentState" cssClass="inputSelect" headerKey="" headerValue="--Please Select--" onkeypress="return handleEnter(this,event);"/></td>
                                                                
                                                            </tr>
                                                            
                                                            <tr>
                                                                <td class="fieldLabel" align="right">Start Date :</td>                                                    
                                                                <td align="left"><s:textfield name="startDate" cssClass="inputTextBlueSmall" onkeypress="return handleEnter(this,event);"/> <a href="javascript:cal1.popup();">
                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                </td>
                                                                <td class="fieldLabel">End Date :</td>                                                    
                                                                <td><s:textfield name="endDate" cssClass="inputTextBlueSmall" onkeypress="return handleEnter(this,event);"/> <a href="javascript:cal2.popup();">
                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                </td>
                                                            </tr>
                                                            
                                                            <tr>                 
                                                                <td class="fieldLabel">Team Lead :</td>                                                    
                                                                <td><s:select list="teamLeadMap" name="teamLeadUID" cssClass="inputSelectLarge" headerKey="" headerValue="--Please Select--" onkeypress="return handleEnter(this,event);"/></td>
                                                                <td class="fieldLabel">Team Size :</td>
                                                                <td>
                                                                    <s:textfield name="totalResources" cssClass="inputTextBlue" onchange="totalResourcesValidate(document.frmSubProjectAdd.totalResources.value);" onblur="return validatenumber(this)"/>
                                                                </td>
                                                            </tr>
                                                            
                                                            <tr>
                                                                <td class="fieldLabel">Descrption :</td>
                                                                <td colspan="3" ><s:textarea cssClass="inputTextarea" rows="2" cols="83" name="description" onchange="descriptionValidate(document.frmSubProjectAdd.description.value);"  /></td>                                                    
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
                                                </div>   
                                                <%-- </sx:div > --%>
                                                <!--//END TAB : -->
                                            
                                            </div>    
                                            <%-- </sx:tabbedpanel> --%>
                                            <!--//END TABBED PANNEL : -->
                                 <script type="text/javascript">

var countries=new ddtabcontent("subProjectPannel")
countries.setpersist(false)
countries.setselectedClassTarget("link") //"link" or "linkparent"
countries.init()

                                </script>
                                
                                
                                
                                            <ul id="SubProjectDetailsPannel" class="shadetabs" >
                                                <li ><a href="#" class="selected" rel="subProjectListTab"> SubProject List </a></li>                                                
                                            </ul>
                                            
                                            <div  style="border:1px solid gray; width:840px;height: 300px; overflow:auto; margin-bottom: 1em;"> 
                                            <!--//START TABBED PANNEL : --> 
                                            <%-- <sx:tabbedpanel id="SubProjectDetailsPannel" cssStyle="width: 840px; height: 300px;padding-left:10px;padding-top:5px;" doLayout="true"> --%>
                                            
                                                <%
                                                
                                                if(request.getAttribute("currentAccountId") != null){
                                                currentAccountId = (String)request.getAttribute("currentAccountId");
                                                }
                                                
                                                if(request.getAttribute("currentProjectId") != null){
                                                currentProjectId = (String)request.getAttribute("currentProjectId");
                                                }
                                                try{%>
                                                
                                                <div id="subProjectListTab" class="tabcontent">
                                                    <%-- <sx:div id="subProjectListTab" label="SubProject List"> --%>
                                                    
                                                    <% 
                                                    /* Getting DataSource using Service Locator */
                                                    
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
                                                    
                                                    strSortCol = "SubProjectName";
                                                    
                                                    connection = ConnectionProvider.getInstance().getConnection();
                                                    
                                                    /* Sql query for retrieving resultset from DataBase */
                                                    queryString  =null;
                                                    queryString = " Select Id,SubProjectName,CurStatus,TeamSize,StartDate,EndDate,ProjectId  from tblSubProjects";
                                                    queryString = queryString + " where ProjectId="+currentProjectId+" ORDER BY StartDate DESC";
                                                    
                                                    String addSubProjectsAction = "subProject.action?accountId="+currentAccountId+"&id="+currentProjectId;
                                                    
                                                    // out.println(queryString);
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
                                                                <td width="100%">
                                                                    <grd:dbgrid id="tblSubProjectsList" name="tblSubProjectsList" width="100" pageSize="8" 
                                                                                currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                                dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                        
                                                                        <grd:gridpager imgFirst="../includes/images/DBGrid/First.gif" imgPrevious="../includes/images/DBGrid/Previous.gif" 
                                                                                       imgNext="../includes/images/DBGrid/Next.gif" imgLast="../includes/images/DBGrid/Last.gif"
                                                                                       addImage="../includes/images/DBGrid/Add.png"   
                                                                                       addAction="<%=addSubProjectsAction%>"/>
                                                                        <%--
                                                                        <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" 
                                                                                        imageAscending="../includes/images/DBGrid/ImgAsc.gif" 
                                                                                        imageDescending="../includes/images/DBGrid/ImgDesc.gif"/>    
                                                                        --%>
                                                                        <grd:rownumcolumn headerText="SNo" width="4" HAlign="right"/>
                                                                        <grd:imagecolumn  headerText="Edit"  width="5"  HAlign="center"  
                                                                                          imageSrc="../includes/images/DBGrid/Edit.gif"  
                                                                                          linkUrl="getSubProject.action?subProjectId={Id}&projectId={ProjectId}" 
                                                                                          imageBorder="0" imageWidth="16" imageHeight="16" alterText="Click to edit" />
                                                                        
                                                                        <grd:textcolumn dataField="SubProjectName"  	headerText="SubProject Name" width="8"/>
                                                                        <grd:textcolumn dataField="CurStatus"  	headerText="Status" width="8"/>
                                                                        <grd:datecolumn dataField="StartDate"  	headerText="Start Date" width="8" dataFormat="MM-dd-yyyy" />
                                                                        <grd:datecolumn dataField="EndDate" headerText="End Date" width="8" dataFormat="MM-dd-yyyy" />
                                                                    </grd:dbgrid>
                                                                    
                                                                    <!-- these components are DBGrid Specific  -->
                                                                    <input type="hidden" name="txtCurr" value="<%=intCurr%>">
                                                                    <input type="hidden" id="txtSortCol" name="txtSortCol" value="<%=strSortCol%>">
                                                                    <input type="hidden" id="txtSortAsc" name="txtSortAsc" value="<%=strSortOrd%>">
                                                                    <input type="hidden" id="accountId" name="accountId" value="<%=currentAccountId%>"/>
                                                                    <input type="hidden" id="id" name="id" value="<%=currentProjectId%>"/>
                                                                    <input type="hidden" name="submitFrom" value="dbGrid">
                                                                    
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
                                                
                                                <%-- </sx:tabbedpanel> --%>
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
