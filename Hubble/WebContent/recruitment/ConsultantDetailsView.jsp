<%--
/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :com.mss.mirage.recruitment
 *
 * Date    :  Created on October 5, 2007, 8:36 PM
 *
 * Author  : Kondaiah Adapa<kadapa@miraclesoft.com>
 *
 * File    : ConsultantDetailsView.jsp
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
 
 --%>

<%@page contentType="text/html;charset=UTF-8" errorPage="../../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider" %>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>

<%@ page import="java.util.Date" %>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd" %>
<%@ page import="com.freeware.gridtag.*" %>
<%@ page import="com.mss.mirage.recruitment.consultantdetails.ConsultantDetailsAction" %>
<html>
    <head>
        
        <title>Hubble Organization Portal :: Recruitment</title>
                 
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link REL="StyleSheet" HREF="<s:url value="/includes/css/GridStyle.css"/>" type="text/css">
        <link REL="StyleSheet" HREF="<s:url value="/includes/css/leftMenu.css"/>" type="text/css">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script language="javascript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
        <script language="javascript" src="<s:url value="/includes/javascripts/DBConsultantGrid.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
         <s:include value="/includes/template/headerScript.html"/>
    </head>
    
    <body class="bodyGeneral" oncontextmenu="return false;" >
        
        <%!
        Connection connection=null;
        String strSQL=null;
        int intSortOrd = 0;
        String custId=null;
        boolean blnSortAsc = true;
        
        %>
        
        <%
        if(request.getParameter("consultantId")!=null) {
            
            custId =request.getParameter("consultantId");
            request.getSession().setAttribute("consultantId",custId);
            
        }
        custId= request.getSession(false).getAttribute("consultantId").toString();
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
                        <td width="850px" class="cellBorder" valign="top" style="padding-left:10px;padding-top:5px;">
                            
                            <ul id="consultantDetailsTabs" class="shadetabs" >
                                    <li ><a href="#" class="selected" rel="demographicTab"  > Consultant Details </a></li>                                   
                            </ul>
                        
                        <!--//START TABBED PANNEL : -->
                                
                        <%-- <sx:tabbedpanel id="consultantDetails" cssStyle="width: 840px; height: 160px;padding-left:10px;padding-top:5px;" doLayout="true"> --%>
                            
                            <!--//START TAB : -->
                         <%--   <sx:div id="demographicTab" label="Consultant Details"  > --%>
                            <div  style="border:1px solid gray; width:840px;height: 160px; overflow:auto; margin-bottom: 1em;">
                                
                                <div id="demographicTab" class="tabcontent"  >
                                    <s:form name="frmDetails" action="popUpConsultant" theme="simple">
                                        
                                        <table cellpadding="2" cellspacing="0" border="0" width="100%">                                                
                                            <tr align="right">
                                                <td class="headerText" colspan="6">
                                                    
                                                    <s:submit cssClass="buttonBg" value="Back to List"/>
                                                </td>
                                            </tr> 
                                            
                                            <tr>
                                                <td colspan="6">&nbsp;</td>
                                            </tr>
                                            
                                            <tr> 
                                                
                                                <td class="fieldLabel">First Name :</td>
                                                <td colspan="1"><s:textfield name="fiName" value="%{consultantDetails.firstName}" cssClass="inputTextBlue" readonly="true" /> </td>
                                                <td class="fieldLabel">Last Name :</td>                  
                                                <td ><s:textfield name="laName" cssClass="inputTextBlue" value="%{consultantDetails.lastName}"  readonly="true" /> </td>
                                                <td class="fieldLabel">Middile Name :</td>
                                                <td ><s:textfield name="miName" cssClass="inputTextBlue"  value="%{consultantDetails.middleName}" readonly="true" /> </td>
                                            </tr>               
                                            
                                            <tr> 
                                                <td class="fieldLabel">Email :</td>
                                                <td  colspan="3" ><s:textfield readonly="true" name="eMail1" value="%{consultantDetails.email}" cssClass="inputTextBlueLarge"/></td> 
                                                
                                                <td class="fieldLabel">Phone :</td>
                                                <td  ><s:textfield  readonly="true" name="cellPhoneNo" value="%{consultantDetails.phoneNo}"  cssClass="inputTextarea" /></td> 
                                                
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
                            <%-- </sx:div > --%>
                            
                            <!--//END TAB : -->
                                
                       <%-- </sx:tabbedpanel> --%>
                       
                       <ul id="consultantotherDetailsTabs" class="shadetabs" >
                                    <li ><a href="#" class="selected" rel="skillTab"  > Skills </a></li>
                                    <li ><a href="#" rel="resumeAttachmentListTab">Resume Attachment</a></li>
                                    <li ><a href="#" rel="resumeSubmittTab">Resume Submit</a></li>
                                    <li ><a href="#" rel="techReviewTab">Tech Reviews</a></li>
                      </ul>
                       
                       <%-- <sx:tabbedpanel id="consultantotherDetails" cssStyle="width: 840px; height: 390px;padding-left:10px;padding-top:5px;" doLayout="true" selectedTab="resumeAttachmentListTab" useSelectedTabCookie="true"> --%>
                         <%--   <sx:div id="skillTab"  label=" Skills "  > --%>
                         <div  style="border:1px solid gray; width:840px;height: 390px; overflow:auto; margin-bottom: 1em;">
                             
                             <div id="skillTab" class="tabcontent"  >
                                 <form name="frmDBSkillGrid" action="" method="post">
                                     <table cellpadding="2" cellspacing="0" border="0" width="100%">                                               
                                         <tr align="right">
                                             <td class="headerText">
                                                 <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">                                                    
                                             </td>
                                         </tr>
                                         
                                         <tr>
                                             <td align="center">
                                                 
                                                 <%
                                                 int intCurr = 1;
                                                 intSortOrd = 0;
                                                 String strTmp = null;
                                                 strSQL = null;
                                                 String strSortCol = null;
                                                 String strSortOrd = "ASC";
                                                 blnSortAsc = true;
                                                 strSQL = "select * from tblRecSkills where empId="+Integer.parseInt(custId);
                                                 Connection objCnn    = null;
                                                 Class      objDrvCls = null;
                                                 objDrvCls = Class.forName("com.mysql.jdbc.Driver");
                                                 objCnn=ConnectionProvider.getInstance().getConnection();
                                                 if (objDrvCls != null) objDrvCls = null;
                                                 strTmp = request.getParameter("txtSkillCurr");
                                                 
                                                 try {
                                                 if (strTmp != null)
                                                 intCurr = Integer.parseInt(strTmp);
                                                 
                                                 } catch (NumberFormatException NFEx) {
                                                 }
                                                 strSortCol = request.getParameter("txtSkillSortCol");
                                                 
                                                 strSortOrd = request.getParameter("txtSkillSortAsc");
                                                 
                                                 if (strSortCol == null) strSortCol = "eno";
                                                 if (strSortOrd == null) strSortOrd = "ASC";
                                                 blnSortAsc = (strSortOrd.equals("ASC"));
                                                 String consultantQuery="./viewConsultantSkills.action?consultantId="+custId;
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
                                                     <grd:gridpager imgFirst="../includes/images/DBGrid/First.gif" 
                                                                    imgPrevious="../includes/images/DBGrid/Previous.gif" 
                                                                    imgNext="../includes/images/DBGrid/Next.gif" 
                                                                    imgLast="../includes/images/DBGrid/Last.gif" 
                                                                    addImage="../includes/images/DBGrid/Add.png" 
                                                                    addAction="<%=consultantQuery%>" scriptFunction="getConsultSkill" />
                                                     <grd:gridsorter sortColumn="<%=strSortCol%>" 
                                                                     sortAscending="<%=blnSortAsc%>" />
                                                     
                                                     <grd:imagecolumn  headerText="Edit" 
                                                                       width="3"  HAlign="center" 
                                                                       imageSrc="../includes/images/DBGrid/newEdit_17x18.png" 
                                                                       linkUrl="getConsultantSkills.action?id={id}" 
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
                                                 if (objCnn != null)
                                                 objCnn.close();
                                                 } catch (SQLException SQLExIgnore) {
                                                 }
                                                 if (objCnn != null) 
                                                 objCnn.close();
                                                 objCnn= null;
                                                 %>
                                             </td>
                                         </tr>
                                     </table>
                                 </form>
                             </div>
                             <%-- </sx:div > --%>
                             <!--//END TAB : -->
                             <!--//Start Resumes Tab : -->
                                
                             <%--  <sx:div id="resumeAttachmentListTab" name="resumeAttachmentListTab"  label="Resume Attachment"  > --%>
                          
                             <div id="resumeAttachmentListTab" class="tabcontent"  >
                                 <form name="frmDBAttachGrid" action="" method="post">
                                     
                                     <table cellpadding="2" cellspacing="0" border="0" width="100%">  
                                         <tr align="right">
                                             <td class="headerText">
                                                 <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">   
                                             </td>
                                         </tr>    
                                         <tr>
                                             <td align="center">
                                                 <%
                                                 intCurr = 1;
                                                 intSortOrd = 0;
                                                 strTmp = null;
                                                 strSQL = null;
                                                 strSortCol = null;
                                                 strSortOrd = "ASC";
                                                 blnSortAsc = true;
                                                 strSQL = "select * from tblRecAttachments where ObjectType='A' and ObjectId="+Integer.parseInt(custId);
                                                 objCnn    = null;
                                                 
                                                 objDrvCls = null;
                                                 
                                                 objDrvCls = Class.forName("com.mysql.jdbc.Driver");
                                                 objCnn = ConnectionProvider.getInstance().getConnection();
                                                 if (objDrvCls != null) objDrvCls = null;
                                                 strTmp = request.getParameter("txtAttachCurr");
                                                 
                                                 try {
                                                 if (strTmp != null)
                                                 intCurr = Integer.parseInt(strTmp);
                                                 
                                                 } catch (NumberFormatException NFEx) {
                                                 }
                                                 strSortCol = request.getParameter("txtAttachSortCol");
                                                 
                                                 strSortOrd = request.getParameter("txtAttachSortAsc");
                                                 
                                                 if (strSortCol == null) strSortCol = "eno";
                                                 if (strSortOrd == null) strSortOrd = "ASC";
                                                 blnSortAsc = (strSortOrd.equals("ASC"));
                                                 //out.println(strSQL);
                                                 %>
                                                 <grd:dbgrid  id="tblAttach" 
                                                              name="tblAttach" 
                                                              width="100"
                                                              pageSize="10" 
                                                              currentPage="<%=intCurr%>"
                                                              border="0" 
                                                              cellSpacing="1" 
                                                              cellPadding="2" 
                                                              dataMember="<%=strSQL%>" 
                                                              dataSource="<%=objCnn%>" 
                                                              cssClass="gridTable">
                                                     <grd:gridpager imgFirst="../includes/images/DBGrid/First.gif"
                                                                    imgPrevious="../includes/images/DBGrid/Previous.gif" 
                                                                    imgNext="../includes/images/DBGrid/Next.gif"
                                                                    imgLast="../includes/images/DBGrid/Last.gif"  
                                                                    addImage="../includes/images/DBGrid/Add.png" 
                                                                    addAction="./ConsultantResume.jsp" scriptFunction="getResumeAttach" />
                                                     <grd:textcolumn dataField="AttachmentName"
                                                                     headerText="Attachment File Name" 
                                                                     width="10" 
                                                                     HAlign="center"/>
                                                     <grd:textcolumn dataField="AttachmentLocation"
                                                                     headerText="Attachment File Location" 
                                                                     width="18" 
                                                                     HAlign="center"/>                
                                                     <grd:datecolumn dataField="DateUploaded"
                                                                     headerText="Date Of Attachment"  
                                                                     dataFormat="MM/dd/yyyy hh:mm:ss" 
                                                                     width="8"  
                                                                     HAlign="center"/>
                                                     <grd:imagecolumn  headerText="DownLoad" 
                                                                       width="4"  
                                                                       HAlign="center" 
                                                                       imageSrc="../includes/images/download_11x10.jpg" 
                                                                       linkUrl="getAttachment.action?id={Id}" 
                                                                       imageBorder="0" 
                                                                       imageWidth="11" 
                                                                       imageHeight="10" 
                                                                       alterText="download" />   
                                                     <%--                   
                                                     <grd:imagecolumn  headerText="Delete" 
                                                                       width="3" 
                                                                       HAlign="center" 
                                                                       imageSrc="../includes/images/DBGrid/Delete.png"
                                                                       linkUrl="deleteConsultantAttachment.action?id={Id}" 
                                                                       imageBorder="0" 
                                                                       imageWidth="51" 
                                                                       imageHeight="20" 
                                                                       alterText="Click to edit" />
                                                     --%>
                                                 </grd:dbgrid>
                                                 <input TYPE="hidden" NAME="txtAttachCurr" VALUE="<%=intCurr%>">
                                                 <input TYPE="hidden" NAME="txtAttachSortCol" VALUE="<%=strSortCol%>">
                                                 <input TYPE="hidden" NAME="txtAttachSortAsc" VALUE="<%=strSortOrd%>">
                                                 <input TYPE="hidden" NAME="txtSkillCurr" VALUE="">
                                                 <input TYPE="hidden" NAME="txtSubmittCurr" VALUE="">
                                                 <input TYPE="hidden" NAME="txtTechCurr" VALUE="">
                                                 <input TYPE="hidden" NAME="submitFrom" VALUE="dbGrid">
                                                 
                                                 <%
                                                 try {
                                                 if (objCnn != null)
                                                 objCnn.close();
                                                 } catch (SQLException SQLExIgnore) {
                                                 }
                                                 if (objCnn != null) 
                                                 objCnn.close();
                                                 objCnn= null;
                                                 %>
                                             </td>
                                         </tr>
                                     </table>
                                 </form>
                             </div>
                             <%-- </sx:div > --%>
                             <!--//End Resumes Tab : -->
                                
                             <!--//Start Resumes Submit Tab : -->
                             <%-- <sx:div id="resumeSubmittTab"  label=" Resume Submit "  > --%>
                             <div id="resumeSubmittTab" class="tabcontent"  >
                                 <form name="frmDBSubmittGrid" action="" method="post">
                                     <table cellpadding="2" cellspacing="0" border="0" width="100%">                                               
                                         <tr align="right">
                                             <td class="headerText">
                                                 <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0"> 
                                             </td>
                                         </tr>    
                                         <tr>
                                             <td align="center">
                                                 <%
                                                 intCurr = 1;
                                                 intSortOrd = 0;
                                                 strTmp = null;
                                                 strSQL = null;
                                                 strSortCol = null;
                                                 strSortOrd = "ASC";
                                                 blnSortAsc = true;
                                                 strSQL = "select * from tblRecAttachments where ObjectType='S' and ObjectId="+Integer.parseInt(custId);
                                                 objCnn    = null;
                                                 objDrvCls = null;
                                                 objDrvCls = Class.forName("com.mysql.jdbc.Driver");
                                                 objCnn=ConnectionProvider.getInstance().getConnection();
                                                 if (objDrvCls != null) objDrvCls = null;
                                                 strTmp = request.getParameter("txtSubmittCurr");
                                                 
                                                 try {
                                                 if (strTmp != null)
                                                 intCurr = Integer.parseInt(strTmp);
                                                 
                                                 } catch (NumberFormatException NFEx) {
                                                 }
                                                 strSortCol = request.getParameter("txtSubmittSortCol");
                                                 
                                                 strSortOrd = request.getParameter("txtSubmittSortAsc");
                                                 
                                                 if (strSortCol == null) strSortCol = "eno";
                                                 if (strSortOrd == null) strSortOrd = "ASC";
                                                 blnSortAsc = (strSortOrd.equals("ASC"));
                                                 %>
                                                 <grd:dbgrid  id="tblSubmitt"
                                                              name="tblSubmitt" 
                                                              width="100"
                                                              pageSize="10" 
                                                              currentPage="<%=intCurr%>" 
                                                              border="0"
                                                              cellSpacing="1" 
                                                              cellPadding="2" 
                                                              dataMember="<%=strSQL%>" 
                                                              dataSource="<%=objCnn%>" 
                                                              cssClass="gridTable">
                                                     <grd:gridpager imgFirst="../includes/images/DBGrid/First.gif"
                                                                    imgPrevious="../includes/images/DBGrid/Previous.gif" 
                                                                    imgNext="../includes/images/DBGrid/Next.gif" 
                                                                    imgLast="../includes/images/DBGrid/Last.gif"  
                                                                    addImage="../includes/images/DBGrid/Add.png" 
                                                                    addAction="./ConsultantSubmittals.jsp" scriptFunction="getResumeSubmitt"/>
                                                     
                                                     <grd:textcolumn dataField="AttachmentName" 
                                                                     headerText="Submitt File Name" 
                                                                     width="10"
                                                                     HAlign="center"/>
                                                     <grd:textcolumn dataField="AttachmentLocation" 
                                                                     headerText="Submitt File Location" 
                                                                     width="20" 
                                                                     HAlign="center"/>                
                                                     <grd:datecolumn dataField="DateUploaded" 
                                                                     headerText="Date Of Submitt"  
                                                                     dataFormat="MM/dd/yyyy hh:mm:ss" 
                                                                     width="10"  
                                                                     HAlign="center"/>
                                                     <grd:imagecolumn  headerText="DownLoad" 
                                                                       width="4"
                                                                       HAlign="center" 
                                                                       imageSrc="../includes/images/download_11x10.jpg"
                                                                       linkUrl="getAttachment.action?id={Id}" 
                                                                       imageBorder="0" 
                                                                       imageWidth="11"
                                                                       imageHeight="10" 
                                                                       alterText="download" />   
                                                     <%--
                                                     <grd:imagecolumn  headerText="Delete" 
                                                                       width="4" 
                                                                       HAlign="center"  
                                                                       imageSrc="../includes/images/DBGrid/Delete.png"
                                                                       linkUrl="deleteConsultantAttachment.action?Id={Id}" 
                                                                       imageBorder="0"
                                                                       imageWidth="51" 
                                                                       imageHeight="20" 
                                                                       alterText="Click to edit" />
                                                    --%>                   
                                                 </grd:dbgrid>
                                                 <input TYPE="hidden" NAME="txtSubmittCurr" VALUE="<%=intCurr%>">
                                                 <input TYPE="hidden" NAME="txtSubmittSortCol" VALUE="<%=strSortCol%>">
                                                 <input TYPE="hidden" NAME="txtSubmittSortAsc" VALUE="<%=strSortOrd%>">
                                                 <input TYPE="hidden" NAME="txtAttachCurr" VALUE="">
                                                 <input TYPE="hidden" NAME="txtSkillCurr" VALUE="">
                                                 <input TYPE="hidden" NAME="txtTechCurr" VALUE="">
                                                 <input TYPE="hidden" NAME="submitFrom" VALUE="dbGrid"> 
                                                 <%
                                                 try {
                                                 if (objCnn != null)
                                                 objCnn.close();
                                                 } catch (SQLException SQLExIgnore) {
                                                 }
                                                 if (objCnn != null)
                                                 objCnn.close();
                                                 objCnn= null;
                                                 %>
                                             </td>
                                         </tr>
                                     </table>
                                 </form>
                             </div>
                             <%--  </sx:div > --%>
                             <!--//End Resumes SubmitTab : -->
                                
                             <!--//Start Tech Review Tab : -->
                             <%-- <sx:div id="techReviewTab"  label=" Tech Reviews "  > --%>
                             <div id="techReviewTab" class="tabcontent"  >
                                 <form name="frmDBTechGrid" action="" method="post">
                                     <table cellpadding="2" cellspacing="0" border="0" width="100%">             
                                         <tr align="right">
                                             <td class="headerText">
                                                 <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">                                                                                                                
                                             </td>
                                         </tr>    
                                         <tr>
                                             <td align="center">
                                                 <%
                                                 intCurr = 1;
                                                 intSortOrd = 0;
                                                 strTmp = null;
                                                 strSQL = null;
                                                 strSortCol = null;
                                                 strSortOrd = "ASC";
                                                 blnSortAsc = true;
                                                 strSQL = "select * from tblRecAttachments where ObjectType='T' and ObjectId="+Integer.parseInt(custId);
                                                 objCnn    = null;
                                                 objDrvCls = null;
                                                 objDrvCls = Class.forName("com.mysql.jdbc.Driver");
                                                 objCnn=ConnectionProvider.getInstance().getConnection();
                                                 if (objDrvCls != null) objDrvCls = null;
                                                 strTmp = request.getParameter("txtTechCurr");
                                                 
                                                 try {
                                                 if (strTmp != null)
                                                 intCurr = Integer.parseInt(strTmp);
                                                 
                                                 } catch (NumberFormatException NFEx) {
                                                 }
                                                 strSortCol = request.getParameter("txtTechSortCol");
                                                 
                                                 strSortOrd = request.getParameter("txtTechSortAsc");
                                                 
                                                 if (strSortCol == null) strSortCol = "eno";
                                                 if (strSortOrd == null) strSortOrd = "ASC";
                                                 blnSortAsc = (strSortOrd.equals("ASC"));
                                                 %>
                                                 <grd:dbgrid  id="tblTechreview"
                                                              name="tblTechreview"  
                                                              width="100"
                                                              pageSize="10" 
                                                              currentPage="<%=intCurr%>"
                                                              border="0"
                                                              cellSpacing="1" 
                                                              cellPadding="2" 
                                                              dataMember="<%=strSQL%>" 
                                                              dataSource="<%=objCnn%>"
                                                              cssClass="gridTable">
                                                     <grd:gridpager imgFirst="../includes/images/DBGrid/First.gif" 
                                                                    imgPrevious="../includes/images/DBGrid/Previous.gif" 
                                                                    imgNext="../includes/images/DBGrid/Next.gif"
                                                                    imgLast="../includes/images/DBGrid/Last.gif" 
                                                                    addImage="../includes/images/DBGrid/Add.png"
                                                                    addAction="./ConsultantsTechReviews.jsp" scriptFunction="getTechReview"/>
                                                     <grd:textcolumn dataField="AttachmentName"
                                                                     headerText="TechReview File Name" 
                                                                     width="10" 
                                                                     HAlign="center"/>
                                                     <grd:textcolumn dataField="AttachmentLocation"
                                                                     headerText="TechReview File Location" 
                                                                     width="18" 
                                                                     HAlign="center"/>                
                                                     <grd:datecolumn dataField="DateUploaded"
                                                                     headerText="Date Of Submitt" 
                                                                     dataFormat="MM/dd/yyyy hh:mm:ss" 
                                                                     width="10"  
                                                                     HAlign="center"/>
                                                     <grd:imagecolumn  headerText="DownLoad"
                                                                       width="4" 
                                                                       HAlign="center" 
                                                                       imageSrc="../includes/images/download_11x10.jpg"
                                                                       linkUrl="getAttachment.action?id={Id}" 
                                                                       imageBorder="0"
                                                                       imageWidth="11" 
                                                                       imageHeight="10" 
                                                                       alterText="download" />    
                                                     
                                                     <%--
                                                     <grd:imagecolumn  headerText="Delete"
                                                                       width="3" 
                                                                       HAlign="center" 
                                                                       imageSrc="../includes/images/DBGrid/Delete.png"
                                                                       linkUrl="deleteConsultantAttachment.action?id={Id}" 
                                                                       imageBorder="0"
                                                                       imageWidth="51"
                                                                       imageHeight="20"
                                                                       alterText="Click to edit" />
                                                     --%>
                                                 </grd:dbgrid>
                                                 <input TYPE="hidden" NAME="txtTechCurr" VALUE="<%=intCurr%>">
                                                 <input TYPE="hidden" NAME="txtTechSortCol" VALUE="<%=strSortCol%>">
                                                 <input TYPE="hidden" NAME="txtTechSortAsc" VALUE="<%=strSortOrd%>">
                                                 
                                                 <input TYPE="hidden" NAME="txtAttachCurr" VALUE="">
                                                 <input TYPE="hidden" NAME="txtSubmittCurr" VALUE="">
                                                 <input TYPE="hidden" NAME="txtSkillCurr" VALUE="">
                                                 <input TYPE="hidden" NAME="submitFrom" VALUE="dbGrid">
                                                 <%
                                                 try {
                                                 if (objCnn != null)
                                                 objCnn.close();
                                                 } catch (SQLException SQLExIgnore) {
                                                 }
                                                 if (objCnn != null)
                                                 objCnn.close();
                                                 objCnn= null;
                                                 %>
                                             </td>
                                         </tr>
                                     </table>
                                 </form>
                             </div>
                         </div>
                         
                         <script type="text/javascript">

                                                    var countries=new ddtabcontent("consultantotherDetailsTabs")
                                                    countries.setpersist(false)
                                                    countries.setselectedClassTarget("link") //"link" or "linkparent"
                                                    countries.init()

                        </script>
                           <%-- </sx:div > --%>
                            <!--//End Tech Review Tab : -->
                       <%-- </sx:tabbedpanel> --%>
                        <!--//END DATA COLUMN : Coloumn for Screen Content-->
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