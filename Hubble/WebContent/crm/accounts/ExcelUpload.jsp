<%-- 
    Document   : ExcelUpload
    Created on : Mar 14, 2016, 4:29:58 PM
    Author     : miracle
--%>

<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <title>Hubble Organization Portal :: Excel Upload</title>
    <sx:head cache="true"/>
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>   
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/jquery.min.js"/>"></script>
    <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/jquery.js"/>"></script>   
    <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/ajaxfileupload.js"/>"></script>  
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/FileUpload.js"/>"></script>

    <s:include value="/includes/template/headerScript.html"/>
</head>
<body class="bodyGeneral" >


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
                            Excel&nbsp;Upload                                  
                        </a></li>
                </ul>
                <div  style="border:1px solid gray; width:850px;height: 400px; overflow:auto; margin-bottom: 1em;">
                    <!--//START TAB : -->
                    <div id="employeeReviewTab" class="tabcontent"  >
                        <div theme="simple" >
                        <%--  <s:form theme="simple"  align="center" name="addReviewForm" action="NewAddReview.action" method="post" enctype="multipart/form-data"> --%>
                        <s:form theme="simple"  align="center" name="excelupload"   enctype="multipart/form-data" >

                            <table align="center" border="0">                               
                                <tr>
                                    <div id="resultMessage"></div>
                                <td class="fieldLabel">Upload File :<font style="color:red;">*</font></td>                                
                                <td><s:file name="file" label="file" cssClass="inputTextarea" id="file" onchange="return accountExcelFileValidation();putFileName();"/></td> 
                                <td  align="center" >
                                    <input type="button" value="Upload" onclick="return ajaxExcelUpload();" class="buttonBg" id="addButton"/> 
                                    <%-- <s:submit cssClass="buttonBg"  align="right" id="save" value="Save" /> --%>

                                </td>
                                </tr>    
                                <tr>
                                <td colspan="3"><br><br>
                                    
                                     
                                    
                                    
                                    
                                    <table>
                                        <tr><th style="color:darkblue;" align="left">Instructions:</th></tr>
                                    <tr><td class="fieldLabelLeft">1.File extension should be <b>.xls(workbook 97-2003)</b></td></tr>
                        <tr><td class="fieldLabelLeft">2.Data should be in First sheet.</b></td></tr>
                        <tr><td class="fieldLabelLeft">3.<b>Account name</b> should be less than <b>60</b> characters</td></tr>
                        <tr><td class="fieldLabelLeft">4.<b>Subsidiary Name</b> should be less than <b>60</b> characters</td></tr>
                        <tr><td class="fieldLabelLeft">5.<b>URL</b> should be less than <b>50</b> characters</td></tr>
                        <tr><td class="fieldLabelLeft">6.<b>Street</b> name should be less than <b>200</b> characters</td></tr>
                        <tr><td class="fieldLabelLeft">7.<b>City</b> should be less than <b>25</b> characters</td></tr>
                        <tr><td class="fieldLabelLeft">8.<b>Zip</b> should be less than <b>15</b> characters</td></tr>
                        <tr><td class="fieldLabelLeft">9.<b>BDE/BDM loginId</b> should be less than <b>30</b> characters</td></tr>
                        <tr><td class="fieldLabelLeft">To download Sample Excel template <a href="getSampleAccountExcel.action"><b>Click here</b></a></td></tr>
                                        
                                    </table>
                                    
                                </td>
                                </tr>

                            </table>
                        </s:form>                                       
                    </div>
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
