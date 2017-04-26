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
        <title>Hubble Organization Portal :: ${title} Details</title>
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
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/RegionTerritoryUtil.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CommonAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/StringUtility.js"/>"></script>
        <%--  <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AjaxRequirement.js"/>"></script> --%>


        <%--<script type="text/JavaScript" src="<s:url value="/includes/javascripts/ajax/ajaxTabs.js"/>"></script> --%>
        <%--<script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
            <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AccountDetailsClientValidation.js"/>"></script>   
            <script type="text/JavaScript" src="<s:url value="/includes/javascripts/SoftwareClientValidation.js"/>"></script>   --%>

        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CustomerPopup.js"/>"></script> 
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/StandardClientValidations.js?version=1.1"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DbGridForOppertunity.js"/>"></script>
        
        <script>
            function AccountTeamDetailsPopup(url) {
                //newwindow=window.open(url,'name','height=350,width=200,top=200,left=250');
                newwindow=window.open(url,'name','height=350,width=300,top=200,left=250');
                if (window.focus) {newwindow.focus()}
            }
            
             function getStatusHeader(){
    //alert("hi");
    document.getElementById("requirementstatus").value="open";
}
function enableConatctEnter(e) {
    var keynum;
    var keychar;
    var numcheck;
    
    if(window.event) {
        keynum = e.keyCode;
    }
    else if(e.which) // Netscape/Firefox/Opera
    {
        keynum = e.which;
    }
    try{
        if(keynum==13){
            getContactsList();
            return false;
        }
    }catch(e){
        alert("Error"+e);
    }
};
        </script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBRecGrid.js"/>"></script>
        <s:include value="/includes/template/headerScript.html"/>
    </head>


    <%-- <body class="bodyGeneral" oncontextmenu="return false;" onload="getContactsList();checkCountry(); getActivityList();getAllActivityList();"> --%>
    
<!-- <body class="bodyGeneral" oncontextmenu="return false;" onload="getContactsList();checkCountry(); getActivityList(); getLeadDetailsList();getOpportunityList();getStatusHeader();getRequirementList();"> -->
<body class="bodyGeneral" oncontextmenu="return false;">
<script type="text/JavaScript" src="<s:url value="/includes/javascripts/wz_tooltip.js"/>"></script>
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


        <!--//START MAIN TABLE : Table for template Structure-->
        <table  class="templateTable1000x580" align="center" cellpadding="0" cellspacing="0">

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
<s:hidden name="roleName" id="roleName" value="%{#session.roleName}"/>

                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->

                            <!--//START DATA COLUMN : Coloumn for Screen Content-->
                            <td width="850px" class="cellBorder" valign="top" style="padding-left:10px;padding-top:5px;">
                                <!--//START TABBED PANNEL : -->
                                <ul id="accountTabs" class="shadetabs" >
                                    <li><a href="#" rel="accountTab" class="selected" > ${title} </a></li>
                                    <%-- Commented for Vendor <s:if test="#session.roleName != 'Vendor'"> --%>
                                    <li><a href="#" rel="SoftwareTab">Software</a></li>
                                    <li><a href="#" rel="AccountTeamTab">${title} Team</a></li>
                                    <%-- Commented for Vendor </s:if> --%>

                                </ul>
                                <%--     <sx:tabbedpanel id="accountsPannel" cssStyle="width: 840px; padding-left:10px;padding-top:5px;" doLayout="false" useSelectedTabCookie="true" > --%>
                                <div  style="border:1px solid gray; width:840px; margin-bottom: 1em;overflow:auto">   
                                    <!--//START TAB : -->
                                    <%--   <sx:div id="accountTab" label="%{title}"> --%>
                                    <div id="accountTab" class="tabcontent" >
                                        <s:form name="frmAccForm" action="editAccount" theme="simple" onsubmit="return validateCountry();">
                                            <table cellpadding="1"  cellspacing="1" border="0" width="100%">
                                                <tr align="right">
                                                    <td class="headerText" colspan="6">
                                                        <% if (request.getAttribute(ApplicationConstants.RESULT_MSG) != null) {
                                                                out.println(request.getAttribute(ApplicationConstants.RESULT_MSG));
                                                            }%>
                                                        <s:if test="%{activitySummaryFlag == 1}">
                                                            <a href="<s:url action="../../crm/activities/myActivitiesInfo"></s:url>?activitySummaryFlag=<s:property value="activitySummaryFlag"/>&dashBoardStartDate=<s:property value="dashBoardStartDate"/>&dashBoardEndDate=<s:property value="dashBoardEndDate"/>" style="align:center;">
                                                                    <img alt="Back to List"
                                                                         src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                    width="66px" 
                                                                    height="19px"
                                                                    border="0" align="bottom"></a>
                                                            </s:if> 
                                                            <s:elseif test="%{activitySummaryFlag == 2}">
                                                            <a href="<s:url action="../../crm/activities/teamActivitiesInfo"></s:url>?activitySummaryFlag=<s:property value="activitySummaryFlag"/>&dashBoardStartDate=<s:property value="dashBoardStartDate"/>&dashBoardEndDate=<s:property value="dashBoardEndDate"/>&empId=<s:property value="empId"/>&activityType=<s:property value="activityType"/>" style="align:center;">
                                                                    <img alt="Back to List"
                                                                         src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                    width="66px" 
                                                                    height="19px"
                                                                    border="0" align="bottom"></a>

                                                        </s:elseif>
                                                        <!--                                        activitySummaryFlag == 2 for account contacts-->
                                                        <s:elseif test="%{activitySummaryFlag == 3}">
                                                            <a href="<s:url action="../../crm/activities/accountContactsSearch"></s:url>?dashBoardStartDate=<s:property value="dashBoardStartDate"/>&dashBoardEndDate=<s:property value="dashBoardEndDate"/>&empId=<s:property value="empId"/>&contactStatus=<s:property value="contactStatus"/>" style="align:center;">
                                                                    <img alt="Back to List"
                                                                         src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                    width="66px" 
                                                                    height="19px"
                                                                    border="0" align="bottom"></a>

                                                        </s:elseif><s:elseif test="%{backToFlag == 'Bde'}">
                                                             <a href="<s:url value="../../marketing/getAccountDetailsForInsideSalesStatus.action?backToFlag=Yes"/>" style="align:center;">
                                                                <img alt="Back to List"
                                                                     src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                     width="66px" 
                                                                     height="19px"
                                                                     border="0" align="bottom"></a>

                                                        </s:elseif>
                                                        <s:else>

                                                            <a href="<s:url value="crmBackToList.action"/>" style="align:center;">
                                                                <img alt="Back to List"
                                                                     src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                     width="66px" 
                                                                     height="19px"
                                                                     border="0" align="bottom"></a>
                                                            </s:else>
                                                        &nbsp;&nbsp;
                                                        <s:submit cssClass="buttonBg" value="Save"/>
                                                        <s:hidden name="id" value="%{currentAccount.id}"/>
                                                        <s:hidden name="primaryAddressId" value="%{currentAccount.primaryAddressId}"/>
                                                        <s:hidden  name="activitySummaryFlag" value="%{activitySummaryFlag}"/>
                                                        <s:hidden  name="dashBoardStartDate" value="%{dashBoardStartDate}"/>
                                                        <s:hidden  name="dashBoardEndDate" value="%{dashBoardEndDate}"/>
                                                        <s:hidden  name="contactStatus" value="%{contactStatus}"/>
                                                        <s:hidden  name="empId" value="%{empId}"/>
                                                        <s:hidden  name="activityType" value="%{activityType}"/>

                                                    </td>
                                                </tr>


                                                <s:if test="#session.roleName != 'Vendor'">

                                                    <tr>
                                                        <td class="fieldLabel">${title} Name:</td>
                                                        <s:if test="#session.roleName != 'Admin' && #session.roleName != 'Marketing'">
                                                            <td colspan="3"><s:textfield name="accountName" id="accountName" cssClass="inputTextBlueLargeAccount" value="%{currentAccount.accountName}" readonly="true" onchange="fieldLengthValidator(this);changeCase(this);" /> </td> 
                                                        </s:if>
                                                        <s:else>
                                                            <td colspan="3"><s:textfield name="accountName" id="accountName" cssClass="inputTextBlueLargeAccount" value="%{currentAccount.accountName}" readonly="false" onchange="fieldLengthValidator(this);changeCase(this);" /> </td> 
                                                        </s:else>


                                                        <td class="fieldLabel"> ${title} Type:</td>
                                                        <td><s:select  list="accountTypeList" name="accountType" id="accountType"  cssClass="inputSelect" value="%{currentAccount.accountType}"/></td> 
                                                    </tr>                                                
                                                    <tr>                                                    
                                                        <td class="fieldLabel">URL:</td>
                                                        <td colspan="3"><s:textfield name="url" id="url" cssClass="inputTextBlueLargeAccount"  value="%{currentAccount.url}"  onchange="fieldLengthValidator(this);allSmalls(this);"/></td>
                                                        <td class="fieldLabel">Synonyms:</td>
                                                        <td><s:textfield name="synonyms" id="synonyms" cssClass="inputTextBlue"  value="%{currentAccount.synonyms}" onchange="fieldLengthValidator(this);changeCase(this);"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="fieldLabel">Phone:</td>
                                                        <td><s:textfield name="phone" id="phone" cssClass="inputTextBlue"  value="%{currentAccount.phone}" onchange="return formatPhone(this);" onblur="return validatenumber(this)"/></td>
                                                        <td class="fieldLabel">Fax:</td>
                                                        <td><s:textfield name="fax" id="fax" cssClass="inputTextBlue"  value="%{currentAccount.fax}" onchange="fieldLengthValidator(this);" onblur="return validatenumber(this)"/></td>   
                                                        <td  class="fieldLabel">Status:</td>
                                                        <td><s:select list="accountStatusList" name="status" id="status" cssClass="inputSelect" value="%{currentAccount.status}"/></td>
                                                    </tr>

                                                    <tr>
                                                        <td class="fieldLabel">Industry:</td>
                                                        <td><s:select list="industryList" name="industry" id="industry" cssClass="inputSelect" value="%{currentAccount.industry}"/></td>
                                                        <!--Below Block is for AJAX Based Call to DB-->
                                                        <%-- <td class="fieldLabel">Region:</td>
                                                                    <td><s:select list="regionList" name="region" id="region" cssClass="inputSelect" value="%{currentAccount.region}" onchange="getTerritoryData();"/></td>
                                                                    <td class="fieldLabel">Territory:</td>
                                                                    <td><s:select list="territoryList" name="territory" id="territory" cssClass="inputSelect" value="%{currentAccount.territory}"/></td> --%>
                                                        <!--Below Block is for Static Regions and Territories With out AJAX -->
                                                        <td class="fieldLabel">Region:</td>
                                                        <td><s:select list="{'Central','East','Enterprise','West'}" name="region" id="region" cssClass="inputSelect" value="%{currentAccount.region}" onchange="getTerritories(this.form, this.value);"/></td>
                                                        <td class="fieldLabel">Territory:</td>
                                                        <td><select name="territory" id="territory" class="inputSelect"><option value="<s:property value="%{currentAccount.territory}"/>"><s:property value="%{currentAccount.territory}"/></option></select></td> 
                                                    </tr>
                                                    <tr>
                                                        <td class="fieldLabel">Lead Source:</td>
                                                        <td><s:textfield name="leadSource" id="leadSource" cssClass="inputTextBlue"  value="%{currentAccount.leadSource}" onchange="fieldLengthValidator(this);changeCase(this);"/></td>
                                                        <td class="fieldLabel">No. of Employees:</td>
                                                        <td><s:textfield name="noOfEmployees" id="noOfEmployees" cssClass="inputTextBlue"  value="%{currentAccount.noOfEmployees}" onchange="fieldLengthValidator(this);" onblur="return validatenumber(this)"/></td>  
                                                        <td class="fieldLabel">Revenues:</td>
                                                        <td><s:textfield name="revenues" id="revenues" cssClass="inputTextBlue"  value="%{currentAccount.revenues}" onblur="return validatenumber(this)"/><s:label cssClass="fieldLabel" value="(Millions)"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="fieldLabel">IT Budget:</td>
                                                        <td><s:textfield name="itBudget" id="itBudget" cssClass="inputTextBlue"  value="%{currentAccount.itBudget}" onblur="return validatenumber(this)"/></td>
                                                        <td class="fieldLabel">Tax ID:</td>
                                                        <td><s:textfield name="taxId" id="taxId" cssClass="inputTextBlue"  value="%{currentAccount.taxId}" onchange="fieldLengthValidator(this);"/></td> 
                                                        <td class="fieldLabel">Stock Symbol1:</td>
                                                        <td><s:textfield name="stockSymbol1" id="stockSymbol1" cssClass="inputTextBlue"  value="%{currentAccount.stockSymbol1}" onchange="fieldLengthValidator(this);changeCase(this);"/></td>
                                                    </tr>
                                                    <tr>


                                                        <td class="fieldLabel">IBM PPA No:</td>
                                                         <td><s:textfield name="ibmPPANo" cssClass="inputTextBlue"  onchange="fieldLengthValidator(this);" size="40" id="ibmPPANo"  value="%{currentAccount.ibmPPANo}" theme="simple"/></td>
                                                        <td class="fieldLabel">IBM Site No:</td>
                                                        <td><s:textfield name="ibmSiteNo" cssClass="inputTextBlue"  onchange="fieldLengthValidator(this);" id="ibmSiteNo" size="40"  value="%{currentAccount.ibmSiteNo}"/></td> 
                                                        <td class="fieldLabel">PPA Renewal:</td>
                                                        <td> <s:textfield name="dateOfPPARenewal" cssClass="inputTextBlue" id="DateOfPPARenewal" value="%{currentAccount.dateOfPPARenewal}"/>
                                                            <a href="javascript:cal1.popup();">
                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                     width="20" height="20" border="0"></a></td>
                                                                <%--<td class="fieldLabel">Stock Symbol2:</td>
                                                                <td colspan="5" align="left"><s:textfield name="stockSymbol2" id="stockSymbol2" cssClass="inputTextBlue"  value="%{currentAccount.stockSymbol2}"  onchange="fieldLengthValidator(this);changeCase(this);"/></td>
                                                                <td class="fieldLabel">Account Team:</td>
                                                                            <td><s:textfield name="accountTeam" cssClass="inputTextBlue"  value="%{currentAccount.accountTeam}"/></td>--%>
                                                    </tr>

                                                    <!-- Account Priority -->
                                             <%--       <s:if test="%{pri == 'All' || (#session.ceo == 'yes')}"> --%>
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

                                               <%--     </s:if>                      
                                                    <s:else>

                                                        <s:if test="#session.teamName=='B2B'">
                                                            <tr>
                                                                <td class="fieldLabel">
                                                                    Main :
                                                                </td>
                                                                <td>
                                                                    <s:textfield name="mainPriority" cssClass="inputTextBlue2" id="mainPriority" value="%{currentAccount.mainPriority}" onblur="return validatenumber(this)"/>
                                                                    <span class="fieldLabel"> B2B :</span><s:textfield name="b2bPriority" cssClass="inputTextBlue2" id="b2bPriority" value="%{currentAccount.b2bPriority}" onblur="return validatenumber(this)"/>

                                                                </td>
                                                                <td>
                                                                    
                                                                    <s:hidden name="bpmPriority" cssClass="inputTextBlue2" id="bpmPriority" value="%{currentAccount.bpmPriority}" onblur="return validatenumber(this)"/>
                                                                    <s:hidden name="sapPriority" cssClass="inputTextBlue2" id="sapPriority" value="%{currentAccount.sapPriority}" onblur="return validatenumber(this)"/>
                                                                    <s:hidden name="ecomPriority" cssClass="inputTextBlue2" id="ecomPriority" value="%{currentAccount.ecomPriority}" onblur="return validatenumber(this)"/>
                                                                    <s:hidden name="qaPriority" cssClass="inputTextBlue2" id="qaPriority" value="%{currentAccount.qaPriority}" onblur="return validatenumber(this)"/>
                                                                </td>
                                                            </tr> 
                                                        </s:if>  

                                                        <s:if test="#session.teamName=='BPM'">
                                                            <tr>
                                                                <td class="fieldLabel">
                                                                    Main :
                                                                </td>
                                                                <td>
                                                                    <s:textfield name="mainPriority" cssClass="inputTextBlue2" id="mainPriority" value="%{currentAccount.mainPriority}" onblur="return validatenumber(this)"/>
                                                                    <span class="fieldLabel">  BPM :</span><s:textfield name="bpmPriority" cssClass="inputTextBlue2" id="bpmPriority" value="%{currentAccount.bpmPriority}" onblur="return validatenumber(this)"/>

                                                                </td>

                                                                <td>
                                                                    <s:hidden name="b2bPriority" cssClass="inputTextBlue2" id="b2bPriority" value="%{currentAccount.b2bPriority}" onblur="return validatenumber(this)"/>
                                                                    
                                                                    <s:hidden name="sapPriority" cssClass="inputTextBlue2" id="sapPriority" value="%{currentAccount.sapPriority}" onblur="return validatenumber(this)"/>
                                                                    <s:hidden name="ecomPriority" cssClass="inputTextBlue2" id="ecomPriority" value="%{currentAccount.ecomPriority}" onblur="return validatenumber(this)"/>
                                                                    <s:hidden name="qaPriority" cssClass="inputTextBlue2" id="qaPriority" value="%{currentAccount.qaPriority}" onblur="return validatenumber(this)"/>

                                                                </td>
                                                            </tr> 
                                                        </s:if>  

                                                        <s:if test="#session.teamName=='SAP'">
                                                            <tr>
 <td class="fieldLabel">
                                                                    Main :
                                                                </td>
                                                                <td>
                                                                    <s:textfield name="mainPriority" cssClass="inputTextBlue2" id="mainPriority" value="%{currentAccount.mainPriority}" onblur="return validatenumber(this)"/>
                                                                    <span class="fieldLabel">  SAP :</span><s:textfield name="sapPriority" cssClass="inputTextBlue2" id="sapPriority" value="%{currentAccount.sapPriority}" onblur="return validatenumber(this)"/>
                                                                       
                                                                </td>
                                                                <td>
                                                                    <s:hidden name="b2bPriority" cssClass="inputTextBlue2" id="b2bPriority" value="%{currentAccount.b2bPriority}" onblur="return validatenumber(this)"/>
                                                                    <s:hidden name="bpmPriority" cssClass="inputTextBlue2" id="bpmPriority" value="%{currentAccount.bpmPriority}" onblur="return validatenumber(this)"/>
                                                                    
                                                                    <s:hidden name="ecomPriority" cssClass="inputTextBlue2" id="ecomPriority" value="%{currentAccount.ecomPriority}" onblur="return validatenumber(this)"/>
                                                                    <s:hidden name="qaPriority" cssClass="inputTextBlue2" id="qaPriority" value="%{currentAccount.qaPriority}" onblur="return validatenumber(this)"/>

                                                                </td>
                                                            </tr> 
                                                        </s:if>  

                                                        <s:if test="#session.teamName=='E-Commerce'">
                                                            <tr>
