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
        <title>Hubble Organization Portal :: Customer Project Details</title>
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
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmployeeAjax.js"/>"></script>

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
        String currentAccountId;
        String customerName;
        int currentProjectId;
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
                                <li ><a href="#" class="selected" rel="employeeSearchTab"  >Customer Project Details</a></li>
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
                                                <% if (request.getAttribute(ApplicationConstants.RESULT_MSG) != null) {
                                                        out.println(request.getAttribute(ApplicationConstants.RESULT_MSG));
                                                    }

                                                    if (request.getAttribute("submitFrom") != null) {
                                                        submittedFrom = request.getAttribute("submitFrom").toString();
                                                    }

                                                    if (!"dbGrid".equalsIgnoreCase(submittedFrom)) {
                                                        searchSubmitValue = submittedFrom;
                                                    }

                                                %>

                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <s:form name="frmEmpSearch" action="" theme="simple">
                                                     <table cellpadding="1" cellspacing="1" border="0" style="margin-left: 6.9%">
                                                        <tr> 
                                                            <td class="fieldLabel" align="left">Customer Name :</td>
                                                            <td style="font-size: 12px;font-style: normal;color:green "><s:label><s:property  value="customerName" /></s:label></td>

                                                            
                                                        </tr>
                                                    </table>

                                                </s:form>
                                            </td>
                                        </tr>

                                        <tr>
                                            <td>
                                                <s:form name="frmEmpSearch" action="getCustomerProjectsDetailsList" theme="simple">
                                                    <table cellpadding="1" cellspacing="1" border="0" width="750px" >
                                                        <tr> 
                                                            <s:hidden name="accountId" value="%{accountId}"/>
                                                            <s:hidden name="customerName" id="customerName" value="%{customerName}"/>
                                                            <td class="fieldLabel" align="center">Project Name :</td>
                                                            <td ><s:textfield name="prjName" cssClass="inputTextBlueLarge" style="width:100%" value="%{prjName}"  onchange="fieldLengthValidatorforProject(this);"  id="projectName" size="25" /></td>                                                     

                                                            <td class="fieldLabel" align="center">Status :</td>
                                                            <td class="inputOptionText"><s:select name="status" id="projectStatus" value="%{status}" list="{'Active','Completed','Terminated','Initiated'}" cssClass="inputSelectSmall1" /></td> 
                                                            <td>
                                                                <s:submit type="button" value="Search" cssClass="buttonBg" />
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
                                                    strTmp = request.getParameter("txtProjTeamCurr");

                                                    intCurr = 1;

                                                    if (strTmp != null) {
                                                        intCurr = Integer.parseInt(strTmp);
                                                    }

                                                    /* Specifing Shorting Column */
                                                    strSortCol = request.getParameter("txtProjSortCol");

                                                    if (strSortCol == null) {
                                                        strSortCol = "Fname";
                                                    }

                                                    strSortOrd = request.getParameter("txtProjSortAsc");
                                                    if (strSortOrd == null) {
                                                        strSortOrd = "ASC";
                                                    }


                                                    try {
                                                        if (request.getAttribute("accountId") != null) {
                                                            currentAccountId = (String) request.getAttribute("accountId");
                                                            customerName = (String) request.getAttribute("customerName");
                                                            // currentProjectId=(Integer)request.getAttribute("projectId");
                                                            //  System.out.println("currentProjectId..."+currentProjectId);
                                                        }


                                                        /* Getting DataSource using Service Locator */
                                                        connection = ConnectionProvider.getInstance().getConnection();

                                                        /* Sql query for retrieving resultset from DataBase */
                                                        queryString = null;
                                                        queryString = session.getAttribute(ApplicationConstants.QS_CUSTOMER_PROJECTS_LIST).toString();
                                                        String heirarchyAction = "../employee/getAddProject.action?accountId=" + currentAccountId + "&accountName=" + customerName+"&backFlag=2";
                                                        // out.println(queryString);

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


                                                                      

                                                                    <grd:anchorcolumn dataField="ProjectName" linkUrl="getCustomerProjectDetails.action?projectId={Id}&accountId={CustomerId}&accountName={Name}&backFlag=2" linkText="{ProjectName}"  headerText="ProjectName" width="18"/>
                                                                    <grd:datecolumn dataField="TotalResources" headerText="TotalResources" width="20"/>
                                                                    <grd:textcolumn dataField="STATUS" headerText="Status" HAlign="left" width="18"/>
                                                                    <grd:textcolumn dataField="PMO" headerText="PMO" HAlign="left" width="18"/>
                                                                    <grd:textcolumn dataField="preSalesMgr" headerText="PreSalesMgr" HAlign="left" width="18"/>
                                                                    <%-- <grd:textcolumn dataField="ResourceTitle" headerText="Role" width="12"/> --%>
                                                                    <grd:datecolumn dataField="ProjectStartDate" headerText="StartDate"  width="10" dataFormat="MM/dd/yyyy"/>
                                                                    <grd:datecolumn dataField="ProjectEndDate" headerText="EndDate"  width="10" dataFormat="MM/dd/yyyy"/>
                                                                <%-- <grd:imagecolumn headerText="IsCompleted" width="4" HAlign="center" imageSrc="../includes/images/go_21x21.gif"
                                                                                  imageWidth="18" imageHeight="16" alterText="Click to Inactive" linkUrl="javascript:doInactiveCustomerProject({Id},{CustomerId},'{ProjectEndDate}','{Comments}')"></grd:imagecolumn> --%>
                                      
                                                                </grd:dbgrid>

                                                                <input TYPE="hidden" NAME="txtProjTeamCurr" VALUE="<%=intCurr%>">
                                                                <input TYPE="hidden" NAME="txtProjSortCol" VALUE="<%=strSortCol%>">
                                                                <input TYPE="hidden" NAME="txtProjSortAsc" VALUE="<%=strSortOrd%>">
                                                                <input TYPE="hidden" NAME="txtAttachCurr1" VALUE="">
                                                                <input TYPE="hidden" NAME="submitFrom" VALUE="dbGrid">
                                                                
                                                               

                                                            </td>
                                                        </tr>
                                                    </table>                                

                                                </s:form>

                                                <%
                                                        connection.close();
                                                        connection = null;
                                                    } catch (Exception ex) {
                                                        out.println(ex.toString());
                                                    } finally {
                                                        if (connection != null) {
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
