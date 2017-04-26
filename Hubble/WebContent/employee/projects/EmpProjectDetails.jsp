<%--
/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * File    : EmpProjectDetails.jsp
 *
 * Package :
 *
 * $Author: hkondala $  
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 
 * *****************************************************************************
 */
 --%>

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
        
        <title>Hubble Organization Portal :: Project Details</title>
         <sx:head cache="true"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGProjDetails.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/projectsValidations/ProjectDetailsClientValidation.js"/>"></script> 
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
        String strSortOrd;
        int intSortOrd = 0;
        int intCurr;
        boolean blnSortAsc = true;
        String userRoleName;
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
                                <!--//START TABBED PANNEL : -->
                                
                                <table cellpadding="0" cellspacing="0" width="100%">
                                    <tr>
                                        
                                        <%--
                                        <%
                                        userRoleName = session.getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
                                        %> 
                                        <%
                                        if (("Customer Manager".equalsIgnoreCase(userRoleName)) ){
                                        %>
                                        <td><span class="fieldLabel">Account Name :</span>&nbsp;
                                        <label class="navigationText"><s:property value="#request.accountName"/></label></td>                                        
                                        <%  }else  {%>
                                        <td><span class="fieldLabel">Account Name :</span>&nbsp;
                                        <a href="<s:url action="../crm/accounts/getAccount"><s:param name="id" value="%{accountId}"/></s:url>" class="navigationText"><s:property value="#request.accountName"/></a></td>
                                        <%}%>
                                        --%>
                                        
                                    </tr>
                                    <tr>
                                        <td valign="top">
                                            <!--//START TABBED PANNEL : -->
                                            <sx:tabbedpanel id="projectPannel" cssStyle="width: 840px; padding-left:10px; padding-top:5px;" doLayout="false">
                                                
                                                <!--//START TAB : -->
                                                <sx:div id="ProjectDetailsTab" label="Project Details" >
                                                    <s:form name="frmProjectEdit" action="" theme="simple" >
                                                        
                                                        <table cellpadding="1" cellspacing="1" border="0" width="100%">
                                                            <tr align="right">
                                                                <td class="headerText" colspan="6">
                                                                    <s:property value="#request.resultMessage"/>
                                                                    <s:hidden name="customerId" value="%{currentProject.customerId}" />
                                                                    <s:hidden name="id" value="%{currentProject.id}"/>
                                                                    <%--
                                                                    <s:submit cssClass="buttonBg" value="Back to List"/>
                                                                    --%>
                                                                    <input type="button" class="buttonBg" value="Back" onclick="history.go(-1);return false;" />
                                                                </td>
                                                            </tr>      
                                                            
                                                            <tr>
                                                                <td class="fieldLabel" >Project Name:</td>                                                    
                                                                <td><s:textfield name="prjName" cssClass="inputTextBlueLarge" value="%{currentProject.prjName}" onkeypress="return handleEnter(this,event);" onchange="prjNameValidate(document.frmProjectEdit.prjName.value);" readonly="true"/></td>                                                     
                                                                <td class="fieldLabel">Project Type :</td>
                                                                <td>
                                                                    <s:select list="prjectTypeList" name="projectType" cssClass="inputSelect" value="%{currentProject.projectType}"  headerKey="" headerValue="--Please Select--" disabled="true"/>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel" align="right">Start Date :</td>
                                                                <td align="left"><s:textfield name="startDate" cssClass="inputTextBlueSmall" value="%{currentProject.startDateOne}" onkeypress="return handleEnter(this,event);" disabled="true"/><a href="javascript:cal1.popup();">
                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                </td>
                                                                <td class="fieldLabel">End Date :</td>
                                                                <td><s:textfield name="endDate" cssClass="inputTextBlueSmall" value="%{currentProject.endDateOne}" onkeypress="return handleEnter(this,event);" disabled="true"/><a href="javascript:cal2.popup();">
                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel">Project Manager :</td>                                                    
                                                                <td><s:select list="projectManagersMap" name="prjManagerUID" cssClass="inputSelectLarge" value="%{currentProject.prjManagerUID}"  headerKey="" headerValue="--Please Select--" disabled="true"/> </td> 
                                                                <td class="fieldLabel">No.Of ReSources :</td>                                                    
                                                                <td><s:textfield name="totalResources" cssClass="inputTextBlue" value="%{currentProject.totalResources}" onkeypress="return handleEnter(this,event);" onchange="totalResourcesValidate(document.frmProjectEdit.totalResources.value);"  onblur="return validatenumber(this)" readonly="true"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel">Descrption :</td>
                                                                <td colspan="3"><s:textarea cols="85" rows="2" name="description" cssClass="inputTextarea" value="%{currentProject.description}" onkeypress="return handleEnter(this,event);" onchange="descriptionValidate(document.frmProjectEdit.description.value);" readonly="true"/></td>                                                    
                                                            </tr>
                                                        </table>
                                                        <script type="text/JavaScript">
                                                           var cal1 = new CalendarTime(document.forms['frmProjectEdit'].elements['startDate']);
                                                           cal1.year_scroll = true;
                                                           cal1.time_comp = false;
                                                           
                                                           var cal2 = new CalendarTime(document.forms['frmProjectEdit'].elements['endDate']);
                                                           cal2.year_scroll = true;
                                                           cal2.time_comp = false;
                                                    </script>
                                                    </s:form>
                                                </sx:div>
                                                
                                                <!--//END TAB : -->
                                            </sx:tabbedpanel>
                                            <!--//END TABBED PANNEL : -->
                                        </td>
                                    </tr>
                                    <tr>
                                        <td valign="top">
                                            
                                            <!--//END TABBED PANNEL : -->
                                 
                                            <!--//START TABBED PANNEL : --> 
                                            <sx:tabbedpanel id="ProjectDetailsPannel" cssStyle="width: 840px; height: 300px;padding-left:10px;padding-top:5px;" doLayout="true" useSelectedTabCookie="true">
                                                
                                                <%
                                                
                                                if(request.getAttribute("currentAccountId") != null){
                                            currentAccountId = (String)request.getAttribute("currentAccountId");
                                                }
                                        
                                        if(request.getAttribute("currentProjectId") != null){
                                            currentProjectId = (String)request.getAttribute("currentProjectId");
                                        }
                                        try{%>
                                                
                                                <sx:div id="subProjectListTab" label="SubProject List" >
                                                    
                                                    <%
                                                    
                                                    
                                                    /* Getting DataSource using Service Locator */
                                                    
                                                    connection = ConnectionProvider.getInstance().getConnection();
                                                    
                                                    intCurr = 1;
                                                    /* String Variable for storing current position of records in dbgrid*/
                                                    strTmp = request.getParameter("txtSubProjCurr");
                                                    
                                                    if (strTmp != null)
                                                        intCurr = Integer.parseInt(strTmp);
                                                    
                                                    /* Specifing Shorting Column */
                                                    strSortCol = request.getParameter("txtSubProjSortCol");
                                                    
                                                    if (strSortCol == null) strSortCol = "SubProjectName";
                                                    
                                                    strSortOrd = request.getParameter("txtSubProjSortAsc");
                                                    if (strSortOrd == null) strSortOrd = "DESC";
                                                    
                                                    /* Sql query for retrieving resultset from DataBase */
                                                    queryString  =null;
                                                    queryString = " Select Id,SubProjectName,CurStatus,TeamSize,StartDate,EndDate,ProjectId  from tblSubProjects";
                                                    queryString = queryString + " where ProjectId="+currentProjectId+" ORDER BY StartDate DESC";
                                                    
                                                    //String addSubProjectsAction = "subProject.action?accountId="+currentAccountId+"&id="+currentProjectId;
                                                    
                                                    
                                                    %>
                                                    <s:form action="" theme="simple" name="frmDBSubProjGrid">   
                                                        
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
                                                                        
                                                                        <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                                       imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                                       addImage="../../includes/images/DBGrid/Add.png"/>   
                                                                                       <%--
                                                                                       addAction="<%=addSubProjectsAction%>"
                                                                                       scriptFunction="getNextSubProjects"/>
                                                                                       --%>
                                                                        <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" 
                                                                                        imageAscending="../../includes/images/DBGrid/ImgAsc.gif" 
                                                                                        imageDescending="../../includes/images/DBGrid/ImgDesc.gif"/>    
                                                                        
                                                                        <grd:rownumcolumn headerText="SNo" width="4" HAlign="right"/>
                                                                        <grd:imagecolumn  headerText="Edit"  width="5"  HAlign="center"  
                                                                                          imageSrc="../../includes/images/DBGrid/Edit.gif"  
                                                                                          linkUrl="getEmpSubProject.action?subProjectId={Id}&projectId={ProjectId}" 
                                                                                          imageBorder="0" imageWidth="16" imageHeight="16" alterText="Click to edit" />
                                                                        
                                                                        <grd:textcolumn dataField="SubProjectName"  	headerText="SubProject Name" width="8"/>
                                                                        <grd:textcolumn dataField="CurStatus"  	headerText="Status" width="8"/>
                                                                        <grd:datecolumn dataField="StartDate"  	headerText="Start Date" width="8" dataFormat="MM-dd-yyyy" />
                                                                        <grd:datecolumn dataField="EndDate" headerText="End Date" width="8" dataFormat="MM-dd-yyyy" />
                                                                    </grd:dbgrid>
                                                                    
                                                                    <input TYPE="hidden" NAME="txtSubProjCurr" VALUE="<%=intCurr%>">
                                                                    <input TYPE="hidden" NAME="txtSubProjSortCol" VALUE="<%=strSortCol%>">
                                                                    <input TYPE="hidden" NAME="txtSubProjSortAsc" VALUE="<%=strSortOrd%>">
                                                                    <input TYPE="hidden" NAME="txtAttachCurr" VALUE="">    
                                                                    <input type="hidden" name="id" value="<%=currentAccountId%>">
                                                                    <input type="hidden" name="submitFrom" value="dbGrid">
                                                                    <input type="hidden" name="isRequestFromGrid" value="YES">
                                                                </td>
                                                            </tr>
                                                        </table>                                
                                                    </s:form>
                                                </sx:div>
                                                
                                                <sx:div id="attachmentsList" label="Project Attachments" >
                                                    
                                                    <%
                                                    
                                                    /* String Variable for storing current position of records in dbgrid*/
                                                    strTmp = request.getParameter("txtAttachCurr");
                                                    
                                                    if (strTmp != null)
                                                        intCurr = Integer.parseInt(strTmp);
                                                    
                                                    /* Specifing Shorting Column */
                                                    strSortCol = request.getParameter("txtAttachSortCol");
                                                    
                                                    if (strSortCol == null) strSortCol = "ObjectType";
                                                    
                                                    strSortOrd = request.getParameter("txtAttachSortAsc");
                                                    if (strSortOrd == null) strSortOrd = "ASC";
                                                    
                                                    /* Sql query for retrieving resultset from DataBase */
                                                    queryString ="Select Id,AttachmentType,DateOfUpload,UploadedBy,Description,ObjectId,ObjectType from tblPrjAttachments WHERE ObjectType ='Project'";
                                                    queryString = queryString + "AND ObjectId ="+currentAccountId+" ORDER BY DateOfUpload DESC";
                                                    
                                                    String attachmentAction = "getAttachment.action";
                                                    
                                                    if(request.getAttribute("currentAccountId") != null){
                                                        attachmentAction = attachmentAction+"?objectId="+request.getAttribute("currentAccountId")+"&objectType=Project"+"&projectId="+currentProjectId;
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
                                                                    <grd:dbgrid id="tblprjAttachments" name="tblprjAttachments" width="100" pageSize="8" 
                                                                                currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                                dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                        
                                                                        <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                                       imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                                       addImage="../../includes/images/DBGrid/Add.png" />
                                                                                       <%--
                                                                                       addAction="<%=attachmentAction%>"
                                                                                       scriptFunction="getProjAttachment"/>
                                                                                        --%>
                                                                        <grd:textcolumn dataField="AttachmentType"  headerText="Attachment Type"   width="30" sortable="true"/> 
                                                                        <grd:textcolumn dataField="ObjectType"  headerText="Object Type" width="30"/>  
                                                                        <grd:datecolumn dataField="DateOfUpload"  headerText="Date Uploaded" dataFormat="MM-dd-yyyy HH:mm:SS" width="20"/>
                                                                        <grd:imagecolumn headerText="Download" width="4" HAlign="center" 
                                                                                         imageSrc="../../includes/images/download_11x10.jpg"
                                                                                         linkUrl="getDownload.action?Id={Id}" imageBorder="0"
                                                                                         imageWidth="11" imageHeight="10" alterText="Download"></grd:imagecolumn>
                                                                    </grd:dbgrid>
                                                                    <!-- these components are DBGrid Specific  -->
                                                                    
                                                                    <input TYPE="hidden" NAME="txtAttachCurr" VALUE="<%=intCurr%>">
                                                                    <input TYPE="hidden" NAME="txtAttachSortCol" VALUE="<%=strSortCol%>">
                                                                    <input TYPE="hidden" NAME="txtAttachSortAsc" VALUE="<%=strSortOrd%>">
                                                                    <input TYPE="hidden" NAME="txtSubProjCurr" VALUE="">
                                                                    <input TYPE="hidden" NAME="submitFrom" VALUE="dbGrid">
                                                                    <input type="hidden" name="id" value="<%=currentAccountId%>">
                                                                </td>
                                                            </tr>
                                                        </table>    
                                                    </form>  
                                                    
                                                </sx:div>
                                                
                                                <%--
                                                <!--//START TAB : -->
                                                <s:div id="mapListTab" label="Map List" theme="ajax" >
                                                    
                                                    <%
                                                    
                                                    
                                                    intCurr = 1;
                                                    
                                                    /* String Variable for storing current position of records in dbgrid*/
                                                    strTmp = request.getParameter("txtMapsCurr");
                                                    
                                                    if (strTmp != null)
                                                        intCurr = Integer.parseInt(strTmp);
                                                    
                                                    /* Specifing Shorting Column */
                                                    strSortCol = request.getParameter("txtMapsSortCol");
                                                    
                                                    if (strSortCol == null) strSortCol = "MapStartDate";
                                                    
                                                    strSortOrd = request.getParameter("txtMapsSortAsc");
                                                    if (strSortOrd == null) strSortOrd = "DESC";
                                                    
                                                    
                                                    
                                                    /* Sql query for retrieving resultset from DataBase */
                                                    queryString  =null;
                                                    
                                                    queryString ="Select Id,MapName,MapStartDate,CurrentState,MapperUID,ProjectId,CustomerId  from tblMaps";
                                                    queryString = queryString+" where ProjectId="+currentProjectId+" ORDER BY MapStartDate DESC";
                                                    
                                                    String addMapAction = "map.action?accountId="+currentAccountId+"&id="+currentProjectId;
                                                    
                                                    %>
                                                    <s:form action="" theme="simple" name="frmDBMapsGrid">   
                                                        
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
                                                                    <grd:dbgrid id="tblMapsList" name="tblMapsList" width="100" pageSize="7" 
                                                                                currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                                dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                        
                                                                        <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                                       imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                                       addImage="../../includes/images/DBGrid/Add.png"   
                                                                                       addAction="<%=addMapAction%>"
                                                                                       scriptFunction="getNextMaps"/>
                                                                        
                                                                        <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" 
                                                                                        imageAscending="../../includes/images/DBGrid/ImgAsc.gif" 
                                                                                        imageDescending="../../includes/images/DBGrid/ImgDesc.gif"/>    
                                                                        
                                                                        <grd:rownumcolumn headerText="SNo" width="4" HAlign="right"/>
                                                                        <grd:imagecolumn  headerText="Edit"  width="5"  HAlign="center"  
                                                                                          imageSrc="../../includes/images/DBGrid/Edit.gif"  
                                                                                          linkUrl="getMap.action?mapId={Id}&projectId={ProjectId}&accountId={CustomerId}" 
                                                                                          imageBorder="0" imageWidth="16" imageHeight="16" 
                                                                                          alterText="Click to Edit" />
                                                                        
                                                                        <grd:textcolumn dataField="MapName"  headerText="Map Name" width="8"/>
                                                                        <grd:datecolumn dataField="MapStartDate" headerText="Map Start Date" width="8"dataFormat="MM-dd-yyyy" />
                                                                        <grd:textcolumn dataField="CurrentState" headerText="Status" width="8"  />
                                                                        
                                                                    </grd:dbgrid>
                                                                    
                                                                    <input TYPE="hidden" NAME="txtMapsCurr" VALUE="<%=intCurr%>">
                                                                    
                                                                    <input TYPE="hidden" NAME="txtSubProjCurr" VALUE="">
                                                                    
                                                                    <input TYPE="hidden" NAME="txtMapsSortCol" VALUE="<%=strSortCol%>">
                                                                    <input TYPE="hidden" NAME="txtMapsSortAsc" VALUE="<%=strSortOrd%>">
                                                                    
                                                                </td>
                                                            </tr>
                                                        </table>                                
                                                        
                                                    </s:form>
                                                    
                                                </s:div>
                                                <!--//END TAB : -->
                                                
                                                --%>
                                                <%
                                                }catch(Exception ex){
                                                    out.println(ex.toString());
                                                }finally{
                                                    if(connection!= null){
                                                        connection.close();
                                                    }
                                                }
                                                %>
                                                
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
                <td align="center"><s:include value="/includes/template/Footer.jsp"/></td>
            </tr>
            <!--//END FOOTER : Record for Footer Background and Content-->
        </table>
        <!--//END MAIN TABLE : Table for template Structure-->
    </body>
</html>
