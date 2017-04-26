<!-- 
 *******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  September 30, 2007, 3:25 PM
 *
 * Author  : Rajasekhar Yenduva<ryenduva@miraclesoft.com>
 *
 * File    : ActivityAdd.jsp
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
-->
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%-- <%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<%@ page import="com.freeware.gridtag.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib  prefix="grd" uri="/WEB-INF/tlds/datagrid.tld" %>

<html>
    <head>
        <title>Hubble Organization Portal :: Activity Adding</title>
      
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tooltip.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tooltip.js"/>"></script>   
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>   
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
        <%--<script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ActivityClientValidation.js"/>"></script> 
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/StringUtility.js"/>"></script>--%>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/StandardClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <%--<script type="text/JavaScript" src="<s:url value="/includes/javascripts/CommonAjax.js"/>"></script>--%>
        
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/Contact-Activity.js?version=1.3"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ajax/AjaxPopup.js"/>"></script> 
        <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/jquery.js"/>"></script>        
      <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/jquery.min.js"/>"></script>
       
        <s:include value="/includes/template/headerScript.html"/>
       <script>
            $(document).ready(function() {
          $('#accContacts').selectivity({
                    
                    multiple: true,
                    minimumInputLength: 2,
                    placeholder: 'Type to search Contact Name'
                });
                   });

        </script>
        <!-- <script>
         $(document).ready(function(){
                $('#accContacts').change(function(e){
                      var myString = $('#accContacts').selectivity('value');
                    //  alert(myString.length);
                       if(myString.length>4){
                           $('#accContacts').selectivity('remove', myString[myString.length-1]);
                            alert("Selecting more than 4 not allowed");
                       }else {
                          // alert(myString.length-1);
                           document.getElementById("contactsHidden").value=myString;
                       }
                });
                    
            });

        </script> -->
         <script>
         $(document).ready(function(){
                $('#accContacts').change(function(e){
                     
                      var myString = $('#accContacts').selectivity('value');
                     
                      
                     // alert(myString)
                    //  alert(myString.length);
                    //isContactEmailExist();
                       if(myString.length>4){
                           $('#accContacts').selectivity('remove', myString[myString.length-1]);
                            alert("Selecting more than 4 not allowed");
                       }else {
                          // alert(myString.length-1);
                           if(document.getElementById("activityType").value=="Campaign"){
                            var wotEmailContact = "";
                          if(myString.length==1){
                              wotEmailContact = isContactEmailExist(myString);
                          }else {
                              wotEmailContact =  isContactEmailExist(myString[myString.length-1]);
                              }
                               if(wotEmailContact!=""){
                                  alert("Please update email of "+wotEmailContact);
                                   $('#accContacts').selectivity('remove', myString[myString.length-1]);
                              }
                          }
                           document.getElementById("contactsHidden").value=myString;
                       }
                     
                });
                    
            });

        </script>
    
    </head>
    <%-- <body class="bodyGeneral" oncontextmenu="return false;" onload="getAddActivityList();"> --%>
    <!-- <body class="bodyGeneral" oncontextmenu="return false;" onload="getAddActivityList();dispalyDatePicker();"> -->
     <body class="bodyGeneral" oncontextmenu="return false;" >

     
        
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
                            <td width="850px" class="cellBorder" valign="top">
                                <table cellpadding="0" cellspacing="0" width="100%">
                                    <tr>
                                        <td>
                                            <span class="fieldLabel"><s:if test="#session.roleName != 'Vendor'">Account Name :</s:if><s:else>Vendor Name :</s:else></span>&nbsp;
                                            <a class="navigationText" href="<s:url action="../accounts/getAccount"><s:param name="id" value="%{accountId}"/></s:url>"><s:property value="#session.accountName"/></a>
                                            <s:if test="contactId !=0">
                                                &nbsp;&nbsp;<span class="fieldLabel">Contact Name :</span>&nbsp;
                                                <a class="navigationText" href="<s:url action="../contacts/getContact"><s:param name="id" value="%{contactId}"/></s:url>"><s:property value="#session.contactName"/></a>
                                            </s:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td valign="top" style="padding-left: 10px ;padding-top:5px; ">
                                            <!--//START TABBED PANNEL : -->
                                            <ul id="accountTabs" class="shadetabs"  >
                                                <li ><a href="#" class="selected"  rel="activityTab" > Add Activity </a></li>
                                                
                                            </ul>
                                            <div  style="border:1px solid gray; width:845px;height: 350px;margin-bottom: 1em;">
                                                <%--  <sx:tabbedpanel id="activityPannel" cssStyle="width: 845px; height: 250px;padding-left:10px;padding-top:5px;" doLayout="true"> --%>
                                                
                                                <!--//START TAB : -->
                                                <%--  <sx:div id="activityTab" label="Activity" cssStyle="overflow: auto" > --%>
                                                <div id="activityTab" class="tabcontent"   >    
                                                    <%--<s:form name="activityForm" action="addActivity" theme="simple"> --%>
                                                 <%--   <s:form name="activityForm" action=" %{ActionType}" theme="simple"> --%>
                                                 
                                                 <s:form name="activityForm" id="activityForm"  action=" %{actionType}" theme="simple" onsubmit="return checkvalidation();saveContactDetails()">

                                                        <table width="100%" cellpadding="0" cellspacing="0" border="0">
                                                            <tr><td colspan="4"><div id="resultMessage"></div></td></tr>  
                                                            <tr align="right">
                                                                <td class="headerText" colspan="4">
                                                                    <% if(request.getAttribute(ApplicationConstants.RESULT_MSG) != null){
                                                                        out.println(request.getAttribute(ApplicationConstants.RESULT_MSG));
                                                                    }%>
                                                                    
                                                                    <a href="<s:url value="../accounts/getAccount.action?id=%{accountId}"/>" style="align:center;">
                                                                        <img alt="Back to List"
                                                                             src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                             width="66px" 
                                                                             height="19px"
                                                                         border="0" align="bottom"></a>&nbsp;&nbsp;
                                                                    <%--<s:if test="%{actionType == 'addActivity'}">
                                                                        
                                                                        <s:submit label="Submit" value="Update" cssClass="buttonBg" onmouseover="fixedtooltip('<b>Adding</b> Activity',this,event, 100,1,-10)" onmouseout="delayhidetip()" />
                                                                    </s:if>
                                                                    <s:else>
                                                                        <s:submit label="Submit" value="Save" cssClass="buttonBg" onmouseover="fixedtooltip('<b>Adding</b> Activity',this,event, 100,1,-10)" onmouseout="delayhidetip()" />          
                                                                    </s:else>
                                                                    
                                                                    <s:hidden name="accountId" value="%{accountId}"/>
                                                                    <s:hidden name="contactId" value="%{contactId}"/>
                                                                    <s:hidden name="calDateStart" value="%{dueDate}" cssClass="inputTextBlue"/> 
                                                                    <s:hidden name="calAccountName" value="%{#session.accountName}" /> 
                                                                    <s:hidden name="calContactName" value="%{#session.contactName}" /> 
                                                                    <s:hidden  name="officeEmail" value="%{officeEmail}" />
                                                                    <s:hidden  name="personalEmail" value="%{personalEmail}" /> --%>
                                                                     <s:if test="%{actionType == 'updateActivity'}">
                                                                        
                                                                        <s:submit label="Submit" value="Update" cssClass="buttonBg" onmouseover="fixedtooltip('<b>Update</b> Activity',this,event, 100,1,-10)" onmouseout="delayhidetip()" />
                                                                        <s:hidden name="id" value="%{currentActivity.id}"/>
                                                                        <s:hidden name="accountId" value="%{currentActivity.accountId}"/>
                                                                        <s:hidden name="contactId" value="%{currentActivity.contactId}"/>
                                                                        <s:hidden name="calDateStart" value="%{currentActivity.dueDate}" cssClass="inputTextBlue"/> 
                                                                       <%--  $$<s:property value="%{currentActivity.id}" />$$ --%>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <s:submit label="Submit" value="Save" cssClass="buttonBg" onmouseover="fixedtooltip('<b>Adding</b> Activity',this,event, 100,1,-10)" onmouseout="delayhidetip()" />        
                                                                        <s:hidden name="accountId" value="%{accountId}"/>
                                                                        <s:hidden name="contactId" value="%{contactId}"/>
                                                                        <s:hidden name="calDateStart" value="%{dueDate}" cssClass="inputTextBlue"/> 
                                                                    </s:else>

                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td colspan="4">                                                        
                                                                    <!-- for displaying validation Errors and Action Messages -->
                                                                    <s:actionerror/>
                                                                    <s:actionmessage/>
                                                                    <s:fielderror/>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <s:if test="#session.roleName == 'Vendor'">
                                                                    <td width="130px"class="fieldLabel">Activity Type :</td>
                                                                    <td><s:select list="{'Call - Inbound','Call - Outbound','Email - Inbound','Email - Outbound','Received Resume','Confirmed Resume','Issued PO'}" name="activityType"  cssClass="inputSelect" /></td>
                                                                </s:if>
                                                                <s:else>
                                                                    <td width="130px"class="fieldLabel">Activity Type :</td>
                                                                    <%-- <td><s:select list="activityTypeList" name="activityType"  cssClass="inputSelect" /></td>     --%>
                                                                    <td><s:select list="activityTypeList" name="activityType" id="activityType" cssClass="inputSelect" onchange="enableCampaignName(this.value);dispalyDatePicker();displayConferenceDropdowns();"/></td>    

                                                                </s:else>
                                                                
                                                                <td class="fieldLabel">Priority :</td>
                                                                <td><s:select list="priorityList" name="priority" cssClass="inputSelect" /></td>
                                                            </tr>
                                                            
                                                            <tr id="contactTypeTr" style="display: none">
                                                             <td class="fieldLabel">Contact Type :</td>
                                                                    <td><s:select list="{'Regular Information','Influencer','Decision Maker'}" name="contactType" headerValue="--Please Select Contact Type--" headerKey="" id="contactType" cssClass="inputSelect" value="%{currentActivity.contactType}" /></td>
                                                                   <td class="fieldLabel">Practice:<font style="color:red;">*</font></td>
                                                                                         
                                                             <td><s:select cssClass="inputTextBlue" list="practiceList" name="practiceName" id="practiceName" onchange="" headerValue="--Please Select Practice--" headerKey="" value="%{currentActivity.practiceName}"/></td>

                                                        </tr>
                                                            
                                                            <tr>
                                                                <td class="fieldLabel">Campaign Name :</td>
                                                                <%-- <td><s:select list="campaignIdMap" name="campaignId" cssClass="inputSelect" value="5"/></td> --%>
                                                                <td><s:select list="campaignIdMap" headerKey=""
                                                  headerValue="-- Please Select --" name="campaignId"  id="campaignId" cssClass="inputSelect" /></td>

                                                              <%--  <td class="fieldLabel">Assigned To :</td>
                                                                <td><s:select list="assignedToIdMap" 
                                                                                  headerKey="%{createdById}"
                                                                                  headerValue="%{employeeName}" 
                                                                                  name="assignedToId" 
                                                                              cssClass="inputSelectLarge"  /></td> --%>
                                                              <td class="fieldLabel">Opportunities :<font style="color:red;">*</font></td>
                                                                <td><s:select list="oppurtunitiesMap" name="oppurtunityId" id="oppurtunityId" headerKey="-1" headerValue="--Please Select--" cssClass="inputSelect" value="%{currentActivity.oppurtunityId}" /></td>

                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel">Status :</td>
                                                                <td><s:select list="activityStatusList" name="status" cssClass="inputSelect"/></td>
                                                              <%-- <td class="fieldLabel">Due Date :</td>
                                                                <td>
                                                                    <s:textfield name="dueDate" value="%{dueDate}" cssClass="inputTextBlue"/>
                                                                    <a href="javascript:cal1.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                         width="20" height="20" border="0"></a>
                                                                </td> --%>
                                                            </tr>
                                                            <tr id="enableTr">
                                                                 <td class="fieldLabel">Date :</td>                                                    
                                                                <td style="width: 100px;">
                                                                    <div id="enbale" >  
                                                                        <s:textfield name="dueDate" id="dueDate" cssClass="inputTextBlueSmall" onkeypress="return handleEnter(this,event);" readonly="true"/><a href="javascript:cal1.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                    </div>    
                                                                </td>  
                                                             </tr>

                                                        <%--    <tr>
                                                                
                                                                <td class="fieldLabel">Alarm :</td>
                                                                <td align="left"><s:checkbox name="alarm" value="%{alarm}"  fieldValue="true" theme="simple"/> </td>
                                                                <td class="fieldLabel" >Send Invitation :</td>
                                                                <td align="left"><s:checkbox name="sendInvitation" value="%{sendInvitation}"  fieldValue="true" theme="simple"/> </td>
                                                            </tr> --%>
                                                          <%--  <tr>
                                                                <td  class="fieldLabel" valign="top">Description :</td>                                                                  
                                                                <td  colspan="3" ><s:textfield name="description" cssClass="inputTextBlueExtraLarge" value="%{description}"  onKeyUp="symbolRestForDesc(this.value);" onchange="fieldLengthValidator(this);"  id="description" size="255"/></td>
                                                            </tr> --%>
                                                            <tr>
                                                                <td  class="fieldLabel" valign="top">Comments :</td>                                                                  
                                                                <td  colspan="3"><s:textarea name="comments" rows="3" cols="80" cssClass="inputTextarea" value="%{comments}"  onKeyUp="symbolRestForComments(this.value);"  onchange="fieldLengthValidator(this);"  id="comments"/></td> 
                                                                <%-- <td  colspan="3"><s:textarea name="comments" rows="3" cols="80" cssClass="inputTextarea" value="%{comments}" onchange="fieldLengthValidator(this);"  id="comments"/></td> --%>
                                                            </tr>
                                                            <tr> 
                                                                      <td class="fieldLabel forRemove">Contacts :</td>
                                                                    <td colspan="3">
                                                                        <s:select  name="accContacts"  id="accContacts"  list="contactsMap" cssClass="inputTextBlueLargeAccount"  style="width:435px; height: 93px" multiple="true" value="%{currentActivity.contactsIdList}" onchange="getValues(this);"/>
                                                                    <s:hidden name="contactsHidden" id="contactsHidden" />
                                                                     <s:select  name="wotEmailContacts"  id="wotEmailContacts"  list="wotEmailContactsMap" cssStyle="display:none"/>
                                                                    </td>
                                                                </tr>

                                                        </table>
                                                    </s:form>
                                                    <script type="text/JavaScript">
                                                 var cal1 = new CalendarTime(document.forms['activityForm'].elements['dueDate']);
				                 cal1.year_scroll = true;
				                 cal1.time_comp = false;
                                            
                                                    </script>
                                                    <%--   </sx:div > --%>
                                                    <!--//END TAB : -->
                                                </div>
                                                <%--    </sx:tabbedpanel> --%>
                                            </div>
                                            <script type="text/javascript">

                    var countries=new ddtabcontent("accountTabs")
                    countries.setpersist(false)
                    countries.setselectedClassTarget("link") //"link" or "linkparent"
                    countries.init()

                                            </script>
                                            <!--//END TABBED PANNEL : -->
                                
                                            <!--//START TABBED PANNEL : -->
                                            <%--    <sx:tabbedpanel id="activityListPannel" cssStyle="width: 850px; height: 250px;padding-left:10px;padding-top:5px;" doLayout="true"> --%>
                                            <ul id="accountTabs1" class="shadetabs" >
                                                <li ><a href="#" class="selected" rel="activityListTab" > Activity List </a></li>
                                                
                                            </ul>
                                            <%-- Activity List Start ---%>
               <div  style="border:1px solid gray; width:850px;height:250px;padding-left:10px;padding-top:5px;overflow:auto; margin-bottom: 1em;">
                                                <%--    <sx:div id="accountsListTab" label="Accounts List" cssStyle="overflow:auto;"> --%>
                                        <div id="activityListTab" class="tabcontent"   >
                                                    <form action="" name="frmDBGrid" method="post"> 
                                                        <table cellpadding="0" cellspacing="0" width="100%">
                                                            <tr>
                                                                <td class="headerText">
                                                                  
                                                                    <s:hidden name="accId" id="accId" value="%{accountId}"/>
                                                                    <s:hidden name="curContactId" id="curContactId" value="%{contactId}"/>
                                                                    <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td colspan="3">
                                                                   <div id="AccountActivityList" style="display: block">
                                    <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                    <table id="tblUpdate2" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='100%' align="center">
                                        <COLGROUP ALIGN="left" >
                                     <COL width="5%%">
                                        <COL width="15%">
                                        <COL width="20%">
                                        <COL width="10%">
                                        <COL width="15%">
                                        <COL width="15%">
                                        <COL width="20%">
                                        <%--<COL width="15%">
                                        <COL width="20%">--%>
                                       <%-- <COL width="10%">
                                        <COL width="10%"> --%>
                                    </table>
                                    <br>
                                   <center><span id="spnFast" class="activeFile" style="display: none;"></span></center> 
                                </div>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </form>
                                                    <%--    </sx:div> --%>
                                                </div>

                                            </div>

                                            <%-- Activity List End  --%>
                                            
                                            <!--//END TABBED PANNEL : -->
                                            <script type="text/javascript">

                    var countries=new ddtabcontent("accountTabs1")
                    countries.setpersist(false)
                    countries.setselectedClassTarget("link") //"link" or "linkparent"
                    countries.init()

                                            </script>
                                        </td>
                                    </tr> 
                                </table>
                                
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
            <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/selectivity-full.css"/>">
            <script type="text/JavaScript" src="<s:url value="/includes/javascripts/selectivity-full.min.js"/>"></script>
            <script type="text/javascript">
		$(window).load(function(){
	getAddActivityList();
	dispalyDatePicker();
		});
		</script>
    </body>
</html>
