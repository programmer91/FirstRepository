<%--
/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * File    : ListOfProjects.jsp
 *
 * Package :
 *
 * $Author: hkondala $  
 *
 * $Date: 2009-03-31 10:48:48 $         
 *
 * $Header: /Hubble/Hubble_CVS/Hubble/web/employee/projects/ListOfProjects.jsp,v 1.2 2009-03-31 10:48:48 hkondala Exp $
 *
 * $Id: ListOfProjects.jsp,v 1.2 2009-03-31 10:48:48 hkondala Exp $
 *
 * $Log: ListOfProjects.jsp,v $
 * Revision 1.2  2009-03-31 10:48:48  hkondala
 * cache=true
 *
 * Revision 1.1  2009-01-21 22:04:26  hkondala
 * Hubble Version 1.3
 *
 *
 * $Name:  $
 *
 * $Revision: 1.2 $
 *
 * $Source: /Hubble/Hubble_CVS/Hubble/web/employee/projects/ListOfProjects.jsp,v $  
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 
 * *****************************************************************************
 */
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
<%--<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>--%>


<html>
    <head>        
        <title>Hubble Organization Portal :: List Of Projects</title>
        <sx:head cache="true"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <%--<link rel="stylesheet" href="<s:url value="/includes/css/displaytag.css"/>">--%>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ProjectAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <s:include value="/includes/template/headerScript.html"/>        
    </head>
    
    <body class="bodyGeneral" oncontextmenu="return false;">
        
        <%!
        /* Declarations */
        Connection connection;
        String queryString;
        String currentEmployeeId;
        String currentAccountId;
        String strTmp;
        String strSortCol;
        String strSortOrd;
        String submittedFrom;
        boolean blnSortAsc = true;
        //int intSortOrd = 0;
        int intCurr;
        
        %>
        
        <!--//START MAIN TABLE : Table for template Structure-->
        <table class="templateTableLogin" align="center" cellpadding="0" cellspacing="0">
            
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
                    <table class="innerTableLogin" cellpadding="0" cellspacing="0">
                        <tr>
                            
                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                            <td width="150px;" class="leftMenuBgColor" valign="top">
                                <s:include value="/includes/template/LeftMenu.jsp"/>
                                
                            </td>
                            <!--//END DATA COLUMN : Coloumn for LeftMenu-->
                            
                            <!--//START DATA COLUMN : Coloumn for Screen Content-->
                            <td width="850px" class="cellBorder" valign="top">
                                
                                
                                <!--//START TABBED PANNEL : --> 
                                <sx:tabbedpanel id="List of Projects" cssStyle="width: 840px; height: 530px;padding-left:10px;padding-top:5px;" doLayout="true">
                                    <%try{%> 
                                    <!--//START TAB : -->
                                    <sx:div id="projectsList" label="All Projects" cssStyle="overflow: auto;"> 
                                        <%
                                        
                                        /* String Variable for storing current position of records in dbgrid*/
                                        strTmp = request.getParameter("txtProjectCurr");
                                        
                                        
                                        strTmp = request.getParameter("txtCurr");
                                        intCurr = 1;
                                        if (strTmp != null)
                                            intCurr = Integer.parseInt(strTmp);
                                        
                                        /* Specifing Shorting Column */
                                        strSortCol = request.getParameter("txtSortCol");
                                        
                                        if (strSortCol == null) strSortCol = "DateCreated";
                                        
                                        strSortOrd = request.getParameter("txtSortAsc");
                                        if (strSortOrd == null) strSortOrd = "ASC";
                                        
                                        currentEmployeeId = session.getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
                                        String userId = session.getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                                        String role = session.getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString();
                                        
                                        connection = ConnectionProvider.getInstance().getConnection();
                                        /* Sql query for retrieving resultset from DataBase */
                                        //if(role.equals("Sales")) {
                                        //queryString = "Select  Id,ProjectName,ProjectStartDate,ProjectEndDate,TotalResources,CustomerId,ProjectManagerUID FROM tblProjects";
                                        //queryString = queryString+" WHERE Id in (select ProjectId from vwProjectTeamEmployees where EmpId="+currentEmployeeId+" ORDER BY ProjectName DESC)";
                                        //}else
                                        //queryString = "Select  Id,ProjectName,ProjectStartDate,ProjectEndDate,TotalResources,CustomerId,ProjectManagerUID,CreatedBy FROM tblProjects WHERE ProjectManagerUID='"+userId+"' OR CreatedBy='"+userId+"'";
                                        queryString = "Select `tblProjects`.`Id` AS `Id`,`tblProjects`.`ProjectName` AS `ProjectName`,`tblProjects`.`ProjectStartDate` AS `ProjectStartDate`,`tblProjects`.`ProjectEndDate` AS `ProjectEndDate`,`tblProjects`.`ProjectManagerUID` AS `ProjectManagerUID`,`tblLKProStatusCode`.`Description` AS `StatusCode` from (`tblPrjStatusTracker` join `tblProjects` on(`tblPrjStatusTracker`.`ProjectId` = `tblProjects`.`Id`) join `tblLKProStatusCode` on(`tblLKProStatusCode`.`Id` = `tblPrjStatusTracker`.`StatusCode`))  WHERE ProjectManagerUID='"+userId+"' OR CreatedBy='"+userId+"'";
                                        //out.println(queryString);
                                        // select * from tblProjects where Id in (select ProjectId from vwProjectTeamEmployees where EmpId=210)
                                        
                                        //String projectAddAction = "../projects/project.action?empId="+currentEmployeeId;
                                        
                                        %>
                                        <form action="" name="frmDBGrid" method="post"> 
                                            <table cellpadding="0" cellspacing="0" width="100%">
                                                <tr>
                                                    <td class="headerText">
                                                        <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <!-- DataGrid for Projects -->
                                                        <grd:dbgrid id="tblProjects" name="tblProjects" width="100" pageSize="20" 
                                                                    currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                    dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                            
                                                            <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                           imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                           addImage="../../includes/images/DBGrid/Add.png" addAction="../../employee/projects/addProject.action" />                                                                             
                                                            <%--
                                                                           addAction="<%=projectAddAction%>"
                                                                           scriptFunction="getNextProjects"/>
                                                                           --%>
                                                            <grd:rownumcolumn headerText="SNo" width="5" HAlign="right"/>
                                                            <grd:imagecolumn  headerText="Edit"  width="5"   
                                                                              linkUrl="../../employee/projects/getEmpProject.action?projId={Id}"
                                                                              HAlign="center"
                                                                              imageSrc="../../includes/images/DBGrid/newEdit_17x18.png"
                                                                              imageBorder="0" 
                                                                              imageWidth="16" 
                                                                              imageHeight="16" 
                                                                              alterText="Click to Edit"/>
                                                            <grd:datecolumn dataField="ProjectStartDate" headerText="Start Date" width="15" dataFormat="MM-dd-yyyy" />
                                                            <grd:datecolumn dataField="ProjectEndDate"  headerText="End Date" width="15" dataFormat="MM-dd-yyyy" />
                                                            <grd:textcolumn dataField="ProjectName"  headerText="Project Name" width="30"/>
                                                            <grd:textcolumn dataField="ProjectManagerUID" headerText="Project Owner" width="15" />
                                                            <grd:numbercolumn dataField="StatusCode" dataFormat=" " headerText="Status" width="25" />
                                                            
                                                        </grd:dbgrid>
                                                        <!-- these components are DBGrid Specific  -->
                                                        <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                        <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                        <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                                                        
                                                    </td>
                                                </tr>
                                            </table>
                                        </form> 
                                    </sx:div>
                                    <!--//END TAB : -->
                                    
                                    <%
                                    connection.close();
                                    connection = null;
                                    }catch(Exception se){
                                        System.out.println("Exception in Customer Projects "+se);
                                    }finally{
                                        if(connection!= null){
                                            connection.close();
                                            connection = null;
                                        }
                                    }
                                    %>     
                                    
                                    
                                    <!--//END TAB : -->
                                        <sx:div id="projectStatus" label="Project Status">
                                            <s:form name="statusForm" id="statusForm" action="" theme="simple">
                                                <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                    <div id="loadMessage" style="display: none" align="center" class="error" >
                                                        <b>Loading.... Please Wait....</b>
                                                    </div>
                                                    <tr>
                                                        <td colspan="4" class="headerText">
                                                            <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="fieldLabel">Projects :</td>
                                                        <td>
                                                            <s:select id="projects" name="projects" list="%{projectsList}" headerKey="-1" headerValue="--please select--" onchange="getStatus(),divHide();" cssClass="inputSelect"/>
                                                            <s:hidden id="projectId" name="projectId" value=""/>
                                                        </td>
                                                        <td class="fieldLabel">Status :</td>
                                                        <td>
                                                            <select id="status" name="status" class="inputSelect" onchange="divHide();"> <option value="<s:property value="%{currentAccount.territory}"/>"><s:property value="%{currentAccount.territory}"/></option></select>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="4">
                                                            <div id="reasonDiv" style="display: none" align="center">
                                                                <table border="0" width="50%">
                                                                    <tr>
                                                                        <td class="fieldLabel">Reason :</td>
                                                                        <td>
                                                                            <s:textarea rows="5" cols="55" id="reason" name="reason" cssClass="inputTextarea"/>
                                                                        </td>
                                                                    </tr>
                                                                </table>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="4" align="center">
                                                            <input type="button" class="buttonbg" id="submit" value="Save" onclick="doAddStatus();">
                                                        </td>
                                                    </tr>
                                                </table>
                                            </s:form>
                                        </sx:div>
                                    
                                </sx:tabbedpanel>
                                <!--//END TABBED PANNEL : -->
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