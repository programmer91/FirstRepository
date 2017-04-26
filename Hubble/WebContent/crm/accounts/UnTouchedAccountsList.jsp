<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%-- <%@ taglib prefix="sx" uri="/struts-dojo-tags" %>--%>
<%@ page import="com.freeware.gridtag.*" %> 
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd" %>
<%--<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>--%>

<html>
    <head>
        
        <title>Hubble Organization Portal :: Untouched Accounts List</title>
    
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <%--<link rel="stylesheet" href="<s:url value="/includes/css/displaytag.css"/>">--%>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
        <!-- Enable below option if AJAX usage To Retrieve Regions,Territoris  -->
        <%-- <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CrmAjax.js"/>"></script> --%>
        <!-- Disable below option if AJAX usage To Retrieve Regions,Territoris  -->
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/RegionTerritoryUtil.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
         <script type="text/JavaScript" src="<s:url value="/includes/javascripts/StandardClientValidations.js?ver=1.1"/>"></script>
        <s:include value="/includes/template/headerScript.html"/>
        
    </head>
    <body class="bodyGeneral" oncontextmenu="return false;">
        
        <%!
        /* Declarations */
        Connection connection;
        String queryString;
        String currentAccountId;
        String strTmp;
        String userId;
        String strSortCol;
        String strSortOrd = "ASC";
        String submittedFrom;
        String accountSearchBy;
        String searchAction;
        boolean blnSortAsc = true;
        String viewType;
        //int intSortOrd = 0;
        int intCurr = 1;
        
        %>
        
        <!--//START MAIN TABLE : Table for template Structure-->
        <table class="templateTable1000x580" align="center" cellpadding="0" cellspacing="0">
            
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
                    <table class="innerTable1000x515" cellpadding="0" cellspacing="0">
                        <tr>
                            
                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                            <td width="150px;" class="leftMenuBgColor" valign="top">
                                <s:include value="/includes/template/LeftMenu.jsp"/>
                                
                            </td>
                            <!--//END DATA COLUMN : Coloumn for LeftMenu-->
                            
                            <!--//START DATA COLUMN : Coloumn for Screen Content-->
                            <td width="850px" class="cellBorder" valign="top" style="padding-left:10px;padding-top:5px;">
                                
                                <ul id="accountTabs" class="shadetabs" >
                                    <li ><a href="#" class="selected" rel="accountsListTab" >Untouched Accounts </a></li>
                                    <li ><a href="#" rel="accountsSearchTab">Untouched A/c Search</a></li>
                                </ul>
                                <!--//START TABBED PANNEL : --> 
                                <%--     <sx:tabbedpanel id="accountListPannel" cssStyle="width: 840px; height: 500px;padding-left:10px;padding-top:5px;" doLayout="true"> --%>
                                    
                                <!--//START TAB : -->
                                <div  style="border:1px solid gray; width:840px;height: 500px;overflow:auto; margin-bottom: 1em;">
                                    <%--  <sx:div id="accountsListTab" label="Accounts List" cssStyle="overflow:auto;"> --%>
                                    <div id="accountsListTab" class="tabcontent"   >
                                        
                                        <%
                                        
                                        
                                        
                                        strTmp = request.getParameter("txtCurr");
                                        if (strTmp != null){
                                            
                                            try {
                                                intCurr = Integer.parseInt(strTmp);
                                            } catch (NumberFormatException NFEx) {
                                                NFEx.printStackTrace();
                                            }
                                        }else{
                                            intCurr = 1;
                                        }
                                        
                                        strSortCol = "AccountName";
                                        
                                        try{
                                            
                                            /* Getting DataSource using Service Locator */
                                            
                                            connection = ConnectionProvider.getInstance().getConnection();
                                            
                                            /* Sql query for retrieving resultset from DataBase */
                                            queryString  = null;
                                            viewType = null;
                                            submittedFrom = null;
                                            userId = session.getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                                            if(request.getAttribute("accountSearchBy") != null){
                                                accountSearchBy = request.getAttribute("accountSearchBy").toString();
                                            }
                                            
                                            if(request.getAttribute("submitFrom") != null){
                                                submittedFrom = request.getAttribute("submitFrom").toString();
                                            }
                                            
                                            queryString = session.getAttribute(ApplicationConstants.QS_OTHER_ACC_LIST).toString();
                                            //  queryString="select Name from tblCrmAccount where DateModified <  DATE_SUB('2008-01-30', INTERVAL 30 DAY)";
                                            // pageContext.setAttribute("accountSearchBy",accountSearchBy);
                                            // out.print(accountSearchBy);
                                            //out.print(viewType);
                                            //out.print(submittedFrom);
                                             //out.println(queryString);
                                        %>
                                        
                                        
                                        <table cellpadding="0" cellspacing="0" width="100%">
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
                                                    
                                                    <%-- <display:table name="accountsData" 
                                                                       class="data" 
                                                                       pagesize="14" 
                                                                       decorator="com.mss.mirage.crm.accounts.AccountWrapper"
                                                                       requestURI="/crm/accounts/accountsList.action">
                                                            
                                                            <display:column property="nameLink" maxLength="15" title="AccountName"/>
                                                            <display:column property="status"/>
                                                            <display:column property="industry" maxLength="20"/>
                                                            <display:column property="url"/>
                                                            <display:column property="accountTeamName" title="AccountTeam"/>
                                                            <display:column property="region"/>
                                                            <display:column property="territory"/>
                                                            <display:column property="dateLastActivity"/>
                                                            
                                                        </display:table>
                                                        --%>
                                                        
                                                        
                                                    <div style="width:840px;">
                                                        
                                                        <form action="" method="post" name="frmDBGrid">  
                                                            <grd:dbgrid id="tblAccountList" name="tblAccountList" width="100" pageSize="18" 
                                                                        currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                        dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                
                                                                <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                               imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"/>
                                                                
                                                                <%--<grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" 
                                                                                imageAscending="../../includes/images/DBGrid/ImgAsc.gif" 
                                                                                imageDescending="../../includes/images/DBGrid/ImgDesc.gif"/>    
                                                                
                                                                 <grd:imagecolumn headerText="Edit" width="4" HAlign="center" imageSrc="../../includes/images/DBGrid/newEdit_17x18.png"
                                                                                 linkUrl="getAccount.action?id={AccountId}" imageBorder="0"
                                                                                 imageWidth="16" imageHeight="16" alterText="Click to edit"></grd:imagecolumn>--%>
                                                                
                                                                <grd:anchorcolumn dataField="AccountName" 
                                                                                  headerText="Account Name" 
                                                                                  linkUrl="getAccount.action?id={AccountId}" linkText="{AccountName}" width="20"/>
                                                                <grd:textcolumn dataField="Status"          headerText="Status" width="5"/>
                                                                <grd:textcolumn dataField="URL"             headerText="URL" width="15"/>
                                                                <grd:textcolumn dataField="Phone"             headerText="Phone" width="10"/>
                                                                <grd:datecolumn dataField="DateLastActivity" headerText="LastActivity" dataFormat="MM-dd-yyyy" width="10"/>
                                                                <grd:textcolumn dataField="Industry"	headerText="Industry" width="20"/>
                                                                <%--<grd:textcolumn dataField="AccountTeam"	headerText="Team" width="5"/>--%>
                                                                <grd:textcolumn dataField="Region"	        headerText="Region" width="10"/>
                                                                <grd:textcolumn dataField="Teritory"        headerText="Teritory" width="10"/>
                                                                <grd:textcolumn dataField="State" headerText="State" width="10"/>
                                                                
                                                                
                                                            </grd:dbgrid>
                                                            
                                                            <input type="hidden" name="txtCurr" value="<%=intCurr%>">
                                                            <input type="hidden" id="txtSortCol" name="txtSortCol" value="<%=strSortCol%>">
                                                            <input type="hidden" id="txtSortAsc" name="txtSortAsc" value="<%=strSortOrd%>">
                                                            <%--<input type="hidden" id="viewType" name="viewType" value="<%=viewType%>"/>--%>
                                                            <input type="hidden" name="submitFrom" value="dbGrid">
                                                            <s:hidden  name="operator" value="%{operator}"/>
                                                                                  <s:hidden  name="revenues" value="%{revenues}"/>
                                                                                   <s:hidden  name="city" value="%{city}"/>
                                                            
                                                        </form>
                                                    </div>
                                                </td>
                                            </tr>
                                        </table>                                
                                        
                                        
                                        <%
                                        connection.close();
                                        connection = null;
                                        }catch(Exception ex){
                                            ex.printStackTrace();
                                        }finally{
                                            if(connection!= null){
                                                connection.close();
                                                connection = null;
                                            }
                                        }
                                        %>
                                        <%--    </sx:div> --%>
                                        <!--//END TAB : -->
                                    
                                    </div>
                                    <div id="accountsSearchTab" class="selected" >
                                        
                                        <s:form name="frmSearch" action="UntouchedsearchSubmit" theme="simple">
                                            
                                            <table border="0" cellpadding="1" cellspacing="1" width="100%">
                                                <tr>
                                                    <td class="headerText" colspan="6" align="right">
                                                        <s:hidden name="submitFrom" value="SearchOther"/>
                                                        <input type="hidden" name="accountSearchBy" id="accountSearchBy" value="<%=accountSearchBy%>"/>
                                                        <s:hidden name="viewType" value="%{viewType}"/>
                                                        <s:submit cssClass="buttonBg" value="Search"/>
                                                      <%--  <s:if test="%{accountSearchRedirect == 'untouchedAccounts'}" >
                                                            <input type="hidden" name="accountSearchType" id="accountSearchType" value="untouchedAccounts"/> 
                                                        </s:if>
                                                        <s:else>
                                                            <input type="hidden" name="accountSearchType" id="accountSearchType" value="untouchedTeamAccounts"/> 
                                                        </s:else> --%>
                                                    </td>
                                                </tr>
                                                <tr>                                                    
                                                    <td class="fieldLabel">Name:</td>
                                                    <td><s:textfield name="accountName" id="accountName" cssClass="inputTextBlue" value="%{accountName}" onchange="fieldLengthValidator(this);changeCase(this);"/></td>
                                                    
                                                    <td  class="fieldLabel">Status:</td>
                                                    <td><s:select list="accountStatusList" name="status" id="status" headerKey="" headerValue="--Select--" cssClass="inputSelect" value="%{status}" /></td>
                                                    
                                                    <td class="fieldLabel">AccountType:</td>
                                                    <td><s:select list="accountTypeList" name="accountType" id="accountType" headerKey="" headerValue="--Select Type--" cssClass="inputSelect" value="%{accountType}"/></td>
                                                    
                                                </tr>
                                                
                                                <tr>
                                                    <td class="fieldLabel">Industry:</td>
                                                    <td ><s:select list="industryList" name="industry" id="industry" headerKey="" headerValue="-- Select --"   cssClass="inputSelect" value="%{industry}"/></td>
                                                    <td class="fieldLabel"> Revenue: </td> 
                                                        <td><s:select list="{'<','>','=','>=','<='}" style="width:35px" name="operator" id="operator" cssClass="inputSelectSmall" value="%{operator}" />
                                                        <s:textfield name="revenues" id="revenues" cssClass="inputTextBlueSmall" value="%{revenues}" onchange="validatenumber(this);"/></td>
                                                    <td class="fieldLabel"> Description: </td> 
                                                    <td><s:textfield name="description" id="description" cssClass="inputTextBlue" value="%{description}" onchange="fieldLengthValidator(this);changeCase(this);"/></td>
                                                </tr>
                                                
                                                <tr>
                                                    <!--Below Block is for AJAX Based Call to DB-->
                                                    <%--<td class="fieldLabel">Region:</td>
                                                    <td><s:select 
                                                            list="regionList" headerKey="" headerValue="-- Select --"  
                                                            name="region"
                                                            id="region"  
                                                            cssClass="inputSelect"
                                                            onchange="getTerritoryData();" value="%{region}"/>
                                                    </td>
                                                    
                                                    <td class="fieldLabel">Territory:</td>
                                                    <td><s:select list="territoryList" name="territory" id="territory" headerKey="" headerValue="--Select Region--" cssClass="inputSelect" value="%{territory}"/></td>--%>
                                                    <!--Below Block is for Static Regions and Territories With out AJAX -->
                                                    <td class="fieldLabel">Region:</td>
                                                    <td><s:select list="{'Central','East','Enterprise','West'}" name="region" id="region" headerKey="" headerValue="-- Select --" cssClass="inputSelect" value="%{region}" onchange="getTerritories(this.form, this.value);"/></td>
                                                    <td class="fieldLabel">Territory:</td>
                                                    <td><s:select list="{''}" headerKey="" headerValue="--Please Select--" name="territory" id="territory" cssClass="inputSelect" value="%{territory}"/></td>
                                                    
                                                    <td class="fieldLabel"> TeamMember </td>
                                                    <td>
                                                        <%
                                                        if(session.getAttribute(ApplicationConstants.UNTOUCH_SEARCH) != null){
                                            searchAction = session.getAttribute(ApplicationConstants.UNTOUCH_SEARCH).toString();
                                                        }
                                        if(searchAction.equalsIgnoreCase("untouchedAccounts")){
                                                        %>
                                                        <s:select list="myTeamMembers" name="accountTeam" id="accountTeam" headerKey="" headerValue="--Select Team--" cssClass="inputSelect" value="%{accountTeam}" disabled="true"/>
                                                        <%}else if(searchAction.equalsIgnoreCase("untouchedTeamAccounts")){%>
                                                        <s:select list="myTeamMembers" name="accountTeam" id="accountTeam" headerKey="" headerValue="--Select Team--" cssClass="inputSelect" value="%{accountTeam}" disabled="false"/>
                                                        <%}%>
                                                       <%-- <s:if test="%{accountSearchRedirect == 'untouchedAccounts'}" >
                                                             <s:select list="myTeamMembers" name="accountTeam" id="accountTeam" headerKey="" headerValue="--Select Team--" cssClass="inputSelect" value="%{accountTeam}" disabled="true"/>   
                                                            </s:if>
                                                            <s:else>
                                                             <s:select list="myTeamMembers" name="accountTeam" id="accountTeam" headerKey="" headerValue="--Select Team--" cssClass="inputSelect" value="%{accountTeam}" disabled="false"/>   
                                                            </s:else>--%>
                                                    </td>
                                                </tr>
                                                
                                                <tr>
                                                    <td class="fieldLabel">Renewal Date:</td>
                                                    <td><s:textfield name="taxId" cssClass="inputTextBlue"  value="%{taxId}" onchange=""/>
                                                        <font face="arial" size="2pt" color="red">(mm-dd)</font>
                                                    </td>
                                                    
                                                     <td class="fieldLabel">State:</td>
                                                    <td colspan=""><s:textfield name="state" cssClass="inputTextBlue"  value="%{state}" onchange=""/>                                                        
                                                    </td>
                                                    <td class="fieldLabel">City: </td> 
                                                        <td><s:textfield name="city" id="city" cssClass="inputTextBlue" value="%{city}" onchange="fieldLengthValidator(this);" disabled="false"/></td>
                                                        </td>
                                                </tr>
                                                
                                                <tr>
                                                    <td colspan="6">
                                                        <span class="messageNote">WildCard : * </span>
                                                    </td>
                                                </tr>
                                                
                                            </table>
                                            
                                        </s:form>
                                        
                                        <%--    </sx:div> --%>
                                    </div>
                                    
                                    
                                    <!--//END TAB : -->
                                </div>           
                                <%--    </sx:tabbedpanel> --%>
                                <!--//END TABBED PANNEL : -->
                                <script type="text/javascript">

var countries=new ddtabcontent("accountTabs")
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
        
        
        
    </body>
    
</html>