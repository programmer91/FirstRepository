<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%-- <%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<%@ page import="com.freeware.gridtag.*" %> 
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd" %>


<html>
    <head>
        <sx:head cache="false"/>
        <title>Hubble Portal :: Opportunity Adding</title>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
         <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/StringUtility.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
        <%--<script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/OpportunityClientValidation.js"/>"></script> --%>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/StandardClientValidations.js?ver=1.2"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
         <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tooltip.css"/>">
<script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGAccDetails.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tooltip.js"/>"></script>
    
    <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/jquery.js"/>"></script>  
      <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/jquery.min.js"/>"></script>
           
      <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ajax/AjaxPopup.js"/>"></script> 
       <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/helpText.css"/>">
       


        <s:include value="/includes/template/headerScript.html"/>
    </head>
<!-- <body class="bodyGeneral" oncontextmenu="return false;" onload="poplatingvalues();"> -->
 <body class="bodyGeneral" oncontextmenu="return false;">
        <%!
        /* Declarations */
        Connection connection;
        String queryString;
        String strTmp;
        String strSortCol;
        String strSortOrd;
        String currentAccountId;
        int intSortOrd = 0;
        int intCurr;
        boolean blnSortAsc = true;
        String OpportunityId;
        %>
        
        
        <!--//START MAIN TABLE : Table for template Structure-->
        <table class="templateTable1000x580" align="center" cellpadding="0" cellspacing="0" border="0">
            
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
                    <table class="innerTable1000x515" cellpadding="0" cellspacing="0" border="0">
                        <tr>
                            
                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                            <td width="150px;" class="leftMenuBgColor" valign="top"  >
                            <s:include value="/includes/template/LeftMenu.jsp"/></td>
                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                            
                            <!--//START DATA COLUMN : Coloumn for Screen Content-->
                            <td width="850px" class="cellBorder" valign="top">
                                <table cellpadding="0" cellspacing="0" width="100%">
                                    <tr>
                                        <td>
                                            <span class="fieldLabel">Account Name :</span>&nbsp;
                                            <a class="navigationText" href="<s:url action="../accounts/getAccount"><s:param name="id" value="%{accountId}"/></s:url>"><s:property value="accountName"/></a>
                                        </td>
                                    </tr>
                                    <tr>
                                    <td valign="top" style="padding-left:10px;padding-top:5px;">
                                        <ul id="accountTabs" class="shadetabs" >
                                            <li ><a href="#" class="selected" rel="contactTab">
                                                   <% if(request.getParameter("addingOppurtunties").equalsIgnoreCase("addOppur")) {%>
                                                        Add Opportunity
                                                    <%}else {%>
                                                        Edit Opportunity
                                                   <%}%>
                                                    
                                                </a></li>
                                            
                                        </ul>
                                        <!--//START TABBED PANNEL : -->
                                        <%--   <sx:tabbedpanel id="opportunityAddPannel" cssStyle="width:840px; height: 290px;padding-left:10px;padding-top:5px;" doLayout="true"> --%>
                                        <div  style="border:1px solid gray; width:840px;height: 290px; overflow:auto; margin-bottom: 1em;">                        
                                            <!--//START TAB : -->
                                            <%--   <sx:div id="contactTab" label="Opportunity"  theme="ajax" > --%>
                                            <div id="contactTab" class="tabcontent"  >
                                                <s:form action="%{actionString}" theme="simple" id="frmOpportunity" name="frmOpportunity" onsubmit="return OpportunityValidation();">
                                                   <table align="center" width="70%" border="0" cellspacing="0"  cellpadding="0" >
                                                       
                                                        <tr align="right"> 
                                                            <td  colspan="4"  class="headerText">
                                                                <% if(request.getAttribute(ApplicationConstants.RESULT_MSG) != null){
                                                                    out.println(request.getAttribute(ApplicationConstants.RESULT_MSG));
                                                                }%>
                                                                <s:hidden name="accountId" value="%{accountId}"/>
                                                                <s:hidden name="id" value="%{currentOpportunity.id}"/>
                                                                     <s:hidden name="leadId" value="%{leadId}" id="leadId"/> 
                                                                <s:if test="%{actionString=='editOpportunity'}">
                                                                    <s:hidden name="previousInsideSalesId" value="%{currentOpportunity.insideSalesId}"/>
                                                                      <s:hidden name="previousRegionalMgrId" value="%{currentOpportunity.regionalMgrId}"/>
                                                                        <s:hidden name="previousPracticeMgrId" value="%{currentOpportunity.practiceMgrId}"/>
                                                                </s:if>
                                                                <s:if test="%{leadId != 0}">
                                                                    <a href="<s:url action="../../marketing/doEditLeads.action"><s:param name="accountId" value="%{accountId}"/><s:param name="leadId" value="%{leadId}"/></s:url>" style="align:center;">
                                                                    <img alt="Back to List"
                                                                         src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                         width="66px" 
                                                                         height="19px"
                                                                     border="0" align="bottom"></a>
                                                                </s:if>
                                                                <s:else>
                                                                <a href="<s:url action="../accounts/getAccount.action"><s:param name="id" value="%{accountId}"/><s:param name="pri" value="%{pri}"/></s:url>" style="align:center;">
                                                                    <img alt="Back to List"
                                                                         src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                         width="66px" 
                                                                         height="19px"
                                                                     border="0" align="bottom"></a></s:else>&nbsp;&nbsp;
                                                                <s:submit name="submit" value="Save" cssClass="buttonBg" />
                                                            </td>
                                                        </tr>
                                                        
                                                        <tr>
                                                           
                                                            <td class="fieldLabel"> Opportunity&nbsp;Title :&nbsp;<font color="red">* </td>
                                                               <td  colspan="3">  
         <s:textfield name="title" cssClass="inputTextBlueLarge1" value="%{currentOpportunity.title}" onchange="fieldLengthValidator(this);changeCase(this);"  id="opportunity" size="50"/> 
     </td>
                                                          
                                                        </tr>
                                                        <tr> 
                                                             <td  class="fieldLabel">State:&nbsp;<font style="color:red;">*</font></td>
                                                            <td> 
                                                                <s:select name="state" id="state" cssClass="inputSelect" list="opportunityStateList"   value="%{currentOpportunity.state}"/>
                                                            </td>
                                                              <td class="fieldLabel">Practice:<font style="color:red;">*</font></td>
                                                                                         
                                                             <td><s:select cssClass="inputTextBlue" list="practiceList" name="practiceName" id="practiceName" onchange="" headerValue="--Please Select--" headerKey="" value="%{currentOpportunity.practiceName}"/></td>
                                                              
                                                           
                                                       <%--       <td class="fieldLabel"> Architect :&nbsp; </td>
                                                            <td>  
                                                                <s:select name="architectId" id="architectId" cssClass="inputTextBlue" list="architectMap"  headerKey="-1" headerValue="--Please Select--" value="%{currentOpportunity.architectId}"/><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/info.jpg" onmouseover="fixedtooltip('Dispalys employees list who are having the tiltle \'Sr. Architect\' and \'Jr.Architect\'.',this,event, 200,-20,40)" onmouseout="delayhidetip()" height="18" width="20">
                                                                
                                                            </td>--%>
                                                            <%--<td class="fieldLabel">Regional Manager :&nbsp;<font color="red">* </td> --%>
                                                             
                                                          
                                                        </tr>
                                                        <tr>
                                                            <td  class="fieldLabel">Type:&nbsp;<font color="red">* </td>
                                                            <td  > 
                                                                <s:select name="type" id="type" cssClass="inputSelect" list="typeList" emptyOption="false" headerKey="-1" headerValue="--Please Select--" value="%{currentOpportunity.type}" onchange="displayMandatory(this);"/>
                                                                
                                                            </td>
                                                             <td  class="fieldLabel">Stage:&nbsp;<font color="red">* </td>
                                                            <td >
                                                                <s:select name="stage" id="stage" cssClass="inputSelect" list="stageList"  headerKey="-1" headerValue="--Please Select--" value="%{currentOpportunity.stage}"/>
                                                            </td>
                                                            
                                                        </tr>
                                                        <tr> 
                                                              <td class="fieldLabel">Inside Sales:&nbsp;<font color="red">*  </td>
                                                            <td> 
                                                                <s:select  name="insideSalesId" id="insideSalesId" cssClass="inputSelect" list="insideSalesMap1"  emptyOption="false" headerKey="-1" headerValue="--Please Select--" value="%{currentOpportunity.insideSalesId}"/><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/info.jpg" onmouseover="fixedtooltip('Dispalys employees list who are having the department \'Sales\' and Practice as\'Inside\'.',this,event, 200,-20,40)" onmouseout="delayhidetip()" height="18" width="20">
                                                            </td>
                                                            <td  class="fieldLabel">Field&nbsp;Sales:&nbsp; </td>
                                                            <td  > 
                                                                <s:select name="bdmId" id="bdmId" cssClass="inputSelect" list="fieldSalesMap" emptyOption="false" headerKey="-1" headerValue="--Please Select--" value="%{currentOpportunity.bdmId}"/><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/info.jpg" onmouseover="fixedtooltip('Dispalys employees list who are having the department \'Sales\' and Practice as \'Fields\'.',this,event, 200,-20,40)" onmouseout="delayhidetip()" height="18" width="20">
                                                            </td>
                                                           
                                                        </tr>
                                                        <tr>
                                                            <td class="fieldLabel">Practice Sales:&nbsp;<span id="practiceSalesStar" style="display: none"><font color="red">*</font></span></td>
                                                            <td > 
                                                                <s:select name="regionalMgrId" id="regionalMgrId" cssClass="inputTextBlue" list="practiceSalesMap" headerKey="-1" headerValue="--Please Select--" value="%{currentOpportunity.regionalMgrId}"/><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/info.jpg" onmouseover="fixedtooltip('Dispalys employees list who are having the department \'Sales\' and Practice as \'Practice\'.',this,event, 200,-20,40)" onmouseout="delayhidetip()" height="18" width="20"> 
                                                            </td> 
                                                             <td class="fieldLabel">Pre-Sales:&nbsp;</td>
                                                            <td > 
                                                                <s:select name="practiceMgrId" id="practiceMgrId" cssClass="inputSelect" list="onsitePMMap" emptyOption="false" headerKey="-1" headerValue="--Please Select--" value="%{currentOpportunity.practiceMgrId}"/><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/info.jpg" onmouseover="fixedtooltip('Dispalys employees list who are having the Role \'Pre-Sales\'.',this,event, 200,-20,40)" onmouseout="delayhidetip()" height="18" width="20">
                                                            </td>
                                                             
                                                          <%--  <td class="fieldLabel">Offshore-Practice Manager:&nbsp;<font color="red">* </td> --%>
                                                         
                                                          
                                                        </tr>
                                                        
                                                        <tr> 
                                                             <td  class="fieldLabel">Due Date :&nbsp;<font color="red">* </td>
                                                            <td> 
                                                                <s:textfield name="dueDate" id="dueDate" cssClass="inputTextBlue" value="%{currentOpportunity.dueDate}"/>
                                                                <a href="javascript:cal1.popup();">
                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                     width="20" height="20" border="0"></a>
                                                            </td>
                                                            <td class="fieldLabel"> SVI# </td>
                                                               <td>  
                                                               <s:textfield name="sviNum" cssClass="inputTextBlue" value="%{currentOpportunity.sviNum}" id="sviNum" onchange="fieldLengthValidator(this);"/> 
                                                               </td>
                                                            
                                                        </tr>
                                                        
                                                        <tr> 
                                                            <td class="fieldLabel">Lead&nbsp;Source:&nbsp;</td>
                                                            <td> 
                                                                <s:hidden name="previousLeadSourceId" id="previousLeadSourceId" value="%{currentOpportunity.leadSourceId}"/>
                                                                
                                                                <s:if test ="%{actionString=='addOpportunity' || currentOpportunity.leadSourceId==0 || currentOpportunity.leadSourceId==-1}">
                                                                     <s:select name="leadSourceId" id="leadSourceId" cssClass="inputSelect" list="leadsMap" emptyOption="false" headerKey="-1" headerValue="--Please Select--" value="%{currentOpportunity.leadSourceId}"/><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/info.jpg" onmouseover="fixedtooltip('Dispalys lead source list belongs to current account.',this,event, 200,-20,40)" onmouseout="delayhidetip()" height="18" width="20">
                                                                </s:if><s:else>
                                                                    <s:hidden name="leadSourceId" id="leadSourceId"  value="%{currentOpportunity.leadSourceId}"/>
                                                                    <font color="green" size="2px"><s:property value="%{leadTitle}"/></font>
                                                                </s:else>
                                                               
                                                            </td>
                                                            <td  class="fieldLabel">Value of Opportunity($):&nbsp;<font color="red">* </td>
                                                            <td> 
                                                                <s:textfield name="value" id="value" cssClass="inputTextBlue" value="%{currentOpportunity.value}" onchange="validatenumber(this);" title="Please only enter the total value of the opportunity. If this is for a an hourly position(s), please calculate the total value by multiplying the duration by the hourly rate. DO NOT just enter the hourly rate."/> 
                                                            </td>
                                                            
                                                        </tr>
                                                        <tr>
                                                       
                                                            <td  class="fieldLabel forRemove"> SVI Stage: </td>
                                                            <td colspan="3">
                                                         <s:select headerKey="" headerValue="--Please Select--" id="sviList"  name="sviList"  list="{'Identifying/Validating','Validated/Qualifing','Won/Implementing','Lost'}" value="%{currentOpportunity.sviList}" cssClass="inputSelect"/>
                                                            </td>
                                                        
                                                        </tr>
                                                        <tr>  <td class="fieldLabel forRemove">Contacts :</td>
                                                                    <td colspan="3">
                                                                        <s:select  name="accContacts"  id="accContacts"  list="contactsMap" cssClass="inputTextBlueLargeAccount"  style="width:435px; height: 93px" multiple="true" value="%{currentOpportunity.contactsList}"/>
                                                                    <s:hidden name="contactsHidden" id="contactsHidden" />

                                                                    </td>
                                                                </tr>
                                                        <tr>
                                                            <td  class="fieldLabel">Description:&nbsp;<font color="red">*  </td>
                                                            <td  colspan="3"> 
                                                                <s:textarea name="description" cols="72" rows="3" cssClass="inputTextarea1" value="%{currentOpportunity.description}" onchange="fieldLengthValidator(this);changeCase(this);"  id="description"/>
                                                                
                                                            </td>
                                                        </tr>
                                                        <tr> 