<td class="fieldLabel">
                                                                    Main :
                                                                </td>
                                                                <td>
                                                                    <s:textfield name="mainPriority" cssClass="inputTextBlue2" id="mainPriority" value="%{currentAccount.mainPriority}" onblur="return validatenumber(this)"/>
                                                                    <span class="fieldLabel">  ECOM :</span><s:textfield name="ecomPriority" cssClass="inputTextBlue2" id="ecomPriority" value="%{currentAccount.ecomPriority}" onblur="return validatenumber(this)"/>
                                                                
                                                                       
                                                                </td>
                                                                <td>
                                                                    <s:hidden name="b2bPriority" cssClass="inputTextBlue2" id="b2bPriority" value="%{currentAccount.b2bPriority}" onblur="return validatenumber(this)"/>
                                                                    <s:hidden name="bpmPriority" cssClass="inputTextBlue2" id="bpmPriority" value="%{currentAccount.bpmPriority}" onblur="return validatenumber(this)"/>
                                                                    <s:hidden name="sapPriority" cssClass="inputTextBlue2" id="sapPriority" value="%{currentAccount.sapPriority}" onblur="return validatenumber(this)"/>
                                                                    
                                                                    <s:hidden name="qaPriority" cssClass="inputTextBlue2" id="qaPriority" value="%{currentAccount.qaPriority}" onblur="return validatenumber(this)"/>

                                                                </td>
                                                            </tr> 
                                                        </s:if>  

                                                        <s:if test="#session.teamName=='QA'">
                                                            <tr>
