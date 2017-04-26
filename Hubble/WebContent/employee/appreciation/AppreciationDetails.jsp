<%-- 
    Document   : AppreciationDetails
    Created on : Nov 3, 2015, 4:09:24 PM
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
        <title>Hubble Organization Portal :: Employee&nbsp;Appreciation&nbsp;Details</title>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/selectivity-full.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/recruitment/ConsultantResumeClientValidation.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script> 
        <script type="text/javascript" src="<s:url value="/includes/javascripts/marketing/SurveyFormScripts.js"/>"></script> 
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/appreciation/EmployeeAppreciation.js?version=1.0"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/jquery.min.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/jquery.js"/>"></script>  
        
        <script>
            $(document).ready(function() {
                $('#appreciationCC').selectivity({
                    
                    multiple: true,
                    minimumInputLength: 2,
                    placeholder: 'Type to search emails'
                });
                $('#appreciationBCC').selectivity({
                    
                    multiple: true,
                    minimumInputLength: 2,
                    placeholder: 'Type to search emails'
                });
                 
            });
        </script>
        <script>
            $(document).ready(function(){
                $('#appreciationCC').change(function(e){
                    var emailArry = []; 
                    $("#appreciationCC :selected").each(function(){
                        emailArry.push($(this).val());
                        var len= emailArry.length;
                        if(len>3){
                                  
                            alert("Selecting more than 3 not allowed");
                            //$('#appreciationCC').selectivity('clear');
                            $('#appreciationCC').selectivity('remove', $(this).val());
                            return false;
                        }
                    });
                });
                    
            });
            
            
            $(document).ready(function(){
                $('#appreciationBCC').change(function(e){
                    var emailArry = []; 
                         
                    $("#appreciationBCC :selected").each(function(){
                        emailArry.push($(this).val());
                        var len= emailArry.length;
                        if(len>3){ 
                            alert("Selecting more than 3 not allowed");
                            //$('#appreciationCC').selectivity('clear');
                            $('#appreciationBCC').selectivity('remove', $(this).val());
                            return false;
                        }
                        
                     
                    });
                });
                    
            });
                
        </script>
        <style>
            a.tooltip {outline:none; }
            a.tooltip strong {line-height:30px;}
            a.tooltip:hover {text-decoration:none;} 
            a.tooltip span {
                z-index:15;display:none; padding:14px 20px;
                margin-top:-5px; margin-left:10px;
                width:220px; line-height:16px;
            }

            a.tooltip:hover span{
                display:inline; position:absolute; color:#111;
                border:1px solid #DCA; background:#3E93D4; font-family: lucida-sans; font-size: 15px;
                font-weight: normal;
                color: white;}
            .callout {z-index:20;position:absolute;top:30px;border:0;left:-12px;}

            /*CSS3 extras*/
            a.tooltip span
            {
                border-radius:4px;
                box-shadow: 5px 5px 8px #CCC;
            }
        </style>
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
                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                            <!--//START DATA COLUMN : Coloumn for Screen Content-->
                            <td width="850px" class="cellBorder" valign="top" style="padding-left:10px;padding-top:5px;">
                                <!--//START TABBED PANNEL : -->
                                <ul id="surveyDetailsTabs" class="shadetabs" >
                                    <s:if test="%{currentAction=='doAddAppreciation'}">
                                        <li ><a href="#" class="selected" rel="libraryAdd">Appreciation</a></li> 
                                    </s:if>
                                    <s:else>
                                        <li ><a href="#" class="selected" rel="libraryAdd">Appreciation</a></li>
                                    </s:else>
                                </ul>
                                <div  style="border:1px solid gray; width:830px;height: 550px; overflow:auto; margin-bottom: 1em;">
                                    <div id="libraryAdd" class="tabcontent"  >
                                        <s:form name="frmAppreciation" id="frmAppreciation" action="%{currentAction}" theme="simple" method="POST" enctype="multipart/form-data" onsubmit="return surveyFieldsValidate();">
                                            <table cellpadding="2" cellspacing="0" border="0" width="100%">                                                
                                                <tr align="right">
                                                    <s:hidden name="surveyId" id="surveyId" value="%{surveyId}"/>
                                                    <td class="headerText" colspan="6">
                                                        <%
                                                            if (request.getAttribute("resultMessage") != null) {
                                                                out.println(request.getAttribute("resultMessage").toString());
                                                            }

                                                        %>
                                                        <a href="<s:url value="getMyAppreciation.action?searchflag=%{searchflag}"/>" style="align:center;">
                                                            <img alt="Back to List"
                                                                 src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                 width="66px" 
                                                                 height="19px"
                                                                 border="0" align="bottom">
                                                        </a>  
                                                      
                                                        <s:hidden name="searchflag" id="searchflag" value="%{searchflag}"/>
                                                        <s:hidden name="appriceationId" id="appriceationId" value="%{appriceationId}"/>
                                                        <s:hidden name="sendOrSave" id="sendOrSave" value="%{sendOrSave}"/>
                                                    </td>
                                                </tr>    
                                                <tr>
                                                    <td class="fieldLabel" >Title&nbsp;:<FONT color="red"  ><em>*</em></FONT> </td>
                                                    <td colspan="3"><s:textfield name="appreciationTitle" id="appreciationTitle" required="true"  cssClass="inputTextBlueLargeAccount" placeholder="Thank you for enriching our work environment" onchange="javascript:fieldLenghtValidator(this);" cssStyle="width: 589px; height: 20px" value="%{appreciationTitle}"/>
