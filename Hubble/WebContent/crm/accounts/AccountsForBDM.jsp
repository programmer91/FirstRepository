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
        <title>Hubble Organization Portal :: Get Accounts </title>
        <sx:head cache="false"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        
        
        <!-- issues Related JavaScript  -->
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AjaxUtil.js"/>"></script> 
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AccountAssignment.js"/>"></script> 
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        
        <!-- New account assign -->
        
         <script type="text/JavaScript" src="<s:url value="/includes/javascripts/GetAccounts.js"/>"></script>
         
         <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
         
        <script type="text/JavaScript">
            function statesPopup(url) {
                newwindow=window.open(url,'name','height=600,width=500,top=200,left=250');
                if (window.focus) {newwindow.focus()}
            return false;
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
            <tr>
                <td>
                    <table class="innerTable1000x515" cellpadding="0" cellspacing="0">
                        <tr>
                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                            <td width="150px;" class="leftMenuBgColor" valign="top"> 
                                <s:include value="/includes/template/LeftMenu.jsp"/> 
                            </td>
                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->  
                            <td width="850px" class="cellBorder" valign="top">
                                <form action="updateAccounts" name="searchForm">
                                    
                                    <h3 align="center" style="color:darkblue;">Get Accounts</h3>
                                    <div id="addlabel4">
                                        <table align="center" border="0" cellpadding="0" cellspacing="0">
                                                <tr>
                                                    <s:hidden id="territory" value="%{territory}"/>
                                                    <s:hidden id="state1" value="%{state1}"/>
                                                    <s:hidden id="state2" value="%{state2}"/>
                                                    <s:hidden id="state3" value="%{state3}"/>
                                                    <s:hidden id="state4" value="%{state4}"/>
                                                    <s:hidden id="state5" value="%{state5}"/>
                                                    <s:hidden id="region" value="%{region}"/>
                                                    <td class="fieldLabel"> Region Name: </td>
                                                    <td colspan="2">
                                                     <s:property value="%{region}"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                <td class="fieldLabel"> Teritory Name: </td>
                                                <td colspan="4">
                                                   <s:property value="%{territory}"/>
                                                </td> 
                                                </tr>
                                            
                                                <tr>
                                                    <s:if test="%{!state1.equals(\" \")}">
                                                        <td class="fieldLabel"> State1: </td>
                                                        <td> <s:property value="%{state1}"/></td> 
                                                    </s:if>
                                                    <s:if test="%{!state2.equals(\" \")}">
                                                        <td class="fieldLabel"> State2: </td>
                                                        <td> <s:property value="%{state2}"/></td> 
                                                    </s:if>
                                                    <s:if test="%{!state3.equals(\" \")}">
                                                        <td class="fieldLabel"> State3: </td>
                                                        <td> <s:property value="%{state3}"/></td> 
                                                    </s:if>
                                                    <s:if test="%{!state4.equals(\" \")}">
                                                        <td class="fieldLabel"> State4: </td>
                                                        <td> <s:property value="%{state4}"/></td> 
                                                    </s:if>
                                                    <s:if test="%{!state5.equals(\" \")}">
                                                        <td class="fieldLabel"> State5: </td>
                                                        <td> <s:property value="%{state5}"/></td> 
                                                    </s:if>
                                                </tr>
                                                <tr>
                                                <td class="fieldLabel"> Account Name:<font color="red">*</font> </td>
                                                <td colspan="8">
                                                  <input type="text" name= "accountName" id="accountName" value=" "/>  
                                                </td>    
                                                <td>
                                                   &nbsp;&nbsp;&nbsp; <input type="button" value="Display Accounts" class="buttonBg" onclick="getAccounts();"/></td>
                                                </td>
                                                </tr>
                                          <!--  <tr>
                                                    <td>
                                                        <div id="loadingMessage" style="display:none;" class="error" align="center" ><b><font size="3px" >Loading...Please Wait</font></b></div>
                                                    </td>
                                                </tr>-->

                                        </table>
                                    </div>
                                      <br>
                                      <table align="center" border="0" cellpadding="1" cellspacing="1">
                                          <tr><td> 
                                            <div style="height:500px;  overflow:auto; border:3px; border-right-width: 15px;border-bottom-width: 15px; border-left-width: 15px;display: none; "  id="accountList">
                                              <table cellpadding="1" cellspacing="1" width="100%" class="gridTable" id="RevenueSummery">
                                                <tr class="gridHeader">
                                                  <td width="55%" class="gridHeader" ><input type="checkbox" name="text" id="text">Select&nbsp;All</td> <%-- onclick="checkAll(document.searchForm.check_List);" --%>
                                                  <td width="5%" class="gridHeader">Id </td>
                                                  <td width="53%" class="gridHeader" >AccountName</td>
                                                </tr> 
                                                <tr class="gridRowEven">
                                                    <td  align="center"><div id="gridRow_1"> <input type="checkbox" name="check_List" id="check_List"></div></td>
                                                    <td> <input type="text" name="text1"  id="text1" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texta1"  id="texta1" class="gridColumnLarge" align="left" value="" readonly></td>
                                                    <%-- <td> <input type="checkbox" name="text3" id="text3" class="gridCol" align="left" value="Assign" onclick="getAccountDetails('3')"></td>--%>
                                                </tr>
                                                <tr class="gridRowEven">
                                                    <td align="center"><div id="gridRow_2"> <input type="checkbox" name="check_List" id="check_List"></div></td>
                                                    <td> <input type="text" name="text2" id="text2" value="" class="gridCol" align="left" readonly></td>
                                                    <td> <input type="text" name="texta2"  id="texta2" value="" class="gridColumnLarge" align="left" readonly></td><%--  onclick="getAccountDetails('6')" --%>
                                                    </tr>
                                                <tr class="gridRowEven">
                                                    <td align="center"> <div id="gridRow_3"><input type="checkbox" name="check_List" id="check_List"></div></td><%--  onclick="getAccountDetails('9')"--%>
                                                    <td> <input type="text" name="text3"  id="text3" value="" class="gridCol" align="left" readonly></td>
                                                    <td> <input type="text" name="texta3"  id="texta3" value="" class="gridColumnLarge" align="left" readonly></td>
                                                </tr>
                                                <tr class="gridRowEven">
                                                    <td align="center"><div id="gridRow_4"> <input type="checkbox" name="check_List" id="check_List"></div></td><%--  onclick="getAccountDetails('12')"--%>
                                                    <td> <input type="text" name="text4"  id="text4" value="" class="gridCol" align="left" readonly ></td>
                                                    <td> <input type="text" name="texta4"  id="texta4"  value="" class="gridColumnLarge" align="left" readonly></td>
                                               </tr>
                                                <tr class="gridRowEven">
                                                    <td align="center"><div id="gridRow_5"> <input type="checkbox" name="check_List" id="check_List"></div></td><%--  onclick="getAccountDetails('15')"--%>
                                                    <td> <input type="text" name="text5"  id="text5" value="" class="gridCol" align="left" readonly ></td>
                                                    <td> <input type="text" name="texta5"  id="texta5"  value="" class="gridColumnLarge" align="left" readonly></td>
                                                </tr>
                                                <tr class="gridRowEven">
                                                    <td align="center"><div id="gridRow_6"> <input type="checkbox" name="check_List" id="check_List"></div></td><%--  onclick="getAccountDetails('18')"--%>
                                                    <td> <input type="text" name="text6"  id="text6" value="" class="gridCol" align="left" readonly ></td>
                                                    <td> <input type="text" name="texta6"  id="texta6"  value="" class="gridColumnLarge" align="left" readonly></td>
                                                </tr>
                                                <tr class="gridRowEven">
                                                    <td align="center"> <div id="gridRow_7"><input type="checkbox" name="check_List" id="check_List"><div></td><%--  onclick="getAccountDetails('21')"--%>
                                                    <td> <input type="text" name="text7"  id="text7" value="" class="gridCol" align="left" readonly ></td>
                                                    <td> <input type="text" name="texta7"  id="texta7"  value="" class="gridColumnLarge" align="left" readonly></td>
                                                </tr>
                                                <tr class="gridRowEven">
                                                    <td align="center"><div id="gridRow_8"> <input type="checkbox" name="check_List" id="check_List"></div></td><%--  onclick="getAccountDetails('24')"--%>
                                                    <td> <input type="text" name="text8"  id="text8" value="" class="gridCol" align="left" readonly ></td>
                                                    <td> <input type="text" name="texta8"  id="texta8"  value="" class="gridColumnLarge" align="left" readonly></td>
                                                </tr>
                                              <tr class="gridRowEven">
                                                    <td align="center"><div id="gridRow_9"> <input type="checkbox" name="check_List" id="check_List"></div></td><%--  onclick="getAccountDetails('27')"--%>
                                                    <td> <input type="text" name="text9"  id="text9" value="" class="gridCol" align="left" readonly ></td>
                                                    <td> <input type="text" name="texta9"  id="texta9"  value="" class="gridColumnLarge" align="left" readonly></td>
                                              </tr>
                                                <tr class="gridRowEven">
                                                    <td align="center"><div id="gridRow_10"> <input type="checkbox" name="check_List" id="check_List"></div></td><%--  onclick="getAccountDetails('30')"--%>
                                                    <td> <input type="text" name="text10"  id="text10" value="" class="gridCol" align="left" readonly ></td>
                                                    <td> <input type="text" name="texta10"  id="texta10"  value="" class="gridColumnLarge" align="left" readonly></td>
                                                 </tr>
                                                <tr class="gridRowEven">
                                                    <td align="center"><div id="gridRow_11"> <input type="checkbox" name="check_List" id="check_List"></div></td><%--  onclick="getAccountDetails('30')"--%>
                                                    <td> <input type="text" name="text11"  id="text11" value="" class="gridCol" align="left" readonly ></td>
                                                    <td> <input type="text" name="texta11"  id="texta11"  value="" class="gridColumnLarge" align="left" readonly></td>
                                                </tr>
                                                <tr class="gridRowEven">
                                                                    <td align="center"> <div id="gridRow_12"><input type="checkbox" name="check_List" id="check_List"></div></td><%--  onclick="getAccountDetails('30')"--%>
                                                                    <td> <input type="text" name="text12"  id="text12" value="" class="gridCol" align="left" readonly ></td>
                                                                    <td> <input type="text" name="texta12"  id="texta12"  value="" class="gridColumnLarge" align="left" readonly></td>
                                                   </tr>
                                                   <tr class="gridRowEven">
                                                       <td align="center"><div id="gridRow_13"> <input type="checkbox" name="check_List" id="check_List"></div></td><%--  onclick="getAccountDetails('30')"--%>
                                                        <td> <input type="text" name="text13"  id="text13" value="" class="gridCol" align="left" readonly ></td>
                                                        <td> <input type="text" name="texta13"  id="texta13"  value="" class="gridColumnLarge" align="left" readonly></td>
                                                    </tr>
                                                    <tr class="gridRowEven">
                                                     <td align="center"> <div id="gridRow_14"><input type="checkbox" name="check_List" id="check_List"></div></td><%--  onclick="getAccountDetails('30')"--%>
                                                     <td> <input type="text" name="text14"  id="text14" value="" class="gridCol" align="left" readonly ></td>
                                                     <td> <input type="text" name="texta14"  id="texta14"  value="" class="gridColumnLarge" align="left" readonly></td>  
                                                    </tr>
                                                    <tr class="gridRowEven">
                                                                    <td align="center"> <div id="gridRow_15"><input type="checkbox" name="check_List" id="check_List"></div></td><%--  onclick="getAccountDetails('30')"--%>
                                                                     <td> <input type="text" name="text15"  id="text15" value="" class="gridCol" align="left" readonly ></td>
                                                                     <td> <input type="text" name="texta15"  id="texta15"  value="" class="gridColumnLarge" align="left" readonly></td>
                                                      </tr>
                                                      <tr class="gridRowEven">
                                                                  <td align="center"><div id="gridRow_16"> <input type="checkbox" name="check_List" id="check_List"></div></td><%--  onclick="getAccountDetails('30')"--%>
                                                                <td> <input type="text" name="text16"  id="text16" value="" class="gridCol" align="left" readonly ></td>
                                                                <td> <input type="text" name="texta16"  id="texta16"  value="" class="gridColumnLarge" align="left" readonly></td>
                                                       </tr>
                                                        <tr class="gridRowEven">
                                                            
                                                                <td align="center"> <div id="gridRow_17"><input type="checkbox" name="check_List" id="check_List"></div></td><%--  onclick="getAccountDetails('30')"--%>
                                                                <td> <input type="text" name="text17"  id="text17" value="" class="gridCol" align="left" readonly ></td>
                                                                <td> <input type="text" name="texta17"  id="texta17"  value="" class="gridColumnLarge" align="left" readonly></td>
                                                            
                                                        </tr>
                                                        <tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_18"> <input type="checkbox" name="check_List" id="check_List"></div></td><%--  onclick="getAccountDetails('30')"--%>
                                                            <td> <input type="text" name="text18"  id="text18" value="" class="gridCol" align="left" readonly ></td>
                                                            <td> <input type="text" name="texta18"  id="texta18"  value="" class="gridColumnLarge" align="left" readonly></td>
                                                            
                                                        </tr>
                                                        
                                                         <tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_19"> <input type="checkbox" name="check_List" id="check_List"></div></td>
                                                            <td> <input type="text" name="text19"  id="text19" value="" class="gridCol" align="left" readonly ></td>
                                                            <td> <input type="text" name="texta19"  id="texta19"  value="" class="gridColumnLarge" align="left" readonly></td>
                                                            
                                                        </tr>
                                                       
                                                        <tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_20"> <input type="checkbox" name="check_List" id="check_List"></div></td>
                                                            <td> <input type="text" name="text20"  id="text20" value="" class="gridCol" align="left" readonly ></td>
                                                            <td> <input type="text" name="texta20"  id="texta20"  value="" class="gridColumnLarge" align="left" readonly></td>
                                                            
                                                        </tr>
                                                        <tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_21"> <input type="checkbox" name="check_List" id="check_List"></div></td>
                                                            <td> <input type="text" name="text21"  id="text21" value="" class="gridCol" align="left" readonly ></td>
                                                            <td> <input type="text" name="texta21"  id="texta21"  value="" class="gridColumnLarge" align="left" readonly></td>
                                                            
                                                        </tr>
                                                        <tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_22"> <input type="checkbox" name="check_List" id="check_List"></div></td>
                                                            <td> <input type="text" name="text22"  id="text22" value="" class="gridCol" align="left" readonly ></td>
                                                            <td> <input type="text" name="texta22"  id="texta22"  value="" class="gridColumnLarge" align="left" readonly></td>
                                                            
                                                        </tr><tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_23"> <input type="checkbox" name="check_List" id="check_List"></div></td>
                                                            <td> <input type="text" name="text23"  id="text23" value="" class="gridCol" align="left" readonly ></td>
                                                            <td> <input type="text" name="texta23"  id="texta23"  value="" class="gridColumnLarge" align="left" readonly></td>
                                                            
                                                        </tr><tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_24"> <input type="checkbox" name="check_List" id="check_List"></div></td>
                                                            <td> <input type="text" name="text24"  id="text24" value="" class="gridCol" align="left" readonly ></td>
                                                            <td> <input type="text" name="texta24"  id="texta24"  value="" class="gridColumnLarge" align="left" readonly></td>
                                                            
                                                        </tr><tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_25"> <input type="checkbox" name="check_List" id="check_List"></div></td>
                                                            <td> <input type="text" name="text25"  id="text25" value="" class="gridCol" align="left" readonly ></td>
                                                            <td> <input type="text" name="texta25"  id="texta25"  value="" class="gridColumnLarge" align="left" readonly></td>
                                                            
                                                        </tr><tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_26"> <input type="checkbox" name="check_List" id="check_List"></div></td>
                                                            <td> <input type="text" name="text26"  id="text26" value="" class="gridCol" align="left" readonly ></td>
                                                            <td> <input type="text" name="texta26"  id="texta26"  value="" class="gridColumnLarge" align="left" readonly></td>
                                                            
                                                        </tr><tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_27"> <input type="checkbox" name="check_List" id="check_List"></div></td>
                                                            <td> <input type="text" name="text27"  id="text27" value="" class="gridCol" align="left" readonly ></td>
                                                            <td> <input type="text" name="texta27"  id="texta27"  value="" class="gridColumnLarge" align="left" readonly></td>
                                                            
                                                        </tr><tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_28"> <input type="checkbox" name="check_List" id="check_List"></div></td>
                                                            <td> <input type="text" name="text28"  id="text28" value="" class="gridCol" align="left" readonly ></td>
                                                            <td> <input type="text" name="texta28"  id="texta28"  value="" class="gridColumnLarge" align="left" readonly></td>
                                                            
                                                        </tr><tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_29"> <input type="checkbox" name="check_List" id="check_List"></div></td>
                                                            <td> <input type="text" name="text29"  id="text29" value="" class="gridCol" align="left" readonly ></td>
                                                            <td> <input type="text" name="texta29"  id="texta29"  value="" class="gridColumnLarge" align="left" readonly></td>
                                                            
                                                        </tr>
                                                        <tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_30"> <input type="checkbox" name="check_List" id="check_List"></div></td>
                                                            <td> <input type="text" name="text30"  id="text30" value="" class="gridCol" align="left" readonly ></td>
                                                            <td> <input type="text" name="texta30"  id="texta30"  value="" class="gridColumnLarge" align="left" readonly></td>
                                                            
                                                        </tr>

                                              
                                                     
                                                        <tr class="gridPager">
                                                            <td colspan="5" align="left" class="gridFooter" ></td>
                                                        </tr>
                                                      </table>
                                                      
                                                  </div>
                                              </td>
                                              <td align="top">
                                                  <div id="assignButton" style="display:none;">
                                                  <input type="button" class="buttonBg" onclick="getData(document.searchForm.check_List);" value="Click To Assign"/>
                                                  </div>
                                              </td>
                                          </tr>
                                          
                                          <tr>
                                              <td>
                                                  
                                              </td>
                                          </tr>
                                          
                                      </table>
                                </form>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <!--//START DATA RECORD : Record for LeftMenu and Screen Content-->
    
            <!--//START FOOTER : Record for Footer Background and Content-->
            <tr class="footerBg">
                <td align="center"><s:include value="/includes/template/Footer.jsp"/>   </td>
            </tr>
            <!--//END FOOTER : Record for Footer Background and Content-->
            
        </table>
        <!--//END MAIN TABLE : Table for template Structure-->
    
    </body>
</html>
