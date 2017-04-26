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

<%@ page import="java.util.List"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=8" /> 
        <title>Hubble Organization Portal :: MCERT</title>
        <sx:head cache="false"/>
        <link rel="stylesheet" type="text/css"  href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        
        <%-- <link href="<s:url value="/includes/css/media/dataTables/demo_page.css"/>" rel="stylesheet" type="text/css" />  --%>
        
        <%-- <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ecertification/ecertificationAjax.js"/>"></script> --%>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/mcertification/mcertificationAjax.js"/>"></script>
         <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ecertification/CountDown.js"/>"></script>
     
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <script type="text/JavaScript">
              function disableButtons(){
                 
                 document.getElementById("submitButton").disabled = true;
                 document.getElementById("previous").disabled = true;
                 document.getElementById("next").disabled = true;
                 //document.getElementById("loadMsg").disabled = false;
                 //document.getElementById("loadMsg").style.display = 'block';
                 var totalQuestions = parseInt(document.getElementById("totalQuest").value);
                   for (var i=1;i<=totalQuestions;i++)
                    {
                   
                    document.getElementById("q"+i).disabled = true;
                    }
                 
             }
              function enableButtons(){
                 
                 document.getElementById("submitButton").disabled = false;
                 document.getElementById("previous").disabled = false;
                 document.getElementById("next").disabled = false;
                 //document.getElementById("loadMsg").disabled = false;
                 //document.getElementById("loadMsg").style.display = 'block';
                 var totalQuestions = parseInt(document.getElementById("totalQuest").value);
                 //totalQuest
                 for (var i=1;i<=totalQuestions;i++)
                    {
                    document.getElementById("q"+i).disabled = false;
                    }
             }
             
             
            function strtExamInit() {
                var t = document.getElementById("durationTime").value;
                t = parseInt(t*60);
                ActivateCountDown("CountDownPanel", t);
               // getQuestion(parseInt(0),parseInt(0),'N',0,0,0);
               getQuestion(parseInt(0),parseInt(0),'I',0,0,0,0);
            }
            
            
            function getNext() {
                            var isChecked = false;
            
            disableButtons();
            var subtopicId = parseInt(document.getElementById("subtopicId").value);
             var nextQid = parseInt(document.getElementById("questionId").value);
             var radios = document.getElementsByName('option');
             var checkedValue = 0;
                 for (var i = 0, length = radios.length; i < length; i++) {
                    if (radios[i].checked) {
                    // do whatever you want with the checked radio
                    checkedValue = radios[i].value;
                    // only one radio can be logically checked, don't check the rest
                    isChecked = true;
                    break;
                    }
                }
                var remQues = document.getElementById("hideremainingQuestions").value;
                getQuestion(nextQid,checkedValue,'N',1,remQues,subtopicId,0);
                enableButtons();
                 if(isChecked == true) {
                     
                // document.getElementById(nextQid).style.color="green";
                    document.getElementById("flag"+nextQid).innerHTML = '<img alt="Checked" src="/Hubble/includes/images/ecertification/green.png" width="12px" height="12px" border="0" >';
                    //document.getElementById("flagUnChecked"+nextQid).style.display = "none";
                    //document.getElementById("flagChecked"+nextQid).style.display = "block";
                }
            }
            
            function getPrevious() {
            disableButtons();
                        var isChecked = false;
            var subtopicId = parseInt(document.getElementById("subtopicId").value);
             var previoueQuestionNo = parseInt(document.getElementById("questionId").value);
             var radios = document.getElementsByName('option');
             var checkedValue = 0;
                 for (var i = 0, length = radios.length; i < length; i++) {
                    if (radios[i].checked) {
                    checkedValue = radios[i].value;
                    // only one radio can be logically checked, don't check the rest
                    isChecked = true;
                    break;
                    }
                }
                var remQues = document.getElementById("hideremainingQuestions").value;
                getQuestion(previoueQuestionNo,checkedValue,'P',1,remQues,subtopicId,0);
                enableButtons();
                if(isChecked == true) {
                    document.getElementById("flag"+previoueQuestionNo).innerHTML = '<img alt="Checked" src="/Hubble/includes/images/ecertification/green.png" width="12px" height="12px" border="0" >';
                    //document.getElementById(previoueQuestionNo).style.color="green";
                    //document.getElementById("flagUnChecked"+nextQid).style.display = "none";
                    //document.getElementById("flagChecked"+nextQid).style.display = "block";
                }
            }
             /*
             *Get specific Question start
             */
                function getSpecificQuestion(reqQuestion) {
               
            disableButtons();
            var isChecked = false;
            var subtopicId = parseInt(document.getElementById("subtopicId").value);
             var questionNo = parseInt(document.getElementById("questionId").value);
             var radios = document.getElementsByName('option');
             var checkedValue = 0;
                 for (var i = 0, length = radios.length; i < length; i++) {
                    if (radios[i].checked) {
                    checkedValue = radios[i].value;
                    // only one radio can be logically checked, don't check the rest
                    isChecked = true;
                    break;
                    }
                }
                var remQues = document.getElementById("hideremainingQuestions").value;
                getQuestion(questionNo,checkedValue,'R',1,remQues,subtopicId,reqQuestion);
                enableButtons();
                  if(isChecked == true) {
                    document.getElementById("flag"+questionNo).innerHTML = '<img alt="Checked" src="/Hubble/includes/images/ecertification/green.png" width="12px" height="12px" border="0" >';
                    //document.getElementById(questionNo).style.color="green";
                    //document.getElementById("flagUnChecked"+nextQid).style.display = "none";
                    //document.getElementById("flagChecked"+nextQid).style.display = "block";
                }
            }
            /*
             *Get Specific Question end
             */
         // function getsubmitForm(isTimeExpired){
               function getsubmitForm(){
             
              
              //if(isTimeExpired == false) {
                   var r=confirm("Do you want to submit?");
           if (r==true)
            {
            disableButtons();
            var subtopicId = parseInt(document.getElementById("subtopicId").value);
             var nextQid = document.getElementById("questionId").value;
             var radios = document.getElementsByName('option');
             var checkedValue = 0;
                 for (var i = 0, length = radios.length; i < length; i++) {
                    if (radios[i].checked) {
                    checkedValue = radios[i].value;
                    break;
                    }
                }
                var remQues = document.getElementById("hideremainingQuestions").value;
                getQuestion(nextQid,checkedValue,'S',1,remQues,subtopicId,0);
                //document.forms["ecertificationForm"].submit();
            }
             // }else {
                //  disableButtons();
                 // getQuestion(nextQid,checkedValue,'S',1,remQues,subtopicId);
              //}
            }
             window.history.forward();
             function noBack() { 
                  window.history.forward(); 
             }
             
             /**   refresh  Restriction java script */
            document.onkeydown=function(e) {
                e=e||window.event;
              if (e.keyCode === 116) {
                e.keyCode = 0;
                //alert("This action is not allowed");
                if(e.preventDefault)e.preventDefault();
                else e.returnValue = false;
                return false;
                }  
            }
            
        </script>
        
    </head>
      <body class="bodyGeneral" oncontextmenu="return false;" onload="strtExamInit();noBack();" onpageshow="if (event.persisted) noBack();" >
    
         <!--//START MAIN TABLE : Table for template Structure-->
       
        <table class="innerTable1000x515" align="center" cellpadding="0" cellspacing="0">
            
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
                            </td> --%>
                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                            
                           <td width="850px" class="cellBorder" valign="top" style="padding:10px 5px 5px 5px;">
                                <ul id="EcertTabs" class="shadetabs" >
                                    <li ><a href="#" class="selected" rel="EcertTab"  > MCERT Exam</a></li>
    				</ul>  
                                <%-- <sx:tabbedpanel id="empSearch" cssStyle="width: 845px; height: 500px;padding:10px 5px 5px 5px" doLayout="true"> --%>
                                <div  style="border:1px solid gray; width:990px;height: 490px; overflow:auto; margin-bottom: 1em;">
                                    <table border="0">
                                        <tr>
                                            <td>
                                                 <div id="EcertTab" class="tabcontent"  > 
                                          <s:form name="ecertificationForm" id="ecertificationForm" action="submitMcertExam" method="POST"  theme="simple" >
                                              <%-- <h3 align="center" style="color:darkblue;">Start Exam</h3>  --%>
                                              <table align="center" width="100%" cellpadding="4" cellspacing="4" border="0" >
                                                               <tr>
                                                    <td class="headerText" >
                                                        <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="1px" border="0">
                                                    </td>
                                                </tr>
                                                  <tr>
                                                      
                                                      <!--//START DATA COLUMN : Coloumn for Screen Content-->
                                                      <td width="850px"  valign="center" background="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/miragewatermark_850X600.gif">
                                                          <!--//START TABBED PANNEL : -->
                                                          
                                                          <table border="0" cellpadding="1" cellspacing="1" align="center" width="600px" height="300px"  background="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/miragewatermark_850X600.gif">
                                                              <%-- Start of Exam details  --%>
                                                 
                                                              <%-- Exam details --%>
                                                              <tr>
                                                                  <td valign="top" colspan="4">
                                                                      <table width="600px" cellpadding="0" cellspacing="0" border="0" align="center" >
                                                                          <!--START ANIMATED TEXT ROW -->
                                                                          <tr>
                                                                              <td colspan="6" >
                                                                                 
                                                                                  <%-- <div class="BgForEcert"> --%>
                                                                                  <div style="background-color:#048FD2;" align="center">
                                                                                       <b>
                                                                                            <font color="white" style="align:right;font-family: lucida-sans;font-size: 14px;font-style: normal;font-variant: normal;"> 
                                                                                        <table border="0">
                                                                                            <tr><td width="76%">
                                                                                                 
                                                                                              <%--  <span>Topic :&nbsp</span><s:property value="%{topicName}"/> --%>
                                                                                                <span>Exam :&nbsp</span><s:property value="%{examType}"/>
                                                                                                &nbsp;&nbsp;<s:hidden name="insTopicId" id="insTopicId" value="%{InsTopicId}"/>
                                                                                                <span style="align:right;">Section:</span>&nbsp;<span id="sectionName"></span>&nbsp;&nbsp;
                                                                                               <br>
                                                                                                <span style="align:right;">Remaining Questions:</span>&nbsp;<span id="remainingQuestions"></span>&nbsp;&nbsp;
                                                                                               
                                                                                                
                                                                                            
                                                                                            </td>
                                                                                            <td align="right" valign="top">
                                                                                                <span >Remaining&nbsp;Time&nbsp;:</span>&nbsp;<span id="CountDownPanel"></span>&nbsp;&nbsp;
                                                                                            </td>
                                                                                        </tr>
                                                                                        
                                                                                        </table>
                                                                                      </font>
                                                                                        </b>
                                                                                    </div>
                                                                                  <div style="background-color:#048FD2;" align="center">
                                                                                      <b>
                                                                                          <font color="white" style="align:right;font-family: lucida-sans;font-size: 14px;font-style: normal;font-variant: normal;"> 
                                                                                              <marquee direction="left" scrollamount="3">Don't refresh while writing the exam !</marquee>
                                                                                          </font>
                                                                                      </b>
                                                                                   </div>
                                                                              </td>
                                                                          </tr>
                                                                        
                                                                          <!--END ANIMATED TEXT ROW -->
                                                                
                                                                          <%-- Exam  Duration etc  --%>
                                                                          <tr>
                                                                              <td>
                                                                                  <s:hidden name="domainName" id="domainName" value="%{domainName}"/>
                                                                                  <s:hidden name="topicName" id="topicName" />
                                                                                  <s:hidden name="subTopicName" id="subTopicName" />
                                                                                  <s:hidden name="totalQuest" id = "totalQuest" value="%{totalQuest}"/>
                                                                                  <s:hidden name="durationTime" id="durationTime" value="%{durationTime}"/>
                                                                                  <s:hidden name="hideremainingQuestions" id = "hideremainingQuestions" value=""/>
                                                                                 <s:hidden name="examNumber" id = "examNumber" value="%{examNumber}"/>
                                                                                 <s:hidden name="examType" id="examType" value="%{examType}"/>
                                                                              </td>  
                                                                          </tr>    
                                                                          <%-- Exam Duration end --%>
                                                                          <tr>
                                                                              <td colspan="6" >
                                                                                  <%-- Exam Data Start --%>
                                                                                  
                                                                                      <table  cellpadding="0" class="cellBorderForExamTable" cellspacing="0" border="0" align="center" >
                                                                                          <tr valign="top">
                                                                                              
                                                                                                      <td>
                                                                                                         
                                                                                                          <div style="overflow:auto;">
                                                                                                      <table border="0" class="cellBorderForInnerExamTable">
                                                                                                          <tr>
                                                                                                           
                                                                                                             
                                                                                                      <td class="fieldLabeforQuestion" colspan="2" valign="top" >
                                                                                                          
                                                                                                          <br>
                                                                                                          <span id="ques"></span><span>.</span>&nbsp;<span id="qname">Q</span><br>
                                                                                                          <s:hidden name="questionId" id = "questionId" value=""/>
                                                                                                          <s:hidden name="subtopicId" id = "subtopicId" value=""/>
                                                                                                          
                                                                                                          <input type="radio" name="option" value="1" ><span id="opt1"></span><br>
                                                                                                          <input type="radio" name="option" value="2" ><span id="opt2"></span><br>
                                                                                                          <input type="radio" name="option" value="3" ><span id="opt3"></span><br>
                                                                                                          <input type="radio" name="option" value="4" ><span id="opt4"></span><br>
                                                                                                  </td></tr>
                                                                                                  <tr>
                                                                                                      <td align="left">
                                                                                                         
                                                                                                          &nbsp;&nbsp;<input type="button" value="Submit Exam" onclick="getsubmitForm()" Class="buttonBg" id="submitButton"/>
                                                                                                      </td>
                                                                                                      <td align="right">
                                                                                                          <input type="button" value="Previous" Class="buttonBg" onclick="getPrevious()" id="previous"/>
                                                                                                          <input type="button" value="Next" Class="buttonBg" onclick="getNext()" id="next" /> &nbsp;&nbsp;
                                                                                                      </td>
                                                                                                  </tr>
                                                                                                  
                                                                                                      </table>
                                                                                                      </div>
                                                                                              </td>
                                                                                              
                                                                                          </tr>
                                                                                          <%-- Exam Data END --%>
                                                                                      </table>
                                                                              
                                                                              </td>
                                                                          </tr>
                                                                      </table>
                                                                      
                                                                      
                                                                      <%--  </sx:div > --%>
                                                                      <!--//END TAB : -->
                                    
                                                                      <%--  </sx:tabbedpanel>--%>
                                                                      <!--//END TABBED PANNEL : -->
                              
                                                                  </td>
                                                                  <!--//END DATA COLUMN : Coloumn for Screen Content-->
                            
                            
                                                              </tr>
                                                              
                                                          </table>
                                                      </td>
                                                  </tr>
                                              </table>
                                          </s:form>
                                     </div>
                                            </td>
                                            <td>
                                                 <div style="overflow:auto;width:130px; height: 490px;border:1px solid #B2B3CE;">
                                                   <%-- <font style="align:left;font-family: lucida-sans;font-size: 8px;font-style: normal;font-variant: normal;">    --%>
                                                        <table cellpadding="2" cellspacing="1" border="0">
                                                            
                                                           <tr>
                                                                                                              <td  align="left" width="35px" >
                                                                                                                  <img alt="UnChecked" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/ecertification/red.png" width="12px" height="12px" border="0">
                                                                                                              </td>
                                                                                                              <td class="fieldLabelLeft">
                                                                                                                  Not&nbsp;Attempted
                                                                                                              </td>
                                                                                                          </tr>
                                                                                                           <tr>
                                                                                                              <td  align="left" width="35px" >
                                                                                                                  <img alt="Checked" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/ecertification/green.png" width="12px" height="12px" border="0">
                                                                                                              </td>
                                                                                                              <td class="fieldLabelLeft">
                                                                                                                  Attempted
                                                                                                              </td>
                                                                                                          </tr> 
                                                        </table>
                                                                                                              <hr>
                                               <%--     </font> --%>
                                                                                                              <table cellpadding="2" cellspacing="1" border="0">
                                                                                                          <%--    <s:iterator value="#request.currentQuestionsCollection"> --%>
                                                                                                         <%-- <tr class="gridRowEven">
                                                                                                              <td  align="left" width="70px" colspan="2">
                                                                                                                  <img alt="UnChecked" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/ecertification/red.png" width="12px" height="12px" border="0">Not&nbsp;Attempted
                                                                                                              </td>
                                                                                                          </tr>
                                                                                                           <tr class="gridRowEven">
                                                                                                              <td  align="left" width="70px" colspan="2">
                                                                                                                  <img alt="UnChecked" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/ecertification/green.png" width="12px" height="12px" border="0">Attempted
                                                                                                              </td>
                                                                                                          </tr> --%>
                                                                                                          <s:iterator value="#session.sessionMcertQuestionsMap">
                                                                                                                  <tr class="gridRowEven">
                                                                                                                   <%--   <td class="gridColumn" align="left"><a href="javascript:changeStatus(<s:property value="authorId"/>,<s:property value="topicId"/>,'<s:property value="authorLoginId"/>');" ><s:property value="key"/></a></td>--%>
                                                                                                                   <td  align="left" width="35px"><span id="flag<s:property value="key"/>"><img alt="UnChecked" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/ecertification/red.png" width="12px" height="12px" border="0"></span></td> <td  align="left" width="35px"><a href="javascript:getSpecificQuestion(<s:property value="key"/>);" id="q<s:property value="key"/>"><s:property value="key" /></a></td> 
                                                                                                             <%--    <td  align="left" width="35px"><a href="javascript:getSpecificQuestion(<s:property value="key"/>);" id="<s:property value="key"/>" style="color:red"><s:property value="key" /></a></td> --%>
                                                                                                                  </tr>
                                                                                                              </s:iterator>
                                                                                                                  </table>
                                                                                                          </div> 
                                            </td>
                                        </tr>
                                    </table>
                                    
                                                                 <%--  <td width="50px" class="cellBorder" valign="top"> --%>
                                                                                                                  
                                                                                                            <%--   </td> --%>
                                    
                                    <%--  </sx:tabbedpanel> --%>
 
                                </div>
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
