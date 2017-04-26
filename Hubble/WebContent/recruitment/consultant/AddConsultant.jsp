<%--
/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :com.mss.mirage.recruitment
 *
 * Date    :  August 04, 2008, 6:17 PM
 *
 * Author  : Hari Krishna Kondala<hkondala@miraclesoft.com>
 *
 * File    : AddConsultant.jsp
 *
 * Copyright 2008 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
--%>
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ page import="com.mss.mirage.util.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
<%@ page import="java.util.Date" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>Hubble Organization Portal :: Recruitment-Consultant Details</title>

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script language="javascript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/RequirementUtil.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ConsultantUtil.js"/>"></script>
        <%--<script type="text/JavaScript" src="<s:url value="/includes/javascripts/recruitment/ConsultantTechReviewsClientValidation.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/recruitment/ConsultantAddClientValidation.js"/>"></script>--%>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/StringUtility.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/RecStandardClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <script language="javascript" src="<s:url value="/includes/javascripts/DBConsultantGrid.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>

        <s:include value="/includes/template/headerScript.html"/>
        <!--<script type="text/JavaScript">
            function getEmailId(){
                var emailValue = document.addConsultantForm.email2.value;
                if(emailValue != ''){
                    getDetails();
                }
            }
            </script>-->

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
    </head>
    <!--<body class="bodyGeneral" oncontextmenu="return false;" onload="init();"> -->
    <body class="bodyGeneral" oncontextmenu="return false;" >
        <%!
            /* Declarations */
            Connection connection;
            String accountPrimaryTeamMember;
            String userId;
            String userRoleName;
            int isUserManager;
            String queryString;
            String consultantId;
            ;
        String strTmp;
            String strSortCol;
            String strSortOrd;
            int intSortOrd = 0;
            int intCurr;
            String strSQL = null;
            String custId = null;
            boolean blnSortAsc = true;
            String reqList = null;
            String isSearch = null;
            String url = null;
            String consultId;
            String requirementId;
            String requirementAdminFlag;
        %>
        <%
            consultId = request.getSession(false).getAttribute("consultId").toString();
            requirementId = request.getSession(false).getAttribute("requirementId").toString();
            requirementAdminFlag = request.getSession(false).getAttribute("requirementAdminFlag").toString();
        %>
        <%--<%!
                
                /* Declarations */
                Connection connection;
                String queryString;
                String strTmp;
                String strSortCol;
                String strSortOrd;
                String consultantId;
                
                int intSortOrd = 0;
                int intCurr;
                boolean blnSortAsc = true;
                %>--%>
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

                            <!--//START DATA COLUMN n: Coloumn for LeftMenu-->

                            <!--//START DATA COLUMN : Coloumn for Screen Content-->
                            <td width="850px" class="cellBorder" valign="top" style="padding-left:10px;padding-top:5px;">
                                <ul id="addConsultantTabs" class="shadetabs" >
                                    <li ><a href="#" class="selected" rel="consultantDiv"  >Consultant Details</a></li>                                    
                                </ul>

                                <%--  <sx:tabbedpanel id="consultantPanel" cssStyle="width: 840px;padding: 5px 5px;"> --%>
                                <%-- <sx:div name="consultantDiv" id="consultantDiv" label="Consultant"> --%>
                                <s:if test="%{currentConsultant.currentAction!= 'editConsultant'}">  
                                    <div  style="border:1px solid gray; width:800px;height: 400px; overflow:auto; margin-bottom: 1em;">
                                    </s:if>
                                    <s:else>
                                        <div  style="border:1px solid gray; width:800px;height: 320px; overflow:auto; margin-bottom: 1em;">

                                        </s:else>
                                        <div id="consultantDiv" class="tabcontent"  >
                                            <div id="overlayRecruitment"></div>              <!-- Start Overlay -->

                                            <div id="specialBoxRecruitment" style="left:auto;width:410px;">

                                                <div   id="addedConultantDiv" style="display: none;" >
                                                    <table align="center" border="0" cellspacing="0" style="width:100%;" >
                                                        <tr>                               
                                                            <td colspan="1" style="background-color: #288AD1" >
                                                        <lable style="color:darkblue;font: bold" align="left">
                                                            <span id="headerLabel"></span>
                                                        </lable>

                                                        <a style="float:right;" href="#" onmousedown="closeConsultantList()" >
                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> 

                                                        </a>  

                                                        </td></tr>
                                                        <tr>
                                                            <td colspan="4">
                                                                <div id="load" style="color: green;display: none;">Loading..</div>
                                                                <div id="resultMessage"></div>
                                                            </td>
                                                        </tr>

                                                        <tr>
                                                            <td>
                                                                <table id="tblConsultantStatus" align='center' cellpadding='1' cellspacing='1' border='0' class="gridTable" width="404">
                                                                    <%--   <script type="text/JavaScript" src="<s:url value="/includes/javascripts/wz_tooltip.js"/>"></script> --%>
                                                                    <COLGROUP ALIGN="left" >

                                                                        <COL width="7%">
                                                                        <COL width="7%">
                                                                        <COL width="7%">


                                                                </table> 
                                                            </td>
                                                        </tr>

                                                    </table>    

                                                </div>
                                            </div>
                                            <s:form name="addConsultantForm" action="%{currentConsultant.currentAction}" method="post" enctype="multipart/form-data" theme="simple" onsubmit="return checkConsultantAddForm();">
                                                <s:hidden name="objectId" id="objectId" value="%{currentConsultant.objectId}"/> 
                                                <%--<s:textfield name="reqList" id="reqList" value="%{reqList}"/> --%>
                                                <s:hidden name="reqList" id="reqList" value="%{reqList}"/> 
                                                <s:hidden name="requestType" value="A"/>
                                                <s:hidden name="uploadFileName" value="" />
                                                <s:hidden name="filepath" value="" /> 
                                                <s:hidden name="date" value="" /> 
                                                <s:hidden name="siteConsultants" value="%{siteConsultants}" /> 
                                                <s:hidden name="consultId" value="%{consultId}"/>
                                                <s:hidden name="requirementId" value="%{requirementId}"/>
                                                <s:hidden name="requirementAdminFlag" value="%{requirementAdminFlag}"/>
                                                <table border="0" width="100%" align="center" cellpadding="2" cellspacing="0">
                                                    <tr >
                                                        <td align="left" colspan="2">
                                                            <%--  Consultant Details:--%>
                                                        </td>
                                                        <td align="right" colspan="5">
                                                            <%
                                                                if (request.getAttribute(ApplicationConstants.RESULT_MSG) != null) {
                                                                    out.println(request.getAttribute(ApplicationConstants.RESULT_MSG));
                                                                }
                                                            %>

                                                            <s:if test="%{reqList == '-1'}">
                                                                <s:if test="%{currentConsultant.currentAction == 'editConsultant'}"> 
                                                                    <%-- <a href="<s:url value="crmBackToList.action"/>" >
                                                                         
                                                                         <img alt="Back to List"
                                                                              src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                              width="66px" 
                                                                              height="19px"
                                                                          border="0" style="vertical-align: bottom;"  ></a>--%>

                                                                    <%-- <%
                                                                     if(request.getAttribute("isSearch")!=null){
                                                                      isSearch =  session.getAttribute("isSearch").toString();
                                                                      url="../../recruitment/consultant/consultantSearch.action&isSearch="+isSearch;
                                                                    }
                                                                    
                                                                    url="../../recruitment/consultant/consultantSearch.action&isSearch="+null;
                                                                    
                                                                    
                                                                    %> --%>

                                                                    <%--      <a href="<s:url value="../../recruitment/consultant/consultantSearch.action"/>" >
                                                                         
                                                                         <img alt="Back to List"
                                                                              src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                              width="66px" 
                                                                              height="19px"
                                                                          border="0" style="vertical-align: bottom;"  ></a> --%>
                                                                    <% String cons = request.getParameter("consultId").toString();
                                                                        if (!request.getParameter("consultId").toString().equals("0") && !request.getParameter("consultId").toString().equals("-1")) {
                                                                            String consultId = request.getParameter("consultId").toString();
                                                                            String objectId = request.getParameter("requirementId").toString();
                                                                            String requirementAdminFlag = request.getParameter("requirementAdminFlag").toString();
                                                                    %>
                                                                    <a href="<s:url value="../../crm/requirement/getConsultantRequirement.action"/>?consultId=<%=consultId%>&objectId=<%=objectId%>&requirementAdminFlag=<%=requirementAdminFlag%>" >

                                                                        <img alt="Back to List"
                                                                             src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                             width="66px" 
                                                                             height="19px"
                                                                             border="0" style="vertical-align: bottom;"  ></a>
                                                                        <%} else {%>
                                                                        <s:if test="%{siteConsultants ==-1}"> 

                                                                        <a href="<s:url value="../../recruitment/consultantsFromWebsite.action"/>" >

                                                                            <img alt="Back to List"
                                                                                 src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                                 width="66px" 
                                                                                 height="19px"
                                                                                 border="0" style="vertical-align: bottom;"  ></a>
                                                                        </s:if><s:else>
                                                                            <%    if (!request.getParameter("consultId").toString().equals("-1")) {%>
                                                                        <a href="<s:url value="../../recruitment/consultant/consultantSearch.action"/>" >

                                                                            <img alt="Back to List"
                                                                                 src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                                 width="66px" 
                                                                                 height="19px"
                                                                                 border="0" style="vertical-align: bottom;"  ></a>
                                                                            <%} else {%>
                                                                        <a href="<s:url value="../../recruitment/consultantSearch.action"/>" >
                                                                            <img alt="Back to List"
                                                                                 src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                                 width="66px" 
                                                                                 height="19px"
                                                                                 border="0" style="vertical-align: bottom;"  ></a>
                                                                            <%}%>
                                                                        </s:else>
                                                                        <%}%>
                                                                    </s:if> 
                                                                </s:if>
                                                                <s:else>
                                                                <a href="<s:url value="../../crm/requirement/requirementList.action"/>" style="align:center;">
                                                                    <img alt="Back to List"
                                                                         src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                         width="66px" 
                                                                         height="19px"
                                                                         border="0" align="bottom"></a>&nbsp;&nbsp;
                                                                </s:else>
                                                                <%--  <s:else>
                                                                 <INPUT type="button" CLASS="buttonBg" value="Back to List" onClick="history.go(-1)">&nbsp;&nbsp;  
                                                                   </s:else> --%>
                                                                <s:if test="%{currentConsultant.currentAction == 'editConsultant'}">
                                                                    <s:submit align="right" id="save" value="Update" cssClass="buttonBg"/>
                                                                </s:if>
                                                                <s:else>
                                                                    <s:submit align="right" id="save" value="Add" cssClass="buttonBg"/>
                                                                </s:else>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td width="15%" class="fieldLabel"><b>Personal Email :</b></td>
                                                        <td colspan="3">
                                                            <s:textfield id="email2" autocomplete="off" name="email2" onkeyup="getConsultant();" value="%{currentConsultant.email2}" cssClass="inputTextBlueLarge" onchange="checkEmail(this);"/><FONT color="red" SIZE="0.5"><em>*</em></FONT><font class="fieldLabelLeft">(Check Availability)</font>
                                                            <s:if test="%{currentConsultant.currentAction == 'editConsultant'}"> 
                                                                <s:if test="%{currentConsultant.isReject == 1}">

                                                                    <img  onclick="javascript:getConsultantStatusDetails();" alt="Checked" src="/Hubble/includes/images/ecertification/red.png" title="Rejected" width="20px" height="20px" border="0" >
                                                                </s:if>
                                                                <s:else>
                                                                    <img onclick="javascript:getConsultantStatusDetails();" alt="Checked" src="/Hubble/includes/images/ecertification/green.png" width="20px" height="20px" border="0" >
                                                                </s:else>
                                                            </s:if>
                                                            <div class="fieldLabelLeft" id="validationMessage"></div>
                                                            <div style="display: none; position: absolute; overflow:auto;" id="menu-popup">
                                                                <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" ></table>
                                                            </div>

                                                        </td>
                                                        <td class="fieldLabel" >Available From:</td>
                                                        <td>
                                                            <s:textfield id="AvailableFrom" name="availableFrom" cssClass="inputTextBlueWithDatePicker" value="%{currentConsultant.availableFrom}"  onchange="checkDates(this);"/>
                                                            <a href="javascript:cal1.popup();">
                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                     width="20" height="20" border="0"></a>
                                                        </td>

                                                    </tr>
                                                    <tr>
                                                        <td class="fieldLabel" >First Name :</td>
                                                        <td>
                                                            <s:textfield id="FirstName" name="fiName" cssClass="inputTextBlue" value="%{currentConsultant.fiName}" onchange="fieldLengthValidator(this),changeCase(this);"/><FONT color="red" SIZE="0.5"><em>*</em></FONT>
                                                        </td>
                                                        <td class="fieldLabel" >Last Name :</td>
                                                        <td>
                                                            <s:textfield id="LastName" name="laName" cssClass="inputTextBlue" value="%{currentConsultant.laName}" onchange="fieldLengthValidator(this),changeCase(this);"/><FONT color="red" SIZE="0.5"><em>*</em></FONT>
                                                        </td>
                                                        <td class="fieldLabel" >Middle Name :</td>
                                                        <td>
                                                            <s:textfield id="MiddleName" name="miName" value="%{currentConsultant.miName}" cssClass="inputTextBlue" onchange="fieldLengthValidator(this),changeCase(this);"/>
                                                        </td>
                                                    </tr>

                                                    <tr>
                                                        <td class="fieldLabel">Gender :</td>
                                                        <td>
                                                            <s:select headerKey="1" headerValue="--Please Select--" list="{'Male','Female'}" id="gender" name="gender" value="%{currentConsultant.gender}" cssClass="inputSelect"/><FONT color="red" SIZE="0.5"><em>*</em></FONT>
                                                        </td>
                                                        <td class="fieldLabel">Job Title :</td>
                                                        <td>
                                                            <s:textfield id="titleTypeId" name="titleTypeId" value="%{currentConsultant.titleTypeId}" cssClass="inputTextBlue" onchange="fieldLengthValidator(this);"/>
                                                        </td>
                                                        <td class="fieldLabel">Office Phone :</td>
                                                        <td>
                                                            <s:textfield id="workPhoneNo" name="workPhoneNo" value="%{currentConsultant.workPhoneNo}" cssClass="inputTextBlue" onchange="return formatPhone(this);"/><FONT color="red" SIZE="0.5"><em>*</em></FONT>
                                                        </td>
                                                    </tr>

                                                    <tr>
                                                        <td class="fieldLabel">Home Phone :</td>
                                                        <td>
                                                            <s:textfield id="homePhoneNo" name="homePhoneNo" value="%{currentConsultant.homePhoneNo}" cssClass="inputTextBlue" onchange="return formatPhone(this);"/>
                                                        </td>
                                                        <td class="fieldLabel">Mobile :</td>
                                                        <td>
                                                            <s:textfield id="cellPhoneNo" name="cellPhoneNo" value="%{currentConsultant.cellPhoneNo}" cssClass="inputTextBlue" onchange="return formatPhone(this);"/>
                                                        </td>
                                                        <td class="fieldLabel">Work Authoriztion :</td>
                                                        <td>
                                                            <s:select headerKey="1" headerValue="--Please Select--" value="%{currentConsultant.workAuthorization}" list="{'US Citizen','Green Card','EAD','H1','B1','TN Permit Visa','Need H1B','Student Visa'}" 
                                                                      name="workAuthorization" id="workAuthorization" cssClass="inputSelect"/>
                                                        </td>
                                                    </tr>

                                                    <tr>
                                                        <td class="fieldLabel">Skills :</td>
                                                        <td colspan="6">
                                                            <s:textfield id="skills" name="skills" value="%{currentConsultant.skills}" cssClass="inputTextBlueDoubleExtraLarge" onchange="skillValidate(this),fieldLengthValidator(this);"/>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="fieldLabel">Description  :</td>
                                                        <td colspan="6">
                                                            <s:textfield id="description" name="description" value="%{currentConsultant.description}"  cssClass="inputTextBlueDoubleExtraLarge" onchange="fieldLengthValidator(this);"/>
                                                        </td>
                                                    </tr>

                                                    <tr>
                                                        <td class="fieldLabel">Rate/Salary :</td>
                                                        <td>
                                                            <s:textfield id="ratePerHour" name="ratePerHour" value="%{currentConsultant.ratePerHour}" cssClass="inputTextBlue" onchange="fieldLengthValidator(this);"/>
                                                        </td>
                                                        <td class="fieldLabel">Industry :</td>
                                                        <td>
                                                            <s:textfield id="industryId" name="industryId" value="%{currentConsultant.industryId}" cssClass="inputTextBlue" onchange="fieldLengthValidator(this);"/>
                                                        </td>
                                                        <td class="fieldLabel">Practice :</td> 
                                                        <td>
                                                            <s:select headerKey="1" headerValue="--Please Select--" value="%{currentConsultant.practiceId}" list="practiceList" 
                                                                      id="practiceId" name="practiceId" cssClass="inputSelect"/>
                                                        </td>
                                                        <%--<td class="fieldLabel">Worked With Miracle :</td>
                                                                                <td>
                                                                                    <s:checkbox name="wwm" id="wwm" cssClass=""/>
                                                                                </td>--%>
                                                    </tr>
                                                    <tr>
                                                        <td class="fieldLabel">State :</td>
                                                        <td>
                                                            <s:select headerKey="" headerValue="--Please Select--" value="%{currentConsultant.country}" list="statesList" 
                                                                      id="country" name="country" cssClass="inputSelect"/><FONT color="red" SIZE="0.5"><em>*</em></FONT>
                                                        </td>
                                                        <td class="fieldLabel">Reffered By :</td>
                                                        <td>
                                                            <s:textfield name="refferedBy" id="refferedBy" value="%{currentConsultant.refferedBy}" cssClass="inputTextBlue" onchange="fieldLengthValidator(this);"/>
                                                        </td>
                                                        <td class="fieldLabel">Source :</td>
                                                        <td>
                                                            <s:textfield name="source" id="source" value="%{currentConsultant.source}" cssClass="inputTextBlue" onchange="fieldLengthValidator(this);"/><FONT color="red" SIZE="0.5"><em>*</em></FONT>
                                                        </td> 
                                                        <%-- <td class="fieldLabel">Source :</td>
                                                        <td>
                                                            <s:select headerKey="1" headerValue="--Please Select--" value="%{currentConsultant.source}" list="{'Monster','Dice','CareerBuilder','TechFetch','Referral','Others'}" 
                                                                      name="workAuthorization" id="workAuthorization" cssClass="inputSelect"/>
                                                        </td>--%>
                                                    </tr>
                                                    <tr>
                                                        <td class="fieldLabel">Org :</td>

                                                        <td >

                                                            <s:select cssClass="inputSelect" label="Select Org" 
                                                                      name="org" 
                                                                      headerKey="" id="org"

                                                                      list="orgMap" value="%{currentConsultant.org}"/>


                                                        </td>
                                                        <td class="fieldLabel">Experience :</td>

                                                        <td >

                                                            <s:select  headerValue="--Select Exp--" cssClass="inputSelect" label="Select Exp" 
                                                                       name="exp" 
                                                                       headerKey="1" id="exp"
                                                                       list="expMap" value="%{currentConsultant.exp}"/><FONT color="red" SIZE="0.5"><em>*</em></FONT>


                                                        </td>
                                                        <td class="fieldLabel">Technically&nbsp;Evaluated :</td>

                                                        <td >

                                                            <s:select  headerValue="--Please Select--" cssClass="inputSelect" label="Select Exp" 
                                                                       name="techEval" 
                                                                       headerKey="1" id="techEval"

                                                                       list="{'Yes','No'}" value="%{currentConsultant.techEval}"/>


                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="fieldLabel">Available :</td>

                                                        <td >

                                                            <s:select  headerValue="--Please Select--" cssClass="inputSelect" label="-Please Select-" 
                                                                       name="available" 
                                                                       headerKey="1" id="available"

                                                                       list="{'Yes','No'}" value="%{currentConsultant.available}"/>


                                                        </td>

                                                        <td class="fieldLabel">Preferred&nbsp;State:</td>

                                                        <td >

                                                            <s:select  headerValue="--Please Select--" cssClass="inputSelect" label="-Please Select-" 
                                                                       name="preferredState" 
                                                                       headerKey="1" id="preferredState"

                                                                       list="{'Relocation','Travel'}" value="%{currentConsultant.preferredState}"/>


                                                        </td>

                                                    </tr>
                                                    <tr>
                                                        <td class="fieldLabel">Comments :</td>
                                                        <td colspan="6">
                                                            <s:textarea rows="3" cols="90" id="comments" name="comments" value="%{currentConsultant.comments}" cssClass="inputTextarea" onchange="fieldLengthValidator(this);"/>
                                                        </td>
                                                    </tr>
                                                    <s:if test="%{currentConsultant.currentAction!= 'editConsultant'}">  
                                                        <tr class="headerText">

                                                            <td width="15%" class="fieldLabel"><b>Resume :</b></td>

                                                        </tr>

                                                        <tr>
                                                            <!--<td colspan="6">
                                                                                    <fieldset>
                                                                                        <legend>Resume :</legend>
                                                                                        <table border="0" width="100%" cellpadding="5" cellspacing="0">
                                                                                            <tr>-->
                                                            <td class="fieldLabel">Resume Title :</td>
                                                            <td>
                                                                <s:textfield id="attachmentName" name="attachmentName" cssClass="inputTextBlue" value="" onchange="attachmentNameValidate();"/>
                                                            </td>
                                                            <td class="fieldLabel">Attachment path :</td>
                                                            <td colspan="2"><!--onblur="attachmentValidate(this);"-->
                                                                <s:file id="upload" name="upload" theme="simple" cssClass="inputTextBlueLarge" onchange="attachmentFileNameValidate();"/>
                                                            </td>
                                                        </tr>
                                                    </s:if>
                                                </table>
                                                <%--   <p>
                                                                            <table align="center" border="0" width="25%" cellpadding="0" cellspacing="0">
                                                                                <tr>
                                                                                    <td align="center">
                                                                                        <s:if test="%{currentConsultant.currentAction == 'editConsultant'}">
                                                                                            <s:submit name="resumeList" id="resumeList" value="Resume Details" cssClass="buttonBg" onclick="getResumeDetails1()"/>  &nbsp;
                                                                                            <s:submit name="activities" id="activities" value="Activities" cssClass="buttonBg" onclick="getActivities1()"/>
                                                                                        </s:if>
                                                                                        <s:submit name="resumeList" id="resumeList" cssStyle="display: none;" value="Resume Details" cssClass="buttonBg" onclick="getResumeDetails()"/>
                                                                                    </td>
                                                                                    <td>
                                                                                        <s:submit name="activities" id="activities" cssStyle="display: none;" value="Activities" cssClass="buttonBg" onclick="getActivities()"/>
                                                                                    </td>
                                                                                </tr>
                                                                            </table>
                                                                        </p>  --%>
                                            </s:form>
                                        </div>
                                        <script type="text/javascript">
                                       
                                            var cal1 = new CalendarTime(document.forms['addConsultantForm'].elements['AvailableFrom']);
                                            cal1.year_scroll = true;
                                            cal1.time_comp = false;
                                            var countries=new ddtabcontent("addConsultantTabs")
                                            countries.setpersist(false)
                                            countries.setselectedClassTarget("link") //"link" or "linkparent"
                                            countries.init()

                                        </script>
                                    </div>


                                    <%--  </sx:tabbedpanel> --%>
                                    <s:if test="%{currentConsultant.currentAction == 'editConsultant'}">  

                                        <ul id="accountTabs1" class="shadetabs" >

                                            <li ><a href="#" rel="skillTab">Skills </a></li>
                                            <li ><a href="#" rel="resumeAttachmentListTab">Resumes</a></li>
                                            <li ><a href="#" rel="resumeSubmittTab">Resume Submission</a></li>
                                            <li ><a href="#" rel="techReviewTab">Tech Attachments</a></li>
                                            <li><a href="#" rel="AccountActivityList" class="selected" >Activities</a></li>
                                            <%-- <li ><a href="#" rel="reviewsTab">Tech Reviews</a></li> --%>

                                        </ul>

                                        <!--//START TABBED PANNEL :Two -->
                                        <%--  <sx:tabbedpanel id="accountsDetailsPannels" cssStyle="width: 840px; height: 275px;padding-left:10px;padding-top:5px;padding-bottom:5px;" doLayout="true"  useSelectedTabCookie="true">  --%> 
                                        <div  style="border:1px solid gray; width:800px;height: 275px; margin-bottom: 1em;overflow:auto; ">  

                                            <div id="AccountActivityList" class="tabcontent" >  


                                                <%

                                                    reqList = request.getAttribute("reqList").toString();
                                                    /* String Variable for storing current position of records in dbgrid*/
                                                    strTmp = request.getParameter("txtCurr");

                                                    intCurr = 1;

                                                    if (strTmp != null) {
                                                        intCurr = Integer.parseInt(strTmp);
                                                    }

                                                    try {

                                                        /* Getting DataSource using Service Locator */
                                                        connection = ConnectionProvider.getInstance().getConnection();

                                                        //New
                                                        String reqList = request.getAttribute("reqList").toString();
                                                        // out.println("Consultant Details ---->"+reqList);
                                                        // request.setAttribute("reqList",reqList);


                                                        /*Setting reqList Id into session for diffrencting the consultant search and requrement list */
                                                        session.setAttribute("skillUniqueID", reqList);


                                                        /* Sql query for retrieving resultset from DataBase */
                                                        String queryString = null;
                                                        //String empLeaveAction = "/"+ApplicationConstants.CONTEXT_PATH+"/employee/leaveRequest.action";

                                                        //consultantId = session.getAttribute("consultantId").toString();
                                                        //out.println(consultantId);

                                                        //queryString ="SELECT Id,ConsultantId,ActivityType,Status,Availability,Comments,InterviewDate,ReportingDate,CreatedBy,CreatedDate,AssignedTo FROM tblRecActivity";
                                                        //queryString = queryString + "ORDER BY CreatedDate DESC";
                                                        if (request.getAttribute("empId") != null) {
                                                            consultantId = request.getAttribute("empId").toString();
                                                        } else {
                                                            consultantId = request.getSession(false).getAttribute("consultantId").toString();
                                                        }

                                                        queryString = session.getAttribute(ApplicationConstants.QUERY_STRING).toString();
                                                        queryString = "SELECT Id,ConsultantId,ActivityType,Status,Availability,Comments,InterviewDate,ReportingDate,CreatedBy,CreatedDate,AssignedTo FROM tblRecActivity WHERE ConsultantId='" + consultantId + "' ORDER BY CreatedDate DESC ";
                                                        String activityAction = "../consultant/activity.action";
                                                        if (consultantId != null) {
                                                            activityAction = activityAction + "?consultantId=" + consultantId + "&reqList=" + reqList+ "&consultId=" + consultId + "&requirementId=" + requirementId + "&requirementAdminFlag=" + requirementAdminFlag;
                                                        }
String editAction="activity.action?id={Id}&consultantId={ConsultantId}&consultId="+consultId+"&requirementId="+requirementId+"&requirementAdminFlag="+requirementAdminFlag;	
                                                        // out.println(queryString);
%>

                                                <form method="post" id="frmActivityDBGrid" name="frmActivityDBGrid" action="">
                                                    <table cellpadding="0" cellspacing="0" width="100%">
                                                        <tr>
                                                            <td class="headerText"> <a href="<%=activityAction%>" style="align:left;">
                                                                    <img alt="Add Activity"
                                                                         src="<s:url value="/includes/images/add.gif"/>" 
                                                                         width="33px" 
                                                                         height="19px"
                                                                         border="0" align="left"></a>&nbsp;&nbsp;

                                                            </td>
                                                        </tr>     
                                                        <tr>
                                                            <td>
                                                                <!-- DataGrid for list all activities -->
                                                                <grd:dbgrid id="tblRecActivity" name="tblRecActivity" width="100" pageSize="10" 
                                                                currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable" >


                                                                    <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                                   imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                                   scriptFunction="doActivityNavigate"/>

                                                                    <grd:imagecolumn  headerText="Edit" width="5"  HAlign="center" 
                                                                                      imageSrc="../../includes/images/DBGrid/Edit.gif" 
                                                                                      linkUrl="<%=editAction%>" 
                                                                                      imageBorder="0" imageWidth="16" imageHeight="16" 
                                                                                      alterText="Click to edit" /> 
                                                                    <grd:datecolumn dataField="CreatedDate" headerText="DateCreated" dataFormat="MM-dd-yyyy" width="13"/>
                                                                    <grd:textcolumn dataField="ActivityType"  headerText="ActivityType"   width="15"/>                                                                      
                                                                    <grd:textcolumn dataField="Comments"      headerText="Comments" width="29"/>

                                                                    <grd:textcolumn dataField="AssignedTo"   headerText="AssignedTo"  width="15"/>  
                                                                    <grd:textcolumn dataField="Status"       headerText="Status"     width="15"/>     
                                                                    <grd:datecolumn dataField="InterviewDate" headerText="InterviewDate" dataFormat="MM-dd-yyyy" width="20"/>

                                                                </grd:dbgrid>

                                                                <!-- these components are DBGrid Specific  -->
                                                                <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
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

                                            <div id="skillTab" class="tabcontent"  >
                                                <form name="frmDBSkillGrid" action="" method="post">
                                                    <%

                                                        String reqList = request.getAttribute("reqList").toString();
                                                        //  out.println("Consultant Details ---->"+reqList);
                                                        // request.setAttribute("reqList",reqList);


                                                        /*Setting reqList Id into session for diffrencting the consultant search and requrement list */
                                                        session.setAttribute("skillUniqueID", reqList);
                                                        // out.println("request.consultent="+request.getParameter("consultantId")+"----page.consultent="+consultantId+"----request.empId="+request.getParameter("empId"));


                                                        // out.println("request.consultent="+request.getParameter("consultantId")+"----page.consultent="+consultantId+"----request.empId="+request.getParameter("empId"));
                                                        if (request.getParameter("empId") != null) {

                                                            custId = request.getParameter("empId");
                                                            reqList = request.getAttribute("reqList").toString();
                                                            // out.println("reqList from getter----"+request.getAttribute("reqList"));

                                                            request.getSession().setAttribute("consultantId", custId);
                                                            //custId= request.getSession(false).getAttribute("consultantId").toString();
                                                        } else {
                                                            custId = request.getSession(false).getAttribute("consultantId").toString();
                                                        }


                                                    %>   
                                                    <%
                                                        int intCurr = 1;
                                                        intSortOrd = 0;
                                                        String strTmp = null;
                                                        strSQL = null;
                                                        String strSortCol = null;
                                                        String strSortOrd = "ASC";
                                                        blnSortAsc = true;
                                                        strSQL = "select * from tblRecSkills where empId=" + Integer.parseInt(custId);
                                                        //out.print(strSQL);
                                                        Connection objCnn = null;
                                                        Class objDrvCls = null;
                                                        objDrvCls = Class.forName("com.mysql.jdbc.Driver");
                                                        objCnn = ConnectionProvider.getInstance().getConnection();
                                                        if (objDrvCls != null) {
                                                            objDrvCls = null;
                                                        }
                                                        strTmp = request.getParameter("txtSkillCurr");

                                                        try {
                                                            if (strTmp != null) {
                                                                intCurr = Integer.parseInt(strTmp);
                                                            }

                                                        } catch (NumberFormatException NFEx) {
                                                        }
                                                        strSortCol = request.getParameter("txtSkillSortCol");

                                                        strSortOrd = request.getParameter("txtSkillSortAsc");

                                                        if (strSortCol == null) {
                                                            strSortCol = "eno";
                                                        }
                                                        if (strSortOrd == null) {
                                                            strSortOrd = "ASC";
                                                        }
                                                        blnSortAsc = (strSortOrd.equals("ASC"));


                                                        String consultantQuery = "../viewConsultantSkills.action?consultantId=" + custId + "&reqList=" + reqList + "&consultId=" + consultId + "&requirementId=" + requirementId + "&requirementAdminFlag=" + requirementAdminFlag;
                                                        String editAction = "../getConsultantSkills.action?id={id}&consultantId={empId}&consultId=" + consultId + "&requirementId=" + requirementId + "&requirementAdminFlag=" + requirementAdminFlag;
                                                    %>
                                                    <s:hidden name="consultantId" value="%{empId}"/>
                                                    <table cellpadding="2" cellspacing="0" border="0" width="100%">                                               

                                                        <tr>
                                                            <td class="headerText"> <a href="<%=consultantQuery%>" style="align:left;">
                                                                    <img alt="Add Activity"
                                                                         src="<s:url value="/includes/images/add.gif"/>" 
                                                                         width="33px" 
                                                                         height="19px"
                                                                         border="0" align="left"></a>&nbsp;&nbsp;

                                                            </td>
                                                        </tr>


                                                        <tr>
                                                            <td align="center">

                                                                <grd:dbgrid  id="tblSkills" 
                                                                             name="tblSkills" 
                                                                             width="100" 
                                                                             pageSize="10" 
                                                                currentPage="<%=intCurr%>"
                                                                             border="0" 
                                                                             cellSpacing="1" 
                                                                             cellPadding="2" 
                                                                dataMember="<%=strSQL%>" 
                                                                dataSource="<%=objCnn%>"
                                                                             cssClass="gridTable">
                                                                    <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                                   imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                                   scriptFunction="getConsultSkill"/>
                                                                    <grd:gridsorter sortColumn="<%=strSortCol%>" 
                                                                    sortAscending="<%=blnSortAsc%>" />

                                                                    <grd:imagecolumn  headerText="Edit" 
                                                                                      width="3"  HAlign="center" 
                                                                                      imageSrc="../../includes/images/DBGrid/newEdit_17x18.png" 
                                                                    linkUrl="<%=editAction%>" 
                                                                                      imageBorder="0"
                                                                                      imageWidth="16"
                                                                                      imageHeight="16" 
                                                                                      alterText="Click to edit" />
                                                                    <grd:textcolumn dataField="YearsOfExperience" 
                                                                                    headerText="Years Of Experience"  
                                                                                    HAlign="center"
                                                                                    width="15"   />
                                                                    <grd:textcolumn dataField="SkillSet" headerText="SkillSet" 
                                                                                    width="15"    HAlign="center"/>
                                                                    <grd:textcolumn dataField="ProjectInfo" headerText="ProjectInfo" 
                                                                                    width="15"  HAlign="center"/>
                                                                    <%--                
                                                                             <grd:imagecolumn  headerText="Delete" 
                                                                                               width="5" 
                                                                                               HAlign="center" 
                                                                                               imageSrc="../includes/images/DBGrid/Delete.png" 
                                                                                               linkUrl="deleteConsultantSkills.action?id={Id}"
                                                                                               imageBorder="0" 
                                                                                               imageWidth="51"
                                                                                               imageHeight="20"
                                                                                               alterText="Click to edit" />                
                                                                    --%>
                                                                </grd:dbgrid>
                                                                <input TYPE="hidden" NAME="txtSkillCurr" VALUE="<%=intCurr%>">
                                                                <input TYPE="hidden" NAME="txtSkillSortCol" VALUE="<%=strSortCol%>">
                                                                <input TYPE="hidden" NAME="txtSkillSortAsc" VALUE="<%=strSortOrd%>">
                                                                <input TYPE="hidden" NAME="txtAttachCurr" VALUE="">
                                                                <input TYPE="hidden" NAME="txtSubmittCurr" VALUE="">
                                                                <input TYPE="hidden" NAME="txtTechCurr" VALUE="">
                                                                <input TYPE="hidden" NAME="submitFrom" VALUE="dbGrid">

                                                                <%
                                                                    try {
                                                                        if (objCnn != null) {
                                                                            objCnn.close();
                                                                        }
                                                                    } catch (SQLException SQLExIgnore) {
                                                                    }
                                                                    if (objCnn != null) {
                                                                        objCnn.close();
                                                                    }
                                                                    objCnn = null;
                                                                %>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </form>
                                            </div>

                                            <div id="resumeAttachmentListTab" class="tabcontent"  >
                                                <form name="frmDBAttachGrid" action="" method="post">
                                                    <%
                                                        intCurr = 1;
                                                        intSortOrd = 0;
                                                        strTmp = null;
                                                        strSQL = null;
                                                        strSortCol = null;
                                                        strSortOrd = "ASC";
                                                        blnSortAsc = true;
                                                        strSQL = "select * from tblRecAttachments where ObjectType='A' and ObjectId=" + Integer.parseInt(custId);
                                                        objCnn = null;

                                                        objDrvCls = null;

                                                        objDrvCls = Class.forName("com.mysql.jdbc.Driver");
                                                        objCnn = ConnectionProvider.getInstance().getConnection();
                                                        if (objDrvCls != null) {
                                                            objDrvCls = null;
                                                        }
                                                        strTmp = request.getParameter("txtAttachCurr");

                                                        try {
                                                            if (strTmp != null) {
                                                                intCurr = Integer.parseInt(strTmp);
                                                            }

                                                        } catch (NumberFormatException NFEx) {
                                                        }
                                                        strSortCol = request.getParameter("txtAttachSortCol");

                                                        strSortOrd = request.getParameter("txtAttachSortAsc");

                                                        if (strSortCol == null) {
                                                            strSortCol = "eno";
                                                        }
                                                        if (strSortOrd == null) {
                                                            strSortOrd = "ASC";
                                                        }
                                                        blnSortAsc = (strSortOrd.equals("ASC"));
                                                        // out.println(strSQL);
                                                        String resumeAction = "../consultantResume.action?consultantId=" + custId + "&reqList=" + reqList+ "&consultId=" + consultId + "&requirementId=" + requirementId + "&requirementAdminFlag=" + requirementAdminFlag;

                                                    %>
                                                    <table cellpadding="2" cellspacing="0" border="0" width="100%">  

                                                        <tr>
                                                            <%-- <td class="headerText"> <a href="../ConsultantResume.jsp?empId=<%=custId%>&reqList=<%=reqList%>" style="align:left;"> --%>
                                                            <td class="headerText"> <a href="<%=resumeAction%>" style="align:left;">
                                                                    <img alt="Add Activity"
                                                                         src="<s:url value="/includes/images/add.gif"/>" 
                                                                         width="33px" 
                                                                         height="19px"
                                                                         border="0" align="left"></a>&nbsp;&nbsp;

                                                            </td>
                                                        </tr> 
                                                        <tr>
                                                            <td align="center">

                                                                <grd:dbgrid  id="tblAttach" 
                                                                             name="tblAttach" 
                                                                             width="100"
                                                                             pageSize="10" 
                                                                currentPage="<%=intCurr%>"
                                                                             border="0" 
                                                                             cellSpacing="1" 
                                                                             cellPadding="2" 
                                                                dataMember="<%=strSQL%>" 
                                                                dataSource="<%=objCnn%>" 
                                                                             cssClass="gridTable">
                                                                    <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif"
                                                                                   imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                                   imgNext="../../includes/images/DBGrid/Next.gif"
                                                                                   imgLast="../../includes/images/DBGrid/Last.gif"  
                                                                                   scriptFunction="getResumeAttach" />
                                                                    <grd:textcolumn dataField="AttachmentName"
                                                                                    headerText="Attachment File Name" 
                                                                                    width="10" 
                                                                                    HAlign="center"/>
                                                                    <grd:textcolumn dataField="AttachmentLocation"
                                                                                    headerText="Attachment File Location" 
                                                                                    width="18" 
                                                                                    HAlign="center"/>                
                                                                    <grd:datecolumn dataField="DateUploaded"
                                                                                    headerText="Date Of Attachment"  
                                                                                    dataFormat="MM/dd/yyyy hh:mm:ss" 
                                                                                    width="8"  
                                                                                    HAlign="center"/>
                                                                    <grd:imagecolumn  headerText="DownLoad" 
                                                                                      width="4"  
                                                                                      HAlign="center" 
                                                                                      imageSrc="../../includes/images/download_11x10.jpg" 
                                                                                      linkUrl="../getAttachment.action?id={Id}" 
                                                                                      imageBorder="0" 
                                                                                      imageWidth="11" 
                                                                                      imageHeight="10" 
                                                                                      alterText="download" />   
                                                                    <%--                   
                                                                             <grd:imagecolumn  headerText="Delete" 
                                                                                               width="3" 
                                                                                               HAlign="center" 
                                                                                               imageSrc="../includes/images/DBGrid/Delete.png"
                                                                                               linkUrl="deleteConsultantAttachment.action?id={Id}" 
                                                                                               imageBorder="0" 
                                                                                               imageWidth="51" 
                                                                                               imageHeight="20" 
                                                                                               alterText="Click to edit" />
                                                                    --%>
                                                                </grd:dbgrid>
                                                                <input TYPE="hidden" NAME="txtAttachCurr" VALUE="<%=intCurr%>">
                                                                <input TYPE="hidden" NAME="txtAttachSortCol" VALUE="<%=strSortCol%>">
                                                                <input TYPE="hidden" NAME="txtAttachSortAsc" VALUE="<%=strSortOrd%>">
                                                                <input TYPE="hidden" NAME="txtSkillCurr" VALUE="">
                                                                <input TYPE="hidden" NAME="txtSubmittCurr" VALUE="">
                                                                <input TYPE="hidden" NAME="txtTechCurr" VALUE="">
                                                                <input TYPE="hidden" NAME="submitFrom" VALUE="dbGrid"> 
                                                                <!-- these components are DBGrid Specific  -->
                                                                <%-- <input TYPE="hidden" NAME="txtReqCurr" VALUE="<%=intCurr%>">
                                                                 <input TYPE="hidden" NAME="txtReqSortCol" VALUE="<%=strSortCol%>">
                                                                 <input TYPE="hidden" NAME="txtReqSortAsc" VALUE="<%=strSortOrd%>">
                                                                 <input type="hidden" name="submitFrom" value="dbGrid"> --%>

                                                                <%
                                                                    try {
                                                                        if (objCnn != null) {
                                                                            objCnn.close();
                                                                        }
                                                                    } catch (SQLException SQLExIgnore) {
                                                                    }
                                                                    if (objCnn != null) {
                                                                        objCnn.close();
                                                                    }
                                                                    objCnn = null;
                                                                %>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </form>
                                            </div>

                                            <div id="resumeSubmittTab" class="tabcontent"  >
                                                <form name="frmDBSubmittGrid" action="" method="post">
                                                    <%
                                                        intCurr = 1;
                                                        intSortOrd = 0;
                                                        strTmp = null;
                                                        strSQL = null;
                                                        strSortCol = null;
                                                        strSortOrd = "ASC";
                                                        blnSortAsc = true;
                                                        strSQL = "select * from tblRecAttachments where ObjectType='S' and ObjectId=" + Integer.parseInt(custId);
                                                        objCnn = null;
                                                        objDrvCls = null;
                                                        objDrvCls = Class.forName("com.mysql.jdbc.Driver");
                                                        objCnn = ConnectionProvider.getInstance().getConnection();
                                                        if (objDrvCls != null) {
                                                            objDrvCls = null;
                                                        }
                                                        strTmp = request.getParameter("txtSubmittCurr");

                                                        try {
                                                            if (strTmp != null) {
                                                                intCurr = Integer.parseInt(strTmp);
                                                            }

                                                        } catch (NumberFormatException NFEx) {
                                                        }
                                                        strSortCol = request.getParameter("txtSubmittSortCol");

                                                        strSortOrd = request.getParameter("txtSubmittSortAsc");

                                                        if (strSortCol == null) {
                                                            strSortCol = "eno";
                                                        }
                                                        if (strSortOrd == null) {
                                                            strSortOrd = "ASC";
                                                        }
                                                        blnSortAsc = (strSortOrd.equals("ASC"));
                                                        //  out.print(strSQL);
                                                        String resumeSubmitAction = "../consultantResumeSubmit.action?consultantId=" + custId + "&reqList=" + reqList+ "&consultId=" + consultId + "&requirementId=" + requirementId + "&requirementAdminFlag=" + requirementAdminFlag;
                                                    %>
                                                    <table cellpadding="2" cellspacing="0" border="0" width="100%">        

                                                        <tr>
                                                            <%-- <td class="headerText"> <a href="../ConsultantSubmittals.jsp?empId=<%=custId%>&reqList=<%=reqList%>" style="align=left;"> --%>
                                                            <td class="headerText"> <a href="<%=resumeSubmitAction%>" style="align:left;">

                                                                    <img alt="Add Activity"
                                                                         src="<s:url value="/includes/images/add.gif"/>" 
                                                                         width="33px" 
                                                                         height="19px"
                                                                         border="0" align="left"></a>&nbsp;&nbsp;

                                                            </td>
                                                        </tr>   
                                                        <tr>
                                                            <td align="center">

                                                                <grd:dbgrid  id="tblSubmitt"
                                                                             name="tblSubmitt" 
                                                                             width="100"
                                                                             pageSize="10" 
                                                                currentPage="<%=intCurr%>" 
                                                                             border="0"
                                                                             cellSpacing="1" 
                                                                             cellPadding="2" 
                                                                dataMember="<%=strSQL%>" 
                                                                dataSource="<%=objCnn%>" 
                                                                             cssClass="gridTable">
                                                                    <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif"
                                                                                   imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                                   imgNext="../../includes/images/DBGrid/Next.gif" 
                                                                                   imgLast="../../includes/images/DBGrid/Last.gif"  
                                                                                   scriptFunction="getResumeSubmitt"/>

                                                                    <grd:textcolumn dataField="AttachmentName" 
                                                                                    headerText="Submitt File Name" 
                                                                                    width="10"
                                                                                    HAlign="center"/>
                                                                    <grd:textcolumn dataField="AttachmentLocation" 
                                                                                    headerText="Submitt File Location" 
                                                                                    width="20" 
                                                                                    HAlign="center"/>                
                                                                    <grd:datecolumn dataField="DateUploaded" 
                                                                                    headerText="Date Of Submitt"  
                                                                                    dataFormat="MM/dd/yyyy hh:mm:ss" 
                                                                                    width="10"  
                                                                                    HAlign="center"/>
                                                                    <grd:imagecolumn  headerText="DownLoad" 
                                                                                      width="4"
                                                                                      HAlign="center" 
                                                                                      imageSrc="../../includes/images/download_11x10.jpg"
                                                                                      linkUrl="../getAttachment.action?id={Id}" 
                                                                                      imageBorder="0" 
                                                                                      imageWidth="11"
                                                                                      imageHeight="10" 
                                                                                      alterText="download" />   
                                                                    <%--
                                                                             <grd:imagecolumn  headerText="Delete" 
                                                                                               width="4" 
                                                                                               HAlign="center"  
                                                                                               imageSrc="../includes/images/DBGrid/Delete.png"
                                                                                               linkUrl="deleteConsultantAttachment.action?Id={Id}" 
                                                                                               imageBorder="0"
                                                                                               imageWidth="51" 
                                                                                               imageHeight="20" 
                                                                                               alterText="Click to edit" />
                                                                    --%>                   
                                                                </grd:dbgrid>
                                                                <input TYPE="hidden" NAME="txtSubmittCurr" VALUE="<%=intCurr%>">
                                                                <input TYPE="hidden" NAME="txtSubmittSortCol" VALUE="<%=strSortCol%>">
                                                                <input TYPE="hidden" NAME="txtSubmittSortAsc" VALUE="<%=strSortOrd%>">
                                                                <input TYPE="hidden" NAME="txtAttachCurr" VALUE="">
                                                                <input TYPE="hidden" NAME="txtSkillCurr" VALUE="">
                                                                <input TYPE="hidden" NAME="txtTechCurr" VALUE="">
                                                                <input TYPE="hidden" NAME="submitFrom" VALUE="dbGrid"> 
                                                                <%
                                                                    try {
                                                                        if (objCnn != null) {
                                                                            objCnn.close();
                                                                        }
                                                                    } catch (SQLException SQLExIgnore) {
                                                                    }
                                                                    if (objCnn != null) {
                                                                        objCnn.close();
                                                                    }
                                                                    objCnn = null;
                                                                %>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </form>
                                            </div>
                                            <%--  </sx:div > --%>
                                            <!--//End Resumes SubmitTab : -->

                                            <!--//Start Tech Review Tab : -->
                                            <%-- <sx:div id="techReviewTab"  label=" Tech Reviews "  > --%>
                                            <div id="techReviewTab" class="tabcontent"  >
                                                <form name="frmDBTechGrid" action="" method="post">
                                                    <%
                                                        intCurr = 1;
                                                        intSortOrd = 0;
                                                        strTmp = null;
                                                        strSQL = null;
                                                        strSortCol = null;
                                                        strSortOrd = "ASC";
                                                        blnSortAsc = true;
                                                        strSQL = "select * from tblRecAttachments where ObjectType='T' and ObjectId=" + Integer.parseInt(custId);
                                                        objCnn = null;
                                                        objDrvCls = null;
                                                        objDrvCls = Class.forName("com.mysql.jdbc.Driver");
                                                        objCnn = ConnectionProvider.getInstance().getConnection();
                                                        if (objDrvCls != null) {
                                                            objDrvCls = null;
                                                        }
                                                        strTmp = request.getParameter("txtTechCurr");
                                                        //out.print(strSQL);
                                                        try {
                                                            if (strTmp != null) {
                                                                intCurr = Integer.parseInt(strTmp);
                                                            }

                                                        } catch (NumberFormatException NFEx) {
                                                        }
                                                        strSortCol = request.getParameter("txtTechSortCol");

                                                        strSortOrd = request.getParameter("txtTechSortAsc");

                                                        if (strSortCol == null) {
                                                            strSortCol = "eno";
                                                        }
                                                        if (strSortOrd == null) {
                                                            strSortOrd = "ASC";
                                                        }
                                                        blnSortAsc = (strSortOrd.equals("ASC"));
                                                        String techReviewAction = "../techReview.action?consultantId=" + custId + "&reqList=" + reqList+ "&consultId=" + consultId + "&requirementId=" + requirementId + "&requirementAdminFlag=" + requirementAdminFlag;
                                                    %>
                                                    <table cellpadding="2" cellspacing="0" border="0" width="100%">             
                                                        <tr>
                                                            <%--    <td class="headerText"> <a href="../ConsultantsTechReviews.jsp?empId=<%=custId%>&reqList=<%=reqList%>" style="align=left;"> --%>
                                                            <td class="headerText"> <a href="<%=techReviewAction%>" style="align:left;">
                                                                    <img alt="Add Activity"
                                                                         src="<s:url value="/includes/images/add.gif"/>" 
                                                                         width="33px" 
                                                                         height="19px"
                                                                         border="0" align="left"></a>&nbsp;&nbsp;

                                                            </td>
                                                        </tr>     
                                                        <tr>
                                                            <td align="center">

                                                                <grd:dbgrid  id="tblTechreview"
                                                                             name="tblTechreview"  
                                                                             width="100"
                                                                             pageSize="10" 
                                                                currentPage="<%=intCurr%>"
                                                                             border="0"
                                                                             cellSpacing="1" 
                                                                             cellPadding="2" 
                                                                dataMember="<%=strSQL%>" 
                                                                dataSource="<%=objCnn%>"
                                                                             cssClass="gridTable">
                                                                    <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" 
                                                                                   imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                                   imgNext="../../includes/images/DBGrid/Next.gif"
                                                                                   imgLast="../../includes/images/DBGrid/Last.gif" 
                                                                                   scriptFunction="getTechReview"/>
                                                                    <grd:textcolumn dataField="AttachmentName"
                                                                                    headerText="TechReview File Name" 
                                                                                    width="10" 
                                                                                    HAlign="center"/>
                                                                    <grd:textcolumn dataField="AttachmentLocation"
                                                                                    headerText="TechReview File Location" 
                                                                                    width="18" 
                                                                                    HAlign="center"/>                
                                                                    <grd:datecolumn dataField="DateUploaded"
                                                                                    headerText="Date Of Submitt" 
                                                                                    dataFormat="MM/dd/yyyy hh:mm:ss" 
                                                                                    width="10"  
                                                                                    HAlign="center"/>
                                                                    <grd:imagecolumn  headerText="DownLoad"
                                                                                      width="4" 
                                                                                      HAlign="center" 
                                                                                      imageSrc="../../includes/images/download_11x10.jpg"
                                                                                      linkUrl="../getAttachment.action?id={Id}" 
                                                                                      imageBorder="0"
                                                                                      imageWidth="11" 
                                                                                      imageHeight="10" 
                                                                                      alterText="download" />    

                                                                    <%--
                                                                             <grd:imagecolumn  headerText="Delete"
                                                                                               width="3" 
                                                                                               HAlign="center" 
                                                                                               imageSrc="../includes/images/DBGrid/Delete.png"
                                                                                               linkUrl="deleteConsultantAttachment.action?id={Id}" 
                                                                                               imageBorder="0"
                                                                                               imageWidth="51"
                                                                                               imageHeight="20"
                                                                                               alterText="Click to edit" />
                                                                    --%>
                                                                </grd:dbgrid>
                                                                <input TYPE="hidden" NAME="txtTechCurr" VALUE="<%=intCurr%>">
                                                                <input TYPE="hidden" NAME="txtTechSortCol" VALUE="<%=strSortCol%>">
                                                                <input TYPE="hidden" NAME="txtTechSortAsc" VALUE="<%=strSortOrd%>">

                                                                <input TYPE="hidden" NAME="txtAttachCurr" VALUE="">
                                                                <input TYPE="hidden" NAME="txtSubmittCurr" VALUE="">
                                                                <input TYPE="hidden" NAME="txtSkillCurr" VALUE="">
                                                                <input TYPE="hidden" NAME="submitFrom" VALUE="dbGrid">
                                                                <%
                                                                    try {
                                                                        if (objCnn != null) {
                                                                            objCnn.close();
                                                                        }
                                                                    } catch (SQLException SQLExIgnore) {
                                                                    }
                                                                    if (objCnn != null) {
                                                                        objCnn.close();
                                                                    }
                                                                    objCnn = null;
                                                                %>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </form>
                                            </div>
                                            <!-- reviews tab start -->

                                            <!-- end -->

                                        </div> 
                                        <script type="text/javascript">

                                            var countries=new ddtabcontent("accountTabs1")
                                            countries.setpersist(true)
                                            countries.setselectedClassTarget("link") //"link" or "linkparent"
                                            countries.init()

                                        </script>

                                    </s:if>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <!--//START FOOTER : Record for Footer Background and Content-->
            <tr class="footerBg">
                <td align="center"><s:include value="/includes/template/Footer.jsp"/></td>
            </tr>
        </table>
            <script type="text/javascript">
		$(window).load(function(){
	init();
		});
		</script>
    </body>
</html>
