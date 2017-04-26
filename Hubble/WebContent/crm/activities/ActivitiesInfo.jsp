<%@page import="java.util.regex.Pattern"%>
<%@page import="com.mss.mirage.util.DataSourceDataProvider"%>
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
            function showCommentsPopUp(text) {
            var background = "#3E93D4";
            var title = "Comments";
            var text1 = ""; 
            if(text == ""){
                text1 = "No Comments Given";
            }
            else{
                text1 = text; 
            }
            var size = text1.length;
    
            //Now create the HTML code that is required to make the popup
            var content = "<html><head><title>"+title+"</title></head>\
<body bgcolor='"+background +"' style='color:white;'><h4>"+title+"</h4>"+text1+"<br />\
</body></html>";
    
            if(size < 50){
                //Create the popup       
                popup = window.open("","window","channelmode=0,width=300,height=150,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
                popup.document.write(content); //Write content into it.    
            }
    
            else if(size < 100){
                //Create the popup       
                popup = window.open("","window","channelmode=0,width=400,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
                popup.document.write(content); //Write content into it.    
            }
    
            else if(size < 260){
                //Create the popup       
                popup = window.open("","window","channelmode=0,width=400,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
                popup.document.write(content); //Write content into it.    
            }
    
        }
        </script>
    </head>
   <%-- <body  class="bodyGeneral" oncontextmenu="return false;" onload="getCurrentDate();"> --%>
    <body  class="bodyGeneral" oncontextmenu="return false;">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/wz_tooltip.js"/>"></script>
        
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
                                    <li ><a href="#" class="selected" rel="accountsListTab" >Activities Summary</a></li>
                                    
                                </ul>
                                <%--   <sx:tabbedpanel id="teamActivitiesPanel" cssStyle="width: 845px; height: 550px;padding-left:10px;padding-top:5px;" doLayout="true"> --%>
                                <div  style="border:1px solid gray; width:830px;height: 450px;overflow:auto; margin-bottom: 1em;">   
                                    <!--//START TAB : -->
                                    <%--    <sx:div id="teamActivitiesTab" label="Activities Summary" cssStyle="overflow:auto;"> --%>
                                    <div id="accountsListTab" class="tabcontent"   >
                                        
                                        <s:form action="%{formAction}" name="ActivitiesForm" theme="simple">   
                                            <div style="width=1300px;">
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
                                                                        <td class="fieldLabel">Activity Type</td>
                                                                        <td><%--<s:textfield name="empId" cssClass="inputTextBlueSmall"/></td>--%>
                                                                            <s:select list="activityTypeList" name="activityType" id="activityType" headerKey="" headerValue="--Select Team--" cssClass="inputSelect" />
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
                                                                    queryString = request.getAttribute(ApplicationConstants.QS_TEAM_ACTIVITIES_LIST).toString();
                                                                    
                                                                    connection = ConnectionProvider.getInstance().getConnection();
                                                                    
                                                                    stmt = connection.createStatement();
                                                                    
                                                                    rs  =  stmt.executeQuery(queryString);
                                                                   //  out.println(queryString);
                                                                    recordCount = 0;
                                                                %>
                                                               <tr class="gridHeader">
                                                                            <td width="5%" class="gridHeader" ALIGN="left">Account Name</td>
                                                                            <td width="5%" class="gridHeader" ALIGN="left">Activity Type</td>
                                                                            <td width="5%" class="gridHeader" ALIGN="left">Comments</td>
                                                                            <td width="5%" class="gridHeader" ALIGN="left">Contacts</td>
                                                                            <td width="5%" class="gridHeader" ALIGN="left">Status</td>
                                                                            <!--<td width="10%" class="gridHeader" ALIGN="left">DueDate</td>
                                                                            <td width="10%" class="gridHeader" ALIGN="left">Contact Name</td>
                                                                            <td width="10%" class="gridHeader" ALIGN="left">Contact Title</td>-->
                                                                            <td width="5%" class="gridHeader" ALIGN="left">Created By</td>
                                                                            <td width="5%" class="gridHeader" ALIGN="left">Created Date</td>
                                                                            <td width="5%" class="gridHeader" ALIGN="left">Opportunity</td>

                                                                            </tr>
                                                                <% while (rs.next()) {
                                                                                     String contactNames = "";
                                                                                    if (rs.getString("ContactId") == null || "".equals(rs.getString("ContactId").trim())) {
                                                                                        contactNames = "-";
                                                                                    } else {
                                                                                        contactNames = DataSourceDataProvider.getInstance().getContactsNames(rs.getString("ContactId"));
                                                                                    }
                                                                                     String firstcontact=contactNames.split(Pattern.quote(","))[0];
                                                                                     String opportunity = "";
                if (rs.getInt("OpportunityId") == 0 || rs.getInt("OpportunityId") == -1 || rs.getInt("OpportunityId") == -2) {
                    opportunity = "No Opportunity";
                } else {
                    opportunity = DataSourceDataProvider.getInstance().getOpportunityTitle(rs.getInt("OpportunityId"));
                }
                                                                                    //Countting Total records
                                                                                    ++recordCount;

                                                                            %>
                                                                 <tr class="gridRowEven">
                                                                                <%--  <td class="gridColumn" align="left"><a href="../accounts/getAccount.action?id=<%=rs.getInt("Id") %>"><%=rs.getString("Name")%></a></td> --%>
                                                                            <td class="gridColumn" align="left"><a href="../accounts/getAccount.action?id=<%=rs.getInt("Id")%>&activitySummaryFlag=<s:property value="activitySummaryFlag"/>&dashBoardStartDate=<s:property value="dashBoardStartDate"/>&dashBoardEndDate=<s:property value="dashBoardEndDate"/>&empId=<s:property value="empId"/>&activityType=<s:property value="activityType"/>"><%=rs.getString("Name")%></a></td>
                                                                            <%--<td class="gridColumn" align="left"><%=rs.getString("Id")%></td>--%>
                                                                            <td class="gridColumn" align="left"><a href="getActivity.action?id=<%=rs.getInt("ActivityId")%>"><%=rs.getString("ActivityType")%></a></td>
                                                                            <td class="gridColumn" align="left"><a href="javascript:showCommentsPopUp('<%=rs.getString("Comments")%>')">Click to view</a></td>
                                                                            <td class="gridColumn" align="left"><a href="" onmouseover="javascript:Tip('<%=contactNames%>');" onmouseout="javascript:UnTip();" style="text-decoration: none;color: #000;"><%=firstcontact%>..</a></td>
                                                                            <td class="gridColumn" align="left"><%=rs.getString("STATUS")%></td>
                                                                           <td class="gridColumn" align="left"><%=rs.getString("CreatedById")%></td>
                                                                            <td class="gridColumn" align="left"><%=rs.getString("CreatedDate")%></td>
                                                                            <td class="gridColumn" align="left"><%=opportunity%></td>

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
    </body>
</html>
