<%-- 
    Document   : AppraisalsList
    Created on : Mar 25, 2015, 11:48:01 PM
    Author     : miracle
--%>

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
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/AjaxReviewList.js"/>"></script>
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
                <li ><a href="#" class="selected" rel="employeeSearchTab"  >Appraisal Search </a></li>
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
                           <s:form name="frmEmpSearch" action="doEmployeeRevisedSearch.action" theme="simple">
                                                   
                                                    <table cellpadding="1" cellspacing="1" border="0" width="420px" align="center">
                                                      <%--  <tr>
                                                            <td class="fieldLabel">Start&nbsp;Date&nbsp;(mm/dd/yyyy)&nbsp;:</td>
                                                            <td><s:textfield name="startDate" id="startDate" cssClass="inputTextBlueSmall" value="%{startDate}"/><a href="javascript:cal2.popup();">
                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                            </td>

                                                            <td class="fieldLabel">End&nbsp;Date&nbsp;(mm/dd/yyyy)&nbsp;:</td>
                                                            <td>  <s:textfield name="endDate" id="endDate" cssClass="inputTextBlueSmall" value="%{endDate}"/><a href="javascript:cal3.popup();">
                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                            </td>
                                                        </tr>
                                                            --%>
                                                            
                                                      <%--  <tr>
                                                            <td class="fieldLabel" >Review Type :<FONT color="red"  ><em>*</em></FONT> </td>
                                                            <td width="15%"> <s:select id="reviewType"  name="reviewType"  list="searchReviewMap" headerKey="" headerValue="All"   value="%{reviewType}" cssClass="inputSelect" onchange="getAssignedReviews(this);" disabled="False"/></td>
                                                            <td class="fieldLabel" >State :<FONT color="red"  ><em>*</em></FONT> </td>
                                                            <td width="15%"> <s:select id="reviewStatus"  name="reviewStatus"  list="{'Opened','Approved','Denied'}" headerKey="" headerValue="All"   value="%{reviewStatus}" cssClass="inputSelect" onchange="getAssignedReviews(this);" disabled="False"/></td>
                                                          
                                                        </tr> --%>

                                                      <tr>
                                                          
                                                             <td class="fieldLabel" align="right">Department :</td>
                    
                    <td><s:select label="Select Department" 
                                      name="departmentId"
                                      id="departmentId"
                                      headerKey=""
                                      headerValue="All"
                                  list="departmentIdList" cssClass="inputSelect" value="%{departmentId}" onchange="getEmployeesByDept();"/></td>        
                                                          <td class="fieldLabel">EmpName :</td>
                                                            <td ><s:select list="empnamesList" id="empnameById" name="empnameById" headerKey="-1" headerValue="All" cssClass="inputSelect" value="%{empnameById}"/></td> 
                                                            
                                                             
                    
                                                      
                                                            
                                                        </tr>
                                                        
                                                         <tr>
                                                                <td class="fieldLabel">Year(YYYY):</td>
                                                                <td>
                                                                  
                                                                    <s:textfield name="year" id="year" maxlength="4" cssClass="inputTextBlue" value="%{year}"/>
                                                                </td>
                                                                <td class="fieldLabel">Month:</td>
                                                                <td><s:select list="#@java.util.LinkedHashMap@{'1':'Jan','2':'Feb','3':'Mar','4':'Apr','5':'May','6':'June','7':'July','8':'Aug','9':'Sept','10':'Oct','11':'Nov','12':'Dec'}" name="month" id="month" onchange="load(event);" headerValue="select" headerKey="0" value="%{month}"/></td>
                                                                
                                                            </tr>
                                                         
                                                        <tr>
                                                            <td colspan="4" align="right">
                                                                
                                                                <s:submit cssClass="buttonBg"  align="right"  value="Search" />

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
                       queryString = request.getAttribute(ApplicationConstants.EMP_REVIEWS_LIST).toString();
                       // queryString = "SELECT tblEmployee.Id ,CONCAT(FName,' ',LName) AS EmpName,Email1,COUNT(tblEmpReview.Id) AS ReviwCount  FROM tblEmployee LEFT OUTER JOIN tblEmpReview ON (tblEmployee.LoginId=tblEmpReview.UserId) WHERE UserId IN ('nseerapu','vkandregula') AND NextRevisedDate LIKE '2015-03-25' GROUP BY tblEmployee.Id ";
                        //queryString = "SELECT * FROM vwAppraisalList ";
                     // out.println(queryString);
                        
                        //out.println("--------"+submittedFrom);
                    %>
                    
                    <s:form action="" theme="simple" name="frmDBGrid">   
                        
                        <table cellpadding="0" cellspacing="0" width="100%" border="0">
                            
                            
                            <tr>
                                <td width="100%">
                                    
                                    <grd:dbgrid id="tblStat" name="tblStat" width="98" pageSize="17"
                                                currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2"
                                                dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                        
                                        <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                       imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"/>
                                        
                                        <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" 
                                                        imageAscending="../../includes/images/DBGrid/ImgAsc.gif" 
                                                        imageDescending="../../includes/images/DBGrid/ImgDesc.gif"/>   
                                        
                                        <grd:rownumcolumn headerText="SNo" width="4" HAlign="right"/>
                                        
                                        
                                        
                                        <grd:textcolumn dataField="EmpName" headerText="EmpName" width="18"/>
                                        <grd:textcolumn dataField="Email1" headerText="Email" width="12"/>
                                        
                                       
                                        <grd:datecolumn dataField="ReviwCount" headerText="ReviwCount" width="13"/>
                                        <grd:imagecolumn headerText="Report" width="4" HAlign="center" imageSrc="../../includes/images/DBGrid/View.gif"
                                                         linkUrl="getEmpReviewsList.action?userId={LoginId}" imageBorder="0"
                                                         imageWidth="23" imageHeight="12" alterText="Click to edit"></grd:imagecolumn>
                                    </grd:dbgrid>
                                    
                                    <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                    <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                    <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                                    
                                    <input type="hidden" name="submitFrom" value="dbGrid">
                                    <s:hidden  name="departmentId" value="%{departmentId}"/>
                                    <s:hidden  name="empnameById" value="%{empnameById}"/>
                                    <s:hidden  name="year" value="%{year}"/>
                                    <s:hidden  name="month" value="%{month}"/>
                                   
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
