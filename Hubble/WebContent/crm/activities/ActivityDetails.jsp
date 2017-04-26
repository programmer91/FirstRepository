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
 * File    : ActivityDetails.jsp
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
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd"%>

<html>
    <head>
        <title>Hubble Organization Portal :: Activity Details</title>
        
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
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/jquery.min.js"/>"></script>
          <script type="text/JavaScript" src="<s:url value="/includes/javascripts/Contact-Activity.js?version=1.3"/>"></script>
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
        <script>
     /*    $(document).ready(function(){
                $('#accContacts').change(function(e){
                    var emailArry = []; 
                    $("#accContacts :selected").each(function(){
                        emailArry.push($(this).val());
                        var len= emailArry.length;
                        if(len>4){
                                  
                            alert("Selecting more than 4 not allowed");
                            //$('#appreciationCC').selectivity('clear');
                            $('#accContacts').selectivity('remove', $(this).val());
                            return false;
                        }
                        populatingContactvalues();
                    });
                });
                    
            });*/
    
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
        <script>
            
            function populatingContactvalues()
{
   
    var skillCatArry = [];    
    $("#accContacts :selected").each(function(){
        skillCatArry.push($(this).val()); 

    });
    document.getElementById("contactsHidden").value=skillCatArry;
}
        </script>

        
    </head>
   <!-- <body class="bodyGeneral" oncontextmenu="return false;" onload="disableCampaignName();dispalyDatePicker();populatingContactvalues();displayConferenceDropdowns();"> -->
    <body class="bodyGeneral" oncontextmenu="return false;">

        <%!
        /* Declarations */
        Connection connection;
        String queryString;
        String strTmp;
        String strSortCol;
        String strSortOrd;
        String currentActivityId;
        String currentActivityType;
        String currentAccountId;
        int intSortOrd = 0;
        int intCurr;
        boolean blnSortAsc = true;
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
                            <td width="850px" class="cellBorder" valign="top">
                                <table cellpadding="0" cellspacing="0" width="100%">
                                    <tr>
                                        <td>
                                            <span class="fieldLabel"><s:if test="#session.roleName != 'Vendor'">Account Name :</s:if><s:else>Vendor Name :</s:else></span>&nbsp;
                                            <a class="navigationText" href="<s:url action="../accounts/getAccount"><s:param name="id" value="%{currentActivity.accountId}"/></s:url>"><s:property value="#session.accountName"/></a>
                                            <s:if test="contactId !=0">
                                                &nbsp;&nbsp;<span class="fieldLabel">Contact Name :</span>&nbsp;
                                                <a class="navigationText" href="<s:url action="../contacts/getContact"><s:param name="id" value="%{currentActivity.contactId}"/></s:url>"><s:property value="#session.contactName"/></a>
                                            </s:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td valign="top" style="padding-left: 10px ;padding-top:5px; ">
                                            <!--//START TABBED PANNEL : -->
                                            <ul id="accountTabs" class="shadetabs"  >
                                                <li ><a href="#" class="selected"  rel="activityTab" > Activity </a></li>
                                                
                                            </ul>
                                            <div  style="border:1px solid gray; width:845px;height: 350px;margin-bottom: 1em;">
                                                <%--  <sx:tabbedpanel id="activityPannel" cssStyle="width: 845px; height: 250px;padding-left:10px;padding-top:5px;" doLayout="true"> --%>
                                                
                                                <!--//START TAB : -->
                                                <%-- <sx:div id="activityTab" label="Activity"> --%>
                                                <div id="activityTab" class="tabcontent">  
                                                    <s:form name="activityForm" action="editActivity" theme="simple" onsubmit="return checkvalidation();saveContactDetails()">
                                                        <s:hidden name="createdById" value="%{currentActivity.createdById}" id="createdById"/>
                                                        <table width="100%" cellpadding="0" cellspacing="0" border="0">
                                                            <tr><td colspan="4"><div id="resultMessage"></div></td></tr>  
                                                            <tr align="right">
                                                                <td class="headerText" colspan="4">
                                                                    <% if(request.getAttribute(ApplicationConstants.RESULT_MSG) != null){
                                                                    out.println(request.getAttribute(ApplicationConstants.RESULT_MSG));
                                                                    }%>
                                                                    <a href="<s:url value="../accounts/getAccount.action?id=%{currentActivity.accountId}"/>" style="align:center;">
                                                                        <img alt="Back to List"
                                                                             src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                             width="66px" 
                                                                             height="19px"
                                                                         border="0" align="bottom"></a>&nbsp;&nbsp;
                                                                        <%if(request.getAttribute("currentActivity.createdById").toString().equals(session.getAttribute("userId").toString())){%>       
                                                                    <s:submit label="Submit" value="Update" cssClass="buttonBg"/>
                                                                    <%}%>
                                                                    <s:hidden name="id" value="%{currentActivity.id}"/>
                                                                    <s:hidden name="accountId" value="%{currentActivity.accountId}"/>
                                                                    <s:hidden name="contactId" value="%{currentActivity.contactId}"/>
                                                                    <s:hidden  name="officeEmail" value="%{officeEmail}" />
                                                                    <s:hidden  name="personalEmail" value="%{personalEmail}" />
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
                                                                <td width="130px"class="fieldLabel">Activity Type :</td>
                                                                <td>
                                                                    <s:if test="#session.roleName == 'Vendor'">
                                                                        <s:select list="{'Call - Inbound','Call - Outbound','Email - Inbound','Email - Outbound','Received Resume','Confirmed Resume','Issued PO'}" value="%{currentActivity.activityType}" name="activityType"  cssClass="inputSelect" />
                                                                    </s:if>
                                                                    <s:else>
                                                                        <s:select list="activityTypeList" name="activityType" id="activityType" cssClass="inputSelect" value="%{currentActivity.activityType}" onchange="enableCampaignName(this.value);dispalyDatePicker();displayConferenceDropdowns();"/>
                                                                    </s:else>
                                                                </td>
                                                                <td class="fieldLabel">Priority :</td>
                                                                <td>
                                                                    <s:select list="priorityList" name="priority" 
                                                                              value="%{currentActivity.priority}"
                                                                              cssClass="inputSelect"/>
                                                                </td>
                                                            </tr>
                                                            
                                                            <tr id="contactTypeTr" style="display: none">
                                                             <td class="fieldLabel">Contact Type :</td>
                                                                    <td><s:select list="{'Regular Information','Influencer','Decision Maker'}" name="contactType" headerValue="--Please Select Contact Type--" headerKey="" id="contactType" cssClass="inputSelect" value="%{currentActivity.contactType}"/></td>
                                                                   <td class="fieldLabel">Practice:<font style="color:red;">*</font></td>
                                                                                         
                                                             <td><s:select cssClass="inputTextBlue" list="practiceList" name="practiceName" id="practiceName" onchange="" headerValue="--Please Select Practice--" headerKey="" value="%{currentActivity.practiceName}"/></td>

                                                        </tr>
                                                            
                                                           <tr>
                                                                <td class="fieldLabel">Campaign Name :</td>
                                                                <td>
                                                                    <s:select list="campaignIdMap" headerKey=""
                                                  headerValue="-- Please Select --"
                                                                              name="campaignId" 
                                                                              id="campaignId"
                                                                              value="%{currentActivity.campaignId}"
                                                                              cssClass="inputSelect" />
                                                                </td>
                                                                
                                                                         <td class="fieldLabel">Oppurtunities :<font style="color:red;">*</font></td>
                                                                <td><s:select list="oppurtunitiesMap" name="oppurtunityId"  headerKey="-1" headerValue="--Please Select--"  id="oppurtunityId" cssClass="inputSelect" value="%{currentActivity.oppurtunityId}" /></td>
                                                                
                                                                
