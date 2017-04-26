<!-- 
 *******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  july 22, 2013, 3:25 PM
 *
 * Author  : Prasad Kandregula<vkandregula@miraclesoft.com>
 *
 * File    : QuestionsList.jsp
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
        <title>Hubble Organization Portal :: Employee Exams List</title>
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
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ecertification/ecertificationAjax.js"/>"></script>
        
        <s:include value="/includes/template/headerScript.html"/>
        <%-- for issue reminder popup --%>
        <%-- <script type="text/javascript">
            function win_open2(url,id,priemail){
             // alert(priemail.length);
                    if(priemail.length <=1)
                    {
                        alert("This task is not assigned to any person.Please assign the issue before sending reminder");
                    }else{
                        //alert("id-->"+id);
                        var values=document.getElementById('mailid').innerHTML;
                        url = url+"?issueid="+id;
                        newWindow=window.open(url,'issueid','height=230,width=540');
                    }
        }               
            

        
        </script> --%>
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
       <%
String check = null;

if(request.getAttribute("check")!=null)
  check = request.getAttribute("check").toString();
    %> 
    </head>
 <%--   <body class="bodyGeneral" onload="init1('<%=check%>');" oncontextmenu="return false;"> --%>
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
        String roleName = null;
        String isManager = null;
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
                           <%
                           roleName = session.getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
                           isManager = session.getAttribute(ApplicationConstants.SESSION_IS_USER_MANAGER).toString();
                           //out.println("roleName--->"+roleName+"========isManager--------->"+isManager);
                           %> 
                            <!--//START DATA COLUMN : Coloumn for Screen Content-->
                            <td width="850px" class="cellBorder" valign="top" style="padding:5px 5px;">
                                <!--//START TABBED PANNEL : -->
                              
                                <ul id="accountTabs" class="shadetabs" >
                                  <li ><a href="#" class="selected" rel="issuesTab"  > Exams List </a></li>
                                  <li ><a href="#" rel="IssuesSearchTab">Exam Search</a></li>
                                </ul>
                                
                                <%-- <sx:tabbedpanel id="IssuesPanel" cssStyle="width: 845px; height: 500px;padding:5px 5px;" doLayout="true"> --%>
                               <%
                                 if(roleName.equals("Admin")){
                               // if((isManager.equals("1")) || (roleName.equals("Admin"))){ 
                              // if(isManager.equals("1")){
                                %>  
                              <%-- <s:if test="#session.isUserManager==1 || #session.isUserTeamLead==1"> --%>
                                
                                <div  style="border:1px solid gray; width:845px;height: 500px; overflow:auto; margin-bottom: 1em;">
                                    <!--//START TAB : -->
                                   
                                    <div id="issuesTab" class="tabcontent"  >
                                        
                                        <%
                                        
                                        try{
                                            queryString="";
                                            /* Getting DataSource using Service Locator */
                                            connection = ConnectionProvider.getInstance().getConnection();
                                            String practiceAction = "../ecertification/addPractice.action";
                                           // System.out.println("list1");
                                            /* String Variable for storing current position of records in dbgrid*/
                                            strTmp = request.getParameter("txtCurr");
                                            intCurr = 1;
                                            if (strTmp != null)
                                                intCurr = Integer.parseInt(strTmp);
                                            
                                            /* Specifing Shorting Column */
                                            strSortCol = request.getParameter("txtSortCol");
                                            
                                            if (strSortCol == null) strSortCol = "CreatedDate";
                                            
                                            strSortOrd = request.getParameter("txtSortAsc");
                                            if (strSortOrd == null) strSortOrd = "ASC";
                                            String userId = session.getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                                           // out.println(userId); 
                                            String name = (String)request.getSession().getAttribute("query");
                                            // out.println(name);
                                            //out.println(ApplicationConstants.QS_EXAM_LIST);
                                           
                                           // queryString = session.getAttribute(ApplicationConstants.QS_EXAM_LIST).toString();
                                           queryString = session.getAttribute(ApplicationConstants.QS_AUTHORED_EXAM_LIST).toString();
                                            /* Sql query for retrieving resultset from DataBase */
                                          // out.println(queryString);
                                           
                                        
                                        %>
                                        <s:form action="" name="frmDBGrid" theme="simple"> 
                                            <div style="width:840px;"> 
                                                <table cellpadding="0" border="0" cellspacing="0" width="100%">
                                                    <tr>
                                                       <td class="headerText">
                                                            <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                        </td> 
                                                    </tr> 
                                                     <tr align="right">
                                  
                                </tr> 
                                                    <tr>
                                                        <td>
                                                              
                                                                <grd:dbgrid id="tblLKSubTopics" name="tblLKSubTopics" width="100" pageSize="17" 
                                                                            currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                            dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                    <grd:gridpager imgFirst="../includes/images/DBGrid/First.gif" imgPrevious="../includes/images/DBGrid/Previous.gif" 
                                                                                   imgNext="../includes/images/DBGrid/Next.gif" imgLast="../includes/images/DBGrid/Last.gif"
                                                                                   addImage="../includes/images/DBGrid/Add.png"  addAction="<%=practiceAction%>" />
                                                                    <grd:rownumcolumn headerText="SNo" width="2" HAlign="center"/> 
                                                                    <grd:imagecolumn  headerText="Practice Edit" width="3"  HAlign="center" 
                                                                              imageSrc="../includes/images/DBGrid/Edit.gif" 
                                                                              linkUrl="getPractice.action?topicId={TopicId}" 
                                                                              imageBorder="0" imageWidth="12" imageHeight="16" 
                                                                              alterText="Click to edit" />
                                                                    <grd:textcolumn dataField="DomainName"  headerText="Practice Name"   width="10" sortable="true"/> 
                                                                    <grd:textcolumn dataField="TopicName"  headerText="Topic Name"   width="10" sortable="true"/> 
                                                                  <grd:imagecolumn  headerText="Add Author(s)/SubTopic(s)" width="4"  HAlign="center"  
                                                                          imageSrc="../includes/images/go_21x21.gif" linkUrl="viewAuthorsAndSubtopics.action?topicId={TopicId}"
                                                                          imageBorder="0" imageWidth="18" imageHeight="15" alterText="Click to Add" />
                                                                    <grd:imagecolumn  headerText="Question(s)" width="5"  HAlign="center"  
                                                                          imageSrc="../includes/images/go_21x21.gif" linkUrl="searchQuestions.action?topicId={TopicId}&list=1&sflag=1"
                                                                          imageBorder="0" imageWidth="18" imageHeight="15" alterText="Click to Add" />
                                                                    </grd:dbgrid>
                                                            <!-- these components are DBGrid Specific  -->
                                                            <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                            <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                            <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                                                            <s:hidden name="domainName" value="%{domainName}"/>
                                                             <s:hidden name="topicName" value="%{topicName}"/>
                                                        </td>
                                                    </tr>
                                                </table>    
                                            </div>
                                        </s:form>  
                                        <%
                                       // connection.close();
                                       // connection = null;
                                        /* For Exception handling mechanism */
                                        }catch(Exception se){
                                            out.println(se.toString());
                                            System.out.println("Exception in ExamList "+se);
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
                                 <%--   <%}%> --%>
                                    <!--//END TAB : -->
                                  
                                    <%--  <sx:div id="IssuesSearchTab" label="Issues Search" > --%>
                                    <div id="IssuesSearchTab" class="tabcontent"  >   
                                        <s:form name="frmSearch" action="serachExamList" theme="simple" method="post" onsubmit="return (validateDates() && compareDates(document.getElementById('startDate').value,document.getElementById('endDate').value));">
                                           <!--The components of searching issues -->
                                            <table border="0" cellpadding="1" cellspacing="1" width="100%">
                                                <tr>
                                                    <td class="headerText">
                                                        <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                        <br>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="center"> 
                                                        <table cellpadding="1" cellspacing="1" border="0">
                                                       <tr>
                                                        <td class="fieldLabel" width="30%">PracticeName:</td>
                                                        <td width="20%"><s:textfield name="domainName" id="domainName"  cssClass="inputTextBlue"  value="%{domainName}"  theme="simple"/></td>
                                                        <td class="fieldLabel">TopicName:</td>
                                                        <td width="20%"><s:textfield name="topicName" id="topicName"  cssClass="inputTextBlue"  value="%{topicName}"  theme="simple"/></td>
                                                       <td align="">
                                                            <s:submit cssClass="buttonBg" value="Search" />
                                                           
                                                        </td>
                                                       </tr> 
                                                         <tr>
                                                        <td></td><td></td><td></td>
                                                        
                                                        </tr>

                                                        </tr>
                                                        </table>  
                                                    </td>
                                                </tr> 
                                                
                                            </table>
                                            
                                        </s:form>
                                        <!-- this script for After loading Form it will instantiate Calender Objects as you require -->
                                     
                                    </div>
                                    
                                    <!--//END TAB : -->
                                    <%--  </sx:tabbedpanel> --%>
                                </div>
                               <%
                                 }else if(isManager.equals("1")){
                               
                              // out.println("roleName--->"+roleName+"========isManager--------->"+isManager);
                                %>  
                              <%-- </s:if><s:else> --%>
                                
                                        <div  style="border:1px solid gray; width:845px;height: 500px; overflow:auto; margin-bottom: 1em;">
                                    <!--//START TAB : -->
                                   
                                    <div id="issuesTab" class="tabcontent"  >
                                        
                                        <%
                                        
                                        try{
                                            queryString="";
                                            /* Getting DataSource using Service Locator */
                                            connection = ConnectionProvider.getInstance().getConnection();
                                            
                                           // System.out.println("list1");
                                            /* String Variable for storing current position of records in dbgrid*/
                                            strTmp = request.getParameter("txtCurr");
                                            intCurr = 1;
                                            if (strTmp != null)
                                                intCurr = Integer.parseInt(strTmp);
                                            
                                            /* Specifing Shorting Column */
                                            strSortCol = request.getParameter("txtSortCol");
                                            
                                            if (strSortCol == null) strSortCol = "CreatedDate";
                                            
                                            strSortOrd = request.getParameter("txtSortAsc");
                                            if (strSortOrd == null) strSortOrd = "ASC";
                                            String userId = session.getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                                           // out.println(userId); 
                                            String name = (String)request.getSession().getAttribute("query");
                                            // out.println(name);
                                            //out.println(ApplicationConstants.QS_EXAM_LIST);
                                           
                                            queryString = session.getAttribute(ApplicationConstants.QS_AUTHORED_EXAM_LIST).toString();
                                           
                                            /* Sql query for retrieving resultset from DataBase */
                                         //  out.println(queryString);
                                           
                                        
                                        %>
                                        <s:form action="" name="frmDBGrid" theme="simple"> 
                                            <div style="width:840px;">  
                                                <table cellpadding="0" border="0" cellspacing="0" width="100%">
                                                    <tr>
                                                       <td class="headerText">
                                                            <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                        </td> 
                                                    </tr> 
                                                    
                                                    <tr>
                                                        <td>
                                                              
                                                                <grd:dbgrid id="tblLKSubTopics" name="tblLKSubTopics" width="100" pageSize="19" 
                                                                            currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                            dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                    <grd:gridpager imgFirst="../includes/images/DBGrid/First.gif" imgPrevious="../includes/images/DBGrid/Previous.gif" 
                                                                                   imgNext="../includes/images/DBGrid/Next.gif" imgLast="../includes/images/DBGrid/Last.gif" />
                                                                    <grd:rownumcolumn headerText="SNO" width="2" HAlign="center"/>  
                                                                    <grd:imagecolumn  headerText="Practice Edit" width="3"  HAlign="center" 
                                                                              imageSrc="../includes/images/DBGrid/Edit.gif" 
                                                                              linkUrl="getPractice.action?topicId={TopicId}" 
                                                                              imageBorder="0" imageWidth="12" imageHeight="16" 
                                                                              alterText="Click to edit" />
                                                                    <grd:textcolumn dataField="DomainName"  headerText="Practice Name"   width="10" sortable="true"/> 
                                                                    <grd:textcolumn dataField="TopicName"  headerText="Topic Name"   width="10" sortable="true"/>
                                                                 <grd:imagecolumn  headerText="Add Author(s)/SubTopic(s)" width="4"  HAlign="center"  
                                                                          imageSrc="../includes/images/go_21x21.gif" linkUrl="viewAuthorsAndSubtopics.action?topicId={TopicId}"
                                                                          imageBorder="0" imageWidth="18" imageHeight="15" alterText="Click to Add" />
                                                                  <%--  <grd:imagecolumn  headerText="Add Question(s)" width="5"  HAlign="center"  
                                                                          imageSrc="../includes/images/go_21x21.gif" linkUrl="addQuestions.action?topicId={TopicId}"
                                                                          imageBorder="0" imageWidth="18" imageHeight="15" alterText="Click to Add" /> --%>
                                                                    <grd:imagecolumn  headerText="Question(s)" width="5"  HAlign="center"  
                                                                          imageSrc="../includes/images/go_21x21.gif" linkUrl="searchQuestions.action?topicId={TopicId}&list=1&sflag=0"
                                                                          imageBorder="0" imageWidth="18" imageHeight="15" alterText="Click to Add" />
                                                                    </grd:dbgrid>
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
                                       // connection.close();
                                       // connection = null;
                                        /* For Exception handling mechanism */
                                        }catch(Exception se){
                                            out.println(se.toString());
                                            System.out.println("Exception in ExamList "+se);
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
                                 <%--   <%}%> --%>
                                    <!--//END TAB : -->
                                  
                                    <%--  <sx:div id="IssuesSearchTab" label="Issues Search" > --%>
                                    <div id="IssuesSearchTab" class="tabcontent"  >   
                                        <s:form name="frmSearch" action="searchMyAuthoredExamList" theme="simple" method="post" onsubmit="return (validateDates() && compareDates(document.getElementById('startDate').value,document.getElementById('endDate').value));">
                                           <!--The components of searching issues -->
                                            <table border="0" cellpadding="1" cellspacing="1" width="100%">
                                                <tr>
                                                    <td class="headerText">
                                                        <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                        <br>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="center"> 
                                                        <table cellpadding="1" cellspacing="1" border="0">
                                                       <tr>
                                                       
                                                        <td class="fieldLabel">TopicName:</td>
                                                        <td width="20%"><s:textfield name="topicName" id="topicName"  cssClass="inputTextBlue"  value="%{topicName}"  theme="simple"/></td>
                                                      
                                                        <td></td><td></td><td></td>
                                                        <td align="">
                                                            <s:submit cssClass="buttonBg" value="Search" />
                                                        </td>
                                                        </tr>

                                                        </tr>
                                                        </table>  
                                                    </td>
                                                </tr> 
                                                
                                            </table>
                                            
                                        </s:form>
                                        <!-- this script for After loading Form it will instantiate Calender Objects as you require -->
                                       
                                    </div>
                                    
                                    <!--//END TAB : -->
                                    <%--  </sx:tabbedpanel> --%>
                                </div>
                                
                                
                                
                                
                                
                              
                                <%-- </s:else> --%> 
                                
                                
                          <%--    <%}%>  --%>
                              
                              
                              
                               <%
                                 }else{
                               
                              // out.println("roleName--->"+roleName+"========isManager--------->"+isManager);
                                %>  
                              <%-- </s:if><s:else> --%>
                                
                                        <div  style="border:1px solid gray; width:845px;height: 500px; overflow:auto; margin-bottom: 1em;">
                                    <!--//START TAB : -->
                                   
                                    <div id="issuesTab" class="tabcontent"  >
                                        
                                        <%
                                        
                                        try{
                                            queryString="";
                                            /* Getting DataSource using Service Locator */
                                            connection = ConnectionProvider.getInstance().getConnection();
                                            
                                           // System.out.println("list1");
                                            /* String Variable for storing current position of records in dbgrid*/
                                            strTmp = request.getParameter("txtCurr");
                                            intCurr = 1;
                                            if (strTmp != null)
                                                intCurr = Integer.parseInt(strTmp);
                                            
                                            /* Specifing Shorting Column */
                                            strSortCol = request.getParameter("txtSortCol");
                                            
                                            if (strSortCol == null) strSortCol = "CreatedDate";
                                            
                                            strSortOrd = request.getParameter("txtSortAsc");
                                            if (strSortOrd == null) strSortOrd = "ASC";
                                            String userId = session.getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                                           // out.println(userId); 
                                            String name = (String)request.getSession().getAttribute("query");
                                            // out.println(name);
                                            //out.println(ApplicationConstants.QS_EXAM_LIST);
                                           
                                            queryString = session.getAttribute(ApplicationConstants.QS_AUTHORED_EXAM_LIST).toString();
                                           
                                            /* Sql query for retrieving resultset from DataBase */
                                         //  out.println(queryString);
                                           
                                        
                                        %>
                                        <s:form action="" name="frmDBGrid" theme="simple"> 
                                            <div style="width:840px;">  
                                                <table cellpadding="0" border="0" cellspacing="0" width="100%">
                                                    <tr>
                                                       <td class="headerText">
                                                            <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                        </td> 
                                                    </tr> 
                                                    
                                                    <tr>
                                                        <td>
                                                              
                                                                <grd:dbgrid id="tblLKSubTopics" name="tblLKSubTopics" width="100" pageSize="19" 
                                                                            currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                            dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                    <grd:gridpager imgFirst="../includes/images/DBGrid/First.gif" imgPrevious="../includes/images/DBGrid/Previous.gif" 
                                                                                   imgNext="../includes/images/DBGrid/Next.gif" imgLast="../includes/images/DBGrid/Last.gif" />
                                                                    <grd:rownumcolumn headerText="SNO" width="2" HAlign="center"/>                 
                                                                    <grd:textcolumn dataField="DomainName"  headerText="Practice Name"   width="10" sortable="true"/> 
                                                                    <grd:textcolumn dataField="TopicName"  headerText="Topic Name"   width="10" sortable="true"/>
                                                                
                                                                  <%--  <grd:imagecolumn  headerText="Add Question(s)" width="5"  HAlign="center"  
                                                                          imageSrc="../includes/images/go_21x21.gif" linkUrl="addQuestions.action?topicId={TopicId}"
                                                                          imageBorder="0" imageWidth="18" imageHeight="15" alterText="Click to Add" /> --%>
                                                                    <grd:imagecolumn  headerText="Question(s)" width="5"  HAlign="center"  
                                                                          imageSrc="../includes/images/go_21x21.gif" linkUrl="searchQuestions.action?topicId={TopicId}&list=1&sflag=0"
                                                                          imageBorder="0" imageWidth="18" imageHeight="15" alterText="Click to Add" />
                                                                    </grd:dbgrid>
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
                                       // connection.close();
                                       // connection = null;
                                        /* For Exception handling mechanism */
                                        }catch(Exception se){
                                            out.println(se.toString());
                                            System.out.println("Exception in ExamList "+se);
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
                                 <%--   <%}%> --%>
                                    <!--//END TAB : -->
                                  
                                    <%--  <sx:div id="IssuesSearchTab" label="Issues Search" > --%>
                                    <div id="IssuesSearchTab" class="tabcontent"  >   
                                        <s:form name="frmSearch" action="searchMyAuthoredExamList" theme="simple" method="post" onsubmit="return (validateDates() && compareDates(document.getElementById('startDate').value,document.getElementById('endDate').value));">
                                           <!--The components of searching issues -->
                                            <table border="0" cellpadding="1" cellspacing="1" width="100%">
                                                <tr>
                                                    <td class="headerText">
                                                        <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                        <br>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="center"> 
                                                        <table cellpadding="1" cellspacing="1" border="0">
                                                       <tr>
                                                       
                                                        <td class="fieldLabel">TopicName:</td>
                                                        <td width="20%"><s:textfield name="topicName" id="topicName"  cssClass="inputTextBlue"  value="%{topicName}"  theme="simple"/></td>
                                                        </tr> 
                                                         <tr>
                                                        <td></td><td></td><td></td>
                                                        <td align="">
                                                            <s:submit cssClass="buttonBg" value="Search" />
                                                        </td>
                                                        </tr>

                                                        </tr>
                                                        </table>  
                                                    </td>
                                                </tr> 
                                                
                                            </table>
                                            
                                        </s:form>
                                        <!-- this script for After loading Form it will instantiate Calender Objects as you require -->
                                       
                                    </div>
                                    
                                    <!--//END TAB : -->
                                    <%--  </sx:tabbedpanel> --%>
                                </div>
                                
                                
                                
                                
                                
                              
                                <%-- </s:else> --%> 
                                
                                
                              <%}%> 
                              
                              
                              
                              
                              
                              
                              
                              
                              
                              
                              
                              
                              
                              
                              
                              
                              
                              
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
       <script>
$(document).ready(function() {
   init1('<%=check%>');
     });
</script>	
    </body>
</html>

