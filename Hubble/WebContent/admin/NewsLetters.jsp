<!-- 
 *******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  Auguest 28, 2013, 6:09 AM
 *
 * Author  : Santosh Kumar Kola
 *
 * File    : CreConsultantList.jsp
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
<%@ page import="java.util.Map" %>
<%@ page import="com.mss.mirage.util.DataSourceDataProvider"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hubble Organization Portal :: Newsletters</title>

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css?ver=1.0"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tooltip.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tooltip.js"/>"></script>   
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>   
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>   
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>



        <s:include value="/includes/template/headerScript.html"/>
        <script>
            function init(){
                var d = new Date();
                var n = d.getMonth();
                var year=d.getFullYear();
                var yearOptions=d.getFullYear()-2;
                
              
                for(var i=0;i<10;i++){
                    document.getElementById('year').options[i]=new Option(yearOptions+i,yearOptions+i);
                    document.getElementById('yearOverlay').options[i]=new Option(yearOptions+i,yearOptions+i);
                    document.getElementById('yearImages').options[i]=new Option(yearOptions+i,yearOptions+i);
                    document.getElementById('yearImagesOverlay').options[i]=new Option(yearOptions+i,yearOptions+i);
                 }
                document.getElementById('month').value=n; 
                document.getElementById('monthOverlay').value=n;
                document.getElementById('monthImages').value=n;
                document.getElementById('monthImagesOverlay').value=n;
               
                document.getElementById('year').value=year;
                document.getElementById('yearOverlay').value=year;
                document.getElementById('yearImages').value=year;
                 document.getElementById('yearImagesOverlay').value=year;
            }
            
            
            
            
            
