<!--/*******************************************************************************
/*
 * @(#)GreenSheetList.java	September 24, 2007, 12:13 AM 
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */ -->

<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%--<%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<%@ page import="com.mss.mirage.util.*,java.util.Date" %>
<%@ page import="com.freeware.gridtag.*" %> 
<%@ taglib prefix="grd" uri="/WEB-INF/tlds/datagrid.tld" %> 

<html>
    <head>
        
        
        <title>Hubble Organization Portal :: GreenSheet List</title>
        <sx:head cache="false"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" HREF="<s:url value="/includes/css/GridStyle.css"/>" type="text/css">
        <link rel="stylesheet" HREF="<s:url value="/includes/css/leftMenu.css"/>" type="text/css">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script language="javascript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
        <%--<script language="javascript" src="<s:url value="/includes/javascripts/TimeSheetWeekDays.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>--%>
        <script type="text/javascript" src="<s:url value="/includes/javascripts/GreenSheetClientValidation.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/javascripts/GreenSheetUtil.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>  
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
    </head>
   <!-- <body class="bodyGeneral" onload="init1();" oncontextmenu="return false;"> -->
    <body class="bodyGeneral" oncontextmenu="return false;">
        
        <table class="templateTableLogin" align="center" cellpadding="0" cellspacing="0" border="0">
            
            <tr class="headerBg">
                <td valign="top">
                    <s:include value="/includes/template/Header.jsp"/>                    
                </td>
            </tr> 
            
            <tr>
                <td>
                    <table class="innerTableLogin" cellpadding="0" cellspacing="0">
                        <tr>
                            
                            
                            <td width="150px;" class="leftMenuBgColor" valign="top">
                                <s:include value="/includes/template/LeftMenu.jsp"/>
                            </td>
                            
                            <td width="850px" class="cellBorder" valign="top" style="padding-left:10px;padding-top:5px;">
                                <!--//START TABBED PANNEL : -->
                                <ul id="accountTabs" class="shadetabs" >
                                    <li ><a href="#" class="selected" rel="greenSheetListTab"> GreenSheet List </a></li>
                                    <li ><a href="#" rel="contactTab">Search</a></li>
                                </ul>
                                
                                <%-- <sx:tabbedpanel id="greenSheetListPannel" cssStyle="width: 845px; padding-left:10px;padding-top:5px;" doLayout="false"> --%>
                                <div  style="border:1px solid gray; width:845px;overflow:auto; margin-bottom: 1em;">
                                    <!--//START TAB : -->
                                    <%--  <sx:div id="greenSheetListTab" label="GreenSheet List" theme="ajax" > --%>
                                    <div id="greenSheetListTab" class="tabcontent"  >
                                        <table cellpadding="0" border="0" cellspacing="0" width="100%" align="center">
                                            <tr>
                                                <td class="headerText" >
                                                    <s:property value="#request.resultMessage"/>
                                                    
                                                    <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0"> 
                                                </td>
                                            </tr> 
                                            
                                            <tr>
                                                <td align="center">
                                                    
                                                    <%
                                                    int intCurr = 1;
                                                    int intSortOrd = 0;
                                                    String strTmp = null;
                                                    StringBuffer   strSQL= null;
                                                    String strSortCol = null;
                                                    String strSortOrd = "ASC";
                                                    boolean blnSortAsc = true;
                                                    
                                                    
                                                    
                                                    strSQL = (StringBuffer)session.getAttribute(ApplicationConstants.QS_GS_LIST);
                                                    Connection connection = ConnectionProvider.getInstance().getConnection();
                                                    
                                                    strTmp = request.getParameter("txtCurr");
                                                    
                                                    try {
                                                        if (strTmp != null)
                                                            intCurr = Integer.parseInt(strTmp);
                                                        
                                                    } catch (NumberFormatException NFEx) {
                                                    }
                                                                       /* Specifing Shorting Column*/
                                                            strSortCol = request.getParameter("txtSortCol");
                                                           // if (strSortCol == null) strSortCol = "CustomerName";
                                                             if (strSortCol == null) strSortCol = "DateStart";

                                                            strSortOrd = request.getParameter("txtSortAsc");
                                                            if (strSortOrd == null) strSortOrd = "DESC";

                                                            blnSortAsc = (strSortOrd.equals("ASC"));
                                                    
                                                //  out.println(strSQL);
                                                    %>
                                                    
                                                    <form method="post" id="frmDBGrid" name="frmDBGrid" action="">  
                                                        
                                                        <s:if test="#request.teamGreensheets == 'true'">                                            
                                                            <grd:dbgrid  id="tblStat" name="tblStat"  width="100" pageSize="17" 
                                                                         currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                         dataMember="<%=strSQL.toString()%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                
                                                                <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                               imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                               /><%-- addImage="../../includes/images/DBGrid/Add.png"
                                                                               addAction="viewGreenSheet.action?teamGreensheets=true"--%>
                                                                <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>"  imageAscending="../../includes/images/DBGrid/ImgAsc.gif" imageDescending="../../includes/images/DBGrid/ImgDesc.gif" />
                                                               
                                                                <grd:imagecolumn  headerText="Edit" width="5"  HAlign="center"  
                                                                                  imageSrc="../../includes/images/DBGrid/newEdit_17x18.png" 
                                                                                  linkUrl="getGreenSheetByID.action?greenSheetId={Id}&teamGreensheets=true" 
                                                                                  imageBorder="0" imageWidth="16" imageHeight="16" alterText="Click to edit" />
                                                               <%-- <grd:textcolumn dataField="" headerText="Status"  sortable="false" 
                                                                                width="20"/>   --%>              
                                                                <grd:textcolumn dataField="CustomerName" headerText="Customer Name"  sortable="true"
                                                                                width="20"/>
                                                                <grd:textcolumn dataField="ConsultantName" headerText="Consultant Name" sortable="false" 
                                                                                width="19"/>
                                                                <grd:textcolumn dataField="Status" headerText="GreenSheet Status" 
                                                                                width="10" sortable="false"  HAlign="center" /> 
                                                                <grd:textcolumn dataField="ConsultantType" headerText="Consultant Type" 
                                                                                width="14"  HAlign="center"/>
                                                                               
                                                                <grd:datecolumn dataField="DateStart" headerText="Start Date" dataFormat="MM-dd-yyyy"
                                                                                width="9" sortable="true" />
                                                                <grd:datecolumn dataField="DateEnd" headerText="End Date" sortable="true" dataFormat="MM-dd-yyyy"
                                                                                width="8"/>
                                                                <grd:numbercolumn dataFormat="#00.00" dataField="UnitsRate" headerText="Billing Rate/hr " />
                                                                <grd:textcolumn dataField="POStatus" headerText="POStatus" 
                                                                                width="10" sortable="true" HAlign="center" />
                                                                 
                                                                <grd:textcolumn dataField="CreatedBy" headerText="CreatedBy" sortable="false"/>
                                                                <grd:textcolumn dataField="ModifiedBy" headerText="ModifiedBy" sortable="false"/>
                                                                
                                                            </grd:dbgrid>
                                                        </s:if>                                                        
                                                        
                                                        <s:else>    
                                                            <grd:dbgrid  id="tblStat" name="tblStat" width="100" pageSize="17" 
                                                                         currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                         dataMember="<%=strSQL.toString()%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                
                                                                <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                               imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                               addImage="../../includes/images/DBGrid/Add.png"/>
                                                                
                                                                <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>"  imageAscending="../../includes/images/DBGrid/ImgAsc.gif" imageDescending="../../includes/images/DBGrid/ImgDesc.gif" />
                                                                <grd:imagecolumn  headerText="Edit" width="5"  HAlign="center"  
                                                                                  imageSrc="../../includes/images/DBGrid/newEdit_17x18.png" 
                                                                                  linkUrl="getGreenSheetByID.action?greenSheetId={Id}&teamGreensheets=false" 
                                                                                  imageBorder="0" imageWidth="16" imageHeight="16" alterText="Click to edit" />
                                                                <grd:textcolumn dataField="CustomerName" headerText="Customer Name" sortable="true"   
                                                                                width="20"/>
                                                                <grd:textcolumn dataField="ConsultantName" headerText="Consultant Name" sortable="false" 
                                                                                width="19"/>
                                                                <grd:textcolumn dataField="ConsultantType" headerText="Consultant Type" 
                                                                                width="14"  HAlign="center"/>
                                                                <grd:datecolumn dataField="DateStart" headerText="Start Date" dataFormat="MM-dd-yyyy" sortable="true"
                                                                                width="9"/>
                                                            <grd:datecolumn dataField="DateEnd" headerText="End Date" sortable="true"  dataFormat="MM-dd-yyyy"
                                                                                width="8"/>
                                                                 <grd:textcolumn dataField="Status" headerText="GreenSheet Status" 
                                                                                width="10" sortable="false"  HAlign="center" />               
                                                                <grd:textcolumn dataField="POStatus" headerText="POStatus" 
                                                                                width="10" sortable="true"  HAlign="center" />
                                                                <grd:textcolumn dataField="CreatedBy" headerText="CreatedBy" />
                                                                <grd:textcolumn dataField="ModifiedBy" headerText="ModifiedBy" />
                                                                
                                                            </grd:dbgrid>
                                                        </s:else>   
                                                        
                                                        <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                        <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                        <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                                                        <input TYPE="hidden" name="submitFrom" value="dbGrid"/>
                                                        <input id="txtDBId" TYPE="hidden" NAME="txtDBId" VALUE="0">
                                                        <input id="txtTRId"TYPE="hidden" NAME="txtTRId" VALUE="1">
                                                    </form>
                                                    
                                                    <%
                                                    try {
                                                        connection.close();
                                                        connection = null;
                                                    } catch (SQLException SQLExIgnore) {
                                                    }finally{
                                                        if(connection!=null){
                                                            connection.close();
                                                            connection = null;
                                                        }
                                                    }
                                                    
                                                    %>
                                                </td>
                                                
                                            </tr>
                                            
                                        </table>
                                        <%--    </sx:div> --%>
                                    </div>
                                    <!--//END TAB : -->
                                    
                                    <%--  <sx:div id="contactTab" label="Search"  theme="ajax" > --%>
                                    <div id="contactTab" class="tabcontent"  >
                                        <s:form action="searchSubmit" theme="simple" id="frmAddGreenSheet" name="frmAddGreenSheet" onsubmit="return checkGreenSheetSearchForm();">
                                            <table  width="100%" border="0" cellspacing="0" cellpadding="0" >
                                                <tr>
                                                    <td class="headerText" align="right">                                                   
                                                        <s:submit cssClass="buttonBg" value="Search"/>
                                                    </td> 
                                                    <%-- <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">  --%>
                                                </tr> 
                                                
                                                <tr>
                                                    <td>
                                                        <table cellpadding="1" cellspacing="1" border="0" width="100%">
                                                            <tr>
                                                                <td class="fieldLabel">Customer Name : <FONT color="red"  ><em>*</em></FONT></td>
                                                                <td colspan="2">  <%-- onkeyup="fillCustomer();"--%>
                                                                    <s:textfield name="customerName" id="customerName"  cssClass="inputTextBlue"  value="%{customerName || '%'}"  theme="simple" onkeyup="fillCustomer();"/>
                                                                    <div id="validationMessage"></div>
                                                                    <s:hidden name="consultantId" value="%{accountId}" /> 
                                                                </td>
                                                                
                                                                <td class="fieldLabel"> PO Status :</td>
                                                                <td colspan="2">
                                                                    <s:select list="{'Open','Received','Cancelled','Closed'}" headerKey="" headerValue="--Please Select--" value="%{poStatusType}" name="poStatusType" cssClass="inputSelect"/>
                                                                    
                                                                    <s:hidden name="submitFrom" value="Search"/>
                                                                    <s:hidden name="teamScreen" value="%{teamGreensheets}"/>
                                                                </td>
                                                            </tr>
                                                            
                                                            
                                                            
                                                            <tr>
                                                                <td class="fieldLabel">EmpFirst Name :</td>
                                                                <td colspan="2"><s:textfield name="fname" id="fname" cssClass="inputTextBlue" onkeydown="return enableEnter(event);"/></td>
                                                                
                                                                <td class="fieldLabel">EmpLast Name :</td>
                                                                <td colspan="2"><s:textfield name="lastName" id="lastName" cssClass="inputTextBlue" onkeydown="return enableEnter(event);"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel">Primary Sales Person : </td>
                                                                <td colspan="2"><s:select list="salesMap"  name="primarySalesPerson"  headerKey="1" headerValue="-- Please Select --" cssClass="inputSelect" /></td>
                                                                <td class="fieldLabel">Primary Sales Manager : </td>
                                                                <td colspan="2"><s:select list="salesManagerMap"  name="primarySalesManager" headerKey="1" headerValue="-- Please Select --" cssClass="inputSelect" /></td>
                                                                
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel">Primary Vice President : </td>
                                                                <td colspan="2"><s:select list="salesManagerMap" name="primaryVicePresident"  headerKey="1" headerValue="-- Please Select --" cssClass="inputSelect" /></td>
                                                                <td class="fieldLabel">Green Sheet Status : </td>
                                                                <td><s:select list="{'Created','NotKnown','Renewal','Approved','Rejected'}" headerKey="1" headerValue="-- Please Select --"  name="status" id="status" cssClass="inputSelect" /></td>
                                                                
                                                            </tr>


                                                            <tr>
                                                                <td class="fieldLabel">PO StartDate :</td>
                                                                <td><s:textfield id="poStartDateFrom" name="poStartDateFrom" cssClass="inputDate" onkeydown="return enableEnter(event);"/>        
                                                                    <a href="javascript:cal1.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                         width="20" height="20" border="0" ></a>
                                                                    
                                                                </td>
                                                                <td><s:textfield id="poStartDateTo" name="poStartDateTo" cssClass="inputDate" onkeydown="return enableEnter(event);"/>        
                                                                    <a href="javascript:cal2.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                         width="20" height="20" border="0" ></a>
                                                                </td>
                                                                
                                                                <td class="fieldLabel">PO EndDate :</td>
                                                                <td><s:textfield id="poEndDateFrom" name="poEndDateFrom" cssClass="inputDate" onkeydown="return enableEnter(event);"/>
                                                                    <a href="javascript:cal3.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                         width="20" height="20" border="0" ></a>
                                                                </td>
                                                                <td><s:textfield id="poEndDateTo" name="poEndDateTo" cssClass="inputDate" onkeydown="return enableEnter(event);"/>
                                                                    <a href="javascript:cal4.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                         width="20" height="20" border="0" ></a>
                                                                </td>
                                                            </tr>  
                                                            
                                                            <tr>
                                                                <td class="fieldLabel">Consultant StartDate :</td>
                                                                <td><s:textfield id="empStartDateFrom" name="empStartDateFrom" cssClass="inputDate" onkeydown="return enableEnter(event);"/>
                                                                    <a href="javascript:cal5.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                         width="20" height="20" border="0" ></a>
                                                                </td>
                                                                <td><s:textfield id="empStartDateTo" name="empStartDateTo" cssClass="inputDate" onkeydown="return enableEnter(event);"/>
                                                                    <a href="javascript:cal6.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                         width="20" height="20" border="0" ></a>
                                                                </td>
                                                                
                                                                <td class="fieldLabel">Consultant EndDate :</td>
                                                                <td><s:textfield id="empEndDateFrom" name="empEndDateFrom" cssClass="inputDate" onkeydown="return enableEnter(event);"/>
                                                                    <a href="javascript:cal7.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                         width="20" height="20" border="0" ></a>
                                                                </td>
                                                                <td><s:textfield id="empEndDateTo" name="empEndDateTo" cssClass="inputDate" onkeydown="return enableEnter(event);"/>
                                                                    <a href="javascript:cal8.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                         width="20" height="20" border="0" ></a>
                                                                </td>
                                                            </tr>
                                                            
                                                            <s:if test="#session.roleName == 'Admin'">                                                                                                                        
                                                                <tr>    
                                                                    <td class="fieldLabel">Working Country :</td>                                                                                
                                                                    <td colspan="5">
                                                                        <s:select 
                                                                            list="countryList" 
                                                                            name="country" 
                                                                            id="country" 
                                                                            cssClass="inputSelect"
                                                                            headerKey=""
                                                                            headerValue="--Please Select--"
                                                                            theme="simple"/>
                                                                    </td>     
                                                                </tr> 
                                                            </s:if>
                                                            
                                                        </table>
                                                    </td>
                                                </tr>
                                                
                                                
                                            </table>
                                            
                                        </s:form>
                                        
                                        
                                        <script type="text/JavaScript">
                        var cal1 = new CalendarTime(document.forms['frmAddGreenSheet'].elements['poStartDateFrom']);
	           cal1.year_scroll = true;
	            cal1.time_comp = false;
            var cal2 = new CalendarTime(document.forms['frmAddGreenSheet'].elements['poStartDateTo']);
	      cal2.year_scroll = true;
	   cal2.time_comp = false;
           
           var cal3 = new CalendarTime(document.forms['frmAddGreenSheet'].elements['poEndDateFrom']);
	           cal3.year_scroll = true;
	            cal3.time_comp = false;
            var cal4 = new CalendarTime(document.forms['frmAddGreenSheet'].elements['poEndDateTo']);
	      cal4.year_scroll = true;
	   cal4.time_comp = false;
           
           var cal5 = new CalendarTime(document.forms['frmAddGreenSheet'].elements['empStartDateFrom']);
	           cal5.year_scroll = true;
	            cal5.time_comp = false;
            var cal6 = new CalendarTime(document.forms['frmAddGreenSheet'].elements['empStartDateTo']);
	      cal6.year_scroll = true;
	   cal6.time_comp = false;
           
           var cal7 = new CalendarTime(document.forms['frmAddGreenSheet'].elements['empEndDateFrom']);
	           cal7.year_scroll = true;
	            cal7.time_comp = false;
            var cal8 = new CalendarTime(document.forms['frmAddGreenSheet'].elements['empEndDateTo']);
	      cal8.year_scroll = true;
	   cal8.time_comp = false;
                                        </script>      
                                        
                                        
                                        <%--     </sx:div > --%>
                                    </div>
                                    
                                    <%--   </sx:tabbedpanel> --%>
                                </div>
                                <!--//END TABBED PANNEL : -->
                                <script type="text/javascript">

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
                <td align="center"><s:include value="/includes/template/Footer.jsp"/></td>
            </tr>
            <!--//END FOOTER : Record for Footer Background and Content-->
            <tr>
                <td>
                    
                    <div style="display: none; position: absolute; top:170px;left:320px;overflow:auto;" id="menu-popup">
                        <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" />
                    </div>
                    
                </td>
            </tr>
        </table>
        <!--//END MAIN TABLE : Table for template Structure-->
        <script type="text/javascript">
		$(window).load(function(){
		
		init1();
		
                
		});
		</script>
    </body>
</html>
