<%-- 
    Document   : AppreciationDetails
    Created on : Nov 3, 2015, 4:09:24 PM
    Author     : miracle
--%>

<%@page import="java.util.regex.Pattern"%>
<%@page contentType="text/html;charset=UTF-8" errorPage="../../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.freeware.gridtag.*" %> 
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="com.mss.mirage.util.*"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ page import="java.sql.*,java.lang.*,java.util.*" %>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd" %>

<html>
    <head>
        <title>Hubble Organization Portal :: Quarterly Review&nbsp;Details</title>

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css?version=1"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/selectivity-full.css"/>">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/recruitment/ConsultantResumeClientValidation.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script> 
        <script type="text/javascript" src="<s:url value="/includes/javascripts/marketing/SurveyFormScripts.js"/>"></script> 
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmployeeAjax.js?version=53"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/jquery.min.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/jquery.js"/>"></script>  
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/new/x0popup.min.css?version=1.0"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/payroll/x0popup.min.js"/>"></script>
        <style>
            textarea:focus,
            input[type="text"]:hover,
            input[type="password"]:focus,
            input[type="datetime"]:focus,
            input[type="datetime-local"]:focus,
            input[type="date"]:focus,
            input[type="month"]:focus,
            input[type="time"]:focus,
            input[type="week"]:focus,
            input[type="number"]:focus,
            input[type="email"]:focus,
            input[type="url"]:focus,
            input[type="search"]:focus,
            input[type="tel"]:focus,
            input[type="color"]:focus,
            .uneditable-input:focus {   
                /*                border-text-color: #00008B;*/
                /*                box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(126, 239, 104, 0.6);*/
                outline: 0 none;
            }
            .buttonq {
                border-radius: 6px;
                background-color: #919191; /* Green */
                border: none;
                color: white;
                padding: 0px 8px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 12px;
                margin: 4px 2px;
                cursor: pointer;
            }
            .buttonq:hover {
                background-color: #39510A;
                color: white;
            }
            .fieldValue{
                text-color: #000;
                font-size: 12px;
                background-color: #ffffff;

            }
        </style>




    </head>

    <body class="bodyGeneral" oncontextmenu="return false;">
        <%
            String appraisalManagerRating = "";
            String calWeightage = "";


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
                    <!--//START TABBED PANNEL : -->
                    <ul id="surveyDetailsTabs" class="shadetabs" >
                        <s:if test="%{currentAction=='doAddAppreciation'}">
                            <li ><a href="#" class="selected" rel="libraryAdd">Quarterly  Review</a></li> 
                        </s:if>
                        <s:else>
                            <li ><a href="#" class="selected" rel="libraryAdd">Quarterly  Review</a></li>
                        </s:else>
                    </ul>
                    <div  style="border:1px solid gray; width:830px;height: 499px; overflow:auto; margin-bottom: 1em;">
                        <div id="libraryAdd" class="tabcontent"  >
                            <div id="empQuartAppraisalOverlay" class="overlay"></div> 
                            <div id="empQuartAppraisalSpecialBox" class="specialBox" style="border-radius: 30px;background: transparent;">
                                <s:form theme="simple"  align="center" >

                                    <%--    <s:hidden name="overlayJobId" id="overlayJobId"/>

                                    
                                    <s:hidden name="teamId" id="teamId" value="%{#session.sourcingFlag}"/>
                                    <s:hidden name="roleId" id="roleId" value="%{#session.roleId}"/> --%>
                                    <input type="hidden" id="descriptionId" value=""/>
                                    <table align="center" border="0" cellspacing="0" style="background: white;width:87%;border-radius: 30px 30px 30px 30px; xborder-collapse: collapse;" >
                                        <td  colspan="" style="background-color: #288AD1;width:100%;border-radius: 30px 0px 0px 0px;  xborder-collapse: collapse;" >
                                            <h3 style="color:darkblue;" align="left">
                                                <span id="headerLabel" style="margin-left: 10px;"></span>


                                            </h3></td>
                                        <td colspan="" style="background-color: #288AD1;width:70%;border-radius: 0px 30px 0px 0px;  xborder-collapse: collapse;" align="right">

                                            <a href="#" onmousedown="toggleAppraisalOverlay()"  style="margin-right: 10px;">
                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> 

                                            </a>  

                                        </td></tr>
                                        <tr style="border-radius: 0px 0px 30px 30px;">
                                        <td colspan="2">
                                            <div id="load" style="color: green;display: none;">Loading..</div>
                                            <div id="resultMessage"></div>
                                        </td>
                                        </tr>    
                                        <tr style="border-radius: 0px 0px 30px 30px; xborder-collapse: collapse;"><td colspan="2" style="border-radius: 0px 0px 30px 30px; border: 1px solid #999;border-top:0; xborder-collapse: collapse;">
                                            <table style="width:80%;" align="center" border="0">

                                                <tr id="empDescriptionTr">
                                                <td colspan="2">
                                                    
                                                    <s:textarea style="width:476px;" rows="5" cols="70"  onfocus="countChar(this,'descriptionCount');"  onkeyup="countChar(this,'descriptionCount');"  placeholder="Enter your Description here...." name="description" cssClass="inputTextareaOverlay1" onchange="checkDoubleQuotes(this);return fieldLengthValidatorQuarter(this);" id="description" /></td>
                                                <td><lable style="color:green" id="descriptionCount"></lable></td>    
                                        </tr>




                                        <tr id="addTr" style="display: none"> 

                                            <%--   <td class="fieldLabel">Job&nbsp;Logo:</td>
                                             <td colspan="2" ><s:file name="file" label="file" cssClass="inputTextarea" id="file" onchange="attachmentFileNameValidate();"/></td>  --%>
                                            <s:if test="%{#session.roleName == 'Employee'}">
                                            <td colspan="2" align="right" >
                                                <input type="button" value="Save" onclick="addAppraisalComments();" class="buttonBg" id="addButton"/> 

                                            </td>
                                        </s:if>
                                        </tr> 
                                        <tr>
                                        <td colspan="2"></td>
                                        <td><lable style="display:none">Testing</lable></td>
                                        </tr>
                                    </table>
                                    </td>
                                    </tr>
                                    </table>
                                </s:form>    
                            </div>



                            <s:form name="frmAppraisal" id="frmAppraisal" action="empQuarterlyAppraisalSave" theme="simple" method="POST" enctype="multipart/form-data" onsubmit="return surveyFieldsValidate();">
                                <div id="goalsOverlay" class="overlay"></div> 
                                <div id="goalsSpecialBox" class="specialBox" style="border-radius: 30px;background: transparent;top: 45px;">


                                    <table align="center" border="0" cellspacing="0" style="background: white;width:85%;border-radius: 30px 30px 30px 30px; xborder-collapse: collapse;" >
                                        <td colspan="" style="background-color: #288AD1;width:70%;border-radius: 30px 0px 0px 0px;  xborder-collapse: collapse;" >
                                            <h3 style="color:darkblue;" align="left">
                                                <span  style="margin-left: 10px;color: white">Goals</span>


                                            </h3></td>
                                        <td colspan="" style="background-color: #288AD1;width:70%;border-radius: 0px 30px 0px 0px;  xborder-collapse: collapse;" align="right">

                                            <a href="#" onmousedown="addGoalsOverlay()"  style="margin-right: 10px;">
                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> 

                                            </a>  

                                        </td></tr>

                                        <tr style="border-radius: 0px 0px 30px 30px; xborder-collapse: collapse;"><td colspan="2" style="border-radius: 0px 0px 30px 30px; border: 1px solid #999; xborder-collapse: collapse;">
                                            <table style="width:80%;" align="center" border="0">
                                                <tr>
                                        <td colspan="2">
                                            <div id="loadGoal" style="color: green;display: none;">Loading..</div>
                                            <div id="resultMessageGoal"></div>
                                        </td>
                                        </tr> 
                                                <tr >
                                                <td class="fieldLabel"> Short&nbsp;Term&nbsp;Goals:</td>
                                                <td>
                                                    <s:if test="%{curretRole == 'my'}">
                                                        <s:textarea rows="5" cols="65" onfocus="countChar(this,'shortTermGoalCount');" onkeyup="countChar(this,'shortTermGoalCount');"  placeholder="Do you have a goal what to do in next 2 years? Are you planning how to achieve it? Do you need any help?" name="shortTermGoal" cssClass="inputTextareaOverlay1" onchange="checkDoubleQuotes(this);return fieldLengthValidatorQuarter(this);" id="shortTermGoal" /></td>
                                                    </s:if>
                                                    <s:else>
                                                        <s:textarea rows="5" readonly="true" cols="65" placeholder="Enter short term goals...." name="shortTermGoal" cssClass="inputTextareaOverlay1" onchange="checkDoubleQuotes(this);return fieldLengthValidatorQuarter(this);" id="shortTermGoal" /></td>

                                        </s:else>
                                        <td><lable style="color:green" id="shortTermGoalCount"></lable></td>
                                        </tr>
                                        <s:if test="%{#session.roleName == 'Employee'}">
                                            <tr id="shortTeamGoalCommentsTr">
                                            <td class="fieldLabel"> Manager&nbsp;Comments&nbsp;:</td>
                                            <td><s:textarea rows="5" cols="65" onfocus="countChar(this,'shortTermGoalCommentsCount');" onkeyup="countChar(this,'shortTermGoalCommentsCount');"  placeholder="Enter your comments here...." name="shortTermGoalComments" cssClass="inputTextareaOverlay1" onchange="checkDoubleQuotes(this);return fieldLengthValidatorQuarter(this);" id="shortTermGoalComments" /></td>
                                            <td><lable style="color:green" id="shortTermGoalCommentsCount"></lable></td>
                                            </tr>
                                        </s:if>
                                        <s:else>
                                            <tr id="shortTeamGoalCommentsTr">
                                            <td class="fieldLabel"> Manager&nbsp;Comments&nbsp;:</td>
                                            <td><s:textarea rows="5" readonly="true" cols="65"   placeholder="Enter your comments here...." name="shortTermGoalComments" cssClass="inputTextareaOverlay1" onchange="checkDoubleQuotes(this);return fieldLengthValidatorQuarter(this);" id="shortTermGoalComments" /></td>
                                            <td><lable style="color:green" id="shortTermGoalCommentsCount"></lable></td>
                                            </tr>
                                        </s:else>

                                        <tr >
                                        <td class="fieldLabel"> Long&nbsp;Term&nbsp;Goals:</td>
                                        <td>
                                            <s:if test="%{curretRole == 'my'}">

                                                <s:textarea rows="5" cols="65" onfocus="countChar(this,'longTermGoalCount');" onkeyup="countChar(this,'longTermGoalCount');"  placeholder="Do you have a goal what to do in next 5 years? Are you planning how to achieve it? Do you need any help?" name="longTermGoal" cssClass="inputTextareaOverlay1" onchange="checkDoubleQuotes(this);return fieldLengthValidatorQuarter(this);" id="longTermGoal" /></td>

                                        </s:if>  <s:else>
                                            <s:textarea rows="5" readonly="true" cols="65" placeholder="Enter long term goals...." name="longTermGoal" cssClass="inputTextareaOverlay1" onchange="checkDoubleQuotes(this);return fieldLengthValidatorQuarter(this);" id="longTermGoal" /></td>

                                        </s:else>
                                        <td><lable style="color:green" id="longTermGoalCount"></lable></td>
                                        </tr>

                                        <s:if test="%{#session.roleName == 'Employee'}">
                                            <tr id="longTeamGoalCommentsTr">
                                            <td class="fieldLabel"> Manager&nbsp;Comments&nbsp;:</td>
                                            <td><s:textarea rows="5" cols="65" onfocus="countChar(this,'longTermGoalCommentsCount');" onkeyup="countChar(this,'longTermGoalCommentsCount');"  placeholder="Enter your comments here...." name="longTermGoalComments" cssClass="inputTextareaOverlay1" onchange="checkDoubleQuotes(this);return fieldLengthValidatorQuarter(this);" id="longTermGoalComments" /></td>
                                            <td><lable style="color:green" id="longTermGoalCommentsCount"></lable></td>
                                            </tr>
                                        </s:if>
                                        <s:else>
                                            <tr id="longTeamGoalCommentsTr">
                                            <td class="fieldLabel"> Manager&nbsp;Comments&nbsp;:</td>
                                            <td><s:textarea readonly="true" rows="5" cols="65"   placeholder="Enter your comments here...." name="longTermGoalComments" cssClass="inputTextareaOverlay1" onchange="checkDoubleQuotes(this);return fieldLengthValidatorQuarter(this);" id="longTermGoalComments" /></td>
                                            <td><lable style="color:green" id="longTermGoalCommentsCount"></lable></td>
                                            </tr>
                                        </s:else>

                                        <tr id="goalsBtnTr"> 
                                        <td></td>
                                        <%--   <td class="fieldLabel">Job&nbsp;Logo:</td>
                                         <td colspan="2" ><s:file name="file" label="file" cssClass="inputTextarea" id="file" onchange="attachmentFileNameValidate();"/></td>  --%>

                                        <td  align="right" >
                                            <s:if test="%{#session.roleName == 'Employee'}">
                                                <input type="button" value="Save" onclick="saveGoals();" class="buttonBg" id="savebtnGoal"/> 
                                            </s:if>
                                        </td>
                                        </tr> 
                                    </table>
                                    </td>
                                    </tr>
                                    </table>

                                </div>

                                <div id="personalOverlay" class="overlay"></div> 
                                <div id="personalSpecialBox" class="specialBox" style="border-radius: 30px;background: transparent;top: 45px;">


                                    <table align="center" border="0" cellspacing="0" style="background: white;width:85%;border-radius: 30px 30px 30px 30px; xborder-collapse: collapse;" >
                                        <td colspan="" style="background-color: #288AD1;width:70%;border-radius: 30px 0px 0px 0px;  xborder-collapse: collapse;" >
                                            <h3 style="color:darkblue;" align="left">
                                                <span  style="margin-left: 10px;color: white">Personality</span>


                                            </h3></td>
                                        <td colspan="" style="background-color: #288AD1;width:70%;border-radius: 0px 30px 0px 0px;  xborder-collapse: collapse;" align="right">

                                            <a href="#" onmousedown="addPersonalOverlay()"  style="margin-right: 10px;">
                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> 

                                            </a>  

                                        </td></tr>
                                        <tr>
                                        <td colspan="2">
                                            <div id="loadPersonality" style="color: green;display: none;">Loading..</div>
                                             <div id="resultMessagePersonality"></div>
                                        </td>
                                        </tr> 
                                        <tr style="border-radius: 0px 0px 30px 30px; xborder-collapse: collapse;"><td colspan="2" style="border-radius: 0px 0px 30px 30px; border: 1px solid #999; xborder-collapse: collapse;border-top:0;">
                                            <table style="width:80%;" align="center" border="0">
                                                <tr>
                                                <td></td>

                                                </tr>
                                                <tr >
                                                <td class="fieldLabel"> Strengths:</td>
                                                <td>
                                                    <s:if test="%{curretRole == 'my'}">
                                                        <s:textarea rows="5" cols="65" onfocus="countChar(this,'personalityCount');" onkeyup="countChar(this,'personalityCount');"  placeholder="What are your personal strengths? What are your technical strengths? What are your other capabilities that we don't know?" name="strength" cssClass="inputTextareaOverlay1" onchange="checkDoubleQuotes(this);return fieldLengthValidatorQuarter(this);" id="strength" />
                                                    </s:if>
                                                    <s:else>
                                                        <s:textarea rows="5" readonly="true" cols="65" placeholder="Enter Strengths...." name="strength" cssClass="inputTextareaOverlay1" onchange="checkDoubleQuotes(this);return fieldLengthValidatorQuarter(this);" id="strength" />
                                                    </s:else>
                                                </td>
                                                <td><lable style="color:green" id="personalityCount"></lable></td>
                                        </tr>
                                        <s:if test="%{#session.roleName == 'Employee'}">
                                            <tr id="strengthsCommentsTr">
                                            <td class="fieldLabel"> Manager&nbsp;Comments&nbsp;:</td>
                                            <td><s:textarea rows="5" cols="65" onfocus="countChar(this,'personalityCommnetsCount');" onkeyup="countChar(this,'personalityCommnetsCount');"  placeholder="Enter your comments here...." name="strengthsComments" cssClass="inputTextareaOverlay1" onchange="checkDoubleQuotes(this);return fieldLengthValidatorQuarter(this);" id="strengthsComments" /></td>
                                            <td><lable style="color:green" id="personalityCommnetsCount"></lable></td>    
                                            </tr>
                                        </s:if>
                                        <s:else>
                                            <tr id="strengthsCommentsTr">
                                            <td class="fieldLabel"> Manager&nbsp;Comments&nbsp;:</td>
                                            <td><s:textarea rows="5" readonly="true" cols="65"   placeholder="Enter your comments here...." name="strengthsComments" cssClass="inputTextareaOverlay1" onchange="checkDoubleQuotes(this);return fieldLengthValidatorQuarter(this);" id="strengthsComments" /></td>
                                            <td><lable style="color:green" id="personalityCommnetsCount"></lable></td>    
                                            </tr> 
                                        </s:else>

                                        <tr >

                                        <td class="fieldLabel"> Improvements&nbsp;:</td>
                                        <td>
                                            <s:if test="%{curretRole == 'my'}">
                                                <s:textarea rows="5" cols="65" onfocus="countChar(this,'improvementsCount');" onkeyup="countChar(this,'improvementsCount');"  placeholder="What are those other areas that you want to improve personally, technically and globally?" name="improvements" cssClass="inputTextareaOverlay1" onchange="checkDoubleQuotes(this);return fieldLengthValidatorQuarter(this);" id="improvements" />
                                            </s:if>
                                            <s:else>
                                                <s:textarea rows="5" readonly="true" cols="65"  placeholder="Enter improvements...." name="improvements" cssClass="inputTextareaOverlay1" onchange="checkDoubleQuotes(this);return fieldLengthValidatorQuarter(this);" id="improvements" />
                                            </s:else>
                                        </td>
                                        <td><lable style="color:green" id="improvementsCount"></lable></td>
                                        </tr>

                                        <s:if test="%{#session.roleName == 'Employee'}">
                                            <tr id="improvementsCommentsTr">
                                            <td class="fieldLabel"> Manager&nbsp;Comments&nbsp;:</td>
                                            <td><s:textarea rows="5" cols="65" onfocus="countChar(this,'improvementsCommentsCount');" onkeyup="countChar(this,'improvementsCommentsCount');"  placeholder="Enter your comments here...." name="improvementsComments" cssClass="inputTextareaOverlay1" onchange="checkDoubleQuotes(this);return fieldLengthValidatorQuarter(this);" id="improvementsComments" /></td>
                                            <td><lable style="color:green" id="improvementsCommentsCount"></lable></td>   
                                            </tr>
                                        </s:if>
                                        <s:else>
                                            <tr id="improvementsCommentsTr">
                                            <td class="fieldLabel"> Manager&nbsp;Comments&nbsp;:</td>
                                            <td><s:textarea readonly="true" rows="5" cols="65"    placeholder="Enter your comments here...." name="improvementsComments" cssClass="inputTextareaOverlay1" onchange="checkDoubleQuotes(this);return fieldLengthValidatorQuarter(this);" id="improvementsComments" /></td>
                                            <td><lable style="color:green" id="improvementsCommentsCount"></lable></td>   
                                            </tr>
                                        </s:else>

                                        <tr> 
                                        <td></td>
                                        <%--   <td class="fieldLabel">Job&nbsp;Logo:</td>
                                         <td colspan="2" ><s:file name="file" label="file" cssClass="inputTextarea" id="file" onchange="attachmentFileNameValidate();"/></td>  --%>
                                        <td id="personalitybtnTr" align="right" >
                                            <s:if test="%{#session.roleName == 'Employee'}">
                                                <input type="button" value="Save" onclick="savePersonality();" class="buttonBg" id="savebtnPersonality"/> 
                                            </s:if>
                                        </td>
                                        </tr> 
                                    </table>
                                    </td>
                                    </tr>
                                    </table>

                                </div>
                                <div id="rejectedCommentsOverlay" class="overlay"></div> 
                                <div id="rejectedCommentsSpecialBox" class="specialBox" style="border-radius: 30px;background: transparent;">

                                    <%--    <s:hidden name="overlayJobId" id="overlayJobId"/>

                                    
                                    <s:hidden name="teamId" id="teamId" value="%{#session.sourcingFlag}"/>
                                    <s:hidden name="roleId" id="roleId" value="%{#session.roleId}"/> --%>
                                    <table align="center" border="0" cellspacing="0" style="background: white;width:70%;border-radius: 30px 30px 30px 30px; xborder-collapse: collapse;" >
                                        <td colspan="" style="background-color: #288AD1;width:70%;border-radius: 30px 0px 0px 0px;  xborder-collapse: collapse;" >
                                            <h3 style="color:darkblue;" align="left">
                                                <span  style="margin-left: 10px;">Rejected Comments</span>


                                            </h3></td>
                                        <td colspan="" style="background-color: #288AD1;width:70%;border-radius: 0px 30px 0px 0px;  xborder-collapse: collapse;" align="right">

                                            <a href="#" onmousedown="toggleRejectedCommentsOverlay()"  style="margin-right: 10px;">
                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> 

                                            </a>  

                                        </td></tr>
                                        <tr>
                                        <td colspan="2">
                                            <div id="load1" style="color: green;display: none;">Loading..</div>
                                        </td>
                                        </tr>    
                                        <tr style="border-radius: 0px 0px 30px 30px; xborder-collapse: collapse;"><td colspan="2" style="border-radius: 0px 0px 30px 30px; border: 1px solid #999; xborder-collapse: collapse;">
                                            <table style="width:80%;" align="center" border="0">

                                                <td colspan="2"><s:textarea rows="5" cols="65"  onfocus="countChar(this,'rejectedCommentsCount');"  onkeyup="countChar(this,'rejectedCommentsCount');"  placeholder="Enter enter rejecte commets...." name="rejectedComments" cssClass="inputTextareaOverlay1" onchange="checkDoubleQuotes(this);return fieldLengthValidatorQuarter(this);" id="rejectedComments" /></td>
                                                <td><lable style="color:green" id="rejectedCommentsCount"></lable></td>    
                                        </tr>




                                        <tr id="rejectBtnTr"> 

                                            <%--   <td class="fieldLabel">Job&nbsp;Logo:</td>
                                             <td colspan="2" ><s:file name="file" label="file" cssClass="inputTextarea" id="file" onchange="attachmentFileNameValidate();"/></td>  --%>
                                        <td colspan="2" align="right" >
                                            <input type="button" value="Submit" onclick="quarterAppraisalAdd('Rejected',this);" class="buttonBg" /> 

                                        </td>

                                        </tr> 

                                    </table>
                                    </td>
                                    </tr>
                                    </table>
                                </div>
                                <table cellpadding="2" cellspacing="0" border="0" width="100%">   

                                    <tr align="right">

                                    <td class="headerText" colspan="8">
                                        <%
                                            if (request.getAttribute("resultMessage") != null) {
                                                out.println(request.getAttribute("resultMessage").toString());
                                            }
 if(session.getAttribute(ApplicationConstants.RESULT_MSG) != null){
                session.removeAttribute(ApplicationConstants.RESULT_MSG);
            }
                                        %>
                                        <s:if test="%{curretRole == 'team'}">
                                          <%--  <a href="<s:url value="teamQuaterAppraisalSearch.action"/>" style="align:center;">
                                                <img alt="Back to List"
                                                     src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                     width="66px" 
                                                     height="19px"
                                                     border="0" align="bottom">
                                            </a>  --%>
                                           <INPUT type="button" CLASS="buttonBg" value="Back to List" onClick="history.go(-1)">
                                        </s:if>
                                        <s:else>     

                                         <%--   <a href="<s:url value="myQuarterlyAppraisalSearch.action"/>" style="align:center;">
                                                <img alt="Back to List"
                                                     src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                     width="66px" 
                                                     height="19px"
                                                     border="0" align="bottom">
                                            </a>  --%>
                                         <s:if test="#request.resultMessage!=null">
                                          <INPUT type="button" CLASS="buttonBg" value="Back to List" onClick="history.go(-2)">
                                          </s:if>
                                          <s:else>
                                          <INPUT type="button" CLASS="buttonBg" value="Back to List" onClick="history.go(-1)">
                                          </s:else>
                                        </s:else>

                                    </td>
                                    </tr>
                                </table>
                                <input type="hidden" id="isManager" name="isManager" value="%{isManager}"/>
                                <input type="hidden" id="rowCount" name="rowCount" value=""/>
                                <s:hidden name="id" id="id"/>
                                <s:hidden name="empId" id="empId" value="%{empId}"/>
                                <s:hidden name="appraisalId" id="appraisalId" value="%{appraisalId}"/>
                                <s:hidden name="curretRole" id="curretRole" value="%{curretRole}"/>
                                <s:hidden name="lineId" id="lineId" value="%{lineId}"/>
                                <s:hidden name="status" id="status" value="%{status}"/>
                                <s:hidden name="quarterly" id="quarterly" value="%{quarterly}"/>
                                <s:hidden name="roleName" id="roleName" value="%{#session.roleName}"/>
                                <s:hidden name="managerRejectedComments" id="managerRejectedComments" value="%{managerRejectedComments}"/>
                                <s:hidden name="operationRejectedComments" id="operationRejectedComments" value="%{operationRejectedComments}"/>
                                <s:hidden name="dayCount" id="dayCount" value="%{dayCount}"/>
                                <s:hidden name="operationTeamStatus" id="operationTeamStatus" value="%{operationTeamStatus}"/>
                                <s:hidden name="accessCount" id="accessCount" value="%{accessCount}"/>
