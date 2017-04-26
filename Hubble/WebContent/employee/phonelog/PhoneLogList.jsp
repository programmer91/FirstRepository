<!-- 
 *******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  Feb 20, 2008, 3:25 PM
 *
 * Author  : Venkateswara rao Nukala<vnukala@miraclesoft.com>
 *
 * File    : PhoneLogSearch.jsp
 *
 * Copyright 2008 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
-->
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="com.freeware.gridtag.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hubble Organization Portal :: Employee PhoneLog List</title>
         <sx:head cache="true"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tooltip.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tooltip.js"/>"></script>   
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>   
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>  
        <script type="text/JavaScript" src="<s:url  value="/includes/javascripts/clientValidations/PhoneLog.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
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
        String action;
        int role;
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
                            <td width="150px;" class="leftMenuBgColor" valign="top"> 
                                <s:include value="/includes/template/LeftMenu.jsp"/> 
                            </td>
                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                            
                            <!--//START DATA COLUMN : Coloumn for Screen Content-->
                            <td width="850px" class="cellBorder" valign="top">
                                <!--//START TABBED PANNEL : -->
                                <sx:tabbedpanel id="PhoneLogPanel" cssStyle="width: 845px;padding:5px 5px;" doLayout="false">
                                    <!--//START TAB : -->
                                    <sx:div id="PhoneLogTab" label="PhoneLogList" >
                                        <%     try {
                                            
                                            queryString="";
                                            /* Getting DataSource using Service Locator */
                                            connection = ConnectionProvider.getInstance().getConnection();
                                            
                                            
                                            /* String Variable for storing current position of records in dbgrid*/
                                            strTmp = request.getParameter("txtCurr");
                                            intCurr = 1;
                                            if (strTmp != null)
                                                intCurr = Integer.parseInt(strTmp);
                                            
                                            /* Specifing Shorting Column */
                                            strSortCol = request.getParameter("txtSortCol");
                                            
                                            if (strSortCol == null) strSortCol = "DateCreated";
                                            
                                            strSortOrd = request.getParameter("txtSortAsc");
                                            if (strSortOrd == null) strSortOrd = "ASC";
                                            // out.println("testing");
                                            queryString =session.getAttribute(ApplicationConstants.QS_PHONE_LIST).toString();
                                            
                                            //out.println(queryString);
                                            
                                            
                                            // queryString="SELECT FromNo ,ToNo ,CallDate,CallStartTime,CallEndTime ,Duration from tblEmpPhoneLog  ";
                                            /* Sql query for retrieving resultset from DataBase */
                                            // out.println(queryString);
                                      
                                            String PhoneLogAction = "../../employee/phonelog/phoneLog.action?currentAction=addPhoneLog";
                                        
                                        %>
                                        <form action="" name="frmDBGrid"> 
                                            <table cellpadding="0" cellspacing="0" width="100%">
                                                <tr>
                                                    <td class="headerText">
                                                        <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <!-- DataGrid for list all Attachments -->
                                                       
                                                        <s:if test="%{currentSearchAction == 'phoneLogSearch'}" >
                                                            
                                                            <grd:dbgrid id="tblEmpPhoneLog" name="tblEmpPhoneLog" width="100" pageSize="15" 
                                                                        currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                        dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                               imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                               addImage="../../includes/images/DBGrid/Add.png"  addAction="<%=PhoneLogAction%>"
                                                                />
                                                                <grd:imagecolumn headerText="Edit" width="4" HAlign="center" imageSrc="../../includes/images/DBGrid/Edit.gif"
                                                                                 linkUrl="getPhoneLog.action?phoneLogId={Id}&currentAction=editPhonelog" imageBorder="0"
                                                                                 imageWidth="16" imageHeight="16" alterText="Click to edit"></grd:imagecolumn> 
                                                                <grd:numbercolumn dataField="CallDate"      headerText="CALL DATE"      width="15" sortable="true" dataFormat="" />  
                                                                <grd:numbercolumn dataField="LoginId"       headerText="EMP LOGIN ID"   width="15" sortable="true" dataFormat=""/>                                                                      
                                                                <grd:numbercolumn dataField="Duration"      headerText="CALL DURATION"  width="15"  sortable="true" dataFormat="" />                           
                                                                <grd:numbercolumn dataField="Description"   headerText="DESCRIPTION"    width="15"  sortable="true" dataFormat="" />
                                                                <grd:numbercolumn dataField="FromNo"        headerText="FROM PHNO"      width="15"  sortable="true" dataFormat="" />
                                                                <grd:numbercolumn dataField="ToNo"          headerText="TO PHNO"        width="15"  sortable="true" dataFormat="" />
                                                                
                                                                <grd:imagecolumn headerText="Delete" width="8" HAlign="center" imageSrc="../../includes/images/DBGrid/Delete.png"
                                                                                 linkUrl="deletePhonelog.action?phoneLogId={Id}&currentAction=deletePhonelog" imageBorder="0"
                                                                                 imageWidth="40" imageHeight="16" alterText="Click to delete"></grd:imagecolumn>  
                                                                
                                                            </grd:dbgrid>
                                                        </s:if>
                                                        <s:else>
                                                            <grd:dbgrid id="tblEmpPhoneLog" name="tblEmpPhoneLog" width="100" pageSize="15" 
                                                                        currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                        dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                               imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                               addImage="../../includes/images/DBGrid/Add.png"
                                                                />
                                                                
                                                                <grd:imagecolumn headerText="Edit" width="4" HAlign="center" imageSrc="../../includes/images/DBGrid/Edit.gif"
                                                                                 linkUrl="getPhoneLog.action?phoneLogId={Id}&currentAction=editPhonelog" imageBorder="0"
                                                                                 imageWidth="16" imageHeight="16" alterText="Click to edit"></grd:imagecolumn> 
                                                                <grd:numbercolumn dataField="CallDate"      headerText="CALL DATE"      width="15" sortable="true" dataFormat="" />  
                                                                <grd:numbercolumn dataField="LoginId"       headerText="EMP LOGIN ID"   width="15" sortable="true" dataFormat=""/>                                                                      
                                                                <grd:numbercolumn dataField="Duration"      headerText="CALL DURATION"  width="15"  sortable="true" dataFormat="" />                           
                                                                <grd:numbercolumn dataField="Description"   headerText="DESCRIPTION"    width="15"  sortable="true" dataFormat="" />
                                                                <grd:numbercolumn dataField="FromNo"        headerText="FROM PHNO"      width="15"  sortable="true" dataFormat="" />
                                                                <grd:numbercolumn dataField="ToNo"          headerText="TO PHNO"        width="15"  sortable="true" dataFormat="" />
                                                                
                                                                <grd:imagecolumn headerText="Delete" width="8" HAlign="center" imageSrc="../../includes/images/DBGrid/Delete.png"
                                                                                 linkUrl="deletePhonelog.action?phoneLogId={Id}&currentAction=deletePhonelog" imageBorder="0"
                                                                                 imageWidth="40" imageHeight="16" alterText="Click to delete"></grd:imagecolumn>  
                                                                
                                                            </grd:dbgrid>
                                                        </s:else>
                                                        
                                                        <!-- these components are DBGrid Specific  -->
                                                        <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                        <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                        <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                                                        <s:hidden name="currentSearchAction" id="currentSearchAction"  value="%{currentSearchAction}" />
                                                        
                                                        
                                                    </td>
                                                </tr>
                                            </table>    
                                        </form>  
                                        <%
                                        
                                        /* For Exception handling mechanism */
                                        }catch(Exception se){
                                            System.out.println("Exception in PhoneLogList "+se);
                                        }finally{
                                            if(connection!= null){
                                                connection.close();
                                                connection = null;
                                            }
                                        }
                                        %>
                                    </sx:div >
                                    <!--//END TAB : -->
                                    <!--//END TAB : -->
                                    
                                    
                                    <sx:div id="PhoneLogSearchTab" label="PhoneLog Search" >
                                        
                                        <s:form name="frmSearch" action="%{currentSearchAction}" theme="simple"  onsubmit="return searchValidation();">
                                            
                                            <!--The components of searching issues -->
                                            <table border="0" cellpadding="1" cellspacing="1" width="100%">
                                                <tr>
                                                    <td class="headerText" colspan="4">
                                                        <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                    </td>
                                                </tr>
                                                <tr>                                                    
                                                    
                                                    <td  class="fieldLabel">Start Date:</td>
                                                    <td> <s:textfield name="startDate" value="" id="startDate" cssClass="inputTextBlue"  value=""   />
                                                        <a href="javascript:cal1.popup();">
                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a> </td>
                                                    <td class="fieldLabel">End Date:</td>
                                                    <td> <s:textfield name="endDate" value="" id="endDate" cssClass="inputTextBlue"  value=""   />
                                                        <a href="javascript:cal2.popup();">
                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a> </td>
                                                </tr> 
                                                
                                                <tr>
                                                    <td></td>                                                    
                                                    <td>
                                                        <s:hidden name="submitFrom" value="Search"/>
                                                        <s:submit cssClass="buttonBg" value="Search"/>
                                                    </td>  
                                                </tr> 
                                                
                                            </table>
                                            
                                        </s:form>
                                        
                                        <!-- this script for After loading Form it will instantiate Calender Objects as you require -->
                                        <script type="text/JavaScript">
                                                           var cal1 = new CalendarTime(document.forms['frmSearch'].elements['startDate']);
                                                           cal1.year_scroll = true;
                                                           cal1.time_comp = false;
                                                           
                                                           var cal2 = new CalendarTime(document.forms['frmSearch'].elements['endDate']);
                                                           cal2.year_scroll = true;
                                                           cal2.time_comp = false;
                                        </script> 
                                        
                                    </sx:div>
                                    
                                    <!--//END TAB : -->
                                </sx:tabbedpanel>
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
                <td align="center"><s:include value="/includes/template/Footer.jsp"/>   </td>
            </tr>
            <!--//END FOOTER : Record for Footer Background and Content-->
            
        </table>
        <!--//END MAIN TABLE : Table for template Structure-->
        
    </body>
</html>
