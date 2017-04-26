<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%-- <%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<%@ page import="com.freeware.gridtag.*" %> 
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd" %>

<html>
    <head>
        <title>Hubble Organization Portal :: Attachments </title>
        <sx:head cache="false"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link REL="StyleSheet" HREF="<s:url value="/includes/css/GridStyle.css"/>" type="text/css">
        <link REL="StyleSheet" HREF="<s:url value="/includes/css/leftMenu.css"/>" type="text/css">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script language="javascript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/StringUtility.js"/>"></script>
        <%--<script type="text/JavaScript" src="<s:url value="/includes/javascripts/CRMAttachmentClientValidation.js"/>"></script>--%>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/StandardClientValidations.js"/>"></script>
         <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <script type="text/JavaScript">
            function checkField() {
                var upload = document.getElementById("upload").value;
                if(upload == '') {
                    alert('Please Enter Valid File Path');
                    return false;
                }else return true;
            }
        </script>
    </head>
    
    <body class="bodyGeneral" oncontextmenu="return false;">
        <%!
        /* Declarations */
        Connection connection;
        String queryString;
        String strTmp;
        String strSortCol;
        String strSortOrd;
        int objectId;
        String objectType;
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
                    <table class="innerTable1000x515" cellpadding="0" cellspacing="0" border="0">
                        <tr>
                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                            <td width="150px;" class="leftMenuBgColor" valign="top">  
                                <s:include value="/includes/template/LeftMenu.jsp"/>  
                            </td>
                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                            
                            <!--//START DATA COLUMN : Coloumn for Screen Content-->
                            <td width="850px" class="cellBorder" valign="top">
                                <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                    <tr>
                                        <td>
                                            <s:if test="objectType == 'Account'">
                                                <span class="fieldLabel"><s:property value="objectTitle"/>Account Name :</span>&nbsp;
                                                <a class="navigationText" href="<s:url action="../accounts/getAccount"><s:param name="id" value="%{objectId}"/></s:url>"><s:property value="#request.objectName"/></a>    
                                            </s:if>
                                            
                                            <s:elseif test="objectType == 'ContactActivity'">
                                                
                                                <s:if test="contactId !=0">                                                    
                                                    <span class="fieldLabel">Account Name :</span>&nbsp;
                                                    <a class="navigationText" href="<s:url action="../accounts/getAccount"><s:param name="id" value="%{accountId}"/></s:url>"><s:property value="#session.accountName"/></a>
                                                    &nbsp;&nbsp;<span class="fieldLabel">Contact Name :</span>&nbsp;
                                                    <a class="navigationText" href="<s:url action="../contacts/getContact"><s:param name="id" value="%{contactId}"/></s:url>"><s:property value="#session.contactName"/></a>
                                                    &nbsp;&nbsp;<span class="fieldLabel">Activity Type :</span>&nbsp;
                                                    <a class="navigationText" href="<s:url action="../activities/getActivity.action"><s:param name="id" value="%{objectId}"/></s:url>"><s:property value="#request.activityType"/></a>
                                                </s:if>
                                                
                                                <s:else>
                                                    <span class="fieldLabel">Account Name :</span>&nbsp;
                                                    <a class="navigationText" href="<s:url action="../accounts/getAccount">
                                                           <s:param name="id" value="%{accountId}"/>
                                                       </s:url>"><s:property value="#session.accountName"/></a>
                                                    &nbsp;&nbsp;
                                                    
                                                    <span class="fieldLabel">Activity Type :</span>&nbsp;
                                                    <a class="navigationText" href="<s:url action="../activities/getActivity.action">
                                                           <s:param name="id" value="%{objectId}"/>
                                                       </s:url>">
                                                    <s:property value="#request.activityType"/></a>                                                
                                                </s:else>                                               
                                                
                                            </s:elseif>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td valign="top" style="padding-left:10px;padding-top:5px;">
                                            
                                            <ul id="accountTabs" class="shadetabs" >
                                                <li ><a href="#" class="selected" rel="attachment"  > Attachments </a></li>
                                                
                                            </ul>
                                            <!--//START TABBED PANNEL : -->
                                            <div  style="border:1px solid gray; width:840px;height: 150px; overflow:auto; margin-bottom: 1em;">
                                                <%--  <sx:tabbedpanel id="attachmentPannel" cssStyle="width: 850px; height: 150px;padding-left:10px;padding-top:5px;" doLayout="true"> --%>
                                                <!--//START TAB : -->
                                                <%--    <sx:div id="attachment" label="Attachments" cssStyle="overflow:auto;"> --%>
                                                <div id="attachment" class="tabcontent"  >        
                                                    <s:form action="addAttachment" method="post" name="attachmentAdd" enctype="multipart/form-data" theme="simple" onsubmit="return checkField();">
                                                        <!--creates a dirctory of name attachments at the location c:/upload-->
                                                        <s:hidden name="objectType" value="%{objectType}"/>
                                                        <s:hidden name="activityType" value="%{activityType}"/>
                                                        <s:hidden name="objectId" value="%{objectId}"/>
                                                        <!-- for displaying action errors and field errors -->
                                           
                                                        <table width="100%" cellpadding="0" cellspacing="0" border="0">
                                                            
                                                            <tr>
                                                                <td colspan="6"  class="headerText">
                                                                    <s:property value="#request.resultMessage"/>
                                                                    <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel">File:</td>
                                                                <td align="left">
                                                                    <s:file name="upload" id="upload" label="File" cssClass="inputTextBlueLargeAccount" theme="simple"/>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel">Attachment Name:</td>
                                                                <td>
                                                                    <s:textfield name="attachmentName" id="attachmentName" cssClass="inputTextBlue" theme="simple" onchange="fieldLengthValidator(this);changeCase(this);"/>&nbsp;&nbsp;
                                                                    <s:submit align="right" value="UpLoad" cssClass="buttonBg"/>
                                                                </td>
                                                                
                                                            </tr>
                                                            
                                                        </table>
                                                        
                                                    </s:form>
                                                    
                                                    <%--  </sx:div > --%>
                                                </div>
                                                <!--//END TAB : -->
                                         
                                                <%--   </sx:tabbedpanel> --%>
                                            </div>
                                            <!--//END TABBED PANNEL : -->
                                            <script type="text/javascript">

