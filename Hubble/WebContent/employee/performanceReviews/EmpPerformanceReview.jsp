<!-- 
 *******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  November 20, 2007, 3:25 PM
 *
 * Author  : Rajasekhar Yenduva<ryenduva@miraclesoft.com>
 *
 * File    : IssuesList.jsp
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
-->
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%--<%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<%@ page import="com.freeware.gridtag.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hubble Organization Portal :: Employee Tasks List</title>
    <sx:head cache="true"/>
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tooltip.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tooltip.js"/>"></script>   
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>   
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>   
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/employee/DateValidator.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ajax/AjaxPopup.js"/>"></script> 
    <script type="text/javascript" src="<s:url value="/includes/javascripts/searchIssue.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmpStandardClientValidations.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
    <script type="text/javascript" src="<s:url value="/includes/javascripts/IssueFill.js"/>"></script>
   
   
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmpPerformananceReviewAjax.js"/>"></script>
 <script type="text/JavaScript" src="<s:url value="/includes/javascripts/jquery.min.js"/>"></script>

    <%--<script type="text/JavaScript" src="<s:url value="/includes/javascripts/newjavascript1.js"/>"></script>--%>
    <s:include value="/includes/template/headerScript.html"/>

   <%-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>--%>

    <%--  <script src="../../includes/javascripts/simple-slider.js"></script>

    <link href="../../includes/css/simple-slider.css" rel="stylesheet" type="text/css" />
    <link href="../../includes/css/simple-slider-volume.css" rel="stylesheet" type="text/css" /> --%>
    <%-- for issue reminder popup --%>
    <script type="text/javascript">
        function win_open2(url,id,priemail){
            // alert(priemail.length);
            if(priemail.length <=1)
            {
                alert("This task is not assigned to any person.Please assign the issue before sending reminder");
            }else{
                //  alert("id-->"+id);
                // alert("url---->"+url);
                // var values=document.getElementById('mailid').innerHTML;
                url = url+"?issueid="+id;
                newWindow=window.open(url,'issueid','height=230,width=540');
            }
        }               
            

        
    </script>

    <script type="text/javascript">
        $(document).ready(function(){
            
             
            if($('#iflag').val() == "0")
            {
                $('#metrics').attr('checked', true); 
                $('#addMetrics').attr('checked', true); 
                
                $('#metricsLabel').css('font-weight', 'bold');
                $('#addMetricsLabel').css('font-weight', 'bold');
                $('#searchMetricsLabel').css('font-weight', 'normal');
                $(".titleAssoc").hide();
                $(".performanceReview").hide();
                $(".searchMetrics").hide();   
                $(".metrics").toggle();
                $(".addMetrics").toggle();
     
                getEmpTitleDataV1();
                $('input[type="checkbox"]').click(function(){
                    if($(this).attr("value")=="searchMetrics"){
                        $('#addMetrics').attr('checked', false); 
                        $('#addMetricsLabel').css('font-weight', 'normal');
                        $('#searchMetricsLabel').css('font-weight', 'bold');
                        $(".addMetrics").hide();
                        $(".searchMetrics").toggle();   
                            
                    }
                    if($(this).attr("value")=="addMetrics"){
                        $('#searchMetrics').attr('checked', false); 
                        $('#addMetricsLabel').css('font-weight', 'bold');
                        $('#searchMetricsLabel').css('font-weight', 'normal');
                        $(".searchMetrics").hide();   
                        $(".addMetrics").toggle();
                            
                    }
                    
                    if($(this).attr("value")=="metrics"){
               
                        $('#metricsLabel').css('font-weight', 'bold');
                        $('#addMetricsLabel').css('font-weight', 'bold');
                        $('#titleAssocLabel').css('font-weight', 'normal');
                        $('#performanceReviewLabel').css('font-weight', 'normal');
                        $('#searchMetricsLabel').css('font-weight', 'normal');
                        $('#addMetrics').attr('checked', true); 
                        $('#titleAssoc').attr('checked', false); 
                        $('#performanceReview').attr('checked', false);
                        $('#searchMetrics').attr('checked', false);
                        $(".titleAssoc").hide();
                        $(".performanceReview").hide();
                        $(".searchMetrics").hide();
                        $(".addMetrics").hide();
                        $(".addMetrics").toggle();
                        $(".metrics").toggle();
                        if( $(".addMetrics").prop("checked") == true){
                            $('#addMetricsLabel').css('font-weight', 'bold');
                            $('#searchMetricsLabel').css('font-weight', 'normal');
                             
                            $(".addMetrics").toggle();
                            $(".searchMetrics").hide();
                        }
                      
                        if($(this).attr("value")=="addMetrics"){
                            $('#searchMetrics').attr('checked', false); 
                            $('#addMetricsLabel').css('font-weight', 'bold');
                            $('#searchMetricsLabel').css('font-weight', 'normal');
                            $(".searchMetrics").hide();   
                            $(".addMetrics").toggle();
                            
                        }
                        if($(this).attr("value")=="searchMetrics"){
                            $('#addMetrics').attr('checked', false); 
                            $('#addMetricsLabel').css('font-weight', 'normal');
                            $('#searchMetricsLabel').css('font-weight', 'bold');
                            $(".addMetrics").hide();
                            $(".searchMetrics").toggle();   
                            
                        }
                                
                      
                    }
                    if($(this).attr("value")=="titleAssoc"){
                        
                        $(".resM").hide();
                        $('#addWeightage').attr('checked', true);
                        $('#metrics').attr('checked', false); 
                        $('#performanceReview').attr('checked', false);
                        $('#searchWeightage').attr('checked', false);
                        $('#titleAssocLabel').css('font-weight', 'bold');
                        $('#metricsLabel').css('font-weight', 'normal');
                        $('#performanceReviewLabel').css('font-weight', 'normal');
                        $(".metrics").hide();
                        $(".performanceReview").hide();
                        $(".searchWeightage").hide();
                        $(".titleAssoc").toggle();
                        
                        if( $("#addWeightage").prop("checked") == true){
                            //alert("");
                            $('#addWeightageLabel').css('font-weight', 'bold'); 
                            $('#searchWeightageLabel').css('font-weight', 'normal'); 
                            $(".addWeightage").hide();
                            $(".addWeightage").toggle();
                        }
                        
                    }
                    if($(this).attr("value")=="performanceReview"){
                        $('#addReview').attr('checked', true);
                        $('#metrics').attr('checked', false); 
                        $('#titleAssoc').attr('checked', false);
                        $('#searchReview').attr('checked', false);
                        $('#performanceReviewLabel').css('font-weight', 'bold');
                        $('#metricsLabel').css('font-weight', 'normal');
                        $('#titleAssocLabel').css('font-weight', 'normal');
                        $(".metrics").hide();
                        $(".titleAssoc").hide();
                        $(".searchReview").hide();
                        $(".performanceReview").toggle();
                        if( $("#addReview").prop("checked") == true){
                            //alert("");
                            $('#addReviewLabel').css('font-weight', 'bold'); 
                            $('#searchReviewLabel').css('font-weight', 'normal'); 
                            $(".addReview").hide();
                            $(".addReview").toggle();
                        }
                       
                    }
                    if($(this).attr("value")=="addReview"){
                        $('#searchReview').attr('checked', false);
                        $('#addReviewLabel').css('font-weight', 'bold'); 
                        $('#searchReviewLabel').css('font-weight', 'normal'); 
                        $(".searchReview").hide();
                        $(".addReview").toggle();
                    }
                    if($(this).attr("value")=="searchReview"){
                        $('#addReview').attr('checked', false); 
                        $('#addReviewLabel').css('font-weight', 'normal'); 
                        $('#searchReviewLabel').css('font-weight', 'bold'); 
                        $(".addReview").hide();
                        $(".searchReview").toggle();
                            
                    }
                    if($(this).attr("value")=="addWeightage"){
                        $('#searchWeightage').attr('checked', false);
                        $('#addWeightageLabel').css('font-weight', 'bold'); 
                        $('#searchWeightageLabel').css('font-weight', 'normal'); 
                        $(".searchWeightage").hide();
                        $(".addWeightage").toggle();
                    }
                    if($(this).attr("value")=="searchWeightage"){
                        $('#addWeightage').attr('checked', false); 
                        $('#addWeightageLabel').css('font-weight', 'normal'); 
                        $('#searchWeightageLabel').css('font-weight', 'bold'); 
                        $(".addWeightage").hide();
                        $(".searchWeightage").toggle();
                            
                    }
                    
                });
            }
        });
        function goToAddMetricsSubmit(){
            var metricName=document.getElementById("metricName").value;
            var minValue=document.getElementById("minValue").value;
            var maxValue=document.getElementById("maxValue").value;
            var statusId=document.getElementById("statusId").value;
            var comments=document.getElementById("comments").value;
            if(metricName==""||metricName==null||minValue==""||minValue==null||maxValue==""||maxValue==null)
            {
                alert("Please provide the mandatory fields");
                return false;
            }
           
            window.location="addMetrics.action?metricName="+metricName+"&minValue="+minValue+"&maxValue="+maxValue+"&statusId="+statusId+"&comments="+comments;    
        }  
        function goToUpdateMetricsSubmit(){

            var id=document.getElementById("id").value;
            //alert(id);
            var metricName=document.getElementById("metricName").value;
            var minValue=document.getElementById("minValue").value;
            var maxValue=document.getElementById("maxValue").value;
            var statusId=document.getElementById("statusId").value;
            var comments=document.getElementById("comments").value;
            if(metricName==""||metricName==null||minValue==""||minValue==null||maxValue==""||maxValue==null)
            {
                alert("Please provide the mandatory fields");
                return false;
            }
           
            window.location="doEditMetrics.action?metricName="+metricName+"&minValue="+minValue+"&maxValue="+maxValue+"&statusId="+statusId+"&comments="+comments+"&id="+id;    
        }  
        function goToAddTitlesSubmit(){
            var metricName=document.getElementById("metricNameForTitle").value;
            var metricId=document.getElementById("metricId").value;
            
            var minValue=document.getElementById("minValueForTitle").value;
            var maxValue=document.getElementById("maxValueForTitle").value;
            var statusId=document.getElementById("statusIdForTitle").value;
            var comments=document.getElementById("commentsForTitle").value;
            var weightage=document.getElementById("weightage").value;
            var departmentId=document.getElementById("departmentId").value;
            var titleId=document.getElementById("titleId").value;
            //alert(departmentId+"-------------"+titleId);
            if(metricName==""||metricName==null||minValue==""||minValue==null||maxValue==""||maxValue==null||titleId=="-- Please Select --"||titleId==""||weightage==""||weightage==null)
            {
                alert("Please provide the mandatory fields");
                return false;
            }
           
            window.location="addTitle.action?metricId="+metricId+"&statusId="+statusId+"&comments="+comments+"&departmentId="+departmentId+"&titleId="+titleId+"&weightage="+weightage;    
        }  
        function goToUpdateTitlesSubmit(){
            var id=document.getElementById("id").value;
            // alert(id);
            var departmentId=document.getElementById("departmentId").value;
            var titleId=document.getElementById("titleId").value;
            var weightage=document.getElementById("weightage").value;
            var metricId=document.getElementById("metricId").value;
            var minValue=document.getElementById("minValueForTitle").value;
            var maxValue=document.getElementById("maxValueForTitle").value;
            var statusId=document.getElementById("statusIdForTitle").value;
            var comments=document.getElementById("commentsForTitle").value;
            if(metricName==""||metricName==null||minValue==""||minValue==null||maxValue==""||maxValue==null)
            {
                alert("Please provide the mandatory fields");
                return false;
            }
           
            window.location="doEditTitles.action?metricId="+metricId+"&minValue="+minValue+"&maxValue="+maxValue+"&statusId="+statusId+"&comments="+comments+"&id="+id+"&departmentId="+departmentId+"&titleId="+titleId+"&weightage="+weightage;    
        }  
        function submitPerformanceForm(){
            // document.getElementById("SavePerformance").disabled = true;
            document.getElementById("addReviewForm").submit();
        }
        
    </script>
    <style>

        [class^=slider] { display: inline-block; margin-bottom: 30px; }
        .output { color: #888; font-size: 14px; padding-top: 1px; margin-left: 5px; vertical-align: top;}


    </style>
    <style type="text/css">
        .box{
            padding: 20px;
            display: none;
            margin-top: 20px;

        }

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
    <%
        String check = null;

        if (request.getAttribute("check") != null) {
            check = request.getAttribute("check").toString();
        }
    %> 
</head>
<%-- <body class="bodyGeneral"  oncontextmenu="return false;" onload="init();"> --%>
<body class="bodyGeneral"  oncontextmenu="return false;">
    <%!
        /* Declarations */
        Connection connection;
        String queryString;
        String strTmp;
        String strSortCol;
        String strSortOrd;
        String action;
        int role;
        int intSortOrd = 0;
        int intCurr;
        String currentSearch = null;
        boolean blnSortAsc = true;
        HttpServletRequest httpServletRequest;
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
                        <td width="850px" class="cellBorder" valign="top" style="padding:10px 5px 5px 5px;">
                            <%--  <ul id="taskTabs" class="shadetabs" >
                                  <li ><a href="#" class="selected" rel="createTask"  >Create Task</a></li>
                            <%--<li ><a href="#" rel="empSkills">Skill Set</a></li>
                            <li ><a href="#" rel="empUpload">Upload Image</a></li>
                        </ul> --%>


                            <ul id="taskTabs" class="shadetabs" >
                                <%--<s:if test="%{currentAction=='addPerformanceReview'}"> --%>
                                <li ><a href="#" class="selected" rel="empReview"  >Performance Review</a></li>
                                <%-- </s:if> --%>


                            </ul>
                            <div  style="border:1px solid gray; width:895px;height: 600px; overflow:auto; margin-bottom: 1em;">   
                                <%-- <sx:div id="empProfileTab" label="Profile" > --%>

                                <div id="empReview" style="width:840px;margin-top:-20px"> 
                                    <s:form theme="simple" method="post">
                                        <table cellpadding="0" border="0" cellspacing="0" width="100%">
                                            <s:hidden name="empType" id="empType" value="%{#session.empType}"/>
                                            <s:hidden name="iflag" id="iflag" value="%{#request.metricFlag}"/>
                                            <s:hidden name="id" id="id" value="%{performanceVTO.id}"/>
                                            <tr class="headerText12">
                                                <td align="center" colspan="6">

                                                    <div id="resM"><s:property value="%{resM}"/></div>

                                                </td>
                                            </tr>
                                            <br><br>
                                            <tr><td align="center" >
                                                    <input type="checkbox" name="metrics" id="metrics" value="metrics"><label id="metricsLabel" class="fieldLabel">Metrics&nbsp; </label> 
                                                    <input type="checkbox" name="titleAssoc" id="titleAssoc" value="titleAssoc" onclick="getEmpTitleDataV1();"> <label id="titleAssocLabel" class="fieldLabel">Title Association&nbsp;</label>
                                                    <input type="checkbox" name="performanceReview" id="performanceReview" value="performanceReview" > <label id="performanceReviewLabel" class="fieldLabel">Performance Review&nbsp;</label></td></tr>   
                                            <tr><td align="center"><div class="metrics box" id="metricsDiv" style="alignment-adjust:central;margin-top:-7px;display: none;">   
                                                        <s:form action="" method="post" theme="simple">
                                                            <table cellpadding="1" cellspacing="1" border="0" width="90%">
                                                                
                                                                <tr><td align="center" >
                                                                        <%--<s:if test="%{#request.metricFlag == 0 || #request.metricFlag == 2 || #request.metricFlag == 3}">
                                                                            <input type="checkbox" name="addMetrics" id="addMetrics" value="addMetrics"><label id="addMetricsLabel" class="fieldLabel">Add Metrics&nbsp; </label> 
                                                                        </s:if>
                                                                        <s:elseif test="%{#request.metricFlag == 1}">
                                                                            <input type="checkbox" name="addMetrics" id="addMetrics" value="addMetrics"><label id="addMetricsLabel" class="fieldLabel">Edit Metrics&nbsp; </label>    
                                                                        </s:elseif> --%>
                                                                         <input type="checkbox" name="addMetrics" id="addMetrics" value="addMetrics"><label id="addMetricsLabel" class="fieldLabel">Add Metrics&nbsp; </label> 
                                                                         <input type="checkbox" name="searchMetrics" id="searchMetrics" value="searchMetrics" onclick="changeEditToAddMetrics();"> <label id="searchMetricsLabel" class="fieldLabel">Search Metrics&nbsp;</label>
                                                                    </td></tr>   
                                                                <tr><td align="center"><div class="addMetrics box" id="addMetricsDiv" style="alignment-adjust:central;margin-top:-7px;"> 
                                                                            <s:form name="metricsForm" id="metricsForm" theme="simple">
                                                                                <table cellpadding="1" cellspacing="1" border="0" width="75%" >
                                                                                <tr class="headerText12"><td colspan="6" align="center">
                                                                                        <p id="metricResultMessage"></p>
                                                                                    </td></tr>
                                                                                    <tr>
                                                                                        <td class="fieldLabel">Metric Name :<FONT color="red"  ><em>*</em></FONT></td> 
                                                                                        <td ><s:textfield cssClass="inputTextBlue" name="metricName" id="metricName" value="%{performanceVTO.metricName}"/></td>
                                                                                        <td colspan="2"><label class="fieldLabel">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Min :<FONT color="red"  ><em>*</em></FONT></label>
                                                                                            <s:textfield cssClass="inputTextBlue31" name="minValue" id="minValue" onkeypress="return isNumber(event);" value="%{performanceVTO.minValue}"/>
                                                                                            <label class="fieldLabel">Max :<FONT color="red"  ><em>*</em></FONT></label>
                                                                                            <s:textfield cssClass="inputTextBlue31" name="maxValue" id="maxValue"  onkeypress="return isNumber(event);" value="%{performanceVTO.maxValue}"/>
                                                                                        </td> 
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td class="fieldLabel">Status :</td>
                                                                                        <td><s:select list="{'Active','InActive'}"
                                                                                                  name="statusId" id="statusId"
                                                                                                  value="%{performanceVTO.statusId}"
                                                                                                  cssClass="inputSelect"  /></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td class="fieldLabel">Description:</td>
                                                                                        <td colspan="3"><s:textarea rows="3" cols="65" name="comments" cssClass="inputTextarea41" id="comments" value="%{performanceVTO.comments}" onchange="fieldLenghtValidator(this);"/></td>
                                                                                    </tr>
                                                                                    <tr><td colspan="4" align="center"><br>
                                                                                         <%--   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                                            <s:if test="%{#request.metricFlag == 0}"><input type="button" value="Submit" class="buttonBg" onclick="goToAddMetricsSubmit()"></s:if>
                                                                                            <s:else>
                                                                                                <input type="button" value="Update" class="buttonBg" onclick="goToUpdateMetricsSubmit()">
                                                                                            </s:else> --%>
                                                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     
                                                                                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                                <input type="button" id="metricSubmit" class="buttonBg" label="Submit" value="Save" onclick="addMetricValuesThroughAjax()" /> </td></tr>
                                                                                    <tr><td colspan="4" align="right">
                                                                                                   
                                                                                            <div style="margin-right: 7.2%;"> <input type="button" id="metricUpdate" class="buttonBg" value="Update" onClick="updateMetricValuesThroughAjax();" style="display:none;" /> </div>
                                                                                        </td></tr> 
                                                                                </table>
                                                                            </s:form>
                                                                        </div></td></tr>
                                                                <tr><td align="center"><div class="searchMetrics box" id="searchMetricsDiv" style="alignment-adjust:central;margin-top:-7px"> 
                                                                            <s:form name="searchMetricsForm" id="searchMetricsForm" theme="simple">
                                                                                <table cellpadding="1" cellspacing="1" border="0" width="75%" >
                                                                                    <tr>
                                                                                        <td class="fieldLabel">Metric Name :</td> 
                                                                                        <td ><s:textfield cssClass="inputTextBlue" name="metricNameForSearch" id="metricNameForSearch" value="%{metricName}"/></td>
                                                                                        <td class="fieldLabel">Status :</td>
                                                                                        <td><s:select list="{'Active','InActive'}"
                                                                                                  name="statusIdForSearch" id="statusIdForSearch"
                                                                                                  value="%{statusId}"
                                                                                                  cssClass="inputSelect"  /></td>
                                                                                        <td>
                                                                                            <input type="button" value="Search" class="buttonBg" onclick="getMetricRecords();">
                                                                                        </td>
                                                                                    </tr> 
                                                                                </table>
                                                                                <table cellpadding="1" cellspacing="1" border="0" width="75%" >
                                                                                    <tr>
                                                                                        <td height="20px" align="center" colspan="9">
                                                                                            <div id="loadActMessage" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                        </td>
                                                                                    </tr>

                                                                                    <tr>
                                                                                        <td>
                                                                                            <br>
                                                                                            <table align="center" cellpadding="2" border="0" cellspacing="1" width="50%" >

                                                                                                <tr>
                                                                                                    <td >
                                                                                                        <br>
                                                                                                        <div id="metricsList" style="display: block">
                                                                                                            <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                                                            <table id="tblEmpMetrics" align='center' cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750'>
                                                                                                                <COLGROUP ALIGN="left" >
                                                                                                                    <COL width="5%">
                                                                                                                    <COL width="15%">
                                                                                                                    <COL width="10%">
                                                                                                                    <COL width="10%">
                                                                                                                    <COL width="13%">
                                                                                                                    <COL width="10%">
                                                                                                                    <COL width="15%">
                                                                                                            </table>
                                                                                                            <br>
                                                                                                            <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                                                                        </div>
                                                                                                    </td>
                                                                                                </tr>
                                                                                            </table>    
                                                                                        </td>
                                                                                    </tr>
                                                                                </table>


                                                                            </s:form>
                                                                        </div></td></tr>

                                                            </table>

                                                        </s:form>


                                                    </div></td></tr>




                                            <tr><td align="center"><div class="titleAssoc box"  id="titleAssocDiv" style="alignment-adjust:central;margin-top:-7px;">   


                                                        <s:form name="titleAssocForm" id="titleAssocForm" theme="simple">
                                                            <table cellpadding="1" cellspacing="1" border="0" width="83%" >

                                                                <tr><td align="center" >
                                                                       <%-- <s:if test="%{#request.metricFlag == 0 || #request.metricFlag == 1 || #request.metricFlag == 3}">
                                                                            <input type="checkbox" name="addWeightage" id="addWeightage" value="addWeightage"><label id="addWeightageLabel" class="fieldLabel">Add Weightage&nbsp; </label> 
                                                                        </s:if>
                                                                        <s:elseif test="%{#request.metricFlag == 2}">
                                                                            <input type="checkbox" name="addWeightage" id="addWeightage" value="addWeightage"><label id="addWeightageLabel" class="fieldLabel">Edit Weightage&nbsp; </label> 
                                                                        </s:elseif> --%>
                                                                            <input type="checkbox" name="addWeightage" id="addWeightage" value="addWeightage"><label id="addWeightageLabel" class="fieldLabel">Add Weightage&nbsp; </label> 
                                                                        <input type="checkbox" name="searchWeightage" id="searchWeightage" value="searchWeightage" onclick="getEmpTitleDataV11();changeEditToAddTitle();"><label id="searchWeightageLabel" class="fieldLabel">Search Weightage&nbsp; </label> 
                                                                    </td></tr>  
                                                                <tr><td align="center"> 

                                                                        <div class="addWeightage box" id="addWeightageDiv"  style="alignment-adjust:central;margin-top:-7px;">     
                                                                            <s:form name="weighatgeForm" id="weighatgeForm" theme="simple">
                                                                                <table cellpadding="1" cellspacing="1" border="0" width="83%" >
                                                                                 <tr class="headerText12"><td colspan="6" align="center">
                                                                                        <p id="titleResultMessage"></p>
                                                                                    </td></tr>
                                                                                    <tr>
                                                                                        <td class="fieldLabel">Department :<FONT color="red"  ><em>*</em></FONT></td>
                                                                                        <td><s:select label="Select Department" 
                                                                                                  name="departmentId"
                                                                                                  id="departmentId"
                                                                                                  list="departmentIdList" cssClass="inputSelect" value="%{performanceVTO.departmentId}" onchange="getEmpTitleDataV1();"/></td>
                                                                                        <td class="fieldLabel">Title :<FONT color="red"  ><em>*</em></FONT></td>  
                                                                                        <td><s:select label="Select Title"
                                                                                                  headerKey=""
                                                                                                  headerValue="-- Please Select --"
                                                                                                  name="titleId"  id="titleId"
                                                                                                  list="titleIdList" cssClass="inputSelect" value="%{performanceVTO.titleId}" onchange=""/></td>      

                                                                                    </tr> 
                                                                                    <tr>
                                                                                        <td class="fieldLabel">Metric Name :<FONT color="red"  ><em>*</em></FONT></td> 
                                                                                        <td>
                                                                                            <s:textfield name="metricNameForTitle" cssClass="inputTextBlue" id="metricNameForTitle" onkeyup="getAllMetricNames();" value="%{performanceVTO.metricNameForTitle}"/>
                                                                                            <s:hidden name="metricId" cssClass="inputTextBlue" id="metricId" value="%{performanceVTO.metricId}" />
                                                                                            <div id="validationMessage2"></div>
                                                                                        </td>     
                                                                                        <td colspan="2"><label class="fieldLabel">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Min :</label>
                                                                                            <s:textfield cssClass="inputTextBlue31" name="minValueForTitle" id="minValueForTitle" value="%{performanceVTO.minValueForTitle}" readOnly="true"  onkeypress="return isNumber(event);"/>
                                                                                            <label class="fieldLabel">Max :</label>
                                                                                            <s:textfield cssClass="inputTextBlue31" name="maxValueForTitle" id="maxValueForTitle" value="%{performanceVTO.maxValueForTitle}" readOnly="true"  onkeypress="return isNumber(event);"/>
                                                                                        </td> 
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td class="fieldLabel" valign="top">Weightage&nbsp;<FONT color="red"  ><em>*</em></FONT>:</td>
                                                                                        <td valign="top">
                                                                                            <s:textfield name="weightage" id="weightage" value="%{performanceVTO.weightage}" size="2" cssClass="inputTextBlue2"  onkeypress="return isNumber(event);"/><s:label cssClass="fieldLabel" value="%"/><s:label cssClass="fieldLabel" value="(Ex:50)"/>
                                                                                        </td>    

                                                                                        <td class="fieldLabel">Status :</td>
                                                                                        <td><s:select list="{'Active','InActive'}"
                                                                                                  name="statusIdForTitle" id="statusIdForTitle"
                                                                                                  value="%{performanceVTO.statusIdForTitle}"
                                                                                                  cssClass="inputSelect"  /></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td class="fieldLabel">Description:</td>
                                                                                        <td colspan="3"><s:textarea rows="3" cols="65" name="commentsForTitle" cssClass="inputTextarea41" id="commentsForTitle" value="%{performanceVTO.commentsForTitle}" onchange="fieldLenghtValidator(this);"/></td>
                                                                                    </tr>
                                                                                         <tr><td colspan="4" align="center"><br>
                                                                             
                                                                                      <%--       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                                           <s:if test="%{#request.metricFlag == 0}"><input type="button" value="Submit" class="buttonBg" onclick="goToAddTitlesSubmit()"></s:if>
                                                                                            <s:else>
                                                                                                <input type="button" value="Update" class="buttonBg" onclick="goToUpdateTitlesSubmit()">
                                                                                            </s:else> --%>
                                                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     
                                                                                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                                   
                                                                                   <input type="button" id="titleSubmit" class="buttonBg" label="Submit" value="Save" onclick="addTitleValuesThroughAjax()" /> </td></tr>
                                                                                       <tr><td colspan="4" align="right">
                                                                                                   
                                                                                               <div style="margin-right: 9%;"><input type="button" id="titleUpdate" class="buttonBg" value="Update" onClick="updateTitleValuesThroughAjax();" style="display:none;" /> </div>
                                                                                        </td></tr> 
                                                                                </table>
                                                                            </s:form>
                                                                        </div>
                                                                    </td></tr>


                                                                <tr><td align="center"> 

                                                                        <div class="searchWeightage box" id="searchWeightageDiv" style="alignment-adjust:central;margin-top:-7px;">     
                                                                            <s:form name="searchWeightage" id="searchWeightage" theme="simple">
                                                                                <table cellpadding="1" cellspacing="1" border="0" width="83%" >
                                                                                    <tr>
                                                                                        <td class="fieldLabel">Department :</td>
                                                                                        <td><s:select label="Select Department" 
                                                                                                  name="searchDepartmentId"
                                                                                                  id="searchDepartmentId"
                                                                                                  list="departmentIdList" cssClass="inputSelect" value="%{currentEmployee.searchDepartmentId}" onchange="getEmpTitleDataV11();"/></td>
                                                                                        <td class="fieldLabel">Title :<FONT color="red"  ><em>*</em></FONT></td>  
                                                                                        <td><s:select label="Select Title"
                                                                                                  headerKey=""
                                                                                                  headerValue="-- Please Select --"
                                                                                                  name="searchTitleId"  id="searchTitleId"
                                                                                                  list="titleIdList" cssClass="inputSelect" value="%{currentEmployee.searchTitleId}" onchange=""/></td>      

                                                                                    </tr> 
                                                                                    <tr>
                                                                                        <td class="fieldLabel">Metric Name :</td> 
                                                                                        <td>
                                                                                            <s:textfield cssClass="inputTextBlue" name="metricNameForTitleSearch" id="metricNameForTitleSearch" onkeyup="getAllMetricNames1();" value="%{metricNameForTitleSearch}"/>
                                                                                            <s:hidden name="metricIdForTitleSearch" cssClass="inputTextBlue" id="metricIdForTitleSearch" value="%{metricIdForTitleSearch}" />
                                                                                            <div id="validationMessage3"></div>

                                                                                        </td>


                                                                                        <td class="fieldLabel">Status :</td>
                                                                                        <td><s:select list="{'Active','InActive'}"
                                                                                                  name="statusIdForTitleSearch" id="statusIdForTitleSearch"
                                                                                                  value="%{statusIdForTitleSearch}"
                                                                                                  cssClass="inputSelect"  /></td>

                                                                                        <td>
                                                                                            <input type="button" value="Search" class="buttonBg" onclick="getAllTitleAssociations();">
                                                                                        </td>
                                                                                    </tr>


                                                                                </table>
                                                                                <table cellpadding="1" cellspacing="1" border="0" width="95%" >
                                                                                    <tr>
                                                                                        <td height="20px" align="center" colspan="9">
                                                                                            <div id="loadActMessage" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                        </td>
                                                                                    </tr>

                                                                                    <tr>
                                                                                        <td>
                                                                                            <br>
                                                                                            <table align="center" cellpadding="2" border="0" cellspacing="1" width="95%" >

                                                                                                <tr>
                                                                                                    <td>
                                                                                                        <br>
                                                                                                        <div id="titlesList" style="display: block">
                                                                                                            <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                                                            <table id="tblTitleMetrics" align='center' cellpadding='1' cellspacing='1' border='0' class="gridTable" width='550'>
                                                                                                                <COLGROUP ALIGN="left" >
                                                                                                                    <COL width="5%">
                                                                                                                    <COL width="25%">
                                                                                                                    <COL width="35%">
                                                                                                                    <COL width="65%">
                                                                                                                    <COL width="13%">
                                                                                                                    <COL width="20%">
                                                                                                                    <COL width="15%">
                                                                                                            </table>
                                                                                                            <br>
                                                                                                            <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                                                                        </div>
                                                                                                    </td>
                                                                                                </tr>
                                                                                            </table>    
                                                                                        </td>
                                                                                    </tr>
                                                                                </table>     

                                                                            </s:form>
                                                                        </div>
                                                                    </td></tr>

                                                            </table>
                                                        </s:form>







                                                    </div></td></tr>

                                            <tr><td align="center"><div class="performanceReview box" id="performanceReviewDiv" style="alignment-adjust:central;margin-top:-7px;">   


                                                        <table cellpadding="1" cellspacing="1" border="0" width="93%" >

                                                            <tr><td align="center" >

                                                                    <input type="checkbox" name="addReview" id="addReview" value="addReview"><label id="addReviewLabel" class="fieldLabel" >Add Review</label> 
                                                                    <input type="checkbox" name="searchReview" id="searchReview" value="searchReview" onclick="setDefaultOppDates1();changeEditToAddPerformance();"><label id="searchReviewLabel" class="fieldLabel">Search Review&nbsp; </label> 
                                                                </td></tr>  



                                                            <tr><td align="center"><div class="addReview box" id="addReviewDiv" style="alignment-adjust:central;margin-top:-7px;">

                                                                        <s:form name="addReviewForm" id="addReviewForm" theme="simple" action="addPerformanceReview">
                                                                            <table cellpadding="1" cellspacing="1" border="0" width="95%" align="center" >
                                                                                <tr class="headerText12"><td colspan="6" align="center">
                                                                                        <p id="performanceResultMessage"></p>
                                                                                    </td></tr>
                                                                                <tr>
                                                                                    <td width="70%"><label class="fieldLabel">Please Enter Employee Name:</label>
                                                                                        <s:textfield name="empName" cssClass="inputTextBlue" id="empName" onkeyup="getAllEmpNames();" value="%{performanceVTO.empName}" />
                                                                                        <s:hidden name="empNameLoginId" cssClass="inputTextBlue" id="empNameLoginId" value="%{performanceVTO.empNameLoginId}" />
                                                                                        <div id="validationMessage"></div>
                                                                                    </td> 
                                                                                    <td>
                                                                                        <s:submit id="performanceSubmit" class="buttonBg" label="Submit" value="Save" /> 
                                                                                        <input type="button" id="performanceUpdate" class="buttonBg" value="Update" onClick="updatePerformanceValues();" style="display:none;" /> 
                                                                                    </td>
                                                                                </tr>
                                                                                <tr><td colspan="2">
                                                                                        <br>

                                                                                        <div id="performanceDiv" style="margin-top:-7px;display:none;"> 
                                                                                            <table cellpadding="1" cellspacing="1" border="0" width="90%" align="center">
                                                                                                <tr>
                                                                                                    <td class="fieldLabel">Department :</td>  
                                                                                                    <td> <s:textfield name="departmentName" cssClass="inputTextBlue" id="departmentName"  value="%{performanceVTO.departmentName}" onchange="fieldLenghtValidator(this);" readonly="true"/></td>      
                                                                                                    <td class="fieldLabel">Title :</td>  
                                                                                                    <td> <s:textfield name="empTitle" cssClass="inputTextBlue" id="empTitle"  value="%{performanceVTO.empTitle}" onchange="fieldLenghtValidator(this);" readonly="true"/></td>      
                                                                                                </tr> 
                                                                                                <tr>         
                                                                                                    <td class="fieldLabel">Review From Date&nbsp;:</td>
                                                                                                    <td>

                                                                                                        <s:textfield  name="fromDate" id="fromDate" value="%{performanceVTO.fromDate}" cssClass="inputTextBlue"  />
                                                                                                        <a href="javascript:cal2.popup();"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                                                                width="20" height="20" border="0"></a> 
                                                                                                    </td>
                                                                                                    <td class="fieldLabel">Review To Date&nbsp;:</td>
                                                                                                    <td colspan="3">

                                                                                                        <s:textfield name="toDate" id="toDate" value="%{performanceVTO.toDate}"  cssClass="inputTextBlue"/>
                                                                                                        <a href="javascript:cal3.popup();">
                                                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                                 width="20" height="20" border="0"></a>

                                                                                                    </td>

                                                                                                </tr>
                                                                                                <tr>
                                                                                                    <td class="fieldLabel">Call-OutBound :</td>  
                                                                                                    <td> <s:textfield name="callOutBound" cssClass="inputTextBlue" id="callOutBound"  value="%{performanceVTO.callOutBound}" onchange="fieldLenghtValidator(this);" readonly="true"/></td>      
                                                                                                    <td class="fieldLabel">Appointments :</td>  
                                                                                                    <td> <s:textfield name="appointment" cssClass="inputTextBlue" id="appointment"  value="%{performanceVTO.appointment}" onchange="fieldLenghtValidator(this);" readonly="true"/></td>      
                                                                                                </tr> 
                                                                                                <tr>
                                                                                                    <td class="fieldLabel">Conference&nbsp;Calls :</td>  
                                                                                                    <td> <s:textfield name="conferenceCalls" cssClass="inputTextBlue" id="conferenceCalls"  value="%{performanceVTO.conferenceCalls}" onchange="fieldLenghtValidator(this);" readonly="true"/></td>      
                                                                                                    <td class="fieldLabel">Meetings :</td>  
                                                                                                    <td> <s:textfield name="meeting" cssClass="inputTextBlue" id="meeting"  value="%{performanceVTO.meeting}" onchange="fieldLenghtValidator(this);" readonly="true"/></td>      
                                                                                                </tr> 
                                                                                                <tr>
                                                                                                    <td class="fieldLabel">Oppurtunities :</td>  
                                                                                                    <td> <s:textfield name="oppurtunity" cssClass="inputTextBlue" id="oppurtunity"  value="%{performanceVTO.oppurtunity}" onchange="fieldLenghtValidator(this);" readonly="true"/></td>      
                                                                                                    <td class="fieldLabel">Requirements :</td>  
                                                                                                    <td> <s:textfield name="requirement" cssClass="inputTextBlue" id="requirement"  value="%{performanceVTO.requirement}" onchange="fieldLenghtValidator(this);" readonly="true"/></td>      

                                                                                                </tr> 
                                                                                                <tr>
                                                                                                    <td class="fieldLabel">Comments:</td>
                                                                                                    <td colspan="4"><s:textarea rows="3" cols="65" name="commentsForPerformance" cssClass="inputTextarea41" id="commentsForPerformance" value="%{performanceVTO.commentsForPerformance}" onchange="fieldLenghtValidator(this);"/></td>
                                                                                                </tr>

                                                                                            </table>

                                                                                            <table align="center">

                                                                                                <tr>
                                                                                                    <td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                                                                                                    <td colspan="2">
                                                                                                        <br>
                                                                                                        <table id="ratingsTable" class="display" border="1" width="95%" style="margin-right: 60px">
                                                                                                            <tr><td> 

                                                                                                                    <div style="height:395px;  overflow:auto; border:3px; border-right-width: 15px;border-bottom-width: 15px; border-left-width: 15px;display:none; "  id="creConsultantList">




                                                                                                                        <table cellpadding="1" cellspacing="1" width="100%" class="gridTable" id="RevenueSummery">
                                                                                                                            <input type="hidden" name="count"  id="count" class="gridCol"  value="" readonly>
                                                                                                                            <tr class="gridHeader">
                                                                                                                                <td width="5%" class="gridHeader">Metrics </td>
                                                                                                                                <td width="45%" class="gridHeader" >Rating</td>
                                                                                                                                <td width="0%" class="gridHeader" >Value</td>
                                                                                                                                <td width="0%" class="gridHeader" >Weightage(%)</td>
                                                                                                                            </tr> 
                                                                                                                            <tr class="gridRowEven">
                                                                                                                            <input type="hidden" name="textp1"  id="textp1" class="gridCol"  value="" readonly>    
                                                                                                                            <input type="hidden" name="text1"  id="text1" class="gridCol"  value="" readonly>
                                                                                                                            <input type="hidden" name="textw1"  id="textw1" class="gridCol"  value="" readonly>
                                                                                                                            <input type="hidden" name="textm1"  id="textm1" class="gridCol"  value="" readonly>
                                                                                                                            <td> <input type="text" name="texti1"  id="texti1" class="gridCol"  value="" readonly></td>
                                                                                                                            <td><div id="div1" style="display:none;margin-bottom: -22px;margin-top: -10px; "><input type=range id="slider1" name="slider1"  value="" step="0.5" onchange="outputUpdate1(value)" ><%--<output for=fader id=volume1 class="output"></output>--%></div></td>
                                                                                                                            <td> <input type="text" name="texts1"  id="texts1" class="gridCol"  value="" readonly></td>
                                                                                                                            <td> <input type="text" name="textc1"  id="textc1" class="gridCol"   value=""  readonly></td>
                                                                                                                            </tr>
                                                                                                                            <tr class="gridRowEven">
                                                                                                                            <input type="hidden" name="textp2"  id="textp2" class="gridCol"  value="" readonly>   
                                                                                                                            <input type="hidden" name="text2"  id="text2" class="gridCol" align="left" value="" readonly>
                                                                                                                            <input type="hidden" name="textw2"  id="textw2" class="gridCol"  value="" readonly>
                                                                                                                            <input type="hidden" name="textm2"  id="textm2" class="gridCol"  value="" readonly>
                                                                                                                            <td> <input type="text" name="texti2"  id="texti2" class="gridCol" align="left" value="" readonly></td>
                                                                                                                            <td><div  id="div2" style="display:none;margin-bottom: -22px;margin-top: -10px; "> <input type=range name="slider2" id="slider2" value="" step="0.5" onchange="outputUpdate2(value)" ><%--<output for=fader id=volume2 class="output"></output>--%></div></td>
                                                                                                                            <td> <input type="text" name="texts2"  id="texts2" class="gridCol" align="left"  value="" readonly></td>
                                                                                                                            <td> <input type="text" name="textc2"  id="textc2" class="gridCol" align="left"  value="" readonly></td>
                                                                                                                            </tr>
                                                                                                                            <tr class="gridRowEven">
                                                                                                                            <input type="hidden" name="textp3"  id="textp3" class="gridCol"  value="" readonly>    
                                                                                                                            <input type="hidden" name="text3"  id="text3" class="gridCol" align="left" value="" readonly>
                                                                                                                            <input type="hidden" name="textw3"  id="textw3" class="gridCol"  value="" readonly>
                                                                                                                            <input type="hidden" name="textm3"  id="textm3" class="gridCol"  value="" readonly>
                                                                                                                            <td> <input type="text" name="texti3"  id="texti3" class="gridCol" align="left" value="" readonly></td>
                                                                                                                            <td><div id="div3"  style="display:none;margin-bottom: -22px;margin-top: -10px; "> <input type=range name="slider3" id="slider3" step="0.5" value="" onchange="outputUpdate3(value)" ><%--<output for=fader id=volume3 class="output"></output>--%></div></td>
                                                                                                                            <td> <input type="text" name="texts3"  id="texts3" class="gridCol" align="left" value="" readonly></td>
                                                                                                                            <td> <input type="text" name="textc3"  id="textc3" class="gridCol" align="left" value="" readonly></td>
                                                                                                                            </tr>
                                                                                                                            <tr class="gridRowEven">
                                                                                                                            <input type="hidden" name="textp4"  id="textp4" class="gridCol"  value="" readonly>    
                                                                                                                            <input type="hidden" name="text4"  id="text4" class="gridCol" align="left" value="" readonly>
                                                                                                                            <input type="hidden" name="textw4"  id="textw4" class="gridCol"  value="" readonly>
                                                                                                                            <input type="hidden" name="textm4"  id="textm4" class="gridCol" value="" readonly>
                                                                                                                            <td> <input type="text" name="texti4"  id="texti4" class="gridCol" align="left"value="" readonly></td>
                                                                                                                            <td><div id="div4" style="display:none;margin-bottom: -22px;margin-top: -10px; "><input type=range name="slider4" id="slider4" step="0.5"  value="" onchange="outputUpdate4(value)" ><%--<output for=fader id=volume4 class="output"></output>--%></div></td>
                                                                                                                            <td> <input type="text" name="texts4"  id="texts4" class="gridCol" align="left" value="" readonly></td>
                                                                                                                            <td> <input type="text" name="textc4"  id="textc4" class="gridCol" align="left" value="" readonly></td>
                                                                                                                            </tr>
                                                                                                                            <tr class="gridRowEven">
                                                                                                                            <input type="hidden" name="textp5"  id="textp5" class="gridCol"  value="" readonly>    
                                                                                                                            <input type="hidden" name="text5"  id="text5" class="gridCol" align="left" value="" readonly>
                                                                                                                            <input type="hidden" name="textw5"  id="textw5" class="gridCol"   value=""  readonly>
                                                                                                                            <input type="hidden" name="textm5"  id="textm5" class="gridCol"   value=""  readonly>
                                                                                                                            <td> <input type="text" name="texti5"  id="texti5" class="gridCol" align="left"  value=""  readonly></td>
                                                                                                                            <td><div id="div5" style="display:none;margin-bottom: -22px;margin-top: -10px; "><input type=range name="slider5" id="slider5"  value=""  step="0.5" onchange="outputUpdate5(value)" ><%--<output for=fader id=volume5 class="output"></output>--%></div></td>
                                                                                                                            <td> <input type="text" name="texts5"  id="texts5" class="gridCol" align="left"  value=""  readonly></td>
                                                                                                                            <td> <input type="text" name="textc5"  id="textc5" class="gridCol" align="left"  value=""  readonly></td>
                                                                                                                            </tr>
                                                                                                                            <tr class="gridRowEven">
                                                                                                                            <input type="hidden" name="textp6"  id="textp6" class="gridCol"  value="" readonly>    
                                                                                                                            <input type="hidden" name="text6"  id="text6" class="gridCol" align="left" value="" readonly>
                                                                                                                            <input type="hidden" name="textw6"  id="textw6" class="gridCol"  value="" readonly>
                                                                                                                            <input type="hidden" name="textm6"  id="textm6" class="gridCol"  value="" readonly>
                                                                                                                            <td> <input type="text" name="texti6"  id="texti6" class="gridCol" align="left" value="" readonly></td>
                                                                                                                            <td><div id="div6" style="display:none;margin-bottom: -22px;margin-top: -10px; "><input type=range name="slider6"  id="slider6" value="" step="0.5" onchange="outputUpdate6(value)" ><%--<output for=fader id=volume6 class="output"></output>--%></div></td>
                                                                                                                            <td> <input type="text" name="texts6"  id="texts6" class="gridCol" align="left" value="" readonly></td>
                                                                                                                            <td> <input type="text" name="textc6"  id="textc6" class="gridCol" align="left" value="" readonly></td>
                                                                                                                            </tr>
                                                                                                                            <tr class="gridRowEven">
                                                                                                                            <input type="hidden" name="textp7"  id="textp7" class="gridCol"  value="" readonly>    
                                                                                                                            <input type="hidden" name="text7"  id="text7" class="gridCol" align="left" value="" readonly>
                                                                                                                            <input type="hidden" name="textw7"  id="textw7" class="gridCol"  value="" readonly>
                                                                                                                            <input type="hidden" name="textm7"  id="textm7" class="gridCol"  value="" readonly>
                                                                                                                            <td> <input type="text" name="texti7"  id="texti7" class="gridCol" align="left" value="" readonly></td>
                                                                                                                            <td><div id="div7" style="display:none;margin-bottom: -22px;margin-top: -10px; "><input type=range name="slider7" id="slider7" value="" step="0.5" onchange="outputUpdate7(value)" ><%--<output for=fader id=volume7 class="output"></output>--%></div></td>
                                                                                                                            <td> <input type="text" name="texts7"  id="texts7" class="gridCol" align="left" value="" readonly></td>
                                                                                                                            <td> <input type="text" name="textc7"  id="textc7" class="gridCol" align="left" value="" readonly></td>

                                                                                                                            </tr>
                                                                                                                            <tr class="gridRowEven">
                                                                                                                            <input type="hidden" name="textp8"  id="textp8" class="gridCol"  value="" readonly>    
                                                                                                                            <input type="hidden" name="text8"  id="text8" class="gridCol" align="left" value="" readonly>
                                                                                                                            <input type="hidden" name="textw8"  id="textw8" class="gridCol"  value="" readonly>
                                                                                                                            <input type="hidden" name="textm8"  id="textm8" class="gridCol"  value="" readonly>
                                                                                                                            <td> <input type="text" name="texti8"  id="texti8" class="gridCol" align="left" value="" readonly></td>
                                                                                                                            <td><div id="div8" style="display:none;margin-bottom: -22px;margin-top: -10px; "><input type=range name="slider8" id="slider8" step="0.5" value="" onchange="outputUpdate8(value)" ><%--<output for=fader id=volume8 class="output"></output>--%></div></td>
                                                                                                                            <td> <input type="text" name="texts8"  id="texts8" class="gridCol" align="left" value="" readonly></td>
                                                                                                                            <td> <input type="text" name="textc8"  id="textc8" class="gridCol" align="left" value="" readonly></td>
                                                                                                                            </tr>
                                                                                                                            <tr class="gridRowEven">
                                                                                                                            <input type="hidden" name="textp9"  id="textp9" class="gridCol"  value="" readonly>    
                                                                                                                            <input type="hidden" name="text9"  id="text9" class="gridCol" align="left" value="" readonly>
                                                                                                                            <input type="hidden" name="textw9"  id="textw9" class="gridCol"  value="" readonly>
                                                                                                                            <input type="hidden" name="textm9"  id="textm9" class="gridCol"  value="" readonly>
                                                                                                                            <td> <input type="text" name="texti9"  id="texti9" class="gridCol" align="left" value="" readonly></td>
                                                                                                                            <td><div id="div9" style="display:none;margin-bottom: -22px;margin-top: -10px; "><input type=range name="slider9"  id="slider9" value="" step="0.5" onchange="outputUpdate9(value)" ><%--<output for=fader id=volume9 class="output"></output>--%></div></td>
                                                                                                                            <td> <input type="text" name="texts9"  id="texts9" class="gridCol" align="left" value="" readonly></td>
                                                                                                                            <td> <input type="text" name="textc9"  id="textc9" class="gridCol" align="left" value="" readonly></td>
                                                                                                                            </tr>
                                                                                                                            <tr class="gridRowEven">
                                                                                                                            <input type="hidden" name="textp10"  id="textp10" class="gridCol"  value="" readonly>    
                                                                                                                            <input type="hidden" name="text10"  id="text10" class="gridCol" align="left" value="" readonly>
                                                                                                                            <input type="hidden" name="textw10"  id="textw10" class="gridCol"  value="" readonly>
                                                                                                                            <input type="hidden" name="textm10"  id="textm10" class="gridCol"  value="" readonly>
                                                                                                                            <td> <input type="text" name="texti10"  id="texti10" class="gridCol" align="left" value="" readonly></td>
                                                                                                                            <td><div id="div10" style="display:none;margin-bottom: -22px;margin-top: -10px; "><input type=range name="slider10" id="slider10" step="0.5" value="" onchange="outputUpdate10(value)" ><%--<output for=fader id=volume10 class="output"></output>--%></div></td>
                                                                                                                            <td> <input type="text" name="texts10"  id="texts10" class="gridCol" align="left" value="" readonly></td>
                                                                                                                            <td> <input type="text" name="textc10"  id="textc10" class="gridCol" align="left" value="" readonly></td>
                                                                                                                            </tr>
                                                                                                                            <tr class="gridRowEven">
                                                                                                                            <input type="hidden" name="textp11"  id="textp11" class="gridCol"  value="" readonly>    
                                                                                                                            <input type="hidden" name="text11"  id="text11" class="gridCol" align="left" value="" readonly>
                                                                                                                            <input type="hidden" name="textw11"  id="textw11" class="gridCol"  value="" readonly>
                                                                                                                            <input type="hidden" name="textm11"  id="textm11" class="gridCol"  value="" readonly>
                                                                                                                            <td> <input type="text" name="texti11"  id="texti11" class="gridCol" align="left" value="" readonly></td>
                                                                                                                            <td><div id="div11" style="display:none;margin-bottom: -22px;margin-top: -10px; "><input type=range  name="slider11" id="slider11" value="" step="0.5" onchange="outputUpdate11(value)" ><%--<output for=fader id=volume11 class="output"></output>--%></div></td>
                                                                                                                            <td> <input type="text" name="texts11"  id="texts11" class="gridCol" align="left" value="" readonly></td>
                                                                                                                            <td> <input type="text" name="textc11"  id="textc11" class="gridCol" align="left" value="" readonly></td>
                                                                                                                            </tr>
                                                                                                                            <tr class="gridRowEven">
                                                                                                                            <input type="hidden" name="textp12"  id="textp12" class="gridCol"  value="" readonly>    
                                                                                                                            <input type="hidden" name="text12"  id="text12" class="gridCol" align="left" value="" readonly>
                                                                                                                            <input type="hidden" name="textw12"  id="textw12" class="gridCol"  value="" readonly>
                                                                                                                            <input type="hidden" name="textm12"  id="textm12" class="gridCol"  value="" readonly>
                                                                                                                            <td> <input type="text" name="texti12"  id="texti12" class="gridCol" align="left" value="" readonly></td>
                                                                                                                            <td><div id="div12" style="display:none;margin-bottom: -22px;margin-top: -10px; "><input type=range  id="slider12" name="slider12" step="0.5" value="" onchange="outputUpdate12(value)" ><%--<output for=fader id=volume12 class="output"></output>--%></div></td>                                                                                                                                
                                                                                                                            <td> <input type="text" name="texts12"  id="texts12" class="gridCol" align="left" value="" readonly></td>
                                                                                                                            <td> <input type="text" name="textc12"  id="textc12" class="gridCol" align="left" value="" readonly></td>
                                                                                                                            </tr>
                                                                                                                            <tr class="gridRowEven">
                                                                                                                            <input type="hidden" name="textp13"  id="textp13" class="gridCol"  value="" readonly>    
                                                                                                                            <input type="hidden" name="text13"  id="text13" class="gridCol" align="left" value="" readonly>
                                                                                                                            <input type="hidden" name="textw13"  id="textw13" class="gridCol"  value="" readonly>
                                                                                                                            <input type="hidden" name="textm13"  id="textm13" class="gridCol"  value="" readonly>
                                                                                                                            <td> <input type="text" name="texti13"  id="texti13" class="gridCol" align="left" value="" readonly></td>
                                                                                                                            <td><div id="div13" style="display:none;margin-bottom: -22px;margin-top: -10px; "><input type=range  id="slider13" name="slider13" step="0.5" value="" onchange="outputUpdate13(value)" ><%--<output for=fader id=volume13 class="output"></output>--%></div></td>                                                                                                                                
                                                                                                                            <td> <input type="text" name="texts13"  id="texts13" class="gridCol" align="left" value="" readonly></td>
                                                                                                                            <td> <input type="text" name="textc13"  id="textc13" class="gridCol" align="left" value="" readonly></td>
                                                                                                                            </tr>
                                                                                                                            <tr class="gridRowEven">
                                                                                                                            <input type="hidden" name="textp14"  id="textp14" class="gridCol"  value="" readonly>   
                                                                                                                            <input type="hidden" name="text14"  id="text14" class="gridCol" align="left" value="" readonly>
                                                                                                                            <input type="hidden" name="textw14"  id="textw14" class="gridCol"  value="" readonly>
                                                                                                                            <input type="hidden" name="textm14"  id="textm14" class="gridCol"  value="" readonly>
                                                                                                                            <td> <input type="text" name="texti14"  id="texti14" class="gridCol" align="left" value="" readonly></td>
                                                                                                                            <td><div id="div14" style="display:none;margin-bottom: -22px;margin-top: -10px; "><input type=range  id="slider14" name="slider14" step="0.5" value="" onchange="outputUpdate14(value)" ><%--<output for=fader id=volume14 class="output"></output>--%></div></td>                                                                                                                                
                                                                                                                            <td> <input type="text" name="texts14"  id="texts14" class="gridCol" align="left" value="" readonly></td>
                                                                                                                            <td> <input type="text" name="textc14"  id="textc14" class="gridCol" align="left" value="" readonly></td>
                                                                                                                            </tr>
                                                                                                                            <tr class="gridRowEven">
                                                                                                                            <input type="hidden" name="textp15"  id="textp15" class="gridCol"  value="" readonly>    
                                                                                                                            <input type="hidden" name="text15"  id="text15" class="gridCol" align="left" value="" readonly>
                                                                                                                            <input type="hidden" name="textw15"  id="textw15" class="gridCol"  value="" readonly>
                                                                                                                            <input type="hidden" name="textm15"  id="textm15" class="gridCol"  value="" readonly>
                                                                                                                            <td> <input type="text" name="texti15"  id="texti15" class="gridCol" align="left" value="" readonly></td>
                                                                                                                            <td><div id="div15" style="display:none;margin-bottom: -22px;margin-top: -10px; "><input type=range  id="slider15" name="slider15" step="0.5" value="%" onchange="outputUpdate15(value)" ><%--<output for=fader id=volume15 class="output"></output>--%></div></td>                                                                                                                                
                                                                                                                            <td> <input type="text" name="texts15"  id="texts15" class="gridCol" align="left" value="" readonly></td>
                                                                                                                            <td> <input type="text" name="textc15"  id="textc15" class="gridCol" align="left" value="" readonly></td>
                                                                                                                            </tr>
                                                                                                                            <tr class="gridPager">
                                                                                                                                <td colspan="5" align="left" class="gridFooter" ></td>
                                                                                                                            </tr>
                                                                                                                        </table>
                                                                                                                    </div>
                                                                                                                </td></tr>

                                                                                                        </table>   
                                                                                                    </td></tr>

                                                                                            </table>                   
                                                                                        </div>
                                                                                    </td></tr>

                                                                            </table></s:form></div></td></tr>


                                                                <script type="text/JavaScript">
                                                                    /*  var cal1 = new CalendarTime(document.forms['issuesForm'].elements['dateCreated']);
                                                   cal1.year_scroll = true;
                                                   cal1.time_comp = true;*/                                                           
                                            
                                                                    var cal2 = new CalendarTime(document.forms['addReviewForm'].elements['fromDate']);
                                                                    cal2.year_scroll = true;
                                                                    cal2.time_comp = true;
                                            
                                                                    var cal3 = new CalendarTime(document.forms['addReviewForm'].elements['toDate']);
                                                                    cal3.year_scroll = true;
                                                                    cal3.time_comp = true;
                                                                </script>

                                                                <tr><td align="center"><div class="searchReview box" id="searchReviewDiv"  style="alignment-adjust:central;margin-top:-7px;">
                                                                        <s:form name="searchReviewForm" id="searchReviewForm" theme="simple">
                                                                            <table cellpadding="1" cellspacing="1" border="0" width="85%" >
                                                                                <tr>
                                                                                    <td class="fieldLabel">Department :<FONT color="red"  ><em>*</em></FONT></td>
                                                                                    <td><s:select label="Select Department" 
                                                                                              name="searchDepartmentForPerformance"
                                                                                              id="searchDepartmentForPerformance"
                                                                                              list="departmentIdList" cssClass="inputSelect" value="%{currentEmployee.searchDepartmentForPerformance}" onchange="getEmpTitleDataV12();"/></td>
                                                                                    <td class="fieldLabel">Title :</td>  
                                                                                    <td><s:select label="Select Title"
                                                                                              headerKey=""
                                                                                              headerValue="-- Please Select --"
                                                                                              name="searchTitleIdForPerformance"  id="searchTitleIdForPerformance"
                                                                                              list="titleIdList" cssClass="inputSelect" value="%{currentEmployee.searchTitleIdForPerformance}" onchange=""/></td>      
                                                                                </tr> 
                                                                                <tr>
                                                                                    <td class="fieldLabel">Employee Name:</td>
                                                                                    <td>
                                                                                        <s:textfield name="empNameForSearch" cssClass="inputTextBlue" id="empNameForSearch" onkeyup="getAllEmpNames1();" value="%{empNameForSearch}"/>
                                                                                        <s:hidden name="empNameLoginIdForSearch" cssClass="inputTextBlue" id="empNameLoginIdForSearch" value="%{empNameLoginIdForSearch}" />
                                                                                        <div id="validationMessage1"></div>
                                                                                    </td>     

                                                                                    <td class="fieldLabel">Title :</td>  
                                                                                    <td> <s:textfield name="empTitleForSearch" cssClass="inputTextBlue" id="empTitleForSearch"  value="%{empTitleForSearch}" onchange="fieldLenghtValidator(this);"/></td>      
                                                                                </tr> 
                                                                                <tr>         
                                                                                    <td class="fieldLabel">Review From Date&nbsp;:<FONT color="red"  ><em>*</em></FONT></td>
                                                                                    <td>

                                                                                        <s:textfield  name="fromDateForSearch" id="fromDateForSearch" value="%{fromDateForSearch}" cssClass="inputTextBlue"  />
                                                                                        <a href="javascript:cal4.popup();"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                                                width="20" height="20" border="0"></a> 
                                                                                    </td>
                                                                                    <td class="fieldLabel">Review To Date&nbsp;:<FONT color="red"  ><em>*</em></FONT></td>
                                                                                    <td colspan="3">

                                                                                        <s:textfield name="toDateForSearch" id="toDateForSearch" value="%{toDateForSearch}"  cssClass="inputTextBlue"/>
                                                                                        <a href="javascript:cal5.popup();">
                                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                 width="20" height="20" border="0"></a>

                                                                                    </td>

                                                                                    <td>
                                                                                        <input type="button" value="Search" class="buttonBg" onclick="getAllReviewedPerformances();">
                                                                                    </td>

                                                                                </tr>
                                                                            </table>
                                                                            <table cellpadding="1" cellspacing="1" border="0" width="75%" >
                                                                                <tr>
                                                                                    <td height="20px" align="center" colspan="9">
                                                                                        <div id="loadActMessage" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                    </td>
                                                                                </tr>

                                                                                <tr>
                                                                                    <td>
                                                                                        <br>
                                                                                        <table align="center" cellpadding="2" border="0" cellspacing="1" width="50%" >

                                                                                            <tr>
                                                                                                <td>
                                                                                                    <br>
                                                                                                    <div id="performanceList" style="display: block">
                                                                                                        <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                                                        <table id="tblEmpPerformance" align='center' cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750'>
                                                                                                            <COLGROUP ALIGN="left" >
                                                                                                                <COL width="5%">
                                                                                                                <COL width="15%">
                                                                                                                <COL width="10%">
                                                                                                                <COL width="10%">
                                                                                                                <COL width="13%">
                                                                                                                <COL width="10%">

                                                                                                        </table>
                                                                                                        <br>
                                                                                                        <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                                                                    </div>
                                                                                                </td>
                                                                                            </tr>
                                                                                        </table>    
                                                                                    </td>
                                                                                </tr>
                                                                            </table>     

                                                                        </s:form>
                                                                        <script type="text/JavaScript">
                                                                            /*  var cal1 = new CalendarTime(document.forms['issuesForm'].elements['dateCreated']);
                                                    cal1.year_scroll = true;
                                                    cal1.time_comp = true;*/                                                           
                                            
                                                                            var cal4 = new CalendarTime(document.forms['searchReviewForm'].elements['fromDateForSearch']);
                                                                            cal4.year_scroll = true;
                                                                            cal4.time_comp = true;
                                            
                                                                            var cal5 = new CalendarTime(document.forms['searchReviewForm'].elements['toDateForSearch']);
                                                                            cal5.year_scroll = true;
                                                                            cal5.time_comp = true;
                                                                        </script>
                                                                    </div>
                                                                </td>
                                                            </tr>

                                                        </table>



                                                    </div></td></tr>

                                            <%-- <tr><td><div class="hubble box">You have selected <strong>Hubble checkbox</strong> so i am here</div></td></tr> 
                                    <tr><td><div class="project box">You have selected <strong>project checkbox</strong> so i am here</div></td></tr>--%>
                                        </table>
                                    </s:form>
                                </div>
                            </div>
                        </td>

                        <td width="850px" class="cellBorder" valign="top" style="padding:5px 5px;">

                        </td>
                        <!--//END DATA COLUMN : Coloumn for Screen Content-->
                    </tr>
                </table>
            </td>
        </tr>

        <!--//END DATA RECORD : Record for LeftMenu and Body Content-->

        <!--//START FOOTER : Record for Footer Background and Content-->
        <tr class="footerBg">
            <td align="center"><s:include value="/includes/template/Footer.jsp"/>   </td>
        </tr>
        <tr>
            <td>

                <div style="display: none; position: absolute; top:170px;left:320px;overflow:auto;" id="menu-popup">
                    <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" />
                </div>

            </td>
        </tr>
        <%-- <tr>
             <td>
                 
                 <div style="display: none; position: absolute; top:170px;left:320px;overflow:auto;" id="menu-popup">
                     <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" />
                 </div>
                 
             </td>
         </tr> --%>

        <!--//END FOOTER : Record for Footer Background and Content-->

    </table>
    <!--//END MAIN TABLE : Table for template Structure-->

    <script>
                                    
        function outputUpdate1(vol) {
    
            var max=document.querySelector('#textm1').value;
            var totWeighatge=document.querySelector('#textw1').value;
            var weightage=(vol/max)*(totWeighatge);
            //alert(weightage);
            //document.querySelector('#volume1').value = vol;
            document.querySelector('#texts1').value = vol;
            document.querySelector('#textc1').value = weightage.toFixed(2);

        }
        function outputUpdate2(vol) {
            var max=document.querySelector('#textm2').value;
            var totWeighatge=document.querySelector('#textw2').value;
            var weightage=(vol/max)*(totWeighatge);
            //document.querySelector('#volume2').value = vol;
            document.querySelector('#texts2').value = vol;
            document.querySelector('#textc2').value =weightage.toFixed(2);

        }
        function outputUpdate3(vol) {
            var max=document.querySelector('#textm3').value;
            var totWeighatge=document.querySelector('#textw3').value;
            var weightage=(vol/max)*(totWeighatge);
            //document.querySelector('#volume3').value = vol;
            document.querySelector('#texts3').value = vol;
            document.querySelector('#textc3').value = weightage.toFixed(2);
        }
        function outputUpdate4(vol) {
            var max=document.querySelector('#textm4').value;
            var totWeighatge=document.querySelector('#textw4').value;
            var weightage=(vol/max)*(totWeighatge);   
            //document.querySelector('#volume4').value = vol;
            document.querySelector('#texts4').value = vol;
            document.querySelector('#textc4').value = weightage.toFixed(2);
        }
        function outputUpdate5(vol) {
            var max=document.querySelector('#textm5').value;
            var totWeighatge=document.querySelector('#textw5').value;
            var weightage=(vol/max)*(totWeighatge);
            //document.querySelector('#volume5').value = vol;
            document.querySelector('#texts5').value = vol;
            document.querySelector('#textc5').value = weightage.toFixed(2);
        }
        function outputUpdate6(vol) {
            var max=document.querySelector('#textm6').value;
            var totWeighatge=document.querySelector('#textw6').value;
            var weightage=(vol/max)*(totWeighatge);
            //document.querySelector('#volume6').value = vol;
            document.querySelector('#texts6').value = vol;
            document.querySelector('#textc6').value = weightage.toFixed(2);
        }
        function outputUpdate7(vol) {
            var max=document.querySelector('#textm7').value;
            var totWeighatge=document.querySelector('#textw7').value;
            var weightage=(vol/max)*(totWeighatge);
            //document.querySelector('#volume7').value = vol;
            document.querySelector('#texts7').value = vol;
            document.querySelector('#textc7').value = weightage.toFixed(2);
        }
        function outputUpdate8(vol) {
            var max=document.querySelector('#textm8').value;
            var totWeighatge=document.querySelector('#textw8').value;
            var weightage=(vol/max)*(totWeighatge);  
            //document.querySelector('#volume8').value = vol;
            document.querySelector('#texts8').value = vol;
            document.querySelector('#textc8').value = weightage.toFixed(2);
        }
        function outputUpdate9(vol) {
            var max=document.querySelector('#textm9').value;
            var totWeighatge=document.querySelector('#textw9').value;
            var weightage=(vol/max)*(totWeighatge);
            //document.querySelector('#volume9').value = vol;
            document.querySelector('#texts9').value = vol;
            document.querySelector('#textc9').value = weightage.toFixed(2);
        }
        function outputUpdate10(vol) {
            var max=document.querySelector('#textm10').value;
            var totWeighatge=document.querySelector('#textw10').value;
            var weightage=(vol/max)*(totWeighatge);
            //document.querySelector('#volume10').value = vol;
            document.querySelector('#texts10').value = vol;
            document.querySelector('#textc10').value = weightage.toFixed(2);
        }
        function outputUpdate11(vol) {
            var max=document.querySelector('#textm11').value;
            var totWeighatge=document.querySelector('#textw11').value;
            var weightage=(vol/max)*(totWeighatge); 
            //document.querySelector('#volume11').value = vol;
            document.querySelector('#texts11').value = vol;
            document.querySelector('#textc11').value = weightage.toFixed(2);
        }
        function outputUpdate12(vol) {
            var max=document.querySelector('#textm12').value;
            var totWeighatge=document.querySelector('#textw12').value;
            var weightage=(vol/max)*(totWeighatge);  
            //document.querySelector('#volume12').value = vol;
            document.querySelector('#texts12').value = vol;
            document.querySelector('#textc12').value = weightage.toFixed(2);
        }
        function outputUpdate13(vol) {
            var max=document.querySelector('#textm13').value;
            var totWeighatge=document.querySelector('#textw13').value;
            var weightage=(vol/max)*(totWeighatge);  
            //document.querySelector('#volume13').value = vol;
            document.querySelector('#texts13').value = vol;
            document.querySelector('#textc13').value = weightage.toFixed(2);
        }
        function outputUpdate14(vol) {
    
            var max=document.querySelector('#textm14').value;
            var totWeighatge=document.querySelector('#textw14').value;
            var weightage=(vol/max)*(totWeighatge);
            //document.querySelector('#volume14').value = vol;
            document.querySelector('#texts14').value = vol;
            document.querySelector('#textc14').value = weightage.toFixed(2);
        }
        function outputUpdate15(vol) {
            var max=document.querySelector('#textm15').value;
            var totWeighatge=document.querySelector('#textw15').value;
            var weightage=(vol/max)*(totWeighatge);   

            //document.querySelector('#volume15').value = vol;
            document.querySelector('#texts15').value = vol;
            document.querySelector('#textc15').value = weightage.toFixed(2);
        }



    </script>


<script type="text/javascript">
		$(window).load(function(){
			init();

		});
		</script>
</body>
</html>
