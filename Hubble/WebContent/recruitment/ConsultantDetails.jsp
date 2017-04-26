<%--
/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :com.mss.mirage.recruitment.consultantdetails
 *
 * Date    :  Created on October 5, 2007, 8:36 PM
 *
 * Author  : Kondaiah Adapa<kadapa@miraclesoft.com>
 *
 * File    : ConsultantDetails.jsp
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
--%>

<%@page contentType="text/html;charset=UTF-8" errorPage="../../exception/ErrorDisplay.jsp"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.*,java.util.Date" %>
<%@ page import="com.freeware.gridtag.*" %> 
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd" %>
<%@ page import="com.mss.mirage.recruitment.consultantdetails.ConsultantDetailsAction" %>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<html>
    <head>

        <title>Hubble Organization Portal :: Recruitment</title>

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link REL="StyleSheet" HREF="<s:url value="/includes/css/GridStyle.css"/>" type="text/css">
        <link REL="StyleSheet" HREF="<s:url value="/includes/css/leftMenu.css"/>" type="text/css">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script language="javascript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
        <script language="javascript" src="<s:url value="/includes/javascripts/TimeSheetWeekDays.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/recruitment/ConsultantDetailsClientValidation.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
    </head>

    <body class="bodyGeneral" >
        <%!            String strStartGrid;
            String strEndGrid;
            String searchString;
            String pathName;
            int resultCount = 0;
            int strIntStartGrid;
            int strIntEndGrid;
            /* Declarations */
            Connection connection;
            String queryString;
            String strTmp;
            String strSortCol;
            String strSortOrd;
            String userId;
            String submittedFrom;
            String action;
            int role;
            int intSortOrd = 0;
            int intCurr;
            boolean blnSortAsc = true;
            String consultId;
            String requirementId;
            String requirementAdminFlag;
        %>

        <!--//START MAIN TABLE : Table for template Structure-->
        <table class="templateTable1000x580" align="center" cellpadding="0" cellspacing="0">

            <!--//START HEADER : Record for Header Background and Mirage Logo-->
            <tr class="headerBg">
                <td valign="top">
                    <s:include value="/includes/template/Header.jsp"/>                    
                </td>
            </tr>

            <tr>
                <td>
                    <table class="innerTable1000x515" cellpadding="0" cellspacing="0">
                        <tr>

                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                            <td width="150px;" class="leftMenuBgColor" valign="top">
                                <s:include value="/includes/template/LeftMenu.jsp"/>
                            </td>
                            <%
                            consultId = request.getSession(false).getAttribute("consultId").toString();
            requirementId = request.getSession(false).getAttribute("requirementId").toString();
            requirementAdminFlag = request.getSession(false).getAttribute("requirementAdminFlag").toString();
                            %>
                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->

                            <!--//START DATA COLUMN : Coloumn for Screen Content-->
                            <td width="850px" class="cellBorder" valign="top" style="padding-left:10px;padding-top:5px;">


                                <span class="fieldLabel">Consultant Name :</span>&nbsp;
                                <s:if test="skillBean == null">
                                    <a class="navigationText" href="<s:url action="consultant/getConsultant"><s:param name="consultantId" value="%{empId}"/><s:param name="requirement" value="-1"/></s:url>&consultId=<%=consultId%>&requirementId=<%=requirementId%>&requirementAdminFlag=<%=requirementAdminFlag%>"><s:property value="%{consultantName}"/></a>

                                </s:if>
                                <s:else>
                                    <a class="navigationText" href="<s:url action="consultant/getConsultant"><s:param name="consultantId" value="%{skillBean.empId}"/><s:param name="requirement" value="-1"/></s:url>&consultId=<%=consultId%>&requirementId=<%=requirementId%>&requirementAdminFlag=<%=requirementAdminFlag%>"><s:property value="%{consultantName}"/></a>

                                </s:else>

                                <!--//START TABBED PANNEL : -->

                                <ul id="consultantDetailsTabs" class="shadetabs" >
                                    <li ><a href="#" class="selected" rel="skillsTab"  > Skills</a></li>                                
                                </ul>

                                <%-- <sx:tabbedpanel id="consultantDetails" cssStyle="width: 840px; height: 350px;padding-left:10px;padding-top:5px;" doLayout="true"> --%>

                                <div  style="border:1px solid gray; width:830px;height: 250px; overflow:auto; margin-bottom: 1em;">

                                    <div id="skillsTab" class="tabcontent"  >

                                        <%--  <sx:div id="skillsTab"  label="Skills"  > --%>
                                        <s:form name="skillsForm" action="%{skillBean.actionName}" theme="simple">
                                            <s:hidden name="id" value="%{skillBean.id}" />
                                            <s:hidden name="empId" value="%{skillBean.empId}" /> 
                                            <s:hidden  name="reqList" value="%{reqList}"/> 

                                            <table cellpadding="2" cellspacing="0" border="0" width="100%">        
                                                <tr align="right">
                                                    <td class="headerText" colspan="6">
                                                        <%
                                                            if (request.getAttribute(ApplicationConstants.RESULT_MSG) != null) {
                                                                out.println(request.getAttribute(ApplicationConstants.RESULT_MSG));
                                                            }
                                                             
                                                        %>
                                                        <a class="navigationText" style="text-decoration: none;" href="<s:url action="consultant/getConsultant"><s:param name="consultantId" value="%{skillBean.empId}"/><s:param name="reqList" value="%{reqList}"/></s:url>&consultId=<%=consultId%>&requirementId=<%=requirementId%>&requirementAdminFlag=<%=requirementAdminFlag%>">
                                                                <img alt="Back to List" 
                                                                     src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                width="66px" 
                                                                height="19px"
                                                                border="0" align="bottom">

                                                        </a>
                                                        <s:if test="%{skillBean.actionName == 'editConsultantSkills'}"> 
                                                            <s:submit cssClass="buttonBg" value="Update"/>
                                                        </s:if>
                                                        <s:else>
                                                            <s:submit cssClass="buttonBg" value="Add"/>
                                                        </s:else>
                                                    </td>
                                                </tr>  

                                                <tr>
                                                    <td colspan="6">&nbsp;</td>
                                                </tr>
                                                <tr>
                                                    <td colspan="6">&nbsp;</td>
                                                </tr>
                                                <tr> 
                                                    <td class="fieldLabel">Years Of Experience :</td>
                                                    <td><s:textfield name="yearsOfExperience" value ="%{skillBean.yearsOfExperience}" cssClass="inputTextarea" onchange="yearsOfExperienceValidate(document.skillsForm.yearsOfExperience.value);" /></td> 

                                                    <td class="fieldLabel">Skill Set :</td>
                                                    <td colspan="3" ><s:textfield name="skillSet" value ="%{skillBean.skillSet}" cssClass="inputTextBlueAddress" onchange="skillSetValidate(document.skillsForm.skillSet.value);" /></td> 

                                                </tr>
                                                <tr> 
                                                    <td class="fieldLabel">Skill Set Utilized :</td>
                                                    <td colspan="5" ><s:textarea name="skillSetUtilized" value ="%{skillBean.skillsetUtilized}" cssClass="inputTextarea" cols="80" rows="4"  onchange="skillSetUtilizedValidate(document.skillsForm.skillSetUtilized.value);"/></td> 
                                                </tr>

                                                <tr> 
                                                    <td class="fieldLabel">Project Info :</td>
                                                    <td colspan="5" ><s:textarea name="projectInfo" value ="%{skillBean.projectInfo}" cssClass="inputTextarea" cols="80" rows="4"  onchange="projectInfoValidate(document.skillsForm.projectInfo.value);"/></td> 
                                                </tr>

                                            </table>



                                        </s:form>
                                    </div>
                                </div>
                                <script type="text/javascript">

                                    var countries=new ddtabcontent("consultantDetailsTabs")
                                    countries.setpersist(false)
                                    countries.setselectedClassTarget("link") //"link" or "linkparent"
                                    countries.init()

                                </script>

                                <s:if test="%{skillBean.actionName != 'editConsultantSkills'}">

                                    <ul id="SkillslistTabs" class="shadetabs" >                                                    
                                        <li ><a href="#" rel="skillTab">Skill List</a></li>
                                    </ul>

                                    <div  style="border:1px solid gray; width:800px;height: 200px; overflow:auto; margin-bottom: 1em;">

                                        <div id="skillTab" class="tabcontent"  >
                                            <form name="frmDBSkillGrid" action="" method="post">
                                                <s:hidden name="consultantId" value="%{empId}"/>
                                                <%-- <s:hidden name="reqList" value="%{reqList}"/>   --%>
                                                <table cellpadding="2" cellspacing="0" border="0" width="100%">                                               
                                                    <tr align="right">
                                                        <td class="headerText">
                                                            <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">                                                    
                                                        </td>
                                                    </tr>

                                                    <tr>
                                                        <td align="center">
                                                            <%
                                                                // out.println("request.consultent="+request.getParameter("consultantId")+"----page.consultent="+consultantId+"----request.empId="+request.getParameter("empId"));
                                                                String custId = null;
                                                                //request.setAttribute("");
                                                                String reqList = request.getParameter("reqList");
                                                                if (request.getParameter("empId") != null) {

                                                                    custId = request.getParameter("empId");
                                                                    request.getSession().setAttribute("consultantId", custId);
                                                                }
                                                                custId = request.getSession(false).getAttribute("consultantId").toString();

                                                            %>   
                                                            <%
                                                                int intCurr = 1;
                                                                intSortOrd = 0;
                                                                String strTmp = null;
                                                                String strSQL = null;
                                                                String strSortCol = null;
                                                                String strSortOrd = "ASC";
                                                                blnSortAsc = true;
                                                                strSQL = "select * from tblRecSkills where empId=" + Integer.parseInt(custId);
                                                                Connection objCnn = null;
                                                                Class objDrvCls = null;
                                                                objDrvCls = Class.forName("com.mysql.jdbc.Driver");
                                                                objCnn = ConnectionProvider.getInstance().getConnection();
                                                                if (objDrvCls != null) {
                                                                    objDrvCls = null;
                                                                }
                                                                strTmp = request.getParameter("txtSkillCurr");

                                                                try {
                                                                    if (strTmp != null) {
                                                                        intCurr = Integer.parseInt(strTmp);
                                                                    }

                                                                } catch (NumberFormatException NFEx) {
                                                                }
                                                                strSortCol = request.getParameter("txtSkillSortCol");

                                                                strSortOrd = request.getParameter("txtSkillSortAsc");

                                                                if (strSortCol == null) {
                                                                    strSortCol = "eno";
                                                                }
                                                                if (strSortOrd == null) {
                                                                    strSortOrd = "ASC";
                                                                }
                                                                blnSortAsc = (strSortOrd.equals("ASC"));
                                                                 String editAction="getConsultantSkills.action?id={id}&consultantId={empId}&consultId="+consultId+"&requirementId="+requirementId+"&requirementAdminFlag="+requirementAdminFlag;
                                                                //String consultantQuery="viewConsultantSkills.action?consultantId="+custId;