<!--                                                            <td class="fieldLabel"> Practice Manager:&nbsp;</td>-->
                                                            <td> 
                                                             <%--  <s:select name="offshorePMId" id="offshorePMId" cssClass="inputSelect" list="offshorePMMap" emptyOption="false" headerKey="-1" headerValue="--Please Select--" value="%{currentOpportunity.offshorePMId}"/><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/info.jpg" onmouseover="fixedtooltip('Dispalys employees list who are having the tiltle \'Practice Manager\', \'Project Manager\', \'Sr. Practice Manager\' and \'Jr. Practice Manager\'.',this,event, 200,-20,40)" onmouseout="delayhidetip()" height="18" width="20"> --%>
                                                                <s:hidden name="offshorePMId" id="offshorePMId" cssClass="inputSelect"   value=""/>
                                                                
                                                            </td>
<!--                                                            <td  class="fieldLabel">VP Sales :&nbsp;</td>-->
                                                            <td  > 
                                                             <%--   <s:select name="vpId" id="vpId" list="vpMap" cssClass="inputTextBlue"   headerKey="-1" headerValue="--Please Select--"  value="%{currentOpportunity.vpId}"/><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/info.jpg" onmouseover="fixedtooltip('Dispalys employees list who are having the tiltle \'Sr. Vice President\' and \'Vice President\'.',this,event, 200,-20,40)" onmouseout="delayhidetip()" height="18" width="20"> --%>
                                                                <s:hidden name="vpId" id="vpId" list="vpMap" cssClass="inputTextBlue"    value=""/> 
                                                            </td>
                                                           
                                                            
                                                        </tr>
                                                    </table>

                                                </s:form>
                                                
                                                <script type="text/JavaScript">
                                                var cal1 = new CalendarTime(document.forms['frmOpportunity'].elements['dueDate']);
				                 cal1.year_scroll = true;
				                   cal1.time_comp = false;
                                                </script>
                                                <%--  </sx:div > --%>
                                            </div>
                                            <!--//END TAB : -->
                                    
                                            <%-- </sx:tabbedpanel> --%>
                                        </div>
                                        <script type="text/javascript">

