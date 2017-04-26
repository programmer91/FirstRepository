<%-- 
    Document   : ProjectHierarchy
    Created on : Mar 10, 2014, 10:40:17 PM
    Author     : itg51
--%>

 

<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="com.freeware.gridtag.*" %> 
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hubble Organization Portal :: Employee Leaves Applied</title>
        <sx:head cache="true"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
    
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGProjDetails.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/projectsValidations/ProjectDetailsClientValidation.js"/>"></script> 
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
       <script type="text/JavaScript">
   
       
       function validate() {

           var reportsToId = document.getElementById("projectReportsTo").value;
           var selObj = document.getElementById("projectEmployees");
   //  var projectsArray = new Array();
   //alert("hiii");
     var isSame = false;
       
        var count = 0;
        
        for (var i=0; i<selObj.options.length; i++) {
        if (selObj.options[i].selected) {
            if(reportsToId == selObj.options[i].value){
                isSame = true;
                break;
            }
        
        }
        }
       
        if(isSame){
            alert("Reports To must be different !");
            return false;
        }

    
    else {
       
            return true;   
    }
    
}

        </script>
        <s:include value="/includes/template/headerScript.html"/>
    </head>
  
        <!-- <body class="bodyGeneral" oncontextmenu="return false;" onload="return bodyOnload();"> -->
        <body class="bodyGeneral" oncontextmenu="return false;">
  <%!
