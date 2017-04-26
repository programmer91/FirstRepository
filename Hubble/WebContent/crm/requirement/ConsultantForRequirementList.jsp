<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%-- <%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<%@ page import="com.freeware.gridtag.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hubble Organization Portal :: Consultant List For Requirement</title>
        <%--<sx:head cache="true"/> --%>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tooltip.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tooltip.js"/>"></script>   
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>   
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>   
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ajax/AjaxPopup.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CommonAjax.js"/>"></script>
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
        String userId;
        String submittedFrom;
        String action;
        String consultantForReqAction = null;
        int role;
        int intSortOrd = 0;
        int intCurr;
        boolean blnSortAsc = true;
        %>
        
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
                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                            
                            <!--//START DATA COLUMN : Coloumn for Screen Content-->
                            <td width="850px" class="cellBorder" valign="top" style="padding:10px 5px 5px 5px">
                               <ul id="accountTabs" class="shadetabs" >
                                    <li ><a href="#" class="selected" rel="consultantRequirementPanel"  >Consultant List</a></li>
                                </ul>
                                
                                <div  style="border:1px solid gray; width:845px;height: 500px; overflow:auto; margin-bottom: 1em;">
                                    <%--<sx:tabbedpanel id="consultantRequirementPanel" cssStyle="width: 845px; height: 500px;padding:10px 5px 5px 5px" doLayout="true"> --%>
                                    
                                    <!--//START TAB : -->
                                    <div id="consultantRequirementPanel" class="tabcontent"  >
                                        <%--  <sx:div id="consultantRequirementDiv" label="Consultant List" cssStyle="overflow:auto;"> --%>
                                        
                                        <%
                                        /* String Variable for storing current position of records in dbgrid*/
                                        strTmp = request.getParameter("txtCurr");
                                        
                                        intCurr = 1;
                                        
                                        if (strTmp != null)
                                        intCurr = Integer.parseInt(strTmp);
                                        
                                        try{
                                        
                                        /* Getting DataSource using Service Locator */
                                        connection=ConnectionProvider.getInstance().getConnection();
                                        
                                        /* Sql query for retrieving resultset from DataBase */
                                        String  queryString  =null;
                                        
                                        queryString=session.getAttribute(ApplicationConstants.QUERY_STRING).toString();
                                        //out.println(queryString);
                                        %>
                                        
                                        <form method="post" id="frmDBGrid" name="frmDBGrid" action=""> 
                                            <table cellpadding="0" cellspacing="0" width="100%">
                                                <tr>
                                                    <td class="headerText">
                                                        <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                    </td>
                                                </tr>
                                                
                                                <%
                                                if(request.getAttribute(ApplicationConstants.RESULT_MSG)!=null){
                                                out.println(request.getAttribute(ApplicationConstants.RESULT_MSG));
                                                }
                                               // Action 
                                                consultantForReqAction = "consultantForRequirement.action?objectId="+request.getAttribute("objectId");
                                                
                                               // out.println("consultantForReqAction---->"+consultantForReqAction);
                                                %>
                                                <tr>
                                                    <td>
                                                        <s:if test="%{#session.roleName =='Recruitment'}">
                                                            <!-- DataGrid for list all activities -->
                                                            <grd:dbgrid id="tblEmpLeaves" name="tblEmpLeaves" width="100" pageSize="15" 
                                                                        currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                        dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                
                                                                
                                                                <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                               imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                               addImage="../../includes/images/DBGrid/Add.png" addAction="<%=consultantForReqAction%>"/>
                                                                <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" />
                                                                
                                                                <grd:imagecolumn  headerText="Edit" width="5"  HAlign="center" 
                                                                                  imageSrc="../../includes/images/DBGrid/Edit.gif" 
                                                                                  linkUrl="getConsultantRequirement.action?consultId={Id}&objectId={Id1} " 
                                                                                  imageBorder="0" imageWidth="16" imageHeight="16" 
                                                                                  alterText="Click to edit" /> 
                                                                <grd:textcolumn dataField="Name" headerText="Name" width="15"/>
                                                                <grd:textcolumn dataField="TechRate" headerText="Tech Rating" dataFormat="" width="12" />
                                                                <grd:textcolumn dataField="RateNegotiated" headerText="Rate" dataFormat="" width="12" />
                                                                <grd:datecolumn dataField="StartDate" headerText="Date Available"  width="16"  dataFormat="MM-dd-yyyy" />
                                                                <grd:textcolumn dataField="Status"  headerText="Status"   width="20" />
                                                                <grd:ajaxpopup dataField="Comments" id="{Id}" linkText=":::" maxLength="15" 
                                                                               headerText="Comments" JSFunction="getComments" width="20" />
                                                                
                                                            </grd:dbgrid>
                                                            
                                                            <!-- these components are DBGrid Specific  -->
                                                            <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                            <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                            <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                                                            
                                                            <!-- <input id="txtDBId" TYPE="hidden" NAME="txtDBId" VALUE="0">
                                                        <input id="txtTRId"TYPE="hidden" NAME="txtTRId" VALUE="1">  -->
                                                        </s:if>
                                                        <s:else>
                                                            <!-- DataGrid for list all activities -->
                                                            <grd:dbgrid id="tblEmpLeaves" name="tblEmpLeaves" width="100" pageSize="15" 
                                                                        currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                        dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                
                                                                
                                                                <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                               imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"/>
                                                                <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" />
                                                                
                                                                <grd:imagecolumn  headerText="Edit" width="5"  HAlign="center" 
                                                                                  imageSrc="../../includes/images/DBGrid/Edit.gif" 
                                                                                  linkUrl="getConsultantRequirement.action?consultId={Id}" 
                                                                                  imageBorder="0" imageWidth="16" imageHeight="16" 
                                                                                  alterText="Click to edit" /> 
                                                                <grd:textcolumn dataField="TechRate" headerText="Tech Rating" dataFormat="" width="12" />
                                                                <grd:textcolumn dataField="RateNegotiated" headerText="Rate" dataFormat="" width="12" />
                                                                <grd:datecolumn dataField="StartDate" headerText="Date Available"  width="16"  dataFormat="MM-dd-yyyy" />
                                                                <grd:textcolumn dataField="Status"  headerText="Status"   width="20" />
                                                                <grd:ajaxpopup dataField="Comments" id="{Id}" linkText=":::" maxLength="15" 
                                                                               headerText="Comments" JSFunction="getComments" width="20" />
                                                                
                                                            </grd:dbgrid>
                                                            
                                                            <!-- these components are DBGrid Specific  -->
                                                            <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                            <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                            <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                                                            
                                                            <!-- <input id="txtDBId" TYPE="hidden" NAME="txtDBId" VALUE="0">
                                                        <input id="txtTRId"TYPE="hidden" NAME="txtTRId" VALUE="1">  -->
                                                        </s:else>
                                                    </td>
                                                </tr>
                                            </table>
                                        </form>
                                        
                                        <%
                                        }catch(Exception ex){
                                        out.println(ex.toString());
                                        }finally{
                                        if(connection!= null){
                                        connection.close();
                                        }
                                        }
                                        %>
                                        <%--   </sx:div> --%>
                                    </div>
                                    <%--</sx:tabbedpanel> --%>
                                </div>
                                 <script type="text/javascript">

var countries=new ddtabcontent("accountTabs")
countries.setpersist(false)
countries.setselectedClassTarget("link") //"link" or "linkparent"
countries.init()

                                </script>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <!--//END DATA RECORD : Record for LeftMenu and Body Content-->
            
            <!--//START FOOTER : Record for Footer Background and Content-->
            <tr class="footerBg">
                <td align="center"><s:include value="/includes/template/Footer.jsp"/>   </td>
            </tr>
            <!--//END FOOTER : Record for Footer Background and Content-->
        </table>
    </body>
</html>
