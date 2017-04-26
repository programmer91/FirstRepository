<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

    
    <!--
###############################################################################
# Basic Roles in System
# 1 - Admin
# 2 - Employee
# 3 - Operations
# 4 - Sales
# 5 - Marketing
# 6 - Recruitment 
# 7 - HR
# 8 - Vendor
# 9 - Customer Manager
###############################################################################
-->

    
    <s:if test="#session.roleId == 1">
        <!-- Menu Generation for Admin Role-->
        <s:include value="/includes/template/MenuAdmin.jsp"></s:include>
    </s:if>
    
    
    <s:if test="#session.roleId == 2">
        <!-- Menu Generation for Employee Role-->
        <s:include value="/includes/template/MenuEmployee.jsp"></s:include>
    </s:if>
    
    
    <s:if test="#session.roleId == 3 || #session.roleId == 11 "  >
        <!-- Menu Generation for Operations Role-->
        <s:include value="/includes/template/MenuOperations.jsp"></s:include>
    </s:if>
    
    
    <s:if test="#session.roleId == 4">
        <!-- Menu Generation for Sales Role-->
        <s:include value="/includes/template/MenuSales.jsp"></s:include>
    </s:if>
    
    
    <s:if test="#session.roleId == 5">
        <!-- Menu Generation for Marketing Role-->
        <s:include value="/includes/template/MenuMarketing.jsp"></s:include>
    </s:if>
    
    
    <s:if test="#session.roleId == 6">
        <!-- Menu Generation for Recruitment Role-->
        <s:include value="/includes/template/MenuRecruitment.jsp"></s:include>
    </s:if>
    
    
    <s:if test="#session.roleId == 7">
        <!-- Menu Generation for Recruitment Role-->
        <s:include value="/includes/template/MenuHR.jsp"></s:include>
    </s:if>
    
    
    <s:if test="#session.roleId == 8">
        <!-- Menu Generation for Vendor Role-->
        <s:include value="/includes/template/MenuVendor.jsp"></s:include>
    </s:if>
    
    
    <s:if test="#session.roleId == 9">
        <!-- Menu Generation for Vendor Role-->
        <s:include value="/includes/template/MenuCustomer.jsp"></s:include>
    </s:if>
          
           <s:if test="#session.roleId == 12">
        <!-- Menu Generation for Vendor Role-->
        <s:include value="/includes/template/MenuWebAdmin.jsp"></s:include>
    </s:if>
      <%--  <s:if test="#session.roleId == 10">
        <!-- Menu Generation for Vendor Role-->
        <s:include value="/includes/template/MenuVendor.jsp"></s:include>
    </s:if>
        <s:if test="#session.roleId == 12">
        <!-- Menu Generation for Vendor Role-->
        <s:include value="/includes/template/MenuVendorCust.jsp"></s:include>
    </s:if> --%>
<s:if test="#session.roleId == 10">
        <!-- Menu Generation for Vendor Role-->
        <s:include value="/includes/template/MenuPreSales.jsp"></s:include>
    </s:if>
<s:if test="#session.roleId == 13">
    <!-- Menu Generation for Vendor Role-->
    <s:include value="/includes/template/MenuPayroll.jsp"></s:include>
</s:if>
