<%@ page contentType="text/html; charset=UTF-8" %>
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
            <s:if test="#session.userId != null && #session.custUserId == null && #session.isUserManager !=1">
                Welcome&nbsp;&nbsp;<s:property value="#session.userName"/>
            </s:if>
            <s:elseif test="#session.custUserId != null && #session.isUserManager !=1">
                Welcome&nbsp;&nbsp;<s:property value="#session.custName"/>
            </s:elseif>
                <s:elseif test="#session.vendorUserId != null && #session.isUserManager !=1">
                Welcome&nbsp;&nbsp;<s:property value="#session.custName"/>
            </s:elseif>
            <s:if test="#session.isUserManager ==1">
                Welcome&nbsp;&nbsp;<s:property value="#session.userName"/>
                <%--<s:if test="#session.roleName != null">
                    &nbsp;Your Role:&nbsp;<s:property value="#session.roleName"/>
                </s:if>--%>
                <s:if test="#session.roleName != null && #session.custUserId == null && #session.vendorUserId == null">
                    &nbsp;Your Role:&nbsp;<s:select theme="simple" list="#session.myRoles" id="roleTypeId" onchange="performAction('/general/roleSubmit.action',this)" name="roleTypeId" value="#session.roleId"  />
                </s:if>
                <s:elseif test="#session.roleName != null && #session.custUserId != null && #session.vendorUserId != null">
                    &nbsp;Your Role:&nbsp; <s:property value="#session.roleName"/>
                </s:elseif>
            </s:if>
            <s:else>
             <%--   <s:if test="#session.roleName != null">
                    &nbsp;Your Role:&nbsp;<s:property value="#session.roleName"/>
                </s:if>       --%>
                <s:if test="#session.roleName != null && #session.custUserId == null &&  #session.vendorUserId == null">
                    &nbsp;Your Role:&nbsp;<s:select theme="simple" list="#session.myRoles" id="roleTypeId" onchange="performAction('/general/roleSubmit.action',this)" name="roleTypeId" value="#session.roleId"  />
                </s:if>
            
            </s:else>
            
        </td>
        
        
        
        <td width="360px" align="right" valign="bottom">
            
                  
            
      
            <s:if test="#session.userId == null && #session.custUserId == null && #session.vendorUserId == null">
                <a class="menuBarLogInLink" href="<s:url value="../customer/login.action"/>">
                    <img alt="Login" 
                         src="<s:url value="/includes/images/spacer.gif"/>" 
                         width="59px" 
                         height="13px" border="0">
                </a>
            </s:if>
            <s:if test="#session.userId != null && #session.custUserId == null">
                <a class="menuBarLogoutLink" href="<s:url value="/general/logout.action"/>">
                    <img alt="Logout" 
                         src="<s:url value="/includes/images/spacer.gif"/>" 
                         width="59px" 
                         height="13px" border="0">
                </a>
                
            </s:if>
            <s:elseif test="#session.custUserId != null">
                 <a class="menuBarLogoutLink" href="<s:url value="/customer/logout.action"/>">
                    
                    <img alt="Logout" 
                         src="<s:url value="/includes/images/spacer.gif"/>" 
                         width="59px" 
                         height="13px" border="0">
                    
                </a>                
            </s:elseif>
            <s:elseif test="#session.vendorUserId != null">
                 <a class="menuBarLogoutLink" href="<s:url value="/vendor/logoutVendor.action"/>">
                    
                    <img alt="Logout" 
                         src="<s:url value="/includes/images/spacer.gif"/>" 
                         width="59px" 
                         height="13px" border="0">
                    
                </a>                
            </s:elseif>
            <a class="menuBarHelpLink" href="<s:url value="/general/help.action"/>">
                <img alt="Help" 
                     src="<s:url value="/includes/images/spacer.gif"/>" 
                     width="44px" 
                     height="13px" border="0">                  
            </a>
            <a class="menuBarContactUsLink" href="<s:url value="/general/contactUs.action"/>">
                <img alt="ContactUs" 
                     src="<s:url value="/includes/images/spacer.gif"/>" 
                     width="82px" 
                     height="13px" border="0">                  
            </a>
        </td>
    </tr>
    
</table>
