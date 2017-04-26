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


<html>
<head>
    
    <title>Hubble Organization Portal :: Territory Information</title>
    <sx:head cache="false"/>
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
    <link rel="StyleSheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
    <link REL="StyleSheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
    <script language="javascript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
    <%--<script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>--%>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
    <SCRIPT type="text/javascript">
	function closeStates()
	{
             window.close(); 
                
	}        
        
    </SCRIPT>
</head>
<%!
Connection connection = null;
%>
<body class="bodyGeneral" oncontextmenu="return false;">
<br>

<table cellpadding="0" width="70%" align="center" cellspacing="0" bordercolor="#efefef" class="cellBorder">  

<tr class="headerText">
    <td colspan="5"> 
        Territory Information
    </td>
</tr>
<tr>
<td colspan="5"> 
    
    <table align="center" cellpadding="0" cellspacing="0"  width="100%">
        <tr>
            <td align="center">
                <br>
                <%
                
                try{
                    //to get the dates from user while searching the timesheet
                   //String  strSQL="SELECT Id,Description,Name  FROM tblLKStates";
                    String  strSQL="SELECT Description,state1,state2,state3,state4,state5 FROM tblLKTerritory WHERE Id>13";
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
                    
                    <grd:dbgrid id="tblDBGrid" name="tblDBGrid" width="75" pageSize="20" 
                                currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                dataMember="<%=strSQL%>" dataSource="<%=connection%>" cssClass="gridTable">
                        <grd:gridpager imgFirst="../includes/images/DBGrid/First.gif" imgPrevious="../includes/images/DBGrid/Previous.gif" 
                                       imgNext="../includes/images/DBGrid/Next.gif" imgLast="../includes/images/DBGrid/Last.gif"/>
                        <grd:rownumcolumn headerText="Id" HAlign="Center"/>               
                        <%-- <grd:numbercolumn dataField="Id" headerText="ID" width="8" dataFormat="" HAlign="center"/>       --%>
                        <grd:textcolumn dataField="Description" headerText="Territory" width="15" HAlign="left"/>
                        <grd:textcolumn dataField="state1" headerText="state1" width="15" HAlign="center" />
                        <grd:textcolumn dataField="state2" headerText="state2" width="15" HAlign="center" />
                        <grd:textcolumn dataField="state3" headerText="state3" width="15" HAlign="center" />
                        <grd:textcolumn dataField="state4" headerText="state4" width="15" HAlign="center" />
                        <grd:textcolumn dataField="state5" headerText="state5" width="15" HAlign="center" />
                        
                    </grd:dbgrid>
                    <input TYPE="hidden" NAME="txtCurr" id="txtCurr" VALUE="<%=intCurr%>">
                    <input TYPE="hidden" NAME="txtSortCol" id="txtSortCol" VALUE="<%=strSortCol%>">
                    <input TYPE="hidden" NAME="txtSortAsc" id="txtSortAsc"	VALUE="<%=strSortOrd%>">
                    
                    <INPUT type="button"  value="Close"  class="buttonBg" onclick="closeStates();"/>
                    
                </s:form>    
            </td>
        </tr>
    </table>
    </div>
    
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
