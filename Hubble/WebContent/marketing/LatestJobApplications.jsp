<%-- 
    Document   : LatestJobApplications
    Created on : May 28, 2015, 3:13:53 PM
    Author     : miracle
--%>

<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.lang.String"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
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
    <script type="text/javascript" src="<s:url value="/includes/javascripts/searchIssue.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmpStandardClientValidations.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
  
      <script type="text/JavaScript" src='<s:url value="/includes/javascripts/GridNavigation.js"/>'></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmployeeAjax.js"/>"></script>
      <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/jquery.min.js"/>"></script>
    <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/jquery.js"/>"></script>   
         <script type="text/JavaScript" src="<s:url value="/includes/javascripts/StandardClientValidations.js"/>"></script>
           <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/ajaxfileupload.js"/>"></script>  
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/marketing/jobPosting.js"/>"></script>  
        
        <s:include value="/includes/template/headerScript.html"/>
        <%-- for issue reminder popup --%>
        <script type="text/javascript">
           
            
            function getAppliedList(jobId) {
                window.location = "appliedConsultantList.action?jobId="+jobId;
            }
        
        </script>
     
       
       <%
String check = null;

if(request.getAttribute("check")!=null)
  check = request.getAttribute("check").toString();
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
        String currentSearch=null;
        boolean blnSortAsc = true;
        HttpServletRequest httpServletRequest;
        %>
        
 <%String contextPath = request.getContextPath();
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
                            <td width="850px" class="cellBorder" valign="top" style="padding:5px 5px ;">
                                <!--//START TABBED PANNEL : -->
                                <%
                                    //out.print("naga::"+ request.getAttribute("subnavigateTo").toString());
                                 /// session.setAttribute(ApplicationConstants.ISSUE_TO_TASK,request.getAttribute("subnavigateTo").toString());
                                %>
                                
                                <ul id="accountTabs" class="shadetabs" >
                                 
                                <%--    <% if(request.getParameter("issueList")==null)
                                       {%> --%>
                                       
                                        <li ><a href="#" class="selected" rel="issuesTab"  > Job List </a></li>
                                       
                                 <%--   <%}else{%>
                                    <li ><a href="#" class="selected" rel="IssuesSearchTab">Tasks Search</a></li>
                                     <% }%> --%>
                                    
                                </ul>
                                 
                                
                                <%-- <sx:tabbedpanel id="IssuesPanel" cssStyle="width: 845px; height: 500px;padding:5px 5px;" doLayout="true"> --%>
                                <div  style="border:1px solid gray; width:845px;height: 500px; overflow:auto; margin-bottom: 1em;">
                                
                            <div id="issuesTab" class="tabcontent" >
                                
                                                      <%--     <div id="overlay" ></div> 
                                                  <div id="specialBox" >
                                                       <s:form theme="simple"  align="center" >
                                                           
                                                           <s:hidden name="overlayJobId" id="overlayJobId"/>
                                                         
                                                           <s:hidden name="id" id="id"/>
                                                           <s:hidden name="teamId" id="teamId" value="%{#session.teamName}"/>
                                                           <table align="center" border="0" cellspacing="0" style="width:100%;" >
                                                                  <tr>                               
                                                    <td colspan="2" style="background-color: #288AD1" >
                                                        <h3 style="color:darkblue;" align="left">
                                                            <span id="headerLabel"></span>


                                                        </h3></td>
                                                    <td colspan="2" style="background-color: #288AD1" align="right">

                                                        <a href="#" onmousedown="toggleOverlay()" >
                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> 

                                                        </a>  

                                                    </td></tr>
                                                               <tr>
                                                    <td colspan="4">
                                                        <div id="load" style="color: green;display: none;">Loading..</div>
                                                        <div id="resultMessage"></div>
                                                    </td>
                                                </tr>    
                                                        <tr><td colspan="4">
                                                         <table style="width:80%;" align="center">
                                                                       <tr>


                                                     <td class="fieldLabel" >Job&nbsp;Title&nbsp;:<FONT color="red"  ><em>*</em></FONT> </td>
                                                     <td colspan="3"><s:textfield name="jobtitle" id="jobtitle" cssClass="inputTextBlueLargeAccount" onchange="return fieldLengthValidator2(this);" /></td>
                                                 
                                                       </tr>
                                                             <tr>
                                                   
                                                     <td align="left" class="fieldLabel" >Experience:<FONT color="red"  ><em>*</em></FONT></td>
                                                     <td ><s:textfield name="jobqualification" id="jobqulification" cssClass="inputTextBlue" onchange="return fieldLengthValidator2(this);"/>
                                                       </td>
                                                         <td class="fieldLabel" >Location:<FONT color="red"  ><em>*</em></FONT></td>
                                                      <td>
                                                          
                                                     
                                                     <s:select id="location" name="location" list="locationList" cssClass="inputSelect" headerKey="" headerValue="All"/>
                                                     </tr>
                                                  
                                            
                                                             <tr>
                                                     <td align="left" class="fieldLabel" >Job Department:<FONT color="red"  ><em>*</em></FONT></td>
                                                     <td ><s:textfield name="jobDepartment" id="jobDepartment" cssClass="inputTextBlue" onchange="return fieldLengthValidator2(this);"/>
                                                     </td> 
                                                      <td  class="fieldLabel" >Job&nbsp;Hire&nbsp;Type:<FONT color="red"  ><em>*</em></FONT></td>
                                                    <td ><s:textfield name="jobHireType" id="jobHireType" cssClass="inputTextBlue" onchange="return fieldLengthValidator2(this);"/>
                                                     </td> 
                                                     
                                                    
                                                       </tr>
                                                       <tr>
                                                            <td  class="fieldLabel" >Job Status:<FONT color="red"  ><em>*</em></FONT></td>
                                                    <td ><s:select id="jobstatus"  name="jobstatus"  list="{'Active','InActive'}" cssClass="inputSelect" onchange="return fieldLengthValidator2(this);" disabled="False"/>
                                                     </td>
                                                       </tr>
                                                        
                                                          <tr>
                                                        <td class="fieldLabel" valign="top">Job&nbsp;Description(Paragraph1):<FONT color="red"  ><em>*</em></FONT></td>
                                                     <td colspan="3" valign="top"><s:textarea rows="5" cols="65" name="jobdescription1" cssClass="inputTextareaOverlay1" onchange="return fieldLengthValidator2(this);" id="jobdescription1" /></td>
                                                            </tr>
                                                           <tr>
                                                        <td class="fieldLabel" valign="top">Job&nbsp;Description(Paragraph2):<FONT color="red"  ><em>*</em></FONT></td>
                                                     <td colspan="3" valign="top"><s:textarea rows="5" cols="65" name="jobdescription2" cssClass="inputTextareaOverlay1" onchange="return fieldLengthValidator2(this);" id="jobdescription2" /></td>
                                                            </tr>
                                                  
                                                  
                                                  <tr id="createdTr">
                                                      <td class="fieldLabel" valign="top">CreatedBy:<FONT color="red"  ><em>*</em></FONT> </td>
                                                      <td><FONT color="green"  ><span id="createdBy"></span></FONT></td>
                                                       <td class="fieldLabel" valign="top">CreatedDate:<FONT color="red"  ><em>*</em></FONT> </td>
                                                      <td><FONT color="green"  ><span id="createdDate"></span></FONT></td>
                                                  </tr>
                                                        <tr id="addTr" style="display: none"> 

                                                   
                                                     <td  align="center" >
                                                         <input type="button" value="Save" onclick="return doJobPost();" class="buttonBg" id="addButton"/> 


                                                        
                                                        <s:reset cssClass="buttonBg"  align="right" value="Reset" />
                                                        
                                                    </td>
                                                        </tr> <tr id="editTr" style="display: none"> 
                                                    <td  align="center" >
                                                         <input type="button" value="Update" onclick="return doUpdateJobPost();" class="buttonBg" id="updateButton"/> 
                                                    </td>
                                                </tr>
                                                               </table>
                                                           </td>
                                                       </tr>
                                                           </table>
                                                       </s:form>    
                                                         </div> --%>
                                     
                                     
                                        <s:form action="jobSearch.action" id="frmDBGrid" name="frmDBGrid" theme="simple"> 
                                            <div style="width:840px;"> 
                                                
                                                <table cellpadding="0" cellspacing="0" align="left" width="100%">
                    
                   <%-- <tr>
                        <td>
                            <table border="0" cellpadding="0" cellspacing="2" align="left" width="100%">
                                <tr>
                                    <td class="headerText" colspan="11">
                                        <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                    </td>
                                </tr>
                               <tr>
                                  
                                                     <td class="fieldLabel">Created Date From:</td>
                                                    <td><s:textfield name="createdDateFrom" id="createdDateFrom" cssClass="inputTextBlue" onchange="validateTimestamp(this);"/>
                                                      <a href="javascript:cal1.popup();">
                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif"
                                                             width="20" height="20" border="0"></a>
                                                        </td>
                                                         <td class="fieldLabel">To:<FONT color="red" SIZE="0.5"><em>*</em></FONT></td>
                                                    <td>
                                                        <s:textfield name="createdDateTo"  id="createdDateTo" cssClass="inputTextBlue" onchange="validateTimestamp(this);"/>
                                                        <a href="javascript:cal2.popup();">
                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif"
                                                             width="20" height="20" border="0"></a>
                                                    </td>
                                                    
                                </tr>
                                <tr>
                                    <td class="fieldLabel">Job&nbsp;Id :</td>
                                    <td>
                                      <s:textfield name="jobId" id="jobId" cssClass="inputTextBlue" theme="simple"/>
                                    </td>
                                  
                                    <td class="fieldLabel">Job&nbsp;Title :</td>
                                    <td>
                                      <s:textfield name="title" id="title" cssClass="inputTextBlue" theme="simple"/>
                                    </td>
                                    
                                </tr>
                                <tr>
                                    <td class="fieldLabel">Status:</td>
                                    <td ><s:select headerKey="" headerValue="--Please Select--" id="status"  name="status"  list="{'Active','InActive'}" cssClass="inputSelect"  disabled="False"/>
                                </tr>
                                <tr>
                                    
                                    <td></td>
                                    <td colspan="2"></td>
                                    <td>
                                        
                                        <s:submit name="search" value="Search" cssClass="buttonBg"/>
                                       
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr> --%>
                                                
                       
           
         
                     <s:if test="#session.latestJobsAppliedlist != null"> 
                    <tr>
                        <td><br><br><br>
                          <table id="results" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='90%' align="center">
                               
                              <%java.util.List mainList = (java.util.List) session.getAttribute("latestJobsAppliedlist");
                                if(mainList.size()>0){


%>
                              
                             <tr class="gridHeader">
                                                        <th>JobId</th>
                                                        <th>JobTitle</th>
                                                        <th>Location</th>
                                                        <th>JobDesc</th>
                                                        <th>Status</th>
                                                        <th>RecentAppliedDate</th>
                                                     <%--   <s:if test="#session.teamName == 'Sourcing'"> --%>
                                                        <s:if test="#session.sourcingFlag == 'YES'">
                                                        <th>Applied</th>
                                                        </s:if>
                                                    </tr>
                          <%
             
             for (int i = 0; i < mainList.size(); i++) {
                 %>
                 <tr CLASS="gridRowEven">
                            <%
                 //java.util.List subList = (java.util.List)mainList.get(i);
                            java.util.Map subList = (java.util.Map)mainList.get(i);
               //  for (int j = 0; j < subList.size(); j++) {
                     
                     %>
                     <td class="title">
                         
                         <%
                       out.println(subList.get("JobId"));%>
                     </td>       
                       <td class="title">
                         <%
                          out.println(subList.get("JobTitle"));

%>
                     </td>    <td class="title">
                         <%
                          out.println(subList.get("Location"));

%>
                     </td>   <td class="title">
                         <%
                       
                         StringEscapeUtils seu = new StringEscapeUtils();
                                  
                         String jobDesc = (String)subList.get("JobDescription");
                      //    out.println(seu.unescapeJavaScript(jobDesc));

%>
<A href="javascript: populateDescriptions( '<%=jobDesc%>');">Click to view</A>
                     </td>     <td class="title">
                         <%
                          out.println(subList.get("JobStatus"));

%>
                     </td>   
                     <td class="title">
                         <%
                          out.println(subList.get("AppliedDate"));

%>
                     </td>   
                  <%--   <s:if test="#session.teamName == 'Sourcing'"> --%>
                   <s:if test="#session.sourcingFlag == 'YES'">
                          <td>
                         <a style="color:#C00067;" href="javascript:getAppliedList('<%=subList.get("JobId")%>');">
                             <img src="../includes/images/go_21x21.gif"/>
                        </a>
                     </td>
                     </s:if>
                    
                     <%
                     
              //   }
                %></tr><% 
                 
             }
                          }else {
             %>
                     <tr><td>
                 <%
                // String contextPath = request.getContextPath();
           // out.println("<img  border='0' align='top'  src='"+contextPath+"/includes/images/alert.gif'/><b> No Records Found to Display!</b>");
                 
                  out.println("<img  border='0' align='top'  src='"+contextPath+"/includes/images/alert.gif'/> <font color='white'><b>No records found!</b></font>");
           // }

            %>
                     </td>
           </tr>
           <%}%>
               
                        </table>
                        </td>
                    </tr>
                    
                    
                    
                     <%
                                         
             if(mainList.size()!=0){
                %>
               
            <tr>
                
                <td align="right" colspan="4" style="background-color:white;" >
                    <div align="right" id="pageNavPosition">hello</div>
             </td>
            </tr> 
        
             <%}
                 %>
                     </s:if>
           
                                               
                                                                
