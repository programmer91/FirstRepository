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
        <title>Hubble Organization Portal :: PMO Activity</title>
        <sx:head cache="true"/>
<script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
<script type="text/JavaScript" src="<s:url value="/includes/javascripts/clientValidations/EmpSearchClientValidation.js"/>"></script>

<link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css?version=2.0"/>">
<link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
<link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tooltip.css"/>">
<link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
<link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">

<script type="text/JavaScript" src="<s:url value="/includes/javascripts/tooltip.js"/>"></script> 
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
<script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmployeeAjax.js"/>"></script>
<%-- <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AjaxRequirement.js"/>"></script> --%>
   <link rel="stylesheet" type="text/css" href="<s:url value="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css"/>">
        <%-- <script type="text/JavaScript" src="<s:url value="/includes/javascripts/payroll/payrollajaxscript.js"/>"></script>
         <script type="text/JavaScript" src="<s:url value="/includes/javascripts/payroll/payrollclientvalidations.js"/>"></script> --%>
       
        
         <style> 
            #fontId {
                position: relative;
                -webkit-animation: mymove 5s infinite; /* Chrome, Safari, Opera */
                animation: mymove 1s infinite;
                font-size:49px;
            }

            /* Chrome, Safari, Opera */
            @-webkit-keyframes mymove {
                from {opacity:0.2;}
            to {opacity:1;}
            }

            @keyframes mymove {
                from {opacity:0.2;}
            to {opacity:1;}
            }
        </style>
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
   <script type="text/javascript">
         function checkName()
{
   var empId = document.getElementById("preAssignEmpId").value=""; 
   if(empId=="" || empId==null){
     //  alert("Select from suggestion list.");
       document.getElementById("assignedToUID").value = "";
   }
}

