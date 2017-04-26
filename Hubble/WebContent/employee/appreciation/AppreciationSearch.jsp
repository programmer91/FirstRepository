<%--
     Document   : AppreciationSearch
     Created on : Nov 3, 2015, 3:44:41 PM
     Author     : miracle
--%>

<%@ page contentType="text/html; charset=UTF-8" 
         errorPage="../exception/ErrorDisplay.jsp"%>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%--<%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<%@ page import="com.freeware.gridtag.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd" %>
<html>
    <head>
        <title>Hubble Organization Portal :: Employee Appreciation Search</title>

        <link rel="stylesheet" type="text/css" href="<s:url 
                  value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url 
                  value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url 
                  value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url 
                  value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url 
            value="/includes/javascripts/DBGrid.js"/>"></script>


        <script type="text/JavaScript" src="<s:url 
            value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url 
            value="/includes/javascripts/tabedPanel.js"/>"></script>
        <script type="text/JavaScript" src="<s:url 
            value="/includes/javascripts/CalendarTime.js"/>"></script>
        <script type="text/JavaScript" src="<s:url 
            value="/includes/javascripts/appreciation/AppreciationScripts.js"/>"></script>


        <s:include value="/includes/template/headerScript.html"/>
    </head>
    <body class="bodyGeneral">

        <%!         /* Declarations */
            Connection connection;
            String queryString;
            String strTmp;
            String strSortCol;
            String strSortOrd;
            String submittedFrom;
            String searchSubmitValue;
            int intSortOrd = 0;
            int intCurr;
            boolean blnSortAsc = true;
            private HttpServletRequest httpServletRequest;
            String searchFlag = null;
        %>
        <%System.out.println("in jsp");
        %>
        <!--//START MAIN TABLE : Table for template Structure-->
        <table class="templateTableLogin" align="center" cellpadding="0" 
               cellspacing="0">

            <!--//START HEADER : Record for Header Background and Mirage 
   Logo-->
            <tr class="headerBg">
                <td valign="top">
                    <s:include value="/includes/template/Header.jsp"/>
                </td>
            </tr>
            <!--//END HEADER : Record for Header Background and Mirage Logo-->


            <!--//START DATA RECORD : Record for LeftMenu and Screen Content-->
            <tr>
                <td>
                    <table class="innerTableLogin" cellpadding="0" 
                           cellspacing="0">
                        <tr>

                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                            <td width="150px;" class="leftMenuBgColor" 
                                valign="top">
                                <s:include 
                                    value="/includes/template/LeftMenu.jsp"/>
                            </td>

                            <!--//END DATA COLUMN : Coloumn for LeftMenu-->

                            <!--//START DATA COLUMN : Coloumn for Screen 
   Content-->
                            <td width="850px" class="cellBorder" 
                                valign="top" style="padding: 5px 5px 5px 5px;">
                                <!--//START TABBED PANNEL : -->
                                <ul id="accountTabs" class="shadetabs" >
                                    <li ><a href="#" class="selected" 
                                            rel="employeeSearchTab"  >Appreciation Search </a></li>
                                </ul>

                                <div  style="border:1px solid gray; 
                                      width:840px;height: 526px; overflow:auto; margin-bottom: 1em;">
                                    <div id="employeeSearchTab" 
                                         class="tabcontent"  >

                                        <!-- Over lay start -->
                                        <div id="AppOverlay"></div>              
                                        <div id="specialBoxApp">
                                            <%--       <s:form theme="simple"  align="center" name="ProjectResources" action="%{currentAction}" method="post" enctype="multipart/form-data" onsubmit="return validateForm();"   >--%>
                                            <table align="center" border="0" cellspacing="0" style="width:100%;">
                                                <tr>                               
                                                    <td colspan="2" style="background-color: #288AD1" >
                                                        <h3 style="color:darkblue;" align="left">
                                                            <span id="headerLabel"></span>
                                                        </h3> </td>
                                                    <td colspan="2" style="background-color: #288AD1" align="right">
                                                        <a href="#" onclick="SubTitleOverlay('0')" >
                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> 
                                                        </a>  
                                                    </td>


                                                </tr>
                                                <tr>
                                                    <td >
                                                        <div id="Title" style="width: 350px; height: 180px;"></div>
                                                    </td>
                                                </tr>


                                            </table>
                                            <%--</s:form> --%>
                                        </div>

                                        <!-- Overlay end -->
                                        <table cellpadding="0" 
                                               cellspacing="0" border="0" width="100%">
                                            <tr align="right">
                                                <td class="headerText">
                                                    <img alt="Home"
                                                         src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif"
                                                         width="100%"
                                                         height="13px"
                                                         border="0"/>
                                                    <%
                                                        if (request.getAttribute(ApplicationConstants.RESULT_MSG) != null) {
                                                            out.println(request.getAttribute(ApplicationConstants.RESULT_MSG));
                                                        }%>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>

                                                    <s:form name="frmEmpSearch" action="getMyTeamAppreciation.action" theme="simple">

                                                        <s:hidden id="searchflag" name="searchflag" value="%{searchflag}"/>
                                                        <table 
                                                            cellpadding="1" cellspacing="1" border="0" width="650px" align="center">
                                                            <tr>
                                                                <td 
                                                                    class="fieldLabel">Start&nbsp;Date&nbsp;(mm/dd/yyyy)&nbsp;:</td>
                                                                <td><s:textfield name="startDate" id="startDate" 
                                                                             cssClass="inputTextBlueSmall" value="%{startDate}"/><a 
                                                                        href="javascript:cal2.popup();" style="text-decoration: none;">
                                                                        <img 
                                                                            src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                            width="20" height="20" border="0"></a>
                                                                </td>

                                                                <td 
                                                                    class="fieldLabel">End&nbsp;Date&nbsp;(mm/dd/yyyy)&nbsp;:</td>
                                                                <td>  <s:textfield name="endDate" id="endDate" 
                                                                             cssClass="inputTextBlueSmall" value="%{endDate}"/><a 
                                                                        href="javascript:cal3.popup();" style="text-decoration: none;">
                                                                        <img 
                                                                            src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                            width="20" height="20" border="0"></a>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td 
                                                                    class="fieldLabel">Title:</td>
                                                                <td><s:textfield name="title" id="title" cssClass="inputTextBlue" value=""/>
                                                                <td class="fieldLabel">Subtitle:</td>
                                                                <td><s:textfield name="subtitle" id="subtitle" cssClass="inputTextBlue" 
                                                                             value=""/>

                                                            </tr>
                                                            <script 
                                                                type="text/javaScript">
                                                                    var cal2 = 
                                                                        new CalendarTime(document.forms['frmEmpSearch'].elements['startDate']);
                                                                    cal2.year_scroll = true;
                                                                    cal2.time_comp = false;
                                                                    var cal3 = 
                                                                        new CalendarTime(document.forms['frmEmpSearch'].elements['endDate']);
                                                                    cal3.year_scroll = true;
                                                                    cal3.time_comp = false;

                                                            </script>
                                                            <tr>

                                                                <s:if test="%{searchflag == 'my'}">

                                                                </s:if>
                                                                <s:else>
                                                                    <td   class="fieldLabel">Select EmpName :</td>
                                                                    <td ><s:select list="empnamesList" id="empnameById" name="empnameById" headerKey="-1" headerValue="All" cssClass="inputSelectLarge" value="%{empnameById}"/></td> 
                                                                    <td  class="fieldLabel">Status :</td>
                                                                    <td>  <s:select   name="status" id="status" Class="inputTextBlue" headerKey="-1" headerValue="All"  value="" list="#@java.util.LinkedHashMap@{'Created':'Created','Send':'Send'}"/>

                                                                    </td>   
                                                                </s:else>

                                                            </tr>
                                                            <s:if test="%{searchflag == 'my'}">
                                                                <tr>
                                                                    <td colspan="8" style="padding-right: 25px;" align="right">
                                                                        <s:submit cssClass="buttonBg"  align="right"  value="Search" /> 
                                                                    </td>
                                                                </tr>
                                                            </s:if>
                                                            <s:else>
                                                                <tr>
                                                                    <td colspan="8" align="right">

                                                                        <input type="button" class="buttonBg"  align="right"  value="Add" 
                                                                               onclick="addAppreciation();"/>
                                                                        <s:submit cssClass="buttonBg"  align="right"  value="Search" onclick="compareDates();"/>
                                                                    </td>
                                                                </tr>
                                                            </s:else>


                                                        </table>
                                                    </s:form>
                                                </td>

                                            </tr>
                                            <tr>
                                                <td>
                                                    <%

                                                        if (request.getAttribute("submitFrom") != null) {
                                                            submittedFrom = request.getAttribute("submitFrom").toString();
                                                        }

                                                        if (!"dbGrid".equalsIgnoreCase(submittedFrom)) {
                                                            searchSubmitValue = submittedFrom;
                                                        }

                                                    %>

                                                </td>
                                            </tr>
                                            <tr>
                                                <td>

                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <%
                                                        /* String Variable 
                                                        for storing current position of records in dbgrid*/
                                                        strTmp =
                                                                request.getParameter("txtCurr");

                                                        intCurr = 1;

                                                        if (strTmp != null) {
                                                            intCurr =
                                                                    Integer.parseInt(strTmp);
                                                        }

                                                        /* Specifing 
                                                        Shorting Column */
                                                        strSortCol =
                                                                request.getParameter("Colname");

                                                        if (strSortCol
                                                                == null) {
                                                            strSortCol =
                                                                    "Fname";
                                                        }

                                                        strSortOrd =
                                                                request.getParameter("txtSortAsc");
                                                        if (strSortOrd
                                                                == null) {
                                                            strSortOrd = "ASC";
                                                        }

                                                        searchFlag = request.getParameter("searchflag").toString();

                                                        try {

                                                            /* Getting 
                                                            DataSource using Service Locator */
                                                            connection =
                                                                    ConnectionProvider.getInstance().getConnection();

                                                            //String empReviewAction = "getAppreciationadd.action";
                                                            /* Sql query for retrieving resultset from 
                                                            DataBase */
                                                            if (request.getAttribute(ApplicationConstants.APPRECIATION_SEARCH) != null) {
                                                                // System.out.println("in if jsp");

                                                                queryString = request.getAttribute(ApplicationConstants.APPRECIATION_SEARCH).toString();
                                                               // System.out.println("queryString-->" + queryString);
                                                                //  out.print(queryString);
                                                            }


                                                    %>






                                                    <s:form action="" theme="simple" name="frmDBGrid">
                                                        <s:if test="%{searchflag == 'my'}">
                                                            <table cellpadding="0" cellspacing="0" 
                                                                   width="100%" border="0">


                                                                <tr>
                                                                    <td width="100%">

                                                                        <grd:dbgrid id="tblAppreciation" 
                                                                                    name="tblAppreciation" width="98" pageSize="15"
                                                                        currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="4"
                                                                        dataMember="<%=queryString%>" dataSource="<%=connection%>" 
                                                                                    cssClass="gridTable">

                                                                            <grd:gridpager 
                                                                                imgFirst="../../includes/images/DBGrid/First.gif" 
                                                                                imgPrevious="../../includes/images/DBGrid/Previous.gif"
                                                                                imgNext="../../includes/images/DBGrid/Next.gif" 
                                                                                imgLast="../../includes/images/DBGrid/Last.gif"/>

                                                                            <grd:gridsorter 
                                                                            sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>"
                                                                                imageAscending="../includes/images/DBGrid/ImgAsc.gif"
                                                                                imageDescending="../includes/images/DBGrid/ImgDesc.gif"/>



                                                                            <grd:imagecolumn headerText="Edit" width="4" HAlign="center" imageSrc="../../includes/images/DBGrid/newEdit_17x18.png"
                                                                                             linkUrl="getEmpAppreciationEdit.action?appriceationId={Id}&searchflag=my" imageBorder="0"
                                                                                             imageWidth="16" imageHeight="16" alterText="Click to edit"></grd:imagecolumn> 

                                                                            <grd:textcolumn 
                                                                                dataField="Title" headerText="Title" width="18"/>
                                                                            <grd:anchorcolumn dataField="SubTitle" linkUrl="javascript:SubTitleOverlay('{SubTitle}')" headerText="SubTitle"
                                                                                              linkText="View"  width="6" HAlign="center"/>
                                                                            <%--  <grd:anchorcolumn dataField="SUBJECT" linkUrl="javascript:SubjectOverlay('{SUBJECT}')" headerText="SUBJECT"
                                                                                                linkText="View"  width="6" HAlign="center"/>   --%>


                                                                            <%-- <grd:textcolumn 
                                                                                 dataField="STATUS" headerText="Status" width="8"/> --%>
                                                                            <grd:datecolumn 
                                                                                dataField="CreatedDate" headerText="CreatedDate" dataFormat="MM-dd-yyyy"
                                                                                width="12" sortable="true"/>
                                                                            <%--  <grd:datecolumn 
                                                                                    dataField="CreatedDate" headerText="CreadtedDate" dataFormat="MM-dd-yyyy HH:mm:SS" width="12" sortable="true"/> --%>
                                                                            <grd:textcolumn 
                                                                                dataField="CreatedBy" headerText="CreatedBy" width="8"/>
                                                                            <grd:imagecolumn headerText="Preview" width="4" HAlign="center" 
                                                                                             imageSrc="../../includes/images/DBGrid/View.gif" 
                                                                                             linkUrl="javascript:win_open2('/Hubble/employee/appreciation/getAppreciationPreview.action?appriceationId={Id}')" 

                                                                                             imageBorder="0" imageWidth="16" imageHeight="16" alterText="Click to edit"></grd:imagecolumn>





                                                                        </grd:dbgrid>

                                                                        <input TYPE="hidden" NAME="txtCurr" 
                                                                               VALUE="<%=intCurr%>">
                                                                        <input TYPE="hidden" 
                                                                               NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                                        <input TYPE="hidden" 
                                                                               NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                                                                    </td>
                                                                </tr>
                                                            </table>   
                                                        </s:if>

                                                        <s:elseif test="%{searchflag == 'team'}">
                                                            <table cellpadding="0" cellspacing="0" 
                                                                   width="100%" border="0">


                                                                <tr>
                                                                    <td width="100%">

                                                                        <grd:dbgrid id="tblAppreciation" 
                                                                                    name="tblAppreciation" width="98" pageSize="20"
                                                                        currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="4"
                                                                        dataMember="<%=queryString%>" dataSource="<%=connection%>" 
                                                                                    cssClass="gridTable">

                                                                            <grd:gridpager 
                                                                                imgFirst="../../includes/images/DBGrid/First.gif" 
                                                                                imgPrevious="../../includes/images/DBGrid/Previous.gif"
                                                                                imgNext="../../includes/images/DBGrid/Next.gif" 
                                                                                imgLast="../../includes/images/DBGrid/Last.gif"/>

                                                                            <grd:gridsorter 
                                                                            sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>"
                                                                                imageAscending="../includes/images/DBGrid/ImgAsc.gif"
                                                                                imageDescending="../includes/images/DBGrid/ImgDesc.gif"/>



                                                                            <grd:imagecolumn headerText="Edit" width="4" HAlign="center" imageSrc="../../includes/images/DBGrid/newEdit_17x18.png"
                                                                                             linkUrl="getEmpAppreciationEdit.action?appriceationId={Id}&searchflag=team" imageBorder="0"
                                                                                             imageWidth="16" imageHeight="16" alterText="Click to edit"></grd:imagecolumn> 

                                                                            <grd:textcolumn 
                                                                                dataField="Title" headerText="Title" width="18"/>
                                                                            <grd:anchorcolumn dataField="SubTitle" linkUrl="javascript:SubTitleOverlay('{SubTitle}')" headerText="SubTitle"
                                                                                              linkText="View"  width="6" HAlign="center"/>
                                                                            <%--   <grd:anchorcolumn dataField="SUBJECT" linkUrl="javascript:SubjectOverlay('{SUBJECT}')" headerText="SUBJECT"
                                                                                                 linkText="View"  width="6" HAlign="center"/>   --%>


                                                                            <grd:textcolumn 
                                                                                dataField="STATUS" headerText="Status" width="8"/>
                                                                            <grd:datecolumn 
                                                                                dataField="CreatedDate" headerText="CreatedDate" dataFormat="MM-dd-yyyy"
                                                                                width="12" sortable="true"/>
                                                                            <%--  <grd:datecolumn 
                                                                                    dataField="CreatedDate" headerText="CreadtedDate" dataFormat="MM-dd-yyyy HH:mm:SS" width="12" sortable="true"/> --%>
                                                                            <grd:textcolumn 
                                                                                dataField="CreatedBy" headerText="CreatedBy" width="8"/>
                                                                            <grd:imagecolumn headerText="Preview" width="4" HAlign="center" 
                                                                                             imageSrc="../../includes/images/DBGrid/View.gif" 
                                                                                             linkUrl="javascript:win_open2('/Hubble/employee/appreciation/getAppreciationPreview.action?appriceationId={Id}')" 

                                                                                             imageBorder="0" imageWidth="16" imageHeight="16" alterText="Click to edit"></grd:imagecolumn>





                                                                        </grd:dbgrid>

                                                                        <input TYPE="hidden" NAME="txtCurr" 
                                                                               VALUE="<%=intCurr%>">
                                                                        <input TYPE="hidden" 
                                                                               NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                                        <input TYPE="hidden" 
                                                                               NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </s:elseif>

                                                        <s:else>
                                                            <table cellpadding="0" cellspacing="0" 
                                                                   width="100%" border="0">


                                                                <tr>
                                                                    <td width="100%">

                                                                        <grd:dbgrid id="tblAppreciation" 
                                                                                    name="tblAppreciation" width="98" pageSize="20"
                                                                        currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="4"
                                                                        dataMember="<%=queryString%>" dataSource="<%=connection%>" 
                                                                                    cssClass="gridTable">

                                                                            <grd:gridpager 
                                                                                imgFirst="../../includes/images/DBGrid/First.gif" 
                                                                                imgPrevious="../../includes/images/DBGrid/Previous.gif"
                                                                                imgNext="../../includes/images/DBGrid/Next.gif" 
                                                                                imgLast="../../includes/images/DBGrid/Last.gif"/>

                                                                            <grd:gridsorter 
                                                                            sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>"
                                                                                imageAscending="../includes/images/DBGrid/ImgAsc.gif"
                                                                                imageDescending="../includes/images/DBGrid/ImgDesc.gif"/>



                                                                            <grd:imagecolumn headerText="Edit" width="4" HAlign="center" imageSrc="../../includes/images/DBGrid/newEdit_17x18.png"
                                                                                             linkUrl="getEmpAppreciationEdit.action?appriceationId={Id}&searchflag=opt" imageBorder="0"
                                                                                             imageWidth="16" imageHeight="16" alterText="Click to edit"></grd:imagecolumn> 

                                                                            <grd:textcolumn 
                                                                                dataField="Title" headerText="Title" width="18"/>
                                                                            <grd:anchorcolumn dataField="SubTitle" linkUrl="javascript:SubTitleOverlay('{SubTitle}')" headerText="SubTitle"
                                                                                              linkText="View"  width="6" HAlign="center"/>
                                                                            <%--  <grd:anchorcolumn dataField="SUBJECT" linkUrl="javascript:SubjectOverlay('{SUBJECT}')" headerText="SUBJECT"
                                                                                                linkText="View"  width="6" HAlign="center"/>   --%>


                                                                            <grd:textcolumn 
                                                                                dataField="STATUS" headerText="Status" width="8"/>
                                                                            <grd:datecolumn 
                                                                                dataField="CreatedDate" headerText="CreatedDate" dataFormat="MM-dd-yyyy"
                                                                                width="12" sortable="true"/>
                                                                            <%--  <grd:datecolumn 
                                                                                    dataField="CreatedDate" headerText="CreadtedDate" dataFormat="MM-dd-yyyy HH:mm:SS" width="12" sortable="true"/> --%>
                                                                            <grd:textcolumn 
                                                                                dataField="CreatedBy" headerText="CreatedBy" width="8"/>
                                                                            <grd:imagecolumn headerText="Preview" width="4" HAlign="center" 
                                                                                             imageSrc="../../includes/images/DBGrid/View.gif" 
                                                                                             linkUrl="javascript:win_open2('/Hubble/employee/appreciation/getAppreciationPreview.action?appriceationId={Id}')" 

                                                                                             imageBorder="0" imageWidth="16" imageHeight="16" alterText="Click to edit"></grd:imagecolumn>





                                                                        </grd:dbgrid>

                                                                        <input TYPE="hidden" NAME="txtCurr" 
                                                                               VALUE="<%=intCurr%>">
                                                                        <input TYPE="hidden" 
                                                                               NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                                        <input TYPE="hidden" 
                                                                               NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </s:else>
                                                    </s:form>




                                                    <%
                                                            connection.close();
                                                            connection = null;
                                                        } catch (Exception ex) {
                                                            out.println(ex.toString());
                                                        } finally {
                                                            if (connection
                                                                    != null) {
                                                                connection.close();
                                                                connection = null;
                                                            }
                                                        }
                                                    %>
                                                </td>
                                            </tr>
                                        </table>

                                        <!-- End Overlay -->
                                        <!-- Start Special Centered Box -->

                                        <%-- </sx:div > --%>
                                    </div>
                                    <!--//END TAB : -->
                                    <%-- </sx:tabbedpanel> --%>
                                </div>
                                <script type="text/javascript">

                                    var countries=new 
                                    ddtabcontent("accountTabs")
                                    countries.setpersist(false)
                                    countries.setselectedClassTarget("link") //"link" or "linkparent"
                                    countries.init()

                                </script>
                                <!--//END TABBED PANNEL : -->
                            </td>
                            <!--//END DATA COLUMN : Coloumn for Screen 
   Content-->
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


