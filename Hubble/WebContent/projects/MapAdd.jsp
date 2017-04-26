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
 * File    : MapAdd.jsp
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
        
        <title>Hubble Organization Portal :: Map Adding</title>
         <%-- <sx:head cache="false"/> --%>
        
        <%--This link for ToolTip css --%>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tooltip.css"/>">
        <%--This is End for ToolTip css --%>
         
        <%--This link for ToolTip js --%>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tooltip.js"/>"></script>
        <%--This End for ToolTip js --%>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CrmAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CommonAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>        
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/MarsAccount.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/projectsValidations/MapAddClientValidation.js"/>"></script> 
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
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
        String userRoleName;
        String strSortOrd = "ASC";
        
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
                                            <span class="fieldLabel">SubProject Name :</span>&nbsp;
                                            <a href="<s:url action="getSubProject">
                                                   <s:param name="subProjectId" value="%{subProjectId}"/>
                                                   <s:param name="projectId" value="%{id}"/>
                                               </s:url>" class="navigationText"><s:property value="#request.subProjectName"/>
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
                                            <span class="fieldLabel">SubProject Name :</span>&nbsp;
                                            <a href="<s:url action="getSubProject">
                                                   <s:param name="subProjectId" value="%{subProjectId}"/>
                                                   <s:param name="projectId" value="%{id}"/>
                                               </s:url>" class="navigationText"><s:property value="#request.subProjectName"/>
                                            </a>
                                            <%}%>
                                            
                                            
                                        </td>
                                    </tr>
                                    
                                    
                                    <tr>
                                        <td valign="top" style="padding-left:10px;padding-top:5px;">
                                            <ul id="accountPannel" class="shadetabs" >
                                            <li ><a href="#" class="selected" rel="mapTab">Map Add</a></li>              
                                            </ul>                                

                                            <!--//START TABBED PANNEL : -->
                                            <%-- <sx:tabbedpanel id="accountPannel" cssStyle="width: 840px; height: 280px;padding-left:10px;padding-top:5px;" doLayout="true"> --%>
                                            <div  style="border:1px solid gray; width:840px; height: 280px; overflow:auto; margin-bottom: 1em;">    
                                                <!--//START TAB : -->
                                                <div id="mapTab" class="tabcontent">
                                                    <%-- <sx:div id="mapTab" label="Map Add"> --%>
                                                    <s:form name="frmMapAdd" action="addMap" theme="simple">
                                                        
                                                        <table cellpadding="1" cellspacing="1" border="0" width="100%">
                                                            
                                                            <tr align="right">
                                                                <td class="headerText" colspan="6">
                                                                    
                                                                    
                                                                    <% if(request.getAttribute(ApplicationConstants.RESULT_MSG) != null){
                                                                    out.println(request.getAttribute(ApplicationConstants.RESULT_MSG));
                                                                    }%>
                                                                    <s:submit cssClass="buttonBg" value="Add"/> 
                                                                    <s:hidden name="accountId" value="%{accountId}"/>
                                                                    <s:hidden name="id" value="%{id}"/>
                                                                    <s:hidden name="subProjectId" value="%{subProjectId}"/>  
                                                                </td>
                                                            </tr>      
                                                            
                                                            
                                                            <tr>  
                                                                <td class="fieldLabel">Project :</td>                                                    
                                                                <td><s:textfield name="projectNames" value="%{projectName}" cssClass="inputTextBlueLarge" disabled="yes"/></td>                                                     
                                                                
                                                                <td class="fieldLabel">SubProject :</td>                                                    
                                                                <td><s:textfield name="subProjectNames" value="%{subProjectName}" cssClass="inputTextBlueLarge" disabled="yes"/></td>          
                                                                
                                                            </tr>
                                                            
                                                            <tr>
                                                                
                                                                <td class="fieldLabel">Map Name :</td>                                                    
                                                                <td><s:textfield name="mapName" cssClass="inputTextBlueLarge" onchange="mapNameValidate(document.frmMapAdd.mapName.value);"/></td> 
                                                                
                                                                <td class="fieldLabel">Current State :</td>                                                    
                                                                <td><s:select list="mapStatusTypeList" name="currentState" cssClass="inputSelect" headerKey="" headerValue="--Please Select--" /></td>
                                                                
                                                            </tr>
                                                            
                                                            <tr>
                                                                <td class="fieldLabel">Start Date :</td>                                                    
                                                                <td><s:textfield name="startDate" cssClass="inputTextBlueSmall" value=""/><a href="javascript:cal1.popup();">
                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                </td>
                                                                
                                                                <td class="fieldLabel">End Date :</td>                                                    
                                                                <td><s:textfield name="endDate" cssClass="inputTextBlueSmall" value=""/><a href="javascript:cal2.popup();">
                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                </td> 
                                                                
                                                                
                                                            </tr>
                                                            <tr>
                                                                
                                                                <td class="fieldLabel">Bussiness Domain :</td>                                                    
                                                                <td><s:textfield name="bussinessDomain" cssClass="inputTextBlueLarge" onchange="bussinessDomainValidate(document.frmMapAdd.bussinessDomain.value);"/></td> 
                                                                
                                                                <td class="fieldLabel">Tool :</td>                                                    
                                                                <td><s:select list="toolsListMaps" name="mapTools" headerKey="" headerValue="--Please Select--" cssClass="inputSelect" /></td>
                                                                
                                                            </tr>
                                                            <tr>
                                                                
                                                                <td class="fieldLabel">Project Manager :</td>                                                    
                                                                <td><s:select list="projectManagerLists" name="projectManager" headerKey="" headerValue="--Please Select--" cssClass="inputSelectLarge" /></td>
                                                                
                                                                <td class="fieldLabel">Tech Lead :</td>
                                                                <td><s:select list="techLeadMaps" name="techLead" headerKey="" headerValue="--Please Select--" cssClass="inputSelectLarge" /></td> 
                                                            </tr>
                                                            
                                                            <tr>
                                                                
                                                                <td class="fieldLabel">Mapper :</td>
                                                                <td><s:select name="mapper" list="mapperMaps" headerKey="" headerValue="--Please Select--"  cssClass="inputSelectLarge"/></td> 
                                                                
                                                                <td class="fieldLabel">Tester:</td>                                                    
                                                                <td><s:select name="tester" list="testerMaps" headerKey="" headerValue="--Please Select--"  cssClass="inputSelectLarge"/></td>
                                                                
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel">Descrption :</td>
                                                                <td colspan="3" ><s:textarea cssClass="inputTextarea" rows="2" cols="83" name="description" cssClass="inputTextarea" onchange="descriptionValidate(document.frmMapAdd.description.value);"/></td>                                                    
                                                                
                                                            </tr>
                                                        </table>
                                                        <script type="text/JavaScript">
                                                           var cal1 = new CalendarTime(document.forms['frmMapAdd'].elements['startDate']);
                                                           cal1.year_scroll = true;
                                                           cal1.time_comp = false;
                                                           
                                                           var cal2 = new CalendarTime(document.forms['frmMapAdd'].elements['endDate']);
                                                           cal2.year_scroll = true;
                                                           cal2.time_comp = false;
                                                        </script>
                                                    </s:form>
                                                    
                                                </div>    
                                            </div>        
                                            
                                            <script type="text/javascript">

