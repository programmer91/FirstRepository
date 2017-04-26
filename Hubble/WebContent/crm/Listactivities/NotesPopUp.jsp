<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%--<%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider" %>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ page import="com.mss.mirage.crm.notes.NotesVTO" %>
<%@ page import="java.util.Date" %>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd" %>
<%@ page import="com.freeware.gridtag.*" %> 

<html>
<head>
    <title>Hubble Organization Portal :: Managing Notes</title>
    <sx:head cache="false"/>
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
    <%--<link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>"> --%>
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script> 
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/NoteClientValidation.js"/>"></script> 
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/StringUtility.js"/>"></script>
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
String accoutId;
String objectType;
int objectId = 0;
int intSortOrd = 0;
int intCurr;
boolean blnSortAsc = true;
%> 
<table class="templateTable1000x580" align="center" cellpadding="0" cellspacing="0">
<%--<tr class="headerBg">
    <td valign="top">
        <s:include value="/includes/template/Header.jsp"/>                    
    </td>
</tr> --%>
<tr>
<td>
    <table class="innerTable1000x515" cellpadding="0" cellspacing="0">
    <tr>
       <%-- <td width="150px;" class="leftMenuBgColor" valign="top"> 
            <s:include value="/includes/template/LeftMenu.jsp"/>                    
        </td> --%>
        <td width="850px" class="cellBorder" valign="top">
        <table cellpadding="0" cellspacing="0" width="100%">
            <tr>
                <td>
                    <span class="fieldLabel"><s:property value="%{objectType}"/>&nbsp;Name :</span>&nbsp;
                    <s:if test="objectType == 'Account'">
                        <a href="<s:url action="../accounts/getAccount"><s:param name="id" value="%{objectId}"/></s:url>" class="navigationText"><s:property value="%{objectName}"/></a>
                    </s:if>
                    <s:elseif test="objectType == 'Contact'">
                        <a href="<s:url action="../contacts/getContact"><s:param name="id" value="%{objectId}"/></s:url>" class="navigationText"><s:property value="%{objectName}"/></a>
                    </s:elseif>
                </td>
            </tr>
            <tr>
                <td valign="top" style="padding-left:10px;padding-top:5px;">
                
                    <tr>
            <td valign="top" style="padding-left:10px;padding-top:5px;">
                
                <ul id="accountTabs1" class="shadetabs" >
                    <li ><a href="#" class="selected" rel="notes"  > List Notes </a></li>
                   
                </ul>
             
                <%--<sx:tabbedpanel id="notesPannel" cssStyle="width: 845px; height: 260px;padding-left:10px;padding-top:5px;" doLayout="true"> --%>
                <div  style="border:1px solid gray; width:400px;height: 250px; overflow:auto; margin-bottom: 1em;">
                    <%-- <sx:div id="notes" label="List Notes" theme="ajax"> --%>
                    <div id="notes" class="tabcontent"  >
                        <%--   <s:form action="" name="activityGrid" method="get" theme="simple"> --%>
                                      
                        <%
                        
                        /* String Variable for storing current position of records in dbgrid*/
                        strTmp = request.getParameter("txtCurr");
                        
                        
                        intCurr = 1;
                        if (strTmp != null)
                        intCurr = Integer.parseInt(strTmp);
                        
                        /* Specifing Shorting Column */
                        strSortCol = request.getParameter("Colname");
                        
                        if (strSortCol == null) strSortCol = "firstName";
                        
                        strSortOrd = request.getParameter("txtSortAsc");
                        if (strSortOrd == null) strSortOrd = "ASC";
                        
                        if(request.getAttribute("objectId") != null){
                        objectId = Integer.parseInt(request.getAttribute("objectId").toString());
                        }
                        
                        if(request.getAttribute("objectType") != null){
                        objectType = request.getAttribute("objectType").toString();
                        }
                        
                        try{
                        
                        /* Getting DataSource using Service Locator */
                        connection = ConnectionProvider.getInstance().getConnection();
                        
                        /* Sql query for retrieving resultset from DataBase */
                        queryString =" Select Id,CreatedDate,NoteType,Note FROM tblCrmNotes";
                        queryString = queryString + " WHERE ObjectId =273109";
                        queryString = queryString + " AND ObjectType = 'Contact' ORDER BY CreatedDate";
                       // out.print(queryString);
                        String notesAction = "../notes/note.action";
                        
                        if(objectId != 0){
                        notesAction = notesAction+"?"+objectType.toLowerCase()+"Id="+objectId;
                        }
                        
                        %>
                        
                        <form action="" method="post" name="frmDBGrid">  
                            <table cellpadding="0" cellspacing="0" width="100%">
                                <tr>
                                    <td class="headerText">
                                        <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <!-- DataGrid for list all activities -->
                                        <grd:dbgrid id="tblStat" name="tblStat" width="100" pageSize="6" 
                                                    currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                    dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable" >
                                            
                                            <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                           imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                           addImage="../../includes/images/DBGrid/Add.png" 
                                                           addAction="<%=notesAction%>"/>
                                            
                                            <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" 
                                                            imageAscending="../../includes/images/DBGrid/ImgAsc.gif" 
                                                            imageDescending="../../includes/images/DBGrid/ImgDesc.gif"/>    
                                            
                                            <grd:imagecolumn  headerText="Edit" width="5"  HAlign="center"  
                                                              imageSrc="../../includes/images/DBGrid/newEdit_17x18.png"  
                                                              linkUrl="../notes/getNote.action?id={Id}" 
                                                              imageBorder="0" 
                                                              imageWidth="16" 
                                                              imageHeight="16" 
                                                              alterText="Click to edit" />
                                            <grd:textcolumn dataField="NoteType"  headerText="NoteType" width="15" sortable="true" />
                                            <grd:textcolumn dataField="Note" headerText="Note" width="40" sortable="true" />
                                            <grd:datecolumn dataField="CreatedDate"  headerText="CreatedDate" HAlign="left" width="8" dataFormat="MM-dd-yyyy"/>
                                        </grd:dbgrid>
                                        
                                        <input TYPE="hidden" NAME="txtCurr"	VALUE="<%=intCurr%>"/>
                                        <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>"/>
                                        <input type="hidden" name="txtSortAsc"  value="<%=strSortOrd%>"/>
                                        <s:hidden id="strContactId" name="strContactId" value="%{contactId}"/>
                                        
                                        
                                    </td>
                                </tr>
                            </table>                                        
                        </form>
                        
                        <%
                        connection.close();
                        connection = null;
                        }catch(Exception se){
                        System.out.println("Exception in NotesAdd"+se);
                        }finally{
                        if(connection!= null){
                        connection.close();
                        connection = null;
                        }
                        }
                        %>
                        <%--</sx:div> --%>
                    </div>
                    <%-- </sx:tabbedpanel> --%>
                </div>
                 <script type="text/javascript">

var countries=new ddtabcontent("accountTabs1")
countries.setpersist(false)
countries.setselectedClassTarget("link") //"link" or "linkparent"
countries.init()

                                </script>
            </td>
        </tr>
        
        </table>
</td>

</tr>
</table>
</td>
</tr>
<tr class="footerBg">
    <td align="center">&reg; 2007  Mirage - All Rights Reserved.</td>
</tr>
</table>
<%-- End  of the Main Table --%>
        
</body>
</html>
