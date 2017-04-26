<%-- 
    Document   : InvestmentBDMList
    Created on : Apr 12, 2016, 7:34:07 PM
    Author     : miracle
--%>


<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %> 
<%@ page import="com.freeware.gridtag.*" %> 
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd" %>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
     
            <title>Hubble Organization Portal :: Investment BDM List</title>
      
       
       <sx:head cache="true"/> 
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
       
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
     
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
 
     
   
  
    <%--  <script type="text/JavaScript" src='<s:url value="/includes/javascripts/GridNavigation.js"/>'></script>
   
      <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/jquery.min.js"/>"></script>
    <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/jquery.js"/>"></script>   
       
   <script type="text/JavaScript" src="<s:url value="/includes/javascripts/marketing/EventsPosting.js"/>"></script>   --%>
        
        <s:include value="/includes/template/headerScript.html"/>
        <%-- for issue reminder popup --%>
      
      
       <script>
            function investmentBDMPopup(url) {
                //newwindow=window.open(url,'name','height=350,width=200,top=200,left=250');
                newwindow=window.open(url,'name','height=300,width=350,top=200,left=250');
                if (window.focus) {newwindow.focus()}
            }
        </script>
  
    </head>
    <body class="bodyGeneral" oncontextmenu="return false;">
       

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
                            <td width="850px" class="cellBorder" valign="top" style="padding:5px 5px ;">
                                <!--//START TABBED PANNEL : -->
                                <%
                                    //out.print("naga::"+ request.getAttribute("subnavigateTo").toString());
                                 /// session.setAttribute(ApplicationConstants.ISSUE_TO_TASK,request.getAttribute("subnavigateTo").toString());
                                %>
                                
                                <ul id="accountTabs" class="shadetabs" >
                                 
                                <%--    <% if(request.getParameter("issueList")==null)
                                       {%> --%>
                                       
                                        <li ><a href="#" class="selected" rel="issuesTab"  > 
                                               
                                                    BDMs List
                                                
                                                
                                            </a></li>
                                        
                                       
                                 <%--   <%}else{%>
                                    <li ><a href="#" class="selected" rel="IssuesSearchTab">Tasks Search</a></li>
                                     <% }%> --%>
                                    
                                </ul>
                                 
                                
                                <%-- <sx:tabbedpanel id="IssuesPanel" cssStyle="width: 845px; height: 500px;padding:5px 5px;" doLayout="true"> --%>
                                <div  style="border:1px solid gray; width:845px;height: 500px; overflow:auto; margin-bottom: 1em;">
                                
                <div id="issuesTab" class="tabcontent" >
                       
                        
                        <s:form name="frmAccTeam" action="investmentBDMSubmit" theme="simple">
                            <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                <tr>
                                    <td class="headerText" align="right" colspan="2">
                                                                                <%
if(request.getAttribute("resultMessage")!=null)  {                                     
    out.println(request.getAttribute("resultMessage").toString());
}

%>
 <a href="<s:url action="leadGen"></s:url>" style="align:center;">
                                                                    <img alt="Back to List"
                                                                         src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                    width="66px" 
                                                                    height="19px"
                                                                    border="0" align="bottom"></a>
                                        <s:submit cssClass="buttonBg" value="Save"/>
                                        <%-- <s:hidden name="id" value="%{currentAccount.id}"/> --%>
                                        
                                        <s:hidden name="investmentId" id="investmentId" value="%{investmentId}"/>
                                        

                                    </td>
                                </tr>
                                <tr>
                                    <td class="fieldLabel">
                                       
                                            Investment&nbsp;Title:
                                       
                                       
                                    </td>
                                    <td><font style="color: green" size="2px;"><s:property value="%{investmentTitle}"/></font></td>
                                </tr>
                                
                                    <tr>
                                        <td  class="fieldLabel" valign="top"> 
                                            
                                                Assign BDMs
                                            
                                            
                                        </td>
                                        <td align="center">
                                            <s:optiontransferselect
                                                name="availableBdms"
                                                leftTitle="Available BDMs"
                                                rightTitle="Assigned BDMs"
                                                list="bdmsMapExceptInvestmentBDMMap"
                                                buttonCssClass="buttonBg"
                                                doubleName="assignedBdms"
                                                doubleList="investmentBDMsMap"
                                                cssClass="inputTextarea"
                                                
                                               >
                                            </s:optiontransferselect> 
                                      <h4 style="padding-left:50px;padding-top:5px;color:green;">
                                         
                                              Assigned BDMs Details:&nbsp;
                                         
                                          
                                         <img SRC="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/help.gif" onclick="javaScript:investmentBDMPopup('investmentBDMrDetailsList.action?investmentId=<%=request.getParameter("investmentId").toString()%>');" WIDTH=16 HEIGHT=16 BORDER=0 ALTER="Territory"></h4>     
                                        </td>
                                        
                                    </tr>
                                    
                               
                                
                            </table>   
                        </s:form>
                       
                        
                        
                        
                    </div>
                                    
                                    
                                  
                                    
                                    
                               
                                   
                  
                                </div>
                                    <!--//END TAB : -->
                                    <%--  </sx:tabbedpanel> --%>
                                
                                <!--//END TABBED PANNEL : -->
                                <script type="text/javascript">

var countries=new ddtabcontent("accountTabs")
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
                   
               
            
            <!--//END DATA RECORD : Record for LeftMenu and Body Content-->
            
            <!--//START FOOTER : Record for Footer Background and Content-->
            <tr class="footerBg">
                <td align="center"><s:include value="/includes/template/Footer.jsp"/>   </td>
            </tr>
             <tr>
                <td>
               <%--     
                    <div style="display: none; position: absolute; top:170px;left:320px;overflow:auto;" id="menu-popup">
                        <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" />
                    </div>
                    --%>
                </td>
            </tr>

            <!--//END FOOTER : Record for Footer Background and Content-->
            
        </table>
        <!--//END MAIN TABLE : Table for template Structure-->
        
        
        
        
        
    </body>
</html>
