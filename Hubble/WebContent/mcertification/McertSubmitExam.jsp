<!-- 
 *******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  July 18, 2014, 07:16 PM
 *
 * Author  : Santosh Kola<skola2@miraclesoft.com>
 *
 * File    : examination.jsp
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
<%@ page import="com.mss.mirage.ecertification.ExamDetailInfoVTO"%>

<%@ page import="java.util.List"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Hubble Organization Portal :: Ecertification</title>
        <sx:head cache="false"/>
        <link rel="stylesheet" type="text/css"  href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
       
         <script type="text/javascript" src="https://www.google.com/jsapi"></script>
         
    <script type="text/javascript">
     // google.load("visualization", "1.0", {packages:["corechart"]});
    //  google.setOnLoadCallback(drawChart);

      function getChart(totalmarksobj,eMarksObj,eStatus){
        var wAns = parseInt(totalmarksobj.value)-parseInt(eMarksObj.value);
        //alert("wAns--->"+wAns);
         drawChart(wAns,eMarksObj.value,eStatus.value);
      }
function drawChart(totalmarksval,eMarksval,eStatus) {
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Topping');
        data.addColumn('number', 'Slices');
        data.addRows([['Wrong Answers', parseInt(totalmarksval)],['Correct Answers', parseInt(eMarksval)]]);
        
        var options = null;
       /* if(eStatus == 'PASS'){
            options = {'fontSize':'10','title':'My Exam Result','width':300,'height':250,'colors':['#499DF5','#008000'],'is3D':true,pieSliceTextStyle: {color: 'black'},pieSliceText:'value'};
        }else{
            options = {'fontSize':'10','title':'My Exam Result','width':300,'height':250,'colors':['#499DF5','#FF0000'],'is3D':true,pieSliceTextStyle: {color: 'black'},pieSliceText:'value'};
        }*/
        options = {'fontSize':'10','title':'My Exam Result','width':300,'height':250,'colors':['#FF0000','#008000'],'is3D':true,pieSliceTextStyle: {color: 'black'},pieSliceText:'value'};
        
        //alert("options----->"+options);
        var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
        chart.draw(data, options);
}


