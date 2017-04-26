<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%--<%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<%@ page import="com.freeware.gridtag.*" %> 
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd" %>
<html>
<head>
    <title>Hubble Organization Portal :: Project Team</title>
    <sx:head cache="true"/>
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/clientValidations/EmpSearchClientValidation.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
     <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBPMOGrid.js"/>"></script>
    <s:include value="/includes/template/headerScript.html"/>
</head>
<body class="bodyGeneral" oncontextmenu="return false;">

<%!
/* Declarations */
Connection connection;
String queryString;
String strTmp;
String strSortCol;
String strSortOrd;
String submittedFrom;
String searchSubmitValue;
String currentAccountId ;
String currentProjectId ;
int intSortOrd = 0;
int intCurr;
boolean blnSortAsc = true;
%>

<!--//START MAIN TABLE : Table for template Structure-->
<table class="templateTableLogin" align="center" cellpadding="0" cellspacing="0">

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
    <table class="innerTableLogin" cellpadding="0" cellspacing="0">
    <tr>
        
        <!--//START DATA COLUMN : Coloumn for LeftMenu-->
        <td width="150px;" class="leftMenuBgColor" valign="top">
            <s:include value="/includes/template/LeftMenu.jsp"/>
        </td>
        
        <!--//END DATA COLUMN : Coloumn for LeftMenu-->
                            
        <!--//START DATA COLUMN : Coloumn for Screen Content-->
        <td width="850px" class="cellBorder" valign="top" style="padding: 5px 5px 5px 5px;">
            <!--//START TABBED PANNEL : -->
            <ul id="ProjectDetailsTabs" class="shadetabs" >
                <li ><a href="#" class="selected" rel="employeeSearchTab"  >Project Team </a></li>
            </ul>
            <%--<sx:tabbedpanel id="employeeSearchPannel" cssStyle="width: 840px; height: 500px;padding: 5px 5px 5px 5px;" doLayout="true"> --%>
            <div  style="border:1px solid gray; width:840px;height: 500px; overflow:auto; margin-bottom: 1em;">   
                <!--//START TAB : -->
                <%--  <sx:div id="employeeSearchTab" label="Employee Search" cssStyle="overflow:auto;"  > --%>
                <div id="employeeSearchTab" class="tabcontent"  >
                    <table cellpadding="0" cellspacing="0" border="0" width="100%">
                        <tr align="right">
                            <td class="headerText">
                                <img alt="Home" 
                                     src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" 
                                     width="100%" 
                                     height="13px" 
                                     border="0">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <% if(request.getAttribute(ApplicationConstants.RESULT_MSG) != null){
                                    out.println(request.getAttribute(ApplicationConstants.RESULT_MSG));
                                }
                                
                                if(request.getAttribute("submitFrom") != null){
                                    submittedFrom = request.getAttribute("submitFrom").toString();
                                }
                                
                                if(!"dbGrid".equalsIgnoreCase(submittedFrom)){
                                    searchSubmitValue = submittedFrom;
                                }
                                
                                %>
                                
                            </td>
                        </tr>
                        <tr>
                                            <td>
                                                <s:form name="frmEmpSearch" action="#" theme="simple">
                                                     <table cellpadding="1" cellspacing="1" border="0" style="margin-left: 6.9%">
                                                        <tr> 
                                                            <td class="fieldLabel" align="left">Customer&nbsp;Name :</td>
                                                            <td style="font-size: 12px;font-style: normal;color:green "><s:label><s:property  value="customerName" /></s:label></td>

                                                             <td class="fieldLabel" align="left">Project&nbsp;Name :</td>
                                                            <td style="font-size: 12px;font-style: normal;color:green "><s:label><s:property  value="projectName" /></s:label></td>

                                                        </tr>
                                                    </table>

                                                </s:form>
                                            </td>
                                        </tr>

                        <tr>
                            <td>
                            <s:form name="frmEmpSearch" action="getProjectTeamQuery.action" theme="simple">
                            <table cellpadding="1" cellspacing="1" border="0" width="750px">
                            <tr> 
                                <td class="fieldLabel">Name :</td>
                                <td><s:textfield name="firstName" id="firstName" cssClass="inputTextBlue" value="%{firstName}" onchange="firstNameValidate(document.frmEmpSearch.firstName.value);"/> </td>
                                <%--<td class="fieldLabel">Last Name :</td>                  
                                                                <td><s:textfield name="lastName" id="lastName" cssClass="inputTextBlue" value="%{lastName}"/></td> --%>
                                
                                
                               <td  class="fieldLabel">Status:</td>
                             <td><s:select list="{'All','Active','InActive'}" id="status" name="empProjectStatus"  cssClass="inputSelect" value="%{empProjectStatus}" /></td>     </tr>
                         
                          <tr>
                                <td width="200px" class="fieldLabel">Reports&nbsp;To&nbsp;:</td>
                                                           <td width="1%">
                                                                <s:select list="projectTeamReportsTo"
                                                                           headerKey="-1" headerValue="--Please Select--"
                                                                         id="reportsTo" name="reportsTo"
                                                                          cssClass="inputSelectForProjects"  />
                                                           </td>
                              <td class="fieldLabel"> Is Billable :</td>
                                                        <td><s:checkbox   id="isBillable"  name="isBillable" /></td>
                                                    
                            </tr>

                            <tr>
                                <td colspan="4">
                                    <span class="messageNote">WildCard : * </span>
                                </td>
                                <td>
                            <a href="<s:url value="pmoActivity.action"/>" style="align:center;">
                                            <img alt="Back to List"
                                                 src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                 width="66px" 
                                                 height="19px"
                                             border="0" align="center"></a>
                                    <s:submit cssClass="buttonBg" value="Search"/>
                                    <input type="hidden" name="submitFrom" value="dbGrid">
                                     <s:hidden name="projectId" value="%{projectId}"/>
                                    <s:hidden name="accountId" value="%{accountId}"/>
                                    <s:hidden name="customerName" value="%{customerName}"/> 
                                  
                                    
                                </td>
                            </tr>
                            
                        </tr>
                    </table>
                    </s:form>
                </td>
            </tr>
            <tr>
                <td>
                    <%
                    /* String Variable for storing current position of records in dbgrid*/
                    strTmp = request.getParameter("txtProjTeamCurr");
                    
                    intCurr = 1;
                    
                    if (strTmp != null)
                        intCurr = Integer.parseInt(strTmp);
                    
                    /* Specifing Shorting Column */
                    strSortCol = request.getParameter("txtProjSortCol");
                    
                    if (strSortCol == null) strSortCol = "Fname";
                    
                    strSortOrd = request.getParameter("txtProjSortAsc");
                    if (strSortOrd == null) strSortOrd = "ASC";
                    
                    
                    try{
                         if(request.getAttribute("accountId") != null){
                    currentAccountId = (String)request.getAttribute("accountId");
                    }
                    
                    if(request.getAttribute("projectId") != null){
                    currentProjectId = (String)request.getAttribute("projectId");
                    }
                        /* Getting DataSource using Service Locator */
                        connection = ConnectionProvider.getInstance().getConnection();
                        
                        /* Sql query for retrieving resultset from DataBase */
                        queryString  =null;
                        queryString = session.getAttribute(ApplicationConstants.QS_PROJECT_TEAM_LIST).toString();
                         String  heirarchyAction = "../projects/addProjectTeam.action?accountId="+currentAccountId+"&projectId="+currentProjectId;
                       //out.println(queryString);
                        
                        //out.println("--------"+submittedFrom);
                    %>
                    
                    <s:form action="" theme="simple" name="frmDBProjectTeamGrid1">   
                        
                        <table cellpadding="0" cellspacing="0" width="100%" border="0">
                            
                            
                            <tr>
                                <td width="100%">
                                    
                                    <grd:dbgrid id="tblProjectGrid" name="tblProjectGrid" width="98" pageSize="11"
                                                currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2"
                                                dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                        
                                        <grd:gridpager imgFirst="../includes/images/DBGrid/First.gif" imgPrevious="../includes/images/DBGrid/Previous.gif" 
                                                       imgNext="../includes/images/DBGrid/Next.gif" imgLast="../includes/images/DBGrid/Last.gif"
                                                       addImage="../includes/images/DBGrid/Add.png"  addAction="<%=heirarchyAction%>" 
                                                                      scriptFunction="getNextProjectsPMOTeam"/>
                                        
                                        <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" 
                                                        imageAscending="../includes/images/DBGrid/ImgAsc.gif" 
                                                        imageDescending="../includes/images/DBGrid/ImgDesc.gif"/>   
                                        
                                       
                                        
                                          <grd:imagecolumn headerText="Edit" width="4" HAlign="center" imageSrc="../includes/images/DBGrid/newEdit_17x18.png"
                                                         linkUrl="../projects/getProjectTeamDetails.action?id={Id}&accountId={AccountId}&projectId={ProjectId}&flag=e&empId={ObjectId}" imageBorder="0"
                                  
                                                         imageWidth="16" imageHeight="16" alterText="Click to edit"></grd:imagecolumn>
                                      <%--  <grd:textcolumn dataField="ProjectName" headerText="ProjectName" width="18"/> --%>
                                        <grd:textcolumn dataField="EmpName" headerText="ResourceName" width="18"/>
                                         <grd:textcolumn dataField="ResourceCountry" headerText="Country" width="8"/>
                                        <grd:datecolumn dataField="StartDate" headerText="StartDate"  width="10" dataFormat="MM/dd/yyyy"/>
                                         <grd:datecolumn dataField="EndDate" headerText="EndDate"  width="10" dataFormat="MM/dd/yyyy"/>
                                        <grd:datecolumn dataField="Utilization" headerText="Utilization" width="10"/>
                                        
                                         <grd:textcolumn dataField="Status" headerText="Status" width="10"/>
                                       <%--  <grd:textcolumn dataField="Email" headerText="EmailId" width="20"/> --%>
                                        <%-- <grd:textcolumn dataField="ResourceTitle" headerText="Role" width="12"/> --%>
                                      <%--  <grd:decodecolumn dataField="ResourceTitle" decodeValues="1,2,3,4,5,6,7" displayValues="Team Member,Team Lead,PM Offshore,PM Onsite,PM Customer,Sponsor,Delivery Manager" headerText="Role" valueSeperator="," width="10"/> --%>
                                        <grd:decodecolumn dataField="ObjectType" decodeValues="E,C,V" displayValues="Employee,Customer Contact,Vendor Contact" headerText="EmployeeType" valueSeperator="," width="10"/>
                                         <grd:numbercolumn dataField="Billable" headerText="Billable" width="10"/>
                                    </grd:dbgrid>
                                    
                                    <input TYPE="hidden" NAME="txtProjTeamCurr" VALUE="<%=intCurr%>">
                                        <input TYPE="hidden" NAME="txtProjSortCol" VALUE="<%=strSortCol%>">
                                        <input TYPE="hidden" NAME="txtProjSortAsc" VALUE="<%=strSortOrd%>">
                                        <input TYPE="hidden" NAME="txtAttachCurr1" VALUE="">
                                       
                                        <s:hidden name="submitFrom" value="%{submitFrom}"/>
                                         <s:if test="%{submitFrom=='dbGrid'}">
                                              <s:hidden name="projectId" value="%{projectId}"/>
                                    <s:hidden name="accountId" value="%{accountId}"/> 
                                    <s:hidden name="customerName" value="%{customerName}"/>
                                             </s:if>
                                    
                                    <s:hidden name="firstName" value="%{firstName}"/>
                                    <s:hidden name="empProjectStatus" value="%{empProjectStatus}"/>
                                      <s:hidden name="reportsTo" value="%{reportsTo}"/>
                                    <s:hidden name="isBillable" value="%{isBillable}"/>
                                          
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
                </td>
            </tr>
              
            
             
     
            </table>
            <%-- </sx:div > --%>
        </div>
        <!--//END TAB : -->
        <%-- </sx:tabbedpanel> --%>
    </div>
    <script type="text/javascript">

var countries=new ddtabcontent("ProjectDetailsTabs")
countries.setpersist(false)
countries.setselectedClassTarget("link") //"link" or "linkparent"
countries.init()

    </script>
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
