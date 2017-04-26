<%-- 
    Document   : WebinarSeriesEventsList
    Created on : Jul 31, 2015, 3:19:45 PM
    Author     : miracle
--%>



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
        <title>Hubble Organization Portal :: Webinar&nbsp;Series&nbsp;Events</title>
       
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
       
   <script type="text/JavaScript" src="<s:url value="/includes/javascripts/marketing/EventsPosting.js"/>"></script>  
       
        <s:include value="/includes/template/headerScript.html"/>
        <%-- for issue reminder popup --%>
    </head>
    <body class="bodyGeneral" oncontextmenu="return false;">
       
        
 <% String contextPath = request.getContextPath();
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
                              
                                <ul id="accountTabs" class="shadetabs" >
                                 
                                <%--    <% if(request.getParameter("issueList")==null)
                                       {%> --%>
                                       
                                        <li ><a href="#" class="selected" rel="issuesTab">Webinar&nbsp;Series&nbsp;Events</a></li>
                                       
                                 <%--   <%}else{%>
                                    <li ><a href="#" class="selected" rel="IssuesSearchTab">Tasks Search</a></li>
                                     <% }%> --%>
                                    
                                </ul>
                                 
                                
                                <%-- <sx:tabbedpanel id="IssuesPanel" cssStyle="width: 845px; height: 500px;padding:5px 5px;" doLayout="true"> --%>
                                <div  style="border:1px solid gray; width:845px;height: 500px; overflow:auto; margin-bottom: 1em;">
                                
                            <div id="issuesTab" class="tabcontent" >
                                                           <div id="overlay"></div> 
                                                  <div id="specialBox">
                                                      <s:form theme="simple"  align="center" name="frmSeriesEvent">
                                                           <table align="center" border="0" cellspacing="0" style="width:100%;">
                                                                  <tr>                               
                                                    <td colspan="2" style="background-color: #288AD1" >
                                                        <h3 style="color:darkblue;" align="left">
                                                            <span id="headerLabel"></span>


                                                        </h3></td>
                                                    <td colspan="2" style="background-color: #288AD1" align="right">

                                                        <a href="#" onmousedown="closeToggleOverlay();" >
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
                                                             
                                                             
                                                            <tr id="seriesTr" style="display: none">
                                                     <%-- <td class="fieldLabel" valign="top">Is Associated:<FONT color="red"  ><em>*</em></FONT> </td>
                                                      <td ><s:select id="isAssociated"  name="isAssociated"  list="{'No','YES'}" cssClass="inputSelect" onchange="getEventSeries('');"/></td> --%>
                                                    <%--   <td class="fieldLabel">Associate&nbsp;Event:</td>
                               <td ><s:select id="associatedEventId"  name="associatedEventId"  list="associatedEventMap" headerKey="" headerValue="--Please Select--" cssClass="inputSelect"/></td>  --%>
                                                  </tr>
                                                              
                                                             
                                                      
                                                       
                                                         
                                                         <tr id="createdTr" style="display: none">
                                                      <td class="fieldLabel" valign="top">CreatedBy:<FONT color="red"  ><em>*</em></FONT> </td>
                                                      <td><FONT color="green"  ><span id="createdBy"></span></FONT></td>
                                                       <td class="fieldLabel" valign="top">CreatedDate:<FONT color="red"  ><em>*</em></FONT> </td>
                                                      <td><FONT color="green"  ><span id="createdDate"></span></FONT></td>
                                                  </tr>
                                                        <tr id="addTr" style="display: none"> 

                                                  
                                                     <td  align="center" >
                                                         <input type="button" value="Create" onclick="return doCreateWebinarSeries();" class="buttonBg" id="addButton"/> 


                                                        
                                                        <s:reset cssClass="buttonBg"  align="right" value="Reset" />
                                                        
                                                    </td>
                                                        </tr> <tr id="editTr" style="display: none"> 
                                                    <td  align="center" >
                                                         <input type="button" value="Update" onclick="return doUpdateWebinarSeries();" class="buttonBg" id="updateButton"/> 
                                                    </td>
                                                </tr>
                                                               </table>
                                                           </td>
                                                       </tr>
                                                           </table>
                                                       </s:form>    
                                                         </div>
                                     
                                     
                                                           <s:form action="addEventToSeries" name="frmDBGrid" id="frmDBGrid" theme="simple" onsubmit="return checkSeriesEventMandatory();"> 
                                            <div style="width:840px;"> 
                                               
                                                <table cellpadding="0" cellspacing="0" align="left" width="100%">
                    
                 <tr>
                        <td>
                            <table border="0" cellpadding="0" cellspacing="2" align="left" width="100%">
                                <tr>
                                    <td class="headerText" colspan="11">
                                        <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                         <%
if(request.getAttribute("resultMessage")!=null)                                {
    out.println(request.getAttribute("resultMessage").toString());
}

