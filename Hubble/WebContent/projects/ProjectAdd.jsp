<%--
/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  January 01, 2008, 12:35 PM
 *
 * Author  : Arjun Sanapathi<asanapathi@miraclesoft.com>
 *           Rajanikanth Teppala<rteppala@miraclesoft.com>
 *
 * File    : ProjectAdd.jsp
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
 --%>

<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
--%>
<%@ page import="com.freeware.gridtag.*" %> 
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd" %>

<html>
    <head>
        
        <title>Hubble Organization Portal :: Project Adding</title>
        <%-- <sx:head cache="false"/> --%>
        
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        
         <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
         <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
         
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/projectsValidations/ProjectClientValidation.js"/>"></script> 
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/StringUtility.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tooltip.css"/>">
         <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tooltip.js"/>"></script>
          <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmpStandardClientValidations.js"/>"></script>
         <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/jquery.min.js"/>"></script>
    </head>
    
    <body class="bodyGeneral">
        
        <%!
        /* Declarations */
        Connection connection;
        String currentAccountId;
        String queryString;
        String strTmp;
        String strSortCol;
        String strSortOrd = "DESC";
        
        
        String accoutId;
        String objectType;
        int objectId = 0;
        int intSortOrd = 0;
        int intCurr;
        boolean blnSortAsc = true;
        String userRoleName;
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
                            <!--//END DATA COLUMN : Coloumn for LeftMenu-->
                            
                            <!--//START DATA COLUMN : Coloumn for Screen Content-->
                            <td width="850px" class="cellBorder" valign="top">
                                <table cellpadding="0" cellspacing="0" width="100%">
                                    <tr>                    
                                        
                                        <%
                                        userRoleName = session.getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
                                        //out.println(userRoleName);
                                        %>                    
                                        
                                        <%
                                        if (("Customer Manager".equalsIgnoreCase(userRoleName)) ){
                                        %>
                                        <td><span class="fieldLabel">Account Name :</span>&nbsp;
                                        <label class="navigationText"><s:property value="#request.accountName"/></label></td>                                            
                                        
                                        <%  }else  {%>
                                        <td><span class="fieldLabel">Account Name :</span>&nbsp;
                                        <a href="<s:url action="../crm/accounts/getAccount"><s:param name="id" value="%{accountId}"/></s:url>" class="navigationText"><s:property value="#request.accountName"/></a></td>                                            
                                        
                                        <%}%>
                                        
                                    </tr>
                                    <tr>                                        
                                            <td valign="top" style="padding-left:10px;padding-top:5px;">
                                            <!--//START TABBED PANNEL : -->
                                            <ul id="accountTabs" class="shadetabs" >
                                                    <li ><a href="#" class="selected" rel="accountTab"  > Add Project </a></li>                                   
                                            </ul>
                                          <%--  
                                          <sx:tabbedpanel id="projectPannel" cssStyle="width: 840px; height: 200px;padding-left:10px;padding-top:5px;" doLayout="true">
                                            --%>    
                                                <!--//START TAB : -->
                                                <div  style="border:1px solid gray; width:898px;height: auto; overflow:auto; margin-bottom: 1em;">  
                                                    <div id="accountTab" class="tabcontent" >
                                                      <%   if(request.getParameter("resultMessage")!=null){
                                                            out.println(request.getParameter("resultMessage"));
                                                                                                                       }
                                                            %>
                                                        <%--  <sx:div id="accountTab" label="Project Add"  > --%>
                                              
                                                        <s:form name="frmProjectAdd" action="addProject" theme="simple" onsubmit="return projectValidation();">
                                                            
                                                            <table cellpadding="1" cellspacing="1" border="0" width="100%">
                                                                     
                                                                <tr style="color:#00008B">
                                                                    <th colspan="4"><center>Project Details</center></th><th></th><th style="text-align: left;">Plan</th><th style="text-align: left;">Actual</th>
                                                                </tr>
                                                                <tr>
                                                                   <td class="fieldLabel" >Project Name:<FONT color="red"  ><em>*</em></FONT></td>                                                    
                                                                      <td style="width: 219px;"><s:textfield name="prjName" cssClass="inputTextBlueLarge" onblur="isExistedProjectName();" onchange="fieldLengthValidatorforProject(this);"  id="projectName" size="25" value="%{currentProject.prjName}" />&nbsp;<img id="correctImg" style="display: none;" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/ecertification/checkSelect.jpg" 
                                                                                                                                                                                                        width="15" height="15" border="0"><img id="wrongImg" style="display: none;" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/ecertification/wrong.jpg"> 
                                                            </td> 
                                                                    <td class="fieldLabel" style="width: 10px;">Practice :<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td style="width: 156px;">
                                                                        <s:select list="practiceList" name="practice" id="practice" cssClass="inputSelect"  headerKey="" headerValue="--Please Select--"/>
                                                                    </td>
                                                                    
                                                                    <td class="fieldLabel" style="width: 10px;">OnSite :</td>
                                                                    <td>
                                                                        <s:textfield name="onSitePlan" cssClass="inputTextBlueSmall"   id="onSitePlan" onkeypress="return isNumberKey(event)" />
                                                                    </td>
                                                                    <td>
                                                                       <s:textfield name="onSiteActual" cssClass="inputTextBlueSmall"    id="onSiteActual"  onkeypress="return isNumberKey(event)"/>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td class="fieldLabel">Customer :</td>
                                                                    <td>
                                                                        <s:textfield name="customer" cssClass="inputTextBlueLarge"    id="customer" size="25" value="%{accountName}" readonly="true" />
                                                                    </td>
                                                                    <td class="fieldLabel">Project&nbsp;Type&nbsp;:</td>
                                                                    <td>
                                                                        <s:select list="{'Support','Development'}" name="projectType" cssClass="inputSelect"  headerKey="" headerValue="--Please Select--"/>
                                                                    </td>
                                                                    <td class="fieldLabel">OffShore :</td>
                                                                    <td>
                                                                       <s:textfield name="offShorePlan" cssClass="inputTextBlueSmall"    id="offShorePlan" onkeypress="return isNumberKey(event)" />
                                                                    </td>
                                                                    <td>
                                                                       <s:textfield name="offShoreActual" cssClass="inputTextBlueSmall"    id="offShoreActual" onkeypress="return isNumberKey(event)" />
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                     <td class="fieldLabel">Pre-Sales :&nbsp;</td>
                                                            <td > 
                                                                <s:select name="preSalesMgrId" id="preSalesMgrId" cssClass="inputSelect" list="preSalesMap" emptyOption="false" headerKey="-1" headerValue="--Please Select--" value="" /><%--<img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/info.jpg" onmouseover="fixedtooltip('Dispalys employees list who are having the Role \'Pre-Sales\'.',this,event, 200,-20,40)" onmouseout="delayhidetip()" height="18" width="20">--%>
                                                            </td>
                                                            <td class="fieldLabel">Offshore&nbsp;Del.&nbsp;Lead:&nbsp;</td>
                                                            <td > 
                                                                <s:select name="offshoreDelLead" id="offshoreDelLead" cssClass="inputSelect" list="offshoreDelLeadMap" emptyOption="false" headerKey="" headerValue="--Please Select--" value="" />
                                                            </td>
                                                             
                                                            <td class="fieldLabel">Near&nbsp;Shore&nbsp;:</td>
                                                                    <td>
                                                                       <s:textfield name="nearShorePlan" cssClass="inputTextBlueSmall"   id="nearShorePlan" onkeypress="return isNumberKey(event)" />
                                                                    </td>
                                                                    <td>
                                                                       <s:textfield name="nearShoreActual" cssClass="inputTextBlueSmall"  id="nearShoreActual" onkeypress="return isNumberKey(event)" />
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                     <td class="fieldLabel">Offshore&nbsp;Tech.&nbsp;Lead:&nbsp;</td>
                                                            <td > 
                                                                <s:select name="offshoreTechLead" id="offshoreTechLead" cssClass="inputSelect" list="offshoreLeadMap" emptyOption="false" headerKey="" headerValue="--Please Select--" value="" />
                                                            </td>
                                                            <td class="fieldLabel">Onsite&nbsp;Lead:&nbsp;</td>
                                                            <td > 
                                                                <s:select name="onsiteLead" id="onsiteLead" cssClass="inputSelect" list="onsiteLeadMap" emptyOption="false" headerKey="" headerValue="--Please Select--" value="" />
                                                            </td>
                                                                    
                                                             
                                                            <td class="fieldLabel" align="right">Start&nbsp;Date&nbsp;:</td>                                                    
                                                                    <td align="left"><s:textfield name="startDatePlan" id="startDatePlan" cssClass="inputTextBlueSmall" onchange="checkDates(this);" value=""/><a style="text-decoration: none;" href="javascript:cal1.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                    </td> 
                                                                    <td align="left"><s:textfield name="startDateActual" id="startDateActual" cssClass="inputTextBlueSmall" onchange="checkDates(this);" value=""/><a style="text-decoration: none;" href="javascript:cal2.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                   <td class="fieldLabel">Cost&nbsp;Model :&nbsp;</td>
                                                            <td > 
                                                                <s:select name="costModel" id="costModel" cssClass="inputSelect" list="{'Fixed','Time & Material','Internal'}" emptyOption="false" headerKey="-1" headerValue="--Please Select--" value="" />
                                                            </td> 
                                                            <td class="fieldLabel">PMO :&nbsp;<FONT color="red"  ><em>*</em></FONT></td>
                                                            <td> 
                                                                <s:select name="pmo" id="pmo" cssClass="inputSelect" list="pmomanagerMap" emptyOption="false" headerKey="-1" headerValue="--Please Select--" value="" />
                                                            </td>
                                                            <td class="fieldLabel">End&nbsp;Date&nbsp;:</td>                                                    
                                                                    <td><s:textfield name="endDatePlan" id="endDatePlan" cssClass="inputTextBlueSmall" onchange="checkDates(this);" value=""/><a style="text-decoration: none;" href="javascript:cal3.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                    </td> 
                                                                    <td><s:textfield name="endDateActual" id="endDateActual" cssClass="inputTextBlueSmall" onchange="checkDates(this);" value=""/><a style="text-decoration: none;" href="javascript:cal4.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                    </td>
                                                                <tr>
                                                                    <td class="fieldLabel">Sector&nbsp;:</td>
                                                            <td > 
                                                                <s:select name="sector" id="sector" cssClass="inputSelect" list="{'Manufacturing','HealthCare','Logistics','Retail','Life Sciences','CPG','Banks','Insurance'}" emptyOption="false" headerKey="-1" headerValue="--Please Select--" value="" />
                                                            </td>
                                                                     <td class="fieldLabel">Complexity&nbsp;:</td>
                                                            <td> 
                                                                <s:select name="complexity" id="complexity" cssClass="inputSelect" list="{'Low','Medium','High','Critical'}" emptyOption="false" headerKey="-1" headerValue="--Please Select--" value="" />
                                                            </td>
                                                            <td class="fieldLabel" colspan="3">Overall&nbsp;State:<s:select name="state" id="state" list="{'Green','Amber','Red'}" cssClass="inputSelectSmall1" />Schedule&nbsp;:<s:select name="schedule" id="schedule" list="{'Green','Amber','Red'}" cssClass="inputSelectSmall1" /></td> 
                                                                
                                                                
                                                                </tr>
                                                                <tr>
                                                            <td class="fieldLabel">Priority&nbsp;:</td>
                                                            <td > 
                                                                <s:select name="priority" id="priority" cssClass="inputSelect" list="{'Low','Medium','High','Critical'}" emptyOption="false" headerKey="-1" headerValue="--Please Select--" value="" />
                                                            </td>
                                                            <td class="fieldLabel">Status:</td>         
                                                                <s:if test="#session.roleName == 'Admin'">   
                                                                <td class="inputOptionText"><s:select name="status" id="projectStatus" list="{'Active','Completed','Terminated','Initiated'}" cssClass="inputSelectSmall1" /></td> 
                                                                </s:if>
                                                                <s:else>                                                                            
                                                                <td class="inputOptionText"><s:select name="status" id="projectStatus" list="{'Active','Completed','Terminated','Initiated'}" disabled="true" cssClass="inputSelectSmall1" /></td> 
                                                                     </s:else> 
                                                                 <td class="fieldLabel" colspan="3">Risk&nbsp;:<s:select name="risk" id="risk" list="{'Green','Amber','Red'}" cssClass="inputSelectSmall1" />Resources&nbsp;:<s:select name="resources" id="resources" list="{'Green','Amber','Red'}" cssClass="inputSelectSmall1" /></td> 
                                                            
                                                                </tr>
                                                                <tr>
                                                                    <td class="fieldLabel">Software :</td>
                                                                    <td colspan="6"><s:textarea cols="145" rows="2" name="software"  cssClass="inputTextarea"   id="software" onchange="fieldLengthValidatorforProject(this);"/></td>                                                    
                                                                 
                                                                </tr>
                                                                   <tr>
                                                                    <td class="fieldLabel">Description :</td>
                                                                    <td colspan="6"><s:textarea cols="145" rows="2" name="description"  cssClass="inputTextarea" onchange="fieldLengthValidatorforProject(this);"  id="description"/></td>                                                    
                                                                </tr>
                                                             
                                                             
                                                               
                                                                 <tr>
                                                                    <td class="fieldLabel">Comments :</td>
                                                                    <td colspan="6"><s:textarea cols="145" rows="2" name="comments"  cssClass="inputTextarea"  id="comments" onchange="fieldLengthValidatorforProject(this);"/></td>                                                    
                                                               
                                                                 </tr>
                                                                
                                                                <tr>
                                                                    
                                                                    
                                                                </tr>
                                                                <%--<tr>
                                                                     
                                                                    <td class="fieldLabel">No.Of ReSources :</td>                                                    
                                                                    <td><s:textfield name="totalResources" cssClass="inputTextBlue" onchange="totalResourcesValidate(document.frmProjectAdd.totalResources.value);"  onblur="return validatenumber(this)"  id="totalResources" size="10" readonly="true"/></td>
                                                                </tr>--%>
                                                                 <tr>
                                                        
                                                            
                                                        </tr>
                                                                
                                                                <tr>
                                                                    <td align="right"><s:checkbox name="isDualReportingRequired" id="isDualReportingRequired" value="%{isDualReportingRequired}"/></td>                                                    
                                                                    <td class="fieldLabelLeft" colspan="3">Is this project requires dual reporting</td>      
                                                                    
                                                                </tr>
                                                                <tr align="right">
                                                                    <td  colspan="7">
                                                                        
                                                                        <s:hidden name="customerId" id="accountId" value="%{accountId}" />   
                                                                        <s:submit cssClass="buttonBg" value="Save"/>
                                                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                    </td>
                                                                </tr> 
                                                                

                                                                 
                                                            </table>
                                                            <script type="text/JavaScript">
                                                           var cal1 = new CalendarTime(document.forms['frmProjectAdd'].elements['startDatePlan']);
                                                           cal1.year_scroll = true;
                                                           cal1.time_comp = false;
                                                           
                                                           var cal2 = new CalendarTime(document.forms['frmProjectAdd'].elements['startDateActual']);
                                                           cal2.year_scroll = true;
                                                           cal2.time_comp = false;
                                                           
                                                           var cal3 = new CalendarTime(document.forms['frmProjectAdd'].elements['endDatePlan']);
                                                           cal3.year_scroll = true;
                                                           cal3.time_comp = false;
                                                           
                                                           var cal4 = new CalendarTime(document.forms['frmProjectAdd'].elements['endDateActual']);
                                                           cal4.year_scroll = true;
                                                           cal4.time_comp = false;
                                                            </script>
                                                        </s:form>
                                                        
                                                        <%-- </sx:div > --%>
                                                
                                                    </div>
                                                    <%--
                                                
                    <s:div id="AssignTeamTab" label="AssignTeam"  >
                    <s:form name="frmProjectTeamAdd" action="projectTeamAdd" theme="simple" >
                    
                    <table cellpadding="1" cellspacing="1" border="0" width="100%">
                    
                    
                    
                    <tr align="right">
                        <td class="headerText" colspan="6">
                            <s:property value="#request.resultMessage"/>
                            <s:hidden name="accountId" value="%{accountId}" />   
                            <s:submit cssClass="buttonBg" value="Save"/>
                        </td>
                    </tr>      
                    
                    
                    <td  class="fieldLabel" valign="top"> Assign Team:</td>  
                    
                    
                    
                    
                    <td>
                        <s:optiontransferselect
                            label="Employee Roles"
                            name="leftSideEmployee"
                            leftTitle="Avilable Employees"
                            rightTitle="Added Employees"
                            list="allDevelopmentMap"
                            headerKey="headerKey"
                            
                            doubleName="addedEmployeeList"
                            doubleList="addedDevelopmentMap"
                            doubleHeaderKey="doubleHeaderKey"
                            doubleValue=""
                            cssClass="inputTextarea"
                        />
                    </td>
                </tr>
                
                
            </table>
            </s:form>
            
            </s:div >            
            <!--//END TAB : -->
            --%>
                                                    <%--</sx:tabbedpanel>--%>
                                                </div>
                                            <!--//END TABBED PANNEL : -->
                                            
                                            
                                             <script type="text/javascript">