var countries=new ddtabcontent("accountTabs")
countries.setpersist(false)
countries.setselectedClassTarget("link") //"link" or "linkparent"
countries.init()

                                        </script>
                                        <!--//END TABBED PANNEL : -->
                                
                                        <!--//START TABBED PANNEL : -->
                                        <%--    <sx:tabbedpanel id="opportunityListPannel" cssStyle="width: 840px; height: 225px;padding-left:10px;padding-top:5px;" doLayout="true"> --%>
                                       <% 
                                         if(!request.getParameter("addingOppurtunties").equalsIgnoreCase("addOppur")) 
                                           
                                         {%>
                                        <ul id="accountTabs1" class="shadetabs" >
                                            <li ><a href="#" class="selected" rel="activitiesTab"  >Activity List </a></li>
                                            
                                             <li><a href="#" rel="attachmentsList" >Attachments</a></li>
                                            
                                        </ul>
                                       
                                        <div  style="border:1px solid gray; width:840px;height: 255px; overflow:auto; margin-bottom: 1em;">
                                            
                                            <!--//START TAB : -->
                                            <%--  <sx:div id="activitiesTab" label="Opportunity List" cssStyle="overflow:auto;"> --%>
                                            <div id="activitiesTab" class="tabcontent"  > 
                                                <%
                                                
                                                /* String Variable for storing current position of records in dbgrid*/
                                                strTmp = request.getParameter("txtCurr");
                                                
                                                intCurr = 1;
                                                if (strTmp != null)
                                                    intCurr = Integer.parseInt(strTmp);
                                                
                                                /* Specifing Shorting Column */
                                                strSortCol = request.getParameter("Colname");
                                                
                                                if (strSortCol == null) strSortCol = "firstName";
                                                
                                                strSortOrd = request.getParameter("txtSortAsc");
                                                if (strSortOrd == null) strSortOrd = "ASC";
                                                
                                                if(request.getAttribute("id") != null){
                                                    OpportunityId = (String)request.getParameter("id");
                                                }
                                                
                                                try{
                                                    
                                                    /* Getting DataSource using Service Locator */
                                                    connection = ConnectionProvider.getInstance().getConnection();
                                                    
                                                    /* Sql query for retrieving resultset from DataBase */
                                                    
                                                    queryString ="SELECT * FROM tblCrmActivity WHERE OpportunityId ="+OpportunityId +" ORDER BY CreatedDate DESC";
//                                                    queryString = queryString + " WHERE AccountId ="+OpportunityId+" ORDER BY CreatedDate";
                                                    String opportunityAction = "../opportunities/opportunity.action";
                                                    if(OpportunityId != null){
                                                        opportunityAction = opportunityAction+"?accountId="+request.getAttribute("accountId");
                                                    }
                                                 //  out.println(request.getParameter("id")); 
                                                %>
                                                
                                                <form action="" name="frmDBGrid"> 
                                                    <table cellpadding="0" cellspacing="0" width="100%">
                                                        <tr>
                                                            <td class="headerText">
                                                                <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <!-- DataGrid for list all activities -->
                                                                <grd:dbgrid id="tblCrmOpportunity" name="tblCrmOpportunity" width="100" pageSize="10" 
                                                                            currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                            dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                    
                                                                    <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                                   imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                                  />
                                                                                   <grd:rownumcolumn     headerText="SNo"  width="5"/>     
                                                                     <grd:datecolumn dataField="CreatedDate"  headerText="Created Date"  dataFormat="MM-dd-yyyy" width="15" />
                                                                      <grd:anchorcolumn dataField="ActivityType" linkUrl="javascript:getActivityData('{Id}')" headerText="ActivityType"
                                                                        linkText="{ActivityType}"  width="10" />
                                                                    <%-- <grd:anchorcolumn dataField="Description" linkUrl="javascript:doGetAll('{Id}')" headerText="Description"
                                                                        linkText="Click To View"  width="10" /> --%>
                                                                     <grd:anchorcolumn dataField="Comments" linkUrl="javascript:doGetComments('{Id}')" headerText="Comments"
                                                                    linkText="Click To View"  width="10" />
                                                                     
                                                                <%--     <grd:textcolumn dataField="Comments"  headerText="Comments"  width="20"/> --%>
                                                                      <grd:textcolumn dataField="STATUS"  headerText="STATUS" width="10"/> 
                                                                     <grd:textcolumn dataField="CreatedById"  headerText="CreatedBy" width="10"/> 
                                                                </grd:dbgrid>
                                                                <!-- these components are DBGrid Specific  -->
                                                                <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                                <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                                <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                                                                <s:hidden name="id" value="%{id}"/>
                                                             
                                                                <s:hidden name="accountId" value="%{accountId}"/>
                                                                
                                                                <input TYPE="hidden" NAME="addingOppurtunties" VALUE="<%=request.getParameter("addingOppurtunties")%>">
                                                            </td>
                                                        </tr>
                                                    </table>    
                                                </form>  
                                                <%
                                                connection.close();
                                                connection = null;
                                                }catch(Exception se){
                                                System.out.println("Exception in Opportunity Add "+se);
                                                }finally{
                                                if(connection!= null){
                                                connection.close();
                                                connection = null;
                                                }
                                                }
                                                %>
                                                
                                                <%-- </sx:div> --%>
                                            </div>
                                            
                                            <!-- Attachment Div Start -->
                                             <%--    <sx:div id="attachmentsList" label="Resume Attachments"> --%>
                                    <div id="attachmentsList" class="tabcontent" > 
                                        <%
                                            /* String Variable for storing current position of records in dbgrid*/
                                            strTmp = request.getParameter("txtAttachCurr");

                                            if (strTmp != null) {
                                                intCurr = Integer.parseInt(strTmp);
                                            }

                                            /* Specifing Shorting Column */
                                            strSortCol = request.getParameter("txtVendorAttachSortCol");

                                            if (strSortCol == null) {
                                                strSortCol = "DateOfUpload";
                                            }

                                            strSortOrd = request.getParameter("txtVendorAttachSortAsc");
                                            if (strSortOrd == null) {
                                                strSortOrd = "ASC";
                                            }
                                             try 
                                             {
                                               connection = ConnectionProvider.getInstance().getConnection();
    
                                            
                                          //  System.out.println("currentAccountId"+request.getAttribute("currentAccountId")); 
                                            /* Sql query for retrieving resultset from DataBase */
                                            queryString = "Select Id,CASE WHEN (AttachmentType!='-1') THEN AttachmentType ELSE '-' END AS AttachmentType,AttachmentName,DateUploaded,UploadedBy,ObjectId,ObjectType from tblAttachments WHERE";
                                            queryString = queryString + " ObjectId =" + request.getAttribute("Id")  + " ORDER BY DateUploaded DESC";
                                           //   out.println("queryString "+queryString);
                                            String attachmentAction = "../../crm/requirement/getRequirementAttachment.action";

                                            if (request.getAttribute("currentAccountId") != null) {
                                                attachmentAction = attachmentAction + "?objectId=" + request.getAttribute("Id") +"&accId=" + request.getAttribute("accountId")+"&oppFlag=opp";
                                            }
                                        %>

                                        <form action=""  method="post" name="frmAttachGrid"> 
                                            <table cellpadding="0" cellspacing="0" width="100%">
                                                <tr>
                                                    <td class="headerText">
                                                        <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <!-- DataGrid for list all Attachments -->
                                                        <grd:dbgrid id="tblReqAttachments" name="tblReqAttachments" width="100" pageSize="10" 
                                                        currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                        dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">

                                                            <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                           imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                           addImage="../../includes/images/DBGrid/Add.png" 
                                                            addAction="<%=attachmentAction%>"
                                                                           scriptFunction="getAttachments"/>
                                                            <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" />
                                                            
                                                            <grd:textcolumn dataField="AttachmentType"  headerText="Attachment Type" width="30"/>  
                                                            <grd:textcolumn dataField="AttachmentName"  headerText="Attachment Name"   width="30" sortable="true"/> 

                                                            <grd:datecolumn dataField="DateUploaded"  headerText="Date Uploaded" dataFormat="MM-dd-yyyy HH:mm:SS" width="20"/>
                                                            <grd:imagecolumn headerText="Download" width="4" HAlign="center" 
                                                                             imageSrc="../../includes/images/download_11x10.jpg"
                                                                             linkUrl="../requirement/getAttachmentDownload.action?Id={Id}" imageBorder="0"
                                                                             imageWidth="11" imageHeight="10" alterText="Download"></grd:imagecolumn>
                                                        </grd:dbgrid>
                                                        <!-- these components are DBGrid Specific  -->



                                                       <input TYPE="hidden" NAME="txtAttachCurr" VALUE="<%=intCurr%>">                                                            
                                                      <!--  <input TYPE="hidden" NAME="txtAccActCurr" VALUE="">
                                                        <input TYPE="hidden" NAME="txtContactCurr" VALUE="">                                                            
                                                        <input TYPE="hidden" NAME="txtOppCurr" VALUE=""> -->

                                                        <input TYPE="hidden" NAME="submitFrom" VALUE="dbGrid">
                                                        <input type="hidden" name="accId" value="<%=currentAccountId%>">                                                            
                                                        <input TYPE="hidden" NAME="txtVendorAttachSortCol" VALUE="">
                                                        <input TYPE="hidden" NAME="txtVendorAttachSortAsc" VALUE="">
                                                          <input TYPE="hidden" NAME="txtVendorAttCur" VALUE="">
                                                          <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                        <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">

                                                    </td>
                                                </tr>
                                            </table>    
                                        </form>  
                                             <%
                                                } catch (Exception ex) {
                                                    out.println(ex.toString());
                                                } finally {
                                                    if (connection != null) {
                                                        connection.close();
                                                    }
                                                }
                                            %>             
                                    </div>
                                            
                                            <!-- Attachment Div End -->
                                            <!--//END TAB : -->
                                    
                                            <%--   </sx:tabbedpanel> --%>
                                        </div>
                                      <%  }else {%>
                                      
                                       <ul id="accountTabs1" class="shadetabs" >
                                            <li ><a href="#" class="selected" rel="opportunitiesTab"  >Opportunity List </a></li>
                                            
                                        </ul>
                                       
                                        <div  style="border:1px solid gray; width:840px;height: 255px; overflow:auto; margin-bottom: 1em;">
                                            
                                            <!--//START TAB : -->
                                            <%--  <sx:div id="activitiesTab" label="Opportunity List" cssStyle="overflow:auto;"> --%>
                                            <div id="opportunitiesTab" class="tabcontent"  > 
                                                <%
                                                
                                                /* String Variable for storing current position of records in dbgrid*/
                                                strTmp = request.getParameter("txtCurr");
                                                
                                                intCurr = 1;
                                                if (strTmp != null)
                                                    intCurr = Integer.parseInt(strTmp);
                                                
                                                /* Specifing Shorting Column */
                                                strSortCol = request.getParameter("Colname");
                                                
                                                if (strSortCol == null) strSortCol = "firstName";
                                                
                                                strSortOrd = request.getParameter("txtSortAsc");
                                                if (strSortOrd == null) strSortOrd = "ASC";
                                                
                                                if(request.getAttribute("currentAccountId") != null){
                                                    currentAccountId = (String)request.getAttribute("currentAccountId");
                                                }
                                                
                                                try{
                                                    
                                                    /* Getting DataSource using Service Locator */
                                                    connection = ConnectionProvider.getInstance().getConnection();
                                                    
                                                    /* Sql query for retrieving resultset from DataBase */
                                                    queryString ="SELECT Id,AccountId,Title,Description,Value,Date(DueDate) as DueDate,CreatedDate FROM tblCrmOpportunity";
                                                    queryString = queryString + " WHERE AccountId ="+currentAccountId+" ORDER BY CreatedDate";
                                                    String opportunityAction = "../opportunities/opportunity.action";
                                                    if(currentAccountId != null){
                                                        opportunityAction = opportunityAction+"?accountId="+currentAccountId+"&addingOppurtunties=addOppur";
                                                    }
                                                
                                                %>
                                                
                                                <form action="" name="frmDBGrid"> 
                                                    <table cellpadding="0" cellspacing="0" width="100%">
                                                        <tr>
                                                            <td class="headerText">
                                                                <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <!-- DataGrid for list all activities -->
                                                                <grd:dbgrid id="tblCrmOpportunity" name="tblCrmOpportunity" width="100" pageSize="10" 
                                                                            currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                            dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                    
                                                                    <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                                   imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                                   addImage="../../includes/images/DBGrid/Add.png" 
                                                                                   addAction="<%=opportunityAction%>"/>
                                                                    
                                                                    <grd:imagecolumn headerText="Edit" width="4" HAlign="center" imageSrc="../../includes/images/DBGrid/newEdit_17x18.png"
                                                                                     linkUrl="getOpportunity.action?id={id}&addingOppurtunties=editOppur" imageBorder="0"
                                                                                     imageWidth="16" imageHeight="16" alterText="Click to edit"></grd:imagecolumn>               
                                                                    
                                                                    <grd:textcolumn dataField="Title"  headerText="Title"   width="25"/> 
                                                                    <grd:textcolumn dataField="Description"  headerText="Description"   width="25"/> 
                                                                    <grd:numbercolumn dataField="Value"  dataFormat="$0.00" headerText="Value" width="5" HAlign="right"/> 
                                                                    <grd:datecolumn dataField="DueDate"  headerText="Due Date"  dataFormat="MM-dd-yyyy" width="18" />
                                                                    <grd:datecolumn dataField="CreatedDate"  headerText="CreatedDate"  dataFormat="MM-dd-yyyy HH:mm:SS" width="18" />
                                                                      
                                                                </grd:dbgrid>
                                                                <!-- these components are DBGrid Specific  -->
                                                                <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                                <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                                <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                                                              
                                                                
                                                                <input TYPE="hidden" NAME="addingOppurtunties" VALUE="<%=request.getParameter("addingOppurtunties")%>">
                                                                <s:hidden name="accountId" value="%{accountId}"/>
                                                            </td>
                                                        </tr>
                                                    </table>    
                                                </form>  
                                                <%
                                                connection.close();
                                                connection = null;
                                                }catch(Exception se){
                                                System.out.println("Exception in Opportunity Add "+se);
                                                }finally{
                                                if(connection!= null){
                                                connection.close();
                                                connection = null;
                                                }
                                                }
                                                %>

                                                
                                            </div>
                                        </div>
                                      <%}%>

                                        <!--//END TABBED PANNEL : -->
                                        <script type="text/javascript">

