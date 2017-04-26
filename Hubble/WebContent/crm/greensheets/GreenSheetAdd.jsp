<!--/*******************************************************************************
/*
 * @(#)GreenSheetAdd.java	September 24, 2007, 12:13 AM 
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */ -->
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
 <%@ taglib prefix="sx" uri="/struts-dojo-tags" %> 
<%@ page import="com.mss.mirage.crm.greensheets.GreenSheetVTO,java.util.Date" %>
<%@ page import="com.mss.mirage.util.ApplicationConstants" %>

<html>
    <head>
        <title>Hubble Organization Portal :: New GreenSheet</title>
        <sx:head cache="false"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/javascript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/javascripts/GreenSheetUtil.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>  
        <script type="text/javascript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/javascripts/GreenSheetClientValidation.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/javascripts/StringUtility.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/StandardClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmpStandardClientValidations.js"/>"></script> 
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <s:include value="/includes/template/headerScript.html"/>
        <style type="text/css">
            
            .popupItem:hover {
            background: #F2F5A9;
            font: arial;
            font-size:10px;
            color: black;
            }
            
            .popupRow {
            background: #3E93D4;
            }
            
            
            .popupItem {
            padding: 2px;
            width: 100%;
            border: black;
            font:normal 9px verdana;
            color: white;
            text-decoration: none;
            line-height:13px;
            z-index:100;
            }
            
        </style>
