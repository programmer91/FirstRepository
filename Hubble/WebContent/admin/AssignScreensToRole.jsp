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
    
    <title>Hubble Organization Portal :: Assign Roles</title>
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
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
    
    
    
</head>

<body class="bodyGeneral" oncontextmenu="return false;">
    
    <!--//START MAIN TABLE : Table for template Structure-->
    <table class="templateTable" align="center" cellpadding="0" cellspacing="0">
        
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
                <table class="innerTable" cellpadding="0" cellspacing="0">
                    <tr>
                    
                    <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                    <td width="150px;" class="leftMenuBgColor" valign="top">
                        <s:include value="/includes/template/LeftMenu.jsp"/>
                    </td>
                    <!--//END DATA COLUMN : Coloumn for LeftMenu-->
                            
                    <!--//START DATA COLUMN : Coloumn for Screen Content-->
                    <td width="850px" class="cellBorder" valign="top">
                    <!--//START TABBED PANNEL : -->
                    <sx:tabbedpanel id="assingnRolePannel" cssStyle="width: 840px; height: 420px;padding-left:10px;padding-top:5px;" doLayout="true">
                    
                    <!--//START TAB : -->
                               
                    <sx:div id="accountTab" label="Assingn Role" >
                        <s:form name="assingnRole" action="transferScreensRole" theme="simple">    
                            <table cellpadding="1" cellspacing="1" border="0" width="100%">
                            
                            <tr align="right"> <td><s:submit cssClass="buttonBg" value="Add"/></td> </tr>
                            
                            <s:hidden name="roleId"  value="%{adminRoleVTO.roleId} "  > </s:hidden>
                            
                            <tr>
                                <td  class="fieldLabel">Role Name:  </td>
                                <td>
                                    <s:textfield name="roleName"  value="%{adminRoleVTO.roleName} " readonly="true"  > </s:textfield>
                                </td>
                            </tr>
                            <tr>
                                <td class="fieldLabel"> Change  Screens</td>  
                                <td>
                                    
                                    <s:optiontransferselect
                                        label="Screens Assigned"
                                        name="leftSideRoleScreens"
                                        leftTitle="Screens Not Assigned"
                                        rightTitle="Screens  Assigned"
                                        list="assignedAllScreensMap"
                                        headerKey="headerKey"
                                        doubleName="rightSideRoleScreens"
                                        doubleList="assignedScreensMap"
                                        doubleHeaderKey="doubleHeaderKey"
                                        doubleValue="" 
                                        cssClass="inputTextarea"
                                    />
                                </td>
                            </tr>
                        </s:form>
                    </sx:div >
                    
                    <tr>
                        <td> 
                            <s:form name="assingScreen" action="assignScreen" theme="simple" id="assingScreen">           
                            
                            <a href="javascript:;" onmousedown="if(document.getElementById('addlabel1').style.display == 'none'){ document.getElementById('addlabel1').style.display = 'block'; }else{ document.getElementById('addlabel1').style.display = 'none'; }" >
                            Add New Screen </a>
                            <div style="border-bottom: #336699  4px solid; border-top-style: ridge; display: none;"   id="addlabel1">
                                <table cellpadding="1" cellspacing="1" border="0" width="100%">
                                    <tr>
                                        <td class="fieldLabel"> Enter Module: </td>
                                        <td> 
                                        <s:select 
                                            list="moduleMap"  
                                            name="moduleId"
                                            id="moduleId"
                                            
                                        />
                                    </tr>
                                    <tr>
                                        <td class="fieldLabel"> Enter Screen Name: </td>
                                        <td>  <s:textfield name="screenName"  > </s:textfield>
                                    </tr>
                                    <tr>
                                        <td class="fieldLabel"> Enter Screen Action: </td>
                                        <td>  <s:textfield name="screenAction"  > </s:textfield>
                                    </tr>
                                    <tr>
                                        <td class="fieldLabel"> Enter Screen Title: </td>
                                        <td>  <s:textfield name="screenTitle"  > </s:textfield>
                                    </tr>
                                    <tr>
                                        <td>
                                            <s:submit value="Add" />
                                            
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </td>
                    </tr>
                </table>
                </s:form>
                
                
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



