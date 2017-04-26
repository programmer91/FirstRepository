<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%--<%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<%@ page import="com.freeware.gridtag.*" %> 
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd" %>
<html>
<head>
    <title>Hubble Organization Portal :: My Review</title>
    <sx:head cache="true"/>
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/clientValidations/EmpSearchClientValidation.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmpStandardClientValidations.js"/>"></script>
    
     <script>
        function clearTimeSheetSearch()
        {
            document.getElementById('firstName').value="";
            document.getElementById("workPhoneNo").value="";
            document.getElementById("currStatus").value="All";
               document.getElementById("departmentId").value="All";
               document.getElementById("orgId").value="All";
               document.getElementById("reportingpersonId").value="All";
               document.getElementById("isManager").checked=false;
               document.getElementById("isTeamLead").checked=false;
        }
        
        
        function validateForm()
        {
            
            var x = document.forms["addReviewForm"]["reviewName"].value;
            var y = document.forms["addReviewForm"]["reviewType"].value;
            var z = document.forms["addReviewForm"]["notes"].value;
            //alert(x);//
            if (x == null || x == "") {
            alert("ReviewName must be filled out");
             return false;
        }  else if (y == null || y == "") {
            alert("ReviewType must be filled out");
             return false;
        }  else if (z == null || z == "") {
            alert("Notes must be filled out");
             return false;
        }else {
            return true;
        }
        
       
      
        
        
        }
        
        </script>

    
    
    <s:include value="/includes/template/headerScript.html"/>
</head>
<body class="bodyGeneral" oncontextmenu="return false;" >

