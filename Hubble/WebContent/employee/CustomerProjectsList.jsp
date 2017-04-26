<!-- 
 *******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  November 20, 2007, 3:25 PM
 *
 * Author  : Teja
 *
 * File    : CustomerProjectsList.jsp
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
-->
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%--<%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<%@ page import="com.freeware.gridtag.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hubble Organization Portal :: Customer Projects List</title>
    <sx:head cache="true"/>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/clientValidations/EmpSearchClientValidation.js"/>"></script>

    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css?ver=1.0"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tooltip.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
   <link rel="stylesheet" type="text/css" href="<s:url value="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css"/>">
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tooltip.js"/>"></script> 
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>   

    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>

    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ajax/AjaxPopup.js"/>"></script> 
    <script type="text/javascript" src="<s:url value="/includes/javascripts/searchIssue.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmpStandardClientValidations.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
    <script type="text/javascript" src="<s:url value="/includes/javascripts/IssueFill.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmployeeAjax.js?ver=1.1"/>"></script>
    <script type="text/javascript" src="<s:url value="/includes/javascripts/GreenSheetUtil.js"/>"></script>
    <%-- <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AjaxRequirement.js"/>"></script> --%>


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

 <script>

         function checkName()

            {

                var accountId = document.getElementById("consultantId").value=""; 

                if(accountId=="" || accountId==null){

                    //  alert(" Please select customer from suggestion list.");

                    document.getElementById("customerName").value = "";

                }

            }

    </script>



</head>
<%-- <body class="bodyGeneral"  onload="init1();getCustomersList();" oncontextmenu="return false;" > --%>
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
                        <td width="850px" class="cellBorder" valign="top" style="padding:5px 5px;">

                            <ul id="customerProjectsListTabs" class="shadetabs" >
                                <li ><a href="#" class="selected" rel="issuesTab"> Customer Projects List </a></li>

                            </ul>

                            <%-- <sx:tabbedpanel id="IssuesPanel" cssStyle="width: 845px; height: 500px;padding:5px 5px;" doLayout="true"> --%>
                            <div  style="border:1px solid gray; width:850px;height: 526px; overflow:auto; margin-bottom: 1em;">

                                <div id="issuesTab" class="tabcontent"  >
                                    <span style="font-size: 14px;font-family: caption; color:green">
                               <%   if(session.getAttribute("resultMessage")!=null){
                                                            out.println(session.getAttribute("resultMessage"));
                                                            session.removeAttribute("resultMessage");
                                                                                                                      
                                                                                                                  }    %>
                                    </span>
                                    <s:form   theme="simple" id="frmAddGreenSheet" name="frmAddGreenSheet" >


                                        <div style="width:840px;" > 

                                            <table cellpadding="0" cellspacing="0" align="left" width="100%">

                                                <tr>
                                                 
                                                    <td>
                                                        <table border="0" cellpadding="0" cellspacing="0" align="left" width="100%" style="margin-top: 40px">

                                                            <tr>
                                                                <td class="fieldLabel">Customer Name&nbsp;:</td>
                                                                <td colspan="2">  
                                                                    <s:textfield name="customerName" id="customerName" onchange="checkName();" cssClass="inputTextBlueLarge"  style="width:83%"  value="%{customerName}"  theme="simple" onkeyup="fillCustomer();"/>
                                                                    <div id="validationMessage"></div>
                                                                    <s:hidden name="consultantId" id="consultantId" value="%{accountId}" /> 

                                                                </td>
                                                                <td>
                                                                    <input type="button" value="GetList" class="buttonBg" onclick="getCustomersList();" />
                                                               <input type="button" value="Add" class="buttonBg" onclick="addProjectToCustomer();" />
                                                            
                                                                </td>
                                                              


                                                            </tr>

                                                        </table>  
                                                    </td>
                                                </tr>

                                                <tr>
                                                    <td>
                                                        <table width="100%" cellpadding="1" cellspacing="1" border="0">
                                                            <tr>
                                                                <td>
                                                                    <div id="loadingMessage" style="color:red; display: none;" align="center"><b><font size="4px" >Loading...Please Wait...</font></b></div>

                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td>
                                                                    <%-- <DIV id="loadingMessage"> </DIV> --%>

                                                                    <TABLE id="tblUpdate" align="center"  
                                                                           cellpadding='1' cellspacing='1' border='0' class="gridTable" width='90%' style="margin-top: 25px">
                                                                        <COLGROUP ALIGN="left" >
                                                                            <COL width="10%">
                                                                            <COL width="40%"> 
                                                                            <COL width="5%">
                                                                            <COL width="10%">
                                                                    </TABLE>

                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </td>
                                                </tr>

                                            </table>   

                                        </div>
                                    </s:form>  



                                    <%--  </sx:div > --%>
                                </div></div>
                                <%--   <%}%> --%>
                            <!--//END TAB : -->



                            <!--//END TABBED PANNEL : -->
                            <script type="text/javascript">

                                var projectsList=new ddtabcontent("customerProjectsListTabs")
                                projectsList.setpersist(false)
                                projectsList.setselectedClassTarget("link") //"link" or "linkparent"
                                projectsList.init()

                            </script>
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
        <tr>
            <td>

                <div style="display: none; position: absolute; top:170px;left:320px;overflow:auto;" id="menu-popup">
                    <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" />
                </div>

            </td>
        </tr>

        <!--//END FOOTER : Record for Footer Background and Content-->

    </table>
                     <script type="text/javascript">
                                                                                                                                                                                                        var recordPage=15;
                                                                                                                                                                                                        function pagerOption(){

                                                                                                                                                                                                       
                                                                                                                                                                                                      //   alert(recordPage)
                                                                                                                                                                                                        $('#tblUpdate').tablePaginate({navigateType:'navigator'},recordPage);

                                                                                                                                                                                                        };
                                                                                                                                                                                                        $('#tblUpdate').tablePaginate({navigateType:'navigator'},recordPage);
                                                                                                                                                                                                        </script>
                                                                                                                                                                                                        <script type="text/JavaScript" src="<s:url value="../includes/javascripts/pagination.js"/>"></script>
    <!--//END MAIN TABLE : Table for template Structure-->


<script type="text/javascript">
		$(window).load(function(){
		init1();
		getCustomersList();

		});
		</script>


</body>
</html>