var countries=new ddtabcontent("accountTabs")
countries.setpersist(false)
countries.setselectedClassTarget("link") //"link" or "linkparent"
countries.init()

                                </script>
                                            
                                        </td>
                                    </tr>
                                    
                               
                                    
                                    
                                    <tr>
                                        <td valign="top" style="padding-left:10px;padding-top:5px;">
                                            
                                               <ul id="accountTabs1" class="shadetabs" >
                                    <li ><a href="#" class="selected" rel="projectsList"> Projects </a></li>                                   
                                            </ul>
                                            
                                                <div  style="border:1px solid gray; width:898px;height: 200px; overflow:auto; margin-bottom: 1em;">  
                                                    <div id="projectsList" class="tabcontent" >
                                                    <%--
                                            <sx:tabbedpanel id="ProjectListPannel" cssStyle="width: 840px; height: 300px;padding-left:10px;padding-top:5px;" doLayout="true">
                                                --%>
                                                
                                                    <%--<sx:div id="projectsList" label="Projects"> --%>
                                                    
                                                    <%
                                                    if(request.getAttribute("currentAccountId") != null){
                                                    currentAccountId = (String)request.getAttribute("currentAccountId");
                                                    }
                                                    strTmp = request.getParameter("txtCurr");
                                                    if (strTmp != null){
                                                    
                                                    try {
                                                    intCurr = Integer.parseInt(strTmp);
                                                    } catch (NumberFormatException NFEx) {
                                                    NFEx.printStackTrace();
                                                    }
                                                    }else{
                                                    intCurr = 1;
                                                    }
                                                    
                                                    strSortCol = "ProjectName";
                                                    
                                                    try{
                                                    /* Getting DataSource using Service Locator */
                                                    connection = ConnectionProvider.getInstance().getConnection();
                                                    
                                                    /* Sql query for retrieving resultset from DataBase */
                                                    queryString = "Select Id,ProjectName,ProjectStartDate,ProjectEndDate,TotalResources,CustomerId,ProjectManagerUID FROM tblProjects";
                                                    queryString = queryString+" WHERE CustomerId="+currentAccountId+" and Status='Active' ORDER BY ProjectStartDate DESC";
                                                    
                                                    %>
                                                    <form action="" method="post" name="frmDBGrid"> 
                                                        <table cellpadding="0" cellspacing="0" width="100%">
                                                            <tr>
                                                                <td class="headerText">
                                                                    <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td>
                                                                    <!-- DataGrid for list all activities -->
                                                                    <grd:dbgrid id="tblProjects" name="tblProjects" width="100" pageSize="8" 
                                                                                currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                                dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                        
                                                                        <grd:gridpager imgFirst="../includes/images/DBGrid/First.gif" imgPrevious="../includes/images/DBGrid/Previous.gif" 
                                                                                       imgNext="../includes/images/DBGrid/Next.gif" imgLast="../includes/images/DBGrid/Last.gif"
                                                                                       addImage="../includes/images/DBGrid/Add.png"/>
                                                                        
                                                                        <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" 
                                                                                        imageAscending="../includes/images/DBGrid/ImgAsc.gif" 
                                                                                        imageDescending="../includes/images/DBGrid/ImgDesc.gif"/>   
                                                                        
                                                                        <grd:rownumcolumn headerText="SNo" width="5" HAlign="right"/>
                                                                        <grd:imagecolumn  headerText="Edit"  width="5"   
                                                                                          linkUrl="../projects/getProject.action?Id={Id}&accountId={CustomerId}"  
                                                                                          HAlign="center"
                                                                                          imageSrc="../includes/images/DBGrid/newEdit_17x18.png"
                                                                                          imageBorder="0" 
                                                                                          imageWidth="16" 
                                                                                          imageHeight="16" 
                                                                                          alterText="Click to Edit" />
                                                                        <grd:datecolumn dataField="ProjectStartDate" headerText="Start Date" width="15" dataFormat="MM-dd-yyyy" />
                                                                        <grd:datecolumn dataField="ProjectEndDate"  headerText="End Date" width="15" dataFormat="MM-dd-yyyy" />
                                                                        <grd:textcolumn dataField="ProjectName"  headerText="Project Name" width="37"/>
                                                                        <%-- <grd:textcolumn dataField="ProjectManagerUID" headerText="Manager UID" width="15" /> --%>
                                                                        <grd:numbercolumn dataField="TotalResources"  headerText="No.Resources" width="8" dataFormat="0"/>
                                                                        
                                                                    </grd:dbgrid>
                                                                    <!-- these components are DBGrid Specific  -->
                                                                    <input type="hidden" name="txtCurr" value="<%=intCurr%>">
                                                                    <input type="hidden" id="txtSortCol" name="txtSortCol" value="<%=strSortCol%>">
                                                                    <input type="hidden" id="txtSortAsc" name="txtSortAsc" value="<%=strSortOrd%>">
                                                                    
                                                                    <input type="hidden" name="submitFrom" value="dbGrid">
                                                                    
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </form> 
                                                    
                                                    <%}catch(Exception se){
                                                    System.out.println("Exception in Contact "+se);
                                                    }finally{
                                                    if(connection!= null){
                                                    connection.close();
                                                    }
                                                    }%>
                                                    
                                                </div>
                                                </div>
                                                
                                                <script type="text/javascript">

var countries=new ddtabcontent("accountTabs1")
countries.setpersist(false)
countries.setselectedClassTarget("link") //"link" or "linkparent"
countries.init()

                                </script>
                                                
                                                <%--</sx:div>
                                            </sx:tabbedpanel>--%>
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
            <!--//END FOOTER : Record for Footer Background and Content-->
        </table>
        <!--//END MAIN TABLE : Table for template Structure-->
     
        
        
    </body>
</html>
