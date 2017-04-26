<!-- 
 *******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  November 08, 2013, 3:25 PM
 *
 * Author  :  Ajay Tummapala
 *
 * File    : TechReviews.jsp
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.`
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
-->
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>

<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ page import="com.freeware.gridtag.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="com.mss.mirage.employee.tasks.TasksVTO"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hubble Organization Portal :: Technical Reviews Consultant</title>
        <%--  <sx:head cache="true"/> 
           <sj:head jqueryui="true"/>--%>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tooltip.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tooltip.js"/>"></script>   
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>   
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>   


        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>

        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>

        <script type="text/javascript" src="<s:url value="/includes/javascripts/recruitment/ConsultantTechReviewsClientValidation.js"/>"></script>


        <script type="text/javascript">
            function showRating()
            {
                var selectedValue=document.getElementById("operationType").value;
                //alert("selectedvalue"+selectedValue);
                if(selectedValue=="Review")
                {
                    document.getElementById("ratingtr").style.display='table-row';
                    document.getElementById("refertr").style.display='none';
                    document.getElementById("referNametr").style.display='none';
        
                    // return false;
                }
                else if(selectedValue=="Forward")
                {
                    document.getElementById("refertr").style.display='table-row';
                    document.getElementById("ratingtr").style.display='none';
                    document.getElementById("referNametr").style.display='table-row';
                    //return false;
                }
                else if(selectedValue=="Review & Forward")
                {
                    document.getElementById("refertr").style.display='table-row';
                    document.getElementById("ratingtr").style.display='table-row';
                    document.getElementById("referNametr").style.display='table-row';
                    //return false;
                }
    
    
            }
            //    function validateFields()
            //    {
            //    //alert("lhkhkj");
            //      var selectedValue=document.getElementById("operationType").value;
            //   // var rating;
            //    var email;
            //      var comments=document.getElementById("comments").value;
            //      
            //   // alert("lhkhkj"+selectedValue);
            //      if(selectedValue==null || selectedValue=="")
            //          {
            //              alert("Please Select the Review Type");
            //              return false;
            //          }
            //       else if(selectedValue=="Review")
            //           {
            //             //  alert("review");
            //                var rating=document.frmConsultantForm.rateing.value;
            //               // alert("djfgdhgfdfh"+rating);
            //              if(rating==null||rating=="")
            //                  {
            //                      alert("Please give rating");
            //                      return false;
            //                  }
            //               else if(comments==null || comments=="")
            //                   {
            //                       alert("Please give comments");
            //                       return false;
            //                   }
            //           }
            //        else if(selectedValue == "Forward")
            //           {
            //               email=document.getElementById("referTo").value;
            //               var techName = document.getElementById("techName").value;
            //              if(email==null || email=="")
            //                  {
            //                      alert("Please give an email id");
            //                      return false;
            //                  }
            //              else if(techName==null || techName=="")
            //                  {
            //                      alert("Please give Techie Name");
            //                      return false;
            //                  }   
            //             else if(comments==null || comments=="")
            //                  {
            //                      alert("Please give comments");
            //                      return false;
            //                  }
            //          }
            //          else if(selectedValue == "Review & Forward")
            //              {
            //                    email=document.getElementById("referTo").value;
            //                     var rating=document.frmConsultantForm.rateing.value;
            //                     var techName = document.getElementById("techName").value;
            //               // alert("djfgdhgfdfh"+rating);
            //              if(rating==null||rating=="")
            //                  {
            //                      alert("Please give rating");
            //                      return false;
            //                  }
            //                if(techName==null || techName=="")
            //                  {
            //                      alert("Please give Techie Name");
            //                      return false;
            //                  } 
            //              if(email==null || email=="")
            //                  {
            //                      alert("Please give an email id");
            //                      return false;
            //                  }
            //             else if(comments==null || comments=="")
            //                  {
            //                      alert("Please give comments");
            //                      return false;
            //                  }
            //              }
            //                      return true;
            //}

            function validateFields()
            {
                //alert("lhkhkj");
                var status=document.getElementById("status").value;
                // var rating; status
                var email;
                var comments=document.getElementById("comments").value;
      
                // alert("lhkhkj"+selectedValue);
                if(status==null || status=="-1")
                {
                    alert("Please Select the Status");
                    return false;
                }
                if(comments==null || comments=="")
                {
                    alert("Please give comments");
                    return false;
                }

                return true;
            }

            function checkEmail(element)
            {
                if(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(element.value)){return(true);
                }element.value="";
                alert("Invalid E-mail Address! Please re-enter.");
                return(false);
            } 
 
            function allowAnswer(evt)
            {
                //var rateing=document.getElementById("rateing").value;
                var rateing=evt.value;

                if(rateing==1 || rateing==2 || rateing==3 || rateing==4 || rateing==5 || rateing==6 || rateing==7 || rateing==8 || rateing==9 || rateing==10)
                {
                    return true;
                }
                else
                {
                    //document.getElementById("rateing").value="";
                    evt.value="0";
                    alert("Rating should be between 1 to 10.");
                    return false;
                }
            };
            function fieldLenghtValidator(element){
                //var totMarks=document.getElementById("totMarks").value;
                //var minMarks=document.getElementById("minMarks").value;

                var i = 0;
                if (element.value != null && (element.value != "")) {
                    //alert("In if standard client validation");
                    if(element.id == 'comments') i = 1000;
                    if(element.id == 'techName') i=100;
            
                    // else if(element.id == 'totQuestions' || element.id == 'totDuration' || element.id == 'totMarks' || element.id == 'minMarks') i = 3;
                    //else if(element.id == 'question') i = 1000;
                    // else if(element.id == 'option1' || element.id == 'option2' || element.id == 'option3' || element.id == 'option4') i = 500;
                    // else if(element.id == 'description') i = 1000;
      
                    if(element.value.replace(/^\s+|\s+$/g,"").length>i){
                        //subStringValue(i,element,"The "+element.id+" must be less than "+i+" characters");
                        str = new String(element.value);
                        element.value=str.substring(0,i);
            
                        alert("The "+element.id+" must be less than "+i+" characters");
                        element.focus();
                        return false;
                    }
                }
            };
        </script>


        <style type="text/css">

            .popupItem:hover {
                background: #F2F5A9;
                font: arial;
                font-size:10px;
                color: black;
            }

            .popupRow {
                background: #3E93D4;
            }

            .popupItem {            
                padding: 2px;
                width: 100%;
                border: black;
                font:normal 9px verdana;
                color: white;
                text-decoration: none;
                line-height:13px;
                z-index:100;
            }

        </style>



    </head>

    <%!
        /*
         * Declarations
         */
        Connection connection;
        String accountPrimaryTeamMember;
        String userId;
        String userRoleName;
        int isUserManager;
        String queryString;
        String currentAccountId;
        String strTmp;
        String strSortCol;
        String strSortOrd;
        int intSortOrd = 0;
        int intCurr;
        boolean blnSortAsc = true;
    %>
    <!-- <body class="bodyGeneral"  oncontextmenu="return false;" onload="init();">  -->
    <body class="bodyGeneral"  oncontextmenu="return false;"> 

        <!-//START MAIN TABLE : Table for template Structure-->
        <table class="templateTable1000x580" align="center" cellpadding="0" cellspacing="0">

            <!--//START HEADER : Record for Header Background and Mirage Logo-->
            <tr class="headerBg">
                <td valign="top">
                    <s:include value="/includes/template/Header.jsp" />                    
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
                            <td width="850px" class="cellBorder" valign="top" style="padding:5px 5px;">
                                <ul id="accountTabs" class="shadetabs" >

                                    <li ><a href="#" class="selected" rel="issueTab"  >Consultant Details </a></li>
                                </ul>
                                <!--//START TABBED PANNEL : -->
                                <div  style="border:1px solid gray; width:830px;height: 500px; overflow:auto; margin-bottom: 0em;">
                                    <!--//START TAB : -->
                                    <div id="issueTab" class="tabcontent"  >
                                        <s:form name="frmConsultantForm" action="technicalReviewSubmit" theme="simple" onsubmit="return validateFields();">
                                            <s:hidden name="consultantId" value="%{consultantId}"/>
                                            <s:hidden name="id" value="%{id}"/>
                                            <%-- <s:hidden name="status" value="%{status}"/>  --%>
                                            <s:hidden name="requirementId" value="%{requirementId}"/>
                                            <s:hidden name="recConsultantId" value="%{recConsultantId}"/>
                                            <s:hidden name="interveiwType" value="%{interveiwType}"/>


                                            <table  align="center" border="0">
                                                <% if (request.getAttribute("resultMessage") != null) {
                                                        out.println(request.getAttribute("resultMessage"));
                                                        session.removeAttribute("resultMessage");
                            }%>
                                                <tr>
                                                    <td class="fieldLabelLeft" colspan="3"><b>Consultant Details for Tech Reviews :</b></td>
                                                    <td align='right'>
                                                        <a href="<s:url value="consultantTechReviews.action"/>" style="align:center;">
                                                            <img alt="Back to List"
                                                                 src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                 width="66px" 
                                                                 height="19px"
                                                                 border="0" align="bottom"></a>
                                                    </td>
                                                </tr>

                                                <tr>
                                                    <td class="fieldLabel">Consultant Name:</td>
                                                    <td class="userInfoLeft" style="width:1%"><s:property value="%{fiName}"/></td>
                                                    <s:hidden name="fiName" value="%{fiName}"/>
                                                    <s:hidden name="email2" value="%{email2}"/>
                                                    <s:hidden name="cellPhoneNo" value="%{cellPhoneNo}"/>
                                                    <s:hidden name="titleTypeId" value="%{titleTypeId}"/>
                                                    <td class="fieldLabel"  style="width:1%">Title :</td>
                                                    <td class="userInfoLeft"><s:property value="%{titleTypeId}"/></td>
                                                </tr>

                                                <tr>
                                                    <td class="fieldLabel">Email:</td>
                                                    <td class="userInfoLeft"><s:property value="%{email2}"/></td>
                                                    <td class="fieldLabel"  style="width:1%">Mobile&nbsp;No:</td>
                                                    <td class="userInfoLeft"><s:property value="%{cellPhoneNo}"/></td>
                                                </tr>
                                                
                                                <tr>
                                                    <td class="fieldLabel">Current Employer:</td>
                                                    <td class="userInfoLeft"><s:property value="%{currentEmployer}"/></td>
                                                    <td class="fieldLabel">Scheduled&nbsp;Date:</FONT></td>
                                                    <td class="userInfoLeft" colspan="2"><s:property value="%{scheduledDate}"/>&nbsp;<s:property value="scheduledTime"/>&nbsp;<s:property value="timeFormat"/>&nbsp;<s:property value="zone"/></td>
                                              
                                                    
                                                </tr>
                                                <tr>
                                                    <td class="fieldLabel">Available From:</td>
                                                    <td class="fieldLabelLeft"><s:property value="%{availableFrom}"/></td>
                                                    <td class="fieldLabel"  style="width:1%">Work&nbsp;Authorization:</td>
                                                    <td class="fieldLabelLeft"><s:property value="%{workAuthorization}"/></td>
                                                </tr>
                                                <tr>
                                                    <td class="fieldLabel">Download Resume :</td>
                                                    <td>
                                                        <a href="<s:url value="../getAttachmentForReview.action"/>?id=<s:property value="%{consultantId}"/>&consultantId=Download" >
                                                            <img src="<s:url value="../../includes/images/download_large.gif"/>" height="20" width="30"/>
                                                        </a>    
                                                    </td>
                                                    <td class="fieldLabel">Interview Type:</td>
                                                    <td class="userInfoLeft"><s:property value="%{interveiwType}"/></td>
                                                </tr>
                                                <%-- <tr>
                                                     <td class="fieldLabel" >Review Type :<FONT color="red"  ><em>*</em></FONT></td> 
                                                     <td ><s:select id="operationType" name="operationType" headerKey="" headerValue="--Please Select--" list="{'Review','Forward','Review & Forward'}"  onchange="javascript:showRating();" cssClass="inputSelect"/></td>
                                                 </tr> --%>
                                                
                                                <tr id="ratingtr" style="display:none;">
                                                    <td class="fieldLabel">Rating :<FONT color="red"  ><em>*</em></FONT></td>
                                                    <td colspan="3"><s:textfield name="rateing" cssClass="inputTextBlueCustomer" id="rateing" value="" style="width:25px" onchange="allowAnswer(this);"/><s:label cssClass="fieldLabelLeft" value="("/><FONT color="red"  >*</FONT><FONT color="red"  >*</FONT>&nbsp;<s:label cssClass="fieldLabelLeft" value="Rating between 1 to 10)"/></td>

                                                </tr>
                                                <tr id="referNametr" style="display:none;">
                                                    <td class="fieldLabel">Techie Name :<FONT color="red"  ><em>*</em></FONT></td>
                                                    <td colspan="3"><s:textfield cssClass="inputTextBlueLarge" name="techName" id="techName" value="" onchange="fieldLenghtValidator(this);"/></td>
                                                </tr>
                                                <tr id="refertr" style="display:none;">
                                                    <td class="fieldLabel">Techie Email :<FONT color="red"  ><em>*</em></FONT></td>
                                                    <td colspan="3"><s:textfield cssClass="inputTextBlueLarge" name="referTo" id="referTo" value="" onchange="checkEmail(this);"/></td>
                                                </tr>
                                                <tr>
                                                    <td class="fieldLabel">Skills:</td>
                                                    <td class="userInfoLeft"><%--<s:property value="%{skills}"/>--%></td>

                                                      </tr>
                                                <s:if test="%{skill1 != ''}">
                                                    <tr>
                                                        <td class="fieldLabel"><s:property value="%{skill1}"/></td><td><s:textfield name="rating1" cssClass="inputTextBlueCustomer" id="techSkills" value="%{rating1}" style="width:25px" onchange="allowAnswer(this);"/></td>
                                                        <s:if test="%{skill2 != ''}">
                                                            <td class="fieldLabel"><s:property value="%{skill2}"/></td><td><s:textfield name="rating2" cssClass="inputTextBlueCustomer" id="techSkills" value="%{rating2}" style="width:25px" onchange="allowAnswer(this);"/></td>
                                                        </s:if>
                                                    </tr>
                                                </s:if>
                                                <s:if test="%{skill3 != ''}">
                                                    <tr>
                                                        <td class="fieldLabel"><s:property value="%{skill3}"/></td><td><s:textfield name="rating3" cssClass="inputTextBlueCustomer" id="techSkills" value="%{rating3}" style="width:25px" onchange="allowAnswer(this);"/></td>
                                                        <s:if test="%{skill4 != ''}">
                                                            <td class="fieldLabel"><s:property value="%{skill4}"/></td><td><s:textfield name="rating4" cssClass="inputTextBlueCustomer" id="techSkills" value="%{rating4}" style="width:25px" onchange="allowAnswer(this);"/></td>
                                                        </s:if>
                                                    </tr>
                                                </s:if>
                                                <s:if test="%{skill5 != ''}">
                                                    <tr>
                                                        <td class="fieldLabel"><s:property value="%{skill5}"/>5</td><td><s:textfield name="rating5" cssClass="inputTextBlueCustomer" id="techSkills" value="%{rating5}" style="width:25px" onchange="allowAnswer(this);"/></td>
                                                        <s:if test="%{skill6 != ''}">
                                                            <td class="fieldLabel"><s:property value="%{skill6}"/></td><td><s:textfield name="rating6" cssClass="inputTextBlueCustomer" id="techSkills" value="%{rating6}" style="width:25px" onchange="allowAnswer(this);"/></td>
                                                        </s:if>
                                                    </tr>
                                                </s:if>
                                                <s:if test="%{skill7 != ''}">
                                                    <tr>
                                                        <td class="fieldLabel"><s:property value="%{skill7}"/></td><td><s:textfield name="rating7" cssClass="inputTextBlueCustomer" id="techSkills" value="%{rating7}" style="width:25px" onchange="allowAnswer(this);"/></td>
                                                        <s:if test="%{skill8 != ''}">
                                                            <td class="fieldLabel"><s:property value="%{skill8}"/></td><td><s:textfield name="rating8" cssClass="inputTextBlueCustomer" id="techSkills" value="%{rating8}" style="width:25px" onchange="allowAnswer(this);"/></td>
                                                        </s:if>
                                                    </tr>
                                                </s:if>
                                                <s:if test="%{skill9 != ''}">
                                                    <tr>
                                                        <td class="fieldLabel"><s:property value="%{skill9}"/></td><td><s:textfield name="rating9" cssClass="inputTextBlueCustomer" id="techSkills" value="%{rating9}" style="width:25px" onchange="allowAnswer(this);"/></td>
                                                        <s:if test="%{skill10 != ''}">
                                                            <td class="fieldLabel"><s:property value="%{skill10}"/></td><td><s:textfield name="rating10" cssClass="inputTextBlueCustomer" id="techSkills" value="%{rating10}" style="width:25px" onchange="allowAnswer(this);"/></td>
                                                        </s:if>
                                                    </tr>
                                                </s:if>
                                                <tr>
                                                    <td class="fieldLabel">Technical&nbsp;skills:<FONT color="red"  ><em>*</em></FONT></td>
                                                    <td><s:textfield name="techSkills" cssClass="inputTextBlueCustomer" id="techSkills" value="%{techSkills}" style="width:25px" onchange="allowAnswer(this);"/></td>
                                                    <td class="fieldLabel">Domain&nbsp;skills:<FONT color="red"  ><em>*</em></FONT></td>
                                                    <td><s:textfield name="domainskills" cssClass="inputTextBlueCustomer" id="domainskills" value="%{domainskills}" style="width:25px" onchange="allowAnswer(this);"/></td> 
                                                </tr>
                                                <tr>
                                                    <td class="fieldLabel">Communication&nbsp;skills:<FONT color="red"  ><em>*</em></FONT></td>
                                                    <td colspan="3"><s:textfield name="commskills" cssClass="inputTextBlueCustomer" id="commskills" value="%{commskills}" style="width:25px" onchange="allowAnswer(this);"/>
                                                        <FONT color="red"  size="2px">Note:Rating between 1 to 10</FONT></td>
                                                        <%-- <s:label cssClass="fieldLabelLeft" value="("/><FONT color="red"  >*</FONT><FONT color="red"  >*</FONT>&nbsp;<s:label cssClass="fieldLabelLeft" value="Rating between 1 to 10)"/> --%>






                                                    <%--  <td class="fieldLabel">Title&nbsp;Type&nbsp;of&nbsp;Techie:</td>
                                                    <td class="userInfoLeft"><s:property value="%{title}"/></td> --%>
                                                </tr>
                                                <tr>
                                                    <td class="fieldLabel" >Status :<FONT color="red"  ><em>*</em></FONT></td> 
                                                    <td > <s:select name="status" id="status"  headerKey="-1" headerValue="--Please Select--" list="{'Tech Screen shotlisted','Tech Screen Reject'}" cssClass="inputSelect" /></td>

                                                </tr>
                                                <tr>
                                                    <td class="fieldLabel">Comments :<FONT color="red"  ><em>*</em></FONT></td>
                                                    <td colspan="5"><s:textarea rows="3" cols="85" name="comments" cssClass="inputTextarea" id="comments" value="%{comments}" onchange="fieldLenghtValidator(this);"/></td>
                                                </tr>
                                                
                                                 <tr>
                                                  <td align="center" colspan="4">
                                                        <s:if test="%{rateing=='Not Yet Reviewed'}">
                                                            <s:submit value="Submit" cssClass="buttonBg"/>
                                                        </s:if><s:else>
                                                            <font color="red">*</font><font color="green">&nbsp;Reviewed</font>
                                                        </s:else>
                                                        
                                                    </td>
                                                    
                                                </tr>
                                            </table>
                                        </s:form>

                                        <!-- this script for After loading Form it will instantiate Calender Objects as you require -->

                                    </div>                                       
                                </div>                               
                                <%--  </sx:tabbedpanel> --%>
                                <!--//END TABBED PANNEL : -->
                                <script type="text/javascript">

                                    var countries=new ddtabcontent("accountTabs")
                                    countries.setpersist(false)
                                    countries.setselectedClassTarget("link") //"link" or "linkparent"
                                    countries.init()

                                </script>
                                <!-- Attachments Grid Start -->


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
            <tr>
                <td>

                    <div style="display: none; position: absolute; top:170px;left:320px;overflow:auto;" id="menu-popup">
                        <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" />
                    </div>

                </td>
            </tr>
        </table>
        <!--//END MAIN TABLE : Table for template Structure-->
<script type="text/javascript">
		$(window).load(function(){
	init();
		});
		</script>
    </body>
</html>
