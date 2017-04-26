<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%-- <%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<html>
<head>
    <title>Hubble Organization Portal :: Contact Login Screen </title>
    <%-- <sx:head cache="true"/>--%>
    
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CustomerPopup.js"/>"></script>  
     <%--<script type="text/JavaScript" src="<s:url value="/includes/javascripts/customer/CreateLoginValidation.js"/>"></script> --%>
     <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/StandardClientValidations.js"/>"></script>
    
    <script type="text/JavaScript">
        var selected_element = false;
         function readonly(ele)
         {
             alert("");
              if(selected_element){
        //if already selected reset to the old value
        $(ele).removeAttr('selected').val(selected_element).attr('selected',true);
        return false;
    }
    //put the selected option value in selected_element variable on very first selection
    selected_element = $(ele).val();

         }      
    </script>
    
    
    
</head>

<body background="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/miragewatermark_850X600.gif"> 
<!--//START DATA COLUMN : Coloumn for Screen Content-->
                            
                                

<!--//START TAB : -->
<s:form name="ContactLoginForm" id="ContactLoginForm" theme="simple">
    
    <br> <br> 
    
    
    <table border="0" cellpadding="2" cellspacing="0" align="center"  class="cellBorder">
        <tr>
            <%  if(request.getAttribute(ApplicationConstants.RESULT_MSG)!=null)
               { if(request.getAttribute("val")!=null)
                                         {
          
%>
            <td colspan="2" class="headerText"><s:property  value="#request.resultMessage"/></td>
            <%
                                          }
     else if(request.getAttribute("val")==null){
        
             %>               
                 <td colspan="2"><font color="red" size="2.7"><s:property  value="#request.resultMessage"/></font></td>           
             <%             }
            }
     else {           
             %>
            <td colspan="2" class="headerText">Enter following details :</td>
<%
}
%>
        </tr>
        <tr>
            <td class="fieldLabel" align="right"> EmailId* :</td>
            <td><s:textfield name="emailId" id="emailId" size="30" cssClass="inputTextBlueLarge" value="%{contactEmailId}" readonly="true" /></td>
        </tr>
        <tr>
            <td class="fieldLabel" align="right"> LoginId* :</td>
            <td><s:textfield name="loginId" id="loginId" size="30" cssClass="inputTextBlueLarge" value="%{custLoginId}"/></td>
        </tr>    
         <tr><td><input type="button" class="buttonBg" value="Create Login" onclick="validate()"></td>
         </tr>
         
        <tr>
           <td>
               
            </td>
        </tr>
        
    </table>

    <s:hidden name="Id" id="Id"  value="%{Id}"/> 
    <s:hidden name="accId"  id="accId" value="%{AccountId}"/> 
</s:form>





</body>
</html>