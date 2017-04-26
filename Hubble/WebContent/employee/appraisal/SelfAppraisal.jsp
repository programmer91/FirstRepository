<!-- 
 *******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * $Date: 2009-03-31 10:48:49 $
 *
 * $Author: hkondala $
 *
 * $Name:  $
 *
 * $Revision: 1.2 $
 * 
 * $Source: /Hubble/Hubble_CVS/Hubble/web/employee/appraisal/SelfAppraisal.jsp,v $
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
-->
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="com.freeware.gridtag.*" %> 
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/tml; charset=UTF-8">
        <title>Hubble Organization Portal :: SelfAppraisal</title>
         <sx:head cache="true"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tooltip.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tooltip.js"/>"></script>           
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        
        <!-- issues Related JavaScript  -->
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EnableEnter.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <s:include value="/includes/template/headerScript.html"/>
        
    </head>
    <body  class="bodyGeneral" oncontextmenu="return false;">
        
        
        <!--//START MAIN TABLE : Table for template Structure-->
        <table class="templateTable1000x580" align="center" cellpadding="0" cellspacing="0">
            
            <!--//START HEADER : Record for Header Background and Mirage Logo-->
            <tr class="headerBg">
                <td valign="top">
                    <s:include value="/includes/template/Header.jsp"/>
                </td>
            </tr>
            
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
                                
                                <sx:tabbedpanel  id="empSelfAppraisal" cssStyle="width: 845px;padding:10px 5px 5px 5px" doLayout="false" useSelectedTabCookie="true">
                                    <sx:div id="general" label="General Details">
                                        <s:form action="" theme="simple" name="general">
                                            <table border="0" width="100%" cellpadding="5" cellspacing="0" align="center">
                                                <tr class="headerText" >
                                                    <td class="headerText" colspan="3">
                                                        
                                                        <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                    </td>
                                                    
                                                </tr>
                                                
                                                <tr style="font-family: arial,verdana; font-size:12px;" >
                                                    <td class="fieldLabel">Name :</td>
                                                    <td align="left" colspan="2">
                                                        <s:property value="%{currentApparaisal.empName}"/> 
                                                    </td> 
                                                </tr>
                                                <tr style="font-family: arial,verdana; font-size:12px;">
                                                    <td class="fieldLabel" >Department :</td>
                                                    <td><s:textfield name="department" cssClass="inputTextBlueLarge" value="%{currentApparaisal.department} " readonly="true" /></td>
                                                </tr>
                                                <tr style="font-family: arial,verdana; font-size:12px;">
                                                    
                                                    <td  class="fieldLabel" >Designation :</td>
                                                    <td colspan="2"><s:textfield name="designation" cssClass="inputTextBlueLarge" value="%{currentApparaisal.designation}" readonly="true"/></td>
                                                    
                                                </tr>
                                                
                                                <tr style="font-family: arial,verdana; font-size:12px;">
                                                    <td class="fieldLabel">Date of join :</td>
                                                    <td><s:textfield name="dateOfJoin" cssClass="inputTextBlueLarge" value="%{currentApparaisal.dateOfJoin}" readonly="true"/></td>
                                                </tr>
                                                <tr style="font-family: arial,verdana; font-size:12px;">
                                                    <td class="fieldLabel">Appraisal Date :</td>
                                                    <td><s:textfield name="appraisalDate" cssClass="inputTextBlueLarge" value="%{currentApparaisal.appraisalDate}" readonly="true"/></td>
                                                </tr>
                                                <tr style="font-family: arial,verdana; font-size:12px;">
                                                    <td class="fieldLabel">Team Name :</td>
                                                    <td><s:textfield name="teamName" cssClass="inputTextBlueLarge" value="%{currentApparaisal.teamName}" readonly="true"/></td>
                                                </tr>
                                                <tr style="font-family: arial,verdana; font-size:12px;">
                                                    <td class="fieldLabel">Tech Manager Name :</td>
                                                    <td><s:textfield name="techMangName" cssClass="inputTextBlueLarge" value="%{currentApparaisal.techMangName}" readonly="true"/></td>
                                                </tr>
                                                
                                            </table>
                                        </s:form>
                                    </sx:div>
                                    
                                    <!-- Self Assessment Div  -->                                    
                                    
                                    <sx:div id="performance Element" label="Self Assessment" cssStyle="overflow:auto;">
                                        
                                        <s:form name="selfAssess" id="selfAssess" action="selfAssessment" theme="simple" method="post">
                                            
                                            <s:hidden id="formSubmit" name="formSubmit" value=""/>
                                            <s:hidden name="currentEmployeeId" id="currentEmployeeId"></s:hidden>
                                            <s:hidden name="empId" id="empId"></s:hidden>
                                            <table width="98%"  cellspacing="2" border="0">
                                                <tr align="left">
                                                    <td class="headerText">
                                                        Self Assessment And performance Element:
                                                    </td>
                                                    <td class="headerText" align="right"><s:property value="#request.resultMessage"/><s:a href="#" onclick="history.go(-1)"><input type="button" class="buttonBg" value="Back"></s:a>
                                                    <s:submit cssClass="buttonBg" value="save" /></td>
                                                </tr>
                                                
                                                <tr>
                                                    <td colspan="2" class="fieldLabelLeft">
                                                        <fieldset>
                                                            <legend> Attendance  & Punctuality  <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/help.gif" onmouseover="fixedtooltip('Occassionally apply leaves and always maintains the time schedules.',this,event, 200,-20,40)" onmouseout="delayhidetip()"></legend>
                                                            <table>
                                                                <tr>
                                                                    <s:if test="%{currentAppraisalAction == 'AddAppraisal'}">
                                                                        <td colspan="2" ><s:textarea cssClass="inputTextArea" value="%{currentApparaisal.pAttendence}" id="pAttendence" name="pAttendence" cols="110" rows="4"/></td>
                                                                    </s:if>
                                                                    <s:elseif test="%{empId != #session.empId && (#session.isUserManager == 1 || #session.isUserTeamLead==1)}">
                                                                        <td colspan="2" ><s:textarea cssClass="inputTextArea" value="%{currentApparaisal.pAttendence}" id="pAttendence" name="pAttendence" cols="110" rows="4" readonly="true"/></td>
                                                                    </s:elseif>
                                                                    <s:else>
                                                                        <td colspan="2" ><s:textarea cssClass="inputTextArea" value="%{currentApparaisal.pAttendence}" id="pAttendence" name="pAttendence" cols="110" rows="4"/></td>
                                                                    </s:else>
                                                                </tr>
                                                                <s:if test="%{empId!= #session.empId && (#session.isUserManager == 1 || #session.isUserTeamLead==1 || #session.roleId==7)}">
                                                                    <tr>
                                                                        <td class="fieldLabelLeft"> HR / TM :</td>
                                                                        <td class="fieldLabelLeft">Rating:</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>
                                                                            <s:textarea cssClass="inputTextArea" value="%{currentApparaisal.pAttendHrComments}" id="pAttendHrComments" name="pAttendHrComments" cols="82" rows="3"/>
                                                                        </td>
                                                                        <td valign="top">
                                                                            <s:select list="{'E-Excellent','M-Meets Expectation','B-Below Performer'}" 
                                                                                      name="pAttendHrRating" id="pAttendHrRating"
                                                                                      headerKey="1" headerValue="--Please Select--" value="%{currentApparaisal.pAttendHrRating}" cssClass="inputSelect" />
                                                                        </td>
                                                                    </tr>
                                                                </s:if>
                                                            </table>
                                                        </fieldset>
                                                    </td>
                                                </tr>
                                                <!--*************  Work Culture & Attitude ****** -->

                                                <tr>
                                                    <td colspan="2" class="fieldLabelLeft">
                                                        <fieldset>
                                                            
                                                            <legend>Work Culture & Attitude <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/help.gif" onmouseover="fixedtooltip('Strictly following the Companys™ rules/policies at Miracle and Client locations.',this,event, 200,-30,40)" onmouseout="delayhidetip()"></legend>
                                                            <table>
                                                                <tr>
                                                                    <s:if test="%{currentAppraisalAction == 'AddAppraisal'}">
                                                                        <td colspan="2" ><s:textarea cssClass="inputTextArea" value="%{currentApparaisal.workAttitude}" id="workAttitude" name="workAttitude" cols="110" rows="4"/></td>
                                                                    </s:if>
                                                                    <s:elseif test="%{ empId != #session.empId && (#session.isUserManager == 1 || #session.isUserTeamLead==1 || #session.roleId==7)}">
                                                                        <td colspan="2" ><s:textarea cssClass="inputTextArea" value="%{currentApparaisal.workAttitude}" id="workAttitude" name="workAttitude" cols="110" rows="4" readonly="true"/></td>
                                                                    </s:elseif>
                                                                    <s:else>
                                                                        <td colspan="2" ><s:textarea cssClass="inputTextArea" value="%{currentApparaisal.workAttitude}" id="workAttitude" name="workAttitude" cols="110" rows="4"/></td>
                                                                    </s:else>
                                                                </tr>
                                                                <s:if test="%{empId != #session.empId && (#session.isUserManager == 1 || #session.isUserTeamLead==1 || #session.roleId==7)}">
                                                                    <tr>
                                                                        <td class="fieldLabelLeft"> HR / TM :</td>
                                                                        <td class="fieldLabelLeft">Rating:</td>
                                                                    </tr>
                                                                    
                                                                    <tr>
                                                                        <td>
                                                                            <s:textarea cssClass="inputTextArea" id="wAttHrComm" value="%{currentApparaisal.wAttHrComment}" name="wAttHrComment" cols="82" rows="3"/>
                                                                        </td>
                                                                        <td valign="top">
                                                                            <s:select list="{'E-Excellent','M-Meets Expectation','B-Below Performer'}" name="wAttHrRating" id="wAttHrRating"
                                                                                      headerKey="1" headerValue="--Please Select--" value="%{currentApparaisal.wAttHrRating}" cssClass="inputSelect" />
                                                                        </td>
                                                                    </tr>
                                                                </s:if>
                                                            </table>
                                                        </fieldset>
                                                    </td>
                                                </tr>
                                                
                                                
                                                <!-- Communications    ***************         --> 
                                                
                                                
                                                <tr>
                                                    <td colspan="2" class="fieldLabelLeft">
                                                        <fieldset>
                                                            <legend>Communications <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/help.gif" onmouseover="fixedtooltip('Oral and Written communications are open and honest.  Information is usually accurate and effectively presented.Oral communications are generally courteous (shows respect, listens well, and responds appropriately)..',this,event, 500,-25,40)" onmouseout="delayhidetip()"></legend>
                                                            <table>
                                                                <tr>
                                                                    <s:if test="%{currentAppraisalAction == 'AddAppraisal'}">
                                                                        <td colspan="2" ><s:textarea cssClass="inputTextArea" value="%{currentApparaisal.communication}" id="communication" name="communication" cols="110" rows="4" /></td>
                                                                    </s:if>
                                                                    <s:elseif test="%{empId != #session.empId  && (#session.isUserManager == 1 || #session.isUserTeamLead==1 || #session.roleId==7)}">
                                                                        <td colspan="2" ><s:textarea cssClass="inputTextArea" value="%{currentApparaisal.communication}" id="communication" name="communication" cols="110" rows="4" readonly="true"/></td>
                                                                    </s:elseif>
                                                                    <s:else>
                                                                        <td colspan="2" ><s:textarea cssClass="inputTextArea" value="%{currentApparaisal.communication}" id="communication" name="communication" cols="110" rows="4"/></td>
                                                                    </s:else>
                                                                </tr>
                                                                <s:if test="%{empId != #session.empId && (#session.isUserManager == 1 || #session.isUserTeamLead==1 || #session.roleId==7)}">
                                                                    <tr>
                                                                        <td class="fieldLabelLeft"> HR / TM :</td>
                                                                        <td class="fieldLabelLeft">Rating:</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>
                                                                            <s:textarea cssClass="inputTextArea" value="%{currentApparaisal.commHrComments}" id="commHrComments" name="commHrComments" cols="82" rows="3"/>
                                                                        </td>
                                                                        <td valign="top">
                                                                            <s:select list="{'E-Excellent','M-Meets Expectation','B-Below Performer'}" name="commHrRating" id="commHrRating"
                                                                                      headerKey="1" headerValue="--Please Select--" value="%{currentApparaisal.commHrRating}" cssClass="inputSelect" />
                                                                        </td>
                                                                    </tr>
                                                                </s:if>
                                                            </table>
                                                        </fieldset>
                                                    </td>
                                                </tr>
                                                
                                                <!--  Work Objectives ************  -->
                                                
                                                <tr>
                                                    <td colspan="2" class="fieldLabelLeft">
                                                        <fieldset>
                                                            <legend>Work Objectives <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/help.gif" onmouseover="fixedtooltip('Work assignments are usually accomplished in an efficient and effective manner.  Assignments are completed by assigned deadlines.  ',this,event, 400,-20,40)" onmouseout="delayhidetip()"></legend>
                                                            <table>
                                                                <tr>
                                                                    <s:if test="%{currentAppraisalAction == 'AddAppraisal'}">
                                                                        <td colspan="2" ><s:textarea cssClass="inputTextArea" value="%{currentApparaisal.workObjectives}" id="workObjectives" name="workObjectives" cols="110" rows="4" /></td>
                                                                    </s:if>
                                                                    <s:elseif test="%{empId != #session.empId  && (#session.isUserManager == 1 || #session.isUserTeamLead==1 || #session.roleId==7)}">
                                                                        <td colspan="2" ><s:textarea cssClass="inputTextArea" value="%{currentApparaisal.workObjectives}" id="workObjectives" name="workObjectives" cols="110" rows="4" readonly="true"/></td>
                                                                    </s:elseif>
                                                                    <s:else>
                                                                        <td colspan="2" ><s:textarea cssClass="inputTextArea" value="%{currentApparaisal.workObjectives}" id="workObjectives" name="workObjectives" cols="110" rows="4"/></td>
                                                                    </s:else>
                                                                </tr>
                                                                <s:if test="%{empId != #session.empId && (#session.isUserManager == 1 || #session.isUserTeamLead==1 || #session.roleId==7)}">
                                                                    <tr>
                                                                        <td class="fieldLabelLeft"> HR / TM :</td>
                                                                        <td class="fieldLabelLeft">Rating:</td>
                                                                    </tr>
                                                                    
                                                                    <tr>
                                                                        <td>
                                                                            <s:textarea cssClass="inputTextArea" value="%{currentApparaisal.workObjHrComm}" id="workObjHrComm" name="workObjHrComm" cols="82" rows="3"/>
                                                                        </td>
                                                                        <td valign="top">
                                                                            <s:select list="{'E-Excellent','M-Meets Expectation','B-Below Performer'}" name="workObjHrRating" id="workObjHrRating"
                                                                                      headerKey="1" headerValue="--Please Select--" value="%{currentApparaisal.workObjHrRating}" cssClass="inputSelect" />
                                                                        </td>
                                                                    </tr>
                                                                </s:if>
                                                            </table>
                                                        </fieldset>
                                                    </td>
                                                </tr>
                                                
                                                
                                                <!-- **************  Collaboration & Team Work  ********-->
                                                
                                                <tr>
                                                    <td colspan="2" class="fieldLabelLeft">
                                                        <fieldset>
                                                            <legend>Collaboration & Team Work <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/help.gif" onmouseover="fixedtooltip('Knowledge of best practices and lessons learned needs to beshared with others.Working relationships with supervisor, co-employees and others are cooperative and respectful.',this,event, 500,-20,40)" onmouseout="delayhidetip()"></legend>
                                                            <table>
                                                                <tr>
                                                                    <s:if test="%{currentAppraisalAction == 'AddAppraisal'}">
                                                                        <td colspan="2" ><s:textarea cssClass="inputTextArea" value="%{currentApparaisal.collaborationTW}" id="collaborationTW" name="collaborationTW" cols="110" rows="4"/></td>
                                                                    </s:if>
                                                                    <s:elseif test="%{empId != #session.empId  && (#session.isUserManager == 1 || #session.isUserTeamLead==1 || #session.roleId==7)}">
                                                                        <td colspan="2" ><s:textarea cssClass="inputTextArea" value="%{currentApparaisal.collaborationTW}" id="collaborationTW" name="collaborationTW" cols="110" rows="4" readonly="true"/></td>
                                                                    </s:elseif>
                                                                    <s:else>
                                                                        <td colspan="2" ><s:textarea cssClass="inputTextArea" value="%{currentApparaisal.collaborationTW}" id="collaborationTW" name="collaborationTW" cols="110" rows="4"/></td>
                                                                    </s:else>
                                                                </tr>
                                                                <s:if test="%{empId != #session.empId && (#session.isUserManager == 1 || #session.isUserTeamLead==1 || #session.roleId==7)}">
                                                                    <tr>
                                                                        <td class="fieldLabelLeft"> HR / TM :</td>
                                                                        <td class="fieldLabelLeft">Rating:</td>
                                                                    </tr>
                                                                    
                                                                    <tr>
                                                                        <td>
                                                                            <s:textarea cssClass="inputTextArea" value="%{currentApparaisal.collTWHrComm}" id="collTWHrComm" name="collTWHrComm" cols="82" rows="3"/>
                                                                        </td>
                                                                        <td valign="top">
                                                                            <s:select list="{'E-Excellent','M-Meets Expectation','B-Below Performer'}" name="collTWHrRating" id="collTWHrRating"
                                                                                      headerKey="1" headerValue="--Please Select--" value="%{currentApparaisal.collTWHrRating}" cssClass="inputSelect" />
                                                                        </td>
                                                                    </tr>
                                                                </s:if>
                                                            </table>
                                                        </fieldset>
                                                    </td>
                                                </tr>
                                                
                                                <!--***********Planning & Organization ********* -->
                                                
                                                <tr>
                                                    <td colspan="2" class="fieldLabelLeft">
                                                        <fieldset>
                                                            <legend>Planning & Organization <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/help.gif" onmouseover="fixedtooltip('Good control of day-to-day work scheduling/activities.  Establishes priorities that address the details and timelines needed to achieve the intended results. ',this,event, 400,-20,40)" onmouseout="delayhidetip()"></legend>
                                                            <table>
                                                                <tr>
                                                                    <s:if test="%{currentAppraisalAction == 'AddAppraisal'}">
                                                                        <td colspan="2" ><s:textarea cssClass="inputTextArea" value="%{currentApparaisal.planOrganization}" id="planOrganization" name="planOrganization" cols="110" rows="4"/></td>
                                                                    </s:if>
                                                                    <s:elseif test="%{empId != #session.empId &&(#session.isUserManager == 1 || #session.isUserTeamLead==1 || #session.roleId==7)}">
                                                                        <td colspan="2" ><s:textarea cssClass="inputTextArea" value="%{currentApparaisal.planOrganization}" id="planOrganization" name="planOrganization" cols="110" rows="4" readonly="true"/></td>
                                                                    </s:elseif>
                                                                    <s:else>
                                                                        <td colspan="2" ><s:textarea cssClass="inputTextArea" value="%{currentApparaisal.planOrganization}" id="planOrganization" name="planOrganization" cols="110" rows="4"/></td>
                                                                    </s:else>
                                                                </tr>
                                                                <s:if test="%{empId != #session.empId && (#session.isUserManager == 1 || #session.isUserTeamLead==1 || #session.roleId==7)}">
                                                                    <tr>
                                                                        <td class="fieldLabelLeft"> HR / TM :</td>
                                                                        <td class="fieldLabelLeft">Rating:</td>
                                                                    </tr>
                                                                    
                                                                    <tr>
                                                                        <td>
                                                                            <s:textarea cssClass="inputTextArea" value="%{currentApparaisal.planOrgHrComm}" id="planOrgHrComm" name="planOrgHrComm" cols="82" rows="3"/>
                                                                        </td>
                                                                        <td valign="top">
                                                                            <s:select list="{'E-Excellent','M-Meets Expectation','B-Below Performer'}" name="planOrgHrRating" id="planOrgHrRating"
                                                                                      headerKey="1" headerValue="--Please Select--" value="%{currentApparaisal.planOrgHrRating}" cssClass="inputSelect" />
                                                                        </td>
                                                                    </tr>
                                                                </s:if>
                                                            </table>
                                                        </fieldset>
                                                    </td>
                                                </tr>
                                                
                                                <!--  ********* Adaptability/Versatility  ******-->
                                                
                                                <tr>
                                                    <td colspan="2" class="fieldLabelLeft">
                                                        <fieldset>
                                                            <legend>Adaptability/Versatility <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/help.gif" onmouseover="fixedtooltip('Absorbs new tasks, procedures and workloads with an average amount of instruction. Reacts positively to changes in management direction, systems and procedures to meet changing organizational needs. Is open to new ideas.',this,event, 500,-30,40)" onmouseout="delayhidetip()"></legend>
                                                            <table>
                                                                <tr valign="top">
                                                                    <s:if test="%{currentAppraisalAction == 'AddAppraisal'}">
                                                                        <td colspan="2" ><s:textarea cssClass="inputTextArea" value="%{currentApparaisal.adaptVersatility}" id="adaptVersatility" name="adaptVersatility" cols="110" rows="4"/></td>
                                                                    </s:if>
                                                                    <s:elseif test="%{empId != #session.empId && (#session.isUserManager == 1 || #session.isUserTeamLead==1 || #session.roleId==7)}">
                                                                        <td colspan="2" ><s:textarea cssClass="inputTextArea" value="%{currentApparaisal.adaptVersatility}" id="adaptVersatility" name="adaptVersatility" cols="110" rows="4" readonly="true"/></td>
                                                                    </s:elseif>
                                                                    <s:else>
                                                                        <td colspan="2" ><s:textarea cssClass="inputTextArea" value="%{currentApparaisal.adaptVersatility}" id="adaptVersatility" name="adaptVersatility" cols="110" rows="4"/></td>
                                                                    </s:else>
                                                                </tr>
                                                                <s:if test="%{empId != #session.empId && (#session.isUserManager == 1 || #session.isUserTeamLead==1 || #session.roleId==7)}">
                                                                    <tr>
                                                                        <td class="fieldLabelLeft"> HR / TM :</td>
                                                                        <td class="fieldLabelLeft">Rating:</td>
                                                                    </tr>
                                                                    
                                                                    <tr>
                                                                        <td>
                                                                            <s:textarea cssClass="inputTextArea" value="%{currentApparaisal.adaptVersatilityHrComm}" id="adaptVersatilityHrComm" name="adaptVersatilityHrComm" cols="82" rows="3"/>
                                                                        </td>
                                                                        <td valign="top">
                                                                            <s:select list="{'E-Excellent','M-Meets Expectation','B-Below Performer'}" name="adaptVersatilityHrRating" id="adaptVersatilityHrRating"
                                                                                      headerKey="1" headerValue="--Please Select--" value="%{currentApparaisal.adaptVersatilityHrRating}"  cssClass="inputSelect" />
                                                                        </td>
                                                                    </tr>
                                                                </s:if>
                                                            </table>
                                                        </fieldset>
                                                    </td>
                                                </tr>
                                                <!--*************  Achievements & Appreciations ******* -->
                                                
                                                
                                                <tr>
                                                    <td colspan="2" class="fieldLabelLeft">
                                                        <fieldset>
                                                            <legend>Achievements & Appreciations <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/help.gif" onmouseover="fixedtooltip('Special achievements which helps the Team & Practice.  Any special appreciations received from client.',this,event, 400,-20,40)" onmouseout="delayhidetip()"></legend>
                                                            <table>
                                                                <tr>
                                                                    <s:if test="%{currentAppraisalAction == 'AddAppraisal'}">
                                                                        <td colspan="2" ><s:textarea cssClass="inputTextArea" value="%{currentApparaisal.achievAppreciations}" id="achievAppreciations" name="achievAppreciations" cols="110" rows="4"/></td>
                                                                    </s:if>
                                                                    <s:elseif test="%{empId != #session.empId && (#session.isUserManager == 1 || #session.isUserTeamLead==1 || #session.roleId==7)}">
                                                                        <td colspan="2" ><s:textarea cssClass="inputTextArea" value="%{currentApparaisal.achievAppreciations}" id="achievAppreciations" name="achievAppreciations" cols="110" rows="4" readonly="true"/></td>
                                                                    </s:elseif>
                                                                    <s:else>
                                                                        <td colspan="2" ><s:textarea cssClass="inputTextArea" value="%{currentApparaisal.achievAppreciations}" id="achievAppreciations" name="achievAppreciations" cols="110" rows="4"/></td>
                                                                    </s:else>
                                                                </tr>
                                                                <s:if test="%{empId != #session.empId && (#session.isUserManager == 1 || #session.isUserTeamLead==1 || #session.roleId==7)}">
                                                                    <tr>
                                                                        <td class="fieldLabelLeft"> HR / TM :</td>
                                                                        <td class="fieldLabelLeft">Rating:</td>
                                                                    </tr>
                                                                    
                                                                    <tr>
                                                                        <td>
                                                                            <s:textarea cssClass="inputTextArea" value="%{currentApparaisal.achievApprecHrComm}" id="achievApprecHrComm" name="achievApprecHrComm" cols="82" rows="3"/>
                                                                        </td>
                                                                        <td valign="top">
                                                                            <s:select list="{'E-Excellent','M-Meets Expectation','B-Below Performer'}" name="achievApprecHrRating" id="achievApprecHrRating"
                                                                                      headerKey="1" headerValue="--Please Select--" value="%{currentApparaisal.achievApprecHrRating}" cssClass="inputSelect" />
                                                                        </td>
                                                                    </tr>
                                                                </s:if>
                                                            </table>
                                                        </fieldset>
                                                    </td>
                                                </tr>
                                            </table>
                                        </s:form>
                                    </sx:div>
                                    
                                    <!-- End Of Project Details  -->                                    
                                    <sx:div id="projectDetails" label="Project Details" cssStyle="overflow:auto;">
                                        
                                        <s:form action="addProject" theme="simple" name="projectDetails">
                                            <s:if test="%{empId != #session.empId && (#session.isUserManager == 1 || #session.isUserTeamLead==1 || #session.roleId==7)}">
                                                <table border="0" width="70%" cellpadding="2" cellspacing="2" align="center" >
                                                    <tr class="headerText" align="right" >
                                                        <td class="headerText" colspan="2">
                                                            <s:property value="#request.resultMessage"/><s:a href="#" onclick="history.go(-1)"><input type="button" class="buttonBg" value="Back"></s:a>
                                                            <s:submit label="Submit" value="Save" cssClass="buttonBg" />
                                                            <s:hidden name="appraisalId" id="appraisalId" /> 
                                                            <s:hidden name="currentEmployeeId" id="currentEmployeeId" />
                                                            <s:hidden name="empId" id="empId" />
                                                        </td>
                                                    </tr> 
                                                    <tr>
                                                        <td colspan="2" width="100%">
                                                            <fieldset>
                                                                <legend>Project One</legend>
                                                                <table border="0" width="830" cellpadding="2" cellspacing="2" >
                                                                    
                                                                    <tr>
                                                                        <td class="fieldLabel">Name Of the Project :</td> 
                                                                        <td colspan="3" ><s:textfield name="nameOfProject1" id="nameOfProject1" cssClass="inputTextBlueLarge" value="%{currentApparaisal.nameOfProject1}" readonly="true"/></td>
                                                                        
                                                                    </tr>
                                                                    <tr >
                                                                        <td class="fieldLabel">Mode of Work :</td>
                                                                        <td><s:select list="{'onsite','offshore'}" headerKey="1" headerValue="--please select--" cssClass="inputTextBlue" name="modeOfWork1" id="modeOfWork1" value="%{currentApparaisal.modeOfWork1}"  /></td>
                                                                        <td class="fieldLabel">Resource Type :</td>
                                                                        <td><s:select list="{'Billable','Non-Billable'}" headerKey="1" headerValue="--please select--" cssClass="inputTextBlue" name="resourceType1" id="resourceType1" value="%{currentApparaisal.resourceType1}"/></td>   
                                                                    </tr>
                                                                    <tr >
                                                                        <td class="fieldLabel">Start Date :</td> 
                                                                        <td> <s:textfield cssClass="inputTextBlue" id="startDate1" name="startDate1"value="%{currentApparaisal.startDate1}" readonly="true"/>
                                                                            <a href="javascript:sd1.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>  </td>
                                                                        
                                                                        <td class="fieldLabel">End Date :</td> 
                                                                        <td> <s:textfield cssClass="inputTextBlue" id="endDate1" name="endDate1"  value="%{currentApparaisal.endDate1}" readonly="true"/>
                                                                            <a href="javascript:ed1.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td class="fieldLabel">Comments :</td>
                                                                        <td colspan="3"><s:textarea id="comments" name="comments1" cssClass="inputTextArea" rows="4" cols="68" readonly="true"value="%{currentApparaisal.comments1}"/></td>
                                                                    </tr>
                                                                </table>
                                                            </fieldset>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="2">
                                                            <fieldset>
                                                                <legend>Project Two</legend>
                                                                <table border="0" width="830" cellpadding="2" cellspacing="2" >
                                                                    
                                                                    <tr>
                                                                        <td class="fieldLabel">Name Of the Project :</td> 
                                                                        <td colspan="3" ><s:textfield name="nameOfProject2"  id="nameOfProject2" cssClass="inputTextBlueLarge" value="%{currentApparaisal.nameOfProject2}" readonly="true"/></td>
                                                                        
                                                                    </tr>
                                                                    <tr>
                                                                        <td class="fieldLabel">Mode of Work :</td>
                                                                        <td><s:select list="{'onsite','offshore'}" headerKey="1" headerValue="--please select--" cssClass="inputTextBlue" name="modeOfWork2" id="modeOfWork2" value="%{currentApparaisal.modeOfWork2}" /></td>
                                                                        <td class="fieldLabel">Resource Type :</td>
                                                                        <td><s:select list="{'Billable','Non-Billable'}" headerKey="1" headerValue="--please select--" cssClass="inputTextBlue" name="resourceType2" id="resourceType2" value="%{currentApparaisal.resourceType2}"/></td>   
                                                                    </tr>
                                                                    <tr>
                                                                        <td class="fieldLabel">Start Date :</td> 
                                                                        <td> <s:textfield cssClass="inputTextBlue" id="startDate2" name="startDate2" value="%{currentApparaisal.startDate2}" readonly="true"/>
                                                                            <a href="javascript:sd2.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>  </td>
                                                                        
                                                                        <td class="fieldLabel">End Date :</td> 
                                                                        <td> <s:textfield cssClass="inputTextBlue" id="endDate2" name="endDate2"  value="%{currentApparaisal.endDate2}" readonly="true"/>
                                                                            <a href="javascript:ed2.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td class="fieldLabel">Comments :</td>
                                                                        <td colspan="3"><s:textarea id="comments2" name="comments2" rows="4" cols="68" cssClass="inputTextArea" value="%{currentApparaisal.comments2}" readonly="true" /></td>
                                                                    </tr>
                                                                </table>
                                                                
                                                            </fieldset>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="2">
                                                            <fieldset>
                                                                <legend>Project Three</legend>
                                                                <table border="0" width="830" cellpadding="2" cellspacing="2" >
                                                                    
                                                                    <tr>
                                                                        <td class="fieldLabel">Name Of the Project :</td> 
                                                                        <td colspan="3" ><s:textfield name="nameOfProject3" id="nameOfProject3" cssClass="inputTextBlueLarge" value="%{currentApparaisal.nameOfProject3}" readonly="true"/></td>
                                                                        
                                                                    </tr>
                                                                    <tr>
                                                                        <td class="fieldLabel">Mode of Work :</td>
                                                                        <td><s:select list="{'onsite','offshore'}" headerKey="1" headerValue="--please select--" cssClass="inputTextBlue" name="modeOfWork3" value="%{currentApparaisal.modeOfWork3}"/></td>
                                                                        <td class="fieldLabel">Resource Type :</td>
                                                                        <td><s:select list="{'Billable','Non-Billable'}" headerKey="1" headerValue="--please select--" cssClass="inputTextBlue" name="resourceType3" value="%{currentApparaisal.resourceType3}"/></td>   
                                                                    </tr>
                                                                    <tr>
                                                                        <td class="fieldLabel">Start Date :</td> 
                                                                        <td> <s:textfield cssClass="inputTextBlue" id="startDate3" name="startDate3" value="%{currentApparaisal.startDate3}" readonly="true"/>
                                                                            <a href="javascript:sd3.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>  </td>
                                                                        
                                                                        <td class="fieldLabel">End Date :</td> 
                                                                        <td> <s:textfield cssClass="inputTextBlue" id="endDate3" name="endDate3"  value="%{currentApparaisal.endDate3}" readonly="true"/>
                                                                            <a href="javascript:ed3.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td class="fieldLabel">Comments :</td>
                                                                        <td colspan="3"><s:textarea id="comments3" name="comments3" rows="4" cols="68" cssClass="inputTextArea" value="%{currentApparaisal.comments3}" readonly="true"/></td>
                                                                    </tr>
                                                                    
                                                                </table>
                                                                
                                                            </fieldset>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="2">
                                                            <fieldset>
                                                                <legend>Project Four</legend>
                                                                <table border="0" width="830" cellpadding="2" cellspacing="2" >
                                                                    
                                                                    <tr>
                                                                        <td class="fieldLabel">Name Of the Project :</td> 
                                                                        <td colspan="3" ><s:textfield name="nameOfProject4" id="nameOfProject4" cssClass="inputTextBlueLarge" value="%{currentApparaisal.modeOfWork4}" readonly="true"/></td>
                                                                        
                                                                    </tr>
                                                                    <tr>
                                                                        <td class="fieldLabel">Mode of Work :</td>
                                                                        <td><s:select list="{'onsite','offshore'}" headerKey="1" headerValue="--please select--" cssClass="inputTextBlue" name="modeOfWork4" value="%{currentApparaisal.modeOfWork4}"/></td>
                                                                        <td class="fieldLabel">Resource Type :</td>
                                                                        <td><s:select list="{'Billable','Non-Billable'}"  headerKey="1" headerValue="--please select--" cssClass="inputTextBlue" name="resourceType4" value="%{currentApparaisal.resourceType4}"/></td>   
                                                                    </tr>
                                                                    <tr>
                                                                        <td class="fieldLabel">Start Date :</td> 
                                                                        <td> <s:textfield cssClass="inputTextBlue" id="startDate4" name="startDate4" value="%{currentApparaisal.startDate4}" readonly="true"/>
                                                                            <a href="javascript:sd4.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>  </td>
                                                                        
                                                                        <td class="fieldLabel">End Date :</td> 
                                                                        <td> <s:textfield cssClass="inputTextBlue" id="endDate4" name="endDate4"  value="%{currentApparaisal.endDate4}" readonly="true"/>
                                                                            <a href="javascript:ed4.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td class="fieldLabel">Comments :</td>
                                                                        <td colspan="3"><s:textarea id="comments4" name="comments4" cssClass="inputTextArea" rows="4" cols="68" value="%{currentApparaisal.comments4}" readonly="true" /></td>
                                                                    </tr>
                                                                    
                                                                </table>
                                                                
                                                            </fieldset>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="2">
                                                            <fieldset>
                                                                <legend>Project Five</legend>
                                                                <table border="0" width="830" cellpadding="2" cellspacing="2" >
                                                                    
                                                                    <tr>
                                                                        <td class="fieldLabel">Name Of the Project :</td> 
                                                                        <td colspan="3" ><s:textfield name="nameOfProject5" cssClass="inputTextBlueLarge" value="%{currentApparaisal.nameOfProject5}" readonly="true"/></td>
                                                                        
                                                                    </tr>
                                                                    <tr>
                                                                        <td class="fieldLabel">Mode of Work :</td>
                                                                        <td><s:select list="{'onsite','offshore'}" headerKey="1" headerValue="--please select--" cssClass="inputTextBlue" name="modeOfWork5" value="%{currentApparaisal.modeOfWork5}"/></td>
                                                                        <td class="fieldLabel">Resource Type :</td>
                                                                        <td><s:select list="{'Billable','Non-Billable'}" headerKey="1" headerValue="--please select--" cssClass="inputTextBlue" name="resourceType5" value="%{currentApparaisal.resourceType5}"/></td>   
                                                                    </tr>
                                                                    <tr>
                                                                        <td class="fieldLabel">Start Date :</td> 
                                                                        <td> <s:textfield cssClass="inputTextBlue" id="startDate5" name="startDate5" value="%{currentApparaisal.startDate5}" readonly="true"/>
                                                                            <a href="javascript:sd5.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>  </td>
                                                                        
                                                                        <td class="fieldLabel">End Date :</td> 
                                                                        <td> <s:textfield cssClass="inputTextBlue" id="endDate5" name="endDate5"  value="%{currentApparaisal.endDate5}" readonly="true"/>
                                                                            <a href="javascript:ed5.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td class="fieldLabel">Comments :</td>
                                                                        <td colspan="3"><s:textarea id="comments5" name="comments5" rows="4" cssClass="inputTextArea" cols="68" value="%{currentApparaisal.comments5}"  readonly="true"/></td>
                                                                    </tr>
                                                                    
                                                                </table>
                                                                
                                                            </fieldset>
                                                        </td>
                                                    </tr>
                                                    <s:if test="%{empId != #session.empId && (#session.isUserManager == 1 || #session.isUserTeamLead==1 || #session.roleId==7)}">
                                                        <tr>
                                                            <td class="fieldLabelLeft">HR/TM :</td>
                                                            <td class="fieldLabelLeft"> Rating :</td>
                                                            
                                                        </tr>
                                                        <tr>
                                                            <td><s:textarea id="prjHrOrTm" name="prjHrOrTm" cssClass="inputTextArea"  rows="3" cols="70" value=""/></td>
                                                            <td valign="top" ><s:select list="{'E - Excellent Performer','M - Meets Expectation ','B - Below Performer'}" headerKey="1" headerValue="---please select--"  cssClass="inputSelect" id="PrjHrRating" name="PrjHrRating" value=""/></td>
                                                        </tr> 
                                                    </s:if>
                                                </table>
                                            </s:if>
                                            <s:else>
                                                <table border="1" width="70%" cellpadding="2" cellspacing="2" align="center" >
                                                    <tr class="headerText" align="right" >
                                                        <td class="headerText" colspan="2">
                                                            <s:property value="#request.resultMessage"/><s:a href="#" onclick="history.go(-1)"><input type="button" class="buttonBg" value="Back"></s:a>
                                                            <s:submit label="Submit" value="Save" cssClass="buttonBg" />
                                                            <s:hidden name="appraisalId" id="appraisalId" /> 
                                                            <s:hidden name="currentEmployeeId" id="currentEmployeeId" />
                                                            <s:hidden name="empId" id="empId" />
                                                        </td>
                                                    </tr> 
                                                    <tr>
                                                        <td colspan="2" width="100%">
                                                            <fieldset>
                                                                <legend>Project One</legend>
                                                                <table border="0" width="830" cellpadding="2" cellspacing="2" >
                                                                    
                                                                    <tr>
                                                                        <td class="fieldLabel"  >Name Of the Project :</td> 
                                                                        <td colspan="3" ><s:textfield name="nameOfProject1" id="nameOfProject1" cssClass="inputTextBlueLarge" value="%{currentApparaisal.nameOfProject1}"/></td>
                                                                        
                                                                    </tr>
                                                                    <tr>
                                                                        <td class="fieldLabel">Mode of Work :</td>
                                                                        <td><s:select list="{'onsite','offshore'}" headerKey="1" headerValue="--please select--" cssClass="inputTextBlue" name="modeOfWork1" id="modeOfWork1" value="%{currentApparaisal.modeOfWork1}"/></td>
                                                                        <td class="fieldLabel">Resource Type :</td>
                                                                        <td><s:select list="{'Billable','Non-Billable'}" headerKey="1" headerValue="--please select--" cssClass="inputTextBlue" name="resourceType1"  id="resourceType1" value="%{currentApparaisal.resourceType1}"/></td>   
                                                                    </tr>
                                                                    <tr >
                                                                        <td class="fieldLabel">Start Date :</td> 
                                                                        <td> <s:textfield cssClass="inputTextBlue" id="startDate1" name="startDate1"value="%{currentApparaisal.startDate1}"/>
                                                                            <a href="javascript:sd1.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>  </td>
                                                                        
                                                                        <td class="fieldLabel">End Date :</td> 
                                                                        <td> <s:textfield cssClass="inputTextBlue" id="endDate1" name="endDate1"  value="%{currentApparaisal.endDate1}"/>
                                                                            <a href="javascript:ed1.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td class="fieldLabel">Comments :</td>
                                                                        <td colspan="3"><s:textarea id="comments" name="comments1" rows="4" cols="68" cssClass="inputTextArea"  value="%{currentApparaisal.comments1}"/></td>
                                                                    </tr>
                                                                </table>
                                                            </fieldset>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="2">
                                                            <fieldset>
                                                                <legend>Project Two</legend>
                                                                <table border="0" width="830" cellpadding="2" cellspacing="2" >
                                                                    
                                                                    <tr>
                                                                        <td class="fieldLabel">Name Of the Project :</td> 
                                                                        <td colspan="3" ><s:textfield name="nameOfProject2"  id="nameOfProject2" cssClass="inputTextBlueLarge" value="%{currentApparaisal.nameOfProject2}"/></td>
                                                                        
                                                                    </tr>
                                                                    <tr>
                                                                        <td class="fieldLabel">Mode of Work :</td>
                                                                        <td><s:select list="{'onsite','offshore'}" headerKey="1" headerValue="--please select--" cssClass="inputTextBlue" name="modeOfWork2" id="modeOfWork2" value="%{currentApparaisal.modeOfWork2}"/></td>
                                                                        <td class="fieldLabel">Resource Type :</td>
                                                                        <td><s:select list="{'Billable','Non-Billable'}" headerKey="1" headerValue="--please select--" cssClass="inputTextBlue" name="resourceType2" id="resourceType2" value="%{currentApparaisal.resourceType2}"/></td>   
                                                                    </tr>
                                                                    <tr>
                                                                        <td class="fieldLabel">Start Date :</td> 
                                                                        <td> <s:textfield cssClass="inputTextBlue" id="startDate2" name="startDate2" value="%{currentApparaisal.startDate2}"/>
                                                                            <a href="javascript:sd2.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>  </td>
                                                                        
                                                                        <td class="fieldLabel">End Date :</td> 
                                                                        <td> <s:textfield cssClass="inputTextBlue" id="endDate2" name="endDate2"  value="%{currentApparaisal.endDate2}"/>
                                                                            <a href="javascript:ed2.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td class="fieldLabel">Comments :</td>
                                                                        <td colspan="3"><s:textarea id="comments2" name="comments2" rows="4" cols="68" cssClass="inputTextArea" value="%{currentApparaisal.comments2}" /></td>
                                                                    </tr>
                                                                </table>
                                                                
                                                            </fieldset>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="2">
                                                            <fieldset>
                                                                <legend>Project Three</legend>
                                                                <table border="0" width="830" cellpadding="2" cellspacing="2" >
                                                                    
                                                                    <tr>
                                                                        <td class="fieldLabel">Name Of the Project :</td> 
                                                                        <td colspan="3" ><s:textfield name="nameOfProject3" id="nameOfProject3" cssClass="inputTextBlueLarge" value="%{currentApparaisal.nameOfProject3}"/></td>
                                                                        
                                                                    </tr>
                                                                    <tr>
                                                                        <td class="fieldLabel">Mode of Work :</td>
                                                                        <td><s:select list="{'onsite','offshore'}" headerKey="1" headerValue="--please select--" cssClass="inputTextBlue" name="modeOfWork3" value="%{currentApparaisal.modeOfWork3}"/></td>
                                                                        <td class="fieldLabel">Resource Type :</td>
                                                                        <td><s:select list="{'Billable','Non-Billable'}" headerKey="1" headerValue="--please select--" cssClass="inputTextBlue" name="resourceType3" value="%{currentApparaisal.resourceType3}"/></td>   
                                                                    </tr>
                                                                    <tr>
                                                                        <td class="fieldLabel">Start Date :</td> 
                                                                        <td> <s:textfield cssClass="inputTextBlue" id="startDate3" name="startDate3" value="%{currentApparaisal.startDate3}"/>
                                                                            <a href="javascript:sd3.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>  </td>
                                                                        
                                                                        <td class="fieldLabel">End Date :</td> 
                                                                        <td> <s:textfield cssClass="inputTextBlue" id="endDate3" name="endDate3"  value="%{currentApparaisal.endDate3}"/>
                                                                            <a href="javascript:ed3.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td class="fieldLabel">Comments :</td>
                                                                        <td colspan="3"><s:textarea id="comments3" name="comments3" rows="4" cssClass="inputTextArea" cols="68" value="%{currentApparaisal.comments3}" /></td>
                                                                    </tr>
                                                                    
                                                                </table>
                                                                
                                                            </fieldset>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="2">
                                                            <fieldset>
                                                                <legend>Project Four</legend>
                                                                <table border="0" width="830" cellpadding="2" cellspacing="2" >
                                                                    
                                                                    <tr>
                                                                        <td class="fieldLabel">Name Of the Project :</td> 
                                                                        <td colspan="3" ><s:textfield name="nameOfProject4" id="nameOfProject4" cssClass="inputTextBlueLarge" value="%{currentApparaisal.modeOfWork4}"/></td>
                                                                        
                                                                    </tr>
                                                                    <tr>
                                                                        <td class="fieldLabel">Mode of Work :</td>
                                                                        <td><s:select list="{'onsite','offshore'}" headerKey="1" headerValue="--please select--" cssClass="inputTextBlue" name="modeOfWork4" value="%{currentApparaisal.modeOfWork4}"/></td>
                                                                        <td class="fieldLabel">Resource Type :</td>
                                                                        <td><s:select list="{'Billable','Non-Billable'}"  headerKey="1" headerValue="--please select--" cssClass="inputTextBlue" name="resourceType4" value="%{currentApparaisal.resourceType4}"/></td>   
                                                                    </tr>
                                                                    <tr>
                                                                        <td class="fieldLabel">Start Date :</td> 
                                                                        <td> <s:textfield cssClass="inputTextBlue" id="startDate4" name="startDate4" value="%{currentApparaisal.startDate4}"/>
                                                                            <a href="javascript:sd4.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>  </td>
                                                                        
                                                                        <td class="fieldLabel">End Date :</td> 
                                                                        <td> <s:textfield cssClass="inputTextBlue" id="endDate4" name="endDate4"  value="%{currentApparaisal.endDate4}"/>
                                                                            <a href="javascript:ed4.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td class="fieldLabel">Comments :</td>
                                                                        <td colspan="3"><s:textarea id="comments4" name="comments4" rows="4" cssClass="inputTextArea"  cols="68" value="%{currentApparaisal.comments4}" /></td>
                                                                    </tr>
                                                                    
                                                                </table>
                                                                
                                                            </fieldset>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="2">
                                                            <fieldset>
                                                                <legend>Project Five</legend>
                                                                <table border="0" width="830" cellpadding="2" cellspacing="2" >
                                                                    
                                                                    <tr>
                                                                        <td class="fieldLabel">Name Of the Project :</td> 
                                                                        <td colspan="3" ><s:textfield name="nameOfProject5" cssClass="inputTextBlueLarge" value="%{currentApparaisal.nameOfProject5}"/></td>
                                                                        
                                                                    </tr>
                                                                    <tr>
                                                                        <td class="fieldLabel">Mode of Work :</td>
                                                                        <td><s:select list="{'onsite','offshore'}" headerKey="1" headerValue="--please select--" cssClass="inputTextBlue" name="modeOfWork5" value="%{currentApparaisal.modeOfWork5}"/></td>
                                                                        <td class="fieldLabel">Resource Type :</td>
                                                                        <td><s:select list="{'Billable','Non-Billable'}" headerKey="1" headerValue="--please select--" cssClass="inputTextBlue" name="resourceType5" value="%{currentApparaisal.resourceType5}"/></td>   
                                                                    </tr>
                                                                    <tr>
                                                                        <td class="fieldLabel">Start Date :</td> 
                                                                        <td> <s:textfield cssClass="inputTextBlue" id="startDate5" name="startDate5" value="%{currentApparaisal.startDate5}"/>
                                                                            <a href="javascript:sd5.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>  </td>
                                                                        
                                                                        <td class="fieldLabel">End Date :</td> 
                                                                        <td> <s:textfield cssClass="inputTextBlue" id="endDate5" name="endDate5"  value="%{currentApparaisal.endDate5}"/>
                                                                            <a href="javascript:ed5.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td class="fieldLabel">Comments :</td>
                                                                        <td colspan="3"><s:textarea id="comments5" name="comments5" rows="4" cssClass="inputTextArea" cols="68" value="%{currentApparaisal.comments5}" /></td>
                                                                    </tr>
                                                                    
                                                                </table>
                                                                
                                                            </fieldset>
                                                        </td>
                                                    </tr>
                                                    
                                                </table>  
                                            </s:else>
                                        </s:form>
                                        <script type="text/JavaScript">
         var sd1 = new CalendarTime(document.forms['projectDetails'].elements['startDate1']);
         sd1.year_scroll = true;
         sd1.time_comp = false;
          var sd2 = new CalendarTime(document.forms['projectDetails'].elements['startDate2']);
         sd2.year_scroll = true;
         sd2.time_comp = false;
         var sd3 = new CalendarTime(document.forms['projectDetails'].elements['startDate3']);
         sd3.year_scroll = true;
         sd3.time_comp = false;
         var sd4 = new CalendarTime(document.forms['projectDetails'].elements['startDate4']);
         sd4.year_scroll = true;
         sd4.time_comp = false;
         var sd5 = new CalendarTime(document.forms['projectDetails'].elements['startDate5']);
         sd5.year_scroll = true;
         sd5.time_comp = false;   
         
         var ed1 = new CalendarTime(document.forms['projectDetails'].elements['endDate1']);
        ed1.year_scroll = true;
        ed1.time_comp = false;
         var ed2 = new CalendarTime(document.forms['projectDetails'].elements['endDate2']);
        ed2.year_scroll = true;
        ed2.time_comp = false;
        var ed3 = new CalendarTime(document.forms['projectDetails'].elements['endDate3']);
        ed3.year_scroll = true;
        ed3.time_comp = false;
        var ed4 = new CalendarTime(document.forms['projectDetails'].elements['endDate4']);
        ed4.year_scroll = true;
        ed4.time_comp = false;
        var ed5 = new CalendarTime(document.forms['projectDetails'].elements['endDate5']);
        ed5.year_scroll = true;
        ed5.time_comp = false;
                                        </script>   
                                        
                                        
                                        
                                    </sx:div>
                                    <!-- Start TeamLead Details  -->                                    
                                    <s:if test="%{empId != #session.empId && #session.isUserTeamLead==1}">
                                        <sx:div id="teamLead" label="TM" cssStyle="overflow:auto;">
                                            <s:form action="tLead" name="teamLead" id="teamLead" theme="simple">
                                                <s:hidden name="appraisalId" id="appraisalId" />
                                                <s:hidden name="currentEmployeeId" id="currentEmployeeId" />
                                                <table align="center" cellpadding='0' width="100%" cellspacing='0' border='0'>
                                                    <tr class="headerText" align="right">
                                                        <td class="headerText" align="right">
                                                            <s:property value="#request.resultMessage"/><s:a href="#" onclick="history.go(-1)"><input type="button" class="buttonBg" value="Back"></s:a>
                                                            <s:submit cssClass="buttonBg" value="save"/>
                                                        </td>
                                                        
                                                    </tr>
                                                    <tr>
                                                        
                                                        <td valign="top">
                                                            <s:hidden name="TM" id="TM" value="TM"/><font style="font-family: arial,verdana; font-size:12px;"> Comments: </font>
                                                            <s:textarea name="tlcomments" rows="10" cols="115" cssClass="inputTextArea" value="%{currentApparaisal.tlcomments}"/>         
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td valign="top">
                                                            <table cellpadding='0' cellspacing='0' border='0' class="fieldLabelLeft"><tr>
                                                                    <td width="2%" class="cellBorder">Name</td>
                                                                    <td width="6%" class="cellBorder"><s:textfield name="eName" id="eName" cssClass="inputTextBlueLarge" value="%{currentApparaisal.empName}" /></td>
                                                                    <td width="6%" class="cellBorder"><table><tr>
                                                                                <td class="fieldLabelLeft">
                                                                                    Rating :
                                                                                </td>
                                                                                <td align="right">
                                                                                    <s:select
                                                                                        name="tlRating"  value="%{currentApparaisal.tlRating}"
                                                                                        list="{'E - Excellent Performer','M - Meets Expectation ','B - Below Performer'}" cssClass="inputSelect"/> 
                                                                                </td>
                                                                            </tr>
                                                                        </table>
                                                                    </td>
                                                                    <td width="4%" class="cellBorder"><table><tr>
                                                                                <td class="fieldLabelLeft">
                                                                                    Date :
                                                                                </td>
                                                                                <td align="right">
                                                                                    <s:textfield name="currentDate" id="currentDate" cssClass="inputTextBlue" value="%{currentDate || currentApparaisal.currentTMDate}"/>  
                                                                                </td>
                                                                            </tr>
                                                                    </table></td>
                                                                </tr>
                                                            </table>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </s:form>
                                        </sx:div>
                                    </s:if>
                                    <!-- Start PracticeManager Details  -->                                    
                                    <s:if test="%{empId != #session.empId && #session.isUserManager == 1}">
                                        <sx:div id="pracManager" label="PM" cssStyle="overflow:auto;">
                                            <s:form action="pracManager" name="pManager" id="pManager" theme="simple">
                                                <s:hidden name="appraisalId" id="appraisalId" />
                                                <s:hidden name="currentEmployeeId" id="currentEmployeeId" />
                                                <table align="center" cellpadding='0' cellspacing='0' border='0' width="100%">
                                                    <tr class="headerText">
                                                        <td class="headerText" align="right"><s:a href="#" onclick="history.go(-1)"><input type="button" class="buttonBg" value="Back"></s:a>
                                                            <s:submit cssClass="buttonBg" value="save"/>
                                                        </td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td align="right">
                                                            <table>
                                                                <tr>
                                                                    <td align="right">
                                                                        <% if(request.getAttribute("resultMessage") != null) {
                                                                            out.println(request.getAttribute("resultMessage"));
                                                                        }
                                                                        %>
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        
                                                        <td valign="top">
                                                            <s:hidden name="PM" id="PM" value="PM"/><font style="font-family: arial,verdana; font-size:12px;"> Comments: </font>
                                                            <s:textarea rows="10" cols="115" name="pmComments" cssClass="inputTextArea" value="%{currentApparaisal.pmComments}"/>         
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td valign="top">
                                                            <table cellpadding='0' cellspacing='0' border='0' class="fieldLabelLeft"><tr>
                                                                    <td width="2%" class="cellBorder" id="nam">Name</td>
                                                                    <td width="6%" class="cellBorder"><s:textfield name="pName" id="pName" cssClass="inputTextBlueLarge" value="%{currentApparaisal.empName}"/></td>
                                                                    <td width="6%" class="cellBorder"><table><tr>
                                                                                <td class="fieldLabelLeft">
                                                                                    Rating :
                                                                                </td>
                                                                                <td align="right">
                                                                                    <s:select
                                                                                        name="pRating"  value="%{currentApparaisal.pRating}"
                                                                                        list="{'E - Excellent Performer','M - Meets Expectation ','B - Below Performer'}" cssClass="inputSelect"/> 
                                                                                </td>
                                                                            </tr>
                                                                        </table>
                                                                    </td>
                                                                    <td width="4%" class="cellBorder"><table><tr>
                                                                                <td class="fieldLabelLeft">
                                                                                    Date :
                                                                                </td>
                                                                                <td align="right">
                                                                                    <s:textfield name="currentDate" id="currentDate" cssClass="inputTextBlue" value="%{currentDate || currentApparaisal.currentPMDate}"/>  
                                                                                </td>
                                                                            </tr>
                                                                    </table></td>
                                                                </tr>
                                                            </table>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </s:form>
                                            
                                        </sx:div>
                                    </s:if>
                                    <!-- Start Individual Details  --> 
                                   
                                    <s:if test="#session.roleId == 7">
                                        <sx:div label="Details Of Individuals" id="individuals" cssStyle="overflow:auto;">
                                            <s:form action="/employee/appraisal/addorUpdate.action" theme="simple" method="post">
                                                <table  width="100%" cellpadding="2" cellspacing="2" border="0">
                                                    <tr align="right">
                                                        <td colspan="2" class="headerText">
                                                            <s:property value="#request.resultMessage"/><s:a href="#" onclick="history.go(-1)"><input type="button" class="buttonBg" value="Back"></s:a>
                                                            <s:submit cssClass="buttonBg" value="save"/>
                                                            
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <table cellpadding="2" cellspacing="0" width="100%" border="0">
                                                                <tr style="font-family: arial,verdana; font-size:12px;">
                                                                    <td class="fieldLabel" align="left">Howmany Months The Individual is on Payroll :</td>
                                                                    <td><s:textfield name="monthsInPayroll" value="%{currentApparaisal.monthsInPayroll}" cssClass="inputTextBlue"/></td>
                                                                </tr>
                                                                
                                                                <tr style="font-family: arial,verdana; font-size:12px;">
                                                                    <td class="fieldLabel">Leave History :</td>
                                                                    <td ><s:textfield name="leaveHistory" value="%{currentApparaisal.leaveHistory}" cssClass="inputTextBlue"/></td>
                                                                </tr>
                                                                
                                                                <tr style="font-family: arial,verdana; font-size:12px;">
                                                                    <td class="fieldLabel">Onsite Project Duration (Months) :</td>
                                                                    <td ><s:textfield name="onsiteProjectDuration" value="%{currentApparaisal.onsiteProjectDuration}" cssClass="inputTextBlue"/></td>
                                                                </tr>
                                                                
                                                                <tr style="font-family: arial,verdana; font-size:12px;">
                                                                    <td class="fieldLabel">Offshore Project Duration (Months) :</td>
                                                                    <td ><s:textfield name="offshoreProjectDuration" value="%{currentApparaisal.offshoreProjectDuration}" cssClass="inputTextBlue"/></td>
                                                                </tr>
                                                                
                                                                <tr style="font-family: arial,verdana; font-size:12px;">
                                                                    <td class="fieldLabel">Howmany Months in Bench :</td>
                                                                    <td ><s:textfield name="benchDuration" value="%{currentApparaisal.benchDuration}" cssClass="inputTextBlue"/></td>
                                                                </tr>
                                                                
                                                                <tr style="font-family: arial,verdana; font-size:12px;">
                                                                    <td class="fieldLabel">Corporate Training Attended :</td>
                                                                    <td><s:textfield name="corporateTrainingDuration" value="%{currentApparaisal.corporateTrainingDuration}" cssClass="inputTextBlue"/></td>
                                                                </tr>
                                                                
                                                                <tr style="font-family: arial,verdana; font-size:12px;">
                                                                    <td class="fieldLabel">Howmany Interviews attended/failed :</td>
                                                                    <td><s:textfield name="interviewsAttended" value="%{currentApparaisal.interviewsAttended}" cssClass="inputTextBlueSmall"/></td>
                                                                </tr>
                                                                
                                                                <tr style="font-family: arial,verdana; font-size:12px;">
                                                                    <td class="fieldLabel">Current Salary :</td>
                                                                    <td><s:textfield name="currentSal" value="%{currentApparaisal.currentSal}" cssClass="inputTextBlueSmall"/></td>
                                                                </tr>
                                                                
                                                                <tr style="font-family: arial,verdana; font-size:12px;">
                                                                    <td class="fieldLabel">Previous Hike :</td>
                                                                    <td><s:textfield name="previousHike" value="%{currentApparaisal.previousHike}" cssClass="inputTextBlueSmall"/></td>
                                                                </tr>
                                                                
                                                                <tr style="font-family: arial,verdana; font-size:12px;">
                                                                    <td class="fieldLabel">VISA Available :</td>
                                                                    <td><s:radio list="{'yes','no'}" name="visaAvailable" value="%{currentApparaisal.visaAvailable}" /></td>
                                                                </tr>
                                                            </table>
                                                        </td>
                                                        <td class="fieldLabelLeft" valign="top">
                                                            SkillSet [Worked on] :<br><s:textarea name="skills" rows="17" cols="40" value="%{currentApparaisal.skills}" cssClass="inputTextArea"/>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="fieldLabelLeft" colspan="2">HR Comments :<br><s:textarea name="hrComments"  rows="5" cols="100" value="%{currentApparaisal.hrComments}" cssClass="inputTextArea"/></td>
                                                    </tr>
                                                    
                                                </table>
                                                
                                                <s:hidden name="currentEmployeeId" id="currentEmployeeId"/>
                                            </s:form>
                                        </sx:div>
                                        
                                    </s:if>
                                    <!-- HR Manager Details  -->                                    
                                   <s:if test="%{empId != #session.empId && #session.roleId == 7}">
                                        <sx:div id="hr" label="HR" cssStyle="overflow:auto;">
                                            <s:form action="hr" name="hr" id="hr" theme="simple">
                                                <s:hidden name="appraisalId" id="appraisalId" />
                                                <s:hidden name="currentEmployeeId" id="currentEmployeeId" />
                                                <table align="center" cellpadding='0' cellspacing='0' border='0'>
                                                    <tr class="headerText">
                                                        <td class="headerText" align="right"><s:a href="#" onclick="history.go(-1)"><input type="button" class="buttonBg" value="Back"></s:a>
                                                            <s:submit cssClass="buttonBg" value="save"/>
                                                        </td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td align="right">
                                                            <table>
                                                                <tr>
                                                                    <td align="right">
                                                                        <% if(request.getAttribute("resultMessage") != null) {
                                                                            out.println(request.getAttribute("resultMessage"));
                                                                        }
                                                                        %>
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        
                                                        <td valign="top">
                                                            <s:hidden name="HR" id="HR" value="HR"/><font style="font-family: arial,verdana; font-size:12px;"> Comments: </font>
                                                            <s:textarea rows="10" cols="115" name="hrComment" cssClass="inputTextArea" value="%{currentApparaisal.hrComment}"/>         
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td valign="top">
                                                            <table cellpadding='0' cellspacing='0' border='0' class="fieldLabelLeft"><tr>
                                                                    <td width="2%" class="cellBorder" id="nam">Name</td>
                                                                    <td width="6%" class="cellBorder"><s:textfield name="hName" id="hName" cssClass="inputTextBlueLarge" value="%{currentApparaisal.empName}"/></td>
                                                                    <td width="6%" class="cellBorder"><table><tr>
                                                                                <td class="fieldLabelLeft">
                                                                                    Rating :
                                                                                </td>
                                                                                <td align="right">
                                                                                    <s:select
                                                                                        name="hrRating" 
                                                                                        list="{'E - Excellent Performer','M - Meets Expectation ','B - Below Performer'}" cssClass="inputSelect" value="%{currentApparaisal.hrRating}"/> 
                                                                                </td>
                                                                            </tr>
                                                                        </table>
                                                                    </td>
                                                                    <td width="4%" class="cellBorder"><table><tr>
                                                                                <td class="fieldLabelLeft">
                                                                                    Date :
                                                                                </td>
                                                                                <td align="right">
                                                                                    <s:textfield name="currentDate" id="currentDate" cssClass="inputTextBlue" value="%{currentDate || currentApparaisal.currentHRDate}"  />  
                                                                                </td>
                                                                            </tr>
                                                                    </table></td>
                                                                </tr>
                                                            </table>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </s:form>
                                        </sx:div>
                                    </s:if>
                                    <!-- Start Vp Details  -->                                    
                                    <s:if test="#session.isUserManager == 1">
                                        <sx:div id="vp" label="VP" cssStyle="overflow:auto;">
                                            <s:form action="vicePresident" name="vp" id="vp" theme="simple">
                                                <s:hidden name="appraisalId" id="appraisalId" />
                                                <s:hidden name="currentEmployeeId" id="currentEmployeeId" />
                                                <table align="center" cellpadding='0' cellspacing='0' width="100%" border='0'>
                                                    <tr class="headerText">
                                                        <td class="headerText" colspan="6" align="right"><s:a href="#" onclick="history.go(-1)"><input type="button" class="buttonBg" value="Back"></s:a>
                                                            <s:submit cssClass="buttonBg" value="save"/>
                                                        </td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td align="right">
                                                            <table>
                                                                <tr>
                                                                    <td align="right">
                                                                        <% if(request.getAttribute("resultMessage") != null) {
                                                                            out.println(request.getAttribute("resultMessage"));
                                                                        }
                                                                        %>
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td valign="top">
                                                            <s:hidden name="VP" id="VP" value="VP"/><font style="font-family: arial,verdana; font-size:12px;"> Comments: </font>
                                                            <s:textarea rows="10" cols="115" name="vpComments" cssClass="inputTextArea" value="%{currentApparaisal.vpComments}"/>         
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td valign="top">
                                                            <table cellpadding='0' cellspacing='0' border='0' class="fieldLabelLeft"><tr>
                                                                    <td width="2%" class="cellBorder" id="nam">Name</td>
                                                                    <td width="6%" class="cellBorder"><s:textfield name="vName" id="vName" cssClass="inputTextBlueLarge" value="%{currentApparaisal.empName}"/></td>
                                                                    <td width="6%" class="cellBorder"><table><tr>
                                                                                <td class="fieldLabelLeft">
                                                                                    Rating :
                                                                                </td>
                                                                                <td align="right">
                                                                                    <s:select
                                                                                        name="vpRating" value="%{currentApparaisal.vpRating}"
                                                                                        list="{'E - Excellent Performer','M - Meets Expectation ','B - Below Performer'}" cssClass="inputSelect"/> 
                                                                                </td>
                                                                            </tr>
                                                                        </table>
                                                                    </td>
                                                                    <td width="4%" class="cellBorder"><table><tr>
                                                                                <td class="fieldLabelLeft">
                                                                                    Date :
                                                                                </td>
                                                                                <td align="right">
                                                                                    <s:textfield name="currentDate" id="currentDate" cssClass="inputTextBlue" value="%{currentDate || currentApparaisal.currentVPDate}" />  
                                                                                </td>
                                                                            </tr>
                                                                    </table></td>
                                                                </tr>
                                                            </table>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </s:form>
                                        </sx:div>
                                        
                                    </s:if>
                                    
                                </sx:tabbedpanel>
                            </td>
                            <!--//END DATA COLUMN : Coloumn for Screen Content-->
                        </tr>
                    </table>
                </td>
            </tr>
            <!--//START FOOTER : Record for Footer Background and Content-->
            <tr class="footerBg">
                <td align="center"><s:include value="/includes/template/Footer.jsp"/></td>
            </tr>
            <!--//END FOOTER : Record for Footer Background and Content-->
        </table>
    </body>
</html>