<%!
/* Declarations */
Connection connection;
String queryString;
String strTmp;
String strSortCol;
String strSortOrd;
String submittedFrom;
String searchSubmitValue;
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
    <table class="innerTableLogin" cellpadding="0" cellspacing="0">
    <tr>
        
        <!--//START DATA COLUMN : Coloumn for LeftMenu-->
        <td width="150px;" class="leftMenuBgColor" valign="top">
            <s:include value="/includes/template/LeftMenu.jsp"/>
        </td>
        <td width="850px" class="cellBorder" valign="top" style="padding: 5px 5px 5px 5px;">
         <ul id="accountTabs" class="shadetabs" >
                <li ><a href="#" class="selected" rel="employeeReviewTab"  >
                        <s:if test="%{currentAction=='doAddReview'}">
                            Review&nbsp;Add
                        </s:if><s:else>
                            Review&nbsp;Edit
                        </s:else>
                       
                    
                    
                    </a></li>
            </ul>
              <div  style="border:1px solid gray; width:850px;height: 400px; overflow:auto; margin-bottom: 1em;">
                <!--//START TAB : -->
                <div id="employeeReviewTab" class="tabcontent"  >
          <%--  <s:form theme="simple"  align="center" name="addReviewForm" action="NewAddReview.action" method="post" enctype="multipart/form-data"> --%>
            <s:form theme="simple"  align="center" name="addReviewForm" action="%{currentAction}" method="post" enctype="multipart/form-data" onsubmit="return validateForm();"  >
                <s:hidden id="reviewId" name="reviewId" value="%{currentReview.reviewId}"/>
                <s:hidden id="reviewFlag" name="reviewFlag" value="%{reviewFlag}"/>
                <table align="center" border="0">
                     <tr>
                                                    <td colspan="4" class="headerText">
                                                        <s:property value="#request.resultMessage"/>
                                                        <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="840px" height="13px" border="0">
                                                    
                                                   
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td colspan="4" align="right">
                                                          <s:if test="%{currentAction=='doAddReview'}">
                                    <s:submit cssClass="buttonBg"  align="right" id="save" value="Save" />
                                </s:if><s:else>
                                     <s:submit cssClass="buttonBg"  align="right" id="save" value="Update" />
                                </s:else>
                                                    </td>
                                                </tr>
                      <tr>
                                                          
                                                   
             <td class="fieldLabel" >Type of Reviews :<FONT color="red"  ><em>*</em></FONT> </td>
            
                                     <td width="15%"> <s:select id="reviewType"  name="reviewType"  list="ReviewList" headerKey="" headerValue="--Please Select--"   value="%{currentReview.reviewType}" cssClass="inputSelect" onchange="getAssignedReviews(this);" disabled="False"/></td>
                                     
                                     <td class="fieldLabel" width="15%">Review Name :<font style="color:red;">*</font></td>
                                                    <td ><s:textfield name="reviewName" id="reviewName" cssClass="inputTextarea" value="%{currentReview.reviewName}" onchange="fieldLengthValidator(this);"/></td>
                                     </tr>
                                     
                                     <tr>
                                         <td class="fieldLabel" valign="top">Notes:</td>
                                                    <td colspan="3" valign="top"><s:textarea rows="3" cols="65" name="notes" cssClass="inputTextarea1" value="%{currentReview.notes}" onchange="fieldLengthValidator(this);" id="notes"/></td>
                                                    
                                                    
                                                    </tr>
                                                    
                                                  
                                                    <%if(session.getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString().equalsIgnoreCase("Employee")&& request.getAttribute("reviewFlag").toString().equals("1")){%>
                                                     <s:hidden name="hrComments" id="hrComments" value="%{currentReview.hrComments}"/>
                                                      <tr>
                                         <td class="fieldLabel" valign="top">TL Comments:</td>
                                                    <td colspan="3" valign="top"><s:textarea rows="3" cols="65" name="tlComments" cssClass="inputTextarea1" value="%{currentReview.tlComments}" onchange="fieldLengthValidator(this);" id="tlComments"/></td>
                                                    <td></td>
                                                    
                                                    </tr>
                                           <%}else if(session.getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString().equalsIgnoreCase("Hr")){%>                          
                                     <tr>
                                         <td class="fieldLabel" valign="top">Hr Comments:</td>
                                                    <td colspan="3" valign="top"><s:textarea rows="3" cols="65" name="hrComments" cssClass="inputTextarea1" value="%{currentReview.hrComments}" onchange="fieldLengthValidator(this);" id="hrComments"/></td>
                                                    <td></td>
                                                    <s:hidden name="tlComments" id="tlComments" value="%{currentReview.tlComments}"/>
                                                    </tr>
                                                    <%}else {%>
                                                      <s:hidden name="tlComments" id="tlComments" value="%{currentReview.tlComments}"/>
                                                    <s:hidden name="hrComments" id="hrComments" value="%{currentReview.hrComments}"/>
                                                    <%}%>
                                                    <s:if test="%{currentAction=='doAddReview'}">
                                                          <tr> 
                                                   <%-- <td class="fieldLabel">Upload File Name :<font style="color:red;">*</font></td>
                                                    <td><s:textfield name="attachmentName" id="attachmentName" cssClass="inputTextarea" onchange="return attachmentNameValidate();"/></td> 
                                                    --%>
                                                    <td class="fieldLabel">Upload File :<font style="color:red;">*</font></td>
                                                    <td colspan="3" ><s:file name="upload" label="file" cssClass="inputTextarea" id="attachmentFileName" onchange="attachmentFileNameValidate();"/></td> 
                                                    
                                                </tr>
                                                    </s:if>    <s:else>
                                                        <tr>
                                                            <td class="fieldLabel">Attachments :</td> 
                                    <td colspan="3">   <a class="navigationText" href="<s:url action="download">
                                               <s:param name="reviewId" value="%{currentReview.reviewId}"/>
                                           </s:url>"><s:property value="%{currentReview.attachmentFileName}"/>
                                        </a></td>
                                                        </tr>
                                                    </s:else>
                                                        
                                                      
                                                    
                             
                                                    
                                              
            </table>
             </s:form>                                       
                </div>
              </div>
    
        
        <!--//END DATA COLUMN : Coloumn for LeftMenu-->
                            
        <!--//START DATA COLUMN : Coloumn for Screen Content-->
       
    <script type="text/javascript">

var countries=new ddtabcontent("accountTabs")
countries.setpersist(false)
countries.setselectedClassTarget("link") //"link" or "linkparent"
countries.init()

    </script>
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
