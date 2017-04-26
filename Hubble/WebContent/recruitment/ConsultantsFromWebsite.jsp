<%--
/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :com.mss.mirage.recruitment
 *
 * Date    :  June 15, 2015, 8:26 PM
 *
 * Author  : Santosh kola<skola2@miraclesoft.com>
 *
 * File    : ConsultantsFromWebsite.jsp
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
--%>
<%@page contentType="text/html;charset=UTF-8" errorPage="../../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.freeware.gridtag.*" %> 
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="com.mss.mirage.util.*"%>
<%@ page import="java.sql.*,java.lang.*,java.util.*" %>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd" %>

<html>
<head>
    
    <title>Hubble Organization Portal :: Recruitment-Consultant Details</title>    
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/RecruitmentAjax.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/recruitment/ConsultantAddClientValidation.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/recruitment/ClientValidationRecruitment.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
    <script>
        onerror = handleErr;
            
        function handleErr(msg,url,l) {
                txt = "There is an error\n\n";
                txt+="error :"  +msg;
                txt+="Line :" +l;
                txt+="url : "+url;
                alert(txt);
                return(true);
        }
        function validSearch(field){
        if(field.value.length <= 3){
        alert("please enter characters more than 3!");
        return (false);
         }
         else return (true);
        }
    </script>
</head>

<body class="bodyGeneral" oncontextmenu="return false;">
<%!

