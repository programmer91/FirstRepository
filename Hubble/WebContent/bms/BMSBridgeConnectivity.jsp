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

<script type="text/JavaScript" src="<s:url value="/includes/javascripts/bms/bms.js"/>"></script>
 <script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

        <script>
            $(function() {
                $( document ).tooltip();
            });
        </script>
      
    <script type="text/javascript">
        var specialKeys = new Array();
               specialKeys.push(8); //Backspace
		specialKeys.push(43); 
		specialKeys.push(37); 
		specialKeys.push(39); 
		//specialKeys.push(37); 
		
		
        function IsNumeric3(e) {
            
            document.getElementById("resultMessage").innerHTML='';
          
              document.getElementById('resultMessage').style.display="none";
            
            var keyCode = e.which ? e.which : e.keyCode
            var ret = (keyCode != 43 && keyCode != 8  && (keyCode >= 48 && keyCode <= 57) || specialKeys.indexOf(keyCode) != -1);
            if(!ret){
             document.getElementById('resultMessage').style.display="block";
                document.getElementById("resultMessage").innerHTML="<font color='red'>Enter numbers only</font>";
            
           
            }
            return ret;
        }
    </script>

    <s:include value="/includes/template/headerScript.html"/>
    
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
                        <td width="850px" class="cellBorder" valign="top" style="padding: 5px 5px 5px 5px;">
                            <!--//START TABBED PANNEL : -->
                            <ul id="accountTabs" class="shadetabs" >
                                <li ><a href="#" class="selected" rel="employeeSearchTab"  >Bridge Search </a></li>
                            </ul>
                            <%--<sx:tabbedpanel id="employeeSearchPannel" cssStyle="width: 840px; height: 500px;padding: 5px 5px 5px 5px;" doLayout="true"> --%>
                            <div  style="border:1px solid gray; width:840px;height: 500px; overflow:auto; margin-bottom: 1em;">   
                                <!--//START TAB : -->
                                <%--  <sx:div id="employeeSearchTab" label="Employee Search" cssStyle="overflow:auto;"  > --%>
                                <div id="employeeSearchTab" class="tabcontent"  >


                                    <div id="overlayForBridge"></div>              <!-- Start Overlay -->
                                    <div id="specialBoxForBridge" style="width: 806px;">
                                        <s:form theme="simple"  align="center" name="addBridge" action="%{currentAction}" method="post" enctype="multipart/form-data" onsubmit="return validateForm();"   >
                                            <table align="center" border="0" cellspacing="0" style="width:100%;">
                                                <tr>                               
                                                    <td colspan="2" style="background-color: #288AD1" >
                                                        <h3 style="color:darkblue;" align="left">
                                                            <span id="headerLabel"></span>


                                                        </h3></td>
                                                    <td colspan="2" style="background-color: #288AD1" align="right">

                                                        <a href="#" onmousedown="mytoggleOverlayForBmsBridge()" >
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
                                                        <table style="width:90%;" align="center" border="0" >
                                                   <tr>
                                                                   

                                                                    <td class="fieldLabel" >Bridge&nbsp;Number&nbsp;: </td>
                                                                    <td> 
                                                                        <s:textfield id="overlayBridgeNumber" name="overlayBridgeNumber" title="Please enter the Bridge Number. (Example : +1 248 233 1911)" cssClass="inputTextBlue" value="%{overlayBNumber}" onKeyPress="return IsNumeric3(event);"  onblur="return formatPhone(this);" ondrop="return false;" onpaste="return false;" />
                                                                        
                                                                    </td>
                                                                    <td class="fieldLabel">Pass&nbsp;Code&nbsp;:
                                                                        
                                                                    </td>
                                                                    <td> 
                                                                        <s:textfield id="overlayPassCode" name="overlayPassCode" cssClass="inputTextBlue" title="Please enter the passcode." maxlength="10" onKeyPress="return IsNumeric3(event);" maxLength="10" />&nbsp;<img id="correctImg" style="display: none;" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/ecertification/checkSelect.jpg" 
                                                                                   width="15" height="15" border="0"><img id="wrongImg" style="display: none;" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/ecertification/wrong.jpg" 
                                                                                   width="10" height="10" border="0"></td>
                                                                     <td class="fieldLabel" style="display: none;" id="bridgeCodeLable">Bridge&nbsp;Code&nbsp;: </td>
                                                                    <td style="width: 160px;display: none;" id="bridgeCodeField"> 
                                                                        <s:textfield id="overlayBridgeCode" name="overlayBridgeCode" placeHolder="B****" cssClass="inputTextBlue" title="Please enter in the below formate (Example : B1999).Start the Bridge Code With 'Alphabets'." value="%{overlayBCode}" />&nbsp;<img id="correctImg" style="display: none;" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/ecertification/checkSelect.jpg" 
                                                                                   width="15" height="15" border="0"><img id="wrongImg" style="display: none;" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/ecertification/wrong.jpg" 
                                                                                   width="10" height="10" border="0"></td>
                                                                </tr>
                                                <tr>
                                                    <td class="fieldLabel" >Bridge&nbsp;Name&nbsp;: </td>
                                                    <td> 
                                                        <s:textfield id="overlayBridgeName" name="overlayBridgeName" maxLength="30" cssClass="inputTextBlue" title="Please enter the Bridge Name." value="%{overlayBName}" /></td>
                                                    <td class="fieldLabel" >Status :</td>
                                                    <td> 
                                                        <s:select id="overlayBridgeStatus"  name="overlayBridgeStatus"  list="{'Active','InActive','Issue'}" value="%{overlayBridgeStatus}" cssClass="inputSelect" contentEditable="true"/>
                                                            </td>
                                                </tr>
                                                    <tr id="updatingTr">
                                                            <td align="right"  colspan="4" style="padding-right: 138px;"> <table><tr><td><div id="update" ><input type="button"  value="Update" onclick="return upadteBMSBridgeDetails();" class="buttonBg"/> </div></td>
                                                                    </tr></table></td>
                                                        </tr>
                                                        <tr id="addingTr">                                               
                                                <td  align="right" colspan="4" style="padding-right:140px;">
                                                        <input type="button" value="Save" onclick="return addBridgeDetails();" class="buttonBg" id="addButton"/> 
                                                        <%-- <s:submit cssClass="buttonBg"  align="right" id="save" value="Save" /> --%>
                                                        <s:reset cssClass="buttonBg"  align="right" value="Reset" />
                                                    </td>
                                                </tr>
                                                        </table>
                                                    </td>
                                                </tr>
                                            </table>
                                        </s:form>              
                                    </div>

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

                                                <s:form name="frmbmsBridgeSearch" action="bridgeSearch.action" theme="simple">
                                                   
                                                    <table cellpadding="1" cellspacing="1" border="0" width="650px" align="center">
                                                        <tr>
                                                            <td class="fieldLabel">Bridge&nbsp;Code&nbsp;:</td>
                                                            <td><s:textfield name="bridgeCode" placeholder="Bridge Code" id="bridgeCode" cssClass="inputTextBlueSmall" value="%{bridgeCode}"/>
                                                                    
                                                            </td>
                                                            <td class="fieldLabel">Status&nbsp;:</td>
                                                            <td>  <s:select id="bmsBridgeStatus"  name="bmsBridgeStatus"  list="{'Active','InActive','Issue'}" headerKey="" headerValue="All"   value="%{bmsBridgeStatus}" cssClass="inputSelect" />
                                                            </td>
                                                        
                                                            <td id="adding">
                                                                 <s:submit cssClass="buttonBg"  align="right"  value="Search" />
                                                                <input type="button" class="buttonBg"  align="right"  value="Add" onclick="mytoggleOverlayForBmsBridge();" />                     
                                                               

                                                            </td>
                                                        </tr>
                                                        
                                                    </table>
                                                </s:form>
                                            </td>

                                        </tr>
                                        <tr>
                                            <td>
                                                <%

                                                    if (request.getAttribute("submitFrom") != null) {
                                                        submittedFrom = request.getAttribute("submitFrom").toString();
                                                    }

                                                    if (!"dbGrid".equalsIgnoreCase(submittedFrom)) {
                                                        searchSubmitValue = submittedFrom;
                                                    }

                                                %>

                                            </td>
                                        </tr>
                                        <tr>
                                            <td>

                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <%
                                                    /* String Variable for storing current position of records in dbgrid*/
                                                    strTmp = request.getParameter("txtCurr");

                                                    intCurr = 1;

                                                    if (strTmp != null) {
                                                        intCurr = Integer.parseInt(strTmp);
                                                    }

                                                    /* Specifing Shorting Column */
                                                    strSortCol = request.getParameter("Colname");

                                                    if (strSortCol == null) {
                                                        strSortCol = "Fname";
                                                    }

                                                    strSortOrd = request.getParameter("txtSortAsc");
                                                    if (strSortOrd == null) {
                                                        strSortOrd = "ASC";
                                                    }


                                                    try {

                                                        /* Getting DataSource using Service Locator */
                                                        connection = ConnectionProvider.getInstance().getConnection();

                                                       
                                                        if (request.getAttribute(ApplicationConstants.QUERY_STRING) != null) {
                                                            queryString = request.getAttribute(ApplicationConstants.QUERY_STRING).toString();
                                                        }



                                                        /* else {
                                                        if(session.getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString().equalsIgnoreCase("Employee")){
                                                        queryString="SELECT  CONCAT(tblEmployee.FName,' ',tblEmployee.MName,'.',tblEmployee.LName) AS EmployeeName,ReviewType,EmpId,ReviewTypeId,EmpComments,tblEmpReview.CreatdBy,tblEmpReview.CreatedDate,AttachmentName,AttachmentLocation,ReviewName,tblEmpReview.Id AS Id,HrComments FROM tblEmpReview JOIN tblLkReviews ON (ReviewTypeId = tblLkReviews.Id) JOIN tblEmployee ON (tblEmpReview.EmpId = tblEmployee.Id) WHERE tblEmpReview.EmpId="+empId;
                                                        }else if(session.getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString().equalsIgnoreCase("Hr")) {
                                                        queryString="SELECT  CONCAT(tblEmployee.FName,' ',tblEmployee.MName,'.',tblEmployee.LName) AS EmployeeName,ReviewType,EmpId,ReviewTypeId,EmpComments,tblEmpReview.CreatdBy,tblEmpReview.CreatedDate,AttachmentName,AttachmentLocation,ReviewName,tblEmpReview.Id AS Id,HrComments FROM tblEmpReview JOIN tblLkReviews ON (ReviewTypeId = tblLkReviews.Id) JOIN tblEmployee ON (tblEmpReview.EmpId = tblEmployee.Id) WHERE WorkingCountry='"+session.getAttribute(ApplicationConstants.WORKING_COUNTRY).toString()+"'";                                  
                            
                                                        }
                            
                                                        }*/
                                                        // queryString="SELECT ReviewType,EmpId,ReviewTypeId,EmpComments,CreatdBy,CreatedDate,AttachmentName,AttachmentLocation,ReviewName,tblEmpReview.Id as Id FROM tblEmpReview JOIN tblLkReviews ON (ReviewTypeId = tblLkReviews.Id) where EmpId="+empId;
                                                     // out.println(queryString);

                                                        //out.println("--------"+submittedFrom);
%>

                                                <s:form action="" theme="simple" name="frmDBGrid">   

                                                    <table cellpadding="0" cellspacing="0" width="100%" border="0">


                                                        <tr>
                                                            <td width="100%">

                                                               
                                                                <grd:dbgrid id="tblStat" name="tblStat" width="98" pageSize="10"
                                                                currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2"
                                                                dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">

                                                                    <%--    <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                                       imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                                       addImage="../../includes/images/DBGrid/Add.png"  addAction="<%=empReviewAction %>"
                                                                                       /> --%>
                                                                    <grd:gridpager imgFirst="../includes/images/DBGrid/First.gif" imgPrevious="../includes/images/DBGrid/Previous.gif" 
                                                                                   imgNext="../includes/images/DBGrid/Next.gif" imgLast="../includes/images/DBGrid/Last.gif"
                                                                                   addImage="../includes/images/DBGrid/Add.png"  addAction="javascript:mytoggleOverlayForBmsBridge()"
                                                                                   />            

                                                                    <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" 
                                                                                    imageAscending="../includes/images/DBGrid/ImgAsc.gif" 
                                                                                    imageDescending="../includes/images/DBGrid/ImgDesc.gif"/>   
                                                                   
                                                                    <grd:anchorcolumn dataField="BCode" 
                                                                                      headerText="Bridge Code" 
                                                                                      linkUrl="javascript:toggleEditOverlayForBmsBridge('{BCode}')" HAlign="center" linkText="{BCode}" width="20"/>
                                                                    <grd:textcolumn dataField="BNumber" headerText="Bridge Number" HAlign="right" width="18"/>
                                                                    <grd:datecolumn dataField="PassCode" headerText="PassCode" HAlign="right" width="18"/>
                                                                    <grd:textcolumn dataField="BName" headerText="Bridge Name" HAlign="right" width="18"/> 
                                                                     <grd:textcolumn dataField="Status" headerText="Status" width="18"/> 
                                                                    
                                                                       <grd:textcolumn dataField="CreatedBy" headerText="CreatedBy" width="18"/>
                                                                       
                                                                     <grd:datecolumn dataField="CreatedDate" headerText="Created&nbsp;Date"  HAlign="right" width="16"  dataFormat="MM-dd-yyyy" />
                                                                   
                                                                    <%--<grd:imagecolumn headerText="Delete" width="4" HAlign="center" 
                                                                                     imageSrc="../../includes/images/DBGrid/Delete.png"
                                                                                     linkUrl="deleteReview.action?reviewId={Id}" imageBorder="0"
                                                                                     imageWidth="51" imageHeight="20" alterText="Delete"></grd:imagecolumn> 
--%>


                                                                </grd:dbgrid>
                                                               



                                                                <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                                <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                                <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">

                                                                <input type="hidden" name="submitFrom" value="dbGrid">
                                                                <s:hidden  name="bmsBridgeStatus" value="%{bmsBridgeStatus}"/>
                                                                 <s:hidden  name="bridgeCode" value="%{bridgeCode}"/>
                                                              
                                                            </td>
                                                        </tr>
                                                    </table>                                

                                                </s:form>

                                                <%
                                                        connection.close();
                                                        connection = null;
                                                    } catch (Exception ex) {
                                                        out.println(ex.toString());
                                                    } finally {
                                                        if (connection != null) {
                                                            connection.close();
                                                            connection = null;
                                                        }
                                                    }
                                                %>
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
        <!--//END FOOTER : Record for Footer Background and Content-->

    </table>
    <!--//END MAIN TABLE : Table for template Structure-->



</body>
</html>