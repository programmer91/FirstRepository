<%--*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  September 29, 2007, 7:50 PM
 *
 * Author  : Jyothi Nimmagadda<jnimmagadda@miraclesoft.com>
 *
 * File    : ContactAction.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * ******************************************************************************
*/--%>
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %> 
<%@ page import="com.freeware.gridtag.*" %> 
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib prefix="grd" uri="/WEB-INF/tlds/datagrid.tld"%>
<html>
    <head>
        <title>Hubble Organization Portal :: Contact Details</title>
        
        <sx:head cache="false"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tooltip.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tooltip.js"/>"></script>   
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
         <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGContDetails.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CommonAjax.js"/>"></script>
        <%--<script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ContactClientValidation.js"/>"></script>--%>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/StringUtility.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ajax/AjaxPopup.js"/>"></script>   
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/StandardClientValidations.js"/>"></script>
         <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        
         <script type="text/JavaScript" src="<s:url value="/includes/javascripts/Contact-Activity.js"/>"></script>
         
        <s:include value="/includes/template/headerScript.html"/>
        <script type="text/javascript">
        function checkStatus()
        {
        //alert("i am in checkStatus method");
        var contactStatus = document.getElementById('contactStatus').value;
		var contactDesig = document.getElementById('designationListId').value;
	var x=document.getElementById("firstName").value;

        var y=document.getElementById("lastName").value;
        var custEmail = document.getElementById('officeEmail').value;	
        if (x==null || x=="")
         {
             alert("First name must be filled out");
             return false;
         }
        //alert("contactStatus-->"+contactStatus);
        if(contactStatus == 'Please Select')
        {
        //alert("in if");
        alert("Please select ContactStatus");
        return false;
        }
        else
        {
        //alert("in else");
        return true;
        }
        }
        </script>
    </head>
    <!-- <body class="bodyGeneral" oncontextmenu="return false;" onload="getContactActivityList();doUnSubscribeChanges();"> -->
    <body class="bodyGeneral" oncontextmenu="return false;">
        <%!
        /* Declarations */
        Connection connection;
        
        String queryString;
        String currentContactId;
        String currentAccountId;
        String strTmp;
        String strSortCol;
        String strSortOrd;
        
        int intSortOrd = 0;
        int intCurr;
        boolean blnSortAsc = true;
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
                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                            
                            <!--//START DATA COLUMN : Coloumn for Screen Content-->
                            <td width="850px" class="cellBorder" valign="top" style="padding-left:10px;padding-top:5px;">
                                <table cellpadding="0" cellspacing="0" width="100%" border="0">
                                    <tr>
                                        <td>
                                            <span class="fieldLabel"> <s:if test="#session.roleName != 'Vendor'">Account Name :</s:if><s:else>Vendor Name :</s:else></span>&nbsp;
                                            <a class="navigationText" href="<s:url action="../accounts/getAccount"><s:param name="id" value="%{currentContact.accountId}"/></s:url>"><s:property value="#session.accountName"/></a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td valign="top">
                                            <s:form name="contactForm" action="editContact" theme="simple" onsubmit="return checkStatus();">
                                                <ul id="accountTabs" class="shadetabs" >
                                                    <li ><a href="#" class="selected" rel="contactTab"  > Contact</a></li>
                                                    <li ><a href="#" rel="contactAddresses">Other Details</a></li>
                                                </ul>
                                                <div  style="border:1px solid gray; width:840px;height: 230px; overflow:auto; margin-bottom: 1em;">
                                                    <!--//START TABBED PANNEL : -->
                                                    <%--  <sx:tabbedpanel id="contactPannel" cssStyle="width: 845px; height: 230px;padding-left:10px;padding-top:5px;" doLayout="true" useSelectedTabCookie="true"> --%>
                                                    <!--//START TAB : -->
                                                    <%--  <sx:div id="contactTab" label="Contact"> --%>
                                                    <div id="contactTab" class="tabcontent"  >
                                                        
                                                        <table width="100%" border="0" cellpadding="0" cellspacing="0">
                                                            <tr align="right">
                                                                <td colspan="6" class="headerText">
                                                                    <% if(request.getAttribute(ApplicationConstants.RESULT_MSG) != null){
                                                                        out.println(request.getAttribute(ApplicationConstants.RESULT_MSG));
                                                                    }%>
                                                                    <a href="<s:url value="../accounts/getAccount.action?id=%{currentContact.accountId}"/>" style="align:center;">
                                                                        <img alt="Back to List"
                                                                             src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                             width="66px" 
                                                                             height="19px"
                                                                         border="0" align="bottom"></a>&nbsp;&nbsp;
                                                                    <s:submit value="Update" cssClass="buttonBg"/>
                                                                    <s:hidden name="id" value="%{currentContact.id}"/>
                                                                    <s:hidden name="accountId" value="%{currentContact.accountId}"/>
                                                                    <s:hidden name="createdBy" value="%{currentContact.createdBy}"/>
                                                                    <s:hidden name="createdDate" value="%{currentContact.createdDate}"/>
                                                                    <s:hidden name="primaryAddressId" value="%{currentContact.primaryAddressId}"/>
                                                                    <s:hidden name="secondaryAddressId" value="%{currentContact.secondaryAddressId}"/>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td colspan="6"><s:fielderror/></td>
                                                            </tr>
                                                            
                                                            <tr>
                                                                <td class="fieldLabel">First Name:</td>
                                                                <td>
                                                                    <s:select list="salutaionList" name="salutation" cssClass="inputSelectSmall" value="%{currentContact.salutation}"/>
                                                                    <s:textfield name="firstName" id="firstName" cssClass="inputTextBlue" value="%{currentContact.firstName}" onchange="fieldLengthValidator(this);changeCase(this);changeCase(this);"/> 
                                                                </td>
                                                                <td class="fieldLabel">Last Name:</td>
                                                                <td><s:textfield name="lastName" id="lastName" cssClass="inputTextBlue" value="%{currentContact.lastName}" onchange="fieldLengthValidator(this);changeCase(this);"/></td>
                                                                <td class="fieldLabel">MI:</td>
                                                                <td><s:textfield name="middleName" id="middleName" cssClass="inputTextBlue" value="%{currentContact.middleName}" onchange="fieldLengthValidator(this);changeCase(this);"/></td> 
                                                                
                                                            </tr>
                                                            
                                                            <tr>
                                                                <td class="fieldLabel">Alias Name:</td>
                                                                <td><s:textfield name="aliasName" id="aliasName" cssClass="inputTextBlue" value="%{currentContact.aliasName}" onchange="fieldLengthValidator(this);changeCase(this);"/></td>
                                                                <td class="fieldLabel">Gender:</td>
                                                                <td><s:select name="gender" list="genderList"  cssClass="inputSelectGender" value="%{currentContact.gender}" onkeypress="return handleEnter(this,event);"/></td>
                                                                <td class="fieldLabel">Title:</td>
                                                                <td><s:textfield name="title" id="title" cssClass="inputTextBlueLarge" value="%{currentContact.title}" onkeypress="return handleEnter(this,event);" onchange="fieldLengthValidator(this);"/></td>
                                                            </tr>
                                                            
                                                            <tr>
                                                                <td class="fieldLabel">Status:</td>
                                                                <td class="inputOptionText"><s:select name="contactStatus" id="contactStatus" list="contactStatusList" cssClass="inputSelect" value="%{currentContact.contactStatus}" onkeypress="return handleEnter(this,event);"/></td>
                                                                <td class="fieldLabel" >Specialization:</td>
                                                                <td><s:textfield name="specialization" id="specialization" cssClass="inputTextBlue" value="%{currentContact.specialization}" onkeypress="return handleEnter(this,event);" onchange="fieldLengthValidator(this);changeCase(this);"/></td>
                                                                <td class="fieldLabel">Source:</td>
                                                                <td><s:textfield name="leadSource" id="leadSource" cssClass="inputTextBlueLarge" value="%{currentContact.leadSource}" onchange="fieldLengthValidator(this);changeCase(this);"/></td>
                                                            </tr>
                                                            
                                                            <tr>
                                                                
                                                                <td class="fieldLabel">Off.Phone:</td>
                                                                <td><s:textfield name="officePhone" id="officePhone" cssClass="inputTextBlue" value="%{currentContact.officePhone}" onchange="return formatPhone(this);"/></td>
                                                                <td class="fieldLabel">Mobile:</td>
                                                                <td><s:textfield name="cellPhone" id="cellPhone" cssClass="inputTextBlue" value="%{currentContact.cellPhone}" onchange="return formatPhone(this);"  onblur="return validatenumber(this)"/></td>
                                                                <td class="fieldLabel" valign="baseline">Off.Email:</td>
                                                                <td> <s:textfield name="officeEmail" id="officeEmail" cssClass="inputTextBlueLarge" value="%{currentContact.officeEmail}"  onchange="fieldLengthValidator(this);allSmalls(this);checkEmail(this);" /> </td>            
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel">Fax:</td>
                                                                <td><s:textfield name="fax" id="fax" cssClass="inputTextBlue" value="%{currentContact.fax}" onchange="fieldLengthValidator(this);"/></td>
                                                                <td class="fieldLabel">Home Phone:</td>
                                                                <td><s:textfield name="homePhone" id="homePhone" cssClass="inputTextBlue" value="%{currentContact.homePhone}" onchange="return formatPhone(this);"/></td>
                                                                <td class="fieldLabel" valign="baseline">Pers.Email:</td>
                                                                <td><s:textfield name="personalEmail" id="personalEmail" cssClass="inputTextBlueLarge" value="%{currentContact.personalEmail}" onchange="fieldLengthValidator(this);allSmalls(this); checkEmail(this);" /></td> 
                                                                
                                                            </tr>
                                                               
                                                            <tr> 
                                                                <s:if test="%{currentContact.dontSend}">
                                                                   
                                                                    <td class="fieldLabel">Dont Send Mail:</td>
                                                                    <td>  <input type="radio" id="dontSend" name="dontSend" checked ></td>
                                                                </s:if>
                                                                <s:else>
                                                                    <td class="fieldLabel">Dont Send Mail:</td>
                                                                    <td>  <input type="radio" id="dontSend" name="dontSend"></td>
                                                                </s:else>
                                                            </tr>
                                                            <tr> 
                                                                <td colspan="6" valign="top">
                                                                    <span class="fieldLabel">Comments:</span>
                                                                    <s:textarea name="comments" id="comments" cols="100" rows="2"  cssClass="inputTextarea" value="%{currentContact.comments}" onchange="fieldLengthValidator(this);"  theme="simple"/>
                                                                </td>
                                                            </tr>
                                                            
                                                        </table>
                                                        
                                                        <%--   </sx:div > --%>
                                                    </div>
                                                    <!--//END TAB : -->
                                                
                                                    <!--//START TAB : -->
                                                    <%--   <sx:div id="contactAddresses" label="Other Details"> --%>
                                                    <div id="contactAddresses" class="tabcontent"  >
                                                        <table cellpadding="1" cellspacing="1" border="0" width="100%">
                                                            <tr>
                                                                <td class="fieldLabel">DOB:</td>
                                                                <td><s:textfield name="DOB" cssClass="inputTextBlueSmall" value="%{currentContact.DOB}" onkeypress="return handleEnter(this,event);"/><a href="javascript:cal1.popup();">
                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                </td>
                                                                <td class="fieldLabel">DOA:</td>
                                                                <td><s:textfield name="DOA" cssClass="inputTextBlueSmall" value="%{currentContact.DOA}" onkeypress="return handleEnter(this,event);"/><a href="javascript:cal2.popup();">
                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                </td>
                                                                <td class="fieldLabel">Ref.By:</td>
                                                                <td><s:textfield name="referredBy" id="referredBy" cssClass="inputTextBlue" value="%{currentContact.referredBy}" onkeypress="return handleEnter(this,event);" onchange="fieldLengthValidator(this);changeCase(this);"/></td>
                                                                
                                                            </tr>
                                                            
                                                            <tr class="headerText">
                                                                <td colspan="3" align="center">Office Address</td>
                                                                <td colspan="3" align="center">Home Address</td>
                                                            </tr>
                                                            <tr>
                                                                <td colspan="3">
                                                                    <s:include value="/includes/template/GeneralAddress.jsp" >
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
                                                                <td colspan="3">
                                                                    <table width="400" cellpadding="1" cellspacing="1" border="0">
                                                                        <tr>
                                                                            <td class="fieldLabel">Add.L1:</td>
                                                                            <td colspan="3"> <s:textfield name="resAddressLine1" id="resAddressLine1" cssClass="inputTextBlueAddress" value="%{currentContact.resAddressLine1}" onkeypress="return handleEnter(this,event);" onchange="fieldLengthValidator(this);changeCase(this);" theme="simple"/> </td>
                                                                        </tr>
                                                                        
                                                                        <tr>
                                                                            <td class="fieldLabel">Add.L2:</td>
                                                                            <td colspan="3"><s:textfield name="resAddressLine2" id="resAddressLine2" cssClass="inputTextBlueAddress" value="%{currentContact.resAddressLine2}" onkeypress="return handleEnter(this,event);" onchange="fieldLengthValidator(this);changeCase(this);" theme="simple"/> </td> 
                                                                        </tr>
                                                                        
                                                                        <tr>
                                                                            <td class="fieldLabel">City:</td>
                                                                            <td><s:textfield name="resCity" id="resCity" cssClass="inputTextBlue" value="%{currentContact.resCity}" onchange="fieldLengthValidator(this);changeCase(this);" onkeypress="return handleEnter(this,event);" theme="simple"/> </td>
                                                                            <td class="fieldLabel">M.Stop:</td>
                                                                            <td><s:textfield name="resMailStop" id="resMailStop" cssClass="inputTextBlue" value="%{currentContact.resMailStop}" onchange="fieldLengthValidator(this);" onkeypress="return handleEnter(this,event);" theme="simple"/></td>
                                                                        </tr>
                                                                        
                                                                        <tr>
                                                                            <td class="fieldLabel">State:</td>
                                                                            
                                                                            <td>
                                                                                <s:select 
                                                                                    list="resStatesList"   
                                                                                    name="resState" 
                                                                                    id="resState"
                                                                                    value="%{currentContact.resState}"
                                                                                    cssClass="inputSelect" 
                                                                                    headerKey="-1"
                                                                                    headerValue="--Please Select--"
                                                                                    onkeypress="return handleEnter(this,event);" theme="simple"
                                                                                />
                                                                                
                                                                            </td>
                                                                            <td class="fieldLabel">Country:</td>
                                                                            <td >
                                                                                <s:select 
                                                                                    list="countryList" 
                                                                                    name="resCountry" 
                                                                                    id="resCountry"
                                                                                    value="%{currentContact.resCountry}"
                                                                                    cssClass="inputSelect"
                                                                                    headerKey="-1"
                                                                                    headerValue="--Please Select--"
                                                                                    onchange="getResStateData();"
                                                                                    onkeypress="return handleEnter(this,event);" theme="simple"
                                                                                />
                                                                            </td>
                                                                        </tr>
                                                                        
                                                                        <tr>
                                                                            <td class="fieldLabel">Zip:</td>
                                                                            <td><s:textfield name="resZip" id="resZip" cssClass="inputTextBlue" value="%{currentContact.resZip}" onchange="fieldLengthValidator(this);" onkeypress="return handleEnter(this,event);" theme="simple"/></td>
                                                                        </tr>    
                                                                        
                                                                    </table>  
                                                                </td>
                                                            </tr>
                                                        </table>
                                                        <script type="text/JavaScript">
                                                           var cal1 = new CalendarTime(document.forms['contactForm'].elements['DOB']);
                                                           cal1.year_scroll = true;
                                                           cal1.time_comp = false;
                                                           
                                                           var cal2 = new CalendarTime(document.forms['contactForm'].elements['DOA']);
                                                           cal2.year_scroll = true;
                                                           cal2.time_comp = false;
                                                        </script>
                                                        <%--   </sx:div> --%>
                                                    </div>
                                                    <!--//END TAB : -->
                                                    <%-- </sx:tabbedpanel> --%>
                                                </div>
                                                <!--//END TABBED PANNEL : -->
                                                <script type="text/javascript">