Connection connection=null;
String strSQL=null;
int intSortOrd = 0;
boolean blnSortAsc = true;
%>
<%
try{
    String userId= (String) session.getAttribute("userId");
    if(request.getAttribute("strSQL")!= null) {
        strSQL=(String)request.getAttribute("strSQL");
        
    } else{
       // strSQL = "select Id,LName,FName,MName,Email2,CellPhoneNo,CreatedDate,ModifiedDate,Source from tblRecConsultant where CreatedBy='"+userId+"' ORDER BY CreatedDate DESC";
         strSQL = "select Id,CONCAT(FName,' ',MName,'.',LName) AS Name,Email2,CellPhoneNo,CreatedDate,ModifiedDate,Source from tblRecConsultant where CreatedBy='"+userId+"' AND SourceFlag!=0 ORDER BY CreatedDate DESC";
    }
 //out.print(strSQL);
}catch(Exception ex) {
    ex.printStackTrace();
}

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
    <div style="display:none"  style="padding-left:10px;padding-top:5px;">
        <s:form name="consultantdemographics" action="%{currentConsultant.actionName}" theme="simple" onsubmit="return checkConsultantAddForm();"> 
            <!--//START TABBED PANNEL : -->
            
            <ul id="ConsultantAddTabs" class="shadetabs" >
                <li ><a href="#" class="selected" rel="DemographicsTab"  > Demographics </a></li>
                <li ><a href="#" rel="otherDetailsTab">OtherDeatils</a></li>
                <li><a href="#" rel="addressTab">Address</a> </li>
                <li><a href="#" rel="commentsTab">Comments</a> </li>
            </ul>
            <%-- <sx:tabbedpanel id="consultantPannel" cssStyle="width: 840px; height: 350px;padding-left:10px;padding-top:5px;" doLayout="true" useSelectedTabCookie="true"> --%>
                
            <div  style="border:1px solid gray; width:820px;height: 350px; overflow:auto; margin-bottom: 1em;">
                
                <div id="DemographicsTab" class="tabcontent"  >
                    <!--//START TAB : -->
                    <%--  <sx:div id="DemographicsTab"  label="Demographics" cssStyle="overflow: auto;" > --%>
                    
                    <s:hidden name="password" value="Attachment"/>   
                    <s:hidden name="loginId" value="Attachment"/>
                    <s:hidden name="homAddressId" value="%{currentConsultant.homAddressId}"/>   
                    <s:hidden name="offAddressId"  value="%{currentConsultant.offAddressId}"/>
                    <s:hidden name="curAddressId"  value="%{currentConsultant.curAddressId}"/>   
                    <s:hidden name="othAddressId"  value="%{currentConsultant.othAddressId}"/>
                    <s:hidden name="consultantId"   value="%{currentConsultant.consultantRecordId}"/>   
                    <s:hidden name="createdBy"/>
                    <s:hidden name="createdDate"/>
                    <s:hidden name="modifiedBy"/>
                    <s:hidden name="modifiedDate"/>
                    <table cellpadding="7" cellspacing="0" border="0" width="100%"> 
                        <tr align="left">
                            <td class="headerText" colspan="6">
                                Consultant Demographics:
                            </td>
                        </tr>  
                        <tr> 
                            <td class="fieldLabel" >First Name :</td>
                            <td colspan="1"><s:textfield id="fiName" name="fiName" value="%{currentConsultant.fiName}" cssClass="inputTextBlue" onchange="fiNameValidate(document.consultantdemographics.fiName.value);"/> </td>
                            <td class="fieldLabel">Last Name :</td>
                            <td colspan="1"><s:textfield id="laName" name="laName" value="%{currentConsultant.laName}" cssClass="inputTextBlue" onchange="laNameValidate(document.consultantdemographics.laName.value);"/> </td>
                            <td class="fieldLabel">Middile Name :</td>
                            <td><s:textfield name="miName" value="%{currentConsultant.miName}" cssClass="inputTextBlue" onchange="miNameValidate(document.consultantdemographics.miName.value);"/> </td>
                        </tr>
                        
                        <tr>
                            <td class="fieldLabel"></td>
                            <td align="left" class="error" id="fiNameError">Required:FirstName</td>
                            <td class="fieldLabel"></td>
                            <td class="fieldLabel"></td>
                            <td align="left" class="error" id="laNameError">Required:LastName</td>
                        </tr><!--Firstname and LastName error display -->
                    
                        <tr>
                            
                            <td class="fieldLabel">Alias Name :</td>
                            <td colspan="1"><s:textfield name="aliasName" value="%{currentConsultant.aliasName}" cssClass="inputTextBlue" size="15" onchange="aliasNameValidate(document.consultantdemographics.aliasName.value);"/></td>
                            <td class="fieldLabel">SSN :</td>
                            <td><s:textfield name="ssn" value="%{currentConsultant.ssn}" cssClass="inputTextBlue" size="15" onchange="ssnValidate(document.consultantdemographics.ssn.value);"/></td>
                            <td class="fieldLabel">Gender :</td>
                            <td><s:select label="Select Gender" 
                                              id="gender"
                                              name="gender" 
                                              headerKey="1"
                                              headerValue="-- Please Select --"
                                          list="genderList" value="%{currentConsultant.gender}" cssClass="inputSelect"/></td>
                        </tr>
                        
                        <tr>
                            <td class="fieldLabel"></td>
                            <td class="fieldLabel"></td>
                            <td class="fieldLabel"></td>
                            <td class="fieldLabel"></td>
                            <td class="fieldLabel"></td>
                            <td align="left" class="error" id="genderError">Required:Gender</td>
                        </tr><!--Gender error display -->
                    
                        <tr>
                            <td class="fieldLabel">Marital Status :</td>
                            <td> <s:select label="Select Marital Status" 
                                               name="maritalStatus" 
                                               headerKey="1"
                                               headerValue="-- Please Select --"
                                           list="maritalStatusList" value="%{currentConsultant.maritalStatus}" cssClass="inputSelect"/></td>
                            <td class="fieldLabel">Consultant Type :</td>
                            <td> <s:select label="Select Consultant Type" 
                                               name="consultantType" 
                                               headerKey="1"
                                               headerValue="-- Please Select --"
                                           list="consultantTypesList" value="%{currentConsultant.consultantType}" cssClass="inputSelect"/></td>   
                            <td class="fieldLabel">Title TypeId :</td>
                            <td><s:select label="Select Title Type" 
                                              name="titleTypeId" 
                                              headerKey="1"
                                              headerValue="-- Please Select --"
                                          list="titleTypeIdList" value="%{currentConsultant.titleTypeId}" cssClass="inputSelect"/></td>
                        </tr>
                        
                        <tr>
                            <td class="fieldLabel">Office Email :</td>
                            <td><s:textfield id="email1" name="email1"  value="%{currentConsultant.email1}" cssClass="inputTextBlue" size="15" onchange="email1Validate(document.consultantdemographics.email1.value);" onblur="return checkEmail1(consultantdemographics.email1.value);"/></td>
                            <td class="fieldLabel">Personal Email :</td>
                            <td><s:textfield id="email2" name="email2" value="%{currentConsultant.email2}" cssClass="inputTextBlue" size="15" onchange="email2Validate(document.consultantdemographics.email2.value);" onblur="return checkEmail2(consultantdemographics.email2.value);"/></td>
                            <td class="fieldLabel">Other Email :</td>
                            <td><s:textfield id="email3" name="email3" value="%{currentConsultant.email3}" cssClass="inputTextBlue" size="15" onchange="email3Validate(document.consultantdemographics.email3.value);" onblur="return checkEmail3(consultantdemographics.email3.value);"/></td>
                            
                        </tr>
                        
                        <tr>
                            <td class="fieldLabel"></td>
                            <td align="left" class="error" id="emailError">Required:Atleast One Email</td>
                        </tr><!--Firstname and LastName error display -->

                    
                        <tr>
                            <td class="fieldLabel">Country :</td>
                            <td><s:select label="Select Country" 
                                              name="country" 
                                              headerKey="1"
                                              headerValue="-- Please Select --"
                                          list="countryList"  value="%{currentConsultant.country}" cssClass="inputSelect"/></td>                                                    
                            <td class="fieldLabel">Work Phone :</td>
                            <td><s:textfield id="workPhoneNo" name="workPhoneNo" value="%{currentConsultant.workPhoneNo}" cssClass="inputTextBlue" size="15" onchange="return workPhoneNoFormat(document.consultantdemographics.workPhoneNo.value);"/></td> 
                            <td class="fieldLabel">Home Phone :</td>
                            <td><s:textfield id="homePhoneNo" name="homePhoneNo" value="%{currentConsultant.homePhoneNo}" cssClass="inputTextBlue" size="15" onchange="return homePhoneNoFormat(document.consultantdemographics.homePhoneNo.value);"/></td>
                            
                        </tr>
                        
                        <tr>
                            <td class="fieldLabel"></td>
                            <td class="fieldLabel"></td>
                            <td class="fieldLabel"></td>
                            <td align="left" class="error" id="phoneNoError">Required:Atleast One Phone Number</td>
                        </tr><!--Firstname and LastName error display -->
                    
                    
                        <tr>
                            <td class="fieldLabel">Cell Phone :</td>
                            <td><s:textfield id="cellPhoneNo" name="cellPhoneNo" value="%{currentConsultant.cellPhoneNo}" cssClass="inputTextBlue" onchange="cellPhoneNoValidate(document.consultantdemographics.cellPhoneNo.value);"  onblur="return validatenumber(this);" /></td>  
                            <td class="fieldLabel">Hotel Phone :</td>
                            <td><s:textfield id="hotelPhoneNo" name="hotelPhoneNo" value="%{currentConsultant.hotelPhoneNo}" cssClass="inputTextBlue" size="15" onchange="hotelPhoneNoValidate(document.consultantdemographics.hotelPhoneNo.value);"  onblur="return validatenumber(this)"/></td>
                            <td class="fieldLabel">India Phone :</td>
                            <td><s:textfield id="indiaPhoneNo" name="indiaPhoneNo" value="%{currentConsultant.indiaPhoneNo}" cssClass="inputTextBlue" size="15" onchange="indiaPhoneNoValidate(document.consultantdemographics.indiaPhoneNo.value);"  onblur="return validatenumber(this)"/></td>
                            
                        </tr>
                        
                        
                        <tr>
                            
                            <td class="fieldLabel">Fax :</td>
                            <td><s:textfield id="faxPhoneNo" name="faxPhoneNo" value="%{currentConsultant.faxPhoneNo}" cssClass="inputTextBlue" size="15" onchange="faxPhoneNoValidate(document.consultantdemographics.faxPhoneNo.value);"  /></td>
                            <td class="fieldLabel">AlterPhone :</td>
                            <td><s:textfield id="alterPhoneNo" name="alterPhoneNo" value="%{currentConsultant.alterPhoneNo}" cssClass="inputTextBlue" size="15"  onchange="alterPhoneNoValidate(document.consultantdemographics.alterPhoneNo.value);"  onblur="return validatenumber(this)"/></td>
                            <td class="fieldLabel">Work Authoriztion :</td>
                            <td><s:select label="Select Authoriztion" 
                                              name="workAuthorization"
                                              headerKey="1"
                                              headerValue="-- Please Select --"
                                          list="{'US Citizen','Green Card','EAD','H1','TN Permit Visa','Need H1B','Student Visa'}"  cssClass="inputSelect"/></td>
                            <td><s:hidden name="deletedFlag" value="%{currentConsultant.deletedFlag}" cssClass="inputTextBlue" /></td>
                            
                        </tr>
                        
                        <tr>
                            <td class="fieldLabel">DOB :</td>
                            <td><s:textfield name="dob"  value="%{currentConsultant.viewDob}" cssClass="inputTextBlueSmall"/><a href="javascript:cal1.popup();">
                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                 width="20" height="20" border="0"></a></td>
                            <td class="fieldLabel">Official DOB :</td>
                            <td><s:textfield name="officialDob" value="%{currentConsultant.viewOfficialDob}" cssClass="inputTextBlueSmall" /><a href="javascript:cal2.popup();">
                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                 width="20" height="20" border="0"></a></td>
                            <td class="fieldLabel">Hire Date :</td>
                            <td><s:textfield name="hireDate" value="%{currentConsultant.viewHireDate}" cssClass="inputTextBlueSmall" /><a href="javascript:cal3.popup();">
                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                 width="20" height="20" border="0"></a></td>
                        </tr>
                        
                    </table>
                </div>
                <%--  </sx:div > --%>
                <!--Start OtherDetails TAB-->
                <%-- <sx:div  id="otherDetailsTab"  label="OtherDetails"  > --%>
                <div id="otherDetailsTab" class="tabcontent"  >
                    <table cellpadding="8"  width="100%" border="0" align="left" cellspacing="0" bordercolor="#efefef" >   
                        <tr>
                            <td class="headerText" align="left" colspan="4">Consultant OtherDetails:</td>
                        </tr>
                        
                        <tr>
                            <td class="fieldLabel" colspan="1" >First Contact Date :</td>
                            <td ><s:textfield  name="firstContactDate" value="%{currentConsultant.viewFirstContactDate}" cssClass="inputTextBlueSmall"/><a href="javascript:cal4.popup();">
                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                 width="20" height="20" border="0"></a></td>
                            <td class="fieldLabel" colspan="1">Anniversary Date :</td>
                            <td><s:textfield  name="anniversaryDate" value="%{currentConsultant.viewAnniversaryDate}" cssClass="inputTextBlueSmall" /><a href="javascript:cal5.popup();">
                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                 width="20" height="20" border="0"></a></td>
                        </tr>
                        
                        <tr>
                            <td class="fieldLabel" colspan="1" >Last Contact Date :</td>
                            <td><s:textfield name="lastContactDate" value="%{currentConsultant.viewLastContactDate}" cssClass="inputTextBlueSmall"/><a href="javascript:cal6.popup();">
                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                 width="20" height="20" border="0"></a></td>
                            <td class="fieldLabel" colspan="1">Last Contact By :</td>
                            
                            <td><s:select label="Select LastContactBy Id" 
                                              name="lastContactBy" 
                                              headerKey="1"
                                              headerValue="-- Please Select --"
                                              list="contactByMap" 
                                              value="%{currentConsultant.lastContactBy}"
                                          cssClass="inputSelect"/></td>
                            
                        </tr>
                        
                        <tr>
                            <td class="fieldLabel" colspan="1">Reffered By :</td>
                            <td><s:textfield name="refferedBy" value="%{currentConsultant.refferedBy}" cssClass="inputTextBlue" size="15" onchange="refferedByValidate(document.consultantdemographics.refferedBy.value);" /></td>
                            <td class="fieldLabel" colspan="1">Refferal Bonus :</td>
                            <td><s:textfield name="refferalBonus" value="%{currentConsultant.refferalBonus}" cssClass="inputTextBlue" size="15" onchange="refferalBonusValidate(document.consultantdemographics.refferalBonus.value);"/></td>
                        </tr>
                        
                        <tr>
                            <td class="fieldLabel" colspan="1">Practice Id :</td>
                            <td><s:select label="Select Practice Id" 
                                              name="practiceId" 
                                              headerKey="1"
                                              headerValue="-- Please Select --"
                                              list="practiceList" 
                                              value="%{currentConsultant.practiceId}"
                                          cssClass="inputSelect"/></td> 
                            <td class="fieldLabel" colspan="1">RecContact Id :</td>
                            
                            <td><s:select label="Select RecContactId" 
                                              name="recContactId" 
                                              headerKey="1"
                                              headerValue="-- Please Select --"
                                              list="recContactIdMap" 
                                              value="%{currentConsultant.recContactId}"
                                          cssClass="inputSelect"/></td>
                        </tr>
                        <tr>
                            <td align="left" class="fieldLabel" colspan="1" >Requested Rate Per Hour :</td>
                            <td ><s:textfield name="ratePerHour" value="%{currentConsultant.ratePerHour}" cssClass="inputTextBlue" size="15" onchange="ratePerHourValidate(document.consultantdemographics.ratePerHour.value);"/></td>
                            <td class="fieldLabel" colspan="1">Rate Negotiated :</td>
                            <td ><s:textfield name="rateNegotiated" value="%{currentConsultant.rateNegotiated}" cssClass="inputTextBlue" size="5" onchange="rateNegotiatedValidate(document.consultantdemographics.rateNegotiated.value);"/></td>                        
                            
                        </tr>
                        
                        
                        <tr>
                            <td class="fieldLabel" colspan="1">Current Employer :</td>
                            <td colspan="1"><s:textfield name="currentEmployer" value="%{currentConsultant.currentEmployer}" cssClass="inputTextBlueAddressSmall " onchange="currentEmployerValidate(document.consultantdemographics.currentEmployer.value);" /></td>                                              
                            <td class="fieldLabel" colspan="1">Current Status :</td>
                            <td><s:select label="Select Current Status" 
                                              name="curStatus" 
                                              headerKey="1"
                                              headerValue="-- Please Select --"
                                          list="currStatusList" value="%{currentConsultant.curStatus}" cssClass="inputSelect"/></td>
                        </tr>
                        
                        <tr>
                            
                            <td class="fieldLabel"  colspan="1">Prefered Question :</td>
                            <td colspan="1" >
                                <s:select cssClass="inputSelectExtraLarge" label="Select Country" 
                                          name="preferedQuestion" 
                                          headerKey="1"
                                          headerValue="-- Please Select --"
                                          value="%{currentConsultant.preferedQuestion}"
                                          list="prefferedQuestionsList"/> 
                            </td> 
                            
                            <td class="fieldLabel" colspan="1">Prefered Answer :</td>
                            <td colspan="1" ><s:textfield name="preferedAnswer" value="%{currentConsultant.preferedAnswer}" cssClass="inputTextBlue"  onchange="preferedAnswerValidate(document.consultantdemographics.preferedAnswer.value);"/></td>
                            
                        </tr>
                    </table>   
                </div>
                <%--  </sx:div>  --%>           
                <!--End OtherDetails TAB-->
     
                <!--ADDRESS TAB START-->
        
                <%-- <sx:div  id="addressTab"  label="Address"  cssStyle="overflow: auto;"> --%>
               
                <div id="addressTab" class="tabcontent"  >
                    
                    <table cellpadding="1"  border="0" align="center" cellspacing="0"  width="100%" >   
                        <tr>
                            <td class="headerText" colspan="2"  >
                                <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0"> 
                            </td>
                        </tr>
                        
                        <tr>
                            <td>
                                <table width="45%" cellpadding="1" cellspacing="0" border="0">
                                    
                                    <tr>
                                        <td class="headerText" colspan="4" align="center">Home Address:</td>
                                    </tr>  
                                    
                                    <tr>
                                        <td class="fieldLabel">Add.L1:</td>
                                        <td colspan="3">
                                            <s:textfield name="AddressLine1" value="%{currentConsultant.AddressLine1}" cssClass="inputTextBlueAddress" onchange="AddressLine1Validate(document.consultantdemographics.AddressLine1.value);"/>
                                        </td>
                                    </tr>
                                    
                                    <tr>
                                        <td class="fieldLabel">Add.L2:</td>
                                        <td colspan="3">
                                            <s:textfield name="AddressLine2" value="%{currentConsultant.AddressLine2}" cssClass="inputTextBlueAddress" onchange="AddressLine2Validate(document.consultantdemographics.AddressLine2.value);"/>
                                        </td>
                                    </tr>
                                    
                                    <tr>
                                        <td class="fieldLabel">City :</td>
                                        <td><s:textfield name="City" value="%{currentConsultant.City}" cssClass="inputTextBlue" onchange="CityValidate(document.consultantdemographics.City.value);"/> </td>
                                        <td class="fieldLabel">MailStop :</td>
                                        <td style="padding-left:4px;"><s:textfield name="MailStop" value="%{currentConsultant.MailStop}" cssClass="inputTextBlue" onchange="MailStopValidate(document.consultantdemographics.MailStop.value);"/> </td>
                                    </tr>
                                    <tr>             
                                        <td class="fieldLabel">State :</td>
                                        <td>
                                            <select  name="State" id ="state"  class="inputTextBlue">
                                                <option value="<s:property value="%{currentConsultant.State}"/>"><s:property value="%{currentConsultant.State}"/> </option> 
                                        </select></td>
                                        
                                        <td class="fieldLabel">Country :</td>
                                        <td style="padding-left:4px;">  <s:select   name="HomeCountry" 
                                                                                        headerKey="1"
                                                                                        headerValue="-- Please Select --"
                                                                                    list="countryList" onchange="getStateData(this);"  value="%{currentConsultant.HomeCountry}" cssClass="inputTextBlue"/> </td>
                                        
                                    </tr>
                                    <tr>             
                                        <td class="fieldLabel">Zip :</td>
                                        <td><s:textfield name="Zip" value="%{currentConsultant.Zip}" cssClass="inputTextBlue" onchange="ZipValidate(document.consultantdemographics.Zip.value);"/></td>
                                    </tr>
                                </table> 
                                
                            </td>
                            <td>
                                <table width="45%" cellpadding="1" cellspacing="0" border="0">
                                    <tr>
                                        <td class="headerText" colspan="4" align="center">Project Address :</td>
                                    </tr>  
                                    
                                    <tr>
                                    <td class="fieldLabel">Add.L1 :</td>
                                    <td colspan="3">
                                        <s:textfield name="curAddressLine1" value="%{currentConsultant.curAddressLine1}" cssClass="inputTextBlueAddress" onchange="curAddressLine1Validate(document.consultantdemographics.curAddressLine1.value);"/>
                                    </td>
                                    <tr>
                                        <td class="fieldLabel">Add.L2 :</td>
                                        <td colspan="3">
                                            <s:textfield name="curAddressLine2" value="%{currentConsultant.curAddressLine2}" cssClass="inputTextBlueAddress" onchange="curAddressLine2Validate(document.consultantdemographics.curAddressLine2.value);"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="fieldLabel">City :</td>
                                        <td><s:textfield name="curCity" value="%{currentConsultant.curCity}" cssClass="inputTextBlue" onchange="curCityValidate(document.consultantdemographics.curCity.value);"/> </td>
                                        <td class="fieldLabel">MailStop :</td>
                                        <td style="padding-left:4px;"><s:textfield name="curMailStop" value="%{currentConsultant.curMailStop}" cssClass="inputTextBlue" onchange="curMailStopValidate(document.consultantdemographics.curMailStop.value);"/> </td>
                                    </tr>
                                    <tr>             
                                        <td class="fieldLabel">State :</td>
                                        <td>   <select name="curState" id="curState" class="inputTextBlue">
                                                <option value="<s:property value="%{currentConsultant.curState}"/>"><s:property value="%{currentConsultant.curState}"/> </option> 
                                        </select></td>
                                        <td class="fieldLabel">Country :</td>
                                        <td style="padding-left:4px;"><s:select   name="ProjectCountry" 
                                                                                      headerKey="1"
                                                                                      headerValue="-- Please Select --"
                                                                                  list="countryList" onchange="getCurrentProjStates(this);" value="%{currentConsultant.ProjectCountry}" cssClass="inputSelect"/></td>                                                    
                                    </tr>
                                    <tr>             
                                        <td class="fieldLabel">Zip :</td>
                                        <td><s:textfield name="curZip" value="%{currentConsultant.curZip}" cssClass="inputTextBlue" onchange="curZipValidate(document.consultantdemographics.curZip.value);"/></td>
                                    </tr>
                                </table> 
                            </td>
                        </tr>
                        
                        <tr>
                            <td >
                                <table width="45%" cellpadding="1" cellspacing="0" border="0">
                                    <tr>
                                        <td class="headerText" colspan="4" align="center">OffShore Address :</td>
                                    </tr>  
                                    <tr>
                                        <td class="fieldLabel">Add.L1 :</td>
                                        <td colspan="3">
                                            <s:textfield name="offAddressLine1" value="%{currentConsultant.offAddressLine1}" cssClass="inputTextBlueAddress" onchange="offAddressLine1Validate(document.consultantdemographics.offAddressLine1.value);"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="fieldLabel">Add.L2 :</td>
                                        <td colspan="3">
                                            <s:textfield name="offAddressLine2" value="%{currentConsultant.offAddressLine2}" cssClass="inputTextBlueAddress" onchange="offAddressLine2Validate(document.consultantdemographics.offAddressLine2.value);"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="fieldLabel">City :</td>
                                        <td><s:textfield name="offCity" value="%{currentConsultant.offCity}" cssClass="inputTextBlue" onchange="offCityValidate(document.consultantdemographics.offCity.value);"/> </td>
                                        <td class="fieldLabel">MailStop :</td>
                                        <td style="padding-left:4px;"><s:textfield name="offMailStop" value="%{currentConsultant.offMailStop}" cssClass="inputTextBlue" onchange="offMailStopValidate(document.consultantdemographics.offMailStop.value);"/> </td>
                                    </tr>
                                    <tr>             
                                        <td class="fieldLabel">State :</td>
                                        <td>
                                            <select  id="offState" name="offState"   class="inputTextBlue">
                                                <option value="<s:property value="%{currentConsultant.offState}"/>"><s:property value="%{currentConsultant.offState}"/> </option> 
                                            </select>
                                        </td>
                                        <td class="fieldLabel">Country :</td>
                                        <td style="padding-left:4px;"><s:select   name="OffShoreCountry" 
                                                                                      headerKey="1"
                                                                                      headerValue="-- Please Select --"
                                                                                  list="countryList" onchange="getOffShoreStates(this);" value="%{currentConsultant.OffShoreCountry}" cssClass="inputSelect"/></td>                                                    
                                    </tr>
                                    <tr>             
                                        <td class="fieldLabel">Zip :</td>
                                        <td><s:textfield name="offZip" value="%{currentConsultant.offZip}" cssClass="inputTextBlue" onchange="offZipValidate(document.consultantdemographics.offZip.value);"/></td>
                                    </tr>    
                                </table> 
                            </td>
                            <td >
                                <table width="45%" cellpadding="1" cellspacing="0" border="0">
                                    <tr>
                                        <td class="headerText" colspan="4" align="center">Other Address :</td>
                                    </tr>  
                                    
                                    <tr>
                                        <td class="fieldLabel"> Add.L1 :</td>
                                        <td colspan="3">
                                            <s:textfield name="othAddressLine1" value="%{currentConsultant.othAddressLine1}" cssClass="inputTextBlueAddress" onchange="othAddressLine1Validate(document.consultantdemographics.othAddressLine1.value);"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="fieldLabel"> Add.L2 :</td>
                                        <td colspan="3">
                                            <s:textfield name="othAddressLine2"  value="%{currentConsultant.othAddressLine2}" cssClass="inputTextBlueAddress" onchange="othAddressLine2Validate(document.consultantdemographics.othAddressLine2.value);"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="fieldLabel">City :</td>
                                        <td><s:textfield name="othCity" value="%{currentConsultant.othCity}" cssClass="inputTextBlue" onchange="othCityValidate(document.consultantdemographics.othCity.value);"/> </td>
                                        <td class="fieldLabel">MailStop :</td>
                                        <td style="padding-left:4px;"><s:textfield name="othMailStop" value="%{currentConsultant.othMailStop}" cssClass="inputTextBlue" onchange="othMailStopValidate(document.consultantdemographics.othMailStop.value);" /> </td>
                                    </tr>
                                    <tr>             
                                        <td class="fieldLabel">State :</td>
                                        <td><select  id="othState" name="othState"  class="inputTextBlue">
                                                <option value="<s:property value="%{currentConsultant.othState}"/>"><s:property value="%{currentConsultant.othState}"/> </option>            
                                            </select>
                                        </td>
                                        <td class="fieldLabel">Country :</td>
                                        
                                        <td style="padding-left:4px;"><s:select   name="OtherCountry" 
                                                                                      headerKey="1"
                                                                                      headerValue="-- Please Select --"
                                                                                  list="countryList" onchange="getOthAddStates(this);" value="%{currentConsultant.OtherCountry}" cssClass="inputSelect"/></td>                                                    
                                    </tr>
                                    <tr>             
                                        <td class="fieldLabel">Zip :</td>
                                        <td><s:textfield name="othZip" value="%{currentConsultant.othZip}" cssClass="inputTextBlue" onchange="othZipValidate(document.consultantdemographics.othZip.value);"/></td>
                                    </tr>  
                                </table> 
                            </td>
                        </tr> 
                    </table>
                </div>
                <%-- </sx:div> --%>
                <!---ADDRESS TAB END-->
                <!--Start Comments TAB-->
                <%-- <sx:div id="commentsTab"  label="Comments"  cssStyle="overflow: auto;"> --%>
               
                <div id="commentsTab" class="tabcontent"  >
                    
                    <table cellpadding="10" width="100%" border="0" align="center" cellspacing="0" bordercolor="#efefef" >  
                        
                        <tr align="right">
                            <td class="headerText" colspan="2">
                                <s:property value="#request.resultMessage"/>
                                <s:submit cssClass="buttonBg" value="save" />
                            </td>
                        </tr>
                        
                        <tr>
                            <td colspan="6">&nbsp;</td>
                        </tr>
                        <tr>
                            <td class="fieldLabel"></td>
                            <td align="left" class="error" id="commentsError">Required Fields are not entered in DemoGraphics Tab</td>
                        </tr>
                        <tr>
                            <td class="fieldLabel">Comments:</td>
                            <td colspan="1" ><s:textarea name="comments"  value="%{currentConsultant.comments}" cssClass="inputTextarea" cols="100" rows="15" onchange="commentsValidate(document.consultantdemographics.comments.value);"/></td>
                        </tr>
                    </table>
                    
                    <%-- </sx:div> --%>
                    <!--//End Comments TAB--->
           <!--//END TAB : -->

                </div>
            </div>
            <%-- </sx:tabbedpanel> --%>
            <!--//END TABBED PANNEL : -->
        </s:form>
        
        <%-- </div> --%>
        <script type="text/javascript">

                                                    var countries=new ddtabcontent("ConsultantAddTabs")
                                                    countries.setpersist(false)
                                                    countries.setselectedClassTarget("link") //"link" or "linkparent"
                                                    countries.init()

        </script>
        <script type="text/JavaScript">
                                                 var cal1 = new CalendarTime(document.forms['consultantdemographics'].elements['dob']);
				                 cal1.year_scroll = true;
				                 cal1.time_comp = false;
                                                 
                                                   var cal2 = new CalendarTime(document.forms['consultantdemographics'].elements['officialDob']);
				                 cal2.year_scroll = true;
				                 cal2.time_comp = false;
                                                 
                                                   var cal3 = new CalendarTime(document.forms['consultantdemographics'].elements['hireDate']);
				                 cal3.year_scroll = true;
				                 cal3.time_comp = false;
                                                 
                                                   var cal4 = new CalendarTime(document.forms['consultantdemographics'].elements['firstContactDate']);
				                 cal4.year_scroll = true;
				                 cal4.time_comp = false;
                                                 
                                                   var cal5 = new CalendarTime(document.forms['consultantdemographics'].elements['anniversaryDate']);
				                 cal5.year_scroll = true;
				                 cal5.time_comp = false;
                                                 
                                                   var cal6 = new CalendarTime(document.forms['consultantdemographics'].elements['lastContactDate']);
				                 cal6.year_scroll = true;
				                 cal6.time_comp = false;
                                                 
                                            
        </script>
        <!--//START TABBED PANNEL : --> 
    </div>
    
    
    <ul id="consultantListTabs" class="shadetabs" >
        <li ><a href="#" class="selected" rel="consultantListTab"  > Consultants List </a></li>
      <%--  <li ><a href="#" rel="consultantSearchTab">Consultant Search</a></li> --%>
       <%-- <li><a href="#" rel="consultantSkillSearchTab">Skill Search</a> </li> --%>
    </ul>
    
    <%-- <sx:tabbedpanel id="consultantListPannel" cssStyle="width: 840px; height: 360px;padding-left:10px;padding-top:5px;" doLayout="true"> --%>
    <div  style="border:1px solid gray; width:830px;height: 500px; overflow:auto; margin-bottom: 1em;">
        <!--//START TAB : -->
        <%-- <sx:div id="consultantListTab"  label="Consultants List"  > --%>
        <div id="consultantListTab" class="tabcontent"  >
            <form name="frmDBGrid" action="">
                <table cellpadding="0" width="100%" border="0" align="center" cellspacing="0" bordercolor="#efefef" >  
                    <tr align="right">
                        <td class="headerText">
                            <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                        </td>
                    </tr>
                    <tr>
                        <td> 
                            
                            <table align="center" cellpadding="0" cellspacing="0"  width="100%">
                                <tr>
                                    <td align="center">
                                        
                                        <%
                                        
                                        try{
                                            
                                            int intCurr = 1;
                                            int intSortOrd = 0;
                                            String strTmp = null;
                                            boolean blnSortAsc = true;
                                            String strSortCol = null;
                                            String strSortOrd = "DSC";
                                            
                                            strTmp = request.getParameter("txtCurr");
                                            try {
                                                if (strTmp != null)
                                                    intCurr = Integer.parseInt(strTmp);
                                            } catch (NumberFormatException NFEx) {
                                                
                                            } //catch
                                            
                                            
                                            connection = ConnectionProvider.getInstance().getConnection();
                                            strSortCol = request.getParameter("Colname");
                                            strSortOrd = request.getParameter("txtSortAsc");
                                            if (strSortCol == null) strSortCol = "FirstName";
                                            if (strSortOrd == null) strSortOrd = "DSC";
                                            blnSortAsc = (strSortOrd.equals("ASC"));
                                        
                                        
                                        %>
                                        <s:form method="post" 
                                                id="activityGrid" 
                                                name="activityGrid" 
                                                theme="simple" 
                                                action="">           
                                            
                                            <grd:dbgrid id="tblDBGrid" 
                                                        name="tblDBGrid" 
                                                        width="100" 
                                                        pageSize="15" 
                                                        currentPage="<%=intCurr%>"
                                                        border="0" 
                                                        cellSpacing="1" 
                                                        cellPadding="2" 
                                                        dataMember="<%=strSQL%>"
                                                        dataSource="<%=connection%>" 
                                                        cssClass="gridTable" >
                                                
                                                <grd:gridpager imgFirst="../includes/images/DBGrid/First.gif" 
                                                               imgPrevious="../includes/images/DBGrid/Previous.gif" 
                                                               imgNext="../includes/images/DBGrid/Next.gif" 
                                                               imgLast="../includes/images/DBGrid/Last.gif"/>
                                                <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" />
                                                
                                                <%--<grd:imagecolumn  headerText="Edit"
                                                                  width="5" 
                                                                  HAlign="center" 
                                                                  imageSrc="../includes/images/DBGrid/newEdit_17x18.png" 
                                                                  linkUrl="getConsultantEdit.action?consultantId={Id}" 
                                                                  
                                                                  imageBorder="0"
                                                                  imageWidth="16"
                                                                  imageHeight="16" 
                                                                  alterText="Click to edit" /> --%>          
                                                
                                                
                                                <grd:imagecolumn  headerText="View" 
                                                                  width="5"  
                                                                  HAlign="center" 
                                                                  imageSrc="../includes/images/DBGrid/Edit.gif" 
                                                                  linkUrl="consultant/getConsultant.action?empId={Id}&requirement=-1&siteConsultants=-1&consultId=0&requirementId=0&requirementAdminFlag=" 
                                                                  imageBorder="0"
                                                                  imageWidth="16" 
                                                                  imageHeight="16" 
                                                                  alterText="Click to View" />      
                                                
                                                
                                               <%-- <grd:textcolumn dataField="FName" headerText="First Name"  width="14" HAlign="center" />
                                                <grd:textcolumn dataField="LName" headerText="Last Name" width="14" HAlign="center" /> 
                                                <grd:textcolumn dataField="MName" headerText="Middle Name" width="14" HAlign="center" />  --%>
                                               <grd:textcolumn dataField="Name" headerText="Consultant Name"  width="14" HAlign="left" />
                                              
                                                
                                                <grd:textcolumn dataField="Email2" headerText="E-Mail" width="18" HAlign="left" /> 
                                                <grd:textcolumn dataField="CellPhoneNo" headerText="Cell Number" width="12" HAlign="left" /> 
                                                <grd:datecolumn dataField="CreatedDate" headerText="CreatedDate" width="12" HAlign="left" dataFormat="yyyy-MM-dd"/>
                                                <grd:datecolumn dataField="ModifiedDate" headerText="ModifiedDate" width="12" HAlign="left" dataFormat="yyyy-MM-dd"/>
                                                 <grd:textcolumn dataField="Source" headerText="Source" width="18" HAlign="left" /> 
                                                
                                                <%--
                                                <grd:imagecolumn  headerText="Delete"
                                                                  width="4"  HAlign="center" 
                                                                  imageSrc="../includes/images/DBGrid/Delete.png" 
                                                                  linkUrl="deleteConsultant.action?consultantId={Id}" 
                                                                  imageBorder="0" 
                                                                  imageWidth="51" 
                                                                  imageHeight="20" 
                                                                  alterText="Click to edit" />
                                                --%>                  
                                            </grd:dbgrid>
                                            <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                            <input TYPE="hidden" NAME="txtSortCol"	VALUE="<%=strSortCol%>">
                                            <input TYPE="hidden" NAME="txtSortAsc"	VALUE="<%=strSortOrd%>">
                                            <input id="txtDBId" TYPE="hidden" NAME="txtDBId" VALUE="">
                                            <input id="txtDBId1" TYPE="hidden" NAME="txtDBId1" VALUE="">
                                            <input id="txtDBId2" TYPE="hidden" NAME="txtDBId2" VALUE="">
                                            <input id="txtDBId3" TYPE="hidden" NAME="txtDBId3" VALUE="">
                                            <input id="txtDBId4" TYPE="hidden" NAME="txtDBId4" VALUE="">
                                            <input id="txtTRId"TYPE="hidden" NAME="txtTRId"	VALUE="">&nbsp;
                                            
                                        </s:form>    
                                    </td>
                                </tr>
                            </table>
                            
                            <%
                            
                                        } catch(Exception ex) {
                                            
                                        } finally {
                                            if(connection!=null)
                                                connection.close();
                                            connection=null;
                                        } // finally
                            %>
                        </td>      
                    </tr>
                </table>
            </form>
        </div>
        <%-- </sx:div> --%>
        <!--//END TAB : -->
        <!--START SEARCH TAB:-->
        <%-- <sx:div id="consultantSearchTab"  label="Consultant Search"  > --%>
        <div id="consultantSearchTab" class="tabcontent"  >
            <table cellpadding="0" width="100%" border="0" align="center" cellspacing="0" bordercolor="#efefef">  
                <tr>
                    <td colspan="5">
                        <table cellpadding="0" cellspacing="0" width="100%" >
                            <tr>
                                <td class="headerText">
                                    <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                
                <tr>
                    <s:form name="search" action="" theme="simple" onsubmit="return formatPhone(document.search.searchPhone);">
                    <td class="fieldLabel">Consultant Name:</td>
                    <td><s:textfield  name="searchName" id="searchName" cssClass="inputTextBlue" onchange="searchNameValidate(document.search.searchName.value);" /> </td>
                    
                    <td class="fieldLabel">CellPhone  No:</td>
                    <td>  <s:textfield name="searchPhone" id="searchPhone" cssClass="inputTextBlue"  onchange="searchPhoneValidate(document.search.searchPhone.value);"  onblur="return validatenumber(this);"/> </td>
                    
                    <td> <INPUT type="image"  onclick='document.search.action="./consultantSearch.action"' src="<s:url value="/includes/images/go_21x21.gif"/>" >
                        
                    </td>
                    <td height="200px">
                        
                    </td>
                </tr>
                
                </s:form>
            </table>
        </div>
        <%-- </sx:div> --%>
        <!--END SEARCH TAB:-->
        <!--START SEARCH TAB:-->
        <%-- <sx:div id="consultantSkillSearchTab"  label="Skill Search"  > --%>
        <div id="consultantSkillSearchTab" class="tabcontent"  >
            <table cellpadding="0" width="100%" border="0" align="center" cellspacing="0" bordercolor="#efefef">  
                <tr>
                    <td colspan="4">
                        <table cellpadding="0" cellspacing="0" width="100%" >
                            <tr>
                                <td class="headerText">
                                    <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                
                <tr>
                    <s:form name="skillsearch" action="" theme="simple">
                    <td class="fieldLabel">Skills :</td>
                    <td width="50%"><s:textfield  name="skills" id="skills" cssClass="inputTextBlueExtraLarge" onchange="validSearch(this);"/> </td>
                    
                    <td> <INPUT type="image"  onclick='document.skillsearch.action="./skillSearch.action"' src="<s:url value="/includes/images/go_21x21.gif"/>" >
                        
                    </td>
                    <td height="120px">
                        
                    </td>
                </tr>
                <tr>
                    <td colspan="4" align="center" style="font-family: arial,verdana; font-size:11px; color:red;">Note : Please enter atleast 4 characters for search (Add * for Efficient Search)</td>
                </tr>
                </s:form>
            </table>
        </div>
        
    </div>
    <script type="text/javascript">

                                                    var countries=new ddtabcontent("consultantListTabs")
                                                    countries.setpersist(false)
                                                    countries.setselectedClassTarget("link") //"link" or "linkparent"
                                                    countries.init()

    </script>
    <%-- </sx:div> --%>
    <!--END SEARCH TAB:-->
    <%-- </sx:tabbedpanel> --%>
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
    <td align="center">&reg; 2007  Mirage - All Rights Reserved.</td>
</tr>
<!--//END FOOTER : Record for Footer Background and Content-->

</table> 
<!--//END MAIN TABLE : Table for template Structure-->

</body>
</html>
