<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%-- <%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<%@ page import="com.freeware.gridtag.*" %> 
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ page import="com.mss.mirage.util.DateUtility"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd" %>
<%--<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>--%>


<html>
    <head>
        
        <title>Hubble Organization Portal :: Calendar</title>
        <sx:head cache="false"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <%--<link rel="stylesheet" href="<s:url value="/includes/css/displaytag.css"/>">--%>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        
        <s:include value="/includes/template/headerScript.html"/>
        
    </head>
    <body class="bodyGeneral" oncontextmenu="return false;">
        
        <%!
        /* Declarations */
        Connection connection;
        String queryString;
        String currentAccountId;
        String strTmp;
        String userId;
        String strSortCol;
        String strSortOrd = "ASC";
        String submittedFrom;
        boolean blnSortAsc = true;
        String viewType;
        //int intSortOrd = 0;
        int intCurr = 1;
        
        %>
        
        <!--//START MAIN TABLE : Table for template Structure-->
        <table class="templateTable" align="center" cellpadding="0" cellspacing="0">
            
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
                    <table class="innerTable" cellpadding="0" cellspacing="0">
                        <tr>
                            
                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                            <td width="150px;" class="leftMenuBgColor" valign="top">
                                <s:include value="/includes/template/LeftMenu.jsp"/>
                                
                            </td>
                            <!--//END DATA COLUMN : Coloumn for LeftMenu-->
                            
                            <!--//START DATA COLUMN : Coloumn for Screen Content-->
                            <td width="850px" class="cellBorder" valign="top" style="padding-left:10px;padding-top:5px;">
                                
                               <ul id="accountTabs" class="shadetabs" >
                                    <li ><a href="#" class="selected" rel="activityTab" >Activity List </a></li>
                                    <li ><a href="#" rel="activitySearchTab">Activity Search</a></li>
                                </ul>
                                  <div  style="border:1px solid gray; width:840px;height: 600px; overflow:auto; margin-bottom: 1em;">
                                <!--//START TABBED PANNEL : --> 
                               <%--  <sx:tabbedpanel id="activitiesList" cssStyle="width: 840px; height: 600px;padding-left:10px;padding-top:5px;" doLayout="true"> --%>
                                    
                                    <!--//START TAB : -->
                                  <%--   <sx:div id="activityTab" label="Activity List" cssStyle="overflow:auto;"> --%>
                                      <div id="activityTab" class="tabcontent"   > 
                                          <%
                                          
                                          
                                          
                                          strTmp = request.getParameter("txtCurr");
                                          if (strTmp != null){
                                          
                                          try {
                                          intCurr = Integer.parseInt(strTmp);
                                          } catch (NumberFormatException NFEx) {
                                          NFEx.printStackTrace();
                                          }
                                          }else{
                                          intCurr = 1;
                                          }
                                          
                                          strSortCol = "accountName";
                                          
                                          try{
                                          
                                          /* Getting DataSource using Service Locator */
                                          
                                          connection = ConnectionProvider.getInstance().getConnection();
                                          
                                          /* Sql query for retrieving resultset from DataBase */
                                          queryString  = null;
                                          viewType = null;
                                          submittedFrom = null;
                                          userId = session.getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                                          
                                          if(request.getAttribute("submitFrom") != null){
                                          submittedFrom = request.getAttribute("submitFrom").toString();
                                          }
                                          if(submittedFrom==null){
                                          queryString="Select activityId,accountName," +
                                          "fName,lName,dueDate,officePhone,description" +
                                          "  FROM vwCalendarActivitiesList " +
                                          "WHERE AssignedToId = '"+userId+"' AND dueDate=(SELECT CURRENT_DATE())" ;
                                          }else{
                                          if(session.getAttribute(ApplicationConstants.QS_ACTIVITY_LIST)!=null){
                                          queryString = session.getAttribute(ApplicationConstants.QS_ACTIVITY_LIST).toString();
                                          }
                                          }
                                          // out.println(queryString);
                                          %>
                                          <table cellpadding="0" cellspacing="0" border="0"width="100%">
                                              <tr align="right">
                                                  <td class="headerText">
                                                      <img alt="Home" 
                                                           src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" 
                                                           width="100%" 
                                                           height="13px" 
                                                           border="0">
                                                  </td>
                                              </tr>   
                                              
                                              <!---BEGIN:: DBGrid Specific ---->  
                                              <tr>
                                                  <td>
                                                      
                                                      <%-- <display:table name="accountsData" 
                                                                       class="data" 
                                                                       pagesize="14" 
                                                                       decorator="com.mss.mirage.crm.accounts.AccountWrapper"
                                                                       requestURI="/crm/accounts/accountsList.action">
                                                            
                                                            <display:column property="nameLink" maxLength="15" title="AccountName"/>
                                                            <display:column property="status"/>
                                                            <display:column property="industry" maxLength="20"/>
                                                            <display:column property="url"/>
                                                            <display:column property="accountTeamName" title="AccountTeam"/>
                                                            <display:column property="region"/>
                                                            <display:column property="territory"/>
                                                            <display:column property="dateLastActivity"/>
                                                            
                                                        </display:table>
                                                        --%>
                                                        
                                                        
                                                      <div style="width=800px;">
                                                          
                                                          <form action="" method="post" name="frmDBGrid">  
                                                              <grd:dbgrid id="tblActivityList" name="tblActivityList" width="100" pageSize="30" 
                                                                          currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                          dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                  
                                                                  <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                                 imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"/>
                                                                  
                                                                  <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" 
                                                                                  imageAscending="../../includes/images/DBGrid/ImgAsc.gif" 
                                                                                  imageDescending="../../includes/images/DBGrid/ImgDesc.gif"/>    
                                                                  <grd:imagecolumn headerText="Edit" width="3" HAlign="center" imageSrc="../../includes/images/DBGrid/newEdit_17x18.png"
                                                                                   linkUrl="getActivity.action?id={activityId}" imageBorder="0"
                                                                                   imageWidth="16" imageHeight="16" alterText="Click to edit"></grd:imagecolumn>
                                                                  <grd:textcolumn dataField="accountName"     headerText="Account Name" width="20" sortable="true"/>
                                                                  <grd:textcolumn dataField="fName"          headerText="FName" width="6"/>
                                                                  <grd:textcolumn dataField="lName"	headerText="LName" width="6"/>
                                                                  <grd:datecolumn dataField="dueDate" headerText="DueDate" dataFormat="MM-dd-yyyy" width="8"/>
                                                                  <grd:numbercolumn dataField="officePhone"	headerText="OffPhno" width="6"/>
                                                                  <grd:textcolumn dataField="description"        headerText="Description" width="10"/>
                                                              </grd:dbgrid>
                                                              
                                                              <input type="hidden" name="txtCurr" value="<%=intCurr%>">
                                                              <input type="hidden" id="txtSortCol" name="txtSortCol" value="<%=strSortCol%>">
                                                              <input type="hidden" id="txtSortAsc" name="txtSortAsc" value="<%=strSortOrd%>">
                                                              <input type="hidden" name="submitFrom" value="dbGrid">
                                                              
                                                          </form>
                                                      </div>
                                                  </td>
                                              </tr>
                                          </table>                                
                                          
                                          
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
                                          <%--   </sx:div> --%>
                                          <!--//END TAB : -->
                                      </div>
                                      <%--      <sx:div id="activitySearchTab" label="Activity Search"> --%>
                                      <div id="activitySearchTab" class="tabcontent"   > 
                                          
                                          
                                          <s:form name="frmSearch" action="activitySearch" theme="simple">
                                              
                                              <table border="0" cellpadding="1" cellspacing="1" width="100%">
                                                  <tr>
                                                      <td class="headerText" colspan="4" align="right">
                                                          <s:hidden name="submitFrom" value="SearchAll"/>
                                                          <s:hidden name="viewType" value="%{viewType}"/>
                                                          <s:submit cssClass="buttonBg" value="Search"/>
                                                      </td>
                                                  </tr>
                                                  <tr>                                                    
                                                      <td class="fieldLabel">StartDate:</td>
                                                      <td><s:textfield name="startDate" cssClass="inputTextBlue" value="%{dateWithOutTime}"/><a href="javascript:cal1.popup();">
                                                          <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                           width="20" height="20" border="0"></td>
                                                      <td class="fieldLabel">EndDate</td>
                                                      <td><s:textfield name="endDate" cssClass="inputTextBlue" value="%{dateWithOutTime}"/><a href="javascript:cal2.popup();">
                                                          <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                           width="20" height="20" border="0"></td>
                                                      
                                                  </tr>
                                              </table>
                                              
                                          </s:form>
                                          <script type="text/JavaScript">
                                                var cal1 = new CalendarTime(document.forms['frmSearch'].elements['startDate']);
				                cal1.year_scroll = true;
				                cal1.time_comp = false;
                                                var cal2 = new CalendarTime(document.forms['frmSearch'].elements['endDate']);
				                cal2.year_scroll = true;
				                cal2.time_comp = false;
                                            
                                          </script>
                                          <%-- </sx:div> --%>
                                      </div>
                                      <!--//END TAB : -->
                                   
                                      <%--  </sx:tabbedpanel> --%>
                                      <!--//END TABBED PANNEL : -->
                                  </div>
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