<%-- <script type="text/JavaScript">
                                             var cal1 = new CalendarTime(document.forms['frmDBGrid'].elements['createdDateFrom']);
				                 cal1.year_scroll = true;
				                 cal1.time_comp = true;

                                             var cal2 = new CalendarTime(document.forms['frmDBGrid'].elements['createdDateTo']);
				                 cal2.year_scroll = true;
				                 cal2.time_comp = true;

						

                                        </script>             --%>                
       <script type="text/javascript">
        var pager = new Pager('results', 10); 
        pager.init(); 
        pager.showPageNav('pager', 'pageNavPosition'); 
        pager.showPage(1);
    	</script>      
                                                </table>
                                            </div>    
                           </s:form>  
                                                     
                                 
                                        <%--  </sx:div > --%>
                                    </div>  
                                 <%--   <%}%> --%>
                                    <!--//END TAB : -->
                                  
                                    <%--  <sx:div id="IssuesSearchTab" label="Issues Search" > --%>
                  
                                </div>
                                    <!--//END TAB : -->
                                    <%--  </sx:tabbedpanel> --%>
                                
                                <!--//END TABBED PANNEL : -->
                                <script type="text/javascript">

var countries=new ddtabcontent("accountTabs")
countries.setpersist(false)
countries.setselectedClassTarget("link") //"link" or "linkparent"
countries.init()

 </script>            
                                </td>
                                </tr>
                    </table>
                            </td>
                            <!--//END DATA COLUMN : Coloumn for Screen Content-->
                        </tr>
                   
               
            
            <!--//END DATA RECORD : Record for LeftMenu and Body Content-->
            
            <!--//START FOOTER : Record for Footer Background and Content-->
            <tr class="footerBg">
                <td align="center"><s:include value="/includes/template/Footer.jsp"/>   </td>
            </tr>
         <%--    <tr>
                <td>
                    
                    <div style="display: none; position: absolute; top:170px;left:320px;overflow:auto;" id="menu-popup">
                        <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" />
                    </div>
                    
                </td>
            </tr> --%>

            <!--//END FOOTER : Record for Footer Background and Content-->
            
        </table>
        <!--//END MAIN TABLE : Table for template Structure-->
        
        
        
        
        
    </body>
</html>
