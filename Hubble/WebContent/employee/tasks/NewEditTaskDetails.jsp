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
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css?ver=1.0"/>">
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
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ajax/AjaxPopup.js?ver=1.0"/>"></script> 
    <script type="text/javascript" src="<s:url value="/includes/javascripts/searchIssue.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmpStandardClientValidations.js?ver=1.0"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
    <script type="text/javascript" src="<s:url value="/includes/javascripts/IssueFill.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmployeeAjax.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/TaskManagementValidations.js?ver=1.0"/>"></script>   
    <s:include value="/includes/template/headerScript.html"/>
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
    <script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            // alert($('#empType').val());
             
             
            if($('#empType').val() == "c"){
                // alert("");
                 
                $('#hr').hide();
                $('#hrLabel').hide();
                $('#network').hide();
                $('#networkLabel').hide();
                $('#infra').hide();
                $('#infraLabel').hide();
                $('#others').hide();
                $('#othersLabel').hide();
                   
            }
              
                
        });
                   
              
       
        function setDiv(type)
        {
            // alert(type);
            if(type==0)
            {
                document.getElementById("hrDiv").style.display="block";
            }
            if(type==1)
            {
                document.getElementById("hubbleDiv").style.display="block";
            }
            if(type==2)
            {
                document.getElementById("projectDiv").style.display="block";
            }
            if(type==3)
            {
                document.getElementById("networkDiv").style.display="block";
            }
            if(type==4)
            {
                document.getElementById("infraDiv").style.display="block";
            }
            if(type==5)
            {
                document.getElementById("otherDiv").style.display="block";
            }
        
        }
        function goToHRSubmit(){
            var type="0";
            var priority=document.getElementById("priority").value;
            var issueType=document.getElementById("issueType").value;
            var title=document.getElementById("title").value;
            var comments=document.getElementById("comments").value;
            var primaryAssignTo=document.getElementById("primaryAssignTo").value;
            var secondaryAssignTo=document.getElementById("secondaryAssignTo").value;
            var secondaryAssignToLoginId = document.getElementById("secondaryAssignToLoginId").value;
            var perCompleted=document.getElementById("perCompleted").value;
            var resolution=document.getElementById("resolution").value;
            var dateAssigned=document.getElementById("dateAssigned").value;
            var dateClosed=document.getElementById("dateClosed").value;
            var id=document.getElementById("id").value;
            var statusId=document.getElementById("statusId").value;
            if(priority==""||priority==null||issueType==""||issueType==null)
            {
                alert("Please provide the mandatory fields");
                return false;
            }
            if(primaryAssignTo==secondaryAssignToLoginId)
            {
                alert("Please select different persons for primary and secondary assign to!");
                return false;
            }
            //window.location="NewEditTask.action?type="+type+"&priority="+priority+"&issueType="+issueType+"&title="+title+"&comments="+comments+"&primaryAssignTo="+primaryAssignTo+"&secondaryAssignTo="+secondaryAssignTo+"&perCompleted="+perCompleted+"&resolution="+resolution+"&dateAssigned="+dateAssigned+"&dateClosed="+dateClosed+"&id="+id+"&statusId="+statusId;
        
        }
        function goToHubbleSubmit(){
            var type="1";
            var priorityHubble=document.getElementById("priorityHubble").value;
            var issueTypeHubble=document.getElementById("issueTypeHubble").value;
            var titleHubble=document.getElementById("titleHubble").value;
            var primaryAssignToHubble=document.getElementById("secondaryAssignToMapForHubble").value;
            var secondaryAssignToHubble=document.getElementById("secondaryAssignToHubble").value;
            var secondaryAssignToLoginIdForHubble = document.getElementById("secondaryAssignToLoginIdForHubble").value;
            var commentsHubble=document.getElementById("commentsHubble").value;
            var perCompleted=document.getElementById("perCompleted").value;
            var resolution=document.getElementById("resolution").value;
            var dateAssigned=document.getElementById("dateAssigned").value;
            var dateClosed=document.getElementById("dateClosed").value;
            var id=document.getElementById("id").value;
            var statusId=document.getElementById("statusId").value;
            if(priorityHubble==""||priorityHubble==null||issueTypeHubble==""||issueTypeHubble==null)
            {
                alert("Please provide the mandatory fields");
                return false;
            }
            if(primaryAssignToHubble==secondaryAssignToLoginIdForHubble)
            {
                alert("Please select different persons for primary and secondary assign to!");
                return false;
            }
            // window.location="NewEditTask.action?type="+type+"&priorityHubble="+priorityHubble+"&issueTypeHubble="+issueTypeHubble+"&titleHubble="+titleHubble+"&commentsHubble="+commentsHubble+"&primaryAssignToHubble="+primaryAssignToHubble+"&secondaryAssignToHubble="+secondaryAssignToHubble+"&perCompleted="+perCompleted+"&resolution="+resolution+"&dateAssigned="+dateAssigned+"&dateClosed="+dateClosed+"&id="+id+"&statusId="+statusId;
        }
        function goToProjectsSubmit(){
            var type="2";
            var projectId=document.getElementById("projectId").value;
            var priorityProject=document.getElementById("priorityProject").value;
            var issueTypeProject=document.getElementById("issueTypeProject").value;
            var titleProject=document.getElementById("titleProject").value;
            var primaryAssignToProject=document.getElementById("primaryAssignToProject").value;
            var secondaryAssignToProject=document.getElementById("secondaryAssignToMapForProject").value;
            var secondaryAssignToLoginIdForProject = document.getElementById("secondaryAssignToLoginIdForProject").value;
            var commentsPorject=document.getElementById("commentsProject").value;
            if(priorityProject==""||priorityProject==null||issueTypeProject==""||issueTypeProject==null||projectId==""||projectId==null)
            {
                alert("Please provide the mandatory fields");
                return false;
            }
            else if(primaryAssignToProject==secondaryAssignToLoginIdForProject)
            {
                alert("Please select different persons for primary and secondary assign to!");
                return false;
            }
            else
            {
                return true;
            }
            //var upload=document.getElementById("uploadFileName").value;
 
            //window.location="newAddTask.action?type="+type+"&priorityProject="+priorityProject+"&issueTypeProject="+issueTypeProject+"&titleProject="+titleProject+"&commentsPorject="+commentsPorject+"&primaryAssignToProject="+primaryAssignToProject+"&secondaryAssignToProject="+secondaryAssignToProject+"&projectId="+projectId;
        }
        function goToNetworkSubmit(){
            var type="3";
            var priorityNetwork=document.getElementById("priorityNetwork").value;
            var issueTypeNetwork=document.getElementById("issueTypeNetwork").value;
            var titleNetwork=document.getElementById("titleNetwork").value;
            var primaryAssignToNetwork=document.getElementById("primaryAssignToNetwork").value;
            var secondaryAssignToNetwork=document.getElementById("secondaryAssignToMapForNetwork").value;
            var secondaryAssignToLoginIdForNetwork = document.getElementById("secondaryAssignToLoginIdForNetwork").value;
            var commentsNetwork=document.getElementById("commentsNetwork").value;
            var perCompleted=document.getElementById("perCompleted").value;
            var resolution=document.getElementById("resolution").value;
            var dateAssigned=document.getElementById("dateAssigned").value;
            var dateClosed=document.getElementById("dateClosed").value;
            var id=document.getElementById("id").value;
            var statusId=document.getElementById("statusId").value;
            if(priorityNetwork==""||priorityNetwork==null||issueTypeNetwork==""||issueTypeNetwork==null)
            {
                alert("Please provide the mandatory fields");
                return false;
            }
            if(primaryAssignToNetwork==secondaryAssignToLoginIdForNetwork)
            {
                alert("Please select different persons for primary and secondary assign to!");
                return false;
            }
            //    window.location="NewEditTask.action?type="+type+"&priorityNetwork="+priorityNetwork+"&issueTypeNetwork="+issueTypeNetwork+"&titleNetwork="+titleNetwork+"&commentsNetwork="+commentsNetwork+"&primaryAssignToNetwork="+primaryAssignToNetwork+"&secondaryAssignToNetwork="+secondaryAssignToNetwork+"&perCompleted="+perCompleted+"&resolution="+resolution+"&dateAssigned="+dateAssigned+"&dateClosed="+dateClosed+"&id="+id+"&statusId="+statusId;    
        }        
     
        function goToInfraSubmit(){
            var type="4";
            var priorityInfra=document.getElementById("priorityInfra").value;
            var issueTypeInfra=document.getElementById("issueTypeInfra").value;
            var titleInfra=document.getElementById("titleInfra").value;
            var primaryAssignToInfra=document.getElementById("primaryAssignToInfra").value;
            var secondaryAssignToInfra=document.getElementById("secondaryAssignToMapForInfra").value;
            var secondaryAssignToLoginIdForInfra = document.getElementById("secondaryAssignToLoginIdForInfra").value;
            var commentsInfra=document.getElementById("commentsInfra").value;  
            var perCompleted=document.getElementById("perCompleted").value;
            var resolution=document.getElementById("resolution").value;
            var dateAssigned=document.getElementById("dateAssigned").value;
            var dateClosed=document.getElementById("dateClosed").value;
            var id=document.getElementById("id").value;
            var statusId=document.getElementById("statusId").value;
            if(priorityInfra==""||priorityInfra==null||issueTypeInfra==""||issueTypeInfra==null)
            {
                alert("Please provide the mandatory fields");
                return false;
            }
            if(primaryAssignToInfra==secondaryAssignToLoginIdForInfra)
            {
                alert("Please select different persons for primary and secondary assign to!");
                return false;
            }
            //      window.location="NewEditTask.action?type="+type+"&priorityInfra="+priorityInfra+"&issueTypeInfra="+issueTypeInfra+"&titleInfra="+titleInfra+"&commentsInfra="+commentsInfra+"&primaryAssignToInfra="+primaryAssignToInfra+"&secondaryAssignToInfra="+secondaryAssignToInfra+"&perCompleted="+perCompleted+"&resolution="+resolution+"&dateAssigned="+dateAssigned+"&dateClosed="+dateClosed+"&id="+id+"&statusId="+statusId;    
        }
     
        function goToOthersSubmit(){
            var type="5";
            var priorityOthers=document.getElementById("priorityOthers").value;
            var issueTypeOthers=document.getElementById("issueTypeOthers").value;
            var titleOthers=document.getElementById("titleOthers").value;
            var primaryAssignToforOthers=document.getElementById("primaryAssignToLoginIdforOthers").value;
            var secondaryAssignToOthers=document.getElementById("secondaryAssignToLoginIdForOthers").value;
            //var secondaryAssignToLoginIdForOthers = document.getElementById("secondaryAssignToLoginId").value;
            var commentsOthers=document.getElementById("commentsOthers").value;  
            var perCompleted=document.getElementById("perCompleted").value;
            var resolution=document.getElementById("resolution").value;
            var dateAssigned=document.getElementById("dateAssigned").value;
            var dateClosed=document.getElementById("dateClosed").value;
            var id=document.getElementById("id").value;
            var statusId=document.getElementById("statusId").value;
            if(priorityOthers==""||priorityOthers==null||issueTypeOthers==""||issueTypeOthers==null)
            {
                alert("Please provide the mandatory fields");
                return false;
            }
            if(primaryAssignToforOthers==secondaryAssignToOthers)
            {
                alert("Please select different persons for primary and secondary assign to!");
                return false;
            }
            //      window.location="NewEditTask.action?type="+type+"&priorityInfra="+priorityInfra+"&issueTypeInfra="+issueTypeInfra+"&titleInfra="+titleInfra+"&commentsInfra="+commentsInfra+"&primaryAssignToInfra="+primaryAssignToInfra+"&secondaryAssignToInfra="+secondaryAssignToInfra+"&perCompleted="+perCompleted+"&resolution="+resolution+"&dateAssigned="+dateAssigned+"&dateClosed="+dateClosed+"&id="+id+"&statusId="+statusId;    
        }
     
     
     
        function getTaskDetails(notesId,taskid)
        {
            //alert("hi");
            // var notesid=document.getElementById("notesId").value;
            //  alert(notesId);
            // var typeid=document.getElementById("taskId").value;
            //   alert(taskid);
            var type=document.getElementById("type").value;
            //alert(type);
            var projectId=document.getElementById("projectId").value;
            // alert(projectId);
            window.location = "../tasks/getNotes.action?notesId="+notesId+"&taskId="+taskid+"&type="+type+"&projectId="+projectId;
        }
        
    </script>

    <script type="text/JavaScript">
            
        function attachmentNameValidate(){
    
            var attachmentName= document.frmAttach.taskFileName;
   
       
            if (attachmentName.value != null && (attachmentName.value != "")) {
                if(attachmentName.value.replace(/^\s+|\s+$/g,"").length>50){
                       
                    str = new String(document.frmAttach.taskFileName.value);
                    document.frmAttach.taskFileName.value=str.substring(0,50);
             
                    alert("The FileName must be less than 50 characters");
           
                }
                document.frmAttach.taskFileName.focus();
                return (false);
            }
  
            return (true);
        };


        function attachmentFileNameValidate(){
   
            //alert("attachmentFileNameValidate");
            var attachmentFileName= document.frmAttach.attachmentFileName;
    
       
            if (attachmentFileName.value != null && (attachmentFileName.value != "")) {
                if(attachmentFileName.value.length>40){
                       
                 
                    document.getElementById('attachmentFileName').value = "";
                    alert("The Resume file name must be less than 40 characters.Please rename the resume file name to less than 40 characters.");
           
                }
                var fileName=attachmentFileName.value;
                var ext = fileName.substring(fileName.lastIndexOf('.')+1);

                if(ext=="pdf" || ext=="docx" || ext=="doc" || ext=="zip" || ext=="txt" || ext=="png" || ext=="jpeg" || ext=="xls" || ext=="xlsx" || ext=="jpg"){  
                    return (true);      
                } else{ 
           
                    document.getElementById('attachmentFileName').value = "";
                    alert("Invalid file extension!Please select docx or doc or txt or pdf or xls or xlsx or zip  or jpg or jpeg or png file.");
           
                }
              
              
                document.frmAttach.attachmentFileName.focus();
                return (false);
            }
  
            return (true);
        };


        function checkManadatory() {
            var attachmentFileName= document.getElementById('attachmentFileName').value;
            if(attachmentFileName.length<=0) {
                alert("Please browse file!");
                document.frmAttach.attachmentFileName.focus();
                return (false);
            }
            else {
 
                return (true);
            }

        }

        function getAddTaskAttachment(){
            var taskId=document.getElementById('id').value;
            var type=document.getElementById('type').value;
            var projectId=document.getElementById('projectId').value;
    
            document.getElementById('resultMessage1').innerHTML='';
            document.getElementById('attachmentFileName').value='';
       
            document.getElementById('taskIdOverlay').value=taskId;
            document.getElementById('typeOverlay').value=type;
            if(projectId==''){
                document.getElementById('projectIdOverlay').value=0;
            }else{
                document.getElementById('projectIdOverlay').value=projectId;
            }
            document.getElementById("headerLabel").style.color="white";
            document.getElementById("headerLabel").innerHTML="Add Task Attachment ";
            
            var overlay = document.getElementById('overlayTaskAttachments');
            var specialBox = document.getElementById('specialBoxTaskAttachments');
            overlay.style.opacity = .8;
            if(overlay.style.display == "block"){
                overlay.style.display = "none";
                specialBox.style.display = "none";
            }
            else {
                overlay.style.display = "block";
                specialBox.style.display = "block";
            }
            
            
        }

        function  toggleCloseUploadOverlay1(){
            var overlay = document.getElementById('overlayTaskAttachments');
            var specialBox = document.getElementById('specialBoxTaskAttachments');
            overlay.style.opacity = .8;
            if(overlay.style.display == "block"){
                overlay.style.display = "none";
                specialBox.style.display = "none";
            }
            else {
                overlay.style.display = "block";
                specialBox.style.display = "block";
            }
        }
 

        function checkManadatoryNotes() {
            var notesName= document.getElementById('notes').value;
            if(notesName.trim().length==0) {
       
   
                alert("Please select notes!");
                //document.frmAttach.notesName.focus();
                return (false);
            }else {
                return (true);
            }



        }
 
 
 
 
 
        function getAddTaskNotes(action){
            var taskId=document.getElementById('id').value;
            var type=document.getElementById('type').value;
            var projectId=document.getElementById('projectId').value;
    
            document.getElementById('resultMessage2').innerHTML='';
       
            document.getElementById('taskIdOverlayNotes').value=taskId;
            document.getElementById('typeOverlayNotes').value=type;
            if(projectId==''){
                document.getElementById('projectIdOverlayNotes').value=0;
            }else{
                document.getElementById('projectIdOverlayNotes').value=projectId;
            }
            document.notesForm.action=action;
            document.getElementById("headerLabel2").style.color="white";
            document.getElementById("headerLabel2").innerHTML="Add Notes ";
            
            var overlay = document.getElementById('overlayTaskNotes');
            var specialBox = document.getElementById('specialBoxTaskNotes');
            overlay.style.opacity = .8;
            if(overlay.style.display == "block"){
                overlay.style.display = "none";
                specialBox.style.display = "none";
            }
            else {
                overlay.style.display = "block";
                specialBox.style.display = "block";
            }
            
            
        }

        function  toggleCloseUploadOverlay2(){
            var overlay = document.getElementById('overlayTaskNotes');
            var specialBox = document.getElementById('specialBoxTaskNotes');
            overlay.style.opacity = .8;
            if(overlay.style.display == "block"){
                overlay.style.display = "none";
                specialBox.style.display = "none";
            }
            else {
                overlay.style.display = "block";
                specialBox.style.display = "block";
            }
        }
 
    </script>

    <style type="text/css">
        .box{
            padding: 20px;
            display: none;
            margin-top: -18px;

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
        String queryString1;
        String strTmp1;
        String strSortCol1;
        String strSortOrd1;
        String action1;
        int role1;
        int intSortOrd1 = 0;
        int intCurr1;
        String currentSearch1 = null;
        boolean blnSortAsc1 = true;
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

                                <li ><a href="#" class="selected" rel="issueTab"  >Edit Task </a></li>


                            </ul>
                            <div  style="border:1px solid gray; width:845px;height: 600px; overflow:auto; margin-bottom: 1em;">   
                                <%-- <sx:div id="empProfileTab" label="Profile" > --%>

                                <div id="issueTab" style="width:840px;"> 
                                    <br>
                                    <table cellpadding="0" border="0" cellspacing="0" width="100%">
                                        <tr align="right">
                                            <td class="headerText12" colspan="4" >
                                                <s:property value="#request.resultMessage"/>
                                                <s:property value="%{resM}"/>
                                                <s:property value="#session.resultMsg"/>
                                                <%
                                                    if (session.getAttribute("resultMsg") != null) {
                                                        session.removeAttribute("resultMsg");
                                                    }
                                                %>
                                            </td></tr>
                                        <tr align="right">
                                           
                                            <td>
                                                <input type="button" value="BackToList" class="buttonBg" onclick="history.go(-1);" style="margin-right: 10vw;margin-bottom: 10px"/>
                                            </td>
                                        
                                            </tr>
                                        
                                        <tr>
                                            <td align="left" >
                                                <s:hidden name="type" value="%{currentTask.type}"/>
                                                <s:hidden name="issueType" id="issueType" value="%{currentTask.issueType}"/>
                                                <s:hidden name="issueTypeHubble" id="issueTypeHubble" value="%{currentTask.issueTypeHubble}"/>
                                                <s:hidden name="issueTypeNetwork" id="issueTypeNetwork" value="%{currentTask.issueTypeNetwork}"/>
                                                <s:hidden name="issueTypeInfra" value="%{currentTask.issueTypeInfra}"/>
                                                <s:hidden name="issueTypeOthers" value="%{currentTask.issueTypeOthers}"/>
                                                <s:hidden name="id" id="id" value="%{currentTask.id}"/>


                                                <label class="fieldLabel" style="margin-left: 101px;">Task Related To :</label>
                                                <s:if test="%{currentTask.type == 0}">
                                                    <input type="checkbox"  name="HR" id="hr" value="hr" checked="true" disabled=true> <label  class="fieldLabel">HR&nbsp;</label>
                                                    <input type="checkbox" name="Hubble" id="hubble" value="hubble" disabled=true><label class="fieldLabel">Hubble&nbsp; </label> 

                                                    <input type="checkbox" name="project" id="project" value="project"  disabled=true > <label class="fieldLabel">Projects&nbsp;</label>
                                                    <input type="checkbox" name="network" id="network" value="network"  disabled=true> <label  class="fieldLabel">Systems&nbsp;</label>
                                                    <input type="checkbox" name="infra" id="infra" value="infra"  disabled=true> <label class="fieldLabel">Facilities&nbsp;</label>
                                                    <input type="checkbox" name="others" id="others" value="Others"  disabled=true> <label id="othersLabel" class="fieldLabel">Others&nbsp;</label>
                                                </s:if>
                                                <s:if test="%{currentTask.type == 1}">
                                                    <input type="checkbox" id="hr" name="HR" value="hr" disabled=true > <label id="hrLabel" class="fieldLabel">HR&nbsp;</label>
                                                    <input type="checkbox" name="Hubble" id="hubble" value="hubble" checked="true" disabled=true><label class="fieldLabel">Hubble&nbsp; </label> 

                                                    <input type="checkbox" name="project" id="project" value="project"  disabled=true > <label class="fieldLabel">Projects&nbsp;</label>
                                                    <input type="checkbox" name="network" id="network" value="network"  disabled=true> <label id="networkLabel" class="fieldLabel">Systems&nbsp;</label>
                                                    <input type="checkbox" name="infra" id="infra" value="infra"  disabled=true> <label id="infraLabel"  class="fieldLabel">Facilities&nbsp;</label>
                                                    <input type="checkbox" name="others" id="others" value="others"  disabled=true> <label id="othersLabel"  class="fieldLabel">Others&nbsp;</label>
                                                </s:if>
                                                <s:if test="%{currentTask.type == 2}">
                                                    <input type="checkbox" id="hr" name="HR" value="hr" disabled=true > <label id="hrLabel" class="fieldLabel">HR&nbsp;</label>
                                                    <input type="checkbox" name="Hubble" id="hubble" value="hubble"  disabled=true><label class="fieldLabel">Hubble&nbsp; </label> 

                                                    <input type="checkbox" name="project" id="project" value="project" checked="true" disabled=true > <label class="fieldLabel">Projects&nbsp;</label>
                                                    <input type="checkbox" name="network" id="network" value="network"  disabled=true> <label id="networkLabel" class="fieldLabel">Systems&nbsp;</label>
                                                    <input type="checkbox" name="infra" id="infra" value="infra"  disabled=true> <label  id="infraLabel" class="fieldLabel">Facilities&nbsp;</label>
                                                    <input type="checkbox" name="others" id="others" value="Others"  disabled=true> <label  id="othersLabel" class="fieldLabel">Others&nbsp;</label>
                                                </s:if>
                                                <s:if test="%{currentTask.type == 3}">
                                                    <input type="checkbox" id="hr" name="HR" value="hr" disabled=true > <label class="fieldLabel">HR&nbsp;</label>
                                                    <input type="checkbox" name="Hubble" id="hubble" value="hubble"  disabled=true><label class="fieldLabel">Hubble&nbsp; </label> 

                                                    <input type="checkbox" name="project" id="project" value="project" disabled=true > <label class="fieldLabel">Projects&nbsp;</label>
                                                    <input type="checkbox" name="network" id="network" value="network" checked="true"  disabled=true> <label class="fieldLabel">Systems&nbsp;</label>
                                                    <input type="checkbox" name="infra" id="infra" value="infra"  disabled=true> <label class="fieldLabel">Facilities&nbsp;</label>
                                                    <input type="checkbox" name="others" id="others" value="others"  disabled=true> <label class="fieldLabel">Others&nbsp;</label>
                                                </s:if>
                                                <s:if test="%{currentTask.type == 4}">
                                                    <input type="checkbox" id="hr" name="HR" value="hr" disabled=true > <label class="fieldLabel">HR&nbsp;</label>
                                                    <input type="checkbox" name="Hubble" id="hubble" value="hubble"  disabled=true><label class="fieldLabel">Hubble&nbsp; </label> 

                                                    <input type="checkbox" name="project" id="project" value="project" disabled=true > <label class="fieldLabel">Projects&nbsp;</label>
                                                    <input type="checkbox" name="network" id="network" value="network"  disabled=true> <label class="fieldLabel">Systems&nbsp;</label>
                                                    <input type="checkbox" name="infra" id="infra" value="infra" checked="true"  disabled=true> <label class="fieldLabel">Facilities&nbsp;</label>
                                                    <input type="checkbox" name="others" id="others" value="others" disabled="true"  disabled=true> <label class="fieldLabel">Others&nbsp;</label>
                                                </s:if>
                                                <s:if test="%{currentTask.type == 5}">
                                                    <input type="checkbox" id="hr" name="HR" value="hr" disabled=true > <label class="fieldLabel">HR&nbsp;</label>
                                                    <input type="checkbox" name="Hubble" id="hubble" value="hubble"  disabled=true><label class="fieldLabel">Hubble&nbsp; </label> 

                                                    <input type="checkbox" name="project" id="project" value="project" disabled=true > <label class="fieldLabel">Projects&nbsp;</label>
                                                    <input type="checkbox" name="network" id="network" value="network"  disabled=true> <label class="fieldLabel">Systems&nbsp;</label>
                                                    <input type="checkbox" name="infra" id="infra" value="infra" disabled="true"  disabled=true> <label class="fieldLabel">Facilities&nbsp;</label>
                                                    <input type="checkbox" name="others" id="others" value="others" checked="true"  disabled=true> <label class="fieldLabel" id="othersLabel">Others&nbsp;</label>
                                                </s:if>

                                            </td> </tr></table><table cellpadding="0" border="0" cellspacing="0" width="100%">   
                                        
                                        <tr><td align="center"><div id="hrDiv" class="box" >   
                                                    <s:form name="hrForm" action="../tasks/NewEditTask.action" method="post" theme="simple"   onsubmit="return goToHRSubmit();" enctype="multipart/form-data">

                                                        <table cellpadding="1" cellspacing="1" border="0" width="85%">
                                                            <s:hidden name="type" id="type" value="%{currentTask.type}"/>
                                                            <s:hidden name="issueType" value="%{currentTask.issueType}"/>
                                                            <s:hidden name="empType" id="empType" value="%{#session.empType}"/>

                                                            <s:hidden name="id" id="id" value="%{currentTask.id}"/>
                                                            <tr> <td class="fieldLabel">Type&nbsp;:<FONT color="red"  ><em>*</em></FONT></td> 
                                                                <td ><s:select id="issueType" name="issueType" headerKey="" headerValue="--Please Select--" list="issueTypeMap" value="%{currentTask.issueType}" cssClass="inputSelect" onchange="getAssignedToBasedOnIssueType(this);" disabled="true"/></td>
                                                                <td class="fieldLabel" >Severity&nbsp;:<FONT color="red"  ><em>*</em></FONT></td> 
                                                                <td ><s:select id="priority" name="priority"  list="{'High','Medium','Low'}" value="%{currentTask.priority}" cssClass="inputSelect" /></td>
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel">Title&nbsp;:</td>
                                                                <td colspan="3"><s:textfield cssClass="inputTextarea3" style="height: 22px;width:498px;"  name="title" id="title" value="%{currentTask.title}" readonly="true"/></td>
                                                            </tr> <tr>
                                                                <%--<td class="fieldLabel" >Primary AssignTo:</td>
                                                                <td><s:textfield cssClass="inputTextBlue" name="primaryAssignTo" id="primaryAssignTo" value="%{currentTask.primaryAssignTo}"/><font size="1.5" color="green">(Email)</font></td> --%>
                                                                <td class="fieldLabel">Primary AssignTo&nbsp;:<FONT color="red"  ><em>*</em></FONT></td>
                                                                <td><s:select list="primaryAssignToMap" id="primaryAssignTo" name="primaryAssignTo" placeholder="Primary AssignTo" cssClass="inputSelect" value="%{currentTask.primaryAssignTo}" /></td>
                                                                <%--    <td class="fieldLabel" >Secondary AssignTo:</td>
                                                                    <td><s:textfield cssClass="inputTextBlue" name="secondaryAssignTo" id="secondaryAssignTo" value="%{currentTask.secondaryAssignTo}"/><font size="1.5" color="green">(Email)</font></td></tr> --%>
                                                                <td class="fieldLabel">Secondary AssignTo&nbsp;:</td>
                                                                <td class="">
                                                                    <s:textfield name="secondaryAssignTo" cssClass="inputTextBlue" placeholder="Secondary AssignTo" id="secondaryAssignTo" onkeyup="getAllEmpNames();" value="%{currentTask.secondaryAssignTo}" onchange="fieldLengthValidator(this);"/>
                                                                    <s:hidden name="secondaryAssignToLoginId" cssClass="inputTextBlue" id="secondaryAssignToLoginId" value="%{currentTask.secondaryAssignToLoginId}" />

                                                                    <div  id="validationMessage"></div>
                                                                </td>     
                                                            </tr>
                                                            <tr>         
                                                                <td class="fieldLabel">Start&nbsp;Date&nbsp;:</td>
                                                                <td>

                                                                    <s:textfield  name="dateAssigned" id="dateAssigned" value="%{dateAssigned}" cssClass="inputTextBlue"  />
                                                                    <a href="javascript:cal2.popup();"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                            width="20" height="20" border="0" style="margin-bottom: -5px"></a> 
                                                                </td>
                                                                <td class="fieldLabel">Due&nbsp;Date&nbsp;:</td>
                                                                <s:if test="#session.isUserManager == 1 || #session.isUserTeamLead==1">
                                                                    <td colspan="3">

                                                                        <s:textfield name="dateClosed" id="dateClosed" value="%{dateClosed}"  cssClass="inputTextBlue"/>
                                                                        <a href="javascript:cal3.popup();">
                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                 width="20" height="20" border="0" style="margin-bottom: -5px"></a>

                                                                    </td>
                                                                </s:if>
                                                                <s:else>
                                                                    <td colspan="3">

                                                                        <s:textfield name="dateClosed" id="dateClosed" value="%{dateClosed}"  cssClass="inputTextBlue" readonly="true"/>


                                                                    </td> 
                                                                </s:else>

                                                            </tr>


                                                            <tr>
                                                                <td class="fieldLabel">Estimated&nbsp;Time&nbsp;:</td>
                                                                <td >
                                                                    <s:textfield name="durationTotheTask" cssClass="inputTextBlue2" placeholder="Ex: 20" id="durationTotheTask"  style="width:48px;" value="%{currentTask.durationTotheTask}" onkeypress="return isNumber(event)"/><s:label cssClass="fieldLabel" value="(Hrs)"/>
                                                                </td>
                                                                <td class="fieldLabel">Completed&nbsp;:</td>
                                                                <td>
                                                                    <s:textfield name="perCompleted" id="perCompleted" value="%{currentTask.perCompleted}" onchange="fieldLengthValidator(this);" onkeypress="return isNumberKey(event)" size="2" cssClass="inputTextBlue2"/><s:label cssClass="fieldLabel" value="%"/><s:label cssClass="fieldLabel" value="(Ex:50)"/>
                                                                </td>  
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel"> Status&nbsp;:</td>
                                                                <td>
                                                                    <s:select list="{'Created','Assigned','InProgress','OnHold','Closed'}"
                                                                              name="statusId"
                                                                              id="statusId"
                                                                              value="%{currentTask.statusId}"
                                                                              cssClass="inputSelect"  />
                                                                    <%--   <s:select list="{'Created','InProgress','Closed','Not Closed','Assigned','Not Started' }"
                                                                                     name="statusId"
                                                                                     id="statusId"
                                                                                     value="%{currentTask.statusId}"
                                                                                 cssClass="inputSelect"  />--%>
                                                                </td>
                                                                <s:hidden name="statusPrev" id="statusPrev" value="%{currentTask.statusId}"/>
                                                            </tr>

                                                            <tr>
                                                                <td class="fieldLabel">Description&nbsp;:</td>
                                                                <td colspan="3"><s:textarea rows="4" cols="65" placeholder="Eneter the Description here..." name="comments" cssClass="inputTextarea2" id="comments" value="%{currentTask.comments}" onchange="fieldLengthValidatorTaskComments(this);" style="width:89%"/></td>
                                                            </tr> 
                                                            <tr>
                                                                <td class="fieldLabel">Resolution&nbsp;:
                                                                </td>
                                                                <td colspan="4">
                                                                    <%-- <s:textarea name="resolution" id = "resolution" rows="5" cols="82" value="%{currentTask.resolution}" onchange="fieldLengthValidator(this);" cssClass="inputTextarea"/>
                                                                    --%>
                                                                    <s:textarea name="resolution" id = "resolution" cssClass="inputTextarea3" placeholder="Eneter the Resolution here..." style="width: 496px;" value="%{currentTask.resolution}" onchange="fieldLengthValidator(this);" />
                                                                </td> 

                                                            </tr>

                                                            <tr><td></td><td></td><td></td><td colspan="4" align="center"><br>
                                                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:submit label="Submit" value="Update" cssClass="buttonBg" />
                                                                </td></tr> 

                                                        </table>



                                                    </s:form>


                                                </div></td></tr>
                                        <tr><td align="center"><div id="hubbleDiv" class="hubble box"  style="alignment-adjust:central;">   
                                                    <s:form name="hubbleForm" action="../tasks/NewEditTask.action" method="post" theme="simple" onsubmit="return goToHubbleSubmit();" enctype="multipart/form-data">

                                                        <table cellpadding="1" cellspacing="1" border="0" width="85%">
                                                            <s:hidden name="type" id="type" value="%{currentTask.type}"/>
                                                            <s:hidden name="issueTypeHubble" value="%{currentTask.issueTypeHubble}"/>


                                                            <s:hidden name="id" id="id" value="%{currentTask.id}"/>
                                                            <tr> <td class="fieldLabel" >Type&nbsp;::<FONT color="red"  ><em>*</em></FONT></td> 
                                                                <td ><s:select id="issueTypeHubble" name="issueTypeHubble" headerKey="" headerValue="--Please Select--"  list="issueTypeMap" value="%{currentTask.issueTypeHubble}" cssClass="inputSelect" onchange="getAssignedToBasedOnIssueTypeForHubble(this);" disabled="true"/></td>
                                                                <td class="fieldLabel" >Severity&nbsp;::<FONT color="red"  ><em>*</em></FONT></td> 
                                                                <td ><s:select id="priorityHubble" name="priorityHubble"  list="{'High','Medium','Low'}" value="%{currentTask.priorityHubble}" cssClass="inputSelect"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel" >Title&nbsp;:</td>
                                                                <td colspan="3"><s:textfield cssClass="inputTextarea3" style="height: 22px;width:498px;" name="titleHubble" id="titleHubble" value="%{currentTask.titleHubble}" readonly="true" /></td>

                                                            </tr>  
                                                            <tr>

                                                                <td class="fieldLabel">Primary AssignTo&nbsp;:<FONT color="red"  ><em>*</em></FONT></td>
                                                                <td><s:select list="primaryAssignToMapForHubble" id="primaryAssignToHubble" placeholder="Primary AssignTo" name="primaryAssignToHubble"  cssClass="inputSelect" value="%{currentTask.primaryAssignToHubble}" /></td>

                                                                <%--  <s:if test="%{currentTask.secondaryAssignToHubble == 1}">
                                                                                   <td class="fieldLabel">Secondary AssignTo:</td>
                                                                                   <td><s:select list="secondaryAssignToMapForHubble" id="secondaryAssignToHubble" name="secondaryAssignToHubble" headerKey="" headerValue="--Please Select--" cssClass="inputSelect" value="%{currentTask.secondaryAssignToHubble}" /></td>
                                                                                  
                                                                              </s:if>
                                                                              <s:else>
                                                                   <td class="fieldLabel">Secondary AssignTo:</td>
                                                                  <td><s:select list="secondaryAssignToMapForHubble" id="secondaryAssignToHubble" name="secondaryAssignToHubble"  cssClass="inputSelect" value="%{currentTask.secondaryAssignToHubble}" /></td>
                                                                              </s:else> --%>
                                                                <td class="fieldLabel">Secondary AssignTo&nbsp;:</td>
                                                                <td class="">
                                                                    <s:textfield name="secondaryAssignToHubble" cssClass="inputTextBlue" placeholder="Secondary AssignTo" id="secondaryAssignToMapForHubble" onkeyup="getAllEmpNamesForHubble();" value="%{currentTask.secondaryAssignToHubble}" onchange="fieldLengthValidator(this);"/>
                                                                    <s:hidden name="secondaryAssignToLoginIdForHubble" cssClass="inputTextBlue" id="secondaryAssignToLoginIdForHubble" value="%{currentTask.secondaryAssignToLoginIdForHubble}" />

                                                                    <div  id="validationMessage"></div>
                                                                </td>   
                                                            </tr> 
                                                            <tr>         
                                                                <td class="fieldLabel">Start&nbsp;Date&nbsp;:</td>
                                                                <td>

                                                                    <s:textfield  name="dateAssigned" id="dateAssigned" value="%{dateAssigned}" cssClass="inputTextBlue"  />
                                                                    <a href="javascript:cal4.popup();"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                            width="20" height="20" border="0" style="margin-bottom: -5px"></a> 
                                                                </td>
                                                                <td class="fieldLabel">Due&nbsp;Date&nbsp;:</td>
                                                                <s:if test="#session.isUserManager == 1 || #session.isUserTeamLead==1">
                                                                    <td colspan="3">

                                                                        <s:textfield name="dateClosed" id="dateClosed" value="%{dateClosed}"  cssClass="inputTextBlue"/>
                                                                        <a href="javascript:cal5.popup();">
                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                 width="20" height="20" border="0" style="margin-bottom: -5px"></a>

                                                                    </td>
                                                                </s:if>
                                                                <s:else>
                                                                    <td colspan="3">

                                                                        <s:textfield name="dateClosed" id="dateClosed" value="%{dateClosed}"  cssClass="inputTextBlue" readonly="true"/>


                                                                    </td> 
                                                                </s:else>

                                                            </tr>


                                                            <tr>
                                                                <td class="fieldLabel">Estimated&nbsp;Time&nbsp;:</td>
                                                                <td >
                                                                    <s:textfield name="durationTotheTask" cssClass="inputTextBlue2" placeholder="Ex: 20" id="durationTotheTask"  style="width:48px;" value="%{currentTask.durationTotheTask}" onkeypress="return isNumber(event)"/><s:label cssClass="fieldLabel" value="(Hrs)"/>
                                                                </td>
                                                                <td class="fieldLabel">Completed&nbsp;:</td>
                                                                <td>
                                                                    <s:textfield name="perCompleted" id="perCompleted" value="%{currentTask.perCompleted}" onchange="fieldLengthValidator(this);" onkeypress="return isNumberKey(event)" size="2" cssClass="inputTextBlue2"/><s:label cssClass="fieldLabel" value="%"/><s:label cssClass="fieldLabel" value="(Ex:50)"/>
                                                                </td>  
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel"> Status&nbsp;:</td>
                                                                <td><s:select list="{'Created','Assigned','InProgress','OnHold','Closed'}"
                                                                          name="statusId"
                                                                          id="statusId"
                                                                          value="%{currentTask.statusId}"
                                                                          cssClass="inputSelect"  /></td>
                                                                    <%--      <td> <s:select list="{'Created','InProgress','Closed','Not Closed','Assigned','Not Started' }"
                                                                                        name="statusId"
                                                                                        id="statusId"
                                                                                        value="%{currentTask.statusId}"
                                                                                    cssClass="inputSelect"  />--%>
                                                                </td>
                                                                <s:hidden name="statusPrev" id="statusPrev" value="%{currentTask.statusId}"/>
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel">Description&nbsp;:</td>
                                                                <td colspan="3"><s:textarea rows="4" cols="65" name="commentsHubble" placeholder="Enter Description Here..." cssClass="inputTextarea2" id="commentsHubble" value="%{currentTask.commentsHubble}" onchange="fieldLengthValidatorTaskComments(this);" style="width:89%"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel">Resolution&nbsp;:
                                                                </td>
                                                                <td colspan="4">
                                                                    <%-- <s:textarea name="resolution" id = "resolution" rows="5" cols="82" value="%{currentTask.resolution}" onchange="fieldLengthValidator(this);" cssClass="inputTextarea"/>
                                                                    --%>
                                                                    <s:textarea name="resolutionHubble" id = "resolutionHubble" placeholder="Enter Resolution Here..." style="width: 496px;" cssClass="inputTextarea3" value="%{currentTask.resolutionHubble}" onchange="fieldLengthValidator(this);"/>
                                                                </td> 

                                                            </tr>

                                                            <tr><td></td><td></td><td></td><td colspan="4" align="center"><br>
                                                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:submit label="Submit" value="Update" cssClass="buttonBg" />
                                                                </td></tr>

                                                        </table>



                                                    </s:form>


                                                </div></td></tr>

                                        <tr><td align="center"><div id="projectDiv" class="project box"  style="alignment-adjust:central;">   
                                                    <s:form name="projectForm" action="../tasks/NewEditTask.action" method="post" theme="simple" onsubmit="return goToProjectsSubmit();" enctype="multipart/form-data">

                                                        <table cellpadding="1" cellspacing="1" border="0" width="85%">
                                                            <s:hidden name="type" id="type" value="%{currentTask.type}"/>
                                                            <s:hidden name="issueTypeProject" value="%{currentTask.issueTypeProject}"/>
                                                            <s:hidden name="projectId" id="projectId" value="%{currentTask.projectId}"/>

                                                            <s:hidden name="id" id="id" value="%{currentTask.id}"/>
                                                            <tr>
                                                                <td class="fieldLabel">Projects&nbsp;:<FONT color="red"  ><em>*</em></FONT></td>

                                                                <td ><s:select id="projectId" name="projectId" list="projectsList" value="%{currentTask.projectId}" cssClass="inputSelect" onchange="getTaskEmpDetailsBasedOnProjIssue(this);" disabled="true"/></td>
                                                                <td class="fieldLabel" >Type&nbsp;:<FONT color="red"  ><em>*</em></FONT></td> 
                                                                <td ><s:select id="issueTypeProject" name="issueTypeForProject" headerKey="" headerValue="--Please Select--" list="issueTypeMap" value="%{currentTask.issueTypeProject}" cssClass="inputSelect" onchange="getAssignedToBasedOnIssueTypeForProject(this);" disabled="true"/></td>

                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel" >Severity&nbsp;:<FONT color="red"  ><em>*</em></FONT></td> 
                                                                <td ><s:select id="priorityProject" name="priorityProject"  list="{'High','Medium','Low'}" value="%{currentTask.priorityProject}" cssClass="inputSelect"/></td>
                                                            </tr><tr> <td class="fieldLabel" >Title&nbsp;:</td>
                                                                <td colspan="3"><s:textfield cssClass="inputTextarea3" style="height: 22px;width:498px;" name="titleProject" id="titleProject" value="%{currentTask.titleProject}" readonly="true"/></td>

                                                            </tr>   
                                                            <tr>
                                                                <td class="fieldLabel">Primary AssignTo&nbsp;:<FONT color="red"  ><em>*</em></FONT></td>
                                                                <td><s:select list="primaryAssignToMapForProject" id="primaryAssignToProject" placeholder="Primary AssignTo" name="primaryAssignToProject"  cssClass="inputSelect" value="%{currentTask.primaryAssignToProject}" /></td>

                                                                <%--   <s:if test="%{currentTask.secondaryAssignToProject == 1}">
                                                                                     <td class="fieldLabel">Secondary AssignTo:</td>
                                                                                     <td><s:select list="secondaryAssignToMapForProject" id="secondaryAssignToProject" name="secondaryAssignToProject" headerKey="" headerValue="--Please Select--" cssClass="inputSelect" value="%{currentTask.secondaryAssignToProject}" /></td>
                                                                                    
                                                                                </s:if>
                                                                                <s:else>
                                                                     <td class="fieldLabel">Secondary AssignTo:</td>
                                                                    <td><s:select list="secondaryAssignToMapForProject" id="secondaryAssignToProject" name="secondaryAssignToProject"  cssClass="inputSelect" value="%{currentTask.secondaryAssignToProject}" /></td>
                                                                                </s:else> --%>
                                                                <td class="fieldLabel">Secondary AssignTo&nbsp;:</td>
                                                                <td class="">
                                                                    <s:textfield name="secondaryAssignToProject" cssClass="inputTextBlue" placeholder="Secondary AssignTo" id="secondaryAssignToMapForProject" onkeyup="getAllEmpNamesForProject();" value="%{currentTask.secondaryAssignToProject}" onchange="fieldLenghtValidator(this);"/>
                                                                    <s:hidden name="secondaryAssignToLoginIdForProject" cssClass="inputTextBlue" id="secondaryAssignToLoginIdForProject" value="%{currentTask.secondaryAssignToLoginIdForProject}" />

                                                                    <div  id="validationMessage"></div>
                                                                </td>   
                                                            </tr>
                                                            <tr>         
                                                                <td class="fieldLabel">Start&nbsp;Date&nbsp;:</td>
                                                                <td>

                                                                    <s:textfield  name="dateAssigned" id="dateAssigned" value="%{dateAssigned}" cssClass="inputTextBlue"  />
                                                                    <a href="javascript:cal6.popup();"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                            width="20" height="20" border="0" style="margin-bottom: -5px"></a> 
                                                                </td>
                                                                <td class="fieldLabel">Due&nbsp;Date&nbsp;:</td>
                                                                <s:if test="#session.isUserManager == 1 || #session.isUserTeamLead==1">
                                                                    <td colspan="3">

                                                                        <s:textfield name="dateClosed" id="dateClosed" value="%{dateClosed}"  cssClass="inputTextBlue"/>
                                                                        <a href="javascript:cal7.popup();">
                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                 width="20" height="20" border="0" style="margin-bottom: -5px"></a>

                                                                    </td>
                                                                </s:if>
                                                                <s:else>
                                                                    <td colspan="3">

                                                                        <s:textfield name="dateClosed" id="dateClosed" value="%{dateClosed}"  cssClass="inputTextBlue" readonly="true"/>


                                                                    </td> 
                                                                </s:else>

                                                            </tr>


                                                            <tr>
                                                                <td class="fieldLabel">Estimated&nbsp;Time:</td>
                                                                <td >
                                                                    <s:textfield name="durationTotheTask" cssClass="inputTextBlue2" placeholder="Ex: 20" id="durationTotheTask"  style="width:48px;" value="%{currentTask.durationTotheTask}" onkeypress="return isNumber(event)"/><s:label cssClass="fieldLabel" value="(Hrs)"/>
                                                                </td>

                                                                <td class="fieldLabel">Completed&nbsp;:</td>
                                                                <td>
                                                                    <s:textfield name="perCompleted" id="perCompleted" value="%{currentTask.perCompleted}" onchange="fieldLengthValidator(this);" onkeypress="return isNumberKey(event)" size="2" cssClass="inputTextBlue2"/><s:label cssClass="fieldLabel" value="%"/><s:label cssClass="fieldLabel" value="(Ex:50)"/>
                                                                </td>  </tr>
                                                            <tr>
                                                                <td class="fieldLabel"> Status&nbsp;:</td>
                                                                <td><s:select list="{'Created','Assigned','InProgress','OnHold','Closed'}"
                                                                          name="statusId"
                                                                          id="statusId"
                                                                          value="%{currentTask.statusId}"
                                                                          cssClass="inputSelect"  /></td>
                                                                    <%--  <td> <s:select list="{'Created','InProgress','Closed','Not Closed','Assigned','Not Started' }"
                                                                                       name="statusId"
                                                                                       id="statusId"
                                                                                       value="%{currentTask.statusId}"
                                                                                   cssClass="inputSelect"  /></td>--%>
                                                                    <s:hidden name="statusPrev" id="statusPrev" value="%{currentTask.statusId}"/>
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel">Description&nbsp;:</td>
                                                                <td colspan="3"><s:textarea rows="4" cols="65" name="commentsPorject" placeholder="Enter Description Here..." cssClass="inputTextarea2" id="commentsProject" value="%{currentTask.commentsProject}" onchange="fieldLengthValidatorTaskComments(this);" style="width:89%"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel">Resolution&nbsp;:
                                                                </td>
                                                                <td colspan="4">
                                                                    <%-- <s:textarea name="resolution" id = "resolution" rows="5" cols="82" value="%{currentTask.resolution}" onchange="fieldLengthValidator(this);" cssClass="inputTextarea"/>
                                                                    --%>
                                                                    <s:textarea name="resolutionProject" placeholder="Enter Resolution Here..." id = "resolutionProject" style="width: 496px;" cssClass="inputTextarea3" value="%{currentTask.resolutionProject}" onchange="fieldLengthValidator(this);"/>
                                                                </td> 

                                                            </tr>
                                                            <tr>

                                                                <s:if test="%{currentTask.uploadFileName!='No File Attached' && currentAction !='addTask'}">
                                                                    <td class="fieldLabel">Attachments&nbsp;:</td> 
                                                                    <td colspan="3"> 
                                                                        <a class="navigationText" href="<s:url action="download">
                                                                               <s:param name="Id" value="%{currentTask.attachmentId}"/>
                                                                           </s:url>"><s:property value="%{currentTask.uploadFileName}"/>
                                                                        </a>
                                                                    </td>
                                                                </s:if> </tr>


                                                            <tr><td></td><td></td><td></td><td colspan="4" align="center"><br>
                                                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:submit label="Submit" value="Update" cssClass="buttonBg" />
                                                                </td></tr>





                                                        </table>



                                                    </s:form>


                                                </div></td></tr>


                                        <tr><td align="center"><div  id="networkDiv" class="network box"  style="alignment-adjust:central;">   
                                                    <s:form name="networkForm" action="../tasks/NewEditTask.action" method="post" theme="simple"   onsubmit="return goToNetworkSubmit();" enctype="multipart/form-data">

                                                        <table cellpadding="1" cellspacing="1" border="0" width="85%">
                                                            <s:hidden name="type" id="type" value="%{currentTask.type}"/>
                                                            <s:hidden name="issueTypeNetwork" value="%{currentTask.issueTypeNetwork}"/>


                                                            <s:hidden name="id" id="id" value="%{currentTask.id}"/>
                                                            <tr> <td class="fieldLabel" >Type&nbsp;:<FONT color="red"  ><em>*</em></FONT></td> 
                                                                <td ><s:select id="issueTypeNetwork" name="issueTypeNetwork"  headerKey="" headerValue="--Please Select--" list="issueTypeMap"   value="%{currentTask.issueTypeNetwork}" cssClass="inputSelect" onchange="getAssignedToBasedOnIssueTypeForNetwork(this);" disabled="true"/></td>
                                                                <td class="fieldLabel" >Severity&nbsp;:<FONT color="red"  ><em>*</em></FONT></td> 
                                                                <td ><s:select id="priorityNetwork" name="priorityNetwork"  list="{'High','Medium','Low'}" value="%{currentTask.priorityNetwork}" cssClass="inputSelect"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel">Title&nbsp;:</td>
                                                                <td colspan="3"><s:textfield cssClass="inputTextarea3" style="height: 22px;width:498px;" name="titleNetwork" id="titleNetwork" value="%{currentTask.titleNetwork}" readonly="true"/></td>

                                                            </tr>
                                                            <tr>

                                                                <td class="fieldLabel">Primary AssignTo&nbsp;:<FONT color="red"  ><em>*</em></FONT></td>
                                                                <td><s:select list="primaryAssignToMapForNetwork" id="primaryAssignToNetwork" placeholder="Primary AssignTo" name="primaryAssignToNetwork"  cssClass="inputSelect" value="%{currentTask.primaryAssignToNetwork}" /></td>

                                                                <%-- <s:if test="%{currentTask.secondaryAssignToNetwork == 1}">
                                                                              <td class="fieldLabel">Secondary AssignTo:</td>
                                                                              <td><s:select list="secondaryAssignToMapForNetwork" id="secondaryAssignToNetwork" name="secondaryAssignToNetwork" headerKey="" headerValue="--Please Select--" cssClass="inputSelect" value="%{currentTask.secondaryAssignToNetwork}" /></td>
                                                                             
                                                                         </s:if>
                                                                         <s:else>
                                                              <td class="fieldLabel">Secondary AssignTo:</td>
                                                             <td><s:select list="secondaryAssignToMapForNetwork" id="secondaryAssignToNetwork" name="secondaryAssignToNetwork"  cssClass="inputSelect" value="%{currentTask.secondaryAssignToNetwork}" /></td>
                                                                         </s:else> --%>
                                                                <td class="fieldLabel">Secondary AssignTo&nbsp;:</td>
                                                                <td class="">
                                                                    <s:textfield name="secondaryAssignToNetwork" cssClass="inputTextBlue" placeholder="Secondary AssignTo" id="secondaryAssignToMapForNetwork" onkeyup="getAllEmpNamesForNetwork();" value="%{currentTask.secondaryAssignToNetwork}" onchange="fieldLenghtValidator(this);"/>
                                                                    <s:hidden name="secondaryAssignToLoginIdForNetwork" cssClass="inputTextBlue" id="secondaryAssignToLoginIdForNetwork" value="%{currentTask.secondaryAssignToLoginIdForNetwork}" />

                                                                    <div  id="validationMessage"></div>
                                                                </td>   
                                                            </tr>
                                                            <tr>         
                                                                <td class="fieldLabel">Start&nbsp;Date&nbsp;:</td>
                                                                <td>

                                                                    <s:textfield  name="dateAssigned" id="dateAssigned" value="%{dateAssigned}" cssClass="inputTextBlue"  />
                                                                    <a href="javascript:cal8.popup();"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                            width="20" height="20" border="0" style="margin-bottom: -5px"></a> 
                                                                </td>
                                                                <td class="fieldLabel">Due&nbsp;Date&nbsp;:</td>
                                                                <s:if test="#session.isUserManager == 1 || #session.isUserTeamLead==1">
                                                                    <td colspan="3">

                                                                        <s:textfield name="dateClosed" id="dateClosed" value="%{dateClosed}"  cssClass="inputTextBlue"/>
                                                                        <a href="javascript:cal9.popup();">
                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                 width="20" height="20" border="0" style="margin-bottom: -5px"></a>

                                                                    </td>
                                                                </s:if>
                                                                <s:else>
                                                                    <td colspan="3">

                                                                        <s:textfield name="dateClosed" id="dateClosed" value="%{dateClosed}"  cssClass="inputTextBlue" readonly="true"/>


                                                                    </td> 
                                                                </s:else>

                                                            </tr>


                                                            <tr>
                                                                <td class="fieldLabel">Estimated&nbsp;Time:</td>
                                                                <td >
                                                                    <s:textfield name="durationTotheTask" cssClass="inputTextBlue2" placeholder="Ex: 20" id="durationTotheTask"  style="width:48px;" value="%{currentTask.durationTotheTask}" onkeypress="return isNumber(event)"/><s:label cssClass="fieldLabel" value="(Hrs)"/>
                                                                </td>
                                                                <td class="fieldLabel">Completed&nbsp;:</td>
                                                                <td>
                                                                    <s:textfield name="perCompleted" id="perCompleted" value="%{currentTask.perCompleted}" onchange="fieldLengthValidator(this);" onkeypress="return isNumberKey(event)" size="2" cssClass="inputTextBlue2"/><s:label cssClass="fieldLabel" value="%"/><s:label cssClass="fieldLabel" value="(Ex:50)"/>
                                                                </td>  
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel"> Status&nbsp;:</td>
                                                                <td><s:select list="{'Created','Assigned','InProgress','OnHold','Closed'}"
                                                                          name="statusId"
                                                                          id="statusId"
                                                                          value="%{currentTask.statusId}"
                                                                          cssClass="inputSelect"  /></td>
                                                                    <s:hidden name="statusPrev" id="statusPrev" value="%{currentTask.statusId}"/>
                                                                    <%
                                                                        if (request.getAttribute("currentTask.bridgeCode") != null && !"".equals(request.getAttribute("currentTask.bridgeCode").toString().trim()) && !"0".equals(request.getAttribute("currentTask.bridgeCode").toString())) {
                                                                    %>
                                                                <td class="fieldLabel">Bridge&nbsp; Extension :</td>
                                                                <td>
                                                                    <s:textfield id="bridgeCode" readonly="true" name="bridgeCode" value="%{currentTask.bridgeCode}"   size="2" cssClass="inputTextBlue"/>
                                                                </td>
                                                                <%                                                                            }
                                                                %>
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel">Description&nbsp;:</td>
                                                                <td colspan="3"><s:textarea rows="4" cols="65" name="commentsNetwork" placeholder="Enter Description Here..."  cssClass="inputTextarea2" id="commentsNetwork" value="%{currentTask.commentsNetwork}" onchange="fieldLengthValidatorTaskComments(this);" style="width:89%"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel">Resolution&nbsp;:
                                                                </td>
                                                                <td colspan="4">
                                                                    <%-- <s:textarea name="resolution" id = "resolution" rows="5" cols="82" value="%{currentTask.resolution}" onchange="fieldLengthValidator(this);" cssClass="inputTextarea"/>
                                                                    --%>
                                                                    <s:textarea name="resolutionNetwork" placeholder="Enter Resolution Here..." id = "resolutionNetwork" style="width: 496px;" cssClass="inputTextarea3" value="%{currentTask.resolutionNetwork}" onchange="fieldLengthValidator(this);"/>
                                                                </td> 

                                                            </tr>

                                                            <tr><td></td><td></td><td></td><td colspan="4" align="center"><br>
                                                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:submit label="Submit" value="Update" cssClass="buttonBg" />
                                                                </td></tr>

                                                        </table>



                                                    </s:form>


                                                </div></td></tr>

                                        <tr><td align="center"><div id="infraDiv" class="infra box"  style="alignment-adjust:central;">   
                                                    <s:form name="infraForm" action="../tasks/NewEditTask.action" method="post" theme="simple"  onsubmit="return goToInfraSubmit();" enctype="multipart/form-data">

                                                        <table cellpadding="1" cellspacing="1" border="0" width="85%">
                                                            <s:hidden name="type" id="type" value="%{currentTask.type}"/>
                                                            <s:hidden name="issueTypeInfra" value="%{currentTask.issueTypeInfra}"/>


                                                            <s:hidden name="id" id="id" value="%{currentTask.id}"/>
                                                            <tr> <td class="fieldLabel" >Type&nbsp;<FONT color="red"  ><em>*</em></FONT></td> 
                                                                <td ><s:select id="issueTypeInfra" name="issueTypeInfra" headerKey="" headerValue="--Please Select--"  list="issueTypeMap" value="%{currentTask.issueTypeInfra}" cssClass="inputSelect" onchange="getAssignedToBasedOnIssueTypeForInfra(this);" disabled="true"/></td>
                                                                <td class="fieldLabel" >Severity&nbsp;<FONT color="red"  ><em>*</em></FONT></td> 
                                                                <td ><s:select id="priorityInfra" name="priorityInfra"  list="{'High','Medium','Low'}" value="%{currentTask.priorityInfra}" cssClass="inputSelect"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel">Title&nbsp;</td>
                                                                <td colspan="3"><s:textfield cssClass="inputTextarea3" style="height: 22px;width:498px;" name="titleInfra" id="titleInfra" value="%{currentTask.titleInfra}" readonly="true"/></td>

                                                            </tr>
                                                            <tr>

                                                                <td class="fieldLabel">Primary AssignTo&nbsp;<FONT color="red"  ><em>*</em></FONT></td>
                                                                <td><s:select list="primaryAssignToMapForInfra" placeholder="Primary AssignTo"  id="primaryAssignToInfra" name="primaryAssignToInfra"  cssClass="inputSelect" value="%{currentTask.primaryAssignToInfra}" /></td>

                                                                <%--  <s:if test="%{currentTask.secondaryAssignToInfra == 1}">
                                                                                  <td class="fieldLabel">Secondary AssignTo:</td>
                                                                                  <td><s:select list="secondaryAssignToMapForInfra" id="secondaryAssignToInfra" name="secondaryAssignToInfra" headerKey="" headerValue="--Please Select--" cssClass="inputSelect" value="%{currentTask.secondaryAssignToInfra}" /></td>
                                                                                 
                                                                             </s:if>
                                                                             <s:else>
                                                                  <td class="fieldLabel">Secondary AssignTo:</td>
                                                                 <td><s:select list="secondaryAssignToMapForInfra" id="secondaryAssignToInfra" name="secondaryAssignToInfra"  cssClass="inputSelect" value="%{currentTask.secondaryAssignToInfra}" /></td>
                                                                             </s:else> --%>
                                                                <td class="fieldLabel">Secondary AssignTo&nbsp;</td>
                                                                <td class="">
                                                                    <s:textfield name="secondaryAssignToInfra" placeholder="Secondary AssignTo" cssClass="inputTextBlue" id="secondaryAssignToMapForInfra" onkeyup="getAllEmpNamesForInfra();" value="%{currentTask.secondaryAssignToInfra}" onchange="fieldLengthValidator(this);"/>
                                                                    <s:hidden name="secondaryAssignToLoginIdForInfra" cssClass="inputTextBlue" id="secondaryAssignToLoginIdForInfra" value="%{currentTask.secondaryAssignToLoginIdForInfra}" />

                                                                    <div  id="validationMessage"></div>
                                                                </td> 
                                                            </tr>
                                                            <tr>         
                                                                <td class="fieldLabel">Start&nbsp;Date&nbsp;:</td>
                                                                <td>

                                                                    <s:textfield  name="dateAssigned" id="dateAssigned" value="%{dateAssigned}" cssClass="inputTextBlue"  />
                                                                    <a href="javascript:cal10.popup();"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                             width="20" height="20" border="0" style="margin-bottom: -5px"></a> 
                                                                </td>
                                                                <td class="fieldLabel">Due&nbsp;Date&nbsp;:</td>
                                                                <s:if test="#session.isUserManager == 1 || #session.isUserTeamLead==1">
                                                                    <td colspan="3">

                                                                        <s:textfield name="dateClosed" id="dateClosed" value="%{dateClosed}"  cssClass="inputTextBlue"/>
                                                                        <a href="javascript:cal11.popup();">
                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                 width="20" height="20" border="0" style="margin-bottom: -5px"></a>

                                                                    </td>
                                                                </s:if>
                                                                <s:else>
                                                                    <td colspan="3">

                                                                        <s:textfield name="dateClosed" id="dateClosed" value="%{dateClosed}"  cssClass="inputTextBlue" readonly="true"/>


                                                                    </td> 
                                                                </s:else>

                                                            </tr>


                                                            <tr>
                                                                <td class="fieldLabel">Estimated&nbsp;Time&nbsp;</td>
                                                                <td >
                                                                    <s:textfield name="durationTotheTask" cssClass="inputTextBlue2" placeholder="Ex: 20" id="durationTotheTask"  style="width:48px;" value="%{currentTask.durationTotheTask}" onkeypress="return isNumber(event)"/><s:label cssClass="fieldLabel" value="(Hrs)"/>
                                                                </td>
                                                                <td class="fieldLabel">Completed&nbsp;:</td>
                                                                <td>
                                                                    <s:textfield name="perCompleted" id="perCompleted" value="%{currentTask.perCompleted}" onchange="fieldLengthValidator(this);" onkeypress="return isNumberKey(event)" size="2" cssClass="inputTextBlue2"/><s:label cssClass="fieldLabel" value="%"/><s:label cssClass="fieldLabel" value="(Ex:50)"/>
                                                                </td>  
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel"> Status&nbsp;</td>
                                                                <td><s:select list="{'Created','Assigned','InProgress','OnHold','Closed'}"
                                                                          name="statusId"
                                                                          id="statusId"
                                                                          value="%{currentTask.statusId}"
                                                                          cssClass="inputSelect"  /></td>
                                                                    <s:hidden name="statusPrev" id="statusPrev" value="%{currentTask.statusId}"/>
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel">Description&nbsp;</td>
                                                                <td colspan="3"><s:textarea rows="4" cols="65" name="commentsInfra" placeholder="Enter Description Here..." cssClass="inputTextarea2" id="commentsInfra" value="%{currentTask.commentsInfra}" onchange="fieldLengthValidatorTaskComments(this);" style="width:89%"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel">Resolution&nbsp;
                                                                </td>
                                                                <td colspan="4">
                                                                    <%-- <s:textarea name="resolution" id = "resolution" rows="5" cols="82" value="%{currentTask.resolution}" onchange="fieldLengthValidator(this);" cssClass="inputTextarea"/>
                                                                    --%>
                                                                    <s:textarea name="resolutionInfra" id = "resolutionInfra" placeholder="Enter Resolution Here..." style="width: 496px;" cssClass="inputTextarea3" value="%{currentTask.resolutionInfra}" onchange="fieldLengthValidator(this);"/>
                                                                </td> 

                                                            </tr>

                                                            <tr><td></td><td></td><td></td><td colspan="4" align="center"><br>
                                                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:submit label="Submit" value="Update" cssClass="buttonBg" />
                                                                </td></tr>

                                                        </table>



                                                    </s:form>


                                                </div></td></tr> 



                                        <tr><td align="center"><div id="otherDiv" class="others box"  style="alignment-adjust:central;">   
                                                    <s:form name="othersForm" action="../tasks/NewEditTask.action" method="post" theme="simple"  onsubmit="return goToOthersSubmit();" enctype="multipart/form-data">

                                                        <table cellpadding="1" cellspacing="1" border="0" width="85%">
                                                            <s:hidden name="type" id="type" value="%{currentTask.type}"/>
                                                            <s:hidden name="issueTypeOthers" value="%{currentTask.issueTypeOthers}"/>


                                                            <s:hidden name="id" id="id" value="%{currentTask.id}"/>
                                                            <tr> <td class="fieldLabel" >Type&nbsp;<FONT color="red"  ><em>*</em></FONT></td> 
                                                                <td ><s:select id="issueTypeOthers" name="issueTypeOthers" headerKey="" headerValue="--Please Select--"  list="issueTypeMap" value="%{currentTask.issueTypeOthers}" cssClass="inputSelect" disabled="true"/></td>
                                                                <td class="fieldLabel" >Severity&nbsp;<FONT color="red"  ><em>*</em></FONT></td> 
                                                                <td ><s:select id="priorityOthers" name="priorityOthers"  list="{'High','Medium','Low'}" value="%{currentTask.priorityOthers}" cssClass="inputSelect"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel">Title&nbsp;</td>
                                                                <td colspan="3"><s:textfield cssClass="inputTextarea3" placeholder="Title" style="height: 22px;width:498px;" name="titleOthers" id="titleOthers" value="%{currentTask.titleOthers}" readonly="true"/></td>

                                                            </tr>
                                                            <tr>                                                                  
                                                                <td class="fieldLabel">Primary AssignTo&nbsp;<FONT color="red"  ><em>*</em></FONT></td>
                                                                <td>
                                                                    <div id="others">
                                                                        <s:textfield name="primaryAssignToforOthers" placeholder="Primary AssignTo" cssClass="inputTextBlue" id="primaryAssignToforOthers" onkeyup="getAllEmpNamesForPrimaryOthers();" value="%{currentTask.primaryAssignToforOthers}" />
                                                                        <s:hidden name="primaryAssignToLoginIdforOthers" cssClass="inputTextBlue" id="primaryAssignToLoginIdforOthers" value="%{currentTask.primaryAssignToLoginIdforOthers}" />

                                                                    </div>

                                                                    <%--    <div id="notOthers">
                                                                        <s:select list="primaryAssignToMap" id="primaryAssignTo" name="primaryAssignTo" cssClass="inputSelect" value="%{currentTask.primaryAssignTo}"/>
                                                                        </div> --%>
                                                                </td>                                                    
                                                                <td class="fieldLabel">Secondary AssignTo&nbsp;</td>
                                                                <td class="">
                                                                    <s:textfield name="secondaryAssignToOthers" placeholder="Secondary AssignTo" cssClass="inputTextBlue" id="secondaryAssignToOthers" onkeyup="getAllEmpNamesSecondaryOthers();" value="%{currentTask.secondaryAssignToOthers}" />
                                                                    <s:hidden name="secondaryAssignToLoginIdForOthers" cssClass="inputTextBlue" id="secondaryAssignToLoginIdForOthers" value="%{currentTask.secondaryAssignToLoginIdForOthers}" />

                                                                    <div id="validationMessage"></div>
                                                                </td>      
                                                            </tr>
                                                            <tr>         
                                                                <td class="fieldLabel">Start&nbsp;Date&nbsp;:</td>
                                                                <td>

                                                                    <s:textfield  name="dateAssigned" id="dateAssigned" value="%{dateAssigned}" cssClass="inputTextBlue"  />
                                                                    <a href="javascript:cal22.popup();"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                             width="20" height="20" border="0" style="margin-bottom: -5px"></a> 
                                                                </td>
                                                                <td class="fieldLabel">Due&nbsp;Date&nbsp;:</td>
                                                                <s:if test="#session.isUserManager == 1 || #session.isUserTeamLead==1">
                                                                    <td colspan="3">

                                                                        <s:textfield name="dateClosed" id="dateClosed" value="%{dateClosed}"  cssClass="inputTextBlue"/>
                                                                        <a href="javascript:cal23.popup();">
                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                 width="20" height="20" border="0" style="margin-bottom: -5px"></a>

                                                                    </td>
                                                                </s:if>
                                                                <s:else>
                                                                    <td colspan="3">

                                                                        <s:textfield name="dateClosed" id="dateClosed" value="%{dateClosed}"  cssClass="inputTextBlue" readonly="true"/>


                                                                    </td> 
                                                                </s:else>                                
                                                            </tr>


                                                            <tr>
                                                                <td class="fieldLabel">Estimated&nbsp;Time:</td>
                                                                <td >
                                                                    <s:textfield name="durationTotheTask" placeholder="Ex: 20" cssClass="inputTextBlue2" id="durationTotheTask"  style="width:48px;" value="%{currentTask.durationTotheTask}" onkeypress="return isNumber(event)"/><s:label cssClass="fieldLabel" value="(Hrs)"/>
                                                                </td>

                                                                <td class="fieldLabel">Completed&nbsp;:</td>
                                                                <td>
                                                                    <s:textfield name="perCompleted" id="perCompleted" value="%{currentTask.perCompleted}" onchange="fieldLengthValidator(this);" onkeypress="return isNumberKey(event)" size="2" cssClass="inputTextBlue2"/><s:label cssClass="fieldLabel" value="%"/><s:label cssClass="fieldLabel" value="(Ex:50)"/>
                                                                </td>  
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel"> Status&nbsp;</td>
                                                                <td><s:select list="{'Created','Assigned','InProgress','OnHold','Closed'}"
                                                                          name="statusId"
                                                                          id="statusId"
                                                                          value="%{currentTask.statusId}"
                                                                          cssClass="inputSelect"  /></td>
                                                                    <s:hidden name="statusPrev" id="statusPrev" value="%{currentTask.statusId}"/>
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel">Description&nbsp;</td>
                                                                <td colspan="3"><s:textarea rows="4" placeholder="Enter Description Here..." cols="65" name="commentsOthers" cssClass="inputTextarea2" id="commentsOthers" value="%{currentTask.commentsOthers}" onchange="fieldLengthValidatorTaskComments(this);" style="width:89%"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel">Resolution&nbsp;
                                                                </td>
                                                                <td colspan="4">

                                                                    <s:textarea name="resolutionOthers" placeholder="Enter Resolution Here..." id = "resolutionOthers" style="width: 496px;" cssClass="inputTextarea" value="%{currentTask.resolutionOthers}" rows="3" cols="65" onchange="fieldLengthValidator(this);"/>
                                                                </td> 

                                                            </tr>

                                                            <tr><td></td><td></td><td></td><td colspan="4" align="center"><br>
                                                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:submit label="Submit" value="Update" cssClass="buttonBg" />
                                                                </td></tr>

                                                        </table>



                                                    </s:form>


                                                </div></td></tr> 


                                        <script type="text/JavaScript">
                                            /*  var cal1 = new CalendarTime(document.forms['issuesForm'].elements['dateCreated']);
                                                      cal1.year_scroll = true;
                                                      cal1.time_comp = true;*/                                                           
                                            
                                            var cal2 = new CalendarTime(document.forms['hrForm'].elements['dateAssigned']);
                                            cal2.year_scroll = true;
                                            cal2.time_comp = true;
                                            
                                            var cal3 = new CalendarTime(document.forms['hrForm'].elements['dateClosed']);
                                            cal3.year_scroll = true;
                                            cal3.time_comp = true;
                                                           
                                            var cal4 = new CalendarTime(document.forms['hubbleForm'].elements['dateAssigned']);
                                            cal4.year_scroll = true;
                                            cal4.time_comp = true;
                                            
                                            var cal5 = new CalendarTime(document.forms['hubbleForm'].elements['dateClosed']);
                                            cal5.year_scroll = true;
                                            cal5.time_comp = true;
                                            var cal6 = new CalendarTime(document.forms['projectForm'].elements['dateAssigned']);
                                            cal6.year_scroll = true;
                                            cal6.time_comp = true;
                                            
                                            var cal7 = new CalendarTime(document.forms['projectForm'].elements['dateClosed']);
                                            cal7.year_scroll = true;
                                            cal7.time_comp = true;
                                            var cal8 = new CalendarTime(document.forms['networkForm'].elements['dateAssigned']);
                                            cal8.year_scroll = true;
                                            cal8.time_comp = true;
                                            
                                            var cal9 = new CalendarTime(document.forms['networkForm'].elements['dateClosed']);
                                            cal9.year_scroll = true;
                                            cal9.time_comp = true;
                                            var cal10 = new CalendarTime(document.forms['infraForm'].elements['dateAssigned']);
                                            cal10.year_scroll = true;
                                            cal10.time_comp = true;
                                            
                                            var cal11 = new CalendarTime(document.forms['infraForm'].elements['dateClosed']);
                                            cal11.year_scroll = true;
                                            cal11.time_comp = true;
                                                           
                                            var cal22 = new CalendarTime(document.forms['othersForm'].elements['dateAssigned']);
                                            cal22.year_scro11 = true;
                                            cal22.time_comp = true;
                                            
                                            var cal23 = new CalendarTime(document.forms['othersForm'].elements['dateClosed']);
                                            cal23.year_scro11 = true;
                                            cal23.time_comp = true;
                                                           
                                        </script>
                                    </table>
                                    <s:hidden name="type"  value="%{currentTask.type}"/>  
                                    <s:hidden name="projectId"  value="%{projectId}"/>  
                                    <ul id="taskAttachments" class="shadetabs" >

                                        <li ><a href="#" class="selected" rel="accountsListTab1" >Task&nbsp;Attachments</a></li>
                                        <li ><a href="#"  rel="accountsnotesTab1" >Notes</a></li>
                                        <li ><a href="#"  rel="accountsTaskHistoryTab1" >Task&nbsp;Tracking&nbsp;History</a></li>

                                    </ul> 
                                    <div  style="border:1px solid grey; width:840px; overflow:auto; margin-bottom: 1em;">
                                        <%--    <sx:div id="accountsListTab" label="Accounts List" cssStyle="overflow:auto;"> --%>
                                        <div id="accountsListTab1" class="tabcontent" >

                                            <div id="overlayTaskAttachments"></div> 
                                            <div id="specialBoxTaskAttachments" style="margin-left: 5vw;margin-top: 100px;">
                                                <s:form name="frmAttach" action="doAddTaskAttachment" id="attachmentAdd" theme="simple" method="POST" enctype="multipart/form-data" onsubmit="return checkManadatory();">
                                                    <s:hidden name="taskId" id="taskIdOverlay" />
                                                    <s:hidden name="type" id="typeOverlay"/>
                                                    <s:hidden name="projectId" id="projectIdOverlay"/>
                                                    <table align="center" border="0" cellspacing="0" style="width:100%;">
                                                        <tr>                               
                                                            <td colspan="2" style="background-color: #288AD1" >
                                                                <h3 style="color:darkblue;" align="left">
                                                                    <span id="headerLabel"></span>


                                                                </h3></td>
                                                            <td colspan="2" style="background-color: #288AD1" align="right">

                                                                <a href="#" onmousedown="toggleCloseUploadOverlay1()" >
                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> 

                                                                </a>  

                                                            </td></tr>
                                                        <tr>
                                                            <td colspan="4">
                                                                <div id="load1" style="color: green;display: none;">Loading..</div>
                                                                <div id="resultMessage1"></div>
                                                            </td>
                                                        </tr>    
                                                        <tr><td colspan="6">
                                                                <table style="vertical-align:80%;" align="center">
                                                                    <tr> 
                                                                        <td class="fieldLabel">Task&nbsp;File&nbsp;Name&nbsp;:</td>
                                                                        <td><s:textfield name="taskFileName" cssClass="inputTextarea"   onchange="attachmentNameValidate(document.frmAttach.taskFileName.value);"/></td> 

                                                                        <td class="fieldLabel">Task&nbsp;File&nbsp;:</td>
                                                                        <td colspan="2" ><s:file name="upload" label="file" cssClass="inputTextarea" id="attachmentFileName" onchange="attachmentFileNameValidate();"/></td> 




                                                                    </tr>

                                                                    <tr align="right">
                                                                        <td colspan="4">

                                                                            <s:url id="myUrl" action="getTask.action">

                                                                                <s:param name="taskId" value="%{taskId}"/>
                                                                                <s:param name="type" value="%{type}"/>
                                                                                <s:param name="projectId" value="%{projectId}"/>
                                                                            </s:url>
                                                                            <s:a href="%{myUrl}" cssClass="navigationText" style="text-decoration: none"> <input type="button" class="buttonBg" value="Cancel"></s:a>


                                                                            <s:submit cssClass="buttonBg" value="Save"/></td>

                                                                    </tr> 
                                                                </table>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </s:form>   

                                            </div>


                                            <!--//START TAB : -->
                                            <%--   <sx:div id="accountsListTab" label="%{title}s List" cssStyle="overflow:auto;"> --%>

                                            <%
                                                /* String Variable for storing current position of records in dbgrid*/
                                                strTmp = request.getParameter("txtCurr");

                                                intCurr = 1;

                                                if (strTmp != null) {
                                                    intCurr = Integer.parseInt(strTmp);
                                                }

                                                /* Specifing Shorting Column */
                                                strSortCol = request.getParameter("Colname");

                                                if (strSortCol == null) {
                                                    strSortCol = "DateUploaded";
                                                }

                                                strSortOrd = request.getParameter("txtSortAsc");
                                                if (strSortOrd == null) {
                                                    strSortOrd = "ASC";
                                                }

                                                try {

                                                    /* Getting DataSource using Service Locator */

                                                    connection = ConnectionProvider.getInstance().getConnection();

                                                    //Retrieving Users Rolename from Session Attributes.


                                                    /* Sql query for retrieving resultset from DataBase */
                                                    queryString = null;

                                                    //int authorTopicId = (Integer)request.getAttribute("topicId");
                                                    int objectId = Integer.parseInt(request.getAttribute("taskObjectId").toString());
                                                    String type = request.getAttribute("type").toString();
                                                    String projectId = request.getAttribute("projectId").toString();
                                                    queryString = "SELECT Id, ObjectId, ObjectType, AttachmentName, AttachmentLocation, AttachmentFileName , DateUploaded,UploadedBy FROM tblTaskAttachments WHERE  ObjectId = " + objectId;
                                                    String addAttachmentAction = "../tasks/addTaskAttachment.action?taskId=" + objectId + "&type=" + type + "&projectId=" + projectId;


                                            %>

                                            <table cellpadding="0" cellspacing="0" width="100%" border="0" >
                                                <br>

                                                <tr align="right">
                                                    <%--  <td class="headerText">
                                                                                               
                                                                                                <img alt="Home" 
                                                                                                     src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" 
                                                                                                     width="100%" 
                                                                                                     height="13px" 
                                                                                                     border="0">
                                                                                            </td> --%>

                                                <tr>
                                                    <td>
                                                        <input type="button" Value="Add" class="buttonBg" onclick="getAddTaskAttachment()"/>
                                                    </td>
                                                </tr>
                                                </tr> 

                                                <!-- BEGIN:: DBGrid Specific -->  

                                                <tr>
                                                    <td>
                                                        <div style="width:840px;">
                                                            <s:form action="" theme="simple" name="frmDBGrid" method="post">   
                                                                <grd:dbgrid id="tblTaskAttachments" name="tblTaskAttachments" width="100" pageSize="6" 
                                                                currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">

                                                                    <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                                   imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"/>

                                                                    <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" 
                                                                                    imageAscending="../includes/images/DBGrid/ImgAsc.gif" 
                                                                                    imageDescending="../includes/images/DBGrid/ImgDesc.gif"/>  
                                                                    <grd:rownumcolumn headerText="SNo" width="2" HAlign="center"/>       
                                                                    <grd:textcolumn dataField="AttachmentName" headerText="AttachmentName" width="10"/>
                                                                    <grd:textcolumn dataField="AttachmentFileName" headerText="AttachmentFileName" width="10"/>
                                                                    <grd:textcolumn dataField="UploadedBy" headerText="Uploaded By" width="10"/>
                                                                    <grd:datecolumn dataField="DateUploaded" headerText="Date Uploaded" HAlign="right" dataFormat="MM-dd-yyyy" width="10"/> 
                                                                    <grd:imagecolumn headerText="Download" width="4" HAlign="center" 
                                                                                     imageSrc="../../includes/images/download_11x10.jpg"
                                                                                     linkUrl="taskAttachmentDownload?id={Id}&fileName={AttachmentFileName}" imageBorder="0"
                                                                                     imageWidth="11" imageHeight="10" alterText="Download"></grd:imagecolumn>

                                                                </grd:dbgrid>


                                                                <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                                <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                                <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">

                                                            </div>
                                                        </td>
                                                    </tr>
                                                </table>                                

                                            </s:form>
                                            <%
                                                    connection.close();
                                                    connection = null;
                                                } catch (Exception ex) {
                                                    out.println(ex.toString());
                                                } finally {
                                                    if (connection != null) {
                                                        connection.close();
                                                        connection = null;
                                                    }
                                                }
                                            %>

                                        </div>

                                        <div id="accountsnotesTab1" class="tabcontent" >


                                            <div id="overlayTaskNotes"></div> 
                                            <div id="specialBoxTaskNotes" style="margin-left: 8vw;margin-top: 100px;">
                                                <s:form name="notesForm" action="" id="notesForm" theme="simple" method="POST" enctype="multipart/form-data" onsubmit="return checkManadatoryNotes();">
                                                    <s:hidden name="taskId" id="taskIdOverlayNotes" />
                                                    <s:hidden name="type" id="typeOverlayNotes"/>
                                                    <s:hidden name="notesId" id="notesIdOverlayNotes"/>
                                                    <s:hidden name="projectId" id="projectIdOverlayNotes"/>
                                                    <table align="center" border="0" cellspacing="0" style="width:100%;">
                                                        <tr>                               
                                                            <td colspan="2" style="background-color: #288AD1" >
                                                                <h3 style="color:darkblue;" align="left">
                                                                    <span id="headerLabel2"></span>


                                                                </h3></td>
                                                            <td colspan="2" style="background-color: #288AD1" align="right">

                                                                <a href="#" onmousedown="toggleCloseUploadOverlay2()" >
                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> 

                                                                </a>  

                                                            </td></tr>
                                                        <tr>
                                                            <td colspan="4">
                                                                <div id="load2" style="color: green;display: none;">Loading..</div>
                                                                <div id="resultMessage2"></div>
                                                            </td>
                                                        </tr>    
                                                        <tr><td colspan="6">
                                                                <table style="vertical-align:80%;" align="center">
                                                                    <tr>
                                                                        <td class="fieldLabel">Notes :</td>
                                                                        <td><s:textarea  name="notes"  cols="70" rows="2" cssClass="inputTextarea" id="notes"  value="%{notes}" onchange="return fieldLengthValidator2(this);" /></td> 
                                                                    </tr>

                                                                    <tr align="right">
                                                                        <td colspan="4">

                                                                            <s:url id="myUrl" action="getTask.action">
                                                                                <s:param name="taskId" value="%{taskId}" />
                                                                                <s:param name="notesId" value="%{notesId}"/>
                                                                                <s:param name="type" value="%{type}"/>
                                                                                <s:param name="projectId" value="%{projectId}"/>
                                                                            </s:url>
                                                                            <%--  <a class="navigationText" href="<s:url action="%{myUrl}"></s:url>"><s:property value="%{title}"/></a>  --%>
                                                                            <s:a href="%{myUrl}" cssClass="navigationText" style="text-decoration: none"> <input type="button" class="buttonBg" value="Cancel"></s:a>


                                                                            <s:submit cssClass="buttonBg" value="Save"/></td>

                                                                    </tr> 
                                                                </table>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </s:form>   

                                            </div>

                                            <!--//START TAB : -->
                                            <%--   <sx:div id="accountsListTab" label="%{title}s List" cssStyle="overflow:auto;"> --%>

                                            <%
                                                /* String Variable for storing current position of records in dbgrid*/
                                                strTmp1 = request.getParameter("txtCurr1");

                                                intCurr1 = 1;

                                                if (strTmp1 != null) {
                                                    intCurr1 = Integer.parseInt(strTmp1);
                                                }

                                                /* Specifing Shorting Column */
                                                strSortCol1 = request.getParameter("Notes");

                                                if (strSortCol1 == null) {
                                                    strSortCol1 = "DateUploaded";
                                                }

                                                strSortOrd1 = request.getParameter("txtSortAsc1");
                                                if (strSortOrd1 == null) {
                                                    strSortOrd1 = "ASC1";
                                                }

                                                try {

                                                    /* Getting DataSource using Service Locator */

                                                    connection = ConnectionProvider.getInstance().getConnection();

                                                    //Retrieving Users Rolename from Session Attributes.


                                                    /* Sql query for retrieving resultset from DataBase */
                                                    queryString1 = null;

                                                    //int authorTopicId = (Integer)request.getAttribute("topicId");
                                                    int objectId = Integer.parseInt(request.getAttribute("taskObjectId").toString());
                                                    String type = request.getAttribute("type").toString();
                                                    String projectId = request.getAttribute("projectId").toString();
                                                    String notesId = request.getAttribute("notesId").toString();
                                                    queryString1 = "SELECT Id, Task_Id, Notes, CreatedDate, CreatedBy , ModifiedDate,ModifiedBy FROM tblTaskNotes WHERE  Task_Id= " + objectId;
                                                    String addNotesAction = "../tasks/addTaskNotes.action?taskId=" + objectId + "&type=" + type + "&projectId=" + projectId;


                                            %>

                                            <table cellpadding="0" cellspacing="0" width="100%" border="0" >
                                                <br>

                                                <tr align="right">

                                                <tr>
                                                    <td>
                                                        <input type="button" Value="Add" class="buttonBg" onclick="getAddTaskNotes('doaddTaskNotes')"/>
                                                    </td>
                                                </tr>
                                                </tr> 

                                                <!-- BEGIN:: DBGrid Specific -->  

                                                <tr>
                                                    <td>
                                                        <div style="width:840px;">
                                                            <s:form action="" theme="simple" name="frmDBGrid" method="post">   
                                                                <grd:dbgrid id="tblTaskNotes" name="tblTaskNotes" width="100" pageSize="6" 
                                                                currentPage="<%=intCurr1%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                dataMember="<%=queryString1%>" dataSource="<%=connection%>" cssClass="gridTable">

                                                                    <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                                   imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"/>

                                                                    <grd:gridsorter sortColumn="<%=strSortCol1%>" sortAscending="<%=blnSortAsc1%>" 
                                                                                    imageAscending="../includes/images/DBGrid/ImgAsc.gif" 
                                                                                    imageDescending="../includes/images/DBGrid/ImgDesc.gif"/>  

                                                                    <grd:imagecolumn  headerText="Edit" width="5"  HAlign="center" 
                                                                                      imageSrc="../../includes/images/DBGrid/Edit.gif" 
                                                                                      linkUrl="javascript:getTaskNotesDetails('doUpdateNotes',{Id},'{Task_Id}')" 
                                                                                      imageBorder="0" imageWidth="16" imageHeight="16" 
                                                                                      alterText="Click to edit" /> 


                                                                    <grd:rownumcolumn headerText="SNo" width="2" HAlign="center"/> 
                                                                    <grd:anchorcolumn dataField="Notes" linkUrl="javascript:populateNotesComments('{Notes}')" headerText="Notes"
                                                                                      linkText="Click To View"  width="10" />

                                                                    <grd:textcolumn dataField="CreatedDate" headerText="CreatedDate" dataFormat="MM-dd-yyyy" width="10"/>
                                                                    <grd:textcolumn dataField="CreatedBy" headerText="CreatedBy"   width="10"/>
                                                                    <%-- <grd:textcolumn dataField="ModifiedDate" headerText="ModifyDate" dataFormat="MM-dd-yyyy" width="10"/>
                                                                       "getNotes.action?notesId={Id}&taskId={Task_Id}" 
                                                                    <grd:textcolumn dataField="ModifiedBy" headerText="ModifyBy"   width="10"/> --%>

                                                                    <%--<grd:imagecolumn headerText="Download" width="4" HAlign="center" 
                                                                                     imageSrc="../../includes/images/download_11x10.jpg"javascript:getIssueDescription('{Id}')"
                                                                                     linkUrl="taskAttachmentDownload?id={Id}&fileName={AttachmentFileName}" imageBorder="0"
                                                                                     imageWidth="11" imageHeight="10" alterText="Download"></grd:imagecolumn> --%>

                                                                </grd:dbgrid>


                                                                <input TYPE="hidden" NAME="txtCurr1" VALUE="<%=intCurr1%>">
                                                                <input TYPE="hidden" NAME="txtSortCol1" VALUE="<%=strSortCol1%>">
                                                                <input TYPE="hidden" NAME="txtSortAsc1" VALUE="<%=strSortOrd1%>">

                                                            </div>
                                                        </td>
                                                    </tr>
                                                </table>                                

                                            </s:form>
                                            <%
                                                    connection.close();
                                                    connection = null;
                                                } catch (Exception ex) {
                                                    out.println(ex.toString());
                                                } finally {
                                                    if (connection != null) {
                                                        connection.close();
                                                        connection = null;
                                                    }
                                                }
                                            %>

                                        </div>       

                                        <!-- Task History start -->


                                        <div id="accountsTaskHistoryTab1" class="tabcontent" >




                                            <!--//START TAB : -->
                                            <%--   <sx:div id="accountsListTab" label="%{title}s List" cssStyle="overflow:auto;"> --%>

                                            <%
                                                /* String Variable for storing current position of records in dbgrid*/
                                                strTmp1 = request.getParameter("txtCurr1");

                                                intCurr1 = 1;

                                                if (strTmp1 != null) {
                                                    intCurr1 = Integer.parseInt(strTmp1);
                                                }

                                                /* Specifing Shorting Column */
                                                strSortCol1 = request.getParameter("TaskHistory");

                                                if (strSortCol1 == null) {
                                                    strSortCol1 = "DateUploaded";
                                                }

                                                strSortOrd1 = request.getParameter("txtSortAsc1");
                                                if (strSortOrd1 == null) {
                                                    strSortOrd1 = "ASC1";
                                                }

                                                try {

                                                    /* Getting DataSource using Service Locator */

                                                    connection = ConnectionProvider.getInstance().getConnection();

                                                    //Retrieving Users Rolename from Session Attributes.


                                                    /* Sql query for retrieving resultset from DataBase */
                                                    queryString1 = null;

                                                    //int authorTopicId = (Integer)request.getAttribute("topicId");
                                                    int objectId = Integer.parseInt(request.getAttribute("taskObjectId").toString());
                                                    queryString1 = "SELECT  ActivityBy, ActivityDate, Description,Status  FROM tblTaskTrackingHistory WHERE  TaskId= " + objectId;


                                            %>

                                            <table cellpadding="0" cellspacing="0" width="100%" border="0" >




                                                <!-- BEGIN:: DBGrid Specific -->  

                                                <tr>
                                                    <td>
                                                        <div style="width:840px;">
                                                            <s:form action="" theme="simple" name="frmDBGrid" method="post">   
                                                                <grd:dbgrid id="tblTaskTrackingHistory" name="tblTaskTrackingHistory" width="100" pageSize="6" 
                                                                currentPage="<%=intCurr1%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                dataMember="<%=queryString1%>" dataSource="<%=connection%>" cssClass="gridTable">

                                                                    <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                                   imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"/>

                                                                    <grd:gridsorter sortColumn="<%=strSortCol1%>" sortAscending="<%=blnSortAsc1%>" 
                                                                                    imageAscending="../includes/images/DBGrid/ImgAsc.gif" 
                                                                                    imageDescending="../includes/images/DBGrid/ImgDesc.gif"/>  




                                                                    <grd:rownumcolumn headerText="SNo" width="2" HAlign="center"/> 
                                                                    <grd:textcolumn dataField="ActivityBy" headerText="ActivityBy"   width="10"/>
                                                                    <grd:textcolumn dataField="Status" headerText="Status"   width="10"/>

                                                                    <grd:textcolumn dataField="ActivityDate" headerText="ActivityDate" dataFormat="MM-dd-yyyy" width="10"/>

                                                                    <grd:anchorcolumn dataField="Description" linkUrl="javascript:populateDescription('{Description}')" headerText="Description"
                                                                                      linkText="Click To View"  width="10" />
                                                                    <%-- <grd:textcolumn dataField="ModifiedDate" headerText="ModifyDate" dataFormat="MM-dd-yyyy" width="10"/>
                                                                       "getNotes.action?notesId={Id}&taskId={Task_Id}" 
                                                                    <grd:textcolumn dataField="ModifiedBy" headerText="ModifyBy"   width="10"/> --%>

                                                                    <%--<grd:imagecolumn headerText="Download" width="4" HAlign="center" 
                                                                                     imageSrc="../../includes/images/download_11x10.jpg"javascript:getIssueDescription('{Id}')"
                                                                                     linkUrl="taskAttachmentDownload?id={Id}&fileName={AttachmentFileName}" imageBorder="0"
                                                                                     imageWidth="11" imageHeight="10" alterText="Download"></grd:imagecolumn> --%>

                                                                </grd:dbgrid>


                                                                <input TYPE="hidden" NAME="txtCurr1" VALUE="<%=intCurr1%>">
                                                                <input TYPE="hidden" NAME="txtSortCol1" VALUE="<%=strSortCol1%>">
                                                                <input TYPE="hidden" NAME="txtSortAsc1" VALUE="<%=strSortOrd1%>">

                                                            </div>
                                                        </td>
                                                    </tr>
                                                </table>                                

                                            </s:form>
                                            <%
                                                    connection.close();
                                                    connection = null;
                                                } catch (Exception ex) {
                                                    out.println(ex.toString());
                                                } finally {
                                                    if (connection != null) {
                                                        connection.close();
                                                        connection = null;
                                                    }
                                                }
                                            %>

                                        </div> 

                                        <!-- Task History End -->


                                        <script type="text/javascript">
                                            var countries=new ddtabcontent("taskAttachments")
                                            countries.setpersist(true)
                                            countries.setselectedClassTarget("link") //"link" or "linkparent"
                                            countries.init()

                                        </script>
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


<s:if test="%{currentTask.type == 0}">
		<script type="text/javascript">
		$(window).load(function(){
		setDiv('0');
		init();

		});
		</script>
   
    </s:if>
    <s:if test="%{currentTask.type == 1}">
	<script type="text/javascript">
		$(window).load(function(){
		setDiv('1');
		init();

		});
		</script>
    
    </s:if>
    <s:if test="%{currentTask.type == 2}">
	<script type="text/javascript">
		$(window).load(function(){
		setDiv(2);
		init();

		});
		</script>
   
    </s:if>
    <s:if test="%{currentTask.type == 3}">
	<script type="text/javascript">
		$(window).load(function(){
		setDiv(3);
		init();

		});
		</script>
    
    </s:if>
    <s:if test="%{currentTask.type == 4}">
	<script type="text/javascript">
		$(window).load(function(){
		setDiv(4);
		init();

		});
		</script>
    
    </s:if>
    <s:if test="%{currentTask.type == 5}">
	<script type="text/javascript">
		$(window).load(function(){
		setDiv(5);
		init();

		});
		</script>
   
    </s:if>
	


                                    </body>
                                    </html>