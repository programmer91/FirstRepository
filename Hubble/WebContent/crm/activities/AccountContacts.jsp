<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%-- <%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<html>
    <head>
        <title>Hubble Organization Portal :: Activities Information</title>
        <sx:head cache="false"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/Contact-Activity.js"/>"></script>
        
        <s:include value="/includes/template/headerScript.html"/>
        <script type="text/JavaScript">
            function getCurrentDate() {
                //var temp = 0;
                var currentYear = new Date().getFullYear();    
                var currentMonth = new Date().getMonth() +1;    
                var currentDay = new Date().getDate();
                if(currentDay <10 ){
                    currentDay = '0'+ currentDay;
                }
                if(currentMonth <10 ){
                    currentMonth = '0'+ currentMonth;
                }
                // month-date-year
                var firstDayOfMonth = (currentMonth) + '/' + (currentDay) + '/' + currentYear;
                document.getElementById('dashBoardStartDate').value = firstDayOfMonth;
                document.getElementById('dashBoardEndDate').value = firstDayOfMonth;
               
            }
        </script>
    </head>
    <!--<body  class="bodyGeneral" oncontextmenu="return false;" onload="getCurrentDate();"> -->
    <!-- <body  class="bodyGeneral" oncontextmenu="return false;" onload="getCurrentDate();"> -->
    <body  class="bodyGeneral" oncontextmenu="return false;">
        
        <%!
        /* Declarations */
        
        String queryString=null;
        Connection connection;
        
        Statement stmt;
        ResultSet rs;
        int recordCount=0;
        String cname ;
        String ctitle;
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
                            <td width="850px" class="cellBorder" valign="top" style="padding-left:10px;padding-top:5px;">
                                <!--//START TABBED PANNEL : -->
                                <ul id="accountTabs" class="shadetabs" >
                                    <li ><a href="#" class="selected" rel="accountsListTab" >Accounts Contacts</a></li>
                                    
                                </ul>
                                <%--   <sx:tabbedpanel id="teamActivitiesPanel" cssStyle="width: 845px; height: 550px;padding-left:10px;padding-top:5px;" doLayout="true"> --%>
                                <div  style="border:1px solid gray; width:830px;height: 450px;overflow:auto; margin-bottom: 1em;">   
                                    <!--//START TAB : -->
                                    <%--    <sx:div id="teamActivitiesTab" label="Activities Summary" cssStyle="overflow:auto;"> --%>
                                    <div id="accountsListTab" class="tabcontent"   >
                                        
                                        <s:form action="%{formAction}" name="ActivitiesForm" theme="simple">   
                                            <div style="width:1300px;">
                                                <table cellpadding="0" cellspacing="0" border="0" width="100%" align="center">
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
                                                            <table>
                                                                <tr>                                                    
                                                                    <td class="fieldLabel">StartDate:</td> <!--value="%{dateWithOutTime}" -->
                                                                    <s:if test="dashBoardStartDate == null">
                                                                        <td><s:textfield name="dashBoardStartDate" id="dashBoardStartDate" cssClass="inputTextBlueSmall" value="%{}"/><a href="javascript:cal1.popup();">
                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                             width="20" height="20" border="0"> </td>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <td><s:textfield name="dashBoardStartDate" id="dashBoardStartDate1" cssClass="inputTextBlueSmall" value="%{dashBoardStartDate}"/><a href="javascript:cal1.popup();">
                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                             width="20" height="20" border="0"> </td>
                                                                    </s:else>
                                                                    <td class="fieldLabel">EndDate</td>
                                                                    <s:if test="dashBoardEndDate == null">
                                                                        <td><s:textfield name="dashBoardEndDate" id="dashBoardEndDate" cssClass="inputTextBlueSmall" value="%{}"/><a href="javascript:cal2.popup();">
                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                             width="20" height="20" border="0"> </td>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <td><s:textfield name="dashBoardEndDate" id="dashBoardEndDate1" cssClass="inputTextBlueSmall" value="%{dashBoardEndDate}"/><a href="javascript:cal2.popup();">
                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                             width="20" height="20" border="0"></td>
                                                                    </s:else>
                                                                    
                                                                    <s:if test="viewMemberSearch == 'view'">
                                                                        <td class="fieldLabel">LoginId</td>
                                                                        <td><%--<s:textfield name="empId" cssClass="inputTextBlueSmall"/></td>--%>
                                                                            <s:select list="salesTeamExceptAccountTeamMap" name="empId" id="empId" headerKey="" headerValue="--Select Team--" cssClass="inputSelect" />
                                                                        </td>
                                                                        <td class="fieldLabel">Status:</td>
                                                                        <td><%--<s:textfield name="empId" cssClass="inputTextBlueSmall"/></td>--%>
                                                                            <s:select list="contactStatusList" name="contactStatus" id="contactStatus" value="%{contactStatus}" cssClass="inputSelect" />
                                                                        </td>
                                                                    </s:if>
                                                                    
                                                                    <td colspan="4" align="center"> <s:submit cssClass="buttonBg" value="Search"/></td>
                                                                </tr>
                                                            </table>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td><br>
                                                            <table cellpadding="2" cellspacing="1" width="97%" border="0" class="gridTable">
                                                                
                                                                <% 
                                                                try{
                                                                   // queryString = request.getAttribute(ApplicationConstants.QS_TEAM_ACTIVITIES_LIST).toString();
                                                                 
                                                                    queryString = request.getAttribute(ApplicationConstants.QUERY_STRING).toString();
                                                                       System.out.println("jsp "+queryString);
                                                                    connection = ConnectionProvider.getInstance().getConnection();
                                                                    
                                                                    stmt = connection.createStatement();
                                                                    
                                                                    rs  =  stmt.executeQuery(queryString);
                                                                     //out.print(queryString);
                                                                    recordCount = 0;
                                                                %>
                                                                <tr class="gridHeader">
                                                                 
                                                                    <td width="2%" class="gridHeader" ALIGN="left">Account Name</td>
                                                                    <td width="2%" class="gridHeader" ALIGN="left">Name</td>
                                                                    <td width="2%" class="gridHeader" ALIGN="left">Title</td>
                                                                    <td width="1%" class="gridHeader" ALIGN="left">Office Phone</td>
                                                                    <td width="1%" class="gridHeader" ALIGN="left">Email</td>
                                                                    <td width="1%" class="gridHeader" ALIGN="left">Created Date</td>
                                                                    <td width="1%" class="gridHeader" ALIGN="left">Description</td>
                                                                  
                                                                    
                                                                </tr>
                                                                <% while(rs.next()){
                                                                        
                                                                        //Countting Total records
                                                                        ++recordCount;
                                                                
                                                                %>
                                                                <tr class="gridRowEven">
                                                                    <td class="gridColumn" align="left"><a href="../accounts/getAccount.action?id=<%=rs.getInt("AccountId") %>&activitySummaryFlag=3&dashBoardStartDate=<s:property value="dashBoardStartDate"/>&dashBoardEndDate=<s:property value="dashBoardEndDate"/>&empId=<s:property value="empId"/>&contactStatus=<s:property value="contactStatus"/>"><%=rs.getString("AccountName")%></a></td>
                                                                    <td class="gridColumn" align="left"><%=rs.getString("FirstName")%> <%=rs.getString("LastName")%></td>
                                                                    <td class="gridColumn" align="left"><%=rs.getString("Title")%></td>
                                                                    <td class="gridColumn" align="left"><%=rs.getString("OfficePhone")%></td>
                                                                    <td class="gridColumn" align="left"><%=rs.getString("Email1")%></td>
                                                                    <td class="gridColumn" align="left"><%=rs.getString("createdDate")%></td>
                                                                    <td class="gridColumn" align="left"><a href="#" onclick="accountContactsDescription('<%=rs.getInt("AccountId") %>');">Click to View</a></td>
                                                                  
                                                                    
                                                                </tr>
                                                                <!--Counting total Activities -->
                                                                <%}  
                                                                
                                                                }catch(Exception e) {
                                                                System.out.println("Team Activity:-"+e);
                                                                
                                                                }finally{
                                                                try{
                                                                if(connection!=null){
                                                                connection.close();
                                                                connection = null;
                                                                }
                                                                }catch(SQLException se){
                                                                System.err.print("Error in Activity Info :"+se);
                                                                }
                                                                }
                                                                %>
                                                                <tr class="gridPager">
                                                                    <td align="right" colspan="8" class="gridFooter" >Total&nbsp;<%=recordCount%></td>
                                                                </tr>
                                                            </table>    
                                                        </td>
                                                    </tr>
                                                    
                                                </table>
                                            </div>
                                        </s:form>
                                        <script type="text/JavaScript">
                                            var cal1 = new CalendarTime(document.forms['ActivitiesForm'].elements['dashBoardStartDate']);
				                 cal1.year_scroll = true;
				                 cal1.time_comp = false;
                                                 
                                                 
                                            var cal2 = new CalendarTime(document.forms['ActivitiesForm'].elements['dashBoardEndDate']);
				                 cal2.year_scroll = true;
				                 cal2.time_comp = false;
                                            
                                        </script>
                                        
                                        <%--    </sx:div > --%>
                                        <!--//END TAB : -->
        
                                        <%--  </sx:tabbedpanel> --%>
                                    </div>
                                </diV>
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
        </table>
        <!--//END MAIN TABLE : Table for template Structure-->
        <script type="text/javascript">
		$(window).load(function(){
	getCurrentDate();
		});
		</script>
    </body>
</html>

