<%-- 
    Document   : TeamHierarchy
    Created on : May 6, 2016, 11:04:01 PM
    Author     : miracle
--%>


<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<html>
    <head>
        
        <title>Hubble Organization Portal :: Employee Hierarchy</title>
         <sx:head cache="true"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
        
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
         <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmployeeAjax.js"/>"></script>
        <script type="text/JavaScript">
            
          

        </script>
        
    </head>
    
    <body class="bodyGeneral">
           <script>
                                             <%--          function treeNodeSelected(nodeId) {
                                                            dojo.io.bind({
                                                                url: "<s:url value='/employee/myTeamTreeSelect.action'/>?nodeId="+nodeId,
                                                                load: function(type, data, evt) {
                                                                    var displayDiv = dojo.byId("displayId");
                                                                    displayDiv.innerHTML = data;
                                                                },
                                                                mimeType: "text/html"
                                                            });
                                                        };
                                                    dojo.event.topic.subscribe("treeSelected", this, "treeNodeSelected");
                                                    --%>
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
                            <td width="850px" class="cellBorder" valign="top" style="padding-left:10px;padding-top:5px;">
                                <!--//START TABBED PANNEL : -->
                            <%--    <span class="fieldLabel">Task Name :</span>&nbsp;
                             <s:url id="myUrl" action="getTask.action">
                            <s:param name="resM" value="0"/>
                            <s:param name="taskId" value="%{taskId}"/>
                            </s:url>
                           
                                <s:a href="%{myUrl}" cssClass="navigationText"><s:property value="%{title}"/></s:a> --%>
                                
                                <ul id="consultantTechReviewDetailsTabs" class="shadetabs" >
                                    <li ><a href="#" class="selected" rel="consultantTechReviewTab"  > Hierarchy</a></li>                                    
                                </ul>
                                <%-- <sx:tabbedpanel id="consultantTechReviewDetails" cssStyle="width: 840px; height: 310px;padding-left:10px;padding-top:5px;" doLayout="true"> --%>
                                <div  style="border:1px solid gray; width:840px;height: 500px; overflow:auto; margin-bottom: 1em;">   
                                    <!--//START TAB : -->
                                    <%--   <sx:div id="consultantTechReviewTab"  label="Consultant Tech Reviews"  > --%>
                                    <div id="consultantTechReviewTab" class="tabcontent"  >
                                         <table cellpadding="2" cellspacing="0" border="0" width="100%" theme="simple">                                                
                                                <tr align="right">
                                                    <td class="headerText" colspan="6">
                                                </tr> 
                                        <s:form name="employeeForm" action="getTeamHirearchy" theme="simple" method="POST" >
                                        
                                       
                                                    <tr>
                                                     <td  class="fieldLabel" width="200px" align="right">Department :</td>
                                                                   <td><s:select
                                                  name="departmentId" 
                                                  id="departmentId"
                                              list="{'Sales','GDC','Recruitment','Operations'}" cssClass="inputSelect" onchange="getReportsToDataV1();"/></td>
                                                                    <td class="fieldLabel" width="200px" align="right">Reports&nbspTo:</td>                           

                                <td><s:select
                                          name="reportsTo"
                                          id="reportsTo"
                                          headerKey=""
                                          headerValue="-- Please Select --"
                                          list="reportsToIdMap" 
                                          cssClass="inputSelectExtraLarge" 
                                          value="%{reportsTo}" 
                                          /></td> 
                                                                 
                                                    </tr>
                                                    <tr> <td colspan="3"></td> <td  align="left"> <s:submit cssClass="buttonBg" value="Search"/></td></tr>
                                        </s:form> <tr>
                                        <td colspan="4"><div >
                                                        <table><tr><td>
                                                   
                                                        <s:if test="%{departmentId=='Sales'}">
                                                        <sx:tree 
                                                            rootNode="%{teamTreeRootNode}"
                                                            childCollectionProperty="children"
                                                            nodeIdProperty="userId"
                                                            nodeTitleProperty="userName"
                                                            treeSelectedTopic="treeSelected">
                                                        </sx:tree>
                                                        </s:if><s:elseif test="%{departmentId=='GDC'}">
                                                             <sx:tree rootNode="%{teamTreeOfGDC}"
                                                                 childCollectionProperty="children"
                                                                 nodeIdProperty="userId"
                                                                 nodeTitleProperty="userName"
                                                                 treeSelectedTopic="treeSelected">
                                                        </sx:tree>
                                                        </s:elseif><s:elseif test="%{departmentId=='Recruitment'}">
                                                             <sx:tree 
                                                                rootNode="%{teamTreeOfRecruitment}"
                                                                childCollectionProperty="children"
                                                                nodeIdProperty="userId"
                                                                nodeTitleProperty="userName"
                                                                treeSelectedTopic="treeSelected">
                                                        </sx:tree>
                                                        </s:elseif><s:elseif test="%{departmentId=='Operations'}">
                                                             <sx:tree 
                                                                rootNode="%{teamTreeRootNodeOfOperations}"
                                                                childCollectionProperty="children"
                                                                nodeIdProperty="userId"
                                                                nodeTitleProperty="userName"
                                                                treeSelectedTopic="treeSelected">
                                                        </sx:tree>
                                                        </s:elseif>
                                                    
                                                 
                                                            </td><td valign="top">  <div id="displayId">
                                                    </div></td>
                                
                                </tr></table></div></td>
                                                    </tr>
                                                     </table>
                                    </div>
                                </div>
                                <script type="text/javascript">

                                                    var countries=new ddtabcontent("consultantTechReviewDetailsTabs")
                                                    countries.setpersist(false)
                                                    countries.setselectedClassTarget("link") //"link" or "linkparent"
                                                    countries.init()

                                </script>
                                <%-- </sx:div >--%>
                                    
                                <%-- </sx:tabbedpanel> --%>
                            </td>
                            <!--//END DATA COLUMN : Coloumn for Screen Content-->
                        </tr>
                    </table>
                </td>
            </tr>
            <!--//END DATA RECORD : Record for LeftMenu and Body Content-->
            
            <!--//START FOOTER : Record for Footer Background and Content-->
            <tr class="footerBg">
              <%--  <td align="center">&reg; 2007  Mirage - All Rights Reserved.</td> --%>
               <td align="center"><s:include value="/includes/template/Footer.jsp"/></td>
            </tr>
            <!--//END FOOTER : Record for Footer Background and Content-->
            
        </table>
        <!--//END MAIN TABLE : Table for template Structure-->
    </body>
</html>