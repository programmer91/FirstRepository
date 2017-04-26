<!-- 
 *******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    : Sep 09, 2007, 3:25 PM
 *
 * Author  : Venkateswara Rao Nukala<vnukala@miraclesoft.com>
 *
 * File    : LeaveList.jsp 
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
-->
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hubble Organization Portal :: Requirement List</title>
        <sx:head cache="true"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tooltip.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tooltip.js"/>"></script>   
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>   
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script> 
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/RequirementUtil.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/employee/DateValidator.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AjaxPopup.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AjaxRequirement.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/LoadConsultantAjax.js"/>"></script> 
        <s:include value="/includes/template/headerScript.html"/>
        
    </head>
    <body class="bodyGeneral" oncontextmenu="return false;">
        <%!
        
        /* Declarations */
        Connection connection;
        String queryString;
        //StringBuffer queryString;
        String strTmp;
        String strSortCol;
        String strSortOrd;
        String userId;
        String submittedFrom;
        String action;
        //new
        String userRoleName;
        
        int role;
        int intSortOrd = 0;
        int intCurr;
        boolean blnSortAsc = true;
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
                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                            
                            <!--//START DATA COLUMN : Coloumn for Screen Content-->
                            <td width="850px" class="cellBorder" valign="top" style="padding:10px 5px 5px 5px;">
                                <!--//START TABBED PANNEL : -->
                                <ul id="accountTabs" class="shadetabs" >
                                    <li ><a href="#" class="selected" rel="requirementList"  > Requirement List</a></li>
                                    <li ><a href="#" rel="searchDiv">Requirement Search</a></li>
                                </ul>
                                <div  style="border:1px solid gray; width:845px;height: 530px; overflow:auto; margin-bottom: 1em;">
                                    <%--  <sx:tabbedpanel id="attachmentPannel2" cssStyle="width: 845px; height: 530px;padding:10px 5px 5px 5px" doLayout="true"> --%>
                                    
                                    <!--//START TAB : -->
                                    <div id="requirementList" class="tabcontent"  >
                                        <%--   <sx:div id="requirementList" label="Requirement List" cssStyle="overflow:auto;"> --%>
                                        
                                        <%-- <sx:div id="requirementList" label="Requirement List" cssStyle="overflow:auto;"> --%>
                                        <%
                                        if(request.getAttribute(ApplicationConstants.RESULT_MSG)!=null){
                                            out.println(request.getAttribute(ApplicationConstants.RESULT_MSG));
                                        }
                                        %>
                                        
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
                                            //String empLeaveAction = "../../employee/leaveRequest.action";
                                            int  empId  = Integer.parseInt((String) session.getAttribute(ApplicationConstants.SESSION_EMP_ID));
                                            // out.println(empId);
                                            //userRoleName = session.getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
                                            queryString=session.getAttribute(ApplicationConstants.QUERY_STRING).toString();
                                          
                                             //queryString ="SELECT TRIM(tblRecRequirement .Id) AS RequirementId,TRIM(JobTitle) AS JobTitle ,CONCAT(tblRecConsultant.FName,'.',tblRecConsultant.LName) AS ConsultantNAME ,CONCAT(`tblRecRequirement`.`State`,',',`tblRecRequirement`.`Country`) AS Location,tblRecRequirement .STATUS,tblRec.CreatedDate as SubmitedDate,tblRecRequirement.AssignedDate,tblRecRequirement.StartDate,AssignedTo AS Recruiter,SecondaryRecruiter AS SecondaryRecruiter,AssignToTechLead AS PreSales,Skills FROM tblRecRequirement LEFT JOIN(tblRecConsultant, tblRec)ON (tblRecRequirement.Id=tblRec.RequirementId AND tblRecConsultant.Id=tblRec.ConsultantId) ";
                                          //  out.println(queryString);
                                        %>
                                        <%
                                        userRoleName = session.getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
                                           if(("Sales".equalsIgnoreCase(userRoleName)) )
                                         {
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
                                                        <grd:dbgrid id="tblRecRequirement" name="tblRecRequirement" width="100" pageSize="15" 
                                                                    currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                    dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                            
                                                            
                                                            <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                           imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"/>
                                                                                                                       
                                                            <grd:imagecolumn  headerText="Edit" width="5"  HAlign="center"  
                                                                              imageSrc="../../includes/images/DBGrid/newEdit_17x18.png" 
                                                                              linkUrl="getRequirement.action?objectId={RequirementId}" 
                                                                              imageBorder="0" imageWidth="16" imageHeight="16" alterText="Click to edit" />
                                                            
                                                            <grd:anchorcolumn dataField="JobTitle" linkUrl="javascript:getRequirementSkills('{RequirementId}')" headerText="Job Title"
                                                                              linkText="{JobTitle}"  width="10" />
                                                            <%--new filelds start --%> 
                                                            <%--<grd:textcolumn dataField="consultantname"  headerText="ConsultantName"   width="30" /> --%>
                                                           
                                                            <%--<grd:anchorcolumn dataField="consultantname" headerText="ConsultantName" 
                                                                                      linkUrl="../../recruitment/consultant/getConsultant.action?empId={ConsultantId}" linkText="{consultantname}" width="30"/> --%>
                                                                                  
                                                            <%--<grd:datecolumn dataField="SubmittedDate" headerText="SubmittedDate"  width="15"  dataFormat="MM-dd-yyyy HH:mm:SS" />
                                                            <grd:datecolumn dataField="AssignedDate" headerText="AssignedDate"  width="15"  dataFormat="MM-dd-yyyy HH:mm:SS" /> --%>
                                                            <%--new filelds start --%>  
                                                            <grd:textcolumn dataField="Location"  headerText="Location" HAlign="center"  width="5" />
                                                            <grd:textcolumn dataField="status" headerText="Status" HAlign="center" dataFormat="" width="5" />
                                                            <grd:datecolumn dataField="AssignedDate" headerText="Assigned Date" HAlign="center"  width="10"  dataFormat="MM-dd-yyyy" />
                                                            <grd:datecolumn dataField="SubmittedDate" headerText="Submitted Date" HAlign="center"  width="10"  dataFormat="MM-dd-yyyy" />
                                                            <grd:numbercolumn dataField="NoResumes"  headerText="No Of Positions" width="2" dataFormat="0"/>
                                                           <%-- <grd:numbercolumn dataField="ResumeCount"  headerText="Resumes Submitted" width="2" dataFormat="0"/> --%>
                                                            
                                                            
                                                           <%-- <grd:datecolumn dataField="startdate" headerText="StartDate"  width="15"  dataFormat="MM-dd-yyyy HH:mm:SS" /> --%>
                                                            <grd:anchorcolumn dataField="Recruiter" linkUrl="javascript:getRecruiterDetails('{Recruiter}')" headerText="Recruiter"
                                                                              linkText="{Recruiter}"  width="15" />
                                                            
                                                            <grd:anchorcolumn dataField="SecondaryRecruiter" linkUrl="javascript:getRecruiterDetails('{SecondaryRecruiter}')" headerText="Secondary Recruiter"
                                                                              linkText="{SecondaryRecruiter}"  width="15" />  
                                                            <grd:anchorcolumn dataField="PreSales" linkUrl="javascript:getRecruiterDetails('{PreSales}')" headerText="Pre-Sales"
                                                                              linkText="{PreSales}"  width="15" />
                                                                           
                                                            
                                                            
                                                            
                                                        </grd:dbgrid>
                                                        
                                                        <!-- these components are DBGrid Specific  -->
                                                        <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                        <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                        <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                                                        <input type="hidden" name="submitFrom" value="dbGrid">
                                                        
                                                    </td>
                                                </tr>
                                            </table>
                                        </form>
                                        <%  }else if(("Recruitment".equalsIgnoreCase(userRoleName))){%>
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
                                                        <grd:dbgrid id="tblRecRequirement" name="tblRecRequirement" width="100" pageSize="15" 
                                                                    currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                    dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                            
                                                            
                                                            <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                           imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"/>
                                                                                                                       
                                                            <grd:imagecolumn  headerText="Edit" width="5"  HAlign="center"  
                                                                              imageSrc="../../includes/images/DBGrid/newEdit_17x18.png" 
                                                                              linkUrl="getRequirement.action?objectId={RequirementId}" 
                                                                              imageBorder="0" imageWidth="16" imageHeight="16" alterText="Click to edit" />
                                                            
                                                            <grd:anchorcolumn dataField="JobTitle" linkUrl="javascript:getRequirementSkills('{RequirementId}')" headerText="Job Title"
                                                                              linkText="{JobTitle}"  width="10" />
                                                            <%--new filelds start --%> 
                                                            <%--<grd:textcolumn dataField="consultantname"  headerText="ConsultantName"   width="30" /> --%>
                                                           
                                                            <grd:anchorcolumn dataField="consultantname" headerText="ConsultantName" 
                                                                                      linkUrl="../../recruitment/consultant/getConsultant.action?empId={ConsultantId}&requirement={RequirementId}" linkText="{consultantname}" width="20"/>
                                                                                 
                                                            <%--<grd:datecolumn dataField="SubmittedDate" headerText="SubmittedDate"  width="15"  dataFormat="MM-dd-yyyy HH:mm:SS" />
                                                            <grd:datecolumn dataField="AssignedDate" headerText="AssignedDate"  width="15"  dataFormat="MM-dd-yyyy HH:mm:SS" /> --%>
                                                            <%--new filelds start --%>  
                                                            <grd:textcolumn dataField="Location"  headerText="Location" HAlign="center"  width="5" />
                                                            <grd:textcolumn dataField="status" headerText="Status" HAlign="center" dataFormat="" width="5" />
                                                            <grd:datecolumn dataField="AssignedDate" headerText="AssignedDate" HAlign="center"  width="10"  dataFormat="MM-dd-yyyy" />
                                                            <grd:datecolumn dataField="SubmittedDate" headerText="SubmittedDate" HAlign="center"  width="10"  dataFormat="MM-dd-yyyy" />
                                                           
                                                            <grd:numbercolumn dataField="NoResumes"  headerText="No Of Positions" width="2" dataFormat="0"/>
                                                            <grd:numbercolumn dataField=""  headerText="Resumes Submitted" width="2" dataFormat="0"/>
                                                            
                                                           <%-- <grd:datecolumn dataField="startdate" headerText="StartDate"  width="15"  dataFormat="MM-dd-yyyy HH:mm:SS" /> --%>
                                                            <grd:anchorcolumn dataField="Recruiter" linkUrl="javascript:getRecruiterDetails('{Recruiter}')" headerText="Recruiter"
                                                                              linkText="{Recruiter}"  width="15" />
                                                            
                                                            <%--<grd:anchorcolumn dataField="SecondaryRecruiter" linkUrl="javascript:getRecruiterDetails('{SecondaryRecruiter}')" headerText="Secondary Recruiter"
                                                                              linkText="{SecondaryRecruiter}"  width="15" /> --%>
                                                            <grd:anchorcolumn dataField="PreSales" linkUrl="javascript:getRecruiterDetails('{PreSales}')" headerText="Pre-Sales"
                                                                              linkText="{PreSales}"  width="15" />
                                                            
                                                            
                                                            
                                                        </grd:dbgrid>
                                                        
                                                        <!-- these components are DBGrid Specific  -->
                                                        <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                        <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                        <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                                                        <input type="hidden" name="submitFrom" value="dbGrid">
                                                        
                                                    </td>
                                                </tr>
                                            </table>
                                        </form>
                                        
                                        
                                         <%}%>
                                        
                                        <%
                                        }catch(Exception ex){
                                            out.println(ex.toString());
                                        }finally{
                                            if(connection!= null){
                                                connection.close();
                                                //session.setAttribute(ApplicationConstants.QUERY_STRING,null);
                                            }
                                        }
                                        %>
                                        
                                        
                                        <%--   </sx:div> --%>
                                    </div>
                                    
                                    <%-- <sx:div name="searchDiv" id="searchDiv" label="Requirement Search" cssStyle="overflow:auto;"> --%>
                                    <div id="searchDiv" class="tabcontent"  >
                                        <s:form name="searchFrom" id="searchFrom" action="requirementSearch" method="post" theme="simple" onsubmit="return compareDates(document.getElementById('postedDate1').value,document.getElementById('postedDate2').value)">
                                            <table border="0" cellpadding="2" cellspacing="0" width="100%">
                                                <tr class="headerText">
                                                    <td align="right" colspan="6">
                                                        <s:submit align="right" id="search" value="Search" cssClass="buttonBg"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="fieldLabel">Created By :</td>
                                                    <td>
                                                        <s:select headerKey="All" headerValue="All" name="createdBy" id="createdBy" list="createdMemebers" cssClass="inputSelectExtraLarge"/>
                                                    </td>
                                                    <td class="fieldLabel">Assigned To :</td>
                                                    <td>
                                                        <s:select headerKey="All" headerValue="All" list="assignedMembers" name="assignedTo" id="assignedTo" cssClass="inputSelectExtraLarge"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="fieldLabel">Status Of Requirement :</td>
                                                    <td>
                                                       <%-- <s:select headerKey="All" headerValue="All" list="{'Open','InProgress','Closed','Lost'}" name="status" id="status" cssClass="inputSelect"/>--%>
                                                        <s:select headerKey="All" headerValue="All" list="{'Forecast','Open','InProgress','Hold','Withdrawn','Won','Lost','Closed'}" name="status" id="status" cssClass="inputSelect"/>
                                                    </td>
                                                    <td class="fieldLabel">Job Title :</td>
                                                    <td>
                                                        <s:textfield name="title" id="title" cssClass="inputTextBlueLarge"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="fieldLabel">Date Posted (Start Date):</td>
                                                    <td><s:textfield name="postedDate1" id="postedDate1" cssClass="inputTextBlue" /><a href="javascript:cal1.popup();">
                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                    </td>
                                                    <td class="fieldLabel">Date Posted (End Date):</td>
                                                    <td><s:textfield name="postedDate2" id="postedDate2" cssClass="inputTextBlue" /><a href="javascript:cal2.popup();">
                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                    </td>
                                                </tr>
                                            </table>
                                        </s:form>
                                        <script type="text/JavaScript">
                                            var cal1 = new CalendarTime(document.forms['searchFrom'].elements['postedDate1']);
                                            cal1.year_scroll = true;
                                            cal1.time_comp = false;
                                            var cal2 = new CalendarTime(document.forms['searchFrom'].elements['postedDate2']);
                                            cal2.year_scroll = true;
                                            cal2.time_comp = false;
                                        </script>
                                        <%-- </sx:div> --%>
                                    </div>
                                    
                                    <!--//END TAB : -->
                                    <%--     </sx:tabbedpanel> --%>
                                </div>
                                <!--//END TABBED PANNEL : -->
                                <script type="text/javascript">

                                    
var countries=new ddtabcontent("accountTabs")
countries.setpersist(false)
countries.setselectedClassTarget("link") //"link" or "linkparent"
countries.init()

                                </script>
                            </td>
                            <!--//END DATA COLUMN : Coloumn for Screen Content-->
                        </tr>
                    </table>
                </td>
            </tr>
            
            <!--//END DATA RECORD : Record for LeftMenu and Body Content-->
            
            <!--//START FOOTER : Record for Footer Background and Content-->
            <tr class="footerBg">
                <td align="center"><s:include value="/includes/template/Footer.jsp"/>   </td>
            </tr>
            <!--//END FOOTER : Record for Footer Background and Content-->
            
        </table>
        <!--//END MAIN TABLE : Table for template Structure-->
        
        
        
        
        
    </body>
</html>