/* Declarations */
Connection connection;
String queryString;
String currentAccountId;
String currentProjectId;
String strTmp;
String strSortCol;
String strSortOrd;
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
                           
                            <td width="850px" class="cellBorder" valign="top">
                                <table cellpadding="0" cellspacing="0" width="100%">
                                    <tr>
                                        <td>
                                            <%
                                            userRoleName = session.getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();                                            
                                            %>                    
                                            
                                            <%
                                            if (("Customer Manager".equalsIgnoreCase(userRoleName)) ){
                                            %>
                                            <span class="fieldLabel">Account Name :</span>&nbsp;
                                            <label class="navigationText"><s:property value="#request.accountName"/>
                                            </label>&nbsp;&nbsp;
                                            <span class="fieldLabel">Project Name :</span>&nbsp;
                                            <a href="<s:url action="getProject">
                                                   <s:param name="id" value="%{projectId}"/>
                                               </s:url>" class="navigationText"><s:property value="#request.projectName"/>
                                            </a>
                                            <%  }else  {
                                           
    %>
                                            <span class="fieldLabel">Account Name :</span>&nbsp;
                                            <a href="<s:url action="../crm/accounts/getAccount">
                                                   <s:param name="id" value="%{accountId}"/>
                                               </s:url>" class="navigationText"><s:property value="#request.accountName"/>
                                            </a>&nbsp;&nbsp;
                                            <span class="fieldLabel">Project Name :</span>&nbsp;
                                            <a href="<s:url action="getProject">
                                                   <s:param name="id" value="%{projectId}"/>
                                               </s:url>" class="navigationText"><s:property value="#request.projectName"/>
                                            </a>
                                            <%}%>
                                            
                                        </td>
                                    </tr>
                             
                            <td width="850px" class="cellBorder" valign="top" style="padding:10px 5px 5px 5px;">
                                <ul id="accountTabs" class="shadetabs" >
                                    <li><a href="#" class="selected" rel="getCreRecords">Assign Hierarchy</a></li>
    					 
                                </ul>
                                
                                 <div  style="border:1px solid gray; width:845px;height: 500px; overflow:auto; margin-bottom: 1em;">
                                
                                     <%--<s:form action="saveProjectHeirarchy" theme="simple" name="creSearchForm" id="creSearchForm"> --%>
                                     <s:form action="%{currentAction}" theme="simple" name="heirarchyForm" id="heirarchyForm">
                                   
                                        
                                        
                                   
                                   
                                      <table align="center" border="0" cellpadding="1" cellspacing="1">
                                          <tr>
                                              <%  if(request.getAttribute(ApplicationConstants.RESULT_MSG)!=null)
               {
            
%>
<td align="right" class="headerText"><s:property  value="#request.resultMessage"/></td>
            <%
}          
%>
                                              
                                          </tr>
                                          <tr>
                                              <td>
                                                
                                                      <table border="0">
                                                          
                                                          
                                                         <%-- <tr>
                                                              
                                                              <td class="fieldLabel" rowspan="4">Reports To:<font color="red">*</font></td>
                                                              <td><s:select name="projectReportsTo" id="projectReportsTo" value="" headerKey="" headerValue="please Select.." cssClass="inputSelect" list="projectReportsToMap" onChange="return getEmployeesList();"></s:select>
                                                             </td>
                                                              <td class="fieldLabel" rowspan="4">Available Employees:<font color="red">*</font></td>
                                                             <td rowspan="4"><s:select name="projectEmployees" id="projectEmployees" value="" cssClass="inputSelect" list="projectEmployeeMap" size="6" multiple="true"></s:select></td> 
                                                             <td rowspan="4"><s:submit name="submit" id="Save" value="Save" cssClass="buttonBg" /></td>
                                                              </tr> --%>
                                                          <tr>
                                                              
                                                             
                                                              <td class="fieldLabel" rowspan="4">Available Employees:<font color="red">*</font></td>
                                                             <td rowspan="4"><s:select name="projectEmployees" id="projectEmployees" value="%{projectEmployees}" cssClass="inputSelect" list="projectEmployeeMap" size="6" multiple="true"></s:select></td> 
                                                              <td class="fieldLabel" rowspan="4">Reports To:<font color="red">*</font></td>
                                                              <td><s:select name="projectReportsTo" id="projectReportsTo" value="%{projectReportsTo}" headerKey="" headerValue="please Select.." cssClass="inputSelect" list="projectReportsToMap" onChange="return getEmployeesList();"></s:select>
                                                             </td>
                                                             <s:if test="%{currentAction == 'saveProjectHeirarchy'}">
                                                             <td rowspan="4"><s:submit name="submit" id="Save" value="Save" cssClass="buttonBg" onclick="return validate();"/></td>
                                                                 </s:if>
                                                                 <s:else>
                                                                     <td rowspan="4"><s:submit name="submit" id="Save" value="Update" cssClass="buttonBg" onclick="return validate();"/></td>
                                                                 </s:else>
                                                              </tr>
                                                      
                                               
                                                         <%-- <tr>
                                            <td valign="top">
                                                <s:if test="%{currentAction == 'doAddSubTopics'}">
                                                    <s:submit name="submit" id="Add" value="Add" cssClass="buttonBg"  />
                                                 
                                                </s:if>
                                                <s:else>
                                                    <s:submit name="submit" value="Update" cssClass="buttonBg" />
                                                </s:else>
                                              
                                                  
                                                
                                              
                                            </td></tr> --%>
                                                         <s:hidden  name="projectEmpName" value="%{projectEmpName}"/>
                                                          <s:hidden  name="projectId" value="%{projectId}"/>
                                                          <s:hidden  name="accountId" value="%{accountId}"/>
                                                          
                                                      </s:form>
                                                      </table>
                                          </tr>
                                      </table>
                                                  </div>
                                              </td>
                                            </table>
                                 </td>
                                          </tr>
                                          
                                      </table>
                                                    
                          
                                   
                                    
                                    <%--  </sx:tabbedpanel> --%>
 
                          
                                
                            </td>
                            
                            
                            
                            <!--//END DATA COLUMN : Coloumn for Screen Content-->
                        </tr>
                    </table>
                                    
          
            <tr class="footerBg">
                <td align="center"><s:include value="/includes/template/Footer.jsp"/>   </td>
            </tr>
            <!--//END FOOTER : Record for Footer Background and Content-->
            
        </table>
          
          <script type="text/javascript">
		$(window).load(function(){
	bodyOnload();
		});
		</script>
    </body>
</html>