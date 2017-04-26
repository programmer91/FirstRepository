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

           
                    
                    <%
                    try{
                    int intCurr = 1;
                    int intSortOrd = 0;
                    String strTmp = null;
                    String   strSQL= null;
                    String strSortCol = null;
                    String strSortOrd = "ASC";
                    boolean blnSortAsc = true;
                    
                    String userId = (String)request.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID);
                    
                    
                    /* Getting DataSource using Service Locator */
                connection = ConnectionProvider.getInstance().getConnection();
                    
                      
                     // out.print("connection:"+connection);
                      currentAccountId=request.getParameter("currentAccountId");
                    
                   /* strSQL = "SELECT Id,CustomerName,concat(LastName,' ',FirstName,' ',MiddleName) AS ConsultantName,ConsultantType,DateStart,DateEnd,POStatus,CreatedBy," +
                            "ModifiedBy,UnitsRate FROM vwGreenSheets WHERE country='USA' and createdBy='"+userId+"' AND RecordType=1 and ConsultantId="+request.getAttribute("currentAccountId")+" order by CustomerName,POStatus,DateEnd ";*/
                    strSQL = "SELECT Id,CustomerName,concat(LastName,' ',FirstName,' ',MiddleName) AS ConsultantName,ConsultantType,DateStart,DateEnd,POStatus,CreatedBy," +
                            "ModifiedBy,UnitsRate FROM vwGreenSheets WHERE country='USA' and createdBy='"+userId+"' AND RecordType=1 and ConsultantId="+currentAccountId+" order by CustomerName,POStatus,DateEnd ";
                    
                    
                    strTmp = request.getParameter("txtCurr");
                    
                    try {
                        if (strTmp != null)
                            intCurr = Integer.parseInt(strTmp);
                        
                    } catch (NumberFormatException NFEx) {
                    }
                    strSortCol = request.getParameter("txtSortCol");
                    strSortOrd = request.getParameter("txtSortAsc");
                    
                    
                   // out.println(strSQL);
                    %>
                    
                    <form method="post" id="frmDBGrid" name="frmDBGrid" action="">  
                        <table cellpadding="0" border="0" cellspacing="0" width="100%" align="center">
            <tr>
                <td class="headerText"> <a href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/greensheets/viewGreenSheet.action?teamGreensheets=true" style="align=left;">
                        <img alt="Add Requirement"
                             src="<s:url value="/includes/images/add.gif"/>" 
                             width="33px" 
                             height="19px"
                         border="0" align="left"></a>&nbsp;&nbsp;
                    
                </td>
            </tr>
            <tr>
                <td align="center">
                        
                        <grd:dbgrid  id="tblStat" name="tblStat"  width="100" pageSize="10" 
                                     currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                     dataMember="<%=strSQL.toString()%>" dataSource="<%=connection%>" cssClass="gridTable">
                            
                            <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                           imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                           addImage="../../includes/images/DBGrid/Add.png"
                                           addAction="../greensheets/viewGreenSheet.action?teamGreensheets=true"/>
                            
                            <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" />
                            <grd:imagecolumn  headerText="Edit" width="5"  HAlign="center"  
                                              imageSrc="../../includes/images/DBGrid/newEdit_17x18.png" 
                                              linkUrl="../greensheets/getGreenSheetByID.action?greenSheetId={Id}&teamGreensheets=true" 
                                              imageBorder="0" imageWidth="16" imageHeight="16" alterText="Click to edit" />
                            <%--  <grd:textcolumn dataField="CustomerName" headerText="Customer Name"  sortable="true"
                                            width="20"/> --%>
                            <grd:textcolumn dataField="ConsultantName" headerText="Consultant Name" sortable="true" 
                                            width="15"/>
                            <%--    <grd:textcolumn dataField="ConsultantType" headerText="Consultant Type" 
                                            width="14"  HAlign="center"/> --%>
                            <grd:datecolumn dataField="DateStart" headerText="Start Date" dataFormat="MM-dd-yyyy"
                                            width="9" sortable="true" />
                            <grd:datecolumn dataField="DateEnd" headerText="End Date" sortable="true" dataFormat="MM-dd-yyyy"
                                            width="8"/>
                            <grd:textcolumn dataField="POStatus" headerText="POStatus" 
                                            width="10" sortable="true" HAlign="center" />
                            <grd:numbercolumn dataFormat="#00.00" width="10" dataField="UnitsRate" headerText="Billing Rate/hr " />
                            
                            <%--    <grd:textcolumn dataField="CreatedBy" headerText="CreatedBy" />
                       <grd:textcolumn dataField="ModifiedBy" headerText="ModifiedBy" /> --%>
                            
                        </grd:dbgrid>
                        
                        
                        
                        <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                        <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                        <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                        <input TYPE="hidden" name="submitFrom" value="dbGrid"/>
                         </td>
                </tr>
            </table>
        </form>
                  
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
