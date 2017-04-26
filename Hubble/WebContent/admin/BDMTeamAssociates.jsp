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
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<html>
    <head>
        <title>Hubble Organization Portal :: Employee Search</title>
    
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
    <sx:head cache="true"/>

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
    <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/ajaxfileupload.js"/>"></script>  
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/FileUpload.js"/>"></script>
 <%--   <script type="text/JavaScript" src="<s:url value="/includes/javascripts/pmoDashBoardAjax.js?version=3.4"/>"></script>  --%>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/BdmAssociation.js?version=3.5"/>"></script>  

    <s:include value="/includes/template/headerScript.html"/>
</head>
<body class="bodyGeneral">

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
                                <li ><a href="#" class="selected" rel="employeeSearchTab"  >BDM Team Association</a></li>
                            </ul>
                            <%--<sx:tabbedpanel id="employeeSearchPannel" cssStyle="width: 840px; height: 500px;padding: 5px 5px 5px 5px;" doLayout="true"> --%>
                            <div  style="border:1px solid gray; width:840px;height: 500px; overflow:auto; margin-bottom: 1em;">   
                                <!--//START TAB : -->
                                <%--  <sx:div id="employeeSearchTab" label="Employee Search" cssStyle="overflow:auto;"  > --%>
                                <div id="employeeSearchTab" class="tabcontent"  >
                                    <div id="overlayBdmAssociates"></div>
                                    <div id="specialBoxBdmAssociates">
        

                                        <s:form theme="simple"  align="center" name="addBdmTeamAssociates" action=" " method="post" enctype="multipart/form-data" onsubmit="return checkResourceNameValidation();">
                                        
                                            <table align="center" border="0" cellspacing="0" style="width:100%;">
                                                <tr>                               
                                                    <td colspan="2" style="background-color: #288AD1" >
                                                        <h3 style="color:darkblue;" align="left">
                                                            <span id="headerLabel"></span>


                                                        </h3></td>
                                                    <td colspan="2" style="background-color: #288AD1" align="right">

                                                        <a href="#" onmousedown="mytoggleOverlayBdmAssociates()" >
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
                                                    <table style="width:50%;" align="center" border="0">
                                                <tr id="addSalesTeam">
                                                    <td class="fieldLabel">Resource&nbsp;Name&nbsp;:</td>
                                     <td ><s:textfield name="assignedToBDM" id="assignedToBDM" value="%{assignedToBDM}" onkeyup="SalesTeam();" cssClass="inputTextBlue"  theme="simple" readonly="false" />
                                     <div id="authorBdmValidationMessage" style="position: absolute; overflow:hidden;"></div>  
                                     <s:hidden name="preAssignSalesId" value="%{preAssignSalesId}" id="preAssignSalesId"/>  
                                     <s:hidden name="bdmName" id="bdmName" value="%{bdmName}"/>
                                      <s:hidden name="bdmId" id="bdmId" value="%{bdmId}"/>
                                </td>
                                               
                                <td align="left"><input type="button" class="buttonBg" align="left" onClick="addSalesTeamToBdm()"  value="Add"/></td>
                                                    </tr>
                                                    
                                                    
                                                    <tr id="editSalesTeam">
                                                    <td class="fieldLabel">Resource&nbsp;Name&nbsp;:</td>
                                     <td ><s:textfield name="salesTeamMember" id="salesTeamMember" onkeyup="SuggestSalesTeam();" value="%{salesTeamMember}" cssClass="inputTextBlue"  theme="simple"/>
                                </td>
                                  <div id="authorBdmUpdateValidationMessage" style="position: absolute; overflow:hidden;"></div>  
                                <td ><s:hidden name="teamMemberId" id="teamMemberId" value="%{teamMemberId}" cssClass="inputTextBlue"  theme="simple"/>
                                     <td ><s:hidden name="uniqueId" id="uniqueId" value="%{uniqueId}" cssClass="inputTextBlue"  theme="simple"/>
                                </td>
                                       <td class="fieldLabel" >Status&nbsp;:<FONT color="red"  ><em>*</em></FONT> </td>
                                                            <td width="15%"> <s:select id="teamMemberStatus"  name="teamMemberStatus"  list="{'Active','Inactive'}"  value="%{teamMemberStatus}" cssClass="inputSelect" disabled="False"/></td>        
                                <td align="right" colspan="3"><table><tr><td><div id="updateBdm">
                                                <input type="button" class="buttonBg"  align="right" onclick="updateSalesTeam();"  value="Update" /></div></td>
                                                    <%--<input type="button"  value="Add" class="buttonBg"  style="margin-left: 28px;"/> </div></td> --%>
                                                                    </tr></table></td>
                                                    </tr>
                                                    
                                                    
                                               <%-- <tr></tr>--%>
                                                    
                                                 
                                                        </table>
                                                    </td>
                                                </tr>
                                                <tr>
        <td>
               <div style="display: none; position: fixed; top:69px;left:242px;overflow:auto;" id="menu-popup">
                        <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" ></table>
                    </div>
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
                                                <%if (request.getAttribute("resultMessage") != null) {
                                       out.println(request.getAttribute("resultMessage"));
                                   }%>
                                            </td>
                                        </tr>

                                        <tr>
                                            <td style="padding-left:10px;padding-top:5px;">

                                                <s:form name="frmAccTeam" action="" theme="simple">
                                            <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                               <tr>
                                               <td  class="fieldLabel" style="text-align: left;width: 70px;">
                                                        BDM Name :  </td>
                                               <td class="navigationText">
                                               <s:property value="%{bdmName}"/>
                                                        <s:hidden name="bdmName" value="%{bdmName}"/>
                                                        <s:hidden name="bdmId" value="%{bdmId}"/>
                                                    </td>
                                               <td align="right">
                                                 <a href="<s:url action="../admin/getBdmAssociates"></s:url>" style="align:center;">
                                                                    <img alt="Back to List"
                                                                         src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                    width="66px" 
                                                                    height="19px"
                                                                    border="0" align="bottom"></a>
                                            </td> 
                                            </tr>
                                              <%-- <tr>
                                                            <td colspan="4" align="right">
                                                                <input type="button" class="buttonBg"  align="right"  value="Add" onclick="mytoggleOverlayBdmAssociates();" />                     
                                                            

                                                            </td>
                                                        </tr>  --%>

                                              
                                                    

                                             
                                                

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
                                                        searchSubmitValue =submittedFrom;
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

                                                   
                                                        /* Sql query for retrieving resultset from DataBase */
                                                       
                                                       if (request.getAttribute(ApplicationConstants.QUERY_STRING) != null) {
                                                           queryString = request.getAttribute(ApplicationConstants.QUERY_STRING).toString();
                                                         //   out.println(queryString);
                                                        }

