<%-- 
    Document   : AuthorAndSubTopic
    Created on : Oct 24, 2013, 11:16:51 AM
    Author     : miracle
--%>

<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ page import="com.freeware.gridtag.*" %> 
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.mss.mirage.util.DataSourceDataProvider"%>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%-- <%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<html>
<head>
    <title>Hubble Organization Portal :: Ecertification </title>
    
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
  
   <!-- Enable below option if AJAX usage To Retrieve Regions,Territoris  -->
        <%-- <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CrmAjax.js"/>"></script> --%>
        <!-- Disable below option if AJAX usage To Retrieve Regions,Territoris  -->
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CommonAjax.js"/>"></script>
        
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/StringUtility.js"/>"></script>
        <%--<script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AccountClientValidation.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/SoftwareClientValidation.js"/>"></script> >--%>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/StandardClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
          <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ecertification/AuthorsList.js"/>"></script>
          <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ecertification/EcertStandardClientValidation.js"/>"></script>
    <s:include value="/includes/template/headerScript.html"/>
      <style type="text/css">
            
            .popupItem:hover {
            background: #F2F5A9;
            font: arial;
            font-size:10px;
            color: black;
            }
            
            .popupRow {
            background: #3E93D4;
            }
            
            
            .popupItem {
            padding: 2px;
            width: 100%;
            border: black;
            font:normal 9px verdana;
            color: white;
            text-decoration: none;
            line-height:13px;
            z-index:100;
            }
            
        </style>
     <script type="text/JavaScript">
            
            //For Adding & Editing Employee Status
            function setTempVar1() {
                document.subtopicForm.tempVar.value = 1;
            }
            function setTempVar2() {
                document.subtopicForm.tempVar.value = 2;
            }
           
            function checkManadatoryFields() {
            var techComments = document.getElementById('techLeadComments').value;
            if(techComments == null || techComments == "") {
            alert("please Enter Comments");
            return false;
            }else if (techComments.length >500) {
             alert("Comments length must be less than 500 Chanracters!");
            return false;
            }
            else {
            return true;
            }
            }
            
              
            
            
             function ValidateAuthorsName()
            {
               // alert("Hello");
                var name = document.getElementById("assignedToUID").value;
                var Id = document.getElementById("preAssignEmpId").value;
                if(name==null || name == "")
                {
                    alert("Please Enter  Author Name.");
                    document.getElementById("assignedToUID").value="";
                    document.getElementById("preAssignEmpId").value="";
                    return false;
                }
                if(Id == null || Id == "")
                {
                      alert("Please Select Only from Sugesstion List");
                    document.getElementById("assignedToUID").value="";
                    return false;
                }
                
                return true;
            }
            function checkSubTopicName()
            {
                var subtopic = document.getElementById("subtopic").value;
                if(subtopic == null || subtopic == "")
                {
                    alert("Please enter subtopic");
                    return false;
                    
                }
                return true;
            }
    </script>
    
    