<%--                                                                <td class="fieldLabel">Assigned To :</td>
                                                                <td>
                                                                    <s:select list="assignedToIdMap"    
                                                                              name="assignedToId" 
                                                                              value="%{currentActivity.assignedToId}"
                                                                              cssClass="inputSelect"/>
                                                                </td>--%>
                                                                
                                                            </tr>

                                                            <tr>
                                                                
                                                                <td class="fieldLabel">Status :</td>
                                                                <td>
                                                                    <s:select list="activityStatusList" 
                                                                              name="status" 
                                                                              value="%{currentActivity.status}"
                                                                              cssClass="inputSelect"/>
                                                                </td>
                                                               
                                                            </tr>
                                                           <tr id="enableTr">    
                                                               <td class="fieldLabel">Date :</td>    
                                                                <td style="width: 100px;">
                                                                    <div id="enbale" >  
                                                                        <s:textfield name="dueDate" id="dueDate" cssClass="inputTextBlueSmall" onkeypress="return handleEnter(this,event);" value="%{currentActivity.dueDate}" readonly="true"/><a href="javascript:cal1.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                        </div>  
                                                                </td>  
                                                                 
                                                             <%--   <td class="fieldLabel" >Send Invitation :</td>
                                                                <td align="left"><s:checkbox name="sendInvitation" value="false"  fieldValue="true" theme="simple"/> </td>  --%>
                                                            </tr>  

                                                           <%-- <tr>
                                                                <td  class="fieldLabel" valign="top">Description :</td>                                                                  
                                                                <td  colspan="3">
                                                       
                                                          <s:textfield name="description" id="description" value="%{currentActivity.description}" cssClass="inputTextBlueExtraLarge" onKeyUp="symbolRestForDesc(this.value);" onchange="fieldLengthValidator(this);" />
                                                                </td>
                                                            </tr> --%>
                                                             <% if(!"".equals(request.getAttribute("currentActivity.description").toString())){ %>
                                                            <tr>
                                                                <td  class="fieldLabel" valign="top">Description :</td>                                                                                                                                                           
                                                        <td><font color="green" > <s:property value="%{currentActivity.description}"/></font></td>
                                                          </td>
                                                            </tr>
                                                            <%}%>

                                                            <tr>
                                                                <td  class="fieldLabel" valign="top">Comments :</td>                                                                  
                                                                <td  colspan="3">
                                                                    <s:textarea name="comments" 
                                                                                id="comments"
                                                                                rows="3" cols="80" 
                                                                                value="%{currentActivity.comments}"
                                                                                cssClass="inputTextarea"
                                                                                onKeyUp="symbolRestForComments(this.value);" 
                                                                                onchange="fieldLengthValidator(this);"/>
                                                                </td>
                                                            </tr>
                                                             <tr> 
                                                                      <td class="fieldLabel forRemove">Contacts :</td>
                                                                    <td colspan="3">
                                                                    <s:select  name="accContacts"  id="accContacts"  list="contactsMap" cssClass="inputTextBlueLargeAccount"  style="width:435px; height: 93px" multiple="true" value="%{currentActivity.contactsIdList}"/>
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
                                                    <%--  </sx:div > --%>
                                                </div>
                                                <!--//END TAB : -->
                                    
                                                <%--  </sx:tabbedpanel> --%>
                                            </div>
                                            <!--//END TABBED PANNEL : -->
                                             <script type="text/javascript">

                    var countries=new ddtabcontent("accountTabs")
                    countries.setpersist(false)
                    countries.setselectedClassTarget("link") //"link" or "linkparent"
                    countries.init()

                                            </script>
                                            
                                           
                                                <!--//START TABBED PANNEL : -->
                                                <s:if test="#session.roleName != 'Vendor'">
                                                    <ul id="accountTabs1" class="shadetabs"  >
                                                        <li ><a href="#" class="selected"  rel="attachmentsList" > Attachments </a></li>
                                                        
                                                    </ul>
                                                </s:if>
                                                <div  style="border:1px solid gray; width:845px;height: 250px;margin-bottom: 1em;">  
                                                    <%-- <sx:tabbedpanel id="activityListPannel" cssStyle="width: 850px; height: 250px;padding-left:10px;padding-top:5px;" doLayout="true"> --%>
                                                    
                                                    <%--  <sx:div id="attachmentsList" label="Attachments"> --%>
                                                    <div id="attachmentsList" class="tabcontent">      
                                                        <%
                                                        
                                                        try{
                                                        
                                                        /* Getting DataSource using Service Locator */
                                                        connection = ConnectionProvider.getInstance().getConnection();
                                                        
                                                        if(request.getAttribute("currentAccountId") != null){
                                                        currentAccountId = (String)request.getAttribute("currentAccountId");
                                                        }
                                                        
                                                        if(request.getAttribute("currentActivityId") != null){
                                                        currentActivityId = (String)request.getAttribute("currentActivityId");
                                                        }
                                                        
                                                        if(request.getAttribute("currentActivityType") != null){
                                                        currentActivityType = request.getAttribute("currentActivityType").toString();
                                                        }
                                                        
                                                        /* String Variable for storing current position of records in dbgrid*/
                                                        strTmp 	= request.getParameter("txtCurr");
                                                        intCurr = 1;
                                                        if (strTmp != null)
                                                        intCurr = Integer.parseInt(strTmp);
                                                        
                                                        /* Specifing Shorting Column */
                                                        strSortCol = request.getParameter("txtSortCol");
                                                        
                                                        if (strSortCol == null) strSortCol = "AttachmentFileName";
                                                        
                                                        strSortOrd = request.getParameter("txtSortAsc");
                                                        if (strSortOrd == null) strSortOrd = "ASC";
                                                        
                                                        /* Sql query for retrieving resultset from DataBase */
                                                        queryString ="SELECT Id,AttachmentName,AttachmentLocation,AttachmentFileName,DateUploaded FROM tblCrmAttachments";
                                                        queryString = queryString + " WHERE ObjectType ='ContactActivity' AND ObjectId="+currentActivityId+" ORDER BY DateUploaded";
                                                        
                                                        String attachmentAction = "../attachments/attachment.action";
                                                        if(currentActivityId != null){
                                                        attachmentAction = attachmentAction+"?objectId="+currentActivityId+"&objectType=ContactActivity&activityType="+currentActivityType+"&accountId="+currentAccountId;
                                                        }
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
                                                                         <%if(request.getAttribute("currentActivity.createdById").toString().equals(session.getAttribute("userId").toString())){%>     
                                                                        <!-- DataGrid for list all Attachments -->
                                                                        <grd:dbgrid id="tblCrmAttachments" name="tblCrmAttachments" width="100" pageSize="6" 
                                                                                    currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                                    dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                            
                                                                            <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                                           imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                                           addImage="../../includes/images/DBGrid/Add.png" 
                                                                                           addAction="<%=attachmentAction%>"/>
                                                                            
                                                                            <%-- <grd:imagecolumn headerText="Delete" width="4" HAlign="center" 
                                                                             imageSrc="../../includes/images/DBGrid/Delete.png"
                                                                             linkUrl="deleteAttachment.action?Id={Id}" imageBorder="0"
                                                                             imageWidth="51" imageHeight="20" alterText="Delete"></grd:imagecolumn>--%>
                                                            
                                                                            <grd:textcolumn dataField="AttachmentFileName"  headerText="Attachment FileName"   width="30" sortable="true"/> 
                                                                            <grd:textcolumn dataField="AttachmentName"  headerText="Attachment Name" width="30" sortable="true"/>  
                                                                            <grd:datecolumn dataField="DateUploaded"  headerText="Date Uploaded"  dataFormat="MM-dd-yyyy HH:mm:SS" width="20" sortable="true"/>
                                                                            <grd:imagecolumn headerText="Download" width="4" HAlign="center" 
                                                                                             imageSrc="../../includes/images/download_11x10.jpg"
                                                                                             linkUrl="../attachments/getAttachment.action?Id={Id}" imageBorder="0"
                                                                                             imageWidth="11" imageHeight="10" alterText="Download"></grd:imagecolumn>
                                                                        </grd:dbgrid>
                                                                        <%}else {%>
                                                                          <grd:dbgrid id="tblCrmAttachments" name="tblCrmAttachments" width="100" pageSize="6" 
                                                                                    currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                                    dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                            
                                                                            <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                                           imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                                         />
                                                                            
                                                                            <%-- <grd:imagecolumn headerText="Delete" width="4" HAlign="center" 
                                                                             imageSrc="../../includes/images/DBGrid/Delete.png"
                                                                             linkUrl="deleteAttachment.action?Id={Id}" imageBorder="0"
                                                                             imageWidth="51" imageHeight="20" alterText="Delete"></grd:imagecolumn>--%>
                                                            
                                                                            <grd:textcolumn dataField="AttachmentFileName"  headerText="Attachment FileName"   width="30" sortable="true"/> 
                                                                            <grd:textcolumn dataField="AttachmentName"  headerText="Attachment Name" width="30" sortable="true"/>  
                                                                            <grd:datecolumn dataField="DateUploaded"  headerText="Date Uploaded"  dataFormat="MM-dd-yyyy HH:mm:SS" width="20" sortable="true"/>
                                                                            <grd:imagecolumn headerText="Download" width="4" HAlign="center" 
                                                                                             imageSrc="../../includes/images/download_11x10.jpg"
                                                                                             linkUrl="../attachments/getAttachment.action?Id={Id}" imageBorder="0"
                                                                                             imageWidth="11" imageHeight="10" alterText="Download"></grd:imagecolumn>
                                                                        </grd:dbgrid>
                                                                        <%}%>
                                                                        <!-- these components are DBGrid Specific  -->
                                                                        <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                                        <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                                        <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                                                                        
                                                                        
                                                                    </td>
                                                                </tr>
                                                            </table>    
                                                        </form>  
                                                        <%
                                                        
                                                        }catch(Exception se){
                                                        System.out.println("Exception in Activity "+se);
                                                        }finally{
                                                        if(connection!= null){
                                                        connection.close();
                                                        connection = null;
                                                        }
                                                        }
                                                        %>
                                                        <%--   </sx:div> --%>
                                                    </div>
                                                    
                                                    
                                                    
                                                    <%-- </sx:tabbedpanel> --%>
                                                </div>
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
              <script type="text/JavaScript" src="<s:url value="/includes/javascripts/Contact-Activity.js?version=1.0"/>"></script>
              <script type="text/javascript">
		$(window).load(function(){
	disableCampaignName();
	dispalyDatePicker();
        populatingContactvalues();
	displayConferenceDropdowns();
		});
		</script>
    </body>
</html>
