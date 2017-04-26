<%-- 
    Document   : ClientEngagementRequestSearch
    Created on : May 24, 2016, 4:57:52 PM
    Author     : miracle
--%>

<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="com.freeware.gridtag.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hubble Organization Portal :: Client Engagement Request</title>
   <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
        
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
     
     <script type="text/javascript" src="<s:url value="/includes/javascripts/crm/ClientEngagementRequest.js"/>"></script>   

        <s:include value="/includes/template/headerScript.html"/> 
    </head>
    <%-- <body class="bodyGeneral" oncontextmenu="return false;" onload="getRequirementsList1();"> --%>
  
        <body class="bodyGeneral" oncontextmenu="return false;">
    

        <%!    /*
             * Declarations
             */
            Connection connection;
            String queryString;
            //StringBuffer queryString;
            String strTmp;
            String strSortCol;
            String strSortOrd;
            String userId;
            String submittedFrom;
            String action;
            //new
            String userRoleName;
            int role;
            int intSortOrd = 0;
            int intCurr;
            boolean blnSortAsc = true;
        %>


        <!-- Start oif the table -->
        <table class="templateTable1000x580" align="center" cellpadding="0" cellspacing="0">


            <!--//START HEADER : Record for Header Background and Mirage Logo-->
            <tr class="headerBg">
                <td valign="top">
                    <s:include value="/includes/template/Header.jsp"/>                    
                </td>
            </tr>
            <!--//END HEADER : Record for Header Background and Mirage Logo-->

            <s:hidden name="isInvestmentRecordsExist" id="isInvestmentRecordsExist" value="%{isInvestmentRecordsExist}"/>
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
                            <td width="850px" class="cellBorder" valign="top" style="padding:10px 5px 5px 5px;">
                                <!--//START TABBED PANNEL : -->
                                <ul id="accountTabs" class="shadetabs" >
                                    <li ><a href="#" class="selected" rel="requirementList">Client Engagement Request Search</a></li>
                                    <!-- <li ><a href="#" rel="searchDiv">Requirement Search</a></li>-->
                                </ul>
                                
                                 
                                
                                <div  style="border:1px solid gray; width:845px;height: 530px; overflow:auto; margin-bottom: 1em;">
                                    <%--  <sx:tabbedpanel id="attachmentPannel2" cssStyle="width: 845px; height: 530px;padding:10px 5px 5px 5px" doLayout="true"> --%>

                                    <!--//START TAB : -->
                                    <%--  <sx:div id="List" label="" cssStyle="overflow:auto;"> --%>
                                   

                                                 <s:form name="frmClientEngagementRequestSearch"  action="clientReqEngagementPreSalesSearch.action" theme="simple" >

                                                    <table cellpadding="1" cellspacing="1" border="0" width="650px" align="center">
                                                       
                                       
                                            <tr>
                                                <td class="fieldLabel">Start&nbsp;Date&nbsp;(mm/dd/yyyy)&nbsp;:</td>
                                                <td><s:textfield name="startDate" id="startDate" cssClass="inputTextBlueSmall" value="%{startDate}"/><a href="javascript:cal2.popup();">
                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                </td>

                                                <td class="fieldLabel">End&nbsp;Date&nbsp;(mm/dd/yyyy)&nbsp;:</td>
                                                <td>  <s:textfield name="endDate" id="endDate" cssClass="inputTextBlueSmall" value="%{endDate}"/><a href="javascript:cal3.popup();">
                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                </td>
                                            </tr>

                                       <tr>
                                           <td class="fieldLabel">Request&nbsp;type:</td>
                                       <td>
                                                            <s:select id="requestType" name="requestType" value="%{requestType}" list="{'PSCER','RFP'}" cssClass="inputSelect" headerKey="" headerValue="All" />
                                      </td>  
                                                       <td align="right" colspan="4">
                                               <!--  <input type="button" class="buttonBg"  align="right"  value="Add" onclick="investmentToggleOverlay();" />                      -->
                                                 <s:submit cssClass="buttonBg"  align="right"  value="Search" onclick="return compareDates()"/>
                                                  
                                                </td>
                                            </tr>
                                        </table>
                                                 <br></br>
                                    </s:form>
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


                                            connection = ConnectionProvider.getInstance().getConnection();
                                            if (request.getAttribute("crsQueryString") != null) {
                                                queryString = request.getAttribute("crsQueryString").toString();
                                            }
                                           // out.println(queryString);
                                              //System.out.println("queryString--***>" + queryString);
