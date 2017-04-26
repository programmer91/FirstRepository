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
 * File    : IssueDetails.jsp
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
        
        <title>Hubble Organization Portal :: Map Issue Details</title>
        <sx:head cache="true"/>
        
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
        String currentIssueId;
        String userRoleName;
        String strTmp;
        String strSortCol;
        String strSortOrd = "ASC";
        
        int intSortOrd = 0;
        int intCurr=1;
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
                                            
                                        <ul id="subProjectPannel" class="shadetabs">
                                        <li ><a href="#" class="selected" rel="subProjectTab">Map Issue Details</a></li>     
                                        </ul>   
                                            
                                            <!--//START TABBED PANNEL : -->
                                            <%-- <sx:tabbedpanel id="issueDetailsPannel" cssStyle="width: 840px; height: 250px;padding-left:10px;padding-top:5px;" doLayout="true"> --%>
                                            <div  style="border:1px solid gray; width:840px; height: 250px; overflow:auto; margin-bottom: 1em;">
                                                
                                                <!--//START TAB : -->
                                                <%-- <sx:div id="accountTab" label="Map Issue Details"> --%>
                                                <div id="subProjectTab" class="tabcontent">
                                                    
                                                    <s:form name="frmAccountAdd" action="updateIssue" theme="simple" method="post" enctype="multipart/form-data">
                                                        <%-- method="post" enctype="multipart/form-data">  --%>
                                                        
                                                        <table cellpadding="1" cellspacing="1" border="0" width="100%">
                                                            
                                                            <tr align="right">
                                                                <td class="headerText" colspan="6">
                                                                    <% if(request.getAttribute(ApplicationConstants.RESULT_MSG) != null){
                                                                    out.println(request.getAttribute(ApplicationConstants.RESULT_MSG));
                                                                    }%>                                                 
                                                                    <s:submit cssClass="buttonBg" value="Update"/>                                                                    
                                                                    <s:hidden name="id" value="%{accountId}"/>
                                                                    <s:hidden name="issueId" value="%{issueId}"/>
                                                                    <s:hidden name="mapId" value="%{mapId}"/>
                                                                    <s:hidden name="projectId" value="%{projectId}"/>
                                                                    <s:hidden name="subProjectId" value="%{subProjectId}"/>
                                                                    
                                                                </td>
                                                            </tr>      
                                                            
                                                            <tr>  
                                                                <td class="fieldLabel">Project :</td>                                                    
                                                                <td><s:textfield name="projectId" cssClass="inputTextBlue" value="%{projectName}"/></td>   
                                                                <td class="fieldLabel">SubProject :</td>                                                    
                                                                <td><s:textfield name="subProjectId" cssClass="inputTextBlue" value="%{subProjectName}"/></td> 
                                                            </tr>
                                                            
                                                            <tr>
                                                                <td class="fieldLabel">Issue Title :</td>                                                    
                                                                <td><s:textfield name="issueNames" cssClass="inputTextBlue" value="%{currentIssue.issueNames}" onchange="issueNamesValidate(document.frmAccountAdd.issueNames.value);" /></td>
                                                                <td class="fieldLabel">Status :</td>                                                    
                                                                <td><s:textfield name="issueStates" cssClass="inputTextBlue" value="%{currentIssue.issueStates}"  onchange="issueStatesValidate(document.frmAccountAdd.issueStates.value);"/></td> 
                                                            </tr>
                                                            
                                                            <tr>
                                                                <td class="fieldLabel">Issue Type :</td>                                                    
                                                                <td> <s:select list="issueTypeMaps" name="issueTypes" value="%{currentIssue.issueTypes}" cssClass="inputTextBlue"/></td>   
                                                                <td class="fieldLabel" align="left">Map:</td>
                                                                <td><s:textfield name="mapId" value="%{mapName}" cssClass="inputTextBlue"/></td>   
                                                            </tr>
                                                            
                                                            <tr>
                                                                <td class="fieldLabel">AssignedTo :</td>                                                    
                                                                <td><s:select list="employeesMaps" name="assignedToUIDs" cssClass="inputSelect" value="%{currentIssue.assignedToUIDs}" /></td> 
                                                                <td class="fieldLabel">Date Created :</td>                                                    
                                                                <td><sx:datetimepicker name="datesCreated" cssClass="inputDateTimePicker" value="%{currentIssue.datesCreatedOne}"/></td>
                                                            </tr>
                                                            
                                                            <tr>
                                                                <td class="fieldLabel">description :</td>                                                    
                                                                <td colspan="3"><s:textarea name="descriptions" cssClass="inputTextarea" rows="3" cols="73" value="%{currentIssue.descriptions}" onchange="descriptionsValidate(document.frmAccountAdd.descriptions.value);" /></td>
                                                            </tr>
                                                            
                                                        </table>
                                                        
                                                    </s:form>
                                                    
                                                </div>
                                                <%-- </sx:div> --%>
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
                                            
                                                                                    
                                            <!--//START TABBED PANNEL : --> 
                                            <%--   <s:tabbedPanel id="accountListPannel" theme="simple" cssStyle="width: 840px; height: 250px;padding-left:10px;padding-top:5px;" doLayout="true">
                                                
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
                                                                    if(request.getAttribute("currentIssueId") != null){
                                                                        currentIssueId = (String)request.getAttribute("currentIssueId");
                                                                    }
                                                                    try{%>
                                                
                                                
                                                <s:div id="attachmentListTab" label="Issue Attachments" >
                                                    
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
                                                    
                                                    strSortCol = "ObjectType";
                                                    
                                                    /* Getting DataSource using Service Locator */
                                                    
                                                    connection = ConnectionProvider.getInstance().getConnection();
                                                    
                                                    /* Sql query for retrieving resultset from DataBase */
                                                    queryString  =null;
                                                    
                                                    queryString ="Select Id,AttachmentType,DateOfUpload,UploadedBy,Description,ObjectType from tblPrjAttachments WHERE ObjectType ='Issue' ORDER BY DATEOFUPLOAD";
                                                    
                                                    String attachmentAction = "getAttachment.action";
                                                    
                                                    attachmentAction = attachmentAction+"?objectId="+request.getAttribute("currentAccountId")+"& objectType=Issue"+"&projectId="+currentProjectId+"&subProjectId="+currentSubProjectId+"&mapId="+currentMapId;
                                                    
                                                    attachmentAction = attachmentAction+"&issueId="+currentIssueId;
                                                    
                                                    %>
                                                    
                                                    <s:form action="" method="post" name="frmDBGrid">   
                                                        
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
                                                                    <grd:dbgrid id="tblAccountList" name="tblAccountList" width="100" pageSize="7" 
                                                                                currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                                dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable"
                                                                                trAttributes="onClick=SelectCurrentRow('{Id}',2) ">
                                                                        
                                                                        <grd:gridpager imgFirst="../includes/images/DBGrid/First.gif" imgPrevious="../includes/images/DBGrid/Previous.gif" 
                                                                                       imgNext="../includes/images/DBGrid/Next.gif" imgLast="../includes/images/DBGrid/Last.gif"
                                                                                       addImage="../includes/images/DBGrid/Add.png" addAction="<%=attachmentAction%>"/>
                                                                        
                                                                        <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" 
                                                                                        imageAscending="../includes/images/DBGrid/ImgAsc.gif" 
                                                                                        imageDescending="../includes/images/DBGrid/ImgDesc.gif"/>     
                                                                        
                                                                        <grd:rownumcolumn headerText="SNo" width="4" HAlign="right"/>
                                                                        <grd:imagecolumn headerText="Download" width="4" HAlign="center" 
                                                                                         imageSrc="../includes/images/download_11x10.jpg"
                                                                                         linkUrl="getDownload.action?Id={Id}" imageBorder="0"
                                                                                         imageWidth="11" imageHeight="10" alterText="Download"></grd:imagecolumn>
                                                                        
                                                                        <grd:textcolumn dataField="AttachmentType"  headerText="Attachment Type"   width="30" sortable="true"/> 
                                                                        <grd:textcolumn dataField="ObjectType"  headerText="Object Type" width="30"/>  
                                                                        <grd:datecolumn dataField="DateOfUpload"  headerText="Date Uploaded" dataFormat="MM-dd-yyyy HH:mm:SS" width="20"/>
                                                                        
                                                                    </grd:dbgrid> 
                                                                    
                                                                    <input type="hidden" name="txtCurr" value="<%=intCurr%>">
                                                                    <input type="hidden" id="txtSortCol" name="txtSortCol" value="<%=strSortCol%>">
                                                                    <input type="hidden" id="txtSortAsc" name="txtSortAsc" value="<%=strSortOrd%>">                                                                 
                                                                    <input type="hidden" name="submitFrom" value="dbGrid">
                                                                    
                                                                    
                                                                </td>
                                                            </tr>
                                                            
                                                        </table>                                
                                                        
                                                    </s:form>
                                                    
                                                </s:div>
                                                <%
                                                                    }catch(Exception ex){
                                                                        out.println(ex.toString());
                                                                    }finally{
                                                                        if(connection!= null){
                                                                            connection.close();
                                                                        }
                                                                    }
                                                %>
                                                
                                            </s:tabbedPanel>  --%>
                                            <!--//END TABBED PANNEL : -->
                                            
                                            
                                            <ul id="attachmentListPannel" class="shadetabs">
                                            <li ><a href="#" class="selected" rel="attachmentListTab">Issue Attachments</a></li>     
                                            </ul>
                                            
                                            <!--//START TABBED PANNEL : --> 
                                            <%-- <sx:tabbedpanel id="attachmentListPannel" cssStyle="width: 840px; height: 250px;padding-left:10px;padding-top:5px;" doLayout="true"> --%>
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
                                                if(request.getAttribute("currentIssueId") != null){
                                                currentIssueId = (String)request.getAttribute("currentIssueId");
                                                }
                                                
                                                
                                                try{%>                       
                                                
                                                <!--//START TAB : -->
                                                <%-- <sx:div id="attachmentListTab" label="Issue Attachments"> --%>   
                                                <div id="attachmentListTab" class="tabcontent">
                                                    
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
                                                    
                                                    strSortCol = "ObjectType";
                                                    
                                                    /* Getting DataSource using Service Locator */
                                                    
                                                    connection = ConnectionProvider.getInstance().getConnection();
                                                    
                                                    /* Sql query for retrieving resultset from DataBase */
                                                    queryString  =null;
                                                    
                                                    queryString ="Select Id,AttachmentType,DateOfUpload,UploadedBy,Description,ObjectType from tblPrjAttachments WHERE ObjectType ='Issue' ORDER BY DATEOFUPLOAD";
                                                    String attachmentAction = "getAttachment.action";
                                                    
                                                    attachmentAction = attachmentAction+"?objectId="+request.getAttribute("currentAccountId")+"& objectType=Issue"+"&projectId="+currentProjectId+"&subProjectId="+currentSubProjectId+"&mapId="+currentMapId;
                                                    
                                                    attachmentAction = attachmentAction+"&issueId="+currentIssueId;
                                                    
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
                                                            
                                                            <tr>
                                                                <td width="100%">
                                                                    <grd:dbgrid id="tblAccountList" name="tblAccountList" width="100" pageSize="5" 
                                                                                currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                                dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable"
                                                                                trAttributes="onClick=SelectCurrentRow('{Id}',2) ">
                                                                        
                                                                        <grd:gridpager imgFirst="../includes/images/DBGrid/First.gif" imgPrevious="../includes/images/DBGrid/Previous.gif" 
                                                                                       imgNext="../includes/images/DBGrid/Next.gif" imgLast="../includes/images/DBGrid/Last.gif"
                                                                                       addImage="../includes/images/DBGrid/Add.png" addAction="<%=attachmentAction%>"/>
                                                                        
                                                                        <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" 
                                                                                        imageAscending="../includes/images/DBGrid/ImgAsc.gif" 
                                                                                        imageDescending="../includes/images/DBGrid/ImgDesc.gif"/>    
                                                                        
                                                                        <grd:rownumcolumn headerText="SNo" width="4" HAlign="right"/>
                                                                        
                                                                        <grd:imagecolumn  headerText="Download"  width="4"  HAlign="center" 
                                                                                          imageSrc="../includes/images/download_11x10.jpg"
                                                                                          linkUrl="getDownload.action?Id={Id}" imageBorder="0"
                                                                                          imageWidth="11" imageHeight="10" alterText="Download"></grd:imagecolumn>
                                                                        
                                                                        <grd:textcolumn dataField="AttachmentType"  headerText="Attachment Type"   width="30" sortable="true"/> 
                                                                        <grd:textcolumn dataField="ObjectType"  headerText="Object Type" width="30"/>  
                                                                        <grd:datecolumn dataField="DateOfUpload"  headerText="Date Uploaded" dataFormat="MM-dd-yyyy HH:mm:SS" width="20"/>
                                                                        
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
                                                    
                                                </div>
                                                <%-- </sx:div> --%>
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
                                            <%-- </sx:tabbedpanel>  --%>                 
                                            
                                            <script type="text/javascript">

var countries=new ddtabcontent("attachmentListPannel")
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
