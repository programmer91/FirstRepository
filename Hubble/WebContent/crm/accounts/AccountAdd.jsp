<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.freeware.gridtag.*" %> 
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException, java.util.Date" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ page import="com.mss.mirage.crm.accounts.AccountVTO" %>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd" %>
<html>
    <head>
        <title>Hubble Portal :: ${title} Adding</title>
        <%-- <sx:head cache="true"/> --%>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
        <!-- Enable below option if AJAX usage To Retrieve Regions,Territoris  -->
        <%-- <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CrmAjax.js"/>"></script> --%>
        <!-- Disable below option if AJAX usage To Retrieve Regions,Territoris  -->
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CommonAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/RegionTerritoryUtil.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/StringUtility.js"/>"></script>
        <%--<script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AccountClientValidation.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/SoftwareClientValidation.js"/>"></script> >--%>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/StandardClientValidations.js?version=1.1"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <s:include value="/includes/template/headerScript.html"/>
    </head>
   <!-- <body onload="getStateData(),checkCountry(),getTerritories(frmAccountAdd, document.getElementById('region').value);" class="bodyGeneral" oncontextmenu="return false;"> -->
    <body class="bodyGeneral" oncontextmenu="return false;">
        <%!
            /* Declarations */
            Connection connection;
            String queryString;
            String currentAccountId;
            String strTmp;
            String strSortCol;
            String strSortOrd;
            String roleName;
            int intSortOrd = 0;
            int intCurr;
            boolean blnSortAsc = true;
        %>
        <!--//START MAIN TABLE : Table for template Structure-->
        <table class="templateTable" align="center" cellpadding="0" cellspacing="0">
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
                    <table class="innerTable" cellpadding="0" cellspacing="0">
                        <tr>
                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                            <td width="150px;" class="leftMenuBgColor" valign="top">
                                <s:include value="/includes/template/LeftMenu.jsp"/>
                            </td>
                            <!--//END DATA COLUMN : Coloumn for LeftMenu-->

                            <!--//START DATA COLUMN : Coloumn for Screen Content-->
                            <td width="850px" class="cellBorder" valign="top" style="padding-left:10px;padding-top:5px;">
                                <ul id="accountTabs" class="shadetabs" >
                                    <s:if test="#session.roleName != 'Vendor'">
                                        <li ><a href="#" class="selected" rel="accountsListTab">Add Account</a></li>
                                    </s:if>
                                    <s:else>
                                        <li ><a href="#" class="selected" rel="accountsListTab">Add Vendor</a></li>
                                    </s:else>
                                </ul>
                                <!--//START TABBED PANNEL : -->
                                <%--    <sx:tabbedpanel id="accountPannel" cssStyle="width: 840px; height: 400px;padding-left:10px;padding-top:5px;" doLayout="true"> --%>

                                <!--//START TAB : -->

                                <%--    <sx:div id="accountTab" label="%{title}"> --%>
                                <div  style="border:1px solid gray; width:840px;margin-bottom: 1em;">
                                    <%--    <sx:div id="accountsListTab" label="Accounts List" cssStyle="overflow:auto;"> --%>
                                    <div id="accountsListTab" class="tabcontent" >
                                        <s:form name="frmAccountAdd" action="addAccount" theme="simple" onsubmit="return (validateCountry());">

                                            <table cellpadding="1" cellspacing="1" border="0" width="100%">
                                                <tr align="right">
                                                    <td class="headerText" colspan="6">
                                                        <% if (request.getAttribute(ApplicationConstants.RESULT_MSG) != null) {
                                                                out.println(request.getAttribute(ApplicationConstants.RESULT_MSG));
                                                            }%>
                                                        <s:submit cssClass="buttonBg" value="Save" onclick="return (fieldValidator());"/>
                                                    </td>
                                                </tr>      

                                                <s:if test="#session.roleName != 'Vendor'">
                                                    <script type="text/javascript">
                                                    
                                                    </script>
                                                    <tr> 
                                                        <td class="fieldLabel">${title} Name:<font color="red" style="font-weight: bold;">&curren;</font></td>
                                                        <td colspan="3">
                                                            <%-- <s:textfield name="accountName" id="accountName" cssClass="inputTextBlueLargeAccount" onchange="fieldLengthValidator1(this);changeCase(this);replaceName(this);"  id="accountName" size="60" /> --%>
                                                            <s:textfield name="accountName" id="accountName" cssClass="inputTextBlueLargeAccount" onchange="lengthAndNamevalidator(this);changeCase(this);"  size="60" /> 
                                                        </td>
                                                        <s:if test="%{#session.roleName =='Recruitment' || #session.roleName =='Vendor'}">
                                                            <td class="fieldLabel"><s:if test="#session.roleName != 'Vendor'">Account</s:if> Type:</td>                  
                                                            <td><s:select  list="{'Vendor','1099'}" name="accountType"   cssClass="inputSelect"/></td> 
                                                        </s:if>
                                                        <s:else>
                                                            <td class="fieldLabel">Account Type:</td>                  
                                                            <td><s:select  list="accountTypeList" name="accountType"  cssClass="inputSelect"/></td> 
                                                        </s:else>
                                                    </tr>
                                                    <tr>
                                                        <td class="fieldLabel">URL:</td>
                                                        <td colspan="3"><s:textfield name="url" cssClass="inputTextBlueLargeAccount" value="" onchange="fieldLengthValidator(this);allSmalls(this);" id="url" size="50"/></td>
                                                        <td class="fieldLabel">Synonyms:</td>
                                                        <td><s:textfield name="synonyms" cssClass="inputTextBlue" value="" onchange="fieldLengthValidator(this);changeCase(this);"  id="synonyms" size="60"/></td>

                                                    </tr>
                                                    <tr>
                                                        <td class="fieldLabel">Phone:</td>
                                                        <td><s:textfield name="phone" id="phone" cssClass="inputTextBlue" value=""  size="16"/></td>
                                                        <td class="fieldLabel">Fax:</td>
                                                        <td><s:textfield name="fax" cssClass="inputTextBlue" value="" onchange="fieldLengthValidator(this);" id="fax" size="16"/></td>   

                                                    </tr>
                                                    <tr>
                                                        <td  class="fieldLabel">Status:</td>
                                                        <td><s:select list="accountStatusList" name="status" cssClass="inputSelect" /></td>
                                                        <td class="fieldLabel">Industry:</td>
                                                        <td><s:select list="industryList" name="industry" cssClass="inputSelect"/></td>
                                                        <!--Below Block is for AJAX Based Call to DB-->

                                                        <%-- <td class="fieldLabel">Region:</td>
                                                    <td><s:select 
                                                            list="regionList"  
                                                            name="region" 
                                                            id="region"
                                                            headerKey="-1"
                                                            headerValue="--Plase Select--"
                                                            cssClass="inputSelect"
                                                        onchange="getTerritoryData();"/></td> --%>
                                                        <!--Below Block is for Static Regions With out AJAX -->
                                                        <td class="fieldLabel">Region:</td>
                                                        <td><s:select list="{'Central','East','Enterprise','West'}" name="region" id="region" cssClass="inputSelect" value="%{currentAccount.region}" onchange="getTerritories(this.form, this.value);"/></td>

                                                    </tr>
                                                    <tr>

                                                        <td class="fieldLabel">Lead Source:</td>
                                                        <td><s:textfield name="leadSource" cssClass="inputTextBlue" value="" onchange="fieldLengthValidator(this);changeCase(this);" id="leadSource" size="50"/></td>
                                                        <td class="fieldLabel">Account Team:</td>
                                                        <td><s:textfield name="accountTeam" cssClass="inputTextBlue"  value="" onchange="fieldLengthValidator(this);changeCase(this);" id="accountTeam" size="20"/></td>
                                                        <!--Below Block is for AJAX Based Call to DB-->
                                                        <%--<td class="fieldLabel">Territory:</td>
                                                    <td><s:select list="territoryList" headerKey="-1" headerValue="--Please Select--" name="territory" id="territory" cssClass="inputSelect"/></td>--%>
                                                        <!--Below Block is for Static Territories With out AJAX -->
                                                        <td class="fieldLabel">Territory:</td>
                                                        <td><s:select list="{''}" headerKey="-1" headerValue="--Please Select--" name="territory" id="territory" cssClass="inputSelect"/></td>
                                                    </tr>

                                                    <tr>
                                                        <td class="fieldLabel">No. of Employees:</td>
                                                        <td><s:textfield name="noOfEmployees" cssClass="inputTextBlue" value="" onchange="fieldLengthValidator(this);" onblur="return validatenumber(this)" id="noOfEmployees" size="10"/></td>  
                                                        <td class="fieldLabel">Revenues:</td>
                                                        <td><s:textfield name="revenues" cssClass="inputTextBlue" value="" id="revenues" onblur="return validatenumber(this)"/><s:label cssClass="fieldLabel" value="(Millions)"/></td>
                                                        <td class="fieldLabel">Stock Symbol1:</td>
                                                        <td><s:textfield name="stockSymbol1" cssClass="inputTextBlue" value="" onchange="fieldLengthValidator(this);changeCase(this);" id="stockSymbol1" size="12"/></td>

                                                    </tr>

                                                    <tr>

                                                        <td class="fieldLabel">IT Budget:</td>
                                                        <td><s:textfield name="itBudget" cssClass="inputTextBlue" value="" onblur="return validatenumber(this)"/></td>
                                                        <td class="fieldLabel">Tax ID:</td>
                                                        <td><s:textfield name="taxId" cssClass="inputTextBlue" value="" onchange="fieldLengthValidator(this);" id="taxId" size="50"/></td> 
                                                        <%--<td class="fieldLabel">Stock Symbol2:</td>
                                                        <td><s:textfield name="stockSymbol2" cssClass="inputTextBlue" value="" onchange="fieldLengthValidator(this);changeCase(this);" id="stockSymbol2" size="12"/></td>
                                                        --%>

                                                    </tr>

                                                    <tr>

                                                        <td class="fieldLabel">IBM PPA No:</td>
                                                        <td><s:textfield name="ibmPPANo" cssClass="inputTextBlue" value="" onchange="fieldLengthValidator(this);" id="ibmPPANo" size="40"/></td>
                                                        <td class="fieldLabel">IBM Site No:</td>
                                                      <td><s:textfield name="ibmSiteNo" cssClass="inputTextBlue" value="" onchange="fieldLengthValidator(this);" id="ibmSiteNo" size="40"/></td> 
                                                        <td class="fieldLabel">PPA Renewal:</td>
                                                        <td> <s:textfield name="dateOfPPARenewal" cssClass="inputTextBlue" id="DateOfPPARenewal" />
                                                            <a href="javascript:cal1.popup();">
                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                     width="20" height="20" border="0"></a></td>

                                                    </tr>

                                                    <script type="text/javascript">
                                                        var cal1 = new CalendarTime(document.forms['frmAccountAdd'].elements['DateOfPPARenewal']);
                                                        cal1.year_scroll = true;
                                                        cal1.time_comp = false;
                                                    </script>
                                                </s:if>    

                                                <s:else>

                                                    <tr> 
                                                        <td class="fieldLabel">${title} Name:<font color="red" style="font-weight: bold;">&curren;</font></td>
                                                        <td colspan="3"><s:textfield name="accountName" cssClass="inputTextBlueLargeAccount" onchange="fieldLengthValidator(this);"  id="accountName" size="60" /> </td>
                                                        <s:if test="%{#session.roleName =='Recruitment' || #session.roleName =='Vendor'}">
                                                            <td class="fieldLabel">Vendor Type:</td>                  
                                                            <td><s:select  list="{'Vendor','1099'}" name="accountType"   cssClass="inputSelect"/></td> 
                                                        </s:if>
                                                        <s:else>
                                                            <td class="fieldLabel">Account Type:</td>                  
                                                            <td><s:select  list="accountTypeList" name="accountType"  cssClass="inputSelect"/></td> 
                                                        </s:else>
                                                    </tr>
                                                    <tr>


                                                        <td class="fieldLabel">URL:</td>
                                                        <td colspan="3"><s:textfield name="url" cssClass="inputTextBlueLargeAccount" value="" onchange="fieldLengthValidator(this);allSmalls(this);" id="url" size="50"/></td>
                                                        <td class="fieldLabel">Synonyms:</td>
                                                        <td><s:textfield name="synonyms" cssClass="inputTextBlue" value="" onchange="fieldLengthValidator(this);changeCase(this);"  id="synonyms" size="60"/></td>

                                                    </tr>
                                                    <tr>
                                                        <td class="fieldLabel">Phone:</td>
                                                        <td><s:textfield name="phone" cssClass="inputTextBlue" value="" onchange="return formatPhone(document.frmAccountAdd.phone.value);" id="phone" size="16"/></td>
                                                        <td class="fieldLabel">Fax:</td>
                                                        <td><s:textfield name="fax" cssClass="inputTextBlue" value="" onchange="fieldLengthValidator(this);" id="fax" size="16"/></td>   
                                                        <td  class="fieldLabel">Status:</td>
                                                        <td><s:select list="accountStatusList" name="status" cssClass="inputSelect" /></td>
                                                    </tr>

                                                    <tr>
                                                        <td class="fieldLabel">No. of Employees:</td>
                                                        <td><s:textfield name="noOfEmployees" cssClass="inputTextBlue" value="" onchange="fieldLengthValidator(this);" onblur="return validatenumber(this)" id="noOfEmployees" size="10"/></td>  
                                                        <td class="fieldLabel">Revenues:</td>
                                                        <td><s:textfield name="revenues" cssClass="inputTextBlue" value="" id="revenues" /></td>
                                                        <td class="fieldLabel">Tax ID:</td>
                                                        <td><s:textfield name="taxId" cssClass="inputTextBlue" value="" onchange="fieldLengthValidator(this);" id="taxId" size="50"/></td> 
                                                        <td><input type="hidden" name="accountTeam" cssClass="inputTextBlue"  value="" onchange="fieldLengthValidator(this);changeCase(this);" id="accountTeam" size="20"/></td>
                                                    </tr>

                                                </s:else>

                                                <!-- Account Priority -->

                                                <s:if test="#session.roleName == 'Admin'">
                                                <tr>
                                                    <td class="fieldLabel">
                                                        Main :
                                                    </td>
                                                    <td>
                                                        <s:textfield name="mainPriority" cssClass="inputTextBlue2" id="mainPriority" value="%{currentAccount.mainPriority}" onblur="return validatenumber(this)"/>
                                                        <span class="fieldLabel"> B2B :</span><s:textfield name="b2bPriority" cssClass="inputTextBlue2" id="b2bPriority" value="%{currentAccount.b2bPriority}" onblur="return validatenumber(this)"/>
                                                    </td>
                                                    <td class="fieldLabel">
                                                        BPM :
                                                    </td>
                                                    <td>

                                                        <s:textfield name="bpmPriority" cssClass="inputTextBlue2" id="bpmPriority" value="%{currentAccount.bpmPriority}" onblur="return validatenumber(this)"/>
                                                        <span class="fieldLabel">SAP :</span><s:textfield name="sapPriority" cssClass="inputTextBlue2" id="sapPriority" value="%{currentAccount.sapPriority}" onblur="return validatenumber(this)"/>
                                                    </td>
                                                    <td class="fieldLabel">
                                                        ECOM :
                                                    </td>
                                                    <td>


                                                        <s:textfield name="ecomPriority" cssClass="inputTextBlue2" id="ecomPriority" value="%{currentAccount.ecomPriority}" onblur="return validatenumber(this)"/>
                                                        <span class="fieldLabel">QA :</span><s:textfield name="qaPriority" cssClass="inputTextBlue2" id="qaPriority" value="%{currentAccount.qaPriority}" onblur="return validatenumber(this)"/>
                                                    </td>

                                                </tr>

                                                </s:if>
                                             <%--   <s:else>

                                                <tr>

                                                    <td class="fieldLabel">
                                                        B2B :   
                                                    </td>
                                                    <td>
                                                        <s:textfield name="b2bPriority" cssClass="inputTextBlue2" id="b2bPriority" value="%{currentAccount.b2bPriority}" onblur="return validatenumber(this)"/>
                                                        <span class="fieldLabel">BPM :</span> <s:textfield name="bpmPriority" cssClass="inputTextBlue2" id="bpmPriority" value="%{currentAccount.bpmPriority}" onblur="return validatenumber(this)"/>
                                                    </td>
                                                    <td class="fieldLabel">
                                                        SAP :
                                                    </td>
                                                    <td>
                                                        <s:textfield name="sapPriority" cssClass="inputTextBlue2" id="sapPriority" value="%{currentAccount.sapPriority}" onblur="return validatenumber(this)"/>

                                                        <span class="fieldLabel">ECOM :</span> <s:textfield name="ecomPriority" cssClass="inputTextBlue2" id="ecomPriority" value="%{currentAccount.ecomPriority}" onblur="return validatenumber(this)"/>

                                                    </td>
                                                    <td class="fieldLabel">
                                                        QA :
                                                    </td>
                                                    <td>
                                                        <s:textfield name="qaPriority" cssClass="inputTextBlue2" id="qaPriority" value="%{currentAccount.qaPriority}" onblur="return validatenumber(this)"/>
                                                    </td>
                                                </tr>

