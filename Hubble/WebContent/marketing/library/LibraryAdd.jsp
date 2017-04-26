<%-- 
    Document   : LibraryAdd
    Created on : Aug 11, 2015, 12:59:22 AM
    Author     : miracle
--%>

<%@page contentType="text/html;charset=UTF-8" errorPage="../../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.freeware.gridtag.*" %> 
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="com.mss.mirage.util.*"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ page import="java.sql.*,java.lang.*,java.util.*" %>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd" %>

<html>
    <head>

        <title>Hubble Organization Portal :: Library</title>

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/recruitment/ConsultantResumeClientValidation.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/marketing/ResourceManagement.js?ver=1.3"/>"></script>  
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script> 
    </head>

  <!--  <body class="bodyGeneral" onload="showCustomerTag();" oncontextmenu="return false;"> -->
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

                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->

                            <!--//START DATA COLUMN : Coloumn for Screen Content-->
                            <td width="850px" class="cellBorder" valign="top" style="padding-left:10px;padding-top:5px;">
                                <!--//START TABBED PANNEL : -->

                                <%--<s:property value="%{consultantName}"/>--%>
                                <ul id="resumeAttachmentDetailsTabs" class="shadetabs" >
                                    <s:if test="%{currentAction=='doAddLibrary'}">
                                        <li ><a href="#" class="selected" rel="libraryAdd"> Library Add </a></li> 

                                    </s:if>
                                    <s:else>
                                        <li ><a href="#" class="selected" rel="libraryAdd"> Library Edit </a></li>
                                    </s:else>



                                </ul>

                                <%-- <sx:tabbedpanel id="resumeAttachmentDetails" cssStyle="width: 840px; height: 310px;padding-left:10px;padding-top:5px;" doLayout="true"> --%>

                                <!--//START TAB : -->
                                <%-- <sx:div id="resumeAttachmentTab"  label="Resume Attachment Details"  > --%>

                                <div  style="border:1px solid gray; width:830px;height: 500px; overflow:auto; margin-bottom: 1em;">



                                    <div id="libraryAdd" class="tabcontent"  >

                                        <s:form name="frmLibrary" action="%{currentAction}" theme="simple" method="POST" enctype="multipart/form-data" onsubmit="return libraryFieldsValidate();">
                                            <table cellpadding="2" cellspacing="0" border="0" width="100%">                                                
                                                <tr align="right">
                                                    <s:hidden name="libraryId" id="libraryId" value="%{libraryId}"/>
                                                    <td class="headerText" colspan="6">
                                                        <%
                                                            if (request.getAttribute("resultMessage") != null) {
                                                                out.println(request.getAttribute("resultMessage").toString());
                                                            }

                                                        %>
                                                         <a href="<s:url value="libraryManagementSearch.action"/>" style="align:center;">
                                            <img alt="Back to List"
                                                 src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                 width="66px" 
                                                 height="19px"
                                             border="0" align="bottom"></a>
                                        &nbsp;&nbsp;
                                                        <s:if test="%{currentAction=='doAddLibrary'}">
                                                            <s:submit cssClass="buttonBg" value="Save"/>
                                                        </s:if>
                                                        <s:else>
                                                            <s:submit cssClass="buttonBg" value="Update"/>

                                                        </s:else>