<td class="fieldLabel">
                                                                    Main :
                                                                </td>
                                                                <td>
                                                                    <s:textfield name="mainPriority" cssClass="inputTextBlue2" id="mainPriority" value="%{currentAccount.mainPriority}" onblur="return validatenumber(this)"/>
                                                                    <span class="fieldLabel">  QA :</span><s:textfield name="qaPriority" cssClass="inputTextBlue2" id="qaPriority" value="%{currentAccount.qaPriority}" onblur="return validatenumber(this)"/>
                                                                
                                                                       
                                                                </td>
                                                                <td>
                                                                    <s:hidden name="b2bPriority" cssClass="inputTextBlue2" id="b2bPriority" value="%{currentAccount.b2bPriority}" onblur="return validatenumber(this)"/>
                                                                    <s:hidden name="bpmPriority" cssClass="inputTextBlue2" id="bpmPriority" value="%{currentAccount.bpmPriority}" onblur="return validatenumber(this)"/>
                                                                    <s:hidden name="sapPriority" cssClass="inputTextBlue2" id="sapPriority" value="%{currentAccount.sapPriority}" onblur="return validatenumber(this)"/>
                                                                    <s:hidden name="ecomPriority" cssClass="inputTextBlue2" id="ecomPriority" value="%{currentAccount.ecomPriority}" onblur="return validatenumber(this)"/>
                                                                    

                                                                </td>
                                                            </tr> 
                                                        </s:if> 
                                                    </s:else> --%>

                                                    <script type="text/javascript">

                                                        var cal1 = new CalendarTime(document.forms['frmAccForm'].elements['DateOfPPARenewal']);
                                                        cal1.year_scroll = true;
                                                        cal1.time_comp = false;
                                                    </script>


                                                </s:if>


                                                <s:else>

                                                    <tr>
                                                        <td class="fieldLabel">${title} Name:</td>
                                                        <td colspan="3"><s:textfield name="accountName" id="accountName" cssClass="inputTextBlueLargeAccount" value="%{currentAccount.accountName}" onchange="fieldLengthValidator(this);changeCase(this);" /> </td> 
                                                        <td class="fieldLabel">${title} Type:</td>                  
                                                        <td><s:select  list="{'Vendor','1099'}" name="accountType" id="accountType"  cssClass="inputSelect" value="%{currentAccount.accountType}"/></td> 
                                                    </tr>                                                
                                                    <tr>                                                    
                                                        <td class="fieldLabel">URL:</td>
                                                        <td colspan="3"><s:textfield name="url" id="url" cssClass="inputTextBlueLargeAccount"  value="%{currentAccount.url}"  onchange="fieldLengthValidator(this);allSmalls(this);"/></td>
                                                        <td class="fieldLabel">Synonyms:</td>
                                                        <td><s:textfield name="synonyms" id="synonyms" cssClass="inputTextBlue"  value="%{currentAccount.synonyms}" onchange="fieldLengthValidator(this);changeCase(this);"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="fieldLabel">Phone:</td>
                                                        <td><s:textfield name="phone" id="phone" cssClass="inputTextBlue"  value="%{currentAccount.phone}" onchange="return formatPhone(document.frmAccForm.phone.value);"/></td>
                                                        <td class="fieldLabel">Fax:</td>
                                                        <td><s:textfield name="fax" id="fax" cssClass="inputTextBlue"  value="%{currentAccount.fax}" onchange="fieldLengthValidator(this);"/></td>   
                                                        <td  class="fieldLabel">Status:</td>
                                                        <td><s:select list="accountStatusList" name="status" id="status" cssClass="inputSelect" value="%{currentAccount.status}"/></td>
                                                    </tr>
                                                    <tr>                                                        
                                                        <td class="fieldLabel">No. of Employees:</td>
                                                        <td><s:textfield name="noOfEmployees" id="noOfEmployees" cssClass="inputTextBlue"  value="%{currentAccount.noOfEmployees}" onchange="fieldLengthValidator(this);" onblur="return validatenumber(this)"/></td>  
                                                        <td class="fieldLabel">Revenues:</td>
                                                        <td><s:textfield name="revenues" id="revenues" cssClass="inputTextBlue"  value="%{currentAccount.revenues}"/></td>
                                                        <td class="fieldLabel">Tax ID:</td>
                                                        <td><s:textfield name="taxId" id="taxId" cssClass="inputTextBlue"  value="%{currentAccount.taxId}" onchange="fieldLengthValidator(this);"/></td> 
                                                    </tr>

                                                </s:else>

                                                <tr>
                                                    <td  valign="top" colspan="3" style="padding-left: 33px;">
                                                        <span class="fieldLabel">Description:</span><br>
                                                        <s:textarea cols="45" rows="8" name="description" id="Description" cssClass="inputTextarea" value="%{currentAccount.description}" onchange="fieldLengthValidator(this);"/>
                                                    </td>

                                                    <td colspan="3" style="padding-left:7px;" >
                                                        <s:include value="/includes/template/GeneralAddress.jsp">
                                                            <s:param name="addressLine1" value="addressLine1"/>
                                                            <s:param name="addressLine2" value="addressLine2"/>
                                                            <s:param name="city" value="city"/>
                                                            <s:param name="mailStop" value="mailStop"/>
                                                            <s:param name="state" value="state"/>
                                                            <s:param name="country" value="country"/>
                                                            <s:param name="zip" value="zip"/>
                                                            <s:param name="countryList" value="countryList"/>
                                                            <s:param name="statesList" value="statesList"/>
                                                        </s:include>
                                                    </td>
                                                </tr>
                                            </table> 
                                        </s:form>
                                        <%--  </sx:div > --%>
                                    </div>
                                    <!--//END TAB : -->
                                    <%-- Commented for Vendor   <s:if test="#session.roleName != 'Vendor'"> --%>
                                    <!--//START TAB : -->
                                    <%--   <sx:div id="SoftwareTab" label="Software">  --%>
                                    <div id="SoftwareTab" class="tabcontent" >

                                        <s:form name="softwareForm" action="%{currentSoftwaresVTO.formAction}" theme="simple">
                                            <table cellpadding="1" cellspacing="1" border="0" width="100%">
                                                <tr align="right">
                                                    <td class="headerText">
                                                        <s:submit cssClass="buttonBg" value="Save"/>
                                                        <s:hidden name="id" value="%{currentAccount.id}"/>   
                                                        <s:hidden  name="activitySummaryFlag" value="%{activitySummaryFlag}"/>
                                                        <s:hidden  name="dashBoardStartDate" value="%{dashBoardStartDate}"/>
                                                        <s:hidden  name="dashBoardEndDate" value="%{dashBoardEndDate}"/>
                                                        <s:hidden  name="contactStatus" value="%{contactStatus}"/>
                                                        <s:hidden  name="empId" value="%{empId}"/>
                                                        <s:hidden  name="activityType" value="%{activityType}"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <table border="0" cellpadding="1" cellspacing="0" width="90%">

                                                            <tr>
                                                                <td class="fieldLabel">Applications:</td>
                                                                <td><s:textfield name="applications" id="applications" cssClass="inputTextBlueLarge" value="%{currentSoftwaresVTO.applications}" onchange="fieldLengthValidator(this);changeCase(this);"/> </td>

                                                                <td class="fieldLabel">Hardware:</td>
                                                                <td><s:textfield name="hardware" id="hardware" cssClass="inputTextBlueLarge"  value="%{currentSoftwaresVTO.hardware}" onchange="fieldLengthValidator(this);changeCase(this);"/> </td>

                                                            </tr>

                                                            <tr>
                                                                <td class="fieldLabel">Softwares:</td>
                                                                <td><s:textfield name="softwares" id="softwares" cssClass="inputTextBlueLarge"   value="%{currentSoftwaresVTO.softwares}" onchange="fieldLengthValidator(this);changeCase(this);"/> </td>
                                                                <td class="fieldLabel">Databases:</td>
                                                                <td><s:textfield name="databases" id="databases" cssClass="inputTextBlueLarge"  value="%{currentSoftwaresVTO.databases}" onchange="fieldLengthValidator(this);changeCase(this);"/> </td>
                                                            </tr>


                                                        </table>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="headerText"> <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0"></td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <table border="0" cellpadding="1" cellspacing="0" width="90%">
                                                            <tr>    
                                                                <td  class="fieldLabel">DB2</td> 
                                                                <td><s:checkbox  name="dbTwo"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.dbTwo}"/></td>

                                                                <td  class="fieldLabel">MSSql</td> 
                                                                <td><s:checkbox  name="msSql"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.msSql}"/></td>

                                                                <td  class="fieldLabel">MySql</td>  
                                                                <td><s:checkbox  name="mySql"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.mySql}"/></td>

                                                                <td  class="fieldLabel">Oracle</td> 
                                                                <td><s:checkbox  name="oracle"   fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.oracle}"/></td>

                                                                <td  class="fieldLabel">OracleFusion</td> 
                                                                <td><s:checkbox  name="oracleFusion"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.oracleFusion}"/></td> 

                                                            </tr> 

                                                            <tr>

                                                                <td  class="fieldLabel">SAP</td>
                                                                <td><s:checkbox  name="sap"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.sap}"/></td>

                                                                <td  class="fieldLabel">Siebel</td>
                                                                <td><s:checkbox  name="siebel" fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.siebel}"/></td>

                                                                <td  class="fieldLabel">OracleApps</td>
                                                                <td><s:checkbox  name="oracleApps"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.oracleApps}"/></td>

                                                                <td  class="fieldLabel">PeopleSoft</td>  
                                                                <td><s:checkbox  name="peopleSoft"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.peopleSoft}"/></td>

                                                                <td  class="fieldLabel">Data Power</td> 
                                                                <td><s:checkbox  name="dataPower"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.dataPower}"/></td>   

                                                            </tr>

                                                            <tr>

                                                                <td  class="fieldLabel">WPS</td>
                                                                <td><s:checkbox  name="wps" fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.wps}"/></td>

                                                                <td  class="fieldLabel">Mercator</td> 
                                                                <td><s:checkbox  name="mercator"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.mercator}"/></td>

                                                                <td  class="fieldLabel">Cobol</td>
                                                                <td><s:checkbox  name="cobol"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.cobol}"/></td>

                                                                <td  class="fieldLabel">DotNet</td>  
                                                                <td><s:checkbox  name="dotNet"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.dotNet}"/></td>

                                                                <td  class="fieldLabel">Jsp</td> 
                                                                <td><s:checkbox  name="jsp" fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.jsp}"/></td>
                                                            </tr>


                                                            <tr>

                                                                <td  class="fieldLabel">Informatica</td> 
                                                                <td><s:checkbox  name="informatica"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.informatica}"/></td>

                                                                <td  class="fieldLabel">JDEdwards</td>  
                                                                <td><s:checkbox  name="jdEdwards"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.jdEdwards}"/></td>

                                                                <td  class="fieldLabel">Baan</td> 
                                                                <td><s:checkbox  name="baan"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.baan}"/></td>

                                                                <td  class="fieldLabel">BeaWebLogic</td>  
                                                                <td><s:checkbox  name="beaWeblogic"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.beaWeblogic}"/></td>

                                                                <td  class="fieldLabel">BusinessObjects</td> 
                                                                <td><s:checkbox  name="businessObjects"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.businessObjects}"/></td>

                                                            </tr> 

                                                            <tr>

                                                                <td  class="fieldLabel">Coldfusion</td>
                                                                <td><s:checkbox  name="coldFusion"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.coldFusion}"/></td>

                                                                <td  class="fieldLabel">Cognos</td> 
                                                                <td><s:checkbox  name="cognos"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.cognos}"/></td>

                                                                <td  class="fieldLabel">Cyclone</td> 
                                                                <td><s:checkbox  name="cyclone"   fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.cyclone}"/></td>

                                                                <td  class="fieldLabel">Fabric</td>
                                                                <td><s:checkbox  name="fabric"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.fabric}"/></td>                       

                                                                <td  class="fieldLabel">Harbinger</td>  
                                                                <td><s:checkbox  name="harbinger"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.harbinger}"/></td>

                                                            </tr>

                                                            <tr>

                                                                <td  class="fieldLabel">HpUx</td> 
                                                                <td><s:checkbox  name="hpUx"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.hpUx}"/></td>

                                                                <td  class="fieldLabel">Hats</td>
                                                                <td><s:checkbox  name="hats"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.hats}"/></td>                       

                                                                <td  class="fieldLabel">Ics</td>
                                                                <td><s:checkbox  name="ics"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.ics}"/></td>

                                                                <td  class="fieldLabel">IConnect</td> 
                                                                <td><s:checkbox  name="iConnect"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.iConnect}"/></td>

                                                                <td  class="fieldLabel">Hyperion</td> 
                                                                <td><s:checkbox  name="hyperion"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.hyperion}"/></td>

                                                            </tr>

                                                            <tr>

                                                                <td  class="fieldLabel">MicroStrategy</td>  
                                                                <td><s:checkbox name="microStrategy"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.microStrategy}"/></td>

                                                                <td  class="fieldLabel">RedHat</td> 
                                                                <td><s:checkbox  name="redHat"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.redHat}"/></td>

                                                                <td  class="fieldLabel">Sapxi</td> 
                                                                <td><s:checkbox  name="sapxi"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.sapxi}"/></td>

                                                                <td  class="fieldLabel">Tibco</td>
                                                                <td><s:checkbox  name="tibco"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.tibco}"/></td>                       

                                                                <td  class="fieldLabel">Windows</td>
                                                                <td><s:checkbox  name="windows"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.windows}"/></td>
                                                            </tr>


                                                            <tr>


                                                                <td  class="fieldLabel">Manuguistics</td> 
                                                                <td><s:checkbox  name="manuguistics"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.manuguistics}"/></td>

                                                                <td  class="fieldLabel">ITwo</td> 
                                                                <td><s:checkbox  name="ITwo"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.ITwo}"/></td>

                                                                <td  class="fieldLabel">Vignette</td> 
                                                                <td><s:checkbox  name="vignette"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.vignette}"/></td>

                                                                <td  class="fieldLabel">Broadvision</td> 
                                                                <td><s:checkbox  name="broadvision"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.broadvision}"/></td>

                                                                <td  class="fieldLabel">Neon</td> 
                                                                <td><s:checkbox  name="neon" fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.neon}"/></td>

                                                            </tr>

                                                            <tr>

                                                                <td  class="fieldLabel">Selectica</td> 
                                                                <td><s:checkbox  name="selectica"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.selectica}"/></td>

                                                                <td  class="fieldLabel">WebSphere</td> 
                                                                <td><s:checkbox  name="webSphere"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.webSphere}"/></td>

                                                                <td  class="fieldLabel">Ariba</td> 
                                                                <td><s:checkbox  name="ariba"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.ariba}"/></td>

                                                                <td  class="fieldLabel">Commerce</td> 
                                                                <td><s:checkbox  name="commerce" fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.commerce}"/></td>

                                                                <td  class="fieldLabel">Portals</td> 
                                                                <td><s:checkbox  name="ibmPortals" fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.ibmPortals}"/></td>

                                                            </tr>

                                                            <tr>
                                                                <td  class="fieldLabel">Xtol</td> 
                                                                <td><s:checkbox  name="xtol"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.xtol}"/></td>

                                                                <td  class="fieldLabel">Gentran</td>
                                                                <td><s:checkbox  name="gentran"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.gentran}"/></td>

                                                                <td  class="fieldLabel">WebMethods</td> 
                                                                <td><s:checkbox  name="webMethods" fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.webMethods}"/></td>

                                                                <td  class="fieldLabel">WTX</td> 
                                                                <td><s:checkbox  name="wtx" fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.wtx}"/></td>


                                                                <td  class="fieldLabel">GXS</td> 
                                                                <td><s:checkbox  name="gxs"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.gxs}"/></td>


                                                            </tr>
                                                            <tr>
                                                                <td  class="fieldLabel">Vitria</td> 
                                                                <td><s:checkbox  name="vitria" fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.vitria}"/></td>

                                                                <td  class="fieldLabel">SeeBurger</td>  
                                                                <td><s:checkbox  name="seeBurger"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.seeBurger}"/></td>


                                                                <td  class="fieldLabel">SeeBeyond</td> 
                                                                <td><s:checkbox  name="seeBeyond"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.seeBeyond}"/></td>

                                                                <td  class="fieldLabel">JCAPS</td> 
                                                                <td><s:checkbox  name="jcaps"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.jcaps}"/></td>

                                                                <td  class="fieldLabel">MQ</td>
                                                                <td><s:checkbox name="mq"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.mq}"/></td>


                                                            </tr>

                                                            <tr>
                                                                <td  class="fieldLabel">MB & IIB</td>
                                                                <td><s:checkbox  name="messageBroker"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.messageBroker}"/></td> 

                                                                <td  class="fieldLabel">AIX</td>  
                                                                <td><s:checkbox name="aix"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.aix}"/></td>
                                                                <td  class="fieldLabel">AS400</td>
                                                                <td><s:checkbox name="asFour"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.asFour}"/></td>

                                                                <td  class="fieldLabel">Solaris</td> 
                                                                <td><s:checkbox  name="solaris"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.solaris}"/></td>

                                                                <td  class="fieldLabel">MainFrames</td>
                                                                <td><s:checkbox  name="mainFrames" fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.mainFrames}"/></td>

                                                            </tr>
                                                            <tr>
                                                                <td  class="fieldLabel">CI</td>
                                                                <td><s:checkbox  name="castIron"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.castIron}"/></td> 

                                                                <td  class="fieldLabel">BPM</td>
                                                                <td><s:checkbox  name="bpm"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.bpm}"/></td> 

                                                                <td  class="fieldLabel">WODM</td>
                                                                <td><s:checkbox  name="wodm"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.wodm}"/></td> 

                                                                <td  class="fieldLabel">Lotus</td>
                                                                <td><s:checkbox  name="lotus"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.lotus}"/></td> 

                                                                <td  class="fieldLabel">Rational</td>
                                                                <td><s:checkbox  name="rational"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.rational}"/></td> 


                                                            </tr>

                                                            <tr>


                                                                <td  class="fieldLabel">HP Quality Center</td>
                                                                <td><s:checkbox  name="hp"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.hp}"/></td>

                                                                <td  class="fieldLabel">Sterling B2BI</td>
                                                                <td><s:checkbox  name="b2b"  fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.b2b}"/></td>
                                                               
                                                                <td  class="fieldLabel" id="tdBox">Hybris</td>
                                                                <td><s:checkbox  name="hybris" fieldValue="true" theme="simple" value="%{currentSoftwaresVTO.hybris}"/></td>
                                                            </tr>


                                                        </table>            
                                                    </td>
                                                </tr>
                                            </table>

                                        </s:form>
                                        <%--      </sx:div >  --%>
                                    </div>

                                    <%-- Commented for Vendor  </s:if> --%>
                                    <!--//END TAB : --> 
                                    <%-- Commented for Vendor  <s:if test="#session.roleName != 'Vendor'"> --%>
                                    <!--//START TAB : -->
                                    <%--  <sx:div id="AccountTeamTab" label="%{title} Team"> --%>
                                    <div id="AccountTeamTab" class="tabcontent" >
                                        <%
                                            accountPrimaryTeamMember = request.getAttribute("primaryTeamMember").toString();
                                            userId = session.getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                                            userRoleName = session.getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
                                            isUserManager = Integer.parseInt(session.getAttribute(ApplicationConstants.SESSION_IS_USER_MANAGER).toString());
                                            //if((accountPrimaryTeamMember.equalsIgnoreCase(userId)) || ("Vendor".equalsIgnoreCase(userRoleName)) || ("Recruitment".equalsIgnoreCase(userRoleName)) || ("Admin".equalsIgnoreCase(userRoleName)) || (isUserManager == 1))
                                            //if((accountPrimaryTeamMember.equalsIgnoreCase(userId)) || ("Vendor".equalsIgnoreCase(userRoleName)) || ("Recruitment".equalsIgnoreCase(userRoleName)) || ("Admin".equalsIgnoreCase(userRoleName)))   
                                            //if((accountPrimaryTeamMember.equalsIgnoreCase(userId)))          
                                            // if((accountPrimaryTeamMember.equalsIgnoreCase(userId)) || ("Vendor".equalsIgnoreCase(userRoleName)) || ("Recruitment".equalsIgnoreCase(userRoleName)) || ("Admin".equalsIgnoreCase(userRoleName)) || (isUserManager == 1 && !"Marketing".equalsIgnoreCase(userRoleName)))
                                            if ((accountPrimaryTeamMember.equalsIgnoreCase(userId)) || ("Vendor".equalsIgnoreCase(userRoleName)) || ("Recruitment".equalsIgnoreCase(userRoleName)) || ("Admin".equalsIgnoreCase(userRoleName)) || isUserManager == 1 || "Marketing".equalsIgnoreCase(userRoleName)) {
                                        %>

                                        <s:form name="frmAccTeam" action="accountTeamSubmit" theme="simple">
                                            <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                <tr>
                                                    <td class="headerText" align="right" colspan="2">
                                                        <% if (request.getAttribute(ApplicationConstants.RESULT_MSG) != null) {
                                                                out.println(request.getAttribute(ApplicationConstants.RESULT_MSG));
                                                            }%>
                                                        <s:submit cssClass="buttonBg" value="Save"/>
                                                        <s:hidden name="id" value="%{currentAccount.id}"/>
                                                        <s:hidden  name="activitySummaryFlag" value="%{activitySummaryFlag}"/>
                                                        <s:hidden  name="dashBoardStartDate" value="%{dashBoardStartDate}"/>
                                                        <s:hidden  name="dashBoardEndDate" value="%{dashBoardEndDate}"/>
                                                        <s:hidden  name="contactStatus" value="%{contactStatus}"/>
                                                        <s:hidden  name="empId" value="%{empId}"/>
                                                        <s:hidden  name="activityType" value="%{activityType}"/>






                                                    </td>
                                                </tr>

                                                <s:if test="#session.roleName != 'Vendor'">
                                                    <tr>
                                                        <td  class="fieldLabel" valign="top"> Assign Team Members:</td>
                                                        <td align="center">
                                                            <s:optiontransferselect
                                                                name="availableTeamMembers"
                                                                leftTitle="Available Team"
                                                                rightTitle="Assigned Team"
                                                                list="salesTeamExceptAccountTeamMap"
                                                                buttonCssClass="buttonBg"
                                                                doubleName="assignedTeamMembers"
                                                                doubleList="accountTeamMap"
                                                                doubleValue="%{primaryTeamMember}"
                                                                cssClass="inputTextarea">
                                                            </s:optiontransferselect>
                                                            <h4 style="padding-left:50px;padding-top:5px;color:green;">Assigned Team Details:&nbsp;<img SRC="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/help.gif" onclick="javaScript:AccountTeamDetailsPopup('AccDetailsPopup.jsp?accountId=<%=request.getParameter("id").toString()%>');" WIDTH=16 HEIGHT=16 BORDER=0 ALTER="Territory"></h4>     
                                                        </td>

                                                    </tr>

                                                </s:if>
                                                <s:else>

                                                    <tr>
                                                        <td  class="fieldLabel" valign="top"> Assign Team Members:</td>
                                                        <td align="center">
                                                            <s:optiontransferselect
                                                                name="availableTeamMembers"
                                                                leftTitle="Available Team"
                                                                rightTitle="Assigned Team"
                                                                list="vendorTeamMap"
                                                                buttonCssClass="buttonBg"
                                                                doubleName="assignedTeamMembers"
                                                                doubleList="accountTeamMap"
                                                                doubleValue="%{primaryTeamMember}"
                                                                cssClass="inputTextarea">
                                                            </s:optiontransferselect>
                                                            <h4 style="padding-left:50px;padding-top:5px;color:green;">Assigned Team Details:&nbsp;<img SRC="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/help.gif" onclick="javaScript:AccountTeamDetailsPopup('AccDetailsPopup.jsp?accountId=<%=request.getParameter("id").toString()%>');" WIDTH=16 HEIGHT=16 BORDER=0 ALTER="Territory"></h4>
                                                        </td>
                                                    </tr>


                                                </s:else>

                                            </table>   
                                        </s:form>
                                        <% // }else if((!accountPrimaryTeamMember.equalsIgnoreCase(userId))){
                                            //}else if((!accountPrimaryTeamMember.equalsIgnoreCase(userId)) || userRoleName.equalsIgnoreCase("Marketing")){  
                                        } else if ((!accountPrimaryTeamMember.equalsIgnoreCase(userId))) {
                                        %>
                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                            <tr>
                                                <td class="headerText" align="left" colspan="2">
                                                    Sorry! You are not the Primary Person to this account. Please Contact with Primary Person to add the Team Member.
                                                </td>
                                            </tr>
                                            <tr>
                                                <td  class="fieldLabel" valign="top"> Assigned Team Members:</td>
                                                <td align="center">
                                                    <s:optiontransferselect
                                                        name="availableTeamMembers"
                                                        leftTitle="Available Team"
                                                        rightTitle="Assigned Team"
                                                        list="salesTeamExceptAccountTeamMap"

                                                        doubleName="assignedTeamMembers"
                                                        doubleList="accountTeamMap"
                                                        doubleValue="%{primaryTeamMember}"
                                                        cssClass="inputTextarea" 

                                                        allowAddAllToLeft="false"
                                                        allowAddAllToRight="false"
                                                        allowAddToLeft="false"
                                                        allowAddToRight="false"
                                                        allowSelectAll="false"
                                                        allowUpDownOnLeft="false"
                                                        allowUpDownOnRight="false">
                                                    </s:optiontransferselect>
                                                </td>
                                            </tr>

                                        </table>   
                                        <%}%>


                                        <%--   </sx:div > --%>
                                    </div>
                                    <%-- Commented for Vendor </s:if> --%>
                                    <!--//END TAB : --> 

                                    <%--  </sx:tabbedpanel> --%>

                                    <script type="text/javascript">

                                        var countries=new ddtabcontent("accountTabs")
                                        countries.setpersist(true)
                                        countries.setselectedClassTarget("link") //"link" or "linkparent"
                                        countries.init()
                           
                                    </script>
                                </div>
                                <% if (request.getAttribute("currentAccountId") != null) {
                                        currentAccountId = (String) request.getAttribute("currentAccountId");
                                    }
                                    //  out.print("accId:"+currentAccountId);