//queryString  ="SELECT Id,CONCAT(TRIM(FName),'.',TRIM(LName)) AS EmployeeName FROM tblEmployee WHERE CurStatus='Active' AND TitleTypeId='BDM' ";
                                                     

                                                        /* else {
                                                        if(session.getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString().equalsIgnoreCase("Employee")){
                                                        queryString="SELECT  CONCAT(tblEmployee.FName,' ',tblEmployee.MName,'.',tblEmployee.LName) AS EmployeeName,ReviewType,EmpId,ReviewTypeId,EmpComments,tblEmpReview.CreatdBy,tblEmpReview.CreatedDate,AttachmentName,AttachmentLocation,ReviewName,tblEmpReview.Id AS Id,HrComments FROM tblEmpReview JOIN tblLkReviews ON (ReviewTypeId = tblLkReviews.Id) JOIN tblEmployee ON (tblEmpReview.EmpId = tblEmployee.Id) WHERE tblEmpReview.EmpId="+empId;
                                                        }else if(session.getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString().equalsIgnoreCase("Hr")) {
                                                        queryString="SELECT  CONCAT(tblEmployee.FName,' ',tblEmployee.MName,'.',tblEmployee.LName) AS EmployeeName,ReviewType,EmpId,ReviewTypeId,EmpComments,tblEmpReview.CreatdBy,tblEmpReview.CreatedDate,AttachmentName,AttachmentLocation,ReviewName,tblEmpReview.Id AS Id,HrComments FROM tblEmpReview JOIN tblLkReviews ON (ReviewTypeId = tblLkReviews.Id) JOIN tblEmployee ON (tblEmpReview.EmpId = tblEmployee.Id) WHERE WorkingCountry='"+session.getAttribute(ApplicationConstants.WORKING_COUNTRY).toString()+"'";                                  
                            
                                                        }
                            
                                                        }*/
                                                        // queryString="SELECT ReviewType,EmpId,ReviewTypeId,EmpComments,CreatdBy,CreatedDate,AttachmentName,AttachmentLocation,ReviewName,tblEmpReview.Id as Id FROM tblEmpReview JOIN tblLkReviews ON (ReviewTypeId = tblLkReviews.Id) where EmpId="+empId;
                                                      //out.println(queryString);

                                                       // out.println("--------"+submittedFrom);