%>
                                    </td>
                                </tr>
                                
                               
                                
                                <%-- <tr>
                                                                 <td class="fieldLabel" >Search Type<FONT color="red"  ><em>*</em></FONT> </td>
                                                                 <td ><s:select id="tableName"  name="tableName"  list="#@java.util.LinkedHashMap@{'tblContactus':'ContactUs','tblEmpVerfication':'Employee Verification','tblEventAttendies':'QuarterlyMeet','tblResourceDepotDetails':'Resource Depot','tblSuggestions':'Suggestion Box'}" cssClass="inputSelect" headerKey="" headerValue="--Select Type--"/></td>
                                 </tr> --%>
                           
                       <%--      <tr>
                                  
                                                     <td class="fieldLabel">Series From:</td>
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
                                    <td > <s:textfield name="searchSeriesTitle"  id="searchSeriesTitle" cssClass="inputTextBlue" /></td>
                                    <td class="fieldLabel">Type :</td>
                                    <td ><s:select headerKey="" headerValue="--Please Select--" list="#@java.util.LinkedHashMap@{'I':'Internal Webinar','E':'External Webinar'}" name="eventType" id="eventType" cssClass="inputSelect" onchange="getEventType(this);" /></td>
                                </tr>
                                <tr>
                                    <td class="fieldLabel">Track:</td>
                                    <td ><s:select headerKey="" headerValue="--Please Select--" id="searchSeriesTrack"  name="searchSeriesTrack"  list="{'Cloud-Services','SOA and Connectivity','B2B Integration and File Transfer','SAP/ERP Services','Application Management','Business Process Management','Application Development','Quality Assurance and Testing','Digital Experience and Commerce'}" cssClass="inputSelect" /></td>
                                  <td class="fieldLabel">Status:</td>
                                    <td ><s:select id="searchSeriesStatus" name="searchSeriesStatus" list="{'Active','InActive'}" cssClass="inputSelect" headerKey="" headerValue="--Please Select--"/></td> 
                                </tr>
                                --%>
                                
                                
                                <s:hidden name="seriesId" id="seriesId" value="%{seriesId}"/>
                                
                                <tr>
                                     <td class="fieldLabel">Associate&nbsp;Event:</td>
                               <td ><s:select id="associatedEventId"  name="associatedEventId"  list="unassociatedEventMap" headerKey="" headerValue="--Please Select--" cssClass="inputSelect" /></td> 
                                    <td></td>
                                    <td colspan="2"></td>
                                    <td>
                                
                                        <s:submit name="add" value="Add" cssClass="buttonBg"/> 
                                       
                                    </td>
                                  
                                </tr>
                            </table>
                        </td>
                    </tr>
                                        
                       
            
         
                     <s:if test="#session.webinarEventsListBySeries != null"> 
                         <tr align="center">
                             <td>
                                  <div id="addLoadMessage" style="color: red; display: none">Loading please wait..</div>
                             </td>
                         </tr>
                    <tr>
                        <td>
                          <table id="results" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='90%' align="center">
                               
                              <%java.util.List mainList = (java.util.List) session.getAttribute("webinarEventsListBySeries");
                                if(mainList.size()>0){


%>

      <tr class="gridHeader">
          
         
                                                         <th>EventTitle</th>
                                                        
                                                        <th>EventDate</th>
                                                        <th>Remove</th>

                                                  
                </tr>          <%
             //tblEventAttendies
             for (int i = 0; i < mainList.size(); i++) {
                 %>
                 <tr CLASS="gridRowEven">
                            <%
                 //java.util.List subList = (java.util.List)mainList.get(i);
                            java.util.Map subList = (java.util.Map)mainList.get(i);
               //  for (int j = 0; j < subList.size(); j++) {
                     
                     %>
                     
                     <td class="title">
                        <%-- <a style="color:#C00067;" href="javascript:getSeriesDetails(<%=subList.get("Id")%>);"> --%>
                         <%
                         
                          out.println(subList.get("event_title").toString());

%><%-- </a> --%>
                     </td>       
                       <td class="title">
                         <%
                          out.println(subList.get("event_startdate"));

%>
                     </td>     
                   
                      <td class="title">
                             
                         <a style="color:#C00067;" href="javascript:getRemoveEventFromSeries('<%=subList.get("event_id")%>','<%=subList.get("SeriesId")%>');">
                             <img src="../includes/images/go_21x21.gif"/>
                        </a>
                           
                     </td>
                   
                     
                     
                     <%
                     //tblEventAttendies
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
				                 cal1.time_comp = false;

                                             var cal2 = new CalendarTime(document.forms['frmDBGrid'].elements['createdDateTo']);
				                 cal2.year_scroll = true;
				                 cal2.time_comp = false;

						
                                        </script>       --%>                                                      
                                        
                          
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