%>


                                <%-- <s:url id="listACT" value="/Hubble/crm/accounts/getAccount.action?id=<%=currentAccountId%>" /> --%>

                                <!--//END TABBED PANNEL : -->
                                <ul id="accountTabs1" class="shadetabs" >
                                    <li><a href="#" rel="contactList" class="selected" >Contacts</a></li>

                                    <li><a href="#" rel="AccountActivityList">Activities</a></li>
                                    <%--<li><a href="#"  rel="AccountActivityList">Activities</a></li> --%>
                                   <%-- <li><a href="#" rel="opportunitiesList" >Opportunities</a></li> --%>
                                   <li><a href="#" rel="accountOpprtunityList" >Opportunities</a></li>
                                   <!-- <li><a href="#" rel="requirementsList" >Requirements</a></li> -->
                                    <li><a href="#" rel="accountRequirmentList">Requirements</a></li>
                                    <li><a href="#" rel="greensheetsList" >Greensheets</a></li>
                                    <li><a href="#" rel="projectsList">Projects</a></li>
                                    <%-- Commented for Vendor <s:if test="#session.roleName != 'Vendor'"> --%>
                                 <!--   <li><a href="#" rel="AllAccountActivitiesList">All Activities</a></li> -->
                                    <%-- Commented for Vendor </s:if> --%>
                                    <%-- <s:if test="(#session.roleName == 'Vendor') || (#session.roleName == 'Admin')"> --%>
                                    <li><a href="#" rel="attachmentsList">Attachments</a></li>
                                    <li><a href="#" rel="leadsList">Leads</a></li>

                                    <%-- </s:if> --%>
                                </ul>

                                <!--//START TABBED PANNEL :Two -->
                                <%--  <sx:tabbedpanel id="accountsDetailsPannels" cssStyle="width: 840px; height: 275px;padding-left:10px;padding-top:5px;padding-bottom:5px;" doLayout="true"  useSelectedTabCookie="true">  --%> 
                                <div  style="border:1px solid gray; width:840px;height: 275px; margin-bottom: 1em;overflow:auto; ">  

                                    <!-- New AccountActivity list using Ajax call -->

                                    <%--  <sx:div id="AccountActivityList" label="Activities" cssStyle="overflow:auto;"> --%>
                                    <div id="AccountActivityList" class="tabcontent" > 

                                        <%


                                            String activityAction = "../activities/activity.action";
                                            if (currentAccountId != null) {
                                                activityAction = activityAction + "?accountId=" + request.getAttribute("currentAccountId") + "&contactId=0";
                                            }
                                        %>
                                        <form action=""  method="post" name="frmDBAccActGrid"> 
                                            <input TYPE="hidden" NAME="txtContactCurr" VALUE="1">
                                            <s:hidden name="role" id="role" value="%{#session.roleName}"/> 
                                            <table cellpadding="0" cellspacing="0" width="100%">
                                                <tr>
                                                    <td class="headerText"> <a href="<%=activityAction%>" style="align:left;">
                                                            <img alt="Add Activity"
                                                                 src="<s:url value="/includes/images/add.gif"/>" 
                                                                 width="33px" 
                                                                 height="19px"
                                                                 border="0" align="left"></a>&nbsp;&nbsp;
                                                        <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <table width="100%" cellpadding="2" cellspacing="2" border="0">

                                                          <%--  <tr><td colspan="3"><div id="activityLoad" style="display: none"><font color="red" size="2px">Loading.. </font></div></td></tr> --%>
                                                                

                                                            <tr>
                                                                <td colspan="3" >

                                                                    <div id="AccountActivityList" style="display: block">
                                                                        <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                        <table id="tblUpdate11" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='100%' align="center">
                                                                            <COLGROUP ALIGN="left" >
                                                                               <COL width="5%">
                                                                                <COL width="10%">
                                                                                <COL width="10%">
                                                                                <COL width="15%">
                                                                                <COL width="10%">
                                                                                <COL width="10%">
                                                                                <COL width="10%">
                                                                                 <COL width="10%">
                                                                               
                                                                                <%-- <COL width="10%">
                                                                                 <COL width="10%"> --%>
                                                                        </table>
                                                                        <br>
                                                                        <center><span id="spnFast" class="activeFile" style="display: none;"></span></center> 
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                </tr>
                                            </table>


                                            </td>
                                            </tr>
                                            </table>    
                                        </form>  
                                        <%--      </sx:div>  --%>
                                    </div> 



                                    <div id="contactList" class="tabcontent" > 

                                        <%

                                            String contactAction = "../contacts/contact.action";
                                            if (currentAccountId != null) {
                                                contactAction = contactAction + "?accountId=" + currentAccountId;
                                            }


                                        %> 
                                       <!-- <form action=""  method="post" name="frmDBContactGrid">  -->
                                            <input TYPE="hidden" NAME="txtContactCurr" VALUE="1">
                                            <s:hidden name="role" id="role" value="%{#session.roleName}"/> 
                                            <table cellpadding="0" cellspacing="0" width="100%">
                                                <tr>
                                                    <td class="headerText"> <a href="<%=contactAction%>" style="align:left;">
                                                            <img alt="Add Activity"
                                                                 src="<s:url value="/includes/images/add.gif"/>" 
                                                                 width="33px" 
                                                                 height="19px"
                                                                 border="0" align="left"></a>&nbsp;&nbsp;
                                                        <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <table width="100%" cellpadding="2" cellspacing="2" border="0">


                                                          <%--  <tr>
                                                                <td class="fieldLabel">Name :</td>
                                                                <td>
                                                                    <input type="text" name="contactName" id="contactName" class="inputTextBlue" onkeydown="return enableConatctEnter(event);">
                                                                    
                                                                    
                                                                </td>  <td class="fieldLabel" style ="padding:0px 300px 0px 0px" >Title :
                                                                   <input type="text" name="contacttitle" id="contacttitle" class="inputTextBlue" >
                                                                   <input type="hidden" name="accId" id="accId" value="<%=currentAccountId%>"><input type="button" value="Search" class="buttonBg" onclick="getContactsList();"/>
                                                            </tr> --%>
                                                           <tr>
                                                                <td class="fieldLabel" style ="padding:0px 0px 0px 76px;">Name :
                                                                  <input type="text" name="contactName"  id="contactName" class="inputTextBlue" onkeydown="return enableConatctEnter(event);"/>
                                                                   <td class="fieldLabel" style ="padding:0px 300px 0px 0px" >Title :
                                                                   <input type="text" name="contacttitle" id="contacttitle" class="inputTextBlue" onkeydown="return enableConatctEnter(event);"/>
                                                                  &nbsp;<input type="button" value="Search"  class="buttonBg" onclick="getContactsList();"/>
                                                               <input type="hidden" name="accId" id="accId" value="<%=currentAccountId%>">
                                                               
                                                                

                                                            </tr>

                                                            <tr>
                                                                <td colspan="2" >

                                                                    <div id="consultantList" style="display: block">
                                                                        <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                        <table id="tblUpdate" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='100%' align="center">
                                                                            <COLGROUP ALIGN="left" >
                                                                                <COL width="4%">
                                                                                <COL width="4%">
                                                                                <COL width="10%">
                                                                                <COL width="10%">
                                                                                <COL width="10%">
                                                                                <COL width="21%">
                                                                                <COL width="15%">
                                                                                <COL width="20%">
                                                                                <COL width="10%">
                                                                        </table>
                                                                        <br>
                                                                        <center><span id="spnFast" class="activeFile" style="display: none;"></span></center>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                </tr>
                                            </table>


                                            </td>
                                            </tr>
                                            </table>    
                                        <!-- </form>   -->
                                        <%--      </sx:div>  --%>
                                    </div>

                                    <%-- Commented for Vendor <s:if test="#session.roleName != 'Vendor'"> --%>
                                    <%--   <sx:div id="AllAccountActivitiesList" label="All Activities" cssStyle="overflow:auto;"> --%>
                                    <div id="AllAccountActivitiesList" class="tabcontent" > 


                                        <form action=""  method="post" name="frmDBAccActGrid"> 
                                            <input TYPE="hidden" NAME="txtContactCurr" VALUE="1">
                                            <s:hidden name="role" id="role" value="%{#session.roleName}"/> 
                                            <table cellpadding="0" cellspacing="0" width="100%">

                                                <tr>
                                                    <td>
                                                        <table width="100%" cellpadding="2" cellspacing="2" border="0">


                                                            <tr>
                                                                <td class="fieldLabel">Contact Name :</td>
                                                                <td>
                                                                    <input type="text" name="activityName" id="activityName" class="inputTextBlue">
                                                                    <input type="hidden" name="accId" id="accId" value="<%=currentAccountId%>"><input type="button" value="Search" class="buttonBg" onclick="getAllActivityList();"/>
                                                                </td>

                                                            </tr>

                                                            <tr>
                                                                <td colspan="3" >

                                                                    <div id="AllAccountActivitiesList" style="display: block">
                                                                        <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                        <table id="tblUpdate22" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='100%' align="center">
                                                                            <COLGROUP ALIGN="left" >
                                                                                <COL width="4%">
                                                                                <COL width="10%">
                                                                                <COL width="15%">
                                                                                <COL width="10%">
                                                                                <COL width="10%">
                                                                                <COL width="10%">
                                                                                <COL width="15%">
                                                                                <COL width="15%">
                                                                                <COL width="20%">
                                                                                <%--<COL width="10%"> --%>
                                                                        </table>
                                                                        <br>
                                                                        <center><span id="spnFast" class="activeFile" style="display: none;"></span></center> 
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                </tr>
                                            </table>


                                            </td>
                                            </tr>
                                            </table>    
                                        </form>  
                                        <%--      </sx:div>  --%>
                                    </div> 
                                    
                                    
                                    
                                    
   <!-- Opprtunity AjaxList Start -->
                                    
                                    <div id="accountOpprtunityList" class="tabcontent" > 


                                        <s:form action=""  method="post" name="frmOpprtunity" theme="simple"> 
                                            
                                            
                                            <table cellpadding="0" cellspacing="0" width="100%">
                                                   <tr>
                                                     <%   String opportunityAction = "../opportunities/opportunity.action";
                                           if (request.getAttribute("currentAccountId") != null) {
                                              //  opportunityAction = opportunityAction + "?accountId=" + request.getAttribute("currentAccountId");
                                                 opportunityAction = opportunityAction + "?accountId=" + request.getAttribute("currentAccountId")+"&addingOppurtunties="+"addOppur";
                                            }%>
                                                    <td class="headerText"> <a href="<%=opportunityAction%>" style="align:left;">
                                                            <img alt="Add Opportunity"
                                                                 src="<s:url value="/includes/images/add.gif"/>" 
                                                                 width="33px" 
                                                                 height="19px"
                                                                 border="0" align="left"></a>&nbsp;&nbsp;
                                                        <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <table width="100%" cellpadding="2" cellspacing="2" border="0">


                                                            <tr>
                                                                <td class="fieldLabel">Status :</td>
                                                                <td colspan="2">
                                                                     <s:select name="opportunityState" id="opportunityState" headerKey="All" headerValue="All" cssClass="inputSelect" list="opportunityStateList" value="%{opportunityState}"/>
                                                                    <input type="button" value="Search" class="buttonBg" onclick="getOpportunityList();"/>
                                                                </td>

                                                            </tr>
                                                            <tr><td colspan="3" align="center"><div id="opploadMessage" style="display: none"><font color="red" size="2px">Loading Please wait.. </font></div></td></tr>
                                                            <tr>
                                                                <td colspan="3" >

                                                                    <div id="AccountOppListDiv" style="display: block">
                                                                        <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                        <table id="tblOpprtunity" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='100%' align="center">
                                                                            <COLGROUP ALIGN="left" >
                                                                                <COL width="4%">
                                                                                <COL width="10%">
                                                                                <COL width="15%">
                                                                                <COL width="10%">
                                                                                <COL width="10%">
                                                                                <COL width="10%">
                                                                                <COL width="15%">
                                                                               
                                                                                <%--<COL width="10%"> --%>
                                                                        </table>
                                                                        <br>
                                                                        
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                </tr>
                                            </table>


                                            </td>
                                            </tr>
                                            </table>    
                                        </s:form>  
                                        <%--      </sx:div>  --%>
                                    </div> 
                                    <!-- Opprtunity AjaxList End -->
                                    
                                    <%-- Commented for Vendor </s:if> 
                                                                   
                                    <s:if test="#session.roleName == 'Vendor'"> --%>
                                    <%--<% try{%>--%>
                                    <%-- Commented for Vendor  <s:if test="#session.roleName != 'Vendor'"> --%>
                                  <%--  <%

                                        try {

                                            /* Getting DataSource using Service Locator */
                                            connection = ConnectionProvider.getInstance().getConnection();
                                            /* Sql query for retrieving resultset from DataBase */

                                    %>

                                    

                                    <div id="opportunitiesList" class="tabcontent" > 

                                        <%

                                            /* String Variable for storing current position of records in dbgrid*/
                                            strTmp = request.getParameter("txtOppCurr");
                                            if (strTmp != null) {
                                                try {
                                                    intCurr = Integer.parseInt(strTmp);
                                                } catch (NumberFormatException NFEx) {
                                                    NFEx.printStackTrace();
                                                }
                                            } else {
                                                intCurr = 1;
                                            }

                                            /* Specifing Shorting Column */
                                            strSortCol = request.getParameter("txtOppSortCol");

                                            if (strSortCol == null) {
                                                strSortCol = "CreatedDate";
                                            }

                                            strSortOrd = request.getParameter("txtOppSortAsc");
                                            if (strSortOrd == null) {
                                                strSortOrd = "ASC";
                                            }

                                            /* Sql query for retrieving resultset from DataBase */
                                            queryString = "SELECT Id,State,AccountId,Title,Description,Value,DueDate,CreatedDate FROM tblCrmOpportunity";
                                            queryString = queryString + " WHERE AccountId =" + currentAccountId;
                                            String opportunityAction = "../opportunities/opportunity.action";
                                           if (request.getAttribute("currentAccountId") != null) {
                                              //  opportunityAction = opportunityAction + "?accountId=" + request.getAttribute("currentAccountId");
                                                 opportunityAction = opportunityAction + "?accountId=" + request.getAttribute("currentAccountId")+"&addingOppurtunties="+"addOppur";
                                            }


                                           // String opportunityEditAction = "../opportunities/getOpportunity.action?id={Id}&accountId=" + currentAccountId;
                                            
                                             String opportunityEditAction = "../opportunities/getOpportunity.action?id={Id}&accountId=" + currentAccountId+"&addingOppurtunties="+"editOppur&pri=All";

                                        %>

                                        <form action=""  method="post" name="frmDBOppGrid"> 
                                            <table cellpadding="0" cellspacing="0" width="100%">
                                                <tr>
                                                    <td class="headerText"> <a href="<%=opportunityAction%>" style="align:left;">
                                                            <img alt="Add Oppertunity"
                                                                 src="<s:url value="/includes/images/add.gif"/>" 
                                                                 width="33px" 
                                                                 height="19px"
                                                                 border="0" align="left"></a>&nbsp;&nbsp;

                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <!-- DataGrid for list all Opportunities -->
                                                        <grd:dbgrid id="tblCrmOpportunity" name="tblCrmOpportunity" width="100" pageSize="10" 
                                                        currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                        dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">

                                                            <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                           imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                           addImage="../../includes/images/DBGrid/Add.png" 
                                                            addAction="<%=opportunityAction%>"
                                                                           scriptFunction="getNextOpportunities"/>

                                                            <grd:imagecolumn headerText="Edit" width="4" HAlign="center" imageSrc="../../includes/images/DBGrid/newEdit_17x18.png"
                                                            linkUrl="<%=opportunityEditAction%>" imageBorder="0"
                                                                             imageWidth="16" imageHeight="16" alterText="Click to edit"></grd:imagecolumn>               
                                                            <grd:textcolumn dataField="State"  headerText="State"   width="25" sortable="true"/> 
                                                            <grd:textcolumn dataField="Title"  headerText="Title"   width="25" sortable="true"/> 
                                                            <grd:textcolumn dataField="Description"  headerText="Description"   width="25" sortable="true"/> 
                                                            <grd:numbercolumn dataField="Value" dataFormat="0.00" headerText="Value"   width="5" /> 
                                                            <grd:datecolumn dataField="DueDate"  headerText="Due Date"  dataFormat="MM-dd-yyyy HH:mm:SS" width="18" sortable="true"/>
                                                            <grd:datecolumn dataField="CreatedDate"  headerText="CreatedDate"  dataFormat="MM-dd-yyyy HH:mm:SS" width="18" sortable="true"/>
                                                        </grd:dbgrid>
                                                        <!-- these components are DBGrid Specific  -->
                                                        <input TYPE="hidden" NAME="txtOppCurr" VALUE="<%=intCurr%>">

                                                        <input TYPE="hidden" NAME="txtProjectCurr" VALUE="">
                                                        <input TYPE="hidden" NAME="txtAllAccActCurr" VALUE="">
                                                        <input TYPE="hidden" NAME="txtGreenSheetCurr" VALUE="">
                                                        <input TYPE="hidden" NAME="txtNotesCurr" VALUE=""/>
                                                        <input TYPE="hidden" NAME="txtAttachCurr" VALUE="">
                                                        <input TYPE="hidden" NAME="txtAccActCurr" VALUE="">

                                                        <input TYPE="hidden" NAME="submitFrom" VALUE="dbGrid">

                                                        <input type="hidden" name="accId" value="<%=currentAccountId%>">

                                                        <input TYPE="hidden" NAME="txtOppSortCol" VALUE="<%=strSortCol%>">
                                                        <input TYPE="hidden" NAME="txtOppSortAsc" VALUE="<%=strSortOrd%>">

                                                        <input type="hidden" name="isRequestFromGrid" value="YES">
                                                    </td>
                                                </tr>
                                            </table>    
                                        </form>
                                        
                                    </div> 
                                                        --%>


                                    <div id="greensheetsList" class="tabcontent"  >

                                        <table cellpadding="0" border="0" cellspacing="0" width="100%" align="center">
                                            <tr>
                                                <td class="headerText"> 

                                                    <%-- <a href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/greensheets/viewGreenSheet.action?teamGreensheets=true" style="align=left;">--%>
                                                    <a href="<s:url action="../greensheets/viewGreenSheet.action">
                                                           <s:param name="teamGreensheets" value="true"/>
                                                           <s:param name="customerName" value="%{currentAccount.accountName}"/>
                                                           <s:param name="accountId" value="%{currentAccount.id}"/>
                                                           <s:param name="accId" value="%{currentAccount.id}"/>
                                                            <s:param name="accountEdit" value="1"/>
                                                       </s:url>" style="align:left;">
                                                        <img alt="Add Requirement"
                                                             src="<s:url value="/includes/images/add.gif"/>" 
                                                             width="33px" 
                                                             height="19px"
                                                             border="0" align="left"></a>&nbsp;&nbsp;

                                                </td>
                                            </tr>
                                                     <tr align="right"><td><font color="#3E93D4" size="3px" style="font-weight: bold;"><%
                                                    if(request.getParameter("resultMessage")!=null){
                                                    out.println(request.getParameter("resultMessage"));

}
%></font></td></tr>
                                            <tr>
                                                <td align="center">

                                                    <%

                                        try {

                                            /* Getting DataSource using Service Locator */
                                            connection = ConnectionProvider.getInstance().getConnection();
                                            /* Sql query for retrieving resultset from DataBase */

                                    
                                                        String strSQL = null;

                                                        strTmp = request.getParameter("txtGreenSheetCurr");
                                                        if (strTmp != null) {
                                                            try {
                                                                intCurr = Integer.parseInt(strTmp);
                                                            } catch (NumberFormatException NFEx) {
                                                                NFEx.printStackTrace();
                                                            }
                                                        } else {
                                                            intCurr = 1;
                                                        }

                                                        String userId = (String) request.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID);

                                                        /* strSQL = "SELECT Id,CustomerName,concat(LastName,' ',FirstName,' ',MiddleName) AS ConsultantName,ConsultantType,DateStart,DateEnd,POStatus,CreatedBy," +
                                                        "ModifiedBy,UnitsRate,Status FROM vwGreenSheets WHERE createdBy='"+userId+"' AND RecordType=1 and ConsultantId="+request.getAttribute("currentAccountId")+" order by CustomerName,POStatus,DateEnd ";*/
                                                        String livingCountry = (String) request.getSession(false).getAttribute(ApplicationConstants.Living_COUNTRY);


                                                        if (livingCountry.equalsIgnoreCase("usa")) {
                                                            strSQL = "SELECT Id,ConsultantId,CustomerName,concat(LastName,' ',FirstName,' ',MiddleName) AS ConsultantName,ConsultantType,DateStart,DateEnd,POStatus,CreatedBy,"
                                                                    + "ModifiedBy,UnitsRate,Status FROM vwGreenSheets WHERE  RecordType=1 and ConsultantId=" + request.getAttribute("currentAccountId") + " order by CustomerName,POStatus,DateEnd ";
                                                        } else {
                                                            strSQL = "SELECT Id,ConsultantId,CustomerName,concat(LastName,' ',FirstName,' ',MiddleName) AS ConsultantName,ConsultantType,DateStart,DateEnd,POStatus,CreatedBy,"
                                                                    + "ModifiedBy,UnitsRate,Status FROM vwGreenSheets WHERE createdBy='" + userId + "' AND RecordType=1 and ConsultantId=" + request.getAttribute("currentAccountId") + " order by CustomerName,POStatus,DateEnd ";
                                                        }

                                                        // country='USA' and 

                                                        strSortCol = request.getParameter("txtSortCol");
                                                        strSortOrd = request.getParameter("txtSortAsc");

                                                        // out.println(strSQL);
%>

                                                    <form method="post" id="frmDBGreenSheetGrid" name="frmDBGreenSheetGrid" action="">  


                                                        <grd:dbgrid  id="tblStat" name="tblStat"  width="100" pageSize="10" 
                                                        currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                        dataMember="<%=strSQL.toString()%>" dataSource="<%=connection%>" cssClass="gridTable">

                                                            <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                           imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                           scriptFunction="getNextGreenSheets" />

                                                            <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" />
                                                            <grd:imagecolumn  headerText="Edit" width="5"  HAlign="center"  
                                                                              imageSrc="../../includes/images/DBGrid/newEdit_17x18.png" 
                                                                              linkUrl="../greensheets/getGreenSheetByID.action?greenSheetId={Id}&teamGreensheets=true&accountEdit=1&accId={ConsultantId}" 
                                                                              imageBorder="0" imageWidth="16" imageHeight="16" alterText="Click to edit" />
                                                            <%--  <grd:textcolumn dataField="CustomerName" headerText="Customer Name"  sortable="true"
                                                                            width="20"/> --%>
                                                            <grd:textcolumn dataField="ConsultantName" headerText="Consultant Name" sortable="true" 
                                                                            width="15"/>
                                                            <%--    <grd:textcolumn dataField="ConsultantType" headerText="Consultant Type" 
                                                                            width="14"  HAlign="center"/> --%>
                                                            <grd:datecolumn dataField="DateStart" headerText="Start Date" dataFormat="MM-dd-yyyy"
                                                                            width="9" sortable="true" />
                                                            <grd:datecolumn dataField="DateEnd" headerText="End Date" sortable="true" dataFormat="MM-dd-yyyy"
                                                                            width="8"/>
                                                            <grd:textcolumn dataField="POStatus" headerText="POStatus" 
                                                                            width="10" sortable="true" HAlign="center" />
                                                            <grd:textcolumn dataField="Status" headerText="Greensheets Status" 
                                                                            width="10" sortable="true" HAlign="center" />               

                                                            <grd:numbercolumn dataFormat="#00.00" width="10" dataField="UnitsRate" headerText="Billing Rate/hr " />

                                                            <%--    <grd:textcolumn dataField="CreatedBy" headerText="CreatedBy" />
                                                       <grd:textcolumn dataField="ModifiedBy" headerText="ModifiedBy" /> --%>

                                                        </grd:dbgrid>



                                                      <!--  <input TYPE="hidden" NAME="txtProjectCurr" VALUE="">
                                                        <input TYPE="hidden" NAME="txtAllAccActCurr" VALUE="">
                                                        <input TYPE="hidden" NAME="txtContactCurr" VALUE="">
                                                        <input TYPE="hidden" NAME="txtNotesCurr" VALUE="">
                                                        <input TYPE="hidden" NAME="txtAttachCurr" VALUE="">
                                                        <input TYPE="hidden" NAME="txtOppCurr" VALUE="">
                                                        <input TYPE="hidden" NAME="txtAccActCurr" VALUE=""> -->


                                                        <input TYPE="hidden" NAME="txtGreenSheetCurr" VALUE="<%=intCurr%>">
                                                         <input TYPE="hidden" NAME="txtGreenSheetProjectCurr" VALUE="">
                                                             <input TYPE="hidden" NAME="txtGreenSheetContactCurr" VALUE="">
                                                              <input TYPE="hidden" NAME="txtGreenSheetOppCurr" VALUE="">
                                                        <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                        <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                                                        <input TYPE="hidden" name="submitFrom" value="dbGrid"/>
                                                    </form>

                                                </td>

                                            </tr>

                                        </table>


                                    </div>

                                    <%--    <sx:div id="attachmentsList" label="Resume Attachments"> --%>
                                    <div id="attachmentsList" class="tabcontent" > 
                                        <%

                                            /* String Variable for storing current position of records in dbgrid*/
                                            strTmp = request.getParameter("txtVendorAttachCurr");

                                            if (strTmp != null) {
                                                intCurr = Integer.parseInt(strTmp);
                                            }

                                            /* Specifing Shorting Column */
                                            strSortCol = request.getParameter("txtVendorAttachSortCol");

                                            if (strSortCol == null) {
                                                strSortCol = "DateOfUpload";
                                            }

                                            strSortOrd = request.getParameter("txtVendorAttachSortAsc");
                                            if (strSortOrd == null) {
                                                strSortOrd = "ASC";
                                            }

                                            /* Sql query for retrieving resultset from DataBase */
                                            queryString = "Select Id,AttachmentType,AttachType,DateOfUpload,UploadedBy,Description,ObjectId,ObjectType from tblPrjAttachments WHERE ObjectType ='Vendor'";
                                            queryString = queryString + "AND ObjectId =" + currentAccountId + " ORDER BY DateOfUpload DESC";

                                            String attachmentAction = "../../projects/getAttachment.action";

                                            if (request.getAttribute("currentAccountId") != null) {
                                                attachmentAction = attachmentAction + "?objectId=" + request.getAttribute("currentAccountId") + "&objectType=Vendor";
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
                                                        <grd:dbgrid id="tblprjAttachments" name="tblprjAttachments" width="100" pageSize="10" 
                                                        currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                        dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">

                                                            <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                           imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                           addImage="../../includes/images/DBGrid/Add.png" 
                                                            addAction="<%=attachmentAction%>"
                                                                           scriptFunction="getVendorAttachment"/>
                                                            <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" />
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
                                                      <!--  <input TYPE="hidden" NAME="txtAccActCurr" VALUE="">
                                                        <input TYPE="hidden" NAME="txtContactCurr" VALUE="">                                                            
                                                        <input TYPE="hidden" NAME="txtOppCurr" VALUE=""> -->

                                                        <input TYPE="hidden" NAME="submitFrom" VALUE="dbGrid">
                                                        <input type="hidden" name="accId" value="<%=currentAccountId%>">                                                            
                                                        <input TYPE="hidden" NAME="txtVendorAttachSortCol" VALUE="">
                                                        <input TYPE="hidden" NAME="txtVendorAttachSortAsc" VALUE="">
                                                          <input TYPE="hidden" NAME="txtVendorAttCur" VALUE="">
                                                          <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                        <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">

                                                    </td>
                                                </tr>
                                            </table>    
                                        </form>  
                                    </div> 

                                    <%--   </sx:div> 
                                    
                                    </s:if> --%>

                                    <div id="projectsList" class="tabcontent" > 
                                        <%

                                            /* String Variable for storing current position of records in dbgrid*/
                                            strTmp = request.getParameter("txtProjectCurr");

                                            if (strTmp != null) {
                                                try {
                                                    intCurr = Integer.parseInt(strTmp);
                                                } catch (NumberFormatException NFEx) {
                                                    NFEx.printStackTrace();
                                                }
                                            } else {
                                                intCurr = 1;
                                            }

                                            /* Specifing Shorting Column */
                                            strSortCol = request.getParameter("txtProjectSortCol");

                                            if (strSortCol == null) {
                                                strSortCol = "ProjectStartDate";
                                            }

                                            strSortOrd = request.getParameter("txtProjectSortAsc");
                                            if (strSortOrd == null) {
                                                strSortOrd = "DESC";
                                            }

                                            /* Sql query for retrieving resultset from DataBase */
                                            queryString = "Select  Id,ProjectName,ProjectStartDate,ProjectEndDate,TotalResources,CustomerId,ProjectManagerUID ,Status FROM tblProjects";
                                            queryString = queryString + " WHERE CustomerId=" + currentAccountId + " ORDER BY ProjectStartDate DESC";
                                            //out.println(queryString);
                                            String projectAddAction = "../../projects/project.action?accountId=" + currentAccountId;

                                        %>
                                        <form action="" name="frmDBProjectGrid" method="post" id="frmDBProjectGrid"> 
                                            <table cellpadding="0" cellspacing="0" width="100%">

                                                <tr>
                                                    <td class="headerText"> <a href="<%=projectAddAction%>" style="align:left;">
                                                            <img alt="Add Requirement"
                                                                 src="<s:url value="/includes/images/add.gif"/>" 
                                                                 width="33px" 
                                                                 height="19px"
                                                                 border="0" align="left"></a>&nbsp;&nbsp;

                                                    </td>
                                                </tr>

                                                <tr>
                                                    <td>
                                                        <!-- DataGrid for Projects -->



                                                        <grd:dbgrid id="tblProjects" name="tblProjects" width="100" pageSize="10" 
                                                        currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                        dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">

                                                            <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                           imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                           addImage="../../includes/images/DBGrid/Add.png"   
                                                            addAction="<%=projectAddAction%>"
                                                                           scriptFunction="getNextProjects"/>
                                                            <grd:rownumcolumn headerText="SNo" width="5" HAlign="right"/>
                                                            <grd:imagecolumn  headerText="Edit"  width="5"   
                                                                              linkUrl="../../projects/getProject.action?Id={Id}&accountId={CustomerId}"  
                                                                              HAlign="center"
                                                                              imageSrc="../../includes/images/DBGrid/newEdit_17x18.png"
                                                                              imageBorder="0" 
                                                                              imageWidth="16" 
                                                                              imageHeight="16" 
                                                                              alterText="Click to Edit"/>
                                                            <grd:datecolumn dataField="ProjectStartDate" headerText="Start Date" width="15" dataFormat="MM-dd-yyyy" />
                                                            <grd:datecolumn dataField="ProjectEndDate"  headerText="End Date" width="15" dataFormat="MM-dd-yyyy" />
                                                            <grd:textcolumn dataField="ProjectName"  headerText="Project Name" width="37"/>
                                                            <grd:textcolumn dataField="Status"  headerText="Status" width="37"/>
                                                            <%--<grd:textcolumn dataField="ProjectManagerUID" headerText="Manager UID" width="15" /> --%>

                                                           <%-- <grd:numbercolumn dataField="TotalResources"  headerText="No.Resources" width="8" dataFormat="0"/> --%>

                                                        </grd:dbgrid>
                                                        <!-- these components are DBGrid Specific  -->
                                                   <!--  <input TYPE="hidden" NAME="txtProjectCurr" VALUE="<%=intCurr%>">-->
                                                        <input TYPE="hidden" NAME="txtProjectCurr" VALUE="<%=intCurr%>">
                                                        <input TYPE="hidden" NAME="txtProjectSortCol" VALUE="<%=strSortCol%>">
                                                        <input TYPE="hidden" NAME="txtProjectSortAsc" VALUE="<%=strSortOrd%>">

                                                        <input TYPE="hidden" NAME="submitFrom" VALUE="dbGrid">
                                                        <input type="hidden" name="accId" value="<%=currentAccountId%>">

                                                        <input TYPE="hidden" NAME="txtGreenSheetCurr" VALUE="">
                                                        <input TYPE="hidden" NAME="txtAllAccActCurr" VALUE="">
                                                        <input TYPE="hidden" NAME="txtContactCurr" VALUE="">
                                                        <input TYPE="hidden" NAME="txtNotesCurr" VALUE="">
                                                        <input TYPE="hidden" NAME="txtAttachCurr" VALUE="">
                                                        <input TYPE="hidden" NAME="txtOppCurr" VALUE="">
                                                        <input TYPE="hidden" NAME="txtAccActCurr" VALUE="">
                                                    </td>
                                                </tr>
                                            </table>
                                        </form> 
                                        <%--     </sx:div> --%>
                                    </div> 

                                   <%--  <div id="requirementsList" class="tabcontent"  >

                                        <%
                                            /* String Variable for storing current position of records in dbgrid*/
                                            strTmp = request.getParameter("txtReqCurr");

                                            intCurr = 1;

                                            if (strTmp != null) {
                                                intCurr = Integer.parseInt(strTmp);
                                            }

                                            /* Sql query for retrieving resultset from DataBase */

                                            //String empLeaveAction = "../../employee/leaveRequest.action";
                                            int empId = Integer.parseInt((String) session.getAttribute(ApplicationConstants.SESSION_EMP_ID));

                                            if (request.getAttribute("currentAccountId") != null) {

                                                String currentAccountId = (String) request.getAttribute("currentAccountId");

                                            }
                                            strSortCol = request.getParameter("txtReqSortCol");


                                            String queryString = "select TRIM(Id) AS RequirementId,CustomerId AS accId,TRIM(JobTitle) AS JobTitle ,CONCAT(`tblRecRequirement`.`State`,', ',`tblRecRequirement`.`Country`) AS Location,STATUS,StartDate,AssignedTo AS Recruiter,AssignToTechLead AS PreSales,Skills FROM tblRecRequirement  WHERE CustomerId='" + currentAccountId + "'   ORDER BY DatePosted DESC";
                                            // out.println(queryString);
                                            String RequirementAction = "../requirement/requirement.action?accId=" + request.getAttribute("currentAccountId");
                                        %>


                                        <form method="post" id="frmReqDBGrid" name="frmReqDBGrid" action=""> 
                                            <table cellpadding="0" cellspacing="0" width="100%">

                                                <tr>
                                                    <td class="headerText"> <a href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/requirement/requirement.action?accId=<%=currentAccountId%>&region=<s:property value="currentAccount.region"/>"style="align:left;">

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
                                                           <grd:textcolumn dataField="RequirementId"  headerText="Req Id"   width="5" />
                                                            <grd:anchorcolumn dataField="JobTitle" linkUrl="javascript:getRequirementSkills('{RequirementId}')" headerText="Job Title"
                                                                              linkText="{JobTitle}"  width="10" />
                                                            <grd:textcolumn dataField="Location"  headerText="Location"   width="5" />
                                                            <grd:textcolumn dataField="status" headerText="Status" HAlign="center" dataFormat="" width="5" />
                                                            <grd:datecolumn dataField="startdate" headerText="StartDate"  width="15"  dataFormat="MM-dd-yyyy HH:mm:SS" />
                                                            <grd:anchorcolumn dataField="Recruiter" linkUrl="javascript:getRecruiterDetails('{Recruiter}')" headerText="Recruiter"
                                                                              linkText="{Recruiter}"  width="15" />

                                                            <grd:anchorcolumn dataField="PreSales" linkUrl="javascript:getRecruiterDetails('{PreSales}')" headerText="Pre-Sales"
                                                                              linkText="{PreSales}"  width="15" />

                                                         

                                                        </grd:dbgrid>

                                                        <!-- these components are DBGrid Specific  -->
                                                        <input TYPE="hidden" NAME="txtReqCurr" VALUE="<%=intCurr%>">
                                                        <input TYPE="hidden" NAME="txtReqSortCol" VALUE="<%=strSortCol%>">
                                                        <input TYPE="hidden" NAME="txtReqSortAsc" VALUE="<%=strSortOrd%>">
                                                        <input type="hidden" name="submitFrom" value="dbGrid">

                                                    </td>
                                                </tr>
                                            </table>
                                        </form>

                                       
                                    </div> --%>

                                   
                                   <!-- Requirement Ajax Div Start -->
                                    <div id="accountRequirmentList" class="tabcontent" > 


                                        <s:form action=""  method="post" name="frmRequirement" theme="simple"> 
                                            
                                            
                                            <table cellpadding="0" cellspacing="0" width="100%">
                                               
                                                   <tr>
                                                     <%   String requirementAction = "../requirement/requirement.action";
                                                  
                                           if (request.getAttribute("currentAccountId") != null) {
                                              //  opportunityAction = opportunityAction + "?accountId=" + request.getAttribute("currentAccountId");
                                                 requirementAction = requirementAction + "?accId=" + request.getAttribute("currentAccountId");
                                            }%>
                                                    <td class="headerText"> <a href="<%=requirementAction%>" style="align:left;">
                                                            <img alt="Add Requirement"
                                                                 src="<s:url value="/includes/images/add.gif"/>" 
                                                                 width="33px" 
                                                                 height="19px"
                                                                 border="0" align="left"></a>&nbsp;&nbsp;
                                                        <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <table width="100%" cellpadding="2" cellspacing="2" border="0">

                                                   <s:param name="accId" value="%{currentAccount.id}"/>
                                                 <s:param name="accountId" value="%{currentAccount.id}"/>
                                                            <tr>
                                                                <td class="fieldLabel">Status :</td>
                                                                <td colspan="2">
                            
                                                                  <%--  <s:select name="requirementstatus" id="requirementstatus" list="#@java.util.LinkedHashMap@{'Open':'Open','InProgress':'InProgress','Lost':'Lost','Withdrawn':'Withdrawn','Hold':'Hold','Forecast':'Forecast','won':'won','lost':'lost'}" cssClass="inputSelect" multiple="true"/>
                                                                    <s:hidden name="requirementstatus1" id="requirementstatus1"/>  --%>
                                                                  <s:select label="Select Status" name="requirementstatus" id="requirementstatus" list="requirementStatusList" cssClass="inputSelect" multiple="true"/> 
                                                                  <s:hidden name="requirementstatus1" id="requirementstatus1"/>
                                                                    <input type="button" value="Search" class="buttonBg" onclick="getRequirementList();"/>
                                                                </td>

                                                            </tr>
                                                            <tr><td colspan="3" align="center"><div id="reqloadMessage" style="display: none"><font color="red" size="2px">Loading Please wait.. </font></div></td></tr>
                                                            <tr>
                                                                <td colspan="3" >

                                                                    <div id="AccountReqListDiv" style="display: block">
                                                                        <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                        <table id="tblRequirement222" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='95%' align="center">
                                                                                <COLGROUP ALIGN="left" >
                                                                                <COL width="5%">
                                                                                <COL width="10%">
                                                                                <COL width="20%">
                                                                                <COL width="1%">
                                                                                <COL width="10%">
                                                                                <COL width="20%">
                                                                                <COL width="20%">
                                                                                <COL width="20%">
                                                                                <%--<COL width="10%"> --%>
                                                                        </table>
                                                                        <br>
                                                                        
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                </tr>
                                            </table>


                                            </td>
                                            </tr>
                                            </table>    
                                        </s:form>  
                                        <%--      </sx:div>  --%>
                                    </div> 
							
                                   <!-- Requirement Ajax Div End -->
                                   
                                    <!-- Lead Div Start -->
                                    <div id="leadsList" class="tabcontent" > 

                                        <%
                                            String AddLeadsAction = "../../marketing/doAddLeads.action?accountId=" + currentAccountId;
                                        %>
                                        <form action=""  method="post" name="frmDBAccActGrid"> 
                                            <input TYPE="hidden" NAME="txtContactCurr" VALUE="1">
                                            <s:hidden name="role" id="role" value="%{#session.roleName}"/> 
                                            <table cellpadding="0" cellspacing="0" width="100%">
                                                <tr>
                                                    <td class="headerText"> <a href="<%=AddLeadsAction%>" style="align:left;">
                                                            <s:if test="#session.roleName != 'Sales'">
                                                                 <img alt="Add Activity"
                                                                 src="<s:url value="/includes/images/add.gif"/>" 
                                                                 width="33px" 
                                                                 height="19px"
                                                                 border="0" align="left"></a>&nbsp;&nbsp;
                                                            </s:if>
                                                        
                                                        
                                                           
                                                        <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                   
                                                     <% if(request.getAttribute("leadsResultMessage") != null){
                                                                        out.println(request.getAttribute("leadsResultMessage"));
                                                                    }%></td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <table width="100%" cellpadding="2" cellspacing="2" border="0">
                                                            <tr>
                                                                <td colspan="3" >

                                                                    <div id="LeadAddingList" style="display: block">
                                                                        <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                        <table id="tblUpdate111" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='100%' align="center">
                                                                            <COLGROUP ALIGN="left" >
                                                                                <COL width="5%">
                                                                                <COL width="15%">
                                                                                <COL width="15%">
                                                                                <COL width="15%">
                                                                                <COL width="15%">
                                                                                <COL width="15%">
                                                                                
                                                                                <%-- <COL width="20%">--%>
                                                                                <%-- <COL width="10%">
                                                                                 <COL width="10%"> --%>
                                                                        </table>
                                                                        <br>
                                                                        <center><span id="spnFast" class="activeFile" style="display: none;"></span></center> 
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                </tr>
                                            </table>


                                            </td>
                                            </tr>
                                            </table>    
                                        </form>  
                                        <%--      </sx:div>  --%>
                                    </div> 


                                    <!-- lead Div End -->

                                </div>  
                                <!--//START TABBED PANNEL :Two -->

                                <%
                                        connection.close();
                                        connection = null;
                                    } catch (Exception se) {
                                        System.out.println("Exception in AccountDetails " + se.getMessage());
                                    } finally {
                                        if (connection != null) {
                                            connection.close();
                                            connection = null;
                                        }
                                    }
                                %>  
                                <script type="text/javascript">

                                    var countries=new ddtabcontent("accountTabs1")
                                    countries.setpersist(true)
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
		getContactsList();
                checkCountry(); 
                getActivityList(); 
                getLeadDetailsList();
                getOpportunityList();
                getStatusHeader();
                getRequirementList();
		});
		</script>

    </body>
</html>