<input type="hidden" name="userId1" id="userId1" value="<s:property value="#session.userId"/>"/>
                                <table cellpadding="1" cellspacing="1" border="0" width="90%" align="center" >

                                    <tr>
                                    <td class="fieldLabel" >Emp&nbsp;Name&nbsp;:</td>
                                    <td><s:label class="navigationText" value="%{EmpName}" style="font-weight: bold;"/></td> 
                                    <td class="fieldLabel" >ITG&nbsp;:</td>
                                    <td><s:label class="navigationText" value="%{itgBatch}" style="font-weight: bold;"/><s:label class="navigationText" value="(Batch)"/></td> 
                                    <td class="fieldLabel" >DOJ&nbsp;:</td>
                                    <td><s:label class="navigationText" value="%{empDateOFBirth}" style="font-weight: bold;"/></td>
                                   


                                    </tr>
                                    <tr>
                                         <td class="fieldLabel" >Current Status:</td>
                                         <td colspan="3"><s:label class="navigationText" value="%{currStatus}" style="font-weight: bold;"/></td> 
                                     <td class="fieldLabel" >Practice:</td>
                                    <td><s:label name="practiceId" cssClass="navigationText" value="%{practiceId}" style="font-weight: bold;"/></td>

                                    </tr>
                                </table>
                                    <s:if test="#session.userId =='rkalaga'">
                                        <table cellpadding="0" cellspacing="0" border="0" width="90%" align="center" id="measurementTable" style="border: 1px solid #ddd; border-collapse: collapse;">
                                        <br>
                                        <tr  bgcolor="#3E93D4">
                                        <th style="color:#FFF;border: 1px solid #ddd;font-size: 12px;" rowspan="2" >
                                            Measurement
                                        </th>
                                        <th style="color:#FFF;border: 1px solid #ddd;padding: 5px;font-size: 12px;" rowspan="2">
                                            Key&nbsp;Factor
                                        </th>

                                        <th style="color:#FFF;border: 1px solid #ddd;padding: 5px;font-size: 12px;" rowspan="2">
                                            Weightage
                                        </th>


                                        <th colspan="2" style="color:#FFF;border: 1px solid #ddd;padding: 5px;font-size: 12px;">
                                            Employee 
                                        </th>

                                        <th colspan="2" style="color:#FFF;border: 1px solid #ddd;padding: 5px;font-size: 12px;">
                                            Manager
                                        </th>
                                        <th style="color:#FFF;border: 1px solid #ddd;font-size: 12px;padding: 5px;" rowspan="2">
                                            Calculated<br>Weightage
                                        </th>

                                        </tr>

                                        <tr style="color:#FFF" bgcolor="#3E93D4"> 



                                        <th colspan="" style="font-weight:normal;border: 1px solid #ddd;padding: 5px;font-size: 12px;">
                                            Desc
                                        </th>
                                        <th colspan="" style="font-weight:normal;border: 1px solid #ddd;padding: 5px;font-size: 12px;">
                                            Rating<s:label class="navigationText" value="(1-10)" style="font-size: 10px;color:#FFF;"/>
                                        </th>

                                        <th colspan="" style="font-weight:normal;border: 1px solid #ddd;padding: 5px;font-size: 12px;">
                                            Comments
                                        </th>
                                        <th colspan="" style="font-weight:normal;border: 1px solid #ddd;padding: 5px;font-size: 12px;">
                                            Rating<s:label class="navigationText" value="(1-10)" style="font-size: 10px;color:#FFF;"/>
                                        </th>


                                        </tr>
                                        <% if (request.getAttribute("QuarterAppraisalDetails") != null) {
                                                List deliveryKeyFactorsList = (List) request.getAttribute("deliveryKeyFactorsList");
                                                String QuarterAppraisalDetails = (String) request.getAttribute("QuarterAppraisalDetails");
                                                String[] individualRowDetails = QuarterAppraisalDetails.split(Pattern.quote("*@!"));

                                                for (int i = 1; i <= individualRowDetails.length; i++) {
                                                    String[] individualColumn = individualRowDetails[i - 1].split(Pattern.quote("#^$"));

                                        %>
                                        <tr style="height: 35px;" bgcolor="#FFF">
                                            <% if (!individualColumn[0].equals("_")) {%>
                                        <td class=""  style="text-align: center;border: 1px solid #ddd;padding: 5px;color: #000" rowspan="<%=individualColumn[1]%>"><%=individualColumn[0]%>
                                            <input type="hidden" id="measurementTittle<%=i%>" name="measurementTittle<%=i%>" value="<%=individualColumn[0]%>"/>
                                        </td><%}%>
                                        <td class="fieldValue" style="text-align: left;border: 1px solid #ddd;padding: 5px;"><%=individualColumn[4]%><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/info.jpg"  height="18" width="20" style="width: 15px; height: 15px; margin-bottom: -4px;" title="<%=individualColumn[12]%>" > </td>
                                        <input type="hidden" id="measurementId<%=i%>" name="measurementId<%=i%>" value="<%=individualColumn[2]%>"/>
                                        <input type="hidden" id="keyFactorId<%=i%>" name="keyFactorId<%=i%>" value="<%=individualColumn[3]%>" />
                                        <input type="hidden" id="keyFactorName<%=i%>" name="keyFactorName<%=i%>" value="<%=individualColumn[4]%>" />
                                        <td align="center" style="border: 1px solid #ddd;padding: 5px;"> 
                                            <input type="text" name="keyFactorWeightage<%=i%>" id="keyFactorWeightage<%=i%>" required="true"  class="inputTextBlue2" value="<%=individualColumn[5]%> " style="text-align: right;background-color:#dddddd;color:#000;" readonly="true"/>
                                        </td>



                                        <td align="center" style="border: 1px solid #ddd;padding: 5px;"> 
                                        <button type="button" id="empDescription<%=i%>btn" class="buttonq" title="<%=individualColumn[6]%>" onclick="addCommentsForQAppraisal('Employee',<%=i%>);">View</button></td>

                                        <input type="hidden" id="empDescription<%=i%>" name="empDescription<%=i%>" value="<%=individualColumn[6]%>"/>
                                        <input type="hidden" id="id<%=i%>" name="id<%=i%>" value="<%=individualColumn[11]%>"/>
                                        <td align="center" style="border: 1px solid #ddd;padding: 5px;">

                                            <input type="text" readOnly="true" tabindex="<%=i%>" name="empRating<%=i%>" value="<%=individualColumn[7]%>" id="empRating<%=i%>"  style="text-align: right;background-color:#dddddd;color:#000;" class="inputTextBlue2" value="<%=individualColumn[7]%>" onchange="isNumeric(this);totalQuarterCalculations();"/>

                                        </td>

                                        <td align="center" style="border: 1px solid #ddd;padding: 5px;">
                                        <button type="button" tabindex="<%=i%>" id="mgrComments<%=i%>btn"  title="<%=individualColumn[8]%>" class="buttonq" onclick="addCommentsForQAppraisal('Manager',<%=i%>);" title="<%=individualColumn[8]%>" >View</button></td>
                                        <input type="hidden" id="mgrComments<%=i%>" name="mgrComments<%=i%>" value="<%=individualColumn[8]%>"/>
                                        <%
                                            appraisalManagerRating = individualColumn[9];
                                            calWeightage = individualColumn[10];
                                            if (individualColumn[9].equals("0")) {
                                                appraisalManagerRating = "";
                                            }
                                            if (individualColumn[10].equals("0")) {
                                                calWeightage = "";
                                            }

                                        %>
                                        <td align="center" style="border: 1px solid #ddd;padding: 5px;">
                                            <input type="text" readOnly="true" required="true" name="appraisalManagerRating<%=i%>" id="appraisalManagerRating<%=i%>"   class="inputTextBlue2" value="<%=appraisalManagerRating%>" style="text-align: right;background-color:#dddddd;color:#000;" onchange="isNumeric(this);weightageCalculation(<%=i%>);"/>
                                        </td>
                                        <td align="center" style="border: 1px solid #ddd;padding: 5px;"> 
                                            <input type="text" readOnly="true" name="calWeightage<%=i%>" id="calWeightage<%=i%>" required="true"  class="inputTextBlue2" value="<%=calWeightage%>" onKeyPress="isNumeric(this);" style="text-align: right;background-color:#dddddd;color:#000;"/>
                                        </td>

                                        </tr>
                                        <%                                           }
                                            }%>

                                        <tr  bgcolor="#3E93D4">
                                        <th style="color:#FFF;border: 1px solid #ddd;padding: 5px;" colspan="2">
                                            Total
                                        </th>



                                        <th align="center" style="border: 1px solid #ddd;padding: 5px;">
                                            <input type="text" readOnly="true" name="totalKeyFactor" id="totalKeyFactor" required="true"  class="inputTextBlue2" value="" style="background-color:#B0C4DE;color:#000;border:1px solid #FFFAF0;font-weight: bold;text-align: center;"  />
                                        </th>

                                        <th colspan="" style="color:#00008B;border: 1px solid #ddd;padding: 5px;">

                                        </th>
                                        <th align="center" style="border: 1px solid #ddd;padding: 5px;">
                                            <input style="display: none;" type="text" name="totalEmpRating" id="totalEmpRating" required="true"  class="inputTextBlue2" value="" style="background-color:#B0C4DE;color:#000;border:1px solid #FFFAF0;font-weight: bold;text-align: center;" onKeyPress="isNumeric(this);" />
                                        </th>
                                        <%
                                            if ((session.getAttribute(ApplicationConstants.SESSION_IS_USER_MANAGER).toString().equals("1") && request.getAttribute("curretRole").equals("team")) || request.getAttribute("status") != null && request.getAttribute("status").toString().equals("Approved")) {
                                        %>
                                        <th colspan="" style="color:#00008B;border: 1px solid #ddd;padding: 5px;">

                                        </th>
                                        <th align="center" style="border: 1px solid #ddd;padding: 5px;">
                                            <input style="display: none;" type="text" name="totalMgrRating" id="totalMgrRating" required="true"  class="inputTextBlue2" value="" style="background-color:#B0C4DE;color:#000;border:1px solid #FFFAF0;font-weight: bold;text-align: center;" />
                                        </th>
                                        <th align="center" style="border: 1px solid #ddd;padding: 5px;">
                                            <input type="text" readOnly="true" name="totalCalWeightage" id="totalCalWeightage" required="true"  class="inputTextBlue2" value="" style="background-color:#B0C4DE;color:#000;border:1px solid #FFFAF0;font-weight: bold;text-align: center"  />
                                        </th>
                                        <% }%>
                                        </tr>

                                    </table>
                                        <table cellpadding="0" cellspacing="0" border="0" width="90%" align="center">
                                        <tr>
                                        <td>
                                            <input type="button" class="button_payroll"  value="Personality" onclick="addPersonalOverlay();" />
                                            <input type="button" class="button_payroll"  value="Goals" onclick="addGoalsOverlay();" />

                                        </td>
                                        <s:if test="%{dayCount==0}">
                                            <s:if test="%{status=='Entered'}">
                                                <td colspan="10" align="right">
                                                <lable style="color:green">Employee has to be submit  the appraisal..</lable>
                                                </td>

                                            </s:if>

                                            <s:if test="%{status=='Submitted'}">
                                                <td colspan="10" align="right">
                                                <lable style="color:green">Manager to approve ..</lable>
                                                </td>

                                            </s:if>

                                        </s:if>
                                        <s:if test="%{dayCount==1}">

                                            <s:if test="%{status=='Approved'}">
                                                <td colspan="10" align="right">
                                                     <lable style="color:green">Operation team to approve ..</lable>
                                                </td>

                                            </s:if>
                                            <s:if test="%{status=='Rejected'}">
                                                <td colspan="10" align="right">
                                                <lable style="color:green">Rejected by Manager..</lable>
                                                <a class="underscore" href='javascript:showRejectedCommentsOverlay("managerRejectedComments")'>
                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/ecertification/wrong.jpg" 
                                                         width="13" height="13" border="0" ></a>
                                                </td>

                                            </s:if>
                                            <s:if test="%{status=='Entered'}">
                                                <td colspan="10" align="right">
                                                <lable style="color:green">Employee has to be submit  the review..</lable>
                                                </td>

                                            </s:if>

                                            <s:if test="%{status=='Submitted'}">
                                                <td colspan="10" align="right">
                                                <lable style="color:green">Manager to approve ..</lable>
                                                </td>

                                            </s:if>
                                        </s:if>
                                        <s:if test="%{dayCount==2}">
                                            <s:if test="%{operationTeamStatus=='Approved'}">
                                                <td colspan="10" align="center">
                                                <lable style="color:green">Approved by Hr team..</lable>
                                                </td>
                                            </s:if>
                                                <s:if test="%{operationTeamStatus=='Closed'}">
                                                <td colspan="10" align="center">
                                                <lable style="color:green">Closed..</lable>
                                                </td>
                                            </s:if>
                                            <s:if test="%{operationTeamStatus=='Rejected'}">
                                                <td colspan="10" align="right">
                                                <lable style="color:green">Rejected by Hr team..</lable><a class="underscore" href='javascript:showRejectedCommentsOverlay("operationRejectedComments")'>
                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/ecertification/wrong.jpg" 
                                                         width="13" height="13" border="0" ></a>
                                                </td>
                                            </s:if>
                                            </tr>

                                        </s:if>

                                    </table>
                                    </s:if>
                                <s:elseif test="%{#session.roleName == 'Employee' && curretRole=='my'}">
                                    <table cellpadding="0" cellspacing="0" border="0" width="90%" align="center" id="measurementTable" style="border: 1px solid #ddd; border-collapse: collapse;">
                                        <br>
                                        <tr  bgcolor="#3E93D4">
                                        <th style="color:#FFF;border: 1px solid #ddd;font-size: 12px;" rowspan="2" >
                                            Measurement
                                        </th>
                                        <th style="color:#FFF;border: 1px solid #ddd;padding: 5px;font-size: 12px;" rowspan="2">
                                            Key&nbsp;Factor
                                        </th>

                                        <th style="color:#FFF;border: 1px solid #ddd;padding: 5px;font-size: 12px;display: 
                                            none;" rowspan="2">
                                            Weightage
                                        </th>


                                        <th colspan="2" style="color:#FFF;border: 1px solid #ddd;padding: 5px;font-size: 12px;">
                                            Employee
                                        </th>
                                        <%
                                            if (request.getAttribute("status") != null && request.getAttribute("status").toString().equals("Approved")) {
                                        %>
                                        <th colspan="2" style="color:#FFF;border: 1px solid #ddd;padding: 5px;font-size: 12px;display: none;">
                                            Manager
                                        </th>
                                        <th style="color:#FFF;border: 1px solid #ddd;font-size: 12px;padding: 5px;display: none" rowspan="2">
                                            Calculated<br>Weightage
                                        </th>

                                        </tr>
                                        <% }%>
                                        <tr style="color:#FFF" bgcolor="#3E93D4"> 



                                        <th colspan="" style="font-weight:normal;border: 1px solid #ddd;padding: 5px;font-size: 12px;">
                                            Desc
                                        </th>
                                        <th colspan="" style="font-weight:normal;border: 1px solid #ddd;padding: 5px;font-size: 12px;">
                                            Rating<s:label class="navigationText" value="(1-10)" style="font-size: 10px;color:#FFF;"/>
                                        </th>
                                        <%
                                            if (request.getAttribute("status") != null && request.getAttribute("status").toString().equals("Approved")) {
                                        %>
                                        <th colspan="2" style="font-weight:normal;border: 1px solid #ddd;padding: 5px;font-size: 12px;display: none;">
                                            Comments
                                        </th>
                                        <th colspan="" style="font-weight:normal;border: 1px solid #ddd;padding: 5px;font-size: 12px;display: none">
                                            Rating<s:label class="navigationText" value="(1-10)" style="font-size: 10px;color:#FFF;"/>
                                        </th>
                                        <% }%>

                                        </tr>
                                        <% if (request.getAttribute("QuarterAppraisalDetails") != null) {
                                                List deliveryKeyFactorsList = (List) request.getAttribute("deliveryKeyFactorsList");
                                                String QuarterAppraisalDetails = (String) request.getAttribute("QuarterAppraisalDetails");
                                               // System.out.println(QuarterAppraisalDetails);
                                                String[] individualRowDetails = QuarterAppraisalDetails.split(Pattern.quote("*@!"));

                                                for (int i = 1; i <= individualRowDetails.length; i++) {
                                                    String[] individualColumn = individualRowDetails[i - 1].split(Pattern.quote("#^$"));

                                        %>
                                        <tr style="height: 35px;" bgcolor="#FFF">
                                            <% if (!individualColumn[0].equals("_")) {%>
                                        <td class="fieldValue"  style="text-align: center;border: 1px solid #ddd;padding: 5px;text-color: #000" rowspan="<%=individualColumn[1]%>"><%=individualColumn[0]%>
                                            <input type="hidden" id="measurementTittle<%=i%>" name="measurementTittle<%=i%>" value="<%=individualColumn[0]%>"/>
                                        </td><%}%>
                                        <s:if test="%{appraisalId==0}">
                                            <td class="fieldValue" style="text-align: left;border: 1px solid #ddd;padding: 5px;"><%=individualColumn[4]%><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/info.jpg"  height="18" width="20" style="width: 15px; height: 15px; margin-bottom: -4px;" title="<%=individualColumn[6]%>" > </td>
                                        </s:if>
                                        <s:else>
                                            <td class="fieldValue" style="text-align: left;border: 1px solid #ddd;padding: 5px;"><%=individualColumn[4]%><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/info.jpg"  height="18" width="20" style="width: 15px; height: 15px; margin-bottom: -4px;" title="<%=individualColumn[12]%>" > </td>
                                        </s:else>
                                        
                                        <input type="hidden" id="measurementId<%=i%>" name="measurementId<%=i%>" value="<%=individualColumn[2]%>"/>
                                        <input type="hidden" id="keyFactorId<%=i%>" name="keyFactorId<%=i%>" value="<%=individualColumn[3]%>" />
                                        <input type="hidden" id="keyFactorName<%=i%>" name="keyFactorName<%=i%>" value="<%=individualColumn[4]%>" />
                                        <td align="center" class="fieldValue" style="border: 1px solid #ddd;padding: 5px;display: none;"> 
                                            <input type="text" name="keyFactorWeightage<%=i%>" id="keyFactorWeightage<%=i%>" required="true"  class="inputTextBlue2" value="<%=individualColumn[5]%> " style="text-align: right;background-color:#dddddd;color:#000;" readonly="true"/>
                                        </td>

                                        <%-- empdescription--%>    
                                        <td align="center" class="fieldValue" style="border: 1px solid #ddd;padding: 5px;"> 
                                            <s:if test="%{appraisalId==0}">
                                            <button type="button"  id="empDescription<%=i%>btn"  class="buttonq" onclick="addCommentsForQAppraisal('Employee',<%=i%>);">Add</button>
                                            <input type="hidden" id="empDescription<%=i%>" placeholder="<%=individualColumn[6]%>" name="empDescription<%=i%>" value=""/>
                                            <input type="hidden" id="id<%=i%>" name="id<%=i%>" value=""/>
                                        </s:if>
                                        <s:else>
                                            <%    if (request.getAttribute("status") != null && (request.getAttribute("status").toString().equals("Approved") || request.getAttribute("status").toString().equals("Submitted"))) {
                                            %>
                                            <button type="button" id="empDescription<%=i%>btn" class="buttonq" title="<%=individualColumn[6]%>" onclick="addCommentsForQAppraisal('Employee',<%=i%>);">View</button>
                                            <%
                                            } else {
                                                if (individualColumn[6].equals("")) {%>
                                            <button type="button" id="empDescription<%=i%>btn" class="buttonq" onclick="addCommentsForQAppraisal('Employee',<%=i%>);">Add</button>
                                            <%} else {%>
                                            <button type="button" id="empDescription<%=i%>btn" class="buttonq" title="<%=individualColumn[6]%>" onclick="addCommentsForQAppraisal('Employee',<%=i%>);">Update</button>
                                            <%}
                                                }
                                            %>
                                            <input type="hidden" id="empDescription<%=i%>" name="empDescription<%=i%>" value="<%=individualColumn[6]%>" placeholder="<%=individualColumn[12]%>"/>
                                            <input type="hidden" id="id<%=i%>" name="id<%=i%>" value="<%=individualColumn[11]%>"/>

                                        </s:else>
                                        </td>
                                        <%-- for emprating --%>
                                        <td align="center" class="fieldValue" style="border: 1px solid #ddd;padding: 5px;">
                                            <s:if test="%{appraisalId==0}">
                                                <input type="text"  tabindex="<%=i%>" name="empRating<%=i%>" id="empRating<%=i%>" required="true" style="text-align: right;" class="inputTextBlue2" value="" onchange="isNumeric(this);totalQuarterCalculations();"/>
                                            </s:if>
                                            <s:else>

                                                <s:if test="%{(status=='Approved' or status=='Submitted')}">

                                                    <input type="text" readOnly="true" tabindex="<%=i%>" name="empRating<%=i%>" id="empRating<%=i%>" required="true" style="text-align: right;background-color:#dddddd;color:#000;" class="inputTextBlue2" value="<%=individualColumn[7]%>" onchange="isNumeric(this);totalQuarterCalculations();"/>
                                                </s:if>
                                                <s:else>
                                                    <input type="text"  tabindex="<%=i%>" name="empRating<%=i%>" id="empRating<%=i%>" required="true" style="text-align: right;" class="inputTextBlue2" value="<%=individualColumn[7]%>" onchange="isNumeric(this);totalQuarterCalculations();"/>

                                                </s:else>
                                            </s:else>
                                        </td>

                                        <%

                                            if (request.getAttribute("status") != null && request.getAttribute("status").toString().equals("Approved")) {


                                        %>
                                        <td align="center" class="fieldValue" style="border: 1px solid #ddd;padding: 5px;display: none;">

                                        <button type="button" tabindex="<%=i%>" id="mgrComments<%=i%>btn" class="buttonq" title="<%=individualColumn[8]%>" onclick="addCommentsForQAppraisal('Manager',<%=i%>);" title="<%=individualColumn[8]%>" >View</button></td>

                                        <input type="hidden" id="mgrComments<%=i%>" name="mgrComments<%=i%>" value="<%=individualColumn[8]%>"/>
                                        <%
                                            appraisalManagerRating = individualColumn[9];
                                            calWeightage = individualColumn[10];
                                            if (individualColumn[9].equals("0")) {
                                                appraisalManagerRating = "";
                                            }
                                            if (individualColumn[10].equals("0")) {
                                                calWeightage = "";
                                            }

                                        %>
                                        <td class="fieldValue" align="center" style="border: 1px solid #ddd;padding: 5px;display: none">
                                            <input type="text" readOnly="true" name="appraisalManagerRating<%=i%>" id="appraisalManagerRating<%=i%>"   class="inputTextBlue2" value="<%=appraisalManagerRating%>" style="text-align: right;background-color:#dddddd;color:#000;" onchange="isNumeric(this);weightageCalculation(<%=i%>);"/>
                                        </td>
                                        <td class="fieldValue" align="center" style="border: 1px solid #ddd;padding: 5px;display: none"> 
                                            <input type="text" readOnly="true" name="calWeightage<%=i%>" id="calWeightage<%=i%>" required="true"  class="inputTextBlue2" value="<%=calWeightage%>" onKeyPress="isNumeric(this);" style="text-align: right;background-color:#dddddd;color:#000;"/>
                                        </td>
                                        <% }



                                        %>
                                        </tr>
                                        <%                                           }// for loop close
                                            } // main if close %>

                                        <tr  bgcolor="#3E93D4">
                                        <th style="color:#FFF;border: 1px solid #ddd;padding: 5px;" colspan="2" rowspan="2">
                                            &nbsp;
                                        </th>



                                        <th align="center" style="border: 1px solid #ddd;padding: 5px;">
                                            <input type="text" readonly name="totalKeyFactor" id="totalKeyFactor" required="true"  class="inputTextBlue2" value="" style="background-color:#B0C4DE;color:#000;border:1px solid #FFFAF0;font-weight: bold;text-align: center;display: none;"  />
                                        </th>

                                        <th colspan="" style="color:#00008B;border: 1px solid #ddd;padding: 5px;display: none;">

                                        </th>
                                        <th align="center" style="border: 1px solid #ddd;padding: 5px;">
                                            <input style="display: none;" type="text" name="totalEmpRating" id="totalEmpRating" required="true"  class="inputTextBlue2" value="" style="background-color:#B0C4DE;color:#000;border:1px solid #FFFAF0;font-weight: bold;text-align: center;" onKeyPress="isNumeric(this);" />
                                        </th>
                                        <%
                                            if (request.getAttribute("status") != null && request.getAttribute("status").toString().equals("Approved")) {
                                        %>
                                        <th colspan="" style="color:#00008B;border: 1px solid #ddd;padding: 5px;display: none;">

                                        </th>
                                        <th align="center" style="border: 1px solid #ddd;padding: 5px;display: none">
                                            <input style="display: none;" type="text" name="totalMgrRating" id="totalMgrRating" required="true"  class="inputTextBlue2" value="" style="background-color:#B0C4DE;color:#000;border:1px solid #FFFAF0;font-weight: bold;text-align: center;" />
                                        </th>
                                        <th align="center" style="border: 1px solid #ddd;padding: 5px;display: none">
                                            <input type="text" readOnly="true" name="totalCalWeightage" id="totalCalWeightage" required="true"  class="inputTextBlue2" value="" style="background-color:#B0C4DE;color:#000;border:1px solid #FFFAF0;font-weight: bold;text-align: center"  />
                                        </th>
                                        <% }%>
                                        </tr>

                                    </table>
                                    <table cellpadding="0" cellspacing="0" border="0" width="90%" align="center">
                                        <tr>
                                        <td colspan="10">
                                            <input type="button" class="button_payroll"  value="Personality" onclick="addPersonalOverlay();" />
                                            <input type="button" class="button_payroll"  value="Goals" onclick="addGoalsOverlay();" />

                                        </td>
                                        <td  style="text-align: center;width: 344px;"><font color="red" size="2px" face="arial">*Note:&nbsp;After&nbsp;submitting&nbsp;this&nbsp;quarterly&nbsp;review&nbsp;you&nbsp;can't&nbsp;edit&nbsp;again.</font></td>
                                        
                                        <s:if test="%{appraisalId==0}">
                                            <td colspan="10" align="right">

                                            <input type="button" class="buttonBg"  value="Save" onclick="quarterAppraisalAdd('Entered',this);"  id="finalSaveButton"/>

                                            <input type="button" class="buttonBg"  value="Submit" onclick="quarterAppraisalAdd('Submitted',this);" />
                                        </td>
                                        </tr>
                                        </s:if>
                                        <s:else>
                                            <s:if test="%{dayCount==0}">
                                               <s:if test="%{status=='Entered'}">
                                                <td colspan="10" align="right">

                                            <input type="button" class="buttonBg"  value="Save" onclick="quarterAppraisalAdd('Entered',this);"  id="finalSaveButton"/>

                                            <input type="button" class="buttonBg"  value="Submit" onclick="quarterAppraisalAdd('Submitted',this);" />
                                        </td>

                                            </s:if>

                                            <s:if test="%{status=='Submitted'}">
                                                <td colspan="10" align="right">
                                                <lable style="color:green">Manager to approve ..</lable>
                                                </td>

                                            </s:if>
                                            </s:if>
                                            
                                            <s:if test="%{dayCount==1}">

                                            <s:if test="%{status=='Approved'}">
                                                <td colspan="10" align="right">
                                                <lable style="color:green">Operation team has to be approve..</lable>
                                                </td>

                                            </s:if>
                                            <s:if test="%{status=='Rejected'}">
                                                 <td colspan="10" align="right">

                                            <input type="button"  class="buttonBg"  value="Save" onclick="quarterAppraisalAdd('Entered',this);"  id="finalSaveButton"/>

                                            <input type="button"  disabled  class="buttonBg"  value="Submit" onclick="quarterAppraisalAdd('Submitted',this);" />
                                            <input type="button" class="buttonBg"  value="ReSubmit" onclick="quarterAppraisalAdd('Resubmitted',this);" />
                                           <lable style="color:green">Rejected..</lable> <a class="underscore" href='javascript:showRejectedCommentsOverlay("managerRejectedComments")'>
                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/ecertification/wrong.jpg" 
                                                 width="13" height="13" border="0" ></a>
                                        </td>

                                            </s:if>
                                            <s:if test="%{status=='Entered'}">
                                                <td colspan="10" align="right">

                                            <input type="button" class="buttonBg"  value="Save" onclick="quarterAppraisalAdd('Entered',this);"  id="finalSaveButton"/>

                                            <input type="button" class="buttonBg"  value="Submit" onclick="quarterAppraisalAdd('Submitted',this);" />
                                        </td>

                                            </s:if>

                                            <s:if test="%{status=='Submitted'}">
                                                <td colspan="10" align="right">
                                                <lable style="color:green">Manager to approve ..</lable>
                                                </td>

                                            </s:if>
                                        </s:if>

                                        <s:if test="%{dayCount==2}">
                                            <s:if test="%{operationTeamStatus=='Approved'}">
                                                <td colspan="10" align="center">
                                                <lable style="color:green">Approved..</lable>
                                                </td>
                                            </s:if>
                                                <s:if test="%{operationTeamStatus=='Closed'}">
                                                <td colspan="10" align="center">
                                                <lable style="color:green">Closed..</lable>
                                                </td>
                                            </s:if>
                                            <s:if test="%{operationTeamStatus=='Rejected'}">
                                                 <td colspan="10" align="right">
                                                <lable style="color:green">Rejected by operation team..</lable>
                                                </td>
                                            </s:if>
                                            </tr>
                                            <s:if test="%{status=='Rejected'}">
                                                <tr>
                                        <td colspan="20" align="right">
                                        <lable style="color:green">Rejected..</lable><a class="underscore" href='javascript:showRejectedCommentsOverlay("managerRejectedComments")'>
                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/ecertification/wrong.jpg" 
                                                 width="13" height="13" border="0" ></a>
                                        </td>
                                        </tr>
                                            </s:if>
                                        </s:if>
                                       
                                        
                                        

                                        </s:else>

                                    </table>
                                </s:elseif><%-- my role close --%>
                                <%-- team role start --%>
                                <s:elseif test="%{#session.roleName == 'Employee' && curretRole=='team'}">
                                    <table cellpadding="0" cellspacing="0" border="0" width="90%" align="center" id="measurementTable" style="border: 1px solid #ddd; border-collapse: collapse;">
                                        <br>
                                        <tr  bgcolor="#3E93D4">
                                        <th style="color:#FFF;border: 1px solid #ddd;font-size: 12px;" rowspan="2" >
                                            Measurement
                                        </th>
                                        <th style="color:#FFF;border: 1px solid #ddd;padding: 5px;font-size: 12px;" rowspan="2">
                                            Key&nbsp;Factor
                                        </th>

                                        <th style="color:#FFF;border: 1px solid #ddd;padding: 5px;font-size: 12px;display: none;" rowspan="2">
                                            Weightage
                                        </th>


                                        <th  style="color:#FFF;border: 1px solid #ddd;padding: 5px;font-size: 12px;">
                                            Employee
                                        </th>

                                        <th colspan="2" style="color:#FFF;border: 1px solid #ddd;padding: 5px;font-size: 12px;">
                                            Manager
                                        </th>
                                        <th style="color:#FFF;border: 1px solid #ddd;font-size: 12px;padding: 5px;display: none;" rowspan="2">
                                            Calculated<br>Weightage
                                        </th>

                                        </tr>

                                        <tr style="color:#FFF" bgcolor="#3E93D4"> 



                                        <th colspan="" style="font-weight:normal;border: 1px solid #ddd;padding: 5px;font-size: 12px;">
                                            Desc
                                        </th>
                                        <th colspan="" style="font-weight:normal;border: 1px solid #ddd;padding: 5px;font-size: 12px;display: none;">
                                            Rating<s:label class="navigationText" value="(1-10)" style="font-size: 10px;color:#FFF;"/>
                                        </th>

                                        <th colspan="" style="font-weight:normal;border: 1px solid #ddd;padding: 5px;font-size: 12px;">
                                            Comments
                                        </th>
                                        <th colspan="" style="font-weight:normal;border: 1px solid #ddd;padding: 5px;font-size: 12px;">
                                            Rating<s:label class="navigationText" value="(1-10)" style="font-size: 10px;color:#FFF;"/>
                                        </th>


                                        </tr>
                                        <% if (request.getAttribute("QuarterAppraisalDetails") != null) {
                                                List deliveryKeyFactorsList = (List) request.getAttribute("deliveryKeyFactorsList");
                                                String QuarterAppraisalDetails = (String) request.getAttribute("QuarterAppraisalDetails");
                                                String[] individualRowDetails = QuarterAppraisalDetails.split(Pattern.quote("*@!"));

                                                for (int i = 1; i <= individualRowDetails.length; i++) {
                                                    String[] individualColumn = individualRowDetails[i - 1].split(Pattern.quote("#^$"));

                                        %>
                                        <tr style="height: 35px;" bgcolor="#FFF">
                                            <% if (!individualColumn[0].equals("_")) {%>
                                        <td class="fieldValue"  style="text-align: center;border: 1px solid #ddd;padding: 5px;text-color: #000" rowspan="<%=individualColumn[1]%>"><%=individualColumn[0]%>
                                            <input type="hidden" id="measurementTittle<%=i%>" name="measurementTittle<%=i%>" value="<%=individualColumn[0]%>"/>
                                        </td><%}%>
                                        <td class="fieldValue" style="text-align: left;border: 1px solid #ddd;padding: 5px;"><%=individualColumn[4]%><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/info.jpg"  height="18" width="20" style="width: 15px; height: 15px; margin-bottom: -4px;" title="<%=individualColumn[12]%>" > </td>
                                        <input type="hidden" id="measurementId<%=i%>" name="measurementId<%=i%>" value="<%=individualColumn[2]%>"/>
                                        <input type="hidden" id="keyFactorId<%=i%>" name="keyFactorId<%=i%>" value="<%=individualColumn[3]%>" />
                                        <input type="hidden" id="keyFactorName<%=i%>" name="keyFactorName<%=i%>" value="<%=individualColumn[4]%>" />
                                        <td align="center" style="border: 1px solid #ddd;padding: 5px;display: none;"> 
                                            <input type="text" name="keyFactorWeightage<%=i%>" id="keyFactorWeightage<%=i%>" required="true"  class="inputTextBlue2" value="<%=individualColumn[5]%> " style="text-align: right;background-color:#dddddd;color:#000;" readonly="true"/>
                                        </td>

                                        <td class="fieldValue" align="center" style="border: 1px solid #ddd;padding: 5px;"> 


                                        <button type="button" id="empDescription<%=i%>btn" class="buttonq" title="<%=individualColumn[6]%>" onclick="addCommentsForQAppraisal('Employee',<%=i%>);">View</button>

                                        <input type="hidden" id="empDescription<%=i%>" name="empDescription<%=i%>" value="<%=individualColumn[6]%>"/>
                                        <input type="hidden" id="id<%=i%>" name="id<%=i%>" value="<%=individualColumn[11]%>"/>
                                        </td>
                                        <td class="fieldValue" align="center" style="border: 1px solid #ddd;padding: 5px;display: none;">
                                            <input type="text" readOnly="true" tabindex="<%=i%>" name="empRating<%=i%>" id="empRating<%=i%>" required="true" style="text-align: right;background-color:#dddddd;color:#000;" class="inputTextBlue2" value="<%=individualColumn[7]%>" onchange="isNumeric(this);totalQuarterCalculations();"/>

                                        </td>


                                        <s:if test="%{dayCount==0}">
                                            <%
                                                appraisalManagerRating = individualColumn[9];
                                                calWeightage = individualColumn[10];
                                                if (individualColumn[9].equals("0")) {
                                                    appraisalManagerRating = "";
                                                }
                                                if (individualColumn[10].equals("0")) {
                                                    calWeightage = "";
                                                }

                                            %>
                                            <s:if test="%{status=='Submitted'}">
                                                <td class="fieldValue" align="center" style="border: 1px solid #ddd;padding: 5px;">
                                                    <%if (individualColumn[8].equals("")) {%>
                                                <button type="button" tabindex="<%=i%>" id="mgrComments<%=i%>btn" class="buttonq" onclick="addCommentsForQAppraisal('Manager',<%=i%>);"  >Add</button>
                                                <%} else {%>    
                                                <button type="button" tabindex="<%=i%>" id="mgrComments<%=i%>btn" class="buttonq" onclick="addCommentsForQAppraisal('Manager',<%=i%>);" title="<%=individualColumn[8]%>" >Update</button>

                                                <%}%>
                                                <input type="hidden" id="mgrComments<%=i%>" name="mgrComments<%=i%>" value="<%=individualColumn[8]%>"/>    
                                                </td>   
                                                <td class="fieldValue" align="center" style="border: 1px solid #ddd;padding: 5px;">
                                                    <input type="text"  name="appraisalManagerRating<%=i%>" id="appraisalManagerRating<%=i%>"   class="inputTextBlue2" value="<%=appraisalManagerRating%>" style="text-align: right;" onchange="isNumeric(this);weightageCalculation(<%=i%>);"/>
                                                </td>
                                            </s:if>

                                            <s:if test="%{status=='Entered'}">
                                                <td class="fieldValue" align="center" style="border: 1px solid #ddd;padding: 5px;">
                                                <button type="button" tabindex="<%=i%>" id="mgrComments<%=i%>btn" class="buttonq" onclick="addCommentsForQAppraisal('Manager',<%=i%>);" title="<%=individualColumn[8]%>" >View</button>
                                                <input type="hidden" id="mgrComments<%=i%>" name="mgrComments<%=i%>" value="<%=individualColumn[8]%>"/>
                                                </td>
                                                <td class="fieldValue" align="center" style="border: 1px solid #ddd;padding: 5px;">
                                                    <input type="text" readOnly="true" name="appraisalManagerRating<%=i%>" id="appraisalManagerRating<%=i%>"   class="inputTextBlue2" value="<%=appraisalManagerRating%>" style="text-align: right;background-color:#dddddd;color:#000;" onchange="isNumeric(this);weightageCalculation(<%=i%>);"/>
                                                </td>
                                            </s:if>
                                        </s:if>
                                        <s:if test="%{dayCount==1}">
                                            <%
                                                appraisalManagerRating = individualColumn[9];
                                                calWeightage = individualColumn[10];
                                                if (individualColumn[9].equals("0")) {
                                                    appraisalManagerRating = "";
                                                }
                                                if (individualColumn[10].equals("0")) {
                                                    calWeightage = "";
                                                }

                                            %>

                                            <s:if test="%{status=='Entered' || status=='Approved' || status=='Rejected' }">
                                                <td class="fieldValue" align="center" style="border: 1px solid #ddd;padding: 5px;">
                                                <button type="button" tabindex="<%=i%>" id="mgrComments<%=i%>btn" class="buttonq" onclick="addCommentsForQAppraisal('Manager',<%=i%>);" title="<%=individualColumn[8]%>" >View</button>
                                                <input type="hidden" id="mgrComments<%=i%>" name="mgrComments<%=i%>" value="<%=individualColumn[8]%>"/>
                                                </td>
                                                <td class="fieldValue" align="center" style="border: 1px solid #ddd;padding: 5px;">
                                                    <input type="text" readOnly="true" name="appraisalManagerRating<%=i%>" id="appraisalManagerRating<%=i%>"   class="inputTextBlue2" value="<%=appraisalManagerRating%>" style="text-align: right;background-color:#dddddd;color:#000;" onchange="isNumeric(this);weightageCalculation(<%=i%>);"/>
                                                </td>
                                            </s:if>
                                            <s:else>
                                            <s:if test="%{(status=='Approved' && operationTeamStatus=='Rejected') || status=='Submitted'}">
                                                <td class="fieldValue" align="center" style="border: 1px solid #ddd;padding: 5px;">
                                                    <%if (individualColumn[8].equals("")) {%>
                                                <button type="button" tabindex="<%=i%>" id="mgrComments<%=i%>btn" class="buttonq" onclick="addCommentsForQAppraisal('Manager',<%=i%>);"  >Add</button>
                                                <%} else {%>    
                                                <button type="button" tabindex="<%=i%>" id="mgrComments<%=i%>btn" class="buttonq" onclick="addCommentsForQAppraisal('Manager',<%=i%>);" title="<%=individualColumn[8]%>" >Update</button>

                                                <%}%>
                                                <input type="hidden" id="mgrComments<%=i%>" name="mgrComments<%=i%>" value="<%=individualColumn[8]%>"/>    
                                                </td>   
                                                <td class="fieldValue" align="center" style="border: 1px solid #ddd;padding: 5px;">
                                                    <input type="text"  name="appraisalManagerRating<%=i%>" id="appraisalManagerRating<%=i%>"   class="inputTextBlue2" value="<%=appraisalManagerRating%>" style="text-align: right;" onchange="isNumeric(this);weightageCalculation(<%=i%>);"/>
                                                </td>
                                            </s:if>
                                                </s:else>

                                            
                                        </s:if>
                                                <s:if test="%{dayCount==2}">
                                            <%
                                                appraisalManagerRating = individualColumn[9];
                                                calWeightage = individualColumn[10];
                                                if (individualColumn[9].equals("0")) {
                                                    appraisalManagerRating = "";
                                                }
                                                if (individualColumn[10].equals("0")) {
                                                    calWeightage = "";
                                                }

                                            %>


                                            <s:if test="%{status=='Submited' || (status=='Approved' && operationTeamStatus=='Rejected')}">
                                                <td class="fieldValue" align="center" style="border: 1px solid #ddd;padding: 5px;">
                                                    <%if (individualColumn[8].equals("")) {%>
                                                <button type="button" tabindex="<%=i%>" id="mgrComments<%=i%>btn" class="buttonq" onclick="addCommentsForQAppraisal('Manager',<%=i%>);"  >Add</button>
                                                <%} else {%>    
                                                <button type="button" tabindex="<%=i%>" id="mgrComments<%=i%>btn" class="buttonq" onclick="addCommentsForQAppraisal('Manager',<%=i%>);" title="<%=individualColumn[8]%>" >Update</button>

                                                <%}%>
                                                <input type="hidden" id="mgrComments<%=i%>" name="mgrComments<%=i%>" value="<%=individualColumn[8]%>"/>    
                                                </td>   
                                                <td class="fieldValue" align="center" style="border: 1px solid #ddd;padding: 5px;">
                                                    <input type="text"  name="appraisalManagerRating<%=i%>" id="appraisalManagerRating<%=i%>"   class="inputTextBlue2" value="<%=appraisalManagerRating%>" style="text-align: right;" onchange="isNumeric(this);weightageCalculation(<%=i%>);"/>
                                                </td>
                                            </s:if>
                                                
                                            <s:else>
                                                <td class="fieldValue" align="center" style="border: 1px solid #ddd;padding: 5px;">
                                                <button type="button" tabindex="<%=i%>" id="mgrComments<%=i%>btn" class="buttonq" onclick="addCommentsForQAppraisal('Manager',<%=i%>);" title="<%=individualColumn[8]%>" >View</button>
                                                <input type="hidden" id="mgrComments<%=i%>" name="mgrComments<%=i%>" value="<%=individualColumn[8]%>"/>
                                                </td>
                                                <td class="fieldValue" align="center" style="border: 1px solid #ddd;padding: 5px;">
                                                    <input type="text" readOnly="true" name="appraisalManagerRating<%=i%>" id="appraisalManagerRating<%=i%>"   class="inputTextBlue2" value="<%=appraisalManagerRating%>" style="text-align: right;background-color:#dddddd;color:#000;" onchange="isNumeric(this);weightageCalculation(<%=i%>);"/>
                                                </td>
                                            </s:else>
                                        </s:if>
                                        <td class="fieldValue" align="center" style="border: 1px solid #ddd;padding: 5px;display: none;"> 
                                            <input type="text" readOnly="true" name="calWeightage<%=i%>" id="calWeightage<%=i%>" required="true"  class="inputTextBlue2" value="<%=calWeightage%>" onKeyPress="isNumeric(this);" style="text-align: right;background-color:#dddddd;color:#000;"/>
                                        </td>

                                        </tr>
                                        <%                                           } // for loop close
                                            } // main if close%>

                                        <tr  bgcolor="#3E93D4" style="display: none;">
                                        <th style="color:#FFF;border: 1px solid #ddd;padding: 5px;" colspan="2">
                                            Total
                                        </th>



                                        <th align="center" style="border: 1px solid #ddd;padding: 5px;">
                                            <input type="text" readonly name="totalKeyFactor" id="totalKeyFactor" required="true"  class="inputTextBlue2" value="" style="background-color:#B0C4DE;color:#000;border:1px solid #FFFAF0;font-weight: bold;text-align: center;"  />
                                        </th>

                                        <th colspan="" style="color:#00008B;border: 1px solid #ddd;padding: 5px;">

                                        </th>
                                        <th align="center" style="border: 1px solid #ddd;padding: 5px;">
                                            <input style="display: none;" type="text" name="totalEmpRating" id="totalEmpRating" required="true"  class="inputTextBlue2" value="" style="background-color:#B0C4DE;color:#000;border:1px solid #FFFAF0;font-weight: bold;text-align: center;" onKeyPress="isNumeric(this);" />
                                        </th>

                                        <th colspan="" style="color:#00008B;border: 1px solid #ddd;padding: 5px;">

                                        </th>
                                        <th align="center" style="border: 1px solid #ddd;padding: 5px;">
                                            <input style="display: none;" type="text" name="totalMgrRating" id="totalMgrRating" required="true"  class="inputTextBlue2" value="" style="background-color:#B0C4DE;color:#000;border:1px solid #FFFAF0;font-weight: bold;text-align: center;" />
                                        </th>
                                        <th align="center" style="border: 1px solid #ddd;padding: 5px;">
                                            <input type="text" readOnly="true" name="totalCalWeightage" id="totalCalWeightage" required="true"  class="inputTextBlue2" value="" style="background-color:#B0C4DE;color:#000;border:1px solid #FFFAF0;font-weight: bold;text-align: center"  />
                                        </th>

                                        </tr>

                                    </table>
                                    <table cellpadding="0" cellspacing="0" border="0" width="90%" align="center">
                                        <tr>
                                        <td>
                                            <input type="button" class="button_payroll"  value="Personality" onclick="addPersonalOverlay();" />
                                            <input type="button" class="button_payroll"  value="Goals" onclick="addGoalsOverlay();" />

                                        </td>

                                        <s:if test="%{dayCount==0}">

                                            <s:if test="%{status=='Entered'}">
                                                <td colspan="10" align="right">
                                                <lable style="color:green">Employee has to be submit  the review..</lable>
                                                </td>

                                            </s:if>

                                            <s:if test="%{status=='Submitted'}">
                                                <td colspan="10" align="right">
                                                    <input type="button" class="buttonBg"  value="Save" onclick="quarterAppraisalAdd('MSaved',this);" />
                                                    <input type="button" class="buttonBg"  value="Reject" onclick="toggleRejectedCommentsOverlay();" />

                                                    <input type="button" class="buttonBg"  value="Approve" onclick="quarterAppraisalAdd('Approved',this);" />
                                                </td>

                                            </s:if>
                                        </s:if>
                                        <s:if test="%{dayCount==1}">

                                            <s:if test="%{status=='Approved'}">
                                                <td colspan="10" align="right">
                                                <lable style="color:green">To be approved by operation team..</lable>
                                                </td>

                                            </s:if>
                                            <s:if test="%{status=='Rejected'}">
                                                <td colspan="10" align="right">
                                                <lable style="color:green">Employee has to be resubmit  the review..</lable>
                                                </td>

                                            </s:if>
                                            <s:if test="%{status=='Entered'}">
                                                <td colspan="10" align="right">
                                                <lable style="color:green">Employee has to be submit  the review..</lable>
                                                </td>

                                            </s:if>

                                            <s:if test="%{status=='Submitted'}">
                                                <td colspan="10" align="right">
                                                    <input type="button" class="buttonBg"  value="Save" onclick="quarterAppraisalAdd('MSaved',this);" />
                                                    <input type="button" class="buttonBg"  value="Reject" onclick="toggleRejectedCommentsOverlay();" />

                                                    <input type="button" class="buttonBg"  value="Approve" onclick="quarterAppraisalAdd('Approved',this);" />
                                                </td>

                                            </s:if>
                                        </s:if>

                                        <s:if test="%{dayCount==2}">
                                            <s:if test="%{operationTeamStatus=='Approved'}">
                                                <td colspan="10" align="center">
                                                <lable style="color:green">Approved..</lable>
                                                </td>
                                            </s:if>
                                                <s:if test="%{operationTeamStatus=='Closed'}">
                                                <td colspan="10" align="center">
                                                <lable style="color:green">Closed..</lable>
                                                </td>
                                            </s:if>
                                            <s:if test="%{operationTeamStatus=='Rejected'}">
                                                <td colspan="10" align="right">
                                                    <input type="button" class="buttonBg"  value="Save" onclick="quarterAppraisalAdd('MSaved',this);" />
                                                    <input type="button"  class="buttonBg"  value="Reject" onclick="toggleRejectedCommentsOverlay();" />

                                                    <input type="button" disabled class="buttonBg"  value="Approve" onclick="quarterAppraisalAdd('Approved',this);" />
                                                    <input type="button" class="buttonBg"  value="ReApprove" onclick="quarterAppraisalAdd('Reapproved',this);" />
                                                </td>
                                            </s:if>
                                            </tr>
                                            <s:if test="%{operationTeamStatus=='Rejected'}">
                                                <tr>
                                                <td colspan="20" align="right">
                                                <lable style="color:green">Rejected..</lable><a class="underscore" href='javascript:showRejectedCommentsOverlay("operationRejectedComments")'>
                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/ecertification/wrong.jpg" 
                                                         width="13" height="13" border="0" ></a>
                                                </td>
                                                </tr>
                                            </s:if>
                                        </s:if>





                                    </table>

                                </s:elseif> <%-- team role close --%>
                                <%-- operation role start --%>
                                <s:else>
                                    <s:if test="%{accessCount==1}">
                                    <table cellpadding="0" cellspacing="0" border="0" width="90%" align="center" id="measurementTable" style="border: 1px solid #ddd; border-collapse: collapse;">
                                        <br>
                                        <tr  bgcolor="#3E93D4">
                                        <th style="color:#FFF;border: 1px solid #ddd;font-size: 12px;" rowspan="2" >
                                            Measurement
                                        </th>
                                        <th style="color:#FFF;border: 1px solid #ddd;padding: 5px;font-size: 12px;" rowspan="2">
                                            Key&nbsp;Factor
                                        </th>

                                        <th style="color:#FFF;border: 1px solid #ddd;padding: 5px;font-size: 12px;" rowspan="2">
                                            Weightage
                                        </th>


                                        <th colspan="2" style="color:#FFF;border: 1px solid #ddd;padding: 5px;font-size: 12px;">
                                            Employee 
                                        </th>

                                        <th colspan="2" style="color:#FFF;border: 1px solid #ddd;padding: 5px;font-size: 12px;">
                                            Manager
                                        </th>
                                        <th style="color:#FFF;border: 1px solid #ddd;font-size: 12px;padding: 5px;" rowspan="2">
                                            Calculated<br>Weightage
                                        </th>

                                        </tr>

                                        <tr style="color:#FFF" bgcolor="#3E93D4"> 



                                        <th colspan="" style="font-weight:normal;border: 1px solid #ddd;padding: 5px;font-size: 12px;">
                                            Desc
                                        </th>
                                        <th colspan="" style="font-weight:normal;border: 1px solid #ddd;padding: 5px;font-size: 12px;">
                                            Rating<s:label class="navigationText" value="(1-10)" style="font-size: 10px;color:#FFF;"/>
                                        </th>

                                        <th colspan="" style="font-weight:normal;border: 1px solid #ddd;padding: 5px;font-size: 12px;">
                                            Comments
                                        </th>
                                        <th colspan="" style="font-weight:normal;border: 1px solid #ddd;padding: 5px;font-size: 12px;">
                                            Rating<s:label class="navigationText" value="(1-10)" style="font-size: 10px;color:#FFF;"/>
                                        </th>


                                        </tr>
                                        <% if (request.getAttribute("QuarterAppraisalDetails") != null) {
                                                List deliveryKeyFactorsList = (List) request.getAttribute("deliveryKeyFactorsList");
                                                String QuarterAppraisalDetails = (String) request.getAttribute("QuarterAppraisalDetails");
                                                String[] individualRowDetails = QuarterAppraisalDetails.split(Pattern.quote("*@!"));

                                                for (int i = 1; i <= individualRowDetails.length; i++) {
                                                    String[] individualColumn = individualRowDetails[i - 1].split(Pattern.quote("#^$"));

                                        %>
                                        <tr style="height: 35px;" bgcolor="#FFF">
                                            <% if (!individualColumn[0].equals("_")) {%>
                                        <td class="fieldValue"  style="text-align: center;border: 1px solid #ddd;padding: 5px;text-color: #000" rowspan="<%=individualColumn[1]%>"><%=individualColumn[0]%>
                                            <input type="hidden" id="measurementTittle<%=i%>" name="measurementTittle<%=i%>" value="<%=individualColumn[0]%>"/>
                                        </td><%}%>
                                        <td class="fieldValue" style="text-align: left;border: 1px solid #ddd;padding: 5px;"><%=individualColumn[4]%><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/info.jpg"  height="18" width="20" style="width: 15px; height: 15px; margin-bottom: -4px;" title="<%=individualColumn[12]%>" > </td>
                                        <input type="hidden" id="measurementId<%=i%>" name="measurementId<%=i%>" value="<%=individualColumn[2]%>"/>
                                        <input type="hidden" id="keyFactorId<%=i%>" name="keyFactorId<%=i%>" value="<%=individualColumn[3]%>" />
                                        <input type="hidden" id="keyFactorName<%=i%>" name="keyFactorName<%=i%>" value="<%=individualColumn[4]%>" />
                                        <td class="fieldValue" align="center" style="border: 1px solid #ddd;padding: 5px;"> 
                                            <input type="text" name="keyFactorWeightage<%=i%>" id="keyFactorWeightage<%=i%>" required="true"  class="inputTextBlue2" value="<%=individualColumn[5]%> " style="text-align: right;background-color:#dddddd;color:#000;" readonly="true"/>
                                        </td>



                                        <td class="fieldValue" align="center" style="border: 1px solid #ddd;padding: 5px;"> 
                                        <button type="button" id="empDescription<%=i%>btn" class="buttonq" title="<%=individualColumn[6]%>" onclick="addCommentsForQAppraisal('Employee',<%=i%>);">View</button></td>

                                        <input type="hidden" id="empDescription<%=i%>" name="empDescription<%=i%>" value="<%=individualColumn[6]%>"/>
                                        <input type="hidden" id="id<%=i%>" name="id<%=i%>" value="<%=individualColumn[11]%>"/>
                                        <td class="fieldValue" align="center" style="border: 1px solid #ddd;padding: 5px;">

                                            <input type="text" readOnly="true" tabindex="<%=i%>" name="empRating<%=i%>" value="<%=individualColumn[7]%>" id="empRating<%=i%>"  style="text-align: right;background-color:#dddddd;color:#000;" class="inputTextBlue2" value="<%=individualColumn[7]%>" onchange="isNumeric(this);totalQuarterCalculations();"/>

                                        </td>

                                        <td class="fieldValue" align="center" style="border: 1px solid #ddd;padding: 5px;">
                                        <button type="button" tabindex="<%=i%>" id="mgrComments<%=i%>btn"  title="<%=individualColumn[8]%>" class="buttonq" onclick="addCommentsForQAppraisal('Manager',<%=i%>);" title="<%=individualColumn[8]%>" >View</button></td>
                                        <input type="hidden" id="mgrComments<%=i%>" name="mgrComments<%=i%>" value="<%=individualColumn[8]%>"/>
                                        <%
                                            appraisalManagerRating = individualColumn[9];
                                            calWeightage = individualColumn[10];
                                            if (individualColumn[9].equals("0")) {
                                                appraisalManagerRating = "";
                                            }
                                            if (individualColumn[10].equals("0")) {
                                                calWeightage = "";
                                            }

                                        %>
                                        <td class="fieldValue" align="center" style="border: 1px solid #ddd;padding: 5px;">
                                            <input type="text" readOnly="true" required="true" name="appraisalManagerRating<%=i%>" id="appraisalManagerRating<%=i%>"   class="inputTextBlue2" value="<%=appraisalManagerRating%>" style="text-align: right;background-color:#dddddd;color:#000;" onchange="isNumeric(this);weightageCalculation(<%=i%>);"/>
                                        </td>
                                        <td class="fieldValue" align="center" style="border: 1px solid #ddd;padding: 5px;"> 
                                            <input type="text" readOnly="true" name="calWeightage<%=i%>" id="calWeightage<%=i%>" required="true"  class="inputTextBlue2" value="<%=calWeightage%>" onKeyPress="isNumeric(this);" style="text-align: right;background-color:#dddddd;color:#000;"/>
                                        </td>

                                        </tr>
                                        <%                                           }
                                            }%>

                                        <tr  bgcolor="#3E93D4">
                                        <th style="color:#FFF;border: 1px solid #ddd;padding: 5px;" colspan="2">
                                            Total
                                        </th>



                                        <th align="center" style="border: 1px solid #ddd;padding: 5px;">
                                            <input type="text" readOnly="true" name="totalKeyFactor" id="totalKeyFactor" required="true"  class="inputTextBlue2" value="" style="background-color:#B0C4DE;color:#000;border:1px solid #FFFAF0;font-weight: bold;text-align: center;"  />
                                        </th>

                                        <th colspan="" style="color:#00008B;border: 1px solid #ddd;padding: 5px;">

                                        </th>
                                        <th align="center" style="border: 1px solid #ddd;padding: 5px;">
                                            <input style="display: none;" type="text" name="totalEmpRating" id="totalEmpRating" required="true"  class="inputTextBlue2" value="" style="background-color:#B0C4DE;color:#000;border:1px solid #FFFAF0;font-weight: bold;text-align: center;" onKeyPress="isNumeric(this);" />
                                        </th>
                                        <%
                                            if ((session.getAttribute(ApplicationConstants.SESSION_IS_USER_MANAGER).toString().equals("1") && request.getAttribute("curretRole").equals("team")) || request.getAttribute("status") != null && request.getAttribute("status").toString().equals("Approved")) {
                                        %>
                                        <th colspan="" style="color:#00008B;border: 1px solid #ddd;padding: 5px;">

                                        </th>
                                        <th align="center" style="border: 1px solid #ddd;padding: 5px;">
                                            <input style="display: none;" type="text" name="totalMgrRating" id="totalMgrRating" required="true"  class="inputTextBlue2" value="" style="background-color:#B0C4DE;color:#000;border:1px solid #FFFAF0;font-weight: bold;text-align: center;" />
                                        </th>
                                        <th align="center" style="border: 1px solid #ddd;padding: 5px;">
                                            <input type="text" readOnly="true" name="totalCalWeightage" id="totalCalWeightage" required="true"  class="inputTextBlue2" value="" style="background-color:#B0C4DE;color:#000;border:1px solid #FFFAF0;font-weight: bold;text-align: center"  />
                                        </th>
                                        <% }%>
                                        </tr>

                                    </table>
                                    </s:if><s:else>
                                        <table cellpadding="0" cellspacing="0" border="0" width="90%" align="center" id="measurementTable" style="border: 1px solid #ddd; border-collapse: collapse;">
                                        <br>
                                        <tr  bgcolor="#3E93D4">
                                        <th style="color:#FFF;border: 1px solid #ddd;font-size: 12px;" rowspan="2" >
                                            Measurement
                                        </th>
                                        <th style="color:#FFF;border: 1px solid #ddd;padding: 5px;font-size: 12px;" rowspan="2">
                                            Key&nbsp;Factor
                                        </th>

                                        <th style="color:#FFF;border: 1px solid #ddd;padding: 5px;font-size: 12px;display: none;" rowspan="2">
                                            Weightage
                                        </th>


                                        <th  style="color:#FFF;border: 1px solid #ddd;padding: 5px;font-size: 12px;" rowspan="2">
                                            Employee  Desc
                                        </th>

                                        <th  style="color:#FFF;border: 1px solid #ddd;padding: 5px;font-size: 12px;" rowspan="2">
                                            Manager comments
                                        </th>
                                        <th style="color:#FFF;border: 1px solid #ddd;font-size: 12px;padding: 5px;display: none " rowspan="2">
                                            Calculated<br>Weightage
                                        </th>

                                        </tr>

                                        <tr style="color:#FFF;" bgcolor="#3E93D4;" > 



                                        <th colspan="" style="font-weight:normal;border: 1px solid #ddd;padding: 5px;font-size: 12px;display: none">
                                            Desc
                                        </th>
                                        <th colspan="" style="font-weight:normal;border: 1px solid #ddd;padding: 5px;font-size: 12px;display: none">
                                            Rating<s:label class="navigationText" value="(1-10)" style="font-size: 10px;color:#FFF;"/>
                                        </th>

                                        <th colspan="" style="font-weight:normal;border: 1px solid #ddd;padding: 5px;font-size: 12px;display: none">
                                            Comments
                                        </th>
                                        <th colspan="" style="font-weight:normal;border: 1px solid #ddd;padding: 5px;font-size: 12px;display: none">
                                            Rating<s:label class="navigationText" value="(1-10)" style="font-size: 10px;color:#FFF;"/>
                                        </th>


                                        </tr>
                                        <% if (request.getAttribute("QuarterAppraisalDetails") != null) {
                                                List deliveryKeyFactorsList = (List) request.getAttribute("deliveryKeyFactorsList");
                                                String QuarterAppraisalDetails = (String) request.getAttribute("QuarterAppraisalDetails");
                                                String[] individualRowDetails = QuarterAppraisalDetails.split(Pattern.quote("*@!"));

                                                for (int i = 1; i <= individualRowDetails.length; i++) {
                                                    String[] individualColumn = individualRowDetails[i - 1].split(Pattern.quote("#^$"));

                                        %>
                                        <tr style="height: 35px;" bgcolor="#FFF">
                                            <% if (!individualColumn[0].equals("_")) {%>
                                        <td class="fieldValue"  style="text-align: center;border: 1px solid #ddd;padding: 5px;text-color: #000" rowspan="<%=individualColumn[1]%>"><%=individualColumn[0]%>
                                            <input type="hidden" id="measurementTittle<%=i%>" name="measurementTittle<%=i%>" value="<%=individualColumn[0]%>"/>
                                        </td><%}%>
                                        <td class="fieldValue" style="text-align: left;border: 1px solid #ddd;padding: 5px;"><%=individualColumn[4]%> </td>
                                        <input type="hidden" id="measurementId<%=i%>" name="measurementId<%=i%>" value="<%=individualColumn[2]%>"/>
                                        <input type="hidden" id="keyFactorId<%=i%>" name="keyFactorId<%=i%>" value="<%=individualColumn[3]%>" />
                                        <input type="hidden" id="keyFactorName<%=i%>" name="keyFactorName<%=i%>" value="<%=individualColumn[4]%>" />
                                        <td class="fieldValue" align="center" style="border: 1px solid #ddd;padding: 5px;display: none"> 
                                            <input type="text" name="keyFactorWeightage<%=i%>" id="keyFactorWeightage<%=i%>" required="true"  class="inputTextBlue2" value="<%=individualColumn[5]%> " style="text-align: right;background-color:#dddddd;color:#000;" readonly="true"/>
                                        </td>



                                        <td class="fieldValue" align="center" style="border: 1px solid #ddd;padding: 5px;"> 
                                        <button type="button" id="empDescription<%=i%>btn" class="buttonq" title="<%=individualColumn[6]%>" onclick="addCommentsForQAppraisal('Employee',<%=i%>);">View</button></td>

                                        <input type="hidden" id="empDescription<%=i%>" name="empDescription<%=i%>" value="<%=individualColumn[6]%>"/>
                                        <input type="hidden" id="id<%=i%>" name="id<%=i%>" value="<%=individualColumn[11]%>"/>
                                        <td class="fieldValue" align="center" style="border: 1px solid #ddd;padding: 5px;display: none">

                                            <input type="text" readOnly="true" tabindex="<%=i%>" name="empRating<%=i%>" value="<%=individualColumn[7]%>" id="empRating<%=i%>"  style="text-align: right;background-color:#dddddd;color:#000;" class="inputTextBlue2" value="<%=individualColumn[7]%>" onchange="isNumeric(this);totalQuarterCalculations();"/>

                                        </td>

                                        <td class="fieldValue" align="center" style="border: 1px solid #ddd;padding: 5px;">
                                        <button type="button" tabindex="<%=i%>" id="mgrComments<%=i%>btn"  title="<%=individualColumn[8]%>" class="buttonq" onclick="addCommentsForQAppraisal('Manager',<%=i%>);" title="<%=individualColumn[8]%>" >View</button></td>
                                        <input type="hidden" id="mgrComments<%=i%>" name="mgrComments<%=i%>" value="<%=individualColumn[8]%>"/>
                                        <%
                                            appraisalManagerRating = individualColumn[9];
                                            calWeightage = individualColumn[10];
                                            if (individualColumn[9].equals("0")) {
                                                appraisalManagerRating = "";
                                            }
                                            if (individualColumn[10].equals("0")) {
                                                calWeightage = "";
                                            }

                                        %>
                                        <td class="fieldValue" align="center" style="border: 1px solid #ddd;padding: 5px;display: none">
                                            <input type="text" readOnly="true" required="true" name="appraisalManagerRating<%=i%>" id="appraisalManagerRating<%=i%>"   class="inputTextBlue2" value="<%=appraisalManagerRating%>" style="text-align: right;background-color:#dddddd;color:#000;" onchange="isNumeric(this);weightageCalculation(<%=i%>);"/>
                                        </td>
                                        <td class="fieldValue" align="center" style="border: 1px solid #ddd;padding: 5px;display: none"> 
                                            <input type="text" readOnly="true" name="calWeightage<%=i%>" id="calWeightage<%=i%>" required="true"  class="inputTextBlue2" value="<%=calWeightage%>" onKeyPress="isNumeric(this);" style="text-align: right;background-color:#dddddd;color:#000;"/>
                                        </td>

                                        </tr>
                                        <%                                           }
                                            }%>

                                        <tr  bgcolor="#3E93D4">
                                        <th style="color:#FFF;border: 1px solid #ddd;padding: 5px;" colspan="2">
                                            &nbsp;
                                        </th>



                                        <th align="center" style="border: 1px solid #ddd;padding: 5px;">
                                            <input type="text" style="display: none;" readOnly="true" name="totalKeyFactor" id="totalKeyFactor" required="true"  class="inputTextBlue2" value="" style="background-color:#B0C4DE;color:#000;border:1px solid #FFFAF0;font-weight: bold;text-align: center;"  />
                                        </th>

                                        <th colspan="" style="color:#00008B;border: 1px solid #ddd;padding: 5px;">

                                        </th>
                                        <th align="center" style="border: 1px solid #ddd;padding: 5px;display: none">
                                            <input style="display: none;" type="text" name="totalEmpRating" id="totalEmpRating" required="true"  class="inputTextBlue2" value="" style="background-color:#B0C4DE;color:#000;border:1px solid #FFFAF0;font-weight: bold;text-align: center;" onKeyPress="isNumeric(this);" />
                                        </th>
                                        
                                        <th colspan="" style="color:#00008B;border: 1px solid #ddd;padding: 5px;display: none">

                                        </th>
                                        <th align="center" style="border: 1px solid #ddd;padding: 5px;display: none">
                                            <input style="display: none;" type="text" name="totalMgrRating" id="totalMgrRating" required="true"  class="inputTextBlue2" value="" style="background-color:#B0C4DE;color:#000;border:1px solid #FFFAF0;font-weight: bold;text-align: center;" />
                                        </th>
                                        <th align="center" style="border: 1px solid #ddd;padding: 5px;display: none">
                                            <input style="display: none;" type="text" readOnly="true" name="totalCalWeightage" id="totalCalWeightage" required="true"  class="inputTextBlue2" value="" style="background-color:#B0C4DE;color:#000;border:1px solid #FFFAF0;font-weight: bold;text-align: center"  />
                                        </th>
                                       
                                        </tr>

                                    </table>
                                    </s:else>
                                    <table cellpadding="0" cellspacing="0" border="0" width="90%" align="center">
                                        <tr>
                                        <td>
                                            <input type="button" class="button_payroll"  value="Personality" onclick="addPersonalOverlay();" />
                                            <input type="button" class="button_payroll"  value="Goals" onclick="addGoalsOverlay();" />

                                        </td>
                                        <s:if test="%{dayCount==0}">
                                            <s:if test="%{status=='Entered'}">
                                                <td colspan="10" align="right">
                                                <lable style="color:green">Employee has to be submit  the appraisal..</lable>
                                                </td>

                                            </s:if>

                                            <s:if test="%{status=='Submitted'}">
                                                <td colspan="10" align="right">
                                                <lable style="color:green">Manager to approve ..</lable>
                                                </td>

                                            </s:if>

                                        </s:if>
                                        <s:if test="%{dayCount==1}">

                                            <s:if test="%{status=='Approved'}">
                                                <td colspan="10" align="right">
                                                    <input type="button" class="buttonBg"  value="Reject" onclick="toggleRejectedCommentsOverlay();" />

                                                    <input type="button" class="buttonBg"  value="Approve" onclick="quarterAppraisalAdd('Closed',this);" />
                                                </td>

                                            </s:if>
                                            <s:if test="%{status=='Rejected'}">
                                                <td colspan="10" align="right">
                                                <lable style="color:green">Rejected by Manager..</lable>
                                                </td>

                                            </s:if>
                                            <s:if test="%{status=='Entered'}">
                                                <td colspan="10" align="right">
                                                <lable style="color:green">Employee has to be submit  the review..</lable>
                                                </td>

                                            </s:if>

                                            <s:if test="%{status=='Submitted'}">
                                                <td colspan="10" align="right">
                                                <lable style="color:green">Manager to approve ..</lable>
                                                </td>

                                            </s:if>
                                        </s:if>
                                        <s:if test="%{dayCount==2}">
                                            <s:if test="%{operationTeamStatus=='Approved'}">
                                                <td colspan="10" align="center">
                                                <lable style="color:green">Approved..</lable>
                                                </td>
                                            </s:if>
                                                <s:if test="%{operationTeamStatus=='Closed'}">
                                                <td colspan="10" align="center">
                                                <lable style="color:green">Closed..</lable>
                                                </td>
                                            </s:if>
                                            <s:if test="%{operationTeamStatus=='Rejected'}">
                                                <td colspan="10" align="right">
                                                <lable style="color:green">Rejected..</lable><a class="underscore" href='javascript:showRejectedCommentsOverlay("operationRejectedComments")'>
                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/ecertification/wrong.jpg" 
                                                         width="13" height="13" border="0" ></a>
                                                </td>
                                            </s:if>
                                            </tr>

                                        </s:if>

                                    </table>
                                </s:else>



                            </s:form>
                        </div>
                    </div>


                    <script type="text/javascript">

                        var countries=new ddtabcontent("surveyDetailsTabs")
                        countries.setpersist(false)
                        countries.setselectedClassTarget("link") //"link" or "linkparent"
                        countries.init()

                    </script>
                    <%-- </sx:div > --%>
                    <%-- </sx:tabbedpanel> --%>
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
<script>
    
    $(document).ready(function(){
        
       




        totalQuarterCalculations();
 
      /*  document.getElementById('totalKeyFactor').readOnly=true;
        document.getElementById('totalEmpRating').readOnly=true;
        document.getElementById('totalMgrRating').readOnly=true;
      document.getElementById('totalCalWeightage').readOnly=true;*/
    });

</script>
</body>
<link rel="stylesheet" href=<s:url value="/includes/css/new/jquery-ui.css?version=3"/>>       
<script type="text/JavaScript" src="<s:url value="/includes/javascripts/jquery/jquery-1.12.4.js"/>"></script>
<script type="text/JavaScript" src="<s:url value="/includes/javascripts/jquery/jquery-ui.js"/>"></script>



<script>
    $(function() {
        $( document ).tooltip();
    });
</script>
</html>








