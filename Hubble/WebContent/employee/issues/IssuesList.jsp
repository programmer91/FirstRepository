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
        <title>Hubble Organization Portal :: Employee Issues List</title>
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
        
        <s:include value="/includes/template/headerScript.html"/>
        <%-- for issue reminder popup --%>
        <script type="text/javascript">
            function win_open1(url,id,priemail){
             // alert(priemail.length);
                    if(priemail.length <=1)
                    {
                        alert("This issue is not assigned to any person.Please assign the issue before sending reminder");
                    }else{
                        //alert("id-->"+id);
                        var values=document.getElementById('mailid').innerHTML;
                        url = url+"?issueid="+id;
                        newWindow=window.open(url,'issueid','height=230,width=540');
                    }
        }
        
        // New functionality 
        
         function initDate(sampleValue) {
                     if(sampleValue == null || sampleValue=="null")
                         {
                   
                        Date.prototype.getWeek = function(start)
                        {    
                        start = start || 0;
                        var today = new Date(this.setHours(0, 0, 0, 0));
                        var day = today.getDay() - start;
                        var date = today.getDate() - day;

                        // Grabbing Start/End Dates
                        var StartDate = new Date(today.setDate(date));
                        var EndDate = new Date(today.setDate(date + 6));
                        return [StartDate, EndDate];
                        }

                        var Dates = new Date().getWeek();
                        var month ;
                        var monthOrig = Dates[0].getMonth()+1;
                        var monthOrig1 = Dates[1].getMonth()+1;
                        if(parseInt(monthOrig) <10)
                        {
                            month = parseInt(Dates[0].getMonth())+parseInt("1");
                            month = "0"+month
                        }
                        var month1 ;
                        if(parseInt(monthOrig1) <10)
                        {
                           month1 = parseInt(Dates[1].getMonth())+parseInt("1");
                           month1 = "0"+month1;
                        }
                        var datefrom =month+"/"+Dates[0].getDate()+"/"+Dates[0].getFullYear();
                        var dateto = month1+"/"+Dates[1].getDate()+"/"+Dates[1].getFullYear();
                       //alert (datefrom + "    ----- "+dateto);
 
                           document.getElementById('startDate').value = datefrom;
                           document.getElementById('endDate').value = dateto;

                         } 
                      
		}
        
        
        
        </script>
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
    	
	<%
String check = null;
if(request.getAttribute("check")!=null)
  check = request.getAttribute("check").toString();

