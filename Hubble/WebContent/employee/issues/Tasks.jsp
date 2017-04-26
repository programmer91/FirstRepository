<!-- 
 *******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  November 20, 2007, 3:25 PM
 *
 * Author  : Rajasekhar Yenduva<ryenduva@miraclesoft.com>
 *
 * File    : Tasks.jsp
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * ******************************************************************************
 */
-->
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%-- <%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<%@ page import="com.freeware.gridtag.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd"%>
<%@ page import="com.mss.mirage.util.DataSourceDataProvider"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hubble Organization Portal :: My Tasks</title>
        <sx:head cache="true"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tooltip.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tooltip.js"/>"></script>   
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>   
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>  
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <s:include value="/includes/template/headerScript.html"/>
    </head>
    <body class="bodyGeneral" oncontextmenu="return false;">
        <%!
        /* Declarations */
        Connection connection;
        String queryString;
        String strTmp;
        String strSortCol;
        String strSortOrd;
        private DataSourceDataProvider dataSourceDataProvider;
        
        int intSortOrd = 0;
        int intCurr;
        boolean blnSortAsc = true;
        HttpServletRequest httpServletRequest;
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
                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                            
                            <!--//START DATA COLUMN : Coloumn for Screen Content-->
                            <td width="850px" class="cellBorder" valign="top">
                                <table cellpadding="0" cellspacing="0" width="100%">
                                    <%--<tr>
                                        <td>
                                            <span class="fieldLabel">Account Name :</span>&nbsp;
                                            <a class="navigationText" href="<s:url action="../accounts/getAccount"><s:param name="id" value="%{accountId}"/></s:url>"><s:property value="#session.accountName"/></a>
                                            <s:if test="contactId !=0">
                                                &nbsp;&nbsp;<span class="fieldLabel">Contact Name :</span>&nbsp;
                                                <a class="navigationText" href="<s:url action="../contacts/getContact"><s:param name="id" value="%{contactId}"/></s:url>"><s:property value="#session.contactName"/></a>
                                            </s:if>
                                        </td>
                                    </tr>--%>
                                    <tr>
                                        <td valign="top" style="padding:5px 5px 5px 5px;"> 
                                            
                                                <!--//START TABBED PANNEL : -->
                                                <ul id="accountTabs" class="shadetabs" >
                                                    <s:if test="%{addBtnVisible == 'TeamTasks'}">
                                                    <li ><a href="#" class="selected" rel="issuesTab"  >Team Tasks </a></li>
                                                    <%--  123 <s:property value="addBtnVisible"/> 456 --%>
                                                     </s:if>
                                                      <s:else>
                                                        <li ><a href="#" class="selected" rel="issuesTab"  >Tasks </a></li>
                                                        </s:else>
                                                </ul>
                                           
                                            <%--<sx:tabbedpanel id="IssuesPanel" cssStyle="width: 845px; height: 500px;padding:5px 5px 5px 5px;" doLayout="true"> --%>
                                            <div  style="border:1px solid gray; width:845px;height: 500px; overflow:auto; margin-bottom: 1em;">
                                                <!--//START TAB : -->
                                                <%--   <sx:div id="issuesTab" label="Tasks" cssStyle="overflow:auto;"> --%>
                                                <div id="issuesTab" class="tabcontent"  > 
                                                    <%
                                                    
                                                    try{
                                                        
                                                        /* Getting DataSource using Service Locator */
                                                        connection = ConnectionProvider.getInstance().getConnection();
                                                        
                                                        intCurr = 1;
                                                        
                                                        /* String Variable for storing current position of records in dbgrid*/
                                                        strTmp = request.getParameter("txtCurr");
                                                        if (strTmp != null)
                                                            intCurr = Integer.parseInt(strTmp);
                                                        
                                                        /* Specifing Shorting Column */
                                                        strSortCol = request.getParameter("txtSortCol");
                                                        
                                                        if (strSortCol == null) strSortCol = "DateCreated";
                                                        
                                                        strSortOrd = request.getParameter("txtSortAsc");
                                                        if (strSortOrd == null) strSortOrd = "ASC";
                                                        String username =(String)session.getAttribute("userId");
                                                        
                                                        dataSourceDataProvider = DataSourceDataProvider.getInstance();
                                                        String department =  dataSourceDataProvider.getDepartmentName(username);
                                                        //out.print("Depattment"+department);
                                                        String   assingedName =  dataSourceDataProvider.getFname_Lname(username);
                                                         %>
                                                         <s:if test="%{addBtnVisible == 'TeamTasks'}">
                                                             <%
                                                            // System.err.println("Tasks Query");
                                                             queryString = session.getAttribute("qsIssueList").toString();
                                                              out.println("Tasks Query is"+ queryString);
                                                             %>
                                                         </s:if>
                                                         <s:else>
                                                          <%
                                                            // System.err.println("Issues Query");
                                                           // out.println("roleName"+session.getAttribute("roleName"));
                                                             //queryString = "SELECT  Id,Category,IssueType,DateCreated,CreatedBy,AssignedTo,Status,Description from tblEmpIssues";
                                                             if((department.equalsIgnoreCase("Sales") && !session.getAttribute("roleName").equals("Recruitment")) || (department.equalsIgnoreCase("Admin") && !session.getAttribute("roleName").equals("Recruitment"))){
                                                             queryString = "SELECT tblEmpIssues.Id as Id,tblEmpIssues.Category as Category,tblEmpIssues.IssueType as IssueType,tblEmpIssues.DateCreated as DateCreated,"+
                                                                     "tblEmpIssues.CreatedBy as CreatedBy,tblEmpIssues.AssignedTo as AssignedTo,tblEmpIssues.Status as Status,tblEmpIssues.Description as Description,tblCrmAccount.Name as Name "+
                                                                     "from (tblEmpIssues left join tblCrmActivity on (tblCrmActivity.Id=TaskID)left join tblCrmAccount "+
                                                                     "on(tblCrmActivity.AccountId=tblCrmAccount.Id))";
                                                             
                                                             //+ " or assignedTo is NULL)";
                                                            // queryString = queryString+ " and Status <> 'Closed' AND DateCreated >= '2012-01-01 00:00:00' ";
                                                              queryString = queryString+ " where tblEmpIssues.Status <> 'Closed' AND tblEmpIssues.DateCreated > '2012-04-11 00:00:00' ";   
                                                              queryString = queryString+ " and  (tblEmpIssues.AssignedTo='" +  assingedName +"') ";
                                                             }else{
                                                                 queryString = "SELECT  Id,Category,IssueType,DateCreated,CreatedBy,AssignedTo,Status,Description from tblEmpIssues";
                                                                 queryString = queryString+ " where Status <> 'Closed' AND DateCreated > '2012-04-11 00:00:00' "; 
                                                                 queryString = queryString+ " and  (AssignedTo='" +  assingedName +"') ";
                                                             }
                                                              String   catagory  =  dataSourceDataProvider.getIssueCatagory(username);
                                                     
                                                        if(catagory.equalsIgnoreCase("Mirage")) {
                                                           // queryString = queryString+ "  and Category in ('Mirage')";
                                                                  queryString = queryString+ "  and tblEmpIssues.Category in ('Mirage')";
                                                        }
                                                        
                                                        if(catagory.equalsIgnoreCase("SysAdmin")) {
                                                           // queryString = queryString+ " and Category in ('SysAdmin')";
                                                                  queryString = queryString+ " and tblEmpIssues.Category in ('SysAdmin')";
                                                            
                                                        }
                                                        
                                                        if(catagory.equalsIgnoreCase("HR")) {
                                                           // queryString = queryString+ " and Category in ('HR')";
                                                                  queryString = queryString+ " and tblEmpIssues.Category in ('HR')";
                                                            
                                                        }
                                                        if(catagory.equalsIgnoreCase("Sales")) {
                                                            //queryString = queryString+ " and Category in ('Sales')";
                                                                  queryString = queryString+ " and tblEmpIssues.Category in ('Sales')";
                                                            
                                                        }      
                                                        
                                                        //queryString = queryString+" order by DateCreated DESC";
                                                              queryString = queryString+" order by tblEmpIssues.DateCreated DESC LIMIT 150";
                                                        
                                                        // DESC
                                                      //  out.println(queryString);
                                                        // queryString = queryString + " WHERE objectType ='ContactActivity' ORDER BY DateUploaded"; tempory
                                                        
                                                        String attachmentAction = "../../employee/issues/issue.action";
                                                             
                                                             
                                                             %>
                                                          </s:else>
                                                        <%
                                                    /* if(department.equalsIgnoreCase("OPERATIONS")) {
                                                    queryString = queryString+ " and  Category in ('HR', 'Office Supplies')";
                                                    }*/
                                                        
                                                     //out.print("naga::"+ request.getAttribute("subnavigateTo").toString());
                                                    session.setAttribute(ApplicationConstants.ISSUE_TO_TASK,request.getAttribute("subnavigateTo").toString());
                                                       
                                                       //out.print("naga::"+resl);
                                                    
                                                    %>
                                                    <form action="" name="frmDBGrid" method="post">  
                                                        <table cellpadding="0" cellspacing="0" width="100%">
                                                            <tr>
                                                                <td class="headerText">
                                                                    <s:property value="#request.resultMessage || #session.resultMessage"/>
                                                                    <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td>
                                                                   <%-- <s:if test="#session.isUserManager == 1 || #session.isUserTeamLead==1 "> --%>
                                                                        <!-- DataGrid for list all Attachments -->
                                                                        <%--<s:property value="#session.roleName"/>--%>
                                                                        <%   //out.print("roleid-->"+session.getAttribute("roleId"));
                                                                              if((department.equalsIgnoreCase("Sales") && !session.getAttribute("roleName").equals("Recruitment")) || session.getAttribute("roleName").equals("Sales") || (session.getAttribute("roleName").equals("Admin") && !session.getAttribute("roleName").equals("Recruitment"))){ %>
                                                                              <grd:dbgrid id="tblCrmAttachments" name="tblCrmAttachments" width="100" pageSize="19" 
                                                                              currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                              dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                            
                                                                            <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                                           imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                                           addImage="../../includes/images/DBGrid/Add.png" />
                                                                            <%--addAction="<%=attachmentAction%>"/>--%>
                                                                            <grd:rownumcolumn headerText="SNo" width="2" HAlign="center"/>
                                                                            <grd:imagecolumn headerText="Edit" width="4" HAlign="center" imageSrc="../../includes/images/DBGrid/Edit.gif"
                                                                                             linkUrl="getIssue.action?issueId={Id}&accessType=Task" imageBorder="0"
                                                                                             imageWidth="16" imageHeight="16" alterText="Click to edit"></grd:imagecolumn>
                                                                            
                                                                            <grd:textcolumn dataField="Category"  headerText="Category"   width="10" sortable="true"/>                  
                                                                            <grd:textcolumn dataField="CreatedBy"  headerText="AssignedBy"   width="8" sortable="true"/> 
                                                                            <%-- <grd:textcolumn dataField="AssignedTo"  headerText="AssignedTo"   width="10" sortable="true"/>  --%>
                                                                            <grd:textcolumn dataField="IssueType"  headerText="IssueType" width="14" sortable="true"/> 
                                                                            <grd:textcolumn dataField="Name"  headerText="AccountName"   width="16" sortable="true"/>
                                                                            <grd:textcolumn dataField="Status"  headerText="Status" width="10" sortable="true"/>  
                                                                            <grd:datecolumn dataField="DateCreated"  headerText="DateCreated"  dataFormat="MM-dd-yyyy HH:mm:SS" width="14" sortable="true"/>
                                                                            <grd:textcolumn dataField="Description"  headerText="Description" width="40" sortable="true"/>  
                                                                            
                                                                            
                                                                            </grd:dbgrid>
                                                                            
                                                                        <%}else{%>
                                                                       
                                                                            <grd:dbgrid id="tblCrmAttachments" name="tblCrmAttachments" width="100" pageSize="19" 
                                                                              currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                              dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                            
                                                                            <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                                           imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                                           addImage="../../includes/images/DBGrid/Add.png" />
                                                                            <%--addAction="<%=attachmentAction%>"/>--%>
                                                                            <grd:rownumcolumn headerText="SNo" width="2" HAlign="center"/>
                                                                            <grd:imagecolumn headerText="Edit" width="4" HAlign="center" imageSrc="../../includes/images/DBGrid/Edit.gif"
                                                                                             linkUrl="getIssue.action?issueId={Id}&accessType=Task" imageBorder="0"
                                                                                             imageWidth="16" imageHeight="16" alterText="Click to edit"></grd:imagecolumn>
                                                                            
                                                                            <grd:textcolumn dataField="Category"  headerText="Category"   width="14" sortable="true"/>                  
                                                                            <grd:textcolumn dataField="CreatedBy"  headerText="AssignedBy"   width="10" sortable="true"/> 
                                                                            <%-- <grd:textcolumn dataField="AssignedTo"  headerText="AssignedTo"   width="10" sortable="true"/>  --%>
                                                                            <grd:textcolumn dataField="IssueType"  headerText="IssueType" width="10" sortable="true"/> 
                                                                            <%--<grd:textcolumn dataField="Name"  headerText="AccountName"   width="10" sortable="true"/>--%>
                                                                            <grd:textcolumn dataField="Status"  headerText="Status" width="10" sortable="true"/>  
                                                                            <grd:datecolumn dataField="DateCreated"  headerText="DateCreated"  dataFormat="MM-dd-yyyy HH:mm:SS" width="18" sortable="true"/>
                                                                            <grd:textcolumn dataField="Description"  headerText="Description" width="40" sortable="true"/>  
                                                                            
                                                                            
                                                                            </grd:dbgrid>
                                                                       <%}%>
                                                                 <%--   </s:if>
                                                                    <s:else>
                                                                    <!-- DataGrid for list all Attachments -->
                                                                    <grd:dbgrid id="tblCrmAttachments" name="tblCrmAttachments" width="100" pageSize="19" 
                                                                                currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                                dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                        
                                                                        <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                                       imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                                       />
                                                                        <%--addAction="<%=attachmentAction%>"/>--%>
                                                                      <%--  <grd:rownumcolumn headerText="SNo" width="2" HAlign="center"/>
                                                                        <grd:imagecolumn headerText="Edit" width="4" HAlign="center" imageSrc="../../includes/images/DBGrid/Edit.gif"
                                                                                         linkUrl="getIssue.action?issueId={Id}&accessType=Task" imageBorder="0"
                                                                                         imageWidth="16" imageHeight="16" alterText="Click to edit"></grd:imagecolumn>
                                                                        
                                                                        <grd:textcolumn dataField="Category"  headerText="Category"   width="14" sortable="true"/>                  
                                                                        <grd:textcolumn dataField="CreatedBy"  headerText="AssignedBy"   width="10" sortable="true"/> 
                                                                       <%-- <grd:textcolumn dataField="AssignedTo"  headerText="AssignedTo"   width="10" sortable="true"/>  --%>
                                                                      <%--  <grd:textcolumn dataField="IssueType"  headerText="IssueType" width="10" sortable="true"/>  
                                                                        <grd:textcolumn dataField="Status"  headerText="Status" width="10" sortable="true"/>  
                                                                        <grd:datecolumn dataField="DateCreated"  headerText="DateCreated"  dataFormat="MM-dd-yyyy HH:mm:SS" width="18" sortable="true"/>
                                                                        <grd:textcolumn dataField="Description"  headerText="Description" width="40" sortable="true"/>  
                                                           
                                                                   
                                                                    </grd:dbgrid>
                                                                    </s:else>--%>
                                                                    
                                                                    <!-- these components are DBGrid Specific  -->
                                                                    <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                                    <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                                    <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                                                                    <input id="txtDBId" TYPE="hidden" NAME="txtDBId" VALUE="0"/>
                                                                    
                                                                    <!-- Used for Storing the Current Row No. in the Table -->
                                                                    <input id="txtTRId"TYPE="hidden" NAME="txtTRId" VALUE="1"/>
                                                                </td>
                                                            </tr>
                                                        </table>    
                                                    </form>  
                                                    <%
                                                    connection.close();
                                                    connection = null;
                                                    }catch(Exception se){
                                                    System.out.println("Exception in Tasks "+se);
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
                                            <!--//END TAB : -->
                                     
                                            <!--//END TABBED PANNEL : -->
                                        </td>
                                    </tr> 
                                </table>
                                
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
