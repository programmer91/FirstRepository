<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
<script  type="text/javascript" >
    function performAction(action,element) {
        url = CONTENXT_PATH+action+"?"+element.id+"="+element.value;
        document.location = url;
    }
</script>

<table width="100%" cellpadding="0" cellspacing="0" border="0">
    <tr>
        <td width="135px">
            <img alt="Hubble" 
                 src="<s:url value="/includes/images/hubbleLogo_135x32_2.gif"/>" 
                 width="135px" 
                 height="30px">
        </td>
        <td width="595px" align="center" class="userInfo">
           
             <s:if test="#session.empId != null ">
                Welcome&nbsp;&nbsp;<s:property value="#session.sesConsultantName"/>
            </s:if>
            
            
        </td>
        
         <td width="360px" align="center" valign="bottom">
             <!-- Cre Login start-->
              <s:if test="#session.empId == null ">
                <a class="menuBarLogInLink" href="<s:url value="../general/crelogin.action"/>">
                    <img alt="Home" 
                         src="<s:url value="/includes/images/spacer.gif"/>" 
                         width="59px" 
                         height="13px" border="0">
                </a>
            </s:if>
            
          
            <!-- Cre Login End-->
            
          <%-- 
       <s:if test=" #session.empId != null">
               <a class="menuBarLogoutLink" href="<s:url value="../general/crelogout.action"/>">
                    <img alt="Logout" 
                         src="<s:url value="/includes/images/spacer.gif"/>" 
                         width="59px" 
                         height="13px" border="0">
                </a>
            </s:if>  
           
         --%>
        </td>
    </tr>
    
</table>
