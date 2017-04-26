<!--/*******************************************************************************
/*
 * @(#)CurrencyInfo.java	September 24, 2007, 12:13 AM 
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */ -->
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>

<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.*,java.util.Date" %>
<%@ page import="com.freeware.gridtag.*" %> 
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%-- <%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd" %>

<SCRIPT type="text/javascript">
	function closeCurrency()
	{
             window.close(); 
                
	}        
        
</SCRIPT>
<html>
    <head>
        
        <title>Hubble Organization Portal :: CurrencyInfo</title>
        
        <sx:head cache="false"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link REL="StyleSheet" HREF="<s:url value="/includes/css/GridStyle.css"/>" type="text/css">
        <link REL="StyleSheet" HREF="<s:url value="/includes/css/leftMenu.css"/>" type="text/css">
        <script language="javascript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
        <%--<script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>--%>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        
    </head>
    <%!
    Connection connection = null;
    
    %>
    <body class="bodyGeneral" oncontextmenu="return false;">
        <br>
        
        <table cellpadding="0" width="70%" align="center" cellspacing="0" bordercolor="#efefef" class="cellBorder">  
            
            <tr class="headerText">
                <td colspan="5"> 
                    Currency And Country Info...
                </td>
            </tr>
            <tr>
                <td colspan="5"> 
                    
                    <table align="center" cellpadding="0" cellspacing="0"  width="98%">
                        <tr>
                            <td align="center">
                                <br>
                                <%
                                
                                try{
                                    //to get the dates from user while searching the timesheet
                                    String  strSQL="SELECT Id,Country,countrycurrency  FROM tblLKCountry";
                                    int intCurr = 1;
                                    int intSortOrd = 0;
                                    String strTmp = null;
                                    boolean blnSortAsc = true;
                                    String strSortCol = null;
                                    String strSortOrd = "DSC";
                                    
                                    strTmp = request.getParameter("txtCurr");
                                    try {
                                        if (strTmp != null)
                                            intCurr = Integer.parseInt(strTmp);
                                    } catch (NumberFormatException NFEx) {
                                        
                                    } //catch
                                    
                                    // for lookup connection
                                    connection = ConnectionProvider.getInstance().getConnection();
                                    strSortCol = request.getParameter("Colname");
                                    strSortOrd = request.getParameter("txtSortAsc");
                                    if (strSortCol == null) strSortCol = "FirstName";
                                    if (strSortOrd == null) strSortOrd = "DSC";
                                    blnSortAsc = (strSortOrd.equals("ASC"));
                                
                                
                                %>
                                <s:form method="post" id="frmDBGrid" name="frmDBGrid" theme="simple"  action="">           
                                    
                                    <grd:dbgrid id="tblDBGrid" name="tblDBGrid" width="75" pageSize="10" 
                                                currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                dataMember="<%=strSQL%>" dataSource="<%=connection%>" cssClass="gridTable">
                                        <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                       imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"/>
                                        <grd:numbercolumn dataField="Id" headerText="ID" width="8" dataFormat="" HAlign="center"/>      
                                        <grd:textcolumn dataField="Country" headerText="CountryName" width="15" HAlign="center"/>
                                        <grd:textcolumn dataField="countrycurrency" headerText="CurrencyType" width="15" HAlign="center" />
                                        
                                    </grd:dbgrid>
                                    <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                    <input TYPE="hidden" NAME="txtSortCol"	VALUE="<%=strSortCol%>">
                                    <input TYPE="hidden" NAME="txtSortAsc"	VALUE="<%=strSortOrd%>">
                                    
                                    <INPUT type="button"  value="Close"  class="buttonBg" onclick="closeCurrency();"/>
                                    
                                </s:form>    
                            </td>
                        </tr>
                    </table>
                    
                    <%
                    connection.close();
                    connection = null;
                    } catch(Exception ex) {
                    
                    } finally {
                    if(connection!=null)
                    connection.close();
                    connection = null;
                    } // finally
                    %>
                    
                    <p align="center">
                        
                        
                    </p>
                </td>      
            </tr>
        </table>
        
        
    </body>
</html>
