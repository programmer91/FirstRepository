    <%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ page import="com.freeware.gridtag.*" %> 
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd" %>
<html>
    <head>        
        <title>Hubble Organization Portal :: ${title} ListAll</title>
        <%--<sx:head cache="true"/> --%>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <%--<link rel="stylesheet" href="<s:url value="/includes/css/displaytag.css"/>">--%>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>   
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CurrentDateUtilplus2.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/employee/DateValidator.js"/>"></script>
        <!-- Enable below option if AJAX usage To Retrieve Regions,Territoris  -->
        <%-- <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CrmAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AccountSearchClientValidation.js"/>"></script>  --%>
        <!-- Disable below option if AJAX usage To Retrieve Regions,Territoris  -->
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/RegionTerritoryUtil.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/StringUtility.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/StandardClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
	<%--<script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmpStandardClientValidations.js"/>"></script>--%>
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
        String roleName;
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
                            <td width="850px" class="cellBorder" valign="top" style="padding: 10px">
                                
                                
                                <!--//START TABBED PANNEL : --> 
                                <%--   <sx:tabbedpanel id="accountListPannel" cssStyle="width: 840px; height: 500px;padding-left:10px;padding-top:5px;" doLayout="true"> --%>
                               <ul id="accountTabs" class="shadetabs" >
  
                                    <s:if test="#session.roleName == 'Vendor'">
                                         <% if(request.getParameter("accList")==null)
                                       {%>
                                       <li ><a href="#"  rel="accountsSearchTab">Vector Search</a></li>
                                     <li ><a href="#" class="selected" rel="accountsListTab"  > Vendor List </a></li>
                                    <%}else{%>
                                    <li ><a href="#" class="selected" rel="accountsSearchTab">Vendor Search</a></li>
                                      
                                    <% }%>
                                    </s:if>
                                    <s:else>

                                        <% if(request.getParameter("accList")==null)
                                       {%>
                                       <li ><a href="#"  rel="accountsSearchTab">Account Search</a></li>
                                     <li ><a href="#" class="selected" rel="accountsListTab"  > Accounts List </a></li>
                                    <%}else{%>
                                    <li ><a href="#" class="selected" rel="accountsSearchTab">Account Search</a></li>
                                      
                                    <% }%>
                                    </s:else>
                                </ul>
                                <div  style="border:1px solid gray; width:840px; height: 500px; overflow:auto; margin-bottom: 1em;">
                                    <!--//START TAB : -->
                                    <%--  <sx:div id="accountsListTab" label="%{title}s List" cssStyle="overflow:auto;"> --%>
                                     <% if(request.getParameter("accList")==null)
                                            {
                                                //System.out.println("list"+request.getParameter("list"));
                                            %>

                                    <div id="accountsListTab" class="tabcontent" >
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
                                            
                                            if(request.getAttribute("submitFrom") != null){
                                                submittedFrom = request.getAttribute("submitFrom").toString();
                                            }
                                            
                                            //Retrieving Users Rolename from Session Attributes.
                                            roleName = session.getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
                                            
                                            //Checking Whether User Role is Vendor Or not
                                            if(!("Vendor".equalsIgnoreCase(roleName))){
                                                queryString = session.getAttribute(ApplicationConstants.QS_ALL_ACC_LIST).toString();
                                            } else {
                                                queryString = session.getAttribute(ApplicationConstants.QS_ALL_VEND_LIST).toString();
                                            }
                                            out.println(queryString);
                                            //out.print(viewType);
                                            //out.print(submittedFrom);
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
                                                    
                                                    <%--<display:table name="accountsData" 
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
                                                            <s:if test="#session.roleName != 'Vendor'">
                                                                <grd:dbgrid id="tblAccountList" name="tblAccountList" width="100" pageSize="17" 
                                                                            currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                            dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                    
                                                                    <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                                   imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"/>
                                                                    
                                                                    <%--   <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" 
                                                                                imageAscending="../../includes/images/DBGrid/ImgAsc.gif" 
                                                                                imageDescending="../../includes/images/DBGrid/ImgDesc.gif"/>    
                                                                 <grd:imagecolumn headerText="Edit" width="4" HAlign="center" imageSrc="../../includes/images/DBGrid/newEdit_17x18.png"
                                                                                 linkUrl="getAccount.action?id={Id}" imageBorder="0"
                                                                                 imageWidth="16" imageHeight="16" alterText="Click to edit"></grd:imagecolumn> --%>
                                                                    <grd:anchorcolumn dataField="Name" headerText="${title} Name" linkUrl="getAccount.action?id={Id}&pri=All" linkText="{Name}" width="20"/>
                                                                    <grd:textcolumn dataField="Status"          headerText="Status" width="5"/>
                                                                    <grd:datecolumn dataField="MainPriority"          headerText="Priority" width="5"/>
                                                                    <grd:textcolumn dataField="WebAddress"             headerText="URL" width="15"/>
                                                                    <grd:textcolumn dataField="Phone"             headerText="Phone" width="10"/>
                                                                    <grd:datecolumn dataField="DateLastActivity" headerText="LastActivity" dataFormat="MM-dd-yyyy" width="10"/>
                                                                    <grd:textcolumn dataField="Industry"	headerText="Industry" width="20"/>
                                                                    <grd:textcolumn dataField="Teritory"        headerText="Teritory" width="10"/>
                                                                    <grd:textcolumn dataField="Region"	        headerText="Region" width="10"/>
                                                                    <grd:textcolumn dataField="State" headerText="State" width="10"/>
                                                                </grd:dbgrid>
                                                            </s:if>
                                                            <s:else>
                                                                <grd:dbgrid id="tblAccountList" name="tblAccountList" width="100" pageSize="17" 
                                                                            currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                            dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                    
                                                                    <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                                   imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"/>
                                                                    
                                                                    <%--   <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" 
                                                                                imageAscending="../../includes/images/DBGrid/ImgAsc.gif" 
                                                                                imageDescending="../../includes/images/DBGrid/ImgDesc.gif"/>    
                                                                 <grd:imagecolumn headerText="Edit" width="4" HAlign="center" imageSrc="../../includes/images/DBGrid/newEdit_17x18.png"
                                                                                 linkUrl="getAccount.action?id={Id}" imageBorder="0"
                                                                                 imageWidth="16" imageHeight="16" alterText="Click to edit"></grd:imagecolumn> --%>
                                                                    <grd:anchorcolumn dataField="Name" headerText="${title} Name" linkUrl="getAccount.action?id={Id}" linkText="{Name}" width="20"/>
                                                                    <grd:textcolumn dataField="Status"          headerText="Status" width="5"/>
                                                                    <grd:textcolumn dataField="WebAddress"             headerText="URL" width="10"/>
                                                                    <grd:textcolumn dataField="Phone"             headerText="Phone" width="10"/>
                                                                    <grd:datecolumn dataField="DateLastActivity" headerText="LastActivity" dataFormat="MM-dd-yyyy" width="10"/>
                                                                    
                                                                    
                                                                </grd:dbgrid>
                                                                
                                                            </s:else>
                                                            
                                                            
                                                            <input type="hidden" name="txtCurr" value="<%=intCurr%>">
                                                            <input type="hidden" id="txtSortCol" name="txtSortCol" value="<%=strSortCol%>">
                                                            <input type="hidden" id="txtSortAsc" name="txtSortAsc" value="<%=strSortOrd%>">
                                                            <%--<input type="hidden" id="viewType" name="viewType" value="<%=viewType%>"/>--%>
                                                            <input type="hidden" name="submitFrom" value="dbGrid">
                                                            
                                                            <s:hidden  name="lastActivityFrom" value="%{lastActivityFrom}"/>
                                                            <s:hidden  name="lastActivityTo" value="%{lastActivityTo}"/>
                                                            <s:hidden  name="accountTeam" value="%{accountTeam}"/>
                                                            <s:hidden  name="accountName" value="%{accountName}"/>
                                                            <s:hidden  name="status" value="%{status}"/>
                                                            <s:hidden  name="accountType" value="%{accountType}"/>
                                                            <s:hidden  name="industry" value="%{industry}"/>
                                                            <s:hidden  name="description" value="%{description}"/>
                                                            <s:hidden  name="territory" value="%{territory}"/>
                                                            <s:hidden  name="region" value="%{region}"/>
                                                            <s:hidden  name="taxId" value="%{taxId}"/>
                                                            <s:hidden  name="state" value="%{state}"/>
                                                            <s:hidden  name="zip" value="%{zip}"/>
                                                            <s:hidden  name="phone" value="%{phone}"/>
                                                            
							    <s:hidden  name="sap" value="%{sap}"/>
                                                            <s:hidden  name="mercator" value="%{mercator}"/>
                                                            <s:hidden  name="messageBroker" value="%{messageBroker}"/>
                                                            <s:hidden  name="gentran" value="%{gentran}"/>
                                                            <s:hidden  name="wps" value="%{wps}"/>
                                                            <s:hidden  name="commerce" value="%{commerce}"/>
                                                            <s:hidden  name="dataPower" value="%{dataPower}"/>
                                                            <s:hidden  name="ibmPortals" value="%{ibmPortals}"/>
                                                            
                                                            <s:hidden  name="b2bPriority" value="%{b2bPriority}"/>
                                                            <s:hidden  name="bpmPriority" value="%{bpmPriority}"/>
                                                            <s:hidden  name="sapPriority" value="%{sapPriority}"/>
                                                            <s:hidden  name="ecomPriority" value="%{ecomPriority}"/>
                                                            <s:hidden  name="qaPriority" value="%{qaPriority}"/>
                                                             <s:hidden  name="conatctFName" value="%{conatctFName}"/>
                                                                  <s:hidden  name="conatctLName" value="%{conatctLName}"/>
                                                                   <s:hidden  name="conatctAliasName" value="%{conatctAliasName}"/>
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
                                            out.println(ex.toString());
                                        }finally{
                                            if(connection!= null){
                                                connection.close();
                                                connection = null;
                                            }
                                        }
                                        %>
                                        <%--   </sx:div> --%>
                                    </div>
                                    <%}%>
                                    <!--//END TAB : -->
                                    
                                    
                                    <%--   <sx:div id="accountsSearchTab" label="%{title}s Search" theme="ajax"> --%>
                                    <div id="accountsSearchTab" class="tabcontent">
                                        
                                        <s:form name="frmSearch" action="searchSubmitAll" theme="simple">
                                            
                                            <table border="0" cellpadding="1" cellspacing="1" width="100%">
                                                <tr>
                                                    <td class="headerText" colspan="6" align="right">
                                                        <s:hidden name="submitFrom" value="SearchAll"/>
                                                        <s:hidden name="viewType" value="%{viewType}"/>
                                                         <input type="button" Class="buttonBg" value="Reset" onClick="this.form.reset();getTerritories(this.form,'');"/>
                                                        <s:submit cssClass="buttonBg" value="Search"/>
                                                    </td>
                                                </tr>
                                                
                                                <s:if test="#session.roleName != 'Vendor'">
                                                    <tr>                                                    
                                                        <td class="fieldLabel">Name:</td>
                                                        <td><s:textfield name="accountName" id="accountName" cssClass="inputTextBlueLarge" value="%{accountName}" onchange="fieldLengthValidator(this);changeCase(this);"/></td>
                                                        
                                                        <td  class="fieldLabel">Status:</td>
                                                        <td><s:select list="accountStatusList" name="status" id="status" headerKey="" headerValue="--Select--" cssClass="inputSelect" value="%{status}" /></td>
                                                        
                                                        <td class="fieldLabel">AccountType:</td>
                                                        <td><s:select list="accountTypeList" name="accountType" id="accountType" headerKey="" headerValue="--Select Type--" cssClass="inputSelect" value="%{accountType}"/></td>
                                                        
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td class="fieldLabel">Industry:</td>
                                                        <td><s:select list="industryList" name="industry" id="industry" headerKey="" headerValue="-- Select --" cssClass="inputSelect" value="%{industry}"/></td>
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
                                                       <%-- <td class="fieldLabel">Region:</td>
                                                        <td><s:select list="{'Central','East','Enterprise','West'}" name="region" id="region" headerKey="" headerValue="-- Select --" cssClass="inputSelect" value="%{region}" onchange="getTerritories(this.form, this.value);"/></td>
                                                        <td class="fieldLabel">Territory:</td>
                                                        <td><s:select list="{''}" headerKey="" headerValue="--Please Select--" name="territory" id="territory" cssClass="inputSelect" value="%{territory}"/></td>--%>
                                                        <td class="fieldLabel">Region:</td>
                                                        <td><s:select list="{'Central','East','Enterprise','West'}" name="region" id="region" headerKey="" headerValue="-- Select --" cssClass="inputSelect" value="%{region}" onchange="getTerritories(this.form, this.value);"/></td>
                                                        <td class="fieldLabel">Territory:</td>
                                                        <td><s:select list="{''}" headerKey="" headerValue="--Please Select--" name="territory" id="territory" cssClass="inputSelect" value="%{territory}"/></td>
                                                        
                                                        
                                                        <td class="fieldLabel"> TeamMember </td>
                                                        <td>
                                                            <s:select list="myTeamMembers" name="accountTeam" id="accountTeam" headerKey="" headerValue="--Select Team--" cssClass="inputSelect" value="%{accountTeam}" />
                                                        </td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td class="fieldLabel">Renewal Date:</td>
                                                        <td><s:textfield name="taxId" cssClass="inputTextBlue"  value="%{taxId}" onchange="checkDates(this);"/>
                                                             <a href="javascript:cal3.popup();">
                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                 width="20" height="20" border="0"></a> <font face="arial" size="1pt" color="red">(mm/dd/yyyy)</font>
                                                        </td>
                                                        <td class="fieldLabel">State:</td>
                                                        <td><s:textfield name="state" id="state" cssClass="inputTextBlue"  value="%{state}" onchange="" disabled="false"/>                                                        
                                                        </td>
                                                       <td class="fieldLabel">City: </td> 
                                                        <td><s:textfield name="city" id="city" cssClass="inputTextBlue" value="%{city}" onchange="fieldLengthValidator(this);" disabled="false"/></td>
                                                        </td>
                                                    </tr>
                                                    
                                                    <tr>
                                                    <td colspan="4"></td>
                                                    <td class="fieldLabel">ZIP: </td> 
                                                         <td><s:textfield name="zip" id="zip" cssClass="inputTextBlue" value="%{zip}" onchange="fieldLengthValidator(this);" disabled="false"/></td>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="fieldLabel">Last Activity From:</td>
                                                        <td><s:textfield name="lastActivityFrom" id="lastActivityFrom" cssClass="inputTextBlue" onchange="validateTimestamp(this);"/>                                                        
                                                            <a href="javascript:cal1.popup();">
                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                 width="20" height="20" border="0"></a>
                                                        </td>
                                                        <td class="fieldLabel">To:<FONT color="red" SIZE="0.5"><em>*</em></FONT></td>
                                                        <td>
                                                            <s:textfield name="lastActivityTo"  id="lastActivityTo" cssClass="inputTextBlue" onchange="validateTimestamp(this);"/>
                                                            <a href="javascript:cal2.popup();">
                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                 width="20" height="20" border="0"></a>
                                                        </td>                    
                                                        
                                               <%-- New Field        onchange="fieldLengthValidator(this);" --%>          
                                              <td class="fieldLabel">Phone: </td> 
                                              <td><s:textfield name="phone" id="phone" cssClass="inputTextBlue" value="%{phone}"/></td>
                                                        <%--  End --%>
                                                    </tr>
								 <tr>
                                                    <td class="fieldLabel" style="Display:block">  </td>
                                                    <td>
                                                        <span class="messageNote">(mm/dd/yyyy HH:mm:ss)</span>
                                                    </td>
                                                    <td class="fieldLabel" style="Display:block">  </td>
                                                    <td>
                                                        <span class="messageNote">(mm/dd/yyyy HH:mm:ss)</span>
                                                    </td>
                                                </tr>
                                                
                                                <!-- New Functionality for Account Search based on conatct name -->
                                                    <tr>
                                                      <td class="fieldLabel">Contact&nbsp;First&nbsp;Name:</td>
                                                      <td><s:textfield name="conatctFName" id="conatctFName" cssClass="inputTextBlue" value="%{conatctFName}" onchange="validateByAddressInALL(this);fieldLengthValidator(this);changeCase(this);"/></td>  
                                                       
                                                       <td class="fieldLabel">Contact&nbsp;Last&nbsp;Name:</td>
                                                      <td><s:textfield name="conatctLName" id="conatctLName" cssClass="inputTextBlue" value="%{conatctLsName}" onchange="validateByAddressInALL(this);fieldLengthValidator(this);changeCase(this);"/></td>  
                                                        <td class="fieldLabel">Contact&nbsp;Alias&nbsp;Name:</td>
                                                      <td><s:textfield name="conatctAliasName" id="conatctAliasName" cssClass="inputTextBlue" value="%{conatctAliasName}" /></td>  
                                                    </tr>
                                                    <!-- end -->
                                           <!-- Account Priority -->
                                                    
                                                    
                        <tr>
                            
                            <td class="fieldLabel">
                              B2B :   
                            </td>
                            <td>
                                <s:textfield name="b2bPriority" cssClass="inputTextBlue2" id="b2bPriority" value="%{b2bPriority}" onblur="return validatenumber(this)"/>
                                <span class="fieldLabel">BPM :</span> <s:textfield name="bpmPriority" cssClass="inputTextBlue2" id="bpmPriority" value="%{bpmPriority}" onblur="return validatenumber(this)"/>
                            </td>
                            <td class="fieldLabel">
                                SAP :
                            </td>
                            <td>
                                <s:textfield name="sapPriority" cssClass="inputTextBlue2" id="sapPriority" value="%{sapPriority}" onblur="return validatenumber(this)"/>
                                
                                <span class="fieldLabel">ECOM :</span> <s:textfield name="ecomPriority" cssClass="inputTextBlue2" id="ecomPriority" value="%{ecomPriority}" onblur="return validatenumber(this)"/>
                               
                            </td>
                            <td class="fieldLabel">
                                QA :
                            </td>
                            <td>
                                <s:textfield name="qaPriority" cssClass="inputTextBlue2" id="qaPriority" value="%{qaPriority}" onblur="return validatenumber(this)"/>
                            </td>
                        </tr>                
                                               <tr>
                                                        <td colspan="6">
                                                            <span class="messageNote">WildCard : * </span>
                                                        </td>
                                                    </tr>
                                                    
                                                    <tr>                                                                  
                                                        <td  class="fieldLabel">Mercator</td> 
                                                       
                                                        <td><s:checkbox  name="mercator" id="mercator"  fieldValue="true" theme="simple" value="%{mercator}" /></td>
                                                        <td  class="fieldLabel">Sap</td>
                                                        <td><s:checkbox  name="sap" id="sap"  fieldValue="true" theme="simple" value="%{sap}"/></td>
                                                        
                                                        <td  class="fieldLabel">MessageBroker</td>
                                                        <td><s:checkbox  name="messageBroker" id="messageBroker"  fieldValue="true" theme="simple" value="%{messageBroker}"/></td> 
                                                        
                                                    </tr> 
                                                    <tr>
                                                        <td  class="fieldLabel">Gentran</td>
                                                        <td><s:checkbox  name="gentran" id="gentran"  fieldValue="true" theme="simple" value="%{gentran}"/></td>
                                                        
                                                        <td  class="fieldLabel">Wps</td>
                                                        <td><s:checkbox  name="wps" id="wps"  fieldValue="true" theme="simple" value="%{wps}"/></td>
                                                        
                                                        <td  class="fieldLabel">Commerce</td> 
                                                        <td><s:checkbox  name="commerce" id="commerce"  fieldValue="true" theme="simple" value="%{commerce}"/></td>
                                                        
                                                    </tr>
                                                    <tr>
                                                        <td  class="fieldLabel">DataPower</td>
                                                        <td><s:checkbox  name="dataPower" id="dataPower"  fieldValue="true" theme="simple" value="%{dataPower}"/></td>
                                                        
                                                        <td  class="fieldLabel">IbmPortals</td>
                                                        <td><s:checkbox  name="ibmPortals" id="ibmPortals"  fieldValue="true" theme="simple" value="%{ibmPortals}"/></td>
                                                    </tr>


                                                    
                                                </s:if>
                                                <s:else>
                                                    
                                                    <tr>                                                    
                                                        <td class="fieldLabel">Name:</td>
                                                        <td><s:textfield name="accountName" id="accountName" cssClass="inputTextBlueLarge" value="%{accountName}" onchange="fieldLengthValidator(this);changeCase(this);"/></td>
                                                        
                                                        <td  class="fieldLabel">Status:</td>
                                                        <td><s:select list="accountStatusList" name="status" id="status" headerKey="" headerValue="--Select--" cssClass="inputSelect" value="%{status}" /></td>
                                                        
                                                        <td class="fieldLabel">Vendor Type:</td>
                                                        <td><s:select list="{'Vendor','1099'}" name="accountType" id="accountType" headerKey="" headerValue="--Select Type--" cssClass="inputSelect" value="%{accountType}"/></td>
                                                        
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td class="fieldLabel"> Description: </td> 
                                                        <td><s:textfield name="description" id="description" cssClass="inputTextBlue" value="%{description}" onchange="fieldLengthValidator(this);changeCase(this);"/></td>
                                                        <td class="fieldLabel"> TeamMember </td>
                                                        <td>
                                                            <s:select list="vendorTeamMap" name="accountTeam" id="accountTeam" headerKey="" headerValue="--Select Team--" cssClass="inputSelect" value="%{accountTeam}" />
                                                        </td>
                                                         <td class="fieldLabel"> Revenue: </td> 
                                                        <td><s:select list="{'<','>','=','>=','<='}" style="width:35px" name="operator" id="operators" cssClass="inputSelectSmall" value="%{operator}" />
                                                        <s:textfield name="revenues" id="revenue" cssClass="inputTextBlueSmall" value="%{revenues}" onchange="validatenumber(this);"/></td>
                                                    </tr>
                                                    
                                                    
                                                    
                                                    <tr>
                                                        <td class="fieldLabel">Renewal Date:</td>
                                                        <td><s:textfield name="taxId" cssClass="inputTextBlue"  value="%{taxId}" onchange="checkDates(this);"/>
                                                             <a href="javascript:cal3.popup();">
                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                 width="20" height="20" border="0"></a> <font face="arial" size="1pt" color="red">(mm/dd/yyyy)</font>

                                                        </td>
                                                        <td class="fieldLabel">State:</td>
                                                        <td><s:textfield name="state" id="state" cssClass="inputTextBlue"  value="%{state}" onchange=""/>                                                        
                                                        </td>
                                                        <td class="fieldLabel">ZIP: </td> 
                                                        <td><s:textfield name="zip" id="zip" cssClass="inputTextBlue" value="%{zip}" onchange="fieldLengthValidator(this);"/>
                                                        </td>    
                                                    </tr>
                                                    <tr>
                                                        <td class="fieldLabel">Last Activity From:</td>
                                                        <td><s:textfield name="lastActivityFrom" id="lastActivityFrom" cssClass="inputTextBlue" onchange="validateTimestamp(this);"/>                                                        
                                                            <a href="javascript:cal1.popup();">
                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                 width="20" height="20" border="0"></a>
                                                        </td>
                                                        <td class="fieldLabel">To:<FONT color="red" SIZE="0.5"><em>*</em></FONT></td>
                                                        <td>
                                                            <s:textfield name="lastActivityTo"  id="lastActivityTo" cssClass="inputTextBlue" onchange="validateTimestamp(this);"/>
                                                            <a href="javascript:cal2.popup();">
                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                 width="20" height="20" border="0"></a>
                                                        </td>                    
                                                        
                                                    </tr>
                                                     <tr>
                                                    <td class="fieldLabel" style="Display:block">  </td>
                                                    <td>
                                                        <span class="messageNote">(mm/dd/yyyy HH:mm:ss)</span>
                                                    </td>
                                                    <td class="fieldLabel" style="Display:block">  </td>
                                                    <td>
                                                        <span class="messageNote">(mm/dd/yyyy HH:mm:ss)</span>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td colspan="6">
                                                        <span class="messageNote">WildCard : * </span>
                                                    </td>
                                                </tr>
                                                    
                                                    
                                                </s:else>
                                               
						      

		
                                            </table>
                                            
                                        </s:form>
                                        <script type="text/JavaScript">
                                             var cal1 = new CalendarTime(document.forms['frmSearch'].elements['lastActivityFrom']);
				                 cal1.year_scroll = true;
				                 cal1.time_comp = true;
                                                   
                                             var cal2 = new CalendarTime(document.forms['frmSearch'].elements['lastActivityTo']);
				                 cal2.year_scroll = true;
				                 cal2.time_comp = true;     

						   var cal3 = new CalendarTime(document.forms['frmSearch'].elements['taxId']);
				                 cal3.year_scroll = true;
				                 cal3.time_comp = false;    
                                            
                                        </script>
                                        <%--  </sx:div> --%>
                                    </div>
                                    
                                    <!--//END TAB : -->
                                     
                                     
                                    <%--   </sx:tabbedpanel> --%>
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