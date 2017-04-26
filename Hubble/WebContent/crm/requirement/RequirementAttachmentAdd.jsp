



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
        
        <title>Hubble Organization Portal :: Attachment Adding</title>
         <%-- <sx:head cache="false"/> --%>
        
        <%--This link for ToolTip css --%>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tooltip.css"/>">
        <%--This is End for ToolTip css --%>
         
        <%--This link for ToolTip js --%>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tooltip.js"/>"></script>
        <%--This End for ToolTip js --%>
        
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CrmAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CommonAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/projectsValidations/IssueAttachmentClientValidation.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>        
    </head>
    
    <body>
        
        <%!
        /* Declarations */
        Connection connection;
        String queryString;
        String currentAccountId;
        String strTmp;
        String strSortCol;
        String strSortOrd;
        
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
                    <table class="templateTableLogin" cellpadding="0" cellspacing="0">
                        <tr>
                            
                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                            <td width="150px;" class="leftMenuBgColor" valign="top">
                                <s:include value="/includes/template/LeftMenu.jsp"/>
                            </td>
                            <!--//END DATA COLUMN : Coloumn for LeftMenu-->
                            
                            <!--//START DATA COLUMN : Coloumn for Screen Content-->
                            <td width="850px" class="cellBorder" valign="top">
                                <table cellpadding="0" cellspacing="0" width="100%">
                                  
                                    <tr>
                                        <td valign="top" style="padding-left:10px;padding-top:5px;">
                                            
                                            <ul id="attachmentPannel" class="shadetabs">
                                            <li><a href="#" class="selected" rel="attachmentTab">Attachment Details</a></li>                                             
                                            </ul>
                                            
                                            <!--//START TABBED PANNEL : -->
                                            <%-- <sx:tabbedpanel id="attachmentPannel" cssStyle="width: 840px; height: 420px;padding-left:10px;padding-top:5px;" doLayout="true"> --%>
                                            <div  style="border:1px solid gray; width:840px; height: 420px; overflow:auto; margin-bottom: 1em;">
                                                
                                                <!--//START TAB : -->
                                                <%-- <sx:div id="attachmentTab" label="Attachment Details"> --%> 
                                                <div id="attachmentTab" class="tabcontent">
                                                    
                                                    <s:form name="attachmentForm" action="requrementAttachmentAdd" method="POST" enctype="multipart/form-data" theme="simple">
                                                        
                                                        
                                                        <s:hidden name="objectId" value="%{objectId}"/>
                                                         <s:hidden name="accId" value="%{accId}"/>
                                                         <s:hidden name="oppFlag" value="%{oppFlag}"/>
                                                        <table cellpadding="1" cellspacing="1" border="0" width="100%">
                                                            
                                                            
                                                            <tr align="right">
                                                                <td class="headerText" colspan="6">
                                                                    <% if(request.getAttribute(ApplicationConstants.RESULT_MSG) != null){
                                                                    out.println(request.getAttribute(ApplicationConstants.RESULT_MSG));
                                                                    }%>
                                                                    <s:hidden name="accountId" value="%{objectId}"/>
                                                               
                                                                    <s:submit cssClass="buttonBg" value="Submit"/>
                                                                    <s:reset cssClass="buttonBg" value="Reset"/>                                                        
                                                                </td>
                                                            </tr>  
                                                            <%
                                                            if(request.getParameter("resultMessage")!=null)
                                                            {
                                                                 out.println(request.getParameter("resultMessage"));
                                                            }
                                                            %>
                                                            <tr>
                                                            
                                                           
                                                                 <%
                                                                 
                                                                 if(request.getParameter("oppFlag")!=null && "opp".equals(request.getParameter("oppFlag")))
                                                                   
                                                                        {
                                                                 %>
                                                                   <td class="fieldLabel">Opportunity Title :&nbsp;<font color="red">*</td>
                                                                 <td>
                                                           
                                                               <a class="navigationText" href="<s:url action="../opportunities/getOpportunity.action">
                                                                       <s:param name="id" value="%{objectId}"/>
                                                                   <s:param name="accountId" value="%{accId}"/>
                                                               
                                                                  </s:url>&addingOppurtunties=editOppur&pri=All">  <%-- %{currentContact.accountId}--%>
                                                                   <s:property value="%{title}"/></a>      
                                                               </td>
                                                                    <td class="fieldLabel" width="200px" align="right">Attachment Type :</td>
                                                                  <td><s:select headerKey="-1" headerValue="--Please Select--" list="{'SOW','project scope','presentation'}" name="attachmentType" id="attachmentType" cssClass="inputSelect" /></td>

                                                                 
                                                                 <%
                                                                         }
                                                                else{
                                                                        
                                                                   %>
                                                                    <td class="fieldLabel">Job Title :&nbsp;<font color="red">*</td>
                                                                 <td>
                                                           
                                                               <a class="navigationText" href="<s:url action="getRequirement.action">
                                                                       <s:param name="objectId" value="%{objectId}"/>
                                                                   <s:param name="accId" value="%{accId}"/>
                                                                  </s:url>">  <%-- %{currentContact.accountId}--%>
                                                                   <s:property value="%{title}"/></a>      
                                                               </td>
                                                                    <td class="fieldLabel" width="200px" align="right">Attachment Type :</td>
                                                                  <td><s:select headerKey="-1" headerValue="--Please Select--" list="{'Job Description'}" name="attachmentType" id="attachmentType" cssClass="inputSelect" /></td>
                                                                   
                                                                   <%
                                                                    }
                                                                    %>
                                                             </tr>
                                                                <tr>
                                                                    <td class="fieldLabel">Attachment Title :</td>                                                    
                                                                    <td><s:textfield name="attachmentName" cssClass="inputTextarea" onchange="attachmentNameValidate(document.attachmentForm.attachmentName.value);"/></td> 
                                                            
                                                                    <td class="fieldLabel">File:</td>
                                                                   <td><s:file name="upload" label="File" cssClass="inputTextBlueLarge"  id="attachmentFileName" onchange="validateFileSize(this);attachmentFileNameValidate();" theme="simple" />
                                                                        <s:hidden name="attachType"/>
                                                                    </td>
                                                                </tr> 
                                                         
                                                        </table>
                                                        
                                                    </s:form>
                                                    
                                                </div>
                                                <%-- </sx:div> --%>
                                                <!--//END TAB : -->
                                     
                                            </div>    
                                            <%-- </sx:tabbedpanel> --%>
                                            <!--//END TABBED PANNEL : -->
                                            
                                            <script type="text/javascript">

var countries=new ddtabcontent("attachmentPannel")
countries.setpersist(false)
countries.setselectedClassTarget("link") //"link" or "linkparent"
countries.init()

</script>
                                            
                                        </td>
                                    </tr>
                                </table>            
                                
                                
                                <!--//START TABBED PANNEL : --> 
                                
                                <!--//END TABBED PANNEL : -->
                            </td>
                            <!--//END DATA COLUMN : Coloumn for Screen Content-->
                        </tr>
                    </table>
                </td>
            </tr>
            <!--//END DATA RECORD : Record for LeftMenu and Body Content-->
            
            <!--//START FOOTER : Record for Footer Background and Content-->
            <tr class="footerBg">
                <td align="center"><s:include value="/includes/template/Footer.jsp"/></td>
            </tr>
            <!--//END FOOTER : Record for Footer Background and Content-->
            
        </table>
        <!--//END MAIN TABLE : Table for template Structure-->
     
        
        
    </body>
</html>
