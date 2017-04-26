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
        <title>Hubble Organization Portal :: Bridge Connectivity</title>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/helpText.css"/>"> 
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/clientValidations/EmpSearchClientValidation.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>

        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/jquery.min.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/jquery.js"/>"></script>  
   <%--   <script type="text/JavaScript" src="<s:url value="/includes/javascripts/pmoDashBoardAjax.js?version=3.4"/>"></script>  --%>
        <link rel="stylesheet" type="text/css" href="<s:url value="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css"/>">
  <script type="text/JavaScript" src="<s:url value="/includes/javascripts/BdmAssociation.js?version=3.5"/>"></script>


        <s:include value="/includes/template/headerScript.html"/>
        <style> 
            #fontId {
                position: relative;
                -webkit-animation: mymove 5s infinite; /* Chrome, Safari, Opera */
                animation: mymove 1s infinite;
                font-size:49px;
            }

            /* Chrome, Safari, Opera */
            @-webkit-keyframes mymove {
                from {opacity:0.2;}
            to {opacity:1;}
            }

            @keyframes mymove {
                from {opacity:0.2;}
            to {opacity:1;}
            }
        </style>
    </head>
    <body class="bodyGeneral" >

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

                <!--//END DATA COLUMN : Coloumn for LeftMenu-->

                <!--//START DATA COLUMN : Coloumn for Screen Content-->
                <td width="500px" class="cellBorder" valign="top" style="padding: 5px 5px 5px 5px;">
                    <!--//START TABBED PANNEL : -->
                    <ul id="accountTabs" class="shadetabs" >
                        <li ><a href="#" class="selected" rel="employeeSearchTab"  >BDM Associations </a></li>
                    </ul>
                    <%--<sx:tabbedpanel id="employeeSearchPannel" cssStyle="width: 840px; height: 500px;padding: 5px 5px 5px 5px;" doLayout="true"> --%>
                    <div  style="border:1px solid gray; width:840px;height: 500px; overflow:auto; margin-bottom: 1em;">   
                        <!--//START TAB : -->
                        <%--  <sx:div id="employeeSearchTab" label="Employee Search" cssStyle="overflow:auto;"  > --%>
                        <div id="employeeSearchTab" class="tabcontent"  >


                            <!-- Start Overlay -->


                            <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                <tr align="right">
                                <td class="headerText">
                                    <img alt="Home" 
                                         src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" 
                                         width="100%" 
                                         height="13px" 
                                         border="0"/>
                                    <%if (request.getAttribute(ApplicationConstants.RESULT_MSG) != null) {
                                            out.println(request.getAttribute(ApplicationConstants.RESULT_MSG));
                                        }%>
                                </td>
                                </tr>

                                <tr>
                                <td>

                                    <s:form name="frmbdmAssociates" action="" theme="simple">

                                        <table cellpadding="1" cellspacing="1" border="0" width="50%" align="center">

                                            <tr>
                                            <td class="fieldLabel">BDM&nbsp;Name :</td>

                                            <%-- <s:textfield name="customerName"  id="customerName" cssClass="inputTextBlue" theme="simple"/>
                                             </td> --%>
                                            <td><s:select list="bdmMap" headerKey="" headerValue="--Please Select--" name="bdmId" id="bdmId" value="%{bdmId}" cssClass="inputSelect"/> </td>
                                            <td><input type="button" class="buttonBg"  align="right"  value="Search" onclick="getBdmList();"/></td>
                                            </tr>
                                            <tr>
                                            <td class="fieldLabel" style="display: none;">Total&nbsp;Records&nbsp;:</td>
                                            <td class="userInfoLeft" id="totalState1" style="display: none;"></td>   
                                            </tr>                                              

                                        </table>
                                    </s:form>
                                </td>

                                </tr>
                           
                                <tr>
                                <td>
                                   
                                    <table align="center" cellpadding="2" border="0" cellspacing="1" width="50%" >

                                        <tr>
                                        <td height="20px" align="center" colspan="9">
                                            <div id="bdmAvailableReport" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                        </td>
                                        </tr>


                                        <tr>
                                        <td >
                                            <div id="bdmAvailableReport" style="display: block">
                                                <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                <table id="tblBdmReport" align='center' cellpadding='1' cellspacing='1' border='0' class="gridTable" width='700'>

                                                </table> <br>
                                                <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                            </div>
                                        </td>
                                        </tr>
                                    </table>    
                                </td>
                                </tr>

                            </table>

                            <!-- End Overlay -->
                            <!-- Start Special Centered Box -->

                            <%-- </sx:div > --%>
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

<tr>
<td>

    <div style="display: none; position: absolute; top:170px;left:320px;overflow:auto;" id="menu-popup">
        <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" />
    </div>

</td>
</tr>
<!--//END FOOTER : Record for Footer Background and Content-->

</table>
<!--//END MAIN TABLE : Table for template Structure-->
<script>
    $(document).ready(function(){
        
      getBdmList();
      
    });

</script>
<script type="text/javascript">
                                                                                                                                                                                                       
    function pagerOption(){

        // var paginationSize = document.getElementById("paginationOption").value;
                                                                                                                                                                                                      
        var  recordPage=10;
                                                                                                                                                                                                  
        $('#tblBdmReport').tablePaginate({navigateType:'navigator'},recordPage);

    }
                                                                                                                                                                                                        
                                                                                                                                                                                                        
                                                                                                                                                                                                        
                                                                                                                                                                                                           
</script>
<script type="text/JavaScript" src="<s:url value="/includes/javascripts/paginationAll.js"/>"></script>

</body>
</html>