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
<%--<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>--%>


<html>
    <head>
        
        <title>Hubble Organization Portal :: List Of Projects</title>
         <sx:head cache="false"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <%--<link rel="stylesheet" href="<s:url value="/includes/css/displaytag.css"/>">--%>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
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
        String strSortCol;
        String strSortOrd = "ASC";
        String submittedFrom;
        boolean blnSortAsc = true;
        //int intSortOrd = 0;
        int intCurr = 1;
        
        %>
        
        <!--//START MAIN TABLE : Table for template Structure-->
        <table class="templateTableLogin" align="center" cellpadding="0" cellspacing="0">
            
            <!--//START HEADER : Record for Header Background and Mirage Logo-->
            <tr class="headerBg">
                <td valign="top">
                    <s:include value="/includes/template/CustHeader.jsp"/>                    
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
                                <s:include value="/includes/template/MenuCustomer.jsp"/>
                                
                            </td>
                            <!--//END DATA COLUMN : Coloumn for LeftMenu-->
                            
                            <!--//START DATA COLUMN : Coloumn for Screen Content-->
                            <td width="850px" class="cellBorder" valign="top">
                                
                                
                                <!--//START TABBED PANNEL : --> 
                                <sx:tabbedpanel id="List of Projects" cssStyle="width: 840px; height: 500px;padding-left:10px;padding-top:5px;" doLayout="true" >
                                    <%try{%> 
                                    <!--//START TAB : -->
                                    <sx:div id="projectsList" label="Projects"> 
                                        <%
                                        
                                        /* String Variable for storing current position of records in dbgrid*/
                                        strTmp = request.getParameter("txtProjectCurr");
                                        
                                        
                                        if (strTmp != null)
                                        intCurr = Integer.parseInt(strTmp);
                                        
                                        /* Specifing Shorting Column */
                                        strSortCol = request.getParameter("txtProjectSortCol");
                                        
                                        if (strSortCol == null) strSortCol = "ProjectStartDate";
                                        
                                        strSortOrd = request.getParameter("txtProjectSortAsc");
                                        if (strSortOrd == null) strSortOrd = "DESC";
                                        
                                        if(session.getAttribute(ApplicationConstants.SESSION_CUST_ACC_ID)!=null){
                                        currentAccountId = session.getAttribute(ApplicationConstants.SESSION_CUST_ACC_ID).toString();
                                        }
                                        
                                        
                                        connection = ConnectionProvider.getInstance().getConnection();
                                        /* Sql query for retrieving resultset from DataBase */
                                        queryString = "Select  Id,ProjectName,ProjectStartDate,ProjectEndDate,TotalResources,CustomerId,ProjectManagerUID  FROM tblProjects";
                                        queryString = queryString+" WHERE CustomerId="+currentAccountId+" ORDER BY ProjectStartDate DESC";
                                        // out.println(queryString);
                                        String projectAddAction = "../projects/project.action?accountId="+currentAccountId;
                                        
                                        %>
                                        <form action="" name="frmDBProjectGrid" method="post"> 
                                            <table cellpadding="0" cellspacing="0" width="100%">
                                                <tr>
                                                    <td class="headerText">
                                                        <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <!-- DataGrid for Projects -->
                                                        <grd:dbgrid id="tblProjects" name="tblProjects" width="100" pageSize="15" 
                                                                    currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                    dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                            
                                                            <grd:gridpager imgFirst="../includes/images/DBGrid/First.gif" imgPrevious="../includes/images/DBGrid/Previous.gif" 
                                                                           imgNext="../includes/images/DBGrid/Next.gif" imgLast="../includes/images/DBGrid/Last.gif"
                                                                           addImage="../includes/images/DBGrid/Add.png"   
                                                                           addAction="<%=projectAddAction%>"
                                                                           scriptFunction="getNextProjects"/>
                                                            <grd:rownumcolumn headerText="SNo" width="5" HAlign="right"/>
                                                            <grd:imagecolumn  headerText="Edit"  width="5"   
                                                                              linkUrl="../projects/getProject.action?Id={Id}&accountId={CustomerId}"  
                                                                              HAlign="center"
                                                                              imageSrc="../includes/images/DBGrid/newEdit_17x18.png"
                                                                              imageBorder="0" 
                                                                              imageWidth="16" 
                                                                              imageHeight="16" 
                                                                              alterText="Click to Edit"/>
                                                            <grd:datecolumn dataField="ProjectStartDate" headerText="Start Date" width="15" dataFormat="MM-dd-yyyy" />
                                                            <grd:datecolumn dataField="ProjectEndDate"  headerText="End Date" width="15" dataFormat="MM-dd-yyyy" />
                                                            <grd:textcolumn dataField="ProjectName"  headerText="Project Name" width="37"/>
                                                            <%-- <grd:textcolumn dataField="ProjectManagerUID" headerText="Manager UID" width="15" /> --%>
                                                            <grd:numbercolumn dataField="TotalResources"  headerText="No.Resources" width="8" dataFormat="0"/>
                                                            
                                                        </grd:dbgrid>
                                                        <!-- these components are DBGrid Specific  -->
                                                        <input TYPE="hidden" NAME="txtProjectCurr" VALUE="<%=intCurr%>">
                                                        <input TYPE="hidden" NAME="txtProjectSortCol" VALUE="<%=strSortCol%>">
                                                        <input TYPE="hidden" NAME="txtProjectSortAsc" VALUE="<%=strSortOrd%>">
                                                        
                                                        <input TYPE="hidden" NAME="submitFrom" VALUE="dbGrid">
                                                        <input type="hidden" name="id" value="<%=currentAccountId%>">
                                                    </td>
                                                </tr>
                                            </table>
                                        </form> 
                                    </sx:div>
                                    <!--//END TAB : -->
                                    
                                    <%
                                    connection.close();
                                    connection = null;
                                    }catch(Exception se){
                                    System.out.println("Exception in Customer Projects "+se);
                                    }finally{
                                    if(connection!= null){
                                    connection.close();
                                    connection = null;
                                    }
                                    }
                                    %>     
                                    
                                    
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