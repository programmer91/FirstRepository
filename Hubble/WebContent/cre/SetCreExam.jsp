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
                                       
                                        <li ><a href="#" class="selected" rel="creConsultantList"  >Cre Consultant Exam </a></li>
                                         
                                 <%--   <%}else{%>
                                    <li ><a href="#" class="selected" rel="IssuesSearchTab">Tasks Search</a></li>
                                     <% }%> --%>
                                    
                                </ul>
                                
                                <%-- <sx:tabbedpanel id="IssuesPanel" cssStyle="width: 845px; height: 500px;padding:5px 5px;" doLayout="true"> --%>
                                <div  style="border:1px solid gray; width:845px;height: 500px; overflow:auto; margin-bottom: 1em;">
                                    <!--//START TAB : -->
                                   
                                   <%--  <% if(request.getParameter("issueList")==null)
                                            {
                                                System.out.println("list");
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
                                            String practiceAction = "../cre/addExamDetails.action";
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
                                                              <%
                                                            
                                                            if(rolesMap.containsKey("7")) {%>
                                                                <grd:dbgrid id="tblCreSetExam" name="tblCreSetExam" width="100" pageSize="18" 
                                                                            currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                            dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                    <grd:gridpager imgFirst="../includes/images/DBGrid/First.gif" imgPrevious="../includes/images/DBGrid/Previous.gif" 
                                                                                   imgNext="../includes/images/DBGrid/Next.gif" imgLast="../includes/images/DBGrid/Last.gif"
                                                                                   addImage="../includes/images/DBGrid/Add.png"  addAction="<%=practiceAction%>"/>
                                                                     <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>"  imageAscending="../includes/images/DBGrid/ImgAsc.gif" imageDescending="../includes/images/DBGrid/ImgDesc.gif" />
                                                                    <grd:rownumcolumn headerText="SNo" width="2" HAlign="center"/>  
                                                                   <%-- <grd:imagecolumn  headerText="Practice Edit" width="3"  HAlign="center" 
                                                                              imageSrc="../includes/images/DBGrid/Edit.gif" 
                                                                              linkUrl="getCreExamDetails.action?subtopicId1={Id}" 
                                                                              imageBorder="0" imageWidth="12" imageHeight="16" 
                                                                              alterText="Click to edit" /> --%>
                                                                    <grd:anchorcolumn dataField="ExamType"  headerText="ExamType" linkUrl="getCreExamDetails.action?subtopicId1={Id}"  linkText="{ExamType}"  width="7" sortable="true"/> 
                                                                    <grd:numbercolumn dataField="MinMarks" headerText="MinMarks" width="5" sortable="true" dataFormat=""/>
                                                                    <grd:numbercolumn dataField="Time"  headerText="Time"   width="5" sortable="true" dataFormat=""/> 
                                                                    <grd:numbercolumn dataField="NoOfQuestions"  headerText="NoOfQuestions"   width="5" sortable="true" dataFormat=""/>
                                                                    <grd:imagecolumn  headerText="Inactive" width="3"  HAlign="center" 
                                                                              imageSrc="../includes/images/DBGrid/remove.png" 
                                                                              linkUrl="inActiveCreExam.action?subtopicId1={Id}" 
                                                                              imageBorder="0" imageWidth="14" imageHeight="18" 
                                                                              alterText="Click to InActive" />
                                                            
                                                                    </grd:dbgrid>
                                                                    
                                                            <%} else{%>
                                                            <grd:dbgrid id="tblCreSetExam" name="tblCreSetExam" width="100" pageSize="20" 
                                                                            currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                            dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                    <grd:gridpager imgFirst="../includes/images/DBGrid/First.gif" imgPrevious="../includes/images/DBGrid/Previous.gif" 
                                                                                   imgNext="../includes/images/DBGrid/Next.gif" imgLast="../includes/images/DBGrid/Last.gif" />
                                                                    <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>"  imageAscending="../includes/images/DBGrid/ImgAsc.gif" imageDescending="../includes/images/DBGrid/ImgDesc.gif" />
                                                                    <grd:rownumcolumn headerText="SNo" width="2" HAlign="center"/> 
                                                                    <grd:imagecolumn  headerText="SubTopic Edit" width="3"  HAlign="center" 
                                                                              imageSrc="../includes/images/DBGrid/Edit.gif" 
                                                                              linkUrl="getCreExamDetails.action?subtopicId1={Id}" 
                                                                              imageBorder="0" imageWidth="12" imageHeight="16" 
                                                                              alterText="Click to edit" />
                                                                    <grd:textcolumn  dataField="ExamType"  headerText="ExamType"   width="10" sortable="true"/> 
                                                                    <grd:numbercolumn dataField="MinMarks" headerText="MinMarks" width="10" sortable="true" dataFormat=""/>
                                                                    <grd:numbercolumn dataField="Time"  headerText="Time"   width="5" sortable="true" dataFormat=""/> 
                                                                    <grd:numbercolumn dataField="NoOfQuestions"  headerText="NoOfQuestions"   width="10" sortable="true" dataFormat=""/>
                                                                    <grd:imagecolumn  headerText="Inactive" width="3"  HAlign="center" 
                                                                              imageSrc="../includes/images/DBGrid/remove.png" 
                                                                              linkUrl="inActiveCreExam.action?subtopicId1={Id}" 
                                                                              imageBorder="0" imageWidth="18" imageHeight="20" 
                                                                              alterText="Click to InActive" />
                                                                    </grd:dbgrid>  
                                                                
                                                            <%}%>
                                                            <!-- these components are DBGrid Specific  -->
                                                            <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                            <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                            <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                                                            
                                                            
                                                           
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
                                  
                                    
                                    <!--//END TAB : -->
                                    <%--  </sx:tabbedpanel> --%>
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
                <td align="center"><s:include value="/includes/template/Footer.jsp"/>   </td>
            </tr>
             

            <!--//END FOOTER : Record for Footer Background and Content-->
            
        </table>
        <!--//END MAIN TABLE : Table for template Structure-->
        
        
        
        
        
    </body>
</html>


