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

   <%--    <table  class="templateTable1000x580" align="center" cellpadding="0" cellspacing="0">
            <tr>
                <td> 
        --%> 
        <%
        try{
        /* String Variable for storing current position of records in dbgrid*/
        strTmp = request.getParameter("txtCurr");
        
        //intCurr = 1;
        
        if (strTmp != null)
            intCurr = Integer.parseInt(strTmp);
            
            /* Specifing Shorting Column */
        strSortCol = request.getParameter("txtOppSortCol");
        
        if (strSortCol == null) strSortCol = "CreatedDate";
        
        strSortOrd = request.getParameter("txtOppSortAsc");
        if (strSortOrd == null) strSortOrd = "ASC";
        
        
        /* Getting DataSource using Service Locator */       
        connection = ConnectionProvider.getInstance().getConnection(); 
       // System.err.println("connection In Requirement"+connection);
        currentAccountId=request.getParameter("currentAccountId");
        
         String  queryString  ="select TRIM(Id) AS RequirementId,CustomerId AS accId,TRIM(JobTitle) AS JobTitle ,CONCAT(`tblRecRequirement`.`State`,', ',`tblRecRequirement`.`Country`) AS Location,STATUS,StartDate,AssignedTo AS Recruiter,AssignToTechLead AS PreSales,Skills FROM tblRecRequirement  WHERE country='USA' and CustomerId='"+currentAccountId+"'   ORDER BY DatePosted DESC";
         //out.println(queryString);
         
         String RequirementAction = "../requirement/requirement.action?accId="+request.getAttribute("currentAccountId");
         
        
        if(request.getAttribute("currentAccountId") != null){
            
            String currentAccountId = (String)request.getAttribute("currentAccountId");
            
        }
        %>
        
        
        <form method="post" id="frmDBGrid" name="frmDBGrid" action=""> 
            <table cellpadding="0" cellspacing="0" width="100%">
                
                <tr>
                    <td class="headerText"> <a href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/requirement/requirement.action?accId=<%=currentAccountId%>"style="align=left;">
                            <img alt="Add Requirement"
                                 src="<s:url value="/includes/images/add.gif"/>" 
                                 width="33px" 
                                 height="19px"
                             border="0" align="left"></a>&nbsp;&nbsp;
                        
                    </td>
                </tr>
                
                <tr>
                    <td>
                        
                        <!-- DataGrid for list all activities -->
                        <grd:dbgrid id="tblRecRequirement" name="tblRecRequirement" width="100" pageSize="10" 
                                    currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                    dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                            
                            
                            <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                           imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                           addImage="../../includes/images/DBGrid/Add.png"
                                           addAction="<%=RequirementAction%>"/>
                            <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" />
                            
                            
                            
                            <grd:imagecolumn  headerText="Edit" width="5"  HAlign="center"  
                                              imageSrc="../../includes/images/DBGrid/newEdit_17x18.png" 
                                              linkUrl="../requirement/getRequirement.action?objectId={RequirementId}&accId={accId}"
                                              imageBorder="0" imageWidth="16" imageHeight="16" alterText="Click to edit" />
                            
                            <grd:anchorcolumn dataField="JobTitle" linkUrl="javascript:getRequirementSkills('{RequirementId}')" headerText="Job Title"
                                              linkText="{JobTitle}"  width="10" />
                            <grd:textcolumn dataField="Location"  headerText="Location"   width="5" />
                            <grd:textcolumn dataField="status" headerText="Status" HAlign="center" dataFormat="" width="5" />
                            <grd:datecolumn dataField="startdate" headerText="StartDate"  width="15"  dataFormat="MM-dd-yyyy HH:mm:SS" />
                            <grd:anchorcolumn dataField="Recruiter" linkUrl="javascript:getRecruiterDetails()" headerText="Recruiter"
                                              linkText="{Recruiter}"  width="15" />
                            
                            <grd:anchorcolumn dataField="PreSales" linkUrl="javascript:getPreSalesDetails('{PreSales}')" headerText="Pre-Sales"
                                              linkText="{PreSales}"  width="15" />
                            
                            <%--  <grd:imagecolumn headerText="Consultants" width="14" HAlign="center" imageSrc="../../includes/images/DBGrid/List.png" 
                                             linkUrl="consultantForRequirementList.action?objectId={RequirementId}" imageBorder="0" imageWidth="45" imageHeight="20"/>
                           <grd:textcolumn dataField="skills"  headerText="SkillSet"   width="20" /> --%>
                                                                
                        </grd:dbgrid>
                        
                        <!-- these components are DBGrid Specific  -->
                        <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                        <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                        <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                        <input type="hidden" name="submitFrom" value="dbGrid">
                        
                    </td>
                </tr>
            </table>
        </form>
        
            <%--     </td>
            </tr>
        </table> --%>
  <%
    connection.close();
    connection = null;
                }catch(Exception se){
                    System.out.println("Exception in Requirement Details  "+se.getMessage());
                }finally{
                    if(connection!= null){
                        connection.close();
                        connection = null;
                    }
                }
    
    %>
    </body>
</html>
