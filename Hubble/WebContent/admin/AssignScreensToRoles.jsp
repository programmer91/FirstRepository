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
        
        <title>Hubble Organization Portal :: Account Adding</title>
            <sx:head cache="true"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        
    </head>
    <body class="bodyGeneral" oncontextmenu="return false;">
        
        <%!
        /* Declarations */
        Connection connection;
        String queryString;
        String currentAccountId;
        String strTmp;
        String strSortCol;
        String strSortOrd;
        int intSortOrd = 0;
        int intCurr;
        boolean blnSortAsc = true;
        %>
        
        <!--//START MAIN TABLE : Table for template Structure-->
        <table class="templateTable" align="center" cellpadding="0" cellspacing="0">
            
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
                    <table class="innerTable" cellpadding="0" cellspacing="0">
                        <tr>
                            
                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                            <td width="150px;" class="leftMenuBgColor" valign="top">
                                <s:include value="/includes/template/LeftMenu.jsp"/>
                                
                            </td>
                            <!--//END DATA COLUMN : Coloumn for LeftMenu-->
                            
                            <!--//START DATA COLUMN : Coloumn for Screen Content-->
                            <td width="850px" class="cellBorder" valign="top">
                                
                                
                                <!--//START TABBED PANNEL : --> 
                                <sx:tabbedpanel id="accountListPannel" cssStyle="width: 840px; height: 650px;padding-left:10px;padding-top:5px;" doLayout="true" >
                                    
                                    <!--//START TAB : -->
                                    
                                    <!--//END TAB : -->
                                    
                                    
                                    <sx:div id="rolesListTab" label="Avilable Roles" theme="ajax" >
                                        
                                        <%
                                        /* String Variable for storing current position of records in dbgrid*/
                                        strTmp = request.getParameter("txtCurr");
                                        
                                        intCurr = 1;
                                        
                                        if (strTmp != null)
                                        intCurr = Integer.parseInt(strTmp);
                                        
                                        /* Specifing Shorting Column */
                                        strSortCol = request.getParameter("Colname");
                                        
                                        if (strSortCol == null) strSortCol = "AccountName";
                                        
                                        strSortOrd = request.getParameter("txtSortAsc");
                                        if (strSortOrd == null) strSortOrd = "ASC";
                                        
                                        try{
                                        
                                        /* Getting DataSource using Service Locator */
                                        
                                        connection = ConnectionProvider.getInstance().getConnection();
                                        
                                        /* Sql query for retrieving resultset from DataBase */
                                        queryString  =null;
                                        queryString = "SELECT Id ,description from tblLKRoles";
                                       // out.print("queryString"+queryString);
                                        
                                        %>
                                        <s:form action="" theme="simple" method="post" name="frmDBGrid">   
                                            <table cellpadding="1" cellspacing="1" width="100%" border="0">
                                                
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
                                                    <td align="center">
                                                        
                                                        <grd:dbgrid id="tblStat" name="tblStat" width="98" pageSize="20"
                                                                    currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2"
                                                                    dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                            
                                                            <grd:gridpager imgFirst="../includes/images/DBGrid/First.gif" imgPrevious="../includes/images/DBGrid/Previous.gif" 
                                                                           imgNext="../includes/images/DBGrid/Next.gif" imgLast="../includes/images/DBGrid/Last.gif"
                                                            />
                                                            
                                                            
                                                            
                                                            <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" 
                                                                            imageAscending="../includes/images/DBGrid/ImgAsc.gif" 
                                                                            imageDescending="../includes/images/DBGrid/ImgDesc.gif"/>    
                                                            
                                                            
                                                            
                                                            <grd:imagecolumn headerText="Edit" width="4" HAlign="center" imageSrc="../includes/images/DBGrid/newEdit_17x18.png"
                                                                             linkUrl="getAssingnRoleScreen.action?roleId={Id}" imageBorder="0"
                                                                             imageWidth="16" imageHeight="16" alterText="Click to edit"></grd:imagecolumn>
                                                            
                                                            
                                                            
                                                            <grd:numbercolumn dataField="Id" headerText="RoleId" width="15" dataFormat="0" />
                                                            <grd:textcolumn dataField="description" headerText="RoleName" width="12" sortable="true" />
                                                            
                                                            
                                                        </grd:dbgrid>
                                                        
                                                        <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                        <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                        <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                                                        <input id="txtDBId" TYPE="hidden" NAME="txtDBId" VALUE="0">
                                                        <input id="txtTRId" TYPE="hidden" NAME="txtTRId" VALUE="1"> 
                                                        
                                                    </td>
                                                </tr>
                                            </table>                                
                                            
                                        </s:form>
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
                                    </sx:div>
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