<script language="javascript">
function printdiv(printpage)
{
var headstr = "<html><head><title></title></head><body>";
var footstr = "</body>";
var newstr = document.all.item(printpage).innerHTML;
var oldstr = document.body.innerHTML;
document.body.innerHTML = headstr+newstr+footstr;
window.print();
document.body.innerHTML = oldstr;
return false;
}
</script>

   
        
    </head>
    <%! String createdDate=new java.text.SimpleDateFormat("MM/dd/yyyy").format(new java.util.Date()); %>
   <!-- <body class="bodyGeneral" onload="init(); test();" oncontextmenu="return false;"> -->
    <body class="bodyGeneral" oncontextmenu="return false;">
        
        <!--//START MAIN TABLE : Table for template Structure-->
        <table class="templateTable" align="center" cellpadding="0" cellspacing="0" border="0">
            
            <!--//START HEADER : Record for Header Background and Mirage Logo-->
            <tr class="headerBg">
                <td valign="top">
                    <s:include value="/includes/template/Header.jsp"/>                    
                </td>
            </tr>
            
            <tr>
                <td>
                    <table class="innerTable" cellpadding="0" cellspacing="0" border="0">
                        <tr>
                            
                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                            <td width="150px;" class="leftMenuBgColor" valign="top">
                                <s:include value="/includes/template/LeftMenu.jsp"/>
                            </td>
                            
                            <td width="850px" class="cellBorder" valign="top">
                                <table cellpadding="0" cellspacing="0" width="100%" border="0"> 
                                    <%--    <tr>
                                        <td>
                                            <s:if test="#request.accountName != null">
                                                <span class="fieldLabel">Account Name :</span>&nbsp;
                                                <a class="navigationText" href="<s:url action="../accounts/getAccount"><s:param name="id" value="%{accountId}"/></s:url>">
                                                    <s:property value="#request.accountName"/>
                                                </a>    
                                            </s:if>
                                        </td>
                                    </tr>--%>
                                   
                                    <tr>
                                        <td valign="top" style="padding-left:5px;padding-top:5px;">
                                            <!--//START TABBED PANNEL : -->
                                            <ul id="GreenSheetTab" class="shadetabs" >
                                                <li ><a href="#" class="selected" rel="greenSheetAdd"  > Green Sheet </a></li>
                                                
                                            </ul>
                                            
                                            <%-- <sx:tabbedpanel id="addGreenSheetPannel" cssStyle="width: 830px; height:670px;padding-left:5px;padding-top:5px;" doLayout="true"> --%>
                                            <div  style="border:1px solid gray; width:830px;height: 670px; overflow:auto; margin-bottom: 1em;">
                                                <!--//START TAB : -->
                                                <%--   <sx:div  id="addGreenSheetTab" name="addGreenSheetTab" label="GreenSheet" cssStyle="overflow: auto;"> --%>
                                                <div id="greenSheetAdd" class="tabcontent">      
                                                    <s:form  name="frmAddGreenSheet" action="%{greensheetVTO.actionType}" theme="simple" method="POST" enctype="multipart/form-data" onsubmit="return checkGreenSheetForm();"> 
                                                        
                                                        <s:hidden name="id" value="%{greensheetVTO.id}" />
                                                        <s:hidden name="objectId" value="" /> 
                                                        <s:hidden name="requestType" value=""/>
                                                        <s:hidden name="uploadFileName" value="" />  
                                                        <s:hidden name="filepath" value="" /> 
                                                        <s:hidden name="date" value="" />                                                            
                                                        <s:hidden name="teamGreensheets" value="%{teamGreensheets}"/>  
                                                        <s:hidden id="onlyGreensheet" name="onlyGreensheet" value="0"/>
                                                        <input TYPE="hidden" NAME="accountEdit" VALUE="<%=request.getParameter("accountEdit")%>">
                                                        <input TYPE="hidden" NAME="accId" VALUE="<%=request.getParameter("accId")%>">
                                                        <table border="0" cellpadding="0" cellspacing="1" width="100%">
                                                            
                                                            <%--      
                                                            <a href="<s:url value="../accounts/crmBackToList.action">
                                                                           <s:param name="id" value="%{accountId}"/>
                                                                       </s:url>"
                                                            
                                                            <a href="<s:url action="getProject">
                                                            <s:param name="id" value="%{id}"/>
                                                            </s:url>" class="navigationText"><s:property value="#request.projectName"/>
                                                            </a>                                                            
                                                            --%>
                                                            
                                                            <tr class="headerText">
                                                                <td align="left" class="headerTextNormal">Placement Info</td>
                                                                <td colspan="3" align="right">
                                                                    <s:property value="#request.resultMessage"/>
                                                                    <%--
                                                                    
                                                                    <a href="<s:url value="../greensheets/greenSheet.action"/>"
                                                                       <img alt="Back to List"
                                                                         src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                         width="66px" 
                                                                         height="19px"
                                                                         border="0" align="bottom"></a>&nbsp;&nbsp;
                                                                    --%>
                                                                 
                                                                        <s:if test="#session.roleName == 'Admin'">&nbsp;
                                                                        <s:if test="%{accountEdit==1}">
                                                                            <a href="<s:url value="../accounts/getAccount.action?id=%{accId}&pri=All"/>" style="align:center;">
                                                                                <img alt="Back to List"
                                                                                     src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                                     width="66px" 
                                                                                     height="19px"
                                                                                 border="0" align="bottom"></a>&nbsp;&nbsp;
                                                                        </s:if><s:else>
                                                                            <a href="<s:url value="../greensheets/greenSheet.action"/>" style="align:center;">
                                                                                <img alt="Back to List"
                                                                                     src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                                     width="66px" 
                                                                                     height="19px"
                                                                                 border="0" align="bottom"></a>&nbsp;&nbsp;
                                                                        </s:else>
                                                                             

                                                                      </s:if>
                                                                                 <s:elseif test="#session.roleName == 'Sales'"></span>&nbsp;
                                                                        <s:if test="%{accountEdit==1}">
                                                                            <a href="<s:url value="../accounts/getAccount.action?id=%{accId}"/>" style="align:center;">
                                                                                <img alt="Back to List"
                                                                                     src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                                     width="66px" 
                                                                                     height="19px"
                                                                                 border="0" align="bottom"></a>&nbsp;&nbsp;
                                                                        </s:if><s:else>
                                                                            
                                                                          
                                                                                <a href="<s:url value="../greensheets/greenSheet.action?teamValue=%{teamGreensheets}"/>" style="align:center;">
                                                                                <img alt="Back to List"
                                                                                     src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                                     width="66px" 
                                                                                     height="19px"
                                                                                 border="0" align="bottom"></a>&nbsp;&nbsp;
                                                                           
                                                                            
                                                                            
                                                                        </s:else>
                                                                             

                                                                      </s:elseif>
                                                                      <s:elseif test="#session.roleName == 'Operations'">
                                                                           <a href="<s:url value="../greensheets/greenSheet.action"/>" style="align:center;">
                                                                                <img alt="Back to List"
                                                                                     src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                                     width="66px" 
                                                                                     height="19px"
                                                                                 border="0" align="bottom"></a>&nbsp;&nbsp;
                                                                      </s:elseif>
																	  
																	  

                                                                      <%--<s:property value="%{modeType}"/>--%>
                                                                      
                                                                      <s:if test="%{modeType == 'Add Greensheet'}">
                                                                          <s:submit value="Save" cssClass="buttonBg"/>
                                                                      </s:if>
                                                                      <s:else>
                                                                          <s:submit value="Update" cssClass="buttonBg"/>
                                                                      </s:else>
                                                                      

                                                                    
                                                                </td>
                                                            </tr>
                                                            
                                                            <tr> 
                                                                <td class="fieldLabel">Consultant Type</td>
                                                                <td class="inputOptionText">
                                                                    <s:hidden name="consultantTypeId" id="consultantTypeId" value="%{greensheetVTO.consultantType}" />
                                                                    <s:radio id="contactStatus" name="consultantType"  list="{'Internal'}"  cssClass="inputOptionSmall" onclick="hideVendorDetails();"/>
                                                                    <s:radio id="contactStatus" name="consultantType" list="{'External'}" cssClass="inputOptionSmall" onclick="showVendorDetails();" />
                                                                    
                                                                    
                                                                </td>
                                                                <td class="fieldLabel">PO Type : </td>
                                                                <td><s:select name="poType" id="poType" list="{'Services','Expenses','FixedBid','Software', 'Support','Others'}" 
                                                                              value="%{greensheetVTO.poType || 'Service'}" cssClass="inputSelect" onchange="getOtherDivs();"/></td>
                                                                
                                                            </tr>
                                                            <tr>
                                                                <td  class="fieldLabel">Customer Name : <FONT color="red" SIZE="0.5"><em>*</em></FONT></td>
                                                                <td>
                                                                    <%--<s:textfield name="customerName" id="customerName" onkeyup="fillCustomer();" cssClass="inputTextBlue"  value="%{greensheetVTO.customerName}"  theme="simple" readonly="true"/> --%>
                                                                   <s:textfield cssClass="inputTextBlue"  value="%{greensheetVTO.customerName}"  theme="simple" readonly="true"/>
                                                                    <s:hidden name="customerName" id="customerName" value="%{greensheetVTO.customerName}"/>
                                                                    <!--<div id="validationMessage"></div>-->
                                                                   
                                                                    <s:hidden name="consultantId" id="consultantId" value="%{accountId}" /> 
                                                                </td>   
                                                                
                                                                <td class="fieldLabel">Country : <FONT color="red" SIZE="0.5"><em>*</em></FONT></td>                                                                
                                                                <td>
                                                                    <s:select 
                                                                        list="countryList" 
                                                                        name="country" 
                                                                        id="country" 
                                                                        cssClass="inputSelect"
                                                                        headerKey="-1"
                                                                        headerValue="--Please Select--"
                                                                        theme="simple"
                                                                        value="%{greensheetVTO.country}"/>
                                                                </td>
                                                                <%-- <td  align="center">  <s:submit value="Add " cssClass="buttonBg" onclick='document.frmAddGreenSheet.action="./viewAccount.action"'/> --%>                                                                
                                                            </tr>
                                                            
                                                            <tr>
                                                                <td class="fieldLabel">Green Sheet Status : </td>
                                                                <td><s:select list="{'Created','NotKnown','Renewal','Approved','Rejected'}" value="%{greensheetVTO.status || 'Created'}"  name="status" id="status" cssClass="inputSelect"/></td>
                                                                <td class="fieldLabel">PO Status :</td>
                                                                <td><s:select list="{'Open','Received','Cancelled','Closed'}" value="%{greensheetVTO.poStatus || 'Open'}"  name="poStatus" id="poStatus" cssClass="inputSelect" /></td>
                                                            </tr>                                           
                                                
                                                            <tr>
                                                                <td class="fieldLabel">PO Renewal Email : </td>
                                                                <td><s:textfield name="renewalEmail" id="renewalEmail" cssClass="inputTextBlue" value="%{greensheetVTO.renewalEmail}"
                                                                                 onblur="validateEmail(this);"/>
                                                                <td class="fieldLabel">PO Renewal Int. Email : </td>
                                                                <td><s:textfield name="renewalIntEmail" id="renewalIntEmail" cssClass="inputTextBlue" value="%{greensheetVTO.renewalIntEmail}"                                                                    
                                                                                 onblur="validateEmail(this);"/>
                                                            </tr>
                                                            
                                                            <tr>                                                                
                                                                <td class="fieldLabel">PO Number : </td>
                                                                <td ><s:textfield name="poNumber" id="poNumber" cssClass="inputTextBlue" value="%{greensheetVTO.poNumber}"/>
                                                                <td class="fieldLabel">PO LineNo : </td>
                                                                <td><s:textfield name="poLineno" id="poLineno" cssClass="inputTextBlue" value="%{greensheetVTO.poLineno}"/>
                                                            </tr>
                                                            
                                                            <tr>                                                              
                                                                <td class="fieldLabel"> <label id="poStartDateLabel">PO Start Date : </label><FONT color="red" SIZE="0.5"><em>*</em></FONT></td>
                                                                <td><s:textfield id="startDate" name="startDate" value="%{greensheetVTO.startDate}" cssClass="inputTextBlue" onchange="checkDates(this);"/>
                                                                    <a href="javascript:cal1.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                         width="20" height="20" border="0" ></a>
                                                                         
                                                                </td>
                                                                
                                                                <td class="fieldLabel" id = "poEndDateTDLabel">
                                                                <label id = "poEndDateLabel">PO End Date :</label> <FONT color="red" SIZE="0.5"><em>*</em></FONT></td>
                                                                <td id = "poEndDateTDText"><s:textfield id="endDate" name="endDate" value="%{greensheetVTO.endDate}"  cssClass="inputTextBlue" onchange="checkDates(this);"/>
                                                                    <a href="javascript:cal2.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                         width="20" height="20" border="0" ></a>
                                                                </td>                                                                
                                                            </tr>
                                                            
                                                            <tr>
                                                                <td class="fieldLabel">Internal RefCode : </td>
                                                                <td><s:textfield name="intRefcode" id="intRefcode"  cssClass="inputTextBlue" value="%{greensheetVTO.intRefcode}"/>
                                                                <td class="fieldLabel">MSS Invoice No : </td>
                                                                <td><s:textfield name="invoiceNo" id="invoiceNo"  cssClass="inputTextBlue" value="%{greensheetVTO.invoiceNo}"/>
                                                                
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel"><label id="reportManagerLabel">Reporting Manager : </label></td>
                                                                <td ><s:textfield name="reportingManager" size="50" cssClass="inputTextBlue" value="%{greensheetVTO.reportingManagerName}" onchange="fieldLengthValidator1(this);changeCase(this);" id="reportingManager"  theme="simple"/></td>
                                                                <td class="fieldLabel"><label id="reportPhoneLabel">Reporting Phone : </label></td>
                                                                <td><s:textfield name="reportingMGRPhone" id="reportingMGRPhone" cssClass="inputTextBlue" value="%{greensheetVTO.reportingMGRPhone}" onchange="return formatPhone(this);" size="25" theme="simple"></s:textfield></td>
                                                            </tr>
                                                            
                                                            <tr>
                                                                <td class="fieldLabel"><label id="reportEmailLabel">Reporting Manager Email : </label></td><!--onblur="valueCheck(document.frmAddGreenSheet.reportingManagerEmail.value)" -->
                                                                <td><s:textfield name="reportingManagerEmail" size="50" cssClass="inputTextBlue" value="%{greensheetVTO.reportingManageremail}" onblur="validateEmail(this);allSmalls(this);"   id="reportingManagerEmail" theme="simple"/></td>
                                                                
                                                                <td class="fieldLabel">Expenses : </td>
                                                                <td><s:select list="{'NONE','ClientPaid','Mss Paid'}" value="%{greensheetVTO.expenses || 'NONE'}"  name="expenses" cssClass="inputSelect" /></td>
                                                                
                                                            </tr>
                                                            <tr> 
                                                              <td class="fieldLabel">Practice:</td>
                                                                                         
                                                             <td><s:select cssClass="inputTextBlue" list="practiceList" name="practiceName" id="practiceName" onchange="" headerValue="--Please Select--" headerKey="" value="%{greensheetVTO.practiceName}"/></td>
                                                             </tr>
                                                            <tr>
                                                                <td class="fieldLabel">Attach PO : </td>
                                                                <td align="left" colspan="3" ><s:file name="upload" cssClass="inputTextBlueLargeAccount" id="attachmentFileName" onchange="validateFileSize(this);attachmentFileNameValidate();" theme="simple"/><FONT  color="red" SIZE="0.5"><em>Note: (Maximum File Upload Limit 2MB)</em></FONT></td>
                                                                
                                                            </tr>
                                                            
                                                            <tr>
                                                                <td  class="fieldLabel">Project Name : </td>
                                                                <td colspan="3"><s:textfield name="projectName" id="projectName" cssClass="inputTextBlueExtraLarge"  value="%{greensheetVTO.projectName}"  theme="simple"/>
                                                                </td>                                                                    
                                                            </tr>
                                                            <s:if test="%{modeType == 'Edit Greensheet'}">
                                                               <tr id="reasonTab">
                                                                <td class="fieldLabel">Comments :<FONT color="red" SIZE="0.5"><em>*</em></FONT> </td>
                                                                <td colspan="3" >
                                                                    <s:textarea name="rejectesReason" cols="85" rows="2" cssClass="inputTextarea" id="rejectesReason" theme="simple" value="%{greensheetVTO.rejectesReason}" onchange="fieldLengthValidator1(this);" />
                                                                </td>
                                                            </tr>  
                                                            </s:if>
                                                       
                                                            
                                                            <tr>
                                                                <td colspan="4">
                                                                    <a class="navigationText" 
                                                                       href="<s:url action="../attachments/getAttachment">
                                                                           <s:param name="Id" value="%{greensheetVTO.fileId}"/>
                                                                       </s:url>"><s:property value="%{greensheetVTO.fileName}"/>
                                                                    </a>
                                                                </td>
                                                            </tr>
                                                            
                                                            
                                                            <%--- Toogle Consultant Details Starts Here ---%>
                                                            <tr  class="headerText" id="consultantTab1">
                                                                <td colspan="4" class="headerTextNormal">Consultant </td>
                                                            </tr>
                                                            <tr  class="headerText" id="consultantTab14">
                                                                <td colspan="4" class="headerTextNormal">Customer Details</td>
                                                            </tr>
                                                            
                                                            <tr id="consultantTab2">
                                                                <td class="fieldLabel">First Name : <FONT color="red" SIZE="0.5"><em>*</em></FONT></td>
                                                                <td><s:textfield name="fname"  cssClass="inputTextBlue"  value="%{greensheetVTO.firstName}" 
                                                                                     onchange="fieldLengthValidator(this);changeCase(this);"  id="firstName" theme="simple"/><div id="validationMessage"></div>
                                                                </td>
                                                                <%-- onkeyup="fillConsultant();" --%>
                                                                <td class="fieldLabel">Last Name : <FONT color="red" SIZE="0.5"><em>*</em></FONT></td>
                                                                <td><s:textfield  name="lastName" cssClass="inputTextBlue" value="%{greensheetVTO.lastName}"  
                                                                                  onchange="fieldLengthValidator(this);changeCase(this);"  id="lastName" theme="simple"/></td>
                                                                
                                                            </tr>
                                                            
                                                            <tr id="consultantTab3">
                                                                <td class="fieldLabel">Middle Name : </td>
                                                                <td><s:textfield name="middleName" cssClass="inputTextBlue" value="%{greensheetVTO.middleName}" onchange="fieldLengthValidator(this);changeCase(this);" id="middleName" theme="simple"/></td>
                                                                <td class="fieldLabel">Phone : </td>
                                                                <td ><s:textfield name="phone" cssClass="inputTextBlue" value="%{greensheetVTO.phone}"   onchange="return formatPhone(this);"  id="phone" theme="simple"/></td>
                                                            </tr>
                                                            
                                                            <tr id="softwareTab10">
                                                            <td class="fieldLabel">ContactFax :</td>
                                                            <td><s:textfield name="contactFaxNumber" value="%{greensheetVTO.ContactFaxNumber}"  cssClass="inputTextBlue" onchange="fieldLengthValidator1(this);" id="contactFaxNumber" size="16"/></td>
                                                             </tr> 
                                                            
                                                            
                                                            <tr id="consultantTab4">
                                                                <td class="fieldLabel">Duration : </td>
                                                                <td><s:textfield name="duration" id="duration" size="15" onchange="countPoEndDate();" cssClass="inputTextBlue" onblur="return validatenumber(this);" value="%{greensheetVTO.duration}" theme="simple"/><b class="fieldLabel">(Months)</b></td>
                                                                <td class="fieldLabel">Units : <FONT color="red" SIZE="0.5"><em>*</em></FONT></td>
                                                                <td><s:select list="{'Hourly','Daily','Monthly'}" name="units" id="units" value="%{greensheetVTO.units || 1}" cssClass="inputSelect"  /></td>
                                                                
                                                            </tr>
                                                            
                                                            <tr id="consultantTab5">
                                                                <td class="fieldLabel">Max Qty : </td>
                                                                <td><s:textfield name="maxQuantity" id="maxQuantity" cssClass="inputTextBlue" value="%{greensheetVTO.maxQuantity}"/></td>
                                                                <td class="fieldLabel">Total Value : </td>
                                                                <td><s:textfield name="totalValue" id="totalValue" cssClass="inputTextBlue" value="%{greensheetVTO.totalValue}"/></td>
                                                            </tr>
                                                            
                                                            <tr id="consultantTab6">
                                                                <td class="fieldLabel">Location : </td>
                                                                <td><s:textfield name="location" id="location" cssClass="inputTextBlue" value="%{greensheetVTO.location}"/></td> 
                                                                <td class="fieldLabel">Employee Code : </td>
                                                                <td><s:textfield name="empCode" id="empCode" cssClass="inputTextBlue" value="%{greensheetVTO.empCode}"/> </td>
                                                                
                                                            </tr>
                                                            
                                                            <tr id="consultantTab7">
                                                                <td class="fieldLabel">Client Billing Rate : <FONT color="red" SIZE="0.5"><em>*</em></FONT></td>
                                                                <td  valign="top"><s:textfield name="clientBillingRate" id="clientBillingRate" size="15" cssClass="inputTextBlue" onblur="return checkDecimal(frmAddGreenSheet.clientBillingRate.value);" value="%{greensheetVTO.clientBillingRate}" />
                                                                    &nbsp;&nbsp; 
                                                                    <s:select list="clientCurrencyMap" name="clientBillingRateType" id="clientBillingRateType" value="%{greensheetVTO.clientBillingRateType || 6}" cssClass="inputSelectSmallCurr" /><FONT color="red" SIZE="0.5"><em>*</em></FONT>
                                                                    <s:submit value="...."  cssClass="buttonBg" onclick="return currencyPopup('CurrencyInfo.jsp')" align="left"/>
                                                                </td>
                                                                <td class="fieldLabel">OT Allowed : </td>
                                                                <td><s:select list="{'Yes','No'}" value="%{greensheetVTO.otallowed || 'No'}" name="otAllowed" cssClass="inputSelect" />
                                                                </td>
                                                            </tr>
                                                            
                                                            <tr id="consultantTab8">
                                                                <td class="fieldLabel">Expenses Details : </td>
                                                                <td colspan="3" >
                                                                    <s:textarea name="expensesDetails" cols="85" rows="2" cssClass="inputTextarea" value="%{greensheetVTO.expenseDetail}" onchange="fieldLengthValidator1(this);changeCase(this);" id="expensesDetails"/>
                                                                </td>
                                                            </tr>
                                                            
                                                            <tr id="consultantTab9">
                                                                <td class="fieldLabel"><label>Equipment Needed : </td>
                                                                <td colspan="3" >
                                                                    <s:textarea name="equipmentNeeded"  cols="85" rows="2" cssClass="inputTextarea" value="%{greensheetVTO.equipmentNeeded}" onchange="fieldLengthValidator1(this);changeCase(this);"  id="equipmentNeeded"/>
                                                                </td>                                                                             
                                                            </tr>
                                                            
                                                            <tr id="consultantTab10">
                                                                <td class="fieldLabel">Reporting Address : </td>
                                                                <td colspan="3" >
                                                                    <s:textarea name="reportingAddress" cols="85" rows="2" cssClass="inputTextarea" value="%{greensheetVTO.reportingAddress}" onchange="fieldLengthValidator1(this);changeCase(this);"  id="reportingAddress"/>
                                                                </td>
                                                            </tr>
                                                            
                                                            <tr id="consultantTab11">
                                                                <td class="fieldLabel">Consultant Start Date : <FONT color="red" SIZE="0.5"><em>*</em></FONT></td>
                                                                <td><s:textfield id="consStartDate" name="consStartDate" value="%{greensheetVTO.consStartDate}" cssClass="inputTextBlue" onchange="checkDates(this);"/>
                                                                    <a href="javascript:cal3.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                         width="20" height="20" border="0" ></a>
                                                                </td>
                                                                <td class="fieldLabel">Consultant End Date : </td>
                                                                <td><s:textfield id="consEndDate" name="consEndDate" value="%{greensheetVTO.consEndDate}"  cssClass="inputTextBlue" onchange="checkDates(this);"/>
                                                                    <a href="javascript:cal4.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                         width="20" height="20" border="0" ></a>
                                                                </td>                                                                
                                                            </tr>
                                                            
                                                            <tr id="consultantTab12">
                                                                <td class="fieldLabel">Noc : </td>
                                                                <td><s:select name="nocValue" id="nocValue" list="{'No','Yes'}" cssClass="inputSelect" value="%{greensheetVTO.nocValue || 'No'}"/></td>
                                                                
                                                                <td class="fieldLabel">Noc Date : </td>
                                                                <td><s:textfield id="nocDate" name="nocDate" cssClass="inputTextBlue" value="%{greensheetVTO.nocDate}" onchange="checkDates(this);"/>
                                                                    <a href="javascript:cal5.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                         width="20" height="20" border="0" ></a>
                                                                </td>
                                                                
                                                            </tr>
                                                            
                                                            <tr id="consultantTab13">
                                                                <td class="fieldLabel">Comments : </td>
                                                                <td colspan="3" >
                                                                    <s:textarea name="comments" cols="85" rows="2" cssClass="inputTextarea" id="comments" theme="simple" value="%{greensheetVTO.comments}" onchange="fieldLengthValidator1(this);"/>
                                                                </td>
                                                            </tr>                                                            
                                                            <%--- Toogle Consultant Details End Here ---%>
                                                            
                                                            
                                                            
                                                            <%--- Toogle Software / Fixed Bid Details Starts Here ---%>
                                                            <tr class="headerText" id="softwareTab1">
                                                                <td colspan="4" class="headerTextNormal"><label id="softwareHeaderLabel">Resale Information </label></td>
                                                                
                                                            </tr>
                                                            
                                                            <tr id="softwareTab2">
                                                                <td class="fieldLabel">Customer/Location : </td>
                                                                <td><s:textfield id="customerLocation" name="customerLocation" cssClass="inputTextBlue" value="%{greensheetVTO.customerLocation}"/></td> 
                                                                
                                                                <td class="fieldLabel">Resale Date : </td>
                                                                <td><s:textfield id="resaleDate" name="resaleDate" cssClass="inputTextBlue" value="%{greensheetVTO.resaleDate}" onchange="checkDates(this);"/>
                                                                    <a href="javascript:cal6.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                         width="20" height="20" border="0" ></a>
                                                                </td>
                                                            </tr>
                                                            
                                                            <tr id="softwareTab3">
                                                                <td class="fieldLabel"><label id="poDetailsLabel">Software Details : </td>
                                                                <td colspan="3">
                                                                    <s:textarea name="softDetails"  cols="85" rows="2" cssClass="inputTextarea" id="softDetails" value="%{greensheetVTO.softDetails}" onchange="fieldLengthValidator1(this);"/>
                                                                </td>                                                                            
                                                            </tr>
                                                            
                                                             <tr id="softwareTab8">
                                                                <td class="fieldLabel">Type of Resale : </td>
                                                                <td  ><s:textarea name="typeOfResale" cols="40"  rows="3" cssClass="inputTextarea" value="%{greensheetVTO.typeOfResale}"  id="typeOfResale" theme="simple" onchange="fieldLengthValidator1(this);"></s:textarea></td>
                                                                <td class="fieldLabel">Part Number : </td>
                                                                <td><s:textfield name="partNumber" id="partNumber" cssClass="inputTextBlue" value="%{greensheetVTO.partNumber}" onchange="fieldLengthValidator1(this);" /></td>                                                           
                                                            </tr>
                                                            
                                                            <tr id="softwareTab4">
                                                                <td class="fieldLabel"><label id="custPriceLabel">Customer Price : </label></td>
                                                                <td><s:textfield id="customerPrice" name="customerPrice"  cssClass="inputTextBlue" value="%{greensheetVTO.customerPrice}"/></td> 
                                                                
                                                                <td class="fieldLabel"><label id="ourPriceLabel">Avnet Price : </label></td>
                                                                <td><s:textfield id="avnetPrice" name="avnetPrice"  cssClass="inputTextBlue" value="%{greensheetVTO.avnetPrice}"/></td>                                                                             
                                                            </tr>
                                                            
                                                            <tr id="softwareTab5">
                                                                <td class="fieldLabel">IBM ICSF/SVI : </td>
                                                                <td><s:textfield id="ibmIcsf" name="ibmIcsf" cssClass="inputTextBlue" value="%{greensheetVTO.ibmIcsf}"/></td> 
                                                                
                                                                <td class="fieldLabel">Additional Services : </td>
                                                                <td><s:textfield id="addServices" name="addServices" cssClass="inputTextBlue" value="%{greensheetVTO.addServices}"/></td>                                                                             
                                                            </tr>
                                                            
                                                            <tr id="softwareTab6">
                                                                <td class="fieldLabel">Sales Tax : </td>
                                                                <td><s:textfield id="salesTax" name="salesTax" cssClass="inputTextBlue" value="%{greensheetVTO.salesTax}"/></td> 
                                                                
                                                                <td class="fieldLabel">Sales Tax State: </td>
                                                                <td><s:textfield id="salesTaxState" name="salesTaxState" cssClass="inputTextBlue" value="%{greensheetVTO.salesTaxState}"/></td>                                                                             
                                                            </tr>
                                                            
                                                            <tr id = "softwareTab7">
                                                                <td class="fieldLabel">Profit/Loss Amount : </td>
                                                                <td><s:textfield id="profitAmt" name="profitAmt"  cssClass="inputTextBlue" value="%{greensheetVTO.profitAmt}"/></td> 
                                                                
                                                                <td class="fieldLabel">Profit/Loss(%) : </td>
                                                                <td><s:textfield id="profitPercent" name="profitPercent"  cssClass="inputTextBlue" value="%{greensheetVTO.profitPercent}"/></td>                                                                             
                                                            </tr> 
                                                            
                                                             <%--<tr id="softwareTab9">
                                                                <td class="fieldLabel">Contact Name: </td>
                                                                <td><s:textfield id="contactName" name="contactName" cssClass="inputTextBlue" value="%{greensheetVTO.contactName}" onchange="fieldLengthValidator1(this);"/></td> 
                                                             <td class="fieldLabel">Contact Phone : </td>
                                                             <td><s:textfield name="contactPhoneNumber" id="contactPhoneNumber" cssClass="inputTextBlue" value="%{greensheetVTO.contactPhoneNumber}" onchange="return formatPhone(this);" /></td> 
                                                               
                                                            </tr>--%>
                                                             
                                                            <%-- Toogle Software / Fixed Bid Details Ends Here --%>
                                                            
                                                            
                                                            <%--- Toogle Vendor Details Starts Here ---%>
                                                            <tr class="headerText" id="vendorTab1">
                                                                <td colspan="4" class="headerTextNormal">Vendor </td>
                                                            </tr>
                                                            
                                                            <tr id="vendorTab2">
                                                                <td class="fieldLabel">Agency Name : <FONT color="red" SIZE="0.5"><em>*</em></FONT></td>
                                                                <td><s:select id="agencyName" list="greensheetVendorNamesList" name="agencyName" headerKey="-1" headerValue="-- Please Select --" value="%{greensheetVTO.agencyName}" cssClass="inputSelect"/></td> 
                                                                <td class="fieldLabel">Tax ID/SSN : </td>
                                                                <td><s:textfield name="vendorTaxId" id="vendorTaxId" cssClass="inputTextBlue" value="%{greensheetVTO.vendorTaxId}" onchange="fieldLengthValidator1(this);" /></td>
                                                                
                                                            </tr>
                                                            
                                                            <tr id="vendorTab3">
                                                                <td class="fieldLabel">Vendor Phone : </td>
                                                                <td><s:textfield name="vendorPhone" id="vendorPhone" cssClass="inputTextBlue" value="%{greensheetVTO.vendorContactPhone}" onchange="return formatPhone(this);" /></td>
                                                                <td class="fieldLabel">Fax Number:</td>
                                                                <td><s:textfield name="vendorFaxNumber" value="%{greensheetVTO.vendorFaxNumber}"  cssClass="inputTextBlue" onchange="fieldLengthValidator1(this);" id="vendorFaxNumber" size="16"/></td>
                                                             </tr>  
                                                            <tr id="vendorTab5">
                                                                <td class="fieldLabel">Vendor Email : </td>
                                                                <td><s:textfield name="vendorEmail" cssClass="inputTextBlue" value="%{greensheetVTO.vendorContactEmail}" onblur="validateEmail(this)"/></td>
                                                                <td class="fieldLabel">Vendor Contact Person : </td>
                                                                <td><s:textfield name="vendorContactPerson" id="vendorContactPerson" value="%{greensheetVTO.vendorContactPerson}" cssClass="inputTextBlue" onchange="fieldLengthValidator1(this);"/></td>
                                                            </tr>
                                                            
                                                            <tr id="vendorTab4">
                                                                <td class="fieldLabel">Vendor Units : <FONT color="red" SIZE="0.5"><em>*</em></FONT></td>
                                                                <td><s:select list="{'Hourly','Daily','Monthly'}" name="vendorUnits" value="%{greensheetVTO.venUnits || 'Hourly'}" headerKey="" headerValue="" cssClass="inputSelect"/></td>
                                                                <td class="fieldLabel">Rate : <FONT color="red" SIZE="0.5"><em>*</em></FONT></td>
                                                                <td><s:textfield name="vendorRate" id="vendorRate" cssClass="inputTextBlue" value="%{greensheetVTO.venUnitRate}" onblur="return checkDecimal(frmAddGreenSheet.vendorRate.value);"/></td>
                                                            </tr>
                                                            <tr id="vendorTab7">
                                                                <td class="fieldLabel">Payment Address : </td>
                                                                <td colspan="3" >
                                                                    <s:textarea name="vendorPaymentAddress" cols="85" rows="2" cssClass="inputTextarea" id="vendorPaymentAddress" theme="simple" value="%{greensheetVTO.vendorPaymentAddress}" onchange="fieldLengthValidator1(this);"/>
                                                                </td>
                                                            </tr> 
                                                             <tr id="vendorTab6">
                                                                <td class="fieldLabel">Additional Terms(If Any) : </td>
                                                                <td colspan="3" >
                                                                    <s:textarea name="vendorComments" cols="85" rows="2" cssClass="inputTextarea" id="vendorComments" theme="simple" value="%{greensheetVTO.vendorComments}" onchange="fieldLengthValidator1(this);"/>
                                                                </td>
                                                            </tr>  
                                                            
                                                            <%--
                                                            <tr>
                                                                <td height="10px"></td>
                                                            </tr>                                                                        
                                                            --%>
                                                            
                                                            <input type="hidden" name="dateCreated"  value="<%=createdDate%>"/>
                                                            <%-- Toogle Vendor Details Ends Here --%>
                                                            
                                                            
                                                                
                                                            <tr  class="headerText">
                                                                <td colspan="4" class="headerTextNormal">Invoice </td>
                                                            </tr>
                                                            
                                                            <tr>
                                                                <td class="fieldLabel">Billing Manager : </td>
                                                                <td><s:textfield name="billingManager" value="%{greensheetVTO.billingManagerName}" cssClass="inputTextBlue" onchange="fieldLengthValidator(this);changeCase(this);" id="billingManager" size="50"/></td>
                                                                <td class="fieldLabel">Billing Manager Email : </td><!--onblur="valueCheck(document.frmAddGreenSheet.billingManagerEmail.value);allSmalls(this);" -->
                                                                <td><s:textfield name="billingManagerEmail"   cssClass="inputTextBlue" value="%{greensheetVTO.billingManageremail}" onchange="checkEmail(this);"  id="billingManagerEmail" size="30"/></td>
                                                                
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel">Billing Phone : </td>
                                                                <td><s:textfield name="billingphone" cssClass="inputTextBlue" value="%{greensheetVTO.billingManagerPhone}" onchange="return formatPhone(this);"  id="billingphone" size="25"/></td>
                                                                <td class="fieldLabel">Fax Number:</td>
                                                                <td><s:textfield name="invFaxNumber" value="%{greensheetVTO.invFaxNumber}"  cssClass="inputTextBlue" id="invFaxNumber" size="16" onchange="fieldLengthValidator1(this);"/></td>
                                                                                                                               
                                                                <%--
                                                                <td><s:select list="greensheetScopeOfWork" name="scopeOfWork" value="%{greensheetVTO.scopeOfWork || 8}"  cssClass="inputSelect" /></td>                                                                
                                                                --%>
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel">Billing Address : <FONT color="red" SIZE="0.5"><em>*</em></FONT></td>
                                                                <td  ><s:textarea name="billingAddress" cols="40"  rows="3" cssClass="inputTextarea" value="%{greensheetVTO.billingAddress}" id="billingAddress" theme="simple"></s:textarea></td>
                                                                <td class="fieldLabel" >Invoicing : </td>
                                                                <td><s:select list="{'Weekly','Bi Weekly','Monthly'}" value="%{greensheetVTO.invoicing || 3}" name="invoicing" cssClass="inputSelect" /></td>
                                                                
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel">VP Sales Approved : </td>
                                                                <td><s:select list="{'NotKnown','Approved','UnApproved'}" value="%{greensheetVTO.vpApprovalStatus || 'NotKnown'}" name="vpSalesApproved" cssClass="inputSelect" /></td>
                                                                <input type="hidden" name="empId" value="1" />
                                                                <td class="fieldLabel">Payment Terms : </td>
                                                                <td><s:select list="{'OnReceipt','net-5','net-10','net-15','net-30','net-45','net-55','net-60','net-75','net-90'}" value="%{greensheetVTO.paymentTerms || 'OnReceipt'}" name="payementTerms"  cssClass="inputSelect" /></td>
                                                            </tr>
                                                            <tr>
                                                          
                                                            
                                                             <td class="fieldLabel">Attention/Comments: </td>
                                                             <td  ><s:textarea name="invAttnComments" cols="40"  rows="3" cssClass="inputTextarea" value="%{greensheetVTO.invAttnComments}"  id="invAttnComments" onchange="fieldLengthValidator1(this);" theme="simple"></s:textarea></td>
                                                            <td class="fieldLabel" >Scope of Work : </td>
                                                                <td><s:textfield name="scopeOfWork" value="%{greensheetVTO.scopeOfWork}"  cssClass="inputTextBlue" id="scopeOfWork" /></td> 
                                                            </tr>
                                                           
                                                            <tr>
                                                                <td height="10px" colspan="4"></td>
                                                            </tr>
                                                            
                                                            <tr class="headerText">
                                                                <td colspan="4" class="headerTextNormal">Commissions </td>
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel">Primary Sales Person : <FONT color="red" SIZE="0.5"><em>*</em></FONT></td>
                                                                <%-- <td><s:select list="salesMap"  name="primarySalesPerson" value="%{greensheetVTO.priSalesPersonId}" headerKey="-1" headerValue="-- Please Select --" cssClass="inputSelect" /></td> --%>
                                                                
                                                            <s:if test="%{greensheetVTO.actionType == 'newGreenSheetSubmit' || greensheetVTO.priSalesPerStatus == 'Active'}">
                                                                <td><s:select list="salesMap"  name="primarySalesPerson" value="%{greensheetVTO.priSalesPersonId}" headerKey="-1" headerValue="-- Please Select --" cssClass="inputSelect" /></td>
                                                            </s:if>  
                                                            <s:else>
                                                                <s:hidden name="primarySalesPerson" value="%{greensheetVTO.priSalesPersonId}"/>
                                                                <td style="color: green; font-size:14px"><s:property  value="%{greensheetVTO.priSalesPerName}"/></td>
                                                            </s:else>
                                                                <td class="fieldLabel">Commission : </td>
                                                                <td ><s:textfield name="priSalesPersonCommission" value="%{greensheetVTO.priSalesPersonCommission}" onkeypress="return isNumberKey(event)" cssClass="inputTextBlueComm" size="15"/><b class="fieldLabel">%</b></td>
                                                                
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel">Primary Sales Manager : <FONT color="red" SIZE="0.5"><em>*</em></FONT></td>
                                                              <%--  <td><s:select list="salesManagerMap"  name="primarySalesManager" value="%{greensheetVTO.priSalesMgrId}" headerKey="-1" headerValue="-- Please Select --" cssClass="inputSelect" /></td> --%>
                                                                 <s:if test="%{greensheetVTO.actionType == 'newGreenSheetSubmit' || greensheetVTO.priSalesPerMgrStatus == 'Active'}">
                                                                <td><s:select list="salesManagerMap"  name="primarySalesManager" value="%{greensheetVTO.priSalesMgrId}" headerKey="-1" headerValue="-- Please Select --" cssClass="inputSelect" /></td>
                                                                 </s:if>
                                                                 <s:else>
                                                                     <s:hidden name="primarySalesManager" value="%{greensheetVTO.priSalesMgrId}"/>
                                                                      <td style="color: green; font-size:14px"><s:property  value="%{greensheetVTO.priSalesPerMgrName}"/></td> 
                                                                 </s:else>
                                                                <td class="fieldLabel">Commission : </td>
                                                                <td ><s:textfield name="priSalesMngCommission" value="%{greensheetVTO.priSalesMgrCommission}" onkeypress="return isNumberKey(event)" cssClass="inputTextBlueComm" size="15"/><b class="fieldLabel">%</b></td>
                                                                
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel">Secondary Sales Person : <FONT color="red" SIZE="0.5"><em>*</em></FONT></td>
                                                                <%-- <td><s:select list="salesMap" name="secondarySalesPerson" value="%{greensheetVTO.secSalesPersonId}" headerKey="-1" headerValue="-- Please Select --" cssClass="inputSelect" /></td> --%>
                                                                  <s:if test="%{greensheetVTO.actionType == 'newGreenSheetSubmit' || greensheetVTO.secSalesPerStatus == 'Active'}">
                                                                <td><s:select list="salesMap" name="secondarySalesPerson" value="%{greensheetVTO.secSalesPersonId}" headerKey="-1" headerValue="-- Please Select --" cssClass="inputSelect" /></td>
                                                                 </s:if>
                                                                <s:else>
                                                                      <s:hidden name="secondarySalesPerson" value="%{greensheetVTO.secSalesPersonId}"/>
                                                                       <td style="color: green; font-size:14px"><s:property  value="%{greensheetVTO.secSalesPerName}"/></td> 
                                                                </s:else>
                                                                <td class="fieldLabel">Commission : </td>
                                                                <td><s:textfield name="secondarySalesPersonCommission" cssClass="inputTextBlueComm" onkeypress="return isNumberKey(event)" value="%{greensheetVTO.secSalesPersonCommission}" size="15"/><b class="fieldLabel">%</b></td>                                                                
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel">Primary Vice President : </td>
                                                                <td><s:select list="salesManagerMap" name="primaryVicePresident" value="%{greensheetVTO.primaryVicePresidentId}" headerKey="0" headerValue="-- None --" cssClass="inputSelect" /></td>
                                                                <td class="fieldLabel">Commission : </td>
                                                                <td><s:textfield name="primaryVicePresidentCommission" cssClass="inputTextBlueComm" onkeypress="return isNumberKey(event)" value="%{greensheetVTO.primaryVicePresidentCommission}" size="15"/><b class="fieldLabel">%</b></td>                                                                
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel">Secondary Vice President : </td>
                                                                <td><s:select list="salesManagerMap" name="secondaryVicePresident" value="%{greensheetVTO.secondaryVicePresidentId}" headerKey="0" headerValue="-- None --" cssClass="inputSelect" /></td>
                                                                <td class="fieldLabel">Commission : </td>
                                                                <td><s:textfield name="secondaryVicePresidentCommission" onkeypress="return isNumberKey(event)" cssClass="inputTextBlueComm" value="%{greensheetVTO.secondaryVicePresidentCommission}" size="15"/><b class="fieldLabel">%</b></td>                                                                
                                                            </tr>
                                                            
                                                        </table>
                                                    </s:form>
                                                    
                                                    <script type="text/JavaScript">
                        var cal1 = new CalendarTime(document.forms['frmAddGreenSheet'].elements['startDate']);
	           cal1.year_scroll = true;
	            cal1.time_comp = false;
            var cal2 = new CalendarTime(document.forms['frmAddGreenSheet'].elements['endDate']);
	      cal2.year_scroll = true;
	   cal2.time_comp = false;
           
            var cal3 = new CalendarTime(document.forms['frmAddGreenSheet'].elements['consStartDate']);
	           cal3.year_scroll = true;
	            cal3.time_comp = false;
            var cal4 = new CalendarTime(document.forms['frmAddGreenSheet'].elements['consEndDate']);
	      cal4.year_scroll = true;
	   cal4.time_comp = false;
           var cal5 = new CalendarTime(document.forms['frmAddGreenSheet'].elements['nocDate']);
	      cal5.year_scroll = true;
	   cal5.time_comp = false;
           var cal6 = new CalendarTime(document.forms['frmAddGreenSheet'].elements['resaleDate']);
	      cal6.year_scroll = true;
	   cal6.time_comp = false;
                                            
                                                    </script>
                                                    <%--     </sx:div > --%>
                                                </div>
                                                
                                                
                                               <!-- <input type="button" align="center" onclick="printDiv('greenSheetAdd')" value="print a div!" /> -->
                                                  <s:submit cssClass="buttonBg" align="center" onClick="printdiv('greenSheetAdd');" value="Print"/>
                                                
                                                <!--//END TAB : -->             
                                                <%--  </sx:tabbedpanel> --%>
                                            </div>
                                            <!--//END TABBED PANNEL : -->
                          <script type="text/JavaScript">

var countries=new ddtabcontent("GreenSheetTab")
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
            <tr>
                <td>
                    
                    <div style="display: none; position: absolute; top:170px;left:320px;overflow:auto;" id="menu-popup">
                        <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" />
                    </div>
                    
                </td>
            </tr>
            <!--//END FOOTER : Record for Footer Background and Content-->
            
        </table>
        <!--//END MAIN TABLE : Table for template Structure-->
        <script type="text/javascript">
		$(window).load(function(){
		
		init(); 
		test();
		
                
		});
		</script>
    </body>
</html>