%>

                                                <s:form action="" theme="simple" name="frmDBGrid">   

                                                    <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">


                                                        <tr>
                                                        <td width="100%" align="center">

                                                               
                                                                <grd:dbgrid id="tblEmployee" name="tblEmployee" width="98" pageSize="12"
                                                                currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2"
                                                                dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">

                                                                    <%--    <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                                       imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                                       addImage="../../includes/images/DBGrid/Add.png"  addAction="<%=empReviewAction %>"
                                                                                       /> --%>
                                                                    <grd:gridpager imgFirst="../includes/images/DBGrid/First.gif" imgPrevious="../includes/images/DBGrid/Previous.gif" 
                                                                                   imgNext="../includes/images/DBGrid/Next.gif" imgLast="../includes/images/DBGrid/Last.gif"
                                                                                   addImage="../includes/images/DBGrid/Add.png"  addAction="javascript:mytoggleOverlayBdmAssociates()"
                                                                                   />            

                                                                    <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" 
                                                                                    imageAscending="../includes/images/DBGrid/ImgAsc.gif" 
                                                                                    imageDescending="../includes/images/DBGrid/ImgDesc.gif"/>   
                                                                   
                                                                <%--     <grd:anchorcolumn dataField="ReviewName" 
                                                                                      headerText="Review Title" 
                                                                                      linkUrl="javascript:toggleEditOverlay({Id})" linkText="{ReviewName}" width="20"/>
                                                                    <grd:textcolumn dataField="ReviewType" headerText="ReviewType" width="18"/>

                                                                  
                                                                   <grd:numbercolumn dataField="Id" headerText="RoleId" width="15" dataFormat="0"/> --%>
                                                                     <grd:rownumcolumn headerText="SNo" width="5" HAlign="right"/>
                                                                 <%--    <grd:textcolumn dataField="EmployeeName" headerText="Employee Name" width="18"/> --%>
                                                                 <grd:anchorcolumn dataField="EmployeeName" headerText="EmployeeName" linkUrl="javascript:toggleEditBDMAssociateOverlay({Id},{BdmId})" linkText="{EmployeeName}"/>
                                                                     <grd:textcolumn dataField="Email" headerText="Email-Id" width="15"/> 
                                                                     <grd:textcolumn dataField="WorkPhnNo" headerText="Work Phone" width="15"/> 
                                                                     <grd:textcolumn dataField="Title" headerText="Title" width="15"/> 
                                                                     <grd:textcolumn dataField="Status" headerText="Status" width="15"/> 
                                                                     <grd:textcolumn dataField="CreatedBy" headerText="CreatedBy" width="15"/> 
                                                                    <%--  <grd:datecolumn dataField="ReviewDate"  headerText="ReviewDate"  HAlign="right" dataFormat="MM-dd-yyyy" width="15" /> 


                                                                   
                                                                  <grd:imagecolumn headerText="Delete" width="4" HAlign="center" 
                                                                                     imageSrc="../../includes/images/DBGrid/Delete.png"
                                                                                     linkUrl="deleteReview.action?reviewId={Id}" imageBorder="0"
                                                                                     imageWidth="51" imageHeight="20" alterText="Delete"></grd:imagecolumn> 
--%>


                                                                </grd:dbgrid>
                                                               

                                                              


                                                                <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                                <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                                <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                                                                <input type="hidden" name="submitFrom" value="dbGrid">
                                                      
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

