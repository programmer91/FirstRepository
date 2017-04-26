 <!-- 
 *******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  Augest 07, 2013, 3:25 PM
 *
 * Author  : Ajay Tummapala<atummapala@miraclesoft.com>
 *
 * File    : SearchQuestionsList.jsp
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
        <title>Hubble Organization Portal :: Search Questions</title>
        
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
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ecertification/ecertificationAjax.js?version=1.0"/>"></script>
         <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ecertification/AuthorsList.js"/>"></script>
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
            

        
        </script>  --%>
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
    <%-- <body class="bodyGeneral" onload="init1('<%=check%>');" oncontextmenu="return false;"> --%>
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
                              
                                
                                <ul id="accountTabs" class="shadetabs" >
                                 
                                  
                                        <li ><a href="#" class="selected" rel="issuesTab"  > Questions List </a></li>
                                         <li ><a href="#" rel="IssuesSearchTab">Questions Search</a></li>
                                      <li ><a href="#" rel="uploadQuestionsTab">Upload Questions</a></li>

                                    
                                </ul>
                                
                                <%-- <sx:tabbedpanel id="IssuesPanel" cssStyle="width: 845px; height: 500px;padding:5px 5px;" doLayout="true"> --%>
                                <div  style="border:1px solid gray; width:845px;height: 500px; overflow:auto; margin-bottom: 1em;">
                                    <!--//START TAB : -->
                                   
                                    
                                    <div id="issuesTab" class="tabcontent"  >
                                        
                                        <%
                                        
                                        try{
                                            queryString="";
                                            /* Getting DataSource using Service Locator */
                                            connection = ConnectionProvider.getInstance().getConnection();
                                            
                                            int topicId=0;
                                          //  System.out.println("request.getAttribute('topicId')----->"+request.getAttribute("topicId"));
                                           // if(request.getAttribute("topicId")!=null)
                                                    topicId = (Integer)request.getAttribute("topicId");
                                           // System.out.println("topic Id --->"+topicId);
                                          //  out.println("topicId---->"+topicId);
                                            String addQuestionsAction = "../ecertification/addQuestions.action?topicId="+topicId;
                                          //  out.println("add Action --->"+addQuestionsAction);
                                         //  System.out.println("list1");
                                            /* String Variable for storing current position of records in dbgrid*/
                                            strTmp = request.getParameter("txtCurr");
                                           // System.out.println("list2");
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
                                           // String name = (String)request.getSession().getAttribute("query");
                                            // out.println(name);
                                            //out.println(ApplicationConstants.QS_EXAM_LIST)
                                            queryString = session.getAttribute(ApplicationConstants.QS_QUESTION_SEARCH_LIST).toString();
                                             
                                           
                                            /* Sql query for retrieving resultset from DataBase */
                                         //  out.println("query->"+queryString);
                                           
                                        
                                        %>
                                        <s:form action="%{currentAction}" name="frmDBGrid" theme="simple"> 
                                            <div style="width:850px;"> 
                                                <table cellpadding="0" border="0" cellspacing="0" width="100%">
                                                    <tr>
                                                       <td class="headerText">
                                                           <% if(request.getParameter("resultMessage") != null){
                                            out.println(request.getParameter("resultMessage"));
                                            session.removeAttribute("resultMessage");
                                        }%>		
                                                            <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                        </td> 
                                                    </tr> 
                                                     <tr align="right">
                                  
                                </tr> 
                                                    <tr>
                                                        <td>
                                                              
                                                                <grd:dbgrid id="QuestionList" name="QuestionList" width="100" pageSize="18" 
                                                                            currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                            dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                    <grd:gridpager imgFirst="../includes/images/DBGrid/First.gif" imgPrevious="../includes/images/DBGrid/Previous.gif" 
                                                                                   imgNext="../includes/images/DBGrid/Next.gif" imgLast="../includes/images/DBGrid/Last.gif" 
                                                                                   addImage="../includes/images/DBGrid/Add.png"  addAction="<%=addQuestionsAction%>"
                                                                                   />
                                                                    <grd:rownumcolumn headerText="SNo" width="2" HAlign="center"/>     
                                                                     <grd:imagecolumn  headerText="Question Edit" width="5"  HAlign="center" 
                                                                              imageSrc="../includes/images/DBGrid/Edit.gif" 
                                                                              linkUrl="populateQuestionDetails.action?id={Id}&topicId={TopicId}" 
                                                                              imageBorder="0" imageWidth="12" imageHeight="16" 
                                                                              alterText="Click to edit" /> 
                                                                   <%-- <grd:textcolumn dataField="Question"  headerText="Question"   width="10" sortable="true"/> --%>
                                                                  <grd:anchorcolumn dataField="Explanation" linkUrl="javascript:getQuestionPopup('{Id}')" headerText="Description"
                                                                                      linkText="Click To View"  width="10" />
                                                                                      <grd:textcolumn dataField="CreatedBy"          headerText="CreatedBy" width="10"/>
                                                                    <%--   <grd:datecolumn dataField="DateCreated" headerText="Date Created" dataFormat="MM-dd-yyyy" width="10"/> --%>
                                                                    <grd:datecolumn dataField="CreatedDate" headerText="CreatedDate" dataFormat="MM-dd-yyyy" width="10"/>
                                                                    <grd:imagecolumn  headerText="Delete" width="4"  HAlign="center"  
                                                                          imageSrc="../includes/images/DBGrid/Delete.png" linkUrl="javascript:deleteQuestion('{Id}','{TopicId}')" 
                                                                          imageBorder="0" imageWidth="51" imageHeight="20" alterText="Delete" />
                                                                   <%-- <grd:imagecolumn  headerText="Inactive Question(s)" width="4"  HAlign="center"  
                                                                          imageSrc="../includes/images/DBGrid/inactive1.png" linkUrl="deleteQuestion.action?questionId={Id}&topicId={TopicId}"
                                                                          imageBorder="0" imageWidth="51" imageHeight="20" alterText="Delete" />
                                                                   <%-- <grd:imagecolumn  headerText="Add Question(s)" width="5"  HAlign="center"  
                                                                          imageSrc="../includes/images/go_21x21.gif" linkUrl="addQuestions.action?topicId={TopicId}"
                                                                          imageBorder="0" imageWidth="18" imageHeight="15" alterText="Click to Add" />
                                                                     <grd:imagecolumn  headerText="Search Question(s)" width="5"  HAlign="center"  
                                                                          imageSrc="../includes/images/go_21x21.gif" linkUrl="searchQuestions.action?topicId={TopicId}"
                                                                          imageBorder="0" imageWidth="18" imageHeight="15" alterText="Click to Add" />
                                                                        --%>  
                                                                          
                                                                    </grd:dbgrid>
                                                            <!-- these components are DBGrid Specific  -->
                                                            <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                            <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                            <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                                                            
                                                            <s:hidden  name="question" value="%{question}"/>
                                                            <s:hidden  name="topicId" value="%{topicId}"/>
                                                            <s:hidden  name="authorLoginId" value="%{authorLoginId}"/>
                                                            <s:hidden  name="subTopicId" value="%{subTopicId}"/>
                                                            <%
                                                                if(request.getAttribute("question")!=null){
                                                                String question = request.getAttribute("question").toString();
                                                                session.setAttribute("question",question);
                                                                }
                                                            %>
                                                        </td>
                                                    </tr>
                                                </table>    
                                            </div>
                                        </s:form>  
                                        <%
                                            connection.close();
                                            connection = null;
                                            }catch(Exception ex){
                                            ex.printStackTrace();
                                            }finally{
                                            if(connection!= null){
                                            connection.close();
                                            connection = null;
                                            }
                                            }
                                            %>
                                        <%--  </sx:div > --%>
                                    </div>
                               
                                    <!--//END TAB : -->
                                  
                                    <%--  <sx:div id="IssuesSearchTab" label="Issues Search" > --%>
                                    <div id="IssuesSearchTab" class="tabcontent"  >   
                                        <s:form name="frmSearch" action="searchQuestionList" theme="simple" method="post" onsubmit="return (validateDates() && compareDates(document.getElementById('startDate').value,document.getElementById('endDate').value));">
                                           <!--The components of searching issues -->
                                           <s:hidden name="topicId" value="%{topicId}"/>
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
                                                        <td class="fieldLabel" width="30%">Question :</td>
                                                        <td width="20%"><s:textfield name="question" id="question"  cssClass="inputTextBlueLarge"  value="%{question}"  theme="simple"/></td>
                                                       <%-- <td class="fieldLabel">TopicName:</td>
                                                        <td width="20%"><s:textfield name="topicName" id="topicName"  cssClass="inputTextBlue"  value=""  theme="simple"/></td>
                                                        --%>
                                                        <td class="fieldLabel">CreatedBy :</td>
                                                       <td><s:select
                                                                      name="authorLoginId" id="authorLoginId"
                                                                      headerKey="" 
                                                                      headerValue="-- Please Select --"
                                                                       tabindex="7"
                                                                  list="authorList"  cssClass="inputSelect"/></td>

                                                        </tr> 
                                                         <tr>
                                                       <td class="fieldLabel">Subtopic :</td>
                                                             <td><s:select label="Select Subtopic" id="subTopicId" 
                                                                       name="subTopicId" headerKey=""            
                                                                       headerValue="-- Please Select --"
                                                                       list="subTopicMap" cssClass="inputSelect"  value="%{subTopicId}"/></td>
                                                             
                                                             <td></td>
                                                        <td align="">
                                                            <s:submit cssClass="buttonBg" value="Search" />
                                                        </td>
                                                        </tr>

                                                        
                                                        </table>  
                                                    </td>
                                                </tr> 
                                                
                                            </table>
                                            
                                        </s:form>
                                        <!-- this script for After loading Form it will instantiate Calender Objects as you require -->
                                        <%--<script type="text/JavaScript">
                                                           var cal1 = new CalendarTime(document.forms['frmSearch'].elements['startDate']);
                                                           cal1.year_scroll = true;
                                                           cal1.time_comp = false;
                                            
                                                           var cal2 = new CalendarTime(document.forms['frmSearch'].elements['endDate']);
                                                           cal2.year_scroll = true;
                                                           cal2.time_comp = false;
                                        </script>--%>
                                        
                                        <%--  </sx:div> --%>
                                    </div>
                                    
                                    <!--//END TAB : -->
                                    
                                     <!-- Start Upload Questions Tab -->
                                    <div id="uploadQuestionsTab" class="tabcontent" >   
                                        <s:form name="frmSearch" action="" theme="simple" method="post" >
                                           <!--The components of searching issues -->
                                           <s:hidden name="topicId" id="topicId" value="%{topicId}"/>
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
                                                                
                                                            <div align="left" id="resultMessage"></div>
                                                               
                                                            </tr>
                                                       <tr>
                                                        
                                                                <%--td class="fieldLabel">Practice :</td>
                                                                <td><s:select label="Select Domain" id="domainIdReport" 
                                                                       name="domainId" headerKey="-1"            
                                                                       headerValue="-- Please Select --"
                                                                       list="domainMap" cssClass="inputSelect" value="%{domainId}" onchange="getTopicDataReport();"/></td>
                                                               
                                                                <td class="fieldLabel">Topic :</td>
                                                                <td><s:select label="Select Domain" id="topicIdReport" 
                                                                       name="topicId" headerKey="-1"            
                                                                       headerValue="-- Please Select --"
                                                                       list="topicsMap" cssClass="inputSelect" value="%{topicId}" onchange="getSubTopicByTopic();" /></td --%>
                                                                
                                                                <td class="fieldLabel">Subtopic :</td>
                                                                <td><s:select label="Select Subtopic" id="subTopicIdUpload" 
                                                                       name="subTopicId" headerKey=""            
                                                                       headerValue="-- Please Select --"
                                                                       list="subTopicMap" cssClass="inputSelect"  value="%{subTopicId}"/></td>
                                                             
                                                             
                                                       
                                                       
                                                            <td class="fieldLabel">Questions&nbsp;Upload&nbsp;:</td>
                                                            <td colspan="">
                                                                <input type="file" id="file" name="file"  accept=".xls,.xlsx" class="browseButtonStyle" onchange="putFileName();"/> 
                                                            </td>
                                                            
                                                       
                                                            <td>
                                                       <input type="button" value="Upload" onclick="return ajaxQuestionsFileUpload();" class="buttonBg" id="addButton"/> 
                                                       </td> 
                                                       </tr>
                                                       <tr>
                                <td colspan="3"><br><br>
                        <table>
                         <tr><td class="fieldLabelLeft">To download Sample Excel template <a href="getSampleQuestionExcel.action"><b>Click here</b></a></td></tr>
                        </table>
                                    
                                </td>
                                </tr>

                                                        
                                                        </table>  
                                                    </td>
                                                </tr> 
                                                
                                            </table>
                                            
                                        </s:form>
                                       
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
        
        
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/jquery.min.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/jquery.js"/>"></script>   
        <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/ajaxfileupload.js"/>"></script>  
      <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ecertification/ecertificationAjax.js"/>"></script>
        
        <script>
$(document).ready(function() {
   init1('<%=check%>');
     });
</script>	
    </body>
</html>