%>
                                                            <grd:dbgrid  id="tblSkills" 
                                                                         name="tblSkills" 
                                                                         width="100" 
                                                                         pageSize="10" 
                                                            currentPage="<%=intCurr%>"
                                                                         border="0" 
                                                                         cellSpacing="1" 
                                                                         cellPadding="2" 
                                                            dataMember="<%=strSQL%>" 
                                                            dataSource="<%=objCnn%>"
                                                                         cssClass="gridTable">
                                                                <grd:gridpager imgFirst="../includes/images/DBGrid/First.gif" imgPrevious="../includes/images/DBGrid/Previous.gif" 
                                                                               imgNext="../includes/images/DBGrid/Next.gif" imgLast="../includes/images/DBGrid/Last.gif"
                                                                               />
                                                                <grd:gridsorter sortColumn="<%=strSortCol%>" 
                                                                sortAscending="<%=blnSortAsc%>" />

                                                                <grd:imagecolumn  headerText="Edit" 
                                                                                  width="3"  HAlign="center" 
                                                                                  imageSrc="../includes/images/DBGrid/newEdit_17x18.png" 
                                                                                  linkUrl="<%=editAction%>"
                                                                                  imageBorder="0"
                                                                                  imageWidth="16"
                                                                                  imageHeight="16" 
                                                                                  alterText="Click to edit" />
                                                                <grd:textcolumn dataField="YearsOfExperience" 
                                                                                headerText="Years Of Experience"  
                                                                                HAlign="center"
                                                                                width="15"   />
                                                                <grd:textcolumn dataField="SkillSet" headerText="SkillSet" 
                                                                                width="15"    HAlign="center"/>
                                                                <grd:textcolumn dataField="ProjectInfo" headerText="ProjectInfo" 
                                                                                width="15"  HAlign="center"/>
                                                                <%--                
                                                     <grd:imagecolumn  headerText="Delete" 
                                                                       width="5" 
                                                                       HAlign="center" 
                                                                       imageSrc="../includes/images/DBGrid/Delete.png" 
                                                                       linkUrl="deleteConsultantSkills.action?id={Id}"
                                                                       imageBorder="0" 
                                                                       imageWidth="51"
                                                                       imageHeight="20"
                                                                       alterText="Click to edit" />                
                                                                --%>
                                                            </grd:dbgrid>
                                                            <input TYPE="hidden" NAME="txtSkillCurr" VALUE="<%=intCurr%>">
                                                            <input TYPE="hidden" NAME="txtSkillSortCol" VALUE="<%=strSortCol%>">
                                                            <input TYPE="hidden" NAME="txtSkillSortAsc" VALUE="<%=strSortOrd%>">
                                                            <input TYPE="hidden" NAME="txtAttachCurr" VALUE="">
                                                            <input TYPE="hidden" NAME="txtSubmittCurr" VALUE="">
                                                            <input TYPE="hidden" NAME="txtTechCurr" VALUE="">
                                                            <input TYPE="hidden" NAME="submitFrom" VALUE="dbGrid">

                                                            <%
                                                                try {
                                                                    if (objCnn != null) {
                                                                        objCnn.close();
                                                                    }
                                                                } catch (SQLException SQLExIgnore) {
                                                                }
                                                                if (objCnn != null) {
                                                                    objCnn.close();
                                                                }
                                                                objCnn = null;
                                                            %>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </form>
                                        </div>
                                        <script type="text/javascript">

                                            var countries=new ddtabcontent("SkillslistTabs")
                                            countries.setpersist(false)
                                            countries.setselectedClassTarget("link") //"link" or "linkparent"
                                            countries.init()

                                        </script>
                                    </div>
                                </s:if>



                                <%--  </sx:div > --%>

                                <!--//END TAB : -->

                                <%-- </sx:tabbedpanel> --%>


                            </td>
                            <!--//END DATA COLUMN : Coloumn for Screen Content-->
                        </tr>
                    </table>
                </td>
            </tr>
            <!--//END DATA RECORD : Record for LeftMenu and Body Content-->

            <!--//START FOOTER : Record for Footer Background and Content-->
            <tr class="footerBg">
                <td align="center">&reg; 2007  Mirage - All Rights Reserved.</td>
            </tr>
            <!--//END FOOTER : Record for Footer Background and Content-->

        </table>
        <!--//END MAIN TABLE : Table for template Structure-->
    </body>
</html>