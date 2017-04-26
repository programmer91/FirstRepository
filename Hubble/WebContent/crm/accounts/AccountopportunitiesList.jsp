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

     <%-- <table  class="templateTable1000x580" align="center" cellpadding="0" cellspacing="0">
            <tr>
                <td>   --%>
        <%
        try{
        /* String Variable for storing current position of records in dbgrid*/
        strTmp = request.getParameter("txtOppCurr");
        
        if (strTmp != null)
            intCurr = Integer.parseInt(strTmp);
        
        /* Specifing Shorting Column */
        strSortCol = request.getParameter("txtOppSortCol");
        
        if (strSortCol == null) strSortCol = "CreatedDate";
        
        strSortOrd = request.getParameter("txtOppSortAsc");
        if (strSortOrd == null) strSortOrd = "ASC";
        
        
          /* Getting DataSource using Service Locator */
                connection = ConnectionProvider.getInstance().getConnection();         
        currentAccountId=request.getParameter("currentAccountId");
        /* Sql query for retrieving resultset from DataBase */
        queryString ="SELECT Id,AccountId,Title,Description,Value,DueDate,CreatedDate FROM tblCrmOpportunity";
        queryString = queryString + " WHERE AccountId ="+currentAccountId;
        String opportunityAction = "../opportunities/opportunity.action";
        
       // if(request.getAttribute("currentAccountId") != null){
        if(currentAccountId != null){    
           // opportunityAction = opportunityAction+"?accountId="+request.getAttribute("currentAccountId");
            opportunityAction = opportunityAction+"?accountId="+currentAccountId;
        }
        
        String opportunityEditAction = "../opportunities/getOpportunity.action?id={Id}&accountId="+currentAccountId;
        
       // out.print(queryString+" "+connection);
        
        %>
        
        <form action=""  method="post" name="frmDBOppGrid"> 
            <table cellpadding="0" cellspacing="0" width="100%">
                <tr>
                    <td class="headerText"> <a href="<%=opportunityAction%>" style="align=left;">
                            <img alt="Add Oppertunity"
                                 src="<s:url value="/includes/images/add.gif"/>" 
                                 width="33px" 
                                 height="19px"
                             border="0" align="left"></a>&nbsp;&nbsp;
                        
                    </td>
                </tr>
                <tr>
                    <td>
                        <!-- DataGrid for list all Opportunities -->
                        <grd:dbgrid id="tblCrmOpportunity" name="tblCrmOpportunity" width="100" pageSize="10" 
                                    currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                    dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                            
                            <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                           imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                           addImage="../../includes/images/DBGrid/Add.png" 
                                           addAction="<%=opportunityAction%>"
                                           scriptFunction="getNextOpportunities"/>
                            
                            <grd:imagecolumn headerText="Edit" width="4" HAlign="center" imageSrc="../../includes/images/DBGrid/newEdit_17x18.png"
                                             linkUrl="<%=opportunityEditAction%>" imageBorder="0"
                                             imageWidth="16" imageHeight="16" alterText="Click to edit"></grd:imagecolumn>               
                            
                            <grd:textcolumn dataField="Title"  headerText="Title"   width="25" sortable="true"/> 
                            <grd:textcolumn dataField="Description"  headerText="Description"   width="25" sortable="true"/> 
                            <grd:numbercolumn dataField="Value" dataFormat="0.00" headerText="Value"   width="5" /> 
                            <grd:datecolumn dataField="DueDate"  headerText="Due Date"  dataFormat="MM-dd-yyyy HH:mm:SS" width="18" sortable="true"/>
                            <grd:datecolumn dataField="CreatedDate"  headerText="CreatedDate"  dataFormat="MM-dd-yyyy HH:mm:SS" width="18" sortable="true"/>
                        </grd:dbgrid>
                        <!-- these components are DBGrid Specific  -->
                        <input TYPE="hidden" NAME="txtOppCurr" VALUE="<%=intCurr%>">
                        
                        <input TYPE="hidden" NAME="txtProjectCurr" VALUE="">
                        <input TYPE="hidden" NAME="txtAllAccActCurr" VALUE="">
                        <input TYPE="hidden" NAME="txtContactCurr" VALUE="">
                        <input TYPE="hidden" NAME="txtGreenSheetCurr" VALUE="">
                        <input TYPE="hidden" NAME="txtNotesCurr" VALUE=""/>
                        <input TYPE="hidden" NAME="txtAttachCurr" VALUE="">
                        <input TYPE="hidden" NAME="txtAccActCurr" VALUE="">
                        
                        <input TYPE="hidden" NAME="submitFrom" VALUE="dbGrid">
                        
                        <input type="hidden" name="accId" value="<%=currentAccountId%>">
                        
                        <input TYPE="hidden" NAME="txtOppSortCol" VALUE="<%=strSortCol%>">
                        <input TYPE="hidden" NAME="txtOppSortAsc" VALUE="<%=strSortOrd%>">
                        
                        <input type="hidden" name="isRequestFromGrid" value="YES">
                    </td>
                </tr>
            </table>    
        </form>
       <%--   </td>
            </tr>
        </table>  --%>
  <%
    connection.close();
    connection = null;
                }catch(Exception se){
                    System.out.println("Exception in opportunities "+se.getMessage());
                }finally{
                    if(connection!= null){
                        connection.close();
                        connection = null;
                    }
                }
    
    %>
    </body>
</html>

