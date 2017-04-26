<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%-- <%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.freeware.gridtag.*" %> 
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd" %>
<html>
    <head>
        <title>Hubble Organization Portal :: AllReports</title>
        <%-- <sx:head cache="true"/> --%>
    
    
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/animatedcollapse.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/jquery-1.2.2.pack.js"/>"></script>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">        
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>        
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EnableEnter.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/VenusReport.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/VenusReportAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmpStandardClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/employee/DateValidator.js"/>"></script>
        <s:include value="/includes/template/headerScript.html"/>
        <script language="JavaScript">
		animatedcollapse.addDiv('timesheetsDiv', 'fade=1;speed=400;persist=1;group=app');
		animatedcollapse.addDiv('notApprovedDiv', 'fade=1;persist=1;group=app');
                animatedcollapse.addDiv('leaveReportDiv', 'fade=1;persist=1;group=app');               
               // animatedcollapse.addDiv('birthdayReports', 'fade=1;persist=1;group=app');   
                animatedcollapse.addDiv('projectReport', 'fade=1;persist=1;group=app');               
		animatedcollapse.init();
                
                function display(element)
               {
               
               var radios= document.forms['bDate'].elements['listby'];
                for (var i=0; i <radios.length; i++) {
                if (radios[i].checked) {
                //alert("You selected   "+document.bDate.listby[i].value)
                }
                }
                if(element.value==4)
                {
                document.getElementById('birthreport').style.display='block';
                }
                else{ 
                document.getElementById('birthreport').style.display='none';
                    }

               }
        </script>
    </head>
    <body  class="bodyGeneral"  oncontextmenu="return false;">
        <%!
        /* Declarations */
        Connection connection;
        
        String queryString;
        String userId;
        String strTmp;
        String strSortCol;
        String strSortOrd;
        
        int intSortOrd = 0;
        int intCurr;
        boolean blnSortAsc = true;
        %>
        
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
                            <td width="850px;" class="leftMenuBgColor" valign="top"> 
                                <s:include value="/includes/template/LeftMenu.jsp"/>
                            </td>
                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                            
                            <!--//START DATA COLUMN : Coloumn for Screen Content-->                            
                            <td width="850px" class="cellBorder" valign="top" style="padding: 5px 5px;">
                                
                                <ul id="reportTabs" class="shadetabs">
                                    <li ><a href="#" class="selected" rel="reportsTab">Report Details</a></li>
                                </ul>
                                
                                <!--//START TABBED PANNEL : -->
                                <div  style="border:1px solid gray; width: 845px; height: 550px; overflow:auto; margin-bottom: 1em;">
                                    <%--<sx:tabbedpanel id="resetPasswordPannel" cssStyle="width: 845px; height: 550px;padding-left:10px;padding-top:5px;" doLayout="true" useSelectedTabCookie="true">--%>
                                    
                                    <!--//START TAB : -->
                                    <%-- <sx:div id="reportsTab" label="Report Details" cssStyle="overflow:auto;"> --%>
                                    <div id="reportsTab" class="tabcontent">      
                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                            <tr>
                                                <td height="20px">
                                                    
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="homePortlet" valign="top">
                                                    <div class="portletTitleBar">
                                                        <div class="portletTitleLeft">Not Entered TimeSheets Report</div>
                                                        <div class="portletIcons">
                                                            <a href="javascript:animatedcollapse.hide('timesheetsDiv')" title="Minimize">
                                                            <img src="../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                            <a href="javascript:animatedcollapse.show('timesheetsDiv')" title="Maximize">
                                                                <img src="../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                            </a>                                                           
                                                        </div>
                                                        <div class="clear"></div>
                                                    </div>
                                                    <div id="timesheetsDiv" style="background-color:#ffffff">
                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                            <tr>
                                                                <td width="40%" valign="top" align="center">
                                                                    <s:form action="" theme="simple" name="timeSheetsReport">   
                                                                        
                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                                            <tr align="right">
                                                                                <td class="headerText" colspan="9">
                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">                                                        
                                                                                </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td>
                                                                                    <div id="loadMessage2" style="display: none" align="center" class="error" >
                                                                                        <b>Loading.... Please Wait....</b>
                                                                                    </div>
                                                                                    <table border="0" align="center" cellpadding="0" cellspacing="0">
                                                                                        <tr>                                                    
                                                                                            <td class="fieldLabel">Department :></td> 
                                                                                            <td><s:select list="departmentIdList" name="departmentId" id="departmentId" cssClass="inputTextBlue" /></td>
                                                                                            
                                                                                            <td class="fieldLabel">StartDate :<FONT color="red" SIZE="0.5"><em>*</em></FONT></td>                                                                                                                                                                             
                                                                                            <td><s:textfield name="timeSheetWeekStartDate" id="timeSheetWeekStartDate" cssClass="inputTextBlue" onchange="checkDates(this);"/> <a href="javascript:cal5.popup();">
                                                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                 width="20" height="20" border="0"></td>                                                                                                                                                                                            
                                                                                            
                                                                                            <td class="fieldLabel">EndDate :<FONT color="red" SIZE="0.5"><em>*</em></FONT></td>                                                                                                                                                                             
                                                                                            <td><s:textfield name="timeSheetWeekEndDate" id="timeSheetWeekEndDate" cssClass="inputTextBlue" onchange="checkDates(this);"/> <a href="javascript:cal6.popup();">
                                                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                 width="20" height="20" border="0"></td>
                                                                                            
                                                                                            <td colspan="4" align="center">
                                                                                                <input type="button" value="Search" class="buttonBg" onclick="getTimeSheetReportList()"/>
                                                                                            </td> 
                                                                                        </tr>
                                                                                    </table>
                                                                                </td>
                                                                            </tr>
                                                                            
                                                                            <tr >
                                                                                <td>
                                                                                    
                                                                                    <br>
                                                                                    <div id="timeSheetReportList" style="display: block">
                                                                                        <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                                        <table id="tblTimeSheets" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='80%' align="center">
                                                                                            <COLGROUP ALIGN="left" >
                                                                                            <COL width="3%">
                                                                                            <COL width="30%">
                                                                                            <COL width="25%">                                                                                                
                                                                                        </table>
                                                                                        <br>
                                                                                        <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                                                    </div>
                                                                                </td>
                                                                            </tr>
                                                                            
                                                                        </table>
                                                                    </s:form> 
                                                                </td>
                                                            </tr>
                                                        </table>
                                                        <script type="text/JavaScript">
                                                    var cal5 = new CalendarTime(document.forms['timeSheetsReport'].elements['timeSheetWeekStartDate']);
                                                    cal5.year_scroll = true;
                                                    cal5.time_comp = false; 
                                                    var cal6 = new CalendarTime(document.forms['timeSheetsReport'].elements['timeSheetWeekEndDate']);
                                                    cal6.year_scroll = true;
                                                    cal6.time_comp = false;
                                                        </script>
                                                    </div>
                                                </td>
                                            </tr>
                                            
                                            
                                            <tr>
                                                <td class="homePortlet" valign="top">
                                                    <div class="portletTitleBar">
                                                        <div class="portletTitleLeft">Entered TimeSheets Report</div>
                                                        <div class="portletIcons">
                                                            <a href="javascript:animatedcollapse.hide('notApprovedDiv')" title="Minimize">
                                                            <img src="../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                            <a href="javascript:animatedcollapse.show('notApprovedDiv')" title="Maximize">
                                                                <img src="../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                            </a>                                                           
                                                        </div>
                                                        <div class="clear"></div>
                                                    </div>
                                                    <div id="notApprovedDiv" style="background-color:#ffffff">
                                                        
                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                            <tr>
                                                                <td width="80%" valign="top" align="center">
                                                                    <s:form action="" theme="simple" name="notApprovedReport">   
                                                                        
                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                                            <tr align="right">
                                                                                <td class="headerText" colspan="9">
                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">                                                        
                                                                                </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td>
                                                                                    <div id="loadMessage3" style="display: none" align="center" class="error" >
                                                                                        <b>Loading.... Please Wait....</b>
                                                                                    </div>
                                                                                    <table border="0" align="center" cellpadding="0" cellspacing="0">
                                                                                        <tr>                                                    
                                                                                            <td class="fieldLabel">Department :</td> 
                                                                                            <td><s:select list="notApprovedDepartmentList" name="notApprovedDepartmentId" id="notApprovedDepartmentId" cssClass="inputTextBlue" /></td>
                                                                                            
                                                                                            <td class="fieldLabel">StartDate :<FONT color="red" SIZE="0.5"><em>*</em></FONT></td>                                                                                                                                                                             
                                                                                            <td><s:textfield name="notApprovedStartDate" id="notApprovedStartDate" cssClass="inputTextBlue" onchange="checkDates(this);"/> <a href="javascript:cal7.popup();">
                                                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                 width="20" height="20" border="0"></td>                                                                                                                                                                                            
                                                                                            
                                                                                            <td class="fieldLabel">EndDate :<FONT color="red" SIZE="0.5"><em>*</em></FONT></td>                                                                                                                                                                             
                                                                                            <td><s:textfield name="notApprovedEndDate" id="notApprovedEndDate" cssClass="inputTextBlue" onchange="checkDates(this);"/> <a href="javascript:cal8.popup();">
                                                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                 width="20" height="20" border="0"></td>
                                                                                            
                                                                                            <td colspan="4" align="center">
                                                                                                <input type="button" value="Search" class="buttonBg" onclick="getNotApprovedReportList()"/>
                                                                                            </td> 
                                                                                        </tr>
                                                                                        
                                                                                    </table>
                                                                                </td>
                                                                            </tr>
                                                                            
                                                                            <tr>
                                                                                <td>
                                                                                    
                                                                                    <br>
                                                                                    <div id="notApprovedTimeSheetReportList" style="display: block">
                                                                                        <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                                        <table id="tblNotApprovedTimeSheets" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='80%' align="center">
                                                                                            <COLGROUP ALIGN="left" >
                                                                                            <COL width="3%">
                                                                                            <COL width="30%">
                                                                                            <COL width="25%">                                                                                                
                                                                                        </table>
                                                                                        <br>
                                                                                        <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                                                    </div>
                                                                                </td>
                                                                            </tr>
                                                                            
                                                                            
                                                                        </table>
                                                                    </s:form> 
                                                                    
                                                                </td>
                                                            </tr>
                                                            <!--2nd td in First table -->
                                                        </table>
                                                        <script type="text/JavaScript">
                                                            
                                                              var cal8 = new CalendarTime(document.forms['notApprovedReport'].elements['notApprovedEndDate']);
                                                    cal8.year_scroll = true;
                                                    cal8.time_comp = false;
                                                    var cal7 = new CalendarTime(document.forms['notApprovedReport'].elements['notApprovedStartDate']);
                                                    cal7.year_scroll = true;
                                                    cal7.time_comp = false; 
                                                  
                                                        </script>
                                                    </div>
                                                </td>
                                            </tr> 
                                            <%--
                                            <tr>
                                                <td class="homePortlet" valign="top">
                                                    <div class="portletTitleBar">
                                                        <div class="portletTitleLeft">Proect Resources Report</div>
                                                        <div class="portletIcons">
                                                            <a href="javascript:animatedcollapse.hide('projectReport')" title="Minimize">
                                                            <img src="../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                            <a href="javascript:animatedcollapse.show('projectReport')" title="Maximize">
                                                                <img src="../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                            </a>                                                           
                                                        </div>
                                                        <div class="clear"></div>
                                                    </div>
                                                    <div id="projectReport" style="background-color:#ffffff">
                                                        
                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                            <tr>
                                                                <td width="80%" valign="top" align="center">
                                                                    
                                                                    
                                                                    <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                                        <tr align="right">
                                                                            <td class="headerText" colspan="9">
                                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                                            </td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>
                                                                                <s:form action="projectSearch" theme="simple" name="projectReport">   
                                                                                    <table border="1" align="center" cellpadding="0" cellspacing="0">
                                                                                        <tr>                                                    
                                                                                            <td class="fieldLabel">Project Name :</td> 
                                                                                            <td><s:textfield name="projectName" id="projectName" cssClass="inputTextBlue" /> </td>
                                                                                            <td class="fieldLabel">Employee Status:</td> 
                                                                                            <td><s:select name="empStatus" headerKey="" headerValue="All" list="{'Available','OverHead','OnProject'}" id="empStatus" cssClass="inputTextBlue" /> </td>
                                                                                            <td class="fieldLabel">Date :<FONT color="red" SIZE="0.5"><em>*</em></FONT></td>
                                                                                            <td><s:textfield name="projectDate" id="projectDate" cssClass="inputTextBlue" onchange="checkDates(this);"/> <a href="javascript:cal9.popup();">
                                                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                 width="20" height="20" border="0"></td>
                                                                                            <td align="center" width="100" >
                                                                                                <s:submit value="submit" cssClass="buttonBg" />
                                                                                            </td> 
                                                                                        </tr>
                                                                                        
                                                                                    </table>
                                                                                </s:form> 
                                                                            </td>
                                                                        </tr>
                                                                        
                                                                        <tr>
                                                                            <td>
                                                                                
                                                                                <%
                                                                                /* String Variable for storing current position of records in dbgrid*/
                                                                                //  System.out.println("first--");
                                                                                strTmp = request.getParameter("txtCurr");
                                                                                
                                                                                intCurr = 1;
                                                                                //   System.out.println("first--"+strTmp);
                                                                                if (strTmp != null)
                                                                                    intCurr = Integer.parseInt(strTmp);
                                                                                //    System.out.println("first--"+intCurr);
                                                                                /* Specifing Shorting Column */
                                                                                strSortCol = request.getParameter("txtSortCol");
                                                                                //     System.out.println("second--");
                                                                                if (strSortCol == null) strSortCol = "CreatedDate";
                                                                                
                                                                                strSortOrd = request.getParameter("txtSortAsc");
                                                                                if (strSortOrd == null) strSortOrd = "DESC";
                                                                                
                                                                                userId = session.getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                                                                                //     System.out.println("third--");
                                                                                try{
                                                                                    
                                                                                    /* Getting DataSource using Service Locator */
                                                                                    connection=ConnectionProvider.getInstance().getConnection();
                                                                                    
                                                                                    /* Sql query for retrieving resultset from DataBase */
                                                                                    queryString  =null;
                                                                                    //         System.out.println("here--");
                                                                                    //String  queryString=request.getAttribute(ApplicationConstants.QS_USER_LIST).toString();
                                                                                    if(request.getAttribute(ApplicationConstants.QS_USER_LIST) == null) {
                                                                                        queryString="select * from tblEmpStateHistory ";
                                                                                    }else {
                                                                                        queryString=request.getAttribute(ApplicationConstants.QS_USER_LIST).toString();
                                                                                    }
                                                                                    //       System.out.println("here--"+queryString);
                                                                                %>
                                                                                
                                                                                <form action="" name="frmDBGrid" id="frmDBGrid">   
                                                                                    <table cellpadding="0" cellspacing="0" width="95%" align="center">
                                                                                        <tr>
                                                                                            <td>
                                                                                                <!-- DataGrid for list all activities -->
                                                                                                <grd:dbgrid id="tblEmpInsurance" name="tblEmpInsurance" width="100" pageSize="8" 
                                                                                                            currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                                                            dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                                                    <grd:gridpager imgFirst="../includes/images/DBGrid/First.gif" imgPrevious="../includes/images/DBGrid/Previous.gif" 
                                                                                                                   imgNext="../includes/images/DBGrid/Next.gif" imgLast="../includes/images/DBGrid/Last.gif"/>
                                                                                                    <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" />
                                                                                                    <grd:rownumcolumn headerText="SNo" width="4" HAlign=""/>
                                                                                                    <grd:textcolumn dataField="LoginId"  headerText="Login Id"   width="18" HAlign="left"/>
                                                                                                    <grd:textcolumn dataField="PrjName"  headerText="Project Name"   width="18" HAlign="left"/>
                                                                                                    <grd:datecolumn dataField="StartDate" headerText="Project Start Date" width="10" dataFormat="MM/dd/yyyy" HAlign="left"/>
                                                                                                    <grd:textcolumn dataField="EndDate"  headerText="Project End Date"  dataFormat="MM/dd/yyyy" width="18"  HAlign="left"/>
                                                                                                    
                                                                                                </grd:dbgrid>
                                                                                                <!-- these components are DBGrid Specific  -->
                                                                                                <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                                                                <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                                                                <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                                                                                            </td>
                                                                                        </tr>
                                                                                    </table>
                                                                                </form>
                                                                                <%
                                                                                connection.close();
                                                                                connection = null;
                                                                                }catch(Exception ex){
                                                                                    out.println(ex.toString());
                                                                                }finally{
                                                                                    if(connection!= null){
                                                                                        connection.close();
                                                                                        connection = null;
                                                                                        
                                                                                    }
                                                                                }
                                                                                %>
                                                                            </td>
                                                                        </tr>
                                                                        
                                                                        
                                                                    </table>
                                                                    
                                                                    
                                                                </td>
                                                            </tr>
                                                            <!--2nd td in First table -->
                                                        </table>
                                                        <script type="text/JavaScript">
                                                    var cal9 = new CalendarTime(document.forms['projectReport'].elements['projectDate']);
                                                    cal9.year_scroll = true;
                                                    cal9.time_comp = false; 
                                                   
                                                        </script>
                                                    </div>
                                                </td>
                                            </tr>
                                            
                                      --%>      
                                            <tr>
                                                <td>
                                                    <div class="portletTitleBar">
                                                        <div class="portletTitleLeft">Birthday Reports</div>
                                                        <div class="portletIcons">
                                                            <a href="javascript:animatedcollapse.hide('birthdayReports')" title="Minimize">
                                                            <img src="../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                            <a href="javascript:animatedcollapse.show('birthdayReports')" title="Maximize">
                                                                <img src="../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                            </a>
                                                        </div>
                                                        <div class="clear"></div>
                                                    </div>
                                                    
                                                    <div id="birthdayReports" style="background-color:#ffffff">
                                                        
                                                        <s:form name="bDate" id="bDate" action="generateBdayreport" theme="simple" method="post" onsubmit="return validateDates2();">
                                                            <!-- for displaying action errors and field errors -->
                                                            <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                                <tr>
                                                                    <td class="headerText" align="right">
                                                                        <s:property value="#request.reportResult"/>
                                                                        <s:submit name="submit" value="Generate Report" cssClass="buttonBg"/>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        <table width="100%" cellpadding="2" cellspacing="2" border="1">
                                                                            
                                                                            
                                                                            
                                                                            
                                                                            <tr>
                                                                                <td align="right" class="fieldLabel" colspan="5">View Birthdays : 
                                                                                    <INPUT TYPE="radio" NAME="listby" value="1" onClick="display(this);"/>Today
                                                                                    <INPUT TYPE="radio" NAME="listby" value="2" onClick="display(this);" checked="true"/>This Weeek
                                                                                    <INPUT TYPE="radio" NAME="listby" value="3" onClick="display(this);"/>This Month
                                                                                    <INPUT TYPE="radio" NAME="listby" value="4" onClick="display(this);"/>Between Dates
                                                                        <%--        <s:radio id="listby" label="View Birthday" name="listby" list="#{'1':'Today','2':'This Weeek','3':'This Month','4':'Between Dates'}" onchange="display(this);" value="2" />
                                                                        --%>
                                                                        </td>
                                                                                
                                                                            </tr>
                                                                            
                                                                        </table>
                                                                        <div style="display:none;" id="birthreport">
                                                                            <table width="60%" cellpadding="2" cellspacing="2" border="0">
                                                                                <tr>
                                                                                    
                                                                                    <td align="right" class="fieldLabel">Start Date:</td>
                                                                                    <td><s:textfield id="startDate" cssClass="inputTextBlue"  name="startDate" onchange="checkDates(this);" required="true"/>
                                                                                        <a href="javascript:cal1.popup();">
                                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif"
                                                                                             width="20" height="20" border="0" ></a>
                                                                                    </td>
                                                                                    <td align="right" class="fieldLabel">End Date:</td>
                                                                                    <td><s:textfield id="endDate" cssClass="inputTextBlue" name="endDate" onchange="checkDates(this);" required="true"/>
                                                                                        <a href="javascript:cal2.popup();">
                                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif"
                                                                                             width="20" height="20" border="0" ></a>
                                                                                    </td>
                                                                                    
                                                                                </tr>
                                                                                
                                                                                
                                                                                
                                                                        </table>  </div>
                                                                        
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </s:form>
                                                        
                                                        
                                                        <script type="text/JavaScript">
                                         var cal1 = new CalendarTime(document.forms['bDate'].elements['startDate']);
	                                cal1.year_scroll = true;
	                                cal1.time_comp = false;
                                        var cal2 = new CalendarTime(document.forms['bDate'].elements['endDate']);
	                                cal2.year_scroll = true;
	                                cal2.time_comp = false;
                                                        </script>
                                                        
                                                    </div>
                                                    
                                                </td>
                                            </tr>
                                            
                                        </table>
                                        
                                        <%-- </sx:div> --%>
                                    </div>
                                    <%-- </sx:tabbedpanel> --%>
                                </div>
                                
                                <script type="text/javascript">

var countries=new ddtabcontent("reportTabs")
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







