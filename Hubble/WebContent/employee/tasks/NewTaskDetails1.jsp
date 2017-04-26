  <%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
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
<%-- <%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
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
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/helpText.css"/>"> 
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css?ver=1.0"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tooltip.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/CreateTask_new.css"/>">
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tooltip.js"/>"></script>   
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>   
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>   
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/employee/DateValidator.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ajax/AjaxPopup.js"/>"></script> 
    <script type="text/javascript" src="<s:url value="/includes/javascripts/searchIssue.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmpStandardClientValidations.js?ver=1.0"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
    <script type="text/javascript" src="<s:url value="/includes/javascripts/IssueFill.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmployeeAjax.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/TaskManagementValidations.js?ver=1.0"/>"></script>
    <script src="http://code.jquery.com/jquery-1.10.2.js"></script>
    <script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>


    <s:include value="/includes/template/headerScript.html"/>
    <script>
        $(function() {
            $( document ).tooltip();
        });
    </script>
    <s:include value="/includes/template/headerScript.html"/>
    <%-- for issue reminder popup --%>
    <script type="text/javascript">
        function win_open2(url,id,priemail){
            // alert(priemail.length);
            if(priemail.length <=1)
            {
                alert("This task is not assigned to any person.Please assign the issue before sending reminder");
            }else{
                //alert("id-->"+id);
                //alert("url---->"+url);
                //var values=document.getElementById('mailid').innerHTML;
                url = url+"?issueid="+id;
                newWindow=window.open(url,'issueid','height=230,width=540');
            }
        }               
            

        
    </script>
    <script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            //alert($('#empType').val());
             
            if($('#empType').val() == "e")
            {
              
                var chkBoxList = document.getElementsByName("issuerelatedId");
                document.getElementById("issueType").value="1";
                 document.getElementById("issueRelType").value="0";
                
                //alert("in e"+ chkBoxList);
                var chkBoxCount= chkBoxList.length;
                //alert("in e"+ chkBoxCount);
                chkBoxList[0].checked=true;
                chkBoxList[1].checked=false;
                chkBoxList[2].checked=false;
                chkBoxList[3].checked=false;
                chkBoxList[4].checked=false;
                chkBoxList[5].checked=false;
                $("#projectsSelectBox").hide();
                $("#projectsAttachBox").hide();  
                $("#projectsAttachBoxId").hide();  
                getIssueTypeBasedOnIssueRel("0");
              getTaskEmpDetailsBasedOnIssueRel1("0");
              //document.getElementById("issueType").value="1";
            
            }
            if($('#empType').val() == "c"){
                // alert("");
                var chkBoxList = document.getElementsByName("issuerelatedId");
                  document.getElementById("issueType").value="2";
                 document.getElementById("issueRelType").value="1";
                //alert("in e"+ chkBoxList);
                var chkBoxCount= chkBoxList.length;
                //alert("in e"+ chkBoxCount);
                 //chkBoxList[0].readOnly=true;
                chkBoxList[0].checked=true;
                chkBoxList[1].checked=false;
               $("#projectsSelectBox").hide();
                $("#projectsAttachBox").hide();
                $("#projectsAttachBoxId").hide();
                  getIssueTypeBasedOnIssueRel("1");
              getTaskEmpDetailsBasedOnIssueRel1("1");
            }
        });
      
        function goToHRSubmit(){
            var type=document.getElementById("issueRelType").value;
           // alert(type);
            var projectId="0"
     if(type=="3"){
         
       projectId  =document.getElementById("projectId").value;
       
     }
         //alert(projectId);
            var priority=document.getElementById("priority").value;
            var issueType=document.getElementById("issueType").value;
            var title=document.getElementById("title").value;
            var comments=document.getElementById("comments").value;
            var primaryAssignTo=document.getElementById("primaryAssignTo").value;
            var secondaryAssignTo=document.getElementById("secondaryAssignTo").value;
            var secondaryAssignToLoginId = document.getElementById("secondaryAssignToLoginId").value;
            if(priority==""||priority==null||issueType==""||issueType==null)
            {
                alert("Please provide the mandatory fields");
                return false;
            }
            //alert(primaryAssignTo)
            else if(primaryAssignTo==""||primaryAssignTo==null)
            {
                alert("Please provide the primaryAssignTo person");
                return false;
            }
            //alert();
            else if(primaryAssignTo==secondaryAssignToLoginId)
            {
                alert("Please select different persons for primary and secondary assign to!");
                return false;
            }
            window.location="newAddTask.action?type="+type+"&priority="+priority+"&issueType="+issueType+"&title="+title+"&comments="+comments+"&primaryAssignTo="+primaryAssignTo+"&secondaryAssignTo="+secondaryAssignTo+"&secondaryAssignToLoginId="+secondaryAssignToLoginId+"&projectId="+projectId;
        
        }
     
    function getChangeIssuereletedTo(element)
    {  
        //alert(element.value);
        var chkBoxList = document.getElementsByName("issuerelatedId");
        //alert("in e"+ chkBoxList);
        var chkBoxCount= chkBoxList.length;
        //alert("in e"+ chkBoxCount);
         if($('#empType').val() == "e")
            {
       if(element.value=="0")
           {
               //issueTitle,SecondaryAssignTo,Comments,secondaryAssignTo
            chkBoxList[1].checked=false;
            chkBoxList[2].checked=false;
            chkBoxList[3].checked=false;
            chkBoxList[4].checked=false;
            chkBoxList[5].checked=false;
           
            $("#projectsSelectBox").hide(); 
            $("#projectsAttachBox").hide(); 
            $("#projectsAttachBoxId").hide(); 
          //  getTaskEmpDetailsBasedOnIssueRel();
            getIssueTypeBasedOnIssueRel("0");
            getTaskEmpDetailsBasedOnIssueRel1(element.value);
              document.getElementById("title").value="";
               document.getElementById("secondaryAssignTo").value="";
               document.getElementById("comments").value="";
                document.getElementById("issueRelType").value="0";
               getTitleBasedOnIssueType();
        }
        else if(element.value=="1") 
        {
            chkBoxList[0].checked=false;
            chkBoxList[2].checked=false;
            chkBoxList[3].checked=false;
            chkBoxList[4].checked=false;
            chkBoxList[5].checked=false;
            $("#projectsSelectBox").hide();
            $("#projectsAttachBox").hide(); 
            $("#projectsAttachBoxId").hide(); 
            getIssueTypeBasedOnIssueRel("1");
           getTaskEmpDetailsBasedOnIssueRel1(element.value);
              document.getElementById("title").value="";
             document.getElementById("secondaryAssignTo").value="";
             document.getElementById("comments").value="";
              document.getElementById("issueRelType").value="1";
             getTitleBasedOnIssueType();
        }
        else if(element.value=="2") 
        {
            chkBoxList[0].checked=false;
            chkBoxList[1].checked=false;
            chkBoxList[3].checked=false;
            chkBoxList[4].checked=false;
            chkBoxList[5].checked=false;
         
            /*$(".hubble").hide(); 
                $(".project").show();
                $(".network").hide();
                $(".infra").hide();
                $(".hr").hide();*/
            $("#projectsSelectBox").show();
            $("#projectsAttachBox").show(); 
            $("#projectsAttachBoxId").show(); 
            getIssueTypeBasedOnIssueRel("2");
            getTaskEmpDetailsBasedOnProjIssue(element.value);
             document.getElementById("title").value="";
             document.getElementById("secondaryAssignTo").value="";
             document.getElementById("comments").value="";
              document.getElementById("issueRelType").value="2";
              document.getElementById("issueType").value="4";
             getTitleBasedOnIssueType();
            //             $(".hr").show();
        }
        else if(element.value=="3") 
        {
            chkBoxList[0].checked=false;
            chkBoxList[1].checked=false;
            chkBoxList[2].checked=false;
            chkBoxList[4].checked=false;
            chkBoxList[5].checked=false;
         
         
            $("#projectsSelectBox").hide(); 
             $("#projectsAttachBox").hide(); 
             $("#projectsAttachBoxId").hide(); 
             getIssueTypeBasedOnIssueRel("3");
             getTaskEmpDetailsBasedOnIssueRel1(element.value);
                  document.getElementById("title").value="";
             document.getElementById("secondaryAssignTo").value="";
             document.getElementById("comments").value="";
              document.getElementById("issueRelType").value="3";
             getTitleBasedOnIssueType();
         
        }
        else if(element.value=="4") 
        {
            //alert("in 5")
            chkBoxList[0].checked=false;
            chkBoxList[1].checked=false;
            chkBoxList[2].checked=false;
            chkBoxList[3].checked=false;
            chkBoxList[5].checked=false;
             
      
            $("#projectsSelectBox").hide(); 
           $("#projectsAttachBox").hide(); 
           $("#projectsAttachBoxId").hide(); 
           getIssueTypeBasedOnIssueRel("4");
         getTaskEmpDetailsBasedOnIssueRel1(element.value);
          document.getElementById("title").value="";
             document.getElementById("secondaryAssignTo").value="";
             document.getElementById("comments").value="";
              document.getElementById("issueRelType").value="4";
             getTitleBasedOnIssueType();
        }
        
        else if(element.value=="5") 
        {
            //alert("in 5")
            chkBoxList[0].checked=false;
            chkBoxList[1].checked=false;
            chkBoxList[2].checked=false;
            chkBoxList[3].checked=false;
            chkBoxList[4].checked=false;
             
      
            $("#projectsSelectBox").hide(); 
           $("#projectsAttachBox").hide(); 
           $("#projectsAttachBoxId").hide(); 
           getIssueTypeBasedOnIssueRel("5");
         getTaskEmpDetailsBasedOnIssueRel1(element.value);
          document.getElementById("title").value="";
             document.getElementById("secondaryAssignTo").value="";
              document.getElementById("primaryAssignToforOthers").value="";
             document.getElementById("comments").value="";
              document.getElementById("issueRelType").value="5";
             getTitleBasedOnIssueType();
        }
        
            }
            else{
               // alert("in c");
                if(element.value=="1") 
        {
            chkBoxList[1].checked=false;
            $("#projectsSelectBox").hide();
            $("#projectsAttachBox").hide(); 
            $("#projectsAttachBoxId").hide(); 
            getIssueTypeBasedOnIssueRel("1");
          
           getTaskEmpDetailsBasedOnIssueRel1(element.value);
              document.getElementById("title").value="";
             document.getElementById("secondaryAssignTo").value="";
             document.getElementById("comments").value="";
              document.getElementById("issueRelType").value="1";
                getTitleBasedOnCusIssueType();
            
        }
        else if(element.value=="2") 
        {
            chkBoxList[0].checked=false;
        
            $("#projectsSelectBox").show();
            $("#projectsAttachBox").show(); 
             $("#projectsAttachBoxId").show(); 
            getIssueTypeBasedOnIssueRel("2");
          
            getTaskEmpDetailsBasedOnProjIssue(element.value);
             document.getElementById("title").value="";
             document.getElementById("secondaryAssignTo").value="";
             document.getElementById("comments").value="";
              document.getElementById("issueRelType").value="2";
                 getTitleBasedOnCusIssueType();
            
                 } 
            }
    }
           
     
        
        
    </script>
    <style type="text/css">
        .box{
            padding: 20px;
            display: block;
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


<%-- <body class="bodyGeneral" oncontextmenu="return false;" onload="init();"> --%>
<body class="bodyGeneral" oncontextmenu="return false;">
    <%!
    //class="bodyGeneral"  oncontextmenu="return false;" onload="init();"
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
                                <s:if test="%{currentAction=='addTask'}">
                                    <li ><a href="#" class="selected" rel="createTask"  >Create Task </a></li>
                                </s:if>
                                <s:else>
                                    <li ><a href="#" class="selected" rel="issueTab"  >Edit Task </a></li>
                                </s:else>

                            </ul>
                            <div  style="border:1px solid gray; width:845px;height: 500px; overflow:auto; margin-bottom: 1em;">   
                                <%-- <sx:div id="empProfileTab" label="Profile" > --%>

                                <div id="createTask" style="width:840px;"> 

                                    <table cellpadding="0" border="0" cellspacing="0" width="100%">
                                        <tr align="right">
                                            <td class="headerText12" colspan="5" >
                                                <s:property value="#request.resultMessage"/>
                                                <s:hidden name="empType" id="empType" value="%{#session.empType}"/>
                                              <s:hidden name="othersFlag" id="othersFlag" value="0"/>
                                                <s:property value="%{resM}"/>
                                                <s:property value="#session.resultMsg"/>
                                                <br/>
                                            </td>
                                        </tr>
                                        
                                                <tr>
                                                <td>
                                                     <table cellpadding='1' cellspacing='1' border='0' width='75%'><tr>
                                             <td class="fieldLabel">Task&nbsp;Relate&nbsp;to:</td> 
                                      <td class="fieldLabelLeft" colspan="4" width="60%">
                                      <%
                                       Map isuueMap = null;
                                       if(session.getAttribute("empType").toString().equalsIgnoreCase("e"))
                                      {
                                       isuueMap = (Map)request.getAttribute("issuerelatedtoList");
                                      }
                                     else{
                                         isuueMap = (Map)request.getAttribute("issuecusrelatedtoList"); 
                                         }
                                      Iterator entries = isuueMap.entrySet().iterator();
                                      int i=0;
                                     while (entries.hasNext()) {
                                          Entry thisEntry = (Entry) entries.next();
                                          Object key = thisEntry.getKey();
                                          Object value = thisEntry.getValue();
                                          String mapId = (String)key;
                                          String mapValue = (String)value;
  
                                out.println("<input type=\"checkbox\" name=\"issuerelatedId\" value="+mapId+" onclick=\"getChangeIssuereletedTo(this);\">"+mapValue);
  
  
                                    }                                         
                                              
                               %>
                                      </td>       </tr> </table> </td></tr>


                                        <tr><td align="center" colspan="5"><div class="hr box" >   
                                                    <s:form action="../tasks/newAddTask.action" method="post" theme="simple" enctype="multipart/form-data" onsubmit="return taskValidation();">

                                                        <table cellpadding="1" cellspacing="1" border="0" width="70%">
                                                             <%-- <tr>
                                                                <td colspan="5">
                                                                    <div id="projectsSelectBox" style="margin-left:9.3%;">
                                                                           <s:hidden name="type" id="issueRelType" value=""/>
                                                                        <table >

                                                                            <tr>
                                                                                <td class="fieldLabel">Projects:<FONT color="red"  ><em>*</em></FONT></td>

                                                                                <td colspan="3" ><s:select id="projectId" name="projectId" list="projectsList" value="%{currentTask.projectId}" cssClass="inputSelect" onchange="getTaskEmpDetailsBasedOnProjIssue(this);"/></td>
                                                                            </tr>
                                                                        </table>
                                                                    </div>
                                                                </td>
                                                            </tr> 
                                                            <tr>
                                                                <td colspan="5">
                                                                    <div id="projectsAttachBox" style="margin-left:6.3%;">
                                                                        <table >

                                                                            <tr>                   
                                                                                <td class="fieldLabel">Attachment :</td>
                                                                                <td colspan="3" ><s:file name="upload" theme="simple" size="60" cssClass="inputTextareaForAttachmentTitle" id="uploadFileName" onchange="attachmentFileNameValidate();" /></td></tr>  
                                                                        </table>
                                                                    </div>
                                                                </td>
                                                            </tr> --%>
                                                             <tr>
                                                                <td colspan="4">
                                                                    <div id="projectsSelectBox" align="left" style="margin-left: 9%;" >
                                                                           <s:hidden name="type" id="issueRelType" value=""/>
                                                                        <table >

                                                                            <tr>
                                                                                <td class="fieldLabel">Projects:<FONT color="red"  ><em>*</em></FONT></td>

                                                                                <td colspan="" ><s:select id="projectId" name="projectId" list="projectsList" value="%{currentTask.projectId}" cssClass="inputSelect" onchange="getTaskEmpDetailsBasedOnProjIssue(this);" headerKey="-1" headerValue="--please select--"/></td></tr>
                                                                           
                                                                        </table>
                                                                    </div>
                                                                </td>
                                                            </tr>

                                                          
                                                            <tr> <td class="fieldLabel">Type&nbsp;:<FONT color="red"><em>*</em></FONT></td> 
                                                                <td >
                                                                    <%if(session.getAttribute("empType").toString().equalsIgnoreCase("e")){%>
                                                                    <s:select id="issueType" name="issueType"  list="issueTypeMap" value="%{currentTask.issueType}" cssClass="inputSelect" onchange="getTitleBasedOnIssueType();"/>
                                                                   <%}else{%>
                                                                    <s:select id="issueType" name="issueType"  list="issueTypeMap" value="%{currentTask.issueType}" cssClass="inputSelect" onchange="getTitleBasedOnCusIssueType();"/>
                                                                     <%}%>
                                                                </td>
                                                                <td class="fieldLabel" >Severity&nbsp;:<FONT color="red"><em>*</em></FONT></td> 
                                                                <td><s:select id="priority" name="priority"  list="{'High','Medium','Low'}" value="%{currentTask.priority}" cssClass="inputSelect"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel">Title&nbsp;:</td>
                                                                <td colspan="3"><s:textfield cssClass="inputTextBlueForIssueTitle" name="title" style="width:413px;" id="title" value="%{currentTask.title}" readonly="false" onchange="fieldLengthValidator(this);"  /></td>
                                                            </tr>  
                                                            <tr>
                                                                <%--<td class="fieldLabel" >Primary AssignTo:</td>
                                                                <td><s:textfield cssClass="inputTextBlue" name="primaryAssignTo" id="primaryAssignTo" value="%{currentTask.primaryAssignTo}"/><font size="1.5" color="green">(Email)</font></td> --%>
                                                                <td class="fieldLabel">Primary&nbsp;AssignTo&nbsp;:<FONT color="red"  ><em>*</em></FONT></td>
                                                                <td>
                                                                    <div id="others" style="display:none;">
                                                                        <s:textfield name="primaryAssignToforOthers" placeholder="Primary AssignTo" cssClass="inputTextBlue" id="primaryAssignToforOthers" onkeyup="getAllEmpNamesForPrimaryOthers();" value="%{currentTask.primaryAssignToforOthers}" />
                                                                    <s:hidden name="primaryAssignToLoginIdforOthers" cssClass="inputTextBlue" id="primaryAssignToLoginIdforOthers" value="%{currentTask.primaryAssignToLoginIdforOthers}" />

                                                                    </div>
                                                                    
                                                                    <div id="notOthers">
                                                                    <s:select list="primaryAssignToMap" placeholder="Primary AssignTo" id="primaryAssignTo" name="primaryAssignTo" cssClass="inputSelect" value="%{currentTask.primaryAssignTo}"/>
                                                                    </div>
                                                                </td>
                                                                <td class="fieldLabel">Secondary&nbsp;AssignTo&nbsp;:</td>
                                                                <td class="">
                                                                    <s:textfield name="secondaryAssignTo" cssClass="inputTextBlue" placeholder="Secondary AssignTo" id="secondaryAssignTo" onkeyup="getAllEmpNames();" value="%{currentTask.secondaryAssignTo}" />
                                                                    <s:hidden name="secondaryAssignToLoginId" cssClass="inputTextBlue" id="secondaryAssignToLoginId" value="%{currentTask.secondaryAssignToLoginId}" />

                                                                    <div id="validationMessage"></div>
                                                                </td>     
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel">Estimated&nbsp;Time&nbsp;:</td>
                                                                <td >
                                                                    <s:textfield name="durationTotheTask" placeholder="Ex: 20" cssClass="inputTextBlue2" style="width:48px;" id="durationTotheTask"  value="%{currentTask.durationTotheTask}" onkeypress="return isNumber(event)"/><s:label cssClass="fieldLabel" value="(Hrs)"/>
                                                                </td>
                                                                <td id="bridgeTr" style="display: none;" class="fieldLabel">Bridge&nbsp;Code&nbsp;:<font color="red">*</font></td>  
                                                                <td id="bridgeTextTr" style="display: none;" ><s:textfield id="bridgeCode"  name="bridgeCode"  placeHolder="B****" cssClass="inputTextBlueSmall" onblur="isActiveBridge();"/>&nbsp;<img id="correctImg" style="display: none;" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/ecertification/checkSelect.jpg" 
                                                                                                                                                                                                        width="15" height="15" border="0"><img id="wrongImg" style="display: none;" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/ecertification/wrong.jpg" >
                                                                </td>
                                                            </tr>
                                                            
                                                            
                                                            
                                                              <tr>
                                                                    <td class="fieldLabel" id="projectsAttachBox">Attachment&nbsp;:</td>
                                                                    <td colspan="3" id="projectsAttachBoxId" ><s:file name="upload" theme="simple" size="60" cssClass="inputTextareaForAttachmentTitle" id="uploadFileName" onchange="validateFileSize(this);attachmentFileNameValidate();" /></td>
                                                                </td>
                                                            </tr>
                                                            
                                                        </table><table cellpadding="1" cellspacing="1" border="0" width="70%">
                                                            <tr>
                                                                <td class="fieldLabel" style="width: 102px;">Description&nbsp;:</td>
                                                                
                                                            
                                                                
                                                                <td colspan="3"><s:textarea rows="4" cols="60" placeholder="Enter Description Here..." name="comments" cssClass="inputTextarea2" id="comments" value="%{currentTask.comments}" onchange="fieldLengthValidatorTaskComments(this);" style="width: 413px;"/></td>
                                                           </tr> 
                                                            

                                                        </table>
                                                           <table border="0">
                                                               <tr><td colspan="4" align="right" style="width: 508px;"><br>
                                                                   &nbsp;&nbsp;  &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:submit label="Submit" value="Create Task" cssClass="buttonBg"/>
                                                                </td></tr> 
                                                           </table>



                                                    </s:form>


                                                </div></td></tr>
                                             

                                    </table>
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
    <script type="text/javascript">
		$(window).load(function(){
		
		init();

		});
		</script>
</body>
</html>
