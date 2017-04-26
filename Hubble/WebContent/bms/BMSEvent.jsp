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
        <title>Hubble Organization Portal :: Bridge Event</title>
    <sx:head cache="true"/>
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/helpText.css"/>"> 
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/selectivity-full.css"/>">
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/StandardClientValidations.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmpStandardClientValidations.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tooltip.css"/>">
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tooltip.css"/>">
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tooltip.js"/>"></script> 
    <%-- <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bms/style-personal.css"/>"> --%>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/bms/bms.js"/>"></script>
    
        <script>
            $(document).ready(function(){
                
                $('#internalAttendees').change(function(e){
                    var internalAttendeesArray = []; 
                    $("#internalAttendees :selected").each(function(){
                        internalAttendeesArray.push($(this).val());
                        var len= internalAttendeesArray.length;
                        if(len>10){
                                  
                            alert("Selecting more than 10 not allowed");
                            //$('#appreciationCC').selectivity('clear');
                            $('#internalAttendees').selectivity('remove', $(this).val());
                            return false;
                        }
                    });
                });
                    
            });
            </script>

    <s:include value="/includes/template/headerScript.html"/>
    <script>
        $(function() {
            $( document ).tooltip();
        });
    </script>
    <style>
        a.tooltip {outline:none; }
        a.tooltip strong {line-height:30px;}
        a.tooltip:hover {text-decoration:none;} 
        a.tooltip span {
            z-index:15;display:none; padding:14px 20px;
            margin-top:-5px; margin-left:10px;
            width:130px; height:80px; line-height:16px;
        }

        a.tooltip:hover span{
            display:inline; position:absolute; color:#111;
            border:1px solid #DCA; background:#f5f5f5; font-family: lucida-sans; font-size: 15px;
            font-weight: normal;
            color: black;}
        .callout {z-index:20;position:absolute;top:30px;border:0;left:-12px;}

        /*CSS3 extras*/
        a.tooltip span
        {
            border-radius:4px;
            box-shadow: 5px 5px 8px #CCC;
        }
        a{
            text-decoration: none;
        }
    </style>