%>


                                    <s:form action="" theme="simple" name="frmDBGrid">   

                                        <table cellpadding="0" cellspacing="0" width="100%" border="0">

<tr>
                                                <td width="100%">

<s:if test="%{requestType=='RFP'}"> 
                                                    <grd:dbgrid id="tblPSCER" name="tblPSCER" width="98" pageSize="15"
                                                    currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2"
                                                    dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">

                                                        <%--    <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                           imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                           addImage="../../includes/images/DBGrid/Add.png"  addAction="<%=empReviewAction %>"
                                                                           /> --%>
                                                        <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                       imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                          />


                                                        <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" 
                                                                        imageAscending="../../includes/images/DBGrid/ImgAsc.gif" 
                                                                        imageDescending="../../includes/images/DBGrid/ImgDesc.gif"/>   
                                                        <!--    Inv_Id Inv_Name Country StartDate EndDate TotalExpenses Currency Location Description AttachmentFileName AttachmentLocation CreatedBy CreatedDate ModifiedBy ModifiedDate -->
                                                        <%-- <grd:anchorcolumn dataField="Inv_Id" 
                                                                           headerText="InvestmentId" 
                                                                           linkUrl="javascript:toggleEditInvestmentOverlay({Inv_Id})" linkText="{Inv_Id}" width="20"/> --%>
                                                       
                                                         <grd:rownumcolumn headerText="SNo" width="4" HAlign="right"/>
                                        
                                        <grd:imagecolumn headerText="Edit" width="4" HAlign="center" imageSrc="../../includes/images/DBGrid/newEdit_17x18.png"
                                                         linkUrl="getClientEngagementDetails.action?requestId={Id}" imageBorder="0"
                                                         imageWidth="16" imageHeight="16" alterText="Click to edit"></grd:imagecolumn>
                                                        
                                                        
                                                       

                                                        <grd:textcolumn dataField="CustomerName"  headerText="CustomerName"  width="15" /> 
                                                        <grd:textcolumn dataField="State"  headerText="State"  width="15" /> 
                                                         <grd:textcolumn dataField="RequestType"  headerText="RequestType"  width="15" />
                                                      <%-- <grd:textcolumn dataField="MeetingType"  headerText="MeetingType"  width="15" /> 
                                                         <grd:datecolumn dataField="MeetingDate"  headerText="MeetingDate"  dataFormat="MM/dd/yyyy" width="12" /> --%>
                                                                <grd:datecolumn dataField="CreatedDate"  headerText="CreatedDate"  dataFormat="MM/dd/yyyy" width="12" /> 
                                                              <grd:textcolumn dataField="RequestorId"  headerText="Requestor"  width="15" />        
                                                 <grd:anchorcolumn dataField="Comments" 
                                                                          headerText="Comments"
                                                                          linkUrl="javascript:getRequestComments({Id})" linkText="Click to View" width="15"/>
                                                        

                                                        <%--<grd:imagecolumn headerText="Delete" width="4" HAlign="center" 
                                                                         imageSrc="../../includes/images/DBGrid/Delete.png"
                                                                         linkUrl="deleteReview.action?reviewId={Id}" imageBorder="0"
                                                                         imageWidth="51" imageHeight="20" alterText="Delete"></grd:imagecolumn> 
                                                        --%>


                                                    </grd:dbgrid>

