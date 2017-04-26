<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="com.freeware.gridtag.*" %> 
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants" %>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd" %>
<%--<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>--%>


<html>
    <head>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hubble Organization Portal :: Bridge Schedule List</title>
        <sx:head cache="true"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <%--<link rel="stylesheet" href="<s:url value="/includes/css/displaytag.css"/>">--%>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script> 
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        
        <s:include value="/includes/template/headerScript.html"/>
        
    </head>
    <body class="bodyGeneral">
        <%!
        /* Declarations */
        Connection connection;
        String queryString;
        String currentAccountId;
        String strTmp;
        String userId;
        String strSortCol;
        String strSortOrd = "ASC";
        String submittedFrom;
        String accountSearchBy;
        boolean blnSortAsc = true;
        String viewType;
        //int intSortOrd = 0;
        int intCurr = 1;
        
        %>
        <!--//START MAIN TABLE : Table for template Structure-->
        <table class="innerTable1000x515" align="center" cellpadding="0" cellspacing="0" border="0">
            
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
                    <table class="innerTable1000x515" cellpadding="0" cellspacing="0" border="1">
                        <tr>
                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                            <td width="150px;" class="leftMenuBgColor" valign="top"> 
                                <s:include value="/includes/template/LeftMenu.jsp"/> 
                            </td>
                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                            
                            <!--//START DATA COLUMN : Coloumn for Screen Content-->
                            <td width="850px" class="cellBorder" valign="top">
                                
                                
                                <!--//START TABBED PANNEL : --> 
                                <sx:tabbedpanel id="listOfBridges" cssStyle="width: 840px; height: 500px; padding-left:10px;padding-top:5px;" doLayout="true">
                                    
                                    <!--//START TAB : -->
                                    <sx:div id="bridges" label="List Of Bridges" theme="ajax">
                                        
                                        <%    
                                       try{ 
                                        /* String Variable for storing current position of records in dbgrid*/
                                        strTmp 	= request.getParameter("txtCurr");
                                        intCurr = 1;
                                        if (strTmp != null){
                                            intCurr = Integer.parseInt(strTmp);
                                        }
                                        
                                        /* Specifing Sorting Column */
                                        strSortCol = request.getParameter("Colname");
                                        if (strSortCol == null){
                                            strSortCol = "TitleOfUsage";
                                        }
                                        
                                        // this column for sorting resultSet
                                        strSortOrd = request.getParameter("txtSortAsc");
                                        if (strSortOrd == null){
                                            strSortOrd = "ASC";
                                        }
                                        
                                        Connection connection = ConnectionProvider.getInstance().getConnection();
                                        
                                        /* Sql query for retrieving resultset from DataBase */
                                        queryString = "select * from tblbridgeshedule ORDER BY starttime DESC";
                                        
                                        String AddSchedule = "../../employee/bridgeconference/bridge.action?currentAction=AddBridgeSchedule.action";
                                        
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
                                                        <grd:dbgrid id="tblbridgeshedule" name="tblbridgeshedule" width="100" pageSize="10"
                                                                    currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2"
                                                                    dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable"
                                                                    >
                                                            
                                                            
                                                            <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                           imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                           addImage="../../includes/images/DBGrid/Add.png" addAction="<%=AddSchedule%>"/>                            
                                                            <grd:rownumcolumn headerText="SNo" width="2" HAlign="left"/>
                                                            <grd:imagecolumn headerText="Edit" width="2"  HAlign="center" imageSrc="../../includes/images/DBGrid/Edit.gif"
                                                                             linkUrl="getBridgeConference.action?bridgeId={bridgeid}&currentAction=EditBridgeSchedule" imageBorder="0" imageWidth="15" imageHeight="15" alterText="Click to edit"/>
                                                            <grd:textcolumn dataField="bridgenumber" headerText="BridgeNo" width="15" sortable="true"/>
                                                            <grd:textcolumn dataField="date" headerText="Date" width="10" sortable="true" dataFormat="MM/dd/yyyy"/>
                                                            <grd:datecolumn dataField="starttime" headerText="StartTime" width="15" sortable="true" dataFormat="MM/dd/yyyy HH:mm:ss"/>
                                                            <grd:datecolumn dataField="endtime" headerText="EndTime" width="15" sortable="true" dataFormat="MM/dd/yyyy HH:mm:ss" />
                                                            <grd:textcolumn dataField="engagedby" headerText="EngagedBy" width="15"/>
                                                            <grd:textcolumn dataField="title" headerText="Title" sortable="true" width="10"/>
                                                            <grd:textcolumn dataField="status" headerText="Status"  sortable="true" width="8"/>
                                                            <grd:imagecolumn  headerText="Delete" width="8"  HAlign="center"  imageSrc="../../includes/images/DBGrid/Delete.png" 
                                                                              linkUrl="delete.action?bridgeId={bridgeid}" imageBorder="0" imageWidth="50" imageHeight="20" alterText="Click to delete" />
                                                            
                                                        </grd:dbgrid>
                                                        
                                                        
                                                        
                                                        
                                                        <!-- these components are DBGrid Specific  -->
                                                        <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                        <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                        <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                                                        <input id="txtDBId" TYPE="hidden" NAME="txtDBId" VALUE="0">
                                                        <input id="txtTRId"TYPE="hidden" NAME="txtTRId" VALUE="1">  
                                                    </td>
                                                </tr>
                                            </table>
                                        </form>
                                        <%
                                        }catch(Exception ex){
                                            out.println(ex.toString());
                                        }finally{
                                            if(connection!= null){
                                                connection.close();
                                                connection = null;
                                            }
                                        }
                                        %>
                                        
                                        
                                    </sx:div>
                                    <!--//END TAB : -->
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
                <td align="center"><s:include value="/includes/template/Footer.jsp"/>   </td>
            </tr>
            <!--//END FOOTER : Record for Footer Background and Content-->
            
        </table>
        <!--//END MAIN TABLE : Table for template Structure-->
    </body>
</html>

