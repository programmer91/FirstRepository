<!-- 
 *******************************************************************************
 *
 * Project : Mirage V2123
 *
 * Package :
 *
 * Date    :  July 18, 2014, 07:16 PM
 *
 * Author  : Santosh Kola uuuuu Katappa121 <skola2@miraclesoft.com>
 *
 * File    : ecertificationDomain.jsp
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
-->
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="com.freeware.gridtag.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd"%>
<%-- <%@ page import="com.mss.mirage.ecertification.EcertificationVTO"%> --%>
<%@ page import="java.util.List"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=8" /> 
        <title>Hubble Organization Portal :: Ecertification</title>
        <sx:head cache="false"/>
        
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        
        
        <link href="<s:url value="/includes/css/media/dataTables/demo_page.css"/>" rel="stylesheet" type="text/css" /> 
        <link href="<s:url value="/includes/css/media/dataTables/demo_table_jui.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/includes/css/media/themes/base/jquery-ui.css"/>" rel="stylesheet" type="text/css" media="all" />
        <link href="<s:url value="/includes/css/media/themes/smoothness/jquery-ui-1.7.2.custom.css"/>" rel="stylesheet" type="text/css" media="all" />
        
        <script src="<s:url value="/includes/javascripts/ecertification/jquery.js"/>" type="text/javascript"></script>
        <script src="<s:url value="/includes/javascripts/ecertification/jquery.dataTables.min.js"/>" type="text/javascript"></script>
        
        
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ecertification/ecertificationAjax.js"/>"></script>
        
        <script type="text/javascript">
        $(document).ready(function () {
            $("#topics").dataTable({
            "sScrollY": "250px",
            /*"sScrollX": "150px",*/
            "iDisplayLength" : 10, 
            "bLengthChange":true, 
                "bJQueryUI": true
            });
        });
        
           function guidelinesPopup(url) {
                newwindow=window.open(url,'name','height=600,width=500,top=200,left=250');
                if (window.focus) {newwindow.focus()}
           }
        </script>
        <link rel="stylesheet" type="text/css"  href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
    </head>
    <body class="bodyGeneral" oncontextmenu="return false;" onload="init()" id="dt_example">
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
                                <ul id="EcertTabs" class="shadetabs" >
                                    <li ><a href="#" class="selected" rel="issueTab"  >E-Certification </a></li>
                                </ul>
                                <!--//START TABBED PANNEL : -->
                                <div  style="border:1px solid gray; width:845px;height: 500px; overflow:auto; margin-bottom: 1em;">
                                    
                                    
                                    
                                    
                                    <%-- Exam Init div start --%>
                                         
                                    <div id="topicDiv" >
                                        
                                        <s:form name="ecertificationForm" id="ecertificationForm" action="getSubTopics" method="POST" enctype="multipart/form-data" theme="simple" onsubmit="return chekValues();">
                                            <table width="100%" cellpadding="3" cellspacing="3" border="0" >
                                                <tr>
                                                    <td  class="fieldLabel" >Domain&nbsp;: </td>
                                                    <td >  
                                                        <s:select label="Select Domain" id="domainId" 
                                                                  name="domainId" headerKey=""            
                                                                  headerValue="-- Please Select --"
                                                                  list="domainMap" cssClass="inputSelect" value="%{domainId}" onchange="getTopicData();"/>
                                                        
                                                    </td>
                                                    <td  class="fieldLabel" >Topic&nbsp;: </td>
                                                    <td >  
                                                        <s:select label="Select Domain" id="topicId" 
                                                                  name="topicId" headerKey="-1"            
                                                                  headerValue="-- Please Select --"
                                                                  list="topicsMap" cssClass="inputSelect" value="%{topicId}" />
                                                        
                                                    </td>
                                                    <td> <s:submit label="Submit" value="GetSubTopics" cssClass="buttonBg" /></td>
                                                </tr> 
                                                <tr>
                                                    <td colspan="6" class="fieldLabeforecert">
                                                        <%-- <s:property value="%{eflag}"/>--%>
                                                        <s:if test="%{eflag!=0}">
                                                            <%--   <s:include value="ecertificationList.jsp"/>  --%>
                                                            <div id="container">
                                                                <table id="topics" class="display" border="1">
                                                                    <thead>
                                                                        <tr>
                                                                            <th>START</th>
                                                                            <th>SUB-TOPIC</th>
                                                                        </tr>
                                                                    </thead>
                                                                    <tbody>
                                                                      <%-- <% 
                                                                        List subTopicsData= (List)session.getAttribute(ApplicationConstants.ECERT_SUBTOPICS_LIST);
                                                                        for(int i=0;i<subTopicsData.size();i++){
                                                                            EcertificationVTO c = (EcertificationVTO)subTopicsData.get(i);
                                                                        %>
                                                                        <tr>
                                                                            <td><a HREF="javascript:getInstructions(<%=c.getSubTopicId()%>)">Start Test</a></td>
                                                                            <td><%=c.getSubTopicName()%></td>
                                                                        </tr>
                                                                        <% } %> --%>
                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                        </s:if> 
                                                    </td>
                                                </tr>
                                            </table>
                                            
                                        </s:form>
                                    </div>
                                    
                                    
                                    
                                    
                                    <%-- exam div end --%>   
                             
                                         
                                    <%-- Instruction div ---%>
                                    <div id="instructionDiv"> 
                                        <s:form name="instructionForm" id="instructionForm" action="startExam" method="POST" theme="simple">
                                            <h3 align="center" style="color:darkblue;">Start Exam</h3>
                                            <table align="center" width="100%" cellpadding="0" cellspacing="0" border="0" >
                                                <tr>
                                                    <td>
                                                        <table align="center">
                                                            <tr>
                                                                <td class="fieldLabeforecert">
                                                                    <span>Domain :</span>&nbsp;
                                                                </td>
                                                                <td class="fieldLabeforecert">
                                                                    <label id="insdomainName"> </label>
                                                                    <s:hidden name="insdomainId" id="insdomainId"/>
                                                                    <s:hidden name="domainName" id="domainName" value=""/>
                                                                </td>
                                                                <%--  <td align="right" width="60%">
                                                              <a HREF="javaScript:guidelinesPopup('ecertificationGuideLines.jsp');" id="guideLines_Div" >Instructions</a>
                                                          </td>--%>
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabeforecert">
                                                                    <span>Topic :</span>&nbsp;
                                                                </td>
                                                                <td class="fieldLabeforecert">
                                                                    <label id="insTopicName"> </label>
                                                                    <s:hidden name="insTopicId" id="insTopicId"/>
                                                                    <s:hidden name="topicName" id="topicName" />
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabeforecert">
                                                                    <span>Sub Topic :</span>&nbsp;
                                                                </td>
                                                                <td class="fieldLabeforecert">
                                                                    <label id="inssubTopicName"></label>
                                                                    <s:hidden name="inssubTopicId" id="inssubTopicId"/>
                                                                    <s:hidden name="subTopicName" id="subTopicName" />
                                                                    
                                                                </td>
                                                            </tr>
                                                            
                                                            
                                                            
                                                            <tr>
                                                                <td class="fieldLabeforecert">
                                                                    <span>Total Questions :</span>&nbsp;
                                                                </td>
                                                                <td class="fieldLabeforecert">
                                                                    <label id="totalQuestions" name="totalQuestions"></label>
                                                                    <s:hidden name="totalQuest" id="totalQuest" />
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabeforecert">
                                                                    <span>Duration :</span>&nbsp;
                                                                </td>
                                                                <td class="fieldLabeforecert">
                                                                    <label id="duration" name="duration"></label>&nbsp;&nbsp;Mins
                                                                    <s:hidden name="durationTime" id="durationTime" />
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                
                                                                <td class="fieldLabeforecert">
                                                                    <s:submit value="Start Exam" cssClass="buttonBg" />
                                                                </td>
                                                            </tr>
                                                            
                                                        </table>
                                                </td></tr>
                                                <tr>
                                                    <td align="center">
                                                        
                                                        <font style="font-family: lucida-sans;color : darkblue;" size="3px">Instructions for submission of online exam form</font>	
                                                        
                                                        <table align="center">
                                                            <tr>
                                                                <td class="fieldLabeforecert">
                                                                    1. Don't click left menu links in middle of the exam.
                                                            </td></tr>
                                                            <tr>
                                                                <td class="fieldLabeforecert">
                                                                    2. Don't change your role in middle of the exam.
                                                            </td></tr>
                                                            <tr>
                                                                <td class="fieldLabeforecert">
                                                                    3. Don't refresh the page.
                                                            </td></tr>
                                                            <tr>
                                                                <td class="fieldLabeforecert">
                                                                    4. Each question carry 1 mark, no negative marks.
                                                            </td></tr>
                                                            <tr>
                                                                <td class="fieldLabeforecert">
                                                                    5. Test will be submitted automatically if the time expired.
                                                            </td></tr>
                                                            <%-- <tr>

                                                         <td  align="center">
                                                        <s:submit value="Start Exam" cssClass="buttonBg" />
                                                        </td> 
                                                        </tr>--%>
                                                        </table>
                                                    </td>
                                                </tr>
                                                
                                            </table>
                                        </s:form>
                                    </div>
                                    
                                    <%-- End of instructions div--%>
                                        
                                         
                                         
                                         
                                         
                                         
                                         
                                         
                                         
                                         
                                </div>                     
                                
                                
                                
                                <!--//END TABBED PANNEL : -->
                                <script type="text/javascript">

var countries=new ddtabcontent("EcertTabs")
countries.setpersist(false)
countries.setselectedClassTarget("link") //"link" or "linkparent"
countries.init()

                                </script>            
                                
                                
                                
                                
                                
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
