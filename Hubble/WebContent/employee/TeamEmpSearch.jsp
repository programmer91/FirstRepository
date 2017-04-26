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
    <title>Hubble Organization Portal :: Employee Search</title>
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
            <ul id="accountTabs" class="shadetabs" >
                <li ><a href="#" class="selected" rel="employeeSearchTab"  >Employee Search </a></li>
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
                            <s:form name="frmEmpSearch" action="getTeamQuery" theme="simple">
                            <table cellpadding="1" cellspacing="1" border="0" width="600px">
                            <tr> 
                                <td class="fieldLabel">Name :</td>
                                <td><s:textfield name="firstName" id="firstName" cssClass="inputTextBlue" value="%{firstName}" onchange="firstNameValidate(document.frmEmpSearch.firstName.value);"/> </td>
                                <%--<td class="fieldLabel">Last Name :</td>                  
                                                                <td><s:textfield name="lastName" id="lastName" cssClass="inputTextBlue" value="%{lastName}"/></td> --%>
                                <td class="fieldLabel">Work-Phone No :</td>                  
                                <td><s:textfield name="workPhoneNo" id="workPhoneNo" cssClass="inputTextBlue" value="%{workPhoneNo}" onchange="workPhoneNoValidate(document.frmEmpSearch.workPhoneNo.value);" onblur="return validatenumber(this)"/></td> 
                            </tr>
                            <tr>
                                <td  class="fieldLabel">Current Status:</td>
                                <td><s:select list="currStatusList" id="currStatus" name="currStatus"  cssClass="inputSelect" value="%{currStatus}" /></td>
                                <td class="fieldLabel" width="200px" align="right">Department :</td>
                                <td><s:select label="Select Department" 
                                                  name="departmentId" 
                                                  headerKey="All"
                                                  headerValue="All"
                                              list="departmentIdList" cssClass="inputSelect" value="%{departmentId}"/></td>
                            </tr>
                            <tr>
                                <td class="fieldLabel">Organization:</td>
                                <td colspan="2"><s:select list="orgIdList" id="orgId" name="orgId" headerKey="All" headerValue="All" cssClass="inputSelectExtraLarge" value="%{orgId}"/></td>
                                <td>
                                    <s:reset name="reset" value="Reset" cssClass="buttonBg"/>
                                    <input type="hidden" name="submitFrom" value="<%=searchSubmitValue%>">
                                    <s:submit cssClass="buttonBg" value="Search"/>
                                </td>
                            </tr>
                            <td colspan="4">
                                <span class="messageNote">WildCard : * </span>
                            </td>
                        </tr>
                    </table>
                    </s:form>
                </td>
            </tr>
            <tr>
                <td>
                    <%
                    /* String Variable for storing current position of records in dbgrid*/
                    strTmp = request.getParameter("txtCurr");
                    
                    intCurr = 1;
                    
                    if (strTmp != null)
                        intCurr = Integer.parseInt(strTmp);
                    
                    /* Specifing Shorting Column */
                    strSortCol = request.getParameter("Colname");
                    
                    if (strSortCol == null) strSortCol = "Fname";
                    
                    strSortOrd = request.getParameter("txtSortAsc");
                    if (strSortOrd == null) strSortOrd = "ASC";
                    
                    
                    try{
                        
                        /* Getting DataSource using Service Locator */
                        connection = ConnectionProvider.getInstance().getConnection();
                        
                        /* Sql query for retrieving resultset from DataBase */
                        queryString  =null;
                        queryString = session.getAttribute(ApplicationConstants.QS_EMP_LIST).toString();
                        
                     // out.println(queryString);
                        
                        //out.println("--------"+submittedFrom);
                    %>
                    
                    <s:form action="" theme="simple" name="frmDBGrid">   
                        
                        <table cellpadding="0" cellspacing="0" width="100%" border="0">
                            
                            
                            <tr>
                                <td width="100%">
                                    
                                    <grd:dbgrid id="tblStat" name="tblStat" width="98" pageSize="10"
                                                currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2"
                                                dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                        
                                        <grd:gridpager imgFirst="../includes/images/DBGrid/First.gif" imgPrevious="../includes/images/DBGrid/Previous.gif" 
                                                       imgNext="../includes/images/DBGrid/Next.gif" imgLast="../includes/images/DBGrid/Last.gif"/>
                                        
                                        <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" 
                                                        imageAscending="../includes/images/DBGrid/ImgAsc.gif" 
                                                        imageDescending="../includes/images/DBGrid/ImgDesc.gif"/>   
                                        
                                        <grd:rownumcolumn headerText="SNo" width="4" HAlign="right"/>
                                        
                                        <grd:imagecolumn headerText="Edit" width="4" HAlign="center" imageSrc="../includes/images/DBGrid/newEdit_17x18.png"
                                                         linkUrl="getEmployee.action?empId={Id}" imageBorder="0"
                                                         imageWidth="16" imageHeight="16" alterText="Click to edit"></grd:imagecolumn>
                                        
                                        <grd:textcolumn dataField="Fname" headerText="FirstName" width="18"/>
                                        <grd:textcolumn dataField="Lname" headerText="LastName" width="12"/>
                                        <grd:textcolumn dataField="WorkPhoneNo" headerText="WorkPhone" width="13"/>
                                        <grd:textcolumn dataField="CellPhoneNo" headerText="CellPhone" width="13"/>
                                        <grd:textcolumn dataField="Email1" headerText="EmailId" width="20"/>
                                        <grd:textcolumn dataField="CurStatus" headerText="Status" width="12"/>
                                        <grd:imagecolumn headerText="Termination" width="4" HAlign="center" 
                                                         imageSrc="../includes/images/DBGrid/terminate.png"
                                                         linkUrl="deleteEmployee.action?empId={Id}" imageBorder="0"
                                                         imageWidth="69" imageHeight="20" alterText="Delete"></grd:imagecolumn>
                                        
                                    </grd:dbgrid>
                                    
                                    <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                    <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                    <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                                    
                                    <input type="hidden" name="submitFrom" value="dbGrid">
                                    <input type="hidden" name="submitFromReportsTo" value="dbGrid">
                                    
                                    <s:hidden  name="firstName" value="%{firstName}"/>
                                    <s:hidden  name="currStatus" value="%{currStatus}"/>
                                    <s:hidden  name="orgId" value="%{orgId}"/>
                                    <s:hidden  name="departmentId" value="%{departmentId}"/>
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

var countries=new ddtabcontent("accountTabs")
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
