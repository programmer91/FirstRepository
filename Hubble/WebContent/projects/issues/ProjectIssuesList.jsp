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
 * File    : IssuesList.jsp
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


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hubble Organization Portal :: Project Issues List</title>
         <%-- <sx:head cache="false"/> --%>
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
                                <s:include value="/includes/template/MenuCustomer.jsp"/> 
                            </td>
                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                            
                            <!--//START DATA COLUMN : Coloumn for Screen Content-->
                            <td width="850px" class="cellBorder" valign="top" style="padding-left:10px;padding-top:5px;">
         
                                    <ul id="IssuesPanel" class="shadetabs">
                                        <li ><a href="#" class="selected" rel="issuesTab">Issues List</a></li>  
                                        <li ><a href="#" rel="IssuesSearchTab">Issues Search</a></li>      
                                    </ul>
                                    
                                <!--//START TABBED PANNEL : -->
                                <%-- <sx:tabbedpanel id="IssuesPanel" cssStyle="width: 845px; height: 500px;padding:5px 5px;" doLayout="true"> --%>
                                <div  style="border:1px solid gray; width:840px; height: 500px; overflow:auto; margin-bottom: 1em;">                                    
                                    
                                    <!--//START TAB : -->
                                    <%-- <sx:div id="issuesTab" label="IssuesList"> --%>
                                    <div id="issuesTab" class="tabcontent">
                                        <%
                                        
                                        try{
                                        queryString="";
                                        /* Getting DataSource using Service Locator */
                                        connection = ConnectionProvider.getInstance().getConnection();
                                        
                                        
                                        /* String Variable for storing current position of records in dbgrid*/
                                        strTmp = request.getParameter("txtCurr");
                                        intCurr = 1;
                                        if (strTmp != null)
                                        intCurr = Integer.parseInt(strTmp);
                                        
                                        /* Specifing Shorting Column */
                                        strSortCol = request.getParameter("txtSortCol");
                                        
                                        if (strSortCol == null) strSortCol = "DateCreated";
                                        
                                        strSortOrd = request.getParameter("txtSortAsc");
                                        if (strSortOrd == null) strSortOrd = "ASC";
                                        
                                        queryString = session.getAttribute(ApplicationConstants.PROJ_QS_ISSUES_LIST).toString();
                                        /* Sql query for retrieving resultset from DataBase */
                                        //out.println(queryString);
                                        
                                        
                                        String issueAction = "../../projects/issues/issue.action?accessType=Issue";
                                        
                                        %>
                                        <s:form action="" name="frmDBGrid" theme="simple"> 
                                            <div style="width=1000px;">
                                                <table cellpadding="0" border="0" cellspacing="0" width="100%">
                                                    <tr>
                                                        <td class="headerText">
                                                            <s:property value="#request.resultMessage || #session.resultMessage"/>
                                                            <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            
                                                            <s:if test="%{addBtnVisible == 'TeamIssues'}">
                                                                <!-- DataGrid for list all Attachments -->
                                                                <grd:dbgrid id="tblCrmAttachments" name="tblCrmAttachments" width="100" pageSize="15" 
                                                                            currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                            dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                    <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                                   imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                                   addImage="../../includes/images/DBGrid/Add.png"
                                                                    />
                                                                    <grd:rownumcolumn headerText="SNo" width="2" HAlign="center"/>
                                                                    <grd:imagecolumn headerText="Edit" width="4" HAlign="center" imageSrc="../../includes/images/DBGrid/Edit.gif"
                                                                                     linkUrl="getIssue.action?issueId={Id}&accessType=Issue" imageBorder="0"
                                                                                     imageWidth="16" imageHeight="16" alterText="Click to edit"></grd:imagecolumn>
                                                                    
                                                                    <grd:textcolumn dataField="CreatedBy"  headerText="CreatedBy"   width="10" sortable="true"/> 
                                                                    
                                                                    <grd:textcolumn dataField="Status"  headerText="Status" width="10" sortable="true"/>  
                                                                    <grd:datecolumn dataField="DateCreated"  headerText="DateCreated"  dataFormat="MM-dd-yyyy HH:mm:SS" width="15" sortable="true"/>
                                                                    <grd:textcolumn dataField="AssignedTo"  headerText="AssignedTo" width="12" sortable="true"/>  
                                                                    <grd:textcolumn dataField="Description"  headerText="Description" width="47" sortable="true"/>  
                                                                    
                                                                </grd:dbgrid>
                                                            </s:if>
                                                            
                                                            <s:else>
                                                                <!-- DataGrid for list all Attachments -->
                                                                <grd:dbgrid id="tblCrmAttachments" name="tblCrmAttachments" width="100" pageSize="15" 
                                                                            currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                            dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                    <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                                   imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                                   addImage="../../includes/images/DBGrid/Add.png"
                                                                                   addAction="<%=issueAction%>"/>
                                                                    <grd:rownumcolumn headerText="SNo" width="2" HAlign="center"/>
                                                                    <grd:imagecolumn headerText="Edit" width="4" HAlign="center" imageSrc="../../includes/images/DBGrid/Edit.gif"
                                                                                     linkUrl="getIssue.action?issueId={Id}&accessType=Issue" imageBorder="0"
                                                                                     imageWidth="16" imageHeight="16" alterText="Click to edit"></grd:imagecolumn>
                                                                    
                                                                    <grd:textcolumn dataField="CreatedBy"  headerText="CreatedBy"   width="10" sortable="true"/> 
                                                                    
                                                                    <grd:textcolumn dataField="Status"  headerText="Status" width="10" sortable="true"/>  
                                                                    <grd:datecolumn dataField="DateCreated"  headerText="DateCreated"  dataFormat="MM-dd-yyyy HH:mm:SS" width="15" sortable="true"/>
                                                                    <grd:textcolumn dataField="AssignedTo"  headerText="AssignedTo" width="12" sortable="true"/>  
                                                                    <grd:textcolumn dataField="Description"  headerText="Description" width="47" sortable="true"/>  
                                                                    
                                                                </grd:dbgrid>
                                                            </s:else>
                                                            
                                                            <!-- these components are DBGrid Specific  -->
                                                            <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                            <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                            <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                                                            
                                                        </td>
                                                    </tr>
                                                </table>    
                                            </div>
                                        </s:form>  
                                        <%
                                        connection.close();
                                        connection = null;
                                        /* For Exception handling mechanism */
                                        }catch(Exception se){
                                        System.out.println("Exception in IssuesList "+se);
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
                                        <%-- </sx:div> --%>
                                        <!--//END TAB : -->
                                    </div>
                                    
                                    <%-- <sx:div id="IssuesSearchTab" label="Issues Search"> --%>
                                    <div id="IssuesSearchTab" class="tabcontent">    
                                        
                                        <s:form name="frmSearch" action="%{currentSearch}" theme="simple">
                                            
                                            <!--The components of searching issues -->
                                            <table border="0" cellpadding="1" cellspacing="1" width="100%">
                                                <tr>
                                                    <td class="headerText">
                                                        <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                    </td>
                                                </tr>
                                                <tr><td align="left"> <table>
                                                            <tr>
                                                                <td  class="fieldLabel">Status:</td>
                                                                <td><s:select name="status" list="{'Assigned','InProgress','Closed','Not Closed','Created'}"
                                                                                  name="statusId"
                                                                                  headerKey="" 
                                                                                  headerValue="--select--"
                                                                                  value="%{currentIssue.statusId}"
                                                                              cssClass="inputSelect"  /></td>
                                                                <td  class="fieldLabel">Start Date:</td>
                                                                <td><s:textfield name="startDate" cssClass="inputTextBlue"/><a href="javascript:cal1.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                     width="20" height="20" border="0"></a></td>
                                                                <td class="fieldLabel">End Date:</td>
                                                                <td><s:textfield name="endDate" cssClass="inputTextBlue"/><a href="javascript:cal2.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                     width="20" height="20" border="0"></a></td>
                                                                <td>
                                                                    <s:hidden name="submitFrom" value="Search"/>
                                                                    <s:submit cssClass="buttonBg" value="Search"/>
                                                                </td> 
                                                            </tr>
                                                        </table>  
                                                    </td>
                                                </tr> 
                                            </table>
                                            
                                        </s:form>
                                        <!-- this script for After loading Form it will instantiate Calender Objects as you require -->
                                        <script type="text/JavaScript">
                                                           var cal1 = new CalendarTime(document.forms['frmSearch'].elements['startDate']);
                                                           cal1.year_scroll = true;
                                                           cal1.time_comp = false;
                                            
                                                           var cal2 = new CalendarTime(document.forms['frmSearch'].elements['endDate']);
                                                           cal2.year_scroll = true;
                                                           cal2.time_comp = false;
                                        </script>
                                    </div>    
                                    <%-- </sx:div> --%>                                    
                                    <!--//END TAB : -->
                                </div>    
                                <%-- </sx:tabbedpanel> --%>
                                <!--//END TABBED PANNEL : -->
                                
                                <script type="text/javascript">

var countries=new ddtabcontent("IssuesPanel")
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
            <!--//END FOOTER : Record for Footer Background and Content-->
            
        </table>
        <!--//END MAIN TABLE : Table for template Structure-->
      
    </body>
</html>
