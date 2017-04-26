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
        <title>Hubble Organization Portal :: Employee Authored Exams List</title>
        
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tooltip.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tooltip.js"/>"></script>   
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>   
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>   
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/cre/CreAjax.js"/>"></script>
        <s:include value="/includes/template/headerScript.html"/>
    </head>
   <!-- <body class="bodyGeneral"  oncontextmenu="return false;" onload="setCreDefaultDatesForReport();"> -->
    <body class="bodyGeneral"  oncontextmenu="return false;">
        <%!
        /* Declarations */
        Connection connection;
        String queryString;
        String strTmp;
        String strSortCol;
        String strSortOrd;
        String action;
        int role;
        int intSortOrd = 0;
        int intCurr;
        String currentSearch=null;
        boolean blnSortAsc = true;
        HttpServletRequest httpServletRequest;
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
                                       
                                        <li ><a href="#" class="selected" rel="creConsultantList"  >Cre Consultant List </a></li>
                                         <li ><a href="#" rel="creConsultantSearchTab">Cre Consultant Search</a></li>
                                          <li ><a href="#" rel="creConsultantReportsTab">Consultant&nbsp;Reports</a></li>
                                 <%--   <%}else{%>
                                    <li ><a href="#" class="selected" rel="IssuesSearchTab">Tasks Search</a></li>
                                     <% }%> --%>
                                    
                                </ul>
                                
                                <%-- <sx:tabbedpanel id="IssuesPanel" cssStyle="width: 845px; height: 500px;padding:5px 5px;" doLayout="true"> --%>
                                <div  style="border:1px solid gray; width:845px;height: 600px; overflow:auto; margin-bottom: 1em;">
                                    <!--//START TAB : -->
                                   
                                   <%--  <% if(request.getParameter("issueList")==null)
                                            {
                                              //  System.out.println("list");
                                            %> --%>
                                    <div id="creConsultantList" class="tabcontent"  >
                                        
                                        <%
                                        
                                        try{
                                            
                                              int intCurr = 1;
                                                    int intSortOrd = 0;
                                                    String strTmp = null;
                                                    StringBuffer   strSQL= null;
                                                    String strSortCol = null;
                                                    String strSortOrd = "ASC";
                                                    boolean blnSortAsc = true;
                                            queryString="";
                                            /* Getting DataSource using Service Locator */
                                            connection = ConnectionProvider.getInstance().getConnection();
                                            
                                           // System.out.println("list1");
                                            /* String Variable for storing current position of records in dbgrid*/
                                            strTmp = request.getParameter("txtCurr");
                                            intCurr = 1;
                                            if (strTmp != null)
                                                intCurr = Integer.parseInt(strTmp);
                                            
                                            /* Specifing Shorting Column */
                                            strSortCol = request.getParameter("txtSortCol");
                                            
                                            if (strSortCol == null) strSortCol = "ConsultentId";
                                            
                                            strSortOrd = request.getParameter("txtSortAsc");
                                            if (strSortOrd == null) strSortOrd = "DESC";
                                            blnSortAsc = (strSortOrd.equals("ASC"));
                                           
                                            String userId = session.getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                                           // out.println(userId); 
                                            
                                            // out.println(name);
                                            //out.println(ApplicationConstants.QS_EXAM_LIST);
                                           
                                            queryString = session.getAttribute(ApplicationConstants.QUERY_STRING).toString();
                                           
                                            /* Sql query for retrieving resultset from DataBase */
                                          //  out.println(queryString);
                                           
                                        if(request.getAttribute("resultMessage")!=null) {
                                                out.println(request.getAttribute("resultMessage").toString());
                                        }
                                        %>
                                        <s:form action="" name="frmDBGrid" theme="simple"> 
                                            <div style="width:840px;"> 
                                                <table cellpadding="0" border="0" cellspacing="0" width="100%">
                                                    <tr>
                                                       <td class="headerText">
                                                            <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                        </td> 
                                                    </tr> 
                                                            <%  
                                                            Map rolesMap =  (Map)session.getAttribute(ApplicationConstants.SESSION_MY_ROLES);
                                                            %>
                                                    <tr>
                                                        <td>
                                                              <%if(rolesMap.containsKey("7")) {%>
                                                                <grd:dbgrid id="tblCreConsultentDetails" name="tblCreConsultentDetails" width="100" pageSize="18" 
                                                                            currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                            dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                    <grd:gridpager imgFirst="../includes/images/DBGrid/First.gif" imgPrevious="../includes/images/DBGrid/Previous.gif" 
                                                                                   imgNext="../includes/images/DBGrid/Next.gif" imgLast="../includes/images/DBGrid/Last.gif" />
                                                                     <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>"  imageAscending="../includes/images/DBGrid/ImgAsc.gif" imageDescending="../includes/images/DBGrid/ImgDesc.gif" />
                                                                    <grd:rownumcolumn headerText="SNo" width="2" HAlign="center"/>  
                                                                    <grd:textcolumn dataField="ConsultentId"  headerText="ConsultantId"   width="10" sortable="true"/> 
                                                                    <grd:anchorcolumn dataField="ConsultentName" 
                                                                                      headerText="Consultant Name" 
                                                                                      linkUrl="getConsultantEdit.action?id={Id}" linkText="{ConsultentName}" width="20" sortable="true"/>
                                                                    <grd:textcolumn dataField="STATUS"  headerText="Status"   width="5" sortable="true"/> 
                                                                    <grd:textcolumn dataField="Level"  headerText="Level"   width="10" sortable="true"/>
                                                                    <grd:anchorcolumn dataField="ConsultentName" 
                                                                                      headerText="Comments" 
                                                                                      linkUrl="javascript:getCreConsultantComments('{Id}','{Level}')" linkText="Click To View" width="8"/>
                                                                    
                                                                    <grd:imagecolumn  headerText="Reviews" width="5"  HAlign="center"  
                                                                          imageSrc="../includes/images/go_21x21.gif" linkUrl="reviewCreConsultant.action?id={Id}"
                                                                          imageBorder="0" imageWidth="18" imageHeight="15" alterText="Click Add/View Review" />
                                                                    <grd:imagecolumn  headerText="Make Employee" width="5"  HAlign="center"  
                                                                          imageSrc="../includes/images/go_21x21.gif" linkUrl="javascript:doAddAsEmployee({Id},'{STATUS}','{Level}')"
                                                                          imageBorder="0" imageWidth="18" imageHeight="15" alterText="Make" />
                                                                    
                                                                <grd:imagecolumn  headerText="Onboard" width="5"  HAlign="center"  
                                                                          imageSrc="../includes/images/download_11x10.jpg" linkUrl="javascript:downloadOnboardForm({Id},'{Level}')"
                                                                          imageBorder="0" imageWidth="12" imageHeight="12" alterText="Print OnboardForm" />
                                                                    </grd:dbgrid>
																	
                                                                    
                                                            <%} else{%>
                                                            <grd:dbgrid id="tblCreConsultentDetails" name="tblCreConsultentDetails" width="100" pageSize="20" 
                                                                            currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                            dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                    <grd:gridpager imgFirst="../includes/images/DBGrid/First.gif" imgPrevious="../includes/images/DBGrid/Previous.gif" 
                                                                                   imgNext="../includes/images/DBGrid/Next.gif" imgLast="../includes/images/DBGrid/Last.gif" />
                                                                    <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>"  imageAscending="../includes/images/DBGrid/ImgAsc.gif" imageDescending="../includes/images/DBGrid/ImgDesc.gif" />
                                                                    <grd:rownumcolumn headerText="SNo" width="2" HAlign="center"/>  
                                                                    <grd:textcolumn dataField="ConsultentId"  headerText="ConsultantId"   width="10" sortable="true"/> 
                                                                    <grd:anchorcolumn dataField="ConsultentName" 
                                                                                      headerText="Consultant Name" 
                                                                                      linkUrl="getConsultantEdit.action?id={Id}" linkText="{ConsultentName}" width="20"/>
                                                                    <grd:textcolumn dataField="STATUS"  headerText="Status"   width="5" sortable="true"/> 
                                                                    <grd:textcolumn dataField="Level"  headerText="Level"   width="10" sortable="true"/>
                                                                    <grd:anchorcolumn dataField="ConsultentName" 
                                                                                      headerText="Comments" 
                                                                                      linkUrl="javascript:getCreConsultantComments('{Id}','{Level}')" linkText="Click To View" width="8"/>
                                                                    
                                                                    <grd:imagecolumn  headerText="Reviews" width="5"  HAlign="center"  
                                                                          imageSrc="../includes/images/go_21x21.gif" linkUrl="reviewCreConsultant.action?id={Id}"
                                                                          imageBorder="0" imageWidth="18" imageHeight="15" alterText="Click Add/View Review" /> 
                                                                    </grd:dbgrid>  
                                                                
                                                            <%}%>
                                                            <!-- these components are DBGrid Specific  -->
                                                            <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                            <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                            <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                                                            
                                                            <s:hidden name="consultantId" value="%{consultantId}"/>
                                                            <s:hidden name="consultantName" value="%{consultantName}"/>
                                                            <s:hidden name="status" value="%{status}"/>
                                                            <s:hidden name="level" value="%{level}"/>
                                                            <s:hidden name="interviewAt" value="%{interviewAt}"/>
                                                            <s:hidden name="recLocation" value="%{recLocation}"/>
                                                            <s:hidden name="email" value="%{email}"/>
                                                            
                                                        </td>
                                                    </tr>
                                                </table>    
                                            </div>
                                        </s:form>  
                                        <%
                                       // connection.close();
                                       // connection = null;
                                        /* For Exception handling mechanism */
                                        }catch(Exception se){
                                            out.println(se.toString());
                                            System.out.println("Exception in ExamList "+se);
                                        }finally{
                                            if(connection!= null){
                                                connection.close();
                                                connection = null;
                                            }
                                            if(session!=null){
                                                session.removeAttribute("resultMessage");
                                            }
                                        }
                                        %>
                                        <%--  </sx:div > --%>
                                    </div>
                                 <%--   <%}%> --%>
                                    <!--//END TAB : -->
                                  
                                    <%--  <sx:div id="IssuesSearchTab" label="Issues Search" > --%>
                                    <div id="creConsultantSearchTab" class="tabcontent"  >   
                                        <s:form name="frmSearch" action="searchCreConsultants" theme="simple" method="post" >
                                           <!--The components of searching issues -->
                                            <table border="0" cellpadding="3" cellspacing="3" width="100%">
                                           
                                                  <tr>
                                                   
                                                </tr>
                                                <tr>
                                                    <td align="center"> 
                                                        <table cellpadding="1" cellspacing="1" border="0">
                                                       <tr>
                                                       
                                                        <td class="fieldLabel" width="20%">Consultant Id</td>
                                                        <td><s:textfield name="consultantId" id="consultantId"  cssClass="inputTextBlue"  value="%{consultantId}"  theme="simple"/></td>
                                                         <td class="fieldLabel" width="30%">Consultant Name</td> 
                                                        <td ><s:textfield name="consultantName" id="consultantName"  cssClass="inputTextBlue"  value="%{consultantName}"  theme="simple"/></td>
                                                        </tr> 
                                                          <tr>
                                                         <td class="fieldLabel">Level</td> 
                                                        <td >
                                                            <%--<s:textfield name="level" id="level"  cssClass="inputTextBlue"  value="%{level}"  theme="simple"/> --%>
                                                            <s:select headerKey="" headerValue="--Please Select--" list="{'Registration','Exam','Tech Level','HR level','VP level'}" name="level" id="level" cssClass="inputSelect"  value="%{level}"  theme="simple"></s:select>
                                                        </td>
                                                        <td class="fieldLabel">Status</td>
                                                        <td >
                                                            <%--<s:textfield name="status" id="status"  cssClass="inputTextBlue"  value="%{status}"  theme="simple"/> --%>
                                                            <s:select headerKey="" headerValue="--Please Select--" list="{'Registered','Active','Process','FAIL','PASS','Hold','Selected','Rejected'}" name="status" id="status" cssClass="inputSelect"  value="%{status}"  theme="simple">
                                                            </s:select>
                                                        </td>
                                                       
                                                        </tr> 
                                                         <tr>
                                                       
                                                        <td class="fieldLabel">Attending&nbsp;Interview&nbsp;at</td>
                                                        <td >
                                                            <%--<s:textfield name="status" id="status"  cssClass="inputTextBlue"  value="%{status}"  theme="simple"/> --%>
                                                            <s:select headerKey="" headerValue="--Please Select--" list="#@java.util.LinkedHashMap@{'1':'MiracleCampus','2':'CampusRecruitment','3':'JobFair'}" name="interviewAt" id="interviewAt" cssClass="inputSelect"  value="%{interviewAt}"  theme="simple">
                                                            </s:select>
                                                        </td>
                                                        <td class="fieldLabel">College&nbsp;Names&nbsp;</td>
                                                        <td>
                                                        <s:select name="recLocation" id="recLocation" cssClass="inputSelect" headerKey="" headerValue="--Please Select--" list="recLocationList" theme="simple" onchange="fieldLengthValidator(this);"/>
                                                        </td>
                                                         </tr>
                                                         
