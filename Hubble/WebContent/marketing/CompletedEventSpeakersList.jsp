<%-- 
    Document   : CompletedEventSpeakersList
    Created on : Jul 28, 2015, 4:52:06 PM
    Author     : miracle
--%>

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
       
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
       
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
     
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
 
     
   
  
      <script type="text/JavaScript" src='<s:url value="/includes/javascripts/GridNavigation.js"/>'></script>
   
      <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/jquery.min.js"/>"></script>
    <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/jquery.js"/>"></script>   
       
   <script type="text/JavaScript" src="<s:url value="/includes/javascripts/marketing/EventsPosting.js"/>"></script>  
        
        <s:include value="/includes/template/headerScript.html"/>
        <%-- for issue reminder popup --%>
        <script type="text/javascript">
           
            
            function getAppliedList(jobId) {
                window.location = "appliedConsultantList.action?jobId="+jobId;
            }
            
             function doResumeDownload(Id){
                //alert(path);
                 //window.location ="resumeDownload.action?path="+decodeURIComponent(path);
                 window.location ="resumeDownload.action?jobConsultantId="+Id;
            }
        
        </script>
      
       
  
    </head>
    <body class="bodyGeneral" oncontextmenu="return false;">
       
        
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
                                       
                                        <li ><a href="#" class="selected" rel="issuesTab"  > Event Speakers List </a></li>
                                       
                                 <%--   <%}else{%>
                                    <li ><a href="#" class="selected" rel="IssuesSearchTab">Tasks Search</a></li>
                                     <% }%> --%>
                                    
                                </ul>
                                 
                                
                                <%-- <sx:tabbedpanel id="IssuesPanel" cssStyle="width: 845px; height: 500px;padding:5px 5px;" doLayout="true"> --%>
                                <div  style="border:1px solid gray; width:845px;height: 500px; overflow:auto; margin-bottom: 1em;">
                                
                            <div id="issuesTab" class="tabcontent" >
                                                           <div id="overlay"></div> 
                                                  <div id="specialBox">
                                                       <s:form theme="simple"  align="center" >
                                                           
                                                           
                                                           <s:hidden name="eventId" id="eventId" value="%{eventId}"/>
                                                         <s:hidden name="speakerId" id="speakerId"/>
                                                         <s:hidden name="primarySpeakerExist" id="primarySpeakerExist" value="%{primarySpeakerExist}"/>
                                                           
                                                           <table align="center" border="0" cellspacing="0" style="width:100%;">
                                                                  <tr>                               
                                                    <td colspan="2" style="background-color: #288AD1" >
                                                        <h3 style="color:darkblue;" align="left">
                                                            <span id="headerLabel"></span>


                                                        </h3></td>
                                                    <td colspan="2" style="background-color: #288AD1" align="right">

                                                        <a href="#" onmousedown="closeSpeakerOverlay()" >
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
                                                     <td class="fieldLabel" >Speaker Name:<FONT color="red"  ><em>*</em></FONT> </td>
                                                     <td colspan="3"><s:textfield name="speakerName" id="speakerName" cssClass="inputTextBlueLargeAccount" onchange="return fieldLengthValidator2(this);" /></td>
                                                       
                                                       </tr>
                                                        <tr>
                                                     <td class="fieldLabel" >Designation:<FONT color="red"  ><em>*</em></FONT> </td>
                                                      <td colspan="3"><s:textfield name="designation" id="designation" cssClass="inputTextBlueLargeAccount" onchange="return fieldLengthValidator2(this);" /></td>
                                                       
                                                       </tr>
                                                      
                                                       <tr>
                                                                 <td class="fieldLabel" >Company<FONT color="red"  ><em>*</em></FONT> </td>
                                                      <td colspan="3"><s:textfield name="company" id="company" cssClass="inputTextBlueLargeAccount" onchange="return fieldLengthValidator2(this);" /></td>
                                                     
                                                    
                                                       </tr>  
                                                        <tr>
                                                                 <td class="fieldLabel" >Speaker Type<FONT color="red"  ><em>*</em></FONT> </td>
                                                    <td ><s:select id="speakerType"  name="speakerType"  list="#@java.util.LinkedHashMap@{'1':'Primary','0':'Secondary'}" cssClass="inputSelect" /></td>
                                                     
                                                     <td class="fieldLabel" >Status<FONT color="red"  ><em>*</em></FONT> </td>
                                                    <td ><s:select id="status"  name="status"  list="{'Active','InActive'}" cssClass="inputSelect" /></td>
                                                     
                                                       </tr>  
                                                       
                                                        <tr id="addTr" style="display: none" align="right" > 

                                                  
                                                     <td  colspan="4">
                                                         <input type="button" value="Save" onclick="return doAddSpeaker();" class="buttonBg" id="addButton" align="right"/> 


                                                        
                                                        <s:reset cssClass="buttonBg"  align="right" value="Reset" />
                                                        <br></br>
                                                    </td>
                                                        </tr> <tr id="editTr" style="display: none"> 
                                                    <td  colspan="3">
                                                         <input type="button" value="Update" onclick="return doUpdateSpeaker();" class="buttonBg" id="updateButton"/> 
                                                    </td>
                                                </tr>
                                                      
                                                
                                                       
                                                               </table>
                                                           </td>
                                                       </tr>
                                                           </table>
                                                       </s:form>    
                                                         </div>
                                     
                                     
                                        <s:form action="#" name="frmDBGrid" id="frmDBGrid" theme="simple"> 
                                            <div style="width:840px;"> 
                                                <s:hidden name="jobId" id="jobId" value="%{jobId}"/>
                                                <table cellpadding="0" cellspacing="0" align="left" width="100%">
                    
                 <tr>
                        <td>
                            <table border="0" cellpadding="0" cellspacing="2" align="left" width="100%">
                                <tr>
                                    <td class="headerText" colspan="11">
                                        <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                    </td>
                                </tr>
                              <%--  <tr>
                                  
                                                     <td class="fieldLabel">Applied Date From:</td>
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
                                                    
                                </tr> --%>
                              <!--  <tr>
                                    <td class="fieldLabel">Name :</td>
                                    <td>
                                      <s:textfield name="name" id="name" cssClass="inputTextBlue" theme="simple"/>
                                    </td>
                                  
                                    <td class="fieldLabel">Email :</td>
                                    <td>
                                      <s:textfield name="email" id="email" cssClass="inputTextBlue" theme="simple"/>
                                    </td>
                                    
                                </tr> -->
                               
                                <tr>
                                    
                                    <td></td>
                                    <td colspan="2"></td>
                                    <td>
                                        <!-- <input type="button" value="Search" class="buttonBg" onclick="getSearchReqList();"/> -->
                                        <!-- <input type="button" class="buttonBg"  align="right"  value="Add" onclick="addEventSpeaker();" />  -->
                                    <%--    <s:submit name="search" value="Search" cssClass="buttonBg"/> --%>
                                        <!--<input type="button" align="right" id="search" value="Search" cssClass="buttonBg" onclick="getSearchReqList();"/>-->
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                                        
                       
            
         
                     <s:if test="#session.eventSpeakersList != null"> 
                         <tr align="center">
                             <td>
                                  <div id="addLoadMessage" style="color: red; display: none">Loading please wait..</div>
                             </td>
                         </tr>
                    <tr>
                        <td>
                          <table id="results" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='90%' align="center">
                               
                              <%java.util.List mainList = (java.util.List) session.getAttribute("eventSpeakersList");
                                if(mainList.size()>0){


%>
                              
<tr class="gridHeader">
                                                        <th>Speaker Name</th>
                                                        <th>Designation</th>
                                                        <th>Primary</th>
                                                        <th>Company</th>
                                                        <th>Status</th>
                                                      
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
                        <%-- <a style="color:#C00067;" href="javascript:getSpeakerDetails(<%=subList.get("Id")%>);"> --%>
                         <%
                          out.println(subList.get("event_speaker"));

%>

<%-- </a> --%>
                     </td>       
                       <td class="title">
                         <%
                          out.println(subList.get("event_designation"));

%>
                     </td>     <td class="title">
                         <%
                         if("1".equals(subList.get("primary_speaker").toString()))
                        out.println("Yes");
                                                else 
                                                    out.println("No");

%>
                     </td>     <td class="title">
                         <%
                        out.println(subList.get("Company"));
                     
%>
                     
                     </td>
                     <td class="title">
                          <%
                        out.println(subList.get("Status"));
                     
%>      
                     </td>
                     
                     
                     
                     
                     
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
        
        
        
        
        
    </body>
</html>