<!--                                                        <a href="#" class="tooltip">  <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/information.png" width="15px" 
                                                                                           height="15px" />
                                                            <span> <strong>Title:</strong><br/>Thank you for enriching our work environment   </span>
                                                        </a>-->
                                                    </td>
                                                </tr>   
                                                <tr>
                                                <td class="fieldLabel" >Sub&nbsp;Title&nbsp;:<FONT color="red"  ><em>*</em></FONT> </td>
                                                    <td colspan="3"><s:textfield name="subAppreciationTitle" id="subAppreciationTitle" required="true" placeholder="Congratulations for the Outstanding Job"  cssClass="inputTextBlueLargeAccount" onchange="javascript:fieldLenghtValidator(this);" cssStyle="width: 589px; height: 20px" value="%{subAppreciationTitle}"/>
<!--                                                        <a href="#" class="tooltip">  <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/information.png" width="15px" 
                                                                                           height="15px" />
                                                            <span> <strong>SubTitle:</strong><br/>Congratulations&#33 for the Outstanding Job    </span>
                                                        </a>-->
                                                    </td>
                                                </tr>  
                                                <tr>
                                                    <td class="fieldLabel" >Subject&nbsp;:<FONT color="red"  ><em>*</em></FONT> </td>
                                                    <td colspan="3"><s:textfield name="appreciationSubject" id="appreciationSubject" required="true" placeholder="Reg:Appreciation process" cssClass="inputTextBlueLargeAccount" onchange="javascript:fieldLenghtValidator(this);" cssStyle="width: 589px; height: 20px" value="%{appreciationSubject}" />
<!--                                                        <a href="#" class="tooltip">  <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/information.png" width="15px" 
                                                                                           height="15px" />
                                                            <span> <strong>Subject:</strong><br/> Reg:Appreciation process </span>
                                                        </a>-->
                                                    </td>
                                                </tr>  
                                                
                                             <s:if test="%{searchflag == 'my'}">
                                                     <tr>
                                                     <td   class="fieldLabel">Team [TO]&nbsp;:<FONT color="red"  ><em>*</em></FONT></td>
                                                     <td><font color="green" style="font-family: lucida-sans;font-size: 12px;"><s:property value="#session.userName"/></font></td>
                                                       </tr>
                                                                </s:if>
                                                                      <s:else>
                                                <tr>
                                                    <td   class="fieldLabel">Team [TO]&nbsp;:<FONT color="red"  ><em>*</em></FONT></td>
                                                    <td ><s:select list="empnamesList" id="empnameById" name="empnameById" required="true" cssClass="inputSelectLarge" multiple="true" onchange="javascript:getValue();" value="%{appreciationToList}"/>
                                                    <a href="#" class="tooltip"> <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/information.png" width="15px" height="15px" />
                                                    <!--    <span style="margin-top:45px;"> By default mail is generated to selected Employees <strong>1st</strong> level , <strong>2nd</strong> level <strong>"Reports To"</strong> and <strong>1st</strong> level <strong>"Operations contact"</strong>.<br> No need to enter these email Ids either in CC or BCC.</span> -->
                                                    <span style="margin-top:45px;"> By default Email is generated to selected Employees, <strong>1st</strong> level and <strong>2nd</strong> level <strong>"Reports To"</strong> and <strong>1st</strong> level <strong>"Operations contact"</strong>.<br>So, no need to enter their Email Ids either in CC or BCC.</span>
                                                        </a>
                                                    </td> 
                                                    <s:hidden name="ToHidden" id="ToHidden"/>
                                                </tr>
                                                                </s:else>

                                                <tr>
                                                    <td class="fieldLabel forRemove"  >CC&nbsp;: </td>
                                                    <td colspan="3"><s:select name="appreciationCC" id="appreciationCC" list="employeeEmails" multiple="true" cssClass="inputTextBlueLargeAccount" style="width: 589px; height: 80px; float:left;" value="%{appreciationCCList}" />
                                                        
                                                        <a href="#" class="tooltip"> <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/information.png" width="15px" height="15px" />
                                                          <!--  <span> By default mail is generated to their corresponding <strong>1st</strong> level , <strong>2nd</strong> level <strong>"Reports To"</strong> and <strong>1st</strong> level <strong>"Operations contact"</strong>.No need to enter their email Ids here. So, Here you can enter maximum of 3 employees email Ids only  <strong>CC</strong> other than those.   </span> -->
                                                        <span> By default mail is generated to their corresponding <strong>1st</strong> level ,<strong>2nd</strong> level <strong>"Reports To"</strong> and <strong>1st</strong> level <strong>"Operations contact"</strong>.No need to enter their Email Ids here. Here you can enter maximum of 3 employees Email Ids only.    </span>
                                                        </a>
                                                    </td>
                                                        <s:hidden id="employeeEmailsHidden"  value="%{employeeEmails}"/>
                                                        <s:hidden name="CCHidden" id="CCHidden" />
                                                          
                                                    
                                                </tr>  
                                                <tr>
                                                    <td class="fieldLabel" >BCC&nbsp;: </td>
                                                    <td colspan="3"><s:select name="appreciationBCC" id="appreciationBCC" list="employeeEmails" multiple="true" cssClass="inputTextBlueLargeAccount" style="width: 589px; height: 80px; float:left; " value="%{appreciationBCCList}"/>
                                                      <a href="#" class="tooltip">  <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/information.png" width="15px" height="15px" />
                                                        <!--  <span>By default mail is generated to <strong>FROM Address as BCC</strong>. So,If required any other Employee in the BCC, You can Add them.   </span> -->
                                                        <span>By default Email is generated to <strong>FROM Address as BCC</strong>. So,If required any other Employee in the BCC, You can Add them.   </span>
                                                        </a>
                                                    </td>
                                                    <s:hidden name="BCCHidden" id="BCCHidden"/>
                                                </tr>  
                                                <tr>
                                                    <td class="fieldLabel" valign="center">Body Content&nbsp;:<FONT color="red"  ><em>*</em></FONT></td>
                                                    <td colspan="3" valign="top"><s:textarea rows="5" cols="65" name="appreciationBodyContent" placeholder="Good day! A Warmest Thank you for the outstanding leadership and contribution.The company is proud to have such a dedicated and hardworking member like you with us on board.Enthusiasm and motivation helps to increase the productivity, do extra work and complete each aspect in time.We appreciate you for your diligence and commitment by efficiently handling the tough project.It is really commendable to see how you organize your time and inspire the team members to move forward.The organization has received many compliments and words of praise for you from our clients and we are delighted to have such a valuable resource. We once again take this opportunity to congratulate you on your success and express our gratitude.\nAll the best and Keep up the good work."
                                                                required="true" cssClass="inputTextareaOverlay1" id="appreciationBodyContent" style="width: 589px; height: 150px" value="%{appreciationBodyContent}" onchange="javascript:fieldLenghtValidator(this);" />