<tr><td class="fieldLabel" width="30%">Email&nbsp;Id&nbsp;</td>
       <td > <s:textfield name="email" cssClass="inputTextBlue" id="email" theme="simple"/></td>
                                                         <td  align="right">
                                                        <s:submit cssClass="buttonBg" value="Search" />
                                                    </td>
                                                        </tr>
                                                       

                                                        
                                                        </table>  
                                                    </td>
                                                </tr> 
                                                
                                            </table>
                                            
                                        </s:form>
                                      
                                    </div>
                                    
                                    <!-- Consultant Reports tab Start -->
                                    <div id="creConsultantReportsTab" class="tabcontent"  >   
                                        <s:form name="frmReportSearch" action="generateCreConsultantsReports" theme="simple" method="post" onsubmit="return checkSearchFields();">
                                           <!--The components of searching issues -->
                                            <table border="0" cellpadding="3" cellspacing="3" width="100%">
                                           
                                                  <tr>
                                                   
                                                </tr>
                                                <tr>
                                                    <td align="center"> 
                                                        <table cellpadding="1" cellspacing="1" border="0">
                                                           <tr>
                                                                <td class="fieldLabel">Start&nbsp;Date&nbsp;: </td>
                                                        <td > <s:textfield name="startDate" cssClass="inputTextBlueSmall" id="startDate" readonly="true"/>
                                                            <a href="javascript:cal1.popup();">
                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                             width="20" height="20" border="0"></a></td>
                                                             
                                                             <td class="fieldLabel">To&nbsp;Date&nbsp;: </td>
                                                        <td > <s:textfield name="endDate" cssClass="inputTextBlueSmall" id="endDate" readonly="true"/>
                                                            <a href="javascript:cal.popup();">
                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                             width="20" height="20" border="0"></a></td>
                                                             
                                                           
                                                        </tr>
                                                            
                                                          <tr>
                                                         <td class="fieldLabel">Exam&nbsp;Type:</td> 
                                                        
                                                            <%--<s:textfield name="level" id="level"  cssClass="inputTextBlue"  value="%{level}"  theme="simple"/> --%>
                                                            <td><s:select headerKey="" headerValue="--Please Select--" name="examType" id="examType" cssClass="inputSelect" list="examTypeMap"></s:select>
                                                        </td>
                                                        <td class="fieldLabel">Status</td>
                                                        <td >
                                                            <%--<s:textfield name="status" id="status"  cssClass="inputTextBlue"  value="%{status}"  theme="simple"/> --%>
                                                          <%-- <s:select headerKey="" headerValue="--Please Select--" list="{'Registered','Active','Process','FAIL','PASS','Hold','Selected','Rejected'}" name="status" id="reportStatus" cssClass="inputSelect" theme="simple"/> --%>
                                                          <s:select headerKey="" headerValue="--Please Select--" list="{'FAIL','PASS'}" name="status" id="reportStatus" cssClass="inputSelect" theme="simple"/>
                                                            
                                                        </td>
                                                       
                                                        </tr> 
                                                         <tr>
                                                       
                                                        <td class="fieldLabel">Attending&nbsp;Interview&nbsp;at</td>
                                                            <td >
                                                            <s:select headerKey="" headerValue="--Please Select--" list="#@java.util.LinkedHashMap@{'1':'MiracleCampus','2':'CampusRecruitment','3':'JobFair'}" name="interviewAtConsultantReport" id="interviewAtConsultantReport" cssClass="inputSelect"  value="%{interviewAt}"  theme="simple">
                                                            </s:select>
                                                            </td>
                                                        <td class="fieldLabel">College&nbsp;Name&nbsp;</td>
                                                        <td>
                                                        <s:select name="recLocation" id="recLocation" cssClass="inputSelect" headerKey="" headerValue="--Please Select--" list="recLocationList" theme="simple" onchange="fieldLengthValidator(this);"/>
                                                        </td>
                                                         
                                                        <td  colspan="2" align="right">
                                                        <s:submit cssClass="buttonBg" value="Generate" />
                                                    </td>
                                                        </tr>

                                                        
                                                        </table>  
                                                    </td>
                                                </tr> 
                                                
                                            </table>
                                            
                                        </s:form>
                                      
                                    </div>
                                    <!-- Consultant Reports tab End -->
                                    
                                    <!--//END TAB : -->
                                    <%--  </sx:tabbedpanel> --%>
                                </div>
                                <!--//END TABBED PANNEL : -->
                                <script type="text/javascript">
   
                                                          var cal1 = new CalendarTime(document.forms['frmReportSearch'].elements['startDate']);
                                                             cal1.year_scroll = true;
                                                             cal1.time_comp = false;
                                                             
                                                             var cal = new CalendarTime(document.forms['frmReportSearch'].elements['endDate']);
                                                             cal.year_scroll = true;
                                                             cal.time_comp = false;
                                                        
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
   setCreDefaultDatesForReport();
     });
</script>	
        
        
    </body>
</html>


