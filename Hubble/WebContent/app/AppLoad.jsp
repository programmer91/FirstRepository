<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="com.mss.mirage.util.DataServiceLocator"%>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="com.mchange.v2.c3p0.PooledDataSource"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>


<%--
 author:MrutyumjayaRao Chennu
 email:mchennu@miraclesoft.com
--%>
<html>
    <head>
        <title>Hubble Organization Portal :: Application Datasource Load </title>
        <sx:head cache="true"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
    </head>
    <body class="bodyGeneral"  oncontextmenu="return false;"><!-- onload="clock();" -->
        <%! DataSource datasource = null;%>
        <%--This is END for toopTip --%>
        
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
                    <table border="0" class="innerTableLogin" cellpadding="0" cellspacing="0">
                        
                        <tr>
                            <!-- START Animated Header -->
                            <td valign="top" height="150px">
                                <object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0" width="1000" height="150" id="header" align="middle">
                                    <param name="allowScriptAccess" value="sameDomain" />
                                    <param name="movie" value="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/flash/mirageBanner.swf" />
                                    <param name="quality" value="high" />
                                    <param name="bgcolor" value="#000000" />
                                    <embed src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/flash/mirageBanner.swf" quality="high" bgcolor="#000000" width="1000" height="150" name="header" align="middle" allowScriptAccess="sameDomain" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />
                                </object>    
                            </td>
                            <!-- END Animated Header -->
                        </tr>
                        
                        
                        <tr>
                            <td>
                                <%
                                datasource = DataServiceLocator.getInstance().getDataSource("jndi/mirageteam");
                                //datasource = DataServiceLocator.getInstance().getDataSource("jndi/mirage");
                                if(datasource instanceof PooledDataSource){
                                    PooledDataSource pooledDatasource = (PooledDataSource)datasource;
                                %>
                                
                                <table cellpadding="0" cellspacing="0" border="0" width="500px" align="center" class="borderHome">
                                    <col width="400px">
                                    <col width="100px">
                                    <tr>
                                        <td class="fieldLabelLeft">Number of Connections of Default User :</td>
                                        <td style="color:green;font-weight:bold;" align="center"><%=pooledDatasource.getNumConnectionsDefaultUser()%></td>
                                    </tr>
                                    
                                    <tr>
                                        <td class="fieldLabelLeft borderBlue">Number of Busy Connection of Default User:</td>
                                        <td style="color:red;font-weight:bold;" class="borderBlue" align="center"><%=pooledDatasource.getNumBusyConnectionsDefaultUser()%></td>
                                    </tr>
                                    
                                    <tr>
                                        <td class="fieldLabelLeft">Number of Idle Connection of Default User:</td>
                                        <td style="color:blue;font-weight:bold;" align="center"><%=pooledDatasource.getNumIdleConnectionsDefaultUser()%></td>
                                    </tr>
                                    
                                    <tr>
                                        <td class="fieldLabelLeft">Thread Pool Sise:</td>
                                        <td style="color:green;font-weight:bold;" align="center"><%=pooledDatasource.getThreadPoolSize()%></td>
                                    </tr>
                                    
                                    <tr>
                                        <td class="fieldLabelLeft borderBlue">Active Threads:</td>
                                        <td style="color:red;font-weight:bold;" class="borderBlue" align="center"><%=pooledDatasource.getThreadPoolNumActiveThreads()%></td>
                                    </tr>
                                    
                                    <tr>
                                        <td class="fieldLabelLeft">Idle Threads:</td>
                                        <td style="color:blue;font-weight:bold;" align="center"><%=pooledDatasource.getThreadPoolNumIdleThreads()%></td>
                                    </tr>
                                    
                                    <tr>
                                        <td class="fieldLabelLeft borderBlue">Number Of Connections All Users:</td>
                                        <td style="color:green;font-weight:bold;" class="borderBlue" align="center"><%=pooledDatasource.getNumConnectionsAllUsers()%></td>
                                    </tr>
                                    
                                    <tr>
                                        <td class="fieldLabelLeft">Number Of User Pools:</td>
                                        <td style="color:green;font-weight:bold;" align="center"><%=pooledDatasource.getNumUserPools()%></td>
                                    </tr>
                                    
                                </table>
                                <%}else{%>
                                <b>Given Datasource is not a c3p0 Pooled Datasource!</b>
                                <%}%>

                            </td>
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