//System.out.println("check-->"+check);
    %>
    
   <%-- <body class="bodyGeneral" oncontextmenu="return false;" onload="init1();initDate('<%=check%>');"> --%>
   <body class="bodyGeneral" oncontextmenu="return false;"> 
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
        String currentSearch=null;
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
                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                            
                            <!--//START DATA COLUMN : Coloumn for Screen Content-->
                            <td width="850px" class="cellBorder" valign="top" style="padding:5px 5px;">
                                <!--//START TABBED PANNEL : -->
                                <%
                                    //out.print("naga::"+ request.getAttribute("subnavigateTo").toString());
                                 /// session.setAttribute(ApplicationConstants.ISSUE_TO_TASK,request.getAttribute("subnavigateTo").toString());
                                %>
                                
                                <ul id="accountTabs" class="shadetabs" >
                                  <%--  <li ><a href="#" class="selected" rel="issuesTab"  > IssuesList </a></li>
                                    <li ><a href="#" rel="IssuesSearchTab">Issues Search</a></li> --%>
                                    <% if(request.getParameter("issueList")==null)
                                       {%>
                                        <li ><a href="#" rel="IssuesSearchTab">Task Search</a></li>
                                    <li ><a href="#" class="selected" rel="issuesTab"  > Tasks List </a></li>
                                    <%}else{%>
                                    <li ><a href="#" class="selected" rel="IssuesSearchTab">Tasks Search</a></li>
                                     <% }%>
                                    
                                </ul>
                                
                                <%-- <sx:tabbedpanel id="IssuesPanel" cssStyle="width: 845px; height: 500px;padding:5px 5px;" doLayout="true"> --%>
                                <div  style="border:1px solid gray; width:845px;height: 500px; overflow:auto; margin-bottom: 1em;">
                                    <!--//START TAB : -->
                                    <%-- <sx:div id="issuesTab" label="IssuesList" cssStyle="overflow:auto;" > --%>
                                     <% if(request.getParameter("issueList")==null)
                                            {
                                                //System.out.println("list"+request.getParameter("list"));
                                            %>
                                    <div id="issuesTab" class="tabcontent"  >
                                        
                                        <%
                                        
                                        try{
                                            queryString="";
                                            /* Getting DataSource using Service Locator */
                                            connection = ConnectionProvider.getInstance().getConnection();
                                            
                                            
                                            /* String Variable for storing current position of records in dbgrid*/
                                            strTmp = request.getParameter("txtCurr");
                                            intCurr = 1;
                                            if (strTmp != null)
                                                intCurr = Integer.parseInt(strTmp);
                                            
                                            /* Specifing Shorting Column */
                                            strSortCol = request.getParameter("txtSortCol");
                                            
                                            if (strSortCol == null) strSortCol = "DateCreated";
                                            
                                            strSortOrd = request.getParameter("txtSortAsc");
                                            if (strSortOrd == null) strSortOrd = "ASC";
                                            
                                            queryString = session.getAttribute(ApplicationConstants.QS_ISSUES_LIST).toString();
                                            /* Sql query for retrieving resultset from DataBase */
                                           // out.println(queryString);
                                            
                                           
                                            
                                            // out.print("naga::"+ request.getAttribute("subnavigateTo").toString());
                                             
                                           // session.setAttribute(ApplicationConstants.ISSUE_TO_TASK,request.getAttribute("subnavigateTo").toString());
                                           String userRoleName = session.getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
                                            String issueAction = "../../employee/issues/issue.action?accessType=Issue";
                                        
                                        %>
                                        <s:form action="" name="frmDBGrid" theme="simple"> 
                                            <div style="width=1000px;"> 
                                                <table cellpadding="0" border="0" cellspacing="0" width="100%">
                                                    <tr>
                                                        <td class="headerText">
                                                            <s:property value="#request.resultMessage || #session.resultMessage"/>
                                                            <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <s:if test="#session.isUserManager == 1 || #session.isUserTeamLead==1">     
                                                                <grd:dbgrid id="tblCrmAttachments" name="tblCrmAttachments" width="100" pageSize="18" 
                                                                            currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                            dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                    <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                                   imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                                   />
                                                                    <grd:rownumcolumn headerText="SNo" width="2" HAlign="center"/>                 
                                                                    <grd:anchorcolumn dataField="IssueTitle" 
                                                                                      headerText="IssueTitle" 
                                                                                      linkUrl="getIssue.action?issueId={Id}&accessType=Issue&resM=" linkText="{IssueTitle}" width="20"/>
                                                                    
                                                                    <%-- <grd:textcolumn dataField="IssueTitle"  headerText="IssueTitle"   width="10" sortable="true"/>   --%>
                                                                    <grd:textcolumn dataField="IssueType"  headerText="IssueType"   width="10" sortable="true"/> 
                                                                    <grd:datecolumn dataField="DateCreated"  headerText="DateCreated"  dataFormat="MM-dd-yyyy HH:mm:SS" width="15" sortable="true"/>
                                                                    <grd:textcolumn dataField="Status"  headerText="Status" width="10" sortable="true"/> 
                                                                    <grd:textcolumn dataField="Severity"  headerText="Severity"   width="10" sortable="true"/> 
                                                                    <%-- <grd:textcolumn dataField="Comments"  headerText="Comments" width="10" sortable="true"/>   --%>
                                                                    <grd:anchorcolumn dataField="Comments" linkUrl="javascript:getIssueComments('{Id}')" headerText="Comments"
                                                                                      linkText="Click To View"  width="10" />
                                                                    
                                                                    <grd:textcolumn dataField="AssignedTo"  headerText="AssignedTo" width="12" sortable="true"/> 
                                                                    <grd:textcolumn dataField="SecAssignTO"  headerText="Sec-AssignedTo" width="12" sortable="true"/>
                                                                    <%--  <grd:imagecolumn headerText="Alert" width="4" HAlign="center" imageSrc="../../includes/images/DBGrid/Edit.gif"
                                                                                     linkUrl="getIssue.action?issueId={Id}&accessType=Issue" imageBorder="0"
                                                                                     imageWidth="16" imageHeight="16" alterText="Click to edit"></grd:imagecolumn>  --%>
                                                                    <grd:imagecolumn  headerText="reminder" width="4" HAlign="center" imageSrc="../../includes/images/DBGrid/reminder.jpg"
                                                                                      linkUrl="javascript:win_open1('/Hubble/employee/issues/IssueReminderWindow.jsp','{Id}','{AssignedTo}')"
                                                                                      imageBorder="0" 
                                                                                      imageWidth="16" imageHeight="16" alterText="Click to edit"></grd:imagecolumn> 
                                                                    
                                                                </grd:dbgrid>
                                                            </s:if>
                                                            <s:else>
                                                                  <grd:dbgrid id="tblCrmAttachments" name="tblCrmAttachments" width="100" pageSize="18" 
                                                                            currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                            dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                    <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                                   imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                                   />
                                                                    <grd:rownumcolumn headerText="SNo" width="2" HAlign="center"/>                 
                                                                    <grd:anchorcolumn dataField="IssueTitle" 
                                                                                      headerText="IssueTitle" 
                                                                                      linkUrl="getIssue.action?issueId={Id}&accessType=Issue&resM=" linkText="{IssueTitle}" width="20"/>
                                                                    
                                                                    <%-- <grd:textcolumn dataField="IssueTitle"  headerText="IssueTitle"   width="10" sortable="true"/>   --%>
                                                                    <grd:textcolumn dataField="IssueType"  headerText="IssueType"   width="10" sortable="true"/> 
                                                                    <grd:datecolumn dataField="DateCreated"  headerText="DateCreated"  dataFormat="MM-dd-yyyy HH:mm:SS" width="15" sortable="true"/>
                                                                    <grd:textcolumn dataField="Status"  headerText="Status" width="10" sortable="true"/> 
                                                                    <grd:textcolumn dataField="Severity"  headerText="Severity"   width="10" sortable="true"/> 
                                                                    <%-- <grd:textcolumn dataField="Comments"  headerText="Comments" width="10" sortable="true"/>   --%>
                                                                    <grd:anchorcolumn dataField="Comments" linkUrl="javascript:getIssueComments('{Id}')" headerText="Comments"
                                                                                      linkText="Click To View"  width="10" />
                                                                    
                                                                    <grd:textcolumn dataField="AssignedTo"  headerText="AssignedTo" width="12" sortable="true"/> 
                                                                     <grd:textcolumn dataField="SecAssignTO"  headerText="Sec-AssignedTo" width="12" sortable="true"/>
                                                                    <%--  <grd:imagecolumn headerText="Alert" width="4" HAlign="center" imageSrc="../../includes/images/DBGrid/Edit.gif"
                                                                                     linkUrl="getIssue.action?issueId={Id}&accessType=Issue" imageBorder="0"
                                                                                     imageWidth="16" imageHeight="16" alterText="Click to edit"></grd:imagecolumn>  --%>
                                                                   <%-- <grd:imagecolumn  headerText="reminder" width="4" HAlign="center" imageSrc="../../includes/images/DBGrid/reminder.jpg"
                                                                                      linkUrl="javascript:win_open1('/Hubble/employee/issues/IssueReminderWindow.jsp','{Id}')"
                                                                                      imageBorder="0" 
                                                                                      imageWidth="16" imageHeight="16" alterText="Click to edit"></grd:imagecolumn>  --%>
                                                                    
                                                                </grd:dbgrid>
                                                            </s:else>
                                                        
                                                            <!-- these components are DBGrid Specific  -->
                                                            <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                            <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                            <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                                                            
                                                        </td>
                                                    </tr>
                                                </table>    
                                            </div>
                                        </s:form>  
                                        <%
                                        connection.close();
                                        connection = null;
                                        /* For Exception handling mechanism */
                                        }catch(Exception se){
                                            System.out.println("Exception in IssuesList "+se);
                                        }finally{
                                            if(connection!= null){
                                                connection.close();
                                                connection = null;
                                            }
                                            if(session!=null){
                                                session.removeAttribute("resultMessage");
                                            }
                                        }
                                        %>
                                        <%--  </sx:div > --%>
                                    </div>
                                    <%}%>
                                    <!--//END TAB : -->
                                  
                                    <%--  <sx:div id="IssuesSearchTab" label="Issues Search" > --%>
                                    <div id="IssuesSearchTab" class="tabcontent"  >   
                                        <s:form name="frmSearch" action="%{currentSearch}" theme="simple" method="post" onsubmit="return (validateDates() && compareDates(document.getElementById('startDate').value,document.getElementById('endDate').value));">
                                        
                                            <!--The components of searching issues -->
                                            <table border="0" cellpadding="1" cellspacing="1" width="100%">
                                                <tr>
                                                    <td class="headerText" align="right">
                                                     
                                                                    <s:hidden name="submitFrom" value="Search"/>
                                                                    <s:submit cssClass="buttonBg" value="Search"/>
                            
                                                    </td>
                                                </tr>
                                              <%--  <tr>
                                                    <td class="headerText">
                                                        <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                    </td>
                                                </tr>--%>
                                                <tr><td align="left"> <table>
                                                    <%-- Issue name based search  --%>
                                                            <tr>
                                                            <td class="fieldLabel">Task Title:</td>
                                                            <td width="20%"><s:textfield name="issueName" id="issueName"  cssClass="inputTextBlue"  value="%{currentIssue.issueName}"  theme="simple"/></td>
                                                            <td  class="fieldLabel">Status:</td>
                                                                <td><s:select list="{'Assigned','InProgress','Closed','Not Closed','Created','Not Started'}"
                                                                                  name="statusId"
                                                                                  headerKey="" 
                                                                                  headerValue="--select--"
                                                                                  value="%{currentIssue.statusId}"
                                                                              cssClass="inputSelect"  /></td>
                                                            </tr> 
                                                            <%-- end of issue name based search ---%>
                                                            <tr>
                                                                
                                                                <td  class="fieldLabel">Start Date:</td>
                                                                   
                                                                <td width="40%"><s:textfield name="startDate" value="%{startDate}" id="startDate" cssClass="inputTextBlue"/><a href="javascript:cal1.popup();">
                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                     width="20" height="20" border="0"></a></td>
                                                                <td class="fieldLabel">End Date:</td>
                                                                <td><s:textfield name="endDate" id="endDate" value="%{endDate}" cssClass="inputTextBlue"/><a href="javascript:cal2.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                     width="20" height="20" border="0"></a></td>
                                                               
                                                            </tr>
                                                              <tr>
                    <td class="fieldLabel">Customer Name : </td>
                    <td>  <%-- onkeyup="fillCustomer();"   %{customerName || '%'}--%>
                        <s:textfield name="customerName" id="customerName"  cssClass="inputTextBlue"  value="%{currentIssue.customerName}"  theme="simple" onkeyup="fillCustomerInIssue();"/>
                        <div id="validationMessage"></div>
                        <s:hidden name="customerId" value="%{currentIssue.customerId}" id="customerId"/> 
                    </td>
                    
                       <td class="fieldLabel">Project Name : </td>
                        <td>  
                            <s:select list="%{projectNamesMap}" 
                                      name="projectName"
                                      id="projectName"
                                      headerKey="" 
                                      headerValue="--PleaseSelect--"
                                      value="%{currentIssue.projectName}"
                                      cssClass="inputSelect"  />
                        </td>
                     
                </tr> 
                <s:if test="%{currentSearch == 'doSearchTeamIssues' }">
                      <tr>
                      
                      
                  <td class="fieldLabel">Creayed&nbsp;By :</td>
            <s:if test="#session.isUserManager == 1 || #session.isUserTeamLead==1 ">
                
                <td ><s:textfield name="assignedToUID" id="assignedToUID" onkeyup="fillEmployee('pre');" cssClass="inputTextBlue" value="%{currentIssue.assignedToUID}" theme="simple" readonly="false"/>
                    
                    <div id="assignEmpValidationMessage"></div>  
                    <s:hidden name="preAssignEmpId" value="%{currentIssue.preAssignEmpId}" id="preAssignEmpId"/> 
                </td>                
                
                
                <!-- sec assign to start-->
                     
                <td class="fieldLabel">Assign&nbsp;To :</td>
                <td ><s:textfield name="postAssignedToUID" id="postAssignedToUID" onkeyup="fillEmployee('post');" cssClass="inputTextBlue" value="%{currentIssue.postAssignedToUID}" theme="simple" readonly="false"/>
                    <div id="postAssignEmpValidationMessage"></div>  
                    <s:hidden name="postAssignEmpId" value="%{currentIssue.postAssignEmpId}" id="postAssignEmpId"/>
                </td>                
                
                
            </s:if>  
            <!-- sec assignto End -->
            </s:if>
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
                                            
                                                           var cal2 = new CalendarTime(document.forms['frmSearch'].elements['endDate']);
                                                           cal2.year_scroll = true;
                                                           cal2.time_comp = false;
                                        </script>
                                        
                                        <%--  </sx:div> --%>
                                    </div>
                                    
                                    <!--//END TAB : -->
                                    <%--  </sx:tabbedpanel> --%>
                                </div>
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
		$(window).load(function(){
       init1();
	   initDate('<%=check%>');
		});
		</script>
        
    </body>
</html>
