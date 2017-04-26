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
 * *****************************************************************************
*/--%>
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%--<%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<%@ page import="com.freeware.gridtag.*" %> 
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd" %>
<html>
    <head>
        <title>Hubble Organization Portal :: Adding Contact</title>
       <%-- <sx:head cache="false"/> --%>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script> 
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>   
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CommonAjax.js"/>"></script>
        <%--<script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ContactClientValidation.js"/>"></script>--%>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/StringUtility.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/StandardClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/Contact-Activity.js"/>"></script>
        <s:include value="/includes/template/headerScript.html"/>
        
        <script type="text/javascript">
        function checkStatus()
        {
            
        var x=document.getElementById("firstName").value;

        var y=document.getElementById("lastName").value;
        var custEmail = document.getElementById('officeEmail').value;	
        if (x==null || x=="")
         {
             alert("First name must be filled out");
             return false;
         }
        //alert("i am in checkStatus method");
        var contactStatus = document.getElementById('contactStatus').value;
      //  var contactDesig = document.getElementById('designationListId').value;
        //alert("contactDesig-->"+contactDesig);
        if(contactStatus == 'Please Select')
        {
        //alert("in if");
        alert("Please select ContactStatus");
        return false;
        }
       
        else
        {
       // alert("in else");
        return true;
        }
        }
        </script>
    </head>
  <!--  <body class="bodyGeneral" oncontextmenu="return false;"  onload="getContactaddContactList();"> -->
    <body class="bodyGeneral" oncontextmenu="return false;">
        <%!
        /* Declarations */
        Connection connection;
        
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
                            <td width="850px" class="cellBorder" valign="top">
                                
                                <table cellpadding="0" cellspacing="0" width="100%">
                                    <tr>
                                        <td><span class="fieldLabel"><s:if test="#session.roleName != 'Vendor'">Account Name :</s:if><s:else>Vendor Name :</s:else></span>&nbsp;
                                        <a href="<s:url action="../accounts/getAccount"><s:param name="id" value="%{accountId}"/></s:url>" class="navigationText"><s:property value="#session.accountName"/></a></td>
                                    </tr>
                                    <tr>
                                        <td valign="top" style="padding-left:10px;padding-top:5px;">
                                            <s:form name="contactForm" action="%{ActionName}" theme="simple" onsubmit="return checkStatus();">
                                                <!--//START TABBED PANNEL : -->
                                                <%--   <sx:tabbedpanel id="contactPannel" cssStyle="width: 845px; height: 230px;padding-left:10px;padding-top:5px;" doLayout="true"> --%>
                                                <ul id="accountTabs" class="shadetabs" >
                                                    <li ><a href="#" class="selected" rel="contactTab"  > Add Contact </a></li>
                                                    <li ><a href="#" rel="contactAddresses">Other Details</a></li>
                                                </ul> 
                                                <div  style="border:1px solid gray; width:840px;height: 230px; overflow:auto; margin-bottom: 1em;">                                               
                                                    
                                                    
                                                    <!--//START TAB : -->
                                                    <%--  <sx:div id="contactTab" label="Contact" > --%>
                                                    <div id="contactTab" class="tabcontent"  >
                                                        
                                                        
                                                        <table width="100%" border="0" cellpadding="0" cellspacing="0">
                                                            <tr align="right">
                                                                <td colspan="6" class="headerText">
                                                                    <% if(request.getAttribute(ApplicationConstants.RESULT_MSG) != null){
                                                                        out.println(request.getAttribute(ApplicationConstants.RESULT_MSG));
                                                                    }%>
                                                                    <a href="<s:url value="../accounts/getAccount.action?id=%{accountId}"/>" style="align:center;">
                                                                        <img alt="Back to List"
                                                                             src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                             width="66px" 
                                                                             height="19px"
                                                                         border="0" align="bottom"></a>&nbsp;&nbsp;
                                                                   
                                                                         <s:if test="%{actionName == 'addContact'}">
                                                                            <%--<s:property value="actionName"/> 
                                                                             <s:property value="accountId"/>
                                                                              <s:property value="Id"/>--%>
                                                                             <%-- <s:submit label="Insert" value="Update" cssClass="buttonBg" action="editContact"/> --%>
                                                                             <s:submit label="Insert" value="Save" cssClass="buttonBg"/>
                                                                             <s:hidden name="accountId" value="%{accountId}"/>
                                                                         </s:if>
                                                                         <s:else>
                                                                             <s:submit label="Insert" value="Update" cssClass="buttonBg"  />
                                                                             <%--<s:submit label="Insert" value="Save" cssClass="buttonBg"/> action="editContact" --%>
                                                                             <s:hidden name="id" value="%{currentContact.id}"/>
                                                                             <s:hidden name="accountId" value="%{currentContact.accountId}"/>
                                                                             <s:hidden name="createdBy" value="%{currentContact.createdBy}"/>
                                                                             <s:hidden name="createdDate" value="%{currentContact.createdDate}"/>
                                                                             <s:hidden name="primaryAddressId" value="%{currentContact.primaryAddressId}"/>
                                                                             <s:hidden name="secondaryAddressId" value="%{currentContact.secondaryAddressId}"/>
                                                                         </s:else>
                                                                    
                                                                    
                                                                    
                                                                  
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td colspan="6"><s:fielderror/></td>
                                                            </tr>
                                                            
                                                            <tr>
                                                                <td class="fieldLabel">First Name:</td>
                                                                <td>
                                                                    <s:select list="salutaionList" name="salutation"  value="#{'Mr.'}" cssClass="inputSelectSmall"/>
                                                                    <s:textfield name="firstName" cssClass="inputTextBlue"  onchange="fieldLengthValidator(this);changeCase(this);" id="firstName" size="30"/> 
                                                                </td>
                                                                <td class="fieldLabel">Last Name:</td>
                                                                <td><s:textfield name="lastName"  cssClass="inputTextBlue"  onchange="fieldLengthValidator(this);changeCase(this);"  id="lastName" size="30"/></td>
                                                                <td class="fieldLabel">MI:</td>
                                                                <td><s:textfield name="middleName"  cssClass="inputTextBlue" onchange="fieldLengthValidator(this);changeCase(this);"  id="middleName" size="30"/></td> 
                                                                
                                                                
                                                            </tr>
                                                            
                                                            <tr>
                                                                <td class="fieldLabel">Alias Name:</td>
                                                                <td><s:textfield name="aliasName"  cssClass="inputTextBlue" onchange="fieldLengthValidator(this);changeCase(this);"  id="aliasName" size="20"/></td>
                                                                <td class="fieldLabel">Gender:</td>
                                                                <td><s:select name="gender" list="genderList"  cssClass="inputSelectGender"/></td>
                                                                <td class="fieldLabel">Title:</td>
                                                                <td><s:textfield name="title"  cssClass="inputTextBlue" onchange="fieldLengthValidator(this);"  id="title" size="40"/></td>
                                                                
                                                            </tr>
                                                            
                                                            <tr>
                                                                <td class="fieldLabel">Status:</td>
                                                                <td class="inputOptionText"><s:select name="contactStatus" id="contactStatus" list="contactStatusList" cssClass="inputSelect" /></td>
                                                                <td class="fieldLabel" >Specialization:</td>
                                                                <td><s:textfield name="specialization"  cssClass="inputTextBlue" onchange="fieldLengthValidator(this);changeCase(this);"  id="specialization" size="40"/></td>
                                                                
                                                                <td class="fieldLabel">Source:</td>
                                                                <td><s:textfield name="leadSource"  cssClass="inputTextBlue" onchange="fieldLengthValidator(this);changeCase(this);"   id="leadSource" size="50"/></td>
                                                            </tr>
                                                            
                                                            <tr>
                                                                
                                                                <td class="fieldLabel">Off.Phone:</td>
                                                                <td><s:textfield name="officePhone"  cssClass="inputTextBlue" onchange="return formatPhone(this);" id="officePhone" size="16"/></td>
                                                                <td class="fieldLabel">Cell Phone:</td>
                                                                <td><s:textfield name="cellPhone" cssClass="inputTextBlue" onchange="return formatPhone(this);"  onblur="return validatenumber(this)"  id="cellPhone" size="16"/></td>
                                                                <td class="fieldLabel" valign="baseline">Off.Email:</td>
                                                                <td colspan="3"> <s:textfield name="officeEmail"  cssClass="inputTextBlueLarge" onchange="fieldLengthValidator(this);allSmalls(this);" onblur="return checkEmail(this);" id="officeEmail" size="50"/> </td>            
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel">Fax:</td>
                                                                <td><s:textfield name="fax"  cssClass="inputTextBlue" onchange="fieldLengthValidator(this);" id="fax" size="16"/></td>
                                                                <td class="fieldLabel">Home Phone:</td>
                                                                <td><s:textfield name="homePhone"  cssClass="inputTextBlue" onchange="return formatPhone(this);"  id="homePhone" size="16"/></td>
                                                                <td class="fieldLabel" valign="baseline">Pers.Email:</td>
                                                                <td colspan="5" align="left"><s:textfield name="personalEmail"  cssClass="inputTextBlueLarge" onchange="fieldLengthValidator(this);allSmalls(this);" onblur="return checkEmail(this);" id="personalEmail" size="50"/></td> 
                                                            </tr>
                                                             
                                                            <tr>
                                                                <td colspan="6">
                                                                    <span class="fieldLabel">Comments:</span>
                                                                    <s:textarea name="comments" cols="100" rows="2"  cssClass="inputTextarea" onchange="fieldLengthValidator(this);" id="comments"/>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                        <%--  </sx:div> --%>
                                                    </div>
                                                    
                                                    <%-- <sx:div id="contactAddresses" label="Other Details">  --%>
                                                    <div id="contactAddresses" class="tabcontent"  >
                                                        <table cellpadding="1" cellspacing="1" border="0" width="100%">
                                                            <tr>
                                                                <td class="fieldLabel">DOB:</td>
                                                                <td><s:textfield name="DOB" cssClass="inputTextBlueSmall" value=""/><a href="javascript:cal1.popup();">
                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                </td>
                                                                <td class="fieldLabel">DOA:</td>
                                                                <td><s:textfield name="DOA" cssClass="inputTextBlueSmall" value=""/><a href="javascript:cal2.popup();">
                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                </td>
                                                                <td class="fieldLabel">Ref.By:</td>
                                                                <td><s:textfield name="referredBy" id="referredBy" cssClass="inputTextBlue" onchange="fieldLengthValidator(this);changeCase(this);"/></td>
                                                            </tr>
                                                            <tr class="headerText">
                                                                <td colspan="3" align="center">Office Address</td>
                                                                <td colspan="3" align="center">Home Address</td>
                                                            </tr>
                                                            <tr>
                                                                <td colspan="3" align="center">
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
                                                                <td colspan="3" align="center">
                                                                    <table width="400" cellpadding="1" cellspacing="1" border="0">
                                                                        <tr>
                                                                            <td class="fieldLabel">Add.L1:</td>
                                                                            <td colspan="3"> <s:textfield name="resAddressLine1" id="resAddressLine1" cssClass="inputTextBlueAddress" onchange="fieldLengthValidator(this);changeCase(this);"/> </td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td class="fieldLabel">Add.L2:</td>
                                                                            <td colspan="3"><s:textfield name="resAddressLine2" id="resAddressLine2" cssClass="inputTextBlueAddress" onchange="fieldLengthValidator(this);changeCase(this);"/> </td> 
                                                                        </tr>
                                                                        <tr>
                                                                            <td class="fieldLabel">City:</td>
                                                                            <td><s:textfield name="resCity" id="resCity" cssClass="inputTextBlue" onchange="fieldLengthValidator(this);changeCase(this);"/> </td>
                                                                            <td class="fieldLabel">M.Stop:</td>
                                                                            <td><s:textfield name="resMailStop" id="resMailStop" cssClass="inputTextBlue" onchange="fieldLengthValidator(this);"/></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td class="fieldLabel">State:</td>
                                                                            
                                                                            <td>
                                                                                <s:select 
                                                                                    list="resStatesList"   
                                                                                    name="resState" 
                                                                                    id="resState"
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
                                                                                    cssClass="inputSelect"
                                                                                    headerKey="-1"
                                                                                    headerValue="--Please Select--"
                                                                                    onchange="getResStateData();"
                                                                                    onkeypress="return handleEnter(this,event);" theme="simple"/>
                                                                            </td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td class="fieldLabel">Zip:</td>
                                                                            <td><s:textfield name="resZip" id="resZip" cssClass="inputTextBlue"  onchange="fieldLengthValidator(this);"/></td>
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
                                                        <%-- </sx:div > --%>
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
                                            </s:form>
                                            
                                            <!--//START TABBED PANNEL : -->
                                        <%--    <sx:tabbedpanel id="contactListPannel" cssStyle="width: 845px; height: 250px;padding-left:10px;padding-top:5px;" doLayout="true"> --%>
                                                 <ul id="accountTabs1" class="shadetabs" >
                                                    <li ><a href="#" class="selected" rel="contactListTab"  > Contacts List</a></li>
                                                    
                                                </ul>
                                                <div  style="border:1px solid gray; width:840px;height: 250px; overflow:auto; margin-bottom: 1em;">
                                                    <!--//START TAB : -->
                                                    <%--  <sx:div id="contactListTab" label="Contacts List" cssStyle="overflow: auto;"> --%>
                                                    <div id="contactListTab" class="tabcontent"  >
                                                        
                                                        <%
                                                        
                                                        /* String Variable for storing current position of records in dbgrid*/
                                                        strTmp = request.getParameter("txtCurr");
                                                        int intCurr = 1;
                                                        if (strTmp != null)
                                                        intCurr = Integer.parseInt(strTmp);
                                                        
                                                        /* Specifing Shorting Column */
                                                        strSortCol = request.getParameter("Colname");
                                                        if (strSortCol == null) strSortCol = "FirstName";
                                                        strSortOrd = request.getParameter("txtSortAsc");
                                                        if (strSortOrd == null) strSortOrd = "ASC";
                                                        
                                                        if(request.getAttribute("currentAccountId") != null){
                                                        currentAccountId = request.getAttribute("currentAccountId").toString();
                                                        }
                                                        
                                                       // try{
                                                        
                                                        /* Getting DataSource using Service Locator */
                                                        //connection = ConnectionProvider.getInstance().getConnection();
                                                        
                                                        /* Sql query for retrieving resultset from DataBase */
                                                        /*queryString="SELECT Id,Salutation, Gender,LastName,FirstName,AliasName,OfficePhone,Email1,Title from tblCrmContact";
                                                        queryString = queryString + " WHERE AccountId="+currentAccountId;*/
                                                        
                                                        queryString="SELECT Id,Salutation, Gender,LastName,FirstName,AliasName,OfficePhone,Email1,Title from tblCrmContact";
                                                        queryString = queryString + " WHERE AccountId="+currentAccountId+" GROUP BY Salutation, Gender,LastName,FirstName,AliasName,OfficePhone,Email1,Title ORDER BY CreatedDate DESC";
                                                        
                                                        
                                                        //out.print(queryString);
                                                        
                                                        %>
                                                    <form action="" name="frmDBGrid" method="post"> 
                                                            <table cellpadding="0" cellspacing="0" width="100%">
                                                                <tr>
                                                                    <td class="headerText">
                                                                        <input type="hidden" name="accId" id="accId" value="<%=currentAccountId%>">
                                                                        <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td colspan="2">
                                                                     <!-- New    -->
                                                                     <div id="consultantList" style="display: block">
                                                                         <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                         <table id="tblUpdate" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='100%' align="center">
                                                                              <COLGROUP ALIGN="left" >
                                                                                 <COL width="4%">
                                                                                 <COL width="6%">
                                                                                 <COL width="12%">
                                                                                 <COL width="12%">
                                                                                 <COL width="10%">
                                                                                 <COL width="25%">
                                                                                 <COL width="15%">
                                                                                 <COL width="20%">
                                                                                 <%--<COL width="10%">--%>
                                                                         </table>
                                                                         <br>
                                                                                  <center><span id="spnFast" class="activeFile" style="display: none;"></span></center>
                                                                    </div>
                                                                     <!-- END -->
                                                                    </td>
                                                                </tr>
                                                            </table>    
                                                            
                                                        </form>

                                                        
                                                     
                                                        <%-- </sx:div> --%>
                                                    </div>
                                                    <!--//END TAB : -->
                                                
                                                    <%--  </sx:tabbedpanel> --%>
                                                </div>
                                            <!--//END TABBED PANNEL : --> 
                                            <script type="text/javascript">

var countries=new ddtabcontent("accountTabs1")
countries.setpersist(false)
countries.setselectedClassTarget("link") //"link" or "linkparent"
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
		
		getContactaddContactList();
		
                
		});
		</script>
		
    </body>
</html>