</head>
<!-- <body  class="bodyGeneral" oncontextmenu="return false;" onload="init1();"> -->
<body  class="bodyGeneral" oncontextmenu="return false;">

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
            <td width="850px" class="cellBorder" valign="top" style="padding: 5px 5px 5px 5px;">
                <ul id="accountTabs" class="shadetabs" >
                    <li ><a href="#" class="selected" rel="authorList"  >Authors</a></li>
                    <li ><a href="#" class="selected" rel="subtopicList"  >Subtopics</a></li>
                </ul>
                <!--//START TABBED PANNEL : -->
                                
                <!--//START TABBED PANNEL : -->
                <%--    <sx:tabbedpanel id="employeeUpdatePannel" cssStyle="width:850px; height:510px;padding: 5px 5px 5px 5px;" doLayout="true" useSelectedTabCookie="true">--%>
                <div  style="border:1px solid gray; width:850px;height: 480px; overflow:auto; margin-bottom: 1em;">
                <!--//START TAB : -->
                <%-- <sx:div id="personalDetailsTab" label="Employee Details" cssStyle="overflow:auto;" > --%>
                <div id="authorList" class="tabcontent"  >
                <s:form name="authorForm" id="authorForm" action="authorUpdate" method="post" theme="simple" onsubmit="return checkManadatoryFields();">
                 <s:hidden value="%{topicId}" name="topicId" /> 
                 
                 
           <table cellpadding="1" cellspacing="1" border="0" width="100%">
                
                <tr>
                    <td class="headerText" colspan="6">
                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                            <tr align="right">
                                <td align="left" width="670px">
                                    <% if(request.getAttribute("resultMessage") != null){
                                                    out.println(request.getAttribute("resultMessage"));
                                    }%>
                                </td>
                                
                                 <s:if test="#request.resultMessage!=null">
                                   
                                                <td valign="middle">
                                                    <INPUT type="button" CLASS="buttonBg" value="Back to List" onClick="history.go(-2)">  
                                                </td>
                                            </s:if>
                                            <s:else>
                                                <td valign="middle">
                                            
                                                    <INPUT type="button" CLASS="buttonBg" value="Back to List" onClick="history.go(-1)">
                                                </td>
                                            </s:else>
                                            
                                            
                              
                                <td valign="top">
                                <%--    <s:if test="%{navId == 1}">
                                        <s:submit name="submit" value="Update" cssClass="buttonBg" onclick="setTempVar1();"/>
                                         
                                    </s:if> --%>
                                  
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td class="fieldLabel"  align="right">Practice Name&nbsp;:</td>
                    <td><font color="green" style="font-family: lucida-sans;font-size: 12px;"><s:property value="%{domainName}"/></font></td>
                   <td class="fieldLabel"  align="right">Topic Name&nbsp;:</td>
                    <td><font color="green" style="font-family: lucida-sans;font-size: 12px;"><s:property value="%{topicName}"/></font></td>
                </tr>
   
               
               <tr> 
                                                           <td class="fieldLabel">Author Name&nbsp;:<font color="red" >*</font></td>
                                                           
                                                           <td ><s:textfield name="assignedToUID" id="assignedToUID" onkeyup="fillAuthor();" cssClass="inputTextBlue" value="%{assignedToUID}" theme="simple" readonly="false"/>
                                                               <div id="authorEmpValidationMessage" style="position: absolute; overflow:auto;"></div>  
                                                               <s:hidden name="preAssignEmpId" value="%{preAssignEmpId}" id="preAssignEmpId"/> 
                                                           </td>   
                                                           <%--  <td class="fieldLabel">Status :</td>                  
                                                            <td><s:select  list="{'Active','InActive'}" name="status"  id="status" cssClass="inputSelect"/></td>      --%>
                                                           <s:hidden name="status" value="Active"/>
                                                           <td></td>
                                                         <td>
                        <!--<input type="button" name="addNew" id="addNew" class="buttonBg" value="Add Status" onclick="addStatus();">-->
                        
                            <s:submit id="addStatus" name="addStatus" value="Add Author" cssClass="buttonBg" onclick="return ValidateAuthorsName();"/>
                      
                        <!--<div id="loadMessage" style="display: none" align="center" class="error" >
                            <b>Loading.... Please Wait....</b>
                        </div>-->
                    </td>
                                                       </tr>
                
                <tr>
                    <tr>
                        <td colspan="6">
                            <table cellpadding="2" cellspacing="1" width="98%" class="gridTable">
                                <tr class="gridHeader">
                                    
                                    <td width="6%" class="gridHeader" >AuthorId</td>
                                    <td width="8%" class="gridHeader">CreatedBy</td>
                                    <td width="8%" class="gridHeader">CreatedDate</td>
                                    <td width="8%" class="gridHeader">Status</td>
                                    <td width="8%" class="gridHeader">Delete</td>
                                </tr>
                             
                                <s:iterator value="#request.currentAuthorCollection">
                                    <tr class="gridRowEven">
                                       
                                        <td class="gridColumn" align="left"><s:property value="authorLoginId"/></td>
                                        <td class="gridColumn" align="left"><s:property value="createdBy"/></td>
                                        <td class="gridColumn" align="left"><s:property value="createdDate"/></td>
                                        <td class="gridColumn" align="left"><s:property value="authorStatus"/></td>
                                       
                                      <td class="gridColumn" align="left"><a href="javascript:changeStatus(<s:property value="authorId"/>,<s:property value="topicId"/>,'<s:property value="authorLoginId"/>');" ><img src="../includes/images/DBGrid/Delete.png"></a></td>
                                    </tr>
                                </s:iterator>
                                <tr class="gridPager">
                                    <td colspan="8">&nbsp;</td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                
                
            </table> 
     
        
                 </s:form>
   
    <%-- </sx:div > --%>
    </div>
     <div id="subtopicList" class="tabcontent"  >
        <s:form action="subtopicUpdate" theme="simple" method="post" id="subtopicForm" name="subtopicForm" >
       
            <s:hidden value="%{topicId}" name="topicId"/> 
                 <s:hidden name="tempVar" id="tempVar" value="%{tempVar}"/>
                <s:hidden name="navId" id="navId" value="%{navId}"/>
           <table cellpadding="1" cellspacing="1" border="0" width="100%">
                
                <tr>
                    <td class="headerText" colspan="6">
                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                            <tr align="right">
                                <td align="left" width="670px">
                                    <% if(request.getAttribute("subtopicResultMessage") != null){
                                                    out.println(request.getAttribute("subtopicResultMessage"));
                                    }%>
                                </td>
                                
                                 <s:if test="#request.subtopicResultMessage!=null">
                                                <td valign="middle">
                                                    <INPUT type="button" CLASS="buttonBg" value="Back to List" onClick="history.go(-2)">  
                                                </td>
                                            </s:if>
                                            <s:else>
                                               
                                                <td valign="middle">
                                                   
                                                    <INPUT type="button" CLASS="buttonBg" value="Back to List" onClick="history.go(-1)">
                                                </td>
                                            </s:else>
                                            
                                            
                              
                                <td valign="top">
                                    <s:if test="%{navId == 1}">
                                        <s:submit name="submit" value="Update" cssClass="buttonBg" onclick="setTempVar1();return checkSubTopicName();"/>
                                         <s:hidden name="subTopicId" value="%{topicsVTO.subTopicId}"/>
                                    </s:if>
                                  
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td class="fieldLabel"  align="right">Practice Name:</td>
                    <td><font color="green" style="font-family: lucida-sans;font-size: 12px;"><s:property value="%{domainName}"/></font></td>
                   <td class="fieldLabel"  align="right">Topic Name:</td>
                    <td><font color="green" style="font-family: lucida-sans;font-size: 12px;"><s:property value="%{topicName}"/></font></td>
                </tr>
                <tr>
              
                    
                  <td class="fieldLabel" width="30%">Sub Topic Name:<font color="red" >*</font></td>
                  <td width="20%"><s:textfield name="subtopic" id="subtopic"  cssClass="inputTextBlue" onchange="fieldLenghtValidator(this);"  value="%{topicsVTO.subtopic}"  theme="simple"/></td>
                  <td></td>  <td>
                        <s:if test="%{navId == 0}">
                            <s:submit id="addSubtopic" name="addSubtopic" value="Add Subtopic" cssClass="buttonBg" onclick="setTempVar2();return checkSubTopicName();"/>
                         </s:if >
                        <!--<div id="loadMessage" style="display: none" align="center" class="error" >
                            <b>Loading.... Please Wait....</b>
                        </div>-->
                    </td>                                   
                </tr>
               
               
                
              
                <tr>
                    <tr>
                        <td colspan="6">
                            <table cellpadding="2" cellspacing="1" width="98%" class="gridTable">
                                <tr class="gridHeader">
                                    <td width="4%" class="gridHeader" >Edit</td>
                                    
                                    <td width="8%" class="gridHeader">SubTopic Name</td>
                                    
                                    <td width="6%" class="gridHeader" >Status</td>
                                   <td width="8%" class="gridHeader">Delete</td>  
                                </tr>
                                <%--
                                private int subTopicId;
    private String subtopic;
    private String subTopicStatus;--%>
                                <s:iterator value="#request.currentSubtopicCollection">
                                    <tr class="gridRowEven">
                                        <td class="gridColumn" align="left"><a href="getSubTopic.action?subTopicId=<s:property value="subTopicId"/>&navId=1"><img src="../includes/images/DBGrid/newEdit_17x18.png"></a></td>
                                        <td class="gridColumn" align="left"><s:property value="subtopic"/></td>
                                        <td class="gridColumn" align="left"><s:property value="subTopicStatus"/></td>
                                        
                                       
                                       
                                         <td class="gridColumn" align="left"><a href="javascript:doDeleteSubtopic(<s:property value="subTopicId"/>,<s:property value="topicId"/>,'<s:property value="subtopic"/>');" ><img src="../includes/images/DBGrid/Delete.png"></a></td>
                                    </tr>
                                </s:iterator>
                                <tr class="gridPager">
                                    <td colspan="8">&nbsp;</td>
                                </tr>
                            </table>
                        </td>
                    </tr>
               
                
            </table> 
     
        </s:form>
       
        
        <%-- </sx:div> --%>
    </div>
    
    <!--//END TAB : -->
    
  
    <!-- Other Details END -->
    
    <%--  </sx:tabbedpanel> --%>
    </div>
    <script type="text/javascript">

var countries=new ddtabcontent("accountTabs")
countries.setpersist(true)
countries.setselectedClassTarget("link") //"link" or "linkparent"
countries.init()

    </script>
    <!--//END TABBED PANNEL : -->
    
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
<tr>
                <td>
                    
                    <div style="display: none; position: absolute; top:170px;left:320px;overflow:auto;" id="menu-popup">
                        <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" />
                    </div>
                    
                </td>
            </tr>
</table>
<!--//END MAIN TABLE : Table for template Structure-->
<script type="text/javascript">
		$(window).load(function(){
	init1();
		});
		</script>
</body>
</html>

