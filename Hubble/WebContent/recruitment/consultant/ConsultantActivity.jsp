<%--
/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package : com.mss.mirage.recruitment
 *
 * Date    : October 22, 2008, 7:45 PM
 *
 * Author  : Hari Krishna Kondala<hkondala@miraclesoft.com>
 *
 * File    : ConsultantActivity.jsp
 *
 * Copyright 2008 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
 --%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.mss.mirage.util.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.freeware.gridtag.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>Hubble Organization Portal :: Consultant Activity</title>        
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmpStandardClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/recruitment/ClientValidationRecruitment.js"/>"></script>
        
        <s:include value="/includes/template/headerScript.html"/>
    </head>
    <body class="bodyGeneral" oncontextmenu="return false;">  
        <%!
        
        /* Declarations */
        Connection connection;
        String queryString;
        String strTmp;
        String strSortCol;
        String strSortOrd;
        String consultantId;
        
        int intSortOrd = 0;
        int intCurr;
        boolean blnSortAsc = true;
        String consultId;
            String requirementId;
            String requirementAdminFlag;
        %>
        <%
                            consultId = request.getSession(false).getAttribute("consultId").toString();
            requirementId = request.getSession(false).getAttribute("requirementId").toString();
            requirementAdminFlag = request.getSession(false).getAttribute("requirementAdminFlag").toString();
                            %>

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
                            <td width="850px" class="cellBorder" valign="top">
                                <table cellpadding="0" cellspacing="0" width="100%" border="0">
                                    <tr style="font-family: arial,verdana; font-size:12px;">
                                        <td>
                                            <span class="fieldLabel">Consultant Name :</span>&nbsp;
                                            <s:if test="currentConsultant == null">
                                                <a class="navigationText" href="<s:url action="getConsultant"><s:param name="consultantId" value="%{consultantId}"/><s:param name="requirement" value="-1"/></s:url>&consultId=<%=consultId%>&requirementId=<%=requirementId%>&requirementAdminFlag=<%=requirementAdminFlag%>"><s:property value="%{consultantName}"/></a>
                                                <%--<s:property value="%{consultantName}"/>--%>
                                            </s:if>
                                            <s:else>
                                                <a class="navigationText" href="<s:url action="getConsultant"><s:param name="consultantId" value="%{currentConsultant.consultantId}"/><s:param name="requirement" value="-1"/></s:url>&consultId=<%=consultId%>&requirementId=<%=requirementId%>&requirementAdminFlag=<%=requirementAdminFlag%>"><s:property value="%{consultantName}"/></a>
                                                <%--<s:property value="%{consultantName}"/>--%>
                                            </s:else>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td valign="top" style="padding-left:10px;padding-top:5px;">
                                            
                                            <ul id="ConsultantActivityTabs" class="shadetabs" >
                                                <li ><a href="#" class="selected" rel="activityDiv"  > Activity Details </a></li>                                                    
                                            </ul>
                                            <%-- <sx:tabbedpanel id="consultantActivityPanel" cssStyle="width: 840px; height: 240px;padding: 5px 5px;"> --%>
                                           
                                            <div  style="border:1px solid gray; width:800px;height: 200px; overflow:auto; margin-bottom: 1em;">
                                                
                                                <%--  <sx:div id="activityDiv" label="Activity Add" cssStyle="overflow:auto;"> --%>
                                              
                                                <div id="activityDiv" class="tabcontent"  >
                                                    
                                                    <s:form name="activityForm" id="activityForm" action="%{currentConsultant.currentAction}" onsubmit="return ConsultantAddFormvalidation();" theme="simple" method="post">
                                                        <s:hidden name="id" id="id" value="%{currentConsultant.objectId}"/>
                                                        <s:hidden name="consultantId" id="consultantId" value="%{currentConsultant.consultantId}"/>
                                                        
                                                       <%-- <input type="text" name="reqList" value="<%=session.getAttribute("reqList").toString()%>"/>--%>
                                                        <table border="0" cellpadding="2" cellspacing="0" width="100%" align="center">
                                                            <tr class="headerText">
                                                                <%-- <td align="left" colspan="2">
                                                                    Activity Details:
                                                                </td> --%>
                                                                <td align="right" colspan="5">
                                                                    <%
                                                                    if(request.getAttribute(ApplicationConstants.RESULT_MSG)!=null){
                                                                        out.println(request.getAttribute(ApplicationConstants.RESULT_MSG));
                                                                    }
                                                                    %>
                                                                    
                                  <INPUT type="button" CLASS="buttonBg" value="Back to List" onClick="history.go(-1)">&nbsp;&nbsp;
                                   <!--new code for backtoList-->
                             
                                   <!--end new Code-->
                                                                           <%--<%}%>--%>
                                                                    <s:if test="%{currentConsultant.currentAction == 'editActivity'}">
                                                                        <s:submit align="right" id="save" value="Update" cssClass="buttonBg"/>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <s:submit align="right" id="save" value="Save" cssClass="buttonBg"/>
                                                                    </s:else>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel">Activity Type : <FONT color="red"  ><em>*</em></FONT></td>
                                                                <td>
                                                                    <s:select name="activityType" id="activityType" value="%{currentConsultant.activityType}" headerKey="-1" headerValue="--Please Select--" list="{'Email','Call-Inbound','Call-Outbound'}" cssClass="inputSelect"/>
                                                                </td>
                                                                <td class="fieldLabel">Availability :</td>
                                                                <td>
                                                                    <s:select name="availability" id="availability" value="%{currentConsultant.availability}" headerKey="Available Now" headerValue="Available Now" list="{'1Week','2Weeks','3Weeks','1-3Months','3-6Months','6-12Months','Above 1Year'}" cssClass="inputSelect"/>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel">Assigned To : <FONT color="red"  ><em>*</em></FONT></td>
                                                                <td>
                                                                    <s:select name="assignedTo" id="assignedTo" value="%{currentConsultant.assignedTo}" headerKey="-1" headerValue="--Please Select--" list="assignedMembers" cssClass="inputSelect"/> 
                                                                </td>
                                                                <td class="fieldLabel">Status :</td>
                                                                 <td>
                                                                   <s:select name="status" id="status" value="%{currentConsultant.status}" list="activityStatusList" cssClass="inputSelect"/> 
                                                                </td>
                                                            </tr>
                                                           
                                                            <tr>
                                                                <td class="fieldLabel">Due Date :</td>
                                                                <td>
                                                                    <s:textfield name="dueDate" value="%{currentConsultant.dueDate}" cssClass="inputTextBlue"/>
                                                                    <a href="javascript:cal3.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                         width="20" height="20" border="0"></a>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel">Comments :</td>
                                                                <td colspan="4">
                                                                    <s:textarea name="comments1" id="comments1" value="%{currentConsultant.comments1}" onchange="fieldLengthValidator(this)" rows="3" cols="77" cssClass="inputTextarea"/>
                                                                </td>
                                                            </tr>
                                                            
                                                            
                                                        </table>
                                            
                                                        
                                                        <script type="text/JavaScript">
                                                           var cal3 = new CalendarTime(document.forms['activityForm'].elements['dueDate']);
                                                           cal3.year_scroll = true;
                                                           cal3.time_comp = true;
                                                        </script>
                                                    </s:form>
                                                </div>
                                            </div>
                                            
                                            <script type="text/javascript">

                                                    var countries=new ddtabcontent("ConsultantActivityTabs")
                                                    countries.setpersist(false)
                                                    countries.setselectedClassTarget("link") //"link" or "linkparent"
                                                    countries.init()

                                            </script>
                                            
                                            <%-- </sx:div> --%>
                                            <%-- </sx:tabbedpanel> --%>
                                            
                                            <s:if test="%{currentConsultant.currentAction == 'addActivity'}">
                                                
                                                <ul id="ActivitylistTabs" class="shadetabs" >                                                    
                                                    <li ><a href="#" rel="consultantActivityDiv">Activity List</a></li>
                                                </ul>
                                                
                                                <div  style="border:1px solid gray; width:800px;height: 200px; overflow:auto; margin-bottom: 1em;">
                                                    
                                                    <div id="consultantActivityDiv" class="tabcontent"  >
                                                        
                                                        <%-- <sx:tabbedpanel id="ActivityPanel" cssStyle="width: 840px; height: 250px;padding: 5px 5px;" doLayout="true"> --%>
                                                        <%-- <sx:div name="consultantActivityDiv" id="consultantActivityDiv" label="Activity List" cssStyle="overflow:auto;"> --%>
                                                        
                                                        <%
                                                        /* String Variable for storing current position of records in dbgrid*/
                                                        strTmp = request.getParameter("txtCurr");
                                                        
                                                        intCurr = 1;
                                                        
                                                        if (strTmp != null)
                                                            intCurr = Integer.parseInt(strTmp);
                                                        
                                                        try{
                                                            
                                                            /* Getting DataSource using Service Locator */
                                                            connection=ConnectionProvider.getInstance().getConnection();
                                                            
                                                            
                                                            /* Sql query for retrieving resultset from DataBase */
                                                            String  queryString  =null;
                                                            //String empLeaveAction = "/"+ApplicationConstants.CONTEXT_PATH+"/employee/leaveRequest.action";
                                                            
                                                            //consultantId = session.getAttribute("consultantId").toString();
                                                            //out.println(consultantId);
                                                            
                                                            //queryString ="SELECT Id,ConsultantId,ActivityType,Status,Availability,Comments,InterviewDate,ReportingDate,CreatedBy,CreatedDate,AssignedTo FROM tblRecActivity";
                                                            //queryString = queryString + "ORDER BY CreatedDate DESC";
                                                            queryString = session.getAttribute(ApplicationConstants.QUERY_STRING).toString();
                                                            //out.println(queryString);
                                                            
                                                            String editAction="activity.action?id={Id}&consultantId={ConsultantId}&consultId="+consultId+"&requirementId="+requirementId+"&requirementAdminFlag="+requirementAdminFlag;	
                                                        %>
                                                        
                                                        <form method="post" id="frmDBGrid" name="frmDBGrid" action="">
                                                            <table cellpadding="0" cellspacing="0" width="100%">
                                                                <tr>
                                                                    <td class="headerText">
                                                                        <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        <!-- DataGrid for list all activities -->
                                                                        <grd:dbgrid id="tblRecActivity" name="tblRecActivity" width="100" pageSize="15" 
                                                                                    currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                                    dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                            
                                                                            
                                                                            <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                                           imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"/>
                                                                            
                                                                            <grd:imagecolumn  headerText="Edit" width="5"  HAlign="center" 
                                                                                              imageSrc="../../includes/images/DBGrid/Edit.gif" 
                                                                                              linkUrl="<%=editAction%>" 
                                                                                              imageBorder="0" imageWidth="16" imageHeight="16" 
                                                                                              alterText="Click to edit" /> 
                                                                            <grd:datecolumn dataField="CreatedDate" headerText="DateCreated" dataFormat="MM-dd-yyyy" width="13"/>
                                                                            <grd:textcolumn dataField="ActivityType"  headerText="ActivityType"   width="15"/>                                                                      
                                                                            <grd:textcolumn dataField="Comments"      headerText="Comments" width="29"/>
                                                                            
                                                                            <grd:textcolumn dataField="AssignedTo"   headerText="AssignedTo"  width="15"/>  
                                                                            <grd:textcolumn dataField="Status"       headerText="Status"     width="15"/>     
                                                                            <grd:datecolumn dataField="InterviewDate" headerText="InterviewDate" dataFormat="MM-dd-yyyy" width="20"/>
                                                                            
                                                                        </grd:dbgrid>
                                                                        
                                                                        <!-- these components are DBGrid Specific  -->
                                                                        <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                                        <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                                        <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </form>
                                                        <%
                                                        }catch(Exception ex){
                                                            out.println(ex.toString());
                                                        }finally{
                                                            if(connection!= null){
                                                                connection.close();
                                                            }
                                                        }
                                                        %>
                                                    </div>
                                                </div>
                                                <script type="text/javascript">

                                                     var countries=new ddtabcontent("ActivitylistTabs")
                                                     countries.setpersist(false)
                                                     countries.setselectedClassTarget("link") //"link" or "linkparent"
                                                     countries.init()

                                                </script>
                                                <%-- </sx:div> --%>
                                                <%-- </sx:tabbedpanel> --%>
                                            </s:if>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <!--//START FOOTER : Record for Footer Background and Content-->
            <tr class="footerBg">
                <td align="center"><s:include value="/includes/template/Footer.jsp"/></td>
            </tr>
        </table>
    </body>
</html>
