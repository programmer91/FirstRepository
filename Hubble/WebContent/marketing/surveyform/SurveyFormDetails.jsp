<%-- 
    Document   : SurveyFormDetails
    Created on : Aug 26, 2015, 3:03:03 PM
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

        <title>Hubble Organization Portal :: Survey&nbsp;Form&nbsp;Details</title>

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/recruitment/ConsultantResumeClientValidation.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script> 
        <script type="text/javascript" src="<s:url value="/includes/javascripts/marketing/SurveyFormScripts.js?version=2.0"/>"></script>   
    </head>

    <!-- <body class="bodyGeneral" onload="customizeTextMessage();internalType();"> -->
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
                                <ul id="surveyDetailsTabs" class="shadetabs" >
                                    <s:if test="%{currentAction=='doAddSurveyForm'}">
                                        <li ><a href="#" class="selected" rel="libraryAdd"> Survey&nbsp;Form&nbsp;Add </a></li> 

                                    </s:if>
                                    <s:else>
                                        <li ><a href="#" class="selected" rel="libraryAdd">Survey&nbsp;Form&nbsp;Edit </a></li>
                                    </s:else>



                                </ul>

                                <%-- <sx:tabbedpanel id="resumeAttachmentDetails" cssStyle="width: 840px; height: 310px;padding-left:10px;padding-top:5px;" doLayout="true"> --%>

                                <!--//START TAB : -->
                                <%-- <sx:div id="resumeAttachmentTab"  label="Resume Attachment Details"  > --%>

                                <div  style="border:1px solid gray; width:830px;height: 500px; overflow:auto; margin-bottom: 1em;">



                                    <div id="libraryAdd" class="tabcontent"  >

                                        <s:form name="frmSurveyForm" action="%{currentAction}" theme="simple" method="POST" enctype="multipart/form-data" onsubmit="return surveyFieldsValidate();">
                                            <table cellpadding="2" cellspacing="0" border="0" width="100%">                                                
                                                <tr align="right">
                                                    <s:hidden name="surveyId" id="surveyId" value="%{surveyId}"/>
                                                    <td class="headerText" colspan="6">
                                                        <%
                                                            if (request.getAttribute("resultMessage") != null) {
                                                                out.println(request.getAttribute("resultMessage").toString());
                                                            }

                                                        %>
                                                         <a href="<s:url value="surveyFormListSearch.action"/>" style="align:center;">
                                            <img alt="Back to List"
                                                 src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                 width="66px" 
                                                 height="19px"
                                             border="0" align="bottom"></a>
                                        &nbsp;&nbsp;
                                                        <s:if test="%{currentAction=='doAddSurveyForm'}">
                                                            <s:submit cssClass="buttonBg" value="Save"/>
                                                        </s:if>
                                                        <s:else>
                                                            <s:if test="%{currStatus=='Active'}">
                                                                <s:submit cssClass="buttonBg" value="Update"/>
                                                            </s:if>
                                                            

                                                        </s:else>


                                        <s:hidden name="currentDate" id="currentDate" value="%{currentDate}"/>

                                                    </td>
                                                </tr>    

                                                <tr>
                                                    <td class="fieldLabel">Type :<FONT color="red"  ><em>*</em></FONT></td>
                                                    <td>
                                                      <%--  <s:if test="%{currentAction=='doAddSurveyForm'}"> --%>
                                                            <s:select id="surveyType" name="surveyType" list="#@java.util.LinkedHashMap@{'I':'Internal','E':'External'}" cssClass="inputSelect" headerKey="" headerValue="--Please Select--" onchange="internalType()"/>
                                                       <%-- </s:if><s:else>
                                                            
                                                            <s:select name="surveyType" list="#@java.util.LinkedHashMap@{'I':'Internal','E':'External'}" cssClass="inputSelect" headerKey="" headerValue="--Please Select--"  disabled="true"/>
                                                            <s:hidden name="surveyType" id="surveyType" value="%{surveyType}"/>
                                                        </s:else> --%>
                                                        
                                                    
                                                    </td>
                                               
                                                    <td class="fieldLabel">Status:<FONT color="red"  ><em>*</em></FONT></td>
                                                    <td> <s:select id="currStatus" name="currStatus" list="{'Active','InActive','Published'}" cssClass="inputSelect"  />
                                                   </td>
                                                </tr>

                                                <tr>
                                                    <%--   <td class="fieldLabel" >Resource&nbsp;Title:<FONT color="red"  ><em>*</em></FONT> </td> --%>
                                                    <td class="fieldLabel" >Title:<FONT color="red"  ><em>*</em></FONT> </td>
                                                    <td colspan="3"><s:textfield name="surveyTitle" id="surveyTitle" cssClass="inputTextBlueLargeAccount" cssStyle="width: 589px; height: 20px" onchange="surveyFormFieldsValidator(this);"/></td>

                                                </tr>   
                                            
                                              

                                                <tr>

                                                    <td class="fieldLabel">Expiry&nbsp;Date:<FONT color="red"  ><em>*</em></FONT></td>
                                                    <td colspan="3"><s:textfield name="expiryDate" id="expiryDate" cssClass="inputTextBlue" onchange="validateTimestamp(this);"/>
                                                        <a href="javascript:cal3.popup();">
                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif"
                                                                 width="20" height="20" border="0"></a>
                                                    </td>

                                                </tr> 
                                                
                                               
                                                     <tr>
                                                    <td class="fieldLabel" valign="top">Description:<FONT color="red"  ><em>*</em></FONT></td>
                                                    <td colspan="3" valign="top"><s:textarea rows="5" cols="65" name="surveyDescription" cssClass="inputTextareaOverlay1" id="surveyDescription" style="width: 589px; height: 150px" onchange="surveyFormFieldsValidator(this);"/>
                                                </td>
                                                </tr>
                                              <%--  <tr>
                                                     <td class="fieldLabel" valign="top">Do you Want Customized Message?<FONT color="red"  ><em>*</em></FONT></td>   
                                                     <td style="padding-right: 25px;"><s:checkbox name="customCheckBox" id="customCheckBox"  theme="simple"   onclick="customizeTextMessage();"/> </td>
                                                 </tr>
                                                 <tr>
                                                    <td colspan="8" style="padding-right: 39px;" align="right">
                                                         <div id="my_box" style="display:none;">
                                                             <s:textfield  name="surveyFormCustomizedTextBox"  cssClass="inputTextBlueLargeAccount" cssStyle="width: 589px; height: 20px" id="surveyFormCustomizedTextBox"   />  
                                                         
                                                         </div>
                                                     </td>
                                                 </tr> --%>
                                              <tr> <td align="right"><s:checkbox name="allowDuplicates" id="allowDuplicates" /> </td>
                                                       <td class="fieldLabelLeft" valign="top">Do you want to allow Duplicates?</td>  
                                                      
                                                       <td class="fieldLabel" valign="top">Number of maximum Submissions:</td>  
                                                       <td><s:textfield name="maxSubmissions" id="maxSubmissions" cssClass="inputTextHours" onkeypress="return isNumberKey(event)"/> </td>
                                                 </tr>
                                              
                                                
                                                
                                                <tr><td align="right"><s:checkbox name="customCheckBox" id="customCheckBox"  theme="simple"   onclick="customizeTextMessage();"/> </td>
                                                <td class="fieldLabelLeft" valign="top" colspan="3"><FONT color="red"  ><em>*</em></FONT>Do you Want Customized Message?</td>   
                                                     
                                                 </tr>
                                                 <tr><td></td>
                                                    <td colspan="3" >
                                                         <div id="my_box" style="display:none;">
                                                             <s:textfield  name="surveyFormCustomizedTextBox"  cssClass="inputTextBlueLargeAccount" cssStyle="width: 589px; height: 20px" id="surveyFormCustomizedTextBox"   />  
                                                           <%--  <s:textarea rows="5" cols="65" name="surveyFormCustomizedTextBox" cssClass="inputTextareaOverlay1" id="surveyFormCustomizedTextBox" style="width: 589px; height: 150px"  /> --%>
                                                         </div>
                                                     </td>
                                                 </tr>
                                                 
                                                    <tr><td align="right"><s:checkbox name="anonymousCheckBox" id="anonymousCheckBox"  theme="simple" style="display:none"/> </td>
                                                     
                                                     <td class="fieldLabelLeft" valign="top" id="anonymousLabelPost" style="display: none" colspan="3">Do you want to post anonymously?</td>   
                                                     
                                                 </tr>
                                                <%-- <tr>
                                                     
                                                     <td class="fieldLabel" valign="top" id="anonymousLabelPost" style="display: none">Do you want to post anonymously?</td>   
                                                     <td style="padding-right: 25px;"><s:checkbox name="anonymousCheckBox" id="anonymousCheckBox"  theme="simple" style="display:none"/> </td>
                                                 </tr>
                                                --%>

                                            </table>
                                        </s:form>
                                    </div>
                                </div>

                                <script type="text/JavaScript">
                                    var cal3 = new CalendarTime(document.forms['frmSurveyForm'].elements['expiryDate']);
                                    cal3.year_scroll = true;
                                    cal3.time_comp = true;
                                </script> 
                                <script type="text/javascript">

                                    var countries=new ddtabcontent("surveyDetailsTabs")
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
                <td align="center">&reg; 2015  Mirage - All Rights Reserved.</td>
            </tr>
            <!--//END FOOTER : Record for Footer Background and Content-->

        </table>
        <!--//END MAIN TABLE : Table for template Structure-->
        <script type="text/javascript">
		$(window).load(function(){
		customizeTextMessage();
		internalType();

		});
		</script>
    </body>
</html>