var countries=new ddtabcontent("accountTabs")
countries.setpersist(false)
countries.setselectedClassTarget("link") //"link" or "linkparent"
countries.init()

                                            </script>
                                            
                                            <!--//START TABBED PANNEL : -->
                                            <ul id="accountTabs1" class="shadetabs" >
                                                <li ><a href="#" class="selected" rel="attachmentTab"  > AttachmentList </a></li>
                                                
                                            </ul>
                                            <div  style="border:1px solid gray; width:840px;height:300px; overflow:auto; margin-bottom: 1em;"> 
                                                <%-- <sx:tabbedpanel id="attachmentPannel2" cssStyle="width: 850px; height: 300px;padding-left:10px;padding-top:5px;" doLayout="true"> --%>
                                                
                                                <!--//START TAB : -->
                                                <%--     <sx:div id="attachmentTab" label="AttachmentList"> --%>
                                                <div id="attachmentTab" class="tabcontent"  >
                                                    
                                                    <%
                                                    try {
                                                        
                                                        intCurr = 1;
                                                        intSortOrd = 0;
                                                        
                                                        strSortOrd = "DSC";
                                                        
                                                        strTmp = request.getParameter("txtCurr");
                                                        
                                                        int attCounter = 0;
                                                        
                                                        if(request.getAttribute("objectId") != null){
                                                            objectId = Integer.parseInt(request.getAttribute("objectId").toString());
                                                        }
                                                        
                                                        if(request.getAttribute("objectType") != null){
                                                            objectType = (String)request.getAttribute("objectType");
                                                        }
                                                        
                                                        
                                                        try {
                                                            if (strTmp != null)
                                                                intCurr = Integer.parseInt(strTmp);
                                                        } catch (NumberFormatException NFEx) {
                                                            
                                                        } //catch
                                                        
                                                        // for lookup connection
                                                        connection = ConnectionProvider.getInstance().getConnection();
                                                        
                                                        strSortCol = request.getParameter("Colname");
                                                        strSortOrd = request.getParameter("txtSortAsc");
                                                        
                                                        if (strSortCol == null) strSortCol = "DateUploaded";
                                                        
                                                        if (strSortOrd == null) strSortOrd = "DESC";
                                                        
                                                        blnSortAsc = (strSortOrd.equals("ASC"));
                                                        
                                                        queryString ="SELECT Id,AttachmentName,AttachmentLocation,AttachmentFileName,DateUploaded FROM tblCrmAttachments";
                                                        
                                                        if((objectId != 0) || (objectType != null)){
                                                            queryString = queryString + " WHERE";
                                                        }
                                                        
                                                        if(objectId != 0 && attCounter == 0){
                                                            queryString = queryString + " ObjectId ="+objectId;
                                                            attCounter++;
                                                        }else if(objectId != 0 && attCounter !=0){
                                                            queryString = queryString + " AND ObjectId ="+objectId;
                                                        }
                                                        
                                                        if(objectType != null && attCounter == 0){
                                                            queryString = queryString + " ObjectType ='"+objectType+"'";
                                                        }else if(objectType != null && attCounter !=0){
                                                            queryString = queryString + " AND ObjectType ='"+objectType+"'";
                                                        }
                                                        
                                                        queryString = queryString + " ORDER BY DateUploaded";
                                                        
                                                        //out.println(queryString);
                                                    
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
                                                                    <grd:dbgrid id="tblCrmAttachments" name="tblCrmAttachments" width="100" pageSize="8" 
                                                                                currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                                dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                        
                                                                        <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                                       imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"/>
                                                                        
                                                                        
                                                                        <grd:textcolumn dataField="AttachmentFileName"  headerText="Attachment FileName"   width="30" sortable="true"/> 
                                                                        <grd:textcolumn dataField="AttachmentName"  headerText="Attachment Name" width="30" sortable="true"/>  
                                                                        <grd:datecolumn dataField="DateUploaded"  headerText="Date Uploaded"  dataFormat="MM-dd-yyyy HH:mm:SS" width="20" sortable="true"/>
                                                                        <grd:imagecolumn headerText="Download" width="4" HAlign="center" 
                                                                                         imageSrc="../../includes/images/download_11x10.jpg"
                                                                                         linkUrl="getAttachment.action?Id={Id}" imageBorder="0"
                                                                                         imageWidth="11" imageHeight="10" alterText="Download"></grd:imagecolumn>
                                                                    </grd:dbgrid>
                                                                    <!-- these components are DBGrid Specific  -->
                                                                    <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                                    <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                                    <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                                                                    
                                                                </td>
                                                            </tr>
                                                        </table>    
                                                    </form>  
                                                    
                                                    <%
                                                    connection.close();
                                                    connection = null;
                                                    }catch(Exception se){
                                                        out.println(se.toString());
                                                    }finally{
                                                        if(connection!= null){
                                                            connection.close();
                                                            connection = null;
                                                        }
                                                    }
                                                    %>
                                                    <%--  </sx:div> --%>
                                                </div>
                                                <!--//END TAB : -->
                                    
                                                <%--     </sx:tabbedpanel> --%>
                                            </div>
                                            <!--//END TABBED PANNEL : -->
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
