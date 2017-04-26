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

<html>
    <head>
        
        <title>Hubble Organization Portal :: Assigned Roles</title>
       <sx:head cache="true"/>
        
        <%--This link for ToolTip css --%>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tooltip.css"/>">
        <%--This is End for ToolTip css --%>
         
        <%--This link for ToolTip js --%>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tooltip.js"/>"></script>
        <%--This End for ToolTip js --%>
        
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
      
        <script type="text/JavaScript">
            function assignedRolesCheck()
            {
                
              var addedRolesList =  document.getElementById("addedRolesList");
            var isAdminFlag =  document.getElementById("isAdminFlag").value;
             if(isAdminFlag == 'NO')
                 {
              for(var i=0;i<addedRolesList.length;i++){
                
                 if(addedRolesList.options[i].value=='1' || addedRolesList.options[i].value=='13'){
                     alert("Oops! wrong assignment.");
                     return false;
                 }
              }
                 }
            return true;
            }
        </script>  

        
    </head>
    
    <body class="bodyGeneral" oncontextmenu="return false;">
        
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
                            <!--//END DATA COLUMN : Coloumn for LeftMenu-->
                            
                            <!--//START DATA COLUMN : Coloumn for Screen Content-->
                            <td width="850px" class="cellBorder" valign="top">
                                <!--//START TABBED PANNEL : -->
                                <sx:tabbedpanel id="assingnRolePannel" cssStyle="width: 840px; height: 420px;padding-left:10px;padding-top:5px;" doLayout="true" >
                                    
                                    <!--//START TAB : -->
                                <s:hidden id="isAdminFlag" value="%{isAdminFlag}"></s:hidden>
                                    <sx:div id="accountTab" label="Assingn Role" >
                                        <s:form name="assingnRole" action="transferRole" theme="simple"  onsubmit="return assignedRolesCheck();">    
                                            <table cellpadding="1" cellspacing="1" border="0" width="100%">
                                                <tr>
                                                    <td class="headerText" align="right" colspan="2">
                                                        <s:submit cssClass="buttonBg" value="Save"/>
                                                        <s:hidden name="empRoleId" value="%{adminRoleVTO.id}"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td  class="fieldLabel">User Name:</td>
                                                    <td class="userInfoLeft">
                                                        &nbsp;&nbsp;<s:property value="%{adminRoleVTO.userName}"/>
                                                        <s:hidden name="userName" value="%{adminRoleVTO.userName}"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td  class="fieldLabel">Login Id:</td>
                                                    <td class="userInfoLeft">
                                                        &nbsp;&nbsp;<s:property value="%{adminRoleVTO.loginId}"/>
                                                        <s:hidden name="loginId" value="%{adminRoleVTO.loginId}"/>
                                                    </td>
                                                </tr>
                                                
                                                <tr>
                                                    <td  class="fieldLabel"> Primary Role:</td>
                                                    <td>
                                                        <s:select 
                                                            list="primaryRolesList"  
                                                            name="primaryRole"
                                                            id="primaryRole"
                                                            value="%{adminRoleVTO.primaryRole}"
                                                        />
                                                    </td>
                                                </tr>   
                                                <tr>
                                                    <td  class="fieldLabel" valign="top"> Assign Roles:</td>  
                                                    <td>
                                                        <s:optiontransferselect
                                                            label="Employee Roles"
                                                            name="leftSideEmployeeRoles"
                                                            leftTitle="Avilable Roles"
                                                            rightTitle="Added Roles"
                                                            list="notAssignedRolesMap"
                                                            headerKey="headerKey"
                                                            
                                                            doubleId="addedRolesList"
                                                            doubleName="addedRolesList"
                                                            doubleList="assignedRolesMap"
                                                            doubleHeaderKey="doubleHeaderKey"
                                                            doubleValue="%{adminRoleVTO.primaryRole}"
                                                            cssClass="inputTextarea"
                                                        />
                                                    </td>
                                                </tr>
                                            </table>
                                            
                                        </s:form>
                                    </sx:div >
                                </sx:tabbedpanel>
                            </td>
                            <!--//END DATA COLUMN : Coloumn for Screen Content-->
                        </tr>
                    </table>
                </td>
            </tr>
            <!--//END DATA RECORD : Record for LeftMenu and Body Content-->
            
            <!--//START FOOTER : Record for Footer Background and Content-->
            <tr class="footerBg">
                <td align="center"><s:include value="/includes/template/Footer.jsp"/></td>
            </tr>
            <!--//END FOOTER : Record for Footer Background and Content-->
            
        </table>
        <!--//END MAIN TABLE : Table for template Structure-->
    </body>
    
    
    
    
</html>