var timeoutId;
function initSessionTimer() {
// alert("init...")


function setSessionTimeout(){

timeoutId = setTimeout(

function()

{
history.pushState(null, null, 'pagename');
window.addEventListener('popstate', function(event) {
history.pushState(null, null, 'pagename');
});
alert('You\'re session has timed out. Please re-login.');
window.location = "../general/logout.action";
}, 1800000); //1800000 -->30 min
}

document.onclick = function resetTimeout(){
clearTimeout(timeoutId);
setSessionTimeout();
};
}
            
        </script>
    </head>
   <!-- <body class="bodyGeneral"  oncontextmenu="return false;"  onload="init();"> -->
    <body class="bodyGeneral"  oncontextmenu="return false;">



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
                                <!--//START TABBED PANNEL : -->
                                <%
                                    //out.print("naga::"+ request.getAttribute("subnavigateTo").toString());
                                    /// session.setAttribute(ApplicationConstants.ISSUE_TO_TASK,request.getAttribute("subnavigateTo").toString());
                                %>

                                <ul id="accountTabs" class="shadetabs" >

                                    <%--    <% if(request.getParameter("issueList")==null)
                                           {%> --%>

                                    <li ><a href="#" class="selected" rel="newsLetters"  >Newsletters </a></li>
                                    <li ><a href="#" rel="images">Images</a></li>



                                </ul>

                                <%-- <sx:tabbedpanel id="IssuesPanel" cssStyle="width: 845px; height: 500px;padding:5px 5px;" doLayout="true"> --%>
                                <div  style="border:1px solid gray; width:845px;height: 600px; overflow:auto; margin-bottom: 1em;">
                                     <s:hidden id="urlImages" name="urlImages" value="%{urlImages}"/>
                                                 <s:hidden id="urlNewsletters" name="urlNewsletters" value="%{urlNewsletters}"/>
                                                 
                                                 <s:hidden id="yearSearch" name="yearSearch" />
                                                   <s:hidden id="monthSearch" name="monthSearch" />
                                                 <s:hidden id="typeSearch" name="typeSearch" />
                                                 <s:hidden id="categorySearch" name="categorySearch" />
                                    
                                    <!--//START TAB : -->

                                    <%--  <% if(request.getParameter("issueList")==null)
                                             {
                                               //  System.out.println("list");
                                             %> --%>
                                    <div id="newsLetters" class="tabcontent"  >

                                        <div id="overlay"></div> 
                                        <div id="specialBox" style="margin-left: 0vw;margin-top: 100px;">
                                            <s:form theme="simple"  align="center" name="newsLettersOverlay">
                                               
                                               
                                                <table align="center" border="0" cellspacing="0" style="width:100%;">
                                                    <tr>                               
                                                        <td colspan="2" style="background-color: #288AD1" >
                                                            <h3 style="color:darkblue;" align="left">
                                                                <span id="headerLabel1"></span>


                                                            </h3></td>
                                                        <td colspan="2" style="background-color: #288AD1" align="right">

                                                            <a href="#" onmousedown="toggleCloseUploadOverlay()" >
                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> 

                                                            </a>  

                                                        </td></tr>
                                                    <tr>
                                                        <td colspan="4">
                                                            <div id="load" style="color: green;display: none;">Loading..</div>
                                                            <div id="resultMessage"></div>
                                                        </td>
                                                    </tr>    
                                                    <tr><td colspan="4">
                                                            <table style="width:80%;" align="center">
                                                                <tr>

                                                                    <td class="fieldLabel">Year</td>
                                                                    <td><s:select cssClass="inputSelect" list="{}" name="year" id="yearOverlay"  /></td>
                                                                    <td class="fieldLabel">Month</td> 
                                                                    <td ><s:select cssClass="inputSelect" list="#@java.util.LinkedHashMap@{'0':'January','1':'February','2':'March','3':'April','4':'May','5':'June','6':'July','7':'August','8':'September','9':'October','10':'November','11':'December'}" name="month" id="monthOverlay"   /></td>

                                                                </tr> 

                                                                <tr> <td class="fieldLabel" >Type</td>
                                                                    <td><s:select cssClass="inputSelect" list="#@java.util.LinkedHashMap@{'Internal':'Internal','External':'External'}" name="type" id="typeOverlay"  /></td>
                                                                    <td class="fieldLabel" >Category</td>
                                                                    <td> <s:select cssClass="inputSelect" list="#@java.util.LinkedHashMap@{'Webinars':'Webinars','Campaigns':'Campaigns','Events':'Events'}" name="category" id="categoryOverlay"   /></td>


                                                                </tr>
                                                                <tr>
                                                                    <td class="fieldLabel" >Upload&nbsp;File:</td>
                                                                    <td><s:file name="file" label="file" cssClass="inputTextarea" id="file" onchange="fileValidationNewsLetters();"/></td>
                                                                    <td align="right" colspan="5">
                                                                        <input type="button" label="Submit" value="Save" class="buttonBg" onclick="doAddNewsLetters()" style="margin-right: 30px;"/>
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </s:form>   

                                        </div>



                                        <s:form action="" name="frmDBGrid" theme="simple"> 
                                            <div style="width:840px;"> 
                                                <table cellpadding="0" border="0" cellspacing="0" width="100%">
                                                    <tr>
                                                        <td class="headerText">
                                                            <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                        </td> 
                                                    </tr>

                                                    <tr>
                                                        <td align="center"> 
                                                            <table cellpadding="1" cellspacing="1" border="0">
                                                                <tr>

                                                                    <td class="fieldLabel" width="20%">Year :</td>
                                                                    <td><s:select cssClass="inputSelect" list="{}" name="year" id="year"  /></td>
                                                                    <td class="fieldLabel" width="30%">Month :</td> 
                                                                    <td ><s:select cssClass="inputSelect" list="#@java.util.LinkedHashMap@{'0':'January','1':'February','2':'March','3':'April','4':'May','5':'June','6':'July','7':'August','8':'September','9':'October','10':'November','11':'December'}" name="month" id="month"   /></td>

                                                                </tr> 

                                                                <tr> <td class="fieldLabel" width="30%">Type :</td>
                                                                    <td ><s:select cssClass="inputSelect" list="#@java.util.LinkedHashMap@{'Internal':'Internal','External':'External'}" name="type" id="type"  /></td>
                                                                    <td class="fieldLabel" width="30%">Category :</td>
                                                                    <td > <s:select cssClass="inputSelect" list="#@java.util.LinkedHashMap@{'Webinars':'Webinars','Campaigns':'Campaigns','Events':'Events'}" name="category" id="category"   /></td>
                                                                    
                                                                </tr>
                                                                <tr>
                                                                    
                                                                   
                                                                    <td></td>
                                                                     <td></td>
                                                                     
                                                                    <td  align="right">
                                                                        <input type="button" class="buttonBg" value="Search" onclick="getAllFilesInDirectory();" style="margin-right: -8vw"/>
                                                                    </td>
                                                                     <td>
                                                                        <input type="button" class="buttonBg" value="Add" onclick="getNewsLettersOverlay();"/>
                                                                    </td>
                                                                </tr>

                                                                <tr>
                                                                    <td height="20px" align="center" colspan="9">
                                                                        <div id="loadActMessageAS" style="display:none" class="error" ><br></br><b>Loading Please Wait.....</b></div>
                                                                    </td>
                                                                </tr>

                                                                <tr>
                                                                    <td colspan="8">
                                                                        <table id="tblNewsLetters" align='center' cellpadding='1' cellspacing='1' border='0' class="gridTable" width='700'>
                                                                            <COLGROUP ALIGN="left" >
                                                                                <COL width="25%">
                                                                                <COL width="10%">


                                                                        </table>  



                                                                    </td>
                                                                </tr>


                                                            </table>  
                                                        </td>
                                                    </tr>


                                                </table>    
                                            </div>
                                        </s:form>  

                                        <%--  </sx:div > --%>
                                    </div>
                                    <%--   <%}%> --%>
                                    <!--//END TAB : -->

                                    <%--  <sx:div id="IssuesSearchTab" label="Issues Search" > --%>
                                    <div id="images" class="tabcontent"  >   
                                       <div id="overlayImages"></div> 
                                        <div id="specialBoxImages" style="margin-left: 0vw;margin-top: 100px;">
                                            <s:form theme="simple"  align="center" name="imagesOverlay">
                                                <table align="center" border="0" cellspacing="0" style="width:100%;">
                                                    <tr>                               
                                                        <td colspan="2" style="background-color: #288AD1" >
                                                            <h3 style="color:darkblue;" align="left">
                                                                <span id="headerLabel"></span>


                                                            </h3></td>
                                                        <td colspan="2" style="background-color: #288AD1" align="right">

                                                            <a href="#" onmousedown="toggleCloseUploadOverlay1()" >
                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> 

                                                            </a>  

                                                        </td></tr>
                                                    <tr>
                                                        <td colspan="4">
                                                            <div id="load1" style="color: green;display: none;">Loading..</div>
                                                            <div id="resultMessage1"></div>
                                                        </td>
                                                    </tr>    
                                                    <tr><td colspan="4">
                                                            <table style="width:80%;" align="center">
                                                                <tr>

                                                                    <td class="fieldLabel" >Year</td>
                                                                    <td><s:select cssClass="inputSelect" list="{}" name="year" id="yearImagesOverlay"  /></td>
                                                                    <td class="fieldLabel" >Month</td> 
                                                                    <td ><s:select cssClass="inputSelect" list="#@java.util.LinkedHashMap@{'0':'January','1':'February','2':'March','3':'April','4':'May','5':'June','6':'July','7':'August','8':'September','9':'October','10':'November','11':'December'}" name="month" id="monthImagesOverlay"   /></td>

                                                                </tr> 

                                                               
                                                                <tr>
                                                                    <td class="fieldLabel" >Upload&nbsp;File:</td>
                                                                    <td><s:file name="file" label="file" cssClass="inputTextarea" id="file1" onchange="fileValidationNewsLettersImages();" /></td>
                                                                    <td align="right" colspan="5">
                                                                        <input type="button" label="Submit" value="Save" class="buttonBg" onclick="doAddNewsLettersImages()" style="margin-right: 30px;"/>
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </s:form>   

                                        </div>



                                        <s:form action="" name="frmDBGrid" theme="simple"> 
                                            <div style="width:840px;"> 
                                                <table cellpadding="0" border="0" cellspacing="0" width="100%">
                                                    <tr>
                                                        <td class="headerText">
                                                            <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                        </td> 
                                                    </tr>

                                                    <tr>
                                                        <td align="center"> 
                                                            <table cellpadding="1" cellspacing="1" border="0">
                                                                <tr>

                                                                    <td class="fieldLabel" width="20%">Year :</td>
                                                                    <td><s:select cssClass="inputSelect" list="{}" name="year" id="yearImages"  /></td>
                                                                    <td class="fieldLabel" width="30%">Month :</td> 
                                                                    <td ><s:select cssClass="inputSelect" list="#@java.util.LinkedHashMap@{'0':'January','1':'February','2':'March','3':'April','4':'May','5':'June','6':'July','7':'August','8':'September','9':'October','10':'November','11':'December'}" name="month" id="monthImages"   /></td>

                                                                </tr> 

                              
                                                                <tr>
                                                                    
                                                                   
                                                                    <td></td>
                                                                     <td></td>
                                                                     
                                                                    <td  align="right">
                                                                        <input type="button" class="buttonBg" value="Search" onclick="getAllFilesInImagesDirectory();" style="margin-right: -8vw"/>
                                                                    </td>
                                                                     <td>
                                                                        <input type="button" class="buttonBg" value="Add" onclick="getNewsLettersImagesOverlay();"/>
                                                                    </td>
                                                                </tr>

                                                                <tr>
                                                                    <td height="20px" align="center" colspan="9">
                                                                        <div id="loadActMessageAS1" style="display:none" class="error" ><br></br><b>Loading Please Wait.....</b></div>
                                                                    </td>
                                                                </tr>

                                                                <tr>
                                                                    <td colspan="8">
                                                                        <table id="tblNewsLettersImages" align='center' cellpadding='1' cellspacing='1' border='0' class="gridTable" width='700'>
                                                                            <COLGROUP ALIGN="left" >
                                                                                <COL width="25%">
                                                                                <COL width="10%">


                                                                        </table>  



                                                                    </td>
                                                                </tr>


                                                            </table>  
                                                        </td>
                                                    </tr>


                                                </table>    
                                            </div>
                                        </s:form>

                                    </div>

                                    <!-- Consultant Reports tab Start -->

                                    <!-- Consultant Reports tab End -->

                                    <!--//END TAB : -->
                                    <%--  </sx:tabbedpanel> --%>
                                </div>
                                <!--//END TABBED PANNEL : -->
                                <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/jquery.min.js"/>"></script>
                                <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/jquery.js"/>"></script>   
                                <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/ajaxfileupload.js"/>"></script>  
                                <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/FileUpload.js"/>"></script>
                                <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ExecutiveDashBoard.js?ver=2.0"/>"></script>
                                <script>
                                                                                            
                                    var countries=new ddtabcontent("accountTabs")
                                    countries.setpersist(false)
                                    countries.setselectedClassTarget("link") //"link" or "linkparent"
                                    countries.init()
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


            <!--//END FOOTER : Record for Footer Background and Content-->

        </table>
        <!--//END MAIN TABLE : Table for template Structure-->


<script>
            $(document).ready(function() {
                init();
            });
        </script>


    </body>
</html>


