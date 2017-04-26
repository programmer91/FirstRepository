<%--
/********************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :com.mss.mirage.recruitment
 *
 * Date    :  September 04, 2008, 10:17 PM
 *
 * Author  : Hari Krishna Kondala<hkondala@miraclesoft.com>
 *
 * File    : RequirementAdd.jsp
 *
 * Copyright 2008 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
--%>
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ page import="com.mss.mirage.util.*" %>
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
        <title>Hubble Organization Portal :: CRM-Requirement Details</title>
        <%-- <sx:head cache="true"/> --%>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/helpText.css"/>"> 
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script> 
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/RequirementUtil.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/StandardClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
         <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGAccDetails.js"/>"></script>
        <%-- for Resume Submission to Customer --%>
        <script src="http://code.jquery.com/jquery-1.10.2.js"></script>
        <script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
        <script>
            $(function() {
                $( document ).tooltip();
            });
        </script>
        <style>
            label {
                display: inline-block;
                width: 5em;
            }
        </style>
        <script type="text/javascript">
            function win_open2(url,recid,requirementid,consultantid,resumeattachmentid){
                url=url+"?resumeRecId="+recid+"&resumeRequirementId="+requirementid+"&resumeConsultantId="+consultantid+"&resumeAttachmentId="+resumeattachmentid;
                       
                newWindow=window.open(url,'resumeRecId','height=400,width=680');
            }               
        </script>
        <!--<script type="text/JavaScript">
                function checkDate() {
                    var startDate = document.getElementById('startDate').value;
                    var endDate = document.getElementById('endDate').value;
                    var mm = startDate.substring(0,2);
                    var dd = startDate.substring(3,5);
                    var yyyy = startDate.substring(6,10);
                    var mm1 = endDate.substring(0,2);
                    var dd1 = endDate.substring(3,5);
                    var yyyy1 = endDate.substring(6,10);
                    if(yyyy1 < yyyy) {
                        alert('Start Date is older than End Date');
                        document.getElementById('startDate').value = '';
                        document.getElementById('endDate').value = '';
                        return false;
                    }
                    else if((yyyy1 == yyyy) && (mm1 < mm)) {
                        alert('Start Date is older than End Date');
                        document.getElementById('startDate').value = '';
                        document.getElementById('endDate').value = '';
                        return false;
                    }
                    else if((yyyy1 == yyyy) && (mm1 == mm) && (dd1 < dd)) {
                        alert('Start Date is older than End Date');
                        document.getElementById('startDate').value = '';
                        document.getElementById('endDate').value = '';
                        return false;
                    }
                }
        </script>-->

        <SCRIPT language=Javascript>
            document.getElementById("status").value='open'; 
            function isNumberKey(evt)
            {
                var charCode = (evt.which) ? evt.which : event.keyCode
                if (charCode > 31 && (charCode < 48 || charCode > 57))
                {
                    alert("Please enter numeric value");
                    return false;
                } 

                return true;
            }
      
            function doForward(consultantId,recConsultantId,requirementId){
                var requirementAdminFlag = document.getElementById("requirementAdminFlag").value;
                var assignedTo2=document.getElementById("assignedTo2").value;
                var assignedTo3=document.getElementById("assignedTo3").value;
                window.location = "../../recruitment/consultant/reviews.action?consultantId="+consultantId+"&recConsultantId="+recConsultantId+"&requirementId="+requirementId+"&requirementAdminFlag="+requirementAdminFlag+"&assignedTo2="+assignedTo2+"&assignedTo3="+assignedTo3;
            }
            function getConsultantRequirement(consultantId,recConsultantId,requirementId){
                var requirementAdminFlag = document.getElementById("requirementAdminFlag").value;
                var accId = document.getElementById("accId").value;
                
                // window.location = "../../recruitment/consultant/reviews.action?consultantId="+consultantId+"&recConsultantId="+recConsultantId+"&requirementId="+requirementId+"&requirementAdminFlag="+requirementAdminFlag;
                window.location = "getConsultantRequirement.action?consultId="+recConsultantId+"&objectId="+requirementId+"&requirementAdminFlag="+requirementAdminFlag+"&accId="+accId;
          
            }

           function commaValidator(element) {
                var x = element;

                var f=x.value;

                var ch1=f.substring(f.length-2,f.length-1);

                var ch2=f.substring(f.length-1,f.length);

                if(((ch1==ch2) && (ch1==',')) || (f.charAt(0)==',')){

                    x.value = f.substring(0,f.length-1);
                }else{
                    x.value = f.substring(0,f.length);
                }

                 var r=x.value;
                if(ch2==','){
                   r=r.substring(0,r.length-1);
                    var n = r.lastIndexOf(",")+1;
                    var lastSkill=r.substring(n,r.length);
                    var finalval=r.substring(0,n);
                    var a=r.split(',');
                    var i=a.length;
                    if(lastSkill.length>35){
                        lastSkill=lastSkill.substring(0,34);
                        finalval=finalval+lastSkill;
                         alert('skill length should be less than 35 characters');
                    }
                    else{
                        finalval=finalval+lastSkill+",";
                    }
                   // finalval=finalval+lastSkill;
                    //alert(lastSkill);
                    x.value=finalval;
//                    for( n=0;n<i;n++){
//                        if(a[n].length>34){
//                            alert('skill length should be less than 35 characters');
//                            x.value = r.substring(0,r.length-1);
//                        }
//                    } 
                }
            }
      
        </SCRIPT>

    </head>

    <body class="bodyGeneral" oncontextmenu="return false;">

        <%!    /* Declarations */
            Connection connection;
            String queryString;
            String strTmp;
            String strSortCol;
            String strSortOrd;
            String userId;
            String submittedFrom;
            String action;
            int role;
            int intSortOrd = 0;
            int intCurr;
            boolean blnSortAsc = true;
            String currentAccountId;
        %>
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
                            <td width="850px" class="cellBorder" valign="top" style="padding: 5px 5px;">


                                <%-- <sx:tabbedpanel id="requirementPanel" cssStyle="width: 830px; height: 570px;padding: 5px 5px;" doLayout="true"> --%>
                                <ul id="accountTabs" class="shadetabs" >
                                    <li ><a href="#" class="selected" rel="requirementDiv"  >Requirement Details </a></li>
                                    <s:if test="%{currentRequirement.actionType == 'requirementEdit'}">
                                        <li><a href="#" rel="jobDetailsDiv">Job Details</a> 
                                        <li><a href="#" rel="timeLinesDiv">Time lines</a></li>
                                    </s:if>

                                </ul>
                                <s:if test="%{currentRequirement.actionType =='requirementAdd' && #session.roleName == 'Sales'}" >
                                    <div  style="border:1px solid gray; width:830px;height: 450px; overflow:auto; margin-bottom: 1em;">
                                    </s:if><s:else>
                                        <div  style="border:1px solid gray; width:830px;height: 350px; overflow:auto; margin-bottom: 1em;">
                                        </s:else>
                                        <div id="requirementDiv" class="tabcontent"  >
                                            <%

                                                if (session.getAttribute(ApplicationConstants.RESULT_MSG) != null) {
                                                    out.println(session.getAttribute(ApplicationConstants.RESULT_MSG).toString());
                                                    session.removeAttribute(ApplicationConstants.RESULT_MSG);
                                                }
                                            %>
                                            <%--    <sx:div id="requirementDiv" name="requirementDiv" label="Requirement Details" cssStyle="overflow:auto;"> --%>
                                            <s:form name="requirementForm" id="requirementForm" action="%{currentRequirement.actionType}" method="post" theme="simple" onsubmit="return requirementCheck();">
                                                <s:hidden name="objectId" id="objectId" value="%{currentRequirement.objectId}"/>
                                                <s:hidden name="accId" id="accId" value="%{accId}"/>
                                                <s:hidden name="divType" id="divType"  value="requirementDetails"/>
                                                <s:hidden name="roleName" value="%{#session.roleName}"/>
                                                <s:hidden name="requirementAdminFlag" id="requirementAdminFlag" value="%{requirementAdminFlag}"/>
                                                <table border="0" width="98%" align="left" cellpadding="2" cellspacing="0">
                                                    <s:if test="%{#session.roleName =='Admin'}">
                                                        <tr>
                                                            <td colspan="6">
                                                                <span class="fieldLabel"> 

                                                                    <s:if test="#session.roleName != 'Vendor'">Account Name :</s:if>
                                                                    <s:else>Vendor Name :</s:else></span>&nbsp;
                                                                <a class="navigationText" href="<s:url action="../accounts/getAccount">
                                                                       <s:param name="id" value="%{accId}"/></s:url>">  <%-- %{currentContact.accountId}--%>
                                                                   <s:property value="#session.accountName"/></a>
                                                            </td>
                                                        </tr>
                                                    </s:if>
                                                    <tr class="headerText">


                                                        <td align="right" colspan="6">
                                                            <%
                                                                if (request.getAttribute(ApplicationConstants.RESULT_MSG) != null) {
                                                                    out.println(request.getAttribute(ApplicationConstants.RESULT_MSG));
                                                                }
                                                            %>
                                                            <%--<s:if test="#session.roleName == 'Admin'"></span>&nbsp;
                                                                <a href="<s:url value="../accounts/crmBackToList.action"/>" style="align:center;">
                                                                    <img alt="Back to List"
                                                                         src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                         width="66px" 
                                                                         height="19px"
                                                                         border="0" align="bottom"></a>&nbsp;&nbsp;

                                                            </s:if>--%>

                                                            <s:if test="%{(#session.roleName =='Pre-Sales') || ((#session.roleName =='Sales') && (currentRequirement.actionType == 'requirementEdit')) || ( (#session.roleName =='Recruitment') && (#session.isUserManager == 1 || #session.isUserTeamLead==1))}">
                                                                <%-- <INPUT type="button" CLASS="buttonBg" value="Back to List" onClick="history.go(-1)">&nbsp;&nbsp; --%>

                                                                <s:if test="%{requirementAdminFlag == 'YES'}">
                                                                    <a href="<s:url value="../requirement/requirementAdminList.action"/>" style="align:center;">
                                                                        <img alt="Back to List"
                                                                             src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                             width="66px" 
                                                                             height="19px"
                                                                             border="0" align="bottom"></a>
                                                                    </s:if>


                                                                <s:else>

                                                                    <%-- <s:if test="%{#session.roleName =='Sales' && accId != 0} "> --%>
                                                                    <%if (session.getAttribute("roleName").toString().equals("Sales") && !request.getAttribute("accId").toString().equals("0")) {%>
                                                                    <a href="<s:url action="../accounts/getAccount">
                                                                           <s:param name="id" value="%{accId}"/></s:url>" style="align:center;">
                                                                           <img alt="Back to List"
                                                                                src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                           width="66px" 
                                                                           height="19px"
                                                                           border="0" align="bottom"></a>
                                                                       <%} else {%>
                                                                       <%-- </s:if>
                                                                       <s:else> --%>
                                                                <%--    <a href="<s:url value="../requirement/requirementList.action"/>" style="align:center;"> <!-- going to here-->
                                                                        <img alt="Back to List"
                                                                             src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                             width="66px" 
                                                                             height="19px"
                                                                             border="0" align="bottom"></a> --%>
                                                                             
                                                                             <s:if test="%{ #session.roleName !='Pre-Sales'}">
                                                                             <a href="<s:url value="../requirement/requirementList.action"/>" style="align:center;"> <!-- going to here-->
                                                                        <img alt="Back to List"
                                                                             src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                             width="66px" 
                                                                             height="19px"
                                                                             border="0" align="bottom"></a>
                                                                       </s:if><s:else >
                                                                             <a href="<s:url value="../requirement/requirementListForPresalesMy.action"/>" style="align:center;"> <!-- going to here-->
                                                                        <img alt="Back to List"
                                                                             src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                             width="66px" 
                                                                             height="19px"
                                                                             border="0" align="bottom"></a>
                                                                       </s:else>
                                                                             
                                                                        <%}%>
                                                                        <%-- </s:else> --%>
                                                                    </s:else>
                                                                    <s:if test="%{ #session.roleName !='Pre-Sales'}">
                                                                    &nbsp;&nbsp;<s:submit align="right" id="save" value="Update" cssClass="buttonBg"/>
                                                                </s:if>
                                                            </s:if>
                                                            <s:else>
                                                                <s:if test="%{((#session.roleName =='Sales') || (#session.roleName =='Recruitment')) && (currentRequirement.actionType == 'requirementEdit')}">
                                                                    <a href="<s:url value="../requirement/requirementList.action"/>" style="align:center;">
                                                                        <img alt="Back to List"
                                                                             src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                             width="66px" 
                                                                             height="19px"
                                                                             border="0" align="bottom"></a>
                                                                        <s:submit align="right" id="save" value="Update" cssClass="buttonBg"/>
                                                                    </s:if>
                                                                    <s:else>
                                                                    <a href="<s:url action="../accounts/getAccount">
                                                                           <s:param name="id" value="%{accId}"/></s:url>" style="align:center;">
                                                                           <img alt="Back to List"
                                                                                src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                           width="66px" 
                                                                           height="19px"
                                                                           border="0" align="bottom"></a>
                                                                       <s:submit align="right" id="save" value="Save" cssClass="buttonBg"/>
                                                                    </s:else>
                                                                </s:else>


                                                        </td>
                                                    </tr>

                                                    <tr>

                                                        <td class="fieldLabel">Job Title :&nbsp;<font color="red">* </td>
                                                        <td>
                                                            <s:textfield name="title" id="jobTitle" cssClass="inputTextBlue" value="%{currentRequirement.title}" onchange="fieldLengthValidator(this);"/>

                                                        </td>
                                                        <td class="fieldLabel">Practice :&nbsp;<font color="red">* </td>
                                                        <td>
                                                            <s:select headerKey="-1" headerValue="--Please Select--" list="{'SAP','J2EE','Integration','Portals','Commerce','B2B','QA','BI','Microsoft','Others'}" name="practiceId" id="practiceId" cssClass="inputSelect" value="%{currentRequirement.practiceId}"/>
                                                        </td>
                                                        <td class="fieldLabel">Status :</td>
                                                        <td>
                                                         <%--   <s:select headerKey="open" headerValue="open" list="{'Forecast','InProgress','Hold','Withdrawn','won','lost'}" name="status" id="status" cssClass="inputSelect" value="%{currentRequirement.status}"/> --%>
                                                          <s:select label="Select Status"  name="status" id="status" list="requirementStatusList" cssClass="inputSelect" value="%{currentRequirement.status}"/> 
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <s:if test="%{currentRequirement.actionType == 'requirementEdit'}">   
                                                            <td class="fieldLabel">Created By :</td>
                                                            <td style="color: #333;font-family: lucida-sans;font-size: 14px;height: 1.7em;margin: 0;"> <s:label value="%{currentRequirement.createdBy}" /></td>
                                                        </s:if> 
                                                        <td class="fieldLabel">Contact No :</td>
                                                        <td>
                                                            <s:textfield name="contactNo" id="contactNo" cssClass="inputTextBlue" maxLength="20" onkeypress="return isNumberKey(event);" onchange="return workPhoneNoFormat(document.requirementForm.contactNo.value);" value="%{currentRequirement.contactNo}"/>
                                                        </td>
                                                        <td class="fieldLabel">Region:</td>
                                                        <td><s:select list="{'Central','East','Enterprise','West'}" name="region" id="region" cssClass="inputSelect" value="%{currentRequirement.region}" /></td>


                                                        <%--<s:hidden name="assignedTo" id="assignedTo" value="%{currentRequirement.assignedTo}"/>--%>
                                                    </tr>
                                                    <tr>
                                                        <td class="fieldLabel"> Primary Skills :&nbsp;<font color="red">* </td>
                                                        <td colspan="6">
                                                            <s:textfield name="skills" id="recSkills" title="Please enter technical skills only separated by comma (Example : java,db2,b2b). Do not enter description here.These comma separated skills will be evaluated by PresSales persons." cssClass="inputTextBlueDoubleExtraLarge2" value="%{currentRequirement.skills}" onchange="fieldLengthValidator(this);" onkeyup="commaValidator(this);"/>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="fieldLabel">Secondary Skills :&nbsp;</td>
                                                        <td colspan="6">
                                                            <s:textfield name="secondarySkills" id="secondarySkills" title="Please Enter Technical skills which are not in primary ." cssClass="inputTextBlueDoubleExtraLarge2" value="%{currentRequirement.secondarySkills}" onchange="fieldLengthValidator(this);" onkeyup="commaValidator(this);"/>
                                                        </td>
                                                    </tr>

                                                    <tr>

                                                        <td class="fieldLabel">Country :&nbsp;<font color="red">* </td>
                                                        <td>
                                                            <s:select headerKey="-1" headerValue="--Please Select--" name="country" id="country" list="{'USA','India','UK','Australia'}" cssClass="inputSelect" value="%{currentRequirement.country}" onchange="getStates(this.form, this.value);"/>
                                                        </td>
                                                        <td class="fieldLabel">State :</td>
                                                        <td>
                                                            <select name="state" id="state" class="inputSelect"><option value="<s:property value="%{currentRequirement.state}"/>"><s:property value="%{currentRequirement.state}"/></option></select>
                                                        </td>
                                                        <td class="fieldLabel">City :</td>
                                                        <td>
                                                            <s:textfield name="city" id="recCity" cssClass="inputTextBlue" value="%{currentRequirement.city}" onchange="fieldLengthValidator(this);"/>
                                                        </td>

                                                    </tr>
                                                    <tr>
                                                        <td class="fieldLabel"></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="fieldLabel">Start Date  :&nbsp;<font color="red">* </td>
                                                        <td><s:textfield name="startDate" id="startDate" cssClass="inputTextBlue" onchange="checkDates(this);"  value="%{currentRequirement.startDate}"/><a href="javascript:cal1.popup();">
                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                        </td>
                                                        <td class="fieldLabel">Duration :</td>
                                                        <td>
                                                            <s:textfield name="duration" id="duration" cssClass="inputTextBlue" value="%{currentRequirement.duration}" onchange="fieldLengthValidator(this);"/>
                                                        </td>
                                                        <td class="fieldLabel">Location :&nbsp;<font color="red">* </td>
                                                        <td>
                                                            <s:select headerKey="-1" headerValue="--Please Select--" name="location" id="location" list="#@java.util.LinkedHashMap@{'1':'Onsite','2':'Off Site','3':'Off Shore'}" cssClass="inputSelect" value="%{currentRequirement.location}" />
                                                        </td>
                                                        <!--<td class="fieldLabel">End Date  (mm/dd/yyyy):</td>
                                                                                                        <td>  s:textfield name="endDate" id="endDate" cssClass="inputTextBlue"  onchange="checkDates(this);" value="%{currentRequirement.endDate}"/><a href="javascript:cal2.popup();">
                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                        </td>-->
                                                    </tr>
                                                    <tr>

                                                        <td class="fieldLabel">Target Rate/Hr :<font color="red">* </td>
                                                        <s:if test="%{currentRequirement.actionType == 'requirementEdit'}">
                                                            <td>
                                                                <s:textfield name="targetRate" id="targetRate" cssClass="inputTextBlue" value="%{currentRequirement.targetRate}" onchange="fieldLengthValidator(this);"/>
                                                            </td>
                                                        </s:if>
                                                        <s:else>
                                                            <td>
                                                                <s:textfield name="targetRate" id="targetRate" cssClass="inputTextBlue" value="$50/hr" onchange="fieldLengthValidator(this);"/>
                                                            </td>
                                                        </s:else>
                                                        <td class="fieldLabel">Tax Term :</td>
                                                        <td>
                                                            <s:select headerKey="-1" headerValue="--Please Select--" list="{'Contract','Contract to hire','Green card or US citizens only','Permanent'}" name="taxTerm" id="taxTerm" cssClass="inputSelect" value="%{currentRequirement.taxTerm}"/>
                                                        </td>
                                                        <s:if test="%{currentRequirement.actionType == 'requirementEdit'}">
                                                            <td class="fieldLabel">No.of Positions :</td>
                                                            <td>
                                                                <s:textfield name="noResumes" id="noResumes" onkeypress="return isNumberKey(event)" cssClass="inputTextBlue" value="%{currentRequirement.noResumes}"/>
                                                            </td>
                                                        </s:if>
                                                        <s:else>
                                                            <td class="fieldLabel">No.of Positions :</td>  
                                                            <td>
                                                                <s:textfield name="noResumes" id="noResumes" onkeypress="return isNumberKey(event)" cssClass="inputTextBlue" value="1"/>
                                                            </td>
                                                        </s:else>
                                                    </tr>
                                                    <s:if test="%{currentRequirement.actionType == 'requirementEdit'}">
                                                        <tr>
                                                            <td class="fieldLabel">Reject Reason :</td>
                                                            <td>
                                                                <s:select headerKey="-1" headerValue="--Please Select--" list="{'Delayed Resumes','Not Right Candidate','Price Too High','Lack of Budget','Lost to other vendors','Position is on hold','No Reason'}" name="rejectReason" id="rejectReason" cssClass="inputSelect" value="%{currentRequirement.rejectReason}"/>
                                                            </td>
                                                        </tr>
                                                    </s:if>

                                                    <s:if test="%{currentRequirement.actionType == 'requirementEdit' && #session.roleName =='Recruitment'}">
                                                        <tr>
                                                            <td class="fieldLabel">Recruiter1 :</td>
                                                            <td>
                                                                <%--   <s:select headerKey="" headerValue="--Please Select--" list="assignedMembers" name="assignedTo" id="assignedTo" cssClass="inputSelect" value="%{currentRequirement.assignedTo}"/> --%>
                                                                <%--hi<s:property value='%{currentRequirement.assignedTo}'/>bye --%>	
                                                                <s:if test="%{requirementAdminFlag != 'YES'}">
                                                                    <s:select headerKey="" headerValue="--Please Select--" list="assignedMembers" cssClass="inputSelect" value="%{currentRequirement.assignedTo}" disabled="true"/>
                                                                    <s:hidden name="assignedTo" id="assignedTo" value="%{currentRequirement.assignedTo}"/> 
                                                                </s:if><s:else>
                                                                    <s:select headerKey="" headerValue="--Please Select--" list="assignedMembers" name="assignedTo" id="assignedTo" cssClass="inputSelect" value="%{currentRequirement.assignedTo}"/>
                                                                </s:else>

                                                            </td>
                                                            <td class="fieldLabel">Recruiter2 :</td>
                                                            <td>
                                                                <s:select headerKey="" headerValue="--Please Select--" list="assignedMembers" name="secondaryRecruiter" id="assignedTo1" cssClass="inputSelect" value="%{currentRequirement.secondaryRecruiter}"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td class="fieldLabel">Presales1:</td>
                                                            <td>
                                                                <s:select headerKey="" headerValue="--Please Select--" list="techLeadList" name="assignToTechLead" id="assignedTo2" cssClass="inputSelect" value="%{currentRequirement.assignToTechLead}"/>
                                                            </td>
                                                            <td class="fieldLabel">Presales2:</td>
                                                            <td>
                                                                <s:select headerKey="" headerValue="--Please Select--" list="techLeadList" name="secondaryTechLead" id="assignedTo3" cssClass="inputSelect" value="%{currentRequirement.secondaryTechLead}"/>
                                                            </td>
                                                            <%--<s:else>
                                                                                                                <td class="fieldLabel">Assigned To :</td>
                                                                                                                <td>
                                                                                                                    <s:select headerKey="Ramakrishna Subramanya.Chitoori" headerValue="Ramakrishna Subramanya.Chitoori" list="{}" name="assignedTo" id="assignedTo" cssClass="inputSelectLarge"/>
                                                                                                                </td>
                                                                                                            </s:else>--%>

                                                        </tr>

                                                    </s:if>


                                                    <s:if test="%{currentRequirement.actionType == 'requirementEdit' && (#session.roleName =='Sales' || #session.roleName =='Pre-Sales') }">

                                                        <tr>
                                                            <td class="fieldLabel">Recruiter1 :</td>
                                                            <td>
                                                                <s:select headerKey="" headerValue="--Please Select--" list="assignedMembers" name="assignedTo" id="assignedTo" cssClass="inputSelect" value="%{currentRequirement.assignedTo}" disabled="true"/>
                                                                <s:hidden name="assignedTo" id="assignedTo" value="%{currentRequirement.assignedTo}"/>
                                                                <%--hi<s:property value='%{currentRequirement.assignedTo}'/>bye --%>			
                                                            </td>
                                                            <td class="fieldLabel">Recruiter2 :</td>
                                                            <td>
                                                                <s:select headerKey="" headerValue="--Please Select--" list="assignedMembers" name="secondaryRecruiter" id="assignedTo1" cssClass="inputSelect" value="%{currentRequirement.secondaryRecruiter}" disabled="true"/>
                                                                <s:hidden name="secondaryRecruiter" id="assignedTo" value="%{currentRequirement.secondaryRecruiter}"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td class="fieldLabel">Presales1:</td>
                                                            <td>
                                                                <s:select headerKey="" headerValue="--Please Select--" list="techLeadList" name="assignToTechLead" id="assignedTo2" cssClass="inputSelect" value="%{currentRequirement.assignToTechLead}" disabled="true"/>
                                                                <s:hidden name="assignToTechLead" id="assignedTo" value="%{currentRequirement.assignToTechLead}"/>
                                                            </td>
                                                            <td class="fieldLabel">Presales2:</td>
                                                            <td>
                                                                <s:select headerKey="" headerValue="--Please Select--" list="techLeadList" name="secondaryTechLead" id="assignedTo3" cssClass="inputSelect" value="%{currentRequirement.secondaryTechLead}" disabled="true"/>
                                                                <s:hidden name="secondaryTechLead" id="assignedTo" value="%{currentRequirement.secondaryTechLead}"/>
                                                            </td>
                                                        </tr>   
                                                    </s:if>
                                                    <%-- <s:property value='%{currentRequirement.actionType}'/>123 --%>
                                                    <s:if test="%{currentRequirement.actionType =='requirementAdd' && ((#session.roleName == 'Sales') || (#session.roleName =='Admin'))}" > <%--  || currentRequirement.actionType ==' '--%>
                                                        <tr>
                                                            <td class="fieldLabel">Functions/Description :&nbsp;<font color="red">* </td>
                                                            <td colspan="4">
                                                                <s:textarea cols="81" rows="5" cssClass="inputTextarea" name="functions" id="functions" value="%{currentRequirement.functions}" onchange="fieldLengthValidator(this);"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td class="fieldLabel">Responsibilities :</td>
                                                            <td colspan="4">
                                                                <s:textarea cols="81" rows="5" cssClass="inputTextarea" name="responsibilities" id="responsibilities"  value="%{currentRequirement.responsibilities}" onchange="fieldLengthValidator(this);"/>
                                                            </td>
                                                        </tr>



                                                        <tr>
                                                            <td class="fieldLabel">Sales Comments :</td>
                                                            <td colspan="4">
                                                                <s:textarea cols="81" rows="5"  cssClass="inputTextarea" name="comments" id="recComments"  value="%{currentRequirement.comments}" onchange="fieldLengthValidator(this);"/>
                                                            </td>
                                                        </tr>
                                                        <%--<tr>
                                                                   <td class="fieldLabel">Rec Comments :</td>
                                                                   <td colspan="4">
                                                                       <s:textarea cols="81" rows="5" readonly="true" cssClass="inputTextarea" name="recruiterComments" id="recComments"  value="%{currentRequirement.recruiterComments}" onchange="fieldLengthValidator(this);"/>
                                                                   </td>
                                                       </tr>--%>
                                                    </s:if>


                                                    <!--<tr class="headerText">
                                                                                                        <td align="left" colspan="6">
                                                                                                            Contract Details:
                                                                                                        </td>
                                                                                                    </tr>-->

                                                </table>
                                            </s:form>
                                            <script type="text/JavaScript">
                                                var cal1 = new CalendarTime(document.forms['requirementForm'].elements['startDate']);
                                                cal1.year_scroll = true;
                                                cal1.time_comp = true;
                                            
                                            </script>
                                            <%--  </sx:div> 
                                                                                    
                                                                                        var cal2 = new CalendarTime(document.forms['requirementForm'].elements['endDate']);
                                                                                        cal2.year_scroll = true;
                                                                                        cal2.time_comp = true;
                                            --%>
                                        </div>
                                        <div id="jobDetailsDiv" class="tabcontent" >
                                            <s:form name="requirementForm1" id="requirementForm1" action="%{currentRequirement.actionType}" method="post" theme="simple" onsubmit="return requirementCheckUpdate();">
                                                <s:hidden name="objectId" id="objectId" value="%{currentRequirement.objectId}"/>
                                                <s:hidden name="divType" id="divType"  value="jobDetails"/>
                                                <s:hidden name="assignedTo" id="assignedTo"  value="%{currentRequirement.assignedTo}" />
                                                <s:hidden name="secondaryRecruiter" id="secondaryRecruiter"  value="%{currentRequirement.secondaryRecruiter}"/>
                                                <s:hidden name="assignToTechLead" id="assignToTechLead"  value="%{currentRequirement.assignToTechLead}"/>
                                                <s:hidden name="secondaryTechLead" id="secondaryTechLead"  value="%{currentRequirement.secondaryTechLead}"/>
                                                <s:hidden name="status" id="status" value="%{currentRequirement.status}"/>

                                                <table border="0" width="98%" align="left" cellpadding="2" cellspacing="0">

                                                    <tr class="headerText">

                                                        <td align="right" colspan="4">
                                                            <%
                                                                if (request.getAttribute(ApplicationConstants.RESULT_MSG) != null) {
                                                                    out.println(request.getAttribute(ApplicationConstants.RESULT_MSG));
                                                                }
                                                            %>

                                                            <%--   <s:if test="%{((#session.roleName =='Sales')) || ( (#session.roleName =='Recruitment') && (#session.isUserManager == 1 || #session.isUserTeamLead==1))}"> --%>
                                                           

                                                            <s:if test="%{(#session.roleName =='Pre-Sales') || ((#session.roleName =='Sales') && (currentRequirement.actionType == 'requirementEdit')) || ( (#session.roleName =='Recruitment') && (#session.isUserManager == 1 || #session.isUserTeamLead==1))}">
                                                                <%-- <INPUT type="button" CLASS="buttonBg" value="Back to List" onClick="history.go(-1)">&nbsp;&nbsp; --%>

                                                                <s:if test="%{requirementAdminFlag == 'YES'}">
                                                                    <a href="<s:url value="../requirement/requirementAdminList.action"/>" style="align:center;">
                                                                        <img alt="Back to List"
                                                                             src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                             width="66px" 
                                                                             height="19px"
                                                                             border="0" align="bottom"></a>
                                                                    </s:if>


                                                                <s:else>

                                                                    <%-- <s:if test="%{#session.roleName =='Sales' && accId != 0} "> --%>
                                                                    <%if (session.getAttribute("roleName").toString().equals("Sales") && !request.getAttribute("accId").toString().equals("0")) {%>
                                                                    <a href="<s:url action="../accounts/getAccount">
                                                                           <s:param name="id" value="%{accId}"/></s:url>" style="align:center;">
                                                                           <img alt="Back to List"
                                                                                src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                           width="66px" 
                                                                           height="19px"
                                                                           border="0" align="bottom"></a>
                                                                       <%} else {%>
                                                                       <%-- </s:if>
                                                                       <s:else> --%>
                                                                    <a href="<s:url value="../requirement/requirementList.action"/>" style="align:center;"> <!-- going to here-->
                                                                        <img alt="Back to List"
                                                                             src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                             width="66px" 
                                                                             height="19px"
                                                                             border="0" align="bottom"></a>
                                                                        <%}%>
                                                                        <%-- </s:else> --%>
                                                                    </s:else>
                                                                    
                                                            </s:if>
                                                            <s:else>
                                                                <s:if test="%{((#session.roleName =='Sales') || (#session.roleName =='Recruitment')) && (currentRequirement.actionType == 'requirementEdit')}">
                                                                    <a href="<s:url value="../requirement/requirementList.action"/>" style="align:center;">
                                                                        <img alt="Back to List"
                                                                             src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                             width="66px" 
                                                                             height="19px"
                                                                             border="0" align="bottom"></a>
                                                                      
                                                                    </s:if>
                                                                    <s:else>
                                                                    <a href="<s:url action="../accounts/getAccount">
                                                                           <s:param name="id" value="%{accId}"/></s:url>" style="align:center;">
                                                                           <img alt="Back to List"
                                                                                src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                           width="66px" 
                                                                           height="19px"
                                                                           border="0" align="bottom"></a>
                                                                       
                                                                    </s:else>
                                                                </s:else>


                                                        
                                                            <s:if test="%{((#session.roleName =='Sales')) || ( (#session.roleName =='Recruitment'))}">
                                                                <s:submit align="right" id="save" value="Update" cssClass="buttonBg"/>
                                                            </s:if>
                                                        </td>
                                                    </tr>

                                                    <s:if test="%{#session.roleName =='Sales'}">
                                                        <tr>
                                                            <td class="fieldLabel">Functions/Description :&nbsp;<font color="red">* </td>
                                                            <td colspan="4">
                                                                <s:textarea cols="81" rows="5" cssClass="inputTextarea" name="functions" id="functions" value="%{currentRequirement.functions}" onchange="fieldLengthValidator(this);"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td class="fieldLabel">Responsibilities :</td>
                                                            <td colspan="4">
                                                                <s:textarea cols="81" rows="5" cssClass="inputTextarea" name="responsibilities" id="responsibilities"  value="%{currentRequirement.responsibilities}" onchange="fieldLengthValidator(this);"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td class="fieldLabel">Sales Comments :</td>
                                                            <td colspan="4">
                                                                <s:textarea cols="81" rows="5" cssClass="inputTextarea" name="comments" id="recComments"  value="%{currentRequirement.comments}" onchange="fieldLengthValidator(this);"/>
                                                                <s:hidden name="" value=""/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td class="fieldLabel">Rec Comments :</td>
                                                            <td colspan="4">
                                                                <s:textarea cols="81" rows="5" readonly="true" cssClass="inputTextarea" name="recruiterComments" id="recRecruiterComments"  value="%{currentRequirement.recruiterComments}" onchange="fieldLengthValidator(this);"/>
                                                            </td>
                                                        </tr>
                                                    </s:if><s:else>
                                                        <tr>
                                                            <td class="fieldLabel">Functions/Description :&nbsp;<font color="red">* </td>
                                                            <td colspan="4">
                                                                <s:textarea cols="81" rows="5" readonly="true" cssClass="inputTextarea" name="functions" id="functions" value="%{currentRequirement.functions}" onchange="fieldLengthValidator(this);"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td class="fieldLabel">Responsibilities :</td>
                                                            <td colspan="4">
                                                                <s:textarea cols="81" rows="5" readonly="true" cssClass="inputTextarea" name="responsibilities" id="responsibilities"  value="%{currentRequirement.responsibilities}" onchange="fieldLengthValidator(this);"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td class="fieldLabel">Sales Comments :</td>
                                                            <td colspan="4">
                                                                <s:textarea cols="81" rows="5" readonly="true" cssClass="inputTextarea" name="comments" id="recComments"  value="%{currentRequirement.comments}" onchange="fieldLengthValidator(this);"/>
                                                                <s:hidden name="" value=""/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td class="fieldLabel">Rec Comments :</td>
                                                            <td colspan="4">
                                                                <s:textarea cols="81" rows="5"  cssClass="inputTextarea" name="recruiterComments" id="recRecruiterComments"  value="%{currentRequirement.recruiterComments}" onchange="fieldLengthValidator(this);"/>
                                                            </td>
                                                        </tr> 
                                                    </s:else>

                                                    <!-- <tr>
                                                                                            <td class="fieldLabel">Education :</td>
                                                                                            <td colspan="1">
                                                                                                s:textfield cssClass="inputTextBlue" name="education" id="education" value="%{currentRequirement.education}" onchange="fieldLengthValidator(this);"/>
                                                                                            </td>
                                                                                            <td class="fieldLabel">Experience(In Years) :</td>
                                                                                            <td>
                                                                                                s:textfield name="experience" id="experience" cssClass="inputTextBlue" value="%{currentRequirement.experience}"/>
                                                                                            </td>
                                                                                        </tr>-->
                                                </table>
                                            </s:form>

                                        </div>  

                                        <div id="timeLinesDiv" class="tabcontent" >
                                            <table border="0" width="90%" align="left" cellpadding="2" cellspacing="0">
                                                <tr class="headerText">
                                                    <td colspan="6" align="right">&nbsp;
                                                     <s:if test="%{(#session.roleName =='Pre-Sales') || ((#session.roleName =='Sales') && (currentRequirement.actionType == 'requirementEdit')) || ( (#session.roleName =='Recruitment') && (#session.isUserManager == 1 || #session.isUserTeamLead==1))}">
                                                                <%-- <INPUT type="button" CLASS="buttonBg" value="Back to List" onClick="history.go(-1)">&nbsp;&nbsp; --%>

                                                                <s:if test="%{requirementAdminFlag == 'YES'}">
                                                                    <a href="<s:url value="../requirement/requirementAdminList.action"/>" style="align:center;">
                                                                        <img alt="Back to List"
                                                                             src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                             width="66px" 
                                                                             height="19px"
                                                                             border="0" align="bottom"></a>
                                                                    </s:if>


                                                                <s:else>

                                                                    <%-- <s:if test="%{#session.roleName =='Sales' && accId != 0} "> --%>
                                                                    <%if (session.getAttribute("roleName").toString().equals("Sales") && !request.getAttribute("accId").toString().equals("0")) {%>
                                                                    <a href="<s:url action="../accounts/getAccount">
                                                                           <s:param name="id" value="%{accId}"/></s:url>" style="align:center;">
                                                                           <img alt="Back to List"
                                                                                src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                           width="66px" 
                                                                           height="19px"
                                                                           border="0" align="bottom"></a>
                                                                       <%} else {%>
                                                                       <%-- </s:if>
                                                                       <s:else> --%>
                                                                    <a href="<s:url value="../requirement/requirementList.action"/>" style="align:center;"> <!-- going to here-->
                                                                        <img alt="Back to List"
                                                                             src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                             width="66px" 
                                                                             height="19px"
                                                                             border="0" align="bottom"></a>
                                                                        <%}%>
                                                                        <%-- </s:else> --%>
                                                                    </s:else>
                                                                    
                                                            </s:if>
                                                            <s:else>
                                                                <s:if test="%{((#session.roleName =='Sales') || (#session.roleName =='Recruitment')) && (currentRequirement.actionType == 'requirementEdit')}">
                                                                    <a href="<s:url value="../requirement/requirementList.action"/>" style="align:center;">
                                                                        <img alt="Back to List"
                                                                             src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                             width="66px" 
                                                                             height="19px"
                                                                             border="0" align="bottom"></a>
                                                                      
                                                                    </s:if>
                                                                    <s:else>
                                                                    <a href="<s:url action="../accounts/getAccount">
                                                                           <s:param name="id" value="%{accId}"/></s:url>" style="align:center;">
                                                                           <img alt="Back to List"
                                                                                src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                           width="66px" 
                                                                           height="19px"
                                                                           border="0" align="bottom"></a>
                                                                       
                                                                    </s:else>
                                                                </s:else>
                                                    
                                                    </td>

                                                </tr>
                                                <tr>
                                                    <td class="fieldLabel"> Created Date: </td>
                                                    <td style="color: #333;font-family: lucida-sans;font-size: 14px;height: 1.7em;margin: 0;padding-left:15px;"><s:property value="%{currentRequirement.createdDate}"/></td>
                                                    <td class="fieldLabel"> Assigned Date: </td>
                                                    <td style="color: #333;font-family: lucida-sans;font-size: 14px;height: 1.7em;margin: 0;padding-left:15px;"><s:property value="%{currentRequirement.assignedDate}"/></td>
                                                </tr>
                                                <tr>
                                                    <td class="fieldLabel"> Modified Date: </td>
                                                    <td style="color: #333;font-family: lucida-sans;font-size: 14px;height: 1.7em;margin: 0;padding-left:15px;"><s:property value="%{currentRequirement.modifiedDate}"/></td>
                                                    <td class="fieldLabel"> Closed Date: </td>
                                                    <td style="color: #333;font-family: lucida-sans;font-size: 14px;height: 1.7em;margin: 0;padding-left:15px;"><s:property value="%{currentRequirement.closedDate}"/></td>
                                                </tr>
                                                <%-- <tr>
                                                     <td class="fieldLabel"> Modified Date: </td>
                                                     <td style="color: #333;font-family: lucida-sans;font-size: 14px;height: 1.7em;margin: 0;padding-left:15px;"><s:property value="%{currentRequirement.modifiedDate}"/></td>
                                                     <td class="fieldLabel"> Approved Date: </td>
                                                     <td style="color: #333;font-family: lucida-sans;font-size: 14px;height: 1.7em;margin: 0;padding-left:15px;"><s:property value=""/></td>
                                                 </tr>
                                                 <tr>
                                                     <td class="fieldLabel"> Released Date: </td>
                                                     <td style="color: #333;font-family: lucida-sans;font-size: 14px;height: 1.7em;margin: 0;padding-left:15px;"><s:property value=""/></td>
                                                     <td class="fieldLabel"> Closed Date: </td>
                                                     <td style="color: #333;font-family: lucida-sans;font-size: 14px;height: 1.7em;margin: 0;padding-left:15px;"><s:property value="%{currentRequirement.closedDate}"/></td>
                                                 </tr> --%>
                                            </table>
                                        </div>    
                                        <%--   </sx:tabbedpanel> --%>
                                    </div>
                                    
                                     <% if (request.getAttribute("currentAccountId") != null) {
                                        currentAccountId = (String) request.getAttribute("currentAccountId");
                                    }
                                    //  out.print("accId:"+currentAccountId);
                                    %>
                                    <s:if test="%{currentRequirement.actionType == 'requirementEdit'}">
                                        
                                        <ul id="consultantsTabs" class="shadetabs" >
                                            <li><a href="#" rel="consultantsList" class="selected" >Consultants List</a></li>
                                            <li><a href="#" rel="attachmentsList" >Attachments</a></li>
                                        </ul>

                                        <div id="consultantsList" style="border:1px solid gray; width:845px;height: 200px; overflow:auto; margin-bottom: 1em;">

                                            <%
                                                /* String Variable for storing current position of records in dbgrid*/
                                                strTmp = request.getParameter("txtCurr");

                                                intCurr = 1;

                                                if (strTmp != null) {
                                                    intCurr = Integer.parseInt(strTmp);
                                                }

                                                try {

                                                    /* Getting DataSource using Service Locator */
                                                    connection = ConnectionProvider.getInstance().getConnection();

                                                    /* Sql query for retrieving resultset from DataBase */
                                                    String queryString = null;
                                                    String addAction = "consultantForRequirement.action?objectId=" + request.getAttribute("objectId") + "&requirementAdminFlag=" + request.getAttribute("requirementAdminFlag")+"&accId="+ request.getAttribute("accId");

                                                    /*queryString="select tblRec.CreatedBy,tblRec.Id as Id,tblRec.RequirementId as Id1,concat(FName,'.',LName) as Name,TechRate,tblRec.RateNegotiated,StartDate,Status,tblRec.Comments from " +
                                                    "tblRec,tblRecConsultant where tblRecConsultant.Id=tblRec.ConsultantId and tblRec.RequirementId ="+request.getAttribute("objectId");*/
                                                    queryString = "select ResumeAttachmentId as Id2,tblRecConsultant.Id as Id3,tblRec.CreatedBy,tblRec.Id as Id,tblRec.RequirementId as Id1,concat(FName,'.',LName) as Name,TechRate,tblRec.RateNegotiated,tblRec.AvailableFrom AS StartDate,Status,tblRec.Comments from "
                                                            + "tblRec,tblRecConsultant where tblRecConsultant.Id=tblRec.ConsultantId and tblRec.RequirementId =" + request.getAttribute("objectId");
                                                   //  out.println(queryString);
%>

                                            <form method="post" id="frmDBGrid" name="frmDBGrid" action=""> 
                                                <table cellpadding="0" cellspacing="0" width="100%">
                                                    <s:if test="%{(#session.roleName =='Recruitment') || (#session.roleName =='Admin')}">
                                                        <tr>
                                                            <td class="headerText">  <a href="/<%=ApplicationConstants.CONTEXT_PATH%>/crm/requirement/<%=addAction%>" style="align:left;">

                                                                    <img alt="Add Requirement"
                                                                         src="<s:url value="/includes/images/add.gif"/>" 
                                                                         width="33px" 
                                                                         height="19px"
                                                                         border="0" align="left"></a>&nbsp;&nbsp;

                                                            </td>

                                                        </tr> 
                                                    </s:if>
                                                    <%
                                                        if (request.getAttribute(ApplicationConstants.RESULT_MSG) != null) {
                                                            out.println(request.getAttribute(ApplicationConstants.RESULT_MSG));
                                                        }
                                                    %>
                                                    <tr>
                                                        <td>
                                                            <s:if test="%{#session.roleName =='Recruitment'}">
                                                                <!-- DataGrid for list all activities -->
                                                                <grd:dbgrid id="tblEmpLeaves" name="tblEmpLeaves" width="100" pageSize="10" 
                                                                currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">


                                                                    <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                                   imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                    addImage="../../includes/images/DBGrid/Add.png" addAction='<%=addAction%>' />
                                                                    <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" />

                                                                    <%--  <grd:imagecolumn  headerText="Edit" width="5"  HAlign="center" 
                                                                                        imageSrc="../../includes/images/DBGrid/Edit.gif" 
                                                                                        linkUrl="getConsultantRequirement.action?consultId={Id}&objectId={Id1} " 
                                                                                        imageBorder="0" imageWidth="16" imageHeight="16" 
                                                                                        alterText="Click to edit" /> --%>
                                                                    <grd:imagecolumn  headerText="Edit" width="5"  HAlign="center" 
                                                                                      imageSrc="../../includes/images/DBGrid/Edit.gif" 
                                                                                      linkUrl="javascript:getConsultantRequirement({Id3},{Id},{Id1},{Id2})" 
                                                                                      imageBorder="0" imageWidth="16" imageHeight="16" 
                                                                                      alterText="Click to edit" /> 
                                                                    <grd:textcolumn dataField="Name" headerText="Name" width="15"/>
                                                                    <%-- <grd:textcolumn dataField="TechRate" headerText="Tech Rating" dataFormat="" width="12" />
                                                                     <grd:textcolumn dataField="RateNegotiated" headerText="Rate" dataFormat="" width="12" />--%>
                                                                    <grd:datecolumn dataField="StartDate" headerText="Date Available"  width="16"  dataFormat="MM-dd-yyyy" />
                                                                    <grd:textcolumn dataField="Status"  headerText="Current Status"   width="20" />
                                                                    <grd:textcolumn dataField="CreatedBy" headerText="SubmittedBy" width="15"/>
                                                                    <%--  <grd:ajaxpopup dataField="Comments" id="{Id}" linkText=":::" maxLength="15" 
                                                                                                                   headerText="Comments" JSFunction="getComments" width="20" />
                                                                    --%>
                                                                    <%-- new for resume submission to customer --%>
                                                                    <%--   <grd:imagecolumn  headerText="ResumeSubmission" width="4" HAlign="center" imageSrc="../../includes/images/DBGrid/reminder.jpg"
                                                                                         linkUrl="javascript:win_open2('/Hubble/crm/requirement/CustomerResumeSubmissionWindow.jsp','{Id}','{Id1}','{Id3}','{Id2}')"
                                                                                         imageBorder="0" 
                                                                                         imageWidth="16" imageHeight="16" alterText="Click to edit"></grd:imagecolumn> --%>

                                                                    <%--<grd:imagecolumn  headerText="Add TechnicalReview" width="4" HAlign="center" imageSrc="../../includes/images/DBGrid/forward.png"
                                                                                     linkUrl="javascript:doForward({Id3},{Id},{Id1})"
                                                                                     imageBorder="0" 
                                                                                     imageWidth="20" imageHeight="20" alterText="Click to edit"></grd:imagecolumn> --%>
                                                                </grd:dbgrid>


                                                                <!-- these components are DBGrid Specific  -->
                                                                <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                                <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                                <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">

                                                                <!-- <input id="txtDBId" TYPE="hidden" NAME="txtDBId" VALUE="0">
                                                                                            <input id="txtTRId"TYPE="hidden" NAME="txtTRId" VALUE="1">  -->
                                                            </s:if>
                                                            <s:elseif test="%{#session.roleName =='Pre-Sales'}">
                                                                <!-- DataGrid for list all activities -->
                                                                <grd:dbgrid id="tblEmpLeaves" name="tblEmpLeaves" width="100" pageSize="10" 
                                                                currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">


                                                                    <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                                   imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                                   />
                                                                    <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" />

                                                                    <grd:imagecolumn  headerText="Edit" width="5"  HAlign="center" 
                                                                                      imageSrc="../../includes/images/DBGrid/Edit.gif" 
                                                                                      linkUrl="getConsultantRequirement.action?consultId={Id}&objectId={Id1} " 
                                                                                      imageBorder="0" imageWidth="16" imageHeight="16" 
                                                                                      alterText="Click to edit" /> 
                                                                    <grd:textcolumn dataField="Name" headerText="Name" width="15"/>
                                                                    <%-- <grd:textcolumn dataField="TechRate" headerText="Tech Rating" dataFormat="" width="12" />
                                                                     <grd:textcolumn dataField="RateNegotiated" headerText="Rate" dataFormat="" width="12" />--%>
                                                                    <grd:datecolumn dataField="StartDate" headerText="Date Available"  width="16"  dataFormat="MM-dd-yyyy" />
                                                                    <grd:textcolumn dataField="Status"  headerText="Corrent Status"   width="20" />
                                                                    <grd:textcolumn dataField="CreatedBy" headerText="SubmittedBy" width="15"/>
                                                                    <%--  <grd:ajaxpopup dataField="Comments" id="{Id}" linkText=":::" maxLength="15" 
                                                                                                                   headerText="Comments" JSFunction="getComments" width="20" />
                                                                    --%>
                                                                    <%-- new for resume submission to customer --%>
                                                                    <%--    <grd:imagecolumn  headerText="ResumeSubmission" width="4" HAlign="center" imageSrc="../../includes/images/DBGrid/reminder.jpg"
                                                                                          linkUrl="javascript:win_open2('/Hubble/crm/requirement/CustomerResumeSubmissionWindow.jsp','{Id}','{Id1}','{Id3}','{Id2}')"
                                                                                          imageBorder="0" 
                                                                                          imageWidth="16" imageHeight="16" alterText="Click to edit"></grd:imagecolumn> --%>
                                                                </grd:dbgrid>

                                                                <!-- these components are DBGrid Specific  -->
                                                                <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                                <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                                <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">

                                                                <!-- <input id="txtDBId" TYPE="hidden" NAME="txtDBId" VALUE="0">
                                                                                            <input id="txtTRId"TYPE="hidden" NAME="txtTRId" VALUE="1">  -->
                                                            </s:elseif>
                                                            <s:else>
                                                                <!-- DataGrid for list all activities -->
                                                                <grd:dbgrid id="tblEmpLeaves" name="tblEmpLeaves" width="100" pageSize="10" 
                                                                currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">


                                                                    <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                                   imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                    addImage="../../includes/images/DBGrid/Add.png" addAction='<%=addAction%>' />
                                                                    <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" />

                                                                    <%--  <grd:imagecolumn  headerText="Edit" width="5"  HAlign="center" 
                                                                                        imageSrc="../../includes/images/DBGrid/Edit.gif" 
                                                                                        linkUrl="getConsultantRequirement.action?consultId={Id}&objectId={Id1} " 
                                                                                        imageBorder="0" imageWidth="16" imageHeight="16" 
                                                                                        alterText="Click to edit" /> --%>
                                                                    <grd:imagecolumn  headerText="Edit" width="5"  HAlign="center" 
                                                                                      imageSrc="../../includes/images/DBGrid/Edit.gif" 
                                                                                      linkUrl="javascript:getConsultantRequirement({Id3},{Id},{Id1},{Id2})" 
                                                                                      imageBorder="0" imageWidth="16" imageHeight="16" 
                                                                                      alterText="Click to edit" /> 
                                                                    <grd:textcolumn dataField="Name" headerText="Name" width="15"/>
                                                                    <%-- <grd:textcolumn dataField="TechRate" headerText="Tech Rating" dataFormat="" width="12" />
                                                                     <grd:textcolumn dataField="RateNegotiated" headerText="Rate" dataFormat="" width="12" />--%>
                                                                    <grd:datecolumn dataField="StartDate" headerText="Date Available"  width="16"  dataFormat="MM-dd-yyyy" />
                                                                    <grd:textcolumn dataField="Status"  headerText="Current Status"   width="20" />
                                                                    <grd:textcolumn dataField="CreatedBy" headerText="SubmittedBy" width="15"/>
                                                                    <%--  <grd:ajaxpopup dataField="Comments" id="{Id}" linkText=":::" maxLength="15" 
                                                                                                                   headerText="Comments" JSFunction="getComments" width="20" />
                                                                    --%>
                                                                    <%-- new for resume submission to customer --%>
                                                                    <%--   <grd:imagecolumn  headerText="ResumeSubmission" width="4" HAlign="center" imageSrc="../../includes/images/DBGrid/reminder.jpg"
                                                                                         linkUrl="javascript:win_open2('/Hubble/crm/requirement/CustomerResumeSubmissionWindow.jsp','{Id}','{Id1}','{Id3}','{Id2}')"
                                                                                         imageBorder="0" 
                                                                                         imageWidth="16" imageHeight="16" alterText="Click to edit"></grd:imagecolumn> --%>


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
                                                } catch (Exception ex) {
                                                    out.println(ex.toString());
                                                } finally {
                                                    if (connection != null) {
                                                        connection.close();
                                                    }
                                                }
                                            %>
                                            <%--   </sx:div> --%>

                                            <%--</sx:tabbedpanel> --%>
                                        </div>
                                        
                                        <!-- Attachments Div Start -->
                                        
                                         <%--    <sx:div id="attachmentsList" label="Resume Attachments"> --%>
                                    <div id="attachmentsList" class="tabcontent" > 
                                        <%
                                            /* String Variable for storing current position of records in dbgrid*/
                                            strTmp = request.getParameter("txtAttachCurr");

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
                                             try 
                                             {
                                               connection = ConnectionProvider.getInstance().getConnection();
    
                                            
                                          //  System.out.println("currentAccountId"+request.getAttribute("currentAccountId")); 
                                            /* Sql query for retrieving resultset from DataBase */
                                            queryString = "Select Id,CASE WHEN (AttachmentType!='-1') THEN AttachmentType ELSE '-' END AS AttachmentType,AttachmentName,DateUploaded,UploadedBy,ObjectId,ObjectType from tblAttachments WHERE";
                                            queryString = queryString + " ObjectId =" + request.getAttribute("objectId")  + " ORDER BY DateUploaded DESC";
                                           //   out.println("queryString "+queryString);
                                            String attachmentAction = "getRequirementAttachment.action";

                                            if (request.getAttribute("currentAccountId") != null) {
                                                attachmentAction = attachmentAction + "?objectId=" + request.getAttribute("objectId") +"&accId=" + request.getAttribute("accId");
                                            }
                                        %>

                                        <form action=""  method="post" name="frmAttachGrid"> 
                                            <table cellpadding="0" cellspacing="0" width="100%">
                                                <tr>
                                                    <td class="headerText">
                                                        <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <!-- DataGrid for list all Attachments -->
                                                        <grd:dbgrid id="tblReqAttachments" name="tblReqAttachments" width="100" pageSize="10" 
                                                        currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                        dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">

                                                            <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                           imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                           addImage="../../includes/images/DBGrid/Add.png" 
                                                              addAction="<%=attachmentAction%>"
                                                                           scriptFunction="getAttachments"/>
                                                            <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" />
                                                            <grd:textcolumn dataField="AttachmentType"  headerText="Attachment Type" width="30"/>  
                                                            <grd:textcolumn dataField="AttachmentName"  headerText="Attachment Name"   width="30" sortable="true"/> 

                                                            <grd:datecolumn dataField="DateUploaded"  headerText="Date Uploaded" dataFormat="MM-dd-yyyy HH:mm:SS" width="20"/>
                                                            <grd:imagecolumn headerText="Download" width="4" HAlign="center" 
                                                                             imageSrc="../../includes/images/download_11x10.jpg"
                                                                             linkUrl="getAttachmentDownload.action?Id={Id}" imageBorder="0"
                                                                             imageWidth="11" imageHeight="10" alterText="Download"></grd:imagecolumn>
                                                        </grd:dbgrid>
                                                        <!-- these components are DBGrid Specific  -->



                                                       <input TYPE="hidden" NAME="txtAttachCurr" VALUE="<%=intCurr%>">                                                            
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
                                             <%
                                                } catch (Exception ex) {
                                                    out.println(ex.toString());
                                                } finally {
                                                    if (connection != null) {
                                                        connection.close();
                                                    }
                                                }
                                            %>             
                                    </div>
                                        <!-- Attachments Div end -->

                                    </s:if>

                                    <script type="text/javascript">

                                        var countries=new ddtabcontent("accountTabs")
                                        countries.setpersist(false)
                                        countries.setselectedClassTarget("link") //"link" or "linkparent"
                                        countries.init()
                                           var countries1=new ddtabcontent("consultantsTabs")
                                        countries1.setpersist(false)
                                        countries1.setselectedClassTarget("link") //"link" or "linkparent"
                                        countries1.init()
                                    </script>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <!--//START FOOTER : Record for Footer Background and Content-->
            <tr class="footerBg">
                <td align="center"><s:include value="/includes/template/Footer.jsp"/></td>
            </tr>
            <tr>
                <td>

                    <div style="display: none; position: absolute; top:115px;left:350px;overflow:auto;" id="menu-popup">
                        <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" />
                    </div>

                </td>
            </tr>
        </table>
    </body>
</html>
