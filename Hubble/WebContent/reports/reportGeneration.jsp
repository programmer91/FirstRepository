<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="com.freeware.gridtag.*" %> 
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd" %>

<html>
    <head>
        <title>Hubble Organization Portal :: Employee Availability Report </title>
         <sx:head cache="true"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link REL="StyleSheet" HREF="<s:url value="/includes/css/GridStyle.css"/>" type="text/css">
        <link REL="StyleSheet" HREF="<s:url value="/includes/css/leftMenu.css"/>" type="text/css">
        <script language="javascript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <s:include value="/includes/template/headerScript.html"/>
    </head>
    <body class="bodyGeneral" oncontextmenu="return false;">
        
        <%!
        /* Declarations */
        Connection connection;
        
        String queryString;
        String userId;
        String strTmp;
        String strSortCol;
        String strSortOrd;
        
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
                    <table class="innerTable1000x515" cellpadding="0" cellspacing="0" border="0">
                        <tr>
                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                            <td width="150px;" class="leftMenuBgColor" valign="top">  
                                <s:include value="/includes/template/LeftMenu.jsp"/>  
                            </td>
                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                            
                            <!--//START DATA COLUMN : Coloumn for Screen Content-->
                            <td width="850px" class="cellBorder" valign="top">
                                <!--//START TABBED PANNEL : -->
                                <sx:tabbedpanel id="reportSelectionPannel" cssStyle="width: 840px; height: 150px;padding-left:10px;padding-top:5px;" doLayout="true">
                                    <!--//START TAB : -->
                                    <sx:div id="reportSelection" label="Employee Availability Report "  >
                                        <s:form action="generateAvailabilityReport" method="POST" theme="simple">
                                            <!-- for displaying action errors and field errors -->
                                            <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                <tr>
                                                    <td class="headerText" align="right">
                                                        <% if(request.getAttribute(ApplicationConstants.RESULT_MSG) != null){
                                                            out.println(request.getAttribute(ApplicationConstants.RESULT_MSG));
                                                        }%>
                                                        <s:hidden name="submitFrom" value="reportGeneration"/>
                                                        <s:submit value="Generate Report" cssClass="buttonBg"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <table width="400px" cellpadding="2" cellspacing="2" border="0">
                                                            
                                                            <tr>
                                                                <td class="fieldLabel" width="200px" align="right">Organization :</td>                                                                
                                                                <td colspan="3"><s:select label="Select Organization" 
                                                                                              name="organization" 
                                                                                          list="organizationList" cssClass="inputSelectEmpUpdate"/></td>                                                                
                                                            </tr>
                                                            
                                                            <tr>
                                                                <td class="fieldLabel" width="200px" align="right">Current State:</td>                                                                                           
                                                                <td><s:select name="empState" 
                                                                              list="empCurrentStatus" cssClass="inputSelect"/></td>                                                                                                                      
                                                                <td class="fieldLabel" width="200px" align="right">Report Type:</td>                                                                                           
                                                                <td><s:select name="empReportType" 
                                                                              list="empReportTypeList" cssClass="inputSelect"/></td>                                                                                                                      
                                                            </tr>
                                                        </table>            
                                                    </td>
                                                </tr>
                                            </table>
                                            
                                            
                                        </s:form>
                                        
                                    </sx:div >
                                    <!--//END TAB : -->
                                    
                                </sx:tabbedpanel>
                                <!--//END TABBED PANNEL : -->
                                
                                <!--//START TABBED PANNEL : -->
                                <sx:tabbedpanel id="reportListPannel" cssStyle="width: 840px; height: 350px;padding-left:10px;padding-top:5px;" doLayout="true">
                                    <!--//START TAB : -->
                                    <sx:div id="reportTab" label="Reports List"  >
                                        <%
                                        /* String Variable for storing current position of records in dbgrid*/
                                        strTmp = request.getParameter("txtCurr");
                                        
                                        intCurr = 1;
                                        
                                        if (strTmp != null)
                                            intCurr = Integer.parseInt(strTmp);
                                        
                                        /* Specifing Shorting Column */
                                        strSortCol = request.getParameter("txtSortCol");
                                        
                                        if (strSortCol == null) strSortCol = "CreatedDate";
                                        
                                        strSortOrd = request.getParameter("txtSortAsc");
                                        if (strSortOrd == null) strSortOrd = "DESC";
                                        
                                        userId = session.getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                                        
                                        try{
                                            
                                            /* Getting DataSource using Service Locator */
                                            connection=ConnectionProvider.getInstance().getConnection();
                                            
                                            /* Sql query for retrieving resultset from DataBase */
                                            queryString  =null;
                                            String  queryString="Select Id,LoginId,ReportType,ReportName,CreatedDate,Description from tblReports WHERE LoginId='"+userId+"' ORDER BY CreatedDate DESC";
                                        %>
                                        
                                        
                                        <form method="post" id="frmDBGrid" name="frmDBGrid" action=""> 
                                            <table cellpadding="0" cellspacing="0" width="100%">
                                                <tr>
                                                    <td class="headerText">
                                                        <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <table cellpadding="0" cellspacing="0" width="95%" align="center">
                                                            <tr>
                                                                <td>
                                                                    <!-- DataGrid for list all activities -->
                                                                    <grd:dbgrid id="tblEmpInsurance" name="tblEmpInsurance" width="100" pageSize="8" 
                                                                                currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                                dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                        
                                                                        
                                                                        <grd:gridpager imgFirst="../includes/images/DBGrid/First.gif" imgPrevious="../includes/images/DBGrid/Previous.gif" 
                                                                                       imgNext="../includes/images/DBGrid/Next.gif" imgLast="../includes/images/DBGrid/Last.gif"/>
                                                                        <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" />
                                                                        
                                                                        
                                                                        
                                                                        
                                                                        <grd:gridpager imgFirst="../includes/images/DBGrid/First.gif" imgPrevious="../includes/images/DBGrid/Previous.gif" 
                                                                                       imgNext="../includes/images/DBGrid/Next.gif" imgLast="../includes/images/DBGrid/Last.gif"/>
                                                                        
                                                                        <grd:textcolumn dataField="Id"       headerText="Id" width="5" sortable="true" dataFormat="" HAlign="center"/>
                                                                        <grd:textcolumn dataField="Description"  headerText="Report Type"   width="18" sortable="true" HAlign="center"/>
                                                                        <grd:textcolumn dataField="ReportName"  headerText="ReportName"   width="60" sortable="true" HAlign="center"/>
                                                                        <grd:imagecolumn headerText="Download" width="5" imageSrc="../includes/images/download_11x10.jpg" linkUrl="getAttachment.action?id={Id}"  HAlign="center" imageHeight="10" imageWidth="11" imageBorder="0"/> 
                                                                        <grd:imagecolumn headerText="Delete" width="12" imageSrc="../includes/images/DBGrid/Delete.png" linkUrl="deleteReports.action?id={Id}"  HAlign="center" imageHeight="20" imageWidth="51" imageBorder="0"/>   
                                                                    </grd:dbgrid>
                                                                    <!-- these components are DBGrid Specific  -->
                                                                    <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                                    <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                                    <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                                                                    <input TYPE="hidden" NAME="submitFrom" VALUE="dbGrid">
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </td>
                                                </tr>
                                            </table>
                                            
                                        </form>
                                        
                                        <%
                                        connection.close();
                                        connection = null;
                                        }catch(Exception ex){
                                            out.println(ex.toString());
                                        }finally{
                                            if(connection!= null){
                                                connection.close();
                                                connection = null;
                                                        
                                            }
                                        }
                                        %>
                                        
                                        
                                    </sx:div >
                                    <!--//END TAB : -->
                                    
                                </sx:tabbedpanel>
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