var countries=new ddtabcontent("accountPannel")
countries.setpersist(false)
countries.setselectedClassTarget("link") //"link" or "linkparent"
countries.init()

</script>
                                            
                                                    <%--</sx:div >
                                                <!--//END TAB :                                                 
                                            </sx:tabbedpanel>--%>
                                            <!--//END TABBED PANNEL : -->
                                
                                            <ul id="mapListPannel" class="shadetabs">
                                            <li ><a href="#" class="selected" rel="mapListTab">Map List</a></li>                                    
                                            </ul>                                            
                                            
                                            <div  style="border:1px solid gray; width:840px;  height: 220px; overflow:auto; margin-bottom: 1em;">
                                                <%-- <sx:tabbedpanel id="mapListPannel" cssStyle="width: 840px; height: 220px;padding-left:10px;padding-top:5px;" doLayout="true"> --%>
                                                
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
                                                
                                                try{%>
                                                
                                                <div id="mapListTab" class="tabcontent">
                                                    <%-- <sx:div id="mapListTab" label="Map List" > --%>
                                                    
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
                                                    
                                                    strSortCol = "MapName";
                                                    
                                                    connection = ConnectionProvider.getInstance().getConnection();
                                                    
                                                    /* Sql query for retrieving resultset from DataBase */
                                                    queryString  =null;
                                                    queryString = "Select Id,MapperUID,MapName,ProjectId,SubProjectId,MapStartDate,CustomerId from tblMaps";
                                                    queryString = queryString + " where ProjectId="+currentProjectId+" AND SubProjectId="+currentSubProjectId+" ORDER BY MapStartDate DESC";
                                                    
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
                                                            <tr>  <%--<tr align="right">
                                                                <td class="headerText">
                                                                    <img alt="Home" 
                                                                         src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" 
                                                                         width="100%" 
                                                                         height="13px" 
                                                                         border="0">
                                                                </td>
                                                            </tr>    --%>
                                                                <td width="100%">
                                                                    <grd:dbgrid id="tblSubProjectsList" name="tblSubProjectsList" width="100" pageSize="6" 
                                                                                currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                                dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                        
                                                                        <grd:gridpager imgFirst="../includes/images/DBGrid/First.gif" imgPrevious="../includes/images/DBGrid/Previous.gif" 
                                                                                       imgNext="../includes/images/DBGrid/Next.gif" imgLast="../includes/images/DBGrid/Last.gif"
                                                                                       addImage="../includes/images/DBGrid/Add.png"/>
                                                                        
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
                                                                    
                                                                    <!-- these components are DBGrid Specific  -->
                                                                    <input type="hidden" name="txtCurr" value="<%=intCurr%>">
                                                                    <input type="hidden" id="txtSortCol" name="txtSortCol" value="<%=strSortCol%>">
                                                                    <input type="hidden" id="txtSortAsc" name="txtSortAsc" value="<%=strSortOrd%>">
                                                                    
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
                                                <%-- </sx:tabbedpanel>  --%>                                          
                                                <!--//END TABBED PANNEL : -->
                                            </div>
                                            
                                            <script type="text/javascript">

var countries=new ddtabcontent("mapListPannel")
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
