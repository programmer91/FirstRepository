<!-- 
 *******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  November 20, 2007, 3:25 PM
 *
 * Author  : Ajay Tummapala<atummapala@miraclesoft.com>
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
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %> 
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
        <title>Hubble Organization Portal :: Exam Result List</title>
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
        <%--  <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ecertification/ecertificationAjax.js"/>"></script> --%>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/cre/CreAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ecertification/ecertificationAjax.js?version=1.0"/>"></script>

        <s:include value="/includes/template/headerScript.html"/>
        <%-- for issue reminder popup --%>

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
            function init1() {
                // alert("hello");
                
                var insflag =  document.getElementById("insSerachFlag").value;
                // alert("insflag---->"+insflag);
              
 
                if(insflag==0){
                    var today = new Date();
                    var endDay   = new Date();
                    var day = today.getDay() || 7; // Get current day number, converting Sun. to 7
                    if( day !== 1 ) {               // Only manipulate the date if it isn't Mon.
                        today.setHours(-24 * (day - 1)); 
                        endDay.setHours(-24*(day -7)); 


                    } // Set the hours to day number minus 1
                    var cDate = today.getDate();
                    var cMonth = today.getMonth()+1;
                    var cYear = today.getFullYear();

                    var sDate = endDay.getDate();
                    var sMonth = endDay.getMonth()+1;
                    var sYear = endDay.getFullYear();
 
                    var datefrom =cMonth+"/"+cDate+"/"+cYear;
                    var dateto = sMonth+"/"+sDate+"/"+sYear;
                    var datefrom1 =cMonth+"/"+cDate+"/"+cYear;
                    var dateto1 = sMonth+"/"+sDate+"/"+sYear;

                    document.getElementById("startDate").value = datefrom;
                    document.getElementById("endDate").value = dateto;
                 
                }

                //--------------------   Employee name suggestion list -----
                autorow = document.getElementById("menu-popup");
                autorow.style.display = "none";

                var height = document.documentElement.clientHeight - 120;
   
                autorow.style.height = Math.max(height, 150);
                autorow.style.overflowY = "scroll";
    
                completeTables = document.getElementById("completeTable");
                completeTables.setAttribute("bordercolor", "white");
                fieldVisable();
                //--------------------
            }    
            
        </script>

        <script type="text/javascript">
            function fieldVisable(){
                //alert("hiiiii");
                var et = document.getElementById("empType").value;
                // alert("et--->"+et);
                if(et==1){
        
                    /*  document.getElementById("empName").value = "";
        document.getElementById("loginId1").value = "";
        document.getElementById("conId1").value = "";*/
        
                    document.getElementById("consultantLable").style.display = "none";
                    document.getElementById("consultantField").style.display = "none";
                    document.getElementById("emplable").style.display = "none";
                    document.getElementById("empField").style.display = "none";

                    document.getElementById("emplable").style.display = "block";
                    document.getElementById("empField").style.display = "block";
                }
                if(et==2){
                    /*  document.getElementById("empName").value = "";
        document.getElementById("loginId1").value = "";
        document.getElementById("conId1").value = "";*/
        
                    document.getElementById("consultantLable").style.display = "none";
                    document.getElementById("consultantField").style.display = "none";
                    document.getElementById("emplable").style.display = "none";
                    document.getElementById("empField").style.display = "none";

                    document.getElementById("consultantLable").style.display = "block";
                    document.getElementById("consultantField").style.display = "block";
                }
            }
        </script>

    </head>



    <!--<body class="bodyGeneral" oncontextmenu="return false;" onload="init1();"> -->
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
                                    <li ><a href="#" rel="IssuesSearchTab">Exam Results Search</a></li>
                                    <li ><a href="#" class="selected" rel="issuesTab">Exam Results List</a></li>
                              <!--  <li ><a href="#" rel="IssuesSearchExcelTab">Exam Result Excel sheet</a></li>-->
                                </ul>

                                <%-- <sx:tabbedpanel id="IssuesPanel" cssStyle="width: 845px; height: 500px;padding:5px 5px;" doLayout="true"> --%>
                                <div  style="border:1px solid gray; width:845px;height: 500px; overflow:auto; margin-bottom: 1em;">
                                    <!--//START TAB : -->
                                    <%-- <sx:div id="issuesTab" label="IssuesList" cssStyle="overflow:auto;" > --%>

                                    <div id="issuesTab" class="tabcontent"  >

                                        <%

                                            try {
                                                queryString = "";
                                                /* Getting DataSource using Service Locator */
                                                connection = ConnectionProvider.getInstance().getConnection();


                                                /* String Variable for storing current position of records in dbgrid*/
                                                strTmp = request.getParameter("txtCurr");
                                                intCurr = 1;
                                                if (strTmp != null) {
                                                    intCurr = Integer.parseInt(strTmp);
                                                }

                                                /* Specifing Shorting Column */
                                                strSortCol = request.getParameter("txtSortCol");

                                                if (strSortCol == null) {
                                                    strSortCol = "DateCreated";
                                                }

                                                strSortOrd = request.getParameter("txtSortAsc");
                                                if (strSortOrd == null) {
                                                    strSortOrd = "ASC";
                                                }

                                                queryString = session.getAttribute(ApplicationConstants.ECERT_MY_EXAM_RESULT_QUERY).toString();
                                                /* Sql query for retrieving resultset from DataBase */
                                                //out.println(queryString);

                                                if (session.getAttribute("resultMessage1") != null) {
                                                    out.println(session.getAttribute("resultMessage1"));
                                        %>
                                        <script>
                                            alert("No records exists for the employee between given dates!!");
                                        </script>
                                        <%

                                                session.removeAttribute("resultMessage1");
                                            }
                                        %>
                                        <s:form action="" name="frmDBGrid" theme="simple"> 
                                            <div style="width:825px;"> 
                                                <table cellpadding="0" border="0" cellspacing="0" width="100%">
                                                    <tr>
                                                        <td class="headerText">

                                                            <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <!-- SubtopicName,ExamKeyId,Marks,TotalQuestions,AttemptedQuestions,DateSubmitted,ExamStatus -->
                                                            <grd:dbgrid id="tblCrmAttachments" name="tblCrmAttachments" width="100" pageSize="19" 
                                                            currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                            dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                <grd:gridpager imgFirst="../includes/images/DBGrid/First.gif" imgPrevious="../includes/images/DBGrid/Previous.gif" 
                                                                               imgNext="../includes/images/DBGrid/Next.gif" imgLast="../includes/images/DBGrid/Last.gif"
                                                                               />
                                                                <grd:rownumcolumn headerText="SNo" width="2" HAlign="center"/>                 
                                                                <%-- <grd:numbercolumn dataField="ExamKeyId"  headerText="ExamKeyId" width="10"   dataFormat=""/>--%>
                                                                <grd:anchorcolumn dataField="empId" 
                                                                                  headerText="Candidate Name" 
                                                                                  linkUrl="javascript:getCandidateName('{empId}','{TopicName}');" linkText="{empId}" width="20"/>

                                                                <%-- <grd:textcolumn dataField="TopicName"  headerText="Exam Name"   width="10" sortable="true"/> --%>

                                                                <grd:ajaxpopup dataField="TopicName" id="{TopicId}" linkText=":::" maxLength="3" 
                                                                               headerText="Exam Name" JSFunction="getExamName"  width="15" />

                                                                <%-- <grd:datecolumn dataField="DateSubmitted"  headerText="Date Taken"  dataFormat="MM-dd-yyyy HH:mm:SS" width="15" sortable="true"/>--%>

                                                                <grd:datecolumn dataField="DateSubmitted"  headerText="Date Taken"  dataFormat="MM-dd-yyyy" width="15" sortable="true"/>

                                                                <grd:numbercolumn dataField="TotalQuestions"  headerText="TotalQuestions" width="10"   dataFormat=""/> 
                                                                <grd:numbercolumn dataField="AttemptedQuestions"  headerText="AttemptedQuestions"   width="10"  dataFormat=""/> 

                                                                <grd:numbercolumn dataField="Marks"  headerText="Marks" width="8"  dataFormat=""/>
                                                                <grd:numbercolumn dataField="Percentage"  headerText="Percentage(%)" width="8"  dataFormat=""/>
                                                                <grd:textcolumn dataField="ExamStatus"  headerText="Status"   width="8" sortable="true"/> 

                                                                <grd:imagecolumn  headerText="Reivew" width="5"  HAlign="center"  
                                                                                  imageSrc="../includes/images/DBGrid/View.gif" 
                                                                                  linkUrl="reviewExam.action?examKeyId={ExamKeyId}" 
                                                                                  imageBorder="0" imageWidth="23" imageHeight="12" alterText="Click to Add" />
                                                            </grd:dbgrid>



                                                            <!-- these components are DBGrid Specific  -->
                                                            <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                            <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                            <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                                                            <s:hidden name="startDate" value="%{startDate}"/>
                                                            <s:hidden name="endDate" value="%{endDate}"/>
                                                            <s:hidden name="loginId" value="%{loginId}"/>
                                                            <s:hidden name="conId" value="%{conId}"/>
                                                            <s:hidden name="empType" value="%{empType}"/>
                                                        </td>
                                                    </tr>
                                                </table>    
                                            </div>
                                        </s:form>  
                                        <%
                                                connection.close();
                                                connection = null;
                                                /* For Exception handling mechanism */
                                            } catch (Exception se) {
                                                System.out.println("Exception in IssuesList " + se);
                                            } finally {
                                                if (connection != null) {
                                                    connection.close();
                                                    connection = null;
                                                }
                                                if (session != null) {
                                                    session.removeAttribute("resultMessage");
                                                }
                                            }
                                        %>
                                        <%--  </sx:div > --%>
                                    </div>


                                    <div id="IssuesSearchTab" class="tabcontent"  >  
                                        <!--searchExamResultList-->

                                        <s:form name="frmExamResultSearch" action="%{currentAction}" theme="simple" method="post" onsubmit="return (validateDates() && compareDates(document.getElementById('startDate').value,document.getElementById('endDate').value));">

                                            <!--The components of searching issues -->
                                            <table border="0" cellpadding="1" cellspacing="1" width="100%">

                                                <tr>
                                                    <td class="headerText" align="right">
                                                        <s:hidden name="insSerachFlag" id="insSerachFlag"  cssClass="inputTextBlue"  value="%{insSerachFlag}"  theme="simple"/>
                                                        <s:submit cssClass="buttonBg" value="Search" />
                                                        <input type="button" value="Generate Excel" Class="buttonBg" onclick="getExamReport();"/> 
                                                    </td>
                                                </tr>

                                                <tr><td align="center"> <table>

                                                            <tr>
                                                                <td  class="fieldLabel">Start Date:</td>

                                                                <td width="40%"><s:textfield name="startDate" value="%{startDate}" id="startDate" cssClass="inputTextBlue"/><a href="javascript:cal1.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                             width="20" height="20" border="0"></a></td>
                                                                <td class="fieldLabel">End Date:</td>
                                                                <td><s:textfield name="endDate" id="endDate" value="%{endDate}" cssClass="inputTextBlue"/><a href="javascript:cal2.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                             width="20" height="20" border="0"></a></td>

                                                                <td></td>
                                                                <td></td>

                                                            </tr>
                                                            
                                                            <tr>
                                                                <s:if test="%{currentAction == 'searchExamResultList'}">

                                                                    <td class="fieldLabel">Exam&nbsp;Type:</td>
                                                                    <td>
                                                                        <s:select headerKey="" headerValue="--select--" list="#@java.util.LinkedHashMap@{'1':'Employee', '2':'Consultant'}" name="empType" 
                                                                                  value="%{empType}" cssClass="inputSelect" id="empType" onchange="fieldVisable();" onblur="fieldVisable();getDomainByExamType();"/>
                                                                    </td>

                                                                    <td class="fieldLabel">
                                                                        <span id="emplable" style="display:none">Name:</span>
                                                                        <span id="consultantLable" style="display:none">Id:</span>
                                                                    </td>
                                                                    <td>
                                                                        <span id="empField" style="display:none">
                                                                            <s:textfield name="empName" id="empName"  cssClass="inputTextBlue"  theme="simple" onkeyup="fillEmployee();"/>

                                                                            <s:hidden name="loginId" id="loginId1"  cssClass="inputTextBlue" theme="simple"/>
                                                                        </span>
                                                                        <span id="consultantField" style="display:none">
                                                                            <s:textfield name="conId" id="conId1"  cssClass="inputTextBlue" theme="simple"/>
                                                                        </span>
                                                                        <div id="empValidationMessage"></div> 
                                                                    </td>

                                                                </s:if>
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel">Practice :</td>
                                                                <td> <s:select label="Select Domain" id="domainId" 
                                                                       name="domainId" headerKey=""            
                                                                       headerValue="-- Please Select --"
                                                                       list="domainMap" cssClass="inputSelect" value="%{domainId}" onchange="getTopicData();"/></td>
                                                                <td class="fieldLabel">Topic :</td>
                                                                <td>  <s:select label="Select Domain" id="topicId" 
                                                                       name="topicId" headerKey="-1"            
                                                                       headerValue="-- Please Select --"
                                                                       list="topicsMap" cssClass="inputSelect" value="%{topicId}" /></td>
                                                                
                                                            </tr>
                                                            <%-- <tr>
                                                             <td></td>
                                                             <td align="">
                                                             <s:submit cssClass="buttonBg" value="Search" />
                                                             </td>
                                                         </tr>--%>

                                                        </table>  
                                                    </td>
                                                </tr> 

                                            </table>

                                        </s:form>
                                        <!-- this script for After loading Form it will instantiate Calender Objects as you require -->
                                        <script type="text/JavaScript">
                                            var cal1 = new CalendarTime(document.forms['frmExamResultSearch'].elements['startDate']);
                                            cal1.year_scroll = true;
                                            cal1.time_comp = false;
                                            
                                            var cal2 = new CalendarTime(document.forms['frmExamResultSearch'].elements['endDate']);
                                            cal2.year_scroll = true;
                                            cal2.time_comp = false;
                                        </script>

                                        <%--  </sx:div> --%>
                                    </div>
                                    <!--//END TAB : -->


                                </div>
                                <!--//END TABBED PANNEL : -->
                                <script type="text/javascript">

                                    var countries=new ddtabcontent("accountTabs")
                                    countries.setpersist(false)
                                    <%-- <%
                                     String ExamResultFalg=(String)session.getAttribute("ExamResultFalg");
                                     if(ExamResultFalg!=null){
                                     %>
                                         countries.setpersist(true)
                                     <%
                                                                    }else{
                                     %>
                                             alert("demo");
                                              countries.setpersist(false)
                                      <%
                                                                    }
                                     session.removeAttribute("ExamResultFalg");
                                     %>--%>
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
                        <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0"></table>
                    </div>

                </td>
            </tr>

            <!--//END FOOTER : Record for Footer Background and Content-->

        </table>
        <!--//END MAIN TABLE : Table for template Structure-->
<script>
$(document).ready(function() {
   init1();
     });
</script>	
    </body>
</html>