var countries=new ddtabcontent("accountTabs1")
countries.setpersist(false)
countries.setselectedClassTarget("link") //"link" or "linkparent"
countries.init()

                                        </script>
                                    </td>
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
                <td align="center"> <s:include value="/includes/template/Footer.jsp"/>   </td>
            </tr>
            <!--//END FOOTER : Record for Footer Background and Content-->
            
        </table>
        <!--//END MAIN TABLE : Table for template Structure-->
         
            
            
              <script src="http://code.jquery.com/jquery-1.10.2.js"></script>
        <script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
        <script>
            $(function() {
                $( document ).tooltip();
            });
        </script>
         <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/selectivity-full.css"/>">
            <script type="text/JavaScript" src="<s:url value="/includes/javascripts/selectivity-full.min.js"/>"></script>
            <script type="text/javascript">
		$(window).load(function(){
		
		poplatingvalues();
                
		});
		</script>
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
         $(document).ready(function(){
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
                        poplatingvalues();
                    });
                });
                    
                    if($("#type").val()=='Software Renewal'){
                      //  alert("In ");
                         $("#practiceSalesStar").hide();
                        
                    }else {
                     //   alert("In else");
                       $("#practiceSalesStar").show();
                    }
                    
                    
                    
                    
            });

        </script>
        <script>
            
            function poplatingvalues()
{
   
    var skillCatArry = [];    
    $("#accContacts :selected").each(function(){
        skillCatArry.push($(this).val()); 

    });
    document.getElementById("contactsHidden").value=skillCatArry;
}

function getActivityData(accId) {
    //alert('hi');
    //document.location="/getActivity.action?id="+accId;
    document.location="/Hubble/crm/activities/getActivity.action?id="+accId;
    }
        </script>

        <s:include value="/includes/template/headerScript.html"/>
    </body>
</html>