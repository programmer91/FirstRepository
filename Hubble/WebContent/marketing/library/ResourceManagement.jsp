<%-- 
    Document   : ResourceManagement
    Created on : Jul 30, 2015, 3:26:59 PM
    Author     : miracle
--%>


<%@page import="com.mss.mirage.util.Properties"%>
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
        <title>Hubble Organization Portal :: Digital&nbsp;Library&nbsp;Management</title>
       
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
       
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
     
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
 
      <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>   
   
  
      <script type="text/JavaScript" src='<s:url value="/includes/javascripts/GridNavigation.js"/>'></script>
   
      <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/jquery.min.js"/>"></script>
    <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/jquery.js"/>"></script>   
       
   <script type="text/JavaScript" src="<s:url value="/includes/javascripts/marketing/ResourceManagement.js"/>"></script>  
        <script>
       function getQmeetAttendiesExcelSheet(){
           
          var tableName=document.getElementById('tableName').value;
          
                window.location="websiteDataDownload.action?tableName="+tableName;
           
        }
   </script>
        <s:include value="/includes/template/headerScript.html"/>
        <%-- for issue reminder popup --%>
    </head>
    <%-- <body class="bodyGeneral" oncontextmenu="return false;" onload="getLibraryDetails();"> --%>
    <body class="bodyGeneral" oncontextmenu="return false;">
       <%!
        /* Declarations */
        Connection connection;
        String queryString;
        String strTmp;
        String strSortCol;
        String strSortOrd;
        String submittedFrom;
        String searchSubmitValue;
        int intSortOrd = 0;
        int intCurr;
        boolean blnSortAsc = true;
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
                                       
                                        <li ><a href="#" class="selected" rel="issuesTab">Digital&nbsp;Library&nbsp;Management</a></li>
                                       
                                 <%--   <%}else{%>
                                    <li ><a href="#" class="selected" rel="IssuesSearchTab">Tasks Search</a></li>
                                     <% }%> --%>
                                    
                                </ul>
                                 
                                
                                <%-- <sx:tabbedpanel id="IssuesPanel" cssStyle="width: 845px; height: 500px;padding:5px 5px;" doLayout="true"> --%>
                                <div  style="border:1px solid gray; width:845px;height: 500px; overflow:auto; margin-bottom: 1em;">
                                
                            <div id="issuesTab" class="tabcontent" >
                                                           <div id="overlay"></div> 
                                                  <div id="specialBox">
                                                      <s:form theme="simple"  align="center" name="frmResource">
                                                           
                                                          
                                                           
                                                           <table align="center" border="0" cellspacing="0" style="width:100%;">
                                                                  <tr>                               
                                                    <td colspan="2" style="background-color: #288AD1" >
                                                        <h3 style="color:darkblue;" align="left">
                                                            <span id="headerLabel"></span>


                                                        </h3></td>
                                                    <td colspan="2" style="background-color: #288AD1" align="right">

                                                        <a href="#" onmousedown="closeToggleOverlay()" >
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
                                                     <td class="fieldLabel" >Resource&nbsp;Title:<FONT color="red"  ><em>*</em></FONT> </td>
                                                     <td colspan="3"><s:textfield name="resourceTitle" id="resourceTitle" cssClass="inputTextBlueLargeAccount" /></td>
                                                       
                                                        <%--<td class="fieldLabel" >EmailId:<FONT color="red"  ><em>*</em></FONT> </td>
                                                     <td><s:textfield name="emailId" id="emailId" cssClass="inputTextBlue" /></td>
                                                     
                                                     <td class="fieldLabel" >WorkPhone:<FONT color="red"  ><em>*</em></FONT> </td>
                                                     <td ><s:textfield name="workPhone" id="workPhone" cssClass="inputTextBlue" /></td> --%>
                                                       </tr>   
                                                               <tr>
                                                           
                                                            <td class="fieldLabel">Date&nbsp;Of&nbsp;Publish:</td>
                                                    <td><s:textfield name="dateOfPublish" id="dateOfPublish" cssClass="inputTextBlue" onchange="validateTimestamp(this);"/>
                                                      <a href="javascript:cal3.popup();">
                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif"
                                                             width="20" height="20" border="0"></a>
                                                        </td>
                                                       
                                                         </tr>  
                                                       <tr>
                                                           <td class="fieldLabel">Type :</td>
                                    <td ><s:select id="resourceType" name="resourceType" list="{'Case Study','Technical Article','Product Brief','Solution Brief','Presentation'}" cssClass="inputSelect" headerKey="" headerValue="--Please Select--"/></td>
                                     <td class="fieldLabel">Do&nbsp;you&nbsp;want&nbsp;to&nbsp;gate&nbsp;the&nbsp;content</td>
                                                        <td ><s:select id="gateContent" name="gateContent" list="{'YES','NO'}" cssClass="inputSelect" /></td>
                                                       </tr>
                                                       
                                                         <tr>
                                    <td class="fieldLabel">Primary&nbsp;Track:</td>
                                    <td ><s:select id="resourcePrimaryTrack" name="resourcePrimaryTrack" list="{'Cloud-Services','SOA and Connectivity','B2B Integration and File Transfer','SAP/ERP Services','Application Life Cycle Management','Business Process Management','Quality Assurance and Testing','Digital Experience and Commerce'}" cssClass="inputSelect" headerKey="" headerValue="--Please Select--"/></td>
                                    <td class="fieldLabel">Secondary&nbsp;Track:</td>
                                    <td ><s:select id="resourceSecondaryTrack" name="resourceSecondaryTrack" list="{'Cloud-Services','SOA and Connectivity','B2B Integration and File Transfer','SAP/ERP Services','Application Life Cycle Management','Business Process Management','Quality Assurance and Testing','Digital Experience and Commerce'}" cssClass="inputSelect" headerKey="" headerValue="--Please Select--"/></td>
                                   
                                                         </tr>   
                                                         <tr>
                                                              <td class="fieldLabel">Industry:</td>
                                    <td ><s:select id="resourceIndustry" name="resourceIndustry" list="{'Retail','Manufacturing','Health Care','Logistics','Finance and Insurance','Application Development','Cross Industry'}" cssClass="inputSelect" headerKey="" headerValue="--Please Select--"/></td>
                                    
                                                         </tr>
                                                         
                                                          <tr id="resourceDescriptionTr">
                                                        <td class="fieldLabel" valign="top">Resource Description:<FONT color="red"  ><em>*</em></FONT></td>
                                                     <td colspan="3" valign="top"><s:textarea rows="5" cols="65" name="resourceDescription" cssClass="inputTextareaOverlay1" onchange="checkDoubleQuotes(this);return fieldLengthValidator2(this);" id="resourceDescription" />
                                                        
                                                         <font class="fieldLabel">
                                                             <br> Please include p tag to every paragraph like &lt;p class='methodText'&gt;...&lt;/p&gt;...&lt;p class='methodText'&gt;...&lt;/p&gt;
                                                         </font>
                                                     </td>
                                                            </tr>
                                                          <tr>
                                                     <td class="fieldLabel" >Bread&nbsp;Crumb&nbsp;Heading:<FONT color="red"  ><em>*</em></FONT> </td>
                                                     <td colspan="3"><s:textfield name="breadCrumbHeading" id="breadCrumbHeading" cssClass="inputTextBlueLargeAccount" /></td>
                                                       </tr>   
                                                         <tr id="resourceDescriptionTr">
                                                        <td class="fieldLabel" valign="top">WebPage&nbsp;Creation&nbsp;Details:<FONT color="red"  ><em>*</em></FONT></td>
                                                     <td colspan="3" valign="top"><s:textarea rows="5" cols="65" name="webPageCreationdetails" cssClass="inputTextareaOverlay1" onchange="checkDoubleQuotes(this);return fieldLengthValidator2(this);" id="webPageCreationdetails" />
                                                        
                                                         <font class="fieldLabel">
                                                             <br> Please include p tag to every paragraph like &lt;p class='methodText'&gt;...&lt;/p&gt;...&lt;p class='methodText'&gt;...&lt;/p&gt;
                                                         </font>
                                                     </td>
                                                            </tr>
                                                             <tr>
                                                              <td class="fieldLabel">Does this resource have downloadable content?</td>
                                                              <td ><s:checkbox name="isResourceDownloadable" id="isResourceDownloadable" /></td>
                                    
                                                         </tr>
                                                        <tr id="uploadTr"> 

                                                    <td class="fieldLabel">Upload File :</td>
                                                    <td colspan="2" ><s:file name="file" label="file" cssClass="inputTextarea" id="file" onchange="putFileName();"/></td> 
                                                        </tr>
                                                               </table>
                                                           </td>
                                                       </tr>
                                                           </table>
                                                       </s:form>    
                                                         </div>
                                     
                                     
                                                           <s:form action="libraryManagementSearch" name="frmDBGrid" id="frmDBGrid" theme="simple" onsubmit="return checkMandatory();"> 
                                            <div style="width:840px;"> 
                                               
                                                <table cellpadding="0" cellspacing="0" align="left" width="100%">
                    
                 <tr>
                        <td>
                            <table border="0" cellpadding="0" cellspacing="2" align="left" width="100%">
                                <tr>
                                    <td class="headerText" colspan="11">
                                        <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                    </td>
                                </tr>
                                <%-- <tr>
                                                                 <td class="fieldLabel" >Search Type<FONT color="red"  ><em>*</em></FONT> </td>
                                                                 <td ><s:select id="tableName"  name="tableName"  list="#@java.util.LinkedHashMap@{'tblContactus':'ContactUs','tblEmpVerfication':'Employee Verification','tblEventAttendies':'QuarterlyMeet','tblResourceDepotDetails':'Resource Depot','tblSuggestions':'Suggestion Box'}" cssClass="inputSelect" headerKey="" headerValue="--Select Type--"/></td>
                                 </tr> --%>
                           
                             <tr>
                                  
                                                     <td class="fieldLabel">Date From:</td>
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
                                    <td class="fieldLabel">Title:</td>
                                    <td > <s:textfield name="searchTitle"  id="searchTitle" cssClass="inputTextBlue" /></td>
                                    
                                     <td class="fieldLabel">File&nbsp;Name:</td>
                                    <td > <s:textfield name="searchFileName"  id="searchFileName" cssClass="inputTextBlue" /></td>
                                    
                                   
                                </tr>
                                <tr>
                                     <td class="fieldLabel">Type :</td>
                                  <%--  <td ><s:select id="searchType" name="searchType" list="{'Case Study','Technical Article','Product Brief','Solution Brief','Presentation'}" cssClass="inputSelect" headerKey="" headerValue="--Please Select--"/></td> --%>
                                    <td ><s:select id="searchType" name="searchType" list="{'Case Study','Technical Article','Solution Brief','Presentation'}" cssClass="inputSelect" headerKey="" headerValue="--Please Select--"/></td>
                                    
                                    <td class="fieldLabel">Track:</td>
                                    <td ><s:select id="searchTrack" name="searchTrack" list="trackNamesMap" cssClass="inputSelect" headerKey="" headerValue="--Please Select--"/></td>
                                  
                                </tr>
                                <tr>
                                      <td class="fieldLabel">Industry:</td>
                                  <%--  <td ><s:select id="searchIndustry" name="searchIndustry" list="{'Retail','Manufacturing','Health Care','Logistics','Finance and Insurance','Application Development'}" cssClass="inputSelect" headerKey="" headerValue="--Please Select--"/></td> --%>
                                    <td ><s:select id="searchIndustry" name="searchIndustry" list="{'Retail, Logistics and Supply Chain','Manufacturing and Automotive','Healthcare and Pharmacy','Finance, Banking and Insurance','Energy and Utilities','Cross Industry'}" cssClass="inputSelect" headerKey="" headerValue="--Please Select--"/></td>
                                </tr>
                                
                                <tr>
                                    
                                    <td></td>
                                    <td colspan="2"></td>
                                    <td>
                                        <input type="button" class="buttonBg"  align="right"  value="Add" onclick="addLibrary();" />
                                        <s:submit name="search" value="Search" cssClass="buttonBg"/>
                                    </td>
                                </tr>
                                
                                <s:if test="#session.libraryManagementlist != null"> 
                    <tr>
                        <td colspan="4">
                            <br>
                          <table id="results" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='90%' align="center">
                               
                              <%java.util.List mainList = (java.util.List) session.getAttribute("libraryManagementlist");
                                if(mainList.size()>0){


%>
                              
                             <tr class="gridHeader">
                                                       <th>Edit</th>
                                                        <th>DocType</th>
                                                       <!-- <th>EventDesc</th> -->
                                                       <th>DocTitle</th>
                                                       <th>FileName</th>
                                                       <th>Industry</th>
                                                       
                                                     <%--   <th>TrackName</th> --%>
                                                         <th>DateofPublish</th>
                                                         <th>Authors</th>
                                                       <th>Page&nbsp;View</th>
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
                       <td>
                           <%
                           if(subList.get("LibraryFlag").toString().equals("0")){
                        %>
                       <a style="color:#C00067;" href="editLibrary.action?libraryId=<%=subList.get("Id")%>">
                         <img src="../../includes/images/DBGrid/Edit.gif"/>
                        </a>
                         <%}%>
                     </td>
                     <td class="title">
                           <%
                          out.println(subList.get("DocType").toString());
                         %>
                     </td>
                     <td class="title">
                          <%
                          out.println(subList.get("DocTitle").toString());
                         %>
                     </td>
                     <td class="title">
                          <%
                          out.println(subList.get("PhpFileName").toString());
                         %>
                     </td>
                     <td class="title">
                          <%
                          out.println(subList.get("Industry").toString());
                         %>
                     </td>
                    <%-- <td class="title">
                          <%
                          out.println(subList.get("TrackName").toString());
                         %>
                     </td> --%>
                     <td class="title">
                          <%
                          out.println(subList.get("DateOfPublish").toString());
                         %>
                     </td>
                     
                      <td class="title">
                            <%
                            if(!subList.get("DocType").toString().equals("Case Study")) {
                                
                            
                           if(subList.get("LibraryFlag").toString().equals("0")){
                       
                            %>
                         <a style="color:#C00067;" href="javascript:getAuthors('<%=subList.get("Id")%>','LB');">
                             <img src="../../includes/images/go_21x21.gif"/>
                        </a>
                            <%} }%>  
                     </td>
                    
                     
                      <td>
                         <%
                        String url= Properties.getProperty("Emp.Library.Path");
                         %>
                        <a style="color:#C00067;" href="<%=url%><%=subList.get("SourceLink")%>" target="_blank">
                             
                             
                            <img src="../../includes/images/DBGrid/View.gif" width="23" height="12"/>
                        </a>
                     </td>


                       
                     
                     
                    
               
                          
                             <% } %>
                    
                   
                     <%
                     
              //   }
                %></tr><% 
                 
             
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
                                    
               
                        </table>
                        </td>
                    </tr>
                    
                 
           
                                               
                                                                                               
<script type="text/JavaScript">
                                             var cal1 = new CalendarTime(document.forms['frmDBGrid'].elements['createdDateFrom']);
				                 cal1.year_scroll = true;
				                 cal1.time_comp = false;

                                             var cal2 = new CalendarTime(document.forms['frmDBGrid'].elements['createdDateTo']);
				                 cal2.year_scroll = true;
				                 cal2.time_comp = false;

						 var cal3 = new CalendarTime(document.forms['frmResource'].elements['dateOfPublish']);
				                 cal3.year_scroll = true;
				                 cal3.time_comp = false;
                                        </script>                                                            
                                        
                          
       <script type="text/javascript">
        var pager = new ReviewPager('results', 10); 
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
		getLibraryDetails();

		});
		</script>
        
        
    </body>
</html>