function clearResourceName()
{
    document.getElementById("assignedToUID").value = "";
    document.getElementById("preAssignEmpId").value="";
}
   </script>
        
        <s:include value="/includes/template/headerScript.html"/>
      
       
       
        
       
    </head>
    <body class="bodyGeneral"   oncontextmenu="return false;" >
       

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
                            <td width="850px" class="cellBorder" valign="top" style="padding:5px 5px;">
                                <!--//START TABBED PANNEL : -->
                                <%
                                    //out.print("naga::"+ request.getAttribute("subnavigateTo").toString());
                                 /// session.setAttribute(ApplicationConstants.ISSUE_TO_TASK,request.getAttribute("subnavigateTo").toString());
                                %>
                                
                                <ul id="accountTabs" class="shadetabs" >
                                 
                                <%--    <% if(request.getParameter("issueList")==null)
                                       {%> --%>
                                       
                                        <li ><a href="#" class="selected" rel="issuesTab"  > Projects </a></li>
                                       <%-- <li ><a href="#"  rel="IssuesSearchTab"  > Project Search</a></li> --%>
                                       
                                         
                                 <%--   <%}else{%>
                                    <li ><a href="#" class="selected" rel="IssuesSearchTab">Tasks Search</a></li>
                                     <% }%> --%>
                                    
                                </ul>
                                
                                <%-- <sx:tabbedpanel id="IssuesPanel" cssStyle="width: 845px; height: 500px;padding:5px 5px;" doLayout="true"> --%>
                                <div  style="border:1px solid gray; width:850px;height: 675px; overflow:auto; margin-bottom: 1em;">
                                    <!--//START TAB : -->
                                   
                                   <%--  <% if(request.getParameter("issueList")==null)
                                            {
                                                System.out.println("list");
                                            %> --%>
                                    <div id="issuesTab" class="tabcontent"  >
                                         <div id="overlayResourceType"></div>              <!-- Start Overlay -->

                            <div id="specialBoxResourceType">

                                <div id="resourceTypeDetails"  >
                                    <table align="center" border="0" cellspacing="0" style="width:100%;">
                                        <tr>                               
                                        <td colspan="2" style="background-color: #288AD1" >
                                            <h3 style="color:darkblue;" align="left">
                                                <span id="headerLabel1"></span>


                                            </h3></td>
                                        <td colspan="2" style="background-color: #288AD1" align="right">

                                            <a href="#" onmousedown="toggleCloseUploadOverlay1()" >
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
                                            <table id="tblResourceTypeDetails"  class="gridTable" width="400" cellspacing="1" cellpadding="7" border="0" align="center" style="margin-left:37px;">
                                                <%--   <script type="text/JavaScript" src="<s:url value="/includes/javascripts/wz_tooltip.js"/>"></script> --%>
                                                <COLGROUP ALIGN="left" >
                                                    <COL width="15%">
                                                        <COL width="15%">


                                                            </table> 
                                                            </td>
                                                           
                                                            </tr>

                                                            </table>    

                                                            </div>
                                                            </div>
									
                                       
                                        
                <s:form action="" theme="simple" name="frmSearch">
                
                    
                    <div style="width:840px;" > 
                                               
                <table cellpadding="0" cellspacing="0" align="left" width="100%">
                    
                    <tr>
                        <td>
                            <table border="0" cellpadding="0" cellspacing="2" align="left" width="100%">
                                <tr>
                                    <td class="headerText" colspan="11">
                                         <s:property value="#request.resultMessage || #session.resultMessage"/>
                                        <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                    </td>
                                </tr>
                                <tr>
                                   <td class="fieldLabel">Customer Name :</td>
                                    <td>
                                       
                                        <s:select headerKey="" headerValue="All" list="clientMap" name="customerName" id="customerName" cssClass="inputSelect" theme="simple" onchange="getProjectsByAccountId();"/>
                                    </td>
                                   <td class="fieldLabel">ProjectName :</td>
                                    <td>
                                        
                                         <s:select headerKey="" headerValue="All"  list="{}" name="projectName" id="projectName" cssClass="inputSelect" theme="simple"/>
                                    </td>
                                </tr>
                               
                                <tr>
                                    <td  class="fieldLabel">Status:</td>
                                                                <td><s:select list="{'Active','Completed','Terminated','Initiated'}"
                                                                                  name="status"
                                                                                  headerKey="" 
                                                                                  headerValue="--select--"
                                                                                  value="%{status}"
                                                                              cssClass="inputSelect" id="status"/></td>
                                                            
                                   <td class="fieldLabel">ProjectStartDate :</td>
                                    <td><s:textfield name="projectStartDate" id="projectStartDate" cssClass="inputTextBlue" theme="simple" /><a href="javascript:cal1.popup();">
                                        
                                                     <a href="javascript:cal4.popup();">
                                                            <img style="margin-bottom:-6px;margin-left:-4px" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                 width="20" height="20" border="0" ></a>
                                    </td>
                                </tr>
                               <tr>
                                   
                                   
                                   
                                      <td class="fieldLabel" >Practice :</td>
                                    <td >
                                        <s:select list="practiceList" name="practiceId" id="practiceId" value="%{practiceId}" cssClass="inputSelect"  headerKey="" headerValue="--Please Select--"/>
                                    </td>
                                    
                                      
                                    
                                    
                                      <td class="fieldLabel">Resource&nbsp;Name&nbsp;:</td>
                                     <td ><s:textfield name="assignedToUID" id="assignedToUID" value="%{assignedToUID}"   onchange="checkName();"  onkeyup="EmployeeForProject();"  cssClass="inputTextBlue"  theme="simple" readonly="false"/>
                                     <div id="authorEmpValidationMessage" style="position: absolute; overflow:hidden;"></div>  
                                     <s:hidden name="preAssignEmpId" value="%{preAssignEmpId}" id="preAssignEmpId"/>  
                                </td> 
                                   
                                </tr>
                                <tr>
                                    <s:if test="#session.isUserManager == 1 || #session.isUserTeamLead==1 || #session.empPmoActivityAccess==1 || #session.isAdminAccess==1">
                                         <td  class="fieldLabel">PMO&nbsp;:</td>
                                                                <td><s:select list="myTeamMembers"
                                                                                  name="pmoLoginId"
                                                                                  headerKey="" 
                                                                                  headerValue="--Please Select--"
                                                                                  value="%{pmoLoginId}"
                                                                              cssClass="inputSelect" id="pmoLoginId"/></td>
                                                                </s:if><s:else>
                                                                <td colspan="2">
                                                                    <s:hidden name="pmoLoginId" id="pmoLoginId" value=""/>
                                                                </td>
                                                                </s:else>
                                    <td></td>
                                    <td>
                                        <input type="button" value="Search" class="buttonBg" onclick="getpmoActivityList();" style="margin-left: 79px;"/>
                                    </td> 
                                </tr>
                            </table>  
                        </td>
                    </tr>
                    
                    <tr>
                        <td>
                        <table width="100%" cellpadding="1" cellspacing="1" border="0">
                        <tr>
                            <td>
                                <div id="loadingMessage" style="color:red; display: none;" align="center"><b><font size="4px" >Loading...Please Wait...</font></b></div>
                               
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <%-- <DIV id="loadingMessage"> </DIV> --%>
                                 
                                    <TABLE id="tblUpdate" align="left"  
                                       cellpadding='1' cellspacing='1' border='0' class="gridTable" width='100%'>
                                     
                                     </TABLE>
                                    
                            </td>
                        </tr>
                        </table>
                        </td>
                    </tr>
                
                                              <%--     <tr>
                                                        <td>
                                                          
                                                                <grd:dbgrid id="tblCrmAttachments" name="tblCrmAttachments" width="100" pageSize="18" 
                                                                            currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                            dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                    <grd:gridpager imgFirst="../includes/images/DBGrid/First.gif" imgPrevious="../includes/images/DBGrid/Previous.gif" 
                                                                                   imgNext="../includes/images/DBGrid/Next.gif" imgLast="../includes/images/DBGrid/Last.gif"
                                                                                   />
                                                                    <grd:rownumcolumn headerText="SNo" width="2" HAlign="center"/>                 
                                                                   
                                                                    
                                                                   <%--  <grd:anchorcolumn dataField="ProjectName" 
                                                                                      headerText="ProjectName" 
                                                                                      linkUrl="getTask.action?taskId={Id}&resM=" linkText="{Title}" width="20"/> --%>
                                                                 <%--   <grd:textcolumn dataField="Name"  headerText="CustomerName" width="15" sortable="true"/>
                                                                    <grd:textcolumn dataField="ProjectName"  headerText="ProjectName" width="10" sortable="true"/> 
                                                                    <grd:datecolumn dataField="ProjectStartDate"  headerText="StartDate"  dataFormat="MM-dd-yyyy" width="8" sortable="true"/>
                                                                    <grd:textcolumn dataField="Status"  headerText="Status"   width="7" sortable="true"/> 
                                                                    <grd:numbercolumn dataField="TotalResources"  headerText="TotalResources" dataFormat=""  width="3" sortable="true"/> 
                                                                   --%>
                                                                   <%--        
                                                                    <grd:textcolumn dataField="NAME"  headerText="NAME" width="15" sortable="true"/>
                                                                    <grd:textcolumn dataField="ProjectName"  headerText="ProjectName" width="10" sortable="true"/> 
                                                                    <grd:datecolumn dataField="ProjectStartDate"  headerText="ProjectStartDate"  dataFormat="MM-dd-yyyy" width="8" sortable="true"/>
                                                                    
                                                                     <grd:numbercolumn dataField="Resources"  headerText="Resources" dataFormat=""  width="3" sortable="true"/>
                                                                    <grd:textcolumn dataField="STATUS"  headerText="STATUS"   width="7" sortable="true"/> 
                                                                     
                                                                    
                                                                    
                                                                    <grd:imagecolumn  headerText="Activity" width="4"  HAlign="center"  
                                                                          imageSrc="../includes/images/go_21x21.gif" linkUrl="viewProjectTeam.action?projectId={ProjectId}&accountId={AccountId}"
                                                                          imageBorder="0" imageWidth="18" imageHeight="15" alterText="Click to Add" />
                                                                    <%-- <grd:textcolumn dataField="Comments"  headerText="Comments" width="10" sortable="true"/>   --%>
                                                                   <%-- <grd:anchorcolumn dataField="Description" linkUrl="javascript:getIssueDescription('{Id}')" headerText="Description"
                                                                                      linkText="Click To View"  width="10" />
                                                                    
                                                                    <grd:textcolumn dataField="PriAssignTO"  headerText="PriAssignTO" width="12" sortable="true"/> 
                                                                    <grd:textcolumn dataField="SecAssignTO"  headerText="Sec-AssignedTo" width="12" sortable="true"/>
                                                                    <%--  <grd:imagecolumn headerText="Alert" width="4" HAlign="center" imageSrc="../../includes/images/DBGrid/Edit.gif"
                                                                                     linkUrl="getIssue.action?issueId={Id}&accessType=Issue" imageBorder="0"
                                                                                     imageWidth="16" imageHeight="16" alterText="Click to edit"></grd:imagecolumn>  --%>
                                                                    <%--<grd:imagecolumn  headerText="reminder" width="4" HAlign="center" imageSrc="../../includes/images/DBGrid/reminder.jpg"
                                                                                      linkUrl="javascript:win_open2('/Hubble/employee/tasks/TaskReminderWindow.jsp','{Id}','{PriAssignTO}')"
                                                                                      imageBorder="0" 
                                                                                      imageWidth="16" imageHeight="16" alterText="Click to edit"></grd:imagecolumn> 
                                                                    --%>
                                                                    <%--     </grd:dbgrid>
                                                         
                                                            
                                                        
                                                            <!-- these components are DBGrid Specific  -->
                                                            <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                            <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                            <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                                                            
                                                        </td>
                                                    </tr> --%>
                                                </table>   
                                                <script type="text/JavaScript">
                                          
                                              var cal4 = new CalendarTime(document.forms['frmSearch'].elements['projectStartDate']);
	                                cal4.year_scroll = true;
	                                cal4.time_comp = false;
                                            
                                        </script>
                                            </div>
                                        </s:form>  
                                        
                                         
                                       
                                        <%--  </sx:div > --%>
                                    </div>
                                 <%--   <%}%> --%>
                                    <!--//END TAB : -->
                                  
                                    <%--  <sx:div id="IssuesSearchTab" label="Issues Search" > --%>
                                <%--   <div id="IssuesSearchTab" class="tabcontent"  >   
                                        <s:form name="frmSearch" action="pmoActivitySearch" theme="simple" method="post" onsubmit="return (validateDates() && compareDates(document.getElementById('startDate').value,document.getElementById('endDate').value));">
                                        
                                            <!--The components of searching issues -->
                                            <table border="0" cellpadding="1" cellspacing="1" width="100%" align="center">
                                                <tr>
                                                    <td class="headerText" align="right">
                                                     
                                                         &nbsp;
                                                    </td>
                                                </tr>
                                             
                                                <tr><td align="center"> <table>
                                                   
                                                            <tr>
                                                            <td class="fieldLabel">Project&nbsp;Name&nbsp;:</td>
                                                            <td width="20%"><s:textfield name="projectName" id="projectName"  cssClass="inputTextBlue"  value="%{projectName}"  theme="simple"/></td>
                                                            <td  class="fieldLabel">Status:</td>
                                                                <td><s:select list="{'Active','Completed'}"
                                                                                  name="status"
                                                                                  headerKey="" 
                                                                                  headerValue="--select--"
                                                                                  value="%{status}"
                                                                              cssClass="inputSelect"  /></td>
                                                            
                                                             <td  class="fieldLabel">Start&nbsp;Date:</td>
                                                                   
                                                                <td width="40%"><s:textfield name="startDate" value="%{startDate}" id="startDate" cssClass="inputTextBlue"/><a href="javascript:cal1.popup();">
                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                     width="20" height="20" border="0"></a></td>
                                                             <td  align="center">
                                                     
                                                         <s:submit cssClass="buttonBg" value="Search"/>
                                                    </td>    
                                                    <td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
                                                    <td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
                                                   
                                                   
                                                            </tr> 
                                                           
                                                        
                                                            

                                                           
                    
               
        </tr>
        
                                                        </table>  
                                                    </td>
                                                </tr> 
                                                
                                            </table>
                                            
                                        </s:form>
                                            
                                        <!-- this script for After loading Form it will instantiate Calender Objects as you require -->
                                        <script type="text/JavaScript">
                                                           var cal1 = new CalendarTime(document.forms['frmSearch'].elements['startDate']);
                                                           cal1.year_scroll = true;
                                                           cal1.time_comp = false;
                                            
                                                          
                                        </script>
                                        
                                       
                                    </div> --%>
                                  
                                <!--//END TABBED PANNEL : -->
                                <script type="text/javascript">

var countries=new ddtabcontent("accountTabs")
countries.setpersist(false)
countries.setselectedClassTarget("link") //"link" or "linkparent"
countries.init()

                                </script>
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

            <!--//END FOOTER : Record for Footer Background and Content-->
            
        </table>
        <!--//END MAIN TABLE : Table for template Structure-->
        
        
        <script type="text/javascript">
        function pagerOption(){

            // var paginationSize = document.getElementById("paginationOption").value;
                                                                                                                                                                                                      
            var  recordPage=15;
                                                                                                                                                                                                  
            $('#tblUpdate').tablePaginate({navigateType:'navigator'},recordPage);

        }
                                                                                                                                                                                                        
                                                                                                                                                                                                           
    </script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/paginationAll.js"/>"></script>


        
        
    </body>
</html>