<s:hidden name="curentActionId" id="curentActionId" value="%{currentAction}"/>


                                                    </td>
                                                </tr>    

                                                <tr>
                                                    <td class="fieldLabel">Type :<FONT color="red"  ><em>*</em></FONT></td>
                                                    <td colspan="3">
                                                        <s:if test="%{currentAction=='doAddLibrary'}">
                                                           <%-- <s:select id="resourceType" name="resourceType" list="{'Case Study','Technical Article','Product Brief','Solution Brief','Presentation'}" cssClass="inputSelect" headerKey="" headerValue="--Please Select--" onchange="hideBodyContent();"/> --%>
                                                            <s:select id="resourceType" name="resourceType" list="{'Case Study','Technical Article','Solution Brief','Presentation'}" cssClass="inputSelect" headerKey="" headerValue="--Please Select--" onchange="showCustomerTag();hideBodyContent();"/>
                                                        </s:if><s:else>
                                                            
                                                            <s:select name="resourceType" list="{'Case Study','Technical Article','Solution Brief','Presentation'}" cssClass="inputSelect" headerKey="" headerValue="--Please Select--" onchange="hideBodyContent();" disabled="true"/>
                                                            <s:hidden name="resourceType" id="resourceType" value="%{resourceType}"/>
                                                        </s:else>
                                                        
                                                    
                                                    </td>
                                                </tr>


                                                <tr>
                                                    <%--   <td class="fieldLabel" >Resource&nbsp;Title:<FONT color="red"  ><em>*</em></FONT> </td> --%>
                                                    <td class="fieldLabel" >Title:<FONT color="red"  ><em>*</em></FONT> </td>
                                                    <td colspan="3"><s:textfield name="resourceTitle" id="resourceTitle" cssClass="inputTextBlueLargeAccount" cssStyle="width: 589px; height: 20px" onChange="pageTitleCheck();"/><div id="load"> </div></td>

                                                </tr>   
                                               


                                                <%--     <tr>
                                                    
                                                        <td class="fieldLabel" >Library&nbsp;Title:<FONT color="red"  ><em>*</em></FONT> </td>
                                                        <td colspan="3"><s:textfield name="bodyTitle" id="bodyTitle" cssClass="inputTextBlueLargeAccount" cssStyle="width: 589px; height: 20px"/></td>
                                                     
                                                          </tr>    --%>
                                                <tr id="fileNameTr">
                                                    <s:if test="%{currentAction=='doAddLibrary'}">
                                                         <td class="fieldLabel" >File&nbsp;Name:<FONT color="red"  ><em>*</em></FONT> </td>
                                                         <td colspan="3">
                                                             <s:textfield name="phpFileName" id="phpFileName" cssClass="inputTextBlueLargeAccount" cssStyle="width: 589px; height: 20px" onchange="validateFileName();"/>
                                                         </td>
                                                    </s:if><s:else>
                                                        <s:if test="%{resourceType=='Presentation'}">
                                                            <s:hidden name="phpFileName" id="phpFileName" />
                                                        </s:if><s:else>
                                                             <td class="fieldLabel" >File&nbsp;Name:<FONT color="red"  ><em>*</em></FONT> </td>
                                                               <td colspan="3"><s:textfield name="phpFileName" id="phpFileName" cssClass="inputTextBlueLargeAccount" cssStyle="width: 589px; height: 20px" readonly="true"/></td>
                                                        </s:else>
                                                    </s:else>
                                                  

                                                </tr>  
                                                <tr id="customerNameTr" style="display: none;">
                                    <td class="fieldLabel" >Customer&nbsp;Name<FONT color="red"  ><em>*</em></FONT> </td>
                                    <td colspan="3"><s:textfield name="customerName" id="customerName" cssClass="inputTextBlueLargeAccount" cssStyle="width: 589px; height: 20px" onChange="libraryFieldLengthValidator(this);"/></td>

                                    </tr>  

                                                <tr>

                                                    <td class="fieldLabel">Date&nbsp;Of&nbsp;Publish:<FONT color="red"  ><em>*</em></FONT></td>
                                                    <td colspan="3"><s:textfield name="dateOfPublish" id="dateOfPublish" cssClass="inputTextBlue" onchange="validateTimestamp(this);"/>
                                                        <a href="javascript:cal3.popup();">
                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif"
                                                                 width="20" height="20" border="0"></a>
                                                    </td>

                                                </tr> 
                                                <tr>
                                                    <td class="fieldLabel">Status :<FONT color="red"  ><em>*</em></FONT></td>
                                                    <td ><s:select id="resourceStatus" name="resourceStatus" list="{'Active','InActive'}" cssClass="inputSelect"/></td>
                                                    <%--  <td class="fieldLabel">Do&nbsp;you&nbsp;want&nbsp;to&nbsp;gate&nbsp;the&nbsp;content</td>
                                                                         <td ><s:select id="gateContent" name="gateContent" list="{'YES','NO'}" cssClass="inputSelect" /></td>--%>
                                                    <td class="fieldLabel">Industry:<FONT color="red"  ><em>*</em></FONT></td>
                                                
                                                  <s:if test="%{currentAction=='doAddLibrary'}">
                                        <td width="29%"><s:select id="resourceIndustry" name="resourceIndustry" list="{'Retail, Logistics and Supply Chain','Manufacturing and Automotive','Healthcare and Pharmacy','Finance, Banking and Insurance','Energy and Utilities','Cross Industry'}" cssClass="inputSelect" headerKey="" headerValue="--Please Select--"/></td>
                                    </s:if><s:else>
                                        <td> 
                                        <lable id="libraryEdit" style="font-weight: bold;"><font size="2px" color="green"><s:property value="%{resourceIndustry}"/></font> </lable>
                                        <s:hidden name="resourceIndustry" id="resourceIndustry" value="%{resourceIndustry}"/></td>
                                    </s:else>
                                                    
                                                </tr>

                                                <tr>
                                                    <td class="fieldLabel">Primary&nbsp;Track:<FONT color="red"  ><em>*</em></FONT></td>
                                                    <td ><s:select id="resourcePrimaryTrack" name="resourcePrimaryTrack" list="trackNamesMap" cssClass="inputSelect" headerKey="" headerValue="--Please Select--"/></td>
                                                    <td class="fieldLabel">Secondary&nbsp;Track:</td>
                                                    <td ><s:select id="resourceSecondaryTrack" name="resourceSecondaryTrack" list="trackNamesMap" cssClass="inputSelect" headerKey="" headerValue="--Please Select--"/></td>

                                                </tr>   
                                                <%-- <tr>
                                                      <td class="fieldLabel">Industry:</td>
                            <td ><s:select id="resourceIndustry" name="resourceIndustry" list="{'Retail','Manufacturing','Health Care','Logistics','Finance and Insurance','Application Development'}" cssClass="inputSelect" headerKey="" headerValue="--Please Select--"/></td>
                            
                                                 </tr> --%>
                                                 <tr>
                                                    <td valign="top" align="right">
                                                        <s:if test="%{currentAction=='doAddLibrary'}">
                                                            <s:checkbox name="gatedContent" id="gatedContent"
                                                                    value="%{gatedContent}" 
                                                                    onchange="hideResourceDepotContent();"/> 
                                                        </s:if><s:else>
                                                            <s:checkbox name="gatedContent" 
                                                                    value="%{gatedContent}" 
                                                                    onchange="hideResourceDepotContent();" disabled="true"/> 
                                                             <s:hidden name="gatedContent" id="gatedContent" value="%{gatedContent}"/>
                                                        </s:else>
                                                        


                                                    </td>
                                                    <td class="fieldLabel" colspan="3">
                                                        <div style="text-align: justify;text-justify: inter-word; width: 90%">: Do you want to gate the content?</div>

                                                    </td>
                                                    <%--   <td class="fieldLabel" >Resource&nbsp;Title:<FONT color="red"  ><em>*</em></FONT> </td> --%>


                                                </tr>  
                                                <s:if test="%{gatedContent}">
                                                     <tr id="resourceDescriptionTr">
                                                    <td class="fieldLabel" valign="top">Resource&nbsp;Depot&nbsp;Description:<FONT color="red"  ><em>*</em></FONT></td>
                                                    <td colspan="3" valign="top"><s:textarea rows="5" cols="65" name="resourceDescription" cssClass="inputTextareaOverlay1" onchange="checkDoubleQuotes(this);" id="resourceDescription" style="width: 589px; height: 150px"/>
                                                </td>
                                                </tr>
                                                </s:if><s:else>
                                                    <tr id="resourceDescriptionTr" style="display: none">
                                                    <td class="fieldLabel" valign="top">Resource&nbsp;Depot&nbsp;Description:<FONT color="red"  ><em>*</em></FONT></td>
                                                    <td colspan="3" valign="top"><s:textarea rows="5" cols="65" name="resourceDescription" cssClass="inputTextareaOverlay1" onchange="checkDoubleQuotes(this);" id="resourceDescription" style="width: 589px; height: 150px"/>
                                                </td>
                                                </tr>
                                                </s:else>
                                               
                                                <s:if test="%{resourceType=='Presentation'}">
                                                    <s:hidden name="breadCrumbHeading" id="breadCrumbHeading" />
                                                    <s:hidden name="webPageCreationdetails" cssClass="inputTextareaOverlay1" id="webPageCreationdetails" />
                                                </s:if><s:else>
                                                     <tr id="resourceBreadcrumbNameTr">
                                                    <td class="fieldLabel" >Breadcrumb&nbsp;Name:<FONT color="red"  ><em>*</em></FONT> </td>
                                                    <td colspan="3"><s:textfield name="breadCrumbHeading" id="breadCrumbHeading" cssClass="inputTextBlueLargeAccount"  cssStyle="width: 589px; height: 20px"/></td>
                                                </tr>   
                                                <tr id="resourcePageDescriptionTr">
                                                  
                                                    <td class="fieldLabel" valign="top">Body&nbsp;Content:<FONT color="red"  ><em>*</em></FONT></td>
                                                    <td colspan="3" valign="top"><s:textarea rows="10" cols="65" name="webPageCreationdetails" cssClass="inputTextareaOverlay1" onchange="checkDoubleQuotes(this);" id="webPageCreationdetails" style="width: 589px; height: 150px"/>

                                                    </td>
                                                </tr>
                                                </s:else>
                                               

                                                <tr id="resourceBodyDescriptionTr" style="display: none">
                                                    <td class="fieldLabel" valign="top">Body&nbsp;Content2:<FONT color="red"  ><em>*</em></FONT></td>
                                                    <td colspan="3" valign="top"><s:textarea rows="10" cols="65" name="bodyContent" cssClass="inputTextareaOverlay1" onchange="checkDoubleQuotes(this);" id="bodyContent" style="width: 589px; height: 150px"/>


                                                    </td>
                                                </tr>
                                          <%--      <tr>
                                                    <td class="fieldLabel">Primary Author :</td>
                                                    <td ><s:select id="primaryAuthor" name="primaryAuthor" value="%{primaryAuthor}" list="speakersMapExceptEventSpeakerMap" cssClass="inputSelect" headerKey="" headerValue="--Please Select--"/></td>
                                                   
                                                    <td class="fieldLabel">Author2:</td>
                                                    <td ><s:select id="author2" name="author2" value="%{author2}" list="speakersMapExceptEventSpeakerMap" cssClass="inputSelect" headerKey="" headerValue="--Please Select--"/></td>
                                                </tr>
                                                <tr>
                                                    <td class="fieldLabel">Author3:</td>
                                                    <td ><s:select id="author3" name="author3" value="%{author3}" list="speakersMapExceptEventSpeakerMap" cssClass="inputSelect" headerKey="" headerValue="--Please Select--"/></td>



                                                </tr> --%>

                                                <s:if test="%{currentAction=='doAddLibrary'}"> 

                                                    <tr><td class="fieldLabel">

                                                            <%--    <s:checkbox name="isResourceDownloadable" id="isResourceDownloadable" /> --%>
                                                            <s:checkbox  name="isResourceDownloadable" id="isResourceDownloadable" value="%{isResourceDownloadable}" onchange="hideUploadFile();"/>

                                                        </td>
                                                        <td class="fieldLabelLeft">Does this resource have downloadable content?</td>


                                                    </tr>

                                                    <tr id="uploadTr"> 

                                                        <td class="fieldLabel">Upload File :<FONT color="red"  ><em>*</em></FONT></td>
                                                        <td colspan="3" ><s:file name="libraryFile" label="file" cssClass="inputTextarea" id="libraryFile" onchange="validateFileSize(this);"/></td> 
                                                    </tr> 
                                                </s:if>   <s:else>
                                                    <s:if test="%{libraryFileFileName!=''}"> 
                                                    <tr id="uploadTr"> 

                                                        <td class="fieldLabel">Attachment&nbsp;:</td>
                                                        <td colspan="3">
                                                        
                                                        <a class="navigationText" href="javascript:downloadDoc();" id="downloadLink">

                                                            <span id="downloadFile"><s:property value="libraryFileFileName"/></span>
                                                            
                                                        </a>
                                                        </td>
                                                    </tr>
                                                     </s:if>  
                                                    </s:else>


                                            </table>
                                        </s:form>
                                    </div>
                                </div>

                                <script type="text/JavaScript">
                                           

                                    var cal3 = new CalendarTime(document.forms['frmLibrary'].elements['dateOfPublish']);
                                    cal3.year_scroll = true;
                                    cal3.time_comp = true;
                                </script> 
                                <script type="text/javascript">

                                    var countries=new ddtabcontent("resumeAttachmentDetailsTabs")
                                    countries.setpersist(false)
                                    countries.setselectedClassTarget("link") //"link" or "linkparent"
                                    countries.init()

                                </script>
                                <%-- </sx:div > --%>

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
        <script type="text/javascript">
		$(window).load(function(){
		showCustomerTag();

		});
		</script>
    </body>
</html>
