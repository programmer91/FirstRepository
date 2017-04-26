<!-- 
 *******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  November 20, 2007, 3:25 PM
 *
 * Author  : Arjun Sanapathi<asanapathi@miraclesoft.com>
 *
 * File    : IssueDetails.jsp
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
-->
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%-- <%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<%@ page import="com.freeware.gridtag.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hubble Organization Portal :: Account Assignment</title>
        <sx:head cache="false"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        
        
        <!-- issues Related JavaScript  -->
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AjaxUtil.js"/>"></script> 
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AccountAssignment.js"/>"></script> 
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        
        <!-- New account assign -->
        
         <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AccountMerge.js"/>"></script>
         
        <script type="text/JavaScript">
            function statesPopup(url) {
                newwindow=window.open(url,'name','height=600,width=500,top=200,left=250');
                if (window.focus) {newwindow.focus()}
            return false;
}
        </script>
    </head>
   <!-- <body class="bodyGeneral" oncontextmenu="return false;" onload="init();"> -->
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
                            <td width="850px" class="cellBorder" valign="top">
                                
                                   <s:if test="#session.roleName != 'Marketing'">
                                <form action="updateAccounts" name="searchForm">
                                    
                                    <h3 align="center" style="color:darkblue;">Account Operations</h3>
                                    
                                    
                                    
                                    
                                    <div style="display: block" id="addlabel2">
                                        
                                        <table align="center" border="0" cellpadding="0" cellspacing="0">
                                            <tr>
                                                <td class="fieldLabel">AccountType :</td>
                                                <td><select name="acctype" id="acctype" size="1" onchange="displayTable()">
                                                        <option value="4" selected>-- Select--</option>
                                                        <option value="1"> Primary(Replace With New  TeamMember)</option>
                                                        <option value="2"> Secondary(Add new TeamMember)</option>
                                                        <option value="3"> Remove Accounts</option>
                                                        <option value="5"> Transfer Accounts</option>
                                                    </select>
                                                </td> 
                                                
                                                
                                            </tr> 
                                        </table>
                                    </div>
                                    
                                    
                                    <script language="javascript">
                                        
                                        function disableAccNameEdit()
                                        {
                                        document.getElementById("accountName").readOnly = true;
                                        }
                                        function Reset(){
                                        document.getElementById("accountName").readOnly = false;
                                        document.getElementById("accountName").value = "";
                                        document.getElementById("state").value = "";
                                        }
                                        
                                    </script>
                                    
                                    
                                    <div style="display: none" id="addlabel3">
                                        
                                        <table align="center" border="0" cellpadding="0" cellspacing="0">
                                            <tr>
                                                <td class="fieldLabel">Account Name :</td>
                                                <td><!-- <input type="text" size="50" name="accountName" id="accountName" class="inputTextBlueLarge"/>-->
                                            <input type="text" size="50" name="accountName" autocomplete="off" id="accountName" class="inputTextBlueLarge" onkeyup="getAccountName3(this);" > 
                                            <div onclick="disableAccNameEdit();" style="display: none; position: absolute; overflow:auto;" id="menu-popup">
                                               <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" >
                                                   </table>
                                                </div>
                                                <div style="display: none; position: absolute; overflow:auto;" id="menu-popup1">
                                                    <table id="completeTable1" border="1" bordercolor="#e5e4f2" style="display: none;border: 1px dashed gray;">
                                                    </table>
                                                </div>
                                            </td>   
                                                <td class="fieldLabel">State :</td>
                                                <td><input type="text" size="50" name="state" id="state" class="inputTextBlueSmall"/></td> 
                                                <td style="padding-left:15px;"><input type="button" value="states List"  class="buttonBg" onclick="return statesPopup('StatesInfo.jsp')" align="left"/>
                                                <input type="button" value="Search" class="buttonBg" onclick="RetriveType()"/> <input type="button" value="Reset" class="buttonBg" onclick="Reset()"/></td>
                                                
                                            </tr>  
                                            
                                            <!-- New tr for displaying accounts list -->
                                            
                                           <!-- <tr>
                                             <td>&nbsp;</td>
                                               <td><div style="display: none; position: absolute; overflow:auto;" id="menu-popup">
                                                 <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" >
                                                   </table>
                                                </div></td>
                                               <td>&nbsp;</td>
                                                 <td></td>
                                             </tr>-->
                                            
                                        </table>
                                    </div>
                                    
                                    <br> 
                                    
                                    <div style="display: none" id="addlabel4">
                                        
                                        <table align="center" border="0" cellpadding="0" cellspacing="0">
                                            <tr>
                                                <td>
                                                    <input type="button" name="search4"  class="buttonBg" value="Another search"  onclick="searchStart()">
                                            </td></tr>
                                        </table>
                                    </div>
                                    
                                    <div style="display: none" id="addlabel5">
                                        
                                        <table align="center" border="0" cellpadding="0" cellspacing="0">
                                            <tr>
                                               <!-- <td class="fieldLabel">New LoginId :</td>
                                                <td>
                                                    <input type="text" name="newloginId"  class="inputTextBlue" id="newloginId" value="newloginId">
                                                   
                                                </td>-->
                                                <td class="fieldLabel">New TeamMember Name: </td>
                                                 <td><s:select list="#session.myTeamMap" name="newloginId" id="newloginId"  cssClass="inputSelect" headerValue="select" headerKey="newloginId" theme="simple" /> </td> 
                                                
                                                <td class="fieldLabel">State :</td>
                                                <td>
                                                    <input type="text" name="secondState" class="inputTextBlue" id="secondState"  value="state">
                                                </td>
                                                <td style="padding-left:15px;"><input type="button" value="states List"  class="buttonBg" onclick="return statesPopup('StatesInfo.jsp')" align="left"/></td>
                                                <td style="padding-left:15px;">
                                                    <input type="button" name="search5"  class="buttonBg" value="Assign Accounts" onclick="RetriveSecondType()">
                                                </td>
                                                
                                            </tr>
                                        </table>
                                    </div>
                                    <div style="display: none" id="addlabel7">
                                        
                                  <%--      <s:form name="reAssignAccounts" action="transferAccounts" theme="simple"> --%>
                                            
                                                <table border="0" cellpadding="0" cellspacing="0" align="center" width="500px" height="150px" class="cellBorder">
                                                    <tr>
                                                        <td colspan="4" class="headerText">Enter following details to  ReAssign Screens</td>
                                                    </tr>
                                                      <tr>
                                                                                <td align="center" colspan="4">
                                                                                    <div id="loadTransferMessage" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                </td>
                                                                            </tr>
                                                    <tr>
                                                        <td class="fieldLabel" align="right">From<font color="red">*</font> :</td>
                                                        <td>
                                                          <s:select list="#session.myTeamMap" name="frmLoginId" id="frmLoginId" headerKey="" headerValue="--Select Team--" cssClass="inputSelect" theme="simple"/>
                                                         
                                                        </td>
                                                        
                                                        
                                                        <td class="fieldLabel" align="right">To<font color="red">*</font> :</td>
                                                        <td>
                                                            <s:select list="#session.myTeamMap" name="toLoginId" id="toLoginId" headerKey="" headerValue="--Select Team--" cssClass="inputSelect" theme="simple"/>
                                                            
                                                        </td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td colspan="2" style="padding-left:218px;">
                                                            <input type="button" name="Submit"  class="buttonBg" value="Submit" onclick="transferAccounts();">
                                                            
                                                        </td>
                                                    </tr>
                                                  
                                                </table>
                                            
                                        <%-- </s:form> --%>
                                    </div>


                                    <div style="display: none" id="addlabel6">
                                        
                                        <table align="center" border="0" cellpadding="0" cellspacing="0">
                                            <tr>
                                               <!-- <td class="fieldLabel">New LoginId :</td>
                                                <td>
                                                    <input type="text" name="newloginId"  class="inputTextBlue" id="newloginId" value="newloginId">
                                                   
                                                </td>-->
                                                <td class="fieldLabel">Team Member Name: </td>
                                                 <td><s:select list="#session.myTeamMap" name="teamMemberId" id="teamMemberId"  cssClass="inputSelect" headerValue="select" headerKey="teamMemberId" theme="simple" /> </td> 
                                                
                                                <td class="fieldLabel">State :</td>
                                                <td>
                                                    <input type="text" name="delStateAcc" class="inputTextBlue" id="delStateAcc"  value="">
                                                </td>
                                                <td style="padding-left:15px;"><input type="button" value="states List"  class="buttonBg" onclick="return statesPopup('StatesInfo.jsp')" align="left"/></td>
                                                <td style="padding-left:15px;">
                                                    <input type="button" name="Delete"  class="buttonBg" value="Delete" onclick="deleteEmpAccount()">
                                                </td>
                                                
                                            </tr>
                                        </table>
                                    </div>
                                    <br>
                                    <table align="center" border="0" cellpadding="1" cellspacing="1">
                                        <tr><td> 
                                                
                                                <div style="height:500px;  overflow:auto; border:3px; border-right-width: 15px;border-bottom-width: 15px; border-left-width: 15px; display: none;  "  id="addlabel1">
                                                    <table cellpadding="1" cellspacing="1" width="98%" class="gridTable" id="RevenueSummery">
                                                        <tr class="gridHeader">
                                                            <td width="5%" class="gridHeader">Id </td>
                                                            <td width="53%" class="gridHeader" >AccountName</td>
                                                            <td width="10%" class="gridHeader" >From</td>
                                                            <td width="10%" class="gridHeader" >To(LoginId)</td>
                                                            <td width="20%" class="gridHeader" >Assign</td>
                                                        </tr>
                                                        
                                                        <tr class="gridRowEven">
                                                            <td> <input type="text" name="text6"  id="text6" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="text7"  id="text7" class="gridColumnLarge" align="left"value="" readonly></td>
                                                            <td> <input type="text" name="text8"  id="text8" class="gridCol" align="left" value="" readonly></td>
                                                           <td> <input type="text" name="text9" id="text9" class="gridCol" align="left"></td> <!--  onblur="getDetails('8')"    -->
                                                            <td> <input type="text" name="text10" id="text10" class="gridCol" align="left" value="Assign" onclick="getDetails('10')"></td>
                                                        </tr>
                                                        
                                                        <tr class="gridRowEven">
                                                            <td> <input type="text" name="text11" id="text11" value="" class="gridCol" align="left" readonly></td>
                                                            <td> <input type="text" name="text12"  id="text12" value="" class="gridColumnLarge" align="left" readonly></td>
                                                            <td> <input type="text" name="text13" id="text13" value="" class="gridCol" align="left" readonly></td>
                                                            <td> <input type="text" name="text14"   id="text14" value="" class="gridCol" align="left"  ></td>
                                                            <td> <input type="text" name="text15" id="text15" class="gridCol" align="left" value="Assign" onclick="getDetails('15')"></td>
                                                        </tr>
                                                        <tr class="gridRowEven">
                                                            <td> <input type="text" name="text16"  id="text16" value="" class="gridCol" align="left" readonly></td>
                                                            <td> <input type="text" name="text17"  id="text17" value="" class="gridColumnLarge" align="left" readonly></td>
                                                            <td> <input type="text" name="text18"  id="text18" value=""  class="gridCol" align="left" readonly></td>
                                                            <td> <input type="text" name="text19"  id="text19" value=""   class="gridCol" align="left"></td>
                                                            <td> <input type="text" name="text20" id="text20" class="gridCol" align="left" value="Assign" onclick="getDetails('20')"></td>
                                                        </tr>
                                                        <tr class="gridRowEven">
                                                            <td> <input type="text" name="text21"  id="text21" value="" class="gridCol" align="left" readonly ></td>
                                                            <td> <input type="text" name="text22"  id="text22"  value="" class="gridColumnLarge" align="left" readonly></td>
                                                            <td> <input type="text" name="text23" id="text23" value="" class="gridCol" align="left" readonly></td>
                                                            <td> <input type="text" name="text24" id="text24" value="" class="gridCol" align="left"></td>
                                                            <td> <input type="text" name="text25" id="text25" class="gridCol" align="left" value="Assign" onclick="getDetails('25')"></td>
                                                        </tr>
                                                        
                                                        
                                                        <tr class="gridRowEven">
                                                            <td> <input type="text" name="text26"  id="text26" value="" class="gridCol" align="left" readonly ></td>
                                                            <td> <input type="text" name="text27"  id="text27"  value="" class="gridColumnLarge" align="left" readonly></td>
                                                            <td> <input type="text" name="text28" id="text28" value="" class="gridCol" align="left" readonly></td>
                                                            <td> <input type="text" name="text29" id="text29" value="" class="gridCol" align="left" ></td>
                                                            <td> <input type="text" name="text30" id="text30" class="gridCol" align="left" value="Assign" onclick="getDetails('30')"></td>
                                                        </tr>
                                                        
                                                        <tr class="gridRowEven">
                                                            <td> <input type="text" name="text31"  id="text31" value="" class="gridCol" align="left" readonly ></td>
                                                            <td> <input type="text" name="text32"  id="text32"  value="" class="gridColumnLarge" align="left" readonly></td>
                                                            <td> <input type="text" name="text33" id="text33" value="" class="gridCol" align="left" readonly></td>
                                                            <td> <input type="text" name="text34" id="text34" value="" class="gridCol" align="left"></td>
                                                            <td> <input type="text" name="text35" id="text35" class="gridCol" align="left" value="Assign" onclick="getDetails('35')"></td>
                                                        </tr>
                                                        <tr class="gridRowEven">
                                                            <td> <input type="text" name="text36"  id="text36" value="" class="gridCol" align="left" readonly ></td>
                                                            <td> <input type="text" name="text37"  id="text37"  value="" class="gridColumnLarge" align="left" readonly></td>
                                                            <td> <input type="text" name="text38" id="text38" value="" class="gridCol" align="left" readonly></td>
                                                            <td> <input type="text" name="text39" id="text39" value="" class="gridCol" align="left"></td>
                                                            <td> <input type="text" name="text40" id="text40" class="gridCol" align="left" value="Assign" onclick="getDetails('40')"></td>
                                                        </tr>
                                                        <tr class="gridRowEven">
                                                            <td> <input type="text" name="text40"  id="text41" value="" class="gridCol" align="left" readonly ></td>
                                                            <td> <input type="text" name="text41"  id="text42"  value="" class="gridColumnLarge" align="left" readonly></td>
                                                            <td> <input type="text" name="text42" id="text43" value="" class="gridCol" align="left" readonly></td>
                                                            <td> <input type="text" name="text43" id="text44" value="" class="gridCol" align="left" ></td>
                                                            <td> <input type="text" name="text45" id="text45" class="gridCol" align="left" value="Assign" onclick="getDetails('45')"></td>
                                                        </tr>
                                                        <tr class="gridRowEven">
                                                            <td> <input type="text" name="text46"  id="text46" value="" class="gridCol" align="left" readonly ></td>
                                                            <td> <input type="text" name="text47"  id="text47"  value="" class="gridColumnLarge" align="left" readonly></td>
                                                            <td> <input type="text" name="text48" id="text48" value="" class="gridCol" align="left" readonly></td>
                                                            <td> <input type="text" name="text49" id="text49" class="gridCol" align="left"></td>
                                                            <td> <input type="text" name="text50" id="text50" class="gridCol" align="left" value="Assign" onclick="getDetails('50')"></td>
                                                        </tr>
                                                        <tr class="gridRowEven">
                                                            <td> <input type="text" name="text51"  id="text51" value="" class="gridCol" align="left" readonly ></td>
                                                            <td> <input type="text" name="text52"  id="text52"  value="" class="gridColumnLarge" align="left" readonly></td>
                                                            <td> <input type="text" name="text53" id="text53" value="" class="gridCol" align="left" readonly></td>
                                                            <td> <input type="text" name="text54" id="text54" value="" class="gridCol" align="left"></td>
                                                            <td> <input type="text" name="text55" id="text55" class="gridCol" align="left" value="Assign" onclick="getDetails('55')"></td>
                                                        </tr>
                                                       
                                                        
                                                        <tr class="gridPager">
                                                            <td colspan="5" align="left" class="gridFooter" ></td>
                                                        </tr>
                                                    </table> 
                                                </div>
                                    </td></tr></table>
                                </form>
                                   </s:if>
                                   <s:else>
                                       <s:form action="" name="updateMarketingForm" theme="simple" >
                                       <table style="width: 80%;margin-top: 10%;" align="center" border="0" cellpadding="0" cellspacing="0">
                                            <tr>
                                                <td class="fieldLabel">LoginId: </td>
                                                 <td><s:textfield name="newloginIdMarketing" id="newloginIdMarketing"  cssClass="inputSelect"/> </td> 
                                                
                                                <td class="fieldLabel">State :</td>
                                                <td>
                                                    <input type="text" name="secondStateMarketing" class="inputTextBlue" id="secondStateMarketing"  value="">
                                                </td>
                                                <td style="padding-left:15px;"><input type="button" value="states List"  class="buttonBg" onclick="return statesPopup('StatesInfo.jsp')" align="left"/></td>
                                                <td style="padding-left:15px;">
                                                    <input type="button" name="search5"  class="buttonBg" value="Assign Accounts" onclick="RetriveSecondMarketingType()">
                                                </td>
                                                
                                            </tr>
                                        </table>
                                                 
                                                 
                                    
                                       </s:form>
                                   </s:else>
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
           <script type="text/javascript">
		$(window).load(function(){
		
		init();
		
                
		});
		</script>
    </body>
</html>
 
 