function disableStart(){
                 
                 document.getElementById("startExamButton").disabled = true;
                 document.getElementById("loadMsg").disabled = false;
                 document.getElementById("loadMsg").style.display = 'block';
              //   alert("before Submit");
                 document.forms["startExamForm"].submit();
               //  alert("after Submit");
             }
    </script>
    </head>
    <body class="bodyGeneral" oncontextmenu="return false;" onload="getChart(document.getElementById('totalQuest'),document.getElementById('examMarks'),document.getElementById('eStatus'))" >
 <!--//START MAIN TABLE : Table for template Structure-->
       
        <table class="templateTable1000x580" align="center" cellpadding="0" cellspacing="0">
            
            <!--//START HEADER : Record for Header Background and Mirage Logo-->
      <tr class="headerBg">
                <td valign="top">
                    <s:include value="/includes/template/McertHeader.jsp"/>
                </td>
            </tr>
            <!--//END HEADER : Record for Header Background and Mirage Logo-->
            
            <!--//START DATA RECORD : Record for LeftMenu and Screen Content-->
            <tr>
                <td>
                    <table class="innerTable1000x515" cellpadding="0" cellspacing="0">
                        <tr>
                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                           <%-- <td width="150px;" class="leftMenuBgColor" valign="top"> 
                                <s:include value="/includes/template/LeftMenu.jsp"/> 
                            </td>  --%>
                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                            
                            <!--//START DATA COLUMN : Coloumn for Screen Content-->
                               <td width="850px"  valign="top" style="padding:10px 5px 5px 5px;">
                                <ul id="accountTabs" class="shadetabs" >
                                    <li ><a href="#" class="selected" rel="empSearchTab"  > Exam Result</a></li>
    				</ul>
                                
                                
                                <%-- <sx:tabbedpanel id="empSearch" cssStyle="width: 845px; height: 500px;padding:10px 5px 5px 5px" doLayout="true"> --%>
                                <div  style="border:1px solid gray; width:845px;height: 580px; overflow:auto; margin-bottom: 1em;">
                                    <%--  <sx:div id="empSearchTab" label="Employee Search" cssStyle="overflow: auto;"> --%>
                                   
                                    
                                    
                                     <div id="empSearchTab" class="tabcontent" > 
                                        <%-- <s:form name="instructionForm" id="instructionForm" action="startExam" method="POST" theme="simple">
                                               <h3 align="center" style="color:darkblue;">Start Exam</h3>  --%>
                                              <table align="center" width="100%" cellpadding="0" cellspacing="0" border="0" >
                                                  <tr>
                                                                <td class="headerText">
                                                                <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                                </td>
                                                                </tr>
                                                <tr>
                                              <!--//START DATA COLUMN : Coloumn for Screen Content-->
                                                    <td width="850px"  valign="center" background="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/miragewatermark_850X600.gif">
                                                     <!--//START TABBED PANNEL : -->
                                                    <%--<sx:tabbedpanel id="resetPasswordPannel" cssStyle="width: 800px; height: 410px;padding-left:5px;padding-right:5px;padding-bottom:8px;" doLayout="true" > --%>
                                    
                                    <!--//START TAB : -->
                                   <%-- <sx:div id="resetPasswordTab" label="Reset Password">--%>
                                       <%-- <s:form action="resetPasswordSubmit" name="resetForm" theme="simple" onsubmit="return resetEmpPwdSubmit();">--%>
                                            <div>
                                                <table border="0" cellpadding="1" cellspacing="1" align="center" width="700px" height="450px" class="cellBorder" background="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/miragewatermark_850X600.gif">
                                                    
                                                    <tr>
                                                        <td valign="top" colspan="2">
                                                            <table width="500px" cellpadding="1" cellspacing="1" border="0" align="center">
                                                                <!--START ANIMATED TEXT ROW -->
                                                                
                                                                <tr>
                                                                    <td colspan="4">
                                                                        
                                                                        <h3 class="bgColor" align="center">
                                                                         <script type="text/JavaScript" src="<s:url value="/includes/javascripts/mcertification/mcertResult.js"/>"></script>
                                                                        </h3>
                                                                    </td>
                                                                </tr>  
                                                                <!--END ANIMATED TEXT ROW -->
                                                                
                                                                <tr>
                                                                    <td colspan="2" align="center" >
                                                                            <s:if test="%{examStatus=='PASS'}">
                                                                            <img alt="Congrtulations" src="<s:url value="/includes/images/ecertification/Congratulations_1.jpg"/>"/>
                                                                            </s:if>
                                                                            <s:elseif test="%{examStatus=='FAIL'}">
                                                                            <img alt="Sorry" src="<s:url value="/includes/images/ecertification/Sorry_1.jpg"/>"/>
                                                                            </s:elseif>
                                                                      </td>
                                                                </tr>
                                                                
                                                                
                                                                <tr>
                                                                    <td>
                                                                      <%-- Exam Result data Start --%>  
                                                                        <table>
                                                                            <tr>
                                                                                <td width="50%" colspan="2">
                                                                                    <table cellpadding="0" cellspacing="0" border="1" width="100%">
                                                                                    <tr>
                                                                                    <td colspan="2" class="bgColor">
                                                                                      <%--  <span class="fieldLabeforecertForResult">
                                                                                            Result:&nbsp;<s:property value="%{examMarks}"/>/<s:property value="%{totalQuest}"/>
                                                                                        </span> --%>
                                                                                      
                                                                                                 <%!
                                                                                                    double totalPercentage = 0.0;
                                                                                                %>
                                                                                        <span class="fieldLabeforecertForResult">
                                                                                            <%
                                                                                             totalPercentage = Math.round((Integer.parseInt(request.getAttribute("examMarks").toString())* 100.0)/Integer.parseInt(request.getAttribute("totalQuest").toString()));
                                                                                           %>
                                                                                          <%--  Result:&nbsp;<s:property value="%{examMarks}"/>/<s:property value="%{totalQuest}"/> --%>
                                                                                          Result:&nbsp;<%=totalPercentage%>&nbsp;%
                                                                                        </span>
                                                                                    </td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                    <td class="fieldLabel" width="50%"><span>Practice&nbsp;:</span>&nbsp;</td>
                                                                                    <td class="fieldLabelLeft" width="50%"><s:property value="%{domainName}"/></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                    <td class="fieldLabel" ><span>Exam&nbsp;:</span>&nbsp;</td>
                                                                                    <td class="fieldLabelLeft" ><s:property value="%{examType}"/></td>
                                                                                    </tr>
                                                                                   <%-- <tr>
                                                                                    <td class="fieldLabel" ><span>Topic&nbsp;:</span>&nbsp;</td>
                                                                                    <td class="fieldLabelLeft" ><s:property value="%{topicName}"/></td>
                                                                                    </tr>
                                                                                     --%>
                                                                                    <tr>
                                                                                    <td class="fieldLabel"><span>Total&nbsp;Questions&nbsp;:</span>&nbsp;</td>
                                                                                    <td class="fieldLabelLeft"><s:property value="%{totalQuest}"/>
                                                                                    <s:hidden name="totalQuest" id="totalQuest" value="%{totalQuest}"/>
                                                                                    </td>
                                                                                    </tr>
                                                                                  <%--  <tr>
                                                                                    <td class="fieldLabel"><span>Attempted&nbsp;Questions&nbsp;:</span>&nbsp;</td>
                                                                                    <td class="fieldLabelLeft"><s:property value="%{attemptedQuestions}"/></td>
                                                                                    </tr> --%>
                                                                                        <tr>
                                                                                    <td class="fieldLabel"><span>Minimum&nbsp;Marks&nbsp;:</span>&nbsp;</td>
                                                                                    <td class="fieldLabelLeft"><s:property value="%{minMarks}"/>
                                                                                    <s:hidden name="minMarks" id="minMarks" value="%{minMarks}"/>
                                                                                    </td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                    <td class="fieldLabel"><span>Marks&nbsp;Obtained&nbsp;:</span>&nbsp;</td>
                                                                                    <td class="fieldLabelLeft"><s:property value="%{examMarks}"/>
                                                                                    <s:hidden name="examMarks" id="examMarks" value="%{examMarks}"/>
                                                                                    </td>
                                                                                    </tr>
                                                                                     <tr>
                                                                                    <td class="fieldLabel"><span>Exam&nbsp;Status&nbsp;:</span>&nbsp;</td>
                                                                                    <td class="fieldLabelLeft">
                                                                                        <s:if test="%{examStatus=='PASS'}">
                                                                                            <font color="Green">
                                                                                       </s:if>
                                                                                        <s:elseif test="%{examStatus=='FAIL'}">
                                                                                        <font color="red">
                                                                                        </s:elseif>
                                                                                        <s:property value="%{examStatus}"/>
                                                                                       </font>
                                                                                    <s:hidden name="eStatus" id="eStatus" value="%{examStatus}"/>
                                                                                   
                                                                                    
                                                                                    </td>
                                                                                    </tr>
                                                                                      
                                                                                    </table>
                                                                                </td>
                                                                                <td align="right" valign="top">
                                                                                   <div id="chart_div" style="width: 400px; height: 150px;"></div>
                                                                                </td>
                                                                            </tr>
                                                                            <tr>
                                                                                                <td colspan="3" ><br><br><br>
                                                                                                    <font align="center" style="font-family: lucida-sans;color: darkblue;font-size: 16px;text-align: center;">
                                                                                                        <b> Section wise Result</b>
                                                                                                    </font>
                                                                                                </td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td colspan="3" align="center"><br>
                                                                                                <table cellpadding="0" cellspacing="0" border="1" width="100%">
                                                                                                <tr>
                                                                                                    <td class="bgColor"><span class="fieldLabeforecertForResult">SubTopic Name</span></td>
                                                                                                    <td class="bgColor"><span class="fieldLabeforecertForResult">Total Questions</span></td>
                                                                                                    <td class="bgColor"><span class="fieldLabeforecertForResult">Attempted Questions</span></td>
                                                                                                    <td class="bgColor"><span class="fieldLabeforecertForResult">Correct</span></td>
                                                                                                    <td class="bgColor"><span class="fieldLabeforecertForResult">Percentage</span></td>
                                                                                                </tr>
                                                                                                <%
                                                                                                List subTopicResultList = (List)request.getAttribute("examDetailInfoList");
                                                                                                 ExamDetailInfoVTO examDetailInfoVTO =null;
                                                                                                for(int i=0;i<subTopicResultList.size();i++) {
                                                                                                examDetailInfoVTO = (ExamDetailInfoVTO)subTopicResultList.get(i);
                                                                                                %>
                                                                                               <tr> <td class="fieldLabelLeft"><%
                                                                                                    out.println(examDetailInfoVTO.getSubtopicName());
                                                                                                    
                                                                                                %></td>
                                                                                                <td class="fieldLabelLeft"><%
                                                                                                    out.println(examDetailInfoVTO.getSubtopictotalQuestions());
                                                                                                    
                                                                                                %></td>
                                                                                                <td class="fieldLabelLeft"><%
                                                                                                    out.println(examDetailInfoVTO.getAttemptedQuestion());
                                                                                                    
                                                                                                %></td>
                                                                                                <td class="fieldLabelLeft"><%
                                                                                                    out.println(examDetailInfoVTO.getSubtopicMarks());
                                                                                                    
                                                                                            %></td>
                                                                                                
                                                                                                <td class="fieldLabelLeft"><%
                                                                                                //double percentage = examDetailInfoVTO.getSubtopicMarks()/examDetailInfoVTO.getSubtopictotalQuestions();
                                                                                                
                                                                                                double percent = Math.round((examDetailInfoVTO.getSubtopicMarks() * 100.0) / examDetailInfoVTO.getSubtopictotalQuestions());
                                                                                                
                                                                                                    out.println(percent+" %");
                                                                                                    
                                                                                            %></td>
                                                                                               </tr>
                                                                                            
                                                                                            <%}%>
                                                                                            
                                                                                        </table>
                                                                                </td>
                                                                            </tr>
                                                                            <s:if test="%{isLastExam=='NO'}">
                                                                                  <tr>
                                                                                      <td colspan="3" align="center">
                                                                                     <%--         <s:form action="/cre/startExam" theme="simple" id="startExamForm" name="startExamForm"> --%>
                                                                                     <s:form action="/mcertification/mcertStartExam" theme="simple" id="startExamForm" name="startExamForm">
                                                <table border="0" cellpadding="1" cellspacing="1" align="center" width="100%">
                                                  <tr>
                                                         <td class="fieldLabelLeft">Congrats! You have completed <s:property value="%{examType}"/> successfully.<br>Click button to start next exam</td>
                                                        <td >
                                                            <s:hidden name="examNumber" id = "examNumber" value="%{examNumber}"/>
                                                         <%--   <s:submit cssClass="buttonBg" value="Start Exam" id="startExamButton" onclick="javascript:disableStart();"/> --%>
                                                            <input type="button" class="buttonBg" value="Start Exam" id="startExamButton" onclick="disableStart();"/> 
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <div id="loadMsg" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                            <%-- <b id="loadMsg" style="display: none;" class="error" ><font color="red">Loading Please wait....</font></b> --%>
                                                        </td>
                                                    </tr>
                                                    
                                                </table>
                                            </s:form>
                                                                                      </td>
                                                                            </tr>
                                                                            </s:if><s:elseif test="%{ isLastExam=='YES'}">
                                                                                  <tr>
                                                                                      <td colspan="3" align="center" class="fieldLabelLeft">
                                                                                         Congrats! You have completed <s:property value="%{examType}"/> successfully.Please logout properly before leaving the system.
                                                                                      </td>
                                                                                  </tr>
                                                                            </s:elseif>
                                                                                  <s:elseif test="%{examStatus=='FAIL'}">
                                                                                    <tr>
                                                                                      <td colspan="3" align="center" class="fieldLabelLeft">
                                                                                         Thanks for your valuable time spend for us.Better luck next time . Please logout properly before leaving the system.
                                                                                      </td>
                                                                                  </tr>  
                                                                                  </s:elseif>
                                                                          
                                                                        </table>
                                                                        <%-- Exam result data end --%>
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        <br> </td>
                                                    </tr>
                                                   
                                                    
                                                   
                                                </table>
                                            </div>
                                       
                               <%--     </sx:div >  --%>
                                    <!--//END TAB : -->
                                    
                               <%-- </sx:tabbedpanel>--%>
                                <!--//END TABBED PANNEL : -->
                              
                            </td>
                            
                            
                            
                            <!--//END DATA COLUMN : Coloumn for Screen Content-->
                            </tr>
                            </table>
                             <%--    </s:form> --%>
                                     </div>
                                    
                                    
                                    <%--  </sx:tabbedpanel> --%>
 
                                </div>
                                    <script type="text/javascript">
                                    var countries=new ddtabcontent("accountTabs")
                                    countries.setpersist(false)
                                    countries.setselectedClassTarget("link") //"link" or "linkparent"
                                    countries.init()
                                    </script>
                                    <script type="text/javascript">
                            google.load("visualization", "1.0", {packages:["corechart"]});
                            </script> 
                            </td>
                            
                                  
                            <!--//END TABBED PANNEL : -->

                                                              
                                
                            
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