</s:else> --%>
                                                <tr>
                                                    <td class="fieldLabel" valign="top">Description:</td>
                                                    <td colspan="2" valign="top"><s:textarea cols="30" rows="9" name="description" cssClass="inputTextarea" value="" onchange="fieldLengthValidator(this);changeCase(this);" id="Description"/></td>
                                                    <td colspan="3" style="padding-left:2px;">
                                                        <s:include value="/includes/template/GeneralAddress.jsp"/>
                                                        <s:param name="countryList" value="countryList"/>
                                                        <s:param name="statesList" value="statesList"/>
                                                    </td>
                                                </tr>



                                                <tr>
                                                    <td colspan="6" align="center">

                                                    </td>
                                                </tr>
                                            </table>
                                        </s:form>
                                        <%--    </sx:div > --%>
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

                                <!--//START TABBED PANNEL : --> 
                                <%--    <sx:tabbedpanel id="accountListPannel" cssStyle="width: 840px; height: 330px;padding-left:10px;padding-top:5px;" doLayout="true"> --%>
                                <ul id="accountTabs1" class="shadetabs" >
                                    <s:if test="#session.roleName != 'Vendor'">
                                        <li ><a href="#" class="selected" rel="accountsListTab1" > Accounts List </a></li>
                                    </s:if>
                                    <s:else>
                                        <li ><a href="#" class="selected" rel="accountsListTab1" > Vendor List </a></li>
                                    </s:else>

                                </ul> 
                                <div  style="border:1px solid gray; width:840px; height: 330px; overflow:auto; margin-bottom: 1em;">
                                    <%--    <sx:div id="accountsListTab" label="Accounts List" cssStyle="overflow:auto;"> --%>
                                    <div id="accountsListTab1" class="tabcontent" >

                                        <!--//START TAB : -->
                                        <%--   <sx:div id="accountsListTab" label="%{title}s List" cssStyle="overflow:auto;"> --%>

                                        <%
                                            /* String Variable for storing current position of records in dbgrid*/
                                            strTmp = request.getParameter("txtCurr");

                                            intCurr = 1;

                                            if (strTmp != null) {
                                                intCurr = Integer.parseInt(strTmp);
                                            }

                                            /* Specifing Shorting Column */
                                            strSortCol = request.getParameter("Colname");

                                            if (strSortCol == null) {
                                                strSortCol = "AccountName";
                                            }

                                            strSortOrd = request.getParameter("txtSortAsc");
                                            if (strSortOrd == null) {
                                                strSortOrd = "ASC";
                                            }

                                            try {

                                                /* Getting DataSource using Service Locator */

                                                connection = ConnectionProvider.getInstance().getConnection();

                                                //Retrieving Users Rolename from Session Attributes.
                                                roleName = session.getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();

                                                /* Sql query for retrieving resultset from DataBase */
                                                queryString = null;


                                                if (!("Vendor".equalsIgnoreCase(roleName))) {
                                                    queryString = "Select Id,Name,Status,Industry,WebAddress,DateLastActivity,DateCreated,Region,Teritory,Phone";
                                                    queryString = queryString + " FROM tblCrmAccount ORDER BY DateCreated DESC LIMIT 40";
                                                } else {
                                                    queryString = "Select Id,Name,Status,Type,Industry,WebAddress,DateLastActivity,Region,Teritory,Phone";
                                                    queryString = queryString + " FROM tblCrmAccount WHERE Type in ('Vendor','1099') ORDER BY Name LIMIT 40";
                                                }

                                        %>
                                        <s:form action="" theme="simple" name="frmDBGrid" method="post">   

                                            <table cellpadding="0" cellspacing="0" width="95%">
                                                <tr align="right">
                                                    <td class="headerText">
                                                        <img alt="Home" 
                                                             src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" 
                                                             width="100%" 
                                                             height="13px" 
                                                             border="0">
                                                    </td>
                                                </tr>    
                                                <!---BEGIN:: DBGrid Specific ---->  

                                                <tr>
                                                    <td>
                                                        <div style="width:1000px;">
                                                            <s:if test="#session.roleName != 'Vendor'">

                                                                <grd:dbgrid id="tblAccountList" name="tblAccountList" width="100" pageSize="9" 
                                                                currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">

                                                                    <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                                   imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"/>

                                                                    <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" 
                                                                                    imageAscending="../../includes/images/DBGrid/ImgAsc.gif" 
                                                                                    imageDescending="../../includes/images/DBGrid/ImgDesc.gif"/>  
                                                                    <grd:anchorcolumn dataField="Name" 
                                                                                      headerText="${title} Name" 
                                                                                      linkUrl="getAccount.action?id={Id}" linkText="{Name}" width="10"/>

                                                                    <grd:textcolumn dataField="WebAddress"            headerText="URL" width="16"/>
                                                                    <grd:textcolumn dataField="Status"          headerText="Status" width="10"/>
                                                                    <grd:datecolumn dataField="DateCreated" headerText="Date Created" dataFormat="MM-dd-yyyy" width="10"/>
                                                                    <grd:datecolumn dataField="DateLastActivity" headerText="LastActivity" dataFormat="MM-dd-yyyy" width="10"/>
                                                                    <grd:textcolumn dataField="Industry"	headerText="Industry" width="15"/>



                                                                </grd:dbgrid>
                                                            </s:if>
                                                            <s:else>

                                                                <grd:dbgrid id="tblAccountList" name="tblAccountList" width="100" pageSize="9" 
                                                                currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">

                                                                    <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                                   imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"/>

                                                                    <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" 
                                                                                    imageAscending="../../includes/images/DBGrid/ImgAsc.gif" 
                                                                                    imageDescending="../../includes/images/DBGrid/ImgDesc.gif"/>  
                                                                    <grd:anchorcolumn dataField="Name" 
                                                                                      headerText="${title} Name" 
                                                                                      linkUrl="getAccount.action?id={Id}" linkText="{Name}" width="24"/>

                                                                    <grd:textcolumn dataField="Status"          headerText="Status" width="10"/>
                                                                    <%--   <grd:datecolumn dataField="DateCreated" headerText="Date Created" dataFormat="MM-dd-yyyy" width="10"/> --%>
                                                                    <grd:datecolumn dataField="DateLastActivity" headerText="LastActivity" dataFormat="MM-dd-yyyy" width="10"/>




                                                                </grd:dbgrid>

                                                            </s:else>
                                                            <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                            <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                            <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                                                        </div>
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
                                        <%--   </sx:div> --%>
                                        <%--
                                    <!--//END TAB : -->
                                    <s:div id="accountsSearchTab" label="Accounts Search" theme="ajax">
                                        <table cellpadding="1" cellspacing="1" width="200px">
                                            <tr>
                                                <td class="fieldLabel"> Name: </td>
                                                <td> <input type="text" name="txtName" id="txtName" class="inputTextBlue"></td>
                                                <td><input type="submit" name="btnPhone" value="OK" class="buttonBg"></td>
                                            </tr>
                                        </table>
                                    </s:div>
                                        --%>
                                        <%--  </sx:tabbedpanel> --%>
                                    </div>
                                </div>
                                <!--//END TABBED PANNEL : -->
                                <script type="text/javascript">
                                    var countries=new ddtabcontent("accountTabs1")
                                    countries.setpersist(false)
                                    countries.setselectedClassTarget("link") //"link" or "linkparent"
                                    countries.init()

                                </script>
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

<script type="text/javascript">
		$(window).load(function(){
		getStateData();
		checkCountry();
		getTerritories(frmAccountAdd, document.getElementById('region').value);
		});
		</script>

    </body>
</html>
