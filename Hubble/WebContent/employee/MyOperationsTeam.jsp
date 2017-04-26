<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<html>
    <head>
        <title>Hubble Organization Portal :: My Operations Team</title>
         <sx:head cache="true"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        
        <s:include value="/includes/template/headerScript.html"/>
        
    </head>
    
    <body class="bodyGeneral" oncontextmenu="return false;">
        
        <script>
                                                    <%--    function treeNodeSelected(nodeId) {
                                                            dojo.io.bind({
                                                                url: "<s:url value='/employee/myTeamTreeSelect.action'/>?nodeId="+nodeId,
                                                                load: function(type, data, evt) {
                                                                    var displayDiv = dojo.byId("displayId");
                                                                    displayDiv.innerHTML = data;
                                                                },
                                                                mimeType: "text/html"
                                                            });
                                                        };

                                                    dojo.event.topic.subscribe("treeSelected", this, "treeNodeSelected"); --%>
                                                                    dojo.event.topic.subscribe("treeSelected", function treeNodeSelected(node) {
                                                    dojo.io.bind({
                                                        url: "<s:url value='/employee/myTeamTreeSelect.action'/>?name="+node.node.title+"&id="+node.node.widgetId,
                                                        load: function(type, data, evt) {
                                                            var divDisplay = dojo.byId("displayId");

                                                            divDisplay.innerHTML=data;
                                                        },
                                                        mimeType: "text/html"

                                                    });

                                                });
                                                        </script>
        
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
                            
                            <!--//END DATA COLUMN : Coloumn for LeftMenu-->
                            
                            <!--//START DATA COLUMN : Coloumn for Screen Content-->
                            <td width="850px" class="cellBorder" valign="top">
                                
                                
                                
                                
                                
                                <!--//START TABBED PANNEL : -->
                                <sx:tabbedpanel id="myTeamPannel" cssStyle="width: 840px; height: 500px;padding: 5px 5px 5px 5px;" doLayout="true">
                                    
                                    <!--//START TAB : -->
                                    <sx:div id="myTeamTab" label="My Team" cssStyle="overflow:auto;"  >
                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                            <tr align="right">
                                                <td class="headerText">
                                                    <img alt="Home" 
                                                         src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" 
                                                         width="100%" 
                                                         height="13px" 
                                                         border="0">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    
                                                    <div style="float:left; margin-right: 50px;">
                                                        <sx:tree 
                                                                rootNode="%{teamTreeRootNodeOfOperations}"
                                                                childCollectionProperty="children"
                                                                nodeIdProperty="userId"
                                                                nodeTitleProperty="userName"
                                                                treeSelectedTopic="treeSelected">
                                                        </sx:tree>
                                                    </div>
                                                    
                                                    <div id="displayId">
                                                        
                                                    </div>
                                                </td>
                                            </tr>
                                        </table>
                                    </sx:div >
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
                <td align="center"><s:include value="/includes/template/Footer.jsp"/></td>
            </tr>
            <!--//END FOOTER : Record for Footer Background and Content-->
            
        </table>
        <!--//END MAIN TABLE : Table for template Structure-->
        
        
        
    </body>
</html>