var countries=new ddtabcontent("accountTabs")
countries.setpersist(false)
countries.setselectedClassTarget("link") //link" or "linkparent"
countries.init()

                                                </script>
                                            </s:form>
                                            <!--//START TABBED PANNEL : -->
                                     <ul id="accountTabs1" class="shadetabs" >
                                    <li ><a href="#" class="selected" rel="activityListTab"> Activity List </a></li>
                                    <li ><a href="#" rel="notesListTab">Notes</a></li>
                                </ul>
                                            <%--    <sx:tabbedpanel id="activityPannel" cssStyle="width: 845px; height: 250px;padding-left:10px;padding-top:5px;" doLayout="true" useSelectedTabCookie="true"> --%>
                                            <div  style="border:1px solid gray; width:840px;height: 250px; overflow:auto; margin-bottom: 1em;">   
                                                <%
                                                
                                                /* String Variable for storing current position of records in dbgrid*/
                                                strTmp 	= request.getParameter("txtActCurr");
                                                intCurr = 1;
                                                if (strTmp != null)
                                                intCurr = Integer.parseInt(strTmp);
                                                
                                                /* Specifing Shorting Column */
                                                strSortCol = request.getParameter("txtActSortCol");
                                                
                                                if (strSortCol == null) strSortCol = "CreatedDate"; // this column for sorting resultSet
                                                strSortOrd = request.getParameter("txtActSortAsc");
                                                
                                                if (strSortOrd == null) strSortOrd = "DESC";
                                               // try{
                                                
                                                if(request.getAttribute("currentAccountId") != null){
                                                currentAccountId = request.getAttribute("currentAccountId").toString();
                                                }
                                                
                                                if(request.getAttribute("currentContactId") != null){
                                                currentContactId = request.getAttribute("currentContactId").toString();
                                                }
                                                
                                                // Getting connection from ConnectionProvider
                                               // connection = ConnectionProvider.getInstance().getConnection();
                                                
                                                
                                              
                                                
                                                queryString = "SELECT ActivityId,ActivityType,Description,DateDue,"
                                                + "CreatedDate,AssignedToId"
                                                + " FROM vwActivityList WHERE ContactId ="+currentContactId
                                                + " AND AccountId="+request.getAttribute("currentAccountId")+" GROUP BY ActivityType,Description,AssignedToId ORDER BY CreatedDate DESC";
                                                
                                                
                                                String activityAction = "../activities/activity.action";
                                                if((currentAccountId != null)&&(currentContactId != null)){
                                                activityAction = activityAction+"?accountId="
                                                +request.getAttribute("currentAccountId")
                                                +"&contactId="
                                                +request.getAttribute("currentContactId");
                                                }
                                              //  out.print(queryString);
                                                %>
                                                
                                                <!--//START TAB : -->
                                                <%--   <sx:div id="activityListTab" label="Activity List" cssStyle="overflow:auto;">  --%>
                                                <div id="activityListTab" class="tabcontent"  >
                                                    <form action="" name="frmDBActGrid" method="post"> 
                                                        <table cellpadding="0" cellspacing="0" width="100%" border="0">
                                                            <tr align="left">
                                                                <td class="headerText">
                                                                    <input type="hidden" name="accId" id="accId" value="<%=currentAccountId%>"/>
                                                                    <input type="hidden" name="curContactId" id="curContactId" value="<%=currentContactId%>"/>
                                                                  <%--  <a href="<%=activityAction%>" style="align:left;">
                                                                        <img alt="Add Activity"
                                                                             src="<s:url value="/includes/images/add.gif"/>" 
                                                                             width="33px" 
                                                                             height="19px"
                                                                         border="0" align="left"></a>&nbsp;&nbsp; --%>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td>
                                                                     <table width="100%" cellpadding="2" cellspacing="2" border="0">
                        
                      
                        
                        <tr>
                            <td colspan="3" >
                                
                                <div id="AccountActivityList" style="display: block">
                                    <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                    <table id="tblUpdate11" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='100%' align="center">
                                        <COLGROUP ALIGN="left" >
                                        <COL width="5%">
                                        <COL width="20%">
                                        <COL width="30%">
                                        <COL width="20%">
                                        <COL width="25%">
                                       <%-- <COL width="10%">
                                        <COL width="15%">
                                        <COL width="20%">--%>
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
                                                    <%--   </sx:div> --%>
                                                </div>
                                                <!--//END TAB : -->
                                    
                                                <%--<s:if test="session.roleName != 'Vendor'">--%>
                                                <!--//START TAB : -->
                                                <%--    <sx:div id="notesListTab" label="Notes" cssStyle="overflow:auto;" > --%>
                                                <div id="notesListTab" class="tabcontent"  >
                                                    
                                                    <%
                                                    
                                                    /* String Variable for storing current position of records in dbgrid*/
                                                    strTmp 	= request.getParameter("txtNotesCurr");
                                                    intCurr = 1;
                                                    if (strTmp != null)
                                                    intCurr = Integer.parseInt(strTmp);
                                                    
                                                    /* Specifing Shorting Column */
                                                    strSortCol = request.getParameter("txtNotesSortCol");
                                                    
                                                    if (strSortCol == null) strSortCol = "CreatedDate"; // this column for sorting resultSet
                                                    strSortOrd = request.getParameter("txtNotesSortAsc");
                                                    
                                                    if (strSortOrd == null) strSortOrd = "DESC";
                                                    try{
                                                        
                                                        // Getting connection from ConnectionProvider
                                                      connection = ConnectionProvider.getInstance().getConnection();
                                                   
                                                    /* Sql query for retrieving resultset from DataBase */
                                                    queryString =" Select Id,CreatedDate,NoteType,Note FROM tblCrmNotes";
                                                    queryString = queryString + " WHERE ObjectId ="+currentContactId;
                                                    queryString = queryString + " AND ObjectType = 'Contact'";
                                                    
                                                    String notesAction = "../notes/note.action";
                                                    
                                                    if(currentContactId != null){
                                                    notesAction = notesAction+"?contactId="+currentContactId;
                                                    }
                                                    
                                                    String notesEditAction = "../notes/getNote.action?id={Id}&contactId="+currentContactId;
                                                    
                                                    %>
                                                    
                                                    <form action="" name="frmDBNotesGrid" method="post">  
                                                        <table cellpadding="0" cellspacing="0" width="100%">
                                                            <tr>
                                                                <td class="headerText">
                                                                    <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td>
                                                                    <!-- DataGrid for list all activities -->
                                                                    
                                                                                <grd:dbgrid id="tblStat" name="tblStat" width="100" pageSize="8" 
                                                                                currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                                dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                        
                                                                        <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                                       imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                                       addImage="../../includes/images/DBGrid/Add.png" 
                                                                                       addAction="<%=notesAction%>"
                                                                                       scriptFunction="getNextNotes"/>
                                                                        
                                                                        <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" 
                                                                                        imageAscending="../../includes/images/DBGrid/ImgAsc.gif" 
                                                                                        imageDescending="../../includes/images/DBGrid/ImgDesc.gif"/>    
                                                                        
                                                                        <grd:imagecolumn  headerText="Edit" width="5"  HAlign="center"  
                                                                                          imageSrc="../../includes/images/DBGrid/newEdit_17x18.png"  
                                                                                          linkUrl="<%=notesEditAction%>"
                                                                                          imageBorder="0" 
                                                                                          imageWidth="16" 
                                                                                          imageHeight="16" 
                                                                                          alterText="Click to edit" />
                                                                        <grd:textcolumn dataField="NoteType" headerText="NoteType" width="10"/>
                                                                        <grd:datecolumn dataField="CreatedDate" headerText="CreatedDate" width="8" dataFormat="MM-dd-yyyy"/>
                                                                        <grd:textcolumn dataField="Note" headerText="Note" width="68"/>
                                                                        
                                                                    </grd:dbgrid>
                                                                    
                                                                    <input TYPE="hidden" NAME="txtNotesCurr" VALUE="<%=intCurr%>">
                                                                    <input TYPE="hidden" NAME="txtActCurr" VALUE="">
                                                                    
                                                                    
                                                                    <input TYPE="hidden" NAME="txtNotesSortCol" VALUE="<%=strSortCol%>">
                                                                    <input type="hidden" name="txtNotesSortAsc"  value="<%=strSortOrd%>">
                                                                    <input TYPE="hidden" NAME="accountId" VALUE="<%=currentAccountId%>">
                                                                    <input TYPE="hidden" NAME="submitFrom" VALUE="dbGrid">
                                                                </td>
                                                            </tr>
                                                        </table>                                        
                                                    </form>                           
                                                    
                                                    <%-- </sx:div > --%>
                                                </div>
                                                
                                                
                                                <%
                                                connection.close();
                                                connection = null;
                                                }catch(Exception se){
                                                System.out.println("Exception in ContactDetails "+se);
                                                }finally{
                                                if(connection!= null){
                                                connection.close();
                                                connection = null;
                                                }
                                                }
                                                %>
                                                <!--//END TAB : --> 
                                                <%-- </sx:tabbedpanel> --%>
                                            </div>
                                            <!--//END TABBED PANNEL : -->
                                            <script type="text/javascript">
                                              
var countries=new ddtabcontent("accountTabs1")
countries.setpersist(false)
countries.setselectedClassTarget("link") //link" or "linkparent"
countries.init()

                                                </script>
                                        </td>
                                    </tr>
                                </table>
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
		getContactActivityList();
                doUnSubscribeChanges();
                
		});
		</script>
    </body>
</html>