</head>
<!--<body class="bodyGeneral" oncontextmenu="return false;" onload="getBridgeDetailsSearch();"> -->
<body class="bodyGeneral" oncontextmenu="return false;">

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
                        <td width="850px" class="cellBorder" valign="top" style="padding: 5px 5px 5px 5px;">
                            <!--//START TABBED PANNEL : -->
                            <ul id="accountTabs" class="shadetabs" >
                                <li ><a href="#" class="selected" rel="employeeSearchTab"  >Conference Bridge </a></li>
                            </ul>
                            <%--<sx:tabbedpanel id="employeeSearchPannel" cssStyle="width: 840px; height: 500px;padding: 5px 5px 5px 5px;" doLayout="true"> --%>


                            <div  style="border:1px solid gray; width:840px;height: 700px; overflow:auto; margin-bottom: 1em;">   

                                <div id="employeeSearchTab" class="tabcontent"  >





                                    <div id="bridgeCancelOverlay"></div>              <!-- Start Overlay -->
                                    <div id="bridgeCancelSpecialBox">


                                        <s:form theme="simple"  align="center" name="bridgeCancel" action="" method="post"    >


                                            <table align="center" border="0" cellspacing="0" style="width:100%;">
                                                <tr>                               
                                                    <td colspan="2" style="background-color: #288AD1" >

                                                        <b><lable style="color: #ffffff">Cancel Event</lable></b>


                                                    </td>
                                                    <td colspan="2" style="background-color: #288AD1" align="right">

                                                        <a href="#" onmousedown="cancelBridge(0)" >
                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> 

                                                        </a>  

                                                    </td></tr>
                                                <tr>
                                                    <td colspan="4">
                                                        <div id="loadCancel" style="color: green;display: none;">Loading..</div>
                                                        <div id="resultMessageCancel"></div>
                                                    </td>
                                                </tr>
                                                <tr><td colspan="4">
                                                        <table style="width:100%;" align="right" border="0">
                                                            <tr>



                                                                <td class="fieldLabel" >Reason:</td><td><s:textarea rows="3" cols="95" style='width: 445px; height: 45px;' name="reason" cssClass="inputTextarea1"  onchange="fieldLengthValidator(this);" id="reason"/> 
                                                                    <input type="hidden" id="cancelId"/>
                                                                </td>


                                                            </tr>
                                                            <tr>

                                                                <td align="right" colspan="2"><input  type="button" value="Confirm" class="buttonBg" onClick="doCancelBridge();"/></td>

                                                            </tr>
                                                        </table>

                                                    </td>
                                                </tr>


                                            </table>
                                        </s:form>




                                    </div>
                                    <div id="bridgeIssueOverlay"></div>              <!-- Start Overlay -->
                                    <div id="bridgeIssueSpecialBox">


                                        <s:form theme="simple"  align="center" name="bridgeAvailableOverlay" action="" method="post" onsubmit="return validateForm();"   >



                                            <table align="center" border="0" cellspacing="0" style="width:100%;">
                                                <tr>                               
                                                    <td colspan="2" style="background-color: #288AD1" >

                                                        <b><lable style="color: #ffffff">Create&nbsp;Issue</lable></b>


                                                    </td>
                                                    <td colspan="2" style="background-color: #288AD1" align="right">

                                                        <a href="#" onmousedown="issueBridgeOverlay()" >
                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> 

                                                        </a>  

                                                    </td></tr>
                                                <tr>
                                                    <td colspan="4">
                                                        <div id="loadAvalable" style="color: red;display: none;">Loading..</div>
                                                         <div id="IssueResultMessage"></div>
                                                    </td>
                                                </tr>
                                                <tr><td class="fieldLabel">Bridge&nbsp;Extension :<FONT color="red"><em>*</em></FONT></td>  
                                                                <td ><s:textfield id="bridgeCodeIssue"  name="bridgeCode" title="Bridge code starts with alphabet B or b followed by  digits. ex: B1911." placeHolder="B****" cssClass="inputTextBlueSmall" onblur="isActiveBridge();"/>&nbsp;<img id="correctImg" style="display: none;" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/ecertification/checkSelect.jpg" 
                                                                                                                                                                                                        width="15" height="15" border="0"><img id="wrongImg" style="display: none;" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/ecertification/wrong.jpg" 
                                                                                                                                                                                                        width="10" height="10" border="0"></td>
                                                                 <td class="fieldLabel" >Severity :<FONT color="red"><em>*</em></FONT></td> 
                                                                <td ><s:select id="priority" name="priority"  list="{'High','Medium','Low'}"  cssClass="inputSelect"/></td>
                                                         
                                                </tr>
                                                <tr>
                                                                <td class="fieldLabel">AssignTo :<FONT color="red" ><em>*</em></FONT></td>
                                                                <td> <s:select list="taskAssignToMap" id="assignTo" name="assignTo" cssClass="inputSelect" /></td>
                                                            </tr>
                                                <tr>



                                                                <td class="fieldLabel" >Comments :<FONT color="red"><em>*</em></FONT></td><td colspan="3"><s:textarea rows="3" cols="95" style='width: 404px; height: 45px;' name="commentsIssue" cssClass="inputTextarea1" value="%{comments}" onchange="fieldLengthValidator(this);" id="comments1"/> 
                                                                </td>


                                                            </tr>
                                                            
                                                <tr>
                                                                <td></td>
                                                                <td></td>
                                                                <td></td>
                                                                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="bookButton" type="button" value="Create" class="buttonBg" onClick="createBridgeIssue();"/></td>

                                                            </tr>


                                            </table>
                                        </s:form>




                                    </div>
                                    <s:form id="bridgeSearch" name="bridgeSearch" action="getQuery" theme="simple">
                                    <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                        <tr align="right">
                                            <td class="headerText">
                                                <img alt="Home" 
                                                     src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" 
                                                     width="100%" 
                                                     height="13px" 
                                                     border="0">

                                            </td>
                                        </tr>
                                        <tr>
                                            <td>


                                                <br><br>

                                                
                                                    <div style="border-width: 2px; border-style: double; border-color: #3e93d4;width: auto;margin-left: 20px;margin-right: 20px; ">
                                                    <table cellpadding="1" cellspacing="1" border="0" width="800" >
                                                        <tr>
                                                        </tr>
                                                        <tr>

                                                            <td class="fieldLabel">Date :<FONT color="red" SIZE="0.5"><em>*</em></FONT></td>
                                                            <td>

                                                                <s:textfield name="bridgeDate" id="bridgeDate" value="%{bridgeDate}" onchange="checkDates(this);" cssClass="inputTextBlueSmall"/>&nbsp;
                                                                <a href="javascript:cal1.popup();">
                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                         width="20" height="20" border="0"></a>

                                                            </td> 
                                                            <%-- <td class="fieldLabel">Bridge&nbsp;Code:</td>
                                                                 <td><s:textfield name="bridgeCode" id="bridgeCodeSearch" placeHolder="B****" cssClass="inputTextBlueSmall" value="%{bridgeCode}"/>
                                                                 </td> --%>
                                                                 <td class="fieldLabel">TimeZone:</td> 
                                                            <td>
                                                                
                                                                <s:select name="timeZone" list="{'EST','IST','MST','CST','PST'}" cssClass="inputSelectSmall" disabled="true"/>
                                                                <s:hidden name="timeZone" value="EST" id="timeZone"/>
                                                            </td>

                                                            <td class="fieldLabel">Start&nbsp;Time:</td>
                                                            <td colspan=""><s:textfield name="startTime" id="startTime" maxLength="5" placeholder="HH:MM" Class="inputSelectSmall" onchange="timeValidator(this);" onkeyup="enterdTime(this);"/>
                                                                <s:select id="midDayFrom"  name="midDayFrom"  list="{'AM','PM'}" cssClass="inputSelectSmall" />
                                                            </td>
                                                         
                                                            <td class="fieldLabel">Duration :</td>
                                                            <td colspan=""><s:textfield name="duration" id="duration" maxLength="5" placeholder="Min/Hrs" Class="inputSelectSmall" maxlength="2" onchange="minHrs(this);" onkeypress="return isNumberKey(event)"/>
                                                                <s:select id="durationType"  name="durationType"  list="{'Min','Hrs'}" cssClass="inputSelectSmall" />
                                                            </td>
                                                       </tr>
                                                        <tr>
                                                            
                                                            <td align="right" style="padding-right:35px;" colspan="8" >

                                                                <input type="button" value="Show Bridges" onclick="getAvailableBridges();" class="buttonBg" id="searchButton"/>
                                                                <input type="button" value="My Events" onclick="return getBridgeDetailsSearch();" class="buttonBg" id="searchButton"/>
                                                                <input type="button" value="Create Issue" onclick="issueBridgeOverlay();" class="buttonBg" id="searchButton"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="7">
                                                                <div id="mainResultMessage"></div>
                                                            </td>
                                                        </tr>
                                                    </table></div>

                                               
                                                <script type="text/javaScript">
                                                    var cal1 = new CalendarTime(document.forms['bridgeSearch'].elements['bridgeDate']);
                                                    // document.getElementById('fromDate').focus();
                                                    cal1.year_scroll = true;
                                                    cal1.time_comp = false;
                                               
                                                                                 
                                                </script>



                                            </td>
                                        </tr>
                                        <tr id="availableTr" style="display:none;"><td  class="fieldLabel" colspan="4">
                                                <label style="top: 10%; position: relative;"> Display <select id="paginationOption" class="disPlayRecordsCss" onchange="pagerOption()" style="width: auto">

                                                        <option>5</option>
                                                        <option>7</option>
                                                        <option>9</option>
                                                    </select> Rows</label>
                                                <table id="tblbridges" align='center' cellpadding='1' cellspacing='1' border='0'  width='100%' style="width: 812px;">



                                                </table>  

                                                <div id="resultData"></div>

                                            </td>
                                        </tr>
                                        <tr id="searchTR" style="display:none;">
                                            <td>





                                                <table cellpadding="1" cellspacing="1" border="0" width="150px"><tr><td style="width: 300px;">
                                                    <lable id="bridgeListTitle" style="font-weight: bold;"><font color="#00008B">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Events&nbsp;:&nbsp;</font><s:property value="bridgeDate"/>&nbsp;&nbsp;<font color="#00008B">Total&nbsp;Events&nbsp;:&nbsp;</font>0</lable></td>
                                            <td><div style="float: left;" id="infoDiv"><a href="#" class="tooltip"> <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/info.jpg" width="20px" height="20px" />
                                                        <span><font size="2">
                                                            Completed&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img  alt="Checked" src="/Hubble/includes/images/ecertification/green.png"  width="20px" height="20px" border="0" ><br>
                                                            Now in call&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img  alt="Checked" src="/Hubble/includes/images/ecertification/blue1.png"  width="20px" height="20px" border="0" ><br>
                                                            Not&nbsp;yet&nbsp;Started&nbsp;<img  alt="Checked" src="/Hubble/includes/images/ecertification/yellow2.png"  width="20px" height="20px" border="0" ><br>
                                                            Canceled&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img  alt="Checked" src="/Hubble/includes/images/ecertification/red.png"  width="20px" height="20px" border="0" ><br>
                                                            </font></span>
                                                    </a>
                                                </div><br></td>
                                        </tr></table>

                                    <center><div id="tblload" style="display:none" class="error" ><br></br><b>Loading Please Wait.....</b></div></center>
                                    <table id="tblEventList" align='center' cellpadding='1' cellspacing='1' border='0' class="gridTable" width='800' style="max-width:99%;">
                                        <COLGROUP ALIGN="left" >
                                            <COL width="3%">
                                            <COL width="7%">
                                            <COL width="10%">
                                               <COL width="7%">
                                            <COL width="25%">
                                            <COL width="15%">
                                            <COL width="7%">
                                            <COL width="7%">
                                            <COL width="7%">
                                            <COL width="7%">
                                         

                                    </table> 

                                    </td>


                                    </tr>
                                    <tr id="addTR" style="display:none;">
                                        <td>
                                            <br>
                                    <table cellpadding="1" cellspacing="1" border="0"  theme="simple" width="800">
                                         <tr>
                                                    <td colspan="4">
                                                        <div id="load" style="color: green;display: none;">Loading..</div>
                                                        <div id="resultMessage"></div>
                                                        <s:hidden name="passCode" id="passCode"/>
                                                    </td>
                                                </tr>
                                        <tr>  
                                            <td class="fieldLabel">Bridge&nbsp;Extension:</td>  
                                            <td><s:textfield id="bridgeCode" readonly="true" name="bridgeCode"   cssClass="inputTextBlueSmall" onblur="isActiveBridge();"/>
                                            </td>
                                            <td class="fieldLabel">Bridge&nbsp;Number:</td>  
                                            <td ><s:textfield id="bridgeNumber" readonly="true" name="bridgeNumber"   cssClass="inputTextBlue" />
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="fieldLabel forRemove"  >Internal Attendees: <font color="red">*</font></td>
                                                    <td colspan="3"><s:select name="internalAttendees" id="internalAttendees" list="employeeEmails" multiple="true" cssClass="inputTextBlueLargeAccount" style="width: 495px; height: auto; float:left;" value="%{appreciationCCList}" />
                                                        
                                                      <%--  <a href="#" class="tooltip"> <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/information.png" width="15px" height="15px" />
                                                          <!--  <span> By default mail is generated to their corresponding <strong>1st</strong> level , <strong>2nd</strong> level <strong>"Reports To"</strong> and <strong>1st</strong> level <strong>"Operations contact"</strong>.No need to enter their email Ids here. So, Here you can enter maximum of 3 employees email Ids only  <strong>CC</strong> other than those.   </span> -->
                                                        <span> By default mail is generated to their corresponding <strong>1st</strong> level ,<strong>2nd</strong> level <strong>"Reports To"</strong> and <strong>1st</strong> level <strong>"Operations contact"</strong>.No need to enter their Email Ids here. Here you can enter maximum of 3 employees Email Ids only.    </span>
                                                        </a>--%>
                                                    </td>
                                                        
                                                          
                                                    
                                                </tr>  
                                                <tr>
                                                    <td class="fieldLabel forRemove"  >External Attendees: </td>
                                                    <td colspan="3"><s:textarea placeholder="Enter email Id's seperated by comma. Ex: abc@domain.com,xyz@domain.co.in" rows="3" cols="120" style='width: 497px; height: 45px;' name="externalAttendees" cssClass="inputTextarea1"   id="externalAttendees" onblur="maiilValidate(this);"/>
                                                        
                                                       <%-- <a href="#" class="tooltip"> <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/information.png" width="15px" height="15px" />
                                                          <!--  <span> By default mail is generated to their corresponding <strong>1st</strong> level , <strong>2nd</strong> level <strong>"Reports To"</strong> and <strong>1st</strong> level <strong>"Operations contact"</strong>.No need to enter their email Ids here. So, Here you can enter maximum of 3 employees email Ids only  <strong>CC</strong> other than those.   </span> -->
                                                        <span> By default mail is generated to their corresponding <strong>1st</strong> level ,<strong>2nd</strong> level <strong>"Reports To"</strong> and <strong>1st</strong> level <strong>"Operations contact"</strong>.No need to enter their Email Ids here. Here you can enter maximum of 3 employees Email Ids only.    </span>
                                                        </a> --%>
                                                    </td>
                                                        
                                                    
                                                </tr> 
                                        <tr>



                                            <td class="fieldLabel" >Description:<font color="red">*</font></td><td colspan="3"><s:textarea rows="3" cols="120" style='width: 497px; height: 45px;' name="description" cssClass="inputTextarea1" value="%{comments}" onchange="fieldLengthValidator(this);" id="description"/> 
                                            </td>


                                        </tr>
                                        <tr >

                                            <td class="fieldLabel" colspan="2"><!--Do you want to send a event invitation from Hubble?--></td><td><s:checkbox name="mailFlag" id="mailFlag" style="display:none"/></td>
                                            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="addButton" type="button" value="Book Now" class="buttonBg" onClick="addBridgeEvent();"/></td>

                                        </tr>
                                    </table>
                                            
                                            </td>
                                                    </tr>
                                            </td>
                                                    </tr>
                                    
                                    </table>
                                             </s:form>
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
                            <script type="text/javascript" src="<s:url value="/includes/javascripts/bms/pagination.js"/>"></script> 

                            <script type="text/javascript">
                                var recordPage=5;
                                function pagerOption(){

                                    var paginationSize = document.getElementById("paginationOption").value;
                                    if(isNaN(paginationSize))
                                    {
                       
                                    }
                                    recordPage=paginationSize;
                                    // alert(recordPage)
                                    $('#tblbridges').tablePaginate({navigateType:'navigator'},recordPage);

                                };
                                $('#tblbridges').tablePaginate({navigateType:'navigator'},recordPage);
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

<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
    <script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script type="text/JavaScript" src="<s:url value="/includes/javascripts/selectivity-full.min.js"/>"></script>
        <script>
            $(document).ready(function() {
                $('#internalAttendees').selectivity({
                    
                    multiple: true,
                    minimumInputLength: 2,
                    placeholder: 'Type to search emails'
                });
              
                 
            });
        </script>
<script type="text/javascript">
		$(window).load(function(){
	getBridgeDetailsSearch();
		});
		</script>
</body>
</html>