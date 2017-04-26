<%-- 
    Document   : EventSpeakersDetails
    Created on : Aug 7, 2015, 6:31:24 PM
    Author     : miracle
--%>

<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.*,java.util.Date" %>
<%@ page import="com.freeware.gridtag.*" %> 
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%-- <%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd" %>


<html>
<head>
    
    <title>Hubble Organization Portal :: Event Speaker/Author Information</title>
    <sx:head cache="false"/>
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
    <link rel="StyleSheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
    <link REL="StyleSheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
    <script language="javascript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
    <%--<script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>--%>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
    <SCRIPT type="text/javascript">
	function closeStates()
	{
          window.close();      
	}        
        
    </SCRIPT>
</head>
<%!
Connection connection = null;
%>
<body class="bodyGeneral" oncontextmenu="return false;">
<br>
 <%String contextPath = request.getContextPath();
                    %>
<table cellpadding="0" width="70%" align="center" cellspacing="0" bordercolor="#efefef" class="cellBorder">  

<tr class="headerText">
    <td></td>
    <td> 
        <s:if test="%{objectType=='EV'}">
            Event Speaker Details
        </s:if><s:else>
            Author Details
        </s:else>
        
    </td>
</tr>
<tr>
<td colspan="5"> 
    
    <table align="center" cellpadding="0" cellspacing="0"  width="100%">
        <tr>
            <td align="center">
                <br>
                   
                  <s:if test="#session.peopleDetailsList != null"> 
                    <tr>
                        <td>
                          <table id="results" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='90%' align="center">
                               
                              <%java.util.List mainList = (java.util.List) session.getAttribute("peopleDetailsList");
                                if(mainList.size()>0){


%>
                              
                             <tr class="gridHeader">
                                 <s:if test="%{objectType=='EV'}">
                                     <th>SpeakerName</th>
                                 </s:if><s:else>
                                     <th>AuthorName</th>
                                 </s:else>
                                                        
                                                       <th>Designation</th>
                                                       <th>Company</th>
                                                       <th>Primary</th>
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
                         
                        
                         
                        
                         <%
                          out.println(subList.get("SpeakerName"));
                         %>
                     </td>    
                     <td class="title">
                          <%
                          
                         
                          
                          out.println("Designation");
                         %>
                     </td>
                     
                       
                     
                     <td class="title">
                         <%
                          out.println(subList.get("Company"));

%>
                     </td> <td class="title">
                         <%
                         if(subList.get("PrimaryFlag").toString().equals("1"))
                          out.println("Yes");
                          else
                              out.println("NO");
%>
                     </td> <td class="title">
                         <%
                          out.println(subList.get("STATUS"));

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
                    
                    
                    
                    
                     </s:if>
           
                
                
                
            </td>
        </tr>
    </table>
    </div>

   
    <p align="center">
    </p>
</td>      
</tr>
</table>


</body>
</html>
