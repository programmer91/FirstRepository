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
        <title>Hubble Organization Portal :: ${title} Activities </title>
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
                <td> --%>
                     <%
        try{
        /* String Variable for storing current position of records in dbgrid*/
        strTmp = request.getParameter("txtAccActCurr");
        
        if (strTmp != null)
            intCurr = Integer.parseInt(strTmp);
        
        /* Specifing Shorting Column */
        strSortCol = request.getParameter("txtAccActSortCol");
        
        if (strSortCol == null) strSortCol = "DateDue";
        
        strSortOrd = request.getParameter("txtAccActSortAsc");
        if (strSortOrd == null) strSortOrd = "DESC";
        
          /* Getting DataSource using Service Locator */
                connection = ConnectionProvider.getInstance().getConnection();
              //  System.err.println("connection"+connection);
        
        currentAccountId=request.getParameter("currentAccountId");
        /* Sql query for retrieving resultset from DataBase */
        queryString ="SELECT Id,ActivityType,Status,Description,DateDue,CreatedDate,AssignedToId,Comments FROM tblCrmActivity";
        queryString = queryString + " WHERE AccountId ="+currentAccountId+" AND ContactId =0 ORDER BY CreatedDate DESC";
        
        /*
        queryString ="SELECT Id,AccountId,ActivityType,Status FROM tblCrmActivity";
        queryString = queryString + " WHERE AccountId ="+currentAccountId+ "ORDER BY CreatedDate";
        queryString = queryString + " AND ContactId=0";
         */
        
       // out.print(queryString);
        String activityAction = "../activities/activity.action";
       // out.print(activityAction);
        
        if(currentAccountId != null){
            activityAction = activityAction+"?accountId="+currentAccountId+"&contactId=0";
                  /*  +request.getAttribute("currentAccountId")
                    +"&contactId=0";*/
        }
        
        %>   
     <form action="" method="post" name="frmDBAccActGrid"> 
         <table cellpadding="0" cellspacing="0" border="0" width="100%">
             <tr align="left">
                    <td class="headerText">
                        <a href="<%=activityAction%>" style="align=left;">
                            <img alt="Add Activity"
                                 src="<s:url value="/includes/images/add.gif"/>" 
                                 width="33px" 
                                 height="19px"
                             border="0" align="left"></a>&nbsp;&nbsp;
                    </td>
                </tr>
                
                <tr>
                    <td>
                        <grd:dbgrid id="tblCrm" name="tblCrm" width="100" pageSize="20" 
                                    currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                    dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                        
                             <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                           imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif" 
                                           addImage="../../includes/images/DBGrid/Add.png" addAction="<%=activityAction%>" 
                                           scriptFunction="getNextAccountActivities"/>
                             <grd:datecolumn dataField="CreatedDate" headerText="DateCreated" dataFormat="MM-dd-yyyy" width="13"/> 
                             <grd:anchorcolumn dataField="ActivityType" 
                                              headerText="Activity Type" 
                                              linkUrl="../activities/getActivity.action?id={Id}" linkText="{ActivityType}" width="15"/>
                              <grd:anchorcolumn dataField="Description" linkUrl="javascript:doGetAll('{Id}')" headerText="Description"
                                              linkText="Click To View"  width="10" />
                             <grd:anchorcolumn dataField="Comments" linkUrl="javascript:doGetComments('{Id}')" headerText="Comments"
                                              linkText="Click To View"  width="10" />
                            
                            <grd:textcolumn dataField="AssignedToId"   headerText="AssignedTo"  width="15"/>  
                            <grd:textcolumn dataField="Status"       headerText="Status"     width="15"/>     
                            <grd:datecolumn dataField="DateDue" headerText="DueDate" dataFormat="MM-dd-yyyy" width="20"/>
                            
                            
                        </grd:dbgrid>
                        
                        <!-- these components are DBGrid Specific  -->
                        <input TYPE="hidden" NAME="txtAccActCurr" VALUE="<%=intCurr%>">
                        <input TYPE="hidden" NAME="txtAllAccActCurr" VALUE="">
                        <input TYPE="hidden" NAME="txtContactCurr" VALUE="">
                        <input TYPE="hidden" NAME="txtProjectCurr" VALUE="">
                        <input TYPE="hidden" NAME="txtVendorAttachCurr" VALUE="">
                        
                        <input TYPE="hidden" NAME="submitFrom" VALUE="dbGrid">
                        <input type="hidden" name="accId" value="<%=currentAccountId%>">
                        
                        <input TYPE="hidden" NAME="txtAccActSortCol" VALUE="<%=strSortCol%>">
                        <input TYPE="hidden" NAME="txtAccActSortAsc" VALUE="<%=strSortOrd%>">
                    </td>
                </tr>
         </table>
     </form>    
          <%--       </td>
            </tr>
        </table>  --%>
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
