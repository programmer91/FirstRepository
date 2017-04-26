<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %> 
<%@ page import="com.freeware.gridtag.*" %> 
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd" %>
<%--
<s:if test="currentAccount!=null">
    <s:text name="item.create" id="currentAccountId">
        <s:param>
            <s:text name="id"/>
        </s:param>
    </s:text>
</s:if>
--%>
<html>
    <head>
        <title>Hubble Organization Portal :: ${title} Activities</title>
        <sx:head cache="true"/> 
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ajax/AjaxPopup.js"/>"></script> 
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGAccDetails.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
        <!-- Enable below option if AJAX usage To Retrieve Regions,Territoris  -->
        <%-- <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CrmAjax.js"/>"></script> --%>
        <!-- Disable below option if AJAX usage To Retrieve Regions,Territoris  -->
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/RegionTerritoryUtil.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CommonAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/StringUtility.js"/>"></script>
        <%--<script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AccountDetailsClientValidation.js"/>"></script>   
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/SoftwareClientValidation.js"/>"></script>   --%>
      
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CustomerPopup.js"/>"></script> 
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/StandardClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ajax/ajaxTabs.js"/>"></script>
    </head>
    <body class="bodyGeneral" oncontextmenu="return false;">
       <%!
/* Declarations */
Connection connection;

String accountPrimaryTeamMember;
String userId;
String userRoleName;
int isUserManager;

String queryString;
String currentAccountId;
String strTmp;
String strSortCol;
String strSortOrd;

int intSortOrd = 0;
int intCurr;
boolean blnSortAsc = true;
%>
 <%--  <table  class="templateTable1000x580" align="center" cellpadding="0" cellspacing="0">
            <tr>
                <td> --%>
 <% try{
        
        /* String Variable for storing current position of records in dbgrid*/
        strTmp = request.getParameter("txtProjectCurr");
        
        
        if (strTmp != null)
            intCurr = Integer.parseInt(strTmp);
        
        /* Specifing Shorting Column */
        strSortCol = request.getParameter("txtProjectSortCol");
        
        if (strSortCol == null) strSortCol = "ProjectStartDate";
        
        strSortOrd = request.getParameter("txtProjectSortAsc");
        if (strSortOrd == null) strSortOrd = "DESC";
        
         /* Getting DataSource using Service Locator */
            connection = ConnectionProvider.getInstance().getConnection();
            // out.print("con::"+connection); 
            
        currentAccountId=request.getParameter("currentAccountId");
        /* Sql query for retrieving resultset from DataBase */
        queryString = "Select  Id,ProjectName,ProjectStartDate,ProjectEndDate,TotalResources,CustomerId,ProjectManagerUID  FROM tblProjects";
        queryString = queryString+" WHERE CustomerId="+currentAccountId+" ORDER BY ProjectStartDate DESC";
       // out.println(queryString);
        String projectAddAction = "../../projects/project.action?accountId="+currentAccountId;
        
        %>
        
       
        <form action="" name="frmDBProjectGrid" method="post" id="frmDBProjectGrid"> 
            <table cellpadding="0" cellspacing="0" width="100%">
                
                <tr>
                    <td class="headerText"> <a href="<%=projectAddAction%>" style="align=left;">
                            <img alt="Add Requirement"
                                 src="<s:url value="/includes/images/add.gif"/>" 
                                 width="33px" 
                                 height="19px"
                             border="0" align="left"></a>&nbsp;&nbsp;
                        
                    </td>
                </tr>
                
                <tr>
                    <td>
                        <!-- DataGrid for Projects -->
                        <grd:dbgrid id="tblProjects" name="tblProjects" width="100" pageSize="7" 
                                    currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                    dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                            
                            <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                           imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                           addImage="../../includes/images/DBGrid/Add.png"   
                                           addAction="<%=projectAddAction%>"
                                           scriptFunction="getNextProjects"/>
                            <grd:rownumcolumn headerText="SNo" width="5" HAlign="right"/>
                            <grd:imagecolumn  headerText="Edit"  width="5"   
                                              linkUrl="../../projects/getProject.action?Id={Id}&accountId={CustomerId}"  
                                              HAlign="center"
                                              imageSrc="../../includes/images/DBGrid/newEdit_17x18.png"
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
                        <input type="hidden" name="accId" value="<%=currentAccountId%>">
                        
                        <input TYPE="hidden" NAME="txtGreenSheetCurr" VALUE="">
                        <input TYPE="hidden" NAME="txtAllAccActCurr" VALUE="">
                        <input TYPE="hidden" NAME="txtContactCurr" VALUE="">
                        <input TYPE="hidden" NAME="txtNotesCurr" VALUE="">
                        <input TYPE="hidden" NAME="txtAttachCurr" VALUE="">
                        <input TYPE="hidden" NAME="txtOppCurr" VALUE="">
                        <input TYPE="hidden" NAME="txtAccActCurr" VALUE="">
                    </td>
                </tr>
            </table>
        </form> 
       <%--   </td>
            </tr>
        </table> --%>
 <%
    connection.close();
    connection = null;
                }catch(Exception se){
                    System.out.println("Exception in AccountDetails "+se.getMessage());
                }finally{
                    if(connection!= null){
                        connection.close();
                        connection = null;
                    }
                }
    
    %>
    </body>
</html>
