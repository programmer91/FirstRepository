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
<%--   <table  class="templateTable1000x580" align="center" cellpadding="0" cellspacing="0">
            <tr>
                <td>   --%>
                
                 <%
                 try{  
        /* String Variable for storing current position of records in dbgrid*/
        strTmp = request.getParameter("txtVendorAttachCurr");
        
        if (strTmp != null)
            intCurr = Integer.parseInt(strTmp);
        
        /* Specifing Shorting Column */
        strSortCol = request.getParameter("txtVendorAttachSortCol");
        
        if (strSortCol == null) strSortCol = "DateOfUpload";
        
        strSortOrd = request.getParameter("txtVendorAttachSortAsc");
        if (strSortOrd == null) strSortOrd = "ASC";
        
         /* Getting DataSource using Service Locator */
                connection = ConnectionProvider.getInstance().getConnection();
               // System.err.println("connection"+connection);
        currentAccountId=request.getParameter("currentAccountId");
        /* Sql query for retrieving resultset from DataBase */
        queryString ="Select Id,AttachmentType,AttachType,DateOfUpload,UploadedBy,Description,ObjectId,ObjectType from tblPrjAttachments WHERE ObjectType ='Vendor'";
        queryString = queryString + "AND ObjectId ="+currentAccountId+" ORDER BY DateOfUpload DESC";
        
        String attachmentListAction = "../../projects/getAttachment.action";
        
        if(request.getAttribute("currentAccountId") != null){
           // attachmentListAction = attachmentListAction+"?objectId="+request.getAttribute("currentAccountId")+"&objectType=Vendor";
             attachmentListAction = attachmentListAction+"?objectId="+currentAccountId+"&objectType=Vendor";
           //out.print(queryString);
        }
        %>
        
        <form action=""  method="post" name="frmVendorAttachGrid"> 
            <table cellpadding="0" cellspacing="0" width="100%">
                <tr>
                    <td class="headerText">
                        <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                    </td>
                </tr>
                <tr>
                    <td>
                        <!-- DataGrid for list all Attachments -->
                        <grd:dbgrid id="tblprjAttachments" name="tblprjAttachments" width="100" pageSize="2" 
                                    currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                    dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                            
                            <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                           imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                           addImage="../../includes/images/DBGrid/Add.png" 
                                           addAction="<%=attachmentListAction%>"
                                           scriptFunction="getVendorAttachment"/>
                            
                            <grd:textcolumn dataField="AttachmentType"  headerText="Attachment FileName" width="30"/>  
                            <grd:textcolumn dataField="AttachType"  headerText="Attachment Name"   width="30" sortable="true"/> 
                            
                            <grd:datecolumn dataField="DateOfUpload"  headerText="Date Uploaded" dataFormat="MM-dd-yyyy HH:mm:SS" width="20"/>
                            <grd:imagecolumn headerText="Download" width="4" HAlign="center" 
                                             imageSrc="../../includes/images/download_11x10.jpg"
                                             linkUrl="../../projects/getDownload.action?Id={Id}" imageBorder="0"
                                             imageWidth="11" imageHeight="10" alterText="Download"></grd:imagecolumn>
                        </grd:dbgrid>
                        <!-- these components are DBGrid Specific  -->
                                                            
                                                            
                                                            
                        <input TYPE="hidden" NAME="txtVendorAttachCurr" VALUE="<%=intCurr%>">                                                            
                        <input TYPE="hidden" NAME="txtAccActCurr" VALUE="">
                        <input TYPE="hidden" NAME="txtContactCurr" VALUE="">                                                            
                        <input TYPE="hidden" NAME="txtOppCurr" VALUE="">
                        
                        <input TYPE="hidden" NAME="submitFrom" VALUE="dbGrid">
                        <input type="hidden" name="accId" value="<%=currentAccountId%>">                                                            
                        <input TYPE="hidden" NAME="txtVendorAttachSortCol" VALUE="<%=strSortCol%>">
                        <input TYPE="hidden" NAME="txtVendorAttachSortAsc" VALUE="<%=strSortOrd%>">
                        
                        
                        
                    </td>
                </tr>
            </table>    
        </form>  
          <%--         </td>
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
