<%-- 
    Document   : campaignDetails
    Created on : 4 Jan, 2016, 5:13:58 PM
    Author     : Kalpana
--%>

<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
--%>
<%@ page import="com.freeware.gridtag.*" %> 
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd" %>

<html>
    <head>

        <title>Hubble Organization Portal :: Campaign Details</title>
        <%-- <sx:head cache="false"/> --%>

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>

        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>            
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>   
        <%-- <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CommonAjax.js"/>"></script> --%>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/marketing/CampaignScript.js"/>"></script>
        <script type="text/JavaScript">
            /*function checkDate() {
                var sdate = document.getElementById('startDate').value;
                var edate = document.getElementById('endDate').value;
                var mm = sdate.substring(0,2);
                var dd = sdate.substring(3,5);
                var yyyy = sdate.substring(6,10);
                var mm1 = edate.substring(0,2);
                var dd1 = edate.substring(3,5);
                var yyyy1 = edate.substring(6,10);
                if(yyyy1 < yyyy) {
                    alert('End Date is older than Start Date');
                    return false;
                }
                else if((yyyy1 == yyyy) && (mm1 < mm)) {
                    alert('End Date is older than Start Date');
                    return false;
                }
                else if((yyyy1 == yyyy) && (mm1 == mm) && (dd1 < dd)) {
                    alert('End Date is older than Start Date');
                    return false;
                }
            }
            */
            function setTempVar1() {
                document.frmCampaignAdd.tempVar.value = 1;
            }
            function setTempVar2() {
                document.frmCampaignAdd.tempVar.value = 2;
            }
            
            function checkCampaign()
            {
                var campaignName=document.getElementById('campaignName').value;
                //alert("campaignName-->"+campaignName);
                var campaignEndDate=document.getElementById('campaignEndDate').value;
                var description=document.getElementById('description').value;
                                
                if(campaignName=="" || campaignEndDate==""  || description=="" || campaignName==null || campaignEndDate==null || description==null )
                {
                    alert("plz fill the Campaign Name, campaignEndDate and Description ");
                    return false;
                      
                }
                else
                {
                    return true;
                }
                
                
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
                            <!--//END DATA COLUMN : Coloumn for LeftMenu-->

                            <!--//START DATA COLUMN : Coloumn for Screen Content-->
                            <td width="850px" class="cellBorder" valign="top">
                                <table cellpadding="0" cellspacing="0" width="100%">
                                   
                                    <tr>                                        
                                        <td valign="top" style="padding-left:10px;padding-top:5px;">
                                            <!--//START TABBED PANNEL : -->
                                            <ul id="accountTabs" class="shadetabs" >
                                                <s:if test="%{navId!= 1}">
                                                <li ><a href="#" class="selected" rel="accountTab"  > Add Campaign </a></li>      
                                                </s:if>
                                                <s:else>
                                                   <li ><a href="#" class="selected" rel="accountTab"  > Edit Campaign </a></li>      
                                                </s:else>
                                            </ul>
                                            <%--  
                                            <sx:tabbedpanel id="projectPannel" cssStyle="width: 840px; height: 200px;padding-left:10px;padding-top:5px;" doLayout="true">
                                            --%>    
                                            <!--//START TAB : -->
                                            <div  style="border:1px solid gray; width:840px;height: 200px; overflow:auto; margin-bottom: 1em;">  
                                                <div id="accountTab" class="tabcontent" >
                                                    <%--  <sx:div id="accountTab" label="Project Add"  > --%>

                                                    <s:form name="frmCampaignAdd" action="addOrUpdateCampaign" theme="simple" onsubmit="return checkCampaign();">

                                                        <table cellpadding="1" cellspacing="1" border="0" width="100%">
                                                            <tr align="right">

                                                                <td class="headerText" colspan="6">
                                                                    <s:hidden name="tempVar" id="tempVar" value="%{tempVar}"/>
                                                                    <s:hidden name="navId" id="navId" value="%{navId}"/>
                                                                    <s:hidden name="campaignId" id="campaignId" value="%{campaignId}"/>
                                                                    <s:hidden name="roleName" id="roleName" value="%{#session.roleName}"/>
                                                                    <s:property value="#request.resultMessage"/>   
                                                                    <INPUT type="button" CLASS="buttonBg" value="Back to List" onClick="history.go(-1)"></INPUT>
                                                                    <s:if test="#session.roleId!=4 && #session.roleId!=12">
                                                                      <s:if test="%{navId == 1}">
                                                                        <s:submit cssClass="buttonBg" value="Update" onclick="setTempVar2()"/>

                                                                    </s:if>
                                                                    <s:else>

                                                                        <s:submit cssClass="buttonBg" value="Save" onclick="setTempVar1()"/>
                                                                    </s:else>  
                                                                    </s:if>
                                                                    
                                                                </td>

                                                            </tr>      

                                                            <tr>
                                                                <td class="fieldLabel" >Campaign Title:<FONT color="red"  ><em>*</em></FONT>
</td>                                                    
                                                                <td><s:textfield name="campaignName" cssClass="inputTextBlueLarge"  id="campaignName" value="%{campaignName}" size="25" /></td>                                                     

                                                            </tr>
                                                            <tr>                                                              
                                                                <td class="fieldLabel">Completion Date :<FONT color="red"  ><em>*</em></FONT>
</td>                                                    
                                                                <td style="width: 100px;">
                                                                    <s:textfield name="campaignEndDate" id="campaignEndDate" cssClass="inputTextBlueSmall" onkeypress="return handleEnter(this,event);" value="%{campaignEndDate}"/><a href="javascript:cal2.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                </td>                                                             
                                                                <td class="fieldLabel">Status:</td>     
                                                             <%--   <td class="inputOptionText"><s:select name="campaignStatus" id="campaignStatus" list="#@java.util.LinkedHashMap@{'Active':'Active','InActive':'InActive','Completed':'Completed'}" value="%{campaignStatus}" cssClass="inputSelect" /></td> --%>
                                                               <td class="inputOptionText" > <s:select list="{'Active','Inactive','Completed'}"  name="campaignStatus" style="width: 90px;" id="campaignStatus" cssClass="inputTextBlue"/></td>

                                                            </tr>

                                                            <tr>
                                                                <td class="fieldLabel">Campaign Descrption :<FONT color="red"  ><em>*</em></FONT>
</td>
                                                                <td colspan="3"><s:textarea cols="85" rows="2" name="description"  cssClass="inputTextarea" onchange="descriptionValidate(document.frmProjectAdd.description.value);"  id="description" value="%{description}"/></td>                                                    
                                                            </tr>                                                         
                                                             <tr>
                                                                <td class="fieldLabel">Sending Dates :</td>
                                                                <td colspan="3"><s:textarea cols="85" rows="2" name="sendDates"  cssClass="inputTextarea" id="sendDates" value="%{sendDates}"/></td>                                                    
                                                            </tr>

                                                        </table>
                                                        <script type="text/JavaScript">
//                                                            var cal1 = new CalendarTime(document.forms['frmCampaignAdd'].elements['campaignStartDate']);
//                                                            cal1.year_scroll = true;
//                                                            cal1.time_comp = false;
                                                           
                                                            var cal2 = new CalendarTime(document.forms['frmCampaignAdd'].elements['campaignEndDate']);
                                                            cal2.year_scroll = true;
                                                            cal2.time_comp = false;
                                                        </script>
                                                    </s:form>

                                                    <%-- </sx:div > --%>

                                                </div>
                                                
                                            </div>
                                            <!--//END TABBED PANNEL : -->


                                            <script type="text/javascript">

                                                var countries=new ddtabcontent("accountTabs")
                                                countries.setpersist(false)
                                                countries.setselectedClassTarget("link") //"link" or "linkparent"
                                                countries.init()

                                            </script>

                                        </td>
                                    </tr>
                                    <s:if test="%{navId == 1}">
                                    <tr>
                                        <td valign="top" >
                                            <ul id="accountTabs1" class="shadetabs" >
                                                <li ><a href="#" class="selected" rel="CampaignList"> Contacts </a></li>                                   
                                            </ul>

                                            <div  style="border:1px solid gray; width:840px;height: 250px; overflow:auto; margin-bottom: 1em;">  
                                                    <center><div id="load" style="color: red;display: none;"><font size="4px;">Loading..</font></div></center>                                              
                                                
                                          <s:form name="contactSearch" action="#" theme="simple" >
                                         
                                            <table   width="100%" border="0">
                                                <tr><td><br></td></tr>
                                                  <tr>
                                                  <td  class="fieldLabel" align="left">Start&nbsp;Date&nbsp;(mm/dd/yyyy)&nbsp;:</td>
                                                  <td>   <s:textfield name="contactStartDate" id="contactStartDate" cssClass="inputTextBlueSmall" readonly="true"/><a href="javascript:cal4.popup();"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                </td>
                                                 <td  class="fieldLabel" >End&nbsp;Date&nbsp;(mm/dd/yyyy)&nbsp;:</td>
                                                 <td>  <s:textfield name="contactEndDate" id="contactEndDate" cssClass="inputTextBlueSmall" readonly="true"/><a href="javascript:cal5.popup();"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                </td> 
                                                <td></td>
                                                            <td >
                                                         
                                                           <input type="button" class="buttonBg" value="Search" onclick="getCampaignContactsList();"/>
                                                           
                                                        
                                                               </td> 
                                                               <td>
                                                                   <div id="GenerateExcel" style="display: none">
                                                                <input type="button"  class="buttonBg" value="Generate Excel" onclick="return getCampaignContactsExcel();" />
                                                           </div>
                                                               </td>
                                                            </tr>
                                            </table>
                                                </s:form>
                                                   
                                                      
                                                <table width="100%">
                                                <tr>
                                                <td colspan="4">
                                                        <table width="100%" cellpadding="2" cellspacing="2" border="0">
                                                           
                                                            <tr>
                                                                <td colspan="3" >

                                                                    <div id="" style="display: block">
                                                                        <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                        <table id="tblUpdate111" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='100%' align="center">
                                                                            <COLGROUP ALIGN="left" >
                                                                                <COL width="5%">
                                                                                <COL width="15%">
                                                                                <COL width="15%">
                                                                                <COL width="15%">
                                                                        </table>
                                                                        <br>
                                                                        <center><span id="spnFast" class="activeFile" style="display: none;"></span></center> 
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                
                                            </table>


                                            </td>
                                            </tr>
                                             
                                            </table>    
                                        


                                               
                                            </div>

                                            <script type="text/javascript">
                                                 var cal4 = new CalendarTime(document.forms['contactSearch'].elements['contactStartDate']);
                                                cal4.year_scroll = true;
                                                cal4.time_comp = false;
                                                var cal5 = new CalendarTime(document.forms['contactSearch'].elements['contactEndDate']);
                                                cal5.year_scroll = true;
                                                cal5.time_comp = false;
                                               
                                               
                                                var countries=new ddtabcontent("accountTabs1")
                                                countries.setpersist(false)
                                                countries.setselectedClassTarget("link") //"link" or "linkparent"
                                                countries.init()
                                            </script>

                                            <%--</sx:div>
                                        </sx:tabbedpanel>--%>
                                        </td>
                                    </tr>
                                    </s:if>
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
  <s:if test="%{navId == 1}">
		 <script type="text/javascript">
		$(window).load(function(){
	getCampaignContactsList();
		});
		</script>
         
         </s:if>


    </body>
</html>