</s:if>
                                                        <s:else>
                                                              <grd:dbgrid id="tblPSCER" name="tblPSCER" width="98" pageSize="15"
                                                    currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2"
                                                    dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">

                                                        <%--    <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                           imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                           addImage="../../includes/images/DBGrid/Add.png"  addAction="<%=empReviewAction %>"
                                                                           /> --%>
                                                        <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                       imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                          />


                                                        <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" 
                                                                        imageAscending="../../includes/images/DBGrid/ImgAsc.gif" 
                                                                        imageDescending="../../includes/images/DBGrid/ImgDesc.gif"/>   
                                                        <!--    Inv_Id Inv_Name Country StartDate EndDate TotalExpenses Currency Location Description AttachmentFileName AttachmentLocation CreatedBy CreatedDate ModifiedBy ModifiedDate -->
                                                        <%-- <grd:anchorcolumn dataField="Inv_Id" 
                                                                           headerText="InvestmentId" 
                                                                           linkUrl="javascript:toggleEditInvestmentOverlay({Inv_Id})" linkText="{Inv_Id}" width="20"/> --%>
                                                       
                                                         <grd:rownumcolumn headerText="SNo" width="4" HAlign="right"/>
                                        
                                        <grd:imagecolumn headerText="Edit" width="4" HAlign="center" imageSrc="../../includes/images/DBGrid/newEdit_17x18.png"
                                                         linkUrl="getClientEngagementDetails.action?requestId={Id}" imageBorder="0"
                                                         imageWidth="16" imageHeight="16" alterText="Click to edit"></grd:imagecolumn>
                                                        
                                                        
                                                       

                                                        <grd:textcolumn dataField="CustomerName"  headerText="CustomerName"  width="15" /> 
                                                        <grd:textcolumn dataField="State"  headerText="State"  width="15" /> 
                                                         <grd:textcolumn dataField="RequestType"  headerText="RequestType"  width="15" />
                                                    <grd:textcolumn dataField="MeetingType"  headerText="MeetingType"  width="15" /> 
                                                         <grd:datecolumn dataField="MeetingDate"  headerText="MeetingDate"  dataFormat="MM/dd/yyyy" width="12" /> 
                                                                <grd:datecolumn dataField="CreatedDate"  headerText="CreatedDate"  dataFormat="MM/dd/yyyy" width="12" /> 
                                                              <grd:textcolumn dataField="RequestorId"  headerText="Requestor"  width="15" />        
                                                 <grd:anchorcolumn dataField="Comments" 
                                                                          headerText="Comments"
                                                                          linkUrl="javascript:getRequestComments({Id})" linkText="Click to View" width="15"/>
                                                        

                                                        <%--<grd:imagecolumn headerText="Delete" width="4" HAlign="center" 
                                                                         imageSrc="../../includes/images/DBGrid/Delete.png"
                                                                         linkUrl="deleteReview.action?reviewId={Id}" imageBorder="0"
                                                                         imageWidth="51" imageHeight="20" alterText="Delete"></grd:imagecolumn> 
                                                        --%>


                                                    </grd:dbgrid>    
                                                            
                                                        </s:else>


                                                    <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                    <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                    <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                                                    <input type="hidden" name="submitFrom" value="dbGrid">
                                                    <s:hidden name="startDate" value="%{startDate}"/>
                                                    <s:hidden name="endDate" value="%{endDate}"/>
                                                    <s:hidden name="requestType" value="%{requestType}"/>
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


                                    <%--   </sx:div> --%>


                                    <script type="text/javascript">
                                               
                                                var cal2 = new CalendarTime(document.forms['frmClientEngagementRequestSearch'].elements['startDate']);
                                                cal2.year_scroll = true;
                                                cal2.time_comp = false;
                                                var cal3 = new CalendarTime(document.forms['frmClientEngagementRequestSearch'].elements['endDate']);
                                                cal3.year_scroll = true;
                                                cal3.time_comp = false;
                                               
                                                                                 
                                            
                                    
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

        <!--  End of the main table -->        
   <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/jquery.min.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/jquery.js"/>"></script>   

    </body>
</html>
 

