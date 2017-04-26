<!-- 
 *******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  November 20, 2007, 3:25 PM
 *
 * Author  : Rajasekhar Yenduva<ryenduva@miraclesoft.com>
 *
 * File    : Tasks.jsp
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
-->
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%-- <%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<%@ page import="com.freeware.gridtag.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd"%>
<%@ page import="com.mss.mirage.util.DataSourceDataProvider"%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hubble Organization Portal :: My Project Tasks</title>
         <%-- <sx:head cache="false"/> --%>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tooltip.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tooltip.js"/>"></script>   
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>   
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>   
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <s:include value="/includes/template/headerScript.html"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>        
    </head>
    <body class="bodyGeneral" oncontextmenu="return false;">
        <%!
        /* Declarations */
        Connection connection;
        String queryString;
        String strTmp;
        String strSortCol;
        String strSortOrd;
        private DataSourceDataProvider dataSourceDataProvider;
        
        int intSortOrd = 0;
        int intCurr;
        boolean blnSortAsc = true;
        %>
        <!--//START MAIN TABLE : Table for template Structure-->
        <table class="templateTableLogin" align="center" cellpadding="0" cellspacing="0">
            
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
                    <table class="innerTableLogin" cellpadding="0" cellspacing="0">
                        <tr>
                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                            <td width="150px;" class="leftMenuBgColor" valign="top"> 
                                <s:include value="/includes/template/LeftMenu.jsp"/> 
                            </td>
                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                            
                            <!--//START DATA COLUMN : Coloumn for Screen Content-->
                            <td width="850px" class="cellBorder" valign="top">
                                <table cellpadding="0" cellspacing="0" width="100%">
                                    <%--<tr>
                                        <td>
                                            <span class="fieldLabel">Account Name :</span>&nbsp;
                                            <a class="navigationText" href="<s:url action="../accounts/getAccount"><s:param name="id" value="%{accountId}"/></s:url>"><s:property value="#session.accountName"/></a>
                                            <s:if test="contactId !=0">
                                                &nbsp;&nbsp;<span class="fieldLabel">Contact Name :</span>&nbsp;
                                                <a class="navigationText" href="<s:url action="../contacts/getContact"><s:param name="id" value="%{contactId}"/></s:url>"><s:property value="#session.contactName"/></a>
                                            </s:if>
                                        </td>
                                    </tr>--%>
                                    <tr>
                                        <td valign="top" style="padding-left:10px;padding-top:5px;">
                                            
                                            <ul id="IssuesPanel" class="shadetabs">
                                                <li ><a href="#" class="selected" rel="issuesTab">Project Tasks</a></li>     
                                            </ul>
                                            
                                            <!--//START TABBED PANNEL : -->
                                            <%-- <sx:tabbedpanel id="IssuesPanel" cssStyle="width: 845px; height: 500px;padding:5px 5px 5px 5px;" doLayout="true"> --%>
                                            <div  style="border:1px solid gray; width:840px; height: 500px; overflow:auto; margin-bottom: 1em;">                                            
                                                
                                                <!--//START TAB : -->
                                                <%-- <sx:div id="issuesTab" label="Project Tasks"> --%>
                                                <div id="issuesTab" class="tabcontent">
                                                    
                                                    <%
                                                    
                                                    try{                                                        
                                                    /* Getting DataSource using Service Locator */
                                                    connection = ConnectionProvider.getInstance().getConnection();
                                                    
                                                    intCurr = 1;
                                                    
                                                    /* String Variable for storing current position of records in dbgrid*/
                                                    strTmp = request.getParameter("txtCurr");
                                                    if (strTmp != null)
                                                    intCurr = Integer.parseInt(strTmp);
                                                    
                                                    /* Specifing Shorting Column */
                                                    strSortCol = request.getParameter("txtSortCol");
                                                    
                                                    if (strSortCol == null) strSortCol = "DateCreated";
                                                    
                                                    strSortOrd = request.getParameter("txtSortAsc");
                                                    if (strSortOrd == null) strSortOrd = "ASC";
                                                    
                                                    //String username =(String)session.getAttribute("userId");
                                                    
                                                    String username = session.getAttribute(ApplicationConstants.SESSION_CUST_USER_ID).toString();
                                                    
                                                    dataSourceDataProvider = DataSourceDataProvider.getInstance();
                                                    String department =  dataSourceDataProvider.getDepartmentName(username);
                                                    //out.print("Depattment"+department);
                                                    String  assingedName =  dataSourceDataProvider.getFname_Lname(username);
                                                    
                                                    
                                                    queryString = "SELECT  Id,ProjectName,IssueType,DateCreated,CreatedBy,Status,Description from tblPrjIssues";
                                                    queryString = queryString+ " WHERE  (AssignedTo='" +  assingedName +"'" + " or assignedTo is NULL)";
                                                    queryString = queryString+ " and Status <> 'Closed'";
                                                    
                                                    /* if(department.equalsIgnoreCase("OPERATIONS")) {
                                                    queryString = queryString+ " and  Category in ('HR', 'Office Supplies')";
                                                    }*/
                                                    
                                                    
                                                    //out.println(queryString);
                                                    // queryString = queryString + " WHERE objectType ='ContactActivity' ORDER BY DateUploaded"; tempory
                                                    
                                                    //String attachmentAction = "../../projects/issues/issue.action";
                                                    
                                                    %>
                                                    <form action="" name="frmDBGrid"> 
                                                        <table cellpadding="0" cellspacing="0" width="100%">
                                                            <tr>
                                                                <td class="headerText">
                                                                    <s:property value="#request.resultMessage || #session.resultMessage"/>
                                                                    <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td>
                                                                    <!-- DataGrid for list all Attachments -->
                                                                    <grd:dbgrid id="tblCrmAttachments" name="tblCrmAttachments" width="100" pageSize="15" 
                                                                                currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                                dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                        
                                                                        <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                                       imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                                       addImage="../../includes/images/DBGrid/Add.png" />
                                                                        <%--addAction="<%=attachmentAction%>"/>--%>
                                                                        <grd:rownumcolumn headerText="SNo" width="2" HAlign="center"/>
                                                                        <grd:imagecolumn headerText="Edit" width="4" HAlign="center" imageSrc="../../includes/images/DBGrid/Edit.gif"
                                                                                         linkUrl="getIssue.action?issueId={Id}&accessType=Task" imageBorder="0"
                                                                                         imageWidth="16" imageHeight="16" alterText="Click to edit"></grd:imagecolumn>
                                                                        
                                                                        <grd:textcolumn dataField="ProjectName"  headerText="Project Name"   width="14" sortable="true"/>                  
                                                                        <grd:textcolumn dataField="CreatedBy"  headerText="CreatedBy"   width="10" sortable="true"/> 
                                                                        <grd:textcolumn dataField="IssueType"  headerText="IssueType" width="10" sortable="true"/>  
                                                                        <grd:textcolumn dataField="Status"  headerText="Status" width="10" sortable="true"/>  
                                                                        <grd:datecolumn dataField="DateCreated"  headerText="DateCreated"  dataFormat="MM-dd-yyyy HH:mm:SS" width="18" sortable="true"/>
                                                                        <grd:textcolumn dataField="Description"  headerText="Description" width="40" sortable="true"/>  
                                                                        
                                                                    </grd:dbgrid>
                                                                    <!-- these components are DBGrid Specific  -->
                                                                    <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                                    <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                                    <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                                                                    <input id="txtDBId" TYPE="hidden" NAME="txtDBId" VALUE="0"/>
                                                                    
                                                                    <!-- Used for Storing the Current Row No. in the Table -->
                                                                    <input id="txtTRId"TYPE="hidden" NAME="txtTRId" VALUE="1"/>
                                                                </td>
                                                            </tr>
                                                        </table>    
                                                    </form>  
                                                    <%
                                                    connection.close();
                                                    connection = null;
                                                    }catch(Exception se){
                                                    System.out.println("Exception in Tasks "+se);
                                                    }finally{
                                                    if(connection!= null){
                                                    connection.close();
                                                    connection = null;
                                                    }
                                                    if(session!=null){
                                                    session.removeAttribute("resultMessage");
                                                    }
                                                    }
                                                    %>
                                                    
                                                </div>
                                                <%-- </sx:div> --%>
                                                <!--//END TAB : -->
                                            </div>   
                                            <%-- </sx:tabbedpanel> --%>
                                            <!--//END TABBED PANNEL : -->
                                            <!--//END TAB : -->
                                     
                                            <script type="text/javascript">

var countries=new ddtabcontent("IssuesPanel")
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
                <td align="center"><s:include value="/includes/template/Footer.jsp"/>   </td>
            </tr>
            <!--//END FOOTER : Record for Footer Background and Content-->
            
        </table>
        <!--//END MAIN TABLE : Table for template Structure-->
        
        
        
        
        
    </body>
</html>