<!--                                                        <a href="#" class="tooltip">  <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/information.png" width="15px" 
                                                                                           height="15px" />
                                                            <span  style="width:600px; margin-top:-90px; margin-left:-600px;"> <strong>Body Content:</strong><br/> 

                                                                Good day! A Warmest Thank you for the outstanding leadership and contribution.
                                                                The company is proud to have such a dedicated and hardworking member like you with us on board.
                                                                Enthusiasm and motivation helps to increase the productivity, do extra work and complete each aspect in time.
                                                                We appreciate you for your diligence and commitment by efficiently handling the tough project.
                                                                It is really commendable to see how you organize your time and inspire the team members to move forward. 
                                                                The organization has received many compliments and words of praise for you from our clients and we are delighted
                                                                to have such a valuable resource. We once again take this opportunity to congratulate you on your success and
                                                                express our gratitude.<br> 
                                                                All the best and Keep up the good work.

                                                            </span>
                                                        </a>-->
                                                        <s:if test="searchflag=='team' || searchflag=='opt'">                                         
                                                            <s:if test="status=='Send'"><center><FONT color="red">Already Send Email,You Cannot Edit Again</FONT></center></s:if>                                    
                                                    </s:if>
                                                </td>
                                                </tr>

                                                <tr>
                                                    <td colspan="2"  style="padding-right: 110px;" align="right">
                                                        <s:if test="status=='Created'">
                                                            <s:submit cssClass="buttonBg" value="Update" onclick="saveStaus();"/>&nbsp;&nbsp; <s:submit type="button" cssClass="buttonBg" value="Send" onclick="sendDetails();"/>
                                                        </s:if>

                                                        <s:if test="%{currentAction=='doAddAppreciation'}">
                                                            <s:submit cssClass="buttonBg" value="Create" onclick="saveStaus();"/> 
                                                            &nbsp;&nbsp; </s:if> &nbsp;&nbsp;
                                                        <s:if test="appriceationId>=1">
                                                            <a href="<s:url action="../../employee/appreciation/getAppreciationPreview"></s:url>?appriceationId=<s:property value="appriceationId"/>"  style="text-decoration:none" target="_blank" > 
                                                                <input type="button" Class="buttonBg" value="Preview"/> </a>
                                                            </s:if>
                                                    </td>
                                                </tr>
                                            </table>
                                        </s:form>
                                    </div>
                                </div>


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
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/selectivity-full.min.js"/>"></script>
        	<s:if test="status=='Send'">
		<script type="text/javascript">
		$(window).load(function(){
	init();
		});
		</script>
        
       
        </s:if>
    </body>
</html>







